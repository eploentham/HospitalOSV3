/*
 * PanelOrder.java
 *
 * Created on 29 กันยายน 2546, 9:32 น.
 */
package com.hosv3.gui.panel.transaction;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import com.hosv3.object.*;
import com.hosv3.subject.*;
import com.hosv3.control.*;
import com.hosv3.utility.*;
import com.hosv3.utility.connection.*;
import com.hosv3.gui.dialog.*;
import com.hosv3.gui.component.*;
import com.hosv3.usecase.transaction.*;
import com.hosv3.object.printobject.*;

import com.hospital_os.object.*;
import com.hospital_os.utility.CelRendererContinue;
import com.hospital_os.utility.CellRendererCheckBox;
import com.hospital_os.utility.ComboFix;
import com.hospital_os.utility.CelRenderer;
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.TaBleModel;
import com.hosv3.utility.Constant;


/**
 *
 * @author  Surachai Thowong
 * @modify amp
 * @modify pu
 *
 */
public class PanelOrder extends javax.swing.JPanel implements ManageVisitResp
        , ManagePatientResp, ManageOrderResp, ManageBillingResp, ManagePrintResp
        ,ManageVitalResp ,ManageLabXrayResp, ExecuteControlInf
{
    HosObject theHO;
    HosControl theHC;
    HosSubject theHS;
    UpdateStatus theUS;
    HosDialog theHD;
    
    public final static String CARD_BLANK = "CARD_BLANK";
    public final static String CARD_DRUG = "CARD_DRUG";
    static final long serialVersionUID=0;
    public final static String CARD_SERVICE = "CARD_SERVICE";
    public final static String CARD_SUPPLY = "CARD_SUPPLY";
    public final static String CARD_LAB = "CARD_LAB";
    public boolean show_cancel_order = true;

    String[] col_jTableOrderList = {"฿"
                                ,"NAME"
                                ,"qty"
                                ,"Price"
                                ,"Status"
                                ,"ขอตรวจ"};
    //String[] col = {"ชื่อ","ชื่อกลุ่ม"};
    String[] col_jTableItemList = {"NAME",
                                "Groupname"};
        /*ผู้ป่วยใน*/
    String[] col_jTableOrderList_ipd = {"฿"
        ,"NAME"
        ,"qty"
        ,"Price"
        ,"Status"
        ,"ขอตรวจ"
        ,"Ongoing"
        ,"บ้าน"};
        /*ผู้ป่วยนอก */
    String[] col_jTableOrderList_opd = {"฿"
        ,"NAME"
        ,"qty"
        ,"Price"
        ,"Status"
        ,"ขอตรวจ"};

    CelRenderer cellRenderer = new CelRenderer();
    CelRendererContinue cellRendererContinue = new CelRendererContinue();
    CellRendererCheckBox cellRendererCheckBox = new CellRendererCheckBox();
    CellRendererDayOrder cellRendererDayOrder;
    CellRendererItem cellRendererItem;
    Patient thePatient = new Patient();
    /** ใช้ในการตรวจสอบการ Double-Click */
    long firstClickTime = 0;
    /*วิธีการใช้ยา*/
    Vector theOrderItemV;
    Vector theItemV;
    QueueXray theQueueXray;
    QueueLab theQueueLab;
    QueueDespense theQueueDespense;
    OrderLabSecret theOrderLabSecret;

    DialogChooseOrderItemPrint theDialogChooseOrderItemPrint = null;
    DialogShowLabResult dslr = null;
    ComboFix theComboFix = null;
    ComboFix theEmployeeComboFix = null;
    /** Creates new form PanelOrder */
    CardLayout layoutOrder;
    CardLayout layoutDrug;
    /*ใช้ในการพิมพ์*/
    private int preview = PrintControl.MODE_PREVIEW; //ใช้ในการพิมพ์ข้อมูลเมื่อต้องให้แสดงข้อมูลก่อนพิมพ์
    private int nopreview = PrintControl.MODE_PRINT; //ใช้ในการพิมพ์ข้อมูลเมื่อไม่ต้องการให้แสดงข้อมูลก่อนพิมพ์
    int selectedrows[] = {}; //เก็บ index ของ row ที่ถูกเลือกจากรายการตรวจรักษา pu 19/07/2549*/
    private Vector vRowExecute; //amp:29/08/2549: เก็บ row ที่เพิ่งจะถูก Execute

    private Visit theVisit;

    private DiagDoctorClinic ddc;

    private int print_sticker=0;

    public PanelOrder()
    {
        initComponents(); 
        setLanguage(null);
        
        this.jTableItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dateComboBoxStart.setEnabled(false);
        dateComboBoxEnd.setEnabled(false);
        jSplitPane2.setDividerLocation(575);
    }
    public void setControl(HosControl hc, UpdateStatus us)
    {
        theHC = hc;
        theHO = hc.theHO;
        theHS = hc.theHS;
        theUS = us;
        pDOrder1.setControl(hc,us);        
        cellRendererDayOrder = new CellRendererDayOrder(true);
        cellRendererItem = new CellRendererItem(true);
        cellRendererDayOrder.setFont(this.defaultFont1);
        cellRendererItem.setFont(this.defaultFont1);
        
        hc.theHS.theVisitSubject.attachManageVisit(this);
        hc.theHS.thePatientSubject.attachManagePatient(this);
        hc.theHS.theOrderSubject.attachManageOrder(this);
        hc.theHS.theBillingSubject.attachManageBilling(this);
        hc.theHS.thePrintSubject.attachManagePrint(this);
        hc.theHS.theResultSubject.attachManageLab(this);
        hc.theHS.theResultSubject.attachManageXray(this);
        hc.theHS.theVitalSubject.attachManageVital(this);
        initComboBox();
        pDOrder1.setOrderItem(null);
        pDOrder1.setItem(null);
        setVisit(null);
        initAuthen(theHO.theEmployee);
        initPopup();
    }
    public void initComboBox()
    {
        ComboboxModel.initComboBox(jComboBoxGroup,theHC.theLookupControl.listCategoryGroupItem());
        Vector vc = theHC.theLookupControl.listCategoryGroup();
        ComboboxModel.initComboBox(jComboBoxGroup1,vc);
        ComboFix alltype = new ComboFix();
        alltype.code = "";
        alltype.name = "แสดงทั้งหมด";
        jComboBoxGroup1.insertItemAt((Object)alltype,0);
        jComboBoxGroup1.setSelectedIndex(0);
        ////////////////////////////////////////////////////////////////
        String command = theHC.theLookupControl.readOption().b3_command;
        String ttt = theHC.theLookupControl.readOption().b3_description;
        String icon = theHC.theLookupControl.readOption().b3_icon;
        this.jButtonB3.setVisible(!command.equals(""));
        this.jButtonB3.setToolTipText(ttt);
        if(!icon.equals("")) {
            this.jButtonB3.setIcon(new javax.swing.ImageIcon("icon/"+icon));
            this.jButtonB3.setText("");
        }
        ////////////////////////////////////////////////////////////////
    }
    public void initAuthen(Employee e)
    {
        GActionAuthV gaav = theHO.theGActionAuthV;
        jButtonCal.setVisible(gaav.isReadPOrderButtonCalBill());
        jButtonVerify.setVisible(gaav.isReadPOrderButtonVerify());
        jButtonExecute.setVisible(gaav.isReadPOrderButtonExecute());
        jButtonDispense.setVisible(gaav.isReadPOrderButtonDispense());
        jButtonCancel.setVisible(gaav.isReadPOrderButtonCancel());
        jButtonDrugRx.setVisible(gaav.isReadPOrderButtonPDrugRx());
        jButtonSticker.setVisible(gaav.isReadPOrderButtonPSticker());
        jButtonBarcode.setVisible(gaav.isReadMenuPrinterSticker());
    }
    public void setDialog(HosDialog hd)
    {
        theHD = hd;
    }
    private void initPopup()
    {
        GActionAuthV gaav = theHO.theGActionAuthV;
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu1.setInvoker(this.jTableOrderList);
        javax.swing.JSeparator jSeparator1 = new javax.swing.JSeparator();
        if(gaav.isReadPOrderButtonVerify())
        {
            jMenuItemVerify = new javax.swing.JMenuItem();
            jMenuItemVerify.setAccelerator(javax.swing.KeyStroke.getKeyStroke('V'));
            jMenuItemVerify.setText(Constant.getTextBundle("ยืนยัน"));
            jMenuItemVerify.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    jMenuItemVerifyActionPerformed(evt);
                }
            });
            jPopupMenu1.add(jMenuItemVerify);
        }
        if(gaav.isReadPOrderButtonCancel())
        {
            jMenuItemCancel = new javax.swing.JMenuItem();
            jMenuItemCancel.setAccelerator(javax.swing.KeyStroke.getKeyStroke("DELETE"));
            jMenuItemCancel.setText(Constant.getTextBundle("ยกเลิก"));
            jMenuItemCancel.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    jMenuItemCancelActionPerformed(evt);
                }
            });
            jPopupMenu1.add(jMenuItemCancel);
        }
        if(gaav.isReadPOrderButtonExecute())
        {
            jMenuItemExecute = new javax.swing.JMenuItem();
            jMenuItemExecute.setAccelerator(javax.swing.KeyStroke.getKeyStroke('E'));
            jMenuItemExecute.setText(Constant.getTextBundle("ดำเนินการ"));
            jMenuItemExecute.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    jMenuItemExecuteActionPerformed(evt);
                }
            });
            jPopupMenu1.add(jMenuItemExecute);
        }
        if(gaav.isReadPOrderButtonDispense())
        {
            jMenuItemDispense = new javax.swing.JMenuItem();
            jMenuItemDispense.setAccelerator(javax.swing.KeyStroke.getKeyStroke('D'));
            jMenuItemDispense.setText(Constant.getTextBundle("จ่าย"));
            jMenuItemDispense.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    jMenuItemDispenseActionPerformed(evt);
                }
            });
            jPopupMenu1.add(jMenuItemDispense);
        }
        jPopupMenu1.add(jSeparator1);
    }

    private void jMenuItemVerifyActionPerformed(java.awt.event.ActionEvent evt)
    {
        jButtonVerifyActionPerformed(null);
    }
    private void jMenuItemExecuteActionPerformed(java.awt.event.ActionEvent evt)
    {
        jButtonExecuteActionPerformed(null);
    }
    private void jMenuItemDispenseActionPerformed(java.awt.event.ActionEvent evt)
    {
        jButtonDispenseActionPerformed(null);
    }
    private void jMenuItemCancelActionPerformed(java.awt.event.ActionEvent evt)
    {
        jButtonCancelActionPerformed(null);
    }
