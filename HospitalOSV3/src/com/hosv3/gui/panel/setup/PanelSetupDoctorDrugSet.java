/*
 * PanelSetupDoctorDrugSet.java
 *
 * Created on 12 ธันวาคม 2546, 10:11 น.
 */
package com.hosv3.gui.panel.setup;
import java.awt.*;
import java.util.Vector;

import com.hosv3.usecase.setup.*;
import com.hosv3.control.*;
import com.hosv3.subject.*;
import com.hosv3.utility.connection.*;
import com.hosv3.gui.dialog.*;
import com.hosv3.utility.GuiLang;
import com.hosv3.object.*;
import com.hosv3.control.lookup.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import com.hosv3.utility.Constant;
//import com.hosv3.usecase.transaction.order.*;
/**
 *
 * @Panel Author amp 
 * @Code Author amp
 */
public class PanelSetupDoctorDrugSet extends javax.swing.JPanel implements
ManageItemReq //หาเจอ 2 ตัว listItemByGroupResp ManageItemReq กับ ManageContractReq
,ManageOptionReq
{
    public final static String CARD_BLANK = "CARD_BLANK";
    public final static String CARD_DRUG = "CARD_DRUG";
    LookupControl theLookupControl;
    ComboboxModel theComboboxModel;
    UpdateStatus theUS;
    HosControl theHC;
    SetupControl theSetupControl;
    SetupSubject theSetupSubject;
    OrderControl theOrderControl;
    Vector drugSetGroup = new Vector();
    Vector drugSet = new Vector();
    Vector owner = new Vector();
    Vector use_uom = new Vector();
    Vector instruction = new Vector();
    Vector frequency = new Vector();
    Vector purch_uom = new Vector();
    DrugSet theDrugSet = new DrugSet();
    DrugSetGroup theDrugSetGroup = new DrugSetGroup();
    DoseDrugSet theDoseDrugSet = new DoseDrugSet();
    Employee theEmployee = new Employee();
    Item theItem = new Item();
    CategoryGroupItem theCategoryGroupItem = new CategoryGroupItem();
    PanelSetupSearchSub  psep;
    int old_row = 0;
    int offset = 15;
    int next = 0;
    int prev = 0;
    int saved = 0; // 0 คือ ไม่สามารถ insertได้ 1 คือ insert ได้
    /** pu : 25/08/2549 : เก็บ Index ของยาชุด ตัวล่างสุดของหน้าปัจจุบัน*/
    int curNext = 0;
    /** pu : 25/08/2549 : เก็บ Index ของยาชุดตัวล่างสุดของหน้าก่อนหน้าปัจจุบัน*/
    int curPrev = 0;
    String[] col_TaBleModel = {"ชื่อชุด","เจ้าของยาชุด"};
    String[] col = {"รายการตรวจรักษา"};
    public PanelSetupDoctorDrugSet(HosControl hc,UpdateStatus us)
    {
        initComponents();
        setLanguage();
        setControl(hc,us);
    }
    public PanelSetupDoctorDrugSet()
    {
        initComponents();
        setLanguage();
    }
    /////////////////////Use this for decrease memory usage
    public void setControl(HosControl hc,UpdateStatus us)
    {
        this.jTableGroup.setGuiMode(false);
        this.jTableItem.setGuiMode(false);
        this.jButtonSaveDose.setVisible(false);
        this.jLabel2.setVisible(false);
        jTableGroup.setGuiMode(true);
        theUS = us;
        theHC = hc;
        theLookupControl = hc.theLookupControl;
        theSetupControl = hc.theSetupControl;
        theOrderControl = hc.theOrderControl;
        hc.theHS.theSetupSubject.addForLiftAttach(this);
        hc.theHS.theSetupSubject.addpanelrefrash(this);
        hc.theHS.theSetupSubject.addAllItem(this);
        theSetupSubject = hc.theHS.theSetupSubject;
        setupLookup();
        jRadioButtonGroupDoctor.setVisible(false);
        jRadioButtonGroupService.setVisible(false);
        jCheckBox1.setSelected(false);
        jComboBoxNameDoctor1.setEnabled(false);
        jComboBoxInstruction.setControl(null,new DrugInstructionLookup(hc.theLookupControl),new DrugInstruction2());
        jComboBoxFrequency.setControl(null,new DrugFrequencyLookup(hc.theLookupControl),new DrugFrequency2());
        jComboBoxUse.setControl(null,new DoseUomLookup(hc.theLookupControl),new Uom2());
        jComboBoxQty1.setControl(null,new DoseUomLookup(hc.theLookupControl),new Uom2());
    }
    /////////////////////Use this for decrease memory usage
    
    public void setupLookup()
    {
        //amp:20/7/2549:เพื่อให้ user อื่น สามารถเพิ่มยาชุดได้ด้วย
        //ComboboxModel.initComboBox(jComboBoxNameDoctor,owner);
        ComboboxModel.initComboBox(jComboBoxNameDoctor,theHC.theLO.theEmployee);
        ComboboxModel.initComboBox(jComboBoxNameDoctor1,theHC.theLO.theEmployee);
    }
    /**
     *@Author : amp
     *@date : 29/02/2549
     *@see : จัดการเกี่ยวกับภาษา
     */
    private void setLanguage()
    {
        GuiLang.setLanguage(jLabel4);
        GuiLang.setLanguage(jLabel3);
        GuiLang.setLanguage(jLabelICD9code);
        GuiLang.setLanguage(jButtonSearch);
        GuiLang.setLanguage(jLabel2);
        GuiLang.setLanguage(jRadioButtonGroupDoctor);
        GuiLang.setLanguage(jRadioButtonGroupService);
        GuiLang.setLanguage(jLabel5);
        GuiLang.setLanguage(jButtonSave);
        GuiLang.setLanguage(jCheckBoxSpecialUsage);
        GuiLang.setLanguage(jLabel7);
        GuiLang.setLanguage(jLabelDrugInstruction);
        GuiLang.setLanguage(jLabel8);
        GuiLang.setLanguage(jButtonSaveDose);
        GuiLang.setLanguage(jCheckBox1);
        GuiLang.setLanguage(col);
        GuiLang.setLanguage(col_TaBleModel);
        GuiLang.setLanguage(jCheckBox1);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        fontFormatTitle1 = new com.hospital_os.gui.font.FontFormatTitle();
        tableResultsModel1 = new com.hospital_os.utility.TableResultsModel();
        buttonGroupTypeOner = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabelICD9code = new javax.swing.JLabel();
        jTextFieldSCode = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableGroup = new com.hosv3.gui.component.HJTableSort();
        jPanel14 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBoxNameDoctor1 = new javax.swing.JComboBox();
        jPanel13 = new javax.swing.JPanel();
        jButtonPrev = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNameDrugSet = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxNameDoctor = new javax.swing.JComboBox();
        jRadioButtonGroupService = new javax.swing.JRadioButton();
        jRadioButtonGroupDoctor = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableItem = new com.hosv3.gui.component.HJTableSort();
        jPanel6 = new javax.swing.JPanel();
        jButtonAddItem = new javax.swing.JButton();
        jButtonDelItem = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanelBlank = new javax.swing.JPanel();
        jPanelDrug = new javax.swing.JPanel();
        jCheckBoxSpecialUsage = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jLabelDrugInstruction = new javax.swing.JLabel();
        jTextFieldDescription = new javax.swing.JTextField();
        jTextFieldCaution = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanelSPU = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jTextFieldUseUnit = new javax.swing.JTextField();
        jComboBoxInstruction = new com.hosv3.gui.component.HosComboBox();
        jComboBoxUse = new com.hosv3.gui.component.HosComboBox();
        jComboBoxFrequency = new com.hosv3.gui.component.HosComboBox();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextAreaTextUsage = new javax.swing.JTextArea();
        jPanel11 = new javax.swing.JPanel();
        jButtonSaveDose = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldQtyUnit = new javax.swing.JTextField();
        jComboBoxQty1 = new com.hosv3.gui.component.HosComboBox();
        jPanel1 = new javax.swing.JPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonDel = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(fontFormatTitle1);
        jLabel4.setText("\u0e23\u0e32\u0e22\u0e01\u0e32\u0e23\u0e22\u0e32\u0e0a\u0e38\u0e14");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jPanel4.add(jLabel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanel4, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setMinimumSize(new java.awt.Dimension(300, 25));
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 404));
        jLabelICD9code.setFont(defaultFont1);
        jLabelICD9code.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("SEARCH"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 11);
        jPanel3.add(jLabelICD9code, gridBagConstraints);

        jTextFieldSCode.setFont(defaultFont1);
        jTextFieldSCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSCodeActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel3.add(jTextFieldSCode, gridBagConstraints);

        jButtonSearch.setFont(defaultFont1);
        jButtonSearch.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("SEARCH"));
        jButtonSearch.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSearch.setMaximumSize(new java.awt.Dimension(67, 25));
        jButtonSearch.setMinimumSize(new java.awt.Dimension(67, 25));
        jButtonSearch.setPreferredSize(new java.awt.Dimension(67, 25));
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel3.add(jButtonSearch, gridBagConstraints);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(100, 22));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 80));
        jTableGroup.setModel(tableResultsModel1);
        jTableGroup.setFont(defaultFont1);
        jTableGroup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableGroupMouseReleased(evt);
            }
        });
        jTableGroup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableGroupKeyReleased(evt);
            }
        });

        jScrollPane1.setViewportView(jTableGroup);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(jScrollPane1, gridBagConstraints);

        jPanel14.setLayout(new java.awt.GridBagLayout());

        jPanel12.setLayout(new java.awt.GridBagLayout());

        jCheckBox1.setFont(defaultFont1);
        jCheckBox1.setText("\u0e41\u0e1e\u0e17\u0e22\u0e4c");
        jCheckBox1.setMaximumSize(new java.awt.Dimension(70, 25));
        jCheckBox1.setMinimumSize(new java.awt.Dimension(70, 25));
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        jPanel12.add(jCheckBox1, gridBagConstraints);

        jComboBoxNameDoctor1.setFont(defaultFont1);
        jComboBoxNameDoctor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxNameDoctor1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel12.add(jComboBoxNameDoctor1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel14.add(jPanel12, gridBagConstraints);

        jPanel13.setLayout(new java.awt.GridBagLayout());

        jButtonPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Back16.gif")));
        jButtonPrev.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonPrev.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonPrev.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrevActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel13.add(jButtonPrev, gridBagConstraints);

        jButtonNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Forward16.gif")));
        jButtonNext.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonNext.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonNext.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel13.add(jButtonNext, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel14.add(jPanel13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel3.add(jPanel14, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        add(jPanel3, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMinimumSize(new java.awt.Dimension(350, 100));
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 100));
        jLabel2.setFont(defaultFont1);
        jLabel2.setText("\u0e01\u0e25\u0e38\u0e48\u0e21");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 2, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        jTextFieldNameDrugSet.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 2, 5);
        jPanel2.add(jTextFieldNameDrugSet, gridBagConstraints);

        jLabel5.setFont(defaultFont1);
        jLabel5.setText("\u0e40\u0e08\u0e49\u0e32\u0e02\u0e2d\u0e07\u0e22\u0e32\u0e0a\u0e38\u0e14");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 2, 5);
        jPanel2.add(jLabel5, gridBagConstraints);

        jComboBoxNameDoctor.setFont(defaultFont1);
        jComboBoxNameDoctor.setMaximumSize(new java.awt.Dimension(150, 24));
        jComboBoxNameDoctor.setMinimumSize(new java.awt.Dimension(150, 24));
        jComboBoxNameDoctor.setPreferredSize(new java.awt.Dimension(150, 24));
        jComboBoxNameDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxNameDoctorActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 2, 5);
        jPanel2.add(jComboBoxNameDoctor, gridBagConstraints);

        buttonGroupTypeOner.add(jRadioButtonGroupService);
        jRadioButtonGroupService.setFont(defaultFont1);
        jRadioButtonGroupService.setText("\u0e08\u0e38\u0e14\u0e1a\u0e23\u0e34\u0e01\u0e32\u0e23");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 2, 5);
        jPanel2.add(jRadioButtonGroupService, gridBagConstraints);

        buttonGroupTypeOner.add(jRadioButtonGroupDoctor);
        jRadioButtonGroupDoctor.setFont(defaultFont1);
        jRadioButtonGroupDoctor.setText("\u0e41\u0e1e\u0e17\u0e22\u0e4c");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 2, 5);
        jPanel2.add(jRadioButtonGroupDoctor, gridBagConstraints);

        jLabel3.setFont(defaultFont1);
        jLabel3.setText("\u0e0a\u0e37\u0e48\u0e2d\u0e0a\u0e38\u0e14");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 2, 5);
        jPanel2.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        add(jPanel2, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setMinimumSize(new java.awt.Dimension(350, 180));
        jPanel5.setPreferredSize(new java.awt.Dimension(350, 180));
        jScrollPane2.setFont(defaultFont1);
        jScrollPane2.setMaximumSize(new java.awt.Dimension(200, 230));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(200, 230));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(200, 230));
        jTableItem.setModel(tableResultsModel1);
        jTableItem.setFont(defaultFont1);
        jTableItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableItemMouseReleased(evt);
            }
        });
        jTableItem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableItemFocusGained(evt);
            }
        });
        jTableItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableItemKeyReleased(evt);
            }
        });

        jScrollPane2.setViewportView(jTableItem);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel5.add(jScrollPane2, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jPanel6.setFont(defaultFont1);
        jButtonAddItem.setFont(defaultFont1);
        jButtonAddItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif")));
        jButtonAddItem.setEnabled(false);
        jButtonAddItem.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonAddItem.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonAddItem.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonAddItem.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddItemActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        jPanel6.add(jButtonAddItem, gridBagConstraints);

        jButtonDelItem.setFont(defaultFont1);
        jButtonDelItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif")));
        jButtonDelItem.setEnabled(false);
        jButtonDelItem.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonDelItem.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDelItem.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDelItem.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonDelItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelItemActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(jButtonDelItem, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel5.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        add(jPanel5, gridBagConstraints);

        jPanel7.setLayout(new java.awt.CardLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelBlank.setLayout(new java.awt.GridBagLayout());

        jPanel7.add(jPanelBlank, "CARD_BLANK");

        jPanelDrug.setLayout(new java.awt.GridBagLayout());

        jCheckBoxSpecialUsage.setFont(defaultFont1);
        jCheckBoxSpecialUsage.setText("\u0e27\u0e34\u0e18\u0e35\u0e01\u0e32\u0e23\u0e43\u0e0a\u0e49\u0e22\u0e32\u0e1e\u0e34\u0e40\u0e28\u0e29");
        jCheckBoxSpecialUsage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSpecialUsageActionPerformed(evt);
            }
        });
        jCheckBoxSpecialUsage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCheckBoxSpecialUsageKeyReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanelDrug.add(jCheckBoxSpecialUsage, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jPanel8.setMinimumSize(new java.awt.Dimension(219, 55));
        jPanel8.setPreferredSize(new java.awt.Dimension(219, 55));
        jLabelDrugInstruction.setFont(defaultFont1);
        jLabelDrugInstruction.setText("\u0e23\u0e32\u0e22\u0e25\u0e30\u0e40\u0e2d\u0e35\u0e22\u0e14");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 2);
        jPanel8.add(jLabelDrugInstruction, gridBagConstraints);

        jTextFieldDescription.setFont(defaultFont1);
        jTextFieldDescription.setMinimumSize(new java.awt.Dimension(160, 24));
        jTextFieldDescription.setPreferredSize(new java.awt.Dimension(160, 24));
        jTextFieldDescription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldDescriptionKeyReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 2);
        jPanel8.add(jTextFieldDescription, gridBagConstraints);

        jTextFieldCaution.setFont(defaultFont1);
        jTextFieldCaution.setMinimumSize(new java.awt.Dimension(160, 24));
        jTextFieldCaution.setPreferredSize(new java.awt.Dimension(160, 24));
        jTextFieldCaution.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCautionKeyReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        jPanel8.add(jTextFieldCaution, gridBagConstraints);

        jLabel8.setFont(defaultFont1);
        jLabel8.setText("\u0e04\u0e33\u0e40\u0e15\u0e37\u0e2d\u0e19");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        jPanel8.add(jLabel8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanelDrug.add(jPanel8, gridBagConstraints);

        jPanelSPU.setLayout(new java.awt.CardLayout());

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jPanel10.setMinimumSize(new java.awt.Dimension(156, 55));
        jPanel10.setPreferredSize(new java.awt.Dimension(156, 55));
        jTextFieldUseUnit.setColumns(3);
        jTextFieldUseUnit.setFont(defaultFont1);
        jTextFieldUseUnit.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldUseUnit.setMinimumSize(new java.awt.Dimension(60, 24));
        jTextFieldUseUnit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldUseUnitKeyReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 3);
        jPanel10.add(jTextFieldUseUnit, gridBagConstraints);

        jComboBoxInstruction.setFont(defaultFont1);
        jComboBoxInstruction.setMinimumSize(new java.awt.Dimension(121, 24));
        jComboBoxInstruction.setPreferredSize(new java.awt.Dimension(121, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 3, 0, 3);
        jPanel10.add(jComboBoxInstruction, gridBagConstraints);

        jComboBoxUse.setFont(defaultFont1);
        jComboBoxUse.setMinimumSize(new java.awt.Dimension(81, 24));
        jComboBoxUse.setPreferredSize(new java.awt.Dimension(81, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 5);
        jPanel10.add(jComboBoxUse, gridBagConstraints);

        jComboBoxFrequency.setFont(defaultFont1);
        jComboBoxFrequency.setMinimumSize(new java.awt.Dimension(121, 24));
        jComboBoxFrequency.setPreferredSize(new java.awt.Dimension(121, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 3, 0, 5);
        jPanel10.add(jComboBoxFrequency, gridBagConstraints);

        jPanelSPU.add(jPanel10, "Normal");

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jPanel9.setMinimumSize(new java.awt.Dimension(156, 55));
        jPanel9.setPreferredSize(new java.awt.Dimension(156, 55));
        jScrollPane11.setMinimumSize(new java.awt.Dimension(22, 45));
        jScrollPane11.setPreferredSize(new java.awt.Dimension(22, 45));
        jTextAreaTextUsage.setLineWrap(true);
        jTextAreaTextUsage.setWrapStyleWord(true);
        jScrollPane11.setViewportView(jTextAreaTextUsage);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(jScrollPane11, gridBagConstraints);

        jPanelSPU.add(jPanel9, "Special");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanelDrug.add(jPanelSPU, gridBagConstraints);

        jPanel11.setLayout(new java.awt.GridBagLayout());

        jButtonSaveDose.setFont(defaultFont1);
        jButtonSaveDose.setText("\u0e1a\u0e31\u0e19\u0e17\u0e36\u0e01");
        jButtonSaveDose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveDoseActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 2);
        jPanel11.add(jButtonSaveDose, gridBagConstraints);

        jLabel7.setFont(defaultFont1);
        jLabel7.setText("\u0e08\u0e33\u0e19\u0e27\u0e19");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 2);
        jPanel11.add(jLabel7, gridBagConstraints);

        jTextFieldQtyUnit.setColumns(3);
        jTextFieldQtyUnit.setFont(defaultFont1);
        jTextFieldQtyUnit.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldQtyUnit.setMinimumSize(new java.awt.Dimension(60, 24));
        jTextFieldQtyUnit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldQtyUnitFocusGained(evt);
            }
        });
        jTextFieldQtyUnit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldQtyUnitKeyReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 2);
        jPanel11.add(jTextFieldQtyUnit, gridBagConstraints);

        jComboBoxQty1.setMinimumSize(new java.awt.Dimension(81, 24));
        jComboBoxQty1.setPreferredSize(new java.awt.Dimension(81, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 2);
        jPanel11.add(jComboBoxQty1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanelDrug.add(jPanel11, gridBagConstraints);

        jPanel7.add(jPanelDrug, "CARD_DRUG");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        add(jPanel7, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButtonAdd.setFont(defaultFont1);
        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif")));
        jButtonAdd.setMargin(new java.awt.Insets(0, 0, 0, 0));
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
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        jPanel1.add(jButtonAdd, gridBagConstraints);

        jButtonDel.setFont(defaultFont1);
        jButtonDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif")));
        jButtonDel.setEnabled(false);
        jButtonDel.setMargin(new java.awt.Insets(0, 0, 0, 0));
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
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel1.add(jButtonDel, gridBagConstraints);

        jButtonSave.setFont(defaultFont1);
        jButtonSave.setText("\u0e1a\u0e31\u0e19\u0e17\u0e36\u0e01");
        jButtonSave.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSave.setMinimumSize(new java.awt.Dimension(60, 24));
        jButtonSave.setPreferredSize(new java.awt.Dimension(60, 24));
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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        add(jPanel1, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxSpecialUsageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSpecialUsageActionPerformed
        if(jCheckBoxSpecialUsage.isSelected())
            showItemSubProperty("Special");
        else
            showItemSubProperty("Normal");
    }//GEN-LAST:event_jCheckBoxSpecialUsageActionPerformed

    private void jCheckBoxSpecialUsageKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCheckBoxSpecialUsageKeyReleased
//      recordDoseInVector();
    }//GEN-LAST:event_jCheckBoxSpecialUsageKeyReleased

    private void jTextFieldDescriptionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDescriptionKeyReleased
//      recordDoseInVector();
    }//GEN-LAST:event_jTextFieldDescriptionKeyReleased

    private void jTextFieldCautionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCautionKeyReleased
//     recordDoseInVector();
    }//GEN-LAST:event_jTextFieldCautionKeyReleased

    private void jTextFieldUseUnitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldUseUnitKeyReleased
