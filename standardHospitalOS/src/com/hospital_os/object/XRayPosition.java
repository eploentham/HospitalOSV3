/*
 * XRayPosition.java
 *
 * Created on 25 æƒ…¿“§¡ 2547, 11:33 π.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author  tong
 */
public class XRayPosition  extends Persistent {
    
    /** Creates a new instance of XRayPosition */
    public String xray_position_id;
    public String description;
    public String active;
    public XRayPosition() {
    }
    public String toString(){
        return description;
    }
    
}
