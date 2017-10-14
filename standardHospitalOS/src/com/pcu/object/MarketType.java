/*
 * MarketType.java
 *
 * Created on 8 กรกฎาคม 2548, 18:09 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class MarketType extends Persistent{
    private static String init = "";
    public String number = init;
    public String description = init;   
    public String active = init;
    
    /** Creates a new instance of MarketType */
    public MarketType() {
    }
    
}
