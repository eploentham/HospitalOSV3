/*
 * QueueTransferDB.java
 *
 * Created on 1 พฤษภาคม 2548, 13:54 น.
 */

package com.hospital_os.objdb;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  tong
 */
public class QueueTransferDB {
    
    /** Creates a new instance of QueueTransferDB */
    public ConnectionInf theConnectionInf;
    public ListTransfer dbObj;
    final public String idtable = "272";/*"195";*/
    
    private  String SQL = "";
    
    public QueueTransferDB(ConnectionInf db) {
        
    theConnectionInf = db;
        dbObj = new ListTransfer();
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
        return true;
    }
    
    public int insert(ListTransfer o) throws Exception
    {
        String sql="";
        ListTransfer p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field  + " ,"
        + dbObj.assign_time  + " ,"
        + dbObj.description  + " ,"
        + dbObj.fname  + " ,"
        + dbObj.hn  + " ,"
        + dbObj.lname   + " ,"
        + dbObj.locking  + " ,"
        + dbObj.name  + " ,"
        + dbObj.color  + " ,"
        + dbObj.patient_id   + " ,"
        + dbObj.doctor  + " ,"
        + dbObj.visit_id  + " ,"
        + dbObj.vn  + " ,"
        + dbObj.queue  + " ,"
        + dbObj.visit_type  + " ,"
        + dbObj.servicepoint_id   + " ,"
        + dbObj.sex   + " ,"
        + dbObj.prefix   + " ,"
        + dbObj.patient_allergy + " "
       
        
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
        + p.patient_allergy 
        
        + "')";
        
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
        
        
        
    }
    
