/*
 * AricSubGroup.java
 *
 * Created on 22 ตุลาคม 2548, 13:41 น.
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
public class AricSubGroup extends Persist implements StandardObject{
    
    //public Stirng aricsubgroupid ;//r_aric_subgroup_id;
    public String number;//aric_subgroup_number;
    public String description;//aric_subgroup_description;
    public String aricgroupid;//r_aric_group_id;
    public AricSubGroup() {
        idTable ="810";
        tableName= "r_aric_subgroup"; 
    }
    public static String getStringTable()
    {
        return "r_aric_subgroup";  
    }
    
    public static String getStringFieldPKTable() 
    {
        return "r_aric_subgroup_id";    
    }
    
    public static String getStringFieldNumber() 
    {
        return "aric_subgroup_number";    
    }
    
    public static String getStringFieldDescription() 
    {
        return "aric_subgroup_description";    
    }

    public static String getStringFieldAricGroupID() 
    {
        return "r_aric_group_id";    
    }
    public void setInitData() {
        number = "";
        description = "";
        aricgroupid = "";
    }

    public Object setInitDataFieldName() {
        this.pk_table = getStringFieldPKTable();
        this.number = getStringFieldNumber();
        this.description = getStringFieldDescription();
        this.aricgroupid = getStringFieldAricGroupID();
        return this;
    }
    
}
