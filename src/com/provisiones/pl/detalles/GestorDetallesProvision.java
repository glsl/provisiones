package com.provisiones.pl.detalles;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisiones.dal.ConnectionManager;

public class GestorDetallesProvision implements Serializable 
{
	private static final long serialVersionUID = 6585445551060014479L;
	
	private static Logger logger = LoggerFactory.getLogger(GestorDetallesProvision.class.getName());
	
	private String sNUPROF = "";
	private String sFEPFON = "";
	private String sFechaValidacion = "";
	private String sEstado = "";
	private String sValorTolal = "";
	private String sNumGastos = "";

	public GestorDetallesProvision()
	{
		if (ConnectionManager.comprobarConexion())
		{
			logger.debug("Iniciando GestorDetallesProvision...");	
		}
	}

	public String getsNUPROF() {
		return sNUPROF;
	}

	public void setsNUPROF(String sNUPROF) {
		this.sNUPROF = sNUPROF;
	}

	public String getsFEPFON() {
		return sFEPFON;
	}

	public void setsFEPFON(String sFEPFON) {
		this.sFEPFON = sFEPFON;
	}

	public String getsFechaValidacion() {
		return sFechaValidacion;
	}

	public void setsFechaValidacion(String sFechaValidacion) {
		this.sFechaValidacion = sFechaValidacion;
	}

	public String getsEstado() {
		return sEstado;
	}

	public void setsEstado(String sEstado) {
		this.sEstado = sEstado;
	}

	public String getsValorTolal() {
		return sValorTolal;
	}

	public void setsValorTolal(String sValorTolal) {
		this.sValorTolal = sValorTolal;
	}

	public String getsNumGastos() {
		return sNumGastos;
	}

	public void setsNumGastos(String sNumGastos) {
		this.sNumGastos = sNumGastos;
	}

	
}
