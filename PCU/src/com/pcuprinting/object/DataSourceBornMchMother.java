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
public class DataSourceBornMchMother implements JRDataSource
{
    private int index = -1;
    private Vector vdata;    
    /** Creates a new instance of DataSourceafterMchMother */
    public DataSourceBornMchMother(Vector v) 
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
        if("hn".equals(fieldName))
        {
            value = ((AttributeBornMchMother) vdata.get(index)).pHN; 
        }
        else if ("bornplace".equals(fieldName))
        {
            value = ((AttributeBornMchMother) vdata.get(index)).pbornplace; 
        }
        else if ("executor".equals(fieldName))
        {
            value = ((AttributeBornMchMother) vdata.get(index)).pexecutor; 
        }
        else if ("methodgivebirth".equals(fieldName))
        {
            value = ((AttributeBornMchMother) vdata.get(index)).pmethodgivebirth; 
        }
        else if ("abnormalinpregnant".equals(fieldName))
        {
            value = ((AttributeBornMchMother) vdata.get(index)).pabnormalinpregnant; 
        }
        else if ("abnormalbeforpregnant".equals(fieldName))
        {
            value = ((AttributeBornMchMother) vdata.get(index)).pabnormalAfterpregnant; 
        } 
        else if ("naemplaceborn".equals(fieldName))
        {
            value = ((AttributeBornMchMother) vdata.get(index)).pnameplaceborn; 
        }
        else if ("lifenum".equals(fieldName))
        {
            value = ((AttributeBornMchMother) vdata.get(index)).lifeNum; 
        }
        else if ("pregnantseq".equals(fieldName))
        {
            value = ((AttributeBornMchMother) vdata.get(index)).pregnantSeq; 
        }
        else if ("borndatetime".equals(fieldName))
        {
            value = ((AttributeBornMchMother) vdata.get(index)).bornDateTime; 
        }
        else if ("pregnantage".equals(fieldName))
        {
            value = ((AttributeBornMchMother) vdata.get(index)).pregnantAge; 
        }
        else if ("deathnum".equals(fieldName))
        {
            value = ((AttributeBornMchMother) vdata.get(index)).deathNum; 
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
