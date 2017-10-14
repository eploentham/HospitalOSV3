/*
 * SchoolDB.java
 *
 * Created on 11 ÁÔ¶Ø¹ÒÂ¹ 2548, 10:15 ¹.
 */

package com.pcu.objdb.objdbclass;

import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.pcu.object.*;

/**
 *
 * @author amp
 */
public class SchoolDB {
    
    /** Creates a new instance of SchoolDB */
    public SchoolDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public School dbObj;
    final private String idtable = "704";
    
    public SchoolDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new School();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_school";        
        dbObj.pk_field = "t_health_school_id";
        dbObj.village_id ="t_health_village_id";
        dbObj.school_number = "school_number";   
        dbObj.school_name ="school_name";
        dbObj.school_staff_record ="school_staff_record";
        dbObj.school_staff_modify = "school_staff_modify";   
        dbObj.school_staff_cancel ="school_staff_cancel";
        dbObj.school_record_date_time ="school_record_date_time";
        dbObj.school_modify_date_time = "school_modify_date_time";   
        dbObj.school_cancel_date_time ="school_cancel_date_time";
        dbObj.school_close = "school_close";   
        dbObj.school_close_date_time ="school_close_date_time";
        dbObj.school_close_note ="school_close_note";
        dbObj.school_active = "school_active";  
        
        return true;
    }
    
    public int insert(School o) throws Exception
    {
        String sql="";
        School p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.village_id
        + " ,"  + dbObj.school_number
        + " ,"	+ dbObj.school_name
        + " ,"  + dbObj.school_staff_record
        + " ,"	+ dbObj.school_staff_modify
        + " ,"	+ dbObj.school_staff_cancel  
        + " ,"  + dbObj.school_record_date_time
        + " ,"  + dbObj.school_modify_date_time
        + " ,"	+ dbObj.school_cancel_date_time
        + " ,"  + dbObj.school_close
        + " ,"	+ dbObj.school_close_date_time
        + " ,"	+ dbObj.school_close_note
        + " ,"	+ dbObj.school_active  
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.village_id
        + "','" + p.school_number
        + "','" + p.school_name
        + "','" + p.school_staff_record
        + "','" + p.school_staff_modify
        + "','" + p.school_staff_cancel
        + "','" + p.school_record_date_time
        + "','" + p.school_modify_date_time
        + "','" + p.school_cancel_date_time
        + "','" + p.school_close
        + "','" + p.school_close_date_time
        + "','" + p.school_close_note
        + "','" + p.school_active
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(School o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        School p=o;
        String field =""
        + "', " + dbObj.village_id + "='" + p.village_id
        + "', " + dbObj.school_number + "='" + p.school_number
        + "', " + dbObj.school_name + "='" + p.school_name
        + "', " + dbObj.school_staff_record + "='" + p.school_staff_record
        + "', " + dbObj.school_staff_modify + "='" + p.school_staff_modify
        + "', " + dbObj.school_staff_cancel + "='" + p.school_staff_cancel        
        + "', " + dbObj.school_record_date_time + "='" + p.school_record_date_time
        + "', " + dbObj.school_modify_date_time + "='" + p.school_modify_date_time
        + "', " + dbObj.school_cancel_date_time + "='" + p.school_cancel_date_time
        + "', " + dbObj.school_close + "='" + p.school_close
        + "', " + dbObj.school_close_date_time + "='" + p.school_close_date_time
        + "', " + dbObj.school_close_note + "='" + p.school_close_note
        + "', " + dbObj.school_active + "='" + p.school_active        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int updateSchoolToActiveZeroByVillageId(String villageId) throws Exception
    {       
        String sql="update " + dbObj.table + " set";
        String field =""        
        + "', " + dbObj.school_active + " = '0'"     
        + " where " + dbObj.village_id + " = '" + villageId +"'";
        sql = sql+field.substring(2);
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(School o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listSchoolByNameOrNumber(String search,String villageId) throws Exception
    {
        String sql="select * from " + dbObj.table + " where ";            
            if(search.trim().length() !=0)
            {
                sql = sql + "(" +  dbObj.school_number
                + " like '%" + search + "%'" + " or "
                + dbObj.school_name+ " like '%" + search + "%'" + ") and ";
            }            
            if(!villageId.equals("0"))
            {
                sql = sql + dbObj.village_id + " = '" + villageId + "' and ";
            }
            sql = sql + dbObj.school_active + " = '1' order by t_health_village_id,school_number";
            
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        School p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new School();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.village_id = rs.getString(dbObj.village_id);
            p.school_number = rs.getString(dbObj.school_number);
            p.school_name = rs.getString(dbObj.school_name);
            p.school_staff_record = rs.getString(dbObj.school_staff_record);
            p.school_staff_modify = rs.getString(dbObj.school_staff_modify);
            p.school_staff_cancel = rs.getString(dbObj.school_staff_cancel);  
            p.school_record_date_time = rs.getString(dbObj.school_record_date_time);
            p.school_modify_date_time = rs.getString(dbObj.school_modify_date_time);
            p.school_cancel_date_time = rs.getString(dbObj.school_cancel_date_time);
            p.school_close = rs.getString(dbObj.school_close);
            p.school_close_date_time = rs.getString(dbObj.school_close_date_time);
            p.school_close_note = rs.getString(dbObj.school_close_note);
            p.school_active = rs.getString(dbObj.school_active);  
            list.add(p);
        }
        rs.close();
        return list;
    }
    
     public Vector selectAll() throws Exception
    {   
        Vector vSchool = new Vector();
        
        String sql ="select * from " 
                + dbObj.table + " where " + dbObj.school_active + " = '1' order by "
                + dbObj.school_number;
        
        vSchool = veQuery(sql);
        
        if(vSchool.size()==0)
            return null;
        else
            return vSchool;        
    }
    
    public Vector selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "' and " + dbObj.school_active + " ='1'  order by " + dbObj.school_staff_record + " desc" ;    
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
     
     
    public Vector veQuery(String sql) throws Exception
    {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ComboFix();
            p.code = rs.getString(dbObj.pk_field);
            p.name = rs.getString(dbObj.school_name);
            list.add(p);
        }
        rs.close();
        return list;
    }

    public Vector selectByNoVillage(String school_number, String village_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.village_id + " = '" + village_id + "' " +
        " and " + dbObj.school_number + " ='" + school_number + "' " +
        " and " + dbObj.school_active + " ='1'" +
        "  order by " + dbObj.school_staff_record + " desc" ;    
        return eQuery(sql);
    }
}
