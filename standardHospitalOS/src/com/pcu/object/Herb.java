/*
 * Herb.java
 *
 * Created on 11 มิถุนายน 2548, 12:40 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;

/**
 *
 * @author Administrator
 */
public class Herb extends Persistent {
   private static String init = "";
    public String home_id = init;
    public String herb_name = init;
    public String herb_use = init;    
    public String staff_record = init; 
    public String staff_modify = init;
    public String staff_cancel = init;
    public String record_date_time = init;
    public String modify_date_time = init;
    public String cancel_date_time = init;
    public String herb_active = init;

    
    /** Creates a new instance of Herb */
    public Herb() {
    }
    
}
