/*
 * RP505DB.java
 *
 * Created on 7 กันยายน 2548, 20:15 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.objdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportcenter.objdb.DBPersist;
import com.standardReport.object.RP505Group;
import com.standardReport.object.RP505Name;
import com.standardReport.subject.StandardDB;
import com.standardReport.utility.*;
import java.util.*;
import java.sql.*;
import java.io.*;
/**
 *
 * @author nu_ojika
 */
public class RP505DB extends DBPersist implements StandardDB{
    
    /**
     * Creates a new instance of RP505DB 
     */
    public ConnectionInf theConnectionInf;
    RP505Group theRP505Group,ObjectRP505Group;
    RP505Name theRP505Name,ObjectRP505Name;
    ResultSet rs = null;
    Vector vRP505Group;
    Vector vRP505Name;
    String description = "";
    public FileOutputStream fileOutput;
    
    /**
     * Creates a new instance of RP505DB 
     */
    public RP505DB(ConnectionInf c)
    {
        theConnectionInf = c;
        theRP505Group = new RP505Group();
        theRP505Name = new RP505Name();
        
        theRP505Group.setInitDataFieldName();
        theRP505Name.setInitDataFieldName();
    }

    public int deleteByKeyID(String key_id)
    {
        return 0;
    }

    public int insertData(Object object)
    {
        return 0;
    }

    public Object selectByKeyID(String key_id)
    {
        return null;
    }

    public int updateByKeyID(String key_id)
    {
        return 0;
    }
    
     /**
     *  ใช้ในการ Convert Object เป็น String โดยที่รับ ค่า true หรือ false เพื่อสร้างหัวคอลัมน์หรือไม่
     *  ถ้าเป็น true จะให้แสดงหัวคอลัมน์ และใช้ Tab เป็นตัวคั่น 
     *  ถ้าเป็น false จะไม่แสดงไม่หัวคอลัมน์ และใช้ Pipe เป็นตัวคั่น
     *  @param vObject เป็น Vector ของ Obeject ชือ RP505Group
     *  @param isGetColumnName เป็น Boolean ถ้าเป็น true จะให้แสดงหัวคอลัมน์ ถ้าเป็น false จะไม่แสดงไม่หัวคอลัมน์ 
     *  @param obj เป็น String ที่ใช้เพื่อตรวจสอบว่า Object ใด ที่ถูกเก็บอยู่ใน Vector 
     *  @return เป็น String ของ Sql ที่มีการแปลงค่าเรียบร้อยแล้ว
     */
    public String convertToString(Vector vObject,boolean isGetColumnName,String obj)
    {
        System.out.println("In method convert To String  size => "+vObject.size()+" Boolean "+isGetColumnName);
        StringBuffer sql = new StringBuffer();
        String separator = Constant.PIPE;
        /**/
        if(vObject != null)
        {
            //รายกลุ่ม
            if(obj.equals(((Report)Constant.Report.get(String.valueOf("2"))).ENG_NAME))//Constant.REPORT_NAME_EN[1]))
            {
                RP505Group p = null;
                if(isGetColumnName)
                {
                    separator = Constant.TAB;
                    sql.append("code_group"+separator
                            +"group_detail_thai"+separator
                            +"group_detail_eng"+separator
                            +"male"+separator
                            +"female"+separator
                            +"non_spec"+separator
                            +"total"
                            +Constant.NEWLINE);
                }
                for(int i=0;i<vObject.size();i++)
                {
                    p = (RP505Group)vObject.elementAt(i);
                    sql.append(p.group_rp505_number+separator
                            +p.group_rp505_description_th+separator
                            +p.group_rp505_description_en+separator
                            +p.group_rp505_male+separator
                            +p.group_rp505_female+separator
                            +p.group_rp505_non_spec+separator
                            +p.group_rp505_total
                            +Constant.NEWLINE);
                }
            }
            else if(obj.equals(((Report)Constant.Report.get(String.valueOf("5"))).ENG_NAME))   //Constant.REPORT_NAME_EN[4]))
            {   //รายโรค
            
                RP505Name p = null;
                if(isGetColumnName)
                {
                    separator = Constant.TAB;
                    sql.append("ICD_Code"+separator
                            +"ICD_Name"+separator
                            +"Male"+separator
                            +"Female"+separator
                            +"non_spec"+separator
                            +"Total"
                            +Constant.NEWLINE);
                }
                for(int i=0;i<vObject.size();i++)
                {
                    p = (RP505Name)vObject.elementAt(i);
                    sql.append(p.name_rp505_ICD_code+separator
                            +p.name_rp505_ICD_name+separator
                            +p.name_rp505_male+separator
                            +p.name_rp505_female+separator
                            +p.name_rp505_non_spec+separator 
                            +p.name_rp505_total
                            +Constant.NEWLINE);
                }
            }
        }
        return sql.toString();
    }
    
