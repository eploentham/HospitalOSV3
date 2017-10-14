/*
 * EpiTTItemDB.java
 *
 * Created on 28 มีนาคม 2549, 11:30 น.
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
public class EpiTTItemDB extends DBPersist
{
    
    /** Creates a new instance of EpiTTItemDB */
    ConnectionInf theConnectionInf;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    EpiTTItem theEpiTTItem;
    
    public EpiTTItemDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theEpiTTItem = new EpiTTItem();
    }

    /**
     *  สำหรับค้นหารายการวัคซีนของ TT
     *  @Author Ojika
     *  @Date 28/03/2549
     **/
    public Vector selectAll()throws Exception
    {
        SQL = " SELECT * FROM r_epi_tt_item ";
        
        return eQuery(SQL);
    }
    
    /**
     *  สำหรับลบรายการวัคซีนของ TT
     *  @Author Ojika
     *  @Date 28/03/2549
     **/
    public int deleteByKeyID(String key_id)throws Exception
    {
        SQL = " DELETE FROM r_epi_tt_item WHERE r_epi_tt_item_id = '"+ key_id +"'";
        
        return theConnectionInf.eUpdate(SQL);
    }

    /**
     *  สำหรับบันทึกรายการวัคซีนของ TT
     *  @Author Ojika
     *  @Date 28/03/2549
     **/
    public int insertData(Object object)throws Exception
    {
        theEpiTTItem = (EpiTTItem)object;
        theEpiTTItem.generateOID(theEpiTTItem.idTable);
        
        SQL = " INSERT INTO r_epi_tt_item (" +
                "r_epi_tt_item_id " +
                ",b_health_epi_group_id " +
                ",epi_tt_item_description " +
                ") values (" +
                "'" + theEpiTTItem.getObjectId() + "' " +
                ",'" + theEpiTTItem.b_health_epi_group_id + "' " +
                ",'" + theEpiTTItem.epi_TT_item_description + "' " +
                ")";
        
        return theConnectionInf.eUpdate(SQL);
    }

    /**
     *  สำหรับค้นหารายการวัคซีนของ TT จากตำค้นที่ส่งมา
     *  @Param keyword เป็น ตัวแปรของคำค้น โดยจะนำไปค้นหาจากรายละเอียด
     *  @Author Ojika
     *  @Date 28/03/2549
     **/
    public Vector selectByKeyword(String keyword)throws Exception
    {
        SQL = " SELECT * FROM r_epi_tt_item " +
                " WHERE UPPER(epi_tt_item_description) like '%"+ keyword  +"%' ";
        
        return eQuery(SQL);
    }
    
    private java.util.Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new Vector();
        try{
            while(rs.next()) 
            {
                theEpiTTItem = new EpiTTItem();
                
                theEpiTTItem.setObjectId(rs.getString(1));
                theEpiTTItem.r_epi_TT_item_id = rs.getString(1);
                
                theEpiTTItem.b_health_epi_group_id = rs.getString(2);
                theEpiTTItem.epi_TT_item_description = rs.getString(3);
                
                vObject.add(theEpiTTItem);
                theEpiTTItem = null;
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
