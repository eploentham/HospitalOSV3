/*
 * AppointmentTemplate.java
 *
 * Created on 10 �ԧ�Ҥ� 2549, 10:47 �.
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
    /**���͵�Ǫ��¹Ѵ*/
    public String template_name = "";
    /**�ѹ���Ѵ*/
    public String date = "";
    /**�֧�ѹ���*/
    public String date_to = "";
    /**���ҷ��Ѵ*/
    public String time = "";
    /**�Ѵ������*/
    public String aptype = "";
    /**�Ѵ��ѧ�ش��ԡ��*/
    public String service_point = "";
    /**ᾷ����Ѵ*/
    public String doctor = "";    
    /**��������´��ùѴ*/
    public String description = "";    
    /**key_id �ͧ���*/
    public String queue_visit_id  = "";    
    /**user ���ѹ�֡�Ѵ*/
    public String appoint_staff_record = "";
    /**�ѹ������ҷ��ӹѴ*/
    public String appoint_record_date_time = "";
    /**user �����䢹Ѵ*/
    public String appoint_staff_update = "";
    /**�ѹ������ҷ����䢹Ѵ*/
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
