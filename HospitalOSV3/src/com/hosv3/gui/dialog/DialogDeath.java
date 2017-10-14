/*
 * DialogAdmit.java
 *
 * Created on 8 พฤศจิกายน 2546, 14:14 น.
 */
package com.hosv3.gui.dialog;
import com.hosv3.control.lookup.Icd10Lookup;
import java.util.*;
import java.awt.*;
import javax.swing.*;

import com.hosv3.control.*;
import com.hosv3.utility.*;
import com.hosv3.object.*;
import com.hosv3.utility.connection.*;

import com.hospital_os.object.*; 
import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.ComboboxModel;
import com.hosv3.utility.Constant;
import com.hospital_os.utility.TaBleModel;
import com.hospital_os.utility.CellRendererHos;

import com.pcu.object.*;

/**
 *
 * @author  tong
 */
public class DialogDeath extends javax.swing.JDialog implements UpdateStatus{

    HosObject theHO;
    HosControl theHC;
    public boolean actionCommand = false;
    private LookupControl theLookupControl;
    private VisitControl theVisitControl;
    private DiagnosisControl theDiagnosisControl;
    private PatientControl thePatientControl;
    private SystemControl theSystemControl;
    private Visit theVisit;
    private Patient thePatient;
    private Death theDeath;
    private Family theFamily;
    boolean mode_not_save = false;
    CellRendererHos dateRender = new CellRendererHos(CellRendererHos.DATE_TIME);
  //     * เก็บข้อมูล Vector จากการค้นหาข้อมูลการตาย
    Vector vDeath;
  //   *เก็บข้อมุลตำแหน่งที่เลือกข้อมูลขาก Vector
    int row=0;
    public static boolean closeDialog = false;
    /** Creates new form DialogAdmit */
    private String[] col_jTableListDeath = {"ชื่อ-สกุล","VN","วันที่"};
    CellRendererHos vnRender = new CellRendererHos(CellRendererHos.VN);
    public DialogDeath(HosControl hc,JFrame frm)//,Office of) 
    {
        super(frm, true);
        initComponents();
        setControl(hc);
        setSize(640,480);
//        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/hospital_os/images/death.gif")));
        setTitle(Constant.getTextBundle("บันทึกข้อมูลการตาย"));
        Toolkit thekit = getToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//thekit.getScreenSize();
        setLocation((screenSize.width-getSize().width)/2, (screenSize.height-getSize().height)/2);              
    } 
    public DialogDeath(HosControl hc,UpdateStatus us)//,Office of)
    {
        super(us.getJFrame(), true);
        initComponents();
        setControl(hc);
        setSize(640,480);
        setTitle(Constant.getTextBundle("บันทึกข้อมูลการตาย"));
        Toolkit thekit = getToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//thekit.getScreenSize();
        setLocation((screenSize.width-getSize().width)/2, (screenSize.height-getSize().height)/2);              
    }
    public void setControl(HosControl hc)
    {
        theLookupControl = hc.theLookupControl;
        theVisitControl = hc.theVisitControl;
        theDiagnosisControl = hc.theDiagnosisControl;
        thePatientControl = hc.thePatientControl;
        theSystemControl = hc.theSystemControl;
        theHO = hc.theHO;
        theHC = hc;
    vnRender = new CellRendererHos(CellRendererHos.VN,theLookupControl.getSequenceDataVN().pattern);
        this.jTableListDeath.setGuiMode(true);
        setLanguage("");
        setComboBoxEdit(true);
        ComboboxModel.initComboBox(jComboBoxLocalType, theLookupControl.listLocalType());
        jButtonDel.setVisible(true);
        this.jTextFieldCaseDeath.setControl(new Icd10Lookup(theLookupControl),true);
        this.jTextFieldCdeatha.setControl(new Icd10Lookup(theLookupControl),true);
        this.jTextFieldCdeathb.setControl(new Icd10Lookup(theLookupControl),true);
        this.jTextFieldCdeathc.setControl(new Icd10Lookup(theLookupControl),true);
        this.jTextFieldCdeathd.setControl(new Icd10Lookup(theLookupControl),true);
//        showJpanel();
        setTableListDeath(vDeath);
//        jButtonSearch.setVisible(false);
    }
    //////////////////////////////////////////////////////////////////////////
    /**
     *dialog ที่ใช้ในการส่งข้อความเตื่อนผู้ใช้
     */
     public void setStatus(String str, int status) 
     {
         
        ThreadStatus theTT = new ThreadStatus(this.getJFrame(),this.jLabelStatus);
        theTT.start();
        str = Constant.getTextBundle(str);
        jLabelStatus.setText(" " + str);
        Constant.println("----SetStatus---- " + str);
        if(status == UpdateStatus.WARNING){
            jLabelStatus.setBackground(Color.YELLOW);
        }
        if(status == UpdateStatus.COMPLETE){
            jLabelStatus.setBackground(Color.GREEN);
        }
        if(status == UpdateStatus.ERROR){
            jLabelStatus.setBackground(Color.RED);
        }        
    }

