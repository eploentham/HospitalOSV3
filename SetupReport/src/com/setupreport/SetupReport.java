/*
 * SetupReport.java
 *
 * Created on 21 ตุลาคม 2548, 16:17 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport;
import com.hospital_os.usecase.connection.ModuleInfTool;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.control.HosControl;
import com.setupreport.gui.panel.PanelMainSetup;

import com.setupreport.manage.HosManage;
import com.setupreport.utility.Language;

/**
 *
 * @author tong(Padungrat)
 */
public class SetupReport implements ModuleInfTool{
    
    /**เป็น Object ของ ConnectionInf เพื่อรับ connection */
    private ConnectionInf theConnectionInf;
    /**เป็น Object ของ Manager ที่ใช้ในการควบคุมการทำงาน*/
    private HosManage theManager;
    int language =1;

    private HosControl theHC;

    private PanelMainSetup thePanelMainSetup;
    public SetupReport() {
    }
    public javax.swing.JMenu getJMenu() {
            return null;
    }

    
    public javax.swing.JPanel getJPanel() {
        return null;//thePanelStandardReport;
    }

    public String getNamePanel() {
        
        return Language.getTextBundle("SetupReports",language);
    }

    public java.util.Vector getVectorJMenuItem(String str) {
    
        java.util.Vector  vc = null;
        return vc;
    }

    public java.util.Vector getVectorSetupModule() {
        return thePanelMainSetup.getVectorSetupPanel();
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
        return true;
    }

    public void setHosControl(Object obj) 
    {
        System.out.println("HenbeTEst rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        if(obj instanceof ConnectionInf)
        {
            theConnectionInf = (ConnectionInf)obj;
        }
        if(obj instanceof HosControl)
        {
            theHC = (HosControl)obj;
            theManager = new HosManage(theHC.theConnectionInf,theHC.theUS);  
            thePanelMainSetup = new PanelMainSetup(theManager);
        }
    }
    
    public Object getObjectVersion() {
       return null;//thePanelStandardReport.getVersion();
    }

    public java.util.Vector getVectorJPanel() {
        return null;
    }
}
