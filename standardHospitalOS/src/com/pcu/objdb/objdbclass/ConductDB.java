/*
 * ConductDB.java
 *
 * Created on 5 กรกฎาคม 2548, 14:16 น.
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
public class ConductDB {
    
    /** Creates a new instance of ConductDB */
    public ConductDB() {
    }
    
     public ConnectionInf theConnectionInf;
    public Conduct dbObj;
    
    
    public ConductDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Conduct();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_pregnancy_conduct";        
        dbObj.pk_field = "f_health_pregnancy_conduct_id";
        dbObj.description ="health_pregnancy_conduct_description";
        
        return true;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vComChar = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vComChar = veQuery(sql);
        
        if(vComChar.size()==0)
            return null;
        else
            return vComChar;        
    }
    public Vector eQuery(String sql) throws Exception
    {
        Conduct p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Conduct();
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
