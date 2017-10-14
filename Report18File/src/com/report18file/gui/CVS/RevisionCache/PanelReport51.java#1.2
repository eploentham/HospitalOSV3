package com.report18file.gui;
/*
 * PanelReport18File.java
 *
 * Created on 16 สิงหาคม 2548, 15:40 น.
 */

import com.hospital_os.usecase.connection.ConnectionInf; 

import com.report18file.control.Rp18Control;
import com.report18file.help.ShowHelp18File;
import com.report18file.utility.Report18FileName;
import com.report18file.subject.ManageReport18File;
import com.report18file.utility.CellRendererReport;
import com.report18file.utility.Config;
import com.report18file.utility.Constant;
import com.report18file.utility.Language;
import com.report18file.utility.Report18FileData;
import com.report18file.utility.ShowCommonLine;
import com.reportcenter.utility.TableModelGUI;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
//import com.reportcenter.usecase.connection.*;
import com.hospital_os.object.*;
import com.hosv3.utility.connection.UpdateStatus;
import com.report18file.control.DBSuit;

/**
 *
 * @author  tong(padungrat)
 */
public class PanelReport51 extends javax.swing.JPanel implements ManageReport18File {
    public String cur_path = System.getProperty("user.dir");


    private boolean isSelectBetweenDate = false;
    private boolean isProcessRunning = false;

    /**ใช้ในการจัดการกับ cell ในตารางให้อยู่ตรงกลาง*/
    private DefaultTableCellRenderer rendererCenter = new DefaultTableCellRenderer();
    /**ใช้ในการจัดการกับ cell ในตารางให้เป็นรูป Report*/
    private CellRendererReport cellRendererReport  = new CellRendererReport();
    /**เป็น Object ของ 12 แฟ้ม ที่ระบุว่าต้องการจากให้ save เป็นไฟล์ไหนบ้าง*/
    private Report18FileName theReport18FileName;
    /**เป็นตัวกำหนด path ของไฟล์*/
    private String select_path = "";
    /**เป็นตัวกำหนด ให้ save ออกมาเป็นไฟล์ชนิดไหน*/

    /**เป็นตัวกำหนด วันที่เริ่มค้นหา*/
    private String startDate ="";
    /**เป็นตัวกำหนด วันที่สิ้นสุดของการค้นหา*/
    private String endDate = "";
    /**เป็นตัวกำหนด Pathเริ่มต้น*/
    private String oldPath = ".";
    /**เป็นการแสดง Dialog เลือกไฟล์ที่ต้องการจะ บันทึก*/
    private JFileChooser chooser;

    private String[] col;
    /**เป็นตัวกำหนดภาษา*/
    private int language =1; 


    private ShowHelp18File theShowHelp18File;

    //private Rp18Control theHosControl;
    private Rp18Control theHosControl;

    UpdateStatus theUS;

    private javax.swing.JMenuItem jMenuMapClinic = new javax.swing.JMenuItem();
    ConnectionInf theConnectionInf;
    int index;
    DBSuit dbs;
    private CardLayout card;
    private Container theParent;
    private String main_index;

    public PanelReport51() {
        initComponents();
        setLanguage(); 
        setTableListReport();
    }
    public void setContainer(Container cont,String main){
        theParent = cont;
        main_index = main;
        card = (CardLayout)theParent.getLayout();
    }
    /**ใช้ในการกำหนด version ของ การออกรายงาน 12 แฟ้ม
     *@return เป็น Object ของ Vesion
     */
    public Version getVersion() {
        Version version = new Version();
        version.app_code = Config.getTextBundle("Version");
        version.description = Config.getTextBundle("Description");
        version.update_time = Config.getTextBundle("Update");
        version.db_code = Config.getTextBundle("VersionDatabaseUse");
        version.version_id = "help12.ShowHelp18File";
        return version; 
    }

