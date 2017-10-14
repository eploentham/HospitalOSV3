/*
 * PressureDB.java
 *
 * Created on 5 เมษายน 2549, 17:19 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.healthy.objdb;
import com.healthy.object.*;
import com.healthy.object.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author hospitalos5
 */
public class PressureDB {
    
    Pressure dbObj;
    public ConnectionInf theConnectionInf;
    final private String idtable = "ht005";
    /** Creates a new instance of PressureDB */
    public PressureDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new Pressure();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_pressure";
        
        dbObj.pk_field = "t_health_pressure_id";
        //dbObj.pressure_id = "t_health_pressure_id";
        dbObj.idy = "pressure_idy";
        dbObj.date_serv = "pressure_date_serv";
        dbObj.systolic = "pressure_systolic";
        dbObj.diastolic = "pressure_diastolic";
        dbObj.family_id = "t_health_family_id";
        dbObj.result = "pressure_result";
        dbObj.record_time = "pressure_record_time";
        dbObj.modify_time = "pressure_modify_time";
        dbObj.cancel_time = "pressure_cancel_time";
        dbObj.staff_record = "pressure_staff_record";
        dbObj.staff_modify = "pressure_staff_modify";
        dbObj.staff_cancel = "pressure_staff_cancel";
        dbObj.active = "pressure_active";
     
        return true;
    }
     
     public int insert(Pressure o) throws Exception
    {
        String sql="";
        Pressure p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        //+ " ,"  + dbObj.pressure_id
        + " ,"  + dbObj.idy
        + " ,"  + dbObj.date_serv
        + " ,"  + dbObj.systolic
        + " ,"  + dbObj.diastolic
        + " ,"  + dbObj.family_id
        + " ,"  + dbObj.result
        + " ,"  + dbObj.record_time
        + " ,"  + dbObj.modify_time
        + " ,"  + dbObj.cancel_time
        + " ,"  + dbObj.staff_record
        + " ,"  + dbObj.staff_modify
        + " ,"  + dbObj.staff_cancel
        + " ,"  + dbObj.active
        + " ) values ('"
        + p.getObjectId()
        //+ "','" + p.pressure_id
        + "','" + p.idy
        + "','" + p.date_serv
        + "','" + p.systolic
        + "','" + p.diastolic
        + "','" + p.family_id
        + "','" + p.result
        + "','" + p.record_time
        + "','" + p.modify_time
        + "','" + p.cancel_time
        + "','" + p.staff_record
        + "','" + p.staff_modify
        + "','" + p.staff_cancel
        + "','" + p.active             
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
     }
     
    public int update(Pressure o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Pressure p=o;
        String field =""
        //+ "', " + dbObj.pressure_id + "='" + p.pressure_id
        + "', " + dbObj.idy + "='" + p.idy
        + "', " + dbObj.date_serv + "='" + p.date_serv
        + "', " + dbObj.systolic + "='" + p.systolic
        + "', " + dbObj.diastolic + "='" + p.diastolic
        + "', " + dbObj.family_id + "='" + p.family_id
        + "', " + dbObj.result + "='" + p.result
        + "', " + dbObj.record_time + "='" + p.record_time
        + "', " + dbObj.modify_time + "='" + p.modify_time
        + "', " + dbObj.cancel_time + "='" + p.cancel_time
        + "', " + dbObj.staff_record + "='" + p.staff_record
        + "', " + dbObj.staff_modify + "='" + p.staff_modify
        + "', " + dbObj.staff_cancel + "='" + p.staff_cancel
        + "', " + dbObj.active + "='" + p.active
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        Pressure p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Pressure();
            p.setObjectId(rs.getString(dbObj.pk_field));
            //p.pressure_id = rs.getString(dbObj.pressure_id);
            p.idy = rs.getString(dbObj.idy);
            p.date_serv = rs.getString(dbObj.date_serv);
            p.systolic = rs.getString(dbObj.systolic);
            p.diastolic = rs.getString(dbObj.diastolic);
            p.family_id = rs.getString(dbObj.family_id);
            p.result = rs.getString(dbObj.result);
            p.record_time = rs.getString(dbObj.record_time);
            p.modify_time = rs.getString(dbObj.modify_time);
            p.cancel_time = rs.getString(dbObj.cancel_time);
            p.staff_record = rs.getString(dbObj.staff_record);
            p.staff_modify = rs.getString(dbObj.staff_modify);
            p.staff_cancel = rs.getString(dbObj.staff_cancel);
            p.active = rs.getString(dbObj.active);
            list.add(p);
        }
        rs.close();
        return list;
    }
     
     public int delete(Pressure o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectByFamily(String family_id) throws Exception{
        String sql="select * from " + dbObj.table
            + " where " + dbObj.family_id + " like '" + family_id + "'";
            //+ " order by " + dbObj.getObjectId() + " desc " ;
	return eQuery(sql);
    }
    
    public XPersistent getInstant() throws Exception {
        Pressure b = new Pressure();
        return b;
    }
}
