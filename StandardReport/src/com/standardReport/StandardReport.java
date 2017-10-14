/*
 * StandardReport.java
 *
 * Created on 5 กันยายน 2548, 16:45 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport;
import com.generalpcu.control.ManageControlSubject;
import com.generalpcu.control.RpPcuControl;
import com.generalpcu.gui.panel.PanelGeneralPCU;
import com.generalpcu.gui.panel.PanelRpPCU;
import com.generalreport.gui.panel.PanelGeneralReport;
import com.hosv3.utility.connection.UpdateStatus;
import com.standardReport.gui.panel.PanelStandardReport;
import com.standardReport.control.HosManage;
import com.standardReport.utility.*;

import com.hospital_os.usecase.connection.ModuleInfTool;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.control.HosControl;

/**
 *
 * @author tong(Padungrat)
 */
public class StandardReport implements ModuleInfTool
{
    /**เป็น Object ของ Panel ที่ใช้ในการแสดงข้อมูล*/
    private PanelStandardReport thePanelStandardReport;
    /**เป็น Object ของ ConnectionInf เพื่อรับ connection */ 
    /**เป็น Object ของ Manager ที่ใช้ในการควบคุมการทำงาน*/
    private HosManage theManager;

    private PanelGeneralPCU thePanelGeneralPCU;
    private PanelRpPCU thePanelReportPCU;

    private PanelGeneralReport thePanelGeneralReport;
    public StandardReport() {
        
    }

     public javax.swing.JMenu getJMenu() {
        return null;
    }

    public javax.swing.JPanel getJPanel() {
        return thePanelStandardReport;
    }

    public String getNamePanel() {
        return Gutil.getTextBundle("StandardReport");
    }

    public java.util.Vector getVectorJMenuItem(String str) {
    
        java.util.Vector  vc = null;
        if(thePanelStandardReport != null)
        {
            vc  = thePanelStandardReport.getMenuItem();
        }
        
        return vc;
    }

    public java.util.Vector getVectorSetupModule() {
        return null;
    }

    public boolean isInMenuStandard() {
        return false;
    }

    public boolean isJMenuVisible(String str) {
        return false;
    }

    public boolean isJPanelVisible(String str) {
        return true;
    }

    public boolean isJTreeVisible(String str) {
        return false;
    }

    public void setHosControl(Object obj) {
        if(obj instanceof HosControl)
        {
            HosControl hc = (HosControl)obj;
            hc.theConnectionInf.open();
            com.generalreport.control.HosManage theHosManage = 
                    new com.generalreport.control.HosManage(hc,hc.theConnectionInf,hc.theUS);
            thePanelGeneralReport = new PanelGeneralReport(theHosManage);
            
            ManageControlSubject theMCS = new ManageControlSubject(hc,hc.theConnectionInf,hc.theUS);
            thePanelGeneralPCU = new PanelGeneralPCU(theMCS);   

            RpPcuControl rc = new RpPcuControl(hc);
            thePanelReportPCU = new PanelRpPCU();
            thePanelReportPCU.setControl(rc);

            theManager = new HosManage(hc);
            thePanelStandardReport = new PanelStandardReport(
                    theManager,thePanelGeneralPCU,thePanelGeneralReport,thePanelReportPCU);
        }
    }
    
    public Object getObjectVersion() {
       return thePanelStandardReport.getVersion();
    }

    public java.util.Vector getVectorJPanel() {
        return null;
    }
    
}
