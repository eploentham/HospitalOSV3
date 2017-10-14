/*
 * SchoolStandardDB.java
 *
 * Created on 28 ÁÔ¶Ø¹ÒÂ¹ 2548, 14:47 ¹.
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
public class CommunityStandardDB {
    
    /** Creates a new instance of SchoolStandardDB */
    public CommunityStandardDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public CommunityStandard dbObj;
    
    public CommunityStandardDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new CommunityStandard();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_community_standard";        
        dbObj.pk_field = "f_health_community_standard_id";
        dbObj.description ="community_standard_description";
        
        return true;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vCommunityStandard = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vCommunityStandard = veQuery(sql);
        
        if(vCommunityStandard.size()==0)
            return null;
        else
            return vCommunityStandard;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        CommunityStandard p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new CommunityStandard();
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
