/*
 * PanelSetupPCU.java
 *
 * Created on 19 �ԧ�Ҥ� 2548, 17:58 �.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.gui.panel.transaction;
import com.pcu.gui.dialog.HosDialog;
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.util.Vector;
import com.pcu.gui.panel.setup.*;
import com.pcu.control.HosManage;
import com.hospital_os.object.SetupModule;
import com.hospital_os.utility.IconData;
import com.pcu.utility.GutilPCU;
/**
 *
 * @author tong(Padungrat)
 */
public class PanelSetupPCU
{
    
    public PanelSetupFamilyPlaning thePanelSetupFamilyPlaning;
    public PanelSetupEpi thePanelSetupEpi;
    public PanelSetupAGRCode thePanelSetupAGRCode;
    public PanelSetupAGRType thePanelSetupAGRType;
    public PanelSetupCompanyType thePanelSetupCompanyType;
    public PanelSetupMarketType thePanelSetupMarketType;
    public PanelSetupMarketTypeLock thePanelSetupMarketTypeLock;
    public PanelSetupPetType thePanelSetupPetType;
    public PanelSetupResource thePanelSetupResource;
    //public PanelSetupServicePlace thePanelSetupServicePlace;
    public PanelSetupTempleType thePanelSetupTempleType;
    public PanelSetupVillageLocation thePanelSetupVillageLocation;
    public PanelSetupWaterType thePanelSetupWaterType;
    public PenelSetupTemplePersonel thePenelSetupTemplePersonel;
    
    public PanelSetupCheckHealthYearActivity thePanelSetupCheckHealthYearActivity;
    public PanelSetupSchoolClass thePanelSetupSchoolClass;
    
    public PanelSetupServiceType thePanelSetupServiceType;
    public PanelSetupDisease thePanelSetupDisease;
    public PanelSetupAgeSurvey thePanelSetupAgeSurvey;
    public PanelSetupMaim thePanelSetupMaim;
    public PanelSetupSurvey thePanelSetupSurvey;
    SetupModule theSetupModule;
    public Vector vSetupModule;
    HosManage theHosManage;
    private String panelName = "";
    Icon ICON = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/com/hospital_os/images/MainProgram16.gif"));
    DefaultMutableTreeNode m_health = new DefaultMutableTreeNode(new IconData(ICON, "�������"));
    DefaultMutableTreeNode m_health1 = new DefaultMutableTreeNode(new IconData(ICON, "�����ҹ"));
    DefaultMutableTreeNode m_health2 = new DefaultMutableTreeNode(new IconData(ICON, "��ҹ"));
    DefaultMutableTreeNode m_health3 = new DefaultMutableTreeNode(new IconData(ICON, "���Ǩ"));
    DefaultMutableTreeNode m_health4 = new DefaultMutableTreeNode(new IconData(ICON, "�������"));
    //DefaultMutableTreeNode c_health = new DefaultMutableTreeNode(new IconData(ICON, "EPI")); ;
    JFrame theJF;
    
    private HosDialog theHosDialog;
    
