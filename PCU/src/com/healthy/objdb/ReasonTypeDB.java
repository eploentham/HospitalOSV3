/*
 * ReasonTypeDB.java
 *
 * Created on 17 เมษายน 2549, 21:26 น.
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
public class ReasonTypeDB {
    public ConnectionInf theConnectionInf;
    public  ReasonType dbObj;
    final public String idtable = "htf008";
    /** Creates a new instance of ReasonTypeDB */
    public ReasonTypeDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new  ReasonType();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table="f_health_reason";
        dbObj.pk_field="f_health_reason_id";
        dbObj.description="reason_name";

        return true;
    }
    public Vector selectAll() throws Exception
    {   Vector vc = new Vector();
        String sql ="select * from " + dbObj.table + " order by "+
        dbObj.pk_field;
        vc = veQuery(sql);
        
        if(vc.size()==0)
            return null;
        else
            return vc;
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
