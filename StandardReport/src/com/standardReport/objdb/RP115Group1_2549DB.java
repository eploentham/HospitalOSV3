/*
 * RP115Group1DB_2549.java
 *
 * Created on 12 กันยายน 2548, 10:29 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.objdb;

import com.standardReport.object.RP115Group1IPD;
import com.standardReport.object.RP115Group1OPD;
import com.standardReport.object.RP115Group1PCU;
import com.standardReport.object.RP115Group1Refer;
import com.standardReport.object.RP115Group1_2549;
import java.util.*;
import java.sql.*;
import java.io.*;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.standardReport.subject.StandardDB;
import com.standardReport.utility.*;

/**
 *
 * @author pu
 */
public class RP115Group1_2549DB {
    
    /**
     * Creates a new instance of RP115Group1DB_2549 
     */
    public ConnectionInf theConnectionInf;
    RP115Group1OPD theRP115Group1OPD; 
    RP115Group1IPD theRP115Group1IPD;
    RP115Group1PCU theRP115Group1PCU;
    RP115Group1Refer theRP115Group1Refer;
    
    ResultSet rs = null;
    Vector vReport;
    
    public RP115Group1_2549DB(ConnectionInf c) {
        
        theConnectionInf = c;
        
        theRP115Group1OPD = new RP115Group1OPD();      
    }
    
