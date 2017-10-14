/*
 * TambonDB.java
 *
 * Created on 6 ÁÔ¶Ø¹ÒÂ¹ 2549, 9:56 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.objdb;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportnan.utility.ComboFix;
import java.sql.ResultSet;
import java.util.Vector;
/**
 *
 * @author pu
 */
public class TambonDB
{
    ConnectionInf theConnectionInf;
    Vector vTambon;
    String SQL = "";
    ResultSet rs;
    /** Creates a new instance of TambonDB */
    public TambonDB(ConnectionInf conf)
    {
        theConnectionInf = conf;
    }
    
    public Vector selectAll(String amphur_id)
    {
        vTambon = new Vector();
        try
        {
            SQL = "SELECT f_address_id ,address_description" +
                    " FROM f_address" +
                    " WHERE address_tambon_type = '3' AND " +
                    " address_amphur_id = '"+ amphur_id +"' ";
            System.out.println(SQL);
            vTambon = evQuery(SQL);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vTambon;
    }  
    
    
    
    private Vector evQuery(String sql) throws Exception
    {
        ComboFix theComboFix;
        Vector vTambon = new Vector();
        
        rs = theConnectionInf.eQuery(sql);
        
        while(rs.next())
        {
            theComboFix = new ComboFix();
            
            theComboFix.code = rs.getString(1);
            theComboFix.name = rs.getString(2);
            
            vTambon.add(theComboFix);
        }        
        return vTambon;
    }
}
