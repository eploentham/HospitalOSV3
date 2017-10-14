/*
 * RPPcuUncontagiousDB.java
 *
 * Created on 23 กุมภาพันธ์ 2549, 15:15 น.
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
public class RPPcuUncontagiousDB
{
    public ConnectionInf theConnectionInf;
    String SQL = "";
    Vector vc;
    ResultSet rs = null;
    ResultSetMetaData metadata;
    Vector vPateintInservicePoint ;
    Vector vString;
    Vector vData;
    /** Creates a new instance of RPPcuUncontagiousDB */
    public RPPcuUncontagiousDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    
    /**
     *จำนวนผู้ป่วยที่เป็นโรคไม่ติดต่อ แยกตามหมู่บ้าน 
     *@param String village_id รหัสหมู่บ้านที่ต้องการค้นหา
     *       String diasease_id รหัสโรคไม่ติดต่อที่ต้องการค้นหา
     *       String startdate วันที่เริ่มต้นในการดึงข้อมูล
     *       String enddate วันที่สิ้นสุดในการดึงข้อมูล
     *@return Vector ของข้อูลจำนวนผู้ป่วยโรคไม่ติดต่อ
     *@Date 23/02/2006
     *@Author pu
     */
    public Vector queryPatientAmountByDate(String village_id,String diasease_id,String startdate,String enddate)
    {
        /*
        SQL = "SELECT uncontagious_person.village_no AS MOO " +
                ",uncontagious_person.village_name AS VILLAGE_NAME " +
                ",b_health_disease.health_disease_description AS DISEASE_NAME " +
                ",  CASE WHEN (SUM(uncontagious_person.male) >0 ) " +
                "	THEN SUM(uncontagious_person.male) " +
                "	ELSE 0 " +
                "   END	AS MALE" +
                ",  CASE WHEN (SUM(uncontagious_person.female) >0 ) " +
                "	THEN SUM(uncontagious_person.female) " +
                "	ELSE 0 " +
                "   END	AS FEMALE " +
                ",  CASE WHEN (SUM(uncontagious_person.nonspecify) >0 ) " +
                "	THEN SUM(uncontagious_person.nonspecify) " +
                "	ELSE 0 " +
                "   END	AS NONSPEC_SEX " +
                ", SUM(1) AS TOTAL " +
              "FROM " +
                "b_health_disease " +
                "INNER JOIN (   SELECT t_health_village.village_moo AS village_no " +
                "                       , t_health_village.village_name AS village_name " +
                "               	,t_health_uncontagious.t_health_family_id AS family_id " +
                "			,t_health_uncontagious.b_health_disease_id AS disease_id " +
                "			,(CASE  WHEN (t_health_family.f_sex_id = '1') " +
                "				THEN  1 " +
                "				ELSE 0 " +
                "       		END)  AS male " +
                "			,(CASE  WHEN (t_health_family.f_sex_id = '2') " +
                "				THEN  1 " +
                "				ELSE 0  " +
                "			END)  AS female " +
                "			,(CASE  WHEN ((t_health_family.f_sex_id <> '2' ) AND (t_health_family.f_sex_id <> '1' ))  " +
                "				THEN  1 " +
                "				ELSE 0  " +
                "			END)  AS  nonspecify  " +
                "		FROM " +
                "			t_health_village INNER JOIN t_health_home " +
                "			ON t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                "				INNER JOIN   t_health_family " +
                "			ON t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                "				INNER JOIN t_health_uncontagious " +
                "               	ON t_health_uncontagious.t_health_family_id = t_health_family.t_health_family_id " +
                "		WHERE " +
                "			t_health_uncontagious.health_uncontagious_get_well =  '0' " +
                "			AND t_health_uncontagious.health_uncontagious_survey_date <> '' " +
                "			AND t_health_uncontagious.health_uncontagious_active = '1' " ;
               if(!village_id.equals("0"))
                {
                    SQL = SQL + "       AND t_health_village.t_health_village_id = '" + village_id + "' ";
                }
               if(!diasease_id.equals("0"))
                {
                    SQL = SQL + "       AND t_health_uncontagious.b_health_disease_id = '"+ diasease_id +"' ";
                }              
                SQL = SQL + "   	AND t_health_uncontagious.health_uncontagious_survey_date BETWEEN '"+ startdate +"' AND '"+ enddate +"' " +
                "			) AS uncontagious_person " +
                "ON (b_health_disease.b_health_disease_id = uncontagious_person.disease_id	) " +
                "GROUP BY " +
                "	uncontagious_person.village_no " +
                "       ,uncontagious_person.village_name " +
                "       ,b_health_disease.health_disease_description " +
                "ORDER BY " +
                "	uncontagious_person.village_no" ;
         *
         */
        
        SQL = "SELECT  " +
        "uncontagious_person.village_no AS MOO " + 
        ",uncontagious_person.village_name AS VILLAGE_NAME " + 
        ",b_health_disease.health_disease_description AS DISEASE_NAME " + 
        ",  CASE WHEN (SUM(uncontagious_person.male) >0 ) " + 	
                "THEN SUM(uncontagious_person.male) " + 	
                "ELSE 0 " +    
        "END	AS MALE " +
        ",  CASE WHEN (SUM(uncontagious_person.female) >0 ) " + 	
                "THEN SUM(uncontagious_person.female) " + 	
                "ELSE 0 " +    
        "END	AS FEMALE " + 
        ",  CASE WHEN (SUM(uncontagious_person.nonspecify) >0 ) " + 	
                "THEN SUM(uncontagious_person.nonspecify) " + 	
                "ELSE 0 " +    
        "END	AS NONSPEC_SEX " + 
        ", SUM(1) AS TOTAL " + 
        "FROM  " +
                "b_health_disease INNER JOIN  " +
                "(SELECT " + 
                "t_health_village.village_moo AS village_no " +                        
                ", t_health_village.village_name AS village_name " +                	
                ",t_health_uncontagious.t_health_family_id AS family_id  " +			
                ",t_health_uncontagious.b_health_disease_id AS disease_id  " +				
                ",(CASE  WHEN (t_health_family.f_sex_id = '1') " + 				
                        "THEN  1 " + 				
                        "ELSE 0 " +        		
                "END)  AS male  " +			
                ",(CASE  WHEN (t_health_family.f_sex_id = '2') " + 				
                        "THEN  1 " + 				
                        "ELSE 0  " + 			
                "END)  AS female " + 			
                ",(CASE  WHEN ((t_health_family.f_sex_id <> '2' ) AND (t_health_family.f_sex_id <> '1' )) " +  				
                        "THEN  1  " +				
                        "ELSE 0  " + 			
                "END)  AS  nonspecify  " + 		
                "FROM " +			
                "t_health_village INNER JOIN t_health_home " + 			
                "ON t_health_home.t_health_village_id = t_health_village.t_health_village_id  " +				
                "INNER JOIN   t_health_family " + 			
                "ON t_health_family.t_health_home_id = t_health_home.t_health_home_id  " +				
                "INNER JOIN t_health_uncontagious " +                	
                "ON t_health_uncontagious.t_health_family_id = t_health_family.t_health_family_id  " +		
                "WHERE  " +			
                "t_health_uncontagious.health_uncontagious_get_well =  '0'  " +			
                "AND t_health_uncontagious.health_uncontagious_survey_date <> ''  " +			
                "AND t_health_uncontagious.health_uncontagious_active = '1'   ";
        
                if(!village_id.equals("0"))
                {
                    SQL = SQL + "       AND t_health_village.t_health_village_id = '" + village_id + "' ";
                }
               if(!diasease_id.equals("0"))
                {
                    SQL = SQL + "       AND t_health_uncontagious.b_health_disease_id = '"+ diasease_id +"' ";
                }              
        
                SQL = SQL + "   	AND t_health_uncontagious.health_uncontagious_survey_date BETWEEN '"+ startdate +"' AND '"+ enddate +"' " +

                "UNION " +

                "SELECT  " +
                "t_health_village.village_moo AS village_no  " +                       
                ", t_health_village.village_name AS village_name    " +             	
                ",t_health_uncontagious.t_health_family_id AS family_id  " +			
                ",t_health_uncontagious.b_health_disease_id AS disease_id  " +				
                ",(CASE  WHEN (t_health_family.f_sex_id = '1') 	 " +			
                        "THEN  1 " + 				
                        "ELSE 0   " +      		
                "END)  AS male  " +			
                ",(CASE  WHEN (t_health_family.f_sex_id = '2') " + 				
                        "THEN  1 " + 				
                        "ELSE 0   " +			
                "END)  AS female  " +			
                ",(CASE  WHEN ((t_health_family.f_sex_id <> '2' ) AND (t_health_family.f_sex_id <> '1' ))  " + 				
                        "THEN  1 " + 				
                        "ELSE 0 " +  			
                "END)  AS  nonspecify  " + 		
                "FROM " + 			
                "t_health_village INNER JOIN t_health_home " + 			
                "ON t_health_home.t_health_village_id = t_health_village.t_health_village_id " + 				
                "INNER JOIN   t_health_family  " +			
                "ON t_health_family.t_health_home_id = t_health_home.t_health_home_id 	 " +
                "INNER JOIN t_patient " +
                "ON t_health_family.t_health_family_id = t_patient.t_health_family_id " +
                "INNER JOIN t_visit  " +
                "ON t_patient.t_patient_id = t_visit.t_patient_id and t_visit.f_visit_status_id <> '4' " +
                "INNER JOIN t_health_uncontagious  " +               	
                "ON t_health_uncontagious.t_visit_id = t_visit.t_visit_id  " +		
                "WHERE " + 			
                "t_health_uncontagious.health_uncontagious_get_well =  '0' " + 			
                "AND t_health_uncontagious.health_uncontagious_active = '1'  ";
                        
                if(!village_id.equals("0"))
                {
                    SQL = SQL + "  AND t_health_village.t_health_village_id = '" + village_id + "' ";
                }
               if(!diasease_id.equals("0"))
                {
                    SQL = SQL + "  AND t_health_uncontagious.b_health_disease_id = '"+ diasease_id +"' ";
                }              
        
                SQL = SQL + "   AND t_health_uncontagious.health_uncontagious_record_date_time BETWEEN '"+ startdate +"' AND '"+ enddate +"' " +
                        
                ") AS uncontagious_person " + 

                "ON (b_health_disease.b_health_disease_id = uncontagious_person.disease_id)  " +
        "GROUP BY " + 	
                "uncontagious_person.village_no " +        
                ",uncontagious_person.village_name     " +    
                ",b_health_disease.health_disease_description  " +
        "ORDER BY 	uncontagious_person.village_no ";
                
        System.out.println("PatientAmountByDate  : " + SQL);
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
     *รายชื่อผู้ป่วยที่เป็นโรคไม่ติดต่อแยกตามหมู่บ้าน
     *@param String village_id รหัสหมู่บ้านที่ต้องการค้นหา
     *       String diasease_id รหัสโรคไม่ติดต่อที่ต้องการค้นหา
     *       String startdate วันที่เริ่มต้นในการดึงข้อมูล
     *       String enddate วันที่สิ้นสุดในการดึงข้อมูล
     *@return Vector ที่เก็บรายชื่อผู้ป่วยที่เป็นโรคไม่ติดต่อ
     *@Date 23/02/2006
     *@Author pu
     */
    public Vector queryPatienNametByDate(String village_id,String diasease_id,String startdate,String enddate)
    {
        /*SQL = "SELECT t_health_village.village_moo AS MOO " +
                ",t_health_village.village_name AS village_name " +
                ", f_patient_prefix.patient_prefix_description AS PREFIX " +
                ", t_health_family.patient_name AS FIRSTNAME " +
                ", t_health_family.patient_last_name AS LASTNAME " +
                ", CASE WHEN (to_number(substring(' ' || age(to_date(t_health_uncontagious.health_uncontagious_survey_date,'YYYY-MM-DD') " +
                "             ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year'),'999') IS NOT NULL) " +
                "  THEN trim(substring(' ' || age(to_date(t_health_uncontagious.health_uncontagious_survey_date,'YYYY-MM-DD') " +
                "             ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year')) " +
                "  ELSE '0' END AS YEAR " +
                ", CASE WHEN (to_number(substring(' ' || age(to_date(t_health_uncontagious.health_uncontagious_survey_date,'YYYY-MM-DD') " +
                "            ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)mon'),'999') IS NOT NULL) " +
                "  THEN trim(substring(' ' || age(to_date(t_health_uncontagious.health_uncontagious_survey_date,'YYYY-MM-DD') " +
                "            ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)mon')) " +
                "  ELSE '0' END AS MONTH " +
                ", CASE WHEN (to_number(substring(' ' || age(to_date(t_health_uncontagious.health_uncontagious_survey_date,'YYYY-MM-DD') " +
                "            ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)day'),'999') IS NOT NULL) " +
                "  THEN trim(substring(' ' || age(to_date(t_health_uncontagious.health_uncontagious_survey_date,'YYYY-MM-DD') " +
                "            ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)day')) " +
                "  ELSE '0' END AS DAY " +
                ",t_health_home.health_home_house AS HOUSE " +
                ",b_health_disease.health_disease_description AS DISEASE_NAME " +
              "FROM " +
                "t_health_village INNER JOIN t_health_home " +
                "	ON t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                "		INNER JOIN   t_health_family " +
                "	ON t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                "		INNER JOIN t_health_uncontagious " +
                "	ON t_health_uncontagious.t_health_family_id = t_health_family.t_health_family_id " +
                "		INNER JOIN f_patient_prefix " +
                "	ON f_patient_prefix.f_patient_prefix_id = t_health_family.f_prefix_id " +
                "		INNER JOIN b_health_disease " +
                "       ON (b_health_disease.b_health_disease_id = t_health_uncontagious.b_health_disease_id " +
                "       AND t_health_uncontagious.health_uncontagious_survey_date <> '' " +
                "       AND t_health_uncontagious.health_uncontagious_active = '1') " +
              "WHERE " +
                "t_health_uncontagious.health_uncontagious_get_well =  '0' " ;
                if(!village_id.equals("0"))
                {
                    SQL = SQL + "       AND t_health_village.t_health_village_id = '" + village_id + "' ";
                }
                if(!diasease_id.equals("0"))
                {
                    SQL = SQL + "       AND t_health_uncontagious.b_health_disease_id = '"+ diasease_id +"' ";
                }              
                SQL = SQL + "   	AND t_health_uncontagious.health_uncontagious_survey_date BETWEEN '"+ startdate +"' AND '"+ enddate +"' " +
              " GROUP BY " +
                "t_health_village.village_moo " +
                ",t_health_village.village_name " +
                ",t_health_family.patient_birthday " +
                ",t_health_uncontagious.health_uncontagious_survey_date	" +
                ", f_patient_prefix.patient_prefix_description " +
                ", t_health_family.patient_name " +
                ", t_health_family.patient_last_name " +
                ",t_health_home.health_home_house " +
                ",b_health_disease.health_disease_description " +
             "ORDER BY " +
                "t_health_village.village_moo";
         */
        SQL = "select * " +
                "from " +
                "(SELECT  " +
                "t_health_village.village_moo AS MOO  " +
                ",t_health_village.village_name AS village_name  " +
                ", f_patient_prefix.patient_prefix_description AS PREFIX  " +
                ", t_health_family.patient_name AS FIRSTNAME  " +
                ", t_health_family.patient_last_name AS LASTNAME  " +
                ", CASE WHEN (to_number(substring(' ' || age(to_date(t_health_uncontagious.health_uncontagious_survey_date,'YYYY-MM-DD') " +             
                                ",to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year'),'999') IS NOT NULL) " +   
                        "THEN trim(substring(' ' || age(to_date(t_health_uncontagious.health_uncontagious_survey_date,'YYYY-MM-DD') " +              
                                ",to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year')) " +   
                        "ELSE '0' " + 
                "END AS YEAR  " +
                ", CASE WHEN (to_number(substring(' ' || age(to_date(t_health_uncontagious.health_uncontagious_survey_date,'YYYY-MM-DD')  " +          
                                ",to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)mon'),'999') IS NOT NULL)  " +  
                        "THEN trim(substring(' ' || age(to_date(t_health_uncontagious.health_uncontagious_survey_date,'YYYY-MM-DD')  " +     
                                ",to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)mon')) " +   
                        "ELSE '0'  " +
                "END AS MONTH  " +
                ", CASE WHEN (to_number(substring(' ' || age(to_date(t_health_uncontagious.health_uncontagious_survey_date,'YYYY-MM-DD')  " +     
                                ",to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)day'),'999') IS NOT NULL)  " +  
                        "THEN trim(substring(' ' || age(to_date(t_health_uncontagious.health_uncontagious_survey_date,'YYYY-MM-DD')  " +  
                                ",to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)day'))  " + 
                        "ELSE '0'  " +
                "END AS DAY " +
                ",t_health_home.health_home_house AS HOUSE  " +
                ",b_health_disease.health_disease_description AS DISEASE_NAME  " +
                "FROM  " +
                "t_health_village INNER JOIN t_health_home  " +	
                "ON t_health_home.t_health_village_id = t_health_village.t_health_village_id  " +
                "INNER JOIN   t_health_family  " +
                "ON t_health_family.t_health_home_id = t_health_home.t_health_home_id  " +
                "INNER JOIN t_health_uncontagious  " +
                "ON t_health_uncontagious.t_health_family_id = t_health_family.t_health_family_id  " +	
                "INNER JOIN f_patient_prefix  " +	
                "ON f_patient_prefix.f_patient_prefix_id = t_health_family.f_prefix_id  " +	
                "INNER JOIN b_health_disease  " +    
                "ON (b_health_disease.b_health_disease_id = t_health_uncontagious.b_health_disease_id) " + 
                "WHERE  " +
                "t_health_uncontagious.health_uncontagious_get_well =  '0'  " +  
                "AND t_health_uncontagious.health_uncontagious_survey_date <> ''  " +  
                "AND t_health_uncontagious.health_uncontagious_active = '1' ";
                
                if(!village_id.equals("0"))
                {
                    SQL = SQL + "       AND t_health_village.t_health_village_id = '" + village_id + "' ";
                }
                if(!diasease_id.equals("0"))
                {
                    SQL = SQL + "       AND t_health_uncontagious.b_health_disease_id = '"+ diasease_id +"' ";
                }     
        
                SQL = SQL + "   	AND t_health_uncontagious.health_uncontagious_survey_date BETWEEN '"+ startdate +"' AND '"+ enddate +"' " +
                        
                "GROUP BY  " +
                "t_health_village.village_moo  " +
                ",t_health_village.village_name " +
                ",t_health_family.patient_birthday " +
                ",t_health_uncontagious.health_uncontagious_survey_date	 " +
                ", f_patient_prefix.patient_prefix_description " + 
                ", t_health_family.patient_name " + 
                ", t_health_family.patient_last_name " +
                ",t_health_home.health_home_house " +
                ",b_health_disease.health_disease_description  " + 

                "UNION " +

                "SELECT  " +
                "t_health_village.village_moo AS MOO  " +
                ",t_health_village.village_name AS village_name  " +
                ", f_patient_prefix.patient_prefix_description AS PREFIX  " +
                ", t_health_family.patient_name AS FIRSTNAME  " +
                ", t_health_family.patient_last_name AS LASTNAME  " +
                ", CASE WHEN (to_number(substring(' ' || age(to_date(t_health_uncontagious.health_uncontagious_record_date_time,'YYYY-MM-DD')   " +           
                                ",to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year'),'999') IS NOT NULL)    " +
                        "THEN trim(substring(' ' || age(to_date(t_health_uncontagious.health_uncontagious_record_date_time,'YYYY-MM-DD')     " +          
                                ",to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year'))  " +  
                        "ELSE '0'  " +
                "END AS YEAR  " +
                ", CASE WHEN (to_number(substring(' ' || age(to_date(t_health_uncontagious.health_uncontagious_record_date_time,'YYYY-MM-DD')  " +          
                                ",to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)mon'),'999') IS NOT NULL)  " +  
                        "THEN trim(substring(' ' || age(to_date(t_health_uncontagious.health_uncontagious_record_date_time,'YYYY-MM-DD') " +      
                                ",to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)mon')) " +   
                        "ELSE '0'  " +
                "END AS MONTH  " +
                ", CASE WHEN (to_number(substring(' ' || age(to_date(t_health_uncontagious.health_uncontagious_record_date_time,'YYYY-MM-DD')  " +     
                                ",to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)day'),'999') IS NOT NULL)   " + 
                        "THEN trim(substring(' ' || age(to_date(t_health_uncontagious.health_uncontagious_record_date_time,'YYYY-MM-DD')    " +
                                ",to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)day'))  " + 
                        "ELSE '0'  " +
                "END AS DAY " +
                ",t_health_home.health_home_house AS HOUSE  " +
                ",b_health_disease.health_disease_description AS DISEASE_NAME  " +
                "FROM  " +
                "t_health_village INNER JOIN t_health_home  " +	
                "ON t_health_home.t_health_village_id = t_health_village.t_health_village_id  " +
                "INNER JOIN   t_health_family  " +
                "ON t_health_family.t_health_home_id = t_health_home.t_health_home_id  " +
                "INNER JOIN t_patient " +
                "ON t_patient.t_health_family_id = t_health_family.t_health_family_id " +
                "INNER JOIN t_visit " +
                "ON t_visit.t_patient_id = t_patient.t_patient_id " +
                "INNER JOIN t_health_uncontagious  " +
                "ON t_health_uncontagious.t_visit_id = t_visit.t_visit_id  " +	
                "INNER JOIN f_patient_prefix  " +	
                "ON f_patient_prefix.f_patient_prefix_id = t_health_family.f_prefix_id 	 " +
                "INNER JOIN b_health_disease  " +    
                "ON (b_health_disease.b_health_disease_id = t_health_uncontagious.b_health_disease_id) " + 
                "WHERE  " +
                "t_health_uncontagious.health_uncontagious_get_well =  '0'   " + 
                "AND t_health_uncontagious.health_uncontagious_active = '1' ";
                
                if(!village_id.equals("0"))
                {
                    SQL = SQL + "       AND t_health_village.t_health_village_id = '" + village_id + "' ";
                }
                if(!diasease_id.equals("0"))
                {
                    SQL = SQL + "       AND t_health_uncontagious.b_health_disease_id = '"+ diasease_id +"' ";
                }     
        
                SQL = SQL + "   	AND t_health_uncontagious.health_uncontagious_record_date_time BETWEEN '"+ startdate +"' AND '"+ enddate +"' " +
                        
                "GROUP BY  " +
                "t_health_village.village_moo  " +
                ",t_health_village.village_name " +
                ",t_health_family.patient_birthday " +
                ",t_health_uncontagious.health_uncontagious_record_date_time " +	
                ", f_patient_prefix.patient_prefix_description  " +
                ", t_health_family.patient_name  " +
                ", t_health_family.patient_last_name " +
                ",t_health_home.health_home_house " +
                ",b_health_disease.health_disease_description  " +
                ") AS query1 " +

                "ORDER BY query1.MOO";
        System.out.println("PatienNametByDate  : " + SQL);
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
