/*
 * SpecialPatientDB.java
 *
 * Created on 25 มกราคม 2548, 09:58 น.
 */
package com.hospital_os.objdb.specialQuery;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.specialQuery.*;
import com.hospital_os.object.Authentication;
import com.hospital_os.object.OrderStatus;
import com.hospital_os.utility.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  ojika
 */
public class SpecialEmployeeDB
{
    /** Creates a new instance of SpecialPatientDB */
    public ConnectionInf theConnectionInf;
    public SpecialEmployeeDB(ConnectionInf db)
    {
        theConnectionInf = db;
    }
    /*////////////////////////////////////////////////////////////////////////////*/
    public String queryByEmployeeName(String visit_id) throws Exception
    {
        String sql = " SELECT " +
        " b_employee.employee_firstname " +
        " || ' ' || " +
        " b_employee.employee_lastname AS EMPLOYEE_NAME " +
        " FROM " +
        " t_visit_service " +
        ",b_employee " +
        " WHERE " +
        " t_visit_service.t_visit_id = '"+visit_id+"' " +
        " AND b_employee.b_employee_id = t_visit_service.visit_service_staff_doctor " +
        " ORDER BY " +
        " t_visit_service.visit_service_finish_date_time DESC " +
        " LIMIT 1 ";
        
        
        return eQuery(sql);
    }
    
    public Vector queryStaffDoctorVerifyOrder(String visit_id) throws Exception
    {
        String sql = "SELECT b_employee.b_employee_id, b_employee.employee_firstname, b_employee.employee_lastname " +
        " FROM b_employee INNER JOIN t_order ON b_employee.b_employee_id = t_order.order_staff_verify" +
        " WHERE (" +
        "     ((t_order.f_order_status_id)<>'" + OrderStatus.DIS_CONTINUE + "'" +
        "   And (t_order.f_order_status_id)<>'" + OrderStatus.NOT_VERTIFY + "')" +
        " AND ((b_employee.f_employee_authentication_id)='" + Authentication.DOCTOR + "') " +
        " AND ((t_order.t_visit_id)='"+ visit_id + "')" +
        " )" +
        " GROUP BY b_employee.b_employee_id, b_employee.employee_firstname, b_employee.employee_lastname";
        
        return eQueryStaffDoctor(sql);
    }
    
    private Vector eQueryStaffDoctor(String sql ) throws Exception
    {
        ComboFix cf = null;
        Vector vc = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {    cf = new ComboFix();
             cf.name = rs.getString(2) + " " + rs.getString(3);
             cf.code = rs.getString(1);
             vc.add(cf);
             cf = null;
        }
        cf = null;
        
        rs.close();
        rs = null;
        return vc;
    }
    
    /*////////////////////////////////////////////////////////////////////////////*/
    private String eQuery(String sql) throws Exception
    {
        String employee_name = "";
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            employee_name = new String();
            
            employee_name  = rs.getString("EMPLOYEE_NAME");
            
        }
        rs.close();
        return employee_name;
    }
    public Vector getDoctorInServiceDoctor(String staff_doctor_id) throws Exception
    {
        String sql = "SELECT b_employee.b_employee_id, b_employee.employee_firstname, b_employee.employee_lastname " +
        " FROM b_service_point_doctor ,b_employee  where " +
        " b_service_point_doctor.service_point_doctor_staff = b_employee.b_employee_id " +
        "and b_service_point_doctor.b_service_point_id = '" + staff_doctor_id + "'";
        Vector vc = veQuery(sql);
        if(vc.size() ==0)
            return null;
        else
            return vc;
    }
    
    private Vector veQuery(String sql) throws Exception
    {
        ComboFix p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new ComboFix();
            p.code = rs.getString(1);
            p.name = rs.getString(2)+ " " + rs.getString(3);
            list.add(p);
        }
        rs.close();
        return list;
    }
}
