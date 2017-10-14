/*
 * Visit.java
 *
 * Created on 15 ตุลาคม 2548, 11:28 น.
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
public class Visit  extends Persist implements StandardObject{
    
    /**เลข hn*/
    public String hn;
    /**เลข vn*/
    public String vn;
    /**รหัสของ patient_id*/
    public String patient_id;
    /**ประเภทของการ visit*/
    public String visit_type;
    /**วันและเวลาเริ่ม visit*/
    public String begin_visit_time;
    /**หมายเหตุของการ visit*/
    public String visit_note;
    /**หมายเลข สถานพยาบาลที่ใช้ในการ refer in*/
    public String refer_in;
    /**หมายเลข สถานพยาบาลที่ใช้ในการ refer out*/
    public String refer_out;
    /**หมายเหตุของการลง Dx*/
    public String diagnosis_note;
    /***/
    public String dischange_opd;
    public String dischange_ipd;
    public String discharge_status;
    public String admisstion;
    /*สถานะของการ lock*/
    public String locking;
    /**ผู้ lock*/
    public String lock_user;
    /**เวลาที่ lock*/
    public String lock_time;
    /**สถานะของการ visit*/
    public String visit_status;
    /**สถานะของการตั้งครรภ์*/
    public String pregnant;
    /***/
    public String admit_clinic;
    /**วอร์ดที่ทำการ admit*/
    public String ward;
    /**เตียงที่ทำการ admit*/
    public String bed;
    /**สถานะของการ ฝากนอน*/
    public String observe;
    /***/
    public String visit_clinic;
    public String queue; /*????????????*/
    public String service;/*????????????*/
    public String observe_user;
    public String doctor_dx;
    public String doctor_note;
    public String status_dis_ipd;
    public String status_dis_money;
    public String status_dis_doctor;
    public String financial_discharge_time;   
    public String financial_discharge_user;
    public String doctor_discharge_user;
    public String doctor_discharge_time;
    public String stat_dx;
    public String an;
    public String begin_admit_time;
    public String patient_type;
    /*?????? autovisit*/
    public String max_vn;
    public String visit_patient_self_doctor = "";
    public String deny_allergy;/*??????????????*/
    public String is_pcu_service;
    public String is_hospital_service;
    public String is_first;
    public String patient_age;       
    public Visit() {
        idTable ="255";
        tableName= "t_visit"; 
    }

    public void setInitData() {
        max_vn= "max_vn";
        
        
        hn   ="";
        vn   ="";
        visit_type   ="";
        begin_visit_time   ="";
        financial_discharge_time   ="";
        visit_note   ="";
        refer_in   ="";
        refer_out   ="";
        diagnosis_note   ="";
        dischange_opd   ="";
        dischange_ipd   ="";
        discharge_status   ="";
        locking   ="";
        lock_user   ="";
        lock_time   ="";
        visit_status   ="";
        pregnant   ="";
        admit_clinic   ="";
        ward   ="";
        bed   ="";
        observe   ="";
        visit_clinic   ="";
        queue   ="";
        service   ="";
        observe_user   ="";
        doctor_dx   ="";
        status_dis_ipd   ="";
        status_dis_money   ="";
        status_dis_doctor   ="";
        patient_id   ="";
        financial_discharge_user   ="";
        doctor_discharge_user   ="";
        doctor_discharge_time   ="";
        an   ="";
        stat_dx   ="";
        begin_admit_time   ="";
        deny_allergy   ="";
        visit_patient_self_doctor = "";
        is_pcu_service = "";
        is_hospital_service = "";
        is_first = "";
        patient_age = "";
    }
    
    public static String getStringTable()
    {
        return "t_visit";  
    }
    
    public static String getStringFieldPKTable() 
    {
        return "t_visit_id";    
    }
   
    public static String getStringFieldHN() 
    {
        return "visit_hn";    
    }
    
    public static String getStringFieldVN() 
    {
        return "visit_vn";    
    }
    public static String getStringFieldVisitTypeID() 
    {
        return "f_visit_type_id";    
    }
    public static String getStringFieldBeginVisitTime() 
    {
        return "visit_begin_visit_time";    
    }
    public static String getStringFieldFinancialDischargeTime() 
    {
        return "visit_financial_discharge_time";    
    }
    public static String getStringFieldNotice() 
    {
        return "visit_notice";    
    }
    public static String getStringFieldVisitReferIN() 
    {
        return "b_visit_office_id_refer_in";    
    }
    public static String getStringFieldVisitReferOut() 
    {
        return "b_visit_office_id_refer_out";    
    }
    public static String getStringFieldVisitDiagNotice() 
    {
        return "visit_diagnosis_notice";    
    }
    public static String getStringFieldVisitOPDDischargeStatusID() 
    {
        return "f_visit_opd_discharge_status_id";    
    }
    public static String getStringFieldVisitIPDDischargeTypeID() 
    {
        return "f_visit_ipd_discharge_type_id";    
    }
    public static String getStringFieldVisitIPDDischargeStatusID() 
    {
        return "f_visit_ipd_discharge_status_id";    
    }
     
    
    public static String getStringFieldVisitLocking() 
    {
        return "visit_locking";    
    }
    public static String getStringFieldVisitStaffLock() 
    {
        return "visit_staff_lock";    
    }
    public static String getStringFieldVisitLockDateTime() 
    {
        return "visit_lock_date_time";    
    }
    public static String getStringFieldVisitStatusID() 
    {
        return "f_visit_status_id";    
    }
    
    public static String getStringFieldVisitPregnant() 
    {
        return "visit_pregnant";    
    }
    public static String getStringFieldVisitClinicIPD() 
    {
        return "b_visit_clinic_id";    
    }
    public static String getStringFieldVisitWardIPD() 
    {
        return "b_visit_ward_id";    
    }
    public static String getStringFieldVisitBedIPD() 
    {
        return "visit_bed";    
    }
    public static String getStringFieldVisitObserve() 
    {
        return "visit_observe";    
    }
    
    public static String getStringFieldVisitPatientType() 
    {
        return "visit_patient_type";    
    }
    public static String getStringFieldVisitQueue() 
    {
        return "visit_queue";    
    }
    
    public static String getStringFieldServicePointID() 
    {
        return "b_service_point_id";    
    }
    public static String getStringFieldVisitStaffObserve() 
    {
        return "visit_staff_observe";    
    }
    
    public static String getStringFieldVisitDx() 
    {
        return "visit_dx";    
    }
    public static String getStringFieldVisitDischargeStatusIPD() 
    {
        return "visit_ipd_discharge_status";    
    }
    public static String getStringFieldVisitDischargeStatusMoney() 
    {
        return "visit_money_discharge_status";    
    }
    
    public static String getStringFieldVisitDischargeStatusDoctor() 
    {
        return "visit_doctor_discharge_status";    
    }
    public static String getStringFieldPatientID() 
    {
        return "t_patient_id";    
    }
    
    public static String getStringFieldVisitStaffFinancialDischarge() 
    {
        return "visit_staff_financial_discharge";    
    }
    public static String getStringFieldVisitStaffDoctorDischarge() 
    {
        return "visit_staff_doctor_discharge";    
    }
    public static String getStringFieldVisitStaffDoctorDischargeDateTime() 
    {
        return "visit_staff_doctor_discharge_date_time";    
    }
    
    public static String getStringFieldVisitAN() 
    {
        return "visit_an";    
    }
    public static String getStringFieldVisitDxStat() 
    {
        return "visit_dx_stat";    
    }
    
    public static String getStringFieldVisitBeginAdmitDateTime() 
    {
        return "visit_begin_admit_date_time";    
    }
    public static String getStringFieldVisitDenyAllergy() 
    {
        return "visit_deny_allergy";    
    }
    
    public static String getStringFieldVisitPatientSelfDoctor() 
    {
        return "visit_patient_self_doctor";    
    }
    public static String getStringFieldVisitPCUService() 
    {
        return "visit_pcu_service";    
    }
    public static String getStringFieldVisitHospitalService() 
    {
        return "visit_hospital_service";    
    }
    public static String getStringFieldFirstVisit() 
    {
        return "first_visit";    
    }
    public static String getStringFieldPatientAge() 
    {
        return "patient_age";    
    }
    public Object setInitDataFieldName() {
        max_vn= "max_vn";
        
        this.pk_table = getStringFieldPKTable();
      
        hn   = getStringFieldHN();
        vn   = getStringFieldVN();
        visit_type   = getStringFieldVisitTypeID();
        begin_visit_time   = getStringFieldBeginVisitTime();
        financial_discharge_time   = getStringFieldFinancialDischargeTime();
        visit_note   = getStringFieldNotice();
        refer_in   = getStringFieldVisitReferIN();
        refer_out   =getStringFieldVisitReferOut();
        diagnosis_note   = getStringFieldVisitDiagNotice();
        dischange_opd   = getStringFieldVisitOPDDischargeStatusID();
        dischange_ipd   = getStringFieldVisitIPDDischargeTypeID();
        discharge_status   = getStringFieldVisitIPDDischargeStatusID();
        locking   =getStringFieldVisitLocking();
        lock_user   = getStringFieldVisitStaffLock();
        lock_time   = getStringFieldVisitLockDateTime();
        visit_status   = getStringFieldVisitStatusID();
        pregnant   = getStringFieldVisitPregnant();
        admit_clinic   = getStringFieldVisitClinicIPD();
        ward   = getStringFieldVisitWardIPD();
        bed   = getStringFieldVisitBedIPD();
        observe   = getStringFieldVisitObserve();
        visit_clinic   = getStringFieldVisitPatientType();
        queue   = getStringFieldVisitQueue();
        service   = getStringFieldServicePointID();
        observe_user   = getStringFieldVisitStaffObserve();
        doctor_dx   = getStringFieldVisitDx();
        status_dis_ipd   = getStringFieldVisitDischargeStatusIPD();
        status_dis_money   =getStringFieldVisitDischargeStatusMoney();
        status_dis_doctor   = getStringFieldVisitDischargeStatusDoctor();
        patient_id   = getStringFieldPatientID();
        financial_discharge_user   = getStringFieldVisitStaffFinancialDischarge();
        doctor_discharge_user   = getStringFieldVisitStaffDoctorDischarge();
        doctor_discharge_time   = getStringFieldVisitStaffDoctorDischargeDateTime();
        an   = getStringFieldVisitAN();
        stat_dx   = getStringFieldVisitDxStat();
        begin_admit_time   = getStringFieldVisitBeginAdmitDateTime();
        deny_allergy   = getStringFieldVisitDenyAllergy();
        visit_patient_self_doctor = getStringFieldVisitPatientSelfDoctor();
        is_pcu_service = getStringFieldVisitPCUService();
        is_hospital_service = getStringFieldVisitHospitalService();
        is_first = getStringFieldFirstVisit();
        patient_age = getStringFieldPatientAge();
        return this;
    }
    
}
