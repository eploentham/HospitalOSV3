/*
 * VillageDB.java
 *
 * Created on 11 กุมภาพันธ์ 2549, 10:08 น.
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
public class VillageDB
{
    ConnectionInf theConnectionInf;
    Vector vc;
    String SQL = "";
    ResultSet rs = null;
    
    /** Creates a new instance of VillageDB */
    public VillageDB(ConnectionInf c)
    {
        theConnectionInf = c;
    }
    
    public Vector selectAll() throws Exception
    {
        SQL = " SELECT " +
                " t_health_village_id " +
                " ,village_name" +
                " FROM " +
                " t_health_village " +
                " WHERE " +
                " village_active = '1' " + 
                // ให้ดึงเฉพาะหมู่บ้านในเขตเท่านั้น sumo 21/10/2549
                " AND " +
                " (village_moo <> '0' AND village_moo <> '00')";
        
        vc = evQuery(SQL);
        return vc;
    }
    
    private Vector evQuery(String sql) throws Exception
    {
        ComboFix theComboFix;
        Vector vVillage = new Vector();
        
        rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            theComboFix = new ComboFix(); 
            
            theComboFix.code = rs.getString(1);
            theComboFix.name = rs.getString(2);
            
            vVillage.add(theComboFix);
        }    
        
        return vVillage;
    }
}
