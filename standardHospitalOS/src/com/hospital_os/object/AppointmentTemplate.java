/*
 * AppointmentTemplate.java
 *
 * Created on 10 สิงหาคม 2549, 10:47 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class AppointmentTemplate extends Persistent implements CommonInf
{
    /**ชื่อตัวช่วยนัด*/
    public String template_name = "";
    /**วันที่นัด*/
    public String date = "";
    /**ถึงวันที่*/
    public String date_to = "";
    /**เวลาที่นัด*/
    public String time = "";
    /**นัดมาเพื่อ*/
    public String aptype = "";
    /**นัดไปยังจุดบริการ*/
    public String service_point = "";
    /**แพทย์ผู้นัด*/
    public String doctor = "";    
    /**รายละเอียดการนัด*/
    public String description = "";    
    /**key_id ของคิว*/
    public String queue_visit_id  = "";    
    /**user ผู้บันทึกนัด*/
    public String appoint_staff_record = "";
    /**วันและเวลาที่ทำนัด*/
    public String appoint_record_date_time = "";
    /**user ผู้แก้ไขนัด*/
    public String appoint_staff_update = "";
    /**วันและเวลาที่แก้ไขนัด*/
    public String appoint_update_date_time = "";
        
    static public final String DM = "2930000000001";
    static public final String HT = "2930000000002";
    static public final String H = "2930000000003";
    /**
     * Creates a new instance of AppointmentTemplate 
     */
    public AppointmentTemplate()
    {
    }

    public String getCode(){return getObjectId();}
    public String getName(){return template_name;}
    public String toString(){return template_name;}
    
}
