/*
 * ResourceHistory.java
 *
 * Created on 15 กรกฎาคม 2548, 20:36 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class ResourceHistory extends Persistent{
    private static String init = "";
    public String resource_id = init;
    public String resorce_history_owner_id = init;
    public String resource_history_owner_description = init;
    public String resorce_history_supervise_id = init;
    public String resource_history_supervise_description = init; 
    public String resource_history_home_number = init;
    public String resource_history_moo = init;    
    public String resource_history_road = init;
    public String resource_history_phone = init;
    public String resource_history_tambol = init;
    public String resource_history_amphur = init;
    public String resource_history_changwat = init;
    public String resource_history_state = init;   
    public String f_health_community_standard_type_id = init;    
    public String resource_history_standard_type_description = init;   
    public String f_health_community_standard_id = init;    
    public String resource_history_time_of_pass = init;   
    public String resource_history_date_of_pass = init;    
    public String resource_history_period_of_pass = init;   
    public String resource_history_staff_record = init;    
    public String resource_history_staff_modify = init;   
    public String resource_history_record_date_time = init;    
    public String resource_history_modify_date_time = init;    
    
    /** Creates a new instance of ResourceHistory */
    public ResourceHistory() {
    }
    
}
