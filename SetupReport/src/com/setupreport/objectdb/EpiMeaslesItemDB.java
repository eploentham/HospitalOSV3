/*
 * EpiMeaslesItemDB.java
 *
 * Created on 7 มีนาคม 2549, 11:30 น.
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
public class EpiMeaslesItemDB extends DBPersist
{
    
    /** Creates a new instance of EpiMeaslesItemDB */
    ConnectionInf theConnectionInf;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    EpiMeaslesItem theEpiMeaslesItem;
    
    public EpiMeaslesItemDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theEpiMeaslesItem = new EpiMeaslesItem();
    }

    /**
     *  สำหรับค้นหารายการวัคซีนของหัดเยอรมัน ทั้งหมด
     *  @Author Ojika
     *  @Date 08/03/2549
     **/
    public Vector selectAll()throws Exception
    {
        SQL = " SELECT * FROM r_epi_measles_item ";
        
        return eQuery(SQL);
    }
    
    /**
     *  สำหรับลบรายการวัคซีนของหัดเยอรมัน
     *  @Author Ojika
     *  @Date 07/03/2549
     **/
    public int deleteByKeyID(String key_id)throws Exception
    {
        SQL = " DELETE FROM r_epi_measles_item WHERE r_epi_measles_item_id = '"+ key_id +"'";
        
        return theConnectionInf.eUpdate(SQL);
    }

    /**
     *  สำหรับบันทึกรายการวัคซีนของหัดเยอรมัน
     *  @Author Ojika
     *  @Date 07/03/2549
     **/
    public int insertData(Object object)throws Exception
    {
        theEpiMeaslesItem = (EpiMeaslesItem)object;
        theEpiMeaslesItem.generateOID(theEpiMeaslesItem.idTable);
        
        SQL = " INSERT INTO r_epi_measles_item (" +
                "r_epi_measles_item_id " +
                ",b_health_epi_group_id " +
                ",epi_measles_item_description " +
                ") values (" +
                "'" + theEpiMeaslesItem.getObjectId() + "' " +
                ",'" + theEpiMeaslesItem.b_health_epi_group_id + "' " +
                ",'" + theEpiMeaslesItem.epi_measles_item_description + "' " +
                ")";
        
        return theConnectionInf.eUpdate(SQL);
    }

    /**
     *  สำหรับค้นหารายการวัคซีนของหัดเยอรมัน จากตำค้นที่ส่งมา
     *  @Param keyword เป็น ตัวแปรของคำค้น โดยจะนำไปค้นหาจากรายละเอียด
     *  @Author Ojika
     *  @Date 07/03/2549
     **/
    public Vector selectByKeyword(String keyword)throws Exception
    {
        SQL = " SELECT * FROM r_epi_measles_item " +
                " WHERE UPPER(epi_measles_item_description) like '%"+ keyword  +"%' ";
        
        return eQuery(SQL);
    }
    
    private java.util.Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new Vector();
        try{
            while(rs.next()) 
            {
                theEpiMeaslesItem = new EpiMeaslesItem();
                
                theEpiMeaslesItem.setObjectId(rs.getString(1));
                theEpiMeaslesItem.r_epi_measles_item_id = rs.getString(1);
                
                theEpiMeaslesItem.b_health_epi_group_id = rs.getString(2);
                theEpiMeaslesItem.epi_measles_item_description = rs.getString(3);
                
                vObject.add(theEpiMeaslesItem);
                theEpiMeaslesItem = null;
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
