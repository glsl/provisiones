package com.provisiones.types;

public class Gasto 
{

	private String COACES = "";
	private String COGRUG = "";
	private String COTPGA = "";
	private String COSBGA = "";
	private String PTPAGO = "";
	private String FEDEVE = "";
	private String FFGTVP = "";
	private String FELIPG = "";
	private String COSIGA = "";
	private String FEEESI = "";
	private String FEECOI = "";
	private String FEEAUI = "";
	private String FEEPAI = "";
	private String IMNGAS = "";
	private String YCOS02 = "";
	private String IMRGAS = "";
	private String YCOS04 = "";
	private String IMDGAS = "";
	private String YCOS06 = "";
	private String IMCOST = "";
	private String YCOS08 = "";
	private String IMOGAS = "";
	private String YCOS10 = "";
	private String IMDTGA = "";
	private String IMIMGA = "";
	private String COIMPT = "";
	private String COTNEG = "";
	private String FEAGTO = "";
	private String COMONA = "";
	private String BIAUTO = "";
	private String FEAUFA = "";
	private long valor_total = 0;
	
	//Constructor de clase

	public Gasto(String cOACES, String cOGRUG, String cOTPGA, String cOSBGA,
			String pTPAGO, String fEDEVE, String fFGTVP, String fELIPG,
			String cOSIGA, String fEEESI, String fEECOI, String fEEAUI,
			String fEEPAI, String iMNGAS, String yCOS02, String iMRGAS,
			String yCOS04, String iMDGAS, String yCOS06, String iMCOST,
			String yCOS08, String iMOGAS, String yCOS10, String iMDTGA,
			String iMIMGA, String cOIMPT, String cOTNEG, String fEAGTO,
			String cOMONA, String bIAUTO, String fEAUFA) {
		super();
		COACES = cOACES;
		COGRUG = cOGRUG;
		COTPGA = cOTPGA;
		COSBGA = cOSBGA;
		PTPAGO = pTPAGO;
		FEDEVE = fEDEVE;
		FFGTVP = fFGTVP;
		FELIPG = fELIPG;
		COSIGA = cOSIGA;
		FEEESI = fEEESI;
		FEECOI = fEECOI;
		FEEAUI = fEEAUI;
		FEEPAI = fEEPAI;
		IMNGAS = iMNGAS;
		YCOS02 = yCOS02;
		IMRGAS = iMRGAS;
		YCOS04 = yCOS04;
		IMDGAS = iMDGAS;
		YCOS06 = yCOS06;
		IMCOST = iMCOST;
		YCOS08 = yCOS08;
		IMOGAS = iMOGAS;
		YCOS10 = yCOS10;
		IMDTGA = iMDTGA;
		IMIMGA = iMIMGA;
		COIMPT = cOIMPT;
		COTNEG = cOTNEG;
		FEAGTO = fEAGTO;
		COMONA = cOMONA;
		BIAUTO = bIAUTO;
		FEAUFA = fEAUFA;
		setValor_total();
	}

	//M�todos de acceso
	
	public String getCOACES() {
		return COACES;
	}

	public void setCOACES(String cOACES) {
		COACES = cOACES;
	}

	public String getCOGRUG() {
		return COGRUG;
	}

	public void setCOGRUG(String cOGRUG) {
		COGRUG = cOGRUG;
	}

	public String getCOTPGA() {
		return COTPGA;
	}

	public void setCOTPGA(String cOTPGA) {
		COTPGA = cOTPGA;
	}

	public String getCOSBGA() {
		return COSBGA;
	}

	public void setCOSBGA(String cOSBGA) {
		COSBGA = cOSBGA;
	}

	public String getPTPAGO() {
		return PTPAGO;
	}

	public void setPTPAGO(String pTPAGO) {
		PTPAGO = pTPAGO;
	}

	public String getFEDEVE() {
		return FEDEVE;
	}

	public void setFEDEVE(String fEDEVE) {
		FEDEVE = fEDEVE;
	}

	public String getFFGTVP() {
		return FFGTVP;
	}

	public void setFFGTVP(String fFGTVP) {
		FFGTVP = fFGTVP;
	}

	public String getFELIPG() {
		return FELIPG;
	}

	public void setFELIPG(String fELIPG) {
		FELIPG = fELIPG;
	}

	public String getCOSIGA() {
		return COSIGA;
	}

	public void setCOSIGA(String cOSIGA) {
		COSIGA = cOSIGA;
	}

	public String getFEEESI() {
		return FEEESI;
	}

	public void setFEEESI(String fEEESI) {
		FEEESI = fEEESI;
	}

	public String getFEECOI() {
		return FEECOI;
	}

	public void setFEECOI(String fEECOI) {
		FEECOI = fEECOI;
	}

	public String getFEEAUI() {
		return FEEAUI;
	}

	public void setFEEAUI(String fEEAUI) {
		FEEAUI = fEEAUI;
	}

	public String getFEEPAI() {
		return FEEPAI;
	}

	public void setFEEPAI(String fEEPAI) {
		FEEPAI = fEEPAI;
	}

	public String getIMNGAS() {
		return IMNGAS;
	}

	public void setIMNGAS(String iMNGAS) {
		IMNGAS = iMNGAS;
	}

	public String getYCOS02() {
		return YCOS02;
	}

	public void setYCOS02(String yCOS02) {
		YCOS02 = yCOS02;
	}

	public String getIMRGAS() {
		return IMRGAS;
	}

	public void setIMRGAS(String iMRGAS) {
		IMRGAS = iMRGAS;
	}

	public String getYCOS04() {
		return YCOS04;
	}

	public void setYCOS04(String yCOS04) {
		YCOS04 = yCOS04;
	}

