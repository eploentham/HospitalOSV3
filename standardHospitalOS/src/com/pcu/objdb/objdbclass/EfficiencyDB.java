/*
 * EfficiencyDB.java
 *
 * Created on 20 มิถุนายน 2548, 18:25 น.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.pcu.object.Efficiency;

import com.hospital_os.utility.*;
/**
 *
 * @author Noom
 */
public class EfficiencyDB {
    public ConnectionInf theConnectionInf;
    public Efficiency dbObj;
    final private String idtable = "727";
    
    
    /** Creates a new instance of EfficiencyDB */
    public EfficiencyDB() {
    }
    
    
    public EfficiencyDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new Efficiency();
        initConfig();
    }
    
    public boolean initConfig() {
        dbObj.table = "t_health_efficiency";
        dbObj.pk_field = "t_health_efficiency_id";
        dbObj.efficiency_detail = "efficiency_detail";
        dbObj.efficiency_remark ="efficiency_remark";
        dbObj.efficiency_record_time = "efficiency_record_time";
        dbObj.efficiency_modify_time = "efficiency_modify_time";
        dbObj.efficiency_cancle_time = "efficiency_cancle_time";
        dbObj.efficiency_staff_record = "efficiency_staff_record";
        dbObj.efficiency_staff_modify = "efficiency_staff_modify";
        dbObj.efficiency_staff_cancle = "efficiency_staff_cancle";
        dbObj.efficiency_active = "efficiency_active";
        dbObj.visit_id ="t_visit_id";
        dbObj.patient_id ="t_patient_id";
        dbObj.family_id = "t_health_family_id";
        dbObj.survey_date = "efficiency_survey_date" ;
        return true;
    }
    
    public int insert(Efficiency o) throws Exception {
        String sql="";
        Efficiency p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"	+ dbObj.efficiency_detail
                + " ,"	+ dbObj.efficiency_remark
                + " ,"	+ dbObj.efficiency_record_time
                + " ,"	+ dbObj.efficiency_modify_time
                + " ,"	+ dbObj.efficiency_cancle_time
                + " ,"	+ dbObj.efficiency_staff_record
                + " ,"	+ dbObj.efficiency_staff_modify
                + " ,"	+ dbObj.efficiency_staff_cancle
                + " ,"	+ dbObj.efficiency_active
                + " ,"	+ dbObj.visit_id
                + " ,"	+ dbObj.patient_id
                + " ,"	+ dbObj.family_id
                + " ,"	+ dbObj.survey_date
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.efficiency_detail
                + "','" + p.efficiency_remark
                + "','" + p.efficiency_record_time
                + "','" + p.efficiency_modify_time
                + "','" + p.efficiency_cancle_time
                + "','" + p.efficiency_staff_record
                + "','" + p.efficiency_staff_modify
                + "','" + p.efficiency_staff_cancle
                + "','" + p.efficiency_active
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
                + " and "+dbObj.efficiency_active+" = '1'"
                + " order by "+dbObj.efficiency_record_time;
        return eQuery(sql);
    }
    
     public Vector selectByPatientID(String patient_id) throws Exception {
             String sql="select * from " + dbObj.table
                + " where " + dbObj.patient_id
                + " = '" + patient_id + "'"
                + " and "+dbObj.efficiency_active+" = '1'"
                + " order by "+dbObj.efficiency_record_time;
        return eQuery(sql);
    }
     
    public Vector selectByFamilyID(String family_id) throws Exception {
             String sql="select * from " + dbObj.table
                + " where " + dbObj.family_id
                + " = '" + family_id + "'"
                + " and "+dbObj.efficiency_active+" = '1'"
                + " order by "+dbObj.efficiency_record_time;
       return eQuery(sql);
    } 
    
    public Vector eQuery(String sql) throws Exception {
        Efficiency p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) {
            p = new Efficiency();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.efficiency_detail = rs.getString(dbObj.efficiency_detail);
            p.efficiency_remark = rs.getString(dbObj.efficiency_remark);
            p.efficiency_record_time = rs.getString(dbObj.efficiency_record_time);
            p.efficiency_modify_time = rs.getString(dbObj.efficiency_modify_time);
            p.efficiency_cancle_time = rs.getString(dbObj.efficiency_cancle_time);
            p.efficiency_staff_record = rs.getString(dbObj.efficiency_staff_record);
            p.efficiency_staff_modify = rs.getString(dbObj.efficiency_staff_modify);
            p.efficiency_staff_cancle = rs.getString(dbObj.efficiency_staff_cancle);
            p.efficiency_active = rs.getString(dbObj.efficiency_active);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.patient_id = rs.getString(dbObj.patient_id);
            p.family_id = rs.getString(dbObj.family_id);
            p.survey_date = rs.getString(dbObj.survey_date);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public int update(Efficiency o) throws Exception {
        String sql="update " + dbObj.table + " set ";
        Efficiency p=o;
        sql += dbObj.efficiency_detail + "='" + p.efficiency_detail
                + "', " + dbObj.efficiency_remark + "='" + p.efficiency_remark
                + "', " + dbObj.efficiency_record_time + "='" + p.efficiency_record_time
                + "', " + dbObj.efficiency_modify_time + "='" + p.efficiency_modify_time
                + "', " + dbObj.efficiency_cancle_time + "='" + p.efficiency_cancle_time
                + "', " + dbObj.efficiency_staff_record + "='" + p.efficiency_staff_record
                + "', " + dbObj.efficiency_staff_modify + "='" + p.efficiency_staff_modify
                + "', " + dbObj.efficiency_staff_cancle + "='" + p.efficiency_staff_cancle
                + "', " + dbObj.efficiency_active + "='" + p.efficiency_active
                + "', " + dbObj.visit_id + "='" + p.visit_id
                + "', " + dbObj.patient_id + "='" + p.patient_id
                + "', " + dbObj.family_id + "='" + p.family_id
                + "', " + dbObj.survey_date + "='" + p.survey_date
                + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(Efficiency o) throws Exception {
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
//        EfficiencyDB v = new EfficiencyDB(theConnectionInf);
//        Efficiency vh = new Efficiency();
//        vh.pk_field = "55566dddd";
//        v.insert(vh);
//        /*
//        Vector vec = v.selectByVisitID("666");
//        if(vec != null){
//            
//            for(int i=0;i<vec.size();i++){
//                vh = (Efficiency)vec.elementAt(i);
//                
//                
//            }
//        }*/
//        
//    }
    
/////////////////////////////////////////////////////////////////////////////    
    public int updateFidByFid(String family_id,String family_from) throws Exception
    {
        String sql="update " + dbObj.table + " set " 
                + dbObj.family_id + "='" + family_id + "',"
                + dbObj.efficiency_staff_cancle + "="+ dbObj.efficiency_staff_cancle + "||'-'||'" + family_from + "'"
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
