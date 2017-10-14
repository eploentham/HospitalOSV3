/*
 * Payer2.java
 *
 * Created on 20 ธันวาคม 2548, 11:45 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package com.hosv3.object;
import com.hospital_os.object.Payer;
import com.hospital_os.usecase.connection.CommonInf;

/**
 *
 * @author sumo1
 */
public class Payer2 extends Payer implements CommonInf{
    
	static final long serialVersionUID = 0;
    /** Creates a new instance of Payer2 */
    public Payer2 (){
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
