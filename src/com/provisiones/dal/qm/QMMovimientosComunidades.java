package com.provisiones.dal.qm;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.types.MovimientoComunidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QMMovimientosComunidades
{
	static String sClassName = QMComunidades.class.getName();

	static String sTable = "e1_movimientos_tbl";

	static String sField1 = "e1_movimiento_id";
	
	static String sField2 = "cod_codtrn";
	static String sField3 = "cotdor";
	static String sField4 = "idprov";
	static String sField5 = "cod_coacci";
	static String sField6 = "coengp";
	static String sField7 = "cod_cocldo";
	static String sField8 = "nudcom_id";
	static String sField9 = "cod_bitc10";
	static String sField10 = "cod_coaces";
	static String sField11 = "cod_bitc01";
	static String sField12 = "nomcoc";
	static String sField13 = "cod_bitc02";
	static String sField14 = "nodcco";
	static String sField15 = "cod_bitc03";
	static String sField16 = "nomprc";
	static String sField17 = "cod_bitc04";
	static String sField18 = "nutprc";
	static String sField19 = "cod_bitc05";
	static String sField20 = "nomadc";
	static String sField21 = "cod_bitc06";
	static String sField22 = "nutadc";
	static String sField23 = "cod_bitc07";
	static String sField24 = "nodcad";
	static String sField25 = "cod_bitc08";
	static String sField26 = "nuccen";
	static String sField27 = "nuccof";
	static String sField28 = "nuccdi";
	static String sField29 = "nuccnt";
	static String sField30 = "cod_bitc09";
	static String sField31 = "obtexc";
	static String sField32 = "obdeer";         

	


	public static boolean addMovimientoComunidad(MovimientoComunidad NuevoMovimientoComunidad)

	{
		String sMethod = "addMovimientoComunidad";
		Statement stmt = null;
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try {

			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + " ("
				       + sField2  + ","              
				       + sField3  + ","              
				       + sField4  + ","              
				       + sField5  + ","              
				       + sField6  + ","              
				       + sField7  + ","              
				       + sField8  + ","              
				       + sField9  + ","              
				       + sField10 + ","              
				       + sField11 + ","              
				       + sField12 + ","              
				       + sField13 + ","              
				       + sField14 + ","              
				       + sField15 + ","              
				       + sField16 + ","              
				       + sField17 + ","              
				       + sField18 + ","              
				       + sField19 + ","              
				       + sField20 + ","              
				       + sField21 + ","              
				       + sField22 + ","              
				       + sField23 + ","              
				       + sField24 + ","              
				       + sField25 + ","              
				       + sField26 + ","              
				       + sField27 + ","              
				       + sField28 + ","              
				       + sField29 + ","
				       + sField30 + ","
				       + sField31 + ","
				       + sField32 +               
				       ") VALUES ('" 
				       + NuevoMovimientoComunidad.getCODTRN() + "','" 
				       + NuevoMovimientoComunidad.getCOTDOR() + "','"
				       + NuevoMovimientoComunidad.getIDPROV() + "','"
				       + NuevoMovimientoComunidad.getCOACCI() + "','"
				       + NuevoMovimientoComunidad.getCOENGP() + "','"
				       + NuevoMovimientoComunidad.getCOCLDO() + "','"
				       + NuevoMovimientoComunidad.getNUDCOM() + "','"
				       + NuevoMovimientoComunidad.getBITC10() + "','"
				       + NuevoMovimientoComunidad.getCOACES() + "','"
				       + NuevoMovimientoComunidad.getBITC01() + "','"
				       + NuevoMovimientoComunidad.getNOMCOC() + "','"
				       + NuevoMovimientoComunidad.getBITC02() + "','"
				       + NuevoMovimientoComunidad.getNODCCO() + "','"
				       + NuevoMovimientoComunidad.getBITC03() + "','"
				       + NuevoMovimientoComunidad.getNOMPRC() + "','"
				       + NuevoMovimientoComunidad.getBITC04() + "','"
				       + NuevoMovimientoComunidad.getNUTPRC() + "','"
				       + NuevoMovimientoComunidad.getBITC05() + "','"
				       + NuevoMovimientoComunidad.getNOMADC() + "','"
				       + NuevoMovimientoComunidad.getBITC06() + "','"
				       + NuevoMovimientoComunidad.getNUTADC() + "','"
				       + NuevoMovimientoComunidad.getBITC07() + "','"
				       + NuevoMovimientoComunidad.getNODCAD() + "','"
				       + NuevoMovimientoComunidad.getBITC08() + "','"
				       + NuevoMovimientoComunidad.getNUCCEN() + "','"
				       + NuevoMovimientoComunidad.getNUCCOF() + "','"
				       + NuevoMovimientoComunidad.getNUCCDI() + "','"
				       + NuevoMovimientoComunidad.getNUCCNT() + "','"
				       + NuevoMovimientoComunidad.getBITC09() + "','"
				       + NuevoMovimientoComunidad.getOBTEXC() + "','"
				       + NuevoMovimientoComunidad.getOBDEER() + "' )");
		} 
		catch (SQLException ex) 
		{


			//System.out.println("["+sClassName+"."+sMethod+"] ERROR: COGRAP: " + NuevoMovimientoComunidad.getCOGRAP());
			
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: NUDCOM: " + NuevoMovimientoComunidad.getNUDCOM());
			
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());			
		} 
		finally
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return true;
	}
	public static boolean modMovimientoComunidad(MovimientoComunidad NuevoMovimientoComunidad, String sMovimientoComunidadID, String sValidado)
	{
		String sMethod = "modMovimientoComunidad";
		Statement stmt = null;
		boolean bExit = false;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField2  + " = '"+ NuevoMovimientoComunidad.getCODTRN() + "','" 
					+ sField3  + " = '"+ NuevoMovimientoComunidad.getCOTDOR() + "','" 
					+ sField4  + " = '"+ NuevoMovimientoComunidad.getIDPROV() + "','" 
					+ sField5  + " = '"+ NuevoMovimientoComunidad.getCOACCI() + "','" 
					+ sField6  + " = '"+ NuevoMovimientoComunidad.getCOENGP() + "','" 
					+ sField7  + " = '"+ NuevoMovimientoComunidad.getCOCLDO() + "','" 
					+ sField8  + " = '"+ NuevoMovimientoComunidad.getNUDCOM() + "','" 
					+ sField9  + " = '"+ NuevoMovimientoComunidad.getBITC10() + "','" 
					+ sField10 + " = '"+ NuevoMovimientoComunidad.getCOACES() + "','" 
					+ sField11 + " = '"+ NuevoMovimientoComunidad.getBITC01() + "','" 
					+ sField12 + " = '"+ NuevoMovimientoComunidad.getNOMCOC() + "','" 
					+ sField13 + " = '"+ NuevoMovimientoComunidad.getBITC02() + "','" 
					+ sField14 + " = '"+ NuevoMovimientoComunidad.getNODCCO() + "','" 
					+ sField15 + " = '"+ NuevoMovimientoComunidad.getBITC03() + "','" 
					+ sField16 + " = '"+ NuevoMovimientoComunidad.getNOMPRC() + "','" 
					+ sField17 + " = '"+ NuevoMovimientoComunidad.getBITC04() + "','" 
					+ sField18 + " = '"+ NuevoMovimientoComunidad.getNUTPRC() + "','" 
					+ sField19 + " = '"+ NuevoMovimientoComunidad.getBITC05() + "','" 
					+ sField20 + " = '"+ NuevoMovimientoComunidad.getNOMADC() + "','" 
					+ sField21 + " = '"+ NuevoMovimientoComunidad.getBITC06() + "','" 
					+ sField22 + " = '"+ NuevoMovimientoComunidad.getNUTADC() + "','" 
					+ sField23 + " = '"+ NuevoMovimientoComunidad.getBITC07() + "','" 
					+ sField24 + " = '"+ NuevoMovimientoComunidad.getNODCAD() + "','" 
					+ sField25 + " = '"+ NuevoMovimientoComunidad.getBITC08() + "','" 
					+ sField26 + " = '"+ NuevoMovimientoComunidad.getNUCCEN() + "','" 
					+ sField27 + " = '"+ NuevoMovimientoComunidad.getNUCCOF() + "','" 
					+ sField28 + " = '"+ NuevoMovimientoComunidad.getNUCCDI() + "','" 
					+ sField29 + " = '"+ NuevoMovimientoComunidad.getNUCCNT() + "','" 
					+ sField30 + " = '"+ NuevoMovimientoComunidad.getBITC09() + "','" 
					+ sField31 + " = '"+ NuevoMovimientoComunidad.getOBTEXC() + "','" 
					+ sField32 + " = '"+ NuevoMovimientoComunidad.getOBDEER() +
					"' "+
					" WHERE "
					+ sField1 + " = '"+ sMovimientoComunidadID +"'");
			
		} 
		catch (SQLException ex) 
		{

			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());
		} 
		finally 
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
			bExit = true;
		}
		ConnectionManager.CloseDBConnection(conn);
		return bExit;
	}

	public static boolean delMovimientoComunidad(String sMovimientoComunidadID)
	{
		String sMethod = "delMovimientoComunidad";
		Statement stmt = null;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sMovimientoComunidadID + "' )");
		} 
		catch (SQLException ex) 
		{

			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());
		} 
		finally 
		{

			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return true;
	}

	public static MovimientoComunidad getMovimientoComunidad(String sMovimientoComunidadID)
	{//pendiente de coaces, de la tabla activos
		
		String sMethod = "getMovimientoComunidad";

		Statement stmt = null;
		ResultSet rs = null;

		String sCODTRN = "";
		String sCOTDOR = "";
		String sIDPROV = "";
		String sCOACCI = "";
		String sCOENGP = "";
		String sCOCLDO = "";
		String sNUDCOM = "";
		String sBITC10 = "";
		String sCOACES = "";
		String sBITC01 = "";
		String sNOMCOC = "";
		String sBITC02 = "";
		String sNODCCO = "";
		String sBITC03 = "";
		String sNOMPRC = "";
		String sBITC04 = "";
		String sNUTPRC = "";
		String sBITC05 = "";
		String sNOMADC = "";
		String sBITC06 = "";
		String sNUTADC = "";
		String sBITC07 = "";
		String sNODCAD = "";
		String sBITC08 = "";
		String sNUCCEN = "";
		String sNUCCOF = "";
		String sNUCCDI = "";
		String sNUCCNT = "";
		String sBITC09 = "";
		String sOBTEXC = "";
		String sOBDEER = "";

		PreparedStatement pstmt = null;
		boolean found = false;
		
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();

			pstmt = conn.prepareStatement("SELECT "
				       + sField2  + ","
				       + sField3  + ","              
				       + sField4  + ","              
				       + sField5  + ","              
				       + sField6  + ","              
				       + sField7  + ","              
				       + sField8  + ","              
				       + sField9  + ","              
				       + sField10 + ","              
				       + sField11 + ","              
				       + sField12 + ","              
				       + sField13 + ","              
				       + sField14 + ","              
				       + sField15 + ","              
				       + sField16 + ","              
				       + sField17 + ","              
				       + sField18 + ","              
				       + sField19 + ","              
				       + sField20 + ","              
				       + sField21 + ","              
				       + sField22 + ","              
				       + sField23 + ","              
				       + sField24 + ","              
				       + sField25 + ","              
				       + sField26 + ","              
				       + sField27 + ","              
				       + sField28 + ","              
				       + sField29 + ","
				       + sField30 + ","
				       + sField31 + ","
				       + sField32 +               
       
			"  FROM " + sTable + 
					" WHERE (" + sField7 + " = '" + sMovimientoComunidadID	+ "')");

			rs = pstmt.executeQuery();

			System.out.println("===================================================");
			System.out.println(sField1 + ": " + sMovimientoComunidadID);

			if (rs != null) 
			{

				while (rs.next()) 
				{
					found = true;

					sCODTRN = rs.getString(sField2);  
					sCOTDOR = rs.getString(sField3);  
					sIDPROV = rs.getString(sField4);  
					sCOACCI = rs.getString(sField5);  
					sCOENGP = rs.getString(sField6);  
					sCOCLDO = rs.getString(sField7);  
					sNUDCOM = rs.getString(sField8);  
					sBITC10 = rs.getString(sField9);  
					sCOACES = rs.getString(sField10); 
					sBITC01 = rs.getString(sField11); 
					sNOMCOC = rs.getString(sField12); 
					sBITC02 = rs.getString(sField13); 
					sNODCCO = rs.getString(sField14); 
					sBITC03 = rs.getString(sField15); 
					sNOMPRC = rs.getString(sField16); 
					sBITC04 = rs.getString(sField17); 
					sNUTPRC = rs.getString(sField18); 
					sBITC05 = rs.getString(sField19); 
					sNOMADC = rs.getString(sField20); 
					sBITC06 = rs.getString(sField21); 
					sNUTADC = rs.getString(sField22); 
					sBITC07 = rs.getString(sField23); 
					sNODCAD = rs.getString(sField24); 
					sBITC08 = rs.getString(sField25); 
					sNUCCEN = rs.getString(sField26); 
					sNUCCOF = rs.getString(sField27); 
					sNUCCDI = rs.getString(sField28); 
					sNUCCNT = rs.getString(sField29); 
					sBITC09 = rs.getString(sField30); 
					sOBTEXC = rs.getString(sField31); 
					sOBDEER = rs.getString(sField32); 

				}
			}
			if (found == false) 
			{
				System.out.println("No Information Found");
			}

		} 
		catch (SQLException ex) 
		{

			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLException: " + ex.getMessage());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: SQLState: " + ex.getSQLState());
			System.out.println("["+sClassName+"."+sMethod+"] ERROR: VendorError: " + ex.getErrorCode());
		} 
		finally 
		{
			Utils.closeResultSet(rs,sClassName,sMethod);
			Utils.closeStatement(stmt, sClassName, sMethod);
		}
		ConnectionManager.CloseDBConnection(conn);
		return new MovimientoComunidad(sCODTRN, sCOTDOR, sIDPROV, sCOACCI, sCOENGP,
				sCOCLDO, sNUDCOM, sBITC10, sCOACES, sBITC01, sNOMCOC, sBITC02,
				sNODCCO, sBITC03, sNOMPRC, sBITC04, sNUTPRC, sBITC05, sNOMADC,
				sBITC06, sNUTADC, sBITC07, sNODCAD, sBITC08, sNUCCEN, sNUCCOF,
				sNUCCDI, sNUCCNT, sBITC09, sOBTEXC, sOBDEER);
	}
}