    /** Creates a new instance of PanelSetupPCU */
    public PanelSetupPCU(HosManage hm,HosDialog hd)
    {
        theHosManage = hm;
//        theJF = theHosManage.theHosInf.getHosControl().theUS.getJFrame();
        theJF = theHosManage.theHosUS.getJFrame();
        hd.setJFrame(theJF);
        theHosDialog = hd;
        vSetupModule = new Vector();
        //��Ǩ�ͺ��ҡ�͹�������ա�����ͧ init
        if(theHosManage != null)
        {
            setPanelSetupPCU();
            /*
             * amp
             * 1=�������ҹ��ԧ
             */
            if(("1").equals(theHosManage.theHC.theLookupControl.readOption().life))
                setEnableButtonSetupPanel(true);
            else
                setEnableButtonSetupPanel(false);
        }
        
    }
    /**
     *  ��㹡�� init panel setup pcu ������
     */
    private void setPanelSetupPCU()
    {
        //village
        this.m_health.add(m_health1);
        this.m_health.add(m_health2);
        this.m_health.add(m_health3);
        this.m_health.add(m_health4);
        setPanelSetupVillageLocation();
        setPanelSetupSchoolClass();
        setPanelSetupTemplePersonel();
        setPanelSetupTempleType();
        setPanelSetupCompanyType();
        setPanelSetupWaterType();
        setPanelSetupMarketType();
        setPanelSetupMarketTypeLock();
        setPanelSetupResource();
        setPanelSetupAGRCode();
        setPanelSetupAGRType();
        //home
        setPanelSetupPetType();
        //survey
        setPanelSetupUncontagious();
        setPanelSetupMaim();
        setPanelSetupSurvey();
        setPanelSetupAgeSurvey();
        //health care
        setPanelSetupFPWomen();
        setPanelSetupEPI(theHosDialog);
        setPanelSetupServiceType();
        setPanelSetupCheckHealthYearActivity();
    }
    /**��㹡�á�˹� ����ź���ӧҹ������� �Ѻ�ء Panel Setup
     *  ����͹ ��ҵç����ͧ��ҹ��� new �ҡ�͹
     *@param enabled �� boolean ��˹��� true ���� false
     */
    public void setEnableButtonSetupPanel(boolean enabled)
    {
        if(thePanelSetupEpi!= null)
        {
            thePanelSetupEpi.setEnableButtonSetupPanel(enabled);
        }
        if(thePanelSetupFamilyPlaning != null)
        {
            thePanelSetupFamilyPlaning.setEnableButtonSetupPanel(enabled);
        }
        if(thePanelSetupAGRCode != null)
        {
            thePanelSetupAGRCode.setEnableButtonSetupPanel(enabled);
        }
        if(thePanelSetupAGRType != null)
        {
            thePanelSetupAGRType.setEnableButtonSetupPanel(enabled);
        }
        if(thePanelSetupCompanyType != null)
        {
            thePanelSetupCompanyType.setEnableButtonSetupPanel(enabled);
        }
        if(thePanelSetupMarketType != null)
        {
            thePanelSetupMarketType.setEnableButtonSetupPanel(enabled);
        }
        if(thePanelSetupMarketTypeLock != null)
        {
            thePanelSetupMarketTypeLock.setEnableButtonSetupPanel(enabled);
        }
        if(thePanelSetupPetType != null)
        {
            thePanelSetupPetType.setEnableButtonSetupPanel(enabled);
        }
        if(thePanelSetupResource != null)
        {
            thePanelSetupResource.setEnableButtonSetupPanel(enabled);
        }
        if(thePanelSetupTempleType != null)
        {
            thePanelSetupTempleType.setEnableButtonSetupPanel(enabled);
        }
        if(thePanelSetupVillageLocation != null)
        {
            thePanelSetupVillageLocation.setEnableButtonSetupPanel(enabled);
        }
        if(thePanelSetupWaterType != null)
        {
            thePanelSetupWaterType.setEnableButtonSetupPanel(enabled);
        }
        if(thePenelSetupTemplePersonel != null)
        {
            thePenelSetupTemplePersonel.setEnableButtonSetupPanel(enabled);
        }
        if(thePanelSetupCheckHealthYearActivity != null)
        {
            thePanelSetupCheckHealthYearActivity.setEnableButtonSetupPanel(enabled);
        }
        if(thePanelSetupSchoolClass != null)
        {
            thePanelSetupSchoolClass.setEnableButtonSetupPanel(enabled);
        }
        if(thePanelSetupServiceType != null)
        {
            thePanelSetupServiceType.setEnableButtonSetupPanel(enabled);
        }
        if(thePanelSetupDisease != null)
        {
            thePanelSetupDisease.setEnableButtonSetupPanel(enabled);
        }
        
        if(thePanelSetupMaim != null)
        {
            thePanelSetupMaim.setEnableButtonSetupPanel(enabled);
        }
        if(thePanelSetupSurvey != null)
        {
            thePanelSetupSurvey.setEnableButtonSetupPanel(enabled);
        }
    }
    /**
     *��㹡�á�˹�������ŧ Vector
     */
    private void setPanelSetup(String panelName,Object panel,DefaultMutableTreeNode mothorNode)
    {
        theSetupModule = new  SetupModule();
        theSetupModule.theMainTreeNode = m_health;
        theSetupModule.theTreeNode = mothorNode;
        //if(childNode != null)
        //theSetupModule.theTreeNode = childNode;
        theSetupModule.thePanel = (JPanel)panel;
        theSetupModule.pname = GutilPCU.getTextBundle(panelName);
        vSetupModule.add(theSetupModule);
    }
    private void setPanelSetupEPI(HosDialog theHosDialog)
    {
        /**setup �ͧ epi*/
        thePanelSetupEpi = new PanelSetupEpi(theHosManage,theHosDialog);
        panelName = "VaccineSetup";
        setPanelSetup(panelName, thePanelSetupEpi,m_health4);
    }
    private void setPanelSetupUncontagious()
    {
        thePanelSetupDisease = new PanelSetupDisease(theHosManage);
        panelName = "Disease";
        setPanelSetup(panelName, thePanelSetupDisease,m_health3);
    }
    private void setPanelSetupAgeSurvey()
    {
        thePanelSetupAgeSurvey = new PanelSetupAgeSurvey(theHosManage);
        panelName = "AgeSurvey";
        setPanelSetup(panelName, thePanelSetupAgeSurvey,m_health3);
    }
    private void setPanelSetupMaim()
    {
        thePanelSetupMaim = new PanelSetupMaim(theHosManage);
        panelName = "Maim";
        setPanelSetup(panelName, thePanelSetupMaim,m_health3);
    }
    private void setPanelSetupSurvey()
    {
        thePanelSetupSurvey = new PanelSetupSurvey(theHosManage);
        panelName = "SurveyForm";
        setPanelSetup(panelName, thePanelSetupSurvey,m_health3);
    }
    
