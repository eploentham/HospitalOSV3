/*
 * RPPaymentINOUTCupDB.java
 *
 * Created on 11 ตุลาคม 2548, 11:41 น.
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
 * @author tong(Padungrat)
 */
public class RPPaymentINOUTCupDB {
    
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
    Payment thePayment;
    public RPPaymentINOUTCupDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        initObject();
    }
    private void initObject()
    {   
        theVisit = new Visit();
        thePayment = new Payment();
      
        theVisit.setInitDataFieldName();
        
        thePayment.setInitDataFieldName();
    }
     public Vector queryPaymentINOUTCupByDate(String startDate,String finishDate,String startCheck,String endCheck,boolean isJan)
    {   
        vc  =null;
        try
        {
            SQL = "SELECT b_contract_plans.contract_plans_description as Payment , " +
                    "sum(CASE WHEN (patient_incup <> '') THEN 1 ELSE 0 END) AS patient_incup , " +
                    "CASE WHEN ((sum(visit_incup)) > 0 ) THEN sum(visit_incup) ELSE  0 END  AS visit_incup , " +
                    "sum(CASE WHEN (patient_outcup <> '') THEN  1 ELSE 0 END) AS patient_outcup , " +
                    "CASE WHEN ((sum(visit_outcup)) > 0 ) THEN sum(visit_outcup) ELSE  0 END AS visit_outcup " +
                    "FROM b_contract_plans " +
                    "LEFT JOIN  (SELECT  t_visit_payment.b_contract_plans_id AS b_contract_plans_id ," +
                    "t_visit.t_patient_id AS t_patient_id, " +
                    "CASE WHEN (((t_visit_payment.visit_payment_main_hospital IN (Select  hospital_incup_code " +
                    "from r_hospital_incup)) OR ( t_visit_payment.visit_payment_sub_hospital IN (Select  hospital_incup_code " +
                    "from r_hospital_incup))) ";
                    if(!isJan)
                    {
                        SQL = SQL + "AND (t_visit.t_patient_id  NOT IN (SELECT  distinct t_patient.t_patient_id " +
                        "FROM t_visit INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id  " +
                        "where (t_visit.f_visit_status_id <> '4') " +
                        "AND ((SUBSTRING(t_visit.visit_begin_visit_time,1,7) >= '" + startCheck + "') " +
                        "AND (SUBSTRING(t_visit.visit_begin_visit_time,1,7) < '" + endCheck + "')) " +
                        "Order by t_patient.t_patient_id)) ";
                    }
                    
                    SQL = SQL + " ) THEN t_visit.t_patient_id ELSE '' END AS patient_incup ," +
                            "sum(CASE WHEN ((t_visit_payment.visit_payment_main_hospital IN (Select  hospital_incup_code " +
                            "from r_hospital_incup)) OR (t_visit_payment.visit_payment_sub_hospital IN (Select  hospital_incup_code " +
                            "from r_hospital_incup))) THEN 1 ELSE 0 END) AS visit_incup ," +
                            "CASE WHEN (((t_visit_payment.visit_payment_main_hospital NOT IN (Select  hospital_incup_code " +
                            "from r_hospital_incup)) OR (t_visit_payment.visit_payment_sub_hospital NOT IN (Select  hospital_incup_code " +
                            "from r_hospital_incup))) ";
                    if(!isJan)
                    {
                            SQL = SQL + "AND (t_visit.t_patient_id  NOT IN (SELECT  distinct t_patient.t_patient_id " +
                            "FROM t_visit INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id  " +
                                    "where (t_visit.f_visit_status_id <> '4')  " +
                                    "AND ((SUBSTRING(t_visit.visit_begin_visit_time,1,7) >= '" + startCheck + "') " +
                                    "AND (SUBSTRING(t_visit.visit_begin_visit_time,1,7) < '" + endCheck + "')) " +
                                    "Order by t_patient.t_patient_id)) ";
                    }
                    SQL = SQL + " ) THEN t_visit.t_patient_id ELSE '' END AS patient_outcup ," +
                            "sum(CASE WHEN ((t_visit_payment.visit_payment_main_hospital NOT IN (Select  hospital_incup_code " +
                            "from r_hospital_incup)) AND (t_visit_payment.visit_payment_sub_hospital NOT IN (Select  hospital_incup_code " +
                            "from r_hospital_incup))) THEN 1 ELSE 0 END) AS visit_outcup " +
                            "FROM  t_visit INNER JOIN t_visit_payment ON t_visit.t_visit_id = t_visit_payment.t_visit_id " +
                            "WHERE (t_visit.f_visit_status_id <> '4') AND ( t_visit_payment.visit_payment_priority = '0' ) " +
                            "AND ( t_visit_payment.visit_payment_active = '1') AND ( t_visit.f_visit_type_id = '0') " +
                            "AND (substring(t_visit.visit_financial_discharge_time,0,11) Between '" + startDate + "' And '" + finishDate + "') " +
                            "AND (t_visit.visit_financial_discharge_time <> '') " +
                            "GROUP BY b_contract_plans_id ,t_visit.t_patient_id,patient_incup ,patient_outcup ) AS query1 " +
                            "ON b_contract_plans.b_contract_plans_id = query1.b_contract_plans_id " +
                            "GROUP BY b_contract_plans.contract_plans_description";
                    
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
