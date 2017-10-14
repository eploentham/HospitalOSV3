/*
 * XRayLeteral.java
 *
 * Created on 25 æƒ…¿“§¡ 2547, 11:33 π.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author tong
 */
public class XRayLeteral extends Persistent {
    
    /** Creates a new instance of XRayLeteral */
    public String xray_leteral_id;
    public String description;
    public String active;
    
    public XRayLeteral() {
    }
    public String toString(){
        return description;
    }
    
}
