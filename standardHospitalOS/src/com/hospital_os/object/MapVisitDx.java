package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;

public class MapVisitDx extends Persistent 
{

        /**���ʢͧ���ҧ t_visit*/
        public String visit_id;
        /**������ѡ�ͧ���ҧ template(�����)*/
        public String dx_template_id;
        
        /**������ѡ�ͧ���ҧ*/
        public String t_visit_diag_map_id  ;
        /**Dx 		*/
        public String visit_diag_map_dx ="";	
        /**���� ICD10 �ͧ��� map (�����)*/
        public String visit_diag_map_icd ="";	
        /**���ʢͧ���ŧ Dx (���繷�駾�Һ�����ᾷ��)*/
        public String visit_diag_map_staff ="";	
        /**���ҷ��ŧ*/
        public String visit_diag_map_date_time ="";	
        
        /**���ʢͧ���ҧ t_patient*/
        public String t_patient_id ="";	
         /**���¡��ԡ*/
        public String visit_diag_map_staff_cancel="";
        /**���ҷ��¡��ԡ*/
        public String visit_diag_map_cancel_date_time ="";
        /**	�óշ��١¡��ԡ ���Ͷ١ź������¹�� 0*/
        public String visit_diag_map_active ="1";
        /** �����Ť��й� */
        public String guide_id = "";// sumo  09/08/2549
       
   
   /**
    * @roseuid 3F658BBB036E
    */
        public MapVisitDx() 
   {
    
   }

}
