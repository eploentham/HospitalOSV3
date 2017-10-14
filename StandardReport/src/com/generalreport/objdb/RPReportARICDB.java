/*
 * RPReportARICDB.java
 *
 * Created on 11 ตุลาคม 2548, 11:52 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalreport.utility.Language;
import com.generalreport.object.*;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author tong(Padungrat)
 */
public class RPReportARICDB {
    
    public ConnectionInf theConnectionInf;
    Vector vc;
    OrderItem theOrderItem,ObjectOrderItem;
    Employee theEmployee;
    
    String SQL = "";
    ResultSet rs = null;
    ResultSetMetaData metadata;
    java.util.Vector vDrugDispense ;
    Vector vString;
    Vector vData;
    int language =1;
    public RPReportARICDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theOrderItem = new OrderItem();        
        theEmployee = new Employee();
        
        theEmployee.setInitDataFieldName();
        theOrderItem.setInitDataFieldName();
    }
    public Vector queryARICByDate(String startDate,String finishDate)
    {   vc  =null;
        try
        {
             SQL = " SELECT " +
                        " r_aric_subgroup.aric_subgroup_description as aric_name, " +
                        " CASE WHEN (sum(opd) > 0) " +
                        "       THEN sum(opd)      " +
                        "       ELSE 0   " +
                        " END AS OPD, " +
                        " CASE WHEN (sum(ipd) > 0)      " +
                        "       THEN sum(ipd) " +
                        "       ELSE 0  " +
                        " END AS IPD " +
                     " FROM  " +
                        " r_aric_subgroup LEFT JOIN " +
                        " (SELECT   " +
                        "   query1.t_visit_id,   " +
                        "   CASE WHEN (query1.f_visit_type_id = '0')   " +
                        "           THEN 1 " +
                        "           ELSE 0  " +
                        "   END AS opd, " +
                        "   CASE WHEN (query1.f_visit_type_id = '1') " +
                        "           THEN 1 " +
                        "           ELSE 0  " +
                        "   END AS ipd,  " +
                        "   CASE WHEN ((r_aric_group.aric_group_number = '1') AND (query1.checkDrug = '1'))    " +
                        "           THEN  'ARIC01' " +          
			"	WHEN ((r_aric_group.aric_group_number = '1') AND (query1.checkDrug = '0')) " +           
			"           THEN 'ARIC02' " +         
			"	WHEN ((r_aric_group.aric_group_number = '2') AND (query1.checkDrug = '1')) " +          
			"           THEN 'ARIC03' " +            
			"	WHEN ((r_aric_group.aric_group_number = '2') AND (query1.checkDrug = '0'))  " +            
			"           THEN 'ARIC04' " +  
			"   END AS aric_subgroup_number,  " +
			"   query1.item_antibiotic " +
                        " FROM   	" +
			"   r_aric_group  INNER JOIN " +
			"   (SELECT    " +
			"	t_visit.t_visit_id  AS t_visit_id,  " +
			"	t_diag_icd10.diag_icd10_number AS diag_icd10_number,   " +
			"	t_visit.f_visit_type_id  AS f_visit_type_id,  " +
			"	r_aric_disease_code.r_aric_group_id  AS r_aric_group_id,  " + 
			"	max(r_aric_antibiotic.b_item_id) AS item_antibiotic,   " +
			"	CASE WHEN (max(r_aric_antibiotic.b_item_id) > '1')    " +
			"			THEN '1'     " +
			"			ELSE '0' " +
			"	END AS checkDrug  " +
                        "   FROM   " +
			"       ( ( ( (t_visit INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id " +
                        "               AND t_patient.patient_birthday <> '') " +
                        "       INNER JOIN t_diag_icd10 ON t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn)      " +         
			"	LEFT JOIN t_order ON t_order.t_visit_id = t_visit.t_visit_id ) " +
			"	LEFT JOIN r_aric_antibiotic ON r_aric_antibiotic.b_item_id = t_order.b_item_id ), " +  
			"	r_aric_disease_code " +
			"   WHERE        " +
			"	(t_visit.f_visit_status_id <> '4') " +
			"	AND (t_diag_icd10.f_diag_icd10_type_id = '1') " +
                        "       AND t_diag_icd10.diag_icd10_active = '1' " +
			"	AND ( t_diag_icd10.diag_icd10_number between r_aric_disease_code.aric_disease_code_begin " +
			"			and r_aric_disease_code.aric_disease_code_end)    " +
                        "       AND (t_visit.visit_financial_discharge_time <> '') " +
			"	AND (substring(t_visit.visit_financial_discharge_time,0,11) Between '"+ startDate +"' And '"+ finishDate +"')  " +
			"	AND (t_visit.visit_patient_age <> '' And t_visit.visit_patient_age <> 'null' And t_visit.visit_patient_age IS NOT NULL)" +
                        "       AND ((substring(' ' || age(to_date(substring(t_visit.visit_financial_discharge_time,0,11) " +
                        "              ,'YYYY-MM-DD'), to_date(t_patient.patient_birthday,'YYYY-MM-DD')) from '(...)year')) IS NULL " +
                        "          OR to_number(substring(' ' || age(to_date(substring(t_visit.visit_financial_discharge_time,0,11)  " +
                        "              ,'YYYY-MM-DD'), to_date(t_patient.patient_birthday,'YYYY-MM-DD')) from '(...)year'),'999') < 6  " +
                        "         ) " + // ตรวจสอบอายุของเด็ก 
                        "   GROUP BY           " +
			"	t_visit.t_visit_id,        " + 
			"	t_diag_icd10.diag_icd10_number, " +
			"	t_visit.f_visit_type_id,   " +
			"	r_aric_disease_code.r_aric_group_id" +
			"   ORDER BY           " +
			"	t_visit.t_visit_id " +
			"   ) AS query1  ON r_aric_group.r_aric_group_id = query1.r_aric_group_id " +
                        " ) AS query2  ON query2.aric_subgroup_number = r_aric_subgroup.aric_subgroup_number " +
                    " GROUP BY " +  
                    "   r_aric_subgroup.aric_subgroup_description,r_aric_subgroup.aric_subgroup_number " +
                    " ORDER BY  " +
                    "   r_aric_subgroup.aric_subgroup_number ";
          
            System.out.println("SQL queryARICByDate : " + SQL);
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
    
    public Vector checkDataSetup()
    {   vc  =null;
        try
        {
            SQL = " select * from r_aric_antibiotic ";
            rs = theConnectionInf.eQuery(SQL);
            vc = getDataCheck(rs);
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
    
    private Vector getDataCheck(ResultSet resultset) throws Exception
    {
        vData = new Vector();
        //ตรวจสอบค่า resultset
        if(rs!= null)
        {
            while(rs.next())
            {   
                vData.add(rs.getString(1));                        
            }
        }
        return vData;
    }
}
