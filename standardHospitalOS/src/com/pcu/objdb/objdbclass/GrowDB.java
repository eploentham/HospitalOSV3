/*
 * GrowDB.java
 *
 * Created on 26 ÁÔ¶Ø¹ÒÂ¹ 2548, 10:57 ¹.
 */

package com.pcu.objdb.objdbclass;

import java.util.*;
import java.sql.*; 

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;

import com.pcu.object.*;
/**
 *
 * @author Administrator
 */
public class GrowDB {
    
    /** Creates a new instance of GrowDB */
    public GrowDB() {
    }
    
    
    public ConnectionInf theConnectionInf;
    public Grow dbObj;    
    
    public GrowDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Grow();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_grow";        
        dbObj.pk_field = "f_health_grow_id";
        dbObj.description ="health_grow_description";
        dbObj.grow_standard ="health_grow_standard";
        return true;
    }
    
    public int insert(Grow o) throws Exception
    {
        String sql="";
        Grow p=o;
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.description 
        + " ,"  + dbObj.grow_standard 
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.description 
        + "','" + p.grow_standard 
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(Grow o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Grow p=o;
        String field =""
        + "', " + dbObj.description + "='" + p.description   
        + "', " + dbObj.grow_standard + "='" + p.grow_standard   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(Grow o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectByCombo(String month) throws Exception
    {
        Vector vVector = new Vector();
        
       String sql ="select * from " 
                + dbObj.table + " where " + dbObj.pk_field + " = '" + month +"'"; //order by "
                //+ dbObj.pk_field;
        
        vVector = eQuery(sql);
        
        if(vVector.size()==0)
            return null;
        else
            return vVector;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vGrow = new Vector();
        
        String sql ="select * from " 
                + dbObj.table;// + " order by "
                //+ dbObj.pk_field;
        
        vGrow = veQuery(sql);
        
        if(vGrow.size()==0)
            return null;
        else
            return vGrow;        
    }
    
    public Vector selectAllGrow() throws Exception
    {
        String sql="select * from " + dbObj.table ;
        
        return eQuery(sql);
    }
    
    public Vector selectByCN(String key) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.description
        + " like UPPER ('" + key+ "') ";
        
        return eQuery(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        Grow p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Grow();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);           
            p.grow_standard = rs.getString(dbObj.grow_standard);           
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
            //p.month = rs.getString(dbObj.grow_standard);
            
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
