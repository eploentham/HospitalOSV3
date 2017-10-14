package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
public class DiagIcd9 extends Persistent 
{
        /**หมายเลขการรับบริการ*/
        public String vn = "";
        /**รหัส ICD9*/
        public String icd9_code = "";
        /**เวลาที่เริ่มผ่าตัด*/
        public String time_in = "";
        /**เวลาที่สิ้นสุดการผ่าตัด*/
        public String time_out = "";
        /**ประเภทของ ICD9*/
        public String type = "";
        /**หมายเหตุการผ่าตัด*/
        public String dischange_note = "";
        /**รหัสแพทย์ผู้ให้รหัส ICD9*/
        public String doctor_kid = "";
        /**รหัสคลีนิค*/
        public String clinic_kid = "";
        /**รหัสพยาบาลที่เข้าร่วมผ่าตัด*/
        public String nurse_kid = "";
        /**รหัสผู้บันทึก ICD9*/
        public String diag_icd9_staff_record ="";
        /**วันเวลาที่บันทึก ICD9*/
        public String diag_icd9_record_date_time ="";
        /**รหัสผู้แก้ไข ICD9*/
        public String diag_icd9_staff_update ="";
        /**วันเวลาที่แก้ไข ICD9*/
        public String diag_icd9_update_date_time ="";
        /**รหัสผู้ยกเลิก ICD9*/
        public String diag_icd9_staff_cancel ="";
        /**วันเวลาที่ยกเลิก ICD9*/
        public String diag_icd9_cancel_date_time ="";
        /**สถานะการใช้งานของ ICD9*/
        public String diag_icd9_active ="1";
        /**เพื่อให้รองรับ primary ได้มากกว่า 1 รายการ ICD9*/
        public String primary_report;
                
   /**
    * @roseuid 3F658BBB036E
    */
   public DiagIcd9() 
   {
    
   }
}
