/*
 * IpdDB.java
 *
 * Created on 1 สิงหาคม 2548, 10:19 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.objdb;
import com.hospital_os.utility.IOStream;
import com.reportcenter.utility.StringDate;
import com.reportcenter.utility.Util;
import java.sql.*;
import java.util.Vector;
import com.report12file.object.Ipd;
//import com.report18file.usecase.connection.ConnectionInf;

import com.report12file.utility.*;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.utility.ConnectionDBMgr;

import com.linuxense.javadbf.*;


import java.io.*;
/**
 *
 * @author tong(Padungrat)
 */
public class IpdDB  implements File12DB{
    public ConnectionInf theConnectionInf;
    public Ipd dbObj;
    public DBFWriter writer;
    public FileOutputStream fileOutput;
    public DBFField fields[];
    int exportFor;
    
    
    /** Creates a new instance of IpdDB */
    public IpdDB() {
    }
    
    public IpdDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new Ipd();
    }
    
    
    /**ใช้ในการ query ข้อมูลจากฐานข้อมูล และเก็บข้อมูลลง Vector 
     *  ของ Ipd 
     * @param sql เป็น String ของ sql
     * @return Vector ของ Object Ipd
     */
    public Vector selectByDate(String startDate, String endDate,int exp) throws Exception {
        
        //String sql = get12file();
        exportFor = exp;
        String sql = IOStream.readInputDefault("config/rp_12file/12file_ipd.sql");
        PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        pm.setString(3,startDate);
        pm.setString(4,endDate);
        return eQuery(pm.executeQuery());
    }

    public java.util.Vector eQuery(String sql) throws Exception 
    {
        ResultSet rs = theConnectionInf.eQuery(sql);
        return eQuery(rs);
    }
    public java.util.Vector eQuery(ResultSet rs) throws Exception 
    {
        Vector vc = new Vector();
        while(rs.next()) {
            Ipd theipd = new Ipd();
            theipd.hn = rs.getString(1);
            theipd.an = rs.getString(2);
            theipd.dateadm = rs.getString(3);
            theipd.timeadm = rs.getString(4);
            theipd.datedsc = rs.getString(5);
            theipd.timedsc = rs.getString(6);
            theipd.dischs = rs.getString(7);
            theipd.discht = rs.getString(8);
            theipd.warddsc = rs.getString(9);
            theipd.dept = rs.getString(10);
            theipd.adm_w = rs.getString(11);
            try{
                theipd.adm_w = String.valueOf(Double.parseDouble(theipd.adm_w)*1000);
            }
            catch(Exception e){
                System.out.println("public java.util.Vector eQuery(ResultSet rs) throws Exception  " + e.getMessage());
            }
            vc.add(theipd);
            theipd = null;
        }
        rs.close();
        return vc;
    }
    
    
    /***/
    public String convertToString(Vector vObject,boolean isGetColumnName) {
        System.out.println("In method convert To String  size => "+vObject.size()+" Boolean "+isGetColumnName);
        StringBuffer sql = new StringBuffer();
        String separator = Constant.PIPE;
        if(vObject != null) {
            Ipd p = null;
            if(isGetColumnName){
                separator = Constant.TAB;
                sql.append("hn" +separator
                            + "an" +separator
                            + "dateadm" +separator
                            + "timeadm" +separator
                            + "datedsc" +separator
                            + "timedsc" +separator
                            + "dischs" +separator
                            + "discht" +separator
                            + "warddsc" +separator
                            + "dept" +separator
                            + "adm_w"
                        +Constant.NEWLINE);
            }
            for(int i=0;i<vObject.size();i++) {
                p = (Ipd)vObject.elementAt(i);
                sql.append(p.hn +separator
                            + p.an +separator
                            + p.dateadm +separator
                            + p.timeadm +separator
                            + p.datedsc +separator
                            + p.timedsc +separator
                            + p.dischs +separator
                            + p.discht +separator
                            + p.warddsc +separator
                            + p.dept +separator
                            + p.adm_w
                        +Constant.NEWLINE);
            }
        }
        return sql.toString();
    }
    
    /**ใช้ในการสร้างตารางของ Fox*/
    public void createTable() throws Exception{
        if(writer == null)
        {
        fields = new DBFField[11];
        
        fields[0] = new DBFField();
        fields[0].setName( "hn");
        fields[0].setDataType( DBFField.FIELD_TYPE_C);
        fields[0].setFieldLength(9);
        
        fields[1] = new DBFField();
        fields[1].setName( "an");
        fields[1].setDataType( DBFField.FIELD_TYPE_C);
        fields[1].setFieldLength(9);
        
        fields[2] = new DBFField();
        fields[2].setName( "dateadm");
        fields[2].setDataType( DBFField.FIELD_TYPE_D);
        //fields[2].setFieldLength(1);
        
        fields[3] = new DBFField();
        fields[3].setName( "timeadm");
        fields[3].setDataType( DBFField.FIELD_TYPE_C);
        fields[3].setFieldLength(4);
        
        fields[4] = new DBFField();
        fields[4].setName( "datedsc");
        fields[4].setDataType( DBFField.FIELD_TYPE_D);
        //fields[4].setFieldLength(4);
        
        fields[5] = new DBFField();
        fields[5].setName( "timedsc");
        fields[5].setDataType( DBFField.FIELD_TYPE_C);
        fields[5].setFieldLength(4);
        
        fields[6] = new DBFField();
        fields[6].setName( "dischs");
        fields[6].setDataType( DBFField.FIELD_TYPE_C);
        fields[6].setFieldLength(1);
        
        fields[7] = new DBFField();
        fields[7].setName( "discht");
        fields[7].setDataType( DBFField.FIELD_TYPE_C);
        fields[7].setFieldLength(1);
        
        fields[8] = new DBFField();
        fields[8].setName( "warddsc");
        fields[8].setDataType( DBFField.FIELD_TYPE_C);
        fields[8].setFieldLength(4);

        fields[9] = new DBFField();
        fields[9].setName( "dept");
        fields[9].setDataType( DBFField.FIELD_TYPE_C);
        fields[9].setFieldLength(2);
        
        fields[10] = new DBFField();
        fields[10].setName( "adm_w");
        //fields[10].setDataType( DBFField.FIELD_TYPE_C);
        //fields[10].setFieldLength(7);
        fields[10].setDataType( DBFField.FIELD_TYPE_N);
        fields[10].setFieldLength(7);
        fields[10].setDecimalCount(3);
        
       
        
        
        
            writer = new DBFWriter();
            writer.setCharactersetName(Constant.ENCODE_TH);
            writer.setFields( fields);
        }
        
    }
    
    /**ทำการ Insert ข้อมูลลงฐานข้อมูลของ Fox
     * @param vObject เป็น Vector ของ Ipd
     */
    public void insertData(Vector vObject) throws Exception
    {
        createTable();
        if(vObject != null) {
            Ipd p = null;
            Object rowData[];
            if(writer == null){
                writer = new DBFWriter();
            }
            
            for(int i=0;i<vObject.size();i++) 
            {
                p = (Ipd)vObject.elementAt(i);
                rowData = new Object[11];
                rowData[0] = p.hn;
                rowData[1] = p.an;
                if(!("").equals(p.dateadm))
                    rowData[2] = StringDate.StringDateToDate(p.dateadm);
                else
                    rowData[2] = null;
                
                 p.timeadm = p.timeadm.replaceAll(":","");
                rowData[3] = p.timeadm;
                if(!("").equals(p.datedsc))
                    rowData[4] = StringDate.StringDateToDate(p.datedsc);
                else
                    rowData[4] = null;
                
                 p.timedsc = p.timedsc.replaceAll(":","");
                rowData[5] = p.timedsc;
                rowData[6] = p.dischs;
                rowData[7] = p.discht;
                rowData[8] = p.warddsc;
                rowData[9] = p.dept;
                
                if(!("").equals(p.adm_w))
                    rowData[10] = new Double(p.adm_w);
                else
                    rowData[10] = null;
                
                writer.addRecord(rowData);
            }
            writer.write(fileOutput);
        }
        writer = null;
    }
    
    /**ใช้ในการสร้างชื่อไฟล์ที่กำหนด
     *@param path เป็น String ของ path
     */
    public void setDBFPathFile(String path) throws FileNotFoundException{
        System.out.println("Path is = "+path);
        fileOutput = new FileOutputStream(path);
        
    }
    
    /**
     * ใช้ในการปิดไฟล์ที่ได้เปิดไว้
     */
    public void closeFile() throws Exception{
        if(fileOutput != null){
            fileOutput.close();
            System.out.println("---------------Close file OK");
        }
        
    }
    
    public Vector convertData(Vector vData) throws Exception {
        
        return vData;        
    }

    public String getFileName() {
        return "IPD";
    }
    
}
