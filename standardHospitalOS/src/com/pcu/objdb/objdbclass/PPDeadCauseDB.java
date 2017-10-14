/*
 * PPDeadCauseDB.java
 *
 * Created on 28 กรกฎาคม 2548, 16:12 น.
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
public class PPDeadCauseDB
{
    
    /** Creates a new instance of PPDeadCauseDB */
    public PPDeadCauseDB()
    {
    }
    
    public ConnectionInf theConnectionInf;
    public PPDeadCause dbObj;
    
    public PPDeadCauseDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PPDeadCause();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_pp_dead_cause";        
        dbObj.pk_field = "f_health_pp_dead_cause_id";
        dbObj.description ="pp_dead_cause_description";
        
        return true;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vPPDeadCause = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vPPDeadCause = veQuery(sql);
        
        if(vPPDeadCause.size()==0)
            return null;
        else
            return vPPDeadCause;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        PPDeadCause p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PPDeadCause();
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
