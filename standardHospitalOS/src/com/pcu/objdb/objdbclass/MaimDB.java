/*
 * MaimDB.java
 *
 * Created on 22 กุมภาพันธ์ 2549, 10:28 น.
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
 * @author Jao
 */
public class MaimDB {
    
    /** Creates a new instance of MaimDB */
    public MaimDB() {
    }
    
    public ConnectionInf theConnectionInf;
    public Maim dbObj;
    final private String idtable = "784";
    
    public MaimDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new Maim();
        initConfig(); 
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_health_maim";        
        dbObj.pk_field = "t_health_maim_id";        
        dbObj.family_id="t_health_family_id";
        dbObj.maim_id="b_health_maim_id";
        dbObj.patient_id="t_patient_id";
        dbObj.visit_id="t_visit_id";
        dbObj.maim_treat="health_maim_treat";
        dbObj.maim_registry = "health_maim_registry";
        dbObj.staff_record="health_maim_staff_record";
        dbObj.staff_modify="health_maim_staff_modify";
        dbObj.staff_cancel="health_maim_staff_cancel";
        dbObj.record_date_time="health_maim_record_date_time";
        dbObj.modify_date_time="health_maim_modify_date_time";
        dbObj.cancel_date_time="health_maim_cancel_date_time";
        dbObj.active="health_maim_active";
        dbObj.survey_date ="health_maim_survey_date";
        dbObj.description = "health_maim_description";
        return true;
    }
    
    /**
     * บันทึกข้อมูลความพิการ 
     * @param  Object Maim
     * @return 
     * @author Jao
     * @date 04-03-2549
     */
    public int insert(Maim o) throws Exception
    {
        String sql="";
        Maim p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field        
        + " ,"  + dbObj.family_id
        + " ,"  + dbObj.maim_id
        + " ,"  + dbObj.patient_id
        + " ,"  + dbObj.visit_id
        + " ,"  + dbObj.maim_treat
        + " ,"  + dbObj.maim_registry
        + " ,"  + dbObj.staff_record
        + " ,"  + dbObj.staff_modify
        + " ,"  + dbObj.staff_cancel
        + " ,"  + dbObj.record_date_time
        + " ,"  + dbObj.modify_date_time
        + " ,"  + dbObj.cancel_date_time
        + " ,"  + dbObj.active
        + " ,"  + dbObj.survey_date
        + " ,"  + dbObj.description
        + " ) values ('"
        + p.getObjectId()        
        + "','" + p.family_id
        + "','" + p.maim_id
        + "','" + p.patient_id
        + "','" + p.visit_id
        + "','" + p.maim_treat
        + "','" + p.maim_registry        
        + "','" + p.staff_record
        + "','" + p.staff_modify
        + "','" + p.staff_cancel
        + "','" + p.record_date_time
        + "','" + p.modify_date_time
        + "','" + p.cancel_date_time
        + "','" + p.active
        + "','" + p.survey_date
        + "','" + p.description
        + "')";        
        return theConnectionInf.eUpdate(sql);
    }
    
    /**
     * อัพเดตข้อมูลความพิการ 
     * @param  Object Maim
     * @return 
     * @author Jao
     * @date 04-03-2549
     */
    public int update(Maim o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        Maim p=o;
        String field =""        
        + "', " + dbObj.family_id+ "='" + p.family_id
        + "', " + dbObj.maim_id+ "='" + p.maim_id
        + "', " + dbObj.patient_id+ "='" + p.patient_id
        + "', " + dbObj.visit_id+ "='" + p.visit_id
        + "', " + dbObj.maim_treat+ "='" + p.maim_treat
        + "', " + dbObj.maim_registry+ "='" + p.maim_registry
        + "', " + dbObj.staff_record+ "='" + p.staff_record
        + "', " + dbObj.staff_modify+ "='" + p.staff_modify
        + "', " + dbObj.staff_cancel+ "='" + p.staff_cancel
        + "', " + dbObj.record_date_time+ "='" + p.record_date_time
        + "', " + dbObj.modify_date_time+ "='" + p.modify_date_time
        + "', " + dbObj.cancel_date_time+ "='" + p.cancel_date_time
        + "', " + dbObj.active+ "='" + p.active
        + "', " + dbObj.survey_date+ "='" + p.survey_date
        + "', " + dbObj.description+ "='" + p.description
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = sql+field.substring(2);
        return theConnectionInf.eUpdate(sql);
    }
    
    /**
     * ลบข้อมูลความพิการ 
     * @param  Object Maim
     * @return int
     * @author Jao
     * @date 04-03-2549
     */
    public int delete(Maim o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    /**
     * ค้นหาข้อมูลความพิการ FamilyID
     * @param  FamilyID
     * @return Vector of Maim
     * @author Jao
     * @date 04-03-2549
     */
    public Vector selectByFamilyID(String familyID) throws Exception
    {
        String sql = "select * from "+dbObj.table
                +" where "+dbObj.family_id+" = '"+familyID+"'"
                + " and "+dbObj.active+" = '1'";
        return this.eQuery(sql);        
    }
    
    /**
     * ค้นหาโดยใช้ Primary key
     * @param  primary key
     * @return Object of Maim
     * @author kingland
     * @date 27-02-2549
     */
    public Maim selectByPK(String pk) throws Exception
    {
        String sql = "select * from "+dbObj.table
                +" where "+dbObj.pk_field+" = '"+pk+"'"
                + " and "+dbObj.active+" = '1'";
        Vector v = eQuery(sql);        
        return (Maim) v.get(0);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        Maim p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Maim();
            p.setObjectId(rs.getString(dbObj.pk_field));            
            p.family_id= rs.getString(dbObj.family_id);
            p.maim_id= rs.getString(dbObj.maim_id);
            p.patient_id= rs.getString(dbObj.patient_id);
            p.visit_id= rs.getString(dbObj.visit_id);
            p.maim_treat= rs.getString(dbObj.maim_treat);
            p.maim_registry= rs.getString(dbObj.maim_registry);
            p.staff_record= rs.getString(dbObj.staff_record);
            p.staff_modify= rs.getString(dbObj.staff_modify);
            p.staff_cancel= rs.getString(dbObj.staff_cancel);
            p.record_date_time= rs.getString(dbObj.record_date_time);
            p.modify_date_time= rs.getString(dbObj.modify_date_time);
            p.cancel_date_time= rs.getString(dbObj.cancel_date_time);
            p.active= rs.getString(dbObj.active);
            p.survey_date= rs.getString(dbObj.survey_date);
            p.description= rs.getString(dbObj.description);
            list.add(p);
        }
        rs.close();
        return list;
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
