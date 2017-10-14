/*
 * RPEmergencyPatientDB.java
 *
 * Created on 5 มิถุนายน " + year1 + ", 9:56 น.
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
public class RPEmergencyPatientDB
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
    StringBuffer strbuff;
    /** Creates a new instance of RPEmergencyPatientDB */
    public RPEmergencyPatientDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    
     /**
     *รายงานผู้ป่วยฉุกเฉิน-ไม่ฉุกเฉิน
     *@param year1 เป็น String ที่เก็บปีงบประมาณที่ต้องการดึงข้อมูล
     *@param year2 เป็น String ที่เก็บปีก่อนหน้าของปีงบประมาณที่ต้องการดึงข้อมูล
     *@param morning_time_start เก็บ String เวลาเริ่มต้นของเวรเช้า
     *@param morning_time_end เก็บ String เวลาสิ้นสุดของเวรเช้า
     *@param afternoon_time_start เก็บ String เวลาเริ่มต้นของเวรบ่าย
     *@param afternoon_time_end เก็บ String เวลาสิ้นสุดของเวรบ่าย
     *@param night_time_start เก็บ String เวลาเริ่มต้นของเวรดึก
     *@param night_time_end เก็บ String เวลาสิ้นสุดของเวรดึก
     *@param morning_sat_start เก็บ String เวลาเริ่มต้นของเวรเช้า วันเสาร์
     *@param morning_sat_end เก็บ String เวลาสิ้นสุดของเวรเวรเช้า วันเสาร์
     *@param morning_sun_start เก็บ String เวลาเริ่มต้นของเวรเช้า วันอาทิตย์
     *@param morning_sun_end เก็บ String เวลาสิ้นสุดของเวรเช้า วันอาทิตย์     
     *@return Vector ของผู้ป่วยฉุกเฉิน-ไม่ฉุกเฉิน
     *@Author pu
     *@Date 12/06/2006
     */
    public Vector queryEmergencyPatientByDateTime(String year1,String year2,String morning_time_start,String morning_time_end
            ,String afternoon_time_start,String afternoon_time_end, String night_time_start, String night_time_end
            ,String morning_sat_start,String morning_sat_end,String morning_sun_start,String morning_sun_end)
    {
        strbuff = new StringBuffer();
        try
        {    
            SQL = "SELECT " +
                    "'1' AS order " +
                    ",'ER(morning)'  AS TURN_OF_THE_TIME " +
                    ",f_emergency_status.emergency_status_description AS status  " +
                    ",(CASE WHEN (october_"+ year2.substring(2)+" IS NOT NULL) " +
                     "THEN october_" +year2.substring(2) +" "+
                     "ELSE 0 END) AS october " +

                    ",(CASE WHEN (november_"+ year2.substring(2)+" IS NOT NULL) " +
                     "THEN november_" +year2.substring(2) +" "+
                     "ELSE 0 END) AS november " +

                    ",(CASE WHEN (december_"+ year2.substring(2)+" IS NOT NULL) " +
                     "THEN december_" +year2.substring(2) +" "+
                     "ELSE 0 END) AS december " +

                    ",(CASE WHEN (january_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN january_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS january " +

                    ",(CASE WHEN (february_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN february_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS february " +

                    ",(CASE WHEN (march_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN march_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS march " +

                    ",(CASE WHEN (april_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN april_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS april " +

                    ",(CASE WHEN (may_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN may_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS may " +

                    ",(CASE WHEN (june_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN june_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS june " +

                    ",(CASE WHEN (july_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN july_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS july " +

                    ",(CASE WHEN (august_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN august_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS august " +

                    ",(CASE WHEN (september_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN september_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS september " +

                   ",(CASE WHEN (summary IS NOT NULL) " +
                     "THEN summary " +
                     "ELSE 0 END) AS total " +
            "FROM  f_emergency_status LEFT JOIN  " +
                   "(SELECT t_visit.f_emergency_status_id AS mergency_status_id " +
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year2 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '10') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS october_" +year2.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year2 + "'  " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '11') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS november_" +year2.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year2 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '12') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS december_" +year2.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " + 
                                "AND substring(t_visit_service.assign_date_time,6,2) = '01') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS january_" +year1.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " + 
                                "AND substring(t_visit_service.assign_date_time,6,2) = '02') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS february_" +year1.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '03') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS march_" +year1.substring(2) +" "+
                  ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '04') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS april_" +year1.substring(2) +" "+
                  ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '05') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS may_" +year1.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '06') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS june_" +year1.substring(2) +" "+
                  ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '07') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS july_" +year1.substring(2) +" "+
                  ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '08') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS august_" +year1.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '09') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS september_" +year1.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,10) BETWEEN '" + year2 + "-10-01' AND '" + year1 + "-09-30') " +
                            "THEN 1 " +
                            "ELSE 0 END) AS summary " +
                   "FROM t_visit INNER JOIN t_visit_service " +
                        "ON t_visit.t_visit_id = t_visit_service.t_visit_id " +
                   "WHERE f_visit_status_id <> '4' " +
                   "AND t_visit_service.b_service_point_id IN (SELECT b_service_point_id  " +
                                                            "FROM r_service_point_type INNER JOIN r_service_point_type_map " +
                                                            "ON r_service_point_type.r_service_point_type_id = r_service_point_type_map.r_service_point_type_id " +
                                                            "WHERE service_point_type_number ='1') " +
                   "AND (date_part('dow',cast(to_number(substring(t_visit_service.assign_date_time,1,4),'9999') - 543 || substring(t_visit_service.assign_date_time,5,6) as DATE)) <> 0 " +
                   "AND date_part('dow',cast(to_number(substring(t_visit_service.assign_date_time,1,4),'9999') - 543 || substring(t_visit_service.assign_date_time,5,6) as DATE)) <> 6) " +       
                   "AND substring(t_visit_service.assign_date_time,12,5) BETWEEN '" + morning_time_start + "' AND '" + morning_time_end + "' " +
                   "GROUP BY t_visit.f_emergency_status_id) AS query1 " +
                    "ON (f_emergency_status.f_emergency_status_id = query1.mergency_status_id) " +

                    "UNION " +

                    "SELECT  " +
                    "'2' AS order " +
                    ",'ER(afternoon)' AS TURN_OF_THE_TIME " +
                    ",f_emergency_status.emergency_status_description AS status " +
                    ",(CASE WHEN (october_"+ year2.substring(2)+" IS NOT NULL) " +
                     "THEN october_" +year2.substring(2) +" "+
                     "ELSE 0 END) AS october " +

                    ",(CASE WHEN (november_"+ year2.substring(2)+" IS NOT NULL) " +
                     "THEN november_" +year2.substring(2) +" "+
                     "ELSE 0 END) AS november " +

                    ",(CASE WHEN (december_"+ year2.substring(2)+" IS NOT NULL) " +
                     "THEN december_" +year2.substring(2) +" "+
                     "ELSE 0 END) AS december " +

                    ",(CASE WHEN (january_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN january_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS january " +

                    ",(CASE WHEN (february_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN february_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS february " +

                    ",(CASE WHEN (march_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN march_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS march " +

                    ",(CASE WHEN (april_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN april_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS april " +

                    ",(CASE WHEN (may_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN may_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS may " +

                    ",(CASE WHEN (june_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN june_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS june " +

                    ",(CASE WHEN (july_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN july_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS july " +

                    ",(CASE WHEN (august_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN august_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS august " +

                    ",(CASE WHEN (september_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN september_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS september " +

                   ",(CASE WHEN (summary IS NOT NULL) " +
                     "THEN summary " +
                     "ELSE 0 END) AS total " +
            "FROM  f_emergency_status LEFT JOIN  " +
                   "(SELECT t_visit.f_emergency_status_id AS mergency_status_id " +
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year2 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '10') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS october_" +year2.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year2 + "'  " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '11') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS november_" +year2.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year2 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '12') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS december_" +year2.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "'  " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '01') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS january_" +year1.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "'  " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '02') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS february_" +year1.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '03') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS march_" +year1.substring(2) +" "+
                  ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '04') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS april_" +year1.substring(2) +" "+
                  ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '05') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS may_" +year1.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '06') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS june_" +year1.substring(2) +" "+
                  ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '07') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS july_" +year1.substring(2) +" "+
                  ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '08') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS august_" +year1.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '09') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS september_" +year1.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,10) BETWEEN '" + year2 + "-10-01' AND '" + year1 + "-09-30') " +
                            "THEN 1 " +
                            "ELSE 0 END) AS summary " +
                   "FROM t_visit INNER JOIN t_visit_service " +
                        "ON t_visit.t_visit_id = t_visit_service.t_visit_id " +
                   "WHERE f_visit_status_id <> '4' " +
                   "AND t_visit_service.b_service_point_id IN (SELECT b_service_point_id  " +
                                                            "FROM r_service_point_type INNER JOIN r_service_point_type_map " +
                                                            "ON r_service_point_type.r_service_point_type_id = r_service_point_type_map.r_service_point_type_id " +
                                                            "WHERE service_point_type_number ='1') " +
                   "AND (date_part('dow',cast(to_number(substring(t_visit_service.assign_date_time,1,4),'9999') - 543 || substring(t_visit_service.assign_date_time,5,6) as DATE)) <> 0 " +
                    "AND date_part('dow',cast(to_number(substring(t_visit_service.assign_date_time,1,4),'9999') - 543 || substring(t_visit_service.assign_date_time,5,6) as DATE)) <> 6) " +       
                   "AND substring(t_visit_service.assign_date_time,12,5) BETWEEN '" + afternoon_time_start + "' AND '" + afternoon_time_end + "' " +
                   "GROUP BY t_visit.f_emergency_status_id) AS query1 " +
                   "ON (f_emergency_status.f_emergency_status_id = query1.mergency_status_id)  " +

                    "UNION " +

                    "SELECT " + 
                    "'3' AS order " +
                    ",'ER(night)' AS TURN_OF_THE_TIME " +
                    ",f_emergency_status.emergency_status_description AS status " +
                    ",(CASE WHEN (october_"+ year2.substring(2)+" IS NOT NULL) " +
                    "THEN october_" +year2.substring(2) +" "+
                     "ELSE 0 END) AS october " +

                    ",(CASE WHEN (november_"+ year2.substring(2)+" IS NOT NULL) " +
                     "THEN november_" +year2.substring(2) +" "+
                     "ELSE 0 END) AS november " +

                    ",(CASE WHEN (december_"+ year2.substring(2)+" IS NOT NULL) " +
                     "THEN december_" +year2.substring(2) +" "+
                     "ELSE 0 END) AS december " +

                    ",(CASE WHEN (january_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN january_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS january " +

                    ",(CASE WHEN (february_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN february_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS february " +

                    ",(CASE WHEN (march_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN march_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS march " +

                    ",(CASE WHEN (april_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN april_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS april " +

                    ",(CASE WHEN (may_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN may_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS may " +

                    ",(CASE WHEN (june_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN june_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS june " +

                    ",(CASE WHEN (july_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN july_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS july " +

                    ",(CASE WHEN (august_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN august_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS august " +

                    ",(CASE WHEN (september_"+ year1.substring(2)+" IS NOT NULL) " +
                     "THEN september_" +year1.substring(2) +" "+
                     "ELSE 0 END) AS september " +

                   ",(CASE WHEN (summary IS NOT NULL) " +
                     "THEN summary " +
                     "ELSE 0 END) AS total " +
            "FROM  f_emergency_status LEFT JOIN " + 
                   "(SELECT t_visit.f_emergency_status_id AS mergency_status_id " +
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year2 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '10') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS october_" +year2.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year2 + "' " + 
                                "AND substring(t_visit_service.assign_date_time,6,2) = '11') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS november_" +year2.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year2 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '12') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS december_" +year2.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " + 
                                "AND substring(t_visit_service.assign_date_time,6,2) = '01') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS january_" +year1.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " + 
                                "AND substring(t_visit_service.assign_date_time,6,2) = '02') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS february_" +year1.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '03') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS march_" +year1.substring(2) +" "+
                  ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '04') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS april_" +year1.substring(2) +" "+
                  ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '05') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS may_" +year1.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '06') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS june_" +year1.substring(2) +" "+
                  ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '07') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS july_" +year1.substring(2) +" "+
                  ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '08') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS august_" +year1.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                                "AND substring(t_visit_service.assign_date_time,6,2) = '09') " +
                   "THEN 1 " +
                   "ELSE 0 END) AS september_" +year1.substring(2) +" "+
                   ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,10) BETWEEN '" + year2 + "-10-01' AND '" + year1 + "-09-30') " +
                            "THEN 1 " +
                            "ELSE 0 END) AS summary " +
                   "FROM t_visit INNER JOIN t_visit_service " +
                        "ON t_visit.t_visit_id = t_visit_service.t_visit_id " +
                   "WHERE f_visit_status_id <> '4' " +
                   "AND t_visit_service.b_service_point_id IN (SELECT b_service_point_id " + 
                                                            "FROM r_service_point_type INNER JOIN r_service_point_type_map " +
                                                            "ON r_service_point_type.r_service_point_type_id = r_service_point_type_map.r_service_point_type_id " +
                                                            "WHERE service_point_type_number ='1') " +
                   "AND (date_part('dow',cast(to_number(substring(t_visit_service.assign_date_time,1,4),'9999') - 543 || substring(t_visit_service.assign_date_time,5,6) as DATE)) <> 0 " +
                    "AND date_part('dow',cast(to_number(substring(t_visit_service.assign_date_time,1,4),'9999') - 543 || substring(t_visit_service.assign_date_time,5,6) as DATE)) <> 6) " +       
                   "AND substring(t_visit_service.assign_date_time,12,5) BETWEEN '"+ night_time_start +"' AND '" + night_time_end + "' " +
                   "GROUP BY t_visit.f_emergency_status_id) AS query1 " +
                    "ON (f_emergency_status.f_emergency_status_id = query1.mergency_status_id) " +
                  "UNION " +

	"SELECT " + 
	"'4' AS order " +
	",'ER(sat_morning)' AS TURN_OF_THE_TIME " +
	",f_emergency_status.emergency_status_description AS status " +
        ",(CASE WHEN (october_"+ year2.substring(2)+" IS NOT NULL) " +
         "THEN october_" +year2.substring(2) +" "+
         "ELSE 0 END) AS october " +

        ",(CASE WHEN (november_"+ year2.substring(2)+" IS NOT NULL) " +
         "THEN november_" +year2.substring(2) +" "+
         "ELSE 0 END) AS november " +

        ",(CASE WHEN (december_"+ year2.substring(2)+" IS NOT NULL) " +
         "THEN december_" +year2.substring(2) +" "+
         "ELSE 0 END) AS december " +

        ",(CASE WHEN (january_"+ year1.substring(2)+" IS NOT NULL) " +
         "THEN january_" +year1.substring(2) +" "+
         "ELSE 0 END) AS january " +

        ",(CASE WHEN (february_"+ year1.substring(2)+" IS NOT NULL) " +
         "THEN february_" +year1.substring(2) +" "+
         "ELSE 0 END) AS february " +

        ",(CASE WHEN (march_"+ year1.substring(2)+" IS NOT NULL) " +
         "THEN march_" +year1.substring(2) +" "+
         "ELSE 0 END) AS march " +
        
        ",(CASE WHEN (april_"+ year1.substring(2)+" IS NOT NULL) " +
         "THEN april_" +year1.substring(2) +" "+
         "ELSE 0 END) AS april " +
        
        ",(CASE WHEN (may_"+ year1.substring(2)+" IS NOT NULL) " +
         "THEN may_" +year1.substring(2) +" "+
         "ELSE 0 END) AS may " +

        ",(CASE WHEN (june_"+ year1.substring(2)+" IS NOT NULL) " +
         "THEN june_" +year1.substring(2) +" "+
         "ELSE 0 END) AS june " +

        ",(CASE WHEN (july_"+ year1.substring(2)+" IS NOT NULL) " +
         "THEN july_" +year1.substring(2) +" "+
         "ELSE 0 END) AS july " +

        ",(CASE WHEN (august_"+ year1.substring(2)+" IS NOT NULL) " +
         "THEN august_" +year1.substring(2) +" "+
         "ELSE 0 END) AS august " +
       
        ",(CASE WHEN (september_"+ year1.substring(2)+" IS NOT NULL) " +
         "THEN september_" +year1.substring(2) +" "+
         "ELSE 0 END) AS september " +
         
       ",(CASE WHEN (summary IS NOT NULL) " +
         "THEN summary " +
         "ELSE 0 END) AS total " +
