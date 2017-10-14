/*Source file: C:\\HospitalOS\\src\\com.hospital_os.object\\Constant.java
 */

package com.hospital_os.utility;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.PrintStream;
import com.hospital_os.usecase.connection.*;
import java.awt.Color;

/**
 * <b>Title:</b>        Constant<br>
 * <b>Description:</b><blockquote>
 * Contain constant in program
 * </blockquote>
 * <b>Copyright:</b>    Copyright (c) 2002<br>
 * <b>Company:</b>      4th Tier<br>
 * @author Surachai Thowong
 * @version 1.0 2002-01-17
 */
public class Constant extends ConstantMain {
    public static Cursor CUR_WAIT = new Cursor(Cursor.WAIT_CURSOR);
    public static Cursor CUR_DEFAULT = new Cursor(Cursor.DEFAULT_CURSOR);
    public static String PRINT_PATH_FILE = ".printpath.cfg";
    /** ตัวดักจับการ Double-Click */
    public static final int CLICK_INTERVAL = 300;
    public static int TIME_REFRESH = 60000;
    public static int TIME_UPDATE_STATUS = 3000;
    /**
     * Differ from Buddhist-Christian Era
     */
    public static final int ERA_DIFF = 543;
    public static final int YEAR_NOT_REACHABLE = 1800;
    public static final int MONTH_NOT_REACHABLE = 1;
    public static final int DATE_NOT_REACHABLE = 1;
    /**
     * CSV Convert
     * '\t' \u0009 HORIZONTAL TABULATION
     * '\n' \u000A NEW LINE
     * '\f' \u000C FORM FEED
     * '\r' \u000D CARRIAGE RETURN
     * ' '  \u0020 SPACE
     */
    public static String log_filename;
    public static final char SEPARATE = '\t';
    public static final char SEPARATE_TAB = '\t';
    public static final char NEW_LINE = '\n';
    
    /**
     * Constant Color
     */
    public static final Color COLOR_SUCCESS = new Color(0, 128, 0);
    public static final Color COLOR_NORMAL = new Color(102, 102, 153);
    public static final Color COLOR_WARNING = Color.black;
    public static final Color COLOR_ERROR = Color.red;
    public static final Color COLOR_BG_READ_ONLY = new Color(204, 204, 204);
    public static final Color COLOR_BG_EDITABLE = Color.white;
    public static final Color COLOR_BG_HEADER = new Color(159, 152, 207);
    public static final Color COLOR_FG_HEADER = Color.black;
    public static final Color COLOR_BG_CURRENT_VISIT = new Color(51, 153, 0);
    
    /** เลขที่ใบเสร็จ 10 หลัก */
    public static final int RECEIPT_NO = 10;
    
    public static final int CONTRACT_RUNNING_DIGIT = 3;
    public static final int PLAN_RUNNING_DIGIT = 3;
    /**
     * HN Format<br>
     * เลขจำนวนเต็ม(มี 0 นำหน้า) 9 หลัก ใช้สำหรับ รูปแบบ Running แบบที่ 1 เท่านั้น
     */
    public static final int HN_RUNNING_DIGIT = 9;
    /**
     * VN and AN Format
     * 0 = OPD
     * 1 = IPD
     * 0 + year + Running 7 digit from sequence vn_seq
     * 1 + year + Running 7 digit from sequence an_seq
     */
    public static final int VN_RUNNING_DIGIT = 7;
    public static final String VN_PREFIX = new String("0");
    public static final String AN_PREFIX = new String("1");
    
//   public static Logger logger = Logger.getLogger(Constant.class);
   /*
    * -1 fail format
    * 0 fail pattern
    * 1 right pattern
    */
    public static int isCorrectPID(String pid) {
        int a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13;
        int sum =0;
        int values =0;
        
        if(pid.length() !=13){
            return -1;
        }
        a13 = Integer.parseInt(pid.substring(0,1));
        a12 = Integer.parseInt(pid.substring(1,2));
        a11 = Integer.parseInt(pid.substring(2,3));
        a10 = Integer.parseInt(pid.substring(3,4));
        a9 = Integer.parseInt(pid.substring(4,5));
        a8 = Integer.parseInt(pid.substring(5,6));
        a7 = Integer.parseInt(pid.substring(6,7));
        a6 = Integer.parseInt(pid.substring(7,8));
        a5 = Integer.parseInt(pid.substring(8,9));
        a4 = Integer.parseInt(pid.substring(9,10));
        a3 = Integer.parseInt(pid.substring(10,11));
        a2 = Integer.parseInt(pid.substring(11,12));
        a1 = Integer.parseInt(pid.substring(12,13));
        
        sum = (13 * a13) + (12 *a12 ) + (11 * a11)+ (10 * a10) + ( 9 * a9 )+( 8 * a8 )+(7 * a7)+(6 * a6)+(5 * a5)+(4 * a4)+(3 * a3)+( 2 * a2);
        values = ((11 -  (sum%11)) % 10);
        if(values == a1)   return 1;
        else   return 0;
    } 
    public static String getTextBundle(String str) {
            return str;
    }
    public static String getTextBundleConfig(String str) {
        if(str.trim().equals(""))return "";
        try{
            return java.util.ResourceBundle.getBundle("com/hosv3/property/Config").getString(str);
        }catch(Exception e){
            // println(str + ":Not Found ");
            return str;
        }
    }
    public static String getStringSplitPipeAnd(String data,int datareturn) {
        String spiltPipe[] = data.split("&",2);
        return  spiltPipe[datareturn];
    }
    
