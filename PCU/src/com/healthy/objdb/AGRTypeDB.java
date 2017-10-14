/*
 * AGRTypeDB.java
 *
 * Created on 12 เมษายน 2549, 15:37 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.healthy.objdb;

import com.pcu.objdb.objdbclass.AGRDB;
import java.util.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.sql.*;
/**
 *
 * @author hospitalos5
 */
public class AGRTypeDB extends AGRDB {
    
    /** Creates a new instance of AGRTypeDB */
    public AGRTypeDB(ConnectionInf db) {
        super(db);
    }
    
    public Vector selectAll() throws Exception
    {
        Vector vc = new Vector();
        String sql ="select * from " + dbObj.table ;
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
            p.name = rs.getString(dbObj.agr_name);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
