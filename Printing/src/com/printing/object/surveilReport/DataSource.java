/*
 * DataSource.java
 *
 * Created on 08 ธันวาคม 2547, 15:14 น.
 */

package com.printing.object.surveilReport;

/**
 *
 * @author  ojika
 *  เป็น Object ที่ใช้ในการ ให้ค่ากับตัวแปร ที่เป็น ประเภท Field 
 *  โดยจะใช้คู่กับ DataSourcePrintChronicReport
 *  
 */
public class DataSource {
    
    /** Creates a new instance of DataSource */
        public String hn;
        public String vn;
        public String fname;
        public String lname;
        public String sex;
        public String age;
        public String icd10;
        public String date_dx;
        public String date_discharge;
        public String status;        
        public String date_update;
        public String patient_address;
        
    public DataSource() {
    }
    
}