/** This method is called from within the constructor to
 * initialize the form.
 * WARNING: Do NOT modify this code. The content of this method is
 * always regenerated by the Form Editor.
 */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroupaKeySearch = new javax.swing.ButtonGroup();
        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jSplitPane2 = new javax.swing.JSplitPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanelSearchItem = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableItemList = new com.hosv3.gui.component.HJTableSort();
        jPanel3 = new javax.swing.JPanel();
        jButtonDrugSet = new javax.swing.JButton();
        jButtonDrugOld = new javax.swing.JButton();
        jRadioButtonBegin = new javax.swing.JRadioButton();
        jRadioButtonConsist = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        jComboBoxGroup = new javax.swing.JComboBox();
        jCheckBoxGroup = new javax.swing.JCheckBox();
        jPanel10 = new javax.swing.JPanel();
        jTextFieldKeyword = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jPanelOrderList = new javax.swing.JPanel();
        jPanelCondition = new javax.swing.JPanel();
        dateComboBoxEnd = new com.hospital_os.utility.DateComboBox();
        dateComboBoxStart = new com.hospital_os.utility.DateComboBox();
        jLabelTo = new javax.swing.JLabel();
        jCheckBoxPeriod = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxGroup1 = new javax.swing.JComboBox();
        jButtonRefresh = new javax.swing.JButton();
        jScrollPaneOrderList = new javax.swing.JScrollPane();
        jTableOrderList = new com.hosv3.gui.component.HJTableSort();
        jPanelRightSide = new javax.swing.JPanel();
        jButtonB3 = new javax.swing.JButton();
        jCheckBoxContinue = new javax.swing.JToggleButton();
        jCheckBoxShowCancel = new javax.swing.JToggleButton();
        jButtonBarcode = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanelAction = new javax.swing.JPanel();
        jButtonVerify = new javax.swing.JButton();
        jButtonExecute = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jButtonDispense = new javax.swing.JButton();
        jToggleButtonSelectAll = new javax.swing.JToggleButton();
        jButtonCal = new javax.swing.JButton();
        jButtonDrugRx = new javax.swing.JButton();
        jButtonSticker = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaNote = new javax.swing.JTextArea();
        jButtonBilling = new javax.swing.JButton();
        pDOrder1 = new com.hosv3.gui.panel.detail.PDOrder();

        setLayout(new java.awt.GridBagLayout());

        jSplitPane2.setDividerLocation(275);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setOneTouchExpandable(true);

        jSplitPane1.setBorder(null);
        jSplitPane1.setOneTouchExpandable(true);

        jPanelSearchItem.setLayout(new java.awt.GridBagLayout());

        jTableItemList.setFont(defaultFont1);
        jTableItemList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableItemListMouseReleased(evt);
            }
        });
        jTableItemList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableItemListKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableItemListKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableItemList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 3);
        jPanelSearchItem.add(jScrollPane1, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jButtonDrugSet.setFont(defaultFont1);
        jButtonDrugSet.setText("ชุดยา");
        jButtonDrugSet.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButtonDrugSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDrugSetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel3.add(jButtonDrugSet, gridBagConstraints);

        jButtonDrugOld.setFont(defaultFont1);
        jButtonDrugOld.setText("ยาเดิม");
        jButtonDrugOld.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButtonDrugOld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDrugOldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel3.add(jButtonDrugOld, gridBagConstraints);

        buttonGroupaKeySearch.add(jRadioButtonBegin);
        jRadioButtonBegin.setFont(defaultFont1);
        jRadioButtonBegin.setText("นำหน้า");
        jRadioButtonBegin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonBeginActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(jRadioButtonBegin, gridBagConstraints);

        buttonGroupaKeySearch.add(jRadioButtonConsist);
        jRadioButtonConsist.setFont(defaultFont1);
        jRadioButtonConsist.setSelected(true);
        jRadioButtonConsist.setText("ประกอบด้วย");
        jRadioButtonConsist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonConsistActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jRadioButtonConsist, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 3);
        jPanelSearchItem.add(jPanel3, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jComboBoxGroup.setFont(defaultFont1);
        jComboBoxGroup.setEnabled(false);
        jComboBoxGroup.setMinimumSize(new java.awt.Dimension(26, 24));
        jComboBoxGroup.setPreferredSize(new java.awt.Dimension(26, 24));
        jComboBoxGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxGroupActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel8.add(jComboBoxGroup, gridBagConstraints);

        jCheckBoxGroup.setFont(defaultFont1);
        jCheckBoxGroup.setText("กลุ่ม");
        jCheckBoxGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxGroupActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel8.add(jCheckBoxGroup, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 3);
        jPanelSearchItem.add(jPanel8, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jTextFieldKeyword.setFont(defaultFont1);
        jTextFieldKeyword.setMaximumSize(new java.awt.Dimension(100, 21));
        jTextFieldKeyword.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldKeyword.setPreferredSize(new java.awt.Dimension(100, 21));
        jTextFieldKeyword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldKeywordMouseReleased(evt);
            }
        });
        jTextFieldKeyword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldKeywordActionPerformed(evt);
            }
        });
        jTextFieldKeyword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldKeywordKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel10.add(jTextFieldKeyword, gridBagConstraints);

        jButtonSearch.setFont(defaultFont1);
        jButtonSearch.setText("ค้นหา");
        jButtonSearch.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel10.add(jButtonSearch, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 0, 3);
        jPanelSearchItem.add(jPanel10, gridBagConstraints);

        jSplitPane1.setLeftComponent(jPanelSearchItem);

        jPanelOrderList.setMaximumSize(new java.awt.Dimension(500, 2147483647));
        jPanelOrderList.setLayout(new java.awt.GridBagLayout());

        jPanelCondition.setLayout(new java.awt.GridBagLayout());

        dateComboBoxEnd.setEnabled(false);
        dateComboBoxEnd.setFont(defaultFont1);
        dateComboBoxEnd.setMinimumSize(new java.awt.Dimension(93, 24));
        dateComboBoxEnd.setPreferredSize(new java.awt.Dimension(93, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 2);
        jPanelCondition.add(dateComboBoxEnd, gridBagConstraints);

        dateComboBoxStart.setEnabled(false);
        dateComboBoxStart.setFont(defaultFont1);
        dateComboBoxStart.setMinimumSize(new java.awt.Dimension(93, 24));
        dateComboBoxStart.setPreferredSize(new java.awt.Dimension(93, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 3);
        jPanelCondition.add(dateComboBoxStart, gridBagConstraints);

        jLabelTo.setFont(defaultFont1);
        jLabelTo.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 4);
        jPanelCondition.add(jLabelTo, gridBagConstraints);

        jCheckBoxPeriod.setFont(defaultFont1);
        jCheckBoxPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPeriodActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelCondition.add(jCheckBoxPeriod, gridBagConstraints);

        jLabel4.setFont(defaultFont1);
        jLabel4.setText("กลุ่ม");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelCondition.add(jLabel4, gridBagConstraints);

        jComboBoxGroup1.setFont(defaultFont1);
        jComboBoxGroup1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxGroup1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelCondition.add(jComboBoxGroup1, gridBagConstraints);

        jButtonRefresh.setFont(defaultFont1);
        jButtonRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/Refresh.png"))); // NOI18N
        jButtonRefresh.setToolTipText("เรียกดูข้อมูล");
        jButtonRefresh.setMaximumSize(new java.awt.Dimension(62, 24));
        jButtonRefresh.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonRefresh.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanelCondition.add(jButtonRefresh, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelOrderList.add(jPanelCondition, gridBagConstraints);

        jTableOrderList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableOrderListMouseReleased(evt);
            }
        });
        jTableOrderList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableOrderListKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableOrderListKeyReleased(evt);
            }
        });
        jScrollPaneOrderList.setViewportView(jTableOrderList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 0);
        jPanelOrderList.add(jScrollPaneOrderList, gridBagConstraints);

        jPanelRightSide.setLayout(new java.awt.GridBagLayout());

        jButtonB3.setFont(defaultFont1);
        jButtonB3.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonB3.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonB3.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonB3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonB3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelRightSide.add(jButtonB3, gridBagConstraints);

        jCheckBoxContinue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/order_continue.gif"))); // NOI18N
        jCheckBoxContinue.setToolTipText("ดูรายการยาต่อเนื่อง");
        jCheckBoxContinue.setMaximumSize(new java.awt.Dimension(26, 26));
        jCheckBoxContinue.setMinimumSize(new java.awt.Dimension(26, 26));
        jCheckBoxContinue.setPreferredSize(new java.awt.Dimension(26, 26));
        jCheckBoxContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxContinueActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelRightSide.add(jCheckBoxContinue, gridBagConstraints);

        jCheckBoxShowCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/cancel.gif"))); // NOI18N
        jCheckBoxShowCancel.setToolTipText("ดูรายการที่ยกเลิก");
        jCheckBoxShowCancel.setMaximumSize(new java.awt.Dimension(26, 26));
        jCheckBoxShowCancel.setMinimumSize(new java.awt.Dimension(26, 26));
        jCheckBoxShowCancel.setPreferredSize(new java.awt.Dimension(26, 26));
        jCheckBoxShowCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxShowCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelRightSide.add(jCheckBoxShowCancel, gridBagConstraints);

        jButtonBarcode.setFont(defaultFont1);
        jButtonBarcode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/barcode.png"))); // NOI18N
        jButtonBarcode.setToolTipText("พิมพ์ Sticker ติด Tube");
        jButtonBarcode.setMaximumSize(new java.awt.Dimension(62, 24));
        jButtonBarcode.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonBarcode.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBarcodeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        jPanelRightSide.add(jButtonBarcode, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        jPanelRightSide.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 1, 0, 5);
        jPanelOrderList.add(jPanelRightSide, gridBagConstraints);

        jSplitPane1.setRightComponent(jPanelOrderList);

        jSplitPane2.setLeftComponent(jSplitPane1);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanelAction.setLayout(new java.awt.GridBagLayout());

        jButtonVerify.setFont(defaultFont1);
        jButtonVerify.setText("ยืนยัน");
        jButtonVerify.setToolTipText("เลือกรายการแล้วกดปุ่ม v");
        jButtonVerify.setMargin(new java.awt.Insets(1, 3, 1, 3));
        jButtonVerify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerifyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelAction.add(jButtonVerify, gridBagConstraints);

        jButtonExecute.setFont(defaultFont1);
        jButtonExecute.setText("ดำเนินการ");
        jButtonExecute.setToolTipText("เลือกรายการแล้วกดปุ่ม e");
        jButtonExecute.setMargin(new java.awt.Insets(1, 3, 1, 3));
        jButtonExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExecuteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 0);
        jPanelAction.add(jButtonExecute, gridBagConstraints);

        jButtonCancel.setFont(defaultFont1);
        jButtonCancel.setText("ยกเลิก");
        jButtonCancel.setToolTipText("เลือกรายการแล้วกดปุ่ม Delete");
        jButtonCancel.setMargin(new java.awt.Insets(1, 3, 1, 3));
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 0);
        jPanelAction.add(jButtonCancel, gridBagConstraints);

        jButtonDispense.setFont(defaultFont1);
        jButtonDispense.setText("จ่าย");
        jButtonDispense.setToolTipText("เลือกรายการแล้วกดปุ่ม d");
        jButtonDispense.setMargin(new java.awt.Insets(1, 3, 1, 3));
        jButtonDispense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDispenseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 0);
        jPanelAction.add(jButtonDispense, gridBagConstraints);

        jToggleButtonSelectAll.setFont(defaultFont1);
        jToggleButtonSelectAll.setText("ทั้งหมด");
        jToggleButtonSelectAll.setToolTipText("เลือกรายการแล้วกดปุ่ม Ctrl+a");
        jToggleButtonSelectAll.setMargin(new java.awt.Insets(1, 3, 1, 3));
        jToggleButtonSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonSelectAllActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanelAction.add(jToggleButtonSelectAll, gridBagConstraints);

        jButtonCal.setFont(defaultFont1);
        jButtonCal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/mo_ney.gif"))); // NOI18N
        jButtonCal.setToolTipText("คิดเงิน เลือกรายการแล้วกดปุ่ม c");
        jButtonCal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        jPanelAction.add(jButtonCal, gridBagConstraints);

        jButtonDrugRx.setFont(defaultFont1);
        jButtonDrugRx.setText("ใบสั่งยา");
        jButtonDrugRx.setToolTipText("เลือกรายการแล้วกดปุ่ม d");
        jButtonDrugRx.setMargin(new java.awt.Insets(1, 3, 1, 3));
        jButtonDrugRx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDrugRxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelAction.add(jButtonDrugRx, gridBagConstraints);

        jButtonSticker.setFont(defaultFont1);
        jButtonSticker.setText("สติ้กเกอร์");
        jButtonSticker.setToolTipText("เลือกรายการแล้วกดปุ่ม d");
        jButtonSticker.setMargin(new java.awt.Insets(1, 3, 1, 3));
        jButtonSticker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStickerActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 0);
        jPanelAction.add(jButtonSticker, gridBagConstraints);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextAreaNote.setFont(defaultFont1);
        jTextAreaNote.setLineWrap(true);
        jTextAreaNote.setWrapStyleWord(true);
        jTextAreaNote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextAreaNoteKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTextAreaNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanelAction.add(jScrollPane2, gridBagConstraints);

        jButtonBilling.setFont(defaultFont1);
        jButtonBilling.setText("ใบเสร็จ");
        jButtonBilling.setToolTipText("เลือกรายการแล้วกดปุ่ม d");
        jButtonBilling.setMargin(new java.awt.Insets(1, 3, 1, 3));
        jButtonBilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBillingActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 0);
        jPanelAction.add(jButtonBilling, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 5);
        jPanel2.add(jPanelAction, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(pDOrder1, gridBagConstraints);

        jSplitPane2.setRightComponent(jPanel2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jSplitPane2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxContinueActionPerformed
        continueDrug(false);
    }//GEN-LAST:event_jCheckBoxContinueActionPerformed

    private void jCheckBoxShowCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxShowCancelActionPerformed
        jButtonRefreshActionPerformed(null);
    }//GEN-LAST:event_jCheckBoxShowCancelActionPerformed

    private void jTextFieldKeywordMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldKeywordMouseReleased
        jTextFieldKeyword.setSelectionStart(0);
        jTextFieldKeyword.setSelectionEnd(jTextFieldKeyword.getText().length());

    }//GEN-LAST:event_jTextFieldKeywordMouseReleased

    private void jButtonBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBillingActionPerformed
        if(!Gutil.checkModulePrinting())
        {    
            theUS.setStatus("ไม่มี Module Printing",UpdateStatus.WARNING);
             return ;
        }
        Vector vOrder = getOrderItemV();
        theHC.thePrintControl.printSumByBillingGroupNew(preview, vOrder);
    }//GEN-LAST:event_jButtonBillingActionPerformed
    
    private void jTextAreaNoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaNoteKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_TAB)
            jToggleButtonSelectAll.requestFocus();
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            theHC.theVitalControl.saveDxNote(jTextAreaNote.getText());
    }//GEN-LAST:event_jTextAreaNoteKeyReleased
    
    private void jButtonStickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStickerActionPerformed
        int[] rows = this.jTableOrderList.getSelectedRows();

        Vector vOr = getOrderItemV();
        if(vOr.isEmpty())
        {
            Item item = getItem();
            if(item!=null)
                theHC.thePrintControl.printItemSticker(item, getOrderItemDrug());
        }
        else
            theHC.thePrintControl.printDrugSticker(vOr,null);                    
    }//GEN-LAST:event_jButtonStickerActionPerformed
    
    private void jButtonDrugRxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDrugRxActionPerformed
        if(theHO.theVisit == null)
        {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return;
        }
        if(!Gutil.checkModulePrinting())
        {
            theUS.setStatus("ไม่สามารถพิมพ์ได้ ยังขาด Module Printing",UpdateStatus.WARNING);
            return ;
        }
        int[] rows = this.jTableOrderList.getSelectedRows();
        // เพิ่มการเก็บข้อมูลการพิมพ์แก้ไขปัญหาพิมพ์ใบสั่งยาจากปุ่มพิมพ์ใบสั่งยาแล้วรายการที่เลือกมีผู้ยืนยันมากกว่า 1 คน ให้ไปดึงมาพิมพ์ด้วย 
        // โดยจะพิมพ์ชื่อแพทย์คนล่าสุดให้ sumo 25/7/2549
        PrintSelectDrugList pd = new PrintSelectDrugList();
        pd.typePrint = 1;
        pd.employeeid = "";
        pd.nameDoctor = "";
        pd.selectdrug = true;
        pd.selectlab = true;
        pd.selectservice = true;
        pd.selectsupply = true;
        pd.selectxray = true;
        theHC.thePrintControl.printSelectDrugList(pd,nopreview,theOrderItemV,rows);
    }//GEN-LAST:event_jButtonDrugRxActionPerformed
    
    private void jButtonB3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonB3ActionPerformed
        String command = theHC.theLookupControl.readOption().b3_command;
        try{
            Process proc = Runtime.getRuntime().exec(command);
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
        }
    }//GEN-LAST:event_jButtonB3ActionPerformed
    
    private void jCheckBoxPeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPeriodActionPerformed
        if(jCheckBoxPeriod.isSelected())
        {
            dateComboBoxEnd.setEnabled(true);
            dateComboBoxStart.setEnabled(true);
        }
        else
        {
            dateComboBoxEnd.setEnabled(false);
            dateComboBoxStart.setEnabled(false);
        }
        jButtonRefreshActionPerformed(null);
    }//GEN-LAST:event_jCheckBoxPeriodActionPerformed
                
    private void jTableOrderListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableOrderListKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            int row = jTableOrderList.getSelectedRow();
            if(row == 0)
                jTableOrderList.setRowSelectionInterval(0,0);
            else
                jTableOrderList.setRowSelectionInterval(row-1 ,row-1);
            this.pDOrder1.requestFocus();
        }
    }//GEN-LAST:event_jTableOrderListKeyPressed
    
    private void jTableItemListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableItemListKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            int row = jTableItemList.getSelectedRow();
            if(row == 0) row = 1;
            jTableItemList.setRowSelectionInterval(row-1 ,row-1);
            pDOrder1.requestFocus();
        }
    }//GEN-LAST:event_jTableItemListKeyPressed
            
    private void jToggleButtonSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonSelectAllActionPerformed
        //ไม่มีรายการตรวจรักษาเลย
        if(jToggleButtonSelectAll.isSelected())
        {
            jTableOrderList.selectAll();
            jTableOrderList.requestFocus();
        }
        else
            jTableOrderList.clearSelection();
        pDOrder1.setOrderItem(null);
        //layoutOrder.show(jPanelOrderProperty,PanelOrder.CARD_BLANK);
        jTableOrderListMouseReleased(null);
    }//GEN-LAST:event_jToggleButtonSelectAllActionPerformed
            
    private void jComboBoxGroup1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxGroup1ActionPerformed
        jButtonRefreshActionPerformed(null);
    }//GEN-LAST:event_jComboBoxGroup1ActionPerformed
    
    private void jRadioButtonBeginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonBeginActionPerformed
        jButtonSearchActionPerformed(null);
    }//GEN-LAST:event_jRadioButtonBeginActionPerformed
    
    private void jRadioButtonConsistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonConsistActionPerformed
        jButtonSearchActionPerformed(null);
    }//GEN-LAST:event_jRadioButtonConsistActionPerformed
    
    private void jButtonDrugOldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDrugOldActionPerformed
        if(theHO.theVisit.visit_type.equals(VisitType.IPD))
            theHD.showDialogOrderOldVisitByDate(theHO.thePatient,theHO.theVisit);
        else
            theHD.showDialogOrderOldVisitByVn(theHO.thePatient,theHO.theVisit);
    }//GEN-LAST:event_jButtonDrugOldActionPerformed
    
    private void jButtonDrugSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDrugSetActionPerformed
        theHD.showDialogOrderSet(theHO.theVisit);
    }//GEN-LAST:event_jButtonDrugSetActionPerformed
    
    private void jCheckBoxGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxGroupActionPerformed
        jComboBoxGroup.setEnabled(jCheckBoxGroup.isSelected());
    }//GEN-LAST:event_jCheckBoxGroupActionPerformed
        
    private void jTableOrderListKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableOrderListKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_SPACE)
        {
            OrderItem theOrderItem = this.pDOrder1.getOrderItem();
            if(theOrderItem.isXray()
            && (theOrderItem.status.equals("2") ||  theOrderItem.status.equals("4")))
            {
                theHD.showDialogResultXray(theOrderItem);
                return;
            }
            if(theOrderItem.isLab())
            {
                if(theOrderItem.status.equals("2") || theOrderItem.status.equals("4"))
                {
                    theHD.showDialogShowLabResult(theOrderItem);
                    return;
                }
            }
        }
        if(evt.getKeyCode()==KeyEvent.VK_UP || evt.getKeyCode()==KeyEvent.VK_DOWN)
        {
            jTableItemList.clearSelection();
            int row = jTableOrderList.getSelectedRow();
            if(row==-1) return;
            OrderItem theOrderItem = (OrderItem)theOrderItemV.get(row);
            pDOrder1.setOrderItem(theOrderItem);
        }
        GActionAuthV gaav = theHO.theGActionAuthV;
        if(evt.getKeyCode()==KeyEvent.VK_DELETE && gaav.isReadPOrderButtonCancel())
        {
            jButtonCancelActionPerformed(null);
        }
        if(evt.getKeyCode()==KeyEvent.VK_V && gaav.isReadPOrderButtonVerify())
        {
            int[] row = jTableOrderList.getSelectedRows();
            theHC.theOrderControl.verifyOrderItem(theOrderItemV,row);
        }
        if(evt.getKeyCode()==KeyEvent.VK_E && gaav.isReadPOrderButtonExecute())
        {
            this.jButtonExecuteActionPerformed(null);
        }
        if(evt.getKeyCode()==KeyEvent.VK_D && gaav.isReadPOrderButtonDispense())
        {
            int[] row = jTableOrderList.getSelectedRows();
            theHC.theOrderControl.dispenseOrderItem(theOrderItemV,row);
        }
        if(evt.getKeyCode()==KeyEvent.VK_C && gaav.isReadPOrderButtonCalBill())
        {
            this.jButtonCalActionPerformed(null);
        }
    }//GEN-LAST:event_jTableOrderListKeyReleased
    
    
    /**
     *@deprecated henbe มีการเขียนข้อมูลลง HO โดย GUI ซึ่งผิด pattern
     */
    private void jTableOrderListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableOrderListMouseReleased
        jTableItemList.clearSelection();
        if(evt == null)
        {
            return;
        }
        if(evt.isPopupTrigger())   {
            jPopupMenu1.setLocation((evt.getX()+this.jTableOrderList.getLocationOnScreen().x)
                , (evt.getY()+jTableOrderList.getLocationOnScreen().y));
            jPopupMenu1.setVisible(true);
        }
        else {
            jPopupMenu1.setVisible(false);
        }
        theHO.is_order=true;
        int[] select = jTableOrderList.getSelectedRows();
        if(select.length == 1)
        {
            OrderItem oi = (OrderItem)theOrderItemV.get(select[0]);
            pDOrder1.setOrderItem(oi);
            if(oi.isLab())
                this.doubleClickListLab(oi);
            else if(oi.isXray())
                this.doubleClickListXray(oi);
        }
        //Constant.println("jCheckBoxContinue.setSelected(isContinue(theOrderItemV,select));1");
        jCheckBoxContinue.setSelected(isContinue(theOrderItemV,select));
        //Constant.println("jCheckBoxContinue.setSelected(isContinue(theOrderItemV,select));2");
        //คำนวนค่าใช้จ่ายทั้งหมดที่เลือก
        if(select.length>1)
        {
            double price =0;
            for(int i=0;i<select.length;i++)
            {
                OrderItem oi= (OrderItem)theOrderItemV.get(select[i]);
                float ppu = Float.parseFloat(oi.price);
                float qty = Float.parseFloat(oi.qty);
                price = price + Math.ceil(ppu*qty);
            }
            theUS.setStatus(Constant.getTextBundle("จำนวนรายการที่เลือก") + " "+select.length+" " + 
                    Constant.getTextBundle("รายการ รวม") + " "+Math.ceil(price)+ " "+
                    Constant.getTextBundle("บาท"),UpdateStatus.COMPLETE);
        }
        this.jToggleButtonSelectAll.setSelected(false);
    }//GEN-LAST:event_jTableOrderListMouseReleased
    
    private void jTableItemListKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableItemListKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_UP ||  evt.getKeyCode()== KeyEvent.VK_DOWN)
        {
            jTableOrderList.clearSelection();
            int row = jTableItemList.getSelectedRow();
            if(row==-1) return;
            Item item_order = (Item)theItemV.get(row);
            pDOrder1.setItem(item_order);
        }
        if(evt.getKeyCode() == KeyEvent.VK_SPACE)
        {
            OrderItem oi = pDOrder1.getOrderItem();
            if(oi.isLab() && "1".equals(oi.secret))
                    return;
            this.pDOrder1.saveOrderItem();
        }
    }//GEN-LAST:event_jTableItemListKeyReleased
    
    
    /**
     *@deprecated henbe มีการเขียนข้อมูลลง HO โดย GUI ซึ่งผิด pattern
     */
    private void jTableItemListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableItemListMouseReleased
        jTableOrderList.clearSelection();
        int row = jTableItemList.getSelectedRow();
        if(row==-1) return;
        theHO.is_order=false;//amp:27/03/2549
        Item item_order = (Item)theItemV.get(row);
        boolean is_ok = pDOrder1.setItem(item_order);
        OrderItem theOrderItem = pDOrder1.getOrderItem();
        if(!is_ok)
            return;
        if(theOrderItem.isLab()) {
            if("1".equals(theOrderItem.secret)) 
                theOrderLabSecret = new OrderLabSecret();
            else
                pDOrder1.saveOrderItem();
        }
        else if(theOrderItem.isXray())
            pDOrder1.saveOrderItem();
            //amp:02/03/2549 เพราะสั่งแลปปกปิดไม่ได้
        else
            doubleClickList(evt);
        
    }//GEN-LAST:event_jTableItemListMouseReleased
            
    private void jComboBoxGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxGroupActionPerformed
        if(jCheckBoxGroup.isSelected())
        {
            jButtonSearchActionPerformed(null);
        }
    }//GEN-LAST:event_jComboBoxGroupActionPerformed
    
    private void jButtonVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerifyActionPerformed
        int[] rows = jTableOrderList.getSelectedRows();
        // ถ้า Order ที่ยืนยัน
        for(int i =0 ; i < rows.length ;i++)
        {  
            OrderItem theOrderItem = (OrderItem)theOrderItemV.get(rows[i]); 
            // ในกรณีที่เป็นผู้ป่วยนอกและเปิด Option ในหน้า Setup และ Order ที่สั่งยืนยันเป็นเวชภัณฑ์หรือค่าบริการ sumo 05/09/2549
            if(theOrderItem.isService() || theOrderItem.isDental())
            {
                ItemService vItem_service = theHC.theSetupControl.readItemSeviceByItem(theOrderItem.item_code);
                if(vItem_service != null
                && theHC.theLookupControl.readOption().auto_diag_icd9.equals("1")
                && theOrderItem.status.equals(OrderStatus.NOT_VERTIFY)
                && theHO.theDiagDoctorClinic.buseMemory){
                    theHO.theDiagDoctorClinic.doctor_id = theHO.theEmployee.getObjectId();
                    theHD.showDialogDiag(theHO.theDiagDoctorClinic);
                }
            }
        }
        theHC.theOrderControl.verifyOrderItem(theOrderItemV,rows);
    }//GEN-LAST:event_jButtonVerifyActionPerformed
    
    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        int[] a = jTableOrderList.getSelectedRows();
        theHC.theOrderControl.cancelOrderItem(theOrderItemV,a);
    }//GEN-LAST:event_jButtonCancelActionPerformed
    
    private void jButtonDispenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDispenseActionPerformed
        int[] a = jTableOrderList.getSelectedRows();
        theHC.theOrderControl.dispenseOrderItem(theOrderItemV,a);
    }//GEN-LAST:event_jButtonDispenseActionPerformed
    
    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        int[] rows = getCurrentRowsOrder();
        boolean all = !jCheckBoxPeriod.isSelected();
        String df = dateComboBoxStart.getText();
        String dt = dateComboBoxEnd.getText();
        String type = Gutil.getGuiData(jComboBoxGroup1);
        System.err.println("type "+type);
        theOrderItemV = theHC.theOrderControl.listOrderItemByRange(all,df,dt,type
                ,jCheckBoxShowCancel.isSelected());
        setOrderV(theOrderItemV);
        setCurrentRowsOrder(rows);
    }//GEN-LAST:event_jButtonRefreshActionPerformed
    
    private void jButtonCalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalActionPerformed
        int[] row = jTableOrderList.getSelectedRows();
        /* pu 19/07/2549*/
        this.selectedrows = row;
        //jButtonRefreshActionPerformed(null);
        if(theHO.vVisitPayment==null || theHO.vVisitPayment.size()==0)
        {
            theUS.setStatus("ผู้ป่วย ยังไม่มีสิทธิการรักษา",UpdateStatus.WARNING);
            return ;
        }
        if(row.length == 0)
        {
            theUS.setStatus("ยังไม่ได้เลือกรายการ",UpdateStatus.WARNING);
            return;
        }
        /*ตรวจสอบว่าคิดเงินหรือยัง ยังไม่คิดเงิน ตรวจสอบว่าไม่อยู่ในสถานะยกเลิก และ ไม่ยืนยัน*/
        //คิดเงินในรายการที่ ยังไม่คิดเงิน และ ยืนยันแล้ว หรือตำเนินการแล้ว หรือรายงานผลแล้ว หรือจ่ายแล้ว
        //amp:17 ธันวาคม 2548
        Vector vorderItem = new Vector();
        boolean is_outofrange = false;
        for(int i=0;i<row.length;i++)
        {
            OrderItem oi= (OrderItem)theOrderItemV.get(row[i]);
            if(oi.charge_complete.equals("0"))
            {
                if(oi.status.equals(OrderStatus.VERTIFY)
                || oi.status.equals(OrderStatus.DISPENSE)
                || oi.status.equals(OrderStatus.REPORT)
                || oi.status.equals(OrderStatus.EXECUTE)
                || oi.status.equals(OrderStatus.REMAIN))
                    vorderItem.add(oi);
                else is_outofrange=true;
            }
            else is_outofrange=true;
        }
        if(vorderItem.size() == 0)
        {
            //pu: 02/08/2549 : แก้ไขการทำงานของปุ่มเลือกทั้งหมด
            if(this.jToggleButtonSelectAll.isSelected())
                this.jTableOrderList.selectAll();
            String str = "กรุณาเลือกรายการที่ อยู่ในสถานะยืนยัน ดำเนินการ รายงานผล จ่าย และยังไม่ถูกคิดเงิน";
            theUS.setStatus(str,UpdateStatus.WARNING);
            return;
        }
        theHD.showDialogCalBilling(vorderItem,jToggleButtonSelectAll.isSelected());
    }//GEN-LAST:event_jButtonCalActionPerformed
                                
    private void jButtonExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExecuteActionPerformed
        int[] row = jTableOrderList.getSelectedRows();
        Vector vRowExecute = new Vector();
        boolean has_lab_verify = false;
        for(int i=0;i<row.length;i++)
        {
            OrderItem oi = (OrderItem)theOrderItemV.get(row[i]);
            if(oi.isDrug()  && oi.status.equals(OrderStatus.VERTIFY))
            {
                OrderItemDrug oid = theHC.theOrderControl.readOrderItemDrugByOid(
                        oi.getObjectId());
                Drug drug = theHC.theSetupControl.readDrug(oi.item_code);
                if(drug!=null && drug.printting!=null
                    && drug.printting.equals("1")) {
                    vRowExecute.addElement(oi);           
                }
            }
            if(oi.isLab()   && oi.status.equals(OrderStatus.VERTIFY)){
                has_lab_verify = true;
            }
        }
        boolean work = theHC.theOrderControl.executeOrderItem(theOrderItemV,row);
