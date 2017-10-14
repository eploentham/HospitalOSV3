/*
 * TreatStatusDB.java
 *
 * Created on 8 กันยายน 2548, 15:01 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.objdb;

import java.util.*;
import java.sql.*;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.utility.ComboFix;
/**
 *
 * @author nu_ojika
 */
public class TreatStatusDB {
    
    /**
     * Creates a new instance of TreatStatusDB 
     */
    ConnectionInf theConnectionInf;
    ResultSet rs = null;
    ComboFix theComboFix;
    Vector vTreatStatus;
    String sql="";
    
    public TreatStatusDB(ConnectionInf c) {
        theConnectionInf = c;
        theComboFix = new ComboFix();
    }
    
    public Vector getTreatStatusAll() throws Exception
    {
        sql = "select * from f_chronic_discharge_status";
        
        eQuery(sql);
        
        return this.vTreatStatus;
    }
    
    private void eQuery(String sql)
    {
        
        try
        {
        rs = theConnectionInf.eQuery(sql);
        vTreatStatus= new Vector();
        theComboFix = new ComboFix();
            while(rs.next())
            {
                theComboFix = new ComboFix();
                
                theComboFix.code = rs.getString(1);
                theComboFix.name = rs.getString(2);
                
                vTreatStatus.add(theComboFix);
                theComboFix = null;
            }
                     
            rs.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }
    
}
