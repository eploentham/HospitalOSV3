/*
 * TempleHistory.java
 *
 * Created on 4 กรกฎาคม 2548, 17:14 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class TempleHistory extends Persistent{
    private static String init = "";
    public String temple_id = init;
    public String temple_personel = init;
    public String temple_amount_personel = init;
    public String temple_staff_record = init;
    public String temple_staff_modify = init;
    public String temple_record_date_time = init;
    public String temple_modify_date_time = init;    
    
    /** Creates a new instance of TempleHistory */
    public TempleHistory() {
    }
    
}
