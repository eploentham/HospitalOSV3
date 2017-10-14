/*
 * ReferHospitalDB.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2549, 14:05 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.objdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportnan.object.ReferHospital;
import com.reportnan.utility.Language;
import com.reportcenter.objdb.StandardDB;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author ojika
 */
public class ReferHospitalDB implements StandardDB
{
    
    ConnectionInf theConnectionInf;
    ReferHospital theReferHospital,ObjectReferHospital;
    String SQL = "";
    ResultSet rs = null;
    java.util.Vector vObject ;
    int count =0;
    boolean bresult = false;
    
    public ReferHospitalDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
        theReferHospital = new ReferHospital();        
        theReferHospital.setInitDataFieldName();
    }

    public int deleteByKeyID(String key_id)throws Exception
    {
        SQL = "DELETE " +
                " FROM " + theReferHospital.tableName +
                " WHERE " +
                " " + theReferHospital.tableName + "." + theReferHospital.pk_table + "='" + key_id + "' ";
        System.out.println("SQL ReferHospital : deleteByKeyID : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public int insertData(Object object)throws Exception
    {
        ObjectReferHospital = (ReferHospital)object;
        ObjectReferHospital.generateOID(ObjectReferHospital.idTable);
        SQL = "INSERT INTO " +
                " " + theReferHospital.tableName +
                " ( " +
                " " + theReferHospital.pk_table + "," +
                " " + theReferHospital.r_refer_office_type_id + "," +
                " " + theReferHospital.refer_office_code + "," +
                " " + theReferHospital.refer_office_description + " " +
                ") " +
                " VALUES ("+
                "'" + ObjectReferHospital.getObjectId() + "'," +
                "'" + ObjectReferHospital.r_refer_office_type_id + "'," +
                "'" + ObjectReferHospital.refer_office_code + "'," +
                "'" + ObjectReferHospital.refer_office_description + "' " +
                ")" ;
        
        System.out.println("SQL ReferHospital : insertData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    public int updateData(Object object)throws Exception
    {
        ObjectReferHospital = (ReferHospital)object;
        SQL = "UPDATE  " +
                " " + theReferHospital.tableName +
                " SET " +
                " " + theReferHospital.refer_office_code + " = '" + ObjectReferHospital.refer_office_code + "'" +
                ", " + theReferHospital.refer_office_description + " = '" + ObjectReferHospital.refer_office_description + "'" +
                ", " + theReferHospital.r_refer_office_type_id + " = '" + ObjectReferHospital.r_refer_office_type_id + "'" +
                " WHERE "+
                " " + theReferHospital.pk_table + " = '" + ObjectReferHospital.getObjectId() + "'";
        
        System.out.println("SQL ReferHospital : updateData : " + SQL);
        
        return theConnectionInf.eUpdate(SQL);
    }

    public Object selectByKeyID(String key_id)throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theReferHospital.tableName +
                " WHERE " + theReferHospital.tableName + "." + theReferHospital.pk_table + " = '" + key_id + "' ";
        System.out.println("SQL ReferHospital : selectByKeyID : " + SQL);
        return eQuery(SQL);
    }
    
    public Vector selectByKeyword(String keyword)throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + theReferHospital.tableName +
                " WHERE UPPER(" + theReferHospital.tableName + "." + theReferHospital.refer_office_code + ") like UPPER('%" + keyword + "%') " +
                " or UPPER(" + theReferHospital.tableName + "." + theReferHospital.refer_office_description + ") like UPPER('%" + keyword + "%') ";
        System.out.println("SQL ReferHospital : selectByKeyword : " + SQL);
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
        ObjectReferHospital = new ReferHospital();
        try
        {
            while(rs.next())
            {
                ObjectReferHospital = new ReferHospital();
                ObjectReferHospital.setInitData();
                ObjectReferHospital.setObjectId(rs.getString(theReferHospital.pk_table));
                ObjectReferHospital.r_refer_office_type_id= rs.getString(theReferHospital.r_refer_office_type_id);                
                ObjectReferHospital.refer_office_code = rs.getString(theReferHospital.refer_office_code);
                ObjectReferHospital.refer_office_description = rs.getString(theReferHospital.refer_office_description);
                
                vObject.add(ObjectReferHospital);
                ObjectReferHospital = null;
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
