/*
 * PetDB.java
 *
 * Created on 11 ÁÔ¶Ø¹ÒÂ¹ 2548, 15:45 ¹.
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
public class PetDB {
    
    /** Creates a new instance of PetDB */
    public PetDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public Pet dbObj;
    final private String idtable = "721";
    
    public PetDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Pet();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_home_pet";        
        dbObj.pk_field = "t_health_home_pet_id";
        dbObj.home_id ="t_health_home_id";
        dbObj.pet_id = "b_health_home_pet_type_id";   
        dbObj.name ="health_home_pet_name";
        dbObj.sex = "f_health_home_pet_sex_id"; 
        dbObj.birthday = "health_home_pet_birthday";          
        dbObj.vaccine_lastdate = "health_home_pet_vaccine_lastdate";   
        dbObj.birth_control_lastdate ="health_home_pet_birth_control_lastdate";
        dbObj.staff_record = "pet_staff_record"; 
        dbObj.staff_modify = "pet_staff_modify";
        dbObj.staff_cancel = "pet_staff_cancel";
        dbObj.record_date_time = "pet_record_date_time";
        dbObj.modify_date_time = "pet_modify_date_time";
        dbObj.cancel_date_time = "pet_cancel_date_time";
        dbObj.pet_active = "pet_active";          
        
        return true;
    }
    
    public int insert(Pet o) throws Exception
    {
        String sql="";
        Pet p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.home_id
        + " ,"  + dbObj.pet_id
        + " ,"	+ dbObj.name
        + " ,"	+ dbObj.sex 
        + " ,"	+ dbObj.birthday   
        + " ,"  + dbObj.vaccine_lastdate
        + " ,"	+ dbObj.birth_control_lastdate
        + " ,"	+ dbObj.staff_record
        + " ,"	+ dbObj.staff_modify
        + " ,"	+ dbObj.staff_cancel
        + " ,"	+ dbObj.record_date_time
        + " ,"	+ dbObj.modify_date_time
        + " ,"	+ dbObj.cancel_date_time
        + " ,"	+ dbObj.pet_active
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.home_id
        + "','" + p.pet_id
        + "','" + p.name
        + "','" + p.sex 
        + "','" + p.birthday                 
        + "','" + p.vaccine_lastdate
        + "','" + p.birth_control_lastdate
        + "','" + p.staff_record
        + "','" + p.staff_modify   
        + "','" + p.staff_cancel   
        + "','" + p.record_date_time   
        + "','" + p.modify_date_time   
        + "','" + p.cancel_date_time                  
        + "','" + p.pet_active
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(Pet o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Pet p=o;
        String field =""
        + "', " + dbObj.home_id + "='" + p.home_id
        + "', " + dbObj.pet_id + "='" + p.pet_id
        + "', " + dbObj.name + "='" + p.name
        + "', " + dbObj.sex + "='" + p.sex 
        + "', " + dbObj.birthday + "='" + p.birthday                
        + "', " + dbObj.vaccine_lastdate + "='" + p.vaccine_lastdate
        + "', " + dbObj.birth_control_lastdate + "='" + p.birth_control_lastdate
        + "', " + dbObj.staff_record + "='" + p.staff_record
        + "', " + dbObj.staff_modify + "='" + p.staff_modify
        + "', " + dbObj.staff_cancel + "='" + p.staff_cancel
        + "', " + dbObj.record_date_time + "='" + p.record_date_time
        + "', " + dbObj.modify_date_time + "='" + p.modify_date_time
        + "', " + dbObj.cancel_date_time + "='" + p.cancel_date_time
        + "', " + dbObj.pet_active + "='" + p.pet_active
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(Pet o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
        public Vector listPetByHome(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            if(search.trim().length() !=0)
            {
                sql = sql + "(" +  dbObj.home_id
                + " like '%" + search + "%'" +  ") and ";
            }
            sql = sql + dbObj.pet_active + " = '1' order by " + dbObj.record_date_time;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
        
    public Vector eQuery(String sql) throws Exception
    {
        Pet p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Pet();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.home_id = rs.getString(dbObj.home_id);
            p.pet_id = rs.getString(dbObj.pet_id);
            p.name = rs.getString(dbObj.name);
            p.sex = rs.getString(dbObj.sex);
            p.birthday = rs.getString(dbObj.birthday);
            p.vaccine_lastdate = rs.getString(dbObj.vaccine_lastdate);
            p.birth_control_lastdate = rs.getString(dbObj.birth_control_lastdate);
            p.staff_record = rs.getString(dbObj.staff_record);
            p.staff_modify = rs.getString(dbObj.staff_modify);
            p.staff_cancel = rs.getString(dbObj.staff_cancel);
            p.record_date_time = rs.getString(dbObj.record_date_time);
            p.modify_date_time = rs.getString(dbObj.modify_date_time);
            p.cancel_date_time = rs.getString(dbObj.cancel_date_time);
            p.pet_active = rs.getString(dbObj.pet_active);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
