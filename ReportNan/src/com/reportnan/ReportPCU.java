/*
 * Report12File.java
 *
 * Created on 7 กันยายน 2548, 10:10 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan;

import com.hospital_os.object.Version;
import com.hospital_os.usecase.connection.ModuleInfTool;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.usecase.connection.UpdateStatus;
import com.hosv3.control.HosControl;
import com.reportnan.control.Rp18Control;

/**
 *
 * @author tong(Padungrat)
 */
public class ReportPCU  implements ModuleInfTool
{
    /**Panel ของ 12 file*/
    /**Connection*/
    ConnectionInf theConnectionInf;
    /** Creates a new instance of Report12File */
    public ReportPCU() {
    }
    public javax.swing.JMenu getJMenu() {
        return null;
    }

    public javax.swing.JPanel getJPanel() {
//        return thePanelReport;
        return null;
    }

    public java.lang.String getNamePanel() {
        return ("ReportPCU");
    }

    public java.util.Vector getVectorJMenuItem(String str) {
        return null;
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
            System.out.println("Init instance of report 18 file");
            HosControl hc = (HosControl)obj;
            theConnectionInf = hc.theConnectionInf;
            Rp18Control rc = new Rp18Control(this.theConnectionInf,hc.theUS,hc.theHO);
//            thePanelReport = new PanelReportPCU();
            UpdateStatus us = hc.theUS;
//            thePanelReport.setControl(rc);
//            thePanelReport.setUpdateStatus(us);
        }
    }   

    public Object getObjectVersion() {
        return getVersion();
    }

    public java.util.Vector getVectorJPanel() {
        return null;
    }

    private Object getVersion() {
        return new Version();
    }



}
