/*
 * ClinicMapDB.java
 *
 * Created on 16 มีนาคม 2549, 14:44 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.objdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.report12file.object.ClinicMap;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author Ojika
 */
public class ClinicMapDB
{
    
    /** Creates a new instance of ClinicDB */
    ConnectionInf theConnectionInf;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    ClinicMap theClinicMap;
    
    public ClinicMapDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theClinicMap = new ClinicMap();
    }
    
    /**
     *  สำหรับลบรายการ Map รายการ Clinic ของ 12 แฟ้ม
     *  @Author Ojika
     *  @Date 27/01/2550
     *  
     **/
    public int deleteByKeyID(String key_id)throws Exception
    {
        SQL = " DELETE FROM b_report_12files_map_clinic WHERE b_report_12files_map_clinic_id = '"+ key_id +"'";
        
        return theConnectionInf.eUpdate(SQL);
    }

    /**
     *  สำหรับบันทึกรายการ Map รายการ Clinic ของ 12 แฟ้ม
     *  @Author Ojika
     *  @Date 27/01/2550
     **/
    public int insertData(Object object)throws Exception
    {
        theClinicMap = (ClinicMap)object;
        theClinicMap.generateOID(theClinicMap.idTable);
        
        SQL = " INSERT INTO b_report_12files_map_clinic (" +
                "b_report_12files_map_clinic_id " +
                ",b_visit_clinic_id " +
                ",visit_clinic_number " +
                ",visit_clinic_description " +
                ",b_report_12files_std_clinic_id" +
                ") values (" +
                "'" + theClinicMap.getObjectId() + "' " +
                ",'" + theClinicMap.b_visit_clinic_id + "' " +
                ",'" + theClinicMap.visit_clinic_number + "' " +
                ",'" + theClinicMap.visit_clinic_description + "' " +
                ",'" + theClinicMap.t_report_clinic_12files_id + "' " +
                ")";
        
        return theConnectionInf.eUpdate(SQL);
    }

    /**
     *  สำหรับค้นหารายการ Map รายการ Clinic
     *  @Param clinic12FilesId เป็น รหัสของกลุ่มชนิดภาวะโภชนาการ
     *  @Author Ojika
     *  @Date 16/03/2549
     **/
    public Vector selectByClinic12FilesId(String clinic12FilesId)throws Exception
    {
        SQL = " SELECT * FROM b_report_12files_map_clinic " +
                " WHERE b_report_12files_std_clinic_id like '%"+ clinic12FilesId  +"%' " +
                " order by visit_clinic_number ";
        
        return eQuery(SQL);
    }
    
    public Vector selectMap()throws Exception
    {
        SQL = " select b_report_12files_map_clinic_id" +
                ",b_visit_clinic.b_visit_clinic_id" +
                ",b_visit_clinic.visit_clinic_number" +
                ",b_visit_clinic.visit_clinic_description" +
                ",b_report_12files_std_clinic_id " +
                " from b_visit_clinic" +
                " left join b_report_12files_map_clinic on b_visit_clinic.b_visit_clinic_id = b_report_12files_map_clinic.b_visit_clinic_id" +
                " order by b_visit_clinic.visit_clinic_number";
        return eQuery(SQL);
    }
    private java.util.Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new Vector();
        while(rs.next()) 
        {
            theClinicMap = new ClinicMap();
            theClinicMap.setObjectId(rs.getString(1));
            theClinicMap.t_report_map_clinic_id = rs.getString(1);
            theClinicMap.b_visit_clinic_id = rs.getString(2);
            theClinicMap.visit_clinic_number = rs.getString(3);
            theClinicMap.visit_clinic_description = rs.getString(4);
            theClinicMap.t_report_clinic_12files_id = rs.getString(5);
            vObject.add(theClinicMap);
            theClinicMap = null;
        }
        rs.close();
        return vObject;
    }

    /**
     *
     *  สำหรับลบรายการ Map รายการ Clinic ของ 12 แฟ้ม
     *  @Author Ojika
     *  @Date 27/01/2550
     *
     */
    public int deleteByCid(String t_report_clinic_12files_id) throws Exception {

        SQL = " DELETE FROM b_report_12files_map_clinic WHERE b_report_12files_std_clinic_id = '"+ t_report_clinic_12files_id +"'";
        return theConnectionInf.eUpdate(SQL);
    }
    
}
