/*
 * SchoolHistoryDB.java
 *
 * Created on 29 ÁÔ¶Ø¹ÒÂ¹ 2548, 16:09 ¹.
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
public class SchoolHistoryDB {
    
    /** Creates a new instance of SchoolHistoryDB */
    public SchoolHistoryDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public SchoolHistory dbObj;
    final private String idtable = "706";
    
    public SchoolHistoryDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new SchoolHistory();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_school_history";        
        dbObj.pk_field = "t_health_school_history_id";
        dbObj.school_id ="t_health_school_id";
        dbObj.school_history_max_class = "b_health_school_max_class_id";   
        dbObj.school_history_owner ="f_health_community_resorce_owner_id";
        dbObj.school_history_owner_other ="school_history_owner_other";
        dbObj.school_history_standard_type = "f_health_community_standard_type_id"; 
        dbObj.school_history_standard_type_other = "school_history_standard_type_other";
        dbObj.school_history_standard = "f_health_community_standard_id";       
        dbObj.school_history_time_of_pass ="school_history_time_of_pass";
        dbObj.school_history_date_of_pass = "school_history_date_of_pass";        
        dbObj.school_history_period_of_pass = "school_history_period_of_pass";        
        dbObj.school_history_amount_student_m ="school_history_amount_student_m";
        dbObj.school_history_amount_student_fm = "school_history_amount_student_fm";        
        dbObj.school_history_amount_teacher_m = "school_history_amount_teacher_m";        
        dbObj.school_history_amount_teacher_fm ="school_history_amount_teacher_fm";
        dbObj.school_history_staff_record = "school_history_staff_record";  
        dbObj.school_history_staff_modify = "school_history_staff_modify";
        dbObj.school_history_record_date_time = "school_history_record_date_time";
        dbObj.school_history_modify_date_time = "school_history_modify_date_time";
        
        return true;
    }
    
    public int insert(SchoolHistory o) throws Exception
    {
        String sql="";
        SchoolHistory p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.school_id
        + " ,"  + dbObj.school_history_max_class
        + " ,"	+ dbObj.school_history_owner
        + " ,"	+ dbObj.school_history_owner_other
        + " ,"	+ dbObj.school_history_standard_type  
        + " ,"	+ dbObj.school_history_standard_type_other
        + " ,"  + dbObj.school_history_standard
        + " ,"  + dbObj.school_history_time_of_pass
        + " ,"  + dbObj.school_history_date_of_pass
        + " ,"	+ dbObj.school_history_period_of_pass
        + " ,"	+ dbObj.school_history_amount_student_m    
        + " ,"  + dbObj.school_history_amount_student_fm
        + " ,"  + dbObj.school_history_amount_teacher_m
        + " ,"  + dbObj.school_history_amount_teacher_fm
        + " ,"	+ dbObj.school_history_staff_record
        + " ,"	+ dbObj.school_history_staff_modify
        + " ,"	+ dbObj.school_history_record_date_time    
        + " ,"  + dbObj.school_history_modify_date_time
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.school_id
        + "','" + p.school_history_max_class
        + "','" + p.school_history_owner
        + "','" + p.school_history_owner_other
        + "','" + p.school_history_standard_type
        + "','" + p.school_history_standard_type_other
        + "','" + p.school_history_standard
        + "','" + p.school_history_time_of_pass
        + "','" + p.school_history_date_of_pass
        + "','" + p.school_history_period_of_pass
        + "','" + p.school_history_amount_student_m
        + "','" + p.school_history_amount_student_fm
        + "','" + p.school_history_amount_teacher_m
        + "','" + p.school_history_amount_teacher_fm
        + "','" + p.school_history_staff_record
        + "','" + p.school_history_staff_modify        
        + "','" + p.school_history_record_date_time
        + "','" + p.school_history_modify_date_time
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(SchoolHistory o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        SchoolHistory p=o;
        String field =""
        + "', " + dbObj.school_id + "='" + p.school_id
        + "', " + dbObj.school_history_max_class + "='" + p.school_history_max_class
        + "', " + dbObj.school_history_owner + "='" + p.school_history_owner
        + "', " + dbObj.school_history_owner_other + "='" + p.school_history_owner_other
        + "', " + dbObj.school_history_standard_type + "='" + p.school_history_standard_type
        + "', " + dbObj.school_history_standard_type_other + "='" + p.school_history_standard_type_other
        + "', " + dbObj.school_history_standard + "='" + p.school_history_standard 
        + "', " + dbObj.school_history_time_of_pass + "='" + p.school_history_time_of_pass
        + "', " + dbObj.school_history_date_of_pass + "='" + p.school_history_date_of_pass
        + "', " + dbObj.school_history_period_of_pass + "='" + p.school_history_period_of_pass
        + "', " + dbObj.school_history_amount_student_m + "='" + p.school_history_amount_student_m
        + "', " + dbObj.school_history_amount_student_fm + "='" + p.school_history_amount_student_fm
        + "', " + dbObj.school_history_amount_teacher_m + "='" + p.school_history_amount_teacher_m
        + "', " + dbObj.school_history_amount_teacher_fm + "='" + p.school_history_amount_teacher_fm
        + "', " + dbObj.school_history_staff_record + "='" + p.school_history_staff_record
        + "', " + dbObj.school_history_staff_modify + "='" + p.school_history_staff_modify        
        + "', " + dbObj.school_history_record_date_time + "='" + p.school_history_record_date_time
        + "', " + dbObj.school_history_modify_date_time + "='" + p.school_history_modify_date_time
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(CompanyHistory o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listSchoolHistoryBySchoolId(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.school_id + " = '" + search + "' order by "
                + dbObj.school_history_record_date_time;            
        
        return eQuery(sql);
    }
    
    public Vector listSchoolHistoryBySchoolIdDesc(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.school_id + " = '" + search + "' order by "
                + dbObj.school_history_record_date_time+ " DESC";            
        
        return eQuery(sql);
    }
    
    public SchoolHistory readSchoolHistoryBySchoolId(String search) throws Exception
    {
        String sql="select * from " + dbObj.table + " where "
                + dbObj.school_id + " = '" + search + "' order by "
                + dbObj.school_history_record_date_time + " desc";          
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (SchoolHistory)v.get(0);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        SchoolHistory p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new SchoolHistory();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.school_id = rs.getString(dbObj.school_id);
            p.school_history_max_class = rs.getString(dbObj.school_history_max_class);
            p.school_history_owner = rs.getString(dbObj.school_history_owner);
            p.school_history_owner_other = rs.getString(dbObj.school_history_owner_other);
            p.school_history_standard_type = rs.getString(dbObj.school_history_standard_type);
            p.school_history_standard_type_other = rs.getString(dbObj.school_history_standard_type_other);
            p.school_history_standard = rs.getString(dbObj.school_history_standard);
            p.school_history_time_of_pass = rs.getString(dbObj.school_history_time_of_pass);
            p.school_history_date_of_pass = rs.getString(dbObj.school_history_date_of_pass);
            p.school_history_period_of_pass = rs.getString(dbObj.school_history_period_of_pass);
            p.school_history_amount_student_m = rs.getString(dbObj.school_history_amount_student_m);  
            p.school_history_amount_student_fm = rs.getString(dbObj.school_history_amount_student_fm);
            p.school_history_amount_teacher_m = rs.getString(dbObj.school_history_amount_teacher_m);
            p.school_history_amount_teacher_fm = rs.getString(dbObj.school_history_amount_teacher_fm);
            p.school_history_staff_record = rs.getString(dbObj.school_history_staff_record);
            p.school_history_staff_modify = rs.getString(dbObj.school_history_staff_modify);
            p.school_history_record_date_time = rs.getString(dbObj.school_history_record_date_time);  
            p.school_history_modify_date_time = rs.getString(dbObj.school_history_modify_date_time);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
