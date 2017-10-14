/*
 * Relation2.java
 *
 * Created on 18 ตุลาคม 2548, 11:14 น.
 */
package com.hosv3.object;
import com.hospital_os.object.Relation;
import com.hospital_os.usecase.connection.CommonInf;
/**
 *
 * @author  sumo1
 */
public class Relation2 extends Relation implements CommonInf {
    
	static final long serialVersionUID = 0;
    /** Creates a new instance of Relation2 */
    public Relation2() {
        
    }
    public Relation2(String code,String name) {
        this.setObjectId(code);
        this.description = name;
    }
    public Relation2(Relation rl) {
        this.setObjectId(rl.getObjectId());
        this.description = rl.description;
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
