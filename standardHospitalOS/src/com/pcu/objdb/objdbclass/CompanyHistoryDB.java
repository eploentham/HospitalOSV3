/*
 * CompanyHistoryDB.java
 *
 * Created on 11 ÁÔ¶Ø¹ÒÂ¹ 2548, 14:39 ¹.
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
public class CompanyHistoryDB {
    
    /** Creates a new instance of CompanyHistoryDB */
    public CompanyHistoryDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public CompanyHistory dbObj;
    final private String idtable = "701";
    
    public CompanyHistoryDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new CompanyHistory();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_company_history";        
        dbObj.pk_field = "t_health_company_history_id";
        dbObj.company_id ="t_health_company_id";        
        dbObj.register_id ="f_health_register_id";        
        dbObj.company_type_id ="b_health_company_type_id";        
        dbObj.owner_name ="company_owner_name";        
        dbObj.home_number ="company_history_home_number";        
        dbObj.moo ="company_history_moo";        
        dbObj.road ="company_history_road";        
        dbObj.phone ="company_history_phone";        
        dbObj.tambol ="company_history_tambol";        
        dbObj.amphur ="company_history_amphur";        
        dbObj.changwat ="company_history_changwat";   
        dbObj.amount_man = "company_amount_man";   
        dbObj.amount_woman ="company_amount_woman";        
        dbObj.company_co ="company_history_company_co";        
        dbObj.company_co_date_time ="company_history_company_co_date_time";        
        dbObj.standard_type_id ="f_health_community_standard_type_id";        
        dbObj.standard_type_description ="company_history_standard_type_description";   
        dbObj.standard_id = "f_health_community_standard_id";   
        dbObj.time_of_pass ="company_history_time_of_pass";        
        dbObj.date_of_pass ="company_history_date_of_pass";   
        dbObj.period_of_pass = "company_history_period_of_pass";   
        dbObj.in_market ="company_history_in_market";        
        dbObj.company_history_staff_record = "company_history_staff_record"; 
        dbObj.company_history_staff_modify = "company_history_staff_modify";
        dbObj.company_history_record_date_time = "company_history_record_date_time";
        dbObj.company_history_modify_date_time = "company_history_modify_date_time";
        
        return true;
    }
    
    public int insert(CompanyHistory o) throws Exception
    {
        String sql="";
        CompanyHistory p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.company_id 
        + " ,"  + dbObj.register_id                
        + " ,"  + dbObj.company_type_id
        + " ,"	+ dbObj.owner_name
        + " ,"	+ dbObj.home_number    
        + " ,"  + dbObj.moo
        + " ,"  + dbObj.road
        + " ,"	+ dbObj.phone
        + " ,"	+ dbObj.tambol    
        + " ,"  + dbObj.amphur                
        + " ,"  + dbObj.changwat
        + " ,"	+ dbObj.amount_man
        + " ,"	+ dbObj.amount_woman    
        + " ,"  + dbObj.company_co
        + " ,"	+ dbObj.company_co_date_time    
        + " ,"  + dbObj.standard_type_id                
        + " ,"  + dbObj.standard_type_description
        + " ,"	+ dbObj.standard_id
        + " ,"	+ dbObj.time_of_pass    
        + " ,"  + dbObj.date_of_pass
        + " ,"	+ dbObj.period_of_pass    
        + " ,"  + dbObj.in_market                
        + " ,"  + dbObj.company_history_staff_record
        + " ,"	+ dbObj.company_history_staff_modify
        + " ,"	+ dbObj.company_history_record_date_time    
        + " ,"  + dbObj.company_history_modify_date_time
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.company_id
        + "','" + p.register_id
        + "','" + p.company_type_id
        + "','" + p.owner_name
        + "','" + p.home_number
        + "','" + p.moo
        + "','" + p.road
        + "','" + p.phone
        + "','" + p.tambol
        + "','" + p.amphur
        + "','" + p.changwat
        + "','" + p.amount_man
        + "','" + p.amount_woman
        + "','" + p.company_co
        + "','" + p.company_co_date_time
        + "','" + p.standard_type_id
        + "','" + p.standard_type_description
        + "','" + p.standard_id
        + "','" + p.time_of_pass
        + "','" + p.date_of_pass
        + "','" + p.period_of_pass
        + "','" + p.in_market
        + "','" + p.company_history_staff_record
        + "','" + p.company_history_staff_modify
        + "','" + p.company_history_record_date_time
        + "','" + p.company_history_modify_date_time        
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(CompanyHistory o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        CompanyHistory p=o;
        String field =""
        + "', " + dbObj.company_id + "='" + p.company_id
        + "', " + dbObj.register_id + "='" + p.register_id
        + "', " + dbObj.company_type_id + "='" + p.company_type_id
        + "', " + dbObj.owner_name + "='" + p.owner_name
        + "', " + dbObj.home_number + "='" + p.home_number  
        + "', " + dbObj.moo + "='" + p.moo
        + "', " + dbObj.road + "='" + p.road
        + "', " + dbObj.phone + "='" + p.phone
        + "', " + dbObj.tambol + "='" + p.tambol
        + "', " + dbObj.amphur + "='" + p.amphur
        + "', " + dbObj.changwat + "='" + p.changwat
        + "', " + dbObj.amount_man + "='" + p.amount_man
        + "', " + dbObj.amount_woman + "='" + p.amount_woman
        + "', " + dbObj.company_co + "='" + p.company_co
        + "', " + dbObj.company_co_date_time + "='" + p.company_co_date_time  
        + "', " + dbObj.standard_type_id + "='" + p.standard_type_id
        + "', " + dbObj.standard_type_description + "='" + p.standard_type_description
        + "', " + dbObj.standard_id + "='" + p.standard_id
        + "', " + dbObj.time_of_pass + "='" + p.time_of_pass
        + "', " + dbObj.date_of_pass + "='" + p.date_of_pass
        + "', " + dbObj.period_of_pass + "='" + p.period_of_pass  
        + "', " + dbObj.in_market + "='" + p.in_market
        + "', " + dbObj.company_history_staff_record + "='" + p.company_history_staff_record
        + "', " + dbObj.company_history_staff_modify + "='" + p.company_history_staff_modify
        + "', " + dbObj.company_history_record_date_time + "='" + p.company_history_record_date_time
        + "', " + dbObj.company_history_modify_date_time + "='" + p.company_history_modify_date_time
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(CompanyHistory o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listCompanyHistoryByCompanyId(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.company_id + " = '" + search + "' order by "
                + dbObj.company_history_record_date_time + " desc";
        
        return eQuery(sql);
    }
    
    public CompanyHistory readCompanyHistoryByCompanyId(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.company_id + " = '" + search + "' order by "
                + dbObj.company_history_record_date_time + " desc";            
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (CompanyHistory)v.get(0);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        CompanyHistory p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new CompanyHistory();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.company_id = rs.getString(dbObj.company_id);
            p.register_id = rs.getString(dbObj.register_id);
            p.company_type_id = rs.getString(dbObj.company_type_id);
            p.owner_name = rs.getString(dbObj.owner_name);
            p.home_number = rs.getString(dbObj.home_number);  
            p.moo = rs.getString(dbObj.moo);
            p.road = rs.getString(dbObj.road);
            p.phone = rs.getString(dbObj.phone);
            p.tambol = rs.getString(dbObj.tambol);
            p.amphur = rs.getString(dbObj.amphur);  
            p.changwat = rs.getString(dbObj.changwat);
            p.amount_man = rs.getString(dbObj.amount_man);
            p.amount_woman = rs.getString(dbObj.amount_woman);
            p.company_co = rs.getString(dbObj.company_co);
            p.company_co_date_time = rs.getString(dbObj.company_co_date_time);  
            p.standard_type_id = rs.getString(dbObj.standard_type_id);
            p.standard_type_description = rs.getString(dbObj.standard_type_description);
            p.standard_id = rs.getString(dbObj.standard_id);
            p.time_of_pass = rs.getString(dbObj.time_of_pass);
            p.date_of_pass = rs.getString(dbObj.date_of_pass);  
            p.period_of_pass = rs.getString(dbObj.period_of_pass);
            p.in_market = rs.getString(dbObj.in_market);
            p.company_history_staff_record = rs.getString(dbObj.company_history_staff_record);
            p.company_history_staff_modify = rs.getString(dbObj.company_history_staff_modify);
            p.company_history_record_date_time = rs.getString(dbObj.company_history_record_date_time);  
            p.company_history_modify_date_time = rs.getString(dbObj.company_history_modify_date_time);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
