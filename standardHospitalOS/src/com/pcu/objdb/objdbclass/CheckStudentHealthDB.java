/*
 * CheckStudentHealthDB.java
 *
 * Created on 13 �Զع�¹ 2548, 09:25 �.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.pcu.object.CheckStudentHealth;


/**
 *
 * @author Noom
 */
public class CheckStudentHealthDB {
    public ConnectionInf theConnectionInf;
    public CheckStudentHealth dbObj;
    final private String idtable = "750";
    
    
    /** Creates a new instance of CheckStudentHealthDB */
    public CheckStudentHealthDB() {
    }
    
    public CheckStudentHealthDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new CheckStudentHealth();
        initConfig();
    }
    
    public boolean initConfig() {
        dbObj.table = "t_health_check_student_health";
        dbObj.pk_field = "t_health_check_student_health_id";
        dbObj.t_health_student_id ="t_health_student_id";
        dbObj.t_health_visit_school_id ="t_health_visit_school_id";
        dbObj.f_answer_id ="f_answer_id";
        dbObj.f_answer_check_student_health_id = "f_answer_check_student_health_id";
        dbObj.check_student_health_tall  ="check_student_health_tall";
        dbObj.check_student_health_weight  ="check_student_health_weight";
        dbObj.check_student_health_note ="check_student_health_note";
        dbObj.check_student_health_remark ="check_student_health_remark";
        dbObj.b_visit_refer_office_id ="b_visit_refer_office_id";    
        dbObj.check_student_health_record_time = "check_student_health_record_time";
        dbObj.check_student_health_modify_time = "check_student_health_modify_time";
        dbObj.check_student_health_cancle_time = "check_student_health_cancle_time";
        dbObj.check_student_health_staff_record = "check_student_health_staff_record";
        dbObj.check_student_health_staff_modify = "check_student_health_staff_modify";
        dbObj.check_student_health_staff_cancle = "check_student_health_staff_cancle";
        dbObj.check_student_health_active ="check_student_health_active";
        
        return true;
    }
    
    public int insert(CheckStudentHealth o) throws Exception {
        String sql="";
        CheckStudentHealth p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"	+ dbObj.t_health_student_id
                + " ,"	+ dbObj.t_health_visit_school_id
                + " ,"	+ dbObj.f_answer_id
                + " ,"	+ dbObj.f_answer_check_student_health_id
                + " ,"	+ dbObj.check_student_health_tall 
                + " ,"	+ dbObj.check_student_health_weight 
                + " ,"	+ dbObj.check_student_health_note
                + " ,"	+ dbObj.check_student_health_remark
                + " ,"	+ dbObj.b_visit_refer_office_id
                + " ,"	+ dbObj.check_student_health_record_time
                + " ,"	+ dbObj.check_student_health_modify_time
                + " ,"	+ dbObj.check_student_health_cancle_time
                + " ,"	+ dbObj.check_student_health_staff_record
                + " ,"	+ dbObj.check_student_health_staff_modify
                + " ,"	+ dbObj.check_student_health_staff_cancle
                + " ,"	+ dbObj.check_student_health_active
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.t_health_student_id
                + "','" + p.t_health_visit_school_id
                + "','" + p.f_answer_id
                + "','" + p.f_answer_check_student_health_id
                + "','" + p.check_student_health_tall 
                + "','" + p.check_student_health_weight 
                + "','" + p.check_student_health_note
                + "','" + p.check_student_health_remark
                + "','" + p.b_visit_refer_office_id
                + "','" + p.check_student_health_record_time
                + "','" + p.check_student_health_modify_time
                + "','" + p.check_student_health_cancle_time
                + "','" + p.check_student_health_staff_record
                + "','" + p.check_student_health_staff_modify
                + "','" + p.check_student_health_staff_cancle
                + "','" + p.check_student_health_active
                + "')";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAll() throws Exception {
        String sql="select * from " + dbObj.table
                + " where "+dbObj.check_student_health_active+" = '1'"
                + " order by "+dbObj.check_student_health_record_time;
        Vector v =eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
     public Vector selectByPK(String pk) throws Exception{
        Vector vCheckStudentHealth = new Vector();
        String sql ="select * from "+ dbObj.table 
        + " where " + dbObj.pk_field + "='" + pk +"'" 
        + " and "+dbObj.check_student_health_active + " = '1' ";
        vCheckStudentHealth = eQuery(sql);
        if(vCheckStudentHealth.size()==0)
            return null;
        else
            return vCheckStudentHealth;        
    }
    
    public Vector selectByVisitSchoolID(String visit_school_id) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.t_health_visit_school_id
                + " = '" + visit_school_id + "'"
                + " and "+dbObj.check_student_health_active+" = '1'"
                + " order by "+dbObj.check_student_health_record_time;
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
                + " and "+dbObj.check_student_health_active+" = '1'"
                + " order by "+dbObj.check_student_health_record_time;
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
   
    
    public Vector eQuery(String sql) throws Exception {
        CheckStudentHealth p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) {
            p = new CheckStudentHealth();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.t_health_student_id = rs.getString(dbObj.t_health_student_id);
            p.t_health_visit_school_id = rs.getString(dbObj.t_health_visit_school_id);
            p.f_answer_id = rs.getString(dbObj.f_answer_id);
            p.f_answer_check_student_health_id = rs.getString(dbObj.f_answer_check_student_health_id);
            p.check_student_health_tall  = rs.getString(dbObj.check_student_health_tall);
            p.check_student_health_weight  = rs.getString(dbObj.check_student_health_weight );
            p.check_student_health_note = rs.getString(dbObj.check_student_health_note);
            p.check_student_health_remark = rs.getString(dbObj.check_student_health_remark);
            p.b_visit_refer_office_id = rs.getString(dbObj.b_visit_refer_office_id);
            p.check_student_health_record_time = rs.getString(dbObj.check_student_health_record_time);
            p.check_student_health_modify_time = rs.getString(dbObj.check_student_health_modify_time);
            p.check_student_health_cancle_time = rs.getString(dbObj.check_student_health_cancle_time);
            p.check_student_health_staff_record = rs.getString(dbObj.check_student_health_staff_record);
            p.check_student_health_staff_modify = rs.getString(dbObj.check_student_health_staff_modify);
            p.check_student_health_staff_cancle = rs.getString(dbObj.check_student_health_staff_cancle);
            p.check_student_health_active = rs.getString(dbObj.check_student_health_active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public int update(CheckStudentHealth o) throws Exception {
        String sql="update " + dbObj.table + " set ";
        CheckStudentHealth p=o;
        sql += dbObj.t_health_student_id + "='" + p.t_health_student_id
                + "', " + dbObj.t_health_visit_school_id + "='" + p.t_health_visit_school_id
                + "', " + dbObj.f_answer_id + "='" + p.f_answer_id
                + "', " + dbObj.f_answer_check_student_health_id + "='" + p.f_answer_check_student_health_id
                + "', " + dbObj.check_student_health_tall  + "='" + p.check_student_health_tall 
                + "', " + dbObj.check_student_health_weight  + "='" + p.check_student_health_weight 
                + "', " + dbObj.check_student_health_note + "='" + p.check_student_health_note
                + "', " + dbObj.check_student_health_remark + "='" + p.check_student_health_remark
                + "', " + dbObj.b_visit_refer_office_id + "='" + p.b_visit_refer_office_id
                + "', " + dbObj.check_student_health_record_time + "='" + p.check_student_health_record_time
                + "', " + dbObj.check_student_health_modify_time + "='" + p.check_student_health_modify_time
                + "', " + dbObj.check_student_health_cancle_time + "='" + p.check_student_health_cancle_time
                + "', " + dbObj.check_student_health_staff_record + "='" + p.check_student_health_staff_record
                + "', " + dbObj.check_student_health_staff_modify + "='" + p.check_student_health_staff_modify
                + "', " + dbObj.check_student_health_staff_cancle + "='" + p.check_student_health_staff_cancle
                + "', " + dbObj.check_student_health_active + "='" + p.check_student_health_active
                + "' where " + dbObj.pk_field + "= '" + p.getObjectId() +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(CheckStudentHealth o) throws Exception {
        String sql="delete from " + dbObj.table
                + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    //test from noom (�����ź�͡)
    public static void main(String args[]) throws Exception{
        String url = "jdbc:postgresql://192.168.1.6:5432/develop_support";
        String user = "postgres";
        String pass = "postgres";
        String dri = "org.postgresql.Driver";
        String type = "0"; //0 postgres 1 mysql 2 sqlserver
        ConnectionInf theConnectionInf = new ConnectionDBMgr(dri,url,user,pass,type);
        
        CheckStudentHealthDB v = new CheckStudentHealthDB(theConnectionInf);
        CheckStudentHealth vh = new CheckStudentHealth();
        vh.pk_field = "555dd";
        vh.t_health_visit_school_id = "55";
        //vh.b_visit_refer_office_id = "/////";
        v.insert(vh);

        Vector vec = v.selectByVisitSchoolID("55");
        if(vec != null){
            
            for(int i=0;i<vec.size();i++){
                vh = (CheckStudentHealth)vec.elementAt(i);
                
                
            }
        }
       
    }
    
}
