/*
 * PanelMainSetup.java
 *
 * Created on 13 มิถุนายน 2549, 14:05 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.gui.panelsetup;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.util.Vector;
import com.reportnan.subject.ManageControlSubjectSetup;
import com.reportnan.utility.Language;
import com.hospital_os.object.SetupModule;
import com.hospital_os.utility.IconData;
/**
 *
 * @author pu
 */
public class PanelMainSetup
{
    ManageControlSubjectSetup theMCS;
    Vector vSetupModule;
    SetupModule theSetupModule;
    String panelName;
    Icon ICON = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/com/hospital_os/images/MainProgram16.gif"));
    DefaultMutableTreeNode m_NCD = new DefaultMutableTreeNode(new IconData(ICON, Language.getTextBundle("กลุ่มรายงานทั่วไป 2")));

    public PanelSetupServicePointType thePanelSetupServicePointType;
    public PanelSetupReferHospital thePanelSetupReferHospital;
    public PanelSetupOPDItem thePanelSetupOPDItem;
    public PanelSetupNCDItemGroup thePanelSetupNCDItemGroup;
    public PanelSetupOperatingItem thePanelSetupOperatingItem;
    
    /** Creates a new instance of PanelMainSetup */
    public PanelMainSetup(ManageControlSubjectSetup mcs)
    {
        theMCS = mcs;
        vSetupModule = new Vector();
        setPanelSetupReportNan();
    }
    
    private void setPanelSetupReportNan()
    {
        System.out.println("private void setPanelSetupReportNan()");
        setPanelSetupServicePointType();
        setPanelSetupReferHospital();
        setPanelSetupOPDItem();
        setPanelSetupNCDItemGroup();
        setPanelSetupOperatingItem(); 
    }
    
    /**
     *ใช้ในการกำหนดข้อมูลลง Vector
     */
    private void setPanelSetup(String panelName,Object panel
            ,DefaultMutableTreeNode mothorNode,DefaultMutableTreeNode childNode)
    {
        System.out.println("setPanelSetup" + panelName);
        
        theSetupModule = new  SetupModule();
        theSetupModule.theMainTreeNode = mothorNode;
        theSetupModule.theTreeNode = mothorNode;
        
        theSetupModule.thePanel = (JPanel)panel;
        theSetupModule.pname = Language.getTextBundle(panelName);
        vSetupModule.add(theSetupModule);
    }
    
    private void setPanelSetupServicePointType()
    {
        thePanelSetupServicePointType = new PanelSetupServicePointType(theMCS);
        panelName = thePanelSetupServicePointType.getPanelName();
        setPanelSetup(panelName, thePanelSetupServicePointType,m_NCD,null);
    }
    
    /**
     * Setup สถานพยาบาลที่ต้องการดูข้อมูลการ Refer
     * @Author Ojika
     * @Date 19/06/2549
     */
    private void setPanelSetupReferHospital()
    {
        thePanelSetupReferHospital = new PanelSetupReferHospital(theMCS);
        panelName = thePanelSetupReferHospital.getPanelName();  
        setPanelSetup(panelName, thePanelSetupReferHospital,m_NCD,null);
    }
    
    /**
     * Setup รายการตรวจรักษาที่ OPD ต้องการดูรายงาน
     * @Author Ojika
     * @Date 19/06/2549
     */
    private void setPanelSetupOPDItem()
    {
        thePanelSetupOPDItem = new PanelSetupOPDItem(theMCS);
        panelName = thePanelSetupOPDItem.getPanelName();
        setPanelSetup(panelName, thePanelSetupOPDItem,m_NCD,null);
    }
    
    /**
     * Setup กลุ่มรายการ NCD
     * @Author Ojika
     * @Date 19/06/2549
     */
    private void setPanelSetupNCDItemGroup()
    {
        thePanelSetupNCDItemGroup = new PanelSetupNCDItemGroup(theMCS);
        panelName = thePanelSetupNCDItemGroup.getPanelName();
        setPanelSetup(panelName, thePanelSetupNCDItemGroup,m_NCD,null);
    }
    
    /**
     * Setup รายการหัตถการที่ OPD ต้องการตรวจสอบ
     * @Author Ojika
     * @Date 19/06/2549
     */
    private void setPanelSetupOperatingItem()
    {
        thePanelSetupOperatingItem = new PanelSetupOperatingItem(theMCS);
        panelName = thePanelSetupOperatingItem.getPanelName();
        setPanelSetup(panelName, thePanelSetupOperatingItem,m_NCD,null);
    }
    
    /**
     *ใช้ในการให้ค่าของ panel setup
     *@return เป็น Vector ของ panel
     */
    public java.util.Vector getVectorSetupPanel()
    {   System.out.println("-------> Size V Setup Nan : " + vSetupModule.size());
        return vSetupModule;
    }
}
