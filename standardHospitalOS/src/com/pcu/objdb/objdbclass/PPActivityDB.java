/*
 * PPActivityDB.java
 *
 * Created on 28 กรกฎาคม 2548, 11:37 น.
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
public class PPActivityDB
{
    
    /** Creates a new instance of PPActivityDB */
    public PPActivityDB()
    {
    }
    
    public ConnectionInf theConnectionInf;
    public PPActivity dbObj;
    
    public PPActivityDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PPActivity();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_pp_activity";        
        dbObj.pk_field = "f_health_pp_activity_id";
        dbObj.description ="pp_activity_description";
        
        return true;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vPPActivity = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vPPActivity = veQuery(sql);
        
        if(vPPActivity.size()==0)
            return null;
        else
            return vPPActivity;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        PPActivity p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PPActivity();
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