    /**
     *����Ѻ panel �Ǫ�ѳ���ҧἹ��ͺ����
     *@author tong(padungrat)
     */
    private void setPanelSetupFPWomen()
    {
        thePanelSetupFamilyPlaning = new PanelSetupFamilyPlaning(theHosManage,theHosDialog);
        /**setup �ͧ �ҧἹ��ͺ����*/
        panelName = "SetupDrugAndSupplyFP";
        setPanelSetup(panelName, thePanelSetupFamilyPlaning,m_health4);
    }
    private void setPanelSetupAGRCode()
    {
        thePanelSetupAGRCode = new PanelSetupAGRCode(theHosManage);
        panelName = "AGRCodeSetup";
        setPanelSetup(panelName, thePanelSetupAGRCode,m_health1);
    }
    private void setPanelSetupAGRType()
    {
        thePanelSetupAGRType = new PanelSetupAGRType(theHosManage);
        panelName = "AGRTypeSetup";
        setPanelSetup(panelName, thePanelSetupAGRType,m_health1);
    }
    private void setPanelSetupCompanyType()
    {
        thePanelSetupCompanyType = new PanelSetupCompanyType(theHosManage);
        panelName = "CompanyTypeSetup";
        setPanelSetup(panelName, thePanelSetupCompanyType,m_health1);
    }
    private void setPanelSetupMarketType()
    {
        thePanelSetupMarketType = new PanelSetupMarketType(theHosManage);
        panelName = "MarketTypeSetup";
        setPanelSetup(panelName, thePanelSetupMarketType,m_health1);
    }
    private void setPanelSetupMarketTypeLock()
    {
        thePanelSetupMarketTypeLock = new PanelSetupMarketTypeLock(theHosManage);
        panelName = "MarketTypeLockSetup";
        setPanelSetup(panelName, thePanelSetupMarketTypeLock,m_health1);
    }
    private void setPanelSetupPetType()
    {
        thePanelSetupPetType = new PanelSetupPetType(theHosManage);
        panelName = "PetTypeSetup";
        setPanelSetup(panelName, thePanelSetupPetType,m_health2);
    }
    private void setPanelSetupResource()
    {
        thePanelSetupResource = new PanelSetupResource(theHosManage);
        panelName = "Resource";
        setPanelSetup(panelName, thePanelSetupResource,m_health1);
    }
     /*private void setPanelSetupServicePlace()
    {
        thePanelSetupServicePlace = new PanelSetupServicePlace(theHosManage.theHosControl);
        panelName = "ServicePlaceSetup";
        setPanelSetup(panelName, thePanelSetupServicePlace,m_health,null);
      
    }*/
    
    private void setPanelSetupVillageLocation()
    {
        thePanelSetupVillageLocation = new PanelSetupVillageLocation(theHosManage);
        panelName = "VillageLocationSetup";
        setPanelSetup(panelName, thePanelSetupVillageLocation,m_health1);
    }
    private void setPanelSetupWaterType()
    {
        thePanelSetupWaterType = new PanelSetupWaterType(theHosManage);
        panelName = "WaterTypeSetup";
        setPanelSetup(panelName, thePanelSetupWaterType,m_health1);
    }
    private void setPanelSetupTemplePersonel()
    {
        thePenelSetupTemplePersonel = new PenelSetupTemplePersonel(theHosManage);
        panelName = "TemplePersonelSetup";
        setPanelSetup(panelName, thePenelSetupTemplePersonel,m_health1);
    }
    private void setPanelSetupTempleType()
    {
        thePanelSetupTempleType = new PanelSetupTempleType(theHosManage);
        panelName = "TempleTypeSetup";
        setPanelSetup(panelName, thePanelSetupTempleType,m_health1);
    }
    private void setPanelSetupCheckHealthYearActivity()
    {
        thePanelSetupCheckHealthYearActivity = new PanelSetupCheckHealthYearActivity(theHosManage.theHosControl);
        panelName = "CheckHealthYearActivitySetup";
        setPanelSetup(panelName, thePanelSetupCheckHealthYearActivity,m_health4);
    }
    private void setPanelSetupSchoolClass()
    {
        thePanelSetupSchoolClass = new PanelSetupSchoolClass(theHosManage.theHosControl);
        panelName = "SchoolClassSetup";
        setPanelSetup(panelName, thePanelSetupSchoolClass,m_health1);
    }
    private void setPanelSetupServiceType()
    {
        thePanelSetupServiceType = new PanelSetupServiceType(theHosManage.theHosControl);
        panelName = "ServiceTypeSetup";
        setPanelSetup(panelName, thePanelSetupServiceType,m_health4);
    }
    /**��㹡���ŧ���������������
     *@param pname �� String �� ���������ѧ���
     *@return String �� ����������
     */
    private String getPannelName(String pname)
    {
        return GutilPCU.getTextBundle(pname);
    }
    /**
     *��㹡������Ңͧ panel setup
     *@return �� Vector �ͧ panel
     */
    public java.util.Vector getVectorSetupPanel()
    {
        return vSetupModule;
    }
    
}
