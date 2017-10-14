/*
 * FamilyHistoryDB.java
 *
 * Created on 10 ����Ҿѹ�� 2549, 17:16 �.
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
public class FamilyHistoryDB
{
    public ConnectionInf theConnectionInf;
    public FamilyHistory dbObj;
    final public String idtable = "278";
    
    /** Creates a new instance of FamilyHistoryDB */
    public FamilyHistoryDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new FamilyHistory();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_patient_family_history";
        dbObj.pk_field = "t_patient_family_history_id";
        dbObj.patient_id = "t_patient_id";
        dbObj.topic = "patient_family_history_topic";
        dbObj.description = "patient_family_history_description";
        dbObj.staff_record = "patient_family_history_staff_record";
        dbObj.record_date_time ="patient_family_history_record_date_time";        
        return true;
    }
    
    public int insert(FamilyHistory p) throws Exception
    {
        String sql="";
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.patient_id
        + " ,"	+ dbObj.topic
        + " ,"	+ dbObj.description
        + " ,"	+ dbObj.staff_record
        + " ,"	+ dbObj.record_date_time        
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.patient_id
        + "','" + p.topic
        + "','" + p.description
        + "','" + p.staff_record
        + "','" + p.record_date_time        
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(FamilyHistory p) throws Exception
    {
        String sql="update " + dbObj.table + " set "
                + dbObj.patient_id + "='" + p.patient_id
        + "', " + dbObj.topic + "='" + p.topic
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
        FamilyHistory p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new FamilyHistory();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.patient_id = rs.getString(dbObj.patient_id);
            p.topic = rs.getString(dbObj.topic);
            p.description = rs.getString(dbObj.description);
            p.staff_record = rs.getString(dbObj.staff_record);
            p.record_date_time = rs.getString(dbObj.record_date_time);            
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
