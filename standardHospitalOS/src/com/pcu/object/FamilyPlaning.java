/*
 * FamilyPlaning.java
 *
 * Created on 20 มิถุนายน 2548, 11:55 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 * 
 * @author tong
 */
public class FamilyPlaning extends Persistent{
    /**
     * ใช้กำหนดค่าเริ่มต้น
     */
    private static String init = "";
    /**
     * รหัสการเข้ารับบริการ
     */
    public String visit_id = init;

    public String patient_id = init;
    /**
     * วิธีการคุมกำเนิด
     */
    public String f_health_family_planing_method_id = init;
    /**
     * สาเหตุของการไม่คุมกำเนิด
     */
    public String f_health_family_planing_id = init;	
    /**
     * จำนวนบุตร(คน)
     */
    public String health_family_planing_parity = init;	
    /**
     * จำนวนบุตร(คน)
     */
    public String health_family_planing_staff_record = init;	
    /**
     * ผู้ทำการบันทึก
     */
    public String record_date_time = init;
    /**
     * หมายเหตุ
     */
    public String health_family_planing_notice = init;
    /**
     * หมายเลข vn
     */
    public String health_family_planing_vn = init;	
    /**
     * หมายเลข hn
     */
    public String health_family_planing_hn = init;	
    /**
     * ผลการตรวจมะเร็งเต้านม
     */
    public String health_family_planing_breast_exam = init;	
    /**
     * บันทึกผลการผิดปกติของการตรวจมะเร็งเต้านม
     */
    public String health_family_planing_breast_exam_notice = init;	
    /**
     * ผลการตรวจมะเร็งปากมดลูก
     */
    public String health_family_planing_cervix_exam = init;
    /**
     * บันทึกผลการผิดปกติของการตรวจมะเร็งปากมดลูก
     */
    public String health_family_planing_cervix_exam_notice = init;	
    /**
     * ทดสอบการตั้งครรภ์ โดยใช้เวชภัณฑ์ทดสอบการตั้งครรภ์หรือไม่
     */
    public String health_famlily_planing_pregnant_exam = init;
    /**
     * รหัสของยาและเวชภัณฑ์คุมกำเนิด
     */
    public String health_famlily_planing_supply = init;	
    /**
     * จำนวนหน่วยเวชภัณฑ์
     */
    public String health_famlily_planing_supply_qty = init;	
    /**
     * วันที่นัดครั้งถัดไป
     */
    public String health_famlily_planing_appointment = init;	
    /**
     * ผู้ทำการแก้ไข
     */
    public String health_family_planing_staff_update = init;	
    /**
     * วัน-เวลาที่แก้ไข
     */
    public String update_record_date_time = init;
    /**
     * ผู้ทำการยกเลิก
     */
    public String health_family_planing_staff_remove = init;
    /**
     * แสดงหรือไม่แสดง
     */
    public String health_family_planing_active = init;
    /**
     * สถานะของการสั่งเวชภัณฑ์คุมกำเนิด
     */
    public String health_family_planing_order_status = "0";
    /**
     * รหัสบุคคล
     */
    public String family_id = init;
    /**
     * วันที่ให้บริการ
     */
    public String health_family_planing_date = init;
    /**
     * วิธีการตรวจมะเร็งปากมดลูก
     */
    public String health_family_planing_cervix_method  = init;
    /** Creates a new instance of FamilyPlaning */
    public FamilyPlaning() {
    }
    
}
