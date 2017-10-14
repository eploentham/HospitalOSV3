/*
 * AGR.java
 *
 * Created on 25 กรกฎาคม 2548, 14:44 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class AGR extends Persistent
{
    private static String init = "";
    public String village_id = init;
    public String agr_number = init;
    public String agr_name = init;     
    public String agr_staff_record = init;
    public String agr_staff_modify = init;
    public String agr_staff_cancel = init;
    public String agr_record_date_time = init;
    public String agr_modify_date_time = init;
    public String agr_cancel_date_time = init;
    public String agr_close = init;
    public String agr_close_date_time = init;
    public String agr_close_note = init;
    public String agr_active = init;
    
    /** Creates a new instance of AGR */
    public AGR()
    {
    }
    
}
