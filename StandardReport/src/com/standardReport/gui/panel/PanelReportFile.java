/*
 * PanelReportFile.java
 *
 * Created on 16 สิงหาคม 2548, 15:40 น.
 */

package com.standardReport.gui.panel;

import com.standardReport.control.HosManage;
import com.standardReport.subject.ManageMainReport;
import com.standardReport.subject.ManageShowPanelSelect;
import java.util.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.table.DefaultTableCellRenderer;

import com.hospital_os.object.Version;
import com.hospital_os.object.MenuItemPlugIn;
import com.standardReport.utility.*;
import com.standardReport.help.ShowHelpStandardReport;
import com.standardReport.control.HosControl;

/*****/
//import com.standardReport.object.objclass.FileName;

public class PanelReportFile extends javax.swing.JPanel implements ManageMainReport ,
 ManageShowPanelSelect
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
    private String cardname_old = "";
    private String treatStatus = "";
    private boolean pushOption = false;
    private boolean checkstartDate = false,checkfinishDate = false;
    /** Panel Report **/
    private PanelReport504Group thePanelReport504Group;
    private PanelReport505Group thePanelReport505Group;
    private PanelReport506Group thePanelReport506Group;
    private PanelReport504Name thePanelReport504Name;
    private PanelReport505Name thePanelReport505Name;
    private PanelReport506Name thePanelReport506Name;
    private PanelReport506Surveil thePanelReport506FollowPatient;
    private PanelReport115Group1_2549 thePanelReport115Group1_2549;
    private PanelReport115Group2_2549 thePanelReport115Group2_2549;
    private PanelReport115Group3_2549 thePanelReport115Group3_2549;
    private PanelReport115Group4_2549 thePanelReport115Group4_2549;
            
    /**ใช้ในการแสดง Help*/
    private ShowHelpStandardReport theShowHelpStandardReport;
   
    
    public PanelReportFile(HosManage theHosManage)
    {
        initComponents();
        theHosManage.theHosControl.theHosSubject.theMainReportSubject.registerMainReportManage(this);
        theHosManage.theHosControl.theHosSubject.theShowPanelSelectSubject.registerGUIManage(this);
        
        setConnection(theHosManage,theHosManage.theHosControl);
        
        theComboboxModel = new ComboboxModel();
        initComboBox();
        setLanguage();
        setVisibleTreatStatus(false);
        addCardReport();
        
        setGuiForShowReport(320,60);
        thePanelReport115Group3_2549.startDate = dateComboBoxStartDate.getText();
        thePanelReport115Group3_2549.endDate = dateComboBoxEndDate.getText();
    }
    /**ใช้ในการกำหนด version ของ การออกรายงาน 12 แฟ้ม 
     *@return เป็น Object ของ Vesion
     */
    public Version getVersion()
    {
        Version version = new Version();
        version.app_code = Config.getTextBundle("Version");
        version.description = Config.getTextBundle("Description");
        version.update_time = Config.getTextBundle("Update");
        version.db_code = Config.getTextBundle("VersionDatabaseUse");
        
        return version;
        
    }
    
     /**ใช้ในการ ส่ง Dialog Help ให้กับ Frame หลัก*/
    public Vector getMenuItem()
    {   javax.swing.JMenuItem jMenuItem1 = new javax.swing.JMenuItem();
        theShowHelpStandardReport = new ShowHelpStandardReport();
        
        jMenuItem1.setText(Gutil.getTextBundle("ReportStandardHelp"));
        theShowHelpStandardReport.addID(jMenuItem1);
        
        MenuItemPlugIn theMenuItemPlugIn = new MenuItemPlugIn();
        theMenuItemPlugIn.authen = true;
        theMenuItemPlugIn.theJMenuItem = jMenuItem1;
                
        Vector vc = new Vector();
        
        
        vc.add(theMenuItemPlugIn);
        return vc;
    }
    public void addCardReport()
    {
        thePanelReport504Group = new PanelReport504Group(theHosManage.theHosControl);
        thePanelReport505Group = new PanelReport505Group(theHosManage.theHosControl);
        thePanelReport506Group = new PanelReport506Group(theHosManage.theHosControl);
        thePanelReport504Name = new PanelReport504Name(theHosManage.theHosControl);
        thePanelReport505Name = new PanelReport505Name(theHosManage.theHosControl);;
        thePanelReport506Name = new PanelReport506Name(theHosManage.theHosControl);;
        thePanelReport506FollowPatient = new PanelReport506Surveil(theHosManage.theHosControl);
        thePanelReport115Group1_2549 = new PanelReport115Group1_2549(theHosManage.theHosControl);
        thePanelReport115Group2_2549 = new PanelReport115Group2_2549(theHosManage.theHosControl);
        thePanelReport115Group3_2549 = new PanelReport115Group3_2549(theHosManage.theHosControl);
        thePanelReport115Group4_2549 = new PanelReport115Group4_2549(theHosManage.theHosControl);
        
        jPanelDetail.add(thePanelReport504Group,thePanelReport504Group.getCardName());
        jPanelDetail.add(thePanelReport505Group,thePanelReport505Group.getCardName());
        jPanelDetail.add(thePanelReport506Group,thePanelReport506Group.getCardName());
        jPanelDetail.add(thePanelReport504Name,thePanelReport504Name.getCardName());
        jPanelDetail.add(thePanelReport505Name,thePanelReport505Name.getCardName());
        jPanelDetail.add(thePanelReport506Name,thePanelReport506Name.getCardName());
        jPanelDetail.add(thePanelReport506FollowPatient,thePanelReport506FollowPatient.getCardName());
        jPanelDetail.add(thePanelReport115Group1_2549,thePanelReport115Group1_2549.getCardName());
        jPanelDetail.add(thePanelReport115Group2_2549,thePanelReport115Group2_2549.getCardName());
        jPanelDetail.add(thePanelReport115Group3_2549,thePanelReport115Group3_2549.getCardName());
        jPanelDetail.add(thePanelReport115Group4_2549,thePanelReport115Group4_2549.getCardName());
    }
    
    public void setConnection(HosManage theHosManage,HosControl theHosControl)
    {
        this.theHosManage = theHosManage;
        this.theHosControl = theHosControl;
        System.out.println("--- Pass set connection");
        this.jButtonSave.setEnabled(false);
        this.setEnableGUI(true);
        
    }
    
    public void initComboBox()
    {
        initReportName();
        initReportType();
        iniTreatStatus();
    }
    
    private void initReportName()
    {
        Report report;
        for(int i=0;i<Constant.Report.size();i++)//for(int i=0;i<Constant.REPORT_NAME.length;i++)
        {
            report = (Report)Constant.Report.get(String.valueOf(i+1));
            jComboBoxReport.addItem(report.THAI_NAME);//jComboBoxReport.addItem(Constant.REPORT_NAME[i]);
        }
    }
    
    private void initReportType()
    {
        for(int j=0;j<Constant.REPORT_TYPE.length;j++)
        {
            jComboBoxType.addItem(Constant.REPORT_TYPE[j]);
        }
    }
    
    private void iniTreatStatus()
    {
        this.theComboboxModel.initComboBox(this.jComboBoxTreatStatus, this.theHosControl.vTreatStatus);
        this.theComboboxModel.setCodeComboBox(this.jComboBoxTreatStatus, "0");
    }
    /**
     * กำหนดให้ รายการ ที่กำหนดให้แสดงหรือไม่
     *  @param b เป็น boolean
     */
    private void setVisibleTreatStatus(boolean b)
    {
        this.jLabelTreatStatus.setVisible(b);
        this.jComboBoxTreatStatus.setVisible(b);
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

        jPanelControl = new javax.swing.JPanel();
        jLabelReport = new javax.swing.JLabel();
        jComboBoxReport = new javax.swing.JComboBox();
        jComboBoxType = new javax.swing.JComboBox();
        jLabelType = new javax.swing.JLabel();
        jLabelStartDate = new javax.swing.JLabel();
        jButtonSave = new javax.swing.JButton();
        jComboBoxTreatStatus = new javax.swing.JComboBox();
        jLabelTreatStatus = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        dateComboBoxStartDate = new com.standardReport.utility.DateComboBox();
        jLabelEndDate = new javax.swing.JLabel();
        dateComboBoxEndDate = new com.standardReport.utility.DateComboBox();
        jButtonView = new javax.swing.JButton();
        jButtonPrint = new javax.swing.JButton();
        jCheckBoxDBBackup = new javax.swing.JCheckBox();
        jPanelDetail = new javax.swing.JPanel();
        jPanelButtom = new javax.swing.JPanel();
        jButtonBackToMain = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(470, 552));
        setLayout(new java.awt.GridBagLayout());

        jPanelControl.setLayout(new java.awt.GridBagLayout());

        jLabelReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/standardReport/images/assign_permission.gif"))); // NOI18N
        jLabelReport.setText("ReportType");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelControl.add(jLabelReport, gridBagConstraints);

        jComboBoxReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxReportActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelControl.add(jComboBoxReport, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelControl.add(jComboBoxType, gridBagConstraints);

        jLabelType.setText("FileType");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelControl.add(jLabelType, gridBagConstraints);

        jLabelStartDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/standardReport/images/appointment.gif"))); // NOI18N
        jLabelStartDate.setText("StartDate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 0);
        jPanelControl.add(jLabelStartDate, gridBagConstraints);

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
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelControl.add(jButtonSave, gridBagConstraints);

        jComboBoxTreatStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTreatStatusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelControl.add(jComboBoxTreatStatus, gridBagConstraints);

        jLabelTreatStatus.setText("Select_Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanelControl.add(jLabelTreatStatus, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        dateComboBoxStartDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxStartDateActionPerformed(evt);
            }
        });
        dateComboBoxStartDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateComboBoxStartDateFocusLost(evt);
            }
        });
        dateComboBoxStartDate.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                dateComboBoxStartDateInputMethodTextChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 0);
        jPanel1.add(dateComboBoxStartDate, gridBagConstraints);

        jLabelEndDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEndDate.setText("EndDate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 0);
        jPanel1.add(jLabelEndDate, gridBagConstraints);

        dateComboBoxEndDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxEndDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 0);
        jPanel1.add(dateComboBoxEndDate, gridBagConstraints);

        jButtonView.setText("Query");
        jButtonView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jButtonView, gridBagConstraints);

        jButtonPrint.setText("Print");
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButtonPrint, gridBagConstraints);

        jCheckBoxDBBackup.setText("ต้องการดึงรายงานจากฐานข้อมูลสำรอง");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jCheckBoxDBBackup, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelControl.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        add(jPanelControl, gridBagConstraints);

        jPanelDetail.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPanelDetailPropertyChange(evt);
            }
        });
        jPanelDetail.setLayout(new java.awt.CardLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        add(jPanelDetail, gridBagConstraints);

        jPanelButtom.setLayout(new java.awt.GridBagLayout());

        jButtonBackToMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/standardReport/images/Back.gif"))); // NOI18N
        jButtonBackToMain.setText("Back");
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
        jPanelButtom.add(jButtonBackToMain, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        add(jPanelButtom, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelDetailPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanelDetailPropertyChange
// TODO add your handling code here:
    }//GEN-LAST:event_jPanelDetailPropertyChange

    private void jButtonBackToMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackToMainActionPerformed
        backToMainReport();
    }//GEN-LAST:event_jButtonBackToMainActionPerformed

    private void dateComboBoxEndDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxEndDateActionPerformed
        thePanelReport115Group3_2549.startDate = dateComboBoxStartDate.getText();
        thePanelReport115Group3_2549.endDate = dateComboBoxEndDate.getText();
        checkQueryFinishDate();
    }//GEN-LAST:event_dateComboBoxEndDateActionPerformed

    private void dateComboBoxStartDateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxStartDateFocusLost
 
    }//GEN-LAST:event_dateComboBoxStartDateFocusLost

    private void dateComboBoxStartDateInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_dateComboBoxStartDateInputMethodTextChanged

    }//GEN-LAST:event_dateComboBoxStartDateInputMethodTextChanged

    private void dateComboBoxStartDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxStartDateActionPerformed
     //   checkQueryStartDate();
        thePanelReport115Group3_2549.startDate = dateComboBoxStartDate.getText();
        thePanelReport115Group3_2549.endDate = dateComboBoxEndDate.getText();
    }//GEN-LAST:event_dateComboBoxStartDateActionPerformed

    private void jComboBoxTreatStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTreatStatusActionPerformed
        this.treatStatus = this.theComboboxModel.getCodeComboBox(this.jComboBoxTreatStatus);
        System.out.println("TreatStatus :" + this.treatStatus);
    }//GEN-LAST:event_jComboBoxTreatStatusActionPerformed

    private void jComboBoxReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxReportActionPerformed
        
        Report report = (Report)Constant.Report.get( String.valueOf(jComboBoxReport.getSelectedIndex() +1) );
        this.cardname =report.ENG_NAME ;
        if(this.thePanelReport506FollowPatient != null)
        {
            if(this.cardname.equalsIgnoreCase(this.thePanelReport506FollowPatient.getCardName()))
            { 
                this.setVisibleTreatStatus(true);
            }
            else
            {
                this.setVisibleTreatStatus(false);
            }
        }
        else
        {
            this.setVisibleTreatStatus(false);
        }
        report = null;
        this.showCardReport();
    }//GEN-LAST:event_jComboBoxReportActionPerformed
    
    private void jButtonViewActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonViewActionPerformed
    {//GEN-HEADEREND:event_jButtonViewActionPerformed
        this.setDataForQueryReport();
       // this.jButtonSave.setEnabled(true);
    }//GEN-LAST:event_jButtonViewActionPerformed
    
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonSaveActionPerformed
    {//GEN-HEADEREND:event_jButtonSaveActionPerformed
        if(this.setPathFileToSave())
        {
            this.setDataForSaveReport();
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintActionPerformed
            if(cardname.equals(thePanelReport115Group1_2549.getCardName())) {
                thePanelReport115Group1_2549.chooseAll(dateComboBoxStartDate.getText(),dateComboBoxEndDate.getText());
            } else if (cardname.equals(thePanelReport115Group2_2549.getCardName())) {
                thePanelReport115Group2_2549.chooseAll(dateComboBoxStartDate.getText(),dateComboBoxEndDate.getText());
            } else if (cardname.equals(thePanelReport115Group3_2549.getCardName())) {
                thePanelReport115Group3_2549.chooseAll(dateComboBoxStartDate.getText(),dateComboBoxEndDate.getText());
            } else if (cardname.equals(thePanelReport115Group4_2549.getCardName())) {
                thePanelReport115Group4_2549.chooseAll(dateComboBoxStartDate.getText(),dateComboBoxEndDate.getText());
            } else if (cardname.equals(thePanelReport506FollowPatient.getCardName())) {
                thePanelReport506FollowPatient.chooseAll();
            } else {
                JOptionPane.showMessageDialog(this, Gutil.getTextBundle("รายงานที่เลือกยังไม่รองรับการพิมพ์"), "Warning", JOptionPane.WARNING_MESSAGE);
            }
}//GEN-LAST:event_jButtonPrintActionPerformed
    /**ใช้ในการกลับไปยังหน้า MainReport*/
    public void  backToMainReport()
    {
        theHosManage.theHosControl.theHosSubject.theShowPanelSelectSubject.notifyCallBackToMainReport();
    }
    
    /**ใข้ในการตรวจสอบ การเปลี่ยนแปลงวันที่
     * ยังใช้ไม่ได้
     */
    public void checkQueryStartDate()
    {
       
        if(pushOption)
        {
            if(checkstartDate)
            {
                checkstartDate = false;
            }
            else
            {
                showMessageWhenChooseDateAfterQuery();
                checkstartDate = true;
            }
        }
   /*     
        if(this.checkstartDate)
        {
            if(pushOption)
            {
                //pushOption = false;
            }
            else
            {
                showMessageWhenChooseDateAfterQuery();
                this.checkstartDate = false;
            }
        }
     */  
    }
    /**ใข้ในการตรวจสอบ การเปลี่ยนแปลงวันที่
     */
    public void checkQueryFinishDate()
    {
       
        if(this.checkfinishDate)
        {   //ตรวจสอบการกดปุ่ม Option ที่อยู่บน Panel
            if(pushOption)
            {
                //pushOption = false;
            }
            else
            {
                showMessageWhenChooseDateAfterQuery();
                this.checkfinishDate = false;
            }
        }
       
    }
    public void setDataForSaveReport()
    {
        if(isBetweenDateFormat())
        {

            if(this.cardname.equalsIgnoreCase(this.thePanelReport504Group.getCardName()))
            {
                this.thePanelReport504Group.setExportFile(this.fileType,this.select_path);
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport505Group.getCardName()))
            {
                this.thePanelReport505Group.setExportFile(this.fileType,this.select_path);
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport506Group.getCardName()))
            {
                this.thePanelReport506Group.setExportFile(this.fileType,this.select_path);
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport504Name.getCardName()))
            {
                this.thePanelReport504Name.setExportFile(this.fileType,this.select_path);
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport505Name.getCardName()))
            {
                this.thePanelReport505Name.setExportFile(this.fileType,this.select_path);
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport506Name.getCardName()))
            {
                this.thePanelReport506Name.setExportFile(this.fileType,this.select_path);
            }           
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport506FollowPatient.getCardName()))
            {
                this.thePanelReport506FollowPatient.setExportFile(this.fileType,this.select_path);
            }  
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport115Group1_2549.getCardName()))
            {
                this.thePanelReport115Group1_2549.setExportFile(this.fileType,this.select_path);
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport115Group2_2549.getCardName()))
            {
                this.thePanelReport115Group2_2549.setExportFile(this.fileType,this.select_path);
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport115Group3_2549.getCardName()))
            {
                this.thePanelReport115Group3_2549.setExportFile(this.fileType,this.select_path);
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport115Group4_2549.getCardName()))
            {
                this.thePanelReport115Group4_2549.setExportFile(this.fileType,this.select_path);
            }
            
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
            pushOption = true;
            checkstartDate = false;
            checkfinishDate = true;
            if(this.cardname.equalsIgnoreCase(this.thePanelReport504Group.getCardName()))
            {
                this.thePanelReport504Group.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport505Group.getCardName()))
            {
                this.thePanelReport505Group.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport506Group.getCardName()))
            {
                this.thePanelReport506Group.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport504Name.getCardName()))
            {
                this.thePanelReport504Name.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport505Name.getCardName()))
            {
                this.thePanelReport505Name.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport506Name.getCardName()))
            {
                this.thePanelReport506Name.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport506FollowPatient.getCardName()))
            {
                if(this.treatStatus.trim().equalsIgnoreCase("0"))
                {
                    this.thePanelReport506FollowPatient.setQueryReport(dateComboBoxStartDate.getText()
                            ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
                    System.out.println("--- treatStatus IF : " + this.treatStatus);
                }
                else
                {
                    System.out.println("--- treatStatus ELSE : " + this.treatStatus);
                    this.thePanelReport506FollowPatient.setQueryReport(dateComboBoxStartDate.getText()
                            ,dateComboBoxEndDate.getText()
                            , this.treatStatus
                        ,jCheckBoxDBBackup.isSelected());
                }
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport115Group1_2549.getCardName()))
            {
                this.thePanelReport115Group1_2549.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport115Group2_2549.getCardName()))
            {
                this.thePanelReport115Group2_2549.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport115Group3_2549.getCardName()))
            {
                this.thePanelReport115Group3_2549.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
            else if(this.cardname.equalsIgnoreCase(this.thePanelReport115Group4_2549.getCardName()))
            {
                this.thePanelReport115Group4_2549.setQueryReport(dateComboBoxStartDate.getText()
                        ,dateComboBoxEndDate.getText()
                        ,jCheckBoxDBBackup.isSelected());
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this,Gutil.getTextBundle("TimeWarning"),"Warning",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void showCardReport()
    {
        if(thePanelReport506FollowPatient != null && !this.cardname.equalsIgnoreCase(thePanelReport506FollowPatient.getCardName()))
        {
            setGuiForShowReport(320,60);
        }
        else
        {
            setGuiForShowReport(320,80);
        }
        // setEnableSaveToFile(true);
        if(!this.cardname.equalsIgnoreCase(cardname_old))
        {
            this.cardname_old = this.cardname;
            setEnableSaveToFile(false);
        }
        
        this.theHosControl.theHosSubject.theAllPanelSubject.notifySetInitAllGUI();
        
        showCardlayOut(this.cardname);
        
        // ตรวจสอบข้อมูลก่อนการ Query
       // checkDataPreQueryReport();
    }
    
    /**
     * ตรวจสอบข้อมูลก่อนการ Query โดยแสดงแค่ข้อมูลเตือนสำหรับรายการ Setup ที่ไม่มีข้อมูลเท่านั้น
     **/
   /* private void checkDataPreQueryReport()
    {
        if(this.cardname.equalsIgnoreCase(this.thePanelReport115Group3_2549.getCardName()))
        {
            //this.theHosManage.theHosControl.theRP115Group3_2549Control
        }
    }
    */
    private void showCardlayOut(String name)
    {
        CardLayout layout = (CardLayout)this.jPanelDetail.getLayout();
        layout.show(this.jPanelDetail, name);
    }
    
    public void setEnableSaveToFile(boolean isEnable)
    {
        jComboBoxType.setEnabled(isEnable);
        jButtonSave.setEnabled(isEnable);
    }
    
    public void setEnableGUI(boolean isEnable)
    {
        jComboBoxReport.setEnabled(isEnable);
        // jComboBoxType.setEnabled(isEnable);
        setEnableComboBoxDate(isEnable);
        // jButtonSave.setEnabled(isEnable);
    }
    
    private void setEnableComboBoxDate(boolean isEnable)
    {
        dateComboBoxStartDate.setEnabled(isEnable);
        dateComboBoxEndDate.setEnabled(isEnable);
    }
    
    private boolean isBetweenDateFormat()
    {
        if(theHosControl == null)
        {
            System.out.println("-------isBetweenDateFormat---HosControl --null-------------");
        }
        setBetweenDate(dateComboBoxStartDate.getText(),dateComboBoxEndDate.getText());
        if(startDate.compareTo(endDate) <= 0)
        {
            isSelectBetweenDate = true;
        }
        else
        {
            isSelectBetweenDate = false;
        }
        return isSelectBetweenDate;
    }
    
    private  void setBetweenDate(String startDate,String endDate)
    {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public boolean setPathFileToSave()
    {
        boolean isSetPathFileDone = false;
        
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(oldPath));
        chooser.setDialogTitle(Gutil.getTextBundle("PleaseSelectPathForSave"));
        chooser.setApproveButtonText("Save");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
        {
            System.out.println("getCurrentDirectory(): " +  chooser.getCurrentDirectory());
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
            
            String checkKeyFiles = chooser.getSelectedFile().toString();
            int lengthDirectory = chooser.getCurrentDirectory().toString().length();
            int lengthAll = chooser.getSelectedFile().toString().length();
            
            if(lengthAll > lengthDirectory)
            {
//                int checkExport = JOptionPane.showConfirmDialog(this,"คุณไม่สามารถกำหนดชื่อ File " + checkKeyFiles.substring(lengthDirectory+1) + " ได้ และ Diractory หรือ File ที่คุณกำหนดไม่มีอยู่ แต่โปรแกรมจะบันทึกข้อมูลให้คุณใน Path " + oldPath + " แทน คุณต้องการที่จะดำเนินการบันทึกต่อหรือใหม่","Warning",JOptionPane.YES_NO_OPTION);
//                System.out.println("OJIKA CHECK EXPORT : " + checkExport);
//                System.out.println("OJIKATEST : " + checkKeyFiles.substring(lengthDirectory));
                oldPath = chooser.getSelectedFile().toString();
                select_path =  chooser.getSelectedFile().toString()+"\\";
            }
            else{
                oldPath = chooser.getCurrentDirectory().toString();
                select_path =  chooser.getCurrentDirectory().toString()+"\\";
            }
            isSetPathFileDone = true;
        }
        else
        {
            isSetPathFileDone = false;
        }
        return isSetPathFileDone;
    }
    
    private void setFileType(String fileType)
    {
        this.fileType = fileType;
    }
    
    public static void setFrame(int width ,int height)
    {
        PanelReportFile thePanelReport18File= new PanelReportFile(null);
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
        jLabelReport.setText(Gutil.getTextBundle(jLabelReport.getText()));
        jLabelTreatStatus.setText(Gutil.getTextBundle(jLabelTreatStatus.getText()));
        jLabelStartDate.setText(Gutil.getTextBundle(jLabelStartDate.getText()));
        jLabelType.setText(Gutil.getTextBundle(jLabelType.getText()));
        jLabelEndDate.setText(Gutil.getTextBundle(jLabelEndDate.getText()));
        jButtonSave.setText(Gutil.getTextBundle(jButtonSave.getText()));
        jButtonView.setText(Gutil.getTextBundle(jButtonView.getText()));
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
    
    public void notifyShowSaveToFile(boolean show)
    {
        setEnableSaveToFile(show);
        //  setEnableComboBoxDate(show);
    }
    
    /**
     *  ใช้ในการแสดง Message ให้ผู้ใช้ทราบ เรื่อง หากต้องการเปลี่ยนช่วงวันที่ในการดึงรายงานกรุณากดปุ่ม query ใหม่ด้วยค่ะ
     */
    private void showMessageWhenChooseDateAfterQuery()
    {
        JOptionPane.showMessageDialog(this,Gutil.getTextBundle("PleasePushQueryWhenUseNewData"),"Warning",JOptionPane.OK_OPTION);
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
        //pushOption = true;
        checkstartDate = true;
        
//        setDateCombobox(startDate, finishDate);
        
        
    }
    /**ใช้ในการ แสดงรายงานใน Combobox ตาม param ที่ส่งเข้ามา
     *  @param report เป็น Object ของ Report
     */
    private void setShowReport(Report report)
    {
        jComboBoxReport.setSelectedItem(report.THAI_NAME);
    }
    public void notifyCallBackToMainReport()
    {
    }
    
    public void notifyCallReportShow(Report report)
    {
        setShowReport(report);
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.standardReport.utility.DateComboBox dateComboBoxEndDate;
    private com.standardReport.utility.DateComboBox dateComboBoxStartDate;
    private javax.swing.JButton jButtonBackToMain;
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonView;
    private javax.swing.JCheckBox jCheckBoxDBBackup;
    private javax.swing.JComboBox jComboBoxReport;
    private javax.swing.JComboBox jComboBoxTreatStatus;
    private javax.swing.JComboBox jComboBoxType;
    private javax.swing.JLabel jLabelEndDate;
    private javax.swing.JLabel jLabelReport;
    private javax.swing.JLabel jLabelStartDate;
    private javax.swing.JLabel jLabelTreatStatus;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelButtom;
    private javax.swing.JPanel jPanelControl;
    private javax.swing.JPanel jPanelDetail;
    // End of variables declaration//GEN-END:variables
    
}
