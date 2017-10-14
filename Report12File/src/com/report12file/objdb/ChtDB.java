/*
 * ChtDB.java
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
import com.report12file.object.Cht;
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
public class ChtDB implements File12DB {
    public ConnectionInf theConnectionInf;
    public Cht dbObj;
    public DBFWriter writer;
    public FileOutputStream fileOutput;
    public DBFField fields[];

    private int exp;
    
    
    /** Creates a new instance of ChtDB */
    public ChtDB() {
    }
    
    public ChtDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new Cht();
    }
    

    /**ใช้ในการ query ข้อมูลจากฐานข้อมูล และเก็บข้อมูลลง Vector 
     *  ของ Cht 
     * @param sql เป็น String ของ sql
     * @return Vector ของ Object aer
     */
    public Vector selectByDate(String startDate, String endDate,int exp) throws Exception {
        
        //String sql = get12file();
        this.exp = exp;
        String sql = IOStream.readInputDefault("config/rp_12file/12file_cht.sql");
        if(exp==1)
            sql = IOStream.readInputDefault("config/rp_12file50/12file50_cht.sql");
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
            Cht thecht = new Cht();
            thecht.hn = rs.getString(1); 
            thecht.an = rs.getString(2);
            thecht.date = rs.getString(3);
            // ส่งเข้าไปเปลี่ยนรูปแบบวันที่เป็น คศ.
            thecht.date = Util.convertYearString(thecht.date);
            thecht.total = rs.getString(4);
            thecht.paid = rs.getString(5);
            thecht.pttype = rs.getString(6);
            if(thecht.pttype==null)
                thecht.pttype = "";
                   
             if(exp==1)
                    thecht.pid = rs.getString(7);
            vc.add(thecht);
            thecht = null;
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
            Cht p = null;
            if(isGetColumnName){
                separator = Constant.TAB;
                sql.append("HN" +separator
                            + "AN" +separator
                            + "DATE" +separator
                            + "TOTAL" +separator
                            + "PAID" +separator
                            + "PTTYPE");
                if(exp==1)
                    sql.append(separator+"PERSON_ID");
                sql.append(Constant.NEWLINE);
            }
            for(int i=0;i<vObject.size();i++) {
                p = (Cht)vObject.elementAt(i);
                sql.append(p.hn  +separator
                            + p.an  +separator
                            + p.date +separator
                            + p.total  +separator
                            + p.paid  +separator
                            + p.pttype);
                if(exp==1)
                    sql.append(separator+p.pid);
                sql.append(Constant.NEWLINE);
            }
        }
        return sql.toString();
    }
    
    /**ใช้ในการสร้างตารางของ Fox*/
    public void createTable() throws Exception{
        
        if(writer == null)
        {
            fields = new DBFField[6];
            if(exp==1)
                fields = new DBFField[7];
            fields[0] = new DBFField();
            fields[0].setName( "hn");
            fields[0].setDataType( DBFField.FIELD_TYPE_C);
            fields[0].setFieldLength(9);

            fields[1] = new DBFField();
            fields[1].setName( "an");
            fields[1].setDataType( DBFField.FIELD_TYPE_C);
            fields[1].setFieldLength(9);

            fields[2] = new DBFField();
            fields[2].setName( "date");
            fields[2].setDataType( DBFField.FIELD_TYPE_D);
           // fields[2].setFieldLength(8);

            fields[3] = new DBFField();
            fields[3].setName( "total");
            fields[3].setDataType( DBFField.FIELD_TYPE_N);
            fields[3].setFieldLength(20);
            fields[3].setDecimalCount(5);

            fields[4] = new DBFField();
            fields[4].setName( "paid");
            fields[4].setDataType( DBFField.FIELD_TYPE_N);
            fields[4].setFieldLength(20);
            fields[4].setDecimalCount(5);

            fields[5] = new DBFField();
            fields[5].setName( "pttype");
            fields[5].setDataType( DBFField.FIELD_TYPE_C);
            fields[5].setFieldLength(2);
            
            if(exp==1){
                fields[6] = new DBFField();
                fields[6].setName( "PERSON_ID");
                fields[6].setDataType( DBFField.FIELD_TYPE_C);
                fields[6].setFieldLength(13);
            }
            writer = new DBFWriter();
            writer.setCharactersetName(Constant.ENCODE_TH);
            writer.setFields( fields);
        }
    }
    
    /**ทำการ Insert ข้อมูลลงฐานข้อมูลของ Fox
     * @param vObject เป็น Vector ของ cht
     */
    public void insertData(Vector vObject) throws Exception{
        createTable();
        if(vObject != null) {
            Cht p = null;
            Object rowData[];
            if(writer == null){
                writer = new DBFWriter();
            }
            for(int i=0;i<vObject.size();i++) {
                p = (Cht)vObject.elementAt(i);
                rowData = new Object[6];
                if(exp==1)
                    rowData = new Object[7]; 
                rowData[0] = p.hn ;
                rowData[1] = p.an ;                
                if(!("").equals(p.date))
                    rowData[2] = StringDate.StringDateToDate(p.date);
                else
                    rowData[2] = null;                

                if(!("").equals(p.total))
                     rowData[3] = new Double(p.total);
                else
                     rowData[3] = new Double("0");            
               
                if(!("").equals(p.paid))                
                    rowData[4] = new Double(p.paid);
                else            
                    rowData[4] = new Double("0");       
            
                rowData[5] = p.pttype;
                
                if(exp==1)
                    rowData[6] = p.pid;
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

//        Cht theCht = new Cht();
//        Vector vConverData = new Vector();
//        String year = "";
//        if(vData != null && vData.size() > 0)
//        {
//            for(int i=0,size = vData.size();i<size;i++)
//            {
//                theCht = (Cht)vData.get(i);
//                if(theCht != null)
//                {               
//                    // ส่งเข้าไปเปลี่ยนรูปแบบวันที่เป็น คศ.
//                    theCht.date = Util.convertYearString(theCht.date);
//                   
//                    if(theCht.pttype.equals("A1"))
//                        theCht.pttype = "10";
//                    
//                    vConverData.add(theCht);
//                }
//                theCht = null;
//            }
//        }
//        
//        return vConverData;       
        return vData;
    }

    public String getFileName() {
        return "CHT";
    }
    
}
