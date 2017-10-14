/*
 * CompanyDB.java
 *
 * Created on 11 ÁÔ¶Ø¹ÒÂ¹ 2548, 10:16 ¹.
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
public class CompanyDB {
    
    /** Creates a new instance of CompanyDB */
    public CompanyDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public Company dbObj;
    final private String idtable = "700";
    
    public CompanyDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Company();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_company";        
        dbObj.pk_field = "t_health_company_id";
        dbObj.village_id ="t_health_village_id";
        dbObj.company_number = "company_number";  
        dbObj.company_name = "company_name";        
        dbObj.company_staff_record ="company_staff_record";
        dbObj.company_staff_modify = "company_staff_modify";   
        dbObj.company_staff_cancel ="company_staff_cancel";
        dbObj.company_record_date_time ="company_record_date_time";
        dbObj.company_modify_date_time = "company_modify_date_time";   
        dbObj.company_cancel_date_time ="company_cancel_date_time";
        dbObj.company_close ="company_close";
        dbObj.company_close_date_time = "company_close_date_time";        
        dbObj.company_close_note = "company_close_note";
        dbObj.company_active = "company_active";  
        
        return true;
    }
    
    public int insert(Company o) throws Exception
    {
        String sql="";
        Company p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.village_id
        + " ,"  + dbObj.company_number
        + " ,"  + dbObj.company_name        
        + " ,"  + dbObj.company_staff_record
        + " ,"	+ dbObj.company_staff_modify
        + " ,"	+ dbObj.company_staff_cancel  
        + " ,"  + dbObj.company_record_date_time
        + " ,"  + dbObj.company_modify_date_time
        + " ,"	+ dbObj.company_cancel_date_time
        + " ,"	+ dbObj.company_close
        + " ,"	+ dbObj.company_close_date_time    
        + " ,"  + dbObj.company_close_note
        + " ,"	+ dbObj.company_active  
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.village_id
        + "','" + p.company_number
        + "','" + p.company_name        
        + "','" + p.company_staff_record
        + "','" + p.company_staff_modify
        + "','" + p.company_staff_cancel
        + "','" + p.company_record_date_time
        + "','" + p.company_modify_date_time
        + "','" + p.company_cancel_date_time
        + "','" + p.company_close
        + "','" + p.company_close_date_time
        + "','" + p.company_close_note
        + "','" + p.company_active
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(Company o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Company p=o;
        String field =""
        + "', " + dbObj.village_id + "='" + p.village_id
        + "', " + dbObj.company_number + "='" + p.company_number
        + "', " + dbObj.company_name + "='" + p.company_name        
        + "', " + dbObj.company_staff_record + "='" + p.company_staff_record
        + "', " + dbObj.company_staff_modify + "='" + p.company_staff_modify
        + "', " + dbObj.company_staff_cancel + "='" + p.company_staff_cancel        
        + "', " + dbObj.company_record_date_time + "='" + p.company_record_date_time
        + "', " + dbObj.company_modify_date_time + "='" + p.company_modify_date_time
        + "', " + dbObj.company_cancel_date_time + "='" + p.company_cancel_date_time
        + "', " + dbObj.company_close + "='" + p.company_close
        + "', " + dbObj.company_close_date_time + "='" + p.company_close_date_time    
        + "', " + dbObj.company_close_note + "='" + p.company_close_note
        + "', " + dbObj.company_active + "='" + p.company_active        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int updateCompanyToActiveZeroByVillageId(String villageId) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        String field =""
        + "', " + dbObj.company_active + "='0'"        
        + " where " + dbObj.village_id + "='" + villageId +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(Company o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listCompanyByNameOrNumber(String search,String villageId) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            if(search.trim().length() !=0)
            {
                sql = sql + "(" +  dbObj.company_number
                + " like '%" + search + "%'" + " or "
                + dbObj.company_name+ " like '%" + search + "%'" + ") and ";
            }
            if(!villageId.equals("0"))
            {
                sql = sql + dbObj.village_id + " = '" + villageId + "' and ";
            }
            sql = sql + dbObj.company_active + " = '1' order by " + dbObj.company_number;
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        Company p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Company();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.village_id = rs.getString(dbObj.village_id);
            p.company_number = rs.getString(dbObj.company_number);
            p.company_name = rs.getString(dbObj.company_name);            
            p.company_staff_record = rs.getString(dbObj.company_staff_record);
            p.company_staff_modify = rs.getString(dbObj.company_staff_modify);
            p.company_staff_cancel = rs.getString(dbObj.company_staff_cancel);  
            p.company_record_date_time = rs.getString(dbObj.company_record_date_time);
            p.company_modify_date_time = rs.getString(dbObj.company_modify_date_time);
            p.company_cancel_date_time = rs.getString(dbObj.company_cancel_date_time);
            p.company_close = rs.getString(dbObj.company_close);
            p.company_close_date_time = rs.getString(dbObj.company_close_date_time);  
            p.company_close_note = rs.getString(dbObj.company_close_note);
            p.company_active = rs.getString(dbObj.company_active);  
            list.add(p);
        }
        rs.close();
        return list;
    }
}
