/*
 * SchoolHistory.java
 *
 * Created on 29 ÁÔ¶Ø¹ÒÂ¹ 2548, 16:00 ¹.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class SchoolHistory extends Persistent{
    private static String init = "";
    public String school_id = init;
    public String school_history_max_class = init;
    public String school_history_owner = init;
    public String school_history_owner_other = init;
    public String school_history_standard_type = init; 
    public String school_history_standard_type_other = init;
    public String school_history_standard = init; 
    public String school_history_time_of_pass = init;    
    public String school_history_date_of_pass = init;
    public String school_history_period_of_pass = init;
    public String school_history_amount_student_m = init;
    public String school_history_amount_student_fm = init;
    public String school_history_amount_teacher_m = init;
    public String school_history_amount_teacher_fm = init;    
    public String school_history_staff_record = init; 
    public String school_history_staff_modify = init;
    public String school_history_record_date_time = init; 
    public String school_history_modify_date_time = init;
    
    /** Creates a new instance of SchoolHistory */
    public SchoolHistory() {
    }
    
}
