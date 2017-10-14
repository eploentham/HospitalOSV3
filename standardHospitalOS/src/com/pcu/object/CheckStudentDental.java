/*
 * CheckStudentDental.java
 *
 * Created on 14 ¡Ã¡¯Ò¤Á 2548, 10:40 ¹.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Noom
 */
public class CheckStudentDental extends Persistent{
    private static String init = "";
    public String  t_health_check_student_dental_id  = init;
    public String  t_health_student_id  = init;
    public String  t_health_visit_school_id = init; 
    public String  f_answer_id  = init;
    public String  check_student_dental_num_tooth  = init;
    public String  check_student_dental_num_bad_tooth  = init;
    public String  check_student_dental_num_milktooth  = init;
    public String  check_student_dental_num_bad_milktooth  = init;
    public String  f_health_gum_level_id  = init;
    public String  b_visit_refer_office_id = init;
    public String  check_student_dental_note = init;
    public String  check_student_dental_remark = init;
    public String  check_student_dental_record_time  = init;
    public String  check_student_dental_modify_time  = init;
    public String  check_student_dental_cancle_time  = init;
    public String  check_student_dental_staff_record  = init;
    public String  check_student_dental_staff_modify  = init;
    public String  check_student_dental_staff_cancle  = init;
    public String  check_student_dental_active   = init;
    
    
    /** Creates a new instance of Counsel */
    public CheckStudentDental() {
    }
    
}
