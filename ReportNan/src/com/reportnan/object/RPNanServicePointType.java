/*
 * RPNanServicePointType.java
 *
 * Created on 13 ÁÔ¶Ø¹ÒÂ¹ 2549, 11:39 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.object;
import com.reportnan.usecase.StandardObject;
/**
 *
 * @author pu
 */
public class RPNanServicePointType extends Persist implements StandardObject
{
    public String r_service_point_type_id ="";
    public String service_point_type_number ="";
    public String service_point_type_description = "";

    /** Creates a new instance of RPNanServicePointType */
    public RPNanServicePointType()
    {
        idTable ="842";
        tableName= "r_service_point_type";
    }

    public void setInitData()
    {
        r_service_point_type_id ="";
        service_point_type_number ="";
        service_point_type_description = "";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = "r_service_point_type_id";
        service_point_type_number = "service_point_type_number";
        service_point_type_description = "service_point_type_description";
        return this;
    }
    
}
