/*
 * CheckHealthYearDB.java
 *
 * Created on 20 มิถุนายน 2548, 18:25 น.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.pcu.object.CheckHealthYear;

import com.hospital_os.utility.*;
/**
 *
 * @author Noom
 */
public class CheckHealthYearDB {
    public ConnectionInf theConnectionInf;
    public CheckHealthYear dbObj;
    final private String idtable = "729";
    
    
    /** Creates a new instance of CheckHealthYearDB */
    public CheckHealthYearDB() {
    }
    
    public CheckHealthYearDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new CheckHealthYear();
        initConfig();
    }
    
    public boolean initConfig() {
        dbObj.table = "t_health_check_health_year";
        dbObj.pk_field = "t_health_check_health_year_id";
        dbObj.b_health_check_health_year_activity_id ="b_health_check_health_year_activity_id";
        dbObj.check_health_year_result = "check_health_year_result";
        dbObj.check_health_year_remark ="check_health_year_remark";
        dbObj.check_health_year_record_time = "check_health_year_record_time";
        dbObj.check_health_year_modify_time = "check_health_year_modify_time";
        dbObj.check_health_year_cancle_time = "check_health_year_cancle_time";
        dbObj.check_health_year_staff_record = "check_health_year_staff_record";
        dbObj.check_health_year_staff_modify = "check_health_year_staff_modify";
        dbObj.check_health_year_staff_cancle = "check_health_year_staff_cancle";
        dbObj.check_health_year_active = "check_health_year_active";
        dbObj.visit_id ="t_visit_id";
        dbObj.patient_id ="t_patient_id";
        dbObj.family_id = "t_health_family_id";
        dbObj.survey_date = "health_survey_date";
        return true;
    }
    
    public int insert(CheckHealthYear o) throws Exception {
        String sql="";
        CheckHealthYear p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"	+ dbObj.b_health_check_health_year_activity_id
                + " ,"	+ dbObj.check_health_year_result
                + " ,"	+ dbObj.check_health_year_remark
                + " ,"	+ dbObj.check_health_year_record_time
                + " ,"	+ dbObj.check_health_year_modify_time
                + " ,"	+ dbObj.check_health_year_cancle_time
                + " ,"	+ dbObj.check_health_year_staff_record
                + " ,"	+ dbObj.check_health_year_staff_modify
                + " ,"	+ dbObj.check_health_year_staff_cancle
                + " ,"	+ dbObj.check_health_year_active
                + " ,"	+ dbObj.visit_id
                + " ,"	+ dbObj.patient_id
                + " ,"	+ dbObj.family_id
                + " ,"	+ dbObj.survey_date
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.b_health_check_health_year_activity_id
                + "','" + p.check_health_year_result
                + "','" + p.check_health_year_remark
                + "','" + p.check_health_year_record_time
                + "','" + p.check_health_year_modify_time
                + "','" + p.check_health_year_cancle_time
                + "','" + p.check_health_year_staff_record
                + "','" + p.check_health_year_staff_modify
                + "','" + p.check_health_year_staff_cancle
                + "','" + p.check_health_year_active
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
                + " and "+dbObj.check_health_year_active+" = '1'"
                + " order by "+dbObj.check_health_year_record_time;
        return eQuery(sql);
    }
    public Vector selectByVisitType(String visit_id,String type) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.visit_id + " = '" + visit_id + "'"
                + " and "+dbObj.b_health_check_health_year_activity_id + " = '"+type+"'"
                + " and "+dbObj.check_health_year_active+" = '1'"
                + " order by "+dbObj.check_health_year_record_time;
        return eQuery(sql);
    }
    
     public Vector selectByPatientID(String patient_id) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.patient_id
                + " = '" + patient_id + "'"
                + " and "+dbObj.check_health_year_active+" = '1'"
                + " order by "+dbObj.check_health_year_record_time;
        return eQuery(sql);
    }
     
    public Vector selectByFamilyID(String family_id) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.family_id
                + " = '" + family_id + "'"
                + " and "+dbObj.check_health_year_active+" = '1'"
                + " order by "+dbObj.check_health_year_record_time;
        return eQuery(sql);
    } 
    /**
     * ค้นหาการตรวจสุขภาพประจำปี
     * @param type กิจกรรมที่ตรวจ surveydate วันที่ออกตรวจ
     * @return Vector of CheckHealthYear
     * @author kingland
     * @date 24/08/2549
     */
    public Vector selectByTypeAndServeyDate(String familyid, String type, String surveydate) throws Exception
    {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.b_health_check_health_year_activity_id+" = '" + type +"'"
                + " and "+dbObj.survey_date+" = '"+surveydate+"'" 
                + " and "+dbObj.family_id +" = '"+familyid+"'";
        return this.eQuery(sql);
    }
    public Vector eQuery(String sql) throws Exception {
        CheckHealthYear p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) {
            p = new CheckHealthYear();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.b_health_check_health_year_activity_id = rs.getString(dbObj.b_health_check_health_year_activity_id);
            p.check_health_year_result = rs.getString(dbObj.check_health_year_result);
            p.check_health_year_remark = rs.getString(dbObj.check_health_year_remark);
            p.check_health_year_record_time = rs.getString(dbObj.check_health_year_record_time);
            p.check_health_year_modify_time = rs.getString(dbObj.check_health_year_modify_time);
            p.check_health_year_cancle_time = rs.getString(dbObj.check_health_year_cancle_time);
            p.check_health_year_staff_record = rs.getString(dbObj.check_health_year_staff_record);
            p.check_health_year_staff_modify = rs.getString(dbObj.check_health_year_staff_modify);
            p.check_health_year_staff_cancle = rs.getString(dbObj.check_health_year_staff_cancle);
            p.check_health_year_active = rs.getString(dbObj.check_health_year_active);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.patient_id = rs.getString(dbObj.patient_id);
            p.family_id = rs.getString(dbObj.family_id);
            p.survey_date = rs.getString(dbObj.survey_date);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public int update(CheckHealthYear o) throws Exception {
        String sql="update " + dbObj.table + " set ";
        CheckHealthYear p=o;
        sql += dbObj.b_health_check_health_year_activity_id + "='" + p.b_health_check_health_year_activity_id
                + "', " + dbObj.check_health_year_result + "='" + p.check_health_year_result
                + "', " + dbObj.check_health_year_remark + "='" + p.check_health_year_remark
                + "', " + dbObj.check_health_year_record_time + "='" + p.check_health_year_record_time
                + "', " + dbObj.check_health_year_modify_time + "='" + p.check_health_year_modify_time
                + "', " + dbObj.check_health_year_cancle_time + "='" + p.check_health_year_cancle_time
                + "', " + dbObj.check_health_year_staff_record + "='" + p.check_health_year_staff_record
                + "', " + dbObj.check_health_year_staff_modify + "='" + p.check_health_year_staff_modify
                + "', " + dbObj.check_health_year_staff_cancle + "='" + p.check_health_year_staff_cancle
                + "', " + dbObj.check_health_year_active + "='" + p.check_health_year_active
                + "', " + dbObj.visit_id + "='" + p.visit_id
                + "', " + dbObj.patient_id + "='" + p.patient_id
                + "', " + dbObj.family_id + "='" + p.family_id
                + "', " + dbObj.survey_date + "='" + p.survey_date
                + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(CheckHealthYear o) throws Exception {
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
//        CheckHealthYearDB v = new CheckHealthYearDB(theConnectionInf);
//        CheckHealthYear vh = new CheckHealthYear();
//        //vh.pk_field = "555dd";
//        //v.insert(vh);
//        
//        Vector vec = v.selectByVisitID("666");
//        if(vec != null){
//            
//            for(int i=0;i<vec.size();i++){
//                vh = (CheckHealthYear)vec.elementAt(i);
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
                + dbObj.check_health_year_staff_cancle + "="+ dbObj.check_health_year_staff_cancle + "||'-'||'" + family_from + "'"
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

    public CheckHealthYear selectByPK(String id) throws Exception
    {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.pk_field +" = '" + id +"'";
        Vector v = this.eQuery(sql);
        if(v.isEmpty())
            return null;
        else 
            return (CheckHealthYear)v.get(0);
    }
    
}
