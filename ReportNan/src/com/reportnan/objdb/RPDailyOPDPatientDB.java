/*
 * RPDailyOPDPatientDB.java
 *
 * Created on 3 มิถุนายน 2549, 17:26 น.
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
public class RPDailyOPDPatientDB
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
    StringBuffer strBuffer;
    /** Creates a new instance of RPDailyOPDPatientDB */
    public RPDailyOPDPatientDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    
    /**
     *รายงานประจำวัน งานผู้ป่วยนอก
     *@param year เป็น String ที่เก็บปีที่ต้องการดึงข้อมูล
     *@param month เป็น String ที่เก็บเดือนที่ต้องการดึงข้อมูล
     *@return Vector ของประชากร
     *@Author pu
     *@Date 03/06/2006
     */
    public Vector queryDailyOPDPatientByDateTime(String year ,String month,String morning_start,String morning_end,String afternoon_start,String afternoon_end)
    {
        try{  
            strBuffer = new StringBuffer();
            SQL = "SELECT " +
                    "'01' AS num " +
                    ",'ผู้รับบริการ' AS ORDER_DATE  " +
                    ",sum(case when q1.day_visit = '01' " +
                        "then 1 " +
                        "else 0 " + 
                     "end) AS day_1 " + 
                    ",sum(case when q1.day_visit = '02' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_2 " + 
                    ",sum(case when q1.day_visit = '03' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_3 " + 
                    ",sum(case when q1.day_visit = '04' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_4 " +
                    ",sum(case when q1.day_visit = '05' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_5 " +
                    ",sum(case when q1.day_visit = '06' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_6 " +
                    ",sum(case when q1.day_visit = '07' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_7 " +
                    ",sum(case when q1.day_visit = '08' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_8 " +
                    ",sum(case when q1.day_visit = '09' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_9 " +
                    ",sum(case when q1.day_visit = '10' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_10 " +
                    ",sum(case when q1.day_visit = '11' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_11 " +
                    ",sum(case when q1.day_visit = '12' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_12 " +
                    ",sum(case when q1.day_visit = '13' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_13 " +
                    ",sum(case when q1.day_visit = '14' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_14 " +
                    ",sum(case when q1.day_visit = '15' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_15 " +
                    ",sum(case when q1.day_visit = '16' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_16 " +
                     ",sum(case when q1.day_visit = '17' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_17 " +
                     ",sum(case when q1.day_visit = '18' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_18 " +
                     ",sum(case when q1.day_visit = '19' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_19 " +
                     ",sum(case when q1.day_visit = '20' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_20 " +
                     ",sum(case when q1.day_visit = '21' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_21 " +
                     ",sum(case when q1.day_visit = '22' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_22 " +
                     ",sum(case when q1.day_visit = '23' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_23 " +
                     ",sum(case when q1.day_visit = '24' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_24 " +
                     ",sum(case when q1.day_visit = '25' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_25 " +
                     ",sum(case when q1.day_visit = '26' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_26 " +
                     ",sum(case when q1.day_visit = '27' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_27 " +
                     ",sum(case when q1.day_visit = '28' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_28 " +
                     ",sum(case when q1.day_visit = '29' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_29 " +
                     ",sum(case when q1.day_visit = '30' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_30 " +
                     ",sum(case when q1.day_visit = '31' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_31 " +
                     ",sum(1) AS all_day " +
                " FROM " +
                "(select " +
                        "t_visit.t_visit_id " +
                        ",substring(t_visit.visit_begin_visit_time,9,2) AS day_visit " +
                " from " +
                    "t_visit INNER JOIN t_visit_service " +
                    "ON t_visit.t_visit_id = t_visit_service.t_visit_id " +
                "where " +
                    "t_visit.f_visit_status_id <> '4' " +
                    "and t_visit.f_visit_type_id = '0' " +
                    "and substring(t_visit.visit_begin_visit_time,1,4) = '"+ year +"' " +
                    "and substring(t_visit.visit_begin_visit_time,6,2) = '"+ month +"' " +
                    "and substring(t_visit.visit_begin_visit_time,12,5) Between '"+ morning_start +"' And '"+ afternoon_end +"' " +
                    "and t_visit_service.b_service_point_id IN (SELECT r_service_point_type_map.b_service_point_id " +
                                " FROM r_service_point_type INNER JOIN r_service_point_type_map " +
                                        "ON r_service_point_type.r_service_point_type_id = r_service_point_type_map.r_service_point_type_id  " +
                                "WHERE  r_service_point_type.service_point_type_number = '2') " +
                "group by  " +
                        "t_visit.t_visit_id,day_visit " +
                ") AS q1 " +

                "UNION " +

                "SELECT " +
                    "'02' AS num " +
                    ",'ผู้รับบริการ (เช้า)' AS ORDER_DATE " +
                    ",sum(case when q2.day_visit = '01' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_1 " +
                    ",sum(case when q2.day_visit = '02' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_2 " +
                    ",sum(case when q2.day_visit = '03' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_3 " +
                    ",sum(case when q2.day_visit = '04' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_4 " +
                    ",sum(case when q2.day_visit = '05' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_5 " +
                    ",sum(case when q2.day_visit = '06' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_6 " +
                    ",sum(case when q2.day_visit = '07' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_7 " +
                    ",sum(case when q2.day_visit = '08' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_8 " +
                    ",sum(case when q2.day_visit = '09' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_9 " +
                    ",sum(case when q2.day_visit = '10' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_10 " +
                    ",sum(case when q2.day_visit = '11' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_11 " +
                    ",sum(case when q2.day_visit = '12' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_12 " +
                    ",sum(case when q2.day_visit = '13' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_13 " +
                    ",sum(case when q2.day_visit = '14' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_14 " +
                    ",sum(case when q2.day_visit = '15' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_15 " +
                    ",sum(case when q2.day_visit = '16' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_16 " +
                     ",sum(case when q2.day_visit = '17' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_17 " +
                     ",sum(case when q2.day_visit = '18' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_18 " +
                     ",sum(case when q2.day_visit = '19' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_19 " +
                     ",sum(case when q2.day_visit = '20' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_20 " +
                     ",sum(case when q2.day_visit = '21' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_21 " +
                     ",sum(case when q2.day_visit = '22' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_22 " +
                     ",sum(case when q2.day_visit = '23' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_23 " +
                     ",sum(case when q2.day_visit = '24' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_24 " +
                     ",sum(case when q2.day_visit = '25' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_25 " +
                     ",sum(case when q2.day_visit = '26' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_26 " +
                     ",sum(case when q2.day_visit = '27' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_27 " +
                     ",sum(case when q2.day_visit = '28' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_28 " +
                     ",sum(case when q2.day_visit = '29' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_29 " +
                     ",sum(case when q2.day_visit = '30' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_30 " +
                     ",sum(case when q2.day_visit = '31' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_31 " +
                     ",sum(1) AS all_day " +
                " FROM " +
                "(select " +
                        "t_visit.t_visit_id " +
                        ",substring(t_visit.visit_begin_visit_time,9,2) AS day_visit " +
                " from " +
                    "t_visit INNER JOIN t_visit_service " +
                    "ON t_visit.t_visit_id = t_visit_service.t_visit_id " +
                "where " +
                    "t_visit.f_visit_status_id <> '4' " +
                    "and t_visit.f_visit_type_id = '0' " +
                    "and substring(t_visit.visit_begin_visit_time,1,4) = '"+ year +"' " +
                    "and substring(t_visit.visit_begin_visit_time,6,2) = '"+ month +"' " +
                    "and substring(t_visit.visit_begin_visit_time,12,5) Between '"+ morning_start +"' And '"+ morning_end +"' " +
                    "and t_visit_service.b_service_point_id IN (SELECT r_service_point_type_map.b_service_point_id " +
                                " FROM r_service_point_type INNER JOIN r_service_point_type_map " +
                                        "ON r_service_point_type.r_service_point_type_id = r_service_point_type_map.r_service_point_type_id  " +
                                "WHERE  r_service_point_type.service_point_type_number = '2') " +
                "group by  " +
                        "t_visit.t_visit_id,day_visit " +
                ") AS q2 " +

                "UNION " +

                "SELECT " +
                    "'03' AS num " +
                    ",'ผู้รับบริการ (บ่าย)' AS ORDER_DATE  " +   
                    ",sum(case when q3.day_visit = '01' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_1 " +
                    ",sum(case when q3.day_visit = '02' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_2 " +
                    ",sum(case when q3.day_visit = '03' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_3 " +
                    ",sum(case when q3.day_visit = '04' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_4 " +
                    ",sum(case when q3.day_visit = '05' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_5 " + 
                    ",sum(case when q3.day_visit = '06' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_6 " +
                    ",sum(case when q3.day_visit = '07' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_7 " +
                    ",sum(case when q3.day_visit = '08' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_8 " +
                    ",sum(case when q3.day_visit = '09' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_9 " +
                    ",sum(case when q3.day_visit = '10' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_10 " +
                    ",sum(case when q3.day_visit = '11' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_11 " +
                    ",sum(case when q3.day_visit = '12' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_12 " +
                    ",sum(case when q3.day_visit = '13' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_13 " +
                    ",sum(case when q3.day_visit = '14' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_14 " +
                    ",sum(case when q3.day_visit = '15' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_15 " +
                    ",sum(case when q3.day_visit = '16' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_16 " +
                     ",sum(case when q3.day_visit = '17' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_17 " +
                     ",sum(case when q3.day_visit = '18' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_18 " +
                     ",sum(case when q3.day_visit = '19' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_19 " +
                     ",sum(case when q3.day_visit = '20' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_20 " +
                     ",sum(case when q3.day_visit = '21' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_21 " +
                     ",sum(case when q3.day_visit = '22' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_22 " +
                     ",sum(case when q3.day_visit = '23' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_23 " +
                     ",sum(case when q3.day_visit = '24' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_24 " +
                     ",sum(case when q3.day_visit = '25' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_25 " +
                     ",sum(case when q3.day_visit = '26' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_26 " +
                     ",sum(case when q3.day_visit = '27' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_27 " +
                     ",sum(case when q3.day_visit = '28' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_28 " +
                     ",sum(case when q3.day_visit = '29' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_29 " +
                     ",sum(case when q3.day_visit = '30' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_30 " +
                     ",sum(case when q3.day_visit = '31' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_31 " +
                     ",sum(1) AS all_day " +
                " FROM " +
                "(select " +
                        "t_visit.t_visit_id " +
                        ",substring(t_visit.visit_begin_visit_time,9,2) AS day_visit " +
                " from " +
                    "t_visit INNER JOIN t_visit_service " +
                    "ON t_visit.t_visit_id = t_visit_service.t_visit_id " +
                "where " +
                    "t_visit.f_visit_status_id <> '4' " +
                    "and t_visit.f_visit_type_id = '0' " +
                    "and substring(t_visit.visit_begin_visit_time,1,4) = '"+ year +"' " +
                    "and substring(t_visit.visit_begin_visit_time,6,2) = '"+ month +"' " +
                    "and substring(t_visit.visit_begin_visit_time,12,5) Between '"+ afternoon_start +"' And '"+ afternoon_end +"' " +
                    "and t_visit_service.b_service_point_id IN (SELECT r_service_point_type_map.b_service_point_id " +
                                " FROM r_service_point_type INNER JOIN r_service_point_type_map " +
                                        "ON r_service_point_type.r_service_point_type_id = r_service_point_type_map.r_service_point_type_id  " +
                                "WHERE  r_service_point_type.service_point_type_number = '2') " +
                "group by  " +
                        "t_visit.t_visit_id,day_visit " +
                ") AS q3 " +

                "UNION " +

                "select " +
                "'04' AS num " +
                ",'ผู้ป่วยนัด' AS  status_name " +
                ",sum(case when substring(patient_appointment_date,9,2) = '01' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_1 " +
                    ",sum(case when substring(patient_appointment_date,9,2) = '02' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_2 " +
                    ",sum(case when substring(patient_appointment_date,9,2) = '03' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_3 " +
                    ",sum(case when substring(patient_appointment_date,9,2) = '04' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_4 " +
                    ",sum(case when substring(patient_appointment_date,9,2) = '05' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_5 " +
                    ",sum(case when substring(patient_appointment_date,9,2) = '06' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_6 " +
                    ",sum(case when substring(patient_appointment_date,9,2) = '07' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_7 " +
                    ",sum(case when substring(patient_appointment_date,9,2) = '08' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_8 " +
                    ",sum(case when substring(patient_appointment_date,9,2) = '09' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_9 " +
                    ",sum(case when substring(patient_appointment_date,9,2) = '10' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_10 " +
                    ",sum(case when substring(patient_appointment_date,9,2) = '11' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_11 " +
                    ",sum(case when substring(patient_appointment_date,9,2) = '12' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_12 " +
                    ",sum(case when substring(patient_appointment_date,9,2) = '13' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_13 " +
                    ",sum(case when substring(patient_appointment_date,9,2) = '14' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_14 " +
                    ",sum(case when substring(patient_appointment_date,9,2) = '15' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_15 " +
                    ",sum(case when substring(patient_appointment_date,9,2) = '16' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_16 " +
                     ",sum(case when substring(patient_appointment_date,9,2) = '17' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_17 " +
                     ",sum(case when substring(patient_appointment_date,9,2) = '18' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_18 " +
                     ",sum(case when substring(patient_appointment_date,9,2) = '19' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_19 " +
                     ",sum(case when substring(patient_appointment_date,9,2) = '20' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_20 " +
                     ",sum(case when substring(patient_appointment_date,9,2) = '21' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_21 " +
                     ",sum(case when substring(patient_appointment_date,9,2) = '22' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_22 " +
                     ",sum(case when substring(patient_appointment_date,9,2) = '23' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_23 " +
                     ",sum(case when substring(patient_appointment_date,9,2) = '24' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_24 " +
                     ",sum(case when substring(patient_appointment_date,9,2) = '25' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_25 " +
                     ",sum(case when substring(patient_appointment_date,9,2) = '26' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_26 " +
                     ",sum(case when substring(patient_appointment_date,9,2) = '27' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_27 " +
                     ",sum(case when substring(patient_appointment_date,9,2) = '28' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_28 " +
                     ",sum(case when substring(patient_appointment_date,9,2) = '29' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_29 " +
                     ",sum(case when substring(patient_appointment_date,9,2) = '30' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_30 " +
                     ",sum(case when substring(patient_appointment_date,9,2) = '31' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_31 " +
                     ",sum(1) AS all_day " +
                " from " +
                        "t_patient_appointment " +
                "where " +
                        "patient_appointment_active = '1' " +
                        "and substring(patient_appointment_date,1,4) = '"+ year +"' " +
                        "and substring(patient_appointment_date,6,2) = '"+ month +"' " +
                        "and t_patient_appointment.patient_appointment_clinic IN (SELECT r_service_point_type_map.b_service_point_id " +
                                " FROM r_service_point_type INNER JOIN r_service_point_type_map " +
                                        "ON r_service_point_type.r_service_point_type_id = r_service_point_type_map.r_service_point_type_id  " +
                                "WHERE  r_service_point_type.service_point_type_number = '2') " +
                "UNION " +

                "select " +
                "'05' AS num " +
                ",f_appointment_status.appointment_status_name AS  status_name " +
                ",sum(case when query1.day_appoint = '01' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_1 " +
                    ",sum(case when query1.day_appoint = '02' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_2 " +
                    ",sum(case when query1.day_appoint = '03' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_3 " +
                    ",sum(case when query1.day_appoint = '04' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_4 " +
                    ",sum(case when query1.day_appoint = '05' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_5 " +
                    ",sum(case when query1.day_appoint = '06' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_6 " +
                    ",sum(case when query1.day_appoint = '07' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_7 " +
                    ",sum(case when query1.day_appoint = '08' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_8 " +
                    ",sum(case when query1.day_appoint = '09' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_9 " +
                    ",sum(case when query1.day_appoint = '10' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_10 " +
                    ",sum(case when query1.day_appoint = '11' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_11 " +
                    ",sum(case when query1.day_appoint = '12' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_12 " +
                    ",sum(case when query1.day_appoint = '13' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_13 " +
                    ",sum(case when query1.day_appoint = '14' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_14 " +
                    ",sum(case when query1.day_appoint = '15' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_15 " +
                    ",sum(case when query1.day_appoint = '16' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_16 " +
                     ",sum(case when query1.day_appoint = '17' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_17 " +
                     ",sum(case when query1.day_appoint = '18' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_18 " +
                     ",sum(case when query1.day_appoint = '19' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_19 " +
                     ",sum(case when query1.day_appoint = '20' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_20 " +
                     ",sum(case when query1.day_appoint = '21' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_21 " +
                     ",sum(case when query1.day_appoint = '22' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_22 " +
                     ",sum(case when query1.day_appoint = '23' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_23 " +
                     ",sum(case when query1.day_appoint = '24' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_24 " +
                     ",sum(case when query1.day_appoint = '25' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_25 " +
                     ",sum(case when query1.day_appoint = '26' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_26 " +
                     ",sum(case when query1.day_appoint = '27' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_27 " +
                     ",sum(case when query1.day_appoint = '28' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_28 " +
                     ",sum(case when query1.day_appoint = '29' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_29 " +
                     ",sum(case when query1.day_appoint = '30' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_30 " +
                     ",sum(case when query1.day_appoint = '31' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_31 " +
                     ",sum(case when query1.status IS NOT NULL  " +
                        "then 1 " + 
                        "else 0 " + 
                     "end) AS all_day " +
                 " from " +
                "f_appointment_status " +
                "LEFT JOIN  " +
                        "(select  " +
                            "patient_appointment_status AS status " +
                            ",substring(patient_appointment_date,9,2) AS  day_appoint " +
                        " from " +
                            "t_patient_appointment " +
                        "where " +
                            "patient_appointment_active = '1' " +
                            "and substring(patient_appointment_date,1,4) = '"+ year +"' " +
                            "and substring(patient_appointment_date,6,2) = '"+ month +"' " +
                            "and t_patient_appointment.patient_appointment_clinic IN (SELECT r_service_point_type_map.b_service_point_id " +
                                " FROM r_service_point_type INNER JOIN r_service_point_type_map " +
                                        "ON r_service_point_type.r_service_point_type_id = r_service_point_type_map.r_service_point_type_id  " +
                                "WHERE  r_service_point_type.service_point_type_number = '2') " +
                        ") AS query1 " +
                "ON f_appointment_status.f_appointment_status_id = query1.status " +
                "group by " +
                "status_name, num ";
               strBuffer.append(SQL);

               SQL =  "UNION " +

                "SELECT " +
                  "'06' AS num " +
                  ", q_refer_in.type_refer " +
                    ",sum(case when q_refer_in.day_visit  = '01' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_1 " +
                    ",sum(case when q_refer_in.day_visit  = '02' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_2 " +
                    ",sum(case when q_refer_in.day_visit  = '03' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_3 " +
                    ",sum(case when q_refer_in.day_visit  = '04' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_4 " +
                    ",sum(case when q_refer_in.day_visit  = '05' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_5 " +
                    ",sum(case when q_refer_in.day_visit  = '06' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_6 " +
                    ",sum(case when q_refer_in.day_visit  = '07' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_7 " +
                    ",sum(case when q_refer_in.day_visit  = '08' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_8 " +
                    ",sum(case when q_refer_in.day_visit  = '09' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_9 " +
                    ",sum(case when q_refer_in.day_visit  = '10' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_10 " +
                    ",sum(case when q_refer_in.day_visit  = '11' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_11 " +
                    ",sum(case when q_refer_in.day_visit  = '12' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_12 " +
                    ",sum(case when q_refer_in.day_visit  = '13' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_13 " +
                    ",sum(case when q_refer_in.day_visit  = '14' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_14 " +
                    ",sum(case when q_refer_in.day_visit  = '15' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_15 " +
                    ",sum(case when q_refer_in.day_visit  = '16' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_16 " +
                     ",sum(case when q_refer_in.day_visit  = '17' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_17 " +
                     ",sum(case when q_refer_in.day_visit  = '18' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_18 " +
                     ",sum(case when q_refer_in.day_visit  = '19' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_19 " +
                     ",sum(case when q_refer_in.day_visit  = '20' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_20 " +
                     ",sum(case when q_refer_in.day_visit  = '21' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_21 " +
                     ",sum(case when q_refer_in.day_visit  = '22' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_22 " +
                     ",sum(case when q_refer_in.day_visit  = '23' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_23 " +
                     ",sum(case when q_refer_in.day_visit  = '24' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_24 " +
                     ",sum(case when q_refer_in.day_visit  = '25' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_25 " +
                     ",sum(case when q_refer_in.day_visit  = '26' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_26 " +
                     ",sum(case when q_refer_in.day_visit  = '27' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_27 " +
                     ",sum(case when q_refer_in.day_visit  = '28' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_28 " +
                     ",sum(case when q_refer_in.day_visit  = '29' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_29 " +
                     ",sum(case when q_refer_in.day_visit  = '30' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_30 " +
                     ",sum(case when q_refer_in.day_visit = '31' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_31 " +
                    ", count(*) AS all_day " +
                "FROM " +
                "(select " + 
                     "case when r_refer_office.r_refer_office_type_id = '01' " +
                        "then 'รับ Refer จาก สอ.' " +
                        "when (r_refer_office.r_refer_office_type_id = '02'  " +
                                "or r_refer_office.r_refer_office_type_id = '03') " +
                        "then 'รับ Refer จาก ' || r_refer_office.refer_office_description " +
                        "else 'ไม่ทราบ' " +
                     "end AS type_refer " +
                     ", substring(t_visit.visit_begin_visit_time,9,2) AS day_visit " +
                " from " +
                    "t_visit INNER JOIN t_visit_service " +
                    "ON t_visit.t_visit_id = t_visit_service.t_visit_id  " +
                    "INNER JOIN r_refer_office " +
                    "ON t_visit.b_visit_office_id_refer_in = r_refer_office.refer_office_code " +
                "where " +
                    "t_visit.f_visit_status_id <> '4' " +
                    "and t_visit.f_visit_type_id = '0' " +
                    "and substring(t_visit.visit_begin_visit_time,1,4) = '"+ year +"' " +
                    "and substring(t_visit.visit_begin_visit_time,6,2) = '"+ month +"' " +
                    "and t_visit_service.b_service_point_id IN (SELECT r_service_point_type_map.b_service_point_id " +
                                " FROM r_service_point_type INNER JOIN r_service_point_type_map " +
                                        "ON r_service_point_type.r_service_point_type_id = r_service_point_type_map.r_service_point_type_id  " +
                                "WHERE  r_service_point_type.service_point_type_number = '2') " +
                "group by " +
                        "type_refer,day_visit " +
                ") AS q_refer_in " +
                "GROUP BY " +
                    "type_refer,num " +

                "UNION " +

                "SELECT " +
                     "'07 ' AS num " +
                    ",q_item.opd_item_description " +
                    ",sum(case when q_item.day_visit = '01' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_1 " +
                    ",sum(case when q_item.day_visit = '02' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_2 " +
                    ",sum(case when q_item.day_visit = '03' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_3 " +
                    ",sum(case when q_item.day_visit = '04' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_4 " +
                    ",sum(case when q_item.day_visit = '05' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_5 " +
                    ",sum(case when q_item.day_visit = '06' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_6 " +
                    ",sum(case when q_item.day_visit = '07' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_7 " +
                    ",sum(case when q_item.day_visit = '08' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_8 " +
                    ",sum(case when q_item.day_visit = '09' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_9 " +
                    ",sum(case when q_item.day_visit = '10' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_10 " +
                    ",sum(case when q_item.day_visit = '11' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_11 " +
                    ",sum(case when q_item.day_visit = '12' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_12 " +
                    ",sum(case when q_item.day_visit = '13' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_13 " +
                    ",sum(case when q_item.day_visit = '14' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_14 " +
                    ",sum(case when q_item.day_visit = '15' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_15 " +
                    ",sum(case when q_item.day_visit = '16' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_16 " +
                     ",sum(case when q_item.day_visit = '17' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_17 " +
                     ",sum(case when q_item.day_visit = '18' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_18 " +
                     ",sum(case when q_item.day_visit = '19' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_19 " +
                     ",sum(case when q_item.day_visit = '20' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_20 " +
                     ",sum(case when q_item.day_visit = '21' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_21 " +
                     ",sum(case when q_item.day_visit = '22' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_22 " +
                     ",sum(case when q_item.day_visit = '23' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_23 " +
                     ",sum(case when q_item.day_visit = '24' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_24 " +
                     ",sum(case when q_item.day_visit = '25' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_25 " +
                     ",sum(case when q_item.day_visit = '26' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_26 " +
                     ",sum(case when q_item.day_visit = '27' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_27 " +
                     ",sum(case when q_item.day_visit = '28' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_28 " +
                     ",sum(case when q_item.day_visit = '29' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_29 " +
                     ",sum(case when q_item.day_visit = '30' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_30 " +
                     ",sum(case when q_item.day_visit = '31' " +
                        "then 1 " +
                        "else 0 " +
                     "end) AS day_31 " +
                    ", count(*) AS all_day " +
                " FROM " +
                "(select " +
                        "r_opd_item.opd_item_description AS opd_item_description " +
                        ",substring(t_visit.visit_begin_visit_time,9,2) AS day_visit " +
                " from " +
                    "t_visit INNER JOIN t_order " +
                    "ON t_visit.t_visit_id = t_order.t_visit_id " +
                    "INNER JOIN r_opd_item " +
                    "ON t_order.b_item_id = r_opd_item.b_item_id " +
                    "INNER JOIN t_visit_service " +
                    "ON t_visit.t_visit_id = t_visit_service.t_visit_id " + 
                "where " +
                    "t_visit.f_visit_status_id <> '4' " +
                    "and t_visit.f_visit_type_id = '0' " +
                    "and t_order.f_order_status_id <> '0' " + 
                    "and t_order.f_order_status_id <> '1' " + 
                    "and t_order.f_order_status_id <> '3' " +
                    "and substring(t_visit.visit_begin_visit_time,1,4) = '"+ year +"' " +
                    "and substring(t_visit.visit_begin_visit_time,6,2) = '"+ month +"' " +
                    "and t_visit_service.b_service_point_id IN (SELECT r_service_point_type_map.b_service_point_id " +
                                " FROM r_service_point_type INNER JOIN r_service_point_type_map " +
                                        "ON r_service_point_type.r_service_point_type_id = r_service_point_type_map.r_service_point_type_id " + 
                                "WHERE  r_service_point_type.service_point_type_number = '2') " +
                "group by " +
                        "opd_item_description " +
                        ",day_visit " +
                ") AS q_item " +
                "GROUP BY " +
                    "opd_item_description, num";
        strBuffer.append(SQL);
        SQL = strBuffer.toString();
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
    /**ตรวจสอบเงื่อนไข ไม่แสดงคอลัมภ์แรก
     */
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
            for(int i = 0 ; i < (column-1);i++)
            {
                columnname[i] = metadata.getColumnLabel(i+2);
                columnname[i] = Language.getTextBundle(columnname[i].toUpperCase());
            }
            //ทำการให้ค่าของ field กับข้อมูล
            while(rs.next())
            {
                rowdata = new String[column-1];
                for(int i = 0 ; i < (column-1);i++)
                {
                    if(rs.getString(i+2) == null)
                    {
                        rowdata[i] = "0";
                    }
                    else
                    {
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
}
