/*
 * CheckStudentDentalDB.java
 *
 * Created on 13 กรกฏาคม 2548, 11:15 น
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.pcu.object.CheckStudentDental;


/**
 *
 * @author Noom
 */
public class CheckStudentDentalDB {
    public ConnectionInf theConnectionInf;
    public CheckStudentDental dbObj;
    final private String idtable = "778";
    
    
    /** Creates a new instance of CheckStudentDentalDB */
    public CheckStudentDentalDB() {
    }
    
    public CheckStudentDentalDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new CheckStudentDental();
        initConfig();
    }
    
    public boolean initConfig() {
        dbObj.table = "t_health_check_student_dental";
        dbObj.pk_field = "t_health_check_student_dental_id";
        dbObj.t_health_student_id ="t_health_student_id";
        dbObj.t_health_visit_school_id ="t_health_visit_school_id";
        dbObj.f_answer_id ="f_answer_id";
        dbObj.check_student_dental_num_tooth = "check_student_dental_num_tooth";
        dbObj.check_student_dental_num_bad_tooth = "check_student_dental_num_bad_tooth";
        dbObj.check_student_dental_num_milktooth = "check_student_dental_num_milktooth";
        dbObj.check_student_dental_num_bad_milktooth = "check_student_dental_num_bad_milktooth";
        dbObj.f_health_gum_level_id = "f_health_gum_level_id";
        dbObj.check_student_dental_note ="check_student_dental_note";
        dbObj.check_student_dental_remark ="check_student_dental_remark";
        dbObj.b_visit_refer_office_id ="b_visit_refer_office_id";
        dbObj.check_student_dental_record_time = "check_student_dental_record_time";
        dbObj.check_student_dental_modify_time = "check_student_dental_modify_time";
        dbObj.check_student_dental_cancle_time = "check_student_dental_cancle_time";
        dbObj.check_student_dental_staff_record = "check_student_dental_staff_record";
        dbObj.check_student_dental_staff_modify = "check_student_dental_staff_modify";
        dbObj.check_student_dental_staff_cancle = "check_student_dental_staff_cancle";
        dbObj.check_student_dental_active ="check_student_dental_active";
        
        return true;
    }
    
    public int insert(CheckStudentDental o) throws Exception {
        String sql="";
        CheckStudentDental p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"	+ dbObj.t_health_student_id
                + " ,"	+ dbObj.t_health_visit_school_id
                + " ,"	+ dbObj.f_answer_id
                + " ,"	+ dbObj.check_student_dental_num_tooth
                + " ,"	+ dbObj.check_student_dental_num_bad_tooth
                + " ,"	+ dbObj.check_student_dental_num_milktooth
                + " ,"	+ dbObj.check_student_dental_num_bad_milktooth
                + " ,"	+ dbObj.f_health_gum_level_id
                + " ,"	+ dbObj.check_student_dental_note
                + " ,"	+ dbObj.check_student_dental_remark
                + " ,"	+ dbObj.b_visit_refer_office_id
                + " ,"	+ dbObj.check_student_dental_record_time
                + " ,"	+ dbObj.check_student_dental_modify_time
                + " ,"	+ dbObj.check_student_dental_cancle_time
                + " ,"	+ dbObj.check_student_dental_staff_record
                + " ,"	+ dbObj.check_student_dental_staff_modify
                + " ,"	+ dbObj.check_student_dental_staff_cancle
                + " ,"	+ dbObj.check_student_dental_active
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.t_health_student_id
                + "','" + p.t_health_visit_school_id
                + "','" + p.f_answer_id
                + "','" + p.check_student_dental_num_tooth
                + "','" + p.check_student_dental_num_bad_tooth
                + "','" + p.check_student_dental_num_milktooth
                + "','" + p.check_student_dental_num_bad_milktooth
                + "','" + p.f_health_gum_level_id
                + "','" + p.check_student_dental_note
                + "','" + p.check_student_dental_remark
                + "','" + p.b_visit_refer_office_id
                + "','" + p.check_student_dental_record_time
                + "','" + p.check_student_dental_modify_time
                + "','" + p.check_student_dental_cancle_time
                + "','" + p.check_student_dental_staff_record
                + "','" + p.check_student_dental_staff_modify
                + "','" + p.check_student_dental_staff_cancle
                + "','" + p.check_student_dental_active
                + "')";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAll() throws Exception {
        String sql="select * from " + dbObj.table
                + " where "+dbObj.check_student_dental_active+" = '1'"
                + " order by "+dbObj.check_student_dental_record_time;
        Vector v =eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector selectByPK(String pk) throws Exception{
        Vector vCheckStudentDental = new Vector();
        String sql ="select * from "+ dbObj.table
                + " where " + dbObj.pk_field + "='" + pk +"'"
                + " and "+dbObj.check_student_dental_active + " = '1' ";
        vCheckStudentDental = eQuery(sql);
        if(vCheckStudentDental.size()==0)
            return null;
        else
            return vCheckStudentDental;
    }
    
    public Vector selectByVisitSchoolID(String visit_school_id) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.t_health_visit_school_id
                + " = '" + visit_school_id + "'"
                + " and "+dbObj.check_student_dental_active+" = '1'"
                + " order by "+dbObj.check_student_dental_record_time;
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
                + " and "+dbObj.check_student_dental_active+" = '1'"
                + " order by "+dbObj.check_student_dental_record_time;
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    
    
    public Vector eQuery(String sql) throws Exception {
        CheckStudentDental p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) {
            p = new CheckStudentDental();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.t_health_student_id = rs.getString(dbObj.t_health_student_id);
            p.t_health_visit_school_id = rs.getString(dbObj.t_health_visit_school_id);
            p.f_answer_id = rs.getString(dbObj.f_answer_id);
            p.check_student_dental_num_tooth = rs.getString(dbObj.check_student_dental_num_tooth);
            p.check_student_dental_num_bad_tooth = rs.getString(dbObj.check_student_dental_num_bad_tooth);
            p.check_student_dental_num_milktooth = rs.getString(dbObj.check_student_dental_num_milktooth);
            p.check_student_dental_num_bad_milktooth = rs.getString(dbObj.check_student_dental_num_bad_milktooth);
            p.f_health_gum_level_id = rs.getString(dbObj.f_health_gum_level_id);
            p.check_student_dental_note = rs.getString(dbObj.check_student_dental_note);
            p.check_student_dental_remark = rs.getString(dbObj.check_student_dental_remark);
            p.b_visit_refer_office_id = rs.getString(dbObj.b_visit_refer_office_id);
            p.check_student_dental_record_time = rs.getString(dbObj.check_student_dental_record_time);
            p.check_student_dental_modify_time = rs.getString(dbObj.check_student_dental_modify_time);
            p.check_student_dental_cancle_time = rs.getString(dbObj.check_student_dental_cancle_time);
            p.check_student_dental_staff_record = rs.getString(dbObj.check_student_dental_staff_record);
            p.check_student_dental_staff_modify = rs.getString(dbObj.check_student_dental_staff_modify);
            p.check_student_dental_staff_cancle = rs.getString(dbObj.check_student_dental_staff_cancle);
            p.check_student_dental_active = rs.getString(dbObj.check_student_dental_active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public int update(CheckStudentDental o) throws Exception {
        String sql="update " + dbObj.table + " set ";
        CheckStudentDental p=o;
        sql += dbObj.t_health_student_id + "='" + p.t_health_student_id
                + "', " + dbObj.t_health_visit_school_id + "='" + p.t_health_visit_school_id
                + "', " + dbObj.f_answer_id + "='" + p.f_answer_id
                + "', " + dbObj.check_student_dental_num_tooth + "='" + p.check_student_dental_num_tooth
                + "', " + dbObj.check_student_dental_num_bad_tooth + "='" + p.check_student_dental_num_bad_tooth
                + "', " + dbObj.check_student_dental_num_milktooth + "='" + p.check_student_dental_num_milktooth
                + "', " + dbObj.check_student_dental_num_bad_milktooth + "='" + p.check_student_dental_num_bad_milktooth
                + "', " + dbObj.f_health_gum_level_id + "='" + p.f_health_gum_level_id
                + "', " + dbObj.check_student_dental_note + "='" + p.check_student_dental_note
                + "', " + dbObj.check_student_dental_remark + "='" + p.check_student_dental_remark
                + "', " + dbObj.b_visit_refer_office_id + "='" + p.b_visit_refer_office_id
                + "', " + dbObj.check_student_dental_record_time + "='" + p.check_student_dental_record_time
                + "', " + dbObj.check_student_dental_modify_time + "='" + p.check_student_dental_modify_time
                + "', " + dbObj.check_student_dental_cancle_time + "='" + p.check_student_dental_cancle_time
                + "', " + dbObj.check_student_dental_staff_record + "='" + p.check_student_dental_staff_record
                + "', " + dbObj.check_student_dental_staff_modify + "='" + p.check_student_dental_staff_modify
                + "', " + dbObj.check_student_dental_staff_cancle + "='" + p.check_student_dental_staff_cancle
                + "', " + dbObj.check_student_dental_active + "='" + p.check_student_dental_active
                + "' where " + dbObj.pk_field + "= '" + p.getObjectId() +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(CheckStudentDental o) throws Exception {
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
        
        CheckStudentDentalDB v = new CheckStudentDentalDB(theConnectionInf);
        CheckStudentDental vh = new CheckStudentDental();
        vh.pk_field = "555dd";
        vh.t_health_visit_school_id = "55";
        //vh.b_visit_refer_office_id = "/////";
        v.insert(vh);
        
        Vector vec = v.selectByVisitSchoolID("55");
        if(vec != null){
            
            for(int i=0;i<vec.size();i++){
                vh = (CheckStudentDental)vec.elementAt(i);
                
                
            }
        }
        
    }
    
}
