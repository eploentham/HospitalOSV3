/*
 * EpiAgeGroupDB.java
 *
 * Created on 29 มีนาคม 2549, 16:54 น.
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
public class EpiAgeGroupDB
{
    
    /** Creates a new instance of EpiGroupDB */
    ConnectionInf theConnectionInf;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    EpiAgeGroup theEpiAgeGroup;
    
    public EpiAgeGroupDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theEpiAgeGroup = new EpiAgeGroup();
    }
    
    /**
     *  สำหรับบันทึกช่วงอายุการรับวัคซีน
     *  @Author Ojika
     *  @Date 29/03/2549
     **/
    public int insertData(Object object)throws Exception
    {
        theEpiAgeGroup = (EpiAgeGroup)object;
        theEpiAgeGroup.generateOID(theEpiAgeGroup.idTable);
        
        SQL = " INSERT INTO r_epi_age_group (" +
                "r_epi_age_group_id " +
                ",epi_age_group_number " +
                ",epi_age_group_description " +
                ",epi_age_group_start " +
                ",epi_age_group_end " +
                ",r_epi_age_group_type_id " +
                ",r_epi_group_clinic_id " +
                ") values (" +
                "'" + theEpiAgeGroup.getObjectId() + "' " +
                ",'" + theEpiAgeGroup.epi_age_group_number + "' " +
                ",'" + theEpiAgeGroup.epi_age_group_description + "' " +
                ",'" + theEpiAgeGroup.epi_age_group_start + "' " +
                ",'" + theEpiAgeGroup.epi_age_group_end + "' " +
                ",'" + theEpiAgeGroup.r_epi_age_group_type_id + "' " +
                ",'" + theEpiAgeGroup.r_epi_group_clinic_id + "' " +
                ")";
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    /**
     *  สำหรับแก้ไขช่วงอายุการรับวัคซีน
     *  @Author Ojika
     *  @Date 29/03/2549
     **/
    public int updateData(Object object)throws Exception
    {
        theEpiAgeGroup = (EpiAgeGroup)object;
        
        SQL = " UPDATE r_epi_age_group SET " +
                " epi_age_group_number = '" + theEpiAgeGroup.epi_age_group_number + "' " +
                ",epi_age_group_description = '" + theEpiAgeGroup.epi_age_group_description + "' " +
                ",epi_age_group_start = '" + theEpiAgeGroup.epi_age_group_start + "' " +
                ",epi_age_group_end = '" + theEpiAgeGroup.epi_age_group_end + "' " +
                ",r_epi_age_group_type_id = '" + theEpiAgeGroup.r_epi_age_group_type_id + "' " +
                ",r_epi_group_clinic_id = '" + theEpiAgeGroup.r_epi_group_clinic_id + "' " +
                " WHERE " +
                " r_epi_age_group_id = '" + theEpiAgeGroup.getObjectId() + "' ";
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    /**
     *  สำหรับลบช่วงอายุการรับวัคซีน
     *  @Author Ojika
     *  @Date 29/03/2549
     **/
    public int deleteByKeyID(String key_id)throws Exception
    {
        SQL = " DELETE FROM r_epi_age_group WHERE r_epi_age_group_id = '"+ key_id +"'";
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    /**
     *  สำหรับค้นหาช่วงอายุการรับวัคซีน จากคำค้นที่ส่งมา
     *  @Param keyword เป็น ตัวแปรของคำค้น โดยจะนำไปค้นหาจากรายละเอียด
     *  @Author Ojika
     *  @Date 29/03/2549
     **/
    public Vector selectByKeyword(String keyword)throws Exception
    {
        SQL = " SELECT * FROM r_epi_age_group " +
                " WHERE UPPER(epi_age_group_number) like '%"+ keyword  +"%' " +
                " OR UPPER(epi_age_group_description) like '%"+ keyword  +"%' ";
        
        return eQuery(SQL);
    }
    
    /**
     * ค้นหาช่วงอายุตาม number 
     * @Author Ojika
     * @Date 29/03/2549
     **/
    public Vector selectByNumber(String number)throws Exception
    {
        SQL = " SELECT * FROM r_epi_age_group " +
                " WHERE epi_age_group_number = '"+ number +"' " ;
        
        return eQuery(SQL);
    }
    
    private Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new Vector();
        try{
            while(rs.next()) 
            {
                theEpiAgeGroup = new EpiAgeGroup();
                
                theEpiAgeGroup.setObjectId(rs.getString(1));
                theEpiAgeGroup.r_epi_age_group_id = rs.getString(1);
                theEpiAgeGroup.epi_age_group_number = rs.getString(2);
                theEpiAgeGroup.epi_age_group_description = rs.getString(3);
                theEpiAgeGroup.epi_age_group_start = rs.getString(4);
                theEpiAgeGroup.epi_age_group_end = rs.getString(5);
                theEpiAgeGroup.r_epi_age_group_type_id = rs.getString(6);
                theEpiAgeGroup.r_epi_group_clinic_id = rs.getString(7);
                
                vObject.add(theEpiAgeGroup);
                theEpiAgeGroup = null;
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
