/*
 * RP505INClinicDB.java
 *
 * Created on 17 มิถุนายน 2549, 9:40 น.
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
public class RP505INClinicDB
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
    /** Creates a new instance of RP505INClinicDB */
    public RP505INClinicDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    /**
     *รายงาน 505 แยกตามคลินิก
     *@param startdate เป็น String ที่เก็บวันที่เริ่มต้นในการดึงข้อมูล
     *@param enddate เป็น String ที่เก็บวันที่สิ้นสุดในการดึงข้อมูล
     *@return Vector ของข้อมูลผู้ป่วย 505
     *@Author pu
     *@Date 17/06/2006
     */
    public Vector query505INClinicByDate(String startdate,String enddate)
    {
        try
        {
            SQL = "SELECT " + 
                            "b_visit_clinic.visit_clinic_description AS clinic " + 
                            ",count(t_diag_icd10.diag_icd10_number) AS count_ipd " + 
                    "FROM " +   
                            "(t_visit INNER JOIN t_diag_icd10  " + 
                            "ON t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn " + 
                            "INNER JOIN b_visit_clinic " + 
                            "ON t_diag_icd10.b_visit_clinic_id = b_visit_clinic.b_visit_clinic_id) " + 
                            ",f_group_rp505 , r_rp505_disease_code " + 					
                    "WHERE " +   
                            "t_visit.f_visit_type_id = '1'  " + 
                            "AND t_visit.f_visit_status_id <> '4' " +  
                            "AND t_diag_icd10.diag_icd10_active = '1' " +  
                            "AND trim(f_group_rp505.f_group_rp505_id) = trim(r_rp505_disease_code.f_group_rp505_id) " +  
                            "AND ( t_diag_icd10.diag_icd10_number " +  
                                    "between r_rp505_disease_code.rp505_disease_code_begin " +  
                                            "and r_rp505_disease_code.rp505_disease_code_end) " +  
                            "AND (SUBSTRING(t_visit.visit_financial_discharge_time from 1 for 10) " +  
                                    "between '"+ startdate +"' and '"+ enddate +"') " +  
                    "GROUP BY " + 
                            "b_visit_clinic.visit_clinic_description";
            
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