    /**
     *ค้นหาจำนวนการเข้ารับบริการของผู้ป่วยนอก
     *@update 19/10/2549
     *@by Pu
     */
     public Vector selectGroup1OPDByDate(String startDate, String endDate, String startCheckDate, String endCheckDate, boolean isJan) throws Exception
     {
        /* StringBuffer opdSQL = new StringBuffer();
         opdSQL.append("select " );
         opdSQL.append(       " plan_group_description AS plan_group_description " );                 
         opdSQL.append(        " ,sum(CASE WHEN (q1.patient_incup <> '') " );
         opdSQL.append(        "      THEN 1 " );
         opdSQL.append(        "      ELSE 0 " );
         opdSQL.append(        " END ) AS patient_incup " );
         opdSQL.append(        " , sum(CASE WHEN (q1.patient_outcup <> '')" );
         opdSQL.append(        "      THEN 1 " );
         opdSQL.append(        "      ELSE 0 " );
         opdSQL.append(        " END) AS patient_outcup " );   
         opdSQL.append(        " , sum(q1.visit_incup) AS visit_incup " );
         opdSQL.append(        " , sum(q1.visit_outcup) AS visit_outcup " );
         opdSQL.append(        " from ");
         if(isJan)   
         {            
              opdSQL.append(" (select " );
              opdSQL.append(           " r_plan_group.plan_group_description AS plan_group_description" );
              opdSQL.append(           ", case when  ((visit_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))  " );
              opdSQL.append(                      " OR (visit_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup))) " );
              opdSQL.append(           " then  t_patient_id " );
              opdSQL.append(           " else  '' end  AS patient_incup " );
              opdSQL.append(           ", case when  ((visit_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))  " );
              opdSQL.append(                      " AND (visit_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))) " );
              opdSQL.append(           " then  t_patient_id " );
              opdSQL.append(           " else  '' end  AS patient_outcup ") ;
         }
         else
         {            
              opdSQL.append(" (select " );
              opdSQL.append(           " r_plan_group.plan_group_description AS plan_group_description" );
              opdSQL.append(           ",case when  ((t_patient_id  NOT IN " );
              opdSQL.append(                              "(SELECT distinct t_patient.t_patient_id FROM  " );
              opdSQL.append(                              " t_visit INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id " );
              opdSQL.append(                              " WHERE " );
              opdSQL.append(                              " (t_visit.f_visit_status_id <> '4')  " );
              opdSQL.append(                              " AND ((SUBSTRING(t_visit.visit_begin_visit_time,0,9) >= '"+startCheckDate+"') " );
              opdSQL.append(                              " And (SUBSTRING(t_visit.visit_begin_visit_time,0,9) < '"+endCheckDate+"')) " );
              opdSQL.append(                              " Order by t_patient.t_patient_id))  " );
              opdSQL.append(                      " AND ((visit_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))  " );
              opdSQL.append(                      " OR (visit_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup)))) " );
              opdSQL.append(           " then  t_patient_id " );
              opdSQL.append(           " else  '' end AS patient_incup " );
              opdSQL.append(           ",case when  ((t_patient_id  NOT IN " );
              opdSQL.append(                              "(SELECT distinct t_patient.t_patient_id FROM  " );
              opdSQL.append(                              " t_visit INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id " );
              opdSQL.append(                              " WHERE " );
              opdSQL.append(                              " (t_visit.f_visit_status_id <> '4')  " );
              opdSQL.append(                              " AND ((SUBSTRING(t_visit.visit_begin_visit_time,0,9) >= '"+startCheckDate+"') " );
              opdSQL.append(                              " And (SUBSTRING(t_visit.visit_begin_visit_time,0,9) < '"+endCheckDate+"')) " );
              opdSQL.append(                              " Order by t_patient.t_patient_id))  " );
              opdSQL.append(                      " AND ((visit_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))  " );
              opdSQL.append(                      " AND (visit_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup)))) " );
              opdSQL.append(           " then  t_patient_id " );
              opdSQL.append(           " else  '' end AS patient_outcup ") ;
         }
          opdSQL.append(" , r_plan_group.plan_group_number AS plan_group_number" );
          opdSQL.append(           " , t_patient_id AS patient_id " );
          opdSQL.append(           " ,sum(case when (((visit_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))  " );
          opdSQL.append(                      " OR (visit_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup))) " );
          opdSQL.append(                      " AND t_visit_id IS NOT NULL) " );
          opdSQL.append(           " then 1 " );
          opdSQL.append(           " else 0 end ) AS visit_incup " );
          opdSQL.append(           " ,sum(case when (((visit_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))  " );
          opdSQL.append(                      " AND (visit_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup)))" );
          opdSQL.append(                      " AND t_visit_id IS NOT NULL) " );
          opdSQL.append(           " then 1 " );
          opdSQL.append(           " else 0 end ) AS visit_outcup " );
          opdSQL.append(           " from  " );
          opdSQL.append(           " r_plan_group  Left Join " );
          opdSQL.append(           " (SELECT t_visit.t_visit_id, t_visit.t_patient_id, t_visit_payment.visit_payment_main_hospital, " );
          opdSQL.append(           " t_visit_payment.visit_payment_sub_hospital, b_contract_plans.contract_plans_pttype, " );
          opdSQL.append(          " CASE WHEN (r_plan_group_map_pttype.r_plan_group_id IS NULL OR " );
          opdSQL.append(                       " r_plan_group_map_pttype.r_plan_group_id = '' OR " );
          opdSQL.append(                       " r_plan_group_map_pttype.r_plan_group_id = 'null' )" );
          opdSQL.append(                  " THEN '8030000000006' " );
          opdSQL.append(                  " ELSE r_plan_group_map_pttype.r_plan_group_id " );
          opdSQL.append(           " END AS r_plan_group_id" );
          opdSQL.append(           " FROM  " );
          opdSQL.append(           " (t_visit INNER JOIN (t_visit_payment INNER JOIN b_contract_plans  " );
          opdSQL.append(           " ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id)  " );
          opdSQL.append(           " ON t_visit.t_visit_id = t_visit_payment.t_visit_id)  " );
          opdSQL.append(           " Left JOIN r_plan_group_map_pttype ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype " );
          opdSQL.append(           " WHERE " );
          opdSQL.append(           " (t_visit.f_visit_status_id <> '4')  " );
          opdSQL.append(           " AND (t_visit.f_visit_type_id = '0')  " );
          opdSQL.append(           " AND (t_visit.visit_hospital_service = '1') " );
          opdSQL.append(           " AND (t_visit_payment.visit_payment_priority = '0') " );
          opdSQL.append(           " AND (t_visit_payment.visit_payment_active = '1') " );
          opdSQL.append(           " AND (SUBSTRING(t_visit.visit_begin_visit_time,0,11) BETWEEN '" + startDate + "' and '" + endDate + "')) AS query1 " );
          opdSQL.append(           " On r_plan_group.r_plan_group_id = query1.r_plan_group_id " );
          opdSQL.append(           " group by  " );
          opdSQL.append(           " r_plan_group.plan_group_description " );
          opdSQL.append(           " , r_plan_group.r_plan_group_id" );
          opdSQL.append(           " , r_plan_group.plan_group_number " ); 
          opdSQL.append(           " , t_patient_id,query1.visit_payment_main_hospital" );
          opdSQL.append(           " , query1.visit_payment_sub_hospital " );
          opdSQL.append(           " order by " );
          opdSQL.append(           " r_plan_group.plan_group_number ) AS q1 " );
          opdSQL.append(         "group by " );
          opdSQL.append(        " q1.plan_group_description " );
          opdSQL.append(        " ,q1.plan_group_number " );
          opdSQL.append(        " order by" );
          opdSQL.append(        " q1.plan_group_number");          
          */
         
        String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part1_opd.sql");
        PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        eOPDQuery(pm.executeQuery());
 
          sql = null;     
          if(this.vReport.size()==0)
          {
              return null;
          }
          else
          {
              return this.vReport;
          }

     }
     
