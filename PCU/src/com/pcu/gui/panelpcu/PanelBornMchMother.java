/*
 * PanelBornMchMother.java
 * Created on 28 �á�Ҥ� 2548, 11:37 �.
 */
/*
 * ��Ǩ�ͺ�ѹ������Ǩ����
 * kingland
 */
package com.pcu.gui.panelpcu;

import com.hospital_os.utility.ComboboxModel;
import com.hosv3.utility.connection.UpdateStatus;
import com.hospital_os.utility.Constant;
import com.pcu.control.AllComboBoxControl;
import com.pcu.control.HosManage;
import com.pcu.control.PCUControl;
import com.pcu.control.PCUObject;
import com.pcu.gui.dialog.HosDialog;
import javax.swing.*;
import java.util.Vector;
import java.awt.event.*;
import com.hospital_os.utility.Gutil;
import com.hospital_os.object.Patient;
import com.hospital_os.object.Visit;
import com.hospital_os.object.Dischar;
import com.pcu.utility.*;
import com.hosv3.utility.DateUtil;
import com.pcu.object.BornMch;
import com.pcu.object.PostpartumBirthPlace;
import com.pcu.object.MotherStandard;
import com.hospital_os.object.VisitStatus;
import com.hospital_os.object.Office;
import com.hospital_os.object.Sex;
import com.pcu.utility.TableModel;
import com.pcu.object.Family;
import com.hosv3.usecase.transaction.ManageBalloon;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author  tong(Padungrat)
 */
public class PanelBornMchMother extends javax.swing.JPanel {

    private HosManage theHosManage;
    private PCUControl theHC;
    private String visit_id;
    private String patient_id;
    private String staff_id;
    private BornMch theBornMch, theBornMchTemp;
    private String value;
    private String[] headTableBorn = new String[]{"�������","�ѹ����ʹ"};;
    private TableModel tablemodel;
    private Vector vBornMother;
    private String visit_status;
    private String office_id;
    private PCUObject pcuobject;
    private HosDialog theHosDialog;
    private Visit theVisit;
    private Family theFamily;
    private Patient thePatient;
    private JFrame theFrame;
    /**��㹡�äӹǳ�ѹ����ʹ��ͧ����ҡ�����ѹ�Ѩ�غѹ*/
    private boolean checkComboBox = false;
    private boolean checkSurveyBornMch = false;
    private AllComboBoxControl theAllComboBoxControl;
    private UpdateStatus theUS;
    private JDialog theJD;
    PanelPP thePanelPP;
    PanelBornMchMother2 thePanelBornMchMother2;

