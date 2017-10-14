/*
 * MarketTypeLock.java
 *
 * Created on 11 กรกฎาคม 2548, 9:53 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class MarketTypeLock extends Persistent{
    private static String init = "";
    public String number = init;
    public String description = init;   
    public String active = init;
    
    /** Creates a new instance of MarketTypeLock */
    public MarketTypeLock() {
    }
    
}
