/*
 * OperatingItemDB.java
 *
 * Created on 21 ÁÔ¶Ø¹ÒÂ¹ 2549, 15:02 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.objdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportnan.object.OperatingItem;
import com.reportnan.utility.Language;
import com.reportcenter.objdb.StandardDB;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author ojika
 */
public class OperatingItemDB implements StandardDB
{
    ConnectionInf theConnectionInf;
    OperatingItem theOperatingItem,ObjectOperatingItem;
    String SQL = "";
    ResultSet rs = null;
    java.util.Vector vObject ;
            
    public OperatingItemDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
        theOperatingItem = new OperatingItem();        
        theOperatingItem.setInitDataFieldName();
    }

    public int deleteByKeyID(String key_id)throws Exception
    {
        SQL = "DELETE " +
                " FROM " + theOperatingItem.tableName +
                " WHERE " +
                " " + theOperatingItem.tableName + "." + theOperatingItem.pk_table + "='" + key_id + "' ";
        System.out.println("SQL OperatingItem : deleteByKeyID : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public int insertData(Object object)throws Exception
    {
        ObjectOperatingItem = (OperatingItem)object;
        ObjectOperatingItem.generateOID(ObjectOperatingItem.idTable);
        SQL = "INSERT INTO " +
                " " + theOperatingItem.tableName +
                " ( " +
                " " + theOperatingItem.pk_table + "," +
                " " + theOperatingItem.b_item_id + "," +
                " " + theOperatingItem.operating_item_number + "," +
                " " + theOperatingItem.operating_item_description + "," +
                " " + theOperatingItem.operating_item_common_name + " " +
                ") " +
                " VALUES ("+
                "'" + ObjectOperatingItem.getObjectId() + "'," +
                "'" + ObjectOperatingItem.b_item_id + "'," +
                "'" + ObjectOperatingItem.operating_item_number + "'," +
                "'" + ObjectOperatingItem.operating_item_description + "'," +
                "'" + ObjectOperatingItem.operating_item_common_name + "' " +
                ")" ;
        
        System.out.println("SQL OperatingItem : insertData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    public int updateData(Object object)throws Exception
    {
        ObjectOperatingItem = (OperatingItem)object;
        SQL = "UPDATE  " +
                " " + theOperatingItem.tableName +
                " SET " +
                " " + theOperatingItem.operating_item_number + " = '" + ObjectOperatingItem.operating_item_number + "'" +
                ", " + theOperatingItem.operating_item_description + " = '" + ObjectOperatingItem.operating_item_description + "'" +
                ", " + theOperatingItem.operating_item_common_name + " = '" + ObjectOperatingItem.operating_item_common_name + "'" +
                " WHERE "+
                " " + theOperatingItem.pk_table + " = '" + ObjectOperatingItem.getObjectId() + "'";
        
        System.out.println("SQL OperatingItem : updateData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    public Vector selectByKeyword(String keyword)throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theOperatingItem.tableName +
                " WHERE UPPER(" + theOperatingItem.tableName + "." + theOperatingItem.operating_item_number + ") like UPPER('%" + keyword + "%') " +
                " or UPPER(" + theOperatingItem.tableName + "." + theOperatingItem.operating_item_common_name + ") like UPPER('%" + keyword + "%') " +
                " or UPPER(" + theOperatingItem.tableName + "." + theOperatingItem.operating_item_description + ") like UPPER('%" + keyword + "%') ";
        System.out.println("SQL OperatingItem : selectByKeyword : " + SQL);
        return eQuery(SQL);
    }

    public Object selectByKeyID(String key_id)throws Exception
    {
        return null;
    }

    public int updateByKeyID(String key_id)throws Exception
    {
        return 0;
    }
    
    private java.util.Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new java.util.Vector();
        ObjectOperatingItem = new OperatingItem();
        try
        {
            while(rs.next())
            {
                ObjectOperatingItem = new OperatingItem();
                ObjectOperatingItem.setInitData();
                ObjectOperatingItem.setObjectId(rs.getString(theOperatingItem.pk_table));
                ObjectOperatingItem.b_item_id= rs.getString(theOperatingItem.b_item_id);                
                ObjectOperatingItem.operating_item_number = rs.getString(theOperatingItem.operating_item_number);
                ObjectOperatingItem.operating_item_description = rs.getString(theOperatingItem.operating_item_description);
                ObjectOperatingItem.operating_item_common_name = rs.getString(theOperatingItem.operating_item_common_name);
                
                vObject.add(ObjectOperatingItem);
                ObjectOperatingItem = null;
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
