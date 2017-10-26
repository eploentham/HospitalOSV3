/*
 * DataSourcePrintBillingReport.java
 *
 * Created on 28 �á�Ҥ� 2547, 9:18 �.
 */

package com.printing.object.BillingReport;
import net.sf.jasperreports.engine.*;
//import dori.jasper.engine.*;
import java.util.*;

/**
 *
 *  @author ojika
 *  �繵�Ƿ����ʴ�������ŧ�� Printing ����ա�� Run ����� Record
 */
public class DataSourcePrintBillingReport implements JRDataSource{
    
    /** Creates a new instance of DataSourcePrintBillingReport */
    private int index = -1;
    private Vector vdata;
    /**
     *  ����� Vector �ͧ DataSource �ͧ Package BillingReport
     */
    public DataSourcePrintBillingReport(Vector data) 
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
        else if ("vn".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).vn;
        }
        else if ("name".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).name;
        }
        else if ("date".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).date;
        }
        else if ("price".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).price;
        }
        else if ("plan".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).plan;
        }
        
        return value;
    }
    
    public boolean next() throws net.sf.jasperreports.engine.JRException
    {
        index++;
        return (index < vdata.size());
    }
    
    
}
