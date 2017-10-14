/*
 * RPCostPaymentShareOFA7INOUTHosDB.java
 *
 * Created on 18 ตุลาคม 2548, 18:03 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.objdb;
import com.generalreport.object.*;
import com.generalreport.utility.Language;
import com.generalreport.utility.DateUtil;
import com.hospital_os.usecase.connection.ConnectionInf;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author tong(Padungrat)
 */
public class RPCostPaymentShareOFA7INOUTHosDB {
    
    public ConnectionInf theConnectionInf;
    Vector vc;
    Patient thePatient;
    Prefix thePrefix;
    ICD10 theICD10;
    Visit theVisit;
    BillingInvoice theBillingInvoice;
    Payment thePayment;
    Plan thePlan;
    DiagIcd10 theDiagIcd10;
    
    String SQL = "";
    ResultSet rs = null;
    ResultSetMetaData metadata;
    java.util.Vector vDrugDispense ;
    Vector vString;
    Vector vData;
    int language =1;
    final String pttype = "A7";
    public RPCostPaymentShareOFA7INOUTHosDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        initObject();
    
    }
    /**
     *  ใช้ในการ new Object ของข้อมูลที่จะใช้ในการ ทำ SQL
     */
    private void initObject()
    {
        thePatient = new Patient();
        thePrefix = new Prefix();
        theICD10 = new ICD10();
        theVisit = new Visit();
        theBillingInvoice = new BillingInvoice();
        thePayment = new Payment();
        thePlan = new Plan();
        theDiagIcd10 = new DiagIcd10();
        
        thePatient.setInitDataFieldName();
        thePrefix.setInitDataFieldName();
        theICD10.setInitDataFieldName();
        theVisit.setInitDataFieldName();
        theBillingInvoice.setInitDataFieldName();
        thePayment.setInitDataFieldName();
        thePlan.setInitDataFieldName();
        theDiagIcd10.setInitDataFieldName();        
    }
    
    public Vector queryCostPaymentShareOFA7INOUTHosByDate(String startDate,String finishDate,String visit_type)
    {   
        vc  =null;
        try
        {
            SQL = "SELECT " +
                        " t_patient.patient_hn as HN,   " +
                        " CASE WHEN (f_patient_prefix.patient_prefix_description IS NOT NULL AND " +
                        "           f_patient_prefix.patient_prefix_description <> '' AND " +
                        "           f_patient_prefix.patient_prefix_description <> 'null' )" +
                        "      THEN (f_patient_prefix.patient_prefix_description || ' ' || t_patient.patient_firstname || ' ' || 	" +
                        "       t_patient.patient_lastname) " +
                        "      ELSE (t_patient.patient_firstname || ' ' || 	" +
                        "       t_patient.patient_lastname) " +
                        " END AS patient_name,   " +
                        " TO_DATE(t_patient.patient_birthday,'YYYY-MM-DD') as birthday,   "+
                        " t_visit.visit_patient_age AS age,  " +
                        " CASE WHEN (t_patient.patient_house <> '' AND t_patient.patient_moo <> '') " +
                        "       THEN  (t_patient.patient_house || ' ม.' || t_patient.patient_moo || ' ต.' || " +
                        "               f_address_1.address_description || ' อ.' || f_address_2.address_description || ' จ.' || " +
                        "               f_address_3.address_description )        " +
                        "       ELSE (t_patient.patient_house || ' ม.' || t_patient.patient_moo || ' ต.' ||  " +
                        "               f_address_1.address_description || ' อ.' || f_address_2.address_description || ' จ.' || " +
                        "               f_address_3.address_description )  " +
                        " END AS address,  " +
                        " CASE WHEN (b_icd10.icd10_number IS NOT NULL AND " +
                        "           b_icd10.icd10_number <> '' AND " +
                        "           b_icd10.icd10_number <> 'null' ) " +
                        "      THEN b_icd10.icd10_number " +
                        "      ELSE '' " +
                        " END AS icd10_code,  " +
                        " CASE WHEN (b_icd10.icd10_description IS NOT NULL AND " +
                        "           b_icd10.icd10_description <> '' AND " +
                        "           b_icd10.icd10_description <> 'null' ) " +
                        "      THEN b_icd10.icd10_description " +
                        "      ELSE '' " +
                        " END AS icd10_des,  " +
                        " CASE WHEN (t_visit.f_visit_type_id = '1')    " +
                        "       THEN TO_DATE(t_visit.visit_begin_admit_date_time,'YYYY-MM-DD')    " +
                        "       ELSE TO_DATE(t_visit.visit_begin_visit_time ,'YYYY-MM-DD')  " +
                        " END as begin_service_time,  " +
                        " b_contract_plans.contract_plans_description AS plan,   " +
                        " sum(TO_NUMBER(t_billing_invoice.billing_invoice_payer_share_ceil,'999999.99')) as payer_share " +
                    " FROM " +
                    " t_billing_invoice  INNER JOIN t_visit  " +
                    " ON t_visit.t_visit_id = t_billing_invoice.t_visit_id " +
                    " INNER JOIN t_visit_payment " +
                    " ON t_billing_invoice.t_payment_id = t_visit_payment.t_visit_payment_id " +
                    " INNER JOIN t_patient " +
                    " ON t_visit.t_patient_id = t_patient.t_patient_id " +
                    " INNER JOIN b_contract_plans " +
                    " ON b_contract_plans.b_contract_plans_id = t_visit_payment.b_contract_plans_id  " +
                    " LEFT JOIN f_patient_prefix " +
                    " ON t_patient.f_patient_prefix_id = f_patient_prefix.f_patient_prefix_id  " +
                    " LEFT JOIN t_diag_icd10 " +
                    " ON (t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn  AND t_diag_icd10.f_diag_icd10_type_id = '1' AND t_diag_icd10.diag_icd10_active = '1' ) " +
                    " LEFT JOIN f_address AS f_address_1 " +
                    " ON f_address_1.f_address_id = t_patient.patient_tambon  " +
                    " LEFT JOIN f_address AS f_address_2  " +
                    " ON f_address_2.f_address_id = t_patient.patient_amphur  " +
                    " LEFT JOIN f_address AS f_address_3  " +
                    " ON f_address_3.f_address_id = t_patient.patient_changwat  " +
                    " LEFT JOIN b_icd10 " +
                    " ON t_diag_icd10.diag_icd10_number = b_icd10.icd10_number  " +
                  " WHERE  " +
                        " ((SUBSTRING("+ theVisit.tableName +"."+ theVisit.financial_discharge_time +",0,11) Between '"+ startDate + "' And '"+ finishDate + "')  " +
                        " AND (("+ theVisit.tableName +"."+ theVisit.visit_type +")= '"+ visit_type +"')  " +
                        " AND (("+ theBillingInvoice.tableName +"."+ theBillingInvoice.active +")='1')  " +
                        " AND ((TO_NUMBER(t_billing_invoice.billing_invoice_payer_share_ceil,'999999.99') ) > 0.0 )" +
                        " AND (t_visit.f_visit_status_id <> '4' ) " +                       
                        " ) " +
                  " GROUP BY  " +
                        " HN, " +
                        " patient_name," +
                        " birthday, " +
                        " age, " +
                        " address, " +
                        " icd10_code, " +
                        " icd10_des, " +
                        " begin_service_time, " +
                        " plan, " +
                        " t_visit.t_visit_id " +
                  " ORDER BY " +
                        " begin_service_time, " +
                        thePatient.tableName+"."+thePatient.patient_hn ;

                   
            
            System.out.println("SQL queryCostPaymentShareOFA7INOUTHosByDate : " + SQL);
            rs = theConnectionInf.eQuery(SQL);
            vc = getData(rs);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
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
                    {   rowdata[i] = rs.getString(i+1); 
                        if(metadata.getColumnTypeName(i+1).equalsIgnoreCase("date"))
                        {      
                            if(rowdata[i].trim().length() != 0)
                            {
                                rowdata[i] = DateUtil.getDateToString(DateUtil.getDateFromTextServer(rowdata[i]),false);
                            }
                        }
                        
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
