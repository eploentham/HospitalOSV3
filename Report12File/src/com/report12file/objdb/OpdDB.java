/*
 * OpdDB.java
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
import com.report12file.object.Opd;
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
public class OpdDB implements File12DB {
    public ConnectionInf theConnectionInf;
    public Opd dbObj;
    public DBFWriter writer;
    public FileOutputStream fileOutput;
    public DBFField fields[];
    
    private String sql;
    private Opd theopd,theOpdTemp;
    private Vector vc;
    private ResultSet rs;
    
    private int exp;
    private StringBuffer sqlStringBuffer;
    
    /** Creates a new instance of OpdDB */
    public OpdDB() {
    }
    
    public OpdDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new Opd();
    }
    
    
    public void clearObject() {
        
        writer = null;
        fileOutput = null;
        fields =null;
        
        sql = null;
        theopd = null;
        theOpdTemp = null;
        vc = null;
        rs = null;
        sqlStringBuffer = null;
    }
    /**ใช้ในการ query ข้อมูลจากฐานข้อมูล และเก็บข้อมูลลง Vector
     *  ของ Opd
     * @param sql เป็น String ของ sql
     * @return Vector ของ Object Opd
     */
    
    public java.util.Vector eQuery(String sql) throws Exception {
        ResultSet rs = theConnectionInf.eQuery(sql);
        return eQuery(rs);
    }
    public java.util.Vector eQuery(ResultSet rs) throws Exception {
        Vector vc = new Vector();
        while(rs.next()) {
            Opd theopd = new Opd();
            theopd.hn = rs.getString(1);
            theopd.clinic = rs.getString(2);
            theopd.dateopd = rs.getString(3);
            
            if(exp==1)
                theopd.pid = rs.getString(4);
            vc.add(theopd);
            theopd = null;
        }
        rs.close();
        return vc;
    }
    
    
    /***/
    public String convertToString(Vector vObject,boolean isGetColumnName) {
        System.out.println("In method convert To String  size => "+vObject.size()+" Boolean "+isGetColumnName);
        sqlStringBuffer = new StringBuffer();
        String separator = Constant.PIPE;
        if(vObject != null) {
            theOpdTemp = null;
            if(isGetColumnName){
                separator = Constant.TAB;
                sqlStringBuffer.append("hn" +separator
                        + "clinic" +separator
                        + "dateopd");
                if(exp==1)
                    sqlStringBuffer.append(separator+"PERSON_ID");
                sqlStringBuffer.append(Constant.NEWLINE);
            }
            for(int i=0;i<vObject.size();i++) {
                theOpdTemp = (Opd)vObject.elementAt(i);
                sqlStringBuffer.append(theOpdTemp.hn +separator
                        + theOpdTemp.clinic +separator
                        + theOpdTemp.dateopd);
                if(exp==1)
                    sqlStringBuffer.append(separator+theOpdTemp.pid);
                sqlStringBuffer.append(Constant.NEWLINE);
            }
        }
        separator = null;
        return sqlStringBuffer.toString();
    }
    
    /**ใช้ในการสร้างตารางของ Fox*/
    public void createTable() throws Exception{
        if(writer == null){
            
            fields = new DBFField[3];
            if(exp==1)
                fields = new DBFField[4];
            
            fields[0] = new DBFField();
            fields[0].setName( "hn");
            fields[0].setDataType( DBFField.FIELD_TYPE_C);
            fields[0].setFieldLength(9);
            
            fields[1] = new DBFField();
            fields[1].setName( "clinic");
            fields[1].setDataType( DBFField.FIELD_TYPE_C);
            fields[1].setFieldLength(4);
            
            fields[2] = new DBFField();
            fields[2].setName( "dateopd");
            fields[2].setDataType( DBFField.FIELD_TYPE_D);
            // fields[2].setFieldLength(1);
            
            if(exp==1){
                fields[3] = new DBFField();
                fields[3].setName( "PERSON_ID");
                fields[3].setDataType( DBFField.FIELD_TYPE_C);
                fields[3].setFieldLength(13);
            }
            
            
            
            
            writer = new DBFWriter();
            writer.setCharactersetName(Constant.ENCODE_TH);
            writer.setFields( fields);
        }
        
    }
    
    /**ทำการ Insert ข้อมูลลงฐานข้อมูลของ Fox
     * @param vObject เป็น Vector ของ Opd
     */
    public void insertData(Vector vObject) throws Exception{
        createTable();
        if(vObject != null) {
            theOpdTemp = null;
            Object rowData[];
            if(writer == null) {
                writer = new DBFWriter();
            }
            for(int i=0;i<vObject.size();i++) {
                theOpdTemp = (Opd)vObject.elementAt(i);
                rowData = new Object[3];
                if(exp==1)
                    rowData = new Object[4];
                
                rowData[0] = theOpdTemp.hn;
                rowData[1] = theOpdTemp.clinic;
                //  rowData[2] = theOpdTemp.dateopd;
                if(!("").equals(theOpdTemp.dateopd)) {
                    
                    rowData[2] = StringDate.StringDateToDate(theOpdTemp.dateopd);
                }else{
                    rowData[2] = null;
                }
                
                if(exp==1)
                    rowData[3] = theOpdTemp.pid;
                
                
                
                writer.addRecord(rowData);
            }
            writer.write(fileOutput);
            rowData = null;
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
    
    public static void main(String args[]) throws Exception{
//        String url = "jdbc:postgresql://192.168.1.6:5432/develop_aoluk_test";
//        String user = "postgres";
//        String pass = "postgres";
//        String dri = "org.postgresql.Driver";
//        String type = "0";
//        ConnectionInf theConnectionInf = new ConnectionDBMgr(dri,url,user,pass,type);
//
//        OpdDB anc = new OpdDB(theConnectionInf);
//        Vector vOpd;
//        for(int i = 0 ;i< 5;i++)
//        {
//            vOpd = anc.selectByDate("2548-02-01","2548-02-20");
//            if(vOpd != null)
//            {   System.out.println(vOpd.size());
//                System.out.println(anc.convertToString(vOpd, false));
//
//
//
//            }
//            else
//            {
//                System.out.println("Value is null");
//            }
//            anc.clearObject();
//            vOpd =null;
//        }
    }
    
    public Vector selectByDate(String startDate, String endDate, int exp) throws Exception {
        
        this.exp = exp;
        String sql = IOStream.readInputDefault("config/rp_12file/12file_opd.sql");
        if(exp==1)
            sql = IOStream.readInputDefault("config/rp_12file50/12file50_opd.sql");
        PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return eQuery(pm.executeQuery());
    }
    
    public Vector convertData(Vector vData) throws Exception {
        Opd theOpd = new Opd();
        Vector vConverData = new Vector();
        String year = "";
        if(vData != null && vData.size() > 0) {
            for(int i=0,size = vData.size();i<size;i++) {
                theOpd = (Opd)vData.get(i);
                if(theOpd != null) {
                    // ส่งเข้าไปเปลี่ยนรูปแบบวันที่เป็น คศ.
                    theOpd.dateopd = Util.convertYearString(theOpd.dateopd);
                    
                    vConverData.add(theOpd);
                }
                theOpd = null;
            }
        }
        
        return vConverData;
    }
    
    public String getFileName() {
        return "OPD";
    }
    
}
