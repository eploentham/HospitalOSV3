/*
 * CheckEarsDB.java
 *
 * Created on 20 มิถุนายน 2548, 18:25 น.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.pcu.object.CheckEars;


/**
 *
 * @author Noom
 */
public class CheckEarsDB {
    public ConnectionInf theConnectionInf;
    public CheckEars dbObj;
    final private String idtable = "746";
    
    
    /** Creates a new instance of CheckEarsDB */
    public CheckEarsDB() {
    }
    
    public CheckEarsDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new CheckEars();
        initConfig();
    }
    
    public boolean initConfig() {
        dbObj.table = "t_health_check_ears";
        dbObj.pk_field = "t_health_check_ears_id";
        dbObj.t_health_student_id ="t_health_student_id";
        dbObj.t_health_visit_school_id ="t_health_visit_school_id";
        dbObj.f_answer_id ="f_answer_id";
        dbObj.f_health_check_ears_id = "f_health_check_ears_id";
        dbObj.b_visit_refer_office_id = "b_visit_refer_office_id";
        dbObj.check_ears_note ="check_ears_note";
        dbObj.check_ears_remark ="check_ears_remark";
        dbObj.check_ears_record_time = "check_ears_record_time";
        dbObj.check_ears_modify_time = "check_ears_modify_time";
        dbObj.check_ears_cancle_time = "check_ears_cancle_time";
        dbObj.check_ears_staff_record = "check_ears_staff_record";
        dbObj.check_ears_staff_modify = "check_ears_staff_modify";
        dbObj.check_ears_staff_cancle = "check_ears_staff_cancle";
        dbObj.check_ears_active ="check_ears_active";
        
        return true;
    }
    
    public int insert(CheckEars o) throws Exception {
        String sql="";
        CheckEars p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"	+ dbObj.t_health_student_id
                + " ,"	+ dbObj.t_health_visit_school_id
                + " ,"	+ dbObj.f_answer_id
                + " ,"	+ dbObj.f_health_check_ears_id
                + " ,"	+ dbObj.b_visit_refer_office_id
                + " ,"	+ dbObj.check_ears_note
                + " ,"	+ dbObj.check_ears_remark
                + " ,"	+ dbObj.check_ears_record_time
                + " ,"	+ dbObj.check_ears_modify_time
                + " ,"	+ dbObj.check_ears_cancle_time
                + " ,"	+ dbObj.check_ears_staff_record
                + " ,"	+ dbObj.check_ears_staff_modify
                + " ,"	+ dbObj.check_ears_staff_cancle
                + " ,"	+ dbObj.check_ears_active
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.t_health_student_id
                + "','" + p.t_health_visit_school_id
                + "','" + p.f_answer_id
                + "','" + p.f_health_check_ears_id
                + "','" + p.b_visit_refer_office_id
                + "','" + p.check_ears_note
                + "','" + p.check_ears_remark
                + "','" + p.check_ears_record_time
                + "','" + p.check_ears_modify_time
                + "','" + p.check_ears_cancle_time
                + "','" + p.check_ears_staff_record
                + "','" + p.check_ears_staff_modify
                + "','" + p.check_ears_staff_cancle
                + "','" + p.check_ears_active
                + "')";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAll() throws Exception {
        String sql="select * from " + dbObj.table
                + " where "+dbObj.check_ears_active+" = '1'"
                + " order by "+dbObj.check_ears_record_time;
        Vector v =eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
     public Vector selectByPK(String pk) throws Exception{
        Vector vCheckEars = new Vector();
        String sql ="select * from "+ dbObj.table 
        + " where " + dbObj.pk_field + "='" + pk +"'" 
        + " and "+dbObj.check_ears_active + " = '1' ";
        vCheckEars = eQuery(sql);
        if(vCheckEars.size()==0)
            return null;
        else
            return vCheckEars;        
    }
    
    public Vector selectByVisitSchoolID(String visit_school_id) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.t_health_visit_school_id
                + " = '" + visit_school_id + "'"
                + " and "+dbObj.check_ears_active+" = '1'"
                + " order by "+dbObj.check_ears_record_time;
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
                + " and "+dbObj.check_ears_active+" = '1'"
                + " order by "+dbObj.check_ears_record_time;
        Vector v = eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
   
    
    public Vector eQuery(String sql) throws Exception {
        CheckEars p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) {
            p = new CheckEars();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.t_health_student_id = rs.getString(dbObj.t_health_student_id);
            p.t_health_visit_school_id = rs.getString(dbObj.t_health_visit_school_id);
            p.f_answer_id = rs.getString(dbObj.f_answer_id);
            p.f_health_check_ears_id = rs.getString(dbObj.f_health_check_ears_id);
            p.b_visit_refer_office_id = rs.getString(dbObj.b_visit_refer_office_id);
            p.check_ears_note = rs.getString(dbObj.check_ears_note);
            p.check_ears_remark = rs.getString(dbObj.check_ears_remark);
            p.check_ears_record_time = rs.getString(dbObj.check_ears_record_time);
            p.check_ears_modify_time = rs.getString(dbObj.check_ears_modify_time);
            p.check_ears_cancle_time = rs.getString(dbObj.check_ears_cancle_time);
            p.check_ears_staff_record = rs.getString(dbObj.check_ears_staff_record);
            p.check_ears_staff_modify = rs.getString(dbObj.check_ears_staff_modify);
            p.check_ears_staff_cancle = rs.getString(dbObj.check_ears_staff_cancle);
            p.check_ears_active = rs.getString(dbObj.check_ears_active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public int update(CheckEars o) throws Exception {
        String sql="update " + dbObj.table + " set ";
        CheckEars p=o;
        sql += dbObj.t_health_student_id + "='" + p.t_health_student_id
                + "', " + dbObj.t_health_visit_school_id + "='" + p.t_health_visit_school_id
                + "', " + dbObj.f_answer_id + "='" + p.f_answer_id
                + "', " + dbObj.f_health_check_ears_id + "='" + p.f_health_check_ears_id
                + "', " + dbObj.b_visit_refer_office_id + "='" + p.b_visit_refer_office_id
                + "', " + dbObj.check_ears_note + "='" + p.check_ears_note
                + "', " + dbObj.check_ears_remark + "='" + p.check_ears_remark
                + "', " + dbObj.check_ears_record_time + "='" + p.check_ears_record_time
                + "', " + dbObj.check_ears_modify_time + "='" + p.check_ears_modify_time
                + "', " + dbObj.check_ears_cancle_time + "='" + p.check_ears_cancle_time
                + "', " + dbObj.check_ears_staff_record + "='" + p.check_ears_staff_record
                + "', " + dbObj.check_ears_staff_modify + "='" + p.check_ears_staff_modify
                + "', " + dbObj.check_ears_staff_cancle + "='" + p.check_ears_staff_cancle
                + "', " + dbObj.check_ears_active + "='" + p.check_ears_active
                + "' where " + dbObj.pk_field + "= '" + p.getObjectId() +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(CheckEars o) throws Exception {
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
        
        CheckEarsDB v = new CheckEarsDB(theConnectionInf);
        CheckEars vh = new CheckEars();
        vh.pk_field = "555dd";
        vh.t_health_visit_school_id = "55";
        v.insert(vh);
        /*
        Vector vec = v.selectByVisitSchoolID("55");
        if(vec != null){
            
            for(int i=0;i<vec.size();i++){
                vh = (CheckEars)vec.elementAt(i);
            
            
            }
        }
       */
    }
    
}
