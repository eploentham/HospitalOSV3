/*
 * ReportNanControl.java
 *
 * Created on 5 มิถุนายน 2549, 17:07 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.control;
import com.reportnan.objdb.ManageDB;
import com.hospital_os.usecase.connection.ConnectionInf;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class ReportNanControl
{
    ManageDB theManageDB;
    ConnectionInf theConnectionInf;
    
    private String startDate;
    private String finishDate;
    private String[] headColumn;
    private String[] column;
    Vector vcData;
    /** Creates a new instance of ReportNanControl */
    public ReportNanControl(ManageDB mdb)
    {
        theManageDB = mdb;
        theConnectionInf = theManageDB.theConnectionInf;        
    }
    
    /**
     *กำหนดค่าให้กับวันที่เริ่มต้น และสิ้นสุด
     *@Author pu
     *@Date 05/06/2006
     */
    public boolean setDateForQuery(String startDate, String endDate)
    {
        this.startDate = startDate;
        this.finishDate = endDate;
        
        return true;
    }
    
    /**
     *รายงานข้อมูลจำนวนประชากร หลังคาเรือน ครอบครัว แยกตามหมู่บ้าน
     *@param tambon เป็น String ที่เก็บรหัสตำบลที่ต้องการดึงข้อมูล
     *@return Vector ที่เป็นข้อมูลประชากร
     *@Author pu
     *@Date 06/06/2006
     */
    public Vector queryResident(String tambon)
    {
        theConnectionInf.open();
        try
        {            
            vcData = theManageDB.theRPResidentDB.queryResidentByTambon(tambon);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
    /**
     *รายงานผู้ป่วยที่เข้ารับบริการคลินิกต่าง ๆ
     *@return Vector ที่เป็นผู้ป่วยที่ผ่านคลินิกต่างๆ
     *@Author pu
     *@Date 06/06/2006
     */
    public Vector queryPatientInClinic()
    {
        theConnectionInf.open();
        try
        {
            vcData = theManageDB.theRPPatientInClinicDB.queryPatientInClinic(this.startDate, this.finishDate);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
    /**
     *รายงานลูกหนี้รายวันประเภทผู้ป่วยนอก
     *@return Vector ที่เป็นลูกหนี้รายวันประเภทผู้ป่วยนอก
     *@Author pu
     *@Date 06/06/2006
     */
    public Vector queryPatientOPDRemain()
    {
        theConnectionInf.open();
        try
        {
            vcData = theManageDB.theRPPatientOPDRemainDB.queryPatientOPDRemainByDate(this.startDate, this.finishDate); 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
    /**
     *รายงานอุบัติเหตุ 19 สาเหตุแบบฟอร์มของน่าน
     *@return Vector ที่เป็นอุบัติเหตุ 19 สาเหตุ
     *@Author pu
     *@Date 07/06/2006
     */
    public Vector queryAccident19Cause()
    {
        theConnectionInf.open();
        try
        {
            vcData = theManageDB.theRPAccedent19CauseNanDB.queryAccedent19CauseByDate(this.startDate, this.finishDate);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
    /*รายงานผู้ป่วยฉุกเฉิน-ไม่ฉุกเฉิน
     *@param year1 เป็น String ที่เก็บปีงบประมาณที่ต้องการดึงข้อมูล
     *@param year2 เป็น String ที่เก็บปีก่อนหน้าของปีงบประมาณที่ต้องการดึงข้อมูล
     *@param morning_time_start เก็บ String เวลาเริ่มต้นของเวรเช้า
     *@param morning_time_end เก็บ String เวลาสิ้นสุดของเวรเช้า
     *@param afternoon_time_start เก็บ String เวลาเริ่มต้นของเวรบ่าย
     *@param afternoon_time_end เก็บ String เวลาสิ้นสุดของเวรบ่าย
     *@param night_time_start เก็บ String เวลาเริ่มต้นของเวรดึก
     *@param night_time_end เก็บ String เวลาสิ้นสุดของเวรดึก
     *@param morning_sat_start เก็บ String เวลาเริ่มต้นของเวรเช้า วันเสาร์
     *@param morning_sat_end เก็บ String เวลาสิ้นสุดของเวรเวรเช้า วันเสาร์
     *@param morning_sun_start เก็บ String เวลาเริ่มต้นของเวรเช้า วันอาทิตย์
     *@param morning_sun_end เก็บ String เวลาสิ้นสุดของเวรเช้า วันอาทิตย์     
     *@return Vector ที่เป็นผู้ป่วยฉุกเฉิน-ไม่ฉุกเฉิน
     *@Author pu
     *@Date 12/06/2006
     */    
    public Vector queryEmergencyPatient(String year1,String morning_time_start,String morning_time_end,
                                        String afternoon_time_start,String afternoon_time_end,String night_time_start,
                                        String night_time_end,String morning_sat_start,String morning_sat_end,
                                        String morning_sun_start,String morning_sun_end)
    {   
        String year2 = "";
        int year = 0;
        year = Integer.parseInt(year1) - 1;
        year2 = String.valueOf(year);
        System.out.println("-----" + year);
        theConnectionInf.open();
        try {
            vcData = theManageDB.theRPEmergencyPatientDB.queryEmergencyPatientByDateTime(year1, year2, morning_time_start, morning_time_end, afternoon_time_start, afternoon_time_end, night_time_start, night_time_end, morning_sat_start, morning_sat_end, morning_sun_start, morning_sun_end);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    /**
     *รายงานผู้ป่วยที่รับบริการหัตถการ
     *@return Vector ที่เป็นผู้ป่วยที่รับบริการหัตถการ
     *@Author pu
     *@Date 14/06/2006
     */
    public Vector queryPatientOperated(String servicepoint_id)
    {
        theConnectionInf.open();
        try
        {
            vcData = theManageDB.theRPPatientOperatedDB.queryPatientOperatedByDate(this.startDate, this.finishDate, servicepoint_id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
    /**
     *รายงานประจำวัน งานผู้ป่วยนอก
     *@return Vector ที่เป็นผู้ป่วยที่รับบริการหัตถการ
     *@Author pu
     *@Date 14/06/2006
     */
    public Vector queryDailyOPDPatient(String year,String month,
                                       String morning_start,
                                       String morning_end,
                                       String afternoon_start,
                                       String afternoon_end)
    {
        theConnectionInf.open();
        try
        {
            vcData = theManageDB.theRPDailyOPDPatientDB.queryDailyOPDPatientByDateTime(year, month,morning_start,morning_end,afternoon_start,afternoon_end);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
    /**
     *รายงานผู้ป่วยใน
     *@return Vector ที่เป็นผู้ป่วยใน
     *@Author pu
     *@Date 16/06/2006
     */
    public Vector queryIPDPatient(int dischargeType)
    {
        theConnectionInf.open();
        try
        {
            vcData = theManageDB.theRPIPDPatientDB.queryIPDPatientByDate(this.startDate, this.finishDate, dischargeType);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
    /**
     *รายงาน 505 แยกตามคลินิก
     *@return Vector ที่เป็นผู้ป่วย 505
     *@Author pu
     *@Date 17/06/2006
     */
    public Vector query505INClinic()
    {
        theConnectionInf.open();
        try
        {
            vcData = theManageDB.theRP505INClinicDB.query505INClinicByDate(this.startDate, this.finishDate);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
    
    /**
     *รายงาน NCD
     *@return Vector ที่เป็นผู้ป่วย NCD
     *@Author pu
     *@Date 19/06/2006
     */
    public Vector queryPatientNCD(String year,String month)
    {
        theConnectionInf.open();
        try
        {
            vcData = theManageDB.theRPPatientNCDDB.queryPaitentNCD(year,month);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vcData;
    }
}
