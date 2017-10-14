/*
 * CounselDB.java
 *
 * Created on 20 มิถุนายน 2548, 18:25 น.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.pcu.object.Counsel;


/**
 *
 * @author Noom
 */
public class CounselDB {
    public ConnectionInf theConnectionInf;
    public Counsel dbObj;
    final private String idtable = "726";
    
    
    /** Creates a new instance of CounselDB */
    public CounselDB() {
    }
    
    public CounselDB(ConnectionInf db) {
        theConnectionInf=db;
        dbObj = new Counsel();
        initConfig();
    }
    
    public boolean initConfig() {
        dbObj.table = "t_health_counsel";
        dbObj.pk_field = "t_health_counsel_id";
        dbObj.b_health_counsel_type_id ="b_health_counsel_type_id";
        dbObj.counsel_detail = "counsel_detail";
        dbObj.counsel_remark ="counsel_remark";
        dbObj.counsel_record_time = "counsel_record_time";
        dbObj.counsel_modify_time = "counsel_modify_time";
        dbObj.counsel_cancle_time = "counsel_cancle_time";
        dbObj.counsel_staff_record = "counsel_staff_record";
        dbObj.counsel_staff_modify = "counsel_staff_modify";
        dbObj.counsel_staff_cancle = "counsel_staff_cancle";
        dbObj.counsel_active = "counsel_active";
        dbObj.visit_id ="t_visit_id";
        dbObj.patient_id ="t_patient_id";
        dbObj.family_id = "t_health_family_id";
        dbObj.survey_date = "counsel_survey_date";
        return true;
    }
    
    public int insert(Counsel o) throws Exception {
        String sql="";
        Counsel p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"	+ dbObj.b_health_counsel_type_id
                + " ,"	+ dbObj.counsel_detail
                + " ,"	+ dbObj.counsel_remark
                + " ,"	+ dbObj.counsel_record_time
                + " ,"	+ dbObj.counsel_modify_time
                + " ,"	+ dbObj.counsel_cancle_time
                + " ,"	+ dbObj.counsel_staff_record
                + " ,"	+ dbObj.counsel_staff_modify
                + " ,"	+ dbObj.counsel_staff_cancle
                + " ,"	+ dbObj.counsel_active
                + " ,"	+ dbObj.visit_id
                + " ,"	+ dbObj.patient_id
                + " ,"	+ dbObj.family_id
                + " ,"	+ dbObj.survey_date
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.b_health_counsel_type_id
                + "','" + p.counsel_detail
                + "','" + p.counsel_remark
                + "','" + p.counsel_record_time
                + "','" + p.counsel_modify_time
                + "','" + p.counsel_cancle_time
                + "','" + p.counsel_staff_record
                + "','" + p.counsel_staff_modify
                + "','" + p.counsel_staff_cancle
                + "','" + p.counsel_active
                + "','" + p.visit_id
                + "','" + p.patient_id
                + "','" + p.family_id
                + "','" + p.survey_date
                + "')";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectByPatientID(String patient_id) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.patient_id
                + " = '" + patient_id + "'"
                + " and "+dbObj.counsel_active+" = '1'"
                + " order by "+dbObj.counsel_record_time;
        return eQuery(sql);
    }
    
