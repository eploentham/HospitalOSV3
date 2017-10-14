/*
 * AricGroup.java
 *
 * Created on 22 ตุลาคม 2548, 12:00 น.
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
public class AricGroup  extends Persist implements StandardObject
{
    
    //public String r_aric_group_id;
    /**ลำดับข้อมูล*/
    public String number ;//aric_group_number;
    /**คำอธิบาย*/
    public String description; //    aric_group_description;
    public AricGroup() {
        idTable ="809";
        
        tableName= "r_aric_group"; 
    }
    
    public static String getStringTable()
    {
        return "r_aric_group";  
    }
    
    public static String getStringFieldPKTable() 
    {
        return "r_aric_group_id";    
    }
    public static String getStringFieldNumber()
    {
        return "aric_group_number";
    }
    public static String getStringFieldDescription()
    {
        return "aric_group_description";
    }
    public void setInitData() {
        number = "";
        description = "";
    }

    public Object setInitDataFieldName() {
        this.pk_table = getStringFieldPKTable();
        this.number = getStringFieldNumber();
        this.description = getStringFieldDescription();
        return this;
    }
    
}
