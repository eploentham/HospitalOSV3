/*
 * JPanelFp.java
 *
 * Created on 13 มิถุนายน 2548, 18:24 น.
 * ตรวจสอบวันที่สำรวจแล้ว
 * kingland
//henbe comment 100253 kong ทำไมบรรทัดร่น
 */
package com.pcu.gui.panelpcu;


import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.utility.Constant;
import com.pcu.control.AllComboBoxControl;
import com.pcu.control.HosManage;
import com.pcu.control.PCUObject;
import com.pcu.gui.dialog.HosDialog;
import javax.swing.*;
import java.util.Vector;
import com.hospital_os.utility.ComboFix;
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.Gutil;
import com.hospital_os.object.Visit;
import com.hospital_os.object.Employee;
import com.hospital_os.object.Patient;
import com.hospital_os.object.Appointment;
import com.hospital_os.object.VisitStatus;
import com.hospital_os.object.Dischar;

import com.pcu.utility.*;
import com.pcu.control.PCUControl;
import com.pcu.object.*;

import com.hosv3.utility.DateUtil;
import com.hosv3.utility.connection.UpdateStatus;
import com.hosv3.usecase.transaction.*;
import com.hosv3.object.*;
import com.hosv3.utility.TableRenderer;
/**
 *
 * @author  tong(Padungrat)
 */
public class PanelFpWoman extends javax.swing.JPanel implements ManagePatientResp
{
    private HosManage theHosManage;
    private PCUControl theHC;
    private Vector vFP ;
    private ComboFix theDefaultComboFix;
    private FamilyPlaningSupplyGroup theFamilyPlaningSupplyGroup;
    private FamilyPlaning theFamilyPlaning;
    private Appointment theAppointment; ;
    private String[] headTableFP = new String[]{"VN","วันที่"};
    /**เป็น ตัวกำหนดค่าในการ เลือกข้อมูลของ วิธีการคุมกำเนิด*/
    private PCUObject pcuobject;
    private HosDialog theHosDialog;
    private int receiveNotify = 0;
    private JFrame theFrame;

    private boolean firstIns;
    private boolean answer = false;

    private UpdateStatus theUS;

    private Vector vSupplies;

    private AllComboBoxControl theAllComboBoxControl;
    /**
     * Creates new form JPanelFp
     * @author ผดุงรัฐ
     */
    public PanelFpWoman() {
        initComponents();
    }
    public void setControl(HosManage hm,HosDialog hd,UpdateStatus us) {
        // 1. set HosManage
        theHosManage = hm;
        theAllComboBoxControl = hm.theHosControl.theAllComboBoxControl;
        theHC = hm.theHosControl;
        theUS = us;
        pcuobject = hm.thePO;
        theHosDialog = hd;
//        hm.theHosControl.theHcHospitalOS.theHS.pcuobject.getPatient()Subject.attachManagePatient(this);
        // 2. Init Components ของ GUI

        // 3. กำหนดภาษาที่ใช้บน GUI
        setLanguage();
        // 4. กำหนดข้อมูลบน Combobox ที่ใช้ทั้งหมด
        initCombobox();
        // 5. กำหนด ค่าเริ่มต้นให้กับ Object ที่ไว้ใช้ ใน combobox
        defaultData();
        // 6. ใช้ในการ กำหนด ค่าเริ่มต้นของการแสดงผล บน GUI และส่งค่า เป็น true เพื่อกำหนดให้ปุ่มบวกทำงาน
        setFamilyPlaning(null);
    }
    private void initBalloon() {
        theHosManage.theHosControl.balloon.add(jTextAreaResultTreatmentBreast);
        jTextAreaResultTreatmentBreast.setControl(theHosManage.theHosInf.getVitalTemplate());
        jTextAreaResultTreatmentBreast.setJFrame(getJFrame());
        theHosManage.theHosControl.balloon.add(jTextAreaTreatmentUterusCancer);
        jTextAreaTreatmentUterusCancer.setControl(theHosManage.theHosInf.getVitalTemplate());
        jTextAreaTreatmentUterusCancer.setJFrame(getJFrame());
    }
    /**
     *เซต PCU Object
     *@param PCUobject
     *@return void
     *@author tong
     *@modify kingland
     *@date 05/09/2549
     */
    public void setObject(PCUObject pcuobject) {   /** ไปจัดการต่อเรื่องของข้อมูลที่จะรับ ถ้าเมื่อไรไม่มีข้อมูล นั้นจะทำอย่างไรบนหน้า GUI */
        System.err.println("_henbe__________________________________" + this.getClass().toString());
        this.pcuobject = pcuobject;
        //กำหนดค่าเริ่มต้น
        answer = false;
        theFamilyPlaning = null;
        setFamilyPlaning(null);
        //ตรวจสอบข้อมูลของ การเข้ารับบริการ
        if(pcuobject.getVisit() != null) {   //Init ค่าให้กับ Valiable ที่ใช้ในการตรวจสอบ
            // ตรวจสอบเงื่อนไขการทำงานของ การเข้ารับบริการครั้งนั้นๆ
            if(pcuobject.getVisit().visit_status.equals(VisitStatus.isInProcess())) {
                jButtonAdd.setEnabled(true);
                jButtonSave.setEnabled(true);
            }
        }
        //ตรวจสอบข้อมูล ผู้ป่วย
        if(pcuobject.getPatient() != null) {
            setEnableGui(true);
            checkSelectInTable();
            jButtonAdd.setEnabled(true);
            jButtonSave.setEnabled(true);
        }
        //ตรวจสอบข้อมูล ประชาการ
        else if(pcuobject.getFamily() != null) {
            setEnableGui(true);
            jButtonAdd.setEnabled(true);
            jButtonSave.setEnabled(true);
        }
        //ทำการ list ข้อมูล แสดงบนตารางตามข้อมูล การเข้ารับบริการและข้อมูลผู้ป่วย
        //ให้แสดงการนัดในทุกกรณี
        checkServiceFamilyPlaning();
        //ทำการตรวจสอบว่ามีการเลือกข้อมูลบนตารางหรือไม่ ถ้าไม่ ให้ Disable ปุ่มออกทั้นที่
        Vector vFP = theHC.listFamilyPlaning();
        setFamilyPlaningV(vFP);

        if(checkDead()) {
            Constant.println("else if(checkDead())");
            setEnabled(false);
        }
    }

    public void setObjectFamily(Family family) {
        Constant.println("_henbe other setObjectFamily _______________________" + this.getClass().toString());
        answer = false;

        setFamilyPlaning(null);
        //ให้แสดงการนัดในทุกกรณี

        if(pcuobject.getFamily()!=null) {
            setEnableGui(true);
            jButtonAdd.setEnabled(true);
            jButtonSave.setEnabled(true);
            //ให้แสดงการนัดในทุกกรณี
        }
        updateTable(null);
    }

    /**ใช้ในการ Init ข้อมูลเริ่มต้นให้กับ ComboBox*/
    private void initCombobox() {
        vSupplies = theHC.theFamilyPlaningControl.queryFamilyPlaningSupplies();
        ComboboxModel.initComboBox(jComboBoxMedicalSupplies,theHC.theFamilyPlaningControl.ConvertObjectSupplyToComboFix(vSupplies));
        ComboboxModel.initComboBox(jComboBoxHow,theHC.theAllComboBoxControl.queryAllFamilyPlaningMethod());
        ComboboxModel.initComboBox(jComboBoxCauseFp,theHC.theAllComboBoxControl.queryAllFamilyPlaningCause());
        String yes = GutilPCU.setLanguage("ใช้");
        String no = GutilPCU.setLanguage("ไม่ใช้");
        panelYesNoRadioButtonTestPregnant.setTextYesNo(yes, no);
    }

    private void defaultData() {
        theDefaultComboFix = new ComboFix();
        theDefaultComboFix.code = "0";
        theDefaultComboFix.name = "ไม่ระบุ";
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroupSelectBreast = new javax.swing.ButtonGroup();
        buttonGroupSelectCancer = new javax.swing.ButtonGroup();
        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jPanelFpList = new javax.swing.JPanel();
        jScrollPaneFpList = new javax.swing.JScrollPane();
        jTableFP = new javax.swing.JTable();
        jPanelDetail = new javax.swing.JPanel();
        jPanelFpDetail = new javax.swing.JPanel();
        jLabelHow = new javax.swing.JLabel();
        jComboBoxHow = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        integerTextFieldNumberOfSon = new com.pcu.utility.IntegerTextField();
        jLabelNextAppDate = new javax.swing.JLabel();
        jButtonAppointment = new javax.swing.JButton();
        jLabelAppointment = new javax.swing.JLabel();
        jLabelSon = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelTestPregnant = new javax.swing.JLabel();
        panelYesNoRadioButtonTestPregnant = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        jLabelSurveyDate = new javax.swing.JCheckBox();
        dateComboBoxSurvey = new com.pcu.utility.DateComboBox();
        jPanelNotControl = new javax.swing.JPanel();
        jLabelCaseFp = new javax.swing.JLabel();
        jComboBoxCauseFp = new javax.swing.JComboBox();
        jPanelControl = new javax.swing.JPanel();
        jLabelMedicalSupplies = new javax.swing.JLabel();
        jComboBoxMedicalSupplies = new javax.swing.JComboBox();
        jLabelNumMedical = new javax.swing.JLabel();
        integerTextFieldNumMedical = new com.pcu.utility.IntegerTextField();
        jPanelRemark = new javax.swing.JPanel();
        jTextAreaNote = new javax.swing.JTextField();
        jLabelRemark = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        dateComboBoxCheck = new com.hospital_os.utility.DateComboBox();
        jLabel6 = new javax.swing.JLabel();
        timeTextFieldCheck = new com.hospital_os.utility.TimeTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabelVN = new javax.swing.JLabel();
        jPanelBreastExam = new javax.swing.JPanel();
        jPanelOptionBreast = new javax.swing.JPanel();
        jRadioButtonBreastNormal = new javax.swing.JRadioButton();
        jRadioButtonBreastNoTreat = new javax.swing.JRadioButton();
        jRadioButtonBreastAbNormal = new javax.swing.JRadioButton();
        jRadioButtonBreastWait = new javax.swing.JRadioButton();
        jScrollPaneResultBreast = new javax.swing.JScrollPane();
        jTextAreaResultTreatmentBreast = new com.hosv3.gui.component.BalloonTextArea();
        jPanelUterusCancer = new javax.swing.JPanel();
        jPanelOptionUterusCancer = new javax.swing.JPanel();
        jRadioButtonUterusCancerNormal = new javax.swing.JRadioButton();
        jRadioButtonUterusCancerNoTreat = new javax.swing.JRadioButton();
        jRadioButtonUterusCancerWait = new javax.swing.JRadioButton();
        jRadioButtonUterusCancerAbNormal = new javax.swing.JRadioButton();
        jPanelOptionUterusCancer1 = new javax.swing.JPanel();
        jRadioButtonUterusCancerVia = new javax.swing.JRadioButton();
        jRadioButtonUterusCancerPap = new javax.swing.JRadioButton();
        jScrollPaneResultCancer = new javax.swing.JScrollPane();
        jTextAreaTreatmentUterusCancer = new com.hosv3.gui.component.BalloonTextArea();
        jPanelHomeVisitControl1 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonDel = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(700, 400));
        setPreferredSize(new java.awt.Dimension(700, 400));
        setRequestFocusEnabled(false);
        setLayout(new java.awt.GridBagLayout());

