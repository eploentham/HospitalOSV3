/*
 * PPAspirationDB.java
 *
 * Created on 28 กรกฎาคม 2548, 13:07 น.
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
public class PPAspirationDB
{
    
    /** Creates a new instance of PPAspirationDB */
    public PPAspirationDB()
    {
    }
    
    public ConnectionInf theConnectionInf;
    public PPAspiration dbObj;
    
    public PPAspirationDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PPAspiration();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_pp_aspiration";        
        dbObj.pk_field = "f_health_pp_aspiration_id";
        dbObj.description ="pp_aspiration_description";
        
        return true;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vPPAspiration = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vPPAspiration = veQuery(sql);
        
        if(vPPAspiration.size()==0)
            return null;
        else
            return vPPAspiration;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        PPAspiration p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PPAspiration();
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