     public Vector selectByFamilyID(String family_id,String type) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.family_id + " = '" + family_id + "'"
                + " and "+dbObj.counsel_active+" = '1'";
        if(!type.equals("")){
            sql += " and "+dbObj.b_health_counsel_type_id + " = '" + type + "'";
        }
        sql += " order by "+dbObj.counsel_record_time;
        return eQuery(sql);
    }
     public Vector selectByFamilyID(String family_id) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.family_id + " = '" + family_id + "'"
                + " and "+dbObj.counsel_active+" = '1'"
                + " order by "+dbObj.counsel_record_time;
        return eQuery(sql);
    }
    
    public Counsel selectByPK(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
         + " where " + dbObj.pk_field
         + " = '" + pk + "'";
         
         Vector v=eQuery(sql);
         if(v.isEmpty())
             return null;
         else
             return (Counsel)v.get(0);
    }
    
      public Vector selectByVisitID(String visit_id) throws Exception {
        String sql="select * from " + dbObj.table
                + " where " + dbObj.visit_id
                + " = '" + visit_id + "'"
                + " and "+dbObj.counsel_active+" = '1'"
                + " order by "+dbObj.counsel_record_time;
        return eQuery(sql);
    }
    
    public Vector selectByVisitIDAndCounselType(String visit_id,String type) throws Exception {
        String sql="select * from " + dbObj.table
                + " where (" + dbObj.visit_id
                + " = '" + visit_id + "')";
        if(!type.equalsIgnoreCase("")){
            sql+= " and ("+dbObj.b_health_counsel_type_id
                    + " = '" + type + "')";
        }
        sql     += " and "+ dbObj.counsel_active+" = '1'"
                + " order by "+dbObj.counsel_record_time;
        return eQuery(sql);
    }
    
    public Vector selectByPatinetIDAndCounselType(String patient_id,String type) throws Exception {
        String sql="select * from " + dbObj.table
                + " where (" + dbObj.patient_id
                + " = '" + patient_id + "')";
        if(!type.equalsIgnoreCase("")){
            sql+= " and ("+dbObj.b_health_counsel_type_id
                    + " = '" + type + "')";
        }
        sql     += " and "+ dbObj.counsel_active+" = '1'"
                + " order by "+dbObj.counsel_record_time;
        return eQuery(sql);
    }
    public Vector selectByFamilyIDAndCounselType(String family_id,String type) throws Exception {
        String sql="select * from " + dbObj.table
                + " where (" + dbObj.family_id
                + " = '" + family_id + "')";
        if(!type.equalsIgnoreCase("")){
            sql+= " and ("+dbObj.b_health_counsel_type_id
                    + " = '" + type + "')";
        }
        sql     += " and "+ dbObj.counsel_active+" = '1'"
                + " order by "+dbObj.counsel_record_time;
        return eQuery(sql);
    }
    /**
     * ค้นหารายการที่มีวันสำรวจเดียวกันและการให้คำปรึกษาซ้ำกัน
     * @param type ชนิดการให้คำปรึกษา
     * @return Vector ของ Counsel
     * @author kingland
     * @date 25/08/2549
     */
    public Vector selectByTypeAndSurverDate(String family,String type,String serveydate) throws Exception
    {
        if(!"".equals(type) && !"".equals(serveydate))
        {
            String sql = "select * from " + dbObj.table
                + " where " + dbObj.b_health_counsel_type_id+"= '" + type + "' " 
                + " and "+dbObj.survey_date+" = '"+serveydate+"'" 
                + " and "+dbObj.family_id+" = '"+family+"'";
            return this.eQuery(sql);
        }
        return null;
    }
    public Vector eQuery(String sql) throws Exception {
        Counsel p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) {
            p = new Counsel();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.b_health_counsel_type_id = rs.getString(dbObj.b_health_counsel_type_id);
            p.counsel_detail = rs.getString(dbObj.counsel_detail);
            p.counsel_remark = rs.getString(dbObj.counsel_remark);
            p.counsel_record_time = rs.getString(dbObj.counsel_record_time);
            p.counsel_modify_time = rs.getString(dbObj.counsel_modify_time);
            p.counsel_cancle_time = rs.getString(dbObj.counsel_cancle_time);
            p.counsel_staff_record = rs.getString(dbObj.counsel_staff_record);
            p.counsel_staff_modify = rs.getString(dbObj.counsel_staff_modify);
            p.counsel_staff_cancle = rs.getString(dbObj.counsel_staff_cancle);
            p.counsel_active = rs.getString(dbObj.counsel_active);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.patient_id = rs.getString(dbObj.patient_id);
            p.family_id = rs.getString(dbObj.family_id);
            p.survey_date = rs.getString(dbObj.survey_date);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public int update(Counsel o) throws Exception {
        String sql="update " + dbObj.table + " set ";
        Counsel p=o;
        sql += dbObj.b_health_counsel_type_id + "='" + p.b_health_counsel_type_id
                + "', " + dbObj.counsel_detail + "='" + p.counsel_detail
                + "', " + dbObj.counsel_remark + "='" + p.counsel_remark
                + "', " + dbObj.counsel_record_time + "='" + p.counsel_record_time
                + "', " + dbObj.counsel_modify_time + "='" + p.counsel_modify_time
                + "', " + dbObj.counsel_cancle_time + "='" + p.counsel_cancle_time
                + "', " + dbObj.counsel_staff_record + "='" + p.counsel_staff_record
                + "', " + dbObj.counsel_staff_modify + "='" + p.counsel_staff_modify
                + "', " + dbObj.counsel_staff_cancle + "='" + p.counsel_staff_cancle
                + "', " + dbObj.counsel_active + "='" + p.counsel_active
                + "', " + dbObj.visit_id + "='" + p.visit_id
                + "', " + dbObj.patient_id + "='" + p.patient_id
                + "', " + dbObj.family_id + "='" + p.family_id
                + "', " + dbObj.survey_date + "='" + p.survey_date
                + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(Counsel o) throws Exception {
        String sql="delete from " + dbObj.table
                + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
/////////////////////////////////////////////////////////////////////////////    
    public int updateFidByFid(String family_id,String family_from) throws Exception
    {
        String sql="update " + dbObj.table + " set " 
                + dbObj.family_id + "='" + family_id + "',"
                + dbObj.counsel_staff_cancle + "="+ dbObj.counsel_staff_cancle + "||'-'||'" + family_from + "'"
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
