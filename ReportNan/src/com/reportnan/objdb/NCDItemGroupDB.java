/*
 * NCDItemGroup.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2549, 14:04 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.objdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportnan.object.NCDItemGroup;
import com.reportnan.utility.Language;
import com.reportcenter.objdb.StandardDB;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author ojika
 */
public class NCDItemGroupDB implements StandardDB
{
    
    ConnectionInf theConnectionInf;
    NCDItemGroup theNCDItemGroup,ObjectNCDItemGroup;
    String SQL = "";
    ResultSet rs = null;
    java.util.Vector vObject ;
    int count =0;
    boolean bresult = false;
    
    public NCDItemGroupDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
        theNCDItemGroup = new NCDItemGroup();        
        theNCDItemGroup.setInitDataFieldName();
    }

    public int deleteByKeyID(String key_id)throws Exception
    {
        SQL = "DELETE " +
                " FROM " + theNCDItemGroup.tableName +
                " WHERE " +
                " " + theNCDItemGroup.tableName + "." + theNCDItemGroup.pk_table + "='" + key_id + "' ";
        System.out.println("SQL OPDItem : NCDItemGroup : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public int insertData(Object object)throws Exception
    {
        ObjectNCDItemGroup = (NCDItemGroup)object;
        ObjectNCDItemGroup.generateOID(ObjectNCDItemGroup.idTable);
        SQL = "INSERT INTO " +
                " " + theNCDItemGroup.tableName +
                " ( " +
                " " + theNCDItemGroup.pk_table + "," +
                " " + theNCDItemGroup.ncd_item_group_number + "," +
                " " + theNCDItemGroup.ncd_item_group_description + " " +
                ") " +
                " VALUES ("+
                "'" + ObjectNCDItemGroup.getObjectId() + "'," +
                "'" + ObjectNCDItemGroup.ncd_item_group_number + "'," +
                "'" + ObjectNCDItemGroup.ncd_item_group_description + "' " +
                ")" ;
        
        System.out.println("SQL NCDItemGroup : insertData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public Object selectByKeyID(String key_id)throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theNCDItemGroup.tableName +
                " WHERE " + theNCDItemGroup.tableName + "." + theNCDItemGroup.pk_table + " = '" + key_id + "' ";
        System.out.println("SQL NCDItemGroup : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }
    
    public Vector selectByKeyword(String keyword)throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theNCDItemGroup.tableName +
                " WHERE UPPER(" + theNCDItemGroup.tableName + "." + theNCDItemGroup.ncd_item_group_number + ") like UPPER('%" + keyword + "%') " +
                " or UPPER(" + theNCDItemGroup.tableName + "." + theNCDItemGroup.ncd_item_group_description + ") like UPPER('%" + keyword + "%') ";
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
        ObjectNCDItemGroup = new NCDItemGroup();
        try
        {
            while(rs.next())
            {
                ObjectNCDItemGroup = new NCDItemGroup();
                ObjectNCDItemGroup.setInitData();
                ObjectNCDItemGroup.setObjectId(rs.getString(theNCDItemGroup.pk_table));
                ObjectNCDItemGroup.ncd_item_group_number= rs.getString(theNCDItemGroup.ncd_item_group_number);                
                ObjectNCDItemGroup.ncd_item_group_description = rs.getString(theNCDItemGroup.ncd_item_group_description);
                
                vObject.add(ObjectNCDItemGroup);
                ObjectNCDItemGroup = null;
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
