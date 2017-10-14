/*
 * AgeSurveyDB.java
 *
 * Created on 6 มีนาคม 2549, 14:34 น.
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
public class AgeSurveyDB {
    
    public ConnectionInf theConnectionInf;
    public AgeSurvey dbObj;
    public Survey dbObj2;
    final private String idtable = "781";
    /** Creates a new instance of AgeSurveyDB */    
    
     public AgeSurveyDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new AgeSurvey();
        dbObj2 = new Survey();
        initConfig(); 
    }
     /**
     * 
     * @param  
     * @return 
     * @author kingland
     * @date 06-03-2549
     */
    public boolean initConfig()
    {
        dbObj.table = "b_health_age_survey";        
        dbObj.pk_field = "b_health_age_survey_id";
        dbObj.survey_active = "health_age_survey_active";
        dbObj.survey_cancle_datetime = "health_age_survey_cancle_date_time";
        dbObj.survey_end = "health_age_survey_end";
        dbObj.survey_id = "b_health_survey_id";
        dbObj.survey_modify_datetime = "health_age_survey_modify_date_time";
        dbObj.survey_record_datetime = "health_age_survey_record_date_time";
        dbObj.survey_staff_cancle = "health_age_survey_staff_cancle";
        dbObj.survey_staff_modify = "health_age_survey_staff_modify";
        dbObj.survey_staff_record = "health_age_survey_staff_record";
        dbObj.survey_start = "health_age_survey_start";
        dbObj.survey_is_lifetime = "health_age_survey_is_lifetime";
        
        dbObj2.table = "b_health_survey";        
        dbObj2.pk_field = "b_health_survey_id";        
        dbObj2.survey_description = "health_survey_description";
        dbObj2.survey_active = "health_survey_active";
        return true;
    }
    /**
     * 
     * @param  
     * @return 
     * @author kingland
     * @date 06-03-2549
     */
     public Vector eQuery(String sql) throws Exception
    {
        AgeSurvey p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new AgeSurvey();
            p.setObjectId(rs.getString(dbObj.pk_field));            
            p.survey_active = rs.getString(dbObj.survey_active);            
            p.survey_cancle_datetime = rs.getString(dbObj.survey_cancle_datetime);
            p.survey_end = rs.getString(dbObj.survey_end);
            p.survey_id = rs.getString(dbObj.survey_id);
            p.survey_modify_datetime = rs.getString(dbObj.survey_modify_datetime);
            p.survey_record_datetime = rs.getString(dbObj.survey_record_datetime);
            p.survey_staff_cancle = rs.getString(dbObj.survey_staff_cancle);
            p.survey_staff_modify = rs.getString(dbObj.survey_staff_modify);
            p.survey_staff_record = rs.getString(dbObj.survey_staff_record);
            p.survey_start = rs.getString(dbObj.survey_start);            
            p.survey_description = rs.getString(dbObj2.survey_description);       
            p.survey_is_lifetime = rs.getString(dbObj.survey_is_lifetime);       
            list.add(p);
        }
        rs.close();
        return list;
    }
     /**
     * ลบข้อมูลในตาราง
     * @param  
     * @return 
     * @author kingland
     * @date 06-03-2549
     */
    public int delete(AgeSurvey o) throws Exception 
    {
        String sql="delete from " + dbObj.table
                + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    /**
     * เพิ่มข้อมูลเข้าไปในตาราง
     * @param  
     * @return 
     * @author kingland
     * @date 06-03-2549
     */
    public int insert(AgeSurvey o) throws Exception 
    {
        String sql="";
        AgeSurvey p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"	+ dbObj.survey_active                
                + " ,"	+ dbObj.survey_cancle_datetime
                + " ,"	+ dbObj.survey_end
                + " ,"	+ dbObj.survey_id
                + " ,"	+ dbObj.survey_modify_datetime
                + " ,"	+ dbObj.survey_record_datetime
                + " ,"	+ dbObj.survey_staff_cancle
                + " ,"	+ dbObj.survey_staff_modify
                + " ,"	+ dbObj.survey_staff_record
                + " ,"	+ dbObj.survey_start
                + " ,"	+ dbObj.survey_is_lifetime
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.survey_active                
                + "','" + p.survey_cancle_datetime
                + "','" + p.survey_end
                + "','" + p.survey_id
                + "','" + p.survey_modify_datetime
                + "','" + p.survey_record_datetime
                + "','" + p.survey_staff_cancle
                + "','" + p.survey_staff_modify
                + "','" + p.survey_staff_record
                + "','" + p.survey_start
                + "','" + p.survey_is_lifetime
                + "')";
        return theConnectionInf.eUpdate(sql);
    }
    /**
     * อับเดตข้อมูลในตาราง
     * @param  
     * @return 
     * @author kingland
     * @date 06-03-2549
     */
     public int update(AgeSurvey o) throws Exception 
     {
        String sql="update " + dbObj.table + " set ";
        AgeSurvey p=o;
        String field = ""
                + "', " + dbObj.survey_active + "='" + p.survey_active                
                + "', " + dbObj.survey_cancle_datetime + "='" + p.survey_cancle_datetime
                + "', " + dbObj.survey_end + "='" + p.survey_end
                + "', " + dbObj.survey_id + "='" + p.survey_id
                + "', " + dbObj.survey_modify_datetime + "='" + p.survey_modify_datetime
                + "', " + dbObj.survey_record_datetime + "='" + p.survey_record_datetime
                + "', " + dbObj.survey_staff_cancle + "='" + p.survey_staff_cancle
                + "', " + dbObj.survey_staff_modify + "='" + p.survey_staff_modify
                + "', " + dbObj.survey_staff_record + "='" + p.survey_staff_record
                + "', " + dbObj.survey_start + "='" + p.survey_start
                + "', " + dbObj.survey_is_lifetime + "='" + p.survey_is_lifetime
                + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql + field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
     
    /**
     * ค้นหาข้อมูลทั้งหมดที่Active
     * @param  
     * @return 
     * @author kingland
     * @date 06-03-2549
     */
    public Vector selectAll() throws Exception
    {
        Vector v = null;
        String sql = "select * from "+dbObj.table+","+dbObj2.table
                +" where " +dbObj.table+"."+dbObj.survey_id+" = "+dbObj2.table+"."+dbObj2.pk_field
                +" and "+dbObj.survey_active+" = '1'" 
                +" order by "+dbObj.table+"."+dbObj.survey_id+","+dbObj.table+"."+dbObj.survey_start ;
        v = eQuery(sql);
        return v;
    }
    /**
     * ค้นหาข้อมูลทั้งหมดที่ไม่Active
     * @param  
     * @return 
     * @author kingland
     * @date 22-02-2549
     */
    public Vector selectAllNotActive() throws Exception
    {
        Vector v = null;
        String sql = "select * from "+dbObj.table+","+dbObj2.table
                +" where " +dbObj.table+"."+dbObj.survey_id+" = "+dbObj2.table+"."+dbObj2.pk_field
                +" and "+dbObj.survey_active+" = '0'"
                +" order by "+dbObj.table+"."+dbObj.survey_id+","+dbObj.table+"."+dbObj.survey_start;
        v = eQuery(sql);
        return v;
    }
    /**
     * ค้นหาข้อมูลโดยใช้คำค้นหา
     * @param  
     * @return 
     * @author kingland
     * @date 22-02-2549
     */
    public Vector selectByName(String searchword) throws Exception
    {
        Vector v = null;
        String sql = "select * from "+dbObj.table+","+dbObj2.table
                +" where " +dbObj.table+"."+dbObj.survey_id+" = "+dbObj2.table+"."+dbObj2.pk_field
                +" and ("+dbObj.pk_field+" like '%"+searchword+"%'"
                +" or "+dbObj2.survey_description+" like '%"+searchword+"%')"
                +" and "+dbObj.survey_active+" = '1'"
                +" order by "+dbObj.table+"."+dbObj.survey_id+","+dbObj.table+"."+dbObj.survey_start;
        v = eQuery(sql);
        return v;
    }
    /**
     * ค้นหาข้อมูลที่ไม่ Active โดยใช้คำค้นหา
     * @param  
     * @return 
     * @author kingland
     * @date 22-02-2549
     */
    public Vector selectNotActiveByName(String searchword) throws Exception
    {
        Vector v = null;
        String sql = "select * from "+dbObj.table+","+dbObj2.table
                +" where " +dbObj.table+"."+dbObj.survey_id+" = "+dbObj2.table+"."+dbObj2.pk_field
                +" and ("+dbObj.survey_id+" like '%"+searchword+"%'"
                +" or "+dbObj2.survey_description+" like '%"+searchword+"%')"                
                +" and "+dbObj.survey_active+" = '0'"
                +" order by "+dbObj.table+"."+dbObj.survey_id+","+dbObj.table+"."+dbObj.survey_start;
        v = eQuery(sql);
        return v;
    }
    /**
     * ค้นหา
     * @param  
     * @return AgeSurvey
     * @author kingland
     * @date 22-02-2549
     */
    public AgeSurvey selectByPk(String pk) throws Exception
    {
        Vector v = null;
        String sql = "select * from "+dbObj.table+","+dbObj2.table
                +" where "+dbObj.table+"."+dbObj.survey_id+" = "+dbObj2.table+"."+dbObj2.pk_field
                +" and "+dbObj.pk_field+" = '"+pk+"'";
        v = eQuery(sql);
        return (AgeSurvey)v.get(0);
    }
    /**
     * ค้นหาตามช่วงอายุ
     * @param  
     * @return AgeSurvey
     * @author kingland
     * @date 22-02-2549
     */
    public Vector selectByAge(String age) throws Exception
    {
        Vector v = null;
        String sql = "select * from "+dbObj.table+","+dbObj2.table
                +" where "+dbObj.table+"."+dbObj.survey_id+" = "+dbObj2.table+"."+dbObj2.pk_field
                +" and to_number("+dbObj.survey_start+",'999') <= "+age
                +" and "+dbObj.survey_is_lifetime+" = '1'"
                +" UNION "
                +" select * from "+dbObj.table+","+dbObj2.table
                +" where " +dbObj.table+"."+dbObj.survey_id+" = "+dbObj2.table+"."+dbObj2.pk_field
                +" and "+dbObj.survey_is_lifetime+" = '0'"
                +" and (to_number("+dbObj.survey_start+",'999') <= "+age 
                +" and to_number("+dbObj.survey_end+",'999') >= "+age+")";
                v = eQuery(sql);
        return v;
    }

    public Vector selectByAgeActive(String age, String active) throws Exception
    {

        Vector v = null;
        String sql = "select * from "+dbObj.table+","+dbObj2.table
                +" where "+dbObj.table+"."+dbObj.survey_id+" = "+dbObj2.table+"."+dbObj2.pk_field
                +" and to_number("+dbObj.survey_start+",'999') <= "+age
                +" and "+dbObj.survey_is_lifetime+" = '1'"
                +" and "+dbObj.survey_active+" = '"+active+"'"
                +" UNION "
                +" select * from "+dbObj.table+","+dbObj2.table
                +" where " +dbObj.table+"."+dbObj.survey_id+" = "+dbObj2.table+"."+dbObj2.pk_field
                +" and "+dbObj.survey_is_lifetime+" = '0'"
                +" and "+dbObj.survey_active+" = '"+active+"'"
                +" and (to_number("+dbObj.survey_start+",'999') <= "+age 
                +" and to_number("+dbObj.survey_end+",'999') >= "+age+")";
                v = eQuery(sql);
        return v;
    }
}

