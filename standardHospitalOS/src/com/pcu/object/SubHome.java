/*
 * SubHome.java
 *
 * Created on 11 มิถุนายน 2548, 11:35 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
    

/**
 *
 * @author Administrator
 */
public class SubHome extends Persistent{
    
    private static String init = "";
    public String home_id = init;    
    public String record_date_time = init;
    public String staff_record = init;
    public String staff_modify = init;
    public String staff_cancel = init;
    public String modify_date_time = init;
    public String cancel_date_time = init;
    public String sub_home_active = init;

    /** Creates a new instance of SubHome */
    public SubHome() {
    }
    
}