////////////////////////////////////////////////////////////////////////
        if(!vRowExecute.isEmpty()) 
            theHC.thePrintControl.printDrugSticker(vRowExecute, null, null, true);
    }//GEN-LAST:event_jButtonExecuteActionPerformed
                        
    private void jTextFieldKeywordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldKeywordKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_DOWN)
        {
            if(jTableItemList.getRowCount() >0)
            {
                jTableItemList.requestFocus();
                jTableItemList.setRowSelectionInterval(0,0);
            }
        } else if (this.jTextFieldKeyword.getText().endsWith(" ")) {
            jButtonSearchActionPerformed(null);
        } else {
            jButtonSearchActionPerformed(null);
        }
    }//GEN-LAST:event_jTextFieldKeywordKeyReleased
    
    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        //ค้นหารายการ Item ถ้า keyword เป็นช่องว่าง และ ค้นหาจากกลุ่มรายการมีการติ๊กถูกหรือไม่
        String keyword = jTextFieldKeyword.getText();
        keyword = Gutil.CheckReservedWords(keyword);
        if(keyword.length() < 1 && !jCheckBoxGroup.isSelected())
        {
            theUS.setStatus("คำที่ใช้ในการค้นหาสั้นเกินไป",UpdateStatus.WARNING);
            return;
        }
        boolean begin_with = this.jRadioButtonBegin.isSelected();
        // Somprasong 120810 comment fix bug search
