/*
 * RiskFactorDB.java
 *
 * Created on 10 กุมภาพันธ์ 2549, 16:47 น.
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
////////////////////////////////////////////////////////
/**
 *
 * @author amp
 */
public class RiskFactorDB
{
    public ConnectionInf theConnectionInf;
    public RiskFactor dbObj;
    final public String idtable = "277";
    
    /** Creates a new instance of RiskFactorDB */
    public RiskFactorDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new RiskFactor();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_patient_risk_factor";
        dbObj.pk_field = "t_patient_risk_factor_id";
        dbObj.patient_id = "t_patient_id";
        dbObj.topic = "patient_risk_factor_topic";
        dbObj.description = "patient_risk_factor_description";
        dbObj.staff_record = "patient_risk_factor_staff_record";
        dbObj.record_date_time ="patient_risk_factor_record_date_time";        
        return true;
    }
    
    public int insert(RiskFactor o) throws Exception
    {
        String sql="";
        RiskFactor p=o;
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
    
    public int update(RiskFactor o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        RiskFactor p=o;
        String field =""
        + "', " + dbObj.patient_id + "='" + p.patient_id
        + "', " + dbObj.topic + "='" + p.topic
        + "', " + dbObj.description + "='" + p.description
        + "', " + dbObj.staff_record + "='" + p.staff_record
        + "', " + dbObj.record_date_time + "='" + p.record_date_time        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
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
        + " where " + dbObj.patient_id
        + " = '" + patientId + "'";
        
        return eQuery(sql);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        RiskFactor p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new RiskFactor();
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
