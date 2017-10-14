/*
 * PregnancyBirthDoctorTypeDB.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2548, 14:04 ¹.
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
public class PregnancyBirthDoctorTypeDB {
    
    /** Creates a new instance of PregnancyBirthDoctorTypeDB */
    public PregnancyBirthDoctorTypeDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public PregnancyBirthDoctorType dbObj;    
    
    public PregnancyBirthDoctorTypeDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PregnancyBirthDoctorType();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_family_planing";        
        dbObj.pk_field = "f_health_family_planing_id";
        dbObj.description ="health_family_planing_description";
        
        return true;
    }
    
    public int insert(PregnancyBirthDoctorType o) throws Exception
    {
        String sql="";
        PregnancyBirthDoctorType p=o;
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.description 
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.description 
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(PregnancyBirthDoctorType o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        PregnancyBirthDoctorType p=o;
        String field =""
        + "', " + dbObj.description + "='" + p.description   
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(PregnancyBirthDoctorType o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vPregnancyBirthDoctorType = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vPregnancyBirthDoctorType = veQuery(sql);
        
        if(vPregnancyBirthDoctorType.size()==0)
            return null;
        else
            return vPregnancyBirthDoctorType;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        PregnancyBirthDoctorType p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PregnancyBirthDoctorType();
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
