/*
 * CheckStudentHealth.java
 *
 * Created on 13 กรกฎาคม 2548, 09:22 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Noom
 */
public class CheckStudentHealth extends Persistent{
    private static String init = "";
    public String  t_health_check_student_health_id  = init;
    public String  t_health_student_id  = init;
    public String  t_health_visit_school_id = init; 
    public String  f_answer_id  = init;
    public String  f_answer_check_student_health_id  = init;
    public String  check_student_health_tall = init;
    public String  check_student_health_weight = init;
    public String  check_student_health_note = init;
    public String  check_student_health_remark = init;
    public String  b_visit_refer_office_id = init;
    public String  check_student_health_record_time  = init;
    public String  check_student_health_modify_time  = init;
    public String  check_student_health_cancle_time  = init;
    public String  check_student_health_staff_record  = init;
    public String  check_student_health_staff_modify  = init;
    public String  check_student_health_staff_cancle  = init;
    public String  check_student_health_active   = init;

    
    /** Creates a new instance of Counsel */
    public CheckStudentHealth() {
    }
    
}
