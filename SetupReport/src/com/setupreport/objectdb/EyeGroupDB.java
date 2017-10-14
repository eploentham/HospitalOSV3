/*
 * EyeGroupDB.java
 *
 * Created on 26 ตุลาคม 2548, 14:58 น.
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
 * @author americus
 */
public class EyeGroupDB extends DBPersist implements StandardDB
{
    ConnectionInf theConnectionInf;
    EyeGroup theEyeGroup,ObjectEyeGroup;
    String SQL = "";
    ResultSet rs = null;
    java.util.Vector vObject ;
    int count =0;
    boolean bresult = false;
    /** Creates a new instance of EyeGroupDB */
    public EyeGroupDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theEyeGroup = new EyeGroup();
        
        theEyeGroup.setInitDataFieldName();
    }
    
    public int deleteByKeyID(String key_id)throws Exception
    {
        SQL = "DELETE " +
                " FROM " + theEyeGroup.tableName +
                " WHERE " +
                " " + theEyeGroup.tableName + "." + theEyeGroup.pk_table + "='" + key_id + "' ";
        System.out.println("SQL EyeGroup : deleteByKeyID : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    public int insertData(Object object)throws Exception
    {
        ObjectEyeGroup = (EyeGroup)object;
        ObjectEyeGroup.generateOID(ObjectEyeGroup.idTable);
        SQL = "INSERT INTO " +
                " " + theEyeGroup.tableName +
                " ( " +
                " " /* + theEyeGroup.tableName + "." */+ theEyeGroup.pk_table + "," +
                " " /* + theEyeGroup.tableName + "." */+ theEyeGroup.number + "," +
                " " /* + theEyeGroup.tableName + "." */+ theEyeGroup.desc_th + "," +
                " " /* + theEyeGroup.tableName + "." */+ theEyeGroup.desc_eng + "," +
                " " /* + theEyeGroup.tableName + "." */+ theEyeGroup.active + " " +
                ") " +
                " VALUES ("+
                "'" + ObjectEyeGroup.getObjectId() + "'," +
                "'" + ObjectEyeGroup.number + "'," +
                "'" + ObjectEyeGroup.desc_th + "'," +
                "'" + ObjectEyeGroup.desc_eng + "'," +
                "'" + ObjectEyeGroup.active +"')" ;
        
        System.out.println("SQL AricGroup : insertData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    public int updateData(Object object)throws Exception
    {
        ObjectEyeGroup = (EyeGroup)object;
        
        SQL = "UPDATE " +
                " " + theEyeGroup.tableName +
                " SET " +
                " " /* + theEyeGroup.tableName + "." */+ theEyeGroup.number + "='" + ObjectEyeGroup.number + "', " +
                " " /* + theEyeGroup.tableName + "." */ + theEyeGroup.desc_th + "='" + ObjectEyeGroup.desc_th + "'," +
                " " /* + theEyeGroup.tableName + "." */ + theEyeGroup.desc_eng + "='" + ObjectEyeGroup.desc_eng + "'," +
                " " /* + theEyeGroup.tableName + "." */ + theEyeGroup.active + "='" + ObjectEyeGroup.active + "'" +
                " WHERE " +
                " " + theEyeGroup.tableName + "." + theEyeGroup.pk_table + "='" + ObjectEyeGroup.getObjectId() + "' ";
        System.out.println("SQL theEyeGroup : updateData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    /**
     *  ใช้ในการตรวจสอบ หาว่า Code ซ้ำกันหรือไม่ ถ้ามีซ้ำกัน จะ ให้ค่าเป็น false ถ้าไม่ซ้ำจะให้ค่าเป็น true
     *  @return เป็น boolean ถ้าเป็น true จะไม่ซ้ำ ถ้าเป็น false จะซ้ำ
     */
    public boolean checkSameEyeGroupCode(String eyegroupcode,String key_id)
    {   count =0;
        bresult = true;
        
        if(key_id.equalsIgnoreCase(""))
        {
            SQL = "SELECT COUNT( " +
                theEyeGroup.tableName + "." + theEyeGroup.pk_table  +
                ")"   +
                " FROM " + theEyeGroup.tableName +
                " WHERE " +
                " UPPER(" + theEyeGroup.tableName + "." + theEyeGroup.number + ")=UPPER('" + eyegroupcode + "') ";
        }
        else
        {
            SQL = "SELECT COUNT( " +
                theEyeGroup.tableName + "." + theEyeGroup.pk_table  +
                ")"   +
                " FROM " + theEyeGroup.tableName +
                " WHERE " +
                " (UPPER(" + theEyeGroup.tableName + "." + theEyeGroup.number + ")=UPPER('" + eyegroupcode + "')) " +
                " AND " + theEyeGroup.tableName + "." + theEyeGroup.pk_table  + " <> " + key_id ;
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
     *  ใช้ในการตรวจสอบ หาว่า Description ซ้ำกันหรือไม่ ถ้ามีซ้ำกัน จะ ให้ค่าเป็น false ถ้าไม่ซ้ำจะให้ค่าเป็น true
     *  @return เป็น boolean ถ้าเป็น true จะไม่ซ้ำ ถ้าเป็น false จะซ้ำ
     */
    public boolean checkSameEyeGroupThaiDescription(String thaidescription,String key_id)
    {   count =0;
        bresult = true;
        
        if(key_id.equalsIgnoreCase(""))
        {
            SQL = "SELECT COUNT( " +
                theEyeGroup.tableName + "." + theEyeGroup.pk_table  +
                ")"   +
                " FROM " + theEyeGroup.tableName +
                " WHERE " +
                " UPPER(" + theEyeGroup.tableName + "." + theEyeGroup.desc_th + ")=UPPER('" + thaidescription + "') ";
        }
        else
        {
            SQL = "SELECT COUNT( " +
                theEyeGroup.tableName + "." + theEyeGroup.pk_table  +
                ")"   +
                " FROM " + theEyeGroup.tableName +
                " WHERE " +
                " (UPPER(" + theEyeGroup.tableName + "." + theEyeGroup.desc_th + ")=UPPER('" + thaidescription + "')) " +
                " AND " + theEyeGroup.tableName + "." + theEyeGroup.pk_table  + " <> " + key_id ;
        }
        
        System.out.println("SQL EyeGroup : thaidescription : " + SQL);
        
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
     *  ใช้ในการตรวจสอบ หาว่า Description ซ้ำกันหรือไม่ ถ้ามีซ้ำกัน จะ ให้ค่าเป็น false ถ้าไม่ซ้ำจะให้ค่าเป็น true
     *  @return เป็น boolean ถ้าเป็น true จะไม่ซ้ำ ถ้าเป็น false จะซ้ำ
     */
    public boolean checkSameEyeGroupEngDescription(String engdescription,String key_id)
    {   count =0;
        bresult = true;
        
        if(key_id.equalsIgnoreCase(""))
        {
            SQL = "SELECT COUNT( " +
                theEyeGroup.tableName + "." + theEyeGroup.pk_table  +
                ")"   +
                " FROM " + theEyeGroup.tableName +
                " WHERE " +
                " UPPER(" + theEyeGroup.tableName + "." + theEyeGroup.desc_eng + ")=UPPER('" + engdescription + "') ";
        }
        else
        {
            SQL = "SELECT COUNT( " +
                theEyeGroup.tableName + "." + theEyeGroup.pk_table  +
                ")"   +
                " FROM " + theEyeGroup.tableName +
                " WHERE " +
                " (UPPER(" + theEyeGroup.tableName + "." + theEyeGroup.desc_eng + ")=UPPER('" + engdescription + "')) " +
                " AND " + theEyeGroup.tableName + "." + theEyeGroup.pk_table  + " <> " + key_id ;
        }
        
        System.out.println("SQL EyeGroup : thaidescription : " + SQL);
        
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
    
    /**Select ข้อมูลที่อยู่ทั้งหมดในตาราง*/
    public Object selectBySearch(String search,String active)throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theEyeGroup.tableName +
                " WHERE (" + theEyeGroup.tableName + "." + theEyeGroup.number + " like '%" + search + "%' " +
                " OR "  + theEyeGroup.tableName + "." + theEyeGroup.desc_th + " like '%" + search + "%' " +
                " OR "  + theEyeGroup.tableName + "." + theEyeGroup.desc_eng + " like '%" + search + "%') " ;
                if(!active.equals("1"))
                {
                    SQL = SQL + " AND " + theEyeGroup.tableName + "." + theEyeGroup.active +" <> '1'";
                    
                }
                else
                {
                    SQL = SQL + " AND " + theEyeGroup.tableName + "." + theEyeGroup.active +" = '1'";
                }
        SQL  = SQL + " ORDER BY to_number(" + theEyeGroup.tableName + "." + theEyeGroup.number +",'99')" ;
        System.out.println("SQL EyeGroup : selectBySearch : " + SQL);        
        return eQuery(SQL);
    }
    
    /**Select ข้อมูลที่อยู่ทั้งหมดในตาราง*/
    public Object selectByAll()throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theEyeGroup.tableName +
                " ORDER BY to_number(" + theEyeGroup.tableName + "." + theEyeGroup.number +",'99')" ;
        System.out.println("SQL EyeGroup : selectAll : " + SQL);
        return eQuery(SQL);
    }
    
    public Object selectByKeyID(String key_id)throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theEyeGroup.tableName +
                " WHERE " + theEyeGroup.tableName + "." + theEyeGroup.pk_table + " = '" + key_id + "' "+
                " ORDER BY to_number(" + theEyeGroup.tableName + "." + theEyeGroup.number +",'99')" ;
        System.out.println("SQL EyeGroup : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }
    
    public int updateByKeyID(String key_id)throws Exception
    {
        return 0;
    }
    
    private java.util.Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new java.util.Vector();
        ObjectEyeGroup = new EyeGroup();
        try
        {
            while(rs.next())
            {
                ObjectEyeGroup = new EyeGroup();
                ObjectEyeGroup.setInitData();
                ObjectEyeGroup.setObjectId(rs.getString(theEyeGroup.pk_table));
                ObjectEyeGroup.number= rs.getString(theEyeGroup.number);
                ObjectEyeGroup.desc_th = rs.getString(theEyeGroup.desc_th);
                ObjectEyeGroup.desc_eng = rs.getString(theEyeGroup.desc_eng);
                ObjectEyeGroup.active = rs.getString(theEyeGroup.active);
                
                vObject.add(ObjectEyeGroup);
                ObjectEyeGroup = null;
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
