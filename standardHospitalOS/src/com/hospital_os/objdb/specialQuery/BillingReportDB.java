/*
 * BillingReportDB.java
 *
 * Created on 27 กรกฎาคม 2547, 20:09 น.
 */
package com.hospital_os.objdb.specialQuery;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.specialQuery.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  tong
 *  ใช้ในการ query รายงาน ทางการเงิน ที่ซับซ้อน
 *@deprecated henbe unused use com.hospital_os.object.specialQuery instead
 */
public class BillingReportDB
{
    public ConnectionInf theConnectionInf;
    public BillingReport dbObj;
    /** Creates a new instance of BillingReportDB 
     *@deprecated henbe unused use com.hospital_os.object.specialQuery instead
     */
    public BillingReportDB(ConnectionInf db)
    {
        theConnectionInf=db;
        dbObj = new BillingReport();
        initConfig();
    }
    public boolean initConfig()
    {
        dbObj.patient_id = "t_patient_id";/* "billing_receipt_date_time";
*/
        dbObj.hn = "visit_hn";
        dbObj.visit_id = "t_visit_id";
        dbObj.vn = "visit_vn";
        dbObj.plans_id = "b_contract_plans_id";
        dbObj.description = "contract_plans_description";
        dbObj.fname = "patient_firstname";
        dbObj.lname = "patient_lastname";
        dbObj.paid = "paid";/*"billing_receipt_billing_subgroup_paid";
*/
        dbObj.receipt_date = "date_time";/*"billing_receipt_date_time";
*/
        dbObj.visit_type = "f_visit_type_id";
        return true;
    }
    public Vector BillingPatientShareReport(String startDate,String endDate, String startTime,String endTime,String service_id)throws Exception
    {
        Vector vc = new Vector();
        /*String sql = "SELECT visit.vn, visit.hn, patient.fname, patient.lname, plans.description, receipt_subgroup.paid,receipt.receipt_date,visit.visit_id,patient.person_id,plans.key_id,visit_type "
            + " FROM((((((billing INNER JOIN visit ON billing.visit_id = visit.visit_id) "
            + " INNER JOIN receipt ON billing.billing_id = receipt.billing_id) "
            + " INNER JOIN receipt_subgroup ON receipt.receipt_id = receipt_subgroup.receipt_id) "
            + " INNER JOIN billing_invoice_subgroup ON receipt_subgroup.billing_invoice_subgroup_id = billing_invoice_subgroup.billing_invoice_subgroup_id) "
            + " INNER JOIN payment ON billing_invoice_subgroup.payment_id = payment.payment_id) "
            + " INNER JOIN plans ON payment.plan_kid = plans.key_id) INNER JOIN patient ON visit.patient_id = patient.person_id "
            + " WHERE (((billing.active)<>'0') "
            + " AND( substring(receipt.receipt_date,0,11)>= '" +  startDate + "'  "
            + " AND substring(receipt.receipt_date,0,11)<= '" +  endDate + "'  ) "
            + " AND ( substring(receipt.receipt_date,12,5) >= '" +  startTime + "' "
            + " And substring(receipt.receipt_date,12,5) <=  '" +  endTime + "' ) "
            + " ) "
            + "  GROUP BY visit.vn, visit.hn, patient.fname, patient.lname, plans.description, receipt_subgroup.paid,receipt.receipt_date,visit.visit_id,patient.person_id,plans.key_id,visit_type "
            + " ORDER BY visit.vn, plans.description;";
         */
        String sql = "";
        if(!service_id.equalsIgnoreCase(""))
        { sql = "SELECT t_visit.visit_vn, t_visit.visit_hn, t_patient.patient_firstname, t_patient.patient_lastname, b_contract_plans.contract_plans_description   , t_billing_receipt_billing_subgroup.billing_receipt_billing_subgroup_paid as paid ,t_billing_receipt.billing_receipt_date_time as date_time ,t_visit.t_visit_id,t_patient.t_patient_id,b_contract_plans.b_contract_plans_id,t_visit.f_visit_type_id "
          + " FROM ((((((((t_billing INNER JOIN t_visit ON t_billing.t_visit_id = t_visit.t_visit_id) "
          + " INNER JOIN t_billing_receipt ON t_billing.t_billing_id = t_billing_receipt.t_billing_id) "
          + " INNER JOIN t_billing_receipt_billing_subgroup ON t_billing_receipt.t_billing_receipt_id = t_billing_receipt_billing_subgroup.t_billing_receipt_id) "
          + " INNER JOIN t_billing_invoice_billing_subgroup ON t_billing_receipt_billing_subgroup.t_billing_invoice_billing_subgroup_id = t_billing_invoice_billing_subgroup.t_billing_invoice_billing_subgroup_id) "
          + " INNER JOIN t_visit_payment ON t_billing_invoice_billing_subgroup.t_payment_id = t_visit_payment.t_visit_payment_id) "
          + " INNER JOIN b_contract_plans ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id) "
          + " INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id)"
          + " INNER JOIN b_employee ON b_employee.b_employee_id = t_billing_receipt.billing_receipt_staff_record)"
          + " INNER JOIN b_service_point ON b_service_point.b_service_point_id = b_employee.b_service_point_id "
          + " WHERE (((t_billing.billing_active)<>'0') " ;
          if(!service_id.equalsIgnoreCase(""))
              sql = sql + " AND b_service_point.b_service_point_id ='" + service_id + "' ";
          
          sql = sql + " AND( substring(t_billing_receipt.billing_receipt_date_time,0,17)>= '" +  startDate + "'  "
          + " AND substring(t_billing_receipt.billing_receipt_date_time,0,17)<= '" +  endDate + "'  ) "
          /* + " AND ( substring(t_billing_receipt.billing_receipt_date_time,12,5) >= '" +  startTime + "' "
*/
          /* + " And substring(t_billing_receipt.billing_receipt_date_time,12,5) <=  '" +  endTime + "' ) "
*/
          + " ) "
          + "  GROUP BY t_visit.visit_vn, t_visit.visit_hn, t_patient.patient_firstname, t_patient.patient_lastname, b_contract_plans.contract_plans_description   , t_billing_receipt_billing_subgroup.billing_receipt_billing_subgroup_paid,t_billing_receipt.billing_receipt_date_time,t_visit.t_visit_id,t_patient.t_patient_id,b_contract_plans.b_contract_plans_id,t_visit.f_visit_type_id "
          + " ORDER BY t_visit.visit_vn, b_contract_plans.contract_plans_description   ;";
        }
        else
        {
            sql = "SELECT t_visit.visit_vn, t_visit.visit_hn, t_patient.patient_firstname, t_patient.patient_lastname, b_contract_plans.contract_plans_description   , t_billing_receipt_billing_subgroup.billing_receipt_billing_subgroup_paid as paid ,t_billing_receipt.billing_receipt_date_time as date_time ,t_visit.t_visit_id,t_patient.t_patient_id,b_contract_plans.b_contract_plans_id,t_visit.f_visit_type_id "
            + " FROM (((((((t_billing INNER JOIN t_visit ON t_billing.t_visit_id = t_visit.t_visit_id) "
            + " INNER JOIN t_billing_receipt ON t_billing.t_billing_id = t_billing_receipt.t_billing_id) "
            + " INNER JOIN t_billing_receipt_billing_subgroup ON t_billing_receipt.t_billing_receipt_id = t_billing_receipt_billing_subgroup.t_billing_receipt_id) "
            + " INNER JOIN t_billing_invoice_billing_subgroup ON t_billing_receipt_billing_subgroup.t_billing_invoice_billing_subgroup_id = t_billing_invoice_billing_subgroup.t_billing_invoice_billing_subgroup_id) "
            + " INNER JOIN t_visit_payment ON t_billing_invoice_billing_subgroup.t_payment_id = t_visit_payment.t_visit_payment_id) "
            + " INNER JOIN b_contract_plans ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id) "
            + " INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id) "
            
            + " WHERE (((t_billing.billing_active)<>'0') " ;
            /*  if(!service_id.equalsIgnoreCase(""))
*/
            /*     sql = sql + " AND b_service_point.b_service_point_id ='" + service_id + "' ";
*/
            
            sql = sql + " AND( substring(t_billing_receipt.billing_receipt_date_time,0,17)>= '" +  startDate + "'  "
            + " AND substring(t_billing_receipt.billing_receipt_date_time,0,17)<= '" +  endDate + "'  ) "
            /* + " AND ( substring(t_billing_receipt.billing_receipt_date_time,12,5) >= '" +  startTime + "' "
*/
            /* + " And substring(t_billing_receipt.billing_receipt_date_time,12,5) <=  '" +  endTime + "' ) "
*/
            + " ) "
            + "  GROUP BY t_visit.visit_vn, t_visit.visit_hn, t_patient.patient_firstname, t_patient.patient_lastname, b_contract_plans.contract_plans_description   , t_billing_receipt_billing_subgroup.billing_receipt_billing_subgroup_paid,t_billing_receipt.billing_receipt_date_time,t_visit.t_visit_id,t_patient.t_patient_id,b_contract_plans.b_contract_plans_id,t_visit.f_visit_type_id "
            + " ORDER BY t_visit.visit_vn, b_contract_plans.contract_plans_description   ;";
        }
        
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    public Vector BillingPayerShareReport(String startDate,String endDate, String startTime,String endTime,String service_id)throws Exception
    {
        Vector vc = new Vector();
        String sql ="";
        if(!service_id.equalsIgnoreCase(""))
        {
            sql = "SELECT t_visit.visit_hn, t_visit.visit_vn, t_patient.patient_firstname, t_patient.patient_lastname, t_visit.visit_financial_discharge_time AS date_time , b_contract_plans.contract_plans_description, Sum(t_billing_invoice.billing_invoice_payer_share) AS paid, t_visit.f_visit_type_id, t_visit.t_visit_id, t_patient.t_patient_id, b_contract_plans.b_contract_plans_id " +
            " FROM ((((t_visit INNER JOIN t_billing_invoice ON t_visit.t_visit_id = t_billing_invoice.t_visit_id) " +
            " INNER JOIN b_employee ON b_employee.b_employee_id = t_billing_invoice.billing_invoice_staff_record)" +
            " INNER JOIN b_service_point ON b_service_point.b_service_point_id = b_employee.b_service_point_id)" +
            " INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id) " +
            " INNER JOIN (t_visit_payment " +
            "              INNER JOIN b_contract_plans ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id) " +
            "                                          ON t_billing_invoice.t_payment_id = t_visit_payment.t_visit_payment_id " +
            " WHERE (((t_billing_invoice.billing_invoice_active)='1') " ;
            if(!service_id.equalsIgnoreCase(""))
                sql = sql + " AND b_service_point.b_service_point_id ='" + service_id + "' ";
            sql = sql +
            "        AND(  substring(t_visit.visit_financial_discharge_time,0,17)>= '" + startDate+ "'" +
            "              AND substring(t_visit.visit_financial_discharge_time,0,17)<= '" + endDate+ "'" +
            "        ) " +
            /* " AND ( substring(t_visit.visit_financial_discharge_time,12,5) >= '" + startTime+ "'" +
*/
            /* "              And substring(t_visit.visit_financial_discharge_time,12,5) <=  '" + endTime+ "'" +
*/
            /* "        )" +
*/
            ") " +
            " GROUP BY t_visit.visit_hn, t_visit.visit_vn, t_patient.patient_firstname, t_patient.patient_lastname, t_visit.visit_financial_discharge_time, b_contract_plans.contract_plans_description, t_visit.f_visit_type_id, t_visit.t_visit_id, t_patient.t_patient_id, b_contract_plans.b_contract_plans_id" +
            " HAVING (((Sum(t_billing_invoice.billing_invoice_payer_share))>0) AND ((t_visit.f_visit_type_id)<>'2')) " +
            " ORDER BY t_visit.visit_vn, b_contract_plans.contract_plans_description;";
        }
        else
        {   sql = "SELECT t_visit.visit_hn, t_visit.visit_vn, t_patient.patient_firstname, t_patient.patient_lastname, t_visit.visit_financial_discharge_time AS date_time , b_contract_plans.contract_plans_description, Sum(t_billing_invoice.billing_invoice_payer_share) AS paid, t_visit.f_visit_type_id, t_visit.t_visit_id, t_patient.t_patient_id, b_contract_plans.b_contract_plans_id " +
            " FROM ((t_visit INNER JOIN t_billing_invoice ON t_visit.t_visit_id = t_billing_invoice.t_visit_id) " +
            " INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id) " +
            " INNER JOIN (t_visit_payment " +
            "              INNER JOIN b_contract_plans ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id) " +
            "                                          ON t_billing_invoice.t_payment_id = t_visit_payment.t_visit_payment_id " +
            " WHERE (((t_billing_invoice.billing_invoice_active)='1') " ;
            if(!service_id.equalsIgnoreCase(""))
                sql = sql + " AND b_service_point.b_service_point_id ='" + service_id + "' ";
            sql = sql +
            "        AND(  substring(t_visit.visit_financial_discharge_time,0,17)>= '" + startDate+ "'" +
            "              AND substring(t_visit.visit_financial_discharge_time,0,17)<= '" + endDate+ "'" +
            "        ) " +
            /* " AND ( substring(t_visit.visit_financial_discharge_time,12,5) >= '" + startTime+ "'" +
*/
            /* "              And substring(t_visit.visit_financial_discharge_time,12,5) <=  '" + endTime+ "'" +
*/
            /* "        )" +
*/
            ") " +
            " GROUP BY t_visit.visit_hn, t_visit.visit_vn, t_patient.patient_firstname, t_patient.patient_lastname, t_visit.visit_financial_discharge_time, b_contract_plans.contract_plans_description, t_visit.f_visit_type_id, t_visit.t_visit_id, t_patient.t_patient_id, b_contract_plans.b_contract_plans_id" +
            " HAVING (((Sum(t_billing_invoice.billing_invoice_payer_share))>0) AND ((t_visit.f_visit_type_id)<>'2')) " +
            " ORDER BY t_visit.visit_vn, b_contract_plans.contract_plans_description;";
            
        }
        
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    public Vector BillingPatientRemainReport(String startDate,String endDate, String startTime,String endTime,String service_id)throws Exception
    {
        Vector vc = new Vector();
        String sql = "SELECT Sum(t_billing.billing_remain) AS paid, b_contract_plans.contract_plans_description, t_patient.t_patient_id, t_visit.visit_hn, t_visit.visit_vn, t_visit.t_visit_id, t_visit.f_visit_type_id, t_patient.patient_firstname, t_patient.patient_lastname, b_contract_plans.b_contract_plans_id,t_billing.billing_billing_date_time as date_time " +
        " FROM t_patient INNER JOIN (((t_billing INNER JOIN t_visit ON t_billing.t_visit_id = t_visit.t_visit_id) INNER JOIN t_billing_invoice ON t_billing.t_billing_id = t_billing_invoice.t_billing_id) INNER JOIN (t_visit_payment INNER JOIN b_contract_plans ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id) ON t_billing_invoice.t_payment_id = t_visit_payment.t_visit_payment_id) ON t_patient.t_patient_id = t_visit.t_patient_id " +
        "  WHERE (((t_visit.f_visit_status_id)<>'4') AND ((t_billing.billing_active)='1') " +
        "  AND (" +
        "       substring(t_billing.billing_billing_date_time,0,17)>= '" +  startDate+ "'" +
        "      AND substring(t_billing.billing_billing_date_time,0,17)<= '" + endDate+ "'" +
        "  ) " +
        /*" AND ( substring(t_billing.billing_billing_date_time,12,5) >= '" + startTime+ "'" +
*/
        /*"              And substring(t_billing.billing_billing_date_time,12,5) <=  '" + endTime+ "'" +
*/
        /*")" +
*/
        ") " +
        " GROUP BY b_contract_plans.contract_plans_description, t_patient.t_patient_id, t_visit.visit_hn, t_visit.visit_vn, t_visit.t_visit_id, t_visit.f_visit_type_id, t_patient.patient_firstname, t_patient.patient_lastname, b_contract_plans.b_contract_plans_id,t_billing.billing_billing_date_time;";
        
        
        
        
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    
    public Vector BillingSumTreatmentReport(String startDate,String endDate, String startTime,String endTime,String service_id)throws Exception
    {
        Vector vc = new Vector();
        String sql = "";
        if(!service_id.equalsIgnoreCase(""))
        {
            sql = "SELECT t_visit.visit_hn, t_visit.visit_vn, t_visit.f_visit_type_id, Sum(t_billing_invoice_billing_subgroup.billing_invoice_billing_subgroup_total) AS paid, b_contract_plans.b_contract_plans_id, b_contract_plans.contract_plans_description, t_patient.patient_firstname, t_patient.patient_lastname, t_visit.t_visit_id, t_patient.t_patient_id,visit_financial_discharge_time AS date_time " +
            " FROM (((((t_visit_payment INNER JOIN (" +
            " t_visit INNER JOIN t_billing_invoice_billing_subgroup ON t_visit.t_visit_id = t_billing_invoice_billing_subgroup.t_visit_id) " +
            "          ON t_visit_payment.t_visit_payment_id = t_billing_invoice_billing_subgroup.t_payment_id) " +
            "INNER JOIN b_contract_plans ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id) " +
            " INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id)" +
            " INNER JOIN t_billing_invoice ON t_billing_invoice.t_billing_invoice_id = t_billing_invoice_billing_subgroup.t_billing_invoice_id) " +
            " INNER JOIN b_employee ON b_employee.b_employee_id = t_billing_invoice.billing_invoice_staff_record) " +
            " INNER JOIN b_service_point ON b_service_point.b_service_point_id = b_employee.b_service_point_id " +
            " WHERE (((t_billing_invoice_billing_subgroup.billing_invoice_billing_subgroup_active)='1') AND ((t_billing_invoice_billing_subgroup.t_billing_id)<>'') " ;
            if(!service_id.equalsIgnoreCase(""))
                sql = sql + " AND b_service_point.b_service_point_id ='" + service_id + "' ";
            sql  = sql +
            "      AND (" +
            "          substring(t_visit.visit_financial_discharge_time,0,17)>= '" +  startDate+ "'" +
            "          AND substring(t_visit.visit_financial_discharge_time,0,17)<= '" + endDate+ "'" +
            "      ) " +
            /* " AND ( substring(t_visit.visit_financial_discharge_time,12,5) >= '" + startTime+ "'" +
*/
            /* "         And substring(t_visit.visit_financial_discharge_time,12,5) <=  '" + endTime+ "'" +
*/
            /* " )" +
*/
            ") " +
            " GROUP BY t_visit.visit_hn, t_visit.visit_vn, t_visit.f_visit_type_id, b_contract_plans.b_contract_plans_id, b_contract_plans.contract_plans_description, t_patient.patient_firstname, t_patient.patient_lastname, t_visit.t_visit_id, t_patient.t_patient_id,visit_financial_discharge_time;";
        }
        else
        {
            sql = "SELECT t_visit.visit_hn, t_visit.visit_vn, t_visit.f_visit_type_id, Sum(t_billing_invoice_billing_subgroup.billing_invoice_billing_subgroup_total) AS paid, b_contract_plans.b_contract_plans_id, b_contract_plans.contract_plans_description, t_patient.patient_firstname, t_patient.patient_lastname, t_visit.t_visit_id, t_patient.t_patient_id,visit_financial_discharge_time AS date_time " +
            " FROM (" +
            /* "((" +
*/
            "((t_visit_payment INNER JOIN (" +
            " t_visit INNER JOIN t_billing_invoice_billing_subgroup ON t_visit.t_visit_id = t_billing_invoice_billing_subgroup.t_visit_id) " +
            "          ON t_visit_payment.t_visit_payment_id = t_billing_invoice_billing_subgroup.t_payment_id) " +
            "INNER JOIN b_contract_plans ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id) " +
            " INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id)" +
            /* " INNER JOIN t_billing_invoice ON t_billing_invoice.t_billing_invoice_id = t_billing_invoice_billing_subgroup.t_billing_invoice_id) " +
*/
            /* " INNER JOIN b_employee ON b_employee.b_employee_id = t_billing_invoice.billing_invoice_staff_record) " +
*/
            /* " INNER JOIN b_service_point ON b_service_point.b_service_point_id = b_employee.b_service_point_id " +
*/
            " WHERE (((t_billing_invoice_billing_subgroup.billing_invoice_billing_subgroup_active)='1') AND ((t_billing_invoice_billing_subgroup.t_billing_id)<>'') " ;
            if(!service_id.equalsIgnoreCase(""))
                sql = sql + " AND b_service_point.b_service_point_id ='" + service_id + "' ";
            sql  = sql +
            "      AND (" +
            "          substring(t_visit.visit_financial_discharge_time,0,17)>= '" +  startDate+ "'" +
            "          AND substring(t_visit.visit_financial_discharge_time,0,17)<= '" + endDate+ "'" +
            "      ) " +
            /* " AND ( substring(t_visit.visit_financial_discharge_time,12,5) >= '" + startTime+ "'" +
*/
            /* "         And substring(t_visit.visit_financial_discharge_time,12,5) <=  '" + endTime+ "'" +
*/
            /* " )" +
*/
            ") " +
            " GROUP BY t_visit.visit_hn, t_visit.visit_vn, t_visit.f_visit_type_id, b_contract_plans.b_contract_plans_id, b_contract_plans.contract_plans_description, t_patient.patient_firstname, t_patient.patient_lastname, t_visit.t_visit_id, t_patient.t_patient_id,visit_financial_discharge_time;";
        }
        
        vc = eQuery(sql);
        if(vc.size()==0)
            return null;
        else
            return vc;
    }
    /**
     *@deprecated henbe unused use com.hospital_os.object.specialQuery instead
     **/
    public Vector eQuery(String sql) throws Exception
    {
        BillingReport p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new BillingReport();
            p.patient_id  = rs.getString(dbObj.patient_id);
            p.hn = rs.getString(dbObj.hn);
            p.visit_id = rs.getString(dbObj.visit_id);
            p.vn = rs.getString(dbObj.vn);
            p.plans_id = rs.getString(dbObj.plans_id);
            p.description = rs.getString(dbObj.description);
            p.fname = rs.getString(dbObj.fname);
            p.lname = rs.getString(dbObj.lname);
            p.paid = rs.getString(dbObj.paid);
            p.receipt_date = rs.getString(dbObj.receipt_date);
            p.visit_type = rs.getString(dbObj.visit_type);
            list.add(p);
        }
        rs.close();
        return list;
    }
    
    
    
}
