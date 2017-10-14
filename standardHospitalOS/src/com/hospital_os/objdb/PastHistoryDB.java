/*
 * PastHistoryDB.java
 *
 * Created on 10 กุมภาพันธ์ 2549, 17:16 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.objdb;
import java.util.*;
import java.sql.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
import com.hospital_os.utility.*;
/**
 *
 * @author amp
 */
public class PastHistoryDB
{
    public ConnectionInf theConnectionInf;
    public PastHistory dbObj;
    final public String idtable = "280";
    
    /** Creates a new instance of PastHistoryDB */
    public PastHistoryDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PastHistory();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_patient_past_history";
        dbObj.pk_field = "t_patient_past_history_id";
        dbObj.patient_id = "t_patient_id";
        dbObj.date_desc = "patient_past_history_date_description";
        dbObj.topic = "patient_past_history_topic";
        dbObj.description = "patient_past_history_description";
        dbObj.staff_record = "patient_past_history_staff_record";
        dbObj.record_date_time ="patient_past_history_record_date_time";        
        return true;
    }
    
    public int insert(PastHistory o) throws Exception
    {
        String sql="";
        PastHistory p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.patient_id
        + " ,"	+ dbObj.topic
        + " ,"	+ dbObj.date_desc
        + " ,"	+ dbObj.description
        + " ,"	+ dbObj.staff_record
        + " ,"	+ dbObj.record_date_time        
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.patient_id
        + "','" + p.topic
        + "','" + p.date_desc
        + "','" + p.description
        + "','" + p.staff_record
        + "','" + p.record_date_time        
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(PastHistory p) throws Exception
    {
        String sql="update " + dbObj.table + " set "
                + dbObj.patient_id + "='" + p.patient_id
        + "', " + dbObj.topic + "='" + p.topic
        + "', " + dbObj.date_desc + "='" + p.date_desc
        + "', " + dbObj.description + "='" + p.description
        + "', " + dbObj.staff_record + "='" + p.staff_record
        + "', " + dbObj.record_date_time + "='" + p.record_date_time        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(RiskFactor o) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.pk_field + "='" + o.getObjectId() +"'";
        return theConnectionInf.eUpdate(sql);
    }
    public int deleteByPtid(String patient_id) throws Exception
    {
        String sql="delete from " + dbObj.table
        + " where " + dbObj.patient_id + "='" + patient_id +"'";
        return theConnectionInf.eUpdate(sql);
    }
    
    public Vector selectByPatientId(String patientId) throws Exception
    {
        String sql="select * from " + dbObj.table
        + " where " + dbObj.patient_id + " = '" + patientId + "'";
        return eQuery(sql);
        
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        PastHistory p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PastHistory();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.patient_id = rs.getString(dbObj.patient_id);
            p.topic = rs.getString(dbObj.topic);
            p.date_desc = rs.getString(dbObj.date_desc);
            p.description = rs.getString(dbObj.description);
            p.staff_record = rs.getString(dbObj.staff_record);
            p.record_date_time = rs.getString(dbObj.record_date_time);            
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
