/*
 * PanelReportFile.java
 *
 * Created on 16 สิงหาคม 2548, 15:40 น.
 */

package com.generalreport.gui.panel;

import com.generalreport.control.HosManage;
import com.generalreport.subject.ManageMainReport;
import com.generalreport.subject.ManageShowPanelSelect;
import com.generalreport.utility.Constant;
import com.generalreport.utility.Report;
import com.hospital_os.utility.TimeTextField;
import java.util.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.event.*;
import javax.swing.text.*;
import com.generalreport.control.HosControl;
import com.generalreport.utility.ComboboxModel;
import com.generalreport.utility.Language;
import com.generalreport.utility.*;

import com.generalreport.gui.panel.report.*;

/*****/


public class PanelReportFile extends javax.swing.JPanel implements ManageShowPanelSelect,ManageMainReport
{
    public String cur_path = System.getProperty("user.dir");
    private boolean isSelectBetweenDate = false;
    private boolean isProcessRunning = false;
    public HosManage theHosManage;
    private HosControl theHosControl;
    private ComboboxModel theComboboxModel;
    
    private String select_path = "";
    private String fileType = "";
    private String startDate = "";
    private String endDate = "";
    private String oldPath = ".";
    private String cardname = "";
    private String treatStatus = "";
    
    private MainReport theMainReport;
    HashMap hashmap;
    private Report report ;
    private String cardname_old="";
    private JFileChooser chooser;

    private TimeTextField timeTextField1;

    private TimeTextField timeTextField2;
    public PanelReportFile(HosManage hosManage)
    {
        theHosManage = hosManage;
        theHosManage.theHosControl.theHosSubject.theShowPanelSelectSubject.registerGUIManage(this);
        theHosManage.theHosControl.theHosSubject.theMainReportSubject.registerMainReportManage(this);
        
        theMainReport = new MainReport(hosManage);
        initComponents();
        setConnection(theHosManage,theHosManage.theHosControl);
        
        theComboboxModel = new ComboboxModel();
        initComboBox();
        setLanguage();
        getObjectReport();        
        addCardReport();        
        setGuiForShowReport(320,60);
    }
    
    public void getObjectReport()
    {
        hashmap = Constant.Report;
    }   
   
