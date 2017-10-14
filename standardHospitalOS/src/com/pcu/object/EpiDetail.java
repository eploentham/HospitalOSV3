/*
 * EpiDetail.java
 *
 * Created on 24 มิถุนายน 2548, 10:35 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class EpiDetail extends Persistent{
    
    private static String init = "";
    public String epi_id= init;
    public String epi_set_id= init;
    public String epi_start= init;
    public String epi_exp = init;
    public String patient_id= init;
    public String visit_id= init;
    public String staff_record= init;
    public String record_date_time= init;
    public String active= init;
    public String lot = init;
    public String family_id=init;

    
    /** Creates a new instance of EpiDetail */
    public EpiDetail() {
    }
    
}
