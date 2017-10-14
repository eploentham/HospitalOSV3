/*
 * AppointmentTemplateDB.java
 *
 * Created on 10 สิงหาคม 2549, 13:47 น.
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
public class AppointmentTemplateDB
{
    public ConnectionInf theConnectionInf;
    public AppointmentTemplate dbObj;
    final public String idtable = "293";
    
    /** Creates a new instance of AppointmentTemplateDB */
    public AppointmentTemplateDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new AppointmentTemplate();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table="b_template_appointment";
        dbObj.pk_field="b_template_appointment_id";
        dbObj.template_name="template_appointment_name";
        dbObj.date="template_appointment_date";
        dbObj.date_to="template_appointment_date_to";
        dbObj.time="template_appointment_time";
        dbObj.aptype="template_appointment_aptype";
        dbObj.service_point="template_appointment_service_point";
        dbObj.doctor="template_appointment_doctor";
        dbObj.description="template_appointment_description";
        dbObj.queue_visit_id="template_appointment_queue_visit_id";
        dbObj.appoint_staff_record="template_appointment_staff_record";
        dbObj.appoint_record_date_time="template_appointment_record_date_time";
        dbObj.appoint_staff_update="template_appointment_staff_update";
        dbObj.appoint_update_date_time="template_appointment_update_date_time";
        
        return true;
    }
    
    public int insert(AppointmentTemplate o) throws Exception
    {
        String sql="";
        AppointmentTemplate p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.template_name
        + " ,"	+ dbObj.date  
        + " ,"	+ dbObj.date_to
        + " ,"	+ dbObj.time
        + " ,"	+ dbObj.aptype
        + " ,"	+ dbObj.service_point      
        + " ,"	+ dbObj.doctor
        + " ,"	+ dbObj.description
        + " ,"	+ dbObj.queue_visit_id
        + " ,"	+ dbObj.appoint_staff_record  
        + " ,"	+ dbObj.appoint_record_date_time
        + " ,"	+ dbObj.appoint_staff_update
        + " ,"	+ dbObj.appoint_update_date_time       
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.template_name
        + "','" + p.date  
        + "','" + p.date_to
        + "','" + p.time
        + "','" + p.aptype
        + "','" + p.service_point
        + "','" + p.doctor
        + "','" + p.description
        + "','" + p.queue_visit_id
        + "','" + p.appoint_staff_record
        + "','" + p.appoint_record_date_time
        + "','" + p.appoint_staff_update
        + "','" + p.appoint_update_date_time
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(AppointmentTemplate o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        AppointmentTemplate p=o;
        String field =""
        + "', " + dbObj.template_name + "='" + p.template_name
        + "', " + dbObj.date + "='" + p.date  
        + "', " + dbObj.date_to + "='" + p.date_to        
        + "', " + dbObj.time + "='" + p.time
        + "', " + dbObj.aptype + "='" + p.aptype
        + "', " + dbObj.service_point + "='" + p.service_point
        + "', " + dbObj.doctor + "='" + p.doctor
        + "', " + dbObj.description + "='" + p.description
        + "', " + dbObj.queue_visit_id + "='" + p.queue_visit_id
        + "', " + dbObj.appoint_staff_record + "='" + p.appoint_staff_record
        + "', " + dbObj.appoint_record_date_time + "='" + p.appoint_record_date_time
        + "', " + dbObj.appoint_staff_update + "='" + p.appoint_staff_update
        + "', " + dbObj.appoint_update_date_time + "='" + p.appoint_update_date_time
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(AppointmentTemplate o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    /**
     *@Author : amp
     *@date : 10/08/2549
     *@see : ค้นหาตัวช่วยนัด
     *@param : ชื่อ
     *@return : Vector ตัวช่วยนัด
     */
    public Vector selectAppointmentTemplate(String name) throws Exception
    {
        String sql = "select * from " + dbObj.table;       
        if(!"".equals(name))
        {       
            sql = sql + " where ( UPPER(" +  dbObj.template_name + ") like UPPER('" + name.toUpperCase() + "'))";           
        }
        sql  = sql + " order by " + dbObj.template_name;
        
        return eQuery(sql);
    }
    
    /**
     *@Author : amp
     *@date : 10/08/2549
     *@see : ค้นหาตัวช่วยนัด
     *@param : key_id
     *@return : Vector ตัวช่วยนัด
     */
    public AppointmentTemplate selectAppointmentTemplateByPK(String pk) throws Exception
    {
        String sql = "select * from " + dbObj.table       
                    + " where " + dbObj.pk_field + " = '" + pk + "'";                    
        
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (AppointmentTemplate)v.get(0);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        AppointmentTemplate p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new AppointmentTemplate();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.template_name = rs.getString(dbObj.template_name);
            p.date = rs.getString(dbObj.date);
            p.date_to = rs.getString(dbObj.date_to);
            p.time = rs.getString(dbObj.time);
            p.aptype = rs.getString(dbObj.aptype);
            p.service_point = rs.getString(dbObj.service_point);
            p.doctor = rs.getString(dbObj.doctor);
            p.description = rs.getString(dbObj.description);
            p.queue_visit_id = rs.getString(dbObj.queue_visit_id);
            p.appoint_staff_record = rs.getString(dbObj.appoint_staff_record);
            p.appoint_record_date_time = rs.getString(dbObj.appoint_record_date_time);
            p.appoint_staff_update = rs.getString(dbObj.appoint_staff_update);
            p.appoint_update_date_time = rs.getString(dbObj.appoint_update_date_time);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
