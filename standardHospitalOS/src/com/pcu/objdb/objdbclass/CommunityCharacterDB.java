/*
 * CommunityCharacterDB.java
 *
 * Created on 13 ÁÔ¶Ø¹ÒÂ¹ 2548, 13:58 ¹.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
import com.hospital_os.utility.*;
/**
 *
 * @author Administrator
 */
public class CommunityCharacterDB {
    
    /** Creates a new instance of CommunityCharacterDB */
    public CommunityCharacterDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public CommunityCharacter dbObj;
    
    
    public CommunityCharacterDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new CommunityCharacter();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_home_community_charac";        
        dbObj.pk_field = "f_health_home_community_charac_id";
        dbObj.description ="health_home_community_charac_description";
        
        return true;
    }
    
    public Vector selectComChar() throws Exception
    {   
        Vector vComChar = new Vector();
        
        String sql ="select * from " 
                + dbObj.table ;
        
        vComChar = veQuery(sql);
        
        if(vComChar.size()==0)
            return null;
        else
            return vComChar;        
    }
    public Vector eQuery(String sql) throws Exception
    {
        CommunityCharacter p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new CommunityCharacter();
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