     /**
      *ค้นหาจำนวนการเข้าบริการของผู้ป่วยใน
      *@update 19/10/2549
      *@by Pu
      */
     public Vector selectGroup1IPDByDate(String startDate, String endDate) throws Exception
     {
        /* StringBuffer ipdSQL = new StringBuffer();
         ipdSQL.append(" SELECT r_plan_group.plan_group_description " );
         ipdSQL.append(" ,sum(case when ((query1.t_visit_id IS NOT NULL and query1.t_visit_id <> '') " );
         ipdSQL.append(              " AND ((visit_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup)) " );
         ipdSQL.append(              " OR (visit_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup)))) " );
         ipdSQL.append("      then 1 " );
         ipdSQL.append("      else 0 end ) AS ipd_incup " );
         ipdSQL.append(" ,sum(case when ((query1.t_visit_id IS NOT NULL and query1.t_visit_id <> '')" );
         ipdSQL.append(              " AND ((visit_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))  " );
         ipdSQL.append(              " AND (visit_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup)))) " );
         ipdSQL.append("      then 1 " );
         ipdSQL.append("      else 0 end) AS ipd_outcup " );
         ipdSQL.append(" ,sum(case when (((visit_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))  " );
         ipdSQL.append(              " OR (visit_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup)))" );
         ipdSQL.append(              " AND ((to_date(substring(visit_financial_discharge_time,0,11),'yyyy-mm-dd') - " );
         ipdSQL.append(              "   to_date(substring(visit_begin_admit_date_time,0,11),'yyyy-mm-dd')) = 0 )) " );
         ipdSQL.append(     " then 1 " );
         ipdSQL.append(     " when (((visit_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))  " );
         ipdSQL.append(           " OR (visit_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup)))" );
         ipdSQL.append(             " AND ((to_date(substring(visit_financial_discharge_time,0,11),'yyyy-mm-dd') - " );
         ipdSQL.append(              "   to_date(substring(visit_begin_admit_date_time,0,11),'yyyy-mm-dd')) > 0 )) " );
         ipdSQL.append("      then  " );
         ipdSQL.append("          to_date(substring(visit_financial_discharge_time,0,11),'yyyy-mm-dd') - " );
         ipdSQL.append("          to_date(substring(visit_begin_admit_date_time,0,11),'yyyy-mm-dd') " );
         ipdSQL.append("      else 0 end) AS day_stay_incup " );
         ipdSQL.append(" ,sum(case when (((visit_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))  " );
         ipdSQL.append(             " AND (visit_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup)))" );
         ipdSQL.append(             " AND ((to_date(substring(visit_financial_discharge_time,0,11),'yyyy-mm-dd') - " );
         ipdSQL.append(             "   to_date(substring(visit_begin_admit_date_time,0,11),'yyyy-mm-dd')) = 0 )) " );
         ipdSQL.append(     " then 1 " );
         ipdSQL.append(     " when (((visit_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))  " );
         ipdSQL.append(             " AND (visit_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup)))" );
         ipdSQL.append(             " AND ((to_date(substring(visit_financial_discharge_time,0,11),'yyyy-mm-dd') - " );
         ipdSQL.append(             "   to_date(substring(visit_begin_admit_date_time,0,11),'yyyy-mm-dd')) > 0 )) " );
         ipdSQL.append("      then  " );
         ipdSQL.append("          to_date(substring(visit_financial_discharge_time,0,11),'yyyy-mm-dd') - " );
         ipdSQL.append("          to_date(substring(visit_begin_admit_date_time,0,11),'yyyy-mm-dd') " );
         ipdSQL.append("      else 0 end) AS day_stay_incup " );
         ipdSQL.append(" FROM " );
         ipdSQL.append(" r_plan_group LEFT JOIN " );
         ipdSQL.append(" (SELECT " );
         ipdSQL.append(" t_visit.t_visit_id,  " );
         ipdSQL.append(" t_visit.t_patient_id, " );
         ipdSQL.append(" t_visit.visit_financial_discharge_time, " );
         ipdSQL.append(" t_visit.visit_begin_admit_date_time, " );
         ipdSQL.append(" t_visit_payment.visit_payment_main_hospital, " );
         ipdSQL.append(" t_visit_payment.visit_payment_sub_hospital, " );
         ipdSQL.append(" b_contract_plans.contract_plans_pttype, " );
         ipdSQL.append(" CASE WHEN (r_plan_group_map_pttype.r_plan_group_id IS NULL OR " );
         ipdSQL.append(          " r_plan_group_map_pttype.r_plan_group_id = '' OR " );
         ipdSQL.append(           " r_plan_group_map_pttype.r_plan_group_id = 'null' )" );
         ipdSQL.append(      " THEN '8030000000006' " );
         ipdSQL.append(     " ELSE r_plan_group_map_pttype.r_plan_group_id " );
         ipdSQL.append(" END AS r_plan_group_id" );
         ipdSQL.append(" FROM " );
         ipdSQL.append(" ((t_visit INNER JOIN t_visit_payment ON t_visit.t_visit_id = t_visit_payment.t_visit_id) " );
         ipdSQL.append(" INNER JOIN b_contract_plans ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id) " );
         ipdSQL.append(" LEFT JOIN r_plan_group_map_pttype ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype " );
         ipdSQL.append(" WHERE " );
         ipdSQL.append(" (t_visit.f_visit_status_id <> '4' )  " );
         ipdSQL.append(" AND (t_visit_payment.visit_payment_priority = '0' ) " );
         ipdSQL.append(" AND (t_visit_payment.visit_payment_active = '1' ) " );
         ipdSQL.append(" AND (t_visit.f_visit_type_id = '1') " );
         ipdSQL.append(" AND (SUBSTRING(t_visit.visit_financial_discharge_time,0,11) BETWEEN '" + startDate + "' and '" + endDate + "') " );
         ipdSQL.append(" ) AS query1 ON r_plan_group.r_plan_group_id = query1.r_plan_group_id " );
         ipdSQL.append(" GROUP BY " );
         ipdSQL.append(" r_plan_group.plan_group_description " );
         ipdSQL.append(" ,r_plan_group.plan_group_number" );
         ipdSQL.append(" ,r_plan_group.r_plan_group_id " );
         ipdSQL.append(" ORDER BY " );
         ipdSQL.append(" r_plan_group.plan_group_number ");
          */
         // System.out.println(ipdSQL.toString());
        String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part1_ipd.sql");
        PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        eIPDQuery(pm.executeQuery());
 
          sql = null;     
          if(this.vReport.size()==0)
          {
              return null;
          }
          else
          {
              return this.vReport;
          }
     }
     
