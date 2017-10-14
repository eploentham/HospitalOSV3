/*
 * CheckOther.java
 *
 * Created on 13 ¡Ã¡¯Ò¤Á 2548, 09:40 ¹.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Noom
 */
public class CheckOther extends Persistent{
    private static String init = "";
    public String  t_health_check_other_id  = init;
    public String  t_health_student_id  = init;
    public String  t_health_visit_school_id = init; 
    public String  f_answer_id  = init;
    public String  b_visit_refer_office_id = init;
    public String  check_other_note = init;
    public String  check_other_remark = init;
    public String  check_other_record_time  = init;
    public String  check_other_modify_time  = init;
    public String  check_other_cancle_time  = init;
    public String  check_other_staff_record  = init;
    public String  check_other_staff_modify  = init;
    public String  check_other_staff_cancle  = init;
    public String  check_other_active   = init;
    
    
    /** Creates a new instance of Counsel */
    public CheckOther() {
    }
    
}