    public void addCardReport()
    {
        
   //     jPanelDetail.add(theMainReport.thePanelTestReport,theMainReport.thePanelTestReport.getCardName());
        jPanelDetail.add(theMainReport.thePanelReportDrugDispense,theMainReport.thePanelReportDrugDispense.getCardName());
        jPanelDetail.add(theMainReport.thePanelReportChronic,theMainReport.thePanelReportChronic.getCardName());
        jPanelDetail.add(theMainReport.thePanelReportCostPayment,theMainReport.thePanelReportCostPayment.getCardName());
        jPanelDetail.add(theMainReport.thePanelReportPaymentINOUTCup,theMainReport.thePanelReportPaymentINOUTCup.getCardName());
        jPanelDetail.add(theMainReport.thePanelReportClinicINOUTCup,theMainReport.thePanelReportClinicINOUTCup.getCardName());
        jPanelDetail.add(theMainReport.thePanelReportRevenueAndExpenseSummary,theMainReport.thePanelReportRevenueAndExpenseSummary.getCardName());
        jPanelDetail.add(theMainReport.thePanelPatientInServicePoint,theMainReport.thePanelPatientInServicePoint.getCardName());
        jPanelDetail.add(theMainReport.thePanelCostTotalGroupByOrder,theMainReport.thePanelCostTotalGroupByOrder.getCardName());
        jPanelDetail.add(theMainReport.thePanelReportARIC,theMainReport.thePanelReportARIC.getCardName());
        jPanelDetail.add(theMainReport.thePanelPlentyDisease,theMainReport.thePanelPlentyDisease.getCardName());
        jPanelDetail.add(theMainReport.thePanelReportEyeDiseases,theMainReport.thePanelReportEyeDiseases.getCardName());
        jPanelDetail.add(theMainReport.thePanelCostPaymentShareOFA7INOUTHos,theMainReport.thePanelCostPaymentShareOFA7INOUTHos.getCardName());
        jPanelDetail.add(theMainReport.thePanelPatientAdmitAndDischarge,theMainReport.thePanelPatientAdmitAndDischarge.getCardName());
        jPanelDetail.add(theMainReport.thePanelReportPatientOverService,theMainReport.thePanelReportPatientOverService.getCardName());
        jPanelDetail.add(theMainReport.thePanelCostDrugInServicePoint,theMainReport.thePanelCostDrugInServicePoint.getCardName());
        jPanelDetail.add(theMainReport.thePanelOrderLab,theMainReport.thePanelOrderLab.getCardName());
        jPanelDetail.add(theMainReport.thePanelAccident19Cause,theMainReport.thePanelAccident19Cause.getCardName()); 

        ///1
       /* thePanelReport504Group = new PanelReport504Group(theHosManage.theHosControl);
        thePanelReport505Group = new PanelReport505Group(theHosManage.theHosControl);
        thePanelReport506Group = new PanelReport506Group(theHosManage.theHosControl);
        thePanelReport504Name = new PanelReport504Name(theHosManage.theHosControl);
        thePanelReport505Name = new PanelReport505Name(theHosManage.theHosControl);;
        thePanelReport506Name = new PanelReport506Name(theHosManage.theHosControl);;
        thePanelReport506FollowPatient = new PanelReport506Surveil(theHosManage.theHosControl);
        thePanelReport115Group1 = new PanelReport115Group1(theHosManage.theHosControl);
        thePanelReport115Group2 = new PanelReport115Group2(theHosManage.theHosControl);
        thePanelReport115Group3 = new PanelReport115Group3(theHosManage.theHosControl);
        thePanelReport115Group4 = new PanelReport115Group4(theHosManage.theHosControl);
        
        jPanelDetail.add(thePanelReport504Group,thePanelReport504Group.getCardName());
        jPanelDetail.add(thePanelReport505Group,thePanelReport505Group.getCardName());
        jPanelDetail.add(thePanelReport506Group,thePanelReport506Group.getCardName());
        jPanelDetail.add(thePanelReport504Name,thePanelReport504Name.getCardName());
        jPanelDetail.add(thePanelReport505Name,thePanelReport505Name.getCardName());
        jPanelDetail.add(thePanelReport506Name,thePanelReport506Name.getCardName());
        jPanelDetail.add(thePanelReport506FollowPatient,thePanelReport506FollowPatient.getCardName());
        jPanelDetail.add(thePanelReport115Group1,thePanelReport115Group1.getCardName());
        jPanelDetail.add(thePanelReport115Group2,thePanelReport115Group2.getCardName());
        jPanelDetail.add(thePanelReport115Group3,thePanelReport115Group3.getCardName());
        jPanelDetail.add(thePanelReport115Group4,thePanelReport115Group4.getCardName());
        */
    }
    
    public void setConnection(HosManage theHosManage,HosControl theHosControl)
    {
        this.theHosManage = theHosManage;
        this.theHosControl = theHosControl;
        System.out.println("--- Pass set connection");
      //  this.jButtonSave.setEnabled(false);
        this.setEnableGUI(true);
    }
    
    /**ใช้ในการกำหนดให้ Combobox แสดงค่าเริ่มต้น*/
    public void initComboBox()
    {
        initReportName();
        initReportType();      
    }
    /**
     *  กำหนดค่าของ Combobox รายงานแสดงชื่อรายงาน
     */
    private void initReportName()
    { 
        for(int i=0;i<Constant.Report.size();i++)
        {   report = (Report)Constant.Report.get(String.valueOf(i+1));
            jComboBoxReport.addItem(report.THAI_NAME);
            report = null;
        }
        report = null;
    }
    
    /**
     *  กำหนดค่าของ Combobox บันทึก
     */
    private void initReportType()
    {
        for(int j=0;j<Constant.REPORT_TYPE.length;j++)
        {
            jComboBoxType.addItem(Constant.REPORT_TYPE[j]);
        }
    }
 
    
    public void setProcessRunning(boolean isProcessRunning)
    {
        this.isProcessRunning = isProcessRunning;
    }
    