    public Vector selectNameByDate(String startDate, String endDate) throws Exception
    {
        /*String sql = " SELECT " +
                " b_icd10.icd10_number AS code " +
                " , b_icd10.icd10_description AS name " +
                " , SUM(CASE when t_patient.f_sex_id = '1' THEN 1 ELSE 0 END) AS male " +
                " , SUM(CASE when t_patient.f_sex_id = '2' THEN 1 ELSE 0 END) AS female " +
                " , SUM(CASE when (t_patient.f_sex_id <> '2' and t_patient.f_sex_id <> '1') THEN 1 ELSE 0 END) AS non_spec " +
                " , COUNT(t_diag_icd10.t_diag_icd10_id) AS total " +
         " FROM " +
                " t_patient " +
                " , t_visit " +
                " , t_diag_icd10 " +
                " , b_icd10 " +
                " , f_group_rp505 " +
                " , r_rp505_disease_code " +
        " WHERE " +
                " t_patient.t_patient_id = t_visit.t_patient_id " +
                " AND t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn " +
                " AND t_diag_icd10.diag_icd10_number = b_icd10.icd10_number " +
                " AND t_visit.f_visit_type_id = '1' " +
                " AND t_visit.f_visit_status_id <> '4' " +
                " AND f_group_rp505.group_rp505_number <> '99' " +
                " AND t_diag_icd10.diag_icd10_active = '1' " +
                " AND trim(f_group_rp505.f_group_rp505_id) = trim(r_rp505_disease_code.f_group_rp505_id) " +
                " AND ( t_diag_icd10.diag_icd10_number between r_rp505_disease_code.rp505_disease_code_begin and r_rp505_disease_code.rp505_disease_code_end) " +
                " AND (SUBSTRING(t_visit.visit_financial_discharge_time from 1 for 10) between '" + startDate + "' and '" + endDate +"') " +
     " GROUP BY " +
                " b_icd10.icd10_number " +
                " , b_icd10.icd10_description " +
     " ORDER BY " +
                " b_icd10.icd10_number";
        System.out.println(" 505 รายโรค : "+ sql);
        Vector v = eNameQuery(sql);
         */
        String sql = IOStream.readInputDefault("config/rp_standard/standard_505_name.sql");
        PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        Vector v = eNameQuery(pm.executeQuery());
        sql = null;
        
        if(v.size()==0)
        {
            return null;
        }
        else
        {
            return v;
        }
    }
    
