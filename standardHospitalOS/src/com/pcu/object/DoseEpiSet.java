/*
 * DoseEpiSet.java
 *
 * Created on 27 ÁÔ¶Ø¹ÒÂ¹ 2548, 16:27 ¹.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class DoseEpiSet extends Persistent{
    private static String init = "";
    public String epi_set_key_id = init;
    public String description = init;
    public String dose = init;
    public String use_uom = init;
    public String qty = init;
    public String purch_uom = init;
    public String caution = init;
    public String day_time = init;
    public String printting = "1";
    public String instruction = init;
    public String frequency = init;
    public String usage_special = init;
    public String usage_text = init;
    public String item_code = init;
    /** Creates a new instance of DoseEpiSet */
    public DoseEpiSet() {
    }
    
}