"FROM  f_emergency_status LEFT JOIN  " +
       "(SELECT t_visit.f_emergency_status_id AS mergency_status_id " +
       ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year2 + "' " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '10') " +
       "THEN 1 " +
       "ELSE 0 END) AS october_" +year2.substring(2) +" "+
       ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year2 + "'  " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '11') " +
       "THEN 1 " +
       "ELSE 0 END) AS november_" +year2.substring(2) +" "+
       ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year2 + "' " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '12') " +
       "THEN 1 " +
       "ELSE 0 END) AS december_" +year2.substring(2) +" "+
       ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "'  " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '01') " +
       "THEN 1 " +
       "ELSE 0 END) AS january_" +year1.substring(2) +" "+
       ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "'  " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '02') " +
       "THEN 1 " +
       "ELSE 0 END) AS february_" +year1.substring(2) +" "+
       ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '03') " +
       "THEN 1 " +
       "ELSE 0 END) AS march_" +year1.substring(2) +" "+
      ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '04') " +
       "THEN 1 " +
       "ELSE 0 END) AS april_" +year1.substring(2) +" "+
      ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '05') " +
       "THEN 1 " +
       "ELSE 0 END) AS may_" +year1.substring(2) +" "+
       ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '06') " +
       "THEN 1 " +
       "ELSE 0 END) AS june_" +year1.substring(2) +" "+
      ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '07') " +
       "THEN 1 " +
       "ELSE 0 END) AS july_" +year1.substring(2) +" "+
      ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '08') " +
       "THEN 1 " +
       "ELSE 0 END) AS august_" +year1.substring(2) +" "+
       ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '09') " +
       "THEN 1 " +
       "ELSE 0 END) AS september_" +year1.substring(2) +" "+
       ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,10) BETWEEN '" + year2 + "-10-01' AND '" + year1 + "-09-30') " +
                "THEN 1 " +
                "ELSE 0 END) AS summary " +
       "FROM t_visit INNER JOIN t_visit_service " +
            "ON t_visit.t_visit_id = t_visit_service.t_visit_id " +
       "WHERE f_visit_status_id <> '4' " +
       "AND t_visit_service.b_service_point_id IN (SELECT b_service_point_id  " +
                                                "FROM r_service_point_type INNER JOIN r_service_point_type_map " +
                                                "ON r_service_point_type.r_service_point_type_id = r_service_point_type_map.r_service_point_type_id " +
                                                "WHERE service_point_type_number ='1') " +
       "AND date_part('dow',cast(to_number(substring(t_visit_service.assign_date_time,1,4),'9999') - 543 || substring(t_visit_service.assign_date_time,5,6) as DATE)) =  6 " +
       "AND substring(t_visit_service.assign_date_time,12,5) BETWEEN '"+ morning_sat_start +"' AND '"+ morning_sat_end +"' " +
       "GROUP BY t_visit.f_emergency_status_id) AS query1 " +
        "ON (f_emergency_status.f_emergency_status_id = query1.mergency_status_id) ";
        strbuff.append(SQL);
	SQL = "UNION " +

	"SELECT " + 
	"'5' AS order " +
	",'ER(sunday_morning)' AS TURN_OF_THE_TIME " +
	",f_emergency_status.emergency_status_description AS status " +
        ",(CASE WHEN (october_"+ year2.substring(2)+" IS NOT NULL) " +
         "THEN october_" +year2.substring(2) +" "+
         "ELSE 0 END) AS october " +

        ",(CASE WHEN (november_"+ year2.substring(2)+" IS NOT NULL) " +
         "THEN november_" +year2.substring(2) +" "+
         "ELSE 0 END) AS november " +

        ",(CASE WHEN (december_"+ year2.substring(2)+" IS NOT NULL) " +
         "THEN december_" +year2.substring(2) +" "+
         "ELSE 0 END) AS december " +

        ",(CASE WHEN (january_"+ year1.substring(2)+" IS NOT NULL) " +
         "THEN january_" +year1.substring(2) +" "+
         "ELSE 0 END) AS january " +

        ",(CASE WHEN (february_"+ year1.substring(2)+" IS NOT NULL) " +
         "THEN february_" +year1.substring(2) +" "+
         "ELSE 0 END) AS february " +

        ",(CASE WHEN (march_"+ year1.substring(2)+" IS NOT NULL) " +
         "THEN march_" +year1.substring(2) +" "+
         "ELSE 0 END) AS march " +
        
        ",(CASE WHEN (april_"+ year1.substring(2)+" IS NOT NULL) " +
         "THEN april_" +year1.substring(2) +" "+
         "ELSE 0 END) AS april " +
        
        ",(CASE WHEN (may_"+ year1.substring(2)+" IS NOT NULL) " +
         "THEN may_" +year1.substring(2) +" "+
         "ELSE 0 END) AS may " +

        ",(CASE WHEN (june_"+ year1.substring(2)+" IS NOT NULL) " +
         "THEN june_" +year1.substring(2) +" "+
         "ELSE 0 END) AS june " +

        ",(CASE WHEN (july_"+ year1.substring(2)+" IS NOT NULL) " +
         "THEN july_" +year1.substring(2) +" "+
         "ELSE 0 END) AS july " +

        ",(CASE WHEN (august_"+ year1.substring(2)+" IS NOT NULL) " +
         "THEN august_" +year1.substring(2) +" "+
         "ELSE 0 END) AS august " +
       
        ",(CASE WHEN (september_"+ year1.substring(2)+" IS NOT NULL) " +
         "THEN september_" +year1.substring(2) +" "+
         "ELSE 0 END) AS september " +
         
       ",(CASE WHEN (summary IS NOT NULL) " +
         "THEN summary " +
         "ELSE 0 END) AS total " +
