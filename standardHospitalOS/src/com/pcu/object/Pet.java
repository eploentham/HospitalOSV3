/*
 * Pet.java
 *
 * Created on 11 ÁÔ¶Ø¹ÒÂ¹ 2548, 12:52 ¹.
 */

package com.pcu.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class Pet extends Persistent {
    private static String init = "";
    public String home_id = init;
    public String pet_id = init;
    public String name = init;
    public String sex = init;
    public String birthday = init;
    public String vaccine_lastdate = init;
    public String birth_control_lastdate= init;
    public String staff_record = init;
    public String staff_modify = init;
    public String staff_cancel = init;
    public String record_date_time = init;
    public String modify_date_time = init;
    public String cancel_date_time = init;
    public String pet_active = init;
   

    /** Creates a new instance of Pet */
    public Pet() {
    }
    
}
