/*
 * TempleType.java
 *
 * Created on 4 �á�Ҥ� 2548, 11:38 �.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class TempleType extends Persistent{
    private static String init = "";
    public String number = init;
    public String description = init;   
    public String active = init;
    
    /** Creates a new instance of TempleType */
    public TempleType() {
    }
    
}
