/*
 * RP115Group2_2549DB.java
 *
 * Created on 13 กันยายน 2548, 14:25 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.objdb;

import com.standardReport.object.RP115Group2Family_2549;
import com.standardReport.object.RP115Group2Post_2549;
import com.standardReport.object.RP115Group2Pre_2549;
import com.standardReport.object.RP115Group2_2549;
import java.util.*;
import java.sql.*;
import java.io.*;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.standardReport.subject.StandardDB;
import com.standardReport.utility.*;

/**
 *
 * @author nu_ojika
 */
public class RP115Group2_2549DB {
    
    /** Creates a new instance of RP115Group2_2549DB */
    public ConnectionInf theConnectionInf;
    
    RP115Group2Pre_2549 theRP115Group2Pre_2549;
    RP115Group2Post_2549 theRP115Group2Post_2549;
    RP115Group2Family_2549 theRP115Group2Family_2549;
    RP115Group2_2549 theRP115Group2_2549;
            
    ResultSet rs = null;
    String sql = "";
    Vector vReport;
    
    public RP115Group2_2549DB(ConnectionInf c) {
        
        theConnectionInf = c;
      
        theRP115Group2Pre_2549 = new RP115Group2Pre_2549();        
        theRP115Group2Post_2549 = new RP115Group2Post_2549();
        theRP115Group2Family_2549 = new RP115Group2Family_2549();
        theRP115Group2_2549 = new RP115Group2_2549();
        
    }
    
