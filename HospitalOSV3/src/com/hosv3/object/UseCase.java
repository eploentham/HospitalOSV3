/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hosv3.object;

/**
 *
 * @author henbe
 */
public class UseCase {
    public static String NO_TABLE = "";
    public static String TABLE_TRANSACTION_VISIT = "t_visit";
    public static String TABLE_TRANSACTION_ORDER = "t_order";
    public static String TABLE_TRANSACTION_PATIENT = "t_patient";
    public static String TABLE_TRANSACTION_PATIENT_APPOINTMENT = "t_patient_appointment";
    public static String TABLE_TRANSACTION_ALLERGY = "t_patient_drug_allergy";
    public static String TABLE_TRANSACTION_VISIT_PAYMENT = "t_visit_payment";
    public static String TABLE_TRANSACTION_VISIT_VITAL_SIGN = "t_visit_vital_sign";
    public static String TABLE_TRANSACTION_DIAG_ICD10 = "t_diag_icd10";
    public static String TABLE_TRANSACTION_DIAG_ICD9 = "t_diag_icd9";
    public static String TABLE_TRANSACTION_BILLING = "t_billing";
    public static String TABLE_TRANSACTION_ACCIDENT = "t_accident";
    public static String TABLE_TRANSACTION_VISIT_REFER_IN_OUT = "t_visit_refer_in_out";
    public static String TABLE_TRANSACTION_VISIT_REVERSE_ADMIT = "t_visit_reverse_admit";
    public static String TABLE_TRANSACTION_CHRONIC = "t_chronic";
    public static String TABLE_TRANSACTION_SURVEIL = "t_surveil";
    public static String TABLE_TRANSACTION_DEATH = "t_death";
    public static String TABLE_TRANSACTION_ORDER_DRUG_RETURN = "t_order_drug_return";
    public static String TABLE_TRANSACTION_SERVEIL = "t_serveil";
    public static String TABLE_TRANSACTION_ORDER_ITEM = "t_order_item";
    public static String TABLE_BASE_VISIT_WARD = "b_visit_ward";
    public static String TABLE_BASE_SERVICE_POINT = "b_service_point";
    public static String TABLE_BASE_ITEM_AUTO = "b_item_auto";
    public static String TABLE_BASE_TEMPLATE_DX = "b_template_dx";
    public static String TABLE_TRANSACTION_PATIENT_RISK_FACTOR = "t_patient_risk_factor";
    public static String TABLE_TRANSACTION_PATIENT_FAMILY_HISTORY = "t_patient_family_history";
    public static String TABLE_TRANSACTION_PATIENT_PAST_HISTORY = "t_patient_past_history";
    public static String TABLE_TRANSACTION_PATIENT_DRUG_ALLERGY = "t_patient_drug_allergy";
    public static String TABLE_TRANSACTION_PATIENT_PERSONAL_DISEASE = "t_patient_personal_disease";
    public static String TABLE_TRANSACTION_BORROW_FILM_XRAY = "t_borrow_film_xray";
    public static String TABLE_TRANSACTION_BORROW_OPDCARD = "t_borrow_opdcard";
    public static String TABLE_BASE_ITEM_GROUP = "b_item_group";
    public static String TABLE_BASE_ITEM_SET = "b_item_set";
    public static String TABLE_BASE_CONTRACT = "b_contract";
    public static String TABLE_BASE_CONTRACT_PLAN = "b_contract_plans";
    public static String TABLE_BASE_LAB_RESULT_GROUP = "b_lab_result_group";
    public static String TABLE_BASE_ITEM_DRUG_STANDARD_MAP_ITEM = "b_item_drug_standard_map_item";
    public static String TABLE_BASE_ITEM_DRUG_INTERACTION = "b_item_drug_interaction";
    public static String TABLE_BASE_BODY_ORGAN = "b_body_organ";
    public static String TABLE_BASE_GROUP_ICD10 = "b_group_icd10";
    public static String TABLE_TRANSACTION_PATIENT_XN = "t_patient_xn";
    public static String TABLE_TRANSACTION_VISIT_PHYSICAL_EXAM = "t_visit_physical_exam";
    public static String TABLE_TRANSACTION_HEALTH_FAMILY = "t_health_family";
    public static String TABLE_BASE_ITEM_16_GROUP = "b_item_16_group";
    public static String TABLE_BASE_NCD_GROUP = "b_ncd_group";
    public static String TABLE_TRANSACTION_VISIT_DISCHARGE_ADVICE = "t_visit_discharge_advice";
    public static String TABLE_BASE_EMPLOYEE = "b_employee";
    public static String TABLE_TRANSACTION_PATIENT_NCD = "t_patient_ncd";
    public static String TABLE_TRANSACTION_RESULT_XRAY = "t_result_xray";
    public static String TABLE_BASE_GROUP_CHRONIC = "b_group_chronic";
    public static String TABLE_BASE_ITEM_DRUG_DOSE_SHORTCUT = "b_item_drug_dose_shortcut";
    public static String TABLE_BASE_TEMPLATE_APPOINTMENT = "b_template_appointment";
    public static String TABLE_TRANSACTION_BILLING_RECEIPT_ITEM = "t_billing_receipt_item";
    public static String TABLE_TRANSACTION_WOUND = "t_wound";
    public static String TABLE_BASE_LAB_REFER_IN_PATIENT = "t_lab_refer_in_patient";
    public static String TABLE_BASE_HEALTH_MAIM = "b_health_maim";
    public static String TABLE_BASE_AUTO_REPORT_BUG = "b_auto_report_bug";
    public static String TABLE_BASE_NEWS = "b_news";
    public static String TABLE_BASE_ITEM = "b_item";
    public static String TABLE_BASE_ITEM_PRICE = "b_item_price";
    public static String TABLE_BASE_GUI_ACTION = "b_gui_action";
    public static String TABLE_BASE_GUI_ACTION_AUTHEN = "b_gui_action_authen";
    public static String TABLE_TRANSACTION_HEALTH_RESOURCE = "t_health_resource";
    public static String TABLE_TRANSACTION_HEALTH_RESOURCE_HISTORY = "t_health_resource_history";
    public static String TABLE_TRANSACTION_HEALTH_AGR = "t_health_agr";
    public static String TABLE_TRANSACTION_HEALTH_AGR_HISTORY = "t_health_agr_history";
    public static String TABLE_TRANSACTION_HEALTH_CHECK_HEALTH_YEAR = "t_health_check_health_year";
    public static String TABLE_TRANSACTION_HEALTH_COUNSEL = "t_health_counsel";
    public static String TABLE_TRANSACTION_HEALTH_DENTAL = "t_health_dental";
    public static String TABLE_TRANSACTION_HEALTH_EFFICIENCY = "t_health_efficiency";
    public static String TABLE_TRANSACTION_HEALTH_PP_CARE = "t_health_pp_care";
    public static String TABLE_BASE_HEALTH_SURVEY = "b_health_survey";
    public static String TABLE_TRANSACTION_HEALTH_NUTRITION = "t_health_nutrition";
    public static String TABLE_TRANSACTION_HEALTH_VISIT_HOME_VITALSIGN = "t_health_visit_home_vitalsign";
    public static String TABLE_TRANSACTION_HEALTH_VISIT_HOME = "t_health_visit_home";
    public static String TABLE_TRANSACTION_HEALTH_FAMILY_PLANNING = "t_health_family_planing";
    public static String TABLE_TRANSACTION_HEALTH_CHECK_HEALTH = "t_health_check_healthy";
    public static String TABLE_TRANSACTION_HEALTH_POSTPARTUM = "t_health_postpartum";
    public static String TABLE_TRANSACTION_HEALTH_ANC = "t_health_anc";
    public static String TABLE_TRANSACTION_HEALTH_ANC_DETAIL = "t_health_anc_detail";
    public static String TABLE_TRANSACTION_HEALTH_DELIVERY = "t_health_delivery";
    public static String TABLE_TRANSACTION_HEALTH_EPI = "t_health_epi";
    public static String TABLE_TRANSACTION_HEALTH_EPI_DETAIL = "t_health_epi_detail";
    public static String TABLE_TRANSACTION_HEALTH_GROW = "t_health_grow";
    public static String TABLE_TRANSACTION_HEALTH_HOME = "t_health_home";
    public static String TABLE_BASE_HEALTH_EPI_GROUP = "b_health_epi_group";
    public static String TABLE_BASE_HEALTH_DISEASE = "b_health_disease";
    public static String TABLE_BASE_HEALTH_AGE_SURVEY = "b_health_age_survey";
    public static String TABLE_TRANSACTION_HEALTH_EPI_OUTSITE = "t_health_epi_outsite";
    public static String TABLE_TRANSACTION_HEALTH_PREGNANCY = "t_health_pregnancy";
    public static String TABLE_TRANSACTION_HEALTH_VILLAGE = "t_health_village";
    public static String TABLE_TRANSACTION_HEALTH_SCHOOL = "t_health_school";
    public static String TABLE_TRANSACTION_HEALTH_SCHOOL_HISTORY = "t_health_school_history";
    public static String TABLE_TRANSACTION_HEALTH_TEMPLE = "t_health_temple";
    public static String TABLE_TRANSACTION_HEALTH_TEMPLE_HISTORY = "t_health_temple_history";
    public static String TABLE_TRANSACTION_HEALTH_TEMPLE_HISTORY_DETAIL = "t_health_temple_history_detail";
    public static String TABLE_TRANSACTION_HEALTH_COMPANY = "t_health_company";
    public static String TABLE_TRANSACTION_HEALTH_COMPANY_HISTORY = "t_health_company_history";
    public static String TABLE_TRANSACTION_HEALTH_WATER = "t_health_water";
    public static String TABLE_TRANSACTION_HEALTH_MARKET = "t_health_market";
    public static String TABLE_TRANSACTION_PATIENT_PAYMENT = "t_patient_payment";
    public static String TABLE_TRANSACTION_VISIT_PRINARY_SYMPTOM = "t_visit_primary_symptom";
    public static String TABLE_BASE_LAB_RESULT_DETAIL = "b_lab_result_detial";
    public static String TABLE_BASE_VISIT_OFFICE = "b_visit_office";
    public static String TABLE_TRANSACTION_RESULT_XRAY_SIZE = "t_result_xray_size";
    public static String TABLE_BASE_ITEM_DRUG_STANDARD = "b_item_drug_standard";
    public static String TABLE_BASE_GROUP_SURVEIL = "b_group_surveil";
    public static String TABLE_BASE_SERVICE_POINT_DOCTOR = "b_service_point_doctor";
    public static String TABLE_BASE_ICD9 = "b_icd9";
    public static String TABLE_REPORT_SQL_TAMPLATE = "r_sql_template";

    //Hos Usecase
    public static int UCID_visitPatient = 1;
    public static String UC_visitPatient = "visitPatient";
    public static String TH_visitPatient = "การนำผู้ป่วยเข้าสู่กระบวนการ";
    public static String UC_testSendReportBug = "testSendBug";
    public static String TH_testSendReportBug = "ทดสอบระบบส่งเมลแจ้งบัก";

    public static int UCID_visitFamily = 2;
    public static String UC_visitFamily = "visitFamily";
    public static String TH_visitFamily = "การนำประชากรเข้าสู่กระบวนการ";

    public static int UCID_dischargeFinancial = 3;
    public static String UC_dischargeFinancial = "dischargeFinancial";
    public static String TH_dischargeFinancial = "การจำหน่ายทางการเงิน";

    public static int UCID_dropVisitSurvey = 4;
    public static String UC_dropVisitSurvey = "dropVisitSurvey";
    public static String TH_dropVisitSurvey = "การยกเลิกข้อมูลสำรวจ";

    public static int UCID_commitVisitSurvey = 5;
    public static String UC_commitVisitSurvey = "commitVisitSurvey";
    public static String TH_commitVisitSurvey = "การจบกระบวนการสำรวจ";

    public static int UCID_visitSurvey = 6;
    public static String UC_visitSurvey = "visitSurvey";
    public static String TH_visitSurvey = "การนำผู้ป่วยเข้าสู่การสำรวจ";

    public static int UCID_listVisitSurvey = 7;
    public static String UC_listVisitSurvey = "listVisitSurvey";
    public static String TH_listVisitSurvey = "ค้นรายชื่อในกระบวนการสำรวจ";

    public static int UCID_cancelOrderItem = 8;
    public static String UC_cancelOrderItem = "cancelOrderItem";
    public static String TH_cancelOrderItem = "ยกเลิกรายการOrder";

    public static int UCID_cancelSendReferOutLab = 9;
    public static String UC_cancelSendReferOutLab = "cancelSendReferOutLab";
    public static String TH_cancelSendReferOutLab = "ยกเลิกส่งตรวจแลป";

    public static int UCID_sendReferOutLab = 10;
    public static String UC_sendReferOutLab = "sendReferOutLab";
    public static String TH_sendReferOutLab = "ส่งตรวจแลป";

    public static int UCID_resetPatient = 11;
    public static String UC_resetPatient = "resetPatient";
    public static String TH_resetPatient = "เคลียร์ข้อมูลผู้ป่วย";

    public static int UCID_verifyOrderItem = 12;
    public static String UC_verifyOrderItem = "verifyOrderItem";
    public static String TH_verifyOrderItem = "ยืนยันรายการ Order";

    public static int UCID_deletePatient = 13;
    public static String UC_deletePatient = "deletePatient";
    public static String TH_deletePatient = "ยกเลิกข้อมูลผู้ป่วย";

    public static int UCID_deletePatientAllergy = 14;
    public static String UC_deletePatientAllergy = "deletePatientAllergy";
    public static String TH_deletePatientAllergy = "ลบรายการแพ้ยา";

    public static int UCID_savePatientAllergy = 15;
    public static String UC_savePatientAllergy = "savePatientAllergy";
    public static String TH_savePatientAllergy = "บันทึกรายการแพ้ยา";

    public static int UCID_deleteVPayment = 16;
    public static String UC_deleteVPayment = "deleteVPayment";
    public static String TH_deleteVPayment = "ลบสิทธิ์ประจำตัวผู้ป่วย";

    public static int UCID_deleteVitalSign = 17;
    public static String UC_deleteVitalSign = "deleteVitalSign";
    public static String TH_deleteVitalSign = "ลบรายการ vital sign";

    public static int UCID_saveVitalSign = 18;
    public static String UC_saveVitalSign = "saveVitalSign";
    public static String TH_saveVitalSign = "บันทึกรายการ vital sign";

    public static int UCID_dischargeDoctor = 19;
    public static String UC_dischargeDoctor = "dischargeDoctor";
    public static String TH_dischargeDoctor = "จำหน่ายทางการแพทย์";

    public static int UCID_dispenseOrderItem = 20;
    public static String UC_dispenseOrderItem = "dispenseOrderItem";
    public static String TH_dispenseOrderItem = "สั่งจ่ายรายการตรวจรักษา";

    public static int UCID_downVPaymentPriority = 21;
    public static String UC_downVPaymentPriority = "downVPaymentPriority";
    public static String TH_downVPaymentPriority = "ย้ายสิทธิ์การรักษาให้ต่ำลง";

    public static int UCID_upVPaymentPriority = 22;
    public static String UC_upVPaymentPriority = "upVPaymentPriority";
    public static String TH_upVPaymentPriority = "ย้ายลำดับสิทธิ์การรักษาผู้ป่วยให้สูงขึ้น";

    public static int UCID_executeOrderItem = 23;
    public static String UC_executeOrderItem = "executeOrderItem";
    public static String TH_executeOrderItem = "สั่งดำเนินการ รายการตรวจรักษา";

    public static int UCID_saveAppointment = 24;
    public static String UC_saveAppointment = "saveAppointment";
    public static String TH_saveAppointment = "บันทึกการนัดหมาย";

    public static int UCID_saveDiagIcd10 = 25;
    public static String UC_saveDiagIcd10 = "saveDiagIcd10";
    public static String TH_saveDiagIcd10 = "บันทึกการลงรหัสโรค ICD10";

    public static int UCID_saveDiagIcd9 = 26;
    public static String UC_saveDiagIcd9 = "saveDiagIcd9";
    public static String TH_saveDiagIcd9 = "บันทึกการลงรหัสโรค ICD9";

    public static int UCID_saveOrderItem = 27;
    public static String UC_saveOrderItem = "saveOrderItem";
    public static String TH_saveOrderItem = "บันทึกรายการตรวจรักษา";

    public static int UCID_deleteVAppointment = 28;
    public static String UC_deleteVAppointment = "deleteVAppointment";
    public static String TH_deleteVAppointment = "ลบการนัดผู้ป่วย";

    public static int UCID_savePatientPayment = 29;
    public static String UC_savePatientPayment = "savePatientPayment";
    public static String TH_savePatientPayment = "บันทึกสิทธิ์การรักษา";

    public static int UCID_updateVisitPregnant = 30;
    public static String UC_updateVisitPregnant = "updateVisitPregnant";
    public static String TH_updateVisitPregnant = "บันทึกการตั้งครรภ์";

    public static int UCID_sendResultLab = 31;
    public static String UC_sendResultLab = "sendDataResultLab";
    public static String TH_sendResultLab = "บันทึกการตั้งครรภ์";

    public static int UCID_sendVisit = 32;
    public static String UC_sendVisit = "sendVisit";
    public static String TH_sendVisit = "ส่งผู้ป่วยไปยังจุดบริการ";

    public static int UCID_sendXrayResultComplete = 33;
    public static String UC_sendXrayResultComplete = "sendXrayResultComplete";
    public static String TH_sendXrayResultComplete = "ส่งผลการเอ็กซเรย์";

