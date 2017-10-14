/*
 * PanelVisit_1.java
 *
 * Created on 29 สิงหาคม 2548, 9:51 น.
 */

package com.hosv3.gui.panel.transaction;
import java.util.*;
import java.awt.event.*;
import com.pcu.object.*;
import com.hosv3.object.*;
import com.hosv3.subject.*;
import com.hosv3.control.*;
import com.hosv3.utility.connection.*;
import com.hosv3.utility.*;
import com.hosv3.gui.dialog.*;
import com.hosv3.usecase.transaction.*;
import com.hospital_os.object.*;
import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.TaBleModel;
import com.hosv3.gui.component.CellRendererTooltip;
/**
 *
 * @author  Administrator
 */
public class PanelVisit extends javax.swing.JPanel implements ManageVisitResp, ManagePatientResp, ManageVPaymentResp
{
    HosObject theHO;
    HosControl theHC;
    HosSubject theHS;
    public HosDialog theHD;
    UpdateStatus theUS;
    /**
     * @see
     */
    public Visit theVisit;
    public Patient thePatient;
    public Family theFamily;
    public Payment thePaymentNow;
    public Vector thePlan;
    public Vector vPlanAll;
    public Vector theContract;
    public Vector vPatientPayment;
    public Vector vVisitPayment;
    public Vector vTransfer;
    public Vector vAutoOrder;
    public Refer theRefer;
    private boolean IOption = false;
    //private int priority = 0;
    boolean selectTopItem = false;
    boolean checkComboBox = false;
    //ใช้การพิมพ์ OPD CARD
     static String[] column_jTableVisitPayment = {"เลขที่บัตร","สิทธิ","ส่วนลด"};
     static String[] column_jTablePatientPayment = {"เลขที่บัตร","สิทธิ","วันที่อัพเดท"};
     static String[] column_jTablePlan = {"สิทธิการรักษา"};
     //ใช้ในการแสดง tooltiptext ของ สิทธิการรักษา
     CellRendererTooltip theCellRendererTooltip = new CellRendererTooltip(true);
     com.hospital_os.utility.CellRendererVisitPayment theCellRendererVisitPayment = new com.hospital_os.utility.CellRendererVisitPayment(true);
     Option theOption;
    /** Creates new form PanelVisit_1 */
     public PanelVisit() {
       initComponents();
       setLanguage(null);
       dateComboBoxFrom.setEditable(true);
       dateComboBoxTo.setEditable(true);
       dateComboBoxFrom.setText("");
       dateComboBoxTo.setText("");
       theCellRendererVisitPayment.setFont(jLabel2.getFont());
       jButtonUp2.setVisible(false);
       jButtonDown1.setVisible(false);
       jButtonWS.setVisible(false);
//       jButton1.setVisible(false);
    }
   /*
    * เซตค่าControlต่างๆ
    *ทำการลงทะเบียนกับ Subject เพื่อใช้ในการ Notify
    *
    */
    public void setControl(HosControl hc, UpdateStatus us)
    {
        theHC = hc;
        theHO = hc.theHO;
        theHS = hc.theHS;
        theUS = us;
        hc.theHS.thePatientSubject.attachManagePatient(this);
        hc.theHS.theVisitSubject.attachManageVisit(this);
        hc.theHS.theVPaymentSubject.attach(this);
        ////////////////////////////////////////////////////////////////
        //เซตค่าเริ่มต้น
        setAuthentication(theHO.theEmployee);
        initComboBox();
        initAuthen();
        jButtonSearchAllPlanActionPerformed(null);
        setEnabled(false);
        jCheckBoxVisitDate.setSelected(false);
        jCheckBoxVisitDateActionPerformed(null);

    }
    public void initAuthen(){
        GActionAuthV gaav = theHO.theGActionAuthV;
//        jTablePlan.setEnabled(gaav.isReadPBillingSaveNewPayment());
//
//        jButtonDelete.setVisible(gaav.isReadPBillingSaveNewPayment());
//
//        jButtonSaveVisitPayment.setVisible(gaav.isReadPBillingSaveNewPayment());
//henbe comment 100253 ton ใครสั่งให้แก้ข้อนี้แล้วแก้ถูกหลักการหรือเปล่าถ้าอ่านหน้าจอนี้ไม่ได้จะมองเห็นได้อย่างไร
        //พี่ปูสั่งให้แก้โดยการแยก บันทึกสิทธิการรักษา ออกจากแถบการเงิน
        //แล้วทำไมบรรทัดถึงร่น
        jTablePlan.setEnabled(gaav.isReadTabVisitSaveNewPayment());
        jButtonDelete.setVisible(gaav.isReadTabVisitSaveNewPayment());
        jButtonSaveVisitPayment.setVisible(gaav.isReadTabVisitSaveNewPayment());
    }
    public void initComboBox()
    {
        theOption = theHC.theLookupControl.readOption(true);
        IOption = Gutil.isSelected( theHC.theLookupControl.readOption().inqueuevisit);
        vPlanAll  = theHC.theLookupControl.listPlan();
        thePlan = theHC.theLookupControl.listPlan();
        theContract = theHC.theLookupControl.listContract();
        ////////////////////////////////////////////////////////////////
        String command = theHC.theLookupControl.readOption().b1_command;
        String ttt = theHC.theLookupControl.readOption().b1_description;
        String icon = theHC.theLookupControl.readOption().b1_icon;
        this.jButtonB1.setVisible(!command.equals(""));
        this.jButtonB1.setToolTipText(GuiLang.setLanguage(ttt));
        if(!icon.equals("")) {
            this.jButtonB1.setIcon(new javax.swing.ImageIcon("icon/"+icon));
            this.jButtonB1.setText("");
        }
        ////////////////////////////////////////////////////////////////
    }
    public void setLanguage(String msg)
    {
        GuiLang.setLanguage(jButtonDelete);
        GuiLang.setLanguage(jButtonDown);
        GuiLang.setLanguage(jButtonDown1);
        GuiLang.setLanguage(jButtonHosMain);
        GuiLang.setLanguage(jButtonHosReferIn);
        GuiLang.setLanguage(jButtonHosSub);
        GuiLang.setLanguage(jButtonSaveVtPayment);
        GuiLang.setLanguage(jButtonUp);
        GuiLang.setLanguage(jButtonUp2);
        GuiLang.setLanguage(jCheckBoxShowCancel);
        GuiLang.setLanguage(jCheckBoxVisitDate);
        GuiLang.setLanguage(jLabel10);
        GuiLang.setLanguage(jPanel2);
        GuiLang.setLanguage(jPanel3);
        GuiLang.setLanguage(jPanel4);
        GuiLang.setLanguage(jPanelPlan);
        GuiLang.setLanguage(this.column_jTablePatientPayment);
        GuiLang.setLanguage(this.column_jTablePlan);
        GuiLang.setLanguage(this.column_jTableVisitPayment);
        GuiLang.setLanguage(this.jButtonDeletePp);
        GuiLang.setLanguage(this.jButtonPrintOPDCard);
        GuiLang.setLanguage(this.jButtonSavePtPayment);
        GuiLang.setLanguage(this.jButtonSaveVisitPayment);
        GuiLang.setLanguage(this.jButtonSearchAllPlan);
        GuiLang.setLanguage(this.jButtonVisit);
        GuiLang.setLanguage(this.jLabel1);
        GuiLang.setLanguage(this.jLabel2);
        GuiLang.setLanguage(this.jLabel3);
        GuiLang.setLanguage(this.jLabel4);
        GuiLang.setLanguage(this.jLabel41);
        GuiLang.setLanguage(this.jLabel5);
        GuiLang.setLanguage(this.jLabel6);
        GuiLang.setLanguage(this.jLabel8);
        GuiLang.setLanguage(this.jLabel9);
        GuiLang.setLanguage(this.jLabelMoneyLimit);
    }
    /*
     *เซตค่า Hotdialog
     */
    public void setDialog(HosDialog hd)
    {
        theHD = hd;
    }
    public void setPlan(Plan p){
        setPayment(theHO.initPayment(p));
    }
    public void jButtonUpActionPerformed()
    {
        jButtonUpActionPerformed(null);
    }
    public void jButtonVisitActionPerformed()
    {
        jButtonVisitActionPerformed(null);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jTextFieldSearchPlan = new javax.swing.JTextField();
        jButtonSearchAllPlan = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTablePlan = new com.hosv3.gui.component.HJTableSort();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButtonHosReferIn = new javax.swing.JButton();
        jTextFieldHosRefer = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jButtonVisit = new javax.swing.JButton();
        jButtonPrintOPDCard = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        dateComboBoxCheck = new com.hospital_os.utility.DateComboBox();
        jLabel10 = new javax.swing.JLabel();
        timeTextFieldCheck = new com.hospital_os.utility.TimeTextField();
        jLabel11 = new javax.swing.JLabel();
        jCheckBoxVisitDate = new javax.swing.JCheckBox();
        jButtonWS = new javax.swing.JButton();
        integerTextFieldHosRefer = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButtonSavePtPayment = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonSaveVisitPayment = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jRadioButtonIn = new javax.swing.JRadioButton();
        jRadioButtonOut = new javax.swing.JRadioButton();
        jPanelPlan = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldCardID = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabelMoneyLimit = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        dateComboBoxFrom = new com.hospital_os.utility.DateComboBox();
        dateComboBoxTo = new com.hospital_os.utility.DateComboBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldHosMain = new javax.swing.JTextField();
        jButtonHosMain = new javax.swing.JButton();
        jButtonHosSub = new javax.swing.JButton();
        jTextFieldHosSub = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        integerTextFieldHosMainCode = new javax.swing.JTextField();
        integerTextFieldHosSubCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabelPlan = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTableVisitPayment = new com.hosv3.gui.component.HJTableSort();
        jPanel16 = new javax.swing.JPanel();
        jButtonDown = new javax.swing.JButton();
        jButtonUp = new javax.swing.JButton();
        jCheckBoxShowCancel = new javax.swing.JCheckBox();
        jButtonB1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePatientPayment = new com.hosv3.gui.component.HJTableSort();
        jPanel15 = new javax.swing.JPanel();
        jButtonDeletePp = new javax.swing.JButton();
        jButtonDown1 = new javax.swing.JButton();
        jButtonUp2 = new javax.swing.JButton();
        jButtonSaveVtPayment = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("รายการสิทธิการรักษา"));
        jPanel2.setMinimumSize(new java.awt.Dimension(120, 240));
        jPanel2.setPreferredSize(new java.awt.Dimension(120, 240));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel11.setLayout(new java.awt.GridBagLayout());