//      recordDoseInVector();
    }//GEN-LAST:event_jTextFieldUseUnitKeyReleased

    private void jButtonSaveDoseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveDoseActionPerformed
// TODO add your handling code here:
        if(jTableItem.getSelectedRow() != -1)
        {
            recordDoseInVector();
            //theSetupControl.saveDrugSet(drugSet,theDrugSetGroup.getObjectId());
        }
    }//GEN-LAST:event_jButtonSaveDoseActionPerformed

    private void jTextFieldQtyUnitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldQtyUnitFocusGained
//        if(jTextFieldQtyUnit.getText().length()==0) {
//            Gutil.setGuiData(jComboBoxQty,Gutil.getGuiData(jComboBoxUse));
//        }
    }//GEN-LAST:event_jTextFieldQtyUnitFocusGained

    private void jTextFieldQtyUnitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldQtyUnitKeyReleased
//    recordDoseInVector();
    }//GEN-LAST:event_jTextFieldQtyUnitKeyReleased

    private void jComboBoxNameDoctor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxNameDoctor1ActionPerformed
        //pu : 25/07/2549 : กำหนดค่าให้กับ Index สำหรับระบุถึงหน้าปัจจุบันของรายการตรวจรักษา
        this.curNext = 0;
        this.curPrev = 0;
        searchDrugSet();
    }//GEN-LAST:event_jComboBoxNameDoctor1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if(jCheckBox1.isSelected())
        {
            jComboBoxNameDoctor1.setEnabled(true);
        }
        else
        {
            jComboBoxNameDoctor1.setEnabled(false);
        }
        //pu : 25/07/2549 : กำหนดค่าให้กับ Index สำหรับระบุถึงหน้าปัจจุบันของรายการตรวจรักษา
        this.curNext = 0;
        this.curPrev = 0;
        searchDrugSet();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTableItemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableItemKeyReleased
        if(evt.getKeyCode()==evt.VK_UP || evt.getKeyCode()==evt.VK_DOWN){
            if(jTableItem.getSelectedRow()==-1) return;  
            jTableGroup.clearSelection();
            selectTableItemList();          
        }
    }//GEN-LAST:event_jTableItemKeyReleased

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
   //     recordDoseInVector();
    }//GEN-LAST:event_formKeyReleased

    public void recordDoseInVector()
    {        
        Constant.println("public void recordDoseInVector(DoseDrugSet dds)1");
        int row = jTableItem.getSelectedRow();  
        recordDoseInVector(row);
    }
    public void recordDoseInVector(int row)
    {       
        Constant.println("public void recordDoseInVector(DoseDrugSet dds)2");
        if(row>0 && row<drugSet.size())
        {
            DrugSet ds = (DrugSet)drugSet.get(row);
            ds.dose_drug_set = getDrugDose();
        }
    }
    public DoseDrugSet getDrugDose()
    {        
        Constant.println("public void recordDoseInVector(DoseDrugSet dds)3");
        DoseDrugSet dds = new DoseDrugSet();
            dds.item_code = theDrugSet.item_code;
            dds.caution = jTextFieldCaution.getText();                 
            dds.description = jTextFieldDescription.getText();
            dds.dose = jTextFieldUseUnit.getText();
            dds.frequency = jComboBoxFrequency.getText(); 
            dds.instruction = jComboBoxInstruction.getText(); 
            dds.purch_uom = jComboBoxQty1.getText(); 
            dds.qty = jTextFieldQtyUnit.getText();            
            if(jCheckBoxSpecialUsage.isSelected())
                dds.usage_special = "1";
            else
                dds.usage_special = "0";
            dds.usage_text = jTextAreaTextUsage.getText();
            dds.use_uom = jComboBoxUse.getText();             
            dds.printting = theItem.printable;
            return dds;
    }
    private void jTableItemFocusGained(java.awt.event.FocusEvent evt) {                                    

    }                                                                          

    private void jTableItemMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableItemMouseReleased
	//recordDoseInVector(old_row);
        if(jTableItem.getSelectedRow()==-1) return;
        selectTableItemList();
        old_row = jTableItem.getSelectedRow();
                
    }//GEN-LAST:event_jTableItemMouseReleased

   private void jComboBoxNameDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxNameDoctorActionPerformed

    }//GEN-LAST:event_jComboBoxNameDoctorActionPerformed

    private void jButtonAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddItemActionPerformed
        showItem();
        jButtonDelItem.setEnabled(true);
    }//GEN-LAST:event_jButtonAddItemActionPerformed

    private void jButtonDelItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelItemActionPerformed
        deleteItem();
    }//GEN-LAST:event_jButtonDelItemActionPerformed

    private void jButtonDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelActionPerformed
        deleteDrugSet();
        setEnableItem(false);
        this.jButtonSearchActionPerformed(null);
    }//GEN-LAST:event_jButtonDelActionPerformed

    private void jTextFieldSCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSCodeActionPerformed
        //pu : 25/08/2549 : กำหนดค่าให้กับ Index สำหรับระบุถึงหน้าปัจจุบันของรายการ
        this.curNext = 0;
        this.curPrev = 0;
        searchDrugSet();
        clearForm();
    }//GEN-LAST:event_jTextFieldSCodeActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        recordDoseInVector();
        saveDrugSet();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        clearForm();
        drugSet = null;
        jTextFieldNameDrugSet.setText("");
        jTextFieldNameDrugSet.requestFocus();
        theDrugSetGroup = new DrugSetGroup();
        showItemProperty(PanelSetupDoctorDrugSet.CARD_BLANK);
        insertDrugSet();
        setEnableItem(true);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        nextDrugSet();
    }//GEN-LAST:event_jButtonNextActionPerformed

    private void jButtonPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrevActionPerformed
        prevDrugSet();
    }//GEN-LAST:event_jButtonPrevActionPerformed

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        //pu : 25/08/2549 : กำหนดค่าให้กับ Index สำหรับระบุถึงหน้าปัจจุบันของรายการ
        this.curNext = 0;
        this.curPrev = 0;
        searchDrugSet();
        clearForm();
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jTableGroupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableGroupMouseReleased
        
        showItemProperty(PanelSetupDoctorDrugSet.CARD_BLANK);
        jTableItem.clearSelection();
        selectDrugSet();
        jButtonDel.setEnabled(true);
        setEnableItem(true);

    }//GEN-LAST:event_jTableGroupMouseReleased

    private void jTableGroupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableGroupKeyReleased
	if(evt.getKeyCode()==evt.VK_UP || evt.getKeyCode()==evt.VK_DOWN){
            showItemProperty(PanelSetupDoctorDrugSet.CARD_BLANK);
            selectDrugSet();
            jButtonDel.setEnabled(true);
            setEnableItem(true);
        }
    }//GEN-LAST:event_jTableGroupKeyReleased
    private void nextDrugSet()
    {   
        setTableDrugSet(drugSetGroup,1);
    }
    private void prevDrugSet()
    {   
        setTableDrugSet(drugSetGroup,0);
    }
    private void clearAll()
    {           
        jTextFieldNameDrugSet.setText("");
    }
    private void insertDrugSet()
    {   
        saved = 1;
        clearAll();
        //setTableItem(null);        
    }
    private void deleteDrugSet()
    {   
        int ret = 0;
        ret = theSetupControl.deleteDrugSetGroup(theDrugSetGroup);        
        if(ret > 0)
        {
            clearAll();
            //pu : 25/08/2549 : เก็บ Index ปัจจุบันของหน้ารายการที่กำลังบันทึก
            int count = next - prev;
            this.curNext = next - count;
            this.curPrev = prev - offset;
            searchDrugSet();
            setTableItem(null);
        }
    }
    private void setEnableItem(boolean b)
    {
        jButtonAddItem.setEnabled(b);
        jButtonDelItem.setEnabled(b);
    }
    private void searchDrugSet()
    {
        //pu : 25/08/2549 : กำหนดค่า Index ให้กับหน้าที่ต้องการแสดงรายการ
        next = this.curNext;
        prev =  this.curPrev;
        String search = jTextFieldSCode.getText();
        String Doctor = "";
        if(jCheckBox1.isSelected())
        {
            Doctor = Gutil.getGuiData(jComboBoxNameDoctor1);
        }
        String active="0";
        if(jCheckBox1.isSelected())   active = "1";
        
        drugSetGroup =  theSetupControl.listDrugSetGroup(search,Doctor);
        setTableDrugSet(drugSetGroup,1);
    }
    private void setTableDrugSet(Vector voffice,int off)
    {
        DrugSetGroup dsg = new DrugSetGroup();
        int count = offset;
        int p =0;
        int n =0;
        int c =0;
        if(voffice != null && voffice.size() != 0)
        {
            if(off == 1)
            {   p = prev;
                n = next;
                prev = next;
                next = next + offset;
                if(next >= drugSetGroup.size())
                {   next = drugSetGroup.size();
                    count = next - prev;
                }
                if(count == 0)
                {   prev = p;
                    next = n;
                    count = n - p;
                }
            }
            else
            {   next = prev;
                prev = prev - offset;
                if(prev <=0)
                {    prev = 0;
                     next = offset;
                }
                if(next >= drugSetGroup.size())
                {   next= drugSetGroup.size();
                    count = next;
                }
            }
            TaBleModel tm = new TaBleModel(col_TaBleModel,count);
            for(int i=0 ;i<count;i++)
            {
                dsg = (DrugSetGroup)voffice.get(i+prev);
                String doctor = dsg.oner_drug;
                tm.setValueAt(dsg.descroption,i,0);
                theEmployee = theLookupControl.readEmployeeById(doctor);
                if( theEmployee != null)
                {                    
                    String fname = theEmployee.fname;
                    String lname = theEmployee.lname;
                    if((fname!= null || fname.trim().length() >0) || (lname!=null || lname.trim().length() > 0) )
                        tm.setValueAt(fname + " " + lname,i,1);
                    else
                        tm.setValueAt( " " ,i,1);
                    fname = null;
                    lname = null;
                }
            }
            jTableGroup.setModel(tm);
            jTableGroup.getColumnModel().getColumn(0).setPreferredWidth(80); // ชื่อยาชุด
            jTableGroup.getColumnModel().getColumn(1).setPreferredWidth(100); // ชื่อแพทย์เจ้าของยาชุด
        }
        else
        {   TaBleModel tm = new TaBleModel(col,0);
            jTableGroup.setModel(tm);
        }
        if(jTableGroup.getRowCount()==1)
        {
            jTableGroup.setRowSelectionInterval(0,0);
            jTableGroupMouseReleased(null);
        }
    }
       /* for(int i=0 ;i<count;i++)
        {
            dsg = (DrugSetGroup)voffice.get(i+prev);
            String doctor = dsg.oner_drug;
            theEmployee = theLookupControl.readEmployeeById(doctor);
            if( theEmployee != null)
            {
                if(dsg.descroption!=null)
                {   try
                    {
                        tm.setValueAt(dsg.descroption,i,0);
                        String fname = theEmployee.fname;
                        String lname = theEmployee.lname;
                        if((fname!= null || fname.trim().length() >0) || (lname!=null || lname.trim().length() > 0) )
                            tm.setValueAt(fname + " " + lname,i,1);
                        else
                            tm.setValueAt( " " ,i,1);
                        fname = null;
                        lname = null;
                    }
                    catch(Exception ex)
                    {  ex.printStackTrace(Constant.getPrintStream());
                    }
                }
        
            }
        }
        jTableGroup.setModel(tm);
    }
    else
    {   TaBleModel tm = new TaBleModel(col_TaBleModel,0);
        jTableGroup.setModel(tm);
    }
}*/
    private void setTableItem(Vector voffice)
    {
        DrugSet ds = new DrugSet();
        TaBleModel tm ;
        if(voffice != null)
        {   tm = new TaBleModel(col,voffice.size());
            for(int i=0 ;i<voffice.size(); i++)
            {
                ds = (DrugSet)voffice.get(i);
                String item_code = ds.item_code;
                theItem = theSetupControl.listItemByPk(item_code);
                tm.setValueAt(theItem.common_name,i,0);
            }
            jTableItem.setModel(tm);
        }
        else
        {
            tm= new TaBleModel(col,0);
            jTableItem.setModel(tm);
        }
    }
    private void saveDrugSet()
    {
        int ret = 0;
        theDrugSetGroup.descroption = jTextFieldNameDrugSet.getText();
        theDrugSetGroup.oner_drug = ComboboxModel.getCodeComboBox(jComboBoxNameDoctor);
        ret = theSetupControl.saveDrugSetGroup(theDrugSetGroup,drugSet);
        if(ret > 0)
        {
            //pu : 25/07/2549 : เก็บ Index ปัจจุบันของหน้ารายการตรวจรักษาที่กำลังบันทึก
            int count = next - prev;
            this.curNext = next - count;
            this.curPrev = prev - offset;
            saved =0;
            searchDrugSet();
        }
    }
    
    private void selectDrugSet()
    {
        clearDose();
        saved = 0;
        int row = jTableGroup.getSelectedRow();
        row = row  + prev;
        theDrugSetGroup = (DrugSetGroup)drugSetGroup.get(row);
        jTextFieldNameDrugSet.setText(theDrugSetGroup.descroption);
        ComboboxModel.setCodeComboBox(jComboBoxNameDoctor, theDrugSetGroup.oner_drug);
        drugSet = theSetupControl.listDrugSetByGroup(theDrugSetGroup.getObjectId());
        setTableItem(drugSet);
        if(this.jTableItem.getRowCount()>0)
        {
            this.jTableItem.setRowSelectionInterval(0,0);
            selectTableItemList();
        }
    }
    private void showItem()
    {
        if(psep==null)
        {
            psep = new PanelSetupSearchSub(theHC,theUS,6);
            psep.setTitle(Constant.getTextBundle("แสดงรายการตรวจรักษา"));
            if(drugSet==null)
                drugSet = new Vector();
            psep.showSearchDrug(jTableItem,drugSet);
        }
        psep = null;
    }
    private void deleteItem()
    {
        int row = jTableItem.getSelectedRow();
        DrugSet ds = new DrugSet();
        DoseDrugSet dds = new DoseDrugSet();
        if(row > -1)
        {
            ds = (DrugSet)drugSet.get(row);
            if(ds != null)
            {
                if(ds.getObjectId()!= null)
                {
                    theSetupControl.deleteDrugSetReq(ds);
                    if(((DrugSet)drugSet.get(row)).dose_drug_set !=null)
                    {
                        dds = ((DrugSet)drugSet.get(row)).dose_drug_set;
                        theSetupControl.deleteDoseDrugSet(dds);
                    }
                }
            }
            drugSet.remove(row);
            setTableItem(drugSet);
        }
        else
        {
            theUS.setStatus("กรุณาเลือกรายการ Order ก่อนทำการกดปุ่มลบ",theUS.WARNING);
            return;
        }
        ds = null;
    }
    public void showItemProperty(String type)
    {
        CardLayout layout = (CardLayout)jPanel7.getLayout();
        layout.show(jPanel7, type);
    }
    public void showItemSubProperty(String type)
    {
        CardLayout layout = (CardLayout)jPanelSPU.getLayout();
        layout.show(jPanelSPU, type);
    }
    private void selectTableItemList()
    {
        int row = jTableItem.getSelectedRow();
        theDrugSet = (DrugSet)drugSet.get(row);
        if(theDrugSet != null)
            theItem = theSetupControl.listItemByPk(theDrugSet.item_code);
        if(theItem != null)
        {
            theCategoryGroupItem = theLookupControl.readCategoryGroupItemById(theItem.item_group_code_category);
            if (theCategoryGroupItem!=null && theCategoryGroupItem.category_group_code.equals("1"))
            {
                showItemProperty(PanelSetupDoctorDrugSet.CARD_DRUG);
                if(theDrugSet.dose_drug_set !=null)
                {
                    if(theDrugSet.dose_drug_set.usage_special.trim().equals("1"))
                    {        jCheckBoxSpecialUsage.setSelected(true);
                             showItemSubProperty("Special");
                    }
                    else
                    {
                        jCheckBoxSpecialUsage.setSelected(false);
                        showItemSubProperty("Normal");
                    }
                    jTextFieldDescription.setText(theDrugSet.dose_drug_set.description);
                    jTextFieldCaution.setText(theDrugSet.dose_drug_set.caution);
                    jTextFieldQtyUnit.setText(theDrugSet.dose_drug_set.qty);
                    jComboBoxQty1.setText(theDrugSet.dose_drug_set.purch_uom);
                    jTextAreaTextUsage.setText(theDrugSet.dose_drug_set.usage_text);
                    jTextFieldUseUnit.setText(theDrugSet.dose_drug_set.dose);
                    jComboBoxUse.setText(theDrugSet.dose_drug_set.use_uom);
                    jComboBoxInstruction.setText(theDrugSet.dose_drug_set.instruction);
                    jComboBoxFrequency.setText(theDrugSet.dose_drug_set.frequency);
                }
                else
                {
                    Drug drug = new Drug();
                    drug = theOrderControl.listDrugByItem(theItem.getObjectId());
                    if(drug != null)
                    {
                        if(drug.usage_special.trim().equals("1"))
                        {      
                            jCheckBoxSpecialUsage.setSelected(true);
                            showItemSubProperty("Special");
                        }
                        else
                        {
                            jCheckBoxSpecialUsage.setSelected(false);
                            showItemSubProperty("Normal");
                        }
                        jTextFieldDescription.setText(drug.description);
                        jTextFieldCaution.setText(drug.caution);
                        jTextFieldQtyUnit.setText(drug.qty);
                        jComboBoxQty1.setText(drug.purch_uom);
                        jTextAreaTextUsage.setText(drug.usage_text);
                        jTextFieldUseUnit.setText(drug.dose);
                        jComboBoxUse.setText(drug.use_uom);
                        jComboBoxInstruction.setText(drug.instruction);
                        jComboBoxFrequency.setText(drug.frequency);
                    }
                    else
                    {
                        clearDose();
                    }
                }
            }
            else
                showItemProperty(PanelSetupDoctorDrugSet.CARD_BLANK);
        }
    }
    private void clearForm()
    {
        setTableItem(null);
        clearDose();
    }
    private void clearDose()
    {
        jCheckBoxSpecialUsage.setSelected(false);
        showItemSubProperty("Normal");
        jTextFieldDescription.setText("");
        jTextFieldCaution.setText("");
        jTextFieldQtyUnit.setText("");
        jTextAreaTextUsage.setText("");
        jTextFieldUseUnit.setText("");
    }
    public void notifyreFrashPanel()        
    {
        setupLookup();
    }
    public void notifysetEnableForLift(boolean b)
    {
    }
    public int deleteItemByPk(String pk)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return -1;
    }
    public int deleteItemPrice(ItemPrice itemprice)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return -1;
    }
    public int deleteLabResultItem(LabResultItem lri)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return -1;
    }
    public void deleteLabSet(LabSet labset)
    {
    }
    public String deleteMedSupplyUsage(Vector v)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public String deleteService(Vector v)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public int editDrug(Drug drug)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return -1;
    }
    public int editItem(Item item)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return -1;
    }
    public int editItemPrice(Vector itemprice)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return -1;
    }
    public int editLabResultItem(Vector labresult)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return -1;
    }
    public String editService(OrderItem service)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public CategoryGroupItem listCategoryGroupByPk(String pk)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public Vector listItemByGroup(String itemGpID, String itemname, String active)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public Item listItemByItemIdReq(String itemid)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public Vector listItemByName(String itemName)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public Item listItemByPk(String pk)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public Item listItemByPkey(String pk)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public Vector listItemDrugAndSupply(String itemname)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public Vector listItemPrice(String pkItem_id)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public Vector listItemPriceByItemID(String pk)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public LabGroup listLabGroupByItem(String item_id)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public Vector listLabSetByLabGroupID(String labgroup_id)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public Vector listOrderMedSupplyeAllVisit(String hn, String ordertype)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public Vector listOrderServiceAllVisit(String hn, String ordertype)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public Vector listSearchItem(String pk, int sh, String cgc)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public Vector listSearchItemDrug(String pk, int sh, String cgc)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public void notifylistSearchItem(Item item)
    {
    }
    public void notifylistSearchItemDrug(Item drug)
    {
    }
    public void notifysaveItem(Item item)
    {
    }
    public void notifyselectItem(DxTemplate dxtemplate)
    {
    }
    public Drug readDrug(String pkItem_id)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public String readItemById(String itemId)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public Vector readLabResultItemByItem(String pk)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public int saveDrug(Drug drug)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return -1;
    }
    public int saveItem(Item item, Vector itemprice, Drug drug, Vector labresultitem, LabGroup labgroup, Vector labset)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return -1;
    }
    public int saveItemPrice(Vector itemprice, String item_id)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return -1;
    }
    public void saveLabGroupLabSet(LabGroup labgroup, Vector labset)
    {
    }
    public int saveLabResultItem(Vector labresultitem, String item_id)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return -1;
    }
    public int editOption(Option option)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return -1;
    }
    public Vector listOptionAll()
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public void reFrashPanel()
    {
    }
    public Option readOption()
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return null;
    }
    public int saveOption(Option option)
    {
        Constant.println("PanelSetupDoctorDrugSet function is not use.");
        return -1;
    }
    public void setEnableForLift(boolean b)
    {
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupTypeOner;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.hospital_os.gui.font.FontFormatTitle fontFormatTitle1;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAddItem;
    private javax.swing.JButton jButtonDel;
    private javax.swing.JButton jButtonDelItem;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrev;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSaveDose;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBoxSpecialUsage;
    private com.hosv3.gui.component.HosComboBox jComboBoxFrequency;
    private com.hosv3.gui.component.HosComboBox jComboBoxInstruction;
    private javax.swing.JComboBox jComboBoxNameDoctor;
    private javax.swing.JComboBox jComboBoxNameDoctor1;
    private com.hosv3.gui.component.HosComboBox jComboBoxQty1;
    private com.hosv3.gui.component.HosComboBox jComboBoxUse;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelDrugInstruction;
    private javax.swing.JLabel jLabelICD9code;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelBlank;
    private javax.swing.JPanel jPanelDrug;
    private javax.swing.JPanel jPanelSPU;
    private javax.swing.JRadioButton jRadioButtonGroupDoctor;
    private javax.swing.JRadioButton jRadioButtonGroupService;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private com.hosv3.gui.component.HJTableSort jTableGroup;
    private com.hosv3.gui.component.HJTableSort jTableItem;
    private javax.swing.JTextArea jTextAreaTextUsage;
    private javax.swing.JTextField jTextFieldCaution;
    private javax.swing.JTextField jTextFieldDescription;
    private javax.swing.JTextField jTextFieldNameDrugSet;
    private javax.swing.JTextField jTextFieldQtyUnit;
    private javax.swing.JTextField jTextFieldSCode;
    private javax.swing.JTextField jTextFieldUseUnit;
    private com.hospital_os.utility.TableResultsModel tableResultsModel1;
    // End of variables declaration//GEN-END:variables
}
