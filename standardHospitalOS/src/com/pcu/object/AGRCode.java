/*
 * AGRCode.java
 *
 * Created on 25 �á�Ҥ� 2548, 11:31 �.
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
