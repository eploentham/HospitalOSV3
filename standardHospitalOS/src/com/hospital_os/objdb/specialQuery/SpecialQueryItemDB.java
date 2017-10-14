/*
 * SpecialQueryItemDB.java
 *
 * Created on 4 ¾ÄÈ¨Ô¡ÒÂ¹ 2547, 10:00 ¹.
 */
package com.hospital_os.objdb.specialQuery;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.specialQuery.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author  amp
 */
public class SpecialQueryItemDB
{
    /** Creates a new instance of SpecialQueueOrderDB */
    public ConnectionInf theConnectionInf;
    private SpecialQueryItem dbObj ;
    private String table1 = "b_item";
    private String table2 = "b_item_subgroup";
    private static Item dbObjItem ;
    
    
    public SpecialQueryItemDB(ConnectionInf db)
    {
        theConnectionInf = db;
        dbObj = new SpecialQueryItem();
        dbObjItem = new Item();
        
        initConfig();
    }
    
    public boolean initConfigItem()
    {
        dbObjItem.table="b_item";
        dbObjItem.pk_field="b_item_id";
        dbObjItem.item_id   ="item_number";
        dbObjItem.common_name   ="item_common_name";
        dbObjItem.trade_name   ="item_trade_name";
        dbObjItem.nick_name   ="item_nick_name";
        dbObjItem.active   ="item_active";
        dbObjItem.item_group_code_category   ="b_item_subgroup_id";
        dbObjItem.item_group_code_billing   ="b_item_billing_subgroup_id";
        dbObjItem.printable="item_printable";
        dbObjItem.setItemGroupCodeCategoryName("item_subgroup_description");
        return true;
    }
    
    public boolean initConfig()
    {
        dbObj.item_id = "b_item_id";
        dbObj.item_number = "item_number";
        dbObj.common_name = "item_common_name";
        dbObj.trade_name = "item_trade_name";
        dbObj.nick_name = "item_nick_name";
        dbObj.item_group_code_category = "b_item_subgroup_id";
        dbObj.item_group_code_billing = "b_item_billing_subgroup_id";
        dbObj.printable = "item_printable";
        dbObj.active = "item_active";
        
        return true;
    }
    
    /*//////////////////////////////////////////////////////////*/
    public Vector queryDataItem(String pk) throws Exception
    {
        String sql = "SELECT b_item.b_item_id, b_item.item_number, b_item.item_common_name, b_item.item_trade_name, b_item.item_nick_name, b_item.item_active, b_item.b_item_subgroup_id, b_item.b_item_billing_subgroup_id, b_item.item_printable " +
        " FROM b_item INNER JOIN b_item_subgroup ON b_item.b_item_subgroup_id = b_item_subgroup.b_item_subgroup_id " +
        " WHERE (((b_item.item_active)='1') AND ((UPPER(b_item.item_common_name)) like UPPER('%" + pk.toUpperCase() + "%'))) OR (((UPPER(b_item.item_trade_name)) like UPPER('%" + pk.toUpperCase() + "%')) OR ((UPPER(b_item.item_nick_name)) like UPPER('%" + pk.toUpperCase() + "%')))" +
        " GROUP BY b_item.b_item_id, b_item.item_number, b_item.item_common_name, b_item.item_trade_name, b_item.item_nick_name, b_item.item_active, b_item.b_item_subgroup_id, b_item.b_item_billing_subgroup_id, b_item.item_printable, b_item_subgroup.f_item_group_id " +
        " HAVING (((b_item_subgroup.f_item_group_id)='1')) OR (((b_item_subgroup.f_item_group_id)='4')) ";
        Vector vc =  eQuery(sql);
        if(vc.size() ==0)
            return  null;
        else
            return vc;
    }
    
    
    public Vector selectByItemGroup(String keygroup, String pk, String active) throws Exception
    {   initConfigItem();
        String sql = "SELECT  " +
        
         dbObjItem.pk_field + ","
        + dbObjItem.item_id  + ","
        + dbObjItem.common_name + ","
        + dbObjItem.trade_name  + ","
        + dbObjItem.nick_name + ","
        + dbObjItem.active  + ","
        + dbObjItem.table+ "."+dbObjItem.item_group_code_category + ","
        + dbObjItem.item_group_code_billing  + ","
        + dbObjItem.printable + ","
        + dbObjItem.getItemGroupCodeCategoryName() +
        " FROM b_item INNER JOIN b_item_subgroup ON b_item.b_item_subgroup_id = b_item_subgroup.b_item_subgroup_id " +
        " WHERE ((b_item.item_active='1') " ;
        if(!keygroup.equals(""))
        {
            sql = sql + " and (b_item.b_item_subgroup_id = '" + keygroup + "') ";
            
        }
        sql = sql +
        " )AND ((UPPER(b_item.item_common_name) like UPPER('%" + pk.toUpperCase() + "%')) " +
        " OR (UPPER(b_item.item_trade_name) like UPPER('%" + pk.toUpperCase() + "%')) " +
        " OR (UPPER(b_item.item_nick_name) like UPPER('%" + pk.toUpperCase() + "%')))" ;
             
       
        
        
        sql  = sql + " order by " + dbObj.common_name ;
        
        Constant.println(sql);
        
        
        Vector vc =  eQueryOrderItem(sql);
        if(vc.size() ==0)
            return  null;
        else
            return vc;
    }
    
    /*///////////////////////////////////////////////////////////////////////*/
    private Vector eQuery(String sql) throws Exception
    {
        SpecialQueryItem p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        int i = 0;
        while(rs.next())
        {
            p = new SpecialQueryItem();
            p.item_id = rs.getString(dbObj.item_id);
            p.item_number = rs.getString(dbObj.item_number);
            p.common_name = rs.getString(dbObj.common_name);
            p.trade_name = rs.getString(dbObj.trade_name);
            p.nick_name = rs.getString(dbObj.nick_name);
            p.item_group_code_category = rs.getString(dbObj.item_group_code_category);
            p.item_group_code_billing = rs.getString(dbObj.item_group_code_billing);
            p.printable = rs.getString(dbObj.printable);
            p.active = rs.getString(dbObj.active);
            
            list.add(p);
            p = null;
            
        }
        rs.close();
        return list;
    }
    
    
    /*///////////////////////////////////////////////////////////////////////*/
    private Vector eQueryOrderItem(String sql) throws Exception
    {
        Item p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        int i = 0;
        while(rs.next())
        {
            p = new Item();
            p.setObjectId(rs.getString( dbObjItem.pk_field));
            p.item_id = rs.getString(dbObjItem.item_id);
            
            p.common_name = rs.getString(dbObjItem.common_name);
            p.trade_name = rs.getString(dbObjItem.trade_name);
            p.nick_name = rs.getString(dbObjItem.nick_name);
            p.item_group_code_category = rs.getString(dbObjItem.item_group_code_category);
            p.item_group_code_billing = rs.getString(dbObjItem.item_group_code_billing);
            p.printable = rs.getString(dbObjItem.printable);
            p.active = rs.getString(dbObjItem.active);
            p.setItemGroupCodeCategoryName(rs.getString(dbObjItem.getItemGroupCodeCategoryName()));
            
                 
            
            
            list.add(p);
            p = null;
            
        }
        rs.close();
        return list;
    }
    
    
    
}
