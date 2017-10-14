/*
 * PPEyeDrugDB.java
 *
 * Created on 28 กรกฎาคม 2548, 14:01 น.
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
public class PPEyeDrugDB
{
    
    /** Creates a new instance of PPEyeDrugDB */
    public PPEyeDrugDB()
    {
    }
    
    public ConnectionInf theConnectionInf;
    public PPEyeDrug dbObj;
    
    public PPEyeDrugDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PPEyeDrug();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_pp_eye_drug";        
        dbObj.pk_field = "f_health_pp_eye_drug_id";
        dbObj.description ="pp_eye_drug_description";
        
        return true;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vPPEyeDrug = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field + " desc";
        
        vPPEyeDrug = veQuery(sql);
        
        if(vPPEyeDrug.size()==0)
            return null;
        else
            return vPPEyeDrug;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        PPEyeDrug p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PPEyeDrug();
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
