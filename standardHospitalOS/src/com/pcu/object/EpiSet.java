/*
 * EpiSet.java
 *
 * Created on 24 �Զع�¹ 2548, 16:41 �.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class EpiSet extends Persistent{
    private static String init = "";
    public String item_code = init;
    public String epi_set_group_key_id = init;
    
    public DoseEpiSet dose_epi_set ; 
    /** Creates a new instance of EpiSet */
    public EpiSet() {
    }
    
}
