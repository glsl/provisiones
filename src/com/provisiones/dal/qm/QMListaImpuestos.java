package com.provisiones.dal.qm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.misc.ValoresDefecto;

public class QMListaImpuestos 
{
	static String sClassName = QMListaImpuestos.class.getName();

	static String sTable = "lista_impuestos_multi";
	
	static String sField1 = "cod_coaces";
	static String sField2 = "cod_nurcat";
	static String sField3 = "cod_cosbac";
	static String sField4 = "cod_movimiento";
	static String sField5 = "cod_validado";

	public static boolean addRelacionImpuestos(String sCodCOACES, String sCodNURCAT, String sCodCOSBAC, String sCodMovimiento) 
	{
		String sMethod = "addRelacionImpuestos";
		Statement stmt = null;
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO " + sTable + " (" 
			+ sField1 + ","
			+ sField2 + ","
			+ sField3 + "," 
			+ sField4 + "," 
			+ sField5 + ") " 
			+ "VALUES ('" 
			+ sCodCOACES + "','"
			+ sCodNURCAT + "','"
			+ sCodCOSBAC + "','"
			+ sCodMovimiento + "','"
			+ ValoresDefecto.DEF_VALIDADO + "')");
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

	public static boolean delRelacionImpuestos(String sCodCOACES, String sCodNURCAT, String sCodCOSBAC, String CodImpuestos) 
	{
		String sMethod = "delRelacionImpuestos";
		Statement stmt = null;
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM " + sTable + 
					" WHERE (" + sField1 + " = '" + sCodCOACES + "' " +
							"AND  " + sField2 + " = '" + sCodNURCAT + "' "+
							"AND  " + sField3 + " = '" + sCodCOSBAC + "' "+
							"AND  " + sField4 + " = '" + CodImpuestos +"')");
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

	public static ArrayList<String>  getImpuestos(String sCodCOACES, String sCodNURCAT, String sCodCOSBAC) 
	{
		String sMethod = "getImpuestos";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	
		
		ArrayList<String> result = new ArrayList<String>(); 
		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField3 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodCOACES + "' " +
					" AND " + sField2 + " = '" + sCodNURCAT +
					" AND " + sField3 + " = '" + sCodCOSBAC +"' )");

			rs = pstmt.executeQuery();
			
			
			System.out.println("===================================================");
			System.out.println(sField1 + ": " + sCodCOACES);
			System.out.println(sField2 + ": " + sCodNURCAT);
			System.out.println(sField3 + ": " + sCodCOSBAC);

			

			
			int i = 0;
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					result.add(rs.getString(sField3));
					System.out.println(result.get(i));

					System.out.println("===================================================");
					i++;
				}
			}
			if (found == false) 
			{
				result = new ArrayList<String>(); 
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
		return result;
	}

	public static boolean setValidado(String sCodCOACES, String sCodNURCAT, String sCodCOSBAC, String sCodMovimiento, String sValidado)
	{
		String sMethod = "setValidado";
		Statement stmt = null;
		boolean bExit = false;
		Connection conn = null;
		
		conn = ConnectionManager.OpenDBConnection();
		
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE " + sTable + 
					" SET " 
					+ sField4 + " = '"+ sValidado + 
					"' "+
					" WHERE "+
					"(" + sField1 + " = '" + sCodCOACES + "' " +
					" AND " + sField2 + " = '" + sCodNURCAT +
					" AND " + sField3 + " = '" + sCodCOSBAC +
					" AND " + sField4 + " = '" + sCodMovimiento +"' )");
			
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
	
	public static String getValidado(String sCodCOACES, String sCodNURCAT, String sCodCOSBAC)
	{
		String sMethod = "getValidado";

		Statement stmt = null;
		ResultSet rs = null;


		PreparedStatement pstmt = null;
		boolean found = false;
	

		String sValidado = "";

		Connection conn = null;

		conn = ConnectionManager.OpenDBConnection();

		try 
		{
			stmt = conn.createStatement();


			pstmt = conn.prepareStatement("SELECT " + sField4 + "  FROM " + sTable + 
					" WHERE " +
					"(" + sField1 + " = '" + sCodCOACES + "' " +
					" AND " + sField2 + " = '" + sCodNURCAT +
					" AND " + sField3 + " = '" + sCodCOSBAC +"' )");

			rs = pstmt.executeQuery();
			
			
			if (rs != null) 
			{
				
				while (rs.next()) 
				{
					found = true;

					sValidado = rs.getString(sField4);
					System.out.println("===================================================");
					System.out.println(sField1 + ": " + sCodCOACES);
					System.out.println(sField2 + ": " + sCodNURCAT);
					System.out.println(sField3 + ": " + sCodCOSBAC);
					System.out.println(sField4 + ": " + sValidado);


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
		return sValidado;
	}
}
