/*
 * GrowHistory.java
 *
 * Created on 26 ÁÔ¶Ø¹ÒÂ¹ 2548, 16:40 ¹.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class GrowHistory extends Persistent{
    private static String init = "";
    public String health_grow_id = init;
    public String patient_id = init;
    public String visit_id = init;
    public String grow_id = init;    
    public String standard_grow = init;
    public String real_grow = init;
    public String notice= init;
    public String record_date_time= init;
    public String modify_date_time= init;
    public String cancel_date_time= init;
    public String staff_record= init;
    public String staff_modify= init;
    public String staff_cancel= init;
    public String active= init;
    public String family_id =init;
    public String survey_date = init;

    /** Creates a new instance of GrowHistory */
    public GrowHistory() {
    }
    
}
