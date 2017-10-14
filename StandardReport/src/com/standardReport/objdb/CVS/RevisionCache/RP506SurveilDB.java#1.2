/*
 * RP506SurveilDB.java
 *
 * Created on 8 กันยายน 2548, 12:12 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.objdb;

import com.hospital_os.utility.Gutil;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.standardReport.utility.Language;
import com.standardReport.subject.StandardDB;
import com.standardReport.object.RP506Surveil;
import com.standardReport.utility.*;
import java.util.*;
import java.sql.*;
import java.io.FileOutputStream;


/**
 *
 * @author americus
 */
public class RP506SurveilDB implements StandardDB
{
    public ConnectionInf theConnectionInf;
    RP506Surveil theRP506Surveil,ObjectRP506Surveil;
    ResultSet rs = null;
    Vector vRP506FollowPateint ;
    String description = "";
    Vector vc;
    ResultSetMetaData metadata;
    Vector vString;
    Vector vData;
    private int columnsize;
    private int str;
    private FileWriter fileWriter;

    Vector vcDataQuery;
    String[] headColumn;
    String[] dataColumn;
   
    public FileOutputStream fileOutput;
    /**
     * Creates a new instance of RP506SurveilDB 
     */
    public RP506SurveilDB(ConnectionInf c)
    {
       theConnectionInf = c;
       theRP506Surveil = new RP506Surveil();
       theRP506Surveil.setInitDataFieldName();
    }
/**
     *  ใช้ในการ Convert Object เป็น String โดยที่รับ ค่า true หรือ false เพื่อสร้างหัวคอลัมน์หรือไม่
     *  ถ้าเป็น true จะให้แสดงหัวคอลัมน์ และใช้ Tab เป็นตัวคั่น
     *  ถ้าเป็น false จะไม่แสดงไม่หัวคอลัมน์ และใช้ Pipe เป็นตัวคั่น
     *  @param vObject เป็น Vector ของ Obeject ชือ RP506Surveil
     *  @param isGetColumnName เป็น Boolean ถ้าเป็น true จะให้แสดงหัวคอลัมน์ ถ้าเป็น false จะไม่แสดงไม่หัวคอลัมน์
     *  @return เป็น String ของ Sql ที่มีการแปลงค่าเรียบร้อยแล้ว
     */
    /*public String convertToString(Vector vObject,boolean isGetColumnName)
    {
        System.out.println("In method convert To String  size => "+vObject.size()+" Boolean "+isGetColumnName);
        StringBuffer sql = new StringBuffer();
        String separator = Constant.PIPE;

        if(vObject != null)
        {
            RP506Surveil p = null;
            if(isGetColumnName)
            {
                separator = Constant.TAB;
                sql.append("HN"+separator
                        +"Prefix"+separator// new 04 มค 2548
                        +"FName"+separator
                        +"LName"+separator
                        +"Sex"+separator// new 04 มค 2548
                        +"ICD_CODE"+separator
                        +"Start_Sick"+separator
                        +"Status_Treat"+separator
                        +"Address"+separator
                        // new 04 มค 2548
                        +"date_visit"+separator
                        +"age_year"+separator
                        +"age_month"+separator
                        +"age_day"+separator
                        +"vn_an"+separator
                        +"father_name"+separator
                        +"mother_name"+separator
                        +"doctor_treat"+separator
                        +"code506"+separator
                        +"visit_dx"+separator
                        +"occupation"+separator
                        +"nation"+separator
                        +"marriage"+separator
                        +"visit_type"
                        +Constant.NEWLINE);
            }
            for(int i=0;i<vObject.size();i++)
            {
                p = (RP506Surveil)vObject.elementAt(i);
                sql.append(p.rp506follow_hn+separator
                        +p.rp506follow_prefix+separator // new 04 มค 2548                        
                        +p.rp506follow_fname+separator
                        +p.rp506follow_lname+separator
                        +p.rp506follow_sex+separator // new 04 มค 2548
                        +p.rp506follow_icd_code+separator
                        +p.rp506follow_startdate_treat+separator
                        +p.rp506follow_status_treat+separator
                        +p.rp506follow_address+separator
                        // new 04 มค 2548
                        +p.rp506follow_date_visit+separator
                        +p.rp506follow_age_year+separator
                        +p.rp506follow_age_month+separator
                        +p.rp506follow_age_day+separator
                        +p.rp506follow_vn_an+separator
                        +p.rp506follow_father_name+separator
                        +p.rp506follow_mother_name+separator
                        +p.rp506follow_salf_doctor+separator
                        +p.rp506follow_code506+separator
                        +p.rp506follow_visit_dx+separator
                        +p.rp506follow_occupation+separator
                        +p.rp506follow_nation+separator
                        +p.rp506follow_marriage+separator
                        +p.rp506follow_visit_type
                        +Constant.NEWLINE);
            }
        }
        return sql.toString();
    }*/   
    /**
     *  ใช้ในการ Convert Object เป็น String โดยที่รับ ค่า true หรือ false เพื่อสร้างหัวคอลัมน์หรือไม่
     *  ถ้าเป็น true จะให้แสดงหัวคอลัมน์ และใช้ Tab เป็นตัวคั่น
     *  ถ้าเป็น false จะไม่แสดงไม่หัวคอลัมน์ และใช้ Pipe เป็นตัวคั่น
     *  @param vData เป็นข้อมูลที่ได้จากการ query
     *  @param isGetColumnName เป็น Boolean ถ้าเป็น true จะให้แสดงหัวคอลัมน์ ถ้าเป็น false จะไม่แสดงไม่หัวคอลัมน์
     *  @return เป็น String ของ Sql ที่มีการแปลงค่าเรียบร้อยแล้ว
     */
    public String convertToString(Vector vData,boolean isGetColumnName)  throws Exception
    {
        StringBuffer sql = new StringBuffer();
        String separator = Constant.PIPE;
        headColumn = new String[] {""};
        //dataColumn = new String[] {""};
        vcDataQuery = null;
        int size =0;
        if(vData != null)
        {
            headColumn = (String[])vData.get(0);
            vcDataQuery = (Vector)vData.get(1);
            size = vcDataQuery.size();
            System.out.println("--- vcDataQuery : " + vcDataQuery.size());
            
            if(isGetColumnName)
            {
                String datahead = "";
                int length = headColumn.length;
                separator = Constant.TAB;
                for(int i=0;i<length;i++)
                {
                    if(i == (length-1))
                    {
                        datahead = datahead + headColumn[i];
                        System.out.println("--- headColumn[i] : " + headColumn[i]);
                    }
                    else
                    {
                        datahead = datahead + headColumn[i] + separator;
                        System.out.println("--- headColumn[i] : " + headColumn[i]);
                    }
                }
                sql.append(datahead + Constant.NEWLINE);
                datahead = null;
            }
            
            String data = "";
            for(int i=0;i<size;i++)
            {
                String[] dataColumn = (String[])vcDataQuery.get(i);
                System.out.println("--- dataColumn : " + dataColumn.length);
                int length = dataColumn.length;
                data = "";
                for(int j=0;j<length;j++)
                {
                    if(j == (length-1))
                    {
                        data = data + dataColumn[j];
                        System.out.println("---if-- dataColumn[j] : " + dataColumn[j]);
                    }
                    else
                    {
                        data = data + dataColumn[j] + separator;
                        System.out.println("---else-- dataColumn[j] : " + dataColumn[j]);
                    }
                }
                sql.append(data + Constant.NEWLINE);
            }
            data = null;
        }
        return sql.toString();
    }
    
