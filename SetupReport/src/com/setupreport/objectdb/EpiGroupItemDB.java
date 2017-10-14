/*
 * EpiGroupItemDB.java
 *
 * Created on 7 มีนาคม 2549, 11:29 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.objectdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.DBPersist;
import com.hosv3.utility.StandardDB;
import com.setupreport.object.*;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author Ojika
 */
public class EpiGroupItemDB extends DBPersist
{
    
    /** Creates a new instance of EpiGroupItemDB */
    ConnectionInf theConnectionInf;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    EpiGroupItem theEpiGroupItem;
    
    public EpiGroupItemDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theEpiGroupItem = new EpiGroupItem();
        theEpiGroupItem.idTable = "822";
    }

    /**
     *  สำหรับลบรายการวัคซีนของกลุ่มวัคซีนการดูแลเด็กเล็ก
     *  @Author Ojika
     *  @Date 07/03/2549
     **/
    public int deleteByKeyID(String key_id)throws Exception
    {
        SQL = " DELETE FROM r_epi_group_item WHERE r_epi_group_item_id = '"+ key_id +"'";
        
        return theConnectionInf.eUpdate(SQL);
    }

    /**
     *  สำหรับบันทึกรายการวัคซีนของกลุ่มวัคซีนการดูแลเด็กเล็ก
     *  @Author Ojika
     *  @Date 07/03/2549
     **/
    public int insertData(Object object)throws Exception
    {
        theEpiGroupItem = (EpiGroupItem)object;
        theEpiGroupItem.generateOID(theEpiGroupItem.idTable);
        
        SQL = " INSERT INTO r_epi_group_item (" +
                "r_epi_group_item_id " +
                ",r_epi_group_id " +
                ",b_health_epi_group_id " +
                ",epi_group_item_description " +
                ") values (" +
                "'" + theEpiGroupItem.getObjectId() + "' " +
                ",'" + theEpiGroupItem.r_epi_group_id + "' " +
                ",'" + theEpiGroupItem.b_health_epi_group_id + "' " +
                ",'" + theEpiGroupItem.epi_group_item_description + "' " +
                ")";
        
        return theConnectionInf.eUpdate(SQL);
    }

    /**
     *  สำหรับค้นหารายการวัคซีนของกลุ่มวัคซีนการดูแลเด็กเล็ก จากตำค้นที่ส่งมา
     *  @Param epiGroupId เป็น รหัสของกลุ่มวัคซีน
     *  @Author Ojika
     *  @Date 08/03/2549
     **/
    public Vector selectByEpiGroupId(String epiGroupId)throws Exception
    {
        SQL = " SELECT * FROM r_epi_group_item " +
                " WHERE r_epi_group_id like '%"+ epiGroupId  +"%' ";
        
        return eQuery(SQL);
    }
    
    private java.util.Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new Vector();
        try{
            while(rs.next()) 
            {
                theEpiGroupItem = new EpiGroupItem();
                
                theEpiGroupItem.setObjectId(rs.getString(1));
                theEpiGroupItem.r_epi_group_item_id = rs.getString(1);
                
                theEpiGroupItem.r_epi_group_id = rs.getString(2);
                theEpiGroupItem.b_health_epi_group_id = rs.getString(3);
                theEpiGroupItem.epi_group_item_description = rs.getString(4);
                
                vObject.add(theEpiGroupItem);
                theEpiGroupItem = null;
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
