package com.hosv3.gui.frame;
/*
 * FrameConstant.java
 *
 * Created on 29 กันยายน 2546, 9:31 น.
 */
import com.hosv3.HosApp;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import com.hosv3.usecase.transaction.*;
import com.hosv3.control.*;
import com.hosv3.subject.*;
import com.hosv3.object.*;
import com.hosv3.gui.dialog.*;
import com.hosv3.gui.panel.transaction.*;
import com.hosv3.utility.*;
import com.hosv3.utility.connection.*;
import com.hosv3.object.printobject.*;

import com.hospital_os.object.*;
import com.hospital_os.utility.Audio;
import com.hosv3.utility.Constant;
import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.IOStream;
import com.hospital_os.usecase.connection.ModuleInfTool;
import com.hosv3.gui.component.FontDialog;
import com.hosv3.gui.component.HosMenu;
import com.hosv3.gui.dialog.HosDialog;
/**
 *
 * @author  Administrator
 * * 1.  60-10-27 เรื่อง FTP scan เอกสารเก็บเข้า server
 *  Modify doc 10.
 */
public class FrameMain extends javax.swing.JFrame implements UpdateStatus
,ManageVisitResp,ManageVitalResp,ManagePatientResp,ManageDiagnosisResp
,ManageBillingResp,ManageLabXrayResp,ManageOrderResp {
    /** ใช้ในการควบคุมการทำงานร่วมกันของ GUI */
	public static final long  serialVersionUID = 0;    
	HosObject theHO;
    HosControl theHC;
 //  Hosv2Control theOHC ;
    HosSubject theHS;
    HosPanel theHP;
    HosDialog theHD;
    UpdateStatus theUS ;
    ////////henbe add for external module//////////
    public Vector theModuleV;
    ////////henbe add for external module//////////
    private Vector vORID;
    private OrderItemReceiveDrug theORID;
    public Vector theTransfer;
    
    private String auth = Authentication.REGIST;
    private int preview = PrintControl.MODE_PREVIEW; //ใช้ในการพิมพ์ข้อมูลเมื่อต้องให้แสดงข้อมูลก่อนพิมพ์
    private int nopreview = PrintControl.MODE_PRINT; //ใช้ในการพิมพ์ข้อมูลเมื่อไม่ต้องการให้แสดงข้อมูลก่อนพิมพ์
    DialogChooseOrderItemPrint theDialogChooseOrderItemPrint = null;
    private JTabbedPane theJTabbedPane;
    
    public static String theme="";
    HosApp theHosApp;
    DialogTheme2 theDialogTheme;

    private String authen_old;
    private Splash theSplash;
    private HosMenu hosMenu;
    PanelConfigWS thePanelConfigWS;
    /** Creates new form FrameMain */
    public FrameMain()   {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/hospital_os/images/Icon.gif")));
//        initMenuIcon();
        jMenuItemViewHO.setVisible(false);
        jMenuItemReport.setVisible(false);
        jMenuItemAccidentGroup.setVisible(false);
//        jMenuItem1.setVisible(false);
        setSize(800,600);
    }
    
    public void setGui(){
        //will be move in to HosPanel
        setTitle(theHO.theEmployee.fname
        + "  " + theHO.theEmployee.lname
        + " - HospitalOS CE"
        + " Version " + theHC.theSystemControl.getAppVersion().app_code);

        java.awt.GridBagConstraints grid = new java.awt.GridBagConstraints();
        grid.fill = GridBagConstraints.BOTH;
        grid.weightx = 1.0;
        grid.weighty = 1.0;
        theJTabbedPane = theHP.theJTabbedPane;
        theJTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                JTabbedPaneStateChanged(evt);
            }
        });
        jPanelCenter.add(theHP.theJTabbedPane,grid);        
        hosMenu = new HosMenu();
        hosMenu.setText(GuiLang.setLanguage("กำหนดเอง"));
        hosMenu.initPrintMenu("hprinting/byuser");
        hosMenu.setHosObject(theHO,theHC.theConnectionInf);
        this.jMenuPrintAll.add(hosMenu,0);
    }
    
    public void setControl(HosControl hc,HosPanel hp,HosDialog hd)
    {   
        theHC = hc;
        theHO = hc.theHO;
        theHS = hc.theHS;
        theHP = hp;
        theHC.setHosPanel(theHP);
        theHD = hd;
        theUS = theHC.theUS;
        auth = theHO.theEmployee.authentication_id;
        /*tong comment date 24/05/48*/
        theHS.theVisitSubject.attachManageVisit(this);
        theHS.theOrderSubject.attachManageOrder(this);
        theHS.thePatientSubject.attachManagePatient(this);
        theHS.theVitalSubject.attachManageVital(this);
        theHS.theBillingSubject.attachManageBilling(this);
        theHS.theDiagnosisSubject.attachManageDiagnosis(this);
        theHS.theResultSubject.attachManageLab(this);
        theHS.theResultSubject.attachManageXray(this);
        
        setGui();
        initUserAuthen(theHO.theEmployee);
        java.awt.GridBagConstraints grid = new java.awt.GridBagConstraints();
        grid.fill = GridBagConstraints.BOTH;
        grid.weightx = 1.0;
        grid.weighty = 1.0;
        jPanelTop.add(theHP.thePanelCurrentVisit,grid);
        setVisit(null);
        setLanguage(null);        
        authen_old = theHO.theEmployee.authentication_id;
        theHO.theWSConfig = theHC.theSystemControl.readWSConfig("1");
        //checkUser();
    }   
    
    ////////henbe add for external module//////////
    public void showModule(Vector theModuleV) throws Exception
    {
        this.theModuleV = theModuleV;
        auth = theHO.theEmployee.authentication_id;
        boolean isUsedMenu = false;
        int size = theModuleV.size();
        jMenuModule.removeAll();
        
        ///Module Performance Check////////////////////////////////////////////////////
        ConnectionDBMgr cm = (ConnectionDBMgr)theHC.theConnectionInf;
        Vector module_sql = new Vector();
        Vector module_name = new Vector();
        module_name.add("HospitalOSV3");
        module_sql.add(String.valueOf(cm.sql_total));
        ///Module Performance Check////////////////////////////////////////////////////

        for(int i=0;i<size;i++)
        {
            cm.sql_total = 0;
            try{
                ModuleInfTool mi = (ModuleInfTool)theModuleV.get(i);
                mi.setHosControl(this.theHC);
                ///////////////////////////////////////////////////////////////////
                //add menu item
                Constant.println("henbe_test__Class" + mi.getClass().toString());
                Constant.println("henbe_test__getJMenu!=null______" + (mi.getJMenu()!=null));
                Constant.println("henbe_test__isJPanelVisible______"+ mi.isJPanelVisible(auth));
                Constant.println("henbe_test__isJMenuVisible______"+ mi.isJMenuVisible(auth));
                Constant.println("henbe_test__isInMenuStandard______" + mi.isInMenuStandard());
                Constant.println("henbe_test__getVectorJMenuItem!=null______" + (mi.getVectorJMenuItem(auth)!=null));
                Constant.println("henbe_test__getVectorJPanel!=null______"+ (mi.getVectorJPanel()!=null));

                ///////////////////////////////////////////////////////////////////
                if(mi.isJMenuVisible(auth) && mi.getJMenu()!=null){   
                    if(mi.isInMenuStandard()){
                        jMenuModule.add(mi.getJMenu());
                        isUsedMenu = true;
                    }
                    else 
                        jMenuBar.add(mi.getJMenu(),jMenuBar.getMenuCount()-1);
                }
                ///////////////////////////////////////////////////////////////////
                Vector mitem = mi.getVectorJMenuItem(auth);
                if(mitem!=null && !mitem.isEmpty()){
                    jMenuModule.add(new JSeparator());
                    for(int j=0;j<mitem.size();j++){
                        Constant.println("henbe_test Class cast:" + mitem.get(j).getClass().toString());
                        MenuItemPlugIn item = (MenuItemPlugIn)mitem.get(j);
                        if(item.authen) {
                            jMenuModule.add(item.theJMenuItem);
                            isUsedMenu = true;
                        }
                    }
                }
                ////ขั้นตอนการ add Panel//////////////////////////////////////////////
                ///////////////////////////////////////////////////////////////////
                Vector vPanel = mi.getVectorJPanel();
                for(int j=0;vPanel!=null && j<vPanel.size() && mi.isJPanelVisible(auth);j++){
                    ComponentsPanel cp = (ComponentsPanel)vPanel.get(j);
                    boolean b = theHP.replacePanel(cp.index,cp.theJPanel,cp.panelName);
                    if(cp.index.equals("0"))
                    {
                        jPanelTop.remove(0);
                        java.awt.GridBagConstraints grid = new java.awt.GridBagConstraints();
                        grid.fill = GridBagConstraints.BOTH;
                        grid.weightx = 1.0;
                        grid.weighty = 1.0;
                        jPanelTop.add(theHP.thePanelCurrentVisit,grid);
                    }
                }
                ///Module Performance Check////////////////////////////////////////////////////
                module_name.add(mi.getClass().toString());
                module_sql.add(String.valueOf(cm.sql_total));
                ///Module Performance Check////////////////////////////////////////////////////
            }
            catch(Exception e){    e.printStackTrace(Constant.getPrintStream());    }
        }
        ///Module Performance Check////////////////////////////////////////////////////
        for(int i=0,size1=module_sql.size();i<size1;i++)
        {
            String total = (String)module_sql.get(i);
            String name = (String)module_name.get(i);
            Constant.println("MODULE SQL Total__"+name + ":" + total);
        }
        ///Module Performance Check////////////////////////////////////////////////////
        jMenuModule.setVisible(isUsedMenu);        
    }

    public void setSplash(Splash sp) {
        theSplash = sp;
    }
    ////////henbe add for external module//////////
    private void initMenuIcon()
    {
        jMenuItemSocialData.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("VIEW_DATAPATIENT"))));
        jMenuItemQuit.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("QUIT"))));
        jMenuItemRefresh.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("REFRESH"))));
        jMenuItemUnlock.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("UNLOCK"))));
        jMenuItemSearchPatient.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("SEARCH"))));
        jMenuItemSocialData.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("VIEW_DATAPATIENT"))));
        jMenuItemVisit.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("VIEW_VISIT"))));
