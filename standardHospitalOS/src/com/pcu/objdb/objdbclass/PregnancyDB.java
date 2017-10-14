/*
 * PregnancyDB.java
 *
 * Created on 7 �á�Ҥ� 2548, 11:16 �.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
import com.hospital_os.utility.*;
/**
 *
 * @author Administrator
 */
public class PregnancyDB {
    
    /** Creates a new instance of PregnancyDB */
    public PregnancyDB() {
    }
    
    public ConnectionInf theConnectionInf;
    
    public Pregnancy dbObj;
    final private String idtable = "779";
    
    public PregnancyDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Pregnancy();
        initConfig(); 
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_pregnancy";        
        dbObj.pk_field = "t_health_pregnancy_id";        
        dbObj.pregnancy_hn="health_pregnancy_hn";
        dbObj.pregnancy_vn="health_pregnancy_vn";
        dbObj.office_id="b_visit_office_id";
        dbObj.pregnancy_gravida_number="health_pregnancy_gravida_number";
        dbObj.pregnancy_menses_issue_date="health_pregnancy_menses_issue_date";
        dbObj.pregnancy_menses_expire_date="health_pregnancy_menses_expire_date";
        dbObj.pregnancy_birthcontrol="health_pregnancy_birthcontrol";
        dbObj.pregnancy_abnomal="health_pregnancy_abnomal";
        dbObj.pregnancy_result="health_pregnancy_result";
        dbObj.pregnancy_notice="health_pregnancy_notice";
        dbObj.pregnancy_staff_record="health_pregnancy_staff_record";
        dbObj.pregnancy_staff_modify="health_pregnancy_staff_modify";
        dbObj.pregnancy_staff_cancel="health_pregnancy_staff_cancel";
        dbObj.record_date_time="record_date_time";
        dbObj.modify_date_time="modify_date_time";
        dbObj.cancel_date_time="cancel_date_time";
        dbObj.visit_id="t_visit_id";
        dbObj.patient_id="t_patient_id";
        dbObj.active="health_pregnancy_active";
        dbObj.family_id ="t_health_family_id";
        dbObj.survey_date = "health_pregnancy_survey_date";
        dbObj.pregnancy_p="pregnancy_p";
        dbObj.pregnancy_a="pregnancy_a";
        dbObj.pregnancy_l="pregnancy_l";
        
