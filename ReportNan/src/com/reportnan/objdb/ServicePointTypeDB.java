/*
 * ServicePointTypeDB.java
 *
 * Created on 13 มิถุนายน 2549, 13:24 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportnan.object.RPNanServicePointType;
import com.reportnan.utility.Language;
import com.reportcenter.objdb.StandardDB;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author pu
 */
public class ServicePointTypeDB implements StandardDB
{
    ConnectionInf theConnectionInf;
    RPNanServicePointType theRPNanServicePointType,ObjectRPNanServicePointType;
    String SQL = "";
    ResultSet rs = null;
    java.util.Vector vObject ;
    int count =0;
    boolean bresult = false;
    /** Creates a new instance of ServicePointTypeDB */
    public ServicePointTypeDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
        theRPNanServicePointType = new RPNanServicePointType();
        
        theRPNanServicePointType.setInitDataFieldName();
    }
    
    
    public int insertData(Object object)throws Exception
    {
        ObjectRPNanServicePointType = (RPNanServicePointType)object;
        ObjectRPNanServicePointType.generateOID(ObjectRPNanServicePointType.idTable);
        SQL = "INSERT INTO " +
                " " + theRPNanServicePointType.tableName +
                " ( " +
                " " /* + theRPNanServicePointType.tableName + "." */+ theRPNanServicePointType.pk_table + "," +
                " " /* + theRPNanServicePointType.tableName + "." */+ theRPNanServicePointType.service_point_type_number + "," +
                " " /* + theRPNanServicePointType.tableName + "." */+ theRPNanServicePointType.service_point_type_description + " " +
                ") " +
                " VALUES ("+
                "'" + ObjectRPNanServicePointType.getObjectId() + "'," +
                "'" + ObjectRPNanServicePointType.service_point_type_number + "'," +
                "'" + ObjectRPNanServicePointType.service_point_type_description + "' " +
                ")" ;
        
        System.out.println("SQL RPNanServicePointType : insertData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public int deleteByKeyID(String key_id)throws Exception
    {
        SQL = "DELETE " +
                " FROM " + theRPNanServicePointType.tableName +
                " WHERE " +
                " " + theRPNanServicePointType.tableName + "." + theRPNanServicePointType.pk_table + "='" + key_id + "' ";
        System.out.println("SQL RPNanServicePointType : deleteByKeyID : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public Object selectByKeyID(String key_id)throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theRPNanServicePointType.tableName +
                " WHERE " + theRPNanServicePointType.tableName + "." + theRPNanServicePointType.pk_table + " = '" + key_id + "' "+
                " ORDER BY to_number(" + theRPNanServicePointType.tableName + "." + theRPNanServicePointType.service_point_type_number +",'99')" ;
        System.out.println("SQL RPNanServicePointType : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }

    public int updateByKeyID(String key_id)
    {
        return 0;
    }
    public int updateData(Object object)throws Exception
    {
        ObjectRPNanServicePointType = (RPNanServicePointType)object;
        
        SQL = "UPDATE " +
                " " + theRPNanServicePointType.tableName +
                " SET " +
                " " /* + theRPNanServicePointType.tableName + "." */+ theRPNanServicePointType.service_point_type_number + "='" + ObjectRPNanServicePointType.service_point_type_number + "', " +
                " " /* + theRPNanServicePointType.tableName + "." */ + theRPNanServicePointType.service_point_type_description + "='" + ObjectRPNanServicePointType.service_point_type_description + "' " +
                " WHERE " +
                " " + theRPNanServicePointType.tableName + "." + theRPNanServicePointType.pk_table + "='" + ObjectRPNanServicePointType.getObjectId() + "' ";
        System.out.println("SQL RPNanServicePointType : updateData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    /**Select ข้อมูลที่อยู่ทั้งหมดในตาราง*/
    public Object selectBySearch(String search) throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theRPNanServicePointType.tableName +
                " WHERE UPPER(" + theRPNanServicePointType.tableName + "." + theRPNanServicePointType.service_point_type_number + ") like UPPER('%" + search + "%') " +
                " OR UPPER("  + theRPNanServicePointType.tableName + "." + theRPNanServicePointType.service_point_type_description + ") like UPPER('%" + search + "%') " +
                " ORDER BY " + theRPNanServicePointType.tableName + "." + theRPNanServicePointType.service_point_type_number;
        System.out.println("SQL PlanGroup : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }
    /**
     *  ใช้ในการตรวจสอบ หาว่า Description ซ้ำกันหรือไม่ ถ้ามีซ้ำกัน จะ ให้ค่าเป็น false ถ้าไม่ซ้ำจะให้ค่าเป็น true
     *  @return เป็น boolean ถ้าเป็น true จะไม่ซ้ำ ถ้าเป็น false จะซ้ำ
     */
    public boolean checkSameServicePointTypeDescription(String engdescription,String key_id)
    {   count =0;
        bresult = true;
        
        if(key_id.equalsIgnoreCase(""))
        {
            SQL = "SELECT COUNT( " +
                    theRPNanServicePointType.tableName + "." + theRPNanServicePointType.pk_table  +
                    ")"   +
                    " FROM " + theRPNanServicePointType.tableName +
                    " WHERE " +
                    " UPPER(" + theRPNanServicePointType.tableName + "." + theRPNanServicePointType.service_point_type_description + ")=UPPER('" + engdescription + "') ";
        }
        else
        {
            SQL = "SELECT COUNT( " +
                    theRPNanServicePointType.tableName + "." + theRPNanServicePointType.pk_table  +
                    ")"   +
                    " FROM " + theRPNanServicePointType.tableName +
                    " WHERE " +
                    " (UPPER(" + theRPNanServicePointType.tableName + "." + theRPNanServicePointType.service_point_type_description + ")=UPPER('" + engdescription + "')) " +
                    " AND " + theRPNanServicePointType.tableName + "." + theRPNanServicePointType.pk_table  + " <> " + key_id ;
        }
        
        System.out.println("SQL ServicePointType : thaidescription : " + SQL);
        
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
    private java.util.Vector eQuery(String sql) throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new java.util.Vector();
        ObjectRPNanServicePointType = new RPNanServicePointType();
        try
        {
            while(rs.next())
            {
                ObjectRPNanServicePointType = new RPNanServicePointType();
                ObjectRPNanServicePointType.setInitData();
                ObjectRPNanServicePointType.setObjectId(rs.getString(theRPNanServicePointType.pk_table));
                ObjectRPNanServicePointType.service_point_type_number= rs.getString(theRPNanServicePointType.service_point_type_number);
                
                ObjectRPNanServicePointType.service_point_type_description = rs.getString(theRPNanServicePointType.service_point_type_description);
                
                vObject.add(ObjectRPNanServicePointType);
                ObjectRPNanServicePointType = null;
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