//        jMenuItemVitalSign.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("VIEW_VITAL_SIGN"))));
        jMenuItemOrder.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("VIEW_ORDER"))));
        jMenuItemIcd.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("VIEW_DIAG"))));
        jMenuItemBill.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("VIEW_BILLING"))));
        jMenuItemLab.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("VIEW_LAB"))));
        jMenuItemXray.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("VIEW_XRAY"))));
        jMenuItemAppointment.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("PATIENT_APPOINTMENT"))));
        jMenuItemListAppointment.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("PATIENT_APPOINTMENT_ALL"))));
        jMenuItemVisitHistory.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("PATIENT_HISTORY"))));
        jMenuItemOrderHistory.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("PATIENT_ORDER_HISTORY"))));
        //jMenuItemBillingHistory.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("PATIENT_CASH_HISTORY"))));
        jMenuItemSetPrefix.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("MAP_PREFIX"))));
        jMenuItemViewSequence.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("SEQUENCE_DATA"))));
        jMenuItemLabreferOut.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("LAB_REFER_IN"))));
        jMenuItemLabreferin.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("LAB_REFER_OUT"))));
        jMenuItemReferPatient.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("PATIENT_REFER"))));
        jMenuItemWaitDoctorDischarge.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("DROP_TRANSACTION"))));
        jMenuItemDropVisit.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("DROP_VISIT"))));
        //set icon ให้มันผ่านทางหน้าจอ gui ของ netbeans แล้ว
        //jMenuItemDeath.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("DEATH"))));
        //jMenuItemAccident.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("DROP_VISIT"))));
        //jMenuItemServeil.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("SERVEIL"))));
        //jMenuItemChronic.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("DEATH"))));
        //jMenuItemReceiveDrug.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("DRUG_RECEIVE"))));
        jMenuItemAdmit.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("ADMIT"))));
        jMenuItemObserve.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("OBSERV"))));
        jMenuItemSendBackWard.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("SEND_TO_WARD"))));
        jMenuItemHistoryOrderContinue.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("DRUG_RECEIVE_HISTORY"))));
        jMenuItemOrderSet.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("ORDER_SET"))));
        jMenuItemOrderCotinueDrug.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("DRUG_CONTINUE"))));
        jMenuPrintSummary.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("PRINT_SUM_REPORT"))));
        jMenuSelectDrugList.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("PRINT_DRUG_RX"))));
        jMenuItemReverseFinancial.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("UNDO"))));
        jMenuItemReverseDoctor.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("UNDO"))));
        jMenuItemConnection.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("CONNECTION"))));
        jMenuItemAbout.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("ABOUT"))));
        jMenuItemSetPartPrint.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("PRINTER"))));
        //comment henbe 290406
        //jMenuItemSetPartFont.setVisible(false);
        jMenuItemSetPartFont.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("CLOCK"))));
        jMenuItemReverseAdmit.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("OBSERV"))));
        jMenuItemReverseObserve.setIcon(new ImageIcon(getClass().getResource(Constant.getTextBundleImage("OBSERV"))));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jPanelStatus = new javax.swing.JPanel();
        jLabelStatus = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        clockLabel1 = new com.hospital_os.utility.ClockLabel();
        jPanelCenter = new javax.swing.JPanel();
        jPanelTop = new javax.swing.JPanel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemRefresh = new javax.swing.JMenuItem();
        jMenuItemLogin = new javax.swing.JMenuItem();
        jMenuItemTheme = new javax.swing.JMenuItem();
        jMenuItemFont = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jMenuItemUnlock = new javax.swing.JMenuItem();
        jMenuItemSearchPatient = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItemQuit = new javax.swing.JMenuItem();
        jMenuTab = new javax.swing.JMenu();
        jMenuItemSocialData = new javax.swing.JMenuItem();
        jMenuItemVisit = new javax.swing.JMenuItem();
        jMenuItemVitalSign = new javax.swing.JMenuItem();
        jMenuItemOrder = new javax.swing.JMenuItem();
        jMenuItemIcd = new javax.swing.JMenuItem();
        jMenuItemBill = new javax.swing.JMenuItem();
        jMenuItemLab = new javax.swing.JMenuItem();
        jMenuItemXray = new javax.swing.JMenuItem();
        jMenuTool = new javax.swing.JMenu();
        jMenuItemAppointment = new javax.swing.JMenuItem();
        jMenuItemListAppointment = new javax.swing.JMenuItem();
        jMenuItemVisitHistory = new javax.swing.JMenuItem();
        jMenuItemOrderHistory = new javax.swing.JMenuItem();
        jMenuItemBillingHistory = new javax.swing.JMenuItem();
        jMenuItemSetPrefix = new javax.swing.JMenuItem();
        jMenuItemViewSequence = new javax.swing.JMenuItem();
        jMenuItemShowDxDoctor = new javax.swing.JMenuItem();
        jMenuItemBorrowFilmXray = new javax.swing.JMenuItem();
        jMenuItemBorrowOpdCard = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JSeparator();
        jMenuItemLabreferOut = new javax.swing.JMenuItem();
        jMenuItemLabreferin = new javax.swing.JMenuItem();
        jMenuItemReferPatient = new javax.swing.JMenuItem();
        jMenuItemReport = new javax.swing.JMenuItem();
        jMenuItemScanOPDRecord = new javax.swing.JMenuItem();
        jMenuPatientGeneral = new javax.swing.JMenu();
        jMenuItemWaitDoctorDischarge = new javax.swing.JMenuItem();
        jMenuItemDropVisit = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JSeparator();
        jMenuItemAccident = new javax.swing.JMenuItem();
        jMenuItemAccidentGroup = new javax.swing.JMenuItem();
        jMenuItemDeath = new javax.swing.JMenuItem();
        jMenuItemChronic = new javax.swing.JMenuItem();
        jMenuItemServeil = new javax.swing.JMenuItem();
        jMenuIpd = new javax.swing.JMenu();
        jMenuItemAdmit = new javax.swing.JMenuItem();
        jMenuItemReverseAdmit = new javax.swing.JMenuItem();
        jMenuItemObserve = new javax.swing.JMenuItem();
        jMenuItemReverseObserve = new javax.swing.JMenuItem();
        jMenuItemSendBackWard = new javax.swing.JMenuItem();
        jMenuItemHistoryOrderContinue = new javax.swing.JMenuItem();
        jMenuItemDischargeIPD = new javax.swing.JMenuItem();
        jMenuOrder = new javax.swing.JMenu();
        jMenuItemReceiveDrug = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        jMenuItemOrderSet = new javax.swing.JMenuItem();
        jMenuItemOrderListByVn = new javax.swing.JMenuItem();
        jMenuItemOrderListByDate = new javax.swing.JMenuItem();
        jMenuItemOrderCotinueDrug = new javax.swing.JMenuItem();
        jMenuModule = new javax.swing.JMenu();
        jMenuPrintAll = new javax.swing.JMenu();
        jMenuPrintVisitSlip = new javax.swing.JMenu();
        jMenuItemPrintVisitSlip = new javax.swing.JMenuItem();
        jMenuItemPreviewVisitSlip = new javax.swing.JMenuItem();
        jMenuPrintSummary = new javax.swing.JMenu();
        jMenuItemPrintSummaryDischarge = new javax.swing.JMenuItem();
        jMenuItemPreviewSummaryDischarge = new javax.swing.JMenuItem();
        jCheckBoxMenuItemCalDayByHour = new javax.swing.JCheckBoxMenuItem();
        jMenuSelectDrugList = new javax.swing.JMenu();
        jMenuItemPrintSelectDrugList = new javax.swing.JMenuItem();
        jMenuItemPreviewSelectDrugList = new javax.swing.JMenuItem();
        jMenuItemPrintDrugRx = new javax.swing.JMenuItem();
        jMenuOpdCard = new javax.swing.JMenu();
        jMenuItemPrintOpdCard = new javax.swing.JMenuItem();
        jMenuItemPreviewOpdCard = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JSeparator();
        jMenuItemPrintDrugSticker = new javax.swing.JMenuItem();
        jMenuItemPrintIndex = new javax.swing.JMenuItem();
        jMenuItemPrintXNIndex = new javax.swing.JMenuItem();
        jMenuItemLabResult = new javax.swing.JMenuItem();
        jMenuPrintIPDNameCard = new javax.swing.JMenu();
        jMenuItemPrintForPatient = new javax.swing.JMenuItem();
        jMenuItemPrintAllPatientInWard = new javax.swing.JMenuItem();
        jMenuItemPrintIPDnameInChart = new javax.swing.JMenuItem();
        jMenuPrintMedCert = new javax.swing.JMenu();
        jMenuItemPrintMedCertIll = new javax.swing.JMenuItem();
        jMenuItemPrintMedCertInterview = new javax.swing.JMenuItem();
        jMenuItemPrint7Cert = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuReportSumItem = new javax.swing.JMenu();
        jMenuItemPrintReportItem = new javax.swing.JMenuItem();
        jMenuItemPreviewReportItem = new javax.swing.JMenuItem();
        jMenuItemPrintReportItemByItemName = new javax.swing.JMenuItem();
        jMenuItemPreviewReportItemByItemName = new javax.swing.JMenuItem();
        jMenuReportSum16Group = new javax.swing.JMenu();
        jMenuItemPrintReport16Group = new javax.swing.JMenuItem();
        jMenuItemPreveiwReport16Group = new javax.swing.JMenuItem();
        jMenuReportSumGroupBilling = new javax.swing.JMenu();
        jMenuItemPrintReportBilling = new javax.swing.JMenuItem();
        jMenuItemPreveiwReportBilling = new javax.swing.JMenuItem();
        jMenuReportSumGroupItem = new javax.swing.JMenu();
        jMenuItemPrintReportOrder = new javax.swing.JMenuItem();
        jMenuItemPreviewReportOrder = new javax.swing.JMenuItem();
        jMenuReverse = new javax.swing.JMenu();
        jMenuItemReverseFinancial = new javax.swing.JMenuItem();
        jMenuItemReverseDoctor = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemConnection = new javax.swing.JMenuItem();
        jMenuItemAbout = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JSeparator();
        jMenuItemSetPartFont = new javax.swing.JMenuItem();
        jMenuItemSetPartPrint = new javax.swing.JMenuItem();
        jMenuItemViewHO = new javax.swing.JMenuItem();
        jMenuItemNew = new javax.swing.JMenuItem();
        jCheckBoxMenuItemReportBug = new javax.swing.JCheckBoxMenuItem();
        jMenuItemWS = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanelStatus.setLayout(new java.awt.GridBagLayout());

        jLabelStatus.setFont(defaultFont1);
        jLabelStatus.setText("Status");
        jLabelStatus.setMaximumSize(new java.awt.Dimension(4, 24));
        jLabelStatus.setMinimumSize(new java.awt.Dimension(4, 20));
        jLabelStatus.setOpaque(true);
        jLabelStatus.setPreferredSize(new java.awt.Dimension(4, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanelStatus.add(jLabelStatus, gridBagConstraints);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), java.awt.Color.white));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        clockLabel1.setFont(defaultFont1);
        clockLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clockLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(clockLabel1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        jPanelStatus.add(jPanel1, gridBagConstraints);

        getContentPane().add(jPanelStatus, java.awt.BorderLayout.SOUTH);

        jPanelCenter.setLayout(new java.awt.GridBagLayout());
        getContentPane().add(jPanelCenter, java.awt.BorderLayout.CENTER);

        jPanelTop.setLayout(new java.awt.GridBagLayout());
        getContentPane().add(jPanelTop, java.awt.BorderLayout.NORTH);

        jMenuFile.setMnemonic('F');
        jMenuFile.setText("ระบบ");
        jMenuFile.setFont(defaultFont1);
        jMenuFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFileActionPerformed(evt);
            }
        });

        jMenuItemRefresh.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItemRefresh.setFont(defaultFont1);
        jMenuItemRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/Refresh.png"))); // NOI18N
        jMenuItemRefresh.setText("รีเฟรช");
        jMenuItemRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRefreshActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemRefresh);

        jMenuItemLogin.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemLogin.setFont(defaultFont1);
        jMenuItemLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/user.png"))); // NOI18N
        jMenuItemLogin.setText("เปลี่ยนผู้ใช้งาน");
        jMenuItemLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLoginActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemLogin);

        jMenuItemTheme.setFont(defaultFont1);
        jMenuItemTheme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/laf.png"))); // NOI18N
        jMenuItemTheme.setText("Look and Feel");
        jMenuItemTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemThemeActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemTheme);

        jMenuItemFont.setFont(defaultFont1);
        jMenuItemFont.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/ListByDate.png"))); // NOI18N
        jMenuItemFont.setText("Font");
        jMenuItemFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFontActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemFont);
        jMenuFile.add(jSeparator2);

        jMenuItemUnlock.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        jMenuItemUnlock.setFont(defaultFont1);
        jMenuItemUnlock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/Unlock.png"))); // NOI18N
        jMenuItemUnlock.setText("ปลดล็อก");
        jMenuItemUnlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUnlockActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemUnlock);

        jMenuItemSearchPatient.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemSearchPatient.setFont(defaultFont1);
        jMenuItemSearchPatient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/SearchPatient.png"))); // NOI18N
        jMenuItemSearchPatient.setText("ค้นหาผู้ป่วย");
        jMenuItemSearchPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSearchPatientActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemSearchPatient);
        jMenuFile.add(jSeparator1);

        jMenuItemQuit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemQuit.setFont(defaultFont1);
        jMenuItemQuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/1btn.gif"))); // NOI18N
        jMenuItemQuit.setText("ออกจากระบบ");
        jMenuItemQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemQuit);

        jMenuBar.add(jMenuFile);

        jMenuTab.setMnemonic('E');
        jMenuTab.setText("แถบ");
        jMenuTab.setFont(defaultFont1);

        jMenuItemSocialData.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSocialData.setFont(defaultFont1);
        jMenuItemSocialData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/patient.gif"))); // NOI18N
        jMenuItemSocialData.setText("ข้อมูลผู้ป่วย");
        jMenuItemSocialData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSocialDataActionPerformed(evt);
            }
        });
        jMenuTab.add(jMenuItemSocialData);

        jMenuItemVisit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemVisit.setFont(defaultFont1);
        jMenuItemVisit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/service.gif"))); // NOI18N
        jMenuItemVisit.setText("การรับบริการ");
        jMenuItemVisit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVisitActionPerformed(evt);
            }
        });
        jMenuTab.add(jMenuItemVisit);

        jMenuItemVitalSign.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemVitalSign.setFont(defaultFont1);
        jMenuItemVitalSign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/vitalsign.gif"))); // NOI18N
        jMenuItemVitalSign.setText("ปันทึกอาการเจ็บป่วย");
        jMenuItemVitalSign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVitalSignActionPerformed(evt);
            }
        });
        jMenuTab.add(jMenuItemVitalSign);

        jMenuItemOrder.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemOrder.setFont(defaultFont1);
        jMenuItemOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/capsule.gif"))); // NOI18N
        jMenuItemOrder.setText("การตรวจ/รักษา");
        jMenuItemOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOrderActionPerformed(evt);
            }
        });
        jMenuTab.add(jMenuItemOrder);

        jMenuItemIcd.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemIcd.setFont(defaultFont1);
        jMenuItemIcd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/consider.gif"))); // NOI18N
        jMenuItemIcd.setText("การวินิจฉัย");
        jMenuItemIcd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemIcdActionPerformed(evt);
            }
        });
        jMenuTab.add(jMenuItemIcd);

        jMenuItemBill.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemBill.setFont(defaultFont1);
        jMenuItemBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/mo_ney.gif"))); // NOI18N
        jMenuItemBill.setText("การเงิน");
        jMenuItemBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBillActionPerformed(evt);
            }
        });
        jMenuTab.add(jMenuItemBill);

        jMenuItemLab.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_8, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemLab.setFont(defaultFont1);
        jMenuItemLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/LabResult.gif"))); // NOI18N
        jMenuItemLab.setText("แลป");
        jMenuItemLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLabActionPerformed(evt);
            }
        });
        jMenuTab.add(jMenuItemLab);

        jMenuItemXray.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_9, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemXray.setFont(defaultFont1);
        jMenuItemXray.setText("เอ็กซเรย์");
        jMenuItemXray.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemXrayActionPerformed(evt);
            }
        });
        jMenuTab.add(jMenuItemXray);

        jMenuBar.add(jMenuTab);

        jMenuTool.setText("เครื่องมือ");
        jMenuTool.setFont(defaultFont1);

        jMenuItemAppointment.setFont(defaultFont1);
        jMenuItemAppointment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/appoint.gif"))); // NOI18N
        jMenuItemAppointment.setText("การนัดหมาย");
        jMenuItemAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAppointmentActionPerformed(evt);
            }
        });
        jMenuTool.add(jMenuItemAppointment);

        jMenuItemListAppointment.setFont(defaultFont1);
        jMenuItemListAppointment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/AppointmentAll.png"))); // NOI18N
        jMenuItemListAppointment.setText("รายการนัดทั้งหมด");
        jMenuItemListAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemListAppointmentActionPerformed(evt);
            }
        });
        jMenuTool.add(jMenuItemListAppointment);

        jMenuItemVisitHistory.setFont(defaultFont1);
        jMenuItemVisitHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/patientHistory.gif"))); // NOI18N
        jMenuItemVisitHistory.setText("ประวัติการรับบริการ");
        jMenuItemVisitHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVisitHistoryActionPerformed(evt);
            }
        });
        jMenuTool.add(jMenuItemVisitHistory);

        jMenuItemOrderHistory.setFont(defaultFont1);
        jMenuItemOrderHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/orderhistory.gif"))); // NOI18N
        jMenuItemOrderHistory.setText("ประวัติรายการสั่งตรวจ");
        jMenuItemOrderHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOrderHistoryActionPerformed(evt);
            }
        });
        jMenuTool.add(jMenuItemOrderHistory);

        jMenuItemBillingHistory.setFont(defaultFont1);
        jMenuItemBillingHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/calculate.gif"))); // NOI18N
        jMenuItemBillingHistory.setText("ประวัติการคิดเงิน");
        jMenuItemBillingHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBillingHistoryActionPerformed(evt);
            }
        });
        jMenuTool.add(jMenuItemBillingHistory);

        jMenuItemSetPrefix.setFont(defaultFont1);
        jMenuItemSetPrefix.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/prefix.gif"))); // NOI18N
        jMenuItemSetPrefix.setText("จับคู่คำนำหน้ากับเพศ");
        jMenuItemSetPrefix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSetPrefixActionPerformed(evt);
            }
        });
        jMenuTool.add(jMenuItemSetPrefix);

        jMenuItemViewSequence.setFont(defaultFont1);
        jMenuItemViewSequence.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/sequence.gif"))); // NOI18N
        jMenuItemViewSequence.setText("ลำดับเลข Sequence ล่าสุด");
        jMenuItemViewSequence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemViewSequenceActionPerformed(evt);
            }
        });
        jMenuTool.add(jMenuItemViewSequence);

        jMenuItemShowDxDoctor.setFont(defaultFont1);
        jMenuItemShowDxDoctor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/new_doctorclinic.gif"))); // NOI18N
        jMenuItemShowDxDoctor.setText("กำหนดชื่อแพทย์และคลินิกใหม่");
        jMenuItemShowDxDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemShowDxDoctorActionPerformed(evt);
            }
        });
        jMenuTool.add(jMenuItemShowDxDoctor);

        jMenuItemBorrowFilmXray.setFont(defaultFont1);
        jMenuItemBorrowFilmXray.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/PrintXNIndex.gif"))); // NOI18N
        jMenuItemBorrowFilmXray.setText("การยืมคืนฟิล์ม Xray");
        jMenuItemBorrowFilmXray.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBorrowFilmXrayActionPerformed(evt);
            }
        });
        jMenuTool.add(jMenuItemBorrowFilmXray);

        jMenuItemBorrowOpdCard.setFont(defaultFont1);
        jMenuItemBorrowOpdCard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/bor_OPDcard.gif"))); // NOI18N
        jMenuItemBorrowOpdCard.setText("การยืมคืน OPD Card");
        jMenuItemBorrowOpdCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBorrowOpdCardActionPerformed(evt);
            }
        });
        jMenuTool.add(jMenuItemBorrowOpdCard);
        jMenuTool.add(jSeparator8);

        jMenuItemLabreferOut.setFont(defaultFont1);
        jMenuItemLabreferOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/LabreferOut.png"))); // NOI18N
        jMenuItemLabreferOut.setText("แลป Refer Out");
        jMenuItemLabreferOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLabreferOutActionPerformed(evt);
            }
        });
        jMenuTool.add(jMenuItemLabreferOut);

        jMenuItemLabreferin.setFont(defaultFont1);
        jMenuItemLabreferin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/Labreferin.png"))); // NOI18N
        jMenuItemLabreferin.setText("แลป Refer In");
        jMenuItemLabreferin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLabreferinActionPerformed(evt);
            }
        });
        jMenuTool.add(jMenuItemLabreferin);

        jMenuItemReferPatient.setFont(defaultFont1);
        jMenuItemReferPatient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/ReferPatient.png"))); // NOI18N
        jMenuItemReferPatient.setText("การ Refer ผู้ป่วย");
        jMenuItemReferPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReferPatientActionPerformed(evt);
            }
        });
        jMenuTool.add(jMenuItemReferPatient);

        jMenuItemReport.setFont(defaultFont1);
        jMenuItemReport.setText("รายงานต่างๆ");
        jMenuItemReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReportActionPerformed(evt);
            }
        });
        jMenuTool.add(jMenuItemReport);

        jMenuItemScanOPDRecord.setText("Scan OPD Record");
        jMenuItemScanOPDRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemScanOPDRecordActionPerformed(evt);
            }
        });
        jMenuTool.add(jMenuItemScanOPDRecord);

        jMenuBar.add(jMenuTool);

        jMenuPatientGeneral.setText("ผู้ป่วยทั่วไป");
        jMenuPatientGeneral.setFont(defaultFont1);

        jMenuItemWaitDoctorDischarge.setFont(defaultFont1);
        jMenuItemWaitDoctorDischarge.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/tosave.gif"))); // NOI18N
        jMenuItemWaitDoctorDischarge.setText("ค้างบันทึก");
        jMenuItemWaitDoctorDischarge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemWaitDoctorDischargeActionPerformed(evt);
            }
        });
        jMenuPatientGeneral.add(jMenuItemWaitDoctorDischarge);

        jMenuItemDropVisit.setFont(defaultFont1);
        jMenuItemDropVisit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/cancel.gif"))); // NOI18N
        jMenuItemDropVisit.setText("ยกเลิกการเข้ารับบริการ");
        jMenuItemDropVisit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDropVisitActionPerformed(evt);
            }
        });
        jMenuPatientGeneral.add(jMenuItemDropVisit);
        jMenuPatientGeneral.add(jSeparator7);

        jMenuItemAccident.setFont(defaultFont1);
        jMenuItemAccident.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/acc.jpg"))); // NOI18N
        jMenuItemAccident.setText("ข้อมูลการเกิดอุบัติเหตุ");
        jMenuItemAccident.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAccidentActionPerformed(evt);
            }
        });
        jMenuPatientGeneral.add(jMenuItemAccident);

        jMenuItemAccidentGroup.setFont(defaultFont1);
        jMenuItemAccidentGroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/acc.jpg"))); // NOI18N
        jMenuItemAccidentGroup.setText("อุบัติเหตุหมู่");
        jMenuItemAccidentGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAccidentGroupActionPerformed(evt);
            }
        });
        jMenuPatientGeneral.add(jMenuItemAccidentGroup);

        jMenuItemDeath.setFont(defaultFont1);
        jMenuItemDeath.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/Skull.gif"))); // NOI18N
        jMenuItemDeath.setText("ข้อมูลการตาย");
        jMenuItemDeath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDeathActionPerformed(evt);
            }
        });
        jMenuPatientGeneral.add(jMenuItemDeath);

        jMenuItemChronic.setFont(defaultFont1);
        jMenuItemChronic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/death.gif"))); // NOI18N
        jMenuItemChronic.setText("ข้อมูลโรคเรื้อรัง");
        jMenuItemChronic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemChronicActionPerformed(evt);
            }
        });
        jMenuPatientGeneral.add(jMenuItemChronic);

        jMenuItemServeil.setFont(defaultFont1);
        jMenuItemServeil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Target.gif"))); // NOI18N
        jMenuItemServeil.setText("ข้อมูลโรคเฝ้าระวัง");
        jMenuItemServeil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemServeilActionPerformed(evt);
            }
        });
        jMenuPatientGeneral.add(jMenuItemServeil);

        jMenuBar.add(jMenuPatientGeneral);

        jMenuIpd.setText("ผู้ป่วยใน");
        jMenuIpd.setFont(defaultFont1);

        jMenuItemAdmit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAdmit.setFont(defaultFont1);
        jMenuItemAdmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/ipd.gif"))); // NOI18N
        jMenuItemAdmit.setText("Admit");
        jMenuItemAdmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAdmitActionPerformed(evt);
            }
        });
        jMenuIpd.add(jMenuItemAdmit);

        jMenuItemReverseAdmit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemReverseAdmit.setFont(defaultFont1);
        jMenuItemReverseAdmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/observ.gif"))); // NOI18N
        jMenuItemReverseAdmit.setText("ยกเลิกการ Admit");
        jMenuItemReverseAdmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReverseAdmitActionPerformed(evt);
            }
        });
        jMenuIpd.add(jMenuItemReverseAdmit);

        jMenuItemObserve.setFont(defaultFont1);
        jMenuItemObserve.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/sleep.png"))); // NOI18N
        jMenuItemObserve.setText("ฝากนอน");
        jMenuItemObserve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemObserveActionPerformed(evt);
            }
        });
        jMenuIpd.add(jMenuItemObserve);

        jMenuItemReverseObserve.setFont(defaultFont1);
        jMenuItemReverseObserve.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/canclesleep.png"))); // NOI18N
        jMenuItemReverseObserve.setText("ยกเลิกการฝากนอน");
        jMenuItemReverseObserve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReverseObserveActionPerformed(evt);
            }
        });
        jMenuIpd.add(jMenuItemReverseObserve);

        jMenuItemSendBackWard.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemSendBackWard.setFont(defaultFont1);
        jMenuItemSendBackWard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/sendtoward.gif"))); // NOI18N
        jMenuItemSendBackWard.setText("ส่งผู้ป่วยกลับวอร์ด");
        jMenuItemSendBackWard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSendBackWardActionPerformed(evt);
            }
        });
        jMenuIpd.add(jMenuItemSendBackWard);

        jMenuItemHistoryOrderContinue.setFont(defaultFont1);
        jMenuItemHistoryOrderContinue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/dcontihos.gif"))); // NOI18N
        jMenuItemHistoryOrderContinue.setText("ประวัติการสั่งตรวจต่อเนื่อง");
        jMenuItemHistoryOrderContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHistoryOrderContinueActionPerformed(evt);
            }
        });
        jMenuIpd.add(jMenuItemHistoryOrderContinue);

        jMenuItemDischargeIPD.setFont(defaultFont1);
        jMenuItemDischargeIPD.setText("จำหน่ายผู้ป่วยใน");
        jMenuItemDischargeIPD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDischargeIPDActionPerformed(evt);
            }
        });
        jMenuIpd.add(jMenuItemDischargeIPD);

        jMenuBar.add(jMenuIpd);

        jMenuOrder.setText("การสั่งตรวจ");
        jMenuOrder.setFont(defaultFont1);

        jMenuItemReceiveDrug.setFont(defaultFont1);
        jMenuItemReceiveDrug.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/return_drug.jpg"))); // NOI18N
        jMenuItemReceiveDrug.setText("การคืนยา");
        jMenuItemReceiveDrug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReceiveDrugActionPerformed(evt);
            }
        });
        jMenuOrder.add(jMenuItemReceiveDrug);
        jMenuOrder.add(jSeparator4);

        jMenuItemOrderSet.setFont(defaultFont1);
        jMenuItemOrderSet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/orderset.gif"))); // NOI18N
        jMenuItemOrderSet.setText("สั่งชุดรายการตรวจรักษา");
        jMenuItemOrderSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOrderSetActionPerformed(evt);
            }
        });
        jMenuOrder.add(jMenuItemOrderSet);

        jMenuItemOrderListByVn.setFont(defaultFont1);
        jMenuItemOrderListByVn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/ListByVn.png"))); // NOI18N
        jMenuItemOrderListByVn.setText("สั่งรายการเหมือนครั้งที่แล้ว");
        jMenuItemOrderListByVn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOrderListByVnActionPerformed(evt);
            }
        });
        jMenuOrder.add(jMenuItemOrderListByVn);

        jMenuItemOrderListByDate.setFont(defaultFont1);
        jMenuItemOrderListByDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/ListByDate.png"))); // NOI18N
        jMenuItemOrderListByDate.setText("สั่งรายการเหมือนวันที่แล้ว");
        jMenuItemOrderListByDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOrderListByDateActionPerformed(evt);
            }
        });
        jMenuOrder.add(jMenuItemOrderListByDate);

        jMenuItemOrderCotinueDrug.setFont(defaultFont1);
        jMenuItemOrderCotinueDrug.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/drugcon.gif"))); // NOI18N
        jMenuItemOrderCotinueDrug.setText("สั่งยาต่อเนื่อง");
        jMenuItemOrderCotinueDrug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOrderCotinueDrugActionPerformed(evt);
            }
        });
        jMenuOrder.add(jMenuItemOrderCotinueDrug);

        jMenuBar.add(jMenuOrder);

        jMenuModule.setText("โมดูลเสริม");
        jMenuModule.setFont(defaultFont1);
        jMenuBar.add(jMenuModule);

        jMenuPrintAll.setText("รายการพิมพ์");
        jMenuPrintAll.setFont(defaultFont1);
        jMenuPrintAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPrintAllActionPerformed(evt);
            }
        });

        jMenuPrintVisitSlip.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/listcare.gif"))); // NOI18N
        jMenuPrintVisitSlip.setText("ใบรายการตรวจรักษาผู้ป่วย");
        jMenuPrintVisitSlip.setFont(defaultFont1);

        jMenuItemPrintVisitSlip.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemPrintVisitSlip.setFont(defaultFont1);
        jMenuItemPrintVisitSlip.setText("พิมพ์");
        jMenuItemPrintVisitSlip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrintVisitSlipActionPerformed(evt);
            }
        });
        jMenuPrintVisitSlip.add(jMenuItemPrintVisitSlip);

        jMenuItemPreviewVisitSlip.setFont(defaultFont1);
        jMenuItemPreviewVisitSlip.setText("ภาพก่อนพิมพ์");
        jMenuItemPreviewVisitSlip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPreviewVisitSlipActionPerformed(evt);
            }
        });
        jMenuPrintVisitSlip.add(jMenuItemPreviewVisitSlip);

        jMenuPrintAll.add(jMenuPrintVisitSlip);

        jMenuPrintSummary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/sumreport.gif"))); // NOI18N
        jMenuPrintSummary.setText("ใบ Summary");
        jMenuPrintSummary.setFont(defaultFont1);

        jMenuItemPrintSummaryDischarge.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemPrintSummaryDischarge.setFont(defaultFont1);
        jMenuItemPrintSummaryDischarge.setText("พิมพ์");
        jMenuItemPrintSummaryDischarge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrintSummaryDischargeActionPerformed(evt);
            }
        });
        jMenuPrintSummary.add(jMenuItemPrintSummaryDischarge);

        jMenuItemPreviewSummaryDischarge.setFont(defaultFont1);
        jMenuItemPreviewSummaryDischarge.setText("ภาพก่อนพิมพ์");
        jMenuItemPreviewSummaryDischarge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPreviewSummaryDischargeActionPerformed(evt);
            }
        });
        jMenuPrintSummary.add(jMenuItemPreviewSummaryDischarge);

        jCheckBoxMenuItemCalDayByHour.setFont(defaultFont1);
        jCheckBoxMenuItemCalDayByHour.setText("คำนวณวันนอนจากจำนวนชัวโมง");
        jMenuPrintSummary.add(jCheckBoxMenuItemCalDayByHour);

        jMenuPrintAll.add(jMenuPrintSummary);

        jMenuSelectDrugList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/drugrx.gif"))); // NOI18N
        jMenuSelectDrugList.setText("พิมพ์ใบสั่งยา");
        jMenuSelectDrugList.setFont(defaultFont1);
        jMenuSelectDrugList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSelectDrugListActionPerformed(evt);
            }
        });

        jMenuItemPrintSelectDrugList.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemPrintSelectDrugList.setFont(defaultFont1);
        jMenuItemPrintSelectDrugList.setText("พิมพ์");
        jMenuItemPrintSelectDrugList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrintSelectDrugListActionPerformed(evt);
            }
        });
        jMenuSelectDrugList.add(jMenuItemPrintSelectDrugList);

        jMenuItemPreviewSelectDrugList.setFont(defaultFont1);
        jMenuItemPreviewSelectDrugList.setText("ภาพก่อนพิมพ์");
        jMenuItemPreviewSelectDrugList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPreviewSelectDrugListActionPerformed(evt);
            }
        });
        jMenuSelectDrugList.add(jMenuItemPreviewSelectDrugList);

        jMenuItemPrintDrugRx.setFont(defaultFont1);
        jMenuItemPrintDrugRx.setText("ใบสั่งยาเปล่า");
        jMenuItemPrintDrugRx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrintDrugRxActionPerformed(evt);
            }
        });
        jMenuSelectDrugList.add(jMenuItemPrintDrugRx);

        jMenuPrintAll.add(jMenuSelectDrugList);

        jMenuOpdCard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/OPDcard.gif"))); // NOI18N
        jMenuOpdCard.setText("ใบ Opd การ์ด");
        jMenuOpdCard.setFont(defaultFont1);

        jMenuItemPrintOpdCard.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemPrintOpdCard.setFont(defaultFont1);
        jMenuItemPrintOpdCard.setText("พิมพ์");
        jMenuItemPrintOpdCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrintOpdCardActionPerformed(evt);
            }
        });
        jMenuOpdCard.add(jMenuItemPrintOpdCard);

        jMenuItemPreviewOpdCard.setFont(defaultFont1);
        jMenuItemPreviewOpdCard.setText("ภาพก่อนพิมพ์");
        jMenuItemPreviewOpdCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPreviewOpdCardActionPerformed(evt);
            }
        });
        jMenuOpdCard.add(jMenuItemPreviewOpdCard);

        jMenuPrintAll.add(jMenuOpdCard);
        jMenuPrintAll.add(jSeparator12);

        jMenuItemPrintDrugSticker.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemPrintDrugSticker.setFont(defaultFont1);
        jMenuItemPrintDrugSticker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/DrugSticker.png"))); // NOI18N
        jMenuItemPrintDrugSticker.setText("ฉลากยา");
        jMenuItemPrintDrugSticker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrintDrugStickerActionPerformed(evt);
            }
        });
        jMenuPrintAll.add(jMenuItemPrintDrugSticker);

        jMenuItemPrintIndex.setFont(defaultFont1);
        jMenuItemPrintIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/index.gif"))); // NOI18N
        jMenuItemPrintIndex.setText("ใบ Index");
        jMenuItemPrintIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrintIndexActionPerformed(evt);
            }
        });
        jMenuPrintAll.add(jMenuItemPrintIndex);

        jMenuItemPrintXNIndex.setFont(defaultFont1);
        jMenuItemPrintXNIndex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/PrintXNIndex.gif"))); // NOI18N
        jMenuItemPrintXNIndex.setText("ใบ Index เอ็กซเรย์");
        jMenuItemPrintXNIndex.setActionCommand("พิมพ์ใบ Index Xray");
        jMenuItemPrintXNIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrintXNIndexActionPerformed(evt);
            }
        });
        jMenuPrintAll.add(jMenuItemPrintXNIndex);

        jMenuItemLabResult.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemLabResult.setFont(defaultFont1);
        jMenuItemLabResult.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/LabResult.gif"))); // NOI18N
        jMenuItemLabResult.setText("ผลแลป");
        jMenuItemLabResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLabResultActionPerformed(evt);
            }
        });
        jMenuPrintAll.add(jMenuItemLabResult);

        jMenuPrintIPDNameCard.setText("ป้ายชื่อผู้ป่วยใน");
        jMenuPrintIPDNameCard.setFont(defaultFont1);

        jMenuItemPrintForPatient.setFont(defaultFont1);
        jMenuItemPrintForPatient.setText("ป้ายชื่อผู้ป่วย");
        jMenuItemPrintForPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrintForPatientActionPerformed(evt);
            }
        });
        jMenuPrintIPDNameCard.add(jMenuItemPrintForPatient);

        jMenuItemPrintAllPatientInWard.setFont(defaultFont1);
        jMenuItemPrintAllPatientInWard.setText("ป้ายชื่อทุกคนในวอร์ด");
        jMenuItemPrintAllPatientInWard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrintAllPatientInWardActionPerformed(evt);
            }
        });
        jMenuPrintIPDNameCard.add(jMenuItemPrintAllPatientInWard);

        jMenuItemPrintIPDnameInChart.setFont(defaultFont1);
        jMenuItemPrintIPDnameInChart.setText("ป้ายชื่อติด Chart");
        jMenuItemPrintIPDnameInChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrintIPDnameInChartActionPerformed(evt);
            }
        });
        jMenuPrintIPDNameCard.add(jMenuItemPrintIPDnameInChart);

        jMenuPrintAll.add(jMenuPrintIPDNameCard);

        jMenuPrintMedCert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/listcare.gif"))); // NOI18N
        jMenuPrintMedCert.setText("ใบรับรองแพทย์");
        jMenuPrintMedCert.setFont(defaultFont1);

        jMenuItemPrintMedCertIll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemPrintMedCertIll.setFont(defaultFont1);
        jMenuItemPrintMedCertIll.setText("ลาหยุดงาน");
        jMenuItemPrintMedCertIll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrintMedCertIllActionPerformed(evt);
            }
        });
        jMenuPrintMedCert.add(jMenuItemPrintMedCertIll);

        jMenuItemPrintMedCertInterview.setFont(defaultFont1);
        jMenuItemPrintMedCertInterview.setText("สัมภาษณ์งาน");
        jMenuItemPrintMedCertInterview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrintMedCertInterviewActionPerformed(evt);
            }
        });
        jMenuPrintMedCert.add(jMenuItemPrintMedCertInterview);

        jMenuItemPrint7Cert.setText("ใบรับรองแพทย์ 7 โรค");
        jMenuItemPrint7Cert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrint7CertActionPerformed(evt);
            }
        });
        jMenuPrintMedCert.add(jMenuItemPrint7Cert);

        jMenuPrintAll.add(jMenuPrintMedCert);

        jMenu1.setText("การเงิน");

        jMenuReportSumItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/Item.gif"))); // NOI18N
        jMenuReportSumItem.setText("ใบสรุปค่าใช้จ่ายตามรายการ");
        jMenuReportSumItem.setFont(defaultFont1);

        jMenuItemPrintReportItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemPrintReportItem.setFont(defaultFont1);
        jMenuItemPrintReportItem.setText("พิมพ์ (แยก)");
        jMenuItemPrintReportItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrintReportItemActionPerformed(evt);
            }
        });
        jMenuReportSumItem.add(jMenuItemPrintReportItem);

        jMenuItemPreviewReportItem.setFont(defaultFont1);
        jMenuItemPreviewReportItem.setText("ภาพก่อนพิมพ์ (แยก)");
        jMenuItemPreviewReportItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPreviewReportItemActionPerformed(evt);
            }
        });
        jMenuReportSumItem.add(jMenuItemPreviewReportItem);

        jMenuItemPrintReportItemByItemName.setFont(defaultFont1);
        jMenuItemPrintReportItemByItemName.setText("พิมพ์ (รวม)");
        jMenuItemPrintReportItemByItemName.setAutoscrolls(true);
        jMenuItemPrintReportItemByItemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrintReportItemByItemNameActionPerformed(evt);
            }
        });
        jMenuReportSumItem.add(jMenuItemPrintReportItemByItemName);

        jMenuItemPreviewReportItemByItemName.setFont(defaultFont1);
        jMenuItemPreviewReportItemByItemName.setText("ภาพก่อนพิมพ์ (รวม)");
        jMenuItemPreviewReportItemByItemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPreviewReportItemByItemNameActionPerformed(evt);
            }
        });
        jMenuReportSumItem.add(jMenuItemPreviewReportItemByItemName);

        jMenu1.add(jMenuReportSumItem);

        jMenuReportSum16Group.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/16Group.gif"))); // NOI18N
        jMenuReportSum16Group.setText("ใบสรุปค่าใช้จ่ายตาม 16 กลุ่ม");
        jMenuReportSum16Group.setFont(defaultFont1);

        jMenuItemPrintReport16Group.setFont(defaultFont1);
        jMenuItemPrintReport16Group.setText("พิมพ์");
        jMenuItemPrintReport16Group.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrintReport16GroupActionPerformed(evt);
            }
        });
        jMenuReportSum16Group.add(jMenuItemPrintReport16Group);

        jMenuItemPreveiwReport16Group.setFont(defaultFont1);
        jMenuItemPreveiwReport16Group.setText("ภาพก่อนพิมพ์");
        jMenuItemPreveiwReport16Group.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPreveiwReport16GroupActionPerformed(evt);
            }
        });
        jMenuReportSum16Group.add(jMenuItemPreveiwReport16Group);

        jMenu1.add(jMenuReportSum16Group);

        jMenuReportSumGroupBilling.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/Billing.gif"))); // NOI18N
        jMenuReportSumGroupBilling.setText("ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ");
        jMenuReportSumGroupBilling.setFont(defaultFont1);

        jMenuItemPrintReportBilling.setFont(defaultFont1);
        jMenuItemPrintReportBilling.setText("พิมพ์");
        jMenuItemPrintReportBilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrintReportBillingActionPerformed(evt);
            }
        });
        jMenuReportSumGroupBilling.add(jMenuItemPrintReportBilling);

        jMenuItemPreveiwReportBilling.setFont(defaultFont1);
        jMenuItemPreveiwReportBilling.setText("ภาพก่อนพิมพ์");
        jMenuItemPreveiwReportBilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPreveiwReportBillingActionPerformed(evt);
            }
        });
        jMenuReportSumGroupBilling.add(jMenuItemPreveiwReportBilling);

        jMenu1.add(jMenuReportSumGroupBilling);

        jMenuReportSumGroupItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/expenses.gif"))); // NOI18N
        jMenuReportSumGroupItem.setText("ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ");
        jMenuReportSumGroupItem.setFont(defaultFont1);

        jMenuItemPrintReportOrder.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemPrintReportOrder.setFont(defaultFont1);
        jMenuItemPrintReportOrder.setText("พิมพ์");
        jMenuItemPrintReportOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPrintReportOrderActionPerformed(evt);
            }
        });
        jMenuReportSumGroupItem.add(jMenuItemPrintReportOrder);

        jMenuItemPreviewReportOrder.setFont(defaultFont1);
        jMenuItemPreviewReportOrder.setText("ภาพก่อนพิมพ์");
        jMenuItemPreviewReportOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPreviewReportOrderActionPerformed(evt);
            }
        });
        jMenuReportSumGroupItem.add(jMenuItemPreviewReportOrder);

        jMenu1.add(jMenuReportSumGroupItem);

        jMenuPrintAll.add(jMenu1);

        jMenuBar.add(jMenuPrintAll);

        jMenuReverse.setText("การย้อนกลับ");
        jMenuReverse.setFont(defaultFont1);
        jMenuReverse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuReverseActionPerformed(evt);
            }
        });

        jMenuItemReverseFinancial.setFont(defaultFont1);
        jMenuItemReverseFinancial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/backmoney.gif"))); // NOI18N
        jMenuItemReverseFinancial.setText("ย้อนกลับการจำหน่ายทางการเงิน");
        jMenuItemReverseFinancial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReverseFinancialActionPerformed(evt);
            }
        });
        jMenuReverse.add(jMenuItemReverseFinancial);

        jMenuItemReverseDoctor.setFont(defaultFont1);
        jMenuItemReverseDoctor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/backdoctor.gif"))); // NOI18N
        jMenuItemReverseDoctor.setText("ย้อนกลับการจำหน่ายทางการแพทย์");
        jMenuItemReverseDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReverseDoctorActionPerformed(evt);
            }
        });
        jMenuReverse.add(jMenuItemReverseDoctor);

        jMenuBar.add(jMenuReverse);

        jMenuHelp.setMnemonic('H');
        jMenuHelp.setText("ตัวช่วย");
        jMenuHelp.setFont(defaultFont1);
        jMenuHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuHelpActionPerformed(evt);
            }
        });

        jMenuItemConnection.setFont(defaultFont1);
        jMenuItemConnection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/connection.gif"))); // NOI18N
        jMenuItemConnection.setText("การเชื่อมต่อฐานข้อมูล");
        jMenuItemConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConnectionActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemConnection);

        jMenuItemAbout.setFont(defaultFont1);
        jMenuItemAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/about.gif"))); // NOI18N
        jMenuItemAbout.setText("ข้อมูลโปรแกรม");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemAbout);
        jMenuHelp.add(jSeparator10);

        jMenuItemSetPartFont.setFont(defaultFont1);
        jMenuItemSetPartFont.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/clock.gif"))); // NOI18N
        jMenuItemSetPartFont.setText("กำหนด path รูปแบบอักษร");
        jMenuItemSetPartFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSetPartFontActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemSetPartFont);

        jMenuItemSetPartPrint.setFont(defaultFont1);
        jMenuItemSetPartPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/printer.gif"))); // NOI18N
        jMenuItemSetPartPrint.setText("กำหนด path ของไฟล์งานพิมพ์");
        jMenuItemSetPartPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSetPartPrintActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemSetPartPrint);

        jMenuItemViewHO.setFont(defaultFont1);
        jMenuItemViewHO.setText("กำหนด path ของไฟล์งานพิมพ์");
        jMenuItemViewHO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemViewHOActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemViewHO);

        jMenuItemNew.setFont(defaultFont1);
        jMenuItemNew.setText("อ่านข่าวประจำวัน");
        jMenuItemNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNewActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemNew);

        jCheckBoxMenuItemReportBug.setText("รายงานข้อผิดพลาดของโปรแกรม");
        jCheckBoxMenuItemReportBug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemReportBugActionPerformed(evt);
            }
        });
        jMenuHelp.add(jCheckBoxMenuItemReportBug);

        jMenuItemWS.setText("ตั้งค่า Webservice");
        jMenuItemWS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemWSActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemWS);

        jMenuItem2.setText("จับคู่สิทธิ สปสช กับสิทธิของโรงพยาบาล");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItem2);

        jMenuBar.add(jMenuHelp);

        setJMenuBar(jMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemPrintMedCertInterviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrintMedCertInterviewActionPerformed
        theHC.thePrintControl.printMedCertInterview();
    }//GEN-LAST:event_jMenuItemPrintMedCertInterviewActionPerformed

    private void jMenuItemPrintMedCertIllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrintMedCertIllActionPerformed
        theHC.thePrintControl.printMedCertIll();
    }//GEN-LAST:event_jMenuItemPrintMedCertIllActionPerformed

    private void jMenuPrintAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPrintAllActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuPrintAllActionPerformed

    private void jMenuItemPrintDrugRxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrintDrugRxActionPerformed
        theHC.thePrintControl.printEmptyDrugRx(nopreview);
    }//GEN-LAST:event_jMenuItemPrintDrugRxActionPerformed

    private void jMenuItemThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemThemeActionPerformed
        
        theme = "";
        File f = new File(Config.THEME_FILE);
        try {
            FileInputStream fstream = new FileInputStream(f);
        
            byte b = 0;
            char d[] = new char[30];
            int i=0;
            do{
                b = (byte) fstream.read();
                d[i] = (char)b;
                if(b!=-1){
                    theme = theme+d[i];
                }
                i++;
            }
            while(b!=-1 && i<30);
            Constant.println(theme);
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        theDialogTheme = new DialogTheme2(theme,this,theHC);
        theDialogTheme.setVisible(true);
    }//GEN-LAST:event_jMenuItemThemeActionPerformed
