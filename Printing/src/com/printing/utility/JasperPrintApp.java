/*
 * JasperPrint.java
 *
 * Created on 19 พฤษภาคม 2547, 13:55 น.
 */

package com.printing.utility;

import com.printing.control.PrintingAppControl;
import java.util.*;
import net.sf.jasperreports.engine.*;
//import dori.jasper.engine.*;
/**
 *
 * @author  tong
 *  ไฟล์ที่จะนำไปใช้ได้
 *  xml     ->  jasper
 *  jasper  -> jrprint
 *  jrprint -> jrpxml , csv , xls , pdf
 *  jrpxml
 */
public class JasperPrintApp {
    
    /** Creates a new instance of JasperPrint */
   
    public JasperPrintApp() {
        
    }
    
        /**
         *  ทำการ Complie file xml ให้เป็น jasper
         *  ข้อมูลเข้า จะเป็น ชื่อไฟล์.xml และ fullpath
         */
        public static boolean ComplieReport(String filename )
        {   
               return new com.printing.control.PrintingAppControl().Method(filename,"compile",null,null,false);
        }
        /**
         *  ทำการ Fill file jasper ให้เป็น jrprint
         *  ข้อมูลเข้า จะเป็น ชื่อไฟล์.jasper และ fullpath
         */
        public static boolean FillReport(String filename,Map parameters)
        {
             return new com.printing.control.PrintingAppControl().Method(filename,"fill",parameters,null,false);
        }
        
        /**
         *  ทำการ Fill file jasper ให้เป็น jrprint
         *  ข้อมูลเข้า จะเป็น ชื่อไฟล์.jasper และ fullpath
         */
        public static boolean FillCusReport(String filename,Map parameters,JRDataSource datasource)
        {
             return new com.printing.control.PrintingAppControl().Method(filename,"fill_cus",parameters,datasource,false);
        }
         /**
         *  ทำการ พิมพ์ ข้อมูลออกทาง Printing โดยจะปรากฏ Dialog Printing ให้เลือก
         *
         *  ข้อมูลเข้า จะเป็น ชื่อไฟล์.jrprint และ fullpath
         */
        public static boolean PrintReport(String filename,boolean choosePrinter)
        {
             return new com.printing.control.PrintingAppControl().Method(filename,"print",null,null,choosePrinter);      
        }
         /**
         *  ทำการ พิมพ์ ข้อมูลออกเป็น File PDF 
         *
         *  ข้อมูลเข้า จะเป็น ชื่อไฟล์.jrprint และ fullpath
         */
        public static boolean PDFReport(String filename)
        {
             return new com.printing.control.PrintingAppControl().Method(filename,"pdf",null,null,false);        
        }
         /**
         *  ทำการ แสดงข้อมูลออกมา แบบ jrpxml 
         *
         *  ข้อมูลเข้า จะเป็น ชื่อไฟล์.jrprint และ fullpath
         */
        public static boolean XMLReport(String filename)
        {
             return new com.printing.control.PrintingAppControl().Method(filename,"xml",null,null,false);        
        }
         /**
         *  ทำการ แสดงข้อมูลออกมา แบบ jrpxml
         *
         *  ข้อมูลเข้า จะเป็น ชื่อไฟล์.jrprint และ fullpath
         */
        public static boolean XMLEnbedReport(String filename)
        {
             return new com.printing.control.PrintingAppControl().Method(filename,"xmlEmbed",null,null,false);        
        }
         /**
         *  ทำการ พิมพ์ ออกมาเป็นแบบ HTML
         *
         *  ข้อมูลเข้า จะเป็น ชื่อไฟล์.jrprint และ fullpath
         */
        public static boolean HTMLReport(String filename)
        {
             return new com.printing.control.PrintingAppControl().Method(filename,"html",null,null,false);        
        }
        /**
         *  ทำการ พิมพ์ ออกมาเป็นแบบ XLS
         *
         *  ข้อมูลเข้า จะเป็น ชื่อไฟล์.jrprint และ fullpath
         */
        public static boolean XLSReport(String filename)
        {
             return new com.printing.control.PrintingAppControl().Method(filename,"xls",null,null,false);
        }
        /**
         *  ทำการ พิมพ์ ออกมาเป็นแบบ CSV
         *
         *  ข้อมูลเข้า จะเป็น ชื่อไฟล์.jrprint และ fullpath
         */
        public static boolean CSVReport(String filename)
        {
              return new com.printing.control.PrintingAppControl().Method(filename,"csv",null,null,false);       
        }
        /**
         *  ทำการ แสดงข้อมูลก่อนพิมพ์ 
         *
         *  ข้อมูลเข้า จะเป็น ชื่อไฟล์.jrpxml และ fullpath
         */
        public static boolean ViewReport(String filename)
        {
             return new com.printing.control.PrintingAppControl().Method(filename,"view",null,null,false);        
        }
        /**
         *  ทำการ 
         *
         *  ข้อมูลเข้า จะเป็น ชื่อไฟล์.XML และ fullpath
         */
        public static boolean runReport(String filename, Map parameters,JRDataSource datasource)
        {
              return new com.printing.control.PrintingAppControl().Method(filename,"run",parameters,datasource,false);       
        }
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
    
        
        Map parameters = new HashMap();                                
                                parameters.put("name","ผดุงรัฐ  มากชู");       
                                parameters.put("age"," 26 ");
                                parameters.put("xn","47000001");   
                                parameters.put("xray_date","24/09/2547"); 
                                parameters.put("hospital","โรงพยาบาล ภูเก็ต"); 
        String Path = new String();
        Path = ReadWriteFileConfig.ReadFile(".printpath.cfg");
        Path  = Path;// + "/x_ray_card.xml";
        java.io.File f = new java.io.File(Path);
        
        if( !f.isDirectory())//!f.exists())
        {
            System.out.println("ไม่มีไฟล์ อยู่ ");
            f = null;
            Path = null;
            return;
        }
        Path  = Path + "/x_ray_card.xml";
        
        System.out.println(ComplieReport(Path));
        
        System.out.println(FillReport("h:/printing/printing/x_ray_card.jasper",parameters));
       // System.out.println(PrintReport("h:\\printing\\printing\\ChartReport.jrprint"));
        System.out.println(XMLReport("h:/printing/printing/x_ray_card.jrprint"));
      //    System.out.println(XMLEnbedReport("h:\\printing\\printing\\ChartReport.jrprint"));
         
    //     System.out.println(XLSReport("h:\\printing\\printing\\ChartReport.jrprint"));
     //     System.out.println(CSVReport("h:\\printing\\printing\\ChartReport.jrprint"));
     //     System.out.println(HTMLReport("h:\\printing\\printing\\ChartReport.jrprint"));
          System.out.println(ViewReport("h:/printing/printing/x_ray_card.jrpxml"));
    
    }
    
}
