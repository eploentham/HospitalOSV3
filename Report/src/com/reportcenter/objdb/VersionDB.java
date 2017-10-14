/*
 * VersionDB.java
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
public class VersionDB {
    
    ConnectionInf theConnectionInf;
    Vector vc;
    String SQL = "";
    ResultSet rs = null;
    Version theVersion,theObjectVersion;
    boolean bresult;
    int iresult;
    public VersionDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theVersion = new Version();
        theVersion.setInitDataFieldName();
    
    }
    
    public Version selectCurrentVersion()
    {
        SQL = "SELECT " +
              theVersion.tableName + "." + theVersion.pk_table + ","+  
              theVersion.tableName + "." + theVersion.app_code + ","+
              theVersion.tableName + "." + theVersion.db_code + " "+  
              " FROM " +
                theVersion.tableName + "" +
              " ORDER BY " +
                theVersion.tableName + "." + theVersion.update_time + " DESC , " +
                theVersion.tableName + "." + theVersion.db_code + " DESC " +
             " LIMIT 1;";
        theObjectVersion = null;
        try
        {   
            rs = theConnectionInf.eQuery(SQL);
            
            while(rs.next())
            {   theObjectVersion = new Version();
                theObjectVersion.setInitData();
                theObjectVersion.setObjectId(rs.getString(theVersion.pk_table));
                theObjectVersion.app_code = rs.getString(theVersion.app_code);
                theObjectVersion.db_code = rs.getString(theVersion.db_code);
                theObjectVersion.description = UseReport.MODULENAME[0];
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
        
        return theObjectVersion;    
    }
}
