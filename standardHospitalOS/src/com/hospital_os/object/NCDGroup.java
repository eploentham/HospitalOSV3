/*
 * NCDGroup.java
 *
 * Created on 14 ÁÔ¶Ø¹ÒÂ¹ 2549, 10:50 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.usecase.connection.*;
/**
 *
 * @author amp
 */
public class NCDGroup extends Persistent implements CommonInf
{
    
    /** Creates a new instance of NCDGroup */
    public String number = "";
    public String description = "";
    public String pattern = "";
    public String value = "1";
    public String chronic_group = "";
    
    public NCDGroup()
    {
    }   

    public String getCode() {
        return getObjectId();
    }

    public String getName() {
        return description;
    }
    public String toString(){
        return getName();
    }
}
