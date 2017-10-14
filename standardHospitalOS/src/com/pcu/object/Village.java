/*
 * Village.java
 *
 * Created on 10 ÁÔ¶Ø¹ÒÂ¹ 2548, 17:24 ¹.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class Village extends Persistent {
    private static String init = "";
    public String village_number = init;
    public String village_name = init;
    public String village_moo = init;
    public String village_location = init;
    public String village_tambon = init;
    public String village_ampur = init;
    public String village_changwat = init;
    public String village_staff_record = init;
    public String village_staff_modify = init;
    public String village_staff_cancel = init;
    public String village_record_date_time = init;
    public String village_modify_date_time = init;
    public String village_cancel_date_time = init;
    public String village_active = "1";
    
    /** Creates a new instance of Village */
    public Village() {
    }
    
}
