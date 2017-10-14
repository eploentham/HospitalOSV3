/*
 * SQLTemplate.java
 *
 * Created on 5 กันยายน 2548, 17:00 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportquery.objectdb;

import com.reportcenter.objdb.DBPersist;
import java.sql.*;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportcenter.objdb.StandardDB;
import com.reportquery.object.SQLTemplate;
import com.reportquery.object.ComboFix;
import java.util.Vector;
/**
 *
 * @author tong(Padungrat)
 */
public class SQLTemplateDB extends DBPersist implements StandardDB
{
    
    ConnectionInf theConnectionInf;
    SQLTemplate theSQLTemplate,ObjectSQLTemplate;
    String SQL = "";
    ResultSet rs = null;
    java.util.Vector vSQLTemplate ;
    int result =0;
    private ComboFix theComboFix;
    /** Creates a new instance of SQLTemplateDB */
    public SQLTemplateDB(ConnectionInf connectioninf) {
        theConnectionInf = connectioninf;
        theSQLTemplate = new SQLTemplate();
        theSQLTemplate.setInitDataFieldName();
    }
    
    /**ใช้ในการ สร้างตาราง*/
    public void createDB()
    {
        SQL = "CREATE TABLE " + theSQLTemplate.tableName + " ( " +
              theSQLTemplate.pk_table +  " varchar(255) NOT NULL," +
              theSQLTemplate.sql_template_number + " varchar(255)," + 
              theSQLTemplate.sql_template_description + " varchar(255)," +
              theSQLTemplate.sql_template_sql + " varchar(4000)," +
              theSQLTemplate.sql_template_is_query_by_date  + " varchar(255)," +
              theSQLTemplate.sql_template_active  + " varchar(255)" +
              " );";
        int i ;
        try{
           // System.out.println("Query : " + SQL);
            i = theConnectionInf.eUpdate(SQL);
            
            System.out.println("Create Table r_sql_template ..." + i); 
         }
         catch(Exception ex)
         {
             
         }
        
        SQL = "ALTER TABLE ONLY "+ theSQLTemplate.tableName +" ADD CONSTRAINT "+ theSQLTemplate.tableName +"_pkey PRIMARY KEY ("+theSQLTemplate.pk_table+");";
        try{
            
          //   System.out.println("Query : " + SQL);
            i = theConnectionInf.eUpdate(SQL);
            System.out.println("Create Primary key ..." + i); 
         }
         catch(Exception ex)
         {
             
         }
        SQL = "CREATE INDEX "+ theSQLTemplate.tableName +"_key_id_key ON "+ theSQLTemplate.tableName +" USING btree ("+theSQLTemplate.pk_table+");";
        try{
                 
               // System.out.println("Query : " + SQL);
                 i = theConnectionInf.eUpdate(SQL);
                 System.out.println("Create Index ..." + i);
         }
         catch(Exception ex)
         {
             
         }
        System.out.println("Finish Create Table r_sql_template ...");
        
        
    }
    
    /**ใช้ในการตรวจสอบว่ามี ฐานข้อมูลนี้อยู่หรือไม่
     *  @return boolean true มีอยู่ false ไม่มีอยู่
     */
    public boolean isExists()
    {   
        boolean result = true;
        SQL = "Select " + theSQLTemplate.sql_template_is_query_by_date
              + " From " + theSQLTemplate.tableName
              + " WHERE " +  theSQLTemplate.pk_table + "='1234567'" ;
        rs = null;
        try
        {
           rs = theConnectionInf.eQuery(SQL);
        }
        catch(Exception ex)
        {
            System.out.println("Error");
            
        }
        finally
        {
            if(rs==null)
            {
                result = false;
            }
                
        }
        return result;
    }
    
    
     /**
     *   ใช้ในการลบข้อมูลออกจากตาราง b_sql_template ตามKeyID ของตารางนั้น
      *@param key_id เป็น String ของ keyid ที่ต้องการจะลบ
      *@return int เป็น int ผลของการลบ
     */
    public int deleteByKeyID(String key_id) throws Exception
    {
        SQL="delete from " + theSQLTemplate.tableName
        + " where " + theSQLTemplate.pk_table + "='" + key_id +"'";
        System.out.println(SQL);
        return theConnectionInf.eUpdate(SQL);
    }

