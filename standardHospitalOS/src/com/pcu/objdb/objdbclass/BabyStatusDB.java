/*
 * BabyStatusDB.java
 *
 * Created on 5 กรกฎาคม 2548, 14:06 น.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;

import com.pcu.object.*;
/**
 *
 * @author Administrator
 */
public class BabyStatusDB {
    
    /** Creates a new instance of BabyStatusDB */
    public BabyStatusDB() {
    }
    public ConnectionInf theConnectionInf;
    public BabyStatus dbObj;
    
    
    public BabyStatusDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new BabyStatus();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_pregnancy_posture_baby_status";        
        dbObj.pk_field = "f_health_pregnancy_posture_baby_status_id";
        dbObj.description ="health_pregnancy_posture_baby_status_description";
        
        return true;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vComChar = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " Order by " + dbObj.pk_field ;
        
        vComChar = veQuery(sql);
        
        if(vComChar.size()==0)
            return null;
        else
            return vComChar;        
    }
    public Vector eQuery(String sql) throws Exception
    {
        BabyStatus p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new BabyStatus();
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
