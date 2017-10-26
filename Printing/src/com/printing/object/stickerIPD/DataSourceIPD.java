/*
 * DataSourceStickerIPD.java
 *
 * Created on 12 æƒ…¿“§¡ 2548, 19:05 π.
 */

package com.printing.object.stickerIPD;
import net.sf.jasperreports.engine.*;

//import dori.jasper.engine.*;
import java.util.*;
/**
 *
 * @author  ojika
 */
public class DataSourceIPD implements JRDataSource
{
    
    /** Creates a new instance of stickerIPD */
    private int index = -1;
    private Vector vdata;
    public DataSourceIPD(Vector data) {
        vdata = data;
    }
    
    public Object getFieldValue(JRField jRField) throws JRException {
        Object value = null;
		
        String fieldName = jRField.getName();

        if ("fname".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).ffname;
        }
        else if ("lname".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).flname;
        }
        else if ("hn".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fhn;
        }
        else if ("sex".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fsex;
        }
        else if ("prefix".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fprefix;
        }
        else if ("an".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fan;
        }
        else if ("doctor".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fdoctor;
        }
        else if ("birthday".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fbirthday;
        }
        else if ("age".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fage;
        }
        else if ("age_long".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fage_long;
        }
        else if ("ward".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fward;
        }
        else if ("date_admit".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fdate_admit;
        }
        else if ("time_admit".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).ftime_admit;
        }
        
        return value;
    
    }
    
    public boolean next() throws JRException {
        index++;
        return (index < vdata.size());
    }
    
   
    
}
