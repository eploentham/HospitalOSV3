/*
 * PatDB.java
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
import com.report12file.object.Pat;
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
public class PatDB  implements File12DB
{
    public ConnectionInf theConnectionInf;
    public Pat dbObj;
    public DBFWriter writer;
    public FileOutputStream fileOutput;
    public DBFField fields[];
    private String sql;
    private Vector vc;
    private Pat thepat,thePatTemp;
    private ResultSet rs;

    private int exp;

    private StringBuffer sqlStringBuffer;
    /** Creates a new instance of PatDB */
    public PatDB() {
    }
    
    public PatDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new Pat();
    }
    
   
    /**ใช้ในการ query ข้อมูลจากฐานข้อมูล และเก็บข้อมูลลง Vector 
     *  ของ Pat 
     * @param sql เป็น String ของ sql
     * @return Vector ของ Object Pat
     */
    public Vector selectByDate(String startDate, String endDate,int exp) throws Exception {
        
        this.exp = exp;
        String sql = IOStream.readInputDefault("config/rp_12file/12file_pat1.sql");
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
            Pat thepat = new Pat();
            thepat.hcode = rs.getString(1);
            thepat.hn = rs.getString(2);
            thepat.changwat = rs.getString(3);
            thepat.amphur = rs.getString(4);
            thepat.dob = rs.getString(5);
            thepat.sex = rs.getString(6);
            thepat.marriage = rs.getString(7);
            thepat.occupa = rs.getString(8);
            thepat.nation = rs.getString(9);
            thepat.person_id = rs.getString(10);
            if(exp!=1)
                thepat.namepat = rs.getString(11);
            vc.add(thepat);
            thepat = null;
        }
        rs.close();
        return vc;
    }
    
    
    /***/
    public String convertToString(Vector vObject,boolean isGetColumnName) {
        System.out.println("In method convert To String  size => "+vObject.size()+" Boolean "+isGetColumnName);
        sqlStringBuffer = new StringBuffer();
        String separator = Constant.PIPE;
        if(vObject != null) 
        {
            thepat = null;
            if(isGetColumnName)
            {
                separator = Constant.TAB;
                sqlStringBuffer.append("hcode" +separator
                            + "hn" +separator
                            + "changwat" +separator
                            + "ampur" +separator
                            + "dob" +separator
                            + "sex" +separator
                            + "marriage" +separator
                            + "occupa" +separator
                            + "nation" +separator
                            + "person_id"+separator
                            + "namepat");
                sqlStringBuffer.append(Constant.NEWLINE);
            }
            for(int i=0;i<vObject.size();i++) 
            {
                thePatTemp= (Pat)vObject.elementAt(i);
                sqlStringBuffer.append(thePatTemp.hcode +separator
                            + thePatTemp.hn +separator
                            + thePatTemp.changwat +separator
                            + thePatTemp.amphur +separator
                            + thePatTemp.dob +separator
                            + thePatTemp.sex +separator
                            + thePatTemp.marriage +separator
                            + thePatTemp.occupa +separator
                            + thePatTemp.nation +separator
                            + thePatTemp.person_id+separator
                            + thePatTemp.namepat);
                sqlStringBuffer.append(Constant.NEWLINE);
            }
        }
        separator = null;
        return sqlStringBuffer.toString();
    }
    
    /**ใช้ในการสร้างตารางของ Fox*/
    public void createTable() throws Exception
    {
        fields = new DBFField[11];
        if(exp==1)
            fields = new DBFField[10];
        
        fields[0] = new DBFField();
        fields[0].setName( "hcode");
        fields[0].setDataType( DBFField.FIELD_TYPE_C);
        fields[0].setFieldLength(5);
        
        fields[1] = new DBFField();
        fields[1].setName( "hn");
        fields[1].setDataType( DBFField.FIELD_TYPE_C);
        fields[1].setFieldLength(9);
        
        fields[2] = new DBFField();
        fields[2].setName( "changwat");
        fields[2].setDataType( DBFField.FIELD_TYPE_C);
        fields[2].setFieldLength(2);
        
        fields[3] = new DBFField();
        fields[3].setName( "amphur");
        fields[3].setDataType( DBFField.FIELD_TYPE_C);
        fields[3].setFieldLength(2);
        
        fields[4] = new DBFField();
        fields[4].setName( "dob");
        fields[4].setDataType( DBFField.FIELD_TYPE_D);
        //fields[4].setFieldLength(4);
        
        fields[5] = new DBFField();
        fields[5].setName( "sex");
        fields[5].setDataType( DBFField.FIELD_TYPE_C);
        fields[5].setFieldLength(1);
        
        fields[6] = new DBFField();
        fields[6].setName( "marriage");
        fields[6].setDataType( DBFField.FIELD_TYPE_C);
        fields[6].setFieldLength(1);
        
        fields[7] = new DBFField();
        fields[7].setName( "occupa");
        fields[7].setDataType( DBFField.FIELD_TYPE_C);
        fields[7].setFieldLength(3);
        
        fields[8] = new DBFField();
        fields[8].setName( "nation");
        fields[8].setDataType( DBFField.FIELD_TYPE_C);
        fields[8].setFieldLength(2);

        fields[9] = new DBFField();
        fields[9].setName( "person_id");
        fields[9].setDataType( DBFField.FIELD_TYPE_C);
        fields[9].setFieldLength(13);
        
        if(exp!=1){
            fields[10] = new DBFField();
            fields[10].setName( "namepat");
            fields[10].setDataType( DBFField.FIELD_TYPE_C);
            fields[10].setFieldLength(35);
        }
        
        writer = new DBFWriter();
        writer.setCharactersetName(Constant.ENCODE_TH);
        writer.setFields( fields);
        
    }
    
    /**ทำการ Insert ข้อมูลลงฐานข้อมูลของ Fox
     * @param vObject เป็น Vector ของ Pat
     */
    public void insertData(Vector vObject) throws Exception{
        createTable();
        if(writer == null)
            writer = new DBFWriter();

        for(int i=0;i<vObject.size();i++) 
        {
            Pat thePatTemp = (Pat)vObject.elementAt(i);
            Object[] rowData = new Object[11];
            if(exp==1)
                rowData = new Object[10];

            rowData[0] = thePatTemp.hcode;
            rowData[1] = thePatTemp.hn;
            rowData[2] = thePatTemp.changwat;
            rowData[3] = thePatTemp.amphur;
           // rowData[4] = thePatTemp.dob;
            if(!("").equals(thePatTemp.dob))
                rowData[4] = StringDate.StringDateToDate(thePatTemp.dob);
            else
                rowData[4] = null;

            rowData[5] = thePatTemp.sex;
            rowData[6] = thePatTemp.marriage;
            rowData[7] = thePatTemp.occupa;
            rowData[8] = thePatTemp.nation;
            rowData[9] = thePatTemp.person_id;
            if(exp!=1)
                rowData[10] = thePatTemp.namepat;


            writer.addRecord(rowData);
        }
        writer.write(fileOutput);
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
        
        PatDB anc = new PatDB(theConnectionInf);
        Vector vPat = anc.selectByDate("2548-02-01","2548-02-20", 0);
        if(vPat != null)
        {    System.out.println(vPat.size());
            System.out.println(anc.convertToString(vPat, false));
        }
        else
        {
            System.out.println("Value is null");
        }
    }

    public Vector convertData(Vector vData) throws Exception {
        Pat thePat = new Pat();
        Vector vConverData = new Vector();
        String year = "";
        if(vData != null && vData.size() > 0)
        {
            for(int i=0,size = vData.size();i<size;i++)
            {
                thePat = (Pat)vData.get(i);
                if(thePat != null)
                {
                    // ส่งเข้าไปเปลี่ยนรูปแบบวันที่เป็น คศ.
                    thePat.dob = Util.convertYearString(thePat.dob);            
                    
                    vConverData.add(thePat);
                }
                thePat = null;
            }
        }
        
        return vConverData;
    }

    public String getFileName() {
        return "PAT";
    }

    
}
