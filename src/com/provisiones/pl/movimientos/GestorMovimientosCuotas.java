package com.provisiones.pl.movimientos;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.ll.CLActivos;
import com.provisiones.ll.CLCuotas;
import com.provisiones.ll.CLReferencias;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;
import com.provisiones.types.Nota;
import com.provisiones.types.movimientos.MovimientoCuota;
import com.provisiones.types.tablas.ActivoTabla;
import com.provisiones.types.tablas.CuotaTabla;

public class GestorMovimientosCuotas implements Serializable 
{

	private static final long serialVersionUID = 558593056565873600L;
	
	private static Logger logger = LoggerFactory.getLogger(GestorMovimientosCuotas.class.getName());

	private long liCodCuota = 0;
	
	//Accion
	private String sCOACCI = "";

	//Buscar activos
	private String sCOACES = "";

	//Filtro activos
	private String sCOPOIN = "";
	private String sNOMUIN = "";
	private String sNOPRAC = "";
	private String sNOVIAS = "";
	private String sNUPIAC = "";
	private String sNUPOAC = "";
	private String sNUPUAC = "";
	private String sNUFIRE = "";
	
	private String sNURCAT = "";
	
	//Filtro cuotas activo
	private String sCOSBACFA = "";
	private String sFIPAGOFA = "";
	private String sFFPAGOFA = "";
	private String sIMCUCOFA = "";
	private String sFAACTAFA = "";
	private String sPTPAGOFA = "";
	private String sComparadorFA = "";
	private boolean bSeleccionadoFA = true;
	
	//Comunidad
	private String sCOCLDO = "";
	private String sDesCOCLDO = "";
	private String sNUDCOM = "";
	private String sNOMCOC = "";
	private String sNODCCO = "";
	
	//Pago
	private String sCOSBAC = "";
	private String sDesCOSBAC = "";
	private String sFIPAGO = "";
	private String sFFPAGO = "";
	private String sIMCUCO = "";
	private String sFAACTA = "";
	private String sPTPAGO = "";
	private String sDesPTPAGO = "";

	//Observaciones
	private String sOBTEXC = "";
	
	//Notas
	private String sNota = "";
	private String sNotaOriginal = "";
	private boolean bConNotas = false;
	
	private transient ActivoTabla activoseleccionado = null;
	private transient ArrayList<ActivoTabla> tablaactivos = null;

	private transient CuotaTabla cuotaseleccionada = null;
	private transient ArrayList<CuotaTabla> tablacuotas = null;
	
	private Map<String,String> tiposcosbacHM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposptpagoHM = new LinkedHashMap<String, String>();
	
	private Map<String,String> tiposcomparaimporteHM = new LinkedHashMap<String, String>();

	
	public GestorMovimientosCuotas()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorMovimientosCuotas...");
			
			tiposcosbacHM.put("Comunidad",	                   	"0");  
			tiposcosbacHM.put("Ordinaria",                     	"1");  
			tiposcosbacHM.put("Extras Comunidad",              	"2");  
			tiposcosbacHM.put("Mancomunidad",                  	"3");  
			tiposcosbacHM.put("Extras Mancomunidad",           	"4");  
			tiposcosbacHM.put("Obras comunidad",               	"5");
			
			tiposptpagoHM.put("APERIODICO",      "1");
			tiposptpagoHM.put("MENSUAL",         "2");
			tiposptpagoHM.put("BIMENSUAL",       "3");
			tiposptpagoHM.put("TRIMESTRAL",      "4");
			tiposptpagoHM.put("CUATRIMESTRAL",   "5");
			tiposptpagoHM.put("SEMESTRAL",       "6");
			tiposptpagoHM.put("ANUAL",           "7");
			tiposptpagoHM.put("VARIOS PERIODOS", "8");
			
