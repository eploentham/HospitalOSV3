/*
 * SchoolMaxClass.java
 *
 * Created on 10 มิถุนายน 2548, 18:20 น.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class SchoolMaxClass extends Persistent{
    private static String init = "";
    public String number = init;
    public String description = init;
    public String active = init;
    
    /** Creates a new instance of SchoolMaxClass */
    public SchoolMaxClass() {
    }
    
}
