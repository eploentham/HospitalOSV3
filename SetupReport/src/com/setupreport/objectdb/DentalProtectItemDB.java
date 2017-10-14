/*
 * DentalProtectItemDB.java
 *
 * Created on 7 มีนาคม 2549, 11:31 น.
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
public class DentalProtectItemDB extends DBPersist
{
    
    /** Creates a new instance of DentalProtectItemDB */
    ConnectionInf theConnectionInf;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    DentalProtectItem theDentalProtectItem;
    
    public DentalProtectItemDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theDentalProtectItem = new DentalProtectItem();
    }

    /**
     *  สำหรับค้นหารายการ item ของทันตกรรมป้องกันทั้งหมด
     *  @Author Ojika
     *  @Date 07/03/2549
     **/
    public Vector selectAll()throws Exception
    {
        SQL = " SELECT * FROM r_dental_protect_item ";
        
        return eQuery(SQL);
    }
    
    /**
     *  สำหรับลบรายการ item ของทันตกรรมป้องกัน
     *  @Author Ojika
     *  @Date 07/03/2549
     **/
    public int deleteByKeyID(String key_id)throws Exception
    {
        SQL = " DELETE FROM r_dental_protect_item WHERE r_dental_protect_item_id = '"+ key_id +"'";
        
        return theConnectionInf.eUpdate(SQL);
    }

    /**
     *  สำหรับบันทึกรายการ item ของทันตกรรมป้องกัน
     *  @Author Ojika
     *  @Date 07/03/2549
     **/
    public int insertData(Object object)throws Exception
    {
        theDentalProtectItem = (DentalProtectItem)object;
        theDentalProtectItem.generateOID(theDentalProtectItem.idTable);
        
        SQL = " INSERT INTO r_dental_protect_item (" +
                "r_dental_protect_item_id " +
                ",b_item_id " +
                ",dental_protect_item_number " +
                ",dental_protect_item_common_name " +
                ") values (" +
                "'" + theDentalProtectItem.getObjectId() + "' " +
                ",'" + theDentalProtectItem.b_item_id + "' " +
                ",'" + theDentalProtectItem.dental_protect_item_number + "' " +
                ",'" + theDentalProtectItem.dental_protect_item_common_name + "' " +
                ")";
        
        return theConnectionInf.eUpdate(SQL);
    }

    /**
     *  สำหรับค้นหารายการ item ของทันตกรรมป้องกันจากตำค้นที่ส่งมา
     *  @Param keyword เป็น ตัวแปรของคำค้น โดยจะนำไปค้นหาจากรายละเอียด หรือ number
     *  @Author Ojika
     *  @Date 07/03/2549
     **/
    public Vector selectByKeyword(String keyword)throws Exception
    {
        SQL = " SELECT * FROM r_dental_protect_item " +
                " WHERE UPPER(dental_protect_item_number) like '%"+ keyword  +"%' " +
                " OR UPPER(dental_protect_item_common_name) like '%"+ keyword  +"%' ";
        
        return eQuery(SQL);
    }
    
    private java.util.Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new Vector();
        try{
            while(rs.next()) 
            {
                theDentalProtectItem = new DentalProtectItem();
                
                theDentalProtectItem.setObjectId(rs.getString(1));
                theDentalProtectItem.r_dental_protect_item_id = rs.getString(1);
                
                theDentalProtectItem.b_item_id = rs.getString(2);
                theDentalProtectItem.dental_protect_item_number = rs.getString(3);
                theDentalProtectItem.dental_protect_item_common_name = rs.getString(4);
                
                vObject.add(theDentalProtectItem);
                theDentalProtectItem = null;
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
