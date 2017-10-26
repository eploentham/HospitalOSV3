/*
 * DataSourceAppointmentList.java
 *
 * Created on 10 มิถุนายน 2547, 12:48 น.
 */

package com.printing.object.AppointmentList;
import net.sf.jasperreports.engine.*;
//import dori.jasper.engine.*;
import java.util.*;

/**
 *
 *  @author ojika
 *  เป็นตัวที่จะแสดงข้อมูลลงบน Printing ที่มีการ Run หลายๆ Record
 */
public class DataSourcePrintAppointmentList implements JRDataSource{
    
    /** Creates a new instance of DataSourceAppointmentList */
    private int index = -1;
    private Vector vdata;
    /**
     *  ใส่ค่า Vector ของ DataSource ของ Package Report_Order
     */
    public DataSourcePrintAppointmentList(Vector data) 
    {
        vdata = data;
    }
    
    public Object getFieldValue(net.sf.jasperreports.engine.JRField jRField) throws net.sf.jasperreports.engine.JRException
    {
        Object value = null;
		
        String fieldName = jRField.getName();

        if ("hn".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).hn;
        }
        else if ("name".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).name;
        }
        else if ("app_date".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).app_date;
        }
        else if ("app_time".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).app_time;
        }
        else if ("app_type".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).app_type;
        }
        else if ("serviceAppoint".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).serviceAppoint;
        }
        
        return value;
    }
    
    public boolean next() throws net.sf.jasperreports.engine.JRException 
    {
        index++;
        return (index < vdata.size());
    }
    
    
    
}
