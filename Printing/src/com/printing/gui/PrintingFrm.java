/*
 * PrintingFrm.java
 *
 * Created on 19 พฤษภาคม 2547, 19:13 น.
 */

package com.printing.gui;
import com.printing.utility.*;
import java.io.*;
import java.util.*;
//import com.printing.object.*;
import net.sf.jasperreports.engine.*;
//import dori.jasper.engine.*;
/**
 *
 * @author  tong
 *  จะเป็น ตัวคอยจัดการกับ เอกสารการพิพม์ โดยจะใส่ ตัว Control เข้ามาโดยตัว Control จะเป็น
 *  ตัวเลข และข้อมูลจะเป็นข้อมูลที่ถูก Map แล้ว 
 */
public class PrintingFrm {
    
    /** Creates a new instance of PrintingFrm */
    
    /** 
     *  จะเป็น Constuctor ที่ใช้ในการ กำหนดรูปแบบการพิพม์
     *  โดยจะใส่เฉพาะข้อมูลและ ตัวควบคุม
     *  และ กำหนดว่าจะ view หรือไม่ 
     *  และจะ ให้แปลงอยู่ในรูป xls ,html , csv , pdf
     *  convert = 0 ไม่แปลง
     *          = 1 pdf
     *          = 2 xls
     *          = 3 csv
     *          = 4 html
     *  priview = 0 จะไม่แสดง view 
     *          = 1 แสดง
     *  frm ถ้าไม่มีสามารถ กำหนด เป็น null ได้
     */
    public PrintingFrm(javax.swing.JFrame frm ,int choose, Object o,int preview, int convert,JRDataSource datasource) {
     //   objData = o;
   //     choosePrint = choose;
        
        String Path = new String();
        String[] data ;
        boolean  choosePrinter = false;
        Path = ReadWriteFileConfig.ReadFile(".printpath.cfg");
      
        if(Path == null)//Path.trim().length() == 0)
        {
            ConfigPathPrinting.showDialog(frm);
            
            Path = ReadWriteFileConfig.ReadFile(".printpath.cfg");
            
            
            
        }
        
        data = Path.split(";");
        if(data.length == 2)
        {   
           Path= data[1];
           if(data[0].equalsIgnoreCase("1"))
            choosePrinter = true;
                
        }
        
        
        data = null;
        java.io.File f = new java.io.File(Path);
        //ตรวจสอบว่ามีไฟล์อยู่หรือไม่
        if( !f.isDirectory())
        {
            System.out.println("ไม่มี Directory ที่กำหนด ");
            
            f = null;
            Path = null;
            return;
        }
        f = null;
        
        ChoosePrint(Path,choose,o,preview,convert,datasource,choosePrinter);
        
        
    }
    /**
     *  จะเป็นการนำ ข้อมูลและ Path ที่ได้ get มา เลือกว่าจะใช้ รูปแบบการพิมพ์แบบไหน
     *  โดย path จะรับเฉพาะ path และ ชื่อไฟล์ที่ไม่มีนามสกุล
     *  choose จะเป็นตัวควบคุม
     *  o เป็น ข้อมูลที่จะส่งให้
     *  preview จะ view หรือไม่
     *  convert จะให้แปลงเป็น ไฟล์ชนิดไหน
     */
    public boolean ChoosePrint(String path, int choose,Object o,int priview,int convert,JRDataSource datasource,boolean choosePrinter)
    {   boolean result = false;
        String file = new String();
        switch(choose)
        {
            case 1 :    file = "x_ray_card";
                        path = path + file;
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        break;
            case 2 :    file = "Report_item";
                        path = path + file;                        
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        
                        break;
            case 3 :    file = "Drug_Sticker";
                        path = path + file;
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        
                        
                        break;
            case 4 :    file = "appointment";
                        path = path + file;                     
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        
                        
                        break;
            case 5 :    file = "appointmentList";
                        path = path + file;                     
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        
                        break;
            case 6 :    file = "refer";
                        path = path + file;                     
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        
                        break;
            case 7 :    file = "index";
                        path = path + file; 
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        
                        break;
            case 8 :    file = "visitslipNew";
                        path = path + file; 
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        
                        break;
            case 9 :    file = "receipt";
                        path = path + file;
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        
                        break;
            case 10 :    file = "resultLab";
                        path = path + file;
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        
                        break;
            case 11 :    file = "drugRx";
                        path = path + file;
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        
                        break;
            case 12 :    file = "Report_Order";
                        path = path + file;
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        
                        break;
            case 13 :   file = "OPD_Card"; //file = "OPD_Head";
                        path = path + file;
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        
                        break;
            case 14 :    file = "drugFund_receipt";
                        path = path + file;
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        
                        break;
                        
            case 15 :   file = "chronic_report";
                        path = path + file;
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        
                        break;
                        
            case 16 :    file = "Billing_Report";
                        path = path + file;
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        
                        break;
            case 17 :    file = "summary";
                        path = path + file;
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        
                        break;
            case 18 :    file = "surveil_report";
                        path = path + file;
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        
                        break;
            case 19 :    file = "ipd_sticker";
                        path = path + file;
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        
                        break;
            case 20 :    file = "ipd_card";
                        path = path + file;
                        
                        result = PrintReport(path, file , o,priview,convert,datasource,choosePrinter);
                        
                        break;
            default : 
                        
                        break;
        }
    
        return result;
    }
    
