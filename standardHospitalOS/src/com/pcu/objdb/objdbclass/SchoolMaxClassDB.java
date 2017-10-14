/*
 * SchoolMaxClassDB.java
 *
 * Created on 11 ÁÔ¶Ø¹ÒÂ¹ 2548, 17:35 ¹.
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
public class SchoolMaxClassDB {
    
    /** Creates a new instance of SchoolMaxClassDB */
    public SchoolMaxClassDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public SchoolMaxClass dbObj;
    final private String idtable = "705";
    
    public SchoolMaxClassDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new SchoolMaxClass();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "b_health_school_max_class";        
        dbObj.pk_field = "b_health_school_max_class_id";
        dbObj.number ="school_max_class_number";
        dbObj.description = "school_max_class_description";  
        dbObj.active = "school_max_class_active";
        
        return true;
    }
    
    public int insert(SchoolMaxClass o) throws Exception
    {
        String sql="";
        SchoolMaxClass p=o;
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
    
    public int update(SchoolMaxClass o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        SchoolMaxClass p=o;
        String field =""
        + "', " + dbObj.number + "='" + p.number
        + "', " + dbObj.description + "='" + p.description   
        + "', " + dbObj.active + "='" + p.active   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(SchoolMaxClass o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listSchoolMaxClassByNameOrNumberAndActive(String search,String active) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            if(search.trim().length() !=0)
            {
                sql = sql + "(" +  dbObj.number
                + " like '%" + search + "%'" + " or "
                + dbObj.description+ " like '%" + search + "%'" + ") and ";
            }
            sql = sql + dbObj.active + " = '" + active + "' order by " + dbObj.number;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vSchoolMaxClass = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " where " + dbObj.active + " = '1' order by "
                + dbObj.number;
        
        vSchoolMaxClass = veQuery(sql);
        
        if(vSchoolMaxClass.size()==0)
            return null;
        else
            return vSchoolMaxClass;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        SchoolMaxClass p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new SchoolMaxClass();
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
