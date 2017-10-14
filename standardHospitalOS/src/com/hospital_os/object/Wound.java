/*
 * Wound.java
 *
 * Created on 17 ÁÔ¶Ø¹ÒÂ¹ 2549, 17:24 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author Administrator
 */
public class Wound extends Persistent{
    
    public String wound_type = "";
    public String wound_position = "";
    public String describe = "";
    public String width = "";
    public String longs = "";
    public String deep = "";
    public String patient_id = "";
    public String visit_id = "";
    public String position_x = "";
    public String position_y = "";
    public String picture = "";
    public String record_date_time = "";
    public String staff_record = "";
    public String modify_date_time = "";
    public String staff_modify = "";    
    /** Creates a new instance of Wound */
    public Wound() {
    }
    
}
