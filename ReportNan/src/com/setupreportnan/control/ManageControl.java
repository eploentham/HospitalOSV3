/*
 * ManageControl.java
 *
 * Created on 7 กุมภาพันธ์ 2549, 14:41 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreportnan.control;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.setupreportnan.control.ManageDB;
/**
 *
 * @author pu
 */
public class ManageControl
{
    public ConnectionInf theConnectionInf;
    public ManageDB theManageDB; 
    public DialogControl theDialogControl;
    public ServicePointTypeControl theServicePointTypeControl;
    public ServicePointTypeMapControl theServicePointTypeMapControl;
    
    public ComboBoxControl theComboBoxControl;
    public NCDItemGroupControl theNCDItemGroupControl;
    public OPDItemControl theOPDItemControl;
    public OperatingItemControl theOperatingItemControl;
    public ReferHospitalControl theReferHospitalControl;
    
    /** Creates a new instance of ManageControl */
    public ManageControl(ConnectionInf conf)
    {
        theConnectionInf = conf;
        theManageDB = new ManageDB(theConnectionInf);
        theDialogControl = new DialogControl(theManageDB);
        theServicePointTypeControl = new ServicePointTypeControl(theManageDB); 
        theServicePointTypeMapControl = new ServicePointTypeMapControl(theManageDB); 
        
        theComboBoxControl = new ComboBoxControl(theManageDB); 
        theNCDItemGroupControl = new NCDItemGroupControl(theManageDB);
        theOPDItemControl = new OPDItemControl(theManageDB); 
        theOperatingItemControl = new OperatingItemControl(theManageDB);
        theReferHospitalControl = new ReferHospitalControl(theManageDB);    
        
    }
    
}
