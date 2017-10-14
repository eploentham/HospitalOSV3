/*
 * VersionHealthDB.java
 *
 * Created on 4 ¾ÄÈ¨Ô¡ÒÂ¹ 2548, 10:48 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportcenter.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportcenter.object.*;
import com.reportcenter.utility.Language;
import java.util.Vector;
import java.sql.*;
import com.reportcenter.utility.UseReport;
/**
 *
 * @author tong(Padungrat)
 */
public class VersionHealthDB {
    
    ConnectionInf theConnectionInf;
    Vector vc;
    String SQL = "";
    ResultSet rs = null;
    VersionHealth theVersionHealth,theObjectVersionHealth;
    boolean bresult;
    int iresult;
    public VersionHealthDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theVersionHealth = new VersionHealth();
        theVersionHealth.setInitDataFieldName();
    
    }
    
    public VersionHealth selectCurrentVersion()
    {
        SQL = "SELECT " +
              theVersionHealth.tableName + "." + theVersionHealth.pk_table + ","+  
              theVersionHealth.tableName + "." + theVersionHealth.app_code + ","+
              theVersionHealth.tableName + "." + theVersionHealth.db_code + " "+  
              " FROM " +
                theVersionHealth.tableName + "" +
              " ORDER BY " +
                theVersionHealth.tableName + "." + theVersionHealth.update_time + " DESC , " +
                theVersionHealth.tableName + "." + theVersionHealth.db_code + " DESC " +
             " LIMIT 1;";
        theObjectVersionHealth = null;
        try
        {   
            rs = theConnectionInf.eQuery(SQL);
            
            while(rs.next())
            {   theObjectVersionHealth = new VersionHealth();
                theObjectVersionHealth.setInitData();
                theObjectVersionHealth.setObjectId(rs.getString(theVersionHealth.pk_table));
                theObjectVersionHealth.app_code = rs.getString(theVersionHealth.app_code);
                theObjectVersionHealth.db_code = rs.getString(theVersionHealth.db_code);
                theObjectVersionHealth.description = UseReport.MODULENAME[1];
            }
            rs.close();
        }
        catch(Exception ex)
        {   
            ex.printStackTrace();
        }
        finally
        {
           System.out.println(SQL);
        }
        
        return theObjectVersionHealth;    
    
    }
    
}
