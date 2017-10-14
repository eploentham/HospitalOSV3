/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reportcenter.utility;

import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class PrintShowReportLog {
    public String id;
    public String date_time;
    public String ip_address;
    public String employee_firstname;
    public String file_name;
    public String file_export_name;
    public String during;
    


public PrintShowReportLog(){
    config();
}
public void config(){
    id ="id";
    date_time="date_time";
    ip_address ="ip_address";
    employee_firstname="employee_firstname";
    file_name="file_name";
    file_export_name="file_export_name";
    during ="during";
    
}

public Vector rs2Vector(ResultSet rs) throws Exception
    {
        Vector list = new Vector();
        while(rs.next())
        {
            PrintShowReportLog p = new PrintShowReportLog();
            p.id = rs.getString(id);
            p.date_time = rs.getString(date_time);
            p.ip_address = rs.getString(ip_address);
            p.employee_firstname = rs.getString(employee_firstname);
            p.file_name = rs.getString(file_name);
            p.file_export_name = rs.getString(file_export_name);
            p.during = rs.getString(during);
            
            list.add(p);
        }
        rs.close();
        return list;
    }

}
