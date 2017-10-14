/*
 * DrugIn.java
 *
 * Created on 4 มิถุนายน 2548, 15:33 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;

/**
 *
 * @author Administrator
 */
public class DrugIn extends Persistent {
    private static String init = "";
    public String drug_in_invoice_number = init;
    public String drug_in_date_time = init;
    public String drug_in_vendor = init;
    public String drug_in_note = init;    
    
    /** Creates a new instance of DrugIn */
    public DrugIn() {
    }
    
}
