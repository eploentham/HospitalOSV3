/*
 * DialogAccident.java
 *
 * Created on 9 ธันวาคม 2546, 14:14 น.
 */
package com.hosv3.gui.dialog;
import java.util.*;
import java.awt.*;
import javax.swing.*;

import com.hosv3.control.*;
import com.hosv3.control.lookup.*;
import com.hosv3.utility.connection.*;
import com.hosv3.utility.*;
import com.hosv3.object.*;

import com.hospital_os.object.*; 
import com.hospital_os.utility.TaBleModel;
import com.hospital_os.utility.Gutil;
import com.hosv3.utility.Constant;
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.CellRendererHos;
  
/**
 *
 * @author amp
 */
public class DialogReferIn extends javax.swing.JFrame implements UpdateStatus{

    HosObject theHO;
    HosControl theHC;
    public boolean actionCommand = false;
    JFrame aMain;
    LookupControl theLookupControl;    
    VisitControl theVisitControl;
    SetupControl theSetupControl;
    PatientControl thePatientControl;
    SystemControl theSystemControl;
    OrderControl theOrderControl;//tuk: 03/08/2549  เพิ่มเพื่อดึงรายการ Lab เข้ามา
    Refer theRefer;
    Visit theVisit;
    Patient thePatient;
    String officRefer;
    Vector vRefer;
    int row=0;
    private String[] col_jTableListRefer = {"HN","RN","วันที่"};
    // Render Hn ให้แสดงเป็นแบบสั้น sumo 26/7/2549
    CellRendererHos hnRender = new CellRendererHos(CellRendererHos.HN);

    private HosDialog theHD;

    private DialogOffice theDialogOffice;
    ///////////////////////////////////////////////////////////////////
   
    /** Creates new form DialogAccident */
    public DialogReferIn(HosControl hc,UpdateStatus us) 
    {
        aMain = us.getJFrame();
        theHC = hc;
        theHO = theHC.theHO;
        
        theLookupControl = hc.theLookupControl;        
        theVisitControl = hc.theVisitControl;
        theSetupControl = hc.theSetupControl;
        thePatientControl = hc.thePatientControl;
        theSystemControl = hc.theSystemControl;
        hnRender = new CellRendererHos(CellRendererHos.HN,theLookupControl.getSequenceDataHN().pattern);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/hospital_os/images/refer.gif")));
        initComponents();      
//        jComboBoxHosRefer.setPauseMode(true);
        setLanguage("");        
        setInit();
        searchRefer();
    }
    
    public void setHosDialog(HosDialog hd){
        theHD = hd;
    }
    
