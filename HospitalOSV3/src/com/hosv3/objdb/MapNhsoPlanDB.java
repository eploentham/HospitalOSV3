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

public class MapNhsoPlanDB
{
    public ConnectionInf theConnectionInf;
    public MapNhsoPlan dbObj;
    final public String idtable = "";

    /**
     * @param ConnectionInf db
     * @roseuid 3F65897F0326
     */
    public MapNhsoPlanDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new MapNhsoPlan();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.table = "b_map_nhso_plan";
        dbObj.pk_field = "b_map_nhso_plan_id";
        dbObj.b_ws_nhso_right_id = "b_ws_nhso_right_id";
        dbObj.b_contract_plans_id = "b_contract_plans_id";
        dbObj.contract_plans_des = "contract_plans_description";
        dbObj.b_contract_id = "b_contract_id";
        return true;
    }

    public int insert(MapNhsoPlan p) throws Exception
    {
        String sql="";
        p.generateOID(this.idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.b_ws_nhso_right_id
        + " ,"  + dbObj.b_contract_plans_id
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.b_ws_nhso_right_id
        + "','" + p.b_contract_plans_id
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int update(MapNhsoPlan o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        String field =""
        + "', "	+ dbObj.b_ws_nhso_right_id  + "='" + o.b_ws_nhso_right_id
        + "', "	+ dbObj.b_contract_plans_id  + "='" + o.b_contract_plans_id
        + "' where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int delete(MapNhsoPlan of) throws Exception
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
    	String sql ="select b_map_nhso_plan.b_map_nhso_plan_id,b_map_nhso_plan.b_ws_nhso_right_id,b_map_nhso_plan.b_contract_plans_id"
                + ",b_contract_plans.contract_plans_description,"
                + "b_contract_plans.b_contract_id from b_map_nhso_plan inner join b_contract_plans"
                + " on b_map_nhso_plan.b_contract_plans_id = b_contract_plans.b_contract_plans_id"
                + " where b_map_nhso_plan.b_ws_nhso_right_id = '"+id+"'";
        return eQueryN(sql);
    }

    public Vector eQuery(String sql) throws Exception
    {
        MapNhsoPlan p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new MapNhsoPlan();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.b_ws_nhso_right_id = rs.getString(dbObj.b_ws_nhso_right_id);
            p.b_contract_plans_id = rs.getString(dbObj.b_contract_plans_id);
            list.add(p);
        }
        rs.close();
        return list;
    }
    public Vector eQueryN(String sql) throws Exception
    {
        MapNhsoPlan p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new MapNhsoPlan();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.b_ws_nhso_right_id = rs.getString(dbObj.b_ws_nhso_right_id);
            p.b_contract_plans_id = rs.getString(dbObj.b_contract_plans_id);
            p.contract_plans_des = rs.getString(dbObj.contract_plans_des);
            p.b_contract_id = rs.getString(dbObj.b_contract_id);
            list.add(p);
        }
        rs.close();
        return list;
    }
}