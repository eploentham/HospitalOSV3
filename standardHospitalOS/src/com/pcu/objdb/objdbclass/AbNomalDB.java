/*
 * AbNomalDB.java
 *
 * Created on 6 กรกฎาคม 2548, 11:15 น.
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
public class AbNomalDB {
    
    /** Creates a new instance of AbNomalDB */
    public AbNomalDB() {
    }
     public ConnectionInf theConnectionInf;
    public AbNomal dbObj;
    
    
    public AbNomalDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new AbNomal();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_abnormal";        
        dbObj.pk_field = "f_health_abnormal_id";
        dbObj.description ="health_abnormal_description";
        
        return true;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vComChar = new Vector();
        
        String sql ="select * from " 
                + dbObj.table ;
        
        vComChar = veQuery(sql);
        
        if(vComChar.size()==0)
            return null;
        else
            return vComChar;        
    }
    public Vector eQuery(String sql) throws Exception
    {
        AbNomal p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new AbNomal();
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
