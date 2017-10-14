/*
 * RPPatientInServicePointDB.java
 *
 * Created on 12 ตุลาคม 2548, 15:10 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalreport.object.*;
import com.generalreport.utility.Language;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author americus
 */
public class RPPatientInServicePointDB
{
    public ConnectionInf theConnectionInf;
    Vector vc;
    
    String SQL = "";
    ResultSet rs = null;
    ResultSetMetaData metadata;
    java.util.Vector vPateintInservicePoint ;
    Vector vString;
    Vector vData;
    int language = 1;
    String unIdentifyID = "0000000000000";
    /**
     * Creates a new instance of RPPatientInServicePointDB 
     */
    public RPPatientInServicePointDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
    }
    
    public Vector queryPatientInServicePointByDate(String startDate,String finishDate,String servicepoint,String doctor)
    {   vc  =null;
        try
        {
            SQL = "SELECT " +
                    " b_service_point.service_point_description AS NAME_SERVICEPOINT" +
                    ", t_visit.visit_hn AS HN_NUMBER" +
                    ", t_visit.visit_vn AS VN_AN_NUMBER " +
                    ", f_patient_prefix.patient_prefix_description AS PREFIX" +
                    ", t_patient.patient_firstname AS PATIENT_NAME" +
                    ", t_patient.patient_lastname AS LASTNAME " +
                  "FROM " +
                    " ((t_visit INNER JOIN (t_visit_service INNER JOIN b_service_point " +
                    " ON t_visit_service.b_service_point_id = b_service_point.b_service_point_id)  " +
                    " ON t_visit.t_visit_id = t_visit_service.t_visit_id AND t_visit.f_visit_status_id <> '4') " +
                    " INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id) " +
                    " LEFT JOIN f_patient_prefix " +
                    " ON f_patient_prefix.f_patient_prefix_id = t_patient.f_patient_prefix_id " +
                   " WHERE " +
                     " (((substring(assign_date_time,0,11)) Between '" + startDate + "' And '" + finishDate + "') "; 
                    if(!"0".equals(servicepoint))
                     SQL = SQL + " AND ((b_service_point.b_service_point_id)='" + servicepoint + "') ";
                    if(doctor != null && (!doctor.equals(unIdentifyID)))
                    {
                       SQL = SQL + "AND ((t_visit_service.visit_service_staff_doctor)='" + doctor + "') " ;
                    }
                   SQL = SQL + ") " +
                   "GROUP BY " +
                     " b_service_point.service_point_description " +
                     ", t_visit.visit_hn, t_visit.visit_vn " +
                     ", f_patient_prefix.patient_prefix_description " +
                     ", t_patient.patient_firstname " +
                     ", t_patient.patient_lastname " +
                     ", b_service_point.b_service_point_id " +
                     ", t_visit_service.visit_service_staff_doctor " +
                    "ORDER BY " +
                    "t_visit_service.visit_service_staff_doctor DESC";
                     
            System.out.println(" queryPatientInServicePointByDate : " + SQL);
            rs = theConnectionInf.eQuery(SQL);
            vc = getData(rs);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(vc == null)
            {
                vc = null;
            }
        }
        return vc;
    }
    
    private Vector getData(ResultSet resultset) throws Exception
    {
        int column = 0;
        String[] rowdata = null;
        String[] columnname = null;
        vString =  null;
        vData = new Vector();
        //ตรวจสอบค่า resultset
        if(rs!= null)
        {
            //ทำการรับข้อมูลส่วนหัว field
            metadata = rs.getMetaData();
            //นับจำนวน column
            column = metadata.getColumnCount();
            //init array ให้มีจำนวน เท่ากับ column
            columnname = new String[column];
            vString = new Vector();
            //ทำการให้ค่าชื่อ field ที่ get มา
            for(int i = 0 ; i < column;i++)
            {
                columnname[i] = metadata.getColumnLabel(i+1);
                columnname[i] = Language.getTextBundle(columnname[i].toUpperCase(),language);
            }
            //ทำการให้ค่าของ field กับข้อมูล
            while(rs.next())
            {   rowdata = new String[column];
                for(int i = 0 ; i < column;i++)
                {
                    if(rs.getObject(i+1) != null)
                    {
                        rowdata[i] = rs.getString(i+1);
                    }
                }
                vString.add(rowdata);
            }
            vData.add(columnname);
            vData.add(vString);
        }
        return vData;
    }
    
}
