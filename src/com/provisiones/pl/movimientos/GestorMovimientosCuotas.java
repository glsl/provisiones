package com.provisiones.pl.movimientos;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.ll.CLCuotas;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.ActivoTabla;
import com.provisiones.types.CuotaTabla;
import com.provisiones.types.MovimientoCuota;

public class GestorMovimientosCuotas implements Serializable 
{

	private static final long serialVersionUID = 558593056565873600L;
	
	static String sClassName = GestorMovimientosCuotas.class.getName();

	private String sCODTRN = ValoresDefecto.DEF_E2_CODTRN;
	private String sCOTDOR = ValoresDefecto.DEF_COTDOR;
	private String sIDPROV = ValoresDefecto.DEF_IDPROV;
	private String sCOACCI = "";
	private String sCOENGP = ValoresDefecto.DEF_COENGP;

	private String sCOACES = "";
	
	private String sCOCLDO = "";
	private String sDesCOCLDO = "";
	
	private String sNUDCOM = "";
	private String sNOMCOC = "";
	private String sNODCCO = "";
	

	private String sCOSBAC = "";
	private String sDesCOSBAC = "";
	private String sFIPAGO = "";
	private String sFFPAGO = "";
	private String sIMCUCO = "";
	private String sFAACTA = "";
	private String sPTPAGO = "";
	private String sDesPTPAGO = "";
	private String sOBTEXC = "";
	
	private String sOBDEER = "";
	
	private String sCOPOIN = "";
	private String sNOMUIN = "";
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	
	
	private ActivoTabla activoseleccionado = null;
	private ArrayList<ActivoTabla> tablaactivos = null;

	private CuotaTabla cuotaseleccionada = null;
	private ArrayList<CuotaTabla> tablacuotas = null;

	
	public GestorMovimientosCuotas()
	{
		Utils.standardIO2File("");//Salida por fichero de texto
	}
	
	public void borrarCamposCuota()
	{
		this.sCOCLDO = "";
		this.sDesCOCLDO = "";
		this.sNUDCOM = "";
		this.sCOSBAC = "";
		this.sDesCOSBAC = "";
		this.sFIPAGO = "";
		this.sFFPAGO = "";
		this.sIMCUCO = "";
		this.sFAACTA = "";
		this.sPTPAGO = "";
		this.sDesPTPAGO = "";
		this.sOBTEXC = "";
		
    	this.setCuotaseleccionada(null);
    	this.setTablacuotas(null);
		
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
	
	public void limpiarPlantillaCuotas(ActionEvent actionEvent) 
    {  
    	this.sCOACES = "";
    	borrarCamposCuota();
    	borrarCamposActivo();	
    } 

    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  

    	borrarCamposActivo();
   	
    }
    
	public void buscaActivos (ActionEvent actionEvent)
	{
		
		String sMethod = "buscaActivos";
		
		
		FacesMessage msg;
		
		ActivoTabla buscaactivos = new ActivoTabla(
				sCOACES.toUpperCase(), sCOPOIN.toUpperCase(), sNOMUIN.toUpperCase(),
				sNOPRAC.toUpperCase(), sNOVIAS.toUpperCase(), sNUPIAC.toUpperCase(), 
				sNUPOAC.toUpperCase(), sNUPUAC.toUpperCase(), "");
		
		Utils.debugTrace(true, sClassName, sMethod, "Buscando Activos...");
		
		this.setTablaactivos(CLCuotas.buscarActivosConCuotas(buscaactivos));
		
		Utils.debugTrace(true, sClassName, sMethod, "Encontrados "+getTablaactivos().size()+" activos relacionados.");

		msg = new FacesMessage("Encontrados "+getTablaactivos().size()+" activos relacionados.");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
	}
	
	public void seleccionarActivo(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarActivo";

    	FacesMessage msg;
    	
    	
    	
    	//this.sCOACESBuscado = activoseleccionado.getCOACES();
    	
    	this.sCOACES  = activoseleccionado.getCOACES();
    	
    	msg = new FacesMessage("Activo "+ sCOACES +" Seleccionado.");
    	
    	Utils.debugTrace(true, sClassName, sMethod, "Activo seleccionado: |"+sCOACES+"|");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		//return "listacomunidadesactivos.xhtml";
    }
	
	public void cargarCuotas(ActionEvent actionEvent)
	{
		String sMethod = "cargarCuotas";
		
		FacesMessage msg;
		
		Utils.debugTrace(true, sClassName, sMethod, "Buscando cuotas...");
		
		this.tablacuotas = CLCuotas.buscarCuotasActivo(sCOACES.toUpperCase());
		
		Utils.debugTrace(true, sClassName, sMethod, "Encontradas "+getTablacuotas().size()+" cuotas relacionadas.");

		msg = new FacesMessage("Encontradas "+getTablacuotas().size()+" cuotas relacionadas.");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
				
	}
	
