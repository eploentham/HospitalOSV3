/*
 * GrowPcu.java
 *
 * Created on 26 มิถุนายน 2548, 16:35 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class GrowPcu extends Persistent{
    private static String init = "";
    public String patient_id= init;
    public String grow_vn = init;
    public String office_id= init;    
    public String visit_id = init;
    public String grow_hn = init;
    public String record_date_time= init;
    public String staff_record= init;
    public String active= init;
    public String family_id = init;
    /** Creates a new instance of GrowPcu */
    public GrowPcu() {
    }
    
}
