/*
 * HosManage.java
 *
 * Created on 28 กรกฎาคม 2548, 13:34 น.
 */

package com.standardReport.control;
//import com.report501.usecase.connection.ConnectionInf;
import com.hospital_os.usecase.connection.ConnectionInf;

/**
 *
 * @author Noom
 */
public class HosManage {
    public HosControl theHosControl;  
    
    /** Creates a new instance of HosManage */
    public HosManage() {
    }
    
     public HosManage(com.hosv3.control.HosControl c) {
         theHosControl = new HosControl(c);  
    }
}
