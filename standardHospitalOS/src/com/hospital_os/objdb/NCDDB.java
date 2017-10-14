/*
 * NCDDB.java
 *
 * Created on 14 มิถุนายน 2549, 18:00 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.objdb;
import java.util.*;
import java.sql.*;
import java.text.*;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
/**
 *
 * @author amp
 */
public class NCDDB
{
    public ConnectionInf theConnectionInf;
    public NCD dbObj;
    final public String idtable = "290";
    
    /** Creates a new instance of NCDDB */
    public NCDDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new NCD();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_patient_ncd";
        dbObj.pk_field = "t_patient_ncd_id";
        dbObj.patient_id = "t_patient_id";
        dbObj.ncd_group_id = "b_ncd_group_id";
        dbObj.ncd_number = "patient_ncd_number";
        dbObj.staff_record = "patient_ncd_staff_record";
        dbObj.record_date_time = "patient_ncd_record_date_time";
        dbObj.staff_modify = "patient_ncd_staff_modify";
        dbObj.modify_date_time = "patient_ncd_modify_date_time";
        return true;
    }
    
    public int insert(NCD o) throws Exception
    {
        String sql="";
        NCD p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.patient_id        
        + " ,"	+ dbObj.ncd_group_id
        + " ,"	+ dbObj.ncd_number
        + " ,"	+ dbObj.staff_record
        + " ,"	+ dbObj.record_date_time 
        + " ,"	+ dbObj.staff_modify
        + " ,"	+ dbObj.modify_date_time        
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.patient_id
        + "','" + p.ncd_group_id
        + "','" + p.ncd_number
        + "','" + p.staff_record
        + "','" + p.record_date_time
        + "','" + p.staff_modify
        + "','" + p.modify_date_time        
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(NCD o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        NCD p=o;
        String field =""
        + "', " + dbObj.patient_id + "='" + p.patient_id        
        + "', " + dbObj.ncd_group_id + "='" + p.ncd_group_id
        + "', " + dbObj.ncd_number + "='" + p.ncd_number
        + "', " + dbObj.staff_record + "='" + p.staff_record
        + "', " + dbObj.record_date_time + "='" + p.record_date_time
        + "', " + dbObj.staff_modify + "='" + p.staff_modify
        + "', " + dbObj.modify_date_time + "='" + p.modify_date_time        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int delete(NCD p) throws Exception
    {
        String sql="delete from " + dbObj.table 
                + " where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    /**
     *@Author : sumo
     *@date : 28/08/2549
     *@see : ลบข้อมูลการลงทะเบียนผู้ป่วย NCD ตาม Patientid
     *@param : String ของ Patientid
     *@return : int จำนวนรายการที่ลบ
     */
    public int deleteByPatientid(String p) throws Exception
    {
        String sql="delete from " + dbObj.table 
                + " where " + dbObj.patient_id + "='" + p +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectByPatientId(String patientId) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.patient_id
        + " = '" + patientId + "'";
        
        return eQuery(sql);
    }
    
    public NCD selectByNCDNumber(String number) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.ncd_number
        + " = '" + number + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (NCD)v.get(0);
    }
     
    public Vector eQuery(String sql) throws Exception
    {
        NCD p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new NCD();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.patient_id = rs.getString(dbObj.patient_id);
            p.ncd_group_id = rs.getString(dbObj.ncd_group_id);
            p.ncd_number = rs.getString(dbObj.ncd_number);
            p.staff_record = rs.getString(dbObj.staff_record);
            p.record_date_time = rs.getString(dbObj.record_date_time);
            p.staff_modify = rs.getString(dbObj.staff_modify);
            p.modify_date_time = rs.getString(dbObj.modify_date_time);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