	public void seleccionarCuota(ActionEvent actionEvent) 
    {  
    	
    	String sMethod = "seleccionarCuota";

    	FacesMessage msg;
    	
    	this.sCOCLDO = cuotaseleccionada.getCOCLDO(); 
    	this.sDesCOCLDO = cuotaseleccionada.getDCOCLDO();
    	this.sNUDCOM = cuotaseleccionada.getNUDCOM();
    	this.sCOSBAC = cuotaseleccionada.getCOSBAC();
    	this.sDesCOSBAC = cuotaseleccionada.getDCOSBAC();
    	this.sFIPAGO = cuotaseleccionada.getFIPAGO();
    	this.sFFPAGO = cuotaseleccionada.getFFPAGO();
    	this.sIMCUCO = cuotaseleccionada.getIMCUCO();
    	this.sFAACTA = cuotaseleccionada.getFAACTA();
    	this.sPTPAGO = cuotaseleccionada.getPTPAGO();
    	this.sDesPTPAGO = cuotaseleccionada.getDPTPAGO();
    	this.sOBTEXC = cuotaseleccionada.getOBTEXC();
    	

    	//comprobar
    	
    	msg = new FacesMessage("'"+ sDesCOSBAC +"' Seleccionado.");
    	
    	Utils.debugTrace(true, sClassName, sMethod, "Cuota seleccionada: |"+sCOACES+"|");
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		//return "listacomunidadesactivos.xhtml";
    }
	
