/*
 * ReferHospitalTypeDB.java
 *
 * Created on 21 มิถุนายน 2549, 17:19 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.objdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportnan.utility.ComboFix;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author Ojika
 */
public class ReferHospitalTypeDB
{
    ConnectionInf theConnectionInf;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    
    public ReferHospitalTypeDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
    }
    
    /**
     *  สำหรับค้นหา ประเภทสถานพยาบาล เพื่อเซ็ทเป็น Combobox
     *  @Author Ojika
     *  @Date 21/06/2549
     **/
    public Vector selectAll() throws Exception
    {
        SQL = " SELECT r_refer_office_type_id, refer_office_type_description " +
                " FROM r_refer_office_type ";
        
        return eComboQuery(SQL);
    }
    
    private Vector eComboQuery(String SQL) throws Exception
    {
        ComboFix theComboFix;
        Vector vObject = new Vector();
        
        rs = theConnectionInf.eQuery(SQL);
        
        while(rs.next())
        {
            theComboFix = new ComboFix(); 
            
            theComboFix.code = rs.getString(1);
            theComboFix.name = rs.getString(2);
            
            vObject.add(theComboFix);
        }    
        
        return vObject;
    }
    
}
