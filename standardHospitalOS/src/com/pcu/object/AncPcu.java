/*
 * AncPcu.java
 *
 * Created on 7 กรกฎาคม 2548, 10:49 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class AncPcu extends Persistent{
    private static String init = "";
    /**
     * เลข vn
     */
    public String anc_vn= init;
    /**
     * รหัสหมายเลขการตั้งครรถ์
     */
    public String pregnancy_id= init;
    /**
     * รับบริการลำดับช่วงที่
     */
    public String anc_section= init;
    /**
     * อายุครรภ์คิดเป็นสัปดาห์
     */
    public String anc_gravida_week= init;
    /**
     * ผลการตรวจ
     */
    public String anc_exam= init;
    /**
     * รายละเอียดผลการตรวจ
     */
    public String anc_exam_description = init;
    /**
     * รหัสภาวะเสี่ยงของหญิงตั้งครรภ์
     */
    public String birth_high_risk_id= init;
    /**
     * ผลการตรวจVDRL
     */
    public String anc_vdrl= init;
    /**
     * ผลการตรวจTHALASSAEMIA
     */
    public String anc_thalassemia= init;
    /**
     * ผลการตรวจHB
     */
    public String anc_hb= init;
    /**
     * ผลการตรวจHIV
     */
    public String anc_hiv= init;
    /**
     * ผลการตรวจHCT
     */
    public String anc_hct= init;
    /**
     * วันที่ตรวจHCT
     */
    public String anc_hct_date= init;
    /**
     * ผลการตรวจANCRES
     */
    public String anc_ancres= init;
    /**
     * รับวัคซีนTTหรือไม่
     */
    public String anc_tt= init;
    /**
     * รับวัคซีนTTหรือไม่
     */
    public String anc_dental_exam= init;
    /**
     * เหงือกอักเสบหรือไม่
     */
    public String anc_dental_gum= init;
    /**
     * มีหินน้ำลายหรือไม่
     */
    public String anc_dental_tartar= init;
    /**
     * ฝันผุ(จำนวนซี่)
     */
    public String anc_dental_caries= init;
    /**
     * น้ำหนัก(ก.ก.)
     */
    public String anc_weight= init;
    /**
     * ส่วนสูง(ซม.)
     */
    public String anc_high= init;
    /**
     * BMI
     */
    public String anc_bmi= init;
    /**
     * หมายเหตุ
     */
    public String anc_notice= init;
    /**
     * รหัสข้อมูลการเข้ารับบริการ
     */
    public String visit_id= init;
    /**
     * รหัสข้อมูลผู้ป่วย
     */
    public String patient_id= init;
    /**
     * เลข hn
     */
    public String anc_hn= init;
    /**
     * ผู้บันทึก
     */
    public String staff_record= init;
    /**
     * ผู้แก้ไข
     */
    public String staff_modify= init;
    /**
     * ผู้ยกเลิก
     */
    public String staff_cancel= init;
    /**
     * เวลาที่บันทึก
     */
    public String record_date_time= init;
    /**
     * วันที่แก้ไข
     */
    public String modify_date_time= init;
    /**
     * วันที่ยกเลิก
     */
    public String cancel_date_time= init;   
    /**
     * แสดงหรือไม่แสดง
     */
    public String active= init;
    /**
     * คลอดหรือยัง
     */
    public String delivery_status= init;
    /**
     * ความดัน
     */
    public String pressure = init;    
    /**
     * การตรวจครรภ์ครั้งที่
     */
    public String no = init;
    /**
     * อายุครรภ์หน่วยวัน
     */
    public String anc_gravida_day= init;
    /**
     * รหัสหมายเลขประชากร
     */
    public String family_id = init;
    /**
     * เด็กทารกเป็นธาราซิเมียหรือไม่
     */
    public String anc_baby_thalassemia ="0";
    /**
     * วันที่สำรวจ
     */
    public String survey_date = init;
    /**
     * ผล HCT เป็น %
     */
    public String anc_hct_result = init;
    /**
     * ชนิดวัคซีน TT ที่ได้รับ
     */
    public String anc_tt_type = init;
    
    /**
     * สถานที่รับบริการ anc
     * @author pu
     * @date 19/09/08
     * ยกเลิก 17/10/2551
     */
    public String anc_visit_office = init;
    
    public static final String ANC_OTHER_OFFICE = "0000";
    /** Creates a new instance of AncPcu */
    public AncPcu() {
    }
    
}