    private void setGuiForShowReport(int x,int y)
    {
        this.jPanelControl.setPreferredSize(new java.awt.Dimension(x, y));
        this.jPanelControl.setMinimumSize(new java.awt.Dimension(x, y));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelDetail = new javax.swing.JPanel();
        jPanelControl = new javax.swing.JPanel();
        jLabelReport = new javax.swing.JLabel();
        jComboBoxType = new javax.swing.JComboBox();
        jLabelType = new javax.swing.JLabel();
        jComboBoxReport = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabelStartDate = new javax.swing.JLabel();
        jLabelEndDate = new javax.swing.JLabel();
        jButtonView = new javax.swing.JButton();
        dateComboBoxStartDate = new com.hospital_os.utility.DateComboBox();
        dateComboBoxEndDate = new com.hospital_os.utility.DateComboBox();
        jCheckBoxDBBackup = new javax.swing.JCheckBox();
        jButtonSave = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButtonBackToMain = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(470, 552));
        setLayout(new java.awt.GridBagLayout());

        jPanelDetail.setLayout(new java.awt.CardLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jPanelDetail, gridBagConstraints);

        jPanelControl.setLayout(new java.awt.GridBagLayout());

        jLabelReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/standardReport/images/assign_permission.gif"))); // NOI18N
        jLabelReport.setText("รูปแบบรายงาน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanelControl.add(jLabelReport, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanelControl.add(jComboBoxType, gridBagConstraints);

        jLabelType.setText("ชนิดของไฟล์");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelControl.add(jLabelType, gridBagConstraints);

        jComboBoxReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxReportActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanelControl.add(jComboBoxReport, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabelStartDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/standardReport/images/appointment.gif"))); // NOI18N
        jLabelStartDate.setText("จากวันที่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 0);
        jPanel2.add(jLabelStartDate, gridBagConstraints);

        jLabelEndDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEndDate.setText("ถึงวันที่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel2.add(jLabelEndDate, gridBagConstraints);

        jButtonView.setText("Query");
        jButtonView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 0, 0);
        jPanel2.add(jButtonView, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel2.add(dateComboBoxStartDate, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel2.add(dateComboBoxEndDate, gridBagConstraints);

        jCheckBoxDBBackup.setText("ต้องการดึงรายงานจากฐานข้อมูลสำรอง");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel2.add(jCheckBoxDBBackup, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelControl.add(jPanel2, gridBagConstraints);

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanelControl.add(jButtonSave, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jPanelControl, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButtonBackToMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/standardReport/images/Back.gif"))); // NOI18N
        jButtonBackToMain.setText("Back");
        jButtonBackToMain.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonBackToMain.setMaximumSize(new java.awt.Dimension(70, 24));
        jButtonBackToMain.setMinimumSize(new java.awt.Dimension(70, 24));
        jButtonBackToMain.setPreferredSize(new java.awt.Dimension(70, 24));
        jButtonBackToMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackToMainActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButtonBackToMain, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonSaveActionPerformed
    {//GEN-HEADEREND:event_jButtonSaveActionPerformed
        if(this.setPathFileToSave())
        {
            this.setDataForSaveReport();
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jComboBoxReportActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxReportActionPerformed
    {//GEN-HEADEREND:event_jComboBoxReportActionPerformed
       setShowPanel(jComboBoxReport.getSelectedIndex());
    }//GEN-LAST:event_jComboBoxReportActionPerformed

    private void jButtonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewActionPerformed
        queryData();
       
    }//GEN-LAST:event_jButtonViewActionPerformed

    private void jButtonBackToMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackToMainActionPerformed
        backToMainReport();
    }//GEN-LAST:event_jButtonBackToMainActionPerformed
    
    public void setShowPanel(int select)
    {
        //ตรวจสอบลำดับต้องมากกว่า -1
        if(select > -1)
        {   //ตรวจสอบ hashmap มีค่าข้อมูลหรือไม่
            if(hashmap != null)
            {
                //ให้ค่ากับ object report
                this.report = (Report)hashmap.get(String.valueOf(select+1));  
                
                if(this.report.INDEX.equals(Integer.toString(select+1)))
               {
                   System.out.println("SelectedIndex : " + this.report.INDEX);
                   this.cardname = this.report.ENG_NAME;
                   
                   if(!this.cardname.equalsIgnoreCase(cardname_old))
                    {
                        this.cardname_old = this.cardname;
                        setEnableSaveToFile(false);
                    }
                   this.theHosControl.theHosSubject.theAllPanelSubject.notifySetInitAllGUI();
                   this.showCardReport(this.cardname);
               }
            }
        }          
        
    }
    
    
    /**ใช้ในการ Query ข้อมูล และส่งข้อมูลไปยัง รายงานที่เลือกไว้*/
    public void queryData()
    {      
        if(checkData())
        {
            int rowReport = jComboBoxReport.getSelectedIndex();
            System.out.println("Select name Report : " +getNameEngReport(rowReport));
          
            if(theMainReport.thePanelReportDrugDispense.getCardName().equalsIgnoreCase(getNameEngReport(rowReport)))
            {
                theMainReport.thePanelReportDrugDispense.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            if(theMainReport.thePanelReportChronic.getCardName().equalsIgnoreCase(getNameEngReport(rowReport)))
            {
                theMainReport.thePanelReportChronic.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            if(theMainReport.thePanelReportCostPayment.getCardName().equalsIgnoreCase(getNameEngReport(rowReport)))
            {
                theMainReport.thePanelReportCostPayment.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            if(theMainReport.thePanelReportPaymentINOUTCup.getCardName().equalsIgnoreCase(getNameEngReport(rowReport)))
            {
                theMainReport.thePanelReportPaymentINOUTCup.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            if(theMainReport.thePanelReportClinicINOUTCup.getCardName().equalsIgnoreCase(getNameEngReport(rowReport)))
            {
                theMainReport.thePanelReportClinicINOUTCup.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            if(theMainReport.thePanelReportRevenueAndExpenseSummary.getCardName().equalsIgnoreCase(getNameEngReport(rowReport)))
            {
                theMainReport.thePanelReportRevenueAndExpenseSummary.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            if(theMainReport.thePanelPatientInServicePoint.getCardName().equalsIgnoreCase(getNameEngReport(rowReport)))
            {
                theMainReport.thePanelPatientInServicePoint.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            if(theMainReport.thePanelCostTotalGroupByOrder.getCardName().equalsIgnoreCase(getNameEngReport(rowReport)))
            {
                theMainReport.thePanelCostTotalGroupByOrder.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            if(theMainReport.thePanelReportARIC.getCardName().equalsIgnoreCase(getNameEngReport(rowReport)))
            {
                theMainReport.thePanelReportARIC.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
           
            if(theMainReport.thePanelPlentyDisease.getCardName().equalsIgnoreCase(getNameEngReport(rowReport)))
            {
                theMainReport.thePanelPlentyDisease.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }

            if(theMainReport.thePanelReportEyeDiseases.getCardName().equalsIgnoreCase(getNameEngReport(rowReport)))
            {
                theMainReport.thePanelReportEyeDiseases.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            if(theMainReport.thePanelCostPaymentShareOFA7INOUTHos.getCardName().equalsIgnoreCase(getNameEngReport(rowReport)))
            {
                theMainReport.thePanelCostPaymentShareOFA7INOUTHos.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            if(theMainReport.thePanelPatientAdmitAndDischarge.getCardName().equalsIgnoreCase(getNameEngReport(rowReport)))
            {
                theMainReport.thePanelPatientAdmitAndDischarge.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            if(theMainReport.thePanelReportPatientOverService.getCardName().equalsIgnoreCase(getNameEngReport(rowReport)))
            {
                theMainReport.thePanelReportPatientOverService.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            if(theMainReport.thePanelCostDrugInServicePoint.getCardName().equalsIgnoreCase(getNameEngReport(rowReport)))

            {
                theMainReport.thePanelCostDrugInServicePoint.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            if(theMainReport.thePanelOrderLab.getCardName().equalsIgnoreCase(getNameEngReport(rowReport)))

            {
                theMainReport.thePanelOrderLab.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            if(theMainReport.thePanelAccident19Cause.getCardName().equalsIgnoreCase(getNameEngReport(rowReport)))

            {
                theMainReport.thePanelAccident19Cause.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            
            ///2
        }
        else
        {
            JOptionPane.showMessageDialog(this,Language.getTextBundle("TimeWarning",1),"Warning",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private String getNameEngReport(int i)
    {
        report = (Report)Constant.Report.get(String.valueOf(i+1));
        return report.ENG_NAME;
    }
    
    /**
     *  ใช้ในการตรวจสอบค่าของข้อมูลต่างๆว่าถูกต้องตามความต้องการหรือไม่
     *  @return boolean ถ้าเป็น true จะไม่มี่ปัญหากับข้อมูล ถ้าเป็น false จะมีปัญหากับข้อมูล
     */
    private boolean checkData()
    {
        boolean result = false;
        result = this.isBetweenDateFormat();
        return result;
    }
    
    /**ใช้ในการ แสดงรายงานใน Combobox ตาม param ที่ส่งเข้ามา
     *  @param report เป็น Object ของ Report
     */
    private void setShowReport(Report report)
    {
        jComboBoxReport.setSelectedItem(report.THAI_NAME);
        this.cardname = report.ENG_NAME;
        this.showCardReport(this.cardname);
        
    }
    private void clearGUI()
    {
    }
    
    /*
     *ใช้ในการกลับไปยังหน้า MainReport
     *เพื่อเลือกรายงานทั่วไป
     */
    public void  backToMainReport()
    {
        theHosManage.theHosControl.theHosSubject.theShowPanelSelectSubject.notifyCallBackToMainReport();
    }
    
    /**
     *เซ็ตข้อมูลสำหรับบันทึกเป็นไฟล์
     */
    public void setDataForSaveReport()
    {
        if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelReportDrugDispense.getCardName()))
        {
            Vector vcData = this.theMainReport.thePanelReportDrugDispense.getDrugDispense();
            this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
        }
        else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelReportChronic.getCardName()))
        {
            Vector vcData = this.theMainReport.thePanelReportChronic.getChronic();
            this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
        }
        else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelReportCostPayment.getCardName()))
        {
            Vector vcData = this.theMainReport.thePanelReportCostPayment.getCostPayment();
            this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
        }
        
        else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelReportPaymentINOUTCup.getCardName()))
        {
            Vector vcData = this.theMainReport.thePanelReportPaymentINOUTCup.getData();
            this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
        }
        else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelReportClinicINOUTCup.getCardName()))
        {
            Vector vcData = this.theMainReport.thePanelReportClinicINOUTCup.getData();
            this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
        }
        
        else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelReportRevenueAndExpenseSummary.getCardName()))
        {
            Vector vcData = this.theMainReport.thePanelReportRevenueAndExpenseSummary.getRevenueExpense();
            
              this.theHosControl.theExportControl.startExportByVectorOfObject(vcData,this.select_path,this.fileType,this.report.ENG_NAME +"_"+ this.theMainReport.thePanelReportRevenueAndExpenseSummary.getFileName());
        }
        else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelPatientInServicePoint.getCardName()))
        {
            Vector vcData = this.theMainReport.thePanelPatientInServicePoint.getPatientInServicePoint();
            this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
        }
        else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelCostTotalGroupByOrder.getCardName()))
        {
            Vector vcData = this.theMainReport.thePanelCostTotalGroupByOrder.getCostTotalGroupByOrder();
            this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
        }
        else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelReportARIC.getCardName()))
        {
            System.out.println("Show Data Value ของ ARIC");
            Vector vcData = this.theMainReport.thePanelReportARIC.getData();
            this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
        }
        else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelPlentyDisease.getCardName()))
        {
            Vector vcData = this.theMainReport.thePanelPlentyDisease.getPlentyDisease();
            this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME +"_"+ this.theMainReport.thePanelPlentyDisease.getFileName());
        }
            if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelReportDrugDispense.getCardName()))
            {
                Vector vcData = this.theMainReport.thePanelReportDrugDispense.getDrugDispense();
                this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
            }
            else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelReportChronic.getCardName()))
            {
                Vector vcData = this.theMainReport.thePanelReportChronic.getChronic();
                this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
            }
            else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelReportCostPayment.getCardName()))
            {
                Vector vcData = this.theMainReport.thePanelReportCostPayment.getCostPayment();
                this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
            }

