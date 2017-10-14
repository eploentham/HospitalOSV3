/*
 * RP115Group4_2549DB.java
 *
 * Created on 22 มีนาคม 2549, 17:49 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.objdb;
import com.standardReport.object.RP115Group4AbortPregnant_2549;
import com.standardReport.object.RP115Group4Assort_2549;
import com.standardReport.object.RP115Group4CheckConfirm_2549;
import com.standardReport.object.RP115Group4Efficiency_2549;
import com.standardReport.object.RP115Group4HomeAmount_2549;
import com.standardReport.object.RP115Group4PersonAmount_2549;
import com.standardReport.object.RP115Group4SchoolOutSite_2549;
import com.standardReport.object.RP115Group4TreatInfant_2549;
import com.standardReport.object.RP115Group4_2549;
import java.util.*;
import java.sql.*;
import java.io.*;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.standardReport.subject.StandardDB;
import com.standardReport.utility.*;
/**
 *
 * @author pu
 */
public class RP115Group4_2549DB
{
    public ConnectionInf theConnectionInf;
    RP115Group4Assort_2549 theRP115Group4Assort_2549;
    RP115Group4CheckConfirm_2549 theRP115Group4CheckConfirm_2549;
    RP115Group4AbortPregnant_2549 theRP115Group4AbortPregnant_2549;
    RP115Group4TreatInfant_2549 theRP115Group4TreatInfant_2549;        
    RP115Group4Efficiency_2549 theRP115Group4Efficiency_2549;
    RP115Group4PersonAmount_2549 theRP115Group4PersonAmount_2549;
    RP115Group4_2549 theRP115Group4_2549;

    RP115Group4HomeAmount_2549 theRP115Group4HomeAmount_2549;
    RP115Group4SchoolOutSite_2549 theRP115Group4SchoolOutSite_2549;
    
    ResultSet rs = null;
    String SQL = "";
    Vector vReport;
    /** Creates a new instance of RP115Group4_2549DB */
    public RP115Group4_2549DB(ConnectionInf conf)
    {
        theConnectionInf = conf;
        theRP115Group4Assort_2549 = new RP115Group4Assort_2549();
        theRP115Group4CheckConfirm_2549 = new RP115Group4CheckConfirm_2549();
        theRP115Group4AbortPregnant_2549 = new RP115Group4AbortPregnant_2549();
        theRP115Group4TreatInfant_2549 = new RP115Group4TreatInfant_2549();
        theRP115Group4Efficiency_2549 = new RP115Group4Efficiency_2549();
        theRP115Group4PersonAmount_2549 = new RP115Group4PersonAmount_2549();
        theRP115Group4_2549 = new RP115Group4_2549();
        
        theRP115Group4HomeAmount_2549 = new RP115Group4HomeAmount_2549();
        theRP115Group4SchoolOutSite_2549 = new RP115Group4SchoolOutSite_2549();
        
    }
    
    /**
     *รายการการตรวจคัดกรอง
     *@param startdate เป็น String ของวันที่เริ่มต้นในการดึงข้อมูล
     *@param enddate เป็น String ของวันที่สิ้นสุดในการดึงข้อมูล
     *@param startcheck เป็น String ของปีและเดือนช่วงเริ่มต้น เพื่อนำไปตรวจสอบช่วงเวลาในการดึงข้อมูล
     *@param endcheck เป็น String ของปีและเดือนช่วงสิ้นสุด เพื่อนำไปตรวจสอบช่วงเวลาในการดึงข้อมูล
	 *@param isJan เป็น boolean สำหรับตรวจสอบว่าเป็นเดือน มกราคม หรือไม
     *@return Vector ของข้อมูลการตรวจคัดกรอง
     *@Author pu
     *@date 22/03/2006
     */
    public Vector selectGroup4AssortByDate(String startdate,String enddate,String startcheck,String endcheck, boolean isJan)throws Exception
    {
          String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part4_assort.sql");
            PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
            pm.setString(1,startdate);
            pm.setString(2,enddate);
            eAssortQuery(pm.executeQuery());

          sql = null;     
          if(this.vReport.size()==0)
          {
              return null;
          }
          else
          {
              return this.vReport;
          }
    }
    
