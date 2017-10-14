/*
 * GrowHistoryDB.java
 *
 * Created on 26 ÁÔ¶Ø¹ÒÂ¹ 2548, 16:54 ¹.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 

import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;

import com.pcu.object.*;
/**
 *
 * @author Administrator
 */
public class GrowHistoryDB {
    
    /** Creates a new instance of GrowHistoryDB */
    public GrowHistoryDB() {
    }
    
    
     public ConnectionInf theConnectionInf;
    
    public GrowHistory dbObj;
    final private String idtable = "744";
    
    public GrowHistoryDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new GrowHistory();
        initConfig(); 
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_grow_history";        
        dbObj.pk_field = "t_health_grow_history_id";        
        dbObj.health_grow_id = "t_health_grow_id";
        dbObj.patient_id = "t_patient_id";
        dbObj.visit_id = "t_visit_id";
        dbObj.grow_id = "f_health_grow_id";
        dbObj.standard_grow = "standard_grow_history_age";
        dbObj.real_grow = "real_grow_history_age";
        dbObj.notice = "health_grow_history_notice";
        dbObj.record_date_time = "record_date_time";        
        dbObj.modify_date_time = "modify_date_time";        
        dbObj.cancel_date_time = "cancel_date_time";        
        dbObj.staff_record = "health_grow_history_staff_record";
        dbObj.staff_modify = "health_grow_history_staff_modify";
        dbObj.staff_cancel = "health_grow_history_staff_cancel";
        dbObj.active = "health_grow_history_active";
        dbObj.family_id = "t_health_family_id";
        dbObj.survey_date = "grow_survey_date";
        return true;
    }
    
    public int insert(GrowHistory o) throws Exception
    {
        String sql="";
        GrowHistory p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field        
        + " ,"  + dbObj.health_grow_id
        + " ,"  + dbObj.patient_id
        + " ,"  + dbObj.visit_id
        + " ,"  + dbObj.grow_id
        + " ,"  + dbObj.standard_grow
        + " ,"  + dbObj.real_grow
        + " ,"  + dbObj.notice              
        + " ,"  + dbObj.record_date_time
        + " ,"  + dbObj.modify_date_time
        + " ,"  + dbObj.cancel_date_time
        + " ,"  + dbObj.staff_record
        + " ,"  + dbObj.staff_modify
        + " ,"  + dbObj.staff_cancel
        + " ,"  + dbObj.active
        + " ,"  + dbObj.family_id
        + " ,"  + dbObj.survey_date
        + " ) values ('"
        + p.getObjectId()
        
        + "','" +p.health_grow_id
        + "','" +p.patient_id
        + "','" +p.visit_id
        + "','" +p.grow_id
        + "','" +p.standard_grow
        + "','" +p.real_grow
        + "','" +p.notice            
        + "','" +p.record_date_time
        + "','" +p.modify_date_time
        + "','" +p.cancel_date_time
        + "','" +p.staff_record
        + "','" +p.staff_modify
        + "','" +p.staff_cancel
        + "','" +p.active 
        + "','" +p.family_id
        + "','" +p.survey_date
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    public int update(GrowHistory o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        GrowHistory p=o;
        String field =""        
        + "', " + dbObj.health_grow_id+ "='" + p.health_grow_id
        + "', " + dbObj.patient_id+ "='" + p.patient_id
        + "', " + dbObj.visit_id+ "='" + p.visit_id
        + "', " + dbObj.grow_id+ "='" + p.grow_id
        + "', " + dbObj.standard_grow+ "='" + p.standard_grow
        + "', " + dbObj.real_grow+ "='" + p.real_grow
        + "', " + dbObj.notice+ "='" + p.notice 
        + "', " + dbObj.record_date_time+ "='" + p.record_date_time
        + "', " + dbObj.modify_date_time+ "='" + p.modify_date_time
        + "', " + dbObj.cancel_date_time+ "='" + p.cancel_date_time
        + "', " + dbObj.staff_record+ "='" + p.staff_record
        + "', " + dbObj.staff_modify+ "='" + p.staff_modify
        + "', " + dbObj.staff_cancel+ "='" + p.staff_cancel 
        + "', " + dbObj.active+ "='" + p.active        
        + "', " + dbObj.family_id+ "='" + p.family_id
        + "', " + dbObj.survey_date+ "='" + p.survey_date
            + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }    
    
    public int delete(GrowHistory o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
       
     public Vector selectByPatientID(String patient_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.patient_id
        + " = '" + patient_id + "'"
        + " and " + dbObj.active + " = '1' " + " order by "
        + dbObj.record_date_time;
        return eQuery(sql);
    }
     
     public Vector selectByFamilyID(String family_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.family_id
        + " = '" + family_id + "'"
        + " and " + dbObj.active + " = '1' " + " order by "
        + dbObj.record_date_time;
        return eQuery(sql);
    } 
    
    public Vector eQuery(String sql) throws Exception
    {
        GrowHistory p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new GrowHistory();
            p.setObjectId(rs.getString(dbObj.pk_field));            
            p.health_grow_id = rs.getString(dbObj.health_grow_id);
            p.patient_id = rs.getString(dbObj.patient_id);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.grow_id = rs.getString(dbObj.grow_id);
            p.standard_grow = rs.getString(dbObj.standard_grow);
            p.real_grow = rs.getString(dbObj.real_grow);
            p.notice = rs.getString(dbObj.notice);
            p.record_date_time = rs.getString(dbObj.record_date_time);
            p.modify_date_time = rs.getString(dbObj.modify_date_time);
            p.cancel_date_time = rs.getString(dbObj.cancel_date_time);
            p.staff_record = rs.getString(dbObj.staff_record);
            p.staff_modify = rs.getString(dbObj.staff_modify);
            p.staff_cancel = rs.getString(dbObj.staff_cancel);   
            p.active = rs.getString(dbObj.active);
            p.family_id = rs.getString(dbObj.family_id);
            p.survey_date = rs.getString(dbObj.survey_date);
            list.add(p);
        }
        rs.close();
        return list;
    }
/////////////////////////////////////////////////////////////////////////////    
    public int updateFidByFid(String family_id,String family_from) throws Exception
    {
        String sql="update " + dbObj.table + " set " 
                + dbObj.family_id + "='" + family_id + "',"
                + dbObj.staff_cancel + "="+ dbObj.staff_cancel + "||'-'||'" + family_from + "'"
                + " where " + dbObj.family_id + "='" + family_from +"'";
        return theConnectionInf.eUpdate(sql);
    }  
/////////////////////////////////////////////////////////////////////////////    
    public int updateFidByPtid(String family_id,String patient_id) throws Exception
    {
        String sql="update " + dbObj.table + " set " 
                + dbObj.family_id + "='" + family_id
                + "' where " + dbObj.patient_id + "='" + patient_id +"'";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }     


    public int selectCount() throws Exception {
             String sql="select count(*) as count from " + dbObj.table;
             ResultSet rs = theConnectionInf.eQuery(sql);
             if(rs.next()){
                 return rs.getInt("count");
             }
             return 0;
    }
}
