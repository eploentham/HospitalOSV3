/*
 * BirthMethodDB.java
 *
 * Created on 28 กรกฎาคม 2548, 14:31 น.
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
public class BirthMethodDB
{
    
    /** Creates a new instance of BirthMethodDB */
    public BirthMethodDB()
    {
    }
    
    public ConnectionInf theConnectionInf;
    public BirthMethod dbObj;
    
    public BirthMethodDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new BirthMethod();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_birth_method";        
        dbObj.pk_field = "f_health_birth_method_id";
        dbObj.description ="birth_method_description";
        
        return true;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vBirthMethod = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vBirthMethod = veQuery(sql);
        
        if(vBirthMethod.size()==0)
            return null;
        else
            return vBirthMethod;        
    }
    
    public Vector selectAllForPP() throws Exception
    {   
        Vector vBirthMethod = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " where " 
                + dbObj.pk_field + " <> '7' order by "
                + dbObj.pk_field;
        
        vBirthMethod = veQuery(sql);
        
        if(vBirthMethod.size()==0)
            return null;
        else
            return vBirthMethod;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        BirthMethod p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new BirthMethod();
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
