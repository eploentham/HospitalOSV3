/*
 * RPRescidentPCU.java
 *
 * Created on 7 ¡’π“§¡ 2549, 14:30 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalpcu.object;
import com.generalpcu.usecase.StandardObject;
/**
 *
 * @author pu
 */
public class RPRescidentPCU extends Persist implements StandardObject
{
    public String village_no;
    public String village_name;
    public String total_resident;
    public String male;
    public String female;
    public String non_specify;
    
    /** Creates a new instance of RPRescidentPCU */
    public RPRescidentPCU()
    {
    }

    public void setInitData()
    {
        village_no = "";
        village_name = "";
        total_resident = "";
        male = "";
        female = "";
        non_specify = "";
    }

    public Object setInitDataFieldName()
    {
        village_no = "village_no";
        village_name = "village_name";
        total_resident = "total_resident";
        male = "male";
        female = "female";
        non_specify = "non_specify";
        return this;
    }
    
}
