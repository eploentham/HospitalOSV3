/*
 * OccupationPcu.java
 *
 * Created on 9 กันยายน 2548, 11:30 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.object;
import com.hospital_os.object.Occupat;
import com.hospital_os.usecase.connection.CommonInf;
/**
 *
 * @author jao
 */
public class OccupationPcu extends Occupat implements CommonInf//, com.henbe.connection.CommonInf
{
    
    /** Creates a new instance of OccupationPcu */
    
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
