/*
 * PregnancyPregnantLevelDB.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2548, 13:21 ¹.
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
public class PregnancyPregnantLevelDB {
    
    /** Creates a new instance of PregnancyPregnantLevelDB */
    public PregnancyPregnantLevelDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public PregnancyPregnantLevel dbObj;    
    
    public PregnancyPregnantLevelDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PregnancyPregnantLevel();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_pregnancy_pregnant_level";        
        dbObj.pk_field = "f_health_pregnancy_pregnant_level_id";
        dbObj.description ="health_pregnancy_pregnant_level_description";
        
        return true;
    }
    
    public int insert(PregnancyPregnantLevel o) throws Exception
    {
        String sql="";
        PregnancyPregnantLevel p=o;
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.description 
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.description 
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(PregnancyPregnantLevel o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        PregnancyPregnantLevel p=o;
        String field =""
        + "', " + dbObj.description + "='" + p.description   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(PregnancyPregnantLevel o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vPregnancyPregnantLevel = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vPregnancyPregnantLevel = veQuery(sql);
        
        if(vPregnancyPregnantLevel.size()==0)
            return null;
        else
            return vPregnancyPregnantLevel;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        PregnancyPregnantLevel p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PregnancyPregnantLevel();
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