	public String getIMDGAS() {
		return IMDGAS;
	}

	public void setIMDGAS(String iMDGAS) {
		IMDGAS = iMDGAS;
	}

	public String getYCOS06() {
		return YCOS06;
	}

	public void setYCOS06(String yCOS06) {
		YCOS06 = yCOS06;
	}

	public String getIMCOST() {
		return IMCOST;
	}

	public void setIMCOST(String iMCOST) {
		IMCOST = iMCOST;
	}

	public String getYCOS08() {
		return YCOS08;
	}

	public void setYCOS08(String yCOS08) {
		YCOS08 = yCOS08;
	}

	public String getIMOGAS() {
		return IMOGAS;
	}

	public void setIMOGAS(String iMOGAS) {
		IMOGAS = iMOGAS;
	}

	public String getYCOS10() {
		return YCOS10;
	}

	public void setYCOS10(String yCOS10) {
		YCOS10 = yCOS10;
	}

	public String getIMDTGA() {
		return IMDTGA;
	}

	public void setIMDTGA(String iMDTGA) {
		IMDTGA = iMDTGA;
	}

	public String getIMIMGA() {
		return IMIMGA;
	}

	public void setIMIMGA(String iMIMGA) {
		IMIMGA = iMIMGA;
	}

	public String getCOIMPT() {
		return COIMPT;
	}

	public void setCOIMPT(String cOIMPT) {
		COIMPT = cOIMPT;
	}

	public String getCOTNEG() {
		return COTNEG;
	}

	public void setCOTNEG(String cOTNEG) {
		COTNEG = cOTNEG;
	}

	public String getFEAGTO() {
		return FEAGTO;
	}

	public void setFEAGTO(String fEAGTO) {
		FEAGTO = fEAGTO;
	}

	public String getCOMONA() {
		return COMONA;
	}

	public void setCOMONA(String cOMONA) {
		COMONA = cOMONA;
	}

	public String getBIAUTO() {
		return BIAUTO;
	}

	public void setBIAUTO(String bIAUTO) {
		BIAUTO = bIAUTO;
	}

	public String getFEAUFA() {
		return FEAUFA;
	}

	public void setFEAUFA(String fEAUFA) {
		FEAUFA = fEAUFA;
	}

	public long getValor_total() {
		return valor_total;
	}

	public void setValor_total()
	{
		long liIMNGAS = IMNGAS.equals("") ? 0 : Long.parseLong(IMNGAS);
		long liIMRGAS = IMRGAS.equals("") ? 0 : Long.parseLong(IMRGAS);
		long liIMDGAS = IMDGAS.equals("") ? 0 : Long.parseLong(IMDGAS);
		long liIMCOST = IMCOST.equals("") ? 0 : Long.parseLong(IMCOST);
		long liIMOGAS = IMOGAS.equals("") ? 0 : Long.parseLong(IMOGAS);
		long liIMDTGA = IMDTGA.equals("") ? 0 : Long.parseLong(IMDTGA);
		long liIMIMGA = IMIMGA.equals("") ? 0 : Long.parseLong(IMIMGA);


		long liValor = 0;

		if (YCOS02.equals("-"))
		{
			liIMNGAS = -liIMNGAS;
		}
		if (YCOS04.equals("-"))
		{
			liIMRGAS = -liIMRGAS;
		}
		if (YCOS06.equals("-"))
		{
			liIMDGAS = -liIMDGAS;
		}
		if (YCOS08.equals("-"))
		{
			liIMCOST = -liIMCOST;
		}
		if (YCOS10.equals("-"))
		{
			liIMOGAS = -liIMOGAS;
		}
		
		liValor = liIMNGAS+liIMRGAS+liIMDGAS+liIMCOST+liIMOGAS;
		
		if (liValor < 0)
		{
			liValor = liValor + liIMDTGA;
		}
		else
		{
			liValor = liValor - liIMDTGA;
		}
		
		if (liValor < 0)
		{
			liValor = liValor - liIMIMGA;
		}
		else
		{
			liValor = liValor + liIMIMGA;
		}
		
		
		this.valor_total = liValor;
	}

	public String logGasto()
	{
		return String.format("(GASTO)\nCOACES:|%s|\nCOGRUG:|%s|\nCOTPGA:|%s|\nCOSBGA:|%s|\nPTPAGO:|%s|\nFEDEVE:|%s|\nFFGTVP:|%s|\nFELIPG:|%s|\nCOSIGA:|%s|\nFEEESI:|%s|\nFEECOI:|%s|\nFEEAUI:|%s|\nFEEPAI:|%s|\nIMNGAS:|%s|\nYCOS02:|%s|\nIMRGAS:|%s|\nYCOS04:|%s|\nIMDGAS:|%s|\nYCOS06:|%s|\nIMCOST:|%s|\nYCOS08:|%s|\nIMOGAS:|%s|\nYCOS10:|%s|\nIMDTGA:|%s|\nIMIMGA:|%s|\nCOIMPT:|%s|\nCOTNEG:|%s|\nFEAGTO:|%s|\nCOMONA:|%s|\nBIAUTO:|%s|\nFEAUFA:|%s|",COACES,COGRUG,COTPGA,COSBGA,PTPAGO,FEDEVE,FFGTVP,FELIPG,COSIGA,FEEESI,FEECOI,FEEAUI,FEEPAI,IMNGAS,YCOS02,IMRGAS,YCOS04,IMDGAS,YCOS06,IMCOST,YCOS08,IMOGAS,YCOS10,IMDTGA,IMIMGA,COIMPT,COTNEG,FEAGTO,COMONA,BIAUTO,FEAUFA); 
	} 
}

//Autor: Francisco Valverde Manj�n