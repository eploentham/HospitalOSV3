/*
 * EpiAgeGroupItemDB.java
 *
 * Created on 29 มีนาคม 2549, 11:29 น.
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
public class EpiAgeGroupItemDB extends DBPersist
{
    
    /** Creates a new instance of EpiGroupItemDB */
    ConnectionInf theConnectionInf;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    EpiAgeGroupItem theEpiAgeGroupItem;
    
    public EpiAgeGroupItemDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theEpiAgeGroupItem = new EpiAgeGroupItem();
    }

    /**
     *  สำหรับลบรายการวัคซีนของช่วงอายุการรับวัคซีน
     *  @Author Ojika
     *  @Date 29/03/2549
     **/
    public int deleteByKeyID(String key_id)throws Exception
    {
        SQL = " DELETE FROM r_epi_age_group_item WHERE r_epi_age_group_item_id = '"+ key_id +"'";
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    /**
     *  สำหรับลบรายการวัคซีนของช่วงอายุการรับวัคซีน
     *  @Author Ojika
     *  @Date 29/03/2549
     **/
    public int deleteByEpiAgeGroupID(String EpiAgeGroupID)throws Exception
    {
        SQL = " DELETE FROM r_epi_age_group_item WHERE r_epi_age_group_id = '"+ EpiAgeGroupID +"'";
        
        return theConnectionInf.eUpdate(SQL);
    }

    /**
     *  สำหรับบันทึกรายการวัคซีนของช่วงอายุการรับวัคซีน
     *  @Author Ojika
     *  @Date 29/03/2549
     **/
    public int insertData(Object object)throws Exception
    {
        theEpiAgeGroupItem = (EpiAgeGroupItem)object;
        theEpiAgeGroupItem.generateOID(theEpiAgeGroupItem.idTable);
        
        SQL = " INSERT INTO r_epi_age_group_item (" +
                "r_epi_age_group_item_id " +
                ",r_epi_age_group_id " +
                ",r_epi_group_clinic_id " +
                ",b_health_epi_group_id " +
                ",epi_age_group_item_description " +
                ") values (" +
                "'" + theEpiAgeGroupItem.getObjectId() + "' " +
                ",'" + theEpiAgeGroupItem.r_epi_age_group_id + "' " +
                ",'" + theEpiAgeGroupItem.r_epi_group_clinic_id + "' " +
                ",'" + theEpiAgeGroupItem.b_health_epi_group_id + "' " +
                ",'" + theEpiAgeGroupItem.epi_age_group_item_description + "' " +
                ")";
        
        return theConnectionInf.eUpdate(SQL);
    }

    /**
     *  สำหรับค้นหารายการวัคซีนของช่วงอายุการรับวัคซีน จากตำค้นที่ส่งมา
     *  @Param epiGroupId เป็น รหัสของกลุ่มวัคซีน
     *  @Author Ojika
     *  @Date 29/03/2549
     **/
    public Vector selectByEpiAgeGroupId(String epiGroupId)throws Exception
    {
        SQL = " SELECT * FROM r_epi_age_group_item " +
                " WHERE r_epi_age_group_id like '%"+ epiGroupId  +"%' ";
        
        return eQuery(SQL);
    }
    
    private java.util.Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new Vector();
        try{
            while(rs.next()) 
            {
                theEpiAgeGroupItem = new EpiAgeGroupItem();
                
                theEpiAgeGroupItem.setObjectId(rs.getString(1));
                theEpiAgeGroupItem.r_epi_age_group_item_id = rs.getString(1);
                
                theEpiAgeGroupItem.r_epi_age_group_id = rs.getString(2);
                theEpiAgeGroupItem.r_epi_group_clinic_id = rs.getString(3);
                theEpiAgeGroupItem.b_health_epi_group_id = rs.getString(4);
                theEpiAgeGroupItem.epi_age_group_item_description = rs.getString(5);
                
                vObject.add(theEpiAgeGroupItem);
                theEpiAgeGroupItem = null;
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
