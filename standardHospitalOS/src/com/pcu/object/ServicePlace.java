/*
 * ServicePlace.java
 *
 * Created on 20 มิถุนายน 2548, 10:24 น.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class ServicePlace extends Persistent{
    private static String init = "";
    public String number = init;
    public String description = init;
    public String active = init;
    
    /** Creates a new instance of ServicePlace */
    public ServicePlace(){        
    }
    
}