     /**
      *ค้นหาจำนวนผู้เข้ารับบริการ PCU ในโรงพยาบาล
      *@update 19/10/2549
      *@by Pu
      */
     public Vector selectGroup1PCUByDate(String startDate, String endDate, String startCheckDate, String endCheckDate, boolean isJan) throws Exception
     {
         /*StringBuffer pcuSQL = new StringBuffer();
         pcuSQL.append("select " );
         pcuSQL.append(" plan_group_description AS plan_group_description " );                 
         pcuSQL.append(" ,sum(CASE WHEN (q1.patient_incup <> '') " );
         pcuSQL.append("      THEN 1 " );
         pcuSQL.append("      ELSE 0 " );
         pcuSQL.append(" END ) AS patient_incup " );
         pcuSQL.append(" , sum(CASE WHEN (q1.patient_outcup <> '')" );
         pcuSQL.append("      THEN 1 " );
         pcuSQL.append("      ELSE 0 " );
         pcuSQL.append(" END) AS patient_outcup " );   
         pcuSQL.append(" , sum(q1.visit_incup) AS visit_incup " );
         pcuSQL.append(" , sum(q1.visit_outcup) AS visit_outcup " );
         pcuSQL.append(" from ");
         if(isJan)   
         {          
             pcuSQL.append(" (select " );
             pcuSQL.append(" r_plan_group.plan_group_description AS plan_group_description" );
             pcuSQL.append(", case when  ((visit_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))  " );
             pcuSQL.append(          " OR (visit_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup))) " );
             pcuSQL.append(" then  t_patient_id " );
             pcuSQL.append(" else  '' end  AS patient_incup " );
             pcuSQL.append(", case when  ((visit_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))  " );
             pcuSQL.append(          " AND (visit_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))) " );
             pcuSQL.append(" then  t_patient_id " );
             pcuSQL.append(" else  '' end  AS patient_outcup ");
         }
         else
         {
            pcuSQL.append(" (select " );
            pcuSQL.append(" r_plan_group.plan_group_description AS plan_group_description" );
            pcuSQL.append(",case when  ((t_patient_id  NOT IN " );
            pcuSQL.append(                  "(SELECT distinct t_patient.t_patient_id FROM  " );
            pcuSQL.append(                  " t_visit INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id " );
            pcuSQL.append(                  " WHERE " );
            pcuSQL.append(                  " (t_visit.f_visit_status_id <> '4')  " );
            pcuSQL.append(                  " AND ((SUBSTRING(t_visit.visit_begin_visit_time,0,9) >= '"+startCheckDate+"') " );
            pcuSQL.append(                  " And (SUBSTRING(t_visit.visit_begin_visit_time,0,9) < '"+endCheckDate+"')) " );
            pcuSQL.append(                  " Order by t_patient.t_patient_id))  " );
            pcuSQL.append(          " AND ((visit_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))  " );
            pcuSQL.append(          " OR (visit_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup)))) " );
            pcuSQL.append(" then  t_patient_id " );
            pcuSQL.append(" else  '' end AS patient_incup " );
            pcuSQL.append(",case when  ((t_patient_id  NOT IN " );
            pcuSQL.append(                  "(SELECT distinct t_patient.t_patient_id FROM  " );
            pcuSQL.append(                  " t_visit INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id " );
            pcuSQL.append(                  " WHERE " );
            pcuSQL.append(                  " (t_visit.f_visit_status_id <> '4')  " );
            pcuSQL.append(                  " AND ((SUBSTRING(t_visit.visit_begin_visit_time,0,9) >= '"+startCheckDate+"') " );
            pcuSQL.append(                  " And (SUBSTRING(t_visit.visit_begin_visit_time,0,9) < '"+endCheckDate+"')) " );
            pcuSQL.append(                  " Order by t_patient.t_patient_id))  " );
            pcuSQL.append(          " AND ((visit_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))  " );
            pcuSQL.append(          " AND (visit_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup)))) " );
            pcuSQL.append(" then  t_patient_id " );
            pcuSQL.append(" else  '' end AS patient_outcup ");
         }
        pcuSQL.append(" , r_plan_group.plan_group_number AS plan_group_number" );
        pcuSQL.append(" , t_patient_id AS patient_id " );
        pcuSQL.append(" ,sum(case when (((visit_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))  " );
        pcuSQL.append(           " OR (visit_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup))) " );
        pcuSQL.append(          " AND t_visit_id IS NOT NULL) " );
        pcuSQL.append(" then 1 " );
        pcuSQL.append(" else 0 end ) AS visit_incup " );
        pcuSQL.append(" ,sum(case when (((visit_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))  " );
        pcuSQL.append(          " AND (visit_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup)))" );
        pcuSQL.append(          " AND t_visit_id IS NOT NULL) " );
        pcuSQL.append(" then 1 " );
        pcuSQL.append(" else 0 end ) AS visit_outcup " );
        pcuSQL.append(" from  " );
        pcuSQL.append(" r_plan_group  Left Join " );
        pcuSQL.append(" (SELECT t_visit.t_visit_id, t_visit.t_patient_id, t_visit_payment.visit_payment_main_hospital, " );
        pcuSQL.append(" t_visit_payment.visit_payment_sub_hospital, b_contract_plans.contract_plans_pttype, " );
        pcuSQL.append(" CASE WHEN (r_plan_group_map_pttype.r_plan_group_id IS NULL OR " );
        pcuSQL.append(            " r_plan_group_map_pttype.r_plan_group_id = '' OR " );
        pcuSQL.append(            " r_plan_group_map_pttype.r_plan_group_id = 'null' )" );
        pcuSQL.append(      " THEN '8030000000006' " );
        pcuSQL.append(      " ELSE r_plan_group_map_pttype.r_plan_group_id " );
        pcuSQL.append(" END AS r_plan_group_id" );
        pcuSQL.append(" FROM  " );
        pcuSQL.append(" (t_visit INNER JOIN (t_visit_payment INNER JOIN b_contract_plans  " );
        pcuSQL.append(" ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id)  " );
        pcuSQL.append(" ON t_visit.t_visit_id = t_visit_payment.t_visit_id)  " );
        pcuSQL.append(" Left JOIN r_plan_group_map_pttype ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype " );
        pcuSQL.append(" WHERE " );
        pcuSQL.append(" (t_visit.f_visit_status_id <> '4')  " );
        pcuSQL.append(" AND (t_visit.f_visit_type_id = '0')  " );
        pcuSQL.append(" AND (t_visit.visit_pcu_service = '1') " );
        pcuSQL.append(" AND (t_visit_payment.visit_payment_priority = '0') " );
        pcuSQL.append(" AND (t_visit_payment.visit_payment_active = '1') " );
        pcuSQL.append(" AND (SUBSTRING(t_visit.visit_begin_visit_time,0,11) BETWEEN '" + startDate + "' and '" + endDate + "')) AS query1 " );
        pcuSQL.append(" On r_plan_group.r_plan_group_id = query1.r_plan_group_id " );
        pcuSQL.append(" group by  " );
        pcuSQL.append(" r_plan_group.plan_group_description " );
        pcuSQL.append(" , r_plan_group.r_plan_group_id" );
        pcuSQL.append(" , r_plan_group.plan_group_number" );
        pcuSQL.append(" , t_patient_id,query1.visit_payment_main_hospital" );
        pcuSQL.append(" , query1.visit_payment_sub_hospital " );
        pcuSQL.append(" order by " );
        pcuSQL.append(" r_plan_group.plan_group_number ) AS q1 " );
        pcuSQL.append("group by " );
        pcuSQL.append(" q1.plan_group_description " );
        pcuSQL.append(" ,q1.plan_group_number" );
        pcuSQL.append(" ORDER BY " );
        pcuSQL.append(" q1.plan_group_number ");

          System.out.println(pcuSQL.toString());
          ePCUQuery(pcuSQL.toString());
          pcuSQL = null;
          **/
         String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part1_pcu.sql");
        PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        ePCUQuery(pm.executeQuery());
 
          sql = null; 
         
          if(this.vReport.size()==0)
          {
              return null;
          }
          else
          {
              return this.vReport;
          }
     }
     
