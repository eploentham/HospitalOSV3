/*
 * QueueTransfer2DB.java
 *
 * Created on 11 สิงหาคม 2548, 18:56 น.
 */

package com.hosv3.objdb;
import com.hospital_os.objdb.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  kingland
 */
public class QueueTransfer2DB extends QueueTransferDB{
    

    public ListTransfer dbObj2;
    /** Creates a new instance of QueueTransfer2DB */
    public QueueTransfer2DB(ConnectionInf db) {
        super(db);
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_visit_queue_transfer";
        dbObj.pk_field = "t_visit_queue_transfer_id";        
        dbObj.assign_time = "assign_date_time";
        dbObj.description = "visit_queue_setup_description";
        dbObj.fname = "patient_firstname";
        dbObj.hn = "visit_hn";
        dbObj.lname = "patient_lastname";
        dbObj.locking = "visit_locking";
        dbObj.name = "service_point_description";
        dbObj.color = "visit_queue_setup_queue_color";
        dbObj.patient_id = "t_patient_id";
        dbObj.doctor = "visit_service_staff_doctor";
        dbObj.visit_id = "t_visit_id";
        dbObj.vn = "visit_vn";
        dbObj.queue = "visit_queue_map_queue";
        dbObj.visit_type = "f_visit_type_id";
        dbObj.servicepoint_id = "b_service_point_id";
        dbObj.patient_allergy = "patient_drugallergy";
        dbObj.sex = "f_sex_id";
        dbObj.prefix = "f_patient_prefix_id";
        
        dbObj2 = new ListTransfer();
        dbObj2.table = "t_visit_queue_transfer";
        dbObj2.pk_field = "t_visit_queue_transfer_id";        
        dbObj2.assign_time = "assign_date_time";
        dbObj2.description = "visit_queue_setup_description";
        dbObj2.fname = "patient_firstname";
        dbObj2.hn = "visit_hn";
        dbObj2.lname = "patient_lastname";
        dbObj2.locking = "visit_locking";
        dbObj2.name = "service_point_description";
        dbObj2.color = "visit_queue_setup_queue_color";
        dbObj2.doctor = "visit_service_staff_doctor";
        dbObj2.visit_id = "t_visit_id";
        dbObj2.patient_id = "t_patient_id";
        dbObj2.vn = "visit_vn";
        dbObj2.queue = "visit_queue_map_queue";
        dbObj2.visit_type = "f_visit_type_id";
        dbObj2.servicepoint_id = "b_service_point_id";
        dbObj2.patient_allergy = "patient_drugallergy";
        dbObj2.sex = "f_sex_id";
        dbObj2.prefix = "f_patient_prefix_id";
        dbObj2.labstatus = "visit_queue_transfer_lab_status";
        return true;
    }
    
  
    public int insert(ListTransfer p) throws Exception
    {
        String sql="";
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj2.pk_field  + " ,"
        + dbObj2.assign_time  + " ,"
        + dbObj.description  + " ,"
        + dbObj2.fname  + " ,"
        + dbObj2.hn  + " ,"
        + dbObj2.lname   + " ,"
        + dbObj.locking  + " ,"
        + dbObj2.name  + " ,"
        + dbObj2.color  + " ,"
        + dbObj2.patient_id   + " ,"
        + dbObj2.doctor  + " ,"
        + dbObj2.visit_id  + " ,"
        + dbObj2.vn  + " ,"
        + dbObj2.queue  + " ,"
        + dbObj2.visit_type  + " ,"
        + dbObj2.servicepoint_id   + " ,"
        + dbObj2.sex   + " ,"
        + dbObj2.prefix   + " ,"
        + dbObj2.patient_allergy + " ,"
        + dbObj2.labstatus + " "
       
        
        + " ) values ('"
        + p.getObjectId() + "','"
        + p.assign_time  + "','"
        + p.description  + "','"
        + p.fname  + "','"
        + p.hn  + "','"
        + p.lname   + "','"
        + p.locking  + "','"
        + p.name  + "','"
        + p.color  + "','"
        + p.patient_id   + "','"
        + p.doctor  +  "','"
        + p.visit_id  + "','"
        + p.vn  + "','"
        + p.queue  + "','"
        + p.visit_type  + "','"
        + p.servicepoint_id   + "','"
        + p.sex   + "','"
        + p.prefix   + "','"
        + p.patient_allergy + "','"
        + p.labstatus 
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    public int updateMapQueueTransferByVisitID(ListTransfer o) throws Exception
    {
        String sql = "UPDATE " + dbObj.table + "" +
        " set " + dbObj.color + " = '" +o.color + "'" +
        " , " + dbObj.description + " = '" + o.description+ "'" +
        " , " + dbObj.queue + " = '" + o.queue + "'" +
        " where " + dbObj.visit_id + "='" + o.visit_id +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    
     public int update(ListTransfer p) throws Exception
    {
        String sql="update " + dbObj2.table + " set "
        + dbObj2.fname + "='" + p.fname + "', "
        + dbObj2.description + "='" + p.description + "', "
        + dbObj2.assign_time + "='" + p.assign_time + "', "
        + dbObj2.vn + "='" + p.vn + "', "
        + dbObj2.visit_id + "='" + p.visit_id + "', "
        + dbObj2.doctor + "='" + p.doctor + "', "
        + dbObj2.patient_id + "='" + p.patient_id + "', "
        + dbObj2.color + "='" + p.color + "', "
        + dbObj2.name + "='" + p.name + "', "
        + dbObj2.locking + "='" + p.locking + "', "
        + dbObj2.lname + "='" + p.lname + "', "
        + dbObj2.hn + "='" + p.hn + "', "
        + dbObj2.patient_allergy + "='" + p.patient_allergy + "', "
        + dbObj2.prefix + "='" + p.prefix + "', "
        + dbObj2.sex + "='" + p.sex + "', "
        + dbObj2.servicepoint_id + "='" + p.servicepoint_id + "', "
        + dbObj2.visit_type + "='" + p.visit_type + "', "
        + dbObj2.labstatus + "='" + p.labstatus + "', "
        + dbObj2.queue + "='" + p.queue +"'"
        + " where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(ListTransfer o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }    
    public int updateTransferPatientDenyAllergy(String patient_id,String allergy)
    throws Exception{
         String sql = "UPDATE " + dbObj.table + ""
        + " set " + dbObj.patient_allergy + " = '" + allergy + "'"
        + " where " + dbObj.patient_id + "='" + patient_id + "'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector listTransferVisitQueueByServicePoint(String service_point_id
    ,String employee_id_doctor,String choose)  throws Exception
    {
        String SQL = "select * from "+ dbObj.table + " where";
        SQL = SQL + " " + dbObj.servicepoint_id + " like '"+ service_point_id + "'";
        
        if(!employee_id_doctor.trim().equals("")) {
            SQL = SQL + " and " + dbObj.doctor + " = '" + employee_id_doctor + "' ";
        }
        if(choose.equals(VisitType.IPD) || choose.equals(VisitType.OPD)) {
            SQL = SQL + " and " + dbObj.visit_type + " = '" + choose + "' ";
        }
        SQL = SQL + " order by " + dbObj.assign_time;
        Vector v=veQuery(SQL);
        if(v.size()==0)return null;
        else           return v;
    }    
    
    public Vector selectAll() throws Exception
    {
        String SQL = "select * from "+ dbObj.table + 
        " order by " + dbObj.assign_time;
        return veQuery(SQL);
    }
    public Vector selectIPD() throws Exception
    {
        String SQL = "select * from "+ dbObj.table + 
        " where " + dbObj.vn + " like '1%' " + 
        " order by " + dbObj.assign_time;
        return veQuery(SQL);
    }
    
    public Vector veQuery(String sql) throws Exception
    {
        ListTransfer p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ListTransfer();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.assign_time = rs.getString(dbObj.assign_time);
            p.description = rs.getString(dbObj.description);
            p.fname = rs.getString(dbObj.fname);
            p.hn = rs.getString(dbObj.hn);
            p.lname = rs.getString(dbObj.lname);
            p.locking = rs.getString(dbObj.locking);
            p.name = rs.getString(dbObj.name);
            p.patient_id = rs.getString(dbObj.patient_id);
            p.doctor = rs.getString(dbObj.doctor);
            p.color = rs.getString(dbObj.color);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.vn = rs.getString(dbObj.vn);
            p.visit_type = rs.getString(dbObj.visit_type);
            p.queue = rs.getString(dbObj.queue);
            p.servicepoint_id = rs.getString(dbObj.servicepoint_id);
            p.patient_allergy = rs.getString(dbObj.patient_allergy);
            p.sex = rs.getString(dbObj.sex);
            p.prefix = rs.getString(dbObj.prefix);
            p.labstatus = rs.getString(dbObj2.labstatus);
            list.add(p);
        }
        
        rs.close();
        return list;
    }
     
    public ListTransfer select2ByVisitID(String visit_id) throws Exception
    {
        String SQL = "";
        SQL = "select * from "+ dbObj.table 
        + " where " + dbObj.visit_id + "='" + visit_id +"'";
        
        Vector v=veQuery(SQL);
       
        if(v.size()==0)
            return null;
        else
            return (ListTransfer)v.get(0);
    }
    
    public int updateLockByVid(String vid) throws Exception
    {
        String sql = "UPDATE " + dbObj.table + ""
        + " set " + dbObj.locking + " = '" + 0 + "'"
        + " where " + dbObj.visit_id + "='" + vid
        + "'";
        return theConnectionInf.eUpdate(sql); 
    }
    
    /**
     @Author: tuk
     *@date: 08/08/2549
     *@see: update คำนำหน้าชื่อในคิว Transfer
     *@param: patient id และ คำนำหน้าชื่อ
     */
    public int updatePrefix(String patient_id, String prefix_id) throws Exception
    {
        String sql = "UPDATE " + dbObj.table + ""
        + " set " + dbObj.prefix + " = '" + prefix_id + "'"
        + " where " + dbObj.patient_id + "='" + patient_id
        + "'";
        return theConnectionInf.eUpdate(sql); 
    }
}
