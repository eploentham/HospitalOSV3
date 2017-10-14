/*
 * DataSourceafterMchMother.java
 *
 * Created on 7 กุมภาพันธ์ 2549, 17:39 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcuprinting.object;
import java.util.*;
import net.sf.jasperreports.engine.*;
/**
 * 
 * @author kingland
 */
public class DataSourceAfterMchMother implements JRDataSource
{
    private int index = -1;
    private Vector vdata;    
    /** Creates a new instance of DataSourceafterMchMother */
    public DataSourceAfterMchMother(Vector v) 
    {
        vdata = v;
    }
    /**
     * ใช้ในการอ่านค่าแต่ละตัวแปร
     * @param  
     * @return 
     * @author 
     * @date 
     */
    public Object getFieldValue(JRField jRField) throws JRException {
        Object value = null;	
        String fieldName = jRField.getName();
        if("date_long".equals(fieldName))
        {
            value = ((AttributeAfterMchMother) vdata.get(index)).date_long; 
        }
        else if ("date_digit".equals(fieldName))
        {
            value = ((AttributeAfterMchMother) vdata.get(index)).date_digit; 
        }
        else if ("date_short".equals(fieldName))
        {
            value = ((AttributeAfterMchMother) vdata.get(index)).date_short; 
        }
        else if ("bloodpressure".equals(fieldName))
        {
            value = ((AttributeAfterMchMother) vdata.get(index)).bloodpressure; 
        }
        else if ("uterus_level".equals(fieldName))
        {
            value = ((AttributeAfterMchMother) vdata.get(index)).uterus_level; 
        }
        else if ("lochia".equals(fieldName))
        {
            value = ((AttributeAfterMchMother) vdata.get(index)).lochia; 
        }
        else if ("cream".equals(fieldName))
        {
            value = ((AttributeAfterMchMother) vdata.get(index)).cream; 
        }
        else if ("executor".equals(fieldName))
        {
            value = ((AttributeAfterMchMother) vdata.get(index)).executor; 
        }
        else if ("place".equals(fieldName))
        {
            value = ((AttributeAfterMchMother) vdata.get(index)).place; 
        }
        else if ("hn".equals(fieldName))
        {
            value = ((AttributeAfterMchMother) vdata.get(index)).hn; 
        }
        else if ("pregnanttime".equals(fieldName))
        {
            value = ((AttributeAfterMchMother) vdata.get(index)).pregnanttime; 
        }
        else if ("caretime".equals(fieldName))
        {
            value = ((AttributeAfterMchMother) vdata.get(index)).caretime; 
        }
        return value;
    }
    /**
     * อ่านค่าตัวถัดไป
     * @param  
     * @return 
     * @author 
     * @date 
     */
    public boolean next() throws JRException 
    {
        index++;
        return (index < vdata.size());
    }
    
}
