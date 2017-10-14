/*
 * AncPcuDB.java
 *
 * Created on 7 กรกฎาคม 2548, 11:16 น.
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
public class AncPcuDB {
    
    /** Creates a new instance of AncPcuDB */
    public AncPcuDB() {
    }
    
    public ConnectionInf theConnectionInf;
    
    public AncPcu dbObj;
    final private String idtable = "774";
    
    public AncPcuDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new AncPcu();
        initConfig(); 
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_anc";        
        dbObj.pk_field = "t_health_anc_id";        
        dbObj.anc_vn="health_anc_vn";
        dbObj.pregnancy_id="t_health_pregnancy_id";
        dbObj.anc_section="f_health_anc_section";
        dbObj.anc_gravida_week="health_anc_gravida_week";
        dbObj.anc_exam="health_anc_exam";
        dbObj.anc_exam_description = "health_anc_exam_description";
        dbObj.birth_high_risk_id="f_health_pregnancy_birth_high_risk_id";
        dbObj.anc_vdrl="health_anc_vdrl";
        dbObj.anc_thalassemia="health_anc_thalassemia";
        dbObj.anc_hb="health_anc_hb";
        dbObj.anc_hiv="health_anc_hiv";
        dbObj.anc_hct="health_anc_hct";
        dbObj.anc_hct_date="health_anc_hct_date";
        dbObj.anc_ancres="health_anc_ancres";
        dbObj.anc_tt="health_anc_tt";
        dbObj.anc_dental_exam="health_anc_dental_exam";
        dbObj.anc_dental_gum="health_anc_dental_gum";
        dbObj.anc_dental_tartar="health_anc_dental_tartar";
        dbObj.anc_dental_caries="health_anc_dental_caries";
        dbObj.anc_weight="health_anc_weight";
        dbObj.anc_high="health_anc_high";
        dbObj.anc_bmi="health_anc_bmi";
        dbObj.anc_notice="health_anc_notice";
        dbObj.visit_id="t_visit_id";
        dbObj.patient_id="t_patient_id";
        dbObj.anc_hn="health_anc_hn";
        dbObj.staff_record="health_anc_staff_record";
        dbObj.staff_modify="health_anc_staff_modify";
        dbObj.staff_cancel="health_anc_staff_cancel";
        dbObj.record_date_time="record_date_time";
        dbObj.modify_date_time="modify_date_time";
        dbObj.cancel_date_time="cancel_date_time";        
        dbObj.active="health_anc_active";
        dbObj.delivery_status="health_anc_delivery_status";
        dbObj.pressure="health_anc_pressure";
        dbObj.no = "health_anc_no";
        dbObj.anc_gravida_day = "health_anc_gravida_day";
        dbObj.family_id = "t_health_family_id";
        dbObj.anc_baby_thalassemia = "health_anc_baby_thalassemia";
        dbObj.survey_date = "health_anc_survey";
        dbObj.anc_hct_result = "health_anc_hct_result";
        dbObj.anc_tt_type = "f_health_anc_tt_type";
