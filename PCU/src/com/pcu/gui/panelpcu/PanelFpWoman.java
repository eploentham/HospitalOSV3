/*
 * JPanelFp.java
 *
 * Created on 13 �Զع�¹ 2548, 18:24 �.
 * ��Ǩ�ͺ�ѹ������Ǩ����
 * kingland
//henbe comment 100253 kong ������÷Ѵ��
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
    private String[] headTableFP = new String[]{"VN","�ѹ���"};
    /**�� ��ǡ�˹����㹡�� ���͡�����Ţͧ �Ըա�ä�����Դ*/
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
     * @author ��ا�Ѱ
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
        // 2. Init Components �ͧ GUI

        // 3. ��˹����ҷ���麹 GUI
        setLanguage();
        // 4. ��˹������ź� Combobox ����������
        initCombobox();
        // 5. ��˹� �������������Ѻ Object �������� � combobox
        defaultData();
        // 6. ��㹡�� ��˹� ���������鹢ͧ����ʴ��� �� GUI ����觤�� �� true ���͡�˹��������ǡ�ӧҹ
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
     *૵ PCU Object
     *@param PCUobject
     *@return void
     *@author tong
     *@modify kingland
     *@date 05/09/2549
     */
    public void setObject(PCUObject pcuobject) {   /** 仨Ѵ��õ������ͧ�ͧ�����ŷ����Ѻ ��������������բ����� ��鹨з����ҧ�ú�˹�� GUI */
        System.err.println("_henbe__________________________________" + this.getClass().toString());
        this.pcuobject = pcuobject;
        //��˹�����������
        answer = false;
        theFamilyPlaning = null;
        setFamilyPlaning(null);
        //��Ǩ�ͺ�����Ţͧ �������Ѻ��ԡ��
        if(pcuobject.getVisit() != null) {   //Init ������Ѻ Valiable �����㹡�õ�Ǩ�ͺ
            // ��Ǩ�ͺ���͹䢡�÷ӧҹ�ͧ �������Ѻ��ԡ�ä��駹���
            if(pcuobject.getVisit().visit_status.equals(VisitStatus.isInProcess())) {
                jButtonAdd.setEnabled(true);
                jButtonSave.setEnabled(true);
            }
        }
        //��Ǩ�ͺ������ ������
        if(pcuobject.getPatient() != null) {
            setEnableGui(true);
            checkSelectInTable();
            jButtonAdd.setEnabled(true);
            jButtonSave.setEnabled(true);
        }
        //��Ǩ�ͺ������ ��Ъҡ��
        else if(pcuobject.getFamily() != null) {
            setEnableGui(true);
            jButtonAdd.setEnabled(true);
            jButtonSave.setEnabled(true);
        }
        //�ӡ�� list ������ �ʴ������ҧ��������� �������Ѻ��ԡ����Т����ż�����
        //����ʴ���ùѴ㹷ء�ó�
        checkServiceFamilyPlaning();
        //�ӡ�õ�Ǩ�ͺ����ա�����͡�����ź����ҧ������� ������ ��� Disable �����͡��鹷��
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
        //����ʴ���ùѴ㹷ء�ó�

        if(pcuobject.getFamily()!=null) {
            setEnableGui(true);
            jButtonAdd.setEnabled(true);
            jButtonSave.setEnabled(true);
            //����ʴ���ùѴ㹷ء�ó�
        }
        updateTable(null);
    }

    /**��㹡�� Init ����������������Ѻ ComboBox*/
    private void initCombobox() {
        vSupplies = theHC.theFamilyPlaningControl.queryFamilyPlaningSupplies();
        ComboboxModel.initComboBox(jComboBoxMedicalSupplies,theHC.theFamilyPlaningControl.ConvertObjectSupplyToComboFix(vSupplies));
        ComboboxModel.initComboBox(jComboBoxHow,theHC.theAllComboBoxControl.queryAllFamilyPlaningMethod());
        ComboboxModel.initComboBox(jComboBoxCauseFp,theHC.theAllComboBoxControl.queryAllFamilyPlaningCause());
        String yes = GutilPCU.setLanguage("��");
        String no = GutilPCU.setLanguage("�����");
        panelYesNoRadioButtonTestPregnant.setTextYesNo(yes, no);
    }

    private void defaultData() {
        theDefaultComboFix = new ComboFix();
        theDefaultComboFix.code = "0";
        theDefaultComboFix.name = "����к�";
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

        jButtonAppointment.setText("�Ѵ");
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

        jLabelCaseFp.setText("�˵ط����������Դ");
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

        jLabelNumMedical.setText("�ӹǹ");
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

        jLabel5.setText("�ѹ���ѹ�֡");
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

        jLabel6.setText("�.");
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
        jLabel9.setToolTipText("���ҷ���Ǩ");
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

        jPanelOptionUterusCancer1.setBorder(javax.swing.BorderFactory.createTitledBorder("�Ըյ�Ǩ"));
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


    /**��㹡�õ�Ǩ�ͺ�����Ţͧ�������Ѻ��ԡ�� ��ǹ��� ����� visit_id
     * �ç�Ѻ���������������� ��ҵç�ѹ��� �ӡ�����͡�����Ź������
     */
    private void checkServiceFamilyPlaning() {
        int selectRow = -1;
        if(vFP!=null) {
            int size = vFP.size();
            // �ӡ��ǹ�ٻ����������������բ����Ţͧ visit ���駹���������
            for(int i = 0 ;i< size ; i++) {   //����ҷ�� vector ����������˹� i
                FamilyPlaning fp  = (FamilyPlaning)vFP.get(i);
                //��Ǩ�ͺ ��ҷ��ͧ object ��� ������� vector ����դ�Ңͧ visit_id ���ǡѹ��á������Ѻ��ԡ�ä��駹�� �������
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
     *  ��㹡�õ�Ǩ�ͺ����ա�����͡�����ź����ҧ������� �����������ӡ�� Disable ���� ź��鹷��
     *  ����������ա�����͡�����¢����
     */
    private void checkSelectInTable() {   //�Ѻ��ҡ�����͡�ҡ���ҧ�� array �ͧ int
        int[] select = jTableFP.getSelectedRows();
        //��Ǩ�ͺ����ա�����͡�ҡ���� 0 row �������
        if(select.length == 0 ) {   //�ӡ�� Disable ���� ź��鹷��
            setEnableButton(false,0, true);
        }
    }
    /**
     *��Ǩ�ͺ��Ҽ��������ͻ�Ъҡ����ª��Ե�����������
     *@param -
     *@return boolean true=���ª��Ե false=������ª��Ե
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
     *��Ǩ�ͺ�Ȣͧ����Ѻ��ԡ��
     *@param -
     *@return boolean true=��ҹ false=����ҹ
     *@author kingland
     *@date 04/09/2549
     */
    private boolean checkWomenSex(boolean showWarningMessage) {
        boolean result = true;
        //add code by noom ����Ѻ check �� ˭ԧ��ҹ��
        if(pcuobject.getPatient() != null && !("2").equals(pcuobject.getPatient().f_sex_id)) {
            if(showWarningMessage) {
                theUS.setStatus(GutilPCU.getTextBundle("ISSEXWOMAN"),UpdateStatus.WARNING);
                //�ѧ����к��� �������Ȫ�� �������ö����Ѻ��ԡ����ǹ�����
            }
            result = false;
        }
        //����Ѻ check �� ˭ԧ��ҹ��
        else if(pcuobject.getFamily()!=null && !("2").equals(pcuobject.getFamily().f_sex_id)) {
            if(showWarningMessage) {
                theUS.setStatus(GutilPCU.getTextBundle("ISSEXWOMAN"),UpdateStatus.WARNING);
                //�ѧ����к��� �������Ȫ�� �������ö����Ѻ��ԡ����ǹ�����
            }
            result = false;
        } else if(pcuobject.getFamily()!=null && "".equals(pcuobject.getFamily().f_sex_id)) {
            result = false;
        }
        return result;
    }
    /**
     *��Ǩ�ͺ�����䢢����� �������ö��䢢�����
     *@param -
     *@return boolean true=����ö����� false=�������ö�����
     *@author kingland
     *@date 05/09/2549
     */
    private boolean checkDataEdit() {
        boolean result = true;
        if(theFamilyPlaning != null && !"".equals(theFamilyPlaning.getObjectId())
        && theFamilyPlaning.getObjectId() != null) {
            if(pcuobject.getVisit() != null && !theFamilyPlaning.visit_id.equals(pcuobject.getVisit().getObjectId())) {
                theUS.setStatus(GutilPCU.getTextBundle("NotSavePreviousVisit"), UpdateStatus.WARNING);//�������ö��䢢����Ţͧ����Ѻ��ԡ�ä��駡�͹��
                result = false;
            } else if(pcuobject.getVisit() == null && !"".equals(theFamilyPlaning.visit_id)) {
                theUS.setStatus(GutilPCU.getTextBundle("NotSavePreviousVisit"), UpdateStatus.WARNING);//�������ö��䢢����Ţͧ����Ѻ��ԡ�ä��駡�͹��
                result = false;
            }
        }
        return result;
    }
    /**
     * ���Ǩ�ͺ��Ҽ���������ö�����ҹ�˹�ҹ�����������
     * ��ͧ�� ˭ԧ, ��������ӡ��� 15 �� ��������ö��ҹ��
     * @param patient �����ż�����
     * @param first ?
     * @return boolean ����� true ����ö�����ҹ�� ����� false �������ö��ҹ��
     */
    public boolean checkBirthday(String birthday,boolean first) {
        boolean result = false;
        if(birthday!= null && birthday.trim().length() >0) {
            String age = DateUtil.calculateAge(birthday,pcuobject.getCurrentDateTime());
            if(age!= null) {
                if(getStringToInt(age) >= 15) {
                    result = true;
                } else {   //�������֧ 15 �� ��ͧ����红����š�ä�ʹ�������
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

    /**��㹡������¹ String ��� integer*/
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
     * ��㹡���ʴ� Dialog ��ͤ�����������������������Ҩ��� ����׹�ѹ �����͹
     * @param massage ��ͤ�������ͧ��è��ʴ���������Һ
     * @param tpye �繪�Դ�ͧ Dialog ����ͧ��è�����ʴ�
     * @return �͡��繼ŷ������ �׹�ѹ ����¡��ԡ
     * true : ������׹�ѹ
     * false : ���������׹�ѹ
     * @author ��ا�Ѱ
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
     * ��㹵�Ǩ�ͺ��� ���㹼š�õ�Ǩ������͡ Radio �Դ���� �բ����������������
     * @param choose ��Ƿ���˹� ��Ҩ�����ʴ� text ����˹ ����ʴ�
     * choose �� 1 �ʴ� Text �ͧ Breast
     * choose �� 2 �ʴ� Text �ͧ UterusCanser
     * @return �Ũҡ��õ�Ǩ�ͺ �������բ����Ũ��ʴ� �� true ����ը��ʴ��� false
     * @author ��ا�Ѱ
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
     * ૵����Ըա�õ�Ǩ
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
     * ��㹡�á�˹��������ö�о����� ��õ�Ǩ����� ���� Radio �� �Դ����
     * @param choose ��Ƿ���˹� ��Ҩ�����ʴ� text ����˹ ����ʴ�
     * choose �� 1 �ʴ� Text �ͧ Breast
     * choose �� 2 �ʴ� Text �ͧ UterusCanser
     * @author ��ا�Ѱ
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
     * ��㹡������������ ����ҧἹ��ͺ���� ŧ�ҹ������
     * @param boolean first = �繡�����������Ť����á�������
     * @return void
     * @author Tong
     * @date 01/09/2549
     */
    public void addFamilyPlaning(boolean first)
    {   //��Ǩ�ͺ����ա�úѹ�֡���������������ѧ ����Ѻ�����·�� Visit
        checkFirstSave();
        if(checkAddFamily() == true) {
            FamilyPlaning fp = new FamilyPlaning();
            fp.update_record_date_time = pcuobject.getCurrentDateTime();
            setFamilyPlaning(fp);
            setEnableButton(true,1,true);
            //����ʴ���ùѴ㹷ء�ó� setVisibleSurvey(visit_id);
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
     * ��Ǩ�ͺ���͹� ��Ҽ��������ͻ�Ъҡ��� �ѹ�֡�������������
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
     * ��Ǩ�ͺ���͹� �͹������ +
     * @param -
     * @return boolean true = �١��ͧ false = ���١��ͧ
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
     *��Ǩ�ͺ����� Visit ����ӡѹ�������
     *@param -
     *@return boolean false=�����   true=���
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
     * ��㹡��ź������ ����ҧἹ��ͺ�����͡�ҡ���ҧ
     * �� �����¡�ù�����ѹ���ǡѺ���ѹ�֡ ��ź�͡�ҡ���ҧ
     *    �����¡�ù���繤����ѹ�Ѻ���ѹ�֡ ������¹ʶҹ� active �� 0
     * @param -
     * @return void
     * @author Tong
     * @date 01/09/2549
     */
    public void deleteFamilyPlaning()
    {
        if(this.jTableFP.getSelectedRow()==-1){
            theUS.setStatus("��س����͡��¡�÷���ͧ���ź",UpdateStatus.WARNING);
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
     * ��Ǩ�ͺ��¡���ҧἹ��ͺ�����������öź���������
     * @return boolean true = �١��ͧ false = ���١��ͧ
     * @param -
     * @author kingland
     * @date 29/05/2549
     */
    private boolean checkBeforDel() {
        boolean result = true;
        //��Ǩ�����¡���� Visit
        if(theFamilyPlaning != null && !"".equals(theFamilyPlaning.visit_id)) {
            if(pcuobject.getVisit() != null && !"".equals(pcuobject.getVisit().getObjectId())) {   //��� visit id �ç�ѹ����öź��
                if(theFamilyPlaning.visit_id.equals(pcuobject.getVisit().getObjectId())) {
                    result = true;
                }
                //��� visit id ���ç�ѹ�������öź��
                else {
                    result = false;
                    theUS.setStatus(GutilPCU.getTextBundle("NotDeleteBeforVisit"), UpdateStatus.WARNING);
                }
            } else {
                result = false;
                theUS.setStatus(GutilPCU.getTextBundle("NotDeleteBeforVisit"), UpdateStatus.WARNING);
            }
        }
        //��Ǩ�ͺ�����¡������� Visit
        else if(result == true && theFamilyPlaning != null &&  ("".equals(theFamilyPlaning.visit_id) || theFamilyPlaning.visit_id == null)) {
            result = true;
        }
        return result;
    }
    /**
     * ��㹡���ʴ����� �ѹ�֡,���� ��� ź ����˵ء�ó��ҧ�
     * @param b ����ʴ���������ʴ� false : ����ʴ� true : �ʴ�
     * @param choose ����кء�÷ӧҹ �¡�˹����
     * @param showadd �繵�ǡ�˹������� add ����ö�ӧҹ���������
     * 1 ����ź�зӧҹ�ç�����Ѻ �����ŷ�� ��˹���� b �ѹ���
     * 0 ����
     * @author ��ا�Ѱ
     */
    private void setEnableButton(boolean b,int choose,boolean showadd ) {   // ��˹��������ǡ�ӧҹ ��� boolean �ͧ showadd
        jButtonAdd.setEnabled(showadd);
        // ��˹�������ź�ӧҹ ��� boolean �ͧ b
        jButtonDel.setEnabled(b);
        //��Ǩ�ͺ ��� choose �� 1 �������
        if(choose == 1) {    // ����� ��˹������� ź �ӧҹ���ç�����Ѻ b ����Ѻ�����
            jButtonDel.setEnabled(!b);
        }
    }

    /**
     * ��㹡���ʴ��������͡�� ��ѧ�ҡ������͡������㹵��ҧ
     * ����ʴ��ѹ�������Ѻ��ԡ���ҧἹ��ͺ����
     * @param row �Ƿ�������͡
     *
     * @author ��ا�Ѱ
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
     *  ��Ǩ�ͺ �����ŷ������� List �ͧ ���ҧ ���������Ѻ��ԡ��������� ����� �� return �� true
     *  �������� �� false
     *  @return �� boolean true ���բ����š������Ѻ��ԡ������  false ����բ����ū��
     */
    private boolean checkServiceForVisitIDInVector() {   // ������� false
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
     * �ҧἹ��ͺ����
     * new Pattern by Henbe 07/06/2549
     * ʧ������������������ӵ���ٻẺ��������㹿ѧ�ѹ�������ǡѺ list ������
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
     * ��㹡���ʴ�������ŧ���ҧ �ͧ GUI
     * @param vc �� Vector �ͧ Object FamilyPlaning �����㹡���ʴ��ź����ҧ
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
     * ��㹡�� �ѹ�֡�����䢢����Ţͧ����ҧἹ��ͺ����
     * ��зӡ�úѹ�֡������ŧ���ҧ��Ǩ�ѡ�Ҵ����ҡ��¡���Ǫ�ѳ���ҧἹ��ͺ���ǹ���ա�á�˹����ѹ�֡ŧ�ҹ������
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
            theUS.setStatus("��س����͡��Ъҡ����ͺѹ�֡�����Ż�Ъҡá�͹",UpdateStatus.WARNING);
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
            boolean result = showMessageGUI("�š�õ�Ǩ�������ҹ��������移ҡ���١�ѧ������͡ ��ͧ��èС�͡�������",1);
            if(!result)
                return;
        } else if(checkResultExamIsNotData(1)) {
            boolean result = showMessageGUI("�š�õ�Ǩ�������ҹ��ѧ������͡ ��ͧ��èС�͡�������",1);
            if(!result)
                return;
        } else if(checkResultExamIsNotData(2)) {
            boolean result = showMessageGUI("�š�õ�Ǩ����移ҡ���١�ѧ������͡ ��ͧ��èС�͡�������",1);
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
     * �繿ѧ���蹷����㹡�ùѴ������
     * @param familyPlaning = �������ҧἹ��ͺ����
     * @return void
     * @author kingland
     * @date 01/09/2549
     */
    public void saveAppointment(FamilyPlaning familyPlaning) {
        Constant.println("------------------saveAppointment-------------------");
        Appointment app = theHC.theFamilyPlaningControl.checkFamilyPlaningAppointment(familyPlaning);
        if(app != null) {
            app.clinic_code = pcuobject.getServicePoint().getObjectId();
            app.aptype = "�ҧἹ��ͺ����";
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
     *  ��㹡���ʴ� Dialog ��͹����� ����ͧ �Դ�����Դ��Ҵ�ͧ�����ż�����
     */
    private void showDialogErrorDataForPatient() {
        theUS.setStatus(GutilPCU.getTextBundle("ERRORDATA"),UpdateStatus.WARNING);
        //�Դ�����Դ��Ҵ�ͧ�����ż����� ��Т����š������Ѻ��ԡ�� �й� �ͧ���͡�����¤���蹢�����ʴ������š�͹
    }
    /**
     *  ��㹡���ʴ� Dialog ��͹����� ����ͧ �������������˭ԧ
     */
    private void showDialogWarningIsSexWoman() {
        theUS.setStatus(GutilPCU.getTextBundle("ISSEXWOMAN"),UpdateStatus.WARNING);
        //�ѧ����к��� �������Ȫ�� �������ö����Ѻ��ԡ����ǹ�����
    }
    /**
     *  ��㹡���ʴ� Dialog ���͹������ ����ͧ
     */
    private void showDialogWarningCheckVisit() {
        theUS.setStatus(GutilPCU.getTextBundle("WarningCheckVisit"),UpdateStatus.WARNING);
        //�������ѧ������ԴVisit
    }


    /**
     * ��㹡���ʴ��������͡�Ҩҡ ComboBox ��� param �ա�˹�
     * @author ��ا�Ѱ
     * @param combobox ComboBox ����ͧ��èйӤ���͡����ҹ
     * @param type �繵�ǡ�˹���ҷ��� return �͡
     * true ��� �ʴ��͡���� Code
     * false ��� �ʴ��͡���� ��ͤ���
     * @return �ʴ���ҷ���ͧ����͡��
     */
    private String getDataCodeComboBox(JComboBox combobox ,boolean type) {
        if(type) {
            return ComboboxModel.getCodeComboBox(combobox);
        } else {
            return ComboboxModel.getStringConboBox(combobox);
        }
    }

    /**
     * ��㹡�� ��˹��������������Ѻ Combobox
     * @param combobox ComboBox ����ͧ��è�����ʴ����������á
     * @param code ���ʷ���ͧ��è�����ʴ� �繢�ͤ���� combobox
     * @author ��ا�Ѱ
     */
    private void setInitComboBox(JComboBox combobox,String code) {
        ComboboxModel.setCodeComboBox(combobox,code);
    }



    /**
     * ��㹡�ùӢ����ŷ����ҡ Object �ͧ FamilyPlaing ��ʴ��� GIU
     * @author ��ا�Ѱ
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
     * ��㹡�á�˹���Ҩҡ GUI ���Ѻ Object
     * @param -
     * @return  true =  false =
     * @author Tong
     * @modify kingland
     * @date 01/09/2549
     */
    private FamilyPlaning getFamilyPlaning() {
        boolean result = false;

        if(!result) {
            //�������� visit ���ӡ����ҹ�ѹ������Ǩ����
            //����� visit
            //����繡���������������� �����ҹ Visit ���仴���
            //���������繢��������� ����Ǩ�ͺ����繢��������Ǩ�������
            //��������ӡ����ҹ�������ѹ������Ǩ���� ������ͧ�ѹ�֡ Visit

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
     * ��㹡�� �ʴ������ŷ�����͡�ҡ Radio �ͧ����� Breast ������ٻ�ͧ String
     * ����� 0,1,2
     * @author ��ا�Ѱ
     * @param choose ��㹡�� ���͡��ҵ�ͧ��è���� return ��� ���� ��ͧ��è�����˹�ŧ���ҧ
     * true ��� Return ��ҵ�������������͡
     * false ��� set ���ŧ Radio �����ҷ������
     * @param set ��ҷ���ͧ��á�˹�ŧ Radio �� ��ͧ��˹� choose �� false
     * @return �ŷ����ҡ�����͡ Radio �ͧ����� Breast
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
     * ��㹡�� �ʴ������ŷ�����͡�ҡ Radio �ͧ����� Cancer ������ٻ�ͧ String
     * ����� 0,1,2
     * @author ��ا�Ѱ
     * @param choose ��㹡�� ���͡��ҵ�ͧ��è���� return ��� ���� ��ͧ��è�����˹�ŧ���ҧ
     * true ��� Return ��ҵ�������������͡
     * false ��� set ���ŧ Radio �����ҷ������
     * @param set ��ҷ���ͧ��á�˹�ŧ Radio �� ��ͧ��˹� choose �� false
     * @return �ŷ����ҡ�����͡ Radio �ͧ����� Cancer
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
     * ��㹡�á�˹� �ѹ���Ѵ ��������͡��¡�� �Ǫ�ѳ��������¡���� �������˹� �����ѹ���Ѩ�غѹ
     * @param select �����ŷ����㹡�ä���
     * @author ��ا�Ѱ
     */
    public void selectComboBoxMedicalSupplies(String select) {
        //��Ǩ�ͺ��ҷ�����Ѻ�ҡ ComboBox �� null �������
        if(theDefaultComboFix != null) {
            //��Ǩ�ͺ��ҷ���Ѻ�Ҩҡ ComboBox
            if(!theDefaultComboFix.code.equals(select)) {   //�ӡ������¹�ѹ���Ѵ������ѹ���Ѩ�غѹ + �������ҡ�ùѴ ���١��˹�� setup
                //dateComboBoxNextAppDate.setText(theHosManage.theHosControl.theFamilyPlaningControl.getDateFromObjectSupply(vSupplies,selectcomboboxmedicalsupplies));
                //��˹�����͡�ӹǹ�Ǫ�ѳ����
                integerTextFieldNumMedical.setEnabled(true);
            } else {
                //�ӡ������¹�ѹ���Ѵ������ѹ���Ѩ�غѹ
                //dateComboBoxNextAppDate.setText(theHosManage.theHosControl.theFamilyPlaningControl.convertDayToDate());
                //��˹��������͡�ӹǹ�Ǫ�ѳ��
//                integerTextFieldNumMedical.setEnabled(false);
                //��˹��ӹǹ�Ǫ�ѳ�� ����� 0
                integerTextFieldNumMedical.setText("0");
            }

        }
    }
    /**
     * ��㹡�á�˹���� ��¡�� supply �ʴ�����������ä�����Դ �� �������� �Ф�����¡��
     * supply �����������
     * @param selectcomboboxmethod ��� ��ҷ�����Ǩ�ͺ��Ҥ�͡�����ͧ supply ����������ͨ������¡�� supply
     * �ʴ����������繡�����
     * @return void
     * @author ��ا�Ѱ
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
     * ��������ա�ä�ԡ���͡ �Ըԡ�ä�����Դ
     * �¨з���� ���˵آͧ�����������Դ�ʴ� ��������͡ ��������Դ
     * ��Ш�价���� �Ǫ�ѳ�� �ʴ�����¡�õ�ҧ���������͡ �Ըԡ�ä�����Դ
     * @return void
     * @author tong
     * @date 01/09/2549
     */
    public void selectComboBoxHow()
    { /**��Ǩ�ͺʶҹС������Ѻ��ԡ��*/
        if(pcuobject.getVisit()!=null && pcuobject.getVisit().visit_status.equals(VisitStatus.isInProcess())) {
            if(theFamilyPlaning != null
                    && theFamilyPlaning.getObjectId()!=null
                    &&!theFamilyPlaning.visit_id.equals(pcuobject.getVisit().getObjectId())) { //����¹�ŧ��¡���Ǫ�ѳ�� ������ռšѺ��¡�õ�Ǩ�ѡ��
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
     * ���ѹ������Ǩ������ѹ�͹Ҥ��������
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
     *������ setenable gui ����� false �͹��� patient �� null
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
     *૵�����ҹ GUI
     *@param boolean
     *@return void
     *@author kingland
     *@date 05/09/2549
     */
    public void setEnabled(boolean flag) {
        setEnableGui(flag);
    }
    /**
     * ��㹡���ʴ� �����������ѧ���
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
     *૵ʶҹ�
     *@param massege = ��ͤ�������ͧ����ʴ� status = ʶҹ
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
