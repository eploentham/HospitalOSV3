/*
 * PostpartumStatusResultDB.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2548, 13:51 ¹.
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
public class PostpartumStatusResultDB {
    
    /** Creates a new instance of PostpartumStatusResultDB */
    public PostpartumStatusResultDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public PostpartumStatusResult dbObj;    
    
    public PostpartumStatusResultDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PostpartumStatusResult();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_postpartum_status_result";        
        dbObj.pk_field = "f_health_postpartum_status_result_id";
        dbObj.description ="health_postpartum_status_result_description";
        
        return true;
    }
    
    public int insert(PostpartumStatusResult o) throws Exception
    {
        String sql="";
        PostpartumStatusResult p=o;
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.description 
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.description 
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(PostpartumStatusResult o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        PostpartumStatusResult p=o;
        String field =""
        + "', " + dbObj.description + "='" + p.description   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(PostpartumStatusResult o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vPostpartumStatusResult = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vPostpartumStatusResult = veQuery(sql);
        
        if(vPostpartumStatusResult.size()==0)
            return null;
        else
            return vPostpartumStatusResult;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        PostpartumStatusResult p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PostpartumStatusResult();
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