        return true;
    }
    
    public int insert(Pregnancy o) throws Exception
    {
        String sql="";
        Pregnancy p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field        
        + " ,"  + dbObj.pregnancy_hn	
        + " ,"  + dbObj.pregnancy_vn	
        + " ,"  + dbObj.office_id	
        + " ,"  + dbObj.pregnancy_gravida_number	
        + " ,"  + dbObj.pregnancy_menses_issue_date	
        + " ,"  + dbObj.pregnancy_menses_expire_date	
        + " ,"  + dbObj.pregnancy_birthcontrol	
        + " ,"  + dbObj.pregnancy_abnomal	
        + " ,"  + dbObj.pregnancy_result	
        + " ,"  + dbObj.pregnancy_notice	
        + " ,"  + dbObj.pregnancy_staff_record	
        + " ,"  + dbObj.pregnancy_staff_modify	
        + " ,"  + dbObj.pregnancy_staff_cancel	
        + " ,"  + dbObj.record_date_time	
        + " ,"  + dbObj.modify_date_time	
        + " ,"  + dbObj.cancel_date_time	
        + " ,"  + dbObj.visit_id	
        + " ,"  + dbObj.patient_id	
        + " ,"  + dbObj.active	
        + " ,"  + dbObj.family_id
        + " ,"  + dbObj.survey_date
        + " ) values ('"
        + p.getObjectId()
        
        + "','" + p.pregnancy_hn
        + "','" + p.pregnancy_vn
        + "','" + p.office_id
        + "','" + p.pregnancy_gravida_number
        + "','" + p.pregnancy_menses_issue_date
        + "','" + p.pregnancy_menses_expire_date
        + "','" + p.pregnancy_birthcontrol
        + "','" + p.pregnancy_abnomal
        + "','" + p.pregnancy_result
        + "','" + p.pregnancy_notice
        + "','" + p.pregnancy_staff_record
        + "','" + p.pregnancy_staff_modify
        + "','" + p.pregnancy_staff_cancel
        + "','" + p.record_date_time
        + "','" + p.modify_date_time
        + "','" + p.cancel_date_time
        + "','" + p.visit_id
        + "','" + p.patient_id
        + "','" + p.active
        + "','" + p.family_id
        + "','" + p.survey_date
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    public int insertN(Pregnancy o) throws Exception
    {
        String sql="";
        Pregnancy p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"  + dbObj.pregnancy_hn
        + " ,"  + dbObj.pregnancy_vn
        + " ,"  + dbObj.office_id
        + " ,"  + dbObj.pregnancy_gravida_number
        + " ,"  + dbObj.pregnancy_menses_issue_date
        + " ,"  + dbObj.pregnancy_menses_expire_date
        + " ,"  + dbObj.pregnancy_birthcontrol
        + " ,"  + dbObj.pregnancy_abnomal
        + " ,"  + dbObj.pregnancy_result
        + " ,"  + dbObj.pregnancy_notice
        + " ,"  + dbObj.pregnancy_staff_record
        + " ,"  + dbObj.pregnancy_staff_modify
        + " ,"  + dbObj.pregnancy_staff_cancel
        + " ,"  + dbObj.record_date_time
        + " ,"  + dbObj.modify_date_time
        + " ,"  + dbObj.cancel_date_time
        + " ,"  + dbObj.visit_id
        + " ,"  + dbObj.patient_id
        + " ,"  + dbObj.active
        + " ,"  + dbObj.family_id
        + " ,"  + dbObj.survey_date
        + " ,"  + dbObj.pregnancy_p
        + " ,"  + dbObj.pregnancy_a
        + " ,"  + dbObj.pregnancy_l
        + " ) values ('"
        + p.getObjectId()

        + "','" + p.pregnancy_hn
        + "','" + p.pregnancy_vn
        + "','" + p.office_id
        + "','" + p.pregnancy_gravida_number
        + "','" + p.pregnancy_menses_issue_date
        + "','" + p.pregnancy_menses_expire_date
        + "','" + p.pregnancy_birthcontrol
        + "','" + p.pregnancy_abnomal
        + "','" + p.pregnancy_result
        + "','" + p.pregnancy_notice
        + "','" + p.pregnancy_staff_record
        + "','" + p.pregnancy_staff_modify
        + "','" + p.pregnancy_staff_cancel
        + "','" + p.record_date_time
        + "','" + p.modify_date_time
        + "','" + p.cancel_date_time
        + "','" + p.visit_id
        + "','" + p.patient_id
        + "','" + p.active
        + "','" + p.family_id
        + "','" + p.survey_date
        + "','" + p.pregnancy_p
        + "','" + p.pregnancy_a
        + "','" + p.pregnancy_l
        + "')";

        return theConnectionInf.eUpdate(sql);
    }
    public int update(Pregnancy o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Pregnancy p=o;
        String field =""        
        + "', " + dbObj.pregnancy_hn+ "='" + p.pregnancy_hn
        + "', " + dbObj.pregnancy_vn+ "='" + p.pregnancy_vn
        + "', " + dbObj.office_id+ "='" + p.office_id
        + "', " + dbObj.pregnancy_gravida_number+ "='" + p.pregnancy_gravida_number
        + "', " + dbObj.pregnancy_menses_issue_date+ "='" + p.pregnancy_menses_issue_date
        + "', " + dbObj.pregnancy_menses_expire_date+ "='" + p.pregnancy_menses_expire_date
        + "', " + dbObj.pregnancy_birthcontrol+ "='" + p.pregnancy_birthcontrol
        + "', " + dbObj.pregnancy_abnomal+ "='" + p.pregnancy_abnomal
        + "', " + dbObj.pregnancy_result+ "='" + p.pregnancy_result
        + "', " + dbObj.pregnancy_notice+ "='" + p.pregnancy_notice
        + "', " + dbObj.pregnancy_staff_record+ "='" + p.pregnancy_staff_record
        + "', " + dbObj.pregnancy_staff_modify+ "='" + p.pregnancy_staff_modify
        + "', " + dbObj.pregnancy_staff_cancel+ "='" + p.pregnancy_staff_cancel
        + "', " + dbObj.record_date_time+ "='" + p.record_date_time
        + "', " + dbObj.modify_date_time+ "='" + p.modify_date_time
        + "', " + dbObj.cancel_date_time+ "='" + p.cancel_date_time
        + "', " + dbObj.visit_id+ "='" + p.visit_id
        + "', " + dbObj.patient_id+ "='" + p.patient_id
        + "', " + dbObj.active+ "='" + p.active
        + "', " + dbObj.family_id+ "='" + p.family_id        
        + "', " + dbObj.survey_date+ "='" + p.survey_date        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    public int updateN(Pregnancy o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Pregnancy p=o;
        String field =""
        + "', " + dbObj.pregnancy_hn+ "='" + p.pregnancy_hn
        + "', " + dbObj.pregnancy_vn+ "='" + p.pregnancy_vn
        + "', " + dbObj.office_id+ "='" + p.office_id
        + "', " + dbObj.pregnancy_gravida_number+ "='" + p.pregnancy_gravida_number
        + "', " + dbObj.pregnancy_menses_issue_date+ "='" + p.pregnancy_menses_issue_date
        + "', " + dbObj.pregnancy_menses_expire_date+ "='" + p.pregnancy_menses_expire_date
        + "', " + dbObj.pregnancy_birthcontrol+ "='" + p.pregnancy_birthcontrol
        + "', " + dbObj.pregnancy_abnomal+ "='" + p.pregnancy_abnomal
        + "', " + dbObj.pregnancy_result+ "='" + p.pregnancy_result
        + "', " + dbObj.pregnancy_notice+ "='" + p.pregnancy_notice
        + "', " + dbObj.pregnancy_staff_record+ "='" + p.pregnancy_staff_record
        + "', " + dbObj.pregnancy_staff_modify+ "='" + p.pregnancy_staff_modify
        + "', " + dbObj.pregnancy_staff_cancel+ "='" + p.pregnancy_staff_cancel
        + "', " + dbObj.record_date_time+ "='" + p.record_date_time
        + "', " + dbObj.modify_date_time+ "='" + p.modify_date_time
        + "', " + dbObj.cancel_date_time+ "='" + p.cancel_date_time
        + "', " + dbObj.visit_id+ "='" + p.visit_id
        + "', " + dbObj.patient_id+ "='" + p.patient_id
        + "', " + dbObj.active+ "='" + p.active
        + "', " + dbObj.family_id+ "='" + p.family_id
        + "', " + dbObj.survey_date+ "='" + p.survey_date
        + "', " + dbObj.pregnancy_p+ "='" + p.pregnancy_p
        + "', " + dbObj.pregnancy_a+ "='" + p.pregnancy_a
        + "', " + dbObj.pregnancy_l+ "='" + p.pregnancy_l
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectByPatientID(String patient_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.patient_id
        + " = '" + patient_id + "'"
        + " and " + dbObj.active + " = '1' order by " + dbObj.record_date_time;
        return eQuery(sql);
    }
    public Vector selectByFamilyID(String family_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.family_id
        + " = '" + family_id + "'"
        + " and " + dbObj.active + " = '1' order by " + dbObj.record_date_time;
        return eQuery(sql);
    }
    public Pregnancy readByFamilyIDAndGravida(String family_id,String gravida) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.family_id
        + " = '" + family_id + "' and " + dbObj.pregnancy_gravida_number + " ='" + gravida + "'"
        + " and " + dbObj.active + " = '1' order by " + dbObj.record_date_time;
        Vector v = eQuery(sql);
        if(v.isEmpty())
            return null;
        else
            return (Pregnancy)v.get(0);
    }
    public int delete(Pregnancy o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
        
    public Vector eQuery(String sql) throws Exception
    {
        Pregnancy p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Pregnancy();
            p.setObjectId(rs.getString(dbObj.pk_field));            
            p. pregnancy_hn= rs.getString(dbObj. pregnancy_hn);
            p.pregnancy_vn= rs.getString(dbObj.pregnancy_vn);
            p.office_id= rs.getString(dbObj.office_id);
            p.pregnancy_gravida_number= rs.getString(dbObj.pregnancy_gravida_number);
            p.pregnancy_menses_issue_date= rs.getString(dbObj.pregnancy_menses_issue_date);
            p.pregnancy_menses_expire_date= rs.getString(dbObj.pregnancy_menses_expire_date);
            p.pregnancy_birthcontrol= rs.getString(dbObj.pregnancy_birthcontrol);
            p.pregnancy_abnomal= rs.getString(dbObj.pregnancy_abnomal);
            p.pregnancy_result= rs.getString(dbObj.pregnancy_result);
            p.pregnancy_notice= rs.getString(dbObj.pregnancy_notice);
            p.pregnancy_staff_record= rs.getString(dbObj.pregnancy_staff_record);
            p.pregnancy_staff_modify= rs.getString(dbObj.pregnancy_staff_modify);
            p.pregnancy_staff_cancel= rs.getString(dbObj.pregnancy_staff_cancel);
            p.record_date_time= rs.getString(dbObj.record_date_time);
            p.modify_date_time= rs.getString(dbObj.modify_date_time);
            p.cancel_date_time= rs.getString(dbObj.cancel_date_time);
            p.visit_id= rs.getString(dbObj.visit_id);
            p.patient_id= rs.getString(dbObj.patient_id);
            p.active= rs.getString(dbObj.active);
            p.family_id= rs.getString(dbObj.family_id);
            p.survey_date= rs.getString(dbObj.survey_date);
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
                + dbObj.pregnancy_staff_cancel + "="+ dbObj.pregnancy_staff_cancel + "||'-'||'" + family_from + "'"
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