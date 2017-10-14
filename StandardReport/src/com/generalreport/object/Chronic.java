/*
 * Chronic.java
 *
 * Created on 17 ���Ҥ� 2548, 15:10 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.object;

import com.generalreport.utility.StandardObject;

/**
 *
 * @author americus
 */
public class Chronic extends Persist implements StandardObject
{
            /**���ʢ������ä������ѧ*/
            public String t_chronic_id;
            /**�Ţ hn*/
            public String chronic_hn;
            /**�Ţ vn*/
            public String chronic_vn;
            /**�ѹ����ԹԨ����ä*/
            public String chronic_diagnosis_date;
            /**�ѹ����˹��¼�����������ä������ѧ�����������*/
            public String chronic_discharge_date;
            /**����ʶҹТͧ��è�˹��¼���������ش�ͧ�������ä������ѧ*/
            public String f_chronic_discharge_status_id;
            /**��������´�ͧ�ç������ѧ*/
            public String chronic_notice;
            /**�����ä������ѧ*/
            public String chronic_icd10;
            /**���ʢ����š������Ѻ��ԡ��*/
            public String t_visit_id;
            /**���ʢ����ż�����*/
            public String t_patient_id;
            /**�ѹ���ѹ�֡*/
            public String record_date_time;

    /** Creates a new instance of Chronic */
    public Chronic()
    {
        idTable ="137";
        tableName= "t_chronic"; 
    }

    public void setInitData()
    {
           t_chronic_id = "";
           chronic_hn = "";
           chronic_vn = "";
           chronic_diagnosis_date = "";
           chronic_discharge_date = "";
           f_chronic_discharge_status_id = "";
           chronic_notice = "";
           chronic_icd10 = "";
           t_visit_id = "";
           t_patient_id = "";
           record_date_time = "";
    }
    
    public static String getStringTable()
    {
        return "t_chronic";  
    }
    
    public static String getStringFieldPKTable() 
    {
        return "t_chronic_id";    
    }
   
    public static String getStringFieldHN() 
   {
        return "chronic_hn";    
   }
    
   public static String getStringFieldVN() 
   {
        return "chronic_vn";    
   }
   
   public static String getStringFieldDiagnosisDate() 
   {
        return "chronic_diagnosis_date";    
   }
   
   public static String getStringFieldDischargeDate() 
   {
        return "chronic_discharge_date";    
   }
    public static String getStringFieldDischargeStatusID() 
   {
        return "f_chronic_discharge_status_id";    
   }
    
   public static String getStringFieldNotice() 
   {
        return "chronic_notice";    
   }
   
   public static String getStringFieldICD10() 
   {
        return "chronic_icd10";    
   }
   
   public static String getStringFieldVisitID()
   {
        return "t_visit_id";    
   }
   public static String getStringFieldPatientID() 
   {
        return "t_patient_id";    
   }
   
   public static String getStringFieldRecordDateTime() 
   {
        return "record_date_time";    
   } 
   
    public Object setInitDataFieldName()
    {
           this.pk_table = getStringFieldPKTable();
           chronic_hn = getStringFieldHN();
           chronic_vn = getStringFieldVN();
           chronic_diagnosis_date = getStringFieldDiagnosisDate();
           chronic_discharge_date = getStringFieldDischargeDate();
           f_chronic_discharge_status_id = getStringFieldDischargeStatusID();
           chronic_notice = getStringFieldNotice();
           chronic_icd10 = getStringFieldICD10();
           t_visit_id = getStringFieldVisitID();
           t_patient_id = getStringFieldPatientID();
           record_date_time = getStringFieldRecordDateTime();
           return this;
    }
    
}
