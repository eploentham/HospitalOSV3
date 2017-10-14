/*
 * ServicePointTypeMapDB.java
 *
 * Created on 13 ÁÔ¶Ø¹ÒÂ¹ 2549, 13:38 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportnan.object.RPNanServicePointTypeMap;
import com.reportnan.utility.Language;
import com.reportcenter.objdb.StandardDB;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author pu
 */
public class ServicePointTypeMapDB implements StandardDB
{
    ConnectionInf theConnectionInf;
    RPNanServicePointTypeMap theRPNanServicePointTypeMap,ObjectRPNanServicePointTypeMap;
    String SQL = "";
    ResultSet rs = null;
    java.util.Vector vObject ;
    int count =0;
    boolean bresult = false;
    
    /** Creates a new instance of ServicePointTypeMapDB */
    public ServicePointTypeMapDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
        theRPNanServicePointTypeMap = new RPNanServicePointTypeMap();
        
        theRPNanServicePointTypeMap.setInitDataFieldName();        
    }

    public int deleteByKeyID(String key_id)throws Exception
    {
        SQL = " DELETE FROM " +
                theRPNanServicePointTypeMap.tableName + 
                " WHERE " +
                theRPNanServicePointTypeMap.pk_table + " = " + key_id;
        
        System.out.println("SQL RPNanServicePointType : deleteByKeyID : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public int insertData(Object object)throws Exception
    {
        ObjectRPNanServicePointTypeMap = (RPNanServicePointTypeMap)object;
        ObjectRPNanServicePointTypeMap.generateOID(ObjectRPNanServicePointTypeMap.idTable);
        SQL = "INSERT INTO " +
                " " + theRPNanServicePointTypeMap.tableName +
                " ( " +
                " " /* + theRPNanServicePointTypeMap.tableName + "." */+ theRPNanServicePointTypeMap.pk_table + "," +
                " " /* + theRPNanServicePointTypeMap.tableName + "." */+ theRPNanServicePointTypeMap.r_service_point_type_id + "," +
                " " /* + theRPNanServicePointTypeMap.tableName + "." */+ theRPNanServicePointTypeMap.b_service_point_id + "," +
                " " /* + theRPNanServicePointTypeMap.tableName + "." */+ theRPNanServicePointTypeMap.service_point_description + "," +
                " " /* + theRPNanServicePointTypeMap.tableName + "." */+ theRPNanServicePointTypeMap.service_point_type_description + " " +               
                ") " +
                " VALUES ("+
                "'" + ObjectRPNanServicePointTypeMap.getObjectId() + "', " +
                "'" + ObjectRPNanServicePointTypeMap.r_service_point_type_id + "', " +
                "'" + ObjectRPNanServicePointTypeMap.b_service_point_id + "', " +
                "'" + ObjectRPNanServicePointTypeMap.service_point_description + "', " +
                "'" + ObjectRPNanServicePointTypeMap.service_point_type_description + "'" +
                ")" ;
        
        System.out.println("SQL RPNanServicePointType : insertData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public Object selectByKeyID(String key_id)throws Exception
    {
        return null;
    }

    public int updateByKeyID(String key_id)throws Exception
    {
        return 0;
    }
    
    public int updateData(Object object)throws Exception
    {
        ObjectRPNanServicePointTypeMap = (RPNanServicePointTypeMap)object;
        
        SQL = "UPDATE " +
                " " + theRPNanServicePointTypeMap.tableName +
                " SET " +
                " " /* + theRPNanServicePointTypeMap.tableName + "." */+ theRPNanServicePointTypeMap.r_service_point_type_id + "='" + ObjectRPNanServicePointTypeMap.r_service_point_type_id + "', " +
                " " /* + theRPNanServicePointTypeMap.tableName + "." */ + theRPNanServicePointTypeMap.b_service_point_id + "='" + ObjectRPNanServicePointTypeMap.b_service_point_id + "', " +
                " " /* + theRPNanServicePointTypeMap.tableName + "." */ + theRPNanServicePointTypeMap.service_point_description + "='" + ObjectRPNanServicePointTypeMap.service_point_description + "', " +
                " " /* + theRPNanServicePointTypeMap.tableName + "." */ + theRPNanServicePointTypeMap.service_point_type_description + "='" + ObjectRPNanServicePointTypeMap.service_point_type_description + "'" +
              " WHERE " +
                " " + theRPNanServicePointTypeMap.tableName + "." + theRPNanServicePointTypeMap.pk_table + "='" + ObjectRPNanServicePointTypeMap.getObjectId() + "' ";
        System.out.println("SQL RPNanServicePointType : updateData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    public Vector selectByServicePointTypeId(String servicePointTyprId)throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theRPNanServicePointTypeMap.tableName +
                " WHERE " + theRPNanServicePointTypeMap.tableName + "." + theRPNanServicePointTypeMap.r_service_point_type_id + " ='"+ servicePointTyprId +"'" +
                " ORDER BY " + theRPNanServicePointTypeMap.tableName + "." + theRPNanServicePointTypeMap.service_point_description;
        System.out.println("SQL PlanGroup : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }
    
    private java.util.Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new java.util.Vector();
        ObjectRPNanServicePointTypeMap = new RPNanServicePointTypeMap();
        try
        {
            while(rs.next())
            {
                ObjectRPNanServicePointTypeMap = new RPNanServicePointTypeMap();
                ObjectRPNanServicePointTypeMap.setInitData();
                ObjectRPNanServicePointTypeMap.setObjectId(rs.getString(theRPNanServicePointTypeMap.pk_table));
                ObjectRPNanServicePointTypeMap.r_service_point_type_id = rs.getString(theRPNanServicePointTypeMap.r_service_point_type_id);
                ObjectRPNanServicePointTypeMap.b_service_point_id = rs.getString(theRPNanServicePointTypeMap.b_service_point_id);
                ObjectRPNanServicePointTypeMap.service_point_description = rs.getString(theRPNanServicePointTypeMap.service_point_description);
                ObjectRPNanServicePointTypeMap.service_point_type_description = rs.getString(theRPNanServicePointTypeMap.service_point_type_description);
                              
                vObject.add(ObjectRPNanServicePointTypeMap);
                ObjectRPNanServicePointTypeMap = null;
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