	public void registraDatos(ActionEvent actionEvent)
	{
		String sMethod = "registraDatos";
		
		Utils.standardIO2File("");//Salida por fichero de texto
		
		//MovimientoComunidad movimiento = new MovimientoComunidad (sCODTRN.toUpperCase(), sCOTDOR.toUpperCase(), sIDPROV.toUpperCase(), sCOACCI.toUpperCase(), sCOENGP.toUpperCase(), sCOCLDO.toUpperCase(), sNUDCOM.toUpperCase(), sBITC10.toUpperCase(), sCOACES.toUpperCase(), sBITC01.toUpperCase(), sNOMCOC.toUpperCase(), sBITC02.toUpperCase(), sNODCCO.toUpperCase(), sBITC03.toUpperCase(), sNOMPRC.toUpperCase(), sBITC04.toUpperCase(), sNUTPRC.toUpperCase(), sBITC05.toUpperCase(), sNOMADC.toUpperCase(), sBITC06.toUpperCase(), sNUTADC.toUpperCase(), sBITC07.toUpperCase(), sNODCAD.toUpperCase(), sBITC08.toUpperCase(), sNUCCEN.toUpperCase(), sNUCCOF.toUpperCase(), sNUCCDI.toUpperCase(), sNUCCNT.toUpperCase(), sBITC09.toUpperCase(), sOBTEXC.toUpperCase(), sOBDEER.toUpperCase());
		MovimientoCuota movimiento = new MovimientoCuota (
				sCODTRN.toUpperCase(), 
				sCOTDOR.toUpperCase(), 
				sIDPROV.toUpperCase(), 
				sCOACCI.toUpperCase(), 
				sCOCLDO.toUpperCase(), 
				sNUDCOM.toUpperCase(), 
				sCOENGP.toUpperCase(), 
				sCOACES.toUpperCase(), 
				ValoresDefecto.DEF_COGRUG_E2, 
				ValoresDefecto.DEF_COTACA_E2, 
				Utils.compruebaPago(false,sCOSBAC.toUpperCase()), 
				"", 
				Utils.compruebaFecha(sFIPAGO.toUpperCase()), 
				"", 
				Utils.compruebaFecha(sFFPAGO.toUpperCase()), 
				"", 
				Utils.compruebaImporte(sIMCUCO.toUpperCase()), 
				"", 
				Utils.compruebaFecha(sFAACTA.toUpperCase()), 
				"", 
				sPTPAGO.toUpperCase(),
				"", 
				sOBTEXC.toUpperCase(), 
				sOBDEER.toUpperCase());
		
		FacesMessage msg;
		
		String sMsg = "";
		
		int iSalida = CLCuotas.registraMovimiento(movimiento);
		
		Utils.debugTrace(true, sClassName, sMethod, "Codigo de salida:"+iSalida);
		
		switch (iSalida) 
		{
		case 0: //Sin errores
			sMsg = "El movimiento se ha registrado correctamente.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(sMsg,null);
			break;

		case -1: //Error 001 - CODIGO DE ACCION DEBE SER A,M o B
			sMsg = "ERROR:001 - No se ha elegido una acccion correcta. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -3: //Error 003 - NO EXISTE EL ACTIVO
			sMsg = "ERROR:003 - El activo elegido no esta registrado en el sistema. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -4: //Error 004 - CIF DE LA COMUNIDAD NO PUEDE SER BLANCO O NULO
			sMsg = "ERROR:004 - No se ha informado el numero de documento. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -33: //Error 033 - LA FECHA DE PRIMER PAGO DEBE SER LOGICA Y OBLIGATORIA
			sMsg = "ERROR:033 - La fecha del primer pago es obligatoria. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -34: //Error 034 - LA FECHA DE ULTIMO PAGO DEBE SER LOGICA Y OBLIGATORIA
			sMsg = "ERROR:034 - La fecha del ultimo pago es obligatoria. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -35: //Error 035 - LA FECHA DE ULTIMO PAGO NO DEBE DE SER MENOR QUE LA FECHA DE PRIMER PAGO
			sMsg = "ERROR:35 - La fecha del ultimo pago no puede ser menor que la del primero.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -36: //Error 036 - IMPORTE DE CUOTA TIENE QUE SER MAYOR DE CERO
			sMsg = "ERROR:036 - El importe de la cuota tiene ser mayor que cero. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -41: //Error 041 - LA COMUNIDAD NO EXISTE EN LA TABLA DE COMUNIDADES GMAE10
			sMsg = "ERROR:041 - La comunidad propocionada no esta registrada en el sistema. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;


		case -42: //Error 042 - LA RELACION ACTIVO-COMUNIDAD YA EXISTE EN GMAE12. NO SE PUEDE REALIZAR EL ALTA
			sMsg = "ERROR:042 - El activo proporcionado esta asociado a otra comunidad. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -43: //Error 043 - LA RELACION ACTIVO-COMUNIDAD NO EXISTE EN GMAE12. NO SE PUEDE REALIZAR LA MODIFICACION
			sMsg = "ERROR:043 - El activo prorcionado no pertenece a la comunidad. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -44: //Error 044 - NO EXISTE PERIOCIDAD DE PAGO
			sMsg = "ERROR:044 - La periodicidad de pago es obligatoria. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -45: //Error 045 - LA RELACION ACTIVO-COMUNIDAD NO EXISTE EN GMAE12. NO SE PUEDE REALIZAR LA BAJA
			sMsg = "ERROR:045 - El activo prorcionado no pertenece a la comunidad. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -46: //Error 046 - LA FECHA DEL ACTA DEBE SER LOGICA Y OBLIGATORIA 
			sMsg = "ERROR:046 - La fecha de acta es obligatoria. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -801: //Error 801 - alta de una cuota en alta
			sMsg = "ERROR:801 - La cuota ya esta dada de alta. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -802: //Error 802 - cuota de baja no puede recibir mas movimientos
			sMsg = "ERROR:802 - La cuota esta baja y no puede recibir mas movimientos. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -803: //Error 803 - estado no disponible
			sMsg = "ERROR:803 - El estado de la cuota informada no esta disponible. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -804: //Error 804 - modificacion sin cambios
			sMsg = "ERROR:804 - No hay modificaciones que realizar. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;
			
		case -805: //Error 805 - error en importe
			sMsg = "ERROR:805 - El campo importe no se ha informado correctamente. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
			break;

		case -900: //Error 900 - al crear un movimiento
			sMsg = "ERROR:900 - Se ha producido un error al registrar el movimiento. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -901: //Error 901 - error y rollback - error al crear la cuota
			sMsg = "ERROR:901 - Se ha producido un error al registrar la cuota. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;
			
		case -902: //Error 902 - error y rollback - error al registrar la relaccion
			sMsg = "ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -903: //Error 903 - error y rollback - error al cambiar el estado
			sMsg = "ERROR:903 - Se ha producido un error al cambiar el estado de la cuota. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		case -904: //Error 904 - error y rollback - error al modificar la cuota
			sMsg = "ERROR:904 - Se ha producido un error al modificar la cuota. Por favor, revise los datos.";
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;

		default: //error generico
			sMsg = "ERROR:"+iSalida+" - La operacion solicitada ha producido un error desconocido. Por favor, revise los datos."; 
			Utils.debugTrace(true, sClassName, sMethod, sMsg);
			msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
			break;
		}
		
		
		Utils.debugTrace(true, sClassName, sMethod, "Finalizadas las comprobaciones.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

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

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES;
	}

	public String getsCODTRN() {
		return sCODTRN;
	}

	public void setsCODTRN(String sCODTRN) {
		this.sCODTRN = sCODTRN;
	}

	public String getsCOTDOR() {
		return sCOTDOR;
	}

	public void setsCOTDOR(String sCOTDOR) {
		this.sCOTDOR = sCOTDOR;
	}

	public String getsIDPROV() {
		return sIDPROV;
	}

	public void setsIDPROV(String sIDPROV) {
		this.sIDPROV = sIDPROV;
	}

	public String getsCOACCI() {
		return sCOACCI;
	}

	public void setsCOACCI(String sCOACCI) {
		this.sCOACCI = sCOACCI;
	}

	public String getsCOENGP() {
		return sCOENGP;
	}

	public void setsCOENGP(String sCOENGP) {
		this.sCOENGP = sCOENGP;
	}

	public String getsCOCLDO() {
		return sCOCLDO;
	}

	public void setsCOCLDO(String sCOCLDO) {
		this.sCOCLDO = sCOCLDO;
	}

	public String getsNUDCOM() {
		return sNUDCOM;
	}

	public void setsNUDCOM(String sNUDCOM) {
		this.sNUDCOM = sNUDCOM;
	}

	public String getsNOMCOC() {
		return sNOMCOC;
	}

	public void setsNOMCOC(String sNOMCOC) {
		this.sNOMCOC = sNOMCOC;
	}

	public String getsNODCCO() {
		return sNODCCO;
	}

	public void setsNODCCO(String sNODCCO) {
		this.sNODCCO = sNODCCO;
	}

	public String getsCOSBAC() {
		return sCOSBAC;
	}

	public void setsCOSBAC(String sCOSBAC) {
		this.sCOSBAC = sCOSBAC;
	}

	public String getsFIPAGO() {
		return sFIPAGO;
	}

	public void setsFIPAGO(String sFIPAGO) {
		this.sFIPAGO = sFIPAGO;
	}

	public String getsFFPAGO() {
		return sFFPAGO;
	}

	public void setsFFPAGO(String sFFPAGO) {
		this.sFFPAGO = sFFPAGO;
	}

	public String getsIMCUCO() {
		return sIMCUCO;
	}

	public void setsIMCUCO(String sIMCUCO) {
		this.sIMCUCO = sIMCUCO;
	}

	public String getsFAACTA() {
		return sFAACTA;
	}

	public void setsFAACTA(String sFAACTA) {
		this.sFAACTA = sFAACTA;
	}

	public String getsPTPAGO() {
		return sPTPAGO;
	}

	public void setsPTPAGO(String sPTPAGO) {
		this.sPTPAGO = sPTPAGO;
	}

	public String getsOBTEXC() {
		return sOBTEXC;
	}

	public void setsOBTEXC(String sOBTEXC) {
		this.sOBTEXC = sOBTEXC;
	}

	public String getsOBDEER() {
		return sOBDEER;
	}

	public void setsOBDEER(String sOBDEER) {
		this.sOBDEER = sOBDEER;
	}

	public ActivoTabla getActivoseleccionado() {
		return activoseleccionado;
	}

	public void setActivoseleccionado(ActivoTabla activoseleccionado) {
		this.activoseleccionado = activoseleccionado;
	}

	public ArrayList<ActivoTabla> getTablaactivos() {
		return tablaactivos;
	}

	public void setTablaactivos(ArrayList<ActivoTabla> tablaactivos) {
		this.tablaactivos = tablaactivos;
	}

	public CuotaTabla getCuotaseleccionada() {
		return cuotaseleccionada;
	}

	public void setCuotaseleccionada(CuotaTabla cuotaseleccionada) {
		this.cuotaseleccionada = cuotaseleccionada;
	}

	public ArrayList<CuotaTabla> getTablacuotas() {
		return tablacuotas;
	}

	public void setTablacuotas(ArrayList<CuotaTabla> tablacuotas) {
		this.tablacuotas = tablacuotas;
	}

	public String getsDesCOSBAC() {
		return sDesCOSBAC;
	}

	public void setsDesCOSBAC(String sDesCOSBAC) {
		this.sDesCOSBAC = sDesCOSBAC;
	}

	public String getsDesPTPAGO() {
		return sDesPTPAGO;
	}

	public void setsDesPTPAGO(String sDesPTPAGO) {
		this.sDesPTPAGO = sDesPTPAGO;
	}

	public String getsDesCOCLDO() {
		return sDesCOCLDO;
	}

	public void setsDesCOCLDO(String sDesCOCLDO) {
		this.sDesCOCLDO = sDesCOCLDO;
	}
	
	
	
}