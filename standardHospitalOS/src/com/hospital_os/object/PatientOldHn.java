/*
 * PatientOldHn.java
 *
 * Created on 7 ตุลาคม 2548, 11:26 ?.
 */

package com.hospital_os.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author  henbe
 * @ยังไม่ได้ใช้:amp:2/6/2549
 */
public class PatientOldHn extends Persistent{
    
    public String patient_id;
    public String hn;
    public String description;
    public String other_patient_id;
    /** Creates a new instance of PatientOldHn */
    public PatientOldHn() {
        patient_id ="" ;
        hn="" ;
        description="" ;
        other_patient_id="" ;
    }
    
}
