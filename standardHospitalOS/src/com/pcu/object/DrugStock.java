/*
 * DrugStock.java
 *
 * Created on 13 กรกฎาคม 2548, 10:55 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class DrugStock extends Persistent{
   private static String init = ""; 
    public String office_id= init;
    public String item_id= init;
    public String item_common_name= init;
    public String amount= init;
    public String staff_record= init;
    public String staff_modify= init;
    public String staff_cancel= init;
    public String record_date_time= init;
    public String modify_date_time= init;
    public String cancel_date_time= init;
    public String active= init; 
    /** Creates a new instance of DrugStock */
    public DrugStock() {
    }
    
}
