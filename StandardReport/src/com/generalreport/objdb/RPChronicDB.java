/*
 * RPChronicDB.java
 *
 * Created on 6 ตุลาคม 2548, 13:56 น.
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
public class RPChronicDB
{
    public ConnectionInf theConnectionInf;
    Vector vc;
    Visit theVisit;
    Payment thePayment;
    Chronic theChronic;
    ICD10 theICD10;
    
    
    String SQL = "";
    ResultSet rs = null;
    ResultSetMetaData metadata;
    java.util.Vector vDrugDispense ;
    Vector vString;
    Vector vData;
    int language = 1;
    /** Creates a new instance of RPChronicDB */
    public RPChronicDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theVisit = new Visit();
        thePayment = new Payment();
        theChronic = new Chronic();
        theICD10 = new ICD10();
        
        theVisit.setInitDataFieldName();
        thePayment.setInitDataFieldName();
        theChronic.setInitDataFieldName();
        theICD10.setInitDataFieldName();
    }
    
    public Vector queryChronicByDate(String startDate,String finishDate)
    {   vc  =null;
        try
        {    
            SQL = "SELECT  query1.chronic_icd10 AS ICD10_CODE ," +
                    "query1.icd10_description AS ICD_NAME ," +
                    "sum(query1.patient_incup) AS INCUP_PERSON ," +
                    "sum(query1.visit_incup) AS INCUP_TIME ," +
                    "sum(query1.patient_outcup) AS OUTCUP_PERSON ," +
                    "sum(query1.visit_outcup) AS OUTCUP_TIME " +
                    "FROM (SELECT t_visit.t_patient_id ," +
                    "t_chronic.chronic_icd10 AS chronic_icd10," +
                    "b_icd10.icd10_description AS icd10_description ," +
                    "CASE WHEN ((t_visit_payment.visit_payment_main_hospital " +
                    "IN (SELECT  hospital_incup_code FROM r_hospital_incup)) " +
                    "OR (t_visit_payment.visit_payment_sub_hospital " +
                    "IN (SELECT  hospital_incup_code FROM r_hospital_incup))) THEN 1 else 0  END AS patient_incup ," +
                    "sum(CASE WHEN ((t_visit_payment.visit_payment_main_hospital " +
                    "IN (SELECT  hospital_incup_code FROM r_hospital_incup)) " +
                    "OR (t_visit_payment.visit_payment_sub_hospital " +
                    "IN (SELECT  hospital_incup_code FROM r_hospital_incup))) THEN 1 else 0  END) AS visit_incup , " +
                    "CASE WHEN ((t_visit_payment.visit_payment_main_hospital " +
                    "NOT IN (SELECT  hospital_incup_code FROM r_hospital_incup)) " +
                    "AND (t_visit_payment.visit_payment_sub_hospital " +
                    "NOT IN (SELECT  hospital_incup_code FROM r_hospital_incup))) THEN 1 else 0  END  AS patient_outcup ," +
                    "sum(CASE WHEN ((t_visit_payment.visit_payment_main_hospital " +
                    "NOT IN (SELECT  hospital_incup_code FROM r_hospital_incup))  " +
                    "AND (t_visit_payment.visit_payment_sub_hospital " +
                    "NOT IN (SELECT  hospital_incup_code FROM r_hospital_incup))) THEN 1 else 0  END)  AS visit_outcup " +
                    "FROM t_chronic INNER JOIN t_visit " +
                    "ON (t_chronic.t_patient_id = t_visit.t_patient_id " +
                    "AND t_visit.f_visit_status_id <> '4') " +
                    "INNER JOIN t_diag_icd10 " +
                    "ON (t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn " +
                    "AND t_chronic.chronic_icd10 = t_diag_icd10.diag_icd10_number " +
                    "AND t_diag_icd10.diag_icd10_active = '1') " +
                    "INNER JOIN b_icd10 " +
                    "ON t_chronic.chronic_icd10 = b_icd10.icd10_number " +
                    "INNER JOIN t_visit_payment " +
                    "ON (t_visit.t_visit_id = t_visit_payment.t_visit_id " +
                    "AND t_visit_payment.visit_payment_priority = '0' " +
                    "AND t_visit_payment.visit_payment_active = '1' ) " +
                    "WHERE (substring(t_visit.visit_financial_discharge_time,1,10) Between '" + startDate + "' And '" + finishDate + "')  " +
                    "GROUP BY " +
                    "t_visit.t_patient_id ," +
                    "t_chronic.chronic_icd10 ," +
                    "b_icd10.icd10_description ," +
                    "patient_incup  ," +
                    "patient_outcup) AS query1  " +
                    "Group BY  query1.chronic_icd10 ," +
                    "query1.icd10_description " +
                    "ORDER BY query1.chronic_icd10";
                    
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
