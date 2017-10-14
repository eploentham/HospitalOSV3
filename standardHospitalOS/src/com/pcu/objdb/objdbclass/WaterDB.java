/*
 * WaterDB.java
 *
 * Created on 13 ÁÔ¶Ø¹ÒÂ¹ 2548, 9:34 ¹.
 */

package com.pcu.objdb.objdbclass;

//import com.hospital_os.object.WaterType;
import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
/**
 *
 * @author amp
 */
public class WaterDB {
    
    /** Creates a new instance of WaterDB */
    public WaterDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public Water dbObj;
    public WaterType dbObj2;
    final private String idtable = "707";
    
    public WaterDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Water();
        dbObj2 = new WaterType();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_water";        
        dbObj.pk_field = "t_health_water_id";
        dbObj.village_id ="t_health_village_id";
        dbObj.water_number = "water_number";   
        dbObj.water_type ="b_health_water_type_id";
        dbObj.water_construct = "water_construct_id";
        dbObj.water_construct_description = "water_construct_description";
        dbObj.water_supervise = "water_supervise_id";
        dbObj.water_supervise_description = "water_supervise_description";
        dbObj.water_staff_record ="water_staff_record";
        dbObj.water_staff_modify = "water_staff_modify";   
        dbObj.water_staff_cancel ="water_staff_cancel";
        dbObj.water_record_date_time ="water_record_date_time";
        dbObj.water_modify_date_time = "water_modify_date_time";   
        dbObj.water_cancel_date_time ="water_cancel_date_time";
        dbObj.water_active = "water_active";  
        
        dbObj2.table = "b_health_water_type";        
        dbObj2.pk_field = "b_health_water_type_id";
        dbObj2.number ="water_type_number";
        dbObj2.description = "water_type_description";  
        dbObj2.active = "water_type_active";
        
        return true;
    }
    
    public int insert(Water o) throws Exception
    {
        String sql="";
        Water p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.village_id
        + " ,"  + dbObj.water_number
        + " ,"	+ dbObj.water_type
        + " ,"	+ dbObj.water_construct   
        + " ,"  + dbObj.water_construct_description
        + " ,"  + dbObj.water_supervise
        + " ,"	+ dbObj.water_supervise_description
        + " ,"  + dbObj.water_staff_record
        + " ,"  + dbObj.water_staff_modify
        + " ,"	+ dbObj.water_staff_cancel
        + " ,"	+ dbObj.water_record_date_time  
        + " ,"  + dbObj.water_modify_date_time
        + " ,"  + dbObj.water_cancel_date_time
        + " ,"	+ dbObj.water_active       
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.village_id
        + "','" + p.water_number
        + "','" + p.water_type
        + "','" + p.water_construct
        + "','" + p.water_construct_description
        + "','" + p.water_supervise
        + "','" + p.water_supervise_description
        + "','" + p.water_staff_record
        + "','" + p.water_staff_modify
        + "','" + p.water_staff_cancel
        + "','" + p.water_record_date_time
        + "','" + p.water_modify_date_time
        + "','" + p.water_cancel_date_time
        + "','" + p.water_active
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(Water o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Water p=o;
        String field =""
        + "', " + dbObj.village_id + "='" + p.village_id
        + "', " + dbObj.water_number + "='" + p.water_number
        + "', " + dbObj.water_type + "='" + p.water_type
        + "', " + dbObj.water_construct + "='" + p.water_construct  
        + "', " + dbObj.water_construct_description + "='" + p.water_construct_description
        + "', " + dbObj.water_supervise + "='" + p.water_supervise
        + "', " + dbObj.water_supervise_description + "='" + p.water_supervise_description
        + "', " + dbObj.water_staff_record + "='" + p.water_staff_record
        + "', " + dbObj.water_staff_modify + "='" + p.water_staff_modify
        + "', " + dbObj.water_staff_cancel + "='" + p.water_staff_cancel
        + "', " + dbObj.water_record_date_time + "='" + p.water_record_date_time        
        + "', " + dbObj.water_modify_date_time + "='" + p.water_modify_date_time
        + "', " + dbObj.water_cancel_date_time + "='" + p.water_cancel_date_time
        + "', " + dbObj.water_active + "='" + p.water_active
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int updateWaterToActiveZeroByVillageId(String villageId) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        String field =""
        + "', " + dbObj.water_active + "='0'"
        + " where " + dbObj.village_id + "='" + villageId +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(Water o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listWaterByNumber(String search,String villageId) throws Exception
    {
        String sql="select * from " + dbObj.table +" left join "+dbObj2.table + " on "
                +dbObj.table+"."+dbObj.water_type+" = "+dbObj2.table+"."+dbObj2.pk_field
                + " where ";     
            if(search.trim().length() !=0)
            {
                sql = sql + dbObj.water_number
                + " like '%" + search + "%'" 
                +" or "+dbObj2.description +" like '%"+search+"%'" 
                +" and ";                
            }
            if(!villageId.equals("0"))
            {
                sql = sql + dbObj.village_id + " = '" + villageId + "' and ";
            }
            sql = sql + dbObj.water_active + " = '1' order by " + dbObj.water_number;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    public Vector selectByNumberVid(String waterNumber,String villageId) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.water_number + " = '" + waterNumber + "' and "
                + dbObj.village_id + " = '" + villageId + "' and "
                + dbObj.water_active + " = '1'";            
        
        return eQuery(sql);
    }
    public Water readWaterByWaterNumberAngVillageId(String waterNumber,String villageId) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.water_number + " = '" + waterNumber + "' and "
                + dbObj.village_id + " = '" + villageId + "' and "
                + dbObj.water_active + " = '1' ";            
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (Water)v.get(0);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        Water p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Water();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.village_id = rs.getString(dbObj.village_id);
            p.water_number = rs.getString(dbObj.water_number);
            p.water_type = rs.getString(dbObj.water_type);
            p.water_construct = rs.getString(dbObj.water_construct);  
            p.water_construct_description = rs.getString(dbObj.water_construct_description);
            p.water_supervise = rs.getString(dbObj.water_supervise);
            p.water_supervise_description = rs.getString(dbObj.water_supervise_description);
            p.water_staff_record = rs.getString(dbObj.water_staff_record);
            p.water_staff_modify = rs.getString(dbObj.water_staff_modify);
            p.water_staff_cancel = rs.getString(dbObj.water_staff_cancel);
            p.water_record_date_time = rs.getString(dbObj.water_record_date_time);  
            p.water_modify_date_time = rs.getString(dbObj.water_modify_date_time);
            p.water_cancel_date_time = rs.getString(dbObj.water_cancel_date_time);
            p.water_active = rs.getString(dbObj.water_active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
