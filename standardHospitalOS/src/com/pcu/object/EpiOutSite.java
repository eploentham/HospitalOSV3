/*
 * EpiOutSite.java
 * 
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author jao
 */
public class EpiOutSite extends Persistent{
    private static String init = "";
    public String epi_outsite_name = init;
    public String epi_outsite_office = init;
    public String epi_outsite_date = init;
    public String remark = init;
    public String patient_id = init;
    public String hn = init;
    public String office = init;
    public String record_date = init;
    public String modify_date = init;
    public String cancel_date = init;
    public String staff_record = init;
    public String staff_modify = init;
    public String staff_cancel = init;
    public String active = init;
    public String family_id=init;
    public String health_epi_group_id = init;
    
    /** Creates a new instance of EpiOutSite */
    public EpiOutSite() {
    }
    
}
