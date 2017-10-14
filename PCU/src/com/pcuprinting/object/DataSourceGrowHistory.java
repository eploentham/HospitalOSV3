/*
 * DataSourceafterMchMother.java
 *
 * Created on 7 ����Ҿѹ�� 2549, 17:39 �.
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
public class DataSourceGrowHistory implements JRDataSource
{
    private int index = -1;
    private Vector vdata;    
    /** Creates a new instance of DataSourceafterMchMother */
    public DataSourceGrowHistory(Vector v) 
    {
        vdata = v;
    }
    /**
     * ��㹡����ҹ������е����
     * @param  
     * @return 
     * @author 
     * @date 
     */
    public Object getFieldValue(JRField jRField) throws JRException {
        Object value = null;	
        String fieldName = jRField.getName();
        if("datelong".equals(fieldName))
        {
            value = ((AttributeGrowHistory) vdata.get(index)).datelong; 
        }
        else if("dateshort".equals(fieldName))
        {
            value = ((AttributeGrowHistory) vdata.get(index)).dateshort; 
        }
        else if ("growdescription".equals(fieldName))
        {
            value = ((AttributeGrowHistory) vdata.get(index)).growdescription; 
        }
        else if ("standardgrow".equals(fieldName))
        {
            value = ((AttributeGrowHistory) vdata.get(index)).standardgrow; 
        }
        else if ("realgrow".equals(fieldName))
        {
            value = ((AttributeGrowHistory) vdata.get(index)).realgrow; 
        }
        else if ("notice".equals(fieldName))
        {
            value = ((AttributeGrowHistory) vdata.get(index)).notice; 
        }       
        return value;
    }
    /**
     * ��ҹ��ҵ�ǶѴ�
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
