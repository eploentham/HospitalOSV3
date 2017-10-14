/*
 * CheckResultDB.java
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
public class CheckResultDB {
    
    /** Creates a new instance of CheckResultDB */
    public CheckResultDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public CheckResult dbObj;    
    
    public CheckResultDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new CheckResult();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_check_result";        
        dbObj.pk_field = "f_health_check_result_id";
        dbObj.description ="health_check_result_description";
        return true;
    }
    
    public int insert(CheckResult o) throws Exception
    {
        String sql="";
        CheckResult p=o;
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.description 
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.description 
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
     public Vector selectByPK(String pk) throws Exception{
        Vector vCheckResult = new Vector();
        String sql ="select * from "+ dbObj.table 
        + " where " + dbObj.pk_field + "='" + pk +"'" ;
        vCheckResult = eQuery(sql);
        if(vCheckResult.size()==0)
            return null;
        else
            return vCheckResult;        
    }
   
  
     
    public int update(CheckResult o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        CheckResult p=o;
        String field =""
        + "', " + dbObj.description + "='" + p.description   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(CheckResult o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vCheckResult = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vCheckResult = veQuery(sql);
        
        if(vCheckResult.size()==0)
            return null;
        else
            return vCheckResult;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        CheckResult p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new CheckResult();
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
