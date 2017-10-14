/*
 * RPPcuFamilyPlaningDB.java
 *
 * Created on 3 เมษายน 2549, 18:30 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalpcu.objdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalpcu.object.*;
import com.generalpcu.utility.Language;
import com.generalpcu.utility.DateUtil;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author Ojika
 */
public class RPPcuFamilyPlaningDB
{
    
    /** Creates a new instance of RPPcuFamilyPlaningDB */
    public ConnectionInf theConnectionInf;
    Vector vc;
    
    String SQL = "";
    ResultSet rs = null;
    ResultSetMetaData metadata;
    Vector vString;
    Vector vData;
    public RPPcuFamilyPlaningDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
    }
    
    /**
     * ดึงข้อมูล รายงานแสดงจำนวนและอัตราการวางแผนครอบครัว จำแนกรายหมู่บ้าน จาก บัญชี 6 หญิงวัยเจริญพันธุ์
     * @Author Ojika
     * @Date 03/04/2549
     **/
    public Vector queryAmountByVillageAndMarry(String selectVillage, String startDate, String endDate, int isQueryByDate)
    {   
        vc = null;
        
        SQL = " select " +
                    " query1.village_moo AS MOO " +
                    " ,query1.village_name " +
                    " ,query1.patient_marriage_status_description  AS marriage_status " +
                    " ,query2.count_woman " +	
                    " ,query1.method_all " +
                    // ปรับแก้อัตรา % ของการคุมกำเนิดใหม่ sumo 21/10/2549
                    " ,((query3.count_woman_planing * 100) / query2.count_woman) AS PERSENT_METHOD " + 
                    " ,query1.method_1 " +
                    " ,query1.method_2 " +
                    " ,query1.method_3 " +
                    " ,query1.method_4 " +
                    " ,query1.method_5 " +
                    " ,query1.method_6 " +
                    " ,query1.method_7 " +
                    " ,query1.method_8 " +
            " from  " +
                    " (select " +
                        " t_health_village.village_moo " +
                        " ,t_health_village.village_name " +
                        " ,f_patient_marriage_status.f_patient_marriage_status_id " +
                        " ,f_patient_marriage_status.patient_marriage_status_description " +
//                        " , sum(1) AS medthod_all " +    
                        " ,sum(case when (t_health_family_planing.f_health_family_planing_method_id = '1' ) " +
                            " then 1 " +
                            " else 0 " +
                        " end) AS  method_1 " +
                        " ,sum(case when (t_health_family_planing.f_health_family_planing_method_id = '2' ) " +
                            " then 1 " +
                            " else 0 " +
                        " end) AS  method_2 " +
                        " ,sum(case when (t_health_family_planing.f_health_family_planing_method_id = '3' ) " +
                            " then 1 " +
                            " else 0 " +
                        " end) AS  method_3 " +
                        " ,sum(case when (t_health_family_planing.f_health_family_planing_method_id = '4' ) " +
                            " then  1 " +
                            " else 0 " +
                        " end) AS  method_4 " +
                        " ,sum(case when (t_health_family_planing.f_health_family_planing_method_id = '5' ) " +
                            " then  1 " +
                            " else 0 " +
                        " end) AS  method_5 " +
                        " ,sum(case when (t_health_family_planing.f_health_family_planing_method_id = '6' ) " +
                            " then  1 " +
                            " else 0 " +
                        " end) AS  method_6 " +
                        " ,sum(case when (t_health_family_planing.f_health_family_planing_method_id = '7' ) " +
                            " then 1 " +
                            " else 0 " +
                        " end) AS  method_7 " +
                        " ,sum(case when (t_health_family_planing.f_health_family_planing_method_id = '9' ) " +
                            " then  1 " +
                            " else 0 " +
                        " end) AS  method_8 " +
                        // นับยอดวิธีการคุมกำเนิดทั้งหมด โดยต้องเป็นวิธี 1-7 และ 9 sumo 21/10/2549
                        " ,sum(case when (t_health_family_planing.f_health_family_planing_method_id = '1' " + 
                        " OR t_health_family_planing.f_health_family_planing_method_id = '2' " +
                        " OR t_health_family_planing.f_health_family_planing_method_id = '3' " +
                        " OR t_health_family_planing.f_health_family_planing_method_id = '4' " +
                        " OR t_health_family_planing.f_health_family_planing_method_id = '5' " +
                        " OR t_health_family_planing.f_health_family_planing_method_id = '6' " +
                        " OR t_health_family_planing.f_health_family_planing_method_id = '7' " +
                        " OR t_health_family_planing.f_health_family_planing_method_id = '9' )" + 
                        " then  1  else 0  end) AS  method_all" +
                    " from " +
                        " t_health_family_planing INNER JOIN t_health_family " +
                        " ON t_health_family_planing.t_health_family_id = t_health_family.t_health_family_id " +
                        " INNER JOIN t_health_home " +
                        " ON t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                        " INNER JOIN t_health_village  " +
                        " ON t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                        " INNER JOIN f_patient_marriage_status " +
                        " ON f_patient_marriage_status.f_patient_marriage_status_id = t_health_family.f_patient_marriage_status_id " +
                    " where " +
                        " t_health_family_planing.health_family_planing_active = '1' " +
                        " and t_health_family_planing.health_family_planing_date != '' ";
                
                if(!selectVillage.equalsIgnoreCase("0"))
                {
                        SQL = SQL + " AND t_health_village.t_health_village_id = '"+selectVillage+"' " ;
                }
                
                        SQL = SQL + " AND t_health_family.f_sex_id = '2'  " +
                        " AND f_patient_marriage_status.f_patient_marriage_status_id <> '6' " +
                        " AND ((to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                            " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') >= 15 ) " +
                            " AND  " +
                            " (to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                            " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') <= 44 )) ";
                        
                if(isQueryByDate == 1)  
                {
                    SQL = SQL + " AND substring(t_health_family_planing.health_family_planing_date,0,11) Between '"+startDate+"' And '"+endDate+"' ";	
                }
                else
                {
                    SQL = SQL + " AND substring(t_health_family_planing.health_family_planing_date,0,11) <= '"+endDate+"' ";
                }   
                        
                    SQL = SQL + " group by  " +
                            " t_health_village.village_moo " +
                            " ,t_health_village.village_name " +
                            " ,f_patient_marriage_status.f_patient_marriage_status_id " +
                            " ,f_patient_marriage_status.patient_marriage_status_description " +
                    " order by  " +
                            " t_health_village.village_moo " +
                            " ,t_health_village.village_name " +
                            " ,f_patient_marriage_status.f_patient_marriage_status_id " +
                            " ,f_patient_marriage_status.patient_marriage_status_description " +
                    " ) AS query1  " +

            " INNER JOIN  " +

                    " (select " +
                        " t_health_village.village_moo " +
                        " ,t_health_village.village_name    " +
                        " ,f_patient_marriage_status.f_patient_marriage_status_id " +
                        " ,f_patient_marriage_status.patient_marriage_status_description " +
                        " ,count(t_health_family.t_health_family_id) AS count_woman " +
                    " from " +
                        " t_health_family INNER JOIN t_health_home " +
                        " ON t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                        " INNER JOIN t_health_village  " +
                        " ON t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                        " INNER JOIN f_patient_marriage_status " +
                        " ON f_patient_marriage_status.f_patient_marriage_status_id = t_health_family.f_patient_marriage_status_id " +
                    " where " +	   
                        " t_health_family.f_sex_id = '2' ";
                if(!selectVillage.equalsIgnoreCase("0"))
                {
                        SQL = SQL + " AND t_health_village.t_health_village_id = '"+selectVillage+"' " ;
                }                                     
                            
                        SQL = SQL + " AND f_patient_marriage_status.f_patient_marriage_status_id <> '6' " +
                        " AND ((to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                                    " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') >= 15 ) " +
                                    " AND  " +
                                    " (to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                                    " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') <= 44 )) " +
                    " group by " +
                        " t_health_village.village_moo " +
                        " ,t_health_village.village_name    " +
                        " ,f_patient_marriage_status.f_patient_marriage_status_id " +
                        " ,f_patient_marriage_status.patient_marriage_status_description " +
                    " order by " +                    
                        " t_health_village.village_moo " +
                        " ,t_health_village.village_name    " +
                        " ,f_patient_marriage_status.f_patient_marriage_status_id " +
                        " ,f_patient_marriage_status.patient_marriage_status_description " +
                    " ) AS query2 " +	

            " ON query1.village_moo = query2.village_moo and query1.f_patient_marriage_status_id = query2.f_patient_marriage_status_id " +
            
            // นับจำนวนประชากรที่คุมกำเนิดในช่วงเวลาที่ต้องการ sumo 21/10/2549
            " INNER JOIN  " +

                    " (select count(DISTINCT(t_health_family_planing.t_health_family_id)) AS count_woman_planing" +
                        " ,t_health_village.village_moo " +
                        " ,t_health_village.village_name    " +
                        " ,f_patient_marriage_status.f_patient_marriage_status_id " +
                        " ,f_patient_marriage_status.patient_marriage_status_description " +
                    " from " +
                        " t_health_family_planing INNER JOIN t_health_family " +
                        " ON t_health_family.t_health_family_id = t_health_family_planing.t_health_family_id " +
                        " INNER JOIN t_health_home " + 
                        " ON t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                        " INNER JOIN t_health_village  " +
                        " ON t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                        " INNER JOIN f_patient_marriage_status " +
                        " ON f_patient_marriage_status.f_patient_marriage_status_id = t_health_family.f_patient_marriage_status_id " +
                    " where " +	   
                        " t_health_family.f_sex_id = '2' " +
                        " AND t_health_family_planing.f_health_family_planing_method_id <> '0' ";
                if(!selectVillage.equalsIgnoreCase("0"))
                {
                        SQL = SQL + " AND t_health_village.t_health_village_id = '"+selectVillage+"' " ;
                }                                     
                            
                        SQL = SQL + " AND f_patient_marriage_status.f_patient_marriage_status_id <> '6' " +
                        " AND ((to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                                    " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') >= 15 ) " +
                                    " AND  " +
                                    " (to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                                    " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') <= 44 )) ";
               if(isQueryByDate == 1)  
                {
                    SQL = SQL + " AND substring(t_health_family_planing.health_family_planing_date,0,11) Between '"+startDate+"' And '"+endDate+"' ";	
                }
                else
                {
                    SQL = SQL + " AND substring(t_health_family_planing.health_family_planing_date,0,11) <= '"+endDate+"' ";
                }
                    SQL = SQL + " group by " +
                        " t_health_village.village_moo " +
                        " ,t_health_village.village_name    " +
                        " ,f_patient_marriage_status.f_patient_marriage_status_id " +
                        " ,f_patient_marriage_status.patient_marriage_status_description " +
                    " order by " +                    
                        " t_health_village.village_moo " +
                        " ,t_health_village.village_name    " +
                        " ,f_patient_marriage_status.f_patient_marriage_status_id " +
                        " ,f_patient_marriage_status.patient_marriage_status_description " +
                    " ) AS query3 " +	

            " ON query1.village_moo = query3.village_moo and query1.f_patient_marriage_status_id = query3.f_patient_marriage_status_id ";
        
        System.out.println(" queryFpPCU 1: " + SQL);
            
        try
        {            
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
    
    /**
     * ดึงข้อมูล รายงานแสดงจำนวนและอัตราการวางแผนครอบครัว จำแนกรายหมู่บ้าน
     * @Author Ojika
     * @Date 03/04/2549
     **/
    public Vector queryAmountByVillage(String selectVillage, String startDate, String endDate, int isQueryByDate)
    {   
        vc = null;
        
        SQL = " select " +
                    " query1.village_moo AS MOO " +
                    " ,query1.village_name " +
                    " ,query2.count_woman " +
                    " ,query1.method_all " +
                    // ปรับแก้อัตรา % ของการคุมกำเนิดใหม่ sumo 21/10/2549
                    " ,((query3.count_woman_planing * 100) / query2.count_woman) AS PERSENT_METHOD " + 
                    " ,query1.method_1 " +
                    " ,query1.method_2 " +
                    " ,query1.method_3 " +
                    " ,query1.method_4 " +
                    " ,query1.method_5 " +
                    " ,query1.method_6 " +
                    " ,query1.method_7 " +
                    " ,query1.method_8 " +
                " from  " +
                    " (select " +
                        " t_health_village.village_moo AS village_moo " +
                        " ,t_health_village.village_name " +
//                        " , sum(1) AS medthod_all  " +   
                        " ,sum(case when (t_health_family_planing.f_health_family_planing_method_id = '1' ) " +
                            " then 1 " +
                            " else 0 " +
                        " end) AS  method_1 " +
                        " ,sum(case when (t_health_family_planing.f_health_family_planing_method_id = '2' ) " +
                            " then 1 " +
                            " else 0 " +
                        " end) AS  method_2 " +
                        " ,sum(case when (t_health_family_planing.f_health_family_planing_method_id = '3' ) " +
                            " then 1 " +
                            " else 0 " +
                        " end) AS  method_3 " +
                        " ,sum(case when (t_health_family_planing.f_health_family_planing_method_id = '4' ) " +
                            " then  1 " +
                            " else 0 " +
                        " end) AS  method_4 " +
                        " ,sum(case when (t_health_family_planing.f_health_family_planing_method_id = '5' ) " +
                            " then  1 " +
                            " else 0 " +
                        " end) AS  method_5 " +
                        " ,sum(case when (t_health_family_planing.f_health_family_planing_method_id = '6' ) " +
                            " then  1 " +
                            " else 0 " +
                        " end) AS  method_6 " +
                        " ,sum(case when (t_health_family_planing.f_health_family_planing_method_id = '7' ) " +
                            " then 1 " +
                            " else 0 " +
                        " end) AS  method_7 " +
                        " ,sum(case when (t_health_family_planing.f_health_family_planing_method_id = '9' ) " +
                            " then  1 " +
                            " else 0 " +
                        " end) AS  method_8 " +
                        // นับยอดวิธีการคุมกำเนิดทั้งหมด โดยต้องเป็นวิธี 1-7 และ 9 sumo 21/10/2549
                        " ,sum(case when (t_health_family_planing.f_health_family_planing_method_id = '1' " + 
                        " OR t_health_family_planing.f_health_family_planing_method_id = '2' " +
                        " OR t_health_family_planing.f_health_family_planing_method_id = '3' " +
                        " OR t_health_family_planing.f_health_family_planing_method_id = '4' " +
                        " OR t_health_family_planing.f_health_family_planing_method_id = '5' " +
                        " OR t_health_family_planing.f_health_family_planing_method_id = '6' " +
                        " OR t_health_family_planing.f_health_family_planing_method_id = '7' " +
                        " OR t_health_family_planing.f_health_family_planing_method_id = '9' )" + 
                        " then  1  else 0  end) AS  method_all" +
                    " from " +
                        " t_health_family_planing INNER JOIN t_health_family " +
                        " ON t_health_family_planing.t_health_family_id = t_health_family.t_health_family_id " +
                        " INNER JOIN t_health_home " +
                        " ON t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                        " INNER JOIN t_health_village  " +
                        " ON t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                        " INNER JOIN f_patient_marriage_status " +
                        " ON f_patient_marriage_status.f_patient_marriage_status_id = t_health_family.f_patient_marriage_status_id " +
                    " where " +
                        " t_health_family_planing.health_family_planing_active = '1' " +
                        " and t_health_family_planing.health_family_planing_date != '' ";
                    if(!selectVillage.equalsIgnoreCase("0"))
                    {
                        SQL = SQL + " AND t_health_village.t_health_village_id = '"+selectVillage+"' ";
                    }
                                
                        SQL = SQL + " AND t_health_family.f_sex_id = '2' " +
                        " AND f_patient_marriage_status.f_patient_marriage_status_id <> '6' " +
                        " AND ((to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                            " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') >= 15 ) " +
                            " AND  " +
                            " (to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                            " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') <= 44 )) ";
                        
                if(isQueryByDate == 1)
                {
                    SQL = SQL + " AND substring(t_health_family_planing.health_family_planing_date,0,11) Between '"+startDate+"' And '"+endDate+"' " ;
                }
                else
                {
                    SQL = SQL + " AND substring(t_health_family_planing.health_family_planing_date,0,11) <= '"+endDate+"' " ;
                }		
                                
                    SQL = SQL + " group by  " +
                            " t_health_village.village_moo " +
                            " ,t_health_village.village_name " +
                    " order by  " +
                            " t_health_village.village_moo " +
                            " ,t_health_village.village_name " +
                    " ) AS query1  " +

                    " INNER JOIN  " +

                    " (select " +
                        " t_health_village.village_moo AS village_moo " +
                        " ,t_health_village.village_name " +   
                        " ,count(t_health_family.t_health_family_id) AS count_woman " +
                    " from " +
                        " t_health_family INNER JOIN t_health_home " +
                        " ON t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                        " INNER JOIN t_health_village " +
                        " ON t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                        " INNER JOIN f_patient_marriage_status " +
                        " ON f_patient_marriage_status.f_patient_marriage_status_id = t_health_family.f_patient_marriage_status_id " +
                    " where " +                        
                        " t_health_family.f_sex_id = '2' ";
                        
                    if(!selectVillage.equalsIgnoreCase("0"))
                    {
                        SQL = SQL + " AND t_health_village.t_health_village_id = '"+selectVillage+"' ";
                    }
                                
                        SQL = SQL + " AND f_patient_marriage_status.f_patient_marriage_status_id <> '6' " +
                        " AND ((to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                                    " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') >= 15 ) " +
                                    " AND  " +
                                    " (to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                                    " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') <= 44 )) " +
                    " group by " +
                        " t_health_village.village_moo " +
                        " ,t_health_village.village_name   " + 
                    " order by " +
                        " t_health_village.village_moo " +
                        " ,t_health_village.village_name   " + 
                    " ) AS query2 " +	

                    " ON query1.village_moo = query2.village_moo " +
                    // นับจำนวนประชากรที่คุมกำเนิดในช่วงเวลาที่ต้องการ sumo 21/10/2549
                    " INNER JOIN   (select count(DISTINCT(t_health_family_planing.t_health_family_id)) AS count_woman_planing " +
                        " ,t_health_village.village_moo AS village_moo " +
                        " ,t_health_village.village_name " +   
                    " from " +
                        " t_health_family_planing INNER JOIN t_health_family " +
                        " ON t_health_family.t_health_family_id = t_health_family_planing.t_health_family_id  " +
                        " INNER JOIN t_health_home " + 
                        " ON t_health_family.t_health_home_id = t_health_home.t_health_home_id" +
                        " INNER JOIN t_health_village " +
                        " ON t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                    " where " +                        
                        " t_health_family.f_sex_id = '2' " +
                        " AND t_health_family_planing.f_health_family_planing_method_id <> '0' ";
                if(!selectVillage.equalsIgnoreCase("0"))
                {
                        SQL = SQL + " AND t_health_village.t_health_village_id = '"+selectVillage+"' " ;
                }                                     
                            
                        SQL = SQL + " AND f_patient_marriage_status.f_patient_marriage_status_id <> '6' " +
                        " AND ((to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                                    " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') >= 15 ) " +
                                    " AND  " +
                                    " (to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                                    " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') <= 44 )) ";
               if(isQueryByDate == 1)  
                {
                    SQL = SQL + " AND substring(t_health_family_planing.health_family_planing_date,0,11) Between '"+startDate+"' And '"+endDate+"' ";	
                }
                else
                {
                    SQL = SQL + " AND substring(t_health_family_planing.health_family_planing_date,0,11) <= '"+endDate+"' ";
                }
                    SQL = SQL + " group by " +
                        " t_health_village.village_moo " +
                        " ,t_health_village.village_name    " +
                    " order by " +                    
                        " t_health_village.village_moo " +
                        " ,t_health_village.village_name    " +
                    " ) AS query3 " +	

                    " ON query1.village_moo = query3.village_moo ";
        
        System.out.println(" queryFpPCU 2 : " + SQL);
            
        try
        {            
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
    
    /**
     * ดึงข้อมูล รายงานแสดงรายชื่อของหญิงที่มีการวางแผนครอบครัว
     * @Author Ojika
     * @Date 03/04/2549
     **/
    public Vector queryListFpByVillage(String selectVillage, String startDate, String endDate, int isQueryByDate)
    {   
        vc = null;
        
        SQL = " select " + 
                    " t_health_village.village_moo AS MOO" +
                    " ,t_health_village.village_name " +
                    " ,case when (f_patient_prefix.patient_prefix_description IS NOT NULL) " +
                        " then f_patient_prefix.patient_prefix_description " +
                        " else '' " +
                    " end AS PREFIX " +
                    " ,case when (t_health_family.patient_name IS NOT NULL) " +
                        " then t_health_family.patient_name " +
                        " else '' " +
                    " end AS FIRSTNAME " +
                    " ,case when (t_health_family.patient_last_name IS NOT NULL) " +
                        " then t_health_family.patient_last_name " +
                        " else ''  " +
                    " end AS LASTNAME " +
                    " ,case when ((t_health_family.patient_birthday IS NOT NULL) " +
                                " AND ((t_health_family_planing.health_family_planing_date IS NOT NULL) " +
                                " OR (t_health_family_planing.health_family_planing_date != ''))  " +
                                " AND (substring(' ' || age(to_date(substring(t_health_family_planing.health_family_planing_date,0,11),'YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ) IS NOT NULL) " +
                        " then (substring(' ' || age(to_date(substring(t_health_family_planing.health_family_planing_date,0,11),'YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ) " +
                        " else '0' " +
                        " end AS YEAR " +
                        " ,case when ((t_health_family.patient_birthday IS NOT NULL) " +
                                " AND ((t_health_family_planing.health_family_planing_date IS NOT NULL) " +
                                " OR (t_health_family_planing.health_family_planing_date != ''))  " +
                                " AND (substring(' ' || age(to_date(substring(t_health_family_planing.health_family_planing_date,0,11),'YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') ) IS NOT NULL) " +
                        " then (substring(' ' || age(to_date(substring(t_health_family_planing.health_family_planing_date,0,11),'YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') ) " +
                        " else '0' " +
                    " end AS MONTH " +
                    " ,case when ((t_health_family.patient_birthday IS NOT NULL) " +
                                " AND ((t_health_family_planing.health_family_planing_date IS NOT NULL) " +
                                " OR (t_health_family_planing.health_family_planing_date != ''))  " +
                                " AND (substring(' ' || age(to_date(substring(t_health_family_planing.health_family_planing_date,0,11),'YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)day') ) IS NOT NULL) " +
                        " then (substring(' ' || age(to_date(substring(t_health_family_planing.health_family_planing_date,0,11),'YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)day') ) " +
                        " else '0' " +
                    " end AS DAY " +    
                    " ,case when (t_health_home.health_home_house IS NOT NULL) " +
                        " then t_health_home.health_home_house " +
                        " else '' " +
                     " end AS HOUSE " +
                    " ,case when (f_health_family_planing_method.health_family_planing_method_description IS NOT NULL) " +
                        " then f_health_family_planing_method.health_family_planing_method_description " +
                        " else '' " +
                     " end AS UNIT_TYPE " +
                     " , case when ((t_health_family_planing.health_family_planing_date IS NOT NULL) " +
                         " AND (t_health_family_planing.health_family_planing_date != '')) " +
                         " then ( substring(t_health_family_planing.health_family_planing_date,0,11)) " +
                         " else '' " +
                     " end AS FAMILY_PLANING_DATE " +
                " from " +
                    " t_health_family_planing INNER JOIN f_health_family_planing_method " +
                    " ON t_health_family_planing.f_health_family_planing_method_id = f_health_family_planing_method.f_health_family_planing_method_id " +
                    " INNER JOIN t_health_family " +
                    " ON t_health_family_planing.t_health_family_id = t_health_family.t_health_family_id " +
                    " INNER JOIN t_health_home " +
                    " ON t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                    " INNER JOIN t_health_village  " +
                    " ON t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                    " LEFT JOIN f_patient_prefix " +
                    " ON t_health_family.f_prefix_id = f_patient_prefix.f_patient_prefix_id " +
                " where " +
                 " t_health_family_planing.health_family_planing_active = '1' " +
                 " and t_health_family_planing.health_family_planing_date != '' ";
                
                if(!selectVillage.equalsIgnoreCase("0"))
                {
                    SQL = SQL + " AND t_health_village.t_health_village_id = '"+selectVillage+"' ";
                }                
                
                    SQL = SQL + " AND t_health_family_planing.f_health_family_planing_method_id <> '0' " +
                    " AND t_health_family.f_sex_id = '2' " +
                    " AND f_patient_marriage_status.f_patient_marriage_status_id <> '6' " +
                            " AND ((to_number(substring(' ' || age(to_date(substring(t_health_family_planing.health_family_planing_date,0,11),'YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') >= 15 ) " +
                                " AND  " +
                                " (to_number(substring(' ' || age(to_date(substring(t_health_family_planing.health_family_planing_date,0,11),'YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') <= 44 )) ";
                
                if(isQueryByDate == 1)
                {
                    SQL = SQL + " AND substring(t_health_family_planing.health_family_planing_date,0,11) Between '"+startDate+"' And '"+endDate+"' ";
                }
                else
                {
                    SQL = SQL + " AND substring(t_health_family_planing.health_family_planing_date,0,11) <= '"+endDate+"' ";
                }
                
                
                SQL = SQL + " group by " +
                    " t_health_village.village_moo " +
                    " ,f_patient_prefix.patient_prefix_description " +
                    " ,t_health_village.village_name " +
                    " ,t_health_family.patient_name " +
                    " ,t_health_family.patient_last_name " +
                    " ,t_health_family.patient_birthday " +
                    " ,t_health_home.health_home_house " +
                    " ,f_health_family_planing_method.health_family_planing_method_description " +
                    " ,t_health_family_planing.health_family_planing_date " +
                    " order by " +
                    " t_health_village.village_moo " +
                    " ,t_health_village.village_name " +
                    " ,t_health_family.patient_name " +
                    " ,t_health_family.patient_last_name " +
                    " ,t_health_family.patient_birthday " +                    
                    " ,t_health_home.health_home_house " +
                    " ,f_health_family_planing_method.health_family_planing_method_description " +
                    " ,t_health_family_planing.health_family_planing_date ";
        
        System.out.println(" queryFpPCU 3 : " + SQL);
            
        try
        {            
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
    
    /**
     * ดึงข้อมูล รายชื่อหญิงวัยเจริญพันธุ์(อายุ 15-44 ปี;สมรส)ที่ไม่ได้รับการวางแผนครอบครัวในปัจจุบัน
     * @Author Ojika
     * @Date 03/04/2549
     **/
    public Vector queryListNotFp(String selectVillage, String startDate, String endDate, int isQueryByDate)
    {   
        vc = null;
        
        SQL = " select " +
                    " t_health_village.village_moo AS MOO " +
                    " ,t_health_village.village_name " +
                    " ,case when (f_patient_prefix.patient_prefix_description IS NOT NULL) " +
                        " then f_patient_prefix.patient_prefix_description " +
                        " else '' " +
                     " end AS PREFIX " +
                    " ,case when (t_health_family.patient_name IS NOT NULL) " +
                        " then t_health_family.patient_name " +
                        " else '' " +
                     " end AS FIRSTNAME " +
                    " ,case when (t_health_family.patient_last_name IS NOT NULL) " +
                        " then t_health_family.patient_last_name " +
                        " else ''  " +
                    " end AS LASTNAME " +
                    " ,case when ((t_health_family.patient_birthday IS NOT NULL) " +
                                "  AND (substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ) IS NOT NULL) " +
                        " then (substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ) " +
                        " else '0' " +
                    " end AS YEAR " +
                    " ,case when ((t_health_family.patient_birthday IS NOT NULL) " +
                               " AND (substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                               " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') ) IS NOT NULL) " +
                        " then (substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                               " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') ) " +
                        " else '0' " +
                    " end AS MONTH " +
                    " ,case when ((t_health_family.patient_birthday IS NOT NULL) " +
                               " AND (substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                               " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)day') ) IS NOT NULL) " +
                        " then (substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                               " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)day') ) " +
                        " else '0' " +
                    " end AS DAY  " +   
                    " ,case when (t_health_home.health_home_house IS NOT NULL) " +
                        " then t_health_home.health_home_house " +
                        " else '' " +
                     " end AS HOUSE " +
                " from " +
                    " t_health_family INNER JOIN t_health_home " +
                    " ON t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                    " INNER JOIN t_health_village  " +
                    " ON t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                    " LEFT JOIN f_patient_prefix " +
                    " ON t_health_family.f_prefix_id = f_patient_prefix.f_patient_prefix_id " +
                " where " +                    
                    " t_health_family.f_patient_marriage_status_id = '2' ";
                    
                if(!selectVillage.equalsIgnoreCase("0"))
                {
                    SQL = SQL + " AND t_health_village.t_health_village_id = '"+selectVillage+"' ";
                }
        
                    SQL = SQL + " AND t_health_family.f_sex_id = '2' " +
                    " AND ((to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                               "  , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') >= 15 ) " +
                               "  AND  " +
                                " (to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') <= 44 )) " +
                   "  AND t_health_family.t_health_family_id NOT IN  " +
                        " (select  distinct  t_health_family_id  from  t_health_family_planing  " +
                            " where t_health_family_planing.f_health_family_planing_method_id <> '0' ";
                            
                    if(isQueryByDate == 1)
                    {
                       SQL = SQL + " and substring(t_health_family_planing.health_family_planing_date,0,11) Between '"+startDate+"' And '"+endDate+"') ";
                    }
                    else
                    {
                        SQL = SQL + " and substring(t_health_family_planing.health_family_planing_date,0,11) <= '"+endDate+"') ";
                    }
                                                            
                SQL = SQL + " order by " +
                     " t_health_village.village_moo " +
                    " ,t_health_village.village_name " +
                    " ,t_health_family.patient_name " +
                    " ,t_health_family.patient_last_name " +
                    " ,t_health_family.patient_birthday " +
                    " ,t_health_home.health_home_house ";
        
        System.out.println(" queryFpPCU 4 : " + SQL);
            
        try
        {            
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
                        if(metadata.getColumnTypeName(i+1).equalsIgnoreCase("date"))
                        { 
                            /*boolean flag = true;
                            try
                            { int x = Integer.parseInt(rowdata[i]);
                              flag = true; 
                            }
                            catch(NumberFormatException e)
                            {
                              flag = false; 
                            }*/
                            if(rowdata[i].trim().length() != 0 /*&& flag == true*/)
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