    /**
     * Creates new form PanelBornMchMother
     */
    public PanelBornMchMother() {
        initComponents();
        jButtonContinue.setVisible(false);
    }
    public void setPanelBornMchMother2(PanelBornMchMother2 panelBornMchMother2)
    {
        thePanelBornMchMother2 = panelBornMchMother2;
    }
    public void showDialog(boolean b)
    {
        if(theJD==null)
            theJD = new JDialog(theHC.theUS.getJFrame());
        theJD.add(this);
        theJD.setSize(720,490);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, (screenSize.height - theJD.getSize().height) / 2);
        theJD.setTitle("�����š�ä�ʹ");
        theJD.setModal(true);
        jButtonContinue.setVisible(b);
        theJD.setVisible(true);
    }
    public void setPanelPP(PanelPP panelPP)
    {
        thePanelPP = panelPP;
    }
    public void hideHaveLife(boolean b)
    {
        jPanel4.setVisible(!b);
        jLabelHaveLife.setVisible(!b);
    }
    public void setControl(HosManage hm, HosDialog hd, UpdateStatus us) {
        theHC = hm.theHosControl;
        theHosManage = hm;
        pcuobject = hm.thePO;
        theHosDialog = hd;
        theUS = us;
        theAllComboBoxControl = hm.theHosControl.theAllComboBoxControl;

        initCombobox();
        setLanguage();
//        initBalloon();
        setInitDefaultDataGUI(true);
        
        setEnableGui(null);
        setEnabled(false);
    }

    private void initBalloon() {
        theHosManage.theHosControl.balloon.add(jTextAreaNote);
        jTextAreaNote.setControl(theHosManage.theHosInf.getVitalTemplate());
        jTextAreaNote.setJFrame(getJFrame());
        theHosManage.theHosControl.balloon.add(jTextAreaAbnormalPregnant);
        jTextAreaAbnormalPregnant.setControl(theHosManage.theHosInf.getVitalTemplate());
        jTextAreaAbnormalPregnant.setJFrame(getJFrame());
    }

    /**
     *૵ PCU Object
     *@param pcuobject
     *@return void
     *@author tong
     *@date 05/09/2549
     */
    public void setObject(PCUObject pcuobject) {
        Constant.println("_henbe_______________________" + this.getClass().toString());
        theFamily = null;
        thePatient = null;
        this.pcuobject = pcuobject;
        setInitDefaultDataGUI(false);
        this.setEnabled(true);
        visit_status = "0";
        if (pcuobject != null) {
            theFamily = pcuobject.getFamily();
            if (pcuobject.getEmployee() != null) {
                staff_id = pcuobject.getEmployee().getObjectId();
            }
            visit_id = null;
            theVisit = null;
            if (pcuobject.getVisit() != null) {
                visit_id = pcuobject.getVisit().getObjectId();
                theVisit = pcuobject.getVisit();
                visit_status = pcuobject.getVisit().visit_status;
                if (pcuobject.getVisit().visit_status.equalsIgnoreCase(VisitStatus.isInProcess())) {
                    jButtonAdd.setEnabled(true);
                    jButtonSaveMch.setEnabled(true);
                }
            } else {
                jButtonAdd.setEnabled(true);  // add for notVisit by jao 04/01/49
                jButtonSaveMch.setEnabled(true);  // add for notVisit by jao 04/01/49
            }
            patient_id = null;
            thePatient = null;
            if (pcuobject.getPatient() != null) {
                patient_id = pcuobject.getPatient().getObjectId();
                thePatient = pcuobject.getPatient();
                setEnableGui(true);
            }
            office_id = pcuobject.getSite().off_id;
            refreshTableBornMch();
            if (pcuobject.getPatient() != null) {
                addBornMch(true);
                checkVisitSelection();
                //�ӡ�õ�Ǩ�ͺ����ա�����͡�����ź����ҧ������� ������ ��� Disable �����͡��鹷��                
                setEnableGui(true);
            }
        }
        if (!checkPatientAndFamily()) {
            setEnabled(false);
        } else if (checkDead()) {
            setEnabled(false);
        } else if (!checkSex(false)) {
            setEnabled(false);
        }
    }

    /**
     *��˹��������������Ѻ Combobox
     *@param -
     *@return void
     *@author Tong
     *@modify kingland
     *@date 05/09/2549
     */
    private void initCombobox() {
        ComboboxModel.initComboBox(jComboBoxResultGiveBirth, theHC.theAllComboBoxControl.listResultGiveBirth());
        ComboboxModel.initComboBox(jComboBoxBirthPlace, theHC.theAllComboBoxControl.listPostpartumBirthPlace());
        ComboboxModel.initComboBox(jComboBoxBDoctor, theHC.theAllComboBoxControl.listBDoctor());
        ComboboxModel.initComboBox(jComboBoxPreSalt, theHC.theAllComboBoxControl.listResultStatus());
        ComboboxModel.initComboBox(jComboBoxResultICD10, theHC.theAllComboBoxControl.comboBoxViewICD10Pregnant());
        ComboboxModel.initComboBox(jComboBoxMethodGiveBirth, theHC.theAllComboBoxControl.listBirthMethod());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroupStandardMother = new javax.swing.ButtonGroup();
        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jPanelLeft = new javax.swing.JPanel();
        jScrollPaneShowTable = new javax.swing.JScrollPane();
        jTableShowBorn = new javax.swing.JTable();
        jPanelRight = new javax.swing.JPanel();
        jPanelShowDetail = new javax.swing.JPanel();
        jPanelDetail = new javax.swing.JPanel();
        jLabelBirthResult = new javax.swing.JLabel();
        jLabelBirthPlace = new javax.swing.JLabel();
        jLabelMethodGiveBirth = new javax.swing.JLabel();
        jComboBoxMethodGiveBirth = new javax.swing.JComboBox();
        jLabelBDoctor = new javax.swing.JLabel();
        jComboBoxBDoctor = new javax.swing.JComboBox();
        jLabelHaveLife = new javax.swing.JLabel();
        jLabel1Bresalt = new javax.swing.JLabel();
        jComboBoxPreSalt = new javax.swing.JComboBox();
        jPanelStandardMother = new javax.swing.JPanel();
        jRadioButtonYes = new javax.swing.JRadioButton();
        jRadioButtonNo = new javax.swing.JRadioButton();
        jComboBoxBirthPlace = new javax.swing.JComboBox();
        jLabelResultGiveBirth = new javax.swing.JLabel();
        jComboBoxResultGiveBirth = new javax.swing.JComboBox();
        jComboBoxResultICD10 = new javax.swing.JComboBox();
        jPanelShowBirthHos = new javax.swing.JPanel();
        jPanelBirthHosp = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        integerTextFieldBirthHosp = new com.pcu.utility.IntegerTextField();
        jButtonBirthHosp = new javax.swing.JButton();
        jTextFieldBirthHosp = new javax.swing.JTextField();
        jPanelBirthHosOther = new javax.swing.JPanel();
        jLabelBirthHosOther = new javax.swing.JLabel();
        jTextFieldBirthHosOther = new javax.swing.JTextField();
        jLabelStandardMother = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabelTimeBirth = new javax.swing.JLabel();
        timeTextField1 = new com.hospital_os.utility.TimeTextField();
        integerTextFieldPregnantNumber = new com.hospital_os.utility.IntegerTextField();
        jLabelDateGiveBirth = new javax.swing.JLabel();
        dateComboBoxBorn = new com.pcu.utility.DateComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabelDeath = new javax.swing.JLabel();
        integerTextFieldSBorn = new com.hospital_os.utility.IntegerTextField();
        integerTextFieldLBorn = new com.hospital_os.utility.IntegerTextField();
        jButtonAppointment = new javax.swing.JButton();
        jLabelPregnantNumber = new javax.swing.JLabel();
        dateComboBoxSurvey = new com.pcu.utility.DateComboBox();
        jLabelSurveyDate = new javax.swing.JCheckBox();
        jPanelPregnantNotice = new javax.swing.JPanel();
        jScrollPanePregnantNotice = new javax.swing.JScrollPane();
        jTextAreaNote = new com.hosv3.gui.component.BalloonTextArea();
        jPanelPaneNotice = new javax.swing.JPanel();
        jScrollPaneNotice = new javax.swing.JScrollPane();
        jTextAreaAbnormalPregnant = new com.hosv3.gui.component.BalloonTextArea();
        jPanel16 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        dateComboBoxCheck = new com.hospital_os.utility.DateComboBox();
        jLabel6 = new javax.swing.JLabel();
        timeTextFieldCheck = new com.hospital_os.utility.TimeTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabelVN = new javax.swing.JLabel();
        jPanelButtom = new javax.swing.JPanel();
        jButtonSaveMch = new javax.swing.JButton();
        jPanelShowButton = new javax.swing.JPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonPrint = new javax.swing.JButton();
        jButtonContinue = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanelLeft.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mch_Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jPanelLeft.setMinimumSize(new java.awt.Dimension(175, 51));
        jPanelLeft.setPreferredSize(new java.awt.Dimension(175, 51));
        jPanelLeft.setLayout(new java.awt.GridBagLayout());

        jScrollPaneShowTable.setMinimumSize(new java.awt.Dimension(240, 23));
        jScrollPaneShowTable.setPreferredSize(new java.awt.Dimension(250, 100));

        jTableShowBorn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableShowBornMouseReleased(evt);
            }
        });
        jTableShowBorn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableShowBornKeyReleased(evt);
            }
        });
        jScrollPaneShowTable.setViewportView(jTableShowBorn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelLeft.add(jScrollPaneShowTable, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        add(jPanelLeft, gridBagConstraints);

        jPanelRight.setLayout(new java.awt.GridBagLayout());

        jPanelShowDetail.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mch_Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jPanelShowDetail.setLayout(new java.awt.GridBagLayout());

        jPanelDetail.setLayout(new java.awt.GridBagLayout());

        jLabelBirthResult.setText("BirthResult");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelDetail.add(jLabelBirthResult, gridBagConstraints);

        jLabelBirthPlace.setText("BirthPlace");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelDetail.add(jLabelBirthPlace, gridBagConstraints);

        jLabelMethodGiveBirth.setText("MethodGiveBirth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelDetail.add(jLabelMethodGiveBirth, gridBagConstraints);

        jComboBoxMethodGiveBirth.setBackground(new java.awt.Color(204, 255, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelDetail.add(jComboBoxMethodGiveBirth, gridBagConstraints);

        jLabelBDoctor.setText("BDoctor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 4, 0, 0);
        jPanelDetail.add(jLabelBDoctor, gridBagConstraints);

        jComboBoxBDoctor.setBackground(new java.awt.Color(204, 255, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelDetail.add(jComboBoxBDoctor, gridBagConstraints);

        jLabelHaveLife.setText("HaveLife");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelDetail.add(jLabelHaveLife, gridBagConstraints);

        jLabel1Bresalt.setText("PreSalt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelDetail.add(jLabel1Bresalt, gridBagConstraints);

        jComboBoxPreSalt.setBackground(new java.awt.Color(204, 255, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelDetail.add(jComboBoxPreSalt, gridBagConstraints);

        jPanelStandardMother.setLayout(new java.awt.GridBagLayout());

        buttonGroupStandardMother.add(jRadioButtonYes);
        jRadioButtonYes.setText("PassMotherStandard");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelStandardMother.add(jRadioButtonYes, gridBagConstraints);

        buttonGroupStandardMother.add(jRadioButtonNo);
        jRadioButtonNo.setSelected(true);
        jRadioButtonNo.setText("FailMotherStandard");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelStandardMother.add(jRadioButtonNo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelDetail.add(jPanelStandardMother, gridBagConstraints);

        jComboBoxBirthPlace.setBackground(new java.awt.Color(204, 255, 255));
        jComboBoxBirthPlace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBirthPlaceActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelDetail.add(jComboBoxBirthPlace, gridBagConstraints);

        jLabelResultGiveBirth.setText("ResultGiveBirth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelDetail.add(jLabelResultGiveBirth, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelDetail.add(jComboBoxResultGiveBirth, gridBagConstraints);

        jComboBoxResultICD10.setBackground(new java.awt.Color(204, 255, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelDetail.add(jComboBoxResultICD10, gridBagConstraints);

        jPanelShowBirthHos.setLayout(new java.awt.CardLayout());

        jPanelBirthHosp.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        integerTextFieldBirthHosp.setBackground(new java.awt.Color(204, 255, 255));
        integerTextFieldBirthHosp.setColumns(5);
        integerTextFieldBirthHosp.setMinimumSize(new java.awt.Dimension(35, 21));
        integerTextFieldBirthHosp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldBirthHospKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(integerTextFieldBirthHosp, gridBagConstraints);

        jButtonBirthHosp.setText("...");
        jButtonBirthHosp.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonBirthHosp.setMaximumSize(new java.awt.Dimension(21, 21));
        jButtonBirthHosp.setMinimumSize(new java.awt.Dimension(21, 21));
        jButtonBirthHosp.setPreferredSize(new java.awt.Dimension(21, 21));
        jButtonBirthHosp.setRequestFocusEnabled(false);
        jButtonBirthHosp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBirthHospActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel2.add(jButtonBirthHosp, gridBagConstraints);

        jTextFieldBirthHosp.setBackground(new java.awt.Color(204, 255, 255));
        jTextFieldBirthHosp.setEditable(false);
        jTextFieldBirthHosp.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTextFieldBirthHospMouseMoved(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel2.add(jTextFieldBirthHosp, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanelBirthHosp.add(jPanel2, gridBagConstraints);

        jPanelShowBirthHos.add(jPanelBirthHosp, "BirthHosp");

        jPanelBirthHosOther.setLayout(new java.awt.GridBagLayout());

        jLabelBirthHosOther.setText("AddressBirthPlace");
        jPanelBirthHosOther.add(jLabelBirthHosOther, new java.awt.GridBagConstraints());

        jTextFieldBirthHosOther.setText("jTextField1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelBirthHosOther.add(jTextFieldBirthHosOther, gridBagConstraints);

        jPanelShowBirthHos.add(jPanelBirthHosOther, "BirthPlaceOther");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelDetail.add(jPanelShowBirthHos, gridBagConstraints);

        jLabelStandardMother.setText("StandardM");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelDetail.add(jLabelStandardMother, gridBagConstraints);

        jPanel3.setEnabled(false);
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabelTimeBirth.setText("TimeBirth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanel3.add(jLabelTimeBirth, gridBagConstraints);

        timeTextField1.setMinimumSize(new java.awt.Dimension(45, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(timeTextField1, gridBagConstraints);

        integerTextFieldPregnantNumber.setBackground(new java.awt.Color(204, 255, 255));
        integerTextFieldPregnantNumber.setColumns(2);
        integerTextFieldPregnantNumber.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldPregnantNumber.setText("integerTextField1");
        integerTextFieldPregnantNumber.setMinimumSize(new java.awt.Dimension(22, 21));
        integerTextFieldPregnantNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldPregnantNumberKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(integerTextFieldPregnantNumber, gridBagConstraints);

        jLabelDateGiveBirth.setText("DateGiveBirth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanel3.add(jLabelDateGiveBirth, gridBagConstraints);

        dateComboBoxBorn.setBackground(new java.awt.Color(204, 255, 255));
        dateComboBoxBorn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxBornActionPerformed(evt);
            }
        });
        dateComboBoxBorn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateComboBoxBornFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(dateComboBoxBorn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelDetail.add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabelDeath.setText("NoLife");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanel4.add(jLabelDeath, gridBagConstraints);

        integerTextFieldSBorn.setBackground(new java.awt.Color(204, 255, 255));
        integerTextFieldSBorn.setColumns(2);
        integerTextFieldSBorn.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldSBorn.setText("0");
        integerTextFieldSBorn.setMinimumSize(new java.awt.Dimension(22, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(integerTextFieldSBorn, gridBagConstraints);

        integerTextFieldLBorn.setBackground(new java.awt.Color(204, 255, 255));
        integerTextFieldLBorn.setColumns(2);
        integerTextFieldLBorn.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldLBorn.setText("0");
        integerTextFieldLBorn.setMinimumSize(new java.awt.Dimension(22, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(integerTextFieldLBorn, gridBagConstraints);

        jButtonAppointment.setText("�Ѵ");
        jButtonAppointment.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonAppointment.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAppointmentActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(jButtonAppointment, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelDetail.add(jPanel4, gridBagConstraints);

        jLabelPregnantNumber.setText("PregnantNumber");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelDetail.add(jLabelPregnantNumber, gridBagConstraints);

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
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelDetail.add(dateComboBoxSurvey, gridBagConstraints);

        jLabelSurveyDate.setText("SurveyDate");
        jLabelSurveyDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jLabelSurveyDate.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jLabelSurveyDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabelSurveyDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelDetail.add(jLabelSurveyDate, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 11);
        jPanelShowDetail.add(jPanelDetail, gridBagConstraints);

        jPanelPregnantNotice.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pregnant_Note", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jPanelPregnantNotice.setMinimumSize(new java.awt.Dimension(100, 100));
        jPanelPregnantNotice.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanelPregnantNotice.setLayout(new java.awt.GridBagLayout());

        jScrollPanePregnantNotice.setViewportView(jTextAreaNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelPregnantNotice.add(jScrollPanePregnantNotice, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanelShowDetail.add(jPanelPregnantNotice, gridBagConstraints);

        jPanelPaneNotice.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "AbnormalInPregnant", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jPanelPaneNotice.setMinimumSize(new java.awt.Dimension(100, 100));
        jPanelPaneNotice.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanelPaneNotice.setLayout(new java.awt.GridBagLayout());

        jScrollPaneNotice.setMaximumSize(new java.awt.Dimension(30, 55));
        jScrollPaneNotice.setMinimumSize(new java.awt.Dimension(30, 55));
        jScrollPaneNotice.setPreferredSize(new java.awt.Dimension(30, 55));
        jScrollPaneNotice.setViewportView(jTextAreaAbnormalPregnant);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelPaneNotice.add(jScrollPaneNotice, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 11);
        jPanelShowDetail.add(jPanelPaneNotice, gridBagConstraints);

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
        timeTextFieldCheck.setMinimumSize(new java.awt.Dimension(45, 20));
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
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
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
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 11, 0);
        jPanelShowDetail.add(jPanel16, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelRight.add(jPanelShowDetail, gridBagConstraints);

        jPanelButtom.setLayout(new java.awt.GridBagLayout());

        jButtonSaveMch.setText("Save");
        jButtonSaveMch.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSaveMch.setMaximumSize(new java.awt.Dimension(67, 24));
        jButtonSaveMch.setMinimumSize(new java.awt.Dimension(67, 24));
        jButtonSaveMch.setPreferredSize(new java.awt.Dimension(67, 24));
        jButtonSaveMch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveMchActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelButtom.add(jButtonSaveMch, gridBagConstraints);

        jPanelShowButton.setLayout(new java.awt.GridBagLayout());

        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAdd.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonAdd.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonAdd.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonAdd.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        jPanelShowButton.add(jButtonAdd, new java.awt.GridBagConstraints());

        jButtonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelete.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonDelete.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonDelete.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonDelete.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelShowButton.add(jButtonDelete, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelButtom.add(jPanelShowButton, gridBagConstraints);

        jButtonPrint.setText("Print");
        jButtonPrint.setMaximumSize(new java.awt.Dimension(67, 24));
        jButtonPrint.setMinimumSize(new java.awt.Dimension(67, 24));
        jButtonPrint.setPreferredSize(new java.awt.Dimension(67, 24));
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelButtom.add(jButtonPrint, gridBagConstraints);

        jButtonContinue.setText("仵�� >>");
        jButtonContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonContinueActionPerformed(evt);
            }
        });
        jPanelButtom.add(jButtonContinue, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 0, 6);
        jPanelRight.add(jPanelButtom, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanelRight, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelSurveyDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLabelSurveyDateActionPerformed
        this.dateComboBoxSurvey.setEnabled(jLabelSurveyDate.isSelected());
        if (!jLabelSurveyDate.isSelected()) {
            dateComboBoxSurvey.setText("");
        }
    }//GEN-LAST:event_jLabelSurveyDateActionPerformed

    private void timeTextFieldCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeTextFieldCheckMouseClicked
        timeTextFieldCheck.selectAll();
    }//GEN-LAST:event_timeTextFieldCheckMouseClicked

    private void timeTextFieldCheckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timeTextFieldCheckKeyReleased
    }//GEN-LAST:event_timeTextFieldCheckKeyReleased

    private void dateComboBoxCheckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateComboBoxCheckKeyReleased
    }//GEN-LAST:event_dateComboBoxCheckKeyReleased

    private void jButtonAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAppointmentActionPerformed
        if (this.theHosManage.thePO.getPatient() == null) {
            theUS.setStatus(GutilPCU.getTextBundle("No_Patient_Appointment"), UpdateStatus.WARNING);
            return;
        }
        theHosDialog.showDialogAppointment(theHosManage, theHosManage.thePO);
    }//GEN-LAST:event_jButtonAppointmentActionPerformed

    private void jTableShowBornKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableShowBornKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_UP
                || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            jTableShowBornMouseReleased(null);
        }

    }//GEN-LAST:event_jTableShowBornKeyReleased

    private void dateComboBoxSurveyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxSurveyFocusLost

        checkSurveyBornMch = false;
        checkDateSurvayBornMch();

    }//GEN-LAST:event_dateComboBoxSurveyFocusLost

    private void dateComboBoxSurveyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxSurveyActionPerformed

        checkDateSurvayBornMch();
        checkSurveyBornMch = false;

    }//GEN-LAST:event_dateComboBoxSurveyActionPerformed

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintActionPerformed

        printBornMchMother();

    }//GEN-LAST:event_jButtonPrintActionPerformed

    private void dateComboBoxBornFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxBornFocusLost

        checkComboBox = false;

    }//GEN-LAST:event_dateComboBoxBornFocusLost

    private void dateComboBoxBornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxBornActionPerformed

        checkBornDate();

    }//GEN-LAST:event_dateComboBoxBornActionPerformed

    private void jTableShowBornMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableShowBornMouseReleased

        int select = jTableShowBorn.getSelectedRow();
        if (select == -1) {
            return;
        }
        selectTableBornMch(select);
        jButtonPrint.setEnabled(true);
        closeBalloon();

    }//GEN-LAST:event_jTableShowBornMouseReleased

    private void jTextFieldBirthHospMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBirthHospMouseMoved

        setToolTipText(jTextFieldBirthHosp);

    }//GEN-LAST:event_jTextFieldBirthHospMouseMoved

    private void jComboBoxBirthPlaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBirthPlaceActionPerformed

        checkBirthPlace();

    }//GEN-LAST:event_jComboBoxBirthPlaceActionPerformed

    private void integerTextFieldPregnantNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldPregnantNumberKeyReleased

        checkPregnamtNumber();

    }//GEN-LAST:event_integerTextFieldPregnantNumberKeyReleased

    private void jButtonSaveMchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveMchActionPerformed

        saveBornMch();

    }//GEN-LAST:event_jButtonSaveMchActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed

        deleteBornMch();

    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed

        addBornMch(false);

    }//GEN-LAST:event_jButtonAddActionPerformed

    private void integerTextFieldBirthHospKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldBirthHospKeyReleased

        showDescriptionHosp();

    }//GEN-LAST:event_integerTextFieldBirthHospKeyReleased

    private void jButtonBirthHospActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBirthHospActionPerformed

        showDialogBirthHosp();

    }//GEN-LAST:event_jButtonBirthHospActionPerformed

    private void jButtonContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonContinueActionPerformed
        jButtonContinue.setVisible(false);
        thePanelPP.jButtonContinue.setVisible(true);
        theJD.setVisible(false);
        this.thePanelPP.showDialog(true);
    }//GEN-LAST:event_jButtonContinueActionPerformed

    /**
     * ��㹡�õ�Ǩ�ͺ�����Ţͧ�������Ѻ��ԡ�� ��ǹ��� ����� visit_id
     * �ç�Ѻ���������������� ��ҵç�ѹ��� �ӡ�����͡�����Ź������
     * @param -
     * @return void
     * @author tong
     * @modify kingland
     * @date 05/09/2549
     */
    private void checkVisitSelection() {
        int selectRow = -1;
        if (vBornMother != null) {
            int size = vBornMother.size();
            // �ӡ��ǹ�ٻ����������������բ����Ţͧ visit ���駹���������
            for (int i = 0; i < size; i++) {   //����ҷ�� vector ����������˹� i
                theBornMchTemp = (BornMch) vBornMother.get(i);
                //��Ǩ�ͺ ��ҷ��ͧ object ��� ������� vector ����դ�Ңͧ visit_id ���ǡѹ��á������Ѻ��ԡ�ä��駹�� �������
                if (visit_id != null && theBornMchTemp.visit_id.equalsIgnoreCase(visit_id)) {   //  theAfterMchMother = theAfterMchMotherTemp;
                    selectRow = i;
                    break;
                }
                theBornMchTemp = null;
            }
        }
        theBornMchTemp = null;
        // ����� ���ӡ�� ���͡�����Ūش��� ����ʴ��� GUI ��� ��ش ��÷ӧҹ�ͧ loop
        if (selectRow != -1) {
            jTableShowBorn.setRowSelectionInterval(selectRow, selectRow);
            selectTableBornMch(selectRow);
        }
    }

    /**
     *��Ǩ�ͺ�ѹ�Դ ��������ѹ�͹Ҥ�
     *@param -
     *@return void
     *@author tong
     *@modify kingland
     *@date 05/09/2549
     */
    public void checkBornDate() {
        if (!dateComboBoxBorn.getText().equals("")
                && dateComboBoxBorn.getText().length() == 10
                && DateUtil.countDay(dateComboBoxBorn.getText(), theHC.theConnectionInf) == -1
                && DateUtil.isToday(DateUtil.getDateFromText(dateComboBoxBorn.getText()), theHC.theConnectionInf) == false) {
            if (checkComboBox == false) {   // �������ö��͡�ѹ����ʹ���ѹ�͹Ҥ���
                theUS.setStatus(GutilPCU.getTextBundle("NotUseBornDateToFuture"), UpdateStatus.WARNING);
                dateComboBoxBorn.setText("");
                checkComboBox = true;
            }
        }
        if (checkComboBox) {
            dateComboBoxBorn.setText("");
        }
    }

    /**
     * ���Ǩ�ͺ��Ҽ���������ö�����ҹ�˹�ҹ�����������
     *  ��ͧ�� ˭ԧ, ��������ӡ��� 15 �� ��������ö��ҹ��
     * @param patient �����ż�����
     * @return boolean ����� true ����ö�����ҹ�� ����� false �������ö��ҹ��
     * @author tong
     * @modify kingland
     * @date 05/09/2549
     */
    public boolean checkPatientService(Patient patient, boolean first) {
        boolean result = false;
        if (patient != null) {
            if (patient.f_sex_id.equalsIgnoreCase(Sex.isWOMAN())) {
                if (patient.patient_birthday != null && patient.patient_birthday.trim().length() > 0) {
                    String age = DateUtil.calculateAge(patient.patient_birthday, pcuobject.getCurrentDateTime());
                    if (age != null) {
                        if (getStringToInt(age) >= 15) {
                            result = true;
                        } else {   //�������֧ 15 �� ��ͧ����红����š�ä�ʹ�������
                            if (!first) {
                                if (theUS.confirmBox(GutilPCU.getTextBundle("AGELESS15"), UpdateStatus.WARNING)) {
                                    result = true;
                                }
                            } else {
                                result = true;
                            }
                        }
                    }
                }
            } else {   //�ѧ����к��� �������Ȫ�� �������ö����Ѻ��ԡ����ǹ�����
                if (!first) {
                    theUS.setStatus(GutilPCU.getTextBundle("ISSEXWOMAN"), UpdateStatus.WARNING);
                }
            }
        }
        return result;
    }

    /**
     * ��㹡������¹ String ��� integer
     * @param string ����繵���Ţ
     * @return int
     * @author tong
     * @date 05/019/2549
     */
    private int getStringToInt(String string) {
        int integer = 0;
        try {
            integer = Integer.parseInt(string);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return integer;
    }

    /**
     * ��㹡���ʴ��Ţ������� tooltiptext �ͧ textfield
     * @param JTextField
     * @return void
     * @author tong
     * @date 05/09/2549
     */
    public void setToolTipText(javax.swing.JTextField textfield) {
        textfield.setToolTipText(textfield.getText());
    }

    /**
     * ��㹡���ʴ������ŷ����ҡ��ä�������ʶҹ��Һ��
     * @param -
     * @return void
     * @author tong
     * @date 05/09/2549
     */
    public void showDescriptionHosp() {
        String off_code = integerTextFieldBirthHosp.getText();
        if (off_code.length() == 0 || off_code.equals("")) {
            off_code = theHC.theHcHospitalOS.theHO.theSite.off_id;
            integerTextFieldBirthHosp.setText(off_code);
        } else if (off_code.length() == 5) {
            off_code = integerTextFieldBirthHosp.getText();
        }
        Office theOffice = theHC.theHealthSchoolServiceControl.selectOfficeByPK(off_code);
        if (theOffice != null) {
            if (!theOffice.getObjectId().equals("")) {
                jTextFieldBirthHosp.setText(theOffice.name);
            }
        }
    }

    /**
     * �ʴ� Dialog �ͧʶҹ��Һ��
     * @param -
     * @return void
     * @author tong
     * @date 05/09/2549
     */
    public void showDialogBirthHosp() {
        boolean result = false;
        String codeBirthPlace = getDataCodeComboBox(jComboBoxBirthPlace, true);
        String code = integerTextFieldBirthHosp.getText();
        /**��Ǩ�ͺ ʶҹ����ʹ���ç��Һ���������*/
        Office theOffice = new Office();
        if (theHosDialog.showDialogOffice(theHosManage, theOffice)) {
            jTextFieldBirthHosp.setText(theOffice.getName());
            integerTextFieldBirthHosp.setText(theOffice.getCode());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupStandardMother;
    private com.pcu.utility.DateComboBox dateComboBoxBorn;
    private com.hospital_os.utility.DateComboBox dateComboBoxCheck;
    private com.pcu.utility.DateComboBox dateComboBoxSurvey;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.pcu.utility.IntegerTextField integerTextFieldBirthHosp;
    private com.hospital_os.utility.IntegerTextField integerTextFieldLBorn;
    private com.hospital_os.utility.IntegerTextField integerTextFieldPregnantNumber;
    private com.hospital_os.utility.IntegerTextField integerTextFieldSBorn;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAppointment;
    private javax.swing.JButton jButtonBirthHosp;
    public javax.swing.JButton jButtonContinue;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JButton jButtonSaveMch;
    private javax.swing.JComboBox jComboBoxBDoctor;
    private javax.swing.JComboBox jComboBoxBirthPlace;
    private javax.swing.JComboBox jComboBoxMethodGiveBirth;
    private javax.swing.JComboBox jComboBoxPreSalt;
    private javax.swing.JComboBox jComboBoxResultGiveBirth;
    private javax.swing.JComboBox jComboBoxResultICD10;
    private javax.swing.JLabel jLabel1Bresalt;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBDoctor;
    private javax.swing.JLabel jLabelBirthHosOther;
    private javax.swing.JLabel jLabelBirthPlace;
    private javax.swing.JLabel jLabelBirthResult;
    private javax.swing.JLabel jLabelDateGiveBirth;
    private javax.swing.JLabel jLabelDeath;
    private javax.swing.JLabel jLabelHaveLife;
    private javax.swing.JLabel jLabelMethodGiveBirth;
    private javax.swing.JLabel jLabelPregnantNumber;
    private javax.swing.JLabel jLabelResultGiveBirth;
    private javax.swing.JLabel jLabelStandardMother;
    private javax.swing.JCheckBox jLabelSurveyDate;
    private javax.swing.JLabel jLabelTimeBirth;
    private javax.swing.JLabel jLabelVN;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelBirthHosOther;
    private javax.swing.JPanel jPanelBirthHosp;
    private javax.swing.JPanel jPanelButtom;
    private javax.swing.JPanel jPanelDetail;
    private javax.swing.JPanel jPanelLeft;
    private javax.swing.JPanel jPanelPaneNotice;
    private javax.swing.JPanel jPanelPregnantNotice;
    private javax.swing.JPanel jPanelRight;
    private javax.swing.JPanel jPanelShowBirthHos;
    private javax.swing.JPanel jPanelShowButton;
    private javax.swing.JPanel jPanelShowDetail;
    private javax.swing.JPanel jPanelStandardMother;
    private javax.swing.JRadioButton jRadioButtonNo;
    private javax.swing.JRadioButton jRadioButtonYes;
    private javax.swing.JScrollPane jScrollPaneNotice;
    private javax.swing.JScrollPane jScrollPanePregnantNotice;
    private javax.swing.JScrollPane jScrollPaneShowTable;
    public javax.swing.JTable jTableShowBorn;
    private com.hosv3.gui.component.BalloonTextArea jTextAreaAbnormalPregnant;
    private com.hosv3.gui.component.BalloonTextArea jTextAreaNote;
    private javax.swing.JTextField jTextFieldBirthHosOther;
    private javax.swing.JTextField jTextFieldBirthHosp;
    private com.hospital_os.utility.TimeTextField timeTextField1;
    private com.hospital_os.utility.TimeTextField timeTextFieldCheck;
    // End of variables declaration//GEN-END:variables

    /**
     *��Ǩ�ͺʶҹ����Դ
     *@param -
     *@return void
     *@author tong
     *@date 05/09/2549
     */
    private void checkBirthPlace() {
        String code = getDataCodeComboBox(jComboBoxBirthPlace, true);
        setLaoutBirthHos("BirthPlaceOther");
        jTextFieldBirthHosOther.setText("");
        setEnableDataBirthPlace(true);
        if (code.equalsIgnoreCase(PostpartumBirthPlace.isHospital())) {
            setEnableDataBirthPlace(false);
            setLaoutBirthHos("BirthHosp");
            jTextFieldBirthHosOther.setText("����к�");
            if (office_id != null) {
                integerTextFieldBirthHosp.setText(office_id);
                showDescriptionHosp();
            }
        }
        if (code.equalsIgnoreCase(PostpartumBirthPlace.isPCU())) {
            setLaoutBirthHos("BirthHosp");
            jTextFieldBirthHosOther.setText("����к�");
        }
    }

    /**
     * ��㹡���ʴ������Ţͧʶҹ����Դ
     * @param type ���� Laout
     * @return void
     * @author tong
     * @modify kingland
     * @date 05/09/2549
     */
    private void setLaoutBirthHos(String type) {
        java.awt.CardLayout layout = (java.awt.CardLayout) jPanelShowBirthHos.getLayout();
        layout.show(jPanelShowBirthHos, type);
    }

    /**
     *
     * @param -
     * @return void
     * @author tong
     * @modify kingland
     * @date 05/09/2549
     */
    private void setEnableDataBirthPlace(boolean show) {
        jTextFieldBirthHosp.setText("");
        integerTextFieldBirthHosp.setText("");
        jTextFieldBirthHosp.setEnabled(show);
        integerTextFieldBirthHosp.setEnabled(true);
    }

    /**
     *��㹡���觢����š�ä�ʹ��ѧ �����š�ô�����ѧ��ʹ
     *@param -
     *@return void
     *@author kingland
     *@date 05/09/2549
     */
    public void callServiceAfterMch() {
        theHosManage.theHosSubject.theAfterMchSubject.notifyCallAfterMchMother(theBornMch);
    }

    /**
     *��㹡�õ�Ǩ�ͺ��á�͡����Ţ
     *@param -
     *@return void
     *@author tong
     *@modify kingland
     *@date 05/09/2549
     */
    public void checkPregnamtNumber() {
        int number = -1;
        try {
            number = Integer.parseInt(integerTextFieldPregnantNumber.getText());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (number <= 0) {
            theUS.setStatus(GutilPCU.getTextBundle("ValuePragnanLess"), UpdateStatus.WARNING);
            integerTextFieldPregnantNumber.requestFocus();
            integerTextFieldPregnantNumber.selectAll();
        }
        if (number >= 20) {
            theUS.setStatus(GutilPCU.getTextBundle("ValuePragnanMore"), UpdateStatus.WARNING);
            integerTextFieldPregnantNumber.requestFocus();
            integerTextFieldPregnantNumber.selectAll();
        }
    }

    /**
     * ��㹡���ʴ��������͡�� ��ѧ�ҡ������͡������㹵��ҧ
     * ����ʴ��ѹ�������Ѻ��ԡ���ҧἹ��ͺ����
     * @param row �Ƿ�������͡
     * @return void
     * @author tong
     * @date 05/09/2549
     */
    public void selectTableBornMch(int row) {   //1. ��Ǩ�ͺ row ����Ѻ����� ��������ҡѺ -1 ����
        if (row != -1) {   //2. �Ң������ vector �ͧ BornMch ��� row ����Ѻ��
            setBornMch((BornMch) vBornMother.get(row));
            if (visit_status.equalsIgnoreCase(VisitStatus.isInProcess())) {   //6.1 ��Ǩ�ͺ������ bornMch �� visit_id ���ǡѹ�Ѻ �������Ѻ��ԡ�ûѨ�غѹ�������
                if (theBornMch.visit_id.equalsIgnoreCase(visit_id)) {   //6.1.1 �������͹�ѹ ��˹����� �ء���� ��� ����ö�ӧҹ������� ����֧����ź
                    Constant.println("---------------------------- 1 curent visit");
                    jButtonAdd.setEnabled(true);
                    jButtonSaveMch.setEnabled(true);
                } else {   //6.1.2 ����������͹�ѹ ��˹����� ������ź���ӧҹ���ҧ����
                    Constant.println("---------------------------- 2 oldvisit");
                    jButtonAdd.setEnabled(true);
                }
                //����繢����š�����Ǩ����ö��� �ѹ�֡������������㹡�� Visit ���駹��
                if (theBornMch.survey_date != null && !"".equalsIgnoreCase(theBornMch.survey_date)) {
                    jButtonSaveMch.setEnabled(true);
                    jButtonDelete.setEnabled(true);
                }
            } else {
                jButtonAdd.setEnabled(true);
                jButtonDelete.setEnabled(true);
            }
        }
    }

    /**
     *  ��Ǩ�ͺ �����ŷ������� List �ͧ ���ҧ ���������Ѻ��ԡ��������� ����� �� return �� true
     *  �������� �� false
     * @param -
     * @return �� boolean true ���բ����š������Ѻ��ԡ������  false ����բ����ū��
     * @author tong
     * @date 05/09/2549
     */
    private boolean checkServiceForVisitIDInVector() {   // ������� false
        boolean result = false;
        if (vBornMother != null) {
            int size = vBornMother.size();
            for (int i = 0; i < size; i++) {
                theBornMchTemp = (BornMch) vBornMother.get(i);
                if (theBornMchTemp.visit_id.equalsIgnoreCase(visit_id)) {
                    result = true;
                    break;
                }
                theBornMchTemp = null;
            }
            theBornMchTemp = null;
        }
        return result;
    }

    /**
     * ��㹡�ùӢ����ŷ����ҡ Object �ͧ FamilyPlaing ��ʴ��� GIU
     * @param -
     * @author tong
     */
    public void setBornMch(BornMch bmch) {
        theBornMch = bmch;
        if (theBornMch == null) {
            theBornMch = new BornMch();
            theBornMch.updatetime = this.pcuobject.getCurrentDateTime();
        }
        if (theBornMch != null && theBornMch.getObjectId() != null) {
            jTextAreaNote.setText(theBornMch.note);
        }
        integerTextFieldPregnantNumber.setText(theBornMch.gravida);
        dateComboBoxBorn.setText(Gutil.convertFieldDate(theBornMch.bdate));
        timeTextField1.setText(theBornMch.btime);
        setInitComboBox(jComboBoxBDoctor, theBornMch.bdoctor);
        setInitComboBox(jComboBoxBirthPlace, theBornMch.bplace);
        setInitComboBox(jComboBoxResultGiveBirth, theBornMch.btype);
        setInitComboBox(jComboBoxMethodGiveBirth, theBornMch.birthmethod);
        setInitComboBox(jComboBoxResultICD10, theBornMch.bresult);
        setInitComboBox(jComboBoxPreSalt, theBornMch.presult);
        valueSelectGroupMotherStandard(false, theBornMch.mstandard);
        integerTextFieldLBorn.setText(theBornMch.lborn);
        integerTextFieldSBorn.setText(theBornMch.sborn);
        integerTextFieldBirthHosp.setText(theBornMch.bhosp);
        jTextAreaAbnormalPregnant.setText(theBornMch.abnormalpreg);
        jTextFieldBirthHosOther.setText(theBornMch.addressborn);
        dateComboBoxSurvey.setText(Gutil.convertFieldDate(theBornMch.survey_date));
        jLabelSurveyDate.setSelected(!dateComboBoxSurvey.getText().equals(""));
        dateComboBoxSurvey.setEnabled(!dateComboBoxSurvey.getText().equals(""));
        this.jLabelVN.setText("");
        if (!theBornMch.visit_id.equals("")) {
            String vn_id = this.theAllComboBoxControl.readVNbyVid(theBornMch.visit_id);
            this.jLabelVN.setText("VN:" + vn_id);
        }
        this.dateComboBoxCheck.setText(DateUtil.convertFieldDate(theBornMch.updatetime));
        this.timeTextFieldCheck.setText("");
        if (theBornMch.updatetime.length() > 10) {
            this.timeTextFieldCheck.setText(theBornMch.updatetime.substring(11));
        }
        showDescriptionHosp();
    }

    /**
     * ��㹡��ź������ ����ҧἹ��ͺ�����͡�ҡ���ҧ
     * �� �����¡�ù�����ѹ���ǡѺ���ѹ�֡ ��ź�͡�ҡ���ҧ
     * �����¡�ù���繤����ѹ�Ѻ���ѹ�֡ ������¹ʶҹ� active �� 0
     * @param -
     * @return void
     * @author tong
     */
    public void deleteBornMch() {
        if (this.jTableShowBorn.getSelectedRow() == -1) {
            theUS.setStatus("��س����͡��¡�÷���ͧ���ź", UpdateStatus.WARNING);
            return;
        }
        int ret = theHC.theAfterMCHMotherControl.deleteBornMchMotherByKeyID(theBornMch);
        if (ret == 0) {
            return;
        }
        refreshTableBornMch();
        setInitDefaultDataGUI(true);
    }

    /**
     * ��㹡������������ ����ҧἹ��ͺ���� ŧ�ҹ������
     * @param first �繵�ǡ�˹���Ҷ١������á�ͧ������������������͵�Ǩ�ͺ��͹��� �ѹ�֡�����Ť����á�
     * @return void
     * @author tong
     * @date -
     */
    public void addBornMch(boolean first) {
        if (isVisitSame()) {
            return;
        }
        this.setBornMch(null);
        jTableShowBorn.clearSelection();
        jButtonSaveMch.setEnabled(true);
    }

    /**
     *��Ǩ�ͺ����� Visit ����ӡѹ�������
     *@param -
     *@return boolean false=�����   true=���
     *@author Tong
     *@date 01/09/2549
     */
    private boolean isVisitSame() {
//        if (pcuobject.getVisit() == null) {
//            return false;
//        }
//
//        if (this.vBornMother == null) {
//            return false;
//        }
//
//        for (int i = 0; i < vBornMother.size(); i++) {
//            BornMch obj = (BornMch) vBornMother.get(i);
//            if (pcuobject.getVisit().getObjectId().equals(obj.visit_id)) {
//                theUS.setStatus(GutilPCU.getTextBundle("VisitAlready"), UpdateStatus.WARNING);
//                return true;
//            }
//        }
        return false;
    }

    /**
     *��Ǩ�ͺ����� Visit ��ӡѹ�������
     *@param -
     *@return boolean true=����� false=���
     *@author kingland
     *@date 05/09/2549
     */
    private boolean checkVisitSame() {
        boolean resultsame = true;
        if (visit_id != null && theVisit != null) {
            if (vBornMother != null) {
                int rows = vBornMother.size();
                theBornMchTemp = null;
                for (int i = 0; i < rows; i++) {
                    theBornMchTemp = (BornMch) vBornMother.get(i);
                    if (visit_id.equalsIgnoreCase(theBornMchTemp.visit_id)) {
                        resultsame = false;
                        break;
                    }
                    theBornMchTemp = null;
                }
                theBornMchTemp = null;
            }
        } else {
            resultsame = false;
        }
        return resultsame;
    }

    /**
     *��Ǩ�ͺ�Ȣͧ����Ѻ��ԡ��
     *@param -
     *@return boolean true=��ҹ false=����ҹ
     *@author kingland
     *@date 04/09/2549
     */
    private boolean checkSex(boolean showWarningMessage) {
        boolean result = true;
        //add code by noom ����Ѻ check �� ˭ԧ��ҹ��
        if (thePatient != null && !("2").equals(thePatient.f_sex_id)) {
            if (showWarningMessage) {
                theUS.setStatus(GutilPCU.getTextBundle("ISSEXWOMAN"), UpdateStatus.WARNING);
                //�ѧ����к��� �������Ȫ�� �������ö����Ѻ��ԡ����ǹ�����
            }
            result = false;
        } //����Ѻ check �� ˭ԧ��ҹ��
        else if (theFamily != null && !("2").equals(theFamily.f_sex_id)) {
            if (showWarningMessage) {
                theUS.setStatus(GutilPCU.getTextBundle("ISSEXWOMAN"), UpdateStatus.WARNING);
                //�ѧ����к��� �������Ȫ�� �������ö����Ѻ��ԡ����ǹ�����
            }
            result = false;
        }
        return result;
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
        if (theFamily != null && Dischar.DEATH.equals(theFamily.discharge_status_id)) {
            result = true;
        } else if (thePatient != null && Dischar.DEATH.equals(thePatient.discharge_status_id)) {
            result = true;
        }
        return false;//result;
    }

    /**
     *��Ǩ�ͺ Patient ��� Family �ҡ PCUobject
     *@param -
     *@return boolean true=�ռ��������ͻ�Ъҡ� false=����ռ�������л�Ъҡ�
     *@author kingland
     *@date 04/09/2549
     */
    private boolean checkPatientAndFamily() {
        boolean result = true;
        if (theFamily == null) {
            result = false;
        }
        return result;
    }

    /**
     *��Ǩ�ͺ������� ��� �ѹ�Դ
     *@param -
     *@return boolean true �Դ��ë�ӡѹ, false ����ӡѹ
     *@author kigland
     *@date 29/08/2549
     */
    private boolean checkDataBorn() {
        boolean result = false;
        boolean gravida = false;
        boolean bdate = false;
        /**��Ǩ�ͺ��Ңͧ������� ����դ��������� ��Ф�ҷ���� �е�ͧ�����ҡѺ 0*/
        String num = integerTextFieldPregnantNumber.getText().trim();
        if (num.length() > 0) {
            int inum = convertStringToInt(num);
            if (inum <= 0) {   //������ 0
                theUS.setStatus(GutilPCU.getTextBundle("ValuePragnanLess"), UpdateStatus.WARNING);//�ӴѺ������� ��ͧ�դ���ҡ���� 0
                integerTextFieldPregnantNumber.requestFocus();
                integerTextFieldPregnantNumber.selectAll();
                Constant.println("p4");
                result = true;
            }
            if (inum >= 20) {
                theUS.setStatus(GutilPCU.getTextBundle("ValuePragnanMore"), 2);
                integerTextFieldPregnantNumber.requestFocus();
                integerTextFieldPregnantNumber.selectAll();
                Constant.println("p5");
                result = true;
            }
        } else {   //������� ��ͧ�բ�����
            theUS.setStatus(GutilPCU.getTextBundle("ValuePragnanNull"), UpdateStatus.WARNING);//�ӴѺ����������դ�ҵ����1-19
            integerTextFieldPregnantNumber.requestFocus();
            Constant.println("p6");
            result = true;
        }
        /**��Ǩ�ͺ�ѹ����ʹ��ͧ����� �����ҧ*/
        String date = dateComboBoxBorn.getText().trim();
        if (date.length() == 0) {   //��ͧ�к��ѹ����ʹ���¤��
            theUS.setStatus(GutilPCU.getTextBundle("BORNDATE"), UpdateStatus.WARNING);
            dateComboBoxBorn.requestFocus();
            Constant.println("p7");
            result = true;
        }
        date = null;
        num = null;
        if (!result) {
            if (vBornMother != null) {
                /**��Ǩ�ͺ��ë�ӡѹ�ͧ������� ��� �ѹ����ʹ*/
                int size = vBornMother.size();
                for (int i = 0; i < size; i++) {
                    theBornMchTemp = (BornMch) vBornMother.get(i);
                    if (theBornMch.getObjectId() == null) {
                        if (theBornMchTemp.gravida.equalsIgnoreCase(integerTextFieldPregnantNumber.getText())) {
                            gravida = true;
                        }
                        if (theBornMchTemp.bdate.equalsIgnoreCase(dateComboBoxBorn.getText())) {
                            bdate = true;
                        }
                    } else {
                        if (!theBornMchTemp.getObjectId().equalsIgnoreCase(theBornMch.getObjectId())) {
                            if (theBornMchTemp.gravida.equalsIgnoreCase(integerTextFieldPregnantNumber.getText())) {
                                gravida = true;
                            }
                            if (theBornMchTemp.bdate.equalsIgnoreCase(dateComboBoxBorn.getText())) {
                                bdate = true;
                            }
                        }
                    }
                }
            }
            if (gravida && bdate) {   //��������ӡѺ��������� ����ѹ����ʹ��ӡѺ���������
                theUS.setStatus(GutilPCU.getTextBundle("GravidaSame") + " "
                        + GutilPCU.getTextBundle("And") + " "
                        + GutilPCU.getTextBundle("BirthDateSame"), UpdateStatus.WARNING);//�ӴѺ����� ��ӡѺ���������
                integerTextFieldPregnantNumber.requestFocus();
                integerTextFieldPregnantNumber.selectAll();
                result = true;
            } else {
                if (gravida) {   //��������ӡѺ���������
                    theUS.setStatus(GutilPCU.getTextBundle("GravidaSame"), UpdateStatus.WARNING);//�ӴѺ����� ��ӡѺ���������
                    integerTextFieldPregnantNumber.requestFocus();
                    integerTextFieldPregnantNumber.selectAll();
                    result = true;
                } else {
                    if (bdate) {   //�ѹ����ʹ��ӡѺ���������
                        theUS.setStatus(GutilPCU.getTextBundle("BirthDateSame"), UpdateStatus.WARNING);//�ѹ����ʹ ��ӡѺ���������
                        dateComboBoxBorn.requestFocus();
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    /**
     *��㹡���ŧ����ѡ���繵���Ţ
     *@param value �� String ��ͧ�繵���Ţ
     *@return int �繵���Ţ���١�ŧ�͡������ ����Դ error �С�˹���� �� 0
     *@author padungrat(tong)
     */
    private int convertStringToInt(String value) {
        int ivalue = 0;
        try {
            ivalue = Integer.parseInt(value);
        } catch (Exception ex) {
        }
        return ivalue;
    }

    /**
     * �ѹ�֡�����š�ä�ʹ
     */
    public void saveBornMch() {
        Constant.println("pcuobject.getFamily().getObjectId()" + pcuobject.getFamily().getObjectId());
//        if(integerTextFieldLBorn.getText().equals(""))
//        {
//            integerTextFieldLBorn.requestFocus();
//            theUS.setStatus("��سҡ�͡�ӹǹ���Դ�ժվ",UpdateStatus.WARNING);
//            return;
//        }
        if (pcuobject.getFamily() == null) {
            Constant.println("p");
            theUS.setStatus("��س����͡��Ъҡ����ͺѹ�֡�����Ż�Ъҡá�͹", 2);
            return;
        }
        if (!pcuobject.getFamily().f_sex_id.equals("2")) {
            Constant.println("p1");
            theUS.setStatus(GutilPCU.getTextBundle("CheckPatientSex"), UpdateStatus.WARNING);//��ú�ԡ�ù������Ѻ����Ѻ��ԡ�÷������ "˭ԧ" ��ҹ��
            return;
        }
        theBornMch = getBornMch();
        if (theBornMch == null) {
            return;
        }

        if (checkDataBorn()) {
            return;
        }

        BornMch bm = theHC.theAfterMCHMotherControl.saveBornMCHMother(theBornMch);
        if (bm == null) {
            return;
        }
        this.setBornMch(theBornMch);
        refreshTableBornMch();
        for (int i = 0; i < this.vBornMother.size(); i++) {
            BornMch pp = (BornMch) vBornMother.get(i);
            if (pp.getObjectId().equals(theBornMch.getObjectId())) {
                this.jTableShowBorn.setRowSelectionInterval(i, i);
                return;
            }
        }
        setInitDefaultDataGUI(true);
    }

    /**��㹡�� ��˹�������͡ ������㹵��ҧ���������ա�á��ѹ�֡
     * �¨�仵�Ǩ�ͺ�Ѻ vector
     */
    private void setSelectRowInTable() {
        int size = 0;
        if (vBornMother != null) {
            size = vBornMother.size();
        }
        int selectRow = -1;
        for (int i = 0; i < size; i++) {
            theBornMchTemp = (BornMch) vBornMother.get(i);
            if (theBornMchTemp.getObjectId().equalsIgnoreCase(theBornMch.getObjectId())) {
                selectRow = i;
                break;
            }
            theBornMchTemp = null;
        }
        theBornMchTemp = null;
        if (selectRow != -1) {
            jTableShowBorn.setRowSelectionInterval(selectRow, selectRow);
            selectTableBornMch(selectRow);
        }
    }

    /**
     * ��㹡�õ�駤��������鹢ͧ������
     * @author ��ا�Ѱ
     */
    private void setInitDefaultDataGUI(boolean showadd) {
        setInitComboBox(jComboBoxResultGiveBirth, "1");
        setInitComboBox(jComboBoxBirthPlace, "1");
        setInitComboBox(jComboBoxPreSalt, "0");
        setInitComboBox(jComboBoxResultICD10, "");
        setInitComboBox(jComboBoxBDoctor, "1");
        setInitComboBox(jComboBoxMethodGiveBirth, "1");
        dateComboBoxBorn.setText(Gutil.convertFieldDate(pcuobject.getCurrentDateTime()));
        dateComboBoxSurvey.setText(Gutil.convertFieldDate(pcuobject.getCurrentDateTime()));
        integerTextFieldPregnantNumber.setText("1");
        integerTextFieldBirthHosp.setText("");
        jTextFieldBirthHosp.setText("");
        integerTextFieldLBorn.setText("1");
        integerTextFieldSBorn.setText("0");
        jTextAreaNote.setText("");
        jTextAreaAbnormalPregnant.setText("");
        valueSelectGroupMotherStandard(false, MotherStandard.isNotMotherStandard());
//        setEnableButton(false,0,showadd,false);
    }

    /**
     * ��㹡�� ��˹��������������Ѻ Combobox
     * @param combobox ComboBox ����ͧ��è�����ʴ����������á
     * @param code ���ʷ���ͧ��è�����ʴ� �繢�ͤ���� combobox
     * @author ��ا�Ѱ
     */
    private void setInitComboBox(JComboBox combobox, String code) {
        ComboboxModel.setCodeComboBox(combobox, code);
    }

    /**
     * ��㹡�� Refresh ������㹵��ҧ�������Ѻ��ԡ�� �ҧἹ��ͺ����
     * ���������
     * @author ��ا�Ѱ
     */
    public void refreshTableBornMch() {
        vBornMother = theHC.theAfterMCHMotherControl.selectBornMchMotherShowGUITableByFamilyID();
        setBornMchV(vBornMother);
        setEnableGui(vBornMother);
    }
    //henbe_add 311006

    public void refresh(String family_id) {
        theHC.theAfterMCHMotherControl.selectBornMchMotherShowGUITableByFamilyID(family_id);
    }

    /**
     * ��㹡���ʴ�������ŧ���ҧ �ͧ GUI ��� �ѧ�繡�
     * @author ��ا�Ѱ
     * @param vc �� Vector �ͧ Object FamilyPlaning �����㹡���ʴ��ź����ҧ
     * GUI
     */
    private void setBornMchV(Vector vc) {
        vBornMother = vc;
        tablemodel = new TableModel(headTableBorn, 0);
        if (vc != null) {
            tablemodel = new TableModel(headTableBorn, vc.size());
            final int size = vc.size();
            for (int i = 0; i < size; i++) {

                theBornMchTemp = (BornMch) vc.get(i);

                tablemodel.setValueAt(theBornMchTemp.gravida, i, 0);
                tablemodel.setValueAt(GutilPCU.changDateToString(theBornMchTemp.bdate, false), i, 1);
                theBornMchTemp = null;
            }
        }
        jTableShowBorn.setModel(tablemodel);
        jTableShowBorn.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableShowBorn.getColumnModel().getColumn(0).setCellRenderer(ColumnTableRenderer.getRendererCenter());
        jTableShowBorn.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTableShowBorn.getColumnModel().getColumn(1).setCellRenderer(ColumnTableRenderer.getRendererCenter());
        //henbe_add 311006
//        if(vc!=null && !vc.isEmpty())
//        {
//            jTableShowBorn.setRowSelectionInterval(0,0);
//            selectTableBornMch(0);
//        }

    }

    /**
     * ��㹡�á�˹���Ҩҡ GUI ���Ѻ Object
     * @param -
     * @return bolean true =  false =
     * @author ��ا�Ѱ
     * @modify kingland
     * @date 29/08/2549
     */
    private BornMch getBornMch() {
        if (theBornMch == null) {
            theBornMch = new BornMch();
        }
        theBornMch.gravida = integerTextFieldPregnantNumber.getText();
        theBornMch.bdate = dateComboBoxBorn.getText();
        theBornMch.bresult = getDataCodeComboBox(jComboBoxResultICD10, true);
        theBornMch.bplace = getDataCodeComboBox(jComboBoxBirthPlace, true);
        theBornMch.bhosp = integerTextFieldBirthHosp.getText();
        theBornMch.birthmethod = getDataCodeComboBox(jComboBoxMethodGiveBirth, true);
        theBornMch.btype = getDataCodeComboBox(jComboBoxResultGiveBirth, true);
        theBornMch.bdoctor = getDataCodeComboBox(jComboBoxBDoctor, true);
        theBornMch.lborn = integerTextFieldLBorn.getText();
        theBornMch.sborn = integerTextFieldSBorn.getText();
        theBornMch.presult = getDataCodeComboBox(jComboBoxPreSalt, true);
        theBornMch.mstandard = valueSelectGroupMotherStandard(true, null);
        theBornMch.btime = timeTextField1.getText();
        theBornMch.abnormalpreg = Gutil.CheckReservedWords(jTextAreaAbnormalPregnant.getText());
        theBornMch.note = Gutil.CheckReservedWords(jTextAreaNote.getText());
        theBornMch.active = "1";
        theBornMch.user_cancel = "";
        theBornMch.addressborn = Gutil.CheckReservedWords(jTextFieldBirthHosOther.getText());

        if (theBornMch.getObjectId() == null) {
            theBornMch.user_record = staff_id;
            theBornMch.recordtime = pcuobject.getCurrentDateTime();
            theBornMch.user_modify = "";
            theBornMch.updatetime = "";
            if (pcuobject.getVisit() != null) {
                theBornMch.visit_id = pcuobject.getVisit().getObjectId();
            }
        }
        theBornMch.user_modify = staff_id;
        theBornMch.survey_date = dateComboBoxSurvey.getText();
        theBornMch.updatetime = dateComboBoxCheck.getText() + "," + timeTextFieldCheck.getText();
        return theBornMch;
    }

    /**
     * ��㹡���ʴ����� �ѹ�֡,���� ��� ź ����˵ء�ó��ҧ�
     * @param b ����ʴ���������ʴ� false : ����ʴ� true : �ʴ�
     * @param choose ����кء�÷ӧҹ �¡�˹���� 1 ����ź�зӧҹ�ç�����Ѻ �����ŷ�� ��˹���� b �ѹ��� 0 ����
     * @param showadd �繵�ǡ�˹������� add ����ö�ӧҹ���������
     * @param showservice �繵�ǡ�˹������ �Ѻ��ԡ����ѧ��ʹ�ͧ���ӧҹ��
     * @author ��ا�Ѱ
     */
    private void setEnableButton(boolean b, int choose, boolean showadd, boolean showservice) {
        jButtonAdd.setEnabled(showadd);
        jButtonDelete.setEnabled(b);
        jButtonPrint.setEnabled(b);
        if (choose == 1) {
            jButtonDelete.setEnabled(!b);
        }//jButtonSaveMch.setEnabled(true);
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
    private String valueSelectGroupMotherStandard(boolean choose, String set) {
        value = MotherStandard.isNotMotherStandard();
        if (choose) {

            if (jRadioButtonYes.isSelected()) {
                value = MotherStandard.isMotherStandard();
            }
            if (jRadioButtonNo.isSelected()) {
                value = MotherStandard.isNotMotherStandard();
            }

        } else {
            jRadioButtonNo.setSelected(true);
            if (set != null) {
                if (set.length() > 0) {
                    if (set.equalsIgnoreCase(MotherStandard.isMotherStandard())) {
                        jRadioButtonYes.setSelected(true);
                    }

                    if (set.equalsIgnoreCase(MotherStandard.isNotMotherStandard())) {
                        jRadioButtonNo.setSelected(true);
                    }

                }
            }

        }
        return value;
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
    private String getDataCodeComboBox(JComboBox combobox, boolean type) {
        if (type) {
            return ComboboxModel.getCodeComboBox(combobox);
        } else {
            return ComboboxModel.getStringConboBox(combobox);
        }
    }

    /**
     * ૵ Enable ������������š�ä�ʹ
     * @author kingland
     * @param v = �Ǥ����ͧ�����š�ä�ʹ
     * @return �����
     */
    private void setEnableGui(Vector v) {
        if (v == null || v.size() == 0) {
            jButtonDelete.setEnabled(false);
        } else {
            jButtonDelete.setEnabled(true);
        }
    }

    /**
     *૵ Enable GUI
     *@param boolean
     *@return void
     *@author kingland
     *@date 05/09/2549
     */
    private void setEnableGui(boolean flag) {
        integerTextFieldPregnantNumber.setEnabled(flag);
        dateComboBoxBorn.setEnabled(flag);
        timeTextField1.setEnabled(flag);
        jComboBoxBDoctor.setEnabled(flag);
        jComboBoxBirthPlace.setEnabled(flag);
        jComboBoxMethodGiveBirth.setEnabled(flag);
        integerTextFieldBirthHosp.setEnabled(flag);
        jRadioButtonYes.setEnabled(flag);
        jRadioButtonNo.setEnabled(flag);
        jComboBoxResultGiveBirth.setEnabled(flag);
        jComboBoxPreSalt.setEnabled(flag);
        jComboBoxResultICD10.setEnabled(flag);
        jTextAreaNote.setEnabled(flag);
        jTextAreaAbnormalPregnant.setEnabled(flag);
        jButtonBirthHosp.setEnabled(flag);
        integerTextFieldLBorn.setEnabled(flag);
        integerTextFieldSBorn.setEnabled(flag);
        jButtonPrint.setEnabled(flag);
    }

    /**
     *૵ Enable Gui
     *@param boolean
     *@return void
     *@author kingland
     *@date 05/09/2549
     */
    public void setEnabled(boolean flag) {
        setEnableGui(flag);
        jButtonAdd.setEnabled(flag);
        jButtonSaveMch.setEnabled(flag);
        jButtonPrint.setEnabled(false);
    }

    /**
     * ��㹡���ʴ� �����������ѧ���
     * @author ��ا�Ѱ
     */
    private void setLanguage() {
        GutilPCU.setLanguage(headTableBorn);
        /*jLabel*/
        GutilPCU.setLanguage(jLabelSurveyDate);
        jLabelPregnantNumber.setText(GutilPCU.getTextBundle(jLabelPregnantNumber.getText()));
        jLabelDateGiveBirth.setText(GutilPCU.getTextBundle(jLabelDateGiveBirth.getText()));
        jLabelTimeBirth.setText(GutilPCU.getTextBundle(jLabelTimeBirth.getText()));
        jLabelBDoctor.setText(GutilPCU.getTextBundle(jLabelBDoctor.getText()));
        jLabelBirthPlace.setText(GutilPCU.getTextBundle(jLabelBirthPlace.getText()));
        jLabelMethodGiveBirth.setText(GutilPCU.getTextBundle(jLabelMethodGiveBirth.getText()));

        jLabelResultGiveBirth.setText(GutilPCU.getTextBundle(jLabelResultGiveBirth.getText()));
        jLabelHaveLife.setText(GutilPCU.getTextBundle(jLabelHaveLife.getText()));
        jLabelDeath.setText(GutilPCU.getTextBundle(jLabelDeath.getText()));
        jLabel1Bresalt.setText(GutilPCU.getTextBundle(jLabel1Bresalt.getText()));
        jLabelStandardMother.setText(GutilPCU.getTextBundle(jLabelStandardMother.getText()));
        jLabelBirthResult.setText(GutilPCU.getTextBundle(jLabelBirthResult.getText()));

        //jLabelStandardMother.setText(GutilPCU.getTextBundle(jLabelStandardMother.getText()));
        jLabelBirthHosOther.setText(GutilPCU.getTextBundle(jLabelBirthHosOther.getText()));
        GutilPCU.setLanguage(jLabel5);
        GutilPCU.setLanguage(jLabel6);
        /*jButton*/
        GutilPCU.setLanguage(jButtonAppointment);
///        jButtonPp.setText(GutilPCU.getTextBundle(jButtonPp.getText()));
        jButtonSaveMch.setText(GutilPCU.getTextBundle(jButtonSaveMch.getText()));
        jButtonAdd.setText(GutilPCU.getTextBundle(jButtonAdd.getText()));
        jButtonDelete.setText(GutilPCU.getTextBundle(jButtonDelete.getText()));
        jButtonPrint.setText(GutilPCU.getTextBundle(jButtonPrint.getText()));
        /*TitledBorder*/
        GutilPCU.JPanelLabler(jPanelLeft);
        GutilPCU.JPanelLabler(jPanelShowDetail);
        GutilPCU.JPanelLabler(jPanelRight);
        GutilPCU.JPanelLabler(jPanelPregnantNotice);
        GutilPCU.JPanelLabler(jPanelPaneNotice);
        /**JRadio*/
        jRadioButtonYes.setText(GutilPCU.getTextBundle(jRadioButtonYes.getText()));
        jRadioButtonNo.setText(GutilPCU.getTextBundle(jRadioButtonNo.getText()));
        dateComboBoxSurvey.setText(GutilPCU.getTextBundle(jLabelSurveyDate.getText()));
    }

    public void setJFrame(JFrame frame, boolean show_link) {
        theFrame = frame;
        initBalloon();
    }

    public void setJFrame(JFrame frame) {
        setJFrame(frame, true);
    }

    public JFrame getJFrame() {
        return theFrame;
    }

    /**
     * �����ѹ�֡��ä�ʹ
     */
    private void printBornMchMother() {
        if (vBornMother == null || vBornMother.size() == 0) {
            theUS.setStatus("����բ����š�ä�ʹ ��سҡ�͡�����š�͹�����", UpdateStatus.WARNING);
            return;
        }
        int row = jTableShowBorn.getSelectedRow();
        if (row == -1) {
            theUS.setStatus("��س����͡�����š�͹�����", UpdateStatus.WARNING);
            return;
        }
        theHC.thePrintControl.printBornMchMother(vBornMother, row);
    }

    private void closeBalloon() {
        for (int j = 0; j < theHosManage.theHosControl.balloon.size(); j++) {
            ManageBalloon mb = (ManageBalloon) theHosManage.theHosControl.balloon.get(j);
            mb.notifyCloseBalloon("close", 1);
        }
    }

    /**
     * ��Ǩ�ͺ����ѹ�͡��Ǩ���ѹ�͹Ҥ��������
     * @param
     * @return
     * @author kingland
     * @date 17-03-2549
     */
    private void checkDateSurvayBornMch() {
        if (!dateComboBoxSurvey.getText().equals("")
                && dateComboBoxSurvey.getText().length() == 10
                && com.pcu.utility.DateUtil.countDay(dateComboBoxSurvey.getText(), theHC.theConnectionInf) == -1
                && com.pcu.utility.DateUtil.isToday(
                com.pcu.utility.DateUtil.getDateFromText(dateComboBoxSurvey.getText()), theHC.theConnectionInf) == false) {   // �������ö��͡�ѹ������ѹ�͹Ҥ���
            if (checkSurveyBornMch == false) {
                theUS.setStatus(GutilPCU.getTextBundle("NoDateFuture"), UpdateStatus.WARNING);
                dateComboBoxSurvey.setText("");
                checkSurveyBornMch = true;
            }
        }
    }
    /**
     *�ʴ���ͤ����ͧ�����
     *@param message = ��ͤ��� status = ʶҹ�
     *@return void
     *@author kingland
     *@date 29/08/2549
     */
}