    public static int UCID_unlockVisit = 34;
    public static String UC_unlockVisit = "unlockVisit";
    public static String TH_unlockVisit = "ปลดล็อกผู้ป่วย";

    public static int UCID_admit = 35;
    public static String UC_admit = "admit";
    public static String TH_admit = "บันทึกผู้ป่วย Admit";

    public static int UCID_savePatientPaid = 36;
    public static String UC_savePatientPaid = "savePatientPaid";
    public static String TH_savePatientPaid = "บันทึกการรับเงินจากผู้ป่วย";

    public static int UCID_saveAccident = 37;
    public static String UC_saveAccident = "saveAccident";
    public static String TH_saveAccident = "บันทึกรายการอุบัติหตุ";

    public static int UCID_deleteAccident = 38;
    public static String UC_deleteAccident = "deleteAccident";
    public static String TH_deleteAccident = "ลบรายการอุบัติหตุ";

    public static int UCID_saveRefer = 39;
    public static String UC_saveRefer = "saveRefer";
    public static String TH_saveRefer = "บันทึกข้อมูลการReferผู้ป่วย";

    public static int UCID_deleteRefer = 40;
    public static String UC_deleteRefer = "deleteRefer";
    public static String TH_deleteRefer = "ลบผลการReferของผู้ป่วย";

    public static int UCID_observVisit = 41;
    public static String UC_observVisit = "observVisit";
    public static String TH_observVisit = "ฝากนอน";

    public static int UCID_cancelObservVisit = 42;
    public static String UC_cancelObservVisit = "cancelObservVisit";
    public static String TH_cancelObservVisit = "ยกเลิกการฝากนอน";

    public static int UCID_dropVisit = 43;
    public static String UC_dropVisit = "dropVisit";
    public static String TH_dropVisit = "ยกเลิกการเข้ารับบริการ";

    public static int UCID_printAppointmentCard = 44;
    public static String UC_printAppointmentCard = "printAppointment";
    public static String TH_printAppointmentCard = "พิมพ์ใบนัดผู้ป่วย";

    public static int UCID_printAppointmentList = 45;
    public static String UC_printAppointmentList = "printAppointmentList";
    public static String TH_printAppointmentList = "พิมพ์ใบรายการนัดผู้ป่วย";

    public static int UCID_printReferIO = 46;
    public static String UC_printReferIO = "printReferIO";
    public static String TH_printReferIO = "พิมพ์ใบ Refer";

    public static int UCID_remainVisit = 47;
    public static String UC_remainVisit = "remainVisit";
    public static String TH_remainVisit = "ค้างบันทึก";

    public static int UCID_reverseAdmit = 48;
    public static String UC_reverseAdmit = "reverseAdmit";
    public static String TH_reverseAdmit = "ยกเลิกสถานะผู้ป่วยใน";

    public static int UCID_printBillingInvoiceItem = 49;
    public static String UC_printBillingInvoiceItem = "printBillingInvoiceItem";
    public static String TH_printBillingInvoiceItem = "พิมพ์ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ";

    public static int UCID_printSumByBillingGroup = 50;
    public static String UC_printSumByBillingGroup = "printSumByBillingGroup";
    public static String TH_printSumByBillingGroup = "พิมพ์ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ";

    public static int UCID_printChronicList = 51;
    public static String UC_printChronicList = "printChronicList";
    public static String TH_printChronicList = "พิมพ์รายการโรคเรื้อรัง";

    public static int UCID_printDrugSticker = 52;
    public static String UC_printDrugSticker = "printDrugSticker";
    public static String TH_printDrugSticker = "พิมพ์ใบสติ๊กเกอร์ยา";

    public static int UCID_printIndex = 53;
    public static String UC_printIndex = "printIndex";
    public static String TH_printIndex = "พิมพ์ใบIndex";

    public static int UCID_printOPDCard = 54;
    public static String UC_printOPDCard = "printOPDCard";
    public static String TH_printOPDCard = "พิมพ์ OPDCard";

    public static int UCID_printSummary = 55;
    public static String UC_printSummary = "printSummary";
    public static String TH_printSummary = "พิมพ์ใบSummary";

    public static int UCID_printListSurveil = 56;
    public static String UC_printListSurveil = "printListSurveil";
    public static String TH_printListSurveil = "พิมพ์รายการโรคเฝ้าระวัง";

    public static int UCID_reverseDoctor = 57;
    public static String UC_reverseDoctor = "reverseDoctor";
    public static String TH_reverseDoctor = "ย้อนกลับการจำหน่ายทางการแพทย์";

    public static int UCID_reverseFinancial = 58;
    public static String UC_reverseFinancial = "reverseFinancial";
    public static String TH_reverseFinancial = "ย้อนกลับการจำหน่ายทางการเงิน";

    public static int UCID_saveChronic = 59;
    public static String UC_saveChronic = "saveChronic";
    public static String TH_saveChronic = "บันทึกรายการโรคเรื้อรัง";

    public static int UCID_deleteChronic = 60;
    public static String UC_deleteChronic = "deleteChronic";
    public static String TH_deleteChronic = "ลบรายการโรคเรื้อรัง";

    public static int UCID_saveDeath = 61;
    public static String UC_saveDeath = "saveDeath";
    public static String TH_saveDeath = "บันทึกข้อมูลการตาย";

    public static int UCID_deleteDeath = 62;
    public static String UC_deleteDeath = "deleteDeath";
    public static String TH_deleteDeath = "บันทึกข้อมูลการตาย";

    public static int UCID_saveOrderReturn = 63;
    public static String UC_saveOrderReturn = "saveOrderReturn";
    public static String TH_saveOrderReturn = "บันทึกการคืนยา";

    public static int UCID_saveSurveil = 64;
    public static String UC_saveSurveil = "saveSurveil";
    public static String TH_saveSurveil = "บันทึกรายการโรคเฝ้าระวัง";

    public static int UCID_visitFromVAppointment = 65;
    public static String UC_visitFromVAppointment = "visitFromVAppointment";
    public static String TH_visitFromVAppointment = "Visitจากการนัดอัตโนมัติ";

    public static int UCID_saveRemainResultLab = 66;
    public static String UC_saveRemainResultLab = "saveRemainResultLab";
    public static String TH_saveRemainResultLab = "ค้างผลแลป";

    public static int UCID_printXnIndex = 67;
    public static String UC_printXnIndex = "printXnIndex";
    public static String TH_printXnIndex = "พิมพ์ใบปะหน้าเอ็กซเรย์";

    public static int UCID_cancelBillingInvoice = 68;
    public static String UC_cancelBillingInvoice = "cancelBillingInvoice";
    public static String TH_cancelBillingInvoice = "ยกเลิกการใบแจ้งหนี้";

    public static int UCID_cancelOrderDrugContinue = 69;
    public static String UC_cancelOrderDrugContinue = "cancelOrderDrugContinue";
    public static String TH_cancelOrderDrugContinue = "ยกเลิกการสั่งยาต่อเนื่อง";

    public static int UCID_continueOrderItem = 70;
    public static String UC_continueOrderItem = "continueOrderItem";
    public static String TH_continueOrderItem = "สั่งตรวจรายการแบบต่อเนื่อง";

    public static int UCID_saveWard = 71;
    public static String UC_saveWard = "saveWard";
    public static String TH_saveWard = "บันทึกจุดบริการผู้ป่วยใน";

    public static int UCID_saveServicePoint = 72;
    public static String UC_saveServicePoint = "saveServicePoint";
    public static String TH_saveServicePoint = "บันทึกจุดบริการผู้ป่วยนอก";

    public static int UCID_deleteServicePoint = 73;
    public static String UC_deleteServicePoint = "deleteServicePoint";
    public static String TH_deleteServicePoint = "ลบจุดบริการผู้ป่วยนอก";

    public static int UCID_deleteWard = 74;
    public static String UC_deleteWard = "deleteWard";
    public static String TH_deleteWard = "ลบจุดบริการผู้ป่วยใน";

    public static int UCID_saveAutoItem = 75;
    public static String UC_saveAutoItem = "saveAutoItem";
    public static String TH_saveAutoItem = "บันทึกรายการตรวจรักษาอัตโนมัติ";

    public static int UCID_deleteAutoItem = 76;
    public static String UC_deleteAutoItem = "deleteAutoItem";
    public static String TH_deleteAutoItem = "ลบรายการตรวจรักษาอัตโนมัติ";

    public static int UCID_saveTemplateDx = 77;
    public static String UC_saveTemplateDx = "saveTemplateDx";
    public static String TH_saveTemplateDx = "บันทึกรายการ Dx ที่พบบ่อย";

    public static int UCID_deleteTemplateDx = 78;
    public static String UC_deleteTemplateDx = "deleteTemplateDx";
    public static String TH_deleteTemplateDx = "ลบรายการ Dx ที่พบบ่อย";

    public static int UCID_printResultLab = 79;
    public static String UC_printResultLab = "printResultLab";
    public static String TH_printResultLab = "พิมพ์ผลแลบ";

    public static int UCID_savePatientHistory = 80;
    public static String UC_savePatientHistory = "savePatientHistory";
    public static String TH_savePatientHistory = "บันทึกประวัติผู้ป่วย";

    public static int UCID_saveGuide = 81;
    public static String UC_saveGuide = "saveGuide";
    public static String TH_saveGuide = "บันทึกคำแนะนำหลังตรวจ";

    public static int UCID_saveBorrowFilmXray = 82;
    public static String UC_saveBorrowFilmXray = "saveBorrowFilmXray";
    public static String TH_saveBorrowFilmXray = "บันทึกการยืมคืนฟิล์ม Xray";

    public static int UCID_deleteBorrowFilmXray = 83;
    public static String UC_deleteBorrowFilmXray = "deleteBorrowFilmXray";
    public static String TH_deleteBorrowFilmXray = "ลบการยืมคืนฟิล์ม Xray";

    public static int UCID_saveBorrowOpdCard = 84;
    public static String UC_saveBorrowOpdCard = "saveBorrowOpdCard";
    public static String TH_saveBorrowOpdCard = "บันทึกการยืมคืน OpdCard";

    public static int UCID_deleteBorrowOpdCard = 85;
    public static String UC_deleteBorrowOpdCard = "deleteBorrowOpdCard";
    public static String TH_deleteBorrowOpdCard = "ลบการยืมคืน OpdCard";

    public static int UCID_saveDrugSetGroup = 86;
    public static String UC_saveDrugSetGroup = "saveDrugSetGroup";
    public static String TH_saveDrugSetGroup = "บันทึกกลุ่มรายการยาชุด";

    public static int UCID_deleteDrugSetGroup = 87;
    public static String UC_deleteDrugSetGroup = "deleteDrugSetGroup";
    public static String TH_deleteDrugSetGroup = "ลบกลุ่มรายการยาชุด";

    public static int UCID_deleteDoseDrugSet = 88;
    public static String UC_deleteDoseDrugSet = "deleteDoseDrugSet";
    public static String TH_deleteDoseDrugSet = "ลบรายการ Order";

    public static int UCID_saveContractAdjust = 89;
    public static String UC_saveContractAdjust = "saveContractAdjust";
    public static String TH_saveContractAdjust = "บันทึกส่วนลด";

    public static int UCID_deleteContractAdjust = 90;
    public static String UC_deleteContractAdjust = "deleteContractAdjust";
    public static String TH_deleteContractAdjust = "ลบส่วนลด";

    public static int UCID_savePlan = 91;
    public static String UC_savePlan = "savePlan";
    public static String TH_savePlan = "บันทึกสิทธิการรักษา";

    public static int UCID_deletePlan = 92;
    public static String UC_deletePlan = "deletePlan";
    public static String TH_deletePlan = "ลบสิทธิการรักษา";

    public static int UCID_saveLabResuleType = 93;
    public static String UC_saveLabResuleType = "saveLabResuleType";
    public static String TH_saveLabResuleType = "บันทึกชนิดรายงานผลแลป";

    public static int UCID_deleteLabResuleType = 94;
    public static String UC_deleteLabResuleType = "deleteLabResuleType";
    public static String TH_deleteLabResuleType = "ลบชนิดรายงานผลแลป";

    public static int UCID_saveDx = 95;
    public static String UC_saveDx = "saveDx";
    public static String TH_saveDx = "บันทึกผลการตรวจรักษา";

    public static int UCID_addItemXray = 96;
    public static String UC_addItemXray = "addItemXray";
    public static String TH_addItemXray = "เพิ่มรายการOrder";

    public static int UCID_saveDrugStandardMapItem = 97;
    public static String UC_saveDrugStandardMapItem = "saveDrugStandardMapItem";
    public static String TH_saveDrugStandardMapItem = "บันทึกการจับคู่รายการตรวจรักษากับยามาตรฐาน";

    public static int UCID_deleteDrugStandardMapItem = 98;
    public static String UC_deleteDrugStandardMapItem = "deleteDrugStandardMapItem";
    public static String TH_deleteDrugStandardMapItem = "ลบการจับคู่รายการตรวจรักษากับยามาตรฐาน";

    public static int UCID_saveDrugInteraction = 99;
    public static String UC_saveDrugInteraction = "saveDrugInteraction";
    public static String TH_saveDrugInteraction = "บันทึกจับคู่ยามาตรฐานที่มีปฏิกิริยากัน";

    public static int UCID_deleteDrugInteraction = 100;
    public static String UC_deleteDrugInteraction = "deleteDrugInteraction";
    public static String TH_deleteDrugInteraction = "ลบผลการจับคู่ยามาตรฐานที่มีปฏิกิริยากัน";

    public static int UCID_showVisitPaymentCancel = 101;
    public static String UC_showVisitPaymentCancel = "showVisitPaymentCancel";
    public static String TH_showVisitPaymentCancel = "แสดงสิทธิที่ถูกยกเลิก";

    public static int UCID_saveBodyOrgan = 102;
    public static String UC_saveBodyOrgan = "saveBodyOrgan";
    public static String TH_saveBodyOrgan = "บันทึกอวัยวะร่างกาย";

    public static int UCID_deleteBodyOrgan = 103;
    public static String UC_deleteBodyOrgan = "deleteBodyOrgan";
    public static String TH_deleteBodyOrgan = "ลบอวัยวะร่างกาย";

    public static int UCID_saveGroupIcd10 = 104;
    public static String UC_saveGroupIcd10 = "saveGroupIcd10";
    public static String TH_saveGroupIcd10 = "บันทึกข้อมูลการจัดกลุ่มรหัสโรค";

    public static int UCID_deleteGroupIcd10 = 105;
    public static String UC_deleteGroupIcd10 = "deleteGroupIcd10";
    public static String TH_deleteGroupIcd10 = "ลบข้อมูลการจัดกลุ่มรหัสโรค";

    public static int UCID_showHistoryXN = 106;
    public static String UC_showHistoryXN = "showHistoryXN";
    public static String TH_showHistoryXN = "แสดงประวัติเลข XN";

    public static int UCID_savePhysicalExam = 107;
    public static String UC_savePhysicalExam = "savePhysicalExam";
    public static String TH_savePhysicalExam = "บันทึกข้อมูลการตรวจร่างกาย";

    public static int UCID_readPatientByHn = 108;
    public static String UC_readPatientByHn = "readPatientByHn";
    public static String TH_readPatientByHn = "เรียกดูข้อมูลผู้ป่วย";

    public static int UCID_readVisitPatientByVn = 109;
    public static String UC_readVisitPatientByVn = "readVisitPatientByVn";
    public static String TH_readVisitPatientByVn = "เรียกดูข้อมูลการรับบริการของผู้ป่วย";

    public static int UCID_readFamilyByFamilyId = 110;
    public static String UC_readFamilyByFamilyId = "readFamilyByFamilyId";
    public static String TH_readFamilyByFamilyId = "เรียกดูข้อมูลประชากร";

    public static int UCID_updateVisitEmergency = 111;
    public static String UC_updateVisitEmergency = "updateVisitEmergency";
    public static String TH_updateVisitEmergency = "บันทึกฉุกเฉิน";

    public static int UCID_saveItem16Group = 112;
    public static String UC_saveItem16Group = "saveItem16Group";
    public static String TH_saveItem16Group = "บันทึกรายการ 16 กลุ่มมาตรฐาน";

    public static int UCID_deleteItem16Group = 113;
    public static String UC_deleteItem16Group = "deleteItem16Group";
    public static String TH_deleteItem16Group = "ลบรายการ 16 กลุ่มมาตรฐาน";

    public static int UCID_saveNCDGroup = 114;
    public static String UC_saveNCDGroup = "saveNCDGroup";
    public static String TH_saveNCDGroup = "บันทึกโรค NCD";

    public static int UCID_deleteNCDGroup = 115;
    public static String UC_deleteNCDGroup = "deleteNCDGroup";
    public static String TH_deleteNCDGroup = "ลบโรค NCD";

    public static int UCID_listNCDHistory = 116;
    public static String UC_listNCDHistory = "listNCDHistory";
    public static String TH_listNCDHistory = "แสดงประวัติโรค NCD";

    public static int UCID_saveGuideHealthEducation = 117;
    public static String UC_saveGuideHealthEducation = "saveGuideHealthEducation";
    public static String TH_saveGuideHealthEducation = "ให้สุขศึกษา";

    public static int UCID_saveEmployee = 118;
    public static String UC_saveEmployee = "saveEmployee";
    public static String TH_saveEmployee = "บันทึกข้อมูลผู้ใช้งาน";

    public static int UCID_saveVisitNCD = 119;
    public static String UC_saveVisitNCD = "saveVisitNCD";
    public static String TH_saveVisitNCD = "บันทึกข้อมูล NCD ในการรับบริการ";

