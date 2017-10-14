/*
 * PPBreathingTimeDB.java
 *
 * Created on 28 กรกฎาคม 2548, 11:48 น.
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
public class PPBreathingTimeDB
{
    
    /** Creates a new instance of PPBreathingTimeDB */
    public PPBreathingTimeDB()
    {
    }
    
    public ConnectionInf theConnectionInf;
    public PPBreathingTime dbObj;
    
    public PPBreathingTimeDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PPBreathingTime();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_pp_breathing_time";        
        dbObj.pk_field = "f_health_pp_breathing_time_id";
        dbObj.description ="pp_breathing_time_description";
        
        return true;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vPPBreathingTime = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vPPBreathingTime = veQuery(sql);
        
        if(vPPBreathingTime.size()==0)
            return null;
        else
            return vPPBreathingTime;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        PPBreathingTime p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PPBreathingTime();
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
