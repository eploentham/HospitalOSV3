/*
 * OrfDB.java
 *
 * Created on 1 �ԧ�Ҥ� 2548, 10:19 �.
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
import com.report12file.object.Orf;
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
public class OrfDB  implements File12DB{
    public ConnectionInf theConnectionInf;
    public Orf dbObj;
    public DBFWriter writer;
    public FileOutputStream fileOutput;
    public DBFField fields[];
    private String sql;
    private Orf theorf,theOrfTemp;
    private Vector vc;
    private ResultSet rs;
    
    private int exp;
    private StringBuffer sqlStringBuffer;
    /** Creates a new instance of OrfDB */
    public OrfDB() {
    }
    
    public OrfDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new Orf();
    }
    public java.util.Vector eQuery(String sql) throws Exception {
        ResultSet rs = theConnectionInf.eQuery(sql);
        return eQuery(rs);
    }
    public java.util.Vector eQuery(ResultSet rs) throws Exception {
        Vector vc = new Vector();
        while(rs.next()) {
            Orf theorf = new Orf();
            theorf.hn  = rs.getString(1);
            theorf.dateopd = rs.getString(2);
            theorf.clinic = rs.getString(3);
            theorf.refer = rs.getString(4);
            theorf.refertype = rs.getString(5);
            
            if(exp==1)
                theorf.pid = rs.getString(6);
            vc.add(theorf);
            theorf = null;
        }
        rs.close();
        return vc;
    }
    
    
    /***/
    public String convertToString(Vector vObject,boolean isGetColumnName) {
        // System.out.println("In method convert To String  size => "+vObject.size()+" Boolean "+isGetColumnName);
        sqlStringBuffer = new StringBuffer();
        String separator = Constant.PIPE;
        if(vObject != null) {
            theOrfTemp = null;
            if(isGetColumnName) {
                separator = Constant.TAB;
                sqlStringBuffer.append("hn" +separator
                        + "dateopd" +separator
                        + "clinic" +separator
                        + "refer" +separator
                        + "refertype");
                if(exp==1)
                    sqlStringBuffer.append(separator+"PERSON_ID");
                sqlStringBuffer.append(Constant.NEWLINE);
            }
            for(int i=0;i<vObject.size();i++) {
                theOrfTemp = (Orf)vObject.elementAt(i);
                sqlStringBuffer.append(theOrfTemp.hn +separator
                        + theOrfTemp.dateopd +separator
                        + theOrfTemp.clinic +separator
                        + theOrfTemp.refer +separator
                        + theOrfTemp.refertype);
                if(exp==1)
                    sqlStringBuffer.append(separator+theOrfTemp.pid);
                sqlStringBuffer.append(Constant.NEWLINE);
            }
        }
        separator = null;
        return sqlStringBuffer.toString();
    }
    
    /**��㹡�����ҧ���ҧ�ͧ Fox*/
    public void createTable() throws Exception {
        if(writer == null) {
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
            fields[3].setName( "refer");
            fields[3].setDataType( DBFField.FIELD_TYPE_C);
            fields[3].setFieldLength(5);
            
            fields[4] = new DBFField();
            fields[4].setName( "refertype");
            fields[4].setDataType( DBFField.FIELD_TYPE_C);
            fields[4].setFieldLength(1);
            
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
    
    /**�ӡ�� Insert ������ŧ�ҹ�����Ţͧ Fox
     * @param vObject �� Vector �ͧ Orf
     */
    public void insertData(Vector vObject) throws Exception{
        createTable();
        if(vObject != null) {
            theOrfTemp = null;
            Object rowData[];
            if(writer == null){
                writer = new DBFWriter();
            }
            for(int i=0;i<vObject.size();i++) {
                theOrfTemp = (Orf)vObject.elementAt(i);
                rowData = new Object[5];
                if(exp==1)
                    rowData = new Object[6];
                
                rowData[0] = theOrfTemp.hn;
                if(!("").equals(theOrfTemp.dateopd)) {
                    rowData[1] = StringDate.StringDateToDate(theOrfTemp.dateopd);
                } else {
                    rowData[1] = null;
                }
                rowData[2] = theOrfTemp.clinic;
                rowData[3] = theOrfTemp.refer;
                rowData[4] = theOrfTemp.refertype;
                
                if(exp==1)
                    rowData[5] = theOrfTemp.pid;
                
                writer.addRecord(rowData);
            }
            writer.write(fileOutput);
            rowData = null;
        }
        writer = null;
    }
    
    /**��㹡�����ҧ����������˹�
     *@param path �� String �ͧ path
     */
    public void setDBFPathFile(String path) throws FileNotFoundException{
        System.out.println("Path is = "+path);
        fileOutput = new FileOutputStream(path);
        
    }
    
    /**
     * ��㹡�ûԴ��������Դ���
     */
    public void closeFile() throws Exception{
        if(fileOutput != null) {
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
//        OrfDB anc = new OrfDB(theConnectionInf);
//        Vector vOrf = anc.selectByDate("2548-02-01","2548-02-20");
//        if(vOrf != null)
//        {    System.out.println(vOrf.size());
//            System.out.println(anc.convertToString(vOrf, false));
//        }
//        else
//        {
//            System.out.println("Value is null");
//        }
    }
    
    public Vector selectByDate(String startDate, String endDate, int exp) throws Exception {
        //String sql = get12file();
        this.exp = exp;
        String sql = IOStream.readInputDefault("config/rp_12file/12file_orf.sql");
        if(exp==1)
            sql = IOStream.readInputDefault("config/rp_12file50/12file50_orf.sql");
        PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return eQuery(pm.executeQuery());
    }
    
    public Vector convertData(Vector vData) throws Exception {
        Orf theOrf = new Orf();
        Vector vConverData = new Vector();
        String year = "";
        if(vData != null && vData.size() > 0) {
            for(int i=0,size = vData.size();i<size;i++) {
                theOrf = (Orf)vData.get(i);
                if(theOrf != null) {
                    // ����������¹�ٻẺ�ѹ����� ��.
                    theOrf.dateopd= Util.convertYearString(theOrf.dateopd);
                    
                    vConverData.add(theOrf);
                }
                theOrf = null;
            }
        }
        
        return vConverData;
    }
    
    public String getFileName() {
        return "ORF";
    }
    
}
