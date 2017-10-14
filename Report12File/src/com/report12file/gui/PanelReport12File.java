/*
 * PanelReport18File.java
 *
 * Created on 16 �ԧ�Ҥ� 2548, 15:40 �.
 */
package com.report12file.gui; 
import com.report12file.control.ReportControl;
import com.report12file.control.Rp12Control;
import java.util.*;
import javax.swing.*;
/*
import com.lookAndFeel.*;
import com.reportcenter.manage.*;
import com.reportcenter.manage.control.HosControl;
import com.reportcenter.manage.control.convert18files.*;
import com.reportcenter.utility.*;
 */
import javax.swing.table.DefaultTableCellRenderer;
import com.hospital_os.object.*;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hosv3.utility.connection.UpdateStatus;
import com.report12file.control.Rp12Control2;
import com.report12file.utility.*;
import com.report12file.subject.ManageReport12File;
import com.report12file.help.ShowHelp12File;
/**
 *
 *
 * //henbe comment 100253 kong �ҵðҹ 54 �Ҩҡ�˹�õ�駪������Ƿ�������ժش Object
 * @author  tong(padungrat)
 *
 */
public class PanelReport12File extends javax.swing.JPanel implements ManageReport12File {
    public String cur_path = System.getProperty("user.dir");
    private boolean isSelectBetweenDate = false;
    private boolean isProcessRunning = false;
    /**��㹡�èѴ��áѺ cell 㹵��ҧ�������ç��ҧ*/
    private DefaultTableCellRenderer rendererCenter = new DefaultTableCellRenderer();
    /**��㹡�èѴ��áѺ cell 㹵��ҧ������ٻ Report*/
    private CellRendererReport cellRendererReport  = new CellRendererReport();
    /**�� Object �ͧ 12 ��� ����к���ҵ�ͧ��èҡ��� save ������˹��ҧ*/
    private Report12FileName theReport12FileName;
    /**�繵�ǡ�˹� path �ͧ���*/
    private String select_path = "";
    /**�繵�ǡ�˹� ��� save �͡������쪹Դ�˹*/
    private String fileType = "";
    /**�繵�ǡ�˹� �ѹ������������*/
    private String startDate ="";
    /**�繵�ǡ�˹� �ѹ�������ش�ͧ��ä���*/
    private String endDate = "";
    /**�繵�ǡ�˹� Path�������*/
    private String oldPath = ".";
    /**�繡���ʴ� Dialog ���͡������ͧ��è� �ѹ�֡*/
    private JFileChooser chooser;
    private String[] col;
    /**�繵�ǡ�˹�����*/
    private int language =1;
    /**�繵��������Ѻ��Ǩ�ͺ����͡�������͡�������� 0 ��� NHSO , 1 ��� DRG*/
    