/*
    private void selectThemeCloseAction(String theme){
        try {
            if(theme.equals("Mac")){
                LiquidLookAndFeel lq = new LiquidLookAndFeel();
                LiquidLookAndFeel.setLiquidDecorations(false);
                UIManager.setLookAndFeel(lq);
            }else if(theme.equals("Plastic")){
                PlasticXPLookAndFeel px = new PlasticXPLookAndFeel();
                PlasticXPLookAndFeel.setCurrentTheme(new SkyBlue());
                UIManager.setLookAndFeel(px);
            }
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        
        if(theUS.confirmBox(("ต้องการจะเปลี่ยน Theme "+theme+" หรือไม่\nถ้าเปลี่ยน ระบบจะทำการปิดโปรแกรมแล้วเปิดใหม่")
            ,UpdateStatus.WARNING)){
            writeTheme(theme);
        }else{
            try {
            if(theme.equals("Mac")){
                PlasticXPLookAndFeel px = new PlasticXPLookAndFeel();
                PlasticXPLookAndFeel.setCurrentTheme(new SkyBlue());
                UIManager.setLookAndFeel(px);
            }else if(theme.equals("Plastic")){
                com.birosoft.liquid.LiquidLookAndFeel lq = new com.birosoft.liquid.LiquidLookAndFeel();
                LiquidLookAndFeel.setLiquidDecorations(false);
                UIManager.setLookAndFeel(lq);
            }
            } catch (UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
            return;
        }
        
        theHC.theSystemControl.restartProgram();
        this.setVisible(false);
        theHosApp = new HosApp();
        theHosApp.main(theHosApp.args);
    }*/
    
    public void writeTheme(String theme){
        try{
            FileOutputStream fos = new FileOutputStream(Config.THEME_FILE);
            Writer out = new OutputStreamWriter(fos, "UTF8");
            out.write(theme);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
    }
    private void jMenuFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuFileActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuFileActionPerformed

    private void jMenuItemDischargeIPDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDischargeIPDActionPerformed
        Vector v = new Vector();

        //for get date_time to discharge ipd
        boolean b = theHD.showDialogDischarge(theHO.theVisit);
        if(b){
            theHC.theVisitControl.dischargeIPD(theHO.theVisit);
        }
    }//GEN-LAST:event_jMenuItemDischargeIPDActionPerformed

    private void jMenuItemPreviewReportItemByItemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPreviewReportItemByItemNameActionPerformed
        Vector vOrder = theHP.aPanelOrder.getOrderItemV();
        theHC.thePrintControl.printSumItemByItemName(preview,vOrder);
    }//GEN-LAST:event_jMenuItemPreviewReportItemByItemNameActionPerformed

    private void jMenuItemPrintReportItemByItemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrintReportItemByItemNameActionPerformed
        Vector vOrder = theHP.aPanelOrder.getOrderItemV();
        theHC.thePrintControl.printSumItemByItemName(nopreview,vOrder);
    }//GEN-LAST:event_jMenuItemPrintReportItemByItemNameActionPerformed

    private void jMenuItemPreveiwReport16GroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPreveiwReport16GroupActionPerformed

        if(theHP.aPanelOrder!=null)
        {
            Vector vOrder = theHP.aPanelOrder.getOrderItemV();
            theHC.thePrintControl.printSumByItem16Group(preview,vOrder);
        }
        else
        {
            Vector vOrder = theHC.theOrderControl.listOrderItemByRange(true,"","","",false);
            theHC.thePrintControl.printSumByItem16Group(preview,vOrder);
        }
    }//GEN-LAST:event_jMenuItemPreveiwReport16GroupActionPerformed

    private void jMenuItemPrintReport16GroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrintReport16GroupActionPerformed
        if(theHP.aPanelOrder!=null)
        {
            Vector vOrder = theHP.aPanelOrder.getOrderItemV();
            theHC.thePrintControl.printSumByItem16Group(nopreview,vOrder);
        }
        else
        {
            Vector vOrder = theHC.theOrderControl.listOrderItemByRange(true,"","","",false);
            theHC.thePrintControl.printSumByItem16Group(nopreview,vOrder);
        }
    }//GEN-LAST:event_jMenuItemPrintReport16GroupActionPerformed

    private void jMenuItemAccidentGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAccidentGroupActionPerformed

    }//GEN-LAST:event_jMenuItemAccidentGroupActionPerformed

    private void jMenuItemShowDxDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemShowDxDoctorActionPerformed
        showDialogDxDoctorClinic();
    }//GEN-LAST:event_jMenuItemShowDxDoctorActionPerformed

    private void jMenuItemLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLoginActionPerformed
        this.loginEmployee();
    }//GEN-LAST:event_jMenuItemLoginActionPerformed

    private void jMenuReverseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuReverseActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuReverseActionPerformed

    private void jMenuItemPrintIPDnameInChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrintIPDnameInChartActionPerformed
        theHC.thePrintControl.printIpdNameChart(theHO.theVisit);
    }//GEN-LAST:event_jMenuItemPrintIPDnameInChartActionPerformed

    private void jMenuItemBorrowOpdCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBorrowOpdCardActionPerformed
        theHD.showDialogBorrowOpdCard(theHC,theUS);
    }//GEN-LAST:event_jMenuItemBorrowOpdCardActionPerformed

    private void jMenuItemBorrowFilmXrayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBorrowFilmXrayActionPerformed
        theHD.showDialogBorrowFilmXray(theHC,theUS);
    }//GEN-LAST:event_jMenuItemBorrowFilmXrayActionPerformed

    private void jMenuSelectDrugListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSelectDrugListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuSelectDrugListActionPerformed

    private void jMenuItemPrintAllPatientInWardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrintAllPatientInWardActionPerformed
         theHD.showDialogSelectPatient(theHC,theUS);
    }//GEN-LAST:event_jMenuItemPrintAllPatientInWardActionPerformed

    private void jMenuItemPrintForPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrintForPatientActionPerformed
        theHC.thePrintControl.printIpdNameCardForPatient();
    }//GEN-LAST:event_jMenuItemPrintForPatientActionPerformed

    private void jMenuItemViewHOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemViewHOActionPerformed
        theHO.printValue();
    }//GEN-LAST:event_jMenuItemViewHOActionPerformed

    private void jMenuItemLabreferOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLabreferOutActionPerformed
        if(theHO.thePatient == null){     
            setStatus("ยังไม่ได้เลือกผู้ป่วย",UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit == null)
        {   
            setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return;
        }
        DialogLabReferOut.showDialog(theHC,this);                                               
    }//GEN-LAST:event_jMenuItemLabreferOutActionPerformed

    private void jMenuItemReverseObserveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReverseObserveActionPerformed
        theHC.theVisitControl.observVisit(theHO.theVisit,"0");
    }//GEN-LAST:event_jMenuItemReverseObserveActionPerformed

    private void jMenuItemLabreferinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLabreferinActionPerformed
        theHD.showDialogLabReferIn(theHC, theUS);
    }//GEN-LAST:event_jMenuItemLabreferinActionPerformed
    private void jMenuItemReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReportActionPerformed
        confirmBox("เมนูรายการนี้ยังไม่เสร็จสิ้น",UpdateStatus.WARNING);
        //theHD.showDialogShowReport();
    }//GEN-LAST:event_jMenuItemReportActionPerformed
    private void jMenuItemPrintReportBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrintReportBillingActionPerformed
        if(!Gutil.checkModulePrinting())
        {    
            theUS.setStatus("ไม่มี Module Printing",UpdateStatus.WARNING);
             return ;
        }
        //theHC.thePrintControl.selectprintSumByBillingGroup(nopreview);
        Vector vOrder = theHP.aPanelOrder.getOrderItemV();
        theHC.thePrintControl.printSumByBillingGroupNew(nopreview, vOrder);
    }//GEN-LAST:event_jMenuItemPrintReportBillingActionPerformed
    private void jMenuItemPreveiwReportBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPreveiwReportBillingActionPerformed
        if(!Gutil.checkModulePrinting())
        {    
            theUS.setStatus("ไม่มี Module Printing",UpdateStatus.WARNING);
             return ;
        }
        //theHC.thePrintControl.selectprintSumByBillingGroup(preview);
        Vector vOrder = theHP.aPanelOrder.getOrderItemV();
        theHC.thePrintControl.printSumByBillingGroupNew(preview, vOrder);
    }//GEN-LAST:event_jMenuItemPreveiwReportBillingActionPerformed
    private void jMenuItemViewSequenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemViewSequenceActionPerformed
        theHD.showDialogViewSequence();
    }//GEN-LAST:event_jMenuItemViewSequenceActionPerformed
    private void jMenuItemLabResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLabResultActionPerformed
        if(theHO.theVisit == null)
        {   
            setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return;
        }
        Vector voi = theHC.theOrderControl.listOrderLabByVNAndSecret(theHO.theVisit.getObjectId());
        ///////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////        
        if(voi == null || voi.size()==0){
            theUS.setStatus(Constant.getTextBundle("ไม่มีรายการสั่งตรวจแลป")+" "+
                    Constant.getTextBundle("ไม่สามารถพิมพ์ผลได้"),UpdateStatus.WARNING);
            return;
        }
        //ยังพิมพ์ฟอร์มเปล่าไม่ได้ทีแต่ถ้าพิมพ์จาก dialog ได้แล้ว
        Vector vRl = theHC.theResultControl.listResultLabByVisit(theHO.theVisit);
        theHD.showDialogSelectLabPrint(voi,theHO.theVisit,theHO.thePatient,vRl);
    }//GEN-LAST:event_jMenuItemLabResultActionPerformed
    private void jMenuItemPreviewOpdCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPreviewOpdCardActionPerformed
         theHC.thePrintControl.printOPDCard(1,theHO.vVisitPayment);
    }//GEN-LAST:event_jMenuItemPreviewOpdCardActionPerformed
    private void jMenuItemPreviewSelectDrugListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPreviewSelectDrugListActionPerformed
        this.printDrugList(preview);
    }//GEN-LAST:event_jMenuItemPreviewSelectDrugListActionPerformed
    private void jMenuItemPreviewReportItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPreviewReportItemActionPerformed
        Vector vOrder = theHP.aPanelOrder.getOrderItemV();
        theHC.thePrintControl.printSumByItem(preview,vOrder);
    }//GEN-LAST:event_jMenuItemPreviewReportItemActionPerformed
    private void jMenuItemPreviewReportOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPreviewReportOrderActionPerformed
        Vector vOrder = theHP.aPanelOrder.getOrderItemV();
        theHC.thePrintControl.printSumByItemGroupNew(preview,vOrder);
    }//GEN-LAST:event_jMenuItemPreviewReportOrderActionPerformed
    private void jMenuItemPrintOpdCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrintOpdCardActionPerformed
        theHC.thePrintControl.printOPDCard(nopreview,theHO.vVisitPayment);
    }//GEN-LAST:event_jMenuItemPrintOpdCardActionPerformed
    private void jMenuItemPrintSelectDrugListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrintSelectDrugListActionPerformed
        printDrugList(nopreview);
    }//GEN-LAST:event_jMenuItemPrintSelectDrugListActionPerformed
    private void jMenuItemPrintReportItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrintReportItemActionPerformed
        Vector vOrder = theHP.aPanelOrder.getOrderItemV();
        theHC.thePrintControl.printSumByItem(nopreview,vOrder);
    }//GEN-LAST:event_jMenuItemPrintReportItemActionPerformed
    private void jMenuItemPrintReportOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrintReportOrderActionPerformed
        Vector vOrder = theHP.aPanelOrder.getOrderItemV();
        theHC.thePrintControl.printSumByItemGroupNew(nopreview,vOrder);
    }//GEN-LAST:event_jMenuItemPrintReportOrderActionPerformed
    private void jMenuItemPrintSummaryDischargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrintSummaryDischargeActionPerformed
        theHC.thePrintControl.printSummary(nopreview,jCheckBoxMenuItemCalDayByHour.isSelected());
    }//GEN-LAST:event_jMenuItemPrintSummaryDischargeActionPerformed
    private void jMenuItemPreviewSummaryDischargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPreviewSummaryDischargeActionPerformed
        theHC.thePrintControl.printSummary(preview,jCheckBoxMenuItemCalDayByHour.isSelected());
    }//GEN-LAST:event_jMenuItemPreviewSummaryDischargeActionPerformed
    private void jMenuItemPrintVisitSlipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrintVisitSlipActionPerformed
        theHC.thePrintControl.printVisitSlipNew(nopreview,true);
        
    }//GEN-LAST:event_jMenuItemPrintVisitSlipActionPerformed
    private void jMenuItemPreviewVisitSlipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPreviewVisitSlipActionPerformed
       theHC.thePrintControl.printVisitSlipNew(preview,true);       
    }//GEN-LAST:event_jMenuItemPreviewVisitSlipActionPerformed
    private void jMenuItemBillingHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBillingHistoryActionPerformed
        if(theHO.thePatient == null){      
            setStatus("ยังไม่ได้เลือกผู้ป่วย",UpdateStatus.WARNING);
            return;
        }
        theHD.showDialogHistoryBilling(theHO.thePatient);            
    }//GEN-LAST:event_jMenuItemBillingHistoryActionPerformed
	private void jMenuItemSearchPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSearchPatientActionPerformed
