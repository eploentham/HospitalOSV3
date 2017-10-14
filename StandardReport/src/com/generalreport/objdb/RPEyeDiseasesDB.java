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
import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalreport.object.*;
import com.generalreport.utility.Language;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author tong(Padungrat)
 */
public class RPEyeDiseasesDB {
    
    public ConnectionInf theConnectionInf;
    Vector vc;
    Patient thePatient;
    Visit theVisit;
    DiagIcd10 theDiagIcd10;
    
    String SQL = "";
    ResultSet rs = null;
    ResultSetMetaData metadata;
    java.util.Vector vDrugDispense ;
    Vector vString;
    Vector vData;
    int language = 1;
    public RPEyeDiseasesDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        initObject();    
    }
    
    /**
     *  ใช้ในการ new Object ของข้อมูลที่จะใช้ในการ ทำ SQL
     */
    private void initObject()
    {
        thePatient = new Patient();
        theVisit = new Visit();
        theDiagIcd10 = new DiagIcd10(); 
        
        thePatient.setInitDataFieldName();
        theVisit.setInitDataFieldName();
        theDiagIcd10.setInitDataFieldName();
    }
    
     public Vector queryEyeDiseasesByDate(String startDate,String finishDate)
    {   
        vc  =null;
        try
        {
            SQL = "SELECT " +
                    " r_eye_group.eye_group_description_th as eye_des, "+
                    " CASE WHEN (sum(eye_disage.new_patient_male) >0 )  "+
                    "        THEN sum(eye_disage.new_patient_male)  "+
                    "        ELSE 0  "+
                    " END as n_patient_male , "+
                    " CASE WHEN (sum(eye_disage.visit_male)  >0 )  "+
                    "        THEN sum(eye_disage.visit_male)   "+
                    "        ELSE 0  "+
                    " END 	as v_patient_male, "+
                    " CASE WHEN (sum(eye_disage.new_patient_female) >0 )  "+
                    "       THEN sum(eye_disage.new_patient_female)  "+
                    "       ELSE 0  "+
                    " END 	as n_patient_female, "+
                    " CASE WHEN (sum(eye_disage.visit_female)>0 )  "+
                    "       THEN sum(eye_disage.visit_female) "+
                    "       ELSE 0  "+
                    " END 	 as v_patient_female, "+
                    " CASE WHEN (sum(eye_disage.new_patient_nospec) >0 )  "+
                    "       THEN sum(eye_disage.new_patient_nospec)  "+
                    "       ELSE 0  "+
                    " END 	as n_patient_nospec, "+
                    " CASE WHEN (sum(eye_disage.visit_nospec)>0 )  "+
                    "       THEN sum(eye_disage.visit_nospec) "+
                    "       ELSE 0  "+
                    " END 	 as v_patient_nospec, "+

                    " CASE WHEN (sum(eye_disage.new_patient_count) >0 )  "+
                    "       THEN sum(eye_disage.new_patient_count)  "+
                    "       ELSE 0  "+
                    " END 	as n_patient_total, "+
                    " CASE WHEN (sum(eye_disage.visit_count)>0 )  "+
                    "       THEN sum(eye_disage.visit_count) "+
                    "       ELSE 0  "+
                    " END 	 as v_patient_total "+
                 " FROM  "+
                    " r_eye_group  LEFT JOIN "+
                    " ( "+

                    " SELECT "+
                    "       r_eye_disease_code.r_eye_group_id  "+
                    "       , CASE WHEN (("+ thePatient.tableName+"."+thePatient.patient_sex +" = '1') AND  "+
                    "                               ("+ thePatient.tableName+"."+thePatient.pk_table +" NOT IN  "+
                    "                               (SELECT distinct  "+ thePatient.tableName+"."+thePatient.pk_table +" FROM   "+
                    "                                         t_visit INNER JOIN  "+ thePatient.tableName+" ON "+ theVisit.tableName+"."+theVisit.patient_id +" =  "+ thePatient.tableName+"."+thePatient.pk_table +
                    "                                        WHERE  "+
                    "                                        ( "+ theVisit.tableName+"."+theVisit.visit_status +" <> '4')   "+
                    "                                         AND ( "+ theVisit.tableName+"."+theVisit.visit_type +" = '0')   "+
                    "                                        AND (( substring("+ theVisit.tableName+"."+theVisit.financial_discharge_time +",0,8) >= '"+ newYearInCurrent(startDate) + "')  "+
                    "                                           And ( substring("+ theVisit.tableName+"."+theVisit.financial_discharge_time +",0,8) < '"+ getYearmonth(startDate) + "'))  "+
                    "                                        Order by  "+ thePatient.tableName+"."+thePatient.pk_table +") "+
                    "                               )) "+
                    "                       THEN 1 "+
                    "                       ELSE 0 "+
                    "       END AS new_patient_male "+
                    "        , sum( CASE WHEN ("+ thePatient.tableName+"."+thePatient.patient_sex +" = '1') "+
                    "                        THEN 1 "+
                    "                        ELSE 0 "+
                    "       END) AS visit_male "+

                    "       ,  CASE WHEN (("+ thePatient.tableName+"."+thePatient.patient_sex +" = '2') AND  "+
                    "                               ("+ thePatient.tableName+"."+thePatient.pk_table +" NOT IN  "+
                    "                               (SELECT distinct  "+ thePatient.tableName+"."+thePatient.pk_table +" FROM   "+
                    "                                         "+ theVisit.tableName+" INNER JOIN  "+ thePatient.tableName+" ON "+ theVisit.tableName+"."+theVisit.patient_id +" =  "+ thePatient.tableName+"."+thePatient.pk_table +
                    "                                        WHERE  "+
                    "                                        ( "+ theVisit.tableName+"."+theVisit.visit_status +" <> '4')   "+
                    "                                        AND ( "+ theVisit.tableName+"."+theVisit.visit_type +" = '0')   "+
                    "                                        AND (( substring("+ theVisit.tableName+"."+theVisit.financial_discharge_time +",0,8) >= '"+ newYearInCurrent(startDate) + "')  "+
                    "                                           And ( substring("+ theVisit.tableName+"."+theVisit.financial_discharge_time +",0,8) < '"+ getYearmonth(startDate) + "'))  "+
                    "                                        Order by  "+ thePatient.tableName+"."+thePatient.pk_table +") "+
                    "                               )) "+
                    "                       THEN 1 "+
                    "                       ELSE 0 "+
                    "       END AS new_patient_female "+
                    "       , sum( CASE WHEN ("+ thePatient.tableName+"."+thePatient.patient_sex +" = '2') "+
                    "                       THEN 1 "+
                    "                       ELSE 0 "+
                    "       END) AS visit_female "+

                    "       ,  CASE WHEN (("+ thePatient.tableName+"."+thePatient.patient_sex +" = '3') AND  "+
                    "                               ("+ thePatient.tableName+"."+thePatient.pk_table +" NOT IN  "+
                    "                               (SELECT distinct  "+ thePatient.tableName+"."+thePatient.pk_table +" FROM   "+
                    "                                         "+ theVisit.tableName+" INNER JOIN  "+ thePatient.tableName+" ON "+ theVisit.tableName+"."+theVisit.patient_id +" =  "+ thePatient.tableName+"."+thePatient.pk_table +
                    "                                        WHERE  "+
                    "                                        ( "+ theVisit.tableName+"."+theVisit.visit_status +" <> '4')   "+
                    "                                        AND ( "+ theVisit.tableName+"."+theVisit.visit_type +" = '0')   "+
                    "                                        AND (( substring("+ theVisit.tableName+"."+theVisit.financial_discharge_time +",0,8) >= '"+ newYearInCurrent(startDate) + "')  "+
                    "                                           And ( substring("+ theVisit.tableName+"."+theVisit.financial_discharge_time +",0,8) < '"+ getYearmonth(startDate) + "'))  "+
                    "                                        Order by  "+ thePatient.tableName+"."+thePatient.pk_table +") "+
                    "                               )) "+
                    "                       THEN 1 "+
                    "                       ELSE 0 "+
                    "       END AS new_patient_nospec "+
                    "       , sum( CASE WHEN ("+ thePatient.tableName+"."+thePatient.patient_sex +" = '3') "+
                    "                       THEN 1 "+
                    "                       ELSE 0 "+
                    "       END) AS visit_nospec, "+

                    "       CASE WHEN (  "+
                    "                               ("+ thePatient.tableName+"."+thePatient.pk_table +" NOT IN  "+
                    "                               (SELECT distinct  "+ thePatient.tableName+"."+thePatient.pk_table +" FROM   "+
                    "                                         "+ theVisit.tableName+" INNER JOIN  "+ thePatient.tableName+" ON "+ theVisit.tableName+"."+theVisit.patient_id +" =  "+ thePatient.tableName+"."+thePatient.pk_table +
                    "                                        WHERE  "+
                    "                                        ( "+ theVisit.tableName+"."+theVisit.visit_status +" <> '4')   "+
                    "                                        AND ( "+ theVisit.tableName+"."+theVisit.visit_type +" = '0')   "+
                    "                                        AND (( substring("+ theVisit.tableName+"."+theVisit.financial_discharge_time +",0,8) >= '"+ newYearInCurrent(startDate) + "')  "+
                    "                                           And ( substring("+ theVisit.tableName+"."+theVisit.financial_discharge_time +",0,8) < '"+ getYearmonth(startDate) + "'))  "+
                    "                                        Order by  "+ thePatient.tableName+"."+thePatient.pk_table +") "+
                    "                               )) "+
                    "                       THEN 1 "+
                    "                       ELSE 0 "+
                    "       END AS new_patient_count, "+

                    "       count( "+ thePatient.tableName+"."+thePatient.patient_sex +" ) AS visit_count "+


                    " FROM  "+
                    "       r_eye_disease_code, "+
                    thePatient.tableName+" INNER JOIN  "+
                    "       (  "+ theVisit.tableName+" INNER JOIN "+ theDiagIcd10.tableName +
                    "        ON "+ theVisit.tableName+"."+theVisit.pk_table +" = "+ theDiagIcd10.tableName +"." + theDiagIcd10.vn +
                    "        )  "+
                    "        ON "+ thePatient.tableName+"."+thePatient.pk_table +" = "+ theVisit.tableName+"."+theVisit.patient_id +
                    " WHERE  "+
                    "       ( (  ( substring("+ theVisit.tableName+"."+theVisit.financial_discharge_time +",0,11))  "+
                    "               Between '"+ startDate +"' And '"+ finishDate + "')  "+
                    " AND (   ( "+ theDiagIcd10.tableName +"." + theDiagIcd10.icd10_code +")  "+
                    "              Between   r_eye_disease_code.eye_disease_code_begin   "+
                    "                    And   r_eye_disease_code.eye_disease_code_end ) "+
                    " AND (    ( "+ theDiagIcd10.tableName +"." + theDiagIcd10.type +")='1')  "+
                    " AND (( "+ theVisit.tableName+"."+theVisit.visit_status +")<>'4')" +
                    " AND t_diag_icd10.diag_icd10_active = '1' ) "+
                   " GROUP BY "+
                    "       r_eye_disease_code.r_eye_group_id, "+
                    thePatient.tableName+"."+thePatient.pk_table +", "+
                    "       new_patient_male, "+
                    "       new_patient_female, "+
                    "       new_patient_nospec, "+
                    "        new_patient_count  "+

                    " ) AS eye_disage  "+
                    " ON r_eye_group.r_eye_group_id = eye_disage.r_eye_group_id  "+
                 " GROUP BY  "+
                    " r_eye_group.eye_group_description_th, "+
                    " r_eye_group.eye_group_number " +
                  " ORDER BY " +
                    " TO_NUMBER( r_eye_group.eye_group_number,'99') ";
            System.out.println("SQL EYE : " + SQL);
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
     /**ใช้ในการตัดค่าของ yyyy-mm-dd เป็น yyyy-mm
      * param date เป็น String ของวันที่
      * return เป็น String ของวันที่ที่ทำการตัดแล้ว
      */
     private String getYearmonth(String date)
     {  String value = date;
         if(value != null && value.length()>7)
         {
             value = value.substring(0, 7);
         }
         
         return value;
     }
     
     private String newYearInCurrent(String date)
     {
         String value = date;
         if(value != null && value.length()>7)
         {
             value = value.substring(0, 4) + "-" + "01";
         }
         
         return value;
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
