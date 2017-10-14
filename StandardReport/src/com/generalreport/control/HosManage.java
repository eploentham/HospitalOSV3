/*
 * HosManage.java
 *
 * Created on 3 ตุลาคม 2548, 10:58 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalreport.control.HosControl;
import com.hosv3.utility.connection.UpdateStatus;
/**
 *
 * @author tong(Padungrat)
 */
public class HosManage {
    
    public HosControl theHosControl;
    public UpdateStatus theUS;
    /** Creates a new instance of HosManage */
    public HosManage(com.hosv3.control.HosControl theHC,ConnectionInf theConnectionInf,UpdateStatus us) {
        theHosControl = new HosControl(theHC, theConnectionInf);
        theUS = us;
    }
    
}
