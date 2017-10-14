/*
 * WaterTypeDB.java
 *
 * Created on 13 ÁÔ¶Ø¹ÒÂ¹ 2548, 9:54 ¹.
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
public class WaterTypeDB {
    
    /** Creates a new instance of WaterTypeDB */
    public WaterTypeDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public WaterType dbObj;
    final private String idtable = "710";
    
    public WaterTypeDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new WaterType();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "b_health_water_type";        
        dbObj.pk_field = "b_health_water_type_id";
        dbObj.number ="water_type_number";
        dbObj.description = "water_type_description";  
        dbObj.active = "water_type_active";
        
        return true;
    }
    
    public int insert(WaterType o) throws Exception
    {
        String sql="";
        WaterType p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.number
        + " ,"  + dbObj.description 
        + " ,"  + dbObj.active
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.number
        + "','" + p.description 
        + "','" + p.active
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(WaterType o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        WaterType p=o;
        String field =""
        + "', " + dbObj.number + "='" + p.number
        + "', " + dbObj.description + "='" + p.description   
        + "', " + dbObj.active + "='" + p.active   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(WaterType o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listWaterTypeByNameOrNumberAndActive(String search,String active) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            if(search.trim().length() !=0)
            {
                sql = sql + "(" +  dbObj.number
                + " like '%" + search + "%'" + " or "
                + dbObj.description+ " like '%" + search + "%'" + ") and ";
            }
            sql = sql + dbObj.active + " = '" + active + "' order by " + dbObj.number;
        
        return eQuery(sql);
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vWaterType = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " where " + dbObj.active + " = '1' order by "
                + dbObj.number;
        
        vWaterType = veQuery(sql);
        
        if(vWaterType.size()==0)
            return null;
        else
            return vWaterType;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        WaterType p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new WaterType();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.number = rs.getString(dbObj.number);
            p.description = rs.getString(dbObj.description);           
            p.active = rs.getString(dbObj.active);
            list.add(p);
        }
        rs.close();
        return list;
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
            p.name = rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    
    
}
