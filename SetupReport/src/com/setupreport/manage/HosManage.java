/*
 * HosManage.java
 *
 * Created on 21 ตุลาคม 2548, 16:43 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.manage;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.connection.UpdateStatus;
import com.setupreport.manage.control.*;
/**
 *
 * @author tong(Padungrat)
 */
public class HosManage {
    public HosControl theHosControl;
    UpdateStatus theUS;
    /** Creates a new instance of HosManage */
    public HosManage(ConnectionInf c,UpdateStatus us) 
    {
        theUS = us;
        theHosControl = new HosControl(c,theUS); 
    }
    
}