//        if(theHO.thePatient!=null){
//            theHD.showDialogSearchPatient(theHO.thePatient.fname
//                ,theHO.thePatient.lname,"");
//        }
//        else {
//           theHD.showDialogSearchPatient(null,null,null);
           //ให้ทำงานเหมือนกับการกดปุ่มค้นหาในหน้า SocialData  Sumo--03/04/2549
          theHD.showDialogSearchPatient("","","","");       
    }//GEN-LAST:event_jMenuItemSearchPatientActionPerformed
    private void jMenuItemSetPartPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSetPartPrintActionPerformed
        theHC.thePrintControl.checkPathPrint(this,false);
    }//GEN-LAST:event_jMenuItemSetPartPrintActionPerformed
    private void jMenuItemSetPartFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSetPartFontActionPerformed
        
        String Path = new String();
        Path = IOStream.readInputDefault(".fontpath.cfg");
        if(Path == null)//Path.trim().length() == 0)
        {
            ConfigPathFont.showDialog(this);           
         
        }
        else
        {
            if( theUS.confirmBox("ได้กำหนด Path ไว้แล้วต้องการกำหนดใหม่หรือไม่ ?"
                    ,UpdateStatus.WARNING))
            {
                ConfigPathFont.showDialog(this);
            }        
        }       
    }	//GEN-LAST:event_jMenuItemSetPartFontActionPerformed
	private void jMenuItemReferPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReferPatientActionPerformed
        theHD.showDialogReferIn(theHO.theVisit);
    }//GEN-LAST:event_jMenuItemReferPatientActionPerformed

    private void jMenuItemPrintXNIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrintXNIndexActionPerformed
        theHC.thePrintControl.printXnIndex(nopreview);
    }//GEN-LAST:event_jMenuItemPrintXNIndexActionPerformed
	

	private void clockLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clockLabel1MouseClicked
        clockLabel1.startTime();
    }//GEN-LAST:event_clockLabel1MouseClicked

	private void jMenuItemHistoryOrderContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHistoryOrderContinueActionPerformed
        if(theHO.theVisit == null){  
            setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit.visit_type.equals(VisitType.OPD)){
            setStatus("ผู้ป่วยนอกไม่สามารถดูรายการสั่งตรวจต่อเนื่องได้",UpdateStatus.WARNING);
            return;
        }
        theHD.showDialogHistoryOrderContinue(theHO.theVisit);
    }//GEN-LAST:event_jMenuItemHistoryOrderContinueActionPerformed
   
        private void jMenuItemChronicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemChronicActionPerformed
        theHD.showDialogChronic(theHO.theVisit,null);
        }//GEN-LAST:event_jMenuItemChronicActionPerformed
    
	private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        theHD.showDialogDetailHospitalOS(theModuleV);
    }//GEN-LAST:event_jMenuItemAboutActionPerformed
    
	private void jMenuItemReceiveDrugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReceiveDrugActionPerformed
        if(theHO.theVisit == null)
        {   
            setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return;
         }
         theHD.showDialogReceiveDrug(theHO.theVisit);
    }//GEN-LAST:event_jMenuItemReceiveDrugActionPerformed
    
	private void jMenuItemPrintDrugStickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrintDrugStickerActionPerformed
        
        Vector vOr = theHO.vOrderItem;
        if(theHP.aPanelOrder!=null)
            vOr = theHP.aPanelOrder.getOrderItemV();
        
        if(vOr.isEmpty())
        {
            Item item = theHP.aPanelOrder.getItem();
            OrderItemDrug drug = theHP.aPanelOrder.getOrderItemDrug();
            theHC.thePrintControl.printItemSticker(item,drug);
        }
        else
            theHC.thePrintControl.printDrugSticker(vOr,null,null,false);            
    }//GEN-LAST:event_jMenuItemPrintDrugStickerActionPerformed
    
	private void jMenuItemPrintIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrintIndexActionPerformed
        if(theHO.thePatient == null) {      
             setStatus("ยังไม่ได้เลือกผู้ป่วย",UpdateStatus.WARNING);
             return;
        }
        theHC.thePrintControl.printIndex();
    }//GEN-LAST:event_jMenuItemPrintIndexActionPerformed
    
	private void jMenuItemServeilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemServeilActionPerformed
        theHD.showDialogSurveil(null);
    }//GEN-LAST:event_jMenuItemServeilActionPerformed
    
	private void jMenuItemOrderCotinueDrugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOrderCotinueDrugActionPerformed
        if(theHO.theVisit == null)
        {   
            setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return;
        }
        if(!theHO.theVisit.visit_type.equals(VisitType.IPD))           
        {
            setStatus("ผู้ป่วยนอกไม่สามารถใช้เมนูนี้ได้",UpdateStatus.WARNING);
            return;
        }
        theHC.theOrderControl.orderDrugContinue(theHO.theVisit);            
    }//GEN-LAST:event_jMenuItemOrderCotinueDrugActionPerformed
    
	private void jMenuItemReverseFinancialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReverseFinancialActionPerformed
        theHC.theVisitControl.reverseFinancial();
    }//GEN-LAST:event_jMenuItemReverseFinancialActionPerformed
    
	private void jMenuItemReverseDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReverseDoctorActionPerformed
        theHC.theVisitControl.reverseDoctor(theHO.theVisit);
    }//GEN-LAST:event_jMenuItemReverseDoctorActionPerformed
    
	private void jMenuItemOrderHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOrderHistoryActionPerformed
        if(theHO.thePatient == null)
        {      
            setStatus("ยังไม่ได้เลือกผู้ป่วย",UpdateStatus.WARNING);
            return;
        }
        theHD.showDialogOrderHistory(theHO.thePatient);
    }//GEN-LAST:event_jMenuItemOrderHistoryActionPerformed
   
	private void jMenuItemOrderListByDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOrderListByDateActionPerformed
        if(theHO.theVisit == null)
        {   
            setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);  
            return;
        }
        if(theHO.theVisit.visit_type.equals("0"))
        {
            setStatus("ผู้ป่วยนอกไม่สามารถใช้เมนูนี้ได้",UpdateStatus.WARNING);
            return;
        }
        theHD.showDialogOrderOldVisitByDate(theHO.thePatient,theHO.theVisit);
    }//GEN-LAST:event_jMenuItemOrderListByDateActionPerformed
    
	private void jMenuItemOrderListByVnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOrderListByVnActionPerformed
        if(theHO.theVisit == null)
        {   
            setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return;
        }
        theHD.showDialogOrderOldVisitByVn(theHO.thePatient,theHO.theVisit);
    }//GEN-LAST:event_jMenuItemOrderListByVnActionPerformed
   
	private void jMenuItemDeathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeathActionPerformed
            theHD.showDialogDeath();
    }//GEN-LAST:event_jMenuItemDeathActionPerformed
   
	private void jMenuItemReverseAdmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReverseAdmitActionPerformed
        int countHour = DateUtil.countHour(theHO.theVisit.begin_admit_time
            ,theHC.theConnectionInf);
        if(countHour >24 && theHC.theLO.theOption.can_cancel_admit.equals("0"))
        {
            this.setStatus("ผู้ป่วยถูก Admit เกิน 24 ชั่วโมงแล้วไม่สามารถยกเลิกได้"
                ,UpdateStatus.WARNING);            
            return;
        }
        if(theHO.theVisit.is_discharge_doctor.equals("1"))
        {
            this.setStatus("ผู้ป่วยถูกจำหน่ายทางการแพทย์ แล้วไม่สามารถยกเลิกได้",UpdateStatus.WARNING);
            return;
        }
        theHD.showDialogReverseAdmit(theHC, theUS);        
    }//GEN-LAST:event_jMenuItemReverseAdmitActionPerformed
    
	private void jMenuItemOrderSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOrderSetActionPerformed
        if(theHO.theVisit == null){   
            setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return;
        }
        theHD.showDialogOrderSet(theHO.theVisit);
    }//GEN-LAST:event_jMenuItemOrderSetActionPerformed
    private void jMenuItemListAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemListAppointmentActionPerformed
        theHD.showDialogAppointmentlist(theHO.thePatient,theHO.theVisit);
    }//GEN-LAST:event_jMenuItemListAppointmentActionPerformed
    private void jMenuItemVisitHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVisitHistoryActionPerformed
        if(theHO.thePatient == null){      
            setStatus("ยังไม่ได้เลือกผู้ป่วย",UpdateStatus.WARNING);
            return;
        }
        theHD.showDialogHistoryPatient(theHO.thePatient);
    }//GEN-LAST:event_jMenuItemVisitHistoryActionPerformed
   
	private void jMenuItemSetPrefixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSetPrefixActionPerformed
        Vector vPrefix = theHC.theLookupControl.listPrefix();
        if(theHO.thePatient==null)
            theHD.showDialogSetPrefix(vPrefix,"");
        else
            theHD.showDialogSetPrefix(vPrefix,theHO.thePatient.f_prefix_id);
    }//GEN-LAST:event_jMenuItemSetPrefixActionPerformed
    
	private void jMenuItemConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConnectionActionPerformed
        boolean ret = DialogConfig.showDialog(this,Constant.getTextBundle("เลือกฐานข้อมูล"),true);
        if(theUS.confirmBox("ยืนยันการเปลี่ยนฐานข้อมูลตอบตกลงเพื่อออกจากโปรแกรม",UpdateStatus.WARNING))
            System.exit(0);
    }//GEN-LAST:event_jMenuItemConnectionActionPerformed
    
	private void jMenuItemAccidentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAccidentActionPerformed
        theHD.showDialogAccident();        
    }//GEN-LAST:event_jMenuItemAccidentActionPerformed
   
	private void jMenuItemAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAppointmentActionPerformed
        if(theHO.thePatient == null) {      
            setStatus("ยังไม่ได้เลือกผู้ป่วย",UpdateStatus.WARNING);
            return;
        }
        theHD.showDialogAppointment(theHO.thePatient,theHO.theVisit);
    }//GEN-LAST:event_jMenuItemAppointmentActionPerformed
	
	private void jMenuItemWaitDoctorDischargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemWaitDoctorDischargeActionPerformed
        theHC.theVisitControl.remainDoctorDischarge();
    }//GEN-LAST:event_jMenuItemWaitDoctorDischargeActionPerformed
    private void jMenuItemDropVisitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDropVisitActionPerformed
        theHC.theVisitControl.dropVisitPatient(); 
    }//GEN-LAST:event_jMenuItemDropVisitActionPerformed
    private void jMenuItemXrayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemXrayActionPerformed
        if(theHP.aPanelXray!=null)
            theHP.theJTabbedPane.setSelectedComponent(theHP.aPanelXray);
    }//GEN-LAST:event_jMenuItemXrayActionPerformed
    private void jMenuItemLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLabActionPerformed
        if(theHP.aPanelLab!=null)
            theHP.theJTabbedPane.setSelectedComponent(theHP.aPanelLab);
    }    private void jMenuItemBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-LAST:event_jMenuItemLabActionPerformed
        if(theHP.aPanelBilling!=null)//GEN-FIRST:event_jMenuItemBillActionPerformed
            theHP.theJTabbedPane.setSelectedComponent(theHP.aPanelBilling);
    }//GEN-LAST:event_jMenuItemBillActionPerformed
    private void jMenuItemIcdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemIcdActionPerformed
        if(theHP.aPanelDiagIcd!=null)
            theHP.theJTabbedPane.setSelectedComponent(theHP.aPanelDiagIcd);
    }//GEN-LAST:event_jMenuItemIcdActionPerformed
    private void jMenuItemOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOrderActionPerformed
        if(theHP.aPanelOrder!=null)
            theHP.theJTabbedPane.setSelectedComponent(theHP.aPanelOrder);
    }//GEN-LAST:event_jMenuItemOrderActionPerformed
    private void jMenuItemVitalSignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVitalSignActionPerformed
      if(theHP.aPanelVitalSign!=null)
        theHP.theJTabbedPane.setSelectedComponent(theHP.aPanelVitalSign);
    }//GEN-LAST:event_jMenuItemVitalSignActionPerformed
    private void jMenuItemVisitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVisitActionPerformed
      if(theHP.aPanelVisit!=null)
        theHP.theJTabbedPane.setSelectedComponent(theHP.aPanelVisit);
    }//GEN-LAST:event_jMenuItemVisitActionPerformed
    private void jMenuItemSocialDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSocialDataActionPerformed
      if(theHP.aPanelSocialData!=null)
        theHP.theJTabbedPane.setSelectedComponent(theHP.aPanelSocialData);
    }//GEN-LAST:event_jMenuItemSocialDataActionPerformed
    private void jMenuItemRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRefreshActionPerformed
        setCursor(Constant.CUR_DEFAULT);
        theHC.theLookupControl.refreshLookup();
        theHP.refreshLookup();
        if(theHO.theVisit==null){ 
            setStatus("ผู้ป่วยยังไม่ได้เปิด Visit",UpdateStatus.WARNING);
            return;
        }
        theHC.theVisitControl.readVisitPatientByVid(theHO.theVisit.getObjectId());

    }//GEN-LAST:event_jMenuItemRefreshActionPerformed
    private void jMenuItemSendBackWardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSendBackWardActionPerformed
        theHC.theVisitControl.sendVisitBackWard(theHO.theVisit);
    }//GEN-LAST:event_jMenuItemSendBackWardActionPerformed
    private void jMenuItemUnlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUnlockActionPerformed
        //ยืนยันการตรวจรักษาของแพทย์
        //henbe_error เพื่ออะไร
        //theHC.theVisitControl.confirmDoctorTreament();
        theHC.theVisitControl.unlockVisit();
    }//GEN-LAST:event_jMenuItemUnlockActionPerformed
    private void jMenuItemObserveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemObserveActionPerformed
        theHC.theVisitControl.observVisit(theHO.theVisit,"1");
    }//GEN-LAST:event_jMenuItemObserveActionPerformed
    private void jMenuItemAdmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAdmitActionPerformed
        if(theHO.theVisit == null)
        {   
            setStatus("ยังไม่ได้เลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return;
        }
        //amp:5/8/2549 : รวมการแสดงหมาย AN ที่ถูกยกเลิก และรายละเอียดการ Admit เข้าด้วยกัน
        theHD.showDialogAdmit(theHO.theVisit, theHD);
    }//GEN-LAST:event_jMenuItemAdmitActionPerformed
   
    private void jMenuItemQuitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItemQuitActionPerformed
    {//GEN-HEADEREND:event_jMenuItemQuitActionPerformed
        if(!theUS.confirmBox("ออกจากโปรแกรม",UpdateStatus.WARNING))
            return;
        theHC.theSystemControl.exitProgram();
    }//GEN-LAST:event_jMenuItemQuitActionPerformed
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        jMenuItemQuitActionPerformed(null);
    }//GEN-LAST:event_exitForm

    private void jMenuItemFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFontActionPerformed
        FontDialog f = new FontDialog(this);
        f.showDialog();
}//GEN-LAST:event_jMenuItemFontActionPerformed

    private void jMenuItemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNewActionPerformed
        theSplash.showDialog(theUS);
    }//GEN-LAST:event_jMenuItemNewActionPerformed

    private void jMenuHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuHelpActionPerformed
        
    }//GEN-LAST:event_jMenuHelpActionPerformed

    private void jCheckBoxMenuItemReportBugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemReportBugActionPerformed
        if(jCheckBoxMenuItemReportBug.isSelected())
            theHO.is_auto_report_bug = true;
        else
            theHO.is_auto_report_bug = false;
    }//GEN-LAST:event_jCheckBoxMenuItemReportBugActionPerformed

    private void jMenuItemWSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemWSActionPerformed
        if(thePanelConfigWS == null)
        {
            thePanelConfigWS = new PanelConfigWS();
            thePanelConfigWS.setControl(theHC);
        }
        thePanelConfigWS.showDialog();
    }//GEN-LAST:event_jMenuItemWSActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        theHD.showPanelSetupMapNhsoPlan();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItemPrint7CertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPrint7CertActionPerformed
        // TODO add your handling code here:
        theHC.thePrintControl.printMed7Cert();
    }//GEN-LAST:event_jMenuItemPrint7CertActionPerformed

    private void jMenuItemScanOPDRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemScanOPDRecordActionPerformed
        // TODO add your handling code here:
        theHD.showPanelScanOPDRecord();//+1
    }//GEN-LAST:event_jMenuItemScanOPDRecordActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.utility.ClockLabel clockLabel1;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemCalDayByHour;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemReportBug;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenu jMenuIpd;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemAccident;
    private javax.swing.JMenuItem jMenuItemAccidentGroup;
    private javax.swing.JMenuItem jMenuItemAdmit;
    private javax.swing.JMenuItem jMenuItemAppointment;
    private javax.swing.JMenuItem jMenuItemBill;
    private javax.swing.JMenuItem jMenuItemBillingHistory;
    private javax.swing.JMenuItem jMenuItemBorrowFilmXray;
    private javax.swing.JMenuItem jMenuItemBorrowOpdCard;
    private javax.swing.JMenuItem jMenuItemChronic;
    private javax.swing.JMenuItem jMenuItemConnection;
    private javax.swing.JMenuItem jMenuItemDeath;
    private javax.swing.JMenuItem jMenuItemDischargeIPD;
    private javax.swing.JMenuItem jMenuItemDropVisit;
    private javax.swing.JMenuItem jMenuItemFont;
    private javax.swing.JMenuItem jMenuItemHistoryOrderContinue;
    private javax.swing.JMenuItem jMenuItemIcd;
    private javax.swing.JMenuItem jMenuItemLab;
    private javax.swing.JMenuItem jMenuItemLabResult;
    private javax.swing.JMenuItem jMenuItemLabreferOut;
    private javax.swing.JMenuItem jMenuItemLabreferin;
    private javax.swing.JMenuItem jMenuItemListAppointment;
    private javax.swing.JMenuItem jMenuItemLogin;
    private javax.swing.JMenuItem jMenuItemNew;
    private javax.swing.JMenuItem jMenuItemObserve;
    private javax.swing.JMenuItem jMenuItemOrder;
    private javax.swing.JMenuItem jMenuItemOrderCotinueDrug;
    private javax.swing.JMenuItem jMenuItemOrderHistory;
    private javax.swing.JMenuItem jMenuItemOrderListByDate;
    private javax.swing.JMenuItem jMenuItemOrderListByVn;
    private javax.swing.JMenuItem jMenuItemOrderSet;
    private javax.swing.JMenuItem jMenuItemPreveiwReport16Group;
    private javax.swing.JMenuItem jMenuItemPreveiwReportBilling;
    private javax.swing.JMenuItem jMenuItemPreviewOpdCard;
    private javax.swing.JMenuItem jMenuItemPreviewReportItem;
    private javax.swing.JMenuItem jMenuItemPreviewReportItemByItemName;
    private javax.swing.JMenuItem jMenuItemPreviewReportOrder;
    private javax.swing.JMenuItem jMenuItemPreviewSelectDrugList;
    private javax.swing.JMenuItem jMenuItemPreviewSummaryDischarge;
    private javax.swing.JMenuItem jMenuItemPreviewVisitSlip;
    private javax.swing.JMenuItem jMenuItemPrint7Cert;
    private javax.swing.JMenuItem jMenuItemPrintAllPatientInWard;
    private javax.swing.JMenuItem jMenuItemPrintDrugRx;
    private javax.swing.JMenuItem jMenuItemPrintDrugSticker;
    private javax.swing.JMenuItem jMenuItemPrintForPatient;
    private javax.swing.JMenuItem jMenuItemPrintIPDnameInChart;
    private javax.swing.JMenuItem jMenuItemPrintIndex;
    private javax.swing.JMenuItem jMenuItemPrintMedCertIll;
    private javax.swing.JMenuItem jMenuItemPrintMedCertInterview;
    private javax.swing.JMenuItem jMenuItemPrintOpdCard;
    private javax.swing.JMenuItem jMenuItemPrintReport16Group;
    private javax.swing.JMenuItem jMenuItemPrintReportBilling;
    private javax.swing.JMenuItem jMenuItemPrintReportItem;
    private javax.swing.JMenuItem jMenuItemPrintReportItemByItemName;
    private javax.swing.JMenuItem jMenuItemPrintReportOrder;
    private javax.swing.JMenuItem jMenuItemPrintSelectDrugList;
    private javax.swing.JMenuItem jMenuItemPrintSummaryDischarge;
    private javax.swing.JMenuItem jMenuItemPrintVisitSlip;
    private javax.swing.JMenuItem jMenuItemPrintXNIndex;
    private javax.swing.JMenuItem jMenuItemQuit;
    private javax.swing.JMenuItem jMenuItemReceiveDrug;
    private javax.swing.JMenuItem jMenuItemReferPatient;
    private javax.swing.JMenuItem jMenuItemRefresh;
    private javax.swing.JMenuItem jMenuItemReport;
    private javax.swing.JMenuItem jMenuItemReverseAdmit;
    private javax.swing.JMenuItem jMenuItemReverseDoctor;
    private javax.swing.JMenuItem jMenuItemReverseFinancial;
    private javax.swing.JMenuItem jMenuItemReverseObserve;
    private javax.swing.JMenuItem jMenuItemScanOPDRecord;
    private javax.swing.JMenuItem jMenuItemSearchPatient;
    private javax.swing.JMenuItem jMenuItemSendBackWard;
    private javax.swing.JMenuItem jMenuItemServeil;
    private javax.swing.JMenuItem jMenuItemSetPartFont;
    private javax.swing.JMenuItem jMenuItemSetPartPrint;
    private javax.swing.JMenuItem jMenuItemSetPrefix;
    private javax.swing.JMenuItem jMenuItemShowDxDoctor;
    private javax.swing.JMenuItem jMenuItemSocialData;
    private javax.swing.JMenuItem jMenuItemTheme;
    private javax.swing.JMenuItem jMenuItemUnlock;
    private javax.swing.JMenuItem jMenuItemViewHO;
    private javax.swing.JMenuItem jMenuItemViewSequence;
    private javax.swing.JMenuItem jMenuItemVisit;
    private javax.swing.JMenuItem jMenuItemVisitHistory;
    private javax.swing.JMenuItem jMenuItemVitalSign;
    private javax.swing.JMenuItem jMenuItemWS;
    private javax.swing.JMenuItem jMenuItemWaitDoctorDischarge;
    private javax.swing.JMenuItem jMenuItemXray;
    private javax.swing.JMenu jMenuModule;
    private javax.swing.JMenu jMenuOpdCard;
    private javax.swing.JMenu jMenuOrder;
    private javax.swing.JMenu jMenuPatientGeneral;
    private javax.swing.JMenu jMenuPrintAll;
    private javax.swing.JMenu jMenuPrintIPDNameCard;
    private javax.swing.JMenu jMenuPrintMedCert;
    private javax.swing.JMenu jMenuPrintSummary;
    private javax.swing.JMenu jMenuPrintVisitSlip;
    private javax.swing.JMenu jMenuReportSum16Group;
    private javax.swing.JMenu jMenuReportSumGroupBilling;
    private javax.swing.JMenu jMenuReportSumGroupItem;
    private javax.swing.JMenu jMenuReportSumItem;
    private javax.swing.JMenu jMenuReverse;
    private javax.swing.JMenu jMenuSelectDrugList;
    private javax.swing.JMenu jMenuTab;
    private javax.swing.JMenu jMenuTool;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelCenter;
    private javax.swing.JPanel jPanelStatus;
    private javax.swing.JPanel jPanelTop;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    // End of variables declaration//GEN-END:variables
   
    
    /** 
     * ใช้ในการแสดง Dialog ของการลงรหัสโรคใหม่
     */
    public void showDialogDxDoctorClinic()
    {
        if(Authentication.DOCTOR.equalsIgnoreCase(this.theHO.theEmployee.authentication_id))
            this.theHD.showDialogClinicDiag(theHO.theDiagDoctorClinic);
        else
            this.theHD.showDialogDoctorDiag(theHO.theDiagDoctorClinic);
    }
    
    
    
    /**
     * ใช้ในการเปลี่ยนการ login ของผู้ใช้งาน
     * @author padungrat(tong)
     * @date 3/04/49,11:02     
     */
    public void loginEmployee()
    {  
	char[] pass=null;
	ServicePoint sp=null;
        
        theHC.theVisitControl.unlockVisit();
        //เมื่อเรียกฟังชัน showDialogUser โปรแกรมจะเปลี่ยนค่านี้ให้ทำให้ต้องเก็บค่าเดิมไว้ก่อน
        Employee emp_old = theHO.theEmployee;
        ServicePoint sp_old = theHO.theServicePoint;
        String[] user_service = FrameLogin.showDialogUserNew(this,theHC,null,null,sp,1);
        if(user_service==null){
            return;
        }
        Constant.println("_________________________________"+auth);
        Constant.println(theHO.theEmployee.authentication_id);
        Employee emp = theHC.theLookupControl.readEmployeeByUsername(user_service[0]);
        ServicePoint ser = theHC.theLookupControl.readServicePointById(user_service[1]);
        //หากสิทธิไม่เท่ากันย่อมไม่สามารถเปลี่ยนสิทธิได้
        if(!emp.authentication_id.equals(authen_old)){
            theUS.setStatus("สิทธิการใช้งานแตกต่างกันกรุณาปิดโปรแกรมแล้วเปิดใหม่",UpdateStatus.WARNING);
            theHO.theEmployee = emp_old;
            theHO.theServicePoint = sp_old;
            return;
        }
        theHO.theEmployee = emp;
        theHO.theServicePoint = ser;
        theHO.theDiagDoctorClinic.doctor_id = emp.getObjectId();
        
        setTitle(theHO.theEmployee.fname
            + "  " + theHO.theEmployee.lname
            + " - HospitalOS Community Edition"
            + " version " + theHC.theSystemControl.getAppVersion().app_code);
        theUS.setStatus("การเปลี่ยนชื่อผู้ใช้งานเสร็จสิ้น",UpdateStatus.COMPLETE);
    }
    
    /**
     * ใช้ในการพิมพ์รายการยาที่เลือก หรือใบสั่งยา
     * @author pongtorn(henbe)
     * @date 15/05/49,11:02     
     */    
    private void printDrugList(int preview)
    {
        if(theHO.theVisit == null){   
            setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return;
        }
        if(!Gutil.checkModulePrinting()){    
             theUS.setStatus("ไม่สามารถพิมพ์ได้ ยังขาด Module Printing",UpdateStatus.WARNING);
             return ;
        }
        PrintSelectDrugList pd = new PrintSelectDrugList();
        if(!DialogChooseOrderItemPrint.showDialog(theHC,pd,nopreview)){
            return;
        }
        Vector vOr = theHO.vOrderItem;
        if(theHP.aPanelOrder!=null)
            vOr = theHP.aPanelOrder.getOrderItemV();
        else
            vOr = new Vector();
        
        Constant.println("private void printDrugList(int preview) " + vOr.size());
        
        int[] rows = new int[vOr.size()];
        for(int i=0,size=vOr.size();i<size;i++){
            rows[i] = i;
        }
        theHC.thePrintControl.printSelectDrugList(pd,preview,vOr,rows);
    }
    
    private void JTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt){
        theHC.theVitalControl.closeAllBalloon();
    }
    private void setVisit(Visit v)
    {
        setVisit(v,true);
    }
    private void setVisit(Visit v,boolean goto_list)
    {
        boolean is_Vnull = (v==null);
        boolean is_Pnull = (theHO.thePatient==null);
        boolean is_Fnull = (theHO.theFamily==null);
        if(is_Vnull && is_Pnull && is_Fnull && goto_list)
            if(theHP.theJTabbedPane.getTabCount()>0)
                theHP.theJTabbedPane.setSelectedIndex(0);
        jMenuItemAdmit.setEnabled(!is_Vnull);
        jMenuItemOrderCotinueDrug.setEnabled(!is_Vnull);
        jMenuItemHistoryOrderContinue.setEnabled(!is_Vnull);
        jMenuItemOrderListByDate.setEnabled(!is_Vnull);
        jMenuItemOrderListByVn.setEnabled(!is_Vnull);          
        jMenuItemObserve.setEnabled(!is_Vnull);
        jMenuItemReverseObserve.setEnabled(!is_Vnull);
        jMenuItemOrderSet.setEnabled(!is_Vnull);  
        jMenuItemReceiveDrug.setEnabled(!is_Vnull);
//        jMenuItemReferPatient.setEnabled(!is_Vnull);
        //jMenuItemRefresh.setEnabled(!is_Vnull);
        jMenuItemReverseAdmit.setEnabled(!is_Vnull);
        jMenuItemReverseDoctor.setEnabled(!is_Vnull);
        jMenuItemReverseFinancial.setEnabled(!is_Vnull);
        jMenuItemSendBackWard.setEnabled(!is_Vnull);
        jMenuItemWaitDoctorDischarge.setEnabled(!is_Vnull);
        jMenuItemDropVisit.setEnabled(!is_Vnull);
        //jMenuPrintAll.setEnabled(!is_Vnull);
        jMenuSelectDrugList.setEnabled(!is_Vnull);
        jMenuItemVisitHistory.setEnabled(!is_Pnull);
        jMenuItemOrderHistory.setEnabled(!is_Pnull); 
        jMenuItemAppointment.setEnabled(!is_Pnull);
        jMenuItemBillingHistory.setEnabled(!is_Pnull);
     
        if(!is_Vnull){
            boolean is_opd = false;
            if(v.visit_type!=null)
            {
                is_opd = v.visit_type.equals(VisitType.OPD);
            }
            boolean is_observ = false;
            if(v.observe!=null)
            {
                is_observ = v.observe.equals("1");
            }
            Constant.setEnabled(jMenuItemOrderListByDate,!is_opd);
            Constant.setEnabled(jMenuItemOrderCotinueDrug,!is_opd);
            Constant.setEnabled(jMenuItemHistoryOrderContinue,!is_opd);
            Constant.setEnabled(jMenuItemReverseAdmit,!is_opd);
            Constant.setEnabled(jMenuItemObserve,is_opd);
            Constant.setEnabled(jMenuItemSendBackWard,!is_opd);
            Constant.setEnabled(jMenuItemReceiveDrug,!is_opd);
            if(is_opd)  jMenuItemAdmit.setText(Constant.getTextBundle("Admit"));
            else    jMenuItemAdmit.setText(Constant.getTextBundle("แก้ไขการ Admit"));
            boolean is_ipd = v.visit_type.equals(VisitType.IPD);
            Constant.setEnabled(jMenuItemObserve,!is_observ);
            Constant.setEnabled(jMenuItemReverseObserve,is_observ);

            boolean is_dd = v.is_discharge_doctor.equals("1");
            Constant.setEnabled(jMenuItemReverseDoctor,is_dd);
            
            boolean is_fd = v.is_discharge_money.equals("1");
            Constant.setEnabled(jMenuItemReverseFinancial,is_fd);
            Constant.setEnabled(jMenuItemOrderListByDate,!is_fd);
            Constant.setEnabled(jMenuItemOrderListByVn,!is_fd);
            Constant.setEnabled(jMenuItemOrderCotinueDrug,!is_fd);
            Constant.setEnabled(jMenuItemOrderSet,!is_fd);
            Constant.setEnabled(jMenuItemReceiveDrug,!is_fd);
            Constant.setEnabled(jMenuItemAdmit,!is_fd);
            Constant.setEnabled(jMenuItemObserve,!is_fd);
            Constant.setEnabled(jMenuItemWaitDoctorDischarge,!is_Vnull);
            Constant.setEnabled(jMenuItemDropVisit,!is_Vnull);

            //amp:3/6/2549:เมื่ออยู่ในสถานะค้างบันทึก จะ dropvisit และ ค้างบันทึกไม่ได้
            boolean is_remain = v.visit_status.equals("2");
            Constant.setEnabled(jMenuItemDropVisit,!is_remain);
            Constant.setEnabled(jMenuItemWaitDoctorDischarge,!is_remain);
            
            boolean is_end = v.visit_status.equals("3");
            Constant.setEnabled(jMenuItemDropVisit,!is_end);
            Constant.setEnabled(jMenuItemWaitDoctorDischarge,!is_end);
            
            boolean is_locker = v.lock_user.equals(theHO.theEmployee.getObjectId());
            Constant.setEnabled(jMenuItemAdmit,is_locker);
            Constant.setEnabled(jMenuItemOrderCotinueDrug,is_locker);
            Constant.setEnabled(jMenuItemOrderListByDate,is_locker);
            Constant.setEnabled(jMenuItemOrderListByVn,is_locker);          
            Constant.setEnabled(jMenuItemOrderSet,is_locker);
            Constant.setEnabled(jMenuItemReceiveDrug,is_locker);
            Constant.setEnabled(jMenuItemHistoryOrderContinue,is_locker);
            Constant.setEnabled(jMenuItemObserve,is_locker);
            //Constant.setEnabled(jMenuItemReferPatient,is_locker);
            Constant.setEnabled(jMenuItemReverseAdmit,is_locker);
            Constant.setEnabled(jMenuItemReverseDoctor,is_locker);
            Constant.setEnabled(jMenuItemReverseFinancial,is_locker);
            Constant.setEnabled(jMenuItemSendBackWard,is_locker);
            Constant.setEnabled(jMenuItemWaitDoctorDischarge,is_locker);
            Constant.setEnabled(jMenuItemDropVisit,is_locker);
        }
        
    }
 
   /**
    * Overridden so we can exit when window is closed
    * @param ev WindowEvent
    */
   protected void processWindowEvent(WindowEvent ev){
     if(ev.getID() == WindowEvent.WINDOW_CLOSING){
        exitForm(null);
     }
   }
    
        
