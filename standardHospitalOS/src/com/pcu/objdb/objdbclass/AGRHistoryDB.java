/*
 * AGRHistoryDB.java
 *
 * Created on 25 กรกฎาคม 2548, 15:05 น.
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
public class AGRHistoryDB
{
    
    /** Creates a new instance of AGRHistoryDB */
    public AGRHistoryDB()
    {
    }
    
    public ConnectionInf theConnectionInf;
    public AGRHistory dbObj;
    final private String idtable = "767";
    
    public AGRHistoryDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new AGRHistory();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_agr_history";        
        dbObj.pk_field = "t_health_agr_history_id";
        dbObj.agr_id ="t_health_agr_id";        
        dbObj.register_id ="agr_history_register";        
        dbObj.agr_history_group ="agr_history_group";        
        dbObj.agr_history_type ="agr_history_type";  
        dbObj.agr_history_start_year ="agr_history_start_year";
        dbObj.agr_history_home_number ="agr_history_home_number";        
        dbObj.agr_history_moo ="agr_history_moo";        
        dbObj.agr_history_road ="agr_history_road";        
        dbObj.agr_history_phone ="agr_history_phone";        
        dbObj.agr_history_tambol ="agr_history_tambol";        
        dbObj.agr_history_amphur ="agr_history_amphur";        
        dbObj.agr_history_changwat ="agr_history_changwat";   
        dbObj.agr_history_chairman_name = "agr_history_chairman_name";   
        dbObj.agr_history_man_member ="agr_history_man_member";        
        dbObj.agr_history_woman_member ="agr_history_woman_member";        
        dbObj.agr_history_capital ="agr_history_capital";        
        dbObj.standard_type_id ="agr_history_standard_type";        
        dbObj.standard_type_description ="agr_history_standard_type_description";   
        dbObj.standard_id = "agr_history_standard";   
        dbObj.time_of_pass ="agr_history_time_of_pass";        
        dbObj.date_of_pass ="agr_history_date_of_pass";   
        dbObj.period_of_pass = "agr_history_period_of_pass";   
        dbObj.agr_history_staff_record = "agr_history_staff_record"; 
        dbObj.agr_history_staff_modify = "agr_history_staff_modify";
        dbObj.agr_history_record_date_time = "agr_history_record_date_time";
        dbObj.agr_history_modify_date_time = "agr_history_modify_date_time";
        
        return true;        
    }
    
    public int insert(AGRHistory o) throws Exception
    {
        String sql="";
        AGRHistory p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.agr_id 
        + " ,"  + dbObj.register_id                
        + " ,"  + dbObj.agr_history_group
        + " ,"	+ dbObj.agr_history_type
        + " ,"	+ dbObj.agr_history_start_year    
        + " ,"  + dbObj.agr_history_home_number
        + " ,"  + dbObj.agr_history_moo
        + " ,"	+ dbObj.agr_history_road
        + " ,"	+ dbObj.agr_history_phone    
        + " ,"  + dbObj.agr_history_tambol                
        + " ,"  + dbObj.agr_history_amphur
        + " ,"	+ dbObj.agr_history_changwat
        + " ,"	+ dbObj.agr_history_chairman_name    
        + " ,"  + dbObj.agr_history_man_member
        + " ,"	+ dbObj.agr_history_woman_member   
        + " ,"	+ dbObj.agr_history_capital   
        + " ,"  + dbObj.standard_type_id                
        + " ,"  + dbObj.standard_type_description
        + " ,"	+ dbObj.standard_id
        + " ,"	+ dbObj.time_of_pass    
        + " ,"  + dbObj.date_of_pass
        + " ,"	+ dbObj.period_of_pass    
        + " ,"  + dbObj.agr_history_staff_record
        + " ,"	+ dbObj.agr_history_staff_modify
        + " ,"	+ dbObj.agr_history_record_date_time    
        + " ,"  + dbObj.agr_history_modify_date_time
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.agr_id
        + "','" + p.register_id
        + "','" + p.agr_history_group
        + "','" + p.agr_history_type
        + "','" + p.agr_history_start_year
        + "','" + p.agr_history_home_number
        + "','" + p.agr_history_moo
        + "','" + p.agr_history_road
        + "','" + p.agr_history_phone
        + "','" + p.agr_history_tambol
        + "','" + p.agr_history_amphur
        + "','" + p.agr_history_changwat
        + "','" + p.agr_history_chairman_name
        + "','" + p.agr_history_man_member
        + "','" + p.agr_history_woman_member
        + "','" + p.agr_history_capital
        + "','" + p.standard_type_id
        + "','" + p.standard_type_description
        + "','" + p.standard_id
        + "','" + p.time_of_pass
        + "','" + p.date_of_pass
        + "','" + p.period_of_pass
        + "','" + p.agr_history_staff_record
        + "','" + p.agr_history_staff_modify
        + "','" + p.agr_history_record_date_time
        + "','" + p.agr_history_modify_date_time        
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(AGRHistory o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        AGRHistory p=o;
        String field =""
        + "', " + dbObj.agr_id + "='" + p.agr_id
        + "', " + dbObj.register_id + "='" + p.register_id
        + "', " + dbObj.agr_history_group + "='" + p.agr_history_group
        + "', " + dbObj.agr_history_type + "='" + p.agr_history_type
        + "', " + dbObj.agr_history_start_year + "='" + p.agr_history_start_year
        + "', " + dbObj.agr_history_home_number + "='" + p.agr_history_home_number
        + "', " + dbObj.agr_history_moo + "='" + p.agr_history_moo
        + "', " + dbObj.agr_history_road + "='" + p.agr_history_road
        + "', " + dbObj.agr_history_phone + "='" + p.agr_history_phone
        + "', " + dbObj.agr_history_tambol + "='" + p.agr_history_tambol
        + "', " + dbObj.agr_history_amphur + "='" + p.agr_history_amphur
        + "', " + dbObj.agr_history_changwat + "='" + p.agr_history_changwat
        + "', " + dbObj.agr_history_chairman_name + "='" + p.agr_history_chairman_name
        + "', " + dbObj.agr_history_man_member + "='" + p.agr_history_man_member
        + "', " + dbObj.agr_history_woman_member + "='" + p.agr_history_woman_member
        + "', " + dbObj.agr_history_capital + "='" + p.agr_history_capital  
        + "', " + dbObj.standard_type_id + "='" + p.standard_type_id
        + "', " + dbObj.standard_type_description + "='" + p.standard_type_description
        + "', " + dbObj.standard_id + "='" + p.standard_id
        + "', " + dbObj.time_of_pass + "='" + p.time_of_pass
        + "', " + dbObj.date_of_pass + "='" + p.date_of_pass
        + "', " + dbObj.period_of_pass + "='" + p.period_of_pass  
        + "', " + dbObj.agr_history_staff_record + "='" + p.agr_history_staff_record
        + "', " + dbObj.agr_history_staff_modify + "='" + p.agr_history_staff_modify
        + "', " + dbObj.agr_history_record_date_time + "='" + p.agr_history_record_date_time
        + "', " + dbObj.agr_history_modify_date_time + "='" + p.agr_history_modify_date_time
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(AGRHistory o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listAGRHistoryByAGRId(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.agr_id + " = '" + search + "' order by "
                + dbObj.agr_history_record_date_time + " desc";            
        
        return eQuery(sql);
    }
    
    public AGRHistory readAGRHistoryByAGRId(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.agr_id + " = '" + search + "' order by "
                + dbObj.agr_history_record_date_time + " desc";            
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (AGRHistory)v.get(0);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        AGRHistory p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new AGRHistory();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.agr_id = rs.getString(dbObj.agr_id);
            p.register_id = rs.getString(dbObj.register_id);
            p.agr_history_group = rs.getString(dbObj.agr_history_group);
            p.agr_history_type = rs.getString(dbObj.agr_history_type); 
            p.agr_history_start_year = rs.getString(dbObj.agr_history_start_year); 
            p.agr_history_home_number = rs.getString(dbObj.agr_history_home_number); 
            p.agr_history_moo = rs.getString(dbObj.agr_history_moo);
            p.agr_history_road = rs.getString(dbObj.agr_history_road);
            p.agr_history_phone = rs.getString(dbObj.agr_history_phone);
            p.agr_history_tambol = rs.getString(dbObj.agr_history_tambol);
            p.agr_history_amphur = rs.getString(dbObj.agr_history_amphur);  
            p.agr_history_changwat = rs.getString(dbObj.agr_history_changwat);
            p.agr_history_chairman_name = rs.getString(dbObj.agr_history_chairman_name);
            p.agr_history_man_member = rs.getString(dbObj.agr_history_man_member);
            p.agr_history_woman_member = rs.getString(dbObj.agr_history_woman_member);
            p.agr_history_capital = rs.getString(dbObj.agr_history_capital);  
            p.standard_type_id = rs.getString(dbObj.standard_type_id);
            p.standard_type_description = rs.getString(dbObj.standard_type_description);
            p.standard_id = rs.getString(dbObj.standard_id);
            p.time_of_pass = rs.getString(dbObj.time_of_pass);
            p.date_of_pass = rs.getString(dbObj.date_of_pass);  
            p.period_of_pass = rs.getString(dbObj.period_of_pass);
            p.agr_history_staff_record = rs.getString(dbObj.agr_history_staff_record);
            p.agr_history_staff_modify = rs.getString(dbObj.agr_history_staff_modify);
            p.agr_history_record_date_time = rs.getString(dbObj.agr_history_record_date_time);  
            p.agr_history_modify_date_time = rs.getString(dbObj.agr_history_modify_date_time);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
