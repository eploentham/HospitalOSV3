/*
 * MarketHistoryDB.java
 *
 * Created on 12 กรกฎาคม 2548, 11:27 น.
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
public class MarketHistoryDB {
    
    /** Creates a new instance of MarketHistoryDB */
    public MarketHistoryDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public MarketHistory dbObj;
    final private String idtable = "752";
    
    public MarketHistoryDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new MarketHistory();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_market_history";        
        dbObj.pk_field = "t_health_market_history_id";
        dbObj.market_id ="t_health_market_id";
        dbObj.register_id = "b_health_register_id";   
        dbObj.market_type_id ="b_health_market_type_id";
        dbObj.market_type_description ="market_history_market_type_description";
        dbObj.market_type_lock_id = "b_health_market_type_lock_id"; 
        dbObj.market_type_lock_description = "market_history_market_type_lock_description";
        dbObj.market_history_home_number = "market_history_home_number";       
        dbObj.market_history_moo ="market_history_moo";
        dbObj.market_history_road = "market_history_road";        
        dbObj.market_history_phone = "market_history_phone";        
        dbObj.market_history_tambol ="market_history_tambol";
        dbObj.market_history_amphur = "market_history_amphur";        
        dbObj.market_history_changwat = "market_history_changwat";        
        dbObj.market_history_owner_id ="f_health_community_resorce_owner_id";
        dbObj.market_history_owner_description = "market_history_owner_description";  
        dbObj.market_history_market_co = "market_history_market_co";
        dbObj.market_history_market_co_date_time = "market_history_market_co_date_time";
        dbObj.market_history_standard_type_id = "f_health_community_standard_type_id";        
        dbObj.market_history_standard_type_description = "market_history_standard_type_description";         
        dbObj.market_history_standard_id = "f_health_community_standard_id";       
        dbObj.market_history_time_of_pass ="market_history_time_of_pass";        
        dbObj.market_history_date_of_pass = "market_history_date_of_pass";        
        dbObj.market_history_period_of_pass = "market_history_period_of_pass";        
        dbObj.market_history_in_market = "market_history_in_market";        
        dbObj.market_history_staff_record = "market_history_staff_record";        
        dbObj.market_history_staff_modify = "market_history_staff_modify";        
        dbObj.market_history_record_date_time = "market_history_record_date_time";        
        dbObj.market_history_modify_date_time = "market_history_modify_date_time";
        
        return true;
    }
    
    public int insert(MarketHistory o) throws Exception
    {
        String sql="";
        MarketHistory p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.market_id
        + " ,"  + dbObj.register_id
        + " ,"	+ dbObj.market_type_id
        + " ,"	+ dbObj.market_type_description
        + " ,"	+ dbObj.market_type_lock_id  
        + " ,"	+ dbObj.market_type_lock_description
        + " ,"  + dbObj.market_history_home_number
        + " ,"  + dbObj.market_history_moo
        + " ,"  + dbObj.market_history_road
        + " ,"	+ dbObj.market_history_phone
        + " ,"	+ dbObj.market_history_tambol    
        + " ,"  + dbObj.market_history_amphur
        + " ,"  + dbObj.market_history_changwat
        + " ,"  + dbObj.market_history_owner_id
        + " ,"	+ dbObj.market_history_owner_description
        + " ,"	+ dbObj.market_history_market_co
        + " ,"	+ dbObj.market_history_market_co_date_time    
        + " ,"  + dbObj.market_history_standard_type_id
        + " ,"  + dbObj.market_history_standard_type_description
        + " ,"	+ dbObj.market_history_standard_id
        + " ,"	+ dbObj.market_history_time_of_pass    
        + " ,"  + dbObj.market_history_date_of_pass
        + " ,"  + dbObj.market_history_period_of_pass
        + " ,"  + dbObj.market_history_in_market
        + " ,"	+ dbObj.market_history_staff_record
        + " ,"	+ dbObj.market_history_staff_modify
        + " ,"	+ dbObj.market_history_record_date_time    
        + " ,"  + dbObj.market_history_modify_date_time
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.market_id
        + "','" + p.register_id
        + "','" + p.market_type_id
        + "','" + p.market_type_description
        + "','" + p.market_type_lock_id
        + "','" + p.market_type_lock_description
        + "','" + p.market_history_home_number
        + "','" + p.market_history_moo
        + "','" + p.market_history_road
        + "','" + p.market_history_phone
        + "','" + p.market_history_tambol
        + "','" + p.market_history_amphur
        + "','" + p.market_history_changwat
        + "','" + p.market_history_owner_id
        + "','" + p.market_history_owner_description
        + "','" + p.market_history_market_co        
        + "','" + p.market_history_market_co_date_time
        + "','" + p.market_history_standard_type_id
        + "','" + p.market_history_standard_type_description
        + "','" + p.market_history_standard_id
        + "','" + p.market_history_time_of_pass
        + "','" + p.market_history_date_of_pass
        + "','" + p.market_history_period_of_pass
        + "','" + p.market_history_in_market
        + "','" + p.market_history_staff_record
        + "','" + p.market_history_staff_modify        
        + "','" + p.market_history_record_date_time
        + "','" + p.market_history_modify_date_time
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(MarketHistory o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        MarketHistory p=o;
        String field =""
        + "', " + dbObj.market_id + "='" + p.market_id
        + "', " + dbObj.register_id + "='" + p.register_id
        + "', " + dbObj.market_type_id + "='" + p.market_type_id
        + "', " + dbObj.market_type_description + "='" + p.market_type_description
        + "', " + dbObj.market_type_lock_id + "='" + p.market_type_lock_id
        + "', " + dbObj.market_type_lock_description + "='" + p.market_type_lock_description
        + "', " + dbObj.market_history_home_number + "='" + p.market_history_home_number 
        + "', " + dbObj.market_history_moo + "='" + p.market_history_moo
        + "', " + dbObj.market_history_road + "='" + p.market_history_road
        + "', " + dbObj.market_history_phone + "='" + p.market_history_phone
        + "', " + dbObj.market_history_tambol + "='" + p.market_history_tambol
        + "', " + dbObj.market_history_amphur + "='" + p.market_history_amphur
        + "', " + dbObj.market_history_changwat + "='" + p.market_history_changwat
        + "', " + dbObj.market_history_owner_id + "='" + p.market_history_owner_id
        + "', " + dbObj.market_history_owner_description + "='" + p.market_history_owner_description
        + "', " + dbObj.market_history_market_co + "='" + p.market_history_market_co        
        + "', " + dbObj.market_history_market_co_date_time + "='" + p.market_history_market_co_date_time
        + "', " + dbObj.market_history_standard_type_id + "='" + p.market_history_standard_type_id
        + "', " + dbObj.market_history_standard_type_description + "='" + p.market_history_standard_type_description
        + "', " + dbObj.market_history_standard_id + "='" + p.market_history_standard_id
        + "', " + dbObj.market_history_time_of_pass + "='" + p.market_history_time_of_pass
        + "', " + dbObj.market_history_date_of_pass + "='" + p.market_history_date_of_pass
        + "', " + dbObj.market_history_period_of_pass + "='" + p.market_history_period_of_pass
        + "', " + dbObj.market_history_in_market + "='" + p.market_history_in_market
        + "', " + dbObj.market_history_staff_record + "='" + p.market_history_staff_record
        + "', " + dbObj.market_history_staff_modify + "='" + p.market_history_staff_modify        
        + "', " + dbObj.market_history_record_date_time + "='" + p.market_history_record_date_time
        + "', " + dbObj.market_history_modify_date_time + "='" + p.market_history_modify_date_time
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(MarketHistory o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listMarketHistoryByMarketId(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.market_id + " = '" + search + "' order by "
                + dbObj.market_history_record_date_time + " desc ";            
        
        return eQuery(sql);
    }
    
    public MarketHistory readMarketHistoryByMarketId(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.market_id + " = '" + search + "' order by "
                + dbObj.market_history_record_date_time + " desc";            
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (MarketHistory)v.get(0);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        MarketHistory p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new MarketHistory();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.market_id = rs.getString(dbObj.market_id);
            p.register_id = rs.getString(dbObj.register_id);
            p.market_type_id = rs.getString(dbObj.market_type_id);
            p.market_type_description = rs.getString(dbObj.market_type_description);
            p.market_type_lock_id = rs.getString(dbObj.market_type_lock_id);
            p.market_type_lock_description = rs.getString(dbObj.market_type_lock_description);
            p.market_history_home_number = rs.getString(dbObj.market_history_home_number);
            p.market_history_moo = rs.getString(dbObj.market_history_moo);
            p.market_history_road = rs.getString(dbObj.market_history_road);
            p.market_history_phone = rs.getString(dbObj.market_history_phone);
            p.market_history_tambol = rs.getString(dbObj.market_history_tambol);
            p.market_history_amphur = rs.getString(dbObj.market_history_amphur);
            p.market_history_changwat = rs.getString(dbObj.market_history_changwat);
            p.market_history_owner_id = rs.getString(dbObj.market_history_owner_id);  
            p.market_history_owner_description = rs.getString(dbObj.market_history_owner_description);
            p.market_history_market_co = rs.getString(dbObj.market_history_market_co);
            p.market_history_market_co_date_time = rs.getString(dbObj.market_history_market_co_date_time);
            p.market_history_standard_type_id = rs.getString(dbObj.market_history_standard_type_id);
            p.market_history_standard_type_description = rs.getString(dbObj.market_history_standard_type_description);
            p.market_history_standard_id = rs.getString(dbObj.market_history_standard_id);  
            p.market_history_time_of_pass = rs.getString(dbObj.market_history_time_of_pass);
            p.market_history_date_of_pass = rs.getString(dbObj.market_history_date_of_pass);
            p.market_history_period_of_pass = rs.getString(dbObj.market_history_period_of_pass);
            p.market_history_in_market = rs.getString(dbObj.market_history_in_market);
            p.market_history_staff_record = rs.getString(dbObj.market_history_staff_record);
            p.market_history_staff_modify = rs.getString(dbObj.market_history_staff_modify);  
            p.market_history_record_date_time = rs.getString(dbObj.market_history_record_date_time);
            p.market_history_modify_date_time = rs.getString(dbObj.market_history_modify_date_time);            
            list.add(p);
        }
        rs.close();
        return list;
    }
       
}
