/*
 * VillageLocationDB.java
 *
 * Created on 13 ÁÔ¶Ø¹ÒÂ¹ 2548, 10:15 ¹.
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
public class VillageLocationDB {
    
    /** Creates a new instance of VillageLocationDB */
    public VillageLocationDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public VillageLocation dbObj;
    final private String idtable = "713";
    
    public VillageLocationDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new VillageLocation();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "b_health_village_location";        
        dbObj.pk_field = "b_health_village_location_id";
        dbObj.number ="village_location_number";
        dbObj.description = "village_location_description";  
        dbObj.active = "village_location_active";
        
        return true;
    }
    
    public int insert(VillageLocation o) throws Exception
    {
        String sql="";
        VillageLocation p=o;
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
    
    public int update(VillageLocation o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        VillageLocation p=o;
        String field =""
        + "', " + dbObj.number + "='" + p.number
        + "', " + dbObj.description + "='" + p.description   
        + "', " + dbObj.active + "='" + p.active   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(VillageLocation o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vVillageLocation = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " where " + dbObj.active + " = '1' order by "
                + dbObj.number;
        
        vVillageLocation = veQuery(sql);
        
        if(vVillageLocation.size()==0)
            return null;
        else
            return vVillageLocation;        
    }
    
    public Vector listVillageLocationByNameOrNumberAndActive(String search,String active) throws Exception
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
    
    public Vector eQuery(String sql) throws Exception
    {
        VillageLocation p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new VillageLocation();
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