    public int delete(ListTransfer o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    public int deleteByVisitID(String visit_id) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.visit_id + "='" + visit_id +"'";
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
    public int updateTransferPatientAllergy(String patient_id) throws Exception
    {
         String sql = "UPDATE " + dbObj.table + "" +
        " set " + dbObj.patient_allergy + " = '1'" +
        " where " + dbObj.patient_id + "='" + patient_id +"'";
        return theConnectionInf.eUpdate(sql);
    }
    /**Update lock*/
    public int updateLock(ListTransfer listTransfer) throws Exception
    {
        String sql = "UPDATE " + dbObj.table + "" +
        " set " + dbObj.locking + " = '" + listTransfer.locking + "'" +
        " where " + dbObj.pk_field + "='" + listTransfer.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    /**Update lock*/
    public int updateLockByVisitID(String visit_id,String lock) throws Exception
    {
        String sql = "UPDATE " + dbObj.table + "" +
        " set " + dbObj.locking + " = '"+ lock +"'" +
        " where " + dbObj.visit_id + "='" + visit_id +"'";
       
        return theConnectionInf.eUpdate(sql);
    }
    /**Update transfer
     *  field Update  : visit_locking
     *                : service_point_description
     *                : assign_date_time
     *                : b_service_point_id
     *                : visit_service_staff_doctor
     */
    public int updateServiceTransfer(ListTransfer listTransfer) throws Exception
    {
        String sql = "UPDATE " + dbObj.table + "" +
        " set " + dbObj.locking + " = '" + listTransfer.locking + "'" +
        " , " + dbObj.assign_time + " = '" + listTransfer.assign_time + "'" +
        " , " + dbObj.servicepoint_id + " = '" + listTransfer.servicepoint_id + "'" +
        " , " + dbObj.doctor + " = '" + listTransfer.doctor + "'" +
        " , " + dbObj.visit_id + " = '" + listTransfer.visit_id + "'" +
        " , " + dbObj.name + " = '" + listTransfer.name + "'" +
        " where " + dbObj.pk_field + "='" + listTransfer.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    /**Update Drug Allergy when save DrugAllergy*/
    public int updateDrugAllergy(ListTransfer listTransfer) throws Exception 
    {
        String sql = "UPDATE " + dbObj.table + "" +
        " set " + dbObj.patient_allergy + " = '" + listTransfer.patient_allergy + "'" +
        " where " + dbObj.pk_field + "='" + listTransfer.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    /**Update sex when save Patient*/
    public int updatesexByVisitID(String visit_id,String sex,String prefix) throws Exception 
    {
        String sql = "UPDATE " + dbObj.table + "" +
        " set " + dbObj.sex + " = '" + sex + "'" +
        " , " + dbObj.prefix + " = '" + prefix + "'" +
        " where " + dbObj.visit_id + "='" + visit_id +"'";
        return theConnectionInf.eUpdate(sql);
    }
    /**
     *  ใช้ในการ list ผู้ป่วยที่อยู่ในจุดบริการ โดยจะส่งข้อมูลเข้า
     *  service_point_id = key หลักของตาราง โดยจะเป็น key หลักของตาราง service_point
     *  employee_id_doctor = key หลักของตาราง โดยจะเป็น key หลักของตาราง Employee เฉพาะจุดบริการที่เป็นห้องตรวจ
     *  choose = เลือกว่าเป็นผู้ป่วยใน หรือ ผู้ป่วยนอก
     */
    public Vector listTransferVisitQueueByServicePoint(String service_point_id,String employee_id_doctor,String choose)  throws Exception
    {
        SQL = "";
        SQL = "select * from "+ dbObj.table + " where";
        SQL = SQL + " " + dbObj.servicepoint_id + " like '"+ service_point_id + "'";
        if( !employee_id_doctor.trim().equalsIgnoreCase("") )
        {
            SQL = SQL + " and " + dbObj.doctor + " = '" + employee_id_doctor + "' ";
        }
        
        if( choose.equalsIgnoreCase(VisitType.IPD) || choose.equalsIgnoreCase(VisitType.OPD) )
        {
            SQL = SQL + " and " + dbObj.visit_type + " = '" + choose + "' ";
        }
        SQL = SQL + " ORDER BY " + dbObj.assign_time;
       
        Vector v=veQuery(SQL);
       
        if(v.size()==0)
            return null;
        else
            return v;
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
            list.add(p);
        }
        
        rs.close();
        return list;
    }
    
    public ListTransfer selectByVisitID(String visit_id) throws Exception
    {
        SQL = "";
        SQL = "select * from "+ dbObj.table 
        + " where " + dbObj.visit_id + "='" + visit_id +"'";
        
        Vector v=veQuery(SQL);
       
        if(v.size()==0)
            return null;
        else
            return (ListTransfer)v.get(0);
    }
    
    public ListTransfer selectByPatientID(String patient_id) throws Exception
    {
        SQL = "";
        SQL = "select * from "+ dbObj.table 
        + " where " + dbObj.patient_id + "='" + patient_id +"'";
        
        Vector v=veQuery(SQL);
       
        if(v.size()==0)
            return null;
        else
            return (ListTransfer)v.get(0);
    }
    
    public void createTableForBuild8()
    {
        SQL = "CREATE TABLE t_visit_queue_transfer (" +
        " t_visit_queue_transfer_id character varying(255) NOT NULL," +
        " patient_drugallergy character varying(255)," +
        " visit_locking character varying(255)," +
        " visit_hn character varying(255)," +
        " visit_vn character varying(255)," +
        " visit_service_staff_doctor character varying(255)," +
        " patient_firstname character varying(255)," +
        " patient_lastname character varying(255)," +
        " assign_date_time character varying(255)," +
        " service_point_description character varying(255)," +
        " t_patient_id character varying(255)," +
        " t_visit_id character varying(255)," +
        " f_visit_type_id character varying(255)," +
        " b_service_point_id character varying(255)," +
        " visit_queue_map_queue character varying(255)," +
        " visit_queue_setup_queue_color character varying(255)," +
        " visit_queue_setup_description character varying(255)," +
        " f_sex_id character varying(255)," +
        " f_patient_prefix_id character varying(255));";
        
        
         try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
        SQL = "ALTER TABLE ONLY t_visit_queue_transfer  ADD CONSTRAINT t_visit_queue_transfer_p PRIMARY KEY (t_visit_queue_transfer_id);";
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
        SQL = "CREATE INDEX b_service_point_id_idex ON t_visit_queue_transfer USING btree (b_service_point_id);";
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
        SQL = "CREATE INDEX t_patient_id_ide ON t_visit_queue_transfer USING btree (t_patient_id);";
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
        SQL = "CREATE INDEX t_visit_id_idex ON t_visit_queue_transfer USING btree (t_visit_id);";
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
        SQL = "CREATE INDEX t_visit_queue_transfer_index ON t_visit_queue_transfer USING btree (t_visit_queue_transfer_id);";
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
        SQL = "CREATE INDEX visit_service_staff_doctor ON t_visit_queue_transfer USING btree (visit_service_staff_doctor);";
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
    }
    
    public void createTableViewForBuild8()
    {
        
        SQL ="CREATE VIEW t_view_t_visit_service AS " +
        " SELECT visitintransect.patient_drugallergy, visitintransect.visit_locking, " +
        " visitintransect.visit_hn, visitintransect.visit_vn, visitintransect.visit_service_staff_doctor, " +
        " visitintransect.patient_firstname, visitintransect.patient_lastname, " +
        " visitintransect.assign_date_time, visitintransect.service_point_description, " +
        " visitintransect.t_patient_id, visitintransect.t_visit_id, visitintransect.f_visit_type_id, " +
        " visitintransect.b_service_point_id, visitintransect.f_sex_id, visitintransect.f_patient_prefix_id, " +
        " query1.visit_queue_map_queue, query1.visit_queue_setup_queue_color, " +
        " query1.visit_queue_setup_description FROM ((" +
        " SELECT t_patient.patient_drugallergy, " +
        " t_visit_service.visit_service_staff_doctor, " +
        " t_visit.visit_locking, t_visit.visit_hn, " +
        " t_visit.visit_vn, t_patient.patient_firstname, " +
        " t_patient.patient_lastname, t_visit_service.assign_date_time, " +
        " b_service_point.service_point_description, t_visit.t_patient_id, " +
        " t_visit.t_visit_id, t_visit.f_visit_type_id, " +
        " b_service_point.b_service_point_id, t_patient.f_sex_id, " +
        " t_patient.f_patient_prefix_id FROM t_patient, t_visit, " +
        " t_visit_service, b_service_point " +
        " WHERE ((((((t_visit.t_visit_id)::text = (t_visit_service.t_visit_id)::text) " +
        " AND (((t_visit_service.f_visit_service_status_id)::text = '2'::text) " +
        " OR ((t_visit_service.f_visit_service_status_id)::text = '1'::text))) " +
        " AND ((t_visit.f_visit_status_id)::text = '1'::text)) " +
        " AND ((t_patient.t_patient_id)::text = (t_visit.t_patient_id)::text)) " +
        " AND ((t_visit_service.b_service_point_id)::text = (b_service_point.b_service_point_id)::text))" +
        " ) visitintransect LEFT JOIN " +
        " (SELECT b_visit_queue_setup.visit_queue_setup_description, " +
        " b_visit_queue_setup.visit_queue_setup_queue_color, " +
        " t_visit_queue_map.visit_queue_map_queue, t_visit_queue_map.t_visit_id " +
        " FROM (t_visit_queue_map JOIN b_visit_queue_setup ON " +
        " (((t_visit_queue_map.b_visit_queue_setup_id)::text = (b_visit_queue_setup.b_visit_queue_setup_id)::text))) " +
        " WHERE ((t_visit_queue_map.visit_queue_map_active)::text = '1'::text)) query1 " +
        " ON (((visitintransect.t_visit_id)::text = (query1.t_visit_id)::text))) " +
        " ORDER BY visitintransect.assign_date_time;";
         try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
    }
    
    
    public void insertDataToTableForBuild8(String site)
    {
        SQL = " INSERT INTO t_visit_queue_transfer " +
        "  ( patient_drugallergy, " +
        "    visit_locking, " +
        "    visit_hn,  " +
        "    visit_vn,  " +
        "    visit_service_staff_doctor,  " +
        "    patient_firstname,  " +
        "     patient_lastname,  " +
        "    assign_date_time,  " +
        "    service_point_description,  " +
        "    t_patient_id,  " +
        "    t_visit_id,  " +
        "    f_visit_type_id,  " +
        "    b_service_point_id,  " +
        "    visit_queue_map_queue,  " +
        "    visit_queue_setup_description,  " +
        "    t_visit_queue_transfer_id,  " +
        "    visit_queue_setup_queue_color,  " +
        "    f_sex_id, f_patient_prefix_id  " +
        "   )" +
        " SELECT t_view_t_visit_service.patient_drugallergy, " +
        " t_view_t_visit_service.visit_locking, " +
        " t_view_t_visit_service.visit_hn, " +
        " t_view_t_visit_service.visit_vn, " +
        " t_view_t_visit_service.visit_service_staff_doctor, " +
        " t_view_t_visit_service.patient_firstname, " +
        " t_view_t_visit_service.patient_lastname, " +
        " t_view_t_visit_service.assign_date_time, " +
        " t_view_t_visit_service.service_point_description, " +
        " t_view_t_visit_service.t_patient_id, " +
        " t_view_t_visit_service.t_visit_id, " +
        " t_view_t_visit_service.f_visit_type_id, " +
        " t_view_t_visit_service.b_service_point_id, " +
        " t_view_t_visit_service.visit_queue_map_queue, " +
        " t_view_t_visit_service.visit_queue_setup_description, " +
        " ('272'||  float8('"+site+"' || '0000000000') +NEXTVAL('id')) AS Expr1," +
        " t_view_t_visit_service.visit_queue_setup_queue_color, " +
        " t_view_t_visit_service.f_sex_id, t_view_t_visit_service.f_patient_prefix_id" +
        " FROM t_view_t_visit_service;" ;
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
    }
    
    public void createSequenceForBuild8()
    {
        SQL = "create sequence id start 1;";
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
    }
    public void startSequenceForBuild8(String num)
    {
        SQL = "select setval('id',"+ num +");";
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
    }
    public void emptyTableForBuild8()
    {
        SQL = "delete from " + this.dbObj.table ;
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
    }
    public void dropSequenceForBuild8()
    {
        SQL = "DROP sequence id ;";
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
    }
    public void dropTableViewForBuild8()
    {
        SQL = "DROP VIEW t_view_t_visit_service;";
        try{
             theConnectionInf.eUpdate(SQL);
         }
         catch(Exception ex)
         {
             
         }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* TODO code application logic here*/
    }
    
}
