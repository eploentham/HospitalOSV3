/*
 * RPPatientOPDRemainDB.java
 *
 * Created on 5 มิถุนายน 2549, 16:17 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportnan.utility.Language;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class RPPatientOPDRemainDB
{
    ConnectionInf theConnectionInf;
    String SQL = "";
    Vector vc;
    ResultSet rs = null;
    ResultSetMetaData metadata;
    Vector vString;
    Vector vData;
    private int columnsize;
    private int str;
    /**
     * Creates a new instance of RPPatientOPDRemainDB 
     */
    public RPPatientOPDRemainDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    /**
     *ลูกหนี้รายวันประเภทผู้ป่วยนอก
     *@param startdate เป็น String ที่เก็บวันที่เริ่มต้นในการดึงข้อมูล
     *@param enddate เป็น String ที่เก็บวันที่สิ้นสุดในการดึงข้อมูล
     *@return Vector ของข้อมูลลูกหนี้รายวันประเภทผู้ป่วยนอก
     *@Author pu
     *@Date 05/06/2006
     */
    public Vector queryPatientOPDRemainByDate(String startdate,String enddate)
    {
        try
        {
            SQL = "select " + 
                "b_contract_plans.contract_plans_description AS plan " + 
                ",t_patient.patient_hn AS HN " + 
                ",t_patient.patient_firstname AS fname " + 
                ",t_patient.patient_lastname AS lname " + 
                ",sum(t_billing.billing_patient_share) AS patient_total " + 
                ",sum(t_billing.billing_paid) AS paid " + 
                ",sum(t_billing.billing_remain) AS remain " + 
            "from " + 
                "t_patient INNER JOIN t_visit " + 
                "ON t_patient.t_patient_id = t_visit.t_patient_id " + 
                "INNER JOIN t_visit_payment " + 
                "ON t_visit.t_visit_id = t_visit_payment.t_visit_id " + 
                "INNER JOIN b_contract_plans  " + 
                "ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id " + 
                "INNER JOIN t_billing " + 
                "ON t_visit.t_visit_id = t_billing.t_visit_id " + 
            "where " + 
                "t_visit.f_visit_type_id = '0' " + 
                "and t_visit.f_visit_status_id <> '4' " + 
                "and t_visit_payment.visit_payment_priority = '0' " + 
                "and t_visit_payment.visit_payment_active = '1' " + 
                "and t_billing.billing_active = '1' " +
                "and (t_billing.billing_patient_share > t_billing.billing_paid) " +
                "and t_billing.billing_patient_share != '0' " +
                "and t_billing.billing_remain != '0' " +
                "and substring(t_visit.visit_financial_discharge_time,1,10) Between '"+startdate+"' And '"+enddate+"' " + 
            "group by " + 
                "b_contract_plans.contract_plans_description " + 
                ",t_patient.patient_hn " + 
                ",t_patient.patient_firstname " + 
                ",t_patient.patient_lastname " + 

            "UNION " + 

            "select " + 
                "b_contract_plans.contract_plans_description AS plan " + 
                ",'all' AS HN " + 
                ",'all' AS fname " + 
                ",'all' AS lname " + 
                ",sum(t_billing.billing_patient_share) AS patient_total " + 
                ",sum(t_billing.billing_paid) AS paid " + 
                ",sum(t_billing.billing_remain) AS remain " + 
            "from " + 
                "t_patient INNER JOIN t_visit " + 
                "ON t_patient.t_patient_id = t_visit.t_patient_id " + 
                "INNER JOIN t_visit_payment " + 
                "ON t_visit.t_visit_id = t_visit_payment.t_visit_id " + 
                "INNER JOIN b_contract_plans  " + 
                "ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id " + 
                "INNER JOIN t_billing " + 
                "ON t_visit.t_visit_id = t_billing.t_visit_id " + 
            "where " + 
                "t_visit.f_visit_type_id = '0' " + 
                "and t_visit.f_visit_status_id <> '4' " + 
                "and t_visit_payment.visit_payment_priority = '0' " + 
                "and t_billing.billing_active = '1' " +
                "and (t_billing.billing_patient_share > t_billing.billing_paid) " +
                "and t_billing.billing_patient_share != '0' " +
                "and t_billing.billing_remain != '0' " +
                "and substring(t_visit.visit_financial_discharge_time,1,10) Between '"+startdate+"' And '"+enddate+"' " + 
            "group by " + 
                "b_contract_plans.contract_plans_description " + 
                ",HN " + 
                ",fname " + 
                ",lname";
        System.out.println(SQL);
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
                columnname[i] = Language.getTextBundle(columnname[i].toUpperCase());
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
    /*private Vector getData(ResultSet resultset) throws Exception
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
            columnname = new String[column-1];
            vString = new Vector();
            //ทำการให้ค่าชื่อ field ที่ get มา
            for(int i = 0 ; i < column-1;i++)
            {
                columnname[i] = metadata.getColumnLabel(i+2);
                columnname[i] = Language.getTextBundle(columnname[i].toUpperCase());
            }
            //ทำการให้ค่าของ field กับข้อมูล
            while(rs.next())
            {   rowdata = new String[column-1];
                for(int i = 0 ; i < column-1;i++)
                {
                    if(rs.getObject(i+2) != null)
                    {
                        rowdata[i] = rs.getString(i+2);
                    }
                }
                vString.add(rowdata);
            }
            vData.add(columnname);
            vData.add(vString);
        }
        return vData;
    }*/
    
}
