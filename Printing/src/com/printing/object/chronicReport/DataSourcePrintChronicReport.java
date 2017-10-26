/*

 * DataSourcePrintChronicReport.java

 *

 * Created on 08 ธันวาคม 2547, 15:18 น.

 */



package com.printing.object.chronicReport;

import net.sf.jasperreports.engine.*;



//import dori.jasper.engine.*;

import java.util.*;



/**

 *

 * @author ojika

 *  เป็นตัวที่จะแสดงข้อมูลลงบน Printing ที่มีการ Run หลายๆ Record

 */

public class DataSourcePrintChronicReport implements JRDataSource{

    

    /** Creates a new instance of DataSourceAppointmentList */

    private int index = -1;

    private Vector vdata;

    /**

     *  ใส่ค่า Vector ของ DataSource ของ Package Report_Order

     */

    public DataSourcePrintChronicReport(Vector data) 

    {

        vdata = data;

    }

    

    public Object getFieldValue(JRField jRField) throws JRException 

    {

        Object value = null;

		

        String fieldName = jRField.getName();



        if ("hn".equals(fieldName))

        {

                value = ((DataSource)vdata.get(index)).hn;

        }

        else if ("vn".equals(fieldName))

        {

                value = ((DataSource)vdata.get(index)).vn;

        }

        else if ("fname".equals(fieldName))

        {

                value = ((DataSource)vdata.get(index)).fname;

        }
        
        else if ("lname".equals(fieldName))

        {

                value = ((DataSource)vdata.get(index)).lname;

        }
        
        else if ("sex".equals(fieldName))

        {

                value = ((DataSource)vdata.get(index)).sex;

        }
        
        else if ("age".equals(fieldName))

        {

                value = ((DataSource)vdata.get(index)).age;

        }
        
        else if ("icd10".equals(fieldName))

        {

                value = ((DataSource)vdata.get(index)).icd10;

        }
        
        else if ("date_dx".equals(fieldName))

        {

                value = ((DataSource)vdata.get(index)).date_dx;

        }
        
        else if ("date_discharge".equals(fieldName))

        {

                value = ((DataSource)vdata.get(index)).date_discharge;

        }
        
        else if ("status".equals(fieldName))

        {

                value = ((DataSource)vdata.get(index)).status;

        }
        
        else if ("date_update".equals(fieldName))

        {

                value = ((DataSource)vdata.get(index)).date_update;

        }
        
        else if ("patient_address".equals(fieldName))

        {

                value = ((DataSource)vdata.get(index)).patient_address;

        }
      
        return value;

    }    

    public boolean next() throws JRException 

    {

        index++;

        return (index < vdata.size());

    }    

    

  

    

}