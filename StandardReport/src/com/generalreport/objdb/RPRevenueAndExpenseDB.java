/*
 * RPRevenueAndExpenseDB.java
 *
 * Created on 11 ตุลาคม 2548, 15:48 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.objdb;

import java.util.*;
import java.sql.*;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalreport.object.CashInFlow;

/**
 *
 * @author amp
 */
public class RPRevenueAndExpenseDB
{
    public ConnectionInf theConnectionInf;
    private CashInFlow dbObj;
        
    /** Creates a new instance of RPRevenueAndExpenseDB */
    public RPRevenueAndExpenseDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf; 
        dbObj = new CashInFlow();        
        initConfig();
    }
    
    public boolean initConfig()
    {        
        dbObj.patient_prefix_description = "patient_prefix_description";
        dbObj.patient_firstname = "patient_firstname";
        dbObj.patient_lastname = "patient_lastname";
        dbObj.patient_hn = "patient_hn";
        dbObj.visit_vn = "visit_vn";
        dbObj.f_visit_type_id = "f_visit_type_id";
        dbObj.contract_plans_description = "contract_plans_description";
        dbObj.date = "date";
        dbObj.paid = "paid";        
        return true;        
    }
    
    public Vector queryRevenueAndExpenseByDateTime(String startDate,String finishDate, String timeFrom, String timeTo, String codeServicePoint) throws Exception
    {
        String SQL = "SELECT  CASE WHEN (f_patient_prefix.patient_prefix_description IS NOT NULL " +
               "AND f_patient_prefix.patient_prefix_description <> '' " +
               "AND f_patient_prefix.patient_prefix_description <> 'null' ) " +
               "THEN f_patient_prefix.patient_prefix_description ELSE '' END AS " +
               "patient_prefix_description , " +
               "t_patient.patient_firstname," +
               "t_patient.patient_lastname, " +
               "t_patient.patient_hn, " +
               "t_visit.visit_vn, " +
               "t_visit.f_visit_type_id, " +
               "b_contract_plans.contract_plans_description, " +
               "substring(billing_receipt_date_time,1,10) AS date, " +
               "sum(t_billing_receipt_item.billing_receipt_item_paid) AS paid " +
               "FROM t_billing_receipt " +
               "INNER JOIN t_visit ON (t_visit.t_visit_id = t_billing_receipt.t_visit_id " +
               "AND t_visit.f_visit_status_id <> '4' " +
               "AND t_billing_receipt.billing_receipt_active = '1' " +
               "AND t_billing_receipt.billing_receipt_paid > 0.00) " +
               "INNER JOIN t_billing_receipt_item  ON (t_billing_receipt.t_billing_receipt_id = t_billing_receipt_item.t_billing_receipt_id " +
               "AND t_billing_receipt_item.billing_receipt_item_paid > 0) " +
               "INNER JOIN t_visit_payment  ON (t_billing_receipt_item.t_payment_id = t_visit_payment.t_visit_payment_id " +
               "AND t_visit_payment.visit_payment_active = '1') " +
               "LEFT JOIN b_contract_plans  ON t_visit_payment.b_contract_plans_id= b_contract_plans.b_contract_plans_id  " +
               "INNER JOIN t_patient  ON t_visit.t_patient_id = t_patient.t_patient_id  " +
               "LEFT JOIN f_patient_prefix  ON t_patient.f_patient_prefix_id = f_patient_prefix.f_patient_prefix_id  " +
               "LEFT JOIN b_employee  ON t_billing_receipt.billing_receipt_staff_record = b_employee.b_employee_id  " +
               "LEFT JOIN b_service_point  ON b_employee.b_service_point_id = b_service_point.b_service_point_id  " +
               "WHERE ((substring(billing_receipt_date_time,1,10)) Between '" + startDate + "' And '" + finishDate + "') " +
               "AND (substr(billing_receipt_date_time,12,5) Between '" + timeFrom + "' AND '" + timeTo + "') ";
               if(!codeServicePoint.equalsIgnoreCase("0") && !codeServicePoint.equalsIgnoreCase(""))
               {
                  SQL = SQL + " AND (b_service_point.b_service_point_id = '" + codeServicePoint + "')"; 
               }
               SQL = SQL + "GROUP BY patient_prefix_description , " +
                "patient_firstname, " +
                "patient_lastname, " +
                "patient_hn, " +
                "visit_vn, " +
                "f_visit_type_id, " +
                "contract_plans_description, " +
                "date";
               
       System.out.println("SQL : "+ codeServicePoint); 
       System.out.println("SQL queryRevenueAndExpenseByDateTime : "+ SQL);
       return eQuery(SQL);
    }
    
    public Vector queryAccrualsByDateTime(String startDate,String finishDate, String timeFrom, String timeTo, String codeServicePoint) throws Exception
    {
        String SQL = "SELECT  CASE WHEN (f_patient_prefix.patient_prefix_description IS NOT NULL " +
                "AND f_patient_prefix.patient_prefix_description <> '' " +
                "AND f_patient_prefix.patient_prefix_description <> 'null' )  " +
                "THEN f_patient_prefix.patient_prefix_description ELSE '' END AS patient_prefix_description , " +
                "t_patient.patient_firstname, " +
                "t_patient.patient_lastname, " +
                "t_patient.patient_hn, " +
                "t_visit.visit_vn, " +
                "t_visit.f_visit_type_id, " +
                "b_contract_plans.contract_plans_description, " +
                "sum(t_billing_invoice.billing_invoice_payer_share) AS paid, " +
                "substring(t_billing_invoice_date_time,1,10) AS date " +
                "FROM t_billing_invoice " +
                "INNER JOIN t_visit  ON t_visit.t_visit_id = t_billing_invoice.t_visit_id " +
                "AND t_visit.f_visit_status_id <> '4' " +
                "AND t_billing_invoice.billing_invoice_active = '1' " +
                "AND t_billing_invoice.billing_invoice_payer_share > 0.00 " +
                "INNER JOIN t_visit_payment  ON (t_billing_invoice.t_payment_id = t_visit_payment.t_visit_payment_id and t_visit_payment.visit_payment_active = '1') " +
                "LEFT JOIN b_contract_plans  ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id  " +
                "INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id  " +
                "LEFT JOIN f_patient_prefix  ON t_patient.f_patient_prefix_id = f_patient_prefix.f_patient_prefix_id  " +
                "LEFT JOIN b_employee  ON t_billing_invoice.billing_invoice_staff_record = b_employee.b_employee_id " +
                "LEFT JOIN b_service_point  ON b_employee.b_service_point_id = b_service_point.b_service_point_id  " +
                "WHERE ( ((substring(t_billing_invoice_date_time,1,10)) Between '" + startDate + "' And '" + finishDate + "')) " +
                "AND (substr(t_billing_invoice_date_time,12,5) Between '" + timeFrom + "' AND '" + timeTo + "') ";
                if(!codeServicePoint.equalsIgnoreCase("0") && !codeServicePoint.equalsIgnoreCase(""))
                 {
                    SQL = SQL + " AND (b_service_point.b_service_point_id = '" + codeServicePoint + "')"; 
                 }
                SQL = SQL + "GROUP BY patient_prefix_description , " +
                "patient_firstname, " +
                "patient_lastname, " +
                "patient_hn, " +
                "visit_vn, " +
                "f_visit_type_id, " +
                "contract_plans_description, " +
                "date";
       
        System.out.println("SQL queryAccrualsByDateTime : "+ SQL);
        return eQuery(SQL);
    }
    
    public Vector queryRevenueExpenseByDateTime(String startDate,String finishDate, String timeFrom, String timeTo, String codeServicePoint) throws Exception
    {
        String SQL = "SELECT "
                    + " CASE WHEN (f_patient_prefix.patient_prefix_description IS NOT NULL "
                    + "               AND f_patient_prefix.patient_prefix_description <> '' " 
                    + "               AND f_patient_prefix.patient_prefix_description <> 'null') " 
                    + "       THEN f_patient_prefix.patient_prefix_description " 
                    + "       ELSE '' " 
                    + "   END AS patient_prefix_description "
                    + ", t_patient.patient_firstname"
                    + ", t_patient.patient_lastname"
                    + ", t_patient.patient_hn"
                    + ", t_visit.visit_vn"
                    + ", t_visit.f_visit_type_id"
                    + ", b_contract_plans.contract_plans_description"
                    + ", t_billing_invoice.billing_invoice_total AS paid"
                    + ", substring(t_billing_invoice_date_time,1,10) AS date "                    
                + "FROM t_billing_invoice LEFT JOIN t_visit " +
                " ON t_visit.t_visit_id = t_billing_invoice.t_visit_id  " +
                " LEFT JOIN t_visit_payment  " +
                " ON (t_billing_invoice.t_payment_id = t_visit_payment.t_visit_payment_id and t_visit_payment.visit_payment_active = '1') " +
                " LEFT JOIN b_contract_plans " +
                " ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id " +
                " LEFT JOIN t_patient " +
                " ON t_visit.t_patient_id = t_patient.t_patient_id  " +
                " LEFT JOIN f_patient_prefix " +
                " ON t_patient.f_patient_prefix_id = f_patient_prefix.f_patient_prefix_id " +
                " LEFT JOIN b_employee " +
                " ON t_billing_invoice.billing_invoice_staff_record = b_employee.b_employee_id " +
                " LEFT JOIN b_service_point " +
                " ON b_employee.b_service_point_id = b_service_point.b_service_point_id "
                + " WHERE (((t_visit.f_visit_status_id)<> '4') "
                    + "AND (t_billing_invoice.billing_invoice_active = '1') "
                    + "AND ((substring(t_billing_invoice_date_time,1,10)) Between '" + startDate + "' And '" + finishDate + "')) "
                    + "AND (substr(t_billing_invoice_date_time,12,5) Between '" + timeFrom + "' AND '" + timeTo + "')";
        
        if(!codeServicePoint.equalsIgnoreCase("0") && !codeServicePoint.equalsIgnoreCase(""))
         {
            SQL = SQL + " AND (b_service_point.b_service_point_id = '" + codeServicePoint + "')"; 
         }
        
        System.out.println("SQL queryRevenueExpenseByDateTime : "+ SQL);
        return eQuery(SQL);
    }
    
    private Vector eQuery(String sql) throws Exception
    {
        CashInFlow p;
        Vector list = new Vector(); 
        Vector vString = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);    
        if(rs!=null)
        {
            while(rs.next())
            {
                p = new CashInFlow();
                p.patient_hn = rs.getString(dbObj.patient_hn);
                p.visit_vn = rs.getString(dbObj.visit_vn);
                p.patient_prefix_description = rs.getString(dbObj.patient_prefix_description);
                p.patient_firstname = rs.getString(dbObj.patient_firstname);
                p.patient_lastname = rs.getString(dbObj.patient_lastname);
                p.contract_plans_description = rs.getString(dbObj.contract_plans_description);
                p.date = rs.getString(dbObj.date);            
                p.paid = rs.getString(dbObj.paid);                
                p.f_visit_type_id = rs.getString(dbObj.f_visit_type_id);
                vString.add(p);
                p = null;            
            }            
            list.add(null); 
            list.add(vString);  
        }
        rs.close();
        return list;
    } 
}
