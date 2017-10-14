/*
 * PlanGroupMapPtType.java
 *
 * Created on 27 ตุลาคม 2548, 19:24 น.
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
public class PlanGroupMapPtType  extends Persist implements StandardObject{
    
    //public String id ;r_plan_group_map_pttype_id	
    /**กลุ่มของ plan*/
    public String plangroupid;//r_plan_group_id
    /**ชื่อย่อของ pttype*/
    public String mapptypename; //plan_group_map_pttype_pttype
    public PlanGroupMapPtType() {
        idTable ="804";
        tableName= "r_plan_group_map_pttype"; 
    }
    
    public static String getStringTable()
    {
        return "r_plan_group_map_pttype";  
    }
    
    public static String getStringFieldPKTable() 
    {
        return "r_plan_group_map_pttype_id";    
    }
    public static String getStringFieldPlanGroupID() 
    {
        return "r_plan_group_id";    
    }
    public static String getStringFieldMapPtType() 
    {
        return "plan_group_map_pttype_pttype";    
    }
    
    
    public void setInitData() {
        plangroupid = "";
        mapptypename = "";
    }

    public Object setInitDataFieldName() {
        this.pk_table = getStringFieldPKTable();
        this.plangroupid = getStringFieldPlanGroupID();
        this.mapptypename = getStringFieldMapPtType();
        
        return this;
    }
    
}
