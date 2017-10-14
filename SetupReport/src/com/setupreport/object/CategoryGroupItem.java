/*
 * CategoryGroupItem.java
 *
 * Created on 28 ตุลาคม 2548, 10:32 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.object;
import com.setupreport.usecase.connection.StandardObject;
/**
 *
 * @author tong(Padungrat)
 */
public class CategoryGroupItem extends Persist implements StandardObject{
    
    public String category_group_item_id;
    public String description;
    public String category_group_code;
    public String active;
    public CategoryGroupItem() {
        idTable ="130";        
        tableName= "b_item_subgroup";
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
    
    public static String getStringFieldDiscription()
    {
        return "item_subgroup_description";
    }
    
    public static String getStringFieldItemGroupID()
    {
        return "f_item_group_id";
    }
    
    public static String getStringFieldActive()
    {
        return "item_subgroup_active";
    }
    public void setInitData() {
       
        category_group_item_id   ="";
        description   ="";
        category_group_code   ="";
        active ="";
    }

    public Object setInitDataFieldName() {
        
         this.pk_table =  this.getStringFieldPKTable();
        category_group_item_id   = getStringFieldNumber();
        description   = getStringFieldDiscription();
        category_group_code   = getStringFieldItemGroupID();
        active = getStringFieldActive();
        
        return this;
    }
    
}