    public int insertData(Object object)  throws Exception{
        result = -1;
        if(object instanceof SQLTemplate)
        {
            ObjectSQLTemplate = (SQLTemplate)object;
            ObjectSQLTemplate.generateOID(ObjectSQLTemplate.idTable);
            SQL = "INSERT INTO " + theSQLTemplate.tableName + "(" +
                   " " + theSQLTemplate.pk_table +"," + 
                   " " + theSQLTemplate.sql_template_active +"," +
                   " " + theSQLTemplate.sql_template_description +"," +
                   " " + theSQLTemplate.sql_template_is_query_by_date +"," +
                   " " + theSQLTemplate.sql_template_number +"," +
                   " " + theSQLTemplate.sql_template_sql +") " +
                   " VALUES(" ;
                    SQL =  SQL + "?, " ;
                    SQL =  SQL + "?, " ;
                    SQL =  SQL + "?, " ;
                    SQL =  SQL + "?, " ;
                    SQL =  SQL + "?, " ;
                    SQL =  SQL + "? " ;
                    SQL =  SQL + ") " ;

            try
            {   System.out.println("SQL : " + SQL);
                System.out.println("Object ID : " + ObjectSQLTemplate.getObjectId());
                
                PreparedStatement ps = theConnectionInf.ePQuery(SQL);
                ps.setString(	1	,ObjectSQLTemplate.getObjectId());
                ps.setString(	2	,ObjectSQLTemplate.sql_template_active);
                ps.setString(	3	,ObjectSQLTemplate.sql_template_description);
                ps.setString(	4	,ObjectSQLTemplate.sql_template_is_query_by_date);
                ps.setString(	5	,ObjectSQLTemplate.sql_template_number);
                ps.setString(	6	,ObjectSQLTemplate.sql_template_sql);
                result = ps.executeUpdate();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                
            }
        }
        return result;
    }