//        if (jCheckBoxGroup.isSelected()) {
//            theItemV  = theHC.theOrderControl.listItem(
//                    ComboboxModel.getCodeComboBox(jComboBoxGroup)+ " "+keyword,begin_with);
//        }
//        else {
//            theItemV  = theHC.theOrderControl.listItem(keyword,begin_with);
//        }
        String grpId = jCheckBoxGroup.isSelected()?ComboboxModel.getCodeComboBox(jComboBoxGroup):"";
        theItemV  = theHC.theOrderControl.listItem(grpId, keyword, begin_with);
        setItemV(theItemV);
    }//GEN-LAST:event_jButtonSearchActionPerformed
    
    private void jTextFieldKeywordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldKeywordActionPerformed
        jButtonSearchActionPerformed(null);
    }//GEN-LAST:event_jTextFieldKeywordActionPerformed

    private void jButtonBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBarcodeActionPerformed
        theHD.showPanelLabList(this.theHC,this);
    }//GEN-LAST:event_jButtonBarcodeActionPerformed
    
    /**
     * ใช้ในการสั่งยาต่อเนื่องหรือยกเลิกยาต่อเนื่องโดย รับค่ามาเป็น boolean ถ้าเป็น true จะเลือกทั้งหนด ถ้าเป็น false
     * เลือกทั้งหมด
     * @param selectall ถ้าเป็น true ให้เลือกทั้งหมด และทำการตรวจสอบ
     * ถ้าเป็น false ให้เลือกเฉพาะที่ถูกกำหนดมาแล้วจากตาราง
     *
     */
    public void continueDrug(boolean selectall)
    {
        if(selectall)
        {
            jTableOrderList.selectAll();
            jCheckBoxContinue.setSelected(false);
        }
        int[] row = jTableOrderList.getSelectedRows();
        /////////////////////////////////////////////////////////////////////////
        ddc = new DiagDoctorClinic();
        if(theHO.theEmployee.authentication_id.equals(Authentication.DOCTOR))
            ddc.doctor_id = theHO.theEmployee.getObjectId();
        
        if(jCheckBoxContinue.isSelected())
        {
            if(!Authentication.DOCTOR.equals(theHO.theEmployee.authentication_id))
            {
                if(!theHD.showDialogContinueDrug(ddc)){
                    return;
                }
            }
            theHC.theOrderControl.continueOrderItem(theOrderItemV,row,new Employee(ddc.doctor_id));
        }
        ////////////////////////////////////////////////////////////////////////
        else
        {
            if(!isContinue(theOrderItemV,row)) {
                theUS.setStatus("ไม่มีรายการยาต่อเนื่อง",UpdateStatus.WARNING);
                return;
            }
            if(!Authentication.DOCTOR.equals(theHO.theEmployee.authentication_id))
            {
                if(!theHD.showDialogOffDrug(ddc)){
                    return;
                }
            }
            theHC.theOrderControl.cancelOrderDrugContinue(theOrderItemV,row,new Employee(ddc.doctor_id));
        }
        jCheckBoxContinue.setSelected(isContinue(theOrderItemV,row));
        ///////////////////////////////////////////////////////////////////////////////
    }
    
    public boolean isContinue(Vector vorder,int[] row)
    {
        int haveContinue = 0;
        for(int i=0; i<row.length; i++)
        {
            OrderItem oi = (OrderItem)vorder.get(row[i]);
            if(oi.continue_order.equals("1"))
                haveContinue = haveContinue + 1;
        }
        if(row.length>0)
            return (haveContinue==row.length);
        else
            return false;
    }
    
    /*
     *นับยาจากจำนวนยา
     */
    public static int countWaitDispense(Vector theOrderItemV)
    {
        int queueDespense = 0;
        for(int i = 0 ; i< theOrderItemV.size() ; i++)
        {
            OrderItem theOrderItemVs = (OrderItem)theOrderItemV.get(i);
            if(theOrderItemVs.category_group.equals("1")
            && theOrderItemVs.charge_complete.equals(Active.isEnable())
            && theOrderItemVs.dispense != null
                    && !theOrderItemVs.dispense.equals(""))
            {
                queueDespense = queueDespense + 1;
            }
        }
        return queueDespense;
    }
    
    /*
     *นับยาจากจำนวนยา
     */
    private void setVisit(Visit v)
    {
        theVisit = v;
        if(v==null)
        {
            setEnabled(false);
            /*ให้สามารถพิมพ์สติ๊กเกอร์ยาได้ โดยไม่ต้องเลือกผู้ป่วย*/
            /*pu : 20/07/2549*/
            setEnabledRead(true);
            setOrderV(null);
            this.jTextAreaNote.setText("");
            return;
        }
        this.jTextAreaNote.setText(v.diagnosis_note);
        jCheckBoxContinue.setVisible(v.visit_type.equals(VisitType.IPD));        
        if(theHO.theVisit.locking.equals("1")
        && !theHO.theVisit.lock_user.equals(theHO.theEmployee.getObjectId()))
        {
            setEnabled(false);
            setEnabledRead(true);
            return;
        }
        if(theHO.theVisit.is_discharge_money.equals("1"))
        {
            setEnabled(false);
            //ผู้ป่วยจะได้รับยาเมื่อจ่ายเงินเสร็จแล้ว
            boolean b = true;
            jButtonDispense.setEnabled(b);
            jButtonVerify.setEnabled(b);
            jButtonExecute.setEnabled(b);
            jTableOrderList.setEnabled(b);
            jButtonRefresh.setEnabled(b);
            jToggleButtonSelectAll.setEnabled(b);
            jCheckBoxPeriod.setEnabled(b);
            dateComboBoxEnd.setEnabled(b);
            dateComboBoxStart.setEnabled(b);
            jComboBoxGroup1.setEnabled(b);
            return;
        }
        if(theVisit.isOutProcess()
        || theVisit.isDropVisit()) {
            setEnabled(false);
            return;
        }          
        setEnabled(true);
        jCheckBoxContinue.setSelected(false);        
    }
    
        /*
         *
     //henbe_comment ตรวจสอบว่ารายการจ่ายยาหมดหรือยัง ถ้าจ่ายหมดแล้วก็ให้ลบออกจากคิวรอจ่ายยา
         *public static int checkDespense(Vector theOrderItemV)
    //henbe_error this function move to control
         *
         */
    private void setItemV(Vector vc)
    {
        theItemV = vc;
        TaBleModel tm ;
        if(vc == null)
        {
            tm= new TaBleModel(col_jTableItemList,0);
            jTableItemList.setModel(tm);
            return;
        }
        tm= new TaBleModel(col_jTableItemList,vc.size());
        CategoryGroupItem cg=null;
        for(int i=0 ;i<vc.size(); i++)
        {
            Item p = (Item)vc.get(i);
            if(cg==null || !cg.getObjectId().equals(p.item_group_code_category))
                cg = theHC.theLookupControl.readCategoryGroupItemById(p.item_group_code_category);
            tm.setValueAt(p,i,0);
            if(cg!=null)   tm.setValueAt( cg.description,i,1);
            else           tm.setValueAt("ข้อมูลมีปัญหา",i,1);
        }
        if(jTableItemList.getColumnModel().getColumnCount()>0){
            int column1_wide = jTableItemList.getColumnModel().getColumn(0).getWidth();
            int column2_wide = jTableItemList.getColumnModel().getColumn(1).getWidth();
            jTableItemList.setModel(tm);
            jTableItemList.getColumnModel().getColumn(0).setPreferredWidth(column1_wide); // จำนวน
            jTableItemList.getColumnModel().getColumn(1).setPreferredWidth(column2_wide); // กลุ่ม
        }
        else
            jTableItemList.setModel(tm);
        jTableItemList.getColumnModel().getColumn(0).setCellRenderer(cellRendererItem);
        jTableItemList.getColumnModel().getColumn(1).setCellRenderer(TableRenderer.getRendererCenter());
    }
    
    /**
     *เก็บข้อมูลรายการสั่งตรวจ(รายการยา) ที่ต้องการพิมพ์สติ๊กเกอร์
     *โดยไม่ต้องเลือกผู้ป่วย
     *@param item เป็น Object ของรายการสั่งตรวจที่เลือกจากรายการ Item
     *@param drug เป็น Object ของรายการสั่งตรวจที่เป็นรายการยา Drug
     *@return OrderItemDrug เป็น Object ของรายการตรวจรักษาที่สั่งพิมพ์
     *@Author pu
     *@date 20/07/2549
     */
    private void setTableOrderListDefault(int[] rows)
    {
        jTableOrderList.getColumnModel().getColumn(0).setPreferredWidth(30); // ล็อก
        jTableOrderList.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        jTableOrderList.getColumnModel().getColumn(1).setPreferredWidth(250); // ชื่อสามัญสำหรับ รพ.ทั่วไป, ชื่อการค้า สำหรับศูนย์โรคตา
        jTableOrderList.getColumnModel().getColumn(1).setCellRenderer(cellRendererDayOrder);
        jTableOrderList.getColumnModel().getColumn(2).setPreferredWidth(30); // จำนวน
        jTableOrderList.getColumnModel().getColumn(2).setCellRenderer(TableRenderer.getRendererRight());
        jTableOrderList.getColumnModel().getColumn(3).setPreferredWidth(50); // ราคา
        
        jTableOrderList.getColumnModel().getColumn(3).setCellRenderer(TableRenderer.getRendererRight());
        jTableOrderList.getColumnModel().getColumn(4).setPreferredWidth(60); // สถานะ
        jTableOrderList.getColumnModel().getColumn(4).setCellRenderer(TableRenderer.getRendererCenter());
        jTableOrderList.getColumnModel().getColumn(5).setPreferredWidth(50); // ขอตรวจ
        
        /////////////////////////////////////////////////////////////////////////
        HCheckBoxEditor ce = new HCheckBoxEditor(new JCheckBox());
        ce.setEControl(this);
        ce.setType("req");
        jTableOrderList.getColumnModel().getColumn(5).setCellEditor(ce);
        jTableOrderList.getColumnModel().getColumn(5).setCellRenderer(new CellRendererCheckBox());
    }
    
    public int[] getCurrentRowsOrder()
    {
        return jTableOrderList.getSelectedRows();
    }
    public boolean setCurrentRowsOrder(int[] rows)
    {
        //Constant.println("public boolean setCurrentRowsOrder(int[] rows) "+ rows.length);
        this.jToggleButtonSelectAll.setSelected(false);
        if(rows.length == 0 )
        {
            int rows_length = jTableOrderList.getRowCount();
            if(rows_length>1)
                jTableOrderList.setRowSelectionInterval(0,rows_length-1);
            return true;
        }
        if(rows.length > 0 )
        {
            boolean select_all = (rows.length==jTableOrderList.getRowCount());
            //หากเป็นการเลือกรายการเก่าถ้ามี หลายรายการก็ไม่ต้องแสดง detail
            if(jTableOrderList.getRowCount()>rows[rows.length-1])
            {
                jTableOrderList.setRowSelectionInterval(rows[0],rows[rows.length-1]);
            }
            //หากเป็นการเลือกรายการเก่าถ้ามี รายการเดียวก็ให้แสดง detail
            if(rows.length==1 && rows[0]<theOrderItemV.size())
            {
                OrderItem oi = (OrderItem)this.theOrderItemV.get(rows[0]);
                pDOrder1.setOrderItem(oi);
            }
            return true;
        }
        //หากเป็นการเพิ่มรายการใหม่จะไปเลือกรายการใหม่แทน
        else if(jTableOrderList.getRowCount() > 0)
        {
            int row_max = jTableOrderList.getRowCount()-1;
            jTableOrderList.setRowSelectionInterval(row_max,row_max);
            JScrollBar sv = this.jScrollPaneOrderList.getVerticalScrollBar();
            sv.setValue(sv.getMaximum());
            if(row_max < theOrderItemV.size())
            {
                OrderItem oi = (OrderItem)this.theOrderItemV.get(row_max);
                pDOrder1.setOrderItem(oi);
            }
            return true;
        }
        return false;
    }
    /*
     *old selected row
     */
    private void setTableOrderListDefaultHaveContinue(int[] rows)
    {
        jTableOrderList.getColumnModel().getColumn(0).setPreferredWidth(30); // ล็อก
        jTableOrderList.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        jTableOrderList.getColumnModel().getColumn(1).setPreferredWidth(250); // ชื่อสามัญสำหรับ รพ.ทั่วไป, ชื่อการค้า สำหรับศูนย์โรคตา
        jTableOrderList.getColumnModel().getColumn(1).setCellRenderer(cellRendererDayOrder);
        jTableOrderList.getColumnModel().getColumn(2).setPreferredWidth(30); // จำนวน
        jTableOrderList.getColumnModel().getColumn(2).setCellRenderer(TableRenderer.getRendererRight());
        jTableOrderList.getColumnModel().getColumn(3).setPreferredWidth(50); // ราคา
        jTableOrderList.getColumnModel().getColumn(3).setCellRenderer(TableRenderer.getRendererRight());
        jTableOrderList.getColumnModel().getColumn(4).setPreferredWidth(75); // สถานะ
        jTableOrderList.getColumnModel().getColumn(4).setCellRenderer(TableRenderer.getRendererCenter());
        jTableOrderList.getColumnModel().getColumn(5).setPreferredWidth(50); // ขอตรวจ
        HCheckBoxEditor ce = new HCheckBoxEditor(new JCheckBox());
        ce.setEControl(this);
        ce.setType("req");
        jTableOrderList.getColumnModel().getColumn(5).setCellEditor(ce);
        jTableOrderList.getColumnModel().getColumn(5).setCellRenderer(new CellRendererCheckBox());
        jTableOrderList.getColumnModel().getColumn(6).setPreferredWidth(70); // สั่งยาต่อเนื่อง
        jTableOrderList.getColumnModel().getColumn(6).setCellRenderer(cellRendererContinue);
        /**รายการกลับบ้าน*/
        HCheckBoxEditor order_home = new HCheckBoxEditor(new JCheckBox());
        order_home.setEControl(this);
        order_home.setType("home");
        jTableOrderList.getColumnModel().getColumn(7).setCellEditor(order_home);
        jTableOrderList.getColumnModel().getColumn(7).setPreferredWidth(50); // กลับบ้าน
        jTableOrderList.getColumnModel().getColumn(7).setCellRenderer(new CellRendererCheckBox());
        
        
    }
    //OrderItem pDOrder1.setOrderItem(Vector vc)
    /*
     เก็บเฉพาะ order ที่ยังไม่ถูกยกเลิก
     ตัวฐานข้อมูล objdb ดึงเฉพาะส่วนนั้นมาให้แล้ว
     */
    private void setOrderV(Vector vc)
    {
        theOrderItemV = vc;
        if(theOrderItemV == null)
        {
            TaBleModel tm = new TaBleModel(col_jTableOrderList,0);
            jTableOrderList.setModel(tm);
            pDOrder1.setOrderItem(null);
            return;
        }
        int[] rows = jTableOrderList.getSelectedRows();
        /*ผู้ป่วยนอก */
        if(theHO.theVisit.visit_type.equals(VisitType.OPD))
        {
            /*เพื่อกันตัวที่ยกเลิกแล้วออกไป*/
            TaBleModel tm= new TaBleModel(col_jTableOrderList_opd,theOrderItemV.size());
            boolean is_outpc = theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess());
            boolean is_lock = theHO.theVisit.locking.equals("1")
            && !theHO.theVisit.lock_user.equals(theHO.theEmployee.getObjectId());
            if(!is_outpc && !is_lock)
                tm.setEditingCol(5);
            for(int i=0,drug=0 ;i<theOrderItemV.size(); i++)
            {
                OrderItem order = (OrderItem)theOrderItemV.get(i);
                float ppu = Float.parseFloat(order.price);
                float qty = Float.parseFloat(order.qty);
                OrderItemStatus os = theHC.theLookupControl.readOrderItemStatus(order.status);
                String status = "";
                double sum = Math.ceil(ppu*qty);
                if(os!=null) status = os.description;
                tm.setValueAt(order.charge_complete,i,0);
                Hashtable ht = new Hashtable();
                Hashtable ht_status = new Hashtable();
                //ถ้าเป็นยาค่อยค้นการทำปฏิกริยาต่อกันเท่านั้นจะได้ไม่ต้อง query มากเกิน henbe
                ht.put("OrderItem",order);
//                ht.put("ln",this.theHC.theOrderControl.getLabNumber(this.theHO.theVisit.getObjectId(),order.executed_time));
//                ht.put("ln","");
                ht.put("ln",order.isLab()?this.theHC.theOrderControl.getLabNumber(order.getObjectId()) : "-");
                ht.put("String",order.sDrugInteraction);
                String ttt = theHC.theOrderControl.getTTTRenderDayOrder(order,"");
                ht.put("display_string", ttt);
                //Constant.println("order.category_group" + order.category_group);
                //การแสดง dose ย่อให้ใช้กับ order ที่มีรายการไม่เกิน 10 รายการเพราะว่ามันจะคิวรีเยอะมาก
                if(order.isDrug()){
                    drug++;
                    OrderItemDrug oid = order.theOrderItemDrug;

                    if(theHC.theLookupControl.readOption().isShowDose()){
                        if(oid==null)
                            oid = theHC.theOrderControl.readOrderItemDrugByOid(order.getObjectId());
                        String short_dose = theHC.theLookupControl.readShortDoseText(oid);
                        ht.put("short_dose", short_dose);
                    }
                }
                tm.setValueAt(ht,i,1);
                tm.setValueAt(order.qty,i, 2);
                tm.setValueAt(Constant.dicimal(String.valueOf(sum)),i,3);
                tm.setValueAt(status,i, 4);                
                
                boolean status_enable;
                if(OrderStatus.DIS_CONTINUE.equals(order.status)) status_enable = false;
                else status_enable = true;   
                
                ht_status.put("status", Boolean.valueOf(status_enable));
                ht_status.put("request", Boolean.valueOf(order.request.equals("1")));               
                tm.setValueAt(ht_status,i,5);
                
                ht_status=null;
                ht=null;
            }
            jTableOrderList.setModel(tm);
            setTableOrderListDefault(rows);
            return;
        }
        ////////////////////////////////////////////////////////////////////
        /*ผู้ป่วยใน*/
        if(theHO.theVisit.visit_type.equals(VisitType.IPD))
        {
            /*เพื่อกันตัวที่ยกเลิกแล้วออกไป ยกเว้นสถานะจ่าย*/
            TaBleModel tm= new TaBleModel(col_jTableOrderList_ipd,theOrderItemV.size());
            tm.setEditingCol(5,7);
            for(int i=0,drug=0 ;i<theOrderItemV.size(); i++)
            {
                OrderItem p = (OrderItem)theOrderItemV.get(i);
                OrderItemStatus os = theHC.theLookupControl.readOrderItemStatus(p.status);
                String status = "";
                if(os!=null) status = os.description;
                float ppu = Float.parseFloat(p.price);
                float qty = Float.parseFloat(p.qty);
                double sum = Math.ceil(ppu*qty);
                tm.setValueAt(p.charge_complete,i,0);
                //tm.setValueAt(p,i,1);
                //amp:07/04/2549
                Hashtable ht = new Hashtable();
                ht.put("OrderItem",p);
                ht.put("String",p.sDrugInteraction);
                String ttt = theHC.theOrderControl.getTTTRenderDayOrder(p,"");
                ht.put("display_string", ttt);
                ht.put("ln",p.isLab()?this.theHC.theOrderControl.getLabNumber(p.getObjectId()) : "-");
                if(p.isDrug()){
                    drug++;
                    OrderItemDrug oid = p.theOrderItemDrug;
                    if(theHC.theLookupControl.readOption().isShowDose()){
                        if(oid==null)
                            oid = theHC.theOrderControl.readOrderItemDrugByOid(p.getObjectId());
                        String short_dose = theHC.theLookupControl.readShortDoseText(oid);
                        ht.put("short_dose", short_dose);
                    }
                }
                tm.setValueAt(ht,i,1);
                tm.setValueAt(p.qty,i, 2);
                tm.setValueAt(Constant.dicimal(String.valueOf(sum)),i,3);
                tm.setValueAt(status,i, 4);
                tm.setValueAt(Boolean.valueOf(Gutil.isSelected(p.request)),i,5);
                tm.setValueAt(p.continue_order,i,6);
                tm.setValueAt(Boolean.valueOf(Gutil.isSelected(p.order_home)),i,7);
                ht=null;
            }
            jTableOrderList.setModel(tm);
            setTableOrderListDefaultHaveContinue(rows);
            return;
        }
    }
    
    /*
     *เป็นการเลือกรายการ item แล้วโปรแกรมจะแสดงรายละเอียดของ item นั้นและค่า default order
     *
     */
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupaKeySearch;
    private com.hospital_os.utility.DateComboBox dateComboBoxEnd;
    private com.hospital_os.utility.DateComboBox dateComboBoxStart;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private javax.swing.JButton jButtonB3;
    private javax.swing.JButton jButtonBarcode;
    private javax.swing.JButton jButtonBilling;
    private javax.swing.JButton jButtonCal;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonDispense;
    private javax.swing.JButton jButtonDrugOld;
    private javax.swing.JButton jButtonDrugRx;
    private javax.swing.JButton jButtonDrugSet;
    private javax.swing.JButton jButtonExecute;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonSticker;
    private javax.swing.JButton jButtonVerify;
    private javax.swing.JToggleButton jCheckBoxContinue;
    private javax.swing.JCheckBox jCheckBoxGroup;
    private javax.swing.JCheckBox jCheckBoxPeriod;
    private javax.swing.JToggleButton jCheckBoxShowCancel;
    private javax.swing.JComboBox jComboBoxGroup;
    private javax.swing.JComboBox jComboBoxGroup1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelTo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelAction;
    private javax.swing.JPanel jPanelCondition;
    private javax.swing.JPanel jPanelOrderList;
    private javax.swing.JPanel jPanelRightSide;
    private javax.swing.JPanel jPanelSearchItem;
    private javax.swing.JRadioButton jRadioButtonBegin;
    private javax.swing.JRadioButton jRadioButtonConsist;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPaneOrderList;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private com.hosv3.gui.component.HJTableSort jTableItemList;
    private com.hosv3.gui.component.HJTableSort jTableOrderList;
    private javax.swing.JTextArea jTextAreaNote;
    private javax.swing.JTextField jTextFieldKeyword;
    private javax.swing.JToggleButton jToggleButtonSelectAll;
    private com.hosv3.gui.panel.detail.PDOrder pDOrder1;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JMenuItem jMenuItemVerify;
    private javax.swing.JMenuItem jMenuItemExecute;
    private javax.swing.JMenuItem jMenuItemDispense;
    private javax.swing.JMenuItem jMenuItemCancel;
    
    public void setEnabled(boolean b)
    {
        jButtonCal.setEnabled(b);
        
        jButtonVerify.setEnabled(b);
        jButtonExecute.setEnabled(b);
        jButtonDispense.setEnabled(b);
        jButtonCancel.setEnabled(b);
        jButtonDrugRx.setEnabled(b);
//        jButtonSticker.setEnabled(b);
        jTableItemList.setEnabled(b);
        jCheckBoxContinue.setEnabled(b);
        //jCheckBoxShowCancel.setEnabled(b);
        jButtonDrugOld.setEnabled(b);
        jButtonDrugSet.setEnabled(b);
        
        jButtonSearch.setEnabled(b);
        jTextFieldKeyword.setEnabled(b);
        jCheckBoxGroup.setEnabled(b);
        jTableOrderList.setEnabled(b);
        jButtonRefresh.setEnabled(b);
        jToggleButtonSelectAll.setEnabled(b);
        jCheckBoxPeriod.setEnabled(b);
        dateComboBoxEnd.setEnabled(b);
        dateComboBoxStart.setEnabled(b);
        jComboBoxGroup1.setEnabled(b);
        jRadioButtonBegin.setEnabled(b);
        jRadioButtonConsist.setEnabled(b);
        jButtonB3.setEnabled(b);
        pDOrder1.setEnabled(b);
//        jButtonBarcode.setEnabled(b);
    }
    
    public void setLanguage(String msg)
    {
        GuiLang.setLanguage(jButtonCal);
        GuiLang.setLanguage(jButtonBilling);
        GuiLang.setLanguage(pDOrder1);
        GuiLang.setLanguage(jCheckBoxGroup);
        GuiLang.setLanguage(jCheckBoxPeriod);
        GuiLang.setLanguage(jLabelTo);
        GuiLang.setLanguage(jButtonRefresh);
        GuiLang.setLanguage(jToggleButtonSelectAll);
        GuiLang.setLanguage(jButtonVerify);
        GuiLang.setLanguage(jButtonExecute);
        GuiLang.setLanguage(jButtonCancel);
        GuiLang.setLanguage(jButtonDispense);
        GuiLang.setLanguage(jCheckBoxContinue);
        GuiLang.setLanguage(jButtonCal);
        GuiLang.setTextBundle(jPanelSearchItem);
        GuiLang.setTextBundle(jPanelOrderList);
        GuiLang.setLanguage(this.col_jTableItemList);
        GuiLang.setLanguage(this.col_jTableOrderList);
        GuiLang.setLanguage(this.col_jTableOrderList_ipd);
        GuiLang.setLanguage(this.col_jTableOrderList_opd);
        GuiLang.setLanguage(this.jCheckBoxShowCancel);
        GuiLang.setLanguage(this.jRadioButtonBegin);
        GuiLang.setLanguage(this.jRadioButtonConsist);
        GuiLang.setLanguage(this.jButtonDrugOld);
        GuiLang.setLanguage(this.jButtonDrugSet);
        GuiLang.setLanguage(this.jButtonSearch);
        GuiLang.setLanguage(this.jLabel4);
        GuiLang.setLanguage(jButtonDrugRx);
        GuiLang.setLanguage(jButtonSticker);
    }
    public void setEnabledRead(boolean b)
    {
        jButtonSearch.setEnabled(b);
        jTextFieldKeyword.setEnabled(b);
        jCheckBoxGroup.setEnabled(b);
        jTableOrderList.setEnabled(b);
        jButtonRefresh.setEnabled(b);
        jToggleButtonSelectAll.setEnabled(b);
        jCheckBoxPeriod.setEnabled(b);
        dateComboBoxEnd.setEnabled(b);
        dateComboBoxStart.setEnabled(b);
        jComboBoxGroup1.setEnabled(b);
        jRadioButtonBegin.setEnabled(b);
        jRadioButtonConsist.setEnabled(b);
        jTableOrderList.setEnabled(b);
        //พิมพ์สติ๊กเกอร์ยาโดยไม่ต้องเลือกผู้ป่วย 
        //pu : 20/07/2549
        jTableItemList.setEnabled(b);
    }
    
