/*
 * DrugStandard.java
 *
 * Created on 14 ¡’π“§¡ 2549, 10:01 π.
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
public class DrugStandard extends Persistent
{
    public String number;
    public String description = "";
    public String active = "1";
    
    /** Creates a new instance of DrugStandard */
    public DrugStandard()
    {
    }

    public String toString(){
        return description;
    }
}
