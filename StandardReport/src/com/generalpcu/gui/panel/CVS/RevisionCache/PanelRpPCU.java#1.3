package com.generalpcu.gui.panel;
/* 
 * PanelReport18File.java
 *
 */
import com.generalpcu.control.*;
import com.generalpcu.utility.ReportPcuName; 
import com.hospital_os.usecase.connection.UpdateStatus;
import com.hosv3.utility.Config;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author  tong(padungrat)
 */

public class PanelRpPCU extends javax.swing.JPanel   {

    public String cur_path = null;
    private DefaultTableCellRenderer rendererCenter;
    private String select_path = "";
    private String startDate ="";
    private String endDate = "";
    private JFileChooser chooser;
    private UpdateStatus theUS;
    private String oldPath = "";
    RpPcuControl theHosControl;

    public PanelRpPCU() {
        initComponents();
    }
    public void setControl(RpPcuControl rc){
        theHosControl = rc;
        rendererCenter = new DefaultTableCellRenderer();
        setTableListReport();
        cur_path = System.getProperty("user.dir");
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPaneReport = new javax.swing.JScrollPane();
        jTableReport = new com.hosv3.gui.component.HJTableSort();
        jPanel1 = new javax.swing.JPanel();
        jLabelEndDate = new javax.swing.JLabel();
        jLabelStartDate = new javax.swing.JLabel();
        jButtonPrint = new javax.swing.JButton();
        dateComboBoxStartDate = new com.hospital_os.utility.DateComboBox();
        dateComboBoxEndDate = new com.hospital_os.utility.DateComboBox();
        jCheckBoxDbBackup = new javax.swing.JCheckBox();

        setLayout(new java.awt.GridBagLayout());

        jTableReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableReportMouseReleased(evt);
            }
        });
        jScrollPaneReport.setViewportView(jTableReport);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jScrollPaneReport, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabelEndDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEndDate.setText("ถึงวันที่ "); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanel1.add(jLabelEndDate, gridBagConstraints);

        jLabelStartDate.setText("จากวันที่ "); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabelStartDate, gridBagConstraints);

        jButtonPrint.setFont(new java.awt.Font("Dialog", 0, 18));
        jButtonPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/print.gif"))); // NOI18N
        jButtonPrint.setText("พิมพ์"); // NOI18N
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel1.add(jButtonPrint, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(dateComboBoxStartDate, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(dateComboBoxEndDate, gridBagConstraints);

        jCheckBoxDbBackup.setText("ต้องการดึงรายงานจากฐานสำรอง");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jCheckBoxDbBackup, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void dateComboBoxStartDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxStartDateActionPerformed

    }//GEN-LAST:event_dateComboBoxStartDateActionPerformed

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintActionPerformed
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        if(jCheckBoxDbBackup.isSelected()){
            theHosControl.theConnectionInf = Config.getConnectionInfDBBackupFromFile(theHosControl.theHC.theUS.getJFrame());
        }else{
            theHosControl.theConnectionInf = theHosControl.theHC.theConnectionInf;
        }
        theHosControl.printReport(dateComboBoxStartDate.getText()
                , dateComboBoxEndDate.getText()
                , this.jTableReport.getSelectedRow());
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jButtonPrintActionPerformed

    private void jTableReportMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableReportMouseReleased
                // TODO add your handling code here:
    }//GEN-LAST:event_jTableReportMouseReleased
/*
    private void dateComboBoxStartDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxDate1ActionPerformed

}//GEN-LAST:event_dateComboBoxDate1ActionPerformed
*/
    private void dateComboBoxStartDateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxStartDateFocusLost

}//GEN-LAST:event_dateComboBoxStartDateFocusLost

    private void dateComboBoxEndDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxEndDateActionPerformed

}//GEN-LAST:event_dateComboBoxEndDateActionPerformed

    private void dateComboBoxEndDateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxEndDateFocusLost

}//GEN-LAST:event_dateComboBoxEndDateFocusLost

   private void setTableListReport() {
       this.jTableReport.setModel(new DefaultTableModel(ReportPcuName.RP_PCU,ReportPcuName.RP_HEADER));
        rendererCenter.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        jTableReport.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTableReport.getColumnModel().getColumn(0).setCellRenderer(rendererCenter);
        jTableReport.getColumnModel().getColumn(1).setPreferredWidth(750);
        jTableReport.getColumnModel().getColumn(2).setPreferredWidth(150);
    } 

    /**
     */

    public boolean setPathFileToSave(){

        boolean isSetPathFileDone = false;
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(oldPath));
        chooser.setDialogTitle( "ChoosePathToSave" );

        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setApproveButtonText("Save");
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            oldPath = chooser.getSelectedFile().toString();
            select_path =  chooser.getSelectedFile().toString()+"/";
            isSetPathFileDone = true;
        } else {
            isSetPathFileDone = false;
        }
        return isSetPathFileDone;
    }
    /**
     *  @param isShowList
     *  @param isSelectAll
     *
     */
    public static void main(String args[]){

        PanelRpPCU pn = new PanelRpPCU();
        JFrame frm = new JFrame();
        frm.getContentPane().add(pn);
        frm.pack();
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
    }

    public void setUpdateStatus(UpdateStatus us) {
        theUS = us;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.hospital_os.utility.DateComboBox dateComboBoxEndDate;
    private com.hospital_os.utility.DateComboBox dateComboBoxStartDate;
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JCheckBox jCheckBoxDbBackup;
    private javax.swing.JLabel jLabelEndDate;
    private javax.swing.JLabel jLabelStartDate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPaneReport;
    private com.hosv3.gui.component.HJTableSort jTableReport;
    // End of variables declaration//GEN-END:variables

}


