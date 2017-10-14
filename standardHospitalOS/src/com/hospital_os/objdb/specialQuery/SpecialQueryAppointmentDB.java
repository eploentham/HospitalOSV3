/*
 * SpecialQueryAppointmentDB.java
 *
 * Created on 29 ตุลาคม 2547, 11:11 น.
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
 * @author  tong
 */
public class SpecialQueryAppointmentDB
{
    /** Creates a new instance of SpecialQueryAppointmentDB */
    public ConnectionInf theConnectionInf;
    private SpecialQueryAppointment dbObj ;
    private String table1 = "t_patient";
    private String table2 = "t_patient_appointment";
    public SpecialQueryAppointmentDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new SpecialQueryAppointment();
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.patient_appointment_date = "patient_appointment_date";
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
        return true;
    }
    
    /**
     * from    to      sp      patientid
     *     1       1       1                   c1
     *     1       1       0                   c2
     *     0       0       1                   c3
     *     0       0       0                   c4
     */
    public Vector queryDataOrderbyDate(String datefrom, String dateto, String servicepointid,String patientid) throws Exception
    {
        String sql = "SELECT " +
        "t_patient.patient_hn," +
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
        "t_patient_appointment.patient_appointment " +
        "FROM (t_patient_appointment INNER JOIN t_patient ON t_patient_appointment.t_patient_id = t_patient.t_patient_id) INNER JOIN b_service_point ON t_patient_appointment.patient_appointment_clinic = b_service_point.b_service_point_id" ;
        if(patientid == null)
        {
            if(servicepointid != null)
            {
                if(datefrom !=null && dateto != null)
                {   /*c1*/
                    sql = sql +  " WHERE t_patient_appointment.patient_appointment_date >= '" + datefrom + "'" +
                    " AND t_patient_appointment.patient_appointment_date <= '" + dateto + "' " +
                    " AND t_patient_appointment.patient_appointment_clinic = '" + servicepointid + "' ";
                }
                else
                {   /*c3*/
                    sql = sql + " WHERE t_patient_appointment.patient_appointment_clinic = '" + servicepointid + "' ";
                }
            }
            else
            {
                if(datefrom !=null && dateto != null)
                {   /*c2*/
                    sql = sql + " WHERE t_patient_appointment.patient_appointment_date >= '" + datefrom + "'" +
                    " AND t_patient_appointment.patient_appointment_date <= '" + dateto + "' " ;
                }
                else
                {   /*c4*/
                }
            }
        }
        else
        {
            if(datefrom !=null && dateto != null)
            {   /*c5*/
                sql = sql +  " WHERE t_patient_appointment.patient_appointment_date >= '" + datefrom + "'" +
                " AND t_patient_appointment.patient_appointment_date <= '" + dateto + "' " +
                " AND t_patient_appointment.t_patient_id = '" + patientid + "' ";
            }
            else
            {
                sql = sql +  " WHERE t_patient_appointment.t_patient_id = '" + patientid + "' ";
            }
            
        }
        
        
        sql = sql + " order by "  +  dbObj.patient_appointment_date  + " , " + dbObj.service_point_description + " , " +dbObj.patient_hn;
        
        Vector vc =  eQuery(sql);
        if(vc.size() ==0)
            return  null;
        else
            return vc;
    }
    
    private Vector eQuery(String sql) throws Exception
    {
        SpecialQueryAppointment p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        int i = 0;
        while(rs.next())
        {
            p = new SpecialQueryAppointment();
            p.patient_appointment_date  = rs.getString(dbObj.patient_appointment_date);
            p.patient_firstname = rs.getString(dbObj.patient_firstname);
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
            
            list.add(p);
            p = null;
            
        }
        rs.close();
        return list;
    }
}
