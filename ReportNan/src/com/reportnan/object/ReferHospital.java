/*
 * RPNanReferHospital.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2549, 11:56 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.object;

import com.reportnan.usecase.StandardObject;
/**
 *
 * @author ojika
 */
public class ReferHospital extends Persist implements StandardObject
{
    
    public String r_refer_office_id = "";
    public String refer_office_code = "";
    public String refer_office_description = "";
    public String r_refer_office_type_id = "";
    
    public ReferHospital()
    {
        idTable = "837";
        tableName= "r_refer_office";
    }

    public void setInitData()
    {
        r_refer_office_id ="";
        refer_office_code ="";
        refer_office_description = "";
        r_refer_office_type_id = "";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = "r_refer_office_id";
        refer_office_code = "refer_office_code";
        refer_office_description = "refer_office_description";
        r_refer_office_type_id = "r_refer_office_type_id";
        return this;
    }
    
}
