/*
 * OccupationPcuDB.java
 *
 * Created on 9 กันยายน 2548, 11:34 น.
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
 * @author Administrator
 */
public class OccupationPcuDB extends OccupatDB{
    
    /** Creates a new instance of OccupationPcuDB */
    public OccupationPcuDB(ConnectionInf db) {
        super(db);
    }
    
    public OccupationPcu select2ByPK(String str) throws Exception
    {
        Vector vc = new Vector();
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field + " = '" + str + "'";
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        return (OccupationPcu)v.get(0);
    }    
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectAll() throws Exception
    {
        Vector vc = new Vector();
        String sql="select * from " + dbObj.table
        + " order by " + dbObj.description;
        return eQuery(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector selectByCN(String key) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where UPPER(" + dbObj.pk_field
        + ") like UPPER('" + key+ "') "
        + " or UPPER(" + dbObj.description
        + ") like UPPER('" + key+ "') order by " + dbObj.description;
        
        return eQuery(sql);
    }
    //////////////////////////////////////////////////////////////////////////////
    public Vector eQuery(String sql) throws Exception
    {
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            OccupationPcu p = new OccupationPcu();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.description = rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        return list;
    }    
}
