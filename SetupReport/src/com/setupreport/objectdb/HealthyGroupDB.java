/*
 * HealthyGroup.java
 *
 * Created on 9 มีนาคม 2549, 12:03 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.objectdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreport.object.*;
import com.setupreport.utility.ComboFix;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author Ojika
 */
public class HealthyGroupDB
{
    
    /** Creates a new instance of HealthyGroup */
    ConnectionInf theConnectionInf;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    HealthyGroup theHealthyGroup;
    
    public HealthyGroupDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
    }
    
    /**
     *  สำหรับค้นหา กลุ่มการตรวจสุขภาพตาม 11รง5 ส่วนที่ 3
     *  @Param keyword เป็น ตัวแปรของคำค้น โดยจะนำไปค้นหาจากรายละเอียด
     *  @Author Ojika
     *  @Date 09/03/2549
     **/
    public Vector selectByKeyword(String keyword) throws Exception
    {
        SQL = " SELECT * FROM r_healthy_group " +
                " WHERE UPPER(healthy_group_number) like '%"+ keyword  +"%' " +
                " OR UPPER(healthy_group_description) like '%"+ keyword  +"%' ";
        
        return eQuery(SQL);
    }
    
    /**
     *  สำหรับค้นหา กลุ่มการตรวจสุขภาพตาม 11รง5 ส่วนที่ 3 เพื่อเซ็ทเป็น Combobox
     *  @Author Ojika
     *  @Date 14/03/2549
     **/
    public Vector selectAll() throws Exception
    {
        SQL = " SELECT r_healthy_group_id, healthy_group_description " +
                " FROM r_healthy_group ";
        
        return eComboQuery(SQL);
    }
    
    private Vector eQuery(String sql) throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new Vector();
        try{
            while(rs.next()) 
            {
                theHealthyGroup = new HealthyGroup();
                
                theHealthyGroup.setObjectId(rs.getString(1));
                
                theHealthyGroup.r_healthy_group_id = rs.getString(1);
                theHealthyGroup.healthy_group_number = rs.getString(2);
                theHealthyGroup.healthy_group_description = rs.getString(3);
                
                vObject.add(theHealthyGroup);
                theHealthyGroup = null;
            }
            
            rs.close();
        }
        catch(Exception ex)
        {
        }
        finally
        {
            if(vObject.size() == 0)
                vObject = null;
        }
        return vObject;
    }
    
    private Vector eComboQuery(String SQL) throws Exception
    {
        ComboFix theComboFix;
        Vector vHealthyGroup = new Vector();
        
        rs = theConnectionInf.eQuery(SQL);
        
        while(rs.next())
        {
            theComboFix = new ComboFix(); 
            
            theComboFix.code = rs.getString(1);
            theComboFix.name = rs.getString(2);
            
            vHealthyGroup.add(theComboFix);
        }    
        
        return vHealthyGroup;
    }
}
