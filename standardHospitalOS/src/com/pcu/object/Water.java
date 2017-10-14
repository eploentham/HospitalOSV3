/*
 * Water.java
 *
 * Created on 10 ÁÔ¶Ø¹ÒÂ¹ 2548, 17:55 ¹.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class Water extends Persistent{
    private static String init = "";
    public String village_id = init;
    public String water_number = init;
    public String water_type = init;
    public String water_construct = init;
    public String water_construct_description = init;
    public String water_supervise = init;
    public String water_supervise_description = init;
    public String water_staff_record = init;
    public String water_staff_modify = init;
    public String water_staff_cancel = init;
    public String water_record_date_time = init;
    public String water_modify_date_time = init;
    public String water_cancel_date_time = init;
    public String water_active = init;
    
    /** Creates a new instance of Water */
    public Water() {
    }
    
}
