/*
 * Report12File.java
 *
 * Created on 7 2548, 10:10 
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.usecase.connection.ModuleInfTool;
import com.hospital_os.utility.IconData;
import com.hosv3.HosSet;
import com.hosv3.control.HosControl;
import com.hosv3.control.MapCon;
import com.hosv3.gui.component.PanelSetupXPer;
import com.hosv3.gui.panel.detail.PanelMapLookup;
import com.hosv3.object.HosObject;
import com.hosv3.utility.connection.UpdateStatus;
import com.report12file.control.MapControl;
import com.report12file.control.ReportControl;
import com.report12file.control.Rp12Control;

import com.report12file.control.Rp12Control2;
import com.report12file.gui.PanelMapADPCode;
import com.report12file.gui.PanelMapCharItem;
import com.report12file.gui.PanelReport12File;
import com.report12file.utility.Language;
import com.report12file.utility.Report12FileData;
import java.util.Vector;
import javax.swing.tree.DefaultMutableTreeNode;
/**
 *
 * @author tong(Padungrat)
 */
public class Report12File  implements ModuleInfTool
{
    /**Panel ๏ฟฝอง 12 file*/
    String PANEL_SETUP_RP1253_ADPCODE = ("จับคู่รายการตรวจ รง12/53");
    String PANEL_SETUP_CLINIC_MAP = ("จับคู่คลีนิก");
    String PANEL_SETUP_CHARITEM_MAP = ("จับคู่ชนิดของบริการที่คิดค่ารักษา(CharItem)");
    PanelReport12File thePanelReport12File;
    /**Connection*/
    ConnectionInf theConnectionInf;

    private UpdateStatus theUS;
    private Vector vSetupModule;
    private HosControl theHC;
    private HosObject theHO;

    
    public Report12File() {
    }
    public javax.swing.JMenu getJMenu() {
        return null;
    }

    public javax.swing.JPanel getJPanel() {
        return thePanelReport12File;
    }

    public String getNamePanel() {
        return Language.getTextBundle("Report12File", 1);
    }

    public java.util.Vector getVectorJMenuItem(String str) {
        java.util.Vector  vc = null;
        if(thePanelReport12File != null){
            vc  = thePanelReport12File.getMenuItem(); 
        }
        
        return vc;
    }

    public java.util.Vector getVectorSetupModule() { 
        if(vSetupModule!=null)
            return vSetupModule;
        vSetupModule = new Vector();
        DefaultMutableTreeNode tree_main = new DefaultMutableTreeNode(new IconData(PanelSetupXPer.ICONs
                , "รายงาน 12/2553"));
            PanelMapLookup panel = new PanelMapLookup();
            panel.setTitle(PANEL_SETUP_CLINIC_MAP);
            panel.setControl(new MapControl(Report12FileData.MAP_CLINIC_12FILE,theUS,theHC.theConnectionInf),theUS,theHO);
            vSetupModule.add(PanelSetupXPer.getSetupModule(panel,PANEL_SETUP_CLINIC_MAP,tree_main));
            
            PanelMapADPCode panelA = new PanelMapADPCode();
            panelA.setTitle(PANEL_SETUP_RP1253_ADPCODE + " (ค่าใช้จ่ายที่ยังไม่จัดหมวด)");
            panelA.setControl(new MapControl(Report12FileData.MAP_ADPCODE,theUS,theHC.theConnectionInf),theUS,theHO,theHC.theLookupControl);
            vSetupModule.add(PanelSetupXPer.getSetupModule(panelA,PANEL_SETUP_RP1253_ADPCODE,tree_main));

            PanelMapCharItem panelC = new PanelMapCharItem();
            panelC.setTitle(PANEL_SETUP_CHARITEM_MAP);
            panelC.setControl(new MapControl(Report12FileData.MAP_CHARITEM_12FILE,theUS,theHC.theConnectionInf),theUS,theHO,theHC.theLookupControl);
            vSetupModule.add(PanelSetupXPer.getSetupModule(panelC,PANEL_SETUP_CHARITEM_MAP,tree_main));

        return vSetupModule;
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
        if(obj instanceof HosControl){
            theHC = (HosControl)obj;
            theHO = theHC.theHO;
            theUS = theHC.theUS;
            theConnectionInf = theHC.theConnectionInf;
            ReportControl rc = new ReportControl(theConnectionInf);
            Rp12Control rc1 = new Rp12Control(theConnectionInf,theUS,theHC.theHO,rc.theClinicControl);
            Rp12Control2 rc2 = new Rp12Control2(theConnectionInf,theUS,theHC.theHO);
            thePanelReport12File = new PanelReport12File();
            thePanelReport12File.setConnection(rc,rc1,rc2);

            thePanelReport12File.setUpdateStatus(theUS);
            thePanelReport12File.setEnableGUI(true);
        }
    }   


    public Object getObjectVersion() {
        return this.thePanelReport12File.getVersion();
        
    }

    public java.util.Vector getVectorJPanel() {
        return null;
    }

    public static void main(String args[]){

        String[] a = {"-module=com.report12file.Report12File"};
        HosSet.main(a);
    }
}
 
