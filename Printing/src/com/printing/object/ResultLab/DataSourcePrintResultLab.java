/*
 * DataSourcePrintResultLab.java
 *
 * Created on 16 �Զع�¹ 2547, 17:19 �.
 */

package com.printing.object.ResultLab;
import net.sf.jasperreports.engine.*;

//import dori.jasper.engine.*;
import java.util.*;

/**
 *
 * @author ojika
 *  �繵�Ƿ����ʴ�������ŧ�� Printing ����ա�� Run ����� Record
 */
public class DataSourcePrintResultLab implements JRDataSource{
    
    /** Creates a new instance of DataSourcePrintResultLab */
    private int index = -1;
    private Vector vdata;
    /**
     *  ����� Vector �ͧ DataSource �ͧ Package Report_Order
     */
    public DataSourcePrintResultLab(Vector data) {
        vdata = data;
    }    
    
    public Object getFieldValue(JRField jRField) throws JRException 
    {
        Object value = null;
		
        String fieldName = jRField.getName();

        if ("labGroup".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).labGroup;
        }
        else if ("lab".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).lab;
        }
        else if ("labResult".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).labResult;
        }
        else if ("labUnit".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).labUnit;
        }
        else if ("dateOrderLab".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).dateOrderLab;
        }
        else if("dateResultLab".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).dateResultLab;
        }
        return value;
    }
    
    public boolean next() throws JRException 
    {
        index++;
        return (index < vdata.size());
    }
    
   
    
}
