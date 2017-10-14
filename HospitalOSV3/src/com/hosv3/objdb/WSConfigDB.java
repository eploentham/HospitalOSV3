package com.hosv3.objdb;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hosv3.object.*;
import java.util.*;
import java.sql.*;

public class WSConfigDB
{
    public ConnectionInf theConnectionInf;
    public WSConfig dbObj;
    final public String idtable = "";

    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public WSConfigDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new WSConfig();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table = "b_ws_config";
        dbObj.pk_field = "b_ws_config_id";
        dbObj.url = "ws_config_url";
        dbObj.user_name = "ws_config_username";
        dbObj.password = "ws_config_password";
        return true;
    }

    public int insert(WSConfig p) throws Exception
    {
        String sql="";
        p.generateOID(this.idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.url
        + " ,"  + dbObj.user_name
        + " ,"  + dbObj.password
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.url
        + "','" + p.user_name
        + "','" + p.password
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int update(WSConfig o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        String field =""
        + "', "	+ dbObj.url  + "='" + o.url
        + "', "	+ dbObj.user_name  + "='" + o.user_name
        + "', "	+ dbObj.password   + "='" + o.password
        + "' where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int delete(WSConfig of) throws Exception
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

    public WSConfig selectByID(String id) throws Exception
    {
    	String sql ="select * from " + dbObj.table
        + " where " + dbObj.pk_field + " = '" + id + "'";
        Vector v = eQuery(sql);
        if(v.isEmpty()) return null;
        else return (WSConfig)v.get(0);
    }

    public Vector eQuery(String sql) throws Exception
    {
        WSConfig p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new WSConfig();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.url = rs.getString(dbObj.url);
            p.user_name = rs.getString(dbObj.user_name);
            p.password = rs.getString(dbObj.password);
            list.add(p);
        }
        rs.close();
        return list;
    }
}