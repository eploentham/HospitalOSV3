/*
 * AGRCodeDB.java
 *
 * Created on 25 กรกฎาคม 2548, 11:33 น.
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
public class AGRCodeDB
{
    
    /** Creates a new instance of AGRCodeDB */
    public AGRCodeDB()
    {
    }
    
    public ConnectionInf theConnectionInf;
    public AGRCode dbObj;
    final private String idtable = "764";
    
    public AGRCodeDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new AGRCode();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "b_health_agr_code";        
        dbObj.pk_field = "b_health_agr_code_id";
        dbObj.number ="agr_code_number";
        dbObj.description = "agr_code_description";  
        dbObj.active = "agr_code_active";
        
        return true;
    }
    
    public int insert(AGRCode o) throws Exception
    {
        String sql="";
        AGRCode p=o;
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
    
    public int update(AGRCode o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        AGRCode p=o;
        String field =""
        + "', " + dbObj.number + "='" + p.number
        + "', " + dbObj.description + "='" + p.description   
        + "', " + dbObj.active + "='" + p.active   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(AGRCode o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listAGRCodeByNameOrNumberAndActive(String search,String active) throws Exception
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
        Vector vAGRCode = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " where " + dbObj.active + " = '1' order by "
                + dbObj.number;
        
        vAGRCode = veQuery(sql);
        
        if(vAGRCode.size()==0)
            return null;
        else
            return vAGRCode;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        AGRCode p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new AGRCode();
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
