/*
 * VisitSchoolDB.java
 *
 * Created on 20 มิถุนายน 2548, 18:25 น.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.pcu.object.VisitSchool;


/**
 *
 * @author Noom
 */
public class VisitSchoolDB {
    public ConnectionInf theConnectionInf;
    public VisitSchool dbObj;
    final private String idtable = "731";
    
    
    /** Creates a new instance of VisitSchoolDB */
    public VisitSchoolDB() {
    }
    
    public VisitSchoolDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new VisitSchool();
        initConfig();
    }
    
    public boolean initConfig() {
        dbObj.table = "t_health_visit_school";
        dbObj.pk_field = "t_health_visit_school_id";
        dbObj.t_health_school_id ="t_health_school_id";
        dbObj.b_school_class_id = "b_school_class_id";
        dbObj.visit_school_room ="visit_school_room";
        dbObj.f_health_school_service_type_id ="f_health_school_service_type_id";
        dbObj.visit_school_term = "visit_school_term";
        dbObj.visit_school_other_service = "visit_school_other_service";
        dbObj.visit_school_service_date = "visit_school_service_date";
        dbObj.visit_school_record_time = "visit_school_record_time";
        dbObj.visit_school_modify_time = "visit_school_modify_time";
        dbObj.visit_school_cancle_time = "visit_school_cancle_time";
        dbObj.visit_school_staff_record = "visit_school_staff_record";
        dbObj.visit_school_staff_modify = "visit_school_staff_modify";
        dbObj.visit_school_staff_cancle = "visit_school_staff_cancle";
        dbObj.visit_school_active ="visit_school_active";
        
        return true;
    }
    
    public int insert(VisitSchool o) throws Exception {
        String sql="";
        VisitSchool p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"	+ dbObj.t_health_school_id
                + " ,"	+ dbObj.b_school_class_id
                + " ,"	+ dbObj.visit_school_room
                + " ,"	+ dbObj.f_health_school_service_type_id
                + " ,"	+ dbObj.visit_school_term
                + " ,"	+ dbObj.visit_school_other_service
                + " ,"	+ dbObj.visit_school_service_date
                + " ,"	+ dbObj.visit_school_record_time
                + " ,"	+ dbObj.visit_school_modify_time
                + " ,"	+ dbObj.visit_school_cancle_time
                + " ,"	+ dbObj.visit_school_staff_record
                + " ,"	+ dbObj.visit_school_staff_modify
                + " ,"	+ dbObj.visit_school_staff_cancle
                + " ,"	+ dbObj.visit_school_active
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.t_health_school_id
                + "','" + p.b_school_class_id
                + "','" + p.visit_school_room
                + "','" + p.f_health_school_service_type_id
                + "','" + p.visit_school_term
                + "','" + p.visit_school_other_service
                + "','" + p.visit_school_service_date
                + "','" + p.visit_school_record_time
                + "','" + p.visit_school_modify_time
                + "','" + p.visit_school_cancle_time
                + "','" + p.visit_school_staff_record
                + "','" + p.visit_school_staff_modify
                + "','" + p.visit_school_staff_cancle
                + "','" + p.visit_school_active
                + "')";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectAll() throws Exception {
        String sql="select * from " + dbObj.table
                + " where "+dbObj.visit_school_active+" = '1'"
                + " order by "+dbObj.visit_school_service_date;
        Vector v =eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
     public Vector selectByPK(String pk) throws Exception{
        Vector vVisitSchool = new Vector();
        String sql ="select * from "+ dbObj.table 
        + " where " + dbObj.pk_field + "='" + pk +"'" 
        + " and "+dbObj.visit_school_active + " = '1' ";
        vVisitSchool = eQuery(sql);
        if(vVisitSchool.size()==0)
            return null;
        else
            return vVisitSchool;        
    }
    
    public Vector selectBySchoolID(String school_id) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.t_health_school_id
                + " = '" + school_id + "'"
                + " and "+dbObj.visit_school_active+" = '1'"
                + " order by "+dbObj.visit_school_service_date;
        Vector v =eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    public Vector selectBySchoolClassRoomTerm(String school_id,String school_class,String school_room,String school_term) throws Exception {
        String sql="select * from " + dbObj.table
                + " where (" + dbObj.t_health_school_id
                + " = '" + school_id + "')"
                + " and ("+dbObj.b_school_class_id+ " = '" + school_class + "')"
                + " and ("+dbObj.visit_school_room+ " = '" + school_room + "')"
                + " and ("+dbObj.visit_school_term+ " = '" + school_term + "')"
                + " and ("+dbObj.visit_school_active+" = '1')"
                + " order by "+dbObj.visit_school_service_date;
        Vector v =eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
      public Vector selectBySchoolClassRoomTermService(String school_id,String school_class,String school_room,String school_term,String school_service,String school_other) throws Exception {
        String sql="select * from " + dbObj.table
                + " where (" + dbObj.t_health_school_id
                + " = '" + school_id + "')"
                + " and ("+dbObj.b_school_class_id+ " = '" + school_class + "')"
                + " and ("+dbObj.visit_school_room+ " = '" + school_room + "')"
                + " and ("+dbObj.visit_school_term+ " = '" + school_term + "')"
                + " and ("+dbObj.f_health_school_service_type_id+ " = '" + school_service + "')"
                + " and ("+dbObj.visit_school_other_service+ " = '" + school_other + "')"
                + " and ("+dbObj.visit_school_active+" = '1')"
                + " order by "+dbObj.visit_school_service_date;
        Vector v =eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
       public Vector selectBySchoolClassRoomTermService(String school_id,String school_class,String school_room,String school_term,String school_service,String school_other,String school_service_date) throws Exception {
        String sql="select * from " + dbObj.table
                + " where (" + dbObj.t_health_school_id
                + " = '" + school_id + "')"
                + " and ("+dbObj.b_school_class_id+ " = '" + school_class + "')"
                + " and ("+dbObj.visit_school_room+ " = '" + school_room + "')"
                + " and ("+dbObj.visit_school_term+ " = '" + school_term + "')"
                + " and ("+dbObj.f_health_school_service_type_id+ " = '" + school_service + "')"
                + " and ("+dbObj.visit_school_other_service+ " = '" + school_other + "')"
                + " and ("+dbObj.visit_school_service_date+ " = '" + school_service_date + "')"
                + " and ("+dbObj.visit_school_active+" = '1')"
                + " order by "+dbObj.visit_school_service_date;
        Vector v =eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
    }
      
    public Vector selectBySchoolID(Vector vSchoolID) throws Exception {
        if(vSchoolID != null){
           String sqlTemp ="";
           int size = vSchoolID.size();
           for(int i=0;i<size;i++){
              sqlTemp += dbObj.t_health_school_id+" = '"+(String)vSchoolID.elementAt(i)+"'";
              if(i != size-1){
                  sqlTemp += " or ";
              }
           }
         String sql="select * from " + dbObj.table
                + " where (" + sqlTemp + ")"
                + " and "+dbObj.visit_school_active+" = '1'"
                + " order by "+dbObj.visit_school_service_date;
        Vector v =eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return v;
        }
        return null;
    }
    
    public Vector eQuery(String sql) throws Exception {
        VisitSchool p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) {
            p = new VisitSchool();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.t_health_school_id = rs.getString(dbObj.t_health_school_id);
            p.b_school_class_id = rs.getString(dbObj.b_school_class_id);
            p.visit_school_room = rs.getString(dbObj.visit_school_room);
            p.f_health_school_service_type_id = rs.getString(dbObj.f_health_school_service_type_id);
            p.visit_school_term = rs.getString(dbObj.visit_school_term);
            p.visit_school_other_service = rs.getString(dbObj.visit_school_other_service);
            p.visit_school_service_date = rs.getString(dbObj.visit_school_service_date);
            p.visit_school_record_time = rs.getString(dbObj.visit_school_record_time);
            p.visit_school_modify_time = rs.getString(dbObj.visit_school_modify_time);
            p.visit_school_cancle_time = rs.getString(dbObj.visit_school_cancle_time);
            p.visit_school_staff_record = rs.getString(dbObj.visit_school_staff_record);
            p.visit_school_staff_modify = rs.getString(dbObj.visit_school_staff_modify);
            p.visit_school_staff_cancle = rs.getString(dbObj.visit_school_staff_cancle);
            p.visit_school_active = rs.getString(dbObj.visit_school_active);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public int update(VisitSchool o) throws Exception {
        String sql="update " + dbObj.table + " set ";
        VisitSchool p=o;
        sql += dbObj.t_health_school_id + "='" + p.t_health_school_id
                + "', " + dbObj.b_school_class_id + "='" + p.b_school_class_id
                + "', " + dbObj.visit_school_room + "='" + p.visit_school_room
                + "', " + dbObj.f_health_school_service_type_id + "='" + p.f_health_school_service_type_id
                + "', " + dbObj.visit_school_term + "='" + p.visit_school_term
                + "', " + dbObj.visit_school_other_service + "='" + p.visit_school_other_service
                + "', " + dbObj.visit_school_service_date + "='" + p.visit_school_service_date
                + "', " + dbObj.visit_school_record_time + "='" + p.visit_school_record_time
                + "', " + dbObj.visit_school_modify_time + "='" + p.visit_school_modify_time
                + "', " + dbObj.visit_school_cancle_time + "='" + p.visit_school_cancle_time
                + "', " + dbObj.visit_school_staff_record + "='" + p.visit_school_staff_record
                + "', " + dbObj.visit_school_staff_modify + "='" + p.visit_school_staff_modify
                + "', " + dbObj.visit_school_staff_cancle + "='" + p.visit_school_staff_cancle
                + "', " + dbObj.visit_school_active + "='" + p.visit_school_active
                + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(VisitSchool o) throws Exception {
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
        
        VisitSchoolDB v = new VisitSchoolDB(theConnectionInf);
        VisitSchool vh = new VisitSchool();
        //vh.pk_field = "555dd";
        //vh.visit_school_room = "666";
        //v.insert(vh);
        /*
        Vector a = new Vector();
        a.addElement("704888885492563075");
        a.addElement("704888886355374096");
        */
        Vector vec = v.selectBySchoolClassRoomTerm("704888886415501575","733000007839240943","1","5555");
        if(vec != null){
            
            for(int i=0;i<vec.size();i++){
                vh = (VisitSchool)vec.elementAt(i);
                //
                
            }
        }
       
    }
    
}
