/*
 * Prefix2.java
 *
 * Created on 28 กรกฎาคม 2548, 13:54 น.
 */

package com.hosv3.object;
import com.hospital_os.object.Prefix;
import com.hospital_os.usecase.connection.CommonInf;
/**
 *
 * @author  kingland
 */
public class Prefix2 extends Prefix implements CommonInf {
    
	static final long serialVersionUID = 0;
	/** Creates a new instance of Prefix2 */
    public Prefix2() {
        
    }
    public Prefix2(Prefix rl) {
        this.setObjectId(rl.getObjectId());
        this.description = rl.description;
    }
    public Prefix2(String code,String name) {
        this.setObjectId(code);
        this.description = name;
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
