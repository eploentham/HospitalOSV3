/*
 * TempleDB.java
 *
 * Created on 11 ÁÔ¶Ø¹ÒÂ¹ 2548, 15:26 ¹.
 */

package com.pcu.objdb.objdbclass;

import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
/**
 *
 * @author amp
 */
public class TempleDB {
    
    /** Creates a new instance of TempleDB */
    public TempleDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public Temple dbObj;
    final private String idtable = "703";
    
    public TempleDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Temple();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_temple";        
        dbObj.pk_field = "t_health_temple_id";
        dbObj.village_id ="t_health_village_id";
        dbObj.temple_number = "temple_number";   
        dbObj.temple_name ="temple_name";
        dbObj.temple_home_number = "temple_home_number";
        dbObj.temple_moo ="temple_moo";
        dbObj.temple_road = "temple_road";   
        dbObj.temple_phone ="temple_phone";
        dbObj.temple_tambol = "temple_tambol";    
        dbObj.temple_amphur ="temple_amphur";
        dbObj.temple_changwat = "temple_changwat";   
        dbObj.temple_religion_type ="temple_religion_type";
        dbObj.temple_type = "temple_type";
        dbObj.temple_staff_record ="temple_staff_record";
        dbObj.temple_staff_modify = "temple_staff_modify";   
        dbObj.temple_staff_cancel ="temple_staff_cancel";
        dbObj.temple_record_date_time ="temple_record_date_time";
        dbObj.temple_modify_date_time = "temple_modify_date_time";   
        dbObj.temple_cancel_date_time ="temple_cancel_date_time";
        dbObj.temple_active = "temple_active";  
        
