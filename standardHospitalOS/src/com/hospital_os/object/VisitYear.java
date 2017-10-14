/*
 * VisitYear.java
 *
 * Created on 10 พฤษภาคม 2548, 14:30 น.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 * เป็นตารางที่เพิ่มขึ้นมาใหม่ในการเป็น ปีพศ. เพื่อใช้ในการตรวจสอบ เลข VN
 * @author tong
 * @ since V2.0 b8
 */
public class VisitYear extends Persistent {
    
    /** Creates a new instance of VisitYear */
    public String visit_year;
    public VisitYear() {
        visit_year = "00";
    }
    
}
