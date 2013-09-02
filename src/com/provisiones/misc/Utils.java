package com.provisiones.misc;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;

import com.provisiones.types.ImporteDevolucion;

public class Utils 
{
	static String sClassName = Utils.class.getName();
	
	static boolean bTraza = true;
	
	public static void debugTrace(boolean bEnable, String sClass, String sMethod, String sMsg)
	{
		boolean bContrazas = true;
		//String sTimeStamp = "";
		
		if (bContrazas && bEnable)
		{
			System.out.println(timeStamp()+":["+sClass+"."+sMethod+"] "+sMsg);
		}
	}
	
	public static void debugTraceArrayList(boolean bEnable, String sClass, String sMethod, ArrayList<String> result)
	{
		if (bEnable)
		{
			for (int j=0;j<result.size();j++)
			{
				System.out.println("["+sClass+"."+sMethod+"] |"+result.get(j)+"|");
			}
		}
	}
	
	public static FacesMessage pfmsgTrace(boolean bEnable, String sExtClass, String sMethod, String sMsg)
	{
		debugTrace(true, sExtClass, sMethod, sMsg);
		return new FacesMessage(sMsg);
	}
	
	public static FacesMessage pfmsgWarning(boolean bEnable, String sExtClass, String sMethod, String sMsg)
	{
		debugTrace(true, sExtClass, sMethod, sMsg);
		return new FacesMessage(FacesMessage.SEVERITY_WARN, sMsg,null);
	}
	