    public static int UCID_listVisitXrayByDatePid = 120;
    public static String UC_listVisitXrayByDatePid = "listVisitXrayByDatePid";
    public static String TH_listVisitXrayByDatePid = "แสดงประวัติการตรวจ Xray ของผู้ป่วย";

    public static int UCID_saveGroupChronicICD10 = 121;
    public static String UC_saveGroupChronicICD10 = "saveGroupChronicICD10";
    public static String TH_saveGroupChronicICD10 = "บันทึกรายการโรคเรื้อรัง";
    
    public static int UCID_saveDrugDoseShortcut = 122;
    public static String UC_saveDrugDoseShortcut = "saveDrugDoseShortcut";
    public static String TH_saveDrugDoseShortcut = "บันทึกรายการ Dose ย่อ";

    public static int UCID_deleteDrugDoseShortcut = 123;
    public static String UC_deleteDrugDoseShortcut = "deleteDrugDoseShortcut";
    public static String TH_deleteDrugDoseShortcut = "ลบรายการ Dose ย่อ";

    public static int UCID_updateVisitAppointment = 124;
    public static String UC_updateVisitAppointment = "updateVisitAppointment";
    public static String TH_updateVisitAppointment = "บันทึกผู้ป่วยทำนัด";

    public static int UCID_updateVisitRefer = 125;
    public static String UC_updateVisitRefer = "updateVisitRefer";
    public static String TH_updateVisitRefer = "บันทึกผู้ป่วย Refer";

    public static int UCID_printGuide = 126;
    public static String UC_printGuide = "printGuide";
    public static String TH_printGuide = "พิมพ์คำแนะนำหลังตรวจ";

    public static int UCID_saveAppointmentTemplate = 127;
    public static String UC_saveAppointmentTemplate = "saveAppointmentTemplate";
    public static String TH_saveAppointmentTemplate = "บันทึกตัวช่วยการนัด";

    public static int UCID_deleteAppointmentTemplate = 128;
    public static String UC_deleteAppointmentTemplate = "deleteAppointmentTemplate";
    public static String TH_deleteAppointmentTemplate = "ลบตัวช่วยการนัด";

    public static int UCID_printBilling = 129;
    public static String UC_printBilling = "printBilling";
    public static String TH_printBilling = "พิมพ์ใบเสร็จรับเงิน";

    public static int UCID_printVisitSlipNew = 130;
    public static String UC_printVisitSlipNew = "printVisitSlipNew";
    public static String TH_printVisitSlipNew = "พิมพ์ใบรายการตรวจรักษาผู้ป่วย";

    public static int UCID_deleteWound = 131;
    public static String UC_deleteWound = "deleteWound";
    public static String TH_deleteWound = "ลบข้อมูลบาดแผล";

    public static int UCID_saveWound = 132;
    public static String UC_saveWound = "saveWound";
    public static String TH_saveWound = "บันทึกข้อมูลบาดแผล";

    public static int UCID_savePatientLabreferin = 133;
    public static String UC_savePatientLabreferin = "savePatientLabreferin";
    public static String TH_savePatientLabreferin = "บันทึกผู้ป่วย LabReferIn";

    public static int UCID_dischargeIPD = 134;
    public static String UC_dischargeIPD = "dischargeIPD";
    public static String TH_dischargeIPD = "จำหน่ายผู้ป่วยใน";

    public static int UCID_saveOrderHome = 135;
    public static String UC_saveOrderHome = "saveOrderHome";
    public static String TH_saveOrderHome = "บันทึกการรายการกลับบ้าน";

    public static int UCID_readPreviousVisit = 136;
    public static String UC_readPreviousVisit = "readPreviousVisit";
    public static String TH_readPreviousVisit = "ดูข้อมูลการรับบริการของผู้ป่วย";

    public static int UCID_readNextVisit = 137;
    public static String UC_readNextVisit = "readNextVisit";
    public static String TH_readNextVisit = "ดูข้อมูลการรับบริการของผู้ป่วย";

    public static int UCID_deleteICD10GroupChronic = 138;
    public static String UC_deleteICD10GroupChronic = "deleteICD10GroupChronic";
    public static String TH_deleteICD10GroupChronic = "ลบICD10ของกลุ่มโรคเรื้อรัง";

    public static int UCID_saveAutoReportBug = 139;
    public static String UC_saveAutoReportBug = "saveAutoReportBug";
    public static String TH_saveAutoReportBug = "ลบICD10ของกลุ่มโรคเรื้อรัง";

    public static int UCID_dispenseOrders = 140;
    public static String UC_dispenseOrders = "dispenseOrders";
    public static String TH_dispenseOrders = "จ่ายยาทีละหลายๆ คน";

    public static int UCID_printEmptyDrugRx = 141;
    public static String UC_printEmptyDrugRx = "printEmptyDrugRx";
    public static String TH_printEmptyDrugRx = "พิมพ์ใบสั่งยาเปล่า";

    public static int UCID_printMedicalCert = 142;
    public static String UC_printMedicalCert = "printMedicalCert";
    public static String TH_printMedicalCert = "พิมพ์ใบรับรองแพทย์";

    public static int UCID_saveNews = 143;
    public static String UC_saveNews = "saveNews";
    public static String TH_saveNews = "บันทึกข่าว";
//    public static int UCID_saveMapRp18Drug = 138;
//    public static String UC_saveMapRp18Drug = "saveMapRp18Drug";
//    public static String TH_saveMapRp18Drug = "จับคู่รายงาน 18 แฟ้มข้อมูลยา";
//    public static int UCID_saveMapRp12AdpCode = 138;
//    public static String UC_saveMapRp12AdpCode = "saveMapRp12AdpCode";
//    public static String TH_saveMapRp12AdpCode = "จับคู่รายงาน 12 แฟ้มข้อมูลรายการตรวจ";
//    public static int UCID_saveMapRp12Clinic = 138;
//    public static String UC_saveMapRp12Clinic = "saveMapRp12Clinic";
//    public static String TH_saveMapRp12Clinic = "จับคู่รายงาน 12 แฟ้มข้อมูลคลีนิก";
//    public static int UCID_saveMapRp18Occupation = 138;
//    public static String UC_saveMapRp18Occupation = "saveMapRp18Occupation";
//    public static String TH_saveMapRp18Occupation = "จับคู่รายงาน 18 แฟ้มข้อมูลอาชีพ";
//    public static int UCID_saveMapRp18Plan = 138;
//    public static String UC_saveMapRp18Plan = "saveMapRp18Plan";
//    public static String TH_saveMapRp18Plan = "จับคู่รายงาน 18 แฟ้มข้อมูลสิทธิการรักษา";
//    public static int UCID_saveMapRp18Prefix = 138;
//    public static String UC_saveMapRp18Prefix = "saveMapRp18Prefix";
//    public static String TH_saveMapRp18Prefix = "จับคู่รายงาน 18 แฟ้มข้อมูลคำนำหน้าชื่อ";
//    public static int UCID_printByUser = 138;
//    public static String UC_printByUser = "printByUser";
//    public static String TH_printByUser = "งานพิมพ์โดยผู้ใช้";
//    public static int UCID_orderDrugHighAlert = 138;
//    public static String UC_orderDrugHighAlert = "orderDrugHighAlert";
//    public static String TH_orderDrugHighAlert = "สั่งยาอันตราย";
    public static int UCID_setRemainZero = 144;
    public static String UC_setRemainZero = "setRemainZero";
    public static String TH_setRemainZero = "แทงหนี้สูญ";

    public static int UCID_saveItemPrice = 145;
    public static String UC_saveItemPrice = "saveItemPrice";
    public static String TH_saveItemPrice = "บันทึกราคารายการตรวจ";
//    public static int UCID_orderItemAsOldVisit = 138;
//    public static String UC_orderItemAsOldVisit = "orderItemAsOldVisit";
//    public static String TH_orderItemAsOldVisit = "สั่งยาเหมือนครั้งก่อน";
//    public static int UCID_saveICD9Setup = 138;
//    public static String UC_saveICD9Setup = "saveICD9Setup";
//    public static String TH_saveICD9Setup = "บันทึกรหัสหัตถการ";
    public static int UCID_saveAuthen = 146;
    public static String UC_saveAuthen = "saveAuthen";
    public static String TH_saveAuthen = "บันทึกสิทธิการใช้งาน";

    public static int UCID_updatePatch = 147;
    public static String UC_updatePatch = "updatePatch";
    public static String TH_updatePatch = "ปรับปรุงเวอร์ชันอัตโนมัติ";
//PCU Usecase
    public static int UCID_saveSetupMaim= 148;
    public static String UC_saveSetupMaim= "saveSetupMaim";
    public static String TH_saveSetupMaim= "บันทึกและแก้ไขกลุ่มความพิการ";

    public static int UCID_deleteSetupMaimType= 149;
    public static String UC_deleteSetupMaimType= "deleteSetupMaimType";
    public static String TH_deleteSetupMaimType= "ลบกลุ่มความพิการ";

    public static int UCID_saveSetupDisease= 150;
    public static String UC_saveSetupDisease= "saveSetupDisease";
    public static String TH_saveSetupDisease= "บันทึกและแก้ไขเซตอับรายการโรค";

    public static int UCID_deleteSetupDisease= 151;
    public static String UC_deleteSetupDisease= "deleteSetupDisease";
    public static String TH_deleteSetupDisease= "ลบเซตอับรายการโรค";

    public static int UCID_saveSetupAgeSurvay= 152;
    public static String UC_saveSetupAgeSurvay= "saveSetupAgeSurvay";
    public static String TH_saveSetupAgeSurvay= "บันทึกและแก้ไขเซตอับการสำรวจตามช่วงอายุ";

    public static int UCID_deleteSetupAgeSurvey= 153;
    public static String UC_deleteSetupAgeSurvey= "deleteSetupAgeSurvey";
    public static String TH_deleteSetupAgeSurvey= "ลบเซตอับรายการสำรวจตามช่วงอายุ";

    public static int UCID_saveBornMch= 154;
    public static String UC_saveBornMch= "saveBornMch";
    public static String TH_saveBornMch= "บันทึกและแก้ไขข้อมูลการคลอด";

    public static int UCID_deleteBornMch= 155;
    public static String UC_deleteBornMch= "deleteBornMch";
    public static String TH_deleteBornMch= "ลบข้อมูลการคลอด";

    public static int UCID_saveGrow= 156;
    public static String UC_saveGrow= "saveGrow";
    public static String TH_saveGrow= "บันทึกและแก้ไขข้อมูลพัฒนาการ";

    public static int UCID_deleteGrow= 157;
    public static String UC_deleteGrow= "deleteGrow";
    public static String TH_deleteGrow= "ลบข้อมูลพัฒนาการ";

    public static int UCID_saveEPI= 158;
    public static String UC_saveEPI= "saveEPI";
    public static String TH_saveEPI= "บันทึกและแก้ไขข้อมูลการรับวัคซีน";

    public static int UCID_deleteEPI= 159;
    public static String UC_deleteEPI= "deleteEPI";
    public static String TH_deleteEPI= "ลบข้อมูลการรับวัคซีน";

    public static int UCID_SaveEpiOutSite= 160;
    public static String UC_SaveEpiOutSite= "SaveEpiOutSite";
    public static String TH_SaveEpiOutSite= "บันทึกและแก้ไขข้อมูลรับวัคซีนจากที่อื่น";

    public static int UCID_deleteEpiOutSite= 161;
    public static String UC_deleteEpiOutSite= "deleteEpiOutSite";
    public static String TH_deleteEpiOutSite= "ลบข้อมูลรับวัคซีนจากทื่อื่น";

    public static int UCID_savePP= 162;
    public static String UC_savePP= "savePP";
    public static String TH_savePP= "การบันทึกและแก้ไขข้อมูลเด็กทารก";

    public static int UCID_deletePP= 163;
    public static String UC_deletePP= "deletePP";
    public static String TH_deletePP= "การลบข้อมูลเด็กทารก";

    public static int UCID_savePregnancy= 164;
    public static String UC_savePregnancy= "savePregnancy";
    public static String TH_savePregnancy= "การบันทีกการตั้งครรภ์";

    public static int UCID_deletePregnancy= 165;
    public static String UC_deletePregnancy= "deletePregnancy";
    public static String TH_deletePregnancy= "การลบข้อมูลการตั้งครรถ์";

    public static int UCID_saveAnc= 166;
    public static String UC_saveAnc= "saveAnc";
    public static String TH_saveAnc= "การบันทีกการฝากครรถ์";

    public static int UCID_deleteAnc= 167;
    public static String UC_deleteAnc= "deleteAnc";
    public static String TH_deleteAnc= "การลบข้อมูลรายการตรวจฝากครรถ์";

    public static int UCID_saveVillage= 168;
    public static String UC_saveVillage= "saveVillage";
    public static String TH_saveVillage= "การบันทึกและแก้ไขหมู่บ้าน";

    public static int UCID_deleteVillage= 169;
    public static String UC_deleteVillage= "deleteVillage";
    public static String TH_deleteVillage= "การลบหมู่บ้าน";

    public static int UCID_saveSchool= 170;
    public static String UC_saveSchool= "saveSchool";
    public static String TH_saveSchool= "การบันทึกโรงเรียนและประวัติของโรงเรียน";

    public static int UCID_deleteSchool= 171;
    public static String UC_deleteSchool= "deleteSchool";
    public static String TH_deleteSchool= "การลบโรงเรียน";

    public static int UCID_saveTemple= 172;
    public static String UC_saveTemple= "saveTemple";
    public static String TH_saveTemple= "การบันทึกศาสนสถาน ประวัติของศาสนสถาน และบุคลากรในศาสนสถาน";

    public static int UCID_deleteTemple= 173;
    public static String UC_deleteTemple= "deleteTemple";
    public static String TH_deleteTemple= "การลบศาสนสถาน";

    public static int UCID_saveCompany= 174;
    public static String UC_saveCompany= "saveCompany";
    public static String TH_saveCompany= "การบันทึกสถานประกอบการและประวัติของสถานประกอบการ";

    public static int UCID_deleteCompany= 175;
    public static String UC_deleteCompany= "deleteCompany";
    public static String TH_deleteCompany= "การลบสถานประกอบการ";

    public static int UCID_saveWater= 176;
    public static String UC_saveWater= "saveWater";
    public static String TH_saveWater= "การบันทึกและแก้ไขแหล่งน้ำกับประวัติของแหล่งน้ำ";

    public static int UCID_deleteWater= 177;
    public static String UC_deleteWater= "deleteWater";
    public static String TH_deleteWater= "การลบแหล่งน้ำ";

    public static int UCID_saveMarket= 178;
    public static String UC_saveMarket= "saveMarket";
    public static String TH_saveMarket= "การบันทึกตลาดและประวัติของตลาด";

    public static int UCID_deleteMarket= 179;
    public static String UC_deleteMarket= "deleteMarket";
    public static String TH_deleteMarket= "การลบตลาด";

    public static int UCID_saveResource= 180;
    public static String UC_saveResource= "saveResource";
    public static String TH_saveResource= "การบันทึกทรัพยากรและประวัติของทรัพยากร";

    public static int UCID_deleteResource= 181;
    public static String UC_deleteResource= "deleteResource";
    public static String TH_deleteResource= "การลบทรัพยากร";

    public static int UCID_saveAGR= 182;
    public static String UC_saveAGR= "saveAGR";
    public static String TH_saveAGR= "การบันทึกกลุ่มกิจกรรมและประวัติของกลุ่มกิจกรรม";

    public static int UCID_deleteAGR= 183;
    public static String UC_deleteAGR= "deleteAGR";
    public static String TH_deleteAGR= "การลบกลุ่มกิจกรรม";

    public static int UCID_saveCheckHealthYear= 184;
    public static String UC_saveCheckHealthYear= "saveCheckHealthYear";
    public static String TH_saveCheckHealthYear= "บันทึกและแก้ไขข้อมูลตรวจสุขภาพประจำปี";

    public static int UCID_deleteCheckHealthYear= 185;
    public static String UC_deleteCheckHealthYear= "deleteCheckHealthYear";
    public static String TH_deleteCheckHealthYear= "ลบข้อมูลตรวจสุภาพประจำปี";

    public static int UCID_saveConsel= 186;
    public static String UC_saveConsel= "saveConsel";
    public static String TH_saveConsel= "บันทึกและแก้ไขข้อมูลให้คำปรึกษา";

    public static int UCID_deleteConsel= 187;
    public static String UC_deleteConsel= "deleteConsel";
    public static String TH_deleteConsel= "ลบข้อมูลให้คำปรึกษา";

    public static int UCID_saveDental= 188;
    public static String UC_saveDental= "saveDental";
    public static String TH_saveDental= "บันทึกและแก้ไขข้อมูลทันตกรรม";

    public static int UCID_deleteDental= 189;
    public static String UC_deleteDental= "deleteDental";
    public static String TH_deleteDental= "ลบข้อมูลทันตกรรม";

    public static int UCID_saveEfficiency= 190;
    public static String UC_saveEfficiency= "saveEfficiency";
    public static String TH_saveEfficiency= "บันทึกและแก้ไขข้อมูลฟื้นฟูสมรรถภาพ";

    public static int UCID_deleteEfficiency= 191;
    public static String UC_deleteEfficiency= "deleteEfficiency";
    public static String TH_deleteEfficiency= "ลบข้อมูลฟื้นฟูสรรถภาพ";

    public static int UCID_saveMaim= 192;
    public static String UC_saveMaim= "saveMaim";
    public static String TH_saveMaim= "บันทึกรายการคนพิการ";

    public static int UCID_deleteMaim= 192;
    public static String UC_deleteMaim= "deleteMaim";
    public static String TH_deleteMaim= "ลบรายการความพิการ";

