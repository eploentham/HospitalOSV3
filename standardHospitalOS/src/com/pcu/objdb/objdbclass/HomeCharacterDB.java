/*
 * HomeCharacterDB.java
 *
 * Created on 13 ÁÔ¶Ø¹ÒÂ¹ 2548, 12:51 ¹.
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
public class HomeCharacterDB {
    
    /** Creates a new instance of HomeCharacterDB */
    public HomeCharacterDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public HomeCharacter dbObj;
    //final private String idtable = "722";
    
    public HomeCharacterDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new HomeCharacter();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_home_charactor";        
        dbObj.pk_field = "f_health_home_charactor_id";
        dbObj.description ="health_home_charactor_description";
        
        return true;
    }
   
    public Vector selectHomeChar() throws Exception
    {   
        Vector vHomeChar = new Vector();
        
        String sql ="select * from " 
                + dbObj.table ;
        
        vHomeChar = veQuery(sql);
        
        if(vHomeChar.size()==0)
            return null;
        else
            return vHomeChar;        
    } 
    
    public Vector eQuery(String sql) throws Exception
    {
        HomeCharacter p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new HomeCharacter();
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
