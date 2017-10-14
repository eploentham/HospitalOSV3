/*
 * RPCostDrugInServicePointDB.java
 *
 * Created on 20 ตุลาคม 2548, 18:17 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.objdb;
import com.generalreport.object.*;
import com.generalreport.utility.Language;

import com.hospital_os.usecase.connection.ConnectionInf;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author tong(Padungrat)
 */
public class RPCostDrugInServicePointDB {
    
    public ConnectionInf theConnectionInf;
    Vector vc;
    OrderItem theOrderItem,ObjectOrderItem;
    Employee theEmployee;
    Visit theVisit;
    ServicePoint theServicePoint;
    ServicePoint theServicePoint1;
    Payment thePayment;
    OrderItemDrug theOrderItemDrug;
    Plan thePlan;
    
    String SQL = "";
    ResultSet rs = null;
    ResultSetMetaData metadata;
    java.util.Vector vDrugDispense ;
    Vector vString;
    Vector vData;
    int language =1;
    public RPCostDrugInServicePointDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        initObject();
    
    }
    /**
     *  ใช้ในการ new Object ของข้อมูลที่จะใช้ในการ ทำ SQL
     */
    private void initObject()
    {
        theOrderItem = new OrderItem();
        theEmployee = new Employee();
        theVisit = new Visit();
        theServicePoint = new ServicePoint();
        theServicePoint1 = new ServicePoint();
        thePayment = new Payment();
        theOrderItemDrug = new OrderItemDrug();
        thePlan = new Plan();
                
        theOrderItem.setInitDataFieldName();
        theEmployee.setInitDataFieldName();
        theVisit.setInitDataFieldName();
        theServicePoint.setInitDataFieldName();
        theServicePoint1.setInitDataFieldName();
        thePayment.setInitDataFieldName();
        theOrderItemDrug.setInitDataFieldName();
        thePlan.setInitDataFieldName();        
    }
     public Vector queryCostDrugInServicePointByDate(String startDate,String finishDate,String startTime,String finishTime)
    {   
        vc  =null;
        try {
             SQL = " SELECT  b_contract_plans.contract_plans_description AS contact_plan, " +
                   " t_visit.visit_vn AS vn_an_number, " +
                   " t_order.order_common_name AS common_name, " +  
                   "  sum(to_number(t_billing_invoice_item.billing_invoice_item_patient_share_ceil,'999999.99') " +
                   "+ to_number(t_billing_invoice_item.billing_invoice_item_payer_share_ceil,'999999.99')) AS TotalPrice, " +
                   " substr(b_service_point2.service_point_description,4) AS order_staff_execute, " +  
                   " substr(b_service_point1.service_point_description,4) AS order_staff_dispense " + 

                   " FROM " + 
		   " t_visit INNER JOIN t_order " +
		   " ON t_visit.t_visit_id = t_order.t_visit_id " +
		   " INNER JOIN t_billing_invoice_item " +
		   " ON (t_order.t_order_id = t_billing_invoice_item.t_order_item_id " + 
                        " and t_billing_invoice_item.billing_invoice_item_active = '1') " +
		   " INNER JOIN t_visit_payment " +
		   " ON t_billing_invoice_item.t_payment_id = t_visit_payment.t_visit_payment_id " +
		   " INNER JOIN b_contract_plans " +
                   " ON t_visit_payment.b_contract_plans_id=b_contract_plans.b_contract_plans_id " +
                   " INNER JOIN b_employee AS b_employee1 " + 
                   " ON b_employee1.b_employee_id = t_order.order_staff_dispense " +
                   " INNER JOIN b_service_point AS b_service_point1 " +  
                   " ON b_employee1.b_service_point_id = b_service_point1.b_service_point_id " +
                   " INNER JOIN b_employee AS b_employee2 " +
                   " ON b_employee2.b_employee_id = t_order.order_staff_execute " +  
                   " INNER JOIN b_service_point AS b_service_point2 " + 
                   " ON b_employee2.b_service_point_id = b_service_point2.b_service_point_id " +
                   " WHERE ( " +
                   " t_order.f_item_group_id = '1' " + 
                   " AND (t_order.f_order_status_id = '2' " +  
                   " OR t_order.f_order_status_id = '5') " +
                   " AND t_visit.f_visit_status_id <> '4' " +
                   " AND ((SubString(t_order.order_dispense_date_time,0,11) Between '"+ startDate + "' And '" + finishDate + "') " +  
                   " AND (SubString(t_order.order_dispense_date_time,12,5) Between '" + startTime + "' And '" + finishTime + "'))) " +  
                   " GROUP BY  b_contract_plans.contract_plans_description, " +  
                   " t_visit.visit_vn, " +   
                   " t_order.order_common_name, " +
                   " b_service_point1.service_point_description, " +  
                   " b_service_point2.service_point_description  " +
                   " ORDER BY  b_contract_plans.contract_plans_description ";
            
            System.out.println("SQL : " + SQL);
            rs = theConnectionInf.eQuery(SQL);
            vc = getData(rs);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
            if(vc == null)
            {
                vc = null;
            }
        }
        return vc;
     }
     
     private Vector getData(ResultSet resultset) throws Exception
    {
        int column = 0;
        String[] rowdata = null;
        String[] columnname = null;
        vString =  null;
        vData = new Vector();
        //ตรวจสอบค่า resultset
        if(rs!= null)
        {
            //ทำการรับข้อมูลส่วนหัว field
            metadata = rs.getMetaData();
            //นับจำนวน column
            column = metadata.getColumnCount();
            //init array ให้มีจำนวน เท่ากับ column
            columnname = new String[column];
            vString = new Vector();
            //ทำการให้ค่าชื่อ field ที่ get มา
            for(int i = 0 ; i < column;i++)
            {
                columnname[i] = metadata.getColumnLabel(i+1);
                columnname[i] = Language.getTextBundle(columnname[i].toUpperCase(),language);
            }
            //ทำการให้ค่าของ field กับข้อมูล
            while(rs.next())
            {   rowdata = new String[column];
                for(int i = 0 ; i < column;i++)
                {
                    if(rs.getObject(i+1) != null)
                    {
                        rowdata[i] = rs.getString(i+1);      
                    }
                }
                vString.add(rowdata);
            }
            vData.add(columnname);
            vData.add(vString);  
        }
        return vData;
    }
    
}
