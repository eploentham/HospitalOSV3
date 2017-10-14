/*
 * GeneralReport.java
 *
 * Created on 3 ตุลาคม 2548, 10:55 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport;
import com.hospital_os.usecase.connection.ModuleInfTool;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.object.Version;

import com.generalreport.utility.Language;
import com.generalreport.control.HosManage;
import com.generalreport.gui.panel.PanelGeneralReport;
import com.hosv3.control.HosControl;
import com.hosv3.utility.connection.UpdateStatus;
/**
 *
 * @author tong(Padungrat)
 */
public class GeneralReport implements ModuleInfTool
{
    /**PanelGeneralReport*/
    PanelGeneralReport thePanelGeneralReport;
    /**Connection*/
    ConnectionInf theConnectionInf;
    
    HosManage theHosManage;

    
    /** Creates a new instance of GeneralReport */
    public GeneralReport() {
    }
    
   
    public javax.swing.JMenu getJMenu() {
        return null;
    }

    public javax.swing.JPanel getJPanel() {
        return  thePanelGeneralReport;
    }

    public String getNamePanel() {
        return Language.getTextBundle("GeneralReport", 1);
    }

    public java.util.Vector getVectorJMenuItem(String str) {
        java.util.Vector  vc = null;
        if(thePanelGeneralReport != null)
        {
            vc  = thePanelGeneralReport.getMenuItem();
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
            HosControl hc = (HosControl) obj;
            UpdateStatus us = hc.theUS;
            theConnectionInf = hc.theConnectionInf;
            theHosManage = new HosManage(hc,theConnectionInf,us);
            thePanelGeneralReport = new PanelGeneralReport(theHosManage);
        }
    }   


    public Object getObjectVersion() {
        return this.thePanelGeneralReport.getVersion();
        
    }

    public java.util.Vector getVectorJPanel() {
        return null;
    }

}
