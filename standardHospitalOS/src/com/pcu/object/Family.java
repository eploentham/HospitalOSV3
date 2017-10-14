/*
 * Family.java
 *
 * Created on 3 กันยายน 2548, 10:55 น.
 *
 */

package com.pcu.object; 
import com.hospital_os.usecase.connection.Persistent;
/**
 * ข้อมูลครอบครัว
 * @author Jao
 */
public class Family extends Persistent{
    private static String init = "";    
    /**
     * รหัสบ้าน
     */
    public String home_id = init;
    /**
     * ครอบครัวที่
     */
    public String family_number = init;
    /**
     * เลขบัตรประชาชน
     */
    public String pid = init;
    /**
     * คำนำหน้า
     */
    public String f_prefix_id = init; 
    /**
     * ชื่อ
     */
    public String patient_name = init;
    /**
     * นามสกุล
     */
    public String patient_last_name = init;
    /**
     * วันเกิด
     */
    public String patient_birthday = init;
    /**
     * เกิดจริง
     */
    public String patient_birthday_true = init;
    /**
     * เพศ(id)
     */
    public String f_sex_id = init;
    /**
     * สถานะการสมรส(id)
     */
    public String marriage_status_id = init;
    /**
     * ระดับการศึกษา(id)
     */
    public String education_type_id = init;
    /**
     * อาชีพ(id)
     */
    public String occupation_id = init;
    /**
     * สัญชาติ(id)
     */
    public String nation_id = init;
    /**
     * เชื้อชาติ(id)
     */
    public String race_id = init;
    /**
     * ศาสนา(id)
     */
    public String religion_id = init;
    /**
     * สถานะ(id)เจ้าบ้าน ผู้อาศํย
     */
    public String status_id = init;
    /**
     * บิดา
     */
    public String father_firstname = init;
    /**
     * นามสกุลบิดา
     */
    public String father_lastname = init;
    /**
     * เลขบัตรประชาชนบิดา
     */
    public String father_pid = init;
    /**
     * ชื่อมารดา
     */
    public String mother_firstname = init;
    /**
     * นามสกุลมารดา
     */
    public String mother_lastname = init;
    /**
     * เลขที่บัตรประชาชนมารดา
     */
    public String mother_pid = init;
    /**
     * ชื่อคู่สมรส
     */
    public String couple_firstname = init;
    /**
     * นามสกุลคู่สมรส
     */
    public String couple_lastname = init;
    /**
     * เลขบัตรประชาชนคู่สมรส
     */
    public String couple_id = init;
    /**
     * สถานที่ทำงาน
     */
    public String work_office = init;
    /**
     * หมู่เลือด(id)
     */
    public String blood_group_id = init;
    /**
     * สถานะบุคคล
     */
    public String area_status_id = init;
    /**
     * วันที่เวลาบันทึก
     */
    public String record_date_time = init;
    /**
     * วันที่เวลาแก้ไข
     */
    public String modify_date_time = init;
    /**
     * วันเวลาที่ลบ
     */
    public String cancel_date_time = init;
    /**
     * ผู้บันทึก
     */
    public String staff_record = init;
    /**
     * ผู้แก้ไข
     */
    public String staff_modify = init;
    /**
     * ผู้ลบ
     */
    public String staff_cancel = init;
    /**
     * แสดงข้อมูลหรือไม่
     */
    public String active = "1";
    /**
     * เลข HN ของโปรแกรม HCIS
     */
    public String hn_hcis = init;
    /**
     * สถานะการจำหน่าย
     */
    public String discharge_status_id = init;
    /**
     * วันที่จำหน่าย
     */
    public String discharge_date_time = init;
    /**
     * วันที่ย้าย
     */
    public String move_in_date_time = init;
    /**
     * วันที่ย้าย
     */
    public String foreigner_card_no = init;
    public String labor = init;
    public String father_fid = init;
    public String mother_fid = init;
    public String couple_fid = init;
    
    /** Creates a new instance of Family */
    public Family() {
    }


    
}
