/*
 * SpecialPatientReportDB.java
 *
 * Created on 29 มกราคม 2548, 23.42 น.
 */
package com.hospital_os.objdb.specialQuery;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.specialQuery.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  ojika
 *  ใช้ในการ query รายงาน ผู้ป่วยที่ผ่านจุดบริการ
 */
public class SpecialPatientReportDB
{
    public ConnectionInf theConnectionInf;
    public SpecialPatientReport dbObj;
    /** Creates a new instance of BillingReportDB */
    public SpecialPatientReportDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new SpecialPatientReport();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.fname = "fname";
        dbObj.lname = "lname";
        dbObj.hn = "hn";
        dbObj.vn = "vn";
        dbObj.servicepoint = "servicepoint";
        dbObj.sex = "sex";
        return true;
    }
    
    public Vector PatientReport(String startDate,String endDate, String servicepoint)throws Exception
    {
        Vector vc = new Vector();
        String sql = " SELECT " +
        " DISTINCT t_patient.patient_hn AS HN " +
        " ,t_visit.visit_vn AS VN " +
        " ,t_patient.patient_firstname AS NAME " +
        " ,t_patient.patient_lastname AS SURNAME " +
        " ,substring(b_service_point.service_point_description,4) AS SERVICE_POIN " +
        " ,t_patient.f_sex_id AS SEX " +
        " FROM " +
        " t_patient " +
        " ,t_visit " +
        " ,t_visit_service " +
        " ,b_service_point " +
        " WHERE " +
        " t_visit.t_patient_id = t_patient.t_patient_id  " +
        " AND t_visit.t_visit_id = t_visit_service.t_visit_id " +
        " AND t_visit.f_visit_status_id <> '4' " +
        " AND t_visit_service.b_service_point_id = b_service_point.b_service_point_id " +
        " AND t_visit_service.b_service_point_id = '"+servicepoint+"' " +
        " AND (substring(t_visit_service.assign_date_time,0,17) >= '"+startDate+"' " +
        " AND substring(t_visit_service.assign_date_time,0,17) <= '"+endDate+"' )" +
        " ORDER BY " +
        " HN,VN";
        
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    private Vector eQuery(String sql) throws Exception
    {
        SpecialPatientReport p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new SpecialPatientReport();
            p.hn = rs.getString(1);
            p.vn = rs.getString(2);
            p.fname = rs.getString(3);
            p.lname = rs.getString(4);
            p.servicepoint = rs.getString(5);
            
            p.sex = rs.getString(6);
            
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    
    
}