//        dbObj.anc_visit_office = "health_anc_visit_office";
        return true;
    }
    
    public int insert(AncPcu o) throws Exception
    {
        String sql="";
        AncPcu p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field        
        + " ,"  + dbObj.anc_vn
        + " ,"  + dbObj.pregnancy_id
        + " ,"  + dbObj.anc_section
        + " ,"  + dbObj.anc_gravida_week
        + " ,"  + dbObj.anc_exam
        + " ,"  + dbObj.anc_exam_description
        + " ,"  + dbObj.birth_high_risk_id
        + " ,"  + dbObj.anc_vdrl
        + " ,"  + dbObj.anc_thalassemia
        + " ,"  + dbObj.anc_hb
        + " ,"  + dbObj.anc_hiv
        + " ,"  + dbObj.anc_hct
        + " ,"  + dbObj.anc_hct_date
        + " ,"  + dbObj.anc_ancres
        + " ,"  + dbObj.anc_tt
        + " ,"  + dbObj.anc_dental_exam
        + " ,"  + dbObj.anc_dental_gum
        + " ,"  + dbObj.anc_dental_tartar
        + " ,"  + dbObj.anc_dental_caries
        + " ,"  + dbObj.anc_weight
        + " ,"  + dbObj.anc_high
        + " ,"  + dbObj.anc_bmi
        + " ,"  + dbObj.anc_notice
        + " ,"  + dbObj.visit_id
        + " ,"  + dbObj.patient_id
        + " ,"  + dbObj.anc_hn
        + " ,"  + dbObj.staff_record
        + " ,"  + dbObj.staff_modify
        + " ,"  + dbObj.staff_cancel
        + " ,"  + dbObj.record_date_time
        + " ,"  + dbObj.modify_date_time
        + " ,"  + dbObj.cancel_date_time        
        + " ,"  + dbObj.active
        + " ,"  + dbObj.delivery_status
        + " ,"  + dbObj.pressure
        + " ,"  + dbObj.no
        + " ,"  + dbObj.anc_gravida_day        
        + " ,"  + dbObj.family_id
        + " ,"  + dbObj.anc_baby_thalassemia
        + " ,"  + dbObj.survey_date
        + " ,"  + dbObj.anc_hct_result
        + " ,"  + dbObj.anc_tt_type
//        + " ,"  + dbObj.anc_visit_office
        + " ) values ('"
        + p.getObjectId()        
        + "','" + p.anc_vn
        + "','" + p.pregnancy_id
        + "','" + p.anc_section
        + "','" + p.anc_gravida_week
        + "','" + p.anc_exam
        + "','" + p.anc_exam_description        
        + "','" + p.birth_high_risk_id
        + "','" + p.anc_vdrl
        + "','" + p.anc_thalassemia
        + "','" + p.anc_hb
        + "','" + p.anc_hiv
        + "','" + p.anc_hct
        + "','" + p.anc_hct_date
        + "','" + p.anc_ancres
        + "','" + p.anc_tt
        + "','" + p.anc_dental_exam
        + "','" + p.anc_dental_gum
        + "','" + p.anc_dental_tartar
        + "','" + p.anc_dental_caries
        + "','" + p.anc_weight
        + "','" + p.anc_high
        + "','" + p.anc_bmi
        + "','" + p.anc_notice
        + "','" + p.visit_id
        + "','" + p.patient_id
        + "','" + p.anc_hn
        + "','" + p.staff_record
        + "','" + p.staff_modify
        + "','" + p.staff_cancel
        + "','" + p.record_date_time
        + "','" + p.modify_date_time
        + "','" + p.cancel_date_time       
        + "','" + p.active
        + "','" + p.delivery_status
        + "','" + p.pressure
        + "','" + p.no
        + "','" + p.anc_gravida_day
        + "','" + p.family_id
        + "','" + p.anc_baby_thalassemia
        + "','" + p.survey_date
        + "','" + p.anc_hct_result
        + "','" + p.anc_tt_type
//        + "','" + p.anc_visit_office
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    public int update(AncPcu o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        AncPcu p=o;
        String field =""        
        + "', " + dbObj.anc_vn+ "='" + p.anc_vn
        + "', " + dbObj.pregnancy_id+ "='" + p.pregnancy_id
        + "', " + dbObj.anc_section+ "='" + p.anc_section
        + "', " + dbObj.anc_gravida_week+ "='" + p.anc_gravida_week
        + "', " + dbObj.anc_exam+ "='" + p.anc_exam
        + "', " + dbObj.anc_exam_description+ "='" + p.anc_exam_description
        + "', " + dbObj.birth_high_risk_id+ "='" + p.birth_high_risk_id
        + "', " + dbObj.anc_vdrl+ "='" + p.anc_vdrl
        + "', " + dbObj.anc_thalassemia+ "='" + p.anc_thalassemia
        + "', " + dbObj.anc_hb+ "='" + p.anc_hb
        + "', " + dbObj.anc_hiv+ "='" + p.anc_hiv
        + "', " + dbObj.anc_hct+ "='" + p.anc_hct
        + "', " + dbObj.anc_hct_date+ "='" + p.anc_hct_date
        + "', " + dbObj.anc_ancres+ "='" + p.anc_ancres
        + "', " + dbObj.anc_tt+ "='" + p.anc_tt
        + "', " + dbObj.anc_dental_exam+ "='" + p.anc_dental_exam
        + "', " + dbObj.anc_dental_gum+ "='" + p.anc_dental_gum
        + "', " + dbObj.anc_dental_tartar+ "='" + p.anc_dental_tartar
        + "', " + dbObj.anc_dental_caries+ "='" + p.anc_dental_caries
        + "', " + dbObj.anc_weight+ "='" + p.anc_weight
        + "', " + dbObj.anc_high+ "='" + p.anc_high
        + "', " + dbObj.anc_bmi+ "='" + p.anc_bmi
        + "', " + dbObj.anc_notice+ "='" + p.anc_notice
        + "', " + dbObj.visit_id+ "='" + p.visit_id
        + "', " + dbObj.patient_id+ "='" + p.patient_id
        + "', " + dbObj.anc_hn+ "='" + p.anc_hn
        + "', " + dbObj.staff_record+ "='" + p.staff_record
        + "', " + dbObj.staff_modify+ "='" + p.staff_modify
        + "', " + dbObj.staff_cancel+ "='" + p.staff_cancel
        + "', " + dbObj.record_date_time+ "='" + p.record_date_time
        + "', " + dbObj.modify_date_time+ "='" + p.modify_date_time
        + "', " + dbObj.cancel_date_time+ "='" + p.cancel_date_time        
        + "', " + dbObj.active+ "='" + p.active
        + "', " + dbObj.delivery_status+ "='" + p.delivery_status
        + "', " + dbObj.pressure+ "='" + p.pressure
        + "', " + dbObj.no+ "='" + p.no
        + "', " + dbObj.anc_gravida_day+ "='" + p.anc_gravida_day
        + "', " + dbObj.family_id+ "='" + p.family_id
        + "', " + dbObj.anc_baby_thalassemia+ "='" + p.anc_baby_thalassemia
        + "', " + dbObj.survey_date+ "='" + p.survey_date
        + "', " + dbObj.anc_hct_result+ "='" + p.anc_hct_result
        + "', " + dbObj.anc_tt_type+ "='" + p.anc_tt_type
//        + "', " + dbObj.anc_visit_office+ "='" + p.anc_visit_office        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectByPregnantID(String pregnancy_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.pregnancy_id
        + " = '" + pregnancy_id + "' and " + dbObj.active + " ='1' " +
                " Order by " + dbObj.record_date_time + ","+ dbObj.no;
        return eQuery(sql);
     }
    
    public int delete(AncPcu o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
   
    public Vector eQuery(String sql) throws Exception
    {
        AncPcu p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new AncPcu();
            p.setObjectId(rs.getString(dbObj.pk_field));            
            p.anc_vn= rs.getString(dbObj.anc_vn);
            p.pregnancy_id= rs.getString(dbObj.pregnancy_id);
            p.anc_section= rs.getString(dbObj.anc_section);
            p.anc_gravida_week= rs.getString(dbObj.anc_gravida_week);
            p.anc_exam= rs.getString(dbObj.anc_exam);
            p.anc_exam_description= rs.getString(dbObj.anc_exam_description);
            p.birth_high_risk_id= rs.getString(dbObj.birth_high_risk_id);
            p.anc_vdrl= rs.getString(dbObj.anc_vdrl);
            p.anc_thalassemia= rs.getString(dbObj.anc_thalassemia);
            p.anc_hb= rs.getString(dbObj.anc_hb);
            p.anc_hiv= rs.getString(dbObj.anc_hiv);
            p.anc_hct= rs.getString(dbObj.anc_hct);
            p.anc_hct_date= rs.getString(dbObj.anc_hct_date);
            p.anc_ancres= rs.getString(dbObj.anc_ancres);
            p.anc_tt= rs.getString(dbObj.anc_tt);
            p.anc_dental_exam= rs.getString(dbObj.anc_dental_exam);
            p.anc_dental_gum= rs.getString(dbObj.anc_dental_gum);
            p.anc_dental_tartar= rs.getString(dbObj.anc_dental_tartar);
            p.anc_dental_caries= rs.getString(dbObj.anc_dental_caries);
            p.anc_weight= rs.getString(dbObj.anc_weight);
            p.anc_high= rs.getString(dbObj.anc_high);
            p.anc_bmi= rs.getString(dbObj.anc_bmi);
            p.anc_notice= rs.getString(dbObj.anc_notice);
            p.visit_id= rs.getString(dbObj.visit_id);
            p.patient_id= rs.getString(dbObj.patient_id);
            p.anc_hn= rs.getString(dbObj.anc_hn);
            p.staff_record= rs.getString(dbObj.staff_record);
            p.staff_modify= rs.getString(dbObj.staff_modify);
            p.staff_cancel= rs.getString(dbObj.staff_cancel);
            p.record_date_time= rs.getString(dbObj.record_date_time);
            p.modify_date_time= rs.getString(dbObj.modify_date_time);
            p.cancel_date_time= rs.getString(dbObj.cancel_date_time);            
            p.active= rs.getString(dbObj.active);
            p.delivery_status= rs.getString(dbObj.delivery_status);
            p.pressure= rs.getString(dbObj.pressure);
            p.no= rs.getString(dbObj.no);
            p.anc_gravida_day= rs.getString(dbObj.anc_gravida_day);
            p.family_id= rs.getString(dbObj.family_id);
            p.anc_baby_thalassemia = rs.getString(dbObj.anc_baby_thalassemia);
            p.survey_date = rs.getString(dbObj.survey_date);
            p.anc_hct_result = rs.getString(dbObj.anc_hct_result);
            p.anc_tt_type = rs.getString(dbObj.anc_tt_type);
//            p.anc_visit_office = rs.getString(dbObj.anc_visit_office);            
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
                +" where " + dbObj.family_id + "='" + family_from +"'";
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
