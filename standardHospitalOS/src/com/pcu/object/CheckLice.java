/*
 * CheckLice.java
 *
 * Created on 13 ¡Ã¡¯Ò¤Á 2548, 09:40 ¹.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Noom
 */
public class CheckLice extends Persistent{
    private static String init = "";
    public String  t_health_check_lice_id  = init;
    public String  t_health_student_id  = init;
    public String  t_health_visit_school_id = init; 
    public String  f_answer_id  = init;
    public String  f_answer_check_lice_id  = init;
    public String  b_visit_refer_office_id = init;
    public String  check_lice_note = init;
    public String  check_lice_remark = init;
    public String  check_lice_record_time  = init;
    public String  check_lice_modify_time  = init;
    public String  check_lice_cancle_time  = init;
    public String  check_lice_staff_record  = init;
    public String  check_lice_staff_modify  = init;
    public String  check_lice_staff_cancle  = init;
    public String  check_lice_active   = init;
    
    
    /** Creates a new instance of Counsel */
    public CheckLice() {
    }
    
}
