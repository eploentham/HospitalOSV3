/*
 * CategoryGroupItem.java
 *
 * Created on 25 ตุลาคม 2548, 17:12 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.object;
import com.generalreport.utility.StandardObject;
/**
 *
 * @author americus
 */
public class CategoryGroupItem  extends Persist implements StandardObject
{
    public String b_item_subgroup_id;
    public String subgroup_number;
    public String description;
    public String group_id;
    public String active;
    
    /** Creates a new instance of CategoryGroupItem */
    public CategoryGroupItem()
    {
        idTable ="130";
        tableName= "b_item_subgroup";
    }

    public void setInitData()
    {
       b_item_subgroup_id = "";
       subgroup_number = "";
       description = "";
       group_id = "";
       active = "";
    }
    
    public static String getStringTable()
    {
        return "b_item_subgroup";
    }
    
    public static String getStringFieldPKTable()
    {
        return "b_item_subgroup_id";
    }
    
    public static String getStringFieldNumber()
    {
        return "item_subgroup_number";
    }
  
    public static String getStringFieldDescription()
    {
        return "item_subgroup_description";
    }
    
    public static String getStringFieldGroupID()
    {
        return "f_item_group_id";
    }
    
    public static String getStringFieldActive()
    {
        return "item_subgroup_active";
    }
    
    public Object setInitDataFieldName()
    {
        this.pk_table = this.getStringFieldPKTable();
        subgroup_number = this.getStringFieldNumber();
        description = this.getStringFieldDescription();
        group_id = this.getStringFieldGroupID();
        active = this.getStringFieldActive();
        return this;
    }
    
}
