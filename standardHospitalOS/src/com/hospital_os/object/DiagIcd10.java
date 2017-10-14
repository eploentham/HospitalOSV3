package com.hospital_os.object;

public class DiagIcd10 extends X39Persistent {

    final private String idtable = "140";/*"133";*/

    public String icd10_code = "";
    public String type = "";
    public String diagnosis_date = "";
    public String dischange_note = "";
    public String doctor_kid = "";
    public String clinic_kid = "";
    public String diag_icd10_staff_record = "";
    public String diag_icd10_record_date_time = "";
    public String diag_icd10_staff_update = "";
    public String diag_icd10_update_date_time = "";
    public String diag_icd10_staff_cancel = "";
    public String diag_icd10_cancel_date_time = "";
    public String diag_icd10_active = "1";
    public String visit_id = "";
    /**เพื่อให้รองรับ primary ได้มากกว่า 1 รายการ ICD9*/
    public String primary_report;

    /**
     * @roseuid 3F658BBB036E
     */
    public DiagIcd10() {
    }

    public static DiagIcd10 initConfig() {
        DiagIcd10 dbObj = new DiagIcd10();
        dbObj.table = "t_diag_icd10";
        dbObj.setObjectId("t_diag_icd10_id");
        dbObj.pk_field = "t_diag_icd10_id";

        dbObj.visit_id = "diag_icd10_vn";
        dbObj.icd10_code = "diag_icd10_number";
        dbObj.type = "f_diag_icd10_type_id";
        dbObj.dischange_note = "diag_icd10_notice";
        dbObj.doctor_kid = "diag_icd10_staff_doctor";
        dbObj.clinic_kid = "b_visit_clinic_id";
        dbObj.diagnosis_date = "diag_icd10_diagnosis_date";
        dbObj.diag_icd10_record_date_time = "diag_icd10_record_date_time";
        dbObj.diag_icd10_staff_record = "diag_icd10_staff_record";
        dbObj.diag_icd10_staff_update = "diag_icd10_staff_update";
        dbObj.diag_icd10_update_date_time = "diag_icd10_update_date_time";
        dbObj.diag_icd10_staff_cancel = "diag_icd10_staff_cancel";
        dbObj.diag_icd10_cancel_date_time = "diag_icd10_cancel_date_time";
        dbObj.diag_icd10_active = "diag_icd10_active";
        dbObj.primary_report = "primary_report";
        return dbObj;
    }

    @Override
    public X39Persistent getInstant(String[] strd) {
        DiagIcd10 dx10 = new DiagIcd10();
        dx10.setStringArray(strd);
        return dx10;
    }

    @Override
    public String[] getStringArray() {

        return new String[]{
                    getObjectId(), icd10_code, type, diagnosis_date, dischange_note, doctor_kid, clinic_kid, diag_icd10_staff_record, diag_icd10_record_date_time, diag_icd10_staff_update, diag_icd10_update_date_time, diag_icd10_staff_cancel, diag_icd10_cancel_date_time, diag_icd10_active, visit_id
                // henbe wait for update database
//        ,primary_report
                };
    }

    @Override
    public void setStringArray(String[] array) {
        setObjectId(array[0]);
        icd10_code = array[1];
        type = array[2];
        diagnosis_date = array[3];
        dischange_note = array[4];
        doctor_kid = array[5];
        clinic_kid = array[6];
        diag_icd10_staff_record = array[7];
        diag_icd10_record_date_time = array[8];
        diag_icd10_staff_update = array[9];
        diag_icd10_update_date_time = array[10];
        diag_icd10_staff_cancel = array[11];
        diag_icd10_cancel_date_time = array[12];
        diag_icd10_active = array[13];
        visit_id = array[14];
        // henbe wait for update database
//        primary_report = array[14];
    }

    @Override
    public String getIdTable() {
        return this.idtable;
    }
}
