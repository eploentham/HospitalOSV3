package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
public class DiagIcd9 extends Persistent 
{
        /**�����Ţ����Ѻ��ԡ��*/
        public String vn = "";
        /**���� ICD9*/
        public String icd9_code = "";
        /**���ҷ���������ҵѴ*/
        public String time_in = "";
        /**���ҷ������ش��ü�ҵѴ*/
        public String time_out = "";
        /**�������ͧ ICD9*/
        public String type = "";
        /**�����˵ء�ü�ҵѴ*/
        public String dischange_note = "";
        /**����ᾷ����������� ICD9*/
        public String doctor_kid = "";
        /**���ʤ�չԤ*/
        public String clinic_kid = "";
        /**���ʾ�Һ�ŷ�����������ҵѴ*/
        public String nurse_kid = "";
        /**���ʼ��ѹ�֡ ICD9*/
        public String diag_icd9_staff_record ="";
        /**�ѹ���ҷ��ѹ�֡ ICD9*/
        public String diag_icd9_record_date_time ="";
        /**���ʼ����� ICD9*/
        public String diag_icd9_staff_update ="";
        /**�ѹ���ҷ����� ICD9*/
        public String diag_icd9_update_date_time ="";
        /**���ʼ��¡��ԡ ICD9*/
        public String diag_icd9_staff_cancel ="";
        /**�ѹ���ҷ��¡��ԡ ICD9*/
        public String diag_icd9_cancel_date_time ="";
        /**ʶҹС����ҹ�ͧ ICD9*/
        public String diag_icd9_active ="1";
        /**��������ͧ�Ѻ primary ���ҡ���� 1 ��¡�� ICD9*/
        public String primary_report;
                
   /**
    * @roseuid 3F658BBB036E
    */
   public DiagIcd9() 
   {
    
   }
}
