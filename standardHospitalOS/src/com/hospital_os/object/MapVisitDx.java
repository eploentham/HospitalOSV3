package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;

public class MapVisitDx extends Persistent 
{

        /**รหัสของตาราง t_visit*/
        public String visit_id;
        /**รหัสหลักของตาราง template(ถ้ามี)*/
        public String dx_template_id;
        
        /**รหัสหลักของตาราง*/
        public String t_visit_diag_map_id  ;
        /**Dx 		*/
        public String visit_diag_map_dx ="";	
        /**รหัส ICD10 ของการ map (ถ้ามี)*/
        public String visit_diag_map_icd ="";	
        /**รหัสของผู้ลง Dx (จะเป็นทั้งพยาบาลและแพทย์)*/
        public String visit_diag_map_staff ="";	
        /**เวลาที่ลง*/
        public String visit_diag_map_date_time ="";	
        
        /**รหัสของตาราง t_patient*/
        public String t_patient_id ="";	
         /**ผู้ยกเลิก*/
        public String visit_diag_map_staff_cancel="";
        /**เวลาที่ยกเลิก*/
        public String visit_diag_map_cancel_date_time ="";
        /**	กรณีที่ถูกยกเลิก หรือถูกลบจะเปลี่ยนเป็น 0*/
        public String visit_diag_map_active ="1";
        /** ข้อมูลคำแนะนำ */
        public String guide_id = "";// sumo  09/08/2549
       
   
   /**
    * @roseuid 3F658BBB036E
    */
        public MapVisitDx() 
   {
    
   }

}
