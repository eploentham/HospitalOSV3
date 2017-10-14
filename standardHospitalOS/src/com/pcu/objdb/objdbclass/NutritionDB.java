/*
 * NutritionDB.java
 *
 * Created on 23 ÁÔ¶Ø¹ÒÂ¹ 2548, 11:27 ¹.
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
public class NutritionDB {
    
    /** Creates a new instance of NutritionDB */
    public NutritionDB() {
    }
    
     public ConnectionInf theConnectionInf;
    
    public Nutrition dbObj;
    final private String idtable = "728";
    
    public NutritionDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Nutrition();
        initConfig(); 
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_nutrition";        
        dbObj.pk_field = "t_health_nutrition_id";        
        dbObj.patient_id="t_patient_id";
        dbObj.nutrition_vn="health_nutrition_vn";
        dbObj.office_id="b_visit_office_id";
        dbObj.visit_id="t_visit_id";
        dbObj.nutrition_hn="health_nutrition_hn";
        dbObj.nutrition_age="health_nutrition_age";
        dbObj.nutrition_rim="health_nutrition_rim";
        dbObj.nutrition_weight="health_nutrition_weight";
        dbObj.nutrition_high="health_nutrition_high";
        dbObj.nutrition_newtooth="health_nutrition_newtooth";
        dbObj.nutrition_badtooth="health_nutrition_badtooth";
        dbObj.answer_id="f_answer_id";
        dbObj.nutrition_level_id="f_health_nutrition_level_id";
        dbObj.nutrition_bmi = "health_nutrition_bmi";
        dbObj.nutrition_result="health_nutrition_result";
        dbObj.nutrition_notice="health_nutrition_notice";
        dbObj.nutrition_staff_record="health_nutrition_staff_record";
        dbObj.nutrition_staff_modify="health_nutrition_staff_modify";
        dbObj.nutrition_staff_cancel="health_nutrition_staff_cancel";
        dbObj.record_date_time="record_date_time";
        dbObj.modify_date_time="modify_date_time";
        dbObj.cancel_date_time="cancel_date_time";
        dbObj.active="health_nutrition_active";
        dbObj.family_id="t_health_family_id";
        dbObj.survey_date = "nutrition_survey_date";
