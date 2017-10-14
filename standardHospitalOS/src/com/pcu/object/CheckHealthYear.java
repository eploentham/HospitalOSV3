/*
 * check_health_year.java
 *
 * Created on 20 มิถุนายน 2548, 18:22 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Noom
 */
public class CheckHealthYear extends Persistent{
    private static String init = "";
    /**
     * รหัสข้อมูลการตรวจสุขภาพประจำปี
     */
    public String t_health_check_health_year_id = init;
    /**
     * รหัสการตรวจสุขภาพ
     */
    public String b_health_check_health_year_activity_id = init;
    /**
     * ผลการตรวจ
     */
    public String check_health_year_result = init;
    /**
     * หมายเหตุการตรวจสุขภาพประจำปี
     */
    public String check_health_year_remark = init;
    /**
     * วันที่บันทึก
     */
    public String check_health_year_record_time = init;
    /**
     * วันที่แก้ไข
     */
    public String check_health_year_modify_time = init;
    /**
     * วันที่แก้ไข
     */
    public String check_health_year_cancle_time = init;
    /**
     * หมายเลข id ผู้ที่บันทึก
     */
    public String check_health_year_staff_record =init;
    /**
     * หมายเลข id ผู้ที่แก้ไข
     */
    public String check_health_year_staff_modify =init;
    /**
     * หมายเลข id ผู้ที่ยกเลิก
     */
    public String check_health_year_staff_cancle =init;
    /**
     * สถานะการทำงาน
     */
    public String check_health_year_active =init;
    /**
     * visit_id
     */
    public String visit_id = init;
    public String patient_id = init;
    /**
     * รหัสประชากร
     */
    public String family_id = init;
    /**
     * วันทีสำรวจ
     */
    public String survey_date = init;

    
    /** Creates a new instance of check_health_year */
    public CheckHealthYear() {
    }
    
}
