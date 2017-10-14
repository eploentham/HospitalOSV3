/*
 * EmergencyStatus.java
 *
 * Created on 6 ÁÔ¶Ø¹ÒÂ¹ 2549, 18:42 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.object;

/**
 *
 * @author Administrator
 */
public class EmergencyStatus {
    
        public static String URGENT = "2";
        public static String NON_URGENT = "1";
        public static String EMERGENCY = "3";
        public static String UNDEFINE = "0";
        
    /** Creates a new instance of EmergencyStatus */
    public EmergencyStatus() {
    }
    public static String getDescription(String er){
        if(er.equals(URGENT))
            return "Urgent";
        else if(er.equals(NON_URGENT))
            return "Not Urgent";
        else if(er.equals(EMERGENCY))
            return "Emergency";
        return "";
    }
}
