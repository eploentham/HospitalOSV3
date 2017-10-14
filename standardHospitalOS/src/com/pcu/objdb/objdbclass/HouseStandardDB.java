/*
 * HouseStandardDB.java
 *
 * Created on 11 ÁÔ¶Ø¹ÒÂ¹ 2548, 15:21 ¹.
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
public class HouseStandardDB {
    
    /** Creates a new instance of HouseStandardDB */
    public HouseStandardDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public HouseStandard dbObj;
    final private String idtable = "717";
    
    public HouseStandardDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new HouseStandard();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_home_house_standard";        
        dbObj.pk_field = "t_health_home_house_standard_id";
        dbObj.sub_home_id ="t_health_sub_home_id";
        dbObj.durability = "health_home_durability";   
        dbObj.care ="health_home_care";
        dbObj.light = "health_home_light"; 
        dbObj.cleanness = "health_home_cleanness";
        dbObj.ventilation = "health_home_ventilation";
        dbObj.house_standard = "health_home_house_standard";
        
        return true;
    }
    
    public int insert(HouseStandard o) throws Exception
    {
        String sql="";
        HouseStandard p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.sub_home_id
        + " ,"  + dbObj.durability
        + " ,"	+ dbObj.care
        + " ,"	+ dbObj.light 
        + " ,"	+ dbObj.cleanness
        + " ,"	+ dbObj.ventilation
        + " ,"	+ dbObj.house_standard
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.sub_home_id
        + "','" + p.durability
        + "','" + p.care
        + "','" + p.light
        + "','" + p.cleanness
        + "','" + p.ventilation
        + "','" + p.house_standard
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(HouseStandard o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        HouseStandard p=o;
        String field =""
        + "', " + dbObj.sub_home_id + "='" + p.sub_home_id
        + "', " + dbObj.durability + "='" + p.durability
        + "', " + dbObj.care + "='" + p.care
        + "', " + dbObj.light + "='" + p.light
        + "', " + dbObj.cleanness + "='" + p.cleanness 
        + "', " + dbObj.ventilation + "='" + p.ventilation 
        + "', " + dbObj.house_standard + "='" + p.house_standard 
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(HouseStandard o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
     public Vector listHouseBySubHome(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            
                sql = sql + dbObj.sub_home_id
                + " like '%" + search + "%'";
          
        return eQuery(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        HouseStandard p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new HouseStandard();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.sub_home_id = rs.getString(dbObj.sub_home_id);
            p.durability = rs.getString(dbObj.durability);
            p.care = rs.getString(dbObj.care);
            p.light = rs.getString(dbObj.light);
            p.cleanness = rs.getString(dbObj.cleanness); 
            p.ventilation = rs.getString(dbObj.ventilation); 
            p.house_standard = rs.getString(dbObj.house_standard); 
            list.add(p);
        }
        rs.close();
        return list;
    }
}
