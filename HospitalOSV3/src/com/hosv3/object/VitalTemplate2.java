/*
 * VitalTemplate2.java
 *
 * Created on 20 กันยายน 2548, 10:43 น.
 */

package com.hosv3.object;
import com.hospital_os.object.VitalTemplate;
import com.hospital_os.usecase.connection.CommonInf;

/**
 *
 * @author  kingland
 */
public class VitalTemplate2 extends VitalTemplate implements CommonInf{
    
	static final long serialVersionUID = 0;
    /** Creates a new instance of VitalTemplate2 */
    public VitalTemplate2() {
    }
    
    public String getCode() {
        return this.getObjectId();
    }
    
    public String getName() {
        return this.description;
    }
    
}
