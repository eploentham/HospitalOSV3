/*
 * PostpartumGivebirthResultDB.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2548, 13:56 ¹.
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
public class PostpartumGivebirthResultDB {
    
    /** Creates a new instance of PostpartumGivebirthResultDB */
    public PostpartumGivebirthResultDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public PostpartumGivebirthResult dbObj;    
    
    public PostpartumGivebirthResultDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PostpartumGivebirthResult();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_family_planing";        
        dbObj.pk_field = "f_health_family_planing_id";
        dbObj.description ="health_family_planing_description";
        
        return true;
    }
    
    public int insert(PostpartumGivebirthResult o) throws Exception
    {
        String sql="";
        PostpartumGivebirthResult p=o;
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.description 
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.description 
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(PostpartumGivebirthResult o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        PostpartumGivebirthResult p=o;
        String field =""
        + "', " + dbObj.description + "='" + p.description   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(PostpartumGivebirthResult o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vPostpartumGivebirthResult = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vPostpartumGivebirthResult = veQuery(sql);
        
        if(vPostpartumGivebirthResult.size()==0)
            return null;
        else
            return vPostpartumGivebirthResult;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        PostpartumGivebirthResult p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PostpartumGivebirthResult();
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
