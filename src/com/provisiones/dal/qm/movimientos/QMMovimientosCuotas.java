package com.provisiones.dal.qm.movimientos;

import com.provisiones.dal.ConnectionManager;
import com.provisiones.misc.Utils;
import com.provisiones.types.movimientos.MovimientoCuota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class QMMovimientosCuotas
{
	private static Logger logger = LoggerFactory.getLogger(QMMovimientosCuotas.class.getName());

	public static final String TABLA = "pp002_e2_movimientos_tbl";

	public static final String CAMPO1 = "e2_movimiento_id";

	public static final String CAMPO2  = "cod_codtrn";
	public static final String CAMPO3  = "cod_cotdor";
	public static final String CAMPO4  = "idprov";
	public static final String CAMPO5  = "cod_coacci";
	public static final String CAMPO6  = "cod_cocldo";
	public static final String CAMPO7  = "cod_nudcom";
	public static final String CAMPO8  = "coengp";
	public static final String CAMPO9  = "cod_coaces";
	public static final String CAMPO10 = "cogrug";     
	public static final String CAMPO11 = "cotaca";     
	public static final String CAMPO12 = "cod_cosbac";     
	public static final String CAMPO13 = "cod_bitc11"; 
	public static final String CAMPO14 = "fipago";     
	public static final String CAMPO15 = "cod_bitc12"; 
	public static final String CAMPO16 = "ffpago";     
	public static final String CAMPO17 = "cod_bitc13"; 
	public static final String CAMPO18 = "imcuco";     
	public static final String CAMPO19 = "cod_bitc14"; 
	public static final String CAMPO20 = "faacta";     
	public static final String CAMPO21 = "cod_bitc15"; 
	public static final String CAMPO22 = "cod_ptpago";     
	public static final String CAMPO23 = "cod_bitc09"; 
	public static final String CAMPO24 = "obtexc";     
	public static final String CAMPO25 = "obdeer";

	//Campos de control
	public static final String CAMPO26  = "usuario_movimiento";
	public static final String CAMPO27  = "fecha_movimiento";
	
	private QMMovimientosCuotas(){}
	
	public static long addMovimientoCuota(Connection conexion, MovimientoCuota NuevoMovimientoCuota)
	{
		long liCodigo = 0;
		
		if (conexion != null)
		{
			Statement stmt = null;
			ResultSet resulset = null;
			
			String sUsuario = ConnectionManager.getUser();
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "INSERT INTO " 
					   + TABLA + 
					   " ("
				       + CAMPO2  + ","              
				       + CAMPO3  + ","              
				       + CAMPO4  + ","              
				       + CAMPO5  + ","              
				       + CAMPO6  + ","              
				       + CAMPO7  + ","              
				       + CAMPO8  + ","              
				       + CAMPO9  + ","              
				       + CAMPO10 + ","              
				       + CAMPO11 + ","              
				       + CAMPO12 + ","              
				       + CAMPO13 + ","              
				       + CAMPO14 + ","              
				       + CAMPO15 + ","              
				       + CAMPO16 + ","              
				       + CAMPO17 + ","              
				       + CAMPO18 + ","              
				       + CAMPO19 + ","              
				       + CAMPO20 + ","              
				       + CAMPO21 + ","              
				       + CAMPO22 + ","              
				       + CAMPO23 + ","              
				       + CAMPO24 + ","
				       + CAMPO25 + ","
				       + CAMPO26 + ","
				       + CAMPO27 + 
				       ") VALUES ('" 
				       + NuevoMovimientoCuota.getCODTRN() + "','" 
				       + NuevoMovimientoCuota.getCOTDOR() + "','"
				       + NuevoMovimientoCuota.getIDPROV() + "','"
				       + NuevoMovimientoCuota.getCOACCI() + "','"
				       + NuevoMovimientoCuota.getCOCLDO() + "','"
				       + NuevoMovimientoCuota.getNUDCOM() + "','"
				       + NuevoMovimientoCuota.getCOENGP() + "','"
				       + NuevoMovimientoCuota.getCOACES() + "','"
				       + NuevoMovimientoCuota.getCOGRUG() + "','"
				       + NuevoMovimientoCuota.getCOTACA() + "','"
				       + NuevoMovimientoCuota.getCOSBAC() + "','"
				       + NuevoMovimientoCuota.getBITC11() + "','"
				       + NuevoMovimientoCuota.getFIPAGO() + "','"
				       + NuevoMovimientoCuota.getBITC12() + "','"
				       + NuevoMovimientoCuota.getFFPAGO() + "','"
				       + NuevoMovimientoCuota.getBITC13() + "','"
				       + NuevoMovimientoCuota.getIMCUCO() + "','"
				       + NuevoMovimientoCuota.getBITC14() + "','"
				       + NuevoMovimientoCuota.getFAACTA() + "','"
				       + NuevoMovimientoCuota.getBITC15() + "','"
				       + NuevoMovimientoCuota.getPTPAGO() + "','"
				       + NuevoMovimientoCuota.getBITC09() + "','"
				       + NuevoMovimientoCuota.getOBTEXC() + "','"
				       + NuevoMovimientoCuota.getOBDEER() + "','"
				       + sUsuario + "','"
				       + Utils.timeStamp() +
				       "')";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery, Statement.RETURN_GENERATED_KEYS);
				
				resulset = stmt.getGeneratedKeys();
				
				logger.debug("Ejecutada con exito!");
				
				if (resulset.next()) 
				{
					liCodigo= resulset.getLong(1);
				}
			} 
			catch (SQLException ex) 
			{
				liCodigo = 0;
				
				logger.error("ERROR COACES:|"+NuevoMovimientoCuota.getCOACES()+"|");
				logger.error("ERROR COCLDO:|"+NuevoMovimientoCuota.getCOCLDO()+"|");
				logger.error("ERROR NUDCOM:|"+NuevoMovimientoCuota.getNUDCOM()+"|");
				logger.error("ERROR COSBAC:|"+NuevoMovimientoCuota.getCOSBAC()+"|");
				
				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally
			{
				Utils.closeStatement(stmt);
				Utils.closeResultSet(resulset);
			}
		}

		return liCodigo;
	}
	public static boolean modMovimientoCuota(Connection conexion, MovimientoCuota NuevoMovimientoCuota, long liMovimientoCuotaID)
	{
		boolean bSalida = false;
		
		if (conexion != null)
		{
			Statement stmt = null;

			logger.debug("Ejecutando Query...");
			
			String sQuery = "UPDATE " 
					+ TABLA + 
					" SET " 
					+ CAMPO2  + " = '"+ NuevoMovimientoCuota.getCODTRN() + "', "
					+ CAMPO3  + " = '"+ NuevoMovimientoCuota.getCOTDOR() + "', "
					+ CAMPO4  + " = '"+ NuevoMovimientoCuota.getIDPROV() + "', "
					+ CAMPO5  + " = '"+ NuevoMovimientoCuota.getCOACCI() + "', "
					+ CAMPO6  + " = '"+ NuevoMovimientoCuota.getCOCLDO() + "', "
					+ CAMPO7  + " = '"+ NuevoMovimientoCuota.getNUDCOM() + "', "
					+ CAMPO8  + " = '"+ NuevoMovimientoCuota.getCOENGP() + "', "
					+ CAMPO9  + " = '"+ NuevoMovimientoCuota.getCOACES() + "', "
					+ CAMPO10 + " = '"+ NuevoMovimientoCuota.getCOGRUG() + "', "
					+ CAMPO11 + " = '"+ NuevoMovimientoCuota.getCOTACA() + "', "
					+ CAMPO12 + " = '"+ NuevoMovimientoCuota.getCOSBAC() + "', "
					+ CAMPO13 + " = '"+ NuevoMovimientoCuota.getBITC11() + "', "
					+ CAMPO14 + " = '"+ NuevoMovimientoCuota.getFIPAGO() + "', "
					+ CAMPO15 + " = '"+ NuevoMovimientoCuota.getBITC12() + "', "
					+ CAMPO16 + " = '"+ NuevoMovimientoCuota.getFFPAGO() + "', "
					+ CAMPO17 + " = '"+ NuevoMovimientoCuota.getBITC13() + "', "
					+ CAMPO18 + " = '"+ NuevoMovimientoCuota.getIMCUCO() + "', "
					+ CAMPO19 + " = '"+ NuevoMovimientoCuota.getBITC14() + "', "
					+ CAMPO20 + " = '"+ NuevoMovimientoCuota.getFAACTA() + "', "
					+ CAMPO21 + " = '"+ NuevoMovimientoCuota.getBITC15() + "', "
					+ CAMPO22 + " = '"+ NuevoMovimientoCuota.getPTPAGO() + "', "
					+ CAMPO23 + " = '"+ NuevoMovimientoCuota.getBITC09() + "', "
					+ CAMPO24 + " = '"+ NuevoMovimientoCuota.getOBTEXC() + "', "
					+ CAMPO25 + " = '"+ NuevoMovimientoCuota.getOBDEER() + "' "+
					" WHERE "
					+ CAMPO1 + " = '"+ liMovimientoCuotaID +"'";
			
			logger.debug(sQuery);
			
			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con exito!");
				
				bSalida = true;
			} 
			catch (SQLException ex) 
			{
				bSalida = false;
				
				logger.error("ERROR MovimientoCuotaID:|"+liMovimientoCuotaID+"|");
				logger.error("ERROR COACES:|"+NuevoMovimientoCuota.getCOACES()+"|");
				logger.error("ERROR COCLDO:|"+NuevoMovimientoCuota.getCOCLDO()+"|");
				logger.error("ERROR NUDCOM:|"+NuevoMovimientoCuota.getNUDCOM()+"|");
				logger.error("ERROR COSBAC:|"+NuevoMovimientoCuota.getCOSBAC()+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}

	public static boolean delMovimientoCuota(Connection conexion, long liMovimientoCuotaID)
	{
		boolean bSalida = false;

		if (conexion != null)
		{
			Statement stmt = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "DELETE FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '" + liMovimientoCuotaID + "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();
				stmt.executeUpdate(sQuery);
				
				logger.debug("Ejecutada con exito!");
				
				bSalida = true;
			} 
			catch (SQLException ex) 
			{
				bSalida = false;
				
				logger.error("ERROR MovimientoCuotaID:|"+liMovimientoCuotaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bSalida;
	}
	
	public static MovimientoCuota getMovimientoCuota(Connection conexion, long liMovimientoCuotaID)
	{
		String sCODTRN = "";
		String sCOTDOR = "";
		String sIDPROV = "";
		String sCOACCI = "";
		String sCOCLDO = "";
		String sNUDCOM = "";
		String sCOENGP = "";
		String sCOACES = "";
		String sCOGRUG = "";
		String sCOTACA = "";
		String sCOSBAC = "";
		String sBITC11 = "";
		String sFIPAGO = "";
		String sBITC12 = "";
		String sFFPAGO = "";
		String sBITC13 = "";
		String sIMCUCO = "";
		String sBITC14 = "";
		String sFAACTA = "";
		String sBITC15 = "";
		String sPTPAGO = "";
		String sBITC09 = "";
		String sOBTEXC = "";
		String sOBDEER = "";

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
				       + CAMPO2  + ","              
				       + CAMPO3  + ","              
				       + CAMPO4  + ","              
				       + CAMPO5  + ","              
				       + CAMPO6  + ","              
				       + CAMPO7  + ","              
				       + CAMPO8  + ","              
				       + CAMPO9  + ","              
				       + CAMPO10 + ","              
				       + CAMPO11 + ","              
				       + CAMPO12 + ","              
				       + CAMPO13 + ","              
				       + CAMPO14 + ","              
				       + CAMPO15 + ","              
				       + CAMPO16 + ","              
				       + CAMPO17 + ","              
				       + CAMPO18 + ","              
				       + CAMPO19 + ","              
				       + CAMPO20 + ","              
				       + CAMPO21 + ","              
				       + CAMPO22 + ","              
				       + CAMPO23 + ","
				       + CAMPO24 + ","
				       + CAMPO25 +              
				       " FROM " 
				       + TABLA + 
				       " WHERE "
				       + CAMPO1 + " = '" + liMovimientoCuotaID	+ "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");

				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						sCODTRN = rs.getString(CAMPO2);
						sCOTDOR = rs.getString(CAMPO3);
						sIDPROV = rs.getString(CAMPO4);
						sCOACCI = rs.getString(CAMPO5);
						sCOCLDO = rs.getString(CAMPO6);
						sNUDCOM = rs.getString(CAMPO7);
						sCOENGP = rs.getString(CAMPO8);
						sCOACES = rs.getString(CAMPO9); 
						sCOGRUG = rs.getString(CAMPO10);
						sCOTACA = rs.getString(CAMPO11);
						sCOSBAC = rs.getString(CAMPO12);
						sBITC11 = rs.getString(CAMPO13);
						sFIPAGO = rs.getString(CAMPO14);
						sBITC12 = rs.getString(CAMPO15);
						sFFPAGO = rs.getString(CAMPO16);
						sBITC13 = rs.getString(CAMPO17);
						sIMCUCO = rs.getString(CAMPO18);
						sBITC14 = rs.getString(CAMPO19);
						sFAACTA = rs.getString(CAMPO20);
						sBITC15 = rs.getString(CAMPO21);
						sPTPAGO = rs.getString(CAMPO22);
						sBITC09 = rs.getString(CAMPO23);
						sOBTEXC = rs.getString(CAMPO24);
						sOBDEER = rs.getString(CAMPO25);

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+liMovimientoCuotaID+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontr� la informaci�n.");
				}
			} 
			catch (SQLException ex) 
			{
				sCODTRN = "";
				sCOTDOR = "";
				sIDPROV = "";
				sCOACCI = "";
				sCOCLDO = "";
				sNUDCOM = "";
				sCOENGP = "";
				sCOACES = "";
				sCOGRUG = "";
				sCOTACA = "";
				sCOSBAC = "";
				sBITC11 = "";
				sFIPAGO = "";
				sBITC12 = "";
				sFFPAGO = "";
				sBITC13 = "";
				sIMCUCO = "";
				sBITC14 = "";
				sFAACTA = "";
				sBITC15 = "";
				sPTPAGO = "";
				sBITC09 = "";
				sOBTEXC = "";
				sOBDEER = "";
				
				logger.error("ERROR MovimientoCuotaID:|"+liMovimientoCuotaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return new MovimientoCuota(sCODTRN, sCOTDOR, sIDPROV, sCOACCI, sCOCLDO, sNUDCOM,
				sCOENGP, sCOACES, sCOGRUG, sCOTACA, sCOSBAC, sBITC11, sFIPAGO,
				sBITC12, sFFPAGO, sBITC13, sIMCUCO, sBITC14, sFAACTA, sBITC15,
				sPTPAGO, sBITC09, sOBTEXC, sOBDEER);
	}
	
	public static long getMovimientoCuotaID(Connection conexion, MovimientoCuota cuota)
	{
		long liMovimientoCuotaID = 0;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			boolean bEncontrado = false;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT "
					+ CAMPO1 + 
					" FROM " 
					+ TABLA + 
					" WHERE ("
					+ CAMPO2  +" = '" + cuota.getCODTRN() + "' AND "
					+ CAMPO3  +" = '" + cuota.getCOTDOR() + "' AND "
					+ CAMPO4  +" = '" + cuota.getIDPROV() + "' AND "
					+ CAMPO5  +" = '" + cuota.getCOACCI() + "' AND "
					+ CAMPO6  +" = '" + cuota.getCOCLDO() + "' AND "
					+ CAMPO7  +" = '" + cuota.getNUDCOM() + "' AND "
					+ CAMPO8  +" = '" + cuota.getCOENGP() + "' AND "
					+ CAMPO9  +" = '" + cuota.getCOACES() + "' AND "
					+ CAMPO10 +" = '" + cuota.getCOGRUG() + "' AND "
					+ CAMPO11 +" = '" + cuota.getCOTACA() + "' AND "
					+ CAMPO12 +" = '" + cuota.getCOSBAC() + "' AND "
					+ CAMPO13 +" = '" + cuota.getBITC11() + "' AND "
					+ CAMPO14 +" = '" + cuota.getFIPAGO() + "' AND "
					+ CAMPO15 +" = '" + cuota.getBITC12() + "' AND "
					+ CAMPO16 +" = '" + cuota.getFFPAGO() + "' AND "
					+ CAMPO17 +" = '" + cuota.getBITC13() + "' AND "
					+ CAMPO18 +" = '" + cuota.getIMCUCO() + "' AND "
					+ CAMPO19 +" = '" + cuota.getBITC14() + "' AND "
					+ CAMPO20 +" = '" + cuota.getFAACTA() + "' AND "
					+ CAMPO21 +" = '" + cuota.getBITC15() + "' AND "
					+ CAMPO22 +" = '" + cuota.getPTPAGO() + "' AND "
					+ CAMPO23 +" = '" + cuota.getBITC09() + "' AND "
					+ CAMPO24 +" = '" + cuota.getOBTEXC() + "' AND "
					+ CAMPO25 +" = '" + cuota.getOBDEER() + 
					"')";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");
				
				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						liMovimientoCuotaID = rs.getLong(CAMPO1);
						
						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+liMovimientoCuotaID+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontr� la informaci�n.");
				}
			} 
			catch (SQLException ex) 
			{
				liMovimientoCuotaID = 0;
				
				logger.error("ERROR COACES:|"+cuota.getCOACES()+"|");
				logger.error("ERROR COCLDO:|"+cuota.getCOCLDO()+"|");
				logger.error("ERROR NUDCOM:|"+cuota.getNUDCOM()+"|");
				logger.error("ERROR COSBAC:|"+cuota.getCOSBAC()+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeResultSet(rs);
				Utils.closeStatement(stmt);
			}
		}

		return liMovimientoCuotaID;
	}
	
	public static boolean existeMovimientoCuota(Connection conexion, long liMovimientoCuotaID)
	{
		boolean bEncontrado = false;

		if (conexion != null)
		{
			Statement stmt = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			logger.debug("Ejecutando Query...");
			
			String sQuery = "SELECT " 
					+ CAMPO1 + 
					" FROM " 
					+ TABLA + 
					" WHERE " 
					+ CAMPO1 + " = '" + liMovimientoCuotaID + "'";
			
			logger.debug(sQuery);

			try 
			{
				stmt = conexion.createStatement();

				pstmt = conexion.prepareStatement(sQuery);
				rs = pstmt.executeQuery();
				
				logger.debug("Ejecutada con exito!");
				
				if (rs != null) 
				{
					while (rs.next()) 
					{
						bEncontrado = true;

						logger.debug("Encontrado el registro!");
						logger.debug(CAMPO1+":|"+rs.getString(CAMPO1)+"|");
					}
				}
				if (!bEncontrado) 
				{
					logger.debug("No se encontro la informaci�n.");
				}
			} 
			catch (SQLException ex) 
			{
				bEncontrado = false;

				logger.error("ERROR liMovimientoCuotaID:|"+liMovimientoCuotaID+"|");

				logger.error("ERROR "+ex.getErrorCode()+" ("+ex.getSQLState()+"): "+ ex.getMessage());
			} 
			finally 
			{
				Utils.closeStatement(stmt);
			}
		}

		return bEncontrado;
	}

}

//Autor: Francisco Valverde Manj�n