    /**
     *ปัดเศษเพื่อให้ข้อมูลมีค่าเป็นเลขลงตัวและเป็น string
     **/
    public static String calBil(String da) {
        String data = "0";
        try{
            double d = Double.parseDouble(da);
            d= Math.ceil(d);
            data = String.valueOf(d);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        return data;
    }
    public static String getStaticText(String str){
        if(str.equals("฿")) return "__";
        if(str.equals("0")) return "__";
        if(str.equals("Dx :")) return "__";
        if(str.equals("Dx โดยเวชสถิติ")) return "__";
        if(str.equals("Dx:")) return "__";
        if(str.equals("null")) return "__";
        if(str.equals("VN")) return "__";
        if(str.equals("HN")) return "__";
        if(str.equals("กก.")) return "__";
        if(str.equals("กลุ่ม")) return "__";
        if(str.equals("กลุ่มย่อย")) return "__";
        if(str.equals("การ Refer ผู้ป่วย")) return "__";
        if(str.equals("การคืนยา")) return "__";
        if(str.equals("การเงิน")) return "__";
        if(str.equals("การเชื่อมต่อฐานข้อมูล")) return "__";
        if(str.equals("การตรวจร่างกาย")) return "__";
        if(str.equals("การนัดหมาย")) return "__";
        if(str.equals("การย้อนกลับ")) return "__";
        if(str.equals("การรับบริการ")) return "__";
        if(str.equals("การวินิจฉัย")) return "__";
        if(str.equals("การศึกษา")) return "__";
        if(str.equals("การสั่งตรวจ")) return "__";
        if(str.equals("การหายใจ")) return "__";
        if(str.equals("กำหนด path ของไฟล์งานพิมพ์")) return "__";
        if(str.equals("กำหนด path รูปแบบอักษร")) return "__";
        if(str.equals("เกี่ยวข้องเป็น")) return "__";
        if(str.equals("ข้อมูลการเกิดอุบัติเหตุ")) return "__";
        if(str.equals("ข้อมูลการตาย")) return "__";
        if(str.equals("ข้อมูลการแพ้ยา")) return "__";
        if(str.equals("ข้อมูลทั่วไป")) return "__";
        if(str.equals("ข้อมูลโปรแกรม")) return "__";
        if(str.equals("ข้อมูลผู้ป่วย")) return "__";
        if(str.equals("ข้อมูลโรคเฝ้าระวัง")) return "__";
        if(str.equals("ข้อมูลโรคเรื้อรัง")) return "__";
        if(str.equals("ข้อมูลอื่น")) return "__";
        if(str.equals("ค้นหา")) return "__";
        if(str.equals("ค้นหา ICD 10")) return "__";
        if(str.equals("ค้นหา ICD 9")) return "__";
        if(str.equals("ค้นหาจากเล่ม1-2(ชื่อโรคและรหัสโรค) ")) return "__";
        if(str.equals("ค้นหาจากเล่ม3 (เฉพาะชื่อโรค)")) return "__";
        if(str.equals("ค้นหาผู้ป่วย")) return "__";
        if(str.equals("ค้นหารายการสั่งตรวจ")) return "__";
        if(str.equals("ครั้ง")) return "__";
        if(str.equals("ครั้ง/นาที")) return "__";
        if(str.equals("ครั้ง/นาที")) return "__";
        if(str.equals("คลีนิก")) return "__";
        if(str.equals("ความดัน")) return "__";
        if(str.equals("ค้างบันทึก")) return "__";
        if(str.equals("คำเตือน")) return "__";
        if(str.equals("คำนำหน้า")) return "__";
        if(str.equals("คำอธิบาย")) return "__";
        if(str.equals("คิว")) return "__";
        if(str.equals("คุณสมบัติของ ICD 10")) return "__";
        if(str.equals("คุณสมบัติของ ICD 9")) return "__";
        if(str.equals("คุณสมบัติของ Order")) return "__";
        if(str.equals("เครื่องมือ")) return "__";
        if(str.equals("จับคู่คำนำหน้ากับเพศ")) return "__";
        if(str.equals("จ่าย")) return "__";
        if(str.equals("จำนวน")) return "__";
        if(str.equals("จำนวนที่จ่าย")) return "__";
        if(str.equals("จำนวนผู้ป่วยในจุดบริการ 1 คน")) return "__";
        if(str.equals("จำนวนวัสดุ ")) return "__";
        if(str.equals("จำหน่ายทางการเงิน")) return "__";
        if(str.equals("จำหน่ายทางการแพทย์")) return "__";
        if(str.equals("จุดบริการ")) return "__";
        if(str.equals("จุดบริการสุดท้าย")) return "__";
        if(str.equals("ชีพจร")) return "__";
        if(str.equals("ชื่อ")) return "__";
        if(str.equals("ชื่อคู่สมรส")) return "__";
        if(str.equals("ชื่อบิดา")) return "__";
        if(str.equals("ชื่อผู้ติดต่อ")) return "__";
        if(str.equals("ชื่อมารดา")) return "__";
        if(str.equals("ชื่อ-สกุล")) return "__";
        if(str.equals("เชื้อชาติ")) return "__";
        if(str.equals("ซม.")) return "__";
        if(str.equals("ดำเนินการ")) return "__";
        if(str.equals("ต่อเนื่อง")) return "__";
        if(str.equals("ตัวช่วย")) return "__";
        if(str.equals("ตำแหน่งในชุมชน")) return "__";
        if(str.equals("ถึงวันที่")) return "__";
        if(str.equals("แถบ")) return "__";
        if(str.equals("ทั้งหมด")) return "__";
        if(str.equals("ที่อยู่ผู้ติดต่อ")) return "__";
        if(str.equals("ที่อยู่ผู้ป่วย")) return "__";
        if(str.equals("นามสกุล")) return "__";
        if(str.equals("น้ำหนัก")) return "__";
        if(str.equals("บันทีก")) return "__";
        if(str.equals("บันทึก")) return "__";
        if(str.equals("บาท")) return "__";
        if(str.equals("ใบ Opd การ์ด")) return "__";
        if(str.equals("ใบ Summary")) return "__";
        if(str.equals("ใบรายการตรวจรักษาผู้ป่วย")) return "__";
        if(str.equals("ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ")) return "__";
        if(str.equals("ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ")) return "__";
        if(str.equals("ใบสรุปค่าใช้จ่ายตามรายการ")) return "__";
        if(str.equals("ประเภท")) return "__";
        if(str.equals("ประเภทคนต่างด้าว")) return "__";
        if(str.equals("ประวัติการคิดเงิน")) return "__";
        if(str.equals("ประวัติการรับบริการ")) return "__";
        if(str.equals("ประวัติการสั่งยาต่อเนื่อง")) return "__";
        if(str.equals("ประวัติรายการสั่งตรวจ")) return "__";
        if(str.equals("ปลดล็อก")) return "__";
        if(str.equals("ปันทึกอาการเจ็บป่วย")) return "__";
        if(str.equals("ปี")) return "__";
        if(str.equals("เป็นความลับ")) return "__";
        if(str.equals("ผู้ป่วยทั่วไป")) return "__";
        if(str.equals("ผู้ป่วยนอก")) return "__";
        if(str.equals("ผู้ป่วยใน")) return "__";
        if(str.equals("ผู้ป่วยในจุดบริการ")) return "__";
        if(str.equals("ผู้ป่วยรอจ่ายยา")) return "__";
        if(str.equals("ฝากนอน")) return "__";
        if(str.equals("พยาบาลผู้เข้าร่วม")) return "__";
        if(str.equals("พิมพ์ Opd การ์ด")) return "__";
        if(str.equals("พิมพ์ใบ Index")) return "__";
        if(str.equals("พิมพ์ใบ Index เอ็กซเรย์")) return "__";
        if(str.equals("พิมพ์ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ")) return "__";
        if(str.equals("พิมพ์ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ")) return "__";
        if(str.equals("พิมพ์ใบสั่งยา")) return "__";
        if(str.equals("พิมพ์ป้ายชื่อผู้ป่วย")) return "__";
        if(str.equals("พิมพ์ป้ายชื่อผู้ป่วยทุกคนในวอร์ด")) return "__";
        if(str.equals("พิมพ์ป้ายชื่อผู้ป่วยใน")) return "__";
        if(str.equals("พิมพ์ผลแลบ")) return "__";
        if(str.equals("พิมพ์สติ๊กเกอร์ยา")) return "__";
        if(str.equals("เพศ")) return "__";
        if(str.equals("แพทย์")) return "__";
        if(str.equals("แพทย์ประจำตัว")) return "__";
        if(str.equals("แพ้ยา")) return "__";
        if(str.equals("โภชนาการ")) return "__";
        if(str.equals("มุมมอง")) return "__";
        if(str.equals("โมดูลเสริม")) return "__";
        if(str.equals("ไม่มีผู้ป่วยในคิว")) return "__";
        if(str.equals("ยกเลิก")) return "__";
        if(str.equals("ยกเลิกการ Admit")) return "__";
        if(str.equals("ยกเลิกการเข้ารับบริการ")) return "__";
        if(str.equals("ยกเลิกการฝากนอน")) return "__";
        if(str.equals("ยอดค้างชำระ")) return "__";
        if(str.equals("ยอดค้างชำระรวม")) return "__";
        if(str.equals("ย้อนกลับการจำหน่ายทางการเงิน")) return "__";
        if(str.equals("ย้อนกลับการจำหน่ายทางการแพทย์")) return "__";
        if(str.equals("ยืนยัน")) return "__";
        if(str.equals("รหัส ICD 10")) return "__";
        if(str.equals("รหัส ICD9")) return "__";
        if(str.equals("ระบบ")) return "__";
        if(str.equals("ระบุกลุ่ม")) return "__";
        if(str.equals("ระบุคำค้น")) return "__";
        if(str.equals("ระหว่าง")) return "__";
        if(str.equals("รับชำระเงิน")) return "__";
        if(str.equals("ราคาค่าบริการ")) return "__";
        if(str.equals("รายการคิดเงิน")) return "__";
        if(str.equals("รายการตรวจ/รักษา")) return "__";
        if(str.equals("รายการตรวจรักษาของผู้ป่วย")) return "__";
        if(str.equals("รายการนัดทั้งหมด")) return "__";
        if(str.equals("รายการพิมพ์")) return "__";
        if(str.equals("รายการหัตถการ")) return "__";
        if(str.equals("รายงานต่างๆ")) return "__";
        if(str.equals("รายละเอียด")) return "__";
        if(str.equals("รีเฟรช")) return "__";
        if(str.equals("เริ่มการหัตถการ วันที่")) return "__";
        if(str.equals("ล็อก")) return "__";
        if(str.equals("ลำดับคิว")) return "__";
        if(str.equals("ลำดับเลข Sequence ล่าสุด")) return "__";
        if(str.equals("เลขที่บัตร")) return "__";
        if(str.equals("เลือกทั้งหมด")) return "__";
        if(str.equals("แลป")) return "__";
        if(str.equals("แลป Refer In")) return "__";
        if(str.equals("แลป Refer Out")) return "__";
        if(str.equals("วงเงิน")) return "__";
        if(str.equals("วันเกิดจริง")) return "__";
        if(str.equals("วันที่")) return "__";
        if(str.equals("วันที่ และ เวลาที่บันทึก")) return "__";
        if(str.equals("วันที่จำหน่าย")) return "__";
        if(str.equals("วันที่ย้ายเข้า")) return "__";
        if(str.equals("วันที่ลงรหัส")) return "__";
        if(str.equals("วันที่หมดอายุ")) return "__";
        if(str.equals("วันที่ออกบัตร")) return "__";
        if(str.equals("วิธีการใช้ยาแบบพิเศษ")) return "__";
        if(str.equals("เวลา")) return "__";
        if(str.equals("เวลาเริ่มรอจ่ายยา")) return "__";
        if(str.equals("ศาสนา")) return "__";
        if(str.equals("ส่งผู้ป่วยกลับวอร์ด")) return "__";
        if(str.equals("สถานพยาบาลรอง")) return "__";
        if(str.equals("สถานพยาบาลหลัก")) return "__";
        if(str.equals("สถานภาพ")) return "__";
        if(str.equals("สถานะ")) return "__";
        if(str.equals("สถานะการจำหน่าย")) return "__";
        if(str.equals("สถานะบุคคล")) return "__";
        if(str.equals("ส่วนสูง")) return "__";
        if(str.equals("สั่งชุดรายการตรวจรักษา")) return "__";
        if(str.equals("สั่งยาต่อเนื่อง")) return "__";
        if(str.equals("สั่งรายการเหมือนครั้งที่แล้ว")) return "__";
        if(str.equals("สั่งรายการเหมือนวันที่แล้ว")) return "__";
        if(str.equals("สัญชาติ")) return "__";
        if(str.equals("สำหรับพยาบาล")) return "__";
        if(str.equals("สำหรับแพทย์")) return "__";
        if(str.equals("สิทธิการรักษา")) return "__";
        if(str.equals("สิทธิ")) return "__";
        if(str.equals("เสร็จสิ้นการหัตถการ วันที่")) return "__";
        if(str.equals("แสดงตัวอย่างการพิมพ์ใบ Opd การ์ด")) return "__";
        if(str.equals("แสดงตัวอย่างการพิมพ์ใบ Summary")) return "__";
        if(str.equals("แสดงตัวอย่างการพิมพ์ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ")) return "__";
        if(str.equals("แสดงตัวอย่างการพิมพ์ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ")) return "__";
        if(str.equals("แสดงตัวอย่างการพิมพ์ใบสรุปค่าใช้จ่ายตามรายการ")) return "__";
        if(str.equals("แสดงตัวอย่างการพิมพ์รายการตรวจรักษา")) return "__";
        if(str.equals("แสดงตัวอย่างใบสั่งยา")) return "__";
        if(str.equals("หมายเลขบัตรคู่สมรส")) return "__";
        if(str.equals("หมายเลขบัตรบิดา")) return "__";
        if(str.equals("หมายเลขบัตรประชาชน")) return "__";
        if(str.equals("หมายเลขบัตรมารดา")) return "__";
        if(str.equals("หมายเลขเอ็กซเรย์ (XN)")) return "__";
        if(str.equals("หมายเหตุ")) return "__";
        if(str.equals("หมู่เลือด")) return "__";
        if(str.equals("ออกจากระบบ")) return "__";
        if(str.equals("อาการเบื้องต้น")) return "__";
        if(str.equals("อาการปัจจุบัน")) return "__";
        if(str.equals("อาการสำคัญ")) return "__";
        if(str.equals("อาชีพ")) return "__";
        if(str.equals("อายุ")) return "__";
        if(str.equals("อุณหภูมิ")) return "__";
        if(str.equals("เอ็กซเรย์")) return "__";
        if(str.equals("null")) return "__";
        if(str.equals("ข้อมูลการเข้ารับบริการ")) return "__";
        if(str.equals("ข้อมูลการแพ้ยา")) return "__";
        if(str.equals("ข้อมูลทั่วไป")) return "__";
        if(str.equals("ข้อมูลอื่น")) return "__";
        if(str.equals("ค้นหา ICD 10")) return "__";
        if(str.equals("ค้นหา ICD 9")) return "__";
        if(str.equals("ค้นหารายการสั่งตรวจ")) return "__";
        if(str.equals("คุณสมบัติของ ICD 10")) return "__";
        if(str.equals("คุณสมบัติของ ICD 9")) return "__";
        if(str.equals("คุณสมบัติของ Order")) return "__";
        if(str.equals("จำนวนผู้ป่วยในจุดบริการ 2 คน")) return "__";
        if(str.equals("ที่อยู่ผู้ติดต่อ")) return "__";
        if(str.equals("ที่อยู่ผู้ป่วย")) return "__";
        if(str.equals("ผู้ป่วยในจุดบริการ")) return "__";
        if(str.equals("ผู้ป่วยรอจ่ายยา")) return "__";
        if(str.equals("รายการคิดเงิน")) return "__";
        if(str.equals("รายการตรวจรักษาของผู้ป่วย")) return "__";
        if(str.equals("รายการสิทธิการรักษา")) return "__";
        if(str.equals("รายการหัตถการ")) return "__";
        if(str.equals("รายละเอียด")) return "__";
        if(str.equals("สำหรับพยาบาล")) return "__";
        if(str.equals("สำหรับแพทย์")) return "__";
        if(str.equals("สิทธิการรักษา")) return "__";
        if(str.equals("สิทธิการักษาที่ใช้")) return "__";
        if(str.equals("สิทธิประจำตัวผู้ป่วย")) return "__";
        if(str.equals("อาการเบื้องต้น")) return "__";
        if(str.equals("...")) return "__";
        if(str.equals("1.ข้อมูลผู้ป่วย")) return "__";
        if(str.equals("2.การรับบริการ")) return "__";
        if(str.equals("3.บันทึกอาการเจ็บป่วย")) return "__";
        if(str.equals("4.รายการตรวจ/รักษา")) return "__";
        if(str.equals("5.การวินิจฉัย")) return "__";
        if(str.equals("6.การเงิน")) return "__";
        if(str.equals("Admit")) return "__";
        if(str.equals("AN")) return "__";
        if(str.equals("C")) return "__";
        if(str.equals("CANCEL")) return "__";
        if(str.equals("Conferm")) return "__";
        if(str.equals("Diagnosis")) return "__";
        if(str.equals("Dx: ")) return "__";
        if(str.equals("Groupname")) return "__";
        if(str.equals("Groupname")) return "__";
        if(str.equals("mmHg")) return "__";
        if(str.equals("NAME")) return "__";
        if(str.equals("OK")) return "__";
        if(str.equals("Ongoing")) return "__";
        if(str.equals("Price")) return "__";
        if(str.equals("Requested")) return "__";
        if(str.equals("Save")) return "__";
        if(str.equals("Status")) return "__";
        if(str.equals("Unit")) return "__";
        if(str.equals("Visit")) return "__";
        if(str.equals("Vital Sign")) return "__";
        if(str.equals("การลงรหัส ICD-10")) return "__";
        if(str.equals("การลงรหัส ICD-9")) return "__";
        if(str.equals("คงค้าง")) return "__";
        if(str.equals("คน")) return "__";
        if(str.equals("ค้างชำระ")) return "__";
        if(str.equals("คืนยา")) return "__";
        if(str.equals("เครื่องแม่ข่าย")) return "__";
        if(str.equals("จังหวัด")) return "__";
        if(str.equals("จ่ายแล้ว")) return "__";
        if(str.equals("จำนวนผู้ป่วยในจุดบริการ ")) return "__";
        if(str.equals("จุดที่ผ่านมา")) return "__";
        if(str.equals("จุดปัจจุบัน")) return "__";
        if(str.equals("ชื่อฐานข้อมูล")) return "__";
        if(str.equals("ชื่อผู้ใช้")) return "__";
        if(str.equals("ชื่อยา")) return "__";
        if(str.equals("ดูรายการที่ยกเลิก")) return "__";
        if(str.equals("ตำบล")) return "__";
        if(str.equals("เตียง")) return "__";
        if(str.equals("ถนน")) return "__";
        if(str.equals("โทรศัพท์")) return "__";
        if(str.equals("โทรศัพท์มือถือ")) return "__";
        if(str.equals("นำหน้า")) return "__";
        if(str.equals("ใบแจ้งหนี้")) return "__";
        if(str.equals("ปฏิเสธแพ้ยา")) return "__";
        if(str.equals("ประกอบ")) return "__";
        if(str.equals("ผลการตรวจ")) return "__";
        if(str.equals("ผู้ป่วยชำระ")) return "__";
        if(str.equals("ผู้ป่วยตั้งครรภ์")) return "__";
        if(str.equals("พอร์ต")) return "__";
        if(str.equals("พิมพ์ OPD การ์ด")) return "__";
        if(str.equals("ไม่มีผู้ป่วยในจุดบริการ")) return "__";
        if(str.equals("ไม่มีแลบ")) return "__";
        if(str.equals("ไม่ระบุ")) return "__";
        if(str.equals("ยาชุด")) return "__";
        if(str.equals("ยาเดิม")) return "__";
        if(str.equals("รหัส")) return "__";
        if(str.equals("รหัสผ่าน")) return "__";
        if(str.equals("รอผล")) return "__";
        if(str.equals("รับมาจาก รพ.")) return "__";
        if(str.equals("รายการชำระเงิน")) return "__";
        if(str.equals("รายการแลบ")) return "__";
        if(str.equals("รายชื่อตามจุดบริการ")) return "__";
        if(str.equals("รายชื่อในวอร์ด")) return "__";
        if(str.equals("เรียกดูข้อมูล")) return "__";
        if(str.equals("ลำดับ")) return "__";
        if(str.equals("เลขที่")) return "__";
        if(str.equals("วันที่ Admit")) return "__";
        if(str.equals("วันที่คิดเงิน")) return "__";
        if(str.equals("วันและเวลาที่บันทึก")) return "__";
        if(str.equals("สกุล")) return "__";
        if(str.equals("ส่งไปยัง")) return "__";
        if(str.equals("ส่งผู้ป่วย")) return "__";
        if(str.equals("ส่วนของร่างกาย")) return "__";
        if(str.equals("ส่วนลด")) return "__";
        if(str.equals("สิทธิ์")) return "__";
        if(str.equals("สิทธิชำระ")) return "__";
        if(str.equals("สิทธิทั้งหมด")) return "__";
        if(str.equals("เสียชีวิต")) return "__";
        if(str.equals("หมู่ที่")) return "__";
        if(str.equals("หอพักผู้ป่วยใน")) return "__";
        if(str.equals("อาการที่แพ้")) return "__";
        if(str.equals("อำเภอ")) return "__";
        println("eBundle not found:" + str);
        return str;
    }
    
    public static String calculateBMI(String weight,String hight) {
        float bmi = 0;
        if(!weight.equals("") && !hight.equals("")){
            float w = Float.parseFloat(weight);
            float h = Float.parseFloat(hight);
            bmi = (w*10000)/(h*h);
        }
        return Constant.dicimal(String.valueOf(bmi));
    }
    public static void setEnabled(Component c, boolean b){
        if(c.isEnabled())
            c.setEnabled(b);
    }
    
    
    
    public static boolean checkModulePrinting() {
        try{
            Class.forName("com.printing.gui.PrintingFrm");
            return true;
        } catch(ClassNotFoundException ex) {
            return false;
        }
    }
    
    public static String convertSQLToMySQL(String sql,String typeDatabase) {
        if(typeDatabase.equalsIgnoreCase("2")) {
            println("Pass ConvertSQL To MySQL");
            return IOStream.Unicode2ASCII(sql);
            
        } else
            return sql;
    }
    
    public static String getTextBundleImage(String str) {
        if(str.trim().equals(""))return "";
        try{
            return java.util.ResourceBundle.getBundle("com/hospital_os/property/image").getString(str);
        }catch(Exception e){
            // println(str + ":Not Found ");
            return str;
        }
    }
    
    /**  ใช้คำนวณหาทดศนิยม 2 ตำแหน่ง โดยไม่มีการปัด
     */
    public static String dicimal(String num) {   int dic = 2;
    if(num ==null)
        return "0";
    
    try{
        Double.parseDouble(num);
    } catch(Exception ex) {
        return "0";
    }
    //ทดศนิยมต้องมากกว่า 2 ตำแหน่ง
    if(dic < 1)
        dic = 2;
    
    java.text.DecimalFormat d=new  java.text.DecimalFormat();
    String dicimals = "";
    int mul = 1;
    
    
    for(int i =0 ;i < dic ; i++) {
        dicimals = dicimals + "0";
        mul = mul*10;
    }
    d.applyPattern(dicimals);
    
    String re = String.valueOf(num);
    String fon = "0";
    //ตรวจสอบว่าเป้นจำนวนเต็มหรือทดสนิยม
    try{
        fon = re.substring(0,re.indexOf("."));
    } catch(Exception ex) {
        re = re+ ".0000";
        fon = re.substring(0,re.indexOf("."));
    }
    String sec = new String();
    String sum = new String();
    // println(re);
    //นำข้อมูลหลังจุดทอสนิยม
    re = re.substring(re.indexOf(".")+1) + "0000";
    
    int nu = 0;
    //ตรวจสอบว่าความยาวนั้นมากกว่า 2 หรือไม่
    if(re.length() >2) {
        //นำทดสนิยมตำแหน่งที่ 3 มาใช้
        sec = re.substring(dic,dic+1);
        nu = Integer.parseInt(sec);
    }
    // println(re);
    //นำทดสนิยมตำแหน่งที่ 1และ 2 มาเก็บไว้เพื่อพิจารณา
    int un = Integer.parseInt(re.substring(0,dic));
    //  println(nu);
    //   println(un);
    //ถ้าตำแหน่งที่ 3 มีค่ามากกว่า 5 ให้ปัดขึ้น
    //ถ้าน้อยกว่าก็ให้คงเกิม
    
    if(nu >=5)
        un = un + 1;
    if(un >=1.0*mul) {
        int f = Integer.parseInt(fon);
        f = f+1;
        fon = String.valueOf(f);
        un = 0;
    }
    
    sum = fon + "."+ d.format(un);
    re = null;
    fon = null;
    sec = null;
    d=null;
    return sum;
    }
    /*กรองการพิมพ์ตัวอักษรในช่องว่างให้ไม่เกินจำนวนที่กำหนด*/
    public static void filterTextKey(javax.swing.JTextField jtf, int num) {
        if(jtf.getText().length()>num)
            jtf.setText(jtf.getText().substring(1,num+1));
        //if(jtf.getText().length()==num)
        //  jtf.transferFocus();
    }
    /**  ใช้คำนวณหาทดศนิยม 2 ตำแหน่ง โดยมีการปัดเป็น 1 บาท ถ้าได้ทศนิยมระหว่าง 0.01-0.99
     */
    public static String dicimalCalMoney(String num) {
        int dic = 2;
        if(num ==null)
            return "0";
        try{
            Double.parseDouble(num);
        } catch(Exception ex) {
            return "0";
        }
        //ทดศนิยมต้องมากกว่า 2 ตำแหน่ง
        if(dic < 1)
            dic = 2;
        
        java.text.DecimalFormat d=new  java.text.DecimalFormat();
        String dicimals = "";
        int mul = 1;
        
        
        for(int i =0 ;i < dic ; i++) {
            dicimals = dicimals + "0";
            mul = mul*10;
        }
        d.applyPattern(dicimals);
        
        String re = String.valueOf(num);
        String fon = "0";
        //ตรวจสอบว่าเป้นจำนวนเต็มหรือทดสนิยม
        try{
            fon = re.substring(0,re.indexOf("."));
        } catch(Exception ex) {
            re = re+ ".0000";
            fon = re.substring(0,re.indexOf("."));
        }
        String sec = new String();
        String sum = new String();
        //   println(re);
        //นำข้อมูลหลังจุดทอสนิยม
        re = re.substring(re.indexOf(".")+1) + "0000";
        
        int nu = 0;
        //ตรวจสอบว่าความยาวนั้นมากกว่า 2 หรือไม่
        if(re.length() >2) {
            //นำทดสนิยมตำแหน่งที่ 3 มาใช้
            sec = re.substring(dic,dic+1);
            nu = Integer.parseInt(sec);
        }
        //นำทดสนิยมตำแหน่งที่ 1และ 2 มาเก็บไว้เพื่อพิจารณา
        int un = Integer.parseInt(re.substring(0,dic));
        //ถ้าตำแหน่งที่ 3 มีค่ามากกว่า 5 ให้ปัดขึ้น
        //ถ้าน้อยกว่าก็ให้คงเกิม
        
        if(nu >=5)
            un = un + 1;
        String ddd = "0.0";
        
        if(un < 0.1*mul)
            ddd = String.valueOf(0.0*mul);
        else
            ddd = String.valueOf(1.00*mul);
        un = Integer.parseInt(ddd.substring(0,ddd.indexOf(".")));
        
        if(un >=1.0*mul) {
            int f = Integer.parseInt(fon);
            f = f+1;
            fon = String.valueOf(f);
            un = 0;
        }
        
        sum = fon + "."+ d.format(un);
        ddd = null;
        re = null;
        fon = null;
        sec = null;
        d=null;
        return sum;
        
    }
    
    /**  คำนวณเฉพาะราคายังใช้กับการคิดเงินไม่ได้
     */
    public static String dicimalMoney(String num) {
        if(num ==null)
            return "0";
        
        try{
            Double.parseDouble(num);
        } catch(Exception ex) {
            return "0";
        }
        
        java.text.DecimalFormat d=new  java.text.DecimalFormat();
        d.applyPattern("00");
        
        String re = String.valueOf(num);
        String fon = "0";
        //ตรวจสอบว่าเป้นจำนวนเต็มหรือทดสนิยม
        try{
            fon = re.substring(0,re.indexOf("."));
        } catch(Exception ex) {
            re = re+ ".0000";
            fon = re.substring(0,re.indexOf("."));
        }
        String sec = new String();
        String sum = new String();
        //นำข้อมูลหลังจุดทอสนิยม
        re = re.substring(re.indexOf(".")+1) + "0000";
        
        int nu = 0;
        //ตรวจสอบว่าความยาวนั้นมากกว่า 2 หรือไม่
        if(re.length() >2) {
            //นำทดสนิยมตำแหน่งที่ 3 มาใช้
            sec = re.substring(2,3);
            nu = Integer.parseInt(sec);
        }
        //println(re);
        //นำทดสนิยมตำแหน่งที่ 1และ 2 มาเก็บไว้เพื่อพิจารณา
        int un = Integer.parseInt(re.substring(0,2));
        //  println(nu);
        //   println(un);
        //ถ้าตำแหน่งที่ 3 มีค่ามากกว่า 5 ให้ปัดขึ้น
        //ถ้าน้อยกว่าก็ให้คงเกิม
        if(nu >=5)
            un = un + 1;
        
        //ตรวจสอบว่ามากกว่า 100 หรือเปล่าถ้ามากกว่า ให้ปัดขึ้น
        //โดยการบวกจากค่าหน้าจุด
        if(un >100) {
            int f = Integer.parseInt(fon);
            f = f+1;
            fon = String.valueOf(f);
            un = 0;
        }
        
        sum = fon + "."+ d.format(un);
        re = null;
        fon = null;
        sec = null;
        d=null;
        return sum;
        
    }
    
    public static String dicimalNotrim(String num) {
        try{
            Double.parseDouble(num);
        } catch(Exception ex) {
            return "0";
        }
        
        String decimal;
        decimal = num.substring(num.indexOf(".") + 1 );
        int deci = 0;
        deci = decimal.length();
        for(int i = deci-1 ;i >= 0 ;i--) {
            
        }
        // println(value);
        // println(decimal);
        return "";
    }
    
    public static String generateOid(String table_id,String hos_id) throws Exception{
        if(table_id.length()!=3)
            throw new Exception("table_id must be 3 digits:" + table_id);
        if(hos_id.length()!=5)
            throw new Exception("hospital_id must be 5 digits:" + table_id);
        Integer.parseInt(table_id);
        Integer.parseInt(hos_id);
        String id = table_id + hos_id + System.currentTimeMillis();
        return id;
    }
    
    public static String calculateDecimal(String s){
        String str;
        String [] aStr;
        if(s.equals("")){
            return s;
        }
        str = s;
        aStr = str.replace('.', '@').split("@");
        if(aStr.length == 2){
            if(aStr[1].length() == 2){
                str = aStr[0]+"."+aStr[1];
            } else{
                aStr[1] = aStr[1]+0;
                str = aStr[0]+"."+aStr[1];
            }
        } else{
            str = str+".00";
        }
        return str;
    }
    //test Commit to CVS
    public static String getSDouble(String str) {
        try{
            Double.parseDouble(str);
            return str;
        } catch(Exception e){
            return "0";
        }
    }
    /**
     * pongtorn add
     * สำหรับการบวกตัวเลขที่เป็น string จะได้ไม่ต้องแปลงไปมาวุ่นวายเสียเวลา
     * 18/4/2549 henbe modify
     */
    public static double addStringNumber(double number1,String str2) {
        double number2 = 0;
        try{
            number2 = Double.parseDouble(str2);
        }catch(Exception e){}
        return number1+number2;
    }
 /*pongtorn add
  *สำหรับการบวกตัวเลขที่เป็น string จะได้ไม่ต้องแปลงไปมาวุ่นวายเสียเวลา
  * 18/4/2549 henbe modify
  */
    public static String addStringNumber(String str1,String str2) {
        double number1 = 0;
        double number2 = 0;
        try{
            number1 = Integer.parseInt(str1);
        }catch(Exception e){}
        try{
            number2 = Integer.parseInt(str2);
        }catch(Exception e){}
        return String.valueOf(number1+number2);
    }
    /**
     * @author Pongtorn (Henbe)
     * แปลง string เป็น double เอามาจากหน้า xray
     * เพื่อให้หน้า gui ไม่ต้องมี try catch
     */
    public static double toDouble(String value) {
        double dvalue =0.0;
        try {
            dvalue  = Double.parseDouble(value);
        } catch(Exception ex) {
            println(ex.getMessage());
        }
        return dvalue;
    }
  /*
   *@Author amp
   *@date 27/04/2549
   *@see คำนวณระดับโภชนาการ ได้สูตรจาก HCIS
   */
    public static int calculateIndexComboBoxNutrition(String sex,String months,String weights) {
        int index = 5;
        if("1".equals(sex))//เพศชาย
        {
            if("".equals(weights)) {
                return index = 5;
            }
            int month = 0;
            float weight = 0;
            month = Integer.parseInt(months);
            weight = Float.parseFloat(weights);
            switch(month) {
                case 0 :    if(weight < 2.7) index = 4;
                else if(weight >= 2.7 && weight < 2.8) index = 3;
                else if(weight >= 2.8 && weight < 4.0) index = 2;
                else if(weight >= 4.0 && weight < 4.1) index = 1;
                else if(weight >= 4.1) index = 0;
                break;
                case 1 :    if(weight < 3.3) index = 4;
                else if(weight >= 3.3 && weight < 3.4) index = 3;
                else if(weight >= 3.4 && weight < 4.8) index = 2;
                else if(weight >= 4.8 && weight < 5.1) index = 1;
                else if(weight >= 5.1) index = 0;
                break;
                case 2 :    if(weight < 3.9) index = 4;
                else if(weight >= 3.9 && weight < 4.2) index = 3;
                else if(weight >= 4.2 && weight < 5.6) index = 2;
                else if(weight >= 5.6 && weight < 5.9) index = 1;
                else if(weight >= 5.9) index = 0;
                break;
                case 3 :    if(weight < 4.5) index = 4;
                else if(weight >= 4.5 && weight < 4.8) index = 3;
                else if(weight >= 4.8 && weight < 6.5) index = 2;
                else if(weight >= 6.5 && weight < 6.8) index = 1;
                else if(weight >= 6.8) index = 0;
                break;
                case 4 :    if(weight < 5.0) index = 4;
                else if(weight >= 5.0 && weight < 5.3) index = 3;
                else if(weight >= 5.3 && weight < 7.2) index = 2;
                else if(weight >= 7.2 && weight < 7.5) index = 1;
                else if(weight >= 7.5) index = 0;
                break;
                case 5 :    if(weight < 5.5) index = 4;
                else if(weight >= 5.5 && weight < 5.8) index = 3;
                else if(weight >= 5.8 && weight < 7.9) index = 2;
                else if(weight >= 7.9 && weight < 8.2) index = 1;
                else if(weight >= 8.2) index = 0;
                break;
                case 6 :    if(weight < 6.0) index = 4;
                else if(weight >= 6.0 && weight < 6.3) index = 3;
                else if(weight >= 6.3 && weight < 8.5) index = 2;
                else if(weight >= 8.5 && weight < 8.9) index = 1;
                else if(weight >= 8.9) index = 0;
                break;
                case 7 :    if(weight < 6.4) index = 4;
                else if(weight >= 6.4 && weight < 6.8) index = 3;
                else if(weight >= 6.8 && weight < 9.1) index = 2;
                else if(weight >= 9.1 && weight < 9.5) index = 1;
                else if(weight >= 9.5) index = 0;
                break;
                case 8 :    if(weight < 6.8) index = 4;
                else if(weight >= 6.8 && weight < 7.2) index = 3;
                else if(weight >= 7.2 && weight < 9.6) index = 2;
                else if(weight >= 9.6 && weight < 10.0) index = 1;
                else if(weight >= 10.0) index = 0;
                break;
                case 9 :    if(weight < 7.2) index = 4;
                else if(weight >= 7.2 && weight < 7.6) index = 3;
                else if(weight >= 7.6 && weight < 10.0) index = 2;
                else if(weight >= 10.0 && weight < 10.4) index = 1;
                else if(weight >= 10.4) index = 0;
                break;
                case 10 :   if(weight < 7.5) index = 4;
                else if(weight >= 7.5 && weight < 7.9) index = 3;
                else if(weight >= 7.9 && weight < 10.4) index = 2;
                else if(weight >= 10.4 && weight < 10.8) index = 1;
                else if(weight >= 10.8) index = 0;
                break;
                case 11 :   if(weight < 7.7) index = 4;
                else if(weight >= 7.7 && weight < 8.1) index = 3;
                else if(weight >= 8.1 && weight < 10.7) index = 2;
                else if(weight >= 10.7 && weight < 11.2) index = 1;
                else if(weight >= 11.2) index = 0;
                break;
                case 12 :   if(weight < 7.9) index = 4;
                else if(weight >= 7.9 && weight < 8.3) index = 3;
                else if(weight >= 8.3 && weight < 11.1) index = 2;
                else if(weight >= 11.1 && weight < 11.6) index = 1;
                else if(weight >= 11.6) index = 0;
                break;
                case 13 :   if(weight < 8.1) index = 4;
                else if(weight >= 8.1 && weight < 8.5) index = 3;
                else if(weight >= 8.5 && weight < 11.4) index = 2;
                else if(weight >= 11.4 && weight < 11.9) index = 1;
                else if(weight >= 11.9) index = 0;
                break;
                case 14 :   if(weight < 8.3) index = 4;
                else if(weight >= 8.3 && weight < 8.7) index = 3;
                else if(weight >= 8.7 && weight < 11.8) index = 2;
                else if(weight >= 11.8 && weight < 12.3) index = 1;
                else if(weight >= 12.3) index = 0;
                break;
                case 15 :   if(weight < 8.4) index = 4;
                else if(weight >= 8.4 && weight < 8.9) index = 3;
                else if(weight >= 8.9 && weight < 12.1) index = 2;
                else if(weight >= 12.1 && weight < 12.6) index = 1;
                else if(weight >= 12.6) index = 0;
                break;
                case 16 :   if(weight < 8.6) index = 4;
                else if(weight >= 8.6 && weight < 9.1) index = 3;
                else if(weight >= 9.1 && weight < 12.4) index = 2;
                else if(weight >= 12.4 && weight < 12.9) index = 1;
                else if(weight >= 12.9) index = 0;
                break;
                case 17 :   if(weight < 8.8) index = 4;
                else if(weight >= 8.8 && weight < 9.3) index = 3;
                else if(weight >= 9.3 && weight < 12.7) index = 2;
                else if(weight >= 12.7 && weight < 13.2) index = 1;
                else if(weight >= 13.2) index = 0;
                break;
                case 18 :   if(weight < 8.9) index = 4;
                else if(weight >= 8.9 && weight < 9.4) index = 3;
                else if(weight >= 9.4 && weight < 13.0) index = 2;
                else if(weight >= 13.0 && weight < 13.7) index = 1;
                else if(weight >= 13.7) index = 0;
                break;
                case 19 :   if(weight < 9.1) index = 4;
                else if(weight >= 9.1 && weight < 9.6) index = 3;
                else if(weight >= 9.6 && weight < 13.3) index = 2;
                else if(weight >= 13.3 && weight < 14.0) index = 1;
                else if(weight >= 14.0) index = 0;
                break;
                case 20 :   if(weight < 9.3) index = 4;
                else if(weight >= 9.3 && weight < 9.8) index = 3;
                else if(weight >= 9.8 && weight < 13.6) index = 2;
                else if(weight >= 13.6 && weight < 14.3) index = 1;
                else if(weight >= 14.3) index = 0;
                break;
                case 21 :   if(weight < 9.4) index = 4;
                else if(weight >= 9.4 && weight < 9.9) index = 3;
                else if(weight >= 9.9 && weight < 13.9) index = 2;
                else if(weight >= 13.9 && weight < 14.6) index = 1;
                else if(weight >= 14.6) index = 0;
                break;
                case 22 :   if(weight < 9.6) index = 4;
                else if(weight >= 9.6 && weight < 10.2) index = 3;
                else if(weight >= 10.2 && weight < 14.1) index = 2;
                else if(weight >= 14.1 && weight < 14.8) index = 1;
                else if(weight >= 14.8) index = 0;
                break;
                case 23 :   if(weight < 9.6) index = 4;
                else if(weight >= 9.6 && weight < 10.3) index = 3;
                else if(weight >= 10.3 && weight < 14.3) index = 2;
                else if(weight >= 14.3 && weight < 15.0) index = 1;
                else if(weight >= 15.0) index = 0;
                break;
                case 24 :   if(weight < 9.8) index = 4;
                else if(weight >= 9.8 && weight < 10.5) index = 3;
                else if(weight >= 10.5 && weight < 14.5) index = 2;
                else if(weight >= 14.5 && weight < 15.2) index = 1;
                else if(weight >= 15.2) index = 0;
                break;
                case 25 :   if(weight < 9.9) index = 4;
                else if(weight >= 9.9 && weight < 10.6) index = 3;
                else if(weight >= 10.6 && weight < 14.7) index = 2;
                else if(weight >= 14.7 && weight < 15.4) index = 1;
                else if(weight >= 15.4) index = 0;
                break;
                case 26 :   if(weight < 10.1) index = 4;
                else if(weight >= 10.1 && weight < 10.8) index = 3;
                else if(weight >= 10.8 && weight < 14.9) index = 2;
                else if(weight >= 14.9 && weight < 15.6) index = 1;
                else if(weight >= 15.6) index = 0;
                break;
                case 27 :   if(weight < 10.2) index = 4;
                else if(weight >= 10.2 && weight < 10.9) index = 3;
                else if(weight >= 10.9 && weight < 15.2) index = 2;
                else if(weight >= 15.2 && weight < 15.9) index = 1;
                else if(weight >= 15.9) index = 0;
                break;
                case 28 :   if(weight < 10.3) index = 4;
                else if(weight >= 10.3 && weight < 11.0) index = 3;
                else if(weight >= 11.0 && weight < 15.5) index = 2;
                else if(weight >= 15.5 && weight < 16.2) index = 1;
                else if(weight >= 16.2) index = 0;
                break;
                case 29 :   if(weight < 10.5) index = 4;
                else if(weight >= 10.5 && weight < 11.2) index = 3;
                else if(weight >= 11.2 && weight < 15.7) index = 2;
                else if(weight >= 15.7 && weight < 16.4) index = 1;
                else if(weight >= 16.4) index = 0;
                break;
                case 30 :   if(weight < 10.6) index = 4;
                else if(weight >= 10.6 && weight < 11.4) index = 3;
                else if(weight >= 11.4 && weight < 15.9) index = 2;
                else if(weight >= 15.9 && weight < 16.7) index = 1;
                else if(weight >= 16.7) index = 0;
                break;
                case 31 :   if(weight < 10.7) index = 4;
                else if(weight >= 10.7 && weight < 11.5) index = 3;
                else if(weight >= 11.5 && weight < 16.2) index = 2;
                else if(weight >= 16.2 && weight < 17.0) index = 1;
                else if(weight >= 17.0) index = 0;
                break;
                case 32 :   if(weight < 10.9) index = 4;
                else if(weight >= 10.9 && weight < 11.7) index = 3;
                else if(weight >= 11.7 && weight < 16.4) index = 2;
                else if(weight >= 16.4 && weight < 17.2) index = 1;
                else if(weight >= 17.2) index = 0;
                break;
                case 33 :   if(weight < 11.0) index = 4;
                else if(weight >= 11.0 && weight < 11.8) index = 3;
                else if(weight >= 11.8 && weight < 16.7) index = 2;
                else if(weight >= 16.7 && weight < 17.5) index = 1;
                else if(weight >= 17.5) index = 0;
                break;
                case 34 :   if(weight < 11.1) index = 4;
                else if(weight >= 11.1 && weight < 11.9) index = 3;
                else if(weight >= 11.9 && weight < 16.9) index = 2;
                else if(weight >= 16.9 && weight < 17.7) index = 1;
                else if(weight >= 17.7) index = 0;
                break;
                case 35 :   if(weight < 11.2) index = 4;
                else if(weight >= 11.2 && weight < 12.0) index = 3;
                else if(weight >= 12.0 && weight < 17.2) index = 2;
                else if(weight >= 17.2 && weight < 18.0) index = 1;
                else if(weight >= 18.0) index = 0;
                break;
                case 36 :   if(weight < 11.3) index = 4;
                else if(weight >= 11.3 && weight < 12.1) index = 3;
                else if(weight >= 12.1 && weight < 17.3) index = 2;
                else if(weight >= 17.3 && weight < 18.2) index = 1;
                else if(weight >= 18.2) index = 0;
                break;
                case 37 :   if(weight < 11.4) index = 4;
                else if(weight >= 11.4 && weight < 12.2) index = 3;
                else if(weight >= 12.2 && weight < 17.6) index = 2;
                else if(weight >= 17.6 && weight < 18.5) index = 1;
                else if(weight >= 18.5) index = 0;
                break;
                case 38 :   if(weight < 11.6) index = 4;
                else if(weight >= 11.6 && weight < 12.4) index = 3;
                else if(weight >= 12.4 && weight < 17.8) index = 2;
                else if(weight >= 17.8 && weight < 18.7) index = 1;
                else if(weight >= 18.7) index = 0;
                break;
                case 39 :   if(weight < 11.7) index = 4;
                else if(weight >= 11.7 && weight < 12.5) index = 3;
                else if(weight >= 12.5 && weight < 18.1) index = 2;
                else if(weight >= 18.1 && weight < 19.0) index = 1;
                else if(weight >= 19.0) index = 0;
                break;
                case 40 :   if(weight < 11.8) index = 4;
                else if(weight >= 11.8 && weight < 12.6) index = 3;
                else if(weight >= 12.6 && weight < 18.2) index = 2;
                else if(weight >= 18.2 && weight < 19.2) index = 1;
                else if(weight >= 19.2) index = 0;
                break;
                case 41 :   if(weight < 11.9) index = 4;
                else if(weight >= 11.9 && weight < 12.7) index = 3;
                else if(weight >= 12.7 && weight < 18.5) index = 2;
                else if(weight >= 18.5 && weight < 19.5) index = 1;
                else if(weight >= 19.5) index = 0;
                break;
                case 42 :   if(weight < 12.0) index = 4;
                else if(weight >= 12.0 && weight < 12.8) index = 3;
                else if(weight >= 12.8 && weight < 18.7) index = 2;
                else if(weight >= 18.7 && weight < 19.8) index = 1;
                else if(weight >= 19.8) index = 0;
                break;
                case 43 :   if(weight < 12.2) index = 4;
                else if(weight >= 12.2 && weight < 13.0) index = 3;
                else if(weight >= 13.0 && weight < 18.9) index = 2;
                else if(weight >= 18.9 && weight < 20.1) index = 1;
                else if(weight >= 20.1) index = 0;
                break;
                case 44 :   if(weight < 12.3) index = 4;
                else if(weight >= 12.3 && weight < 13.1) index = 3;
                else if(weight >= 13.1 && weight < 19.1) index = 2;
                else if(weight >= 19.1 && weight < 20.3) index = 1;
                else if(weight >= 20.3) index = 0;
                break;
                case 45 :   if(weight < 12.4) index = 4;
                else if(weight >= 12.4 && weight < 13.2) index = 3;
                else if(weight >= 13.2 && weight < 19.4) index = 2;
                else if(weight >= 19.4 && weight < 20.6) index = 1;
                else if(weight >= 20.6) index = 0;
                break;
                case 46 :   if(weight < 12.5) index = 4;
                else if(weight >= 12.5 && weight < 13.4) index = 3;
                else if(weight >= 13.4 && weight < 19.6) index = 2;
                else if(weight >= 19.6 && weight < 20.8) index = 1;
                else if(weight >= 20.8) index = 0;
                break;
                case 47 :   if(weight < 12.6) index = 4;
                else if(weight >= 12.6 && weight < 13.5) index = 3;
                else if(weight >= 13.5 && weight < 19.8) index = 2;
                else if(weight >= 19.8 && weight < 21.0) index = 1;
                else if(weight >= 21.0) index = 0;
                break;
                case 48 :   if(weight < 12.7) index = 4;
                else if(weight >= 12.7 && weight < 13.6) index = 3;
                else if(weight >= 13.6 && weight < 20.0) index = 2;
                else if(weight >= 20.0 && weight < 21.3) index = 1;
                else if(weight >= 21.3) index = 0;
                break;
                case 49 :   if(weight < 12.8) index = 4;
                else if(weight >= 12.8 && weight < 13.7) index = 3;
                else if(weight >= 13.7 && weight < 20.3) index = 2;
                else if(weight >= 20.3 && weight < 21.6) index = 1;
                else if(weight >= 21.6) index = 0;
                break;
                case 50 :   if(weight < 12.9) index = 4;
                else if(weight >= 12.9 && weight < 13.8) index = 3;
                else if(weight >= 13.8 && weight < 20.5) index = 2;
                else if(weight >= 20.5 && weight < 21.8) index = 1;
                else if(weight >= 21.8) index = 0;
                break;
                case 51 :   if(weight < 13.0) index = 4;
                else if(weight >= 13.0 && weight < 13.9) index = 3;
                else if(weight >= 13.9 && weight < 20.7) index = 2;
                else if(weight >= 20.7 && weight < 22.0) index = 1;
                else if(weight >= 22.0) index = 0;
                break;
                case 52 :   if(weight < 13.1) index = 4;
                else if(weight >= 13.1 && weight < 14.0) index = 3;
                else if(weight >= 14.0 && weight < 20.9) index = 2;
                else if(weight >= 20.9 && weight < 22.2) index = 1;
                else if(weight >= 22.2) index = 0;
                break;
                case 53 :   if(weight < 13.2) index = 4;
                else if(weight >= 13.2 && weight < 14.1) index = 3;
                else if(weight >= 14.1 && weight < 21.1) index = 2;
                else if(weight >= 21.1 && weight < 22.2) index = 1;
                else if(weight >= 22.2) index = 0;
                break;
                case 54 :   if(weight < 13.3) index = 4;
                else if(weight >= 13.3 && weight < 14.2) index = 3;
                else if(weight >= 14.2 && weight < 21.3) index = 2;
                else if(weight >= 21.3 && weight < 22.7) index = 1;
                else if(weight >= 22.7) index = 0;
                break;
                case 55 :   if(weight < 13.5) index = 4;
                else if(weight >= 13.5 && weight < 14.4) index = 3;
                else if(weight >= 14.4 && weight < 21.6) index = 2;
                else if(weight >= 21.6 && weight < 23.0) index = 1;
                else if(weight >= 23.0) index = 0;
                break;
                case 56 :   if(weight < 13.6) index = 4;
                else if(weight >= 13.6 && weight < 14.5) index = 3;
                else if(weight >= 14.5 && weight < 21.8) index = 2;
                else if(weight >= 21.8 && weight < 23.3) index = 1;
                else if(weight >= 23.3) index = 0;
                break;
                case 57 :   if(weight < 13.8) index = 4;
                else if(weight >= 13.8 && weight < 14.7) index = 3;
                else if(weight >= 14.7 && weight < 22.0) index = 2;
                else if(weight >= 22.0 && weight < 23.6) index = 1;
                else if(weight >= 23.6) index = 0;
                break;
                case 58 :   if(weight < 13.9) index = 4;
                else if(weight >= 13.9 && weight < 14.8) index = 3;
                else if(weight >= 14.8 && weight < 22.2) index = 2;
                else if(weight >= 22.2 && weight < 23.8) index = 1;
                else if(weight >= 23.8) index = 0;
                break;
                case 59 :   if(weight < 14.0) index = 4;
                else if(weight >= 14.0 && weight < 14.9) index = 3;
                else if(weight >= 14.9 && weight < 22.5) index = 2;
                else if(weight >= 22.5 && weight < 24.1) index = 1;
                else if(weight >= 24.1) index = 0;
                break;
                case 60 :   if(weight < 14.1) index = 4;
                else if(weight >= 14.1 && weight < 15.0) index = 3;
                else if(weight >= 15.0 && weight < 22.7) index = 2;
                else if(weight >= 22.7 && weight < 24.3) index = 1;
                else if(weight >= 24.3) index = 0;
                break;
                case 61 :   if(weight < 14.2) index = 4;
                else if(weight >= 14.2 && weight < 15.1) index = 3;
                else if(weight >= 15.1 && weight < 23.0) index = 2;
                else if(weight >= 23.0 && weight < 24.6) index = 1;
                else if(weight >= 24.6) index = 0;
                break;
                case 62 :   if(weight < 14.4) index = 4;
                else if(weight >= 14.4 && weight < 15.3) index = 3;
                else if(weight >= 15.3 && weight < 23.2) index = 2;
                else if(weight >= 23.2 && weight < 24.8) index = 1;
                else if(weight >= 24.8) index = 0;
                break;
                case 63 :   if(weight < 14.5) index = 4;
                else if(weight >= 14.5 && weight < 15.4) index = 3;
                else if(weight >= 15.4 && weight < 23.4) index = 2;
                else if(weight >= 23.4 && weight < 25.1) index = 1;
                else if(weight >= 25.1) index = 0;
                break;
                case 64 :   if(weight < 14.6) index = 4;
                else if(weight >= 14.6 && weight < 15.5) index = 3;
                else if(weight >= 15.5 && weight < 23.6) index = 2;
                else if(weight >= 23.6 && weight < 25.3) index = 1;
                else if(weight >= 25.3) index = 0;
                break;
                case 65 :   if(weight < 14.7) index = 4;
                else if(weight >= 14.7 && weight < 15.7) index = 3;
                else if(weight >= 15.7 && weight < 23.9) index = 2;
                else if(weight >= 23.9 && weight < 25.6) index = 1;
                else if(weight >= 25.6) index = 0;
                break;
                case 66 :   if(weight < 14.8) index = 4;
                else if(weight >= 14.8 && weight < 15.8) index = 3;
                else if(weight >= 15.8 && weight < 24.1) index = 2;
                else if(weight >= 24.1 && weight < 25.8) index = 1;
                else if(weight >= 25.8) index = 0;
                break;
                case 67 :   if(weight < 14.9) index = 4;
                else if(weight >= 14.9 && weight < 15.9) index = 3;
                else if(weight >= 15.9 && weight < 24.4) index = 2;
                else if(weight >= 24.4 && weight < 26.1) index = 1;
                else if(weight >= 26.1) index = 0;
                break;
                case 68 :   if(weight < 15.1) index = 4;
                else if(weight >= 15.1 && weight < 16.1) index = 3;
                else if(weight >= 16.1 && weight < 24.5) index = 2;
                else if(weight >= 24.5 && weight < 26.3) index = 1;
                else if(weight >= 26.3) index = 0;
                break;
                case 69 :   if(weight < 15.2) index = 4;
                else if(weight >= 15.2 && weight < 16.2) index = 3;
                else if(weight >= 16.2 && weight < 24.7) index = 2;
                else if(weight >= 24.7 && weight < 26.5) index = 1;
                else if(weight >= 26.5) index = 0;
                break;
                case 70 :   if(weight < 15.4) index = 4;
                else if(weight >= 15.4 && weight < 16.4) index = 3;
                else if(weight >= 16.4 && weight < 25.0) index = 2;
                else if(weight >= 25.0 && weight < 26.8) index = 1;
                else if(weight >= 26.8) index = 0;
                break;
                case 71 :   if(weight < 15.5) index = 4;
                else if(weight >= 15.5 && weight < 16.5) index = 3;
                else if(weight >= 16.5 && weight < 25.3) index = 2;
                else if(weight >= 25.3 && weight < 27.1) index = 1;
                else if(weight >= 27.1) index = 0;
                break;
                case 72 :   if(weight < 15.5) index = 4;
                else if(weight >= 15.5 && weight < 16.6) index = 3;
                else if(weight >= 16.6 && weight < 25.5) index = 2;
                else if(weight >= 25.5 && weight < 27.3) index = 1;
                else if(weight >= 27.3) index = 0;
                break;
                default :
                    break;
            }
            return index;
        } else if("2".equals(sex))//เพศหญิง
        {
            if("".equals(weights)) {
                return index = 5;
            }
            int month = 0;
            double weight = 0;
            month = Integer.parseInt(months);
            weight = Double.parseDouble(weights);
            switch(month) {
                case 0 :    if(weight < 2.6) index = 4;
                else if(weight >= 2.6 && weight < 2.7) index = 3;
                else if(weight >= 2.7 && weight < 3.8) index = 2;
                else if(weight >= 3.8 && weight < 3.9) index = 1;
                else if(weight >= 3.9) index = 0;
                break;
                case 1 :    if(weight < 3.2) index = 4;
                else if(weight >= 3.2 && weight < 3.3) index = 3;
                else if(weight >= 3.3 && weight < 3.5) index = 2;
                else if(weight >= 3.5 && weight < 3.8) index = 1;
                else if(weight >= 3.8) index = 0;
                break;
                case 2 :    if(weight < 3.7) index = 4;
                else if(weight >= 3.7 && weight < 3.8) index = 3;
                else if(weight >= 3.8 && weight < 5.3) index = 2;
                else if(weight >= 5.3 && weight < 5.6) index = 1;
                else if(weight >= 5.6) index = 0;
                break;
                case 3 :    if(weight < 4.1) index = 4;
                else if(weight >= 4.1 && weight < 4.4) index = 3;
                else if(weight >= 4.4 && weight < 6.1) index = 2;
                else if(weight >= 6.1 && weight < 6.4) index = 1;
                else if(weight >= 6.4) index = 0;
                break;
                case 4 :    if(weight < 4.6) index = 4;
                else if(weight >= 4.6 && weight < 4.9) index = 3;
                else if(weight >= 4.9 && weight < 6.8) index = 2;
                else if(weight >= 6.8 && weight < 7.1) index = 1;
                else if(weight >= 7.1) index = 0;
                break;
                case 5 :    if(weight < 5.0) index = 4;
                else if(weight >= 5.0 && weight < 5.3) index = 3;
                else if(weight >= 5.3 && weight < 7.4) index = 2;
                else if(weight >= 7.4 && weight < 7.8) index = 1;
                else if(weight >= 7.8) index = 0;
                break;
                case 6 :    if(weight < 5.5) index = 4;
                else if(weight >= 5.5 && weight < 5.8) index = 3;
                else if(weight >= 5.8 && weight < 8.0) index = 2;
                else if(weight >= 8.0 && weight < 8.4) index = 1;
                else if(weight >= 8.4) index = 0;
                break;
                case 7 :    if(weight < 5.8) index = 4;
                else if(weight >= 5.8 && weight < 6.2) index = 3;
                else if(weight >= 6.2 && weight < 8.6) index = 2;
                else if(weight >= 8.6 && weight < 9.0) index = 1;
                else if(weight >= 9.0) index = 0;
                break;
                case 8 :    if(weight < 6.2) index = 4;
                else if(weight >= 6.2 && weight < 6.6) index = 3;
                else if(weight >= 6.6 && weight < 9.1) index = 2;
                else if(weight >= 9.1 && weight < 9.5) index = 1;
                else if(weight >= 9.5) index = 0;
                break;
                case 9 :    if(weight < 6.5) index = 4;
                else if(weight >= 6.5 && weight < 6.9) index = 3;
                else if(weight >= 6.9 && weight < 9.4) index = 2;
                else if(weight >= 9.4 && weight < 9.9) index = 1;
                else if(weight >= 9.9) index = 0;
                break;
                case 10 :   if(weight < 6.8) index = 4;
                else if(weight >= 6.8 && weight < 7.2) index = 3;
                else if(weight >= 7.2 && weight < 9.9) index = 2;
                else if(weight >= 9.9 && weight < 10.4) index = 1;
                else if(weight >= 10.4) index = 0;
                break;
                case 11 :   if(weight < 7.1) index = 4;
                else if(weight >= 7.1 && weight < 7.5) index = 3;
                else if(weight >= 7.5 && weight < 10.3) index = 2;
                else if(weight >= 10.3 && weight < 10.8) index = 1;
                else if(weight >= 10.8) index = 0;
                break;
                case 12 :   if(weight < 7.3) index = 4;
                else if(weight >= 7.3 && weight < 7.7) index = 3;
                else if(weight >= 7.7 && weight < 10.6) index = 2;
                else if(weight >= 10.6 && weight < 11.1) index = 1;
                else if(weight >= 10.1) index = 0;
                break;
                case 13 :   if(weight < 7.5) index = 4;
                else if(weight >= 7.5 && weight < 7.9) index = 3;
                else if(weight >= 7.9 && weight < 10.9) index = 2;
                else if(weight >= 10.9 && weight < 11.4) index = 1;
                else if(weight >= 11.4) index = 0;
                break;
                case 14 :   if(weight < 7.7) index = 4;
                else if(weight >= 7.7 && weight < 8.1) index = 3;
                else if(weight >= 8.1 && weight < 11.2) index = 2;
                else if(weight >= 11.2 && weight < 11.7) index = 1;
                else if(weight >= 11.7) index = 0;
                break;
                case 15 :   if(weight < 7.9) index = 4;
                else if(weight >= 7.9 && weight < 8.3) index = 3;
                else if(weight >= 8.3 && weight < 11.4) index = 2;
                else if(weight >= 11.4 && weight < 12.0) index = 1;
                else if(weight >= 12.0) index = 0;
                break;
                case 16 :   if(weight < 8.0) index = 4;
                else if(weight >= 8.0 && weight < 8.4) index = 3;
                else if(weight >= 8.4 && weight < 11.7) index = 2;
                else if(weight >= 11.7 && weight < 12.2) index = 1;
                else if(weight >= 12.2) index = 0;
                break;
                case 17 :   if(weight < 8.2) index = 4;
                else if(weight >= 8.2 && weight < 8.6) index = 3;
                else if(weight >= 8.6 && weight < 11.9) index = 2;
                else if(weight >= 11.9 && weight < 12.5) index = 1;
                else if(weight >= 12.5) index = 0;
                break;
                case 18 :   if(weight < 8.3) index = 4;
                else if(weight >= 8.3 && weight < 8.8) index = 3;
                else if(weight >= 8.8 && weight < 12.2) index = 2;
                else if(weight >= 12.2 && weight < 12.9) index = 1;
                else if(weight >= 12.9) index = 0;
                break;
                case 19 :   if(weight < 8.5) index = 4;
                else if(weight >= 8.5 && weight < 9.0) index = 3;
                else if(weight >= 9.0 && weight < 12.5) index = 2;
                else if(weight >= 12.5 && weight < 13.2) index = 1;
                else if(weight >= 13.2) index = 0;
                break;
                case 20 :   if(weight < 8.6) index = 4;
                else if(weight >= 8.6 && weight < 9.1) index = 3;
                else if(weight >= 9.1 && weight < 12.7) index = 2;
                else if(weight >= 12.7 && weight < 13.4) index = 1;
                else if(weight >= 13.4) index = 0;
                break;
                case 21 :   if(weight < 8.8) index = 4;
                else if(weight >= 8.8 && weight < 9.3) index = 3;
                else if(weight >= 9.3 && weight < 13.0) index = 2;
                else if(weight >= 13.0 && weight < 13.7) index = 1;
                else if(weight >= 13.7) index = 0;
                break;
                case 22 :   if(weight < 8.9) index = 4;
                else if(weight >= 8.9 && weight < 9.4) index = 3;
                else if(weight >= 9.4 && weight < 13.2) index = 2;
                else if(weight >= 13.2 && weight < 13.9) index = 1;
                else if(weight >= 13.9) index = 0;
                break;
                case 23 :   if(weight < 9.0) index = 4;
                else if(weight >= 9.0 && weight < 9.5) index = 3;
                else if(weight >= 9.5 && weight < 13.5) index = 2;
                else if(weight >= 13.5 && weight < 14.2) index = 1;
                else if(weight >= 14.2) index = 0;
                break;
                case 24 :   if(weight < 9.1) index = 4;
                else if(weight >= 9.1 && weight < 9.7) index = 3;
                else if(weight >= 9.7 && weight < 13.8) index = 2;
                else if(weight >= 13.8 && weight < 14.5) index = 1;
                else if(weight >= 14.5) index = 0;
                break;
                case 25 :   if(weight < 9.2) index = 4;
                else if(weight >= 9.2 && weight < 9.8) index = 3;
                else if(weight >= 9.8 && weight < 14.0) index = 2;
                else if(weight >= 14.0 && weight < 14.7) index = 1;
                else if(weight >= 14.7) index = 0;
                break;
                case 26 :   if(weight < 9.3) index = 4;
                else if(weight >= 9.3 && weight < 10.0) index = 3;
                else if(weight >= 10.0 && weight < 14.3) index = 2;
                else if(weight >= 14.3 && weight < 15.0) index = 1;
                else if(weight >= 15) index = 0;
                break;
                case 27 :   if(weight < 9.5) index = 4;
                else if(weight >= 9.5 && weight < 10.1) index = 3;
                else if(weight >= 10.1 && weight < 14.5) index = 2;
                else if(weight >= 14.5 && weight < 15.2) index = 1;
                else if(weight >= 15.2) index = 0;
                break;
                case 28 :   if(weight < 9.6) index = 4;
                else if(weight >= 9.6 && weight < 10.2) index = 3;
                else if(weight >= 10.2 && weight < 14.7) index = 2;
                else if(weight >= 14.7 && weight < 15.5) index = 1;
                else if(weight >= 15.5) index = 0;
                break;
                case 29 :   if(weight < 9.7) index = 4;
                else if(weight >= 9.7 && weight < 10.4) index = 3;
                else if(weight >= 10.4 && weight < 15.0) index = 2;
                else if(weight >= 15.0 && weight < 15.8) index = 1;
                else if(weight >= 15.8) index = 0;
                break;
                case 30 :   if(weight < 9.8) index = 4;
                else if(weight >= 9.8 && weight < 10.6) index = 3;
                else if(weight >= 10.6 && weight < 15.2) index = 2;
                else if(weight >= 15.2 && weight < 16.0) index = 1;
                else if(weight >= 16.0) index = 0;
                break;
                case 31 :   if(weight < 10.0) index = 4;
                else if(weight >= 10.0 && weight < 10.8) index = 3;
                else if(weight >= 10.8 && weight < 15.5) index = 2;
                else if(weight >= 15.5 && weight < 16.3) index = 1;
                else if(weight >= 16.3) index = 0;
                break;
                case 32 :   if(weight < 10.1) index = 4;
                else if(weight >= 10.1 && weight < 10.9) index = 3;
                else if(weight >= 10.9 && weight < 15.7) index = 2;
                else if(weight >= 15.7 && weight < 16.5) index = 1;
                else if(weight >= 16.5) index = 0;
                break;
                case 33 :   if(weight < 10.3) index = 4;
                else if(weight >= 10.3 && weight < 11.1) index = 3;
                else if(weight >= 11.1 && weight < 16.0) index = 2;
                else if(weight >= 16.0 && weight < 16.8) index = 1;
                else if(weight >= 16.8) index = 0;
                break;
                case 34 :   if(weight < 10.5) index = 4;
                else if(weight >= 10.5 && weight < 11.2) index = 3;
                else if(weight >= 11.2 && weight < 16.2) index = 2;
                else if(weight >= 16.2 && weight < 17.0) index = 1;
                else if(weight >= 17.0) index = 0;
                break;
                case 35 :   if(weight < 10.6) index = 4;
                else if(weight >= 10.6 && weight < 11.4) index = 3;
                else if(weight >= 11.4 && weight < 16.5) index = 2;
                else if(weight >= 16.5 && weight < 17.3) index = 1;
                else if(weight >= 17.3) index = 0;
                break;
                case 36 :   if(weight < 10.7) index = 4;
                else if(weight >= 10.7 && weight < 11.5) index = 3;
                else if(weight >= 11.5 && weight < 16.6) index = 2;
                else if(weight >= 16.6 && weight < 17.5) index = 1;
                else if(weight >= 17.5) index = 0;
                break;
                case 37 :   if(weight < 10.9) index = 4;
                else if(weight >= 10.9 && weight < 11.7) index = 3;
                else if(weight >= 11.7 && weight < 16.9) index = 2;
                else if(weight >= 16.9 && weight < 17.8) index = 1;
                else if(weight >= 17.8) index = 0;
                break;
                case 38 :   if(weight < 11.0) index = 4;
                else if(weight >= 11.0 && weight < 11.8) index = 3;
                else if(weight >= 11.8 && weight < 17.1) index = 2;
                else if(weight >= 17.1 && weight < 18.0) index = 1;
                else if(weight >= 18.0) index = 0;
                break;
                case 39 :   if(weight < 11.1) index = 4;
                else if(weight >= 11.1 && weight < 11.9) index = 3;
                else if(weight >= 11.9 && weight < 17.4) index = 2;
                else if(weight >= 17.4 && weight < 18.3) index = 1;
                else if(weight >= 18.3) index = 0;
                break;
                case 40 :   if(weight < 11.2) index = 4;
                else if(weight >= 11.2 && weight < 12.0) index = 3;
                else if(weight >= 12.0 && weight < 17.6) index = 2;
                else if(weight >= 17.6 && weight < 18.5) index = 1;
                else if(weight >= 18.5) index = 0;
                break;
                case 41 :   if(weight < 11.4) index = 4;
                else if(weight >= 11.4 && weight < 12.2) index = 3;
                else if(weight >= 12.2 && weight < 17.8) index = 2;
                else if(weight >= 17.8 && weight < 18.5) index = 1;
                else if(weight >= 18.8) index = 0;
                break;
                case 42 :   if(weight < 11.5) index = 4;
                else if(weight >= 11.5 && weight < 12.3) index = 3;
                else if(weight >= 12.3 && weight < 18.0) index = 2;
                else if(weight >= 18.0 && weight < 19.0) index = 1;
                else if(weight >= 19.0) index = 0;
                break;
                case 43 :   if(weight < 11.6) index = 4;
                else if(weight >= 11.6 && weight < 12.4) index = 3;
                else if(weight >= 12.4 && weight < 18.2) index = 2;
                else if(weight >= 18.2 && weight < 19.2) index = 1;
                else if(weight >= 19.2) index = 0;
                break;
                case 44 :   if(weight < 11.7) index = 4;
                else if(weight >= 11.7 && weight < 12.6) index = 3;
                else if(weight >= 12.6 && weight < 18.5) index = 2;
                else if(weight >= 18.5 && weight < 19.6) index = 1;
                else if(weight >= 19.6) index = 0;
                break;
                case 45 :   if(weight < 11.8) index = 4;
                else if(weight >= 11.8 && weight < 12.7) index = 3;
                else if(weight >= 12.7 && weight < 18.7) index = 2;
                else if(weight >= 18.7 && weight < 19.8) index = 1;
                else if(weight >= 19.8) index = 0;
                break;
                case 46 :   if(weight < 11.9) index = 4;
                else if(weight >= 11.9 && weight < 12.8) index = 3;
                else if(weight >= 12.8 && weight < 18.8) index = 2;
                else if(weight >= 18.8 && weight < 20.0) index = 1;
                else if(weight >= 20.0) index = 0;
                break;
                case 47 :   if(weight < 12.0) index = 4;
                else if(weight >= 12.0 && weight < 12.9) index = 3;
                else if(weight >= 12.9 && weight < 19.0) index = 2;
                else if(weight >= 19.0 && weight < 20.2) index = 1;
                else if(weight >= 20.2) index = 0;
                break;
                case 48 :   if(weight < 12.1) index = 4;
                else if(weight >= 12.1 && weight < 13.0) index = 3;
                else if(weight >= 13.0 && weight < 19.3) index = 2;
                else if(weight >= 19.3 && weight < 20.5) index = 1;
                else if(weight >= 20.5) index = 0;
                break;
                case 49 :   if(weight < 12.2) index = 4;
                else if(weight >= 12.2 && weight < 13.1) index = 3;
                else if(weight >= 13.1 && weight < 19.5) index = 2;
                else if(weight >= 19.5 && weight < 20.7) index = 1;
                else if(weight >= 20.7) index = 0;
                break;
                case 50 :   if(weight < 12.3) index = 4;
                else if(weight >= 12.3 && weight < 13.2) index = 3;
                else if(weight >= 13.2 && weight < 19.7) index = 2;
                else if(weight >= 19.7 && weight < 20.9) index = 1;
                else if(weight >= 20.9) index = 0;
                break;
                case 51 :   if(weight < 12.4) index = 4;
                else if(weight >= 12.4 && weight < 13.3) index = 3;
                else if(weight >= 13.3 && weight < 19.9) index = 2;
                else if(weight >= 19.9 && weight < 21.1) index = 1;
                else if(weight >= 21.1) index = 0;
                break;
                case 52 :   if(weight < 12.6) index = 4;
                else if(weight >= 12.6 && weight < 13.5) index = 3;
                else if(weight >= 13.5 && weight < 20.0) index = 2;
                else if(weight >= 20.0 && weight < 21.3) index = 1;
                else if(weight >= 21.3) index = 0;
                break;
                case 53 :   if(weight < 12.7) index = 4;
                else if(weight >= 12.7 && weight < 13.6) index = 3;
                else if(weight >= 13.6 && weight < 20.3) index = 2;
                else if(weight >= 20.3 && weight < 21.6) index = 1;
                else if(weight >= 21.6) index = 0;
                break;
                case 54 :   if(weight < 12.8) index = 4;
                else if(weight >= 12.8 && weight < 13.7) index = 3;
                else if(weight >= 13.7 && weight < 20.4) index = 2;
                else if(weight >= 20.4 && weight < 21.7) index = 1;
                else if(weight >= 21.7) index = 0;
                break;
                case 55 :   if(weight < 12.9) index = 4;
                else if(weight >= 12.9 && weight < 13.8) index = 3;
                else if(weight >= 13.8 && weight < 20.6) index = 2;
                else if(weight >= 20.6 && weight < 21.9) index = 1;
                else if(weight >= 21.9) index = 0;
                break;
                case 56 :   if(weight < 13.0) index = 4;
                else if(weight >= 13.0 && weight < 13.9) index = 3;
                else if(weight >= 13.9 && weight < 20.8) index = 2;
                else if(weight >= 20.8 && weight < 22.1) index = 1;
                else if(weight >= 22.1) index = 0;
                break;
                case 57 :   if(weight < 13.1) index = 4;
                else if(weight >= 13.1 && weight < 14.0) index = 3;
                else if(weight >= 14.0 && weight < 21.1) index = 2;
                else if(weight >= 21.1 && weight < 22.4) index = 1;
                else if(weight >= 22.4) index = 0;
                break;
                case 58 :   if(weight < 13.2) index = 4;
                else if(weight >= 13.2 && weight < 14.1) index = 3;
                else if(weight >= 14.1 && weight < 21.3) index = 2;
                else if(weight >= 21.3 && weight < 22.6) index = 1;
                else if(weight >= 22.6) index = 0;
                break;
                case 59 :   if(weight < 13.2) index = 4;
                else if(weight >= 13.2 && weight < 14.1) index = 3;
                else if(weight >= 14.1 && weight < 21.3) index = 2;
                else if(weight >= 21.3 && weight < 22.6) index = 1;
                else if(weight >= 22.6) index = 0;
                break;
                case 60 :   if(weight < 13.5) index = 4;
                else if(weight >= 13.5 && weight < 14.4) index = 3;
                else if(weight >= 14.4 && weight < 21.8) index = 2;
                else if(weight >= 21.8 && weight < 23.2) index = 1;
                else if(weight >= 23.2) index = 0;
                break;
                case 61 :   if(weight < 13.6) index = 4;
                else if(weight >= 13.6 && weight < 14.5) index = 3;
                else if(weight >= 14.5 && weight < 22.1) index = 2;
                else if(weight >= 22.1 && weight < 23.6) index = 1;
                else if(weight >= 23.6) index = 0;
                break;
                case 62 :   if(weight < 13.7) index = 4;
                else if(weight >= 13.7 && weight < 14.7) index = 3;
                else if(weight >= 14.7 && weight < 22.3) index = 2;
                else if(weight >= 22.3 && weight < 23.9) index = 1;
                else if(weight >= 23.9) index = 0;
                break;
                case 63 :   if(weight < 13.9) index = 4;
                else if(weight >= 13.9 && weight < 14.9) index = 3;
                else if(weight >= 14.9 && weight < 22.6) index = 2;
                else if(weight >= 22.6 && weight < 24.2) index = 1;
                else if(weight >= 24.2) index = 0;
                break;
                case 64 :   if(weight < 14.0) index = 4;
                else if(weight >= 14.0 && weight < 15.0) index = 3;
                else if(weight >= 15.0 && weight < 22.8) index = 2;
                else if(weight >= 22.8 && weight < 24.4) index = 1;
                else if(weight >= 24.4) index = 0;
                break;
                case 65 :   if(weight < 14.2) index = 4;
                else if(weight >= 14.2 && weight < 15.2) index = 3;
                else if(weight >= 15.2 && weight < 23.1) index = 2;
                else if(weight >= 23.1 && weight < 24.7) index = 1;
                else if(weight >= 24.7) index = 0;
                break;
                case 66 :   if(weight < 14.3) index = 4;
                else if(weight >= 14.3 && weight < 15.3) index = 3;
                else if(weight >= 15.3 && weight < 23.4) index = 2;
                else if(weight >= 23.4 && weight < 25.0) index = 1;
                else if(weight >= 25.0) index = 0;
                break;
                case 67 :   if(weight < 14.4) index = 4;
                else if(weight >= 14.4 && weight < 15.4) index = 3;
                else if(weight >= 15.4 && weight < 23.6) index = 2;
                else if(weight >= 23.6 && weight < 25.3) index = 1;
                else if(weight >= 25.3) index = 0;
                break;
                case 68 :   if(weight < 14.6) index = 4;
                else if(weight >= 14.6 && weight < 15.6) index = 3;
                else if(weight >= 15.6 && weight < 23.9) index = 2;
                else if(weight >= 23.9 && weight < 25.6) index = 1;
                else if(weight >= 25.6) index = 0;
                break;
                case 69 :   if(weight < 14.6) index = 4;
                else if(weight >= 14.6 && weight < 15.7) index = 3;
                else if(weight >= 15.7 && weight < 24.1) index = 2;
                else if(weight >= 24.1 && weight < 25.8) index = 1;
                else if(weight >= 25.8) index = 0;
                break;
                case 70 :   if(weight < 14.9) index = 4;
                else if(weight >= 14.9 && weight < 15.9) index = 3;
                else if(weight >= 15.9 && weight < 24.3) index = 2;
                else if(weight >= 24.3 && weight < 26.0) index = 1;
                else if(weight >= 26.0) index = 0;
                break;
                case 71 :   if(weight < 14.9) index = 4;
                else if(weight >= 14.9 && weight < 16.0) index = 3;
                else if(weight >= 16.0 && weight < 24.6) index = 2;
                else if(weight >= 24.6 && weight < 26.3) index = 1;
                else if(weight >= 26.3) index = 0;
                break;
                case 72 :   if(weight < 15.0) index = 4;
                else if(weight >= 15.0 && weight < 16.1) index = 3;
                else if(weight >= 16.1 && weight < 24.8) index = 2;
                else if(weight >= 24.8 && weight < 26.5) index = 1;
                else if(weight >= 26.5) index = 0;
                break;
                default :
                    break;
            }
            return index;
        } else//ไม่ระบุ(3)
        {
            return index = 6;
        }
    }
    public static String getYear(String age1) {
        String[] str = null;
        if(age1.indexOf("ปี")!=-1) {
            str = age1.split("ปี");
            if(str[0] != null) {
                return str[0].trim();
            }
        }
        return "";
    }
    public static String getMonth(String age1) {
        String[] str = null;
        if(age1.indexOf("ปี")!=-1) {
            str = age1.split("ปี");
            if(str.length >1) {
                age1 = str[1];
            }
        }
        if(age1.indexOf("เดือน")!=-1) {
            str = age1.split("เดือน");
            if(str[0] != null) {
                return str[0].trim();
            }
        }
        return "";
    }
    public static String getDay(String age1) {
        String[] str = null;
        if(age1.indexOf("ปี")!=-1) {
            str = age1.split("ปี");
            if(str.length >1)
                age1 = str[1];
        }
        if(age1.indexOf("เดือน")!=-1) {
            str = age1.split("เดือน");
            if(str.length >1)
                age1 = str[1];
        }
        if(age1.indexOf("วัน")!=-1) {
            str = age1.split("วัน");
            if(str[0] != null)
                return str[0].trim();
        }
        return "";
    }
    
    public static void println(String str){
        //logger.info(str);
        if(theFileOutputStream==null)
            System.out.println(str);
        else{
            try{
                theFileOutputStream.write((str + "\n").getBytes());
            }
            catch(Exception e){
                e.printStackTrace(Constant.getPrintStream());
            }
        }
    }
    
    public static void println(boolean b) {
        println(Boolean.toString(b));
    }
    
    public static void println(int i) {
        println(Integer.toString(i));
    }
    
    public static void println(Object object) {
        println(object.toString());
    }
    
    public static void println(long time_diff) {
        println(Long.toString(time_diff));
    }
    public static void main(String[] argc){
        println(calculateBMI("64","161"));
    }
    
    public static void print(String string) {
        println(string);
    }
    static FileOutputStream theFileOutputStream = null;
    public static void setLogFile(String string) throws Exception {
        theFileOutputStream = new FileOutputStream(string);
    }

    static PrintStream thePrintStream;
    public static PrintStream getPrintStream() {
        if(theFileOutputStream!=null)
        {
            if(thePrintStream==null)
                thePrintStream = new PrintStream(theFileOutputStream);
            return thePrintStream;
        }
        else
            return System.out;
    }
}