    public JFrame getJFrame() {
        return null;
    }    
     /**
      *dialog ที่ใข้ในการให้ผู้ใข้ทำการยีนยันสิ่งต่าง
      */
    public boolean confirmBox(String str, int status) {
        int i = JOptionPane.showConfirmDialog(this,str,"เตือน",JOptionPane.YES_NO_OPTION);
        return (i==JOptionPane.YES_OPTION);
    }
    
    
    /*
     * สำหรับซ่อนหน้าจอไม่ให้ไมีการบันทึกข้อมูลได้
     */
    private void setEnableAll(boolean var)
    {
        jComboBoxLocalType.setEnabled(var);
        jTextFieldCaseDeath.setEnabled(var);
        jTextFieldPregnantWeeks.setEnabled(var);
        jCheckBoxPregnant.setEnabled(var);
        jLabelHN.setEnabled(var);
        jLabelVisit.setEnabled(var);
        jTextFieldCdeatha.setEnabled(var);
        jTextFieldCdeathb.setEnabled(var);
        jTextFieldCdeathc.setEnabled(var);
        jTextFieldCdeathd.setEnabled(var);
        jTextFieldOdiseae.setEnabled(var);
        dateComboBoxDdeath.setEnabled(var);
        jButtonSave.setEnabled(true);
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabelPlaceDeath = new javax.swing.JLabel();
        jComboBoxLocalType = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabelHN = new javax.swing.JLabel();
        jLabelVisit = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jButtonDel = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldOdiseae = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        dateComboBoxDdeath = new com.hospital_os.utility.DateComboBox();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldTimeDeath = new com.hospital_os.utility.TimeTextField();
        jTextFieldCdeatha = new com.hosv3.gui.component.HosComboBox();
        jTextFieldCdeathb = new com.hosv3.gui.component.HosComboBox();
        jTextFieldCdeathc = new com.hosv3.gui.component.HosComboBox();
        jTextFieldCdeathd = new com.hosv3.gui.component.HosComboBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabelCaseDeath = new javax.swing.JLabel();
        jCheckBoxPregnant = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldPregnantWeeks = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCaseDeath = new com.hosv3.gui.component.HosComboBox();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListDeath = new com.hosv3.gui.component.HJTableSort();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButtonSearch = new javax.swing.JButton();
        jCheckBoxSearchByDate = new javax.swing.JCheckBox();
        hNTextFieldSearchHN = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        dateComboBoxFrom = new com.hospital_os.utility.DateComboBox();
        dateComboBoxTo = new com.hospital_os.utility.DateComboBox();
        jLabelStatus = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setMinimumSize(new java.awt.Dimension(550, 131));
        jPanel1.setPreferredSize(new java.awt.Dimension(550, 131));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Diag_Death_Detail"));
        jPanel4.setMinimumSize(new java.awt.Dimension(340, 100));
        jPanel4.setPreferredSize(new java.awt.Dimension(340, 100));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabelPlaceDeath.setText("PlaceDeath");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(jLabelPlaceDeath, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jComboBoxLocalType, gridBagConstraints);

        jLabel8.setText("HN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jLabel8, gridBagConstraints);

        jLabel9.setText("VN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jLabel9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jLabelHN, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel3.add(jLabelVisit, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(jPanel3, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jButtonSave.setText("บันทึก");
        jButtonSave.setMaximumSize(new java.awt.Dimension(72, 26));
        jButtonSave.setMinimumSize(new java.awt.Dimension(72, 26));
        jButtonSave.setPreferredSize(new java.awt.Dimension(72, 26));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButtonSave, gridBagConstraints);

        jButtonCancel.setText("ยกเลิก");
        jButtonCancel.setMaximumSize(new java.awt.Dimension(72, 26));
        jButtonCancel.setMinimumSize(new java.awt.Dimension(72, 26));
        jButtonCancel.setPreferredSize(new java.awt.Dimension(72, 26));
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel2.add(jButtonCancel, gridBagConstraints);

        jButtonDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDel.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonDel.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonDel.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel2.add(jButtonDel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jPanel2, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel4.add(jPanel7, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel13.setText("cdeath_a");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel8.add(jLabel13, gridBagConstraints);

        jLabel14.setText("cdeath_b");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel8.add(jLabel14, gridBagConstraints);

        jLabel15.setText("cdeath_c");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel8.add(jLabel15, gridBagConstraints);

        jLabel16.setText("cdeath_d");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel8.add(jLabel16, gridBagConstraints);

        jLabel17.setText("odiseae");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel8.add(jLabel17, gridBagConstraints);

        jTextFieldOdiseae.setMinimumSize(new java.awt.Dimension(200, 21));
        jTextFieldOdiseae.setPreferredSize(new java.awt.Dimension(200, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel8.add(jTextFieldOdiseae, gridBagConstraints);

        jPanel11.setLayout(new java.awt.GridBagLayout());

        jLabel12.setText("ddeath");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel11.add(jLabel12, gridBagConstraints);

        dateComboBoxDdeath.setMinimumSize(new java.awt.Dimension(100, 21));
        dateComboBoxDdeath.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel11.add(dateComboBoxDdeath, gridBagConstraints);

        jLabel6.setText("TimeDeath");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel11.add(jLabel6, gridBagConstraints);

        jTextFieldTimeDeath.setToolTipText("");
        jTextFieldTimeDeath.setMaximumSize(new java.awt.Dimension(99, 21));
        jTextFieldTimeDeath.setMinimumSize(new java.awt.Dimension(40, 21));
        jTextFieldTimeDeath.setName("timeTextFieldTimeAppointment"); // NOI18N
        jTextFieldTimeDeath.setPreferredSize(new java.awt.Dimension(40, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel11.add(jTextFieldTimeDeath, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel8.add(jPanel11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 0, 0);
        jPanel8.add(jTextFieldCdeatha, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 0, 0);
        jPanel8.add(jTextFieldCdeathb, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 0, 0);
        jPanel8.add(jTextFieldCdeathc, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 0, 0);
        jPanel8.add(jTextFieldCdeathd, gridBagConstraints);

        jLabel18.setText("( ICD-10 )");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel8.add(jLabel18, gridBagConstraints);

        jLabel19.setText("( ICD-10 )");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel8.add(jLabel19, gridBagConstraints);

        jLabel20.setText("( ICD-10 )");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel8.add(jLabel20, gridBagConstraints);

        jLabel21.setText("( ICD-10 )");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel8.add(jLabel21, gridBagConstraints);

        jLabel22.setText("( ICD-10 )");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel8.add(jLabel22, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(jPanel8, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabelCaseDeath.setText("สาเหตุการตาย (ICD-10)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel6.add(jLabelCaseDeath, gridBagConstraints);

        jCheckBoxPregnant.setText("DeathPregnant");
        jCheckBoxPregnant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPregnantActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel6.add(jCheckBoxPregnant, gridBagConstraints);

        jLabel2.setText("PregnancyLength");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel6.add(jLabel2, gridBagConstraints);

        jTextFieldPregnantWeeks.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldPregnantWeeks.setMinimumSize(new java.awt.Dimension(40, 21));
        jTextFieldPregnantWeeks.setPreferredSize(new java.awt.Dimension(40, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel6.add(jTextFieldPregnantWeeks, gridBagConstraints);

        jLabel1.setText("weeks");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel6.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 0, 0);
        jPanel6.add(jTextFieldCaseDeath, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel4, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Diag_Death_Search"));
        jPanel5.setMinimumSize(new java.awt.Dimension(280, 90));
        jPanel5.setPreferredSize(new java.awt.Dimension(280, 90));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel9.setMinimumSize(new java.awt.Dimension(220, 90));
        jPanel9.setPreferredSize(new java.awt.Dimension(220, 90));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(100, 24));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 404));

        jTableListDeath.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableListDeath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableListDeathMouseReleased(evt);
            }
        });
        jTableListDeath.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableListDeathKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableListDeath);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel5.add(jPanel9, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("ชื่อ HN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 2);
        jPanel10.add(jLabel4, gridBagConstraints);

        jButtonSearch.setText("Search");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 2);
        jPanel10.add(jButtonSearch, gridBagConstraints);

        jCheckBoxSearchByDate.setSelected(true);
        jCheckBoxSearchByDate.setText("Date");
        jCheckBoxSearchByDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSearchByDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel10.add(jCheckBoxSearchByDate, gridBagConstraints);

        hNTextFieldSearchHN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hNTextFieldSearchHNActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel10.add(hNTextFieldSearchHN, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        dateComboBoxFrom.setMinimumSize(new java.awt.Dimension(90, 21));
        dateComboBoxFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxFromActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 2);
        jPanel12.add(dateComboBoxFrom, gridBagConstraints);

        dateComboBoxTo.setMinimumSize(new java.awt.Dimension(90, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 10, 1, 2);
        jPanel12.add(dateComboBoxTo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel10.add(jPanel12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel5.add(jPanel10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel5, gridBagConstraints);

        jLabelStatus.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabelStatus.setMaximumSize(new java.awt.Dimension(4, 24));
        jLabelStatus.setMinimumSize(new java.awt.Dimension(4, 20));
        jLabelStatus.setOpaque(true);
        jLabelStatus.setPreferredSize(new java.awt.Dimension(4, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jLabelStatus, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hNTextFieldSearchHNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hNTextFieldSearchHNActionPerformed
        searchDeath();
        if(this.jTableListDeath.getRowCount()>0)
        {
            jTableListDeath.setRowSelectionInterval(0,0);
            this.selectListDeath();
        }
    }//GEN-LAST:event_hNTextFieldSearchHNActionPerformed

    private void jCheckBoxSearchByDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSearchByDateActionPerformed
        boolean b = this.jCheckBoxSearchByDate.isSelected();
        this.dateComboBoxFrom.setEnabled(b);
        this.dateComboBoxTo.setEnabled(b);
        searchDeath();
        if(this.jTableListDeath.getRowCount()>0)
        {
            jTableListDeath.setRowSelectionInterval(0,0);
            this.selectListDeath();
        }
    }//GEN-LAST:event_jCheckBoxSearchByDateActionPerformed

    private void dateComboBoxFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxFromActionPerformed
//        searchDeath();
//        if(this.jTableListDeath.getRowCount()>0)
//        {
//            jTableListDeath.setRowSelectionInterval(0,0);
//            this.selectListDeath();
//        }
    }//GEN-LAST:event_dateComboBoxFromActionPerformed

    private void jTableListDeathKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableListDeathKeyReleased
        if(evt.getKeyCode()==evt.VK_UP || evt.getKeyCode()==evt.VK_DOWN)
            selectListDeath();
    }//GEN-LAST:event_jTableListDeathKeyReleased

    private void jCheckBoxPregnantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPregnantActionPerformed
        if(jCheckBoxPregnant.isSelected()){
            jTextFieldPregnantWeeks.setEnabled(true);
        }
        else{
            jTextFieldPregnantWeeks.setEnabled(false);
            jTextFieldPregnantWeeks.setText("");
        }
    }//GEN-LAST:event_jCheckBoxPregnantActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        if(checkAddData()){
            getDeath(theDeath);        
            this.actionCommand = true;
            if(this.mode_not_save){
                dispose();
            }
            else{
                theDiagnosisControl.saveDeath(theDeath,this);
                this.searchDeath();
            }
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed
    private void jTableListDeathMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListDeathMouseReleased
        selectListDeath();
    }//GEN-LAST:event_jTableListDeathMouseReleased

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        searchDeath();
        if(this.jTableListDeath.getRowCount()>0)
        {
            jTableListDeath.setRowSelectionInterval(0,0);
            this.selectListDeath();
        }
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jButtonDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelActionPerformed
        deleteDeath();
    }//GEN-LAST:event_jButtonDelActionPerformed
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        closeDialog = true;
        dispose();
    }//GEN-LAST:event_closeDialog
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.utility.DateComboBox dateComboBoxDdeath;
    private com.hospital_os.utility.DateComboBox dateComboBoxFrom;
    private com.hospital_os.utility.DateComboBox dateComboBoxTo;
    private javax.swing.JTextField hNTextFieldSearchHN;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonDel;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JCheckBox jCheckBoxPregnant;
    private javax.swing.JCheckBox jCheckBoxSearchByDate;
    private javax.swing.JComboBox jComboBoxLocalType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCaseDeath;
    private javax.swing.JLabel jLabelHN;
    private javax.swing.JLabel jLabelPlaceDeath;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelVisit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private com.hosv3.gui.component.HJTableSort jTableListDeath;
    private com.hosv3.gui.component.HosComboBox jTextFieldCaseDeath;
    private com.hosv3.gui.component.HosComboBox jTextFieldCdeatha;
    private com.hosv3.gui.component.HosComboBox jTextFieldCdeathb;
    private com.hosv3.gui.component.HosComboBox jTextFieldCdeathc;
    private com.hosv3.gui.component.HosComboBox jTextFieldCdeathd;
    private javax.swing.JTextField jTextFieldOdiseae;
    private javax.swing.JTextField jTextFieldPregnantWeeks;
    private com.hospital_os.utility.TimeTextField jTextFieldTimeDeath;
    // End of variables declaration//GEN-END:variables
    /*
     *มันเป็น ทั้ง GO และ OG
     */
    private void getDeath(Death theDeath)
    { 
       if(theDeath==null){
            Constant.println("private void getDeath(Death theDeath) death is null");
            return;
       }
        // Somprasong cdeath เก็บเฉพาะรหัส icd10 ไปม่ใช่ PK ของ table icd10 จึงต้องทำแบบนี้
        String[] details = jTextFieldCaseDeath.getDetail().split(":");
        theDeath.cdeath = details[0];
        theDeath.pdeath = ComboboxModel.getCodeComboBox(jComboBoxLocalType);
        theDeath.ddeath = dateComboBoxDdeath.getText()+","+jTextFieldTimeDeath.getText();
        theDeath.cdeath_a = getTextCombo(jTextFieldCdeatha.getSelectedItem());
        theDeath.cdeath_b = getTextCombo(jTextFieldCdeathb.getSelectedItem());
        theDeath.cdeath_c = getTextCombo(jTextFieldCdeathc.getSelectedItem());
        theDeath.cdeath_d = getTextCombo(jTextFieldCdeathd.getSelectedItem());
        theDeath.odiseae = jTextFieldOdiseae.getText();
        if(jCheckBoxPregnant.isSelected()){
            theDeath.nogreg="1";
            theDeath.wpreg = jTextFieldPregnantWeeks.getText();
        }
        else{
            theDeath.nogreg="0";
            theDeath.wpreg ="";
        }
        theDeath.active ="1";
        theDeath.user_record = theHO.theEmployee.getObjectId();
   }
   private String getTextCombo(Object obj){
        if(obj==null)
            return "";
        try{    
            ICD10 icd10 = (ICD10) obj;
            return icd10.getObjectId();
        }catch(Exception e){
            
        }
        return "";
   }
    /*
     *เก็ชข้อมูลจาก object ที่ get ค่าไปแสดงใน form
     *input  : 
     *output :
     */
    private void setDeath(Death d)
    {
        if(d==null || theFamily==null)
        {
            jTextFieldCaseDeath.setText("");
            jTextFieldPregnantWeeks.setText("");
            jTextFieldCdeatha.setText("");
            jTextFieldCdeathb.setText("");
            jTextFieldCdeathc.setText("");
            jTextFieldCdeathd.setText("");
            jTextFieldOdiseae.setText("");
            return;
        }
        theDeath = d;
        ICD10 icd10 = theDiagnosisControl.getICD10ByNumber(theDeath.cdeath);
        jTextFieldCaseDeath.setText(icd10==null?"":icd10.getObjectId());
        jTextFieldCdeatha.setText(theDeath.cdeath_a);
        jTextFieldCdeathb.setText(theDeath.cdeath_b);
        jTextFieldCdeathc.setText(theDeath.cdeath_c);
        jTextFieldCdeathd.setText(theDeath.cdeath_d);
        jTextFieldOdiseae.setText(theDeath.odiseae);
        if(theDeath.ddeath.length()==10){
            jTextFieldTimeDeath.setText("");
        }
        else if(theDeath.ddeath.length()==16) {
            jTextFieldTimeDeath.setText(theDeath.ddeath.substring(11,16));
        }
        dateComboBoxDdeath.setText(DateUtil.convertFieldDate(theDeath.ddeath));
        Gutil.setGuiData(jComboBoxLocalType, theDeath.pdeath);
        if(theDeath.nogreg!=null && theDeath.nogreg.equals("1")){
            jCheckBoxPregnant.setSelected(true);
            jTextFieldPregnantWeeks.setText(theDeath.wpreg);
            return;
        }
        jCheckBoxPregnant.setSelected(false);
        jTextFieldPregnantWeeks.setText("");
    }
     /*
     *ค้นหาข้อมูล Death
     *input : hn dateFrom dateTo
     *outup : ผลการค้นหา Vector vDeath
     */
    private void searchDeath()
    {
        String dateFrom = dateComboBoxFrom.getText();
        String dateTo = dateComboBoxTo.getText();
        String hn = hNTextFieldSearchHN.getText(); 
        boolean by_date = this.jCheckBoxSearchByDate.isSelected();
        if(!by_date)
            vDeath = theDiagnosisControl.listDeath(hn);
        else
            vDeath = theDiagnosisControl.listDeath(dateFrom, dateTo, hn);
        setTableListDeath(vDeath);
    }
      /*
     *แสดงข้อมูลการค้นหาข้อมูลการตาย โดยนำ vector มาแสดง
     *input  :vector vDeath
     *output :
     */
    private void setTableListDeath(Vector vdeath)
    {   
        TaBleModel tm ;
        
        if(vdeath ==null ||  vdeath.isEmpty())
        {
            tm = new TaBleModel(col_jTableListDeath,0);
            jTableListDeath.setModel(tm);
            return;
        }
        tm = new TaBleModel(col_jTableListDeath,vdeath.size());
        for(int i=0; i<vdeath.size(); i++)
        {
            Death aDeath = (Death)vdeath.get(i);
            tm.setValueAt(aDeath.family_name,i,0);
            tm.setValueAt(aDeath.vn, i, 1);
            tm.setValueAt(DateUtil.getDateFromText(aDeath.ddeath),i,2);
            
            jTableListDeath.setModel(tm);
        }       
        
        jTableListDeath.getColumnModel().getColumn(0).setWidth(50);
        jTableListDeath.getColumnModel().getColumn(1).setWidth(20);
        jTableListDeath.getColumnModel().getColumn(1).setCellRenderer(vnRender);
        jTableListDeath.getColumnModel().getColumn(2).setWidth(50);
        jTableListDeath.getColumnModel().getColumn(2).setCellRenderer(dateRender);
    }
    
    private void selectListDeath()
    {
        row = jTableListDeath.getSelectedRow();
        if(vDeath == null || vDeath.isEmpty() || row<0) return;
        theDeath = (Death)vDeath.get(row);
        theFamily = thePatientControl.readFamilyByFamilyIdRet(theDeath.family_id);
        thePatient = thePatientControl.readPatientByPatientIdRet(theDeath.patient_id);
        theVisit = theVisitControl.readVisitByVnRet(theDeath.vn);
        setDeath(thePatient,theVisit,theDeath,theFamily);
//        setEnableAll(true);
    }
    
    /*
     * ทำการลบข้อมูลการตาย
     *input : object death
     */
    private void deleteDeath(){
        boolean ret = theDiagnosisControl.deleteDeath(theDeath,theVisit,thePatient,theFamily,this);
        if(!ret)
            return ;
        jButtonSave.setEnabled(false);
        this.searchDeath();
        if(this.jTableListDeath.getRowCount()>0)
        {
            jTableListDeath.setRowSelectionInterval(0,0);
//            this.selectListDeath();
            this.setDeath(null, null, null, null);
        }
        else{
            this.setDeath(null, null, null, null);
        }
        
    }
/*-----------------------------------------------------------*/ 
    
    /* Jao
     * รับ Object ที่มาจาก PCU
     * 07/04/2549
     */
    private void setDeath(Patient p,Visit v,Death d,Family fm)
    {
        theVisit = v;
        thePatient = p;
        theFamily = fm;
        setDeath(d);
        jLabelVisit.setText("");
        jCheckBoxPregnant.setSelected(false);
        if(theVisit!=null){
            jLabelVisit.setText(theLookupControl.getRenderTextVN(v.vn));
            jCheckBoxPregnant.setSelected(theVisit.pregnant.equals("1"));
        }
        String patient_name = "";
        if(thePatient!=null){
            patient_name += theLookupControl.getRenderTextHN(thePatient.hn) + " " ;
        }
        if(theFamily!=null){
            patient_name += theFamily.patient_name + " " + theFamily.patient_last_name ;//+
                    //": " + Constant.getTextBundle("ยังไม่เคยมารับบริการ"); // Somprasong comment use for what? 251209
        }
        // SOmprasong 251209 comment สลับตำแหน่งข้อความ
//        if(thePatient!=null){
//            patient_name += " HN:" + theLookupControl.getRenderTextHN(thePatient.hn) ;
//        }
        jLabelHN.setText(patient_name);
        if(theFamily!=null){
            jCheckBoxPregnant.setEnabled(theFamily.f_sex_id.equals(Sex.isWOMAN()));
            jTextFieldPregnantWeeks.setEnabled(theFamily.f_sex_id.equals(Sex.isWOMAN()));   
        }
        
        if(jCheckBoxPregnant.isSelected())
            jTextFieldPregnantWeeks.setEnabled(true);
        else
            jTextFieldPregnantWeeks.setEnabled(false);
        
//        String cancel = "ข้อมูลถูกยกเลิก";
//        if(theDeath.active.equals("0"))
//            jLabelHN.setText(jLabelHN.getText() + " " + Constant.getTextBundle(cancel));
        
    }  
    
    public static boolean showDialog(HosControl hc,UpdateStatus us
        ,Visit visit)//, Office off)
    {
        DialogDeath dlg = new DialogDeath(hc,us);//,off);
        return dlg.showDialog(hc.theHO.theFamily,hc.theHO.thePatient, hc.theHO.theVisit);
    }
    public boolean showDialog(Death dt)//, Office off)
    {
        mode_not_save = true;
        thePatient = theHO.thePatient;
        theVisit = theHO.theVisit;
        theFamily = theHO.theFamily;
        this.setStatus("", UpdateStatus.NORMAL);
        setDeath(theHO.thePatient,theHO.theVisit,dt,theHO.theFamily);
        setVisible(true);
        setEnableAll(true);
        return actionCommand;
    }
    public boolean showDialog(Family fm,Patient pt,Visit vs)//, Office off)
    {
        thePatient = pt;
        theVisit = vs;
        theFamily = fm;
        this.setStatus("", UpdateStatus.NORMAL);
        Vector v = null;
        if(thePatient!=null) {
            hNTextFieldSearchHN.setText(thePatient.hn);
            this.jCheckBoxSearchByDate.setSelected(false);
            jCheckBoxSearchByDateActionPerformed(null);
        }
        else if(theFamily!=null) {
            hNTextFieldSearchHN.setText(theFamily.patient_name);
            this.jCheckBoxSearchByDate.setSelected(false);
            jCheckBoxSearchByDateActionPerformed(null);
        }
        this.searchDeath();
        if(jTableListDeath.getRowCount()==0)
        {
            if(pt!=null)
                this.setDeath(pt,vs,theHO.initDeath(),fm);
        }
        else{
            jTableListDeath.setRowSelectionInterval(0,0);
            this.selectListDeath();
        }
        
        setVisible(true);
        setEnableAll(true);
        if(actionCommand)
              return true;

       return false;
    }
    
    /*  Jao
     *  PCU จะใช้ Function นีเรียก Dialog
     *  07/04/2549
     */
    public boolean showDialogForPCU(Death de,Family fm)//, Office off)
    {        
        setDeath(null,null,de,fm);
        setSize(640,480);
        setTitle(Constant.getTextBundle("บันทึกข้อมูลการตาย"));
        setLanguage("");
        Toolkit thekit = getToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//thekit.getScreenSize();
        setLocation((screenSize.width-getSize().width)/2, (screenSize.height-getSize().height)/2);              
        setVisible(true);
        setEnableAll(true);
        if(actionCommand)
        {     //off.setObjectId(dlg.theOfficeNow.getObjectId());   
             // off.name = dlg.theOfficeNow.name;
              return true;
        }
       System.gc();
       return false;
    }
    
    private void setComboBoxEdit(boolean b)
    {   dateComboBoxDdeath.setEditable(b);
        dateComboBoxFrom.setEditable(b);
        dateComboBoxTo.setEditable(b);
    }
    
    public void setLanguage(String msg)
    {
        GuiLang.setLanguage(jCheckBoxSearchByDate);//วันที่ค้นหา
	GuiLang.setLanguage(jButtonSearch);//ปุ่มค้นหา
	GuiLang.setLanguage(jButtonSave);//ปุ่มบันทึก
	GuiLang.setLanguage(jLabelPlaceDeath);//สถานที่ตายใน-นอกโรงพยาบาล
	GuiLang.setLanguage(jLabelCaseDeath);//สาเหตุการตาย
	GuiLang.setLanguage(jLabel12);//วันที่ตาย
        GuiLang.setLanguage(jLabel13);//โรคที่เป็นสาเหตุการตาย a 
        GuiLang.setLanguage(jLabel14);//โรคที่เป็นสาเหตุการตาย b
        GuiLang.setLanguage(jLabel15);//โรคที่เป็นสาเหตุการตาย c
        GuiLang.setLanguage(jLabel16);//โรคที่เป็นสาเหตุการตาย d
        GuiLang.setLanguage(jLabel17);//โรคหรือภาวะอื่นทีเป็นเหตุหนุน
	GuiLang.setLanguage(jCheckBoxPregnant);//ผู้เสียชีวิตตั้งครรภ์หรือไม่
        GuiLang.setLanguage(jLabel4);
	GuiLang.setLanguage(jLabel8);
        GuiLang.setLanguage(jLabel9);
        GuiLang.setLanguage(jLabel2);//จำนวนตั้งครรภ์
        GuiLang.setLanguage(jLabel1);//สับดาห์
        GuiLang.setLanguage(jButtonCancel);
        GuiLang.setLanguage(col_jTableListDeath);
        GuiLang.setTextBundle(jPanel4);
        GuiLang.setTextBundle(jPanel5);
        GuiLang.setLanguage(jLabel6);
     }  

    private boolean checkAddData() {
        if(jTextFieldCaseDeath.getText().equals("")){
            setStatus("กรุณาระบุสาเหตุการตาย (ICD-10)", UpdateStatus.WARNING);
            return false;
        }
        return true;
    }
}
