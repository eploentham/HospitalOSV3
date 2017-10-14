/*
 * PPVitKDB.java
 *
 * Created on 28 กรกฎาคม 2548, 15:08 น.
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
public class PPVitKDB
{
    
    /** Creates a new instance of PPVitKDB */
    public PPVitKDB()
    {
    }
    
    public ConnectionInf theConnectionInf;
    public PPVitK dbObj;
    
    public PPVitKDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PPVitK();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_pp_vitk";        
        dbObj.pk_field = "f_health_pp_vitk_id";
        dbObj.description ="pp_vitk_description";
        
        return true;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vPPVitK = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vPPVitK = veQuery(sql);
        
        if(vPPVitK.size()==0)
            return null;
        else
            return vPPVitK;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        PPVitK p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PPVitK();
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
