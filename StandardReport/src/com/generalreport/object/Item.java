/*
 * Item.java
 *
 * Created on 5 ตุลาคม 2548, 16:24 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.object;
import com.generalreport.utility.StandardObject;
/**
 *
 * @author americus
 */
public class Item extends Persist implements StandardObject 
{
    /**รหัสรายการตรวจรักษา*/
    public String b_item_id;
    /**หมายเลขของรายการตรวจรักษา*/
    public String item_number;
    /**ชื่อรายการตรวจรักษา*/
    public String item_common_name;
    /**ชื่อทางการค้า*/
    public String item_trade_name;
    /**ชื่อเล่นของรายการตรวจรักษา*/
    public String item_nick_name;
    /**สถานะการใช้งานของรายการตรวจรักษา ว่าให้แสดงหรือไม่แสดง*/
    public String item_active;
    /**รหัสกลุ่มรายการ Order*/
    public String b_item_subgroup_id;
    /**รหัสกลุ่มรายการใบเสร็จ*/
    public String b_item_billing_subgroup_id;
    /**พิมพ์ฉลากยาหรือไม่*/
    public String item_printable;
    /** Creates a new instance of Item */
    public Item()
    {
        idTable ="174";
        tableName= "b_item";    
    }

    public void setInitData()
    {
        b_item_id = "";
        item_number = "";
        item_common_name = "";
        item_trade_name = "";
        item_nick_name = "";
        item_active = "";
        b_item_subgroup_id = "";
        b_item_billing_subgroup_id = "";
        item_printable = "";
    }
    
    public static String getStringTable()
    {
        return "b_item";
    }
    
    public static String getStringFieldPKTable()
    {
        return "b_item_id";
    }
     public static String getStringFieldItemNumber() 
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
    public static String getStringFieldItemSubgroupID()
    {
        return "b_item_subgroup_id";
    }
    public static String getStringFieldBillingSubgroupID()
    {
        return "b_item_billing_subgroup_id";
    }
    public static String getStringFieldPrintable()
    {
        return "item_printable";
    }
    
    public Object setInitDataFieldName()
    {
        this.pk_table = getStringFieldPKTable();
        item_number = getStringFieldItemNumber();
        item_common_name = getStringFieldCommonName();
        item_trade_name = getStringFieldTradeName();
        item_nick_name = getStringFieldNickName();
        item_active = getStringFieldActive();;
        b_item_subgroup_id = getStringFieldItemSubgroupID();
        b_item_billing_subgroup_id = getStringFieldBillingSubgroupID();
        item_printable = getStringFieldPrintable();
        return this;
    }
    
}
