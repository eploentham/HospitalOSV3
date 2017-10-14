/*
 * WaterHistoryDB.java
 *
 * Created on 13 ÁÔ¶Ø¹ÒÂ¹ 2548, 9:43 ¹.
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
public class WaterHistoryDB {
    
    /** Creates a new instance of WaterHistoryDB */
    public WaterHistoryDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public WaterHistory dbObj;
    final private String idtable = "708";
    
    public WaterHistoryDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new WaterHistory();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_water_history";        
        dbObj.pk_field = "t_health_water_history_id";
        dbObj.water_id ="t_health_water_id";
        dbObj.water_history_state = "f_health_water_state_id";   
        dbObj.water_history_staff_record ="water_history_staff_record";
        dbObj.water_history_record_date_time ="water_history_record_date_time";
        
        return true;
    }
    
    public int insert(WaterHistory o) throws Exception
    {
        String sql="";
        WaterHistory p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.water_id
        + " ,"  + dbObj.water_history_state
        + " ,"	+ dbObj.water_history_staff_record
        + " ,"	+ dbObj.water_history_record_date_time    
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.water_id
        + "','" + p.water_history_state
        + "','" + p.water_history_staff_record
        + "','" + p.water_history_record_date_time
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(WaterHistory o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        WaterHistory p=o;
        String field =""
        + "', " + dbObj.water_id + "='" + p.water_id
        + "', " + dbObj.water_history_state + "='" + p.water_history_state
        + "', " + dbObj.water_history_staff_record + "='" + p.water_history_staff_record
        + "', " + dbObj.water_history_record_date_time + "='" + p.water_history_record_date_time
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(WaterHistory o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listWaterHistoryByWaterId(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.water_id + " = '" + search + "' order by "
                + dbObj.water_history_record_date_time + " desc ";            
        
        return eQuery(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        WaterHistory p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new WaterHistory();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.water_id = rs.getString(dbObj.water_id);
            p.water_history_state = rs.getString(dbObj.water_history_state);
            p.water_history_staff_record = rs.getString(dbObj.water_history_staff_record);
            p.water_history_record_date_time = rs.getString(dbObj.water_history_record_date_time);  
            list.add(p);
        }
        rs.close();
        return list;
    }
}
