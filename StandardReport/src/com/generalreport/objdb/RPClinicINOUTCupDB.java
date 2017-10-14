/*
 * RPEyeDiseasesDB.java
 *
 * Created on 11 ตุลาคม 2548, 11:57 น.
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
public class RPClinicINOUTCupDB {
    
    public ConnectionInf theConnectionInf;
    Vector vc;
    OrderItem theOrderItem,ObjectOrderItem;
    Employee theEmployee;
    
    String SQL = "";
    ResultSet rs = null;
    ResultSetMetaData metadata;
    java.util.Vector vDrugDispense ;
    Vector vString;
    Vector vData;
    int language =1;
    Visit theVisit;
    Clinic theClinic;
    DiagIcd10 theDiagIcd10;
    Payment thePayment;
    public RPClinicINOUTCupDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        initObject();
    
    }
    /**
     *  ใช้ในการ new Object ของข้อมูลที่จะใช้ในการ ทำ SQL
     */
    private void initObject()
    {   theClinic = new Clinic();
        theVisit = new Visit();
        theDiagIcd10 = new DiagIcd10();
        thePayment = new Payment();
        theClinic.setInitDataFieldName();
        theVisit.setInitDataFieldName();
        theDiagIcd10.setInitDataFieldName();
        thePayment.setInitDataFieldName();
    }
     public Vector queryClinicINOUTCupByDate(String startDate,String finishDate,String startCheck,String endCheck,boolean isJan)
    {   
        vc  =null;
        try
        {
            SQL = "SELECT b_visit_clinic.visit_clinic_description as clinic , " +
                    "sum(CASE WHEN (patient_incup <> '') THEN 1 ELSE 0 END) AS patient_incup , " +
                    "CASE WHEN ((sum(visit_incup)) > 0 ) THEN sum(visit_incup) ELSE  0 END  AS visit_incup , " +
                    "sum(CASE WHEN (patient_outcup <> '') THEN  1 ELSE 0 END) AS patient_outcup , " +
                    "CASE WHEN ((sum(visit_outcup)) > 0 ) THEN sum(visit_outcup) ELSE  0 END AS visit_outcup " +
                    "FROM b_visit_clinic " +
                    "LEFT JOIN  (  SELECT t_diag_icd10.b_visit_clinic_id AS b_visit_clinic_id ," +
                    "t_visit.t_patient_id AS t_patient_id , " +
                    "CASE WHEN (((visit_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup)) " +
                    "OR (visit_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup))) ";
            if(!isJan)
            {
                SQL = SQL + "AND (t_visit.t_patient_id  NOT IN (SELECT  distinct t_patient.t_patient_id " +
                        "FROM t_visit INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id " +
                        "where (t_visit.f_visit_status_id <> '4') " +
                        "AND ((SUBSTRING(t_visit.visit_begin_visit_time,1,7) >= '" + startCheck + "') " +
                        "AND (SUBSTRING(t_visit.visit_begin_visit_time,1,7) < '" + endCheck + "')) " +
                        "Order by t_patient.t_patient_id))";
            }
            SQL = SQL + " ) THEN t_visit.t_patient_id ELSE '' END AS patient_incup , " +
                    "sum(CASE WHEN ((visit_payment_main_hospital IN (Select  hospital_incup_code " +
                    "from r_hospital_incup)) OR (visit_payment_sub_hospital IN (Select  hospital_incup_code " +
                    "from r_hospital_incup))) THEN 1 ELSE 0 END) AS visit_incup , " +
                    "CASE WHEN (((visit_payment_main_hospital NOT IN (Select  hospital_incup_code " +
                    "from r_hospital_incup)) OR (visit_payment_sub_hospital NOT IN (Select  hospital_incup_code " +
                    "from r_hospital_incup))) ";
            if(!isJan)
            {
                SQL = SQL + "AND (t_visit.t_patient_id  NOT IN (SELECT  distinct t_patient.t_patient_id " +
                        "FROM t_visit INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id " +
                        "where (t_visit.f_visit_status_id <> '4')  " +
                        "AND ((SUBSTRING(t_visit.visit_begin_visit_time,1,7) >= '" + startCheck + "') " +
                        "AND (SUBSTRING(t_visit.visit_begin_visit_time,1,7) < '" + endCheck + "')) " +
                        "Order by t_patient.t_patient_id))";
            }
            SQL = SQL + " ) THEN t_visit.t_patient_id ELSE '' END AS patient_outcup , " +
                    "sum(CASE WHEN ((visit_payment_main_hospital NOT IN (Select  hospital_incup_code " +
                    "from r_hospital_incup)) " +
                    "AND (visit_payment_sub_hospital NOT IN (Select  hospital_incup_code " +
                    "from r_hospital_incup))) THEN 1 ELSE 0 END) AS visit_outcup FROM ( t_visit INNER JOIN t_visit_payment " +
                    "ON t_visit.t_visit_id = t_visit_payment.t_visit_id ) " +
                    "INNER JOIN t_diag_icd10 ON t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn " +
                    "WHERE ( t_visit.f_visit_status_id = '3' OR t_visit.f_visit_status_id = '2') " +
                    "AND ( t_diag_icd10.f_diag_icd10_type_id = '1')  AND ( t_visit_payment.visit_payment_priority = '0') " +
                    "AND ( t_visit_payment.visit_payment_active = '1') " +
                    "AND ( substring(t_visit.visit_financial_discharge_time,0,11) " +
                    "Between '" + startDate + "' And '" + finishDate + "') " +
                    "GROUP BY t_diag_icd10.b_visit_clinic_id , t_patient_id , patient_incup , patient_outcup  ) AS query1  " +
                    "ON query1.b_visit_clinic_id = b_visit_clinic.b_visit_clinic_id  " +
                    "GROUP BY b_visit_clinic.visit_clinic_description";

                   
            
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
