/*
 * PanelSetupUom.java
 *
 * Created on 17 �ԧ�Ҥ� 2545, 22:56 �.
 */
package com.hosv3.gui.panel.setup;
import java.awt.*;
import java.util.Vector;
import java.awt.event.*;
import javax.swing.*;

import com.hosv3.usecase.setup.*;
import com.hosv3.control.*;
import com.hosv3.subject.*;
import com.hosv3.utility.connection.*;
import com.hosv3.gui.dialog.PanelSetupSearchSub;
import com.hosv3.utility.GuiLang;
import com.hosv3.utility.Constant;
import com.hospital_os.object.*;
import com.hospital_os.utility.TaBleModel;
/**
 *
 * @panel Author  ojika
 *@panal update: pee
 */
public class PanelSetupUom extends javax.swing.JPanel   implements 
ManageOptionReq
{
    UpdateStatus theUS;
    SetupControl theSetupControl;
    Uom theUom =new Uom();
    Vector billinggroup = new Vector();
    Vector uom = new Vector();
    int offset = 23;
    int next = 0;
    int prev = 0;
    int saved = 0; // 0 ��� �������ö insert�� 1 ��� insert ��
    String[] col = {"����","��������´"};
    
    //add code by noom 17/12/2548 ���������Ѻ��������ӹǹ�ҷ����㹡�þ���� ���١�Ѻ˹�����
    HosControl theHC;
    HosSubject theHS;
    PanelSetupSearchSub  thePanelSetupSearchSub;
    LookupControl theLookupControl;
    Vector theDoseMapUom;
    String[] col_AddDrugDose={"�ӹǹ����Ţ","��ͤ���"};
    
    public PanelSetupUom(HosControl hc,UpdateStatus us) {
        initComponents();
        jPanelDrugDose.setVisible(false);
        setLanguage();                 
        setControl(hc,us);
    }
    public PanelSetupUom() {
        initComponents();
        jPanelDrugDose.setVisible(false);
        setLanguage();
    }
      /**
     *@Author : amp
     *@date : 02/02/2549
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
        GuiLang.setLanguage(jCheckBoxS);
        GuiLang.setLanguage(jButtonSave);        
        GuiLang.setLanguage(jCheckBox1); 
        GuiLang.setLanguage(col);
    }
/////////////////////Use this for decrease memory usage
	
    public void setControl(HosControl hc,UpdateStatus us){        
        theUS = us;
        jTable1.setGuiMode(true);
        theSetupControl = hc.theSetupControl;
        hc.theHS.theSetupSubject.addpanelrefrash(this);
        hc.theHS.theSetupSubject.addForLiftAttach(this);
        setEnableAll(false);
        //setEnableSomeData();
        
        //add code by noom 17/12/2548 ��������� theHC ����Ѻ�� object ������� parameter �ͧ PanelSetupSearchSub        
        theHC = hc;
        theHS = hc.theHS;
        theLookupControl =  hc.theLookupControl;

    }
    /////////////////////Use this for decrease memory usage    
     private void setEnableSomeData()
     {
         jButtonAdd.setVisible(false);
         jButtonDel.setVisible(false);
         jTextFieldCode.setEnabled(false);
         jTextFieldName.setEnabled(false);
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
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabelICD9code = new javax.swing.JLabel();
        jTextFieldSCode = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jCheckBoxS = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new com.hosv3.gui.component.HJTableSort();
        jPanel5 = new javax.swing.JPanel();
        jButtonPrev = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanelDrugDose = new javax.swing.JPanel();
        jScrollPaneDrugDose = new javax.swing.JScrollPane();
        jTableDrugDose = new com.hosv3.gui.component.HJTableSort();
        jPanelControlDrugDose = new javax.swing.JPanel();
        jButtonAddDoseMap = new javax.swing.JButton();
        jButtonDelDoseMap = new javax.swing.JButton();
        jPanelTiltleDrugDose = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonDel = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(fontFormatTitle1);
        jLabel3.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("UOM"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jLabel3, gridBagConstraints);

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

        jCheckBoxS.setFont(defaultFont1);
        jCheckBoxS.setSelected(true);
        jCheckBoxS.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("ACTIVE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel3.add(jCheckBoxS, gridBagConstraints);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(100, 22));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 80));
        jTable1.setFont(defaultFont1);
        jTable1.setModel(tableResultsModel1);
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
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
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        add(jPanel3, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMinimumSize(new java.awt.Dimension(350, 90));
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 90));
        jLabel1.setFont(defaultFont1);
        jLabel1.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("CODE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(jLabel1, gridBagConstraints);

        jTextFieldCode.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 5);
        jPanel2.add(jTextFieldCode, gridBagConstraints);

        jLabel2.setFont(defaultFont1);
        jLabel2.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("DETAIL_COL"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        jTextFieldName.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 5);
        jPanel2.add(jTextFieldName, gridBagConstraints);

        jCheckBox1.setFont(defaultFont1);
        jCheckBox1.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("ACTIVE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel2.add(jCheckBox1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        add(jPanel2, gridBagConstraints);

        jPanelDrugDose.setLayout(new java.awt.GridBagLayout());

        jPanelDrugDose.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelDrugDose.setMaximumSize(new java.awt.Dimension(350, 2147483647));
        jPanelDrugDose.setMinimumSize(new java.awt.Dimension(350, 180));
        jPanelDrugDose.setPreferredSize(new java.awt.Dimension(350, 180));
        jScrollPaneDrugDose.setMaximumSize(new java.awt.Dimension(200, 230));
        jScrollPaneDrugDose.setMinimumSize(new java.awt.Dimension(200, 230));
        jScrollPaneDrugDose.setPreferredSize(new java.awt.Dimension(200, 230));
        jTableDrugDose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableDrugDoseMouseReleased(evt);
            }
        });

        jScrollPaneDrugDose.setViewportView(jTableDrugDose);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanelDrugDose.add(jScrollPaneDrugDose, gridBagConstraints);

        jPanelControlDrugDose.setLayout(new java.awt.GridBagLayout());

        jPanelControlDrugDose.setMinimumSize(new java.awt.Dimension(10, 30));
        jPanelControlDrugDose.setPreferredSize(new java.awt.Dimension(10, 30));
        jButtonAddDoseMap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif")));
        jButtonAddDoseMap.setEnabled(false);
        jButtonAddDoseMap.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonAddDoseMap.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonAddDoseMap.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonAddDoseMap.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonAddDoseMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddDoseMapActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        jPanelControlDrugDose.add(jButtonAddDoseMap, gridBagConstraints);

        jButtonDelDoseMap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif")));
        jButtonDelDoseMap.setEnabled(false);
        jButtonDelDoseMap.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonDelDoseMap.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDelDoseMap.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDelDoseMap.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonDelDoseMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelDoseMapActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanelControlDrugDose.add(jButtonDelDoseMap, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanelDrugDose.add(jPanelControlDrugDose, gridBagConstraints);

        jPanelTiltleDrugDose.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(defaultFont1);
        jLabel4.setText("\u0e08\u0e33\u0e19\u0e27\u0e19\u0e22\u0e32\u0e17\u0e35\u0e48\u0e43\u0e0a\u0e49 \u0e2a\u0e33\u0e2b\u0e23\u0e31\u0e1a\u0e01\u0e32\u0e23\u0e1e\u0e34\u0e21\u0e1e\u0e4c");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelTiltleDrugDose.add(jLabel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanelDrugDose.add(jPanelTiltleDrugDose, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        add(jPanelDrugDose, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 2);
        jPanel1.add(jButtonAdd, gridBagConstraints);

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
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel1.add(jButtonDel, gridBagConstraints);

        jButtonSave.setFont(defaultFont1);
        jButtonSave.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("SAVE"));
        jButtonSave.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSave.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonSave.setPreferredSize(new java.awt.Dimension(60, 24));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel1.add(jButtonSave, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        add(jPanel1, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents
    
    /*** add code by noom 18/12/2548 ����Ѻź�ӹǹ�ҷ��������Ѻ��þ���� ***/
    private void jButtonDelDoseMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelDoseMapActionPerformed
        int[] row = jTableDrugDose.getSelectedRows();
        if(theUom != null){
            String item_drug_dose_value = "";
            String b_item_drug_dose_id ="";
            String b_item_drug_uom_id = "";
            for(int i=0;i<row.length;i++){ 
            item_drug_dose_value = (String)jTableDrugDose.getValueAt(row[i], 0);
            Constant.println("Delete select value is ="+item_drug_dose_value);
            Vector vDrugDosePrint = theLookupControl.listDrugDosePrint(item_drug_dose_value, "1");
            if(vDrugDosePrint != null){
                b_item_drug_dose_id = ((DrugDosePrint)vDrugDosePrint.get(0)).getObjectId();
                b_item_drug_uom_id = theUom.getObjectId();
                Constant.println("Event delete b_item_drug_dose_id ="+b_item_drug_dose_id);
                Constant.println("Event delete b_item_drug_uom_id ="+b_item_drug_uom_id);
                DrugDoseMapUom theDrugDoseMapUom = theSetupControl.readDrugDoseMapUomFromDrugUomAndDoseId(b_item_drug_uom_id,b_item_drug_dose_id);
                if(theDrugDoseMapUom != null){
                  Constant.println("theDrugDoseMapUom id = "+theDrugDoseMapUom.getObjectId());
                  theSetupControl.deleteDrugDoseMapUom(theDrugDoseMapUom);
                }
            }
         }
         selectDrugDoseMapUom();
        }  
    }//GEN-LAST:event_jButtonDelDoseMapActionPerformed

    
    private void jTableDrugDoseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDrugDoseMouseReleased
      int row = jTableDrugDose.getSelectedRow();
      if(row != -1){
         jButtonDelDoseMap.setEnabled(true);
      }
    }//GEN-LAST:event_jTableDrugDoseMouseReleased

    private void jButtonAddDoseMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddDoseMapActionPerformed
      showDoseMapUom();
    }//GEN-LAST:event_jButtonAddDoseMapActionPerformed
    private void jButtonDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelActionPerformed
      deleteUom();     
    }//GEN-LAST:event_jButtonDelActionPerformed
    private void jTextFieldSCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSCodeActionPerformed
      searchUom();         
    }//GEN-LAST:event_jTextFieldSCodeActionPerformed
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
      saveUom();       
    }//GEN-LAST:event_jButtonSaveActionPerformed
    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
       insertUom();      
    }//GEN-LAST:event_jButtonAddActionPerformed
    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        nextUom();
    }//GEN-LAST:event_jButtonNextActionPerformed
    private void jButtonPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrevActionPerformed
       prevUom();
    }//GEN-LAST:event_jButtonPrevActionPerformed
    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
      searchUom();    
    }//GEN-LAST:event_jButtonSearchActionPerformed
    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
       selectUom();
       selectDrugDoseMapUom();
    }//GEN-LAST:event_jTable1MouseReleased
    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        selectUom();
        selectDrugDoseMapUom();
	}//GEN-LAST:event_jTable1KeyReleased
       
    /*** add code by noom 17/12/2548 ***/
    /*** method ����Ѻ�ʴ� Dialog 㹡�ä��Ҩӹǹ�ҷ���� ����Ѻ�����***/
    private void showDoseMapUom()
    {
        if(thePanelSetupSearchSub==null)
        {
            if(theUom != null && theUom.getObjectId() != null){
               thePanelSetupSearchSub = new PanelSetupSearchSub(theHC,theUS,15); 
               thePanelSetupSearchSub.setTitle(Constant.getTextBundle("�ʴ���¡�èӹǹ�ҷ���� ����Ѻ��þ����"));
               theDoseMapUom = theHC.theSetupControl.listDrugDoseMapUomByDrugUomId(theUom.getObjectId());
               if(theDoseMapUom != null){
                  theDoseMapUom = new Vector();
               }
               thePanelSetupSearchSub.showSearchDrugDoseMapUom(jTableDrugDose,theDoseMapUom,theUom);
             }else{
               theUS.setStatus("��س����͡˹�����",UpdateStatus.WARNING);
            }
       }
        thePanelSetupSearchSub = null;
    }
    
    /*** method ����Ѻ�Ӥ�Ңͧ�ӹǹ�ҷ���㹡�þ���� ���١�Ѻ˹������ʴ� ***/
    private void selectDrugDoseMapUom(){
      if(theUom != null && theUom.getObjectId() != null){
          Vector vDrugDoseMapUom = theSetupControl.listDrugDoseMapUomByDrugUomId(theUom.getObjectId());
          if(vDrugDoseMapUom!= null){
               setTableDrugDoseMapUom(vDrugDoseMapUom);
          }else{
               setTableDrugDoseMapUom(null);
          }
          jButtonAddDoseMap.setEnabled(true);
          jButtonDelDoseMap.setEnabled(false);
      }else{
          jButtonAddDoseMap.setEnabled(false);
          jButtonDelDoseMap.setEnabled(false);
          setTableDrugDoseMapUom(null);
      }
    }
    
    /*** method ����Ѻ�Ӥ�Ңͧ DrugDoseMapUom ���ʴ�㹵��ҧ ***/
    private void setTableDrugDoseMapUom(Vector vDrugDoseMapUom){  
        if(vDrugDoseMapUom != null){
            DrugDoseMapUom of = new DrugDoseMapUom();
            int count = vDrugDoseMapUom.size();
            TaBleModel tm = new TaBleModel(col_AddDrugDose,count);
            DrugDosePrint theDrugDosePrint = new DrugDosePrint();
            for(int i=0 ;i<count;i++){  
                of = (DrugDoseMapUom)vDrugDoseMapUom.get(i);
                theDrugDosePrint = theSetupControl.readDrugDosePrintByPk(of.b_item_drug_dose_id);
                if(theDrugDosePrint != null){
                  tm.setValueAt(theDrugDosePrint.item_drug_dose_value,i,0);
                  tm.setValueAt(theDrugDosePrint.item_drug_dose_description, i, 1);
                }
            }
            jTableDrugDose.setModel(tm);
        }
        else{
            TaBleModel tm = new TaBleModel(col_AddDrugDose,0);
            jTableDrugDose.setModel(tm);
        }
    } 
    /*** end add code by noom ***/
    
    private void nextUom()
    {   
        setTable(uom,1);
    }
    private void prevUom()
    {   setTable(uom,0);
    }
    private void insertUom()
    {   saved = 1;
        setEnableAll(true);
        //jTextFieldName.setEnabled(true);
        clearAll();
        
        //add code by noom 19/12/2548
        selectDrugDoseMapUom();
    }
    
    private void deleteUom()
    {   
        int ret = theSetupControl.deleteUOM(theUom);
        if(ret > 0)
        {
            //add code by noom 19/12/2548 ����ź������㹵��ҧ b_item_drug_dose_map_uom
            theSetupControl.deleteDrugDoseMapUomFromDrugUomId(theUom.getObjectId());
            selectDrugDoseMapUom();
            setEnableAll(false);
            clearAll();
            searchUom();
        }
    }
    private void clearAll()
    {   jTextFieldCode.setText("");
        jTextFieldName.setText("");
        jCheckBox1.setSelected(true);
        theUom = new Uom();
    }
    private void setEnableAll(boolean var)
    {   
        jTextFieldCode.setEnabled(var);
        jTextFieldName.setEnabled(var);
        jButtonSave.setEnabled(var);
        jCheckBox1.setEnabled(var);
        jButtonDel.setEnabled(var);
    }
    private void selectUom()
    {   
        setEnableAll(true);
        saved = 0;
        int row = jTable1.getSelectedRow();
        String of = (String)jTable1.getValueAt(row, 0);
        jTextFieldCode.setText(of);
        jTextFieldName.setText((String)jTable1.getValueAt(row, 1));
        Uom bgi = new Uom();
        for(int i =0 ; i< uom.size() ; i++)
        {           
             if(of == ((Uom)uom.get(i)).uom_id)
                bgi = (Uom)uom.get(i);
        }
        theUom = bgi;
        if(bgi.active.equals("1"))
            jCheckBox1.setSelected(true);
        else
            jCheckBox1.setSelected(false);
    }
  private void searchUom()
    {   next = 0;
        prev = 0;
        String search = jTextFieldSCode.getText();
        String active = "0";
        if(jCheckBoxS.isSelected())
            active = "1";
       uom =  theSetupControl.listUOM(search, active);
     //  if(uom != null)
        setTable(uom,1);
    }
    private void setTable(Vector voffice,int off)
    {   
        Uom of = new Uom();
        //tableoffice = voffice;
    //    jTable1.removeAll();
        int count = offset;
        int p =0;
        int n =0;
        int c =0;
        if(voffice != null)
        {
            if(off == 1)
            {   p = prev;
                n = next;
                prev = next;
                next = next + offset;
                if(next >= uom.size())
                {   next = uom.size();
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
                if(next >= uom.size())
                {   next= uom.size();
                    count = next;
                }
            }
            TaBleModel tm = new TaBleModel(col,count);
            for(int i=0 ;i<count;i++)
            {  
                of = (Uom)voffice.get(i+prev);
                 tm.setValueAt(of.uom_id,i,0);
                 tm.setValueAt(of.description,i,1);
            }
            jTable1.setModel(tm);
        }
        else
        {   TaBleModel tm = new TaBleModel(col,0);
            jTable1.setModel(tm);
        }
         setTableListDefault();
    }    
        private void setTableListDefault()
    {      
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(60); // �������ѭ����Ѻ þ.�����, ���͡�ä�� ����Ѻ�ٹ���ä��
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(300); // �ӹǹ
    }
    private void saveUom()
    {  
        theUom.uom_id = jTextFieldCode.getText();
        theUom.description = jTextFieldName.getText();
        if(jCheckBox1.isSelected())
                theUom.active = "1";
        else    theUom.active = "0"; 
        if(theUom.uom_id.equals("") || theUom.description.equals("")){
            theUS.setStatus("�������ö�ѹ�֡�������繤����ҧ��",UpdateStatus.WARNING);
            return;
        }
        Uom xr = theSetupControl.listUOMByCode(theUom.uom_id);
        if(xr!=null && theUom.getObjectId()==null) {
            theUS.setStatus("�������ö�ѹ�֡���ʫ����",UpdateStatus.WARNING);
            return;
        }
        if(xr!=null && theUom.getObjectId()==null
        && theUom.getObjectId().equals(xr.getObjectId())) {
            theUS.setStatus("�������ö�ѹ�֡���ʫ����",UpdateStatus.WARNING);
            return;
        }
        theSetupControl.saveUOM(theUom);
        setEnableAll(false);
        searchUom();
        
        //add code by noom 19/12/2548
        selectDrugDoseMapUom();
    }
    public static void main(String args[])
    {
        JFrame frm = new JFrame("Test");
        frm.getContentPane().setLayout(new BorderLayout());
        
        //add code by noom 18/12/2548 for test only this panel 
        /*String url = "jdbc:postgresql://localhost:5432/hosV3";
        String user = "postgres";
        String pass = "grespost";
        String dri = "org.postgresql.Driver";
        String type = "0";
        HosControl hc = new HosControl(url,user,pass,0,dri,type);
        UpdateStatus us = new PanelSetupUom();
        PanelSetupUom pnl = new PanelSetupUom(hc,us);
        */
        
         PanelSetupUom pnl = new PanelSetupUom();
    //    DBConnection db=new DBConnection();
     
        frm.getContentPane().add(pnl,  BorderLayout.CENTER);
        frm.addWindowListener(new WindowAdapter()
        {   public void windowClosing(WindowEvent e)
            {    System.exit(0);
            }
        });
        frm.pack();
        frm.setVisible(true);
    }    
    public void notifyreFrashPanel() {
     
    }
    public void notifysetEnableForLift(boolean b) {
        //jButtonDel.setVisible(b);
    }
    public int editOption(Option option) {
        Constant.println("PanelSetupUom function is not use.");
        return -1;
    }
    public Vector listOptionAll() {
        Constant.println("PanelSetupUom function is not use.");
        return null;
    }
    public void reFrashPanel() {
    }
    public Option readOption() {
        Constant.println("PanelSetupUom function is not use.");
        return null;
    }
    public int saveOption(Option option) {
        Constant.println("PanelSetupUom function is not use.");
        return -1;
    }
    public void setEnableForLift(boolean b) {
    }
    
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.hospital_os.gui.font.FontFormatTitle fontFormatTitle1;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAddDoseMap;
    private javax.swing.JButton jButtonDel;
    private javax.swing.JButton jButtonDelDoseMap;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrev;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBoxS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelICD9code;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelControlDrugDose;
    private javax.swing.JPanel jPanelDrugDose;
    private javax.swing.JPanel jPanelTiltleDrugDose;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneDrugDose;
    private com.hosv3.gui.component.HJTableSort jTable1;
    private com.hosv3.gui.component.HJTableSort jTableDrugDose;
    private javax.swing.JTextField jTextFieldCode;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldSCode;
    private com.hospital_os.utility.TableResultsModel tableResultsModel1;
    // End of variables declaration//GEN-END:variables
}
