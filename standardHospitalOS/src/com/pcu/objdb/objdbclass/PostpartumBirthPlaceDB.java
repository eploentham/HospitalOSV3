/*
 * PostpartumBirthPlaceDB.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2548, 13:04 ¹.
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
public class PostpartumBirthPlaceDB {
    
    /** Creates a new instance of PostpartumBirthPlaceDB */
    public PostpartumBirthPlaceDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public PostpartumBirthPlace dbObj;    
    
    public PostpartumBirthPlaceDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PostpartumBirthPlace();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_postpartum_birth_place";        
        dbObj.pk_field = "f_health_postpartum_birth_place_id";
        dbObj.description ="health_postpartum_birth_place_description";
        
        return true;
    }
    
    public int insert(PostpartumBirthPlace o) throws Exception
    {
        String sql="";
        PostpartumBirthPlace p=o;
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.description 
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.description 
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(PostpartumBirthPlace o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        PostpartumBirthPlace p=o;
        String field =""
        + "', " + dbObj.description + "='" + p.description   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(PostpartumBirthPlace o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vPostpartumBirthPlace = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vPostpartumBirthPlace = veQuery(sql);
        
        if(vPostpartumBirthPlace.size()==0)
            return null;
        else
            return vPostpartumBirthPlace;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        PostpartumBirthPlace p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PostpartumBirthPlace();
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
