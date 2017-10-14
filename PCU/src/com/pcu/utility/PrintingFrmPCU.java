/*
 * PrintingFrmPCU.java
 *
 * Created on 7 กุมภาพันธ์ 2549, 16:37 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.utility;
import com.hospital_os.utility.Constant;
import javax.swing.*;
import java.util.*;
import java.io.File;
import java.sql.Connection;
import com.printing.gui.PrintingFrm;
import com.printing.utility.ReadWriteFileConfig;
import com.pcu.utility.ConfigPathPrinting;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.view.JasperViewer;
/**
 * @author kingland
 */
//public class PrintingFrmPCU extends PrintingFrm
public class PrintingFrmPCU 
{    
    
    String path_print = "";
    boolean choosePrinter = false;
    Connection theConnection;
    /** Creates a new instance of PrintingFrmPCU */
    public PrintingFrmPCU(JFrame frm 
            ,int choose, Object o,int preview
            , int convert,JRDataSource datasource) 
    {
//          super(frm, choose, o, preview, convert, datasource);
        Constant.println("PrintingFrmPCU");
         try
         {
            if(checkPathPrint(frm))
                choosePrint(path_print,choose,o,preview,convert,datasource,choosePrinter);
            else
                Constant.println("Print Error");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(frm,e.getMessage());
        }
    }    
    public PrintingFrmPCU(JFrame frm 
            ,int choose, Object o,int preview
            , int convert,JRDataSource datasource
           ,Connection con) 
    {
//        super(frm, choose, o, preview, convert, datasource);
        Constant.println("PrintingFrmPCU");
        theConnection = con;
         try
         {
            if(checkPathPrint(frm))
                choosePrint(path_print,choose,o,preview,convert,datasource,choosePrinter);
            else
                Constant.println("Print Error");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(frm,e.getMessage());
        }
    }
    
    public boolean checkPathPrint(javax.swing.JFrame frm)
    {
        Constant.println("checkPathPrint Start");   
        path_print = ReadWriteFileConfig.ReadFile(".printpath.cfg");
        if(path_print == null)
        {
            Constant.println("if(path_print == null){");
            ConfigPathPrinting.showDialog(frm);
            path_print = ReadWriteFileConfig.ReadFile(".printpath.cfg");
        }
        String[] data = path_print.split(";");
        Constant.println("path_print" + path_print);        
        choosePrinter = false;
        if(data[0].equals("1"))
            choosePrinter = true;
        if(!data[1].equals(""))
            path_print = data[1];
        Constant.println("checkPathPrint END");   
        return true;
    }
     public boolean printReport(String path, String file, Object o,int priview
            ,int convert,JRDataSource datasource,boolean chosePrinter)
            throws Exception
    {
         Constant.println("printReport Start");   
            long start = System.currentTimeMillis();
            File fj = new File(path + "/" + file + ".jasper");
            if(!fj.exists() ||true)
            {
                Constant.println("ไม่พบไฟล์ " + fj.getAbsolutePath());
                File f = new File(path + "/" + file + ".xml");
                if(!f.exists())
                {
                    throw new Exception("ไม่พบไฟล์ " + f.getAbsolutePath());
                }
                Constant.println("Compli................"+path + "/" + file + ".xml");
                JasperCompileManager.compileReportToFile(path + "/" + file + ".xml");
                System.err.println("Compile time : " + (System.currentTimeMillis() - start));
            }
//            if(datasource == null && theConnection != null)
//            {
//                Constant.println("Fill..........connect");
//                JasperFillManager.fillReportToFile(path + "/" + file + ".jasper",(Map)o, theConnection);
//                System.err.println("Filling time : " + (System.currentTimeMillis() - start));
//            }
            if(datasource == null )
            {
                Constant.println("Fill..........");
                JasperFillManager.fillReportToFile(path + "/" + file + ".jasper",(Map)o, new JREmptyDataSource());
                System.err.println("Filling time : " + (System.currentTimeMillis() - start));
            }
            else
            {
                Constant.println("Fill DataSource..........");
                JasperFillManager.fillReportToFile(path + "/" + file + ".jasper",(Map)o, datasource);
                System.err.println("Filling time : " + (System.currentTimeMillis() - start));
            }
            // ทำการ แปลงข้อมูลให่สามารถ view ได้ก่อน
            Constant.println("Convert XML ..........");
            JasperExportManager.exportReportToXmlFile(path + "/" + file + ".jrprint", false);
            System.err.println("XML creation time : " + (System.currentTimeMillis() - start));
            // ต้องการดูข้อมูลก่อนพิมพ์หรือไม่
            if(convert == 0)
            {   
                if(priview == 1)
                {
                    Constant.println("View  ..........");
                    JasperViewer.viewReport(path + "/" + file + ".jrpxml",true,false);
                }
                else
                {   
                    Constant.println("Convert  ..........");
                    JasperPrintManager.printReport(path + "/" + file + ".jrprint", choosePrinter);
                    System.err.println("Printing time : " + (System.currentTimeMillis() - start));
                }
            }
            Constant.println("printReport END"); 
            return true;
    }
     
     public boolean choosePrint(String path, int choose,Object o,int priview
            ,int convert,JRDataSource datasource,boolean choosePrinter)
            throws Exception
    {
        boolean result = false;
        Constant.println("choosePrint Start");   
        String file = new String();
         switch(choose)
        {
            case 1 :    file = "afterMchMother";
                        result = printReport(path + "/pcu", file , o,priview,convert,datasource,choosePrinter);
                        break;
           case 2 :    file = "bornMchMother";
                        result = printReport(path + "/pcu", file , o,priview,convert,datasource,choosePrinter);
                        break; 
           case 3 :    file = "beforeMch";
                        result = printReport(path + "/pcu", file , o,priview,convert,datasource,choosePrinter);
                        break;
           case 4 :    file = "pp";
                        result = printReport(path + "/pcu", file , o,priview,convert,datasource,choosePrinter);
                        break;
            case 5 :    file = "growhistory";
                        result = printReport(path + "/pcu", file , o,priview,convert,datasource,choosePrinter);
                        break;
            case 6 :    file = "epi";
                        result = printReport(path + "/pcu", file , o,priview,convert,datasource,choosePrinter);
                        break;
            default : 
                        break;
         }
         Constant.println("choosePrint END");   
         return result;
        
     }
}
