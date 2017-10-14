/*
 * DialogAccident.java
 *
 * Created on 9 ธันวาคม 2546, 14:14 น.
 */
package com.hosv3.gui.dialog;
import java.util.*;
import java.awt.*;
import javax.swing.*;

import com.hospital_os.object.*; 
import com.hospital_os.utility.TaBleModel;

import com.hosv3.control.*;
import com.hosv3.utility.connection.*;
import com.hosv3.utility.*;
import com.hosv3.object.*;
import com.hospital_os.utility.CellRendererHos;
/**
 *
 * @author amp
 */
public class DialogAccident extends javax.swing.JFrame implements UpdateStatus
{
	public static final long  serialVersionUID = 0;
    HosControl theHC;
    HosObject theHO;
    public boolean actionCommand = false;
    LookupControl theLookupControl;    
    VisitControl theVisitControl;
    VitalControl theVitalControl;
    DiagnosisControl theDiagnosisControl;
    PatientControl thePatientControl;
    SetupControl theSetupControl;
    String date;
    Accident theAccident = new Accident();
    Vector comboboxaddresscgw = new Vector();
    Vector comboboxaddressamp = new Vector();
    Vector comboboxaddresstmb = new Vector();
    Vector vAeType; 
    Vector vEmType;
    private String[] col_jTableListAccident = {"HN","ชื่อ-สกุล"};
    CellRendererHos hnRender = new CellRendererHos(CellRendererHos.HN);
    CellRendererHos vnRender = new CellRendererHos(CellRendererHos.VN);
    CellRendererHos datetimeRender = new CellRendererHos(CellRendererHos.DATE_TIME);
    /** 
     *เก็บผลจากการค้นหาเป็น vector จากการค้นหาข้อมูลอุบัติเหตุ
     */
    private Vector vAccident;
   /*
     *เก็บข้อมุลตำแหน่งที่เลือกข้อมูลขาก Vector
     */
    int row=0;
    private DialogCostDetail dcd;  
    public static boolean closeDialog = false;
    /** Creates new form DialogAccident */
    public DialogAccident(HosControl hc,UpdateStatus us)
    {
        //super(parent, modal);        
        theLookupControl = hc.theLookupControl;        
        theVisitControl = hc.theVisitControl;     
        theVitalControl = hc.theVitalControl;
        thePatientControl = hc.thePatientControl;
        theSetupControl = hc.theSetupControl;
        theDiagnosisControl = hc.theDiagnosisControl;
    hnRender = new CellRendererHos(CellRendererHos.HN,theLookupControl.getSequenceDataHN().pattern);
    vnRender = new CellRendererHos(CellRendererHos.VN,theLookupControl.getSequenceDataVN().pattern);
        theHC = hc;
        theHO = hc.theHO;
        initComponents();
        setLanguage("");        
        jButtonDelete.setEnabled(false);
      //  jComboBoxEmtype.setVisible(false);
        setComboBoxEdit(true);
        panelAccident1.setControl(hc, this);
        panelWound1.setControl(hc,this);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/hospital_os/images/accident.gif")));
        setTitle(Constant.getTextBundle("ข้อมูลการเกิดอุบัติเหตุ"));
        setSize(800,600);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-getSize().width)/2, (screenSize.height-getSize().height)/2);
    }
    
    //////////////////////////////////////////////////////////////////////////
    /**
     *dialog ที่ใช้ในการส่งข้อความเตื่อนผู้ใช้
     */
     public void setStatus(String str, int status)
     {
         
        ThreadStatus theTT = new ThreadStatus(this,this.jLabelStatus);
        theTT.start();
        str = Constant.getTextBundle(str);
        jLabelStatus.setText(" " + str);
         if(status == UpdateStatus.WARNING){
            jLabelStatus.setBackground(Color.YELLOW);
        }
        if(status == UpdateStatus.COMPLETE){
            jLabelStatus.setBackground(Color.GREEN);
        }
        if(status == UpdateStatus.ERROR){
            jLabelStatus.setBackground(Color.RED);
        }
        if(status == UpdateStatus.NORMAL) {
            jLabelStatus.setBackground(Color.GRAY);
        }
    }

    public JFrame getJFrame() {
        return this;
    }    
     /**
      *dialog ที่ใข้ในการให้ผู้ใข้ทำการยีนยันสิ่งต่าง
      */
    public boolean confirmBox(String str, int status) {
        int i = JOptionPane.showConfirmDialog(this,str,Constant.getTextBundle("เตือน")
                ,JOptionPane.YES_NO_OPTION);
        return (i==JOptionPane.YES_OPTION);
    }
     //////////////////////////////////////////////////////////////////////////
   
    private void setDataAll()
    {   
        if(theHO.theVisit!=null)
        {
            if(theHO.theVisit.getObjectId()!=null)
           {
                panelAccident1.setInitValue(theHO.theVisit.hn,theHO.thePatient.patient_name+ " " + theHO.thePatient.patient_last_name);
                jButtonSave.setEnabled(true);
            }
            else
            {
                jButtonSave.setEnabled(false);
            }
        }       
    }
   
    private void setComboBoxEdit(boolean b)
    {   
        panelAccident1.setComboBoxEdit(b);
      //  dateComboBoxDateAccident.setEditable(b);
      //  dateComboBoxDateAccidentToHos.setEditable(b);
        dateComboBoxFrom.setEditable(b);
        dateComboBoxTo.setEditable(b);
    //    panelCatAddress1.setEnabled(b);
    }
   
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroupUCAE = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        dateComboBoxFrom = new com.hospital_os.utility.DateComboBox();
        dateComboBoxTo = new com.hospital_os.utility.DateComboBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        hNTextFieldSearchHN = new com.hospital_os.utility.HNTextField();
        jButtonRef = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListAccident = new com.hosv3.gui.component.HJTableSort();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonClose = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        panelAccident1 = new com.hosv3.gui.dialog.PanelAccident();
        panelWound1 = new com.hosv3.gui.dialog.PanelWound();
        jLabelStatus = new javax.swing.JLabel();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel4.setMinimumSize(new java.awt.Dimension(230, 204));
        jPanel4.setPreferredSize(new java.awt.Dimension(230, 204));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel17.setText("\u0e16\u0e36\u0e07");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 1, 1);
        jPanel7.add(jLabel17, gridBagConstraints);

        dateComboBoxFrom.setAutoscrolls(true);
        dateComboBoxFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxFromActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 1, 1);
        jPanel7.add(dateComboBoxFrom, gridBagConstraints);

        dateComboBoxTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxToActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 1, 1);
        jPanel7.add(dateComboBoxTo, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel18.setText("HN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 0);
        jPanel5.add(jLabel18, gridBagConstraints);

        hNTextFieldSearchHN.setColumns(9);
        hNTextFieldSearchHN.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        hNTextFieldSearchHN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hNTextFieldSearchHNActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 12, 0, 1);
        jPanel5.add(hNTextFieldSearchHN, gridBagConstraints);

        jButtonRef.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/Refresh.png")));
        jButtonRef.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonRef.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonRef.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonRef.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonRef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 1, 0);
        jPanel5.add(jButtonRef, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel7.add(jPanel5, gridBagConstraints);

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("\u0e27\u0e31\u0e19\u0e17\u0e35\u0e48");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel7.add(jCheckBox1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 5, 0, 5);
        jPanel4.add(jPanel7, gridBagConstraints);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(24, 110));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(454, 100));
        jTableListAccident.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableListAccident.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableListAccidentMouseReleased(evt);
            }
        });
        jTableListAccident.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableListAccidentKeyReleased(evt);
            }
        });

        jScrollPane1.setViewportView(jTableListAccident);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 9, 0, 5);
        jPanel4.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        getContentPane().add(jPanel4, gridBagConstraints);

        jTabbedPane1.setMinimumSize(new java.awt.Dimension(601, 564));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(601, 564));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setMinimumSize(new java.awt.Dimension(500, 165));
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 165));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        jPanel1.add(jButtonSave, gridBagConstraints);

        jButtonClose.setText("Cancel");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jButtonClose, gridBagConstraints);

        jButtonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif")));
        jButtonDelete.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonDelete.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDelete.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDelete.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButtonDelete, gridBagConstraints);

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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jButtonAdd, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 14, 2, 4);
        jPanel2.add(jPanel1, gridBagConstraints);

        panelAccident1.setPreferredSize(new java.awt.Dimension(646, 452));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(panelAccident1, gridBagConstraints);

        jTabbedPane1.addTab("\u0e02\u0e49\u0e2d\u0e21\u0e39\u0e25\u0e2d\u0e38\u0e1a\u0e31\u0e15\u0e34\u0e40\u0e2b\u0e15\u0e38", jPanel2);

        jTabbedPane1.addTab("\u0e02\u0e49\u0e2d\u0e21\u0e39\u0e25\u0e1a\u0e32\u0e14\u0e41\u0e1c\u0e25", panelWound1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane1, gridBagConstraints);

        jLabelStatus.setText("Status");
        jLabelStatus.setMinimumSize(new java.awt.Dimension(27, 21));
        jLabelStatus.setOpaque(true);
        jLabelStatus.setPreferredSize(new java.awt.Dimension(27, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jLabelStatus, gridBagConstraints);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-835)/2, (screenSize.height-598)/2, 835, 598);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefActionPerformed
        searchAccident();
        if(jTableListAccident.getRowCount()>0){
            this.jTableListAccident.setRowSelectionInterval(0,0);
            selectListAccident();            
        }
    }//GEN-LAST:event_jButtonRefActionPerformed

    private void jTableListAccidentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableListAccidentKeyReleased
        if(evt.getKeyCode()==evt.VK_UP || evt.getKeyCode()==evt.VK_DOWN)
            this.jTableListAccidentMouseReleased(null);
    }//GEN-LAST:event_jTableListAccidentKeyReleased

    private void dateComboBoxToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxToActionPerformed

    }//GEN-LAST:event_dateComboBoxToActionPerformed

    private void dateComboBoxFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxFromActionPerformed

    }//GEN-LAST:event_dateComboBoxFromActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        this.dateComboBoxFrom.setEnabled(jCheckBox1.isSelected());
        this.dateComboBoxTo.setEnabled(jCheckBox1.isSelected());
        searchAccident();
        if(jTableListAccident.getRowCount()>0){
            this.jTableListAccident.setRowSelectionInterval(0,0);
            selectListAccident();            
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        deleteAccident();  
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        //this.setAccident(null);
        panelAccident1.setAccident(null);
    }//GEN-LAST:event_jButtonAddActionPerformed
    private void hNTextFieldSearchHNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hNTextFieldSearchHNActionPerformed
        searchAccident();
        if(jTableListAccident.getRowCount()>0){
            this.jTableListAccident.setRowSelectionInterval(0,0);
            selectListAccident();            
        }
    }                                                       private void jTableListAccidentMouseReleased(java.awt.event.MouseEvent evt) {//GEN-LAST:event_hNTextFieldSearchHNActionPerformed
        if(jTableListAccident.getSelectedRow() != -1)//GEN-FIRST:event_jTableListAccidentMouseReleased
        {    
            selectListAccident();
        }
    }                                                    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-LAST:event_jTableListAccidentMouseReleased

        setVisible(false);//GEN-FIRST:event_formWindowClosing
        closeDialog = true;
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        actionCommand = true;  
        String old_ext_cause = theAccident.b_setup_accident_type_id;
        theAccident = panelAccident1.getAccident();
        DiagIcd10 dx10 = null;
        Constant.println("b_setup_accident_type_id" + theAccident.b_setup_accident_type_id);
        //ในการบันทึกครั้งแรกถ้าเป็นอุบัติเหตุต้องลงสาเหตุด้วย
        //ถ้าเป็นการบันทึกครั้งถัดมา เป็นอุบัติเหตุ และสาเหตุเหมือนเดิม
        if((theAccident.getObjectId()==null
            && !theAccident.b_setup_accident_type_id.equals(AccidentType.NOT_ACCIDENT))
        || (theAccident.getObjectId()!=null
            && !old_ext_cause.equals(theAccident.b_setup_accident_type_id))
            && !theAccident.b_setup_accident_type_id.equals(AccidentType.NOT_ACCIDENT))
        {
            dx10 = new DiagIcd10();
            DiagDoctorClinic ddc = new DiagDoctorClinic();
            DialogOffDrug theDialogUseDoctor = new DialogOffDrug(getJFrame(),true,theHC,this);
            theDialogUseDoctor.setTitle(Constant.getTextBundle("เลือกข้อมูลสำหรับลงรหัส"));
            theDialogUseDoctor.setUseDoctor();
            boolean ret = theDialogUseDoctor.showDialog(ddc);
            if(!ret)
                return;
            dx10.clinic_kid = ddc.clinic_id;
            dx10.doctor_kid = ddc.doctor_id;
        }
        if(theDiagnosisControl.saveAccident(theAccident, dx10,this)>0){
            this.panelAccident1.setAccident(theAccident);
            this.panelWound1.setAccident(theAccident);
            searchAccident();
            panelWound1.setVisitId(theAccident.patient_id,theAccident.vn_id);
            setEnableForm(false); 
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed
    
   /*
    * ทำการ enblble ฟอร์มเพื่อให้ไม่สามารแ้ไขค่าได้
    */
    private void setEnableForm(boolean result)
    {
      //  hNTextFieldHN.setEditable(false);  
      //  jTextFieldTime.setEnabled(result);      
      //  jTextFieldAccMu.setEnabled(result);       
      //  jTextFieldKilo.setEnabled(result);
      //  jTextFieldRoadName.setEnabled(result);
    }
    
    private void deleteAccident()
    {
        int ret = theVisitControl.deleteAccident(theAccident,this);
        if(ret > 0){
            theVitalControl.deleteWoundV(panelWound1.getWoundV(),this);
            panelWound1.setWoundV(new Vector());
            panelAccident1.setAccident(new Accident());
            searchAccident();
        }
    }
    
    
    /*
     *ค้นหาข้อมูล Accident
     *input : hn dateFrom dateTo
     *outup : ผลการค้นหา Vector vAccident
     */
    private void searchAccident()
    {
        String dateFrom = dateComboBoxFrom.getText();//dateTextFieldFrom.getTextBDate();
        String dateTo = dateComboBoxTo.getText();//dateTextFieldTo.getTextBDate();
        String hn = hNTextFieldSearchHN.getText(); 
        if(!this.jCheckBox1.isSelected()){
            dateTo = "";
            dateFrom = "";
        }
        vAccident = theVisitControl.listAccident(dateFrom,dateTo,hn);
        setAccidentV(vAccident);    
    }
    
    /*
     *แสดงข้อมูลการค้นหาข้อมูลอุบัติเหตุ โดยนำ vector มาแสดง
     *input  :vector vAcci 
     *output :
     */
    private void setAccidentV(Vector vAcci)
    { 
        vAccident = vAcci;
        TaBleModel tm ;
        if(vAcci !=null && !vAcci.isEmpty()){
            tm = new TaBleModel(col_jTableListAccident,vAcci.size());
            for(int i=0; i<vAcci.size(); i++ )
            {
                Accident aCcident = (Accident)vAcci.get(i);
                tm.setValueAt(aCcident.hn,i,0);
                tm.setValueAt(aCcident.mask_patient_name,i,1);
            }  
        }
        else{
            tm = new TaBleModel(col_jTableListAccident,0);
        }
        jTableListAccident.setModel(tm);
        jTableListAccident.getColumnModel().getColumn(0).setCellRenderer(hnRender);
    }
    
    private void selectListAccident()
    {
        row = jTableListAccident.getSelectedRow();
        if(row != -1 && vAccident!=null && vAccident.size() >0)
        {
            theAccident = (Accident)vAccident.get(row);      
            jButtonSave.setEnabled(true);
            panelAccident1.setAccident(theAccident);
            panelWound1.setVisitId(theAccident.patient_id,theAccident.vn_id);
            panelWound1.setAccident(theAccident);
            jButtonDelete.setEnabled(true);
            jButtonSave.setEnabled(true);
            setEnableForm(true);            
        }                     
    }
    
    
    public boolean showDialog(HosObject ho)
    {
        theHO = ho;        
        date = theHO.date_time.substring(0,10);
        //this.jTabbedPane1.setSelectedIndex(1);
        if(theHO.thePatient!=null){
            hNTextFieldSearchHN.setText(theHO.thePatient.hn);
            jCheckBox1.setSelected(false);
            searchAccident();
        }
        if(jTableListAccident.getRowCount()>0){
            this.jTableListAccident.setRowSelectionInterval(0,0);
            selectListAccident();            
        }  
        this.jTabbedPane1.setSelectedIndex(1);
        setVisible(true);
        this.jTabbedPane1.setSelectedIndex(0);
        return closeDialog;
    }
    
      public void setLanguage(String msg)
      {
            GuiLang.setLanguage(jButtonSave);//ปุ่ม save
            GuiLang.setLanguage(jButtonClose);//ปุ่ม cancle
            GuiLang.setLanguage(jPanel2);
            GuiLang.setLanguage(jPanel4);
            GuiLang.setLanguage(jLabel18);
            GuiLang.setLanguage(this.col_jTableListAccident);
//            GuiLang.setLanguage(jButtonSearch);        
            GuiLang.setLanguage(jLabel17);      
            GuiLang.setLanguage(jCheckBox1);
            GuiLang.setLanguage(jTabbedPane1);
         
      }   
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupUCAE;
    private com.hospital_os.utility.DateComboBox dateComboBoxFrom;
    private com.hospital_os.utility.DateComboBox dateComboBoxTo;
    private com.hospital_os.utility.HNTextField hNTextFieldSearchHN;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonRef;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.hosv3.gui.component.HJTableSort jTableListAccident;
    private com.hosv3.gui.dialog.PanelAccident panelAccident1;
    private com.hosv3.gui.dialog.PanelWound panelWound1;
    // End of variables declaration//GEN-END:variables
}
