/*
 * RP506DB.java
 *
 * Created on 8 กันยายน 2548, 10:20 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.objdb;

import com.hospital_os.usecase.connection.ConnectionInf; 
import com.reportcenter.objdb.DBPersist;
import com.standardReport.object.RP506Group;
import com.standardReport.object.RP506Name;
import com.standardReport.subject.StandardDB;
import com.standardReport.utility.*;
import java.util.*;
import java.sql.*;
import java.io.*;
/**
 *
 * @author americus
 */
public class RP506DB extends DBPersist implements StandardDB
{
    public ConnectionInf theConnectionInf;
    RP506Group theRP506Group,ObjectRP506Group;
    RP506Name theRP506Name,ObjectRP506Name;
    ResultSet rs = null;
    Vector vRP506Group;
    Vector vRP506Name ;
    String description = "";
    public FileOutputStream fileOutput;
    
    /**
     * Creates a new instance of RP506DB 
     */
    public RP506DB(ConnectionInf c)
    {
        theConnectionInf = c;
        theRP506Group = new RP506Group();
        theRP506Name = new RP506Name();
        
        theRP506Group.setInitDataFieldName();
        theRP506Name.setInitDataFieldName();
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
     *  @param vObject เป็น Vector ของ Obeject ชือ RP506Group
     *  @param isGetColumnName เป็น Boolean ถ้าเป็น true จะให้แสดงหัวคอลัมน์ ถ้าเป็น false จะไม่แสดงไม่หัวคอลัมน์
     *  @param obj เป็น String ที่ใช้เพื่อตรวจสอบว่า Object ใด ที่ถูกเก็บอยู่ใน Vector 
     *  @return เป็น String ของ Sql ที่มีการแปลงค่าเรียบร้อยแล้ว
     */
    public String convertToString(Vector vObject,boolean isGetColumnName,String obj)
    {
        System.out.println("In method convert To String  size => "+vObject.size()+" Boolean "+isGetColumnName);
        StringBuffer sql = new StringBuffer();
        String separator = Constant.PIPE;
       
        if(vObject != null)
        {
            
            //รายกลุ่ม
            if(obj.equals(((Report)Constant.Report.get(String.valueOf("3"))).ENG_NAME))//Constant.REPORT_NAME_EN[2]))
            {
                RP506Group p = null;
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
                    p = (RP506Group)vObject.elementAt(i);
                    sql.append(p.group_rp506_number+separator
                            +p.group_rp506_description_th+separator
                            +p.group_rp506_description_en+separator
                            +p.group_rp506_male+separator
                            +p.group_rp506_female+separator
                            +p.group_rp506_non_spec+separator
                            +p.group_rp506_total
                            +Constant.NEWLINE);
                }
            }
            //รายโรค
            else if(obj.equals(((Report)Constant.Report.get(String.valueOf("6"))).ENG_NAME))//Constant.REPORT_NAME_EN[5]))
            {
                RP506Name p = null;
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
                    p = (RP506Name)vObject.elementAt(i);
                    sql.append(p.name_rp506_ICD_code+separator
                            +p.name_rp506_ICD_name+separator
                            +p.name_rp506_male+separator
                            +p.name_rp506_female+separator
                            +p.name_rp506_non_spec+separator
                            +p.name_rp506_total
                            +Constant.NEWLINE);
                }
            }
        }
        return sql.toString();
    }
 public Vector selectNameByDate(String startDate, String endDate) throws Exception
    {
          /*  String sql = " SELECT " +
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
            " , f_group_rp506 " +
            " , b_group_icd10 " +
    " WHERE " +
            " t_patient.t_patient_id = t_visit.t_patient_id " +
            " AND t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn " +
            " AND t_diag_icd10.diag_icd10_number = b_icd10.icd10_number " +
            " AND t_visit.f_visit_status_id <> '4' " +
            " AND f_group_rp506.group_rp506_number <> '99' " +
            " AND t_diag_icd10.diag_icd10_active = '1' " +
            " AND SUBSTRING(t_diag_icd10.diag_icd10_number,0,4) = b_group_icd10.group_icd10_number " +
            " AND f_group_rp506.group_rp506_number = b_group_icd10.group_icd10_group_rp506 " +
            " AND (SUBSTRING(t_visit.visit_financial_discharge_time from 1 for 10) between '" + startDate + "' and '" + endDate + "') " +
            " GROUP BY " +
            " b_icd10.icd10_number " +
            " , b_icd10.icd10_description " +
 " ORDER BY " +
            " b_icd10.icd10_number";

          System.out.println(" 506 รายโรค : "+ sql);
          Vector v = eNameQuery(sql);
           */ 
           String sql = IOStream.readInputDefault("config/rp_standard/standard_506_name.sql");
           System.out.println("theConnectionInf");
           System.out.println(theConnectionInf==null);
           System.out.println("getConnection");
           System.out.println(theConnectionInf.getConnection()==null);
           System.out.println("sql");
           System.out.println(sql==null);
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
	" f_group_rp506.group_rp506_number AS code" +
        " , f_group_rp506.group_rp506_description_th AS description_th" +
        " , f_group_rp506.group_rp506_description_en AS description_en" +
        " , case when (temp.male > 0) then temp.male else  0 end AS male" +
        " , case when (temp.female > 0) then temp.female else  0 end AS female" +
        " , case when (temp.non_spec > 0) then temp.non_spec else  0 end AS non_spec" +
        " , case when (temp.total > 0) then temp.total else 0 end AS total" +
        " FROM " +
	" ( f_group_rp506 LEFT JOIN" +
	" ( SELECT "+
                	" f_group_rp506.group_rp506_number AS code" +
                        " , f_group_rp506.group_rp506_description_th AS des" +
                        " , f_group_rp506.group_rp506_description_en AS des_en" +
                        " , SUM(CASE when t_patient.f_sex_id = '1' THEN 1 ELSE 0 END) AS male" +
                        " , SUM(CASE when t_patient.f_sex_id = '2' THEN 1 ELSE 0 END) AS female" +
                        " , SUM(CASE when (t_patient.f_sex_id <> '2' and t_patient.f_sex_id <> '1') THEN 1 ELSE 0 END) AS non_spec" +
                        " , COUNT(t_diag_icd10.t_diag_icd10_id) AS total" +
		" FROM " +
			" f_group_rp506" +
                        " , b_group_icd10" +
                        " , t_patient" +
                        " , t_visit" +
                        " , t_diag_icd10" +
		" WHERE " +
			" t_patient.patient_hn = t_visit.visit_hn " +
                        " AND t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn " +
			" AND t_visit.f_visit_status_id <> '4' " +
                        " AND t_diag_icd10.diag_icd10_active = '1' " +			
                        " AND SUBSTRING(t_diag_icd10.diag_icd10_number,0,4) = b_group_icd10.group_icd10_number" +
			" AND f_group_rp506.group_rp506_number = b_group_icd10.group_icd10_group_rp506" +
			" AND (SUBSTRING(t_visit.visit_financial_discharge_time from 1 for 10) between '" + startDate + "' and '"+ endDate +"')" +
		" GROUP BY " +
			" f_group_rp506.group_rp506_number " +
                        " , f_group_rp506.group_rp506_description_th " +
                        " , f_group_rp506.group_rp506_description_en ) AS temp " +
            " ON temp.code = f_group_rp506.group_rp506_number)" +
    " WHERE  " +
        " f_group_rp506.group_rp506_number <> '99' " + 
    " ORDER BY " +
	" to_number(f_group_rp506.group_rp506_number,'99')";
        
        System.out.println(" 506 รายกลุ่ม : "+ sql);
        Vector v = eGroupQuery(sql);
        */
        String sql = IOStream.readInputDefault("config/rp_standard/standard_506_group.sql");
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
        
        try
        {
        //System.out.println("SQL : "+ sql);
       // rs = theConnectionInf.eQuery(sql);
        vRP506Group= new Vector();
        ObjectRP506Group = new RP506Group();
            while(rs.next())
            {
                ObjectRP506Group = new RP506Group();
                ObjectRP506Group.setInitData();
                
                ObjectRP506Group.group_rp506_number = rs.getString(1);
                ObjectRP506Group.group_rp506_description_th = rs.getString(2);
                ObjectRP506Group.group_rp506_description_en = rs.getString(3);
                ObjectRP506Group.group_rp506_male = rs.getString(4);
                ObjectRP506Group.group_rp506_female = rs.getString(5);
                ObjectRP506Group.group_rp506_non_spec = rs.getString(6);
                ObjectRP506Group.group_rp506_total = rs.getString(7);
                
                vRP506Group.add(ObjectRP506Group);
                ObjectRP506Group = null;
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vRP506Group;
    }
    
    public Vector eNameQuery(ResultSet rs)
    {
        
        try
        {
        //System.out.println("SQL : "+ sql);
        //rs = theConnectionInf.eQuery(sql);
        vRP506Name= new Vector();
        ObjectRP506Name = new RP506Name();
            while(rs.next())
            {
                ObjectRP506Name = new RP506Name();
                ObjectRP506Name.setInitData();
                
                ObjectRP506Name.name_rp506_ICD_code = rs.getString(1);
                ObjectRP506Name.name_rp506_ICD_name = rs.getString(2);
                ObjectRP506Name.name_rp506_male = rs.getString(3);
                ObjectRP506Name.name_rp506_female = rs.getString(4);
                ObjectRP506Name.name_rp506_non_spec = rs.getString(5);
                ObjectRP506Name.name_rp506_total = rs.getString(6);
                
                vRP506Name.add(ObjectRP506Name);
                ObjectRP506Name = null;
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vRP506Name;
    }
    
}
