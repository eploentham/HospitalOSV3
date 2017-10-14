/*
 * VitalTemplate2.java
 *
 * Created on 20 กันยายน 2548, 10:43 น.
 */

package com.hosv3.object;
import com.hospital_os.usecase.connection.CommonInf;
import com.hospital_os.utility.XPersistent;
/**
 *
 * @author  kingland
 */
public class OfficeInCup extends XPersistent implements CommonInf{
    
	static final long serialVersionUID = 0;
	public String code="";
    public String name="";
    /** Creates a new instance of VitalTemplate2 */
    public OfficeInCup() {
    }
    
    public String getCode() {
        return this.getObjectId();
    }
    
    public String getName() {
        return this.name;
    }
    public String toString(){
        return this.name;
    }
    
}