    /**
     *  ส่วนที่จะแสดงผลและพิมพ์ โดยจะทำการตรวจสอบก่อนว่า ไฟล์นี้มีอยู่จริงหรือไม่
     *  จากนั้นก็จะทำการ ส่ง Path ชื่อ และ นามสกุล ส่งไปยัง ตัว control 
     *  เพื่อ แสดงผล
     */
    public boolean PrintReport(String path, String file, Object o,int priview,int convert,JRDataSource datasource,boolean chosePrinter)
    {
            String filepath = new String();
            String checkfilepath = new String();            
            
            // ทำการ complie
            checkfilepath = path + ".jasper"; 
            File fj = new File(checkfilepath);
            if(!fj.exists())
            {
                System.out.println("ไฟล์ " + file + ".jasper" + " ไม่มีอยู่ ");
                filepath = path + ".xml";
                File f = new File(filepath);

                if(!f.exists())
                {   System.out.println("ไฟล์ " + file + ".xml" + " ไม่มีอยู่ ");
                    return false;
                }
                JasperPrintApp.ComplieReport(filepath);
            }
            
            filepath = checkfilepath;            
            if(datasource == null)
            {// ทำการ fill ข้อมูล
                System.out.println("Fill..........");
                JasperPrintApp.FillReport(filepath,(Map)o);
            }
            else
            {   System.out.println("Fill DataSource..........");
                //test
                JasperPrintApp.FillCusReport(filepath,(Map)o,datasource);
            }
            filepath = path + ".jrprint";
            // ทำการ แปลงข้อมูลให่สามารถ view ได้ก่อน
            System.out.println("Convert XML ..........");
            JasperPrintApp.XMLReport(filepath);
            // ทำการ view
            // ต้องการดูข้อมูลก่อนพิมพ์หรือไม่
            if(convert == 0)
            {   
                if(priview == 1)
                {   System.out.println("View  ..........");
                    filepath = path + ".jrpxml";
                    JasperPrintApp.ViewReport(filepath);
                }
                else
                {   System.out.println("Convert  ..........");
                    filepath = path + ".jrprint";
                    JasperPrintApp.PrintReport(filepath,chosePrinter);
                }
            }
            else
            {
                filepath = path + ".jrprint";
                PrintReportConvert(filepath,convert);
            }
            
            return true;
    }
    
    /**
     *  จะทำการ แปลงออกมาตามรูปแบบ Control ที่ต้องการ
     *  
     *  convert = 0 ไม่แปลง
     *          = 1 pdf
     *          = 2 xls
     *          = 3 csv
     *          = 4 html    
     *  path เป็น Path และ ไฟล์ + นามสกุล jrprint
     */
    public boolean PrintReportConvert(String filepath,int convert)
    {   boolean result = true;
        switch(convert)
        {
            case 1 :  
                      JasperPrintApp.PDFReport(filepath);
                      break;
            case 2 :  
                      JasperPrintApp.XLSReport(filepath);
                      break;
            case 3 :  
                      JasperPrintApp.CSVReport(filepath);
                      break;
            case 4 :  
                      JasperPrintApp.HTMLReport(filepath);
                      break;
            default :   result = false;
                break;
        
        }
        return result;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        com.printing.object.Xray.PrintXrayIndex pxi = new com.printing.object.Xray.PrintXrayIndex();
        pxi.setAge(" 26 ");
        pxi.setHospital("โรงพยาบาล ภูเก็ต"); 
        pxi.setName("ผดุงรัฐ  มากชู"); 
        pxi.setXRayDate("24/09/2547"); 
        pxi.setXn("47000001");   
        
        Map parameters = new HashMap();                                
        parameters.put("name","ผดุงรัฐ  มากชู");       
        parameters.put("age"," 26 ");
        parameters.put("xn","47000001");   
        parameters.put("xray_date","24/09/2547"); 
        parameters.put("hospital","โรงพยาบาล ภูเก็ต"); 
        
        parameters.put("name","ผดุงรัฐ  มากชู");       
        parameters.put("age"," 25 ");
        parameters.put("xn","47000001");   
        parameters.put("xray_date","24/09/2547"); 
        parameters.put("hospital","โรงพยาบาล ภูเก็ต");
        PrintingFrm thePrintingFrm = new PrintingFrm(null,1,pxi.getData(),1,0,null);
        thePrintingFrm = null;
        // TODO code application logic here
    }
    
}
