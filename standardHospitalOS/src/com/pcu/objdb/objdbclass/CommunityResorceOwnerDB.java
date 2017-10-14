/*
 * CommunityResorceOwnerDB.java
 *
 * Created on 28 ÁÔ¶Ø¹ÒÂ¹ 2548, 15:12 ¹.
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
public class CommunityResorceOwnerDB {
    
    /** Creates a new instance of CommunityResorceOwnerDB */
    public CommunityResorceOwnerDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public CommunityResorceOwner dbObj;
    
    public CommunityResorceOwnerDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new CommunityResorceOwner();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_community_resorce_owner";        
        dbObj.pk_field = "f_health_community_resorce_owner_id";
        dbObj.description ="community_resorce_owner_description";
        
        return true;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vCommunityResorceOwner = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vCommunityResorceOwner = veQuery(sql);
        
        if(vCommunityResorceOwner.size()==0)
            return null;
        else
            return vCommunityResorceOwner;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        CommunityResorceOwner p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new CommunityResorceOwner();
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
