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
public class DataSourceBeforMch implements JRDataSource
{
    private int index = -1;
    private Vector vdata;    
    /** Creates a new instance of DataSourceafterMchMother */
    public DataSourceBeforMch(Vector v) 
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
        if("dateshort".equals(fieldName))
        {
            value = ((AttributeBeforMch) vdata.get(index)).dateshort; 
        }
        else if("datelong".equals(fieldName))
        {
            value = ((AttributeBeforMch) vdata.get(index)).dateLong; 
        }
        else if ("weight".equals(fieldName))
        {
            value = ((AttributeBeforMch) vdata.get(index)).weight; 
        }
        else if ("pressure".equals(fieldName))
        {
            value = ((AttributeBeforMch) vdata.get(index)).pressure; 
        }
        else if ("urin".equals(fieldName))
        {
            value = ((AttributeBeforMch) vdata.get(index)).urin; 
        }
        else if ("alblumin".equals(fieldName))
        {
            value = ((AttributeBeforMch) vdata.get(index)).alblumin; 
        }
        else if ("uterus_level".equals(fieldName))
        {
            value = ((AttributeBeforMch) vdata.get(index)).uterus_level; 
        }
        else if ("hf".equals(fieldName))
        {
            value = ((AttributeBeforMch) vdata.get(index)).hf; 
        }
        else if ("cms".equals(fieldName))
        {
            value = ((AttributeBeforMch) vdata.get(index)).cms; 
        }
        else if ("conduct".equals(fieldName))
        {
            value = ((AttributeBeforMch) vdata.get(index)).conduct; 
        }
        else if ("sound_heart".equals(fieldName))
        {
            value = ((AttributeBeforMch) vdata.get(index)).sound_heart; 
        }
        else if ("moving_baby".equals(fieldName))
        {
            value = ((AttributeBeforMch) vdata.get(index)).moving_baby; 
        }
        else if ("ageofpregnant".equals(fieldName))
        {
            value = ((AttributeBeforMch) vdata.get(index)).ageofpregnant; 
        }
        else if ("highrisk".equals(fieldName))
        {
            value = ((AttributeBeforMch) vdata.get(index)).highrisk; 
        }
        else if ("result".equals(fieldName))
        {
            value = ((AttributeBeforMch) vdata.get(index)).result; 
        }
        else if ("resultdetail".equals(fieldName))
        {
            value = ((AttributeBeforMch) vdata.get(index)).resultdetail; 
        }
        else if ("executor".equals(fieldName))
        {
            value = ((AttributeBeforMch) vdata.get(index)).executor; 
        }
        else if ("place".equals(fieldName))
        {
            value = ((AttributeBeforMch) vdata.get(index)).place; 
        }
        else if ("sugar".equals(fieldName))
        {
            value = ((AttributeBeforMch) vdata.get(index)).sugar; 
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
