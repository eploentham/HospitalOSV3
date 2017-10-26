/*
 * DataSourcePrintDrugRx.java
 *
 * Created on 18 กรกฎาคม 2547, 19:34 น.
 */

package com.printing.object.DrugRx;
import net.sf.jasperreports.engine.*;

//import dori.jasper.engine.*;
import java.util.*;
/**
 *
 *  @author ojika
 *  เป็นตัวที่จะแสดงข้อมูลลงบน Printing ที่มีการ Run หลายๆ Record
 */
public class DataSourcePrintDrugRx implements JRDataSource{
    
    /** Creates a new instance of DataSourcePrintDrugRx */
    private int index = -1;
    private Vector vdata;
    /**
     *  ใส่ค่า Vector ของ DataSource ของ Package Report_Order
     */
    public DataSourcePrintDrugRx(Vector data) 
    {
        vdata = data;
    }
    
    public Object getFieldValue(JRField jRField) throws JRException 
    {
        Object value = null;
	
        String fieldName = jRField.getName();

        if ("drugAndDose".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).drugAndDose; 
        }
        else if ("execute".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).execute; 
        }
        else if ("verifyName".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).verifyName; 
        }
        return value;
    }
    
    public boolean next() throws JRException 
    {
        index++;
        return (index < vdata.size());
    }
    
   
    
}
