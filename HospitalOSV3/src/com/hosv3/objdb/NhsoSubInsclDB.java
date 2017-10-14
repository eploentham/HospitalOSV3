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

public class NhsoSubInsclDB
{
    public ConnectionInf theConnectionInf;
    public NhsoSubInscl dbObj;
    final public String idtable = "";

    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public NhsoSubInsclDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new NhsoSubInscl();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table = "f_nhso_sub_inscl";
        dbObj.pk_field = "nhso_sub_inscl_id";
        dbObj.nhso_sub_inscl_dateexp = "nhso_sub_inscl_dateexp";
        dbObj.nhso_sub_inscl_name = "nhso_sub_inscl_name";
        dbObj.nhso_sub_inscl_seq = "nhso_sub_inscl_seq";
        dbObj.nhso_sub_main_inscl = "nhso_sub_main_inscl";
        return true;
    }

    public int insert(NhsoSubInscl p) throws Exception
    {
        String sql="";
        p.generateOID(this.idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.nhso_sub_inscl_dateexp
        + " ,"  + dbObj.nhso_sub_inscl_name
        + " ,"  + dbObj.nhso_sub_inscl_seq
        + " ,"  + dbObj.nhso_sub_main_inscl
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.nhso_sub_inscl_dateexp
        + "','" + p.nhso_sub_inscl_name
        + "','" + p.nhso_sub_inscl_seq
        + "','" + p.nhso_sub_main_inscl
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int update(NhsoSubInscl o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        String field =""
        + "', "	+ dbObj.nhso_sub_inscl_dateexp  + "='" + o.nhso_sub_inscl_dateexp
        + "', "	+ dbObj.nhso_sub_inscl_name  + "='" + o.nhso_sub_inscl_name
        + "', "	+ dbObj.nhso_sub_inscl_seq  + "='" + o.nhso_sub_inscl_seq
        + "', "	+ dbObj.nhso_sub_main_inscl  + "='" + o.nhso_sub_main_inscl
        + "' where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int delete(NhsoSubInscl of) throws Exception
    {
        String sql ="delete from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + of.getObjectId() + "'";
        return theConnectionInf.eUpdate(sql);
    }
    public Vector selectAll() throws Exception
    {
    	String sql ="select * from " + dbObj.table;
        return eQuery(sql);
    }

    public NhsoSubInscl selectByID(String id) throws Exception
    {
    	String sql ="select * from " + dbObj.table
        + " where " + dbObj.pk_field + " = '" + id + "'";
        Vector v = eQuery(sql);
        if(v.isEmpty()) return null;
        else return (NhsoSubInscl)v.get(0);
    }

//    public NhsoSubInscl selectByDes(String des) throws Exception
//    {
//    	String sql ="select * from " + dbObj.table
//        + " where " + dbObj.des + " = '" + des.trim() + "'";
//        Vector v = eQuery(sql);
//        if(v.isEmpty()) return null;
//        else return (NhsoSubInscl)v.get(0);
//    }

    public Vector eQuery(String sql) throws Exception
    {
        NhsoSubInscl p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new NhsoSubInscl();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.nhso_sub_inscl_dateexp = rs.getString(dbObj.nhso_sub_inscl_dateexp);
            p.nhso_sub_inscl_name = rs.getString(dbObj.nhso_sub_inscl_name);
            p.nhso_sub_inscl_seq = rs.getString(dbObj.nhso_sub_inscl_seq);
            p.nhso_sub_main_inscl = rs.getString(dbObj.nhso_sub_main_inscl);
            list.add(p);
        }
        rs.close();
        return list;
    }
}