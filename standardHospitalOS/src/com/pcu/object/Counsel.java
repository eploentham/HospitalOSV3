/*
 * Counsel.java
 *
 * Created on 20 มิถุนายน 2548, 18:22 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Noom
 */
public class Counsel extends Persistent{
    private static String init = "";
    /*** รหัสข้อมูลการให้คำปรึกษา*/
    public String t_health_counsel_id = init;
    /*** ประเภทการเรื่องที่ให้คำปรึกษา*/
    public String b_health_counsel_type_id = init;
    /*** รายละเอียดการให้คำปรึกษา*/
    public String counsel_detail = init;
    /*** หมายเหตุการให้คำปรึกษา*/
    public String counsel_remark = init;
    /*** วันที่บันทึก*/
    public String counsel_record_time = init;
    /*** วันที่แก้ไข*/
    public String counsel_modify_time = init;
    /*** วันที่ยกเลิก*/
    public String counsel_cancle_time = init;
    /*** หมายเลข id ผู้ที่บันทึก*/
    public String counsel_staff_record =init;
    /*** หมายเลข id ผู้ที่แก้ไข*/
    public String counsel_staff_modify =init;
    /*** หมายเลข id ผู้ที่ยกเลิก*/
    public String counsel_staff_cancle =init;
    /*** สถานะการทำงาน*/
    public String counsel_active =init;
    /*** รหัสการรับบริการ*/
    public String visit_id = init;
    public String patient_id = init;
    /*** รหัสประชากร*/
    public String family_id  = init;
    /*** วันที่สำรวจ*/
    public String survey_date = init;
    /** Creates a new instance of Counsel */
    public Counsel() {
    }
    
}
