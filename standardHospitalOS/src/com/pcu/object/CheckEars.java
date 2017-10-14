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
public class CheckEars extends Persistent{
    private static String init = "";
    public String  t_health_check_ears_id  = init;
    public String  t_health_student_id  = init;
    public String  t_health_visit_school_id = init; 
    public String  f_answer_id  = init;
    public String  f_health_check_ears_id  = init;
    public String  b_visit_refer_office_id = init;
    public String  check_ears_note = init;
    public String  check_ears_remark = init;
    public String  check_ears_record_time  = init;
    public String  check_ears_modify_time  = init;
    public String  check_ears_cancle_time  = init;
    public String  check_ears_staff_record  = init;
    public String  check_ears_staff_modify  = init;
    public String  check_ears_staff_cancle  = init;
    public String  check_ears_active   = init;
    
    
    /** Creates a new instance of Counsel */
    public CheckEars() {
    }
    
}
