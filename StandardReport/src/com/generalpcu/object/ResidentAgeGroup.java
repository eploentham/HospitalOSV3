/*
 * ResidentAgeGroup.java
 *
 * Created on 1 ¡’π“§¡ 2549, 18:21 π.
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
public class ResidentAgeGroup extends Persist implements StandardObject
{
    public String r_resident_age_group_id = "";
    public String number = "";
    public String begin = "";
    public String end = "";
    
    /** Creates a new instance of ResidentAgeGroup */
    public ResidentAgeGroup()
    {
        idTable ="819";
        this.tableName = "r_resident_age_group";        
    }

    public void setInitData()
    {
        r_resident_age_group_id = "";
        number = "";
        begin = "";
        end = "";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = "r_resident_age_group_id";
        number = "resident_age_group_number";
        begin = "resident_age_group_begin";        
        end = "resident_age_group_end";
        return this;
    }
    
}
