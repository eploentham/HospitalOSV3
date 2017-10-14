/*
 * SchoolTypeDB.java
 *
 * Created on 12 เมษายน 2549, 21:44 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.healthy.objdb;
import com.healthy.object.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author Aviator
 */
public class SchoolTypeDB {

    public ConnectionInf theConnectionInf;
    public SexBehaviorType dbObj;
    final public String idtable = "htf007";
    
    /** Creates a new instance of SchoolTypeDB */
    public SchoolTypeDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new SexBehaviorType();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table="f_health_tobe_school";
        dbObj.pk_field="f_health_tobe_school_id";
        dbObj.description="tobe_school_name";

        return true;
    }
    
    public Vector selectAll(Vector v) throws Exception
    {   Vector vc = new Vector();
        String sql ="select * from " + dbObj.table + " order by "+
        dbObj.pk_field;
        vc = veQuery(sql, v);
        
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    public Vector veQuery(String sql, Vector v) throws Exception
    {
        ComboFix p;
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.description);
            v.add(p);
        }
        rs.close();
        return v;
    }
    
}
