/*
 * RPAccident19CauseDB.java
 *
 * Created on 1 พฤศจิกายน 2548, 18:50 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.objdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalreport.utility.DateUtil;
import com.generalreport.utility.Language;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author nu_ojika
 */
public class RPAccident19CauseDB {
    
    /** Creates a new instance of RPAccident19CauseDB */
    public ConnectionInf theConnectionInf;
    Vector vc;
    
    String SQL = "";
    ResultSet rs = null;
    ResultSetMetaData metadata;
    java.util.Vector vDrugDispense ;
    Vector vString;
    Vector vData;
    int language = 1;
    
    public RPAccident19CauseDB(ConnectionInf connectionInf) {
        theConnectionInf = connectionInf;
    }
    
    public Vector queryAccident19CauseByDate(String startDate,String endDate)
    {   
        vc  =null;
        try
        {
            SQL = "SELECT  r_accident_group.accident_group_description AS accident_group  ," +
                    "case when (sum(query1.male)  > 0 ) then (sum(query1.male))  else 0 end AS male," +
                    "case when (sum(query1.female) > 0) then (sum(query1.female)) else 0  end AS female," +
                    "case when (sum(query1.nonspecify) > 0) then (sum(query1.nonspecify) ) else 0 end AS non_specify," +
                    "case when (sum(query1.all) > 0) then (sum(query1.all) ) else 0  end AS sumary," +
                    "case when (sum(query1.male_inprovince) > 0) then (sum(query1.male_inprovince)) else 0 end AS male_inprovince," +
                    "case when (sum(query1.female_inprovince) > 0) then (sum(query1.female_inprovince) ) else 0 end AS female_inprovince  ," +
                    "case when (sum(query1.nonspecify_inprovince) > 0) then (sum(query1.nonspecify_inprovince)) else 0 end AS  non_specify_inprovince  ," +
                    "case when (sum(query1.all_inprovince) > 0) then (sum(query1.all_inprovince) ) else 0 end AS sumary_inprovince  ," +
                    "case when (sum(query1.male_outprovince) > 0) then (sum(query1.male_outprovince)) else 0 end AS  male_outprovince  ," +
                    "case when (sum(query1.female_outprovince)> 0) then (sum(query1.female_outprovince) ) else 0 end AS female_outprovince ," +
                    "case when (sum(query1.nonspecify_outprovince) > 0) then (sum(query1.nonspecify_outprovince) ) else 0 end AS  non_specify_outprovince  ," +
                    "case when (sum(query1.all_outprovince) > 0 ) then (sum(query1.all_outprovince) ) else 0 end AS sumary_outprovince  ," +
                    "case when (sum(query1.male_death) > 0) then (sum(query1.male_death) ) else 0 end AS male_death  ," +
                    "case when (sum(query1.female_death) > 0) then (sum(query1.female_death) ) else 0 end AS female_death  ," +
                    "case when (sum(query1.nonspecify_death) > 0) then (sum(query1.nonspecify_death) ) else 0 end AS non_specify_death  ," +
                    "case when (sum(query1.all_death) > 0) then (sum(query1.all_death)) else 0 end AS sumary_death  " +
                    "FROM r_accident_group  " +
                    "LEFT JOIN (SELECT distinct t_visit.visit_vn," +
                    "r_accident_group_code.r_accident_group_id AS r_accident_group_id," +
                    "(CASE WHEN (t_patient.f_sex_id = '1') THEN 1 ELSE 0 END) AS male," +
                    "(CASE WHEN (t_patient.f_sex_id = '2') THEN 1 ELSE 0 END) AS female ," +
                    "(CASE WHEN ((t_patient.f_sex_id <> '2' ) " +
                    "AND (t_patient.f_sex_id <> '1' )) THEN 1 ELSE 0 END) AS nonspecify  , " +
                    "(1) AS all  ," +
                    "(CASE WHEN ((t_patient.f_sex_id = '1') " +
                    "AND (t_patient.patient_changwat IN (SELECT b_site.site_changwat FROM b_site))) THEN 1 ELSE 0 END)  AS male_inprovince  ," +
                    "(CASE  WHEN ((t_patient.f_sex_id = '2') " +
                    "AND (t_patient.patient_changwat IN (SELECT b_site.site_changwat FROM b_site))) THEN  1  ELSE 0  END)  AS female_inprovince  ," +
                    "(CASE  WHEN ((((t_patient.f_sex_id <> '2' ) " +
                    "AND (t_patient.f_sex_id <> '1' )))  " +
                    "AND (t_patient.patient_changwat " +
                    "IN (SELECT b_site.site_changwat FROM b_site)))  THEN  1  ELSE 0  END)  AS  nonspecify_inprovince  ," +
                    "(CASE  WHEN (t_patient.patient_changwat " +
                    "IN (SELECT b_site.site_changwat FROM b_site))  THEN  1  ELSE 0  END)  AS all_inprovince  ," +
                    "(CASE  WHEN ((t_patient.f_sex_id = '1') " +
                    "AND (t_patient.patient_changwat " +
                    "NOT IN (SELECT b_site.site_changwat FROM b_site)))  THEN  1  ELSE 0  END)  AS male_outprovince  ," +
                    "(CASE  WHEN ((t_patient.f_sex_id = '2') " +
                    "AND (t_patient.patient_changwat " +
                    "NOT IN (SELECT b_site.site_changwat FROM b_site)))  THEN  1  ELSE 0  END)  AS female_outprovince  ," +
                    "(CASE  WHEN (((t_patient.f_sex_id <> '2' ) AND (t_patient.f_sex_id <> '1' )) " +
                    "AND (t_patient.patient_changwat " +
                    "NOT IN (SELECT b_site.site_changwat FROM b_site)))  THEN  1  ELSE 0  END)  AS  nonspecify_outprovince  ," +
                    "(CASE  WHEN (t_patient.patient_changwat " +
                    "NOT IN (SELECT b_site.site_changwat FROM b_site))  THEN  1  ELSE 0  END)  AS all_outprovince  ," +
                    "(CASE  WHEN ((t_patient.f_sex_id = '1') " +
                    "AND   ((t_visit.f_visit_opd_discharge_status_id = '55')  " +
                    "OR (t_visit.f_visit_opd_discharge_status_id = '52')  ))  THEN  1  ELSE 0   END)  AS male_death  ," +
                    "(CASE  WHEN ((t_patient.f_sex_id = '2') " +
                    "AND   ((t_visit.f_visit_opd_discharge_status_id = '55')  " +
                    "OR (t_visit.f_visit_opd_discharge_status_id = '52')   ))  " +
                    "THEN  1  ELSE 0  END)  AS female_death  ," +
                    "(CASE  WHEN ((t_patient.f_sex_id <> '2' ) " +
                    "AND (t_patient.f_sex_id <> '1' )  " +
                    "AND   ((t_visit.f_visit_opd_discharge_status_id = '55')  " +
                    "OR (t_visit.f_visit_opd_discharge_status_id = '52')  ))  " +
                    "THEN  1  ELSE 0  END)  AS  nonspecify_death  ," +
                    "(CASE  WHEN ((t_visit.f_visit_opd_discharge_status_id = '55') " +
                    "OR (t_visit.f_visit_opd_discharge_status_id = '52') ) " +
                    "THEN  1  ELSE 0  END)  AS  all_death " +
                    "FROM  r_accident_group_code, " +
                    "(t_patient INNER JOIN (t_visit INNER JOIN t_diag_icd10 " +
                    "ON t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn " +
                    "AND ( t_visit.f_visit_status_id <> '4') " +
                    "AND (t_visit.f_visit_type_id = '0') " +
                    "AND (t_diag_icd10.diag_icd10_active = '1')) " +
                    "ON t_patient.t_patient_id = t_visit.t_patient_id)  " +
                    "WHERE (((substring(t_visit.visit_financial_discharge_time,1,10)) Between '" + startDate + "' And '" + endDate + "') " +
                    "AND (( t_diag_icd10.diag_icd10_number) Between r_accident_group_code.accident_group_code_begin " +
                    "And r_accident_group_code.accident_group_code_end)) " +
                    "GROUP BY " +
                    "r_accident_group_code.r_accident_group_id " +
                    ",t_visit.visit_vn " +
                    ",t_patient.f_sex_id " +
                    ",t_patient.patient_changwat " +
                    ",t_visit.f_visit_opd_discharge_status_id " +
                    "ORDER BY  r_accident_group_id) AS query1  " +
                    "ON r_accident_group.r_accident_group_id = query1.r_accident_group_id  " +
                    "GROUP BY  accident_group ";
            
            System.out.println(" SQL queryAccident19CauseByDate  : " + SQL);
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
                columnname[i] = Language.getTextBundle(columnname[i].toUpperCase(),language);
            
            }
            //ทำการให้ค่าของ field กับข้อมูล
            while(rs.next())
            {   rowdata = new String[column];
                for(int i = 0 ; i < column;i++)
                {
                    if(rs.getObject(i+1) != null)
                    {   rowdata[i] = rs.getString(i+1); 
                        if(metadata.getColumnTypeName(i+1).equalsIgnoreCase("date"))
                        { //  System.out.println("Column Name : Date :"  + rowdata[i].trim().length() + ":") ; 
                                
                            if(rowdata[i].trim().length() != 0)
                            {//   System.out.println("Convert Date");
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
