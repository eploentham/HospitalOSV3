/*
 * ResourceDB.java
 *
 * Created on 15 กรกฎาคม 2548, 15:40 น.
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
public class CommunityResourceDB {
    
    /** Creates a new instance of ResourceDB */
    public CommunityResourceDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public CommunityResource dbObj;
    final private String idtable = "761";
    
    public CommunityResourceDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new CommunityResource();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "b_health_resource";        
        dbObj.pk_field = "b_health_resource_id";
        dbObj.number ="resource_number";
        dbObj.description = "resource_description";  
        dbObj.active = "resource_active";
        
        dbObj.max_communityresource_id = "max_communityresource_id";
        
        return true;
    }
    
    public int insert(CommunityResource o) throws Exception
    {
        String sql="";
        CommunityResource p=o;
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
    
    public int update(CommunityResource o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        CommunityResource p=o;
        String field =""
        + "', " + dbObj.number + "='" + p.number
        + "', " + dbObj.description + "='" + p.description   
        + "', " + dbObj.active + "='" + p.active   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(CommunityResource o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listResourceByNameOrNumberAndActive(String search,String active) throws Exception
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
    
    public CommunityResource readCommunityResourceByResourceName(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.description + " like '" + search + "' and "
                + dbObj.active + " = '1'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (CommunityResource)v.get(0);
    }
    
    public String selectMaxCode() throws Exception
    {
        String sql ="select max(" + dbObj.number + ") as max_communityresource_id from " + dbObj.table;
        
        Vector v = communityResourceQuery(sql);
        if(v.size()==0)
            return null;
        else
            return ((CommunityResource)v.get(0)).getObjectId();
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vResource = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " where " + dbObj.active + " = '1' order by "
                + dbObj.number;
        
        vResource = veQuery(sql);
        
        if(vResource.size()==0)
            return null;
        else
            return vResource;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        CommunityResource p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new CommunityResource();
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
    
    public Vector communityResourceQuery(String sql) throws Exception
    {
        CommunityResource p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            p = new CommunityResource();
            p.setObjectId(rs.getString(dbObj.max_communityresource_id));
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
