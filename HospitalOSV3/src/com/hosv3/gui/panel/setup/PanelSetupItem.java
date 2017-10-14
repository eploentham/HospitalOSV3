/*
 * PanelSetupOrderItem.java
 *
 * Created on 14 ตุลาคม 2546, 14:44 น.
 */
package com.hosv3.gui.panel.setup;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import com.hosv3.usecase.setup.*;
import com.hosv3.control.*;
import com.hosv3.control.lookup.*;
import com.hosv3.utility.*;
import com.hosv3.gui.dialog.*;
import com.hosv3.subject.*;
import com.hosv3.utility.connection.*;
import com.hospital_os.utility.TaBleModel;
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.Gutil;
import com.hosv3.utility.Constant;
import com.hospital_os.object.*;
import com.hospital_os.utility.ComboFix;
import javax.swing.JFrame;

/**
 *
 * @Panel author : amp 
 */
public class PanelSetupItem extends javax.swing.JPanel implements ManageOptionReq {

    LookupControl theLookupControl;
    UpdateStatus theUS;
    SetupControl theSetupControl;
    SetupSubject theSetupSubject;
    ComboboxModel theComboboxModel;
    HosSubject theHS;
    Item theItem = new Item();
    ItemPrice theItemPrice;
    Drug theDrug = new Drug();
    LabResultItem theLabResultItem = new LabResultItem();
    LabGroup theLabGroup = new LabGroup();
    Vector vItemPrice = new Vector();
    Vector vItem_service = new Vector();
    ItemService theItemService = null;
    Vector vItem = new Vector();
    Vector vLabSet = new Vector();
    PanelSetupSearchSub psss;
    HosControl theHC;
    int offset = 20;
    int next = 0;
    int prev = 0;
    int saved = 0; // 0 คือ ไม่สามารถ insertได้ 1 คือ insert ได้ 
    int category = 0;
    /** pu : 25/07/2549 : เก็บ Index ของ Item ตัวล่างสุดของหน้าปัจจุบัน*/
    int curNext = 0;
    /** pu : 25/07/2549 : เก็บ Index ของ Item ตัวล่างสุดของหน้าก่อนหน้าปัจจุบัน*/
    int curPrev = 0;
    String[] col = {"รหัส", "ชื่อ"};
    String[] col_list = {"รายการ"};
    int currentRow = 0;
    //amp:13/03/2549 เพื่อใช้ตรวจสอบว่ามีการย้ายกลุ่มจากยาเป็นอย่างอื่นหรือไม่
    boolean is_drug = false;

    public PanelSetupItem() {
        initComponents();
        setLanguage();
    }

    public PanelSetupItem(HosControl hc, UpdateStatus us) {
        initComponents();
        setLanguage();
        setControl(hc, us);
    }

    public static void main(String[] argc) {
        HosControl theHC = new HosControl();
        PanelSetupItem panelSetupItem = new PanelSetupItem(theHC, null);
        JFrame jf = new JFrame();
        jf.getContentPane().add(panelSetupItem);
        jf.setVisible(true);
    }
    /////////////////////Use this for decrease memory usage

    public void setControl(HosControl hc, UpdateStatus us) {
        theUS = us;
        theHC = hc;
        theHS = hc.theHS;
        this.jPanelDrugDescription.setControl(hc, us);
        theLookupControl = hc.theLookupControl;
        theSetupControl = hc.theSetupControl;
        theSetupSubject = hc.theHS.theSetupSubject;
        hc.theHS.theSetupSubject.addForLiftAttach(this);
        setupLookup();
        setEnabled(false);

        theSetupSubject.addItem(this);
        theSetupSubject.addItemLab(this);
        theSetupSubject.addpanelrefrash(this);

        jTabbedPane1.remove(jPanel9);
        dateTextField.setEditable(true);
        jTextFieldIcdCode.setControl(new Icd9Lookup(theHC.theLookupControl), theUS.getJFrame());
        balloonTextArea1.setJFrame(theUS.getJFrame());
        balloonTextArea1.setControl(new Icd9Lookup(theHC.theLookupControl));
        ComboboxModel.initComboBox(jComboBoxSpecified, theHC.theLookupControl.listOrderSpecified());
        jTextFieldIcdCode.setEControl(new Icd9Lookup(theHC.theSetupControl));
        theHS.theBalloonSubject.attachBalloon(jTextFieldIcdCode);
        theHS.theBalloonSubject.attachBalloon(balloonTextArea1);
        jTextFieldDescription.setEditable(false);
        jTextFieldIcdCode.setVisible(false);
    }

    /**
     *@Author : amp
     *@date : 29/02/2549
     *@see : จัดการเกี่ยวกับภาษา    
     */
    private void setLanguage() {
        for (int i = 0; i < this.getComponentCount(); i++) {
            GuiLang.setLanguage(this.getComponent(i));
        }
    }

