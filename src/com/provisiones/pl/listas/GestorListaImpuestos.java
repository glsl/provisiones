package com.provisiones.pl.listas;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLImpuestos;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Sesion;
import com.provisiones.misc.Utils;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.ImpuestoRecursoTabla;

public class GestorListaImpuestos implements Serializable 
{
	private static final long serialVersionUID = 658962915704318075L;

	private static Logger logger = LoggerFactory.getLogger(GestorListaImpuestos.class.getName());

	private String sNURCAT = "";
	private String sCOSBAC = "";
	
	//Buscar activos
	private String sCOACES = "";

	private String sCOPOIN = "";
	private String sNOMUIN = "";	
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";

	private transient ArrayList<ActivoTabla> tablaactivos = null;
	private transient ActivoTabla activoseleccionado = null;
	
	private transient ArrayList<ImpuestoRecursoTabla> tablaimpuestos = null;
	private transient ImpuestoRecursoTabla impuestoseleccionado = null;
	
	public GestorListaImpuestos()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorListaImpuestos...");	
		}
	}
	
	public void borrarCamposActivo()
	{
		this.sCOPOIN = "";
		this.sNOMUIN = "";
		this.sNOPRAC = "";
		this.sNOVIAS = "";
		this.sNUPIAC = "";
		this.sNUPOAC = "";
		this.sNUPUAC = "";
    	
    	this.setActivoseleccionado(null);
    	this.setTablaactivos(null);
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	borrarCamposActivo();
    }
    
	public void borrarCamposImpuesto()
	{
		this.sCOACES = "";
    	
    	this.setImpuestoseleccionado(null);
    	this.setTablaimpuestos(null);
	}
    
    public void limpiarPlantilla(ActionEvent actionEvent) 
    {
    	borrarCamposActivo();
    	borrarCamposImpuesto();
    }
    
	public void buscarActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			ActivoTabla filtro = new ActivoTabla(
					"", sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
					sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
					sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");
			
			this.setTablaactivos(CLImpuestos.buscarActivosConImpuestos(filtro));
			
			String sMsg = "Encontrados "+getTablaactivos().size()+" activos relacionados.";
			msg = Utils.pfmsgInfo(sMsg);
			logger.info(sMsg);
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void seleccionarActivo(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
	    	
	    	this.sCOACES  = activoseleccionado.getCOACES();
	    	
	    	String sMsg = "Activo '"+ sCOACES +"' Seleccionado.";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	logger.info(sMsg);
	    	
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void buscarImpuestos(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			if (sCOACES.equals(""))
			{
				sMsg = "ERROR: Debe informar el Activo para realizar una b�squeda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else
			{
				try
				{
					this.sNURCAT  = CLReferencias.referenciaCatastralAsociada(Integer.parseInt(sCOACES));
			    	

			    	if (!sNURCAT.equals("") && CLReferencias.estadoReferencia(sNURCAT).equals("A") )
					{
			    		this.tablaimpuestos = CLImpuestos.buscarImpuestosActivos(Integer.parseInt(sCOACES));
			    		
			    		sMsg = "Encontrados "+getTablaimpuestos().size()+" impuestos relacionados.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
					}
			    	else
			    	{
			    		sMsg = "ERROR: No existe referencia catastral de alta para el activo consultado.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
			        }
				}
				catch(NumberFormatException nfe)
				{
					sMsg = "ERROR: El activo debe ser num�rico. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
			}
			
	    	FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void cargarDetallesImpuesto(ActionEvent actionEvent) 
    { 
		String sPagina = ".";
		
		if (ConnectionManager.comprobarConexion())
		{
			String sMsg = "";
			
			if (impuestoseleccionado != null)
			{

				this.sNURCAT = impuestoseleccionado.getNURCAT();
		    	this.sCOSBAC = impuestoseleccionado.getCOSBAC();
		    	
		    	logger.debug("sNURCAT:|"+sNURCAT+"|");
		    	logger.debug("sCOSBAC:|"+sCOSBAC+"|");
		    	
		    	
		    	String sCodImpuesto = Long.toString(CLImpuestos.buscarCodigoImpuesto(sNURCAT, sCOSBAC));
		    	logger.debug("sCodImpuesto:|"+sCodImpuesto+"|");
		    	
		    	Sesion.guardaDetalle(sCodImpuesto);
		    	Sesion.limpiarHistorial();
		    	Sesion.guardarHistorial("listaimpuestos.xhtml","GestorDetallesImpuesto");

		    	sPagina = "detallesimpuesto.xhtml";
		    	
		    	
				try 
				{
					sMsg = "Redirigiendo hacia '"+sPagina+"'";
					logger.info(sMsg);
					FacesContext.getCurrentInstance().getExternalContext().redirect(sPagina);
				}
				catch (IOException e)
				{
					FacesMessage msg;
					
					sMsg = "ERROR: Ocurri� un problema al acceder a los detalles. Por favor, avise a soporte.";
					
					msg = Utils.pfmsgFatal(sMsg);
					logger.error(sMsg);
					
					FacesContext.getCurrentInstance().addMessage(null, msg);
					

				}
			}
			else
			{
				FacesMessage msg;

				sMsg = "ERROR: No se ha seleccionado una Cuota.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
				
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			


		}

		//return sPagina;
    }

	public String getsNURCAT() {
		return sNURCAT;
	}

	public void setsNURCAT(String sNURCAT) {
		this.sNURCAT = sNURCAT;
	}

	public String getsCOSBAC() {
		return sCOSBAC;
	}

	public void setsCOSBAC(String sCOSBAC) {
		this.sCOSBAC = sCOSBAC;
	}

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
	}

	public String getsCOPOIN() {
		return sCOPOIN;
	}

	public void setsCOPOIN(String sCOPOIN) {
		this.sCOPOIN = sCOPOIN;
	}

	public String getsNOMUIN() {
		return sNOMUIN;
	}

	public void setsNOMUIN(String sNOMUIN) {
		this.sNOMUIN = sNOMUIN;
	}

	public String getsNOPRAC() {
		return sNOPRAC;
	}

	public void setsNOPRAC(String sNOPRAC) {
		this.sNOPRAC = sNOPRAC;
	}

	public String getsNOVIAS() {
		return sNOVIAS;
	}

	public void setsNOVIAS(String sNOVIAS) {
		this.sNOVIAS = sNOVIAS;
	}

	public String getsNUPIAC() {
		return sNUPIAC;
	}

	public void setsNUPIAC(String sNUPIAC) {
		this.sNUPIAC = sNUPIAC;
	}

	public String getsNUPOAC() {
		return sNUPOAC;
	}

	public void setsNUPOAC(String sNUPOAC) {
		this.sNUPOAC = sNUPOAC;
	}

	public String getsNUPUAC() {
		return sNUPUAC;
	}

	public void setsNUPUAC(String sNUPUAC) {
		this.sNUPUAC = sNUPUAC;
	}

	public ArrayList<ActivoTabla> getTablaactivos() {
		return tablaactivos;
	}

	public void setTablaactivos(ArrayList<ActivoTabla> tablaactivos) {
		this.tablaactivos = tablaactivos;
	}

	public ActivoTabla getActivoseleccionado() {
		return activoseleccionado;
	}

	public void setActivoseleccionado(ActivoTabla activoseleccionado) {
		this.activoseleccionado = activoseleccionado;
	}

	public ArrayList<ImpuestoRecursoTabla> getTablaimpuestos() {
		return tablaimpuestos;
	}

	public void setTablaimpuestos(ArrayList<ImpuestoRecursoTabla> tablaimpuestos) {
		this.tablaimpuestos = tablaimpuestos;
	}

	public ImpuestoRecursoTabla getImpuestoseleccionado() {
		return impuestoseleccionado;
	}

	public void setImpuestoseleccionado(ImpuestoRecursoTabla impuestoseleccionado) {
		this.impuestoseleccionado = impuestoseleccionado;
	}
}