/*
 * SchoolServiceTypeDB.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2548, 10:56 ¹.
 */

package com.pcu.objdb.objdbclass;

import java.util.*;
import java.sql.*; 

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;

import com.pcu.object.*;
/**
 *
 * @author noom
 */
public class SchoolServiceTypeDB {
    
    /** Creates a new instance of SchoolServiceTypeDB */
    public SchoolServiceTypeDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public SchoolServiceType dbObj;    
    
    public SchoolServiceTypeDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new SchoolServiceType();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_school_service_type";        
        dbObj.pk_field = "f_health_school_service_type_id";
        dbObj.description ="health_school_service_type_description";
        return true;
    }
    
    public int insert(SchoolServiceType o) throws Exception
    {
        String sql="";
        SchoolServiceType p=o;
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.description 
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.description 
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(SchoolServiceType o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        SchoolServiceType p=o;
        String field =""
        + "', " + dbObj.description + "='" + p.description   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(SchoolServiceType o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vSchoolServiceType = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vSchoolServiceType = veQuery(sql);
        
        if(vSchoolServiceType.size()==0)
            return null;
        else
            return vSchoolServiceType;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        SchoolServiceType p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new SchoolServiceType();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);           
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
