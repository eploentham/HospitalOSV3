/*
 * QueueXray.java
 *
 * Created on 27 ���Ҥ� 2546, 22:46 �.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author  tong
 */
public class QueueXray  extends Persistent {
    
    
  
    public String visit_id; 
    public String number_order;
    public String last_service;
    public String assign_time;  
    public String patient_id;

    
    /** Creates a new instance of QueueXray */
    public QueueXray() {
    }
    
}
