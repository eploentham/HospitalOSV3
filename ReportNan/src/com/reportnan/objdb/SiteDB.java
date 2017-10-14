/*
 * SiteDB.java
 *
 * Created on 6 ÁÔ¶Ø¹ÒÂ¹ 2549, 10:32 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.objdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import java.sql.*;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class SiteDB
{
    ConnectionInf theConnectionInf;
    Vector vc;
    String SQL = "";
    ResultSet rs = null;
    String site_amphur;
    /** Creates a new instance of SiteDB */
    public SiteDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    
    public String getSiteAmphur(String id)
    {
        SQL = "SELECT site_amphur " +
                " FROM b_site " +
                " WHERE	b_visit_office_id ='"+ id + "'";  
         System.out.println(SQL);
         site_amphur = sQuery(SQL);
         return site_amphur;
    }
    
    private String sQuery(String SQL)
    {       
        try
        {
             rs = theConnectionInf.eQuery(SQL);
             while(rs.next())
            {           
                this.site_amphur = rs.getString(1);
            }    
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.print("*-**-*-*-*-*-*- "+this.site_amphur);        
        return  this.site_amphur;
    }
}
