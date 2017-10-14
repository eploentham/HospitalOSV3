/*
 * CheckHealthDB.java
 *
 * Created on 8 มีนาคม 2549, 13:56 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.objdb.objdbclass;
import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
import com.hospital_os.utility.*;
/**
 *
 * @author kingland
 */
public class CheckHealthDB {
    public ConnectionInf theConnectionInf;
    public CheckHealth dbObj;
    final private String idtable = "780";
    /** Creates a new instance of CheckHealthDB */
    public CheckHealthDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new CheckHealth();        
        initConfig(); 
    }
     /**
     * 
     * @param  
     * @return 
     * @author kingland
     * @date 08-03-2549
     */
    public boolean initConfig()
    {
        dbObj.table = "t_health_check_healthy";        
        dbObj.pk_field = "t_health_check_healthy_id";          
        dbObj.active = "health_check_healthy_active";
        dbObj.age_end = "health_check_healthy_age_end";
        dbObj.age_start = "health_check_health_age_start";
        dbObj.age_survey_id = "b_health_age_survey_id";
        dbObj.cancle_datetime = "health_check_healthy_cancle_date_time";
        dbObj.check_date = "health_check_healthy_date";
        dbObj.family_age = "health_family_age";
        dbObj.family_id = "t_health_family_id";
        dbObj.is_lifetime = "health_age_survey_is_lifetime";
        dbObj.modify_datetime = "health_check_healthy_modify_date_time";
        dbObj.patient_id = "t_patient_id";
        dbObj.record_datetime = "health_check_healthy_record_date_time";
        dbObj.result = "health_check_healthy_result";
        dbObj.staff_cancle = "health_check_healthy_staff_cancle";
        dbObj.staff_modify = "health_check_healthy_staff_modify";
        dbObj.staff_record = "health_check_healthy_staff_record";
        dbObj.survey_id = "b_health_survey_id";
        dbObj.visit_id = "t_visit_id";
        return true;
    }
    /**
     * 
     * @param  
     * @return 
     * @author kingland
     * @date 08-03-2549
     */
     public Vector eQuery(String sql) throws Exception
    {
        CheckHealth p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new CheckHealth();
            p.setObjectId(rs.getString(dbObj.pk_field));            
            p.active = rs.getString(dbObj.active);            
            p.age_end = rs.getString(dbObj.age_end);
            p.age_start = rs.getString(dbObj.age_start);
            p.survey_id = rs.getString(dbObj.survey_id);
            p.age_survey_id = rs.getString(dbObj.age_survey_id);
            p.cancle_datetime = rs.getString(dbObj.cancle_datetime);
            p.check_date = rs.getString(dbObj.check_date);
            p.family_age = rs.getString(dbObj.family_age);
            p.family_id = rs.getString(dbObj.family_id);
            p.is_lifetime = rs.getString(dbObj.is_lifetime);            
            p.modify_datetime = rs.getString(dbObj.modify_datetime);       
            p.patient_id = rs.getString(dbObj.patient_id);
            p.record_datetime = rs.getString(dbObj.record_datetime);
            p.result = rs.getString(dbObj.result);
            p.staff_cancle = rs.getString(dbObj.staff_cancle);
            p.staff_modify = rs.getString(dbObj.staff_modify);
            p.staff_record = rs.getString(dbObj.staff_record);
            p.survey_id = rs.getString(dbObj.survey_id);
            p.visit_id = rs.getString(dbObj.visit_id);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /**
     * เพิ่มข้อมูลเข้าไปในตาราง
     * @param  CheckHealth
     * @return 
     * @author kingland
     * @date 06-03-2549
     */
    public int insert(CheckHealth o) throws Exception 
    {
        String sql="";
        CheckHealth p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"	+ dbObj.family_id                
                + " ,"	+ dbObj.family_age
                + " ,"	+ dbObj.patient_id
                + " ,"	+ dbObj.visit_id
                + " ,"	+ dbObj.check_date
                + " ,"	+ dbObj.record_datetime
                + " ,"	+ dbObj.modify_datetime
                + " ,"	+ dbObj.cancle_datetime
                + " ,"	+ dbObj.staff_record
                + " ,"	+ dbObj.staff_modify
                + " ,"	+ dbObj.staff_cancle
                + " ,"	+ dbObj.active
                + " ,"	+ dbObj.survey_id
                + " ,"	+ dbObj.age_survey_id
                + " ,"	+ dbObj.result
                + " ,"	+ dbObj.age_start
                + " ,"	+ dbObj.age_end
                + " ,"	+ dbObj.is_lifetime
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.family_id                
                + "','" + p.family_age
                + "','" + p.patient_id
                + "','" + p.visit_id
                + "','" + p.check_date
                + "','" + p.record_datetime
                + "','" + p.modify_datetime
                + "','" + p.cancle_datetime
                + "','" + p.staff_record
                + "','" + p.staff_modify
                + "','" + p.staff_cancle
                + "','" + p.active
                + "','" + p.survey_id
                + "','" + p.age_survey_id
                + "','" + p.result
                + "','" + p.age_start
                + "','" + p.age_end
                + "','" + p.is_lifetime
                + "')";
        return theConnectionInf.eUpdate(sql);
    }
    /**
     * อับเดตข้อมูลในตาราง
     * @param  CheckHealth
     * @return 
     * @author kingland
     * @date 06-03-2549
     */
     public int update(CheckHealth o) throws Exception 
     {
        String sql="update " + dbObj.table + " set ";
        CheckHealth p=o;
        String field = ""
                + "', " + dbObj.family_id + "='" + p.family_id                
                + "', " + dbObj.family_age + "='" + p.family_age
                + "', " + dbObj.patient_id + "='" + p.patient_id
                + "', " + dbObj.visit_id + "='" + p.visit_id
                + "', " + dbObj.check_date + "='" + p.check_date
                + "', " + dbObj.record_datetime + "='" + p.record_datetime
                + "', " + dbObj.modify_datetime + "='" + p.modify_datetime
                + "', " + dbObj.cancle_datetime + "='" + p.cancle_datetime
                + "', " + dbObj.staff_record + "='" + p.staff_record
                + "', " + dbObj.staff_modify + "='" + p.staff_modify
                + "', " + dbObj.staff_cancle + "='" + p.staff_cancle
                + "', " + dbObj.active + "='" + p.active
                + "', " + dbObj.survey_id + "='" + p.survey_id
                + "', " + dbObj.age_survey_id + "='" + p.age_survey_id
                + "', " + dbObj.result + "='" + p.result
                + "', " + dbObj.age_start + "='" + p.age_start
                + "', " + dbObj.age_end + "='" + p.age_end
                + "', " + dbObj.is_lifetime + "='" + p.is_lifetime
                + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql + field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    /**
     * อับเดตข้อมูลถ้ามีการใส่ข้อมูลซ้ำกันในวันเดียวกัน โดยไม่มี Visit
     * @param  CheckHealth
     * @return 
     * @author kingland
     * @date 06-03-2549
     */
     public int updateDateSurveyID(CheckHealth o) throws Exception 
     {
        String sql="update " + dbObj.table + " set ";
        CheckHealth p=o;
        String field = ""
                + "', " + dbObj.family_id + "='" + p.family_id                
                + "', " + dbObj.family_age + "='" + p.family_age
                + "', " + dbObj.patient_id + "='" + p.patient_id
                + "', " + dbObj.visit_id + "='" + p.visit_id
                + "', " + dbObj.check_date + "='" + p.check_date
                + "', " + dbObj.record_datetime + "='" + p.record_datetime
                + "', " + dbObj.modify_datetime + "='" + p.modify_datetime
                + "', " + dbObj.cancle_datetime + "='" + p.cancle_datetime
                + "', " + dbObj.staff_record + "='" + p.staff_record
                + "', " + dbObj.staff_modify + "='" + p.staff_modify
                + "', " + dbObj.staff_cancle + "='" + p.staff_cancle
                + "', " + dbObj.active + "='" + p.active
                + "', " + dbObj.survey_id + "='" + p.survey_id
                + "', " + dbObj.age_survey_id + "='" + p.age_survey_id
                + "', " + dbObj.result + "='" + p.result
                + "', " + dbObj.age_start + "='" + p.age_start
                + "', " + dbObj.age_end + "='" + p.age_end
                + "', " + dbObj.is_lifetime + "='" + p.is_lifetime
                + "' where " + dbObj.check_date + "='" + p.check_date +"'"
                + " and " + dbObj.survey_id + "='" + p.survey_id +"'";
        sql = sql + field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    /**
     * อับเดตข้อมูลถ้ามีการใส่ข้อมูลซ้ำกันในวันเดียวกัน ใน Visit เดียวกัน
     * @param  CheckHealth
     * @return 
     * @author kingland
     * @date 06-03-2549
     */
      public int updateVisitID(CheckHealth o) throws Exception 
     {
        String sql="update " + dbObj.table + " set ";
        CheckHealth p=o;
        String field = ""
                + "', " + dbObj.family_id + "='" + p.family_id                
                + "', " + dbObj.family_age + "='" + p.family_age
                + "', " + dbObj.patient_id + "='" + p.patient_id
                + "', " + dbObj.visit_id + "='" + p.visit_id
                + "', " + dbObj.check_date + "='" + p.check_date
                + "', " + dbObj.record_datetime + "='" + p.record_datetime
                + "', " + dbObj.modify_datetime + "='" + p.modify_datetime
                + "', " + dbObj.cancle_datetime + "='" + p.cancle_datetime
                + "', " + dbObj.staff_record + "='" + p.staff_record
                + "', " + dbObj.staff_modify + "='" + p.staff_modify
                + "', " + dbObj.staff_cancle + "='" + p.staff_cancle
                + "', " + dbObj.active + "='" + p.active
                + "', " + dbObj.survey_id + "='" + p.survey_id
                + "', " + dbObj.age_survey_id + "='" + p.age_survey_id
                + "', " + dbObj.result + "='" + p.result
                + "', " + dbObj.age_start + "='" + p.age_start
                + "', " + dbObj.age_end + "='" + p.age_end
                + "', " + dbObj.is_lifetime + "='" + p.is_lifetime
                + "' where " + dbObj.visit_id + "='" + p.visit_id +"'"
                + " and " + dbObj.survey_id + "='" + p.survey_id +"'";
        sql = sql + field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
     /**
     * ลบข้อมูลในตาราง
     * @param  
     * @return 
     * @author kingland
     * @date 06-03-2549
     */
    public int delete(CheckHealth o) throws Exception 
    {
        String sql="delete from " + dbObj.table
                + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    /**
     * ค้นหาข้อมูลโดยใช้ Primary key
     * @param  
     * @return 
     * @author kingland
     * @date 11-03-2549
     */
    public CheckHealth selectByPk(String pk) throws Exception
    {
        Vector v = null;
        String sql = "select * from "+dbObj.table
                +" where "+dbObj.pk_field +" = '"+pk+"'";
        v = eQuery(sql);
        return (CheckHealth)v.get(0);
    }
    /**
     * ค้นหาข้อมูลโดยใช้วันที่
     * @param  
     * @return 
     * @author kingland
     * @date 11-03-2549
     */
    public Vector selectByDate(String familyid,String dateStart ,String dateEnd) throws Exception
    {
        Vector v = null;
        String sql = "select * from "+dbObj.table
                +" where "+dbObj.family_id+" = '"+familyid+"'"
                +" and "+dbObj.check_date+" >= '"+dateStart+"'";
        if(!"".equals(dateEnd) || dateEnd == null)
        {
            sql = sql+" and "+dbObj.check_date+" <= '"+dateEnd+"'";
        }
        sql = sql+" and "+dbObj.active+" = '1'";
        v = this.eQuery(sql);
        return v;
    }
/////////////////////////////////////////////////////////////////////////////    
    public int updateFidByFid(String family_id,String family_from) throws Exception
    {
        String sql="update " + dbObj.table + " set " 
                + dbObj.family_id + "='" + family_id + "',"
                + dbObj.staff_cancle + "="+ dbObj.staff_cancle + "||'-'||'" + family_from + "'"
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