    public void setupLookup() {
        Vector catagolyitemgroup = theLookupControl.listCategoryGroupItem();
        ComboboxModel.initComboBox(jComboBoxSCategory, catagolyitemgroup);
        ComboboxModel.initComboBox(jComboBoxOrderList, catagolyitemgroup);
        Vector billingitemgroup = theLookupControl.listBillingGroupItem();
        ComboboxModel.initComboBox(jComboBoxReceiptList, billingitemgroup);
        Vector labresulttype = theLookupControl.listLabResultGroup();
        ComboboxModel.initComboBox(jComboBoxList, labresulttype);
        Vector standarditemgroup = theLookupControl.listItem16Group();
        ComboboxModel.initComboBox(jComboBoxStandardGroup, standarditemgroup);
        jComboBoxPlan.setControl(null, new PlanLookup(theHC.theLookupControl), new Plan());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroupLab = new javax.swing.ButtonGroup();
        buttonGroupLabResultType = new javax.swing.ButtonGroup();
        fontFormatTitle1 = new com.hospital_os.gui.font.FontFormatTitle();
        buttonGroupBeginWith = new javax.swing.ButtonGroup();
        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroupNormalValue = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTextFieldSCode = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jCheckBoxS = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new com.hosv3.gui.component.HJTableSort();
        jRadioButtonBegin = new javax.swing.JRadioButton();
        jRadioButtonConsist = new javax.swing.JRadioButton();
        jPanel17 = new javax.swing.JPanel();
        jCheckBoxSearchGroup = new javax.swing.JCheckBox();
        jComboBoxSCategory = new javax.swing.JComboBox();
        jButtonPrev = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaCommonName = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaTradeName = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaNickName = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxOrderList = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxReceiptList = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel37 = new javax.swing.JLabel();
        jComboBoxStandardGroup = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new com.hosv3.gui.component.HJTableSort();
        jPanel11 = new javax.swing.JPanel();
        jButtonAddPrice = new javax.swing.JButton();
        jButtonDelPrice = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        doubleTextFieldPriceCost = new com.hospital_os.utility.DoubleTextField();
        jTextFieldPrice = new com.hospital_os.utility.DoubleTextField();
        dateTextField = new com.hospital_os.utility.DateComboBox();
        jCheckBoxPlan = new javax.swing.JCheckBox();
        jComboBoxPlan = new com.hosv3.gui.component.HosComboBox();
        jPanel7 = new javax.swing.JPanel();
        jPanelBlank = new javax.swing.JPanel();
        jPanelLabDescription = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jRadioButtonLabDetail = new javax.swing.JRadioButton();
        jRadioButtonLabGroup = new javax.swing.JRadioButton();
        jComboBoxLabRpGroup = new javax.swing.JComboBox();
        jPanel22 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jCheckBoxSecret = new javax.swing.JCheckBox();
        jCheckBoxNCDFBS = new javax.swing.JCheckBox();
        jCheckBoxNCDHCT = new javax.swing.JCheckBox();
        jPanel30 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldLabDetail = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jTextFieldLabUnit = new javax.swing.JTextField();
        jPanel31 = new javax.swing.JPanel();
        jRadioButtonNumber = new javax.swing.JRadioButton();
        jRadioButtonPointNumber = new javax.swing.JRadioButton();
        jRadioButtonText = new javax.swing.JRadioButton();
        jRadioButtonTexts = new javax.swing.JRadioButton();
        jPanelDefaultValue = new javax.swing.JPanel();
        jComboBoxList = new javax.swing.JComboBox();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTableList = new com.hosv3.gui.component.HJTableSort();
        jRadioButtonList = new javax.swing.JRadioButton();
        jPanel32 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jTextFieldMin = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jTextFieldMax = new javax.swing.JTextField();
        jRadioButtonInPeriod = new javax.swing.JRadioButton();
        jRadioButtonInSQL = new javax.swing.JRadioButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextFieldDefaultValue = new javax.swing.JTextArea();
        jPanel10 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable4 = new com.hosv3.gui.component.HJTableSort();
        jPanel51 = new javax.swing.JPanel();
        jButtonDelLabItem = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanelIcdCode = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jTextFieldDescription = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldIcdCode = new com.hosv3.gui.component.BalloonTextField();
        jScrollPane14 = new javax.swing.JScrollPane();
        balloonTextArea1 = new com.hosv3.gui.component.BalloonTextArea();
        jComboBoxSpecified = new com.hosv3.gui.component.HosComboBox();
        jLabel38 = new javax.swing.JLabel();
        jPanelDrugDescription = new com.hosv3.gui.panel.detail.PDItemDrug();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable3 = new com.hosv3.gui.component.HJTableSort();
        jPanel111 = new javax.swing.JPanel();
        jButtonAddPrice1 = new javax.swing.JButton();
        jButtonDelPrice1 = new javax.swing.JButton();
        jButtonSavePrice1 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButtonPrev1 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonDel = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(fontFormatTitle1);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/hosv3/property/thai"); // NOI18N
        jLabel4.setText(bundle.getString("ITEM")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jPanel4.add(jLabel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanel4, gridBagConstraints);

        jPanel3.setMinimumSize(new java.awt.Dimension(300, 25));
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 404));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jTextFieldSCode.setFont(defaultFont1);
        jTextFieldSCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSCodeActionPerformed(evt);
            }
        });
        jTextFieldSCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSCodeKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 3, 5);
        jPanel3.add(jTextFieldSCode, gridBagConstraints);

        jButtonSearch.setFont(defaultFont1);
        jButtonSearch.setText(bundle.getString("SEARCH")); // NOI18N
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel3.add(jButtonSearch, gridBagConstraints);

        jCheckBoxS.setFont(defaultFont1);
        jCheckBoxS.setSelected(true);
        jCheckBoxS.setText(bundle.getString("ACTIVE")); // NOI18N
        jCheckBoxS.setMaximumSize(new java.awt.Dimension(1, 1));
        jCheckBoxS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel3.add(jCheckBoxS, gridBagConstraints);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(100, 22));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 80));

        jTable1.setFont(defaultFont1);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(jScrollPane1, gridBagConstraints);

        buttonGroupBeginWith.add(jRadioButtonBegin);
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
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel3.add(jRadioButtonBegin, gridBagConstraints);

        buttonGroupBeginWith.add(jRadioButtonConsist);
        jRadioButtonConsist.setFont(defaultFont1);
        jRadioButtonConsist.setSelected(true);
        jRadioButtonConsist.setText("ประกอบ");
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
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel3.add(jRadioButtonConsist, gridBagConstraints);

        jPanel17.setLayout(new java.awt.GridBagLayout());

        jCheckBoxSearchGroup.setFont(defaultFont1);
        jCheckBoxSearchGroup.setText("กลุ่ม");
        jCheckBoxSearchGroup.setMaximumSize(new java.awt.Dimension(1, 1));
        jCheckBoxSearchGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSearchGroupActionPerformed(evt);
            }
        });
        jCheckBoxSearchGroup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxSearchGroupMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel17.add(jCheckBoxSearchGroup, gridBagConstraints);

        jComboBoxSCategory.setFont(defaultFont1);
        jComboBoxSCategory.setEnabled(false);
        jComboBoxSCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSCategoryActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel17.add(jComboBoxSCategory, gridBagConstraints);

        jButtonPrev.setFont(defaultFont1);
        jButtonPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Back16.gif"))); // NOI18N
        jButtonPrev.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonPrev.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonPrev.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrevActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel17.add(jButtonPrev, gridBagConstraints);

        jButtonNext.setFont(defaultFont1);
        jButtonNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Forward16.gif"))); // NOI18N
        jButtonNext.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonNext.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonNext.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel17.add(jButtonNext, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(jPanel17, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        add(jPanel3, gridBagConstraints);

        jPanel2.setMinimumSize(new java.awt.Dimension(350, 400));
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 400));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jTabbedPane1.setFont(defaultFont1);
        jTabbedPane1.setMaximumSize(new java.awt.Dimension(350, 420));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(350, 420));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(350, 420));

        jPanel5.setFont(defaultFont1);
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(defaultFont1);
        jLabel1.setText(bundle.getString("CODE")); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(1, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(jLabel1, gridBagConstraints);

        jTextFieldCode.setBackground(new java.awt.Color(204, 255, 255));
        jTextFieldCode.setFont(defaultFont1);
        jTextFieldCode.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel5.add(jTextFieldCode, gridBagConstraints);

        jLabel2.setFont(defaultFont1);
        jLabel2.setText(bundle.getString("ITEM_COMMON_NAME")); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(1, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(jLabel2, gridBagConstraints);

        jScrollPane2.setMaximumSize(new java.awt.Dimension(150, 48));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(150, 48));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(150, 45));

        jTextAreaCommonName.setBackground(new java.awt.Color(204, 255, 255));
        jTextAreaCommonName.setFont(defaultFont1);
        jTextAreaCommonName.setLineWrap(true);
        jTextAreaCommonName.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextAreaCommonName);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel5.add(jScrollPane2, gridBagConstraints);

        jLabel3.setFont(defaultFont1);
        jLabel3.setText(bundle.getString("ITEM_TRADE_NAME")); // NOI18N
        jLabel3.setMaximumSize(new java.awt.Dimension(1, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(jLabel3, gridBagConstraints);

        jScrollPane3.setMaximumSize(new java.awt.Dimension(150, 48));
        jScrollPane3.setMinimumSize(new java.awt.Dimension(150, 48));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(150, 45));

        jTextAreaTradeName.setFont(defaultFont1);
        jTextAreaTradeName.setLineWrap(true);
        jTextAreaTradeName.setWrapStyleWord(true);
        jScrollPane3.setViewportView(jTextAreaTradeName);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel5.add(jScrollPane3, gridBagConstraints);

        jLabel5.setFont(defaultFont1);
        jLabel5.setText(bundle.getString("ITEM_NICK_NAME")); // NOI18N
        jLabel5.setMaximumSize(new java.awt.Dimension(1, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(jLabel5, gridBagConstraints);

        jScrollPane4.setMaximumSize(new java.awt.Dimension(150, 48));
        jScrollPane4.setMinimumSize(new java.awt.Dimension(150, 48));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(150, 45));

        jTextAreaNickName.setFont(defaultFont1);
        jTextAreaNickName.setLineWrap(true);
        jTextAreaNickName.setWrapStyleWord(true);
        jScrollPane4.setViewportView(jTextAreaNickName);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel5.add(jScrollPane4, gridBagConstraints);

        jLabel6.setFont(defaultFont1);
        jLabel6.setText(bundle.getString("LIST_ORDER")); // NOI18N
        jLabel6.setMaximumSize(new java.awt.Dimension(1, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(jLabel6, gridBagConstraints);

        jComboBoxOrderList.setFont(defaultFont1);
        jComboBoxOrderList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxOrderListMouseClicked(evt);
            }
        });
        jComboBoxOrderList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxOrderListItemStateChanged(evt);
            }
        });
        jComboBoxOrderList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxOrderListActionPerformed(evt);
            }
        });
        jComboBoxOrderList.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBoxOrderListFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(jComboBoxOrderList, gridBagConstraints);

        jLabel7.setFont(defaultFont1);
        jLabel7.setText(bundle.getString("LIST_RECEIPT")); // NOI18N
        jLabel7.setMaximumSize(new java.awt.Dimension(1, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(jLabel7, gridBagConstraints);

        jComboBoxReceiptList.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(jComboBoxReceiptList, gridBagConstraints);

        jCheckBox1.setFont(defaultFont1);
        jCheckBox1.setText(bundle.getString("ACTIVE")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(jCheckBox1, gridBagConstraints);

        jTextArea1.setEditable(false);
        jTextArea1.setLineWrap(true);
        jTextArea1.setText("รายการตรวจรักษานี้ไม่สามารถลบออกจากฐานข้อมูลได้\nหากไม่ต้องการ ให้เลือก active ออก");
        jTextArea1.setWrapStyleWord(true);
        jScrollPane12.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(jScrollPane12, gridBagConstraints);

        jLabel37.setFont(defaultFont1);
        jLabel37.setText("16 กลุ่มมาตรฐาน");
        jLabel37.setMaximumSize(new java.awt.Dimension(1, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(jLabel37, gridBagConstraints);

        jComboBoxStandardGroup.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel5.add(jComboBoxStandardGroup, gridBagConstraints);

        jTabbedPane1.addTab(bundle.getString("MEDSUPPLY"), jPanel5); // NOI18N

        jPanel6.setFont(defaultFont1);
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jScrollPane5.setMinimumSize(new java.awt.Dimension(250, 150));
        jScrollPane5.setPreferredSize(new java.awt.Dimension(250, 150));
        jScrollPane5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jScrollPane5MouseReleased(evt);
            }
        });

        jTable2.setFont(defaultFont1);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable2MouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(jTable2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 3, 3, 3);
        jPanel6.add(jScrollPane5, gridBagConstraints);

        jPanel11.setLayout(new java.awt.GridBagLayout());

        jButtonAddPrice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAddPrice.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonAddPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddPriceActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel11.add(jButtonAddPrice, gridBagConstraints);

        jButtonDelPrice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelPrice.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonDelPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelPriceActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel11.add(jButtonDelPrice, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(8, 3, 0, 0);
        jPanel6.add(jPanel11, gridBagConstraints);

        jPanel26.setLayout(new java.awt.GridBagLayout());

        jLabel9.setFont(defaultFont1);
        jLabel9.setText(bundle.getString("DATE")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel26.add(jLabel9, gridBagConstraints);

        jLabel13.setFont(defaultFont1);
        jLabel13.setText(bundle.getString("PRICE_BATH")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel26.add(jLabel13, gridBagConstraints);

        jLabel30.setFont(defaultFont1);
        jLabel30.setText("ราคาต้นทุน (บาท)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel26.add(jLabel30, gridBagConstraints);

        doubleTextFieldPriceCost.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        doubleTextFieldPriceCost.setText("0");
        doubleTextFieldPriceCost.setFont(defaultFont1);
        doubleTextFieldPriceCost.setMinimumSize(new java.awt.Dimension(40, 21));
        doubleTextFieldPriceCost.setPreferredSize(new java.awt.Dimension(40, 21));
        doubleTextFieldPriceCost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                doubleTextFieldPriceCostKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel26.add(doubleTextFieldPriceCost, gridBagConstraints);

        jTextFieldPrice.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldPrice.setFont(defaultFont1);
        jTextFieldPrice.setMinimumSize(new java.awt.Dimension(40, 21));
        jTextFieldPrice.setPreferredSize(new java.awt.Dimension(40, 21));
        jTextFieldPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPriceKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel26.add(jTextFieldPrice, gridBagConstraints);

        dateTextField.setFont(defaultFont1);
        dateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateTextFieldActionPerformed(evt);
            }
        });
        dateTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateTextFieldFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel26.add(dateTextField, gridBagConstraints);

        jCheckBoxPlan.setFont(defaultFont1);
        jCheckBoxPlan.setText("ตามสิทธิ");
        jCheckBoxPlan.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBoxPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPlanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel26.add(jCheckBoxPlan, gridBagConstraints);

        jComboBoxPlan.setEnabled(false);
        jComboBoxPlan.setFont(defaultFont1);
        jComboBoxPlan.setMinimumSize(new java.awt.Dimension(121, 24));
        jComboBoxPlan.setPreferredSize(new java.awt.Dimension(121, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel26.add(jComboBoxPlan, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel6.add(jPanel26, gridBagConstraints);

        jTabbedPane1.addTab(bundle.getString("PRICE"), jPanel6); // NOI18N

        jPanel7.setFont(defaultFont1);
        jPanel7.setLayout(new java.awt.CardLayout());

        jPanelBlank.setLayout(new java.awt.GridBagLayout());
        jPanel7.add(jPanelBlank, "Blank");

        jPanelLabDescription.setLayout(new java.awt.GridBagLayout());

        jPanel23.setMinimumSize(new java.awt.Dimension(10, 25));
        jPanel23.setLayout(new java.awt.GridBagLayout());

        buttonGroupLab.add(jRadioButtonLabDetail);
        jRadioButtonLabDetail.setFont(defaultFont1);
        jRadioButtonLabDetail.setSelected(true);
        jRadioButtonLabDetail.setText("LabDetail");
        jRadioButtonLabDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonLabDetailActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel23.add(jRadioButtonLabDetail, gridBagConstraints);

        buttonGroupLab.add(jRadioButtonLabGroup);
        jRadioButtonLabGroup.setFont(defaultFont1);
        jRadioButtonLabGroup.setText("LabGroup");
        jRadioButtonLabGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonLabGroupActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel23.add(jRadioButtonLabGroup, gridBagConstraints);

        jComboBoxLabRpGroup.setFont(defaultFont1);
        jComboBoxLabRpGroup.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "จุลชีววิทยาคลินิก(Microbiology)", "จุลทรรศน์ศาสตร์(Macroscopy)", "ภูมิคุ้มกันวิทยา/อิมมูนวิทยา(Serology)", "ธนาคารเลือด(Blood Bank) ", "เคมีคลีนิค( Chemeclinic)", "โลหิตวิทยาคลินิก (Hemotology)", "เซลล์วิทยาคลินิก (Cytology)" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel23.add(jComboBoxLabRpGroup, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanelLabDescription.add(jPanel23, gridBagConstraints);

        jPanel22.setMinimumSize(new java.awt.Dimension(462, 488));
        jPanel22.setLayout(new java.awt.CardLayout());

        jPanel21.setLayout(new java.awt.GridBagLayout());

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel15.setLayout(new java.awt.GridBagLayout());

        jCheckBoxSecret.setFont(defaultFont1);
        jCheckBoxSecret.setText("ปกปิด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel15.add(jCheckBoxSecret, gridBagConstraints);

        jCheckBoxNCDFBS.setFont(defaultFont1);
        jCheckBoxNCDFBS.setText("ระดับน้ำตาลในเลือด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel15.add(jCheckBoxNCDFBS, gridBagConstraints);

        jCheckBoxNCDHCT.setFont(defaultFont1);
        jCheckBoxNCDHCT.setText("ความเข้มข้นของเม็ดเลือดแดง");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel15.add(jCheckBoxNCDHCT, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel21.add(jPanel15, gridBagConstraints);

        jPanel30.setLayout(new java.awt.GridBagLayout());

        jLabel12.setFont(defaultFont1);
        jLabel12.setText("ชื่อ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel30.add(jLabel12, gridBagConstraints);

        jTextFieldLabDetail.setFont(defaultFont1);
        jTextFieldLabDetail.setMinimumSize(new java.awt.Dimension(77, 21));
        jTextFieldLabDetail.setPreferredSize(new java.awt.Dimension(77, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel30.add(jTextFieldLabDetail, gridBagConstraints);

        jLabel35.setFont(defaultFont1);
        jLabel35.setText("หน่วย");
        jLabel35.setAutoscrolls(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel30.add(jLabel35, gridBagConstraints);

        jTextFieldLabUnit.setFont(defaultFont1);
        jTextFieldLabUnit.setMinimumSize(new java.awt.Dimension(50, 21));
        jTextFieldLabUnit.setPreferredSize(new java.awt.Dimension(50, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel30.add(jTextFieldLabUnit, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel21.add(jPanel30, gridBagConstraints);

        jPanel31.setLayout(new java.awt.GridBagLayout());

        buttonGroupLabResultType.add(jRadioButtonNumber);
        jRadioButtonNumber.setFont(defaultFont1);
        jRadioButtonNumber.setText("ตัวเลขจำนวนเต็ม");
        jRadioButtonNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNumberActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel31.add(jRadioButtonNumber, gridBagConstraints);

        buttonGroupLabResultType.add(jRadioButtonPointNumber);
        jRadioButtonPointNumber.setFont(defaultFont1);
        jRadioButtonPointNumber.setText("ตัวเลขทศนิยม");
        jRadioButtonPointNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPointNumberActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel31.add(jRadioButtonPointNumber, gridBagConstraints);

        buttonGroupLabResultType.add(jRadioButtonText);
        jRadioButtonText.setFont(defaultFont1);
        jRadioButtonText.setSelected(true);
        jRadioButtonText.setText("ข้อความ 1 บรรทัด");
        jRadioButtonText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTextActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel31.add(jRadioButtonText, gridBagConstraints);

        buttonGroupLabResultType.add(jRadioButtonTexts);
        jRadioButtonTexts.setFont(defaultFont1);
        jRadioButtonTexts.setText("หลายบรรทัด");
        jRadioButtonTexts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTextsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel31.add(jRadioButtonTexts, gridBagConstraints);

        jPanelDefaultValue.setLayout(new java.awt.GridBagLayout());

        jComboBoxList.setFont(defaultFont1);
        jComboBoxList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxListActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        jPanelDefaultValue.add(jComboBoxList, gridBagConstraints);

        jTableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableList.setFont(defaultFont1);
        jScrollPane13.setViewportView(jTableList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelDefaultValue.add(jScrollPane13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel31.add(jPanelDefaultValue, gridBagConstraints);

        buttonGroupLabResultType.add(jRadioButtonList);
        jRadioButtonList.setFont(defaultFont1);
        jRadioButtonList.setText("มีผลแลบย่อย");
        jRadioButtonList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonListActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel31.add(jRadioButtonList, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel21.add(jPanel31, gridBagConstraints);

        jPanel32.setMinimumSize(new java.awt.Dimension(362, 125));
        jPanel32.setPreferredSize(new java.awt.Dimension(579, 122));
        jPanel32.setLayout(new java.awt.GridBagLayout());

        jLabel33.setFont(defaultFont1);
        jLabel33.setText("ค่าปกติ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel32.add(jLabel33, gridBagConstraints);

        jTextFieldMin.setFont(defaultFont1);
        jTextFieldMin.setMinimumSize(new java.awt.Dimension(40, 21));
        jTextFieldMin.setPreferredSize(new java.awt.Dimension(40, 21));
        jTextFieldMin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldMinFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel32.add(jTextFieldMin, gridBagConstraints);

        jLabel34.setFont(defaultFont1);
        jLabel34.setText("ถึง");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel32.add(jLabel34, gridBagConstraints);

        jTextFieldMax.setFont(defaultFont1);
        jTextFieldMax.setMinimumSize(new java.awt.Dimension(40, 21));
        jTextFieldMax.setPreferredSize(new java.awt.Dimension(40, 21));
        jTextFieldMax.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldMaxFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel32.add(jTextFieldMax, gridBagConstraints);

        buttonGroupNormalValue.add(jRadioButtonInPeriod);
        jRadioButtonInPeriod.setText("อยู่ในช่วง");
        jRadioButtonInPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonInPeriodActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel32.add(jRadioButtonInPeriod, gridBagConstraints);

        buttonGroupNormalValue.add(jRadioButtonInSQL);
        jRadioButtonInSQL.setText("ตามเงื่อนไข SQL");
        jRadioButtonInSQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonInSQLActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel32.add(jRadioButtonInSQL, gridBagConstraints);

        jTextFieldDefaultValue.setColumns(20);
        jTextFieldDefaultValue.setLineWrap(true);
        jTextFieldDefaultValue.setRows(5);
        jTextFieldDefaultValue.setText("select \ncase when visit_age<'10' then '0' \n        when visit_age > '10' then '1'\n        else '2' end as min\n,case when visit_age<'10' then '10' \n        when visit_age > '10' then '11'\n        else '20' end as max \nfrom t_visit where t_visit_id = ?");
        jTextFieldDefaultValue.setWrapStyleWord(true);
        jScrollPane11.setViewportView(jTextFieldDefaultValue);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel32.add(jScrollPane11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel21.add(jPanel32, gridBagConstraints);

        jPanel22.add(jPanel21, "cardlabdetail");

        jPanel10.setFont(defaultFont1);
        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel32.setFont(defaultFont1);
        jLabel32.setText(bundle.getString("SUB_LIST")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel10.add(jLabel32, gridBagConstraints);

        jTable4.setFont(defaultFont1);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable4MouseReleased(evt);
            }
        });
        jScrollPane10.setViewportView(jTable4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel10.add(jScrollPane10, gridBagConstraints);

        jPanel51.setLayout(new java.awt.GridBagLayout());

        jButtonDelLabItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelLabItem.setMaximumSize(new java.awt.Dimension(13, 22));
        jButtonDelLabItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelLabItemActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel51.add(jButtonDelLabItem, gridBagConstraints);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel51.add(jButton3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel10.add(jPanel51, gridBagConstraints);

        jPanel22.add(jPanel10, "cardlabgroup");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelLabDescription.add(jPanel22, gridBagConstraints);

        jPanel7.add(jPanelLabDescription, "LabDescription");

        jPanelIcdCode.setLayout(new java.awt.GridBagLayout());

        jLabel11.setFont(defaultFont1);
        jLabel11.setText("รหัสหัตถการ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 12);
        jPanelIcdCode.add(jLabel11, gridBagConstraints);

        jLabel36.setFont(defaultFont1);
        jLabel36.setText("ชื่อรายการหัตถการ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 12);
        jPanelIcdCode.add(jLabel36, gridBagConstraints);

        jTextFieldDescription.setFont(defaultFont1);
        jTextFieldDescription.setMinimumSize(new java.awt.Dimension(70, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 5);
        jPanelIcdCode.add(jTextFieldDescription, gridBagConstraints);

        jLabel10.setFont(defaultFont1);
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("จับคู่ค่าบริการกับรหัสหัตถการ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelIcdCode.add(jLabel10, gridBagConstraints);

        jTextFieldIcdCode.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanelIcdCode.add(jTextFieldIcdCode, gridBagConstraints);

        jScrollPane14.setMinimumSize(new java.awt.Dimension(104, 61));
        jScrollPane14.setPreferredSize(new java.awt.Dimension(104, 61));

        balloonTextArea1.setWrapStyleWord(true);
        balloonTextArea1.setFont(defaultFont1);
        jScrollPane14.setViewportView(balloonTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanelIcdCode.add(jScrollPane14, gridBagConstraints);

        jComboBoxSpecified.setEnabled(false);
        jComboBoxSpecified.setFont(defaultFont1);
        jComboBoxSpecified.setMinimumSize(new java.awt.Dimension(121, 24));
        jComboBoxSpecified.setPreferredSize(new java.awt.Dimension(121, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanelIcdCode.add(jComboBoxSpecified, gridBagConstraints);

        jLabel38.setFont(defaultFont1);
        jLabel38.setText("ประเภทค่าบริการ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 12);
        jPanelIcdCode.add(jLabel38, gridBagConstraints);

        jPanel7.add(jPanelIcdCode, "ServiceDescription");
        jPanel7.add(jPanelDrugDescription, "DrugDescription");

        jTabbedPane1.addTab(bundle.getString("PANEL_SETUP_ITEM_DETAIL"), jPanel7); // NOI18N

        jPanel9.setFont(defaultFont1);
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jScrollPane9.setViewportView(jTable3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel9.add(jScrollPane9, gridBagConstraints);

        jPanel111.setLayout(new java.awt.GridBagLayout());

        jButtonAddPrice1.setFont(defaultFont1);
        jButtonAddPrice1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 2);
        jPanel111.add(jButtonAddPrice1, gridBagConstraints);

        jButtonDelPrice1.setFont(defaultFont1);
        jButtonDelPrice1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelPrice1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel111.add(jButtonDelPrice1, gridBagConstraints);

        jButtonSavePrice1.setFont(defaultFont1);
        jButtonSavePrice1.setText(bundle.getString("SAVE")); // NOI18N
        jButtonSavePrice1.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonSavePrice1.setPreferredSize(new java.awt.Dimension(60, 26));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel111.add(jButtonSavePrice1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel9.add(jPanel111, gridBagConstraints);

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel21.setFont(defaultFont1);
        jLabel21.setText(bundle.getString("RECEIVE")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel12.add(jLabel21, gridBagConstraints);

        jTextField1.setBackground(new java.awt.Color(204, 204, 204));
        jTextField1.setFont(defaultFont1);
        jTextField1.setMaximumSize(new java.awt.Dimension(48, 24));
        jTextField1.setMinimumSize(new java.awt.Dimension(50, 21));
        jTextField1.setPreferredSize(new java.awt.Dimension(50, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 2);
        jPanel12.add(jTextField1, gridBagConstraints);

        jLabel22.setFont(defaultFont1);
        jLabel22.setText(bundle.getString("PAY")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 2);
        jPanel12.add(jLabel22, gridBagConstraints);

        jTextField2.setBackground(new java.awt.Color(204, 204, 204));
        jTextField2.setFont(defaultFont1);
        jTextField2.setMaximumSize(new java.awt.Dimension(48, 24));
        jTextField2.setMinimumSize(new java.awt.Dimension(50, 21));
        jTextField2.setPreferredSize(new java.awt.Dimension(50, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel12.add(jTextField2, gridBagConstraints);

        jLabel23.setFont(defaultFont1);
        jLabel23.setText(bundle.getString("REMAIN")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 2);
        jPanel12.add(jLabel23, gridBagConstraints);

        jTextField3.setBackground(new java.awt.Color(204, 204, 204));
        jTextField3.setFont(defaultFont1);
        jTextField3.setMaximumSize(new java.awt.Dimension(48, 24));
        jTextField3.setMinimumSize(new java.awt.Dimension(50, 21));
        jTextField3.setPreferredSize(new java.awt.Dimension(50, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 2);
        jPanel12.add(jTextField3, gridBagConstraints);

        jButtonPrev1.setFont(defaultFont1);
        jButtonPrev1.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonPrev1.setPreferredSize(new java.awt.Dimension(24, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel12.add(jButtonPrev1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel9.add(jPanel12, gridBagConstraints);

        jPanel13.setLayout(new java.awt.GridBagLayout());

        jLabel24.setFont(defaultFont1);
        jLabel24.setText(bundle.getString("DATE")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel13.add(jLabel24, gridBagConstraints);

        jTextField4.setFont(defaultFont1);
        jTextField4.setMaximumSize(new java.awt.Dimension(26, 24));
        jTextField4.setMinimumSize(new java.awt.Dimension(50, 21));
        jTextField4.setPreferredSize(new java.awt.Dimension(50, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel13.add(jTextField4, gridBagConstraints);

        jLabel25.setFont(defaultFont1);
        jLabel25.setText("/");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel13.add(jLabel25, gridBagConstraints);

        jTextField5.setFont(defaultFont1);
        jTextField5.setMaximumSize(new java.awt.Dimension(26, 24));
        jTextField5.setMinimumSize(new java.awt.Dimension(50, 21));
        jTextField5.setPreferredSize(new java.awt.Dimension(50, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel13.add(jTextField5, gridBagConstraints);

        jLabel26.setFont(defaultFont1);
        jLabel26.setText("/");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel13.add(jLabel26, gridBagConstraints);

        jTextField6.setFont(defaultFont1);
        jTextField6.setMaximumSize(new java.awt.Dimension(48, 24));
        jTextField6.setMinimumSize(new java.awt.Dimension(50, 21));
        jTextField6.setPreferredSize(new java.awt.Dimension(50, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel13.add(jTextField6, gridBagConstraints);

        jLabel27.setFont(defaultFont1);
        jLabel27.setText(bundle.getString("DATE_RECEIVE")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel13.add(jLabel27, gridBagConstraints);

        jTextField7.setBackground(new java.awt.Color(204, 204, 204));
        jTextField7.setFont(defaultFont1);
        jTextField7.setBorder(null);
        jTextField7.setMaximumSize(new java.awt.Dimension(150, 24));
        jTextField7.setMinimumSize(new java.awt.Dimension(150, 24));
        jTextField7.setPreferredSize(new java.awt.Dimension(150, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel13.add(jTextField7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel9.add(jPanel13, gridBagConstraints);

        jPanel14.setLayout(new java.awt.GridBagLayout());

        jLabel28.setFont(defaultFont1);
        jLabel28.setText(bundle.getString("DRUG_QTY")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel14.add(jLabel28, gridBagConstraints);

        jTextField8.setFont(defaultFont1);
        jTextField8.setMaximumSize(new java.awt.Dimension(150, 24));
        jTextField8.setMinimumSize(new java.awt.Dimension(50, 21));
        jTextField8.setPreferredSize(new java.awt.Dimension(50, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel14.add(jTextField8, gridBagConstraints);

        jComboBox2.setFont(defaultFont1);
        jComboBox2.setMaximumSize(new java.awt.Dimension(150, 24));
        jComboBox2.setMinimumSize(new java.awt.Dimension(150, 24));
        jComboBox2.setPreferredSize(new java.awt.Dimension(150, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel14.add(jComboBox2, gridBagConstraints);

        jLabel29.setFont(defaultFont1);
        jLabel29.setText(bundle.getString("TOTAL")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel14.add(jLabel29, gridBagConstraints);

        jTextField9.setFont(defaultFont1);
        jTextField9.setMaximumSize(new java.awt.Dimension(150, 24));
        jTextField9.setMinimumSize(new java.awt.Dimension(50, 21));
        jTextField9.setPreferredSize(new java.awt.Dimension(50, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel14.add(jTextField9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel9.add(jPanel14, gridBagConstraints);

        jTabbedPane1.addTab(bundle.getString("MED_STOCK"), jPanel9); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jTabbedPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        add(jPanel2, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButtonAdd.setFont(defaultFont1);
        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAdd.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonAdd.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonAdd.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jButtonAdd, gridBagConstraints);

        jButtonDel.setFont(defaultFont1);
        jButtonDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDel.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDel.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDel.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButtonDel, gridBagConstraints);

        jButtonSave.setFont(defaultFont1);
        jButtonSave.setText(bundle.getString("SAVE")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButtonSave, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPlanActionPerformed
        this.jComboBoxPlan.setEnabled(jCheckBoxPlan.isSelected());
    }//GEN-LAST:event_jCheckBoxPlanActionPerformed

    private void jTable2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseReleased
//        vItemPrice = theSetupControl.listItemPrice(theItem.getObjectId());
        int row = jTable2.getSelectedRow();
        if (row != -1) {
            theItemPrice = (ItemPrice) vItemPrice.get(row);
            String date = theItemPrice.active_date.substring(0, 10);
//            date = date.replaceAll("-","/");
            String dateArr[] = date.split("-");
            date = dateArr[2] + "/" + dateArr[1] + "/" + dateArr[0];
            dateTextField.setText(date);
            this.jComboBoxPlan.setText(theItemPrice.item_price_id);
            jCheckBoxPlan.setSelected(false);
            if (theItemPrice.item_price_id != null) {
                this.jCheckBoxPlan.setSelected(theItemPrice.item_price_id.length() > 10);
            }
            this.jComboBoxPlan.setEnabled(jCheckBoxPlan.isSelected());
            this.dateTextField.setEnabled(!jCheckBoxPlan.isSelected());
            jTextFieldPrice.setText(theItemPrice.price);
            doubleTextFieldPriceCost.setText(theItemPrice.price_cost);
        }
    }//GEN-LAST:event_jTable2MouseReleased

    private void jScrollPane5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane5MouseReleased
// TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane5MouseReleased

    private void jRadioButtonNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNumberActionPerformed
        chooseLabType();
    }//GEN-LAST:event_jRadioButtonNumberActionPerformed

    private void jRadioButtonPointNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPointNumberActionPerformed
        chooseLabType();
    }//GEN-LAST:event_jRadioButtonPointNumberActionPerformed

    private void jRadioButtonTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTextActionPerformed
        chooseLabType();
    }//GEN-LAST:event_jRadioButtonTextActionPerformed

    private void jRadioButtonTextsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTextsActionPerformed
        chooseLabType();
    }//GEN-LAST:event_jRadioButtonTextsActionPerformed

    private void jRadioButtonListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonListActionPerformed
        chooseLabType();
    }//GEN-LAST:event_jRadioButtonListActionPerformed

    private void jTextFieldMinFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldMinFocusLost
        String str = jTextFieldMin.getText();
        if (jRadioButtonNumber.isSelected()) {
            try {
                if (!str.equals("")) {
                    Integer.parseInt(str);
                }
            } catch (Exception e) {
                jTextFieldMin.setText("");
                jTextFieldMin.requestFocus();
            }
        } else if (jRadioButtonPointNumber.isSelected()) {
            boolean isDot = false;
            char[] c = str.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (c[i] == '.') {
                    if (c.length - 1 >= i + 1) {
                        isDot = true;
                        break;
                    } else {
                        jTextFieldMin.setText("");
                        jTextFieldMin.requestFocus();
                        isDot = false;
                        break;
                    }
                }
            }
            if (isDot) {
                try {
                    if (!str.equals("")) {
                        Float.parseFloat(jTextFieldMin.getText());
                    }
                } catch (Exception e) {
                    jTextFieldMin.setText("");
                    jTextFieldMin.requestFocus();
                }
            } else if (!str.equals("")) {
                jTextFieldMin.setText("");
                jTextFieldMin.requestFocus();
            }
        }
    }//GEN-LAST:event_jTextFieldMinFocusLost

    private void jTextFieldMaxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldMaxFocusLost
        String str = jTextFieldMax.getText();
        if (jRadioButtonNumber.isSelected()) {
            try {
                if (!str.equals("")) {
                    Integer.parseInt(str);
                }
            } catch (Exception e) {
                jTextFieldMax.setText("");
                jTextFieldMax.requestFocus();
            }
        } else if (jRadioButtonPointNumber.isSelected()) {
            boolean isDot = false;
            char[] c = str.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (c[i] == '.') {
                    if (c.length - 1 >= i + 1) {
                        isDot = true;
                        break;
                    } else {
                        jTextFieldMin.setText("");
                        jTextFieldMin.requestFocus();
                        break;
                    }
                }
            }
            if (isDot) {
                try {
                    if (!str.equals("")) {
                        Float.parseFloat(jTextFieldMax.getText());
                    }
                } catch (Exception e) {
                    jTextFieldMax.setText("");
                    jTextFieldMax.requestFocus();
                }
            } else if (!str.equals("")) {
                jTextFieldMax.setText("");
                jTextFieldMax.requestFocus();
            }
        }
    }//GEN-LAST:event_jTextFieldMaxFocusLost

    private void jComboBoxListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxListActionPerformed
        if (jRadioButtonList.isSelected()) {
            ComboFix cf = (ComboFix) jComboBoxList.getSelectedItem();
            Vector vd = null;
            if (cf != null) {
                vd = theHC.theSetupControl.listLabResultDetailByResultType(cf.getCode());
            }
            setLabResultDetailV(vd);
        }
    }//GEN-LAST:event_jComboBoxListActionPerformed

    private void doubleTextFieldPriceCostKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_doubleTextFieldPriceCostKeyReleased
    {//GEN-HEADEREND:event_doubleTextFieldPriceCostKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButtonSaveActionPerformed(null);
        }
    }//GEN-LAST:event_doubleTextFieldPriceCostKeyReleased

    private void jTextFieldPriceKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextFieldPriceKeyReleased
    {//GEN-HEADEREND:event_jTextFieldPriceKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_ENTER) {
            doubleTextFieldPriceCost.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldPriceKeyReleased

    private void jCheckBoxSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSActionPerformed
        //pu : 25/07/2549 : กำหนดค่าให้กับ Index สำหรับระบุถึงหน้าปัจจุบันของรายการตรวจรักษา
        this.curNext = 0;
        this.curPrev = 0;
        searchItemGroup();
    }//GEN-LAST:event_jCheckBoxSActionPerformed

    private void jRadioButtonConsistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonConsistActionPerformed
        //pu : 25/07/2549 : กำหนดค่าให้กับ Index สำหรับระบุถึงหน้าปัจจุบันของรายการตรวจรักษา
        this.curNext = 0;
        this.curPrev = 0;
        searchItemGroup();
    }//GEN-LAST:event_jRadioButtonConsistActionPerformed

    private void jRadioButtonBeginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonBeginActionPerformed
        //pu : 25/07/2549 : กำหนดค่าให้กับ Index สำหรับระบุถึงหน้าปัจจุบันของรายการตรวจรักษา
        this.curNext = 0;
        this.curPrev = 0;
        searchItemGroup();
    }//GEN-LAST:event_jRadioButtonBeginActionPerformed

    private void jTextFieldSCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSCodeKeyReleased
        /**
         * สามารถค้นหารายการตรวจรักษา โดยการระบุคำค้นได้ช่องค้นหา แล้วค้นหาให้อัตโนมัติ (ไม่ต้องกด Enter ก็ได้)
         * เลือกรายการตรวจรักษา ด้วยการกดปุ่มลูกศรขึ้น-ลง หลังจากค้นหารายการตรวจรักษาแล้ว
         * @Modifier pu
         * @Date 25/07/2549
         */
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            jTable1.requestFocus();
            jTable1.setRowSelectionInterval(0, 0);
            selectItemGroup();
        }
        if (jTextFieldSCode.getText().length() > 2) {
            this.jTextFieldSCodeActionPerformed(null);
        }
    }//GEN-LAST:event_jTextFieldSCodeKeyReleased

    private void jRadioButtonLabGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonLabGroupActionPerformed
        showLabProperty("cardlabgroup");
    }//GEN-LAST:event_jRadioButtonLabGroupActionPerformed

    private void dateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateTextFieldActionPerformed
    }//GEN-LAST:event_dateTextFieldActionPerformed

    private void dateTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateTextFieldFocusLost
    }//GEN-LAST:event_dateTextFieldFocusLost

    private void jComboBoxSCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSCategoryActionPerformed
        //pu : 25/07/2549 : กำหนดค่าให้กับ Index สำหรับระบุถึงหน้าปัจจุบันของรายการตรวจรักษา
        this.curNext = 0;
        this.curPrev = 0;
        searchItemGroup();
    }//GEN-LAST:event_jComboBoxSCategoryActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        addLabGroupItemGroup();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonAddPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddPriceActionPerformed
        setItemPrice(null);
    }//GEN-LAST:event_jButtonAddPriceActionPerformed

    private void jTable4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseReleased
    }//GEN-LAST:event_jTable4MouseReleased

    private void jButtonDelLabItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelLabItemActionPerformed
        deleteLabSet();
    }//GEN-LAST:event_jButtonDelLabItemActionPerformed
    private void jRadioButtonLabDetailActionPerformed(java.awt.event.ActionEvent evt) {
        showLabProperty("cardlabdetail");
    }
    private void jComboBoxOrderListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxOrderListItemStateChanged
    }//GEN-LAST:event_jComboBoxOrderListItemStateChanged

    private void jComboBoxOrderListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxOrderListMouseClicked
        selectOrderItemGroup();
    }//GEN-LAST:event_jComboBoxOrderListMouseClicked

    private void jComboBoxOrderListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxOrderListActionPerformed
        selectOrderItemGroup();
    }//GEN-LAST:event_jComboBoxOrderListActionPerformed

    private void jCheckBoxSearchGroupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxSearchGroupMouseClicked
        checkItemGroup();
    }//GEN-LAST:event_jCheckBoxSearchGroupMouseClicked

    private void jCheckBoxSearchGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSearchGroupActionPerformed
        //pu : 25/07/2549 : กำหนดค่าให้กับ Index สำหรับระบุถึงหน้าปัจจุบันของรายการตรวจรักษา
        this.curNext = 0;
        this.curPrev = 0;
        searchItemGroup();
    }//GEN-LAST:event_jCheckBoxSearchGroupActionPerformed

    private void jButtonDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelActionPerformed
        deleteItemGroup();
    }//GEN-LAST:event_jButtonDelActionPerformed

    private void jTextFieldSCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSCodeActionPerformed
        //pu : 25/07/2549 : กำหนดค่าให้กับ Index สำหรับระบุถึงหน้าปัจจุบันของรายการตรวจรักษา
        this.curNext = 0;
        this.curPrev = 0;
        searchItemGroup();
    }//GEN-LAST:event_jTextFieldSCodeActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
//        String common_name = jTextAreaCommonName.getText();
//        common_name = common_name.replace("\\\'", "\'");
//        common_name = common_name.replace("\'", "\\\'");
//        jTextAreaCommonName.setText(common_name);
//        String trade_name = jTextAreaTradeName.getText();
//        trade_name = trade_name.replace("\\\'", "\'");
//        trade_name = trade_name.replace("\'", "\\\'");
//        jTextAreaTradeName.setText(trade_name);
//        String general_name = jTextAreaNickName.getText();
//        general_name = general_name.replace("\\\'", "\'");
//        general_name = general_name.replace("\'", "\\\'");
//        jTextAreaNickName.setText(general_name);
        boolean re = true;
        int ret = theSetupControl.saveItem(getItem(), getItemPrice(), jPanelDrugDescription.getDrug(), getLabResultItem(), getLabGroup(), getLabSetV(), jRadioButtonLabGroup.isSelected(), getItemService());
        vItemPrice = theSetupControl.listItemPrice(theItem.getObjectId());
        if (ret > 0) {
            // Somprasong comment 090810 ทำเพื่ออะไรไม่เข้าใจ
//            this.setItem(theItem);
//            //henbe comment 100253 kong ต้องปรับตาม pattern ของการ setObject(null)
//            setItemPrice(null);
            // Somprasong add 090810 ให้ค้นหาใหม่เมื่อบันทึกสำเร็จ
//            jTextFieldSCode.setText(theItem.item_id);
            //LionHeart แก้ให้ค้นรายการจากชื่อรายการ
            jTextFieldSCode.setText(theItem.common_name);
            searchItemGroup();
        }
//        searchItemGroup();// Somprasong comment 090810
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
//        insertItemGroup();
//        jTextFieldCode.setToolTipText(Constant.getTextBundle("รหัสที่แสดงจะเป็นค่าสุดท้าย กรุณาเพิ่มรหัสโดยบวกค่าอีก1"));
//        theUS.setStatus("รหัสที่แสดงจะเป็นค่าสุดท้าย กรุณาเพิ่มรหัสโดยบวกค่าอีก1", UpdateStatus.NORMAL);
        // SOmprasong fix bug add new 090810
        this.addNewItem();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        nextItemGroup();
    }//GEN-LAST:event_jButtonNextActionPerformed

    private void jButtonPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrevActionPerformed
        prevItemGroup();
    }//GEN-LAST:event_jButtonPrevActionPerformed

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        //pu : 25/07/2549 : กำหนดค่าให้กับ Index สำหรับระบุถึงหน้าปัจจุบันของรายการตรวจรักษา
        this.curNext = 0;
        this.curPrev = 0;
        searchItemGroup();
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        selectItemGroup();
    }//GEN-LAST:event_jTable1MouseReleased

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if ((evt.getKeyCode() == KeyEvent.VK_DOWN) || (evt.getKeyCode() == KeyEvent.VK_UP)) {
            selectItemGroup();
        }
	}//GEN-LAST:event_jTable1KeyReleased

    private void jButtonDelPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelPriceActionPerformed

        //henbe comment 100253 kong ต้องปรับตาม pattern ของการ setObject(null)
        this.deletePriceItemGroup();
        setItemPrice(null);
}//GEN-LAST:event_jButtonDelPriceActionPerformed

    private void jComboBoxOrderListFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxOrderListFocusLost
        this.selectOrderItemGroup();
    }//GEN-LAST:event_jComboBoxOrderListFocusLost

    private void jRadioButtonInSQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonInSQLActionPerformed
        this.jTextFieldDefaultValue.setEnabled(jRadioButtonInSQL.isSelected());
        if (jRadioButtonInSQL.isSelected()) {
            this.jTextFieldDefaultValue.setText("select case when visit_age<'10' then '0' "
                    + "        when visit_age > '10' then '1'"
                    + "        else '2' end as min"
                    + ",case when visit_age<'10' then '10' "
                    + "        when visit_age > '10' then '11'"
                    + "        else '20' end as max from t_visit where t_visit_id = ?");
            jTextFieldMin.setText("");
            jTextFieldMax.setText("");
        }
}//GEN-LAST:event_jRadioButtonInSQLActionPerformed

    private void jRadioButtonInPeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonInPeriodActionPerformed
        this.jTextFieldMax.setEnabled(jRadioButtonInPeriod.isSelected());
        this.jTextFieldMin.setEnabled(jRadioButtonInPeriod.isSelected());
        if (jRadioButtonInPeriod.isSelected()) {
            jTextFieldDefaultValue.setText("");
        }
}//GEN-LAST:event_jRadioButtonInPeriodActionPerformed

    private void nextItemGroup() {
        setItemV(vItem, 1);
    }

    private void prevItemGroup() {
        setItemV(vItem, 0);
    }

    @Deprecated
    private void insertItemGroup() {
        saved = 1;
        setEnabled(true);
        jTextFieldCode.requestFocus();
        jTextFieldCode.setEnabled(true);
        jTextAreaCommonName.setEnabled(true);
        String cat = ComboboxModel.getCodeComboBox(jComboBoxOrderList);
        String str = theHC.theSetupControl.readMaxItemCodeByCat(cat);
        this.jRadioButtonLabDetail.setSelected(true);
        clearAll();
        this.jTextFieldCode.setText(str);
    }

    private void deleteLabSet() {
        int[] row = jTable4.getSelectedRows();
        if (row.length == 0) {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ", UpdateStatus.WARNING);
            return;
        }
        theSetupControl.deleteLabSet(vLabSet, row);
        setLabSetV(vLabSet);
    }

    private void deleteItemGroup() {
        int ret = theSetupControl.deleteItemByPk(theItem.getObjectId());
        if (ret == 0) {
            return;
        }
        setEnabled(false);
        clearAll();
        //pu : 25/07/2549 : เก็บ Index ปัจจุบันของหน้ารายการตรวจรักษาที่กำลังบันทึก
        int count = next - prev;
        this.curNext = next - count;
        this.curPrev = prev - offset;
        searchItemGroup();
    }

    private void deletePriceItemGroup() {
        int row = jTable2.getSelectedRow();
        if (row != -1) {
            String of = (String) jTable2.getValueAt(row, 0);
            String ros = new String();
            theItemPrice = (ItemPrice) vItemPrice.get(row);
            if (theItemPrice.getObjectId() != null) {
                int ret = theSetupControl.deleteItemPrice(theItemPrice);
                if (ret == 0) {
                    return;
                }
                vItemPrice.remove(row);
            } else {
                vItemPrice.remove(row);
            }
            setItemPriceV(vItemPrice);
            of = null;
        }
    }

    private void showLabProperty(String type) {
        CardLayout layout = (CardLayout) jPanel22.getLayout();
        layout.show(jPanel22, type);
    }

    private ItemPrice getItemPrice() {
        //pu:25/08/2549 : เก็บเวลาในการบันทึกใช้งานราคา Item ด้วย เพื่อให้ sort ได้ถูกต้องตอนแสดงในตาราง
        if (jTextFieldPrice.getText().equals("")) {
            return null;
        }
        theItemPrice = new ItemPrice();
        if (this.jCheckBoxPlan.isSelected()) {
            theItemPrice.item_price_id = jComboBoxPlan.getText();
        }

        theItemPrice.active_date = dateTextField.getText() + "," + DateUtil.getTextCurrentTime(this.theHC.theConnectionInf);
        theItemPrice.price = Constant.dicimalMoney(jTextFieldPrice.getText());
        theItemPrice.price_cost = Constant.dicimalMoney(doubleTextFieldPriceCost.getText());

        return theItemPrice;
    }

    public void saveItemPrice(ItemPrice itemprice) {
        if (itemprice.active_date.equals("") || itemprice.price.equals("")) {
            theUS.setStatus("ไม่สามารถบันทึกข้อมูลเป็นค่าว่างได้", UpdateStatus.WARNING);
            return;
        }
        if (vItemPrice == null) {
            vItemPrice = new Vector();
        }
        if (itemprice.getObjectId() == null) {
            vItemPrice.add(itemprice);
        }
        setItemPriceV(vItemPrice);
        theSetupControl.saveItemPrice(vItemPrice, theItem.getObjectId());
        this.jComboBoxPlan.setText("");
        this.jCheckBoxPlan.setSelected(theItemPrice.item_price_id.length() > 10);
        this.jComboBoxPlan.setEnabled(jCheckBoxPlan.isSelected());
        this.dateTextField.setEnabled(!jCheckBoxPlan.isSelected());
        jTextFieldPrice.setText("");
        doubleTextFieldPriceCost.setText("");
        jTextFieldPrice.setEnabled(false);
        doubleTextFieldPriceCost.setEnabled(false);
    }

    private void setItemPriceV(Vector price) {
        vItemPrice = price;
        String[] col = {Constant.getTextBundle("วันที่"), Constant.getTextBundle("ขาย"), Constant.getTextBundle("ทุน")};
        TaBleModel tm;
        if (price != null) {
            tm = new TaBleModel(col, price.size());
            for (int i = 0; i < price.size(); i++) {
                ItemPrice itemprice = (ItemPrice) price.get(i);
                try {
                    tm.setValueAt(DateUtil.getDateToString(DateUtil.getDateFromText(itemprice.active_date), false), i, 0);
                } catch (Exception ex) {
                    tm.setValueAt(itemprice.active_date, i, 0);
                }
                if (itemprice.price == null) {
                    tm.setValueAt("0", i, 1);
                } else {
                    tm.setValueAt(itemprice.price, i, 1);
                }
                if (itemprice.price_cost == null || itemprice.price_cost.equalsIgnoreCase("null")) {
                    tm.setValueAt("0", i, 2);
                } else {
                    tm.setValueAt(itemprice.price_cost, i, 2);
                }
            }
        } else {
            tm = new TaBleModel(col, 0);
        }
        jTable2.setModel(tm);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(300);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable2.getColumnModel().getColumn(1).setCellRenderer(TableRenderer.getRendererRight());
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable2.getColumnModel().getColumn(2).setCellRenderer(TableRenderer.getRendererRight());
    }

    private void setItemPrice(ItemPrice ip) {
        theItemPrice = ip;
        if (theItemPrice == null) {
            theItemPrice = new ItemPrice();
        }
        jTextFieldPrice.setEnabled(true);
        doubleTextFieldPriceCost.setEnabled(true);
        jTextFieldPrice.setText(theItemPrice.price);
        doubleTextFieldPriceCost.setText(theItemPrice.price_cost);
        jTextFieldPrice.requestFocus();
        jCheckBoxPlan.setSelected(false);
        jComboBoxPlan.setText(theItemPrice.item_price_id);
    }

    //it is lookup of lab ไม่ใช่ตัวหลักสำคัญไม่ต้องสนใจ
    private void setLabResultDetailV(Vector v) {
        TaBleModel tm;
        if (v != null) {
            tm = new TaBleModel(col_list, v.size());
            LabResultDetail ld = new LabResultDetail();
            for (int i = 0; i < v.size(); i++) {
                ld = (LabResultDetail) v.get(i);
                tm.setValueAt(ld.value, i, 0);
            }
            ld = null;
        } else {
            tm = new TaBleModel(col_list, 0);
        }
        jTableList.setModel(tm);
    }

    private Vector getLabSetV() {
        for (int i = 0; i < vLabSet.size(); i++) {
            LabSet ls = (LabSet) vLabSet.get(i);
            ls.item_name = String.valueOf(jTable4.getValueAt(i, 1));
        }
        return vLabSet;
    }

    private boolean setLabSetV(Vector vls) {
        String[] col = {Constant.getTextBundle("Item"), Constant.getTextBundle("ลำดับ"), Constant.getTextBundle("กลุ่ม")};
        this.vLabSet = vls;
        if (vLabSet == null) {
            vLabSet = new Vector();
        }

        TaBleModel tm = new TaBleModel(col, vLabSet.size());
        for (int i = 0; i < vLabSet.size(); i++) {
            LabSet ls = (LabSet) vLabSet.get(i);
            tm.setValueAt(ls.item_common_name, i, 0);
            tm.setValueAt(ls.item_name, i, 1);
            tm.setValueAt(ls.has_sub, i, 2);
        }
        tm.setEditingCol(1);
        jTable4.setModel(tm);

        jTable4.getColumnModel().getColumn(0).setPreferredWidth(300);
        jTable4.getColumnModel().getColumn(1).setCellRenderer(TableRenderer.getRendererRight());
        jTable4.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable4.getColumnModel().getColumn(2).setCellRenderer(TableRenderer.getRendererRight());
        jTable4.getColumnModel().getColumn(2).setPreferredWidth(100);
        return true;
    }

    private void clearAll() {   //ชื่อ
        jTextFieldCode.setText("");
        jTextAreaCommonName.setText("");
        jTextAreaTradeName.setText("");
        jTextAreaNickName.setText("");
        jCheckBox1.setSelected(true);
        //price
        jTextFieldPrice.setText("");
        doubleTextFieldPriceCost.setText("");
        // Dose
        this.jPanelDrugDescription.clearAll();
        // Lab
        jTextFieldLabDetail.setText("");
        jTextFieldLabUnit.setText("");
        jRadioButtonLabGroup.setEnabled(true);
        jRadioButtonLabDetail.setEnabled(true);
        showLabProperty("cardlabdetail");
        vItemPrice = new Vector();
        vLabSet = new Vector();
        setLabResultItem(null);
        setLabSetV(null);
        setItemPriceV(null);

        setItemService(null);
        theItem = new Item();
        theDrug = new Drug();
        theItemPrice = null;
        theLabGroup = new LabGroup();
        jCheckBoxSecret.setSelected(false);
        jCheckBoxNCDFBS.setSelected(false);
        jCheckBoxNCDHCT.setSelected(false);
        /*
        vItemPrice.clear();
        setItemPriceV(vItemPrice);
         */
    }

    public void setEnabled(boolean var) {
//        jTextFieldCode.setEnabled(var);
//        jTextAreaCommonName.setEnabled(var);
        //jTextAreaTradeName.setEnabled(var);
        //jTextAreaNickName.setEnabled(var);
        //jTextFieldPrice.setEnabled(var);
        //jTextFieldQtyUse.setEnabled(var);
        //jTextFieldQtyPay.setEnabled(var);
        //jTextAreaWarn.setEnabled(var);
        //jTextAreaDescription.setEnabled(var);
        jButtonDel.setEnabled(var);
        jTextFieldCode.setToolTipText("");
        //jCheckBoxSecret.setEnabled(var);
        jCheckBoxNCDFBS.setEnabled(var);
        jCheckBoxNCDHCT.setEnabled(var);
    }

    /**ทำให้ combobox สามารถทำงานได้ หรือ ไม่ได้*/
    private void checkItemGroup() {
        if (jCheckBoxSearchGroup.isSelected()) {
            jComboBoxSCategory.setEnabled(true);
        } else {
            jComboBoxSCategory.setEnabled(false);
        }
    }

    /**ทำการค้นหา item ตามกลุ่มหรือ ตาม ชื่อที่ต้องการค้นหา*/
    private void searchItemGroup() {   //pu : 25/07/2549 : กำหนดค่า Index ให้กับหน้าที่ต้องการแสดงรายการตรวจรักษา
        next = this.curNext;
        prev = this.curPrev;
        String itemname = jTextFieldSCode.getText();
        String itemgp = "";
        if (jCheckBoxSearchGroup.isSelected()) {
            itemgp = ComboboxModel.getCodeComboBox(jComboBoxSCategory);
        }
        String active = "0";
        if (jCheckBoxS.isSelected()) {
            active = "1";
        }

        if (itemname.equals("") && itemgp.equals("")) {
            theUS.setStatus("กรุณากรอกคำค้นก่อนการค้นหา", UpdateStatus.WARNING);
            return;
        }
        boolean begin_with = this.jRadioButtonBegin.isSelected();
        vItem = theSetupControl.listItemByGroup(itemgp, itemname, active, begin_with);
        setItemV(vItem, 1);
    }

    /**tong */
    private CategoryGroupItem selectOrderItemGroup() {
        String codeorderitem = ComboboxModel.getCodeComboBox(jComboBoxOrderList);
        CategoryGroupItem cgitem = theLookupControl.readCategoryGroupItemById(codeorderitem);
        if (cgitem != null) {
            category = Integer.parseInt(cgitem.category_group_code);
            if (cgitem.category_group_code.equals(CategoryGroup.isDrug())) {
                showOrderProperty("DrugDescription");
            }
            if (cgitem.category_group_code.equals(CategoryGroup.isLab())) //Lab
            {
                showOrderProperty("LabDescription");
            }
            if (cgitem.category_group_code.equals(CategoryGroup.isService()) || cgitem.category_group_code.equals(CategoryGroup.isDental())) //ค่าบริการ และ ทันตกรรม
            {
                showOrderProperty("ServiceDescription");
            }
            if (cgitem.category_group_code.equals(CategoryGroup.isXray()) || cgitem.category_group_code.equals(CategoryGroup.isSupply())) //Xray และ เวชภัณฑ์
            {
                showOrderProperty("Blank");
            }
        }
        return cgitem;
    }

    private void showOrderProperty(String type) {
        CardLayout layout = (CardLayout) jPanel7.getLayout();
        layout.show(jPanel7, type);
    }

    private void selectItemGroup() {
        setEnabled(true);
        clearAll();
        int row = jTable1.getSelectedRow();
        this.currentRow = jTable1.getSelectedRow();
        theItem = (Item) vItem.get(row + prev);
        setItem(theItem);
    }

    private void setItem(Item it) {
        theItem = it;
        theItem = theSetupControl.listItemByPk(theItem.getObjectId());
        ComboboxModel.setCodeComboBox(jComboBoxOrderList, theItem.item_group_code_category);
        jTextFieldCode.setText(theItem.item_id);
        jTextAreaCommonName.setText(theItem.common_name);
        jTextAreaNickName.setText(theItem.nick_name);
        jTextAreaTradeName.setText(theItem.trade_name);
        ComboboxModel.setCodeComboBox(jComboBoxReceiptList, theItem.item_group_code_billing);
        ComboboxModel.setCodeComboBox(jComboBoxStandardGroup, theItem.item_16_group);
        jCheckBox1.setSelected(theItem.getActive());
        ComboboxModel.setCodeComboBox(jComboBoxSpecified, theItem.specified);
        jRadioButtonLabDetail.setEnabled(it.getObjectId() == null);
        jRadioButtonLabGroup.setEnabled(it.getObjectId() == null);

        jPanelDrugDescription.setUnitPack(it.unit_pack53);

        setItemPriceV(theSetupControl.listItemPrice(theItem.getObjectId()));
        CategoryGroupItem cgitem = selectOrderItemGroup();
        if (cgitem.category_group_code.equals(CategoryGroup.isDrug())) {
            jPanelDrugDescription.setDrug(theSetupControl.readDrug(theItem.getObjectId()));
        }
        if (cgitem.category_group_code.equals(CategoryGroup.isService()) || cgitem.category_group_code.equals(CategoryGroup.isDental())) //ค่าบริการ และ ทันตกรรม
        {
            setItemService(theSetupControl.readItemSeviceByItem(theItem.getObjectId()));
        }
        if (cgitem.category_group_code.equals(CategoryGroup.isLab())) {
            boolean ret = setLabGroup(theSetupControl.listLabGroupByItem(theItem.getObjectId()), null);
            if (ret) {
                jRadioButtonLabGroup.setSelected(true);
                this.jRadioButtonLabGroupActionPerformed(null);
                setLabSetV(theSetupControl.listLabSetByItemId(theItem.getObjectId(), "sort"));
                return;
            }
            jRadioButtonLabDetail.setSelected(true);
            this.jRadioButtonLabDetailActionPerformed(null);
            setLabResultItem(theSetupControl.readLabResultItem(theItem.getObjectId()));
            try {
                this.jComboBoxLabRpGroup.setSelectedIndex(Integer.parseInt(theItem.rp_lab_type) - 1);
            } catch (Exception e) {
                //not thing to think
            }
            this.chooseLabType();
        }
    }

    public void setItemService(ItemService is) {
        theItemService = is;
        String icd9 = "";
        jTextFieldIcdCode.setText("");
        balloonTextArea1.setText("");
        jTextFieldDescription.setText("");
        if (theItemService == null) {
            theItemService = new ItemService();
        }

        jTextFieldDescription.setText(theItem.common_name);
        if (theItemService.icd9_code != null && !theItemService.icd9_code.equals("")) {
            ICD9 oicd9 = theSetupControl.listIcd9ByCode(theItemService.icd9_code);
            if (oicd9 != null) {
                icd9 = oicd9.getName();
                jTextFieldIcdCode.setText(icd9);
                balloonTextArea1.setText(icd9);
            }
        }
    }

    /**
     *@deprecated henbe unused
     **/
    public boolean setLabGroup(LabGroup lg, Vector vls) {
        theLabGroup = lg;
        if (theLabGroup == null) {
            Constant.println("if(theLabGroup==null)");
            setLabSetV(null);
            return false;
        }
        if (theLabGroup.getObjectId() == null) {
            setLabSetV(null);
            Constant.println("if(theLabGroup.getObjectId()==null)");
            return false;
        }
        setLabSetV(vls);
        return true;
    }

    private Item getItem() {
        // set Item
        theItem.item_id = Gutil.CheckReservedWords(jTextFieldCode.getText());
//        theItem.item_id = theItem.item_id.replace("'", "\\'");
        theItem.common_name = Gutil.CheckReservedWords(jTextAreaCommonName.getText());
//        theItem.common_name = theItem.item_id.replace("'", "\\'");
        theItem.nick_name = Gutil.CheckReservedWords(jTextAreaNickName.getText());
//        theItem.nick_name = theItem.nick_name.replace("\\\'", "\'");
//        theItem.nick_name = theItem.nick_name.replace("\'", "\\\'");
//        theItem.nick_name = theItem.item_id.replace("'", "\\'");
        theItem.trade_name = Gutil.CheckReservedWords(jTextAreaTradeName.getText());
//        theItem.trade_name = theItem.trade_name.replace("\\", "");
//        theItem.trade_name = theItem.trade_name.replace("\'", "\\\'");
//        theItem.trade_name = theItem.item_id.replace("'", "\\'");
        theItem.item_group_code_billing = ComboboxModel.getCodeComboBox(jComboBoxReceiptList);
        theItem.item_group_code_category = ComboboxModel.getCodeComboBox(jComboBoxOrderList);
        theItem.item_16_group = ComboboxModel.getCodeComboBox(jComboBoxStandardGroup);
        theItem.setSecret(jCheckBoxSecret.isSelected());
        theItem.setActive(jCheckBox1.isSelected());
        theItem.unit_pack53 = jPanelDrugDescription.getUnitPack();
        theItem.rp_lab_type = String.valueOf(jComboBoxLabRpGroup.getSelectedIndex() + 1);
        theItem.specified = Gutil.getGuiData(jComboBoxSpecified);
        return theItem;
    }

    public ItemService getItemService() {
        if (category!=6 && category != 5) {
            return null;
        }

        if (theItemService == null) {
            return null;
        }

        theItemService.item_id = theItem.getObjectId();
        theItemService.description = theItem.common_name;
//        String icd9 = jTextFieldIcdCode.getText();
        String icd9 = balloonTextArea1.getText();
        String icd9code = "";
        if (!icd9.equals("") && icd9.indexOf(" : ") > -1) {
            icd9code = icd9.substring(0, icd9.indexOf(" : "));
        } else {
            icd9code = icd9;
        }
        theItemService.active = "1";
        theItemService.check = icd9code;
        if (theItemService.getObjectId() != null) {
            if (icd9code.equals("")) {
                theItemService.check = "";
            }
            if (!theItemService.icd9_code.equals(icd9code)) {
                theItemService.active = "0";
                theItemService.icd9_code_tmp = icd9code;
            }
        }
        if (!icd9code.equals("")) {
            ICD9 i9 = theSetupControl.listIcd9ByCode(icd9code);
            if (i9 == null) {
                theUS.setStatus(Constant.getTextBundle("ไม่สามารถบันทึกข้อมูลได้") + " "
                        + Constant.getTextBundle("เนื่องจากไม่พบรายการหัตถการที่กรอกไว้"), UpdateStatus.WARNING);
                return null;
            } else {
                if (theItemService.icd9_code.equals("")) {
                    theItemService.icd9_code = icd9code;
                }
                return theItemService;
            }
        }
        return theItemService;
    }

    public LabGroup getLabGroup() {
        if (theLabGroup == null) {
            theLabGroup = new LabGroup();
        }
        if (vLabSet == null) {
            vLabSet = new Vector();
        }
        return theLabGroup;
    }

    private LabResultItem getLabResultItem() {
        if (theLabResultItem == null) {
            theLabResultItem = new LabResultItem();
        }

        theLabResultItem.name = Gutil.CheckReservedWords(jTextFieldLabDetail.getText());
        theLabResultItem.unit = Gutil.CheckReservedWords(jTextFieldLabUnit.getText());
        theLabResultItem.min = jTextFieldMin.getText();
        theLabResultItem.max = jTextFieldMax.getText();
        //tong
        theLabResultItem.default_value = Gutil.CheckReservedWords(jTextFieldDefaultValue.getText().trim());

        if (jRadioButtonNumber.isSelected()) {
            theLabResultItem.resultType = LabResultType.INTEGER;
        }
        if (jRadioButtonPointNumber.isSelected()) {
            theLabResultItem.resultType = LabResultType.FLOAT;
        }
        if (jRadioButtonText.isSelected()) {
            theLabResultItem.resultType = LabResultType.TEXT_ONE;
        }
        if (jRadioButtonTexts.isSelected()) {
            theLabResultItem.resultType = LabResultType.TEXT_MANY;
        }
        if (jRadioButtonList.isSelected()) {
            theLabResultItem.resultType = LabResultType.LIST;
            ComboFix cf = (ComboFix) jComboBoxList.getSelectedItem();
            if (cf != null) {
                theLabResultItem.labresult_id = cf.getCode();
            }
        }

        //amp:19/06/2549
        if (jRadioButtonLabDetail.isSelected()) {
            theLabResultItem.setNcdFbs(jCheckBoxNCDFBS.isSelected());
            theLabResultItem.setNcdHct(jCheckBoxNCDHCT.isSelected());
        }
        return theLabResultItem;
    }

    private void setLabResultItem(LabResultItem lab) {

        theLabResultItem = lab;
        if (theLabResultItem == null) {
            jTextFieldLabDetail.setText("");
            jTextFieldLabUnit.setText("");
            jTextFieldMin.setText("");
            jTextFieldMax.setText("");
            Gutil.setGuiData(jComboBoxList, "");
            jTextFieldDefaultValue.setText("");


            return;
        }

        jTextFieldLabDetail.setText(theLabResultItem.name);
        jTextFieldLabUnit.setText(theLabResultItem.unit);
        jTextFieldMin.setText(theLabResultItem.min);
        jTextFieldMax.setText(theLabResultItem.max);
        Gutil.setGuiData(jComboBoxList, theLabResultItem.labresult_id);
        jCheckBoxSecret.setSelected(theItem.getSecret());
        jTextFieldDefaultValue.setText(theLabResultItem.default_value);

        jRadioButtonList.setSelected(false);
        jRadioButtonTexts.setSelected(false);
        jRadioButtonText.setSelected(false);
        jRadioButtonPointNumber.setSelected(false);
        jRadioButtonNumber.setSelected(false);

        if (theLabResultItem.resultType.equals(LabResultType.INTEGER)) {
            jRadioButtonNumber.setSelected(true);
        } else if (theLabResultItem.resultType.equals(LabResultType.FLOAT)) {
            jRadioButtonPointNumber.setSelected(true);
        } else if (theLabResultItem.resultType.equals(LabResultType.TEXT_ONE)) {
            jRadioButtonText.setSelected(true);
        } else if (theLabResultItem.resultType.equals(LabResultType.TEXT_MANY)) {
            jRadioButtonTexts.setSelected(true);
        } else if (theLabResultItem.resultType.equals(LabResultType.LIST)) {
            jRadioButtonList.setSelected(true);
        }

        jCheckBoxNCDFBS.setSelected("1".equals(theLabResultItem.ncd_fbs));
        jCheckBoxNCDHCT.setSelected("1".equals(theLabResultItem.ncd_hct));

        if (theLabResultItem.min != null && !theLabResultItem.min.equals("")) {
            jTextFieldMin.setText(theLabResultItem.min);
        } else {
            jTextFieldMin.setText("");
        }
        if (theLabResultItem.max != null && !theLabResultItem.max.equals("")) {
            jTextFieldMax.setText(theLabResultItem.max);
        } else {
            jTextFieldMax.setText("");
        }
        if (theLabResultItem.resultType != null && theLabResultItem.resultType.equals("0")) {
            jRadioButtonNumber.setSelected(true);
        } else if (theLabResultItem.resultType != null && theLabResultItem.resultType.equals("1")) {
            jRadioButtonPointNumber.setSelected(true);
        } else if (theLabResultItem.resultType == null || theLabResultItem.resultType.equals("2")) {
            jRadioButtonText.setSelected(true);
        } else if (theLabResultItem.resultType != null && theLabResultItem.resultType.equals("3")) {
            jRadioButtonTexts.setSelected(true);
        } else if (theLabResultItem.resultType != null && theLabResultItem.resultType.equals("4")) {
            jRadioButtonList.setSelected(true);
            com.hospital_os.utility.ComboboxModel.setCodeComboBox(jComboBoxList, theLabResultItem.labresult_id);
        }

    }

    public void chooseLabType() {
        if (jRadioButtonNumber.isSelected() || jRadioButtonPointNumber.isSelected()) {
            jComboBoxList.setEnabled(false);
            setLabResultDetailV(null);
        } else if (jRadioButtonList.isSelected()) {
            jComboBoxList.setEnabled(true);
        } else if (jRadioButtonText.isSelected() || jRadioButtonTexts.isSelected()) {
            jComboBoxList.setEnabled(false);
            setLabResultDetailV(null);
        }
    }

    private void setItemV(Vector voffice, int off) {
        Item of = new Item();
        int count = offset;
        int p = 0;
        int n = 0;
        int c = 0;
        if (voffice != null && voffice.size() != 0) {
            if (off == 1)//กดปุ่ม next
            {
                p = prev;
                n = next;
                prev = next;
                next = next + offset;
                if (next >= vItem.size()) {
                    next = vItem.size();
                    count = next - prev;
                }
                if (count == 0) {
                    prev = p;
                    next = n;
                    count = n - p;
                }
            } else //กดปุ่ม previous
            {
                next = prev;
                prev = prev - offset;
                if (prev <= 0) {
                    prev = 0;
                    next = offset;
                }
                if (next >= vItem.size()) {
                    next = vItem.size();
                    count = next;
                }
            }
            TaBleModel tm = new TaBleModel(col, count);
            for (int i = 0; i < count; i++) {
                of = (Item) voffice.get(i + prev);
                tm.setValueAt(of.item_id, i, 0);
                tm.setValueAt(of.common_name, i, 1);
            }
            jTable1.setModel(tm);
        } else {
            TaBleModel tm = new TaBleModel(col, 0);
            jTable1.setModel(tm);
        }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(60); // ชื่อสามัญสำหรับ รพ.ทั่วไป, ชื่อการค้า สำหรับศูนย์โรคตา
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(300); // จำนวน
        if (jTable1.getRowCount() == 1) {
            jTable1.setRowSelectionInterval(0, 0);
            jTable1MouseReleased(null);
        }
    }

    public void addLabGroupItemGroup() {
        if (psss == null) {
            psss = new PanelSetupSearchSub(theHC, theUS, 2);
            psss.setTitle(Constant.getTextBundle("กำหนดรายการ Lab ชุด"));
            if (vLabSet == null) {
                vLabSet = new Vector();
            }
            psss.showSearchLabGroup(jTable4, vLabSet, null);
        }
        psss = null;
    }

    public void setEnableForLift(boolean b) {
        jButtonDel.setVisible(b);
        jButtonDelLabItem.setVisible(b);
    }

    public int saveOption(Option option) {
        return 0;
    }

    public int editOption(Option option) {
        return 0;
    }

    public void notifysetEnableForLift(boolean b) {
    }

    public Option readOption() {
        return null;
    }

    public void reFrashPanel() {
    }

    public void notifyreFrashPanel() {
        setupLookup();
    }

    public Vector listOptionAll() {
        return null;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hosv3.gui.component.BalloonTextArea balloonTextArea1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroupBeginWith;
    private javax.swing.ButtonGroup buttonGroupLab;
    private javax.swing.ButtonGroup buttonGroupLabResultType;
    private javax.swing.ButtonGroup buttonGroupNormalValue;
    private com.hospital_os.utility.DateComboBox dateTextField;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.hospital_os.utility.DoubleTextField doubleTextFieldPriceCost;
    private com.hospital_os.gui.font.FontFormatTitle fontFormatTitle1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAddPrice;
    private javax.swing.JButton jButtonAddPrice1;
    private javax.swing.JButton jButtonDel;
    private javax.swing.JButton jButtonDelLabItem;
    private javax.swing.JButton jButtonDelPrice;
    private javax.swing.JButton jButtonDelPrice1;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrev;
    private javax.swing.JButton jButtonPrev1;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSavePrice1;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBoxNCDFBS;
    private javax.swing.JCheckBox jCheckBoxNCDHCT;
    private javax.swing.JCheckBox jCheckBoxPlan;
    private javax.swing.JCheckBox jCheckBoxS;
    private javax.swing.JCheckBox jCheckBoxSearchGroup;
    private javax.swing.JCheckBox jCheckBoxSecret;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBoxLabRpGroup;
    private javax.swing.JComboBox jComboBoxList;
    private javax.swing.JComboBox jComboBoxOrderList;
    private com.hosv3.gui.component.HosComboBox jComboBoxPlan;
    private javax.swing.JComboBox jComboBoxReceiptList;
    private javax.swing.JComboBox jComboBoxSCategory;
    private com.hosv3.gui.component.HosComboBox jComboBoxSpecified;
    private javax.swing.JComboBox jComboBoxStandardGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel111;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelBlank;
    private javax.swing.JPanel jPanelDefaultValue;
    private com.hosv3.gui.panel.detail.PDItemDrug jPanelDrugDescription;
    private javax.swing.JPanel jPanelIcdCode;
    private javax.swing.JPanel jPanelLabDescription;
    private javax.swing.JRadioButton jRadioButtonBegin;
    private javax.swing.JRadioButton jRadioButtonConsist;
    private javax.swing.JRadioButton jRadioButtonInPeriod;
    private javax.swing.JRadioButton jRadioButtonInSQL;
    private javax.swing.JRadioButton jRadioButtonLabDetail;
    private javax.swing.JRadioButton jRadioButtonLabGroup;
    private javax.swing.JRadioButton jRadioButtonList;
    private javax.swing.JRadioButton jRadioButtonNumber;
    private javax.swing.JRadioButton jRadioButtonPointNumber;
    private javax.swing.JRadioButton jRadioButtonText;
    private javax.swing.JRadioButton jRadioButtonTexts;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.hosv3.gui.component.HJTableSort jTable1;
    private com.hosv3.gui.component.HJTableSort jTable2;
    private com.hosv3.gui.component.HJTableSort jTable3;
    private com.hosv3.gui.component.HJTableSort jTable4;
    private com.hosv3.gui.component.HJTableSort jTableList;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextAreaCommonName;
    private javax.swing.JTextArea jTextAreaNickName;
    private javax.swing.JTextArea jTextAreaTradeName;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jTextFieldCode;
    private javax.swing.JTextArea jTextFieldDefaultValue;
    private javax.swing.JTextField jTextFieldDescription;
    private com.hosv3.gui.component.BalloonTextField jTextFieldIcdCode;
    private javax.swing.JTextField jTextFieldLabDetail;
    private javax.swing.JTextField jTextFieldLabUnit;
    private javax.swing.JTextField jTextFieldMax;
    private javax.swing.JTextField jTextFieldMin;
    private com.hospital_os.utility.DoubleTextField jTextFieldPrice;
    private javax.swing.JTextField jTextFieldSCode;
    // End of variables declaration//GEN-END:variables

    private void addNewItem() {
        saved = 1;
        setEnabled(true);
        jTextFieldCode.requestFocus();
        jTextFieldCode.setEnabled(true);
        jTextAreaCommonName.setEnabled(true);
        String cat = ComboboxModel.getCodeComboBox(jComboBoxOrderList);
        String str = theHC.theSetupControl.readMaxItemCodeByCat(cat);
        this.jRadioButtonLabDetail.setSelected(true);
        clearAll();
        this.jTextFieldCode.setText(str);
        jTextFieldCode.setToolTipText(Constant.getTextBundle("รหัสที่แสดงจะเป็นค่าสุดท้าย กรุณาเพิ่มรหัสโดยบวกค่าอีก1"));
        selectOrderItemGroup();
    }
}
