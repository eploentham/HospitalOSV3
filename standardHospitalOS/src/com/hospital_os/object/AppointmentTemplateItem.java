/*
 * AppointmentTemplateItem.java
 *
 * Created on 10 �ԧ�Ҥ� 2549, 12:54 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class AppointmentTemplateItem extends Persistent
{
    /**key_id �ͧ��Ǫ��¹Ѵ*/
    public String appointment_template_id = "";
    /**key_id �ͧ item*/
    public String item_id = "";
    /**item_common_name*/
    public String item_common_name = "";
    
    /**
     * Creates a new instance of AppointmentTemplateItem 
     */
    public AppointmentTemplateItem()
    {
    }
    
}
