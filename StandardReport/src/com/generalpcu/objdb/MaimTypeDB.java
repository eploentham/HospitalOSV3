/*
 * MaimTypeDB.java
 *
 * Created on 27 ¡’π“§¡ 2549, 15:27 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalpcu.objdb;
import com.generalpcu.utility.ComboFix;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalpcu.object.MaimType;
import java.sql.*;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class MaimTypeDB
{
    ConnectionInf theConnectionInf;
    Vector vc;
    String SQL = "";
    ResultSet rs = null;
    
    /** Creates a new instance of MaimTypeDB */
    public MaimTypeDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    
    public Vector selectAll() throws Exception
    {
        SQL = "SELECT b_health_maim_id,health_maim_description FROM b_health_maim " +
                "WHERE health_maim_active = '1'";
        
        vc = evQuery(SQL);
        return vc;
    }
    
    private Vector evQuery(String sql) throws Exception
    {
        ComboFix theComboFix;
        Vector vMaimType = new Vector();
        
        rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            theComboFix = new ComboFix();
            
            theComboFix.code = rs.getString(1);
            theComboFix.name = rs.getString(2);
            
            vMaimType.add(theComboFix);
        }        
        return vMaimType;
    }
}
