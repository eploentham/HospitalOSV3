/*
 * ManageControl.java
 *
 * Created on 3 ÁÔ¶Ø¹ÒÂ¹ 2549, 11:27 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.reportnan.objdb.ManageDB;
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
    public ReportNanControl theReportNanControl;
    
    /** Creates a new instance of ManageControl */
    public ManageControl(ConnectionInf conf)
    {
        theConnectionInf = conf;
        theManageDB = new ManageDB(theConnectionInf);
        
        theComboBoxControl = new ComboBoxControl(theManageDB);
        theExportControl = new ExportControl();
        theReportNanControl = new ReportNanControl(theManageDB);
    }
}