    public void setInit()
    {
        jTextFieldDoctorRefer.setVisible(false);
        jComboBoxDoctorRefer.setVisible(true);
        jLabel2.setVisible(false);
        hNTextFieldHnRefer.setVisible(false);
        ComboboxModel.initComboBox(jComboBoxDoctorRefer, theLookupControl.listDoctor());
        ComboboxModel.initComboBox(jComboBoxResultDoctorResult, theLookupControl.listDoctor());
        ComboboxModel.initComboBox(jComboBoxReferCause, theLookupControl.listReferCause());
        Constant.println("public void setInit(){");
        jComboBoxHosRefer.setControl(new OfficeLookup(theHC.theLookupControl),false);
//        jComboBoxHosRefer.setAutoRefresh(false);
//        jComboBoxHosRefer.refresh();
        dateComboBoxResultDateResult.setEditable(true);
        dateComboBoxFrom.setEditable(true);
        dateComboBoxTo.setEditable(true);
        jTextFieldAge.setEnabled(false);
        jTextFieldSex.setEnabled(false);
        hNTextFieldHnRefer.setEditable(false);
        jTextFieldReferName.setEnabled(false);
        
        setSize(640,520);
        setTitle(Constant.getTextBundle("การ ส่ง/รับ ผู้ป่วยไปรับการตรวจ"));
        Toolkit thekit = getToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-getSize().width)/2, (screenSize.height-getSize().height)/2);
    }
    
        //////////////////////////////////////////////////////////////////////////
    /**
     *dialog ที่ใช้ในการส่งข้อความเตื่อนผู้ใช้
     */
     public void setStatus(String str, int status) {   
         
        ThreadStatus theTT = new ThreadStatus(this,this.jLabelStatus);
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
     
    private void clearReferFormAll(){
        theVisit = null;
        theRefer = null;
        hNTextFieldHnRefer.setText("");
        jTextFieldReferName.setText("");
        this.jTextFieldAge.setText("");
        jTextFieldSex.setText("");
        jTextFieldReferNumber.setText("");
        jTextFieldNearLocation.setText("");        
        jCheckBoxRfinTreatment.setSelected(false);
        jCheckBoxRfinLab.setSelected(false);
        jCheckBoxRfinObserve.setSelected(false);
        jCheckBoxRfinResult.setSelected(false);
        jRadioButtonTypeReferIn.setSelected(false);
        jRadioButtonTypeReferOut.setSelected(false);
        jTextAreaResultOtherDetail.setText("");
        jTextAreaHistoryCurrent.setText("");
        jTextAreaHistoryFamily.setText("");
        jTextAreaHistoryLab.setText("");
        jTextAreaTreatment.setText("");
        jTextFieldFirstDx.setText("");
        jTextFieldCauseRefer.setText("");
        jTextFieldDoctorRefer.setText("");
        jRadioButtonInfectiousYes.setSelected(false);
        jRadioButtonInfectiousNo.setSelected(false);
        //แถบผลการ Refer
        jTextFieldResultFinalDx.setText("");
        jTextAreaResultOrder.setText("");
        jTextAreaResultLab.setText("");
        jTextAreaResultContinueTreatment.setText("");
        jTextFieldResultDoctorResult.setText("");   
        jComboBoxReferCause.setSelectedIndex(0);
        jComboBoxHosRefer.setText(theHO.theSite.off_id);
    }
    
 
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroupTypeRefer = new javax.swing.ButtonGroup();
        buttonGroupInfectious = new javax.swing.ButtonGroup();
        buttonGroupIO = new javax.swing.ButtonGroup();
        jLabelStatus = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel15 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListRefer = new com.hosv3.gui.component.HJTableSort();
        jPanel2 = new javax.swing.JPanel();
        jCheckBoxStatusComplete = new javax.swing.JCheckBox();
        jRadioButtonSearchReferIn = new javax.swing.JRadioButton();
        jRadioSearchReferOut = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        dateComboBoxFrom = new com.hospital_os.utility.DateComboBox();
        dateComboBoxTo = new com.hospital_os.utility.DateComboBox();
        jCheckBoxSeachDate = new javax.swing.JCheckBox();
        jPanel21 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldSearchReferNumber = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldReferNumber = new javax.swing.JTextField();
        hNTextFieldHnRefer = new com.hospital_os.utility.HNTextField();
        jPanel22 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxHosRefer = new com.hosv3.gui.component.HosComboBoxStd();
        integerTextFieldHosMainCode = new javax.swing.JTextField();
        jButtonHosMain = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldReferName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldSex = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldAge = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldNearLocation = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldCauseRefer = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jComboBoxDoctorRefer = new javax.swing.JComboBox();
        jTextFieldDoctorRefer = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextAreaHistoryCurrent = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextAreaHistoryLab = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextAreaTreatment = new javax.swing.JTextArea();
        jComboBoxReferCause = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextAreaHistoryFamily = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextFieldFirstDx = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jCheckBoxRfinTreatment = new javax.swing.JCheckBox();
        jCheckBoxRfinLab = new javax.swing.JCheckBox();
        jCheckBoxRfinObserve = new javax.swing.JCheckBox();
        jCheckBoxRfinResult = new javax.swing.JCheckBox();
        jRadioButtonTypeReferIn = new javax.swing.JRadioButton();
        jRadioButtonTypeReferOut = new javax.swing.JRadioButton();
        jCheckBoxInfectious = new javax.swing.JCheckBox();
        jRadioButtonInfectiousYes = new javax.swing.JRadioButton();
        jRadioButtonInfectiousNo = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        dateComboBoxDateRefer = new com.hospital_os.utility.DateComboBox();
        jPanel11 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldResultFinalDx = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldResultDoctorResult = new javax.swing.JTextField();
        jComboBoxResultDoctorResult = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaResultOrder = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaResultLab = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaResultContinueTreatment = new javax.swing.JTextArea();
        jCheckBoxResultReferStatus = new javax.swing.JCheckBox();
        jLabel31 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        dateComboBoxResultDateResult = new com.hospital_os.utility.DateComboBox();
        jButtonUpdateResult = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaResultOtherDetail = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jButtonReferSave = new javax.swing.JButton();
        jButtonEXPrintRefer = new javax.swing.JButton();
        jButtonReferCancel = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jButtonDel = new javax.swing.JButton();
        jButtonPrintRefer = new javax.swing.JButton();
        jCheckBoxResultLab = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("null");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabelStatus.setMinimumSize(new java.awt.Dimension(4, 20));
        jLabelStatus.setOpaque(true);
        jLabelStatus.setPreferredSize(new java.awt.Dimension(4, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jLabelStatus, gridBagConstraints);

        jSplitPane1.setBorder(null);
        jSplitPane1.setOneTouchExpandable(true);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("ค้นหารายการ Refer ผู้ป่วย"));
        jPanel15.setLayout(new java.awt.GridBagLayout());

        jPanel19.setPreferredSize(new java.awt.Dimension(253, 408));
        jPanel19.setLayout(new java.awt.GridBagLayout());

        jTableListRefer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableListReferMouseReleased(evt);
            }
        });
        jTableListRefer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableListReferKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableListRefer);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel19.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel15.add(jPanel19, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jCheckBoxStatusComplete.setText("จบกระบวนการ");
        jCheckBoxStatusComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxStatusCompleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jCheckBoxStatusComplete, gridBagConstraints);

        buttonGroupIO.add(jRadioButtonSearchReferIn);
        jRadioButtonSearchReferIn.setText("refer in");
        jRadioButtonSearchReferIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSearchReferInActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jRadioButtonSearchReferIn, gridBagConstraints);

        buttonGroupIO.add(jRadioSearchReferOut);
        jRadioSearchReferOut.setSelected(true);
        jRadioSearchReferOut.setText("refer out");
        jRadioSearchReferOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioSearchReferOutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel2.add(jRadioSearchReferOut, gridBagConstraints);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/Refresh.png"))); // NOI18N
        jButton1.setToolTipText("เรียกดูข้อมูลใหม่");
        jButton1.setMinimumSize(new java.awt.Dimension(26, 26));
        jButton1.setPreferredSize(new java.awt.Dimension(26, 26));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel2.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel15.add(jPanel2, gridBagConstraints);

        jPanel20.setLayout(new java.awt.GridBagLayout());

        dateComboBoxFrom.setMinimumSize(new java.awt.Dimension(90, 21));
        dateComboBoxFrom.setPreferredSize(new java.awt.Dimension(90, 21));
        dateComboBoxFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxFromActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel20.add(dateComboBoxFrom, gridBagConstraints);

        dateComboBoxTo.setMinimumSize(new java.awt.Dimension(90, 21));
        dateComboBoxTo.setPreferredSize(new java.awt.Dimension(90, 21));
        dateComboBoxTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxToActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel20.add(dateComboBoxTo, gridBagConstraints);

        jCheckBoxSeachDate.setSelected(true);
        jCheckBoxSeachDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSeachDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel20.add(jCheckBoxSeachDate, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel15.add(jPanel20, gridBagConstraints);

        jPanel21.setLayout(new java.awt.GridBagLayout());

        jLabel27.setText("เลข Refer/HN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        jPanel21.add(jLabel27, gridBagConstraints);

        jTextFieldSearchReferNumber.setMinimumSize(new java.awt.Dimension(80, 21));
        jTextFieldSearchReferNumber.setPreferredSize(new java.awt.Dimension(80, 21));
        jTextFieldSearchReferNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchReferNumberActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel21.add(jTextFieldSearchReferNumber, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel15.add(jPanel21, gridBagConstraints);

        jSplitPane1.setLeftComponent(jPanel15);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("ข้อมูลทั่วไปของผู้ป่วย"));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("ReferNumber");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel7.add(jLabel1, gridBagConstraints);

        jLabel2.setText("HN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel7.add(jLabel2, gridBagConstraints);

        jTextFieldReferNumber.setMinimumSize(new java.awt.Dimension(75, 22));
        jTextFieldReferNumber.setPreferredSize(new java.awt.Dimension(75, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel7.add(jTextFieldReferNumber, gridBagConstraints);

        hNTextFieldHnRefer.setColumns(9);
        hNTextFieldHnRefer.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel7.add(hNTextFieldHnRefer, gridBagConstraints);

        jPanel22.setLayout(new java.awt.GridBagLayout());

        jLabel6.setText("สถานพยาบาล*");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel22.add(jLabel6, gridBagConstraints);

        jComboBoxHosRefer.setMinimumSize(new java.awt.Dimension(120, 22));
        jComboBoxHosRefer.setPreferredSize(new java.awt.Dimension(120, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel22.add(jComboBoxHosRefer, gridBagConstraints);

        integerTextFieldHosMainCode.setMaximumSize(new java.awt.Dimension(57, 21));
        integerTextFieldHosMainCode.setMinimumSize(new java.awt.Dimension(45, 21));
        integerTextFieldHosMainCode.setPreferredSize(new java.awt.Dimension(45, 21));
        integerTextFieldHosMainCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                integerTextFieldHosMainCodeFocusLost(evt);
            }
        });
        integerTextFieldHosMainCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldHosMainCodeKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 1);
        jPanel22.add(integerTextFieldHosMainCode, gridBagConstraints);

        jButtonHosMain.setText("...");
        jButtonHosMain.setToolTipText("สถานพยาบาลหลัก");
        jButtonHosMain.setMaximumSize(new java.awt.Dimension(22, 22));
        jButtonHosMain.setMinimumSize(new java.awt.Dimension(22, 22));
        jButtonHosMain.setPreferredSize(new java.awt.Dimension(22, 22));
        jButtonHosMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHosMainActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel22.add(jButtonHosMain, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel7.add(jPanel22, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("FName+Lname");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel6.add(jLabel3, gridBagConstraints);

        jTextFieldReferName.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldReferName.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel6.add(jTextFieldReferName, gridBagConstraints);

        jLabel5.setText("Sex");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel6.add(jLabel5, gridBagConstraints);

        jTextFieldSex.setMinimumSize(new java.awt.Dimension(40, 21));
        jTextFieldSex.setPreferredSize(new java.awt.Dimension(40, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel6.add(jTextFieldSex, gridBagConstraints);

        jLabel4.setText("Age");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel6.add(jLabel4, gridBagConstraints);

        jTextFieldAge.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldAge.setMinimumSize(new java.awt.Dimension(40, 21));
        jTextFieldAge.setPreferredSize(new java.awt.Dimension(40, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel6.add(jTextFieldAge, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel7.add(jPanel6, gridBagConstraints);

        jLabel14.setText("NearLocate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jLabel14, gridBagConstraints);

        jTextFieldNearLocation.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldNearLocation.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jTextFieldNearLocation, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel10.add(jPanel7, gridBagConstraints);

        jPanel1.setMinimumSize(new java.awt.Dimension(400, 380));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 380));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("ข้อมูลการรักษา"));
        jPanel3.setMinimumSize(new java.awt.Dimension(280, 380));
        jPanel3.setPreferredSize(new java.awt.Dimension(280, 380));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel7.setText("HistoryFamily");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jLabel7, gridBagConstraints);

        jLabel8.setText("HistoryCurrent");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jLabel8, gridBagConstraints);

        jLabel9.setText("HistoryLab");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jLabel9, gridBagConstraints);

        jLabel10.setText("FirstDx");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jLabel10, gridBagConstraints);

        jLabel11.setText("Treatment");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jLabel11, gridBagConstraints);

        jLabel13.setText("CauseRefer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jLabel13, gridBagConstraints);

        jTextFieldCauseRefer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCauseReferKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 5);
        jPanel3.add(jTextFieldCauseRefer, gridBagConstraints);

        jLabel28.setText("DoctorRefer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jLabel28, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 5);
        jPanel3.add(jComboBoxDoctorRefer, gridBagConstraints);

        jTextFieldDoctorRefer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldDoctorReferKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 5);
        jPanel3.add(jTextFieldDoctorRefer, gridBagConstraints);

        jTextAreaHistoryCurrent.setLineWrap(true);
        jTextAreaHistoryCurrent.setWrapStyleWord(true);
        jTextAreaHistoryCurrent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextAreaHistoryCurrentKeyReleased(evt);
            }
        });
        jScrollPane7.setViewportView(jTextAreaHistoryCurrent);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 5);
        jPanel3.add(jScrollPane7, gridBagConstraints);

        jTextAreaHistoryLab.setLineWrap(true);
        jTextAreaHistoryLab.setWrapStyleWord(true);
        jTextAreaHistoryLab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextAreaHistoryLabKeyReleased(evt);
            }
        });
        jScrollPane8.setViewportView(jTextAreaHistoryLab);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 5);
        jPanel3.add(jScrollPane8, gridBagConstraints);

        jTextAreaTreatment.setLineWrap(true);
        jTextAreaTreatment.setWrapStyleWord(true);
        jTextAreaTreatment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextAreaTreatmentKeyReleased(evt);
            }
        });
        jScrollPane9.setViewportView(jTextAreaTreatment);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 5);
        jPanel3.add(jScrollPane9, gridBagConstraints);

        jComboBoxReferCause.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBoxReferCauseKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 5);
        jPanel3.add(jComboBoxReferCause, gridBagConstraints);

        jLabel30.setText("สาเหตุอื่นๆ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jLabel30, gridBagConstraints);

        jTextAreaHistoryFamily.setLineWrap(true);
        jTextAreaHistoryFamily.setWrapStyleWord(true);
        jTextAreaHistoryFamily.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextAreaHistoryFamilyMouseReleased(evt);
            }
        });
        jScrollPane6.setViewportView(jTextAreaHistoryFamily);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 5);
        jPanel3.add(jScrollPane6, gridBagConstraints);

        jTextFieldFirstDx.setLineWrap(true);
        jTextFieldFirstDx.setWrapStyleWord(true);
        jTextFieldFirstDx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldFirstDxKeyReleased(evt);
            }
        });
        jScrollPane10.setViewportView(jTextFieldFirstDx);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 5);
        jPanel3.add(jScrollPane10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("ลักษณะการ refer"));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jCheckBoxRfinTreatment.setText("TreatmentReceive");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 10, 1, 0);
        jPanel5.add(jCheckBoxRfinTreatment, gridBagConstraints);

        jCheckBoxRfinLab.setText("TreatmentLab");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 10, 1, 0);
        jPanel5.add(jCheckBoxRfinLab, gridBagConstraints);

        jCheckBoxRfinObserve.setText("TreatmentObserve");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 10, 1, 0);
        jPanel5.add(jCheckBoxRfinObserve, gridBagConstraints);

        jCheckBoxRfinResult.setText("TreatmentResult");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 10, 1, 0);
        jPanel5.add(jCheckBoxRfinResult, gridBagConstraints);

        buttonGroupTypeRefer.add(jRadioButtonTypeReferIn);
        jRadioButtonTypeReferIn.setText("Refer In");
        jRadioButtonTypeReferIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTypeReferInActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        jPanel5.add(jRadioButtonTypeReferIn, gridBagConstraints);

        buttonGroupTypeRefer.add(jRadioButtonTypeReferOut);
        jRadioButtonTypeReferOut.setSelected(true);
        jRadioButtonTypeReferOut.setText("Refer Out");
        jRadioButtonTypeReferOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTypeReferOutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        jPanel5.add(jRadioButtonTypeReferOut, gridBagConstraints);

        jCheckBoxInfectious.setText("Infectious");
        jCheckBoxInfectious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxInfectiousActionPerformed(evt);
            }
        });
        jCheckBoxInfectious.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jCheckBoxInfectiousKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(jCheckBoxInfectious, gridBagConstraints);

        buttonGroupInfectious.add(jRadioButtonInfectiousYes);
        jRadioButtonInfectiousYes.setText("InfectiousYes");
        jRadioButtonInfectiousYes.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel5.add(jRadioButtonInfectiousYes, gridBagConstraints);

        buttonGroupInfectious.add(jRadioButtonInfectiousNo);
        jRadioButtonInfectiousNo.setText("InfectiousNo");
        jRadioButtonInfectiousNo.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel5.add(jRadioButtonInfectiousNo, gridBagConstraints);

        jLabel19.setText("DateRefer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel5.add(jLabel19, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 15, 1, 3);
        jPanel5.add(dateComboBoxDateRefer, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel1.add(jPanel5, gridBagConstraints);

        jTabbedPane1.addTab("ReferIn / Out", jPanel1);

        jPanel11.setMinimumSize(new java.awt.Dimension(400, 400));
        jPanel11.setPreferredSize(new java.awt.Dimension(400, 400));
        jPanel11.setLayout(new java.awt.GridBagLayout());

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("สรุปผลการตรวจ"));
        jPanel13.setLayout(new java.awt.GridBagLayout());

        jLabel15.setText("การวินิจฉัย");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel13.add(jLabel15, gridBagConstraints);

        jLabel17.setText("ResultOrder");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel13.add(jLabel17, gridBagConstraints);

        jLabel16.setText("ดำเนินการต่อ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel13.add(jLabel16, gridBagConstraints);

        jLabel12.setText("ผลแลบ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel13.add(jLabel12, gridBagConstraints);

        jTextFieldResultFinalDx.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldResultFinalDx.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel13.add(jTextFieldResultFinalDx, gridBagConstraints);

        jLabel24.setText("แพทย์ผู้ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel13.add(jLabel24, gridBagConstraints);

        jTextFieldResultDoctorResult.setMinimumSize(new java.awt.Dimension(100, 23));
        jTextFieldResultDoctorResult.setPreferredSize(new java.awt.Dimension(100, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel13.add(jTextFieldResultDoctorResult, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel13.add(jComboBoxResultDoctorResult, gridBagConstraints);

        jTextAreaResultOrder.setLineWrap(true);
        jScrollPane2.setViewportView(jTextAreaResultOrder);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel13.add(jScrollPane2, gridBagConstraints);

        jTextAreaResultLab.setLineWrap(true);
        jScrollPane3.setViewportView(jTextAreaResultLab);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel13.add(jScrollPane3, gridBagConstraints);

        jTextAreaResultContinueTreatment.setLineWrap(true);
        jScrollPane4.setViewportView(jTextAreaResultContinueTreatment);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel13.add(jScrollPane4, gridBagConstraints);

        jCheckBoxResultReferStatus.setText("จบกระบวนการ Refer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel13.add(jCheckBoxResultReferStatus, gridBagConstraints);

        jLabel31.setText("สิ่งที่ขอให้");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel13.add(jLabel31, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel23.setText("DateResult");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel8.add(jLabel23, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel8.add(dateComboBoxResultDateResult, gridBagConstraints);

        jButtonUpdateResult.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Refresh16.gif"))); // NOI18N
        jButtonUpdateResult.setToolTipText("ดึงข้อมูลการรักษาปัจจุบัน");
        jButtonUpdateResult.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonUpdateResult.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonUpdateResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateResultActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel8.add(jButtonUpdateResult, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel13.add(jPanel8, gridBagConstraints);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("รายละเอียดอื่น ๆ"));
        jPanel9.setMinimumSize(new java.awt.Dimension(150, 82));
        jPanel9.setPreferredSize(new java.awt.Dimension(150, 82));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jTextAreaResultOtherDetail.setLineWrap(true);
        jTextAreaResultOtherDetail.setMinimumSize(new java.awt.Dimension(280, 45));
        jTextAreaResultOtherDetail.setPreferredSize(new java.awt.Dimension(280, 50));
        jScrollPane5.setViewportView(jTextAreaResultOtherDetail);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(jScrollPane5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridheight = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel13.add(jPanel9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel11.add(jPanel13, gridBagConstraints);

        jTabbedPane1.addTab("ผลการ Refer", jPanel11);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(jTabbedPane1, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jButtonReferSave.setText("Save");
        jButtonReferSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReferSaveActionPerformed(evt);
            }
        });
        jButtonReferSave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButtonReferSaveKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(jButtonReferSave, gridBagConstraints);

        jButtonEXPrintRefer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/preview24.gif"))); // NOI18N
        jButtonEXPrintRefer.setToolTipText("แสดงตัวอย่างใบ Refer");
        jButtonEXPrintRefer.setEnabled(false);
        jButtonEXPrintRefer.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonEXPrintRefer.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonEXPrintRefer.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonEXPrintRefer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEXPrintReferActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jButtonEXPrintRefer, gridBagConstraints);

        jButtonReferCancel.setText("Cancel");
        jButtonReferCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReferCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel4.add(jButtonReferCancel, gridBagConstraints);

        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAdd.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonAdd.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(jButtonAdd, gridBagConstraints);

        jButtonDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDel.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDel.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(jButtonDel, gridBagConstraints);

        jButtonPrintRefer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/print24.gif"))); // NOI18N
        jButtonPrintRefer.setToolTipText("พิมพ์ใบ Refer");
        jButtonPrintRefer.setEnabled(false);
        jButtonPrintRefer.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonPrintRefer.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonPrintRefer.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonPrintRefer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintReferActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        jPanel4.add(jButtonPrintRefer, gridBagConstraints);

        jCheckBoxResultLab.setText("ผลแลป");
        jCheckBoxResultLab.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBoxResultLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxResultLabActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(jCheckBoxResultLab, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel10.add(jPanel4, gridBagConstraints);

        jSplitPane1.setRightComponent(jPanel10);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jSplitPane1, gridBagConstraints);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-712)/2, (screenSize.height-578)/2, 712, 578);
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxResultLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxResultLabActionPerformed
        if(theRefer==null)
            return;
        Vector vrl = null;
        if(this.jCheckBoxResultLab.isSelected())
            vrl = theHC.theResultControl.listResultLabRefer(theHO.vOrderItem);
        
        Refer refer = theHO.getUpdateRefer(theRefer,vrl,true);
        setRefer(refer);
    }//GEN-LAST:event_jCheckBoxResultLabActionPerformed

    private void jButtonHosMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHosMainActionPerformed
        Office office = new Office();
        office.setObjectId(theRefer.office_refer);
        if(theDialogOffice==null)
            theDialogOffice = new DialogOffice(theHC,this,office);
        
        if(theDialogOffice.showDialog(office)) {
            this.jComboBoxHosRefer.setText(office.getObjectId());
            integerTextFieldHosMainCode.setText(office.getObjectId());
        }
    }//GEN-LAST:event_jButtonHosMainActionPerformed

    private void integerTextFieldHosMainCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldHosMainCodeKeyReleased
        if(integerTextFieldHosMainCode.getText().length()==5) {
            Office office = theHC.theLookupControl.readHospitalByCode(integerTextFieldHosMainCode.getText());
            if(office == null)   return;
            if(office.getObjectId()!=null) {
                this.jComboBoxHosRefer.setText(office.getObjectId());
            }
        }
    }//GEN-LAST:event_integerTextFieldHosMainCodeKeyReleased

    private void integerTextFieldHosMainCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integerTextFieldHosMainCodeFocusLost
        Office office = theHC.theLookupControl.readHospitalByCode(integerTextFieldHosMainCode.getText());
        if(office==null) {
            integerTextFieldHosMainCode.setText("");
            this.jComboBoxHosRefer.setText("99999");
            setStatus(Constant.getTextBundle("ไม่พบสถานพยาบาลที่ตรงกับรหัสที่ระบุ")+" "+
                    Constant.getTextBundle("กรุณาตรวจสอบรหัสอีกครั้ง"), UpdateStatus.WARNING);
        } else {
            this.jComboBoxHosRefer.setText(office.getObjectId());
        }
    }//GEN-LAST:event_integerTextFieldHosMainCodeFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.searchRefer();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonUpdateResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateResultActionPerformed
        Vector vrl = theHC.theResultControl.listResultLabRefer(theHO.vOrderItem);
        setRefer(theHO.getUpdateRefer(theRefer,vrl, false));
    }//GEN-LAST:event_jButtonUpdateResultActionPerformed

    private void jButtonPrintReferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintReferActionPerformed
        //สั่งพิมพ์ใบ Refer sumo 21/7/2549
        if(theRefer.refer_out.equals("1"))
            theHC.thePrintControl.printRefer(theRefer, PrintControl.MODE_PRINT);
        else
            theHC.thePrintControl.printReferResult(theRefer, false);
    }//GEN-LAST:event_jButtonPrintReferActionPerformed

    private void jRadioSearchReferOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioSearchReferOutActionPerformed
        this.searchRefer();
    }//GEN-LAST:event_jRadioSearchReferOutActionPerformed

    private void jRadioButtonSearchReferInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSearchReferInActionPerformed
        this.searchRefer();
    }//GEN-LAST:event_jRadioButtonSearchReferInActionPerformed

    private void dateComboBoxToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxToActionPerformed
