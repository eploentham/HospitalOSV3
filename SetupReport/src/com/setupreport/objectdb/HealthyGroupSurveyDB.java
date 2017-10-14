/*
 * HealthyGroupSurveyDB.java
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
public class HealthyGroupSurveyDB
{
    
    /** Creates a new instance of HealthyGroupSurveyDB */
    ConnectionInf theConnectionInf;
    String SQL = "";
    ResultSet rs = null;
    Vector vObject ;
    HealthyGroupSurvey theHealthyGroupSurvey;
    
    public HealthyGroupSurveyDB(ConnectionInf connectionInf)
    {
        theConnectionInf = connectionInf;
    }
    
    /**
     *  สำหรับบันทึกรายการสำรวจที่อยู่ในกลุ่มการตรวจสุขภาพ
     *  @Author Ojika
     *  @Date 09/03/2549
     **/
    public int insertData(Object object)throws Exception
    {
        theHealthyGroupSurvey = (HealthyGroupSurvey)object;
        theHealthyGroupSurvey.generateOID(theHealthyGroupSurvey.idTable);
        
        SQL = " INSERT INTO r_healthy_group_survey (" +
                "r_healthy_group_survey_id " +
                ",r_healthy_subgroup_id " +
                ",b_health_survey_id " +
                ",healthy_group_survey_description " +
                ") values (" +
                "'" + theHealthyGroupSurvey.getObjectId() + "' " +
                ",'" + theHealthyGroupSurvey.r_healthy_subgroup_id + "' " +
                ",'" + theHealthyGroupSurvey.b_health_survey_id + "' " +
                ",'" + theHealthyGroupSurvey.healthy_group_survey_description + "' " +
                ")";
        
        return theConnectionInf.eUpdate(SQL);
    }
 
    /**
     *  สำหรับลบรายการสำรวจที่อยู่ในกลุ่มการตรวจสุขภาพ
     *  @Author Ojika
     *  @Date 09/03/2549
     **/
    public int deleteByKeyID(String key_id)throws Exception
    {
        SQL = " DELETE FROM r_healthy_group_survey WHERE r_healthy_group_survey_id = '"+ key_id +"'";
        
        return theConnectionInf.eUpdate(SQL);
    }
    
    /**
     *  สำหรับค้นหา รายการสำรวจที่อยู่ในกลุ่มการตรวจสุขภาพตาม 11รง5 ส่วนที่ 3
     *  @Param healthyGroupId เป็น ตัวแปรของรหัสข้อมูลของกลุ่มการตรวจสุขภาพตาม 11รง5 ส่วนที่ 3
     *  @Author Ojika
     *  @Date 09/03/2549
     **/
    public Vector selectByHealthySubgroupId(String healthySubgroupId)throws Exception
    {
        SQL = " SELECT * FROM r_healthy_group_survey " +
                " WHERE r_healthy_subgroup_id = '"+ healthySubgroupId  +"' ";
        
        return eQuery(SQL);
    }
    
    private Vector eQuery(String sql)throws Exception
    {
        rs = theConnectionInf.eQuery(sql);
        vObject = new Vector();
            while(rs.next()) 
            {
                theHealthyGroupSurvey = new HealthyGroupSurvey();
                
                theHealthyGroupSurvey.setObjectId(rs.getString(1));
                
                theHealthyGroupSurvey.r_healthy_group_survey_id = rs.getString(1);
                theHealthyGroupSurvey.r_healthy_subgroup_id = rs.getString(2);
                theHealthyGroupSurvey.b_health_survey_id = rs.getString(3);
                theHealthyGroupSurvey.healthy_group_survey_description = rs.getString(4);
                
                vObject.add(theHealthyGroupSurvey);
                theHealthyGroupSurvey = null;
            }
            
            rs.close();
        return vObject;
    }
}
