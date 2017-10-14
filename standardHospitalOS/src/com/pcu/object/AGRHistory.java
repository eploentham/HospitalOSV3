/*
 * AGRHistory.java
 *
 * Created on 25 กรกฎาคม 2548, 14:58 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class AGRHistory extends Persistent
{
    private static String init = "";
    public String agr_id = init;
    public String register_id = init;
    public String agr_history_group = init;    
    public String agr_history_type = init;
    public String agr_history_start_year = init;    
    public String agr_history_home_number = init;
    public String agr_history_moo = init;
    public String agr_history_road = init;    
    public String agr_history_phone = init;
    public String agr_history_tambol = init;    
    public String agr_history_amphur = init;    
    public String agr_history_changwat = init;  
    public String agr_history_chairman_name = init;
    public String agr_history_man_member = init;    
    public String agr_history_woman_member = init;    
    public String agr_history_capital = init;    
    public String standard_type_id = init;    
    public String standard_type_description = init;
    public String standard_id = init;   
    public String time_of_pass = init;    
    public String date_of_pass = init;
    public String period_of_pass = init;    
    public String agr_history_staff_record = init; 
    public String agr_history_staff_modify = init; 
    public String agr_history_record_date_time = init;
    public String agr_history_modify_date_time = init;
    
    /** Creates a new instance of AGRHistory */
    public AGRHistory()
    {
    }
    
}
