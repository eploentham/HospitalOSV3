/*
 * NutritionTypeMapDB.java
 *
 * Created on 26 เมษายน 2549, 15:30 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.objdb;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author amp
 */
public class NutritionTypeMapDB
{
    public ConnectionInf theConnectionInf;
    public NutritionTypeMap dbObj;
    final public String idtable = "288";
    
    /** Creates a new instance of NutritionTypeMapDB */
    public NutritionTypeMapDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new NutritionTypeMap();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table="b_nutrition_map";
        dbObj.pk_field="b_nutrition_map_id";
        dbObj.nutrition_new="f_visit_nutrition_level_new";
        dbObj.nutrition_old="f_visit_nutrition_level_old";        
        return true;
    }
    
    public int insert(NutritionTypeMap o) throws Exception
    {
        String sql="";
        NutritionTypeMap p = o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.nutrition_new
        + " ,"	+ dbObj.nutrition_old        
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.nutrition_new
        + "','" + p.nutrition_old        
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(NutritionTypeMap o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        NutritionTypeMap p=o;
        String field =""
        + "', " + dbObj.nutrition_new + "='" + p.nutrition_new
        + "', " + dbObj.nutrition_old + "='" + p.nutrition_old
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public NutritionTypeMap selectByNutritionOld(String nutrition_old) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.nutrition_old
        + " = '" + nutrition_old + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (NutritionTypeMap)v.get(0);
    }
    
    public Vector listNutritionMap() throws Exception
    {
        Vector vc = new Vector();
        
        String sql = "SELECT " +
                "b_nutrition_map.b_nutrition_map_id, " +
                "b_nutrition_map.f_visit_nutrition_level_new, " +
                "f_visit_nutrition_level.visit_nutrition_level_description AS nutrition_new_description, " +
                "b_nutrition_map.f_visit_nutrition_level_old, " +
                "f_visit_nutrition_level_1.visit_nutrition_level_description AS nutrition_old_description " +
                "FROM f_visit_nutrition_level AS f_visit_nutrition_level_1 " +
                "RIGHT JOIN (f_visit_nutrition_level RIGHT JOIN b_nutrition_map ON " +
                "f_visit_nutrition_level.f_visit_nutrition_level_id = b_nutrition_map.f_visit_nutrition_level_new) ON " +
                "f_visit_nutrition_level_1.f_visit_nutrition_level_id = b_nutrition_map.f_visit_nutrition_level_old " +
                "ORDER BY b_nutrition_map.f_visit_nutrition_level_new ";
        
        return specialQuery(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        NutritionTypeMap p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new NutritionTypeMap();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.nutrition_new = rs.getString(dbObj.nutrition_new);
            p.nutrition_old = rs.getString(dbObj.nutrition_old);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public Vector specialQuery(String sql) throws Exception
    {
        NutritionTypeMap p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new NutritionTypeMap();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.nutrition_new = rs.getString(dbObj.nutrition_new);
            p.nutrition_new_description = rs.getString("nutrition_new_description");
            p.nutrition_old = rs.getString(dbObj.nutrition_old);            
            p.nutrition_old_description = rs.getString("nutrition_old_description");
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
