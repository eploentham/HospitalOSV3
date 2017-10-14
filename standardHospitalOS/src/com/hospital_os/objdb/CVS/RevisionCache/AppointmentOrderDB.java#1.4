/*
 * AppointmentOrderDB.java
 *
 * Created on 23 กุมภาพันธ์ 2549, 14:51 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import java.util.*;
import java.sql.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
/**
 *
 * @author amp
 */
public class AppointmentOrderDB
{
    public ConnectionInf theConnectionInf;
    public AppointmentOrder dbObj;
    final public String idtable = "280";
    
    /** Creates a new instance of AppointmentOrderDB */
    public AppointmentOrderDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new AppointmentOrder();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table="t_patient_appointment_order";
        dbObj.pk_field="t_patient_appointment_order_id";
        dbObj.patient_id="t_patient_id";
        dbObj.appointment_id="t_patient_appointment_id";
        dbObj.item_id="b_item_id";
        dbObj.item_common_name="patient_appointment_order_common_name";
        return true;
    }
    
    public int insert(AppointmentOrder o) throws Exception
    {
        String sql="";
        AppointmentOrder p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.patient_id
        + " ,"	+ dbObj.appointment_id
        + " ,"	+ dbObj.item_id  
        + " ,"	+ dbObj.item_common_name        
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.patient_id
        + "','" + p.appointment_id
        + "','" + p.item_id  
        + "','" + p.item_common_name        
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(AppointmentOrder o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        AppointmentOrder p=o;
        String field =""
        + "', " + dbObj.patient_id + "='" + p.patient_id
        + "', " + dbObj.appointment_id + "='" + p.appointment_id
        + "', " + dbObj.item_id + "='" + p.item_id   
        + "', " + dbObj.item_common_name + "='" + p.item_common_name        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(AppointmentOrder o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    public int deleteByAppid(String aid) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.appointment_id + "='" + aid +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectByPatientAndAppointment(String patient_id, String appointment_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.patient_id
        + " = '" + patient_id + "' and " + dbObj.appointment_id
        + " = '" + appointment_id + "'";
        
        return eQuery(sql);
    }
    public Vector selectByAppointment(String appointment_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.appointment_id + " = '" + appointment_id + "'";
        return eQuery(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        AppointmentOrder p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new AppointmentOrder();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.patient_id = rs.getString(dbObj.patient_id);
            p.appointment_id = rs.getString(dbObj.appointment_id);
            p.item_id = rs.getString(dbObj.item_id);
            p.item_common_name = rs.getString(dbObj.item_common_name);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
