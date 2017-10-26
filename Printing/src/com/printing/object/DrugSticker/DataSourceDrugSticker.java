/*
 * DrugSticker.java
 *
 * Created on 21 æƒ…¿“§¡ 2547, 16:46 π.
 */

package com.printing.object.DrugSticker;
import net.sf.jasperreports.engine.*;

//import dori.jasper.engine.*;
import java.util.*;
/**
 *
 * @author  tong
 */
public class DataSourceDrugSticker implements JRDataSource
{
    
    /** Creates a new instance of DrugSticker */
    private int index = -1;
    private Vector vdata;
    public DataSourceDrugSticker(Vector data) {
        vdata = data;
    }
    
    public Object getFieldValue(JRField jRField) throws JRException {
        Object value = null;
		
        String fieldName = jRField.getName();

        if ("Name".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fname;
        }
        else if ("VN".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fvn;
        }
        else if ("HN".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fhn;
        }
        else if ("Description".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fdescription;
        }
        else if ("Instruction".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).finstructiom;
        }
        else if ("Frequency".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).ffrequency;
        }
        else if ("Page".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fpage;
        }
        else if ("Date".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fdate;
        }
        else if ("Common".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fcommon;
        }
        else if ("Dose".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fdose;
        }
        else if ("Dose_Uom".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fdose_uom;
        }
        else if ("Qty".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fqty;
        }
        else if ("Qty_Uom".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fqty_uom;
        }
        else if ("Cautiond".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fcaution;
        }
        else if ("time".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).ftime;
        }
        else if ("DoseSpecial".equals(fieldName))
        {
                value = ((DataSource)vdata.get(index)).fdosespecial;
        }
        return value;
    
    }
    
    public boolean next() throws JRException {
        index++;
        return (index < vdata.size());
    }
    
   
    
}
