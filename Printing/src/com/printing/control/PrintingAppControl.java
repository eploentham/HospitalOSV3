/**
 *@author  tong
 *  ใช้ในการพิพม์เอกสารออกทางเครื่องพิมพ์ และใช้ในการ Comply ไฟล์ นามสกุล XML 
 *  ให้เป็น jasper หรือ xls หรือรูปแบบต่างๆตามต้องการ
 */

package com.printing.control;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.util.*;
import net.sf.jasperreports.view.*;
//import dori.jasper.engine.*;
//import dori.jasper.engine.export.*;
//import dori.jasper.engine.util.*;
//import dori.jasper.view.*;
import java.util.*;
import java.io.*;
import java.sql.*;


/**
 *
 */
public class PrintingAppControl
{


	/**
	 *
	 */
	private  final String TASK_COMPILE = "compile";
	private  final String TASK_FILL = "fill";
	private  final String TASK_PRINT = "print";
	private  final String TASK_PDF = "pdf";
	private  final String TASK_XML = "xml";
	private  final String TASK_XML_EMBED = "xmlEmbed";
	private  final String TASK_HTML = "html";
	private  final String TASK_XLS = "xls";
	private  final String TASK_CSV = "csv";
	private  final String TASK_RUN = "run";
        private  final String TASK_VIEW= "view";
        private  final String TASK_FILL_CUS = "fill_cus";
	
	
        
	/**
	 *
	 */
	public boolean Method(String fimename, String taskname, Map parameters,JRDataSource dataSource,boolean choosePrinter)
	{
		String fileName = fimename;
		String taskName = taskname;
                boolean values = true;
		if(fileName.length() == 0  || taskName.length() == 0)//if(args.length == 0)
		{
			usage();
			return false;
		}
				
		
                System.out.println(taskName);
                System.out.println(fileName);
            
                
		try
		{
			long start = System.currentTimeMillis();
			if (TASK_COMPILE.equals(taskName))
			{
				JasperCompileManager.compileReportToFile(fileName);
				System.err.println("Compile time : " + (System.currentTimeMillis() - start));
			//	System.exit(0);
			}
			else if (TASK_FILL.equals(taskName))
			{
				JasperFillManager.fillReportToFile(fileName,parameters, new JREmptyDataSource());//new JREmptyDataSource());
				System.err.println("Filling time : " + (System.currentTimeMillis() - start));
			//	System.exit(0);
			}
                        else if (TASK_FILL_CUS.equals(taskName))
			{
				JasperFillManager.fillReportToFile(fileName,parameters, dataSource);//new JREmptyDataSource());
				System.err.println("Filling time : " + (System.currentTimeMillis() - start));
			//	System.exit(0);
			}
			else if (TASK_PRINT.equals(taskName))
			{
				JasperPrintManager.printReport(fileName, choosePrinter);
				System.err.println("Printing time : " + (System.currentTimeMillis() - start));
			//	System.exit(0);
			}
			else if (TASK_PDF.equals(taskName))
			{
				JasperExportManager.exportReportToPdfFile(fileName);
				System.err.println("PDF creation time : " + (System.currentTimeMillis() - start));
			//	System.exit(0);
			}
			else if (TASK_XML.equals(taskName))
			{
				JasperExportManager.exportReportToXmlFile(fileName, false);
				System.err.println("XML creation time : " + (System.currentTimeMillis() - start));
			//	System.exit(0);
			}
			else if (TASK_XML_EMBED.equals(taskName))
			{
				JasperExportManager.exportReportToXmlFile(fileName, true);
				System.err.println("XML creation time : " + (System.currentTimeMillis() - start));
			//	System.exit(0);
			}
			else if (TASK_HTML.equals(taskName))
			{
				JasperExportManager.exportReportToHtmlFile(fileName);
				System.err.println("HTML creation time : " + (System.currentTimeMillis() - start));
			//	System.exit(0);
			}
			else if (TASK_XLS.equals(taskName))
			{
				File sourceFile = new File(fileName);
				JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);
                                File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".xls");
				JRXlsExporter exporter = new JRXlsExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
				exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
				exporter.exportReport();
				System.err.println("XLS creation time : " + (System.currentTimeMillis() - start));
			//	System.exit(0);
			}
			else if (TASK_CSV.equals(taskName))
			{
				File sourceFile = new File(fileName);
                                JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);
                                File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".csv");
				JRCsvExporter exporter = new JRCsvExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
				exporter.exportReport();
				System.err.println("CSV creation time : " + (System.currentTimeMillis() - start));
			//	System.exit(0);
			}
                        else if (TASK_VIEW.equals(taskName))
			{   // ใช้ได้ jrpxml  
                                JasperViewer.viewReport(fileName,true,false);
                               
                               
                         //   dori.jasper.view.JasperDesignViewer.viewReportDesign(fileName,true);
                            System.err.println("PDF creation time : " + (System.currentTimeMillis() - start));
			
			}
			else if (TASK_RUN.equals(taskName))
			{
				JasperRunManager.runReportToPdfFile(fileName, parameters, new JREmptyDataSource());
				System.err.println("PDF running time : " + (System.currentTimeMillis() - start));
			//	System.exit(0);
			}
			else
			{
				usage();
			//	System.exit(0);
                                values = false;
			}
		}
		catch (JRException e)
		{
			e.printStackTrace();
		//	System.exit(1);
                        values = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
                        values = false;
		//	System.exit(1);
		}
                
                
                fileName = null;
		taskName = null;
                System.gc();
                return values;
	}


	/**
	 *
	 */
	private static void usage()
	{
		System.out.println( "ChartApp usage:" );
		System.out.println( "\tjava ChartApp -Ttask -Ffile" );
		System.out.println( "\tTasks : compile | fill | print | pdf | xml | xmlEmbed | html | xls | csv | run" );
	}

        public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		
		String driver = "org.hsqldb.jdbcDriver";
		String connectString = "jdbc:hsqldb:hsql://localhost";
		String user = "sa";
		String password = "";       
 
                Connection conn = null;
                return conn;
	}
        
        
        
        public static void main(String[] argv)
        {  		
            new PrintingAppControl().Method("C:\\jasperreports-0.5.2-project\\jasperreports\\demo\\samples\\chart\\ChartReport.jrpxml","view",null,null,true);
        
        }
}
