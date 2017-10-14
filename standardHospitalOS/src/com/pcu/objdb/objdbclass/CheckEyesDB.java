/*
 * CheckEyesDB.java
 *
 * Created on 20 มิถุนายน 2548, 18:25 น.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.pcu.object.CheckEyes;


/**
 *
 * @author Noom
 */
public class CheckEyesDB {
    public ConnectionInf theConnectionInf;
    public CheckEyes dbObj;
    final private String idtable = "735";
    
    
    /** Creates a new instance of CheckEyesDB */
    public CheckEyesDB() {
    }
    
    public CheckEyesDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new CheckEyes();
        initConfig();
    }
    
    public boolean initConfig() {
        dbObj.table = "t_health_check_eyes";
        dbObj.pk_field = "t_health_check_eyes_id";
        dbObj.t_health_student_id ="t_health_student_id";
        dbObj.t_health_visit_school_id ="t_health_visit_school_id";
        dbObj.f_answer_id ="f_answer_id";
        dbObj.f_health_check_eyes_id = "f_health_check_eyes_id";
        dbObj.check_eyes_note ="check_eyes_note";
        dbObj.check_eyes_remark ="check_eyes_remark";
        dbObj.b_visit_refer_office_id ="b_visit_refer_office_id";    
        dbObj.check_eyes_record_time = "check_eyes_record_time";
        dbObj.check_eyes_modify_time = "check_eyes_modify_time";
        dbObj.check_eyes_cancle_time = "check_eyes_cancle_time";
        dbObj.check_eyes_staff_record = "check_eyes_staff_record";
        dbObj.check_eyes_staff_modify = "check_eyes_staff_modify";
        dbObj.check_eyes_staff_cancle = "check_eyes_staff_cancle";
        dbObj.check_eyes_active ="check_eyes_active";
        
        return true;
    }
    
    public int insert(CheckEyes o) throws Exception {
        String sql="";
        CheckEyes p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"	+ dbObj.t_health_student_id
                + " ,"	+ dbObj.t_health_visit_school_id
                + " ,"	+ dbObj.f_answer_id
                + " ,"	+ dbObj.f_health_check_eyes_id
                + " ,"	+ dbObj.check_eyes_note
                + " ,"	+ dbObj.check_eyes_remark
                + " ,"	+ dbObj.b_visit_refer_office_id
                + " ,"	+ dbObj.check_eyes_record_time
                + " ,"	+ dbObj.check_eyes_modify_time
                + " ,"	+ dbObj.check_eyes_cancle_time
                + " ,"	+ dbObj.check_eyes_staff_record
                + " ,"	+ dbObj.check_eyes_staff_modify
                + " ,"	+ dbObj.check_eyes_staff_cancle
                + " ,"	+ dbObj.check_eyes_active
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.t_health_student_id
                + "','" + p.t_health_visit_school_id
                + "','" + p.f_answer_id
                + "','" + p.f_health_check_eyes_id
                + "','" + p.check_eyes_note
                + "','" + p.check_eyes_remark
                + "','" + p.b_visit_refer_office_id
                + "','" + p.check_eyes_record_time
                + "','" + p.check_eyes_modify_time
                + "','" + p.check_eyes_cancle_time
                + "','" + p.check_eyes_staff_record
                + "','" + p.check_eyes_staff_modify
                + "','" + p.check_eyes_staff_cancle
                + "','" + p.check_eyes_active
                + "')";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAll() throws Exception {
        String sql="select * from " + dbObj.table
                + " where "+dbObj.check_eyes_active+" = '1'"
                + " order by "+dbObj.check_eyes_record_time;
        Vector v =eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
     public Vector selectByPK(String pk) throws Exception{
        Vector vCheckEyes = new Vector();
        String sql ="select * from "+ dbObj.table 
        + " where " + dbObj.pk_field + "='" + pk +"'" 
        + " and "+dbObj.check_eyes_active + " = '1' ";
        vCheckEyes = eQuery(sql);
        if(vCheckEyes.size()==0)
            return null;
        else
            return vCheckEyes;        
    }
    
    public Vector selectByVisitSchoolID(String visit_school_id) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.t_health_visit_school_id
                + " = '" + visit_school_id + "'"
                + " and "+dbObj.check_eyes_active+" = '1'"
                + " order by "+dbObj.check_eyes_record_time;
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
        public Vector selectByStudentID(String student_id) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.t_health_student_id
                + " = '" + student_id + "'"
                + " and "+dbObj.check_eyes_active+" = '1'"
                + " order by "+dbObj.check_eyes_record_time;
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
   
    
    public Vector eQuery(String sql) throws Exception {
        CheckEyes p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) {
            p = new CheckEyes();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.t_health_student_id = rs.getString(dbObj.t_health_student_id);
            p.t_health_visit_school_id = rs.getString(dbObj.t_health_visit_school_id);
            p.f_answer_id = rs.getString(dbObj.f_answer_id);
            p.f_health_check_eyes_id = rs.getString(dbObj.f_health_check_eyes_id);
            p.check_eyes_note = rs.getString(dbObj.check_eyes_note);
            p.check_eyes_remark = rs.getString(dbObj.check_eyes_remark);
            p.b_visit_refer_office_id = rs.getString(dbObj.b_visit_refer_office_id);
            p.check_eyes_record_time = rs.getString(dbObj.check_eyes_record_time);
            p.check_eyes_modify_time = rs.getString(dbObj.check_eyes_modify_time);
            p.check_eyes_cancle_time = rs.getString(dbObj.check_eyes_cancle_time);
            p.check_eyes_staff_record = rs.getString(dbObj.check_eyes_staff_record);
            p.check_eyes_staff_modify = rs.getString(dbObj.check_eyes_staff_modify);
            p.check_eyes_staff_cancle = rs.getString(dbObj.check_eyes_staff_cancle);
            p.check_eyes_active = rs.getString(dbObj.check_eyes_active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public int update(CheckEyes o) throws Exception {
        String sql="update " + dbObj.table + " set ";
        CheckEyes p=o;
        sql += dbObj.t_health_student_id + "='" + p.t_health_student_id
                + "', " + dbObj.t_health_visit_school_id + "='" + p.t_health_visit_school_id
                + "', " + dbObj.f_answer_id + "='" + p.f_answer_id
                + "', " + dbObj.f_health_check_eyes_id + "='" + p.f_health_check_eyes_id
                + "', " + dbObj.check_eyes_note + "='" + p.check_eyes_note
                + "', " + dbObj.check_eyes_remark + "='" + p.check_eyes_remark
                + "', " + dbObj.b_visit_refer_office_id + "='" + p.b_visit_refer_office_id
                + "', " + dbObj.check_eyes_record_time + "='" + p.check_eyes_record_time
                + "', " + dbObj.check_eyes_modify_time + "='" + p.check_eyes_modify_time
                + "', " + dbObj.check_eyes_cancle_time + "='" + p.check_eyes_cancle_time
                + "', " + dbObj.check_eyes_staff_record + "='" + p.check_eyes_staff_record
                + "', " + dbObj.check_eyes_staff_modify + "='" + p.check_eyes_staff_modify
                + "', " + dbObj.check_eyes_staff_cancle + "='" + p.check_eyes_staff_cancle
                + "', " + dbObj.check_eyes_active + "='" + p.check_eyes_active
                + "' where " + dbObj.pk_field + "= '" + p.getObjectId() +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(CheckEyes o) throws Exception {
        String sql="delete from " + dbObj.table
                + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    //test from noom (เดี่ยวลบออก)
    public static void main(String args[]) throws Exception{
        String url = "jdbc:postgresql://192.168.1.6:5432/develop_support";
        String user = "postgres";
        String pass = "postgres";
        String dri = "org.postgresql.Driver";
        String type = "0"; //0 postgres 1 mysql 2 sqlserver
        ConnectionInf theConnectionInf = new ConnectionDBMgr(dri,url,user,pass,type);
        
        CheckEyesDB v = new CheckEyesDB(theConnectionInf);
        CheckEyes vh = new CheckEyes();
        vh.pk_field = "555dd";
        vh.t_health_visit_school_id = "55";
        //vh.b_visit_refer_office_id = "/////";
        v.insert(vh);

        Vector vec = v.selectByVisitSchoolID("55");
        if(vec != null){
            
            for(int i=0;i<vec.size();i++){
                vh = (CheckEyes)vec.elementAt(i);
                
                
            }
        }
       
    }
    
}