        jTextFieldSearchPlan.setFont(defaultFont1);
        jTextFieldSearchPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchPlanActionPerformed(evt);
            }
        });
        jTextFieldSearchPlan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchPlanKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 5);
        jPanel11.add(jTextFieldSearchPlan, gridBagConstraints);

        jButtonSearchAllPlan.setFont(defaultFont1);
        jButtonSearchAllPlan.setText("สิทธิทั้งหมด");
        jButtonSearchAllPlan.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonSearchAllPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchAllPlanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel11.add(jButtonSearchAllPlan, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel11, gridBagConstraints);

        jTablePlan.setFont(defaultFont1);
        jTablePlan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTablePlanMouseReleased(evt);
            }
        });
        jTablePlan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTablePlanKeyReleased(evt);
            }
        });
        jScrollPane11.setViewportView(jTablePlan);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel2.add(jScrollPane11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 0);
        add(jPanel2, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("ข้อมูลการเข้ารับบริการ"));
        jPanel4.setMinimumSize(new java.awt.Dimension(420, 287));
        jPanel4.setPreferredSize(new java.awt.Dimension(420, 287));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel9.setMinimumSize(new java.awt.Dimension(213, 100));
        jPanel9.setPreferredSize(new java.awt.Dimension(213, 100));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setMinimumSize(new java.awt.Dimension(22, 38));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(22, 38));

        jTextArea1.setFont(defaultFont1);
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setMinimumSize(new java.awt.Dimension(100, 50));
        jTextArea1.setPreferredSize(new java.awt.Dimension(100, 10));
        jScrollPane2.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel9.add(jScrollPane2, gridBagConstraints);

        jLabel1.setFont(defaultFont1);
        jLabel1.setText("หมายเหตุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel9.add(jLabel1, gridBagConstraints);

        jLabel8.setFont(defaultFont1);
        jLabel8.setText("รับมาจาก รพ.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel9.add(jLabel8, gridBagConstraints);

        jButtonHosReferIn.setFont(defaultFont1);
        jButtonHosReferIn.setText("...");
        jButtonHosReferIn.setToolTipText("โรงพยาบาลที่Refer");
        jButtonHosReferIn.setBorder(null);
        jButtonHosReferIn.setMaximumSize(new java.awt.Dimension(22, 22));
        jButtonHosReferIn.setMinimumSize(new java.awt.Dimension(22, 22));
        jButtonHosReferIn.setPreferredSize(new java.awt.Dimension(22, 22));
        jButtonHosReferIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHosReferInActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel9.add(jButtonHosReferIn, gridBagConstraints);

        jTextFieldHosRefer.setEditable(false);
        jTextFieldHosRefer.setFont(defaultFont1);
        jTextFieldHosRefer.setBorder(null);
        jTextFieldHosRefer.setMinimumSize(new java.awt.Dimension(100, 22));
        jTextFieldHosRefer.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel9.add(jTextFieldHosRefer, gridBagConstraints);

        jPanel14.setLayout(new java.awt.GridBagLayout());

        jButtonVisit.setFont(defaultFont1);
        jButtonVisit.setText("Visit");
        jButtonVisit.setMargin(new java.awt.Insets(2, 7, 2, 7));
        jButtonVisit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVisitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel14.add(jButtonVisit, gridBagConstraints);

        jButtonPrintOPDCard.setFont(defaultFont1);
        jButtonPrintOPDCard.setText("OPD Card");
        jButtonPrintOPDCard.setMargin(new java.awt.Insets(2, 7, 2, 7));
        jButtonPrintOPDCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintOPDCardActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel14.add(jButtonPrintOPDCard, gridBagConstraints);

        jPanel17.setLayout(new java.awt.GridBagLayout());

        dateComboBoxCheck.setEnabled(false);
        dateComboBoxCheck.setFont(defaultFont1);
        dateComboBoxCheck.setMinimumSize(new java.awt.Dimension(100, 25));
        dateComboBoxCheck.setPreferredSize(new java.awt.Dimension(100, 25));
        dateComboBoxCheck.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dateComboBoxCheckKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 3);
        jPanel17.add(dateComboBoxCheck, gridBagConstraints);

        jLabel10.setFont(defaultFont1);
        jLabel10.setText("น.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 1);
        jPanel17.add(jLabel10, gridBagConstraints);

        timeTextFieldCheck.setEnabled(false);
        timeTextFieldCheck.setFont(defaultFont1);
        timeTextFieldCheck.setMaximumSize(new java.awt.Dimension(46, 21));
        timeTextFieldCheck.setMinimumSize(new java.awt.Dimension(46, 21));
        timeTextFieldCheck.setPreferredSize(new java.awt.Dimension(46, 21));
        timeTextFieldCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timeTextFieldCheckMouseClicked(evt);
            }
        });
        timeTextFieldCheck.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                timeTextFieldCheckKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 3);
        jPanel17.add(timeTextFieldCheck, gridBagConstraints);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/clock.gif"))); // NOI18N
        jLabel11.setToolTipText("เวลาที่ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 3);
        jPanel17.add(jLabel11, gridBagConstraints);

        jCheckBoxVisitDate.setFont(defaultFont1);
        jCheckBoxVisitDate.setText("วันที่");
        jCheckBoxVisitDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBoxVisitDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxVisitDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel17.add(jCheckBoxVisitDate, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel14.add(jPanel17, gridBagConstraints);

        jButtonWS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/web.jpg"))); // NOI18N
        jButtonWS.setToolTipText("ตรวจสอบสิทธิ์ผ่านทางเว็บเซอร์วิสโดยใช้เลขบัตรประจำตัวประชาชน 13 หลัก");
        jButtonWS.setMaximumSize(new java.awt.Dimension(39, 25));
        jButtonWS.setMinimumSize(new java.awt.Dimension(39, 25));
        jButtonWS.setPreferredSize(new java.awt.Dimension(39, 25));
        jButtonWS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWSActionPerformed(evt);
            }
        });
        jPanel14.add(jButtonWS, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel9.add(jPanel14, gridBagConstraints);

        integerTextFieldHosRefer.setFont(defaultFont1);
        integerTextFieldHosRefer.setMaximumSize(new java.awt.Dimension(57, 21));
        integerTextFieldHosRefer.setMinimumSize(new java.awt.Dimension(45, 22));
        integerTextFieldHosRefer.setPreferredSize(new java.awt.Dimension(45, 22));
        integerTextFieldHosRefer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                integerTextFieldHosReferFocusLost(evt);
            }
        });
        integerTextFieldHosRefer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldHosReferKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel9.add(integerTextFieldHosRefer, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 5);
        jPanel4.add(jPanel9, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButtonSavePtPayment.setFont(defaultFont1);
        jButtonSavePtPayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Move.gif"))); // NOI18N
        jButtonSavePtPayment.setToolTipText("ย้ายเป็นสิทธิประจำตัวผู้ป่วย");
        jButtonSavePtPayment.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonSavePtPayment.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonSavePtPayment.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonSavePtPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSavePtPaymentActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel1.add(jButtonSavePtPayment, gridBagConstraints);

        jButtonDelete.setFont(defaultFont1);
        jButtonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelete.setToolTipText("ลบสิทธิ์การรักษา");
        jButtonDelete.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonDelete.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonDelete.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel1.add(jButtonDelete, gridBagConstraints);

        jButtonSaveVisitPayment.setFont(defaultFont1);
        jButtonSaveVisitPayment.setText("บันทึก");
        jButtonSaveVisitPayment.setToolTipText("บันทึกสิทธิ์การรักษา\n");
        jButtonSaveVisitPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveVisitPaymentActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel1.add(jButtonSaveVisitPayment, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        buttonGroup1.add(jRadioButtonIn);
        jRadioButtonIn.setSelected(true);
        jRadioButtonIn.setText("ให้บริการในหน่วย");
        jPanel5.add(jRadioButtonIn, new java.awt.GridBagConstraints());

        buttonGroup1.add(jRadioButtonOut);
        jRadioButtonOut.setText("ให้บริการนอกหน่วย");
        jPanel5.add(jRadioButtonOut, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jPanel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 1, 0, 1);
        jPanel4.add(jPanel1, gridBagConstraints);

        jPanelPlan.setBackground(java.awt.SystemColor.window);
        jPanelPlan.setMinimumSize(new java.awt.Dimension(332, 130));
        jPanelPlan.setPreferredSize(new java.awt.Dimension(332, 130));
        jPanelPlan.setLayout(new java.awt.GridBagLayout());

        jPanel8.setBackground(java.awt.SystemColor.window);
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(defaultFont1);
        jLabel3.setText("วงเงิน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        jPanel8.add(jLabel3, gridBagConstraints);

        jTextFieldCardID.setFont(defaultFont1);
        jTextFieldCardID.setMinimumSize(new java.awt.Dimension(185, 21));
        jTextFieldCardID.setPreferredSize(new java.awt.Dimension(185, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel8.add(jTextFieldCardID, gridBagConstraints);

        jLabel9.setFont(defaultFont1);
        jLabel9.setText("บาท");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        jPanel8.add(jLabel9, gridBagConstraints);

        jLabelMoneyLimit.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        jPanel8.add(jLabelMoneyLimit, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 1, 1, 5);
        jPanelPlan.add(jPanel8, gridBagConstraints);

        jPanel10.setBackground(java.awt.SystemColor.window);
        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(defaultFont1);
        jLabel4.setText("วันที่หมดอายุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 5);
        jPanel10.add(jLabel4, gridBagConstraints);

        dateComboBoxFrom.setFont(defaultFont1);
        dateComboBoxFrom.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxFrom.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxFromActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 5);
        jPanel10.add(dateComboBoxFrom, gridBagConstraints);

        dateComboBoxTo.setFont(defaultFont1);
        dateComboBoxTo.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxTo.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxToActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 5);
        jPanel10.add(dateComboBoxTo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 5);
        jPanelPlan.add(jPanel10, gridBagConstraints);

        jPanel7.setBackground(java.awt.SystemColor.window);
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(defaultFont1);
        jLabel5.setText("สถานพยาบาลหลัก");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 5);
        jPanel7.add(jLabel5, gridBagConstraints);

        jTextFieldHosMain.setEditable(false);
        jTextFieldHosMain.setFont(defaultFont1);
        jTextFieldHosMain.setBorder(null);
        jTextFieldHosMain.setMinimumSize(new java.awt.Dimension(4, 21));
        jTextFieldHosMain.setPreferredSize(new java.awt.Dimension(4, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 0);
        jPanel7.add(jTextFieldHosMain, gridBagConstraints);

        jButtonHosMain.setFont(defaultFont1);
        jButtonHosMain.setText("...");
        jButtonHosMain.setToolTipText("สถานพยาบาลหลัก");
        jButtonHosMain.setBorder(null);
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
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel7.add(jButtonHosMain, gridBagConstraints);

        jButtonHosSub.setFont(defaultFont1);
        jButtonHosSub.setText("...");
        jButtonHosSub.setToolTipText("สถานพยาบาลรอง");
        jButtonHosSub.setBorder(null);
        jButtonHosSub.setMaximumSize(new java.awt.Dimension(22, 22));
        jButtonHosSub.setMinimumSize(new java.awt.Dimension(22, 22));
        jButtonHosSub.setPreferredSize(new java.awt.Dimension(22, 22));
        jButtonHosSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHosSubActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel7.add(jButtonHosSub, gridBagConstraints);

        jTextFieldHosSub.setEditable(false);
        jTextFieldHosSub.setFont(defaultFont1);
        jTextFieldHosSub.setBorder(null);
        jTextFieldHosSub.setMinimumSize(new java.awt.Dimension(4, 21));
        jTextFieldHosSub.setPreferredSize(new java.awt.Dimension(4, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 0);
        jPanel7.add(jTextFieldHosSub, gridBagConstraints);

        jLabel6.setFont(defaultFont1);
        jLabel6.setText("สถานพยาบาลรอง");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 5);
        jPanel7.add(jLabel6, gridBagConstraints);

        integerTextFieldHosMainCode.setBackground(new java.awt.Color(204, 255, 255));
        integerTextFieldHosMainCode.setFont(defaultFont1);
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
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel7.add(integerTextFieldHosMainCode, gridBagConstraints);

        integerTextFieldHosSubCode.setFont(defaultFont1);
        integerTextFieldHosSubCode.setMaximumSize(new java.awt.Dimension(57, 21));
        integerTextFieldHosSubCode.setMinimumSize(new java.awt.Dimension(45, 21));
        integerTextFieldHosSubCode.setPreferredSize(new java.awt.Dimension(45, 21));
        integerTextFieldHosSubCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                integerTextFieldHosSubCodeActionPerformed(evt);
            }
        });
        integerTextFieldHosSubCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                integerTextFieldHosSubCodeFocusLost(evt);
            }
        });
        integerTextFieldHosSubCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldHosSubCodeKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel7.add(integerTextFieldHosSubCode, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 0, 5);
        jPanelPlan.add(jPanel7, gridBagConstraints);

        jLabel2.setFont(defaultFont1);
        jLabel2.setText("เลขที่บัตร");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 1, 1);
        jPanelPlan.add(jLabel2, gridBagConstraints);

        jLabel41.setFont(defaultFont1);
        jLabel41.setText("วันที่ออกบัตร");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 1, 5);
        jPanelPlan.add(jLabel41, gridBagConstraints);

        jLabelPlan.setFont(defaultFont1);
        jLabelPlan.setText("สิทธิการรับบริการ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 2);
        jPanelPlan.add(jLabelPlan, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel4.add(jPanelPlan, gridBagConstraints);

        jPanel6.setMinimumSize(new java.awt.Dimension(48, 110));
        jPanel6.setPreferredSize(new java.awt.Dimension(483, 110));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jTableVisitPayment.setFont(defaultFont1);
        jTableVisitPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableVisitPaymentMouseReleased(evt);
            }
        });
        jTableVisitPayment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableVisitPaymentKeyReleased(evt);
            }
        });
        jScrollPane12.setViewportView(jTableVisitPayment);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel6.add(jScrollPane12, gridBagConstraints);

        jPanel16.setLayout(new java.awt.GridBagLayout());

        jButtonDown.setFont(defaultFont1);
        jButtonDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/down.gif"))); // NOI18N
        jButtonDown.setToolTipText("เลื่อนลง\n");
        jButtonDown.setEnabled(false);
        jButtonDown.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonDown.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonDown.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDownActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel16.add(jButtonDown, gridBagConstraints);

        jButtonUp.setFont(defaultFont1);
        jButtonUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/up.gif"))); // NOI18N
        jButtonUp.setToolTipText("เลื่อนขึ้น\n");
        jButtonUp.setEnabled(false);
        jButtonUp.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonUp.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonUp.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel16.add(jButtonUp, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel6.add(jPanel16, gridBagConstraints);

        jCheckBoxShowCancel.setFont(defaultFont1);
        jCheckBoxShowCancel.setText("แสดงสิทธิที่ถูกยกเลิก");
        jCheckBoxShowCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxShowCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 2, 0);
        jPanel6.add(jCheckBoxShowCancel, gridBagConstraints);

        jButtonB1.setFont(defaultFont1);
        jButtonB1.setText("B1");
        jButtonB1.setToolTipText("");
        jButtonB1.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonB1.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonB1.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonB1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 2, 0);
        jPanel6.add(jButtonB1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 3);
        add(jPanel4, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("สิทธิประจำตัวผู้ป่วย"));
        jPanel3.setMinimumSize(new java.awt.Dimension(120, 160));
        jPanel3.setPreferredSize(new java.awt.Dimension(120, 160));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jTablePatientPayment.setFont(defaultFont1);
        jTablePatientPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTablePatientPaymentMouseReleased(evt);
            }
        });
        jTablePatientPayment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTablePatientPaymentKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTablePatientPayment);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jScrollPane1, gridBagConstraints);

        jPanel15.setLayout(new java.awt.GridBagLayout());

        jButtonDeletePp.setFont(defaultFont1);
        jButtonDeletePp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDeletePp.setToolTipText("ลบสิทธิ์ประจำตัว");
        jButtonDeletePp.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonDeletePp.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonDeletePp.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonDeletePp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeletePpActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel15.add(jButtonDeletePp, gridBagConstraints);

        jButtonDown1.setFont(defaultFont1);
        jButtonDown1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/down.gif"))); // NOI18N
        jButtonDown1.setToolTipText("เลื่อนลง");
        jButtonDown1.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonDown1.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonDown1.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonDown1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDown1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel15.add(jButtonDown1, gridBagConstraints);

        jButtonUp2.setFont(defaultFont1);
        jButtonUp2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/up.gif"))); // NOI18N
        jButtonUp2.setToolTipText("เลื่อนขึ้น");
        jButtonUp2.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonUp2.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonUp2.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonUp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUp2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel15.add(jButtonUp2, gridBagConstraints);

        jButtonSaveVtPayment.setFont(defaultFont1);
        jButtonSaveVtPayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Moveback.gif"))); // NOI18N
        jButtonSaveVtPayment.setToolTipText("บันทึกเป็นสิทธิในการรักษาครั้งนี้");
        jButtonSaveVtPayment.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonSaveVtPayment.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonSaveVtPayment.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonSaveVtPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveVtPaymentActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel15.add(jButtonSaveVtPayment, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel3.add(jPanel15, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 0);
        add(jPanel3, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxVisitDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxVisitDateActionPerformed
//        dateComboBoxCheck.setText(DateUtil.convertFieldDate(theHO.date_time));
//        timeTextFieldCheck.setText(theHO.date_time.substring(11));
        dateComboBoxCheck.setEnabled(jCheckBoxVisitDate.isSelected());
        timeTextFieldCheck.setEnabled(jCheckBoxVisitDate.isSelected());
    }//GEN-LAST:event_jCheckBoxVisitDateActionPerformed

    private void timeTextFieldCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeTextFieldCheckMouseClicked
        timeTextFieldCheck.selectAll();
    }//GEN-LAST:event_timeTextFieldCheckMouseClicked

    private void timeTextFieldCheckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timeTextFieldCheckKeyReleased

    }//GEN-LAST:event_timeTextFieldCheckKeyReleased

    private void dateComboBoxCheckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateComboBoxCheckKeyReleased

    }//GEN-LAST:event_dateComboBoxCheckKeyReleased

    private void jButtonB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonB1ActionPerformed
        String command = theHC.theLookupControl.readOption().b1_command;
        try{
            Process proc = Runtime.getRuntime().exec(command);
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
            e.printStackTrace(Constant.getPrintStream());
        }
    }//GEN-LAST:event_jButtonB1ActionPerformed

    private void jCheckBoxShowCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxShowCancelActionPerformed
        checkBoxShowCancelVisitPayment(this.jCheckBoxShowCancel.isSelected());
        this.theHO.showVisitPaymentCancel = this.jCheckBoxShowCancel.isSelected();
    }//GEN-LAST:event_jCheckBoxShowCancelActionPerformed

    private void jButtonSaveVtPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveVtPaymentActionPerformed
        int select = jTablePatientPayment.getSelectedRow();
        if(select < 0){
            theUS.setStatus("กรุณาเลือกสิทธิ์ที่ต้องการบันทึกเป็นสิทธิในกระบวนการ",UpdateStatus.WARNING);
            return;
        }
        PatientPayment payment = (PatientPayment)vPatientPayment.get(select);
        payment.setObjectId(null);
        saveVisitPayment(payment);

    }//GEN-LAST:event_jButtonSaveVtPaymentActionPerformed

    private void integerTextFieldHosSubCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_integerTextFieldHosSubCodeActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_integerTextFieldHosSubCodeActionPerformed

    private void dateComboBoxToFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxToFocusLost

    }//GEN-LAST:event_dateComboBoxToFocusLost

    private void dateComboBoxToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxToActionPerformed
        if(dateComboBoxTo.getText()!=null || dateComboBoxTo.getText()!="")
        {
            /**
             *fromDate เก็บวันออกบัตร
             *toDate เก็บวันหมดอายุบัตร
             */
            String fromDate = dateComboBoxFrom.getText();
            if(fromDate==null || fromDate.equals(""))
            {
                return;
            }
            String toDate = dateComboBoxTo.getText();
            if(toDate==null || toDate.equals(""))
            {
                toDate = dateComboBoxFrom.getText();
            }
            /**
             *yearFrom เก็บปีวันออกบัตร
             *monthFrom เก็บเดือนวันออกบัตร
             *dateFrom เก็บวันวันออกบัตร
             *yearTo เก็บปีวันหมดอายุบัตร
             *monthTo เก็บเดือนวันหมดอายุบัตร
             *dateTo เก็บวันที่วันหมดอายุบัตร
             */
            int yearFrom = Integer.parseInt(fromDate.substring(0,4));
            int monthFrom = Integer.parseInt(fromDate.substring(5,7));
            int dateFrom = Integer.parseInt(fromDate.substring(8,10));
            int yearTo = Integer.parseInt(toDate.substring(0,4));
            int monthTo = Integer.parseInt(toDate.substring(5,7));
            int dateTo = Integer.parseInt(toDate.substring(8,10));
            if(yearTo < yearFrom)
            {
                theUS.setStatus("วันหมดอายุบัตรไม่สามารถเป็นปีย้อนหลังจากวันออกบัตร",theUS.WARNING);
                return;
            }
            if(yearTo == yearFrom)
            {
                if(monthTo < monthFrom)
                {
                    theUS.setStatus("วันหมดอายุบัตรไม่สามารถเป็นเดือนย้อนหลังจากวันออกบัตร",theUS.WARNING);
                    return;
                }
                if(monthTo == monthFrom)
                {
                    if(dateTo < dateFrom)
                    {
                        theUS.setStatus("วันหมดอายุบัตรไม่สามารถเป็นวันที่ย้อนหลังจากวันออกบัตร",theUS.WARNING);
                        return;
                    }
                }
            }
        }
    }//GEN-LAST:event_dateComboBoxToActionPerformed

    private void dateComboBoxFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxFromActionPerformed
