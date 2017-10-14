/*
 * SchoolStandardTypeDB.java
 *
 * Created on 28 ÁÔ¶Ø¹ÒÂ¹ 2548, 13:50 ¹.
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
public class CommunityStandardTypeDB {
    
    /** Creates a new instance of CommunityStandardTypeDB */
    public CommunityStandardTypeDB() {             
    }
    
    public ConnectionInf theConnectionInf;
    public CommunityStandardType dbObj;
    
    public CommunityStandardTypeDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new CommunityStandardType();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_community_standard_type";        
        dbObj.pk_field = "f_health_community_standard_type_id";
        dbObj.description ="community_standard_type_description";
        
        return true;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vCommunityStandardType = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vCommunityStandardType = veQuery(sql);
        
        if(vCommunityStandardType.size()==0)
            return null;
        else
            return vCommunityStandardType;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        CommunityStandardType p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new CommunityStandardType();
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
