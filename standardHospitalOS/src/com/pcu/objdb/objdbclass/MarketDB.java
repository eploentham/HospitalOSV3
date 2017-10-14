/*
 * MarketDB.java
 *
 * Created on 12 กรกฎาคม 2548, 11:03 น.
 */

package com.pcu.objdb.objdbclass;

import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.pcu.object.*;
/**
 *
 * @author amp
 */
public class MarketDB {
    
    /** Creates a new instance of MarketDB */
    public MarketDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public Market dbObj;
    final private String idtable = "751";
    
    public MarketDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Market();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_market";        
        dbObj.pk_field = "t_health_market_id";
        dbObj.village_id ="t_health_village_id";
        dbObj.market_number = "market_number";   
        dbObj.market_name ="market_name";
        dbObj.market_staff_record ="market_staff_record";
        dbObj.market_staff_modify = "market_staff_modify";   
        dbObj.market_staff_cancel ="market_staff_cancel";
        dbObj.market_record_date_time ="market_record_date_time";
        dbObj.market_modify_date_time = "market_modify_date_time";   
        dbObj.market_cancel_date_time ="market_cancel_date_time";
        dbObj.market_close = "market_close";   
        dbObj.market_close_date_time ="market_close_date_time";
        dbObj.market_close_note ="market_close_note";
        dbObj.market_active = "market_active";  
        
        return true;
    }
    
    public int insert(Market o) throws Exception
    {
        String sql="";
        Market p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.village_id
        + " ,"  + dbObj.market_number
        + " ,"	+ dbObj.market_name
        + " ,"  + dbObj.market_staff_record
        + " ,"	+ dbObj.market_staff_modify
        + " ,"	+ dbObj.market_staff_cancel  
        + " ,"  + dbObj.market_record_date_time
        + " ,"  + dbObj.market_modify_date_time
        + " ,"	+ dbObj.market_cancel_date_time
        + " ,"  + dbObj.market_close
        + " ,"	+ dbObj.market_close_date_time
        + " ,"	+ dbObj.market_close_note
        + " ,"	+ dbObj.market_active  
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.village_id
        + "','" + p.market_number
        + "','" + p.market_name
        + "','" + p.market_staff_record
        + "','" + p.market_staff_modify
        + "','" + p.market_staff_cancel
        + "','" + p.market_record_date_time
        + "','" + p.market_modify_date_time
        + "','" + p.market_cancel_date_time
        + "','" + p.market_close
        + "','" + p.market_close_date_time
        + "','" + p.market_close_note
        + "','" + p.market_active
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(Market o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Market p=o;
        String field =""
        + "', " + dbObj.village_id + "='" + p.village_id
        + "', " + dbObj.market_number + "='" + p.market_number
        + "', " + dbObj.market_name + "='" + p.market_name
        + "', " + dbObj.market_staff_record + "='" + p.market_staff_record
        + "', " + dbObj.market_staff_modify + "='" + p.market_staff_modify
        + "', " + dbObj.market_staff_cancel + "='" + p.market_staff_cancel        
        + "', " + dbObj.market_record_date_time + "='" + p.market_record_date_time
        + "', " + dbObj.market_modify_date_time + "='" + p.market_modify_date_time
        + "', " + dbObj.market_cancel_date_time + "='" + p.market_cancel_date_time
        + "', " + dbObj.market_close + "='" + p.market_close
        + "', " + dbObj.market_close_date_time + "='" + p.market_close_date_time
        + "', " + dbObj.market_close_note + "='" + p.market_close_note
        + "', " + dbObj.market_active + "='" + p.market_active        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int updateMarketToActiveZeroByVillageId(String villageId) throws Exception
    {
        String sql="update " + dbObj.table + " set";
        String field =""        
        + "', " + dbObj.market_active + " = '0'"     
        + " where " + dbObj.village_id + " = '" + villageId +"'";
        sql = sql+field.substring(2);
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(Market o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectByNumberVid(String search,String villageId) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                +  dbObj.market_number + " = '" + search + "' and "
                + dbObj.village_id + " = '" + villageId + "' and "
                + dbObj.market_active + " = '1' order by " + dbObj.market_number;     
        return eQuery(sql);
    }
    public Vector listMarketByNameOrNumber(String search,String villageId) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            if(search.trim().length() !=0)
            {
                sql = sql + "(" +  dbObj.market_number
                + " like '%" + search + "%'" + " or "
                + dbObj.market_name+ " like '%" + search + "%'" + ") and ";
            }            
            if(!villageId.equals("0"))
            {
                sql = sql + dbObj.village_id + " = '" + villageId + "' and ";
            }
            sql = sql + dbObj.market_active + " = '1' order by " + dbObj.market_number;            
            
        return eQuery(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        Market p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Market();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.village_id = rs.getString(dbObj.village_id);
            p.market_number = rs.getString(dbObj.market_number);
            p.market_name = rs.getString(dbObj.market_name);
            p.market_staff_record = rs.getString(dbObj.market_staff_record);
            p.market_staff_modify = rs.getString(dbObj.market_staff_modify);
            p.market_staff_cancel = rs.getString(dbObj.market_staff_cancel);  
            p.market_record_date_time = rs.getString(dbObj.market_record_date_time);
            p.market_modify_date_time = rs.getString(dbObj.market_modify_date_time);
            p.market_cancel_date_time = rs.getString(dbObj.market_cancel_date_time);
            p.market_close = rs.getString(dbObj.market_close);
            p.market_close_date_time = rs.getString(dbObj.market_close_date_time);
            p.market_close_note = rs.getString(dbObj.market_close_note);
            p.market_active = rs.getString(dbObj.market_active);  
            list.add(p);
        }
        rs.close();
        return list;
    }
    
     public Vector selectAll() throws Exception
    {   
        Vector vMarket = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " where " + dbObj.market_active + " = '1' order by "
                + dbObj.market_number;
        
        vMarket = veQuery(sql);
        
        return vMarket;        
    }
    
    public Vector veQuery(String sql) throws Exception
    {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.market_name);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