    private DialogShowStatus theDialogShowStatus;
    private ShowHelp12File theShowHelp12File;
    private Rp12Control theRpControl;
    private UpdateStatus theUS;
    private Rp12Control2 theRpControl2;
    public PanelReport12File() {
        initComponents();
        setLanguage();
        initComboBox();
        //initConnection();
        setEnableGUI(false);
        setTableListReport3();
    }
    /**��㹡�á�˹� version �ͧ ����͡��§ҹ 12 ���
     *
     *@return �� Object �ͧ Vesion
     *
     */
    public Version getVersion() {
        Version version = new Version();
        version.app_code = Config.getTextBundle("Version");
        version.description = Config.getTextBundle("Description");
        version.update_time = Config.getTextBundle("Update");
        version.db_code = Config.getTextBundle("VersionDatabaseUse");
        version.version_id = "help12.ShowHelp12File";
        return version;
    }
    /**��㹡�� �� Dialog Help ���Ѻ Frame ��ѡ  */
    public Vector getMenuItem() {
        Vector vc = new Vector();
        javax.swing.JMenuItem jMenuItem1 = new javax.swing.JMenuItem();
        theShowHelp12File = new ShowHelp12File();
        theShowHelp12File.addID(jMenuItem1);
        jMenuItem1.setText(Language.getTextBundle("Report12FileHelp",language));
        MenuItemPlugIn theMenuItemPlugIn = new MenuItemPlugIn();
        theMenuItemPlugIn.authen = true;
        theMenuItemPlugIn.theJMenuItem = jMenuItem1;
        vc.add(theMenuItemPlugIn);
        return vc;
    }
    /**
     *
     *  ��㹡�á�˹� HosManage ���Ѻ Panel ��� ��ѧ�ҡ panel ����ա�� new Instance
     *
     *  ��������� ����ա�����Ҩ� Enable panel ������ӧҹ
     *
     *  @param theHosManage �� Object �ͧ HosManage
     *
     */
    public void setConnection(ReportControl rc,Rp12Control rp,Rp12Control2 rp2){
        this.theRpControl = rp;
        this.theRpControl.theGUISubject.registerGUI(this);
        this.theRpControl2 = rp2;
        this.theRpControl2.theGUISubject.registerGUI(this);
        ShowCommonLine.show("--- Pass set connection in report 12 files",false);
        this.setEnableGUI(true);
    }
    /**��㹡�� init ComboBox ����ա����ҹ*/
    public void initComboBox(){
//        for(int j=0;j<Constant.REPORT_TYPE.length;j++){
//            jComboBoxType.addItem(Constant.REPORT_TYPE[j]);
//        }
//        jComboBoxType.setSelectedItem(Constant.REPORT_TYPE[2]);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanelControl = new javax.swing.JPanel();
        jPanelButtonControl = new javax.swing.JPanel();
        jRadioButtonForDRG = new javax.swing.JRadioButton();
        jRadioButtonForNHSO = new javax.swing.JRadioButton();
        jCheckBoxVP = new javax.swing.JCheckBox();
        jRadioButtonForNHSO1 = new javax.swing.JRadioButton();
        jRadioButtonForNHSO53 = new javax.swing.JRadioButton();
        jRadioButtonForNHSO531 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        dateComboBoxStartDate = new com.report12file.utility.DateComboBox();
        jLabelEndDate = new javax.swing.JLabel();
        dateComboBoxEndDate = new com.report12file.utility.DateComboBox();
        jCheckBoxSelectAll = new javax.swing.JCheckBox();
        jLabelStartDate = new javax.swing.JLabel();
        jLabelType = new javax.swing.JLabel();
        jComboBoxType = new javax.swing.JComboBox();
        jButtonOK = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jToggleButtonExportFail = new javax.swing.JCheckBox();
        jToggleButtonCheckDD = new javax.swing.JCheckBox();
        jToggleButtonAll = new javax.swing.JCheckBox();
        printShowReportLogControl2 = new com.reportcenter.utility.PrintShowReportLogControl();
        jCheckBoxDBBackup = new javax.swing.JCheckBox();
        jScrollPaneReport = new javax.swing.JScrollPane();
        jTableReport = new javax.swing.JTable();

        setLayout(new java.awt.GridBagLayout());

        jPanelControl.setLayout(new java.awt.GridBagLayout());

        jPanelButtonControl.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelButtonControl.setLayout(new java.awt.GridBagLayout());

        buttonGroup1.add(jRadioButtonForDRG);
        jRadioButtonForDRG.setText("ʹ�.");
        jRadioButtonForDRG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonForDRGActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelButtonControl.add(jRadioButtonForDRG, gridBagConstraints);

        buttonGroup1.add(jRadioButtonForNHSO);
        jRadioButtonForNHSO.setText("ʻʪ. (25 �.�. 51)");
        jRadioButtonForNHSO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonForNHSOActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelButtonControl.add(jRadioButtonForNHSO, gridBagConstraints);

        jCheckBoxVP.setText("�Է�ԡ���Ѻ��ԡ��");
        jCheckBoxVP.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanelButtonControl.add(jCheckBoxVP, gridBagConstraints);

        buttonGroup1.add(jRadioButtonForNHSO1);
        jRadioButtonForNHSO1.setText("ʻʪ. ����� eClaim V1.0.19");
        jRadioButtonForNHSO1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonForNHSO1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelButtonControl.add(jRadioButtonForNHSO1, gridBagConstraints);

        buttonGroup1.add(jRadioButtonForNHSO53);
        jRadioButtonForNHSO53.setText("ʻʪ. 53 eClaim (�.�. 53)");
        jRadioButtonForNHSO53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonForNHSO53ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelButtonControl.add(jRadioButtonForNHSO53, gridBagConstraints);

        buttonGroup1.add(jRadioButtonForNHSO531);
        jRadioButtonForNHSO531.setSelected(true);
        jRadioButtonForNHSO531.setText("ʻʪ. 53 OP-PP2010 (�.�. 53)");
        jRadioButtonForNHSO531.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonForNHSO531ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelButtonControl.add(jRadioButtonForNHSO531, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanelControl.add(jPanelButtonControl, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

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
        jLabelEndDate.setText("�֧�ѹ���");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanel1.add(jLabelEndDate, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(dateComboBoxEndDate, gridBagConstraints);

        jCheckBoxSelectAll.setText("���͡������");
        jCheckBoxSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSelectAllActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jCheckBoxSelectAll, gridBagConstraints);

        jLabelStartDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/report12file/gui/images/appointment.gif"))); // NOI18N
        jLabelStartDate.setText("�ҡ�ѹ���");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jLabelStartDate, gridBagConstraints);

        jLabelType.setText("��Դ�ͧ���");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanel1.add(jLabelType, gridBagConstraints);

        jComboBoxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "dbf ���", "text ���" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jComboBoxType, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelControl.add(jPanel1, gridBagConstraints);

        jButtonOK.setFont(new java.awt.Font("Dialog", 0, 18));
        jButtonOK.setText("��ŧ");
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanelControl.add(jButtonOK, gridBagConstraints);

        jButtonCancel.setFont(new java.awt.Font("Dialog", 0, 18));
        jButtonCancel.setText("¡��ԡ");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanelControl.add(jButtonCancel, gridBagConstraints);

        buttonGroup2.add(jToggleButtonExportFail);
        jToggleButtonExportFail.setText("���͡��¡�÷������ҹ");
        jToggleButtonExportFail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonExportFailActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanelControl.add(jToggleButtonExportFail, gridBagConstraints);

        buttonGroup2.add(jToggleButtonCheckDD);
        jToggleButtonCheckDD.setSelected(true);
        jToggleButtonCheckDD.setText("��Ǩ�ͺ Datadict");
        jToggleButtonCheckDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonCheckDDActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanelControl.add(jToggleButtonCheckDD, gridBagConstraints);

        buttonGroup2.add(jToggleButtonAll);
        jToggleButtonAll.setText("���͡������");
        jToggleButtonAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonAllActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanelControl.add(jToggleButtonAll, gridBagConstraints);

        printShowReportLogControl2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printShowReportLogControl2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 0, 3);
        jPanelControl.add(printShowReportLogControl2, gridBagConstraints);

        jCheckBoxDBBackup.setText("��ͧ��ô֧��§ҹ�ҡ�ҹ���ͧ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanelControl.add(jCheckBoxDBBackup, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 0, 11);
        add(jPanelControl, gridBagConstraints);

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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jScrollPaneReport, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButtonExportFailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonExportFailActionPerformed
        jToggleButtonCheckDDActionPerformed(null);
}//GEN-LAST:event_jToggleButtonExportFailActionPerformed

    private void jRadioButtonForNHSO1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonForNHSO1ActionPerformed
        jCheckBoxDBBackup.setEnabled(false);
        jCheckBoxDBBackup.setSelected(false);
        this.jCheckBoxVP.setEnabled(jRadioButtonForNHSO.isSelected());
        setTableListReport2();
        if(jCheckBoxSelectAll.isSelected()){
            jCheckBoxSelectAllActionPerformed(null);
        }
    }//GEN-LAST:event_jRadioButtonForNHSO1ActionPerformed
    private void dateComboBoxStartDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxStartDateActionPerformed
//        Calendar c = Calendar.getInstance();
//        c.setTime(DateUtil.getDateFromText(dateComboBoxStartDate.getText()));
//        int month = c.get(Calendar.MONTH) + 1;
//        c.set(Calendar.MONTH,month);
//        String date_str = DateUtil.getTextDB(c.getTime(),false);
//        this.dateComboBoxEndDate.setText(DateUtil.getGuiDateTime(date_str));
    }//GEN-LAST:event_dateComboBoxStartDateActionPerformed
    private void jRadioButtonForDRGActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRadioButtonForDRGActionPerformed
    {//GEN-HEADEREND:event_jRadioButtonForDRGActionPerformed
        jCheckBoxDBBackup.setEnabled(false);
        jCheckBoxDBBackup.setSelected(false);
        setTableListReport();
        if(jCheckBoxSelectAll.isSelected()){
            jCheckBoxSelectAllActionPerformed(null);
        }
    }//GEN-LAST:event_jRadioButtonForDRGActionPerformed
    private void jRadioButtonForNHSOActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRadioButtonForNHSOActionPerformed
    {//GEN-HEADEREND:event_jRadioButtonForNHSOActionPerformed
        jCheckBoxDBBackup.setEnabled(false);
        jCheckBoxDBBackup.setSelected(false);
        this.jCheckBoxVP.setEnabled(jRadioButtonForNHSO.isSelected());
        setTableListReport();
        if(jCheckBoxSelectAll.isSelected()){
            jCheckBoxSelectAllActionPerformed(null);
        }
    }//GEN-LAST:event_jRadioButtonForNHSOActionPerformed
    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        cancelProcess();
    }//GEN-LAST:event_jButtonCancelActionPerformed
    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        queryReport();
    }//GEN-LAST:event_jButtonOKActionPerformed
    private void jCheckBoxSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSelectAllActionPerformed
        int length = this.jTableReport.getRowCount();
        if(jCheckBoxSelectAll.isSelected())
            this.jTableReport.setRowSelectionInterval(0,length-1);
        else
            this.jTableReport.clearSelection();
    }//GEN-LAST:event_jCheckBoxSelectAllActionPerformed
    private void jTableReportMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableReportMouseReleased
    }//GEN-LAST:event_jTableReportMouseReleased

    private void jToggleButtonCheckDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonCheckDDActionPerformed
        this.jComboBoxType.setEnabled(true);
        if(!jToggleButtonCheckDD.isSelected()){
            this.jComboBoxType.setEnabled(false);
            this.jComboBoxType.setSelectedIndex(1);
            theUS.setStatus("����͡��§ҹ�������ҹ���͹� Datadict ���������ö���͡��ٻẺ DBF ��",UpdateStatus.WARNING);
        }
    }//GEN-LAST:event_jToggleButtonCheckDDActionPerformed

    private void jToggleButtonAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonAllActionPerformed
        jToggleButtonCheckDDActionPerformed(null);
}//GEN-LAST:event_jToggleButtonAllActionPerformed

    private void jRadioButtonForNHSO53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonForNHSO53ActionPerformed
        jCheckBoxDBBackup.setEnabled(true);
        this.jCheckBoxVP.setEnabled(jRadioButtonForNHSO.isSelected());
        setTableListReport3();
        if(jCheckBoxSelectAll.isSelected()){
            jCheckBoxSelectAllActionPerformed(null);
        }
}//GEN-LAST:event_jRadioButtonForNHSO53ActionPerformed

    private void printShowReportLogControl2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printShowReportLogControl2ActionPerformed
        try {
            printShowReportLogControl2.selectReportLog(theRpControl.theConnectionInf);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_printShowReportLogControl2ActionPerformed

    private void jRadioButtonForNHSO531ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonForNHSO531ActionPerformed
        jCheckBoxDBBackup.setEnabled(true);
        this.jCheckBoxVP.setEnabled(jRadioButtonForNHSO.isSelected());
        setTableListReport3();
        if(jCheckBoxSelectAll.isSelected()){
            jCheckBoxSelectAllActionPerformed(null);
        }
    }//GEN-LAST:event_jRadioButtonForNHSO531ActionPerformed
    private void setTableListReport() {
        TableModelGUI tm= new TableModelGUI(col,Report12FileData.reportName.length);
        for(int i=0 ;i<Report12FileData.reportName.length; i++) {
            tm.setValueAt(String.valueOf(i+1),i,0);
            tm.setValueAt(Report12FileData.reportName[i],i,1);
            tm.setValueAt(Report12FileData.reportExplain[i],i,2);
        }
        jTableReport.setModel(tm);
        rendererCenter.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        jTableReport.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTableReport.getColumnModel().getColumn(0).setCellRenderer(rendererCenter);
        jTableReport.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableReport.getColumnModel().getColumn(2).setPreferredWidth(750);
    }
    private void setTableListReport2() {
        TableModelGUI tm= new TableModelGUI(col,Report12FileData.report_eClaim_v.length);
        for(int i=0 ;i<Report12FileData.report_eClaim_v.length; i++) {
            tm.setValueAt(String.valueOf(i+1),i,0);
            tm.setValueAt(Report12FileData.report_eClaim_v[i],i,1);
            tm.setValueAt(Report12FileData.report_eClaim_v_Explain[i],i,2);
        }
        jTableReport.setModel(tm);
        rendererCenter.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        jTableReport.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTableReport.getColumnModel().getColumn(0).setCellRenderer(rendererCenter);
        jTableReport.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableReport.getColumnModel().getColumn(2).setPreferredWidth(750);
    }
    private void setTableListReport3() {
        TableModelGUI tm= new TableModelGUI(col,Report12FileData.report_eClaim.length);
        for(int i=0 ;i<Report12FileData.report_eClaim.length; i++) {
            tm.setValueAt(String.valueOf(i+1),i,0);
            tm.setValueAt(Report12FileData.report_eClaim[i],i,1);
            tm.setValueAt(Report12FileData.report_eClaim_Explain[i],i,2);
        }
        jTableReport.setModel(tm);
        rendererCenter.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        jTableReport.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTableReport.getColumnModel().getColumn(0).setCellRenderer(rendererCenter);
        jTableReport.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableReport.getColumnModel().getColumn(2).setPreferredWidth(750);
    }
    /**
     *
     *  ��㹡�� �������㹡�ä�����§ҹ��кѹ�֡ŧ������к�
     *
     */
    public void queryReport() 
    {
        System.out.println("public void queryReport()");
        /**��Ǩ�ͺ�ѹ�������㹪�ǧ��ԧ�������*/

        int index = jComboBoxType.getSelectedIndex();
        if(index == 0)              fileType = Constant.DBF_INDEX;
        else                        fileType = Constant.TXT_INDEX;

        this.startDate = dateComboBoxStartDate.getText();
        this.endDate = dateComboBoxEndDate.getText();
        int mode = Rp12Control.MODE_DRG;
        if(this.jRadioButtonForNHSO.isSelected())            mode = Rp12Control.MODE_NHSO;
        else if(this.jRadioButtonForNHSO1.isSelected())      mode = Rp12Control.MODE_ECLM;
        else if(this.jRadioButtonForNHSO53.isSelected())     mode = Rp12Control.MODE_ECLM53;
        else if(this.jRadioButtonForNHSO531.isSelected())     mode =Rp12Control.MODE_OPPP53;

        int export = 1;
        if(this.jToggleButtonAll.isSelected())              export = Rp12Control.EXP_ALL;
        else if(this.jToggleButtonCheckDD.isSelected())     export = Rp12Control.EXP_PASS;
        else if(this.jToggleButtonExportFail.isSelected())  export = Rp12Control.EXP_FAIL;

        if(startDate.compareTo(endDate) > 0){
            theUS.setStatus("��س��кت�ǧ�ѹ������١��ͧ",UpdateStatus.WARNING);
            return;
        }
        if(!setPathFileToSave()) {
            theUS.setStatus("��سҡ�˹����˹觷���������§ҹ",UpdateStatus.WARNING);
            return;
        }
        theUS.setStatus("���ѧ�͡��§ҹ...���ѡ����",UpdateStatus.WARNING);
        if (mode < 3) {
            boolean ret = theRpControl.setDataExport12File(jTableReport.getSelectedRows(),
                    startDate, endDate, select_path, fileType, export, mode, jCheckBoxVP.isSelected() );
            if (!ret) {
                return;
            }
            theRpControl.start();
        } else {
                boolean ret = theRpControl2.setDataExport12File(jTableReport.getSelectedRows(),
                        startDate, endDate, select_path, fileType, export, mode,
                        theUS, theRpControl.theConnectionInf,jCheckBoxDBBackup.isSelected());
                if (!ret) {
                    return;
                }
                theRpControl2.start();
        }
    }
    /**��㹡�� ��ش��÷ӧҹ�ͧ ����� ��������ӧҹ㹢�й�� ���ʴ� Dialog ��͹
     *
     *
     *
     */
    public void cancelProcess() {   /**��Ǩ�ͺʶҹТͧ��÷ӧҹ*/
        int choose  = JOptionPane.showConfirmDialog(this
                ,Language.getTextBundle("ConformStopProcess",language)
                ,Language.getTextBundle("Warning",language),JOptionPane.OK_CANCEL_OPTION);
        /**��Ǩ�ͺ����׹�ѹ*/
        if(choose != JOptionPane.OK_OPTION)
            return ;
        this.theRpControl.stop();
        this.theRpControl2.stop();
        this.setEnabled(true);
    }
    /**
     *
     *  ��㹡�á�˹���� Panel �������ö�ӧҹ���������
     *
     *  @param isEnable �� boolean ����� true �к��������ö�ӧҹ��
     *
     * , false �к�����������ö�ӧҹ��
     *
     */
    public void setEnableGUI(boolean isEnable){
        //jComboBoxReport.setEnabled(isEnable);
        jComboBoxType.setEnabled(isEnable);
        dateComboBoxStartDate.setEnabled(isEnable);
        dateComboBoxEndDate.setEnabled(isEnable);
        jCheckBoxSelectAll.setEnabled(isEnable);
        jButtonOK.setEnabled(isEnable);
        //jButtonCancel.setEnabled(isEnable);
        jTableReport.setEnabled(isEnable);
        jScrollPaneReport.setEnabled(isEnable);
        this.jRadioButtonForDRG.setEnabled(isEnable);
        this.jRadioButtonForNHSO.setEnabled(isEnable);
        this.jRadioButtonForNHSO53.setEnabled(isEnable);
        this.jRadioButtonForNHSO531.setEnabled(isEnable);
        jCheckBoxVP.setEnabled(false);
        if(jRadioButtonForNHSO.isEnabled() && jRadioButtonForNHSO.isSelected())
            jCheckBoxVP.setEnabled(true);
        this.jRadioButtonForNHSO1.setEnabled(isEnable);
        jToggleButtonExportFail.setEnabled(isEnable);
        jToggleButtonAll.setEnabled(isEnable);
        jToggleButtonCheckDD.setEnabled(isEnable);
        printShowReportLogControl2.setEnabled(isEnable);

    }
    /**��㹡������ҷ�����͡�����ҧ ������͡��§ҹ���ú�ҧ
     *
     *  @return �� Report12FileName ��觨��� �����ŷ��͡�����§ҹ���е�ǵ�ͧ� query �������
     *
     */
    private Report12FileName getReport12FileName(){
        System.out.println("private Report12FileName getReport12FileName(){");
        theReport12FileName = new Report12FileName();
        int[] rows = jTableReport.getSelectedRows();
//        System.out.println("row[i] = " + rows.length);
        for(int i=0;i<rows.length;i++){
            if(rows[i]==0){
                theReport12FileName.ins = Constant.SELECTED;
            }else if(rows[i]==1){
                theReport12FileName.pat = Constant.SELECTED;
            }else if(rows[i]==2){
                theReport12FileName.opd = Constant.SELECTED;
            }else if(rows[i]==3){
                theReport12FileName.orf= Constant.SELECTED;
            }else if(rows[i]==4){
                theReport12FileName.odx = Constant.SELECTED;
            }else if(rows[i]==5){
                theReport12FileName.oop = Constant.SELECTED;
            }else if(rows[i]==6){
                theReport12FileName.ipd = Constant.SELECTED;
            }else if(rows[i]==7){
                theReport12FileName.irf = Constant.SELECTED;
            }else if(rows[i]==8){
                theReport12FileName.idx = Constant.SELECTED;
            }else if(rows[i]==9){
                theReport12FileName.iop = Constant.SELECTED;
            }else if(rows[i]==10){
                theReport12FileName.cht = Constant.SELECTED;
            }else if(rows[i]==11){
                theReport12FileName.cha = Constant.SELECTED;
            }else if(rows[i]==12){
                theReport12FileName.aer = Constant.SELECTED;
            }
//            System.out.println("row[i] = " + rows[i]);
        }
        return theReport12FileName;
    }
    /**
     *
     *  ��㹡�õ�Ǩ�ͺ �ѹ���������� �Ѻ�ѹ�������ش���ѹ���ǡѹ������� ������ѹ���ǡѹ
     *
     *  ��� return �� false ����������� return �� true
     *
     *  @return boolean true ����� false ���
     *
     */
    private boolean isBetweenDateFormat(){
        /**��˹�����ѹ���*/
        this.startDate = dateComboBoxStartDate.getText();
        this.endDate = dateComboBoxEndDate.getText();
        /**��Ǩ�ͺ�ѹ�������������ѹ���ǡѹ�Ѻ�ѹ�������ش�������*/
        if(startDate.compareTo(endDate) <= 0){
            isSelectBetweenDate = true;
        }else{
            isSelectBetweenDate = false;
        }
        return isSelectBetweenDate;
    }
    /**��㹡�����͡ Folder ����ͧ��èкѹ�֡���
     *
     *@return �� boolean
     *
     */
    public boolean setPathFileToSave(){
        boolean isSetPathFileDone = false;
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(oldPath));
        //��س����͡ path ������ͧ��úѹ�֡
        chooser.setDialogTitle(Language.getTextBundle("ChoosePathToSave",language) );
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setApproveButtonText("Save");
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            ShowCommonLine.show("getCurrentDirectory(): " +  chooser.getCurrentDirectory(),false);
            /**�� path ���������*/
            oldPath = chooser.getSelectedFile().toString();
            /**���� path ��������*/
            select_path =  chooser.getSelectedFile().toString()+"/";
            isSetPathFileDone = true;
        } else {
            isSetPathFileDone = false;
        }
        return isSetPathFileDone;
    }
    /**
     *
     *  ��㹡���ʴ������ź����ҧ
     *
     *  @param isShowList
     *
     *  @param isSelectAll
     *
     *
     *
     */
    public static void main(String args[]){
        try {
            //      UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        PanelReport12File thePanelReport12File= new PanelReport12File();
        JFrame frm = new JFrame("PanelReport12File");
        frm.getContentPane().add(thePanelReport12File);
        frm.pack();
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
    }
    /**��㹡���ʴ�����*/
    private void setLanguage() {
        jLabelType.setText(Language.getTextBundle("SaveType",language));
        jLabelStartDate.setText(Language.getTextBundle("StartDate",language));
        jLabelEndDate.setText(Language.getTextBundle("FinishDate",language));
        jCheckBoxSelectAll.setText(Language.getTextBundle("SelectAll",language)); 
        jButtonOK.setText(Language.getTextBundle("OK",language));
        jButtonCancel.setText(Language.getTextBundle("Cancel",language));
        jRadioButtonForNHSO.setText(Language.getTextBundle(jRadioButtonForNHSO.getText(),language));
        jRadioButtonForDRG.setText(Language.getTextBundle(jRadioButtonForDRG.getText(),language));
        col = new  String[3];
        col[0] = "no";//Language.getTextBundle("Sequence",language);
        col[1] = "name";//Language.getTextBundle("ReportName",language);
        col[2] = Language.getTextBundle("Description",language);
    }
    public void setEnabled(boolean setEnabled) {
        this.setEnableGUI(setEnabled);
        if(theDialogShowStatus!=null) {
            theDialogShowStatus.setEnableGUI(setEnabled);
            if(setEnabled) {
                System.out.println("SetShow Dialog");
                //theDialogShowStatus.showDialog("");
            }
        }
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
            if(color.equals(java.awt.Color.RED))
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
    private com.report12file.utility.DateComboBox dateComboBoxEndDate;
    private com.report12file.utility.DateComboBox dateComboBoxStartDate;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JCheckBox jCheckBoxDBBackup;
    private javax.swing.JCheckBox jCheckBoxSelectAll;
    private javax.swing.JCheckBox jCheckBoxVP;
    private javax.swing.JComboBox jComboBoxType;
    private javax.swing.JLabel jLabelEndDate;
    private javax.swing.JLabel jLabelStartDate;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelButtonControl;
    private javax.swing.JPanel jPanelControl;
    private javax.swing.JRadioButton jRadioButtonForDRG;
    private javax.swing.JRadioButton jRadioButtonForNHSO;
    private javax.swing.JRadioButton jRadioButtonForNHSO1;
    private javax.swing.JRadioButton jRadioButtonForNHSO53;
    private javax.swing.JRadioButton jRadioButtonForNHSO531;
    private javax.swing.JScrollPane jScrollPaneReport;
    private javax.swing.JTable jTableReport;
    private javax.swing.JCheckBox jToggleButtonAll;
    private javax.swing.JCheckBox jToggleButtonCheckDD;
    private javax.swing.JCheckBox jToggleButtonExportFail;
    private com.reportcenter.utility.PrintShowReportLogControl printShowReportLogControl2;
    // End of variables declaration//GEN-END:variables
}
