/*
 * PPStateDB.java
 *
 * Created on 28 กรกฎาคม 2548, 15:52 น.
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
public class PPStateDB
{
    
    /** Creates a new instance of PPStateDB */
    public PPStateDB()
    {
    }
    
    public ConnectionInf theConnectionInf;
    public PPState dbObj;
    
    public PPStateDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PPState();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_pp_state";        
        dbObj.pk_field = "f_health_pp_state_id";
        dbObj.description ="pp_state_description";
        
        return true;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vPPCondition = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vPPCondition = veQuery(sql);
        
        if(vPPCondition.size()==0)
            return null;
        else
            return vPPCondition;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        PPState p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PPState();
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
