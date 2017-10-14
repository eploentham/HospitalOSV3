/*
 * OPDItem.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2549, 14:05 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.objdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportnan.object.OPDItem;
import com.reportnan.utility.Language;
import com.reportcenter.objdb.StandardDB;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author ojika
 */
public class OPDItemDB implements StandardDB
{
    
    ConnectionInf theConnectionInf;
    OPDItem theOPDItem,ObjectOPDItem;
    String SQL = "";
    ResultSet rs = null;
    java.util.Vector vObject ;
    int count =0;
    boolean bresult = false;
    
    public OPDItemDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
        theOPDItem = new OPDItem();        
        theOPDItem.setInitDataFieldName();
    }

    public int deleteByKeyID(String key_id)throws Exception
    {
        SQL = "DELETE " +
                " FROM " + theOPDItem.tableName +
                " WHERE " +
                " " + theOPDItem.tableName + "." + theOPDItem.pk_table + "='" + key_id + "' ";
        System.out.println("SQL OPDItem : deleteByKeyID : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public int insertData(Object object)throws Exception
    {
        ObjectOPDItem = (OPDItem)object;
        ObjectOPDItem.generateOID(ObjectOPDItem.idTable);
        SQL = "INSERT INTO " +
                " " + theOPDItem.tableName +
                " ( " +
                " " + theOPDItem.pk_table + "," +
                " " + theOPDItem.b_item_id + "," +
                " " + theOPDItem.opd_item_number + "," +
                " " + theOPDItem.opd_item_description + "," +
                " " + theOPDItem.opd_item_common_name + " " +
                ") " +
                " VALUES ("+
                "'" + ObjectOPDItem.getObjectId() + "'," +
                "'" + ObjectOPDItem.b_item_id + "'," +
                "'" + ObjectOPDItem.opd_item_number + "'," +
                "'" + ObjectOPDItem.opd_item_description + "'," +
                "'" + ObjectOPDItem.opd_item_common_name + "' " +
                ")" ;
        
        System.out.println("SQL OPDItem : insertData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    public int updateData(Object object)throws Exception
    {
        ObjectOPDItem = (OPDItem)object;
        SQL = "UPDATE  " +
                " " + theOPDItem.tableName +
                " SET " +
                " " + theOPDItem.opd_item_number + " = '" + ObjectOPDItem.opd_item_number + "'" +
                ", " + theOPDItem.opd_item_description + " = '" + ObjectOPDItem.opd_item_description + "'" +
                ", " + theOPDItem.opd_item_common_name + " = '" + ObjectOPDItem.opd_item_common_name + "'" +
                " WHERE "+
                " " + theOPDItem.pk_table + " = '" + ObjectOPDItem.getObjectId() + "'";
        
        System.out.println("SQL OPDItem : updateData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public Object selectByKeyID(String key_id)throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theOPDItem.tableName +
                " WHERE " + theOPDItem.tableName + "." + theOPDItem.pk_table + " = '" + key_id + "' ";
        System.out.println("SQL OPDItem : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }
    
    public Vector selectByKeyword(String keyword)throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theOPDItem.tableName +
                " WHERE UPPER(" + theOPDItem.tableName + "." + theOPDItem.opd_item_number + ") like UPPER('%" + keyword + "%') " +
                " or UPPER(" + theOPDItem.tableName + "." + theOPDItem.opd_item_common_name + ") like UPPER('%" + keyword + "%') " +
                " or UPPER(" + theOPDItem.tableName + "." + theOPDItem.opd_item_description + ") like UPPER('%" + keyword + "%') ";
        System.out.println("SQL NCDItemGroup : selectByKeyword : " + SQL);
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
        ObjectOPDItem = new OPDItem();
        try
        {
            while(rs.next())
            {
                ObjectOPDItem = new OPDItem();
                ObjectOPDItem.setInitData();
                ObjectOPDItem.setObjectId(rs.getString(theOPDItem.pk_table));
                ObjectOPDItem.b_item_id= rs.getString(theOPDItem.b_item_id);                
                ObjectOPDItem.opd_item_number = rs.getString(theOPDItem.opd_item_number);
                ObjectOPDItem.opd_item_description = rs.getString(theOPDItem.opd_item_description);
                ObjectOPDItem.opd_item_common_name = rs.getString(theOPDItem.opd_item_common_name);
                
                vObject.add(ObjectOPDItem);
                ObjectOPDItem = null;
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
