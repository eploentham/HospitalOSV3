/*
 * UterusLevelDB.java
 *
 * Created on 5 กรกฎาคม 2548, 13:55 น.
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
public class UterusLevelDB {
    
    /** Creates a new instance of UterusLevelDB */
    public UterusLevelDB() {
    }
    
     public ConnectionInf theConnectionInf;
    public UterusLevel dbObj;
    
    
    public UterusLevelDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new UterusLevel();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_pregnancy_uterus_level";        
        dbObj.pk_field = "f_health_pregnancy_uterus_level_id";
        dbObj.description ="health_pregnancy_uterus_level_description";
        
        return true;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vComChar = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by " + dbObj.pk_field ;
        
        vComChar = veQuery(sql);
        
        if(vComChar.size()==0)
            return null;
        else
            return vComChar;        
    }
    public Vector eQuery(String sql) throws Exception
    {
        UterusLevel p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new UterusLevel();
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
