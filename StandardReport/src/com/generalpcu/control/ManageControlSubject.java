/*
 * ManageControlSubject.java
 *
 * Created on 7 กุมภาพันธ์ 2549, 14:41 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalpcu.control;
import com.hospital_os.usecase.connection.ConnectionInf;

import com.generalpcu.control.ManageControl;
import com.generalpcu.usecase.ManageSubject;
import com.hosv3.control.HosControl;
import com.hosv3.utility.connection.UpdateStatus;
/**
 *
 * @author pu
 */
public class ManageControlSubject
{
    ConnectionInf theConnectionInf;
    public ManageControl theManageControl;
    public ManageSubject theManageSubject;
    public UpdateStatus theUS;
    private HosControl theHC;
    
    /** Creates a new instance of ManageControlSubject */
    public ManageControlSubject(HosControl hc,ConnectionInf conf,UpdateStatus us)
    {
        theHC = hc;
        theConnectionInf = conf;
        theManageControl = new ManageControl(theHC, theConnectionInf);
        theManageSubject = new ManageSubject();
        theUS = us;
    }
    
}