//        Constant.println("dateComboBoxToActionPerformed");
//        this.searchRefer();
    }//GEN-LAST:event_dateComboBoxToActionPerformed

    private void dateComboBoxFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxFromActionPerformed
//        Constant.println("dateComboBoxFromActionPerformed");
//        this.searchRefer();
    }//GEN-LAST:event_dateComboBoxFromActionPerformed

    private void jButtonReferSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReferSaveActionPerformed
        Refer refer = getRefer();    
        Constant.println("1"+refer.refer_observe);
        boolean ret = theVisitControl.saveReferIn(thePatient,theVisit,refer,this);  
        if(ret == false)
            return;
        setRefer(refer);
        searchRefer();
    }//GEN-LAST:event_jButtonReferSaveActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        Vector vrl = new Vector();
        if(this.jCheckBoxResultLab.isSelected())
            vrl = theHC.theResultControl.listResultLabRefer(theHO.vOrderItem);
        Refer refer = theHO.initReferOut(vrl);
        if(refer==null) {
            this.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return;
        }
        setRefer(refer);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jCheckBoxSeachDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSeachDateActionPerformed
         Constant.println("jCheckBoxSeachDateActionPerformed");
       dateComboBoxFrom.setEnabled(jCheckBoxSeachDate.isSelected());
        dateComboBoxTo.setEnabled(jCheckBoxSeachDate.isSelected());
