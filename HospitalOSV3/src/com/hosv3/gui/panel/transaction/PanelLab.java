/*
 * PanelFpWoman.java
 *
 * Created on 19 ตุลาคม 2546, 17:07 น.
 */
package com.hosv3.gui.panel.transaction;


import com.hosv3.control.lookup.EmployeeLookup;
import java.awt.event.*;
import java.util.*;

import com.hosv3.control.*;
import com.hosv3.utility.connection.*;
import com.hosv3.subject.*;
import com.hosv3.utility.*;
import com.hosv3.object.*;
import com.hosv3.usecase.transaction.*;
import com.hosv3.gui.dialog.*;
import com.hosv3.gui.component.*;

import com.hospital_os.object.*;
import com.hospital_os.utility.CelRendererLabReferOut;
import com.hosv3.utility.Constant;
import com.hospital_os.utility.TaBleModel;
import com.hospital_os.utility.CellRendererHos;
import com.hospital_os.utility.ComboboxModel;
/**
 *
 * @author  Administrator   
 */ 
public class PanelLab extends javax.swing.JPanel
implements ManageVisitResp,ManageOrderResp,ManageLabXrayResp,ManagePatientResp {
	static final long serialVersionUID = 0;
	HosControl theHC;
	HosObject theHO;
        UpdateStatus theUS;
        HosSubject theHS;
        HosDialog theHD;

        public static int ROW_HIGH = 22;

    private Patient thePatient;
    private Visit theVisit; 
    
    private Vector vOrderItemLab = new Vector();
    private Vector vResultLab;
    
    private Vector visitHistoryV = new Vector();
    private Vector vResultLabHistory = new Vector();
    private Vector vLabResultItem = new Vector();
    
    private int selectdetail = -1;
    private boolean isSaveRePort;
    
    /**แสดงว่า refer out หรือเปล่า*/
    CelRendererLabReferOut celRendererLabReferOut = new CelRendererLabReferOut();
    CelRendererLabReport celRendererLabReport = new CelRendererLabReport();
    CellRendererDayOrder cellRendererDayOrder ;
    CellRendererHos dateRender = new CellRendererHos(CellRendererHos.DATE_TIME);
    String[] col_jTableListOrder = {"ชื่อ","เวลาที่สั่ง","OUT","สถานะ"};
    String[] col_jTableListOrderReport = {"ชื่อ","ส่งตรวจแลป","ส่งผลแลป"};
    //String[] col_jTableDetailOrderLab = {"รายการ","วันที่สั่ง","ชื่อ","ผล","หน่วย","ค่าปกติ"};
    String[] col_jTableDetailOrderLab = {"รายการ","ชื่อ","ผล","หน่วย","ค่าปกติ"};
    String[] jTableXrayHistory_col = {"Vn","วันที่"};
    private static final int FIELD_RESULT_INDEX = 2;
    CellRendererHos vnRender = new CellRendererHos(CellRendererHos.VN);

    /** Creates new form PanelBilling */
    public PanelLab(){
        initComponents();
        jTableListOrderLab.requestFocus();
        setEnabled(false);
        //this.jButtonExecute1.setVisible(false);
    }
    public void setWrite(boolean b)
    {
        panelResultLabCur.setWrite(b);
    }
    public void setControl(HosControl hc, UpdateStatus us){   
        theUS = us;
        theHC = hc;
        theHO = hc.theHO;
        theHS = hc.theHS;
        theVisit = theHO.theVisit; 
        thePatient = theHO.thePatient;
    vnRender = new CellRendererHos(CellRendererHos.VN,theHC.theLookupControl.getSequenceDataVN().pattern);
        theHS.theOrderSubject.attachManageOrder(this);
        theHS.theVisitSubject.attachManageVisit(this);
        theHS.theResultSubject.attachManageLab(this);
        theHS.thePatientSubject.attachManagePatient(this);
        cellRendererDayOrder = new CellRendererDayOrder(true);
//        jComboBoxEmployee.setControl(new EmployeeLookup(theHC.theSetupControl,theHC.theLookupControl),false);
        ComboboxModel.initComboBox(jComboBoxEmployee,theHC.theLookupControl.listLab());
        this.panelResultLabCur.setControl(theHC);
        this.panelResultLabHis.setControl(theHC);
        this.panelResultLabCur.setDateVisible(false);
        this.panelResultLabHis.setDateVisible(false);
        this.panelResultLabCur.setTableOrder(this.jTableListOrderLab);
        setLanguage(null); 
    }   
    public void setDialog(HosDialog hd)
    {
        theHD = hd;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonAddOrderItemLab = new javax.swing.JButton();
        jButtonDelOrderItemLab = new javax.swing.JButton();
        jButtonReferOut = new javax.swing.JButton();
        jButtonCancelReferOut = new javax.swing.JButton();
        jComboBoxEmployee = new com.hosv3.gui.component.HosComboBox();
        jButtonExecute = new javax.swing.JButton();
        jButtonSortResult = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableListOrderLab = new com.hosv3.gui.component.HJTableSort();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaNote = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jButtonSendResult = new javax.swing.JButton();
        jButtonSaveLab = new javax.swing.JButton();
        jButtonPrintResultLab = new javax.swing.JButton();
        jButtonDelectQueueLab = new javax.swing.JButton();
        jButtonRemainLab = new javax.swing.JButton();
        jButtonSelectAll = new javax.swing.JButton();
        panelResultLabCur = new com.hosv3.gui.component.PanelResultLab();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jRadioButtonSelectAll = new javax.swing.JCheckBox();
        jButtonSearchHistory = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jLabelTo1 = new javax.swing.JLabel();
        dateComboBoxHistoryStart = new com.hospital_os.utility.DateComboBox();
        dateComboBoxHistoryEnd = new com.hospital_os.utility.DateComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHistory = new com.hosv3.gui.component.HJTableSort();
        panelResultLabHis = new com.hosv3.gui.component.PanelResultLab();

        setLayout(new java.awt.GridBagLayout());

        jTabbedPane1.setDoubleBuffered(true);
        jTabbedPane1.setFont(defaultFont1);
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(480, 428));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(480, 496));
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jButtonAddOrderItemLab.setFont(defaultFont1);
        jButtonAddOrderItemLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAddOrderItemLab.setToolTipText("เพิ่มรายการแลป");
        jButtonAddOrderItemLab.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonAddOrderItemLab.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonAddOrderItemLab.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonAddOrderItemLab.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonAddOrderItemLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddOrderItemLabActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jButtonAddOrderItemLab, gridBagConstraints);

        jButtonDelOrderItemLab.setFont(defaultFont1);
        jButtonDelOrderItemLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelOrderItemLab.setToolTipText("ลบรายการแลป");
        jButtonDelOrderItemLab.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonDelOrderItemLab.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonDelOrderItemLab.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonDelOrderItemLab.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonDelOrderItemLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelOrderItemLabActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButtonDelOrderItemLab, gridBagConstraints);

        jButtonReferOut.setFont(defaultFont1);
        jButtonReferOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/refer_out.gif"))); // NOI18N
        jButtonReferOut.setToolTipText("ส่งตรวจแลป");
        jButtonReferOut.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonReferOut.setMaximumSize(new java.awt.Dimension(63, 26));
        jButtonReferOut.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonReferOut.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonReferOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReferOutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButtonReferOut, gridBagConstraints);

        jButtonCancelReferOut.setFont(defaultFont1);
        jButtonCancelReferOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/no_refer_out.gif"))); // NOI18N
        jButtonCancelReferOut.setToolTipText("ยกเลิกการส่งตรวจแลป");
        jButtonCancelReferOut.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonCancelReferOut.setMaximumSize(new java.awt.Dimension(63, 26));
        jButtonCancelReferOut.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonCancelReferOut.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonCancelReferOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelReferOutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel2.add(jButtonCancelReferOut, gridBagConstraints);

        jComboBoxEmployee.setDoubleBuffered(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        jPanel2.add(jComboBoxEmployee, gridBagConstraints);

        jButtonExecute.setFont(defaultFont1);
        jButtonExecute.setText("ดำเนินการ");
        jButtonExecute.setMargin(new java.awt.Insets(2, 7, 2, 7));
        jButtonExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExecuteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        jPanel2.add(jButtonExecute, gridBagConstraints);

        jButtonSortResult.setFont(defaultFont1);
        jButtonSortResult.setText("จัดผลแลป");
        jButtonSortResult.setMargin(new java.awt.Insets(2, 7, 2, 7));
        jButtonSortResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSortResultActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        jPanel2.add(jButtonSortResult, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel9.add(jPanel2, gridBagConstraints);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(224, 404));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(224, 404));

        jTableListOrderLab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableListOrderLab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableListOrderLabMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTableListOrderLab);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(jScrollPane2, gridBagConstraints);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setMinimumSize(new java.awt.Dimension(100, 61));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(100, 61));

        jTextAreaNote.setLineWrap(true);
        jTextAreaNote.setWrapStyleWord(true);
        jTextAreaNote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextAreaNoteKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTextAreaNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 1, 0, 1);
        jPanel9.add(jScrollPane3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 0, 0);
        jPanel5.add(jPanel9, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButtonSendResult.setFont(defaultFont1);
        jButtonSendResult.setText("ส่งผล");
        jButtonSendResult.setToolTipText("ส่งผลแลป");
        jButtonSendResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendResultActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(jButtonSendResult, gridBagConstraints);

        jButtonSaveLab.setFont(defaultFont1);
        jButtonSaveLab.setText("บันทึก");
        jButtonSaveLab.setToolTipText("บันทึกผลแลป");
        jButtonSaveLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveLabActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(jButtonSaveLab, gridBagConstraints);

        jButtonPrintResultLab.setFont(defaultFont1);
        jButtonPrintResultLab.setText("พิมพ์ผลแลบ");
        jButtonPrintResultLab.setToolTipText("พิมพ์ผลแลบ");
        jButtonPrintResultLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintResultLabActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jButtonPrintResultLab, gridBagConstraints);

        jButtonDelectQueueLab.setFont(defaultFont1);
        jButtonDelectQueueLab.setText("ลบคิว");
        jButtonDelectQueueLab.setToolTipText("ลบคิวแลป");
        jButtonDelectQueueLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelectQueueLabActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jButtonDelectQueueLab, gridBagConstraints);

        jButtonRemainLab.setFont(defaultFont1);
        jButtonRemainLab.setText("ค้างผล");
        jButtonRemainLab.setToolTipText("ค้างผลแลป");
        jButtonRemainLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemainLabActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(jButtonRemainLab, gridBagConstraints);

        jButtonSelectAll.setFont(defaultFont1);
        jButtonSelectAll.setText("ทั้งหมด");
        jButtonSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelectAllActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButtonSelectAll, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 11, 11);
        jPanel5.add(jPanel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 5, 0, 11);
        jPanel5.add(panelResultLabCur, gridBagConstraints);

        jTabbedPane1.addTab("รายการ", jPanel5);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jPanel7.setMinimumSize(new java.awt.Dimension(220, 438));
        jPanel7.setPreferredSize(new java.awt.Dimension(220, 438));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jRadioButtonSelectAll.setFont(defaultFont1);
        jRadioButtonSelectAll.setSelected(true);
        jRadioButtonSelectAll.setText("ทั้งหมด");
        jRadioButtonSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSelectAllActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel7.add(jRadioButtonSelectAll, gridBagConstraints);

        jButtonSearchHistory.setFont(defaultFont1);
        jButtonSearchHistory.setText("ค้นหา");
        jButtonSearchHistory.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSearchHistory.setMaximumSize(new java.awt.Dimension(50, 24));
        jButtonSearchHistory.setMinimumSize(new java.awt.Dimension(50, 24));
        jButtonSearchHistory.setPreferredSize(new java.awt.Dimension(50, 24));
        jButtonSearchHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchHistoryActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 0);
        jPanel7.add(jButtonSearchHistory, gridBagConstraints);

        jPanel20.setLayout(new java.awt.GridBagLayout());

        jLabelTo1.setFont(defaultFont1);
        jLabelTo1.setText("to");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 5);
        jPanel20.add(jLabelTo1, gridBagConstraints);

        dateComboBoxHistoryStart.setEnabled(false);
        dateComboBoxHistoryStart.setFont(defaultFont1);
        dateComboBoxHistoryStart.setMinimumSize(new java.awt.Dimension(94, 24));
        dateComboBoxHistoryStart.setPreferredSize(new java.awt.Dimension(94, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 5);
        jPanel20.add(dateComboBoxHistoryStart, gridBagConstraints);

        dateComboBoxHistoryEnd.setEnabled(false);
        dateComboBoxHistoryEnd.setFont(defaultFont1);
        dateComboBoxHistoryEnd.setMinimumSize(new java.awt.Dimension(94, 24));
        dateComboBoxHistoryEnd.setPreferredSize(new java.awt.Dimension(94, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 3);
        jPanel20.add(dateComboBoxHistoryEnd, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel7.add(jPanel20, gridBagConstraints);

        jTableHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableHistoryMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableHistory);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel7.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 11, 0);
        jPanel6.add(jPanel7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 11, 11);
        jPanel6.add(panelResultLabHis, gridBagConstraints);

        jTabbedPane1.addTab("ประวัติ", jPanel6);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jTabbedPane1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSortResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSortResultActionPerformed
        int[] a = jTableListOrderLab.getSelectedRows();
        theHC.theOrderControl.generateResultLab(vOrderItemLab,a);
        panelResultLabCur.setResultLabV(theHO.theVisit,vOrderItemLab);
    }//GEN-LAST:event_jButtonSortResultActionPerformed

    private void jTextAreaNoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaNoteKeyReleased

        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            theHC.theVitalControl.saveDxNote(jTextAreaNote.getText());
    }//GEN-LAST:event_jTextAreaNoteKeyReleased

    private void jButtonSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelectAllActionPerformed
        jTableListOrderLab.selectAll();
        panelResultLabCur.setOrderSelectedAll();
    }//GEN-LAST:event_jButtonSelectAllActionPerformed

    private void jTableListOrderLabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListOrderLabMouseReleased
        if(jTableListOrderLab.getSelectedRows().length==0){
            panelResultLabCur.setOrderSelected(null);
            return;
        }
        if(jTableListOrderLab.getSelectedRows().length
                ==jTableListOrderLab.getRowCount()){
            panelResultLabCur.setOrderSelectedAll();
            return;
        }
        int row = jTableListOrderLab.getSelectedRow();
        OrderItem oi = (OrderItem)vOrderItemLab.get(row);
        this.panelResultLabCur.setOrderSelected(oi);
        
    }//GEN-LAST:event_jTableListOrderLabMouseReleased

    private void jButtonSendResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendResultActionPerformed
        //jTableListOrderLab.selectAll();
        int[] a = jTableListOrderLab.getSelectedRows();
        panelResultLabCur.setResultLabV(theHO.theVisit,vOrderItemLab);
        theHC.theResultControl.sendDataResultLab(vOrderItemLab,this.panelResultLabCur.getResultLabV(),a);
    }//GEN-LAST:event_jButtonSendResultActionPerformed

    private void jButtonExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExecuteActionPerformed
        int[] a = jTableListOrderLab.getSelectedRows();
        String eid = this.jComboBoxEmployee.getText();
        theHC.theOrderControl.executeOrderItem(vOrderItemLab,a,eid);
    }//GEN-LAST:event_jButtonExecuteActionPerformed

    private void jButtonDelectQueueLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelectQueueLabActionPerformed
        if(theHO.theVisit == null || theHO.theVisit.getObjectId() == null)
        {
            theUS.setStatus("ยังไม่เลือกผู้ป่วย",UpdateStatus.WARNING);
            return;
        }
        if(!theUS.confirmBox("คุณต้องการที่จะลบคิวใช่หรือไม่ ?",UpdateStatus.WARNING))
            return;
        {
            theHC.theResultControl.deleteQueueLabByVisitID(theHO.theVisit.getObjectId());
        }
    }//GEN-LAST:event_jButtonDelectQueueLabActionPerformed

    private void jTableHistoryMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHistoryMouseReleased
        int select = jTableHistory.getSelectedRow();
        Visit visit = (Visit) visitHistoryV.get(select);
        setHisResultLabV(visit);
    }//GEN-LAST:event_jTableHistoryMouseReleased

    private void jRadioButtonSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSelectAllActionPerformed
         if(jRadioButtonSelectAll.isSelected()){
            dateComboBoxHistoryStart.setEnabled(false);
            dateComboBoxHistoryEnd.setEnabled(false);            
        }
        else{
            dateComboBoxHistoryStart.setEnabled(true);
            dateComboBoxHistoryEnd.setEnabled(true);            
        }
    }//GEN-LAST:event_jRadioButtonSelectAllActionPerformed

    private void jButtonSearchHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchHistoryActionPerformed
         if(theHO.thePatient == null)
         {
            theUS.setStatus("กรุณาเลือกผู้ป่วยก่อนค้นหาประวัติ", UpdateStatus.WARNING);
            return;
        }
        String from = dateComboBoxHistoryStart.getText();
        String to = dateComboBoxHistoryEnd.getText();
        boolean all = jRadioButtonSelectAll.isSelected();
        visitHistoryV = theHC.theVisitControl.listVisitLabByDatePid(all,from,to,theHO.thePatient.getObjectId());
        setVisitHistoryV(visitHistoryV);
    }//GEN-LAST:event_jButtonSearchHistoryActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged

    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jButtonRemainLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemainLabActionPerformed
        int[] select = jTableListOrderLab.getSelectedRows();
        theHC.theResultControl.saveRemainResultLab(vOrderItemLab, select);
    }//GEN-LAST:event_jButtonRemainLabActionPerformed

    private void jButtonSaveLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveLabActionPerformed
        theHC.theResultControl.saveResultLab(vOrderItemLab,panelResultLabCur.getResultLabV(),theUS,jTableListOrderLab.getSelectedRows());
    }//GEN-LAST:event_jButtonSaveLabActionPerformed

    private void jButtonPrintResultLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintResultLabActionPerformed
         // เรียก Dialog ของการเลือกรายการ Lab ที่จะพิมพ์ 
        if(theHO.theVisit == null || theHO.theVisit.getObjectId()== null){      
            theUS.setStatus("ยังไม่เลือกผู้ป่วย",UpdateStatus.WARNING);
            return;
        }
        theHD.showDialogSelectLabPrint(vOrderItemLab,theVisit,thePatient,panelResultLabCur.getResultLabV());   
    }//GEN-LAST:event_jButtonPrintResultLabActionPerformed

    private void jButtonCancelReferOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelReferOutActionPerformed
        int[] a = jTableListOrderLab.getSelectedRows();
        if(a.length ==0){   
            theUS.setStatus("ยังไม่ได้เลือกรายการ Order",UpdateStatus.WARNING);
            return ;
        }        
        theHC.theOrderControl.referOutLab(vOrderItemLab,a, false); 
    }//GEN-LAST:event_jButtonCancelReferOutActionPerformed

    private void jButtonReferOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReferOutActionPerformed
        if(jTableListOrderLab.getSelectedRow()==-1){   
            theUS.setStatus("ยังไม่ได้เลือกรายการ Order",UpdateStatus.WARNING);
            return ;
        }
        if(jTableListOrderLab.getSelectedRows().length ==0){
            return ;
        }
        int[] a = jTableListOrderLab.getSelectedRows();
        for(int i=0; i<a.length; i++){   
            if(((OrderItem)vOrderItemLab.get(a[i])).status.equals(OrderStatus.REPORT)){
                theUS.setStatus("รายการแลปได้รายงานผลแล้วไม่สามารถส่งตรวจได้", UpdateStatus.WARNING);
                return;
            } 
        }
        theHC.theOrderControl.referOutLab(vOrderItemLab, a, true); 
        DialogLabReferOut.showDialog(theHC,theUS,vOrderItemLab, a);
    }//GEN-LAST:event_jButtonReferOutActionPerformed

    private void jButtonDelOrderItemLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelOrderItemLabActionPerformed
        int[] row = jTableListOrderLab.getSelectedRows();
        if(row.length ==0){   
            theUS.setStatus("ยังไม่ได้เลือกรายการ Order",UpdateStatus.WARNING);
            return ;
        }
        for(int i=0; i<row.length; i++){   
            OrderItem oi = (OrderItem) vOrderItemLab.get(row[i]);
            if(!oi.vertifier.equals("") && !theHO.theEmployee.getObjectId().equals(oi.vertifier)){
                theUS.setStatus("ไม่สามารถยกเลิกรายการ Order ที่สั่งมาจากจุดบริการอื่นได้",UpdateStatus.WARNING);               
                return;
            }
        }
        theHC.theOrderControl.cancelOrderItem(vOrderItemLab,row);
    }//GEN-LAST:event_jButtonDelOrderItemLabActionPerformed

    private void jButtonAddOrderItemLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddOrderItemLabActionPerformed
        if(theHO.thePatient == null){
            theUS.setStatus("ยังไม่เลือกผู้ป่วย",UpdateStatus.WARNING);     
            return;
        }
        if(theHO.theVisit == null || theHO.theVisit.getObjectId()==null){      
            theUS.setStatus("ยังไม่เลือกผู้ป่วย",UpdateStatus.WARNING);     
            return;
        }        
        if(theVisit.is_discharge_money.equals("1")){
            theUS.setStatus("ผู้ป่วยจำหน่ายทางการเงินแล้ว ไม่สามารถเพิ่มรายการแลปได้",UpdateStatus.WARNING);
            return;
        }
        if(theHO.isLockingByOther()){
            theUS.setStatus("ผู้ป่วยถูกเจ้าหน้าคนอื่นให้บริการอยู่ ไม่สามารถเพิ่มรายการแลปได้",UpdateStatus.WARNING);
            return;
        } 
         if(!"".equals(this.theHO.orderSecret)){
            theUS.setStatus("ผู้ป่วยเคสแลปปกปิด ไม่สามารถเพิ่มรายการแลปได้",UpdateStatus.WARNING);
            return;
         }
        theHD.showDialogOrderItemLabByLab(vOrderItemLab,theVisit); 
    }//GEN-LAST:event_jButtonAddOrderItemLabActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.utility.DateComboBox dateComboBoxHistoryEnd;
    private com.hospital_os.utility.DateComboBox dateComboBoxHistoryStart;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private javax.swing.JButton jButtonAddOrderItemLab;
    private javax.swing.JButton jButtonCancelReferOut;
    private javax.swing.JButton jButtonDelOrderItemLab;
    private javax.swing.JButton jButtonDelectQueueLab;
    private javax.swing.JButton jButtonExecute;
    private javax.swing.JButton jButtonPrintResultLab;
    private javax.swing.JButton jButtonReferOut;
    private javax.swing.JButton jButtonRemainLab;
    private javax.swing.JButton jButtonSaveLab;
    private javax.swing.JButton jButtonSearchHistory;
    private javax.swing.JButton jButtonSelectAll;
    private javax.swing.JButton jButtonSendResult;
    private javax.swing.JButton jButtonSortResult;
    private com.hosv3.gui.component.HosComboBox jComboBoxEmployee;
    private javax.swing.JLabel jLabelTo1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JCheckBox jRadioButtonSelectAll;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.hosv3.gui.component.HJTableSort jTableHistory;
    private com.hosv3.gui.component.HJTableSort jTableListOrderLab;
    private javax.swing.JTextArea jTextAreaNote;
    private com.hosv3.gui.component.PanelResultLab panelResultLabCur;
    private com.hosv3.gui.component.PanelResultLab panelResultLabHis;
    // End of variables declaration//GEN-END:variables

    
    private void setButton(Vector vOrderItemLab){   
//        if(vOrderItemLab != null && vOrderItemLab.size() > 0){
//            jButtonSaveLab.setEnabled(true);
//            jButtonReferOut.setEnabled(true);
//            jButtonExecute.setEnabled(true);
//            jButtonCancelReferOut.setEnabled(true);
//            jButtonAddOrderItemLab.setEnabled(true);
//            jButtonDelOrderItemLab.setEnabled(true); 
//            jButtonRemainLab.setEnabled(true);
//            jButtonDelectQueueLab.setEnabled(true);
//        }
//        else{
//            jButtonExecute.setEnabled(false);
//            jButtonSaveLab.setEnabled(false);
//            jButtonReferOut.setEnabled(false);
//            jButtonCancelReferOut.setEnabled(false);
//            jButtonRemainLab.setEnabled(false);
//            jButtonDelectQueueLab.setEnabled(false);
//        }
    }
    ////////////////////////////////////////////////////////////////////////////
    /**
     *เซตปุ่มต่างๆ
     */
    public void  setEnabled(boolean b){   
        jButtonRemainLab.setEnabled(b);
        this.jButtonSendResult.setEnabled(b);
        jButtonDelectQueueLab.setEnabled(b);
        jButtonSaveLab.setEnabled(b);
        jButtonReferOut.setEnabled(b);
        jButtonDelOrderItemLab.setEnabled(b);
        jButtonCancelReferOut.setEnabled(b);   
        jButtonPrintResultLab.setEnabled(b);
        jButtonAddOrderItemLab.setEnabled(b);
        jButtonExecute.setEnabled(b);
    }
    
    /**
     *อ่านค่ารายการประวัติของแลป
     */
     private void setOrderLabHistoryV(Visit v){ 
         Vector vc = theHC.theOrderControl.listOrderLabByVN(v.getObjectId());
         if(vc==null)
         {  
            vResultLabHistory = null;
            return;
         }
         vResultLabHistory = new Vector();
         int vc_size = 0;
         if(vc != null)
             vc_size = vc.size();
         for(int i=0 ;i<vc_size; i++)
         {
            OrderItem orderitem = (OrderItem)vc.get(i);
            if(orderitem != null )
            {
                ////////////////////////////////////////////////////////////////
                //นำ ข้อมูลที่อยู่ใน Vector มาใช้หารายการ Lab                
                // true = ไม่มีรายการไหนแล้วที่ต้องรายงาน       
                //boolean resultlab = true;  
                // หาผลLab จาก Item Id
                Vector vlri = theHC.theOrderControl.readLabResultItem(orderitem.item_code);
                int size = vlri.size();
                for(int j=0; j<size; j++)
                {   
                    LabResultItem theLabResultItem = (LabResultItem)vlri.get(j);
                    vResultLabHistory.add(theLabResultItem);
                    ResultLab rl = new ResultLab();
                    rl.name = theLabResultItem.name;
                    rl.result = "";
                    rl.unit = theLabResultItem.unit;
                    rl.order_item_id =orderitem.getObjectId();
                    rl.item_id = theLabResultItem.item_id;
                    ResultLab rl1 =  theHC.theResultControl.readResultLabByOrderID(rl);
                    if(rl1 != null)
                    {  
                        rl = rl1;
                    }
                     //ใช้ในการตรวจสอบlabgroupเพื่อไม่ไห้ใส่item ที่ซ้ำกันลงไป
                  //  boolean isItemRepeat = false;
                    int vOrderItemLab_size = 0;
                    if(vOrderItemLab != null)
                        vOrderItemLab_size = vOrderItemLab.size();
                    for(int k=0; k<vOrderItemLab_size; k++)
                    {
                        OrderItem orderitem2 = (OrderItem)vOrderItemLab.get(k);
                        if(orderitem2.getObjectId().equalsIgnoreCase(orderitem.getObjectId())){
               //            isItemRepeat = true;
                           break;
                        }
                    }
                    //resultlab = false;
                    vResultLabHistory.add(rl);
                }
            }
        }
     }
     //ใช้ในการ refresh รายการ order ที่มีการเปลี่ยนแปลง
     //จะมีผลแค่ตาราง order ในหน้าจอ lab เท่านั้น
     private void setOrderItemLabV(Visit visit)
     {
         if(visit==null){
             setOrderItemLabV(new Vector());
             return;
         }
         Vector vc = theHC.theOrderControl.listOrderLabByVNAndSecret(visit.getObjectId());
        //ให้แสดงในส่วนของ detail ด้วยมั้ย แสดงแค่สถานะของ Order ก็พอ
        setOrderItemLabV(vc);
     }
     
     private void setHisResultLabV(Visit visit)
     {
         if(visit==null){
             this.panelResultLabHis.setResultLabV(new Vector());
             return;
         }
        Vector vc = theHC.theOrderControl.listOrderLabByVNAndSecret(visit.getObjectId());
        this.panelResultLabHis.setResultLabV(visit,vc);
     }
     private void setResultLabV(Vector order_v)
     {   
        //henbe:18/03/2549
         this.panelResultLabCur.setResultLabV(theHO.theVisit,order_v);
     }
     
    ////////////////////////////////////////////////////////////////////////////
    /**
     *เซตVisit ในตาราง History
     */
    private void setVisitHistoryV(Vector v){ 
        this.visitHistoryV = v;
        TaBleModel tm;
        if(v == null || v.size()==0){
            tm = new TaBleModel(jTableXrayHistory_col,0);
        }
        else{
            tm = new TaBleModel(jTableXrayHistory_col,v.size());
            int size = v.size();
            for(int i=0; i<size; i++){
                Visit vi = (Visit) v.get(i);
                tm.setValueAt(vi.vn, i, 0);
                tm.setValueAt(DateUtil.getDateFromText(vi.begin_visit_time),i,1);
            }
        }
         jTableHistory.setModel(tm);
         jTableHistory.getColumnModel().getColumn(0).setPreferredWidth(15);
         jTableHistory.getColumnModel().getColumn(0).setCellRenderer(TableRenderer.getRendererCenter());
         jTableHistory.getColumnModel().getColumn(0).setCellRenderer(vnRender);
         jTableHistory.getColumnModel().getColumn(1).setCellRenderer(dateRender);
    }
    ////////////////////////////////////////////////////////////////////////////
    /**
     *  สร้างตารางในการแสดง LabOrderItem ที่ยังไม่มีการรายงานผล
     */
    private void setOrderItemLabV(Vector order_v)
    {   
        vOrderItemLab = order_v;
        TaBleModel tm ;
        if(vOrderItemLab == null || vOrderItemLab.size()==0){
            tm= new TaBleModel(col_jTableListOrder,0);
            jTableListOrderLab.setModel(tm);             
            return;
        }
        tm = new TaBleModel(col_jTableListOrder,vOrderItemLab.size());
        int vOrderItemLab_size = 0;
        if(vOrderItemLab != null) vOrderItemLab_size = vOrderItemLab.size();
        for(int i=0 ; i<vOrderItemLab_size; i++) 
        {  
            OrderItem oi = (OrderItem)vOrderItemLab.get(i);
            Hashtable ht = new Hashtable();            
            ht.put("OrderItem",oi);
            ht.put("String","");
            String ttt = theHC.theOrderControl.getTTTRenderDayOrder(oi,"");
            ht.put("display_string", ttt);  
            tm.setValueAt(ht,i,0);
            String day_verify = DateUtil.getDateToStringShort(DateUtil.getDateFromText(oi.vertify_time),true); 
            tm.setValueAt(day_verify,i,1);
            tm.setValueAt(oi.refer_out,i,2);
            if(oi.status.equals(OrderStatus.VERTIFY))
                tm.setValueAt(Constant.getTextBundle("ยืนยัน"),i,3);
            else if(oi.status.equals(OrderStatus.EXECUTE))
                tm.setValueAt(Constant.getTextBundle("ดำเนินการ"),i,3);
            else if(oi.status.equals(OrderStatus.REPORT))
                tm.setValueAt(Constant.getTextBundle("รายงานผล"),i,3);
            else if(oi.status.equals(OrderStatus.REMAIN)) 
                tm.setValueAt(Constant.getTextBundle("ค้างผล"),i,3);
        }
        //เป็นการกำหนดให้ table จำรายการที่ถูกเลือกไว้ก่อนที่จะ set vector ใส่เข้าไปใหม่
        //และจะทำการเช็คว่าข้อมูลนั้นมีอยู่หรือเปล่าถ้าไม่มีแล้วก็จะเลือกรายการแรกแทน
        int current_row = -1;
        if(jTableListOrderLab.getSelectedRows().length==1)
            current_row = jTableListOrderLab.getSelectedRow();
        boolean select_all = jTableListOrderLab.getRowCount()>0 &&
                jTableListOrderLab.getSelectedRows().length==jTableListOrderLab.getRowCount();
        
        jTableListOrderLab.setModel(tm); 
        
        jTableListOrderLab.setRowHeight(ROW_HIGH);
        //jTableListOrderLab.selectAll();
        jTableListOrderLab.getColumnModel().getColumn(0).setPreferredWidth(100); // name 
        jTableListOrderLab.getColumnModel().getColumn(0).setCellRenderer(cellRendererDayOrder);
        jTableListOrderLab.getColumnModel().getColumn(1).setPreferredWidth(100); // name 
        jTableListOrderLab.getColumnModel().getColumn(2).setPreferredWidth(15); // referout
        jTableListOrderLab.getColumnModel().getColumn(2).setCellRenderer(celRendererLabReferOut);
        jTableListOrderLab.getColumnModel().getColumn(3).setPreferredWidth(40); // referout
        
        if(current_row!=-1 && current_row < jTableListOrderLab.getRowCount()) {
            jTableListOrderLab.setRowSelectionInterval(current_row,current_row);
            jTableListOrderLabMouseReleased(null);
        }
        else if(select_all){
            jTableListOrderLab.selectAll();
            panelResultLabCur.setOrderSelectedAll();
        }
        else{
            jTableListOrderLab.clearSelection();
            panelResultLabCur.setOrderSelected(null);
        }
            
    }
    
 
    ////////////////////////////////////////////////////////////////////////////
    /**
     *ตรวจสอบการVisit
     *แล้วทำการเซตปุ่มต่างๆๆ
     */
    private boolean setVisit(Visit v)
    {
        jTabbedPane1.setSelectedIndex(0);
        theVisit = v;
        if(theVisit == null){
            clearGui();
            setEnabled(false);
            return false;
        }
        if(theVisit!=null)    
            this.jTextAreaNote.setText(theVisit.diagnosis_note);
        
        setEnabled(true);

        return true;
    }
   
    ////////////////////////////////////////////////////////////////////////////
    public void setLanguage(String msg){
//        jButtonSaveLab.setText(Constant.getTextBundle(jButtonSaveLab.getText()));
//        jButtonCancelReferOut.setText(Constant.getTextBundle(jButtonCancelReferOut.getText()));
//        jButtonPrintResultLab.setText(Constant.getTextBundle(jButtonPrintResultLab.getText()));
        
        GuiLang.setLanguage(this);
        GuiLang.setLanguage(jTabbedPane1);
        GuiLang.setLanguage(col_jTableListOrder);
        GuiLang.setLanguage(col_jTableListOrderReport);
        GuiLang.setLanguage(col_jTableDetailOrderLab);
        GuiLang.setLanguage(jTableXrayHistory_col);
        GuiLang.setLanguage(jButtonSendResult);
        GuiLang.setLanguage(jButtonRemainLab);
        GuiLang.setLanguage(jButtonExecute);
        GuiLang.setLanguage(jButtonDelectQueueLab);
        GuiLang.setLanguage(jButtonPrintResultLab);
        GuiLang.setLanguage(jButtonCancelReferOut);
        GuiLang.setLanguage(jButtonSaveLab);
        GuiLang.setLanguage(jButtonReferOut);
        GuiLang.setLanguage(jButtonAddOrderItemLab);
        GuiLang.setLanguage(jButtonDelOrderItemLab);
        GuiLang.setLanguage(jButtonSearchHistory);
        GuiLang.setLanguage(jRadioButtonSelectAll);
        GuiLang.setLanguage(jButtonSortResult);
        GuiLang.setLanguage(jButtonSelectAll);
    } 
    /**
     * ฟั่งก์ชั่นนี้จะทำงานอัตโนมัติเมื่อมีการเรียกใช้ผู้ป่วยแสดงข้อมูลผู้ป่วย
     * @param  status สถานะ  str ข้อความ
     * @return-
     * @author modify by kingland
     * @date 16/08/2549
     */
    public void notifyReadVisit(String str, int status) 
    {
        //เคลียร์ข้อมูลก่อนทำการเพิ่มข้อมูลใหม่
        String old_patient = "";
        if(theVisit!=null)
            old_patient = theVisit.patient_id;
        
        setVisit(theHO.theVisit);
        setOrderItemLabV(theHO.theVisit);
        setResultLabV(vOrderItemLab);
        jTableListOrderLab.requestFocus();
        
        if(theVisit!=null
        && theHO.theVisit!=null
        && !old_patient.equals(theHO.theVisit.patient_id))
        {
            Vector visitHistoryV = theHC.theVisitControl
                    .listVisitLabByDatePid(true,"","",theHO.thePatient.getObjectId());
            setVisitHistoryV(visitHistoryV);
        }
    }
    ///////////////////////////////////////////////////////////////////////////
    public void notifyCheckDoctorTreament(String str, int status) {
    }    
    //////////////////////////////////////////////////////////////////////////
    public void notifyManagePatientLabReferIn(String str, int status) {
        setEnabled(true);
    }
    
    public void notifyObservVisit(String str, int status) {
    }
    
    public void notifyUnlockVisit(String str, int status) {
        clearGui();
    }
    
    public void notifyVisitPatient(String str, int status) {
    }
    
    public void notifyAdmitVisit(String str, int status) {       
    }
    
    public void notifyReceiveReturnDrug(String str, int status) {
    }        
    public void notifyCancelOrderItem(String str, int status) 
    {
        if(theHO.theVisit!=null){
            setVisit(theHO.theVisit);
            setOrderItemLabV(theHO.theVisit);
            setResultLabV(vOrderItemLab);
            jTableListOrderLab.requestFocus();
            return;
        }           
    }
    
    public void notifyCheckAutoOrder(String str, int status) {
    }
    
    public void notifyDoctorOffDrug(String DoctorId, int status) {
    }      
    
   
    public void notifySaveOrderItem(String str, int status) {
        
    }
    
    public void notifySaveOrderItemFromDialogOrder(Vector vOrderItem, Vector vOrderItemDrug, String msg) {
    }
    
    public void notifySaveQueueLab(QueueLab queueLab, String msg, int status) {
    }
    
    public void notifySaveQueueXray(QueueXray queueXray, String msg, int status) {
    }
    
    public void notifySaveOrderRequest(String str, int status) {
    }
    
    public void notifyDropVisit(String str, int status) {
    }
    
    public void notifySendVisit(String str, int status) {
    }
    
    public void notifyDischargeFinancial(String str, int status) {
    }
    
    public void notifyManagePayment(String str, int status) {
    }
    
    public void notifyReverseFinancial(String str, int status) {
    }
    
    public void notifyReverseDoctor(String str, int status) {
    }
    
    public void notifySaveLabResult(String str, int status) {
        setOrderItemLabV(theHO.theVisit);
    }
    
    public void notifySaveFilmXray(String str, int status) {
    }
    
    public void notifyDeleteVisitPayment(String str, int status) {
    } 
    
    public void notifyDeleteFilmXray(String str, int status) {
    }
    
    public void notifyDeleteXrayPosition(String str, int status) {
    }
    
    public void notifySaveResultXray(String str, int status) {
    }
    
    public void notifySaveXrayPosition(String str, int status) {
    }
    
    public void notifyXrayReportComplete(String str, int status) {
    }
    
    public void notifyDeleteLabOrder(String msg, int status) {
        setOrderItemLabV(theHO.theVisit);
        setResultLabV(vOrderItemLab);
        jTableListOrderLab.requestFocus();
    }
    
    public void notifyDeleteLabResult(String str, int status) {
        setOrderItemLabV(theHO.theVisit);
        setResultLabV(vOrderItemLab);
    }
    
    public void notifyExecuteOrderItem(String str, int status) {
        setOrderItemLabV(theHO.theVisit);
        setResultLabV(vOrderItemLab);
    }
    
    public void notifyContinueOrderItem(String str, int status) {
    }
    
    public void notifyDispenseOrderItem(String str, int status) {
    }
    
    public void notifySaveOrderItemInLab(String str, int status) {
        setOrderItemLabV(theHO.theVisit);
        setResultLabV(vOrderItemLab);
    }
    
    public void notifyReferOutLab(String msg, int status) {
        setOrderItemLabV(theHO.theVisit);
    }
    public void notifyDischargeDoctor(String str, int status) {
    }
    
    public void notifyRemainDoctorDischarge(String str, int status) {
    }
    
    public void notifySendVisitBackWard(String str, int status) {
    }
    
    public void notifySaveReturnDrug(String str, int status) {
    }
    
    public void notifyReportResultLab(String str, int status) {
        clearGui();
    }
    
    public void notifyDeleteResultXray(String str, int status) {
    }
    
    public void notifyVerifyOrderItem(String str, int status) {
        setOrderItemLabV(theHO.theVisit);
        setResultLabV(vOrderItemLab);
        jTableListOrderLab.requestFocus();
    }    
        
    public void notifyAddLabReferOut(String str, int status) {
    }
    
    public void notifyAddLabReferIn(String str, int status) {
    }
    
    public void notifyReveseAdmit(String str, int status) {
    }
    
    public void notifyReverseAdmit(String str, int status) {
    }
    
    public void notifySaveRemainLabResult(String str, int status) {
        setOrderItemLabV(theHO.theVisit);
    }
    
    public void notifySendResultLab(String str, int status) {
        setVisit(theHO.theVisit);
        setOrderItemLabV(theHO.theVisit);
        setResultLabV(vOrderItemLab);
    }
    public void clearGui()
    {
        setOrderItemLabV(new Vector());
        this.panelResultLabCur.setResultLabV(new Vector());
        this.panelResultLabHis.setResultLabV(new Vector());
        setVisitHistoryV(new Vector());
        setEnabled(false);
        vResultLabHistory = null;
        vResultLab = null;
    }
    public void notifyDeleteQueueLab(String str, int status) {
        clearGui();
    }
    public void gc(){
    	
    }
    public void notifySavePatient(String str, int status) {
    }

    public void notifyResetPatient(String str, int status) {
    }

    public void notifyDeletePatient(String str, int status) {
    }

    public void notifyReadPatient(String str, int status) {
        //เคลียร์ข้อมูลก่อนทำการเพิ่มข้อมูลใหม่
        setVisit(theHO.theVisit);
        setOrderItemLabV(theHO.theVisit);
        setResultLabV(new Vector());
        if(theHO.thePatient!=null)
        {
            Vector visitHistoryV = theHC.theVisitControl
                    .listVisitLabByDatePid(true,"","",theHO.thePatient.getObjectId());
            setVisitHistoryV(visitHistoryV);
        }
    }

    public void notifySaveAppointment(String str, int status) {
    }

    public void notifyManageDrugAllergy(String str, int status) {
    }

    public void notifySavePatientPayment(String str, int status) {
    }

    public void notifyDeletePatientPayment(String str, int status) {
    }

    public void notifyReadFamily(String str, int status) {
    }

    
}
