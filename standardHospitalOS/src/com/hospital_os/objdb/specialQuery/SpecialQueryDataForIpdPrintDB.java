/*
 * SpecialQueryDataForIpdPrintDB.java
 *
 * Created on 16 ÁÔ¶Ø¹ÒÂ¹ 2548, 11:10 ¹.
 */
package com.hospital_os.objdb.specialQuery;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.specialQuery.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author nu_ojika
 *
 */
public class SpecialQueryDataForIpdPrintDB
{
    
    /** Creates a new instance of SpecialQueueVisitDB */
    public ConnectionInf theConnectionInf;
    private SpecialQueryDataForIpdPrint dbObj;
    public SpecialQueryDataForIpdPrintDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new SpecialQueryDataForIpdPrint();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.fname = "fname";
        dbObj.lname = "lname";
        dbObj.hn = "hn";
        dbObj.sex = "sex";
        dbObj.prefix = "prefix";
        dbObj.an = "an";
        dbObj.doctor = "doctor";
        dbObj.birthday = "birthday";
        dbObj.age = "age";
        dbObj.age_long = "age_long";
        dbObj.ward = "ward";
        dbObj.date_admit = "date_admit";
        dbObj.time_admit = "time_admit";
        return true;
    }
    
    public SpecialQueryDataForIpdPrint selectByVisitIDDoctorId(String visit_id,String doctor) throws Exception
    {        
            String sql = " SELECT " +
                  " f_patient_prefix.patient_prefix_description AS prefix " +
                  " , t_patient.patient_firstname AS fname " +
                  " , t_patient.patient_lastname AS lname " +
                  " , t_patient.patient_hn AS hn " +
                  " , f_sex.sex_description AS sex " +
                  " , t_visit.visit_vn AS an " +
                  " , t_patient.patient_birthday AS birthday " +
                  " , b_visit_ward.visit_ward_description AS ward " +
                  " , b_employee.employee_firstname || '  ' || employee_lastname AS doctor " +
                  " , substring(t_visit.visit_begin_admit_date_time,0,11)  AS date_admit " +
                  " , substr(t_visit.visit_begin_admit_date_time,12,5) AS time_admit " +
                  " FROM  " +
                  " ((t_patient INNER JOIN f_sex ON t_patient.f_sex_id = f_sex.f_sex_id)  " +
                  " INNER JOIN f_patient_prefix ON t_patient.f_patient_prefix_id = f_patient_prefix.f_patient_prefix_id)  " +
                  " INNER JOIN ((t_visit INNER JOIN b_visit_ward ON t_visit.b_visit_ward_id = b_visit_ward.b_visit_ward_id) " +
                  " LEFT JOIN b_employee ON b_employee.b_employee_id = t_visit.visit_patient_self_doctor)  " +
                  " ON t_patient.t_patient_id = t_visit.t_patient_id " +
                  " WHERE  " +
                  " t_visit.f_visit_type_id = '1' " +
                  " AND t_visit.t_visit_id = '" + visit_id + "' " +                  
                  " AND  t_visit.f_visit_status_id <> '4'";
                
        Vector vc =  eQuery(sql);
        if(vc.size() == 0)
            return null;
        else
            return (SpecialQueryDataForIpdPrint)vc.get(0);
    }
    
    private Vector eQuery(String sql) throws Exception
    {
        SpecialQueryDataForIpdPrint p;
        Vector vc = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        int i = 0;
        while(rs.next())
        {
            p = new SpecialQueryDataForIpdPrint();            
            p.fname = rs.getString(dbObj.fname);
            p.lname = rs.getString(dbObj.lname);
            p.hn = rs.getString(dbObj.hn);
            p.sex = rs.getString(dbObj.sex);
            p.an = rs.getString(dbObj.an);
            p.prefix = rs.getString(dbObj.prefix);
            p.doctor = rs.getString(dbObj.doctor);
            p.birthday = rs.getString(dbObj.birthday);
            p.ward = rs.getString(dbObj.ward);
            p.date_admit = rs.getString(dbObj.date_admit);
            p.time_admit = rs.getString(dbObj.time_admit);
            vc.add(p);            
        }
        rs.close();
        return vc;
    }
}
