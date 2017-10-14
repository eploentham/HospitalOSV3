/*
 * WaterEradicate.java
 *
 * Created on 11 มิถุนายน 2548, 11:51 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;

/**
 *
 * @author Administrator
 */
public class WaterEradicate extends Persistent {
    
    private static String init = "";
    public String sub_home_id = init;
    public String type_id = init;
    public String supply_id = init;
    public String home_water = init;
    public String water_supply = init;
    public String toilet = init;
    public String garbage = init;
    public String eradicate = init;
    public String garbage_method_id = init;

    /** Creates a new instance of WaterEradicate */
    public WaterEradicate() {
    }
    
}
