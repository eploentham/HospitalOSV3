/*
 * ItemNanDB.java
 *
 * Created on 20 มิถุนายน 2549, 15:09 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.objdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.object.Item;
import java.util.*;
import java.sql.*;
/**
 *
 * @author ojika
 */
public class ItemNanDB
{
    public ConnectionInf theConnectionInf;
    public Item dbObj;
    String SQL ="";
    
    public ItemNanDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
        dbObj = new Item();       
        initConfig();
    }
    
    public boolean initConfig()
    {
        dbObj.table= "b_item";
        dbObj.pk_field= "b_item_id";
        dbObj.item_id   = "item_number";
        dbObj.common_name   = "item_common_name";
        dbObj.trade_name   ="item_trade_name";
        dbObj.nick_name   ="item_nick_name";
        dbObj.active = "item_active";
        
        return true;
        
    }

    public Object selectBySearch(String search, int offset) throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + dbObj.table +   
                " WHERE ((UPPER(" + dbObj.table + "." + dbObj.item_id + ") like UPPER('%" + search + "%') " +
                " OR UPPER("  + dbObj.table + "." + dbObj.common_name + ") like UPPER('%" + search + "%') " +
                " OR UPPER("  + dbObj.table + "." + dbObj.nick_name + ") like UPPER('%" + search + "%') " +
                " OR UPPER("  + dbObj.table + "." + dbObj.trade_name + ") like UPPER('%" + search + "%')) " +
                
                " AND " + dbObj.table + "." + dbObj.active + " = '1') " +
                " ORDER BY " + dbObj.table + "." + dbObj.common_name +
                " Limit 15 Offset " + offset ; 
        
        System.out.println("SQL Item : selectBySearch : " + SQL);
        return eQuery(SQL);
    }
    
    /**Select ข้อมูลที่อยู่ทั้งหมดในตาราง*/
    public Object selectAll(int offset)  throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + dbObj.table +
                " WHERE " + dbObj.table + "." + dbObj.active + " ='1'" +
                " ORDER BY " + dbObj.table + "." + dbObj.common_name +
                " Limit 15 Offset " + offset ;
        
        System.out.println("SQL Item : selectAll : " + SQL);
        return eQuery(SQL);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        Item p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Item();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.item_id = rs.getString(dbObj.item_id);
            p.common_name = rs.getString(dbObj.common_name);
            list.add(p);
            p = null;
        }
        rs.close();
        return list;
    }
    
}
