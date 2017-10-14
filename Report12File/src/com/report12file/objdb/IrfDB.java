/*
 * IrfDB.java
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
import com.report12file.object.Irf;
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
public class IrfDB  implements File12DB{
    public ConnectionInf theConnectionInf;
    public Irf dbObj;
    public DBFWriter writer;
    public FileOutputStream fileOutput;
    public DBFField fields[];
    
    
    /** Creates a new instance of IrfDB */
    public IrfDB() {
    }
    
    public IrfDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new Irf();
    }
    
    /**ใช้ในการ query กับฐานข้อมูลของ HospitalOS ตามข้อมูลของตาราง
     *@param startDate เป็น String ของ วันที่ เริ่มค้นหา
     *@param endDate เป็น String ของ วันที่ สิ้นสุดการค้นหา
     *@return เป็น Vector ของ Object Irf
     *@author ผดุงรัฐ
     *@modify จิราภรณ์
     */
//    public Vector selectByDate(String startDate, String endDate) throws Exception
//    {
//                // Refer In
//          String sql = " SELECT " +
//               " t_visit.visit_vn AS AN  " +
//               " , CASE " +
//                        " WHEN ((t_visit.visit_begin_visit_time <> '') AND (t_visit.visit_begin_visit_time <> 'null') " +
//                        " AND Length(t_visit.visit_begin_visit_time) >=16 ) " +
//                        " THEN substring(t_visit.visit_begin_visit_time from 12 for 5 ) " +
//                        " ELSE '' END AS DATEIPD " +
//               " ,t_visit.b_visit_office_id_refer_in as REFER  " +
//               ",'1' as REFERTYPE   " +              
//               " FROM  " +
//               " t_visit  INNER JOIN t_patient ON (t_patient.t_patient_id = t_visit.t_patient_id)  " +
//               " WHERE " +
//               " (t_visit.b_visit_office_id_refer_in <> '' ) AND (t_visit.b_visit_office_id_refer_in <> 'null' ) " +
//               " and (t_visit.b_visit_ward_id <> ''  ) " +
//               " AND (t_visit.f_visit_type_id = '1') " +
//               " AND (substring(t_visit.visit_financial_discharge_time,0,11) BETWEEN '"+ startDate+ "'  AND '"+ endDate+ "' ) " +
//               " group by  " +
//               " t_visit.visit_vn,t_visit.visit_begin_visit_time,t_visit.b_visit_office_id_refer_in   " +
//               " UNION  " +
//               // Refer Out 
//               " SELECT  " +
//               " t_visit.visit_vn AS AN  " +
//               " , CASE " +
//                        " WHEN ((t_visit.visit_begin_visit_time <> '') AND (t_visit.visit_begin_visit_time <> 'null') " +
//                        " AND Length(t_visit.visit_begin_visit_time) >=16 ) " +
//                        " THEN substring(t_visit.visit_begin_visit_time from 12 for 5 ) " +
//                        " ELSE '' END AS DATEIPD " +
//               " ,t_visit.b_visit_office_id_refer_out as REFER  " +
//               ",'2' as REFERTYPE   " +
//               " FROM   " +
//               " t_visit  INNER JOIN t_patient ON (t_patient.t_patient_id = t_visit.t_patient_id)  " +
//               " WHERE " +
//               " (t_visit.b_visit_office_id_refer_out <> '' ) AND (t_visit.b_visit_office_id_refer_out <> 'null' ) " +
//               " and (t_visit.b_visit_ward_id <> ''  ) " +
//               " AND (t_visit.f_visit_type_id = '1') " +
//               " AND (substring(t_visit.visit_financial_discharge_time,0,11) BETWEEN '"+ startDate+ "'  AND '"+ endDate+ "' )  " +
//               " group by t_visit.visit_vn,t_visit.visit_begin_visit_time,t_visit.b_visit_office_id_refer_out ";
//        
//        System.out.println("SQL Irf : " + sql);
//        Vector v = eQuery(sql);
//        return v;   
//    }
    /**ใช้ในการ query ข้อมูลจากฐานข้อมูล และเก็บข้อมูลลง Vector 
     *  ของ Irf 
     * @param sql เป็น String ของ sql
     * @return Vector ของ Object Irf
     */
    public Vector selectByDate(String startDate, String endDate,int exp) throws Exception {
        
        //String sql = get12file();
        String sql = IOStream.readInputDefault("config/rp_12file/12file_irf.sql");
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
            Irf theirf = new Irf();
            theirf.an = rs.getString(1);
            theirf.refer = rs.getString(3);
            theirf.refertype = rs.getString(4);
            
            vc.add(theirf);
            theirf = null;
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
            Irf p = null;
            if(isGetColumnName){
                separator = Constant.TAB;
                sql.append("an" +separator
                            + "refer" +separator
                            +  "refertype"
                        +Constant.NEWLINE);
            }
            for(int i=0;i<vObject.size();i++) {
                p = (Irf)vObject.elementAt(i);
                sql.append(p.an +separator
                            + p.refer +separator
                            + p.refertype
                        +Constant.NEWLINE);
            }
        }
        return sql.toString();
    }
    
    /**ใช้ในการสร้างตารางของ Fox*/
    public void createTable() throws Exception{
        if(writer == null)
        {
        fields = new DBFField[3];
        
        fields[0] = new DBFField();
        fields[0].setName( "an");
        fields[0].setDataType( DBFField.FIELD_TYPE_C);
        fields[0].setFieldLength(9);
        
        fields[1] = new DBFField();
        fields[1].setName( "refer");
        fields[1].setDataType( DBFField.FIELD_TYPE_C);
        fields[1].setFieldLength(5);
        
        fields[2] = new DBFField();
        fields[2].setName( "refertype");
        fields[2].setDataType( DBFField.FIELD_TYPE_C);
        fields[2].setFieldLength(1);
        
        
        
        
            writer = new DBFWriter();
            writer.setCharactersetName(Constant.ENCODE_TH);
            writer.setFields( fields);
        }
        
    }
    
    /**ทำการ Insert ข้อมูลลงฐานข้อมูลของ Fox
     * @param vObject เป็น Vector ของ Irf
     */
    public void insertData(Vector vObject) throws Exception{
        createTable();
        if(vObject != null) {
            Irf p = null;
            Object rowData[];
            if(writer == null){
                writer = new DBFWriter();
            }
            for(int i=0;i<vObject.size();i++) {
                p = (Irf)vObject.elementAt(i);
                rowData = new Object[3];
                
                rowData[0] = p.an;
                rowData[1] = p.refer;
                rowData[2] = p.refertype;
                
                
                
                
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
        return "IRF";
    }
    
}
