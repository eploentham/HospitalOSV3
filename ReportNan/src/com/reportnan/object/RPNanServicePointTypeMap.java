/*
 * RPNanServicePointTypeMap.java
 *
 * Created on 13 ÁÔ¶Ø¹ÒÂ¹ 2549, 11:53 ¹.
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
public class RPNanServicePointTypeMap extends Persist implements StandardObject
{
    public String r_service_point_type_map_id = "";
    public String r_service_point_type_id = "";
    public String b_service_point_id = "";
    public String service_point_description = "";
    public String service_point_type_description = "";
    
    /** Creates a new instance of RPNanServicePointTypeMap */
    public RPNanServicePointTypeMap()
    {
        idTable ="843";
        tableName= "r_service_point_type_map";
    }

    public void setInitData()
    {
         r_service_point_type_map_id = "";
         r_service_point_type_id = "";
         b_service_point_id = "";
         service_point_description = "";
         service_point_type_description = "";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = "r_service_point_type_map_id";
         r_service_point_type_id = "r_service_point_type_id";
         b_service_point_id = "b_service_point_id";
         service_point_description = "service_point_description";
         service_point_type_description = "service_point_type_description";
         return this;
    }
    
}
