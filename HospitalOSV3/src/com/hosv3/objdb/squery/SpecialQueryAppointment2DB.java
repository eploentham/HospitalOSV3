/*
 * SpecialQueryAppointmentDB.java
 *
 * Created on 29 ตุลาคม 2547, 11:11 น.
 */
package com.hosv3.objdb.squery;

import com.hospital_os.usecase.connection.*;
import com.hosv3.object.squery.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  tong
 */
public class SpecialQueryAppointment2DB 
{
    /** Creates a new instance of SpecialQueryAppointmentDB */
    public ConnectionInf theConnectionInf;
    private SpecialQueryAppointment2 dbObj; 
    public SpecialQueryAppointment2DB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new SpecialQueryAppointment2();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.patient_appointment_date = "patient_appointment_date";
        dbObj.patient_prefix = "f_patient_prefix_id";
        dbObj.patient_firstname = "patient_firstname";
        dbObj.patient_hn = "patient_hn";
        dbObj.patient_lastname = "patient_lastname";
        dbObj.service_point_description = "service_point_description";
        dbObj.t_patient_appointment_id = "t_patient_appointment_id";
        dbObj.t_patient_id = "t_patient_id";
        dbObj.t_visit_id = "t_visit_id";
        dbObj.patient_appointment_auto_visit = "patient_appointment_auto_visit";
        dbObj.b_visit_queue_setup_id = "b_visit_queue_setup_id";
        dbObj.patient_appointment_time = "patient_appointment_time";
        dbObj.patient_appointment = "patient_appointment";
        dbObj.patient_appointment_status = "patient_appointment_status";
        return true;
    }
    
    /**
     * from    to      sp      patientid
     *     1       1       1                   c1
     *     1       1       0                   c2
     *     0       0       1                   c3
     *     0       0       0                   c4
     */
    public Vector queryDataOrderbyDateHN(boolean all_period,String datefrom, String dateto
    , String servicepointid,String patientid,String appointstatus,String active) throws Exception
    {
        String sql = "SELECT " +
        "t_patient.patient_hn," +
        "t_patient.f_patient_prefix_id, " +
        "t_patient.patient_firstname, " +
        "t_patient.patient_lastname, " +
        "t_patient_appointment.patient_appointment_date, " +
        "b_service_point.service_point_description, " +
        "t_patient_appointment.t_patient_appointment_id, " +
        "t_patient_appointment.t_patient_id, " +
        "t_patient_appointment.t_visit_id, " +
        "t_patient_appointment.patient_appointment_auto_visit, " +
        "t_patient_appointment.b_visit_queue_setup_id, " +
        "t_patient_appointment.patient_appointment_time, " +
        "t_patient_appointment.patient_appointment, " +
        "t_patient_appointment.patient_appointment_status " +  //amp:9/8/2549      
        " FROM (t_patient_appointment" +
        " INNER JOIN t_patient ON t_patient_appointment.t_patient_id = t_patient.t_patient_id)" +
        " INNER JOIN b_service_point ON t_patient_appointment.patient_appointment_clinic = b_service_point.b_service_point_id" +
        " INNER JOIN f_appointment_status ON t_patient_appointment.patient_appointment_status = f_appointment_status.f_appointment_status_id" +        
        " WHERE true " ;

        if(!servicepointid.equals("0"))
            sql = sql + " AND t_patient_appointment.patient_appointment_clinic = '" + servicepointid + "' ";
        if(!appointstatus.equals(""))
            sql = sql + " AND t_patient_appointment.patient_appointment_status = '" + appointstatus + "' ";
        if(!all_period){   
            sql = sql + 
            " AND t_patient_appointment.patient_appointment_date >= '" + datefrom + "'" +
            " AND t_patient_appointment.patient_appointment_date <= '" + dateto + "' ";
        }
        if(active.equals("1"))
        {   
            sql = sql + 
            " AND t_patient_appointment.patient_appointment_active = '" + active + "'";
        }
        if(patientid != null && !patientid.equals(""))
            sql = sql + " AND t_patient_appointment.t_patient_id = '" + patientid + "' ";

        sql = sql + " order by " + dbObj.patient_appointment_date + " ," +dbObj.patient_hn + " limit 500 ";
        return eQuery(sql);
    }
    
    private Vector eQuery(String sql) throws Exception
    {
        SpecialQueryAppointment2 p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new SpecialQueryAppointment2();
            p.patient_appointment_date  = rs.getString(dbObj.patient_appointment_date);
            p.patient_firstname = rs.getString(dbObj.patient_firstname);
            p.patient_prefix = rs.getString(dbObj.patient_prefix);
            p.patient_hn = rs.getString(dbObj.patient_hn);
            p.patient_lastname = rs.getString(dbObj.patient_lastname);
            p.service_point_description = rs.getString(dbObj.service_point_description);
            p.t_patient_appointment_id = rs.getString(dbObj.t_patient_appointment_id);
            p.t_patient_id = rs.getString(dbObj.t_patient_id);
            p.t_visit_id = rs.getString(dbObj.t_visit_id);
            p.patient_appointment_auto_visit= rs.getString(dbObj.patient_appointment_auto_visit);
            p.b_visit_queue_setup_id= rs.getString(dbObj.b_visit_queue_setup_id);
            p.patient_appointment_time= rs.getString(dbObj.patient_appointment_time);
            p.patient_appointment= rs.getString(dbObj.patient_appointment);
            p.patient_appointment_status= rs.getString(dbObj.patient_appointment_status); //amp:9/8/2549
            list.add(p);
        }
        rs.close();
        return list;
    }
}
