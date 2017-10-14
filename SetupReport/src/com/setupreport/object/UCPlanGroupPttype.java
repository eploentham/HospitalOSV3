/*
 * PlanGroupMapPtType.java
 *
 * Created on 28 ตุลาคม 2548, 09:51 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.object;
import com.setupreport.usecase.connection.StandardObject;
/**
 *
 * @author americus
 */
public class UCPlanGroupPttype  extends Persist implements StandardObject
{    
    /**รหัสของการ Map ประเภทสิทธิ*/
     public String group_pttype_id;
    /**code ของกลุ่มสิทธิ UC*/
     public String pttype_number;
    /**ชื่อย่อของ pttype*/
     public String pttype;


    
    public UCPlanGroupPttype() {
        idTable ="806";
        tableName= "r_uc_plan_group_pttype"; 
    }
    
    public static String getStringTable()
    {
        return "r_uc_plan_group_pttype";  
    }
    
    public static String getStringFieldPKTable() 
    {
        return "r_uc_plan_group_pttype_id";    
    }
    public static String getStringFieldPttypeNumber() 
    {
        return "uc_plan_group_pttype_number";    
    }
    public static String getStringFieldMapPtType() 
    {
        return "uc_plan_group_pttype_pttype";    
    }
    
    
    public void setInitData() 
    {
        group_pttype_id = "";
        pttype_number = "";
        pttype = "";
    }

    public Object setInitDataFieldName() {
        this.pk_table = getStringFieldPKTable();
        this.pttype_number = getStringFieldPttypeNumber();
        this.pttype = getStringFieldMapPtType();
        
        return this;
    }
    
}
