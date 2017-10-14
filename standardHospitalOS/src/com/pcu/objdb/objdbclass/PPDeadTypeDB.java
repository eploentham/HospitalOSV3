/*
 * PPDeadTypeDB.java
 *
 * Created on 28 กรกฎาคม 2548, 16:05 น.
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
public class PPDeadTypeDB
{
    
    /** Creates a new instance of PPDeadTypeDB */
    public PPDeadTypeDB()
    {
    }
    
    public ConnectionInf theConnectionInf;
    public PPDeadType dbObj;
    
    public PPDeadTypeDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PPDeadType();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_pp_dead_type";        
        dbObj.pk_field = "f_health_pp_dead_type_id";
        dbObj.description ="pp_dead_type_description";
        
        return true;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vPPDeadType = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vPPDeadType = veQuery(sql);
        
        if(vPPDeadType.size()==0)
            return null;
        else
            return vPPDeadType;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        PPDeadType p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PPDeadType();
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
