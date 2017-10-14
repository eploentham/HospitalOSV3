/*
 * IopDB.java
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
import com.report12file.object.Iop;

import com.report12file.utility.*;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.utility.ConnectionDBMgr;

import com.linuxense.javadbf.*;


import java.io.*;
/**
 *
 * @author tong(Padungrat)
 * @Midify Ojika
 */
public class IopDB  implements File12DB{
    public ConnectionInf theConnectionInf;
    public Iop dbObj;
    public DBFWriter writer;
    public FileOutputStream fileOutput;
    public DBFField fields[];
    
    
    /** Creates a new instance of IopDB */
    public IopDB() {
    }
    
    public IopDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new Iop();
    }
    
    /**ใช้ในการ query กับฐานข้อมูลของ HospitalOS ตามข้อมูลของตาราง
     *@param startDate เป็น String ของ วันที่ เริ่มค้นหา
     *@param endDate เป็น String ของ วันที่ สิ้นสุดการค้นหา
     *@return เป็น Vector ของ Object Iop
     *@author ผดุงรัฐ
     *@modify จิราภรณ์
     */
//    public Vector selectByDate(String startDate, String endDate) throws Exception {
//        /*String sql = " SELECT t_visit.visit_vn AS AN " +
//                        " , t_diag_icd9.diag_icd9_icd9_number AS OPER " +
//                        " , t_diag_icd9.f_diagnosis_operation_type_id AS OPTYPE " +
//                        " ,b_employee.employee_number AS DROP " +
//                
//                " , CASE " +
//                " WHEN ((t_diag_icd9.diag_icd9_start_time <> '') AND (t_diag_icd9.diag_icd9_start_time <> 'null') " +
//                " AND Length(t_diag_icd9.diag_icd9_start_time) >=10 ) " +
//                " THEN substring(t_diag_icd9.diag_icd9_start_time,0,11) " +
//                " ELSE '' END AS DATEIN " +
//              
//                " , CASE " +
//                " WHEN ((t_diag_icd9.diag_icd9_start_time <> '') AND (t_diag_icd9.diag_icd9_start_time <> 'null') " +
//                " AND Length(t_diag_icd9.diag_icd9_start_time) >=16 ) " +
//                " THEN substring(t_diag_icd9.diag_icd9_start_time from 12 for 5 ) " +
//                " ELSE '' END AS TIMEIN " +
//                
//                " , CASE " +
//                " WHEN ((t_diag_icd9.diag_icd9_finish_time <> '') AND (t_diag_icd9.diag_icd9_finish_time <> 'null') " +
//                " AND Length(t_diag_icd9.diag_icd9_finish_time) >=10 ) " +
//                " THEN substring(t_diag_icd9.diag_icd9_finish_time,0,11) " +
//                " ELSE '' END AS DATEIN " + 
//                
//                " , CASE " +
//                " WHEN ((t_diag_icd9.diag_icd9_finish_time <> '') AND (t_diag_icd9.diag_icd9_finish_time <> 'null') " +
//                " AND Length(t_diag_icd9.diag_icd9_finish_time) >=16 ) " +
//                " THEN substring(t_diag_icd9.diag_icd9_finish_time from 12 for 5 ) " +
//                " ELSE '' END AS TIMEOUT " +
//                
//                        " FROM  t_diag_icd9 " +
//                        " INNER JOIN t_visit ON (t_diag_icd9.diag_icd9_vn = t_visit.t_visit_id) " + 
//                        " LEFT JOIN b_employee ON (t_diag_icd9.diag_icd9_staff_doctor = b_employee.b_employee_id) "+
//                    	" WHERE " +
//                        " t_visit.visit_staff_doctor_discharge_date_time <> '' " +
//                        " AND t_visit.b_visit_ward_id <> '' " +
//                        " AND t_visit.f_visit_type_id = '1' " +
//                        " AND t_visit.f_visit_status_id <> '4' " +
//                        " AND t_diag_icd9.diag_icd9_active = '1' " +
//                        " AND (substring(t_visit.visit_financial_discharge_time,0,11) >= '"+ startDate+ "' "+ 
//			" AND substring(t_visit.visit_financial_discharge_time,0,11) <= '" + endDate  + "') ";*/
//        
//        String sql = "SELECT t_visit.visit_vn AS AN  , " +
//                "t_diag_icd9.diag_icd9_icd9_number AS OPER  , " +
//                "t_diag_icd9.f_diagnosis_operation_type_id AS OPTYPE  ," +
//                "b_employee.employee_number AS DROP  , " +
//                "CASE  WHEN ((t_diag_icd9.diag_icd9_start_time <> '') " +
//                "AND (t_diag_icd9.diag_icd9_start_time <> 'null')  " +
//                "AND Length(t_diag_icd9.diag_icd9_start_time) >=10 )  " +
//                "THEN substring(t_diag_icd9.diag_icd9_start_time,0,11) ELSE '' END AS DATEIN  , " +
//                "CASE  WHEN ((t_diag_icd9.diag_icd9_start_time <> '') " +
//                "AND (t_diag_icd9.diag_icd9_start_time <> 'null')  " +
//                "AND Length(t_diag_icd9.diag_icd9_start_time) >=16 )  " +
//                "THEN substring(t_diag_icd9.diag_icd9_start_time " +
//                "from 12 for 5 )  ELSE '' END AS TIMEIN  , " +
//                "CASE  WHEN ((t_diag_icd9.diag_icd9_finish_time <> '') " +
//                "AND (t_diag_icd9.diag_icd9_finish_time <> 'null')  " +
//                "AND Length(t_diag_icd9.diag_icd9_finish_time) >=10 )  " +
//                "THEN substring(t_diag_icd9.diag_icd9_finish_time,0,11)  ELSE '' END AS DATEIN  , " +
//                "CASE  WHEN ((t_diag_icd9.diag_icd9_finish_time <> '') " +
//                "AND (t_diag_icd9.diag_icd9_finish_time <> 'null')  " +
//                "AND Length(t_diag_icd9.diag_icd9_finish_time) >=16 )  " +
//                "THEN substring(t_diag_icd9.diag_icd9_finish_time from 12 for 5 )  ELSE '' END AS TIMEOUT  " +
//                "FROM  t_diag_icd9  INNER JOIN t_visit " +
//                "ON (t_diag_icd9.diag_icd9_vn = t_visit.t_visit_id " +
//               /* "AND (t_diag_icd9.diag_icd9_icd9_number = '03.91' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '04.81' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '06.39' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '08.25' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '13.43' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '13.59' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '13.71' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '19.40' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '20.49' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '31.10' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '39.59' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '44.63' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '45.90' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '47.20' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '49.46' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '51.21' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '51.22' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '53.01' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '53.02' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '54.11' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '54.19' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '54.40' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '54.50' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '54.91' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '55.11' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '55.51' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '56.20' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '57.19' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '62.50' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '63.10' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '64.00' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '65.29' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '65.49' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '66.32' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '66.40' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '66.51' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '67.39' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '68.40' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '68.59' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '69.02' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '69.09' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '70.33' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '70.50' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '71.23' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '71.24' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '71.79' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '72.10' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '72.52' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '72.71' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '73.59' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '73.60' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '74.10' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '74.91' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '75.40' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '83.61' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '84.01' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '84.03' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '84.05' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '84.11' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '84.12' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '84.15' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '84.17' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '85.21' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '86.04' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '86.09' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '86.22' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '86.23' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '86.30' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '86.30' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '86.59' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '89.51' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '93.57' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '93.90' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '97.29' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '97.84' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '99.29' " +
//                "OR t_diag_icd9.diag_icd9_icd9_number = '99.99' )" +*/
//                ")  " +
//                "LEFT JOIN b_employee ON (t_diag_icd9.diag_icd9_staff_doctor = b_employee.b_employee_id) " +
//                "WHERE  t_visit.visit_staff_doctor_discharge_date_time <> '' " +
//                "AND t_visit.b_visit_ward_id <> ''  " +
//                "AND t_visit.f_visit_type_id = '1'  " +
//                "AND t_visit.f_visit_status_id <> '4'  " +
//                "AND t_diag_icd9.diag_icd9_active = '1'  " +
//                "AND (substring(t_visit.visit_financial_discharge_time,0,11) >= '"+ startDate+ "' "+ 
//                "AND substring(t_visit.visit_financial_discharge_time,0,11) <= '" + endDate  + "')";         
//         System.out.println("SQL Iop : " + sql);
//         Vector v = eQuery(sql);
//         return v;
//    }
    /**ใช้ในการ query ข้อมูลจากฐานข้อมูล และเก็บข้อมูลลง Vector 
     *  ของ Iop 
     * @param sql เป็น String ของ sql
     * @return Vector ของ Object Iop
     */
    public Vector selectByDate(String startDate, String endDate,int exp) throws Exception {
        
        //String sql = get12file();
        String sql = IOStream.readInputDefault("config/rp_12file/12file_iop.sql");
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
            Iop theiop = new Iop();
            theiop.an = rs.getString(1); 
            theiop.oper = rs.getString(2);
            theiop.optype = rs.getString(3);
            theiop.drop = rs.getString(4);
            theiop.datein = rs.getString(5);
            theiop.timein = rs.getString(6);
            theiop.dateout = rs.getString(7);
            theiop.timeout = rs.getString(8);
            
            vc.add(theiop);
            theiop = null;
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
            Iop p = null;
            if(isGetColumnName){
                separator = Constant.TAB;
                sql.append("an" +separator
                            + "oper" +separator
                            + "optype" +separator
                            + "drop" +separator
                            + "datein" +separator
                            + "timein" +separator
                            + "dateout" +separator
                            + "timeout"
                        +Constant.NEWLINE);
            }
            for(int i=0;i<vObject.size();i++) {
                p = (Iop)vObject.elementAt(i);
                sql.append(p.an +separator
                            + p.oper +separator
                            + p.optype +separator
                            + p.drop +separator
                            + p.datein +separator
                            + p.timein +separator
                            + p.dateout +separator
                            + p.timeout
                        +Constant.NEWLINE);
            }
        }
        return sql.toString();
    }
    
    /**ใช้ในการสร้างตารางของ Fox*/
    public void createTable() throws Exception{
        if(writer == null)
        {
            fields = new DBFField[8];

            fields[0] = new DBFField();
            fields[0].setName( "an");
            fields[0].setDataType( DBFField.FIELD_TYPE_C);
            fields[0].setFieldLength(9);

            fields[1] = new DBFField();
            fields[1].setName( "oper");
            fields[1].setDataType( DBFField.FIELD_TYPE_C);
            fields[1].setFieldLength(4);

            fields[2] = new DBFField();
            fields[2].setName( "optype");
            fields[2].setDataType( DBFField.FIELD_TYPE_C);
            fields[2].setFieldLength(1);

            fields[3] = new DBFField();
            fields[3].setName( "drop");
            fields[3].setDataType( DBFField.FIELD_TYPE_C);
            fields[3].setFieldLength(6);

            fields[4] = new DBFField();
            fields[4].setName( "datein");
            fields[4].setDataType( DBFField.FIELD_TYPE_D);
            //fields[4].setFieldLength(4);

            fields[5] = new DBFField();
            fields[5].setName( "timein");
            fields[5].setDataType( DBFField.FIELD_TYPE_C);
            fields[5].setFieldLength(4);

            fields[6] = new DBFField();
            fields[6].setName( "dateout");
            fields[6].setDataType( DBFField.FIELD_TYPE_D);
            //fields[6].setFieldLength(8);

            fields[7] = new DBFField();
            fields[7].setName( "timeout");
            fields[7].setDataType( DBFField.FIELD_TYPE_C);
            fields[7].setFieldLength(4); 
      
            writer = new DBFWriter();
            writer.setCharactersetName(Constant.ENCODE_TH);
            writer.setFields( fields);
        }
        
    }
    
    /**
     * ทำการ Insert ข้อมูลลงฐานข้อมูลของ Fox
     * @param vObject เป็น Vector ของ Iop
     * @throws java.lang.Exception 
     */
    public void insertData(Vector vObject) throws Exception{
        createTable();
        if(vObject != null) {
            Iop p = null;
            Object rowData[];
            if(writer == null){
                writer = new DBFWriter();
            }
            for(int i=0;i<vObject.size();i++) {
                p = (Iop)vObject.elementAt(i);
                rowData = new Object[8];              
                rowData[0] = p.an;
                p.oper = subStringICD9(p.oper);
                rowData[1] = p.oper;                
                rowData[2] = p.optype;
                rowData[3] = p.drop;
                if(!("").equals(p.datein))
                {
                    rowData[4] = StringDate.StringDateToDate(p.datein);
                }else{
                    rowData[4] = null;
                }
                p.timein = p.timein.replaceAll(":","");
                rowData[5] = p.timein;
                
                if(!("").equals(p.dateout))
                {
                    rowData[6] = StringDate.StringDateToDate(p.dateout);
                }else{
                    rowData[6] = null;
                }
                p.timeout = p.timeout.replaceAll(":","");
                rowData[7] = p.timeout;          
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
    
    /** เอาจุดเลขทศนิยมของหมายเลข ICD9 ออกจากหมายเลข ICD9
     *@Param originalStr เป็น String ที่ต้องการ substring
     *@Return String เป็นข้อความที่ได้หลังจากการ substring
     *@Date 19/10/2549
     *@Author Pu
     *@Modify Ojika 16/12/2549
     */ 
    public String subStringICD9(String originalStr)
    {
        String ori = "";
        ori = originalStr;
        if(ori.equals(null) || ori == null || ori.trim().equals(""))
        {
            ori = "";
        }
        else if(ori.trim().length() > 2)
        {
            ori = ori.substring(0, 2) + ori.substring(3);    
        }
        else
        {
            ori = originalStr.trim();
        }
        return ori;
    }
    

    public Vector convertData(Vector vData) throws Exception {
        Iop theIop = new Iop();
        Vector vConverData = new Vector();
        String year = "";
        if(vData != null && vData.size() > 0)
        {
            for(int i=0,size = vData.size();i<size;i++)
            {
                theIop = (Iop)vData.get(i);
                if(theIop != null)
                {
                    theIop.oper = subStringICD9(theIop.oper);
                    // ส่งเข้าไปเปลี่ยนรูปแบบวันที่เป็น คศ.
                    theIop.datein = Util.convertYearString(theIop.datein);   
                    theIop.dateout = Util.convertYearString(theIop.dateout);  
                    theIop.timein = theIop.timein.replaceAll(":", "");
                    theIop.timeout = theIop.timeout.replaceAll(":", "");
                    
                    vConverData.add(theIop);
                }
                theIop = null;
            }
        }
        
        return vConverData;
    }

    public String getFileName() {
        return "IOP";
    }
    
}