    /**ใช้ในการ ส่ง Dialog Help ให้กับ Frame หลัก  */
    public Vector getMenuItem() {
        Vector vc = new Vector();
        javax.swing.JMenuItem jMenuItem1 = new javax.swing.JMenuItem();
        theShowHelp18File = new ShowHelp18File();
        theShowHelp18File.addID(jMenuItem1);

        jMenuItem1.setText(Language.getTextBundle("Report18FileHelp",language));
        MenuItemPlugIn theMenuItemPlugIn = new MenuItemPlugIn();
        theMenuItemPlugIn.authen = true;
        theMenuItemPlugIn.theJMenuItem = jMenuItem1;
        vc.add(theMenuItemPlugIn);
        return vc;
    }

    /**
     *  ใช้ในการกำหนด HosManage ให้กับ Panel นี้ หลังจาก panel นี้มีการ new Instance
     *  ขึ้นมาแล้ว ถ้ามีการส่งมาจะ Enable panel นี้ให้ทำงาน
     *  @param theHosManage เป็น Object ของ HosManage
     */
    public void setConnection(Rp18Control rc,UpdateStatus us){
        this.theHosControl = rc;
        theConnectionInf = rc.theConnectionInf;
        this.theHosControl.theGUISubject.registerGUI(this);
        ShowCommonLine.show("--- Pass set connection in report 12 files",false);
        this.setEnableGUI(true);
        index = Rp18Control.STD_GOV;
        dbs = DBSuitS.getDBSuit(theUS,theConnectionInf);
        theUS = us;
//            index = Rp18Control.STD_NHSO;
//            dbs = DBSuitNh.getDBSuit(theUS,theConnectionInf);
//            index = Rp18Control.STD_PP;
//            dbs = DBSuitPp.getDBSuit(theUS,theConnectionInf);
//            index = Rp18Control.STD_NHSO;
//            dbs = DBSuitNh53.getDBSuit(theUS,theConnectionInf);
//            index = Rp18Control.STD_NHSO53;
    }

    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanelControl = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        dateComboBoxStartDate = new com.reportcenter.utility.DateComboBox();
        jLabelEndDate = new javax.swing.JLabel();
        dateComboBoxEndDate = new com.reportcenter.utility.DateComboBox();
        jCheckBoxSelectAll = new javax.swing.JCheckBox();
        jLabelStartDate = new javax.swing.JLabel();
        jButtonOK = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jLabelType = new javax.swing.JLabel();
        jRadioButtonDbf = new javax.swing.JRadioButton();
        jRadioButtonTxt = new javax.swing.JRadioButton();
        jRadioButtonDD = new javax.swing.JRadioButton();
        jRadioButtonAll = new javax.swing.JRadioButton();
        jRadioButtonNDD = new javax.swing.JRadioButton();
        jLabelType1 = new javax.swing.JLabel();
        jButtonOK1 = new javax.swing.JButton();
        jPanelDetail = new javax.swing.JPanel();
        jScrollPaneReport = new javax.swing.JScrollPane();
        jTableReport = new javax.swing.JTable();

        setLayout(new java.awt.GridBagLayout());

        jPanelControl.setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        dateComboBoxStartDate.setMinimumSize(new java.awt.Dimension(119, 28));
        dateComboBoxStartDate.setPreferredSize(new java.awt.Dimension(119, 28));
        dateComboBoxStartDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxStartDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(dateComboBoxStartDate, gridBagConstraints);

        jLabelEndDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEndDate.setText("ถึงวันที่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jLabelEndDate, gridBagConstraints);

        dateComboBoxEndDate.setMinimumSize(new java.awt.Dimension(119, 28));
        dateComboBoxEndDate.setPreferredSize(new java.awt.Dimension(119, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(dateComboBoxEndDate, gridBagConstraints);

        jCheckBoxSelectAll.setText("เลือกทั้งหมด");
        jCheckBoxSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSelectAllActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 4, 0);
        jPanel1.add(jCheckBoxSelectAll, gridBagConstraints);

        jLabelStartDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/reportcenter/images/process.gif"))); // NOI18N
        jLabelStartDate.setText("จากวันที่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jLabelStartDate, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 7, 5);
        jPanelControl.add(jPanel1, gridBagConstraints);

        jButtonOK.setFont(new java.awt.Font("Dialog", 0, 18));
        jButtonOK.setText("ตกลง");
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 2);
        jPanelControl.add(jButtonOK, gridBagConstraints);

        jButtonCancel.setFont(new java.awt.Font("Dialog", 0, 18));
        jButtonCancel.setText("ยกเลิก");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        jPanelControl.add(jButtonCancel, gridBagConstraints);

        jLabelType.setText("ชนิดของไฟล์");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanelControl.add(jLabelType, gridBagConstraints);

        buttonGroup2.add(jRadioButtonDbf);
        jRadioButtonDbf.setSelected(true);
        jRadioButtonDbf.setText("DBF ไฟล์");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelControl.add(jRadioButtonDbf, gridBagConstraints);

        buttonGroup2.add(jRadioButtonTxt);
        jRadioButtonTxt.setText("TXT ไฟล์");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelControl.add(jRadioButtonTxt, gridBagConstraints);

        buttonGroup1.add(jRadioButtonDD);
        jRadioButtonDD.setSelected(true);
        jRadioButtonDD.setText("ตาม Datadict");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelControl.add(jRadioButtonDD, gridBagConstraints);

        buttonGroup1.add(jRadioButtonAll);
        jRadioButtonAll.setText("ทั้งหมด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelControl.add(jRadioButtonAll, gridBagConstraints);

        buttonGroup1.add(jRadioButtonNDD);
        jRadioButtonNDD.setText("เฉพาะที่ไม่ผ่าน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelControl.add(jRadioButtonNDD, gridBagConstraints);

        jLabelType1.setText("ข้อมูลส่งออก");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanelControl.add(jLabelType1, gridBagConstraints);

        jButtonOK1.setFont(new java.awt.Font("Dialog", 0, 18));
        jButtonOK1.setText("กลับ..");
        jButtonOK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOK1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 2);
        jPanelControl.add(jButtonOK1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        add(jPanelControl, gridBagConstraints);

        jPanelDetail.setLayout(new java.awt.GridBagLayout());

        jTableReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableReportMouseReleased(evt);
            }
        });
        jScrollPaneReport.setViewportView(jTableReport);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelDetail.add(jScrollPaneReport, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 11, 11);
        add(jPanelDetail, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSelectAllActionPerformed
        int length = this.jTableReport.getRowCount();
        if(jCheckBoxSelectAll.isSelected())
            this.jTableReport.setRowSelectionInterval(0,length-1);
        else
            this.jTableReport.clearSelection();
    }//GEN-LAST:event_jCheckBoxSelectAllActionPerformed


    private void dateComboBoxStartDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxStartDateActionPerformed

//        Calendar c = Calendar.getInstance();
//        c.setTime(DateUtil.getDateFromText(dateComboBoxStartDate.getText()));
//        int month = c.get(Calendar.MONTH) + 1;
//        c.set(Calendar.MONTH,month);
//        String date_str = DateUtil.getTextDB(c.getTime(),false);
//        this.dateComboBoxEndDate.setText(DateUtil.getGuiDateTime(date_str));
    }//GEN-LAST:event_dateComboBoxStartDateActionPerformed

            

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        cancelProcess();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        queryReport();
    }//GEN-LAST:event_jButtonOKActionPerformed

    private void jTableReportMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableReportMouseReleased

    }//GEN-LAST:event_jTableReportMouseReleased

    private void jButtonOK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOK1ActionPerformed
        card.show(theParent,main_index);
    }//GEN-LAST:event_jButtonOK1ActionPerformed

    private void setTableListReport() {
        TableModelGUI tm= new TableModelGUI(col,Report18FileData.reportName.length);
        for(int i=0 ;i<Report18FileData.reportName.length; i++) {
            tm.setValueAt(String.valueOf(i+1),i,0);
            tm.setValueAt(Report18FileData.reportName[i],i,1);
            tm.setValueAt(Report18FileData.reportExplain[i],i,2);
        }
        jTableReport.setModel(tm);
        rendererCenter.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        jTableReport.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTableReport.getColumnModel().getColumn(0).setCellRenderer(rendererCenter);
        jTableReport.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableReport.getColumnModel().getColumn(2).setPreferredWidth(750);
    }
    /**
     *  ใช้ในการ เริ่มต้นในการค้นหารายงานและบันทึกลงไฟล์ที่ระบุ
     */

    public void queryReport() 
    {
        System.out.println("public void queryReport()");
        /**ตรวจสอบวันที่อยู่ในช่วงจริงหรือไม่*/
        if(!isBetweenDateFormat()) {
            //กรุณาตรวจสอบวันที่ออกรายงานอีกครั้ง
            JOptionPane.showMessageDialog(this,Language.getTextBundle("CheckDateForReport",language)
                ,Language.getTextBundle("Warning",language),JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(this.jTableReport.getSelectedRows().length==0){
            theUS.setStatus("กรุณาเลือกรายงานที่ต้องการออก",UpdateStatus.WARNING);
            return;
        }
        ShowCommonLine.show("in isBetweenDateFormat()",false);
        if(!setPathFileToSave()) {
            theUS.setStatus("กรุณากำหนดตำแหน่งที่เก็บไฟล์รายงาน",UpdateStatus.WARNING);
            return;
        }
        setEnableGUI(false);
        jButtonCancel.setEnabled(true);
        isProcessRunning = true;
        ShowCommonLine.show("----- in setPathFileToSave()",false);
        /**เริ่มต้นของการค้นหาข้อมูล และบันทึกลงไฟล์*/
        int export = 1;

        if(this.jRadioButtonAll.isSelected()) export = Rp18Control.EXP_ALL;
        else if(this.jRadioButtonDD.isSelected()) export = Rp18Control.EXP_PASS;
        else if(this.jRadioButtonNDD.isSelected()) export = Rp18Control.EXP_FAIL;

        try {
            theHosControl.setDataExport18File(getReport18FileName()
                , dateComboBoxStartDate.getText()
                , dateComboBoxEndDate.getText()
                , select_path
                , this.jRadioButtonDbf.isSelected()?"0":"1"
                , export
                , index
                , dbs);
            this.theHosControl.start();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    /**ใช้ในการ หยุดการทำงานของ โปรแกรม ถ้าโปรแกมทำงานในขณะนั้น จะแสดง Dialog เตือน
     *
     */
    public void cancelProcess() {   /**ตรวจสอบสถานะของการทำงาน*/
        if(isProcessRunning){
            /**ถามความยืนยัน*/
            int choose  = JOptionPane.showConfirmDialog(this,Language.getTextBundle("ConformStopProcess",language),Language.getTextBundle("Warning",language),JOptionPane.OK_CANCEL_OPTION);
            /**ตรวจสอบการยืนยัน*/
            if(choose == JOptionPane.OK_OPTION && this.theHosControl != null) {
                /**หยุดการทำงานของโปรแกรม*/
                this.theHosControl.stop();
                this.setEnableGUI(true);
            }
        }

    }



    /**
     *  ใช้ในการกำหนดให้ Panel นี้สามารถทำงานได้หรือไม่
     *  @param isEnable เป็น boolean ถ้าเป็น true ระบุให้สามารถทำงานได้
     * , false ระบุให้ไม่สามารถทำงานได้
     */
    public void setEnableGUI(boolean isEnable){
        dateComboBoxStartDate.setEnabled(isEnable);
        dateComboBoxEndDate.setEnabled(isEnable);
        jCheckBoxSelectAll.setEnabled(isEnable);
        jButtonOK.setEnabled(isEnable);
        jTableReport.setEnabled(isEnable);
        jScrollPaneReport.setEnabled(isEnable);
        this.jRadioButtonAll.setEnabled(isEnable);
        this.jRadioButtonDD.setEnabled(isEnable);
        this.jRadioButtonDbf.setEnabled(isEnable);
        this.jRadioButtonNDD.setEnabled(isEnable);
        this.jRadioButtonTxt.setEnabled(isEnable);
    }


    /**ใช้ในการให้ค่าที่เลือกบนตาราง ว่าเลือกรายงานอะไรบ้าง
     *  @return เป็น Report18FileName ซึ่งจะเก็บ ข้อมูลที่บอกว่ารายงานแต่ละตัวต้องไป query หรือไม่
     */
    private Report18FileName getReport18FileName(){
        System.out.println("private Report18FileName getReport18FileName(){");
        theReport18FileName = new Report18FileName();
        int[] rows = jTableReport.getSelectedRows();
//        System.out.println("row[i] = " + rows.length);

        for(int i=0;i<rows.length;i++){
            if(rows[i]==0){
                theReport18FileName.Person = Constant.SELECTED;
            }else if(rows[i]==1){
                theReport18FileName.Death = Constant.SELECTED;
            }else if(rows[i]==2){
                theReport18FileName.Chronic = Constant.SELECTED;
            }else if(rows[i]==3){
                theReport18FileName.Card= Constant.SELECTED;
            }else if(rows[i]==4){
                theReport18FileName.Service = Constant.SELECTED;
            }else if(rows[i]==5){
                theReport18FileName.Diag = Constant.SELECTED;
            }else if(rows[i]==6){
                theReport18FileName.Appoint = Constant.SELECTED;
            }else if(rows[i]==7){
                theReport18FileName.Serveil = Constant.SELECTED;
            }else if(rows[i]==8){
                theReport18FileName.Drug = Constant.SELECTED;
            }else if(rows[i]==9){
                theReport18FileName.Proced = Constant.SELECTED;
            }else if(rows[i]==10){
                theReport18FileName.Woman = Constant.SELECTED;
            }else if(rows[i]==11){
                theReport18FileName.Fp = Constant.SELECTED;
            }else if(rows[i]==12){
                theReport18FileName.Epi = Constant.SELECTED;
            }else if(rows[i]==13){
                theReport18FileName.Nutrition = Constant.SELECTED;
            }else if(rows[i]==14){
                theReport18FileName.Anc = Constant.SELECTED;
            }else if(rows[i]==15){
                theReport18FileName.Pp = Constant.SELECTED;
            }else if(rows[i]==16){
                theReport18FileName.Mch = Constant.SELECTED;
            }else if(rows[i]==17){
                theReport18FileName.Home = Constant.SELECTED;
            }
        }
        return theReport18FileName;
    }

    /**
     *  ใช้ในการตรวจสอบ วันที่เริ่มต้น กับวันที่สิ้นสุดเป็นวันเดียวกันหรือไม่ ถ้าเป็นวันเดียวกัน
     *  ให้ return เป็น false ถ้าไม่ใช้ให้ return เป็น true
     *  @return boolean true ไม่ซ้ำ false ซ้ำ
     */
    private boolean isBetweenDateFormat(){
        /**กำหนดค่าวันที่*/
        System.out.print("check gettext1");
        this.startDate = dateComboBoxStartDate.getText();
        this.endDate = dateComboBoxEndDate.getText();
        System.out.print("check gettext2");

        /**ตรวจสอบวันที่เริ่มต้นเป็นวันเดียวกันกับวันที่สิ้นสุดหรือไม่*/
        if(startDate.compareTo(endDate) <= 0){
            isSelectBetweenDate = true;
        }else{
            isSelectBetweenDate = false;
        }
        return isSelectBetweenDate;
    }

    /**ใช้ในการเลือก Folder ที่ต้องการจะบันทึกไฟล์
     *@return เป็น boolean
     */
    public boolean setPathFileToSave(){
        boolean isSetPathFileDone = false;
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(oldPath));


        //กรุณาเลือก path ไฟล์ที่ต้องการบันทึก
        chooser.setDialogTitle(Language.getTextBundle("ChoosePathToSave",language) );
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setApproveButtonText("Save");

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            ShowCommonLine.show("getCurrentDirectory(): " +  chooser.getCurrentDirectory(),false);

            /**เก็บ path เก่าเอาไว้*/
            oldPath = chooser.getSelectedFile().toString();
            /**เพิ่ม path ใหม่เข้าไป*/
            select_path =  chooser.getSelectedFile().toString()+"/";
            isSetPathFileDone = true;
        } else {
            isSetPathFileDone = false;
        }
        return isSetPathFileDone;
    }

    /**
     *  ใช้ในการแสดงข้อมูลบนตาราง
     *  @param isShowList
     *  @param isSelectAll
     *
     */





    public static void main(String args[]){
        try {
            //      UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        PanelReport51 thePanelReport12File= new PanelReport51();
        JFrame frm = new JFrame("PanelReport12File");

        frm.getContentPane().add(thePanelReport12File);
        frm.pack();
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
    }

    /**ใช้ในการแสดงภาษา*/
    private void setLanguage() {

        jLabelType.setText(Language.getTextBundle("SaveType",language));
        jLabelStartDate.setText(Language.getTextBundle("StartDate",language));
        jLabelEndDate.setText(Language.getTextBundle("FinishDate",language));
        jCheckBoxSelectAll.setText(Language.getTextBundle("SelectAll",language));

        jPanelDetail.setBorder(new javax.swing.border.TitledBorder(Language.getTextBundle("DetailReport",language)));

        jButtonOK.setText(Language.getTextBundle("OK",language));
        jButtonCancel.setText(Language.getTextBundle("Cancel",language));

        col = new  String[3];
        col[0] = "no";//Language.getTextBundle("Sequence",language);
        col[1] = "name";//Language.getTextBundle("ReportName",language);
        col[2] = Language.getTextBundle("Description",language);


    }



/**
 *
 *@deprecated henbe unused because old pattern
 * @param setEnabled
 */
    public void setEnabled(boolean setEnabled) {
        this.setEnableGUI(setEnabled);
    }

    public void setUpdateStatus(UpdateStatus us) {
        theUS = us;
    }

    public void onShowStatus(String str, java.awt.Color color) {
        onShowStatus(str,color,false);
    }

    public void onShowStatus(String str, java.awt.Color color, boolean isFinish) {
        if(isFinish)
            theUS.setStatus(str,UpdateStatus.COMPLETE);
        else{
            if(color.equals(Color.RED))
                theUS.setStatus(str,UpdateStatus.ERROR);
            else
                theUS.setStatus(str,UpdateStatus.WARNING);            
        }
    }

    public void onShowPicture(String pic, boolean isVisible) {

    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.reportcenter.utility.DateComboBox dateComboBoxEndDate;
    private com.reportcenter.utility.DateComboBox dateComboBoxStartDate;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JButton jButtonOK1;
    private javax.swing.JCheckBox jCheckBoxSelectAll;
    private javax.swing.JLabel jLabelEndDate;
    private javax.swing.JLabel jLabelStartDate;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JLabel jLabelType1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelControl;
    private javax.swing.JPanel jPanelDetail;
    private javax.swing.JRadioButton jRadioButtonAll;
    private javax.swing.JRadioButton jRadioButtonDD;
    private javax.swing.JRadioButton jRadioButtonDbf;
    private javax.swing.JRadioButton jRadioButtonNDD;
    private javax.swing.JRadioButton jRadioButtonTxt;
    private javax.swing.JScrollPane jScrollPaneReport;
    private javax.swing.JTable jTableReport;
    // End of variables declaration//GEN-END:variables


}
