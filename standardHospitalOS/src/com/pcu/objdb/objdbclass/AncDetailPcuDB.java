/*
 * AncDetailPcuDB.java
 *
 * Created on 7 กรกฎาคม 2548, 11:18 น.
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
public class AncDetailPcuDB {
    
    /** Creates a new instance of AncDetailPcuDB */
    public AncDetailPcuDB() {
    }
    
     public ConnectionInf theConnectionInf;
    
    public AncDetailPcu dbObj;
    final private String idtable = "775";
    
    public AncDetailPcuDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new AncDetailPcu();
        initConfig(); 
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_anc_detail";        
        dbObj.pk_field = "t_health_anc_detail_id";        
        dbObj.patient_id="t_patient_id";
        dbObj.visit_id="t_visit_id";
        dbObj.pregnancy_id="t_health_pregnancy_id";
        dbObj.anc_detail_sugar="health_anc_detail_sugar";
        dbObj.anc_detail_cramp="health_anc_detail_cramp";
        dbObj.anc_detail_headache="health_anc_detail_headache";
        dbObj.anc_detail_fundus_height="health_anc_detail_fundus_height";
        dbObj.anc_detail_fetal_movement="health_anc_detail_fetal_movement";
        dbObj.anc_detail_risk="health_anc_detail_risk";
        dbObj.anc_detail_vaginal_breeding="health_anc_detail_vaginal_breeding";
        dbObj.anc_detail_thyroid="health_anc_detail_thyroid";
        dbObj.anc_detail_heart_disease="health_anc_detail_heart_disease";
        dbObj.anc_detail_edema="health_anc_detail_edema";
        dbObj.anc_detail_fetal_heart_sound="health_anc_detail_fetal_heart_sound";
        dbObj.anc_detail_urine_alblumin="health_anc_detail_urine_alblumin";
        dbObj.anc_detail_albumin="health_anc_detail_albumin";
        dbObj.baby_status_id="f_health_pregnancy_posture_baby_status_id";
        dbObj.anc_detail_nausea="health_anc_detail_nausea";
        dbObj.conduct_id="f_health_pregnancy_conduct_id";
        dbObj.anc_detail_vaginal_discharge="health_anc_detail_vaginal_discharge";        
        dbObj.staff_record="health_anc_detail_staff_record";
        dbObj.staff_modify="health_anc_detail_staff_modify";
        dbObj.staff_cancel="health_anc_detail_staff_cancel";
        dbObj.record_date_time="record_date_time";
        dbObj.modify_date_time="modify_date_time";
        dbObj.cancel_date_time="cancel_date_time";
        dbObj.anc_id="t_health_anc_id";
        dbObj.active="health_anc_detail_active";
        dbObj.heart_rate="health_anc_detail_heart_rate";
        dbObj.anc_detail_uteruscm = "health_anc_detail_uteruscm";
        dbObj.family_id = "t_health_family_id";
        return true;
    }
    
    public int insert(AncDetailPcu o) throws Exception
    {
        String sql="";
        AncDetailPcu p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field        
        + " ,"  + dbObj.patient_id
        + " ,"  + dbObj.visit_id
        + " ,"  + dbObj.pregnancy_id
        + " ,"  + dbObj.anc_detail_sugar
        + " ,"  + dbObj.anc_detail_cramp
        + " ,"  + dbObj.anc_detail_headache
        + " ,"  + dbObj.anc_detail_fundus_height
        + " ,"  + dbObj.anc_detail_fetal_movement
        + " ,"  + dbObj.anc_detail_risk
        + " ,"  + dbObj.anc_detail_vaginal_breeding
        + " ,"  + dbObj.anc_detail_thyroid
        + " ,"  + dbObj.anc_detail_heart_disease
        + " ,"  + dbObj.anc_detail_edema
        + " ,"  + dbObj.anc_detail_fetal_heart_sound
        + " ,"  + dbObj.anc_detail_urine_alblumin
        + " ,"  + dbObj.anc_detail_albumin
        + " ,"  + dbObj.baby_status_id
        + " ,"  + dbObj.anc_detail_nausea
        + " ,"  + dbObj.conduct_id
        + " ,"  + dbObj.anc_detail_vaginal_discharge        
        + " ,"  + dbObj.staff_record
        + " ,"  + dbObj.staff_modify
        + " ,"  + dbObj.staff_cancel
        + " ,"  + dbObj.record_date_time
        + " ,"  + dbObj.modify_date_time
        + " ,"  + dbObj.cancel_date_time
        + " ,"  + dbObj.anc_id
        + " ,"  + dbObj.active        
        + " ,"  + dbObj.heart_rate
        + " ,"  + dbObj.anc_detail_uteruscm
        + " ,"  + dbObj.family_id
        + " ) values ('"
        + p.getObjectId()        
        + "','" + p.patient_id
        + "','" + p.visit_id
        + "','" + p.pregnancy_id
        + "','" + p.anc_detail_sugar
        + "','" + p.anc_detail_cramp
        + "','" + p.anc_detail_headache
        + "','" + p.anc_detail_fundus_height
        + "','" + p.anc_detail_fetal_movement
        + "','" + p.anc_detail_risk
        + "','" + p.anc_detail_vaginal_breeding
        + "','" + p.anc_detail_thyroid
        + "','" + p.anc_detail_heart_disease
        + "','" + p.anc_detail_edema
        + "','" + p.anc_detail_fetal_heart_sound
        + "','" + p.anc_detail_urine_alblumin
        + "','" + p.anc_detail_albumin
        + "','" + p.baby_status_id
        + "','" + p.anc_detail_nausea
        + "','" + p.conduct_id
        + "','" + p.anc_detail_vaginal_discharge        
        + "','" + p.staff_record
        + "','" + p.staff_modify
        + "','" + p.staff_cancel
        + "','" + p.record_date_time
        + "','" + p.modify_date_time
        + "','" + p.cancel_date_time
        + "','" + p.anc_id    
        + "','" + p.active       
        + "','" + p.heart_rate     
        + "','" + p.anc_detail_uteruscm
        + "','" + p.family_id
        + "')";        
        return theConnectionInf.eUpdate(sql);
    }
    public int update(AncDetailPcu o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        AncDetailPcu p=o;
        String field =""        
        + "', " + dbObj.patient_id + "='" + p.patient_id
        + "', " + dbObj.visit_id + "='" + p.visit_id
        + "', " + dbObj.pregnancy_id + "='" + p.pregnancy_id
        + "', " + dbObj.anc_detail_sugar + "='" + p.anc_detail_sugar
        + "', " + dbObj.anc_detail_cramp + "='" + p.anc_detail_cramp
        + "', " + dbObj.anc_detail_headache + "='" + p.anc_detail_headache
        + "', " + dbObj.anc_detail_fundus_height + "='" + p.anc_detail_fundus_height
        + "', " + dbObj.anc_detail_fetal_movement + "='" + p.anc_detail_fetal_movement
        + "', " + dbObj.anc_detail_risk	+ "='" + p.anc_detail_risk
        + "', " + dbObj.anc_detail_vaginal_breeding + "='" + p.anc_detail_vaginal_breeding
        + "', " + dbObj.anc_detail_thyroid + "='" + p.anc_detail_thyroid
        + "', " + dbObj.anc_detail_heart_disease + "='" + p.anc_detail_heart_disease
        + "', " + dbObj.anc_detail_edema + "='" + p.anc_detail_edema
        + "', " + dbObj.anc_detail_fetal_heart_sound + "='" + p.anc_detail_fetal_heart_sound
        + "', " + dbObj.anc_detail_urine_alblumin + "='" + p.anc_detail_urine_alblumin
        + "', " + dbObj.anc_detail_albumin + "='" + p.anc_detail_albumin
        + "', " + dbObj.baby_status_id + "='" + p.baby_status_id
        + "', " + dbObj.anc_detail_nausea + "='" + p.anc_detail_nausea
        + "', " + dbObj.conduct_id + "='" + p.conduct_id
        + "', " + dbObj.anc_detail_vaginal_discharge + "='" + p.anc_detail_vaginal_discharge        
        + "', " + dbObj.staff_record + "='" + p.staff_record
        + "', " + dbObj.staff_modify + "='" + p.staff_modify
        + "', " + dbObj.staff_cancel + "='" + p.staff_cancel
        + "', " + dbObj.record_date_time + "='" + p.record_date_time
        + "', " + dbObj.modify_date_time + "='" + p.modify_date_time
        + "', " + dbObj.cancel_date_time + "='" + p.cancel_date_time
        + "', " + dbObj.anc_id+ "='" + p.anc_id
        + "', " + dbObj.active + "='" + p.active
        + "', " + dbObj.heart_rate + "='" + p.heart_rate
        + "', " + dbObj.anc_detail_uteruscm + "='" + p.anc_detail_uteruscm        
        + "', " + dbObj.family_id + "='" + p.family_id
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectByPregnantID(String anc_id) throws Exception
    {
        return selectByAncId(anc_id);
    }
    public Vector selectByAncId(String anc_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.anc_id
        + " = '" + anc_id + "' and " + dbObj.active + " ='1' Order by " + dbObj.record_date_time ;
        return eQuery(sql);
     }
    
    public int delete(AncDetailPcu o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
   
    public Vector eQuery(String sql) throws Exception
    {
        AncDetailPcu p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new AncDetailPcu();
            p.setObjectId(rs.getString(dbObj.pk_field));            
            p.patient_id= rs.getString(dbObj.patient_id);
            p.visit_id= rs.getString(dbObj.visit_id);
            p.pregnancy_id= rs.getString(dbObj.pregnancy_id);
            p.anc_detail_sugar= rs.getString(dbObj.anc_detail_sugar);
            p.anc_detail_cramp= rs.getString(dbObj.anc_detail_cramp);
            p.anc_detail_headache= rs.getString(dbObj.anc_detail_headache);
            p.anc_detail_fundus_height= rs.getString(dbObj.anc_detail_fundus_height);
            p.anc_detail_fetal_movement= rs.getString(dbObj.anc_detail_fetal_movement);
            p.anc_detail_risk= rs.getString(dbObj.anc_detail_risk);
            p.anc_detail_vaginal_breeding= rs.getString(dbObj.anc_detail_vaginal_breeding);
            p.anc_detail_thyroid= rs.getString(dbObj.anc_detail_thyroid);
            p.anc_detail_heart_disease= rs.getString(dbObj.anc_detail_heart_disease);
            p.anc_detail_edema= rs.getString(dbObj.anc_detail_edema);
            p.anc_detail_fetal_heart_sound= rs.getString(dbObj.anc_detail_fetal_heart_sound);
            p.anc_detail_urine_alblumin= rs.getString(dbObj.anc_detail_urine_alblumin);
            p.anc_detail_albumin= rs.getString(dbObj.anc_detail_albumin);
            p.baby_status_id= rs.getString(dbObj.baby_status_id);
            p.anc_detail_nausea= rs.getString(dbObj.anc_detail_nausea);
            p.conduct_id= rs.getString(dbObj.conduct_id);
            p.anc_detail_vaginal_discharge= rs.getString(dbObj.anc_detail_vaginal_discharge);            
            p.staff_record= rs.getString(dbObj.staff_record);
            p.staff_modify= rs.getString(dbObj.staff_modify);
            p.staff_cancel= rs.getString(dbObj.staff_cancel);
            p.record_date_time= rs.getString(dbObj.record_date_time);
            p.modify_date_time= rs.getString(dbObj.modify_date_time);
            p.cancel_date_time= rs.getString(dbObj.cancel_date_time);
            p.anc_id= rs.getString(dbObj.anc_id);
            p.active= rs.getString(dbObj.active);
            p.heart_rate= rs.getString(dbObj.heart_rate);
            p.anc_detail_uteruscm= rs.getString(dbObj.anc_detail_uteruscm);
            p.family_id= rs.getString(dbObj.family_id);
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

