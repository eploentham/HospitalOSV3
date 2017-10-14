/*
 * Market.java
 *
 * Created on 12 กรกฎาคม 2548, 11:00 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class Market extends Persistent{
    private static String init = "";
    public String village_id = init;
    public String market_number = init;
    public String market_name = init;
    public String market_staff_record = init;
    public String market_staff_modify = init;
    public String market_staff_cancel = init;
    public String market_record_date_time = init;
    public String market_modify_date_time = init;
    public String market_cancel_date_time = init;
    public String market_close = init;
    public String market_close_date_time = init;
    public String market_close_note = init;
    public String market_active = init;
    
    /** Creates a new instance of Market */
    public Market() {
    }
    
}