    public static int UCID_savePPcare= 193;
    public static String UC_savePPcare= "savePPcare";
    public static String TH_savePPcare= "การบันทึกและแก้ไขข้อมูลดูแลลูกหลังคลอด";

    public static int UCID_deletePPcare= 194;
    public static String UC_deletePPcare= "deletePPcare";
    public static String TH_deletePPcare= "การลบข้อมูลดูแลลูกหลังคลอด";

    public static int UCID_savePerson= 195;
    public static String UC_savePerson= "savePerson";
    public static String TH_savePerson= "การบันทึกและแก้ไขข้อมูลประชากร แก้ไขข้อมูลผู้ป่วย";

    public static int UCID_deleteFamily= 196;
    public static String UC_deleteFamily= "deleteFamily";
    public static String TH_deleteFamily= "การลบข้อมูลประชากร หรือตัดความสัมพันธ์ระหว่างผู้ป่วยกับประชากร";

    public static int UCID_saveSetupSurvey= 197;
    public static String UC_saveSetupSurvey= "saveSetupSurvey";
    public static String TH_saveSetupSurvey= "บันทึกเซตอับหัวข้อแบบสำรวจ";

    public static int UCID_deleteSetupSurvey= 198;
    public static String UC_deleteSetupSurvey= "deleteSetupSurvey";
    public static String TH_deleteSetupSurvey= "ลบเซตอับหัวข้อแบบสำรวจ";

    public static int UCID_saveNutrition= 199;
    public static String UC_saveNutrition= "saveNutrition";
    public static String TH_saveNutrition= "บันทึกและแก้ไขข้อมูลโภชนาการ";

    public static int UCID_deleteNutrition= 200;
    public static String UC_deleteNutrition= "deleteNutrition";
    public static String TH_deleteNutrition= "ลบข้อมูลโภชนาการ";

    public static int UCID_saveVisitHome= 201;
    public static String UC_saveVisitHome= "saveVisitHome";
    public static String TH_saveVisitHome= "บันทึกและแก้ไขข้อมูลเยี่ยมบ้าน";

    public static int UCID_deleteVisitHome= 202;
    public static String UC_deleteVisitHome= "deleteVisitHome";
    public static String TH_deleteVisitHome= "ลบข้อมูลเยี่ยมบ้าน";

    public static int UCID_deleteVitalSignVisitHome= 203;
    public static String UC_deleteVitalSignVisitHome= "deleteVitalSignVisitHome";
    public static String TH_deleteVitalSignVisitHome= "การลบ VitalSign การเยี่ยมบ้าน";

//    public static int UCID_saveUncontagios= 204;
//    public static String UC_saveUncontagios= "saveUncontagios";
//    public static String TH_saveUncontagios= "บันทึกรายการโรคไม่ติดต่อ";
//
//    public static int UCID_deleteUncontagios= 205;
//    public static String UC_deleteUncontagios= "deleteUncontagios";
//    public static String TH_deleteUncontagios= "ลบรายการโรคไม่ติดต่อ";

    public static int UCID_saveFpWoman= 206;
    public static String UC_saveFpWoman= "saveFpWoman";
    public static String TH_saveFpWoman= "การบันทึกและแก้ไขข้อมูลวางแผนครอบครัว";

    public static int UCID_deleteFpWoman= 207;
    public static String UC_deleteFpWoman= "deleteFpWoman";
    public static String TH_deleteFpWoman= "การลบข้อมูลวางแผนครอบครัว";

    public static int UCID_saveCheckHealth= 208;
    public static String UC_saveCheckHealth= "saveCheckHealth";
    public static String TH_saveCheckHealth= "บันทึกรายการสำรวจตามอายุ";

    public static int UCID_deleteCheckHealth= 209;
    public static String UC_deleteCheckHealth= "deleteCheckHealth";
    public static String TH_deleteCheckHealth= "ลบรายการสำรวจตามช่วงอายุ";

    public static int UCID_deletePersonPayment= 210;
    public static String UC_deletePersonPayment= "deletePersonPayment";
    public static String TH_deletePersonPayment= "ลบข้อมูลสิทธิประจำตัว";

    public static int UCID_saveAfterMchMother= 211;
    public static String UC_saveAfterMchMother= "saveAfterMchMother";
    public static String TH_saveAfterMchMother= "การบันทึกและแก้ไขข้อมูลดูแลแม่หลังคลอด";

    public static int UCID_deleteAfterMchMother= 212;
    public static String UC_deleteAfterMchMother= "deleteAfterMchMother";
    public static String TH_deleteAfterMchMother= "การลบข้อมูลดูแลแม่หลังคลอด";

    public static int UCID_printAfterMchMother= 213;
    public static String UC_printAfterMchMother= "printAfterMchMother";
    public static String TH_printAfterMchMother= "พิมพ์ข้อมูลดูแลหลังคลอดแม่";

    public static int UCID_printBeforMch= 214;
    public static String UC_printBeforMch= "printBeforMch";
    public static String TH_printBeforMch= "พิมพ์การฝากครรถ์";

    public static int UCID_printBornMchMother= 215;
    public static String UC_printBornMchMother= "printBornMchMother";
    public static String TH_printBornMchMother= "พิมพ์ข้อมูลการเกิด";

    public static int UCID_printlEPI= 216;
    public static String UC_printlEPI= "printlEPI";
    public static String TH_printlEPI= "พิมพ์ข้อมูลการรับวัคซีน";

    public static int UCID_printGrowHistory= 217;
    public static String UC_printGrowHistory= "printGrowHistory";
    public static String TH_printGrowHistory= "พิมพ์ข้อมูลพัฒนาการ";

    public static int UCID_deleteHome= 218;
    public static String UC_deleteHome= "deleteHome";
    public static String TH_deleteHome= "ลบบ้าน";
    
    public static int UCID_saveVaccineGroup= 219;
    public static String UC_saveVaccineGroup= "saveVaccineGroup";
    public static String TH_saveVaccineGroup= "บันทึกชุดวัคซีน";

    public static int UCID_deleteVaccineGroup= 220;
    public static String UC_deleteVaccineGroup= "deleteVaccineGroup";
    public static String TH_deleteVaccineGroup= "ลบชุดวัคซีน";
    
    public static int UCID_printPP= 221;
    public static String UC_printPP= "printPP";
    public static String TH_printPP= "พิมพ์ข้อมูลเด็กทารก";

    //เพิ่มเติมของ Hos

    public static int UCID_cancelBillingByBid = 223;
    public static String UC_cancelBillingByBid = "cancelBillingByBid";
    public static String TH_cancelBillingByBid = "ยกเลิกใบเสร็จ";

    public static int UCID_cancelBillingInvoiceByBIno = 224;
    public static String UC_cancelBillingInvoiceByBIno = "cancelBillingInvoiceByBIno";
    public static String TH_cancelBillingInvoiceByBIno = "ยกเลิกใบแจ้งหนี้";

    public static int UCID_deleteDxIcd10 = 225;
    public static String UC_deleteDxIcd10 = "deleteDxIcd10";
    public static String TH_deleteDxIcd10 = "ยกเลิกการลงรหัสโรค";

    public static int UCID_deleteDxIcd9 = 226;
    public static String UC_deleteDxIcd9 = "deleteDxIcd9";
    public static String TH_deleteDxIcd9 = "ยกเลิกการลงหัตถการ";

    public static int UCID_deleteParticipateOr = 227;
    public static String UC_deleteParticipateOr = "deleteParticipateOr";
    public static String TH_deleteParticipateOr = "ยกเลิกผู้ทำหัตถการ";

    public static int UCID_deletePhysicalExam = 228;
    public static String UC_deletePhysicalExam = "deletePhysicalExam";
    public static String TH_deletePhysicalExam = "ยกเลิกข้อมูลตรวจร่างกาย";

    public static int UCID_deletePrimarySymtomByVid = 229;
    public static String UC_deletePrimarySymtomByVid = "deletePrimarySymtomByVid";
    public static String TH_deletePrimarySymtomByVid = "ยกเลิกข้อมูลอาการเบื้องต้น";

    public static int UCID_deleteVisitPayment = 230;
    public static String UC_deleteVisitPayment = "deleteVisitPayment";
    public static String TH_deleteVisitPayment = "ยกเลิกสิทธิการรักษา";

    public static int UCID_saveDxByStat = 231;
    public static String UC_saveDxByStat = "saveDxByStat";
    public static String TH_saveDxByStat = "บันทึกการตรวจของเวชสถิติ";

    public static int UCID_savePatient = 232;
    public static String UC_savePatient = "savePatient";
    public static String TH_savePatient = "บันทึกข้อมูลผู้ป่วย";

    public static int UCID_savePrimarySymptom = 233;
    public static String UC_savePrimarySymptom = "savePrimarySymptom";
    public static String TH_savePrimarySymptom = "บันทึกอาการเบื้องต้น";

    public static int UCID_saveResultLab = 234;
    public static String UC_saveResultLab = "saveResultLab";
    public static String TH_saveResultLab = "บันทึกผลแลบ";

    public static int UCID_saveResultXray = 235;
    public static String UC_saveResultXray = "saveResultXray";
    public static String TH_saveResultXray = "บันทึกผลเอ็กซเรย์";

    public static int UCID_requestOrder = 236;
    public static String UC_requestOrder = "requestOrder";
    public static String TH_requestOrder = "บันทึกการขอตรวจ";

    public static int UCID_calculateBillingInvoice = 237;
    public static String UC_calculateBillingInvoice = "calculateBillingInvoice";
    public static String TH_calculateBillingInvoice = "ออกใบเสร็จจากใบแจ้งหนี้";

    public static int UCID_sendVisitBackWard = 238;
    public static String UC_sendVisitBackWard = "sendVisitBackWard";
    public static String TH_sendVisitBackWard = "ส่งผู้ป่วยกลับวอร์ด";

    public static int UCID_printOrderSelected = 239;
    public static String UC_printOrderSelected = "printOrderSelected";
    public static String TH_printOrderSelected = "พิมพ์รายการยาที่เลือก";

    public static int UCID_saveAdmitVisit = 240;
    public static String UC_saveAdmitVisit = "saveAdmitVisit";
    public static String TH_saveAdmitVisit = "แก้ไขการ admit";

    public static int UCID_printIpdNameCard = 241;
    public static String UC_printIpdNameCard = "printIpdNameCard";
    public static String TH_printIpdNameCard = "พิมพ์ใบปะหน้าผู้ป่วยใน";

    public static int UCID_printIpdNameCardAllWard = 242;
    public static String UC_printIpdNameCardAllWard = "printIpdNameCardAllWard";
    public static String TH_printIpdNameCardAllWard = "พิมพ์รายชื่อผู้ป่วยใน";

    public static int UCID_saveItem = 243;
    public static String UC_saveItem = "saveItem";
    public static String TH_saveItem = "บันทึกรายการตรวจ";

    public static int UCID_saveOffice = 244;
    public static String UC_saveOffice = "saveOffice";
    public static String TH_saveOffice = "บันทึกสถานพยาบาล";

    public static int UCID_saveResultXraySize = 245;
    public static String UC_saveResultXraySize = "saveResultXraySize";
    public static String TH_saveResultXraySize = "การบันทึกจำนวนฟิล์ม Xray ที่ใช้";

    public static int UCID_saveContract = 246;
    public static String UC_saveContract = "saveContract";
    public static String TH_saveContract = "บันทึกรายการส่วนลด";

    public static int UCID_deleteContract = 247;
    public static String UC_deleteContract = "deleteContract";
    public static String TH_deleteContract = "ลบรายการส่วนลด";

    public static int UCID_saveLabResuleDetail = 248;
    public static String UC_saveLabResuleDetail = "saveLabResuleDetail";
    public static String TH_saveLabResuleDetail = "บันทึกรายละเอียดผลแลป";

    public static int UCID_deleteLabResutlDetail = 249;
    public static String UC_deleteLabResutlDetail = "deleteLabResutlDetail";
    public static String TH_deleteLabResutlDetail = "ลบรายละเอียดผลแลป";

    public static int UCID_deleteResultXrayFilmSize = 250;
    public static String UC_deleteResultXrayFilmSize = "deleteResultXrayFilmSize";
    public static String TH_deleteResultXrayFilmSize = "ลบรายละเอียดของฟิล์ม";

    public static int UCID_saveDrugStandard = 251;
    public static String UC_saveDrugStandard = "saveDrugStandard";
    public static String TH_saveDrugStandard = "บันทึกยามาตรฐาน";

    public static int UCID_deleteDrugStandard = 252;
    public static String UC_deleteDrugStandard = "deleteDrugStandard";
    public static String TH_deleteDrugStandard = "ลบยามาตรฐาน";

    public static int UCID_savePatientXn = 254;
    public static String UC_savePatientXn = "savePatientXn";
    public static String TH_savePatientXn = "บันทึกเลข XN";

    public static int UCID_saveNCD = 255;
    public static String UC_saveNCD = "saveNCD";
    public static String TH_saveNCD = "บันทึกการเข้ารับบริการเกี่ยวกับ NCD";

    public static int UCID_printIpdNameChart  = 256;
    public static String UC_printIpdNameChart  = "printIpdNameChart ";
    public static String TH_printIpdNameChart  = "พิมพ์ใบติดท้าย Chart ผู้ป่วยใน";

    public static int UCID_printSumByItem16Group = 257;
    public static String UC_printSumByItem16Group = "printSumByItem16Group";
    public static String TH_printSumByItem16Group = "พิมพ์ใบสรุปรายการ16กลุ่ม";

    public static int UCID_saveICD10GroupSurveil = 258;
    public static String UC_saveICD10GroupSurveil = "saveICD10GroupSurveil";
    public static String TH_saveICD10GroupSurveil = "บันทึกรายการกลุ่มโรคเฝ้าระวัง";
    
    public static int UCID_deleteICD10GroupSurveil = 259;
    public static String UC_deleteICD10GroupSurveil = "deleteICD10GroupSurveil";
    public static String TH_deleteICD10GroupSurveil = "ลบICD10ของกลุ่มโรคเฝ้าระวัง";

    public static int UCID_orderOldOrder = 260;
    public static String UC_orderOldOrder = "orderOldOrder";
    public static String TH_orderOldOrder = "สั่งยาเหมือนวันก่อน";

    public static int UCID_deletePatientPayment = 261;
    public static String UC_deletePatientPayment = "deletePatientPayment";
    public static String TH_deletePatientPayment = "ยกเลิกสิทธิประจำตัวผู้ป่วย";

    public static int UCID_deleteServicePointDoctor = 262;
    public static String UC_deleteServicePointDoctor = "deleteServicePointDoctor";
    public static String TH_deleteServicePointDoctor = "ลบแพทย์ที่อยู่ในจุดบริการ";

    public static int UCID_saveICD9Setup = 263;
    public static String UC_saveICD9Setup = "saveICD9Setup";
    public static String TH_saveICD9Setup = "บันทึกรหัสหัตถการ";

    public static int UCID_showDialogResultXray = 264;
    public static String UC_showDialogResultXray = "showDialogResultXray";
    public static String TH_showDialogResultXray = "ดูผลเอ็กซเรย์";

    public static int UCID_showDialogHistoryBilling = 265;
    public static String UC_showDialogHistoryBilling = "showDialogHistoryBilling";
    public static String TH_showDialogHistoryBilling = "ดูประวัติการชำระเงิน";

    public static int UCID_showDialogHistoryOrder = 266;
    public static String UC_showDialogHistoryOrder = "showDialogHistoryOrder";
    public static String TH_showDialogHistoryOrder = "ดูประวัติรายการตรวจ";

    public static int UCID_showDialogHistoryOrderContinue = 267;
    public static String UC_showDialogHistoryOrderContinue = "showDialogHistoryOrderContinue";
    public static String TH_showDialogHistoryOrderContinue = "ดูประวัติยาต่อเนื่อง";

    public static int UCID_showDialogHistoryVisit = 268;
    public static String UC_showDialogHistoryVisit = "showDialogHistoryVisit";
    public static String TH_showDialogHistoryVisit = "ดูประวัติยาต่อเนื่อง";

    public static int UCID_ReportQueryModule = 269;
    public static String UC_ReportQueryModule = "ReportQueryModule";
    public static String TH_ReportQueryModule = "ReportQueryModule";

    public static int UCID_StandardReportModule = 270;
    public static String UC_StandardReportModule = "StandardReportModule";
    public static String TH_StandardReportModule = "StandardReportModule";
    
