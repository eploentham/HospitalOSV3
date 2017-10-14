/*
 * EpiGroupDB.java
 *
 * Created on 8 มีนาคม 2549, 16:54 น.
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
public class EpiGroupDB
{
    
    /** Creates a new instance of EpiGroupDB */
    ConnectionInf theConnectionInf;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    EpiGroup theEpiGroup;
    
    public EpiGroupDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theEpiGroup = new EpiGroup();
    }
    
    /**
     *  สำหรับค้นหากลุ่มวัคซีนการดูแลเด็กเล็ก จากคำค้นที่ส่งมา
     *  @Param keyword เป็น ตัวแปรของคำค้น โดยจะนำไปค้นหาจากรายละเอียด
     *  @Author Ojika
     *  @Date 08/03/2549
     **/
    public Vector selectByKeyword(String keyword)throws Exception
    {
        SQL = " SELECT * FROM r_epi_group " +
                " WHERE UPPER(epi_group_number) like '%"+ keyword  +"%' " +
                " OR UPPER(r_epi_group_description) like '%"+ keyword  +"%' ";
        
        return eQuery(SQL);
    }
    
    private Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new Vector();
        try{
            while(rs.next()) 
            {
                theEpiGroup = new EpiGroup();
                
                theEpiGroup.setObjectId(rs.getString(1));
                theEpiGroup.r_epi_group_id = rs.getString(1);
                theEpiGroup.epi_group_number = rs.getString(2);
                theEpiGroup.epi_group_description = rs.getString(3);
                
                vObject.add(theEpiGroup);
                theEpiGroup = null;
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
