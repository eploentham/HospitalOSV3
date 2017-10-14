/*
 * OfficeNanDB.java
 *
 * Created on 20 มิถุนายน 2549, 15:09 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.objdb;

import com.hospital_os.objdb.OfficeDB;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.object.Office;
import java.util.*;
import java.sql.*;
/**
 *
 * @author ojika
 */
public class OfficeNanDB extends OfficeDB
{
    public ConnectionInf theConnectionInf;
    String SQL ="";
    
    public OfficeNanDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    
    public Object selectBySearch(String search, int offset) throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + dbObj.table +   
                " WHERE (UPPER(" + dbObj.table + "." + dbObj.pk_field + ") like UPPER('%" + search + "%') " +
                " OR UPPER("  + dbObj.table + "." + dbObj.name + ") like UPPER('%" + search + "%')) " +
                
                " ORDER BY " + dbObj.table + "." + dbObj.pk_field +
                " Limit 15 Offset " + offset ; 
        
        System.out.println("SQL Item : selectBySearch : " + SQL);
        return eQuery(SQL);
    }
    
    /**Select ข้อมูลที่อยู่ทั้งหมดในตาราง*/
    public Object selectByAll(int offset)  throws Exception
    {
        SQL = "SELECT * FROM " +
                " " + dbObj.table +
                " ORDER BY " + dbObj.table + "." + dbObj.pk_field +
                " Limit 15 Offset " + offset ;
        
        System.out.println("SQL Item : selectAll : " + SQL);
        return eQuery(SQL);
    }
    
    public Vector eQuery(String sql) throws Exception
    {
        Office p;
        Vector list = new Vector();
        ResultSet rs = theConnectionInf.eQuery(sql);
        while(rs.next())
        {
            p = new Office();
            p.setObjectId(rs.getString(dbObj.pk_field));
            p.name = rs.getString(dbObj.name);
            list.add(p);
            p = null;
        }
        rs.close();
        return list;
    }
    
}
