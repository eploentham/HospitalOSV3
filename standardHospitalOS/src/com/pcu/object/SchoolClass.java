/*
 * ServiceType.java
 *
 * Created on 20 มิถุนายน 2548, 17:15 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Noom
 */
public class SchoolClass extends Persistent{
    private static String init = "";
    public String number = init;
    public String description = init;
    public String school_max_class_status = init;
    public String active = init;
    
    /** Creates a new instance of SchoolClass */
    public SchoolClass() {
    }
    
}
