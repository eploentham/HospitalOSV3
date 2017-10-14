/*
 * Item.java
 *
 * Created on 28 ตุลาคม 2548, 9:01 น.
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
public class Item  extends Persist implements StandardObject {
    
    public String item_id;
    public String common_name;
    public String trade_name;
    public String nick_name;
    public String item_group_code_category;
    public String item_group_code_billing;
    public String printable;
    public String active;


    private String item_group_code_category_name;
    public Item() {
        idTable ="174";        
        tableName= "b_item";
    }
    
    public static String getStringTable()
    {
        return "b_item";
    }   
    public static String getStringFieldPKTable()
    {
        return "b_item_id";
    }
    public static String getStringFieldNumber()
    {
        return "item_number";
    }
    public static String getStringFieldCommonName()
    {
        return "item_common_name";
    }
    
    public static String getStringFieldTradeName()
    {
        return "item_trade_name";
    }
    public static String getStringFieldNickName()
    {
        return "item_nick_name";
    }
    public static String getStringFieldActive()
    {
        return "item_active";
    }
    public static String getStringFieldItemSubGroupID()
    {
        return "b_item_subgroup_id";
    }
    public static String getStringFieldItemBillingSubGroupID()
    {
        return "b_item_billing_subgroup_id";
    }
    
    public static String getStringFieldItemPrintable()
    {
        return "item_printable";
    }
    public void setInitData() {
        item_id =  "";
        common_name =  "";
        trade_name =  "";
        nick_name =  "";
        item_group_code_category =  "";
        item_group_code_billing =  "";
        printable =  "";
        active =  "";
    }

    public Object setInitDataFieldName() {
        this.pk_table =  this.getStringFieldPKTable();
  
        item_id   =getStringFieldNumber();
        common_name   = getStringFieldCommonName();
        trade_name   = getStringFieldTradeName();
        nick_name   = getStringFieldNickName();
        active   = getStringFieldActive();
        item_group_code_category   = getStringFieldItemSubGroupID();
        item_group_code_billing   = getStringFieldItemBillingSubGroupID();
        printable= getStringFieldItemPrintable();
        return this;
    }
    
}