//        dbObj.nutrition53 = "r_rp1853_nutritionlevel_id";
               
        return true;
    }
    
    public int insert(Nutrition o) throws Exception
    {
        String sql="";
        Nutrition p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field        
        + " ,"  + dbObj.patient_id
        + " ,"  + dbObj.nutrition_vn
        + " ,"  + dbObj.office_id
        + " ,"  + dbObj.visit_id
        + " ,"  + dbObj.nutrition_hn
        + " ,"  + dbObj.nutrition_age
        + " ,"  + dbObj.nutrition_rim
        + " ,"  + dbObj.nutrition_weight
        + " ,"  + dbObj.nutrition_high
        + " ,"  + dbObj.nutrition_newtooth
        + " ,"  + dbObj.nutrition_badtooth
        + " ,"  + dbObj.answer_id
        + " ,"  + dbObj.nutrition_level_id
        + " ,"  + dbObj.nutrition_bmi
        + " ,"  + dbObj.nutrition_result       
        + " ,"  + dbObj.nutrition_notice
        + " ,"  + dbObj.nutrition_staff_record
        + " ,"  + dbObj.nutrition_staff_modify
        + " ,"  + dbObj.nutrition_staff_cancel
        + " ,"  + dbObj.record_date_time
        + " ,"  + dbObj.modify_date_time
        + " ,"  + dbObj.cancel_date_time
        + " ,"  + dbObj.active
        + " ,"  + dbObj.family_id        
        + " ,"  + dbObj.survey_date
//        + " ,"  + dbObj.nutrition53
        + " ) values ('"
        + p.getObjectId()
        
        + "','" +p.patient_id
        + "','" +p.nutrition_vn
        + "','" +p.office_id
        + "','" +p.visit_id
        + "','" +p.nutrition_hn
        + "','" +p.nutrition_age
        + "','" +p.nutrition_rim
        + "','" +p.nutrition_weight
        + "','" +p.nutrition_high
        + "','" +p.nutrition_newtooth
        + "','" +p.nutrition_badtooth
        + "','" +p.answer_id
        + "','" +p.nutrition_level_id
        + "','" +p.nutrition_bmi
        + "','" +p.nutrition_result        
        + "','" +p.nutrition_notice
        + "','" +p.nutrition_staff_record
        + "','" +p.nutrition_staff_modify
        + "','" +p.nutrition_staff_cancel
        + "','" +p.record_date_time
        + "','" +p.modify_date_time
        + "','" +p.cancel_date_time
        + "','" +p.active
        + "','" +p.family_id
        + "','" +p.survey_date
//        + "','" +p.nutrition53
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    public int update(Nutrition o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Nutrition p=o;
        String field =""        
        + "', " + dbObj.patient_id+ "='" + p.patient_id
        + "', " + dbObj.nutrition_vn+ "='" + p.nutrition_vn
        + "', " + dbObj.office_id+ "='" + p.office_id
        + "', " + dbObj.visit_id+ "='" + p.visit_id
        + "', " + dbObj.nutrition_hn+ "='" + p.nutrition_hn
        + "', " + dbObj.nutrition_age+ "='" + p.nutrition_age
        + "', " + dbObj.nutrition_rim+ "='" + p.nutrition_rim
        + "', " + dbObj.nutrition_weight+ "='" + p.nutrition_weight
        + "', " + dbObj.nutrition_high+ "='" + p.nutrition_high
        + "', " + dbObj.nutrition_newtooth+ "='" + p.nutrition_newtooth
        + "', " + dbObj.nutrition_badtooth+ "='" + p.nutrition_badtooth
        + "', " + dbObj.answer_id+ "='" + p.answer_id
        + "', " + dbObj.nutrition_level_id+ "='" + p.nutrition_level_id
        + "', " + dbObj.nutrition_bmi+ "='" + p.nutrition_bmi
        + "', " + dbObj.nutrition_result+ "='" + p.nutrition_result
        + "', " + dbObj.nutrition_notice+ "='" + p.nutrition_notice
        + "', " + dbObj.nutrition_staff_record+ "='" + p.nutrition_staff_record
        + "', " + dbObj.nutrition_staff_modify+ "='" + p.nutrition_staff_modify
        + "', " + dbObj.nutrition_staff_cancel+ "='" + p.nutrition_staff_cancel
        + "', " + dbObj.record_date_time+ "='" + p.record_date_time
        + "', " + dbObj.modify_date_time+ "='" + p.modify_date_time
        + "', " + dbObj.cancel_date_time+ "='" + p.cancel_date_time
        + "', " + dbObj.active+ "='" + p.active
        + "', " + dbObj.family_id+ "='" + p.family_id
        + "', " + dbObj.survey_date+ "='" + p.survey_date
//        + "', " + dbObj.nutrition53+ "='" + p.nutrition53
            + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
     public Vector selectByPatientID(String search) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.patient_id
                + " = '" + search + "'"
                +" and " + dbObj.active+ " = '1'" 
                +" order by "+dbObj.record_date_time;
        return eQuery(sql);
    }
    
    public Vector selectByFamilyID(String search) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.family_id
                + " = '" + search + "'"
                +" and " + dbObj.active+ " = '1'" 
                +" order by "+dbObj.modify_date_time;
        return eQuery(sql);
    } 
     
    public int delete(Nutrition o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
       
    
    public Vector eQuery(String sql) throws Exception
    {
        Nutrition p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Nutrition();
            p.setObjectId(rs.getString(dbObj.pk_field));            
            p.patient_id = rs.getString(dbObj.patient_id);
            p.nutrition_vn = rs.getString(dbObj.nutrition_vn);
            p.office_id = rs.getString(dbObj.office_id);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.nutrition_hn = rs.getString(dbObj.nutrition_hn);
            p.nutrition_age = rs.getString(dbObj.nutrition_age);
            p.nutrition_rim = rs.getString(dbObj.nutrition_rim);
            p.nutrition_weight = rs.getString(dbObj.nutrition_weight);
            p.nutrition_high = rs.getString(dbObj.nutrition_high);
            p.nutrition_newtooth = rs.getString(dbObj.nutrition_newtooth);
            p.nutrition_badtooth = rs.getString(dbObj.nutrition_badtooth);
            p.answer_id = rs.getString(dbObj.answer_id);
            p.nutrition_level_id = rs.getString(dbObj.nutrition_level_id);
            p.nutrition_bmi = rs.getString(dbObj.nutrition_bmi);
            p.nutrition_result = rs.getString(dbObj.nutrition_result);
            p.nutrition_notice = rs.getString(dbObj.nutrition_notice);
            p.nutrition_staff_record = rs.getString(dbObj.nutrition_staff_record);
            p.nutrition_staff_modify = rs.getString(dbObj.nutrition_staff_modify);
            p.nutrition_staff_cancel = rs.getString(dbObj.nutrition_staff_cancel);
            p.record_date_time = rs.getString(dbObj.record_date_time);
            p.modify_date_time = rs.getString(dbObj.modify_date_time);
            p.cancel_date_time = rs.getString(dbObj.cancel_date_time);
            p.active = rs.getString(dbObj.active);
            p.family_id = rs.getString(dbObj.family_id);
            p.survey_date = rs.getString(dbObj.survey_date);
//            p.nutrition53 = rs.getString(dbObj.nutrition53);
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
                + dbObj.nutrition_staff_cancel + "="+ dbObj.nutrition_staff_cancel + "||'-'||'" + family_from + "'"
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