//        this.searchRefer();
    }//GEN-LAST:event_jCheckBoxSeachDateActionPerformed

    private void jTextFieldSearchReferNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchReferNumberActionPerformed
         Constant.println("jTextFieldSearchReferNumberActionPerformed");
       this.searchRefer();
    }//GEN-LAST:event_jTextFieldSearchReferNumberActionPerformed

    private void jTableListReferKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableListReferKeyReleased
        if(evt.getKeyCode()==evt.VK_UP || evt.getKeyCode()==evt.VK_DOWN){
            row = jTableListRefer.getSelectedRow();
            theRefer = (Refer)vRefer.get(row);
            setRefer(theRefer);
        }
    }//GEN-LAST:event_jTableListReferKeyReleased

    private void jButtonReferSaveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonReferSaveKeyReleased
        Gutil.setTransferCursor(evt,jButtonReferSave,jCheckBoxInfectious,null,null,null);
    }//GEN-LAST:event_jButtonReferSaveKeyReleased

    private void jCheckBoxInfectiousKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCheckBoxInfectiousKeyReleased
        Gutil.setTransferCursor(evt,jCheckBoxInfectious,jTextFieldDoctorRefer,null,jButtonReferSave,null);
    }//GEN-LAST:event_jCheckBoxInfectiousKeyReleased

    private void jTextFieldDoctorReferKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDoctorReferKeyReleased
        Gutil.setTransferCursor(evt,jTextFieldDoctorRefer,jTextFieldCauseRefer,null,jCheckBoxInfectious,null);
    }//GEN-LAST:event_jTextFieldDoctorReferKeyReleased

    private void jTextFieldCauseReferKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCauseReferKeyReleased
        Gutil.setTransferCursor(evt,jTextFieldCauseRefer,jComboBoxReferCause,null,jTextFieldDoctorRefer,null);
    }//GEN-LAST:event_jTextFieldCauseReferKeyReleased

    private void jComboBoxReferCauseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxReferCauseKeyReleased
        Gutil.setTransferCursor(evt,jComboBoxReferCause,jTextAreaTreatment,null,jTextFieldCauseRefer,null);
    }//GEN-LAST:event_jComboBoxReferCauseKeyReleased

    private void jTextAreaTreatmentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaTreatmentKeyReleased
        Gutil.setTransferCursor(evt,jTextAreaTreatment,jTextFieldFirstDx,null,jComboBoxReferCause,null);
    }//GEN-LAST:event_jTextAreaTreatmentKeyReleased

    private void jTextFieldFirstDxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFirstDxKeyReleased
        Gutil.setTransferCursor(evt,jTextFieldFirstDx,jTextAreaHistoryLab,null,jTextAreaTreatment,null);
    }//GEN-LAST:event_jTextFieldFirstDxKeyReleased

    private void jTextAreaHistoryLabKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaHistoryLabKeyReleased
        Gutil.setTransferCursor(evt,jTextAreaHistoryLab,jTextAreaHistoryCurrent,null,jTextFieldFirstDx,null);
    }//GEN-LAST:event_jTextAreaHistoryLabKeyReleased

    private void jTextAreaHistoryCurrentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaHistoryCurrentKeyReleased
        Gutil.setTransferCursor(evt,jTextAreaHistoryCurrent,jTextAreaHistoryFamily,null,jTextAreaHistoryLab,null);
    }//GEN-LAST:event_jTextAreaHistoryCurrentKeyReleased

    private void jTextAreaHistoryFamilyMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaHistoryFamilyMouseReleased
        //Gutil.setTransferCursor(evt,jTextAreaHistoryFamily,null,null,jTextAreaHistoryCurrent,null);
    }//GEN-LAST:event_jTextAreaHistoryFamilyMouseReleased

    private void jCheckBoxStatusCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxStatusCompleteActionPerformed
//        this.searchRefer();
    }//GEN-LAST:event_jCheckBoxStatusCompleteActionPerformed

    private void jButtonDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelActionPerformed
        theVisitControl.deleteReferIn(theRefer);
        clearReferFormAll();
        searchRefer();
    }//GEN-LAST:event_jButtonDelActionPerformed

    private void jButtonReferCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReferCancelActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonReferCancelActionPerformed
    private void jButtonEXPrintReferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEXPrintReferActionPerformed
        //สั่งแสดงตัวอย่างใบ Refer sumo 21/7/2549
        if(theRefer.refer_out.equals("1"))
            theHC.thePrintControl.printRefer(theRefer, PrintControl.MODE_PREVIEW);
        else
            theHC.thePrintControl.printReferResult(theRefer, true);
    }//GEN-LAST:event_jButtonEXPrintReferActionPerformed

	private void jTableListReferMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListReferMouseReleased
        row = jTableListRefer.getSelectedRow();
        theRefer = (Refer)vRefer.get(row);
        setRefer(theRefer);
    }//GEN-LAST:event_jTableListReferMouseReleased

    private void jCheckBoxInfectiousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxInfectiousActionPerformed
        if(jCheckBoxInfectious.isSelected())
        {
            jRadioButtonInfectiousNo.setEnabled(true);
            jRadioButtonInfectiousYes.setEnabled(true);
            jRadioButtonInfectiousNo.setSelected(true);
        }
        else
        {
            jRadioButtonInfectiousNo.setEnabled(false);
            jRadioButtonInfectiousYes.setEnabled(false);
            jRadioButtonInfectiousNo.setSelected(false);
            jRadioButtonInfectiousYes.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxInfectiousActionPerformed
    private void jRadioButtonTypeReferInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTypeReferInActionPerformed
        jRadioButtonTypeReferOutActionPerformed(null);
    }//GEN-LAST:event_jRadioButtonTypeReferInActionPerformed
    private void jRadioButtonTypeReferOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTypeReferOutActionPerformed
        if(jRadioButtonTypeReferOut.isSelected())
        {
             jTextFieldDoctorRefer.setVisible(false);
             jComboBoxResultDoctorResult.setVisible(false);
             jComboBoxDoctorRefer.setVisible(true);
             jTextFieldResultDoctorResult.setVisible(true);
             this.jCheckBoxRfinLab.setEnabled(false);
             this.jCheckBoxRfinObserve.setEnabled(false);
             this.jCheckBoxRfinResult.setEnabled(false);
             this.jCheckBoxRfinTreatment.setEnabled(false);
        }
        else
        {
             jTextFieldDoctorRefer.setVisible(true);
             jComboBoxResultDoctorResult.setVisible(true);
             jComboBoxDoctorRefer.setVisible(false);
             jTextFieldResultDoctorResult.setVisible(false);
             this.jCheckBoxRfinLab.setEnabled(true);
             this.jCheckBoxRfinObserve.setEnabled(true);
             this.jCheckBoxRfinResult.setEnabled(true);
             this.jCheckBoxRfinTreatment.setEnabled(true);
        }
    }                                                            private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-LAST:event_jRadioButtonTypeReferOutActionPerformed
        setVisible(false);//GEN-FIRST:event_formWindowClosing
        dispose();
    }//GEN-LAST:event_formWindowClosing
    private void comboBoxClinic1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxClinic1ActionPerformed
    }//GEN-LAST:event_comboBoxClinic1ActionPerformed
 
  
    
    //////////////////////////////////////////////////////////////////////////
    /*
     *เก็บข้อมูลจาก object ที่ get ค่าไปแสดงใน form
     *input  : 
     *output :
     */
    private Refer getRefer()
    {
        if(theRefer == null)
        {
            this.setStatus("กรุณาเลือกผู้ป่วยที่จะส่งไปรักษาต่อ", UpdateStatus.NORMAL);
            return null;
        }
        theRefer.date_result = dateComboBoxResultDateResult.getText();
        theRefer.final_dx = jTextFieldResultFinalDx.getText();
        theRefer.result_treatment = jTextAreaResultOrder.getText();
        theRefer.result_lab = jTextAreaResultLab.getText();
        theRefer.continue_treatment = jTextAreaResultContinueTreatment.getText();
        theRefer.other_detail = jTextAreaResultOtherDetail.getText();
        boolean is_refer_out = theRefer.refer_out.equals("1");
        if(is_refer_out)//ถ้าเป็น refer out ก็จะเอาข้อมูลแพทย์ที่ส่งผลกลับจาก textfield
        {
            theRefer.doctor_result = jTextFieldResultDoctorResult.getText();
            theRefer.doctor_refer = Gutil.getGuiData(jComboBoxDoctorRefer);
        }
        else
        {
           theRefer.doctor_result = Gutil.getGuiData(jComboBoxResultDoctorResult);
           theRefer.doctor_refer = jTextFieldDoctorRefer.getText();  
        }
        theRefer.refer_complete = Gutil.getText(jCheckBoxResultReferStatus);
        theRefer.refer_number = jTextFieldReferNumber.getText();
        theRefer.near_localtion = jTextFieldNearLocation.getText();
        theRefer.office_refer = jComboBoxHosRefer.getText();
        theRefer.date_refer = dateComboBoxDateRefer.getText();
        theRefer.refer_treatment = Gutil.getText(jCheckBoxRfinTreatment);
        theRefer.refer_observe= Gutil.getText(jCheckBoxRfinObserve);
        theRefer.refer_lab= Gutil.getText(jCheckBoxRfinLab);
        theRefer.refer_result= Gutil.getText(jCheckBoxRfinResult);
        theRefer.history_family = jTextAreaHistoryFamily.getText();
        theRefer.history_current = jTextAreaHistoryCurrent.getText();
        theRefer.history_lab = jTextAreaHistoryLab.getText();
        theRefer.first_dx = jTextFieldFirstDx.getText();
        theRefer.first_treatment = Gutil.CheckReservedWords(jTextAreaTreatment.getText());
        theRefer.cause_refer = jTextFieldCauseRefer.getText();
        theRefer.refer_out = Gutil.getText(jRadioButtonTypeReferOut);
        if(jCheckBoxInfectious.isSelected())
        {
            if(jRadioButtonInfectiousYes.isSelected())
                theRefer.infectious="1";
            else
                theRefer.infectious="2";
        }
        else
            theRefer.infectious="0";
       /////////////////////////////////////////////////////////////////
       theVisit.refer_cause = Gutil.getGuiData(jComboBoxReferCause);
       //////////////////////////////////////////////////////////////////
        return theRefer;
    }
    
       
   //////////////////////////////////////////////////////////////////////////// 
    /**
     * เพื่อใช้ในการ set ค่าให้กับ object (ข้อมูลส่วนการ Refer in หรือ out)
     * input  : date from GUI
     * output : object Refer
     */
    //tuk: 05/08/2549 Pattern ยังมั่วอยู่น่าจะแก้ไขให้ดีกว่านี้ แต่คงต้องใช้เวลา เพราะมันมีเกี่ยวกับการ init ค่าของการ Refer ด้วย
    private void setRefer(Refer refer)
    {
        theRefer = refer;
        //รหัสสถานพยาบาลสำหรับการส่งต่อ
        officRefer = "";
        if(theRefer==null)
        {
            this.clearReferFormAll();
            return;
        }
        theVisit = theVisitControl.readVisitByVidRet(theRefer.vn_id);
        Gutil.setGuiData(jComboBoxReferCause, theVisit.refer_cause);
        ////////////////////////////////////////////////////////////////////////////
        thePatient = thePatientControl.readPatientByPatientIdRet(theRefer.patient_id);
        Patient pt = thePatient;
        if(pt!=null)
        {
            jTextFieldReferName.setText(pt.patient_name + " " + pt.patient_last_name);
            String age = DateUtil.calculateAge(pt.patient_birthday,theHO.date_time);
            jTextFieldSex.setText(theLookupControl.readSexSById(pt.f_sex_id));
            if(!age.equals("0"))
            {
                jTextFieldAge.setText(age);
            }
        }
        else
        {
            jTextFieldReferName.setText(Constant.getTextBundle("ไม่พบข้อมูลผู้ป่วย"));
            //tuk: 02/08/2549 เมื่อกดปุ่ม + ให้ clear ฟอร์มต่าง ๆ 
            this.clearReferFormAll();
            return;
        }
        ////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////
        // เช็คว่ามีข้อมูล Refer หรือไม่ เพื่อเปิดปุ่มพิมพ์ใบ Refer ให้ใช้งาน โดย ojika
        boolean is_refer_out = theRefer.refer_out.equals("1");
        dateComboBoxDateRefer.setText(DateUtil.convertFieldDate(theRefer.date_refer));
        Gutil.setGuiData(jComboBoxDoctorRefer, theRefer.doctor_refer);
        hNTextFieldHnRefer.setText(theLookupControl.getRenderTextHN(theRefer.hn));
        jButtonEXPrintRefer.setEnabled(!theRefer.refer_number.equals(""));
        jButtonPrintRefer.setEnabled(!theRefer.refer_number.equals(""));
        jCheckBoxRfinLab.setSelected(theRefer.refer_lab.equals("1"));
        jCheckBoxRfinObserve.setSelected(theRefer.refer_observe.equals("1"));
        jCheckBoxRfinResult.setSelected(theRefer.refer_result.equals("1"));
        jCheckBoxRfinTreatment.setSelected(theRefer.refer_treatment.equals("1"));
        jComboBoxDoctorRefer.setVisible(is_refer_out);
        if(!theRefer.office_refer.equals(""))
            jComboBoxHosRefer.setText(theRefer.office_refer);
        else
            jComboBoxHosRefer.setText("99999");
        
        jRadioButtonTypeReferIn.setEnabled(theRefer.getObjectId()==null);
        jRadioButtonTypeReferIn.setSelected(!is_refer_out);
        jRadioButtonTypeReferOut.setEnabled(theRefer.getObjectId()==null);
        jRadioButtonTypeReferOut.setSelected(is_refer_out);
        jTextAreaHistoryCurrent.setText(theRefer.history_current);
        jTextAreaHistoryFamily.setText(theRefer.history_family);
        jTextAreaHistoryLab.setText(theRefer.history_lab);
        jTextAreaTreatment.setText(theRefer.first_treatment);
        jTextFieldCauseRefer.setText(theRefer.cause_refer);
        jTextFieldDoctorRefer.setText(theRefer.doctor_refer);
        jTextFieldDoctorRefer.setVisible(!is_refer_out);
        jTextFieldFirstDx.setText(theRefer.first_dx);
        jTextFieldNearLocation.setText(theRefer.near_localtion);
        this.integerTextFieldHosMainCode.setText(theRefer.office_refer);
        this.jComboBoxHosRefer.setText(theRefer.office_refer);
        jTextFieldReferNumber.setText(theRefer.refer_number);
        dateComboBoxResultDateResult.setText(DateUtil.convertFieldDate(theRefer.date_result));
        jCheckBoxResultReferStatus.setSelected(theRefer.refer_complete.equals("1"));
        Gutil.setGuiData(jComboBoxResultDoctorResult, theRefer.doctor_result);
        jComboBoxResultDoctorResult.setVisible(!is_refer_out);
        jTextAreaResultContinueTreatment.setText(theRefer.continue_treatment);
        jTextAreaResultLab.setText(theRefer.result_lab);
        jTextAreaResultOrder.setText(theRefer.result_treatment);
        jTextAreaResultOtherDetail.setText(theRefer.other_detail);
        jTextFieldResultDoctorResult.setText(theRefer.doctor_result);
        jTextFieldResultDoctorResult.setVisible(is_refer_out);
        jTextFieldResultFinalDx.setText(theRefer.final_dx);
        
        if(theRefer.infectious.equals("1"))
        {
            this.jCheckBoxInfectious.setSelected(true);
            jRadioButtonInfectiousYes.setEnabled(true);
            jRadioButtonInfectiousNo.setEnabled(true);
            jRadioButtonInfectiousYes.setSelected(true);
            jRadioButtonInfectiousNo.setSelected(false);
        }
        else if(theRefer.infectious.equals("2"))
        {
            this.jCheckBoxInfectious.setSelected(true);
            jRadioButtonInfectiousYes.setEnabled(true);
            jRadioButtonInfectiousNo.setEnabled(true);
            jRadioButtonInfectiousYes.setSelected(false);
            jRadioButtonInfectiousNo.setSelected(true);
        }
        else
        {
            jCheckBoxInfectious.setSelected(false);
            jRadioButtonInfectiousYes.setEnabled(false);
            jRadioButtonInfectiousNo.setEnabled(false);
        }
        //Aut : 06/11/2006 ให้สถานพยาบาล defualt เป็นสถานพยาบาลที่ติดตั้งโปรแกรม
        if (theRefer.refer_number.equals("")) {            
            this.jComboBoxHosRefer.setText(theHO.theSite.off_id);
        }
        jRadioButtonTypeReferOutActionPerformed(null);

        this.jComboBoxHosRefer.setText("99999");
        this.integerTextFieldHosMainCode.setText("");
        Office office = theHC.theLookupControl.readHospitalByCode(theRefer.office_refer);
        //String hmain = "";
        if(office != null){ 
            this.jComboBoxHosRefer.setText(office.getCode());
            integerTextFieldHosMainCode.setText(office.getCode());
        }        
    }
       
    ///////////////////////////////////////////////////////////////////////////
    /**
     * เพื่อทำการค้นหารายการ Refer ผู้ป่วย
     * input  :  hn ,refernumber,dateFrom ,dateTo
     * output : vRefer
     */
    
    private void searchRefer(){
        String hn = jTextFieldSearchReferNumber.getText();
        String rn = jTextFieldSearchReferNumber.getText();
        String dateFrom = "";
        String dateTo = "";
        String refer_out = Gutil.getText(jRadioSearchReferOut);
        if(this.jCheckBoxSeachDate.isSelected())
        {
            dateFrom = dateComboBoxFrom.getText();
            dateTo = dateComboBoxTo.getText();
        }
        String status = Gutil.getText(jCheckBoxStatusComplete);
        Vector v = theVisitControl.listRefer(hn, rn, dateFrom, dateTo, status, refer_out);               
        setReferV(v);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    /*
     *  เพื่อนำข้อมูลที่ได้จาการค้นหามาแสดงในตารางผลการค้นหา
     *  input  : Vector vRefer
     *  output : 
     */
    private void setReferV(Vector vrefer)
    {
        vRefer = vrefer;
        TaBleModel tm ;
        if(vRefer !=null){
            tm = new TaBleModel(col_jTableListRefer,vRefer.size());
            for(int i=0; i<vRefer.size(); i++ ){
                Refer refer = (Refer)vrefer.get(i);
                tm.setValueAt(refer.hn,i,0);
                tm.setValueAt(refer.refer_number, i, 1);
                tm.setValueAt(DateUtil.convertFieldDate(refer.date_refer),i, 2);
            }  
        }
        else{
            tm = new TaBleModel(col_jTableListRefer,0);
        }
        jTableListRefer.setModel(tm);
        jTableListRefer.getColumnModel().getColumn(0).setCellRenderer(hnRender);
    }
   
    
   /////////////////////////////////////////////////////////////////////////////
 
    public boolean showDialogReferIn(Visit visit)
    {    
        Constant.println("public void showDialogReferIn(Visit visit)");
        this.jCheckBoxSeachDate.setSelected(true);
        this.setRefer(null);
        if(visit!=null)
        {
            this.jTextFieldSearchReferNumber.setText(visit.hn);
            //this.jCheckBoxSeachDate.setSelected(false);
            dateComboBoxFrom.setEnabled(jCheckBoxSeachDate.isSelected());
            dateComboBoxTo.setEnabled(jCheckBoxSeachDate.isSelected());  
            this.searchRefer();
        }
        setVisible(true);
        return true;
    }
  
   ////////////////////////////////////////////////////////////////////////////
   /**
    *เป็นstatic ฟังก์ชันในการเรียก Dialog
    *ใช้เมื่อต้องการให้ Dialog เรียกใช้ Dialog
    *@deprecated
    */
    public static boolean showDialog(HosControl hc,UpdateStatus us,Visit v)
    {
        DialogReferIn dlg = new DialogReferIn(hc,us);
        dlg.showDialogReferIn(v);
        if(dlg.actionCommand){
            return true;
        }
        dlg=null;
        System.gc();
        return false;
    }    
    
      public void setLanguage(String msg)
      {
            GuiLang.setLanguage(jCheckBoxResultLab);
            GuiLang.setLanguage(col_jTableListRefer);
            GuiLang.setLanguage(jButton1);
            GuiLang.setLanguage(jButtonEXPrintRefer);
            GuiLang.setLanguage(jButtonPrintRefer);
            GuiLang.setLanguage(jButtonReferCancel);
            GuiLang.setLanguage(jButtonReferSave);
            GuiLang.setLanguage(jCheckBoxInfectious);
            GuiLang.setLanguage(jCheckBoxResultReferStatus);
            GuiLang.setLanguage(jCheckBoxRfinLab);
            GuiLang.setLanguage(jCheckBoxRfinObserve);
            GuiLang.setLanguage(jCheckBoxRfinResult);
            GuiLang.setLanguage(jCheckBoxRfinTreatment);
            GuiLang.setLanguage(jCheckBoxSeachDate);
            GuiLang.setLanguage(jCheckBoxStatusComplete);
            GuiLang.setLanguage(jLabel1);
            GuiLang.setLanguage(jLabel10);
            GuiLang.setLanguage(jLabel11);
            GuiLang.setLanguage(jLabel12);
            GuiLang.setLanguage(jLabel13);
            GuiLang.setLanguage(jLabel14);
            GuiLang.setLanguage(jLabel15);
            GuiLang.setLanguage(jLabel16);
            GuiLang.setLanguage(jLabel17);
            GuiLang.setLanguage(jLabel19);
            GuiLang.setLanguage(jLabel2);
            GuiLang.setLanguage(jLabel23);
            GuiLang.setLanguage(jLabel24);
            GuiLang.setLanguage(jLabel27);
            GuiLang.setLanguage(jLabel28);
            GuiLang.setLanguage(jLabel3);
            GuiLang.setLanguage(jLabel30);
            GuiLang.setLanguage(jLabel31);
            GuiLang.setLanguage(jLabel4);
            GuiLang.setLanguage(jLabel5);
            GuiLang.setLanguage(jLabel6);
            GuiLang.setLanguage(jLabel7);
            GuiLang.setLanguage(jLabel8);
            GuiLang.setLanguage(jLabel9);
            GuiLang.setLanguage(jRadioButtonInfectiousNo);
            GuiLang.setLanguage(jRadioButtonInfectiousYes);
            GuiLang.setLanguage(jRadioButtonSearchReferIn);
            GuiLang.setLanguage(jRadioButtonTypeReferIn);
            GuiLang.setLanguage(jRadioButtonTypeReferOut);
            GuiLang.setLanguage(jRadioSearchReferOut);
            GuiLang.setLanguage(jTabbedPane1);
            GuiLang.setTextBundle(jPanel15);
            GuiLang.setTextBundle(jPanel3);
            GuiLang.setTextBundle(jPanel5);
            GuiLang.setTextBundle(jPanel7);
            GuiLang.setTextBundle(jPanel9);
            GuiLang.setTextBundle(jPanel13);

      }  
      
      public boolean confirmBox(String str, int status) {
          Constant.println("incomplete");
          return false;
      }
      public JFrame getJFrame() {
          return this;
      }
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupIO;
    private javax.swing.ButtonGroup buttonGroupInfectious;
    private javax.swing.ButtonGroup buttonGroupTypeRefer;
    private com.hospital_os.utility.DateComboBox dateComboBoxDateRefer;
    private com.hospital_os.utility.DateComboBox dateComboBoxFrom;
    private com.hospital_os.utility.DateComboBox dateComboBoxResultDateResult;
    private com.hospital_os.utility.DateComboBox dateComboBoxTo;
    private com.hospital_os.utility.HNTextField hNTextFieldHnRefer;
    private javax.swing.JTextField integerTextFieldHosMainCode;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDel;
    private javax.swing.JButton jButtonEXPrintRefer;
    private javax.swing.JButton jButtonHosMain;
    private javax.swing.JButton jButtonPrintRefer;
    private javax.swing.JButton jButtonReferCancel;
    private javax.swing.JButton jButtonReferSave;
    private javax.swing.JButton jButtonUpdateResult;
    private javax.swing.JCheckBox jCheckBoxInfectious;
    private javax.swing.JCheckBox jCheckBoxResultLab;
    private javax.swing.JCheckBox jCheckBoxResultReferStatus;
    private javax.swing.JCheckBox jCheckBoxRfinLab;
    private javax.swing.JCheckBox jCheckBoxRfinObserve;
    private javax.swing.JCheckBox jCheckBoxRfinResult;
    private javax.swing.JCheckBox jCheckBoxRfinTreatment;
    private javax.swing.JCheckBox jCheckBoxSeachDate;
    private javax.swing.JCheckBox jCheckBoxStatusComplete;
    private javax.swing.JComboBox jComboBoxDoctorRefer;
    private com.hosv3.gui.component.HosComboBoxStd jComboBoxHosRefer;
    private javax.swing.JComboBox jComboBoxReferCause;
    private javax.swing.JComboBox jComboBoxResultDoctorResult;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButtonInfectiousNo;
    private javax.swing.JRadioButton jRadioButtonInfectiousYes;
    private javax.swing.JRadioButton jRadioButtonSearchReferIn;
    private javax.swing.JRadioButton jRadioButtonTypeReferIn;
    private javax.swing.JRadioButton jRadioButtonTypeReferOut;
    private javax.swing.JRadioButton jRadioSearchReferOut;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.hosv3.gui.component.HJTableSort jTableListRefer;
    private javax.swing.JTextArea jTextAreaHistoryCurrent;
    private javax.swing.JTextArea jTextAreaHistoryFamily;
    private javax.swing.JTextArea jTextAreaHistoryLab;
    private javax.swing.JTextArea jTextAreaResultContinueTreatment;
    private javax.swing.JTextArea jTextAreaResultLab;
    private javax.swing.JTextArea jTextAreaResultOrder;
    private javax.swing.JTextArea jTextAreaResultOtherDetail;
    private javax.swing.JTextArea jTextAreaTreatment;
    private javax.swing.JTextField jTextFieldAge;
    private javax.swing.JTextField jTextFieldCauseRefer;
    private javax.swing.JTextField jTextFieldDoctorRefer;
    private javax.swing.JTextArea jTextFieldFirstDx;
    private javax.swing.JTextField jTextFieldNearLocation;
    private javax.swing.JTextField jTextFieldReferName;
    private javax.swing.JTextField jTextFieldReferNumber;
    private javax.swing.JTextField jTextFieldResultDoctorResult;
    private javax.swing.JTextField jTextFieldResultFinalDx;
    private javax.swing.JTextField jTextFieldSearchReferNumber;
    private javax.swing.JTextField jTextFieldSex;
    // End of variables declaration//GEN-END:variables
}