//        dateComboBoxTo.setText(
//            DateUtil.convertFieldDate(dateComboBoxFrom.getText())); 
    }//GEN-LAST:event_dateComboBoxFromActionPerformed

    private void integerTextFieldHosReferKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldHosReferKeyReleased
        if(integerTextFieldHosRefer.getText().length()==5)
        {
            Office office = theHC.theLookupControl.readHospitalByCode(integerTextFieldHosRefer.getText());
            if(office == null)    return;
            if(office.getObjectId()!=null)
            {
                jTextFieldHosRefer.setText(office.name);
                if(theVisit!=null)
                    theVisit.refer_in = office.getObjectId();
            }
            integerTextFieldHosRefer.transferFocus();
        }
    }//GEN-LAST:event_integerTextFieldHosReferKeyReleased

    private void integerTextFieldHosSubCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldHosSubCodeKeyReleased
        if(integerTextFieldHosSubCode.getText().length()==5)
        {
            Office office = theHC.theLookupControl.readHospitalByCode(integerTextFieldHosSubCode.getText());
            if(office == null)    return;
            if(office.getObjectId()!=null)
            {
                jTextFieldHosSub.setText(office.name);
            }
        }
    }//GEN-LAST:event_integerTextFieldHosSubCodeKeyReleased

    private void integerTextFieldHosMainCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldHosMainCodeKeyReleased
        if(integerTextFieldHosMainCode.getText().length()==5)
        {
            Office office = theHC.theLookupControl.readHospitalByCode(integerTextFieldHosMainCode.getText());
            if(office == null)   return;
            if(office.getObjectId()!=null)
            {
                jTextFieldHosMain.setText(office.name);
            }
            integerTextFieldHosSubCode.requestFocus();
        }
    }//GEN-LAST:event_integerTextFieldHosMainCodeKeyReleased

    private void jTextFieldSearchPlanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchPlanKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_DOWN)
        {
             if(jTablePlan.getRowCount() >0)
             {
                jTablePlan.requestFocus();
                jTablePlan.setRowSelectionInterval(0,0);
             }
        }
    }//GEN-LAST:event_jTextFieldSearchPlanKeyReleased

    private void jTableVisitPaymentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableVisitPaymentKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_UP ||evt.getKeyCode()==KeyEvent.VK_DOWN){
            jTableVisitPaymentMouseReleased(null);
        }
    }//GEN-LAST:event_jTableVisitPaymentKeyReleased

    private void jTableVisitPaymentMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVisitPaymentMouseReleased
        this.jTablePatientPayment.clearSelection();
        this.jTablePlan.clearSelection();
        //this.jTableVisitPayment.clearSelection();
        int row = jTableVisitPayment.getSelectedRow();
        if(row==-1) return;
        thePaymentNow = (Payment)vVisitPayment.get(row);
        setPayment(thePaymentNow);        
    }//GEN-LAST:event_jTableVisitPaymentMouseReleased

    private void jTablePatientPaymentMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePatientPaymentMouseReleased
        //this.jTablePatientPayment.clearSelection();
        this.jTablePlan.clearSelection();
        this.jTableVisitPayment.clearSelection();
        thePaymentNow = (Payment)vPatientPayment.get(jTablePatientPayment.getSelectedRow());
        setPayment(thePaymentNow);
        jTableVisitPayment.clearSelection();       
    }//GEN-LAST:event_jTablePatientPaymentMouseReleased

    private void jTablePatientPaymentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablePatientPaymentKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_UP ||evt.getKeyCode()==KeyEvent.VK_DOWN){
            jTablePatientPaymentMouseReleased(null);
        }
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            this.jButtonSaveVtPaymentActionPerformed(null);
        } 
    }//GEN-LAST:event_jTablePatientPaymentKeyReleased

    private void jTablePlanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablePlanKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_UP ||evt.getKeyCode()==KeyEvent.VK_DOWN){
            jTablePlanMouseReleased(null);
        }
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            this.jButtonSaveVisitPaymentActionPerformed(null);
        }         
    }//GEN-LAST:event_jTablePlanKeyReleased

    private void jTablePlanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePlanMouseReleased
        this.jTablePatientPayment.clearSelection();
        //this.jTablePlan.clearSelection();
        this.jTableVisitPayment.clearSelection();
            Plan p = (Plan)thePlan.get(jTablePlan.getSelectedRow());
            setPlan(p);        
    }//GEN-LAST:event_jTablePlanMouseReleased

    private void integerTextFieldHosReferFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integerTextFieldHosReferFocusLost
        Office office = theHC.theLookupControl.readHospitalByCode(integerTextFieldHosRefer.getText());
        if(office==null)
        {
            integerTextFieldHosRefer.setText("");
            jTextFieldHosRefer.setText("");
            theUS.setStatus("ไม่พบสถานพยาบาลที่ตรงกับรหัสที่ระบุ กรุณาตรวจสอบรหัสอีกครั้ง", theUS.WARNING);
            if(theVisit!=null)
            {
                theVisit.refer_in = "";
            }
        }
        else
        {
            jTextFieldHosRefer.setText(office.name);
            if(theVisit!=null)
            {
                theVisit.refer_in = office.getObjectId();
            }
        }
    }//GEN-LAST:event_integerTextFieldHosReferFocusLost

    private void integerTextFieldHosSubCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integerTextFieldHosSubCodeFocusLost
        Office office = theHC.theLookupControl.readHospitalByCode(integerTextFieldHosSubCode.getText());
        if(office==null)
        {
            integerTextFieldHosSubCode.setText("");
            jTextFieldHosSub.setText("");
            theUS.setStatus("ไม่พบสถานพยาบาลที่ตรงกับรหัสที่ระบุ กรุณาตรวจสอบรหัสอีกครั้ง", theUS.WARNING);
        }
        else
        {
            jTextFieldHosSub.setText(office.name);
        }
    }//GEN-LAST:event_integerTextFieldHosSubCodeFocusLost

    private void integerTextFieldHosMainCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integerTextFieldHosMainCodeFocusLost
        Office office = theHC.theLookupControl.readHospitalByCode(integerTextFieldHosMainCode.getText());
        if(office==null)
        {
            integerTextFieldHosMainCode.setText("");
            jTextFieldHosMain.setText("");
            theUS.setStatus("ไม่พบสถานพยาบาลที่ตรงกับรหัสที่ระบุ กรุณาตรวจสอบรหัสอีกครั้ง", theUS.WARNING);
        }
        else
        {
            jTextFieldHosMain.setText(office.name);
        }
    }//GEN-LAST:event_integerTextFieldHosMainCodeFocusLost

    private void jButtonPrintOPDCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintOPDCardActionPerformed
        //int valuePrint = 0;
        //henbe_just theHC.theVisitControl.callPrintOPDCard(valuePrint);
        /**
         * 1 = ให้แสดงก่อนพิมพ์
         * 2 = ไม่ต้องแสดงก่อนพิมพ์
         */
        int printPreview = PrintControl.MODE_PRINT;
         //ต้องมีฟังก์ชั้นนี้ด้วยเพื่อให้ clear ข้อมูลที่ไม่ใช้
        checkBoxShowCancelVisitPayment(false);
        theHC.thePrintControl.printOPDCard(printPreview,vVisitPayment);
    }//GEN-LAST:event_jButtonPrintOPDCardActionPerformed

    private void jButtonVisitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVisitActionPerformed
        Constant.println("test___________________jButtonVisitActionPerformed");
        Constant.println(theHO.vPatientPayment==null);
        if(theHO.theVisit != null)
        {
           theUS.setStatus("ผู้ป่วยเข้าสู่กระบวนการแล้ว",UpdateStatus.WARNING);
           return;
        }
        //สำหรับประชากรที่ยังไม่ได้เป็นผู้ป่วย
        if(theHO.thePatient!=null && theHO.thePatient.discharge_status_id.equals(Dischar.DEATH))  {
            theUS.setStatus("ผู้ป่วยเสียชีวิตแล้วไม่สามารถนำผู้ป่วยเข้าสู่กระบวนการได้",UpdateStatus.WARNING);
            return;
        }
        //สำหรับประชากรที่ยังไม่ได้เป็นผู้ป่วย
        if(theHO.thePatient!=null && theHO.thePatient.active.equals("0"))  {
            theUS.setStatus("UC:ข้อมูลผู้ป่วยถูกยกเลิกแล้วไม่สามารถนำผู้ป่วยเข้าสู่กระบวนการได้",UpdateStatus.WARNING);
            return ;
        }
        if(vVisitPayment == null || vVisitPayment.size()==0)
        {
            Plan plan = theHC.theLookupControl.readPlanById(Plan.SELF_PAY);
            if(plan==null)
            {
                theUS.setStatus("กรุณาบันทึกสิทธิการรักษาก่อน นำผู้ป่วยเข้าสู่กระบวนการ",UpdateStatus.WARNING);
                return;
            }
            setPlan(plan);
            jButtonSaveVisitPaymentActionPerformed(null);
        }
        if(jCheckBoxVisitDate.isSelected() && theOption.future_date_visit.equals("0"))
        {
            int date_valid = DateUtil.countDateDiff(dateComboBoxCheck.getText(),theHO.date_time);
            if(date_valid >0){
                theUS.setStatus("วันที่ Visit ต้องไม่เป็นวันที่ในอนาคต",UpdateStatus.WARNING);
                return;
            }
        }

        //ffff
        theVisit = new Visit();
        getVisit(theVisit);
        QueueVisit qv = new QueueVisit();
        //amp:25/02/2549 เพราะมี uc สั่ง order ล่วงหน้าจากการนัด มาเกี่ยวข้อง จึงต้องแสดงทุกครั้ง
        Vector vWaitAppointment = null;
        //สำหรับประชากรที่ยังไม่ได้เป็นผู้ป่วย
        if(theHO.thePatient!=null)
            vWaitAppointment = theHC.thePatientControl.listWaitAppointment(theHO.thePatient.getObjectId());
        Vector chooseApp = new Vector();
