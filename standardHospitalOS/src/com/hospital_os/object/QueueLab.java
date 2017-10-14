/*
 * QueueXray.java
 *
 * Created on 27 ตุลาคม 2546, 22:46 น.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author  tong
 */
public class QueueLab extends Persistent 
{    
    public String visit_id; 
    public String number_order;
    public String last_service;
    public String assign_time;  
    public String patient_id;
    public String order_id = "";
    public String secret_code = "";
    
    /** Creates a new instance of QueueXray */
    public QueueLab() 
    {
    }
    
}
