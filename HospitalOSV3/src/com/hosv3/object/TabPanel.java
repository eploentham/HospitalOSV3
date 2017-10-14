/*
 * TabPanel.java
 *
 * Created on 28 กรกฎาคม 2548, 16:35 น.
 */
package com.hosv3.object;
import com.hospital_os.utility.XPersistent;
import com.hospital_os.usecase.connection.*;
/**
 *
 * @author  sumo
 */
public class TabPanel extends XPersistent implements CommonInf
{
	
	static final long serialVersionUID = 0;
    public static String FRAME_MAIN_TAB_SOCIAL_DATA = "1";
     /** Social_tab */
    public static String FRAME_MAIN_TAB_VISIT = "2";
     /** Visit_tab*/
    public static String FRAME_MAIN_TAB_VITAL_SIGN = "3";
     /** Vital_tab */
    public static String FRAME_MAIN_TAB_ORDER_LIST = "4";
     /** Order_tab */
    public static String FRAME_MAIN_TAB_DIAGNOSIS = "5";
     /** DIAGNOSIS_tab */
    public static String FRAME_MAIN_TAB_BILLING = "6";
     /** Billing_tab */
    public static String FRAME_MAIN_TAB_LAB = "7";
     /** Lab_tab */
    public static String FRAME_MAIN_TAB_XRAY = "8";
     /** Xray_tab */
    public String description;
   public TabPanel() 
   {
   }
   
    public String getCode() {
        return getObjectId();
    }

    public String getName() {
        return description;
    }
    public String toString(){
        return description;
    }
   
}