            else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelReportPaymentINOUTCup.getCardName()))
            {
                Vector vcData = this.theMainReport.thePanelReportPaymentINOUTCup.getData();
                this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
            }
            else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelReportClinicINOUTCup.getCardName()))
            {
                Vector vcData = this.theMainReport.thePanelReportClinicINOUTCup.getData();
                this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
            }

            else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelReportRevenueAndExpenseSummary.getCardName()))
            {
                Vector vcData = this.theMainReport.thePanelReportRevenueAndExpenseSummary.getRevenueExpense();                
              
                //  this.theHosControl.theExportControl.startExportByVectorOfObject(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
            }
            else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelPatientInServicePoint.getCardName()))
            {
                Vector vcData = this.theMainReport.thePanelPatientInServicePoint.getPatientInServicePoint();
                this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
            }
            else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelCostTotalGroupByOrder.getCardName()))
            {
                Vector vcData = this.theMainReport.thePanelCostTotalGroupByOrder.getCostTotalGroupByOrder();
                this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
            }
            else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelReportARIC.getCardName()))
            {
                System.out.println("Show Data Value ของ ARIC");
                Vector vcData = this.theMainReport.thePanelReportARIC.getData();
                this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
            }
              else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelReportEyeDiseases.getCardName()))
            {

                System.out.println("");
                 Vector vcData = this.theMainReport.thePanelReportEyeDiseases.getData();
                this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
            }
             else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelCostPaymentShareOFA7INOUTHos.getCardName()))
            {
                System.out.println(" ");
                Vector vcData = this.theMainReport.thePanelCostPaymentShareOFA7INOUTHos.getData();
                this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME + this.theMainReport.thePanelCostPaymentShareOFA7INOUTHos.getSaveFile());
            }
             else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelPatientAdmitAndDischarge.getCardName()))
            {
                System.out.println(" ");
                Vector vcData = this.theMainReport.thePanelPatientAdmitAndDischarge.getData();
                this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
            }
            else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelReportPatientOverService.getCardName()))
            {
                System.out.println(" ");
                Vector vcData = this.theMainReport.thePanelReportPatientOverService.getData();
                this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME+"_"+ this.theMainReport.thePanelReportPatientOverService.getFileName()); 
            }
            else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelCostDrugInServicePoint.getCardName()))
            {
                System.out.println(" ");
                Vector vcData = this.theMainReport.thePanelCostDrugInServicePoint.getData();
                this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
            }
            else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelOrderLab.getCardName()))
            {
                System.out.println(" ");
                Vector vcData = this.theMainReport.thePanelOrderLab.getData();
                this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME +"_"+ this.theMainReport.thePanelOrderLab.getFileName());
            }
            else if(this.cardname.equalsIgnoreCase(this.theMainReport.thePanelAccident19Cause.getCardName()))
            {
                System.out.println(" ");
                Vector vcData = this.theMainReport.thePanelAccident19Cause.getData();
                this.theHosControl.theExportControl.startExportByVector(vcData,this.select_path,this.fileType,this.report.ENG_NAME);
            }
        
    }
    
    /**
     *  Function ส่งค่าวันที่เริ่ม Query และ สิ้นสุดการ Query ให้กับ Panel ที่เลือก
     *  ทำการตรวจสอบวันที่จาก Function isBetweenDateFormat() ถ้าถูกต้อง (Return true) ก็สามารถทำงานต่อได้
     *  จากนั้นตรวจสอบรายงานที่เลือกว่าตรงกับ Panel ของรายงานใด ให้ส่งค่าให้ Panel นั้น เพื่อทำงานต่อ
     *  ojika
     **/
    public void setDataForQueryReport()
    {
        if(isBetweenDateFormat())
        {
/*
            if(this.cardname.equalsIgnoreCase(this.thePanelReport504Group.getCardName()))
            {
                this.thePanelReport504Group.setQueryReport(dateComboBoxStartDate.getText(),dateComboBoxEndDate.getText());
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport505Group.getCardName()))
            {
                this.thePanelReport505Group.setQueryReport(dateComboBoxStartDate.getText(),dateComboBoxEndDate.getText());
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport506Group.getCardName()))
            {
                this.thePanelReport506Group.setQueryReport(dateComboBoxStartDate.getText(),dateComboBoxEndDate.getText());
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport504Name.getCardName()))
            {
                this.thePanelReport504Name.setQueryReport(dateComboBoxStartDate.getText(),dateComboBoxEndDate.getText());
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport505Name.getCardName()))
            {
                this.thePanelReport505Name.setQueryReport(dateComboBoxStartDate.getText(),dateComboBoxEndDate.getText());
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport506Name.getCardName()))
            {
                this.thePanelReport506Name.setQueryReport(dateComboBoxStartDate.getText(),dateComboBoxEndDate.getText());
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport506FollowPatient.getCardName()))
            {
                if(this.treatStatus.trim().equalsIgnoreCase("0"))
                {
                    this.thePanelReport506FollowPatient.setQueryReport(dateComboBoxStartDate.getText(),dateComboBoxEndDate.getText());
                    System.out.println("--- treatStatus IF : " + this.treatStatus);
                }
                else
                {
                    System.out.println("--- treatStatus ELSE : " + this.treatStatus);
                    this.thePanelReport506FollowPatient.setQueryReport(dateComboBoxStartDate.getText(),dateComboBoxEndDate.getText(), this.treatStatus);
                }
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport115Group1.getCardName()))
            {
                this.thePanelReport115Group1.setQueryReport(dateComboBoxStartDate.getText(),dateComboBoxEndDate.getText());
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport115Group2.getCardName()))
            {
                this.thePanelReport115Group2.setQueryReport(dateComboBoxStartDate.getText(),dateComboBoxEndDate.getText());
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport115Group3.getCardName()))
            {
                this.thePanelReport115Group3.setQueryReport(dateComboBoxStartDate.getText(),dateComboBoxEndDate.getText());
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport115Group4.getCardName()))
            {
                this.thePanelReport115Group4.setQueryReport(dateComboBoxStartDate.getText(),dateComboBoxEndDate.getText());
            } */
        }
        else
        {
            JOptionPane.showMessageDialog(this,Language.getTextBundle("TimeWarning",1),"Warning",JOptionPane.WARNING_MESSAGE);
        }
    }
    

    public void showCardReport(String cardname)
    {
        CardLayout layout = (CardLayout)this.jPanelDetail.getLayout();
        layout.show(this.jPanelDetail, cardname);
    }
    
    public void setEnableGUI(boolean isEnable)
    {
        jComboBoxReport.setEnabled(isEnable);
        // jComboBoxType.setEnabled(isEnable);
    }
    /*
     *  ใช้ในการตรวจสอบวันที่สำหรับการออกรายงาน ให้อยู่ในรูปแบบที่ถูกต้อง คือ วันที่เริ่มต้นในการ query
     *  ต้องไม่เป็นวันที่ในอนาคต หรือเป็นวันที่มากกว่าวันที่ถูกระบุว่าเป็นวันที่สิ้นสุดของการ query
     *  @return boolean ถ้าค่าเป็น true แสดงว่าเป็บช่วงวันที่ที่ถูกต้อง ถ้าเป็น false แสดงว่าเป็นช่วงวันที่ไม่ถูกต้อง
     */
    private boolean isBetweenDateFormat()
    {
        setBetweenDate(dateComboBoxStartDate.getText(),dateComboBoxEndDate.getText());
        if(Gutil.beforDate(DateUtil.getDateFromText(this.startDate),DateUtil.getDateFromText(this.endDate)) || (this.startDate.compareTo(this.endDate) <= 0))
        {
            isSelectBetweenDate = true;
        }
        else
        {
            isSelectBetweenDate = false;
        }
        return isSelectBetweenDate;
    }
    
    /*
     *กำหนดค่าให้กับตัวแปรวันที่เริ่มต้น และวันที่สิ้นสุด ในการนำมา query
     *@param startDate วันที่เริ่มต้น
     *@param endDate วันที่สิ้นสุด
     */
    private  void setBetweenDate(String startDate,String endDate)
    {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public boolean setPathFileToSave()
    {
        boolean isSetPathFileDone = false;
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(oldPath));
        chooser.setDialogTitle(Language.getTextBundle("PleaseSelectPathForSave",1));
        
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setApproveButtonText("Save");
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            System.out.println("getCurrentDirectory(): " +  chooser.getSelectedFile().getPath());
            int index = jComboBoxType.getSelectedIndex();
            if(index == 0)
            {
                setFileType(Constant.TXT_FILE);
            }
            else if(index == 1)
            {
                setFileType(Constant.XLS_FILE);
            }
            else if(index == 2)
            {
                setFileType(Constant.DBF_FILE);
            }
            oldPath = chooser.getSelectedFile().getPath();
            select_path =  chooser.getSelectedFile().getPath()+"\\";
            isSetPathFileDone = true;
        }
        else
        {
            isSetPathFileDone = false;
        }
        return isSetPathFileDone;
    }
    /**
     *  ใช้ในการกำหนดให้ การบันทึกสามารถทำงานได้หรือไม่
     *  @param isEnable เป็น boolean ที่ใช้ในการกำหนดให้แสดงหรือไม่แสดง
     *
     */
    public void setEnableSaveToFile(boolean isEnable)
    {
        
        jComboBoxType.setEnabled(isEnable);
        jButtonSave.setEnabled(isEnable);
    }
    
    private void setFileType(String fileType)
    {
        this.fileType = fileType;
    }
    
    public static void setFrame(int width ,int height)
    {
        HosManage hosManage = new HosManage(null,null,null);
        PanelReportFile thePanelReport18File= new PanelReportFile(hosManage);
        thePanelReport18File.setSize(500, 400);
        JFrame frm = new JFrame("PanelReportStandard");
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        //thePanelReport18File.setSize(screenSize);
        frm.setSize(width, height);
        frm.setLocation((screenSize.width-frm.getSize().width)/2,(screenSize.height-frm.getSize().height)/2);
        //frm.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        //frm.setVisible(true);
        
        
        frm.getContentPane().add(thePanelReport18File);
        frm.pack();
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
    }
    public void setLanguage()
    {
        jLabelReport.setText(Language.getTextBundle(jLabelReport.getText(),1));
        
        jLabelStartDate.setText(Language.getTextBundle(jLabelStartDate.getText(),1));
        jLabelType.setText(Language.getTextBundle(jLabelType.getText(),1));
        jLabelEndDate.setText(Language.getTextBundle(jLabelEndDate.getText(),1));
        jButtonSave.setText(Language.getTextBundle(jButtonSave.getText(),1));
        jButtonView.setText(Language.getTextBundle(jButtonView.getText(),1));
    }
    
    public static void main(String args[])
    {
        try
        {
            UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        setFrame(800,600);
         /*PanelReportFile thePanelReport18File= new PanelReportFile();
         //thePanelReport18File.setSize(500, 450);
         Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
         //thePanelReport18File.setSize(screenSize);
         JFrame frm = new JFrame("PanelReportFile");
         frm.setSize(800, 600);
         frm.setLocation((screenSize.width-frm.getSize().width)/2,(screenSize.height-frm.getSize().height)/2);
         //frm.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
         //frm.setVisible(true);
          
          
         frm.getContentPane().add(thePanelReport18File);
         frm.pack();
         frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frm.setVisible(true);
          **/
    }
    
    public void notifyCallBackToMainReport()
    {
    }
    
    public void notifyCallReportShow(Report report)
    {
        this.report = report;
        setShowReport(report);
        clearGUI();
        
    }
    /**ใช้ในการกำหนดค่าวันที่เริ่มต้นและวันที่สิ้นสุดให้กับ combobox
     *  @param startDate เป็น String ของ วันที่เริ่มต้น รูปแบบ เป็น yyyy-mm-dd
     *  @param finishDate เป็น String ของ วันที่สิ้นสุด รูปแบบ เป็น yyyy-mm-dd
     */
    private void setDateCombobox(String startDate,String finishDate)
    {
        
        dateComboBoxStartDate.setText(DateUtil.getGuiDateTime(startDate));
        dateComboBoxEndDate.setText(DateUtil.getGuiDateTime(finishDate));
    }
    public void notifyReturnStartAndFinishDate(String startDate, String finishDate)
    {
        setDateCombobox(startDate, finishDate);
    }
    
    public void notifyShowSaveToFile(boolean show)
    {
        setEnableSaveToFile(show);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.utility.DateComboBox dateComboBoxEndDate;
    private com.hospital_os.utility.DateComboBox dateComboBoxStartDate;
    private javax.swing.JButton jButtonBackToMain;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonView;
    private javax.swing.JCheckBox jCheckBoxDBBackup;
    private javax.swing.JComboBox jComboBoxReport;
    private javax.swing.JComboBox jComboBoxType;
    private javax.swing.JLabel jLabelEndDate;
    private javax.swing.JLabel jLabelReport;
    private javax.swing.JLabel jLabelStartDate;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelControl;
    private javax.swing.JPanel jPanelDetail;
    // End of variables declaration//GEN-END:variables
    
}
