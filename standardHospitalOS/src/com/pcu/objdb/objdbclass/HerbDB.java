/*
 * HerbDB.java
 *
 * Created on 11 ÁÔ¶Ø¹ÒÂ¹ 2548, 15:40 ¹.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*; 
/**
 *
 * @author Administrator 
 */
public class HerbDB {
    
    /** Creates a new instance of HerbDB */
    public HerbDB() {
    }
    
    
    public ConnectionInf theConnectionInf;
    public Herb dbObj;
    final private String idtable = "719";
    
    public HerbDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Herb();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_home_herb";        
        dbObj.pk_field = "t_health_home_herb_id";
        dbObj.home_id ="t_health_home_id";
        dbObj.herb_name = "health_home_herb_name";
        dbObj.herb_use ="health_home_herb_use";
        dbObj.staff_record = "herb_staff_record"; 
        dbObj.staff_modify = "herb_staff_modify";
        dbObj.staff_cancel = "herb_staff_cancel";
        dbObj.record_date_time = "herb_record_date_time";
        dbObj.modify_date_time = "herb_modify_date_time";
        dbObj.cancel_date_time = "herb_cancel_date_time";
        dbObj.herb_active ="herb_active";      
        
        return true;
    }
    
    public int insert(Herb o) throws Exception
    {
        String sql="";
        Herb p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.home_id
        + " ,"  + dbObj.herb_name
        + " ,"	+ dbObj.herb_use
        + " ,"	+ dbObj.staff_record
        + " ,"	+ dbObj.staff_modify
        + " ,"	+ dbObj.staff_cancel
        + " ,"	+ dbObj.record_date_time
        + " ,"	+ dbObj.modify_date_time
        + " ,"	+ dbObj.cancel_date_time
        + " ,"	+ dbObj.herb_active     
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.home_id
        + "','" + p.herb_name
        + "','" + p.herb_use
        + "','" + p.staff_record
        + "','" + p.staff_modify   
        + "','" + p.staff_cancel   
        + "','" + p.record_date_time   
        + "','" + p.modify_date_time   
        + "','" + p.cancel_date_time  
        + "','" + p.herb_active
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(Herb o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Herb p=o;
        String field =""
        + "', " + dbObj.home_id + "='" + p.home_id
        + "', " + dbObj.herb_name + "='" + p.herb_name
        + "', " + dbObj.herb_use + "='" + p.herb_use
        + "', " + dbObj.staff_record + "='" + p.staff_record
        + "', " + dbObj.staff_modify + "='" + p.staff_modify
        + "', " + dbObj.staff_cancel + "='" + p.staff_cancel
        + "', " + dbObj.record_date_time + "='" + p.record_date_time
        + "', " + dbObj.modify_date_time + "='" + p.modify_date_time
        + "', " + dbObj.cancel_date_time + "='" + p.cancel_date_time
        + "', " + dbObj.herb_active + "='" + p.herb_active        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(Herb o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listHerbByHome(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            if(search.trim().length() !=0)
            {
                sql = sql + "(" +  dbObj.home_id
                + " like '%" + search + "%'" +  ") and ";
            }
            sql = sql + dbObj.herb_active + " = '1' order by " + dbObj.record_date_time;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        Herb p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Herb();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.home_id = rs.getString(dbObj.home_id);
            p.herb_name = rs.getString(dbObj.herb_name);
            p.herb_use = rs.getString(dbObj.herb_use);
            p.staff_record = rs.getString(dbObj.staff_record);
            p.staff_modify = rs.getString(dbObj.staff_modify);
            p.staff_cancel = rs.getString(dbObj.staff_cancel);
            p.record_date_time = rs.getString(dbObj.record_date_time);
            p.modify_date_time = rs.getString(dbObj.modify_date_time);
            p.cancel_date_time = rs.getString(dbObj.cancel_date_time);
            p.herb_active = rs.getString(dbObj.herb_active);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
