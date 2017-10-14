/*
 * VersionReport.java
 *
 * Created on 3 ¾ÄÈ¨Ô¡ÒÂ¹ 2548, 14:07 ¹.
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
public class VersionReport extends Persist implements StandardObject {
    
    public String number;
    public String description;
    public String version_app;
    public String notice;        
    public String update_time;
    public VersionReport() {
        idTable ="817";
        tableName= "s_report_version";  
    }
     public static String getStringTable()
    {
        return "s_report_version";  
    }
    
    public static String getStringFieldPKTable() 
    {
        return "s_report_version_id";    
    }
    public static String getStringFieldVersionNumber() 
    {
        return "report_version_number";    
    }
    public static String getStringFieldVersionDescription() 
    {
        return "report_version_description";    
    }
    
    public static String getStringFieldVersionAppNumber() 
    {
        return "report_version_application_number";    
    }
    public static String getStringFieldVersionNotice() 
    {
        return "report_version_notice";    
    }
    public static String getStringFieldVersionUpdateDateTime() 
    {
        return "report_version_update_date_time";    
    }
    public void setInitData() {
        number = "";
        description = "";
        version_app = "";
        notice = "";
        update_time = "";
    }

    public Object setInitDataFieldName() {
        this.pk_table = getStringFieldPKTable();
        number = getStringFieldVersionNumber();
        description = getStringFieldVersionDescription();
        version_app = getStringFieldVersionAppNumber();
        notice = getStringFieldVersionNotice();
        update_time = getStringFieldVersionUpdateDateTime();
        return this;
    }
    
}
