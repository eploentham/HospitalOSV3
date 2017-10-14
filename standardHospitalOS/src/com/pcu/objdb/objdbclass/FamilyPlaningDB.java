/*
 * FamilyPlaningDB.java
 *
 * Created on 20 มิถุนายน 2548, 11:56 น.
 */

package com.pcu.objdb.objdbclass;

import java.util.*;
import java.sql.*; 
import com.hospital_os.usecase.connection.*;
import com.pcu.object.*;
import com.hospital_os.utility.*;

import com.pcu.object.*;
/**
 *
 * @author tong
 */
public class FamilyPlaningDB {
    public ConnectionInf theConnectionInf;
    
    public  FamilyPlaning dbObj,p;
    final private String idtable = "742";
    
    private Vector vc;
    private ResultSet rs ;
    private String sql ;
    /** Creates a new instance of FamilyPlaningDB */
     
    
    public FamilyPlaningDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new FamilyPlaning();
        initConfig();
    }
    
   public boolean initConfig()
    {
        dbObj.table="t_health_family_planing";
        dbObj.pk_field="t_health_family_planing_id";
        
        dbObj.visit_id = "t_visit_id";
        dbObj.patient_id = "t_patient_id";
        dbObj.f_health_family_planing_method_id = "f_health_family_planing_method_id";
        dbObj.f_health_family_planing_id = "f_health_family_planing_id";	
        dbObj.health_family_planing_parity = "health_family_planing_parity";	
        dbObj.health_family_planing_staff_record = "health_family_planing_staff_record";	
        dbObj.record_date_time = "record_date_time";
        dbObj.health_family_planing_notice = "health_family_planing_notice";
        dbObj.health_family_planing_vn = "health_family_planing_vn";	
        dbObj.health_family_planing_hn = "health_family_planing_hn";	
        dbObj.health_family_planing_breast_exam = "health_family_planing_breast_exam";	
        dbObj.health_family_planing_breast_exam_notice = "health_family_planing_breast_exam_notice";	
        dbObj.health_family_planing_cervix_exam = "health_family_planing_cervix_exam";
        dbObj.health_family_planing_cervix_exam_notice = "health_family_planing_cervix_exam_notice";	
        dbObj.health_famlily_planing_pregnant_exam = "health_famlily_planing_pregnant_exam";
        dbObj.health_famlily_planing_supply = "b_health_family_planing_group_id";	
        dbObj.health_famlily_planing_supply_qty = "health_famlily_planing_supply_qty";	
        dbObj.health_famlily_planing_appointment = "health_famlily_planing_appointment";	
        dbObj.health_family_planing_staff_update = "health_family_planing_staff_update";	
        dbObj.update_record_date_time = "update_record_date_time";
        dbObj.health_family_planing_staff_remove = "health_family_planing_staff_remove";
        dbObj.health_family_planing_active = "health_family_planing_active";
        dbObj.health_family_planing_order_status = "health_family_planing_order_status";
        dbObj.family_id = "t_health_family_id";
        dbObj.health_family_planing_date ="health_family_planing_date";
        dbObj.health_family_planing_cervix_method = "health_family_planing_cervix_method";
        
        return true;
    }
    
    
    /**
     * @param cmd
     * @param o
     * @return int
     * @roseuid 3F6574DE0394
     */
    public int insert(FamilyPlaning o) throws Exception
    {
        sql="";
    
        o.generateOID(idtable);
        
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.visit_id
        + " ,"	+ dbObj.patient_id
        + " ,"	+ dbObj.f_health_family_planing_method_id
        + " ,"	+ dbObj.f_health_family_planing_id	
        + " ,"	+ dbObj.health_family_planing_parity
        + " ,"	+ dbObj.health_family_planing_staff_record	
        + " ,"	+ dbObj.record_date_time
        + " ,"	+ dbObj.health_family_planing_notice
        + " ,"	+ dbObj.health_family_planing_vn
        + " ,"	+ dbObj.health_family_planing_hn
        + " ,"	+ dbObj.health_family_planing_breast_exam	
        + " ,"	+ dbObj.health_family_planing_breast_exam_notice 
        + " ,"	+ dbObj.health_family_planing_cervix_exam
        + " ,"	+ dbObj.health_family_planing_cervix_exam_notice
        + " ,"	+ dbObj.health_famlily_planing_pregnant_exam
        + " ,"	+ dbObj.health_famlily_planing_supply	
        + " ,"	+ dbObj.health_famlily_planing_supply_qty	
        + " ,"	+ dbObj.health_famlily_planing_appointment
        + " ,"	+ dbObj.health_family_planing_staff_update	
        + " ,"	+ dbObj.update_record_date_time
        + " ,"	+ dbObj.health_family_planing_staff_remove
        + " ,"	+ dbObj.health_family_planing_order_status
        + " ,"	+ dbObj.health_family_planing_active
        + " ,"	+ dbObj.family_id
        + " ,"	+ dbObj.health_family_planing_date
        + " ,"	+ dbObj.health_family_planing_cervix_method
        + " ) values ('"
        + o.getObjectId()
        
        + "','" + o.visit_id
        + "','" + o.patient_id
        + "','" + o.f_health_family_planing_method_id
        + "','" + o.f_health_family_planing_id	
        + "','" + o.health_family_planing_parity
        + "','" + o.health_family_planing_staff_record	
        + "','" + o.record_date_time
        + "','" + o.health_family_planing_notice
        + "','" + o.health_family_planing_vn
        + "','" + o.health_family_planing_hn
        + "','" + o.health_family_planing_breast_exam	
        + "','" + o.health_family_planing_breast_exam_notice 
        + "','" + o.health_family_planing_cervix_exam
        + "','" + o.health_family_planing_cervix_exam_notice
        + "','" + o.health_famlily_planing_pregnant_exam
        + "','" + o.health_famlily_planing_supply	
        + "','" + o.health_famlily_planing_supply_qty	
        + "','" + o.health_famlily_planing_appointment
        + "','" + o.health_family_planing_staff_update	
        + "','" + o.update_record_date_time
        + "','" + o.health_family_planing_staff_remove
        + "','" + o.health_family_planing_order_status
        + "','" + o.health_family_planing_active
        + "','" + o.family_id
        + "','" + o.health_family_planing_date
        + "','" + o.health_family_planing_cervix_method
        + "')";
        
        int ias = theConnectionInf.eUpdate(sql);
        
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return ias;
    }
    public int update(FamilyPlaning p) throws Exception
    {
        sql="update " + dbObj.table + " set "+ dbObj.visit_id + "='" + p.visit_id
        + "', " + dbObj.patient_id + "='" + p.patient_id
        + "', " + dbObj.f_health_family_planing_method_id + "='" + p.f_health_family_planing_method_id
        + "', " + dbObj.f_health_family_planing_id + "='" + p.f_health_family_planing_id	
        + "', " + dbObj.health_family_planing_parity + "='" + p.health_family_planing_parity
        + "', " + dbObj.health_family_planing_staff_record + "='" + p.health_family_planing_staff_record	
        + "', " + dbObj.record_date_time + "='" + p.record_date_time
        + "', " + dbObj.health_family_planing_notice + "='" + p.health_family_planing_notice
        + "', " + dbObj.health_family_planing_vn + "='" + p.health_family_planing_vn
        + "', " + dbObj.health_family_planing_hn + "='" + p.health_family_planing_hn
        + "', " + dbObj.health_family_planing_breast_exam + "='" + p.health_family_planing_breast_exam	
        + "', " + dbObj.health_family_planing_breast_exam_notice  + "='" + p.health_family_planing_breast_exam_notice
        + "', " + dbObj.health_family_planing_cervix_exam + "='" + p.health_family_planing_cervix_exam
        + "', " + dbObj.health_family_planing_cervix_exam_notice + "='" + p.health_family_planing_cervix_exam_notice
        + "', " + dbObj.health_famlily_planing_pregnant_exam + "='" + p.health_famlily_planing_pregnant_exam
        + "', " + dbObj.health_famlily_planing_supply	 + "='" + p.health_famlily_planing_supply
        + "', " + dbObj.health_famlily_planing_supply_qty + "='" + p.health_famlily_planing_supply_qty	
        + "', " + dbObj.health_famlily_planing_appointment + "='" + p.health_famlily_planing_appointment
        + "', " + dbObj.health_family_planing_staff_update + "='" + p.health_family_planing_staff_update	
        + "', " + dbObj.update_record_date_time + "='" + p.update_record_date_time
        + "', " + dbObj.health_family_planing_staff_remove + "='" + p.health_family_planing_staff_remove
        + "', " + dbObj.health_family_planing_order_status + "='" + p.health_family_planing_order_status
        + "', " + dbObj.health_family_planing_active + "='" + p.health_family_planing_active
        + "', " + dbObj.family_id + "='" + p.family_id
        + "', " + dbObj.health_family_planing_date + "='" + p.health_family_planing_date        
        + "', " + dbObj.health_family_planing_cervix_method + "='" + p.health_family_planing_cervix_method
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(FamilyPlaning o) throws Exception
    {
        sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    public int updateActive(FamilyPlaning o) throws Exception
    {
       
       
       
        sql="update " + dbObj.table + " set " +
                "" + dbObj.health_family_planing_staff_remove  + "='" + o.health_family_planing_staff_remove + "'" +
                ", " + dbObj.health_family_planing_active + "='" + o.health_family_planing_active  + "'" +
                ", " + dbObj.update_record_date_time + "='" + o.update_record_date_time +  "'" +
               " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);

    }
    public FamilyPlaning selectByPK(String pk) throws Exception
    {
        sql="select * from " + dbObj.table
        + " where " + dbObj.pk_field
        + " = '" + pk + "'";        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (FamilyPlaning)v.get(0);
    }
    
    /**
     * ใช้ในการแสดงข้อมูลลงตาราง ของ GUI ดังนั้นจึงมีข้อมูลไม่ครบ
     * @author ผดุงรัฐ
     * @return Vector ของ Object แต่จะมีข้อมูลไม่ครบ
     */
    public Vector selectShowTableByVisitID(String visit_id) throws Exception
    {
        sql = "select * "+
              " from " + dbObj.table +
              " Where " + dbObj.visit_id + "='" + visit_id + "'" + 
              " AND " + dbObj.health_family_planing_active + "= '1'" +
              " Order by " + dbObj.record_date_time + " DESC";
        
        return eQueryShowTable(sql);
    }   
    /**
     * ใช้ในการแสดงข้อมูลลงตาราง ของ GUI ดังนั้นจึงมีข้อมูลไม่ครบ
     * @author ผดุงรัฐ
     * @return Vector ของ Object แต่จะมีข้อมูลไม่ครบ
     */
    public Vector selectShowTableByPatientID(String patient_id) throws Exception
    {
        sql = "select * " +
              " from " + dbObj.table +
              " Where " + dbObj.patient_id + "='" + patient_id + "'" + 
              " AND " + dbObj.health_family_planing_active + "= '1'" +
              " Order by " + dbObj.record_date_time + " DESC";
        
        return eQueryShowTable(sql);
    }
    
    /**
     * ใช้ในการแสดงข้อมูลลงตาราง ของ GUI ดังนั้นจึงมีข้อมูลไม่ครบ
     * @author Jao
     * @return Vector ของ Object แต่จะมีข้อมูลไม่ครบ
     */
    public Vector selectShowTableByFamilyID(String family_id) throws Exception
    {
        sql = "select * " +
              " from " + dbObj.table +
              " Where " + dbObj.family_id + "='" + family_id + "'" + 
              " AND " + dbObj.health_family_planing_active + "= '1'" +
              " Order by " + dbObj.record_date_time + " DESC";
        
        return eQueryShowTable(sql);
    }  
    public Vector selectAll() throws Exception
    {
        sql="select * from " + dbObj.table;
        return eQuery(sql);
    }
    
    public Vector selectByHN(String pk) throws Exception
    {
        sql="select * from " + dbObj.table
        + " where " + dbObj.health_family_planing_hn
        + " = '" + pk + "' order by " + dbObj.health_family_planing_vn;
        return eQuery(sql);
    }
    
    public Vector selectByPatientID(String patient_id) throws Exception
    {
        sql="select * from " + dbObj.table
        + " where " + dbObj.patient_id
        + " = '" + patient_id + "' order by " + dbObj.visit_id;
        return eQuery(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        FamilyPlaning p;
        vc = new Vector();
        rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new FamilyPlaning();
            p.setObjectId(rs.getString(dbObj.pk_field));
            
            p.visit_id = rs.getString(dbObj.visit_id);
            p.patient_id = rs.getString(dbObj.patient_id);
            p.f_health_family_planing_method_id = rs.getString(dbObj.f_health_family_planing_method_id);
            p.f_health_family_planing_id	 = rs.getString(dbObj.f_health_family_planing_id);
            p.health_family_planing_parity = rs.getString(dbObj.health_family_planing_parity);
            p.health_family_planing_staff_record = rs.getString(dbObj.health_family_planing_staff_record);	
            p.record_date_time = rs.getString(dbObj.record_date_time);
            p.health_family_planing_notice = rs.getString(dbObj.health_family_planing_notice);
            p.health_family_planing_vn = rs.getString(dbObj.health_family_planing_vn);
            p.health_family_planing_hn = rs.getString(dbObj.health_family_planing_hn);
            p.health_family_planing_breast_exam	 = rs.getString(dbObj.health_family_planing_breast_exam);
            p.health_family_planing_breast_exam_notice  = rs.getString(dbObj.health_family_planing_breast_exam_notice);
            p.health_family_planing_cervix_exam = rs.getString(dbObj.health_family_planing_cervix_exam);
            p.health_family_planing_cervix_exam_notice = rs.getString(dbObj.health_family_planing_cervix_exam_notice);
            p.health_famlily_planing_pregnant_exam = rs.getString(dbObj.health_famlily_planing_pregnant_exam);
            p.health_famlily_planing_supply	 = rs.getString(dbObj.health_famlily_planing_supply);
            p.health_famlily_planing_supply_qty	 = rs.getString(dbObj.health_famlily_planing_supply_qty);
            p.health_famlily_planing_appointment = rs.getString(dbObj.health_famlily_planing_appointment);
            p.health_family_planing_staff_update = rs.getString(dbObj.health_family_planing_staff_update);	
            p.update_record_date_time = rs.getString(dbObj.update_record_date_time);
            p.health_family_planing_staff_remove = rs.getString(dbObj.health_family_planing_staff_remove);
            p.health_family_planing_order_status = rs.getString(dbObj.health_family_planing_order_status);
            p.health_family_planing_active = rs.getString(dbObj.health_family_planing_active);
            p.family_id = rs.getString(dbObj.family_id);
            p.health_family_planing_date = rs.getString(dbObj.health_family_planing_date);
            p.health_family_planing_cervix_method = rs.getString(dbObj.health_family_planing_cervix_method);
            vc.add(p);
        }
        rs.close();
        return vc;
    }
    
    
    public Vector eQueryShowTable(String sql) throws Exception
    {
        FamilyPlaning p;
        vc = new Vector();
        rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new FamilyPlaning();
            p.setObjectId(rs.getString(dbObj.pk_field));
            
            p.visit_id = rs.getString(dbObj.visit_id);
            p.patient_id = rs.getString(dbObj.patient_id);
            //p.f_health_family_planing_method_id = rs.getString(dbObj.f_health_family_planing_method_id);
            //p.f_health_family_planing_id	 = rs.getString(dbObj.f_health_family_planing_id);
            //p.health_family_planing_parity = rs.getString(dbObj.health_family_planing_parity);
            //p.health_family_planing_staff_record = rs.getString(dbObj.health_family_planing_staff_record);	
            p.record_date_time = rs.getString(dbObj.record_date_time);
           // p.health_family_planing_notice = rs.getString(dbObj.health_family_planing_notice);
            p.health_family_planing_vn = rs.getString(dbObj.health_family_planing_vn);
            p.health_family_planing_hn = rs.getString(dbObj.health_family_planing_hn);
            //p.health_family_planing_breast_exam	 = rs.getString(dbObj.health_family_planing_breast_exam);
            //p.health_family_planing_breast_exam_notice  = rs.getString(dbObj.health_family_planing_breast_exam_notice);
            //p.health_family_planing_cervix_exam = rs.getString(dbObj.health_family_planing_cervix_exam);
            //p.health_family_planing_cervix_exam_notice = rs.getString(dbObj.health_family_planing_cervix_exam_notice);
            //p.health_famlily_planing_pregnant_exam = rs.getString(dbObj.health_famlily_planing_pregnant_exam);
            //p.health_famlily_planing_supply	 = rs.getString(dbObj.health_famlily_planing_supply);
            //p.health_famlily_planing_supply_qty	 = rs.getString(dbObj.health_famlily_planing_supply_qty);
            //p.health_famlily_planing_appointment = rs.getString(dbObj.health_famlily_planing_appointment);
            //p.health_family_planing_staff_update = rs.getString(dbObj.health_family_planing_staff_update);	
            p.update_record_date_time = rs.getString(dbObj.update_record_date_time);
            //p.health_family_planing_staff_remove = rs.getString(dbObj.health_family_planing_staff_remove);
            //p.health_family_planing_active = rs.getString(dbObj.health_family_planing_active);
           
            vc.add(p);
        }
        rs.close();
        return vc;
    }
/////////////////////////////////////////////////////////////////////////////    
    public int updateFidByFid(String family_id,String family_from) throws Exception
    {
        String sql="update " + dbObj.table + " set " 
                + dbObj.family_id + "='" + family_id + "',"
                + dbObj.health_family_planing_staff_remove + "="+ dbObj.health_family_planing_staff_remove + "||'-'||'" + family_from + "'"
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
