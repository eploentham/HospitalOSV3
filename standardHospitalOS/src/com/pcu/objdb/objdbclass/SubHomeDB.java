/*
 * SubHome.java
 *
 * Created on 11 ÁÔ¶Ø¹ÒÂ¹ 2548, 14:33 ¹.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
/**
 *
 * @author jao
 */
public class SubHomeDB {
    
    /** Creates a new instance of SubHome */
    public SubHomeDB() {
    }
    public ConnectionInf theConnectionInf;
    public SubHome dbObj;
    final private String idtable = "715";
    
    public SubHomeDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new SubHome();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_sub_home";        
        dbObj.pk_field = "t_health_sub_home_id";
        dbObj.home_id ="t_health_home_id";          
        dbObj.record_date_time = "sub_home_record_date_time";   
        dbObj.staff_record = "health_sub_home_staff_record";   
        dbObj.staff_modify = "health_sub_home_staff_modify";   
        dbObj.staff_cancel = "health_sub_home_staff_cancel";   
        dbObj.modify_date_time = "sub_home_modify_date_time";
        dbObj.cancel_date_time = "sub_home_cancel_date_time";
        dbObj.sub_home_active = "sub_home_active"; 
        
        return true;
    }
    
    public int insert(SubHome o) throws Exception
    {
        String sql="";
        SubHome p=o;
        p.generateOID(idtable);
        //p.setObjectId(idtable + p.hospital_site + String.valueOf(System.currentTimeMillis()));
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.home_id        
        + " ,"	+ dbObj.record_date_time
        + " ,"	+ dbObj.staff_record
        + " ,"	+ dbObj.staff_modify
        + " ,"	+ dbObj.staff_cancel
        + " ,"	+ dbObj.modify_date_time
        + " ,"	+ dbObj.cancel_date_time
        + " ,"	+ dbObj.sub_home_active
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.home_id
        + "','" + p.record_date_time
        + "','" + p.staff_record
        + "','" + p.staff_modify
        + "','" + p.staff_cancel
        + "','" + p.modify_date_time
        + "','" + p.cancel_date_time
        + "','" + p.sub_home_active 
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(SubHome o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        SubHome p=o;
        String field =""
        + "', " + dbObj.home_id + "='" + p.home_id
        + "', " + dbObj.record_date_time + "='" + p.record_date_time 
        + "', " + dbObj.staff_record + "='" + p.staff_record 
        + "', " + dbObj.staff_modify + "='" + p.staff_modify 
        + "', " + dbObj.staff_cancel + "='" + p.staff_cancel 
        + "', " + dbObj.modify_date_time + "='" + p.modify_date_time
        + "', " + dbObj.cancel_date_time + "='" + p.cancel_date_time
        + "', " + dbObj.sub_home_active + "='" + p.sub_home_active
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(SubHome o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    public Vector listSubHomeByDate(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            if(search.trim().length() !=0)
            {
                sql = sql + "(" +  dbObj.home_id
                + " like '%" + search + "%'" +  ") and ";
            }
            sql = sql + dbObj.sub_home_active + " = '1' order by " + dbObj.modify_date_time + " desc ";
        
        return eQuery(sql);
    }
    public Vector eQuery(String sql) throws Exception
    {
        SubHome p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new SubHome();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.home_id = rs.getString(dbObj.home_id);            
            p.record_date_time = rs.getString(dbObj.record_date_time);
            p.staff_record = rs.getString(dbObj.staff_record);
            p.staff_modify = rs.getString(dbObj.staff_modify);
            p.staff_cancel = rs.getString(dbObj.staff_cancel);
            p.modify_date_time = rs.getString(dbObj.modify_date_time);
            p.cancel_date_time = rs.getString(dbObj.cancel_date_time);
            p.sub_home_active = rs.getString(dbObj.sub_home_active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
