/*
 * Epi.java
 *
 * Created on 24 ÁÔ¶Ø¹ÒÂ¹ 2548, 10:26 ¹.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class Epi extends Persistent{
    private static String init = "";
    public String patient_id= init;
    public String epi_vn= init;
    public String office_id= init;
    public String visit_id= init;
    public String epi_hn= init;
    public String epi_nextapp = init;
    public String epi_notice= init;
    public String record_date_time= init;
    public String modify_date_time= init;
    public String cancel_date_time= init;
    public String staff_record= init;
    public String staff_modify= init;
    public String staff_cancel= init;
    public String active= init;
    public String family_id=init;
    public String survey_date = init;
    /** Creates a new instance of Epi */
    public Epi() {
    }
    
}
