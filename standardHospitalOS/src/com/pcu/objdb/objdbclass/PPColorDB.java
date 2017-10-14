/*
 * PPColorDB.java
 *
 * Created on 28 กรกฎาคม 2548, 11:20 น.
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
public class PPColorDB
{    
    /** Creates a new instance of PPColorDB */
    public PPColorDB()
    {
    }
    
    public ConnectionInf theConnectionInf;
    public PPColor dbObj;
    
    public PPColorDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PPColor();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "f_health_pp_color";        
        dbObj.pk_field = "f_health_pp_color_id";
        dbObj.description ="pp_color_description";
        
        return true;
    }
    
    public Vector selectAll() throws Exception
    {   
        Vector vPPColor = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " order by "
                + dbObj.pk_field;
        
        vPPColor = veQuery(sql);
        
        if(vPPColor.size()==0)
            return null;
        else
            return vPPColor;        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        PPColor p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PPColor();
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
