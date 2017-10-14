/*
 * NCD.java
 *
 * Created on 14 มิถุนายน 2549, 17:56 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.Persistent;
/**
 *
 * @author amp
 */
public class NCD extends Persistent
{
    
    /** Creates a new instance of NCD */
    public String patient_id = "";
    public String ncd_group_id = "";
    public String ncd_number = ""; 
    public String staff_record = "";
    public String record_date_time = ""; 
    public String staff_modify = "";
    public String modify_date_time = "";
    
    public NCD()
    {
    }
    
}
