/*
 * ItemDB.java
 *
 * Created on 28 ตุลาคม 2548, 9:50 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.objectdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreport.object.*;
import com.setupreport.utility.Language;
import com.hosv3.utility.DBPersist;
import com.hosv3.utility.StandardDB;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author tong(Padungrat)
 */
public class ItemDB  extends DBPersist implements StandardDB {
    
    ConnectionInf theConnectionInf;
    Item theItem,ObjectItem;
    CategoryGroupItem theCategoryGroupItem;
    String SQL = "";
    ResultSet rs = null;
    java.util.Vector vObject ;
    int count =0;
    boolean bresult = false;
    public ItemDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theItem = new Item();
        theCategoryGroupItem = new CategoryGroupItem();
        theItem.setInitDataFieldName();
        theCategoryGroupItem.setInitDataFieldName();
    }

    
    public Object selectBySearchForDrug(String search) throws Exception
    {
        SQL = "SELECT " + 
              theItem.tableName + "." + theItem.pk_table + "," +
              theItem.tableName + "." + theItem.item_id + "," +      
              theItem.tableName + "." + theItem.common_name + "" +
              " FROM " + 
                 theItem.tableName + " INNER JOIN " + theCategoryGroupItem.tableName +
                  " ON " +  theItem.tableName + "." + theItem.item_group_code_category + "=" +
                  theCategoryGroupItem.tableName  + "." + theCategoryGroupItem.pk_table  +
              " WHERE " +
                  theCategoryGroupItem.tableName  + "." + theCategoryGroupItem.category_group_code + "='1'" +
                  " AND " + theItem.tableName + "." + theItem.active + " = '1'" +
                  " AND (UPPER(" + theItem.tableName + "." + theItem.common_name + ") like '%" + search.toUpperCase() + "%'" +
                        " OR UPPER(" + theItem.tableName + "." + theItem.trade_name + ") like '%" + search.toUpperCase() + "%'" +
                        " OR UPPER(" + theItem.tableName + "." + theItem.nick_name + ") like '%" + search.toUpperCase() + "%')" ;

        System.out.println("SQL AricGroup : selectByKeyID : " + SQL);
        return eQueryForDrug(SQL);
    }
    
    public Vector selectBySearch(String search) throws Exception
    {
        SQL = "SELECT " + 
              theItem.tableName + "." + theItem.pk_table + "," +
              theItem.tableName + "." + theItem.item_id + "," +      
              theItem.tableName + "." + theItem.common_name + "" +
              " FROM " + 
                 theItem.tableName + " INNER JOIN " + theCategoryGroupItem.tableName +
                  " ON " +  theItem.tableName + "." + theItem.item_group_code_category + "=" +
                  theCategoryGroupItem.tableName  + "." + theCategoryGroupItem.pk_table  +
              " WHERE " +
                  theItem.tableName + "." + theItem.active + " = '1'" +
                  " AND (UPPER(" + theItem.tableName + "." + theItem.common_name + ") like '%" + search.toUpperCase() + "%'" +
                        " OR UPPER(" + theItem.tableName + "." + theItem.trade_name + ") like '%" + search.toUpperCase() + "%'" +
                        " OR UPPER(" + theItem.tableName + "." + theItem.nick_name + ") like '%" + search.toUpperCase() + "%')" ;

        System.out.println("SQL : selectBySearch : " + SQL);
        return eQueryForDrug(SQL);
    }
    
    
    public int deleteByKeyID(String key_id)  throws Exception{
        return 0;
    }

    public int insertData(Object object)  throws Exception{
        return 0;
    }

    public Object selectByKeyID(String key_id) throws Exception {
        return null;
    }

    public int updateByKeyID(String key_id)  throws Exception{
        return 0;
    }
    
    private java.util.Vector eQueryForDrug(String sql) throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new java.util.Vector();
        ObjectItem = new Item();
        try{
            while(rs.next()) 
            {
                ObjectItem = new Item();
                ObjectItem.setInitData();
                ObjectItem.setObjectId(rs.getString(theItem.pk_table));
           //     ObjectItem.active = rs.getString(ObjectItem.active);  
                ObjectItem.common_name  = rs.getString(theItem.common_name);  
                ObjectItem.item_id = rs.getString(theItem.item_id);  
           //     ObjectItem.nick_name = rs.getString(ObjectItem.nick_name);  
           //     ObjectItem.trade_name = rs.getString(ObjectItem.trade_name);  
           //     ObjectItem.item_group_code_billing = rs.getString(ObjectItem.item_group_code_billing); 
           //     ObjectItem.item_group_code_category = rs.getString(ObjectItem.item_group_code_category); 
             //   ObjectItem.printable = rs.getString(theItem.printable); 
                
               
                vObject.add(ObjectItem);
                ObjectItem = null;
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(vObject.size() == 0)
                vObject = null;
        }
        return vObject;
    }
    
}
