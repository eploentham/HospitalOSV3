/*
 * Prefix2.java
 *
 * Created on 28 กรกฎาคม 2548, 13:54 น.
 */

package com.hosv3.object;
import com.hospital_os.object.BillingGroupItem;
import com.hospital_os.usecase.connection.CommonInf;
/**
 *
 * @author  kingland
 */
public class BillingGroupItem2 extends BillingGroupItem implements CommonInf {
    
    /** Creates a new instance of Prefix2 */
	static final long serialVersionUID = 0;
	public BillingGroupItem2() {
        
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
