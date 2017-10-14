/*
 * RPPcuChronicDB.java
 *
 * Created on 11 กุมภาพันธ์ 2549, 14:29 น.
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
 * @author nu_ojika
 */
public class RPPcuChronicDB
{
    public ConnectionInf theConnectionInf;
    Vector vc;
    
    String SQL = "";
    ResultSet rs = null;
    ResultSetMetaData metadata;
    Vector vString;
    Vector vData;
    /** Creates a new instance of RPPcuChronicDB */
    public RPPcuChronicDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
    }
    
    /*
     * ค้นหาจำนวนผู้ป่วยตามเงื่อนไขที่กำหนดมา
     * @Param selectReport2 คือ การเลือกว่าผู้ป่วยเสียชีวิตหรือไม่ 1 คือ ยังมีชีวิต , 2 คือ เสียชีวิต
     * @Param selectReport3 คือ เลือกว่าผู้ป่วยในเขต หรือทั้งหมด 1 คือ ในเขต , 2 คือ ทั้งหมด(ในเขต+นอกเขต) 
     * @Param selectVillage คือ รหัสบ้านที่เลือก 
     * @Return Vector ของ หัวตารางและข้อมูลจำนวนผู้ป่วยที่ได้จกการค้นหา
     * @Author Ojika
     * @Date 11/02/2549
     * @Modify By Pu
     * @Modify date 22/04/2006
     **/
    public Vector queryPatientAmount(int selectReport2, int selectReport3, String selectVillage, String endDate)
    {   
        vc = null;
        
        SQL = " SELECT " +
                " query1.chronic_name AS chronic_name " +
                " ,sum(query1.male) AS male " +
                " ,sum(query1.female) AS female " +
                " ,sum(query1.nonspec_sex) AS nonspec_sex " +
                " ,sum(query1.total) AS total " +
                " FROM " +
                " (SELECT  " +
                " t_health_family.t_health_family_id " +
                " , CASE WHEN (t_health_family.f_sex_id = '1') " +
                " THEN 1 " +
                " ELSE 0 " +
                " END AS male " +
                " , CASE WHEN (t_health_family.f_sex_id = '2') " +
                " THEN 1 " +
                " ELSE 0 " +
                " END AS female " +
                " , CASE WHEN (t_health_family.f_sex_id <> '2' and t_health_family.f_sex_id <> '1') " +
                " THEN 1 " +
                " ELSE 0 " +
                " END AS nonspec_sex " +
                " , 1 AS total " +
                " , b_group_chronic.group_chronic_number AS chronic_number " +
                " , b_group_chronic.group_chronic_description_th AS chronic_name " +
                " ,b_site.site_full_name AS hos " +
                " FROM " +
                " b_site " +
                " ,t_chronic INNER JOIN t_health_family  " +
                " ON t_chronic.t_health_family_id = t_health_family.t_health_family_id  " +
                " INNER JOIN b_group_icd10 " +
                " ON substring(t_chronic.chronic_icd10,0,4) = b_group_icd10.group_icd10_number " +
                " INNER JOIN b_group_chronic " +
                " ON b_group_icd10.b_group_chronic_id = b_group_chronic.b_group_chronic_id " +
                " INNER JOIN t_health_home " +
                " ON t_health_home.t_health_home_id = t_health_family.t_health_home_id " +
                " INNER JOIN t_health_village " +
                " ON t_health_village.t_health_village_id = t_health_home.t_health_village_id ";
         if(selectReport2 == 1)
         {
            // ผู้ป่วยที่ยังไม่เสียชีวิต
                SQL = SQL + " WHERE t_health_family.f_patient_discharge_status_id <> '1' ";
         }
         else if(selectReport2 == 2)
         {
            // ผู้ป่วยเสียชีวิต
                SQL = SQL + " WHERE t_health_family.f_patient_discharge_status_id = '1' ";
         }        
        if(selectReport3 == 1)
        {
            // ในเขต
           /* SQL = SQL + " INNER JOIN t_health_home " +
                        " ON t_health_home.t_health_home_id = t_health_family.t_health_home_id " +
                        " INNER JOIN t_health_village " +
                        " ON t_health_village.t_health_village_id = t_health_home.t_health_village_id ";
            */
            SQL = SQL + " AND t_health_home.health_home_moo <> '0' ";
        }
         if(selectReport3 == 1 && !selectVillage.equalsIgnoreCase("0") && !selectVillage.equalsIgnoreCase(""))
         {
            // ในเขต ระบุหมู่บ้าน
                SQL = SQL + " AND t_health_village.t_health_village_id = '"+ selectVillage +"' ";
         }
                
                SQL = SQL + " GROUP BY  " +
                " t_health_family.t_health_family_id " +
                " , t_health_family.f_sex_id " +
                " , b_group_chronic.group_chronic_number " +
                " , b_group_chronic.group_chronic_description_th  " +
                " ,b_site.site_full_name " +
                " ) AS query1 " +
                " GROUP BY " +
                " query1.chronic_name ";
        
        System.out.println(" queryChronicPCU Amount : " + SQL);
            
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
    
    /*
     * ค้นหารายชื่อผู้ป่วยตามเงื่อนไขที่กำหนดมา
     * @Param selectReport2 คือ การเลือกว่าผู้ป่วยเสียชีวิตหรือไม่ 1 คือ ยังมีชีวิต , 2 คือ เสียชีวิต
     * @Param selectReport3 คือ เลือกว่าผู้ป่วยในเขต หรือทั้งหมด 1 คือ ในเขต , 2 คือ ทั้งหมด(ในเขต+นอกเขต) 
     * @Param selectVillage คือ รหัสบ้านที่เลือก 
     * @Return Vector ของ หัวตารางและข้อมูลจำนวนผู้ป่วยที่ได้จกการค้นหา
     * @Author Ojika
     * @Date 11/02/2549
     * @Modify By Pu
     * @Modify date 22/04/2006
     **/
    public Vector queryPatientName(int selectReport2, int selectReport3, String selectVillage, String endDate)
    {   
        vc = null;        
        SQL = " SELECT  " +
                " b_group_chronic.group_chronic_description_th AS chronic_name " +
                " , t_patient.patient_hn AS hn " +
                " , f_patient_prefix.patient_prefix_description AS prefix " +
                " , (t_health_family.patient_name || '  ' || t_health_family.patient_last_name) AS name " +
                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD') AS BIRTHDATE " +
                " ,case when ((t_health_family.patient_birthday IS NOT NULL) " +
                                " AND (substring(' ' || age(to_date('"+endDate+"','YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ) IS NOT NULL) " +
                        " then (substring(' ' || age(to_date('"+endDate+"','YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ) " +
                        " else '' " +
                    " end AS YEAR " +
                    " ,case when ((t_health_family.patient_birthday IS NOT NULL) " +
                                " AND (substring(' ' || age(to_date('"+endDate+"','YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') ) IS NOT NULL) " +
                        " then (substring(' ' || age(to_date('"+endDate+"','YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') ) " +
                        " else '' " +
                    " end AS MONTH " +
                    " ,case when ((t_health_family.patient_birthday IS NOT NULL) " +
                                " AND (substring(' ' || age(to_date('"+endDate+"','YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)day') ) IS NOT NULL) " +
                        " then (substring(' ' || age(to_date('"+endDate+"','YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)day') ) " +
                        " else '' " +
                    " end AS DAY " +    
                " , t_health_home.health_home_house AS house" +
                " , t_health_home.health_home_moo AS moo " +
                " , f_address1.address_description AS tambon " +
                " , f_address2.address_description AS amphur " +
                " , f_address3.address_description AS changwat " +                
                " , b_site.site_full_name AS treat_hos " +
                " FROM  " +
                " b_site " +
                " ,t_chronic INNER JOIN t_health_family " +
                " ON t_chronic.t_health_family_id = t_health_family.t_health_family_id " +
                " LEFT JOIN t_patient " +
                " ON t_chronic.t_patient_id = t_patient.t_patient_id " +
                " INNER JOIN b_group_icd10 " +
                " ON substring(t_chronic.chronic_icd10,0,4) = b_group_icd10.group_icd10_number " +
                " INNER JOIN b_group_chronic " +
                " ON b_group_icd10.b_group_chronic_id = b_group_chronic.b_group_chronic_id " +
                " INNER JOIN t_health_home " +
                " ON t_health_home.t_health_home_id = t_health_family.t_health_home_id " +
                " INNER JOIN t_health_village " +
                " ON t_health_village.t_health_village_id = t_health_home.t_health_village_id " +
                " LEFT JOIN f_patient_prefix " +
                " ON f_patient_prefix.f_patient_prefix_id = t_health_family.f_prefix_id " +
                " LEFT JOIN f_address AS f_address1 " +
                " ON f_address1.f_address_id = t_health_home.health_home_tambon " +
                " LEFT JOIN f_address AS f_address2 " +
                " ON f_address2.f_address_id = t_health_home.health_home_amphur " +
                " LEFT JOIN f_address AS f_address3 " +
                " ON f_address3.f_address_id = t_health_home.health_home_changwat ";
        
         if(selectReport2 == 1)
         {
                // ยังมีชีวิต
                SQL = SQL + " WHERE " +
                            " t_health_family.f_patient_discharge_status_id <> '1' ";
         }
         else if(selectReport2 == 2)
         {
                // เสียชีวิต
                SQL = SQL + " WHERE " +
                            " t_health_family.f_patient_discharge_status_id = '1' ";
         }
        if(selectReport3 == 1)
        {
            // ในเขต
           SQL = SQL + "AND t_health_home.health_home_moo <> '0' ";        
        }  
         if(selectReport3 == 1 && !selectVillage.equalsIgnoreCase("0") && !selectVillage.equalsIgnoreCase(""))
         {
            // ในเขต ระบุหมู่บ้าน
                SQL = SQL + " AND t_health_village.t_health_village_id = '"+ selectVillage +"' ";
         }
                
                SQL = SQL + " GROUP BY " +
                            " t_patient.t_patient_id " +
                            " , t_patient.patient_hn " +
                            " , f_patient_prefix.patient_prefix_description " +
                            " , t_health_family.patient_name " +
                            " , t_health_family.patient_last_name " +
                            " , t_health_family.patient_birthday " +
                            " , t_health_home.health_home_house " +
                            " , t_health_home.health_home_road " +
                            " , t_health_home.health_home_moo " +
                            " , f_address1.address_description  " +
                            " , f_address2.address_description  " +
                            " , f_address3.address_description  " +
                            " , b_group_chronic.group_chronic_number  " +
                            " , b_group_chronic.group_chronic_description_th  " +
                            " ,b_site.site_full_name  " +
                            " ORDER BY " +
                            " b_group_chronic.group_chronic_description_th ";
        
        System.out.println(" queryChronicPCU NAME : " + SQL);
            
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
                    {   rowdata[i] = rs.getString(i+1); 
                        if(metadata.getColumnTypeName(i+1).equalsIgnoreCase("date"))
                        {    
                            if(rowdata[i].trim().length() != 0)
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
