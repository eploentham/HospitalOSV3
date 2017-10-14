/*
 * PanelSetupGroupIcd10.java
 *
 * Created on 11 ���Ҥ� 2546, 15:22 �.
 */
package com.hosv3.gui.panel.setup;
import java.awt.*;
import java.util.Vector;
import java.awt.event.*;
import javax.swing.*;

import com.hosv3.usecase.setup.*;
import com.hosv3.control.*;
import com.hosv3.utility.connection.*;
import com.hosv3.utility.GuiLang;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import com.hosv3.object.LookupObject;
/**
 *
 * @panel author : amp
 * @panel Update : tong
 */
public class PanelSetupGroupIcd10 extends javax.swing.JPanel implements 
ManageOptionReq
{
    UpdateStatus theUS;
    SetupControl theSetupControl;
    LookupControl theLookupControl;
    LookupObject theLookupObject;
    GroupIcd10 theGroupIcd10;
    Vector vGroupIcd10;
    int offset = 24;
    int next = 0;
    int prev = 0;
    int saved = 0; // 0 ��� �������ö insert�� 1 ��� insert ��
    String[] col = {"icd","504","505","506","chronic",GuiLang.setLanguage("disease")};
    CellRendererToolTipText theCellRendererToolTipText = new CellRendererToolTipText(true);
    public PanelSetupGroupIcd10() 
    {
        initComponents();
        setLanguage(); 
    }
    public PanelSetupGroupIcd10(HosControl hc,UpdateStatus us){
        initComponents();
        setLanguage();        
		setControl(hc,us);
                theLookupObject = hc.theLO;
		jTable1.setGuiMode(true);
    }    /////////////////////Use this for decrease memory usage
	   /**
     *@Author : amp
     *@date : 29/02/2549
     *@see : �Ѵ�������ǡѺ����    
     */
    private void setLanguage()
    {
        GuiLang.setLanguage(jLabel3);
        GuiLang.setLanguage(jLabelICD9code);
        GuiLang.setLanguage(jButtonSearch);
        GuiLang.setLanguage(jLabel1);        
        GuiLang.setLanguage(jLabel2);
        GuiLang.setLanguage(jLabel4);
        GuiLang.setLanguage(jLabel5);
        GuiLang.setLanguage(jButtonSave);
        GuiLang.setLanguage(jLabel6);
        GuiLang.setLanguage(jLabel7);
        GuiLang.setLanguage(jLabel8);
    }
    
    public void setControl(HosControl hc,UpdateStatus us)
    {
        theUS = us;
        theSetupControl = hc.theSetupControl;
        theLookupControl = hc.theLookupControl;
        hc.theHS.theSetupSubject.addpanelrefrash(this);
        hc.theHS.theSetupSubject.addForLiftAttach(this);        
        setupLookup();
        setEnableAll();
    }
    /////////////////////Use this for decrease memory usage    

    public void setupLookup()
    {  
        ComboboxModel.initComboBox(jComboBoxGroup504,theLookupControl.listGroup504());
        ComboboxModel.initComboBox(jComboBoxGroup505,theLookupControl.listGroup505());
        ComboboxModel.initComboBox(jComboBoxGroup506,theLookupControl.listGroup506());
        ComboboxModel.initComboBox(jComboBoxGroupChronic,theLookupControl.listGroupChronic());
//        ComboboxModel.initComboBox(jComboBoxGroupChronic,theLookupControl.listGroupChronic2());
        ComboboxModel.initComboBox(jComboBoxGeneralDisease,theLookupControl.listDisease());
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
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabelICD9code = new javax.swing.JLabel();
        jTextFieldSCode = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new com.hosv3.gui.component.HJTableSort();
        jPanel5 = new javax.swing.JPanel();
        jButtonPrev = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldIcdCode = new javax.swing.JTextField();
        jTextFieldOther = new javax.swing.JTextField();
        jComboBoxGroup504 = new javax.swing.JComboBox();
        jComboBoxGroup505 = new javax.swing.JComboBox();
        jComboBoxGroup506 = new javax.swing.JComboBox();
        jComboBoxGroupChronic = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxGeneralDisease = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jButtonDel = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(fontFormatTitle1);
        jLabel3.setText("\u0e23\u0e32\u0e22\u0e01\u0e32\u0e23\u0e01\u0e25\u0e38\u0e48\u0e21 ICD 10");
        jPanel4.add(jLabel3, new java.awt.GridBagConstraints());

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
        jTable1.setFont(defaultFont1);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });

        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(jScrollPane1, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel5.add(jButtonPrev, gridBagConstraints);

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
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel5.add(jButtonNext, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel3.add(jPanel5, gridBagConstraints);

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
        jPanel2.setMinimumSize(new java.awt.Dimension(350, 320));
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 320));
        jLabel1.setFont(defaultFont1);
        jLabel1.setText("ICD-10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 2, 5);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(defaultFont1);
        jLabel2.setText("Group 504");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel4.setFont(defaultFont1);
        jLabel4.setText("Group 505");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel2.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(defaultFont1);
        jLabel5.setText("Group 506");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel2.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(defaultFont1);
        jLabel6.setText("Group chronic");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel2.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(defaultFont1);
        jLabel7.setText("Other");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel2.add(jLabel7, gridBagConstraints);

        jTextFieldIcdCode.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 2, 5);
        jPanel2.add(jTextFieldIcdCode, gridBagConstraints);

        jTextFieldOther.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel2.add(jTextFieldOther, gridBagConstraints);

        jComboBoxGroup504.setFont(defaultFont1);
        jComboBoxGroup504.setMinimumSize(new java.awt.Dimension(150, 24));
        jComboBoxGroup504.setPreferredSize(new java.awt.Dimension(150, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel2.add(jComboBoxGroup504, gridBagConstraints);

        jComboBoxGroup505.setFont(defaultFont1);
        jComboBoxGroup505.setMinimumSize(new java.awt.Dimension(150, 24));
        jComboBoxGroup505.setPreferredSize(new java.awt.Dimension(150, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel2.add(jComboBoxGroup505, gridBagConstraints);

        jComboBoxGroup506.setFont(defaultFont1);
        jComboBoxGroup506.setMinimumSize(new java.awt.Dimension(150, 24));
        jComboBoxGroup506.setPreferredSize(new java.awt.Dimension(150, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel2.add(jComboBoxGroup506, gridBagConstraints);

        jComboBoxGroupChronic.setFont(defaultFont1);
        jComboBoxGroupChronic.setMinimumSize(new java.awt.Dimension(150, 24));
        jComboBoxGroupChronic.setPreferredSize(new java.awt.Dimension(150, 24));
        jComboBoxGroupChronic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxGroupChronicActionPerformed(evt);
            }
        });
        jComboBoxGroupChronic.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBoxGroupChronicFocusGained(evt);
            }
        });
        jComboBoxGroupChronic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxGroupChronicMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jComboBoxGroupChronicMousePressed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel2.add(jComboBoxGroupChronic, gridBagConstraints);

        jLabel8.setFont(defaultFont1);
        jLabel8.setText("\u0e01\u0e25\u0e38\u0e48\u0e21\u0e42\u0e23\u0e04\u0e17\u0e31\u0e48\u0e27\u0e44\u0e1b");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel2.add(jLabel8, gridBagConstraints);

        jComboBoxGeneralDisease.setFont(defaultFont1);
        jComboBoxGeneralDisease.setMinimumSize(new java.awt.Dimension(26, 24));
        jComboBoxGeneralDisease.setPreferredSize(new java.awt.Dimension(26, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 5);
        jPanel2.add(jComboBoxGeneralDisease, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        add(jPanel2, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButtonSave.setFont(defaultFont1);
        jButtonSave.setText("\u0e1a\u0e31\u0e19\u0e17\u0e36\u0e01");
        jButtonSave.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSave.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonSave.setPreferredSize(new java.awt.Dimension(60, 24));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel1.add(jButtonSave, gridBagConstraints);

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
        jButtonAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButtonAddKeyReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jButtonAdd, gridBagConstraints);

        jButtonDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif")));
        jButtonDel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonDel.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDel.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDel.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelActionPerformed(evt);
            }
        });
        jButtonDel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButtonDelKeyReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButtonDel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        add(jPanel1, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxGroupChronicFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxGroupChronicFocusGained
// TODO add your handling code here:
//        setupLookup();
    }//GEN-LAST:event_jComboBoxGroupChronicFocusGained

    private void jComboBoxGroupChronicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxGroupChronicMouseClicked
// TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBoxGroupChronicMouseClicked

    private void jComboBoxGroupChronicMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxGroupChronicMousePressed
// TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxGroupChronicMousePressed

    private void jComboBoxGroupChronicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxGroupChronicActionPerformed
// TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBoxGroupChronicActionPerformed

    private void jButtonDelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonDelKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            jButtonSave.requestFocus();
        }
        if(evt.getKeyCode() == KeyEvent.VK_LEFT) {
            jButtonAdd.requestFocus();
        }
    }//GEN-LAST:event_jButtonDelKeyReleased

    private void jButtonDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelActionPerformed
        delete();
    }//GEN-LAST:event_jButtonDelActionPerformed

    private void jButtonAddKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonAddKeyReleased

    }//GEN-LAST:event_jButtonAddKeyReleased

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        setGroupIcd10(new GroupIcd10());
        this.setEnableAll();
    }//GEN-LAST:event_jButtonAddActionPerformed
    private void jTextFieldSCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSCodeActionPerformed
        searchGroupIcd10();    
    }//GEN-LAST:event_jTextFieldSCodeActionPerformed
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        saveGroupIcd10();
         searchGroupIcd10();
    }//GEN-LAST:event_jButtonSaveActionPerformed
    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        nextGroupIcd10();
    }//GEN-LAST:event_jButtonNextActionPerformed
    private void jButtonPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrevActionPerformed
        prevGroupIcd10();
    }//GEN-LAST:event_jButtonPrevActionPerformed
    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        searchGroupIcd10();
    }//GEN-LAST:event_jButtonSearchActionPerformed
    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        selectListGroupIcd10();
    }//GEN-LAST:event_jTable1MouseReleased
    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        selectListGroupIcd10();
	}//GEN-LAST:event_jTable1KeyReleased
    
    private void nextGroupIcd10()
    {   
        setGroupIcd10V(vGroupIcd10,1);
    }
    
    private void prevGroupIcd10()
    {   
        setGroupIcd10V(vGroupIcd10,0);
    }
  
    private GroupIcd10 getGroupIcd10()
    {
        Group504 g4 = theLookupControl.readGroup504ById(Gutil.getGuiData(this.jComboBoxGroup504));
        theGroupIcd10.group504= g4.group_504_id;
        theGroupIcd10.group_504_id = g4.getObjectId();
        Group505 g5 = theLookupControl.readGroup505ById(Gutil.getGuiData(this.jComboBoxGroup505));
        theGroupIcd10.group505 = g5.group_505_id;
        theGroupIcd10.group_505_id = g5.getObjectId();
        Group506 g6 = theLookupControl.readGroup506ById(Gutil.getGuiData(this.jComboBoxGroup506));
        theGroupIcd10.group506 = g6.group_506_id;
        theGroupIcd10.group_506_id = g6.getObjectId();
        
        theGroupIcd10.icdcode = jTextFieldIcdCode.getText();
        theGroupIcd10.other = jTextFieldOther.getText();
        GroupChronic gc = theSetupControl.listGroupChronicByPk(Gutil.getGuiData(jComboBoxGroupChronic));
        theGroupIcd10.group_chronic_id = Gutil.getGuiData(jComboBoxGroupChronic);
        theGroupIcd10.groupchronic = gc.group_chronic_id;
        theGroupIcd10.group_disease = Gutil.getGuiData(jComboBoxGeneralDisease);//amp:18/04/2549     
        return theGroupIcd10;
    }
    
   private void setGroupIcd10(GroupIcd10 gi)
   {
       theGroupIcd10 = gi;
       jTextFieldIcdCode.setEnabled(theGroupIcd10.icdcode.equals(""));
       jTextFieldIcdCode.setText(theGroupIcd10.icdcode);
       jTextFieldOther.setText(theGroupIcd10.other);
       ComboboxModel.setCodeComboBox(jComboBoxGroup504,theGroupIcd10.group_504_id);
       ComboboxModel.setCodeComboBox(jComboBoxGroup505,theGroupIcd10.group_505_id);
       ComboboxModel.setCodeComboBox(jComboBoxGroup506,theGroupIcd10.group_506_id);
       if(theGroupIcd10.group_chronic_id.equals(""))
       {
           ComboboxModel.setCodeComboBox(jComboBoxGroupChronic, theGroupIcd10.NA_CHRONIC);
       }
       else
       {
           ComboboxModel.setCodeComboBox(jComboBoxGroupChronic, theGroupIcd10.group_chronic_id);
       }
       ComboboxModel.setCodeComboBox(jComboBoxGeneralDisease,theGroupIcd10.group_disease);
   }
   
    private void setEnableAll()
    {   
    }
    
    private void selectListGroupIcd10()
    {  
        int row = jTable1.getSelectedRow();
        String of = (String)jTable1.getValueAt(row, 0);
        GroupIcd10 gicd10 = new GroupIcd10();
        for(int i =0 ; i< vGroupIcd10.size() ; i++)
        {           
             if(of == ((GroupIcd10)vGroupIcd10.get(i)).icdcode)
                gicd10 = (GroupIcd10)vGroupIcd10.get(i);
        }
        setGroupIcd10(gicd10);        
        jComboBoxGroupChronic.setEnabled(true);
        jTextFieldOther.setEnabled(true);
        jButtonSave.setEnabled(true);        
        jComboBoxGroup504.setEnabled(true);
        jComboBoxGroup505.setEnabled(true);
        jComboBoxGroup506.setEnabled(true);
    }
    
    private void searchGroupIcd10()
    {  
        next = 0;
        prev = 0;
        String search = jTextFieldSCode.getText();
        vGroupIcd10 = theSetupControl.listGroupIcd10All(search);
        setGroupIcd10V(vGroupIcd10,1);
    }
    
    private void saveGroupIcd10()
    {
        theSetupControl.saveGroupIcd10(getGroupIcd10());
        jComboBoxGroupChronic.setEnabled(false);
        jTextFieldOther.setEnabled(false);
        jButtonSave.setEnabled(false);
        
        jComboBoxGroup504.setEnabled(false);
        jComboBoxGroup505.setEnabled(false);
        jComboBoxGroup506.setEnabled(false);
    }
    
    private void setGroupIcd10V(Vector vGicd10,int off)
    {  
        this.vGroupIcd10 = vGicd10;
        int count = offset;
        int p =0;
        int n =0;
        int c =0;
        if(vGicd10 != null)
        {
            if(off == 1)
            {   p = prev;
                n = next;
                prev = next;
                next = next + offset;
                if(next >= vGicd10.size())
                {   next = vGicd10.size();
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
                if(next >= vGicd10.size())
                {   next= vGicd10.size();
                    count = next;
                }
            }
            TaBleModel tm = new TaBleModel(col,count);

            for(int i=0 ;i<count;i++)
            {

                GroupIcd10 of = (GroupIcd10)vGroupIcd10.get(i+prev);
                tm.setValueAt(of.icdcode,i,0);
                if(!of.group504.equals("") && !of.group504.equals(" "))
                {
                    Group504 group504 = (Group504) theLookupControl.readGroup504ById(of.group_504_id);
                    tm.setValueAt(group504.description_en,i,1);
                }
                else
                    tm.setValueAt("",i,1);
//                tm.setValueAt(of.group504,i,1);
                if(!of.group505.equals("") && !of.group505.equals(" "))
                {
                    Group505 group505 = (Group505) theLookupControl.readGroup505ById(of.group_505_id);
                    tm.setValueAt(group505.description_en,i,2);
                }
                else
                    tm.setValueAt("",i,2);
//                tm.setValueAt(of.group505,i, 2);
                if(!of.group506.equals("") && !of.group506.equals(" "))
                {
                    Group506 group506 = (Group506) theLookupControl.readGroup506ById(of.group_506_id);
                    tm.setValueAt(group506.description_th,i,3);
                }
                else
                    tm.setValueAt("",i,3);
//                tm.setValueAt(of.group506,i, 3);
                if(!of.groupchronic.equals("") && !of.groupchronic.equals(" "))
                {
                    GroupChronic groupchronic = (GroupChronic) theLookupControl.readGroupChronicByCode(of.group_chronic_id);
                    tm.setValueAt(groupchronic.description_th,i,4);
                }
                else
                    tm.setValueAt("",i,4);
//                tm.setValueAt(of.groupchronic,i,4);
                if(GroupIcd10.NA_Disease.equals(of.group_disease))
                {
                    tm.setValueAt("",i,5);
                }
                else
                {
                    com.pcu.object.Disease disease = theSetupControl.listDiseaseByPk(of.group_disease);
                    if(disease != null)
                    {
                        tm.setValueAt(disease.diseaseName,i,5);
                    }
                    else
                    {
                        tm.setValueAt("����բ�����",i,5);
                    }
                }
            }
            jTable1.setModel(tm);
            jTable1.getColumnModel().getColumn(1).setCellRenderer(theCellRendererToolTipText);
            jTable1.getColumnModel().getColumn(2).setCellRenderer(theCellRendererToolTipText);
            jTable1.getColumnModel().getColumn(3).setCellRenderer(theCellRendererToolTipText);
            jTable1.getColumnModel().getColumn(4).setCellRenderer(theCellRendererToolTipText);
            jTable1.getColumnModel().getColumn(5).setCellRenderer(theCellRendererToolTipText);
        }
        else
        {   TaBleModel tm = new TaBleModel(col,0);
            jTable1.setModel(tm);
        }
         setTableListDefault();
    } 
    
    private void setTableListDefault()
    {      
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(60); 
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(60); 
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(80); 
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(80); 
    }
    
    public static void main(String args[])
    {
        JFrame frm = new JFrame("Test");
        frm.getContentPane().setLayout(new BorderLayout());
        PanelSetupClinic pnl = new PanelSetupClinic();
        frm.getContentPane().add(pnl,  BorderLayout.CENTER);
        frm.addWindowListener(new WindowAdapter()
        {   public void windowClosing(WindowEvent e)
            {    System.exit(0);
            }
        });
        frm.pack();
        frm.setVisible(true);
        frm.setSize(450,400);
    }    
    
    public void notifyreFrashPanel()
    {
        setupLookup();
    }
    
    public void notifysetEnableForLift(boolean b) 
    {
    }
    
    public int editOption(Option option) 
    {
        Constant.println("PanelSetupGroupIcd10 function is not use.");
        return -1;
    }
    
    public Vector listOptionAll()
    {
        Constant.println("PanelSetupGroupIcd10 function is not use.");
        return null;
    }
    
    public void reFrashPanel() 
    {
    }
    
    public Option readOption() 
    {
        Constant.println("PanelSetupGroupIcd10 function is not use.");
        return null;
    }
    
    public int saveOption(Option option) 
    {
        Constant.println("PanelSetupGroupIcd10 function is not use.");
        return -1;    
    }
    
    public void setEnableForLift(boolean b)
    {
    }

    private void delete() {
        int record = theSetupControl.deleteGroupIcd10(theGroupIcd10);
        if(record>0)
            this.searchGroupIcd10();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.hospital_os.gui.font.FontFormatTitle fontFormatTitle1;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDel;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrev;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JComboBox jComboBoxGeneralDisease;
    private javax.swing.JComboBox jComboBoxGroup504;
    private javax.swing.JComboBox jComboBoxGroup505;
    private javax.swing.JComboBox jComboBoxGroup506;
    private javax.swing.JComboBox jComboBoxGroupChronic;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelICD9code;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private com.hosv3.gui.component.HJTableSort jTable1;
    private javax.swing.JTextField jTextFieldIcdCode;
    private javax.swing.JTextField jTextFieldOther;
    private javax.swing.JTextField jTextFieldSCode;
    // End of variables declaration//GEN-END:variables
}