			tiposcomparaimporteHM.put("Igual a",    		"=");
			tiposcomparaimporteHM.put("Mayor o igual a",	">=");
			tiposcomparaimporteHM.put("Menor o igual a",	"<=");
		}
	}
	
	public void borrarCamposFiltroActivo()
	{
    	this.sCOPOIN = "";
    	this.sNOMUIN = "";
    	this.sNOPRAC = "";
    	this.sNOVIAS = "";
    	this.sNUPIAC = "";
    	this.sNUPOAC = "";
    	this.sNUPUAC = "";
    	this.sNUFIRE = "";
    	
    	this.sNURCAT = "";
	}
	
	public void borrarResultadosActivo()
	{
		this.sCOACES = "";
		
    	this.activoseleccionado = null;
    	this.tablaactivos = null;
	}
	
    public void limpiarPlantillaActivo(ActionEvent actionEvent) 
    {  
    	borrarCamposFiltroActivo();
    	borrarResultadosActivo();
    }
    
	public void borrarCamposFiltroCuotasActivo()
	{
		this.sCOSBACFA = "";
		this.sFIPAGOFA = "";
		this.sFFPAGOFA = "";
		this.sIMCUCOFA = "";
		this.sFAACTAFA = "";
		this.sPTPAGOFA = "";
		this.sComparadorFA = "";
		this.bSeleccionadoFA = true; 

	}
	
    public void limpiarPlantillaFiltroCuotasActivo(ActionEvent actionEvent) 
    {  
    	borrarCamposFiltroCuotasActivo();
    }
    
	public void borrarCamposComunidad()
	{
		this.sCOCLDO = "";
		this.sDesCOCLDO = "";
		this.sNUDCOM = "";
    	this.sNOMCOC = "";
    	this.sNODCCO = "";
	}

	public void borrarCamposCuota()
	{
		this.sCOSBAC = "";
		this.sDesCOSBAC = "";
		this.sFIPAGO = "";
		this.sFFPAGO = "";
		this.sIMCUCO = "";
		this.sFAACTA = "";
		this.sPTPAGO = "";
		this.sDesPTPAGO = "";
		this.sOBTEXC = "";
	}
	
	public void borrarResultadosCuota()
	{
    	this.cuotaseleccionada = null;
    	this.tablacuotas = null;
	}
	
	public void limpiarPlantilla(ActionEvent actionEvent) 
    {
		this.liCodCuota = 0;
		
		this.sCOACCI = "";
		
    	borrarCamposFiltroActivo();
    	borrarResultadosActivo();

    	borrarCamposCuota();
    	borrarResultadosCuota();
    	borrarCamposFiltroCuotasActivo();
    }
    
    public void limpiarNota(ActionEvent actionEvent) 
    {  
    	this.sNota = "";
    }
    
	public void guardaNota (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;

			String sMsg = "";
			
			if (liCodCuota == 0)
			{
				sMsg = "Debe de haber cargado una Cuota antes de guardar la nota. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else if (CLCuotas.guardarNota(liCodCuota, sNota))
			{
				sMsg = "Nota guardada correctamente.";
				msg = Utils.pfmsgInfo(sMsg);
				logger.info(sMsg);
			}
			else
			{
				sMsg = "ERROR: Ocurrio un error al guardar la nota de la Cuota. Por favor, revise los datos y avise a soporte.";
				msg = Utils.pfmsgFatal(sMsg);
				logger.error(sMsg);
			}
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		
		}
	}
	
    public void restablecerNota(ActionEvent actionEvent) 
    {  
    	this.sNota = sNotaOriginal;
    	this.bConNotas = !sNota.isEmpty();
    }
    
	public void buscaActivos (ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.activoseleccionado = null;
			
			this.setTablaactivos(null);
			
			if (sNURCAT.isEmpty())
			{
				ActivoTabla filtro = new ActivoTabla(
						"", 
						sCOPOIN.toUpperCase(), 
						sNOMUIN.toUpperCase(),
						sNOPRAC.toUpperCase(), 
						sNOVIAS.toUpperCase(), 
						sNUPIAC.toUpperCase(), 
						sNUPOAC.toUpperCase(), 
						sNUPUAC.toUpperCase(), 
						sNUFIRE.toUpperCase(),
						"");
				
				this.setTablaactivos(CLCuotas.buscarActivosConCuotas(filtro));
				
				if (getTablaactivos().size() == 0)
				{
					sMsg = "No se encontraron Activos con los criterios solicitados.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
				}
				else if (getTablaactivos().size() == 1)
				{
					sMsg = "Encontrado un Activo relacionado.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
				}
				else
				{
					sMsg = "Encontrados "+getTablaactivos().size()+" Activos relacionados.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
				}

			}
			else if (CLReferencias.existeReferenciaCatastral(sNURCAT))
			{
				this.setTablaactivos(CLReferencias.buscarActivoAsociadoConCuotas(sNURCAT));
				
				if (getTablaactivos().size() == 0)
				{
					sMsg = "No se encontraron Activos con los criterios solicitados.";
					msg = Utils.pfmsgWarning(sMsg);
					logger.warn(sMsg);
				}
				else
				{
					sMsg = "Encontrado un Activo relacionado.";
					msg = Utils.pfmsgInfo(sMsg);
					logger.info(sMsg);
				}
			}
			else
			{
		    	sMsg = "La Referencia Catastral informada no se encuentrar registrada en el sistema. Por favor, revise los datos.";
				msg = Utils.pfmsgWarning(sMsg);
				logger.warn(sMsg);
			}

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
	
	public void cargarCuotas(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			if (sCOACES.isEmpty())
			{
				sMsg = "ERROR: Debe informar el Activo para realizar una b�squeda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else
			{
				try
				{
					int iCOACES  = Integer.parseInt(sCOACES);
					
					if (CLActivos.existeActivo(iCOACES))
					{
						this.tablacuotas = CLCuotas.buscarCuotasActivo(iCOACES);
						
						if (getTablacuotas().size() == 0)
						{
							sMsg = "No se encontraron Cuotas con los criterios solicitados.";
							msg = Utils.pfmsgWarning(sMsg);
							logger.warn(sMsg);
						}
						else if (getTablacuotas().size() == 1)
						{
							sMsg = "Encontrada una Cuota relacionada.";
							msg = Utils.pfmsgInfo(sMsg);
							logger.info(sMsg);
						}
						else
						{
							sMsg = "Encontradas "+getTablaactivos().size()+" Cuotas relacionadas.";
							msg = Utils.pfmsgInfo(sMsg);
							logger.info(sMsg);
						}
					}
					else
					{
						sMsg = "El Activo '"+sCOACES+"' no pertenece a la cartera. Por favor, revise los datos.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
					}

				}
				catch(NumberFormatException nfe)
				{
					sMsg = "ERROR: El Activo debe ser num�rico. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
			}
			

			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void buscarCuotasActivo(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			this.cuotaseleccionada = null;

			this.tablacuotas = null;
			
			if (sCOACES.isEmpty())
			{
				sMsg = "ERROR: Debe informar el Activo para realizar una b�squeda. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			else
			{
		    	try
		    	{
		    		int iCOACES = Integer.parseInt(sCOACES);
		    		
					if (CLActivos.existeActivo(iCOACES))
					{
						try
						{
							if (CLActivos.existeActivo(iCOACES))
							{
								String sImporte = "";
								
								if (!sComparadorFA.isEmpty())
								{
									sImporte = Utils.compruebaImporte(sIMCUCOFA);
								}
								
								CuotaTabla filtro = new CuotaTabla(
										"",
										sCOACES,   
										"",
										"",
										"",   
										sCOSBACFA,
										"",
										Utils.compruebaFecha(sFIPAGOFA),  
										Utils.compruebaFecha(sFFPAGOFA),   
										sImporte,  
										Utils.compruebaFecha(sFAACTAFA),   
										sPTPAGOFA,
										"",
										"",
										ValoresDefecto.DEF_ALTA);
								
								//this.tablacuotas = CLCuotas.buscarCuotasActivo(iCOACES);
								this.tablacuotas = CLCuotas.buscarCuotasActivoConFiltro(filtro, sComparadorFA);
								
								if (getTablacuotas().size() == 0)
								{
									sMsg = "No se encontraron Cuotas con los criterios solicitados.";
									msg = Utils.pfmsgWarning(sMsg);
									logger.warn(sMsg);
								}
								else if (getTablacuotas().size() == 1)
								{
									sMsg = "Encontrada una Cuota relacionada.";
									msg = Utils.pfmsgInfo(sMsg);
									logger.info(sMsg);
								}
								else
								{
									sMsg = "Encontradas "+getTablacuotas().size()+" Cuotas relacionadas.";
									msg = Utils.pfmsgInfo(sMsg);
									logger.info(sMsg);
								}
							}
							else
							{
								sMsg = "El Activo '"+sCOACES+"' no pertenece a la cartera. Por favor, revise los datos.";
								msg = Utils.pfmsgWarning(sMsg);
								logger.warn(sMsg);
							}
							

						}
						catch(NumberFormatException nfe)
						{
							sMsg = "ERROR: El activo debe ser num�rico. Por favor, revise los datos.";
							msg = Utils.pfmsgError(sMsg);
							logger.error(sMsg);
						}				
					}
					else
					{
						sMsg = "El Activo '"+sCOACES+"' no pertenece a la cartera. Por favor, revise los datos.";
						msg = Utils.pfmsgWarning(sMsg);
						logger.warn(sMsg);
						
				    	this.setTablacuotas(null);
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
	
	public void seleccionarCuota(ActionEvent actionEvent) 
    {
		if (ConnectionManager.comprobarConexion())
		{
		   	FacesMessage msg;
	    	
		   	this.liCodCuota = Long.parseLong(cuotaseleccionada.getsCuotaID());
		   	
		   	logger.debug("liCodCuota:|"+liCodCuota+"|");
		   	
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
	    	
	    	this.sNotaOriginal = CLCuotas.buscarNota(liCodCuota);
	    	this.sNota = sNotaOriginal;
			
			this.bConNotas = !sNota.isEmpty();
	    	
	    	String sMsg = "Cuota de '"+ sDesCOSBAC +"' cargada correctamente .";
	    	msg = Utils.pfmsgInfo(sMsg);
	    	logger.info(sMsg);

	    	FacesContext.getCurrentInstance().addMessage(null, msg);
		}
    }
	
	public void cambiaComparadorFA()
	{
		this.bSeleccionadoFA = this.sComparadorFA.isEmpty();
		logger.debug("sComparadorFA:|"+sComparadorFA+"|");
	}
	
	public void hoyFIPAGOFA (ActionEvent actionEvent)
	{
		this.setsFIPAGOFA(Utils.fechaDeHoy(true));
		logger.debug("sFIPAGOFA:|"+sFIPAGOFA+"|");
	}
	
	public void hoyFFPAGOFA (ActionEvent actionEvent)
	{
		this.setsFFPAGOFA(Utils.fechaDeHoy(true));
		logger.debug("sFFPAGOFA:|"+sFFPAGOFA+"|");
	}
	
	public void hoyFAACTAFA (ActionEvent actionEvent)
	{
		this.setsFAACTAFA(Utils.fechaDeHoy(true));
		logger.debug("sFAACTAFA:|"+sFAACTAFA+"|");
	}
	
	public void hoyFIPAGO (ActionEvent actionEvent)
	{
		this.setsFIPAGO(Utils.fechaDeHoy(true));
		logger.debug("sFIPAGO:|"+sFIPAGO+"|");
	}

	public void hoyFFPAGO (ActionEvent actionEvent)
	{
		this.setsFFPAGO(Utils.fechaDeHoy(true));
		logger.debug("sFFPAGO:|"+sFFPAGO+"|");
	}
	
	public void hoyFAACTA (ActionEvent actionEvent)
	{
		this.setsFAACTA(Utils.fechaDeHoy(true));
		logger.debug("sFAACTA:|"+sFAACTA+"|");
	}
	
	public void registraDatos(ActionEvent actionEvent)
	{
		if (ConnectionManager.comprobarConexion())
		{
			FacesMessage msg;
			
			String sMsg = "";
			
			try
			{
				if (!CLCuotas.existeCuota(Integer.parseInt(sCOACES), sCOCLDO, sNUDCOM, sCOSBAC))
				{
					sMsg = "ERROR: La cuota no esta dada de alta. Por favor, revise los datos.";
					msg = Utils.pfmsgError(sMsg);
					logger.error(sMsg);
				}
				else
				{

					
					MovimientoCuota movimiento = new MovimientoCuota (
							ValoresDefecto.DEF_E2_CODTRN, 
							ValoresDefecto.DEF_COTDOR, 
							ValoresDefecto.DEF_IDPROV, 
							sCOACCI, 
							sCOCLDO, 
							sNUDCOM, 
							ValoresDefecto.DEF_COENGP, 
							sCOACES, 
							ValoresDefecto.DEF_COGRUG_E2, 
							ValoresDefecto.DEF_COTACA_E2, 
							Utils.compruebaCodigoPago(false,sCOSBAC), 
							"", 
							Utils.compruebaFecha(sFIPAGO), 
							"", 
							Utils.compruebaFecha(sFFPAGO), 
							"", 
							Utils.compruebaImporte(sIMCUCO), 
							"", 
							Utils.compruebaFecha(sFAACTA), 
							"", 
							sPTPAGO.toUpperCase(),
							"", 
							sOBTEXC.toUpperCase(), 
							ValoresDefecto.CAMPO_ALFA_SIN_INFORMAR);

					String sNotaAntigua = CLCuotas.buscarNota(CLCuotas.buscarCodigoCuota(Integer.parseInt(sCOACES), sCOCLDO, sNUDCOM, sCOSBAC));
					
					Nota nota = new Nota (sNotaAntigua.equals(sNota),sNota);
					
					if (nota.isbInvalida())
					{
						nota.setsContenido("");
					}
					
					int iSalida = CLCuotas.registraMovimiento(movimiento, nota);
					
					switch (iSalida) 
					{
					case 0: //Sin errores
						sMsg = "El movimiento se ha registrado correctamente.";
						msg = Utils.pfmsgInfo(sMsg);
						logger.info(sMsg);
						break;

					case -1: //Error 001 - CODIGO DE ACCION DEBE SER A,M o B
						sMsg = "ERROR:001 - No se ha elegido una acccion correcta. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -3: //Error 003 - NO EXISTE EL ACTIVO
						sMsg = "ERROR:003 - El activo elegido no esta registrado en el sistema. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;


					case -4: //Error 004 - CIF DE LA COMUNIDAD NO PUEDE SER BLANCO O NULO
						sMsg = "ERROR:004 - No se ha informado el numero de documento. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -32: //Error 032 - EL SUBTIPO DE ACCION NO EXISTE
						sMsg = "ERROR:032 - El concepto de pago es obligatorio. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -33: //Error 033 - LA FECHA DE PRIMER PAGO DEBE SER LOGICA Y OBLIGATORIA
						sMsg = "ERROR:033 - La fecha del primer pago es obligatoria. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;


					case -34: //Error 034 - LA FECHA DE ULTIMO PAGO DEBE SER LOGICA Y OBLIGATORIA
						sMsg = "ERROR:034 - La fecha del ultimo pago es obligatoria. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -35: //Error 035 - LA FECHA DE ULTIMO PAGO NO DEBE DE SER MENOR QUE LA FECHA DE PRIMER PAGO
						sMsg = "ERROR:35 - La fecha del ultimo pago no puede ser menor que la del primero.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -36: //Error 036 - IMPORTE DE CUOTA TIENE QUE SER MAYOR DE CERO
						sMsg = "ERROR:036 - El importe de la cuota tiene ser mayor que cero. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -41: //Error 041 - LA COMUNIDAD NO EXISTE EN LA TABLA DE COMUNIDADES GMAE10
						sMsg = "ERROR:041 - La comunidad propocionada no esta registrada en el sistema. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -42: //Error 042 - LA RELACION ACTIVO-COMUNIDAD YA EXISTE EN GMAE12. NO SE PUEDE REALIZAR EL ALTA
						sMsg = "ERROR:042 - El activo proporcionado esta asociado a otra comunidad. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -43: //Error 043 - LA RELACION ACTIVO-COMUNIDAD NO EXISTE EN GMAE12. NO SE PUEDE REALIZAR LA MODIFICACION
						sMsg = "ERROR:043 - El activo prorcionado no pertenece a la comunidad. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -44: //Error 044 - NO EXISTE PERIOCIDAD DE PAGO
						sMsg = "ERROR:044 - La periodicidad de pago es obligatoria. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -45: //Error 045 - LA RELACION ACTIVO-COMUNIDAD NO EXISTE EN GMAE12. NO SE PUEDE REALIZAR LA BAJA
						sMsg = "ERROR:045 - El activo prorcionado no pertenece a la comunidad. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -46: //Error 046 - LA FECHA DEL ACTA DEBE SER LOGICA Y OBLIGATORIA 
						sMsg = "ERROR:046 - La fecha de acta es obligatoria. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -701: //Error 701 - error en importe
						sMsg = "ERROR:701 - El campo importe no se ha informado correctamente. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -702: //Error 702 - fecha de primer pago incorrecta
						sMsg = "ERROR:702 - La fecha del primer pago no se ha informado correctamente. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -703: //Error 703 - fecha de ultimo pago incorrecta
						sMsg = "ERROR:703 - La fecha del ultimo pago no se ha informado correctamente. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -704: //Error 704 - fecha de acta incorrecta
						sMsg = "ERROR:704 - La fecha de acta no se ha informado correctamente. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -801: //Error 801 - alta de una cuota en alta
						sMsg = "ERROR:801 - La cuota ya esta dada de alta. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -802: //Error 802 - cuota de baja no puede recibir movimientos
						sMsg = "ERROR:802 - La cuota esta baja y no puede recibir movimientos. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;
						
					case -803: //Error 803 - estado no disponible
						sMsg = "ERROR:803 - El estado de la cuota informada no esta disponible. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -804: //Error 804 - modificacion sin cambios
						sMsg = "ERROR:804 - No hay modificaciones que realizar. Por favor, revise los datos.";
						msg = Utils.pfmsgError(sMsg);
						logger.error(sMsg);
						break;

					case -900: //Error 900 - al crear un movimiento
						sMsg = "[FATAL] ERROR:900 - Se ha producido un error al registrar el movimiento. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -901: //Error 901 - error y rollback - error al crear la cuota
						sMsg = "[FATAL] ERROR:901 - Se ha producido un error al registrar la cuota. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -902: //Error 902 - error y rollback - error al registrar la relaccion
						sMsg = "[FATAL] ERROR:902 - Se ha producido un error al registrar la relacion. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -903: //Error 903 - error y rollback - error al cambiar el estado
						sMsg = "[FATAL] ERROR:903 - Se ha producido un error al cambiar el estado de la cuota. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					case -904: //Error 904 - error y rollback - error al modificar la cuota
						sMsg = "[FATAL] ERROR:904 - Se ha producido un error al modificar la cuota. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
						
					case -910: //Error 910 - error y rollback - error al conectar con la base de datos
						sMsg = "[FATAL] ERROR:910 - Se ha producido un error al conectar con la base de datos. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;

					default: //error generico
						sMsg = "[FATAL] ERROR:"+iSalida+" - La operacion solicitada ha producido un error desconocido. Por favor, revise los datos y avise a soporte.";
						msg = Utils.pfmsgFatal(sMsg);
						logger.error(sMsg);
						break;
					}
				}
				
				
				logger.debug("Finalizadas las comprobaciones.");
			}
			catch(NumberFormatException nfe)
			{
				sMsg = "ERROR: El activo debe ser num�rico. Por favor, revise los datos.";
				msg = Utils.pfmsgError(sMsg);
				logger.error(sMsg);
			}
			
			
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
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

	public String getsNUFIRE() {
		return sNUFIRE;
	}

	public void setsNUFIRE(String sNUFIRE) {
		this.sNUFIRE = sNUFIRE;
	}

	public String getsCOSBACFA() {
		return sCOSBACFA;
	}

	public void setsCOSBACFA(String sCOSBACFA) {
		this.sCOSBACFA = sCOSBACFA;
	}

	public String getsFIPAGOFA() {
		return sFIPAGOFA;
	}

	public void setsFIPAGOFA(String sFIPAGOFA) {
		this.sFIPAGOFA = sFIPAGOFA;
	}

	public String getsFFPAGOFA() {
		return sFFPAGOFA;
	}

	public void setsFFPAGOFA(String sFFPAGOFA) {
		this.sFFPAGOFA = sFFPAGOFA;
	}

	public String getsIMCUCOFA() {
		return sIMCUCOFA;
	}

	public void setsIMCUCOFA(String sIMCUCOFA) {
		this.sIMCUCOFA = sIMCUCOFA;
	}

	public String getsFAACTAFA() {
		return sFAACTAFA;
	}

	public void setsFAACTAFA(String sFAACTAFA) {
		this.sFAACTAFA = sFAACTAFA;
	}

	public String getsPTPAGOFA() {
		return sPTPAGOFA;
	}

	public void setsPTPAGOFA(String sPTPAGOFA) {
		this.sPTPAGOFA = sPTPAGOFA;
	}

	public String getsComparadorFA() {
		return sComparadorFA;
	}

	public void setsComparadorFA(String sComparadorFA) {
		this.sComparadorFA = sComparadorFA;
	}

	public boolean isbSeleccionadoFA() {
		return bSeleccionadoFA;
	}

	public void setbSeleccionadoFA(boolean bSeleccionadoFA) {
		this.bSeleccionadoFA = bSeleccionadoFA;
	}

	public String getsCOACES() {
		return sCOACES;
	}

	public void setsCOACES(String sCOACES) {
		this.sCOACES = sCOACES.trim();
	}

	public String getsCOACCI() {
		return sCOACCI;
	}

	public void setsCOACCI(String sCOACCI) {
		this.sCOACCI = sCOACCI;
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
		this.sIMCUCO = sIMCUCO.trim();
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
		this.sOBTEXC = sOBTEXC.trim().toUpperCase();
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

	public Map<String, String> getTiposcosbacHM() {
		return tiposcosbacHM;
	}

	public void setTiposcosbacHM(Map<String, String> tiposcosbacHM) {
		this.tiposcosbacHM = tiposcosbacHM;
	}

	public Map<String, String> getTiposptpagoHM() {
		return tiposptpagoHM;
	}

	public void setTiposptpagoHM(Map<String, String> tiposptpagoHM) {
		this.tiposptpagoHM = tiposptpagoHM;
	}

	public Map<String, String> getTiposcomparaimporteHM() {
		return tiposcomparaimporteHM;
	}

	public void setTiposcomparaimporteHM(Map<String, String> tiposcomparaimporteHM) {
		this.tiposcomparaimporteHM = tiposcomparaimporteHM;
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

	public String getsNota() {
		return sNota;
	}

	public void setsNota(String sNota) {
		this.sNota = sNota.trim();
	}

	public boolean isbConNotas() {
		return bConNotas;
	}

	public void setbConNotas(boolean bConNotas) {
		this.bConNotas = bConNotas;
	}

	public String getsNURCAT() {
		return sNURCAT;
	}

	public void setsNURCAT(String sNURCAT) {
		this.sNURCAT = sNURCAT.trim().toUpperCase();
	}
	
	
	
}

//Autor: Francisco Valverde Manj�n