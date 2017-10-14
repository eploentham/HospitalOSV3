/*
 * CalDateAppointment.java
 *
 * Created on 7 สิงหาคม 2549, 16:58 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.usecase.connection.CommonInf;
/**
 *
 * @author sumo
 */
public class CalDateAppointment extends Persistent implements CommonInf
{
    
    /*รายละเอียด*/
    public String description = "";

    /** Creates a new instance of CalDateAppointment */
    public CalDateAppointment() {
    }

    public String getCode() {
        return this.getObjectId();
    }
    
    public String getName() {
        return description;
    }
    public String toString(){
        return getName();
    }
    
}
