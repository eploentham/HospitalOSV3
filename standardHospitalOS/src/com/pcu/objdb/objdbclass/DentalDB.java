/*
 * DentalDB.java
 *
 * Created on 20 มิถุนายน 2548, 18:25 น.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;

import com.hospital_os.utility.*;
/**
 *
 * @author Noom
 */
public class DentalDB {
    public ConnectionInf theConnectionInf;
    public Dental dbObj;
    final private String idtable = "730";
    
    
    /** Creates a new instance of DentalDB */
    public DentalDB() {
    }
    
    public DentalDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new Dental();
        initConfig();
    }
    
    public boolean initConfig() {
        dbObj.table = "t_health_dental";
        dbObj.pk_field = "t_health_dental_id";    
        dbObj.dental_num_tooth ="dental_num_tooth";
        dbObj.dental_num_bad_tooth = "dental_num_bad_tooth";
        dbObj.dental_num_milktooth ="dental_num_milktooth";
        dbObj.dental_num_bad_milktooth = "dental_num_bad_milktooth";
        dbObj.f_health_gum_level_id ="f_health_gum_level_id";
        dbObj.dental_detail ="dental_detail";
        dbObj.dental_remark = "dental_remark";
        dbObj.dental_false_teeth_need = "dental_false_teeth_need";
        dbObj.dental_record_time = "dental_record_time";
        dbObj.dental_modify_time = "dental_modify_time";
        dbObj.dental_cancle_time = "dental_cancle_time";
        dbObj.dental_staff_record = "dental_staff_record";
        dbObj.dental_staff_modify = "dental_staff_modify";
        dbObj.dental_staff_cancle = "dental_staff_cancle";
        dbObj.dental_active = "dental_active";
        dbObj.visit_id ="t_visit_id";
        dbObj.patient_id ="t_patient_id";
        dbObj.family_id = "t_health_family_id";
        dbObj.survey_date = "dental_survey_date";
        return true;
    }
    
    public int insert(Dental o) throws Exception {
        String sql="";
        Dental p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"	+ dbObj.dental_num_tooth
                + " ,"	+ dbObj.dental_num_bad_tooth
                + " ,"	+ dbObj.dental_num_milktooth
                + " ,"	+ dbObj.dental_num_bad_milktooth
                + " ,"	+ dbObj.f_health_gum_level_id
                + " ,"	+ dbObj.dental_detail
                + " ,"	+ dbObj.dental_remark
                + " ,"	+ dbObj.dental_false_teeth_need
                + " ,"	+ dbObj.dental_record_time
                + " ,"	+ dbObj.dental_modify_time
                + " ,"	+ dbObj.dental_cancle_time
                + " ,"	+ dbObj.dental_staff_record
                + " ,"	+ dbObj.dental_staff_modify
                + " ,"	+ dbObj.dental_staff_cancle
                + " ,"	+ dbObj.dental_active
                + " ,"	+ dbObj.visit_id
                + " ,"	+ dbObj.patient_id
                + " ,"	+ dbObj.family_id
                + " ,"	+ dbObj.survey_date
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.dental_num_tooth
                + "','" + p.dental_num_bad_tooth
                + "','" + p.dental_num_milktooth
                + "','" + p.dental_num_bad_milktooth
                + "','" + p.f_health_gum_level_id
                + "','" + p.dental_detail
                + "','" + p.dental_remark
                + "','" + p.dental_false_teeth_need
                + "','" + p.dental_record_time
                + "','" + p.dental_modify_time
                + "','" + p.dental_cancle_time
                + "','" + p.dental_staff_record
                + "','" + p.dental_staff_modify
                + "','" + p.dental_staff_cancle
                + "','" + p.dental_active
                + "','" + p.visit_id
                + "','" + p.patient_id
                + "','" + p.family_id
                + "','" + p.survey_date
                + "')";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectByVisitID(String visit_id) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.visit_id
                + " = '" + visit_id + "'"
                + " and "+dbObj.dental_active+" = '1'"
                + " order by "+dbObj.dental_record_time;
        return eQuery(sql);
    }
    
     public Vector selectByPatientID(String patient_id) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.patient_id
                + " = '" + patient_id + "'"
                + " and "+dbObj.dental_active+" = '1'"
                + " order by "+dbObj.dental_record_time;
        return eQuery(sql);
    }
     
     public Vector selectByFamilyID(String family_id) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.family_id
                + " = '" + family_id + "'"
                + " and "+dbObj.dental_active+" = '1'"
                + " order by "+dbObj.dental_record_time;
        return eQuery(sql);
    } 
    
    public Vector eQuery(String sql) throws Exception {
        Dental p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) {
            p = new Dental();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.dental_num_tooth = rs.getString(dbObj.dental_num_tooth);
            p.dental_num_bad_tooth = rs.getString(dbObj.dental_num_bad_tooth);
            p.dental_num_milktooth = rs.getString(dbObj.dental_num_milktooth);
            p.dental_num_bad_milktooth = rs.getString(dbObj.dental_num_bad_milktooth);
            p.f_health_gum_level_id = rs.getString(dbObj.f_health_gum_level_id);
            p.dental_detail = rs.getString(dbObj.dental_detail);
            p.dental_remark = rs.getString(dbObj.dental_remark);
             p.dental_false_teeth_need = rs.getString(dbObj.dental_false_teeth_need);
            p.dental_record_time = rs.getString(dbObj.dental_record_time);
            p.dental_modify_time = rs.getString(dbObj.dental_modify_time);
            p.dental_cancle_time = rs.getString(dbObj.dental_cancle_time);
            p.dental_staff_record = rs.getString(dbObj.dental_staff_record);
            p.dental_staff_modify = rs.getString(dbObj.dental_staff_modify);
            p.dental_staff_cancle = rs.getString(dbObj.dental_staff_cancle);
            p.dental_active = rs.getString(dbObj.dental_active);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.patient_id = rs.getString(dbObj.patient_id);
            p.family_id = rs.getString(dbObj.family_id);
            p.survey_date = rs.getString(dbObj.survey_date);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public int update(Dental o) throws Exception {
        String sql="update " + dbObj.table + " set ";
        Dental p=o;
        sql += dbObj.dental_num_tooth + "='" + p.dental_num_tooth
                + "', " + dbObj.dental_num_bad_tooth + "='" + p.dental_num_bad_tooth
                + "', " + dbObj.dental_num_milktooth + "='" + p.dental_num_milktooth
                + "', " + dbObj.dental_num_bad_milktooth + "='" + p.dental_num_bad_milktooth
                + "', " + dbObj.f_health_gum_level_id + "='" + p.f_health_gum_level_id
                + "', " + dbObj.dental_detail + "='" + p.dental_detail
                + "', " + dbObj.dental_remark + "='" + p.dental_remark
                + "', " + dbObj.dental_false_teeth_need + "='" + p.dental_false_teeth_need
                + "', " + dbObj.dental_record_time + "='" + p.dental_record_time
                + "', " + dbObj.dental_modify_time + "='" + p.dental_modify_time
                + "', " + dbObj.dental_cancle_time + "='" + p.dental_cancle_time
                + "', " + dbObj.dental_staff_record + "='" + p.dental_staff_record
                + "', " + dbObj.dental_staff_modify + "='" + p.dental_staff_modify
                + "', " + dbObj.dental_staff_cancle + "='" + p.dental_staff_cancle
                + "', " + dbObj.dental_active + "='" + p.dental_active
                + "', " + dbObj.visit_id + "='" + p.visit_id
                + "', " + dbObj.patient_id + "='" + p.patient_id
                + "', " + dbObj.family_id + "='" + p.family_id
                + "', " + dbObj.survey_date + "='" + p.survey_date
                + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(Dental o) throws Exception {
        String sql="delete from " + dbObj.table
                + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
//    //test from noom (เดี่ยวลบออก)
//    public static void main(String args[]) throws Exception{
//        String url = "jdbc:postgresql://192.168.1.6:5432/develop_support";
//        String user = "postgres";
//        String pass = "postgres";
//        String dri = "org.postgresql.Driver";
//        String type = "0"; //0 postgres 1 mysql 2 sqlserver
//        ConnectionInf theConnectionInf = new ConnectionDBMgr(dri,url,user,pass,type);
//        
//        DentalDB v = new DentalDB(theConnectionInf);
//        Dental vh = new Dental();
//        vh.pk_field = "555dd";
//        v.insert(vh);
//        
//        Vector vec = v.selectByVisitID("666");
//        if(vec != null){
//            
//            for(int i=0;i<vec.size();i++){
//                vh = (Dental)vec.elementAt(i);
//                
//                
//            }
//        }
//        
//    }
    
/////////////////////////////////////////////////////////////////////////////    
    public int updateFidByFid(String family_id,String family_from) throws Exception
    {
        String sql="update " + dbObj.table + " set " 
                + dbObj.family_id + "='" + family_id + "',"
                + dbObj.dental_staff_cancle + "="+ dbObj.dental_staff_cancle + "||'-'||'" + family_from + "'"
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
