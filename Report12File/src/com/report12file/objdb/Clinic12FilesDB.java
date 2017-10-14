/*
 * Clinic12FilesDB.java
 *
 * Created on 16 มีนาคม 2549, 14:45 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.objdb;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.report12file.object.Clinic12Files;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author Ojika
 */
public class Clinic12FilesDB
{
    
    /** Creates a new instance of Clinic12FilesDB */
    ConnectionInf theConnectionInf;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    Clinic12Files theClinic12Files;
    
    public Clinic12FilesDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
        theClinic12Files = new Clinic12Files();
    }
    
    /**
     *  สำหรับค้นหา clinic ของ 12 แฟ้ม
     *  @Param keyword เป็น ตัวแปรของคำค้น โดยจะนำไปค้นหาจากรายละเอียด
     *  @Author Ojika
     *  @Date 27/01/2550
     **/
    public Vector selectByKeyword(String keyword)throws Exception
    {
        SQL = " SELECT * FROM b_report_12files_std_clinic" +
                " WHERE UPPER(b_report_12files_std_clinic_id) like UPPER('%"+ keyword  +"%') " +
                " OR UPPER(report_clinic_12files_description) like UPPER('%"+ keyword  +"%') " +
                " OR UPPER(report_clinic_12files_description_en) like UPPER('%"+ keyword  +"%') ";
                           
        System.out.println(SQL);
        return eQuery(SQL);
    }
    
    private Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new Vector();
        try{
            while(rs.next()) 
            {
                theClinic12Files = new Clinic12Files();
                
                theClinic12Files.setObjectId(rs.getString(1));
                theClinic12Files.t_report_clinic_12files_id = rs.getString(1);
                theClinic12Files.report_clinic_12files_description = rs.getString(2);
                theClinic12Files.report_clinic_12files_description_en = rs.getString(3);
                
                vObject.add(theClinic12Files);
                theClinic12Files = null;
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
