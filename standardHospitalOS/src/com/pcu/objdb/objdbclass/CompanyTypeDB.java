/*
 * CompanyTypeDB.java
 *
 * Created on 11 ÁÔ¶Ø¹ÒÂ¹ 2548, 15:13 ¹.
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
public class CompanyTypeDB {
    
    /** Creates a new instance of CompanyTypeDB */
    public CompanyTypeDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public CompanyType dbObj;
    final private String idtable = "702";
    
    public CompanyTypeDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new CompanyType();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "b_health_company_type";        
        dbObj.pk_field = "b_health_company_type_id";
        dbObj.number ="company_type_number";
        dbObj.description = "company_type_description";  
        dbObj.active = "company_type_active";
        
        return true;
    }
    
    public int insert(CompanyType o) throws Exception
    {
        String sql="";
        CompanyType p=o;
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
    
    public int update(CompanyType o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        CompanyType p=o;
        String field =""
        + "', " + dbObj.number + "='" + p.number
        + "', " + dbObj.description + "='" + p.description   
        + "', " + dbObj.active + "='" + p.active   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(CompanyType o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listCompanyTypeByNameOrNumberAndActive(String search,String active) throws Exception
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
        Vector vCompanyType = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " where " + dbObj.active + " = '1' order by "
                + dbObj.number;
        
        vCompanyType = veQuery(sql);
        
        if(vCompanyType.size()==0)
            return null;
        else
            return vCompanyType;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        CompanyType p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new CompanyType();
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
