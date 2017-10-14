/*
 * AncDetail.java
 *
 * Created on 09 ธันวาคม 2547, 19:14 น.
 */
package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @deprecated ไม่ใช้แล้ว
 * @author  amp
 */
public class AncDetail extends Persistent {
    /** Creates a new instance of BeforeMchDetail */
    /** */
    /*public String before_mch_detail_id ; 
*/
    /** hn*/
    public String hn ; 
    /** vn*/
    public String vn ; 
    /** รหัสของ before mch id*/
    public String before_mch_id ; 
    /** นำตาลในปัสสาวะ*/
    public String suger ; 
    /** ปวดหัว*/
    public String headache ; 
    /** เด็กดิ้นหรือไม่*/
    public String moving ; 
    /** เลือดออกช่องคลอด*/
    public String vagina ; 
    /** โรคหัวใจ*/
    public String heart_failue ; 
    /** เสียงหัวใขเด็ก*/
    public String sound_heart ; 
    /** อัลบลูมิน*/
    public String alblumin ; 
    /** คลื่นไส้*/
    public String squeamish ; 
    /** ตกขาว*/
    public String menstruation ; 
    /** ตะคิว*/
    public String cramp ; 
    /** ระดับมดลูก*/
    public String uterus ; 
    /** ความเสี่ยง*/
    public String rish ; 
    /** ไทรอยด์โต*/
    public String thyroid ; 
    /** บวมของแม่*/
    public String swell ; 
    /** ระดับบัสสาวะ*/
    public String urine ; 
    /** ท่าเด็ก*/
    public String posture ; 
    /** ส่วนนำ*/
    public String conduct ; 
    /** คลอกหรือยัง*/
    public String give_birth ; 
    /** active*/
    public String active = "1"; 
    /** ผู้ปันทึก*/
    public String user_record =""; 
    /** ผู้แก้ไข*/
    public String user_modify = ""; 
    /** ผู้ยกเลิก*/
    public String user_cancel = "";
    public String anc_id ;
    public AncDetail() {
    }
    
}
