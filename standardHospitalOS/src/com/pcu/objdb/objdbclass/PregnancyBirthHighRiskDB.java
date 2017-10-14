/*
 * PregnancyBirthHighRiskDB.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2548, 14:32 ¹.
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
public class PregnancyBirthHighRiskDB {
    
    /** Creates a new instance of PregnancyBirthHighRiskDB */
    public PregnancyBirthHighRiskDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public PregnancyBirthHighRisk dbObj;    
    
    public PregnancyBirthHighRiskDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PregnancyBirthHighRisk();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_pregnancy_birth_high_risk";        
        dbObj.pk_field = "f_health_pregnancy_birth_high_risk_id";
        dbObj.description ="health_pregnancy_birth_high_risk_description";
        
        return true;
    }
    
    public int insert(PregnancyBirthHighRisk o) throws Exception
    {
        String sql="";
        PregnancyBirthHighRisk p=o;
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.description 
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.description 
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(PregnancyBirthHighRisk o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        PregnancyBirthHighRisk p=o;
        String field =""
        + "', " + dbObj.description + "='" + p.description   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(PregnancyBirthHighRisk o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vPregnancyBirthHighRisk = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vPregnancyBirthHighRisk = veQuery(sql);
        
        if(vPregnancyBirthHighRisk.size()==0)
            return null;
        else
            return vPregnancyBirthHighRisk;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        PregnancyBirthHighRisk p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PregnancyBirthHighRisk();
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
