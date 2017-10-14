/*
 * PPCoditionDB.java
 *
 * Created on 28 กรกฎาคม 2548, 10:48 น.
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
public class PPConditionDB
{
    
    /** Creates a new instance of PPCoditionDB */
    public PPConditionDB()
    {
    }
    
    public ConnectionInf theConnectionInf;
    public PPCondition dbObj;
    
    public PPConditionDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PPCondition();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_pp_condition";        
        dbObj.pk_field = "f_health_pp_condition_id";
        dbObj.description ="pp_condition_description";
        
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
        PPCondition p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PPCondition();
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