//        theHD.showPanelServiceLocation(theVisit);
        if(jRadioButtonIn.isSelected())
            theVisit.service_location = "1";
        if(jRadioButtonOut.isSelected())
            theVisit.service_location = "2";
        if(IOption || vWaitAppointment!=null)
        {
            boolean ret = theHD.showDialogQueueVisit(theVisit,qv,0,IOption,vWaitAppointment,chooseApp);
            if(!ret) return;
        }
        if(chooseApp.isEmpty())
            theHC.theVisitControl.visitPatient(theVisit,vVisitPayment,qv);
        else
        {
            Appointment app = (Appointment)chooseApp.get(0);
            theHC.theVisitControl.visitPatientApp(theVisit,vVisitPayment,qv,app);
        }
    }//GEN-LAST:event_jButtonVisitActionPerformed

    private void jButtonHosReferInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHosReferInActionPerformed
        if(theVisit!=null){
            theUS.setStatus("ผู้ป่วยเข้าสู่กระบวนการแล้วไม่สามารถแก้ไขสถานพยาบาลที่ส่งมาได้",UpdateStatus.WARNING);
            return;
        }
        Office office = new Office();
        office.setObjectId("");
        if(theHD.showDialogOffice(office)){
            this.jTextFieldHosRefer.setText(office.getName());
            this.integerTextFieldHosRefer.setText(office.getObjectId());
        }
    }//GEN-LAST:event_jButtonHosReferInActionPerformed

    private void jButtonSaveVisitPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveVisitPaymentActionPerformed
        saveVisitPayment(getPayment());
        if(vPatientPayment==null || this.vPatientPayment.isEmpty())
        {
            theHC.thePatientControl.savePatientPayment(vPatientPayment,thePaymentNow);
        }
    }//GEN-LAST:event_jButtonSaveVisitPaymentActionPerformed
    public void saveVisitPayment(Payment thePaymentNow)
    {
        if(theHO.thePatient == null && theHO.theFamily == null){
            theUS.setStatus("ผู้ป่วยยังไม่ได้ถูกเลือก ไม่สามารถเพิ่มสิทธิได้",UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit!=null && theHO.theVisit.is_discharge_money.equals("1")){
             theUS.setStatus("ผู้ป่วยจำหน่ายทางการเงินแล้วไม่สามารถแก้ไขสิทธิได้",UpdateStatus.WARNING);
             return;
        }
        if(thePaymentNow == null ){
            theUS.setStatus("กรุณาเลือกสิทธิการรักษา",UpdateStatus.WARNING);
            return;
        }
        if(thePaymentNow.plan_kid == null || thePaymentNow.plan_kid.equals("")){
            theUS.setStatus("กรุณาเลือกสิทธิการรักษา",UpdateStatus.WARNING);
            return;
        }
        if(vVisitPayment == null) {
            vVisitPayment = new Vector();
        }
        Date dateins = DateUtil.getDateFromText(thePaymentNow.card_ins_date);
        Date dateexp = DateUtil.getDateFromText(thePaymentNow.card_exp_date);
        if(dateins != null && dateexp != null){
            int date_valid = DateUtil.countDateDiff(thePaymentNow.card_ins_date
                ,thePaymentNow.card_exp_date);
            if(date_valid >0){
                theUS.setStatus("วันที่ออกบัตรและวันที่หมดอายุมีช่วงที่ไม่ถูกต้อง",UpdateStatus.WARNING);
                return;
            }
        }
        if(dateins != null){
            int date_valid = DateUtil.countDateDiff(thePaymentNow.card_ins_date,theHO.date_time);
            if(date_valid >0){
                theUS.setStatus("วันที่ออกบัตรต้องไม่เป็นวันที่ในอนาคต",UpdateStatus.WARNING);
                return;
            }
        }
        if(thePaymentNow.hosp_main.equals("")){
            theUS.setStatus("กรุณากรอกรหัสสถานพยาบาลหลัก",UpdateStatus.WARNING);
            return;
        }
        //ตรวจสอบว่าสิทธิซ้ำหรือไม่ถ้าซ้ำก็เอาสิทธิใหม่ไปทับสิทธิ์เดิม
        /////////////////////////////////////////////////////////
        int i = 0;
        int size = 0;
        if(theHO.theVisit!=null){
            theHC.theVisitControl.saveVPayment(thePaymentNow,vVisitPayment);
            return;
        }
        for(i=0,size=vVisitPayment.size(); i<size; i++){
            Payment p = ((Payment) vVisitPayment.get(i));
            if(p.plan_kid.equals(thePaymentNow.plan_kid)){
                break;
            }
        }
        int cur_row=0;
        if(i != vVisitPayment.size()){
            vVisitPayment.removeElementAt(i);
            vVisitPayment.insertElementAt(thePaymentNow,i);
            cur_row = i;
        }
        else{
            vVisitPayment.add(thePaymentNow);
            cur_row = vVisitPayment.size()-1;
        }
        setVisitPaymentV(vVisitPayment);
        this.jTableVisitPayment.setRowSelectionInterval(cur_row,cur_row);
        this.jTableVisitPaymentMouseReleased(null);
    }
    private void jButtonHosSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHosSubActionPerformed
        Office office = new Office();
        office.setObjectId(thePaymentNow.hosp_sub);
        if(theHD.showDialogOffice(office))
        {
            jTextFieldHosSub.setText(office.getName());
            integerTextFieldHosSubCode.setText(office.getObjectId());
        }        
    }//GEN-LAST:event_jButtonHosSubActionPerformed

    private void jButtonHosMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHosMainActionPerformed
        Office office = new Office();
        office.setObjectId(thePaymentNow.hosp_main);
        if(theHD.showDialogOffice(office))
        {
            jTextFieldHosMain.setText(office.getName());
            integerTextFieldHosMainCode.setText(office.getObjectId());
        }        
    }//GEN-LAST:event_jButtonHosMainActionPerformed

    private void jButtonSavePtPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSavePtPaymentActionPerformed
        int select = jTableVisitPayment.getSelectedRow();
        if(select < 0){
            theUS.setStatus("กรุณาเลือกสิทธิ์ที่ต้องการบันทึก",UpdateStatus.WARNING);
            return;
        }
        if(vPatientPayment==null) vPatientPayment = new Vector();
        Payment payment = (Payment)vVisitPayment.get(select);
        theHC.thePatientControl.savePatientPayment(vPatientPayment,payment);           
    }//GEN-LAST:event_jButtonSavePtPaymentActionPerformed

    private void jButtonDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDownActionPerformed
        int select = jTableVisitPayment.getSelectedRow();
        if(select == -1 || select+1 > vVisitPayment.size()-1)
        {
            return;
        }
        Payment p = (Payment)vVisitPayment.get(select);
        //ตรวจสอบว่าได้ผ่านการ visit หรือยัง
        boolean res = false;
        if(p.getObjectId() != null)
        {//ผ่านการ visit แล้ว
            res = theHC.theVisitControl.downVPaymentPriority(vVisitPayment,select);
        }
        else
        { //ไม่ผ่านการ visit
            vVisitPayment.remove(select);
            vVisitPayment.add(select + 1,p);
        }
        setVisitPaymentV(vVisitPayment);
        if(res)
            jTableVisitPayment.addRowSelectionInterval(select+1,select+1);        
    }//GEN-LAST:event_jButtonDownActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        int[] row = jTableVisitPayment.getSelectedRows();
        if(theHO.theVisit==null){
            for(int i=0;i<row.length;i++)
                this.vVisitPayment.remove(row[i]);
            this.setVisitPaymentV(vVisitPayment);
        }
        else
            theHC.theVisitControl.deleteVPayment(vVisitPayment,row,theHO.vBillingInvoice);    
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpActionPerformed
       int select = jTableVisitPayment.getSelectedRow();
        if(select <= 0)//|| select-1 <= 0)
        {
            return;
        }
        Payment p = (Payment)vVisitPayment.get(select);
        boolean res = false;
        if(p.getObjectId() != null)
        {
            res = theHC.theVisitControl.upVPaymentPriority(vVisitPayment,select);
        }
        else
        {
            vVisitPayment.remove(select);
            vVisitPayment.add(select - 1,p);
        }
        setVisitPaymentV(vVisitPayment);
        if(res)
            jTableVisitPayment.addRowSelectionInterval(select-1,select-1);
    }//GEN-LAST:event_jButtonUpActionPerformed

    private void jButtonDown1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDown1ActionPerformed
        int row = jTablePatientPayment.getSelectedRow();
        int size = jTablePatientPayment.getRowCount();
        theHC.thePatientControl.downPriorityPatientPayment(vPatientPayment,row);
        if(row +1 >= size){
            jTablePatientPayment.setRowSelectionInterval(size -1 ,size -1);
            return;
        }
        jTablePatientPayment.setRowSelectionInterval(row+1,row+1);
    }//GEN-LAST:event_jButtonDown1ActionPerformed

    private void jButtonDeletePpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeletePpActionPerformed
        int[] select = jTablePatientPayment.getSelectedRows();
        theHC.thePatientControl.deletePatientPayment(vPatientPayment,select);               
    }//GEN-LAST:event_jButtonDeletePpActionPerformed

    private void jButtonUp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUp2ActionPerformed
        int row = jTablePatientPayment.getSelectedRow();
        //int size = jTablePatientPayment.getRowCount();
        theHC.thePatientControl.upPriorityPatientPayment(vPatientPayment,row);
         if(row-1 <= 0){
            jTablePatientPayment.setRowSelectionInterval(0,0);
            return ;
        }
        jTablePatientPayment.setRowSelectionInterval(row-1,row-1);
    }//GEN-LAST:event_jButtonUp2ActionPerformed

    private void jButtonSearchAllPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchAllPlanActionPerformed
        String strplan = jTextFieldSearchPlan.getText();
        if(strplan.equals(""))
        {
            thePlan = theHC.theLookupControl.listPlan();
            setPlanV(thePlan);
        }
        else
        {
            thePlan = theHC.theLookupControl.listPlan(strplan);
            setPlanV(thePlan);
        }        
    }//GEN-LAST:event_jButtonSearchAllPlanActionPerformed

    private void jTextFieldSearchPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchPlanActionPerformed
        jButtonSearchAllPlanActionPerformed(null);
    }//GEN-LAST:event_jTextFieldSearchPlanActionPerformed

    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

      private void jButtonWSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWSActionPerformed
          if(theHO.theVisit!=null)
          {
              theUS.setStatus("ผู้ป่วยเข้าสู่กระบวนการแล้ว", UpdateStatus.WARNING);
              return;
          }
          int rightData = this.theHC.theVisitControl.seacrhWSRightByPid(theHD);
          if(rightData == 0)
          {
              theUS.setStatus("กรุณาตรวจสอบความถูกต้องของเลขบัตรประชาชน", UpdateStatus.WARNING);
              return;
          }
          if(rightData == 4)
          {
              theUS.setStatus("ไม่พบสิทธิ์ใดๆของผู้ป่วย", UpdateStatus.WARNING);
              return;
          }
          if(rightData == 5)
          {
              theUS.setStatus("กรุณาตรวจสอบ Username และ Password ของ Webservice", UpdateStatus.WARNING);
              return;
          }
          if(rightData == 6)
          {
              theUS.setStatus("กรุณาตรวจสอบความถูกต้องของ WS URL", UpdateStatus.WARNING);
              return;
          }
