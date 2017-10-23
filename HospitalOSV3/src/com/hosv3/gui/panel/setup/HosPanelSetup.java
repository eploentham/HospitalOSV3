/*
 * HosPanelSetup.java
 *
 * Created on 16 พฤษภาคม 2548, 20:57 น.
 */
package com.hosv3.gui.panel.setup;

import com.hosv3.utility.Constant;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;

import com.hosv3.object.*;
import com.hosv3.control.*;
import com.hosv3.gui.component.*;
import com.hosv3.utility.connection.*;

import com.hospital_os.utility.IconData;
import com.hospital_os.object.*;
import com.hospital_os.usecase.connection.ModuleInfTool;
import com.hosv3.gui.dialog.HosDialog;
import com.hosv3.gui.panel.detail.*;

/**
 *
 * @author  administrator
 * 1.  60-10-23 เรื่อง ห้อง     Hospital OS เข้าใจว่า ไม่มีห้อง
 * Modify doc 6.
 */
 public class HosPanelSetup {
    
    public DefaultTreeModel theTreeModel;
    public JPanel thePanelCard;
    DefaultMutableTreeNode theTreeNode;
    DefaultMutableTreeNode theHeadNode;
    HosObject theHO;
    HosControl theHC;
    HosDialog theHD;
    UpdateStatus theUS;
    Vector vModule;

    PanelReportSQL panelReportSQL;
    PanelReportSQLParam panelReportSQLParam;
    
    String PANEL_SETUP_XRAY_FILMSIZE = Constant.getTextBundle("PANEL_SETUP_XRAY_FILMSIZE");
    String PANEL_SETUP_LAB_RESULTTYPE = Constant.getTextBundle("PANEL_SETUP_LAB_RESULTTYPE");
    String PANEL_SETUP_AUTO_ORDER_ITEM = Constant.getTextBundle("PANEL_SETUP_AUTO_ORDER_ITEM");
    String PANEL_SETUP_BILL = Constant.getTextBundle("PANEL_SETUP_BILL");
    String PANEL_SETUP_GAA = Constant.getTextBundle("สิทธิการใช้งาน");
    String PANEL_SETUP_CLINIC = Constant.getTextBundle("PANEL_SETUP_CLINIC");
    String PANEL_SETUP_CONTRACT = Constant.getTextBundle("PANEL_SETUP_CONTRACT");
    String PANEL_SETUP_DRUG_FREQUENCY = Constant.getTextBundle("PANEL_SETUP_DRUG_FREQUENCY");
    String PANEL_SETUP_NEWS = Constant.getTextBundle("แจ้งข่าว");
    String PANEL_SETUP_EMAIL = Constant.getTextBundle("ตั้งค่าระบบส่งเมลแจ้งบัก");
    String PANEL_SETUP_DRUG_INSTRUCTION = Constant.getTextBundle("PANEL_SETUP_DRUG_INSTRUCTION");
    String PANEL_SETUP_DRUG_DOSE_PRINT = Constant.getTextBundle("PANEL_SETUP_DRUG_DOSE_PRINT");
    String PANEL_SETUP_DX_TEMPLATE = Constant.getTextBundle("PANEL_SETUP_DX_TEMPLATE");
    // ประกาศเป็น public เพื่อให้ FrameSetup สามารถเรียกใช้งานได้  sumo 19/7/2549
    public String PANEL_SETUP_EMPLOYEE = Constant.getTextBundle("PANEL_SETUP_EMPLOYEE");
    String PANEL_SETUP_ITEM_SET = Constant.getTextBundle("PANEL_SETUP_ITEM_SET");
    String PANEL_SETUP_OFFICE_INCUP = Constant.getTextBundle("PANEL_SETUP_OFFICE_INCUP");
    String PANEL_SETUP_ROOM = Constant.getTextBundle("ตั้งค่าห้อง");    //+1
    String PANEL_SETUP_OPTION = Constant.getTextBundle("PANEL_SETUP_OPTION");
    String PANEL_SETUP_ORDER = Constant.getTextBundle("PANEL_SETUP_ORDER");
    String PANEL_SETUP_ORDER_ITEM = Constant.getTextBundle("PANEL_SETUP_ORDER_ITEM");
    String PANEL_SETUP_PAYER = Constant.getTextBundle("PANEL_SETUP_PAYER");
    String PANEL_SETUP_PLAN = Constant.getTextBundle("PANEL_SETUP_PLAN");
    String PANEL_SETUP_REMAIN_0 = Constant.getTextBundle("ผู้ป่วยค้างชำระ");
    String PANEL_SETUP_VITAL_TEMPLATE = Constant.getTextBundle("PANEL_SETUP_VITAL_TEMPLATE");
    String PANEL_SETUP_WARD = Constant.getTextBundle("จุดบริการผู้ป่วยใน");
    String PANEL_SETUP_SERVICE_POINT = Constant.getTextBundle("จุดบริการผู้ป่วยนอก");
    String PANEL_SETUP_SITE = Constant.getTextBundle("PANEL_SETUP_SITE");
    String PANEL_SETUP_UOM =  Constant.getTextBundle("PANEL_SETUP_UOM");
    String PANEL_SETUP_DOCTOR_DRUGSET =  Constant.getTextBundle("PANEL_SETUP_DOCTOR_DRUGSET");
    String PANEL_SETUP_FORLIFE=  Constant.getTextBundle("PANEL_SETUP_FORLIFE");
    String PANEL_SETUP_CHRONIC=  Constant.getTextBundle("PANEL_SETUP_CHRONIC");
    String PANEL_SETUP_SURVEIL=  Constant.getTextBundle("PANEL_SETUP_SURVEIL");
    String PANEL_SETUP_GROUP_ICD10 =  Constant.getTextBundle("PANEL_SETUP_GROUP_ICD10");
    String PANEL_SETUP_QUEUE_VISIT =  Constant.getTextBundle("PANEL_SETUP_QUEUE_VISIT");
    String PANEL_SETUP_XRAY_LETERAL  = Constant.getTextBundle("PANEL_SETUP_XRAY_LETERAL");
    String PANEL_SETUP_XRAY_POSITION  = Constant.getTextBundle("PANEL_SETUP_XRAY_POSITION");
    String PANEL_SETUP_SQL_TEMPLATE  = Constant.getTextBundle("PANEL_SETUP_SQL_TEMPLATE");
    String PANEL_SETUP_VIEW_SQL_TEMPLATE  = Constant.getTextBundle("PANEL_SETUP_VIEW_SQL_TEMPLATE");
    String PANEL_SETUP_SQL_TEMPLATE_PARAM  = Constant.getTextBundle("PANEL_SETUP_SQL_TEMPLATE_PARAM");
    String PANEL_SETUP_VIEW_SQL_TEMPLATE_PARAM  = Constant.getTextBundle("PANEL_SETUP_VIEW_SQL_TEMPLATE_PARAM");
    String PANEL_SETUP_VIEW_REPORT_RECEIP_ORDER = Constant.getTextBundle("PANEL_SETUP_VIEW_REPORT_RECEIP_ORDER");
    String PANEL_SETUP_VIEW_REPORT_RECEIP_CLINIC = Constant.getTextBundle("PANEL_SETUP_VIEW_REPORT_RECEIP_CLINIC");
    String PANEL_SETUP_VIEW_REPORT_PATIENT_CLINIC = Constant.getTextBundle("PANEL_SETUP_VIEW_REPORT_PATIENT_CLINIC");
    String PANEL_SETUP_CONVERT = "PANEL_CONVERT";     //+1
    
    String PANEL_SETUP_DRUG_STANDARD = Constant.getTextBundle("ชื่อสามัญทางยา"); //amp:14/03/2549 
    String PANEL_SETUP_DRUG_STANDARD_MAP_ITEM = Constant.getTextBundle("จัดกลุ่มยามาตรฐาน"); //amp:14/03/2549 
    String PANEL_SETUP_DRUG_INTERACTION = Constant.getTextBundle("ปฏิกิริยาต่อกันของยา"); //amp:14/03/2549
    String PANEL_SETUP_BODY_ORGAN = Constant.getTextBundle("อวัยวะร่างกาย");//amp:11/04/2549
    String PANEL_SETUP_NUTRITIONMAP = Constant.getTextBundle("จับคู่ระดับโภชนาการ");//amp:26/04/2549
    String PANEL_SETUP_16_GROUP = Constant.getTextBundle("รายการ 16 กลุ่มมาตรฐาน");// sumo:05/06/2549
    String PANEL_SETUP_NCD_GROUP = Constant.getTextBundle("กลุ่มโรค NCD");//amp:14/06/2549
    String PANEL_SETUP_DRUG_DOSE_SHORTCUT = Constant.getTextBundle("รายการ Dose ย่อ");//pu:04/08/2549
    String PANEL_SETUP_GUIDE_AFTER_DX = Constant.getTextBundle("ตัวช่วยคำแนะนำหลังตรวจ");//pu:04/08/2549
    String PANEL_SETUP_ORDER_ITEM_PRICE = Constant.getTextBundle("กำหนดราคา Order");//pu:04/08/2549
    String PANEL_SETUP_ORDER_ITEM_DOSE = Constant.getTextBundle("กำหนด Dose ยา");
    
    String GROUP_SETUP_ADMIN =  Constant.getTextBundle("GROUP_SETUP_ADMIN");
    String GROUP_SETUP_SERVICE =  Constant.getTextBundle("GROUP_SETUP_SERVICE");
    String GROUP_SETUP_ORDER =  Constant.getTextBundle("GROUP_SETUP_ORDER");
    String GROUP_SETUP_LABXRAY =  Constant.getTextBundle("GROUP_SETUP_LABXRAY");
    String GROUP_SETUP_TREAT =  Constant.getTextBundle("GROUP_SETUP_TREAT");
    String GROUP_SETUP_DRUG =  Constant.getTextBundle("GROUP_SETUP_DRUG");
    String GROUP_SETUP_PAY =  Constant.getTextBundle("GROUP_SETUP_PAY");
    String GROUP_SETUP_SQL =  Constant.getTextBundle("GROUP_SETUP_SQL");
    String GROUP_SETUP_REPORT =  Constant.getTextBundle("GROUP_SETUP_REPORT");
    String GROUP_SETUP_OTHER =  Constant.getTextBundle("GROUP_SETUP_OTHER");
    String GROUP_SETUP_ICD =  Constant.getTextBundle("GROUP_SETUP_ICD");
    String GROUP_SETUP_MODULE = Constant.getTextBundle("โมดูลเสริม");
    
    Icon ICONMain = new ImageIcon(getClass().getResource("/com/hospital_os/images/Host16.gif"));
    Icon ICON = new ImageIcon(getClass().getResource("/com/hospital_os/images/MainProgram16.gif"));

    DefaultMutableTreeNode c_admin = new DefaultMutableTreeNode(new IconData(ICON, GROUP_SETUP_ADMIN));
    DefaultMutableTreeNode c_serv = new DefaultMutableTreeNode(new IconData(ICON, GROUP_SETUP_SERVICE));
    DefaultMutableTreeNode c_order = new DefaultMutableTreeNode(new IconData(ICON, GROUP_SETUP_ORDER));
    DefaultMutableTreeNode c_labxray = new DefaultMutableTreeNode(new IconData(ICON, GROUP_SETUP_LABXRAY));
    DefaultMutableTreeNode c_treat = new DefaultMutableTreeNode(new IconData(ICON, GROUP_SETUP_TREAT));
    DefaultMutableTreeNode c_drug = new DefaultMutableTreeNode(new IconData(ICON, GROUP_SETUP_DRUG));
    DefaultMutableTreeNode c_icd = new DefaultMutableTreeNode(new IconData(ICON, GROUP_SETUP_ICD));
    DefaultMutableTreeNode c_pay = new DefaultMutableTreeNode(new IconData(ICON, GROUP_SETUP_PAY));
    DefaultMutableTreeNode c_rep = new DefaultMutableTreeNode(new IconData(ICON, GROUP_SETUP_REPORT));
    DefaultMutableTreeNode c_other = new DefaultMutableTreeNode(new IconData(ICON, GROUP_SETUP_OTHER));
    DefaultMutableTreeNode c_module = new DefaultMutableTreeNode(new IconData(ICONMain, GROUP_SETUP_MODULE)); 

    private PanelSetupNCDGroup panelSetupNCDGroup;
    private PanelSetupServicePoint panelSetupServicePoint;
    private PanelSetupItem panelSetupItem;
    private PanelSetupItemPrice panelSetupItemPrice;
    private PanelSetupAutoOrderItem panelSetupAutoOrderItem;
    private PanelSetupVitalTemplate panelSetupVitalTemplate;
    private PanelSetupDoctorDrugSet panelSetupDoctorDrugSet;
    private PanelSetupGroupIcd10 panelSetupGroupIcd10;
    private PanelSDPlan panelSetupPlan;
    private PanelSetupPayment0 panelSetupPayment0;
    private PanelSDEmployee panelSetupEmployee;
    private PanelSetupGroupChronic panelSetupGroupChronic;
    private PanelSetupItemDose panelSetupItemDose;

//    private PanelSetupGroupSurveil panelSetupGroupSurveil;

    public HosPanelSetup(HosDialog hd,HosControl hc,UpdateStatus us) 
    {
        setControl(hd,hc,us);
    } 
     
    public void setControl(HosDialog hd,HosControl hc,UpdateStatus us)
    {
        theHO = hc.theHO;
        theHC = hc;
        theUS = us;
        theHD = hd;
        theHeadNode = new DefaultMutableTreeNode(new IconData(ICONMain, "Setup"));    
        theTreeModel = new DefaultTreeModel(theHeadNode);
        theTreeNode = new DefaultMutableTreeNode(new IconData(ICONMain, "HospitalOS Setup"));    
        theHeadNode.add(theTreeNode);
        thePanelCard = new JPanel();
        thePanelCard.setSize(400,400);
        thePanelCard.setLayout(new CardLayout());
        Constant.println("Ekapop setControl");
        initTreeModel();
    }
    
    public void refreshComboBox()
    {
        theHC.theLookupControl.refreshLookup();
        if(panelSetupAutoOrderItem!=null)        panelSetupAutoOrderItem.setupLookup();
        if(panelSetupDoctorDrugSet!=null)        panelSetupDoctorDrugSet.setupLookup();
        if(panelSetupEmployee!=null)        panelSetupEmployee.setupLookup();
        if(panelSetupGroupIcd10!=null)        panelSetupGroupIcd10.setupLookup();
        if(panelSetupItem!=null)        panelSetupItem.setupLookup();
        if(panelSetupItemPrice!=null)        panelSetupItemPrice.setupLookup();
        if(panelSetupPlan!=null)        panelSetupPlan.setupLookup();
        if(panelSetupPayment0!=null)        panelSetupPayment0.setupLookup();
        if(panelSetupServicePoint!=null)        panelSetupServicePoint.setupLookup();
        if(panelSetupVitalTemplate!=null)        panelSetupVitalTemplate.setupLookup();
        if(panelSetupNCDGroup!=null)        panelSetupNCDGroup.setupLookup();
    }
    
    private void initTreeNodePS(DefaultMutableTreeNode category,PanelSetup jp,String pname)
    {
        if(pname.equals(PANEL_SETUP_OFFICE_INCUP))
        {
            jp = new PanelSetupOfficeInCup(theHC,theUS);
            ((PanelSetupOfficeInCup)jp).setHosDialog(theHD);
        }
        jp.setControl(theHC,theUS);
        jp.setTitleLabel(pname);
        JPanel jp1 = (JPanel)jp;
//        jp1.setSize(300,300);
        thePanelCard.add(jp1,pname);
        DefaultMutableTreeNode book = new DefaultMutableTreeNode(new IconData(ICONMain, pname));    
        category.add(book);
    }
    
    private void initTreeNode(DefaultMutableTreeNode category,JPanel jp,String pname)
    {
//        jp.setSize(300,300);
        thePanelCard.add(jp,pname);
        DefaultMutableTreeNode book = new DefaultMutableTreeNode(new IconData(ICONMain, pname));    
        category.add(book);
    }

    public boolean showModule(Vector vModule)
    { 
        boolean ret = true;
        if(vModule==null) return true;
        boolean is_used_module=false;
        
        for(int i=0,size=vModule.size();i<size;i++)
        {
            ModuleInfTool mi = (ModuleInfTool)vModule.get(i);
            try{
                mi.setHosControl(theHC);
                boolean enable = mi.isJTreeVisible(theHO.theEmployee.authentication_id);
                if(enable){   
                Vector vSetupModule = mi.getVectorSetupModule();
                int size1 = 0;
                if(vSetupModule!=null)
                    size1 = vSetupModule.size();
                //add module header
                //DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode(new IconData(ICON,mi.getNamePanel()));
                Constant.println("henbe_test_setupmodule vSetupModule.size():" + size1);
                Constant.println("henbe_test_setupmodule mi.getNamePanel():'" + mi.getNamePanel() + "'");

                for(int j=0;j<size1;j++)
                {
                    SetupModule theSetupModule = (SetupModule)vSetupModule.get(j); 
                    //Constant.println("henbe_test_setupmodule theSetupModule.pname:" + theSetupModule.pname);
                    //Constant.println("henbe_test_setupmodule theMainTreeNode != theTreeNode:"
                        //+ (theSetupModule.theMainTreeNode != theSetupModule.theTreeNode));
                    if(size1>1){
                        theHeadNode.add(theSetupModule.theMainTreeNode);
                        //c_module.add(theSetupModule.theMainTreeNode);
                        if(theSetupModule.theMainTreeNode != theSetupModule.theTreeNode){
                            theSetupModule.theMainTreeNode.add(theSetupModule.theTreeNode);
                        }
                        theSetupModule.thePanel.setSize(400,400);
                        thePanelCard.add(theSetupModule.pname,theSetupModule.thePanel);
                        DefaultMutableTreeNode book = new DefaultMutableTreeNode(
                                new IconData(ICONMain, theSetupModule.pname));    
                        theSetupModule.theTreeNode.add(book);
                    }
                    else
                        //initTreeNode(c_module,theSetupModule.thePanel,theSetupModule.pname);
                        initTreeNode(theHeadNode,theSetupModule.thePanel,theSetupModule.pname);
                    is_used_module = true;
                } 
                }
            }catch(Exception e){
                //ป้องกันเฝื่อว่ามี module ตัวไหนไม่ปกติ
                e.printStackTrace(Constant.getPrintStream());
                ret = false;
            }
        }
//        if(is_used_module)  theHeadNode.add(c_module);
        return ret;
    }

    public void initTreeModel()
    {
        String auth = theHO.theEmployee.authentication_id;
        boolean is_admin = auth.equals(Authentication.ADMIN);
            if(is_admin )
            { //default false
                theTreeNode.add(c_admin);
                if(is_admin || theHO.theGActionAuthV.isReadTreeAdminOption())
                {
                    PanelSetupOption panelSetupOption = new PanelSetupOption(theHC,theUS);
                    initTreeNodePS(c_admin,panelSetupOption,PANEL_SETUP_OPTION);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeAdminLock()){
                    PanelLockedPatient panelLockedPatient = new PanelLockedPatient(theHC,theUS);
                    initTreeNodePS(c_admin,panelLockedPatient,PANEL_SETUP_FORLIFE);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeAdminAuthen()){
                    PanelSetupGActionAuth panelSetupGActionAuth = new PanelSetupGActionAuth(theHC,theUS);
                    initTreeNodePS(c_admin,panelSetupGActionAuth,PANEL_SETUP_GAA);
                }
            }
            if(is_admin || theHO.theGActionAuthV.isReadTreeServicePoint()){ //default false
                theTreeNode.add(c_serv);
                if(is_admin || theHO.theGActionAuthV.isReadTreeServicePointWard()){
                    PanelSDWard psi = new PanelSDWard(theHC,theUS);
                    PanelSetupXPer panelSetupWard = new PanelSetupXPer(theHC,theUS,psi);
                    panelSetupWard.setActiveVisible();
                    initTreeNode(c_serv,panelSetupWard,PanelSDWard.TITLE);

                    //PanelSetupWard panelSetupWard = new PanelSetupWard(theHC,theUS);
                    //initTreeNodePS(c_serv,panelSetupWard,PANEL_SETUP_WARD);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeServicePointServicePoint()){
                    panelSetupServicePoint = new PanelSetupServicePoint(theHC,theUS);
                    initTreeNodePS(c_serv,panelSetupServicePoint,PANEL_SETUP_SERVICE_POINT);
//                    panelSetupServicePoint.addDepPanel(panelSetupEmployee);
//                    panelSetupServicePoint.addDepPanel(panelSetupVitalTemplate);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeServicePointClinic()){
                    PanelSDClinic psi = new PanelSDClinic(theHC,theUS);
                    PanelSetupXPer panelSetupClinic = new PanelSetupXPer(theHC,theUS,psi);
                    panelSetupClinic.setActiveVisible();
                    initTreeNode(c_serv,panelSetupClinic,PanelSDClinic.TITLE);

                    //PanelSetupClinic panelSetupClinic = new PanelSetupClinic(theHC,theUS);
                    //initTreeNodePS(c_serv,panelSetupClinic,PANEL_SETUP_CLINIC);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeServicePointOffice()){
                    PanelSDOffice psi = new PanelSDOffice(theHC,theUS);
                    PanelSetupXPer panelSetupOffice = new PanelSetupXPer(theHC,theUS,psi);
                    initTreeNodePS(c_serv,panelSetupOffice,PanelSDOffice.TITLE);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeServicePointOffice()){
//                    PanelSDOfficeInCup psi = new PanelSDOfficeInCup(theHC,theUS,theHD);
//                    PanelSetupXPer panelSetupOfficeInCup = new PanelSetupXPer(theHC,theUS,psi);
//                    panelSetupOfficeInCup.setActiveVisible();
//                    initTreeNode(c_serv,panelSetupOfficeInCup,PanelSDOfficeInCup.TITLE);

                    PanelSetupOfficeInCup panelSetupOfficeInCup = new PanelSetupOfficeInCup(theHC,theUS);
                    initTreeNodePS(c_serv,panelSetupOfficeInCup,PANEL_SETUP_OFFICE_INCUP);
                }
                if(is_admin){       //+1
//                    PanelSDOfficeInCup psi = new PanelSDOfficeInCup(theHC,theUS,theHD);
//                    PanelSetupXPer panelSetupOfficeInCup = new PanelSetupXPer(theHC,theUS,psi);
//                    panelSetupOfficeInCup.setActiveVisible();
//                    initTreeNode(c_serv,panelSetupOfficeInCup,PanelSDOfficeInCup.TITLE);

                    PanelSetupRoom panelSetupRoom = new PanelSetupRoom();
                    panelSetupRoom.setControl(theHC);
                    initTreeNode(c_serv,panelSetupRoom,PANEL_SETUP_ROOM);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeServicePointQueue()){
                    PanelSDQueueVisit psi = new PanelSDQueueVisit(theHC,theUS);
                    PanelSetupXPer panelSetupQueueVisit = new PanelSetupXPer(theHC,theUS,psi);
                    panelSetupQueueVisit.setActiveVisible();
                    initTreeNode(c_serv,panelSetupQueueVisit,PanelSDQueueVisit.TITLE);

                    //PanelSetupQueueVisit panelSetupQueueVisit = new PanelSetupQueueVisit(theHC,theUS);
                    //initTreeNodePS(c_serv,panelSetupQueueVisit,PANEL_SETUP_QUEUE_VISIT);
                }
            }
            if(is_admin || theHO.theGActionAuthV.isReadTreeGroupItem()){ //default true
                theTreeNode.add(c_order);
                if(is_admin || theHO.theGActionAuthV.isReadTreeGroupItemOrder()){
                    PanelSDOrderGroup psi = new PanelSDOrderGroup(theHC,theUS);
                    PanelSetupXPer panelSetupOrderGroup = new PanelSetupXPer(theHC,theUS,psi);
                    panelSetupOrderGroup.setActiveVisible();
                    initTreeNode(c_order,panelSetupOrderGroup,PanelSDOrderGroup.TITLE);

                    //panelSetupOrderGroup = new PanelSetupOrderGroup(theHC,theUS);
                    //initTreeNode(c_order,panelSetupOrderGroup,PANEL_SETUP_ORDER);
                    //panelSetupOrderGroup.addDepPanel(panelSetupItem);
                    //panelSetupOrderGroup.addDepPanel(panelSDContract);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeGroupItemBill()){
                    PanelSDBill psi = new PanelSDBill(theHC,theUS);
                    PanelSetupXPer panelSetupBill = new PanelSetupXPer(theHC,theUS,psi);
                    panelSetupBill.setActiveVisible();
                    initTreeNode(c_order,panelSetupBill,PanelSDBill.TITLE);

                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeGroupItemStandard()){
                    PanelSD16Group psi = new PanelSD16Group(theHC,theUS);
                    PanelSetupXPer panelSetup16Group = new PanelSetupXPer(theHC,theUS,psi);
                    panelSetup16Group.setActiveVisible();
                    initTreeNode(c_order,panelSetup16Group,PanelSD16Group.TITLE);

                }
            }
        ////////////////////////////////////////////////////////////////////////////
            if(is_admin || theHO.theGActionAuthV.isReadTreeItem())
            {//default true
                theTreeNode.add(c_treat);
                if(is_admin || theHO.theGActionAuthV.isReadTreeItemOrder()){
                    if(panelSetupItem == null)
                        panelSetupItem = new PanelSetupItem(theHC,theUS);
                    initTreeNode(c_treat,panelSetupItem,PANEL_SETUP_ORDER_ITEM);
                    if(panelSetupItemPrice == null)
                        panelSetupItemPrice = new PanelSetupItemPrice(theHC,theUS);
                    initTreeNode(c_treat,panelSetupItemPrice,PANEL_SETUP_ORDER_ITEM_PRICE);
                    if(panelSetupItemDose == null)
                        panelSetupItemDose = new PanelSetupItemDose(theHC,theUS);
                    //รอหน่อยนะต้องปรับฐานข้อมูลไม่งั้นก็ใช้งานได้ตั้งนานแล้ว henbe comment
                    initTreeNode(c_treat,panelSetupItemDose,PANEL_SETUP_ORDER_ITEM_DOSE);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeItemOrderAuto()){
                    PanelSDAutoOrderItem psi = new PanelSDAutoOrderItem(theHC,theUS);
                    PanelSetupXPer panelSetupAutoOrderItem = new PanelSetupXPer(theHC,theUS,psi);
                    panelSetupAutoOrderItem.setActiveVisible();
                    initTreeNode(c_treat,panelSetupAutoOrderItem,PanelSDAutoOrderItem.TITLE);

                    //panelSetupAutoOrderItem = new PanelSetupAutoOrderItem(theHC,theUS);
                    //initTreeNode(c_treat,panelSetupAutoOrderItem,PANEL_SETUP_AUTO_ORDER_ITEM);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeItemVitalTemplate()){
                    panelSetupVitalTemplate = new PanelSetupVitalTemplate(theHC,theUS);
                    initTreeNode(c_treat,panelSetupVitalTemplate,PANEL_SETUP_VITAL_TEMPLATE);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeItemOrderSet()){
                    panelSetupDoctorDrugSet = new PanelSetupDoctorDrugSet(theHC,theUS);
                    initTreeNode(c_treat,panelSetupDoctorDrugSet,PANEL_SETUP_DOCTOR_DRUGSET);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeItemDxTemplate())    {
                    PanelSDGuide psi = new PanelSDGuide(theHC,theUS);
                    PanelSetupXPer PanelSetupGuide = new PanelSetupXPer(theHC,theUS,psi);
                    PanelSetupGuide.setActiveVisible();
                    initTreeNode(c_treat,PanelSetupGuide,PanelSDGuide.TITLE);

                    //PanelSetupGuide panel = new PanelSetupGuide(theHC,theUS);
                    //initTreeNode(c_treat,panel,PANEL_SETUP_GUIDE_AFTER_DX);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeItemDxTemplate())  {
                    PanelSDDxTemplate psi = new PanelSDDxTemplate(theHC,theUS);
                    PanelSetupXPer PanelSetupDxTemplate = new PanelSetupXPer(theHC,theUS,psi);
                    PanelSetupDxTemplate.setActiveVisible();
                    initTreeNode(c_treat,PanelSetupDxTemplate,PanelSDDxTemplate.TITLE);

                    //PanelSetupDxTemplate panel = new PanelSetupDxTemplate(theHC,theUS);
                    //initTreeNode(c_treat,panel,PANEL_SETUP_DX_TEMPLATE);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeBodyOrgan()){
                    PanelSDBodyOrgan psi = new PanelSDBodyOrgan(theHC,theUS);
                    PanelSetupXPer PanelSetupBodyOrgan = new PanelSetupXPer(theHC,theUS,psi);
                    PanelSetupBodyOrgan.setActiveVisible();
                    initTreeNode(c_treat,PanelSetupBodyOrgan,PanelSDBodyOrgan.TITLE);

                    //PanelSetupBodyOrgan panel = new PanelSetupBodyOrgan(theHC,theUS);
                    //PanelSetupBodyOrgan amp:11/04/2549 เป็น panel สำหรับจัดการอวัยวะร่างกาย

                    //PanelSetupBodyOrgan panel = new PanelSetupBodyOrgan(theHC,theUS);//amp:26/04/2549
                    //initTreeNode(c_treat,panel,PANEL_SETUP_BODY_ORGANPANEL_SETUP_B);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeNutritionMap()){
                    PanelSetupNutritionMap panel = new PanelSetupNutritionMap(theHC,theUS);//amp:26/04/2549
                    initTreeNode(c_treat,panel,PANEL_SETUP_NUTRITIONMAP);
                }
            }
            if(is_admin || theHO.theGActionAuthV.isReadTreeItem())
            {//default true
                theTreeNode.add(c_labxray);
                if(is_admin || theHO.theGActionAuthV.isReadTreeLabResultType()){
                      PanelSDResultListType psi = new PanelSDResultListType(theHC,theUS);
                    PanelSetupXPer panelSetupResultListType = new PanelSetupXPer(theHC,theUS,psi);
                    panelSetupResultListType.setActiveVisible();
                    initTreeNode(c_labxray,panelSetupResultListType,PanelSDResultListType.TITLE);

                   //PanelSetupResultListType panel = new PanelSetupResultListType(theHC,theUS);
                   //initTreeNode(c_labxray,panel,PANEL_SETUP_LAB_RESULTTYPE);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeItemXraySide())    {
                    PanelSDXRayLeteral psi = new PanelSDXRayLeteral(theHC,theUS);
                    PanelSetupXPer panelSetupXRayLeteral = new PanelSetupXPer(theHC,theUS,psi);
                    panelSetupXRayLeteral.setActiveVisible();
                    initTreeNode(c_labxray,panelSetupXRayLeteral,PanelSDXRayLeteral.TITLE);

                    //PanelSetupXRayLeteral panel = new PanelSetupXRayLeteral(theHC,theUS);
                   // initTreeNode(c_labxray,panel,PANEL_SETUP_XRAY_LETERAL);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeItemXrayPosition()) {
                    PanelSDXRayPosition psi = new PanelSDXRayPosition(theHC,theUS);
                    PanelSetupXPer panelSetupXRayPosition = new PanelSetupXPer(theHC,theUS,psi);
                    panelSetupXRayPosition.setActiveVisible();
                    initTreeNode(c_labxray,panelSetupXRayPosition,PanelSDXRayPosition.TITLE);

                    //PanelSetupXRayPosition panel = new PanelSetupXRayPosition(theHC,theUS);
                    //initTreeNode(c_labxray,panel,PANEL_SETUP_XRAY_POSITION);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeItemXrayFilm())     {
                    //PanelSDXRayFilmSize psi = new PanelSDXRayFilmSize(theHC,theUS);
                    //PanelSetupXPer panelSetupXRayFilmSize = new PanelSetupXPer(theHC,theUS,psi);
                    //panelSetupXRayFilmSize.setActiveVisible();
                    //initTreeNode(c_labxray,panelSetupXRayFilmSize,PanelSDXRayFilmSize.TITLE);

                    PanelSetupXRayFilmSize panel = new PanelSetupXRayFilmSize(theHC,theUS);
                    initTreeNode(c_labxray,panel,PANEL_SETUP_XRAY_FILMSIZE);
                }
            }
            if(is_admin || theHO.theGActionAuthV.isReadTreeDrugDetail())
            {//default true
                theTreeNode.add(c_drug);
                if(is_admin || theHO.theGActionAuthV.isReadTreeDrugDetailFrequency()){
                    PanelSDDrugFrequency psi = new PanelSDDrugFrequency(theHC,theUS);
                    PanelSetupXPer panelSetupDrugFrequency = new PanelSetupXPer(theHC,theUS,psi);
                    panelSetupDrugFrequency.setActiveVisible();
                    initTreeNode(c_drug,panelSetupDrugFrequency,PanelSDDrugFrequency.TITLE);

                    //PanelSetupDrugFrequency panel = new PanelSetupDrugFrequency(theHC,theUS);
                    //initTreeNode(c_drug,panel,PANEL_SETUP_DRUG_FREQUENCY);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeDrugDetailInstruction()){
                    PanelSDDrugInstruction psi = new PanelSDDrugInstruction(theHC,theUS);
                    PanelSetupXPer panelSetupDrugInstruction = new PanelSetupXPer(theHC,theUS,psi);
                    panelSetupDrugInstruction.setActiveVisible();
                    initTreeNode(c_drug,panelSetupDrugInstruction,PanelSDDrugInstruction.TITLE);

                    //PanelSetupDrugInstruction panel = new PanelSetupDrugInstruction(theHC,theUS);
                    //initTreeNode(c_drug,panel,PANEL_SETUP_DRUG_INSTRUCTION);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeDrugDetailUom()){
                    PanelSDUom2 psi = new PanelSDUom2(theHC,theUS);
                    PanelSetupXPer panelSetupUom2 = new PanelSetupXPer(theHC,theUS,psi);
                    panelSetupUom2.setActiveVisible();
                    initTreeNode(c_drug,panelSetupUom2,PanelSDUom2.TITLE);

                    //PanelSetupUom2 panel = new PanelSetupUom2(theHC,theUS);
                    //initTreeNode(c_drug,panel,PANEL_SETUP_UOM);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeDrugDoseShortcut()){
                    PanelSDDrugDoseShortcut psi = new PanelSDDrugDoseShortcut(theHC,theUS);
                    PanelSetupXPer panelSetupDrugDoseShortcut = new PanelSetupXPer(theHC,theUS,psi);
                    panelSetupDrugDoseShortcut.setActiveVisible();
                    initTreeNode(c_drug,panelSetupDrugDoseShortcut,PanelSDDrugDoseShortcut.TITLE);

                    //PanelSetupDrugDoseShortcut panel = new PanelSetupDrugDoseShortcut(theHC,theUS);
                    //initTreeNode(c_drug,panel,PANEL_SETUP_DRUG_DOSE_SHORTCUT);
                }
/*
                if(is_admin || theHO.theGActionAuthV.isReadTreeDrugDosePrint()){
                    PanelSDSetupDrugDosePrint psi = new PanelSDSetupDrugDosePrint(theHC,theUS);
                    PanelSetupXPer panelSetupDrugDosePrint = new PanelSetupXPer(theHC,theUS,psi);
                    panelSetupDrugDosePrint.setActiveVisible();
                    initTreeNode(c_drug,panelSetupDrugDosePrint,PanelSDSetupDrugDosePrint.TITLE);
                }
*/
                if(is_admin || theHO.theGActionAuthV.isReadTreeDrugStandard()){
                    PanelSDDrugStandard psi = new PanelSDDrugStandard(theHC,theUS);
                    PanelSetupXPer panelSetupDrugStandard = new PanelSetupXPer(theHC,theUS,psi);
                    panelSetupDrugStandard.setActiveVisible();
                    initTreeNode(c_drug,panelSetupDrugStandard,PanelSDDrugStandard.TITLE);

                    //PanelSetupDrugStandard panel = new PanelSetupDrugStandard(theHC,theUS);//amp:14/03/2549 เป็น panel สำหรับจัดการยามาตรฐาน
                    //initTreeNode(c_drug,panel,PANEL_SETUP_DRUG_STANDARD);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeDrugStandardMapItem()){
                    PanelSetupDrugStandardMapItem panel = new PanelSetupDrugStandardMapItem(theHC,theUS);//amp:14/03/2549
                    initTreeNode(c_drug,panel,PANEL_SETUP_DRUG_STANDARD_MAP_ITEM);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeDrugInteraction()){
                    PanelSetupDrugInteraction panel = new PanelSetupDrugInteraction(theHC,theUS);//amp:14/03/2549
                    initTreeNode(c_drug,panel,PANEL_SETUP_DRUG_INTERACTION);
                }

            }
            if(is_admin || theHO.theGActionAuthV.isReadTreeIcd()){//default true
                theTreeNode.add(c_icd);
                if(is_admin || theHO.theGActionAuthV.isReadTreeIcdIcd10()){
                    PanelSDICD10 psi = new PanelSDICD10(theHC,theUS);
                    PanelSetupXPer panel = new PanelSetupXPer(theHC,theUS,psi);
                    initTreeNode(c_icd,panel,PanelSDICD10.TITLE);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeIcdIcd9()){
                    PanelSDICD9 psi = new PanelSDICD9(theHC,theUS);
                    PanelSetupXPer panel = new PanelSetupXPer(theHC,theUS,psi);
                    initTreeNode(c_icd,panel,PanelSDICD9.TITLE);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeIcdChronic()){
                    panelSetupGroupChronic = new PanelSetupGroupChronic(theHC,theUS);
                    initTreeNode(c_icd,panelSetupGroupChronic,PANEL_SETUP_CHRONIC);
                    panelSetupGroupChronic.addDepPanel(panelSetupGroupIcd10);
                    panelSetupGroupChronic.addDepPanel(panelSetupNCDGroup);
                }
                /**pu : 10/09/08*/
                /**pu : ยกเลิก 14/10/08*/
//                if(is_admin || theHO.theGActionAuthV.isReadTreeIcdSurveil())
//                {
//                    panelSetupGroupSurveil = new PanelSetupGroupSurveil(theHC,theUS);
//                    initTreeNode(c_icd,panelSetupGroupSurveil,PANEL_SETUP_SURVEIL);
//                    panelSetupGroupSurveil.addDepPanel(panelSetupGroupIcd10);
//                    panelSetupGroupSurveil.addDepPanel(panelSetupNCDGroup);
//                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeNutritionMap()){
                    PanelSDNCDGroup psi = new PanelSDNCDGroup(theHC,theUS);
                    PanelSetupXPer panelSetupNCDGroup = new PanelSetupXPer(theHC,theUS,psi);
                    panelSetupNCDGroup.setActiveVisible();
                    initTreeNode(c_icd,panelSetupNCDGroup,PanelSDNCDGroup.TITLE);

                    //panelSetupNCDGroup = new PanelSetupNCDGroup(theHC,theUS);//amp:16/06/2549
                    //initTreeNode(c_icd,panelSetupNCDGroup,PANEL_SETUP_NCD_GROUP);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeIcdGroupIcd10()){
                    PanelSDGroupIcd10 psi = new PanelSDGroupIcd10(theHC,theUS);
                    PanelSetupXPer panelSetupGroupIcd10 = new PanelSetupXPer(theHC,theUS,psi);
                    panelSetupGroupIcd10.setActiveVisible();
                    initTreeNode(c_icd,panelSetupGroupIcd10,PanelSDGroupIcd10.TITLE);

                    //panelSetupGroupIcd10 = new PanelSetupGroupIcd10(theHC,theUS);
                    //initTreeNode(c_icd,panelSetupGroupIcd10,PANEL_SETUP_GROUP_ICD10);
                }
            }
            if(is_admin || theHO.theGActionAuthV.isReadTreeBilling()){//default true
            theTreeNode.add(c_pay);
                if(is_admin || theHO.theGActionAuthV.isReadTreeBillingPayer()){
                   PanelSDPayer psi = new PanelSDPayer(theHC,theUS);
                   PanelSetupXPer panelSetupPayer = new PanelSetupXPer(theHC,theUS,psi);
                   panelSetupPayer.setActiveVisible();
                   initTreeNode(c_pay,panelSetupPayer,PanelSDPayer.TITLE);

                    //panelSetupPayer = new PanelSetupPayer(theHC,theUS);
                    //initTreeNode(c_pay,panelSetupPayer,PANEL_SETUP_PAYER);
                    //panelSetupPayer.addDepPanel(panelSetupPlan);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeBillingContract()){
                    PanelSDContract psi = new PanelSDContract(theHC,theUS);
                    PanelSetupXPer panel = new PanelSetupXPer(theHC,theUS,psi);
                    initTreeNode(c_pay,panel,PANEL_SETUP_CONTRACT);
//                    psi.addDepPanel(panelSDPlan);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeBillingPlan()){
                   PanelSDPlan psi = new PanelSDPlan(theHC,theUS);
                   PanelSetupXPer panel = new PanelSetupXPer(theHC,theUS,psi);
                   panel.setActiveVisible();
                   initTreeNode(c_pay,panel,PanelSDPlan.TITLE);

                    //panelSetupPlan = new PanelSetupPlan(theHC,theUS);
                    //initTreeNode(c_pay,panelSetupPlan,PANEL_SETUP_PLAN);
                    //panelSetupPlan.addDepPanel(panelSetupAutoOrderItem);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeBillingPlan()){
                    panelSetupPayment0 = new PanelSetupPayment0(theHC,theUS);
                    initTreeNode(c_pay,panelSetupPayment0,PANEL_SETUP_REMAIN_0);
                }
            }
            if(is_admin || theHO.theGActionAuthV.isReadTreeOther()){//default true
                theTreeNode.add(c_other);
                if(is_admin || theHO.theGActionAuthV.isReadTreeOtherUser()){
                   panelSetupEmployee = new PanelSDEmployee(theHC,theUS);
                   PanelSetupXPer panel = new PanelSetupXPer(theHC,theUS,panelSetupEmployee);
                   panel.setActiveVisible();
                   initTreeNode(c_other,panel,PanelSDEmployee.TITLE);

                    //panel = new PanelSetupEmployee(theHC,theUS);
                    //initTreeNode(c_other,panel,PANEL_SETUP_EMPLOYEE);
                    //panel.addDepPanel(panelSetupDoctorDrugSet);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeOtherSite()){
                    PanelSetupSite panel = new PanelSetupSite(theHC,theUS);
                    initTreeNode(c_other,panel,PANEL_SETUP_SITE);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeOtherSequence()){
                    PanelSDSequenceData psi = new PanelSDSequenceData(theHC,theUS);
                    PanelSetupXPer panel = new PanelSetupXPer(theHC,theUS,psi);
                    initTreeNode(c_other,panel,PanelSDSequenceData.TITLE);
                }
                if(is_admin || theHO.theGActionAuthV.isReadTreeNews()){
                    PanelNews psi = new PanelNews();
                    psi.setControl(theHC.theSystemControl,theUS);
                    initTreeNode(c_other,psi,PANEL_SETUP_NEWS);
                }
                
                if(is_admin || theHO.theGActionAuthV.isReadTreeNews()){     //+1
                    PanelConvertData psi = new PanelConvertData(theHC, theUS);
                    //psi.setControl(theHC.theSystemControl,theUS);
                    initTreeNode(c_other,psi,PANEL_SETUP_CONVERT);
                }
            }
    }
    
    public static void main(String[] argc){
        Constant.println(Constant.getTextBundle("GROUP_SETUP_ADMIN"));
    }
}
