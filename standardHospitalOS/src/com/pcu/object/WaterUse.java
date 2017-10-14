/*
 * WaterUse.java
 *
 * Created on 13 มิถุนายน 2548, 14:59 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class WaterUse extends Persistent {
     private static String init = "";
     public String description = init;
     public String active_drink = init;
     public String active_use = init;
    /** Creates a new instance of WaterUse */
    public WaterUse() {
    }
    
}