//          jTablePatientPayment.setRowSelectionInterval(vPatientPayment.size()-1, vPatientPayment.size()-1);
          if(vPatientPayment!=null && !vPatientPayment.isEmpty() && rightData == 1)
          {
              PatientPayment payment = (PatientPayment)vPatientPayment.get(vPatientPayment.size()-1);
              payment.setObjectId(null);
              saveVisitPayment(payment);

              int select = vVisitPayment.size()-1;
              for(int i=select;i>0;i--)
              {
                  jTableVisitPayment.setRowSelectionInterval(i, i);
                  jButtonUpActionPerformed(null);
              }
//              jTableVisitPayment.setRowSelectionInterval(vVisitPayment.size()-1, vVisitPayment.size()-1);
//              jButtonUpActionPerformed(null);
              jButtonVisitActionPerformed(null);
          }
}//GEN-LAST:event_jButtonWSActionPerformed
 
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.hospital_os.utility.DateComboBox dateComboBoxCheck;
    private com.hospital_os.utility.DateComboBox dateComboBoxFrom;
    private com.hospital_os.utility.DateComboBox dateComboBoxTo;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private javax.swing.JTextField integerTextFieldHosMainCode;
    private javax.swing.JTextField integerTextFieldHosRefer;
    private javax.swing.JTextField integerTextFieldHosSubCode;
    private javax.swing.JButton jButtonB1;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonDeletePp;
    private javax.swing.JButton jButtonDown;
    private javax.swing.JButton jButtonDown1;
    private javax.swing.JButton jButtonHosMain;
    private javax.swing.JButton jButtonHosReferIn;
    private javax.swing.JButton jButtonHosSub;
    private javax.swing.JButton jButtonPrintOPDCard;
    private javax.swing.JButton jButtonSavePtPayment;
    private javax.swing.JButton jButtonSaveVisitPayment;
    private javax.swing.JButton jButtonSaveVtPayment;
    private javax.swing.JButton jButtonSearchAllPlan;
    private javax.swing.JButton jButtonUp;
    private javax.swing.JButton jButtonUp2;
    private javax.swing.JButton jButtonVisit;
    private javax.swing.JButton jButtonWS;
    private javax.swing.JCheckBox jCheckBoxShowCancel;
    private javax.swing.JCheckBox jCheckBoxVisitDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelMoneyLimit;
    private javax.swing.JLabel jLabelPlan;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelPlan;
    private javax.swing.JRadioButton jRadioButtonIn;
    private javax.swing.JRadioButton jRadioButtonOut;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private com.hosv3.gui.component.HJTableSort jTablePatientPayment;
    public com.hosv3.gui.component.HJTableSort jTablePlan;
    public com.hosv3.gui.component.HJTableSort jTableVisitPayment;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldCardID;
    private javax.swing.JTextField jTextFieldHosMain;
    private javax.swing.JTextField jTextFieldHosRefer;
    private javax.swing.JTextField jTextFieldHosSub;
    private javax.swing.JTextField jTextFieldSearchPlan;
    private com.hospital_os.utility.TimeTextField timeTextFieldCheck;
    // End of variables declaration//GEN-END:variables
    /**
     * ใช้ในการหาข้อมูลรายการสิทธิที่ถูกยกเลิกหลังจากการคลิกเลือก CheckBox
     * @author padungrat(tong)
     * @param select เป็น boolean ที่กำหนดว่าได้เลือกให้แสดงรายการที่ถูกยกเลิก
     * @date 05/04/2549,09:44
     */
    public void checkBoxShowCancelVisitPayment(boolean select)
    {
        //ตรวจสอบว่าผู้ป่วยอยู่ในสถานะ visit หรือไม่
        if(this.theHO.theVisit!=null && this.theHO.theVisit.getObjectId() != null)
        {   Payment payment = null;
            //ตรวจสอบการเลือกรายการ
            if(select)
            {
                for(int i =  vVisitPayment.size()-1 ; i >=0 ; i--)
                {
                    payment = (Payment)vVisitPayment.get(i);
                    if(!Gutil.isSelected(payment.visit_payment_active))
                    {
                        vVisitPayment.remove(i);
                    }
                    payment = null;
                }
                payment = null;
                Vector vc =  this.theHC.theVisitControl.listVisitPaymentCancel(this.theHO.theVisit.getObjectId());
                if(vc != null)
                {
                    for(int i=0,size=vc.size();i<size;i++)
                    {
                        payment = (Payment)vc.get(i);
                        vVisitPayment.add(payment);
                        payment = null;
                    }
                    payment =null;
                }
                vc = null;
            }
            else
            {
                for(int i =  vVisitPayment.size()-1 ; i >=0 ; i--)
                {
                    payment = (Payment)vVisitPayment.get(i);
                    if(!Gutil.isSelected(payment.visit_payment_active))
                    {
                        vVisitPayment.remove(i);
                    }
                    payment = null;
                }
                payment = null;
            }
            jCheckBoxShowCancel.setSelected(select);
            setVisitPaymentV(vVisitPayment);
        }
        else
        {
            jCheckBoxShowCancel.setSelected(false);
            theUS.setStatus("ยังไม่ได้เลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
        }
    }
    /**
     * @deprecated henbe มาจากไหนใครเขียนเพิ่ม
     */
    private void setAuthentication(Employee em){
        if(em.authentication_id.equals(Authentication.IPD)){
            jButtonVisit.setEnabled(false);
            jButtonHosReferIn.setEnabled(false);
        }
    }
    /*
     *เซตค่าในobject payment
     */
     private Payment getPayment()
    {
        thePaymentNow.card_ins_date =  dateComboBoxFrom.getText();
        thePaymentNow.card_exp_date = dateComboBoxTo.getText();
        thePaymentNow.card_id = Gutil.CheckReservedWords(jTextFieldCardID.getText());
        thePaymentNow.visit_payment_active = "1";
        thePaymentNow.hosp_main = this.integerTextFieldHosMainCode.getText();
        thePaymentNow.hosp_sub = this.integerTextFieldHosSubCode.getText();
        return thePaymentNow;
    }
    /*
     * เซตค่าในobject payment ไปให้GUI
     *
     */
    private void setPayment(Payment pm)
    {
        thePaymentNow = pm;
        if(thePaymentNow == null)
            thePaymentNow = new Payment();
        dateComboBoxFrom.setText(DateUtil.convertFieldDate(thePaymentNow.card_ins_date));
        dateComboBoxTo.setText(DateUtil.convertFieldDate(thePaymentNow.card_exp_date));
        jTextFieldCardID.setText(thePaymentNow.card_id);
        jLabelMoneyLimit.setText(thePaymentNow.money_limit);
        Office office = (Office)theHC.theLookupControl.readHospitalByCode(thePaymentNow.hosp_main);
        String hmain = "";
        String idmain = "";
        if(office!=null)
        {
            hmain = office.getName();
            idmain = office.getObjectId();
        }
        office = (Office)theHC.theLookupControl.readHospitalByCode(thePaymentNow.hosp_sub);
        String hsub = "";
        String idsub = "";
        if(office!=null)
        {
            hsub = office.getName();
            idsub = office.getObjectId();
        }
        jTextFieldHosMain.setText(hmain);
        jTextFieldHosSub.setText(hsub);
        integerTextFieldHosMainCode.setText(idmain);
        integerTextFieldHosSubCode.setText(idsub);
        Plan plan = theHC.theLookupControl.readPlanById(thePaymentNow.plan_kid);
        String title_border = GuiLang.setLanguage("กรุณาเลือกสิทธิการรักษา");
        if(plan!=null)
            title_border = GuiLang.setLanguage("สิทธิ") + " : " + plan.description;
        this.jLabelPlan.setText(title_border);
    }
    /*
     * ทำการupdateค่าใน object plan ให้กับ GUI
     */
    private void setPlanV(Vector pv)
    {
        this.thePlan = pv;
        TaBleModel tm  ;
        jTableVisitPayment.getSelectionModel().clearSelection();
        jTablePatientPayment.getSelectionModel().clearSelection();
        if(thePlan == null)
        {
            thePlan = new Vector();
            tm = new TaBleModel(column_jTablePlan,0);
            jTablePlan.setModel(tm);
            return;
        }
       tm = new TaBleModel(column_jTablePlan,thePlan.size());
       for(int i=0,size = thePlan.size(); i < size; i++)
       {
           Plan plan = (Plan) thePlan.get(i);
           tm.setValueAt(plan.description,i,0);
           if(i==0)  setPlan(plan);
       }
       jTablePlan.setModel(tm);
       jTablePlan.getColumnModel().getColumn(0).setPreferredWidth(300);
       if(thePlan.size()>0)
            jTablePlan.setRowSelectionInterval(0,0);
     }
    private void getVisit(Visit visit)
    {
        visit.visit_note = jTextArea1.getText();
        visit.refer_in = integerTextFieldHosRefer.getText();
        if(this.jCheckBoxVisitDate.isSelected())
            visit.begin_visit_time = this.dateComboBoxCheck.getText() + "," + this.timeTextFieldCheck.getText() + ":00";
    }
     /*
     * ทำการ update ค่าให้object patientpayment ให้กับGUI
     *
     */
    private void setPatientPaymentV(Vector pp)
    {
        this.vPatientPayment = pp;
        TaBleModel tm;
        jTableVisitPayment.getSelectionModel().clearSelection();
        jTablePlan.getSelectionModel().clearSelection();
       if((vPatientPayment == null))
        {
            vPatientPayment = new Vector();
            tm = new TaBleModel(column_jTablePatientPayment,0);
            jTablePatientPayment.setModel(tm);
            return;
        }
       tm = new TaBleModel(column_jTablePatientPayment,vPatientPayment.size());
       for(int i=0,size = vPatientPayment.size() ; i<size; i++)
       {
           PatientPayment pd = (PatientPayment)vPatientPayment.get(i);
           String p = theHC.theLookupControl.readPlanString(pd.plan_kid);
           tm.setValueAt(pd.card_id,i,0);
           tm.setValueAt(p,i,1);
           String cpd = pd.checkplan_date;
           if(cpd.length()==10)
               cpd = cpd.substring(8,10)+"/"+cpd.substring(5,7)+"/"+cpd.substring(2,4);
           tm.setValueAt(cpd,i,2);
       }
       jTablePatientPayment.setModel(tm);
       jTablePatientPayment.getColumnModel().getColumn(0).setPreferredWidth(120);
       jTablePatientPayment.getColumnModel().getColumn(1).setPreferredWidth(230);
       jTablePatientPayment.getColumnModel().getColumn(2).setPreferredWidth(100);
    }
    /*
     * ทำการ update ค่าใน object Visitpayment ให้กับ Gui
     */
    private void setVisitPaymentV(Vector v)
    {
        this.vVisitPayment = v;
        if((vVisitPayment == null) || vVisitPayment.size() == 0)
        {
            vVisitPayment = new Vector();
            TaBleModel tm = new TaBleModel(column_jTableVisitPayment,0);
            jTableVisitPayment.setModel(tm);
            return;
        }
        TaBleModel tm = new TaBleModel(column_jTableVisitPayment,vVisitPayment.size());
        for(int i=0,size = vVisitPayment.size(); i<size; i++)
        {
           Payment pd = ((Payment) vVisitPayment.get(i));
           tm.setValueAt(pd.card_id,i,0);
           Plan plan = theHC.theLookupControl.readPlanById(pd.plan_kid);
           //ไม่ใช้แล้วให้ใช้รูปแบบใหม่แทน  tm.setValueAt(plan.description,i,1);
           tm.setValueAt(theHC.theVisitControl.getTextVisitPayment(pd,plan),i,1);
           tm.setValueAt(theHC.theLookupControl.readContractString(pd.contract_kid),i,2);
        }
        jTableVisitPayment.setModel(tm);
        jTableVisitPayment.getColumnModel().getColumn(0).setPreferredWidth(200);
        jTableVisitPayment.getColumnModel().getColumn(0).setCellRenderer(theCellRendererTooltip);
        jTableVisitPayment.getColumnModel().getColumn(1).setPreferredWidth(300);
        jTableVisitPayment.getColumnModel().getColumn(1).setCellRenderer(theCellRendererVisitPayment);
        jTableVisitPayment.getColumnModel().getColumn(2).setPreferredWidth(300);
    }
     /*
     * เซตโรงพยาบาลที่ส่งและทำการตรวจสอบการตั้งครรถ์
     */
    private void setVisit(Visit v)
    {
        jTextFieldHosRefer.setText("");
        jTextArea1.setText("");
        integerTextFieldHosRefer.setText("");
        theVisit = v;
        if(theVisit == null){
            if(theHO.thePatient==null && theHO.theFamily==null)
                setEnabled(false);
            else
                setEnabled(true);
            return;
        }
        Office office = theHC.theLookupControl.readHospitalByCode(theVisit.refer_in);
        //String hmain = "";
        if(office != null){
            jTextFieldHosRefer.setText(office.getName());
            integerTextFieldHosRefer.setText(office.getCode());
        }
        jTextArea1.setText(theVisit.visit_note);
        if(theVisit.isLockingByOther(theHO.theEmployee.getObjectId())
        || theVisit.isDischargeMoney()
        || theVisit.isDropVisit()) {
            setEnabled(false);
            return;
        }
        dateComboBoxCheck.setText(DateUtil.convertFieldDate(theVisit.begin_visit_time.substring(0,10)));
        timeTextFieldCheck.setText(theVisit.begin_visit_time.substring(11,16));
        setEnabled(true);
    }
    /*
     * กำหนดการเซตปุ่ม
     */
    public void setEnabledPPayment(boolean author)
    {
        jButtonDeletePp.setEnabled(author);
        jButtonSavePtPayment.setEnabled(author);
        jButtonDown1.setEnabled(author);
        jButtonUp2.setEnabled(author);
        jButtonSaveVtPayment.setEnabled(author);
    }
    public void setEnabled(boolean author)
    {
        jButtonPrintOPDCard.setEnabled(author);
        jTextArea1.setEnabled(author);
        jButtonWS.setEnabled(author);
        setEnabledPPayment(author);
        setEnabledVPayment(author);
        setEnabledVisit(author);
    }
    public void setEnabledVisit(boolean author){
        jButtonHosReferIn.setEnabled(author);
        jTextFieldHosRefer.setEnabled(author);
        jButtonVisit.setEnabled(author);
        jTextArea1.setEnabled(author);
        integerTextFieldHosRefer.setEnabled(author);
        jCheckBoxShowCancel.setSelected(false);
        jCheckBoxShowCancel.setEnabled(author);
        jCheckBoxVisitDate.setEnabled(author);
        dateComboBoxCheck.setEnabled(jCheckBoxVisitDate.isSelected());
        timeTextFieldCheck.setEnabled(jCheckBoxVisitDate.isSelected());
        jRadioButtonIn.setEnabled(author);
        jRadioButtonOut.setEnabled(author);
    }
    public void setEnabledVPayment(boolean author)
    {
        integerTextFieldHosMainCode.setEnabled(author);
        integerTextFieldHosSubCode.setEnabled(author);
//        jButtonSearchAllPlan.setEnabled(author);
        jTextFieldCardID.setEnabled(author);
        dateComboBoxFrom.setEnabled(author);
        dateComboBoxTo.setEnabled(author);
        jButtonHosReferIn.setEnabled(author);
        jTextFieldHosMain.setEnabled(author);
        jTextFieldHosSub.setEnabled(author);
        jButtonHosMain.setEnabled(author);
        jButtonHosSub.setEnabled(author);
        jButtonSaveVisitPayment.setEnabled(author);
        jButtonDelete.setEnabled(author);
        jButtonUp.setEnabled(author);
        jButtonDown.setEnabled(author);
//        jTextFieldSearchPlan.setEnabled(author);
    }
    public void setHosObject(HosObject ho)
    {
        jCheckBoxVisitDateActionPerformed(null);
        setPayment(null);
        if(ho.thePatient!=null)
            setPatient(ho.thePatient);
        else
            setFamily(ho.theFamily);
        setVisit(ho.theVisit);
        setPatientPaymentV(ho.vPatientPayment);
        setVisitPaymentV(ho.vVisitPayment);
    }
    public void setPatient(Patient p)
    {
        thePatient = p;
        setEnabled(thePatient!=null);
    }
    public void setFamily(Family f)
    {
        theFamily = f;
        setEnabled(theFamily!=null);
    }
     /*
     * clean function instant
     */
    public void gc()
    {
        theHC.theHS.thePatientSubject.detach(this);
        theHC.theHS.theVisitSubject.detach(this);
        theHC.theHS.theVPaymentSubject.detach(this);
    }
    public void notifyAdmitVisit(String str, int status) {
    }
    public void notifyDischargeDoctor(String str, int status) {
        setHosObject(theHO);
    }
    public void notifyObservVisit(String str, int status) {
    }
    public void notifyUnlockVisit(String str, int status){
        setHosObject(theHO);
        jCheckBoxVisitDate.setSelected(false);
        dateComboBoxCheck.setEnabled(false);
        timeTextFieldCheck.setEnabled(false);
        dateComboBoxCheck.setText(DateUtil.convertFieldDate(theHO.date_time));
        timeTextFieldCheck.setText(theHO.date_time.substring(11));
    }
    public void notifyVisitPatient(String str, int status)
    {
        setHosObject(theHO);
    }
    public void notifyManageAppointment(String str, int status) {
    }
    public void notifyManageDrugAllergy(String str, int status) {
    }
    public void notifySavePatientPayment(String str, int status) {
        setPayment(null);
        vPatientPayment = theHC.thePatientControl.listPatientPayment();
        setPatientPaymentV(vPatientPayment);
    }
    public void notifyReadVisit(String str, int status) {
        setHosObject(theHO);
        jButtonSearchAllPlanActionPerformed(null);
    }
    public void notifyAddItemDrugAllergy(String str, int status)
    {
    }
    public void notifyCheckDoctorTreament(String msg,int state)
    {
    }
    public void notifyDropVisit(String str, int status) {
        setHosObject(theHO);
    }
    public void notifySendVisit(String str, int status) {
        setHosObject(theHO);
    }
    public void notifyDischargeFinancial(String str, int status){
        setHosObject(theHO);
    }
    public void notifyManagePayment(String str, int status)
    {
    }
    public void notifyReverseFinancial(String str, int status)
    {
        setHosObject(theHO);
    }
    public void notifyReverseDoctor(String str, int status)
    {
    }
    public void notifyDeletePatientPayment(String str, int status) {
        setPayment(null);
        vPatientPayment = theHC.thePatientControl.listPatientPayment();
        setPatientPaymentV(vPatientPayment);
    }
    public void notifyDeleteVisitPayment(String str, int status){
        setPayment(null);
        //vVisitPayment = theHC.thePatientControl.listOldPaymentVisitBypatientID(thePatient.getObjectId());
        setVisitPaymentV(theHO.vVisitPayment);
    }
    public void notifyDeletePatient(String str, int status) {
        setHosObject(theHO);
    }
    //หากเป็นผู้ป่วยที่ไม่ได้อยู่ในกระบวนการก็จะเอาสิทธิเก่ามาแสดงให้เห็นนะ
    public void notifyReadPatient(String str, int status) {
        setHosObject(theHO);
        if(thePatient != null)
        {
            Vector vv = theHC.thePatientControl.listOldPaymentVisitBypatientID(thePatient.getObjectId());
            for(int i=0;i<vv.size();i++){
                Payment pm = (Payment)vv.get(i);
                pm.setObjectId(null);
            }
            setVisitPaymentV(vv);
        }
    }
    //หากเป็นผู้ป่วยที่ไม่ได้อยู่ในกระบวนการก็จะเอาสิทธิเก่ามาแสดงให้เห็นนะ
    public void notifyReadFamily(String str, int status) {
        setHosObject(theHO);
    }
     /** Creates a new instance of createPatientAllergy              */
    public void notifySavePatient(String str, int status) {
        setHosObject(theHO);
    }
    public void notifyRemainDoctorDischarge(String str, int status) {
        setHosObject(theHO);
    }
    public void notifySendVisitBackWard(String str, int status) {
        setHosObject(theHO);
    }
    public void notifyDeleteVPayment(String str, int status) {
        this.setVisitPaymentV(theHO.vVisitPayment);
    }
    public void notifyDownVPaymentPriority(String str, int status) {
        this.setVisitPaymentV(theHO.vVisitPayment);
       // xxx
    }
    public void notifySaveVPayment(String str, int status) {
        this.setVisitPaymentV(theHO.vVisitPayment);
    }
    public void notifyUpVPaymentPriority(String str, int status) {
        this.setVisitPaymentV(theHO.vVisitPayment);
    }
    public void notifySaveAppointment(String str, int status) {
    }
    public void notifyReverseAdmit(String str, int status) {
    }
    public void notifyResetPatient(String str, int status) {
        setHosObject(theHO);
    }
    public void notifySaveBorrowFilmXray(String str, int status) {
    }
}