     public Vector selectGroup2PreByDate(String startDate, String endDate) throws Exception
     {
         /* this.sql = " SELECT " +
                     " r_uc_plan_group.uc_plan_group_description " +
                     " ,CASE WHEN (sum(query1.abort_ch) > 0) " +
                     "          THEN sum(query1.abort_ch) " +
                     "          ELSE 0 " +
                     " END AS abort_ch " +
                     " ,CASE WHEN (sum(query1.BirthAndDeath) > 0) " +
                     "          THEN sum(query1.BirthAndDeath) " +
                     "          ELSE 0 " +
                     " END AS BirthAndDeath " +
                     " ,CASE WHEN (sum(query1.Baby7DayDeath) > 0) " +
                     "          THEN sum(query1.Baby7DayDeath) " +
                     "          ELSE 0 " +
                     " END AS Baby7DayDeath " +
                     " ,CASE WHEN (sum(query1.MotherDeath) > 0) " +
                     "          THEN sum(query1.MotherDeath) " +
                     "          ELSE 0 " +
                     " END AS MotherDeath " +
                     " ,CASE WHEN (sum(query1.IPDDeath) > 0) " +
                     "          THEN sum(query1.IPDDeath) " +
                     "          ELSE 0 " +
                     " END AS IPDDeath " +
                     " ,CASE WHEN (sum(query1.OPDDeath) > 0) " +
                     "          THEN sum(query1.OPDDeath) " +
                     "          ELSE 0 " +
                     " END AS OPDDeath " +
                     " FROM " +
                     " r_uc_plan_group LEFT JOIN " +
                     " (SELECT 	" +
                     " CASE WHEN ((r_plan_group.plan_group_number = '1' ) " +
                                " OR (r_plan_group.plan_group_number = '2' )) " +
                            " THEN r_plan_group.plan_group_number " +
                            " WHEN (((r_plan_group.plan_group_number = '3' ) " +
                                " OR (r_plan_group.plan_group_number = '4' )) " +
                                " AND ((visit_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup)) " +
                                " OR (visit_payment_sub_hospital IN (Select hospital_incup_code from r_hospital_incup))) ) " +
                            " THEN '3' " +
                            " WHEN (((r_plan_group.plan_group_number = '3' ) " +
                                " OR (r_plan_group.plan_group_number = '4' )) " +
                                " AND ( ((t_visit_payment.visit_payment_main_hospital = '')  " +
                                " AND (t_visit_payment.visit_payment_sub_hospital = '')) " +
                                " OR ((t_visit_payment.visit_payment_main_hospital NOT IN (Select  hospital_incup_code " +
                                " from r_hospital_incup))  AND (visit_payment_sub_hospital NOT IN " +
                                " (Select  hospital_incup_code from r_hospital_incup))) )) " +
                            " THEN '4' " +
                            " WHEN ((r_plan_group.plan_group_number <> '1' ) AND (r_plan_group.plan_group_number <> '2' ) " +
                                " AND (r_plan_group.plan_group_number <> '3' ) AND (r_plan_group.plan_group_number <> '4' )) " +   
                            " THEN '5' " +
                     " END AS plan_group_id " +
                     " ,t_patient.t_patient_id " +
                     " ,sum(CASE WHEN (substring(t_diag_icd10.diag_icd10_number,0,4) >= 'O00' " +
                     " AND substring(t_diag_icd10.diag_icd10_number,0,4) <= 'O08') " +
                     " THEN 1  "+
                     " ELSE 0 " +
                     " END) AS abort_ch " +
                     " , CASE WHEN (substring(t_diag_icd10.diag_icd10_number,0,4) = 'P95') " +
                     " THEN 1 " +
                     " ELSE 0 " +
                     " END AS BirthAndDeath   " +
                     " ,CASE WHEN ((((t_visit.f_visit_opd_discharge_status_id = '52') " +
                     " OR (t_visit.f_visit_opd_discharge_status_id = '55')) " +
                     " OR (((t_visit.f_visit_ipd_discharge_type_id = '8') " +
                     " OR (t_visit.f_visit_ipd_discharge_type_id = '9')) " +
                     " OR ((t_visit.f_visit_ipd_discharge_status_id = '8') " +
                     " OR (t_visit.f_visit_ipd_discharge_status_id = '9')))) " +
                     " AND ((to_date(t_visit.visit_begin_visit_time,'yyyy-mm-dd') - to_date(t_patient.patient_birthday,'yyyy-mm-dd') ) <= 7)) " +
                     " THEN 1 " +
                     " ELSE 0 " +
                     " END AS Baby7DayDeath " +
                     " ,CASE WHEN ((((t_visit.f_visit_opd_discharge_status_id = '52') " +
                     " OR (t_visit.f_visit_opd_discharge_status_id = '55')) " +
                     " OR (((t_visit.f_visit_ipd_discharge_type_id = '8') " +
                     " OR (t_visit.f_visit_ipd_discharge_type_id = '9')) " +
                     " OR ((t_visit.f_visit_ipd_discharge_status_id = '8') " +
                     " OR (t_visit.f_visit_ipd_discharge_status_id = '9')))) " +
                     " AND (substring(t_diag_icd10.diag_icd10_number,0,4) >= 'O00' " +
                     " AND substring(t_diag_icd10.diag_icd10_number,0,4) <= 'O99')) " +
                     " THEN 1 " +
                     " ELSE 0 " +
                     " END AS MotherDeath " +
                     " , CASE WHEN (( (((t_visit.f_visit_ipd_discharge_type_id = '8') " +
                     " OR (t_visit.f_visit_ipd_discharge_type_id = '9'))  " +
                     " OR ((t_visit.f_visit_ipd_discharge_status_id = '8') " +
                     " OR (t_visit.f_visit_ipd_discharge_status_id = '9'))))  " +
                     " AND (t_visit.f_visit_type_id = '1')  " +
                     //" --AND ((to_date(t_visit.visit_begin_visit_time,'yyyy-mm-dd') - to_date(t_patient.patient_birthday,'yyyy-mm-dd')) >7)" +
                     " AND (substring(t_diag_icd10.diag_icd10_number,0,4) NOT IN ('O00','O01','O02','O03','O04','O05','O06','O07','O08')) )  "+
                     " THEN 1 " +
                     " ELSE 0 " +
                     " END AS IPDDeath  " +
                     " , CASE WHEN (((t_visit.f_visit_opd_discharge_status_id = '52') " +
                     " OR (t_visit.f_visit_opd_discharge_status_id = '55'))  " +
                     " AND (t_visit.f_visit_type_id = '0') " +
                    // " --AND ((to_date(t_visit.visit_begin_visit_time,'yyyy-mm-dd') - to_date(t_patient.patient_birthday,'yyyy-mm-dd') ) >7)" +
                     " AND (substring(t_diag_icd10.diag_icd10_number,0,4) NOT IN ('O00','O01','O02','O03','O04','O05','O06','O07','O08'))) " +
                     " THEN 1  " +
                     " ELSE 0 " +
                     " END AS OPDDeath  " +
                     " FROM  ((((((t_visit  INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id ) " +
                     "      INNER JOIN t_visit_payment ON t_visit.t_visit_id = t_visit_payment.t_visit_id) " +
                     "      INNER JOIN b_contract_plans " +
                     "      ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id)) " +
                     "      LEFT JOIN t_diag_icd10 ON  ((t_diag_icd10.diag_icd10_vn = t_visit.t_visit_id) " +
                                " AND (t_diag_icd10.f_diag_icd10_type_id = '1') AND t_diag_icd10.diag_icd10_active = '1' )) " +
                     "      LEFT JOIN r_plan_group_map_pttype " +
                     "      ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype) " +
                     "      LEFT JOIN r_plan_group" +
                     "      ON r_plan_group.r_plan_group_id = r_plan_group_map_pttype.r_plan_group_id  "+
                     " WHERE   (t_visit.f_visit_status_id <> '4' )  " +
                     "      AND (t_visit_payment.visit_payment_priority = '0' ) " +    
                     "      AND (t_visit_payment.visit_payment_active = '1' ) " +   
                     "      AND (SUBSTRING(t_visit.visit_financial_discharge_time,0,11) " +
                     "      BETWEEN '" + startDate + "' and '" + endDate + "')  " +
                     " GROUP BY   " +
                     " t_patient.t_patient_id " +
                     " ,plan_group_id  " +
                     " ,BirthAndDeath  ,Baby7DayDeath  ,MotherDeath  ,IPDDeath  ,OPDDeath  " +
                     " ORDER BY  plan_group_id " +
                     " ) AS query1 " +
                     " ON query1.plan_group_id = r_uc_plan_group.uc_plan_group_number " +
                     " GROUP BY " +
                     " r_uc_plan_group.uc_plan_group_description " +
                     " ,r_uc_plan_group.uc_plan_group_number " +
                     " ORDER BY " +
                     " r_uc_plan_group.uc_plan_group_number " ;                  
                   
          System.out.println(" SQL ส่วนหน้า : " + sql);
          ePreQuery(this.sql);
          */
         String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part2_pre.sql");
            PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
            pm.setString(1,startDate);
            pm.setString(2,endDate);
            ePreQuery(pm.executeQuery()); 

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
     
     public Vector selectGroup2PostByDate(String startDate, String endDate) throws Exception
     {
         /* this.sql = " SELECT " +
                     " r_uc_plan_group.uc_plan_group_description   " +
                     " ,CASE WHEN (sum(query1.BeforeMch) > 0) " +
                     "          THEN sum(query1.BeforeMch) " +
                     "          ELSE 0 " +
                     " END AS BeforeMch " +
                     " ,CASE WHEN (sum(query1.BeforeMch_visit) > 0) " +
                     "          THEN sum(query1.BeforeMch_visit) " +
                     "          ELSE 0 " +
                     " END AS BeforeMch_visit " +
                     " ,CASE WHEN (sum(query1.AfterMch) > 0) " +
                     "          THEN sum(query1.AfterMch) " +
                     "          ELSE 0 " +
                     " END AS AfterMch " +
                     " ,CASE WHEN (sum(query1.AfterMch_visit) > 0) " +
                     "          THEN sum(query1.AfterMch_visit) " +
                     "          ELSE 0 " +
                     " END AS AfterMch_visit " +
                     " ,CASE WHEN (sum(query1.BirthUsualInfant) > 0) " +
                     "          THEN sum(query1.BirthUsualInfant) " +
                     "          ELSE 0 " +
                     " END AS BirthUsualInfant " +
                     " ,CASE WHEN (sum(query1.BirthUnUsualInfant) > 0) " +
                     "          THEN sum(query1.BirthUnUsualInfant) " +
                     "          ELSE 0 " +
                     " END AS BirthUnUsualInfant " +
                     " ,CASE WHEN (sum(query1.treatment_dental) > 0) " +
                     "          THEN sum(query1.treatment_dental) " +
                     "          ELSE 0 " +
                     " END AS treatment_dental " +
                     " FROM " +
                     " r_uc_plan_group LEFT JOIN " +
                     "(SELECT " +
                     " CASE  WHEN ((r_plan_group.plan_group_number = '1' ) " +
                            "   OR (r_plan_group.plan_group_number = '2' )) " +
                            "   THEN  r_plan_group.plan_group_number " +
                            " WHEN (((r_plan_group.plan_group_number = '3' ) " +
                            "   OR (r_plan_group.plan_group_number = '4' ) )" +
                            "   AND ((visit_payment_main_hospital IN (Select  hospital_incup_code " +
                            "   FROM r_hospital_incup))  OR (visit_payment_sub_hospital IN " +
                            "   (Select  hospital_incup_code from r_hospital_incup))) )" +
                            "   THEN '3' " +
                            " WHEN (((r_plan_group.plan_group_number = '3' ) " +
                            "   OR (r_plan_group.plan_group_number = '4' ) )" +
                            "   AND ( ((t_visit_payment.visit_payment_main_hospital = '')" +
                            "   AND (t_visit_payment.visit_payment_sub_hospital = '')) " +
                            "   OR ((t_visit_payment.visit_payment_main_hospital NOT IN (Select  hospital_incup_code" +
                            "   FROM r_hospital_incup))  AND (visit_payment_sub_hospital NOT IN " +
                            "   (Select  hospital_incup_code from r_hospital_incup))) ))" +
                            "   THEN '4' " +
                            " WHEN ((r_plan_group.plan_group_number <> '1' ) " +
                            "   AND (r_plan_group.plan_group_number <> '2' )" +
                            "   AND (r_plan_group.plan_group_number <> '3' ) " +
                            "   AND (r_plan_group.plan_group_number <> '4' ) )" +
                            "   THEN  '5' " +             
                            " END AS plan_group_id " +
                     " ,t_patient.t_patient_id   " +
                     " , CASE WHEN ((substring(t_diag_icd10.diag_icd10_number,1,3) >= 'Z32'" +
                     " AND substring(t_diag_icd10.diag_icd10_number,1,3) <= 'Z36'))" +
                     " THEN 1" +
                     " ELSE 0" +
                     " END AS BeforeMch  " +                  
                     " , sum(CASE WHEN ((substring(t_diag_icd10.diag_icd10_number,1,3) >= 'Z32'" +
                     " AND substring(t_diag_icd10.diag_icd10_number,1,3) <= 'Z36')) " +
                     " THEN 1" +
                     " ELSE 0" +
                     " END) AS BeforeMch_visit " +
                     " , CASE WHEN (substring(t_diag_icd10.diag_icd10_number,1,3) = 'Z39')" +
                     " THEN 1" +
                     " ELSE 0" +
                     " END AS AfterMch  " +
                     " , sum(CASE WHEN (substring(t_diag_icd10.diag_icd10_number,1,3) = 'Z39')" +
                     " THEN 1  " +
                     " ELSE 0 " +
                     " END) AS AfterMch_visit  " +
                     " , CASE WHEN (substring(t_diag_icd10.diag_icd10_number,1,3) = 'Z38')" +
                     " THEN 1" +
                     " ELSE 0" +
                     " END AS BirthUsualInfant   " +
                     " , CASE WHEN ((substring(t_diag_icd10.diag_icd10_number,1,3) >= 'P00'" +
                     " AND substring(t_diag_icd10.diag_icd10_number,1,3) <= 'P15'))" +
                     " THEN 1" +
                     " ELSE 0  " +
                     " END AS BirthUnUsualInfant  " +
                     " , CASE WHEN ((substring(t_diag_icd10.diag_icd10_number,1,3) >= 'K00'" +
                     " AND substring(t_diag_icd10.diag_icd10_number,1,3) <= 'K14')) " +
                     " THEN 1" +
                     " ELSE 0 " +
                     " END AS treatment_dental " +
                     " FROM   ((((((t_visit  INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id )" +
                     " INNER JOIN t_visit_payment ON t_visit.t_visit_id = t_visit_payment.t_visit_id) " +
                     " INNER JOIN b_contract_plans " +
                     " ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id)) " +
                     " LEFT JOIN t_diag_icd10 ON  ((t_diag_icd10.diag_icd10_vn = t_visit.t_visit_id) " +
                        " AND (t_diag_icd10.f_diag_icd10_type_id = '1') AND t_diag_icd10.diag_icd10_active = '1' )) " +
                     " LEFT JOIN r_plan_group_map_pttype " +
                     " ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype)" +
                     " LEFT JOIN r_plan_group " +
                     " ON r_plan_group.r_plan_group_id = r_plan_group_map_pttype.r_plan_group_id  " + 
                     " WHERE  (t_visit.f_visit_status_id <> '4' )   " +
                     " AND (t_visit_payment.visit_payment_priority = '0' ) " +         
                     " AND (t_visit_payment.visit_payment_active = '1' ) " +    
                     " AND (SUBSTRING(t_visit.visit_financial_discharge_time,1,10) " +
                     " BETWEEN '" + startDate + "' and '" + endDate + "') " +
                     " GROUP BY  plan_group_id  " +
                     ",t_patient.t_patient_id  " +
                     " ,BeforeMch  ,AfterMch  ,BirthUsualInfant     " +
                     " ,BirthUnUsualInfant  ,treatment_dental " +
                     " ORDER BY  plan_group_id ) AS query1 " +
                     " ON query1.plan_group_id = r_uc_plan_group.uc_plan_group_number  " +
                     " GROUP BY " +
                     " r_uc_plan_group.uc_plan_group_description " +
                     " ,r_uc_plan_group.uc_plan_group_number " +
                     " ORDER BY " +
                     " r_uc_plan_group.uc_plan_group_number ";
          
          System.out.println(" SQL ส่วนหลัง : " + sql);
          ePostQuery(this.sql);
          */
         
         String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part2_post.sql");
            PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
            pm.setString(1,startDate);
            pm.setString(2,endDate);
            ePostQuery(pm.executeQuery()); 

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
     
    public Vector selectGroup2FamilyByDate(String startDate, String endDate,String startCheckDate, String endCheckDate, boolean isJan) throws Exception
    {
        /* StringBuffer strFamily = new StringBuffer();
         strFamily.append(" SELECT " );
         strFamily.append(" r_uc_plan_group.uc_plan_group_description " );
         strFamily.append(" ,CASE WHEN (sum(query1.childless_female) > 0) " );
         strFamily.append("          THEN sum(query1.childless_female) " );
         strFamily.append("          ELSE 0 " );
         strFamily.append(" END AS childless_female " );
         strFamily.append(" ,CASE WHEN (sum(query1.childless_male) > 0) " );
         strFamily.append("          THEN sum(query1.childless_male) " );
         strFamily.append("          ELSE 0 " );
         strFamily.append(" END AS childless_male " );
         strFamily.append(" ,CASE WHEN (sum(query1.childless_drug) > 0) " );
         strFamily.append("          THEN sum(query1.childless_drug) " );
         strFamily.append("          ELSE 0 " );
         strFamily.append(" END AS childless_drug " );
         strFamily.append(" ,CASE WHEN (sum(query1.childless_other) > 0) " );
         strFamily.append("          THEN sum(query1.childless_other) " );
         strFamily.append("          ELSE 0 " );
         strFamily.append(" END AS childless_other " );
         strFamily.append(" FROM " );
         strFamily.append(" r_uc_plan_group LEFT JOIN " );
         strFamily.append(" (SELECT " );
             strFamily.append(" CASE  WHEN ((r_plan_group.plan_group_number = '1' ) " );
                     strFamily.append("   OR (r_plan_group.plan_group_number = '2' ) ) " );
                     strFamily.append("   THEN  r_plan_group.plan_group_number " );
                     strFamily.append(" WHEN (((r_plan_group.plan_group_number = '3' ) " );
                     strFamily.append("   OR (r_plan_group.plan_group_number = '4' ) ) " );
                     strFamily.append("   AND ((visit_payment_main_hospital IN (Select  hospital_incup_code " );
                     strFamily.append("   FROM r_hospital_incup))  OR (visit_payment_sub_hospital IN " );
                     strFamily.append("   (Select  hospital_incup_code from r_hospital_incup))) ) " );
                     strFamily.append("   THEN '3' " );
                     strFamily.append(" WHEN (((r_plan_group.plan_group_number = '3' ) " );
                     strFamily.append("   OR (r_plan_group.plan_group_number = '4' ) )" );
                     strFamily.append("   AND ( ((t_visit_payment.visit_payment_main_hospital = '') " );
                     strFamily.append("   AND (t_visit_payment.visit_payment_sub_hospital = '')) " );
                     strFamily.append("   OR ((t_visit_payment.visit_payment_main_hospital NOT IN " );
                     strFamily.append("   (Select  hospital_incup_code " );
                     strFamily.append("   FROM r_hospital_incup))  AND (visit_payment_sub_hospital NOT IN " );
                     strFamily.append("   (Select  hospital_incup_code from r_hospital_incup))) ))" );
                     strFamily.append("   THEN '4' " );
                     strFamily.append(" WHEN ((r_plan_group.plan_group_number <> '1' ) " );
                     strFamily.append("   AND (r_plan_group.plan_group_number <> '2' ) " );
                     strFamily.append("   AND (r_plan_group.plan_group_number <> '3' ) " );
                     strFamily.append("   AND (r_plan_group.plan_group_number <> '4' ) )" );
                     strFamily.append("   THEN  '5' " );
                     strFamily.append("   END AS plan_group_id" );
             
             if(isJan)   
             {strFamily.append(" , CASE WHEN (t_health_family_planing.f_health_family_planing_method_id = '7')" );
                     strFamily.append(" THEN 1 " );
                     strFamily.append(" ELSE 0 " );
                     strFamily.append(" END AS  childless_female" );
                strFamily.append(" , CASE WHEN (t_health_family_planing.f_health_family_planing_method_id = '6') " );              
                     strFamily.append(" THEN 1 " );
                     strFamily.append(" ELSE 0 " );
                     strFamily.append(" END AS  childless_male " );
                strFamily.append(" , CASE WHEN (t_health_family_planing.f_health_family_planing_method_id = '4') " );
                     strFamily.append(" THEN 1 " );
                     strFamily.append(" ELSE 0 " );
                     strFamily.append(" END AS  childless_drug " );
                strFamily.append(" , CASE WHEN ((t_health_family_planing.f_health_family_planing_method_id <> '7')" );
                     strFamily.append(" AND (t_health_family_planing.f_health_family_planing_method_id <> '6') " );
                     strFamily.append(" AND (t_health_family_planing.f_health_family_planing_method_id <> '4')) " );          
                     strFamily.append(" THEN 1 " );
                     strFamily.append(" ELSE 0 " );
                     strFamily.append(" END AS  childless_other " );
             }
             else
             {   strFamily.append(" , CASE WHEN (t_health_family_planing.f_health_family_planing_method_id = '7')" );
                     strFamily.append(" AND t_health_family_planing.t_patient_id NOT IN (SELECT  distinct t_patient.t_patient_id " );
                     strFamily.append(" FROM   t_visit INNER JOIN t_patient " );
                     strFamily.append(" ON t_visit.t_patient_id = t_patient.t_patient_id  " );
                     strFamily.append(" where (t_visit.f_visit_status_id <> '4')  " );
                     strFamily.append(" AND ((SUBSTRING(t_visit.visit_begin_visit_time,1,7) >= '"+startCheckDate+"') " );
                     strFamily.append(" AND (SUBSTRING(t_visit.visit_begin_visit_time,1,7) < '"+endCheckDate+"'))  " );
                     strFamily.append(" Order by t_patient.t_patient_id)" );
                     strFamily.append(" THEN 1 " );
                     strFamily.append(" ELSE 0 " );
                     strFamily.append(" END AS  childless_female" );
                strFamily.append(" , CASE WHEN (t_health_family_planing.f_health_family_planing_method_id = '6') " );
                     strFamily.append(" AND t_health_family_planing.t_patient_id NOT IN (SELECT  distinct t_patient.t_patient_id  " );
                     strFamily.append(" FROM   t_visit INNER JOIN t_patient " );
                     strFamily.append(" ON t_visit.t_patient_id = t_patient.t_patient_id  " );
                     strFamily.append(" where (t_visit.f_visit_status_id <> '4')  " );
                     strFamily.append(" AND ((SUBSTRING(t_visit.visit_begin_visit_time,1,7) >= '"+startCheckDate+"') " );
                     strFamily.append(" AND (SUBSTRING(t_visit.visit_begin_visit_time,1,7) < '"+endCheckDate+"'))  " );
                     strFamily.append(" Order by t_patient.t_patient_id)" );             
                     strFamily.append(" THEN 1 " );
                     strFamily.append(" ELSE 0 " );
                     strFamily.append(" END AS  childless_male " );
                strFamily.append(" , CASE WHEN (t_health_family_planing.f_health_family_planing_method_id = '4') " );
                     strFamily.append(" AND t_health_family_planing.t_patient_id NOT IN (SELECT  distinct t_patient.t_patient_id " );
                     strFamily.append(" FROM   t_visit INNER JOIN t_patient " );
                     strFamily.append(" ON t_visit.t_patient_id = t_patient.t_patient_id  " );
                     strFamily.append(" where (t_visit.f_visit_status_id <> '4')  " );
                     strFamily.append(" AND ((SUBSTRING(t_visit.visit_begin_visit_time,1,7) >= '"+startCheckDate+"') " );
                     strFamily.append(" AND (SUBSTRING(t_visit.visit_begin_visit_time,1,7) < '"+endCheckDate+"'))  " );
                     strFamily.append(" Order by t_patient.t_patient_id)" );
                     strFamily.append(" THEN 1 " );
                     strFamily.append(" ELSE 0 " );
                     strFamily.append(" END AS  childless_drug " );
                strFamily.append(" , CASE WHEN ((t_health_family_planing.f_health_family_planing_method_id <> '7')" );
                     strFamily.append(" AND (t_health_family_planing.f_health_family_planing_method_id <> '6') " );
                     strFamily.append(" AND (t_health_family_planing.f_health_family_planing_method_id <> '4')) " );
                     strFamily.append(" AND t_health_family_planing.t_patient_id NOT IN (SELECT  distinct t_patient.t_patient_id " );
                     strFamily.append(" FROM   t_visit INNER JOIN t_patient " );
                     strFamily.append(" ON t_visit.t_patient_id = t_patient.t_patient_id  " );
                     strFamily.append(" where (t_visit.f_visit_status_id <> '4')  " );
                     strFamily.append(" AND ((SUBSTRING(t_visit.visit_begin_visit_time,1,7) >= '"+startCheckDate+"') " );
                     strFamily.append(" AND (SUBSTRING(t_visit.visit_begin_visit_time,1,7) < '"+endCheckDate+"'))  " );
                     strFamily.append(" Order by t_patient.t_patient_id)" );
                     strFamily.append(" THEN 1 " );
                     strFamily.append(" ELSE 0 " );
                     strFamily.append(" END AS  childless_other " );
       }
      strFamily.append(" FROM " );
      strFamily.append(" ((((t_visit INNER JOIN t_health_family_planing " );
      strFamily.append(" ON t_visit.t_visit_id = t_health_family_planing.t_visit_id) " );
      strFamily.append(" INNER JOIN t_visit_payment " );
      strFamily.append(" ON t_visit.t_visit_id = t_visit_payment.t_visit_id) " );
      strFamily.append(" INNER JOIN b_contract_plans " );
      strFamily.append(" ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id) " );
      strFamily.append(" LEFT JOIN r_plan_group_map_pttype " );
      strFamily.append(" ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype) " );
      strFamily.append(" LEFT JOIN r_plan_group " );
      strFamily.append(" ON r_plan_group.r_plan_group_id = r_plan_group_map_pttype.r_plan_group_id " );
      strFamily.append(" WHERE " );
      strFamily.append(" ((t_visit.f_visit_status_id <>'4') " );
      strFamily.append(" AND ((t_visit_payment.visit_payment_priority)='0') " );
      strFamily.append(" AND ((t_visit_payment.visit_payment_active)='1') " );
      strFamily.append(" AND ((SUBSTRING(visit_financial_discharge_time,0,11)) Between '" + startDate + "' And '" + endDate + "') " );
      strFamily.append(" AND ((t_health_family_planing.health_family_planing_active)='1')) " );
      strFamily.append(" )  AS query1 " );
      strFamily.append(" ON query1.plan_group_id = r_uc_plan_group.uc_plan_group_number " );
      strFamily.append(" GROUP BY " );
      strFamily.append(" r_uc_plan_group.uc_plan_group_number " );
      strFamily.append(" ,r_uc_plan_group.uc_plan_group_description " );
      strFamily.append(" ORDER BY " );
      strFamily.append(" r_uc_plan_group.uc_plan_group_number " );
               
               System.out.println(" SQL วางแผนครอบครัว : " + strFamily.toString());
               eFamilyQuery(strFamily.toString());
               strFamily = null;
         */
        String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part2_familyplaning.sql");
            PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
            pm.setString(1,startDate);
            pm.setString(2,endDate);
            eFamilyQuery(pm.executeQuery()); 

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
        
    
    public void ePreQuery(ResultSet rs)throws Exception
    {
        //rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group2Pre_2549 = new RP115Group2Pre_2549();
    
        try{
            while(rs.next()) 
            {
                theRP115Group2Pre_2549 = new RP115Group2Pre_2549();
                theRP115Group2Pre_2549.setInitData();
                
                theRP115Group2Pre_2549.plan_type = rs.getString(1);         
                theRP115Group2Pre_2549.abort = rs.getString(2);
                theRP115Group2Pre_2549.birth_and_death = rs.getString(3); 
                theRP115Group2Pre_2549.baby_death = rs.getString(4);
                theRP115Group2Pre_2549.mother_death = rs.getString(5);
                theRP115Group2Pre_2549.ipd_death = rs.getString(6);
                theRP115Group2Pre_2549.opd_death = rs.getString(7);
      
                
                this.vReport.add(theRP115Group2Pre_2549);
                theRP115Group2Pre_2549 = null;
            }            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
    } 
    
    public void ePostQuery(ResultSet rs)throws Exception
    {
        //rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group2Post_2549 = new RP115Group2Post_2549();
    
        try{
            while(rs.next()) 
            {
                theRP115Group2Post_2549 = new RP115Group2Post_2549();
                theRP115Group2Post_2549.setInitData();
                
                theRP115Group2Post_2549.plan_type = rs.getString(1);         
                theRP115Group2Post_2549.before_mch_patient = rs.getString(2);
                theRP115Group2Post_2549.before_mch_visit = rs.getString(3); 
                theRP115Group2Post_2549.after_mch_patient = rs.getString(4);
                theRP115Group2Post_2549.after_mch_visit = rs.getString(5);
                theRP115Group2Post_2549.birth_usual_infant = rs.getString(6);
                theRP115Group2Post_2549.birth_un_usual_infant = rs.getString(7);
                theRP115Group2Post_2549.treatment_dental = rs.getString(8);
                
                this.vReport.add(theRP115Group2Post_2549);
                theRP115Group2Post_2549 = null;
            }            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
    } 
    
    public void eFamilyQuery(ResultSet rs)throws Exception
    {
        //rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group2Family_2549 = new RP115Group2Family_2549();
        
        try
        {
            while(rs.next())
            {
                theRP115Group2Family_2549 = new RP115Group2Family_2549();
                theRP115Group2Family_2549.setInitData();
                
                theRP115Group2Family_2549.plan_type = rs.getString(1);
                theRP115Group2Family_2549.childless_female = rs.getString(2);
                theRP115Group2Family_2549.childless_male = rs.getString(3);
                theRP115Group2Family_2549.childless_drug = rs.getString(4);
                theRP115Group2Family_2549.childless_other = rs.getString(5);

                
                this.vReport.add(theRP115Group2Family_2549);
                theRP115Group2Family_2549 = null;
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
     *  @param vObject เป็น Vector ของ Obeject ชือ RP504Group_2549
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
                RP115Group2_2549 p = null;
                if(isGetColumnName)
                {
                    separator = Constant.TAB;
                    sql.append("ประเภทสิทธิ"+separator
                            +"แท้ง(ครั้ง)"+separator
                            +"เกิดไร้ชีพ(คน)"+separator
                            +"เด็กอายุต่ำกว่า 7 วันตาย(คน)"+separator
                            +"แม่ตาย(คน)"+separator
                            +"ตาย(ผู้ป่วยใน)"+separator
                            +"ตาย(ผู้ป่วยนอก)"+separator
                            +"ตรวจก่อนคลอด(คน)"+separator
                            +"ตรวจก่อนคลอด(ครั้ง)"+separator
                            +"ตรวจหลังคลอด(คน)"+separator
                            +"ตรวจหลังคลอด(ครั้ง)"+separator
                            +"ทารกคลอดปกติ(คน)"+separator
                            +"ทารกคลอดผิดปกติ(คน)"+separator
                            +"หมันหญิง(คน)"+separator
                            +"หมันชาย(คน)"+separator
                            +"ยาฝัง(คน)"+separator
                            +"อื่นๆ(คน)"+separator
                            +"รักษาทางทันตกรรม(คน)"
                            +Constant.NEWLINE);
                }
                for(int i=0;i<vObject.size();i++)
                {
                    p = (RP115Group2_2549)vObject.elementAt(i);
                    sql.append(p.plan_type + separator      
                            + p.abort + separator
                            + p.birth_and_death + separator
                            + p.baby_death + separator
                            + p.mother_death + separator
                            + p.ipd_death + separator
                            + p.opd_death + separator
                            + p.before_mch_patient + separator
                            + p.before_mch_visit + separator
                            + p.after_mch_patient + separator
                            + p.after_mch_visit + separator
                            + p.birth_usual_infant + separator
                            + p.birth_un_usual_infant + separator
                            + p.childless_female + separator
                            + p.childless_male + separator
                            + p.childless_drug + separator
                            + p.childless_other + separator
                            + p.treatment_dental
                            + Constant.NEWLINE);
                }
           
        }
        return sql.toString();
    }
    
}
