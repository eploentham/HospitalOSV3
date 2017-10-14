/*
 * FamilyPlaningMethodDB.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2548, 11:49 ¹.
 */

package com.pcu.objdb.objdbclass;

import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
import com.hospital_os.utility.*;
/**
 *
 * @author tong
 */
public class FamilyPlaningMethodDB {
    public ConnectionInf theConnectionInf;
    
    public  FamilyPlaningMethod dbObj,p;
    final private String idtable = "740";
    
    private Vector vc;
    private ResultSet rs ;
    private String sql ;
     
    
    public FamilyPlaningMethodDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new  FamilyPlaningMethod();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_family_planing_method";        
        dbObj.pk_field = "f_health_family_planing_method_id";
        dbObj.health_family_planing_method_description ="health_family_planing_method_description";
        
        return true;
    }
    
    public int insert(FamilyPlaningMethod o) throws Exception
    {
        sql="";
        o.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.health_family_planing_method_description 
        + " ) values ('"
        + o.getObjectId()
        + "','" + o.health_family_planing_method_description 
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(FamilyPlaningMethod o) throws Exception
    {
        sql="update " + dbObj.table + " set ";
     
        String field =""
        + "', " + dbObj.health_family_planing_method_description + "='" + o.health_family_planing_method_description   
        + "' where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(FamilyPlaningMethod o) throws Exception
    {
        sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAll() throws Exception
    {   
       vc = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vc = veQuery(sql);
        
        if(vc.size()==0)
            return null;
        else
            return vc;        
    }
    
    public Vector selectAllWithOut(String datawithout,boolean incomboboc) throws Exception
    {   
       vc = new Vector();
        
        sql ="select * from " 
                + dbObj.table ;
        if(datawithout != null)
            sql = sql + " WHERE " + dbObj.pk_field + "<> '" + datawithout + "'";
        
        sql = sql +" order by " + dbObj.pk_field;
        
        if(incomboboc)
            vc = veQuery(sql);
        else
            vc = eQuery(sql);
        
        if(vc.size()==0)
            return null;
        else
            return vc;        
    }
    
    
    public Vector eQuery(String sql) throws Exception
    {
       
        vc = new Vector();
        rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new FamilyPlaningMethod();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.health_family_planing_method_description = rs.getString(dbObj.health_family_planing_method_description);           
            vc.add(p);
        }
        rs.close();
        return vc;
    }
    
    public Vector veQuery(String sql) throws Exception
    {
        ComboFix p;
        vc = new Vector();
        rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.health_family_planing_method_description);
          
            vc.add(p);
        }
        rs.close();
        return vc;
    }

    public Vector selectNot09() throws Exception 
    {
        sql ="select * from "  + dbObj.table
                + " WHERE " + dbObj.pk_field + "<> '0'"
                + " AND " + dbObj.pk_field + "<> '9'"
                +" order by " + dbObj.pk_field;
        
        return veQuery(sql);
    }
    
}
