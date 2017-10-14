/*
 * ResourceHistoryDB.java
 *
 * Created on 15 กรกฎาคม 2548, 20:42 น.
 */

package com.pcu.objdb.objdbclass;

import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
/**
 *
 * @author amp
 */
public class ResourceHistoryDB {
    
    /** Creates a new instance of ResourceHistoryDB */
    public ResourceHistoryDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public ResourceHistory dbObj;
    final private String idtable = "762";
    
    public ResourceHistoryDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new ResourceHistory();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_resource_history";        
        dbObj.pk_field = "t_health_resource_history_id";
        dbObj.resource_id ="t_health_resource_id";
        dbObj.resorce_history_owner_id = "resorce_history_owner_id";   
        dbObj.resource_history_owner_description ="resource_history_owner_description";
        dbObj.resorce_history_supervise_id ="resorce_history_supervise_id";
        dbObj.resource_history_supervise_description = "resource_history_supervise_description"; 
        dbObj.resource_history_home_number = "resource_history_home_number";
        dbObj.resource_history_moo = "resource_history_moo";       
        dbObj.resource_history_road ="resource_history_road";
        dbObj.resource_history_phone = "resource_history_phone";        
        dbObj.resource_history_tambol = "resource_history_tambol";        
        dbObj.resource_history_amphur ="resource_history_amphur";
        dbObj.resource_history_changwat = "resource_history_changwat";        
        dbObj.resource_history_state = "resource_history_state";        
        dbObj.f_health_community_standard_type_id ="f_health_community_standard_type_id";
        dbObj.resource_history_standard_type_description = "resource_history_standard_type_description";  
        dbObj.f_health_community_standard_id = "f_health_community_standard_id";
        dbObj.resource_history_time_of_pass = "resource_history_time_of_pass";
        dbObj.resource_history_date_of_pass = "resource_history_date_of_pass";        
        dbObj.resource_history_period_of_pass = "resource_history_period_of_pass";         
        dbObj.resource_history_staff_record = "resource_history_staff_record";       
        dbObj.resource_history_staff_modify ="resource_history_staff_modify";        
        dbObj.resource_history_record_date_time = "resource_history_record_date_time";        
        dbObj.resource_history_modify_date_time = "resource_history_modify_date_time";        
                
