/*
 * PlanGroupMapPtTypeDB.java
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
public class PlanGroupMapPtTypeDB  extends DBPersist implements StandardDB{
    
    ConnectionInf theConnectionInf;
    PlanGroupMapPtType thePlanGroupMapPtType,ObjectPlanGroupMapPtType;
    String SQL = "";
    ResultSet rs = null;
    java.util.Vector vObject ;
     int count =0;
    boolean bresult = false;
    public PlanGroupMapPtTypeDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        thePlanGroupMapPtType = new PlanGroupMapPtType();
        
        thePlanGroupMapPtType.setInitDataFieldName();
    }
    
    /**
     *  ใช้ในการตรวจสอบ ว่า มี pttype ซ้ำกันหรือไม่ ถ้าซ้ำกันจะให้ค่าเป็น false ถ้าไม่ซ้ำจะให้ค่าเป็น true
     *  @param pttype เป็น รหัสของตาราง pttype
     *  @return boolean ถ้าเป็น false หมายถึงมีข้อมูลซ้ำ true หมายถึงไม่มีข้อมูลซ้ำ
     */
    public boolean checkSamePlanGroupPtType(String pttype,String key_id)
    {   bresult = true;
        count = 0;
        if(key_id.equalsIgnoreCase(""))
        {
        SQL = "SELECT COUNT( " + 
                thePlanGroupMapPtType.tableName + "." + thePlanGroupMapPtType.pk_table +
              ") " + 
              " FROM " + thePlanGroupMapPtType.tableName +
              " WHERE " +
              " UPPER(" + thePlanGroupMapPtType.tableName + "." + thePlanGroupMapPtType.mapptypename + ")=UPPER('" + pttype + "') " ;
        }
        else
        {
            SQL = "SELECT COUNT( " + 
                thePlanGroupMapPtType.tableName + "." + thePlanGroupMapPtType.pk_table +
              ") " + 
              " FROM " + thePlanGroupMapPtType.tableName +
              " WHERE " +
              " (UPPER(" + thePlanGroupMapPtType.tableName + "." + thePlanGroupMapPtType.mapptypename + ")=UPPER('" + pttype + "')) " +
              " AND " + thePlanGroupMapPtType.tableName + "." + thePlanGroupMapPtType.pk_table  + " <> " + key_id ;
              
        }
        System.out.println("SQL PlanGroupMapPtType : checkSame : " + SQL);
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
            ex.printStackTrace();
        }
        finally
        {
            if(count > 0)
            {
                bresult = false;
            }  
        }
        
        return bresult;
    }
    
    
        /**
     *  ใช้ในการตรวจสอบ ว่า มี pttype ซ้ำกันหรือไม่ ถ้าซ้ำกันจะให้ค่าเป็น false ถ้าไม่ซ้ำจะให้ค่าเป็น true
     *  @param pttype เป็น รหัสของตาราง pttype
     *  @return boolean ถ้าเป็น false หมายถึงมีข้อมูลซ้ำ true หมายถึงไม่มีข้อมูลซ้ำ
     */
    public boolean checkSamePlanGroupID(String groupid)
    {   bresult = false;
        count = 1;
        SQL = "SELECT COUNT( " + 
                thePlanGroupMapPtType.tableName + "." + thePlanGroupMapPtType.pk_table +
              ") " + 
              " FROM " + thePlanGroupMapPtType.tableName +
              " WHERE " + thePlanGroupMapPtType.tableName + "." + thePlanGroupMapPtType.mapptypename + "='" + groupid + "' " ;
        
        System.out.println("SQL PlanGroupMapPtType : checkSame : " + SQL);
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
            if(count == 0)
            {
                bresult = true;
            }
        }
        
        return bresult;
    }
    
    
    
    public int deleteByKeyID(String key_id) throws Exception{
        
        SQL = "DELETE " +
              " FROM " + thePlanGroupMapPtType.tableName +
              " WHERE " +
              " " + thePlanGroupMapPtType.tableName + "." + thePlanGroupMapPtType.pk_table + "='" + key_id + "' ";
        System.out.println("SQL PlanGroupMapPtType : deleteByKeyID : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public int updateData(Object object) throws Exception {
        ObjectPlanGroupMapPtType = (PlanGroupMapPtType)object;
      
        SQL = "UPDATE " +
              " " + thePlanGroupMapPtType.tableName +
              " SET " +
              " " /* + thePlanGroupMapPtType.tableName + "." */+ thePlanGroupMapPtType.mapptypename + "='" + ObjectPlanGroupMapPtType.mapptypename + "', " +
              " " /* + thePlanGroupMapPtType.tableName + "." */ + thePlanGroupMapPtType.plangroupid + "='" + ObjectPlanGroupMapPtType.plangroupid + "'" +
              " WHERE " +
              " " + thePlanGroupMapPtType.tableName + "." + thePlanGroupMapPtType.pk_table + "='" + ObjectPlanGroupMapPtType.getObjectId() + "' ";
        System.out.println("SQL PlanGroupMapPtType : updateData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    public int insertData(Object object) throws Exception {
        ObjectPlanGroupMapPtType = (PlanGroupMapPtType)object;
        ObjectPlanGroupMapPtType.generateOID(ObjectPlanGroupMapPtType.idTable);
        SQL = "INSERT INTO " +
              " " + thePlanGroupMapPtType.tableName +
              " ( " +
              " " /* + thePlanGroupMapPtType.tableName + "." */+ thePlanGroupMapPtType.pk_table + "," +  
              " " /* + thePlanGroupMapPtType.tableName + "." */+ thePlanGroupMapPtType.mapptypename + "," +
              " " /* + thePlanGroupMapPtType.tableName + "." */+ thePlanGroupMapPtType.plangroupid + "" +
              ") " +
              " VALUES ("+
              "'" + ObjectPlanGroupMapPtType.getObjectId() + "'," +  
              "'" + ObjectPlanGroupMapPtType.mapptypename + "'," +
              "'" + ObjectPlanGroupMapPtType.plangroupid + "')" ;
              
        System.out.println("SQL PlanGroupMapPtType : insertData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public Object selectByPlanGroupID(String plangroupid) throws Exception
    {
        SQL = "SELECT * FROM " +
              " " + thePlanGroupMapPtType.tableName + 
              " WHERE " + thePlanGroupMapPtType.tableName + "." +thePlanGroupMapPtType.plangroupid + " ='" + plangroupid +"'" +
              " ORDER BY " + thePlanGroupMapPtType.tableName + "." + thePlanGroupMapPtType.mapptypename   ;
        System.out.println("SQL PlanGroupMapPtType : selectByPlanGroupMapPtType : " + SQL);
        return eQuery(SQL);
    }
    
    public Object selectByKeyID(String key_id)  throws Exception{
        SQL = "SELECT * FROM " +
              " " + thePlanGroupMapPtType.tableName + 
              " WHERE " + thePlanGroupMapPtType.tableName + "." +thePlanGroupMapPtType.pk_table + " ='" + key_id +"'" +
              " ORDER BY " + thePlanGroupMapPtType.tableName + "." + thePlanGroupMapPtType.mapptypename   ;
        System.out.println("SQL PlanGroupMapPtType : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }

     /**Select ข้อมูลที่อยู่ทั้งหมดในตาราง*/
    public Object selectBySearch(String search) throws Exception {
        SQL = "SELECT * FROM " +
              " " + thePlanGroupMapPtType.tableName + 
              " WHERE " + thePlanGroupMapPtType.tableName + "." + thePlanGroupMapPtType.mapptypename + " like '%" + search + "%' " +
              " ORDER BY " + thePlanGroupMapPtType.tableName + "." + thePlanGroupMapPtType.mapptypename   ;
        System.out.println("SQL PlanGroupMapPtType : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }
    
    
    public int updateByKeyID(String key_id)  throws Exception{
        return 0;
    }
    
    private java.util.Vector eQuery(String sql) throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new java.util.Vector();
        ObjectPlanGroupMapPtType = new PlanGroupMapPtType();
        try{
            while(rs.next()) 
            {
                ObjectPlanGroupMapPtType = new PlanGroupMapPtType();
                ObjectPlanGroupMapPtType.setInitData();
                
                ObjectPlanGroupMapPtType.setObjectId(rs.getString(thePlanGroupMapPtType.pk_table));
                ObjectPlanGroupMapPtType.mapptypename= rs.getString(thePlanGroupMapPtType.mapptypename);               
                ObjectPlanGroupMapPtType.plangroupid= rs.getString(thePlanGroupMapPtType.plangroupid);               
              
                
                vObject.add(ObjectPlanGroupMapPtType);
                ObjectPlanGroupMapPtType = null;
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
