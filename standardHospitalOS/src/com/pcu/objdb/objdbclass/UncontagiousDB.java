/*
 * UncontagiousDB.java
 *
 * Created on 22 กุมภาพันธ์ 2549, 10:21 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.objdb.objdbclass;
import com.hospital_os.utility.Gutil;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
/**
 *
 * @author Administrator
 */
public class UncontagiousDB {
    /**
     * Connection
     */
    public ConnectionInf theConnectionInf;
    /**
     * Database Object
     */
    public Uncontagious dbObj;
    final private String idtable = "782";
    /** Creates a new instance of UncontagiousDB */
    public UncontagiousDB(ConnectionInf db) 
    {
        theConnectionInf=db;
        dbObj = new Uncontagious();
        initConfig();
    }
    /**
     * กำหนดฟิลด์ของตาราง
     * @param  
     * @return 
     * @author kingland
     * @date 22-02-2549
     */
    public boolean initConfig() 
    {
        dbObj.table = "t_health_uncontagious";
        dbObj.pk_field = "t_health_uncontagious_id";
        dbObj.disease_id = "b_health_disease_id";
        dbObj.family_id = "t_health_family_id";
        dbObj.visit_id = "t_visit_id";
        dbObj.patient_id = "t_patient_id";
        dbObj.icd10 = "health_uncontagious_icd10_number";
        dbObj.getwell = "health_uncontagious_get_well";
        dbObj.staff_recode = "health_uncontagious_staff_record";
        dbObj.staff_modify = "health_uncontagious_staff_modify";
        dbObj.staff_cancel = "health_uncontagious_staff_cancel";
        dbObj.record_datetime = "health_uncontagious_record_date_time";
        dbObj.modify_datetime = "health_uncontagious_modify_date_time";
        dbObj.cancel_datetime = "health_uncontagious_cancel_date_time";
        dbObj.survey_date = "health_uncontagious_survey_date";
        dbObj.active = "health_uncontagious_active";
        dbObj.contagious_type = "health_contagious_type";
        return true;
    }
     /**
     * เพิ่มข้อมูลเข้าไปในตาราง
     * @param  
     * @return 
     * @author kingland
     * @date 22-02-2549
     */
    public int insert(Uncontagious o) throws Exception 
    {
        String sql="";
        Uncontagious p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
                + dbObj.pk_field
                + " ,"	+ dbObj.active
                + " ,"	+ dbObj.cancel_datetime
                + " ,"	+ dbObj.disease_id
                + " ,"	+ dbObj.family_id
                + " ,"	+ dbObj.getwell
                + " ,"	+ dbObj.icd10
                + " ,"	+ dbObj.modify_datetime
                + " ,"	+ dbObj.patient_id
                + " ,"	+ dbObj.record_datetime
                + " ,"	+ dbObj.staff_cancel
                + " ,"	+ dbObj.staff_modify
                + " ,"	+ dbObj.staff_recode
                + " ,"	+ dbObj.survey_date
                + " ,"	+ dbObj.visit_id
                + " ,"	+ dbObj.contagious_type
                + " ) values ('"
                + p.getObjectId()
                + "','" + p.active
                + "','" + p.cancel_datetime
                + "','" + p.disease_id
                + "','" + p.family_id
                + "','" + p.getwell
                + "','" + p.icd10
                + "','" + p.modify_datetime
                + "','" + p.patient_id
                + "','" + p.record_datetime
                + "','" + p.staff_cancel
                + "','" + p.staff_modify
                + "','" + p.staff_recode
                + "','" + p.survey_date
                + "','" + p.visit_id
                + "','" + p.contagious_type
                + "')";
        return theConnectionInf.eUpdate(sql);
    }
    /**
     * ใช้การคิวรีข้อมูล แล้วสร้างเป็น Object
     * @param  
     * @return 
     * @author kingland
     * @date 22-02-2549
     */        
    public Vector eQuery(String sql) throws Exception 
    {
        Uncontagious p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next()) 
        {
            p = new Uncontagious();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.active = rs.getString(dbObj.active);
            p.cancel_datetime = rs.getString(dbObj.cancel_datetime);
            p.disease_id = rs.getString(dbObj.disease_id);
            p.family_id = rs.getString(dbObj.family_id);
            p.getwell = rs.getString(dbObj.getwell);
            p.icd10 = rs.getString(dbObj.icd10);
            p.modify_datetime = rs.getString(dbObj.modify_datetime);
            p.patient_id = rs.getString(dbObj.patient_id);
            p.record_datetime = rs.getString(dbObj.record_datetime);
            p.staff_cancel = rs.getString(dbObj.staff_cancel);
            p.staff_modify = rs.getString(dbObj.staff_modify);
            p.staff_recode = rs.getString(dbObj.staff_recode);
            p.survey_date = rs.getString(dbObj.survey_date);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.contagious_type = rs.getString(dbObj.contagious_type);
            list.add(p);
        }
        rs.close();
        return list;
    }
    /**
     * อับเดตข้อมูลในตาราง
     * @param  
     * @return 
     * @author kingland
     * @date 22-02-2549
     */
     public int update(Uncontagious o) throws Exception 
     {
        String sql="update " + dbObj.table + " set ";
        Uncontagious p=o;
        sql += dbObj.active + "='" + p.active
                + "', " + dbObj.cancel_datetime + "='" + p.cancel_datetime
                + "', " + dbObj.disease_id + "='" + p.disease_id
                + "', " + dbObj.family_id + "='" + p.family_id
                + "', " + dbObj.getwell + "='" + p.getwell
                + "', " + dbObj.icd10 + "='" + p.icd10
                + "', " + dbObj.modify_datetime + "='" + p.modify_datetime
                + "', " + dbObj.patient_id + "='" + p.patient_id
                + "', " + dbObj.record_datetime + "='" + p.record_datetime
                + "', " + dbObj.staff_cancel + "='" + p.staff_cancel
                + "', " + dbObj.staff_modify + "='" + p.staff_modify
                + "', " + dbObj.staff_recode + "='" + p.staff_recode
                + "', " + dbObj.survey_date + "='" + p.survey_date 
                + "', " + dbObj.visit_id + "='" + p.visit_id
                + "', " + dbObj.contagious_type + "='" + p.contagious_type
                + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
     /**
     * ลบข้อมูลในตาราง
     * @param  
     * @return 
     * @author kingland
     * @date 22-02-2549
     */
    public int delete(Uncontagious o) throws Exception 
    {
        String sql="delete from " + dbObj.table
                + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    /**
     * ค้นหาข้อมูลโรคไม่ติดต่อโดย FamilyID
     * @param  FamilyID
     * @return Vector of Uncontagious
     * @author kingland
     * @date 25-02-2549
     */
    public Vector selectByFamilyID(String familyID) throws Exception
    {
        String sql = "select * from "+dbObj.table
                +" where "+dbObj.family_id+" = '"+familyID+"'"
                + " and "+dbObj.active+" = '1'";
        return this.eQuery(sql);        
    }   
    /**
     * ค้นหาข้อมูลโรคไม่ติดต่อโดย DiseaseID
     * @param  DiseaseID
     * @return Vector of Uncontagious
     * @author henbe
     * @date 25-02-2549
     */
    public Vector selectByDiseaseID(String disID) throws Exception
    {
        String sql = "select * from "+dbObj.table
                +" where "+dbObj.disease_id + " = '"+disID+"'"
                + " and "+dbObj.active+" = '1'";
        return this.eQuery(sql);        
    }
    /**
     * ค้นหาโดยใช้ Primary key
     * @param  primary key
     * @return Object of Uncontagious
     * @author kingland
     * @date 27-02-2549
     */
    public Uncontagious selectByPK(String pk) throws Exception
    {
        String sql = "select * from "+dbObj.table
                +" where "+dbObj.pk_field+" = '"+pk+"'"
                + " and "+dbObj.active+" = '1'";
        Vector v = eQuery(sql);        
        return (Uncontagious) v.get(0);
    }
    
    /**
     *@author amp
     *@date 18/04/2549
     */
    public Uncontagious selectByFamilyIdAndDiseaseId(String family_id,String disease_id) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.family_id
        + " = '" + family_id + "' and " + dbObj.disease_id
        + " = '" + disease_id + "'";
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (Uncontagious)v.get(0);
    }
/////////////////////////////////////////////////////////////////////////////    
    public int updateFidByFid(String family_id,String family_from) throws Exception
    {
        String sql="update " + dbObj.table + " set " 
                + dbObj.family_id + "='" + family_id + "',"
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