        return true;
    }    
    
    public int insert(ResourceHistory o) throws Exception
    {
        String sql="";
        ResourceHistory p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.resource_id
        + " ,"  + dbObj.resorce_history_owner_id
        + " ,"	+ dbObj.resource_history_owner_description
        + " ,"	+ dbObj.resorce_history_supervise_id
        + " ,"	+ dbObj.resource_history_supervise_description  
        + " ,"	+ dbObj.resource_history_home_number
        + " ,"  + dbObj.resource_history_moo
        + " ,"  + dbObj.resource_history_road
        + " ,"  + dbObj.resource_history_phone
        + " ,"	+ dbObj.resource_history_tambol
        + " ,"	+ dbObj.resource_history_amphur    
        + " ,"  + dbObj.resource_history_changwat
        + " ,"  + dbObj.resource_history_state
        + " ,"  + dbObj.f_health_community_standard_type_id
        + " ,"  + dbObj.resource_history_standard_type_description
        + " ,"	+ dbObj.f_health_community_standard_id
        + " ,"	+ dbObj.resource_history_time_of_pass
        + " ,"	+ dbObj.resource_history_date_of_pass    
        + " ,"  + dbObj.resource_history_period_of_pass
        + " ,"  + dbObj.resource_history_staff_record
        + " ,"	+ dbObj.resource_history_staff_modify
        + " ,"	+ dbObj.resource_history_record_date_time    
        + " ,"  + dbObj.resource_history_modify_date_time             
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.resource_id
        + "','" + p.resorce_history_owner_id
        + "','" + p.resource_history_owner_description
        + "','" + p.resorce_history_supervise_id
        + "','" + p.resource_history_supervise_description
        + "','" + p.resource_history_home_number
        + "','" + p.resource_history_moo
        + "','" + p.resource_history_road
        + "','" + p.resource_history_phone
        + "','" + p.resource_history_tambol
        + "','" + p.resource_history_amphur
        + "','" + p.resource_history_changwat
        + "','" + p.resource_history_state
        + "','" + p.f_health_community_standard_type_id
        + "','" + p.resource_history_standard_type_description
        + "','" + p.f_health_community_standard_id        
        + "','" + p.resource_history_time_of_pass
        + "','" + p.resource_history_date_of_pass
        + "','" + p.resource_history_period_of_pass
        + "','" + p.resource_history_staff_record
        + "','" + p.resource_history_staff_modify
        + "','" + p.resource_history_record_date_time
        + "','" + p.resource_history_modify_date_time        
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(ResourceHistory o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        ResourceHistory p=o;
        String field =""
        + "', " + dbObj.resource_id + "='" + p.resource_id
        + "', " + dbObj.resorce_history_owner_id + "='" + p.resorce_history_owner_id
        + "', " + dbObj.resource_history_owner_description + "='" + p.resource_history_owner_description
        + "', " + dbObj.resorce_history_supervise_id + "='" + p.resorce_history_supervise_id
        + "', " + dbObj.resource_history_supervise_description + "='" + p.resource_history_supervise_description
        + "', " + dbObj.resource_history_home_number + "='" + p.resource_history_home_number
        + "', " + dbObj.resource_history_moo + "='" + p.resource_history_moo 
        + "', " + dbObj.resource_history_road + "='" + p.resource_history_road
        + "', " + dbObj.resource_history_phone + "='" + p.resource_history_phone
        + "', " + dbObj.resource_history_tambol + "='" + p.resource_history_tambol
        + "', " + dbObj.resource_history_amphur + "='" + p.resource_history_amphur
        + "', " + dbObj.resource_history_changwat + "='" + p.resource_history_changwat
        + "', " + dbObj.resource_history_state + "='" + p.resource_history_state
        + "', " + dbObj.f_health_community_standard_type_id + "='" + p.f_health_community_standard_type_id
        + "', " + dbObj.resource_history_standard_type_description + "='" + p.resource_history_standard_type_description
        + "', " + dbObj.f_health_community_standard_id + "='" + p.f_health_community_standard_id        
        + "', " + dbObj.resource_history_time_of_pass + "='" + p.resource_history_time_of_pass
        + "', " + dbObj.resource_history_date_of_pass + "='" + p.resource_history_date_of_pass
        + "', " + dbObj.resource_history_period_of_pass + "='" + p.resource_history_period_of_pass
        + "', " + dbObj.resource_history_staff_record + "='" + p.resource_history_staff_record
        + "', " + dbObj.resource_history_staff_modify + "='" + p.resource_history_staff_modify
        + "', " + dbObj.resource_history_record_date_time + "='" + p.resource_history_record_date_time
        + "', " + dbObj.resource_history_modify_date_time + "='" + p.resource_history_modify_date_time        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(ResourceHistory o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listResourceHistoryByResourceId(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.resource_id + " = '" + search + "' order by "
                + dbObj.resource_history_record_date_time + " desc ";            
        
        return eQuery(sql);
    }
    
    public ResourceHistory readResourceHistoryByResourceId(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.resource_id + " = '" + search + "' order by "
                + dbObj.resource_history_record_date_time + " desc";            
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (ResourceHistory)v.get(0);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        ResourceHistory p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ResourceHistory();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.resource_id = rs.getString(dbObj.resource_id);
            p.resorce_history_owner_id = rs.getString(dbObj.resorce_history_owner_id);
            p.resource_history_owner_description = rs.getString(dbObj.resource_history_owner_description);
            p.resorce_history_supervise_id = rs.getString(dbObj.resorce_history_supervise_id);
            p.resource_history_supervise_description = rs.getString(dbObj.resource_history_supervise_description);
            p.resource_history_home_number = rs.getString(dbObj.resource_history_home_number);
            p.resource_history_moo = rs.getString(dbObj.resource_history_moo);
            p.resource_history_road = rs.getString(dbObj.resource_history_road);
            p.resource_history_phone = rs.getString(dbObj.resource_history_phone);
            p.resource_history_tambol = rs.getString(dbObj.resource_history_tambol);
            p.resource_history_amphur = rs.getString(dbObj.resource_history_amphur);
            p.resource_history_changwat = rs.getString(dbObj.resource_history_changwat);
            p.resource_history_state = rs.getString(dbObj.resource_history_state);
            p.f_health_community_standard_type_id = rs.getString(dbObj.f_health_community_standard_type_id);  
            p.resource_history_standard_type_description = rs.getString(dbObj.resource_history_standard_type_description);
            p.f_health_community_standard_id = rs.getString(dbObj.f_health_community_standard_id);
            p.resource_history_time_of_pass = rs.getString(dbObj.resource_history_time_of_pass);
            p.resource_history_date_of_pass = rs.getString(dbObj.resource_history_date_of_pass);
            p.resource_history_period_of_pass = rs.getString(dbObj.resource_history_period_of_pass);
            p.resource_history_staff_record = rs.getString(dbObj.resource_history_staff_record);  
            p.resource_history_staff_modify = rs.getString(dbObj.resource_history_staff_modify);
            p.resource_history_record_date_time = rs.getString(dbObj.resource_history_record_date_time);
            p.resource_history_modify_date_time = rs.getString(dbObj.resource_history_modify_date_time);                      
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
