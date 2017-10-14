/*
 * AppointmentStatus.java
 *
 * Created on 19 กันยายน 2548, 23:02 น.
 */

package com.hospital_os.object;
//import com.hospital_os.utility.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.Constant;
/**
 *
 * @author  Administrator
 */
public abstract class AppointmentStatus {
    
    public static String WAIT="0";
    public static String COMPLETE="1";
    public static String MISS="2";
    public static String CANCEL="3";
    public static String BEFORE="4";
    public static String AFTER="5";
    //amp:9/8/2549
    public static String WAIT_STR = "รอการนัด";
    public static String COMPLETE_STR = "มาตามนัด";
    public static String MISS_STR = "ผิดนัด";
    public static String CANCEL_STR = "ยกเลิกนัด";
    public static String BEFORE_STR = "มาก่อนนัด";
    public static String AFTER_STR = "มาหลังนัด";
    
    public static String WAIT_FN = "/com/hospital_os/images/appointment.gif";
    public static String COMPLETE_FN = "/com/hospital_os/images/appointment_complete.GIF";
    public static String MISS_FN = "/com/hospital_os/images/appointment_miss.GIF";
    public static String CANCEL_FN = "/com/hospital_os/images/appointment_cancel.GIF";
    public static String BEFORE_FN = "/com/hospital_os/images/appointment_before.GIF";
    public static String AFTER_FN = "/com/hospital_os/images/appointment_after.GIF";
    
    /** Creates a new instance of AppointmentStatus */  
    //amp:9/8/2549

    public static String getString(String code)
    {
        if(code.equals(WAIT))
        {
            return (WAIT_STR);
        }
        else if(code.equals(COMPLETE))
        {
            return (COMPLETE_STR);
        }
        else if(code.equals(MISS))
        {
            return (MISS_STR);
        }
        else if(code.equals(CANCEL))
        {
            return (CANCEL_STR);
        }
        else if(code.equals(BEFORE))
        {
            return (BEFORE_STR);
        }
        else if(code.equals(AFTER))
        {
            return (AFTER_STR);
        }
        else
        {
            Constant.println("public static String getString(String code){ " + code);
            return "";
        }
    }
    
}