    /**
     * ใช้ในการตรวจสอบรหัส มีการซ้ำกันหรือไม่
     * จะส่งเป็นตัวเลขไปให้ ถ้า ซ้ำกันจะมีค่ามากกว่า 0
     *@param code เป็น String ที่ใช้ในการค้นหา code
     *@int เป็นจำนวนการซ้ำกัน
     */
    public int checkCodeInDB(String code,String key_id) throws Exception
    {
        result = -1;
        SQL = "SELECT COUNT(" + theSQLTemplate.sql_template_number + ") FROM " + theSQLTemplate.tableName +"" +
              " WHERE UPPER(" +theSQLTemplate.sql_template_number + ") = UPPER('"+code + "')";
        if(key_id!=null)
        {
            SQL = SQL + " AND " + theSQLTemplate.pk_table + "<> '" + key_id + "' ";
        }
        
        rs = theConnectionInf.eQuery(SQL);
        
        try{
            while(rs.next()) 
            {
                result = rs.getInt(1);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return result;
    }
    /**
     * ใช้ในการตรวจสอบรหัส มีการซ้ำกันหรือไม่
     * จะส่งเป็นตัวเลขไปให้ ถ้า ซ้ำกันจะมีค่ามากกว่า 0
     *@param name เป็น String ที่ใช้ในการค้นหา description
     *@int เป็นจำนวนการซ้ำกัน
     */
    public int checkDescriptionInDB(String name,String key_id) throws Exception
    {
        result = -1;
        SQL = "SELECT COUNT(" + theSQLTemplate.sql_template_description + ") FROM " + theSQLTemplate.tableName +"" +
              " WHERE UPPER(" +theSQLTemplate.sql_template_description + ") like UPPER('"+name + "')" ;
        
        if(key_id!=null)
        {
            SQL = SQL + " AND " + theSQLTemplate.pk_table + "<> '" + key_id + "' ";
        }
        rs = theConnectionInf.eQuery(SQL);
        
        try{
            while(rs.next()) 
            {
                result = rs.getInt(1);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public Object selectByKeyID(String key_id) {
        SQL = "SELECT * FROM " + theSQLTemplate.tableName +"" +
                " WHERE " + theSQLTemplate.pk_table + "='"+key_id + "'";
        try{
         //   System.out.println(SQL);
            vSQLTemplate = eQuery(SQL);
        }
        catch(Exception ex)
        {
            
        }
        if(vSQLTemplate.size() == 0)
            return null;
        else
            return (SQLTemplate)vSQLTemplate.get(0);
    }
    
    public Vector selectByName(String key_id) throws Exception 
    {
        SQL = "SELECT * FROM " + theSQLTemplate.tableName +"" +
                " WHERE " + theSQLTemplate.sql_template_description + " ilike '%"+key_id + "%'" +
                " AND "+ theSQLTemplate.sql_template_active + "='1'" +
                " order by " + theSQLTemplate.sql_template_description ;
        return eQuery(SQL);
    }
    /**ใช้ในการ แสดงข้อมูล แสดงบน Table
     *  
     */
    public Object selectAllShowTable() throws Exception
    {
        SQL = "SELECT * FROM " + theSQLTemplate.tableName+"" +
                " WHERE " + theSQLTemplate.sql_template_active+ " = '" + "1" + "'" ;
        vSQLTemplate = veQuery(SQL);
        if(vSQLTemplate.size()==0)
            return null;
        else
            return vSQLTemplate;
    }
    
    /**ใช้ในการ แสดงข้อมูล แสดงบน Table
     *  
     */
    public java.util.Vector selectAllShowInComboBox() throws Exception
    {
        SQL = "SELECT * FROM " + theSQLTemplate.tableName+"" +
                " WHERE " + theSQLTemplate.sql_template_active+ " = '" + "1" + "' " +
                " order by " + theSQLTemplate.sql_template_description;
        vSQLTemplate = eQuery(SQL);
        if(vSQLTemplate.size()==0)
            return null;
        else
            return vSQLTemplate;
    }
    
    public java.util.Vector selectBySearch(String search,String active) throws Exception
    {
        SQL = "Select * from " + theSQLTemplate.tableName + 
              " where "  ;
        if(!search.equalsIgnoreCase(""))
        {  SQL = SQL + "( UPPER(" + theSQLTemplate.sql_template_number + ") like UPPER('%" + search + "%')"
           + " or UPPER(" + theSQLTemplate.sql_template_description + ") like UPPER('%" + search + "%')"
           + " ) and " ;
        }
        SQL = SQL + theSQLTemplate.sql_template_active + "='" + active + "'" +
                " order by " + theSQLTemplate.sql_template_description ;
        System.out.println("SQL : " + SQL);
        vSQLTemplate = eQuery(SQL);
        if(vSQLTemplate.size()==0)
            return null;
        else
            return vSQLTemplate;
    }
    
    
    
    
    public java.util.Vector veQuery(String sql) throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vSQLTemplate = new java.util.Vector();
        ObjectSQLTemplate = new SQLTemplate();
        String[] pre;
        try{
            while(rs.next()) 
            {
                ObjectSQLTemplate = new SQLTemplate();
                ObjectSQLTemplate.setInitData();
                
                ObjectSQLTemplate.setObjectId(rs.getString(theSQLTemplate.pk_table));
                ObjectSQLTemplate.sql_template_description  = rs.getString(theSQLTemplate.sql_template_description);
                ObjectSQLTemplate.sql_template_number  = rs.getString(theSQLTemplate.sql_template_number);
                
                vSQLTemplate.add(ObjectSQLTemplate);
                ObjectSQLTemplate = null;
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
        }
        return vSQLTemplate;
    }
    public int update(Object object) 
    {
        result = -1;
        try
        {
            if(object instanceof SQLTemplate)
            {
                ObjectSQLTemplate = (SQLTemplate)object;

                SQL = "update " + theSQLTemplate.tableName + " set ";
                SQL =  SQL + theSQLTemplate.sql_template_description + "	=	?, " ;
                SQL =  SQL + theSQLTemplate.sql_template_number	+ " =	?, " ;
                SQL =  SQL + theSQLTemplate.sql_template_sql	+ "=	?, " ;
                SQL =  SQL + theSQLTemplate.sql_template_active	+ "=	?, " ;
                SQL =  SQL + theSQLTemplate.sql_template_is_query_by_date	+ "=	? " ;
                SQL =  SQL + " WHERE " + theSQLTemplate.pk_table + "= ? ";

                PreparedStatement ps = theConnectionInf.ePQuery(SQL);
                ps.setString(	1	,ObjectSQLTemplate.sql_template_description);
                ps.setString(	2	,ObjectSQLTemplate.sql_template_number);
                ps.setString(	3	,ObjectSQLTemplate.sql_template_sql);
                ps.setString(	4	,ObjectSQLTemplate.sql_template_active);
                ps.setString(	5	,ObjectSQLTemplate.sql_template_is_query_by_date);
                ps.setString(	6	,ObjectSQLTemplate.getObjectId());


            result = ps.executeUpdate();
            
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
        
    }
        
        
        
    public int updateByKeyID(String key_id) {
        
        
        
        
        return 0;
    }
   
    
    public java.util.Vector eQuery(String sql) throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vSQLTemplate = new java.util.Vector();
        ObjectSQLTemplate = new SQLTemplate();
        String[] pre;
        try{
            while(rs.next()) 
            {
                ObjectSQLTemplate = new SQLTemplate();
                ObjectSQLTemplate.setInitData();
                
                
                
                
                ObjectSQLTemplate.setObjectId(rs.getString(theSQLTemplate.pk_table));
                ObjectSQLTemplate.sql_template_active  = rs.getString(theSQLTemplate.sql_template_active);
                ObjectSQLTemplate.sql_template_description  = rs.getString(theSQLTemplate.sql_template_description);
                ObjectSQLTemplate.sql_template_is_query_by_date  = rs.getString(theSQLTemplate.sql_template_is_query_by_date);
                ObjectSQLTemplate.sql_template_number  = rs.getString(theSQLTemplate.sql_template_number);
                ObjectSQLTemplate.sql_template_sql  = rs.getString(theSQLTemplate.sql_template_sql);
                
                ObjectSQLTemplate.fixObject.code = ObjectSQLTemplate.getObjectId();
                ObjectSQLTemplate.fixObject.name = ObjectSQLTemplate.sql_template_description;
              
                
                
                vSQLTemplate.add(ObjectSQLTemplate);
                ObjectSQLTemplate = null;
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
        }
        return vSQLTemplate;
    } 
}
