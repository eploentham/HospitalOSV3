/*
 * RPCostPaymentDB.java
 *
 * Created on 6 ตุลาคม 2548, 14:56 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalreport.object.*;
import com.generalreport.utility.Language;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author americus
 */

public class RPCostPaymentDB
{
    public ConnectionInf theConnectionInf;
    Vector vc;
    Visit theVisit;
    Payment thePayment;
    Plan thePlan;
    BillingGroupItem theBillingGroupItem;
    BillingGroup theBillingGroup;
    OrderItem theOrderItem;
    
    String SQL = "";
    ResultSet rs = null;
    ResultSetMetaData metadata;
    java.util.Vector vDrugDispense ;
    Vector vString;
    Vector vData;
    int language = 1;
    /**
     * Creates a new instance of RPCostPaymentDB 
     */
    public RPCostPaymentDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theVisit = new Visit();
        thePayment = new Payment();
        thePlan = new Plan();
        theBillingGroupItem = new BillingGroupItem();
        theBillingGroup = new BillingGroup();
        theOrderItem = new OrderItem();
        
        theVisit.setInitDataFieldName();
        thePayment.setInitDataFieldName();
        thePlan.setInitDataFieldName();
        theBillingGroupItem.setInitDataFieldName();
        theBillingGroup.setInitDataFieldName();    
        theOrderItem.setInitDataFieldName();
    }
    
    public Vector queryCostPaymentByDate(String startDate,String finishDate)
    {   
        vc  =null;
        try
        {
                   SQL = "SELECT " +
                    "query1.contract_plans_description AS CONTACT_PLAN " +
                    ",SUM(CASE WHEN TRIM(query1.f_item_billing_group_id) = '05' " +
                               "THEN query1.totalPrice " +
                               " ELSE 0.00 " +
                    "END) AS DRUG_SUPPLY " +
                    ",SUM(CASE WHEN TRIM(query1.f_item_billing_group_id) = '00' " +
                                "THEN query1.totalPrice " +
                                "ELSE 0.00 " +
                    "END) AS LAB " +
                    ",SUM(CASE WHEN TRIM(query1.f_item_billing_group_id) = '01' " +
                                "THEN query1.totalPrice " +
                                "ELSE 0.00 " +
                    "END) AS XRAY " +
                    ",SUM(CASE WHEN TRIM(query1.f_item_billing_group_id) = '02' " +
                                "THEN query1.totalPrice " +
                                "ELSE 0.00 " +
                    "END) AS OTHER " +
                    ",SUM(CASE WHEN TRIM(query1.f_item_billing_group_id) = '03' " +
                                "THEN query1.totalPrice " +
                                "ELSE 0.00 " +
                    "END) AS OPERATING " +
                    ",SUM(CASE WHEN TRIM(query1.f_item_billing_group_id) = '04' " +
                                "THEN query1.totalPrice " +
                                "ELSE 0.00 " +
                    "END) AS OTHER_TREAT " +
                    ",SUM(CASE WHEN TRIM(query1.f_item_billing_group_id) = '06' " +
                                "THEN query1.totalPrice " +
                                "ELSE 0.00 " +
                    "END) AS ICU " +
                    ",SUM(CASE WHEN TRIM(query1.f_item_billing_group_id) = '07' " +
                                "THEN query1.totalPrice " +
                                "ELSE 0.00 " +
                    "END) AS ROOM " +
                    ",SUM(CASE WHEN TRIM(query1.f_item_billing_group_id) = '08' " +
                                "THEN query1.totalPrice " +
                                "ELSE 0.00 " +
                    "END) AS FOOD " +
                    ",SUM(CASE WHEN TRIM(query1.f_item_billing_group_id) = '09' " +
                                "THEN query1.totalPrice " +
                                "ELSE 0.00 " +
                    "END) AS OTHER_PAID " +
                    ",SUM(query1.totalPrice) AS TOTAL_PRICE " +
            "FROM " +
            "(SELECT " +
                   theVisit.tableName +"."+ theVisit.visit_status +
                   ", "+ thePayment.tableName +"."+ thePayment.priority +
                   ", "+ theOrderItem.tableName +"."+ theOrderItem.status +
                   ", "+ theVisit.tableName +"."+ theVisit.pk_table +
                   ", "+ theOrderItem.tableName +"."+ theOrderItem.pk_table +
                   ", "+ theOrderItem.tableName +"."+ theOrderItem.order_price +
                   ", "+ theOrderItem.tableName +"."+ theOrderItem.order_qty +
                   ",("+ theOrderItem.tableName +"."+ theOrderItem.order_price +" * "+ theOrderItem.tableName +"."+ theOrderItem.order_qty + ") AS totalPrice " +
                   ", "+ theBillingGroup.tableName +"."+theBillingGroup.pk_table +" AS f_item_billing_group_id"+
                   ", "+ thePlan.tableName +"."+ thePlan.description +" AS contract_plans_description"+
                   ", SUBSTRING("+ theVisit.tableName +"."+ theVisit.financial_discharge_time +",0,11) AS dateVisit " +
            "FROM " +
                                "((("+ theVisit.tableName +" INNER JOIN ("+ thePayment.tableName +" INNER JOIN "+ thePlan.tableName +
                                " ON "+ thePayment.tableName +"."+ thePayment.plan_kid +" = "+ thePlan.tableName +"."+ thePlan.pk_table +") " +
                                " ON "+ theVisit.tableName +"."+ theVisit.pk_table +" = "+ thePayment.tableName +"."+ thePayment.visit_id +") " +
                                " INNER JOIN "+ theOrderItem.tableName +
                                " ON "+ theVisit.tableName +"."+theVisit.pk_table +" = "+ theOrderItem.tableName +"."+ theOrderItem.visit_id +") " +
                                " INNER JOIN "+ theBillingGroupItem.tableName +
                                " ON "+ theOrderItem.tableName +"."+ theOrderItem.billing_subgroup_id +" = "+ theBillingGroupItem.tableName +"."+ theBillingGroupItem.pk_table + ") " +
                                " INNER JOIN " + theBillingGroup.tableName +
                                " ON "+ theBillingGroupItem.tableName +"."+ theBillingGroupItem.billing_group_id +" = "+ theBillingGroup.tableName +"."+ theBillingGroup.pk_table +
             " WHERE " +
                                "(("+ theVisit.tableName +"."+ theVisit.visit_status +" <>'4') " +
                                "AND ("+ thePayment.tableName +"."+ thePayment.priority +" = '0') " +
                                "AND (t_visit_payment.visit_payment_active = '1') " +
                                "AND (("+ theOrderItem.tableName +"."+ theOrderItem.status +" <>'0')  AND ("+ theOrderItem.tableName +"."+ theOrderItem.status +" <> '3') ) " +
                                "AND (SUBSTRING("+ theVisit.tableName +"."+ theVisit.financial_discharge_time +",0,11) Between " + "?" + " And " + "?" + ")) " +
                        ") AS query1 " +
            "GROUP BY " +
                        "query1.contract_plans_description " +
            "ORDER BY " +
                         "query1.contract_plans_description ";
            
            System.out.println(SQL);
           // rs = theConnectionInf.eQuery(SQL);//"SELECT * from t_patient limit 100");
            PreparedStatement pps = theConnectionInf.ePQuery(SQL);
            pps.setString(	1	,startDate);
            pps.setString(	2	,finishDate);
            rs = pps.executeQuery();
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
