/*
 * ServiceType.java
 *
 * Created on 20 �Զع�¹ 2548, 17:15 �.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Noom
 */
public class ServiceType extends Persistent{
    private static String init = "";
    public String number = init;
    public String description = init;
    public String active = init;
    
    /** Creates a new instance of ServiceType */
    public ServiceType() {
    }
    
}
