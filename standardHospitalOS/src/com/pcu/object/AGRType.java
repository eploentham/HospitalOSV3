/*
 * AGRType.java
 *
 * Created on 25 �á�Ҥ� 2548, 13:18 �.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class AGRType extends Persistent
{    
    private static String init = "";    
    public String number = init;
    public String description = init;
    public String active = init;
    
    /** Creates a new instance of AGRType */
    public AGRType()
    {
    }
    
}
