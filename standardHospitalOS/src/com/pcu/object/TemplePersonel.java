/*
 * TemplePersonel.java
 *
 * Created on 4 �á�Ҥ� 2548, 12:51 �.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class TemplePersonel extends Persistent{
    private static String init = "";
    public String number = init;
    public String description = init;   
    public String active = init;
    
    /** Creates a new instance of TemplePersonel */
    public TemplePersonel() {
    }
    
}
