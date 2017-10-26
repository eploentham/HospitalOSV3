/*
 * DataSourcePrintReceipt.java
 *
 * Created on 25 �Զع�¹ 2547, 16:39 �.
 */

package com.printing.object.Receipt;
import net.sf.jasperreports.engine.*;

//import dori.jasper.engine.*;
import java.util.*;

/**
 *
 * @author ojika
 *  �繵�Ƿ����ʴ�������ŧ�� Printing ����ա�� Run ����� Record
 */
public class DataSourcePrintReceipt implements JRDataSource{
    
    /** Creates a new instance of DataSourceAppointmentList */
    private int index = -1;
    private Vector vdata;
    /**
     *  ����� Vector �ͧ DataSource �ͧ Package Report_Order
     */
    public DataSourcePrintReceipt(Vector data) 
    {
        vdata = data;
    }
    
    public Object getFieldValue(JRField jRField) throws JRException 
    {
        Object value = null;
		
        String fieldName = jRField.getName();

        if ("no".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).no;
        }
        else if ("order".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).order;
        }
        else if ("open".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).open;
        }
        else if ("close".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).close;
        }
        
        return value;
    }
    
    public boolean next() throws JRException 
    {
        index++;
        return (index < vdata.size());
    }    
    
  
    
}