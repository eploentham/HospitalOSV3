/*
 * Counsel.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2548, 18:22 ¹.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Noom
 */
public class Dental extends Persistent{
    private static String init = "";
    public String t_health_dental_id = init;
    public String dental_num_tooth = init;
    public String dental_num_bad_tooth = init;
    public String dental_num_milktooth = init;
    public String dental_num_bad_milktooth = init;
    public String f_health_gum_level_id = init;
    public String dental_detail = init;
    public String dental_remark = init;
    public String dental_false_teeth_need = init;
    public String dental_record_time = init;
    public String dental_modify_time = init;
    public String dental_cancle_time = init;
    public String dental_staff_record =init;
    public String dental_staff_modify =init;
    public String dental_staff_cancle =init;
    public String dental_active =init;
    public String visit_id = init;
    public String patient_id = init;
    public String family_id = init;
    public String survey_date = init;
    
    /** Creates a new instance of Dental */
    public Dental() {
    }
    
}
