/*
 * AncDetailPCu.java
 *
 * Created on 7 กรกฎาคม 2548, 10:56 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class AncDetailPcu extends Persistent{
    private static String init = "";
    /**
     * รหัสข้อมูลผู้ป่วย
     */
    public String patient_id= init;
    /**
     * รหัสการเข้ารับบริการ
     */
    public String visit_id= init;
    /**
     * รหัสก่อนคลอด
     */
    public String pregnancy_id= init;
    /**
     * มีน้ำตาลในเลือดหรือไม่
     */
    public String anc_detail_sugar= init;
    /**
     * มีอาการตะคิวหรือไม่
     */
    public String anc_detail_cramp= init;
    /**
     * ปวดศรีษะหรือไม่
     */
    public String anc_detail_headache= init;
    /**
     * ระดับมดลูก
     */
    public String anc_detail_fundus_height= init;
    /**
     * เด็กดิ้นหรือไม่
     */
    public String anc_detail_fetal_movement= init;
    /**
     * มีความเสี่ยงหรือไม่
     */
    public String anc_detail_risk= init;
    /**
     * มีเลือดออกช่องคลอดหรือไม่
     */
    public String anc_detail_vaginal_breeding= init;
    /**
     * ต่อมไทรอยด์โตหรือไม่
     */
    public String anc_detail_thyroid= init;
    /**
     * มีอาการโรคหัวใจหรือไม่
     */
    public String anc_detail_heart_disease= init;
    /**
     * มีอาการบวมของแม่หรือไม่
     */
    public String anc_detail_edema= init;
    /**
     * ได้ยินเสียงหัวใจเด็กหรือไม่
     */
    public String anc_detail_fetal_heart_sound= init;
    /**
     * ระดับปัสสาวะ
     */
    public String anc_detail_urine_alblumin= init;
    /**
     * ระดับอัลบลูมีน
     */
    public String anc_detail_albumin= init;
    /**
     * รหัสสถานะของท่าเด็กที่อยู่ในครรภ์
     */
    public String baby_status_id= init;
    /**
     * มีอาการคลื่นไส้หรือไม่
     */
    public String anc_detail_nausea= init;
    /**
     * รหัสส่วนนำ
     */
    public String conduct_id= init;
    /**
     * มีตกขาวหรือไม่
     */
    public String anc_detail_vaginal_discharge= init;    
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
     * วันที่บันทึก
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
     * t_health_anc
     */
    public String anc_id=init;
    /**
     * แสดงหรือไม่แสดง
     */
    public String active= init;    
    /**
     * ระดับการเต้นของหัวใจ
     */
    public String heart_rate = init;
    /**
     * ระดับมดลูก
     */
    public String anc_detail_uteruscm = init;
    /**
     * รหัสประะชากร
     */
    public String family_id= init;
    /** Creates a new instance of AncDetailPCu */
    public AncDetailPcu() {
    }
    
}
