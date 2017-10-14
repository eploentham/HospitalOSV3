/*
 * BodyOrgan.java
 *
 * Created on 7 เมษายน 2549, 9:49 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class BodyOrgan extends Persistent
{
    public String number = "";
    public String description = "";
    public String active = "1";
    public String max_number = "";
    
    /** Creates a new instance of BodyOrgan */
    public BodyOrgan()
    {
    }
    public String toString(){
        return description;
    }
    
}
