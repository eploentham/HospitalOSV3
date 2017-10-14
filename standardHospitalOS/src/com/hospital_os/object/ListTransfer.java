/*
 * ListTransfer.java
 *
 * Created on 11 สิงหาคม 2547, 8:42 น.
 */
package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;

/**
 *
 * @author  tong
 */
public class ListTransfer extends Persistent {
    
    /** Creates a new instance of ListTransfer */
    public String locking;
    public String hn;
    public String vn;
    public String fname;
    public String lname;
    public String assign_time;
    public String name;
    public String queue_visit;
    public String description;
    public String visit_id;
    public String patient_id;
    public String queue;
    public String color;
    public String visit_type;
    public String servicepoint_id;
    public String doctor;
    public String patient_allergy;
    public String sex;
    public String prefix;
    public String bed;

    public String labstatus ;
    public String remain;
    public String order_id;
    public String specimen_code;
    /**
     * เก็บ Dx ของผู้ป่วย
     * padungrat(tong) 23/03/49,11:20
     */
    public String doctor_dx;
    public ListTransfer() {
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
         doctor_dx = "";
    }
    
}
