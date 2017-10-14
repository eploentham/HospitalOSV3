/*
 * EpiAgeGroupDB.java
 *
 * Created on 31 ¡’π“§¡ 2549, 15:05 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalpcu.objdb;

import com.generalpcu.utility.ComboFix;
import com.hospital_os.usecase.connection.ConnectionInf;
import java.sql.*;
import java.util.Vector;
/**
 *
 * @author nu_ojika
 */
public class EpiAgeGroupDB
{
    ConnectionInf theConnectionInf;
    Vector vc;
    String SQL = "";
    ResultSet rs = null;
    
    /** Creates a new instance of VillageDB */
    public EpiAgeGroupDB(ConnectionInf c)
    {
        theConnectionInf = c;
    }
    
    public Vector selectAll() throws Exception
    {
        SQL = " SELECT " +
                " r_epi_age_group_id " +
                " ,epi_age_group_description" +
                " FROM " +
                " r_epi_age_group ";
        
        vc = evQuery(SQL);
        return vc;
    }
    
    private Vector evQuery(String sql) throws Exception
    {
        ComboFix theComboFix;
        Vector vEpiAgeGroup = new Vector();
        
        rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            theComboFix = new ComboFix(); 
            
            theComboFix.code = rs.getString(1);
            theComboFix.name = rs.getString(2);
            
            vEpiAgeGroup.add(theComboFix);
        }    
        
        return vEpiAgeGroup;
    }
}
