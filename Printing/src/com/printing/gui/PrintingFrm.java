/*
 * PrintingFrm.java
 *
 * Created on 19 ����Ҥ� 2547, 19:13 �.
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
 *  ���� ��Ǥ�¨Ѵ��áѺ �͡��á�þԾ�� �¨���� ��� Control ������µ�� Control ����
 *  ����Ţ ��Т����Ũ��繢����ŷ��١ Map ���� 
 */
public class PrintingFrm {
    
    /** Creates a new instance of PrintingFrm */
    
    /** 
     *  ���� Constuctor �����㹡�� ��˹��ٻẺ��þԾ��
     *  �¨����੾�Т�������� ��ǤǺ���
     *  ��� ��˹���Ҩ� view ������� 
     *  ��Ш� ����ŧ������ٻ xls ,html , csv , pdf
     *  convert = 0 ����ŧ
     *          = 1 pdf
     *          = 2 xls
     *          = 3 csv
     *          = 4 html
     *  priview = 0 ������ʴ� view 
     *          = 1 �ʴ�
     *  frm ������������ö ��˹� �� null ��
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
        //��Ǩ�ͺ�������������������
        if( !f.isDirectory())
        {
            System.out.println("����� Directory ����˹� ");
            
            f = null;
            Path = null;
            return;
        }
        f = null;
        
        ChoosePrint(Path,choose,o,preview,convert,datasource,choosePrinter);
        
        
    }
    /**
     *  ���繡�ù� ��������� Path ����� get �� ���͡��Ҩ��� �ٻẺ��þ����Ẻ�˹
     *  �� path ���Ѻ੾�� path ��� �������������չ��ʡ��
     *  choose ���繵�ǤǺ���
     *  o �� �����ŷ��������
     *  preview �� view �������
     *  convert ������ŧ�� ��쪹Դ�˹
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
     *  ��ǹ�����ʴ�����о���� �¨зӡ�õ�Ǩ�ͺ��͹��� ������������ԧ�������
     *  �ҡ��鹡�зӡ�� �� Path ���� ��� ���ʡ�� ����ѧ ��� control 
     *  ���� �ʴ���
     */
    public boolean PrintReport(String path, String file, Object o,int priview,int convert,JRDataSource datasource,boolean chosePrinter)
    {
            String filepath = new String();
            String checkfilepath = new String();            
            
            // �ӡ�� complie
            checkfilepath = path + ".jasper"; 
            File fj = new File(checkfilepath);
            if(!fj.exists())
            {
                System.out.println("��� " + file + ".jasper" + " ��������� ");
                filepath = path + ".xml";
                File f = new File(filepath);

                if(!f.exists())
                {   System.out.println("��� " + file + ".xml" + " ��������� ");
                    return false;
                }
                JasperPrintApp.ComplieReport(filepath);
            }
            
            filepath = checkfilepath;            
            if(datasource == null)
            {// �ӡ�� fill ������
                System.out.println("Fill..........");
                JasperPrintApp.FillReport(filepath,(Map)o);
            }
            else
            {   System.out.println("Fill DataSource..........");
                //test
                JasperPrintApp.FillCusReport(filepath,(Map)o,datasource);
            }
            filepath = path + ".jrprint";
            // �ӡ�� �ŧ�������������ö view ���͹
            System.out.println("Convert XML ..........");
            JasperPrintApp.XMLReport(filepath);
            // �ӡ�� view
            // ��ͧ��ô٢����š�͹������������
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
     *  �зӡ�� �ŧ�͡�ҵ���ٻẺ Control ����ͧ���
     *  
     *  convert = 0 ����ŧ
     *          = 1 pdf
     *          = 2 xls
     *          = 3 csv
     *          = 4 html    
     *  path �� Path ��� ��� + ���ʡ�� jrprint
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
        pxi.setHospital("�ç��Һ�� ����"); 
        pxi.setName("��ا�Ѱ  �ҡ��"); 
        pxi.setXRayDate("24/09/2547"); 
        pxi.setXn("47000001");   
        
        Map parameters = new HashMap();                                
        parameters.put("name","��ا�Ѱ  �ҡ��");       
        parameters.put("age"," 26 ");
        parameters.put("xn","47000001");   
        parameters.put("xray_date","24/09/2547"); 
        parameters.put("hospital","�ç��Һ�� ����"); 
        
        parameters.put("name","��ا�Ѱ  �ҡ��");       
        parameters.put("age"," 25 ");
        parameters.put("xn","47000001");   
        parameters.put("xray_date","24/09/2547"); 
        parameters.put("hospital","�ç��Һ�� ����");
        PrintingFrm thePrintingFrm = new PrintingFrm(null,1,pxi.getData(),1,0,null);
        thePrintingFrm = null;
        // TODO code application logic here
    }
    
}
