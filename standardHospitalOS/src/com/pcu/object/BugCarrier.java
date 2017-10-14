/*
 * BugCarrier.java
 *
 * Created on 11 มิถุนายน 2548, 12:46 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;

/**
 *
 * @author Administrator
 */
public class BugCarrier extends Persistent{
    private static String init = "";
    public String sub_home_id = init;
    public String rat_control = init;
    public String cockroach_control= init;
    public String fly_control = init;
    public String bug_control = init;
    public String home_pet = init;
    public String dung_control_id = init;
    public String animal_control = init;

    /**
     * Creates a new instance of BugCarrier 
     */
    public BugCarrier() {
    }
    
}
