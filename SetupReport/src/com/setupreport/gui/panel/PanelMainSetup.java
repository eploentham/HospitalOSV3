/*
 * PanelMainSetup.java
 *
 * Created on 28 ���Ҥ� 2548, 21:26 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.gui.panel; 
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.util.Vector;
import com.setupreport.manage.HosManage;
import com.setupreport.utility.Language;
import com.hospital_os.object.SetupModule;
import com.hospital_os.utility.IconData;
/**
 *
 * @author tong(Padungrat)
 * @Modify Ojika
 */
public class PanelMainSetup {
    
    HosManage theHosManage;
    Vector vSetupModule;
    SetupModule theSetupModule;
    int language =1 ;
    private String panelName = "";
    Icon ICON = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/com/hospital_os/images/MainProgram16.gif"));
    Icon ICONs = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/com/hospital_os/images/Host16.gif"));

   //DefaultMutableTreeNode m_health = new DefaultMutableTreeNode(new IconData(ICON, Language.getTextBundle("ReportSetup",language)));
   
   DefaultMutableTreeNode m_setupreport = new DefaultMutableTreeNode(new IconData(ICONs, Language.getTextBundle("ReportSetup",language)));
   DefaultMutableTreeNode m_aric = new DefaultMutableTreeNode(new IconData(ICON, Language.getTextBundle("ReportSetup_ARIC",language)));
   DefaultMutableTreeNode m_11ng5_3 = new DefaultMutableTreeNode(new IconData(ICON, Language.getTextBundle("ReportSetup_11ng5_3",language)));
   DefaultMutableTreeNode m_11ng5_4 = new DefaultMutableTreeNode(new IconData(ICON, Language.getTextBundle("ReportSetup_11ng5_4",language)));
   DefaultMutableTreeNode m_pcu = new DefaultMutableTreeNode(new IconData(ICON, Language.getTextBundle("ReportSetup_pcu",language)));
   DefaultMutableTreeNode m_11ng5_all = new DefaultMutableTreeNode(new IconData(ICON, Language.getTextBundle("ReportSetup_11ng5_all",language)));
   DefaultMutableTreeNode m_eye = new DefaultMutableTreeNode(new IconData(ICON, Language.getTextBundle("ReportSetup_eye",language)));
   DefaultMutableTreeNode m_18files = new DefaultMutableTreeNode(new IconData(ICON, Language.getTextBundle("ReportSetup_18files",language)));
   //DefaultMutableTreeNode m_12files = new DefaultMutableTreeNode(new IconData(ICON, Language.getTextBundle("ReportSetup_12files",language)));
   
    
    public PanelSetupAntibioticGroup thePanelSetupAntibioticGroup;
    public PanelSetupAntibioticItem thePanelSetupAntibioticItem;
    public PanelSetupAntibioticSubGroup thePanelSetupAntibioticSubGroup;
    public PanelSetupEyeDisease thePanelSetupEyeDisease;
    public PanelSetupMapPlanPtType thePanelSetupMapPlanPtType;
    public PanelSetupUCPlanGroupPttype thePanelSetupUCPlanGroupPttype;
    public PanelSetupResidentAgeGroup thePanelSetupResidentAgeGroup;
    public PanelSetupDentalProtectItem thePanelSetupDentalProtectItem;
    public PanelSetupEpiGroupItem thePanelSetupEpiGroupItem;
    public PanelSetupEpiMeaslesItem thePanelSetupEpiMeaslesItem;
    public PanelSetupHealthyGroupSurvey thePanelSetupHealthyGroupSurvey;
//    public PanelSetupNutrition thePanelSetupNutrition;
    public PanelSetupScreenedDisease thePanelSetupScreenedDisease;
    public PanelSetupEpiTTItem thePanelSetupEpiTTItem;
    public PanelSetupEpiAgeGroup thePanelSetupEpiAgeGroup;
//    public PanelSetupClinic12Files thePanelSetupClinic12Files; 

    public PanelMainSetup(HosManage hosManage) {
        theHosManage = hosManage;
        vSetupModule = new Vector();
        
        setPanelSetupReport();
    }
    /**
     *  ��㹡�� init panel setup ��§ҹ������
     */
     private void setPanelSetupReport()
    { 
         setPanelSetupAntibioticGroup();
         setPanelSetupAntibioticSubGroup();
         setPanelSetupAntibioticItem();
         
         setPanelSetupEyeDisease();
         
         setPanelSetupUCPlanGroupPttype();
         
         setPanelSetupMapPlanPtType();
        
         setPanelSetupResidentAgeGroup();
         
         setPanelSetupDentalProtectItem();
         setPanelSetupEpiMeaslesItem();
         setPanelSetupEpiGroupItem();
         setPanelSetupHealthyGroupSurvey();
         
         setPanelSetupScreenedDisease();
//         setPanelSetupNutrition();
         setPanelSetupEpiTTItem();
         setPanelSetupEpiAgeGroup();
         
//         setPanelSetupClinic12Files();
     }
     
