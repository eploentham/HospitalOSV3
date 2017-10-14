/*
 * reverse_admit.java
 *
 * Created on 23 เมษายน 2547, 9:46 น.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author  tong
 */
public class ReverseAdmit extends Persistent 
{
    public String visit_id;
    public String user_code;
    public String  reverse_admit_date;
    public String reverse_admit_cause ;
    public String clinic; 
    public String ward;
    public String bed;
    public String an;
    public String begin_admit_time;
    public String reused;
    public String used = "0";


    /** Creates a new instance of reverse_admit */
    public ReverseAdmit() {
    }
    
}