"FROM  f_emergency_status LEFT JOIN  " +
       "(SELECT t_visit.f_emergency_status_id AS mergency_status_id " +
       ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year2 + "' " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '10') " +
       "THEN 1 " +
       "ELSE 0 END) AS october_" +year2.substring(2) +" "+
       ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year2 + "'  " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '11') " +
       "THEN 1 " +
       "ELSE 0 END) AS november_" +year2.substring(2) +" "+
       ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year2 + "' " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '12') " +
       "THEN 1 " +
       "ELSE 0 END) AS december_" +year2.substring(2) +" "+
       ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "'  " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '01') " +
       "THEN 1 " +
       "ELSE 0 END) AS january_" +year1.substring(2) +" "+
       ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "'  " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '02') " +
       "THEN 1 " +
       "ELSE 0 END) AS february_" +year1.substring(2) +" "+
       ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '03') " +
       "THEN 1 " +
       "ELSE 0 END) AS march_" +year1.substring(2) +" "+
      ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '04') " +
       "THEN 1 " +
       "ELSE 0 END) AS april_" +year1.substring(2) +" "+
      ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '05') " +
       "THEN 1 " +
       "ELSE 0 END) AS may_" +year1.substring(2) +" "+
       ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '06') " +
       "THEN 1 " +
       "ELSE 0 END) AS june_" +year1.substring(2) +" "+
      ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '07') " +
       "THEN 1 " +
       "ELSE 0 END) AS july_" +year1.substring(2) +" "+
      ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '08') " +
       "THEN 1 " +
       "ELSE 0 END) AS august_" +year1.substring(2) +" "+
       ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,4) = '" + year1 + "' " +
                    "AND substring(t_visit_service.assign_date_time,6,2) = '09') " +
       "THEN 1 " +
       "ELSE 0 END) AS september_" +year1.substring(2) +" "+
       ",SUM(CASE WHEN (substring(t_visit_service.assign_date_time,1,10) BETWEEN '" + year2 + "-10-01' AND '" + year1 + "-09-30') " +
                "THEN 1 " +
                "ELSE 0 END) AS summary " +
       "FROM t_visit INNER JOIN t_visit_service " +
            "ON t_visit.t_visit_id = t_visit_service.t_visit_id " +
       "WHERE f_visit_status_id <> '4' " +
       "AND t_visit_service.b_service_point_id IN (SELECT b_service_point_id  " +
                                                "FROM r_service_point_type INNER JOIN r_service_point_type_map " +
                                                "ON r_service_point_type.r_service_point_type_id = r_service_point_type_map.r_service_point_type_id " +
                                                "WHERE service_point_type_number ='1') " +
       "AND date_part('dow',cast(to_number(substring(t_visit_service.assign_date_time,1,4),'9999') - 543 || substring(t_visit_service.assign_date_time,5,6) as DATE)) =  0 " +     
       "AND substring(t_visit_service.assign_date_time,12,5) BETWEEN '"+ morning_sun_start +"' AND '"+ morning_sun_end +"' " +
       "GROUP BY t_visit.f_emergency_status_id) AS query1 " +
       "ON (f_emergency_status.f_emergency_status_id = query1.mergency_status_id) ";
            strbuff.append(SQL);
            SQL = strbuff.toString();
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
            strbuff = null;
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
                       // rowdata[i] = rs.getString(i+2);
                        if(rs.getString(i+2).length() == 11 && i==0)
                        {
                            rowdata[i] = rs.getString(i+2).substring(0, 3) + Language.getTextBundle(rs.getString(i+2).substring(3, 10)) +")";
                            System.out.println("--------"+ i+"---" + rowdata[i]);
                        }
                        else if(rs.getString(i+2).length() == 13 && i==0)
                        {
                            rowdata[i] = rs.getString(i+2).substring(0, 3) + Language.getTextBundle(rs.getString(i+2).substring(3, 12)) +")";
                            System.out.println("--------"+ i+"---" + rowdata[i]);
                        }
                        else if(rs.getString(i+2).length() == 9 && i==0)
                        {
                            rowdata[i] = rs.getString(i+2).substring(0, 3) + Language.getTextBundle(rs.getString(i+2).substring(3, 8)) +")";
                            System.out.println("--------"+ i+"---" + rowdata[i]);
                        }
                        else if(rs.getString(i+2).length() == 15 && i==0)
                        {
                            rowdata[i] = rs.getString(i+2).substring(0, 3) + Language.getTextBundle(rs.getString(i+2).substring(3, 14)) +")";
                            System.out.println("--------"+ i+"---" + rowdata[i]);
                        }
                        else if(rs.getString(i+2).length() == 18 && i==0)
                        {
                            rowdata[i] = rs.getString(i+2).substring(0, 3) + Language.getTextBundle(rs.getString(i+2).substring(3, 17)) +")";
                            System.out.println("--------"+ i+"---" + rowdata[i]);
                        }
                        else
                            rowdata[i] = rs.getString(i+2);
                    }
                }
                vString.add(rowdata);
            }
            vData.add(columnname);
            vData.add(vString);
        }
        return vData;
    }
    
    public static void main(String[] argv)
    {
        RPEmergencyPatientDB pr = new RPEmergencyPatientDB(null);
        pr.queryEmergencyPatientByDateTime("2549","2548","08:31","16:30","16:31","00:00", "00:01", "08:30","08:31","16:30","08:31","16:30");
    }
}
