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

public class NhsoMainInsclDB
{
    public ConnectionInf theConnectionInf;
    public NhsoMainInscl dbObj;
    final public String idtable = "";

    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public NhsoMainInsclDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new NhsoMainInscl();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table = "f_nhso_main_inscl";
        dbObj.pk_field = "f_nhso_main_inscl_id";
        dbObj.nhso_main_inscl_name = "nhso_main_inscl_name";
        dbObj.nhso_main_right_desc = "nhso_main_right_desc";
        dbObj.nhso_main_right_id = "nhso_main_right_id";
        dbObj.nhso_main_right_main = "nhso_main_right_main";
        dbObj.nhso_main_right_name = "nhso_main_right_name";
        dbObj.nhso_main_right_order = "nhso_main_right_order";
        dbObj.nhso_main_seq = "nhso_main_seq";
        dbObj.nhso_main_used = "nhso_main_used";
        return true;
    }

    public int insert(NhsoMainInscl p) throws Exception
    {
        String sql="";
        p.generateOID(this.idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.nhso_main_inscl_name
        + " ,"  + dbObj.nhso_main_right_desc
        + " ,"  + dbObj.nhso_main_right_id
        + " ,"  + dbObj.nhso_main_right_main
        + " ,"	+ dbObj.nhso_main_right_name
        + " ,"  + dbObj.nhso_main_right_order
        + " ,"  + dbObj.nhso_main_seq
        + " ,"  + dbObj.nhso_main_used
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.nhso_main_inscl_name
        + "','" + p.nhso_main_right_desc
        + "','" + p.nhso_main_right_id
        + "','" + p.nhso_main_right_main
        + "','" + p.nhso_main_right_name
        + "','" + p.nhso_main_right_order
        + "','" + p.nhso_main_seq
        + "','" + p.nhso_main_used
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int update(NhsoMainInscl o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        String field =""
        + "', "	+ dbObj.nhso_main_inscl_name  + "='" + o.nhso_main_inscl_name
        + "', "	+ dbObj.nhso_main_right_desc  + "='" + o.nhso_main_right_desc
        + "', "	+ dbObj.nhso_main_right_id  + "='" + o.nhso_main_right_id
        + "', "	+ dbObj.nhso_main_right_main  + "='" + o.nhso_main_right_main
        + "', "	+ dbObj.nhso_main_right_name  + "='" + o.nhso_main_right_name
        + "', "	+ dbObj.nhso_main_right_order  + "='" + o.nhso_main_right_order
        + "', "	+ dbObj.nhso_main_seq  + "='" + o.nhso_main_seq
        + "', "	+ dbObj.nhso_main_used  + "='" + o.nhso_main_used
        + "' where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int delete(NhsoMainInscl of) throws Exception
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
    public Vector selectExceptWEL() throws Exception
    {
        String sql = "select * from "+dbObj.table + " where nhso_main_inscl_name <> 'WEL'";
        return eQuery(sql);
    }

    public NhsoMainInscl selectByID(String id) throws Exception
    {
    	String sql ="select * from " + dbObj.table
        + " where " + dbObj.pk_field + " = '" + id + "'";
        Vector v = eQuery(sql);
        if(v.isEmpty()) return null;
        else return (NhsoMainInscl)v.get(0);
    }

//    public NhsoMainInscl selectByDes(String des) throws Exception
//    {
//    	String sql ="select * from " + dbObj.table
//        + " where " + dbObj.des + " = '" + des.trim() + "'";
//        Vector v = eQuery(sql);
//        if(v.isEmpty()) return null;
//        else return (NhsoMainInscl)v.get(0);
//    }

    public Vector eQuery(String sql) throws Exception
    {
        NhsoMainInscl p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new NhsoMainInscl();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.nhso_main_inscl_name = rs.getString(dbObj.nhso_main_inscl_name);
            p.nhso_main_right_desc = rs.getString(dbObj.nhso_main_right_desc);
            p.nhso_main_right_id = rs.getString(dbObj.nhso_main_right_id);
            p.nhso_main_right_main = rs.getString(dbObj.nhso_main_right_main);
            p.nhso_main_right_name = rs.getString(dbObj.nhso_main_right_name);
            p.nhso_main_right_order = rs.getString(dbObj.nhso_main_right_order);
            p.nhso_main_seq = rs.getString(dbObj.nhso_main_seq);
            p.nhso_main_used = rs.getString(dbObj.nhso_main_used);
            list.add(p);
        }
        rs.close();
        return list;
    }
}