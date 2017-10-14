/*
 * HomeZoneDB.java
 *
 * Created on 14 ÁÔ¶Ø¹ÒÂ¹ 2548, 15:11 ¹.
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
public class HomeZoneDB {
    
    /** Creates a new instance of HomeZoneDB */
    public HomeZoneDB() {
    }
    public ConnectionInf theConnectionInf;
    public HomeZone dbObj;
    //final private String idtable = "722";
    
    public HomeZoneDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new HomeZone();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_death_place_type";        
        dbObj.pk_field = "f_death_place_type_id";
        dbObj.description ="death_place_type_description";
        
        return true;
    }
    
    public Vector selectHomeZone() throws Exception
    {   
        Vector vHomeZone = new Vector();
        
        String sql ="select * from " 
                + dbObj.table ;
        
        vHomeZone = veQuery(sql);
        
        if(vHomeZone.size()==0)
            return null;
        else
            return vHomeZone;        
    }    
    public Vector eQuery(String sql) throws Exception
    {
        HomeZone p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new HomeZone();
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
