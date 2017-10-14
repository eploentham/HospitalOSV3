/*
 * MarketHistory.java
 *
 * Created on 12 กรกฎาคม 2548, 11:18 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class MarketHistory extends Persistent{
    private static String init = "";
    public String market_id = init;
    public String register_id = init;
    public String market_type_id = init;
    public String market_type_description = init;
    public String market_type_lock_id = init; 
    public String market_type_lock_description = init;
    public String market_history_home_number = init; 
    public String market_history_moo = init;    
    public String market_history_road = init;
    public String market_history_phone = init;
    public String market_history_tambol = init;
    public String market_history_amphur = init;
    public String market_history_changwat = init;
    public String market_history_owner_id = init;   
    public String market_history_owner_description = init;    
    public String market_history_market_co = init;   
    public String market_history_market_co_date_time = init;    
    public String market_history_standard_type_id = init;   
    public String market_history_standard_type_description = init;    
    public String market_history_standard_id = init;   
    public String market_history_time_of_pass = init;    
    public String market_history_date_of_pass = init;   
    public String market_history_period_of_pass = init;    
    public String market_history_in_market = init;   
    public String market_history_staff_record = init; 
    public String market_history_staff_modify = init;
    public String market_history_record_date_time = init; 
    public String market_history_modify_date_time = init;
    
    /** Creates a new instance of MarketHistory */
    public MarketHistory() {
    }
    
}
