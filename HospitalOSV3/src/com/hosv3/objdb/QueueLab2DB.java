/*
 * QueueLab2DB.java
 *
 * Created on 22 กันยายน 2548, 10:22 น.
 */

package com.hosv3.objdb;
import java.util.*;
import java.sql.*;
import com.hospital_os.objdb.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hosv3.object.*;

/**
 *
 * @author  kingland
 */
public class QueueLab2DB extends QueueLabDB{
    
    public QueueLab2 dbObj2;
    /** Creates a new instance of QueueLab2DB */
    public QueueLab2DB(ConnectionInf db) {
        super(db);
    }
     public boolean initConfig()
    {
        dbObj.table="t_visit_queue_lab";
        dbObj.pk_field="t_visit_queue_lab_id";
        dbObj.visit_id   ="t_visit_id";
        dbObj.number_order   ="visit_queue_lab_number_order";
        dbObj.last_service   ="b_service_point_id";
        dbObj.assign_time   ="assign_date_time";
        dbObj.patient_id="t_patient_id";
        
        dbObj2 = new QueueLab2();        
        dbObj2.table="t_visit_queue_lab";
        dbObj2.pk_field="t_visit_queue_lab_id";
        dbObj2.visit_id   ="t_visit_id";
        dbObj2.number_order   ="visit_queue_lab_number_order";
        dbObj2.last_service   ="b_service_point_id";
        dbObj2.assign_time   ="assign_date_time";
        dbObj2.patient_id="t_patient_id";
        dbObj2.remain = "visit_queue_lab_remain";
        dbObj2.order_id = "visit_queue_order";
        dbObj2.secret_code = "visit_queue_secret_code";
        return true;
    }
     
     public int insert(QueueLab2 o) throws Exception
     {
        String sql="";
        QueueLab2 p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj2.pk_field  + " ,"
        + dbObj2.visit_id + " ,"
        + dbObj2.number_order + " ,"
        + dbObj2.last_service + " ,"
        + dbObj2.assign_time  + " ,"
        + dbObj2.patient_id  + " ,"
        + dbObj2.remain + " ,"
        + dbObj2.order_id + " ,"
        + dbObj2.secret_code + " "
        + " ) values ('"
        + p.getObjectId() + "','"
        + p.visit_id + "' ,'"
        + p.number_order + "' ,'"
        + p.last_service + "' ,'"
        + p.assign_time  + "' ,'"
        + p.patient_id + "' ,'"
        + p.remain + "' ,'"
        + p.order_id + "' ,'"        
        + p.secret_code
        + "')";
        
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
     
    public int update(QueueLab2 o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        QueueLab2 p=o;
        
        String field ="" + "', " +
        
        dbObj.visit_id + "='" + p.visit_id + "', "
        + dbObj2.number_order + "='" + p.number_order + "', "
        + dbObj2.last_service + "='" + p.last_service + "', "
        + dbObj2.assign_time  + "='" + p.assign_time + "', "
        + dbObj2.patient_id  + "='" + p.patient_id + "' ,"
        + dbObj2.remain  + "='" + p.remain + "' ,"
        + dbObj2.order_id  + "='" + p.order_id + "' ,"        
        + dbObj2.secret_code + "='" + p.secret_code + "' "       
        + " where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    } 
    
    public int delete(QueueLab2 o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
     
    public Vector eQuery(String sql) throws Exception
    {
        QueueLab2 p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new QueueLab2();
            p.setObjectId(rs.getString(dbObj2.pk_field));
            p.visit_id = rs.getString(dbObj2.visit_id);
            p.number_order = rs.getString(dbObj2.number_order);
            p.last_service = rs.getString(dbObj2.last_service);
            p.assign_time = rs.getString(dbObj2.assign_time);
            p.patient_id = rs.getString(dbObj2.patient_id);
            p.remain = rs.getString(dbObj2.remain);
            p.order_id = rs.getString(dbObj2.order_id);
            p.secret_code = rs.getString(dbObj2.secret_code);
            list.add(p);
        }
        rs.close();
        return list;
    }
    public QueueLab2 select2ByVisitID(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id
        + " = '" + pk + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (QueueLab2)v.get(0);
    }
    public QueueLab2 selectByPatientID(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.patient_id
        + " = '" + pk + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (QueueLab2)v.get(0);
    }
 
    /**
     *@Author: henbe
     *@date : 03/03/2549
     *@see: เลือกคิวผู้ป่วย ให้ค้นจาก remain ด้วยนะ
     */
    public QueueLab2 selectByVidRmnOid(String visit_id,String remain,String orderId) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj2.visit_id + " = '" + visit_id
        + "' and "  + dbObj2.remain + " = '" + remain
        + "' and " + dbObj2.order_id + " = '" + orderId + "'";
        Vector v=eQuery(sql);        
        if(v.size()==0)
            return null;
        else
            return (QueueLab2)v.get(0);
    }    
    /**
     *@Author: amp
     *@date : 03/03/2549
     *@see: เลือกคิวผู้ป่วย
     */
    public QueueLab2 select2ByVisitIDAndOrderId(String visitId,String orderId) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj2.visit_id
        + " = '" + visitId + "' and "
        + dbObj2.order_id + " = '" + orderId + "'";
        
        Vector v=eQuery(sql);
        if(v.size()==0)
            return null;
        else
            return (QueueLab2)v.get(0);
    }
    
    public Vector selectAllByVisitID(String pk) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.visit_id
        + " = '" + pk + "'";
        
        Vector v=eQuery(sql);
        return v;
    }
    
    public int deleteByVisitID(String visit_id,String qremain) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.visit_id + "='" + visit_id +"'"
        + " and " + dbObj2.remain + "='" + qremain +"'"
                //henbe modify เพื่อใม่ให้มันไปลบคิวของ แลบปกปิด
        + " and " + dbObj2.order_id + "=''";
        return theConnectionInf.eUpdate(sql);
    }
        
     /**
      *@Author : amp
      *@date : 08/03/2549
      *@see : ลบคิวแลป
      */
     public int deleteByOrderId(String visitID,String remain,String orderID) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj2.order_id + " = '" + orderID
        + "' and " + dbObj2.remain + " = '" + remain
        + "' and " + dbObj.visit_id + " = '" + visitID + "'";
        
        return theConnectionInf.eUpdate(sql);
    }
}
