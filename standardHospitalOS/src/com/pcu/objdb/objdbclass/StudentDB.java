/*
 * StudentDB.java
 *
 * Created on 20 มิถุนายน 2548, 18:25 น.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.pcu.object.Student;


/**
 *
 * @author Noom
 */
public class StudentDB {
    public ConnectionInf theConnectionInf;
    public Student dbObj;
    final private String idtable = "734";
    
    
    /** Creates a new instance of StudentDB */
    public StudentDB() {
    }
    
    public StudentDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new Student();
        initConfig();
    }
    
    public boolean initConfig() {
        dbObj.table = "t_health_student";
        dbObj.pk_field = "t_health_student_id";
        dbObj.t_health_visit_school_id ="t_health_visit_school_id";
        dbObj.f_patient_prefix_id = "f_patient_prefix_id";
        dbObj.f_sex_id = "f_sex_id";
        dbObj.student_firstname ="student_firstname";
        dbObj.student_surname ="student_surname";
        dbObj.student_number ="student_number";
        dbObj.student_pid ="student_pid";
        dbObj.student_note ="student_note";
        dbObj.student_record_time = "student_record_time";
        dbObj.student_modify_time = "student_modify_time";
        dbObj.student_cancle_time = "student_cancle_time";
        dbObj.student_staff_record = "student_staff_record";
        dbObj.student_staff_modify = "student_staff_modify";
        dbObj.student_staff_cancle = "student_staff_cancle";
        dbObj.student_active ="student_active";
        
        return true;
    }
    
    public int insert(Student o) throws Exception {
        String sql="";
        Student p=o;
        if(o.getObjectId()==null)
            p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"	+ dbObj.t_health_visit_school_id
                + " ,"	+ dbObj.f_patient_prefix_id
                + " ,"	+ dbObj.f_sex_id
                + " ,"	+ dbObj.student_firstname
                + " ,"	+ dbObj.student_surname
                + " ,"	+ dbObj.student_number
                + " ,"	+ dbObj.student_pid
                + " ,"	+ dbObj.student_note
                + " ,"	+ dbObj.student_record_time
                + " ,"	+ dbObj.student_modify_time
                + " ,"	+ dbObj.student_cancle_time
                + " ,"	+ dbObj.student_staff_record
                + " ,"	+ dbObj.student_staff_modify
                + " ,"	+ dbObj.student_staff_cancle
                + " ,"	+ dbObj.student_active
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.t_health_visit_school_id
                + "','" + p.f_patient_prefix_id
                + "','" + p.f_sex_id
                + "','" + p.student_firstname
                + "','" + p.student_surname
                + "','" + p.student_number
                + "','" + p.student_pid
                + "','" + p.student_note
                + "','" + p.student_record_time
                + "','" + p.student_modify_time
                + "','" + p.student_cancle_time
                + "','" + p.student_staff_record
                + "','" + p.student_staff_modify
                + "','" + p.student_staff_cancle
                + "','" + p.student_active
                + "')";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAll() throws Exception {
        String sql="select * from " + dbObj.table
                + " where "+dbObj.student_active+" = '1'"
                + " order by "+dbObj.student_record_time;
        Vector v =eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
     public Vector selectByPK(String pk) throws Exception{
        Vector vStudent = new Vector();
        String sql ="select * from "+ dbObj.table 
        + " where " + dbObj.pk_field + "='" + pk +"'" 
        + " and "+dbObj.student_active + " = '1' ";
        vStudent = eQuery(sql);
        if(vStudent.size()==0)
            return null;
        else
            return vStudent;        
    }
    
    public Vector selectByVisitSchoolID(String visit_school_id) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.t_health_visit_school_id
                + " = '" + visit_school_id + "'"
                + " and "+dbObj.student_active+" = '1'"
                + " order by "+dbObj.student_record_time;
        Vector v =eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
   
    public Vector eQuery(String sql) throws Exception {
        Student p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) {
            p = new Student();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.t_health_visit_school_id = rs.getString(dbObj.t_health_visit_school_id);
            p.f_patient_prefix_id = rs.getString(dbObj.f_patient_prefix_id);
            p.f_sex_id = rs.getString(dbObj.f_sex_id);
            p.student_firstname = rs.getString(dbObj.student_firstname);
            p.student_surname = rs.getString(dbObj.student_surname);
            p.student_number = rs.getString(dbObj.student_number);
            p.student_pid = rs.getString(dbObj.student_pid);
            p.student_note = rs.getString(dbObj.student_note);
            p.student_record_time = rs.getString(dbObj.student_record_time);
            p.student_modify_time = rs.getString(dbObj.student_modify_time);
            p.student_cancle_time = rs.getString(dbObj.student_cancle_time);
            p.student_staff_record = rs.getString(dbObj.student_staff_record);
            p.student_staff_modify = rs.getString(dbObj.student_staff_modify);
            p.student_staff_cancle = rs.getString(dbObj.student_staff_cancle);
            p.student_active = rs.getString(dbObj.student_active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public int update(Student o) throws Exception {
        String sql="update " + dbObj.table + " set ";
        Student p=o;
        sql += dbObj.t_health_visit_school_id + "='" + p.t_health_visit_school_id
                + "', " + dbObj.f_patient_prefix_id + "='" + p.f_patient_prefix_id
                + "', " + dbObj.f_sex_id + "='" + p.f_sex_id
                + "', " + dbObj.student_firstname + "='" + p.student_firstname
                + "', " + dbObj.student_surname + "='" + p.student_surname
                + "', " + dbObj.student_number + "='" + p.student_number
                + "', " + dbObj.student_pid + "='" + p.student_pid
                + "', " + dbObj.student_note + "='" + p.student_note
                + "', " + dbObj.student_record_time + "='" + p.student_record_time
                + "', " + dbObj.student_modify_time + "='" + p.student_modify_time
                + "', " + dbObj.student_cancle_time + "='" + p.student_cancle_time
                + "', " + dbObj.student_staff_record + "='" + p.student_staff_record
                + "', " + dbObj.student_staff_modify + "='" + p.student_staff_modify
                + "', " + dbObj.student_staff_cancle + "='" + p.student_staff_cancle
                + "', " + dbObj.student_active + "='" + p.student_active
                + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(Student o) throws Exception {
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
        
        StudentDB v = new StudentDB(theConnectionInf);
        Student vh = new Student();
        vh.pk_field = "555dd";
        vh.student_firstname = "tanate";
        vh.t_health_visit_school_id = "704888885492563075";
        vh.student_pid = "555";
        vh.student_active = "1";
        v.insert(vh);
        Vector vec = v.selectByVisitSchoolID("704888885492563075");
        if(vec != null){
            
            for(int i=0;i<vec.size();i++){
                vh = (Student)vec.elementAt(i);
                //
                
            }
        }
       
    }
    
}