        jPanelFpList.setMinimumSize(new java.awt.Dimension(175, 120));
        jPanelFpList.setPreferredSize(new java.awt.Dimension(175, 120));
        jPanelFpList.setLayout(new java.awt.GridBagLayout());

        jScrollPaneFpList.setEnabled(false);
        jScrollPaneFpList.setMinimumSize(new java.awt.Dimension(200, 23));
        jScrollPaneFpList.setPreferredSize(new java.awt.Dimension(200, 100));

        jTableFP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableFPMouseReleased(evt);
            }
        });
        jScrollPaneFpList.setViewportView(jTableFP);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelFpList.add(jScrollPaneFpList, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        add(jPanelFpList, gridBagConstraints);

        jPanelDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("FpWoman_Detail"));
        jPanelDetail.setMinimumSize(new java.awt.Dimension(300, 120));
        jPanelDetail.setPreferredSize(new java.awt.Dimension(300, 120));
        jPanelDetail.setLayout(new java.awt.GridBagLayout());

        jPanelFpDetail.setLayout(new java.awt.GridBagLayout());

        jLabelHow.setText("How");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanelFpDetail.add(jLabelHow, gridBagConstraints);

        jComboBoxHow.setBackground(new java.awt.Color(204, 255, 255));
        jComboBoxHow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxHowActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 0, 0);
        jPanelFpDetail.add(jComboBoxHow, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        integerTextFieldNumberOfSon.setBackground(new java.awt.Color(204, 255, 255));
        integerTextFieldNumberOfSon.setColumns(2);
        integerTextFieldNumberOfSon.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        integerTextFieldNumberOfSon.setText("0");
        integerTextFieldNumberOfSon.setMinimumSize(new java.awt.Dimension(30, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(integerTextFieldNumberOfSon, gridBagConstraints);

        jLabelNextAppDate.setText("NextAppDate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanel1.add(jLabelNextAppDate, gridBagConstraints);

        jButtonAppointment.setText("นัด");
        jButtonAppointment.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButtonAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAppointmentActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jButtonAppointment, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabelAppointment, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanelFpDetail.add(jPanel1, gridBagConstraints);

        jLabelSon.setText("Son");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelFpDetail.add(jLabelSon, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabelTestPregnant.setText("TestPregnant");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel2.add(jLabelTestPregnant, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel2.add(panelYesNoRadioButtonTestPregnant, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelFpDetail.add(jPanel2, gridBagConstraints);

        jLabelSurveyDate.setText("SurveyDate");
        jLabelSurveyDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jLabelSurveyDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabelSurveyDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelFpDetail.add(jLabelSurveyDate, gridBagConstraints);

        dateComboBoxSurvey.setEnabled(false);
        dateComboBoxSurvey.setText("");
        dateComboBoxSurvey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxSurveyActionPerformed(evt);
            }
        });
        dateComboBoxSurvey.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateComboBoxSurveyFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelFpDetail.add(dateComboBoxSurvey, gridBagConstraints);

        jPanelNotControl.setLayout(new java.awt.GridBagLayout());

        jLabelCaseFp.setText("เหตุที่ไม่คุมกำเนิด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanelNotControl.add(jLabelCaseFp, gridBagConstraints);

        jComboBoxCauseFp.setBackground(new java.awt.Color(204, 255, 255));
        jComboBoxCauseFp.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 0, 0);
        jPanelNotControl.add(jComboBoxCauseFp, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelFpDetail.add(jPanelNotControl, gridBagConstraints);

        jPanelControl.setLayout(new java.awt.GridBagLayout());

        jLabelMedicalSupplies.setText("MedicalSupplies");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanelControl.add(jLabelMedicalSupplies, gridBagConstraints);

        jComboBoxMedicalSupplies.setBackground(new java.awt.Color(204, 255, 255));
        jComboBoxMedicalSupplies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMedicalSuppliesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 0, 0);
        jPanelControl.add(jComboBoxMedicalSupplies, gridBagConstraints);

        jLabelNumMedical.setText("จำนวน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 0, 0);
        jPanelControl.add(jLabelNumMedical, gridBagConstraints);

        integerTextFieldNumMedical.setBackground(new java.awt.Color(204, 255, 255));
        integerTextFieldNumMedical.setColumns(2);
        integerTextFieldNumMedical.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        integerTextFieldNumMedical.setText("0");
        integerTextFieldNumMedical.setEnabled(false);
        integerTextFieldNumMedical.setMinimumSize(new java.awt.Dimension(30, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 0, 0);
        jPanelControl.add(integerTextFieldNumMedical, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelFpDetail.add(jPanelControl, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 11);
        jPanelDetail.add(jPanelFpDetail, gridBagConstraints);

        jPanelRemark.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelRemark.add(jTextAreaNote, gridBagConstraints);

        jLabelRemark.setText("Remark");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanelRemark.add(jLabelRemark, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelDetail.add(jPanelRemark, gridBagConstraints);

        jPanel16.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("วันที่บันทึก");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 3);
        jPanel16.add(jLabel5, gridBagConstraints);

        dateComboBoxCheck.setBackground(new java.awt.Color(204, 255, 255));
        dateComboBoxCheck.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dateComboBoxCheckKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 3);
        jPanel16.add(dateComboBoxCheck, gridBagConstraints);

        jLabel6.setText("น.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 1);
        jPanel16.add(jLabel6, gridBagConstraints);

        timeTextFieldCheck.setBackground(new java.awt.Color(204, 255, 255));
        timeTextFieldCheck.setMinimumSize(new java.awt.Dimension(45, 23));
        timeTextFieldCheck.setPreferredSize(new java.awt.Dimension(45, 23));
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 3);
        jPanel16.add(timeTextFieldCheck, gridBagConstraints);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/clock.gif"))); // NOI18N
        jLabel9.setToolTipText("เวลาที่ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 3);
        jPanel16.add(jLabel9, gridBagConstraints);

        jLabelVN.setText("VN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel16.add(jLabelVN, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 0);
        jPanelDetail.add(jPanel16, gridBagConstraints);

        jPanelBreastExam.setBorder(javax.swing.BorderFactory.createTitledBorder("Treatment_Breast"));
        jPanelBreastExam.setLayout(new java.awt.GridBagLayout());

        jPanelOptionBreast.setLayout(new java.awt.GridBagLayout());

        buttonGroupSelectBreast.add(jRadioButtonBreastNormal);
        jRadioButtonBreastNormal.setMnemonic('1');
        jRadioButtonBreastNormal.setText("Normal");
        jRadioButtonBreastNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonBreastNormalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanelOptionBreast.add(jRadioButtonBreastNormal, gridBagConstraints);

        buttonGroupSelectBreast.add(jRadioButtonBreastNoTreat);
        jRadioButtonBreastNoTreat.setMnemonic('0');
        jRadioButtonBreastNoTreat.setSelected(true);
        jRadioButtonBreastNoTreat.setText("NoTreat");
        jRadioButtonBreastNoTreat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonBreastNoTreatActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanelOptionBreast.add(jRadioButtonBreastNoTreat, gridBagConstraints);

        buttonGroupSelectBreast.add(jRadioButtonBreastAbNormal);
        jRadioButtonBreastAbNormal.setMnemonic('2');
        jRadioButtonBreastAbNormal.setText("Abnormal");
        jRadioButtonBreastAbNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonBreastAbNormalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelOptionBreast.add(jRadioButtonBreastAbNormal, gridBagConstraints);

        buttonGroupSelectBreast.add(jRadioButtonBreastWait);
        jRadioButtonBreastWait.setMnemonic('2');
        jRadioButtonBreastWait.setText("Wait");
        jRadioButtonBreastWait.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonBreastWaitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelOptionBreast.add(jRadioButtonBreastWait, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanelBreastExam.add(jPanelOptionBreast, gridBagConstraints);

        jScrollPaneResultBreast.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneResultBreast.setAutoscrolls(true);
        jScrollPaneResultBreast.setViewportView(jTextAreaResultTreatmentBreast);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelBreastExam.add(jScrollPaneResultBreast, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelDetail.add(jPanelBreastExam, gridBagConstraints);

        jPanelUterusCancer.setBorder(javax.swing.BorderFactory.createTitledBorder("Treatment_UterusCancer"));
        jPanelUterusCancer.setMinimumSize(new java.awt.Dimension(199, 59));
        jPanelUterusCancer.setLayout(new java.awt.GridBagLayout());

        jPanelOptionUterusCancer.setLayout(new java.awt.GridBagLayout());

        buttonGroupSelectCancer.add(jRadioButtonUterusCancerNormal);
        jRadioButtonUterusCancerNormal.setText("Normal");
        jRadioButtonUterusCancerNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonUterusCancerNormalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanelOptionUterusCancer.add(jRadioButtonUterusCancerNormal, gridBagConstraints);

        buttonGroupSelectCancer.add(jRadioButtonUterusCancerNoTreat);
        jRadioButtonUterusCancerNoTreat.setSelected(true);
        jRadioButtonUterusCancerNoTreat.setText("NoTreat");
        jRadioButtonUterusCancerNoTreat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonUterusCancerNoTreatActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanelOptionUterusCancer.add(jRadioButtonUterusCancerNoTreat, gridBagConstraints);

        buttonGroupSelectCancer.add(jRadioButtonUterusCancerWait);
        jRadioButtonUterusCancerWait.setText("Wait");
        jRadioButtonUterusCancerWait.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonUterusCancerWaitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelOptionUterusCancer.add(jRadioButtonUterusCancerWait, gridBagConstraints);

        buttonGroupSelectCancer.add(jRadioButtonUterusCancerAbNormal);
        jRadioButtonUterusCancerAbNormal.setText("Abnormal");
        jRadioButtonUterusCancerAbNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonUterusCancerAbNormalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelOptionUterusCancer.add(jRadioButtonUterusCancerAbNormal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelUterusCancer.add(jPanelOptionUterusCancer, gridBagConstraints);

        jPanelOptionUterusCancer1.setBorder(javax.swing.BorderFactory.createTitledBorder("วิธีตรวจ"));
        jPanelOptionUterusCancer1.setMinimumSize(new java.awt.Dimension(300, 50));
        jPanelOptionUterusCancer1.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanelOptionUterusCancer1.setLayout(new java.awt.GridBagLayout());

        jRadioButtonUterusCancerVia.setText("VIA");
        jRadioButtonUterusCancerVia.setEnabled(false);
        jRadioButtonUterusCancerVia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonUterusCancerViaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelOptionUterusCancer1.add(jRadioButtonUterusCancerVia, gridBagConstraints);

        jRadioButtonUterusCancerPap.setText("PAP");
        jRadioButtonUterusCancerPap.setEnabled(false);
        jRadioButtonUterusCancerPap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonUterusCancerPapActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanelOptionUterusCancer1.add(jRadioButtonUterusCancerPap, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanelUterusCancer.add(jPanelOptionUterusCancer1, gridBagConstraints);

        jScrollPaneResultCancer.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextAreaTreatmentUterusCancer.setMinimumSize(new java.awt.Dimension(10, 10));
        jTextAreaTreatmentUterusCancer.setPreferredSize(new java.awt.Dimension(10, 10));
        jScrollPaneResultCancer.setViewportView(jTextAreaTreatmentUterusCancer);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelUterusCancer.add(jScrollPaneResultCancer, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelDetail.add(jPanelUterusCancer, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jPanelDetail, gridBagConstraints);

        jPanelHomeVisitControl1.setLayout(new java.awt.GridBagLayout());

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jPanelHomeVisitControl1.add(jButtonSave, gridBagConstraints);

        jButtonDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDel.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDel.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDel.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelHomeVisitControl1.add(jButtonDel, gridBagConstraints);

        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
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
        jPanelHomeVisitControl1.add(jButtonAdd, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 5, 11);
        add(jPanelHomeVisitControl1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    private void jLabelSurveyDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLabelSurveyDateActionPerformed

        this.dateComboBoxSurvey.setEnabled(jLabelSurveyDate.isSelected());
        if(!jLabelSurveyDate.isSelected())
            dateComboBoxSurvey.setText("");

    }//GEN-LAST:event_jLabelSurveyDateActionPerformed

    

    private void timeTextFieldCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeTextFieldCheckMouseClicked

        timeTextFieldCheck.selectAll();

    }//GEN-LAST:event_timeTextFieldCheckMouseClicked

    

    private void timeTextFieldCheckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timeTextFieldCheckKeyReleased

        

    }//GEN-LAST:event_timeTextFieldCheckKeyReleased

    

    private void dateComboBoxCheckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateComboBoxCheckKeyReleased

        

    }//GEN-LAST:event_dateComboBoxCheckKeyReleased

    

    private void jRadioButtonUterusCancerViaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonUterusCancerViaActionPerformed

        setMethode(2);

    }//GEN-LAST:event_jRadioButtonUterusCancerViaActionPerformed

    

    private void jRadioButtonUterusCancerPapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonUterusCancerPapActionPerformed

        setMethode(1);

    }//GEN-LAST:event_jRadioButtonUterusCancerPapActionPerformed

    

    private void jRadioButtonUterusCancerWaitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonUterusCancerWaitActionPerformed

        setTextResultIsEditable(2);

    }//GEN-LAST:event_jRadioButtonUterusCancerWaitActionPerformed

    

    private void jRadioButtonBreastWaitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonBreastWaitActionPerformed

        setTextResultIsEditable(1);

    }//GEN-LAST:event_jRadioButtonBreastWaitActionPerformed

    

    private void dateComboBoxSurveyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxSurveyFocusLost

        checkDateSurvey();

    }//GEN-LAST:event_dateComboBoxSurveyFocusLost

    

    private void dateComboBoxSurveyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxSurveyActionPerformed

        checkDateSurvey();

    }//GEN-LAST:event_dateComboBoxSurveyActionPerformed

    

    private void jButtonAppointmentActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAppointmentActionPerformed

    {//GEN-HEADEREND:event_jButtonAppointmentActionPerformed

        if(pcuobject.getPatient() == null) {
            theUS.setStatus(GutilPCU.getTextBundle("No_Patient_Appointment"),UpdateStatus.WARNING);
            return;
        }
        theHosDialog.showDialogAppointment(theHosManage,pcuobject);
        receiveNotify = 1;

    }//GEN-LAST:event_jButtonAppointmentActionPerformed

    

    private void jRadioButtonUterusCancerAbNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonUterusCancerAbNormalActionPerformed

        setTextResultIsEditable(2);

    }//GEN-LAST:event_jRadioButtonUterusCancerAbNormalActionPerformed

    

    private void jRadioButtonUterusCancerNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonUterusCancerNormalActionPerformed

        setTextResultIsEditable(2);

    }//GEN-LAST:event_jRadioButtonUterusCancerNormalActionPerformed

    

    private void jRadioButtonUterusCancerNoTreatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonUterusCancerNoTreatActionPerformed

        setTextResultIsEditable(2);

    }//GEN-LAST:event_jRadioButtonUterusCancerNoTreatActionPerformed

    

    private void jRadioButtonBreastNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonBreastNormalActionPerformed

        setTextResultIsEditable(1);

    }//GEN-LAST:event_jRadioButtonBreastNormalActionPerformed

    

    private void jRadioButtonBreastNoTreatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonBreastNoTreatActionPerformed

        setTextResultIsEditable(1);

    }//GEN-LAST:event_jRadioButtonBreastNoTreatActionPerformed

    

    private void jRadioButtonBreastAbNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonBreastAbNormalActionPerformed

        setTextResultIsEditable(1);

    }//GEN-LAST:event_jRadioButtonBreastAbNormalActionPerformed

    

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed

        addFamilyPlaning(false);

    }//GEN-LAST:event_jButtonAddActionPerformed

    

    private void jButtonDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelActionPerformed

        deleteFamilyPlaning();

    }//GEN-LAST:event_jButtonDelActionPerformed

    

    private void jTableFPMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFPMouseReleased

        selectTableFP(jTableFP.getSelectedRow());

    }//GEN-LAST:event_jTableFPMouseReleased

    

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed

        saveFpWoman();

    }//GEN-LAST:event_jButtonSaveActionPerformed

    

    private void jComboBoxMedicalSuppliesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMedicalSuppliesActionPerformed

        selectComboBoxMedicalSupplies(ComboboxModel.getCodeComboBox(jComboBoxMedicalSupplies));

    }//GEN-LAST:event_jComboBoxMedicalSuppliesActionPerformed

    

    private void jComboBoxHowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxHowActionPerformed

        selectComboBoxHow();

    }//GEN-LAST:event_jComboBoxHowActionPerformed


    /**ใช้ในการตรวจสอบข้อมูลของการเข้ารับบริการ ส่วนนี้ ว่ามี visit_id
     * ตรงกับที่มีอยู่หรือไม่ ถ้าตรงกันให้ ทำการเลือกข้อมูลนั้นไปเลย
     */
    private void checkServiceFamilyPlaning() {
        int selectRow = -1;
        if(vFP!=null) {
            int size = vFP.size();
            // ทำการวนลูปข้อมูลเพื่อหาว่ามีข้อมูลของ visit ครั้งนั้นหรือไม่
            for(int i = 0 ;i< size ; i++) {   //ให้ค่าที่ vector เก็บอยู่ที่ตำแหน่ง i
                FamilyPlaning fp  = (FamilyPlaning)vFP.get(i);
                //ตรวจสอบ ค่าที่ของ object ที่ เก็บอยู่ใน vector ว่ามีค่าของ visit_id เดียวกันการการเข้ารับบริการครั้งนี้ หรือไม่
                if(pcuobject.getVisit()!=null &&
                    fp.visit_id.equals(pcuobject.getVisit().getObjectId())) {
                    selectRow = i;
                    break;
                }
            }
        }
        if(selectRow != -1) {
            jTableFP.setRowSelectionInterval(selectRow,selectRow);
            selectTableFP(selectRow);
        }
    }
    /**
     *  ใช้ในการตรวจสอบว่ามีการเลือกข้อมูลบนตารางหรือไม่ ถ้าไม่มีให้ทำการ Disable ปุ่ม ลบทั้นที่
     *  จะใช้เมื่อมีการเลือกผู้ป่วยขึ้นมา
     */
    private void checkSelectInTable() {   //รับค่าการเลือกจากตารางเป็น array ของ int
        int[] select = jTableFP.getSelectedRows();
        //ตรวจสอบว่ามีการเลือกมากกว่า 0 row หรือไม่
        if(select.length == 0 ) {   //ทำการ Disable ปุ่ม ลบทั้นที่
            setEnableButton(false,0, true);
        }
    }
    /**
     *ตรวจสอบว่าผู้ป่วยหรือประชากรเสียชีวิตแล้วหรือไม่
     *@param -
     *@return boolean true=เสียชีวิต false=ไม่เสียชีวิต
     *@author kingland
     *@date 04/09/2549
     */
    private boolean checkDead() {
        boolean result = false;
        if(pcuobject.getFamily() != null && Dischar.DEATH.equals(pcuobject.getFamily().discharge_status_id)) {
            result = true;
        } else if(pcuobject.getPatient() != null && Dischar.DEATH.equals(pcuobject.getPatient().discharge_status_id)) {
            result = true;
        }
        return false;//result;
    }
    /**
     *ตรวจสอบเพศของผู้รับบริการ
     *@param -
     *@return boolean true=ผ่าน false=ไม่ผ่าน
     *@author kingland
     *@date 04/09/2549
     */
    private boolean checkWomenSex(boolean showWarningMessage) {
        boolean result = true;
        //add code by noom สำหรับ check เพศ หญิงเท่านั้น
        if(pcuobject.getPatient() != null && !("2").equals(pcuobject.getPatient().f_sex_id)) {
            if(showWarningMessage) {
                theUS.setStatus(GutilPCU.getTextBundle("ISSEXWOMAN"),UpdateStatus.WARNING);
                //ยังไม่ระบุเพศ หรือเป็นเพศชาย ไม่สามารถเข้ารับบริการส่วนนี้ได้
            }
            result = false;
        }
        //สำหรับ check เพศ หญิงเท่านั้น
        else if(pcuobject.getFamily()!=null && !("2").equals(pcuobject.getFamily().f_sex_id)) {
            if(showWarningMessage) {
                theUS.setStatus(GutilPCU.getTextBundle("ISSEXWOMAN"),UpdateStatus.WARNING);
                //ยังไม่ระบุเพศ หรือเป็นเพศชาย ไม่สามารถเข้ารับบริการส่วนนี้ได้
            }
            result = false;
        } else if(pcuobject.getFamily()!=null && "".equals(pcuobject.getFamily().f_sex_id)) {
            result = false;
        }
        return result;
    }
    /**
     *ตรวจสอบการแก้ไขข้อมูล ว่าสามารถแก้ไขข้อมูล
     *@param -
     *@return boolean true=สามารถแก้ไขได้ false=ไม่สามารถแก้ไขได้
     *@author kingland
     *@date 05/09/2549
     */
    private boolean checkDataEdit() {
        boolean result = true;
        if(theFamilyPlaning != null && !"".equals(theFamilyPlaning.getObjectId())
        && theFamilyPlaning.getObjectId() != null) {
            if(pcuobject.getVisit() != null && !theFamilyPlaning.visit_id.equals(pcuobject.getVisit().getObjectId())) {
                theUS.setStatus(GutilPCU.getTextBundle("NotSavePreviousVisit"), UpdateStatus.WARNING);//ไม่สามารถแก้ไขข้อมูลของการรับบริการครั้งก่อนได้
                result = false;
            } else if(pcuobject.getVisit() == null && !"".equals(theFamilyPlaning.visit_id)) {
                theUS.setStatus(GutilPCU.getTextBundle("NotSavePreviousVisit"), UpdateStatus.WARNING);//ไม่สามารถแก้ไขข้อมูลของการรับบริการครั้งก่อนได้
                result = false;
            }
        }
        return result;
    }
    /**
     * ใช้ตรวจสอบว่าผู้ป่วยสามารถเข้าใช้งานในหน้านี้ได้หรือไม่
     * ต้องเป็น หญิง, อายุไม่ต่ำกว่า 15 ปี หรือสามารถใช้งานได้
     * @param patient ข้อมูลผู้ป่วย
     * @param first ?
     * @return boolean ถ้าเป็น true สามารถเข้าใช้งานได้ ถ้าเป็น false ไม่สามารถใช้งานได้
     */
    public boolean checkBirthday(String birthday,boolean first) {
        boolean result = false;
        if(birthday!= null && birthday.trim().length() >0) {
            String age = DateUtil.calculateAge(birthday,pcuobject.getCurrentDateTime());
            if(age!= null) {
                if(getStringToInt(age) >= 15) {
                    result = true;
                } else {   //อายุไม่ถึง 15 ปี ต้องการเก็บข้อมูลการคลอดหรือไม่
                    if(true) {
                        answer = true;
                        result = true;
                    } else {
                        answer = false;
                        result = false;
                    }
                }
            }
        }
        return result;
    }

    /**ใช้ในการเปลี่ยน String ไปเป็น integer*/
    private int getStringToInt(String string) {
        int integer=0;
        try {
            integer = Integer.parseInt(string);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return integer;
    }

    /**
     * ใช้ในการแสดง Dialog ข้อความขึ้นมาให้ผู้ใช้เห็นไม่ว่าจะเป็น การยืนยัน การเตือน
     * @param massage ข้อความที่ต้องการจะแสดงให้ผู้ใช้ทราบ
     * @param tpye เป็นชนิดของ Dialog ที่ต้องการจะให้แสดง
     * @return ออกไปเป็นผลที่ผู้ใช้ ยืนยัน หรือยกเลิก
     * true : ผู้ใช้ยืนยัน
     * false : ผู้ใช้ไม่ยืนยัน
     * @author ผดุงรัฐ
     */
    public boolean showMessageGUI(String message,int type) {
        message = Constant.getTextBundle(message);
        int choose = 0;
        boolean result = false;
        switch(type) {
            case 1 :    result = theUS.confirmBox(message,UpdateStatus.WARNING);
            break;
            default:    break;
        }
        return result;
    }


    /**
     * ใช้ในตรวจสอบว่า ค่าในผลการตรวจที่เลือก Radio ผิดปกติ มีข้อมูลอยู่หรือไม่
     * @param choose ตัวที่กำหนด ว่าจะให้แสดง text ตัวไหน ให้แสดง
     * choose เป็น 1 แสดง Text ของ Breast
     * choose เป็น 2 แสดง Text ของ UterusCanser
     * @return ผลจากการตรวจสอบ ถ้าไม่มีข้อมูลจะแสดง เป็น true ถ้ามีจะแสดงเป็น false
     * @author ผดุงรัฐ
     */
    public boolean checkResultExamIsNotData(int choose) {
        String value;
        boolean check = false;
        switch(choose) {
            case 1 :    if(jRadioButtonBreastAbNormal.isSelected()) {
                value = jTextAreaResultTreatmentBreast.getText().trim() ;
                if(value.length() ==0) {
                    check = true;
                }
            }
            break;
            case 2:     if(jRadioButtonUterusCancerAbNormal.isSelected()) {
                value = jTextAreaTreatmentUterusCancer.getText().trim() ;
                if(value.length() ==0) {
                    check = true;
                }
            }
            break;
            default:    break;
        }


        return check;
    }
    /**
     * เซตการวิธีการตรวจ
     * @param  -
     * @return void
     * @authur Tong
     * @date -
     */
    private void setMethode(int i) {
        switch(i) {
            case 1 :
                jRadioButtonUterusCancerPap.setSelected(true);
                jRadioButtonUterusCancerVia.setSelected(false);
                break;
            case 2 :
                jRadioButtonUterusCancerVia.setSelected(true);
                jRadioButtonUterusCancerPap.setSelected(false);
                break;
            default :
                jRadioButtonUterusCancerPap.setSelected(false);
                jRadioButtonUterusCancerVia.setSelected(false);
                break;
        }
    }
    /**
     * ใช้ในการกำหนดให้สามารถจะพิมพ์ผล การตรวจเมื่อ เลือ Radio เป็น ผิดปกติ
     * @param choose ตัวที่กำหนด ว่าจะให้แสดง text ตัวไหน ให้แสดง
     * choose เป็น 1 แสดง Text ของ Breast
     * choose เป็น 2 แสดง Text ของ UterusCanser
     * @author ผดุงรัฐ
     */
    public void setTextResultIsEditable(int choose) {
        switch(choose) {
            case 1 :    jTextAreaResultTreatmentBreast.setEditable(false);
//            jTextAreaResultTreatmentBreast.setText("");
            if(jRadioButtonBreastAbNormal.isSelected() || jRadioButtonBreastWait.isSelected()
            || jRadioButtonBreastNormal.isSelected()) {
                jTextAreaResultTreatmentBreast.setEditable(true);
                jTextAreaResultTreatmentBreast.requestFocus();
            }
            break;
            case 2:     jTextAreaTreatmentUterusCancer.setEditable(false);
//            jTextAreaTreatmentUterusCancer.setText("");
            if(jRadioButtonUterusCancerAbNormal.isSelected() || jRadioButtonUterusCancerNormal.isSelected()
            || jRadioButtonUterusCancerWait.isSelected()) {
                jRadioButtonUterusCancerPap.setEnabled(true);
                jRadioButtonUterusCancerVia.setEnabled(true);
                jTextAreaTreatmentUterusCancer.setEditable(true);
                jTextAreaTreatmentUterusCancer.requestFocus();
            } else {
                jRadioButtonUterusCancerPap.setEnabled(false);
                jRadioButtonUterusCancerVia.setEnabled(false);
                jRadioButtonUterusCancerPap.setSelected(false);
                jRadioButtonUterusCancerVia.setSelected(false);
            }
            break;
            default:    break;
        }
    }

    /**
     * ใช้ในการเพิ่มข้อมูล การวางแผนครอบครัว ลงฐานข้อมูล
     * @param boolean first = เป็นการเพิ่มข้อมูลครั้งแรกหรือไม่
     * @return void
     * @author Tong
     * @date 01/09/2549
     */
    public void addFamilyPlaning(boolean first)
    {   //ตรวจสอบว่ามีการบันทึกข้อมูลแล้วหรือยัง สำหรับผู้ป่วยที่ Visit
        checkFirstSave();
        if(checkAddFamily() == true) {
            FamilyPlaning fp = new FamilyPlaning();
            fp.update_record_date_time = pcuobject.getCurrentDateTime();
            setFamilyPlaning(fp);
            setEnableButton(true,1,true);
            //ให้แสดงการนัดในทุกกรณี setVisibleSurvey(visit_id);
            jButtonSave.setEnabled(true);
            jTableFP.clearSelection();
        } else {
            if(vFP == null || vFP.size()==0) {
//                jButtonDel.setEnabled(false);
            } else {
                jButtonDel.setEnabled(true);
            }
        }
    }

    /**
     * ตรวจสอบเงื่อนไข ว่าผู้ป่วยหรือประชากรได้ บันทึกข้อมูลหรือไม่
     * @param  -
     * @return -
     * @author kingland
     * @date 25-05-2549
     */
    private void checkFirstSave() {
        if(pcuobject.getVisit() == null) {
            firstIns = true;
        } else if(pcuobject != null || pcuobject.getVisit() != null || !"".equals(pcuobject.getVisit())) {
            firstIns = true;
            for(int i=0;vFP != null && i<vFP.size();i++) {
                FamilyPlaning fp =(FamilyPlaning)vFP.get(i);
                if(pcuobject.getVisit().getObjectId().equals(fp.visit_id));
                {   firstIns = false;
                    break;
                }
            }
        }
    }
    /**
     * ตรวจสอบเงื่อนไข ตอนกดปุ่ม +
     * @param -
     * @return boolean true = ถูกต้อง false = ไม่ถูกต้อง
     * @author kingland
     * @date 25-05-2549
     */
    private boolean checkAddFamily() {
//        boolean result = true;
//        if(firstIns == false && isVisitSame()) {
//            result = false;
//            theUS.setStatus(GutilPCU.getTextBundle("VisitAlready"),UpdateStatus.WARNING);
//        } else if(result == true && pcuobject.getVisit() == null && checkBirthday(pcuobject.getFamily().patient_birthday,firstIns) == false) {
//            result = false;
//        }
//        return result;
        return true;
    }
    /**
     *ตรวจสอบว่ามี Visit ที่ซ้ำกันหรือไม่
     *@param -
     *@return boolean false=ไม่ซ้ำ   true=ซ้ำ
     *@author Tong
     *@date 01/09/2549
     */
    private boolean isVisitSame()
    {
        if(pcuobject.getVisit()==null)
            return false;

        if(vFP == null)
            return false;

        for(int i=0;i<vFP.size();i++)
        {
            FamilyPlaning fp = (FamilyPlaning)vFP.get(i);
            if(pcuobject.getVisit().getObjectId().equals(fp.visit_id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * ใช้ในการลบข้อมูล การวางแผนครอบครัวออกจากตาราง
     * โดย ถ้ารายการนั้นเป็นวันเดียวกับที่บันทึก จะลบออกจากตาราง
     *    ถ้ารายการนั้นเป็นคนละวันกับที่บันทึก จะเปลี่ยนสถานะ active เป็น 0
     * @param -
     * @return void
     * @author Tong
     * @date 01/09/2549
     */
    public void deleteFamilyPlaning()
    {
        if(this.jTableFP.getSelectedRow()==-1){
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ",UpdateStatus.WARNING);
            return;
        }
        if(!checkBeforDel()) {
            return;
        }
        theFamilyPlaning.health_family_planing_active = "0";
        theFamilyPlaning.health_family_planing_staff_remove = pcuobject.getEmployee().getObjectId();
        theFamilyPlaning.update_record_date_time = pcuobject.getCurrentDateTime();
        FamilyPlaning fp  = theHC.theFamilyPlaningControl.deleteFamilyPlaningByKeyID(theFamilyPlaning);
        if(fp==null)
            return;
        updateTable(null);
    }

    /**
     * ตรวจสอบรายการวางแผนครอบครัวว่าสามารถลบได้หรือไม่
     * @return boolean true = ถูกต้อง false = ไม่ถูกต้อง
     * @param -
     * @author kingland
     * @date 29/05/2549
     */
    private boolean checkBeforDel() {
        boolean result = true;
        //ตรวจว่ารายการมี Visit
        if(theFamilyPlaning != null && !"".equals(theFamilyPlaning.visit_id)) {
            if(pcuobject.getVisit() != null && !"".equals(pcuobject.getVisit().getObjectId())) {   //ถ้า visit id ตรงกันสามารถลบได้
                if(theFamilyPlaning.visit_id.equals(pcuobject.getVisit().getObjectId())) {
                    result = true;
                }
                //ถ้า visit id ไม่ตรงกันไม่สามารถลบได้
                else {
                    result = false;
                    theUS.setStatus(GutilPCU.getTextBundle("NotDeleteBeforVisit"), UpdateStatus.WARNING);
                }
            } else {
                result = false;
                theUS.setStatus(GutilPCU.getTextBundle("NotDeleteBeforVisit"), UpdateStatus.WARNING);
            }
        }
        //ตรวจสอบว่ารายการไม่มี Visit
        else if(result == true && theFamilyPlaning != null &&  ("".equals(theFamilyPlaning.visit_id) || theFamilyPlaning.visit_id == null)) {
            result = true;
        }
        return result;
    }
    /**
     * ใช้ในการแสดงปุ่ม บันทึก,เพิ่ม และ ลบ ตามเหตุการณ์ต่างๆ
     * @param b ให้แสดงหรือไม่แสดง false : ไม่แสดง true : แสดง
     * @param choose ตัวระบุการทำงาน โดยกำหนดให้
     * @param showadd เป็นตัวกำหนดให้ปุ่ม add สามารถทำงานได้หรือไม่
     * 1 ปุ่มลบจะทำงานตรงข้ามกับ ข้อมูลที่ กำหนดให้ b ทันที่
     * 0 ปกติ
     * @author ผดุงรัฐ
     */
    private void setEnableButton(boolean b,int choose,boolean showadd ) {   // กำหนดให้ปุ่มบวกทำงาน ตาม boolean ของ showadd
        jButtonAdd.setEnabled(showadd);
        // กำหนดให้ปุ่มลบทำงาน ตาม boolean ของ b
        jButtonDel.setEnabled(b);
        //ตรวจสอบ ค่า choose เป็น 1 หรือไม่
        if(choose == 1) {    // ถ้าใช้ กำหนดให้ปุ่ม ลบ ทำงานที่ตรงข้ามกับ b ที่รับเข้ามา
            jButtonDel.setEnabled(!b);
        }
    }

    /**
     * ใช้ในการแสดงข้อมูลออกมา หลังจากที่เลือกข้อมูลในตาราง
     * การแสดงวันที่เข้ารับบริการวางแผนครอบครัว
     * @param row แถวที่ได้เลือก
     *
     * @author ผดุงรัฐ
     */
    public void selectTableFP(int row) {
        int selectRowInTable = row;
        if(row != -1) {
            theFamilyPlaning = (FamilyPlaning)vFP.get(row);
            theFamilyPlaning = theHC.theFamilyPlaningControl.selectFamilyPlaningByKeyID(theFamilyPlaning.getObjectId());
            Constant.println(theFamilyPlaning.patient_id);
            setFamilyPlaning(theFamilyPlaning);
            setEnableButton(true,0,true);
        }
    }

    /**
     *  ตรวจสอบ ข้อมูลที่อยู่ใน List ของ ตาราง ว่าเคยเข้ารับบริการหรือไม่ ถ้าเคย จะ return เป็น true
     *  ถ้าไม่เคย เป็น false
     *  @return เป็น boolean true จะมีข้อมูลการเข้ารับบริการแล้ว  false ไม่มีข้อมูลซ้ำ
     */
    private boolean checkServiceForVisitIDInVector() {   // ไม่ซ้ำเป็น false
        boolean result = false;
        if(vFP != null) {
            int size = vFP.size();
            for(int i =0 ; i < size ; i++) {
                FamilyPlaning fp = (FamilyPlaning)vFP.get(i);
                if(fp.visit_id.equals(pcuobject.getVisit().getObjectId())) {
                    result = true;
                    break;
                }
                fp = null;
            }
        }
        return result;
    }

    /**
     * วางแผนครอบครัว
     * new Pattern by Henbe 07/06/2549
     * สงสัยอะไรให้ถามและให้ทำตามรูปแบบนี้ทั้งหมดในฟังชันที่เกี่ยวกับ list ทั้งหมด
     */
    private void updateTable(FamilyPlaning fp) { 
        Vector vFP = theHC.listFamilyPlaning(pcuobject.getPatient(),pcuobject.getFamily());
        setFamilyPlaningV(vFP);
        setEnableButton(false,0,true);
        setFamilyPlaning(fp);
        for(int i=0;fp!=null && i<this.vFP.size();i++){
            FamilyPlaning pp = (FamilyPlaning)vFP.get(i);
            if(pp.getObjectId().equals(fp.getObjectId())){
                this.jTableFP.setRowSelectionInterval(i,i);
                return ;
            }
        }
    }
    /**
     * ใช้ในการแสดงข้อมูลลงตาราง ของ GUI
     * @param vc เป็น Vector ของ Object FamilyPlaning ที่ใช้ในการแสดงผลบนตาราง
     * @return void
     * @author Tong
     * @date 01/09/2549
     */
    private void setFamilyPlaningV(Vector vc) {
        this.vFP = vc;
        TableModel tablemodel = new TableModel(headTableFP,vc.size());
        for(int i = 0 ; i < vc.size() ; i++) {
            String vn = "";
            FamilyPlaning fp = (FamilyPlaning)vc.get(i);
            if(fp.health_family_planing_vn.equals("null")||fp.health_family_planing_vn.equals(""))
                vn = GutilPCU.getTextBundle("NoVN");
            else
                vn = fp.health_family_planing_vn;
            tablemodel.setValueAt(GutilPCU.changDateToString(fp.update_record_date_time,false), i, 1);
            tablemodel.setValueAt(vn, i, 0);
        }
        jTableFP.setModel(tablemodel);
        jTableFP.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTableFP.getColumnModel().getColumn(0).setCellRenderer(TableRenderer.getRendererCenter());
        jTableFP.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableFP.getColumnModel().getColumn(1).setCellRenderer(TableRenderer.getRendererCenter());
    }

    /**
     * ใช้ในการ บันทึกและแก้ไขข้อมูลของการวางแผนครอบครัว
     * และทำการบันทึกข้อมูลลงตารางตรวจรักษาด้วยหากรายการเวชภัณฑ์วางแผนครอบครัวนั้นมีการกำหนดให้บันทึกลงฐานข้อมูล
     * @param -
     * @return void
     * @author Tong
     * @modify kingland
     * @date 01/09/2549
     */
    public void saveFpWoman()
    {
        Constant.println("------------------saveFpWoman by Neung-------------------");
        if (pcuobject.getFamily() == null) {
            theUS.setStatus("กรุณาเลือกประชากรหรือบันทึกข้อมูลประชากรก่อน",UpdateStatus.WARNING);
            return;
        }
        // Somprasong comment 221209 not use
//        if(!checkBirthday(pcuobject.getFamily().patient_birthday,false))
//            return;

        FamilyPlaning fp = getFamilyPlaning();
//            System.out.println(fp.f_health_family_planing_method_id);
        if(!checkDataEdit()) {
            return;
        }
        if(checkResultExamIsNotData(1) && checkResultExamIsNotData(2)) {
            boolean result = showMessageGUI("ผลการตรวจมะเร็งเต้านมและมะเร็งปากมดลูกยังไม่ได้กรอก ต้องการจะกรอกหรือไม่",1);
            if(!result)
                return;
        } else if(checkResultExamIsNotData(1)) {
            boolean result = showMessageGUI("ผลการตรวจมะเร็งเต้านมยังไม่ได้กรอก ต้องการจะกรอกหรือไม่",1);
            if(!result)
                return;
        } else if(checkResultExamIsNotData(2)) {
            boolean result = showMessageGUI("ผลการตรวจมะเร็งปากมดลูกยังไม่ได้กรอก ต้องการจะกรอกหรือไม่",1);
            if(!result)
                return;
        }
        saveAppointment(fp);
        if(pcuobject.getPatient()!=null
                && !fp.health_famlily_planing_supply.equals(theDefaultComboFix.code)) {
            Constant.println("------------------saveFpWoman by Neung------------------if(!fp.health_famlily_planing_supply.equals(theDefaultComboFix.code))-");
            theHC.theFamilyPlaningControl.saveItenSupplyFPInOrder(fp);
        }
        int ret = theHC.theFamilyPlaningControl.saveFamilyPlaning(fp);
//            System.out.println(fp.f_health_family_planing_method_id);
        if(ret==0)
            return;

        updateTable(fp);

    }
    /**
     * เป็นฟังก์ชั่นที่ใช้ในการนัดผู้ป่วย
     * @param familyPlaning = ข้อมูลวางแผนครอบครัว
     * @return void
     * @author kingland
     * @date 01/09/2549
     */
    public void saveAppointment(FamilyPlaning familyPlaning) {
        Constant.println("------------------saveAppointment-------------------");
        Appointment app = theHC.theFamilyPlaningControl.checkFamilyPlaningAppointment(familyPlaning);
        if(app != null) {
            app.clinic_code = pcuobject.getServicePoint().getObjectId();
            app.aptype = "วางแผนครอบครัว";
            app.status = "0";
            if(pcuobject.getPatient()==null){
                theUS.setStatus(GutilPCU.getTextBundle("No_Patient_Appointment"),UpdateStatus.WARNING);
                return;
            }
            app.patient_id = pcuobject.getPatient().getObjectId();
            app.visit_id = pcuobject.getVisit().getObjectId();
            theHosDialog.showDialogAppointment(theHosManage,pcuobject, app);
        }

    }
    /**
     *  ใช้ในการแสดง Dialog เตือนผู้ใช้ เรื่อง เกิดความผิดพลาดของข้อมูลผู้ป่วย
     */
    private void showDialogErrorDataForPatient() {
        theUS.setStatus(GutilPCU.getTextBundle("ERRORDATA"),UpdateStatus.WARNING);
        //เกิดความผิดพลาดของข้อมูลผู้ป่วย และข้อมูลการเข้ารับบริการ แนะนำ ลองเลือกผู้ป่วยคนอื่นขึ้นมาแสดงข้อมูลก่อน
    }
    /**
     *  ใช้ในการแสดง Dialog เตือนผู้ใช้ เรื่อง ผู้ป่วยไม่ใช่เพศหญิง
     */
    private void showDialogWarningIsSexWoman() {
        theUS.setStatus(GutilPCU.getTextBundle("ISSEXWOMAN"),UpdateStatus.WARNING);
        //ยังไม่ระบุเพศ หรือเป็นเพศชาย ไม่สามารถเข้ารับบริการส่วนนี้ได้
    }
    /**
     *  ใช้ในการแสดง Dialog เตื่อนผู้ป่วย เรื่อง
     */
    private void showDialogWarningCheckVisit() {
        theUS.setStatus(GutilPCU.getTextBundle("WarningCheckVisit"),UpdateStatus.WARNING);
        //ผู้ป่วยยังไม่ได้เปิดVisit
    }


    /**
     * ใช้ในการแสดงข้อมูลออกมาจาก ComboBox ตาม param ทีกำหนด
     * @author ผดุงรัฐ
     * @param combobox ComboBox ที่ต้องการจะนำค่าออกมาใช้งาน
     * @param type เป็นตัวกำหนดค่าที่จะ return ออก
     * true คือ แสดงออกมาเป็น Code
     * false คือ แสดงออกมาเป็น ข้อความ
     * @return แสดงค่าที่ต้องการออกมา
     */
    private String getDataCodeComboBox(JComboBox combobox ,boolean type) {
        if(type) {
            return ComboboxModel.getCodeComboBox(combobox);
        } else {
            return ComboboxModel.getStringConboBox(combobox);
        }
    }

    /**
     * ใช้ในการ กำหนดค่าเริ่มต้นให้กับ Combobox
     * @param combobox ComboBox ที่ต้องการจะให้แสดงค่าเริ่มแรก
     * @param code รหัสที่ต้องการจะให้แสดง เป็นข้อความใน combobox
     * @author ผดุงรัฐ
     */
    private void setInitComboBox(JComboBox combobox,String code) {
        ComboboxModel.setCodeComboBox(combobox,code);
    }



    /**
     * ใช้ในการนำข้อมูลที่ได้จาก Object ของ FamilyPlaing ไปแสดงบน GIU
     * @author ผดุงรัฐ
     */
    private void setFamilyPlaning(FamilyPlaning fp) {
        theFamilyPlaning = fp;
        if(theFamilyPlaning == null) {
            theFamilyPlaning = new FamilyPlaning();
            panelYesNoRadioButtonTestPregnant.setSelected(true);
            dateComboBoxSurvey.setText("");
            jLabelSurveyDate.setSelected(!dateComboBoxSurvey.getText().equals(""));
            dateComboBoxSurvey.setEnabled(!dateComboBoxSurvey.getText().equals(""));
            setInitComboBox(jComboBoxHow, FamilyPlaningMethod.isNotControl());
            setInitComboBox(jComboBoxCauseFp, FamilyPlaningCause.isOther());
            setInitComboBox(jComboBoxMedicalSupplies, "0");
            integerTextFieldNumberOfSon.setText("0");
            integerTextFieldNumMedical.setText("0");
            jTextAreaNote.setText("");
            jTextAreaResultTreatmentBreast.setText("");
            jTextAreaTreatmentUterusCancer.setText("");
            jLabelAppointment.setText("");
            valueSelectGroupBreast(false,FamilyPlaningTreatment.isNotTreatment());
            valueSelectGroupCancer(false,FamilyPlaningTreatment.isNotTreatment());
            setEnableButton(false,0,true);
            return;
        }
        jTextAreaNote.setText(theFamilyPlaning.health_family_planing_notice);
        integerTextFieldNumberOfSon.setText(theFamilyPlaning.health_family_planing_parity);
        integerTextFieldNumMedical.setText(theFamilyPlaning.health_famlily_planing_supply_qty);
        setInitComboBox(jComboBoxHow, theFamilyPlaning.f_health_family_planing_method_id);
        setInitComboBox(jComboBoxMedicalSupplies, theFamilyPlaning.health_famlily_planing_supply);
        setInitComboBox(jComboBoxCauseFp, theFamilyPlaning.f_health_family_planing_id);
        //setInitComboBox(jComboBoxTestPregnant, theFamilyPlaning.health_famlily_planing_pregnant_exam);
        panelYesNoRadioButtonTestPregnant.setSelected(theFamilyPlaning.health_famlily_planing_pregnant_exam);
        //dateComboBoxNextAppDate.setText(Gutil.convertFieldDate(theFamilyPlaning.health_famlily_planing_appointment));
        jLabelAppointment.setText(GutilPCU.changDateToString((theFamilyPlaning.health_famlily_planing_appointment),true));
        valueSelectGroupBreast(false,theFamilyPlaning.health_family_planing_breast_exam);
        jTextAreaResultTreatmentBreast.setText(theFamilyPlaning.health_family_planing_breast_exam_notice);
        valueSelectGroupCancer(false,theFamilyPlaning.health_family_planing_cervix_exam);
        valueSelectCancerMethod(false, theFamilyPlaning.health_family_planing_cervix_method);
        jTextAreaTreatmentUterusCancer.setText(theFamilyPlaning.health_family_planing_cervix_exam_notice);
        dateComboBoxSurvey.setText(Gutil.convertFieldDate(theFamilyPlaning.health_family_planing_date));
        this.jLabelSurveyDate.setSelected(!dateComboBoxSurvey.getText().equals(""));
        this.jLabelVN.setText("");
        if(!theFamilyPlaning.visit_id.equals("")){
            String vn_id = this.theAllComboBoxControl.readVNbyVid(theFamilyPlaning.visit_id);
            this.jLabelVN.setText("VN:"+vn_id);
        }
        this.dateComboBoxCheck.setText(DateUtil.convertFieldDate(theFamilyPlaning.update_record_date_time));
        if(theFamilyPlaning.update_record_date_time.length()>11)
            this.timeTextFieldCheck.setText(theFamilyPlaning.update_record_date_time.substring(11));
    }
    /**
     * ใช้ในการกำหนดค่าจาก GUI ให้กับ Object
     * @param -
     * @return  true =  false =
     * @author Tong
     * @modify kingland
     * @date 01/09/2549
     */
    private FamilyPlaning getFamilyPlaning() {
        boolean result = false;

        if(!result) {
            //ถ้าไม่มี visit ให้ทำการอ่านวันที่สำรวจด้วย
            //ถ้ามี visit
            //ถ้าเป็นการเพิ่มข้อมูลใหม่ ให้อ่าน Visit เข้าไปด้วย
            //ถ้าไม่ได้เป็นข้อมูลใหม่ ให้ตรวจสอบว่าเป็นข้อมูลสำรวจหรือไม่
            //ถ้าใช่ให้ทำการอ่านข้อมูลวันที่สำรวจด้วย โดยไม่ต้องบันทึก Visit

            theFamilyPlaning.family_id = pcuobject.getFamily().getObjectId();
            theFamilyPlaning.f_health_family_planing_id =  getDataCodeComboBox(jComboBoxCauseFp, true);
            theFamilyPlaning.f_health_family_planing_method_id = getDataCodeComboBox(jComboBoxHow, true);
            theFamilyPlaning.health_family_planing_breast_exam = valueSelectGroupBreast(true, null);
            theFamilyPlaning.health_family_planing_breast_exam_notice = Gutil.CheckReservedWords(jTextAreaResultTreatmentBreast.getText());
            theFamilyPlaning.health_family_planing_cervix_exam = valueSelectGroupCancer(true, null);
            theFamilyPlaning.health_family_planing_cervix_exam_notice  = Gutil.CheckReservedWords(jTextAreaTreatmentUterusCancer.getText());
            theFamilyPlaning.health_family_planing_notice = Gutil.CheckReservedWords(jTextAreaNote.getText());
            theFamilyPlaning.health_family_planing_parity = integerTextFieldNumberOfSon.getText();
            theFamilyPlaning.health_family_planing_cervix_method = valueSelectCancerMethod(true, null);
            theFamilyPlaning.health_famlily_planing_pregnant_exam = panelYesNoRadioButtonTestPregnant.getStringSelect();
            theFamilyPlaning.health_famlily_planing_supply = getDataCodeComboBox(jComboBoxMedicalSupplies,true);
            theFamilyPlaning.health_famlily_planing_supply_qty= integerTextFieldNumMedical.getText();
            theFamilyPlaning.health_family_planing_staff_remove = "";
            theFamilyPlaning.health_family_planing_active = "1";
            if(theAppointment!=null) {
                theFamilyPlaning.health_famlily_planing_appointment = theAppointment.appoint_date;
            }

            if(theFamilyPlaning.getObjectId()==null){
                if (pcuobject.getPatient()!=null) {
                    theFamilyPlaning.health_family_planing_hn = pcuobject.getPatient().hn;
                    theFamilyPlaning.patient_id = pcuobject.getPatient().getObjectId();
                }
                if(theFamilyPlaning.getObjectId() == null) {
                    theFamilyPlaning.health_family_planing_staff_record = pcuobject.getEmployee().getObjectId();
                    theFamilyPlaning.record_date_time = pcuobject.getCurrentDateTime();
                }
                if(pcuobject.getVisit()!=null){
                    theFamilyPlaning.health_family_planing_date = pcuobject.getCurrentDateTime().substring(0,10);
                    theFamilyPlaning.visit_id = pcuobject.getVisit().getObjectId();
                    theFamilyPlaning.health_family_planing_vn = pcuobject.getVisit().vn;
                }
            }
            if(pcuobject.getVisit()==null)
            {
                theFamilyPlaning.health_family_planing_date = dateComboBoxSurvey.getText();
                if(theFamilyPlaning.health_family_planing_date.equals("")) {
                    theUS.setStatus(GutilPCU.getTextBundle("WarningNotHaveSurVeyDate"), UpdateStatus.WARNING);
                    dateComboBoxSurvey.requestFocus();
                    return null;
                }
                if(theFamilyPlaning.health_family_planing_date.length() != 10 ) {
                    theUS.setStatus(GutilPCU.getTextBundle("WarningNotHaveSurVeyDate"), UpdateStatus.WARNING);
                    dateComboBoxSurvey.requestFocus();
                    return null;
                }
            }
            theFamilyPlaning.health_family_planing_staff_update = pcuobject.getEmployee().getObjectId();
            theFamilyPlaning.record_date_time = pcuobject.getCurrentDateTime();
            theFamilyPlaning.health_family_planing_date = dateComboBoxSurvey.getText();
            theFamilyPlaning.update_record_date_time = dateComboBoxCheck.getText()+","+timeTextFieldCheck.getText()+":00";
        }
        return theFamilyPlaning;
    }
    /**
     *
     * @param
     * @return
     * @authur
     * @date
     */
    private String valueSelectCancerMethod(boolean choose,String set) {
        String value = "";
        if(choose) {
            if(jRadioButtonUterusCancerPap.isSelected()) {
                value =  FamilyPlaningTreatment.isPap();
            }
            if(jRadioButtonUterusCancerVia.isSelected()) {
                value =  FamilyPlaningTreatment.isVia();
            }
        } else {
            setMethode(0);
            jRadioButtonUterusCancerPap.setSelected(false);
            jRadioButtonUterusCancerVia.setSelected(false);
            if(set != null) {
                if(set.length() >0) {
                    if(set.equals(FamilyPlaningTreatment.isPap())) {
                        jRadioButtonUterusCancerPap.setSelected(true);
                    }
                    if(set.equals(FamilyPlaningTreatment.isVia())) {
                        jRadioButtonUterusCancerVia.setSelected(true);
                    }
                }
            }
            setTextResultIsEditable(1);
        }
        return value;
    }
    /**
     * ใช้ในการ แสดงข้อมูลที่เลือกจาก Radio ของกลุ่ม Breast อยู่ในรูปของ String
     * ที่เป็น 0,1,2
     * @author ผดุงรัฐ
     * @param choose ใช้ในการ เลือกว่าต้องการจะให้ return ค่า หรือ ต้องการจะให้กำหนดลงตาราง
     * true ให้ Return ค่าตามกลุ่มที่เลือก
     * false ให้ set ค่าลง Radio ตามค่าที่ส่งมา
     * @param set ค่าที่ต้องการกำหนดลง Radio จะ ต้องกำหนด choose เป็น false
     * @return ผลที่ได้จากกาเลือก Radio ของกลุ่ม Breast
     */
    private String valueSelectGroupBreast(boolean choose,String set) {
        String value = FamilyPlaningTreatment.isNotTreatment();
        if(choose) {
            if(jRadioButtonBreastNormal.isSelected()) {
                value =  FamilyPlaningTreatment.isNormal();
            }
            if(jRadioButtonBreastAbNormal.isSelected()) {
                value =  FamilyPlaningTreatment.isAbNormal();
            }
            if(jRadioButtonBreastNoTreat.isSelected()) {
                value =  FamilyPlaningTreatment.isNotTreatment();
            }
            if(jRadioButtonBreastWait.isSelected()) {
                value =  FamilyPlaningTreatment.isWait();
            }
        } else {
            jRadioButtonBreastNoTreat.setSelected(true);
            if(set != null) {
                if(set.length() >0) {
                    if(set.equals(FamilyPlaningTreatment.isNormal())) {
                        jRadioButtonBreastNormal.setSelected(true);
                    }
                    if(set.equals(FamilyPlaningTreatment.isAbNormal())) {
                        jRadioButtonBreastAbNormal.setSelected(true);
                    }
                    if(set.equals(FamilyPlaningTreatment.isWait())) {
                        jRadioButtonBreastWait.setSelected(true);
                    }
                }
            }
            setTextResultIsEditable(1);
        }
        return value;
    }
    /**
     * ใช้ในการ แสดงข้อมูลที่เลือกจาก Radio ของกลุ่ม Cancer อยู่ในรูปของ String
     * ที่เป็น 0,1,2
     * @author ผดุงรัฐ
     * @param choose ใช้ในการ เลือกว่าต้องการจะให้ return ค่า หรือ ต้องการจะให้กำหนดลงตาราง
     * true ให้ Return ค่าตามกลุ่มที่เลือก
     * false ให้ set ค่าลง Radio ตามค่าที่ส่งมา
     * @param set ค่าที่ต้องการกำหนดลง Radio จะ ต้องกำหนด choose เป็น false
     * @return ผลที่ได้จากกาเลือก Radio ของกลุ่ม Cancer
     */
    private String valueSelectGroupCancer(boolean choose,String set) {
        String value = FamilyPlaningTreatment.isNotTreatment();
        if(choose) {

            if(jRadioButtonUterusCancerNormal.isSelected()) {
                value = FamilyPlaningTreatment.isNormal();
            }
            if(jRadioButtonUterusCancerAbNormal.isSelected()) {
                value = FamilyPlaningTreatment.isAbNormal();
            }
            if(jRadioButtonUterusCancerNoTreat.isSelected()) {
                value = FamilyPlaningTreatment.isNotTreatment();
            }
            if(jRadioButtonUterusCancerWait.isSelected()) {
                value = FamilyPlaningTreatment.isWait();
            }
        } else {
            if(set != null) {
                if(set.length() >0) {
                    if(set.equals(FamilyPlaningTreatment.isNotTreatment())) {
                        jRadioButtonUterusCancerNoTreat.setSelected(true);
                        jRadioButtonUterusCancerPap.setEnabled(false);
                        jRadioButtonUterusCancerPap.setSelected(false);
                        jRadioButtonUterusCancerVia.setEnabled(false);
                        jRadioButtonUterusCancerVia.setSelected(false);
                    } else if(set.equals(FamilyPlaningTreatment.isNormal())) {
                        jRadioButtonUterusCancerNormal.setSelected(true);
                    }

                    else if(set.equals(FamilyPlaningTreatment.isAbNormal())) {
                        jRadioButtonUterusCancerAbNormal.setSelected(true);
                    } else if(set.equals(FamilyPlaningTreatment.isWait())) {
                        jRadioButtonUterusCancerWait.setSelected(true);
                    }
                }
            } else {
                jRadioButtonUterusCancerNoTreat.setSelected(true);
                jRadioButtonUterusCancerPap.setEnabled(false);
                jRadioButtonUterusCancerPap.setSelected(false);
                jRadioButtonUterusCancerVia.setEnabled(false);
                jRadioButtonUterusCancerVia.setSelected(false);
            }
            setTextResultIsEditable(2);
        }
        return value;
    }
    /**
     * ใช้ในการกำหนด วันที่นัด เมื่อเลือกรายการ เวชภัณฑ์หรือรายการยา ถ้าไม่กำหนด จะเป็นวันที่ปัจจุบัน
     * @param select ข้อมูลที่ใช้ในการค้นหา
     * @author ผดุงรัฐ
     */
    public void selectComboBoxMedicalSupplies(String select) {
        //ตรวจสอบค่าที่ได้รับจาก ComboBox เป็น null หรือไม่
        if(theDefaultComboFix != null) {
            //ตรวจสอบค่าที่รับมาจาก ComboBox
            if(!theDefaultComboFix.code.equals(select)) {   //ทำการเปลี่ยนวันที่นัดให้เป็นวันที่ปัจจุบัน + ระยะเวลาการนัด ที่ถูกกำหนดใน setup
                //dateComboBoxNextAppDate.setText(theHosManage.theHosControl.theFamilyPlaningControl.getDateFromObjectSupply(vSupplies,selectcomboboxmedicalsupplies));
                //กำหนดให้กรอกจำนวนเวชภัณฑ์ได้
                integerTextFieldNumMedical.setEnabled(true);
            } else {
                //ทำการเปลี่ยนวันที่นัดให้เป็นวันที่ปัจจุบัน
                //dateComboBoxNextAppDate.setText(theHosManage.theHosControl.theFamilyPlaningControl.convertDayToDate());
                //กำหนดไม่ให้กรอกจำนวนเวชภัณฑ์
//                integerTextFieldNumMedical.setEnabled(false);
                //กำหนดจำนวนเวชภัณฑ์ ให้เป็น 0
                integerTextFieldNumMedical.setText("0");
            }

        }
    }
    /**
     * ใช้ในการกำหนดให้ รายการ supply แสดงตามกลุ่มการคุมกำเนิด เช่น ให้เป็นเม็ด จะค้นหารายการ
     * supply ที่เป็นเม็ดให้
     * @param selectcomboboxmethod คือ ค่าที่ใช้ตรวจสอบว่าคือกลุ่มของ supply หรือไม่เพื่อจะให้รายการ supply
     * แสดงขึ้นมาให้เป็นกลุ่มๆ
     * @return void
     * @author ผดุงรัฐ
     * @date 01/09/2549
     */
    public void selectSupplyByMethod(String selectcomboboxmethod) {
        if(vSupplies != null && !vSupplies.isEmpty()) {
            Vector vTempSupplies = new Vector();
            final int size = vSupplies.size();
            for(int i = 0;i< size ;i++) {
                theFamilyPlaningSupplyGroup = (FamilyPlaningSupplyGroup)vSupplies.get(i);
                if(theFamilyPlaningSupplyGroup.f_health_family_planing_group_type_id.equals(selectcomboboxmethod)) {
                    vTempSupplies.add(theFamilyPlaningSupplyGroup);
                }
            }
            if(vTempSupplies.size() != 0) {
                ComboboxModel.initComboBox(jComboBoxMedicalSupplies,theHC.theFamilyPlaningControl.ConvertObjectSupplyToComboFix(vTempSupplies));
            } else {
                vTempSupplies.add(theDefaultComboFix);
                ComboboxModel.initComboBox(jComboBoxMedicalSupplies,vTempSupplies);
            }
        }
    }

    /**
     * ใช้เมื่อมีการคลิกเลือก วิธิการคุมกำเนิด
     * โดยจะทำให้ สาเหตุของการไม่คุมกำเนิดแสดง เมื่อเลือก ไม่คุมกำเนิด
     * และจำไปทำให้ เวชภัณฑ์ แสดงเป็นรายการต่างๆตามการเลือก วิธิการคุมกำเนิด
     * @return void
     * @author tong
     * @date 01/09/2549
     */
    public void selectComboBoxHow()
    { /**ตรวจสอบสถานะการเข้ารับบริการ*/
        if(pcuobject.getVisit()!=null && pcuobject.getVisit().visit_status.equals(VisitStatus.isInProcess())) {
            if(theFamilyPlaning != null
                    && theFamilyPlaning.getObjectId()!=null
                    &&!theFamilyPlaning.visit_id.equals(pcuobject.getVisit().getObjectId())) { //เปลี่ยนแปลงรายการเวชภัณฑ์ จะไม่มีผลกับรายการตรวจรักษา
                theUS.setStatus(GutilPCU.getTextBundle("CHANGEFPSUPPLY"),UpdateStatus.WARNING);
            }
        }
        String selectComboBoxMethod = ComboboxModel.getCodeComboBox(jComboBoxHow);
        selectSupplyByMethod(selectComboBoxMethod);
        boolean not_control = FamilyPlaningMethod.isNotControl().equals(selectComboBoxMethod);
        this.jPanelNotControl.setVisible(not_control);
        this.jPanelControl.setVisible(!not_control);
        if(!not_control)
            setInitComboBox(jComboBoxCauseFp, FamilyPlaningCause.isOther());
    }


    /**
     * เช็ควันที่สำรวจว่าเป็นวันในอนาคตหรือไม่
     * @param -
     * @return void
     * @author jao
     * @date 01/09/2549
     */
    private boolean checkDateSurvey() {
        if(!dateComboBoxSurvey.getText().equals("")
        && dateComboBoxSurvey.getText().length()==10
                && com.pcu.utility.DateUtil.countDay(dateComboBoxSurvey.getText(),theHC.theConnectionInf) == -1
                && com.pcu.utility.DateUtil.isToday(com.pcu.utility.DateUtil.getDateFromText(dateComboBoxSurvey.getText()),theHC.theConnectionInf)==false)
        {
                theUS.setStatus(GutilPCU.getTextBundle("NoDateFuture") , UpdateStatus.WARNING);
                return true;
        }
        return false;
    }

    /**
     *ใช้เพื่อ setenable gui ให้เป็น false ตอนที่ patient เป็น null
     *@param boolean
     *@return void
     *@author amp
     *@modify kingland
     *@date 04/09/2549
     **/
    private void setEnableGui(boolean flag) {
        panelYesNoRadioButtonTestPregnant.setEnabled(flag);
        integerTextFieldNumberOfSon.setEnabled(flag);
        jButtonAppointment.setEnabled(flag);
        jComboBoxHow.setEnabled(flag);
        jComboBoxCauseFp.setEnabled(flag);
        jComboBoxMedicalSupplies.setEnabled(flag);
        integerTextFieldNumMedical.setEnabled(flag);
        jPanelBreastExam.setEnabled(flag);
        jPanelUterusCancer.setEnabled(flag);
        jTextAreaNote.setEnabled(flag);
        jButtonAdd.setEnabled(flag);
        jButtonSave.setEnabled(flag);
        boolean isWomen = checkWomenSex(false);
        jRadioButtonBreastNoTreat.setEnabled(isWomen&&flag);
        jRadioButtonBreastNormal.setEnabled(isWomen&&flag);
        jRadioButtonBreastAbNormal.setEnabled(isWomen&&flag);
        jRadioButtonBreastWait.setEnabled(isWomen&&flag);
        jTextAreaResultTreatmentBreast.setEnabled(isWomen&&flag);
        jRadioButtonUterusCancerNoTreat.setEnabled(isWomen&&flag);
        jRadioButtonUterusCancerNormal.setEnabled(isWomen&&flag);
        jRadioButtonUterusCancerAbNormal.setEnabled(isWomen&&flag);
        jRadioButtonUterusCancerWait.setEnabled(isWomen&&flag);
        jTextAreaTreatmentUterusCancer.setEnabled(isWomen&&flag);
    }
    /**
     *เซตการใช้งาน GUI
     *@param boolean
     *@return void
     *@author kingland
     *@date 05/09/2549
     */
    public void setEnabled(boolean flag) {
        setEnableGui(flag);
    }
    /**
     * ใช้ในการแสดง ภาษาไทยหรืออังกฤษ
     *@param -
     *@return void
     *@author Tong
     *@modify kingland
     *@date 05/09/2549
     */
    private void setLanguage() {  /*jLabel*/
        GutilPCU.setLanguage(jPanelOptionUterusCancer1);
        GutilPCU.setLanguage(jLabel5);
        GutilPCU.setLanguage(jLabel6);
        GutilPCU.setLanguage(jLabelVN);
        jLabelCaseFp.setText(GutilPCU.getTextBundle(jLabelCaseFp.getText()));
        jLabelHow.setText(GutilPCU.getTextBundle(jLabelHow.getText()));
        jLabelSon.setText(GutilPCU.getTextBundle(jLabelSon.getText()));
        jLabelTestPregnant.setText(GutilPCU.getTextBundle(jLabelTestPregnant.getText()));
        jLabelMedicalSupplies.setText(GutilPCU.getTextBundle(jLabelMedicalSupplies.getText()));
        jLabelNumMedical.setText(GutilPCU.getTextBundle(jLabelNumMedical.getText()));
        jLabelNextAppDate.setText(GutilPCU.getTextBundle(jLabelNextAppDate.getText()));
        jLabelRemark.setText(GutilPCU.getTextBundle(jLabelRemark.getText()));
        jLabelSurveyDate.setText(GutilPCU.getTextBundle(jLabelSurveyDate.getText()));
        /*jButton*/
        jButtonAdd.setText(GutilPCU.getTextBundle(jButtonAdd.getText()));
        jButtonDel.setText(GutilPCU.getTextBundle(jButtonDel.getText()));
        jButtonSave.setText(GutilPCU.getTextBundle(jButtonSave.getText()));
        jButtonAppointment.setText(GutilPCU.getTextBundle(jButtonAppointment.getText()));
        /*TitledBorder*/
        GutilPCU.JPanelLabler(jPanelFpList);
        GutilPCU.JPanelLabler(jPanelDetail);
        GutilPCU.JPanelLabler(jPanelBreastExam);
        GutilPCU.JPanelLabler(jPanelUterusCancer);
        /**JRadio*/
        jRadioButtonBreastNoTreat.setText(GutilPCU.getTextBundle(jRadioButtonBreastNoTreat.getText()));
        jRadioButtonBreastAbNormal.setText(GutilPCU.getTextBundle(jRadioButtonBreastAbNormal.getText()));
        jRadioButtonBreastNormal.setText(GutilPCU.getTextBundle(jRadioButtonBreastNormal.getText()));
        jRadioButtonUterusCancerNoTreat.setText(GutilPCU.getTextBundle(jRadioButtonUterusCancerNoTreat.getText()));
        jRadioButtonUterusCancerNormal.setText(GutilPCU.getTextBundle(jRadioButtonUterusCancerNormal.getText()));
        jRadioButtonUterusCancerAbNormal.setText(GutilPCU.getTextBundle(jRadioButtonUterusCancerAbNormal.getText()));
        jRadioButtonBreastWait.setText(GutilPCU.getTextBundle(jRadioButtonBreastWait.getText()));
        jRadioButtonUterusCancerWait.setText(GutilPCU.getTextBundle(jRadioButtonUterusCancerWait.getText()));
        GutilPCU.setLanguage(headTableFP);
    }
    public void setJFrame(JFrame frame) {
        theFrame = frame;
        initBalloon();
    }
    public JFrame getJFrame() {
        return theFrame;
    }
    /**
     *เซตสถานะ
     *@param massege = ข้อความที่ต้องการแสดง status = สถาน
     *@return void
     *@author kingland
     *@date 01/09/2549
     */

    public void notifyDeletePatient(String str, int param) {
    }

    public void notifyDeletePatientPayment(String str, int param) {
    }

    public void notifyManageDrugAllergy(String str, int param) {
    }

    public void notifyReadPatient(String str, int param) {
    }

    public void notifyResetPatient(String str, int param) {
    }

    public void notifySaveAppointment(String str, int param) {
        if(receiveNotify==1) {
            theAppointment = new Appointment();
            theAppointment = theHosDialog.theDialogAppointment.getAppointment();
            jLabelAppointment.setText(GutilPCU.changDateToString((theAppointment.appoint_date),true));
            FamilyPlaning fp = getFamilyPlaning();
            fp.health_famlily_planing_appointment = theAppointment.appoint_date;
            theHC.theFamilyPlaningControl.saveFamilyPlaning(fp);
        }
    }


    public void notifySavePatient(String str, int param) {
    }


    public void notifySavePatientPayment(String str, int param) {
    }

    public void notifyReadFamily(String str, int status) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupSelectBreast;
    private javax.swing.ButtonGroup buttonGroupSelectCancer;
    private com.hospital_os.utility.DateComboBox dateComboBoxCheck;
    private com.pcu.utility.DateComboBox dateComboBoxSurvey;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.pcu.utility.IntegerTextField integerTextFieldNumMedical;
    private com.pcu.utility.IntegerTextField integerTextFieldNumberOfSon;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAppointment;
    private javax.swing.JButton jButtonDel;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBoxCauseFp;
    private javax.swing.JComboBox jComboBoxHow;
    private javax.swing.JComboBox jComboBoxMedicalSupplies;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAppointment;
    private javax.swing.JLabel jLabelCaseFp;
    private javax.swing.JLabel jLabelHow;
    private javax.swing.JLabel jLabelMedicalSupplies;
    private javax.swing.JLabel jLabelNextAppDate;
    private javax.swing.JLabel jLabelNumMedical;
    private javax.swing.JLabel jLabelRemark;
    private javax.swing.JLabel jLabelSon;
    private javax.swing.JCheckBox jLabelSurveyDate;
    private javax.swing.JLabel jLabelTestPregnant;
    private javax.swing.JLabel jLabelVN;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelBreastExam;
    private javax.swing.JPanel jPanelControl;
    private javax.swing.JPanel jPanelDetail;
    private javax.swing.JPanel jPanelFpDetail;
    private javax.swing.JPanel jPanelFpList;
    private javax.swing.JPanel jPanelHomeVisitControl1;
    private javax.swing.JPanel jPanelNotControl;
    private javax.swing.JPanel jPanelOptionBreast;
    private javax.swing.JPanel jPanelOptionUterusCancer;
    private javax.swing.JPanel jPanelOptionUterusCancer1;
    private javax.swing.JPanel jPanelRemark;
    private javax.swing.JPanel jPanelUterusCancer;
    private javax.swing.JRadioButton jRadioButtonBreastAbNormal;
    private javax.swing.JRadioButton jRadioButtonBreastNoTreat;
    private javax.swing.JRadioButton jRadioButtonBreastNormal;
    private javax.swing.JRadioButton jRadioButtonBreastWait;
    private javax.swing.JRadioButton jRadioButtonUterusCancerAbNormal;
    private javax.swing.JRadioButton jRadioButtonUterusCancerNoTreat;
    private javax.swing.JRadioButton jRadioButtonUterusCancerNormal;
    private javax.swing.JRadioButton jRadioButtonUterusCancerPap;
    private javax.swing.JRadioButton jRadioButtonUterusCancerVia;
    private javax.swing.JRadioButton jRadioButtonUterusCancerWait;
    private javax.swing.JScrollPane jScrollPaneFpList;
    private javax.swing.JScrollPane jScrollPaneResultBreast;
    private javax.swing.JScrollPane jScrollPaneResultCancer;
    private javax.swing.JTable jTableFP;
    private javax.swing.JTextField jTextAreaNote;
    private com.hosv3.gui.component.BalloonTextArea jTextAreaResultTreatmentBreast;
    private com.hosv3.gui.component.BalloonTextArea jTextAreaTreatmentUterusCancer;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoRadioButtonTestPregnant;
    private com.hospital_os.utility.TimeTextField timeTextFieldCheck;
    // End of variables declaration//GEN-END:variables



}
