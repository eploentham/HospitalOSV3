/*
 * AGRCode.java
 *
 * Created on 25 กรกฎาคม 2548, 11:31 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class AGRCode extends Persistent
{
    private static String init = "";
    public String number = init;
    public String description = init;
    public String active = init;
    
    /** Creates a new instance of AGRCode */
    public AGRCode()
    {
    }
    
}