	public static FacesMessage pfmsgError(boolean bEnable, String sExtClass, String sMethod, String sMsg)
	{
		debugTrace(true, sExtClass, sMethod, sMsg);
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, sMsg,null);
	}
	
	public static FacesMessage pfmsgFatal(boolean bEnable, String sExtClass, String sMethod, String sMsg)
	{
		debugTrace(true, sExtClass, sMethod, sMsg);
		return new FacesMessage(FacesMessage.SEVERITY_FATAL, sMsg,null);
	}

	public static String fechaDeHoy (boolean bFormato)
	{


		Date fechaHoy = new Date();
	
		String sHoy = "";
		
		if (bFormato)
		{
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			sHoy = format.format(fechaHoy);
			
		}
		else
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			sHoy = format.format(fechaHoy);
		}
		
		
		System.out.println("["+sClassName+".fechaDeHoy] |"+sHoy+"|");
		
		return sHoy;
	}
	
	public static String timeStamp()
	{
		Date fechaHoy = new Date();
	
		String sAhora = "";
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		sAhora = format.format(fechaHoy);
		
		//System.out.println("["+sClassName+".fechaDeHoy] |"+sAhora+"|");
		
		return sAhora;
	}

	public static boolean closeResultSet ( ResultSet rs, String sClassName, String sMethod)
	{
		boolean bExit = true;
		if (rs != null) 
		{
			try 
			{
				rs.close();
			} 
			catch (SQLException sqlEx) 
			{
				debugTrace(bTraza, sClassName, sMethod, "ERROR: Unspected connection close.");
				bExit = false;
			}
			rs = null;
		}
		return bExit;
		
	}

	public static boolean closeStatement( Statement stmt, String sClassName, String sMethod)
	{
		boolean bExit = true;
		if (stmt != null) 
		{
			try 
			{
				stmt.close();
			} 
			catch (SQLException sqlEx) 
			{
				debugTrace(bTraza, sClassName, sMethod, "ERROR: Unspected connection close.");
				bExit = false;
				
			}
			stmt = null;
		}
		return bExit;
		
	}
	public static String FormatPath(String sPath)
	{
		String sResultPath = "";
		
		{
			
		}
		
		return sResultPath;
	}
	public static void standardIO2File(String fileName){
		 
        if(fileName.equals("")){//Si viene vac�o usamos este por defecto
 
            fileName="C:\\javalog.txt";
 
        }
 
        try {
 
            //Creamos un printstream sobre el archivo permitiendo a�adir al
 
            //final para no sobreescribir.
 
            PrintStream ps = new PrintStream(new BufferedOutputStream(
 
                    new FileOutputStream(new File(fileName),true)),true);
 
            //Redirigimos entrada y salida estandar
 
            System.setOut(ps);
 
            System.setErr(ps);
 
        } catch (FileNotFoundException ex) {
 
            System.err.println("Se ha producido una excepci�n FileNotFoundException");
 
        }
 
    }
	
	public static String compruebaCodigoPago(boolean bCodDevolucion, String sTipoPago)
	{
		String sMethod = "compruebaPago";
			
		String sTipo = bCodDevolucion ? "5"+ sTipoPago : sTipoPago;
		
		debugTrace(bTraza, sClassName, sMethod, "Codigo de pago:|"+sTipo+"|");
		
		
		return sTipo;
	}
	
	public static String compruebaCodigoAlfa(String sCodigo)
	{
		String sMethod = "compruebaCodigo";
			
		String sCodigoRevisado = sCodigo;
		
		if (sCodigoRevisado.equals(""))
		{
			sCodigoRevisado = "#";
		}
		
		debugTrace(bTraza, sClassName, sMethod, "Codigo:|"+sCodigoRevisado+"|");
		
		return sCodigoRevisado;
	}
	public static String compruebaCodigoNum(String sCodigo)
	{
		String sMethod = "compruebaCodigo";
			
		String sCodigoRevisado = sCodigo;
		
		if (sCodigoRevisado.equals(""))
		{
			sCodigoRevisado = "0";
		}
		
		debugTrace(bTraza, sClassName, sMethod, "Codigo:|"+sCodigoRevisado+"|");
		
		return sCodigoRevisado;
	}

	public static String compruebaImporte(String sImporte)
	{
		String sMethod = "compruebaImporte";
		String sImporteReal = "#";
		

		String sSeparador = "";
		
		Utils.debugTrace(bTraza, sClassName, sMethod, "Importe:|"+sImporte+"|");
		
		if (sImporte.matches("-?[\\d]+([\\.|,][\\d][\\d]?)?$"))
		{
			//sImporteReal = sImporte.replace(".", "");
			//sImporteReal = sImporteReal.replace(".", "");
			
			if (sImporte.contains("."))
			{
				sSeparador = "\\.";
			}
			else if (sImporte.contains(","))
			{
				sSeparador = ",";
			}
			
			if (!sSeparador.equals(""))
			{
				String[] arrayimporte = sImporte.split(sSeparador);
				String sEuros = arrayimporte[0];
				String sCentimos = arrayimporte[1];
				if (sCentimos.length()<2)
				{
					sCentimos = sCentimos +"0";
				}
				
				Utils.debugTrace(bTraza, sClassName, sMethod, "sEuros:|"+sEuros+"|");
				Utils.debugTrace(bTraza, sClassName, sMethod, "sCentimos:|"+sCentimos+"|");
			
				sImporteReal = sEuros + sCentimos;
			}
			else
			{
				sImporteReal = sImporte+"00";
			}
		}
		else if (sImporte.equals(""))
		{
			sImporteReal= "0";
		}
		Utils.debugTrace(bTraza, sClassName, sMethod, "Importe Real:|"+sImporteReal+"|");

		
		return sImporteReal;
	}
	
	public static ImporteDevolucion separaImporteDevolucion(String sImporte)
	{
		String sMethod = "compruebaImporteDevolucion";
		
		debugTrace(bTraza, sClassName, sMethod, "sImporte:|"+sImporte+"|");
	
		return new ImporteDevolucion(sImporte.startsWith("-"),sImporte.replaceFirst("-", ""));
	}
	
	public static String compruebaImporteDevolucion(boolean bNegativo, String sImporte)
	{
		String sMethod = "compruebaImporteDevolucion";
		String sImporteReal = "#";
		
		if (sImporte.matches("-?[\\d]+([\\.|,][\\d]{2})?$"))
		{
		
			
			sImporteReal = sImporte.replaceFirst("-", "");
		
			if (sImporte.length()>3)
			{
				String sEuros = sImporteReal.substring(0, sImporte.length()-3);
				String sCentimos = sImporteReal.substring(sImporte.length()-2,sImporte.length());
		
				debugTrace(bTraza, sClassName, sMethod, "sEuros:|"+sEuros+"|");
				debugTrace(bTraza, sClassName, sMethod, "sCentimos:|"+sCentimos+"|");
		
			
				sImporteReal = bNegativo ? "-"+ sEuros + sCentimos : sEuros + sCentimos;
			}
			else if (sImporte.equals(""))
				sImporteReal= "0";
		
			debugTrace(bTraza, sClassName, sMethod, "Importe:|"+sImporteReal+"|");
		}

		
		return sImporteReal;
	}

	public static String compruebaFecha(String sFecha)
	{
		String sMethod = "compruebaFecha";
		
		debugTrace(bTraza, sClassName, sMethod, "Fecha:|"+sFecha+"|");
		
		String sFechaFormateada = "";
		
		if (sFecha.matches("[\\d]{2}[/][\\d]{2}[/][\\d]{4}$"))
		{
			/*String sDia = sFecha.substring(0, 2);
			String sMes = sFecha.substring(3, 5);
			String sA�o = sFecha.substring(6, 10);*/
			
			String[] arrayfecha = sFecha.split("/");
			String sDia = arrayfecha[0];
			String sMes = arrayfecha[1];
			String sA�o = arrayfecha[2];
		
			debugTrace(bTraza, sClassName, sMethod, "sDia:|"+sDia+"|");
			debugTrace(bTraza, sClassName, sMethod, "sMes:|"+sMes+"|");
			debugTrace(bTraza, sClassName, sMethod, "sA�o:|"+sA�o+"|");
		
		
			
			sFechaFormateada = sA�o+sMes+sDia;
		
			debugTrace(bTraza, sClassName, sMethod, "Fecha:|"+sFechaFormateada+"|");
		
			try 
			{
				DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				formatter.setLenient(false);
				Date myDate = formatter.parse(sFechaFormateada);
				debugTrace(bTraza, sClassName, sMethod,"|"+myDate+"|");
			} 
			catch (ParseException e) 
			{
				sFechaFormateada = "#";
			} 
		}
		else
			sFechaFormateada = "0";
		
		return sFechaFormateada;
	}
	
    public static boolean compruebaCC(String sNUCCEN, String sNUCCOF, String sNUCCDI, String sNUCCNT) 
    {

		boolean bTraza = true;
		String sMethod = "compruebaCC";
		
    	String sDC = "";
        int iTotal = 0;
        int iProducto = 0;
        String sCifra1 = "";
        String sCifra2 = "";
        
        boolean bResultado = false;
        
        Utils.debugTrace(bTraza, sClassName, sMethod, "sNUCCEN|"+sNUCCEN+"|");
        Utils.debugTrace(bTraza, sClassName, sMethod, "sNUCCOF|"+sNUCCOF+"|");
        Utils.debugTrace(bTraza, sClassName, sMethod, "sNUCCDI|"+sNUCCDI+"|");
        Utils.debugTrace(bTraza, sClassName, sMethod, "sNUCCNT|"+sNUCCNT+"|");
        
        if ((sNUCCEN.length() == 4)
        	&& (sNUCCOF.length() == 4)
        	&& (sNUCCDI.length() == 2)
        	&& (sNUCCNT.length() == 10))
        {
            /*Primer d�gito.*/
            for (int i = 0; i < 4; i++) 
            {
                if (i==0)
                {
                    iProducto = Integer.parseInt(sNUCCEN.substring(i, i + 1))*4;
                }
                else if (i==1)
                {
                    iProducto = Integer.parseInt(sNUCCEN.substring(i, i + 1))*8;
                }
                else if (i==2)
                {
                    iProducto = Integer.parseInt(sNUCCEN.substring(i, i + 1))*5;
                }
                else 
                {
                    iProducto = Integer.parseInt(sNUCCEN.substring(i, i + 1))*10;
                }
                iTotal = iTotal + iProducto;
            }
            for (int j = 0; j < 4; j++) 
            {
                if (j==0)
                {
                    iProducto = Integer.parseInt(sNUCCOF.substring(j, j + 1))*9;
                }
                else if (j==1)
                {
                    iProducto = Integer.parseInt(sNUCCOF.substring(j, j + 1))*7;
                }
                else if (j==2)
                {
                    iProducto = Integer.parseInt(sNUCCOF.substring(j, j + 1))*3;
                }
                else 
                {
                    iProducto = Integer.parseInt(sNUCCOF.substring(j, j + 1))*6;
                }
                iTotal = iTotal + iProducto;
            }
     
            iProducto = 11 - iTotal % 11;
            
            if (iProducto == 10) 
            {
                sCifra1 = "1";
            } 
            else if (iProducto == 11) 
            {
                sCifra1 = "0";
            } 
            else 
            {
                sCifra1 = String.valueOf(iProducto);
            }
            
            iTotal=0;
     
            /*Segundo d�gito.*/
            for (int k=0; k<10; k++)
            {
                if (k==0)
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*1;
                }
                else if (k==1)
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*2;
                }
                else if (k==2)
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*4;
                }
                else if (k==3)
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*8;
                }
                else if (k==4)
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*5;
                }
                else if (k==5)
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*10;
                }
                else if (k==6)
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*9;
                }
                else if (k==7)
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*7;
                }
                else if (k==8)
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*3;
                }
                else 
                {
                    iProducto = Integer.parseInt(sNUCCNT.substring(k, k + 1))*6;
                }
                iTotal = iTotal + iProducto;
            }
            
            iProducto = 11 - iTotal % 11;
            
            if (iProducto == 10) 
            {
                sCifra2 = "1";
            } 
            else if (iProducto == 11) 
            {
                sCifra2 = "0";
            } 
            else 
            {
                sCifra2 = String.valueOf(iProducto);
            }

            sDC=sCifra1+sCifra2;
            
            bResultado = sDC.equals(sNUCCDI); 
        }

        return bResultado;
    }
    
    public static boolean compruebaCIF(String sNUDCOM) 
    {

        boolean bResultado = false;

        try 
        {
            String sCIF = sNUDCOM.trim();

            int iSuma = 0;

            int iCodigoControl = 0;
 
            String sLetrasCIF = "ABCDEFGHJKLMNPQRSUVW";
            
            String sPrimeraLetra = sCIF.substring(0, 1);
            
            
            
            if (!(sCIF.length() == 10))
            {
            	bResultado =  false;
            }
            if (!sCIF.matches("[A-Z][0-9]{9}$"))
            {
            	bResultado =  false;
            }
            if (sLetrasCIF.indexOf(sPrimeraLetra) < 0)
            {
            	bResultado =  false;
            }
            else
            {
                iSuma = iSuma + Integer.parseInt(sCIF.substring(2, 3)) + Integer.parseInt(sCIF.substring(4, 5))
                        + Integer.parseInt(sCIF.substring(6, 7));

                for (int i = 1; i < 8; i = i + 2) 
                {

                    int iValor = (Integer.parseInt(sCIF.substring(i, i + 1)) * 2);

                    if (iValor < 10)
                        iSuma = iSuma + iValor;
                    else 
                    {
                        iSuma = iSuma + (Integer.parseInt(String.valueOf(iValor).substring(0, 1)))
                                + (Integer.parseInt(String.valueOf(iValor).substring(1, 2)));
                    }
                }

                iCodigoControl = ((10 - (iSuma % 10)) % 10);

                String sLetraSociedad = "KLMNPQRSW";
                
                String sUltimaLetra = sCIF.substring(8, 9);
                
                if (sLetraSociedad.indexOf(sPrimeraLetra) >= 0) 
                {
                    byte[] ascii = new byte[1];

                    if (iCodigoControl == 0)
                        iCodigoControl = 10;
                    iCodigoControl = iCodigoControl + 64;
                    ascii[0] = (Integer.valueOf(iCodigoControl)).byteValue();


                    
                    bResultado = (sUltimaLetra.equals(new String(ascii)));
                } 
                else 
                {
                	String sNumsCIF = "0123456789";
                    bResultado = (iCodigoControl == sNumsCIF.indexOf(sUltimaLetra));
                }
            }


        } 
        catch (Exception e) 
        {
            bResultado = false;
        }
        return bResultado;
    }
    
	public static boolean compruebaCorreo(String sCorreo)
	{
		//String sMethod = "compruebaCorreo";
		
		return sCorreo.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}

	
	public static String recuperaImporte(boolean bNegativo, String sImporte)
	{
		String sMethod = "recuperaImporte";
		
		String sImporteReal = "0";
		
		if (sImporte.length()>2)
		{
			String sEuros = sImporte.substring(0, sImporte.length()-2);
			String sCentimos = sImporte.substring(sImporte.length()-2,sImporte.length());
		
			debugTrace(bTraza, sClassName, sMethod, "sEuros:|"+sEuros+"|");
			debugTrace(bTraza, sClassName, sMethod, "sCentimos:|"+sCentimos+"|");
		
			
			sImporteReal = bNegativo ? "-"+ sEuros + "." + sCentimos : sEuros + "." + sCentimos;
		}
		debugTrace(bTraza, sClassName, sMethod, "Importe:|"+sImporteReal+"|");
		
		return sImporteReal;
	}
	
	public static String recuperaFecha(String sFecha)
	{
		String sMethod = "recuperaFecha";
		
		debugTrace(bTraza, sClassName, sMethod, "Fecha:|"+sFecha+"|");
		
		String sFechaFormateada = "";
		
		if (!sFecha.equals("0"))
		{
			String sA�o = sFecha.substring(0, 4);
			String sMes = sFecha.substring(4, 6);
			String sDia = sFecha.substring(6, 8);
			
		
			debugTrace(bTraza, sClassName, sMethod, "sDia:|"+sDia+"|");
			debugTrace(bTraza, sClassName, sMethod, "sMes:|"+sMes+"|");
			debugTrace(bTraza, sClassName, sMethod, "sA�o:|"+sA�o+"|");
		
		
			
			sFechaFormateada = sDia+"/"+sMes+"/"+sA�o;
		
			debugTrace(bTraza, sClassName, sMethod, "Fecha:|"+sFechaFormateada+"|");
		}
		
		return sFechaFormateada;
	}
	
	public static String recuperaCodigo(String sCodigo)
	{
		String sMethod = "compruebaCodigo";
			
		String sCodigoRevisado = sCodigo;
		
		if (sCodigoRevisado.equals("#"))
		{
			sCodigoRevisado = "";
		}
		
		debugTrace(bTraza, sClassName, sMethod, "Codigo:|"+sCodigoRevisado+"|");
		
		return sCodigoRevisado;
	}
}
