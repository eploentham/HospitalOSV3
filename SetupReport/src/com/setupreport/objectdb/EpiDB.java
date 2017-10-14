/*
 * EpiDB.java
 *
 * Created on 8 ¡’π“§¡ 2549, 15:52 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.objectdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreport.object.*;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author Ojika
 */
public class EpiDB
{
    
    /** Creates a new instance of EpiDB */
    ConnectionInf theConnectionInf;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    Epi theEpi;

    public EpiDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theEpi = new Epi();
    }
    
    public Vector selectByKeyword(String keyword)throws Exception
    {
        SQL = " select " +
                " b_health_epi_group_id " +
                " ,health_epi_group_description " +
                " from " +
                " b_health_epi_group " +
                " where " +
                " UPPER(health_epi_group_description)like '%"+keyword+"%'" ;

        System.out.println("SQL : selectBySearch : " + SQL);
        return eQuery(SQL);
    }
    
    private Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new Vector();
        
        try{
            while(rs.next()) 
            {
                theEpi = new Epi();
                
                theEpi.setObjectId(rs.getString(1));  
                theEpi.b_health_epi_group_id  = rs.getString(1);  
                theEpi.health_epi_group_description = rs.getString(2);  
                         
                vObject.add(theEpi);
                theEpi = null;
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if(vObject.size() == 0)
                vObject = null;
        }
        return vObject;
    }
    
}
