/*
 * DrugHistory.java
 *
 * Created on 13 �á�Ҥ� 2548, 10:59 �.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class DrugHistory extends Persistent{
    private static String init = "";
    public String drug_id= init;
    public String tranfer= init;
    public String quantity= init;
    public String note= init;
    public String staff_record= init;
    public String record_date_time= init;
    /** Creates a new instance of DrugHistory */
    public DrugHistory() {
    }
    
}
