/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hosv3.objdb;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hosv3.object.*;
import java.util.*;
import java.sql.*;

public class NhsoRightDB
{
    public ConnectionInf theConnectionInf;
    public NhsoRight dbObj;
    final public String idtable = "";

    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public NhsoRightDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new NhsoRight();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table = "b_ws_nhso_right";
        dbObj.pk_field = "b_ws_nhso_right_id";
        dbObj.des = "ws_nhso_right_des";
        dbObj.active = "ws_nhso_right_active";
        return true;
    }

    public int insert(NhsoRight p) throws Exception
    {
        String sql="";
        p.generateOID(this.idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.des
        + " ,"  + dbObj.active
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.des
        + "','" + p.active
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int update(NhsoRight o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        String field =""
        + "', "	+ dbObj.des  + "='" + o.des
        + "', "	+ dbObj.active  + "='" + o.active
        + "' where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int delete(NhsoRight of) throws Exception
    {
        String sql ="delete from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + of.getObjectId() + "'";
        return theConnectionInf.eUpdate(sql);
    }
    public Vector selectAll() throws Exception
    {
    	String sql ="select * from " + dbObj.table + " where " + dbObj.active + "='1'";
        return eQuery(sql);
    }

    public NhsoRight selectByID(String id) throws Exception
    {
    	String sql ="select * from " + dbObj.table
        + " where " + dbObj.pk_field + " = '" + id + "'";
        Vector v = eQuery(sql);
        if(v.isEmpty()) return null;
        else return (NhsoRight)v.get(0);
    }

    public NhsoRight selectByDes(String des) throws Exception
    {
    	String sql ="select * from " + dbObj.table
        + " where " + dbObj.des + " = '" + des.trim() + "'";
        Vector v = eQuery(sql);
        if(v.isEmpty()) return null;
        else return (NhsoRight)v.get(0);
    }

    public Vector eQuery(String sql) throws Exception
    {
        NhsoRight p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new NhsoRight();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.des = rs.getString(dbObj.des);
            p.active = rs.getString(dbObj.active);
            list.add(p);
        }
        rs.close();
        return list;
    }
}