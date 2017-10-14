/*
 * SQLTemplate.java
 *
 * Created on 5 กันยายน 2548, 17:00 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportquery.object;
import com.hospital_os.usecase.connection.CommonInf;
import com.reportquery.usecase.object.*;
/**
 *
 * @author tong(Padungrat)
 */
public class SQLTemplate extends Persist implements StandardObject,CommonInf
{
 //   public String b_sql_template_id;
    public String sql_template_number;
    public String sql_template_description;
    public String sql_template_active;
    public String sql_template_sql;
    public String sql_template_is_query_by_date;
    /** Creates a new instance of SQLTemplate */
    public SQLTemplate() {
        idTable ="";
        tableName= "r_sql_template";
    }

    public static String getStringTable()
    {
       return "r_sql_template";
    }
    
    public static String getStringFieldPKTable()
    {
       return "r_sql_template_id";
    }
    
    public static String getStringFieldNumber()
    {
       return "sql_template_number";
    }
    
    public static String getStringFieldDescription()
    {
       return "sql_template_description";
    }
    
    public static String getStringFieldActive()
    {
       return "sql_template_active";
    }
    
    public static String getStringFieldSQL()
    {
       return "sql_template_sql";
    }
    
    public static String getStringFieldisQueryByDate()
    {
       return "sql_template_is_query_by_date";
    }
    
    public void setInitData() {
        //b_sql_template_id = "";
        sql_template_number = "";
        sql_template_description = "";
        sql_template_active = "";
        sql_template_sql = "";
        sql_template_is_query_by_date = "";
    }

    public Object setInitDataFieldName() {
        this.pk_table = getStringFieldPKTable();
       
        sql_template_number = getStringFieldNumber();
        sql_template_description = getStringFieldDescription();
        sql_template_active = getStringFieldActive();
        sql_template_sql = getStringFieldSQL();
        sql_template_is_query_by_date = getStringFieldisQueryByDate();
        return this;
    }

    public String getCode() {
        return this.getObjectId();
    }

    public String getName() {
        return this.sql_template_description;
    }
    public String toString(){
        return this.sql_template_description;
        
    }
}
