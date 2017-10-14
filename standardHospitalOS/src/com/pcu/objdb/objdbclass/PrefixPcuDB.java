/*
 * PrefixPcuDB.java
 *
 * Created on 9 กันยายน 2548, 10:42 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.objdb.objdbclass;
import com.hospital_os.usecase.connection.*;

import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import com.hospital_os.objdb.*;

import com.pcu.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author jao
 */
public class PrefixPcuDB extends PrefixDB{
    
    /** Creates a new instance of PrefixPcuDB */
    public PrefixPcuDB(ConnectionInf db) {
        super(db);
    }
    /**
     *@deprecated henbe unused
     public Vector selectTlcok2() throws Exception
    {
        Vector vc = new Vector();
        String sql="select * from " + dbObj.table
        + " where " + dbObj.tlock
        + " = 2 order by " + dbObj.description;
        vc = veQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
     */
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll() throws Exception
    {
        Vector vc = new Vector();
        String sql="select * from " + dbObj.table
        + " order by " + dbObj.description;
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector eQuery(String sql) throws Exception
    {
        PrefixPcu p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            p = new PrefixPcu();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);
            p.sex = rs.getString(dbObj.sex);
            p.tlock = rs.getString(dbObj.tlock);
            list.add(p);
        }
        rs.close();
        return list;
    }
    ////////////////////////////////////////////////////////////////////////////
      public Vector selectByCN(String key) throws Exception
      {
           String sql="select * from " + dbObj.table
            + " where UPPER(" + dbObj.pk_field
            + ") like UPPER('" + key+ "') "
            + " or UPPER(" + dbObj.description
            + ") like UPPER('" + key+ "') order by " + dbObj.description;
            return eQuery(sql);
      }
    
}
