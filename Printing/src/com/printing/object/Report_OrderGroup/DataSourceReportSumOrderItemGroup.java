/*
 * DataSourceReportSumOrderItem.java
 *
 * Created on 21 ����Ҥ� 2547, 12:54 �.
 */

package com.printing.object.Report_OrderGroup;
import net.sf.jasperreports.engine.*;

//import dori.jasper.engine.*;
import java.util.*;
/**
 *
 * @author  tong
 *  �繵�Ƿ����ʴ�������ŧ�� Printing ����ա�� Run ����� Record
 */
public class DataSourceReportSumOrderItemGroup implements JRDataSource
{
    
    /** Creates a new instance of DataSourceReportSumOrderItem */
    private int index = -1;
    private Vector vdata;
    /**
     *  ����� Vector �ͧ DataSource �ͧ Package Report_Order
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
