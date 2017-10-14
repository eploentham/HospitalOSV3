/*
 * QueryReport.java
 *
 * Created on 5 กันยายน 2548, 16:45 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportquery;
import com.hosv3.utility.connection.UpdateStatus;
import com.reportquery.gui.panel.PanelReportQuery;
import com.reportquery.manage.HosManage;
import com.reportquery.utility.Language;

import com.hospital_os.usecase.connection.ModuleInfTool;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.control.HosControl;

/**
 *
 * @author tong(Padungrat)
 */
public class QueryReport implements ModuleInfTool
{
    /**เป็น Object ของ Panel ที่ใช้ในการแสดงข้อมูล*/
    private PanelReportQuery thePanelReportQuery;
    /**เป็น Object ของ ConnectionInf เพื่อรับ connection */
//    private ConnectionInf theConnectionInf;
    /**
     * เป็น Object ของ HosManage ที่ใช้ในการควบคุมการทำงาน
     */
    private HosManage theManager;
    private HosControl theHC;
//    private UpdateStatus theUS;
    public QueryReport() {
        
    }

     public javax.swing.JMenu getJMenu() {
        return null;
    }

    public javax.swing.JPanel getJPanel() {
        return thePanelReportQuery;
    }

    public String getNamePanel() {
        return Language.getTextBundle("QueryReport", 1);
    }

    public java.util.Vector getVectorJMenuItem(String str) {
        
        java.util.Vector  vc = null;
        if(thePanelReportQuery != null)
        {
            vc  = thePanelReportQuery.getMenuItem();
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
            theHC = (HosControl)obj;
            theManager = new HosManage(theHC);
            thePanelReportQuery = new PanelReportQuery(theManager,theHC.theUS);
        }
    }

    public Object getObjectVersion() {
        return thePanelReportQuery.getVersion();
    }

    public java.util.Vector getVectorJPanel() {
        return null;
    }
    
}
