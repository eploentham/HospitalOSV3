/*
 * CheckFeBlood.java
 *
 * Created on 13 ¡Ã¡¯Ò¤Á 2548, 10:40 ¹.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Noom
 */
public class CheckFeBlood extends Persistent{
    private static String init = "";
    public String  t_health_check_fe_blood_id  = init;
    public String  t_health_student_id  = init;
    public String  t_health_visit_school_id = init; 
    public String  f_answer_id  = init;
    public String  f_answer_check_fe_blood_id  = init;
    public String  b_visit_refer_office_id = init;
    public String  check_fe_blood_note = init;
    public String  check_fe_blood_remark = init;
    public String  check_fe_blood_record_time  = init;
    public String  check_fe_blood_modify_time  = init;
    public String  check_fe_blood_cancle_time  = init;
    public String  check_fe_blood_staff_record  = init;
    public String  check_fe_blood_staff_modify  = init;
    public String  check_fe_blood_staff_cancle  = init;
    public String  check_fe_blood_active   = init;
    
    
    /** Creates a new instance of Counsel */
    public CheckFeBlood() {
    }
    
}
