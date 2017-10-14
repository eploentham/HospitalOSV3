/*
 * PlanGroup.java
 *
 * Created on 27 ตุลาคม 2548, 19:23 น.
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
public class PlanGroup  extends Persist implements StandardObject{
    
    //public String id;r_plan_group_id
    /**ลำดับข้อตาราง*/
    public String number; //plan_group_number
    /**คำอธิบาบ*/
    public String description; // plan_group_description
    /**ใช้งานได้หรือไม่*/
    public String active; //plan_group_active
    public PlanGroup() {
        idTable ="803";
        tableName= "r_plan_group"; 
    }

    public static String getStringTable()
    {
        return "r_plan_group";  
    }
    
    public static String getStringFieldPKTable() 
    {
        return "r_plan_group_id";    
    }
    
    public static String getStringFieldNumber() 
    {
        return "plan_group_number";    
    }
    public static String getStringFieldDescription() 
    {
        return "plan_group_description";    
    }
    public static String getStringFieldActive() 
    {
        return "plan_group_active";    
    }
    
    
    public void setInitData() {
        number = "";
        description = "";
        active = "";
    }

    public Object setInitDataFieldName() {
        this.pk_table = getStringFieldPKTable();
        this.number = getStringFieldNumber();
        this.description = getStringFieldDescription();
        this.active = getStringFieldActive();
        return this;
    }
    
}
