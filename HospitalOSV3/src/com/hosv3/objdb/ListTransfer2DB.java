/*
 * ListTransferDB.java
 *
 * Created on 11 สิงหาคม 2547, 8:45 น.
 *
 */
package com.hosv3.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
import com.hospital_os.objdb.*;
import com.hosv3.utility.DateUtil;
//import com.hosv3.object.*;
import java.util.*;
import java.sql.*;
/**
 * 
 * @author henbe
 * @deprecated ให้ใช้ ListTransferDB แทนเพราะว่าแก้ให้สามารถใช้งานได้แล้ว
 */
public class ListTransfer2DB extends ListTransferDB
{
    ListTransfer dbObj2  = new ListTransfer();
    public ListTransfer2DB(ConnectionInf db) {
        super(db);
        super.dbObj.bed = "visit_bed";
        dbObj2.assign_time = "assign_date_time";
        dbObj2.description = "visit_queue_setup_description";
        dbObj2.fname = "patient_firstname";
        dbObj2.hn = "visit_hn";
        dbObj2.lname = "patient_lastname";
        dbObj2.locking = "visit_locking";
        dbObj2.name = "service_point_description";
        dbObj2.patient_id = "t_patient_id";
        dbObj2.queue_visit = "assign_date_time";
        dbObj2.visit_id = "t_visit_id";
        dbObj2.vn = "visit_vn";
        dbObj2.visit_type = "f_visit_type_id";
        dbObj2.servicepoint_id = "b_service_point_id";
        dbObj2.patient_allergy = "patient_drugallergy";
        dbObj2.prefix = "f_patient_prefix_id";
        dbObj2.bed= "visit_bed";
        dbObj2.labstatus = "visit_lab_status_id";        
        dbObj2.doctor_dx = "visit_dx";
    }
    
/********ListTransferDB*********************/
    /**
     *  ใช้ในการ list ผู้ป่วยที่อยู่ในจุดบริการ โดยจะส่งข้อมูลเข้า
     *
     */
    public Vector listQueueXray(String choose)  throws Exception{
        String SQL = "select t_patient.patient_drugallergy,t_visit.visit_locking,t_visit.visit_hn,t_visit.visit_vn,t_patient.f_patient_prefix_id,t_patient.patient_firstname,t_patient.patient_lastname,t_visit_queue_xray.assign_date_time,t_visit.t_visit_id,t_visit.t_patient_id,t_visit.f_visit_type_id,t_visit.visit_lab_status_id " +
        " from t_visit_queue_xray,t_patient,t_visit " +
        " where t_visit.t_visit_id = t_visit_queue_xray.t_visit_id " +
        " and t_patient.t_patient_id = t_visit.t_patient_id " ;
        if( choose.equalsIgnoreCase(VisitType.IPD) || choose.equalsIgnoreCase(VisitType.OPD) ){
            SQL = SQL + " and t_visit.f_visit_type_id  = '" + choose + "' ";
        }
        SQL = SQL +" order by " + dbObj.assign_time;
        Vector v=veQueryLabXrayICD(SQL);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    
    /********ListTransferDB*********************/
     public Vector listQueueLab(String choose)  throws Exception
    {
        /*String SQL = "select t_patient.patient_drugallergy,t_visit.visit_locking,t_visit.visit_hn,t_visit.visit_vn,t_patient.f_patient_prefix_id,t_patient.patient_firstname,t_patient.patient_lastname,t_visit_queue_lab.assign_date_time,t_visit.t_visit_id,t_visit.t_patient_id,t_visit.f_visit_type_id,t_visit.visit_lab_status_id  " +
        " from t_visit_queue_lab,t_patient,t_visit" +
        " where t_visit.t_visit_id = t_visit_queue_lab.t_visit_id " +
        " and t_patient.t_patient_id = t_visit.t_patient_id " +
        " and t_visit_queue_lab.visit_queue_lab_remain = '0'" ;
        if( choose.equalsIgnoreCase(VisitType.IPD) || choose.equalsIgnoreCase(VisitType.OPD) )
        {
            SQL = SQL + " and t_visit.f_visit_type_id  = '" + choose + "' ";
        }
        SQL = SQL +  " order by " + dbObj.assign_time;*/
        
        //amp: 06/06/2549 เพราะมีการเพิ่ม Field ในตาราง t_visit_queue_lab เพื่อแลปปกปิด
        String SQL = "select t_patient.patient_drugallergy,t_visit.visit_locking,t_visit.visit_hn,t_visit.visit_vn,t_patient.f_patient_prefix_id,t_patient.patient_firstname,t_patient.patient_lastname,t_visit_queue_lab.assign_date_time,t_visit.t_visit_id,t_visit.t_patient_id,t_visit.f_visit_type_id,t_visit.visit_lab_status_id,t_visit_queue_lab.visit_queue_order,t_visit_queue_lab.visit_queue_secret_code  " +
        " from t_visit_queue_lab,t_patient,t_visit" +
        " where t_visit.t_visit_id = t_visit_queue_lab.t_visit_id " +
        " and t_patient.t_patient_id = t_visit.t_patient_id " +
        " and t_visit_queue_lab.visit_queue_lab_remain = '0'" ;
        if( choose.equalsIgnoreCase(VisitType.IPD) || choose.equalsIgnoreCase(VisitType.OPD) )
        {
            SQL = SQL + " and t_visit.f_visit_type_id  = '" + choose + "' ";
        }
        SQL = SQL +  " order by " + dbObj.assign_time;
        
        Vector v=veQueryLab(SQL);
        if(v.size()==0)
            return null;
        else
            return v;
    }    
     
 /********ListTransferDB*********************/
    public Vector listRemainQueueLab(String choose)  throws Exception
    {
        String SQL = "select t_patient.patient_drugallergy,t_visit.visit_locking,t_visit.visit_hn,t_visit.visit_vn,t_patient.f_patient_prefix_id,t_patient.patient_firstname,t_patient.patient_lastname,t_visit_queue_lab.assign_date_time,t_visit.t_visit_id,t_visit.t_patient_id,t_visit.f_visit_type_id,t_visit.visit_lab_status_id,t_visit_queue_lab.visit_queue_order,t_visit_queue_lab.visit_queue_secret_code  " +
        " from t_visit_queue_lab,t_patient,t_visit " +
        " where t_visit.t_visit_id = t_visit_queue_lab.t_visit_id " +
        " and t_patient.t_patient_id = t_visit.t_patient_id " +
        " and t_visit_queue_lab.visit_queue_lab_remain = '1' " ;
        if( choose.equalsIgnoreCase(VisitType.IPD) || choose.equalsIgnoreCase(VisitType.OPD) )
        {
            SQL = SQL + " and t_visit.f_visit_type_id  = '" + choose + "' ";
        }
        SQL = SQL + " order by " + dbObj.assign_time;
        
        Vector v=veQueryLab(SQL);
        if(v.size()==0)
            return null;
        else
            return v;
    }
     
    /**
     *  ใช้ในการ list ผู้ป่วยที่อยู่ในจุดบริการ โดยจะส่งข้อมูลเข้า
     *  service_point_id = key หลักของตาราง โดยจะเป็น key หลักของตาราง service_point
     *  employee_id_doctor = key หลักของตาราง โดยจะเป็น key หลักของตาราง Employee เฉพาะจุดบริการที่เป็นห้องตรวจ
     *  choose = เลือกว่าเป็นผู้ป่วยใน หรือ ผู้ป่วยนอก
     */
    public Vector listTransferByServicePoint(String service_point_id,String employee_id_doctor,String choose)  throws Exception
    {   //
        String SQL = "select  patient_drugallergy,t_visit.visit_locking ,t_visit.visit_hn,t_visit.visit_vn,t_patient.patient_firstname,t_patient.patient_lastname,t_visit_service.assign_date_time,b_service_point.service_point_description,t_visit.t_visit_id,t_visit.t_patient_id,t_visit.f_visit_type_id,b_service_point_id " +
        " from t_patient,t_visit,t_visit_service,b_service_point, " +
        " where t_visit_service.b_service_point_id like '" + service_point_id + "' " +
        " and (t_visit_service.f_visit_service_status_id ='1' or t_visit_service.f_visit_service_status_id ='2')" +
        " and t_visit.t_patient_id = t_patient.t_patient_id and t_visit_service.t_visit_id = t_visit.t_visit_id" +
        " and b_service_point.b_service_point_id = t_visit_service.b_service_point_id " +
        " and t_visit.f_visit_status_id = '1' ";
        if( !employee_id_doctor.trim().equals("") )
        {
            SQL = SQL + " and t_visit_service.visit_service_staff_doctor = '" + employee_id_doctor + "' ";
        }
        if( choose.equals(VisitType.IPD) || choose.equals(VisitType.OPD) )
        {
            SQL = SQL + " and t_visit.f_visit_type_id = '" + choose + "' ";
        }
        SQL = SQL + " order by " + dbObj.assign_time;
        return veQuery(SQL);
    }
        
    /**
     *  ใช้ในการ list ผู้ป่วยที่อยู่ในจุดบริการ โดยจะส่งข้อมูลเข้า
     *  service_point_id = key หลักของตาราง โดยจะเป็น key หลักของตาราง service_point
     *  employee_id_doctor = key หลักของตาราง โดยจะเป็น key หลักของตาราง Employee เฉพาะจุดบริการที่เป็นห้องตรวจ
     *  choose = เลือกว่าเป็นผู้ป่วยใน หรือ ผู้ป่วยนอก
     */
    public Vector listTransferVisitQueueByServicePoint(String service_point_id,String employee_id_doctor,String choose)  throws Exception
    {   
        String SQL = "SELECT visitintransect.patient_drugallergy,visitintransect.visit_locking, visitintransect.visit_hn, visitintransect.visit_vn, visitintransect.patient_firstname, visitintransect.patient_lastname, visitintransect.assign_date_time, visitintransect.service_point_description, visitintransect.t_patient_id, visitintransect.t_visit_id, visitintransect.f_visit_type_id,visitintransect.b_service_point_id, Query1.visit_queue_map_queue, Query1.visit_queue_setup_queue_color, Query1.visit_queue_setup_description " +
        " FROM ( " +  //t_patient.patient_drugallergy,
        "      SELECT t_patient.patient_drugallergy,t_visit.visit_locking, t_visit.visit_hn, t_visit.visit_vn, t_patient.patient_firstname, t_patient.patient_lastname, t_visit_service.assign_date_time, b_service_point.service_point_description, t_visit.t_patient_id, t_visit.t_visit_id, t_visit.f_visit_type_id,b_service_point.b_service_point_id " +
        "      FROM t_patient, t_visit, t_visit_service, b_service_point" +
        "      WHERE t_visit.t_visit_id=t_visit_service.t_visit_id AND (t_visit_service.f_visit_service_status_id='2' Or t_visit_service.f_visit_service_status_id='1') AND t_visit.f_visit_status_id='1' " +
        "      AND b_service_point.b_service_point_id Like '" + service_point_id + "' AND t_patient.t_patient_id=t_visit.t_patient_id AND t_visit_service.b_service_point_id=b_service_point.b_service_point_id " ;
        if( !employee_id_doctor.trim().equalsIgnoreCase("") )
        {
            SQL = SQL + " and t_visit_service.visit_service_staff_doctor = '" + employee_id_doctor + "' ";
        }
        if( choose.equalsIgnoreCase(VisitType.IPD) || choose.equalsIgnoreCase(VisitType.OPD) )
        {
            SQL = SQL + " and t_visit.f_visit_type_id = '" + choose + "' ";
        }
      /*  SQL = SQL + " order by " + dbObj.assign_time;
       */
        
        SQL = SQL +
        " ) as visitintransect " +
        " LEFT JOIN " +
        "( " +
        "      SELECT b_visit_queue_setup.visit_queue_setup_description, b_visit_queue_setup.visit_queue_setup_queue_color, t_visit_queue_map.visit_queue_map_queue, t_visit_queue_map.t_visit_id " +
        "      FROM t_visit_queue_map INNER JOIN b_visit_queue_setup ON t_visit_queue_map.b_visit_queue_setup_id = b_visit_queue_setup.b_visit_queue_setup_id " +
        "      WHERE (((t_visit_queue_map.visit_queue_map_active)='1')) " +
        ") as Query1 " +
        "ON visitintransect.t_visit_id = Query1.t_visit_id " +
        " order by " + dbObj.assign_time ;//  + " limit 20 ";
        
        
        return veQuery1(SQL);
    }
    
    //HENBE_APR
    public Vector listTransferVisitQueueByServicePointTable(String service_point_id
    ,String employee_id_doctor,String choose)  throws Exception
    {
        String SQL = "select * "+
        " from t_visit_service where  b_service_point_id like '" + service_point_id +"' "+
        " and (f_visit_service_status_id='2' Or f_visit_service_status_id='1')"+
        " AND f_visit_status_id='1' and visit_queue_map_active='1' ";

        if(!employee_id_doctor.trim().equalsIgnoreCase("")){
            SQL = SQL + " and visit_service_staff_doctor = '" + employee_id_doctor + "' ";
        }
        if( choose.equalsIgnoreCase(VisitType.IPD) 
        || choose.equalsIgnoreCase(VisitType.OPD)){
            SQL = SQL + " and f_visit_type_id = '" + choose + "' ";
        }
        SQL = SQL + " order by " + dbObj.assign_time;
        Vector v=veQuery(SQL);
        if(v.size()==0)      return null;
        else            return v;
    }
    //HENBE_APR//
    /**
     *  ใช้ในการ list ผู้ป่วยที่อยู่ในจุดบริการ โดยจะส่งข้อมูลเข้า
     *  service_point_id = key หลักของตาราง โดยจะเป็น key หลักของตาราง service_point
     *  employee_id_doctor = key หลักของตาราง โดยจะเป็น key หลักของตาราง Employee เฉพาะจุดบริการที่เป็นห้องตรวจ
     *  choose = เลือกว่าเป็นผู้ป่วยใน หรือ ผู้ป่วยนอก
     */
    public Vector listQueueVisitInWard(String ward_id)  throws Exception
    {
        String SQL = "select t_visit.visit_locking,t_visit.visit_vn,t_visit.visit_hn"
        + ",t_patient.patient_firstname,t_patient.patient_lastname"
        + ",t_visit.t_patient_id,t_visit.t_visit_id,t_patient.patient_drugallergy"
        + ",t_visit.visit_begin_admit_date_time as " + dbObj.assign_time
        + ",t_patient.f_patient_prefix_id as " + dbObj.prefix
        + ",t_visit.visit_bed ,t_visit.visit_lab_status_id" +
        ",t_visit.visit_dx " +
        " from t_visit,t_patient " +
        " where t_visit.b_visit_ward_id = '"+ ward_id+ "' " +
        " and t_visit.t_patient_id = t_patient.t_patient_id " +
        " and t_visit.f_visit_status_id = '1' " +
        " and t_visit.f_visit_type_id = '1' " +
        " order by  t_visit.visit_begin_admit_date_time ";
        return veQueryWard(SQL);
    }
    
    
    
    /**
     *  ใช้ในการ list ผู้ป่วยที่อยู่ในจุดบริการ โดยจะส่งข้อมูลเข้า
     *
     */
    public Vector listQueueXray()  throws Exception
    {
        String SQL = "select t_patient.patient_drugallergy,t_visit.visit_locking,t_visit.visit_hn,t_visit.visit_vn,t_patient.f_patient_prefix_id,t_patient.patient_firstname,t_patient.patient_lastname,t_visit_queue_xray.assign_date_time,t_visit.t_visit_id,t_visit.t_patient_id,t_visit.f_visit_type_id,t_visit.visit_lab_status_id " +
        " from t_visit_queue_xray,t_patient,t_visit " +
        " where t_visit.t_visit_id = t_visit_queue_xray.t_visit_id " +
        " and t_patient.t_patient_id = t_visit.t_patient_id " +
        " order by " + dbObj.assign_time;
        
        Vector v=veQueryLabXrayICD(SQL);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    /**
     *  ใช้ในการ list ผู้ป่วยที่อยู่ในจุดบริการ โดยจะส่งข้อมูลเข้า
     *
     */
    public Vector listQueueICD(String choose)  throws Exception
    {
        String SQL = "select t_patient.patient_drugallergy,t_visit.visit_locking,t_visit.visit_hn,t_visit.visit_vn,t_patient.f_patient_prefix_id,t_patient.patient_firstname,t_patient.patient_lastname,t_visit_queue_coding.assign_date_time,t_visit.t_visit_id,t_visit.t_patient_id,t_visit.f_visit_type_id,t_visit.visit_lab_status_id  " +
        " from t_visit_queue_coding,t_patient,t_visit " +
        " where t_visit.t_visit_id = t_visit_queue_coding.t_visit_id " +
        " and t_patient.t_patient_id = t_visit.t_patient_id " ;
        if( choose.equalsIgnoreCase(VisitType.IPD) || choose.equalsIgnoreCase(VisitType.OPD) )
        {
            SQL = SQL + " and t_visit.f_visit_type_id  = '" + choose + "' ";
        }
        SQL = SQL + " Group by t_patient.patient_drugallergy,t_visit.visit_locking,t_visit.visit_hn,t_visit.visit_vn,t_patient.patient_firstname,t_patient.patient_lastname,t_visit_queue_coding.assign_date_time,t_visit.t_visit_id,t_visit.t_patient_id,t_visit.f_visit_type_id,t_patient.f_patient_prefix_id,t_visit.visit_lab_status_id  ";
        SQL = SQL + " order by " + dbObj.assign_time;
     
        Vector v=veQueryLabXrayICD(SQL);
        
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
  
    /**
     *  ใช้ในการ list ผู้ป่วยที่อยู่ในจุดบริการ โดยจะส่งข้อมูลเข้า
     *
     */
    public Vector listQueueLab()  throws Exception
    {
        String SQL = "select t_patient.patient_drugallergy,t_visit.visit_locking,t_visit.visit_hn,t_visit.visit_vn,t_patient.f_patient_prefix_id,t_patient.patient_firstname,t_patient.patient_lastname,t_visit_queue_lab.assign_date_time,t_visit.t_visit_id,t_visit.t_patient_id,t_visit.f_visit_type_id,t_visit.visit_lab_status_id  " +
        " from t_visit_queue_lab,t_patient,t_visit" +
        " where t_visit.t_visit_id = t_visit_queue_lab.t_visit_id " +
        " and t_patient.t_patient_id = t_visit.t_patient_id " +
        " and t_visit_queue_lab.visit_queue_lab_remain = '0'" +
        " order by " + dbObj.assign_time;
        
        Vector v=veQueryLabXrayICD(SQL);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    /**
     *  ใช้ในการ list ผู้ป่วยที่อยู่ในคิวค้างผลแลป
     *
     */
    public Vector listRemainQueueLab()  throws Exception
    {
        String SQL = "select t_patient.patient_drugallergy,t_visit.visit_locking,t_visit.visit_hn,t_visit.visit_vn,t_patient.f_patient_prefix_id,t_patient.patient_firstname,t_patient.patient_lastname,t_visit_queue_lab.assign_date_time,t_visit.t_visit_id,t_visit.t_patient_id,t_visit.f_visit_type_id,t_visit.visit_lab_status_id  " +
        " from t_visit_queue_lab,t_patient,t_visit " +
        " where t_visit.t_visit_id = t_visit_queue_lab.t_visit_id " +
        " and t_patient.t_patient_id = t_visit.t_patient_id " +
        " and t_visit_queue_lab.visit_queue_lab_remain = '1' " +
        " order by " + dbObj.assign_time;
        
        Vector v=veQueryLabXrayICD(SQL);
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
    
    
    public Vector veQueryLabXrayICD(String sql) throws Exception
    {
        ListTransfer p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ListTransfer();
            p.assign_time = rs.getString(dbObj.assign_time);
            //            p.description = rs.getString(dbObj.description);
            p.fname = rs.getString(dbObj.fname);
            p.hn = rs.getString(dbObj.hn);
            p.lname = rs.getString(dbObj.lname);
            p.locking = rs.getString(dbObj.locking);
            //       p.name = rs.getString(dbObj.name);
            p.patient_id = rs.getString(dbObj.patient_id);
            // p.queue_visit = rs.getString(dbObj.queue_visit);
            // p.color = rs.getString(dbObj.color);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.vn = rs.getString(dbObj.vn);
            p.visit_type = rs.getString(dbObj.visit_type);
            //  p.queue = rs.getString(dbObj.queue);
            p.prefix = rs.getString(dbObj.prefix);
            p.patient_allergy = rs.getString(dbObj.patient_allergy);
            p.labstatus = rs.getString(dbObj2.labstatus);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    /**
     *@Author: amp
     *@date : 06/03/2549
     */
    public Vector veQueryLab(String sql) throws Exception
    {
        ListTransfer p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ListTransfer();
            p.assign_time = rs.getString(dbObj.assign_time);
            p.fname = rs.getString(dbObj.fname);
            p.hn = rs.getString(dbObj.hn);
            p.lname = rs.getString(dbObj.lname);
            p.locking = rs.getString(dbObj.locking);
            p.patient_id = rs.getString(dbObj.patient_id);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.vn = rs.getString(dbObj.vn);
            p.visit_type = rs.getString(dbObj.visit_type);
            p.prefix = rs.getString(dbObj.prefix);
            p.patient_allergy = rs.getString(dbObj.patient_allergy);
            p.labstatus = rs.getString(dbObj2.labstatus);
            p.order_id = rs.getString("visit_queue_order");
            p.specimen_code = rs.getString("visit_queue_secret_code");
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public Vector veQuery1(String sql) throws Exception
    {
        ListTransfer p;
        Vector list = new Vector();
        
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ListTransfer();
            p.assign_time = rs.getString(dbObj.assign_time);
            p.description = rs.getString(dbObj.description);
            p.fname = rs.getString(dbObj.fname);
            p.hn = rs.getString(dbObj.hn);
            p.lname = rs.getString(dbObj.lname);
            p.locking = rs.getString(dbObj.locking);
            p.name = rs.getString(dbObj.name);
            p.patient_id = rs.getString(dbObj.patient_id);
            // p.queue_visit = rs.getString(dbObj.queue_visit);
            p.color = rs.getString(dbObj.color);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.vn = rs.getString(dbObj.vn);
            p.visit_type = rs.getString(dbObj.visit_type);
            p.queue = rs.getString(dbObj.queue);
            p.servicepoint_id = rs.getString(dbObj.servicepoint_id);
            p.patient_allergy = rs.getString(dbObj.patient_allergy);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    
    
    public Vector veQuery(String sql) throws Exception
    {
        ListTransfer p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ListTransfer();
            p.assign_time = rs.getString(dbObj.assign_time);
            p.description = rs.getString(dbObj.description);
            p.fname = rs.getString(dbObj.fname);
            p.hn = rs.getString(dbObj.hn);
            p.lname = rs.getString(dbObj.lname);
            p.locking = rs.getString(dbObj.locking);
            p.name = rs.getString(dbObj.name);
            p.patient_id = rs.getString(dbObj.patient_id);
            //p.queue_visit = rs.getString(dbObj.queue_visit);
            p.color = rs.getString(dbObj.color);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.vn = rs.getString(dbObj.vn);
            p.queue = rs.getString(dbObj.queue);
            p.visit_type = rs.getString(dbObj.visit_type);
            p.servicepoint_id = rs.getString(dbObj.servicepoint_id);
            p.patient_allergy = rs.getString(dbObj.patient_allergy);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    public Vector veQueryWard(String sql) throws Exception
    {
        ListTransfer p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ListTransfer();
            p.assign_time = rs.getString(dbObj.assign_time);
            //p.description = rs.getString(dbObj.description);
            p.prefix = rs.getString(dbObj.prefix);
            p.fname = rs.getString(dbObj.fname);
            p.hn = rs.getString(dbObj.hn);
            p.lname = rs.getString(dbObj.lname);
            p.locking = rs.getString(dbObj.locking);
            //p.name = rs.getString(dbObj.name);
            p.patient_id = rs.getString(dbObj.patient_id);
            //p.queue_visit = rs.getString(dbObj.queue_visit);
            //p.color = rs.getString(dbObj.color);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.vn = rs.getString(dbObj.vn);
            p.bed = rs.getString(dbObj.bed);
            //p.visit_type = rs.getString(dbObj.visit_type);
            //p.queue = rs.getString(dbObj.queue);
            p.patient_allergy = rs.getString(dbObj2.patient_allergy);
            p.labstatus = rs.getString(dbObj2.labstatus);
            p.doctor_dx = rs.getString(dbObj2.doctor_dx);
            list.add(p);
        }
        rs.close();
        return list;
    } 
    
    /**
     *  ใช้ในการ list ผู้ป่วยที่อยู่ในจุดบริการ โดยจะส่งข้อมูลเข้า
     *
     */
    public Vector selectByTypeDate(String choose,String date_from,String date_to)  throws Exception
    {
        date_to = DateUtil.addDay(date_to,1);
        String SQL = "select t_patient.patient_drugallergy,t_visit.visit_locking,t_visit.visit_hn,t_visit.visit_vn,t_patient.f_patient_prefix_id,t_patient.patient_firstname,t_patient.patient_lastname,t_visit_queue_coding.assign_date_time,t_visit.t_visit_id,t_visit.t_patient_id,t_visit.f_visit_type_id,t_visit.visit_lab_status_id  " +
                " from t_visit_queue_coding,t_patient,t_visit " +
                " where t_visit.t_visit_id = t_visit_queue_coding.t_visit_id " +
                " and t_patient.t_patient_id = t_visit.t_patient_id " +
                " and t_visit.f_visit_type_id  like '" + choose + "' " + 
                " and assign_date_time > '"+ date_from +"'"+
                " and assign_date_time < '"+ date_to +"'"+
                " order by " + dbObj.assign_time;
     
        Vector v=veQueryLabXrayICD(SQL);
        
        if(v.size()==0)
            return null;
        else
            return v;
    }
    
}
