/*
 *
 *
 * Created on 27 กรกฎาคม 2548, 14:54 น.
 */

package com.hosv3.object;
import com.hospital_os.object.*;
import com.hospital_os.usecase.connection.CommonInf;
/**
 *
 * @author  kingland
 */
public class AccEmType extends Sex implements CommonInf{
    
	static final long serialVersionUID = 0;
	/** Creates a new instance of  */
    public AccEmType() {
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
