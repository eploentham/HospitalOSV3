/*
 * Clinic.java
 *
 * Created on 15 ตุลาคม 2548, 14:44 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.object;
import com.generalreport.utility.StandardObject;
/**
 *
 * @author tong(Padungrat)
 */
public class Clinic extends Persist implements StandardObject{
    
    /**รหัสของตาราง*/
    public String clinic_id;
    /**ชื่อ Clinic*/
    public String name;
    /**ชนิดของ clinic*/
    public String service_type;
    /**ใช้งานได้หรือไม่*/
    public String active;
    public Clinic() {
        idTable ="140";
        tableName= "b_visit_clinic";  
    }

    public void setInitData() {
       
        
        clinic_id   ="";
        name   ="";
        service_type="";
        active ="";
    }

    public static String getStringTable()
    {
        return "b_visit_clinic";  
    }
    
    public static String getStringFieldPKTable() 
    {
        return "b_visit_clinic_id";    
    }
    public static String getStringFieldClinicNumber() 
    {
        return "visit_clinic_number";    
    }
    
    public static String getStringFieldClinicDescription() 
    {
        return "visit_clinic_description";    
    }
    public static String getStringFieldServiceGroupID() 
    {
        return "f_service_group_id";    
    }
    
    public static String getStringFieldActive() 
    {
        return "visit_clinic_active";    
    }
    
    public Object setInitDataFieldName() {
        this.pk_table = getStringFieldPKTable();
     
       
        clinic_id   = getStringFieldClinicNumber();
        name   = getStringFieldClinicDescription();
        service_type= getStringFieldServiceGroupID();
        active =getStringFieldActive();
        
        return this;
    }
    public String getCode() {
        return this.getObjectId();
    }
    
    public String getName() {
        return this.name;
    }
    public String toString(){
        return getName();
    }
}
