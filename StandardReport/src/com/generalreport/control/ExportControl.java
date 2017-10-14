/*
 * ExportControl.java
 *
 * Created on 7 กันยายน 2548, 15:58 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import java.util.*;
import java.io.File;
import com.generalreport.utility.FileWriter;
import com.generalreport.objdb.HosDB;
import com.generalreport.object.*;
import com.generalreport.utility.Constant;

/**
 *
 * @author nu_ojika
 */
public class ExportControl { 
    
    /** Creates a new instance of ExportControl */
    private Vector vc = null;
    private FileWriter fileWriter;
    Vector vcDataQuery;
    String[] headColumn;
    String[] dataColumn;
    
    public ExportControl()
    {
    }
    /*
     *
     */
    public void startExportByVector(Vector vData,String pathFile,String typeFile,String fileName)
    {
        if(vData != null)
        {
            String msg ="";
            String path = pathFile + fileName + "."+typeFile;
            System.out.println("---  Path Save----"+path);
            path = path.replace('\\','/');
            if(("xls").equalsIgnoreCase(typeFile) || ("csv").equalsIgnoreCase(typeFile))
            {
                System.out.println("----- XLS");
                try
                {
                    msg = convertToString(vData,true);
                    
                    if(!("").equals(msg))
                    {
                        fileWriter = new FileWriter(path);
                        fileWriter.setPathFile(path);
                        fileWriter.writeData(msg);
                        fileWriter.closeFile();
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            else
            {
                //text and other file
                System.out.println("----- Text");
                try
                {
                    msg = convertToString(vData,true);
                    if(!("").equals(msg))
                    {
                        System.out.println("----- Text Writing ----");
                        fileWriter = new FileWriter(path);
                        fileWriter.setPathFile(path);
                        fileWriter.writeData(msg);
                        fileWriter.closeFile();
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
                
            }
        }
        else
        {
            System.out.println("---  Not value in person");
        }
    }
   /**
     *  ใช้ในการ Convert Object เป็น String โดยที่รับ ค่า true หรือ false เพื่อสร้างหัวคอลัมน์หรือไม่
     *  ถ้าเป็น true จะให้แสดงหัวคอลัมน์ และใช้ Tab เป็นตัวคั่น 
     *  ถ้าเป็น false จะไม่แสดงไม่หัวคอลัมน์ และใช้ Pipe เป็นตัวคั่น
     *  @param vData เป็นข้อมูลที่ได้จากการ query 
     *  @param isGetColumnName เป็น Boolean ถ้าเป็น true จะให้แสดงหัวคอลัมน์ ถ้าเป็น false จะไม่แสดงไม่หัวคอลัมน์ 
     *  @return เป็น String ของ Sql ที่มีการแปลงค่าเรียบร้อยแล้ว
     */
    public String convertToString(Vector vData,boolean isGetColumnName)  throws Exception
    {
        StringBuffer sql = new StringBuffer();
        String separator = Constant.PIPE;
        headColumn = new String[] {""};
        //dataColumn = new String[] {""};
        vcDataQuery = null;
        int size =0;
        if(!vData.isEmpty())
        {
            headColumn = (String[])vData.get(0);
            vcDataQuery = (Vector)vData.get(1);
            size = vcDataQuery.size();
            System.out.println("--- vcDataQuery : " + vcDataQuery.size());
            
            if(isGetColumnName)
            {
                String datahead = "";
                int length = headColumn.length;
                separator = Constant.TAB;
                for(int i=0;i<length;i++)
                {
                    if(i == (length-1))
                    {
                        datahead = datahead + headColumn[i];
                        System.out.println("--- headColumn[i] : " + headColumn[i]);
                    }
                    else
                    {
                        datahead = datahead + headColumn[i] + separator;
                        System.out.println("--- headColumn[i] : " + headColumn[i]);
                    }
                }
                sql.append(datahead + Constant.NEWLINE);
                datahead = null;
            }
            
            String data = "";
            for(int i=0;i<size;i++)
            {
                String[] dataColumn = (String[])vcDataQuery.get(i);
//                System.out.println("--- dataColumn : " + dataColumn.length);
                int length = dataColumn.length;
                data = "";
                    for(int j=0;j<length;j++)
                    {
                        if(j == (length-1))
                        {
                            data = data + dataColumn[j];
//                            System.out.println("---if-- dataColumn[j] : " + dataColumn[j]);
                        }
                        else
                        {
                            data = data + dataColumn[j] + separator;
//                            System.out.println("---else-- dataColumn[j] : " + dataColumn[j]);
                        }
                    }
                    sql.append(data + Constant.NEWLINE);                    
            }
            data = null;
        }
         return sql.toString();   
    }
    
    /*
     *author : amp
     */
    public void startExportByVectorOfObject(Vector vData,String pathFile,String typeFile,String fileName)
    {
        if(vData != null)
        {
            String msg ="";           
            String path = pathFile + fileName + "."+typeFile;
            if(("xls").equalsIgnoreCase(typeFile) || ("cvs").equalsIgnoreCase(typeFile))
            {
                try
                {
                    msg = convertToStringObject(vData,true);
                    if(!("").equals(msg))
                    {
                        fileWriter = new FileWriter(path);
                        fileWriter.setPathFile(path);
                        fileWriter.writeData(msg);
                        fileWriter.closeFile();
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            else
            {
                try
                {
                    msg = convertToStringObject(vData,true);
                    if(!("").equals(msg))
                    {
                        fileWriter = new FileWriter(path);
                        fileWriter.setPathFile(path);
                        fileWriter.writeData(msg);
                        fileWriter.closeFile();
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        }
        else
        {
            System.out.println("---  Not value  ---");
        }
    } 
    
    /**
     **author : amp
     *
     *  ใช้ในการ Convert Object เป็น String โดยที่รับ ค่า true หรือ false เพื่อสร้างหัวคอลัมน์หรือไม่
     *  ถ้าเป็น true จะให้แสดงหัวคอลัมน์ และใช้ Tab เป็นตัวคั่น 
     *  ถ้าเป็น false จะไม่แสดงไม่หัวคอลัมน์ และใช้ Pipe เป็นตัวคั่น
     *  @param vData เป็นข้อมูลที่ได้จากการ query 
     *  @param isGetColumnName เป็น Boolean ถ้าเป็น true จะให้แสดงหัวคอลัมน์ ถ้าเป็น false จะไม่แสดงไม่หัวคอลัมน์ 
     *  @return เป็น String ของ Sql ที่มีการแปลงค่าเรียบร้อยแล้ว
     */
    public String convertToStringObject(Vector vData,boolean isGetColumnName)  throws Exception
    {
        StringBuffer sql = new StringBuffer();
        String separator = Constant.PIPE;
        headColumn = new String[] {""};
        vcDataQuery = null;
        int size =0;
        if(vData != null)
        {
            headColumn = (String[])vData.get(0);
            vcDataQuery = (Vector)vData.get(1);
            size = vcDataQuery.size();
            
            if(isGetColumnName)
            {
                String datahead = "";
                int length = headColumn.length;
                separator = Constant.TAB;
                for(int i=0;i<length;i++)
                {
                    if(i == (length-1))
                    {
                        datahead = datahead + headColumn[i];
                    }
                    else
                    {
                        datahead = datahead + headColumn[i] + separator;
                    }
                }
                sql.append(datahead + Constant.NEWLINE);
                datahead = null;
            }
            
            String data = "";
            for(int i=0;i<size;i++)
            {
                CashInFlow theCashInFlow = (CashInFlow)vcDataQuery.get(i);
                data = "";
                data = data + theCashInFlow.patient_hn + separator;
                data = data + theCashInFlow.visit_vn + separator;
                data = data + theCashInFlow.patient_prefix_description + separator;
                data = data + theCashInFlow.patient_firstname + separator;
                data = data + theCashInFlow.patient_lastname + separator;
                data = data + theCashInFlow.contract_plans_description + separator;
                data = data + theCashInFlow.date + separator;
                data = data + theCashInFlow.paid;
                sql.append(data + Constant.NEWLINE);                    
            }
            data = null;
        }
         return sql.toString();   
    }
    public static void main(String[] argc){
        String path = "c:\\test\\test/henbe";
         path = path.replace('\\','/');
        System.out.println(path);
    }
}