/*
 * Resource.java
 *
 * Created on 18 กรกฎาคม 2548, 16:25 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class Resource extends Persistent{
    private static String init = ""; 
    public String village_id = init;
    public String resource_number = init;
    public String resource_name = init;
    public String resource_staff_record = init;
    public String resource_staff_modify = init;
    public String resource_staff_cancel = init;
    public String resource_record_date_time = init;
    public String resource_modify_date_time = init;
    public String resource_cancel_date_time = init;
    public String resource_active = init;
       
    /** Creates a new instance of Resource */
    public Resource() {
    }
    
}
