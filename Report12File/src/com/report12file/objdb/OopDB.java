/*
 * OopDB.java
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
import com.report12file.object.Oop;

import com.report12file.utility.*;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.utility.ConnectionDBMgr;

import com.linuxense.javadbf.*;


import java.io.*;
/**
 *
 * @author tong(Padungrat)
 * @Modify Ojika
 */
public class OopDB implements File12DB {
    public ConnectionInf theConnectionInf;
    public Oop dbObj;
    public DBFWriter writer;
    public FileOutputStream fileOutput;
    public DBFField fields[];
    
    private int exp;
    
    
    /** Creates a new instance of OopDB */
    public OopDB() {
    }
    
    public OopDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new Oop();
    }
    
    /**ใช้ในการ query ข้อมูลจากฐานข้อมูล และเก็บข้อมูลลง Vector
     *  ของ Oop
     * @param sql เป็น String ของ sql
     * @return Vector ของ Object Oop
     */
    public java.util.Vector eQuery(String sql) throws Exception {
        ResultSet rs = theConnectionInf.eQuery(sql);
        return eQuery(rs);
    }
    public java.util.Vector eQuery(ResultSet rs) throws Exception {
        Vector vc = new Vector();
        while(rs.next()) {
            Oop theoop = new Oop();
            theoop.hn = rs.getString(1);
            theoop.dateopd = rs.getString(2);
            theoop.clinic = rs.getString(3);
            theoop.oper = rs.getString(4);
            theoop.drop = rs.getString(5);
            
            if(exp==1)
                theoop.pid = rs.getString(6);
            vc.add(theoop);
            theoop = null;
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
            Oop p = null;
            if(isGetColumnName){
                separator = Constant.TAB;
                sql.append("hn" +separator
                        + "dateopd" +separator
                        + "clinic" +separator
                        + "oper" +separator
                        + "drop");
                if(exp==1)
                    sql.append(separator+"PERSON_ID");
                sql.append(Constant.NEWLINE);
            }
            for(int i=0;i<vObject.size();i++) {
                p = (Oop)vObject.elementAt(i);
                sql.append(p.hn +separator
                        + p.dateopd +separator
                        + p.clinic +separator
                        + p.oper +separator
                        + p.drop);
                if(exp==1)
                    sql.append(separator+p.pid);
                sql.append(Constant.NEWLINE);
            }
        }
        return sql.toString();
    }
    
    /**ใช้ในการสร้างตารางของ Fox*/
    public void createTable() throws Exception{
        if(writer == null){
            fields = new DBFField[5];
            if(exp==1)
                fields = new DBFField[6];
            
            fields[0] = new DBFField();
            fields[0].setName( "hn");
            fields[0].setDataType( DBFField.FIELD_TYPE_C);
            fields[0].setFieldLength(9);
            
            fields[1] = new DBFField();
            fields[1].setName( "dateopd");
            fields[1].setDataType( DBFField.FIELD_TYPE_D);
            //  fields[1].setFieldLength(12);
            
            fields[2] = new DBFField();
            fields[2].setName( "clinic");
            fields[2].setDataType( DBFField.FIELD_TYPE_C);
            fields[2].setFieldLength(4);
            
            fields[3] = new DBFField();
            fields[3].setName( "oper");
            fields[3].setDataType( DBFField.FIELD_TYPE_C);
            fields[3].setFieldLength(4);
            
            fields[4] = new DBFField();
            fields[4].setName( "drop");
            fields[4].setDataType( DBFField.FIELD_TYPE_C);
            fields[4].setFieldLength(6);
            
            if(exp==1){
                fields[5] = new DBFField();
                fields[5].setName( "PERSON_ID");
                fields[5].setDataType( DBFField.FIELD_TYPE_C);
                fields[5].setFieldLength(13);
            }
            
            
            
            
            writer = new DBFWriter();
            writer.setCharactersetName(Constant.ENCODE_TH);
            writer.setFields( fields);
        }
        
    }
    
    /**ทำการ Insert ข้อมูลลงฐานข้อมูลของ Fox
     * @param vObject เป็น Vector ของ Oop
     */
    public void insertData(Vector vObject) throws Exception{
        createTable();
        if(vObject != null) {
            Oop p = null;
            Object rowData[];
            if(writer == null){
                writer = new DBFWriter();
            }
            for(int i=0;i<vObject.size();i++) {
                p = (Oop)vObject.elementAt(i);
                rowData = new Object[5];
                if(exp==1)
                    rowData = new Object[6];
                
                rowData[0] = p.hn;
                //rowData[1] = p.dateopd;
                if(!("").equals(p.dateopd)){
                    rowData[1] = StringDate.StringDateToDate(p.dateopd);
                }else{
                    rowData[1] = null;
                }
                rowData[2] = p.clinic;
                rowData[3] = p.oper;
                rowData[4] = p.drop;
                
                if(exp==1)
                    rowData[5] = p.pid;
                
                
                
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
    
    public static void main(String args[]) throws Exception{
        String url = "jdbc:postgresql://192.168.1.6:5432/develop_aoluk_test";
        String user = "postgres";
        String pass = "postgres";
        String dri = "org.postgresql.Driver";
        String type = "0";
        ConnectionInf theConnectionInf = new ConnectionDBMgr(dri,url,user,pass,type);
        
        OopDB anc = new OopDB(theConnectionInf);
        Vector vOop;
        for(int i = 0;i<5;i++) {
            vOop = anc.selectByDate("2548-02-01","2548-02-20",0);
            if(vOop != null) {
                System.out.println(vOop.size());
                System.out.println(anc.convertToString(vOop, false));
            } else {
                System.out.println("Value is null");
            }
            vOop = null;
        }
    }
    
    public Vector selectByDate(String startDate, String endDate, int exp) throws Exception {
        
        //String sql = get12file();
        this.exp = exp;
        String sql = IOStream.readInputDefault("config/rp_12file/12file_oop.sql");
        if(exp==1)
            sql = IOStream.readInputDefault("config/rp_12file50/12file50_oop.sql");
        PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return eQuery(pm.executeQuery());
    }
    
    public Vector convertData(Vector vData) throws Exception {
        Oop theOop = new Oop();
        Vector vConverData = new Vector();
        String year = "";
        if(vData != null && vData.size() > 0) {
            for(int i=0,size = vData.size();i<size;i++) {
                theOop = (Oop)vData.get(i);
                if(theOop != null) {
                    // ส่งเข้าไปเปลี่ยนรูปแบบวันที่เป็น คศ.
                    theOop.dateopd = Util.convertYearString(theOop.dateopd);
                    
                    vConverData.add(theOop);
                }
                theOop = null;
            }
        }
        
        return vConverData;
    }
    
    public String getFileName() {
        return "OOP";
    }
    
}
