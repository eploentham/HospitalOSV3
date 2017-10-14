/*
 * IdxDB.java
 *
 * Created on 1 สิงหาคม 2548, 10:19 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.objdb;
import com.hospital_os.utility.IOStream;
import java.sql.*;
import java.util.Vector;
import com.report12file.object.Idx;

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
public class IdxDB  implements File12DB{
    public ConnectionInf theConnectionInf;
    public Idx dbObj;
    public DBFWriter writer;
    public FileOutputStream fileOutput;
    public DBFField fields[];
    
    
    /** Creates a new instance of IdxDB */
    public IdxDB() {
    }
    
    public IdxDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new Idx();
    }
    
    /**ใช้ในการ query กับฐานข้อมูลของ HospitalOS ตามข้อมูลของตาราง
     *@param startDate เป็น String ของ วันที่ เริ่มค้นหา
     *@param endDate เป็น String ของ วันที่ สิ้นสุดการค้นหา
     *@return เป็น Vector ของ Object aer
     *@author ผดุงรัฐ
     *@modify จิราภรณ์
     */
//    public Vector selectByDate(String startDate, String endDate) throws Exception {
//        StringBuffer sqlBuffer = new StringBuffer();
//        sqlBuffer.append("SELECT t_visit.visit_vn AS AN  , ");
//        sqlBuffer.append("(substring(diag_icd10_number,0,4) || substr(diag_icd10_number,5)) AS DIAG  , ");
//        sqlBuffer.append("t_diag_icd10.f_diag_icd10_type_id AS DXTYPE  , ");
//        sqlBuffer.append("b_employee.employee_number AS DRDX  ");
//        sqlBuffer.append("FROM t_diag_icd10  ");
//        sqlBuffer.append("        INNER JOIN t_visit ON (t_diag_icd10.diag_icd10_vn = t_visit.t_visit_id)  ");
//        sqlBuffer.append("        INNER JOIN b_employee ON (t_diag_icd10.diag_icd10_staff_doctor = b_employee.b_employee_id) "); 
//        sqlBuffer.append("WHERE  t_visit.visit_staff_doctor_discharge_date_time <> ''  ");
//        sqlBuffer.append("        AND t_visit.b_visit_ward_id <> ''  ");
//        sqlBuffer.append("        AND t_visit.f_visit_type_id = '1'  ");
//        sqlBuffer.append("        AND t_visit.f_visit_status_id <> '4'  ");
//        sqlBuffer.append("        AND t_diag_icd10.diag_icd10_active = '1' ");
//        sqlBuffer.append("        AND (substring(t_visit.visit_financial_discharge_time,1,10) >= '"+startDate+"' ");  
//        sqlBuffer.append("        AND substring(t_visit.visit_financial_discharge_time,1,10) <= '"+endDate+"') ");
//        System.out.println("SQL IDX : " + sqlBuffer.toString());
//         Vector v = eQuery(sqlBuffer.toString());
//            return v;
//    }
    /**ใช้ในการ query ข้อมูลจากฐานข้อมูล และเก็บข้อมูลลง Vector 
     *  ของ Idx 
     * @param sql เป็น String ของ sql
     * @return Vector ของ Object aer
     */
    public Vector selectByDate(String startDate, String endDate,int exp) throws Exception {
        
        //String sql = get12file();
        String sql = IOStream.readInputDefault("config/rp_12file/12file_idx.sql");
        PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
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
                Idx theidx = new Idx();
                theidx.an  = rs.getString(1);
                theidx.diag = rs.getString(2);
                theidx.dxtype = rs.getString(3);
                theidx.drdx = rs.getString(4);
            
            vc.add(theidx);
            theidx = null;
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
            Idx p = null;
            if(isGetColumnName){
                separator = Constant.TAB;
                sql.append("an" +separator
                        + "diag" +separator
                        + "dxtype" +separator
                        + "drdx"
                        +Constant.NEWLINE);
            }
            for(int i=0;i<vObject.size();i++) {
                p = (Idx)vObject.elementAt(i);
                sql.append(p.an +separator
                            + p.diag +separator
                            + p.dxtype +separator
                            + p.drdx
                        +Constant.NEWLINE);
            }
        }
        return sql.toString();
    }
    
    /**ใช้ในการสร้างตารางของ Fox*/
    public void createTable() throws Exception{
        if(writer == null)
        {
            fields = new DBFField[4];

            fields[0] = new DBFField();
            fields[0].setName( "an");
            fields[0].setDataType( DBFField.FIELD_TYPE_C);
            fields[0].setFieldLength(9);

            fields[1] = new DBFField();
            fields[1].setName( "diag");
            fields[1].setDataType( DBFField.FIELD_TYPE_C);
            fields[1].setFieldLength(5);

            fields[2] = new DBFField();
            fields[2].setName( "dxtype");
            fields[2].setDataType( DBFField.FIELD_TYPE_C);
            fields[2].setFieldLength(1);

            fields[3] = new DBFField();
            fields[3].setName( "drdx");
            fields[3].setDataType( DBFField.FIELD_TYPE_C);
            fields[3].setFieldLength(8);
        
        
        
        
        
            writer = new DBFWriter();
            writer.setCharactersetName(Constant.ENCODE_TH);
            writer.setFields( fields);
        }
        
    }
    
    /**ทำการ Insert ข้อมูลลงฐานข้อมูลของ Fox
     * @param vObject เป็น Vector ของ aer
     */
    public void insertData(Vector vObject) throws Exception{
        createTable();
        if(vObject != null) {
            Idx p = null;
            Object rowData[];
            if(writer == null){
                writer = new DBFWriter();
            }
            for(int i=0;i<vObject.size();i++) {
                p = (Idx)vObject.elementAt(i);
                rowData = new Object[4];
                
                               
                rowData[0] = p.an;
                rowData[1] = p.diag;
                rowData[2] = p.dxtype;
                rowData[3] = p.drdx;
                
                
                
                
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


    public Vector convertData(Vector v) throws Exception {
        return v;
    }

    public String getFileName() {
        return "IDX";
    }
    
}