    /**
     *ข้อมูลติดตามผู้ป่วย506
     *@param startDate วันที่เริ่มต้นในการดึงข้อมูล
     *@param endDate วันที่สิ้นสุดในการดึงข้อมูล
     *@param treatType สถานะการรักษา
     *@update 26/10/2549
     *@by pu
     */
     public Vector selectByDate(String startDate, String endDate,String treatType) throws Exception
    {
         Vector vc = new Vector();
         /*StringBuffer strSurveil = new StringBuffer();
         strSurveil.append("select t_patient.patient_hn  as HN ");
         strSurveil.append(",f_patient_prefix.patient_prefix_description as prefix ");
         strSurveil.append(",t_patient.patient_firstname as firstname ");
         strSurveil.append(",t_patient.patient_lastname as lastname  ");
         strSurveil.append(",CASE WHEN (t_patient.f_sex_id = '1')       THEN 'Male'  ");
         strSurveil.append("WHEN (t_patient.f_sex_id = '2')        THEN 'Female'  ");
         strSurveil.append("WHEN ((t_patient.f_sex_id <> '1') and (t_patient.f_sex_id <> '2'))        THEN 'Non-Spec'  END as sex ");
         strSurveil.append(",t_surveil.surveil_icd10_number as icd10 ");
         strSurveil.append(",SUBSTRING(t_surveil.surveil_sick_date,1,10) AS  start_date  ");
         strSurveil.append(",f_chronic_discharge_status.chronic_discharge_status_description as status_description ");
         strSurveil.append(",t_patient.patient_house || ' หมู่ที่' || t_patient.patient_moo || ' ต.' || tambol.address_description || ' อ.' || amphur.address_description || ' จ.' || f_address.address_description     as address   ");
      
         strSurveil.append(",substring(t_visit.visit_begin_visit_time,1,10) as visit_time  ");
         strSurveil.append(",CASE WHEN ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)years') ) > 1)   ");
         strSurveil.append("THEN  substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)years')    ");
         strSurveil.append("WHEN  ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)year') )  IS NOT NULL)  ");
         strSurveil.append("AND ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year')   from '(...)years') ) IS NULL)   ");
         strSurveil.append("THEN substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from'(...)year')    ");
         strSurveil.append("WHEN ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)year') ) IS NULL)    ");
         strSurveil.append("AND ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year')   from '(...)years') ) IS NULL)  THEN ''  END AS Year   ");
         strSurveil.append(",CASE WHEN ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)mons') ) > 1)    ");
         strSurveil.append("THEN  substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)mons')    ");
         strSurveil.append("WHEN  ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)mon') )   IS NOT NULL)  ");
         strSurveil.append("AND ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year')   from '(...)mons') ) IS NULL)   ");
         strSurveil.append("THEN substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)mon')    ");
         strSurveil.append("WHEN ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)mons') ) IS NULL)   ");
         strSurveil.append("AND ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year')   from '(...)mon') ) IS NULL)  THEN ''  END AS Month   ");
         strSurveil.append(",CASE WHEN ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)days') ) > 1)    ");
         strSurveil.append("THEN  substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)days')    ");
         strSurveil.append("WHEN  ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)day') )   IS NOT NULL)  ");
         strSurveil.append("AND ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD')- interval '543 year')   from '(...)days') ) IS NULL)   ");
         strSurveil.append("THEN substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)day')    ");
         strSurveil.append("WHEN ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)days') )   IS NULL)   ");
         strSurveil.append("AND ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year')   from '(...)day') ) IS NULL)  THEN '' END AS Day  ");
         strSurveil.append(",t_visit.visit_vn as VN_AN   ");
         strSurveil.append(",t_patient.patient_father_firstname || ' ' ||t_patient.patient_father_lastname  as father_name   ");
         strSurveil.append(",t_patient.patient_mother_firstname || ' ' ||  t_patient.patient_mother_lastname  as mother_name ");
         strSurveil.append(",b_group_icd10.group_icd10_group_rp506  as code506  ");
         strSurveil.append(",t_visit.visit_dx as visit_dx ");
         strSurveil.append(", CASE WHEN (t_diag_icd10.diag_icd10_staff_doctor <> '')  ");
         strSurveil.append(" THEN (b_employee.employee_firstname || '  ' || b_employee.employee_lastname)   ");
         strSurveil.append(" ELSE ''   ");
         strSurveil.append(" END  AS DoctorTreat  ");
         strSurveil.append(",f_patient_occupation.patient_occupation_description as occupation ");
         strSurveil.append(",f_patient_nation.patient_nation_description as nation ");
         strSurveil.append(",f_patient_marriage_status.patient_marriage_status_description as marriage");
         strSurveil.append(",CASE WHEN (t_visit.f_visit_type_id = '1')        THEN 'IPD_patient' ");
         strSurveil.append("WHEN (t_visit.f_visit_type_id = '0')        THEN 'OPD_patient'  ");
         strSurveil.append("WHEN ((t_visit.f_visit_type_id <> '0') and (t_visit.f_visit_type_id <> '1'))  ");
         strSurveil.append("THEN ''  END as visit_type  ");
         strSurveil.append("from b_group_icd10,t_visit INNER JOIN t_surveil ON (t_visit.t_patient_id = t_surveil.t_patient_id ");
         strSurveil.append("    AND t_visit.f_visit_status_id <> '4' )");
         strSurveil.append("INNER JOIN t_diag_icd10 ON (t_diag_icd10.diag_icd10_vn = t_visit.t_visit_id ");
         strSurveil.append("AND t_diag_icd10.diag_icd10_number = t_surveil.surveil_icd10_number ");
         strSurveil.append(" AND t_diag_icd10.diag_icd10_active ='1') ");
         strSurveil.append("LEFT JOIN t_patient ON  t_visit.t_patient_id = t_patient.t_patient_id  ");
         strSurveil.append("LEFT JOIN f_patient_prefix ON t_patient.f_patient_prefix_id = f_patient_prefix.f_patient_prefix_id ");
         strSurveil.append("LEFT JOIN f_sex ON t_patient.f_sex_id = f_sex.f_sex_id ");
         strSurveil.append("LEFT JOIN b_employee ON b_employee.b_employee_id = t_diag_icd10.diag_icd10_staff_doctor ");
         strSurveil.append("LEFT JOIN f_chronic_discharge_status ON t_surveil.f_chronic_discharge_status_id = f_chronic_discharge_status.f_chronic_discharge_status_id ");
         strSurveil.append("LEFT JOIN f_address as tambol ON t_patient.patient_tambon = tambol.f_address_id  ");
         strSurveil.append("LEFT JOIN f_address as amphur ON t_patient.patient_amphur = amphur.f_address_id  ");
         strSurveil.append("LEFT JOIN f_address ON t_patient.patient_changwat  = f_address.f_address_id   ");
         strSurveil.append("LEFT JOIN f_patient_occupation ON t_patient.f_patient_occupation_id = f_patient_occupation.f_patient_occupation_id ");
         strSurveil.append("LEFT JOIN f_patient_nation ON t_patient.f_patient_nation_id = f_patient_nation.f_patient_nation_id ");
         strSurveil.append("LEFT JOIN f_patient_marriage_status ON t_patient.f_patient_marriage_status_id = f_patient_marriage_status.f_patient_marriage_status_id ");
         strSurveil.append("LEFT JOIN f_visit_type ON t_visit.f_visit_type_id = f_visit_type.f_visit_type_id ");
         strSurveil.append("WHERE   SUBSTRING(t_visit.visit_financial_discharge_time,0,11) >= '"+startDate+"'    ");
         strSurveil.append("AND   SUBSTRING(t_visit.visit_financial_discharge_time,0,11) <= '"+endDate+"'   ");
         strSurveil.append("AND   (b_group_icd10.group_icd10_group_rp506 <> '')    ");
         strSurveil.append("AND   (b_group_icd10.group_icd10_group_rp506 <> '99') ");
         strSurveil.append("AND (SUBSTRING(t_surveil.surveil_icd10_number,0,4) = b_group_icd10.group_icd10_number) ");
         if(!treatType.equalsIgnoreCase("0") && !treatType.equalsIgnoreCase(""))
         {
              strSurveil.append("AND t_surveil.f_chronic_discharge_status_id = '"+treatType+"' ");
         }
         strSurveil.append("Group by t_patient.patient_hn  ");
         strSurveil.append(",f_patient_prefix.patient_prefix_description ");
         strSurveil.append(",t_patient.patient_firstname ");
         strSurveil.append(",t_patient.patient_lastname ");
         strSurveil.append(",t_patient.f_sex_id ");
         strSurveil.append(",t_surveil.surveil_icd10_number ");
         strSurveil.append(",t_surveil.surveil_sick_date ");
         strSurveil.append(",f_chronic_discharge_status.chronic_discharge_status_description ");
         strSurveil.append(",t_patient.patient_house ");
         strSurveil.append(",t_patient.patient_road ");
         strSurveil.append(",t_patient.patient_moo ");
         strSurveil.append(",tambol.address_description ");
         strSurveil.append(",amphur.address_description ");
         strSurveil.append(",f_address.address_description ");
         strSurveil.append(",t_visit.visit_begin_visit_time ");
         strSurveil.append(",t_patient.patient_birthday ");
         strSurveil.append(",t_visit.visit_vn ");
         strSurveil.append(",t_patient.patient_father_firstname ");
         strSurveil.append(",t_patient.patient_father_lastname ");
         strSurveil.append(",t_patient.patient_mother_firstname ");
         strSurveil.append(",t_patient.patient_mother_lastname ");
         strSurveil.append(",b_group_icd10.group_icd10_group_rp506 ");
         strSurveil.append(",t_visit.visit_dx ");
         strSurveil.append(",f_patient_occupation.patient_occupation_description ");
         strSurveil.append(",f_patient_nation.patient_nation_description ");
         strSurveil.append(",f_patient_marriage_status.patient_marriage_status_description ");
         strSurveil.append(",t_visit.f_visit_type_id ");
         strSurveil.append(",t_diag_icd10.diag_icd10_staff_doctor ");
         strSurveil.append(",b_employee.employee_firstname ");
         strSurveil.append(",b_employee.employee_lastname ");

         System.out.println(" 506 ติดตามผู้ป่วย : "+ strSurveil.toString());
         rs = this.theConnectionInf.eQuery(strSurveil.toString());

         vc = getData(rs);
         strSurveil = null;
         */
         
         if(!treatType.equalsIgnoreCase("0") && !treatType.equalsIgnoreCase(""))
         {
                String sql = IOStream.readInputDefault("config/rp_standard/standard_surveil_treatType.sql");
                PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
                pm.setString(1,startDate);
                pm.setString(2,endDate);
                pm.setString(3,treatType);
                
                //System.out.println("SQL Treat : " + pm.toString());
                vc = getData(pm.executeQuery());
         }
         else
         {
                String sql = IOStream.readInputDefault("config/rp_standard/standard_surveil.sql");
                PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
                pm.setString(1,startDate);
                pm.setString(2,endDate);
               // System.out.println("SQL All : " + pm.toString());
                vc = getData(pm.executeQuery());
         }
         
         
         if(vc.size()==0)
             return null;
         else
             return vc;
    }
    
