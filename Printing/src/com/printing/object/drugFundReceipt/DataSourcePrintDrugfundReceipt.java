/*

 * DataSourcePrintDrugfundReceipt.java

 *

 * Created on 08 �ѹ�Ҥ� 2547, 15:18 �.

 */



package com.printing.object.drugFundReceipt;

import net.sf.jasperreports.engine.*;



//import dori.jasper.engine.*;

import java.util.*;



/**

 *

 * @author ojika

 *  �繵�Ƿ����ʴ�������ŧ�� Printing ����ա�� Run ����� Record

 */

public class DataSourcePrintDrugfundReceipt implements JRDataSource{

    

    /** Creates a new instance of DataSourceAppointmentList */

    private int index = -1;

    private Vector vdata;

    /**

     *  ����� Vector �ͧ DataSource �ͧ Package Report_Order

     */

    public DataSourcePrintDrugfundReceipt(Vector data) 

    {

        vdata = data;

    }

    

    public Object getFieldValue(JRField jRField) throws JRException 

    {

        Object value = null;

		

        String fieldName = jRField.getName();



        if ("drugfund_no".equals(fieldName))

        {

                value = ((DataSource)vdata.get(index)).drugfund_no;

        }

        else if ("drugfund_order".equals(fieldName))

        {

                value = ((DataSource)vdata.get(index)).drugfund_order;

        }

        else if ("drugfund_order_paid".equals(fieldName))

        {

                value = ((DataSource)vdata.get(index)).drugfund_order_paid;

        }
      
        return value;

    }    

    public boolean next() throws JRException 

    {

        index++;

        return (index < vdata.size());

    }    

    

  

    

}