/*
 * RPPatientAdmitAndDischargeDB.java
 *
 * Created on 19 ตุลาคม 2548, 15:19 น.
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
public class RPPatientAdmitAndDischargeDB {
    
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
    public RPPatientAdmitAndDischargeDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        initObject();
    }
    /**
     *  ใช้ในการ new Object ของข้อมูลที่จะใช้ในการ ทำ SQL
     */
    private void initObject()
    { 
    }
    
     public Vector queryPatientAdmitAndDischargByDate(String startDate,String finishDate)
    {   
        vc  =null;
        try
        {
            SQL = " SELECT  " +
                        " b_contract_plans.contract_plans_description as payment,  " +
                        "    SUM(CASE WHEN ((t_patient.f_sex_id ='1') and  " +
                        " (SubString(t_visit.visit_begin_admit_date_time,0,11) between '" + startDate + "' and '" + finishDate + "') " +
                        "   ) " +
                        " THEN 1 " +
                        " ELSE 0 " +
                        " END) as male_inservice, " +
                        " SUM(CASE WHEN ((t_patient.f_sex_id ='2') and  " +
                        " 	    (SubString(t_visit.visit_begin_admit_date_time,0,11) between '" + startDate + "' and '" + finishDate + "') " +
                        " 	  ) " +
                        " 	THEN 1 " +
                        " 	ELSE 0 " +
                        " END) as female_inservice, " +
                        "                   SUM(CASE WHEN ((t_patient.f_sex_id ='3') and  " +
                        " 	    (SubString(t_visit.visit_begin_admit_date_time,0,11) between '" + startDate + "' and '" + finishDate + "') " +
                        " 	  ) " +
                        " 	THEN 1 " +
                        " 	ELSE 0 " +
                        " END) as nospec_inservice, " +
                        " SUM(CASE WHEN ((t_patient.f_sex_id ='1') and  " +
                        " 	    (SubString(t_visit.visit_financial_discharge_time,0,11) between '" + startDate + "' and '" + finishDate + "') " +
                        " 	  ) " +
                        " 	THEN 1 " +
                        " 	ELSE 0 " +
                        " END) as male_discharge, " +
                        " SUM(CASE WHEN ((t_patient.f_sex_id ='2') and  " +
                        " 	    (SubString(t_visit.visit_financial_discharge_time,0,11) between '" + startDate + "' and '" + finishDate + "') " +
                        " 	  ) " +
                        " 	THEN 1 " +
                        " 	ELSE 0 " +
                        " END) as female_discharge, " +
                        " SUM(CASE WHEN ((t_patient.f_sex_id ='3') and  " +
                        " 	    (SubString(t_visit.visit_financial_discharge_time,0,11) between '" + startDate + "' and '" + finishDate + "') " +
                        " 	  ) " +
                        " 	THEN 1 " +
                        " 	ELSE 0 " +
                        " END) as nospec_discharge " +
                        " FROM  " +
                        " t_patient  " +
                        " INNER JOIN ((t_visit_payment  " +
                        " 	INNER JOIN t_visit ON t_visit_payment.t_visit_id = t_visit.t_visit_id)  " +
                        " 	INNER JOIN b_contract_plans ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id) " + 
                        " ON t_patient.t_patient_id = t_visit.t_patient_id " +
                        " WHERE  " +
                        " (((t_visit.f_visit_type_id)='1')  " +
                        " AND ((t_visit.f_visit_status_id)<>'4')  " +
                        " AND ((t_visit_payment.visit_payment_priority)='0') " +
                        " AND (t_visit_payment.visit_payment_active = '1') " +
                        " AND ((SubString(t_visit.visit_begin_admit_date_time,0,11) between '" + startDate + "' and '" + finishDate + "')  " +
                        " OR (SubString(t_visit.visit_financial_discharge_time,0,11) between '" + startDate + "' and '" + finishDate + "')  " +
                        "  )) " +
                        " GROUP BY " +
                        " b_contract_plans.contract_plans_description  " +
                        " ORDER BY " +
                        " b_contract_plans.contract_plans_description" ;
 
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
