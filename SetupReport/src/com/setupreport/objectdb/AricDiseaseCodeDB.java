/*
 * AricDiseaseCodeDB.java
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
public class AricDiseaseCodeDB extends DBPersist implements StandardDB{
    
    ConnectionInf theConnectionInf;
    AricDiseaseCode theAricDiseaseCode,ObjectAricDiseaseCode;
    String SQL = "";
    ResultSet rs = null;
    java.util.Vector vObject ;
    public AricDiseaseCodeDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theAricDiseaseCode = new AricDiseaseCode();
        
        theAricDiseaseCode.setInitDataFieldName();
    }

    public int deleteByKeyID(String key_id)throws Exception {
        SQL = "DELETE FROM " +
              " " + theAricDiseaseCode.tableName +
              " WHERE " +
              " " + theAricDiseaseCode.tableName + "." + theAricDiseaseCode.pk_table + "='" + key_id + "' ";
        System.out.println("SQL AricDiseaseCode : deleteData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    public int deleteAricGroupID(String aricgroup_id) throws Exception{
        
      
        SQL = "DELETE FROM " +
              " " + theAricDiseaseCode.tableName +
              " WHERE " +
              " " + theAricDiseaseCode.tableName + "." + theAricDiseaseCode.aricgroupid + "='" + aricgroup_id + "' ";
        System.out.println("SQL AricDiseaseCode : updateData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
        
    }
    public int updateData(Object object)throws Exception {
        ObjectAricDiseaseCode = (AricDiseaseCode)object;
      
        SQL = "UPDATE " +
              " " + theAricDiseaseCode.tableName +
              " SET " +
              " " /* + theAricGroup.tableName + "." */+ theAricDiseaseCode.codebegin + "='" + ObjectAricDiseaseCode.codebegin + "', " +
              " " /* + theAricGroup.tableName + "." */ + theAricDiseaseCode.codeend + "='" + ObjectAricDiseaseCode.codeend + "'" +
              " WHERE " +
              " " + theAricDiseaseCode.tableName + "." + theAricDiseaseCode.pk_table + "='" + ObjectAricDiseaseCode.getObjectId() + "' ";
        System.out.println("SQL AricDiseaseCode : updateData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    public int insertData(Object object) throws Exception{
        ObjectAricDiseaseCode = (AricDiseaseCode)object;
        ObjectAricDiseaseCode.generateOID(ObjectAricDiseaseCode.idTable);
        SQL = "INSERT INTO " +
              " " + theAricDiseaseCode.tableName +
              " ( " +
              " " /* + theAricDiseaseCode.tableName + "." */+ theAricDiseaseCode.pk_table + "," +  
              " " /* + theAricDiseaseCode.tableName + "." */+ theAricDiseaseCode.aricgroupid + "," +
              " " /* + theAricDiseaseCode.tableName + "." */+ theAricDiseaseCode.codebegin + "," +
              " " /* + theAricDiseaseCode.tableName + "." */+ theAricDiseaseCode.codeend + "" +
              ") " +
              " VALUES ("+
              "'" + ObjectAricDiseaseCode.getObjectId() + "'," +
              "'" + ObjectAricDiseaseCode.aricgroupid + "'," +
              "'" + ObjectAricDiseaseCode.codebegin + "'," +
              "'" + ObjectAricDiseaseCode.codeend + "')" ;
              
        System.out.println("SQL AricDiseaseCode : insertData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public Object selectByAricGroupID(String aricgroupid)throws Exception
    {
        SQL = "SELECT * FROM " +
              " " + theAricDiseaseCode.tableName + 
              " WHERE " + theAricDiseaseCode.tableName + "." +theAricDiseaseCode.aricgroupid + " ='" + aricgroupid +"'" ;
              
        System.out.println("SQL AricDiseaseCode : selectByAricDiseaseCode : " + SQL);
        return eQuery(SQL);
    }
    
    public Object selectByKeyID(String key_id) throws Exception{
        SQL = "SELECT * FROM " +
              " " + theAricDiseaseCode.tableName + 
              " WHERE " + theAricDiseaseCode.tableName + "." +theAricDiseaseCode.pk_table + " ='" + key_id +"'" ;
        System.out.println("SQL AricDiseaseCode : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }

    public int updateByKeyID(String key_id) throws Exception{
        return 0;
    }
    
    private java.util.Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new java.util.Vector();
        ObjectAricDiseaseCode = new AricDiseaseCode();
        try{
            while(rs.next()) 
            {
                ObjectAricDiseaseCode = new AricDiseaseCode();
                ObjectAricDiseaseCode.setInitData();
                
                ObjectAricDiseaseCode.setObjectId(rs.getString(theAricDiseaseCode.pk_table));
                ObjectAricDiseaseCode.aricgroupid= rs.getString(theAricDiseaseCode.aricgroupid);               
                ObjectAricDiseaseCode.codebegin = rs.getString(theAricDiseaseCode.codebegin);
                ObjectAricDiseaseCode.codeend = rs.getString(theAricDiseaseCode.codeend);
                vObject.add(ObjectAricDiseaseCode);
                ObjectAricDiseaseCode = null;
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
