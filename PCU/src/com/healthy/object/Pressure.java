/*
 * Pressure.java
 *
 * Created on 5 เมษายน 2549, 15:23 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.healthy.object;

import com.hospital_os.utility.XPersistent;

import java.util.*;
/**
 *
 * @author hospitalos5
 */
public class Pressure extends XPersistent {
    
    public String pressure_id = "";
    public String idy = "";
    public String date_serv = "";
    public String systolic = "";
    public String diastolic = "";
    public String family_id = "";
    public String result = "";
    public String record_time = "";
    public String modify_time = "";
    public String cancel_time = "";
    public String staff_record = "";
    public String staff_modify = "";
    public String staff_cancel = "";
    public String active = "";
    
    /** Creates a new instance of Pressure */
    public Pressure() {
    }
    
}
