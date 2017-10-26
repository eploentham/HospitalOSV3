/*
 * DataSourceReportSumOrderItem.java
 *
 * Created on 21 พฤษภาคม 2547, 12:54 น.
 */

package com.printing.object.Report_Order;
import net.sf.jasperreports.engine.*;
//import dori.jasper.engine.*;
import java.util.*;
/**
 *
 * @author  tong
 *  เป็นตัวที่จะแสดงข้อมูลลงบน Printing ที่มีการ Run หลายๆ Record
 */
public class DataSourceReportSumOrderItem implements JRDataSource
{
    
    /** Creates a new instance of DataSourceReportSumOrderItem */
    private int index = -1;
    private Vector vdata;
    /**
     *  ใส่ค่า Vector ของ DataSource ของ Package Report_Order
     */
    public DataSourceReportSumOrderItem(Vector data) {
        vdata = data;
    }
    
    public Object getFieldValue(net.sf.jasperreports.engine.JRField jRField) throws net.sf.jasperreports.engine.JRException {
        Object value = null;
		
        String fieldName = jRField.getName();

        if ("Detail".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).detail;
        }        
        else if ("Price".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).price;
        }
        else if ("Value".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).value;
        }
        else if ("Date_Order".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).date_order;
        }
        else if ("Num".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).num;
        }
        return value;
    
    }
    
    public boolean next() throws net.sf.jasperreports.engine.JRException {
        index++;
        return (index < vdata.size());
    }
    
    
    
}
