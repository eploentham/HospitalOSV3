/*
 * School.java
 *
 * Created on 10 ÁÔ¶Ø¹ÒÂ¹ 2548, 18:22 ¹.
 */

package com.pcu.object;

import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class School extends Persistent{
    private static String init = "";
    public String village_id = init;
    public String school_number = init;
    public String school_name = init;
    public String school_staff_record = init;
    public String school_staff_modify = init;
    public String school_staff_cancel = init;
    public String school_record_date_time = init;
    public String school_modify_date_time = init;
    public String school_cancel_date_time = init;
    public String school_close = init;
    public String school_close_date_time = init;
    public String school_close_note = init;
    public String school_active = init;
    
    /** Creates a new instance of School */
    public School() {
    }
    
}
