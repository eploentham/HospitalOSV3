/*
 * PPAsphyxiaDB.java
 *
 * Created on 28 กรกฎาคม 2548, 15:00 น.
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
public class PPAsphyxiaDB
{
    
    /** Creates a new instance of PPAsphyxiaDB */
    public PPAsphyxiaDB()
    {
    }
    public ConnectionInf theConnectionInf;
    public PPAsphyxia dbObj;
    
    public PPAsphyxiaDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PPAsphyxia();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_pp_asphyxia";        
        dbObj.pk_field = "f_health_pp_asphyxia_id";
        dbObj.description ="pp_asphyxia_description";
        
        return true;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vPPAsphyxia = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vPPAsphyxia = veQuery(sql);
        
        if(vPPAsphyxia.size()==0)
            return null;
        else
            return vPPAsphyxia;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        PPAsphyxia p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PPAsphyxia();
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
