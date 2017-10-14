/*
 * MaimType.java
 *
 * Created on 27 ¡’π“§¡ 2549, 15:28 π.
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
public class MaimType extends Persist implements StandardObject
{
    private String b_health_maim_id = "";
    private String health_maim_number = "";
    private String health_maim_description = "";
    private String health_maim_active ="";
    
    /** Creates a new instance of MaimType */
    public MaimType()
    {
    }

    public void setInitData()
    {
        b_health_maim_id = "";
        health_maim_number = "";
        health_maim_description = "";
        health_maim_active ="";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table  = "b_health_maim_id";
        health_maim_number = "health_maim_number";
        health_maim_description = "health_maim_description";
        health_maim_active ="health_maim_active";
        return this;      
    }
    
}