    public static UseCase initUC(int ucname) {
        if(ucname==UCID_visitPatient)
            return new UseCase(UCID_visitPatient,UC_visitPatient,TH_visitPatient
                    ,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_dischargeFinancial)
            return new UseCase(UCID_dischargeFinancial,UC_dischargeFinancial
                    ,TH_dischargeFinancial,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_visitFamily)
            return new UseCase(UCID_visitFamily,UC_visitFamily,TH_visitFamily
                    ,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_cancelOrderItem)
            return new UseCase(UCID_cancelOrderItem,UC_cancelOrderItem
                    ,TH_cancelOrderItem,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_cancelSendReferOutLab)
            return new UseCase(UCID_cancelSendReferOutLab,UC_cancelSendReferOutLab
                    ,TH_cancelSendReferOutLab,TABLE_TRANSACTION_ORDER);
        if(ucname==UCID_sendReferOutLab)
            return new UseCase(UCID_sendReferOutLab,UC_sendReferOutLab
                    ,TH_sendReferOutLab,TABLE_TRANSACTION_ORDER);
        if(ucname==UCID_resetPatient)
            return new UseCase(UCID_resetPatient,UC_resetPatient,TH_resetPatient
                    ,TABLE_TRANSACTION_PATIENT);
        if(ucname==UCID_verifyOrderItem)
            return new UseCase(UCID_verifyOrderItem,UC_verifyOrderItem
                    ,TH_verifyOrderItem,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_deletePatient)
            return new UseCase(UCID_deletePatient,UC_deletePatient
                    ,TH_deletePatient,TABLE_TRANSACTION_PATIENT);
        if(ucname==UCID_deletePatientAllergy)
            return new UseCase(UCID_deletePatientAllergy,UC_deletePatientAllergy
                    ,TH_deletePatientAllergy,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_savePatientAllergy)
            return new UseCase(UCID_savePatientAllergy,UC_savePatientAllergy
                    ,TH_savePatientAllergy,TABLE_TRANSACTION_ALLERGY);
        if(ucname==UCID_deleteVPayment)
            return new UseCase(UCID_deleteVPayment,UC_deleteVPayment
                    ,TH_deleteVPayment,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_deleteVitalSign)
            return new UseCase(UCID_deleteVitalSign,UC_deleteVitalSign
                    ,TH_deleteVitalSign,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_saveVitalSign)
            return new UseCase(UCID_saveVitalSign,UC_saveVitalSign
                    ,TH_saveVitalSign,TABLE_TRANSACTION_VISIT_VITAL_SIGN);
        if(ucname==UCID_dischargeDoctor)
            return new UseCase(UCID_dischargeDoctor,UC_dischargeDoctor
                    ,TH_dischargeDoctor,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_dispenseOrderItem)
            return new UseCase(UCID_dispenseOrderItem,UC_dispenseOrderItem
                    ,TH_dispenseOrderItem,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_downVPaymentPriority)
            return new UseCase(UCID_downVPaymentPriority,UC_downVPaymentPriority
                    ,TH_downVPaymentPriority,TABLE_TRANSACTION_VISIT_PAYMENT);
        if(ucname==UCID_upVPaymentPriority)
            return new UseCase(UCID_upVPaymentPriority,UC_upVPaymentPriority
                    ,TH_upVPaymentPriority,TABLE_TRANSACTION_VISIT_PAYMENT);
        if(ucname==UCID_executeOrderItem)
            return new UseCase(UCID_executeOrderItem,UC_executeOrderItem
                    ,TH_executeOrderItem,TABLE_TRANSACTION_VISIT_PAYMENT);
        if(ucname==UCID_saveAppointment)
            return new UseCase(UCID_saveAppointment,UC_saveAppointment
                    ,TH_saveAppointment,TABLE_TRANSACTION_PATIENT_APPOINTMENT);
        if(ucname==UCID_saveDiagIcd10)
            return new UseCase(UCID_saveDiagIcd10,UC_saveDiagIcd10,TH_saveDiagIcd10
                    ,TABLE_TRANSACTION_DIAG_ICD10);
        if(ucname==UCID_saveDiagIcd9)
            return new UseCase(UCID_saveDiagIcd9,UC_saveDiagIcd9,TH_saveDiagIcd9
                    ,TABLE_TRANSACTION_DIAG_ICD9);
        if(ucname==UCID_saveOrderItem)
            return new UseCase(UCID_saveOrderItem,UC_saveOrderItem
                    ,TH_saveOrderItem,TABLE_TRANSACTION_ORDER);
        if(ucname==UCID_deleteVAppointment)
            return new UseCase(UCID_deleteVAppointment,UC_deleteVAppointment
                    ,TH_deleteVAppointment,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_savePatientPayment)
            return new UseCase(UCID_savePatientPayment,UC_savePatientPayment
                    ,TH_savePatientPayment,TABLE_TRANSACTION_VISIT_PAYMENT);
        if(ucname==UCID_updateVisitPregnant)
            return new UseCase(UCID_updateVisitPregnant,UC_updateVisitPregnant
                    ,TH_updateVisitPregnant,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_sendResultLab)
            return new UseCase(UCID_sendResultLab,UC_sendResultLab
                    ,TH_sendResultLab,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_sendVisit)
            return new UseCase(UCID_sendVisit,UC_sendVisit,TH_sendVisit
                    ,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_sendXrayResultComplete)
            return new UseCase(UCID_sendXrayResultComplete,UC_sendXrayResultComplete
                    ,TH_sendXrayResultComplete,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_unlockVisit)
            return new UseCase(UCID_unlockVisit,UC_unlockVisit,TH_unlockVisit
                    ,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_admit)
            return new UseCase(UCID_admit,UC_admit,TH_admit,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_savePatientPaid)
            return new UseCase(UCID_savePatientPaid,UC_savePatientPaid
                    ,TH_savePatientPaid,TABLE_TRANSACTION_BILLING);
        if(ucname==UCID_saveAccident)
            return new UseCase(UCID_saveAccident,UC_saveAccident,TH_saveAccident
                    ,TABLE_TRANSACTION_ACCIDENT);
        if(ucname==UCID_deleteAccident)
            return new UseCase(UCID_deleteAccident,UC_deleteAccident
                    ,TH_deleteAccident,TABLE_TRANSACTION_ACCIDENT);
        if(ucname==UCID_saveRefer)
            return new UseCase(UCID_saveRefer,UC_saveRefer,TH_saveRefer
                    ,TABLE_TRANSACTION_VISIT_REFER_IN_OUT);
        if(ucname==UCID_deleteRefer)
            return new UseCase(UCID_deleteRefer,UC_deleteRefer,TH_deleteRefer
                    ,TABLE_TRANSACTION_VISIT_REFER_IN_OUT);
        if(ucname==UCID_observVisit)
            return new UseCase(UCID_observVisit,UC_observVisit,TH_observVisit
                    ,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_cancelObservVisit)
            return new UseCase(UCID_cancelObservVisit,UC_cancelObservVisit
                    ,TH_cancelObservVisit,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_dropVisit)
            return new UseCase(UCID_dropVisit,UC_dropVisit,TH_dropVisit
                    ,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_printAppointmentCard)
            return new UseCase(UCID_printAppointmentCard,UC_printAppointmentCard
                    ,TH_printAppointmentCard,TABLE_TRANSACTION_PATIENT_APPOINTMENT);
        if(ucname==UCID_printAppointmentList)
            return new UseCase(UCID_printAppointmentList,UC_printAppointmentList
//                    ,TH_printAppointmentList,TABLE_PATIENT_APPOINTMENT);
                    ,TH_printAppointmentList,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_printReferIO)
            return new UseCase(UCID_printReferIO,UC_printReferIO,TH_printReferIO
                    ,TABLE_TRANSACTION_VISIT_REFER_IN_OUT);
        if(ucname==UCID_remainVisit)
            return new UseCase(UCID_remainVisit,UC_remainVisit
                    ,TH_remainVisit,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_reverseAdmit)
            return new UseCase(UCID_reverseAdmit,UC_reverseAdmit,TH_reverseAdmit
                    ,TABLE_TRANSACTION_VISIT_REVERSE_ADMIT);
        if(ucname==UCID_printBillingInvoiceItem)
            return new UseCase(UCID_printBillingInvoiceItem,UC_printBillingInvoiceItem
                    ,TH_printBillingInvoiceItem,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_printSumByBillingGroup)
            return new UseCase(UCID_printSumByBillingGroup,UC_printSumByBillingGroup
                    ,TH_printSumByBillingGroup,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_printChronicList)
            return new UseCase(UCID_printChronicList,UC_printChronicList
                    ,TH_printChronicList,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_printDrugSticker)
            return new UseCase(UCID_printDrugSticker,UC_printDrugSticker
                    ,TH_printDrugSticker,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_printIndex)
            return new UseCase(UCID_printIndex,UC_printIndex,TH_printIndex
                    ,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_printOPDCard)
            return new UseCase(UCID_printOPDCard,UC_printOPDCard,TH_printIndex
                    ,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_printSummary)
            return new UseCase(UCID_printSummary,UC_printSummary,TH_printSummary
                    ,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_printListSurveil)
            return new UseCase(UCID_printListSurveil,UC_printListSurveil,TH_printListSurveil
                    ,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_reverseDoctor)
            return new UseCase(UCID_reverseDoctor,UC_reverseDoctor,TH_reverseDoctor
                    ,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_reverseFinancial)
            return new UseCase(UCID_reverseFinancial,UC_reverseFinancial,TH_reverseFinancial
                    ,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_saveChronic)
            return new UseCase(UCID_saveChronic,UC_saveChronic,TH_saveChronic
                    ,TABLE_TRANSACTION_CHRONIC);
        if(ucname==UCID_deleteChronic)
            return new UseCase(UCID_deleteChronic,UC_deleteChronic,TH_deleteChronic
                    ,TABLE_TRANSACTION_CHRONIC);
        if(ucname==UCID_saveDeath)
            return new UseCase(UCID_saveDeath,UC_saveDeath,TH_saveDeath
                    ,TABLE_TRANSACTION_DEATH);
        if(ucname==UCID_saveOrderReturn)
            return new UseCase(UCID_saveOrderReturn,UC_saveOrderReturn
                    ,TH_saveOrderReturn,TABLE_TRANSACTION_ORDER_DRUG_RETURN);
        if(ucname==UCID_saveSurveil)
            return new UseCase(UCID_saveSurveil,UC_saveSurveil,TH_saveSurveil
                    ,TABLE_TRANSACTION_SERVEIL);
        if(ucname==UCID_visitFromVAppointment)
            return new UseCase(UCID_visitFromVAppointment,UC_visitFromVAppointment
                    ,TH_visitFromVAppointment,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_saveRemainResultLab)
            return new UseCase(UCID_saveRemainResultLab,UC_saveRemainResultLab
                    ,TH_saveRemainResultLab,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_printXnIndex)
            return new UseCase(UCID_printXnIndex,UC_printXnIndex,TH_printXnIndex
                    ,TABLE_TRANSACTION_PATIENT);
        if(ucname==UCID_cancelBillingInvoice)
            return new UseCase(UCID_cancelBillingInvoice,UC_cancelBillingInvoice
                    ,TH_cancelBillingInvoice,TABLE_TRANSACTION_ORDER);
        if(ucname==UCID_cancelOrderDrugContinue)
            return new UseCase(UCID_cancelOrderDrugContinue,UC_cancelOrderDrugContinue
                    ,TH_cancelOrderDrugContinue,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_continueOrderItem)
            return new UseCase(UCID_continueOrderItem,UC_continueOrderItem
                    ,TH_continueOrderItem,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_saveWard)
            return new UseCase(UCID_saveWard,UC_saveWard
                    ,TH_saveWard,TABLE_BASE_VISIT_WARD);
        if(ucname==UCID_deleteWard)
            return new UseCase(UCID_deleteWard,UC_deleteWard
                    ,TH_deleteWard,TABLE_BASE_VISIT_WARD);
        if(ucname==UCID_saveServicePoint)
            return new UseCase(UCID_saveServicePoint,UC_saveServicePoint
                    ,TH_saveServicePoint,TABLE_BASE_SERVICE_POINT);
        if(ucname==UCID_deleteServicePoint)
            return new UseCase(UCID_deleteServicePoint,UC_deleteServicePoint
                    ,TH_deleteServicePoint,TABLE_BASE_SERVICE_POINT);
        if(ucname==UCID_saveAutoItem)
            return new UseCase(UCID_saveAutoItem,UC_saveAutoItem
                    ,TH_saveAutoItem,TABLE_BASE_ITEM_AUTO);
        if(ucname==UCID_deleteAutoItem)
            return new UseCase(UCID_deleteAutoItem,UC_deleteAutoItem
                    ,TH_deleteAutoItem,TABLE_BASE_ITEM_AUTO);
        if(ucname==UCID_saveTemplateDx)
            return new UseCase(UCID_saveTemplateDx,UC_saveTemplateDx
                    ,TH_saveTemplateDx,TABLE_BASE_TEMPLATE_DX);
        if(ucname==UCID_deleteTemplateDx)
            return new UseCase(UCID_deleteTemplateDx,UC_deleteTemplateDx
                    ,TH_deleteTemplateDx,TABLE_BASE_TEMPLATE_DX);
        if(ucname==UCID_printResultLab)
            return new UseCase(UCID_printResultLab,UC_printResultLab
                    ,TH_printResultLab,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_savePatientHistory)
            return new UseCase(UCID_savePatientHistory,UC_savePatientHistory,TH_savePatientHistory
                    ,TABLE_TRANSACTION_PATIENT_PAST_HISTORY);
        if(ucname==UCID_saveGuide)
            return new UseCase(UCID_saveGuide,UC_saveGuide,TH_saveGuide,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_saveBorrowFilmXray)
            return new UseCase(UCID_saveBorrowFilmXray,UC_saveBorrowFilmXray
                    ,TH_saveBorrowFilmXray,TABLE_TRANSACTION_BORROW_FILM_XRAY);
        if(ucname==UCID_deleteBorrowFilmXray)
            return new UseCase(UCID_deleteBorrowFilmXray,UC_deleteBorrowFilmXray
                    ,TH_deleteBorrowFilmXray,TABLE_TRANSACTION_BORROW_FILM_XRAY);
        if(ucname==UCID_saveBorrowOpdCard)
            return new UseCase(UCID_saveBorrowOpdCard,UC_saveBorrowOpdCard
                    ,TH_saveBorrowOpdCard,TABLE_TRANSACTION_BORROW_OPDCARD);
        if(ucname==UCID_deleteBorrowOpdCard)
            return new UseCase(UCID_deleteBorrowOpdCard,UC_deleteBorrowOpdCard
                    ,TH_deleteBorrowOpdCard,TABLE_TRANSACTION_BORROW_OPDCARD);
        if(ucname==UCID_saveDrugSetGroup)
            return new UseCase(UCID_saveDrugSetGroup,UC_saveDrugSetGroup
                    ,TH_saveDrugSetGroup,TABLE_BASE_ITEM_GROUP);
        if(ucname==UCID_deleteDrugSetGroup)
            return new UseCase(UCID_deleteDrugSetGroup,UC_deleteDrugSetGroup
                    ,TH_deleteDrugSetGroup,TABLE_BASE_ITEM_GROUP);
        if(ucname==UCID_deleteDoseDrugSet)
            return new UseCase(UCID_deleteDoseDrugSet,UC_deleteDoseDrugSet
                    ,TH_deleteDoseDrugSet,TABLE_BASE_ITEM_SET);
        if(ucname==UCID_saveContractAdjust)
            return new UseCase(UCID_saveContractAdjust,UC_saveContractAdjust
                    ,TH_saveContractAdjust,TABLE_BASE_CONTRACT);
        if(ucname==UCID_deleteContractAdjust)
            return new UseCase(UCID_deleteContractAdjust,UC_deleteContractAdjust
                    ,TH_deleteContractAdjust,TABLE_BASE_CONTRACT);
        if(ucname==UCID_savePlan)
            return new UseCase(UCID_savePlan,UC_savePlan,TH_savePlan
                    ,TABLE_BASE_CONTRACT_PLAN);
        if(ucname==UCID_deletePlan)
            return new UseCase(UCID_deletePlan,UC_deletePlan,TH_deletePlan
                    ,TABLE_BASE_CONTRACT_PLAN);
        if(ucname==UCID_saveLabResuleType)
            return new UseCase(UCID_saveLabResuleType,UC_saveLabResuleType
                    ,TH_saveLabResuleType,TABLE_BASE_LAB_RESULT_GROUP);
        if(ucname==UCID_deleteLabResuleType)
            return new UseCase(UCID_deleteLabResuleType,UC_deleteLabResuleType
                    ,TH_deleteLabResuleType,TABLE_BASE_LAB_RESULT_GROUP);
        if(ucname==UCID_saveDx)
            return new UseCase(UCID_saveDx,UC_saveDx
                    ,TH_saveDx,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_addItemXray)
            return new UseCase(UCID_addItemXray,UC_addItemXray,TH_addItemXray
                    ,TABLE_TRANSACTION_ORDER);
        if(ucname==UCID_saveDrugStandardMapItem)
            return new UseCase(UCID_saveDrugStandardMapItem,UC_saveDrugStandardMapItem
                    ,TH_saveDrugStandardMapItem,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_deleteDrugStandardMapItem)
            return new UseCase(UCID_deleteDrugStandardMapItem,UC_deleteDrugStandardMapItem
                    ,TH_deleteDrugStandardMapItem,TABLE_BASE_ITEM_DRUG_STANDARD_MAP_ITEM);
        if(ucname==UCID_saveDrugInteraction)
            return new UseCase(UCID_saveDrugInteraction,UC_saveDrugInteraction
                    ,TH_saveDrugInteraction,TABLE_BASE_ITEM_DRUG_INTERACTION);
        if(ucname==UCID_deleteDrugInteraction)
            return new UseCase(UCID_deleteDrugInteraction,UC_deleteDrugInteraction
                    ,TH_deleteDrugInteraction,TABLE_BASE_ITEM_DRUG_INTERACTION);
        if(ucname==UCID_showVisitPaymentCancel)
            return new UseCase(UCID_showVisitPaymentCancel,UC_showVisitPaymentCancel
                    ,TH_showVisitPaymentCancel,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_saveBodyOrgan)
            return new UseCase(UCID_saveBodyOrgan,UC_saveBodyOrgan
                    ,TH_saveBodyOrgan,TABLE_BASE_BODY_ORGAN);
        if(ucname==UCID_deleteBodyOrgan)
            return new UseCase(UCID_deleteBodyOrgan,UC_deleteBodyOrgan
                    ,TH_deleteBodyOrgan,TABLE_BASE_BODY_ORGAN);
        if(ucname==UCID_saveGroupIcd10)
            return new UseCase(UCID_saveGroupIcd10,UC_saveGroupIcd10
                    ,TH_saveGroupIcd10,TABLE_BASE_GROUP_ICD10);
        if(ucname==UCID_deleteGroupIcd10)
            return new UseCase(UCID_deleteGroupIcd10,UC_deleteGroupIcd10
                    ,TH_deleteGroupIcd10,TABLE_BASE_GROUP_ICD10);
        if(ucname==UCID_showHistoryXN)
            return new UseCase(UCID_showHistoryXN,UC_showHistoryXN
                    ,TH_showHistoryXN,TABLE_TRANSACTION_PATIENT_XN);
        if(ucname==UCID_savePhysicalExam)
            return new UseCase(UCID_savePhysicalExam,UC_savePhysicalExam
                    ,TH_savePhysicalExam,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_readPatientByHn)
            return new UseCase(UCID_readPatientByHn,UC_readPatientByHn
                    ,TH_readPatientByHn,TABLE_TRANSACTION_PATIENT);
        if(ucname==UCID_readVisitPatientByVn)
            return new UseCase(UCID_readVisitPatientByVn,UC_readVisitPatientByVn
                    ,TH_readVisitPatientByVn,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_readFamilyByFamilyId)
            return new UseCase(UCID_readFamilyByFamilyId,UC_readFamilyByFamilyId
                    ,TH_readFamilyByFamilyId,TABLE_TRANSACTION_HEALTH_FAMILY);
        if(ucname==UCID_updateVisitEmergency)
            return new UseCase(UCID_updateVisitEmergency,UC_updateVisitEmergency
                    ,TH_updateVisitEmergency,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_saveItem16Group)
            return new UseCase(UCID_saveItem16Group,UC_saveItem16Group
                    ,TH_saveItem16Group,TABLE_BASE_ITEM_16_GROUP);
        if(ucname==UCID_deleteItem16Group)
            return new UseCase(UCID_deleteItem16Group,UC_deleteItem16Group
                    ,TH_deleteItem16Group,TABLE_BASE_ITEM_16_GROUP);
        if(ucname==UCID_saveNCDGroup)
            return new UseCase(UCID_saveNCDGroup,UC_saveNCDGroup
                    ,TH_saveNCDGroup,TABLE_BASE_NCD_GROUP);
        if(ucname==UCID_deleteNCDGroup)
            return new UseCase(UCID_deleteNCDGroup,UC_deleteNCDGroup
                    ,TH_deleteNCDGroup,TABLE_BASE_NCD_GROUP);
        if(ucname==UCID_listNCDHistory)
            return new UseCase(UCID_listNCDHistory,UC_listNCDHistory
                    ,TH_listNCDHistory,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_saveGuideHealthEducation)
            return new UseCase(UCID_saveGuideHealthEducation,UC_saveGuideHealthEducation
                    ,TH_saveGuideHealthEducation,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_saveEmployee)
            return new UseCase(UCID_saveEmployee,UC_saveEmployee
                    ,TH_saveEmployee,TABLE_BASE_EMPLOYEE);
        if(ucname==UCID_saveVisitNCD)
            return new UseCase(UCID_saveVisitNCD,UC_saveVisitNCD
                    ,TH_saveVisitNCD,TABLE_TRANSACTION_PATIENT_NCD);
        if(ucname==UCID_listVisitXrayByDatePid)
            return new UseCase(UCID_listVisitXrayByDatePid,UC_listVisitXrayByDatePid
                    ,TH_listVisitXrayByDatePid,TABLE_TRANSACTION_RESULT_XRAY);
        if(ucname==UCID_saveGroupChronicICD10)
            return new UseCase(UCID_saveGroupChronicICD10,UC_saveGroupChronicICD10
                    ,TH_saveGroupChronicICD10,TABLE_BASE_GROUP_CHRONIC);
        if(ucname==UCID_saveDrugDoseShortcut)
            return new UseCase(UCID_saveDrugDoseShortcut,UC_saveDrugDoseShortcut
                    ,TH_saveDrugDoseShortcut,TABLE_BASE_ITEM_DRUG_DOSE_SHORTCUT);
        if(ucname==UCID_deleteDrugDoseShortcut)
            return new UseCase(UCID_deleteDrugDoseShortcut,UC_deleteDrugDoseShortcut
                    ,TH_deleteDrugDoseShortcut,TABLE_BASE_ITEM_DRUG_DOSE_SHORTCUT);
        if(ucname==UCID_updateVisitAppointment)
            return new UseCase(UCID_updateVisitAppointment,UC_updateVisitAppointment
                    ,TH_updateVisitAppointment,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_updateVisitRefer)
            return new UseCase(UCID_updateVisitRefer,UC_updateVisitRefer
                    ,TH_updateVisitRefer,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_printGuide)
            return new UseCase(UCID_printGuide,UC_printGuide
                    ,TH_printGuide,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_saveAppointmentTemplate)
            return new UseCase(UCID_saveAppointmentTemplate,UC_saveAppointmentTemplate
                    ,TH_saveAppointmentTemplate,TABLE_BASE_TEMPLATE_APPOINTMENT);
        if(ucname==UCID_deleteAppointmentTemplate)
            return new UseCase(UCID_deleteAppointmentTemplate,UC_deleteAppointmentTemplate
                    ,TH_deleteAppointmentTemplate,TABLE_BASE_TEMPLATE_APPOINTMENT);
        if(ucname==UCID_printBilling)
            return new UseCase(UCID_printBilling,UC_printBilling
                    ,TH_printBilling,TABLE_TRANSACTION_BILLING);
        if(ucname==UCID_printVisitSlipNew)
            return new UseCase(UCID_printVisitSlipNew,UC_printVisitSlipNew
                    ,TH_printVisitSlipNew,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_deleteWound)
            return new UseCase(UCID_deleteWound,UC_deleteWound
                    ,TH_deleteWound,TABLE_TRANSACTION_WOUND);
        if(ucname==UCID_saveWound)
            return new UseCase(UCID_saveWound,UC_saveWound
                    ,TH_saveWound,TABLE_TRANSACTION_WOUND);
        if(ucname==UCID_savePatientLabreferin)
            return new UseCase(UCID_savePatientLabreferin,UC_savePatientLabreferin
                    ,TH_savePatientLabreferin,TABLE_BASE_LAB_REFER_IN_PATIENT);
        if(ucname==UCID_dischargeIPD)
            return new UseCase(UCID_dischargeIPD,UC_dischargeIPD
                    ,TH_dischargeIPD,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_saveOrderHome)
            return new UseCase(UCID_saveOrderHome,UC_saveOrderHome
                    ,TH_saveOrderHome,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_readPreviousVisit)
            return new UseCase(UCID_readPreviousVisit,UC_readPreviousVisit
                    ,TH_readPreviousVisit,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_readNextVisit)
            return new UseCase(UCID_readNextVisit,UC_readNextVisit
                    ,TH_readNextVisit,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_deleteICD10GroupChronic)
            return new UseCase(UCID_deleteICD10GroupChronic,UC_deleteICD10GroupChronic
                    ,TH_deleteICD10GroupChronic,TABLE_BASE_GROUP_ICD10);
        if(ucname==UCID_saveAutoReportBug)
            return new UseCase(UCID_saveAutoReportBug,UC_saveAutoReportBug
                    ,TH_saveAutoReportBug,TABLE_BASE_AUTO_REPORT_BUG);

        if(ucname==UCID_dispenseOrders)
            return new UseCase(UCID_dispenseOrders,UC_dispenseOrders
                    ,TH_dispenseOrders,TABLE_TRANSACTION_ORDER);
        if(ucname==UCID_printEmptyDrugRx)
            return new UseCase(UCID_printEmptyDrugRx,UC_printEmptyDrugRx
                    ,TH_printEmptyDrugRx,NO_TABLE);
        if(ucname==UCID_printMedicalCert)
            return new UseCase(UCID_printMedicalCert,UC_printMedicalCert,TH_printMedicalCert
                    ,TABLE_TRANSACTION_PATIENT);
        if(ucname==UCID_saveNews)
            return new UseCase(UCID_saveNews,UC_saveNews,TH_saveNews,TABLE_BASE_NEWS);
        if(ucname==UCID_setRemainZero)
            return new UseCase(UCID_setRemainZero,UC_setRemainZero,TH_setRemainZero,TABLE_TRANSACTION_PATIENT);
        if(ucname==UCID_saveItemPrice)
            return new UseCase(UCID_saveItemPrice,UC_saveItemPrice,TH_saveItemPrice
                    ,TABLE_BASE_ITEM+","+TABLE_BASE_ITEM_PRICE);
        if(ucname==UCID_saveAuthen)
            return new UseCase(UCID_saveAuthen,UC_saveAuthen,TH_saveAuthen
                    ,TABLE_BASE_GUI_ACTION);
        if(ucname==UCID_updatePatch)
            return new UseCase(UCID_updatePatch,UC_updatePatch,TH_updatePatch,NO_TABLE);
//PCU Ucecase
        if(ucname==UCID_saveResource)
            return new UseCase(UCID_saveResource,UC_saveResource,TH_saveResource
                    ,TABLE_TRANSACTION_HEALTH_RESOURCE);
        if(ucname==UCID_deleteResource)
            return new UseCase(UCID_deleteResource,UC_deleteResource,TH_deleteResource
                    ,TABLE_TRANSACTION_HEALTH_RESOURCE);
        if(ucname==UCID_saveAGR)
            return new UseCase(UCID_saveAGR,UC_saveAGR,TH_saveAGR
                    ,TABLE_TRANSACTION_HEALTH_AGR);
        if(ucname==UCID_deleteAGR)
            return new UseCase(UCID_deleteAGR,UC_deleteAGR,TH_deleteAGR
                    ,TABLE_TRANSACTION_HEALTH_AGR);
        if(ucname==UCID_saveCheckHealthYear)
            return new UseCase(UCID_saveCheckHealthYear,UC_saveCheckHealthYear
                    ,TH_saveCheckHealthYear,TABLE_TRANSACTION_HEALTH_CHECK_HEALTH_YEAR);
        if(ucname==UCID_deleteCheckHealthYear)
            return new UseCase(UCID_deleteCheckHealthYear,UC_deleteCheckHealthYear
                    ,TH_deleteCheckHealthYear,TABLE_TRANSACTION_HEALTH_CHECK_HEALTH_YEAR);
        if(ucname==UCID_saveConsel)
            return new UseCase(UCID_saveConsel,UC_saveConsel,TH_saveConsel
                    ,TABLE_TRANSACTION_HEALTH_COUNSEL);
        if(ucname==UCID_deleteConsel)
            return new UseCase(UCID_deleteConsel,UC_deleteConsel,TH_deleteConsel
                    ,TABLE_TRANSACTION_HEALTH_COUNSEL);
        if(ucname==UCID_saveDental)
            return new UseCase(UCID_saveDental,UC_saveDental,TH_saveDental
                    ,TABLE_TRANSACTION_HEALTH_DENTAL);
        if(ucname==UCID_deleteDental)
            return new UseCase(UCID_deleteDental,UC_deleteDental,TH_deleteDental
                    ,TABLE_TRANSACTION_HEALTH_DENTAL);
        if(ucname==UCID_saveEfficiency)
            return new UseCase(UCID_saveEfficiency,UC_saveEfficiency,TH_saveEfficiency
                    ,TABLE_TRANSACTION_HEALTH_EFFICIENCY);
        if(ucname==UCID_deleteEfficiency)
            return new UseCase(UCID_deleteEfficiency,UC_deleteEfficiency,TH_deleteEfficiency
                    ,TABLE_TRANSACTION_HEALTH_EFFICIENCY);
        if(ucname==UCID_saveMaim)
            return new UseCase(UCID_saveMaim,UC_saveMaim,TH_saveMaim,TABLE_BASE_HEALTH_MAIM);
        if(ucname==UCID_deleteMaim)
            return new UseCase(UCID_deleteMaim,UC_deleteMaim,TH_deleteMaim,TABLE_BASE_HEALTH_MAIM);
        if(ucname==UCID_savePPcare)
            return new UseCase(UCID_savePPcare,UC_savePPcare,TH_savePPcare
                    ,TABLE_TRANSACTION_HEALTH_PP_CARE);
        if(ucname==UCID_deletePPcare)
            return new UseCase(UCID_deletePPcare,UC_deletePPcare,TH_deletePPcare
                    ,TABLE_TRANSACTION_HEALTH_PP_CARE);
        if(ucname==UCID_savePerson)
            return new UseCase(UCID_savePerson,UC_savePerson,TH_savePerson
                    ,TABLE_TRANSACTION_HEALTH_FAMILY);
        if(ucname==UCID_deleteFamily)
            return new UseCase(UCID_deleteFamily,UC_deleteFamily,TH_deleteFamily
                    ,TABLE_TRANSACTION_HEALTH_FAMILY);
        if(ucname==UCID_saveSetupSurvey)
            return new UseCase(UCID_saveSetupSurvey,UC_saveSetupSurvey,TH_saveSetupSurvey
                    ,TABLE_BASE_HEALTH_SURVEY);
        if(ucname==UCID_deleteSetupSurvey)
            return new UseCase(UCID_deleteSetupSurvey,UC_deleteSetupSurvey,TH_deleteSetupSurvey
                    ,TABLE_BASE_HEALTH_SURVEY);
        if(ucname==UCID_saveNutrition)
            return new UseCase(UCID_saveNutrition,UC_saveNutrition,TH_saveNutrition
                    ,TABLE_TRANSACTION_HEALTH_NUTRITION);
        if(ucname==UCID_deleteNutrition)
            return new UseCase(UCID_deleteNutrition,UC_deleteNutrition,TH_deleteNutrition
                    ,TABLE_TRANSACTION_HEALTH_NUTRITION);
        if(ucname==UCID_saveVisitHome)
            return new UseCase(UCID_saveVisitHome,UC_saveVisitHome,TH_saveVisitHome
                    ,TABLE_TRANSACTION_HEALTH_VISIT_HOME_VITALSIGN);
        if(ucname==UCID_deleteVisitHome)
            return new UseCase(UCID_deleteVisitHome,UC_deleteVisitHome,TH_deleteVisitHome
                    ,TABLE_TRANSACTION_HEALTH_VISIT_HOME_VITALSIGN);
        if(ucname==UCID_deleteVitalSignVisitHome)
            return new UseCase(UCID_deleteVitalSignVisitHome,UC_deleteVitalSignVisitHome
                    ,TH_deleteVitalSignVisitHome,TABLE_TRANSACTION_HEALTH_VISIT_HOME_VITALSIGN);
        if(ucname==UCID_saveFpWoman)
            return new UseCase(UCID_saveFpWoman,UC_saveFpWoman,TH_saveFpWoman
                    ,TABLE_TRANSACTION_HEALTH_FAMILY_PLANNING);
        if(ucname==UCID_deleteFpWoman)
            return new UseCase(UCID_deleteFpWoman,UC_deleteFpWoman,TH_deleteFpWoman
                    ,TABLE_TRANSACTION_HEALTH_FAMILY_PLANNING);
        if(ucname==UCID_saveCheckHealth)
            return new UseCase(UCID_saveCheckHealth,UC_saveCheckHealth,TH_saveCheckHealth
                    ,TABLE_TRANSACTION_HEALTH_CHECK_HEALTH);
        if(ucname==UCID_deleteCheckHealth)
            return new UseCase(UCID_deleteCheckHealth,UC_deleteCheckHealth,TH_deleteCheckHealth
                    ,TABLE_TRANSACTION_HEALTH_CHECK_HEALTH);
        if(ucname==UCID_saveAfterMchMother)
            return new UseCase(UCID_saveAfterMchMother,UC_saveAfterMchMother
                    ,TH_saveAfterMchMother,TABLE_TRANSACTION_HEALTH_POSTPARTUM);
        if(ucname==UCID_deleteAfterMchMother)
            return new UseCase(UCID_deleteAfterMchMother,UC_deleteAfterMchMother
                    ,TH_deleteAfterMchMother,TABLE_TRANSACTION_HEALTH_POSTPARTUM);
        if(ucname==UCID_printPP)
            return new UseCase(UCID_printPP,UC_printPP,TH_printPP,TABLE_TRANSACTION_HEALTH_PP_CARE);
        if(ucname==UCID_printAfterMchMother)
            return new UseCase(UCID_printAfterMchMother,UC_printAfterMchMother
                    ,TH_printAfterMchMother,TABLE_TRANSACTION_HEALTH_FAMILY);
        if(ucname==UCID_printBeforMch)
            return new UseCase(UCID_printBeforMch,UC_printBeforMch,TH_printBeforMch
                    ,TABLE_TRANSACTION_HEALTH_FAMILY);
        if(ucname==UCID_printBornMchMother)
            return new UseCase(UCID_printBornMchMother,UC_printBornMchMother
                    ,TH_printBornMchMother,TABLE_TRANSACTION_HEALTH_DELIVERY);
        if(ucname==UCID_printlEPI)
            return new UseCase(UCID_printlEPI,UC_printlEPI,TH_printlEPI
                    ,TABLE_TRANSACTION_HEALTH_FAMILY);
        if(ucname==UCID_printGrowHistory)
            return new UseCase(UCID_printGrowHistory,UC_printGrowHistory,TH_printGrowHistory,TABLE_TRANSACTION_HEALTH_FAMILY);
        if(ucname==UCID_deleteHome)
            return new UseCase(UCID_deleteHome,UC_deleteHome,TH_deleteHome,TABLE_TRANSACTION_HEALTH_HOME);
        if(ucname==UCID_saveVaccineGroup)
            return new UseCase(UCID_saveVaccineGroup,UC_saveVaccineGroup
                    ,TH_saveVaccineGroup,TABLE_BASE_HEALTH_EPI_GROUP);
        if(ucname==UCID_deleteVaccineGroup)
            return new UseCase(UCID_deleteVaccineGroup,UC_deleteVaccineGroup
                    ,TH_deleteVaccineGroup,TABLE_BASE_HEALTH_EPI_GROUP);
        if(ucname==UCID_saveSetupMaim)
            return new UseCase(UCID_saveSetupMaim,UC_saveSetupMaim,TH_saveSetupMaim
                    ,TABLE_BASE_HEALTH_MAIM);
        if(ucname==UCID_deleteSetupMaimType)
            return new UseCase(UCID_deleteSetupMaimType,UC_deleteSetupMaimType
                    ,TH_deleteSetupMaimType,TABLE_BASE_HEALTH_MAIM);
        if(ucname==UCID_saveSetupDisease)
            return new UseCase(UCID_saveSetupDisease,UC_saveSetupDisease
                    ,TH_saveSetupDisease,TABLE_BASE_HEALTH_DISEASE);
        if(ucname==UCID_deleteSetupDisease)
            return new UseCase(UCID_deleteSetupDisease,UC_deleteSetupDisease
                    ,TH_deleteSetupDisease,TABLE_BASE_HEALTH_DISEASE);
        if(ucname==UCID_saveSetupAgeSurvay)
            return new UseCase(UCID_saveSetupAgeSurvay,UC_saveSetupAgeSurvay
                    ,TH_saveSetupAgeSurvay,TABLE_BASE_HEALTH_AGE_SURVEY);
        if(ucname==UCID_deleteSetupAgeSurvey)
            return new UseCase(UCID_deleteSetupAgeSurvey,UC_deleteSetupAgeSurvey
                    ,TH_deleteSetupAgeSurvey,TABLE_BASE_HEALTH_AGE_SURVEY);
        if(ucname==UCID_saveBornMch)
            return new UseCase(UCID_saveBornMch,UC_saveBornMch,TH_saveBornMch
                    ,TABLE_TRANSACTION_HEALTH_DELIVERY);
        if(ucname==UCID_deleteBornMch)
            return new UseCase(UCID_deleteBornMch,UC_deleteBornMch,TH_deleteBornMch
                    ,TABLE_TRANSACTION_HEALTH_DELIVERY);
        if(ucname==UCID_saveGrow)
            return new UseCase(UCID_saveGrow,UC_saveGrow,TH_saveGrow,TABLE_TRANSACTION_HEALTH_GROW);
        if(ucname==UCID_deleteGrow)
            return new UseCase(UCID_deleteGrow,UC_deleteGrow,TH_deleteGrow,TABLE_TRANSACTION_HEALTH_GROW);
        if(ucname==UCID_saveEPI)
            return new UseCase(UCID_saveEPI,UC_saveEPI,TH_saveEPI,TABLE_TRANSACTION_HEALTH_EPI);
        if(ucname==UCID_deleteEPI)
            return new UseCase(UCID_deleteEPI,UC_deleteEPI,TH_deleteEPI,TABLE_TRANSACTION_HEALTH_EPI);
        if(ucname==UCID_SaveEpiOutSite)
            return new UseCase(UCID_SaveEpiOutSite,UC_SaveEpiOutSite
                    ,TH_SaveEpiOutSite,TABLE_TRANSACTION_HEALTH_EPI_OUTSITE);
        if(ucname==UCID_deleteEpiOutSite)
            return new UseCase(UCID_deleteEpiOutSite,UC_deleteEpiOutSite
                    ,TH_deleteEpiOutSite,TABLE_TRANSACTION_HEALTH_EPI_OUTSITE);
        if(ucname==UCID_savePP)
            return new UseCase(UCID_savePP,UC_savePP,TH_savePP
                    ,TABLE_TRANSACTION_HEALTH_PP_CARE);
        if(ucname==UCID_deletePP)
            return new UseCase(UCID_deletePP,UC_deletePP,TH_deletePP
                    ,TABLE_TRANSACTION_HEALTH_PP_CARE);
        if(ucname==UCID_savePregnancy)
            return new UseCase(UCID_savePregnancy,UC_savePregnancy
                    ,TH_savePregnancy,TABLE_TRANSACTION_HEALTH_PREGNANCY);
        if(ucname==UCID_deletePregnancy)
            return new UseCase(UCID_deletePregnancy,UC_deletePregnancy
                    ,TH_deletePregnancy,TABLE_TRANSACTION_HEALTH_PREGNANCY);
        if(ucname==UCID_saveAnc)
            return new UseCase(UCID_saveAnc,UC_saveAnc,TH_saveAnc
                    ,TABLE_TRANSACTION_HEALTH_ANC);
        if(ucname==UCID_deleteAnc)
            return new UseCase(UCID_deleteAnc,UC_deleteAnc,TH_deleteAnc
                    ,TABLE_TRANSACTION_HEALTH_ANC);
        if(ucname==UCID_saveVillage)
            return new UseCase(UCID_saveVillage,UC_saveVillage,TH_saveVillage
                    ,TABLE_TRANSACTION_HEALTH_VILLAGE);
        if(ucname==UCID_deleteVillage)
            return new UseCase(UCID_deleteVillage,UC_deleteVillage,TH_deleteVillage
                    ,TABLE_TRANSACTION_HEALTH_VILLAGE);
        if(ucname==UCID_saveSchool)
            return new UseCase(UCID_saveSchool,UC_saveSchool,TH_saveSchool
                    ,TABLE_TRANSACTION_HEALTH_SCHOOL);
        if(ucname==UCID_deleteSchool)
            return new UseCase(UCID_deleteSchool,UC_deleteSchool,TH_deleteSchool
                    ,TABLE_TRANSACTION_HEALTH_SCHOOL);
        if(ucname==UCID_saveTemple)
            return new UseCase(UCID_saveTemple,UC_saveTemple,TH_saveTemple
                    ,TABLE_TRANSACTION_HEALTH_TEMPLE);
        if(ucname==UCID_deleteTemple)
            return new UseCase(UCID_deleteTemple,UC_deleteTemple,TH_deleteTemple
                    ,TABLE_TRANSACTION_HEALTH_TEMPLE);
        if(ucname==UCID_saveCompany)
            return new UseCase(UCID_saveCompany,UC_saveCompany,TH_saveCompany
                    ,TABLE_TRANSACTION_HEALTH_COMPANY);
        if(ucname==UCID_deleteCompany)
            return new UseCase(UCID_deleteCompany,UC_deleteCompany,TH_deleteCompany
                    ,TABLE_TRANSACTION_HEALTH_COMPANY);
        if(ucname==UCID_saveWater)
            return new UseCase(UCID_saveWater,UC_saveWater,TH_saveWater
                    ,TABLE_TRANSACTION_HEALTH_WATER);
        if(ucname==UCID_deleteWater)
            return new UseCase(UCID_deleteWater,UC_deleteWater,TH_deleteWater
                    ,TABLE_TRANSACTION_HEALTH_WATER);
        if(ucname==UCID_saveMarket)
            return new UseCase(UCID_saveMarket,UC_saveMarket,TH_saveMarket
                    ,TABLE_TRANSACTION_HEALTH_MARKET
                    );
        if(ucname==UCID_deleteMarket)
            return new UseCase(UCID_deleteMarket,UC_deleteMarket,TH_deleteMarket
                    ,TABLE_TRANSACTION_HEALTH_MARKET);
        if(ucname==UCID_saveResource)
            return new UseCase(UCID_saveResource,UC_saveResource,TH_saveResource
                    ,TABLE_TRANSACTION_HEALTH_RESOURCE);
        if(ucname==UCID_deleteResource)
            return new UseCase(UCID_deleteResource,UC_deleteResource,TH_deleteResource
                    ,TABLE_TRANSACTION_HEALTH_RESOURCE);
        if(ucname==UCID_saveAGR)
            return new UseCase(UCID_saveAGR,UC_saveAGR,TH_saveAGR,TABLE_TRANSACTION_HEALTH_AGR);
        if(ucname==UCID_deleteAGR)
            return new UseCase(UCID_deleteAGR,UC_deleteAGR,TH_deleteAGR,TABLE_TRANSACTION_HEALTH_AGR);
        if(ucname==UCID_saveCheckHealthYear)
            return new UseCase(UCID_saveCheckHealthYear,UC_saveCheckHealthYear
                    ,TH_saveCheckHealthYear,TABLE_TRANSACTION_HEALTH_CHECK_HEALTH_YEAR);
        if(ucname==UCID_deleteCheckHealthYear)
            return new UseCase(UCID_deleteCheckHealthYear,UC_deleteCheckHealthYear
                    ,TH_deleteCheckHealthYear,TABLE_TRANSACTION_HEALTH_CHECK_HEALTH_YEAR);
        if(ucname==UCID_saveConsel)
            return new UseCase(UCID_saveConsel,UC_saveConsel,TH_saveConsel
                    ,TABLE_TRANSACTION_HEALTH_COUNSEL);
        if(ucname==UCID_deleteConsel)
            return new UseCase(UCID_deleteConsel,UC_deleteConsel,TH_deleteConsel
                    ,TABLE_TRANSACTION_HEALTH_COUNSEL);
        if(ucname==UCID_saveDental)
            return new UseCase(UCID_saveDental,UC_saveDental,TH_saveDental
                    ,TABLE_TRANSACTION_HEALTH_DENTAL);
        if(ucname==UCID_deleteDental)
            return new UseCase(UCID_deleteDental,UC_deleteDental,TH_deleteDental
                    ,TABLE_TRANSACTION_HEALTH_DENTAL);
        if(ucname==UCID_saveEfficiency)
            return new UseCase(UCID_saveEfficiency,UC_saveEfficiency
                    ,TH_saveEfficiency,TABLE_TRANSACTION_HEALTH_EFFICIENCY);
        if(ucname==UCID_deleteEfficiency)
            return new UseCase(UCID_deleteEfficiency,UC_deleteEfficiency
                    ,TH_deleteEfficiency,TABLE_TRANSACTION_HEALTH_EFFICIENCY);
        if(ucname==UCID_saveMaim)
            return new UseCase(UCID_saveMaim,UC_saveMaim,TH_saveMaim
                    ,TABLE_BASE_HEALTH_MAIM);
        if(ucname==UCID_deleteMaim)
            return new UseCase(UCID_deleteMaim,UC_deleteMaim,TH_deleteMaim
                    ,TABLE_BASE_HEALTH_MAIM);
        if(ucname==UCID_savePPcare)
            return new UseCase(UCID_savePPcare,UC_savePPcare,TH_savePPcare
                    ,TABLE_TRANSACTION_HEALTH_PP_CARE);
        if(ucname==UCID_deletePPcare)
            return new UseCase(UCID_deletePPcare,UC_deletePPcare,TH_deletePPcare
                    ,TABLE_TRANSACTION_HEALTH_PP_CARE);
        if(ucname==UCID_savePerson)
            return new UseCase(UCID_savePerson,UC_savePerson,TH_savePerson
                    ,TABLE_TRANSACTION_HEALTH_FAMILY);
        if(ucname==UCID_deleteFamily)
            return new UseCase(UCID_deleteFamily,UC_deleteFamily,TH_deleteFamily
                    ,TABLE_TRANSACTION_HEALTH_FAMILY);
        if(ucname==UCID_saveSetupSurvey)
            return new UseCase(UCID_saveSetupSurvey,UC_saveSetupSurvey,TH_saveSetupSurvey
                    ,TABLE_BASE_HEALTH_SURVEY);
        if(ucname==UCID_deleteSetupSurvey)
            return new UseCase(UCID_deleteSetupSurvey,UC_deleteSetupSurvey
                    ,TH_deleteSetupSurvey,TABLE_BASE_HEALTH_SURVEY);
        if(ucname==UCID_saveNutrition)
            return new UseCase(UCID_saveNutrition,UC_saveNutrition
                    ,TH_saveNutrition,TABLE_TRANSACTION_HEALTH_NUTRITION);
        if(ucname==UCID_deleteNutrition)
            return new UseCase(UCID_deleteNutrition,UC_deleteNutrition
                    ,TH_deleteNutrition,TABLE_TRANSACTION_HEALTH_NUTRITION);
        if(ucname==UCID_saveVisitHome)
            return new UseCase(UCID_saveVisitHome,UC_saveVisitHome
                    ,TH_saveVisitHome,TABLE_TRANSACTION_HEALTH_VISIT_HOME);
        if(ucname==UCID_deleteVisitHome)
            return new UseCase(UCID_deleteVisitHome,UC_deleteVisitHome
                    ,TH_deleteVisitHome,TABLE_TRANSACTION_HEALTH_VISIT_HOME);
        if(ucname==UCID_deleteVitalSignVisitHome)
            return new UseCase(UCID_deleteVitalSignVisitHome,UC_deleteVitalSignVisitHome
                    ,TH_deleteVitalSignVisitHome,TABLE_TRANSACTION_HEALTH_VISIT_HOME_VITALSIGN);
        if(ucname==UCID_saveFpWoman)
            return new UseCase(UCID_saveFpWoman,UC_saveFpWoman,TH_saveFpWoman
                    ,TABLE_TRANSACTION_HEALTH_FAMILY_PLANNING);
        if(ucname==UCID_deleteFpWoman)
            return new UseCase(UCID_deleteFpWoman,UC_deleteFpWoman,TH_deleteFpWoman
                    ,TABLE_TRANSACTION_HEALTH_FAMILY_PLANNING);
        if(ucname==UCID_saveCheckHealth)
            return new UseCase(UCID_saveCheckHealth,UC_saveCheckHealth
                    ,TH_saveCheckHealth,TABLE_TRANSACTION_HEALTH_CHECK_HEALTH);
        if(ucname==UCID_deleteCheckHealth)
            return new UseCase(UCID_deleteCheckHealth,UC_deleteCheckHealth
                    ,TH_deleteCheckHealth,TABLE_TRANSACTION_HEALTH_CHECK_HEALTH);
        if(ucname==UCID_deletePersonPayment)
            return new UseCase(UCID_deletePersonPayment,UC_deletePersonPayment
                    ,TH_deletePersonPayment,TABLE_TRANSACTION_PATIENT_PAYMENT);
        if(ucname==UCID_saveAfterMchMother)
            return new UseCase(UCID_saveAfterMchMother,UC_saveAfterMchMother
                    ,TH_saveAfterMchMother,TABLE_TRANSACTION_HEALTH_POSTPARTUM);
        if(ucname==UCID_deleteAfterMchMother)
            return new UseCase(UCID_deleteAfterMchMother,UC_deleteAfterMchMother
                    ,TH_deleteAfterMchMother,TABLE_TRANSACTION_HEALTH_POSTPARTUM);
        if(ucname==UCID_printPP)
            return new UseCase(UCID_printPP,UC_printPP,TH_printPP
                    ,TABLE_TRANSACTION_HEALTH_PP_CARE);
        if(ucname==UCID_printAfterMchMother)
            return new UseCase(UCID_printAfterMchMother,UC_printAfterMchMother
                    ,TH_printAfterMchMother,TABLE_TRANSACTION_HEALTH_PP_CARE);
        if(ucname==UCID_printBeforMch)
            return new UseCase(UCID_printBeforMch,UC_printBeforMch
                    ,TH_printBeforMch,TABLE_TRANSACTION_HEALTH_ANC);
        if(ucname==UCID_printBornMchMother)
            return new UseCase(UCID_printBornMchMother,UC_printBornMchMother
                    ,TH_printBornMchMother,TABLE_TRANSACTION_HEALTH_DELIVERY);
        if(ucname==UCID_printlEPI)
            return new UseCase(UCID_printlEPI,UC_printlEPI,TH_printlEPI
                    ,TABLE_TRANSACTION_HEALTH_EPI);
        if(ucname==UCID_printGrowHistory)
            return new UseCase(UCID_printGrowHistory,UC_printGrowHistory
                    ,TH_printGrowHistory,TABLE_TRANSACTION_HEALTH_GROW);
        if(ucname==UCID_deleteHome)
            return new UseCase(UCID_deleteHome,UC_deleteHome,TH_deleteHome
                    ,TABLE_TRANSACTION_HEALTH_HOME);
        if(ucname==UCID_saveVaccineGroup)
            return new UseCase(UCID_saveVaccineGroup,UC_saveVaccineGroup
                    ,TH_saveVaccineGroup,TABLE_BASE_HEALTH_EPI_GROUP);
        if(ucname==UCID_deleteVaccineGroup)
            return new UseCase(UCID_deleteVaccineGroup,UC_deleteVaccineGroup
                    ,TH_deleteVaccineGroup,TABLE_BASE_HEALTH_EPI_GROUP);

        // เพิ่มเติมของ Hos
        if(ucname==UCID_cancelBillingByBid)
            return new UseCase(UCID_cancelBillingByBid,UC_cancelBillingByBid
                    ,TH_cancelBillingByBid,TABLE_TRANSACTION_BILLING);
        if(ucname==UCID_cancelBillingInvoiceByBIno)
            return new UseCase(UCID_cancelBillingInvoiceByBIno
                    ,UC_cancelBillingInvoiceByBIno,TH_cancelBillingInvoiceByBIno,TABLE_TRANSACTION_BILLING);
        if(ucname==UCID_deleteDxIcd10)
            return new UseCase(UCID_deleteDxIcd10,UC_deleteDxIcd10
                    ,TH_deleteDxIcd10,TABLE_TRANSACTION_DIAG_ICD10);
        if(ucname==UCID_deleteDxIcd9)
            return new UseCase(UCID_deleteDxIcd9,UC_deleteDxIcd9,TH_deleteDxIcd9
                    ,TABLE_TRANSACTION_DIAG_ICD9);
        if(ucname==UCID_deleteParticipateOr)
            return new UseCase(UCID_deleteParticipateOr,UC_deleteParticipateOr
                    ,TH_deleteParticipateOr,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_deletePhysicalExam)
            return new UseCase(UCID_deletePhysicalExam,UC_deletePhysicalExam
                    ,TH_deletePhysicalExam,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_deletePrimarySymtomByVid)
            return new UseCase(UCID_deletePrimarySymtomByVid,UC_deletePrimarySymtomByVid
                    ,TH_deletePrimarySymtomByVid,TABLE_TRANSACTION_VISIT_PRINARY_SYMPTOM);
        if(ucname==UCID_deleteVisitPayment)
            return new UseCase(UCID_deleteVisitPayment,UC_deleteVisitPayment
                    ,TH_deleteVisitPayment,TABLE_TRANSACTION_VISIT_PAYMENT);
        if(ucname==UCID_saveDxByStat)
            return new UseCase(UCID_saveDxByStat,UC_saveDxByStat,TH_saveDxByStat
                    ,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_savePatient)
            return new UseCase(UCID_savePatient,UC_savePatient,TH_savePatient,TABLE_TRANSACTION_PATIENT);
        if(ucname==UCID_savePrimarySymptom)
            return new UseCase(UCID_savePrimarySymptom,UC_savePrimarySymptom
                    ,TH_savePrimarySymptom,TABLE_TRANSACTION_VISIT_PRINARY_SYMPTOM);
        if(ucname==UCID_saveResultLab)
            return new UseCase(UCID_saveResultLab,UC_saveResultLab
                    ,TH_saveResultLab,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_saveResultXray)
            return new UseCase(UCID_saveResultXray,UC_saveResultXray
                    ,TH_saveResultXray,TABLE_TRANSACTION_RESULT_XRAY);
        if(ucname==UCID_requestOrder)
            return new UseCase(UCID_requestOrder,UC_requestOrder
                    ,TH_requestOrder,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_calculateBillingInvoice)
            return new UseCase(UCID_calculateBillingInvoice
                    ,UC_calculateBillingInvoice,TH_calculateBillingInvoice
                    ,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_sendVisitBackWard)
            return new UseCase(UCID_sendVisitBackWard,UC_sendVisitBackWard
                    ,TH_sendVisitBackWard,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_printOrderSelected)
            return new UseCase(UCID_printOrderSelected,UC_printOrderSelected
                    ,TH_printOrderSelected,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_saveAdmitVisit)
            return new UseCase(UCID_saveAdmitVisit,UC_saveAdmitVisit
                    ,TH_saveAdmitVisit,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_printIpdNameCard)
            return new UseCase(UCID_printIpdNameCard,UC_printIpdNameCard
                    ,TH_printIpdNameCard,TABLE_TRANSACTION_PATIENT);
        if(ucname==UCID_printIpdNameCardAllWard)
            return new UseCase(UCID_printIpdNameCardAllWard,UC_printIpdNameCardAllWard
                    ,TH_printIpdNameCardAllWard,NO_TABLE);
        if(ucname==UCID_saveItem)
            return new UseCase(UCID_saveItem,UC_saveItem,TH_saveItem,TABLE_BASE_ITEM);
        if(ucname==UCID_saveOffice)
            return new UseCase(UCID_saveOffice,UC_saveOffice,TH_saveOffice
                    ,TABLE_BASE_VISIT_OFFICE);
        if(ucname==UCID_saveResultXraySize)
            return new UseCase(UCID_saveResultXraySize,UC_saveResultXraySize
                    ,TH_saveResultXraySize,TABLE_TRANSACTION_RESULT_XRAY_SIZE);
        if(ucname==UCID_saveContract)
            return new UseCase(UCID_saveContract,UC_saveContract,TH_saveContract
                    ,TABLE_BASE_CONTRACT);
        if(ucname==UCID_deleteContract)
            return new UseCase(UCID_deleteContract,UC_deleteContract
                    ,TH_deleteContract,TABLE_BASE_CONTRACT);
        if(ucname==UCID_saveLabResuleDetail)
            return new UseCase(UCID_saveLabResuleDetail,UC_saveLabResuleDetail
                    ,TH_saveLabResuleDetail,TABLE_BASE_LAB_RESULT_DETAIL);
        if(ucname==UCID_deleteLabResutlDetail)
            return new UseCase(UCID_deleteLabResutlDetail,UC_deleteLabResutlDetail
                    ,TH_deleteLabResutlDetail,TABLE_BASE_LAB_RESULT_DETAIL);
        if(ucname==UCID_deleteResultXrayFilmSize)
            return new UseCase(UCID_deleteResultXrayFilmSize,UC_deleteResultXrayFilmSize
                    ,TH_deleteResultXrayFilmSize,TABLE_BASE_LAB_RESULT_DETAIL);
        if(ucname==UCID_saveDrugStandard)
            return new UseCase(UCID_saveDrugStandard,UC_saveDrugStandard
                    ,TH_saveDrugStandard,TABLE_BASE_ITEM_DRUG_STANDARD);
        if(ucname==UCID_deleteDrugStandard)
            return new UseCase(UCID_deleteDrugStandard,UC_deleteDrugStandard
                    ,TH_deleteDrugStandard,TABLE_BASE_ITEM_DRUG_STANDARD);
        if(ucname==UCID_savePatientXn)
            return new UseCase(UCID_savePatientXn,UC_savePatientXn
                    ,TH_savePatientXn,TABLE_TRANSACTION_PATIENT);
        if(ucname==UCID_saveNCD)
            return new UseCase(UCID_saveNCD,UC_saveNCD,TH_saveNCD,TABLE_BASE_NCD_GROUP);
        if(ucname==UCID_printIpdNameChart )
            return new UseCase(UCID_printIpdNameChart ,UC_printIpdNameChart 
                    ,TH_printIpdNameChart ,TABLE_TRANSACTION_PATIENT);
        if(ucname==UCID_printSumByItem16Group)
            return new UseCase(UCID_printSumByItem16Group,UC_printSumByItem16Group
                    ,TH_printSumByItem16Group,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_saveICD10GroupSurveil)
            return new UseCase(UCID_saveICD10GroupSurveil,UC_saveICD10GroupSurveil
                    ,TH_saveICD10GroupSurveil,TABLE_BASE_GROUP_SURVEIL);
        if(ucname==UCID_deleteICD10GroupSurveil)
            return new UseCase(UCID_deleteICD10GroupSurveil,UC_deleteICD10GroupSurveil
                    ,TH_deleteICD10GroupSurveil,TABLE_BASE_GROUP_SURVEIL);
        if(ucname==UCID_orderOldOrder)
            return new UseCase(UCID_orderOldOrder,UC_orderOldOrder
                    ,TH_orderOldOrder,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_deletePatientPayment)
            return new UseCase(UCID_deletePatientPayment,UC_deletePatientPayment
                    ,TH_deletePatientPayment,TABLE_TRANSACTION_PATIENT_PAYMENT);
        if(ucname==UCID_deleteServicePointDoctor)
            return new UseCase(UCID_deleteServicePointDoctor,UC_deleteServicePointDoctor
                    ,TH_deleteServicePointDoctor,TABLE_BASE_EMPLOYEE);
        if(ucname==UCID_saveICD9Setup)
            return new UseCase(UCID_saveICD9Setup,UC_saveICD9Setup
                    ,TH_saveICD9Setup,TABLE_BASE_ICD9);
        if(ucname==UCID_showDialogResultXray)
            return new UseCase(UCID_showDialogResultXray,UC_showDialogResultXray
                    ,TH_showDialogResultXray,TABLE_TRANSACTION_RESULT_XRAY);
        if(ucname==UCID_showDialogHistoryBilling)
            return new UseCase(UCID_showDialogHistoryBilling,UC_showDialogHistoryBilling
                    ,TH_showDialogHistoryBilling,TABLE_TRANSACTION_PATIENT);
        if(ucname==UCID_showDialogHistoryOrder)
            return new UseCase(UCID_showDialogHistoryOrder,UC_showDialogHistoryOrder
                    ,TH_showDialogHistoryOrder,TABLE_TRANSACTION_PATIENT);
        if(ucname==UCID_showDialogHistoryOrderContinue)
            return new UseCase(UCID_showDialogHistoryOrderContinue,UC_showDialogHistoryOrderContinue
                    ,TH_showDialogHistoryOrderContinue,TABLE_TRANSACTION_VISIT);
        if(ucname==UCID_showDialogHistoryVisit)
            return new UseCase(UCID_showDialogHistoryVisit,UC_showDialogHistoryVisit
                    ,TH_showDialogHistoryVisit,TABLE_TRANSACTION_PATIENT);
        if(ucname==UCID_ReportQueryModule)
            return new UseCase(UCID_ReportQueryModule,UC_ReportQueryModule
                    ,TH_ReportQueryModule,TABLE_REPORT_SQL_TAMPLATE);
        if(ucname==UCID_StandardReportModule)
            return new UseCase(UCID_StandardReportModule,UC_StandardReportModule
                    ,TH_StandardReportModule,NO_TABLE);
        return null;
    }
    public String uc_name;
    public String table_name;
    public String uc_name_th;
    public final int usecase_id;

