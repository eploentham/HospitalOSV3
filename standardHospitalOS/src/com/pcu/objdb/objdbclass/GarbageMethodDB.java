/*
 * GarbageMethodDB.java
 *
 * Created on 13 ÁÔ¶Ø¹ÒÂ¹ 2548, 15:17 ¹.
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
public class GarbageMethodDB {
    
    /** Creates a new instance of GarbageMethodDB */
    public GarbageMethodDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public GarbageMethod dbObj;
    //final private String idtable = "722";
    
    public GarbageMethodDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new GarbageMethod();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_home_garbage_method";        
        dbObj.pk_field = "f_health_home_garbage_method_id";
        dbObj.description ="health_home_garbage_method_description";
        
        return true;
    }
   
     public Vector selectGarbageMethod() throws Exception
    {   
        Vector vGarbageMethod = new Vector();
        
        String sql ="select * from " 
                 + dbObj.table ;
                
        
        vGarbageMethod = veQuery(sql);
        
        if(vGarbageMethod.size()==0)
            return null;
        else
            return vGarbageMethod;        
    }
     
    public Vector eQuery(String sql) throws Exception
    {
        GarbageMethod p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new GarbageMethod();
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
