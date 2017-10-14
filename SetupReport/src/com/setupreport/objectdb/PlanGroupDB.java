/*
 * PlanGroupDB.java
 *
 * Created on 27 ตุลาคม 2548, 19:37 น.
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
public class PlanGroupDB  extends DBPersist implements StandardDB{
    
    ConnectionInf theConnectionInf;
    PlanGroup thePlanGroup,ObjectPlanGroup;
    String SQL = "";
    ResultSet rs = null;
    java.util.Vector vObject ;
    int count =0;
    boolean bresult = false;
    public PlanGroupDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        thePlanGroup = new PlanGroup();
        
        thePlanGroup.setInitDataFieldName();
    }

/**
     *  ใช้ในการตรวจสอบ หาว่า Description ซ้ำกันหรือไม่ ถ้ามีซ้ำกัน จะ ให้ค่าเป็น false ถ้าไม่ซ้ำจะให้ค่าเป็น true
     *  @return เป็น boolean ถ้าเป็น true จะไม่ซ้ำ ถ้าเป็น false จะซ้ำ
     */
    public boolean checkSamePlanGroupDescription(String plangroupdescription)
    {
        count =0;
        bresult = true;
        SQL = "SELECT COUNT( " +
                    thePlanGroup.tableName + "." + thePlanGroup.pk_table  +
                 ")"   +
              " FROM " + thePlanGroup.tableName +
              " WHERE " +
              " UPPER(" + thePlanGroup.tableName + "." + thePlanGroup.description + ")=UPPER('" + plangroupdescription + "') ";
        System.out.println("SQL PlanGroup : checkSameCode : " + SQL);
        
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
    public boolean checkSamePlanGroupCode(String plangroupcode)
    {   count =0;
        bresult = true;
        SQL = "SELECT COUNT( " +
                    thePlanGroup.tableName + "." + thePlanGroup.pk_table  +
                 ")"   +
              " FROM " + thePlanGroup.tableName +
              " WHERE " +
              " UPPER(" + thePlanGroup.tableName + "." + thePlanGroup.number + ")=UPPER('" + plangroupcode + "') ";
        System.out.println("SQL PlanGroup : checkSameCode : " + SQL);
        
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
    
    public int deleteByKeyID(String key_id)  throws Exception{
        
        SQL = "DELETE " +
              " FROM " + thePlanGroup.tableName +
              " WHERE " +
              " " + thePlanGroup.tableName + "." + thePlanGroup.pk_table + "='" + key_id + "' ";
        System.out.println("SQL PlanGroup : deleteByKeyID : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public int updateData(Object object)  throws Exception{
        ObjectPlanGroup = (PlanGroup)object;
      
        SQL = "UPDATE " +
              " " + thePlanGroup.tableName +
              " SET " +
              " " /* + thePlanGroup.tableName + "." */+ thePlanGroup.number + "='" + ObjectPlanGroup.number + "', " +
              " " /* + thePlanGroup.tableName + "." */ + thePlanGroup.description + "='" + ObjectPlanGroup.description + "'," +
              " " /* + thePlanGroup.tableName + "." */ + thePlanGroup.active + "='" + ObjectPlanGroup.active + "'" +
              " WHERE " +
              " " + thePlanGroup.tableName + "." + thePlanGroup.pk_table + "='" + ObjectPlanGroup.getObjectId() + "' ";
        System.out.println("SQL PlanGroup : updateData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    public int insertData(Object object)  throws Exception{
        ObjectPlanGroup = (PlanGroup)object;
        ObjectPlanGroup.generateOID(ObjectPlanGroup.idTable);
        SQL = "INSERT INTO " +
              " " + thePlanGroup.tableName +
              " ( " +
              " " /* + thePlanGroup.tableName + "." */+ thePlanGroup.pk_table + "," +  
              " " /* + thePlanGroup.tableName + "." */+ thePlanGroup.number + "," +
              " " /* + thePlanGroup.tableName + "." */+ thePlanGroup.description + "," +
              " " /* + thePlanGroup.tableName + "." */+ thePlanGroup.active + "" +
              ") " +
              " VALUES ("+
              "'" + ObjectPlanGroup.getObjectId() + "'," +  
              "'" + ObjectPlanGroup.number + "'," +
              "'" + ObjectPlanGroup.description + "'," +
              "'" + ObjectPlanGroup.active + "'" +
              ")" ;
              
        System.out.println("SQL PlanGroup : insertData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    /**Select ข้อมูลที่อยู่ทั้งหมดในตาราง*/
    public Object selectBySearch(String search,String active)  throws Exception{
        SQL = "SELECT * FROM " +
              " " + thePlanGroup.tableName + 
              " WHERE (" + thePlanGroup.tableName + "." + thePlanGroup.number + " like '%" + search + "%' " +
              " OR "  + thePlanGroup.tableName + "." + thePlanGroup.description + " like '%" + search + "%') " +
              " AND " + thePlanGroup.tableName + "." + thePlanGroup.active + " = '" + active + "' " +
              " ORDER BY " + thePlanGroup.tableName + "." + thePlanGroup.number   ;
        System.out.println("SQL PlanGroup : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }
    
    /**Select ข้อมูลที่อยู่ทั้งหมดในตาราง*/
    public Object selectByAll()  throws Exception{
        SQL = "SELECT * FROM " +
              " " + thePlanGroup.tableName + 
              " WHERE " + thePlanGroup.tableName + "." + thePlanGroup.active + " ='1'" +
              " ORDER BY " + thePlanGroup.tableName + "." + thePlanGroup.number   ;
        System.out.println("SQL PlanGroup : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }
    /**Select ข้อมูลที่อยู่ในตาราง ตาม key หลัก ของตาราง*/
    public Object selectByKeyID(String key_id)  throws Exception{
        SQL = "SELECT * FROM " +
              " " + thePlanGroup.tableName + 
              " WHERE " + thePlanGroup.tableName + "." + thePlanGroup.pk_table + " = '" + key_id + "' "+
              " ORDER BY " + thePlanGroup.tableName + "." + thePlanGroup.number   ;
        System.out.println("SQL PlanGroup : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }

    public int updateByKeyID(String key_id)  throws Exception{
        return 0;
    }
    
    private java.util.Vector eQuery(String sql) throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new java.util.Vector();
        ObjectPlanGroup = new PlanGroup();
        try{
            while(rs.next()) 
            {
                ObjectPlanGroup = new PlanGroup();
                ObjectPlanGroup.setInitData();
                ObjectPlanGroup.setObjectId(rs.getString(thePlanGroup.pk_table));
                ObjectPlanGroup.number= rs.getString(thePlanGroup.number);               
                
                ObjectPlanGroup.description= rs.getString(thePlanGroup.description);               
                ObjectPlanGroup.active = rs.getString(thePlanGroup.active); 
                        
                vObject.add(ObjectPlanGroup);
                ObjectPlanGroup = null;
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
