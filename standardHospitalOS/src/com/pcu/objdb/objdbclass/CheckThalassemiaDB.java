/*
 * CheckThalassemiaDB.java
 *
 * Created on 13 กรกฏาคม 2548, 10:20 น
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.pcu.object.CheckThalassemia;


/**
 *
 * @author Noom
 */
public class CheckThalassemiaDB {
    public ConnectionInf theConnectionInf;
    public CheckThalassemia dbObj;
    final private String idtable = "756";
    
    
    /** Creates a new instance of CheckThalassemiaDB */
    public CheckThalassemiaDB() {
    }
    
    public CheckThalassemiaDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new CheckThalassemia();
        initConfig();
    }
    
    public boolean initConfig() {
        dbObj.table = "t_health_check_thalassemia";
        dbObj.pk_field = "t_health_check_thalassemia_id";
        dbObj.t_health_student_id ="t_health_student_id";
        dbObj.t_health_visit_school_id ="t_health_visit_school_id";
        dbObj.f_answer_id ="f_answer_id";
        dbObj.f_answer_check_thalassemia_id = "f_answer_check_thalassemia_id";
        dbObj.check_thalassemia_note ="check_thalassemia_note";
        dbObj.check_thalassemia_remark ="check_thalassemia_remark";
        dbObj.b_visit_refer_office_id ="b_visit_refer_office_id";    
        dbObj.check_thalassemia_record_time = "check_thalassemia_record_time";
        dbObj.check_thalassemia_modify_time = "check_thalassemia_modify_time";
        dbObj.check_thalassemia_cancle_time = "check_thalassemia_cancle_time";
        dbObj.check_thalassemia_staff_record = "check_thalassemia_staff_record";
        dbObj.check_thalassemia_staff_modify = "check_thalassemia_staff_modify";
        dbObj.check_thalassemia_staff_cancle = "check_thalassemia_staff_cancle";
        dbObj.check_thalassemia_active ="check_thalassemia_active";
        
        return true;
    }
    
    public int insert(CheckThalassemia o) throws Exception {
        String sql="";
        CheckThalassemia p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"	+ dbObj.t_health_student_id
                + " ,"	+ dbObj.t_health_visit_school_id
                + " ,"	+ dbObj.f_answer_id
                + " ,"	+ dbObj.f_answer_check_thalassemia_id
                + " ,"	+ dbObj.check_thalassemia_note
                + " ,"	+ dbObj.check_thalassemia_remark
                + " ,"	+ dbObj.b_visit_refer_office_id
                + " ,"	+ dbObj.check_thalassemia_record_time
                + " ,"	+ dbObj.check_thalassemia_modify_time
                + " ,"	+ dbObj.check_thalassemia_cancle_time
                + " ,"	+ dbObj.check_thalassemia_staff_record
                + " ,"	+ dbObj.check_thalassemia_staff_modify
                + " ,"	+ dbObj.check_thalassemia_staff_cancle
                + " ,"	+ dbObj.check_thalassemia_active
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.t_health_student_id
                + "','" + p.t_health_visit_school_id
                + "','" + p.f_answer_id
                + "','" + p.f_answer_check_thalassemia_id
                + "','" + p.check_thalassemia_note
                + "','" + p.check_thalassemia_remark
                + "','" + p.b_visit_refer_office_id
                + "','" + p.check_thalassemia_record_time
                + "','" + p.check_thalassemia_modify_time
                + "','" + p.check_thalassemia_cancle_time
                + "','" + p.check_thalassemia_staff_record
                + "','" + p.check_thalassemia_staff_modify
                + "','" + p.check_thalassemia_staff_cancle
                + "','" + p.check_thalassemia_active
                + "')";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAll() throws Exception {
        String sql="select * from " + dbObj.table
                + " where "+dbObj.check_thalassemia_active+" = '1'"
                + " order by "+dbObj.check_thalassemia_record_time;
        Vector v =eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
     public Vector selectByPK(String pk) throws Exception{
        Vector vCheckThalassemia = new Vector();
        String sql ="select * from "+ dbObj.table 
        + " where " + dbObj.pk_field + "='" + pk +"'" 
        + " and "+dbObj.check_thalassemia_active + " = '1' ";
        vCheckThalassemia = eQuery(sql);
        if(vCheckThalassemia.size()==0)
            return null;
        else
            return vCheckThalassemia;        
    }
    
    public Vector selectByVisitSchoolID(String visit_school_id) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.t_health_visit_school_id
                + " = '" + visit_school_id + "'"
                + " and "+dbObj.check_thalassemia_active+" = '1'"
                + " order by "+dbObj.check_thalassemia_record_time;
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
                + " and "+dbObj.check_thalassemia_active+" = '1'"
                + " order by "+dbObj.check_thalassemia_record_time;
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
   
    
    public Vector eQuery(String sql) throws Exception {
        CheckThalassemia p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) {
            p = new CheckThalassemia();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.t_health_student_id = rs.getString(dbObj.t_health_student_id);
            p.t_health_visit_school_id = rs.getString(dbObj.t_health_visit_school_id);
            p.f_answer_id = rs.getString(dbObj.f_answer_id);
            p.f_answer_check_thalassemia_id = rs.getString(dbObj.f_answer_check_thalassemia_id);
            p.check_thalassemia_note = rs.getString(dbObj.check_thalassemia_note);
            p.check_thalassemia_remark = rs.getString(dbObj.check_thalassemia_remark);
            p.b_visit_refer_office_id = rs.getString(dbObj.b_visit_refer_office_id);
            p.check_thalassemia_record_time = rs.getString(dbObj.check_thalassemia_record_time);
            p.check_thalassemia_modify_time = rs.getString(dbObj.check_thalassemia_modify_time);
            p.check_thalassemia_cancle_time = rs.getString(dbObj.check_thalassemia_cancle_time);
            p.check_thalassemia_staff_record = rs.getString(dbObj.check_thalassemia_staff_record);
            p.check_thalassemia_staff_modify = rs.getString(dbObj.check_thalassemia_staff_modify);
            p.check_thalassemia_staff_cancle = rs.getString(dbObj.check_thalassemia_staff_cancle);
            p.check_thalassemia_active = rs.getString(dbObj.check_thalassemia_active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public int update(CheckThalassemia o) throws Exception {
        String sql="update " + dbObj.table + " set ";
        CheckThalassemia p=o;
        sql += dbObj.t_health_student_id + "='" + p.t_health_student_id
                + "', " + dbObj.t_health_visit_school_id + "='" + p.t_health_visit_school_id
                + "', " + dbObj.f_answer_id + "='" + p.f_answer_id
                + "', " + dbObj.f_answer_check_thalassemia_id + "='" + p.f_answer_check_thalassemia_id
                + "', " + dbObj.check_thalassemia_note + "='" + p.check_thalassemia_note
                + "', " + dbObj.check_thalassemia_remark + "='" + p.check_thalassemia_remark
                + "', " + dbObj.b_visit_refer_office_id + "='" + p.b_visit_refer_office_id
                + "', " + dbObj.check_thalassemia_record_time + "='" + p.check_thalassemia_record_time
                + "', " + dbObj.check_thalassemia_modify_time + "='" + p.check_thalassemia_modify_time
                + "', " + dbObj.check_thalassemia_cancle_time + "='" + p.check_thalassemia_cancle_time
                + "', " + dbObj.check_thalassemia_staff_record + "='" + p.check_thalassemia_staff_record
                + "', " + dbObj.check_thalassemia_staff_modify + "='" + p.check_thalassemia_staff_modify
                + "', " + dbObj.check_thalassemia_staff_cancle + "='" + p.check_thalassemia_staff_cancle
                + "', " + dbObj.check_thalassemia_active + "='" + p.check_thalassemia_active
                + "' where " + dbObj.pk_field + "= '" + p.getObjectId() +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(CheckThalassemia o) throws Exception {
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
        
        CheckThalassemiaDB v = new CheckThalassemiaDB(theConnectionInf);
        CheckThalassemia vh = new CheckThalassemia();
        vh.pk_field = "555dd";
        vh.t_health_visit_school_id = "55";
        //vh.b_visit_refer_office_id = "/////";
        v.insert(vh);

        Vector vec = v.selectByVisitSchoolID("55");
        if(vec != null){
            
            for(int i=0;i<vec.size();i++){
                vh = (CheckThalassemia)vec.elementAt(i);
                
                
            }
        }
       
    }
    
}
