/*
 * EyeDiseaseCodeDB.java
 *
 * Created on 26 ตุลาคม 2548, 14:57 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.objectdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreport.object.*;
import com.setupreport.utility.Language;
import java.util.Vector;

import com.hosv3.utility.DBPersist;
import com.hosv3.utility.StandardDB;
import java.sql.*;
/**
 *
 * @author americus
 */
public class EyeDiseaseCodeDB extends DBPersist implements StandardDB 
{
    ConnectionInf theConnectionInf;
    EyeDiseaseCode theEyeDiseaseCode,ObjectEyeDiseaseCode;
    String SQL = "";
    ResultSet rs = null;
    java.util.Vector vObject ;
    int count =0;
    boolean bresult = false;
    /** Creates a new instance of EyeDiseaseCodeDB */
    public EyeDiseaseCodeDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theEyeDiseaseCode = new EyeDiseaseCode();
        
        theEyeDiseaseCode.setInitDataFieldName();
    }   
    
    public boolean checkSameBeginICD10(String begin,String key_id)
    {   count =0;
        bresult = true;
        
        if(key_id.equalsIgnoreCase(""))
        {
            SQL = "SELECT COUNT( " +
                theEyeDiseaseCode.tableName + "." + theEyeDiseaseCode.pk_table  +
                ")"   +
                " FROM " + theEyeDiseaseCode.tableName +
                " WHERE " +
                " UPPER(" + theEyeDiseaseCode.tableName + "." + theEyeDiseaseCode.code_begin + ")=UPPER('" + begin + "') ";
        }
        else
        {
            SQL = "SELECT COUNT( " +
                theEyeDiseaseCode.tableName + "." + theEyeDiseaseCode.pk_table  +
                ")"   +
                " FROM " + theEyeDiseaseCode.tableName +
                " WHERE " +
                " (UPPER(" + theEyeDiseaseCode.tableName + "." + theEyeDiseaseCode.code_begin + ")=UPPER('" + begin + "')) " +
                " AND " + theEyeDiseaseCode.tableName + "." + theEyeDiseaseCode.pk_table  + " <> " + key_id ;
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
    
    public boolean checkSameEndICD10(String end,String key_id)
    {   count =0;
        bresult = true;
        
        if(key_id.equalsIgnoreCase(""))
        {
            SQL = "SELECT COUNT( " +
                    theEyeDiseaseCode.tableName + "." + theEyeDiseaseCode.pk_table  +
                    ")"   +
                    " FROM " + theEyeDiseaseCode.tableName +
                    " WHERE " +
                    " UPPER(" + theEyeDiseaseCode.tableName + "." + theEyeDiseaseCode.code_end + ")=UPPER('" + end + "') ";
        }
        else
        {
            SQL = "SELECT COUNT( " +
                    theEyeDiseaseCode.tableName + "." + theEyeDiseaseCode.pk_table  +
                    ")"   +
                    " FROM " + theEyeDiseaseCode.tableName +
                    " WHERE " +
                    " (UPPER(" + theEyeDiseaseCode.tableName + "." + theEyeDiseaseCode.code_end + ")=UPPER('" + end + "')) " +
                    " AND " + theEyeDiseaseCode.tableName + "." + theEyeDiseaseCode.pk_table  + " <> " + key_id ;
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
        
    public int deleteByKeyID(String key_id)throws Exception
    {
        SQL = "DELETE FROM " +
                " " + theEyeDiseaseCode.tableName +
                " WHERE " +
                " " + theEyeDiseaseCode.tableName + "." + theEyeDiseaseCode.pk_table + "='" + key_id + "' ";
        System.out.println("SQL EyeDiseaseCode : updateData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);        
    }
    
    public int deleteEyeDiseaseByGroupID(String eyegroup_id)throws Exception
    {       
        SQL = "DELETE FROM " +
                " " + theEyeDiseaseCode.tableName +
                " WHERE " +
                " " + theEyeDiseaseCode.tableName + "." + theEyeDiseaseCode.group_id + "='" + eyegroup_id + "' ";
        System.out.println("SQL EyeDiseaseCode : updateData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);        
    }

    public int insertData(Object object)throws Exception
    {
        ObjectEyeDiseaseCode = (EyeDiseaseCode)object;
        ObjectEyeDiseaseCode.generateOID(ObjectEyeDiseaseCode.idTable);
        SQL = "INSERT INTO " +
                " " + theEyeDiseaseCode.tableName +
                " ( " +
                " " /* + theEyeDiseaseCode.tableName + "." */+ theEyeDiseaseCode.pk_table + "," +
                " " /* + theEyeDiseaseCode.tableName + "." */+ theEyeDiseaseCode.group_id + "," +
                " " /* + theEyeDiseaseCode.tableName + "." */+ theEyeDiseaseCode.code_begin + "," +
                " " /* + theEyeDiseaseCode.tableName + "." */+ theEyeDiseaseCode.code_end + " " +
                ") " +
                " VALUES ("+
                "'" + ObjectEyeDiseaseCode.getObjectId() + "'," +
                "'" + ObjectEyeDiseaseCode.group_id + "'," +
                "'" + ObjectEyeDiseaseCode.code_begin + "'," +
                "'" + ObjectEyeDiseaseCode.code_end +"')" ;
        
        System.out.println("SQL EyeDiseaseCode : insertData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public int updateData(Object object)throws Exception
    {
        ObjectEyeDiseaseCode = (EyeDiseaseCode)object;
        
        SQL = "UPDATE " +
                " " + theEyeDiseaseCode.tableName +
                " SET " +
                " " /* + theEyeGroup.tableName + "." */+ theEyeDiseaseCode.group_id + "='" + ObjectEyeDiseaseCode.group_id + "', " +
                " " /* + theEyeGroup.tableName + "." */ + theEyeDiseaseCode.code_begin + "='" + ObjectEyeDiseaseCode.code_begin + "'," +
                " " /* + theEyeGroup.tableName + "." */ + theEyeDiseaseCode.code_end + "='" + ObjectEyeDiseaseCode.code_end + "'" +
                " WHERE " +
                " " + theEyeDiseaseCode.tableName + "." + theEyeDiseaseCode.pk_table + "='" + ObjectEyeDiseaseCode.getObjectId() + "' ";
        System.out.println("SQL EyeDiseaseCode : updateData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    public Object selectByKeyID(String key_id)throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theEyeDiseaseCode.tableName +
                " WHERE " + theEyeDiseaseCode.tableName + "." + theEyeDiseaseCode.pk_table + " = '" + key_id + "' "+
                " ORDER BY " + theEyeDiseaseCode.tableName + "." + theEyeDiseaseCode.group_id ;
        System.out.println("SQL EyeDiseaseCode : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }

    /**Select ข้อมูลที่อยู่ทั้งหมดในตาราง*/
   /* public Object selectBySearch(String search)
    {
        SQL = "SELECT * FROM " +
                " " + theEyeDiseaseCode.tableName +
                " WHERE " + theEyeDiseaseCode.tableName + "." + theEyeDiseaseCode.code_begin + " like '%" + search + "%' " +
                //" OR "  + theEyeGroup.tableName + "." + theEyeGroup.desc_th + " like '%" + search + "%' " +
                //" OR "  + theEyeGroup.tableName + "." + theEyeGroup.desc_eng + " like '%" + search + "%' " +
                " ORDER BY " + theEyeDiseaseCode.tableName + "." + theEyeDiseaseCode.code_begin ;
        System.out.println("SQL EyeDiseaseCode : selectBySearch : " + SQL);
        return eQuery(SQL);
    }
    **/
    
     /**Select ข้อมูลที่อยู่ทั้งหมดในตาราง*/
    public Object selectByEyeGroupID(String search)throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theEyeDiseaseCode.tableName +
                " WHERE " + theEyeDiseaseCode.tableName + "." + theEyeDiseaseCode.group_id + " ='" + search + "' " +
                //" OR "  + theEyeGroup.tableName + "." + theEyeGroup.desc_th + " like '%" + search + "%' " +
                //" OR "  + theEyeGroup.tableName + "." + theEyeGroup.desc_eng + " like '%" + search + "%' " +
                " ORDER BY " + theEyeDiseaseCode.tableName + "." + theEyeDiseaseCode.code_begin ;
        System.out.println("SQL EyeDiseaseCode : selectBySearch : " + SQL);
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
        ObjectEyeDiseaseCode = new EyeDiseaseCode();
        try
        {
            while(rs.next())
            {
                ObjectEyeDiseaseCode = new EyeDiseaseCode();
                ObjectEyeDiseaseCode.setInitData();
                ObjectEyeDiseaseCode.setObjectId(rs.getString(theEyeDiseaseCode.pk_table));
                ObjectEyeDiseaseCode.group_id = rs.getString(theEyeDiseaseCode.group_id);
                ObjectEyeDiseaseCode.code_begin = rs.getString(theEyeDiseaseCode.code_begin);
                ObjectEyeDiseaseCode.code_end = rs.getString(theEyeDiseaseCode.code_end);
                
                vObject.add(ObjectEyeDiseaseCode);
                ObjectEyeDiseaseCode = null;
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
