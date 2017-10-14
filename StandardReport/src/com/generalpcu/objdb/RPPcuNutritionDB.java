/*
 * RPPcuNutritionDB.java
 *
 * Created on 11 กุมภาพันธ์ 2549, 10:03 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalpcu.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalpcu.utility.Language;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

/**
 *
 * @author pu
 */
public class RPPcuNutritionDB
{
    public ConnectionInf theConnectionInf;
    String SQL = "";
    Vector vc;
    ResultSet rs = null;
    ResultSetMetaData metadata;
    Vector vPateintInservicePoint ;
    Vector vString;
    Vector vData;
    private int columnsize;
    private int str;
    /** Creates a new instance of RPPcuNutritionDB */
    public RPPcuNutritionDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    
    /**
     *ค้นหาจำนวนเด็ก 0-6 ปี ทั้งหมดที่อยู่ในเขต ตามวันที่เริ่มต้น และสิ้นสุด 
     *สำหรับรายงานโภชนาการ
     *@param startDate เป็น String ที่เก็บวันที่เริ่มต้นในการดึงข้อมูล
     *@param endDate เป็น String ที่เก็บวันที่สิ้นสุดในการดึงข้อมูล
     *@return Vector ของจำนวนเด็ก 0-6ปี
     *@Author pu
     *@Date 11/02/2006
     */
    public Vector queryChildAllNutritionByDate(String startDate,String endDate)
    {
        vc  =null;
        try
        {     SQL = " SELECT " +
                            " t_health_village.village_moo AS moo " +
                            " ,t_health_village.village_name AS village_name " +
                            " ,COUNT(t_health_family.t_health_family_id ) AS patient_child " +
                    " FROM " +
                            " t_health_home  INNER JOIN  t_health_family " +
                                            " ON t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                                       " INNER JOIN t_health_village " +
                                            " ON (t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                                                    " AND t_health_village.village_moo <> '0') " +
                    " WHERE " + 
                            " (substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD') " +
                                                                 " ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year')) IS NULL " +
                                                            " OR  to_number(substring(' ' || age(to_date('"+startDate+"' " +
                                                          " ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') < 6 " +
                                                            " OR ( to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD'), " +
                                                                                                                 " to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') = 6 " +
                                                                    " AND " +
                                                                    " to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD'), " +
                                                                                                                 " to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon'),'999') IS NULL ) " +
                    " GROUP BY " +
                            " t_health_village.village_moo " +
                            " ,t_health_village.village_name " +
                    " ORDER BY " +
                            " t_health_village.village_moo"; 
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
    
    /**
     *ค้นหาจำนวนเด็ก 0-6 ปีที่มีข้อมูลการลง Vitalsign การชั่งน้ำหนัก และค่า BMI 
     *และ นับจำนวนเด็ก 0-6 ปี แยกตามระดับโภชนาการ ตามวันที่เริ่มต้น และสิ้นสุด 
     *สำหรับรายงานโภชนาการ
     *@param startDate เป็น String ที่เก็บวันที่เริ่มต้นในการดึงข้อมูล
     *@param endDate เป็น String ที่เก็บวันที่สิ้นสุดในการดึงข้อมูล
     *@return Vector ของจำนวนเด็ก 0-6ปี
     *@Author pu
     *@Date 28/04/2006
     */
    public Vector queryNutritrionByCriteria(String startDate,String endDate)
    {
        try
        {
            SQL = " SELECT t_health_village.village_moo AS moo " +
                 " ,COUNT(query1.t_health_family_id) AS child_weight " +
                 " ,CASE WHEN (SUM(query1.more_over) >0 ) " +  
                                        " THEN SUM(query1.more_over) " +
                                        " ELSE 0 " + 
                  " END AS more_over " +
                  " ,CASE WHEN (SUM(query1.more_rather) >0 ) " +  
                                        " THEN SUM(query1.more_rather) " +
                                        " ELSE 0 " + 
                  " END AS more_rather " +
                  " ,CASE WHEN (SUM(query1.normal) >0 ) " +  
                                        " THEN SUM(query1.normal) " +
                                        " ELSE 0 " + 
                  " END AS normal " +
                  " ,CASE WHEN (SUM(query1.less_rather) >0 ) " +  
                                        " THEN SUM(query1.less_rather) " +
                                        " ELSE 0 " + 
                  " END AS less_rather " +
                  " ,CASE WHEN (SUM(query1.slighted_less) >0 ) " +  
                                        " THEN SUM(query1.slighted_less) " +
                                        " ELSE 0 " + 
                  " END AS slighted_less " +
                  " FROM " +
                        " t_health_village LEFT JOIN " +
                        " (SELECT t_health_family.t_health_family_id " +
                        " ,t_health_village.t_health_village_id " +
                        " ,t_health_village.village_moo " +
                        " ,CASE WHEN ((t_visit_vital_sign.f_visit_nutrition_level_id IN (SELECT b_nutrition_map.f_visit_nutrition_level_old  " +
                                                                                                      " FROM b_nutrition_map " +
                                                                                                      // เปลี่ยน b_nutrition_map.f_visit_nutrition_level_new ='8' เป็น 
                                                                                                      // b_nutrition_map.f_visit_nutrition_level_new ='08' sumo 19/10/2549
                                                                                                      " WHERE b_nutrition_map.f_visit_nutrition_level_new ='08')) " + 
                                       " OR t_visit_vital_sign.f_visit_nutrition_level_id = '08') " +
                        " THEN 1 " +
                        " ELSE 0 END AS  more_over " +
                        " ,CASE WHEN ((t_visit_vital_sign.f_visit_nutrition_level_id IN (SELECT b_nutrition_map.f_visit_nutrition_level_old " +
                                                                                                      " FROM b_nutrition_map " +
                                                                                                      // เปลี่ยน b_nutrition_map.f_visit_nutrition_level_new ='9' เป็น 
                                                                                                      // b_nutrition_map.f_visit_nutrition_level_new ='09' sumo 19/10/2549
                                                                                                      " WHERE b_nutrition_map.f_visit_nutrition_level_new ='09')) " + 
                                       " OR t_visit_vital_sign.f_visit_nutrition_level_id = '09') " +
                        " THEN 1 " +
                        " ELSE 0 END AS  more_rather " +
                        " ,CASE WHEN ((t_visit_vital_sign.f_visit_nutrition_level_id IN (SELECT b_nutrition_map.f_visit_nutrition_level_old " + 
                                                                                                      " FROM b_nutrition_map " +
                                                                                                      " WHERE b_nutrition_map.f_visit_nutrition_level_new ='10')) " +
                                         " OR t_visit_vital_sign.f_visit_nutrition_level_id = '10') " +
                        " THEN 1 " +
                        " ELSE 0 END AS  normal " +
                        " ,CASE WHEN ((t_visit_vital_sign.f_visit_nutrition_level_id IN (SELECT b_nutrition_map.f_visit_nutrition_level_old " +
                                                                                                      " FROM b_nutrition_map " +
                                                                                                      " WHERE b_nutrition_map.f_visit_nutrition_level_new ='11')) " +
                                        " OR t_visit_vital_sign.f_visit_nutrition_level_id = '11') " +
                        " THEN 1 " +
                        " ELSE 0 END AS  less_rather " +
                        " ,CASE WHEN ((t_visit_vital_sign.f_visit_nutrition_level_id IN (SELECT b_nutrition_map.f_visit_nutrition_level_old " +
                                                                                                      " FROM b_nutrition_map " +
                                                                                                      " WHERE b_nutrition_map.f_visit_nutrition_level_new ='12')) " +
                                        " OR t_visit_vital_sign.f_visit_nutrition_level_id = '12') " +
                        " THEN 1 " +
                        " ELSE 0 END AS  slighted_less " +
                        " FROM  t_visit INNER JOIN t_visit_vital_sign " + 
                                        " ON (t_visit.t_visit_id = t_visit_vital_sign.t_visit_id " +  
                                                " AND t_visit_vital_sign.visit_vital_sign_weight <> '' " +
                                                " AND t_visit_vital_sign.visit_vital_sign_bmi <> '' " +
                                                " AND t_visit_vital_sign.visit_vital_sign_active = '1') " +
                                   " INNER JOIN (SELECT t_visit_vital_sign.t_patient_id,max(t_visit_vital_sign.visit_vital_sign_check_date || t_visit_vital_sign.visit_vital_sign_check_time) as date_max " +
                                                        " FROM t_visit_vital_sign " +
                                                        " WHERE t_visit_vital_sign.visit_vital_sign_check_date BETWEEN '"+startDate+"' AND '"+endDate+"' " +
                                                        " AND t_visit_vital_sign.visit_vital_sign_active = '1' " +
                                                        " GROUP BY t_visit_vital_sign.t_patient_id " + 
                                                      " ) AS query " +
                                        " ON ( t_visit_vital_sign.t_patient_id = query.t_patient_id " +
                                              " AND (t_visit_vital_sign.visit_vital_sign_check_date || t_visit_vital_sign.visit_vital_sign_check_time) = query.date_max) " +
                                   " INNER JOIN t_patient " + 
                                        " ON t_patient.t_patient_id = t_visit.t_patient_id " + 
                                   " INNER JOIN t_health_family " +
                                        " ON t_patient.t_health_family_id = t_health_family.t_health_family_id " +
                                   " INNER JOIN t_health_home " +
                                        " ON t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                                   " INNER JOIN t_health_village " +
                                        " ON (t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                                                " AND t_health_village.village_moo <> '0') " +
                        " WHERE (substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD'),to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year')) IS NULL " +
                            " OR  to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD'),to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') < 6 " +
                            " OR (to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD'),to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') = 6 " +
                                   " AND to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD'),to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon'),'999') IS NULL ) " +
                           " AND t_visit_vital_sign.visit_vital_sign_check_date BETWEEN '"+startDate+"' AND '"+endDate+"' " +
                        " GROUP BY" + 
                                        " t_health_family.t_health_family_id " +
                                        " ,t_health_village.t_health_village_id " +
                                        " ,t_health_village.village_moo " +
                                        " ,t_visit_vital_sign.f_visit_nutrition_level_id " +
                        " UNION " +
                        " SELECT t_health_family.t_health_family_id " +
                        " ,t_health_village.t_health_village_id " +
                        " ,t_health_village.village_moo " +
                        " ,CASE WHEN ((t_health_nutrition.f_health_nutrition_level_id IN (SELECT b_nutrition_map.f_visit_nutrition_level_old " + 
                                                                                                      " FROM b_nutrition_map " +
                                                                                                      // เปลี่ยน b_nutrition_map.f_visit_nutrition_level_new ='8' เป็น 
                                                                                                      // b_nutrition_map.f_visit_nutrition_level_new ='08' sumo 19/10/2549
                                                                                                      " WHERE b_nutrition_map.f_visit_nutrition_level_new ='08')) " + 
                                       " OR t_health_nutrition.f_health_nutrition_level_id = '08') " +
                        " THEN 1 " +
                        " ELSE 0 END AS  more_over " +
                        " ,CASE WHEN ((t_health_nutrition.f_health_nutrition_level_id IN (SELECT b_nutrition_map.f_visit_nutrition_level_old " + 
                                                                                                      " FROM b_nutrition_map " +
                                                                                                      // เปลี่ยน b_nutrition_map.f_visit_nutrition_level_new ='9' เป็น 
                                                                                                      // b_nutrition_map.f_visit_nutrition_level_new ='09' sumo 19/10/2549
                                                                                                      " WHERE b_nutrition_map.f_visit_nutrition_level_new ='09')) " + 
                                       " OR t_health_nutrition.f_health_nutrition_level_id = '09') " +
                        " THEN 1 " +
                        " ELSE 0 END AS  more_rather " +
                        " ,CASE WHEN ((t_health_nutrition.f_health_nutrition_level_id IN (SELECT b_nutrition_map.f_visit_nutrition_level_old " + 
                                                                                                      " FROM b_nutrition_map " +
                                                                                                      " WHERE b_nutrition_map.f_visit_nutrition_level_new ='10')) " +
                                         " OR t_health_nutrition.f_health_nutrition_level_id = '10') " +
                        " THEN 1 " +
                        " ELSE 0 END AS  normal " +
                        " ,CASE WHEN ((t_health_nutrition.f_health_nutrition_level_id IN (SELECT b_nutrition_map.f_visit_nutrition_level_old " +
                                                                                                      " FROM b_nutrition_map " +
                                                                                                      " WHERE b_nutrition_map.f_visit_nutrition_level_new ='11')) " +
                                        " OR t_health_nutrition.f_health_nutrition_level_id = '11') " +
                        " THEN 1 " +
                        " ELSE 0 END AS  less_rather " +
                        " ,CASE WHEN ((t_health_nutrition.f_health_nutrition_level_id IN (SELECT b_nutrition_map.f_visit_nutrition_level_old " +
                                                                                                      " FROM b_nutrition_map " +
                                                                                                      " WHERE b_nutrition_map.f_visit_nutrition_level_new ='12')) " +
                                        " OR t_health_nutrition.f_health_nutrition_level_id = '12') " +
                        " THEN 1 " +
                        " ELSE 0 END AS  slighted_less " +
                        " FROM  t_health_nutrition " +
                                " INNER JOIN t_health_family " +
                                        " ON (t_health_nutrition.t_health_family_id = t_health_family.t_health_family_id " +
                                                " AND t_health_nutrition.health_nutrition_weight <> '' " +
                                                " AND t_health_nutrition.health_nutrition_bmi <> '' " +
                                                " AND t_health_nutrition.health_nutrition_active = '1') " +
                                " INNER JOIN (SELECT t_health_nutrition.t_health_family_id,max(t_health_nutrition.nutrition_survey_date) AS survey_date " +
                                                        " FROM t_health_nutrition " +
                                                        " WHERE t_health_nutrition.nutrition_survey_date BETWEEN '"+startDate+"' AND '"+endDate+"' " +
                                                        " AND t_health_nutrition.health_nutrition_active = '1' " +
                                                        " GROUP BY t_health_nutrition.t_health_family_id) AS query2 " +
                                        " ON (t_health_nutrition.t_health_family_id = query2.t_health_family_id " +
                                               " AND t_health_nutrition.nutrition_survey_date = survey_date ) " +
                                " INNER JOIN t_health_home " +
                                        " ON t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                                " INNER JOIN t_health_village " +
                                        " ON (t_health_home.t_health_village_id = t_health_village.t_health_village_id " + 
                                                " AND t_health_village.village_moo <> '0') " +
                        " WHERE (substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD'),to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year')) IS NULL " +
                            " OR  to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD'),to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') < 6 " +
                            " OR (to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD'),to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') = 6 " +
                                   " AND to_number(substring(' ' || age(to_date('"+startDate+"','YYYY-MM-DD'),to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon'),'999') IS NULL ) " +
                           " AND t_health_nutrition.nutrition_survey_date BETWEEN '"+startDate+"' AND '"+endDate+"' " +
                        " GROUP BY " + 
                                        " t_health_family.t_health_family_id " +
                                        " ,t_health_village.t_health_village_id " +
                                        " ,t_health_village.village_moo " +
                                        " ,t_health_nutrition.f_health_nutrition_level_id " +
                           " )  AS query1 " +
                           " ON t_health_village.t_health_village_id = query1.t_health_village_id " +
                           " WHERE t_health_village.village_moo <> '0' " +
                        " GROUP BY " +
                                " t_health_village.village_moo " +
                        " ORDER BY " +
                                " to_number(t_health_village.village_moo,'99')	";
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
    
    private Vector nutritionLevelAllByDate(ResultSet resultset) throws Exception
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
            columnname = new String[columnsize];
            vString = new Vector();
            //ทำการให้ค่าชื่อ field ที่ get มา
            for(int i = 0 ; i < columnsize;i++)
            {
                if(i < column)
                {
                    columnname[i] = metadata.getColumnLabel(i+1);
                    columnname[i] = Language.getTextBundle(columnname[i].toUpperCase());
                }
                else
                {
                    columnname[i] = "-";
                }
            }
            //ทำการให้ค่าของ field กับข้อมูล
            while(rs.next())
            {   rowdata = new String[columnsize];
                for(int i = 0 ; i < columnsize;i++)
                {
                    if(i < column)
                    {
                        if(rs.getObject(i+1) != null)
                        {
                            rowdata[i] = rs.getString(i+1);
                        }
                        else
                        {
                            rowdata[i] = "-";
                        }
                    }
                    else
                    {
                        rowdata[i] = "-";
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
