/*
 * ManageControl.java
 *
 * Created on 7 กุมภาพันธ์ 2549, 14:41 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalpcu.control;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.generalpcu.objdb.ManageDB;
import com.hosv3.control.HosControl;
/**
 *
 * @author pu
 */
public class ManageControl
{
    public ConnectionInf theConnectionInf;
    public ManageDB theManageDB; 
    
    public ComboBoxControl theComboBoxControl;
    public ExportControl theExportControl;
    public GeneralPCUControl theGeneralPCUControl;
    private final HosControl theHC;
    
    /** Creates a new instance of ManageControl */
    public ManageControl(HosControl hc,ConnectionInf conf)
    {
        theHC = hc;
        theConnectionInf = conf;
        theManageDB = new ManageDB(theConnectionInf);
        
        theComboBoxControl = new ComboBoxControl(theManageDB); 
        theExportControl = new ExportControl();
        theGeneralPCUControl = new GeneralPCUControl(theHC,theManageDB);
        
    }
    
}
