/*
 * AricGroupDB.java
 *
 * Created on 22 ตุลาคม 2548, 12:12 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.objectdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreport.object.*;
import com.hosv3.utility.DBPersist;
import com.hosv3.utility.StandardDB;
//import com.setupreport.utility.language.Language;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author tong(Padungrat)
 */
public class AricGroupDB extends DBPersist implements StandardDB{
    
    ConnectionInf theConnectionInf;
    AricGroup theAricGroup,ObjectAricGroup;
    String SQL = "";
    ResultSet rs = null;
    java.util.Vector vObject ;
    int count =0;
    boolean bresult = false;
    public AricGroupDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theAricGroup = new AricGroup();
        
        theAricGroup.setInitDataFieldName();
    }
    /**
     *  ใช้ในการตรวจสอบ หาว่า Description ซ้ำกันหรือไม่ ถ้ามีซ้ำกัน จะ ให้ค่าเป็น false ถ้าไม่ซ้ำจะให้ค่าเป็น true
     *  @return เป็น boolean ถ้าเป็น true จะไม่ซ้ำ ถ้าเป็น false จะซ้ำ
     */
    public boolean checkSameAricGroupDescription(String aricgroupdescription)
    {
        count =0;
        bresult = true;
        SQL = "SELECT COUNT( " +
                    theAricGroup.tableName + "." + theAricGroup.pk_table  +
                 ")"   +
              " FROM " + theAricGroup.tableName +
              " WHERE " +
              " UPPER(" + theAricGroup.tableName + "." + theAricGroup.description + ")=UPPER('" + aricgroupdescription + "') ";
        System.out.println("SQL AricGroup : checkSameCode : " + SQL);
        
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
   /* public boolean checkSameAricGroupCode(String aricgroupcode)
    {   count =0;
        bresult = true;
        SQL = "SELECT COUNT( " +
                    theAricGroup.tableName + "." + theAricGroup.pk_table  +
                 ")"   +
              " FROM " + theAricGroup.tableName +
              " WHERE " +
              " UPPER(" + theAricGroup.tableName + "." + theAricGroup.number + ")=UPPER('" + aricgroupcode + "') ";
        System.out.println("SQL AricGroup : checkSameCode : " + SQL);
        
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
    **/
    
    /**
     *  ใช้ในการตรวจสอบ หาว่า Code ซ้ำกันหรือไม่ ถ้ามีซ้ำกัน จะ ให้ค่าเป็น false ถ้าไม่ซ้ำจะให้ค่าเป็น true
     *  @return เป็น boolean ถ้าเป็น true จะไม่ซ้ำ ถ้าเป็น false จะซ้ำ
     */
    public boolean checkSameAricGroupCode(String aricgroupcode,String key_id)
    {   count =0;
        bresult = true;
        
        if(key_id.equalsIgnoreCase(""))
        {
            SQL = "SELECT COUNT( " +
                theAricGroup.tableName + "." + theAricGroup.pk_table  +
                ")"   +
                " FROM " + theAricGroup.tableName +
                " WHERE " +
                " UPPER(" + theAricGroup.tableName + "." + theAricGroup.number + ")=UPPER('" + aricgroupcode + "') ";
        }
        else
        {
            SQL = "SELECT COUNT( " +
                theAricGroup.tableName + "." + theAricGroup.pk_table  +
                ")"   +
                " FROM " + theAricGroup.tableName +
                " WHERE " +
                " (UPPER(" + theAricGroup.tableName + "." + theAricGroup.number + ")=UPPER('" + aricgroupcode + "')) " +
                " AND " + theAricGroup.tableName + "." + theAricGroup.pk_table  + " <> " + key_id ;
        }
        
        System.out.println("SQL EyeGroup : checkSameCode : " + SQL);
        
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
    public boolean checkSameAricGroupDescription(String aricgroupdescription,String key_id)
    {   count =0;
        bresult = true;
        
        if(key_id.equalsIgnoreCase(""))
        {
            SQL = "SELECT COUNT( " +
                theAricGroup.tableName + "." + theAricGroup.pk_table  +
                ")"   +
                " FROM " + theAricGroup.tableName +
                " WHERE " +
                " UPPER(" + theAricGroup.tableName + "." + theAricGroup.description + ")=UPPER('" + aricgroupdescription + "') ";
        }
        else
        {
            SQL = "SELECT COUNT( " +
                theAricGroup.tableName + "." + theAricGroup.pk_table  +
                ")"   +
                " FROM " + theAricGroup.tableName +
                " WHERE " +
                " (UPPER(" + theAricGroup.tableName + "." + theAricGroup.description + ")=UPPER('" + aricgroupdescription + "')) " +
                " AND " + theAricGroup.tableName + "." + theAricGroup.pk_table  + " <> " + key_id ;
        }
        
        System.out.println("SQL EyeGroup : checkSameDescription : " + SQL);
        
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
              " FROM " + theAricGroup.tableName +
              " WHERE " +
              " " + theAricGroup.tableName + "." + theAricGroup.pk_table + "='" + key_id + "' ";
        System.out.println("SQL AricGroup : deleteByKeyID : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public int updateData(Object object) throws Exception{
        ObjectAricGroup = (AricGroup)object;
      
        SQL = "UPDATE " +
              " " + theAricGroup.tableName +
              " SET " +
              " " /* + theAricGroup.tableName + "." */+ theAricGroup.number + "='" + ObjectAricGroup.number + "', " +
              " " /* + theAricGroup.tableName + "." */ + theAricGroup.description + "='" + ObjectAricGroup.description + "'" +
              " WHERE " +
              " " + theAricGroup.tableName + "." + theAricGroup.pk_table + "='" + ObjectAricGroup.getObjectId() + "' ";
        System.out.println("SQL AricGroup : updateData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    public int insertData(Object object) throws Exception{
        ObjectAricGroup = (AricGroup)object;
        ObjectAricGroup.generateOID(ObjectAricGroup.idTable);
        SQL = "INSERT INTO " +
              " " + theAricGroup.tableName +
              " ( " +
              " " /* + theAricGroup.tableName + "." */+ theAricGroup.pk_table + "," +  
              " " /* + theAricGroup.tableName + "." */+ theAricGroup.number + "," +
              " " /* + theAricGroup.tableName + "." */+ theAricGroup.description + "" +
              ") " +
              " VALUES ("+
              "'" + ObjectAricGroup.getObjectId() + "'," +  
              "'" + ObjectAricGroup.number + "'," +
              "'" + ObjectAricGroup.description + "')" ;
              
        System.out.println("SQL AricGroup : insertData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    /**Select ข้อมูลที่อยู่ทั้งหมดในตาราง*/
    public Object selectBySearch(String search) throws Exception{
        SQL = "SELECT * FROM " +
              " " + theAricGroup.tableName + 
              " WHERE " + theAricGroup.tableName + "." + theAricGroup.number + " like '%" + search + "%' " +
              " OR "  + theAricGroup.tableName + "." + theAricGroup.description + " like '%" + search + "%' " +
              " ORDER BY " + theAricGroup.tableName + "." + theAricGroup.number   ;
        System.out.println("SQL AricGroup : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }
    
    /**Select ข้อมูลที่อยู่ทั้งหมดในตาราง*/
    public Object selectByAll() throws Exception{
        SQL = "SELECT * FROM " +
              " " + theAricGroup.tableName + 
              " ORDER BY " + theAricGroup.tableName + "." + theAricGroup.number   ;
        System.out.println("SQL AricGroup : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }
    /**Select ข้อมูลที่อยู่ในตาราง ตาม key หลัก ของตาราง*/
    public Object selectByKeyID(String key_id) throws Exception{
        SQL = "SELECT * FROM " +
              " " + theAricGroup.tableName + 
              " WHERE " + theAricGroup.tableName + "." + theAricGroup.pk_table + " = '" + key_id + "' "+
              " ORDER BY " + theAricGroup.tableName + "." + theAricGroup.number   ;
        System.out.println("SQL AricGroup : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }

    public int updateByKeyID(String key_id) throws Exception{
        return 0;
    }
    
    private java.util.Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new java.util.Vector();
        ObjectAricGroup = new AricGroup();
        try{
            while(rs.next()) 
            {
                ObjectAricGroup = new AricGroup();
                ObjectAricGroup.setInitData();
                ObjectAricGroup.setObjectId(rs.getString(theAricGroup.pk_table));
                ObjectAricGroup.number= rs.getString(theAricGroup.number);               
                
                ObjectAricGroup.description= rs.getString(theAricGroup.description);               
                
                vObject.add(ObjectAricGroup);
                ObjectAricGroup = null;
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
