/*
 * Prefix2.java
 *
 * Created on 28 กรกฎาคม 2548, 13:54 น.
 */

package com.hosv3.object;
import com.hospital_os.object.Address;
import com.hospital_os.usecase.connection.CommonInf;
/**
 *
 * @author  kingland
 */
public class Address2 extends Address implements CommonInf {
    
    /** Creates a new instance of Address2 */
	static final long serialVersionUID = 0;
	public Address2() {
        
    }
     public String getCode() {
        return this.getObjectId();
    }
    
    public String getName() {
        return this.description;
    }
    public String toString(){
        return getName();
    }
    
}
