/*
 * DiseaseDB.java
 *
 * Created on 23 กุมภาพันธ์ 2549, 15:00 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalpcu.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalpcu.utility.ComboFix;
import java.sql.*;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class DiseaseDB
{
    private ConnectionInf theConnectionInf;
    private Vector vDisease;
    private String SQL = "";
    private ResultSet rs ;
    /** Creates a new instance of DiseaseDB */
    public DiseaseDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    
   public Vector selectAll() throws Exception
    {
        SQL = "SELECT b_health_disease_id,health_disease_description " +
                " FROM b_health_disease " +
                " WHERE health_disease_active = '1'";
        return evQuery(SQL);
    }
  
        
    private Vector evQuery(String sql) throws Exception
    {
        ComboFix theComboFix;
        Vector vDisease = new Vector();
        
        rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            theComboFix = new ComboFix(); 
            
            theComboFix.code = rs.getString(1);
            theComboFix.name = rs.getString(2);
            
            vDisease.add(theComboFix);
        }       
        return vDisease;
    }
}
