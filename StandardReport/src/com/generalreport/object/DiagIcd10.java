/*
 * DiagIcd10.java
 *
 * Created on 15 ���Ҥ� 2548, 10:59 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.object;
import com.generalreport.utility.StandardObject;
/**
 *
 * @author tong(Padungrat)
 */
public class DiagIcd10 extends Persist implements StandardObject{
    
    /**visit_id*/
    public String vn;
    /**���ʢͧ ICD10*/
    public String icd10_code;
    /**�������ͧ���ŧ ���� �� primary*/
    public String type;
    /**�ѹ����ԹԨ���������ä*/
    public String diagnosis_date;
    /**�ѹ�֡��ͤ���*/
    public String dischange_note;
    /**���ʢͧᾷ����ӡ���ԹԨ��� ���͵�Ǩ�ѡ��*/
    public String doctor_kid;
    /**���ʢͧ Clinic ������������Ѻ��ԡ��*/
    public String clinic_kid;
    
    public DiagIcd10() {
        idTable ="140";
        tableName= "t_diag_icd10";  
    }

    public void setInitData() {
        vn = "";
        icd10_code = "";
        type = "";
        diagnosis_date = "";
        dischange_note = "";
        doctor_kid = "";
        clinic_kid = "";
    }
    public static String getStringTable()
    {
        return "t_diag_icd10";  
    }
    
    public static String getStringFieldPKTable() 
    {
        return "t_diag_icd10_id";    
    }
   
   public static String getStringFieldVisitID() 
   {
        return "diag_icd10_vn";    
   }
    
   public static String getStringFieldICD10INumber() 
   {
        return "diag_icd10_number";    
   }
   
   public static String getStringFieldICD10TypeDiag() 
   {
        return "f_diag_icd10_type_id";    
   }
   
   public static String getStringFieldICD10Notice() 
   {
        return "diag_icd10_notice";    
   }
   
   public static String getStringFieldICD10StaffDoctor() 
   {
        return "diag_icd10_staff_doctor";    
   }
   
   public static String getStringFieldICD10ClinicID() 
   {
        return "b_visit_clinic_id";    
   }
   
   public static String getStringFieldICD10DiagDate() 
   {
        return "diag_icd10_diagnosis_date";    
   }
   public Object setInitDataFieldName() {
        this.pk_table = getStringFieldPKTable();
        vn   =getStringFieldVisitID();
        icd10_code   =getStringFieldICD10INumber();
        type   =getStringFieldICD10TypeDiag();
        dischange_note   =getStringFieldICD10Notice();
        doctor_kid   =getStringFieldICD10StaffDoctor();
        clinic_kid   =getStringFieldICD10ClinicID();
        diagnosis_date =getStringFieldICD10DiagDate();
        return this;
    }
    
    
}
