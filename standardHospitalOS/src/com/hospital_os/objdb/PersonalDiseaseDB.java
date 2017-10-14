/*
 * PersonalDiseaseDB.java
 *
 * Created on 10 กุมภาพันธ์ 2549, 17:20 น.
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
public class PersonalDiseaseDB
{
    public ConnectionInf theConnectionInf;
    public PersonalDisease dbObj;
    final public String idtable = "279";
    
    /** Creates a new instance of PersonalDiseaseDB */
    public PersonalDiseaseDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new PersonalDisease();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table = "t_patient_personal_disease";
        dbObj.pk_field = "t_patient_personal_disease_id";
        dbObj.patient_id = "t_patient_id";
        dbObj.description = "patient_personal_disease_description";
        dbObj.sick_date = "patient_personal_disease_sick_date";
        dbObj.staff_record = "patient_personal_disease_staff_record";
        dbObj.record_date_time ="patient_personal_disease_record_date_time";        
        return true;
    }
    
    public int insert(PersonalDisease o) throws Exception
    {
        String sql="";
        PersonalDisease p=o;
        p.generateOID(idtable);
        sql="insert into " + dbObj.table + " ("
        + dbObj.pk_field
        + " ,"	+ dbObj.patient_id
        + " ,"	+ dbObj.description
        + " ,"	+ dbObj.sick_date
        + " ,"	+ dbObj.staff_record
        + " ,"	+ dbObj.record_date_time        
        + " ) values ('"
        + p.getObjectId()
        + "','" + p.patient_id
        + "','" + p.description
        + "','" + p.sick_date       
        + "','" + p.staff_record
        + "','" + p.record_date_time        
        + "')";
        sql = Gutil.convertSQLToMySQL(sql,theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int update(PersonalDisease o) throws Exception
    {
        String sql="update " + dbObj.table + " set ";
        PersonalDisease p=o;
        String field =""
        + "', " + dbObj.patient_id + "='" + p.patient_id
        + "', " + dbObj.description + "='" + p.description
        + "', " + dbObj.sick_date + "='" + p.sick_date        
        + "', " + dbObj.staff_record + "='" + p.staff_record
        + "', " + dbObj.record_date_time + "='" + p.record_date_time        
        + "' where " + dbObj.pk_field + "='" + p.getObjectId() +"'";
        sql = Gutil.convertSQLToMySQL(sql+field.substring(2),theConnectionInf.gettypeDatabase());
        return theConnectionInf.eUpdate(sql);
    }
    
    public int delete(PersonalDisease o) throws Exception
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
        PersonalDisease p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new PersonalDisease();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.patient_id = rs.getString(dbObj.patient_id);
            p.description = rs.getString(dbObj.description);
            p.sick_date = rs.getString(dbObj.sick_date);
            p.staff_record = rs.getString(dbObj.staff_record);
            p.record_date_time = rs.getString(dbObj.record_date_time);            
            list.add(p);
        }
        rs.close();
        return list;
    }
    
}