//    /**\     * @roseuid 3F83FA0303B9     */
//    public void notifycreateVisitPatient(Visit vnPatient, String msg) 
//    {
//            if(auth.equals(Authentication.PHARM)){
//                //ใช้เมื่อ สามารถนำเลข An มาใช้ใหม่ไก้แล้ว
//                jMenuItemReverseAdmit.setVisible(false);
//                jMenuItemAdmit.setVisible(false);
//                jMenuItemObserve.setVisible(false);
//            }
//            if(auth.equals(Authentication.CASH)) {
//                //ใช้เมื่อ สามารถนำเลข An มาใช้ใหม่ไก้แล้ว
//                jMenuItemReverseAdmit.setVisible(false);
//                //เนื่องจากเมนู Admit กับเมนู แก้ไขการ Admit คือเมนูเดียวกัน แต่สลับ show 
//                jMenuItemAdmit.setVisible(false);
//                jMenuItemObserve.setVisible(false);
//            }           
//            if(auth.equals(Authentication.STAT)){
//                jMenuOrder.setEnabled(false);        
//                jMenuIpd.setEnabled(false);
//            }
//            if(auth.equals(Authentication.LAB)){
//                jMenuOrder.setEnabled(false);        
//                jMenuIpd.setEnabled(false);
//            }
//            if(auth.equals(Authentication.XRAY)){
//                jMenuOrder.setEnabled(false);        
//                jMenuIpd.setEnabled(false);
//            }
//            if(auth.equals(Authentication.INSURE)){
//                jMenuIpd.setVisible(true);
//                jMenuItemAdmit.setVisible(false);  
//                jMenuItemReverseAdmit.setVisible(false);
//                jMenuItemObserve.setVisible(false);  
//                jMenuItemReverseObserve.setVisible(false); 
//                jMenuItemSendBackWard.setVisible(true);
//                jMenuItemHistoryOrderContinue.setVisible(false);
//                jMenuReverse.setVisible(true);
//            }
//    }
        
    public void setLanguage(String msg)
    {
        GuiLang.setLanguage(jCheckBoxMenuItemReportBug);
        GuiLang.setLanguage(jCheckBoxMenuItemCalDayByHour);
        GuiLang.setLanguage(jMenuFile);
        GuiLang.setLanguage(jMenuHelp);
        GuiLang.setLanguage(jMenuIpd);
        GuiLang.setLanguage(jMenuItemAbout);
        GuiLang.setLanguage(jMenuItemAccident);
        GuiLang.setLanguage(jMenuItemAdmit);
        GuiLang.setLanguage(jMenuItemAppointment);
        GuiLang.setLanguage(jMenuItemBill);
        GuiLang.setLanguage(jMenuItemBillingHistory);
        GuiLang.setLanguage(jMenuItemBorrowFilmXray);
        GuiLang.setLanguage(jMenuItemBorrowOpdCard);
        GuiLang.setLanguage(jMenuItemChronic);
        GuiLang.setLanguage(jMenuItemConnection);
        GuiLang.setLanguage(jMenuItemDeath);
        GuiLang.setLanguage(jMenuItemDischargeIPD);
        GuiLang.setLanguage(jMenuItemDropVisit);
        GuiLang.setLanguage(jMenuItemHistoryOrderContinue);
        GuiLang.setLanguage(jMenuItemIcd);
        GuiLang.setLanguage(jMenuItemLab);
        GuiLang.setLanguage(jMenuItemLabreferin);
        GuiLang.setLanguage(jMenuItemLabreferOut);
        GuiLang.setLanguage(jMenuItemLabResult);
        GuiLang.setLanguage(jMenuItemListAppointment);
        GuiLang.setLanguage(jMenuItemLogin);
        GuiLang.setLanguage(jMenuItemObserve);
        GuiLang.setLanguage(jMenuItemOrder);
        GuiLang.setLanguage(jMenuItemOrderCotinueDrug);
        GuiLang.setLanguage(jMenuItemOrderHistory);
        GuiLang.setLanguage(jMenuItemOrderListByDate);
        GuiLang.setLanguage(jMenuItemOrderListByVn);
        GuiLang.setLanguage(jMenuItemOrderSet);
        GuiLang.setLanguage(jMenuItemPreveiwReport16Group);
        GuiLang.setLanguage(jMenuItemPreveiwReportBilling);
        GuiLang.setLanguage(jMenuItemPreviewOpdCard);
        GuiLang.setLanguage(jMenuItemPreviewReportItem);
        GuiLang.setLanguage(jMenuItemPreviewReportItemByItemName);
        GuiLang.setLanguage(jMenuItemPreviewReportOrder);
        GuiLang.setLanguage(jMenuItemPreviewSelectDrugList);
        GuiLang.setLanguage(jMenuItemPreviewSummaryDischarge);
        GuiLang.setLanguage(jMenuItemPreviewVisitSlip);
        GuiLang.setLanguage(jMenuItemPrintAllPatientInWard);
        GuiLang.setLanguage(jMenuItemPrintDrugRx);
        GuiLang.setLanguage(jMenuItemPrintDrugSticker);
        GuiLang.setLanguage(jMenuItemPrintForPatient);
        GuiLang.setLanguage(jMenuItemPrintIndex);
        GuiLang.setLanguage(jMenuItemPrintIPDnameInChart);
        GuiLang.setLanguage(jMenuItemPrintMedCertIll);
        GuiLang.setLanguage(jMenuItemPrintMedCertInterview);
        GuiLang.setLanguage(jMenuItemPrintOpdCard);
        GuiLang.setLanguage(jMenuItemPrintReport16Group);
        GuiLang.setLanguage(jMenuItemPrintReportBilling);
        GuiLang.setLanguage(jMenuItemPrintReportItem);
        GuiLang.setLanguage(jMenuItemPrintReportItemByItemName);
        GuiLang.setLanguage(jMenuItemPrintReportOrder);
        GuiLang.setLanguage(jMenuItemPrintSelectDrugList);
        GuiLang.setLanguage(jMenuItemPrintSummaryDischarge);
        GuiLang.setLanguage(jMenuItemPrintVisitSlip);
        GuiLang.setLanguage(jMenuItemPrintXNIndex);
        GuiLang.setLanguage(jMenuItemQuit);
        GuiLang.setLanguage(jMenuItemReceiveDrug);
        GuiLang.setLanguage(jMenuItemReferPatient);
        GuiLang.setLanguage(jMenuItemRefresh);
        GuiLang.setLanguage(jMenuItemReport);
        GuiLang.setLanguage(jMenuItemReverseAdmit);
        GuiLang.setLanguage(jMenuItemReverseDoctor);
        GuiLang.setLanguage(jMenuItemReverseFinancial);
        GuiLang.setLanguage(jMenuItemReverseObserve);
        GuiLang.setLanguage(jMenuItemSearchPatient);
        GuiLang.setLanguage(jMenuItemSendBackWard);
        GuiLang.setLanguage(jMenuItemServeil);
        GuiLang.setLanguage(jMenuItemSetPartFont);
        GuiLang.setLanguage(jMenuItemSetPartPrint);
        GuiLang.setLanguage(jMenuItemSetPrefix);
        GuiLang.setLanguage(jMenuItemShowDxDoctor);
        GuiLang.setLanguage(jMenuItemSocialData);
        GuiLang.setLanguage(jMenuItemTheme); 
        GuiLang.setLanguage(jMenuItemUnlock);
        GuiLang.setLanguage(jMenuItemViewHO);
        GuiLang.setLanguage(jMenuItemViewSequence);
        GuiLang.setLanguage(jMenuItemVisit);
        GuiLang.setLanguage(jMenuItemVisitHistory);
        GuiLang.setLanguage(jMenuItemVitalSign);
        GuiLang.setLanguage(jMenuItemWaitDoctorDischarge);
        GuiLang.setLanguage(jMenuItemXray);
        GuiLang.setLanguage(jMenuModule);
        GuiLang.setLanguage(jMenuOpdCard);
        GuiLang.setLanguage(jMenuOrder);
        GuiLang.setLanguage(jMenuPatientGeneral);
        GuiLang.setLanguage(jMenuPrintAll);
        GuiLang.setLanguage(jMenuPrintIPDNameCard);
        GuiLang.setLanguage(jMenuPrintMedCert);
        GuiLang.setLanguage(jMenuPrintSummary);
        GuiLang.setLanguage(jMenuPrintVisitSlip);
        GuiLang.setLanguage(jMenuReportSum16Group);
        GuiLang.setLanguage(jMenuReportSumGroupBilling);
        GuiLang.setLanguage(jMenuReportSumGroupItem);
        GuiLang.setLanguage(jMenuReportSumItem);
        GuiLang.setLanguage(jMenuReverse);
        GuiLang.setLanguage(jMenuSelectDrugList);
        GuiLang.setLanguage(jMenuTab);
        GuiLang.setLanguage(jMenuTool);
        GuiLang.setLanguage(jMenuItemOrder);
        GuiLang.setLanguage(jMenuItemDischargeIPD);
        GuiLang.setLanguage(jMenuItemNew);
        GuiLang.setLanguage(jMenuItemFont);
    }
    
    public void setStatus(String str, int status) 
    {   
        str = Constant.getTextBundle(str);
        Constant.println("public void setStatus(String str, int status) " + str);
        ThreadStatus theTT = new ThreadStatus(this,this.jLabelStatus);
        jLabelStatus.setText(" " + str);
        Constant.println("----SetStatus---- " + str);
        if(status == UpdateStatus.WARNING){
            jLabelStatus.setBackground(Color.YELLOW);
            if(theHC.theSystemControl.isSoundEnabled())
                Audio.getAudio("config/sound/warning.wav").play();
        }
        if(status == UpdateStatus.COMPLETE){
            jLabelStatus.setBackground(Color.GREEN);
            if(theHC.theSystemControl.isSoundEnabled())
                Audio.getAudio("config/sound/complete.wav").play();
        }
        if(status == UpdateStatus.ERROR){
            jLabelStatus.setBackground(Color.RED);
            if(theHC.theSystemControl.isSoundEnabled())
                Audio.getAudio("config/sound/error.wav").play();
        }
        if(status == UpdateStatus.NORMAL)//amp:20/03/2549
        {
            jLabelStatus.setBackground(Color.GRAY);
        }
        theTT.start();
    }
    public boolean confirmBox(String str, int status) {
        int i = JOptionPane.showConfirmDialog(this,Constant.getTextBundle(str),Constant.getTextBundle("เตือน"),JOptionPane.YES_NO_OPTION);
        return (i==JOptionPane.YES_OPTION);
    }
    
    public void notifylistOrderItemReceiveDrugByVisitID(Vector oird, String status) 
    {
        vORID = oird;
        int min = 0;
        int hr = 0;
        if(vORID!=null)
        {
            for(int i=0; i<vORID.size(); i++)
            {
                theORID = (OrderItemReceiveDrug)vORID.get(i);
                if(!theORID.qty_receive.equals("null") && !theORID.qty_receive.equals(""))
                {
                    if(Integer.parseInt(theORID.qty_receive) < Integer.parseInt(theORID.qty_return))
                    {
                        min = min + 1;
                    }
                }
                else
                {
                    hr = hr + 1;
                }
            }
        }
    }
        
    public void setEnabled(boolean b) 
    {
        // Tab
            jMenuItemSocialData.setEnabled(b);
            jMenuItemVisit.setEnabled(b);
            jMenuItemVitalSign.setEnabled(b);
            jMenuItemOrder.setEnabled(b);
            jMenuItemIcd.setEnabled(b);
            jMenuItemBill.setEnabled(b);
            jMenuItemLab.setEnabled(b);
            jMenuItemXray.setEnabled(b);
            //Tool
            jMenuItemAppointment.setEnabled(b);
            jMenuItemListAppointment.setEnabled(b);
            jMenuItemVisitHistory.setEnabled(b);
            jMenuItemOrderHistory.setEnabled(b);
            jMenuItemBillingHistory.setEnabled(b);
            jMenuItemSetPrefix.setEnabled(b);
            jMenuItemLabreferOut.setEnabled(b);
            jMenuItemPrintXNIndex.setEnabled(b);
            jMenuItemBorrowFilmXray.setEnabled(b);
            jMenuItemBorrowOpdCard.setEnabled(b);
            //jMenuItemReportBilling.setEnabled(b);
            //ผู้ป่วยทั่วไป
            jMenuPatientGeneral.setVisible(b);
            jMenuItemWaitDoctorDischarge.setVisible(b);
            jMenuItemDropVisit.setVisible(b);
            jMenuItemPrintIndex.setVisible(b);
            jMenuItemAccident.setVisible(b);
            jMenuItemDeath.setVisible(b);
            jMenuItemChronic.setVisible(b);
            jMenuItemServeil.setVisible(b);
            // ผู้ป่วยใน
            jMenuIpd.setVisible(b);            
            jMenuItemAdmit.setVisible(b);       
            jMenuItemObserve.setVisible(b);      
            jMenuItemSendBackWard.setVisible(b);
            jMenuItemHistoryOrderContinue.setVisible(b);
            // การสั่งตรวจรักษา
            //จ่าย
            //คิดเงิน
            jMenuItemReceiveDrug.setEnabled(b);
            jMenuItemPrintDrugSticker.setEnabled(b);
            jMenuItemOrderSet.setEnabled(b);
            jMenuItemOrderListByVn.setEnabled(b);
            jMenuItemOrderCotinueDrug.setEnabled(b);
            jMenuItemOrderListByDate.setEnabled(b);
            // ย้อนกลับ
            jMenuItemReverseDoctor.setEnabled(b);
            jMenuItemReverseFinancial.setEnabled(b);
            //ข้อมูลทางส่งเสริม
            //พิมพ์
            jMenuItemLabResult.setEnabled(b);                   
    }
   
    public JFrame getJFrame() {
        return this;
    }
    
    public void notifyReadVisit(String str,int status)
    {
        Constant.println("public void notifyReadVisit(String str,int status)" + theHO.theEmployee.fname);
        setVisit(theHO.theVisit);
        showDefaultTab(theHO.theEmployee.default_tab);
        setStatus(str,status);
    }
    
    public void notifyVisitPatient(String str,int status) {
        setVisit(theHO.theVisit);
        setStatus(str,status);
    }
    
    public void notifyObservVisit(String str, int status)
    {
        setVisit(theHO.theVisit);
        jMenuItemReverseObserve.setEnabled(theHO.theVisit.observe.equals("1"));
        jMenuItemObserve.setEnabled(!theHO.theVisit.observe.equals("1"));
        setStatus(str,status);
    }
    
    public void notifyUnlockVisit(String str,int status)
    {
        setVisit(theHO.theVisit);       
        setStatus(str,status);
    }
    
    public void notifyAdmitVisit(String str,int status)
    {
        setVisit(theHO.theVisit);
        setStatus(str,status);
    }
    
    public void notifyDischargeDoctor(String str, int status){
        setVisit(theHO.theVisit);
        setStatus(str,status);
    }
    
    public void notifyCheckDoctorTreament(String msg, int state) {
        setStatus(msg,state);
    }
    
    public void notifyDropVisit(String str, int status) {
        setVisit(theHO.theVisit);
        setStatus(str,status);
    }
    
    public void notifySendVisit(String str, int status) {
        setVisit(theHO.theVisit);
        setStatus(str,status);
    }
    
    public void notifyDischargeFinancial(String str, int status) {
        setVisit(theHO.theVisit);
        setStatus(str,status);
    }
        
    public void notifyReverseFinancial(String str, int status) {
        setVisit(theHO.theVisit);
        setStatus(str,status);
    }
    
    public void notifyReverseDoctor(String str, int status) {
        setVisit(theHO.theVisit);
        setStatus(str,status);
    }
    
    public void notifyAddDxTemplate(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifyDeleteMapVisitDxByVisitId(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifyManageAppointment(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifyManageDrugAllergy(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifySavePatientPayment(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifyManagePhysicalExam(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifyManagePrimarySymptom(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifyManageVitalSign(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifySaveMapVisitDx(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifySaveDiagDoctor(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifyManageDiagIcd10(String str, int status) {
        setStatus(str,status);
        if (theHO.theSurveil != null) {
            theHD.showDialogSurveil(null);
            System.out.println("YESSSSSSSS showDialogSurveil");
        }else{
            System.out.println("YESSSSSSSS 111");
        }
        if (theHO.theChronic != null) {
            theHD.showDialogChronic(theHO.theVisit, null);
            System.out.println("YESSSSSSSS showDialogChronic");
        }else{
            System.out.println("YESSSSSSSS 222");
        }
        theHO.theSurveil = null;
        theHO.theChronic = null;
    }
    
    public void notifyManageDiagIcd9(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifyCalculateAllBillingInvoice(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifyCancelBill(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifyCancelBillingInvoice(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifyPatientPaidMoney(String str, int status) {
        setStatus(str,status);
    }
    
    public void showDefaultTab(String tab)
    {
        Constant.println("public void showDefaultTab(String tab)" + tab);
        // ตรวจสอบว่าเป็นการเลือกจากคิวรอจ่ายยา แล้วให้ Default แถบรายการตรวจรักษา sumo 18/7/2549
        if(tab.equals("1")){
            if(theHP.theJTabbedPane.indexOfComponent(theHP.aPanelSocialData)!=-1)
                theHP.theJTabbedPane.setSelectedComponent(
                    theHP.aPanelSocialData);
        }
        if(tab.equals("2")){
            if(theHP.theJTabbedPane.indexOfComponent(theHP.aPanelVisit)!=-1)
                theHP.theJTabbedPane.setSelectedComponent(
                    theHP.aPanelVisit);
        }
        if(tab.equals("3")){
            if(theHP.theJTabbedPane.indexOfComponent(theHP.aPanelVitalSign)!=-1)
                theHP.theJTabbedPane.setSelectedComponent(
                    theHP.aPanelVitalSign);
        }
        if(tab.equals("4")){
            if(theHP.theJTabbedPane.indexOfComponent(theHP.aPanelOrder)!=-1)
                theHP.theJTabbedPane.setSelectedComponent(
                    theHP.aPanelOrder);
        }
        if(tab.equals("5")){
            if(theHP.theJTabbedPane.indexOfComponent(theHP.aPanelDiagIcd)!=-1)
                theHP.theJTabbedPane.setSelectedComponent(
                    theHP.aPanelDiagIcd);
        }
        if(tab.equals("6")){
            if(theHP.theJTabbedPane.indexOfComponent(theHP.aPanelBilling)!=-1)
                theHP.theJTabbedPane.setSelectedComponent(
                    theHP.aPanelBilling);
        }
        if(tab.equals("7")){
            if(theHP.theJTabbedPane.indexOfComponent(theHP.aPanelLab)!=-1)
                theHP.theJTabbedPane.setSelectedComponent(
                    theHP.aPanelLab);
        }
        if(tab.equals("8")){
            if(theHP.theJTabbedPane.indexOfComponent(theHP.aPanelXray)!=-1)
                theHP.theJTabbedPane.setSelectedComponent(
                    theHP.aPanelXray);
        }
    }
    private void setMenuVisible(boolean b) {
//tab8
        jMenuItemSocialData.setVisible(b);
        jMenuItemVisit.setVisible(b);
        jMenuItemVitalSign.setVisible(b);
        jMenuItemOrder.setVisible(b);
        jMenuItemIcd.setVisible(b);
        jMenuItemBill.setVisible(b);
        jMenuItemLab.setVisible(b);
        jMenuItemXray.setVisible(b);
 //Tool
        jMenuItemAppointment.setVisible(b);
        jMenuItemListAppointment.setVisible(b);
        jMenuItemVisitHistory.setVisible(b);
        jMenuItemOrderHistory.setVisible(b);
        jMenuItemBillingHistory.setVisible(b);
        jMenuItemSetPrefix.setVisible(b);
        jMenuItemViewSequence.setVisible(b);
        jMenuItemLabreferOut.setVisible(b);
        jMenuItemLabreferin.setVisible(b);
        jMenuItemReferPatient.setVisible(b);
        jMenuItemBorrowFilmXray.setEnabled(b);
        jMenuItemBorrowOpdCard.setEnabled(b);
//ผู้ป่วยทั่วไป
        jMenuPatientGeneral.setVisible(b);
        jMenuItemWaitDoctorDischarge.setVisible(b);
        jMenuItemDropVisit.setVisible(b);
        jMenuItemAccident.setVisible(b);
        jMenuItemDeath.setVisible(b);
        jMenuItemChronic.setVisible(b);
        jMenuItemServeil.setVisible(b);
// ผู้ป่วยใน
        jMenuIpd.setVisible(b);
        jMenuItemAdmit.setVisible(b);
        jMenuItemReverseAdmit.setVisible(b);
        jMenuItemObserve.setVisible(b);
        jMenuItemReverseObserve.setVisible(b);
        jMenuItemSendBackWard.setVisible(b);
        jMenuItemHistoryOrderContinue.setVisible(b);
// การสั่งตรวจรักษา
        jMenuOrder.setVisible(b);
        jMenuItemReceiveDrug.setVisible(b);
        jMenuItemOrderSet.setVisible(b);
        jMenuItemOrderListByVn.setVisible(b);
        jMenuItemOrderListByDate.setVisible(b);
        jMenuItemOrderCotinueDrug.setVisible(b);
//พิมพ์
        jMenuPrintVisitSlip.setVisible(b);
        jMenuPrintSummary.setVisible(b);
        jMenuReportSumGroupItem.setVisible(b);
        jMenuReportSumGroupBilling.setVisible(b);
        jMenuReportSumItem.setVisible(b);
        jMenuReportSum16Group.setVisible(b);
        jMenuSelectDrugList.setVisible(b);
        jMenuOpdCard.setVisible(b);
        jMenuItemPrintDrugSticker.setVisible(b);
        jMenuItemPrintIndex.setVisible(b);
        jMenuItemPrintXNIndex.setVisible(b);
        jMenuItemLabResult.setVisible(b);
// ย้อนกลับ
        jMenuReverse.setVisible(b);
        jMenuItemReverseDoctor.setVisible(b);
        jMenuItemReverseFinancial.setVisible(b);
    }
        /**
     * ถ้าต้องการให้แสดงให้ทำ comment ไว้ เข้าใจยากเลยเปลี่ยนซะ
     */
    
    private void initUserAuthen(Employee e) 
    {
        GActionAuthV gaav = theHO.theGActionAuthV;
        this.jMenuItemWS.setVisible(theHO.theEmployee.authentication_id.equals(Authentication.ADMIN));
        this.jMenuFile.setVisible(gaav.isReadMenuSystem());
        this.jMenuIpd.setVisible(gaav.isReadMenuIpd());
        this.jMenuOrder.setVisible(gaav.isReadMenuOrder());
        this.jMenuPatientGeneral.setVisible(gaav.isReadMenuOpd());
        this.jMenuPrintAll.setVisible(gaav.isReadMenuPrint());
        this.jMenuReverse.setVisible(gaav.isReadMenuReverse());
        this.jMenuTab.setVisible(gaav.isReadMenuTab());
        this.jMenuTool.setVisible(gaav.isReadMenuTool());
//        this.jMenuView.setVisible(gaav.isReadMenuView());
        this.jMenuHelp.setVisible(gaav.isReadMenuHelp());
//System        
        this.jMenuItemSearchPatient.setVisible(gaav.isReadMenuViewSearch());
        this.jMenuItemRefresh.setVisible(gaav.isReadMenuViewRefresh());
        this.jMenuItemUnlock.setVisible(gaav.isReadMenuViewUnlock());
// Tab 
        theHP.initPanelCVisit(true,true);
        theHP.initPanelSOpd(gaav.isReadTabQueueOpd(),gaav.isWriteTabQueueOpd());
        theHP.initPanelSIpd(gaav.isReadTabQueueIpd(),gaav.isWriteTabQueueIpd());
        theHP.initPanelDPatient(gaav.isReadTabSocialData(),gaav.isWriteTabSocialData());     
        jMenuItemSocialData.setVisible(gaav.isReadTabSocialData());
        theHP.initPanelDVisit(gaav.isReadTabVisit(),gaav.isWriteTabVisit());
        jMenuItemVisit.setVisible(gaav.isReadTabVisit());
        theHP.initPanelDVital(gaav.isReadTabVitalSign(),gaav.isWriteTabVitalSign()); 
        jMenuItemVitalSign.setVisible(gaav.isReadTabVitalSign());
        theHP.initPanelDOrder(gaav.isReadTabOrder(),gaav.isWriteTabOrder());     
        jMenuItemOrder.setVisible(gaav.isReadTabOrder());
        theHP.initPanelDDiagIcd(gaav.isReadTabDiagnosis(),gaav.isWriteTabDiagnosis());   
        jMenuItemIcd.setVisible(gaav.isReadTabDiagnosis());
        theHP.initPanelDBill(gaav.isReadTabBilling(),gaav.isWriteTabBilling());       
        jMenuItemBill.setVisible(gaav.isReadTabBilling());
        theHP.initPanelDLab(gaav.isReadTabLab(),gaav.isWriteTabLab());       
        jMenuItemLab.setVisible(gaav.isReadTabLab());
        theHP.initPanelDXray(gaav.isReadTabXray(),gaav.isWriteTabXray());       
        jMenuItemXray.setVisible(gaav.isReadTabXray());
         
//Tool
        jMenuItemAppointment.setVisible(gaav.isReadMenuToolAppointment());
        jMenuItemListAppointment.setVisible(gaav.isReadMenuToolListAppointment());
        jMenuItemVisitHistory.setVisible(gaav.isReadMenuToolHistoryVisit());
        jMenuItemOrderHistory.setVisible(gaav.isReadMenuToolHistoryOrder());
        jMenuItemBillingHistory.setVisible(gaav.isReadMenuToolHistoryBilling());
        jMenuItemSetPrefix.setVisible(gaav.isReadMenuToolMatchPrefixSex());
        jMenuItemViewSequence.setVisible(gaav.isReadMenuToolSequenceNo());
        jMenuItemReferPatient.setVisible(gaav.isReadMenuToolRefer());
        jMenuItemLabreferin.setVisible(gaav.isReadMenuToolLabReferIn());
        jMenuItemLabreferOut.setVisible(gaav.isReadMenuToolLabReferOut());
        jMenuItemBorrowFilmXray.setVisible(gaav.isReadMenuToolBorrowFilmXray());
        jMenuItemBorrowOpdCard.setVisible(gaav.isReadMenuToolBorrowOpdCard());
           //tong 19/04/2549,16:55
        if(Gutil.isSelected(this.theHC.theLookupControl.readOption().auto_diag_icd10))
        {
            jMenuItemShowDxDoctor.setVisible(gaav.isReadMenuToolShowDxDoctorClinic());
        }
//ผู้ป่วยทั่วไป
        jMenuItemWaitDoctorDischarge.setVisible(gaav.isReadMenuOpdRemainVisit());
        jMenuItemDropVisit.setVisible(gaav.isReadMenuOpdDropVisit());
        jMenuItemAccident.setVisible(gaav.isReadMenuOpdAccident());
        jMenuItemDeath.setVisible(gaav.isReadMenuOpdDeath());
        jMenuItemChronic.setVisible(gaav.isReadMenuOpdChronic());
        jMenuItemServeil.setVisible(gaav.isReadMenuOpdSurveil());
// ผู้ป่วยใน
        jMenuItemAdmit.setVisible(gaav.isReadMenuIpdAdmit());
        jMenuItemReverseAdmit.setVisible(gaav.isReadMenuIpdReverseAdmit());
        jMenuItemObserve.setVisible(gaav.isReadMenuIpdObserv());
        jMenuItemReverseObserve.setVisible(gaav.isReadMenuIpdCancelObserv());
        jMenuItemSendBackWard.setVisible(gaav.isReadMenuIpdSendBackWard());
        jMenuItemHistoryOrderContinue.setVisible(gaav.isReadMenuIpdHistoryOrderContinue());
        jMenuItemDischargeIPD.setVisible(gaav.isReadMenuIpdDischarge());
// การสั่งตรวจรักษา
        jMenuItemReceiveDrug.setVisible(gaav.isReadMenuOrderReturnDrug());
        jMenuItemOrderSet.setVisible(gaav.isReadMenuOrderSet());
        jMenuItemOrderListByVn.setVisible(gaav.isReadMenuOrderLastVisit());
        jMenuItemOrderListByDate.setVisible(gaav.isReadMenuOrderLastDate());
        jMenuItemOrderCotinueDrug.setVisible(gaav.isReadMenuOrderContinue());
//พิมพ์
        jMenuPrintVisitSlip.setVisible(gaav.isReadMenuPrintVisitSlip());
        jMenuPrintSummary.setVisible(gaav.isReadMenuPrintSummary());
        jMenuReportSumGroupItem.setVisible(gaav.isReadMenuPrintBillByGroupOrder());
        jMenuReportSumGroupBilling.setVisible(gaav.isReadMenuPrintBillByGroupBilling());
        jMenuReportSumItem.setVisible(gaav.isReadMenuPrintBillOrder());
        jMenuReportSum16Group.setVisible(gaav.isReadMenuPrintItemStandard());
        jMenuSelectDrugList.setVisible(gaav.isReadMenuPrintDrugList());
        jMenuOpdCard.setVisible(gaav.isReadMenuPrintOpdCard());
        jMenuItemPrintDrugSticker.setVisible(gaav.isReadMenuPrintSticker());
        jMenuItemPrintIndex.setVisible(gaav.isReadMenuPrintIndex());
        jMenuItemPrintXNIndex.setVisible(gaav.isReadMenuPrintXnIndex());
        jMenuItemLabResult.setVisible(gaav.isReadMenuPrintLabResult());
        jMenuPrintIPDNameCard.setVisible(gaav.isReadMenuPrintIpdNameCard());        
// ย้อนกลับ
        jMenuItemReverseDoctor.setVisible(gaav.isReadMenuReverseDoctor());
        jMenuItemReverseFinancial.setVisible(gaav.isReadMenuReverseFinancial());
        
        GuiLang.setLanguage(theHP.theJTabbedPane);
        GuiLang.setLanguage(theHP.aPanelListVisit);
        if(theHP.aPanelDiagIcd!=null)
            GuiLang.setLanguage(theHP.aPanelDiagIcd.jTabbedPane);
    }

    public void notifyDeletePatientPayment(String str, int status) {
        setStatus(str,status); 
    }
    
    public void notifyDeleteFilmXray(String str, int status) {
        setStatus(str,status); 
    }
    
    public void notifyDeleteLabOrder(String str, int status) {
        setStatus(str,status); 
    }
    
    public void notifyDeleteLabResult(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifyDeleteXrayPosition(String str, int status) {
        setStatus(str,status); 
    }
    
    public void notifyManagePatientLabReferIn(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifySaveFilmXray(String str, int status) {
        setStatus(str,status); 
    }
    
    public void notifySaveLabResult(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifySaveResultXray(String str, int status) {
        setStatus(str,status); 
    }
    
    public void notifySaveXrayPosition(String str, int status) {
        setStatus(str,status); 
    }
    
    public void notifyXrayReportComplete(String str, int status) {
        theHP.theJTabbedPane.setSelectedIndex(0);
        setStatus(str,status); 
    }
    
    public void notifyDeleteParticipateOr(String str, int status) {
        setStatus(str,status); 
    }
    
    public void notifyCancelOrderItem(String str, int status) {
        setStatus(str,status); 
    }
    
    public void notifyCheckAutoOrder(String str, int status) {
        setStatus(str,status); 
    }
    
    public void notifyContinueOrderItem(String str, int status) {
        setStatus(str,status); 
    }
    
    public void notifyDispenseOrderItem(String str, int status) {
        setStatus(str,status); 
    }
    
    public void notifyDoctorOffDrug(String str, int status) {
        setStatus(str,status); 
    }
    
    public void notifyExecuteOrderItem(String str, int status) {
        setStatus(str,status); 
    }
    
    public void notifyReferOutLab(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifySaveOrderItem(String str, int status) {
        setStatus(str,status); 
    }
    
    public void notifySaveOrderItemInLab(String str, int status) {
        setStatus(str,status); 
    }
    
    public void notifyReceiveReturnDrug(String str, int status) {
        setStatus(str,status); 
    }
    
    public void notifyVerifyOrderItem(String str, int status) {
        setStatus(str,status); 
    }



    public void notifyAddPrimarySymptom(String str, int status) {
        setStatus(str,status); 
    }

    public void notifyBillingInvoice(String str, int status) {
        setStatus(str,status); 
    }

    public void notifyDeleteVisitPayment(String str, int status) {
        setStatus(str,status); 
    }
    
    public void notifyDeletePatient(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifyReadPatient(String str, int status) {
        setVisit(theHO.theVisit);
        //showDefaultTab(theHO.theEmployee.default_tab);
        setStatus(str,status);
    }
    public void notifyReadFamily(String str, int status) {
        setVisit(theHO.theVisit);
        setStatus(str,status);
    }   
    public void notifySavePatient(String str, int status) {
        setVisit(theHO.theVisit);
        setStatus(str,status);
    }

    public void notifySendVisitBackWard(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifyRemainDoctorDischarge(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifySaveReturnDrug(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifyReportResultLab(String str, int status) {
        //ถ้าไม่มีรายการแลบใดๆ เหลืออยู่อีกแล้ว
        setVisit(theHO.theVisit); 
        setStatus(str,status); 
    }
    
    public void notifyDeleteResultXray(String str, int status) {
    }
    
    public void notifySaveOrderRequest(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifySaveAppointment(String str, int status) {
        setStatus(str,status);
    }
    
    public void notifyAddLabReferOut(String str, int status) {
    }
    
    public void notifyAddLabReferIn(String str, int status) {
    }
    
    public void notifyReverseAdmit(String str, int status) {
        setStatus(str,status);
        jMenuItemReverseAdmit.setEnabled(false);
        setVisit(theHO.theVisit);
    }
    
    public void notifyResetPatient(String str, int status) {
        setVisit(theHO.theVisit,false);       
        setStatus(str,status);
    }
    
    public void notifySaveRemainLabResult(String str, int status) {
        //ถ้าไม่มีรายการแลบใดๆ เหลืออยู่อีกแล้ว
        setStatus(str,status); 
        if(theHO.theVisit==null)
            theHP.theJTabbedPane.setSelectedIndex(0);
    }
    
    public void notifySendResultLab(String str, int status) {
        setStatus(str,status); 
        setVisit(theHO.theVisit);  
        //henbe:21/03/2549 //ถ้าไม่มีรายการแลบใดๆ เหลืออยู่อีกแล้ว
    }
    
    public void notifyDeleteQueueLab(String str, int status) {
        setStatus(str,status); 
        setVisit(theHO.theVisit);  
    }

    public void notifySaveBorrowFilmXray(String str, int status) {
    }
    
}
