/*
 * DataSourceReportSumOrderItem.java
 *
 * Created on 21 พฤษภาคม 2547, 12:54 น.
 */

package com.printing.object.Report_OrderGroup;
import net.sf.jasperreports.engine.*;

//import dori.jasper.engine.*;
import java.util.*;
/**
 *
 * @author  tong
 *  เป็นตัวที่จะแสดงข้อมูลลงบน Printing ที่มีการ Run หลายๆ Record
 */
public class DataSourceReportSumOrderItemGroup implements JRDataSource
{
    
    /** Creates a new instance of DataSourceReportSumOrderItem */
    private int index = -1;
    private Vector vdata;
    /**
     *  ใส่ค่า Vector ของ DataSource ของ Package Report_Order
     */
    public DataSourceReportSumOrderItemGroup(Vector data) {
        vdata = data;
    }
    
    public Object getFieldValue(JRField jRField) throws JRException {
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
        else if ("Num".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).num;
        }
        return value;
    
    }
    
    public boolean next() throws JRException {
        index++;
        return (index < vdata.size());
    }
    
}
