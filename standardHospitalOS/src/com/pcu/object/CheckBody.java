/*
 * CheckBody.java
 *
 * Created on 13 �á�Ҥ� 2548, 09:40 �.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Noom
 */
public class CheckBody extends Persistent{
    private static String init = "";
    public String  t_health_check_body_id  = init;
    public String  t_health_student_id  = init;
    public String  t_health_visit_school_id = init; 
    public String  f_answer_id  = init;
    public String  f_answer_check_body_id  = init;
    public String  b_visit_refer_office_id = init;
    public String  check_body_note = init;
    public String  check_body_remark = init;
    public String  check_body_record_time  = init;
    public String  check_body_modify_time  = init;
    public String  check_body_cancle_time  = init;
    public String  check_body_staff_record  = init;
    public String  check_body_staff_modify  = init;
    public String  check_body_staff_cancle  = init;
    public String  check_body_active   = init;
    
    
    /** Creates a new instance of Counsel */
    public CheckBody() {
    }
    
}