    private UseCase(int ucid, String ucname, String ucnameth, String table) {
        usecase_id = ucid;
        uc_name = ucname;
        uc_name_th = ucnameth;
        table_name = table;
    }
    public static String getUCName(int id)
    {
        if(id == 1) return UC_visitPatient;
        else if(id == 2) return UC_visitFamily;
        else if(id == 3) return UC_dischargeFinancial;
        else if(id == 4) return UC_dropVisitSurvey;
        else if(id == 5) return UC_commitVisitSurvey;
        else if(id == 6) return UC_visitSurvey;
        else if(id == 7) return UC_listVisitSurvey;
        else if(id == 8) return UC_cancelOrderItem;
        else if(id == 9) return UC_cancelSendReferOutLab;
        else if(id == 10) return UC_sendReferOutLab;
        else if(id == 11) return UC_resetPatient;
        else if(id == 12) return UC_verifyOrderItem;
        else if(id == 13) return UC_deletePatient;
        else if(id == 14) return UC_deletePatientAllergy;
        else if(id == 15) return UC_savePatientAllergy;
        else if(id == 16) return UC_deleteVPayment;
        else if(id == 17) return UC_deleteVitalSign;
        else if(id == 18) return UC_saveVitalSign;
        else if(id == 19) return UC_dischargeDoctor;
        else if(id == 20) return UC_dispenseOrderItem;
        else if(id == 21) return UC_downVPaymentPriority;
        else if(id == 22) return UC_upVPaymentPriority;
        else if(id == 23) return UC_executeOrderItem;
        else if(id == 24) return UC_saveAppointment;
        else if(id == 25) return UC_saveDiagIcd10;
        else if(id == 26) return UC_saveDiagIcd9;
        else if(id == 27) return UC_saveOrderItem;
        else if(id == 28) return UC_deleteVAppointment;
        else if(id == 29) return UC_savePatientPayment;
        else if(id == 30) return UC_updateVisitPregnant;
        else if(id == 31) return UC_sendResultLab;
        else if(id == 32) return UC_sendVisit;
        else if(id == 33) return UC_sendXrayResultComplete;
        else if(id == 34) return UC_unlockVisit;
        else if(id == 35) return UC_admit;
        else if(id == 36) return UC_savePatientPaid;
        else if(id == 37) return UC_saveAccident;
        else if(id == 38) return UC_deleteAccident;
        else if(id == 39) return UC_saveRefer;
        else if(id == 40) return UC_deleteRefer;
        else if(id == 41) return UC_observVisit;
        else if(id == 42) return UC_cancelObservVisit;
        else if(id == 43) return UC_dropVisit;
        else if(id == 44) return UC_printAppointmentCard;
        else if(id == 45) return UC_printAppointmentList;
        else if(id == 46) return UC_printReferIO;
        else if(id == 47) return UC_remainVisit;
        else if(id == 48) return UC_reverseAdmit;
        else if(id == 49) return UC_printBillingInvoiceItem;
        else if(id == 50) return UC_printSumByBillingGroup;
        else if(id == 51) return UC_printChronicList;
        else if(id == 52) return UC_printDrugSticker;
        else if(id == 53) return UC_printIndex;
        else if(id == 54) return UC_printOPDCard;
        else if(id == 55) return UC_printSummary;
        else if(id == 56) return UC_printListSurveil;
        else if(id == 57) return UC_reverseDoctor;
        else if(id == 58) return UC_reverseFinancial;
        else if(id == 59) return UC_saveChronic;
        else if(id == 60) return UC_deleteChronic;
        else if(id == 61) return UC_saveDeath;
        else if(id == 62) return UC_deleteDeath;
        else if(id == 63) return UC_saveOrderReturn;
        else if(id == 64) return UC_saveSurveil;
        else if(id == 65) return UC_visitFromVAppointment;
        else if(id == 66) return UC_saveRemainResultLab;
        else if(id == 67) return UC_printXnIndex;
        else if(id == 68) return UC_cancelBillingInvoice;
        else if(id == 69) return UC_cancelOrderDrugContinue;
        else if(id == 70) return UC_continueOrderItem;
        else if(id == 71) return UC_saveWard;
        else if(id == 72) return UC_saveServicePoint;
        else if(id == 73) return UC_deleteServicePoint;
        else if(id == 74) return UC_deleteWard;
        else if(id == 75) return UC_saveAutoItem;
        else if(id == 76) return UC_deleteAutoItem;
        else if(id == 77) return UC_saveTemplateDx;
        else if(id == 78) return UC_deleteTemplateDx;
        else if(id == 79) return UC_printResultLab;
        else if(id == 80) return UC_savePatientHistory;
        else if(id == 81) return UC_saveGuide;
        else if(id == 82) return UC_saveBorrowFilmXray;
        else if(id == 83) return UC_deleteBorrowFilmXray;
        else if(id == 84) return UC_saveBorrowOpdCard;
        else if(id == 85) return UC_deleteBorrowOpdCard;
        else if(id == 86) return UC_saveDrugSetGroup;
        else if(id == 87) return UC_deleteDrugSetGroup;
        else if(id == 88) return UC_deleteDoseDrugSet;
        else if(id == 89) return UC_saveContractAdjust;
        else if(id == 90) return UC_deleteContractAdjust;
        else if(id == 91) return UC_savePlan;
        else if(id == 92) return UC_deletePlan;
        else if(id == 93) return UC_saveLabResuleType;
        else if(id == 94) return UC_deleteLabResuleType;
        else if(id == 95) return UC_saveDx;
        else if(id == 96) return UC_addItemXray;
        else if(id == 97) return UC_saveDrugStandardMapItem;
        else if(id == 98) return UC_deleteDrugStandardMapItem;
        else if(id == 99) return UC_saveDrugInteraction;
        else if(id == 100) return UC_deleteDrugInteraction;
        else if(id == 101) return UC_showVisitPaymentCancel;
        else if(id == 102) return UC_saveBodyOrgan;
        else if(id == 103) return UC_deleteBodyOrgan;
        else if(id == 104) return UC_saveGroupIcd10;
        else if(id == 105) return UC_deleteGroupIcd10;
        else if(id == 106) return UC_showHistoryXN;
        else if(id == 107) return UC_savePhysicalExam;
        else if(id == 108) return UC_readPatientByHn;
        else if(id == 109) return UC_readVisitPatientByVn;
        else if(id == 110) return UC_readFamilyByFamilyId;
        else if(id == 111) return UC_updateVisitEmergency;
        else if(id == 112) return UC_saveItem16Group;
        else if(id == 113) return UC_deleteItem16Group;
        else if(id == 114) return UC_saveNCDGroup;
        else if(id == 115) return UC_deleteNCDGroup;
        else if(id == 116) return UC_listNCDHistory;
        else if(id == 117) return UC_saveGuideHealthEducation;
        else if(id == 118) return UC_saveEmployee;
        else if(id == 119) return UC_saveVisitNCD;
        else if(id == 120) return UC_listVisitXrayByDatePid;
        else if(id == 121) return UC_saveGroupChronicICD10;
        else if(id == 122) return UC_saveDrugDoseShortcut;
        else if(id == 123) return UC_deleteDrugDoseShortcut;
        else if(id == 124) return UC_updateVisitAppointment;
        else if(id == 125) return UC_updateVisitRefer;
        else if(id == 126) return UC_printGuide;
        else if(id == 127) return UC_saveAppointmentTemplate;
        else if(id == 128) return UC_deleteAppointmentTemplate;
        else if(id == 129) return UC_printBilling;
        else if(id == 130) return UC_printVisitSlipNew;
        else if(id == 131) return UC_deleteWound;
        else if(id == 132) return UC_saveWound;
        else if(id == 133) return UC_savePatientLabreferin;
        else if(id == 134) return UC_dischargeIPD;
        else if(id == 135) return UC_saveOrderHome;
        else if(id == 136) return UC_readPreviousVisit;
        else if(id == 137) return UC_readNextVisit;
        else if(id == 138) return UC_deleteICD10GroupChronic;
        else if(id == 139) return UC_saveAutoReportBug;
        else if(id == 140) return UC_dispenseOrders;
        else if(id == 141) return UC_printEmptyDrugRx;
        else if(id == 142) return UC_printMedicalCert;
        else if(id == 143) return UC_saveNews;
        else if(id == 144) return UC_setRemainZero;
        else if(id == 145) return UC_saveItemPrice;
        else if(id == 138) return UC_saveICD9Setup;
        else if(id == 146) return UC_saveAuthen;
        else if(id == 147) return UC_updatePatch;
        else return "";
    }
}
