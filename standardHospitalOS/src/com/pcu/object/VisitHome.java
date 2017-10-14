/*
 * VisitHome.java
 *
 * Created on 17 ÁÔ¶Ø¹ÒÂ¹ 2548, 14:48 ¹.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Noom
 */
public class VisitHome extends Persistent{
    private static String init = "";
    public String t_health_visit_home_id = init;
    public String visit_home_problem = init;
    public String visit_home_object = init;
    public String visit_home_maintain = init;
    public String visit_home_assess = init;
    public String visit_home_plane = init;
    public String visit_home_date = init;
    public String visit_home_nextdate = init;
    public String visit_home_remark = init;
    public String visit_home_record_time =init; 
    public String visit_home_modify_time =init; 
    public String visit_home_cancle_time =init; 
    public String visit_home_staff_record =init;
    public String visit_home_staff_modify =init;
    public String visit_home_staff_cancle =init;
    public String visit_home_active =init;

    public String patient_id = init;
    public String family_id = init;

    
    /** Creates a new instance of VisitHome */
    public VisitHome() {
    }
    
}
