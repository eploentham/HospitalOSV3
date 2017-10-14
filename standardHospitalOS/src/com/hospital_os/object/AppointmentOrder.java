/*
 * AppointmentOrder.java
 *
 * Created on 23 กุมภาพันธ์ 2549, 14:47 น.
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
public class AppointmentOrder extends Persistent
{
    public String patient_id;
    public String appointment_id;
    public String item_id;
    public String item_common_name;
    
    /** Creates a new instance of AppointmentOrder */
    public AppointmentOrder()
    {
    }
    
}
