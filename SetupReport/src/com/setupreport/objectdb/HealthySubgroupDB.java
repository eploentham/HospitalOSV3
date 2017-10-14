/*
 * HealthySubgroup.java
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
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author Ojika
 */
public class HealthySubgroupDB
{
    
    /** Creates a new instance of HealthyGroup */
    ConnectionInf theConnectionInf;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    HealthySubgroup theHealthySubgroup;
    
    public HealthySubgroupDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
    }
    
    /**
     *  สำหรับค้นหา กลุ่มย่อยการตรวจสุขภาพตาม 11รง5 ส่วนที่ 3
     *  @Param keyword เป็น ตัวแปรของคำค้น โดยจะนำไปค้นหาจากรายละเอียด
     *  @Author Ojika
     *  @Date 09/03/2549
     **/
    public Vector selectByKeyword(String keyword)throws Exception
    {
        SQL = " SELECT * FROM r_healthy_subgroup " +
                " WHERE UPPER(healthy_subgroup_number) like '%"+ keyword  +"%' " +
                " OR UPPER(healthy_subgroup_description) like '%"+ keyword  +"%' ";
        
        return eQuery(SQL);
    }
    
    private Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new Vector();
        try{
            while(rs.next()) 
            {
                theHealthySubgroup = new HealthySubgroup();
                
                theHealthySubgroup.setObjectId(rs.getString(1));
                
                theHealthySubgroup.r_healthy_subgroup_id = rs.getString(1);
                theHealthySubgroup.r_healthy_group_id = rs.getString(2);
                theHealthySubgroup.healthy_subgroup_number = rs.getString(3);
                theHealthySubgroup.healthy_subgroup_description = rs.getString(4);
                
                vObject.add(theHealthySubgroup);
                theHealthySubgroup = null;
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
}
