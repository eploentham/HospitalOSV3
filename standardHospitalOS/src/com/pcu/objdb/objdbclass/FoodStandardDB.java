/*
 * FoodStandardDB.java
 *
 * Created on 11 ÁÔ¶Ø¹ÒÂ¹ 2548, 15:27 ¹.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
/**
 *
 * @author Administrator
 */
public class FoodStandardDB {
    
    /** Creates a new instance of FoodStandardDB */
    public FoodStandardDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public FoodStandard dbObj;
    final private String idtable = "718";
    
    public FoodStandardDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new FoodStandard();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_home_food_standard";        
        dbObj.pk_field = "t_health_home_food_standard_id";
        dbObj.sub_home_id ="t_health_sub_home_id";
        dbObj.mixture_food = "health_home_mixture_food";   
        dbObj.food_vessel ="health_home_food_vessel";
        dbObj.food_cover = "health_home_food_cover"; 
        dbObj.food_wash = "health_home_food_wash";
        dbObj.food_keep = "health_home_food_keep";
        dbObj.kitchen_garbage = "health_home_kitchen_garbage";
        dbObj.kitchen_cleanness = "health_home_kitchen_cleanness";
        dbObj.food_standard = "health_home_food_standard";
        dbObj.iodine = "health_home_iodine";
        dbObj.salt_iodine = "health_home_salt_iodine";
        dbObj.product_iodine = "health_home_product_iodine";
        
        return true;
    }
    
    public int insert(FoodStandard o) throws Exception
    {
        String sql="";
        FoodStandard p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.sub_home_id
        + " ,"  + dbObj.mixture_food
        + " ,"	+ dbObj.food_vessel
        + " ,"	+ dbObj.food_cover
        + " ,"	+ dbObj.food_wash
        + " ,"	+ dbObj.food_keep
        + " ,"	+ dbObj.kitchen_garbage
        + " ,"	+ dbObj.kitchen_cleanness
        + " ,"	+ dbObj.food_standard
        + " ,"	+ dbObj.iodine
        + " ,"	+ dbObj.salt_iodine
        + " ,"	+ dbObj.product_iodine
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.sub_home_id
        + "','" + p.mixture_food
        + "','" + p.food_vessel
        + "','" + p.food_cover
        + "','" + p.food_wash   
        + "','" + p.food_keep   
        + "','" + p.kitchen_garbage   
        + "','" + p.kitchen_cleanness   
        + "','" + p.food_standard   
        + "','" + p.iodine   
        + "','" + p.salt_iodine   
        + "','" + p.product_iodine   
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(FoodStandard o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        FoodStandard p=o;
        String field =""
        + "', " + dbObj.sub_home_id + "='" + p.sub_home_id
        + "', " + dbObj.mixture_food + "='" + p.mixture_food
        + "', " + dbObj.food_vessel + "='" + p.food_vessel
        + "', " + dbObj.food_cover + "='" + p.food_cover
        + "', " + dbObj.food_wash + "='" + p.food_wash
        + "', " + dbObj.food_keep + "='" + p.food_keep
        + "', " + dbObj.kitchen_garbage + "='" + p.kitchen_garbage
        + "', " + dbObj.kitchen_cleanness + "='" + p.kitchen_cleanness
        + "', " + dbObj.food_standard + "='" + p.food_standard
        + "', " + dbObj.iodine + "='" + p.iodine
        + "', " + dbObj.salt_iodine + "='" + p.salt_iodine
        + "', " + dbObj.product_iodine + "='" + p.product_iodine
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(FoodStandard o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
     public Vector listFoodBySubHome(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            
                sql = sql + dbObj.sub_home_id
                + " like '%" + search + "%'";
          
        return eQuery(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        FoodStandard p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new FoodStandard();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.sub_home_id = rs.getString(dbObj.sub_home_id);
            p.mixture_food = rs.getString(dbObj.mixture_food);
            p.food_vessel = rs.getString(dbObj.food_vessel);
            p.food_cover = rs.getString(dbObj.food_cover);
            p.food_wash = rs.getString(dbObj.food_wash);
            p.food_keep = rs.getString(dbObj.food_keep);
            p.kitchen_garbage = rs.getString(dbObj.kitchen_garbage);
            p.kitchen_cleanness = rs.getString(dbObj.kitchen_cleanness);
            p.food_standard = rs.getString(dbObj.food_standard);
            p.iodine = rs.getString(dbObj.iodine);
            p.salt_iodine = rs.getString(dbObj.salt_iodine);
            p.product_iodine = rs.getString(dbObj.product_iodine);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
