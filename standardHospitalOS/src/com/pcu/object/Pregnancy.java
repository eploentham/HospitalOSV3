/*
 * Pregnancy.java
 *
 * Created on 7 กรกฎาคม 2548, 10:42 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class Pregnancy extends Persistent{
    private static String init = "";
    public String pregnancy_hn= init;
    public String pregnancy_vn= init;
    public String office_id= init;
    public String pregnancy_gravida_number= init;
    public String pregnancy_menses_issue_date= init;
    public String pregnancy_menses_expire_date= init;
    public String pregnancy_birthcontrol= init;
    public String pregnancy_abnomal= init;
    public String pregnancy_result= init;
    public String pregnancy_notice= init;
    public String pregnancy_staff_record= init;
    public String pregnancy_staff_modify= init;
    public String pregnancy_staff_cancel= init;
    public String record_date_time= init;
    public String modify_date_time= init;
    public String cancel_date_time= init;
    public String visit_id= init;
    public String patient_id= init;
    public String active= init;
    public String family_id = init;
    public String survey_date = init;
    public String pregnancy_p = init;
    public String pregnancy_a = init;
    public String pregnancy_l = init;
    /** Creates a new instance of Pregnancy */
    public Pregnancy() {
    }
    
}