     /**
     *��㹡�á�˹�������ŧ Vector 
     */
    private void setPanelSetup(String panelName,Object panel
            ,DefaultMutableTreeNode mothorNode,DefaultMutableTreeNode childNode)
    {
        theSetupModule = new  SetupModule();
        theSetupModule.theMainTreeNode = m_setupreport;
        theSetupModule.theTreeNode = mothorNode;
        if(childNode != null)
            theSetupModule.theTreeNode = childNode;
        
        theSetupModule.thePanel = (JPanel)panel;
        theSetupModule.pname = Language.getTextBundle(panelName,language);        
        vSetupModule.add(theSetupModule);
    }
    private void setPanelSetupEpiAgeGroup()
    {
        thePanelSetupEpiAgeGroup = new PanelSetupEpiAgeGroup(theHosManage);
        panelName = thePanelSetupEpiAgeGroup.getPanelName();
        setPanelSetup(panelName, thePanelSetupEpiAgeGroup,m_pcu,null);
        
    }
     private void setPanelSetupEpiTTItem()
    {
        thePanelSetupEpiTTItem = new PanelSetupEpiTTItem(theHosManage);
        panelName = thePanelSetupEpiTTItem.getPanelName();
        setPanelSetup(panelName, thePanelSetupEpiTTItem,m_pcu,null);
        
    }
//     private void setPanelSetupClinic12Files()
//    {
//        thePanelSetupClinic12Files = new PanelSetupClinic12Files(theHosManage);
//        panelName = thePanelSetupClinic12Files.getPanelName();
//        setPanelSetup(panelName, thePanelSetupClinic12Files,m_12files,null);        
//    }
//    private void setPanelSetupNutrition()
//    {
//        thePanelSetupNutrition = new PanelSetupNutrition(theHosManage);
//        panelName = thePanelSetupNutrition.getPanelName();
//        setPanelSetup(panelName, thePanelSetupNutrition,m_18files,null);
//    }
     private void setPanelSetupHealthyGroupSurvey()
    {
        thePanelSetupHealthyGroupSurvey = new PanelSetupHealthyGroupSurvey(theHosManage);
        panelName = thePanelSetupHealthyGroupSurvey.getPanelName();
        setPanelSetup(panelName, thePanelSetupHealthyGroupSurvey,m_11ng5_3,null);        
    }
     private void setPanelSetupDentalProtectItem()
    {
        thePanelSetupDentalProtectItem = new PanelSetupDentalProtectItem(theHosManage);
        panelName = thePanelSetupDentalProtectItem.getPanelName();
        setPanelSetup(panelName, thePanelSetupDentalProtectItem,m_11ng5_3,null);        
    }
     private void setPanelSetupEpiMeaslesItem()
    {
        thePanelSetupEpiMeaslesItem = new PanelSetupEpiMeaslesItem(theHosManage);
        panelName = thePanelSetupEpiMeaslesItem.getPanelName();
        setPanelSetup(panelName, thePanelSetupEpiMeaslesItem,m_11ng5_3,null);
        
    }
    
    private void setPanelSetupEpiGroupItem()
    {
        thePanelSetupEpiGroupItem = new PanelSetupEpiGroupItem(theHosManage);
        panelName = thePanelSetupEpiGroupItem.getPanelName();
        setPanelSetup(panelName, thePanelSetupEpiGroupItem,m_11ng5_3,null);
        
    }
    private void setPanelSetupUCPlanGroupPttype()
    {
        thePanelSetupUCPlanGroupPttype = new PanelSetupUCPlanGroupPttype(theHosManage);
        panelName = thePanelSetupUCPlanGroupPttype.getPanelName();
        setPanelSetup(panelName, thePanelSetupUCPlanGroupPttype,m_11ng5_all,null);
        
    }
    private void setPanelSetupMapPlanPtType()
    {
        thePanelSetupMapPlanPtType = new PanelSetupMapPlanPtType(theHosManage);
        panelName = thePanelSetupMapPlanPtType.getPanelName();
        setPanelSetup(panelName, thePanelSetupMapPlanPtType,m_11ng5_all,null);
        
    }
    private void setPanelSetupEyeDisease()
    {
        thePanelSetupEyeDisease = new PanelSetupEyeDisease(theHosManage);
        panelName = thePanelSetupEyeDisease.getPanelName();
        setPanelSetup(panelName, thePanelSetupEyeDisease,m_eye,null);
        
    }
    private void setPanelSetupAntibioticSubGroup()
    {
        thePanelSetupAntibioticSubGroup = new PanelSetupAntibioticSubGroup(theHosManage);
        panelName = thePanelSetupAntibioticSubGroup.getPanelName();
        setPanelSetup(panelName, thePanelSetupAntibioticSubGroup,m_aric,null);
        
    }
    private void setPanelSetupAntibioticGroup()
    {       
        thePanelSetupAntibioticGroup = new PanelSetupAntibioticGroup(theHosManage);
        panelName = thePanelSetupAntibioticGroup.getPanelName();
        setPanelSetup(panelName, thePanelSetupAntibioticGroup,m_aric,null);        
    }
    
    private void setPanelSetupAntibioticItem()
    {        
        thePanelSetupAntibioticItem = new PanelSetupAntibioticItem(theHosManage);
        panelName = thePanelSetupAntibioticItem.getPanelName();
        setPanelSetup(panelName, thePanelSetupAntibioticItem,m_aric,null);        
    }
    
    private void setPanelSetupResidentAgeGroup()
    {
        thePanelSetupResidentAgeGroup = new PanelSetupResidentAgeGroup(theHosManage);
        panelName = thePanelSetupResidentAgeGroup.getPanelName();
        setPanelSetup(panelName, thePanelSetupResidentAgeGroup,m_pcu,null);
    }
    
    private void setPanelSetupScreenedDisease()
    {
        thePanelSetupScreenedDisease = new PanelSetupScreenedDisease(theHosManage);
        panelName = thePanelSetupScreenedDisease.getPanelName();
        setPanelSetup(panelName, thePanelSetupScreenedDisease, m_11ng5_4, null);
    }
    /**
     *��㹡������Ңͧ panel setup 
     *@return �� Vector �ͧ panel
     */
    public java.util.Vector getVectorSetupPanel()
    {   System.out.println("-------> Size V Setup : " + vSetupModule.size());
        return vSetupModule;
    }
}
