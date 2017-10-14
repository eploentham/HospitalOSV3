/*
 * CheckEyes.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2548, 18:22 ¹.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Noom
 */
public class CheckEyes extends Persistent{
    private static String init = "";
    public String  t_health_check_eyes_id  = init;
    public String  t_health_student_id  = init;
    public String  t_health_visit_school_id = init; 
    public String  f_answer_id  = init;
    public String  f_health_check_eyes_id  = init;
    public String  check_eyes_note = init;
    public String  check_eyes_remark = init;
    public String  b_visit_refer_office_id = init;
    public String  check_eyes_record_time  = init;
    public String  check_eyes_modify_time  = init;
    public String  check_eyes_cancle_time  = init;
    public String  check_eyes_staff_record  = init;
    public String  check_eyes_staff_modify  = init;
    public String  check_eyes_staff_cancle  = init;
    public String  check_eyes_active   = init;

    
    /** Creates a new instance of Counsel */
    public CheckEyes() {
    }
    
}