     /**
      *ค้นหาจำนวนผู้ป่วยที่ ReferIn และ ReferOut ของโรงพยาบาล
      *@update 19/10/2549
      *@by Pu
      */
     public Vector selectGroup1ReferByDate(String startDate, String endDate) throws Exception
     {
         /* StringBuffer referSQL = new StringBuffer();
          referSQL.append(" SELECT  r_plan_group.plan_group_description " );
          referSQL.append(",sum(case when (query1.b_visit_office_id_refer_in IN (Select  hospital_incup_code from r_hospital_incup) ");
          referSQL.append("AND query1.f_visit_refer_type_id = '0') "); 
          referSQL.append("then 1  else 0 end) AS referin_incup   " );
          
          referSQL.append(" ,sum(case when ((b_visit_office_id_refer_in NOT IN (Select  hospital_incup_code from r_hospital_incup)) " );
          referSQL.append(" AND query1.f_visit_refer_type_id = '0'");
          referSQL.append(" AND ((query1.changwat_referin <> '') " );
          referSQL.append(" AND (query1.changwat_referin  IN (select  substring(site_changwat,0,3) from b_site)))) " );
          referSQL.append(" then 1 " );
          referSQL.append(" else 0 end) AS referin_inchangwat " );
          
          referSQL.append(" ,sum(case when ((b_visit_office_id_refer_in NOT IN (Select  hospital_incup_code from r_hospital_incup)) " );
          referSQL.append("AND query1.f_visit_refer_type_id = '0' ");
          referSQL.append("AND ((query1.changwat_referin <> '') ");
          referSQL.append("AND (query1.changwat_referin  NOT IN (select  substring(site_changwat,0,3) from b_site)))) " );
          referSQL.append(" then 1 " );
          referSQL.append(" else 0 end) AS referin_outchangwat " );
          
          referSQL.append(" ,sum(case when (b_visit_office_id_refer_out IN (Select  hospital_incup_code from r_hospital_incup) " );
          referSQL.append(" AND query1.f_visit_refer_type_id = '1' )" );
          referSQL.append(" then 1 " );
          referSQL.append(" else 0 end) AS referout_incup");
          
          referSQL.append(" ,sum(case when ((b_visit_office_id_refer_out NOT IN (Select  hospital_incup_code from r_hospital_incup)) " );
          referSQL.append(" AND query1.f_visit_refer_type_id = '1' " );          
          referSQL.append(" AND ((query1.changwat_referout <> '') " );
          referSQL.append(" AND (query1.changwat_referout  IN (select  substring(site_changwat,0,3) from b_site)))) " );
          referSQL.append(" then 1 " );
          referSQL.append(" else 0 end) AS referout_inchangwat " );
          
          referSQL.append(" ,sum(case when  ((b_visit_office_id_refer_out NOT IN (Select  hospital_incup_code from r_hospital_incup)) " );
          referSQL.append(" AND query1.f_visit_refer_type_id = '1' " );
          referSQL.append(" AND ((query1.changwat_referout <> '') ");
          referSQL.append(" AND (query1.changwat_referout  NOT IN (select  substring(site_changwat,0,3) from b_site)))) " );
          referSQL.append(" then 1 " );
          referSQL.append(" else 0 end) AS referout_outchangwat " );
          
          referSQL.append(" FROM " );
          referSQL.append(" r_plan_group  Left Join " );
          referSQL.append(" (SELECT  " );
          referSQL.append(" t_visit.t_visit_id " );
          referSQL.append(" ,t_visit.t_patient_id " );
          referSQL.append(" ,b_visit_office_id_refer_in " );
          referSQL.append(" ,b_visit_office_id_refer_out " );
          referSQL.append(" ,t_visit_refer_in_out.f_visit_refer_type_id");
          referSQL.append(" , CASE WHEN (r_plan_group_map_pttype.r_plan_group_id IS NULL OR " );
          referSQL.append(       " r_plan_group_map_pttype.r_plan_group_id = '' OR " );
          referSQL.append(       " r_plan_group_map_pttype.r_plan_group_id = 'null' )" );
          referSQL.append(   " THEN '8030000000006' " );
          referSQL.append(   " ELSE r_plan_group_map_pttype.r_plan_group_id " );
          referSQL.append(" END AS r_plan_group_id" );
          referSQL.append(" ,case when (b_visit_office_id_refer_in <> '') " );
          referSQL.append(" then (select visit_office_changwat from b_visit_office where b_visit_office_id = b_visit_office_id_refer_in) " );
          referSQL.append(" else '' " );
          referSQL.append(" end AS changwat_referin " );
          referSQL.append(" ,case when (b_visit_office_id_refer_out <> '') " );
          referSQL.append(" then (select visit_office_changwat from b_visit_office where b_visit_office_id = b_visit_office_id_refer_out) " );
          referSQL.append(" else '' " );
          referSQL.append(" end AS changwat_referout " );
          referSQL.append(" FROM  " );
          referSQL.append(" t_visit_refer_in_out INNER JOIN t_visit ON t_visit_refer_in_out.t_visit_id = t_visit.t_visit_id");
          referSQL.append(" INNER JOIN t_visit_payment ON t_visit.t_visit_id = t_visit_payment.t_visit_id  " );
          referSQL.append(" INNER JOIN b_contract_plans ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id " );
          referSQL.append(" LEFT JOIN r_plan_group_map_pttype ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype " );
          referSQL.append(" WHERE  " );
          referSQL.append("  (t_visit.f_visit_status_id <> '4' )  " );
          referSQL.append(" AND (t_visit_payment.visit_payment_priority = '0' )  " );
          referSQL.append(" AND (t_visit_payment.visit_payment_active = '1' )  " );
          referSQL.append(" AND t_visit_refer_in_out.visit_refer_in_out_active = '1' ");
          referSQL.append(" AND (SUBSTRING(t_visit.visit_financial_discharge_time,0,11) BETWEEN '" + startDate + "' and '" + endDate + "') " );
          referSQL.append(" ) AS query1 On r_plan_group.r_plan_group_id = query1.r_plan_group_id " );
          referSQL.append(" group by  " );
          referSQL.append(" r_plan_group.plan_group_description " );
          referSQL.append(" , r_plan_group.plan_group_number" );
          referSQL.append(" ,r_plan_group.r_plan_group_id " );
          referSQL.append(" order by  " );
          referSQL.append(" r_plan_group.plan_group_number ");

          System.out.println(referSQL.toString());
          eReferQuery(referSQL.toString());
          referSQL = null;
          */
         String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part1_refer.sql");
        PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        eReferQuery(pm.executeQuery());
 
          sql = null; 
          if(this.vReport.size()==0)
          {
              return null;
          }
          else
          {
              return this.vReport;
          }
     }
     
