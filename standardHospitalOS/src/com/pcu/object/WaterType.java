/*
 * WaterType.java
 *
 * Created on 10 �Զع�¹ 2548, 18:00 �.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class WaterType extends Persistent{
    private static String init = "";
    public String number = init;
    public String description = init;   
    public String active = init;
    
    /** Creates a new instance of WaterType */
    public WaterType() {
    }
    
}
