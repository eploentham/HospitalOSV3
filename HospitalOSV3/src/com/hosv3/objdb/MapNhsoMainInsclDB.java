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

public class MapNhsoMainInsclDB
{
    public ConnectionInf theConnectionInf;
    public MapNhsoMainInscl dbObj;
    final public String idtable = "";

    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public MapNhsoMainInsclDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new MapNhsoMainInscl();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table = "b_map_nhso_main_inscl";
        dbObj.pk_field = "b_map_nhso_main_inscl_id";
        dbObj.b_contract_plans_id = "b_contract_plans_id";
        dbObj.f_nhso_main_inscl_id = "f_nhso_main_inscl_id";
        dbObj.map_nhso_main_inscl_type = "map_nhso_main_inscl_type";
        dbObj.contract_plans_des = "contract_plans_description";
        dbObj.b_contract_id = "b_contract_id";
        return true;
    }

    public int insert(MapNhsoMainInscl p) throws Exception
    {
        String sql="";
        p.generateOID(this.idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.b_contract_plans_id
        + " ,"  + dbObj.f_nhso_main_inscl_id
        + " ,"  + dbObj.map_nhso_main_inscl_type
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.b_contract_plans_id
        + "','" + p.f_nhso_main_inscl_id
        + "','" + p.map_nhso_main_inscl_type
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int update(MapNhsoMainInscl o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        String field =""
        + "', "	+ dbObj.b_contract_plans_id  + "='" + o.b_contract_plans_id
        + "', "	+ dbObj.f_nhso_main_inscl_id  + "='" + o.f_nhso_main_inscl_id
        + "', "	+ dbObj.map_nhso_main_inscl_type  + "='" + o.map_nhso_main_inscl_type
        + "' where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int delete(MapNhsoMainInscl of) throws Exception
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
    public Vector selectByNhsoRightID(String id) throws Exception
    {
    	String sql ="select b_map_nhso_main_inscl_id,f_nhso_main_inscl_id,b_map_nhso_main_inscl.b_contract_plans_id,map_nhso_main_inscl_type"
                + ",b_contract_plans.contract_plans_description,b_contract_plans.b_contract_id "
                + "from "+dbObj.table + " inner join b_contract_plans on b_contract_plans.b_contract_plans_id = "
                + "b_map_nhso_main_inscl.b_contract_plans_id where f_nhso_main_inscl_id = '"+id+"'";
        return eQueryN(sql);
    }
    public Vector selectByNhsoRightIDAndType(String id,String type) throws Exception
    {
    	String sql ="select b_map_nhso_main_inscl_id,b_map_nhso_main_inscl.f_nhso_main_inscl_id,b_map_nhso_main_inscl.b_contract_plans_id"
                + ",map_nhso_main_inscl_type,b_contract_plans.contract_plans_description,b_contract_plans.b_contract_id "
                + "from "+dbObj.table + " inner join b_contract_plans on b_contract_plans.b_contract_plans_id = "
                + "b_map_nhso_main_inscl.b_contract_plans_id "
                + "inner join f_nhso_main_inscl on f_nhso_main_inscl.f_nhso_main_inscl_id = b_map_nhso_main_inscl.f_nhso_main_inscl_id "
                + "where f_nhso_main_inscl.nhso_main_right_name = '"+id+"' and map_nhso_main_inscl_type = '"+type+"'";
        return eQueryN(sql);
    }
    public MapNhsoMainInscl selectByID(String id) throws Exception
    {
    	String sql ="select * from " + dbObj.table
        + " where " + dbObj.pk_field + " = '" + id + "'";
        Vector v = eQuery(sql);
        if(v.isEmpty()) return null;
        else return (MapNhsoMainInscl)v.get(0);
    }

//    public MapNhsoMainInscl selectByDes(String des) throws Exception
//    {
//    	String sql ="select * from " + dbObj.table
//        + " where " + dbObj.des + " = '" + des.trim() + "'";
//        Vector v = eQuery(sql);
//        if(v.isEmpty()) return null;
//        else return (MapNhsoMainInscl)v.get(0);
//    }

    public Vector eQuery(String sql) throws Exception
    {
        MapNhsoMainInscl p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new MapNhsoMainInscl();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.b_contract_plans_id = rs.getString(dbObj.b_contract_plans_id);
            p.f_nhso_main_inscl_id = rs.getString(dbObj.f_nhso_main_inscl_id);
            p.map_nhso_main_inscl_type = rs.getString(dbObj.map_nhso_main_inscl_type);
            list.add(p);
        }
        rs.close();
        return list;
    }
    public Vector eQueryN(String sql) throws Exception
    {
        MapNhsoMainInscl p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new MapNhsoMainInscl();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.b_contract_plans_id = rs.getString(dbObj.b_contract_plans_id);
            p.f_nhso_main_inscl_id = rs.getString(dbObj.f_nhso_main_inscl_id);
            p.map_nhso_main_inscl_type = rs.getString(dbObj.map_nhso_main_inscl_type);
            p.contract_plans_des = rs.getString(dbObj.contract_plans_des);
            p.b_contract_id = rs.getString(dbObj.b_contract_id);
            list.add(p);
        }
        rs.close();
        return list;
    }
}