    public Vector eQuery(String sql)
    {
        
        try
        {
        System.out.println("SQL : "+ sql);
        rs = theConnectionInf.eQuery(sql);
        vRP506FollowPateint= new Vector();
        ObjectRP506Surveil = new RP506Surveil();
            while(rs.next())
            {
                ObjectRP506Surveil = new RP506Surveil();
                ObjectRP506Surveil.setInitData();
                String address = "";             
                ObjectRP506Surveil.rp506follow_date_visit = Gutil.getDateToString(Gutil.getDateFromText(rs.getString(15)), false);
                ObjectRP506Surveil.rp506follow_age_year = rs.getString(16);
                ObjectRP506Surveil.rp506follow_age_month = rs.getString(17);
                ObjectRP506Surveil.rp506follow_age_day = rs.getString(18);
                ObjectRP506Surveil.rp506follow_vn_an = rs.getString(19);                
                
                // old
                ObjectRP506Surveil.rp506follow_hn = rs.getString(1);
                ObjectRP506Surveil.rp506follow_fname = rs.getString(3);
                ObjectRP506Surveil.rp506follow_lname = rs.getString(4);
                
                ObjectRP506Surveil.rp506follow_father_name = rs.getString(20);
                ObjectRP506Surveil.rp506follow_mother_name = rs.getString(21);
                ObjectRP506Surveil.rp506follow_salf_doctor = rs.getString(13);
                
                // old
                ObjectRP506Surveil.rp506follow_icd_code = rs.getString(6);
                
                ObjectRP506Surveil.rp506follow_code506 = rs.getString(22);
                ObjectRP506Surveil.rp506follow_sex = rs.getString(5);    
                ObjectRP506Surveil.rp506follow_visit_dx = rs.getString(23);   
                ObjectRP506Surveil.rp506follow_occupation = rs.getString(24);   
                ObjectRP506Surveil.rp506follow_nation = rs.getString(25);   
                ObjectRP506Surveil.rp506follow_marriage = rs.getString(26);   
                
                // old
                ObjectRP506Surveil.rp506follow_startdate_treat =  Gutil.getDateToString(Gutil.getDateFromText(rs.getString(7)), false);
                
                ObjectRP506Surveil.rp506follow_visit_type = rs.getString(27);   
                ObjectRP506Surveil.rp506follow_prefix = rs.getString(2); 
                
                //old
                ObjectRP506Surveil.rp506follow_status_treat = rs.getString(8);     
                ObjectRP506Surveil.rp506follow_address = convertAddress(rs.getString(9),rs.getString(10),rs.getString(11)) + rs.getString(12) + rs.getString(13) + rs.getString(14);  
                
                
                
                vRP506FollowPateint.add(ObjectRP506Surveil);
                ObjectRP506Surveil = null;
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vRP506FollowPateint;
    }
    
    public String convertAddress(String house,String road, String moo )
    {
        String address = "";
        if(!house.equals(""))
        {
            address = address + house + " ";
        }
        if(!moo.equals(""))
        {
            address = address + " หมู่ที่ " + moo + " ";
        }
        if(!road.equals(""))
        {
            address = address + " ถนน" + road + " ";
        }
        return address;
    }
    
    private Vector getData(ResultSet rs) throws Exception
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
            int ii = 0;
            while(rs.next())
            {   ii++;
                rowdata = new String[column];
                for(int i = 0 ; i < column;i++)
                {
                    if(rs.getObject(i+1) != null)
                    {
                        rowdata[i] = rs.getString(i+1);                        
                        if(i==4)
                        {
                            rowdata[i] = Language.getTextBundle(rowdata[i]);
                        }
                        else if(i==6)
                        {
                            rowdata[i] = Gutil.getDateToString(Gutil.getDateFromText(rowdata[i]), false);
                        }
                        else if(i==9)
                        {
                            rowdata[i] = Gutil.getDateToString(Gutil.getDateFromText(rowdata[i]), false);
                        }      
                        else if(i==22)
                        {
                            rowdata[i] = Language.getTextBundle(rowdata[i]);
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
    
}
