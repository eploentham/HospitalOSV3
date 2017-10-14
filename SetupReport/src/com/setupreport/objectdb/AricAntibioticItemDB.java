/*
 * AricAntibioticItemDB.java
 *
 * Created on 22 ตุลาคม 2548, 12:12 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.objectdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.DBPersist;
import com.hosv3.utility.StandardDB;
import com.setupreport.object.*;
import com.setupreport.utility.Language;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author tong(Padungrat)
 */
public class AricAntibioticItemDB extends DBPersist implements StandardDB{
    
    ConnectionInf theConnectionInf;
    AricAntibioticItem theAricAntibioticItem,ObjectAricAntibioticItem;
    String SQL = "";
    ResultSet rs = null;
    java.util.Vector vObject ;
    int count =0;
    boolean bresult = false;
    public AricAntibioticItemDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theAricAntibioticItem = new AricAntibioticItem();
        
        theAricAntibioticItem.setInitDataFieldName();
    }
    /**
     *  ใช้ในการตรวจสอบ หาว่า Description ซ้ำกันหรือไม่ ถ้ามีซ้ำกัน จะ ให้ค่าเป็น false ถ้าไม่ซ้ำจะให้ค่าเป็น true
     *  @return เป็น boolean ถ้าเป็น true จะไม่ซ้ำ ถ้าเป็น false จะซ้ำ
     */
    public boolean checkSameAricAntibioticItemDescription(String AricAntibioticItemdescription)
    {
        count =0;
        bresult = true;
        SQL = "SELECT COUNT( " +
                    theAricAntibioticItem.tableName + "." + theAricAntibioticItem.pk_table  +
                 ")"   +
              " FROM " + theAricAntibioticItem.tableName +
              " WHERE " +
              " UPPER(" + theAricAntibioticItem.tableName + "." + theAricAntibioticItem.common_name + ")=UPPER('" + AricAntibioticItemdescription + "') ";
        System.out.println("SQL AricAntibioticItem : checkSameCode : " + SQL);
        
        try
        {
            rs = theConnectionInf.eQuery(SQL);
            while(rs.next()) 
            {
                count = rs.getInt(1);
            }
            rs.close();
        }
        catch(Exception ex)
        {
        }
        finally
        {
            if(count >0)
            {
                bresult = false;
            }
        }
        
        return bresult;
    }
    /**
     *  ใช้ในการตรวจสอบ หาว่า Code ซ้ำกันหรือไม่ ถ้ามีซ้ำกัน จะ ให้ค่าเป็น false ถ้าไม่ซ้ำจะให้ค่าเป็น true
     *  @return เป็น boolean ถ้าเป็น true จะไม่ซ้ำ ถ้าเป็น false จะซ้ำ
     */
    public boolean checkSameAricAntibioticItemCode(String AricAntibioticItemcode)
    {   count =0;
        bresult = true;
        SQL = "SELECT COUNT( " +
                    theAricAntibioticItem.tableName + "." + theAricAntibioticItem.pk_table  +
                 ")"   +
              " FROM " + theAricAntibioticItem.tableName +
              " WHERE " +
              " UPPER(" + theAricAntibioticItem.tableName + "." + theAricAntibioticItem.antibiotic_code + ")=UPPER('" + AricAntibioticItemcode + "') ";
        System.out.println("SQL AricAntibioticItem : checkSameCode : " + SQL);
        
        try
        {
            rs = theConnectionInf.eQuery(SQL);
            while(rs.next()) 
            {
                count = rs.getInt(1);
            }
            rs.close();
        }
        catch(Exception ex)
        {
        }
        finally
        {
            if(count >0)
            {
                bresult = false;
            }
        }
        
        return bresult;
    }
    
    public int deleteByKeyID(String key_id) throws Exception{
        
        SQL = "DELETE " +
              " FROM " + theAricAntibioticItem.tableName +
              " WHERE " +
              " " + theAricAntibioticItem.tableName + "." + theAricAntibioticItem.pk_table + "='" + key_id + "' ";
        System.out.println("SQL AricAntibioticItem : deleteByKeyID : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public int updateData(Object object)throws Exception {
        ObjectAricAntibioticItem = (AricAntibioticItem)object;
      
        SQL = "UPDATE " +
              " " + theAricAntibioticItem.tableName +
              " SET " +
              " " /* + theAricAntibioticItem.tableName + "." */+ theAricAntibioticItem.item_id + "='" + ObjectAricAntibioticItem.item_id + "', " +
              " " /* + theAricAntibioticItem.tableName + "." */+ theAricAntibioticItem.antibiotic_code + "='" + ObjectAricAntibioticItem.antibiotic_code + "', " +
              " " /* + theAricAntibioticItem.tableName + "." */ + theAricAntibioticItem.common_name + "='" + ObjectAricAntibioticItem.common_name + "'" +
              " WHERE " +
              " " + theAricAntibioticItem.tableName + "." + theAricAntibioticItem.pk_table + "='" + ObjectAricAntibioticItem.getObjectId() + "' ";
        System.out.println("SQL AricAntibioticItem : updateData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    public int insertData(Object object)throws Exception {
        ObjectAricAntibioticItem = (AricAntibioticItem)object;
        ObjectAricAntibioticItem.generateOID(ObjectAricAntibioticItem.idTable);
        SQL = "INSERT INTO " +
              " " + theAricAntibioticItem.tableName +
              " ( " +
              " " /* + theAricAntibioticItem.tableName + "." */+ theAricAntibioticItem.pk_table + "," +  
              " " /* + theAricAntibioticItem.tableName + "." */+ theAricAntibioticItem.item_id + "," +
              " " /* + theAricAntibioticItem.tableName + "." */+ theAricAntibioticItem.antibiotic_code + "," +
              " " /* + theAricAntibioticItem.tableName + "." */+ theAricAntibioticItem.common_name + "" +
              ") " +
              " VALUES ("+
              "'" + ObjectAricAntibioticItem.getObjectId() + "'," +  
              "'" + ObjectAricAntibioticItem.item_id + "'," + 
              "'" + ObjectAricAntibioticItem.antibiotic_code + "'," +
              "'" + ObjectAricAntibioticItem.common_name + "')" ;
              
        System.out.println("SQL AricAntibioticItem : insertData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    /**Select ข้อมูลที่อยู่ทั้งหมดในตาราง*/
    public Object selectBySearch(String search)throws Exception {
        SQL = "SELECT * FROM " +
              " " + theAricAntibioticItem.tableName + 
              " WHERE " + theAricAntibioticItem.tableName + "." + theAricAntibioticItem.antibiotic_code + " like '%" + search + "%' " +
              " OR "  + theAricAntibioticItem.tableName + "." + theAricAntibioticItem.common_name + " like '%" + search + "%' " +
              " ORDER BY " + theAricAntibioticItem.tableName + "." + theAricAntibioticItem.antibiotic_code   ;
        System.out.println("SQL AricAntibioticItem : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }
    
    /**Select ข้อมูลที่อยู่ทั้งหมดในตาราง*/
    public Object selectByAll() throws Exception{
        SQL = "SELECT * FROM " +
              " " + theAricAntibioticItem.tableName + 
              " ORDER BY " + theAricAntibioticItem.tableName + "." + theAricAntibioticItem.antibiotic_code   ;
        System.out.println("SQL AricAntibioticItem : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }
    /**Select ข้อมูลที่อยู่ในตาราง ตาม key หลัก ของตาราง*/
    public Object selectByKeyID(String key_id) throws Exception{
        SQL = "SELECT * FROM " +
              " " + theAricAntibioticItem.tableName + 
              " WHERE " + theAricAntibioticItem.tableName + "." + theAricAntibioticItem.pk_table + " = '" + key_id + "' "+
              " ORDER BY " + theAricAntibioticItem.tableName + "." + theAricAntibioticItem.antibiotic_code ;
        System.out.println("SQL AricAntibioticItem : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }

    public int updateByKeyID(String key_id)throws Exception {
        return 0;
    }
    
    private java.util.Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new java.util.Vector();
        ObjectAricAntibioticItem = new AricAntibioticItem();
        try{
            while(rs.next()) 
            {
                ObjectAricAntibioticItem = new AricAntibioticItem();
                ObjectAricAntibioticItem.setInitData();
                
                ObjectAricAntibioticItem.setObjectId(rs.getString(theAricAntibioticItem.pk_table));
                ObjectAricAntibioticItem.item_id = rs.getString(theAricAntibioticItem.item_id);        
                ObjectAricAntibioticItem.antibiotic_code= rs.getString(theAricAntibioticItem.antibiotic_code);         
                ObjectAricAntibioticItem.common_name= rs.getString(theAricAntibioticItem.common_name);               
                
                vObject.add(ObjectAricAntibioticItem);
                System.out.println("++++++++**********+++++++++++ "+vObject);
                ObjectAricAntibioticItem = null;
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
            {
                vObject = null;
                System.out.println("++++++++*****+++++++++++ "+vObject);
            }
        }
        return vObject;
    }
    
}
