/*
 * WaterHistory.java
 *
 * Created on 10 มิถุนายน 2548, 18:03 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class WaterHistory extends Persistent{
    private static String init = "";
    public String water_id = init;
    public String water_history_state = init;    
    public String water_history_staff_record = init;   
    public String water_history_record_date_time = init;    
    
    /** Creates a new instance of WaterHistory */
    public WaterHistory() {
    }
    
}