    public Vector selectGroupByDate(String startDate, String endDate) throws Exception
    {
        /* String sql = " SELECT " +
	" f_group_rp505.group_rp505_number AS code" +
        " , f_group_rp505.group_rp505_description_th AS description_th" +
        " , f_group_rp505.group_rp505_description_en AS description_en" +
        " , case when (temp.male > 0) then temp.male else  0 end AS male" +
        " , case when (temp.female > 0) then temp.female else  0 end AS female" +
        " , case when (temp.non_spec > 0) then temp.non_spec else  0 end AS non_spec" +
        " , case when (temp.total > 0) then temp.total else 0 end AS total" +
        " FROM " +
	" ( f_group_rp505 LEFT JOIN" +
	" ( SELECT "+
                	" f_group_rp505.group_rp505_number AS code" +
                        " , f_group_rp505.group_rp505_description_th AS des" +
                        " , f_group_rp505.group_rp505_description_en AS des_en" +
                        " , SUM(CASE when t_patient.f_sex_id = '1' THEN 1 ELSE 0 END) AS male" +
                        " , SUM(CASE when t_patient.f_sex_id = '2' THEN 1 ELSE 0 END) AS female" +
                        " , SUM(CASE when (t_patient.f_sex_id <> '2' and t_patient.f_sex_id <> '2') THEN 1 ELSE 0 END) AS non_spec" +
                        " , COUNT(t_diag_icd10.t_diag_icd10_id) AS total" +
		" FROM " +
			" f_group_rp505" +
                        " , r_rp505_disease_code" +
                        " , t_patient" +
                        " , t_visit" +
                        " , t_diag_icd10" +
		" WHERE " +
			" t_patient.patient_hn = t_visit.visit_hn" +
                        " AND t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn" +
			" AND t_visit.f_visit_type_id = '1'" +
			" AND t_visit.f_visit_status_id <> '4' " +
                        " AND t_diag_icd10.diag_icd10_active = '1' " +			
			" AND trim(f_group_rp505.f_group_rp505_id) = trim(r_rp505_disease_code.f_group_rp505_id)" +
			" AND ( t_diag_icd10.diag_icd10_number between r_rp505_disease_code.rp505_disease_code_begin and r_rp505_disease_code.rp505_disease_code_end)" +
			" AND (SUBSTRING(t_visit.visit_financial_discharge_time from 1 for 10) between '" + startDate + "' and '"+ endDate +"')" +
		" GROUP BY " +
			" f_group_rp505.group_rp505_number" +
                        " , f_group_rp505.group_rp505_description_th " +
                        " , f_group_rp505.group_rp505_description_en ) AS temp ON temp.code = f_group_rp505.group_rp505_number)" +
    " WHERE " +
        " f_group_rp505.group_rp505_number <> '99' " + 
    " ORDER BY " +                
	" to_number(f_group_rp505.group_rp505_number,'99')";
        
        System.out.println(" 505 รายกลุ่ม : "+ sql);
        System.out.println(sql);
        Vector v = eGroupQuery(sql);
         */
        String sql = IOStream.readInputDefault("config/rp_standard/standard_505_group.sql");
        PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        Vector v = eGroupQuery(pm.executeQuery());
        sql = null;
        if(v.size()==0)
        {
            return null;
        }
        else
        {
            return v;
        }
    }
    
     public Vector eGroupQuery(ResultSet rs)
    {
    
        try{
        //System.out.println("SQL : "+ sql);
        //rs = theConnectionInf.eQuery(sql);
        vRP505Group= new Vector();
        ObjectRP505Group = new RP505Group();
            while(rs.next()) 
            {
                ObjectRP505Group = new RP505Group();
                ObjectRP505Group.setInitData();
                
                ObjectRP505Group.group_rp505_number = rs.getString(1);          
                ObjectRP505Group.group_rp505_description_th = rs.getString(2); 
                ObjectRP505Group.group_rp505_description_en = rs.getString(3); 
                ObjectRP505Group.group_rp505_male = rs.getString(4); 
                ObjectRP505Group.group_rp505_female = rs.getString(5); 
                ObjectRP505Group.group_rp505_non_spec = rs.getString(6); 
                ObjectRP505Group.group_rp505_total = rs.getString(7); 
                
                vRP505Group.add(ObjectRP505Group);
                ObjectRP505Group = null;
            }            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
        return vRP505Group;
    } 
     
     public Vector eNameQuery(ResultSet rs)
     {
         
         try
         {
         //System.out.println("SQL : "+ sql);
         //rs = theConnectionInf.eQuery(sql);
         vRP505Name= new Vector();
         ObjectRP505Name = new RP505Name();
             while(rs.next())
             {
                 ObjectRP505Name = new RP505Name();
                 ObjectRP505Name.setInitData();
                 
                 ObjectRP505Name.name_rp505_ICD_code = rs.getString(1);
                 ObjectRP505Name.name_rp505_ICD_name = rs.getString(2);
                 ObjectRP505Name.name_rp505_male = rs.getString(3);
                 ObjectRP505Name.name_rp505_female = rs.getString(4);
                 ObjectRP505Name.name_rp505_non_spec = rs.getString(5);
                 ObjectRP505Name.name_rp505_total = rs.getString(6);
                 
                 vRP505Name.add(ObjectRP505Name);
                 ObjectRP505Name = null;
             }
             rs.close();
         }
         catch(Exception ex)
         {
             ex.printStackTrace();
         }
         return vRP505Name;
     }

}
