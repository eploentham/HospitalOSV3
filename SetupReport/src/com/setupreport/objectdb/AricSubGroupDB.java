/*
 * AricSubGroupDB.java
 *
 * Created on 22 ตุลาคม 2548, 13:40 น.
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
public class AricSubGroupDB extends DBPersist implements StandardDB{
    
    ConnectionInf theConnectionInf;
    AricSubGroup theAricSubGroup,ObjectAricSubGroup;
    String SQL = "";
    ResultSet rs = null;
    java.util.Vector vObject ;
     int count =0;
    boolean bresult = false;
    public AricSubGroupDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theAricSubGroup = new AricSubGroup();
        
        theAricSubGroup.setInitDataFieldName();
    }

    /**
     *  ใช้ในการตรวจสอบ หาว่า Description ซ้ำกันหรือไม่ ถ้ามีซ้ำกัน จะ ให้ค่าเป็น false ถ้าไม่ซ้ำจะให้ค่าเป็น true
     *  @return เป็น boolean ถ้าเป็น true จะไม่ซ้ำ ถ้าเป็น false จะซ้ำ
     */
    public boolean checkSameAricGroupDescription(String aricsubgroupdescription)
    {
        count =0;
        bresult = true;
        SQL = "SELECT COUNT( " +
                    theAricSubGroup.tableName + "." + theAricSubGroup.pk_table  +
                 ")"   +
              " FROM " + theAricSubGroup.tableName +
              " WHERE " +
              " UPPER(" + theAricSubGroup.tableName + "." + theAricSubGroup.description + ")=UPPER('" + aricsubgroupdescription + "') ";
        System.out.println("SQL AricSubGroup : checkSameCode : " + SQL);
        
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
    public boolean checkSameAricGroupCode(String aricsubgroupcode)
    {   count =0;
        bresult = true;
        SQL = "SELECT COUNT( " +
                    theAricSubGroup.tableName + "." + theAricSubGroup.pk_table  +
                 ")"   +
              " FROM " + theAricSubGroup.tableName +
              " WHERE " +
              " UPPER(" + theAricSubGroup.tableName + "." + theAricSubGroup.number + ")=UPPER('" + aricsubgroupcode + "') ";
        System.out.println("SQL AricSubGroup : checkSameCode : " + SQL);
        
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
              " FROM " + theAricSubGroup.tableName +
              " WHERE " +
              " " + theAricSubGroup.tableName + "." + theAricSubGroup.pk_table + "='" + key_id + "' ";
        System.out.println("SQL AricSubGroup : deleteByKeyID : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public int updateData(Object object) throws Exception{
        ObjectAricSubGroup = (AricSubGroup)object;
      
        SQL = "UPDATE " +
              " " + theAricSubGroup.tableName +
              " SET " +
              " " /* + theAricSubGroup.tableName + "." */+ theAricSubGroup.number + "='" + ObjectAricSubGroup.number + "', " +
              " " /* + theAricSubGroup.tableName + "." */ + theAricSubGroup.description + "='" + ObjectAricSubGroup.description + "'," +
              " " /* + theAricSubGroup.tableName + "." */ + theAricSubGroup.aricgroupid + "='" + ObjectAricSubGroup.aricgroupid + "'" +
              " WHERE " +
              " " + theAricSubGroup.tableName + "." + theAricSubGroup.pk_table + "='" + ObjectAricSubGroup.getObjectId() + "' ";
        System.out.println("SQL AricSubGroup : updateData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    public int insertData(Object object) throws Exception{
        ObjectAricSubGroup = (AricSubGroup)object;
        ObjectAricSubGroup.generateOID(ObjectAricSubGroup.idTable);
        SQL = "INSERT INTO " +
              " " + theAricSubGroup.tableName +
              " ( " +
              " " /* + theAricSubGroup.tableName + "." */+ theAricSubGroup.pk_table + "," +  
              " " /* + theAricSubGroup.tableName + "." */+ theAricSubGroup.number + "," +
              " " /* + theAricSubGroup.tableName + "." */+ theAricSubGroup.description + "," +
                " " /* + theAricSubGroup.tableName + "." */+ theAricSubGroup.aricgroupid + "" +
              ") " +
              " VALUES ("+
              "'" + ObjectAricSubGroup.getObjectId() + "'," +  
              "'" + ObjectAricSubGroup.number + "'," +
              "'" + ObjectAricSubGroup.description + "'," +
              "'" + ObjectAricSubGroup.aricgroupid + "')" ;
              
        System.out.println("SQL AricSubGroup : insertData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public Object selectByAricSubGroupID(String aricsubgroupid)throws Exception
    {
        SQL = "SELECT * FROM " +
              " " + theAricSubGroup.tableName + 
              " WHERE " + theAricSubGroup.tableName + "." +theAricSubGroup.aricgroupid + " ='" + aricsubgroupid +"'" +
              " ORDER BY " + theAricSubGroup.tableName + "." + theAricSubGroup.number   ;
        System.out.println("SQL AricSubGroup : selectByAricSubGroup : " + SQL);
        return eQuery(SQL);
    }
    
    public Object selectByKeyID(String key_id) throws Exception{
        SQL = "SELECT * FROM " +
              " " + theAricSubGroup.tableName + 
              " WHERE " + theAricSubGroup.tableName + "." +theAricSubGroup.pk_table + " ='" + key_id +"'" +
              " ORDER BY " + theAricSubGroup.tableName + "." + theAricSubGroup.number   ;
        System.out.println("SQL AricSubGroup : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }

     /**Select ข้อมูลที่อยู่ทั้งหมดในตาราง*/
    public Object selectBySearch(String search) throws Exception{
        SQL = "SELECT * FROM " +
              " " + theAricSubGroup.tableName + 
              " WHERE " + theAricSubGroup.tableName + "." + theAricSubGroup.number + " like '%" + search + "%' " +
              " OR "  + theAricSubGroup.tableName + "." + theAricSubGroup.description + " like '%" + search + "%' " +
              " ORDER BY " + theAricSubGroup.tableName + "." + theAricSubGroup.number   ;
        System.out.println("SQL AricSubGroup : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }
    
    
    public int updateByKeyID(String key_id) throws Exception{
        return 0;
    }
    
    private java.util.Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new java.util.Vector();
        ObjectAricSubGroup = new AricSubGroup();
        try{
            while(rs.next()) 
            {
                ObjectAricSubGroup = new AricSubGroup();
                ObjectAricSubGroup.setInitData();
                ObjectAricSubGroup.setObjectId(rs.getString(theAricSubGroup.pk_table));
                ObjectAricSubGroup.number= rs.getString(theAricSubGroup.number);               
                ObjectAricSubGroup.description= rs.getString(theAricSubGroup.description);               
                ObjectAricSubGroup.aricgroupid = rs.getString(theAricSubGroup.aricgroupid);
                
                vObject.add(ObjectAricSubGroup);
                ObjectAricSubGroup = null;
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
           // System.out.println("Size in v Aric Sub Group : " + vcData.size());
        }
        finally
        {
            if(vObject.size() == 0)
                vObject = null;
        }
        return vObject;
    }
    
    
}
