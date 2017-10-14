/*
 * VersionHealth.java
 *
 * Created on 4 พฤศจิกายน 2548, 10:43 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportcenter.object;
import com.reportcenter.usecase.connection.StandardObject;
/**
 *
 * @author tong(Padungrat)
 */
public class VersionHealth extends Persist implements StandardObject {
    
    /**ลำดับที่ของ version*/
    public String version_id;
    /**คำอธิบาย version*/
    public String description;
    /**version ของ application ที่ใช้ปัจจุบัน*/
    public String app_code;
    /**version ของ database ที่ใช้ปัจจุบัน*/
    public String db_code;
    /**วันที่-เวลา update */
    public String update_time;
    public VersionHealth() {
        idTable ="971";
        tableName= "s_health_version";
    }
    public static String getStringTable()
    {
        return "s_health_version";  
    }
    
    public static String getStringFieldPKTable() 
    {
        return "s_health_version_id";    
    }
    public static String getStringFieldVersionNumber() 
    {
        return "version_health_number";    
    }
    public static String getStringFieldVersionDiscription() 
    {
        return "version_health_description";    
    }
    
    public static String getStringFieldVersionAppNumber() 
    {
        return "version_health_application_number";    
    }
     public static String getStringFieldVersionDBNumber() 
    {
        return "version_health_database_number";    
    }
     
     public static String getStringFieldVersionUpdateDateTime() 
    {
        return "version_health_update_time";    
    }
    public void setInitData() {
       
        version_id   ="";
        description   ="";
        app_code   ="";
        db_code   ="";
        update_time="";
    }

    public Object setInitDataFieldName() {
        this.pk_table = getStringFieldPKTable();
   
        
        version_id   =getStringFieldVersionNumber();
        description   = getStringFieldVersionDiscription();
        app_code   = getStringFieldVersionAppNumber();
        db_code   = getStringFieldVersionDBNumber();
        update_time= getStringFieldVersionUpdateDateTime();
        return this;
    }
}
