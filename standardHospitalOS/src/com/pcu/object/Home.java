/*
 * Home.java
 *
 * Created on 11 มิถุนายน 2548, 10:14 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;

/**
 * ข้อมูลบ้าน
 * @author Administrator
 */
public class Home extends Persistent{
    private static String init = "";
    /**
     * รหัสสถานพยาบาล
     */
    public String office_id = init;
    /**
     * หมู่บ้าน
     */
    public String village_id = init;
    /**
     * เลขประจำบ้าน
     */
    public String home_number = init;
    /**
     * บ้านเลขที่
     */
    public String home_house = init;   
    /**
     * หมู่(เอามาจากVillage)
     */
    public String home_moo = init;
    /**
     * ถนน
     */
    public String home_road = init;   
    /**
     * ตำบล(เอามาจาก Village)
     */
    public String home_tambol = init;  
    /**
     * อำเภอ(เอามาจากVillage)
     */
    public String home_amphur = init;
    /**
     * จังหวัด(เอามาจากVillage)
     */
    public String home_changwat = init; 
    /**
     * จำนวนครอบครัว
     */
    public String family = init;
    /**
     * เขตที่ตั้ง
     */
    public String zone = init;
    /**
     * รหัส อสม.
     */
    public String volunteer = init;
    /**
     * ชื่อ อสม.
     */
    public String v_name = init;
    /**
     * รหัสเจ้าบ้าน
     */
    public String owner = init;
    /**
     * เบอร์โทรศัพท์บ้าน
     */
    public String owner_number = init;
    /**
     * ลักษณะบ้าน(id)
     */
    public String charactor_id = init;
    /**
     * ลักษณะชุมชน(id)
     */
    public String community_charac_id = init;    
    /**
     * ชื่อผู้บันทึก
     */
    public String home_staff_record = init; 
    /**
     * ชื่อผู้แก้ไข
     */
    public String home_staff_modify = init; 
    /**
     * ชื่อผู้ลบ
     */
    public String home_staff_cancel = init; 
    /**
     * เวลาบันทึก
     */
    public String home_record_date_time = init;
    /**
     * เวลาแก้ไข
     */
    public String home_modify_date_time = init; 
    /**
     * เวลาลบ
     */
    public String home_cancel_date_time = init;     
    /**
     * แสดงข้อมูล
     */
    public String active = "1";
    
    /*amp use for count home*/
    /**
     * พี่ amp ใช้
     */
    public String count_home = init;
    /**
     * พี่ amp ใช้
     */
    public String sum_family = init;
    
    /** Creates a new instance of Home */
    public Home() {
    }
    
}