        return true;
    }
    
    public int insert(Temple o) throws Exception
    {
        String sql="";
        Temple p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.village_id
        + " ,"  + dbObj.temple_number
        + " ,"	+ dbObj.temple_name   
        + " ,"  + dbObj.temple_home_number
        + " ,"  + dbObj.temple_moo
        + " ,"	+ dbObj.temple_road            
        + " ,"  + dbObj.temple_phone
        + " ,"  + dbObj.temple_tambol
        + " ,"	+ dbObj.temple_amphur
        + " ,"	+ dbObj.temple_changwat  
        + " ,"  + dbObj.temple_religion_type
        + " ,"  + dbObj.temple_type
        + " ,"  + dbObj.temple_staff_record
        + " ,"  + dbObj.temple_staff_modify
        + " ,"	+ dbObj.temple_staff_cancel
        + " ,"	+ dbObj.temple_record_date_time  
        + " ,"  + dbObj.temple_modify_date_time
        + " ,"  + dbObj.temple_cancel_date_time
        + " ,"	+ dbObj.temple_active        
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.village_id
        + "','" + p.temple_number
        + "','" + p.temple_name
        + "','" + p.temple_home_number
        + "','" + p.temple_moo
        + "','" + p.temple_road
        + "','" + p.temple_phone
        + "','" + p.temple_tambol
        + "','" + p.temple_amphur
        + "','" + p.temple_changwat
        + "','" + p.temple_religion_type
        + "','" + p.temple_type
        + "','" + p.temple_staff_record
        + "','" + p.temple_staff_modify
        + "','" + p.temple_staff_cancel
        + "','" + p.temple_record_date_time
        + "','" + p.temple_modify_date_time
        + "','" + p.temple_cancel_date_time
        + "','" + p.temple_active        
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(Temple o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Temple p=o;
        String field =""
        + "', " + dbObj.village_id + "='" + p.village_id
        + "', " + dbObj.temple_number + "='" + p.temple_number
        + "', " + dbObj.temple_name + "='" + p.temple_name
        + "', " + dbObj.temple_home_number + "='" + p.temple_home_number
        + "', " + dbObj.temple_moo + "='" + p.temple_moo
        + "', " + dbObj.temple_road + "='" + p.temple_road
        + "', " + dbObj.temple_phone + "='" + p.temple_phone    
        + "', " + dbObj.temple_tambol + "='" + p.temple_tambol
        + "', " + dbObj.temple_amphur + "='" + p.temple_amphur
        + "', " + dbObj.temple_changwat + "='" + p.temple_changwat
        + "', " + dbObj.temple_religion_type + "='" + p.temple_religion_type        
        + "', " + dbObj.temple_type + "='" + p.temple_type   
        + "', " + dbObj.temple_staff_record + "='" + p.temple_staff_record
        + "', " + dbObj.temple_staff_modify + "='" + p.temple_staff_modify
        + "', " + dbObj.temple_staff_cancel + "='" + p.temple_staff_cancel
        + "', " + dbObj.temple_record_date_time + "='" + p.temple_record_date_time        
        + "', " + dbObj.temple_modify_date_time + "='" + p.temple_modify_date_time
        + "', " + dbObj.temple_cancel_date_time + "='" + p.temple_cancel_date_time
        + "', " + dbObj.temple_active + "='" + p.temple_active              
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int updateTempleToActiveZeroByVillageId(String villageId) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        String field =""        
        + "', " + dbObj.temple_active + "='0'"             
        + " where " + dbObj.village_id + "='" + villageId +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(Temple o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listTempleByNameOrNumber(String search,String villageId) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            if(search.trim().length() !=0)
            {
                sql = sql + "(" +  dbObj.temple_number
                + " like '%" + search + "%'" + " or "
                + dbObj.temple_name+ " like '%" + search + "%'" + ") and ";
            }
            if(!villageId.equals("0"))
            {
                sql = sql + dbObj.village_id + " = '" + villageId + "' and ";
            }
            sql = sql + dbObj.temple_active + " = '1' order by " + dbObj.temple_number;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }    
    
    public Vector eQuery(String sql) throws Exception
    {
        Temple p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Temple();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.village_id = rs.getString(dbObj.village_id);
            p.temple_number = rs.getString(dbObj.temple_number);
            p.temple_name = rs.getString(dbObj.temple_name);
            p.temple_home_number = rs.getString(dbObj.temple_home_number);
            p.temple_moo = rs.getString(dbObj.temple_moo);
            p.temple_road = rs.getString(dbObj.temple_road);
            p.temple_phone = rs.getString(dbObj.temple_phone);  
            p.temple_tambol = rs.getString(dbObj.temple_tambol);
            p.temple_amphur = rs.getString(dbObj.temple_amphur);
            p.temple_changwat = rs.getString(dbObj.temple_changwat);
            p.temple_religion_type = rs.getString(dbObj.temple_religion_type);  
            p.temple_type = rs.getString(dbObj.temple_type);  
            p.temple_staff_record = rs.getString(dbObj.temple_staff_record);
            p.temple_staff_modify = rs.getString(dbObj.temple_staff_modify);
            p.temple_staff_cancel = rs.getString(dbObj.temple_staff_cancel);
            p.temple_record_date_time = rs.getString(dbObj.temple_record_date_time);  
            p.temple_modify_date_time = rs.getString(dbObj.temple_modify_date_time);
            p.temple_cancel_date_time = rs.getString(dbObj.temple_cancel_date_time);
            p.temple_active = rs.getString(dbObj.temple_active);
            list.add(p);
        }
        rs.close();
        return list;
    }

    public Vector selectByNumber(String temple_number) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            sql = sql + dbObj.temple_number + " = '" + temple_number + "' and ";
            sql = sql + dbObj.temple_active + " = '1' order by t_health_village_id,temple_number";
        return eQuery(sql);
    }

    public Temple selectByPK(String id) throws Exception {
        
        String sql="select * from " + dbObj.table + " where ";            
            sql = sql + dbObj.pk_field + " = '" + id + "'";
        
        Vector v = eQuery(sql);
        if(v.isEmpty())
            return null;
        else
            return (Temple)v.get(0);
    }

    public Vector selectByName(String temple_name) throws Exception  {
        String sql="select * from " + dbObj.table + " where ";            
            sql = sql + dbObj.temple_name + " = '" + temple_name + "' and ";
            sql = sql + dbObj.temple_active + " = '1' order by " + dbObj.temple_number;
        
        return eQuery(sql);
    }

    public Vector selectByNameVillage(String temple_name, String village_id) throws Exception  {
        String sql="select * from " + dbObj.table + 
                " where " + dbObj.temple_name + " = '" + temple_name + "' " +
                " and " + dbObj.village_id + " = '" + village_id + "' " +
                " and " + dbObj.temple_active + " = '1'" +
                " order by " + dbObj.temple_number;
        return eQuery(sql);
    }

    public Vector selectByNumberVillage(String temple_number, String village_id) throws Exception  {
        String sql="select * from " + dbObj.table + 
                " where " + dbObj.temple_number + " = '" + temple_number + "' " +
                " and " + dbObj.village_id + " = '" + village_id + "' " +
                " and " + dbObj.temple_active + " = '1'" +
                " order by " + dbObj.temple_number;
        return eQuery(sql);
    }
    
}
