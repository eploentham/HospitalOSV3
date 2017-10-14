/*
 * VisitHomeDB.java
 *
 * Created on 17 มิถุนายน 2548, 14:52 น.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.pcu.object.VisitHome;

/**
 *
 * @author Noom
 */
public class VisitHomeDB {
    
    public ConnectionInf theConnectionInf;
    public VisitHome dbObj;
    final private String idtable = "723";
    
    /** Creates a new instance of VisitHomeDB */
    public VisitHomeDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new VisitHome();
        initConfig();
    }
    
    public boolean initConfig() {
        dbObj.table = "t_health_visit_home";
        dbObj.pk_field = "t_health_visit_home_id";
        dbObj.visit_home_problem ="visit_home_problem";
        dbObj.visit_home_object = "visit_home_object";
        dbObj.visit_home_maintain ="visit_home_maintain";
        dbObj.visit_home_assess = "visit_home_assess";
        dbObj.visit_home_plane = "visit_home_plane";
        dbObj.visit_home_date = "visit_home_date";
        dbObj.visit_home_nextdate = "visit_home_nextdate";
        dbObj.visit_home_remark = "visit_home_remark";
        dbObj.visit_home_record_time = "visit_home_record_time";
        dbObj.visit_home_modify_time = "visit_home_modify_time";
        dbObj.visit_home_cancle_time = "visit_home_cancle_time";
        dbObj.visit_home_staff_record = "visit_home_staff_record";
        dbObj.visit_home_staff_modify = "visit_home_staff_modify";
        dbObj.visit_home_staff_cancle = "visit_home_staff_cancle";
        dbObj.visit_home_active = "visit_home_active";
        dbObj.patient_id = "t_patient_id";
        dbObj.family_id = "t_health_family_id";
        return true;
    }
    
    public int insert(VisitHome o) throws Exception {
        String sql="";
        VisitHome p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"  + dbObj.visit_home_problem
                + " ,"  + dbObj.visit_home_object
                + " ,"	+ dbObj.visit_home_maintain
                + " ,"	+ dbObj.visit_home_assess
                + " ,"	+ dbObj.visit_home_plane
                + " ,"	+ dbObj.visit_home_date
                + " ,"	+ dbObj.visit_home_nextdate
                + " ,"	+ dbObj.visit_home_remark
                + " ,"	+ dbObj.visit_home_record_time
                + " ,"	+ dbObj.visit_home_modify_time
                + " ,"	+ dbObj.visit_home_cancle_time
                + " ,"	+ dbObj.visit_home_staff_record
                + " ,"	+ dbObj.visit_home_staff_modify
                + " ,"	+ dbObj.visit_home_staff_cancle
                + " ,"	+ dbObj.visit_home_active
                + " ,"	+ dbObj.patient_id
                + " ,"	+ dbObj.family_id
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.visit_home_problem
                + "','" + p.visit_home_object
                + "','" + p.visit_home_maintain
                + "','" + p.visit_home_assess
                + "','" + p.visit_home_plane
                + "','" + p.visit_home_date
                + "','" + p.visit_home_nextdate
                + "','" + p.visit_home_remark
                + "','" + p.visit_home_record_time
                + "','" + p.visit_home_modify_time
                + "','" + p.visit_home_cancle_time
                + "','" + p.visit_home_staff_record
                + "','" + p.visit_home_staff_modify
                + "','" + p.visit_home_staff_cancle
                + "','" + p.visit_home_active
                + "','" + p.patient_id
                + "','" + p.family_id
                + "')";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectByPatientID(String patient_id) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.patient_id
                + " = '" + patient_id +"'"
                + " and "+dbObj.visit_home_active+" = '1'"
                + " order by "+dbObj.visit_home_date;
        return eQuery(sql);
    }
    
    public Vector selectByFamilyID(String family_id) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.family_id
                + " = '" + family_id +"'"
                + " and "+dbObj.visit_home_active+" = '1'"
                + " order by "+dbObj.visit_home_date;
        return eQuery(sql);
    }
    
    
    public Vector eQuery(String sql) throws Exception {
        VisitHome p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) {
            p = new VisitHome();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.visit_home_problem = rs.getString(dbObj.visit_home_problem);
            p.visit_home_object = rs.getString(dbObj.visit_home_object);
            p.visit_home_maintain = rs.getString(dbObj.visit_home_maintain);
            p.visit_home_assess = rs.getString(dbObj.visit_home_assess);
            p.visit_home_plane = rs.getString(dbObj.visit_home_plane);
            p.visit_home_date = rs.getString(dbObj.visit_home_date);
            p.visit_home_nextdate = rs.getString(dbObj.visit_home_nextdate);
            p.visit_home_remark = rs.getString(dbObj.visit_home_remark);
            p.visit_home_record_time = rs.getString(dbObj.visit_home_record_time);
            p.visit_home_modify_time = rs.getString(dbObj.visit_home_modify_time);
            p.visit_home_cancle_time = rs.getString(dbObj.visit_home_cancle_time);
            p.visit_home_staff_record = rs.getString(dbObj.visit_home_staff_record);
            p.visit_home_staff_modify = rs.getString(dbObj.visit_home_staff_modify);
            p.visit_home_staff_cancle = rs.getString(dbObj.visit_home_staff_cancle);
            p.visit_home_active = rs.getString(dbObj.visit_home_active); 
            p.patient_id = rs.getString(dbObj.patient_id);
            p.family_id = rs.getString(dbObj.family_id);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public int update(VisitHome o) throws Exception {
        String sql="update " + dbObj.table + " set ";
        VisitHome p=o;
        String field =""
        + "', " + dbObj.visit_home_problem + "='" + p.visit_home_problem
        + "', " + dbObj.visit_home_object + "='" + p.visit_home_object
        + "', " + dbObj.visit_home_maintain + "='" + p.visit_home_maintain
        + "', " + dbObj.visit_home_assess + "='" + p.visit_home_assess
        + "', " + dbObj.visit_home_plane + "='" + p.visit_home_plane
        + "', " + dbObj.visit_home_date + "='" + p.visit_home_date
        + "', " + dbObj.visit_home_nextdate + "='" + p.visit_home_nextdate
        + "', " + dbObj.visit_home_remark + "='" + p.visit_home_remark
        + "', " + dbObj.visit_home_record_time + "='" + p.visit_home_record_time
        + "', " + dbObj.visit_home_modify_time + "='" + p.visit_home_modify_time
        + "', " + dbObj.visit_home_cancle_time + "='" + p.visit_home_cancle_time
        + "', " + dbObj.visit_home_staff_record + "='" + p.visit_home_staff_record
        + "', " + dbObj.visit_home_staff_modify + "='" + p.visit_home_staff_modify
        + "', " + dbObj.visit_home_staff_cancle + "='" + p.visit_home_staff_cancle
        + "', " + dbObj.visit_home_active + "='" + p.visit_home_active 
        + "', " + dbObj.patient_id + "='" + p.patient_id
        + "', " + dbObj.family_id + "='" + p.family_id
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";        
        sql = sql+field.substring(2); 
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(VisitHome o) throws Exception {
        String sql="delete from " + dbObj.table
                + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
//    test from noom (เดี่ยวลบออก)
//    public static void main(String args[]) throws Exception{
//        String url = "jdbc:postgresql://192.168.1.6:5432/develop_support";
//        String user = "postgres";
//        String pass = "postgres";
//        String dri = "org.postgresql.Driver";
//        String type = "0"; //0 postgres 1 mysql 2 sqlserver
//        ConnectionInf theConnectionInf = new ConnectionDBMgr(dri,url,user,pass,type);
//        
//        VisitHomeDB v = new VisitHomeDB(theConnectionInf);
//        VisitHome vh = new VisitHome();
//        vh.pk_field = "555dd";
//        v.insert(vh);
//        /*
//        Vector vec = v.selectByPatientID("666");
//        
//        for(int i=0;i<vec.size();i++){
//            vh = (VisitHome)vec.elementAt(i);
//            
//            
//        }
//         */
//    }
/////////////////////////////////////////////////////////////////////////////    
    public int updateFidByPtid(String family_id,String patient_id) throws Exception
    {
        String sql="update " + dbObj.table + " set " 
                + dbObj.family_id + "='" + family_id
                + "' where " + dbObj.patient_id + "='" + patient_id +"'";
        return theConnectionInf.eUpdate(sql);
    }  
/////////////////////////////////////////////////////////////////////////////    
    public int updateFidByFid(String family_id,String family_from) throws Exception
    {
        String sql="update " + dbObj.table + " set " 
                + dbObj.family_id + "='" + family_id + "',"
                + dbObj.visit_home_staff_cancle + "="+ dbObj.visit_home_staff_cancle + "||'-'||'" + family_from + "'"
                + " where " + dbObj.family_id + "='" + family_from +"'";
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
/**
 * ยังใช้ไม่ได้ต้องรอการปรับฐานข้อมูลในรอบหน้า
 * @param patient_id
 * @param visit_id
 * @return
 * @throws Exception
 */
    public Vector selectByPVid(String patient_id, String visit_id) throws Exception {

        String sql="select * from " + dbObj.table
                + " where " + dbObj.patient_id + " = '" + patient_id +"'"
                + " and "+dbObj.visit_home_active+" = '1'";
//        if(visit_id!=null)
//            sql+= " and " + dbObj.visit_id + " = '" + visit_id +"'";
        sql+=" order by "+dbObj.visit_home_date;
        return eQuery(sql);
    }
    
}
