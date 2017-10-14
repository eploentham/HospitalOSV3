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
public class VisitSchool extends Persistent{
    private static String init = "";
    public String  t_health_visit_school_id = init;
    public String  t_health_school_id  = init;
    public String  b_school_class_id = init;
    public String  visit_school_room  = init;
    public String  f_health_school_service_type_id  = init;
    public String  visit_school_term = init;
    public String  visit_school_other_service = init;
    public String  visit_school_service_date = init;
    public String  visit_school_record_time  = init;
    public String  visit_school_modify_time  = init;
    public String  visit_school_cancle_time  = init;
    public String  visit_school_staff_record  = init;
    public String  visit_school_staff_modify  = init;
    public String  visit_school_staff_cancle  = init;
    public String  visit_school_active   = init;
    
    
    /** Creates a new instance of Counsel */
    public VisitSchool() {
    }
    
}
