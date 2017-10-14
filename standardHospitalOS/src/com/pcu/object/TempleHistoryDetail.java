/*
 * TempleHistoryDetail.java
 *
 * Created on 14 กรกฎาคม 2548, 14:27 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class TempleHistoryDetail extends Persistent{
    private static String init = "";
    public String temple_history_id = init;
    public String temple_personel = init;
    public String temple_amount_personel = init;    
            
    /** Creates a new instance of TempleHistoryDetail */
    public TempleHistoryDetail() {
    }
    
}
