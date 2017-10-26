/*
 * DataSourcePrintVisitSlip.java
 *
 * Created on 19 มิถุนายน 2547, 17:37 น.
 */

package com.printing.object.VisitSlip;
import net.sf.jasperreports.engine.*;
//import dori.jasper.engine.*;
import java.util.*;

/**
 *
 * @author ojika
 *  เป็นตัวที่จะแสดงข้อมูลลงบน Printing ที่มีการ Run หลายๆ Record
 */

 public class DataSourcePrintVisitSlip implements JRDataSource{
    
    /** Creates a new instance of DataSourcePrintVisitSlip */
    private int index = -1;
    private Vector vdata;
    /**
     *  ใส่ค่า Vector ของ DataSource ของ Package Report_Order
     **/
    public DataSourcePrintVisitSlip(Vector data) 
    {
        vdata = data;
    }
    
    public Object getFieldValue(net.sf.jasperreports.engine.JRField jRField) throws net.sf.jasperreports.engine.JRException 
    {
        Object value = null;
		
        String fieldName = jRField.getName();

        if ("hospital".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).hospital;
        }
        else if ("clinic".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).clinic;
        }
        else if ("receiveCard".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).receiveCard;
        }
        else if ("pn".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).pn;
        }
        else if ("name".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).name;
        }
        else if ("age".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).age;
        }
        else if ("pid".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).pid;
        }
        else if ("hn".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).hn;
        }
        else if ("plan".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).plan;
        }
        else if ("planCode".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).planCode;
        }
        else if ("mainHospital".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).mainHospital;
        }
        else if ("bp".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).bp;
        }
        else if ("hr".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).hr;
        }
        else if ("r".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).r;
        }
        else if ("t".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).t;
        }
        else if ("bw".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).bw;
        }
        else if ("ht".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).ht;
        }
        else if ("history".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).history;
        }
        else if ("mainSymptom".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).mainSymptom;
        }
        else if ("current".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).current;
        }
        else if ("dx".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).dx;
        }
        else if ("icd".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).icd;
        }
        else if ("lab".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).lab;
        }
        else if ("xray".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).xray;
        }
        else if ("rx".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).rx;
        }
        else if ("doctor".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).doctor;
        }
        return value;
    }
    
    public boolean next() throws net.sf.jasperreports.engine.JRException 
    {
        index++;
        return (index < vdata.size());
    }
    
    
    
}
