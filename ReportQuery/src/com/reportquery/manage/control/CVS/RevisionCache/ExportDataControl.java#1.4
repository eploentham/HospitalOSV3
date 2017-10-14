/*
 * ExportDataControl.java
 *
 * Created on 9 กันยายน 2548, 18:18 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportquery.manage.control;
import com.reportcenter.object.ResultRp;
import com.reportquery.objectdb.File18DB;
import com.reportquery.utility.Constant;
import com.reportquery.utility.FileWriter;
import java.util.*;
import java.io.File;
import com.reportquery.manage.subject.ManageSubject;
/**
 *
 * @author tong(Padungrat)
 * @Modify Ojika(Jiraporn)
 */
public class ExportDataControl {
   
    ManageSubject theManageSubject;
    private FileWriter fileWriter;
    private File18DB theFile18DB = new File18DB();

    public ExportDataControl(ManageSubject manageSubject) {
        theManageSubject = manageSubject;
    }
    
    public void startExportByVector(Vector vData,String[] headColumn,int[] len
            ,String pathFile,String typeFile,String fileName)
    {
        if(vData != null)
        {
//            Vector vRR = new Vector();
            String msg ="";           
            String path = pathFile + fileName + "."+typeFile;
            System.out.println("---  Path Save----"+path);
            if(("xls").equalsIgnoreCase(typeFile) || ("csv").equalsIgnoreCase(typeFile))
            {
                System.out.println("----- XLS");
                msg = convertDataToString(vData,true, headColumn);
                if(!("").equals(msg))
                {
                    fileWriter = new FileWriter(path);
                    fileWriter.setPathFile(path);
                    fileWriter.writeData(msg);
                    fileWriter.closeFile();
                }
            }
            else if(("dbf").equalsIgnoreCase(typeFile)){
                try{
                    theFile18DB.setDBFPathFile(path);
                    theFile18DB.insertData(headColumn,len,vData);
                    theFile18DB.closeFile();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            else{
                //text and other file
                System.out.println("----- Text");
                msg = convertDataToString(vData,false,headColumn);
                if(!("").equals(msg))
                {
                    System.out.println("----- Text Writing ----");
                    fileWriter = new FileWriter(path);
                    fileWriter.setPathFile(path);
                    fileWriter.writeData(msg);
                    fileWriter.closeFile();
                }
            }
//            ResultRp rr = new ResultRp();
//            rr.current_file = fileName+ "."+typeFile;
//            rr.records.add(headColumn);
//            vRR.add(rr);
//            String str = ResultRp.parseHtml(vRR, theManageSubject..date_time, PrintShowReportLogControl., dbName);
        }
        else
        {
            System.out.println("---  Not value in SQL");
        }
    } 
    
    public String convertDataToString(Vector v,boolean isGetColumnName,String[] column)
    {   
        if(v != null)
        {
            
            
            return convertToString(v, isGetColumnName, column);
            /*
            if(obReport instanceof RP504Group)
            {
                return theHosDB.theRP504DB.convertToString(v, isGetColumnName,Constant.REPORT_NAME_EN[0]);
            }
            else if(obReport instanceof RP505Group)
            {
                return theHosDB.theRP505DB.convertToString(v, isGetColumnName,Constant.REPORT_NAME_EN[1]); 
            }
            else if(obReport instanceof RP506Group)
            {
                return theHosDB.theRP506DB.convertToString(v, isGetColumnName,Constant.REPORT_NAME_EN[2]);  
            }
            else if(obReport instanceof RP504Name)
            {
                return theHosDB.theRP504DB.convertToString(v, isGetColumnName,Constant.REPORT_NAME_EN[3]); 
            }
            else if(obReport instanceof RP505Name)
            {
                return theHosDB.theRP505DB.convertToString(v, isGetColumnName,Constant.REPORT_NAME_EN[4]); 
            }
            else if(obReport instanceof RP506Name)
            {
                return theHosDB.theRP506DB.convertToString(v, isGetColumnName,Constant.REPORT_NAME_EN[5]); 
            }
            else if(obReport instanceof RP506FollowPateint)
            {
                return theHosDB.theReport506FollowPatientDB.convertToString(v, isGetColumnName); 
            }
            else
            {
                return "";
            }*/
        }
        else
        {
            return "";
        }
    }  
    
    
    /**
     *  ใช้ในการ Convert Object เป็น String โดยที่รับ ค่า true หรือ false เพื่อสร้างหัวคอลัมน์หรือไม่
     *  ถ้าเป็น true จะให้แสดงหัวคอลัมน์ และใช้ Tab เป็นตัวคั่น 
     *  ถ้าเป็น false จะไม่แสดงไม่หัวคอลัมน์ และใช้ Pipe เป็นตัวคั่น
     *  @param vObject เป็น Vector ของ Obeject ชือ RP504Group
     *  @param isGetColumnName เป็น Boolean ถ้าเป็น true จะให้แสดงหัวคอลัมน์ ถ้าเป็น false จะไม่แสดงไม่หัวคอลัมน์ 
     *  @param obj เป็น String ที่ใช้เพื่อตรวจสอบว่า Object ใด ที่ถูกเก็บอยู่ใน Vector 
     *  @return เป็น String ของ Sql ที่มีการแปลงค่าเรียบร้อยแล้ว
     */
    public String convertToString(Vector vObject,boolean isGetColumnName,String[] headColumn)
    {
        System.out.println("In method convert To String  size => "+vObject.size()+" Boolean "+isGetColumnName);
        StringBuffer sql = new StringBuffer();
        String separator = Constant.PIPE;
        
        if(!vObject.isEmpty())
        {
            int size = vObject.size();
            
            /**รับหัว Column*/
            if(isGetColumnName)
            {
                separator = Constant.TAB;
                int length = headColumn.length;
                String datahead = "";
                /**วนลูป หาหัว column */
                for(int i = 0 ; i< length ; i++)
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
                
                sql.append(datahead+Constant.NEWLINE);
                datahead =null;
            }
            
            
            
                String sqlData = "";
                //วนลูป แถว
                for(int i=0 ;i<size; i++)
               {  
                   String[] rowdata = (String[])vObject.get(i);
                   //วนลูป คอลัมน์
                   sqlData = "";
                   for(int j = 0 ; j < rowdata.length ;j++)
                   {
                
                       if(j == (rowdata.length-1))
                       {
                           sqlData = sqlData + rowdata[j];
                       }
                       else
                       {
                            sqlData = sqlData + rowdata[j] +separator;
                       }
                   }
                   sql.append(sqlData + Constant.NEWLINE);
                   rowdata = null;
                }    
                                              
                
            
            
        }
        return sql.toString();
    }
}
