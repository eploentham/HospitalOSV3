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
public class Student extends Persistent{
    private static String init = "";
    public String  t_health_student_id = init;
    public String  t_health_visit_school_id  = init;
    public String  f_patient_prefix_id  = init;
    public String  f_sex_id  = init;
    public String  student_firstname = init;
    public String  student_surname = init;
    public String  student_number = init;
    public String  student_pid = init;
    public String  student_note = init;
    public String  student_record_time  = init;
    public String  student_modify_time  = init;
    public String  student_cancle_time  = init;
    public String  student_staff_record  = init;
    public String  student_staff_modify  = init;
    public String  student_staff_cancle  = init;
    public String  student_active   = init;
    
    
    /** Creates a new instance of Student */
    public Student() {
    }
    
}
