/*
 * NCDItemGroupMapItem.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2549, 14:04 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.objdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportnan.object.NCDItemGroupMapItem;
import com.reportnan.utility.Language;
import com.reportcenter.objdb.StandardDB;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author ojika
 */
public class NCDItemGroupMapItemDB implements StandardDB 
{
    
    ConnectionInf theConnectionInf;
    NCDItemGroupMapItem theNCDItemGroupMapItem,ObjectNCDItemGroupMapItem;
    String SQL = "";
    ResultSet rs = null;
    java.util.Vector vObject ;
    int count =0;
    boolean bresult = false;
    
    public NCDItemGroupMapItemDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
        theNCDItemGroupMapItem = new NCDItemGroupMapItem();        
        theNCDItemGroupMapItem.setInitDataFieldName();
    }

    public int deleteByKeyID(String key_id)throws Exception
    {
        SQL = "DELETE " +
                " FROM " + theNCDItemGroupMapItem.tableName +
                " WHERE " +
                " " + theNCDItemGroupMapItem.tableName + "." + theNCDItemGroupMapItem.pk_table + "='" + key_id + "' ";
        System.out.println("SQL OPDItem : NCDItemGroupMapItem : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public int insertData(Object object)throws Exception
    {
        ObjectNCDItemGroupMapItem = (NCDItemGroupMapItem)object;
        ObjectNCDItemGroupMapItem.generateOID(ObjectNCDItemGroupMapItem.idTable);
        SQL = "INSERT INTO " +
                " " + theNCDItemGroupMapItem.tableName +
                " ( " +
                " " + theNCDItemGroupMapItem.pk_table + "," +
                " " + theNCDItemGroupMapItem.r_ncd_item_group_id + "," +
                " " + theNCDItemGroupMapItem.b_item_id + "," +
                " " + theNCDItemGroupMapItem.ncd_item_group_map_item_number + "," +
                " " + theNCDItemGroupMapItem.ncd_item_group_map_item_description + " " +
                ") " +
                " VALUES ("+
                "'" + ObjectNCDItemGroupMapItem.getObjectId() + "'," +
                "'" + ObjectNCDItemGroupMapItem.r_ncd_item_group_id + "'," +
                "'" + ObjectNCDItemGroupMapItem.b_item_id + "'," +
                "'" + ObjectNCDItemGroupMapItem.ncd_item_group_map_item_number + "'," +
                "'" + ObjectNCDItemGroupMapItem.ncd_item_group_map_item_description + "' " +
                ")" ;
        
        System.out.println("SQL NCDItemGroupMapItem : insertData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL); 
    }

    public Object selectByKeyID(String key_id)throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theNCDItemGroupMapItem.tableName +
                " WHERE " + theNCDItemGroupMapItem.tableName + "." + theNCDItemGroupMapItem.pk_table + " = '" + key_id + "' ";
        System.out.println("SQL NCDItemGroupMapItem : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }
    
    public Vector selectByNCDItemGroupID(String NCDItemGroupId)throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theNCDItemGroupMapItem.tableName +
                " WHERE " + theNCDItemGroupMapItem.tableName + "." + theNCDItemGroupMapItem.r_ncd_item_group_id + " = '" + NCDItemGroupId + "' ";
        System.out.println("SQL NCDItemGroupMapItem : selectByNCDItemGroupId : " + SQL);
        return eQuery(SQL);
    }

    public int updateByKeyID(String key_id)
    {
        return 0;
    }
    
    private java.util.Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new java.util.Vector();
        ObjectNCDItemGroupMapItem = new NCDItemGroupMapItem();
        try
        {
            while(rs.next())
            {
                ObjectNCDItemGroupMapItem = new NCDItemGroupMapItem();
                ObjectNCDItemGroupMapItem.setInitData();
                ObjectNCDItemGroupMapItem.setObjectId(rs.getString(theNCDItemGroupMapItem.pk_table));
                ObjectNCDItemGroupMapItem.b_item_id= rs.getString(theNCDItemGroupMapItem.b_item_id);                
                ObjectNCDItemGroupMapItem.r_ncd_item_group_id = rs.getString(theNCDItemGroupMapItem.r_ncd_item_group_id);
                ObjectNCDItemGroupMapItem.ncd_item_group_map_item_number = rs.getString(theNCDItemGroupMapItem.ncd_item_group_map_item_number);
                ObjectNCDItemGroupMapItem.ncd_item_group_map_item_description = rs.getString(theNCDItemGroupMapItem.ncd_item_group_map_item_description);
                
                vObject.add(ObjectNCDItemGroupMapItem);
                ObjectNCDItemGroupMapItem = null;
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
        }
        finally
        {
            if(vObject.size() == 0)
                vObject = null;
        }
        return vObject;
    }
    
}