     public void eOPDQuery(ResultSet rs) throws Exception
    {
        this.vReport= new Vector();
        theRP115Group1OPD = new RP115Group1OPD();
    
        try{
            while(rs.next()) 
            {
                theRP115Group1OPD = new RP115Group1OPD();
                theRP115Group1OPD.setInitData();
                theRP115Group1OPD.opd_plan_type = rs.getString("plan_group_description");         
                theRP115Group1OPD.opd_new_patient_incup = rs.getString("patient_incup");
                theRP115Group1OPD.opd_new_patient_outcup = rs.getString("patient_outcup"); 
                theRP115Group1OPD.opd_visit_incup = rs.getString("visit_incup");
                theRP115Group1OPD.opd_visit_outcup = rs.getString("visit_outcup");
                
                this.vReport.add(theRP115Group1OPD);
                theRP115Group1OPD = null;
            }            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
    }   
     
     public void eIPDQuery(ResultSet rs) throws Exception
    {
       // rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group1IPD = new RP115Group1IPD();
    
        try{
            while(rs.next()) 
            {
                theRP115Group1IPD = new RP115Group1IPD();
                theRP115Group1IPD.setInitData();
                
                theRP115Group1IPD.ipd_plan_type = rs.getString(1);         
                theRP115Group1IPD.ipd_discharge_incup = rs.getString(2);
                theRP115Group1IPD.ipd_discharge_outcup = rs.getString(3);  
                theRP115Group1IPD.ipd_day_stay_incup = rs.getString(4);
                theRP115Group1IPD.ipd_day_stay_outcup = rs.getString(5);
                
                this.vReport.add(theRP115Group1IPD);
                theRP115Group1IPD = null;
            }            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
    }
     
     public void ePCUQuery(ResultSet rs) throws Exception
    {
        //rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group1PCU = new RP115Group1PCU(); 
    
        try{
            while(rs.next()) 
            {
                theRP115Group1PCU = new RP115Group1PCU(); 
                theRP115Group1PCU.setInitData();
                
                theRP115Group1PCU.pcu_plan_type = rs.getString(1);         
                theRP115Group1PCU.pcu_new_patient_incup = rs.getString(2);
                theRP115Group1PCU.pcu_new_patient_outcup = rs.getString(3);
                theRP115Group1PCU.pcu_visit_incup = rs.getString(4);
                theRP115Group1PCU.pcu_visit_outcup = rs.getString(5);
                
                this.vReport.add(theRP115Group1PCU);
                theRP115Group1PCU = null;
            }            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
    }
     
     public void eReferQuery(ResultSet rs) throws Exception
    {
        //rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group1Refer = new RP115Group1Refer();
    
        try{
            while(rs.next()) 
            {
                theRP115Group1Refer = new RP115Group1Refer();
                theRP115Group1Refer.setInitData();
                
                theRP115Group1Refer.refer_plan_type = rs.getString(1);         
                theRP115Group1Refer.refer_in_incup = rs.getString(2);
                theRP115Group1Refer.refer_in_inchangwat = rs.getString(3);
                theRP115Group1Refer.refer_in_outchangwat = rs.getString(4);
                theRP115Group1Refer.refer_out_incup = rs.getString(5);
                theRP115Group1Refer.refer_out_inchangwat = rs.getString(6);
                theRP115Group1Refer.refer_out_outchangwat = rs.getString(7);
                
                this.vReport.add(theRP115Group1Refer);
                theRP115Group1Refer = null;
            }            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
    }
     
    /**
     *  ใช้ในการ Convert Object เป็น String โดยที่รับ ค่า true หรือ false เพื่อสร้างหัวคอลัมน์หรือไม่
     *  ถ้าเป็น true จะให้แสดงหัวคอลัมน์ และใช้ Tab เป็นตัวคั่น 
     *  ถ้าเป็น false จะไม่แสดงไม่หัวคอลัมน์ และใช้ Pipe เป็นตัวคั่น
     *  @param vObject เป็น Vector ของ Obeject ชือ RP504Group
     *  @param isGetColumnName เป็น Boolean ถ้าเป็น true จะให้แสดงหัวคอลัมน์ ถ้าเป็น false จะไม่แสดงไม่หัวคอลัมน์ 
     *  @param obj เป็น String ที่ใช้เพื่อตรวจสอบว่า Object ใด ที่ถูกเก็บอยู่ใน Vector 
     *  @return เป็น String ของ Sql ที่มีการแปลงค่าเรียบร้อยแล้ว
     */
    public String convertToString(Vector vObject,boolean isGetColumnName)
    {
        StringBuffer sql = new StringBuffer();
        String separator = Constant.PIPE;
        
        if(vObject != null)
        {
                RP115Group1_2549 p = null;
                if(isGetColumnName)
                {
                    separator = Constant.TAB;
                    sql.append("ประเภทสิทธิ"+separator
                            +"ผู้ป่วยนอก คนใหม่ในปี(ในเครือข่าย)"+separator
                            +"ผู้ป่วยนอก คนใหม่ในปี(นอกเครือข่าย)"+separator
                            +"ผู้ป่วยนอก มาสถานบริการ(ในเครือข่าย)"+separator
                            +"ผู้ป่วยนอก มาสถานบริการ(นอกเครือข่าย)"+separator
                            +"ผู้ป่วยนอกที่ PCU ใน รพ คนใหม่ในปี(ในเครือข่าย)"+separator
                            +"ผู้ป่วยนอกที่ PCU ใน รพ คนใหม่ในปี(นอกเครือข่าย)"+separator
                            +"ผู้ป่วยนอกที่ PCU ใน รพ มาสถานบริการ(ในเครือข่าย)"+separator
                            +"ผู้ป่วยนอกที่ PCU ใน รพ มาสถานบริการ(นอกเครือข่าย)"+separator
                            +"ผู้ป่วยใน จำหน่ายในเดือน(ในเครือข่าย)"+separator
                            +"ผู้ป่วยใน จำหน่ายในเดือน(อกเครือข่าย)"+separator
                            +"ผู้ป่วยใน จำนวนวันนอน(ในเครือข่าย)"+separator
                            +"ผู้ป่วยใน จำนวนวันนอน(นอกเครือข่าย)"+separator
                            +"รับผู้ป่วยรักษาต่อ (ในเครือข่าย)"+separator
                            +"รับผู้ป่วยรักษาต่อ (ในจังหวัด)"+separator
                            +"รับผู้ป่วยรักษาต่อ (นอกจังหวัด)"+separator
                            +"ส่งผู้ป่วยรักษาต่อ (ในเครือข่าย)"+separator
                            +"ส่งผู้ป่วยรักษาต่อ (ในจังหวัด)"+separator
                            +"ส่งผู้ป่วยรักษาต่อ (นอกจังหวัด)"
                            +Constant.NEWLINE);
                }
                for(int i=0;i<vObject.size();i++)
                {
                    p = (RP115Group1_2549)vObject.elementAt(i);
                    sql.append(p.plan_type + separator
                            + p.opd_new_patient_incup + separator
                            + p.opd_new_patient_outcup + separator
                            + p.opd_visit_incup + separator
                            + p.opd_visit_outcup + separator
                            + p.pcu_new_patient_incup + separator
                            + p.pcu_new_patient_outcup + separator
                            + p.pcu_visit_incup + separator
                            + p.pcu_visit_outcup + separator
                            + p.ipd_discharge_incup + separator
                            + p.ipd_discharge_outcup + separator
                            + p.ipd_day_stay_incup + separator
                            + p.ipd_day_stay_outcup + separator
                            + p.refer_in_incup + separator
                            + p.refer_in_inchangwat + separator
                            + p.refer_in_outchangwat + separator
                            + p.refer_out_incup + separator
                            + p.refer_out_inchangwat + separator
                            + p.refer_out_outchangwat 
                            +Constant.NEWLINE);
                }
           
        }
        return sql.toString();
    }
    
}
