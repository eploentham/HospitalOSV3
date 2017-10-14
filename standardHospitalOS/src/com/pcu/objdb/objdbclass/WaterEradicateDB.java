/*
 * WaterEradicateDB.java
 *
 * Created on 11 ÁÔ¶Ø¹ÒÂ¹ 2548, 14:55 ¹.
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
public class WaterEradicateDB {
    
    /** Creates a new instance of WaterEradicateDB */
    public WaterEradicateDB() {
    }
    public ConnectionInf theConnectionInf;
    public WaterEradicate dbObj;
    final private String idtable = "716";
    
    public WaterEradicateDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new WaterEradicate();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_home_water_eradicate";        
        dbObj.pk_field = "t_health_home_water_eradicate_id";
        dbObj.sub_home_id ="t_health_sub_home_id";
        dbObj.type_id = "f_health_home_water_type_id";   
        dbObj.supply_id ="f_health_home_water_supply_id";  
        dbObj.home_water = "health_home_water";   
        dbObj.water_supply = "health_home_water_supply";   
        dbObj.toilet = "health_home_toilet";
        dbObj.garbage = "health_home_garbage";
        dbObj.eradicate = "health_home_water_eradicate";
        dbObj.garbage_method_id = "f_health_home_garbage_method_id";
        
        return true;
    }
    
    public int insert(WaterEradicate o) throws Exception
    {
        String sql="";
        WaterEradicate p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.sub_home_id
        + " ,"  + dbObj.type_id
        + " ,"	+ dbObj.supply_id
        + " ,"	+ dbObj.home_water
        + " ,"	+ dbObj.water_supply 
        + " ,"	+ dbObj.toilet 
        + " ,"	+ dbObj.garbage 
        + " ,"	+ dbObj.eradicate 
        + " ,"	+ dbObj.garbage_method_id 
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.sub_home_id
        + "','" + p.type_id
        + "','" + p.supply_id
        + "','" + p.home_water
        + "','" + p.water_supply   
        + "','" + p.toilet   
        + "','" + p.garbage   
        + "','" + p.eradicate   
        + "','" + p.garbage_method_id         
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(WaterEradicate o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        WaterEradicate p=o;
        String field =""
        + "', " + dbObj.sub_home_id + "='" + p.sub_home_id
        + "', " + dbObj.type_id + "='" + p.type_id
        + "', " + dbObj.supply_id + "='" + p.supply_id
        + "', " + dbObj.home_water + "='" + p.home_water
        + "', " + dbObj.water_supply + "='" + p.water_supply   
        + "', " + dbObj.toilet + "='" + p.toilet   
        + "', " + dbObj.garbage + "='" + p.garbage   
        + "', " + dbObj.eradicate + "='" + p.eradicate   
        + "', " + dbObj.garbage_method_id + "='" + p.garbage_method_id   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(WaterEradicate o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listWaterBySubHome(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            
            
                sql = sql  +  dbObj.sub_home_id
                + " like '%" + search + "%'";
           
        
        return eQuery(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        WaterEradicate p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new WaterEradicate();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.sub_home_id = rs.getString(dbObj.sub_home_id);
            p.type_id = rs.getString(dbObj.type_id);
            p.supply_id = rs.getString(dbObj.supply_id);
            p.home_water = rs.getString(dbObj.home_water); 
            p.water_supply = rs.getString(dbObj.water_supply);
            p.toilet = rs.getString(dbObj.toilet);
            p.garbage = rs.getString(dbObj.garbage);
            p.eradicate = rs.getString(dbObj.eradicate);
            p.garbage_method_id = rs.getString(dbObj.garbage_method_id);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
