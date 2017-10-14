/*
 * RPPcuEpiDB.java
 *
 * Created on 31 มีนาคม 2549, 16:30 น.
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
public class RPPcuEpiDB
{
    public ConnectionInf theConnectionInf;
    Vector vc;
    
    String SQL = "";
    ResultSet rs = null;
    ResultSetMetaData metadata;
    Vector vString;
    Vector vData;
    /** Creates a new instance of RPPcuChronicDB */
    public RPPcuEpiDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
    }
    
    /**
     * ดึงข้อมูล รายชื่อเด็กที่ได้รับวัคซีน
     * @Author Ojika
     * @Date 31/03/2549
     **/
    public Vector queryListEpiPerson(String selectVillage, String startDate, String endDate)
    {   
        vc = null;
        
        SQL = " select " +
                    " t_health_village.village_moo AS MOO " +
                    " ,t_health_village.village_name AS VILLAGE_NAME " +
                    " ,case when (f_patient_prefix.patient_prefix_description IS NOT NULL)" +
                            " then f_patient_prefix.patient_prefix_description " +
                            " else '' " +
                    "  end AS PREFIX " +
                    " ,case when (t_health_family.patient_name IS NOT NULL)" +
                            " then t_health_family.patient_name " +
                            " else '' " +
                    "  end AS FIRSTNAME " +
                    " ,case when (t_health_family.patient_last_name IS NOT NULL)" +
                            " then t_health_family.patient_last_name " +
                            " else '' " +
                    "  end AS LASTNAME " +
                    " ,case when ((t_health_family.patient_birthday IS NOT NULL )" +
                                " and (substring(' ' || age(to_date('"+endDate+"','YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ) IS NOT NULL)" +
                            " then (substring(' ' || age(to_date('"+endDate+"','YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ) " +
                            " else '' " +
                    " end AS YEAR " +
                    " ,case when ((t_health_family.patient_birthday IS NOT NULL )" +
                                " and (substring(' ' || age(to_date('"+endDate+"','YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') ) IS NOT NULL)" +
                            " then (substring(' ' || age(to_date('"+endDate+"','YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') ) " +
                            " else '' " +
                    " end AS MONTH " +
                    " ,case when ((t_health_family.patient_birthday IS NOT NULL )" +
                                " and (substring(' ' || age(to_date('"+endDate+"','YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)day') ) IS NOT NULL)" +
                            " then (substring(' ' || age(to_date('"+endDate+"','YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)day') ) " +
                            " else '' " +
                    " end AS DAY " +                    
                    " ,case when (t_health_home.health_home_house IS NOT NULL)" +
                            " then t_health_home.health_home_house " +
                            " else '' " +
                    "  end AS HOUSE " +
                    " ,case when (t_health_family.patient_father_firstname IS NOT NULL)" +
                            " then t_health_family.patient_father_firstname " +
                            " else '' " +
                    "  end AS FATHERNAME " +
                    " ,case when (t_health_family.patient_mother_firstname IS NOT NULL)" +
                            " then t_health_family.patient_mother_firstname " +
                            " else '' " +
                    "  end AS MOTHERNAME " +
                    " ,b_health_epi_group.health_epi_group_description AS EPI_NAME " +
                    " ,to_date(substring(t_health_epi_detail.record_date_time,0,11),'YYYY-MM-DD') AS EPI_DATE " +
                    " ,b_site.site_name AS EPI_NAME_HOS " +
                " from  " +
                    " b_site " +
                    " ,t_health_family INNER JOIN t_health_epi  " +
                    " ON t_health_family.t_health_family_id = t_health_epi.t_health_family_id " +
                    " INNER JOIN t_health_epi_detail " +
                    " ON t_health_epi.t_health_epi_id = t_health_epi_detail.t_health_epi_id " +
                    " INNER JOIN b_health_epi_group  " +
                    " ON t_health_epi_detail.b_health_epi_set_id = b_health_epi_group.b_health_epi_group_id " +
                    " INNER JOIN t_health_home " +
                    " ON t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                    " INNER JOIN t_health_village " +
                    " ON t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                    " INNER JOIN f_patient_prefix " +
                    " ON f_patient_prefix.f_patient_prefix_id = t_health_family.f_prefix_id " +
                " where " +
                    " t_health_epi_detail.health_epi_detail_active = '1' ";
              
                if(!selectVillage.equalsIgnoreCase("0"))
                {
                    SQL = SQL + " AND t_health_village.t_health_village_id = '"+selectVillage+"' ";
                }
                
                    SQL = SQL + " AND (((substring(' ' || age(to_date('"+endDate+"','YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ) IS NULL) " +
                                " OR  " +
                                " (to_number((substring(' ' || age(to_date('"+endDate+"','YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999') <= 5)) " +
                                // เพิ่มเงื่อนไขว่าถ้าเดือนเกิดและวันที่เกิดของเด็กที่ได้รับวัคซีนเมื่อลบกับวันที่ของจุดสิ้นสุดการ Query จะต้องมากกว่าเท่ากับ 0 sumo 25/10/2549
                                " AND(((to_number((substring(' ' || age(to_date('"+endDate+"','YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') ),'999') >= 0)) " +
                                " AND (to_number((substring(' ' || age(to_date('"+endDate+"','YYYY-MM-DD') " +
                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)day') ),'999') >= 0)) " +
                " order by " +
                    " t_health_village.village_moo " +
                    " ,t_health_village.village_name " +
                    " ,t_health_family.patient_name " +
                    " ,t_health_family.patient_last_name " +
                    " ,t_health_family.patient_birthday ";
        
        System.out.println(" queryEpiPCU List : " + SQL);
            
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
     * ดึงข้อมูล จำนวนเด็กที่ได้รับวัคซีน ในคลินิก wellbaby
     * @Author Ojika
     * @Date 31/03/2549
     **/
    public Vector queryAmountEpiWellbaby(String selectVillage, String EpiAgeGroupId, String startDate, String endDate)
    {   
        vc = null;
        
        SQL = " SELECT  query1.health_epi_group_description AS EPI_NAME" +
                    // เพิ่มการตรวจสอบว่าเด็กที่ได้รับวัคซีนอยู่ในช่วงอายุที่ถูกต้องหรือป่าว โดยเช็คจากเดือนและวันด้วย sumo 19/10/2549
                    " ,sum(case when (query1.epi_age_group_type_number = '1') " +
                                " AND   ((query1.patient_age_year > query1.epi_age_group_start  " +
                                    " and  query1.patient_age_year < query1.epi_age_group_end) " +
                                 " OR  ((query1.patient_age_year = query1.epi_age_group_start " +
                                    " and  query1.patient_age_year = query1.epi_age_group_end) " +
                                 " AND  (query1.patient_age_month = '0') and (query1.patient_age_day = '0'))) " +
                        " then 1 " +
                        " when (query1.epi_age_group_type_number = '2') " +
                        " then 1 " +
                        " when (query1.epi_age_group_type_number = '3') " +
                                " AND   ( query1.patient_age_year >= query1.epi_age_group_start) " +
                        " then 1 " +
                        " when (query1.epi_age_group_type_number = '4') " +
                                " AND   (query1.patient_age_year IS NULL or query1.patient_age_year <= query1.epi_age_group_end) " +
                        " then 1 " +
                        " else 0 " +
                     " end) AS COUNT_EPI " +  
                " FROM " +
                " (SELECT DISTINCT " +
                    " b_health_epi_group.health_epi_group_description AS health_epi_group_description " +
                    " ,t_health_family.t_health_family_id AS t_health_family_id " +
//                    " ,to_number((substring(' ' || age(to_date(substring(t_health_epi_detail.record_date_time,0,11),'YYYY-MM-DD') " +
//                                " , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),999) AS patient_age " + 
                    // เพิ่มการตรวจสอบว่าเด็กที่ได้รับวัคซีนอยู่ในช่วงอายุที่ถูกต้องหรือป่าว โดยเช็คจากเดือนและวันด้วย sumo 19/10/2549
                    ",((substring(' ' || age(to_date(substring(t_health_epi_detail.record_date_time,0,11),'YYYY-MM-DD') " +
                        ",to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') )) AS patient_age_year" +
                        ",((substring(' ' || age(to_date(substring(t_health_epi_detail.record_date_time,0,11),'YYYY-MM-DD') " +
                        ",to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)month') )) AS patient_age_month" +
                        ",((substring(' ' || age(to_date(substring(t_health_epi_detail.record_date_time,0,11),'YYYY-MM-DD') " +
                        ",to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)day') )) AS patient_age_day" +
                    " ,r_epi_age_group_type.epi_age_group_type_number AS epi_age_group_type_number " +
                    " ,r_epi_age_group.epi_age_group_start AS epi_age_group_start " +
                    " ,r_epi_age_group.epi_age_group_end AS epi_age_group_end " +
                " FROM  " +
                    " b_site " +
                    " ,r_epi_age_group INNER JOIN r_epi_age_group_type  " +
                    " ON (r_epi_age_group.r_epi_age_group_type_id = r_epi_age_group_type.r_epi_age_group_type_id  " +
                        " and r_epi_age_group.r_epi_age_group_id = '"+EpiAgeGroupId+"') " +
                    " ,t_health_family INNER JOIN t_health_epi  " +
                    " ON t_health_family.t_health_family_id = t_health_epi.t_health_family_id " +
                    " INNER JOIN t_health_epi_detail " +
                    " ON t_health_epi.t_health_epi_id = t_health_epi_detail.t_health_epi_id " +
                    " INNER JOIN b_health_epi_group  " +
                    " ON t_health_epi_detail.b_health_epi_set_id = b_health_epi_group.b_health_epi_group_id " +
                    " INNER JOIN t_health_home " +
                    " ON t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                    " INNER JOIN t_health_village " +
                    " ON t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                " WHERE  " +
                    " t_health_epi_detail.health_epi_detail_active = '1' ";
                
                if(!selectVillage.equalsIgnoreCase("0"))
                {
                    SQL = SQL + " AND t_health_village.t_health_village_id = '"+selectVillage+"' ";
                }
                
                   SQL = SQL + " AND t_health_epi_detail.b_health_epi_set_id  " +
                       " IN (select r_epi_age_group_item.b_health_epi_group_id from r_epi_age_group_item  " +
                           " where r_epi_age_group_item.r_epi_age_group_id = '"+EpiAgeGroupId+"')  " +
                    " AND substring(t_health_epi_detail.record_date_time,0,11) Between '"+startDate+"' AND '"+endDate+"' " +
                " ) AS query1 " +
                " GROUP BY " +
                    " query1.health_epi_group_description ";
        
        System.out.println(" queryEpiPCU Amount Wellbaby : " + SQL);
            
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
     * ดึงข้อมูล จำนวนผู้ที่ได้รับวัคซีน ในคลินิก TT
     * @Author Ojika
     * @Date 31/03/2549
     **/
    public Vector queryAmountEpiTT(String selectVillage, String startDate, String endDate)
    {   
        vc = null;
        
        SQL = " SELECT " +
                    " b_health_epi_group.health_epi_group_description AS EPI_NAME" +
                    " ,COUNT(*) AS COUNT_EPI " +
                " FROM " + 
                    " b_site " +
                    " ,t_health_family INNER JOIN t_health_epi " + 
                    " ON t_health_family.t_health_family_id = t_health_epi.t_health_family_id " +
                    " INNER JOIN t_health_epi_detail " +
                    " ON t_health_epi.t_health_epi_id = t_health_epi_detail.t_health_epi_id " +
                    " INNER JOIN b_health_epi_group " + 
                    " ON t_health_epi_detail.b_health_epi_set_id = b_health_epi_group.b_health_epi_group_id " +
                    " INNER JOIN t_health_home " +
                    " ON t_health_family.t_health_home_id = t_health_home.t_health_home_id " +
                    " INNER JOIN t_health_village " +
                    " ON t_health_home.t_health_village_id = t_health_village.t_health_village_id " +
                " WHERE " + 
                    " t_health_epi_detail.health_epi_detail_active = '1' " +
                    " AND t_health_family.f_sex_id = '2' ";
                
                if(!selectVillage.equalsIgnoreCase("0"))
                {
                    SQL = SQL + " AND t_health_village.t_health_village_id = '"+selectVillage+"' ";
                }
                
                    SQL = SQL + " AND t_health_epi_detail.b_health_epi_set_id IN (select r_epi_TT_item.b_health_epi_group_id from r_epi_TT_item) " +
                    " AND substring(t_health_epi_detail.record_date_time,0,11) Between '"+startDate+"' AND '"+endDate+"' " +
                " GROUP BY " +
                    " b_health_epi_group.health_epi_group_description " +
                    " ,t_health_family.patient_birthday ";
        
        System.out.println(" queryEpiPCU Amount TT : " + SQL);
            
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
