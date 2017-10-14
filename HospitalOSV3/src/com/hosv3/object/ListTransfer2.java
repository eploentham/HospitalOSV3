/*
 * ListTransfer2.java
 *
 * Created on 23 กันยายน 2548, 15:42 น.
 */

package com.hosv3.object;
import com.hospital_os.object.ListTransfer;

/**
 * 
 * @author kingland
 * @deprecated ให้ใช้ ListTransfer เพราะได้เพิ่ม field ตามของใหม่แล้ว
 */
public class ListTransfer2 extends ListTransfer{
    
	static final long serialVersionUID = 0;
//    public String labstatus ;
//    public String remain;
//    public String order_id;
//    public String specimen_code;
    /** Creates a new instance of ListTransfer2 */
    public ListTransfer2() {
         hn = "";
         vn = "";
         fname = "";
         lname = "";
         assign_time = "";
         name = "";
         queue_visit = "";
         description = "";
         visit_id = "";
         patient_id = "";
         queue = "";
         color = "";
         visit_type = "";
         servicepoint_id = "";
         doctor = "";
         patient_allergy = "";
         sex = "";
         prefix = "";
         bed = "";
         labstatus = "";
         remain = "";
         order_id = "";
         specimen_code = "";
         //padungrat(tong) 23/03/49,11:20
         doctor_dx = "";
    }
    
}
