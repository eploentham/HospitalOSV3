/*
 * EpiDB.java
 *
 * Created on 24 ÁÔ¶Ø¹ÒÂ¹ 2548, 10:40 ¹.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
import com.hospital_os.utility.*;
/**
 *
 * @author Administrator
 */
public class EpiDB {
    
    /** Creates a new instance of EpiDB */
    public EpiDB() {
    }
    public ConnectionInf theConnectionInf;
    
    public Epi dbObj;
    final private String idtable = "724";
    
    public EpiDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Epi();
        initConfig(); 
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_epi";        
        dbObj.pk_field = "t_health_epi_id";        
        dbObj.patient_id = "t_patient_id";
        dbObj.epi_vn = "health_epi_vn";
        dbObj.office_id = "b_visit_office_id";
        dbObj.visit_id = "t_visit_id";
        dbObj.epi_hn = "health_epi_hn";
        dbObj.epi_nextapp = "health_epi_nextapp";
        dbObj.epi_notice = "health_epi_notice";
        dbObj.record_date_time = "record_date_time";
        dbObj.modify_date_time = "modify_date_time";
        dbObj.cancel_date_time = "cancel_date_time";
        dbObj.staff_record = "health_epi_staff_record";
        dbObj.staff_modify = "health_epi_staff_modify";
        dbObj.staff_cancel = "health_epi_staff_cancel";
        dbObj.active = "health_epi_active";
        dbObj.family_id= "t_health_family_id";
        dbObj.survey_date = "epi_survey_date";
                
        return true;
    }
    
    public int insert(Epi o) throws Exception
    {
        String sql="";
        Epi p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field        
        + " ,"  + dbObj.patient_id
        + " ,"  + dbObj.epi_vn
        + " ,"  + dbObj.office_id
        + " ,"  + dbObj.visit_id
        + " ,"  + dbObj.epi_hn
        + " ,"  + dbObj.epi_nextapp
        + " ,"  + dbObj.epi_notice
        + " ,"  + dbObj.record_date_time
        + " ,"  + dbObj.modify_date_time
        + " ,"  + dbObj.cancel_date_time
        + " ,"  + dbObj.staff_record
        + " ,"  + dbObj.staff_modify
        + " ,"  + dbObj.staff_cancel        
        + " ,"  + dbObj.active
        + " ,"  + dbObj.family_id
        + " ,"  + dbObj.survey_date
        + " ) values ('"
        + p.getObjectId()
        
        + "','" +p.patient_id
        + "','" +p.epi_vn
        + "','" +p.office_id
        + "','" +p.visit_id
        + "','" +p.epi_hn
        + "','" +p.epi_nextapp
        + "','" +p.epi_notice
        + "','" +p.record_date_time
        + "','" +p.modify_date_time
        + "','" +p.cancel_date_time
        + "','" +p.staff_record
        + "','" +p.staff_modify
        + "','" +p.staff_cancel        
        + "','" +p.active
        + "','" +p.family_id
        + "','" +p.survey_date
        + "')";
        
        return theConnectionInf.eUpdate(sql);
    }
    public int update(Epi o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Epi p=o;
        String field =""        
        + "', " + dbObj.patient_id+ "='" + p.patient_id
        + "', " + dbObj.epi_vn+ "='" + p.epi_vn
        + "', " + dbObj.office_id+ "='" + p.office_id
        + "', " + dbObj.visit_id+ "='" + p.visit_id
        + "', " + dbObj.epi_hn+ "='" + p.epi_hn
        + "', " + dbObj.epi_nextapp+ "='" + p.epi_nextapp
        + "', " + dbObj.epi_notice+ "='" + p.epi_notice
        + "', " + dbObj.record_date_time+ "='" + p.record_date_time
        + "', " + dbObj.modify_date_time+ "='" + p.modify_date_time
        + "', " + dbObj.cancel_date_time+ "='" + p.cancel_date_time
        + "', " + dbObj.staff_record+ "='" + p.staff_record
        + "', " + dbObj.staff_modify+ "='" + p.staff_modify
        + "', " + dbObj.staff_cancel+ "='" + p.staff_cancel
        + "', " + dbObj.active+ "='" + p.active        
        + "', " + dbObj.family_id+ "='" + p.family_id
        + "', " + dbObj.survey_date+ "='" + p.survey_date
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
     public Vector selectByPatientID(String patient_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.patient_id
        + " = '" + patient_id + "'"
        + " and " + dbObj.active + " = '1' "
        + " order by " + dbObj.staff_record;
        return eQuery(sql);
    }
     
     
    public Vector selectByFamilyID(String family_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.family_id
        + " = '" + family_id + "'"
        + " and " + dbObj.active + " = '1' "
        + " order by " + dbObj.staff_record;
        return eQuery(sql);
    }
    
    public int delete(Epi o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
       
    
    public Vector eQuery(String sql) throws Exception
    {
        Epi p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Epi();
            p.setObjectId(rs.getString(dbObj.pk_field));            
            p.patient_id = rs.getString(dbObj.patient_id);
            p.epi_vn = rs.getString(dbObj.epi_vn);
            p.office_id = rs.getString(dbObj.office_id);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.epi_hn = rs.getString(dbObj.epi_hn);
            p.epi_nextapp = rs.getString(dbObj.epi_nextapp);
            p.epi_notice = rs.getString(dbObj.epi_notice);
            p.record_date_time = rs.getString(dbObj.record_date_time);
            p.modify_date_time = rs.getString(dbObj.modify_date_time);
            p.cancel_date_time = rs.getString(dbObj.cancel_date_time);
            p.staff_record = rs.getString(dbObj.staff_record);
            p.staff_modify = rs.getString(dbObj.staff_modify);
            p.staff_cancel = rs.getString(dbObj.staff_cancel);           
            p.active = rs.getString(dbObj.active);
            p.family_id = rs.getString(dbObj.family_id);
            p.survey_date = rs.getString(dbObj.survey_date);
            list.add(p);
        }
        rs.close();
        return list;
    }
/////////////////////////////////////////////////////////////////////////////    
    public int updateFidByFid(String family_id,String family_from) throws Exception
    {
        String sql="update " + dbObj.table + " set " 
                + dbObj.family_id + "='" + family_id+ "',"
                + dbObj.staff_cancel + "="+ dbObj.staff_cancel + "||'-'||'" + family_from + "'"
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