///////////////////////////////////////////////////////////////////////
    public Vector getOrderItemV()
    {
        Vector v_selected = new Vector();
        int[] row = this.jTableOrderList.getSelectedRows();
        for(int i=0;i<row.length;i++)
            v_selected.add(theOrderItemV.get(row[i]));
        return v_selected;
    }
    public Item getItem(){
        int row = this.jTableItemList.getSelectedRow();
        if(row!=-1)
            return (Item)this.theItemV.get(row);
        return null;
    }
    public OrderItemDrug getOrderItemDrug(){
        return this.pDOrder1.getOrderItemDrug();
    }
   
    /** เรียกใช้เมื่อ Double-Click ที่รายการ */
    private void doubleClickListLab(OrderItem oi)
    {
        Constant.println("doubleClickListLab") ;
        try
        {
            long clickTime = System.currentTimeMillis();
            long clickInterval = clickTime - firstClickTime;
            if(clickInterval < 400)
            {
                firstClickTime = 0;
                if (oi.isLab()
                && (oi.status.equals("4")
                || oi.status.equals("2")))
                {
                    theHD.showDialogShowLabResult(oi);
                }
            }
            else
            {
                firstClickTime = clickTime;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
    }
    /** เรียกใช้เมื่อ Double-Click ที่รายการ */
    private void doubleClickListXray(OrderItem oi)
    {
        try
        {
            long clickTime = System.currentTimeMillis();
            long clickInterval = clickTime - firstClickTime;
            if(clickInterval < 400)
            {
                firstClickTime = 0;
                if(oi.isXray()
                && (oi.status.equals("2")
                || oi.status.equals("4")))
                {
                    theHD.showDialogResultXray(oi);
                }
            }
            else
            {
                firstClickTime = clickTime;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
    }
    
    private void doubleClickList(java.awt.event.MouseEvent evt)
    {
        if(evt.getClickCount()==2)
        {
            OrderItem oi = pDOrder1.getOrderItem();
            if(oi.isLab() && "1".equals(oi.secret))
                    return;
            this.pDOrder1.saveOrderItem();
        }
    }
    /*ManageVisit*/
    public void notifyAdmitVisit(String str, int status)
    {
        setVisit(theHO.theVisit);
        jButtonRefreshActionPerformed(null);
    }
    /*คลิกเลือกผู้ป่วยจากจุดบริการ
     */
    public void notifyReadVisit(String str, int status)
    {
        setVisit(theHO.theVisit);
        Vector v_item_service = theHC.theVitalControl.listItemByDxTemplate();
        if(!v_item_service.isEmpty())
            setItemV(v_item_service);
        this.jTableOrderList.clearSelection();
        jButtonRefreshActionPerformed(null);
        if(!jCheckBoxPeriod.isSelected())
        {
            dateComboBoxStart.setEnabled(false);
            dateComboBoxEnd.setEnabled(false);
        }
    }
    
    public void notifyObservVisit(String str, int status)
    {
        
    }
    
    /*ปลดล็อกผู้ป่วย*/
    public void notifyUnlockVisit(String str, int status)
    {
        setVisit(null);        
    }
    
    public void notifyVisitPatient(String str, int status)
    {
        jButtonRefreshActionPerformed(null);
        setVisit(theHO.theVisit);
    }
    
    public void notifyCheckDoctorTreament(String str, int status)
    {
    }
    
    public void notifyManageAppointment(String str, int status)
    {
    }
    
    public void notifyManageDrugAllergy(String str, int status)
    {
        //amp:04/04/2549
        jButtonRefreshActionPerformed(null);
    }
    
    public void notifySavePatientPayment(String str, int status)
    {
    }
    
    /*ManageOrder henbe_comment_checkusage*/
    public void notifyCheckAutoOrder(String str, int status)
    {
        jButtonRefreshActionPerformed(null);
    }
    
    public void notifyReceiveReturnDrug(String str, int status)
    {
    }
    
    public void notifyDoctorOffDrug(String DoctorId, int status)
    {
        int[] rows = getCurrentRowsOrder();
        jButtonRefreshActionPerformed(null);
        //setCurrentRowsOrder(rows);
    }
    
    public void notifySaveOrderItem(String str, int status)
    {
        int[] rows = getCurrentRowsOrder();
        jButtonRefreshActionPerformed(null);
        //setCurrentRowsOrder(rows);
        jTextFieldKeyword.requestFocus();
        jTextFieldKeyword.selectAll();
    }
    
    /*ManageBilling*/
    public void notifyBillingInvoice(String str, int status)
    {
        // int[] rows = getCurrentRowsOrder();
        jButtonRefreshActionPerformed(null);
        //setCurrentRowsOrder(this.selectedrows);/*จำรายการตรวจรักษาที่เลือกไว้ pu*/
    }
    //henbe_comment check usage
    public void notifyBillingInvoiceComplete(String str, int status)
    {
        setVisit(theHO.theVisit);
    }
    
    public void notifyCalculateAllBillingInvoice(String str, int status)
    {
    }
    
    public void notifyListBilling(Vector bill)
    {
    }
    
    public void notifyRefreshDebt(boolean debt)
    {
    }
    
    public void notifyCancelBillingInvoice(String str, int status)
    {
        jButtonRefreshActionPerformed(null);
    }
    public void notifyDischargeDoctor(String str, int status)
    {
        setVisit(theHO.theVisit);
    }
    
    public void notifyVerifyOrderItem(String str, int status)
    {
        int[] rows = getCurrentRowsOrder();
        jButtonRefreshActionPerformed(null);
        //setCurrentRowsOrder(rows);
    }
    
    
    public void notifyPatientPaidMoney(String str, int status)
    {
    }
    
    public void notifyDropVisit(String str, int status)
    {
        setVisit(theHO.theVisit);
    }
    
    public void notifySendVisit(String str, int status)
    {
        setVisit(theHO.theVisit);
    }
    
    public void notifyDischargeFinancial(String str, int status)
    {
        setVisit(theHO.theVisit);
    }
    
    public void notifyManagePayment(String str, int status)
    {
    }
    
    public void notifyCancelBill(String str, int status)
    {
    }
    
    public void notifyReverseFinancial(String str, int status)
    {
        setVisit(theHO.theVisit);
    }
    
    public void notifyReverseDoctor(String str, int status)
    {
    }
    
    public void notifyDeleteVisitPayment(String str, int status)
    {
    }
    
    public void notifyDeletePatientPayment(String str, int status)
    {
    }
    
    public void notifySaveOrderItemInLab(String str, int status)
    {
    }
    
    public void notifyDispenseOrderItem(String str, int status)
    {
        jButtonRefreshActionPerformed(null);
    }
    
    public void notifyCancelOrderItem(String str, int status)
    {
        jButtonRefreshActionPerformed(null);
    }
    
    public void notifyExecuteOrderItem(String str, int status)
    {
        int[] rows = getCurrentRowsOrder();
        jButtonRefreshActionPerformed(null);
        //setCurrentRowsOrder(rows);
    }
    
    public void notifyContinueOrderItem(String str, int status)
    {
        int[] rows = getCurrentRowsOrder();
        jButtonRefreshActionPerformed(null);
        //setCurrentRowsOrder(rows);
    }
    
    public void notifySaveListOrderItemLab(String msg, int status)
    {
    }
    
    public void notifyReferOutLab(String msg, int status)
    {
    }
    
    public void notifyReadPatient(String str, int status)
    {
        setVisit(theHO.theVisit);
    }
    public void notifyReadFamily(String str, int status)
    {
        setVisit(theHO.theVisit);
    }
    
    public void notifySavePatient(String str, int status)
    {
    }
    
    public void notifyDeletePatient(String str, int status)
    {
    }
    
    public void notifyPreviewAppointmentList(String str, int status)
    {
    }
    
    public void notifyPreviewSelectDrugList(String str, int status)
    {
        // henbe ไม่ได้ใช้แล้ว printing ตาม Pattern แล้วครับ
        //printSelectDrugList(preview);
    }
    
    public void notifyPrintAppointmentList(String str, int status)
    {
    }
    
    public void notifyPrintChronicList(String str, int status)
    {
    }
    
    public void notifyPrintDrugSticker(String str, int status)
    {
//        //pu : 20/07/2549 พิมพ์สติ๊กเกอร์ย โดยไม่ต้องเลือกผู้ป่วย
//        if(this.theHO.thePatient == null ||  this.theHO.theVisit == null )
//        {
//            int row = jTableItemList.getSelectedRow();
//            if(row==-1) return;
//            Item item_order = (Item)theItemV.get(row);
//            Drug drug = theHC.theOrderControl.listDrugByItem(item_order.getObjectId());            
//            printDrugStickerNotVisit(item_order,getObjOrderItemDrugForSticker(item_order, drug));
//        }
//        else
//        {
//            int[] row_execute = new int[this.vRowExecute.size()];
//            for(int i=0,size=this.vRowExecute.size(); i<size; i++)
//            {
//                row_execute[i] = Integer.parseInt((String)this.vRowExecute.get(i));
//            }
//            printDrugSticker(row_execute);
//        }
    }
    
    public void notifyPrintOPDCard(String str, int status)
    {
    }
    
    public void notifyPriviewChronicList(String str, int status)
    {
    }
    
    public void notifyPrintSelectDrugList(String str, int status)
    {
        // henbe ไม่ได้ใช้แล้ว printing ตาม Pattern แล้วครับ
        //printSelectDrugList(nopreview);
    }
    
    public void notifyPrintSumByBillingGroup(String str, int status)
    {
        //theHC.thePrintControl.printSumByBillingGroup(nopreview, theOrderItemV);
    }
    
    public void notifyPreviewSumByBillingGroup(String str, int status)
    {
        //theHC.thePrintControl.printSumByBillingGroup(preview, theOrderItemV);
    }
    
    public void notifyRemainDoctorDischarge(String str, int status)
    {
        setVisit(null);
    }
    
    public void notifySendVisitBackWard(String str, int status)
    {
        setVisit(null);
    }
    
    public void notifySaveReturnDrug(String str, int status)
    {
        int[] rows = getCurrentRowsOrder();
        jButtonRefreshActionPerformed(null);
        //setCurrentRowsOrder(rows);
    }
    
    public void notifySaveOrderRequest(String str, int status)
    {
        jButtonRefreshActionPerformed(null);
    }
    
    public void notifySaveAppointment(String str, int status)
    {
    }
    
    public void notifyReverseAdmit(String str, int status)
    {
        setVisit(theHO.theVisit);
        jButtonRefreshActionPerformed(null);   
    }
    
    public void notifyResetPatient(String str, int status)
    {
        setVisit(null);
    }
    
    public void notifyAddLabReferIn(String str, int status)
    {
    }
    
    public void notifyAddLabReferOut(String str, int status)
    {
    }
    
    public void notifyDeleteFilmXray(String str, int status)
    {
    }
    
    public void notifyDeleteLabOrder(String str, int status)
    {
    }
    
    public void notifyDeleteLabResult(String str, int status)
    {
    }
    
    public void notifyDeleteResultXray(String str, int status)
    {
    }
    
    public void notifyDeleteXrayPosition(String str, int status)
    {
    }
    
    public void notifyManagePatientLabReferIn(String str, int status)
    {
    }
    
    public void notifyReportResultLab(String str, int status)
    {
    }
    
    public void notifySaveFilmXray(String str, int status)
    {
    }
    
    public void notifySaveLabResult(String str, int status)
    {
        Constant.println("public void notifySaveLabResult(String str, int status) {");
        int[] rows = getCurrentRowsOrder();
        jButtonRefreshActionPerformed(null);
        //setCurrentRowsOrder(rows);
    }
    
    public void notifySaveRemainLabResult(String str, int status)
    {
    }
    
    public void notifySaveResultXray(String str, int status)
    {
    }
    
    public void notifySaveXrayPosition(String str, int status)
    {
    }
    
    public void notifySendResultLab(String str, int status)
    {
    }
    
    public void notifyXrayReportComplete(String str, int status)
    {
        setVisit(theHO.theVisit);
        jButtonRefreshActionPerformed(null);
    }
    
    public void notifyDeleteQueueLab(String str, int status)
    {
    }
    
    public boolean execute(Object str)
    {
        Constant.println("public boolean execute(Object str) {" + str);
        int[] row = this.jTableOrderList.getSelectedRows();
        Constant.println(str.toString());
        if(str.toString().startsWith("req"))
        {
            return theHC.theOrderControl.saveOrderRequest(theOrderItemV,row
                    ,str.toString().equals("req1"));
        }
        
        if(str.toString().startsWith("con"))
        {
            return theHC.theOrderControl.saveOrderRequest(theOrderItemV,row
                    ,str.toString().equals("con1"));
        }
        
        if(str.toString().startsWith("home"))
        {
            return theHC.theOrderControl.saveOrderHome(theOrderItemV,row
                    ,str.toString().equals("home1"));
        }
        return false;
    }
    
    public void notifySaveBorrowFilmXray(String str, int status)
    {
    }
    public static void main(String[] argc)
    {


//        Config.setLookAndFeel("Aero");
//                Config.setLookAndFeel("Aluminium");
//                Config.setLookAndFeel("Bernstein");
//                Config.setLookAndFeel("Fast");
//                Config.setLookAndFeel("HiFi");
//                Config.setLookAndFeel("Luna");
//                Config.setLookAndFeel("McWin");
//                Config.setLookAndFeel("Mint");
//                Config.setLookAndFeel("Noire");
                Config.setLookAndFeel("Smart");
//        Config.setLookAndFeel("JTattoo");
        JFrame jf = new JFrame();
        jf.getContentPane().add(new PanelOrder());
        jf.pack();
        jf.setVisible(true);
    }
    
    public void notifyAddDxTemplate(String str, int status)
    {
    }
    
    public void notifyAddPrimarySymptom(String str, int status)
    {
    }
    
    public void notifyDeleteMapVisitDxByVisitId(String str, int status)
    {
    }
    
    public void notifyManagePhysicalExam(String str, int status)
    {
    }
    
    public void notifyManagePrimarySymptom(String str, int status)
    {
    }
    
    public void notifyManageVitalSign(String str, int status)
    {
    }
    
    public void notifySaveDiagDoctor(String str, int status)
    {
        setVisit(theHO.theVisit);
        setItemV(theHC.theVitalControl.listItemByDxTemplate());
        jButtonRefreshActionPerformed(null);

    }
}