    /**
     *รายการการตรวจยืนยันการเป็นโรค
     *@param startdate เป็น String ของวันที่เริ่มต้นในการดึงข้อมูล
     *@param enddate เป็น String ของวันที่สิ้นสุดในการดึงข้อมูล
	 *@param startcheck เป็น String ของวันที่เริ่มต้นในการตรวจสอบข้อมูลคนใหม่ในปี
     *@param endcheck เป็น String ของวันที่สิ้นสุดในการตรวจสอบข้อมูลคนใหม่ในปี
     *@param isJan เป็น boolean สำหรับตรวจสอบว่าเป็นเดือน มกราคม หรือไม่
     *@return Vector ของข้อมูลการตรวจยืนยันการเป็นโรค
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4CheckConfirmByDate(String startdate,String enddate,String startcheck,String endcheck,boolean isJan)throws Exception
    {
       
        String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part4_confirm.sql");
            PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
            pm.setString(1,startdate);
            pm.setString(2,startdate);
            pm.setString(3,startdate);
            pm.setString(4,startdate);
            pm.setString(5,startdate);
            pm.setString(6,enddate);
//            System.out.println(pm.toString());
            echeckConfirmQuery(pm.executeQuery());

          sql = null;   
        if(this.vReport.size()==0)
        {
            return null;
        }
        else
        {
            return this.vReport;
        }
    }
    
    /**
     *รายการการสิ้นสุดการตั้งครรภ์ เนื่องจากทารกในครรภ์เป็น Thalassemia
     *@param startdate เป็น String ของวันที่เริ่มต้นในการดึงข้อมูล
     *@param enddate เป็น String ของวันที่สิ้นสุดในการดึงข้อมูล
     *@return Vector ของข้อมูลการสิ้นสุดการตั้งครรภ์
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4AbortPregnantByDate(String startdate,String enddate)throws Exception
    {        
        
        String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part4_pregnancy.sql");
            PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
            pm.setString(1,startdate);
            pm.setString(2,enddate);
            pm.setString(3,startdate);
            pm.setString(4,enddate);
            eAbortPregnant(pm.executeQuery());

          sql = null;   
        if(this.vReport.size()==0)
        {
            return null;
        }
        else
        {
            return this.vReport;
        }
    }
    
    /**
     *รายการการรักษา เด็ก 0-1 เดือนที่บกพร่องไอโอดีนและไทรอยด์
     *@param startdate เป็น String ของวันที่เริ่มต้นในการดึงข้อมูล
     *@param enddate เป็น String ของวันที่สิ้นสุดในการดึงข้อมูล
	 *@param isJan เป็น boolean สำหรับตรวจสอบว่าเป็นเดือน มกราคม หรือไม
     *@return Vector ของข้อมูลการรักษา เด็ก 0-1 เดือนที่บกพร่องไอโอดีนและไทรอยด์
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4TreatInfantByDate(String startdate,String enddate,String startcheck,String endcheck,boolean isJan)throws Exception
    {        
        
        String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part4_TreatInfant.sql");
            PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
            pm.setString(1,startdate);
            pm.setString(2,enddate);
            eTreatInfant(pm.executeQuery());

          sql = null; 
        if(this.vReport.size()==0)
        {
            return null;
        }
        else
        {
            return this.vReport;
        }
    }
    /**
     *รายการจำนวนครั้งในการให้บริการฟื้นฟูสมรรถภาพ
     *@param startdate เป็น String ของวันที่เริ่มต้นในการดึงข้อมูล
     *@param enddate เป็น String ของวันที่สิ้นสุดในการดึงข้อมูล
     *@return Vector ของข้อมูลการให้บริการฟื้นฟูสมรรถภาพ
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4EfficiencyByDate(String startdate,String enddate) throws Exception
    {
        
        String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part4_Efficiency.sql");
            PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
            pm.setString(1,startdate);
            pm.setString(2,enddate);
            pm.setString(3,startdate);
            pm.setString(4,enddate);
            eEfficiency(pm.executeQuery()); 

          sql = null;
        if(this.vReport.size()==0)
        {
            return null;
        }
        else
        {
            return this.vReport;
        }
    }
    
    
    /**
     *รายการจำนวนผู้รับบริการการเยี่ยมบ้าน
     *@param startdate เป็น String ของวันที่เริ่มต้นในการดึงข้อมูล
     *@param enddate เป็น String ของวันที่สิ้นสุดในการดึงข้อมูล
     *@return Vector ของข้อมูลการเยี่ยมบ้าน
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4PersonAmountByDate(String startdate,String enddate)throws Exception
    {        
       
        String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part4_PersonAmount.sql");
            PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
            pm.setString(1,startdate);
            pm.setString(2,enddate);
            pm.setString(3,startdate);
            pm.setString(4,enddate);
            ePersonAmount(pm.executeQuery()); 

          sql = null;
        if(this.vReport.size()==0)
        {
            return null;
        }
        else
        {
            return this.vReport;
        }
    }
    
    /**
     *การดึงรายงานจำนวนครัวเรือนที่ ได้จากการไปเยี่ยมบ้าน
     *@param startdate เป็น String ของวันที่เริ่มต้นในการดึงข้อมูล
     *@param enddate เป็น String ของวันที่สิ้นสุดในการดึงข้อมูล
     *@return Vector ของข้อมูลจำนวนครัวเรือนที่ ได้จากการไปเยี่ยมบ้าน
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4HomeAmountByDate(String startdate,String enddate) throws Exception
    {
        
        String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part4_HomeAmount.sql");
            PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
            pm.setString(1,startdate);
            pm.setString(2,enddate);
            eHomeAmount(pm.executeQuery()); 

          sql = null;
        if(this.vReport.size()==0)
        {
            return null;
        }
        else
        {
            return this.vReport;
        }
    }
    
    /**
     *การดึงรายงานจำนวนครั้งในการออกหน่วยอนามัยโรงเรียน
     *@param startdate เป็น String ของวันที่เริ่มต้นในการดึงข้อมูล
     *@param enddate เป็น String ของวันที่สิ้นสุดในการดึงข้อมูล
     *@return Vector ของข้อมูลจำนวนครั้งในการออกหน่วยอนามัยโรงเรียน
     *@Author pu
     *@date 23/03/2006
     */
    public Vector selectGroup4SchoolOutSiteByDate(String startdate,String enddate) throws Exception
    {
      
        String sql = IOStream.readInputDefault("config/rp_standard/standard_1105_part4_SchoolOutSite.sql");
            PreparedStatement pm = theConnectionInf.getConnection().prepareStatement(sql);
            pm.setString(1,startdate);
            pm.setString(2,enddate);
            eSchoolOutSite(pm.executeQuery()); 

          sql = null;
        if(this.vReport.size()==0)
        {
            return null;
        }
        else
        {
            return this.vReport;
        }
    }
       
    
    /**
     *การดึงรายงานการตรวจคัดกรอง
     *@param SQL เป็น String ที่เก็บคำสั่ง sql สำหรับดึงข้อมูล
     *@Author pu
     *@date 22/03/2006
     */
    private void eAssortQuery(ResultSet rs)
    {
    
        try{
        //rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group4Assort_2549 = new RP115Group4Assort_2549();
            while(rs.next()) 
            {
                theRP115Group4Assort_2549 = new RP115Group4Assort_2549();
                
                theRP115Group4Assort_2549.plan_type = rs.getString(1);
                theRP115Group4Assort_2549.womb_cancer = rs.getString(2);                
                theRP115Group4Assort_2549.breast_cancer = rs.getString(3); 
                theRP115Group4Assort_2549.thalassemia = rs.getString(4);        
                theRP115Group4Assort_2549.thalassemia_mom = rs.getString(5);
                theRP115Group4Assort_2549.iodine_thiroid = rs.getString(6);
                  
                this.vReport.add(theRP115Group4Assort_2549);
                theRP115Group4Assort_2549 = null;
            }            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
    }
    
    /**
     *การดึงรายงานการตรวจยืนยันการเป็นโรค
     *@param SQL เป็น String ที่เก็บคำสั่ง sql สำหรับดึงข้อมูล
     *@Author pu
     *@date 23/03/2006
     */
    private void echeckConfirmQuery(ResultSet rs)
    {
    
        try{
        //rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group4CheckConfirm_2549 = new RP115Group4CheckConfirm_2549();
            while(rs.next()) 
            {
                theRP115Group4CheckConfirm_2549 = new RP115Group4CheckConfirm_2549();
                
                theRP115Group4CheckConfirm_2549.plan_type = rs.getString(1);
                theRP115Group4CheckConfirm_2549.thalassemia_infant = rs.getString(2);                
                theRP115Group4CheckConfirm_2549.thiroid = rs.getString(3); 
                 
                this.vReport.add(theRP115Group4CheckConfirm_2549);
                theRP115Group4CheckConfirm_2549 = null;
            }            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
    }
    
    /**
     *การดึงรายงานการสิ้นสุดการตั้งครรภ์เนื่องจากทารกในครรภ์เป็น Thalassemia 
     *@param SQL เป็น String ที่เก็บคำสั่ง sql สำหรับดึงข้อมูล
     *@Author pu
     *@date 23/03/2006
     */
    private void eAbortPregnant(ResultSet rs)
    {
    
        try{
        //rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group4AbortPregnant_2549 = new RP115Group4AbortPregnant_2549();
            while(rs.next()) 
            {
                theRP115Group4AbortPregnant_2549 = new RP115Group4AbortPregnant_2549();
                
                theRP115Group4AbortPregnant_2549.plan_type = rs.getString(1);
                theRP115Group4AbortPregnant_2549.terminated_pregnance = rs.getString(2);                
                
                this.vReport.add(theRP115Group4AbortPregnant_2549);
                theRP115Group4AbortPregnant_2549 = null;
            }            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
    }
    
    /**
     *การดึงรายงานเด็ก 0-1 เดือนได้รับการรักษาภาวะบกพร่องไอโอดีนและไทรอยด์
     *@param SQL เป็น String ที่เก็บคำสั่ง sql สำหรับดึงข้อมูล
     *@Author pu
     *@date 23/03/2006
     */
    private void eTreatInfant(ResultSet rs)
    {
        
        try
        {
        //rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group4TreatInfant_2549 = new RP115Group4TreatInfant_2549();
            while(rs.next())
            {
                theRP115Group4TreatInfant_2549 = new RP115Group4TreatInfant_2549();
                
                theRP115Group4TreatInfant_2549.plan_type = rs.getString(1);
                theRP115Group4TreatInfant_2549.treat_thiroid = rs.getString(2);
                
                this.vReport.add(theRP115Group4TreatInfant_2549);
                theRP115Group4TreatInfant_2549 = null;
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        } 
    }
    
    /**
     *การดึงรายงานจำนวนครั้งที่ให้บริการฟื้นฟูสมรรถภาพ
     *@param SQL เป็น String ที่เก็บคำสั่ง sql สำหรับดึงข้อมูล
     *@Author pu
     *@date 23/03/2006
     */
    private void eEfficiency(ResultSet rs)
    {
        
        try
        {
       // rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group4Efficiency_2549 = new RP115Group4Efficiency_2549();
            while(rs.next())
            {
                theRP115Group4Efficiency_2549 = new RP115Group4Efficiency_2549();
                
                theRP115Group4Efficiency_2549.plan_type = rs.getString(1);
                theRP115Group4Efficiency_2549.efficiency = rs.getString(2);
                
                this.vReport.add(theRP115Group4Efficiency_2549);
                theRP115Group4Efficiency_2549 = null;
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     *การดึงรายงานจำนวนผู้รับบริการการเยี่ยมบ้าน
     *@param SQL เป็น String ที่เก็บคำสั่ง sql สำหรับดึงข้อมูล
     *@Author pu
     *@date 23/03/2006
     */
    private void ePersonAmount(ResultSet rs)
    {
        
        try
        {
        //rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group4PersonAmount_2549 = new RP115Group4PersonAmount_2549();
            while(rs.next())
            {
                theRP115Group4PersonAmount_2549 = new RP115Group4PersonAmount_2549();
                
                theRP115Group4PersonAmount_2549.plan_type = rs.getString(1);
                theRP115Group4PersonAmount_2549.person = rs.getString(2);
                
                this.vReport.add(theRP115Group4PersonAmount_2549);
                theRP115Group4PersonAmount_2549 = null;
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     *การดึงรายงานจำนวนครัวเรือนที่ ได้จากการไปเยี่ยมบ้าน
     *@param SQL เป็น String ที่เก็บคำสั่ง sql สำหรับดึงข้อมูล
     *@Author pu
     *@date 23/03/2006
     */
    private void eHomeAmount(ResultSet rs)
    {
        
        try
        {
        //rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group4HomeAmount_2549 = new RP115Group4HomeAmount_2549();
            while(rs.next())
            {
                theRP115Group4HomeAmount_2549 = new RP115Group4HomeAmount_2549();
                
                theRP115Group4HomeAmount_2549.homeAmount = rs.getString(1);
            
                this.vReport.add(theRP115Group4HomeAmount_2549);
                theRP115Group4HomeAmount_2549 = null;
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     *การดึงรายงานจำนวนครั้งในการออกหน่วยอนามัยโรงเรียน
     *@param SQL เป็น String ที่เก็บคำสั่ง sql สำหรับดึงข้อมูล
     *@Author pu
     *@date 23/03/2006
     */
    private void eSchoolOutSite(ResultSet rs)
    {
        
        try
        {
     //   rs = theConnectionInf.eQuery(sql);
        this.vReport= new Vector();
        theRP115Group4SchoolOutSite_2549 = new RP115Group4SchoolOutSite_2549();
            while(rs.next())
            {
                theRP115Group4SchoolOutSite_2549 = new RP115Group4SchoolOutSite_2549();
                
                theRP115Group4SchoolOutSite_2549.visit_school = rs.getString(1);
            
                this.vReport.add(theRP115Group4SchoolOutSite_2549);
                theRP115Group4SchoolOutSite_2549 = null;
            }
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    /**
     *  ใช้ในการ Convert Object เป็น String โดยที่รับ ค่า true หรือ false เพื่อสร้างหัวคอลัมน์หรือไม่
     *  ถ้าเป็น true จะให้แสดงหัวคอลัมน์ และใช้ Tab เป็นตัวคั่น
     *  ถ้าเป็น false จะไม่แสดงไม่หัวคอลัมน์ และใช้ Pipe เป็นตัวคั่น
     *  @param vObject เป็น Vector ของ Obeject ชือ RP115Group4_2549
     *  @param isGetColumnName เป็น Boolean ถ้าเป็น true จะให้แสดงหัวคอลัมน์ ถ้าเป็น false จะไม่แสดงไม่หัวคอลัมน์
     *  @param obj เป็น String ที่ใช้เพื่อตรวจสอบว่า Object ใด ที่ถูกเก็บอยู่ใน Vector
     *  @return เป็น String ของ Sql ที่มีการแปลงค่าเรียบร้อยแล้ว
     */
    public String convertToString(Vector vObject,boolean isGetColumnName)
    {
        StringBuffer sql = new StringBuffer();
        String separator = Constant.PIPE;
        System.out.println("------------- SIZE DATA ------------ " + vObject.size());
        if(vObject != null)
        {
                RP115Group4_2549 p = null;
                if(isGetColumnName)
                {
                    separator = Constant.TAB;
                    sql.append(Gutil.getTextBundle("TypePlan")+separator
                            +Gutil.getTextBundle("Womb_cancer")+separator
                            +Gutil.getTextBundle("Breast_cancer")+separator
                            +Gutil.getTextBundle("Thalassemia")+separator
                            +Gutil.getTextBundle("Thalassemia_infant")+separator
                            +Gutil.getTextBundle("Thalassemia_mom")+separator
                            +Gutil.getTextBundle("Terminated_pregnance")+separator
                            +Gutil.getTextBundle("Iodine_Thiroid")+separator
                            +Gutil.getTextBundle("Thiroid")+separator
                            +Gutil.getTextBundle("Treat_Thiroid")+separator
                            +Gutil.getTextBundle("Efficiency")+separator
                            +Gutil.getTextBundle("Person")
                            +Constant.NEWLINE);
                }
                for(int i=0;i<vObject.size();i++)
                {                    
                    p = (RP115Group4_2549)vObject.elementAt(i);
                    sql.append(p.plan_type + separator      
                            + p.womb_cancer + separator
                            + p.breast_cancer + separator
                            + p.thalassemia + separator
                            + p.thalassemia_infant + separator
                            + p.thalassemia_mom + separator
                            + p.terminated_pregnance + separator
                            + p.iodine_thiroid + separator
                            + p.thiroid + separator
                            + p.treat_thiroid + separator
                            + p.efficiency + separator
                            + p.person
                            + Constant.NEWLINE);
                    System.out.println("------------- DATA ------------ " + p.plan_type);
                }           
        }
        return sql.toString();
    }
}
