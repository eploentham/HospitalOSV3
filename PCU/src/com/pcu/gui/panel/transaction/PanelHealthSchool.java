/*
 * PanelHealthSchool.java
 *
 * Created on 1 กรกฎาคม 2548, 16:04 น.
 */
package com.pcu.gui.panel.transaction;

import com.pcu.control.HomeControl;
import com.pcu.control.PCUObject;
import com.pcu.gui.dialog.HosDialog;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.*;

import com.hosv3.utility.connection.UpdateStatus;
import com.hospital_os.utility.*;
import com.hospital_os.utility.DateUtil;
import com.hospital_os.object.Employee;
import com.hospital_os.object.Prefix;
import com.hospital_os.object.Office;

import com.pcu.utility.*;
import com.pcu.object.*;
import com.pcu.control.VillageControl;
import com.pcu.control.HealthSchoolServiceControl;
import com.pcu.control.AllComboBoxControl;
import com.pcu.control.HosManage;
import java.awt.CardLayout;

/**
 *
 * @author  Noom
 */
public class PanelHealthSchool extends javax.swing.JPanel {

    public static int sizeOfServiceTab = 12;
    private DefaultTableCellRenderer rendererCenter;
    private AllComboBoxControl theAllComboBoxControl;
    private HealthSchoolServiceControl theHealthSchoolServiceControl;
    private VillageControl theVillageControl;
    private HosManage theHosManage;
    private PCUObject thePO;
    private UpdateStatus theUS;
    private HomeControl theHomeControl;
    private School theSchool = new School();
    private Student theStudent = new Student();
    private VisitSchool theVisitSchool = new VisitSchool();
    private CheckEyes theCheckEyes = new CheckEyes();
    private CheckEars theCheckEars = new CheckEars();
    private CheckNutrition theCheckNutrition = new CheckNutrition();
    private CheckStudentHealth theCheckStudentHealth = new CheckStudentHealth();
    private CheckGoiter theCheckGoiter = new CheckGoiter();
    private CheckFeBlood theCheckFeBlood = new CheckFeBlood();
    private CheckStudentDental theCheckStudentDental = new CheckStudentDental();
    private CheckWorm theCheckWorm = new CheckWorm();
    private CheckThalassemia theCheckThalassemia = new CheckThalassemia();
    private CheckLice theCheckLice = new CheckLice();
    private CheckBody theCheckBody = new CheckBody();
    private CheckOther theCheckOther = new CheckOther();
    private Prefix thePrefix = new Prefix();
    private Vector vSchool = new Vector();
    private Vector vStudent = new Vector();
    private Vector vCheckEyes = new Vector();
    private Vector vCheckEars = new Vector();
    private Vector vVisitSchool = new Vector();
    private Vector vCurrent = new Vector();
    private Vector[] vc = null;
    private Hashtable tableHealth = new Hashtable();
    private Hashtable tableSee = new Hashtable();
    private Hashtable tableNormal = new Hashtable();
    private HosDialog theHosDialog;
    private Employee theEmployee;
    private CelRendererHealthSchool theCelRendererLabReferOut = new CelRendererHealthSchool();
    private String[] col = {GutilPCU.getTextBundle("No."),
        GutilPCU.getTextBundle("FirstName"),
        GutilPCU.getTextBundle("SurName"),
        GutilPCU.getTextBundle("StatusCheck"),
        GutilPCU.getTextBundle("ResultCheck")};
    private String selectedStudentNumber = "";
    private String selectedStudentPid = "";

    String[] col_list = new String[]{"ที่", "เรื่องที่ตรวจ", "ตรวจ", "ผล"};
    String[] cola = new String[]{ "ชื่อ-สกุล นักเรียน"};

    /** Creates new form PanelHealthSchool */
    public PanelHealthSchool() {
        initComponents();
    }

    public PanelHealthSchool(HosManage hm, HosDialog hd, UpdateStatus us) {
        theHosManage = hm;
        theHosDialog = hd;
        theUS = us;
        theHomeControl = theHosManage.theHosControl.theHomeControl;
        theAllComboBoxControl = theHosManage.theHosControl.theAllComboBoxControl;
        theVillageControl = theHosManage.theHosControl.theVillageControl;
        theHealthSchoolServiceControl = theHosManage.theHosControl.theHealthSchoolServiceControl;
        thePO = theHosManage.thePO;
        theAllComboBoxControl.initFixDataToHashTable();

        initComponents();
        initDatas();
        setLanguage();
        setSchoolFromEnable(false);
        setStudentFromEnable(false);
        initHashTable();
        setListenner();
    }

    public void initHashTable() {
        tableHealth.put("0", GutilPCU.setLanguage("ไม่แข็งแรง"));
        tableHealth.put("1", GutilPCU.setLanguage("แข็งแรง"));
        tableSee.put("0", GutilPCU.setLanguage("ไม่พบ"));
        tableSee.put("1", GutilPCU.setLanguage("พบ"));
        tableNormal.put("0", GutilPCU.setLanguage("ไม่ปกติ"));
        tableNormal.put("1", GutilPCU.setLanguage("ปกติ"));
    }

    public void setObject(PCUObject pcuobject) {
        /** ไปจัดการต่อเรื่องของข้อมูลที่จะรับ ถ้าเมื่อไรไม่มีข้อมูล นั้นจะทำอย่างไรบนหน้า GUI */
        Constant.println("_henbe_______________________" + this.getClass().toString());
        if (pcuobject != null) {
            if (pcuobject.getEmployee() != null) {
                theEmployee = pcuobject.getEmployee();

            } 
        }
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
        jPanelSchoolDetail = new javax.swing.JPanel();
        jPanelSearchSchool = new javax.swing.JPanel();
        jPanelSearch = new javax.swing.JPanel();
        jTextFieldSearchSchool = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jScrollPaneSchoolList = new javax.swing.JScrollPane();
        jTableSchoolList = new javax.swing.JTable();
        jPanelSchoolControl = new javax.swing.JPanel();
        jButtonAddSchool = new javax.swing.JButton();
        jButtonDelSchool = new javax.swing.JButton();
        jScrollPaneSchoolDetail = new javax.swing.JScrollPane();
        jTableSchoolDetail = new javax.swing.JTable();
        jPanelStudentServiceControl = new javax.swing.JPanel();
        jButtonAddStu = new javax.swing.JButton();
        jButtonDelStu = new javax.swing.JButton();
        jScrollPaneStudentServiceDetail = new javax.swing.JScrollPane();
        jTableStudentService = new javax.swing.JTable();
        jPanelCard = new javax.swing.JPanel();
        jPanelStudentSubDetail = new javax.swing.JPanel();
        jPanelSubStudentDetail = new javax.swing.JPanel();
        jLabelStuNumber2 = new javax.swing.JLabel();
        jTextFieldAge = new javax.swing.JTextField();
        jLableStuPID = new javax.swing.JLabel();
        jLableStuSex = new javax.swing.JLabel();
        jComboBoxStuSex = new javax.swing.JComboBox();
        pIDPanel = new com.pcu.utility.PIDPanel();
        jLabelStuNumber = new javax.swing.JLabel();
        jTextFieldStuNumber = new javax.swing.JTextField();
        jButtonSaveStu = new javax.swing.JButton();
        jScrollPaneStudentNote = new javax.swing.JScrollPane();
        jTextAreaStudentNote = new javax.swing.JTextArea();
        jLableStuSex1 = new javax.swing.JLabel();
        jPanelStuName = new javax.swing.JPanel();
        jComboBoxPrefixStu = new javax.swing.JComboBox();
        jLableNameStu = new javax.swing.JLabel();
        jTextFieldStuName = new javax.swing.JTextField();
        jTextFieldStuSurName = new javax.swing.JTextField();
        jButtonSearchFamily = new javax.swing.JButton();
        jPanelSubSchoolDetail = new javax.swing.JPanel();
        jLabelSchoolNameService = new javax.swing.JLabel();
        jComboBoxSchoolServiceType = new javax.swing.JComboBox();
        jLabelSchoolSchoolType = new javax.swing.JLabel();
        jPanelSchoolName = new javax.swing.JPanel();
        jComboBoxSchoolName = new javax.swing.JComboBox();
        jButtonRefresh = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabelClass = new javax.swing.JLabel();
        jComboBoxClass = new javax.swing.JComboBox();
        jLabelRoom = new javax.swing.JLabel();
        jTextFieldRoom = new javax.swing.JTextField();
        jLabelSchoolYear = new javax.swing.JLabel();
        jTextFieldSchoolYear = new com.pcu.utility.IntegerTextField();
        jLabelSchoolServiceDate = new javax.swing.JLabel();
        dateComboBoxServiceDate = new com.pcu.utility.DateComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabelServiceNote = new javax.swing.JLabel();
        jTextFieldServiceOther = new javax.swing.JTextField();
        jButtonSaveSchool = new javax.swing.JButton();
        jPanelShowSchoolData = new javax.swing.JPanel();
        jLabelDataSchoolClass = new javax.swing.JLabel();
        jLabelDataSchoolRoom = new javax.swing.JLabel();
        jLabelDataSchoolName = new javax.swing.JLabel();
        jLabelSchoolClass = new javax.swing.JLabel();
        jLabelSchoolRoom = new javax.swing.JLabel();
        jLabelSchoolName = new javax.swing.JLabel();
        jPanelServiceType = new javax.swing.JPanel();
        jLabelSchoolService = new javax.swing.JLabel();
        jLabelDataSchoolService = new javax.swing.JLabel();
        jPanelCardCheck = new javax.swing.JPanel();
        jPanelCheckOtherDetail = new javax.swing.JPanel();
        jPanelOtherStudentDetail = new javax.swing.JPanel();
        jLableOtherStatus = new javax.swing.JLabel();
        jLableOtherReferOut = new javax.swing.JLabel();
        jPanelOtherRefer = new javax.swing.JPanel();
        jTextFieldCheckOtherReferOut = new javax.swing.JTextField();
        jButtonCheckOtherReferOut = new javax.swing.JButton();
        integerTextFieldCheckOtherReferOut = new com.pcu.utility.IntegerTextField();
        panelYesNoCheckOther = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        jLabel23 = new javax.swing.JLabel();
        jScrollPaneStudentHealthNote11 = new javax.swing.JScrollPane();
        jTextAreaOtherNote = new javax.swing.JTextArea();
        jPanelSchoolCheckOtherControl = new javax.swing.JPanel();
        jButtonSaveCheckOther = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jTextAreaOtherRemark = new javax.swing.JTextField();
        jPanelCheckBodyDetail = new javax.swing.JPanel();
        jPanelBodyStudentDetail = new javax.swing.JPanel();
        jLableBodyStatus = new javax.swing.JLabel();
        jLabelBodyResultCheck = new javax.swing.JLabel();
        jLableBodyReferOut = new javax.swing.JLabel();
        jPanelBodyRefer = new javax.swing.JPanel();
        jTextFieldCheckBodyReferOut = new javax.swing.JTextField();
        jButtonCheckBodyReferOut = new javax.swing.JButton();
        integerTextFieldCheckBodyReferOut = new com.pcu.utility.IntegerTextField();
        panelYesNoCheckBody = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        panelYesNoCheckBodyResult = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        jLabel21 = new javax.swing.JLabel();
        jScrollPaneStudentHealthNote10 = new javax.swing.JScrollPane();
        jTextAreaBodyNote = new javax.swing.JTextArea();
        jPanelSchoolCheckBodyControl = new javax.swing.JPanel();
        jButtonSaveCheckBody = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jTextAreaBodyRemark = new javax.swing.JTextField();
        jPanelCheckLiceDetail = new javax.swing.JPanel();
        jPanelLiceStudentDetail = new javax.swing.JPanel();
        jLableLiceStatus = new javax.swing.JLabel();
        jLabelLiceResultCheck = new javax.swing.JLabel();
        jLableLiceReferOut = new javax.swing.JLabel();
        jPanelLiceRefer = new javax.swing.JPanel();
        jTextFieldCheckLiceReferOut = new javax.swing.JTextField();
        jButtonCheckLiceReferOut = new javax.swing.JButton();
        integerTextFieldCheckLiceReferOut = new com.pcu.utility.IntegerTextField();
        panelYesNoCheckLice = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        panelYesNoCheckLiceResultCheck = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        jLabel19 = new javax.swing.JLabel();
        jScrollPaneStudentHealthNote9 = new javax.swing.JScrollPane();
        jTextAreaLiceNote = new javax.swing.JTextArea();
        jPanelSchoolCheckLiceControl = new javax.swing.JPanel();
        jButtonSaveCheckLice = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jTextAreaLiceRemark = new javax.swing.JTextField();
        jPanelCheckThalassemiaDetail = new javax.swing.JPanel();
        jPanelThalassemiaStudentDetail = new javax.swing.JPanel();
        jLableThalassemiaStatus = new javax.swing.JLabel();
        jLabelThalassemiaResultCheck = new javax.swing.JLabel();
        jLableThalassemiaReferOut = new javax.swing.JLabel();
        jPanelThalassemiaRefer = new javax.swing.JPanel();
        jTextFieldCheckThalassemiaReferOut = new javax.swing.JTextField();
        jButtonCheckThalassemiaReferOut = new javax.swing.JButton();
        integerTextFieldCheckThalassemiaReferOut = new com.pcu.utility.IntegerTextField();
        panelYesNoCheckThalassemia = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        panelYesNoCheckThalassemiaResult = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        jLabel17 = new javax.swing.JLabel();
        jScrollPaneStudentHealthNote8 = new javax.swing.JScrollPane();
        jTextAreaThalassemiaNote = new javax.swing.JTextArea();
        jPanelSchoolCheckThalassemiaControl = new javax.swing.JPanel();
        jButtonSaveCheckThalassemia = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jTextAreaThalassemiaRemark = new javax.swing.JTextField();
        jPanelCheckWormDetail = new javax.swing.JPanel();
        jPanelWormStudentDetail = new javax.swing.JPanel();
        jLableWormStatus = new javax.swing.JLabel();
        jLabelWormResultCheck = new javax.swing.JLabel();
        jLableWormReferOut = new javax.swing.JLabel();
        jPanelWormRefer = new javax.swing.JPanel();
        jTextFieldCheckWormReferOut = new javax.swing.JTextField();
        jButtonCheckWormReferOut = new javax.swing.JButton();
        integerTextFieldCheckWormReferOut = new com.pcu.utility.IntegerTextField();
        panelYesNoCheckWorm = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        panelYesNoCheckWormResult = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        jLabel15 = new javax.swing.JLabel();
        jScrollPaneStudentHealthNote7 = new javax.swing.JScrollPane();
        jTextAreaWormNote = new javax.swing.JTextArea();
        jPanelSchoolCheckWormControl = new javax.swing.JPanel();
        jButtonSaveCheckWorm = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jTextAreaWormRemark = new javax.swing.JTextField();
        jPanelCheckStudentDentalDetail = new javax.swing.JPanel();
        jPanelStudentDentalStudentDetail = new javax.swing.JPanel();
        jLableStudentDentalStatus = new javax.swing.JLabel();
        jLabelStudentDentalResultCheck = new javax.swing.JLabel();
        jComboBoxStudentDentalResultCheck = new javax.swing.JComboBox();
        jLableStudentDentalReferOut = new javax.swing.JLabel();
        jPanelStudentDentalRefer = new javax.swing.JPanel();
        jTextFieldCheckStudentDentalReferOut = new javax.swing.JTextField();
        jButtonCheckStudentDentalReferOut = new javax.swing.JButton();
        integerTextFieldCheckStudentDentalReferOut = new com.pcu.utility.IntegerTextField();
        jLabelRealTooth = new javax.swing.JLabel();
        jLabelBadRealTooth = new javax.swing.JLabel();
        jLabelMilkTooth = new javax.swing.JLabel();
        jLabelBadMilkTooth = new javax.swing.JLabel();
        integerTextFieldRealTooth = new com.pcu.utility.IntegerTextField();
        integerTextFieldBadRealTooth = new com.pcu.utility.IntegerTextField();
        integerTextFieldMilkTooth = new com.pcu.utility.IntegerTextField();
        integerTextFieldBadMilkTooth = new com.pcu.utility.IntegerTextField();
        panelYesNoCheckDental = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPaneStudentHealthNote6 = new javax.swing.JScrollPane();
        jTextAreaStudentDentalNote = new javax.swing.JTextArea();
        jPanelSchoolCheckStudentDentalControl = new javax.swing.JPanel();
        jButtonSaveCheckStudentDental = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jTextAreaStudentDentalRemark = new javax.swing.JTextField();
        jPanelCheckFeBloodDetail = new javax.swing.JPanel();
        jPanelFeBloodStudentDetail = new javax.swing.JPanel();
        jLableFeBloodStatus = new javax.swing.JLabel();
        jLabelFeBloodResultCheck = new javax.swing.JLabel();
        jLableFeBloodReferOut = new javax.swing.JLabel();
        jPanelFeBloodRefer = new javax.swing.JPanel();
        jTextFieldCheckFeBloodReferOut = new javax.swing.JTextField();
        jButtonCheckFeBloodReferOut = new javax.swing.JButton();
        integerTextFieldCheckFeBloodReferOut = new com.pcu.utility.IntegerTextField();
        panelYesNoCheckFeBlood = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        panelYesNoFeBloodResult = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPaneStudentHealthNote5 = new javax.swing.JScrollPane();
        jTextAreaFeBloodNote = new javax.swing.JTextArea();
        jPanelSchoolCheckFeBloodControl = new javax.swing.JPanel();
        jButtonSaveCheckFeBlood = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jTextAreaFeBloodRemark = new javax.swing.JTextField();
        jPanelCheckGoiterDetail = new javax.swing.JPanel();
        jPanelGoiterStudentDetail = new javax.swing.JPanel();
        jLableGoiterStatus = new javax.swing.JLabel();
        jLabelGoiterResultCheck = new javax.swing.JLabel();
        jLableGoiterReferOut = new javax.swing.JLabel();
        jPanelGoiterRefer = new javax.swing.JPanel();
        jTextFieldCheckGoiterReferOut = new javax.swing.JTextField();
        jButtonCheckGoiterReferOut = new javax.swing.JButton();
        integerTextFieldCheckGoiterReferOut = new com.pcu.utility.IntegerTextField();
        panelYesNoCheckGoiter = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        panelYesNoGoiterResult = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPaneStudentHealthNote4 = new javax.swing.JScrollPane();
        jTextAreaGoiterNote = new javax.swing.JTextArea();
        jPanelSchoolCheckGoiterControl = new javax.swing.JPanel();
        jButtonSaveCheckGoiter = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextAreaGoiterRemark = new javax.swing.JTextField();
        jPanelCheckNutritionDetail = new javax.swing.JPanel();
        jPanelNutritionStudentDetail = new javax.swing.JPanel();
        jLableNutritionStatus = new javax.swing.JLabel();
        jLabelNutritionResultCheck = new javax.swing.JLabel();
        jComboBoxNutritionResultCheck = new javax.swing.JComboBox();
        jLableNutritionReferOut = new javax.swing.JLabel();
        jPanelNutritionRefer = new javax.swing.JPanel();
        jTextFieldCheckNutritionReferOut = new javax.swing.JTextField();
        jButtonCheckNutritionReferOut = new javax.swing.JButton();
        integerTextFieldCheckNutritionReferOut = new com.pcu.utility.IntegerTextField();
        panelYesNoCheckNutrition = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPaneStudentHealthNote3 = new javax.swing.JScrollPane();
        jTextAreaNutritionNote = new javax.swing.JTextArea();
        jPanelSchoolCheckNutritionControl = new javax.swing.JPanel();
        jButtonSaveCheckNutrition = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextAreaNutritionRemark = new javax.swing.JTextField();
        jPanelCheckStudentHealthDetail = new javax.swing.JPanel();
        jPanelStudentHealthStudentDetail = new javax.swing.JPanel();
        jLableStudentHealthStatus = new javax.swing.JLabel();
        jLabelStudentHealthResultCheck = new javax.swing.JLabel();
        jLableCheckStudentHealthReferOut = new javax.swing.JLabel();
        jPanelCheckStudentHealthRefer = new javax.swing.JPanel();
        jTextFieldCheckStudentHealthReferOut = new javax.swing.JTextField();
        jButtonCheckStudentHealthReferOut = new javax.swing.JButton();
        integerTextFieldCheckStudentHealthReferOut = new com.pcu.utility.IntegerTextField();
        jLabelStudentHealthWeight = new javax.swing.JLabel();
        doubleTextFieldStudentHealthWeight = new com.pcu.utility.DoubleTextField();
        doubleTextFieldStudentHealthTall = new com.pcu.utility.DoubleTextField();
        jLabelStudentHealthTall = new javax.swing.JLabel();
        panelYesNoStudentHealthStatus = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        panelYesNoStudentHealthResultCheck = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPaneStudentHealthNote = new javax.swing.JScrollPane();
        jTextAreaStudentHealthNote = new javax.swing.JTextArea();
        jPanelSchoolCheckStudentHealthControl = new javax.swing.JPanel();
        jButtonSaveCheckStudentHealth = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextAreaStudentHealthRemark = new javax.swing.JTextField();
        jPanelCheckEyeDetail = new javax.swing.JPanel();
        jPanelStudentDetail = new javax.swing.JPanel();
        jLableStatus = new javax.swing.JLabel();
        jLabelResultCheck = new javax.swing.JLabel();
        jComboBoxResultCheck = new javax.swing.JComboBox();
        jLableCheckEyeReferOut = new javax.swing.JLabel();
        jPanelRefer = new javax.swing.JPanel();
        jTextFieldCheckEyeReferOut = new javax.swing.JTextField();
        jButtonCheckEyeReferOut = new javax.swing.JButton();
        integerTextFieldCheckEyeReferOut = new com.pcu.utility.IntegerTextField();
        panelYesNoCheckEye = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPaneStudentHealthNote1 = new javax.swing.JScrollPane();
        jTextAreaEyeNote = new javax.swing.JTextArea();
        jPanelSchoolCheckEyeControl = new javax.swing.JPanel();
        jButtonSaveCheckEye = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextAreaEyeRemark = new javax.swing.JTextField();
        jPanelCheckEarDetail = new javax.swing.JPanel();
        jPanelEarStudentDetail = new javax.swing.JPanel();
        jLableEarStatus = new javax.swing.JLabel();
        jLabelEarResultCheck = new javax.swing.JLabel();
        jComboBoxEarResultCheck = new javax.swing.JComboBox();
        jLableCheckEarReferOut = new javax.swing.JLabel();
        jPanelCheckEarRefer = new javax.swing.JPanel();
        jTextFieldCheckEarReferOut = new javax.swing.JTextField();
        jButtonCheckEarReferOut = new javax.swing.JButton();
        integerTextFieldCheckEarReferOut = new com.pcu.utility.IntegerTextField();
        panelYesNoCheckEar = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPaneStudentHealthNote2 = new javax.swing.JScrollPane();
        jTextAreaEarNote = new javax.swing.JTextArea();
        jPanelSchoolCheckEarControl = new javax.swing.JPanel();
        jButtonSaveCheckEar = new javax.swing.JButton();
        jTextAreaEarRemark = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPaneCheckStudentHealth = new javax.swing.JScrollPane();
        jTableCheckStudentHealthDetail = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setLayout(new java.awt.GridBagLayout());

        jPanelSchoolDetail.setBorder(null);
        jPanelSchoolDetail.setLayout(new java.awt.GridBagLayout());

        jPanelSearchSchool.setLayout(new java.awt.GridBagLayout());

        jPanelSearch.setLayout(new java.awt.GridBagLayout());

        jTextFieldSearchSchool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchSchoolActionPerformed(evt);
            }
        });
        jTextFieldSearchSchool.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchSchoolKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanelSearch.add(jTextFieldSearchSchool, gridBagConstraints);

        jButtonSearch.setFont(defaultFont1);
        jButtonSearch.setText("Search");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelSearch.add(jButtonSearch, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelSearchSchool.add(jPanelSearch, gridBagConstraints);

        jTableSchoolList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableSchoolList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableSchoolListMouseReleased(evt);
            }
        });
        jScrollPaneSchoolList.setViewportView(jTableSchoolList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelSearchSchool.add(jScrollPaneSchoolList, gridBagConstraints);

        jPanelSchoolControl.setLayout(new java.awt.GridBagLayout());

        jButtonAddSchool.setFont(defaultFont1);
        jButtonAddSchool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAddSchool.setMaximumSize(new java.awt.Dimension(49, 25));
        jButtonAddSchool.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonAddSchool.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonAddSchool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddSchoolActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelSchoolControl.add(jButtonAddSchool, gridBagConstraints);

        jButtonDelSchool.setFont(defaultFont1);
        jButtonDelSchool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelSchool.setEnabled(false);
        jButtonDelSchool.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDelSchool.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDelSchool.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonDelSchool.setRequestFocusEnabled(false);
        jButtonDelSchool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelSchoolActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelSchoolControl.add(jButtonDelSchool, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanelSearchSchool.add(jPanelSchoolControl, gridBagConstraints);

        jTableSchoolDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableSchoolDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableSchoolDetailMouseReleased(evt);
            }
        });
        jScrollPaneSchoolDetail.setViewportView(jTableSchoolDetail);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelSearchSchool.add(jScrollPaneSchoolDetail, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanelSchoolDetail.add(jPanelSearchSchool, gridBagConstraints);

        jPanelStudentServiceControl.setLayout(new java.awt.GridBagLayout());

        jButtonAddStu.setFont(defaultFont1);
        jButtonAddStu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAddStu.setMaximumSize(new java.awt.Dimension(49, 25));
        jButtonAddStu.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonAddStu.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonAddStu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddStuActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelStudentServiceControl.add(jButtonAddStu, gridBagConstraints);

        jButtonDelStu.setFont(defaultFont1);
        jButtonDelStu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelStu.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDelStu.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDelStu.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonDelStu.setRequestFocusEnabled(false);
        jButtonDelStu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelStuActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelStudentServiceControl.add(jButtonDelStu, gridBagConstraints);

        jTableStudentService.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableStudentServiceMouseReleased(evt);
            }
        });
        jScrollPaneStudentServiceDetail.setViewportView(jTableStudentService);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelStudentServiceControl.add(jScrollPaneStudentServiceDetail, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 4, 5, 11);
        jPanelSchoolDetail.add(jPanelStudentServiceControl, gridBagConstraints);

        jPanelCard.setLayout(new java.awt.CardLayout());

        jPanelStudentSubDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("CheckStudentSubDetail"));
        jPanelStudentSubDetail.setLayout(new java.awt.GridBagLayout());

        jPanelSubStudentDetail.setLayout(new java.awt.GridBagLayout());

        jLabelStuNumber2.setFont(defaultFont1);
        jLabelStuNumber2.setText("Age");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 8, 0, 0);
        jPanelSubStudentDetail.add(jLabelStuNumber2, gridBagConstraints);

        jTextFieldAge.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldAge.setMinimumSize(new java.awt.Dimension(25, 21));
        jTextFieldAge.setPreferredSize(new java.awt.Dimension(25, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 0);
        jPanelSubStudentDetail.add(jTextFieldAge, gridBagConstraints);

        jLableStuPID.setFont(defaultFont1);
        jLableStuPID.setText("PID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelSubStudentDetail.add(jLableStuPID, gridBagConstraints);

        jLableStuSex.setFont(defaultFont1);
        jLableStuSex.setText("Sex");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 8, 0, 0);
        jPanelSubStudentDetail.add(jLableStuSex, gridBagConstraints);

        jComboBoxStuSex.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 0);
        jPanelSubStudentDetail.add(jComboBoxStuSex, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelSubStudentDetail.add(pIDPanel, gridBagConstraints);

        jLabelStuNumber.setFont(defaultFont1);
        jLabelStuNumber.setText("StudentNumber");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelSubStudentDetail.add(jLabelStuNumber, gridBagConstraints);

        jTextFieldStuNumber.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldStuNumber.setMinimumSize(new java.awt.Dimension(80, 21));
        jTextFieldStuNumber.setPreferredSize(new java.awt.Dimension(80, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 0);
        jPanelSubStudentDetail.add(jTextFieldStuNumber, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 11);
        jPanelStudentSubDetail.add(jPanelSubStudentDetail, gridBagConstraints);

        jButtonSaveStu.setFont(defaultFont1);
        jButtonSaveStu.setText("Save");
        jButtonSaveStu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveStuActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 10);
        jPanelStudentSubDetail.add(jButtonSaveStu, gridBagConstraints);

        jScrollPaneStudentNote.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPaneStudentNote.setMinimumSize(new java.awt.Dimension(220, 50));
        jScrollPaneStudentNote.setPreferredSize(new java.awt.Dimension(220, 50));

        jTextAreaStudentNote.setLineWrap(true);
        jTextAreaStudentNote.setWrapStyleWord(true);
        jTextAreaStudentNote.setMinimumSize(new java.awt.Dimension(220, 121));
        jScrollPaneStudentNote.setViewportView(jTextAreaStudentNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelStudentSubDetail.add(jScrollPaneStudentNote, gridBagConstraints);

        jLableStuSex1.setFont(defaultFont1);
        jLableStuSex1.setText("หมายเหตุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 0);
        jPanelStudentSubDetail.add(jLableStuSex1, gridBagConstraints);

        jPanelStuName.setLayout(new java.awt.GridBagLayout());

        jComboBoxPrefixStu.setFont(defaultFont1);
        jComboBoxPrefixStu.setMinimumSize(new java.awt.Dimension(65, 20));
        jComboBoxPrefixStu.setPreferredSize(new java.awt.Dimension(65, 20));
        jComboBoxPrefixStu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPrefixStuActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelStuName.add(jComboBoxPrefixStu, gridBagConstraints);

        jLableNameStu.setFont(defaultFont1);
        jLableNameStu.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelStuName.add(jLableNameStu, gridBagConstraints);

        jTextFieldStuName.setMinimumSize(new java.awt.Dimension(80, 21));
        jTextFieldStuName.setPreferredSize(new java.awt.Dimension(80, 21));
        jTextFieldStuName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStuNameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelStuName.add(jTextFieldStuName, gridBagConstraints);

        jTextFieldStuSurName.setMinimumSize(new java.awt.Dimension(80, 21));
        jTextFieldStuSurName.setPreferredSize(new java.awt.Dimension(80, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelStuName.add(jTextFieldStuSurName, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanelStudentSubDetail.add(jPanelStuName, gridBagConstraints);

        jButtonSearchFamily.setFont(defaultFont1);
        jButtonSearchFamily.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/search.gif"))); // NOI18N
        jButtonSearchFamily.setText("ค้น");
        jButtonSearchFamily.setToolTipText("ค้นประชากร");
        jButtonSearchFamily.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchFamilyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanelStudentSubDetail.add(jButtonSearchFamily, gridBagConstraints);

        jPanelCard.add(jPanelStudentSubDetail, "cardStudent");

        jPanelSubSchoolDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("SchoolSubDetail"));
        jPanelSubSchoolDetail.setLayout(new java.awt.GridBagLayout());

        jLabelSchoolNameService.setFont(defaultFont1);
        jLabelSchoolNameService.setText("SchoolServiceName");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelSubSchoolDetail.add(jLabelSchoolNameService, gridBagConstraints);

        jComboBoxSchoolServiceType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSchoolServiceTypeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelSubSchoolDetail.add(jComboBoxSchoolServiceType, gridBagConstraints);

        jLabelSchoolSchoolType.setFont(defaultFont1);
        jLabelSchoolSchoolType.setText("SchoolServiceType");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelSubSchoolDetail.add(jLabelSchoolSchoolType, gridBagConstraints);

        jPanelSchoolName.setLayout(new java.awt.GridBagLayout());

        jComboBoxSchoolName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSchoolNameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanelSchoolName.add(jComboBoxSchoolName, gridBagConstraints);

        jButtonRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pcu/images/Refresh2.gif"))); // NOI18N
        jButtonRefresh.setToolTipText("Refresh");
        jButtonRefresh.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonRefresh.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonRefresh.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelSchoolName.add(jButtonRefresh, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelSubSchoolDetail.add(jPanelSchoolName, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabelClass.setFont(defaultFont1);
        jLabelClass.setText("Class");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabelClass, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jComboBoxClass, gridBagConstraints);

        jLabelRoom.setFont(defaultFont1);
        jLabelRoom.setText("Room");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanel1.add(jLabelRoom, gridBagConstraints);

        jTextFieldRoom.setMinimumSize(new java.awt.Dimension(50, 30));
        jTextFieldRoom.setPreferredSize(new java.awt.Dimension(50, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jTextFieldRoom, gridBagConstraints);

        jLabelSchoolYear.setFont(defaultFont1);
        jLabelSchoolYear.setText("SchoolYear");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabelSchoolYear, gridBagConstraints);

        jTextFieldSchoolYear.setColumns(4);
        jTextFieldSchoolYear.setText("integerTextField1");
        jTextFieldSchoolYear.setMinimumSize(new java.awt.Dimension(50, 30));
        jTextFieldSchoolYear.setPreferredSize(new java.awt.Dimension(50, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanel1.add(jTextFieldSchoolYear, gridBagConstraints);

        jLabelSchoolServiceDate.setFont(defaultFont1);
        jLabelSchoolServiceDate.setText("SchoolServiceDate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 0);
        jPanel1.add(jLabelSchoolServiceDate, gridBagConstraints);

        dateComboBoxServiceDate.setMinimumSize(new java.awt.Dimension(119, 19));
        dateComboBoxServiceDate.setPreferredSize(new java.awt.Dimension(119, 19));
        dateComboBoxServiceDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxServiceDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel1.add(dateComboBoxServiceDate, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelSubSchoolDetail.add(jPanel1, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabelServiceNote.setFont(defaultFont1);
        jLabelServiceNote.setText("ServiceOther");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 5, 0);
        jPanel3.add(jLabelServiceNote, gridBagConstraints);

        jTextFieldServiceOther.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 5, 11);
        jPanel3.add(jTextFieldServiceOther, gridBagConstraints);

        jButtonSaveSchool.setFont(defaultFont1);
        jButtonSaveSchool.setText("Save");
        jButtonSaveSchool.setEnabled(false);
        jButtonSaveSchool.setMaximumSize(new java.awt.Dimension(67, 25));
        jButtonSaveSchool.setMinimumSize(new java.awt.Dimension(67, 25));
        jButtonSaveSchool.setPreferredSize(new java.awt.Dimension(67, 25));
        jButtonSaveSchool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveSchoolActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel3.add(jButtonSaveSchool, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanelSubSchoolDetail.add(jPanel3, gridBagConstraints);

        jPanelCard.add(jPanelSubSchoolDetail, "cardSchool");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        jPanelSchoolDetail.add(jPanelCard, gridBagConstraints);

        jPanelShowSchoolData.setBorder(javax.swing.BorderFactory.createTitledBorder("HealthSchoolDetail"));
        jPanelShowSchoolData.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelShowSchoolData.add(jLabelDataSchoolClass, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelShowSchoolData.add(jLabelDataSchoolRoom, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelShowSchoolData.add(jLabelDataSchoolName, gridBagConstraints);

        jLabelSchoolClass.setFont(defaultFont1);
        jLabelSchoolClass.setText("HClass");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelShowSchoolData.add(jLabelSchoolClass, gridBagConstraints);

        jLabelSchoolRoom.setFont(defaultFont1);
        jLabelSchoolRoom.setText("HRoom");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        jPanelShowSchoolData.add(jLabelSchoolRoom, gridBagConstraints);

        jLabelSchoolName.setFont(defaultFont1);
        jLabelSchoolName.setText("HSchoolName");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelShowSchoolData.add(jLabelSchoolName, gridBagConstraints);

        jPanelServiceType.setLayout(new java.awt.GridBagLayout());

        jLabelSchoolService.setFont(defaultFont1);
        jLabelSchoolService.setText("HSchoolServiceType");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelServiceType.add(jLabelSchoolService, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelServiceType.add(jLabelDataSchoolService, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelShowSchoolData.add(jPanelServiceType, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelSchoolDetail.add(jPanelShowSchoolData, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        add(jPanelSchoolDetail, gridBagConstraints);

        jPanelCardCheck.setLayout(new java.awt.CardLayout());

        jPanelCheckOtherDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("CheckOtherSubDetail"));
        jPanelCheckOtherDetail.setLayout(new java.awt.GridBagLayout());

        jPanelOtherStudentDetail.setLayout(new java.awt.GridBagLayout());

        jLableOtherStatus.setFont(defaultFont1);
        jLableOtherStatus.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelOtherStudentDetail.add(jLableOtherStatus, gridBagConstraints);

        jLableOtherReferOut.setFont(defaultFont1);
        jLableOtherReferOut.setText("ReferOutPlace");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelOtherStudentDetail.add(jLableOtherReferOut, gridBagConstraints);

        jPanelOtherRefer.setLayout(new java.awt.GridBagLayout());

        jTextFieldCheckOtherReferOut.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelOtherRefer.add(jTextFieldCheckOtherReferOut, gridBagConstraints);

        jButtonCheckOtherReferOut.setText("...");
        jButtonCheckOtherReferOut.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonCheckOtherReferOut.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonCheckOtherReferOut.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonCheckOtherReferOut.setRequestFocusEnabled(false);
        jButtonCheckOtherReferOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckOtherReferOutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelOtherRefer.add(jButtonCheckOtherReferOut, gridBagConstraints);

        integerTextFieldCheckOtherReferOut.setColumns(5);
        integerTextFieldCheckOtherReferOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldCheckOtherReferOutKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelOtherRefer.add(integerTextFieldCheckOtherReferOut, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelOtherStudentDetail.add(jPanelOtherRefer, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelOtherStudentDetail.add(panelYesNoCheckOther, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckOtherDetail.add(jPanelOtherStudentDetail, gridBagConstraints);

        jLabel23.setFont(defaultFont1);
        jLabel23.setText("รายละเอียด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckOtherDetail.add(jLabel23, gridBagConstraints);

        jScrollPaneStudentHealthNote11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPaneStudentHealthNote11.setMinimumSize(new java.awt.Dimension(220, 50));
        jScrollPaneStudentHealthNote11.setPreferredSize(new java.awt.Dimension(220, 50));

        jTextAreaOtherNote.setLineWrap(true);
        jTextAreaOtherNote.setWrapStyleWord(true);
        jTextAreaOtherNote.setMinimumSize(new java.awt.Dimension(220, 121));
        jScrollPaneStudentHealthNote11.setViewportView(jTextAreaOtherNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckOtherDetail.add(jScrollPaneStudentHealthNote11, gridBagConstraints);

        jPanelSchoolCheckOtherControl.setLayout(new java.awt.GridBagLayout());

        jButtonSaveCheckOther.setFont(defaultFont1);
        jButtonSaveCheckOther.setText("Save");
        jButtonSaveCheckOther.setMaximumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckOther.setMinimumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckOther.setPreferredSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckOther.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveCheckOtherActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanelSchoolCheckOtherControl.add(jButtonSaveCheckOther, gridBagConstraints);

        jLabel24.setFont(defaultFont1);
        jLabel24.setText("หมายเหตุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 11);
        jPanelSchoolCheckOtherControl.add(jLabel24, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelSchoolCheckOtherControl.add(jTextAreaOtherRemark, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelCheckOtherDetail.add(jPanelSchoolCheckOtherControl, gridBagConstraints);

        jPanelCardCheck.add(jPanelCheckOtherDetail, "อื่นๆ ระบุ");

        jPanelCheckBodyDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("CheckBodySubDetail"));
        jPanelCheckBodyDetail.setLayout(new java.awt.GridBagLayout());

        jPanelBodyStudentDetail.setLayout(new java.awt.GridBagLayout());

        jLableBodyStatus.setFont(defaultFont1);
        jLableBodyStatus.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelBodyStudentDetail.add(jLableBodyStatus, gridBagConstraints);

        jLabelBodyResultCheck.setFont(defaultFont1);
        jLabelBodyResultCheck.setText("ResultCheck");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelBodyStudentDetail.add(jLabelBodyResultCheck, gridBagConstraints);

        jLableBodyReferOut.setFont(defaultFont1);
        jLableBodyReferOut.setText("ReferOutPlace");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelBodyStudentDetail.add(jLableBodyReferOut, gridBagConstraints);

        jPanelBodyRefer.setLayout(new java.awt.GridBagLayout());

        jTextFieldCheckBodyReferOut.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelBodyRefer.add(jTextFieldCheckBodyReferOut, gridBagConstraints);

        jButtonCheckBodyReferOut.setText("...");
        jButtonCheckBodyReferOut.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonCheckBodyReferOut.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonCheckBodyReferOut.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonCheckBodyReferOut.setRequestFocusEnabled(false);
        jButtonCheckBodyReferOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckBodyReferOutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelBodyRefer.add(jButtonCheckBodyReferOut, gridBagConstraints);

        integerTextFieldCheckBodyReferOut.setColumns(5);
        integerTextFieldCheckBodyReferOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldCheckBodyReferOutKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelBodyRefer.add(integerTextFieldCheckBodyReferOut, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelBodyStudentDetail.add(jPanelBodyRefer, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelBodyStudentDetail.add(panelYesNoCheckBody, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelBodyStudentDetail.add(panelYesNoCheckBodyResult, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckBodyDetail.add(jPanelBodyStudentDetail, gridBagConstraints);

        jLabel21.setFont(defaultFont1);
        jLabel21.setText("รายละเอียด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckBodyDetail.add(jLabel21, gridBagConstraints);

        jScrollPaneStudentHealthNote10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPaneStudentHealthNote10.setMinimumSize(new java.awt.Dimension(220, 50));
        jScrollPaneStudentHealthNote10.setPreferredSize(new java.awt.Dimension(220, 50));

        jTextAreaBodyNote.setLineWrap(true);
        jTextAreaBodyNote.setWrapStyleWord(true);
        jTextAreaBodyNote.setMinimumSize(new java.awt.Dimension(220, 121));
        jScrollPaneStudentHealthNote10.setViewportView(jTextAreaBodyNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckBodyDetail.add(jScrollPaneStudentHealthNote10, gridBagConstraints);

        jPanelSchoolCheckBodyControl.setLayout(new java.awt.GridBagLayout());

        jButtonSaveCheckBody.setFont(defaultFont1);
        jButtonSaveCheckBody.setText("Save");
        jButtonSaveCheckBody.setMaximumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckBody.setMinimumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckBody.setPreferredSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckBody.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveCheckBodyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanelSchoolCheckBodyControl.add(jButtonSaveCheckBody, gridBagConstraints);

        jLabel22.setFont(defaultFont1);
        jLabel22.setText("หมายเหตุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 11);
        jPanelSchoolCheckBodyControl.add(jLabel22, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelSchoolCheckBodyControl.add(jTextAreaBodyRemark, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelCheckBodyDetail.add(jPanelSchoolCheckBodyControl, gridBagConstraints);

        jPanelCardCheck.add(jPanelCheckBodyDetail, "สมรรถภาพร่างกาย");

        jPanelCheckLiceDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("CheckLiceSubDetail"));
        jPanelCheckLiceDetail.setLayout(new java.awt.GridBagLayout());

        jPanelLiceStudentDetail.setLayout(new java.awt.GridBagLayout());

        jLableLiceStatus.setFont(defaultFont1);
        jLableLiceStatus.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelLiceStudentDetail.add(jLableLiceStatus, gridBagConstraints);

        jLabelLiceResultCheck.setFont(defaultFont1);
        jLabelLiceResultCheck.setText("ResultCheck");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelLiceStudentDetail.add(jLabelLiceResultCheck, gridBagConstraints);

        jLableLiceReferOut.setFont(defaultFont1);
        jLableLiceReferOut.setText("ReferOutPlace");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelLiceStudentDetail.add(jLableLiceReferOut, gridBagConstraints);

        jPanelLiceRefer.setLayout(new java.awt.GridBagLayout());

        jTextFieldCheckLiceReferOut.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelLiceRefer.add(jTextFieldCheckLiceReferOut, gridBagConstraints);

        jButtonCheckLiceReferOut.setText("...");
        jButtonCheckLiceReferOut.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonCheckLiceReferOut.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonCheckLiceReferOut.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonCheckLiceReferOut.setRequestFocusEnabled(false);
        jButtonCheckLiceReferOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckLiceReferOutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelLiceRefer.add(jButtonCheckLiceReferOut, gridBagConstraints);

        integerTextFieldCheckLiceReferOut.setColumns(5);
        integerTextFieldCheckLiceReferOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldCheckLiceReferOutKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelLiceRefer.add(integerTextFieldCheckLiceReferOut, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelLiceStudentDetail.add(jPanelLiceRefer, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelLiceStudentDetail.add(panelYesNoCheckLice, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelLiceStudentDetail.add(panelYesNoCheckLiceResultCheck, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckLiceDetail.add(jPanelLiceStudentDetail, gridBagConstraints);

        jLabel19.setFont(defaultFont1);
        jLabel19.setText("รายละเอียด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckLiceDetail.add(jLabel19, gridBagConstraints);

        jScrollPaneStudentHealthNote9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPaneStudentHealthNote9.setMinimumSize(new java.awt.Dimension(220, 50));
        jScrollPaneStudentHealthNote9.setPreferredSize(new java.awt.Dimension(220, 50));

        jTextAreaLiceNote.setLineWrap(true);
        jTextAreaLiceNote.setWrapStyleWord(true);
        jTextAreaLiceNote.setMinimumSize(new java.awt.Dimension(220, 121));
        jScrollPaneStudentHealthNote9.setViewportView(jTextAreaLiceNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckLiceDetail.add(jScrollPaneStudentHealthNote9, gridBagConstraints);

        jPanelSchoolCheckLiceControl.setLayout(new java.awt.GridBagLayout());

        jButtonSaveCheckLice.setFont(defaultFont1);
        jButtonSaveCheckLice.setText("Save");
        jButtonSaveCheckLice.setMaximumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckLice.setMinimumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckLice.setPreferredSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckLice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveCheckLiceActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanelSchoolCheckLiceControl.add(jButtonSaveCheckLice, gridBagConstraints);

        jLabel20.setFont(defaultFont1);
        jLabel20.setText("หมายเหตุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 11);
        jPanelSchoolCheckLiceControl.add(jLabel20, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelSchoolCheckLiceControl.add(jTextAreaLiceRemark, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelCheckLiceDetail.add(jPanelSchoolCheckLiceControl, gridBagConstraints);

        jPanelCardCheck.add(jPanelCheckLiceDetail, "เหา");

        jPanelCheckThalassemiaDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("CheckThalassemiaSubDetail"));
        jPanelCheckThalassemiaDetail.setLayout(new java.awt.GridBagLayout());

        jPanelThalassemiaStudentDetail.setLayout(new java.awt.GridBagLayout());

        jLableThalassemiaStatus.setFont(defaultFont1);
        jLableThalassemiaStatus.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelThalassemiaStudentDetail.add(jLableThalassemiaStatus, gridBagConstraints);

        jLabelThalassemiaResultCheck.setFont(defaultFont1);
        jLabelThalassemiaResultCheck.setText("ResultCheck");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelThalassemiaStudentDetail.add(jLabelThalassemiaResultCheck, gridBagConstraints);

        jLableThalassemiaReferOut.setFont(defaultFont1);
        jLableThalassemiaReferOut.setText("ReferOutPlace");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelThalassemiaStudentDetail.add(jLableThalassemiaReferOut, gridBagConstraints);

        jPanelThalassemiaRefer.setLayout(new java.awt.GridBagLayout());

        jTextFieldCheckThalassemiaReferOut.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelThalassemiaRefer.add(jTextFieldCheckThalassemiaReferOut, gridBagConstraints);

        jButtonCheckThalassemiaReferOut.setText("...");
        jButtonCheckThalassemiaReferOut.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonCheckThalassemiaReferOut.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonCheckThalassemiaReferOut.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonCheckThalassemiaReferOut.setRequestFocusEnabled(false);
        jButtonCheckThalassemiaReferOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckThalassemiaReferOutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelThalassemiaRefer.add(jButtonCheckThalassemiaReferOut, gridBagConstraints);

        integerTextFieldCheckThalassemiaReferOut.setColumns(5);
        integerTextFieldCheckThalassemiaReferOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldCheckThalassemiaReferOutKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelThalassemiaRefer.add(integerTextFieldCheckThalassemiaReferOut, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelThalassemiaStudentDetail.add(jPanelThalassemiaRefer, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelThalassemiaStudentDetail.add(panelYesNoCheckThalassemia, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelThalassemiaStudentDetail.add(panelYesNoCheckThalassemiaResult, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckThalassemiaDetail.add(jPanelThalassemiaStudentDetail, gridBagConstraints);

        jLabel17.setFont(defaultFont1);
        jLabel17.setText("รายละเอียด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckThalassemiaDetail.add(jLabel17, gridBagConstraints);

        jScrollPaneStudentHealthNote8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPaneStudentHealthNote8.setMinimumSize(new java.awt.Dimension(220, 50));
        jScrollPaneStudentHealthNote8.setPreferredSize(new java.awt.Dimension(220, 50));

        jTextAreaThalassemiaNote.setLineWrap(true);
        jTextAreaThalassemiaNote.setWrapStyleWord(true);
        jTextAreaThalassemiaNote.setMinimumSize(new java.awt.Dimension(220, 121));
        jScrollPaneStudentHealthNote8.setViewportView(jTextAreaThalassemiaNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckThalassemiaDetail.add(jScrollPaneStudentHealthNote8, gridBagConstraints);

        jPanelSchoolCheckThalassemiaControl.setLayout(new java.awt.GridBagLayout());

        jButtonSaveCheckThalassemia.setFont(defaultFont1);
        jButtonSaveCheckThalassemia.setText("Save");
        jButtonSaveCheckThalassemia.setMaximumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckThalassemia.setMinimumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckThalassemia.setPreferredSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckThalassemia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveCheckThalassemiaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanelSchoolCheckThalassemiaControl.add(jButtonSaveCheckThalassemia, gridBagConstraints);

        jLabel18.setFont(defaultFont1);
        jLabel18.setText("หมายเหตุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 11);
        jPanelSchoolCheckThalassemiaControl.add(jLabel18, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelSchoolCheckThalassemiaControl.add(jTextAreaThalassemiaRemark, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelCheckThalassemiaDetail.add(jPanelSchoolCheckThalassemiaControl, gridBagConstraints);

        jPanelCardCheck.add(jPanelCheckThalassemiaDetail, "ธาลาสซิเมีย");

        jPanelCheckWormDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("CheckWormSubDetail"));
        jPanelCheckWormDetail.setLayout(new java.awt.GridBagLayout());

        jPanelWormStudentDetail.setLayout(new java.awt.GridBagLayout());

        jLableWormStatus.setFont(defaultFont1);
        jLableWormStatus.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelWormStudentDetail.add(jLableWormStatus, gridBagConstraints);

        jLabelWormResultCheck.setFont(defaultFont1);
        jLabelWormResultCheck.setText("ResultCheck");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelWormStudentDetail.add(jLabelWormResultCheck, gridBagConstraints);

        jLableWormReferOut.setFont(defaultFont1);
        jLableWormReferOut.setText("ReferOutPlace");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelWormStudentDetail.add(jLableWormReferOut, gridBagConstraints);

        jPanelWormRefer.setLayout(new java.awt.GridBagLayout());

        jTextFieldCheckWormReferOut.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelWormRefer.add(jTextFieldCheckWormReferOut, gridBagConstraints);

        jButtonCheckWormReferOut.setText("...");
        jButtonCheckWormReferOut.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonCheckWormReferOut.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonCheckWormReferOut.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonCheckWormReferOut.setRequestFocusEnabled(false);
        jButtonCheckWormReferOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckWormReferOutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelWormRefer.add(jButtonCheckWormReferOut, gridBagConstraints);

        integerTextFieldCheckWormReferOut.setColumns(5);
        integerTextFieldCheckWormReferOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldCheckWormReferOutKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelWormRefer.add(integerTextFieldCheckWormReferOut, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelWormStudentDetail.add(jPanelWormRefer, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelWormStudentDetail.add(panelYesNoCheckWorm, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelWormStudentDetail.add(panelYesNoCheckWormResult, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckWormDetail.add(jPanelWormStudentDetail, gridBagConstraints);

        jLabel15.setFont(defaultFont1);
        jLabel15.setText("รายละเอียด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckWormDetail.add(jLabel15, gridBagConstraints);

        jScrollPaneStudentHealthNote7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPaneStudentHealthNote7.setMinimumSize(new java.awt.Dimension(220, 50));
        jScrollPaneStudentHealthNote7.setPreferredSize(new java.awt.Dimension(220, 50));

        jTextAreaWormNote.setLineWrap(true);
        jTextAreaWormNote.setWrapStyleWord(true);
        jTextAreaWormNote.setMinimumSize(new java.awt.Dimension(220, 121));
        jScrollPaneStudentHealthNote7.setViewportView(jTextAreaWormNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckWormDetail.add(jScrollPaneStudentHealthNote7, gridBagConstraints);

        jPanelSchoolCheckWormControl.setLayout(new java.awt.GridBagLayout());

        jButtonSaveCheckWorm.setFont(defaultFont1);
        jButtonSaveCheckWorm.setText("Save");
        jButtonSaveCheckWorm.setMaximumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckWorm.setMinimumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckWorm.setPreferredSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckWorm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveCheckWormActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanelSchoolCheckWormControl.add(jButtonSaveCheckWorm, gridBagConstraints);

        jLabel16.setFont(defaultFont1);
        jLabel16.setText("หมายเหตุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 11);
        jPanelSchoolCheckWormControl.add(jLabel16, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelSchoolCheckWormControl.add(jTextAreaWormRemark, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelCheckWormDetail.add(jPanelSchoolCheckWormControl, gridBagConstraints);

        jPanelCardCheck.add(jPanelCheckWormDetail, "หนอนพยาธิ");

        jPanelCheckStudentDentalDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("CheckStudentDentalSubDetail"));
        jPanelCheckStudentDentalDetail.setLayout(new java.awt.GridBagLayout());

        jPanelStudentDentalStudentDetail.setLayout(new java.awt.GridBagLayout());

        jLableStudentDentalStatus.setFont(defaultFont1);
        jLableStudentDentalStatus.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelStudentDentalStudentDetail.add(jLableStudentDentalStatus, gridBagConstraints);

        jLabelStudentDentalResultCheck.setFont(defaultFont1);
        jLabelStudentDentalResultCheck.setText("GumLevel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelStudentDentalStudentDetail.add(jLabelStudentDentalResultCheck, gridBagConstraints);

        jComboBoxStudentDentalResultCheck.setMinimumSize(new java.awt.Dimension(50, 20));
        jComboBoxStudentDentalResultCheck.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelStudentDentalStudentDetail.add(jComboBoxStudentDentalResultCheck, gridBagConstraints);

        jLableStudentDentalReferOut.setFont(defaultFont1);
        jLableStudentDentalReferOut.setText("ReferOutPlace");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelStudentDentalStudentDetail.add(jLableStudentDentalReferOut, gridBagConstraints);

        jPanelStudentDentalRefer.setLayout(new java.awt.GridBagLayout());

        jTextFieldCheckStudentDentalReferOut.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelStudentDentalRefer.add(jTextFieldCheckStudentDentalReferOut, gridBagConstraints);

        jButtonCheckStudentDentalReferOut.setText("...");
        jButtonCheckStudentDentalReferOut.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonCheckStudentDentalReferOut.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonCheckStudentDentalReferOut.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonCheckStudentDentalReferOut.setRequestFocusEnabled(false);
        jButtonCheckStudentDentalReferOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckStudentDentalReferOutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelStudentDentalRefer.add(jButtonCheckStudentDentalReferOut, gridBagConstraints);

        integerTextFieldCheckStudentDentalReferOut.setColumns(5);
        integerTextFieldCheckStudentDentalReferOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldCheckStudentDentalReferOutKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelStudentDentalRefer.add(integerTextFieldCheckStudentDentalReferOut, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelStudentDentalStudentDetail.add(jPanelStudentDentalRefer, gridBagConstraints);

        jLabelRealTooth.setFont(defaultFont1);
        jLabelRealTooth.setText("RealTooth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelStudentDentalStudentDetail.add(jLabelRealTooth, gridBagConstraints);

        jLabelBadRealTooth.setFont(defaultFont1);
        jLabelBadRealTooth.setText("BadRealTooth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 0);
        jPanelStudentDentalStudentDetail.add(jLabelBadRealTooth, gridBagConstraints);

        jLabelMilkTooth.setFont(defaultFont1);
        jLabelMilkTooth.setText("MilkTooth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelStudentDentalStudentDetail.add(jLabelMilkTooth, gridBagConstraints);

        jLabelBadMilkTooth.setFont(defaultFont1);
        jLabelBadMilkTooth.setText("BadMilkTooth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 0);
        jPanelStudentDentalStudentDetail.add(jLabelBadMilkTooth, gridBagConstraints);

        integerTextFieldRealTooth.setColumns(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelStudentDentalStudentDetail.add(integerTextFieldRealTooth, gridBagConstraints);

        integerTextFieldBadRealTooth.setColumns(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelStudentDentalStudentDetail.add(integerTextFieldBadRealTooth, gridBagConstraints);

        integerTextFieldMilkTooth.setColumns(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelStudentDentalStudentDetail.add(integerTextFieldMilkTooth, gridBagConstraints);

        integerTextFieldBadMilkTooth.setColumns(2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelStudentDentalStudentDetail.add(integerTextFieldBadMilkTooth, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelStudentDentalStudentDetail.add(panelYesNoCheckDental, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 11);
        jPanelCheckStudentDentalDetail.add(jPanelStudentDentalStudentDetail, gridBagConstraints);

        jLabel13.setFont(defaultFont1);
        jLabel13.setText("รายละเอียด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 11);
        jPanelCheckStudentDentalDetail.add(jLabel13, gridBagConstraints);

        jScrollPaneStudentHealthNote6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPaneStudentHealthNote6.setMinimumSize(new java.awt.Dimension(220, 50));
        jScrollPaneStudentHealthNote6.setPreferredSize(new java.awt.Dimension(220, 50));

        jTextAreaStudentDentalNote.setLineWrap(true);
        jTextAreaStudentDentalNote.setWrapStyleWord(true);
        jTextAreaStudentDentalNote.setMinimumSize(new java.awt.Dimension(220, 121));
        jScrollPaneStudentHealthNote6.setViewportView(jTextAreaStudentDentalNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 11);
        jPanelCheckStudentDentalDetail.add(jScrollPaneStudentHealthNote6, gridBagConstraints);

        jPanelSchoolCheckStudentDentalControl.setLayout(new java.awt.GridBagLayout());

        jButtonSaveCheckStudentDental.setFont(defaultFont1);
        jButtonSaveCheckStudentDental.setText("Save");
        jButtonSaveCheckStudentDental.setMaximumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckStudentDental.setMinimumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckStudentDental.setPreferredSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckStudentDental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveCheckStudentDentalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanelSchoolCheckStudentDentalControl.add(jButtonSaveCheckStudentDental, gridBagConstraints);

        jLabel14.setFont(defaultFont1);
        jLabel14.setText("หมายเหตุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 11);
        jPanelSchoolCheckStudentDentalControl.add(jLabel14, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 3, 11);
        jPanelSchoolCheckStudentDentalControl.add(jTextAreaStudentDentalRemark, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 3, 11);
        jPanelCheckStudentDentalDetail.add(jPanelSchoolCheckStudentDentalControl, gridBagConstraints);

        jPanelCardCheck.add(jPanelCheckStudentDentalDetail, "ทันตกรรม");

        jPanelCheckFeBloodDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("CheckFeBloodSubDetail"));
        jPanelCheckFeBloodDetail.setLayout(new java.awt.GridBagLayout());

        jPanelFeBloodStudentDetail.setLayout(new java.awt.GridBagLayout());

        jLableFeBloodStatus.setFont(defaultFont1);
        jLableFeBloodStatus.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelFeBloodStudentDetail.add(jLableFeBloodStatus, gridBagConstraints);

        jLabelFeBloodResultCheck.setFont(defaultFont1);
        jLabelFeBloodResultCheck.setText("ResultCheck");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelFeBloodStudentDetail.add(jLabelFeBloodResultCheck, gridBagConstraints);

        jLableFeBloodReferOut.setFont(defaultFont1);
        jLableFeBloodReferOut.setText("ReferOutPlace");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelFeBloodStudentDetail.add(jLableFeBloodReferOut, gridBagConstraints);

        jPanelFeBloodRefer.setLayout(new java.awt.GridBagLayout());

        jTextFieldCheckFeBloodReferOut.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelFeBloodRefer.add(jTextFieldCheckFeBloodReferOut, gridBagConstraints);

        jButtonCheckFeBloodReferOut.setText("...");
        jButtonCheckFeBloodReferOut.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonCheckFeBloodReferOut.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonCheckFeBloodReferOut.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonCheckFeBloodReferOut.setRequestFocusEnabled(false);
        jButtonCheckFeBloodReferOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckFeBloodReferOutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelFeBloodRefer.add(jButtonCheckFeBloodReferOut, gridBagConstraints);

        integerTextFieldCheckFeBloodReferOut.setColumns(5);
        integerTextFieldCheckFeBloodReferOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldCheckFeBloodReferOutKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelFeBloodRefer.add(integerTextFieldCheckFeBloodReferOut, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelFeBloodStudentDetail.add(jPanelFeBloodRefer, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelFeBloodStudentDetail.add(panelYesNoCheckFeBlood, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelFeBloodStudentDetail.add(panelYesNoFeBloodResult, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckFeBloodDetail.add(jPanelFeBloodStudentDetail, gridBagConstraints);

        jLabel11.setFont(defaultFont1);
        jLabel11.setText("รายละเอียด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckFeBloodDetail.add(jLabel11, gridBagConstraints);

        jScrollPaneStudentHealthNote5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPaneStudentHealthNote5.setMinimumSize(new java.awt.Dimension(220, 50));
        jScrollPaneStudentHealthNote5.setPreferredSize(new java.awt.Dimension(220, 50));

        jTextAreaFeBloodNote.setLineWrap(true);
        jTextAreaFeBloodNote.setWrapStyleWord(true);
        jTextAreaFeBloodNote.setMinimumSize(new java.awt.Dimension(220, 121));
        jScrollPaneStudentHealthNote5.setViewportView(jTextAreaFeBloodNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckFeBloodDetail.add(jScrollPaneStudentHealthNote5, gridBagConstraints);

        jPanelSchoolCheckFeBloodControl.setLayout(new java.awt.GridBagLayout());

        jButtonSaveCheckFeBlood.setFont(defaultFont1);
        jButtonSaveCheckFeBlood.setText("Save");
        jButtonSaveCheckFeBlood.setMaximumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckFeBlood.setMinimumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckFeBlood.setPreferredSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckFeBlood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveCheckFeBloodActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanelSchoolCheckFeBloodControl.add(jButtonSaveCheckFeBlood, gridBagConstraints);

        jLabel12.setFont(defaultFont1);
        jLabel12.setText("หมายเหตุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 11);
        jPanelSchoolCheckFeBloodControl.add(jLabel12, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelSchoolCheckFeBloodControl.add(jTextAreaFeBloodRemark, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelCheckFeBloodDetail.add(jPanelSchoolCheckFeBloodControl, gridBagConstraints);

        jPanelCardCheck.add(jPanelCheckFeBloodDetail, "โลหิตจางขาดธาตุเหล็ก");

        jPanelCheckGoiterDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("CheckGoiterSubDetail"));
        jPanelCheckGoiterDetail.setLayout(new java.awt.GridBagLayout());

        jPanelGoiterStudentDetail.setLayout(new java.awt.GridBagLayout());

        jLableGoiterStatus.setFont(defaultFont1);
        jLableGoiterStatus.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelGoiterStudentDetail.add(jLableGoiterStatus, gridBagConstraints);

        jLabelGoiterResultCheck.setFont(defaultFont1);
        jLabelGoiterResultCheck.setText("ResultCheck");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelGoiterStudentDetail.add(jLabelGoiterResultCheck, gridBagConstraints);

        jLableGoiterReferOut.setFont(defaultFont1);
        jLableGoiterReferOut.setText("ReferOutPlace");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelGoiterStudentDetail.add(jLableGoiterReferOut, gridBagConstraints);

        jPanelGoiterRefer.setLayout(new java.awt.GridBagLayout());

        jTextFieldCheckGoiterReferOut.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelGoiterRefer.add(jTextFieldCheckGoiterReferOut, gridBagConstraints);

        jButtonCheckGoiterReferOut.setText("...");
        jButtonCheckGoiterReferOut.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonCheckGoiterReferOut.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonCheckGoiterReferOut.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonCheckGoiterReferOut.setRequestFocusEnabled(false);
        jButtonCheckGoiterReferOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckGoiterReferOutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelGoiterRefer.add(jButtonCheckGoiterReferOut, gridBagConstraints);

        integerTextFieldCheckGoiterReferOut.setColumns(5);
        integerTextFieldCheckGoiterReferOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldCheckGoiterReferOutKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelGoiterRefer.add(integerTextFieldCheckGoiterReferOut, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelGoiterStudentDetail.add(jPanelGoiterRefer, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelGoiterStudentDetail.add(panelYesNoCheckGoiter, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelGoiterStudentDetail.add(panelYesNoGoiterResult, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckGoiterDetail.add(jPanelGoiterStudentDetail, gridBagConstraints);

        jLabel9.setFont(defaultFont1);
        jLabel9.setText("รายละเอียด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckGoiterDetail.add(jLabel9, gridBagConstraints);

        jScrollPaneStudentHealthNote4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPaneStudentHealthNote4.setMinimumSize(new java.awt.Dimension(220, 50));
        jScrollPaneStudentHealthNote4.setPreferredSize(new java.awt.Dimension(220, 50));

        jTextAreaGoiterNote.setLineWrap(true);
        jTextAreaGoiterNote.setWrapStyleWord(true);
        jTextAreaGoiterNote.setMinimumSize(new java.awt.Dimension(220, 121));
        jScrollPaneStudentHealthNote4.setViewportView(jTextAreaGoiterNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckGoiterDetail.add(jScrollPaneStudentHealthNote4, gridBagConstraints);

        jPanelSchoolCheckGoiterControl.setLayout(new java.awt.GridBagLayout());

        jButtonSaveCheckGoiter.setFont(defaultFont1);
        jButtonSaveCheckGoiter.setText("Save");
        jButtonSaveCheckGoiter.setMaximumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckGoiter.setMinimumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckGoiter.setPreferredSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckGoiter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveCheckGoiterActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanelSchoolCheckGoiterControl.add(jButtonSaveCheckGoiter, gridBagConstraints);

        jLabel10.setFont(defaultFont1);
        jLabel10.setText("หมายเหตุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 11);
        jPanelSchoolCheckGoiterControl.add(jLabel10, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelSchoolCheckGoiterControl.add(jTextAreaGoiterRemark, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelCheckGoiterDetail.add(jPanelSchoolCheckGoiterControl, gridBagConstraints);

        jPanelCardCheck.add(jPanelCheckGoiterDetail, "คอพอก");

        jPanelCheckNutritionDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("CheckNutritionSubDetail"));
        jPanelCheckNutritionDetail.setLayout(new java.awt.GridBagLayout());

        jPanelNutritionStudentDetail.setLayout(new java.awt.GridBagLayout());

        jLableNutritionStatus.setFont(defaultFont1);
        jLableNutritionStatus.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelNutritionStudentDetail.add(jLableNutritionStatus, gridBagConstraints);

        jLabelNutritionResultCheck.setFont(defaultFont1);
        jLabelNutritionResultCheck.setText("ResultCheck");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelNutritionStudentDetail.add(jLabelNutritionResultCheck, gridBagConstraints);

        jComboBoxNutritionResultCheck.setMinimumSize(new java.awt.Dimension(50, 20));
        jComboBoxNutritionResultCheck.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelNutritionStudentDetail.add(jComboBoxNutritionResultCheck, gridBagConstraints);

        jLableNutritionReferOut.setFont(defaultFont1);
        jLableNutritionReferOut.setText("ReferOutPlace");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelNutritionStudentDetail.add(jLableNutritionReferOut, gridBagConstraints);

        jPanelNutritionRefer.setLayout(new java.awt.GridBagLayout());

        jTextFieldCheckNutritionReferOut.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelNutritionRefer.add(jTextFieldCheckNutritionReferOut, gridBagConstraints);

        jButtonCheckNutritionReferOut.setText("...");
        jButtonCheckNutritionReferOut.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonCheckNutritionReferOut.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonCheckNutritionReferOut.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonCheckNutritionReferOut.setRequestFocusEnabled(false);
        jButtonCheckNutritionReferOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckNutritionReferOutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelNutritionRefer.add(jButtonCheckNutritionReferOut, gridBagConstraints);

        integerTextFieldCheckNutritionReferOut.setColumns(5);
        integerTextFieldCheckNutritionReferOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldCheckNutritionReferOutKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelNutritionRefer.add(integerTextFieldCheckNutritionReferOut, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelNutritionStudentDetail.add(jPanelNutritionRefer, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelNutritionStudentDetail.add(panelYesNoCheckNutrition, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckNutritionDetail.add(jPanelNutritionStudentDetail, gridBagConstraints);

        jLabel7.setFont(defaultFont1);
        jLabel7.setText("รายละเอียด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckNutritionDetail.add(jLabel7, gridBagConstraints);

        jScrollPaneStudentHealthNote3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPaneStudentHealthNote3.setMinimumSize(new java.awt.Dimension(220, 50));
        jScrollPaneStudentHealthNote3.setPreferredSize(new java.awt.Dimension(220, 50));

        jTextAreaNutritionNote.setLineWrap(true);
        jTextAreaNutritionNote.setWrapStyleWord(true);
        jTextAreaNutritionNote.setMinimumSize(new java.awt.Dimension(220, 121));
        jScrollPaneStudentHealthNote3.setViewportView(jTextAreaNutritionNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckNutritionDetail.add(jScrollPaneStudentHealthNote3, gridBagConstraints);

        jPanelSchoolCheckNutritionControl.setLayout(new java.awt.GridBagLayout());

        jButtonSaveCheckNutrition.setFont(defaultFont1);
        jButtonSaveCheckNutrition.setText("Save");
        jButtonSaveCheckNutrition.setMaximumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckNutrition.setMinimumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckNutrition.setPreferredSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckNutrition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveCheckNutritionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanelSchoolCheckNutritionControl.add(jButtonSaveCheckNutrition, gridBagConstraints);

        jLabel8.setFont(defaultFont1);
        jLabel8.setText("หมายเหตุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 11);
        jPanelSchoolCheckNutritionControl.add(jLabel8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelSchoolCheckNutritionControl.add(jTextAreaNutritionRemark, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelCheckNutritionDetail.add(jPanelSchoolCheckNutritionControl, gridBagConstraints);

        jPanelCardCheck.add(jPanelCheckNutritionDetail, "โภชนาการ");

        jPanelCheckStudentHealthDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("CheckStudentHealthSubDetail"));
        jPanelCheckStudentHealthDetail.setLayout(new java.awt.GridBagLayout());

        jPanelStudentHealthStudentDetail.setLayout(new java.awt.GridBagLayout());

        jLableStudentHealthStatus.setFont(defaultFont1);
        jLableStudentHealthStatus.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelStudentHealthStudentDetail.add(jLableStudentHealthStatus, gridBagConstraints);

        jLabelStudentHealthResultCheck.setFont(defaultFont1);
        jLabelStudentHealthResultCheck.setText("ResultCheck");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelStudentHealthStudentDetail.add(jLabelStudentHealthResultCheck, gridBagConstraints);

        jLableCheckStudentHealthReferOut.setFont(defaultFont1);
        jLableCheckStudentHealthReferOut.setText("ReferOutPlace");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelStudentHealthStudentDetail.add(jLableCheckStudentHealthReferOut, gridBagConstraints);

        jPanelCheckStudentHealthRefer.setLayout(new java.awt.GridBagLayout());

        jTextFieldCheckStudentHealthReferOut.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelCheckStudentHealthRefer.add(jTextFieldCheckStudentHealthReferOut, gridBagConstraints);

        jButtonCheckStudentHealthReferOut.setText("...");
        jButtonCheckStudentHealthReferOut.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonCheckStudentHealthReferOut.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonCheckStudentHealthReferOut.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonCheckStudentHealthReferOut.setRequestFocusEnabled(false);
        jButtonCheckStudentHealthReferOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckStudentHealthReferOutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelCheckStudentHealthRefer.add(jButtonCheckStudentHealthReferOut, gridBagConstraints);

        integerTextFieldCheckStudentHealthReferOut.setColumns(5);
        integerTextFieldCheckStudentHealthReferOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldCheckStudentHealthReferOutKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelCheckStudentHealthRefer.add(integerTextFieldCheckStudentHealthReferOut, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelStudentHealthStudentDetail.add(jPanelCheckStudentHealthRefer, gridBagConstraints);

        jLabelStudentHealthWeight.setFont(defaultFont1);
        jLabelStudentHealthWeight.setText("NtrWeight");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelStudentHealthStudentDetail.add(jLabelStudentHealthWeight, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelStudentHealthStudentDetail.add(doubleTextFieldStudentHealthWeight, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelStudentHealthStudentDetail.add(doubleTextFieldStudentHealthTall, gridBagConstraints);

        jLabelStudentHealthTall.setFont(defaultFont1);
        jLabelStudentHealthTall.setText("NtrHigh");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelStudentHealthStudentDetail.add(jLabelStudentHealthTall, gridBagConstraints);

        panelYesNoStudentHealthStatus.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelStudentHealthStudentDetail.add(panelYesNoStudentHealthStatus, gridBagConstraints);

        panelYesNoStudentHealthResultCheck.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelStudentHealthStudentDetail.add(panelYesNoStudentHealthResultCheck, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckStudentHealthDetail.add(jPanelStudentHealthStudentDetail, gridBagConstraints);

        jLabel1.setFont(defaultFont1);
        jLabel1.setText("รายละเอียด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckStudentHealthDetail.add(jLabel1, gridBagConstraints);

        jScrollPaneStudentHealthNote.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPaneStudentHealthNote.setMinimumSize(new java.awt.Dimension(220, 50));
        jScrollPaneStudentHealthNote.setPreferredSize(new java.awt.Dimension(220, 50));

        jTextAreaStudentHealthNote.setLineWrap(true);
        jTextAreaStudentHealthNote.setWrapStyleWord(true);
        jTextAreaStudentHealthNote.setMinimumSize(new java.awt.Dimension(220, 121));
        jScrollPaneStudentHealthNote.setViewportView(jTextAreaStudentHealthNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckStudentHealthDetail.add(jScrollPaneStudentHealthNote, gridBagConstraints);

        jPanelSchoolCheckStudentHealthControl.setLayout(new java.awt.GridBagLayout());

        jButtonSaveCheckStudentHealth.setFont(defaultFont1);
        jButtonSaveCheckStudentHealth.setText("Save");
        jButtonSaveCheckStudentHealth.setMaximumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckStudentHealth.setMinimumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckStudentHealth.setPreferredSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckStudentHealth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveCheckStudentHealthActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanelSchoolCheckStudentHealthControl.add(jButtonSaveCheckStudentHealth, gridBagConstraints);

        jLabel2.setFont(defaultFont1);
        jLabel2.setText("หมายเหตุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 11);
        jPanelSchoolCheckStudentHealthControl.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelSchoolCheckStudentHealthControl.add(jTextAreaStudentHealthRemark, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelCheckStudentHealthDetail.add(jPanelSchoolCheckStudentHealthControl, gridBagConstraints);

        jPanelCardCheck.add(jPanelCheckStudentHealthDetail, "ตรวจสุขภาพ");

        jPanelCheckEyeDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("CheckEyeSubDetail"));
        jPanelCheckEyeDetail.setLayout(new java.awt.GridBagLayout());

        jPanelStudentDetail.setLayout(new java.awt.GridBagLayout());

        jLableStatus.setFont(defaultFont1);
        jLableStatus.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelStudentDetail.add(jLableStatus, gridBagConstraints);

        jLabelResultCheck.setFont(defaultFont1);
        jLabelResultCheck.setText("ResultCheck");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelStudentDetail.add(jLabelResultCheck, gridBagConstraints);

        jComboBoxResultCheck.setMinimumSize(new java.awt.Dimension(50, 20));
        jComboBoxResultCheck.setPreferredSize(new java.awt.Dimension(50, 20));
        jComboBoxResultCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxResultCheckActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelStudentDetail.add(jComboBoxResultCheck, gridBagConstraints);

        jLableCheckEyeReferOut.setFont(defaultFont1);
        jLableCheckEyeReferOut.setText("ReferOutPlace");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelStudentDetail.add(jLableCheckEyeReferOut, gridBagConstraints);

        jPanelRefer.setLayout(new java.awt.GridBagLayout());

        jTextFieldCheckEyeReferOut.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelRefer.add(jTextFieldCheckEyeReferOut, gridBagConstraints);

        jButtonCheckEyeReferOut.setText("...");
        jButtonCheckEyeReferOut.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonCheckEyeReferOut.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonCheckEyeReferOut.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonCheckEyeReferOut.setRequestFocusEnabled(false);
        jButtonCheckEyeReferOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckEyeReferOutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelRefer.add(jButtonCheckEyeReferOut, gridBagConstraints);

        integerTextFieldCheckEyeReferOut.setColumns(5);
        integerTextFieldCheckEyeReferOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldCheckEyeReferOutKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelRefer.add(integerTextFieldCheckEyeReferOut, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelStudentDetail.add(jPanelRefer, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelStudentDetail.add(panelYesNoCheckEye, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckEyeDetail.add(jPanelStudentDetail, gridBagConstraints);

        jLabel3.setFont(defaultFont1);
        jLabel3.setText("รายละเอียด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckEyeDetail.add(jLabel3, gridBagConstraints);

        jScrollPaneStudentHealthNote1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPaneStudentHealthNote1.setMinimumSize(new java.awt.Dimension(220, 50));
        jScrollPaneStudentHealthNote1.setPreferredSize(new java.awt.Dimension(220, 50));

        jTextAreaEyeNote.setLineWrap(true);
        jTextAreaEyeNote.setWrapStyleWord(true);
        jTextAreaEyeNote.setMinimumSize(new java.awt.Dimension(220, 121));
        jScrollPaneStudentHealthNote1.setViewportView(jTextAreaEyeNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckEyeDetail.add(jScrollPaneStudentHealthNote1, gridBagConstraints);

        jPanelSchoolCheckEyeControl.setLayout(new java.awt.GridBagLayout());

        jButtonSaveCheckEye.setFont(defaultFont1);
        jButtonSaveCheckEye.setText("Save");
        jButtonSaveCheckEye.setMaximumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckEye.setMinimumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckEye.setPreferredSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckEye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveCheckEyeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        jPanelSchoolCheckEyeControl.add(jButtonSaveCheckEye, gridBagConstraints);

        jLabel4.setFont(defaultFont1);
        jLabel4.setText("หมายเหตุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 11);
        jPanelSchoolCheckEyeControl.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelSchoolCheckEyeControl.add(jTextAreaEyeRemark, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelCheckEyeDetail.add(jPanelSchoolCheckEyeControl, gridBagConstraints);

        jPanelCardCheck.add(jPanelCheckEyeDetail, "ตรวจสายตา");

        jPanelCheckEarDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("CheckEarSubDetail"));
        jPanelCheckEarDetail.setLayout(new java.awt.GridBagLayout());

        jPanelEarStudentDetail.setLayout(new java.awt.GridBagLayout());

        jLableEarStatus.setFont(defaultFont1);
        jLableEarStatus.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelEarStudentDetail.add(jLableEarStatus, gridBagConstraints);

        jLabelEarResultCheck.setFont(defaultFont1);
        jLabelEarResultCheck.setText("ResultCheck");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelEarStudentDetail.add(jLabelEarResultCheck, gridBagConstraints);

        jComboBoxEarResultCheck.setMinimumSize(new java.awt.Dimension(50, 20));
        jComboBoxEarResultCheck.setPreferredSize(new java.awt.Dimension(50, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelEarStudentDetail.add(jComboBoxEarResultCheck, gridBagConstraints);

        jLableCheckEarReferOut.setFont(defaultFont1);
        jLableCheckEarReferOut.setText("ReferOutPlace");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelEarStudentDetail.add(jLableCheckEarReferOut, gridBagConstraints);

        jPanelCheckEarRefer.setLayout(new java.awt.GridBagLayout());

        jTextFieldCheckEarReferOut.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelCheckEarRefer.add(jTextFieldCheckEarReferOut, gridBagConstraints);

        jButtonCheckEarReferOut.setText("...");
        jButtonCheckEarReferOut.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonCheckEarReferOut.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonCheckEarReferOut.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonCheckEarReferOut.setRequestFocusEnabled(false);
        jButtonCheckEarReferOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckEarReferOutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelCheckEarRefer.add(jButtonCheckEarReferOut, gridBagConstraints);

        integerTextFieldCheckEarReferOut.setColumns(5);
        integerTextFieldCheckEarReferOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldCheckEarReferOutKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelCheckEarRefer.add(integerTextFieldCheckEarReferOut, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelEarStudentDetail.add(jPanelCheckEarRefer, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelEarStudentDetail.add(panelYesNoCheckEar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckEarDetail.add(jPanelEarStudentDetail, gridBagConstraints);

        jLabel5.setFont(defaultFont1);
        jLabel5.setText("รายละเอียด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckEarDetail.add(jLabel5, gridBagConstraints);

        jScrollPaneStudentHealthNote2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPaneStudentHealthNote2.setMinimumSize(new java.awt.Dimension(220, 50));
        jScrollPaneStudentHealthNote2.setPreferredSize(new java.awt.Dimension(220, 50));

        jTextAreaEarNote.setLineWrap(true);
        jTextAreaEarNote.setWrapStyleWord(true);
        jTextAreaEarNote.setMinimumSize(new java.awt.Dimension(220, 121));
        jScrollPaneStudentHealthNote2.setViewportView(jTextAreaEarNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelCheckEarDetail.add(jScrollPaneStudentHealthNote2, gridBagConstraints);

        jPanelSchoolCheckEarControl.setLayout(new java.awt.GridBagLayout());

        jButtonSaveCheckEar.setFont(defaultFont1);
        jButtonSaveCheckEar.setText("Save");
        jButtonSaveCheckEar.setMaximumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckEar.setMinimumSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckEar.setPreferredSize(new java.awt.Dimension(67, 25));
        jButtonSaveCheckEar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveCheckEarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanelSchoolCheckEarControl.add(jButtonSaveCheckEar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelSchoolCheckEarControl.add(jTextAreaEarRemark, gridBagConstraints);

        jLabel6.setFont(defaultFont1);
        jLabel6.setText("หมายเหตุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 11, 0, 11);
        jPanelSchoolCheckEarControl.add(jLabel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 11);
        jPanelCheckEarDetail.add(jPanelSchoolCheckEarControl, gridBagConstraints);

        jPanelCardCheck.add(jPanelCheckEarDetail, "ตรวจการได้ยิน");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jPanelCardCheck, gridBagConstraints);

        jScrollPaneCheckStudentHealth.setMinimumSize(new java.awt.Dimension(23, 212));
        jScrollPaneCheckStudentHealth.setPreferredSize(new java.awt.Dimension(452, 230));

        jTableCheckStudentHealthDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableCheckStudentHealthDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableCheckStudentHealthDetailMouseReleased(evt);
            }
        });
        jScrollPaneCheckStudentHealth.setViewportView(jTableCheckStudentHealthDetail);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jScrollPaneCheckStudentHealth, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * เช็ควันที่ที่สัตว์เลี้ยงเกิดว่าเป็นวันในอนาคตหรือไม่
     * @jao 
     */
    private void checkDatePetBirth() {
        boolean checkpetborn = false;
        if (!dateComboBoxServiceDate.getText().equals("") && dateComboBoxServiceDate.getText().length() == 10 && com.pcu.utility.DateUtil.countDay(dateComboBoxServiceDate.getText(), this.theHosManage.theHosControl.theConnectionInf) == -1 && com.pcu.utility.DateUtil.isToday(com.pcu.utility.DateUtil.getDateFromText(dateComboBoxServiceDate.getText()), this.theHosManage.theHosControl.theConnectionInf) == false) {
            // ไม่สามารถกรอกวันที่เป็นวันในอนาคตได้
            theUS.setStatus(GutilPCU.getTextBundle("NoDateFuture"), UpdateStatus.WARNING);
            dateComboBoxServiceDate.setText("");
            dateComboBoxServiceDate.setText("");
        }
    }

    private void dateComboBoxServiceDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxServiceDateActionPerformed
        checkDatePetBirth();
    }//GEN-LAST:event_dateComboBoxServiceDateActionPerformed

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        ComboboxModel.initComboBox(this.jComboBoxSchoolName, this.theAllComboBoxControl.listSchoolName());
        ComboboxModel.initComboBox(this.jComboBoxClass, this.theAllComboBoxControl.listSchoolClass());
    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void jButtonSaveCheckOtherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveCheckOtherActionPerformed

        theHealthSchoolServiceControl.saveCheckOther(getCheckOther());
        updateCheckStudentList();
    }//GEN-LAST:event_jButtonSaveCheckOtherActionPerformed

    private void integerTextFieldCheckBodyReferOutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldCheckBodyReferOutKeyReleased
        if (integerTextFieldCheckBodyReferOut.getText().length() == 0 || integerTextFieldCheckBodyReferOut.getText().equals("")) {
            jTextFieldCheckBodyReferOut.setText("");
        } else if (integerTextFieldCheckBodyReferOut.getText().length() == 5) {
            Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(integerTextFieldCheckBodyReferOut.getText());
            if (theOffice != null) {
                if (!theOffice.getObjectId().equals("")) {
                    this.jTextFieldCheckBodyReferOut.setText(theOffice.name);
                    this.theCheckBody.b_visit_refer_office_id = theOffice.getObjectId();
                }
            }
        }
    }//GEN-LAST:event_integerTextFieldCheckBodyReferOutKeyReleased

    private void jButtonCheckBodyReferOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckBodyReferOutActionPerformed
        showDialogOfficeReferOut(evt);
    }//GEN-LAST:event_jButtonCheckBodyReferOutActionPerformed

    private void jButtonSaveCheckBodyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveCheckBodyActionPerformed

        this.theHealthSchoolServiceControl.saveCheckBody(getCheckBody());
        updateCheckStudentList();
    }//GEN-LAST:event_jButtonSaveCheckBodyActionPerformed

    private void integerTextFieldCheckOtherReferOutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldCheckOtherReferOutKeyReleased
        if (integerTextFieldCheckOtherReferOut.getText().length() == 0 || integerTextFieldCheckOtherReferOut.getText().equals("")) {
            jTextFieldCheckOtherReferOut.setText("");
        } else if (integerTextFieldCheckOtherReferOut.getText().length() == 5) {
            Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(integerTextFieldCheckOtherReferOut.getText());
            if (theOffice != null) {
                if (!theOffice.getObjectId().equals("")) {
                    this.jTextFieldCheckOtherReferOut.setText(theOffice.name);
                    this.theCheckOther.b_visit_refer_office_id = theOffice.getObjectId();
                }
            }
        }
    }//GEN-LAST:event_integerTextFieldCheckOtherReferOutKeyReleased

    private void jButtonCheckOtherReferOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckOtherReferOutActionPerformed
        showDialogOfficeReferOut(evt);
    }//GEN-LAST:event_jButtonCheckOtherReferOutActionPerformed

    private void jComboBoxSchoolServiceTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSchoolServiceTypeActionPerformed
        if (GutilPCU.getGuiData(jComboBoxSchoolServiceType).equals("20")) {
            jTextFieldServiceOther.setEditable(true);
        } else {
            jTextFieldServiceOther.setText("");
            jTextFieldServiceOther.setEditable(false);
        }
    }//GEN-LAST:event_jComboBoxSchoolServiceTypeActionPerformed

    private void jButtonSaveCheckLiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveCheckLiceActionPerformed

        this.theHealthSchoolServiceControl.saveCheckLice(getCheckLice());
        updateCheckStudentList();
    }//GEN-LAST:event_jButtonSaveCheckLiceActionPerformed

    private void jButtonCheckLiceReferOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckLiceReferOutActionPerformed
        showDialogOfficeReferOut(evt);
    }//GEN-LAST:event_jButtonCheckLiceReferOutActionPerformed

    private void integerTextFieldCheckLiceReferOutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldCheckLiceReferOutKeyReleased
        if (integerTextFieldCheckLiceReferOut.getText().length() == 0 || integerTextFieldCheckLiceReferOut.getText().equals("")) {
            jTextFieldCheckLiceReferOut.setText("");
        } else if (integerTextFieldCheckLiceReferOut.getText().length() == 5) {
            Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(integerTextFieldCheckLiceReferOut.getText());
            if (theOffice != null) {
                if (!theOffice.getObjectId().equals("")) {
                    this.jTextFieldCheckLiceReferOut.setText(theOffice.name);
                    this.theCheckLice.b_visit_refer_office_id = theOffice.getObjectId();
                }
            }
        }
    }//GEN-LAST:event_integerTextFieldCheckLiceReferOutKeyReleased

    private void integerTextFieldCheckThalassemiaReferOutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldCheckThalassemiaReferOutKeyReleased
        if (integerTextFieldCheckThalassemiaReferOut.getText().length() == 0 || integerTextFieldCheckThalassemiaReferOut.getText().equals("")) {
            jTextFieldCheckThalassemiaReferOut.setText("");
        } else if (integerTextFieldCheckThalassemiaReferOut.getText().length() == 5) {
            Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(integerTextFieldCheckThalassemiaReferOut.getText());
            if (theOffice != null) {
                if (!theOffice.getObjectId().equals("")) {
                    this.jTextFieldCheckThalassemiaReferOut.setText(theOffice.name);
                    this.theCheckThalassemia.b_visit_refer_office_id = theOffice.getObjectId();
                }
            }
        }
    }//GEN-LAST:event_integerTextFieldCheckThalassemiaReferOutKeyReleased

    private void jButtonSaveCheckThalassemiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveCheckThalassemiaActionPerformed

        this.theHealthSchoolServiceControl.saveCheckThalassemia(getCheckThalassemia());
        updateCheckStudentList();
    }//GEN-LAST:event_jButtonSaveCheckThalassemiaActionPerformed

    private void jButtonCheckThalassemiaReferOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckThalassemiaReferOutActionPerformed
        showDialogOfficeReferOut(evt);
    }//GEN-LAST:event_jButtonCheckThalassemiaReferOutActionPerformed

    private void jButtonSaveCheckWormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveCheckWormActionPerformed

        this.theHealthSchoolServiceControl.saveCheckWorm(getCheckWorm());
        updateCheckStudentList();
    }//GEN-LAST:event_jButtonSaveCheckWormActionPerformed

    private void jButtonCheckWormReferOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckWormReferOutActionPerformed
        showDialogOfficeReferOut(evt);
    }//GEN-LAST:event_jButtonCheckWormReferOutActionPerformed

    private void integerTextFieldCheckWormReferOutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldCheckWormReferOutKeyReleased
        if (integerTextFieldCheckWormReferOut.getText().length() == 0 || integerTextFieldCheckWormReferOut.getText().equals("")) {
            jTextFieldCheckWormReferOut.setText("");
        } else if (integerTextFieldCheckWormReferOut.getText().length() == 5) {
            Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(integerTextFieldCheckWormReferOut.getText());
            if (theOffice != null) {
                if (!theOffice.getObjectId().equals("")) {
                    this.jTextFieldCheckWormReferOut.setText(theOffice.name);
                    this.theCheckWorm.b_visit_refer_office_id = theOffice.getObjectId();
                }
            }
        }
    }//GEN-LAST:event_integerTextFieldCheckWormReferOutKeyReleased

    private void jButtonSaveCheckStudentDentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveCheckStudentDentalActionPerformed

        this.theHealthSchoolServiceControl.saveCheckStudentDental(getCheckStudentDental());
        updateCheckStudentList();
    }//GEN-LAST:event_jButtonSaveCheckStudentDentalActionPerformed

    private void integerTextFieldCheckStudentDentalReferOutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldCheckStudentDentalReferOutKeyReleased
        if (integerTextFieldCheckStudentDentalReferOut.getText().length() == 0 || integerTextFieldCheckStudentDentalReferOut.getText().equals("")) {
            jTextFieldCheckStudentDentalReferOut.setText("");
        } else if (integerTextFieldCheckStudentDentalReferOut.getText().length() == 5) {
            Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(integerTextFieldCheckStudentDentalReferOut.getText());
            if (theOffice != null) {
                if (!theOffice.getObjectId().equals("")) {
                    this.jTextFieldCheckStudentDentalReferOut.setText(theOffice.name);
                    this.theCheckStudentDental.b_visit_refer_office_id = theOffice.getObjectId();
                }
            }
        }
    }//GEN-LAST:event_integerTextFieldCheckStudentDentalReferOutKeyReleased

    private void jButtonCheckStudentDentalReferOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckStudentDentalReferOutActionPerformed
        showDialogOfficeReferOut(evt);
    }//GEN-LAST:event_jButtonCheckStudentDentalReferOutActionPerformed

    private void jButtonSaveCheckFeBloodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveCheckFeBloodActionPerformed

        this.theHealthSchoolServiceControl.saveCheckFeBlood(getCheckFeBlood());
        updateCheckStudentList();
    }//GEN-LAST:event_jButtonSaveCheckFeBloodActionPerformed

    private void integerTextFieldCheckFeBloodReferOutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldCheckFeBloodReferOutKeyReleased
        if (integerTextFieldCheckFeBloodReferOut.getText().length() == 0 || integerTextFieldCheckFeBloodReferOut.getText().equals("")) {
            jTextFieldCheckFeBloodReferOut.setText("");
        } else if (integerTextFieldCheckFeBloodReferOut.getText().length() == 5) {
            Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(integerTextFieldCheckFeBloodReferOut.getText());
            if (theOffice != null) {
                if (!theOffice.getObjectId().equals("")) {
                    this.jTextFieldCheckFeBloodReferOut.setText(theOffice.name);
                    this.theCheckFeBlood.b_visit_refer_office_id = theOffice.getObjectId();
                }
            }
        }
    }//GEN-LAST:event_integerTextFieldCheckFeBloodReferOutKeyReleased

    private void jButtonCheckFeBloodReferOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckFeBloodReferOutActionPerformed
        showDialogOfficeReferOut(evt);
    }//GEN-LAST:event_jButtonCheckFeBloodReferOutActionPerformed

    private void jButtonSaveCheckGoiterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveCheckGoiterActionPerformed

        this.theHealthSchoolServiceControl.saveCheckGoiter(getCheckGoiter());
        updateCheckStudentList();
    }//GEN-LAST:event_jButtonSaveCheckGoiterActionPerformed

    private void integerTextFieldCheckGoiterReferOutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldCheckGoiterReferOutKeyReleased
        if (integerTextFieldCheckGoiterReferOut.getText().length() == 0 || integerTextFieldCheckGoiterReferOut.getText().equals("")) {
            jTextFieldCheckGoiterReferOut.setText("");
        } else if (integerTextFieldCheckGoiterReferOut.getText().length() == 5) {
            Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(integerTextFieldCheckGoiterReferOut.getText());
            if (theOffice != null) {
                if (!theOffice.getObjectId().equals("")) {
                    this.jTextFieldCheckGoiterReferOut.setText(theOffice.name);
                    this.theCheckGoiter.b_visit_refer_office_id = theOffice.getObjectId();
                }
            }
        }
    }//GEN-LAST:event_integerTextFieldCheckGoiterReferOutKeyReleased

    private void jButtonCheckGoiterReferOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckGoiterReferOutActionPerformed
        showDialogOfficeReferOut(evt);
    }//GEN-LAST:event_jButtonCheckGoiterReferOutActionPerformed

    private void jButtonSaveCheckStudentHealthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveCheckStudentHealthActionPerformed

        this.theHealthSchoolServiceControl.saveCheckStudentHealth(getCheckStudentHealth());
        updateCheckStudentList();
    }//GEN-LAST:event_jButtonSaveCheckStudentHealthActionPerformed

    private void jTableCheckStudentHealthDetailMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCheckStudentHealthDetailMouseReleased
        int row = jTableCheckStudentHealthDetail.getSelectedRow();
        CardLayout cl = (CardLayout) this.jPanelCardCheck.getLayout();
        cl.show(jPanelCardCheck,HealthSchoolServiceControl.CHECK_LIST[row]);
    }//GEN-LAST:event_jTableCheckStudentHealthDetailMouseReleased

    private void integerTextFieldCheckStudentHealthReferOutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldCheckStudentHealthReferOutKeyReleased
        if (integerTextFieldCheckStudentHealthReferOut.getText().length() == 0 || integerTextFieldCheckStudentHealthReferOut.getText().equals("")) {
            jTextFieldCheckStudentHealthReferOut.setText("");
        } else if (integerTextFieldCheckStudentHealthReferOut.getText().length() == 5) {
            Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(integerTextFieldCheckStudentHealthReferOut.getText());
            if (theOffice != null) {
                if (!theOffice.getObjectId().equals("")) {
                    this.jTextFieldCheckStudentHealthReferOut.setText(theOffice.name);
                    this.theCheckStudentHealth.b_visit_refer_office_id = theOffice.getObjectId();
                }
            }
        }
    }//GEN-LAST:event_integerTextFieldCheckStudentHealthReferOutKeyReleased

    private void jButtonCheckStudentHealthReferOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckStudentHealthReferOutActionPerformed
        showDialogOfficeReferOut(evt);
    }//GEN-LAST:event_jButtonCheckStudentHealthReferOutActionPerformed

    private void jButtonSaveCheckNutritionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveCheckNutritionActionPerformed

        this.theHealthSchoolServiceControl.saveCheckNutrition(getCheckNutrition());
        updateCheckStudentList();
    }//GEN-LAST:event_jButtonSaveCheckNutritionActionPerformed

    private void integerTextFieldCheckNutritionReferOutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldCheckNutritionReferOutKeyReleased
        if (integerTextFieldCheckNutritionReferOut.getText().length() == 0 || integerTextFieldCheckNutritionReferOut.getText().equals("")) {
            jTextFieldCheckNutritionReferOut.setText("");
        } else if (integerTextFieldCheckNutritionReferOut.getText().length() == 5) {
            Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(integerTextFieldCheckNutritionReferOut.getText());
            if (theOffice != null) {
                if (!theOffice.getObjectId().equals("")) {
                    this.jTextFieldCheckNutritionReferOut.setText(theOffice.name);
                    this.theCheckNutrition.b_visit_refer_office_id = theOffice.getObjectId();
                }
            }
        }
    }//GEN-LAST:event_integerTextFieldCheckNutritionReferOutKeyReleased

    private void jButtonCheckNutritionReferOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckNutritionReferOutActionPerformed
        showDialogOfficeReferOut(evt);
    }//GEN-LAST:event_jButtonCheckNutritionReferOutActionPerformed

    private void integerTextFieldCheckEarReferOutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldCheckEarReferOutKeyReleased
        if (integerTextFieldCheckEarReferOut.getText().length() == 0 || integerTextFieldCheckEarReferOut.getText().equals("")) {
            jTextFieldCheckEyeReferOut.setText("");
        } else if (integerTextFieldCheckEarReferOut.getText().length() == 5) {
            Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(integerTextFieldCheckEarReferOut.getText());
            if (theOffice != null) {
                if (!theOffice.getObjectId().equals("")) {
                    this.jTextFieldCheckEarReferOut.setText(theOffice.name);
                    this.theCheckEars.b_visit_refer_office_id = theOffice.getObjectId();
                }
            }
        }
    }//GEN-LAST:event_integerTextFieldCheckEarReferOutKeyReleased

    private void integerTextFieldCheckEyeReferOutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldCheckEyeReferOutKeyReleased
        if (integerTextFieldCheckEyeReferOut.getText().length() == 0 || integerTextFieldCheckEyeReferOut.getText().equals("")) {
            jTextFieldCheckEyeReferOut.setText("");
        } else if (integerTextFieldCheckEyeReferOut.getText().length() == 5) {
            Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(integerTextFieldCheckEyeReferOut.getText());
            if (theOffice != null) {
                if (!theOffice.getObjectId().equals("")) {
                    this.jTextFieldCheckEyeReferOut.setText(theOffice.name);
                    this.theCheckEyes.b_visit_refer_office_id = theOffice.getObjectId();
                }
            }
        }
    }//GEN-LAST:event_integerTextFieldCheckEyeReferOutKeyReleased

    private void jButtonCheckEarReferOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckEarReferOutActionPerformed
        showDialogOfficeReferOut(evt);
    }//GEN-LAST:event_jButtonCheckEarReferOutActionPerformed

    private void jButtonCheckEyeReferOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckEyeReferOutActionPerformed
        showDialogOfficeReferOut(evt);
    }//GEN-LAST:event_jButtonCheckEyeReferOutActionPerformed

    private void jButtonSaveCheckEarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveCheckEarActionPerformed

        this.theHealthSchoolServiceControl.saveCheckEars(getCheckEars());
        updateCheckStudentList();
    }//GEN-LAST:event_jButtonSaveCheckEarActionPerformed

    private void jTextFieldSearchSchoolKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchSchoolKeyReleased
        searchSchool();
    }//GEN-LAST:event_jTextFieldSearchSchoolKeyReleased

    private void jComboBoxPrefixStuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPrefixStuActionPerformed
        thePrefix = this.theHealthSchoolServiceControl.selectPrefixByPK(GutilPCU.getGuiData(jComboBoxPrefixStu));
        if (thePrefix != null) {
            GutilPCU.setGuiData(jComboBoxStuSex, thePrefix.sex);
        }
    }//GEN-LAST:event_jComboBoxPrefixStuActionPerformed

    private void jComboBoxResultCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxResultCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxResultCheckActionPerformed

    private void jTableStudentServiceMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableStudentServiceMouseReleased
        int row = jTableStudentService.getSelectedRow();
        if (row == -1) {
            return;
        }
        setStudent((Student) this.vStudent.get(row));
        setSchoolFromEnable(true);
        setEnableStudentService(true);
        CardLayout cl = (CardLayout) this.jPanelCard.getLayout();
        cl.show(jPanelCard, "cardStudent");
    }//GEN-LAST:event_jTableStudentServiceMouseReleased

    private void jButtonDelStuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelStuActionPerformed
        int row = this.jTableStudentService.getSelectedRow();
        if (row == -1) {
            return;
        }
        if (!theUS.confirmBox(GutilPCU.getTextBundle("ConfirmDeleteStudent"), UpdateStatus.WARNING)) {
            return;
        }

        this.theHealthSchoolServiceControl.deleteStudent((Student) vStudent.get(row));
        deleteStudentService(theStudent);
        updateStudentList();
    }//GEN-LAST:event_jButtonDelStuActionPerformed

    private void jButtonSaveStuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveStuActionPerformed
        if (isEmptyName()) {
            theUS.setStatus(GutilPCU.getTextBundle("EmptyName"), UpdateStatus.WARNING);
            return;
        }
        if (!pIDPanel.getText().equals("") && pIDPanel.getText().length() != 13) {
            theUS.setStatus(GutilPCU.getTextBundle("Pidlength"), UpdateStatus.WARNING);
            return;
        }
        Constant.println("-----++++" + pIDPanel.getText() + pIDPanel.getText().length() + "---------");
        if (checkSameStudentIdAndPid()) {
            theUS.setStatus(GutilPCU.getTextBundle("เลขบัตรซ้ำ"), UpdateStatus.WARNING);
            return;
        }
        //หลังจากการบันทึกนักเรียน โปรแกรมจะ return แถวที่โปรแกรมจะต้อง selection ให้
        getStudent();
        this.theHealthSchoolServiceControl.saveStudent(theStudent, jTextFieldAge.getText());
        updateStudentList();
    }//GEN-LAST:event_jButtonSaveStuActionPerformed

    private void jButtonAddStuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddStuActionPerformed
        if (!("").equals(jLabelDataSchoolClass.getText())) {
            theStudent = new Student();
            clearGUIStudentDetail();
            jTableStudentService.clearSelection();
            setStudentFromEnable(true);
            CardLayout cl = (CardLayout) this.jPanelCard.getLayout();
            cl.show(jPanelCard, "cardStudent");
        } else {
            theUS.setStatus(GutilPCU.getTextBundle("PleaseSelectSchool"), UpdateStatus.WARNING);
        }
    }//GEN-LAST:event_jButtonAddStuActionPerformed

    private void jButtonSaveCheckEyeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveCheckEyeActionPerformed

        this.theHealthSchoolServiceControl.saveCheckEyes(getCheckEyes());
        updateCheckStudentList();
    }//GEN-LAST:event_jButtonSaveCheckEyeActionPerformed

    private void jComboBoxSchoolNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSchoolNameActionPerformed
        String t_health_school_id = GutilPCU.getGuiData(jComboBoxSchoolName);
        if (!("").equals(t_health_school_id)) {
            Vector vSchoolHistory = this.theVillageControl.listSchoolHistoryBySchoolIdDesc(t_health_school_id);
            if (vSchoolHistory != null) {
                SchoolHistory theSchoolHistory = new SchoolHistory();
                theSchoolHistory = (SchoolHistory) vSchoolHistory.elementAt(0);
                Vector vSchoolClass = this.theHealthSchoolServiceControl.selectSchoolClassByPK(theSchoolHistory.school_history_max_class);
                if (vSchoolClass != null) {
                    SchoolClass theSchoolClass = (SchoolClass) vSchoolClass.elementAt(0);
                    Vector vLowerSchoolClass = this.theHealthSchoolServiceControl.selectLowerSchoolClassBySchoolClassNumber(theSchoolClass.number);
                    if (vLowerSchoolClass != null) {
                        ComboboxModel.initComboBox(this.jComboBoxClass, vLowerSchoolClass);
                    }
                } else {
                    ComboboxModel.initComboBox(this.jComboBoxClass, this.theAllComboBoxControl.listSchoolClass());
                }
            } else {
                ComboboxModel.initComboBox(this.jComboBoxClass, this.theAllComboBoxControl.listSchoolClass());
            }

        } else {
            ComboboxModel.initComboBox(this.jComboBoxClass, this.theAllComboBoxControl.listSchoolClass());
        }

    }//GEN-LAST:event_jComboBoxSchoolNameActionPerformed

    private void jButtonDelSchoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelSchoolActionPerformed
        if (theUS.confirmBox(GutilPCU.getTextBundle("ConfirmDeleteHealthSchool"), UpdateStatus.WARNING)) {
            deleteVisitSchool();
        }
    }//GEN-LAST:event_jButtonDelSchoolActionPerformed

    private void jTableSchoolDetailMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSchoolDetailMouseReleased
        int row = jTableSchoolDetail.getSelectedRow();
        if (row == -1) {
            return;
        }

        VisitSchool visitSchool = (VisitSchool) vVisitSchool.get(row);
        setVisitSchool(visitSchool);
        jLabelDataSchoolClass.setText(theAllComboBoxControl.getValueOfSchoolClass(visitSchool.b_school_class_id));
        jLabelDataSchoolRoom.setText(visitSchool.visit_school_room);
        jLabelDataSchoolService.setText(ComboboxModel.getDescriptionComboBox(jComboBoxSchoolServiceType, visitSchool.f_health_school_service_type_id));
        checkAutoAddStudent();
        setSchoolFromEnable(true);
        updateStudentList();
        setStudentFromEnable(true);
        setEnableStudentService(true);

        CardLayout cl = (CardLayout) this.jPanelCard.getLayout();
        cl.show(jPanelCard, "cardSchool");
    }//GEN-LAST:event_jTableSchoolDetailMouseReleased

    private void jButtonSaveSchoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveSchoolActionPerformed
        if (checkSameVisitSchool()) {
            theUS.setStatus(GutilPCU.getTextBundle("SameVisitSchool"), UpdateStatus.WARNING);
            return;
        }
        if (checkSameVisitSchool()) {
            theUS.setStatus(GutilPCU.getTextBundle("SameVisitSchool"), UpdateStatus.WARNING);
            return;
        }
        saveVisitSchool();
        int row = jTableSchoolDetail.getSelectedRow();
        if (row != -1) {
            setVisitSchool((VisitSchool) vVisitSchool.get(row));
            setSchoolFromEnable(true);
            VisitSchool visitSchool = (VisitSchool) vVisitSchool.get(row);
            jLabelDataSchoolClass.setText(theAllComboBoxControl.getValueOfSchoolClass(visitSchool.b_school_class_id));
            jLabelDataSchoolRoom.setText(visitSchool.visit_school_room);
            jLabelDataSchoolService.setText(ComboboxModel.getDescriptionComboBox(jComboBoxSchoolServiceType, visitSchool.f_health_school_service_type_id));

        }
        checkAutoAddStudent();
        updateStudentList();

        //เพิ่ม code สำหรับให้ประเภทของงานอนามัยขึ้นตรงกับที่ผู้ใช้บันทึก
        row = jTableSchoolDetail.getSelectedRow();
        if (row != -1) {
            setVisitSchool((VisitSchool) vVisitSchool.get(row));
            setSchoolFromEnable(true);
            VisitSchool visitSchool = (VisitSchool) vVisitSchool.get(row);
            jLabelDataSchoolClass.setText(theAllComboBoxControl.getValueOfSchoolClass(visitSchool.b_school_class_id));
            jLabelDataSchoolRoom.setText(visitSchool.visit_school_room);
            jLabelDataSchoolService.setText(ComboboxModel.getDescriptionComboBox(jComboBoxSchoolServiceType, visitSchool.f_health_school_service_type_id));

        }
    }//GEN-LAST:event_jButtonSaveSchoolActionPerformed

    private void jButtonAddSchoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddSchoolActionPerformed
        theVisitSchool = new VisitSchool();
        clearGUISchoolDetail();
        jTableSchoolDetail.clearSelection();
        setSchoolFromEnable(true);
        CardLayout cl = (CardLayout) this.jPanelCard.getLayout();
        cl.show(jPanelCard, "cardSchool");
    }//GEN-LAST:event_jButtonAddSchoolActionPerformed

    private void jTableSchoolListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSchoolListMouseReleased
        int row = jTableSchoolList.getSelectedRow();
        if (row == -1)
            return;

        theVisitSchool.t_health_school_id = getDataFromObjectSchool(row).getObjectId();
        ComboboxModel.setCodeComboBox(this.jComboBoxSchoolName, theVisitSchool.t_health_school_id);
        updateVisitSchoolList();
        clearGUISchoolDetail();
        //แสดงบนมุมซ้ายบน
        jLabelDataSchoolName.setText((String) jTableSchoolList.getValueAt(row, 1));
        jLabelDataSchoolClass.setText("");
        jLabelDataSchoolRoom.setText("");
        jLabelDataSchoolService.setText("");

        setTableListStudent(null);
        clearGUIStudentDetail();
        setEnableStudentService(false);
        setStudentFromEnable(false);
        setShowDataCheckStudentHealth(false);
        setShowDataCheckEye(false);
        setShowDataCheckEar(false);
        setShowDataNutrition(false);
        setShowDataGoiter(false);
        setShowDataFeBlood(false);
        setShowDataStudentDental(false);
        setShowDataCheckWorm(false);
        setShowDataThalassemia(false);
        setShowDataCheckLice(false);
        setShowDataCheckBody(false);
        setShowDataCheckOther(false);
    }//GEN-LAST:event_jTableSchoolListMouseReleased

    private void jTextFieldSearchSchoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchSchoolActionPerformed
        searchSchool();
    }//GEN-LAST:event_jTextFieldSearchSchoolActionPerformed

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        searchSchool();
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jTextFieldStuNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldStuNameActionPerformed
    }//GEN-LAST:event_jTextFieldStuNameActionPerformed

    private void jButtonSearchFamilyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchFamilyActionPerformed
        String family_id = theHosDialog.showSearchFamily(this.theHosManage);
        if (family_id == null) {
            return;
        }
        Family fam = theHomeControl.readFamilyById(family_id);
        if (fam == null) {
            return;
        }
        ComboboxModel.setCodeComboBox(this.jComboBoxPrefixStu, fam.f_prefix_id);
        ComboboxModel.setCodeComboBox(this.jComboBoxStuSex, fam.f_sex_id);
        this.jTextFieldStuName.setText(fam.patient_name);
        this.jTextFieldStuSurName.setText(fam.patient_last_name);
        this.jTextFieldStuNumber.setText("");
        this.pIDPanel.setText(fam.pid);
        this.jTextAreaStudentNote.setText("");
        this.theStudent = new Student();
        theStudent.setObjectId(family_id);
//        setTablePatient() ;
}//GEN-LAST:event_jButtonSearchFamilyActionPerformed

    private void promptSaveStudent() {
        if (!("").equals(jLabelDataSchoolClass.getText())) {
            Constant.println("----------------- Prompt Save Student");
            theStudent = new Student();
            clearGUIStudentDetail();
            jTableStudentService.clearSelection();
            setStudentFromEnable(true);
            jButtonAddStu.setEnabled(false);
            jButtonDelStu.setEnabled(false);
            Constant.println("----------------- After set jButtonAddStu.setEnabled(false)");
        }
        jButtonAddStu.setEnabled(false);
        jButtonDelStu.setEnabled(false);
        Constant.println("----------------- 2 After set jButtonAddStu.setEnabled(false)");
    }

    private void promptSaveVisitSchool() {
        Constant.println("In method promptSaveVisitSchool == null");
        theVisitSchool = new VisitSchool();
        clearGUISchoolDetail();
        jTableSchoolDetail.clearSelection();
        setSchoolFromEnable(true);
    }

    private void setListenner() {


        panelYesNoStudentHealthStatus.getRadioButtonYes().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableStudentHealthResultCheck();
            }
        });

        panelYesNoStudentHealthStatus.getRadioButtonNo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableStudentHealthResultCheck();
            }
        });

        panelYesNoCheckEye.getRadioButtonYes().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckEyeResultCheck();
            }
        });

        panelYesNoCheckEye.getRadioButtonNo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckEyeResultCheck();
            }
        });

        panelYesNoCheckLice.getRadioButtonYes().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckLiceResultCheck();
            }
        });

        panelYesNoCheckLice.getRadioButtonNo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckLiceResultCheck();
            }
        });

        panelYesNoCheckEar.getRadioButtonYes().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckEarResultCheck();
            }
        });

        panelYesNoCheckEar.getRadioButtonNo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckEarResultCheck();
            }
        });


        panelYesNoCheckNutrition.getRadioButtonYes().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckNutritionResultCheck();
            }
        });

        panelYesNoCheckNutrition.getRadioButtonNo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckNutritionResultCheck();
            }
        });


        panelYesNoCheckGoiter.getRadioButtonYes().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckGoiterResultCheck();
            }
        });

        panelYesNoCheckGoiter.getRadioButtonNo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckGoiterResultCheck();
            }
        });


        panelYesNoCheckFeBlood.getRadioButtonYes().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckFeBloodResultCheck();
            }
        });

        panelYesNoCheckFeBlood.getRadioButtonNo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckFeBloodResultCheck();
            }
        });

        panelYesNoCheckDental.getRadioButtonYes().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckDentalResultCheck();
            }
        });

        panelYesNoCheckDental.getRadioButtonNo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckDentalResultCheck();
            }
        });


        panelYesNoCheckWorm.getRadioButtonYes().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckWormResultCheck();
            }
        });

        panelYesNoCheckWorm.getRadioButtonNo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckWormResultCheck();
            }
        });


        panelYesNoCheckThalassemia.getRadioButtonYes().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckThalassemiaResultCheck();
            }
        });

        panelYesNoCheckThalassemia.getRadioButtonNo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckThalassemiaResultCheck();
            }
        });


        panelYesNoCheckBody.getRadioButtonYes().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckBodyResultCheck();
            }
        });

        panelYesNoCheckBody.getRadioButtonNo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckBodyResultCheck();
            }
        });


        panelYesNoCheckOther.getRadioButtonYes().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckOtherResultCheck();
            }
        });

        panelYesNoCheckOther.getRadioButtonNo().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEnableCheckOtherResultCheck();
            }
        });

        setEnableStudentHealthResultCheck();
        setEnableCheckEyeResultCheck();
        setEnableCheckLiceResultCheck();
        setEnableCheckEarResultCheck();
        setEnableCheckNutritionResultCheck();
        setEnableCheckGoiterResultCheck();
        setEnableCheckFeBloodResultCheck();
        setEnableCheckDentalResultCheck();
        setEnableCheckWormResultCheck();
        setEnableCheckThalassemiaResultCheck();
        setEnableCheckBodyResultCheck();
        setEnableCheckOtherResultCheck();
    }

    public void setEnableStudentHealthResultCheck() {
        if (("0").equals(panelYesNoStudentHealthStatus.getStringSelect())) {
            panelYesNoStudentHealthResultCheck.setEnableRadioButton(false);
            doubleTextFieldStudentHealthTall.setEnabled(false);
            doubleTextFieldStudentHealthWeight.setEnabled(false);
            doubleTextFieldStudentHealthTall.setText("");
            doubleTextFieldStudentHealthWeight.setText("");
        } else {
            panelYesNoStudentHealthResultCheck.setEnableRadioButton(true);
            doubleTextFieldStudentHealthTall.setEnabled(true);
            doubleTextFieldStudentHealthWeight.setEnabled(true);
        }
    }

    public void setEnableCheckEyeResultCheck() {
        if (("0").equals(panelYesNoCheckEye.getStringSelect())) {
            jComboBoxResultCheck.setEnabled(false);
        } else {
            jComboBoxResultCheck.setEnabled(true);
        }
    }

    public void setEnableCheckNutritionResultCheck() {
        if (("0").equals(panelYesNoCheckNutrition.getStringSelect())) {
            jComboBoxNutritionResultCheck.setEnabled(false);
        } else {
            jComboBoxNutritionResultCheck.setEnabled(true);
        }
    }

    public void setEnableCheckEarResultCheck() {
        if (("0").equals(panelYesNoCheckEar.getStringSelect())) {
            jComboBoxEarResultCheck.setEnabled(false);
        } else {
            jComboBoxEarResultCheck.setEnabled(true);
        }
    }

    public void setEnableCheckLiceResultCheck() {
        if (("0").equals(panelYesNoCheckLice.getStringSelect())) {
            panelYesNoCheckLiceResultCheck.setEnableRadioButton(false);
        } else {
            panelYesNoCheckLiceResultCheck.setEnableRadioButton(true);
        }
    }

    public void setEnableCheckGoiterResultCheck() {
        if (("0").equals(panelYesNoCheckGoiter.getStringSelect())) {
            panelYesNoGoiterResult.setEnableRadioButton(false);
        } else {
            panelYesNoGoiterResult.setEnableRadioButton(true);
        }
    }

    public void setEnableCheckFeBloodResultCheck() {
        if (("0").equals(panelYesNoCheckFeBlood.getStringSelect())) {
            panelYesNoFeBloodResult.setEnableRadioButton(false);
        } else {
            panelYesNoFeBloodResult.setEnableRadioButton(true);
        }
    }

    public void setEnableCheckDentalResultCheck() {
        if (("0").equals(panelYesNoCheckDental.getStringSelect())) {
            jComboBoxStudentDentalResultCheck.setEnabled(false);
            integerTextFieldRealTooth.setText("");
            integerTextFieldBadRealTooth.setText("");
            integerTextFieldMilkTooth.setText("");
            integerTextFieldBadMilkTooth.setText("");
            integerTextFieldRealTooth.setEnabled(false);
            integerTextFieldBadRealTooth.setEnabled(false);
            integerTextFieldMilkTooth.setEnabled(false);
            integerTextFieldBadMilkTooth.setEnabled(false);
        } else {
            jComboBoxStudentDentalResultCheck.setEnabled(true);
            integerTextFieldRealTooth.setEnabled(true);
            integerTextFieldBadRealTooth.setEnabled(true);
            integerTextFieldMilkTooth.setEnabled(true);
            integerTextFieldBadMilkTooth.setEnabled(true);
        }
    }

    public void setEnableCheckWormResultCheck() {
        if (("0").equals(panelYesNoCheckWorm.getStringSelect())) {
            panelYesNoCheckWormResult.setEnableRadioButton(false);
        } else {
            panelYesNoCheckWormResult.setEnableRadioButton(true);
        }
    }

    public void setEnableCheckThalassemiaResultCheck() {
        if (("0").equals(panelYesNoCheckThalassemia.getStringSelect())) {
            panelYesNoCheckThalassemiaResult.setEnableRadioButton(false);
        } else {
            panelYesNoCheckThalassemiaResult.setEnableRadioButton(true);
        }
    }

    public void setEnableCheckBodyResultCheck() {
        if (("0").equals(panelYesNoCheckBody.getStringSelect())) {
            panelYesNoCheckBodyResult.setEnableRadioButton(false);
        } else {
            panelYesNoCheckBodyResult.setEnableRadioButton(true);
        }
    }

    public void setEnableCheckOtherResultCheck() {
        if (("0").equals(panelYesNoCheckOther.getStringSelect())) {
            jTextAreaOtherNote.setText("");
            jTextAreaOtherNote.setEnabled(false);
        } else {
            jTextAreaOtherNote.setEnabled(true);
        }
    }

    private void setEnableStudentService(boolean flag) {
        jButtonAddStu.setEnabled(flag);
        jButtonDelStu.setEnabled(flag);
        jButtonSaveStu.setEnabled(flag);
        pIDPanel.setEnabled(flag);
    }

    private boolean isEmptyName() {
        if (jTextFieldStuName.getText().trim().equals("") || jTextFieldStuSurName.getText().trim().equals("")) {
            return true;
        }
        return false;
    }

    private boolean checkSameStudentIdAndPid() {
        boolean isSameStudentIDAndPID = false;
        String pId = this.pIDPanel.getText();
        String stuNumber = this.jTextFieldStuNumber.getText();
        if (this.theVisitSchool != null) {
            String visitSchoolID = this.theVisitSchool.getObjectId();
            Vector vStudent = theHealthSchoolServiceControl.listStudentByVisitSchoolID(visitSchoolID);
            if (vStudent != null) {
                for (int i = 0, size = vStudent.size(); i < size; i++) {
                    Student theStudent = (Student) vStudent.get(i);
                    if (stuNumber.equals(theStudent.student_number) && !("").equals(stuNumber) && !selectedStudentNumber.equals(stuNumber)) {
                        theUS.setStatus(GutilPCU.getTextBundle("SameStudentNo"), UpdateStatus.WARNING);
                        isSameStudentIDAndPID = true;
                        break;
                    }
                    if (pId.equals(theStudent.student_pid) && !("").equals(pId) && !selectedStudentPid.equals(pId)) {
                        theUS.setStatus(GutilPCU.getTextBundle("SameStudentPid"), UpdateStatus.WARNING);
                        isSameStudentIDAndPID = true;
                        break;
                    }
                    if (!theStudent.student_pid.equals("") && theStudent.student_pid.length() != 13) {
                        theUS.setStatus(GutilPCU.getTextBundle("Pidlength"), UpdateStatus.WARNING);
                        isSameStudentIDAndPID = true;
                        break;
                    }
                }
            }
        }
        return isSameStudentIDAndPID;

    }

    private boolean checkSameVisitSchool() {
        String school_id = ComboboxModel.getCodeComboBox(jComboBoxSchoolName);
        String school_class = ComboboxModel.getCodeComboBox(jComboBoxClass);
        String school_room = this.jTextFieldRoom.getText();
        String school_term = this.jTextFieldSchoolYear.getText();
        String school_service = ComboboxModel.getCodeComboBox(jComboBoxSchoolServiceType);
        String school_other_service = this.jTextFieldServiceOther.getText();
        String school_service_date = this.dateComboBoxServiceDate.getText();
        Vector vSameVisitSchool = this.theHealthSchoolServiceControl.selectVisitSchoolBySchoolClassRoomTermService(school_id, school_class, school_room, school_term, school_service, school_other_service, school_service_date);
        if (vSameVisitSchool != null) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * ค้นนักเรียนมาแสดง
     */
    private void checkAutoAddStudent() {
    }

    public void initDatas() {
        ComboboxModel.initComboBox(this.jComboBoxStuSex, this.theAllComboBoxControl.listSex());
        ComboboxModel.initComboBox(this.jComboBoxPrefixStu, this.theAllComboBoxControl.listPrefix());
        ComboboxModel.initComboBox(this.jComboBoxSchoolName, this.theAllComboBoxControl.listSchoolName());
        ComboboxModel.initComboBox(this.jComboBoxClass, this.theAllComboBoxControl.listSchoolClass());
        ComboboxModel.initComboBox(this.jComboBoxSchoolServiceType, this.theAllComboBoxControl.listSchoolServiceType());
        ComboboxModel.initComboBox(this.jComboBoxResultCheck, this.theAllComboBoxControl.listCheckResult());
        ComboboxModel.initComboBox(this.jComboBoxEarResultCheck, this.theAllComboBoxControl.listCheckResult());
        ComboboxModel.initComboBox(this.jComboBoxNutritionResultCheck, this.theAllComboBoxControl.listNutritionType());
        ComboboxModel.initComboBox(this.jComboBoxStudentDentalResultCheck, this.theAllComboBoxControl.listGumLevel());


        panelYesNoCheckEye.setTextYesNo(GutilPCU.setLanguage("ตรวจ"), GutilPCU.setLanguage("ไม่ตรวจ"));
        panelYesNoCheckEye.setSelected("1");

        panelYesNoCheckEar.setTextYesNo(GutilPCU.setLanguage("ตรวจ"), GutilPCU.setLanguage("ไม่ตรวจ"));
        panelYesNoCheckEar.setSelected("1");

        panelYesNoCheckNutrition.setTextYesNo(GutilPCU.setLanguage("ตรวจ"), GutilPCU.setLanguage("ไม่ตรวจ"));
        panelYesNoCheckNutrition.setSelected("1");


        panelYesNoStudentHealthStatus.setTextYesNo(GutilPCU.setLanguage("ตรวจ"), GutilPCU.setLanguage("ไม่ตรวจ"));
        panelYesNoStudentHealthStatus.setSelected("1");

        panelYesNoStudentHealthResultCheck.setTextYesNo(GutilPCU.setLanguage("แข็งแรง"), GutilPCU.setLanguage("ไม่แข็งแรง"));
        panelYesNoStudentHealthResultCheck.setSelected("1");

        panelYesNoCheckGoiter.setTextYesNo(GutilPCU.setLanguage("ตรวจ"), GutilPCU.setLanguage("ไม่ตรวจ"));
        panelYesNoCheckGoiter.setSelected("1");

        panelYesNoGoiterResult.setTextYesNo(GutilPCU.setLanguage("ปกติ"), GutilPCU.setLanguage("ไม่ปกติ"));
        panelYesNoGoiterResult.setSelected("1");

        panelYesNoCheckFeBlood.setTextYesNo(GutilPCU.setLanguage("ตรวจ"), GutilPCU.setLanguage("ไม่ตรวจ"));
        panelYesNoCheckFeBlood.setSelected("1");

        panelYesNoFeBloodResult.setTextYesNo(GutilPCU.setLanguage("ปกติ"), GutilPCU.setLanguage("ไม่ปกติ"));
        panelYesNoFeBloodResult.setSelected("1");

        panelYesNoCheckDental.setTextYesNo(GutilPCU.setLanguage("ตรวจ"), GutilPCU.setLanguage("ไม่ตรวจ"));
        panelYesNoCheckDental.setSelected("1");


        panelYesNoCheckWorm.setTextYesNo(GutilPCU.setLanguage("ตรวจ"), GutilPCU.setLanguage("ไม่ตรวจ"));
        panelYesNoCheckWorm.setSelected("1");

        panelYesNoCheckWormResult.setTextYesNo(GutilPCU.setLanguage("พบ"), GutilPCU.setLanguage("ไม่พบ"));
        panelYesNoCheckWormResult.setSelected("0");

        panelYesNoCheckThalassemia.setTextYesNo(GutilPCU.setLanguage("ตรวจ"), GutilPCU.setLanguage("ไม่ตรวจ"));
        panelYesNoCheckThalassemia.setSelected("1");

        panelYesNoCheckThalassemiaResult.setTextYesNo(GutilPCU.setLanguage("ปกติ"), GutilPCU.setLanguage("ไม่ปกติ"));
        panelYesNoCheckThalassemiaResult.setSelected("1");

        panelYesNoCheckLice.setTextYesNo(GutilPCU.setLanguage("ตรวจ"), GutilPCU.setLanguage("ไม่ตรวจ"));
        panelYesNoCheckLice.setSelected("1");

        panelYesNoCheckLiceResultCheck.setTextYesNo(GutilPCU.setLanguage("พบ"), GutilPCU.setLanguage("ไม่พบ"));
        panelYesNoCheckLiceResultCheck.setSelected("0");

        panelYesNoCheckBody.setTextYesNo(GutilPCU.setLanguage("ตรวจ"), GutilPCU.setLanguage("ไม่ตรวจ"));
        panelYesNoCheckBody.setSelected("1");

        panelYesNoCheckBodyResult.setTextYesNo(GutilPCU.setLanguage("ปกติ"), GutilPCU.setLanguage("ไม่ปกติ"));
        panelYesNoCheckBodyResult.setSelected("1");

        panelYesNoCheckOther.setTextYesNo(GutilPCU.setLanguage("ตรวจ"), GutilPCU.setLanguage("ไม่ตรวจ"));
        panelYesNoCheckOther.setSelected("1");
    }

    /*แสดงรายละเอียดการเข้ารับบริการของแต่ละ VisitSchool*/
    private void showHealthServiceDetail() {
        updateStudentList();
    }

    private School getDataFromObjectSchool(int row) {
        theSchool = (School) vSchool.get(row);
        return theSchool;
    }

    private CheckEyes getDataFromObjectCheckEyes(int row) {
        theCheckEyes = (CheckEyes) vCheckEyes.get(row);
        return theCheckEyes;
    }

    private CheckEars getDataFromObjectCheckEars(int row) {
        theCheckEars = (CheckEars) vCheckEars.get(row);
        return theCheckEars;
    }

    /*
     *receiveService เป็น method สำหรับการให้บริการงานอนามัยโรงเรียน ตาม VisitSchool
     *โดยการบริการแต่ละบริการจะอ้างอิงตามตัวเลข (Magic Number) ซึ่งมีรายละเอียดดังนี้
     * 01 = ตรวจสุขภาพ
     * 02 = ตรวจสายตา
     * 03 = ตรวจการได้ยิน
     * 04 = โภชนาการ
     * 05 = คอพอก
     * 06 = โลหิตจางขาดธาตุเหล็ก
     * 07 = ทันตกรรม
     * 08 = หนอนพยาธิ
     * 09 = ธาลาสซิเมีย
     * 10 = เหา
     * 18 = สมรรถภาพร่างกาย
     * 20 = อื่นๆ ระบุ
     */
    /*ลบข้อมูลงานอนามัยโรงเรียน (เปลี่ยนสถานะของ active)*/
    public void deleteVisitSchool() {
        boolean flagConfirmDelete = false;
        int row = jTableSchoolDetail.getSelectedRow();
        if (row != -1) {
            theVisitSchool = (VisitSchool) vVisitSchool.get(row);
            Vector vStudentInVisitSchool = this.theHealthSchoolServiceControl.listStudentByVisitSchoolID(theVisitSchool.getObjectId());
            if (vStudentInVisitSchool != null) {
                if (theUS.confirmBox(Constant.getTextBundle("มีข้อมูลนักเรียนอยู่") +
                        " " + vStudentInVisitSchool.size() + " " +
                        Constant.getTextBundle("คน") +
                        " " +
                        Constant.getTextBundle("คุณต้องการลบข้อมูลหรือไม่ ?"), UpdateStatus.WARNING)) {
                    flagConfirmDelete = true;
                    for (int i = 0; i < vStudentInVisitSchool.size(); i++) {
                        Student theStudent = (Student) vStudentInVisitSchool.elementAt(i);
                        this.theHealthSchoolServiceControl.deleteStudent(theStudent);
                        deleteStudentService(theStudent);
                    }
                }
            } else {
                flagConfirmDelete = true;
            }
            if (flagConfirmDelete) {
                theVisitSchool.visit_school_active = "0";
                theVisitSchool.visit_school_staff_cancle = theEmployee.getObjectId();
                theVisitSchool.visit_school_cancle_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
                this.theHealthSchoolServiceControl.saveVisitSchool(this.theVisitSchool);
                if (vCurrent != null) {
                    if (vCurrent == null) {
                        vCurrent = new Vector();
                    } else {
                        vCurrent.removeElementAt(row);
                    }
                }
                setTableListVisitSchool(vCurrent);
                //add code by noom 19/10/2548 สำหรับ clear หน้าจอการรับบริการของนักเรียน
                setTableListStudent(null);
                String id_service = theVisitSchool.f_health_school_service_type_id;
                setTableListCheck();
                    
                if (("01").equals(id_service)) {
                    jTextAreaStudentHealthNote.setText("");
                    jTextAreaStudentHealthRemark.setText("");
                    jTextFieldCheckStudentHealthReferOut.setText("");
                    integerTextFieldCheckStudentHealthReferOut.setText("");
                    panelYesNoStudentHealthStatus.setSelected("0");
                    doubleTextFieldStudentHealthTall.setText("");
                    doubleTextFieldStudentHealthWeight.setText("");
                    panelYesNoStudentHealthResultCheck.setEnableRadioButton(false);
                    panelYesNoStudentHealthStatus.setSelected("1");
                    panelYesNoStudentHealthResultCheck.setSelected("1");
                } else if (("02").equals(id_service)) {
                    jTextAreaEyeNote.setText("");
                    jTextAreaEyeRemark.setText("");
                    jTextFieldCheckEyeReferOut.setText("");
                    integerTextFieldCheckEyeReferOut.setText("");
                    panelYesNoCheckEye.setSelected("0");
                    jComboBoxResultCheck.setEnabled(false);
                    panelYesNoCheckEye.setSelected("1");
                } else if (("03").equals(id_service)) {
                    jTextFieldCheckEarReferOut.setText("");
                    jTextAreaEarNote.setText("");
                    jTextAreaEarRemark.setText("");
                    integerTextFieldCheckEarReferOut.setText("");
                    jTextFieldCheckEarReferOut.setText("");
                    panelYesNoCheckEar.setSelected("1");
                } else if (("04").equals(id_service)) {
                    jTextFieldCheckNutritionReferOut.setText("");
                    jTextAreaNutritionNote.setText("");
                    jTextAreaNutritionRemark.setText("");
                    integerTextFieldCheckNutritionReferOut.setText("");
                    jTextFieldCheckNutritionReferOut.setText("");
                    panelYesNoCheckNutrition.setSelected("1");
                } else if (("05").equals(id_service)) {
                    jTextFieldCheckGoiterReferOut.setText("");
                    jTextAreaGoiterNote.setText("");
                    jTextAreaGoiterRemark.setText("");
                    integerTextFieldCheckGoiterReferOut.setText("");
                    jTextFieldCheckGoiterReferOut.setText("");
                    panelYesNoCheckGoiter.setSelected("1");
                    panelYesNoGoiterResult.setSelected("1");
                } else if (("06").equals(id_service)) {
                    jTextFieldCheckFeBloodReferOut.setText("");
                    jTextAreaFeBloodNote.setText("");
                    jTextAreaFeBloodRemark.setText("");
                    integerTextFieldCheckFeBloodReferOut.setText("");
                    jTextFieldCheckFeBloodReferOut.setText("");
                    panelYesNoCheckFeBlood.setSelected("1");
                    panelYesNoFeBloodResult.setSelected("1");
                    ;
                } else if (("07").equals(id_service)) {
                    jTextFieldCheckStudentDentalReferOut.setText("");
                    jTextAreaStudentDentalNote.setText("");
                    jTextAreaStudentDentalRemark.setText("");
                    integerTextFieldCheckStudentDentalReferOut.setText("");
                    jTextFieldCheckStudentDentalReferOut.setText("");
                    jComboBoxStudentDentalResultCheck.setEnabled(false);
                    integerTextFieldRealTooth.setText("");
                    integerTextFieldBadRealTooth.setText("");
                    integerTextFieldMilkTooth.setText("");
                    integerTextFieldBadMilkTooth.setText("");
                    panelYesNoCheckDental.setSelected("1");
                } else if (("08").equals(id_service)) {
                    jTextFieldCheckWormReferOut.setText("");
                    jTextAreaWormNote.setText("");
                    jTextAreaWormRemark.setText("");
                    integerTextFieldCheckWormReferOut.setText("");
                    jTextFieldCheckWormReferOut.setText("");
                    panelYesNoCheckWorm.setSelected("1");
                    panelYesNoCheckWormResult.setSelected("0");
                } else if (("09").equals(id_service)) {
                    jTextFieldCheckThalassemiaReferOut.setText("");
                    jTextAreaThalassemiaNote.setText("");
                    jTextAreaThalassemiaRemark.setText("");
                    integerTextFieldCheckThalassemiaReferOut.setText("");
                    jTextFieldCheckThalassemiaReferOut.setText("");
                    panelYesNoCheckThalassemia.setSelected("1");
                    panelYesNoCheckThalassemiaResult.setSelected("1");
                } else if (("10").equals(id_service)) {
                    jTextFieldCheckLiceReferOut.setText("");
                    jTextAreaLiceNote.setText("");
                    jTextAreaLiceRemark.setText("");
                    integerTextFieldCheckLiceReferOut.setText("");
                    jTextFieldCheckLiceReferOut.setText("");

                    panelYesNoCheckLice.setSelected("1");
                    panelYesNoCheckLiceResultCheck.setSelected("0");
                } else if (("18").equals(id_service)) {
                    jTextFieldCheckBodyReferOut.setText("");
                    jTextAreaBodyNote.setText("");
                    jTextAreaBodyRemark.setText("");
                    integerTextFieldCheckBodyReferOut.setText("");
                    jTextFieldCheckBodyReferOut.setText("");
                    panelYesNoCheckBody.setSelected("1");
                    panelYesNoCheckBodyResult.setSelected("1");
                } else if (("20").equals(id_service)) {
                    jTextFieldCheckOtherReferOut.setText("");
                    jTextAreaOtherNote.setText("");
                    jTextAreaOtherRemark.setText("");
                    integerTextFieldCheckOtherReferOut.setText("");
                    jTextFieldCheckOtherReferOut.setText("");
                    panelYesNoCheckOther.setSelected("1");
                }
                jLabelDataSchoolService.setText("");
                jLabelDataSchoolClass.setText("");
                jLabelDataSchoolRoom.setText("");
                jLabelDataSchoolName.setText("");
                clearGUISchoolDetail();
                clearGUIStudentDetail();
            }
        }
    }

    private void setSchoolFromEnable(boolean flag) {
        jComboBoxClass.setEnabled(flag);
        jTextFieldRoom.setEnabled(flag);
        jButtonSaveSchool.setEnabled(flag);
        jButtonDelSchool.setEnabled(flag);
        jComboBoxSchoolName.setEnabled(flag);
        jTextFieldSchoolYear.setEnabled(flag);
        dateComboBoxServiceDate.setEnabled(flag);
        jComboBoxSchoolServiceType.setEnabled(flag);
    }

    private void setStudentFromEnable(boolean flag) {
        jComboBoxPrefixStu.setEnabled(flag);
        jTextFieldStuName.setEnabled(flag);
        jTextFieldStuSurName.setEnabled(flag);
        jTextFieldAge.setEnabled(flag);
        pIDPanel.setEnabled(flag);
        jComboBoxStuSex.setEnabled(flag);
        jTextAreaStudentNote.setEnabled(flag);
    }

    /*บันทึกหรือแก้ไขข้อมูลงานอนามัยโรงเรียน*/
    public void saveVisitSchool() {
        boolean isUpdate = false;
        int selectRow = 0;
        int totalRow = 0;
        getVisitSchool();
        if (this.theVisitSchool != null) {
            /*ตรวจสอบว่าเป็นการ update หรือ insert ข้อมูลใหม่*/
            if (this.theVisitSchool.getObjectId() != null) {
                isUpdate = true;
                selectRow = jTableSchoolDetail.getSelectedRow();
                Constant.println("+++++++++++++++++++++++++++++++++" + selectRow);
            }
            this.theHealthSchoolServiceControl.saveVisitSchool(this.theVisitSchool);
        }
        vVisitSchool = this.theHealthSchoolServiceControl.selectVisitSchoolByPK(theVisitSchool.getObjectId());
        if (vVisitSchool != null) {
            if (vCurrent == null) {
                vCurrent = new Vector();
            }
            try {
                if (isUpdate && selectRow != -1) {
                    vCurrent.setElementAt(vVisitSchool.elementAt(0), selectRow);
                } else {
                    vCurrent.addElement(vVisitSchool.elementAt(0));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setTableListVisitSchool(vCurrent);

        /*กำหนดให้ตารางเลือกข้อมูลที่ update หรือ insert อัตโนมัติ*/
        if (isUpdate && selectRow != -1) {
            jTableSchoolDetail.setRowSelectionInterval(selectRow, selectRow);
        } else {
            totalRow = jTableSchoolDetail.getRowCount();
            if (totalRow != 0) {
                totalRow--;
                jTableSchoolDetail.setRowSelectionInterval(totalRow, totalRow);
            }
        }
    }

    private void deleteStudentService(Student theStudent) {
        Vector vVisitSchoolDelete = this.theHealthSchoolServiceControl.selectVisitSchoolByPK(theStudent.t_health_visit_school_id);
        if (vVisitSchoolDelete != null) {
            VisitSchool theVisitSchoolDelete = (VisitSchool) vVisitSchoolDelete.elementAt(0);
            String serviceID = theVisitSchoolDelete.f_health_school_service_type_id;
            if (("01").equals(serviceID)) {
                deleteCheckStudentHealth();
            } else if (("02").equals(serviceID)) {
                deleteCheckEyes();
            } else if (("03").equals(serviceID)) {
                deleteCheckEars();
            } else if (("04").equals(serviceID)) {
                deleteCheckNutrition();
            } else if (("05").equals(serviceID)) {
                deleteCheckGoiter();
            } else if (("06").equals(serviceID)) {
                deleteCheckFeBlood();
            } else if (("07").equals(serviceID)) {
                deleteCheckStudentDental();
            } else if (("08").equals(serviceID)) {
                deleteCheckWorm();
            } else if (("09").equals(serviceID)) {
                deleteCheckThalassemia();
            } else if (("10").equals(serviceID)) {
                deleteCheckLice();
            } else if (("18").equals(serviceID)) {
                deleteCheckBody();
            } else if (("20").equals(serviceID)) {
                deleteCheckOther();
            }
        }



    }

    public void deleteCheckStudentHealth() {
        Vector vCheckStudentHealthDelete = this.theHealthSchoolServiceControl.selectCheckStudentHealthResultByStudentID(theStudent.getObjectId());
        if (vCheckStudentHealthDelete != null) {
            CheckStudentHealth theCheckStudentHealthDelete = (CheckStudentHealth) vCheckStudentHealthDelete.elementAt(0);
            theCheckStudentHealthDelete.check_student_health_active = "0";
            theCheckStudentHealthDelete.check_student_health_staff_cancle = theEmployee.getObjectId();
            theCheckStudentHealthDelete.check_student_health_cancle_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
            this.theHealthSchoolServiceControl.saveCheckStudentHealth(theCheckStudentHealthDelete);
        }
    }

    public void deleteCheckEyes() {
        Vector vCheckEyesDelete = this.theHealthSchoolServiceControl.selectCheckEyesResultByStudentID(theStudent.getObjectId());
        if (vCheckEyesDelete != null) {
            CheckEyes theCheckEyesDelete = (CheckEyes) vCheckEyesDelete.elementAt(0);
            theCheckEyesDelete.check_eyes_active = "0";
            theCheckEyesDelete.check_eyes_staff_cancle = theEmployee.getObjectId();
            theCheckEyesDelete.check_eyes_cancle_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
            this.theHealthSchoolServiceControl.saveCheckEyes(theCheckEyesDelete);
        }
    }

    public void deleteCheckEars() {
        Vector vCheckEarsDelete = this.theHealthSchoolServiceControl.selectCheckEarsResultByStudentID(theStudent.getObjectId());
        if (vCheckEarsDelete != null) {
            CheckEars theCheckEarsDelete = (CheckEars) vCheckEarsDelete.elementAt(0);
            theCheckEarsDelete.check_ears_active = "0";
            theCheckEarsDelete.check_ears_staff_cancle = theEmployee.getObjectId();
            theCheckEarsDelete.check_ears_cancle_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
            this.theHealthSchoolServiceControl.saveCheckEars(theCheckEarsDelete);
        }
    }

    public void deleteCheckNutrition() {
        Vector vCheckNutritionDelete = this.theHealthSchoolServiceControl.selectCheckNutritionResultByStudentID(theStudent.getObjectId());
        if (vCheckNutritionDelete != null) {
            CheckNutrition theCheckNutritionDelete = (CheckNutrition) vCheckNutritionDelete.elementAt(0);
            theCheckNutritionDelete.check_nutrition_active = "0";
            theCheckNutritionDelete.check_nutrition_staff_cancle = theEmployee.getObjectId();
            theCheckNutritionDelete.check_nutrition_cancle_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
            this.theHealthSchoolServiceControl.saveCheckNutrition(theCheckNutritionDelete);
        }
    }

    public void deleteCheckGoiter() {
        Vector vCheckGoiterDelete = this.theHealthSchoolServiceControl.selectCheckGoiterResultByStudentID(theStudent.getObjectId());
        if (vCheckGoiterDelete != null) {
            CheckGoiter theCheckGoiterDelete = (CheckGoiter) vCheckGoiterDelete.elementAt(0);
            theCheckGoiterDelete.check_goiter_active = "0";
            theCheckGoiterDelete.check_goiter_staff_cancle = theEmployee.getObjectId();
            theCheckGoiterDelete.check_goiter_cancle_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
            this.theHealthSchoolServiceControl.saveCheckGoiter(theCheckGoiterDelete);
        }
    }

    public void deleteCheckFeBlood() {
        Vector vCheckFeBloodDelete = this.theHealthSchoolServiceControl.selectCheckFeBloodResultByStudentID(theStudent.getObjectId());
        if (vCheckFeBloodDelete != null) {
            CheckFeBlood theCheckFeBloodDelete = (CheckFeBlood) vCheckFeBloodDelete.elementAt(0);
            theCheckFeBloodDelete.check_fe_blood_active = "0";
            theCheckFeBloodDelete.check_fe_blood_staff_cancle = theEmployee.getObjectId();
            theCheckFeBloodDelete.check_fe_blood_cancle_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
            this.theHealthSchoolServiceControl.saveCheckFeBlood(theCheckFeBloodDelete);
        }
    }

    public void deleteCheckStudentDental() {
        Vector vCheckStudentDentalDelete = this.theHealthSchoolServiceControl.selectCheckStudentDentalResultByStudentID(theStudent.getObjectId());
        if (vCheckStudentDentalDelete != null) {
            CheckStudentDental theCheckStudentDentalDelete = (CheckStudentDental) vCheckStudentDentalDelete.elementAt(0);
            theCheckStudentDentalDelete.check_student_dental_active = "0";
            theCheckStudentDentalDelete.check_student_dental_staff_cancle = theEmployee.getObjectId();
            theCheckStudentDentalDelete.check_student_dental_cancle_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
            this.theHealthSchoolServiceControl.saveCheckStudentDental(theCheckStudentDentalDelete);
        }
    }

    public void deleteCheckWorm() {
        Vector vCheckWormDelete = this.theHealthSchoolServiceControl.selectCheckWormResultByStudentID(theStudent.getObjectId());
        if (vCheckWormDelete != null) {
            CheckWorm theCheckWormDelete = (CheckWorm) vCheckWormDelete.elementAt(0);
            theCheckWormDelete.check_worm_active = "0";
            theCheckWormDelete.check_worm_staff_cancle = theEmployee.getObjectId();
            theCheckWormDelete.check_worm_cancle_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
            this.theHealthSchoolServiceControl.saveCheckWorm(theCheckWormDelete);
        }
    }

    public void deleteCheckThalassemia() {
        Vector vCheckThalassemiaDelete = this.theHealthSchoolServiceControl.selectCheckThalassemiaResultByStudentID(theStudent.getObjectId());
        if (vCheckThalassemiaDelete != null) {
            CheckThalassemia theCheckThalassemiaDelete = (CheckThalassemia) vCheckThalassemiaDelete.elementAt(0);
            theCheckThalassemiaDelete.check_thalassemia_active = "0";
            theCheckThalassemiaDelete.check_thalassemia_staff_cancle = theEmployee.getObjectId();
            theCheckThalassemiaDelete.check_thalassemia_cancle_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
            this.theHealthSchoolServiceControl.saveCheckThalassemia(theCheckThalassemiaDelete);
        }
    }

    public void deleteCheckLice() {
        Vector vCheckLiceDelete = this.theHealthSchoolServiceControl.selectCheckLiceResultByStudentID(theStudent.getObjectId());
        if (vCheckLiceDelete != null) {
            CheckLice theCheckLiceDelete = (CheckLice) vCheckLiceDelete.elementAt(0);
            theCheckLiceDelete.check_lice_active = "0";
            theCheckLiceDelete.check_lice_staff_cancle = theEmployee.getObjectId();
            theCheckLiceDelete.check_lice_cancle_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
            this.theHealthSchoolServiceControl.saveCheckLice(theCheckLiceDelete);
        }
    }

    public void deleteCheckBody() {
        Vector vCheckBodyDelete = this.theHealthSchoolServiceControl.selectCheckBodyResultByStudentID(theStudent.getObjectId());
        if (vCheckBodyDelete != null) {
            CheckBody theCheckBodyDelete = (CheckBody) vCheckBodyDelete.elementAt(0);
            theCheckBodyDelete.check_body_active = "0";
            theCheckBodyDelete.check_body_staff_cancle = theEmployee.getObjectId();
            theCheckBodyDelete.check_body_cancle_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
            this.theHealthSchoolServiceControl.saveCheckBody(theCheckBodyDelete);
        }
    }

    public void deleteCheckOther() {
        Vector vCheckOtherDelete = this.theHealthSchoolServiceControl.selectCheckOtherResultByStudentID(theStudent.getObjectId());
        if (vCheckOtherDelete != null) {
            CheckOther theCheckOtherDelete = (CheckOther) vCheckOtherDelete.elementAt(0);
            theCheckOtherDelete.check_other_active = "0";
            theCheckOtherDelete.check_other_staff_cancle = theEmployee.getObjectId();
            theCheckOtherDelete.check_other_cancle_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
            this.theHealthSchoolServiceControl.saveCheckOther(theCheckOtherDelete);
        }
    }

    public void updateVisitSchoolList() {
        if (theVisitSchool != null) {
            vVisitSchool = this.theHealthSchoolServiceControl.listVisitSchoolBySchoolID(theVisitSchool.t_health_school_id);
        }
        vCurrent = vVisitSchool;
        setTableListVisitSchool(vCurrent);
    }

    public void updateStudentList() {
        if (theStudent != null) {
            vStudent = this.theHealthSchoolServiceControl.listStudentByVisitSchoolID(theVisitSchool.getObjectId());
        }
        setTableListStudent(vStudent);
    }

    public void showAllVisitSchoolList() {
        if (theVisitSchool != null) {
            vVisitSchool = this.theHealthSchoolServiceControl.selectAllVisitSchool();
        }
        setTableListVisitSchool(vVisitSchool);
    }

    private void setEmployee(Employee theEmployee) {
        this.theEmployee = theEmployee;
    }

    /*นำค่าจาก Student Object มาแสดงผล*/
    public void setStudent(Student std) {
        this.theStudent = std;
        if (theStudent == null) {
            theStudent = new Student();
        }

        ComboboxModel.setCodeComboBox(this.jComboBoxPrefixStu, theStudent.f_patient_prefix_id);
        ComboboxModel.setCodeComboBox(this.jComboBoxStuSex, theStudent.f_sex_id);
        this.jTextFieldStuName.setText(theStudent.student_firstname);
        this.jTextFieldStuSurName.setText(theStudent.student_surname);
        this.jTextFieldStuNumber.setText(theStudent.student_number);
        this.pIDPanel.setText(theStudent.student_pid);
        
        this.jTextAreaStudentNote.setText(theStudent.student_note);

        selectedStudentNumber = theStudent.student_number;
        selectedStudentPid = theStudent.student_pid;
        Family fam = this.theHomeControl.readFamilyById(theStudent.getObjectId());
        if(fam!=null)
            jTextFieldAge.setText(DateUtil.calculateAge(fam.patient_birthday,thePO.getCurrentDateTime()));
        
        vc = this.theHealthSchoolServiceControl.selectCheckStudent(theStudent.getObjectId());
        updateCheckStudentList();
    }
    public void updateCheckStudentList(){
        setTableListCheck();
        setVCheckStudentHealthPanel(vc[0]);
        setVCheckEyesPanel(vc[1]);
        setVCheckEarsPanel(vc[2]);
        setVCheckNutritionPanel(vc[3]);
        setVCheckGoiterPanel(vc[4]);
        setVCheckFeBloodPanel(vc[5]);
        setVCheckStudentDentalPanel(vc[6]);
        setVCheckWormPanel(vc[7]);
        setVCheckThalassemiaPanel(vc[8]);
        setVCheckLicePanel(vc[9]);
        setVCheckBodyPanel(vc[10]);
        setVCheckOtherPanel(vc[11]);
    }

    /*นำค่าจาก VisitSchool Object มาแสดงผล*/
    public void setVisitSchool(VisitSchool vs) {
        theVisitSchool = vs;
        if (theVisitSchool == null) {
            theVisitSchool = new VisitSchool();
        }

        ComboboxModel.setCodeComboBox(this.jComboBoxSchoolName, theVisitSchool.t_health_school_id);
        ComboboxModel.setCodeComboBox(this.jComboBoxClass, theVisitSchool.b_school_class_id);
        this.jTextFieldRoom.setText(theVisitSchool.visit_school_room);
        ComboboxModel.setCodeComboBox(this.jComboBoxSchoolServiceType, theVisitSchool.f_health_school_service_type_id);
        this.dateComboBoxServiceDate.setText(
                DateUtil.convertFieldDate(theVisitSchool.visit_school_service_date));
        this.jTextFieldSchoolYear.setText(theVisitSchool.visit_school_term);
        this.jTextFieldServiceOther.setText(theVisitSchool.visit_school_other_service);

        if (theSchool != null) {
            jLabelDataSchoolService.setText(
                    theAllComboBoxControl.getValueOfSchoolServiceType(
                    theVisitSchool.f_health_school_service_type_id));
            jLabelDataSchoolClass.setText(
                    theAllComboBoxControl.getValueOfSchoolClass(
                    theVisitSchool.b_school_class_id));
            jLabelDataSchoolRoom.setText(theVisitSchool.visit_school_room);
            jLabelDataSchoolName.setText(theSchool.school_name);
        } else {
            jLabelDataSchoolService.setText("");
            jLabelDataSchoolClass.setText("");
            jLabelDataSchoolRoom.setText("");
            jLabelDataSchoolName.setText("");
        }
    }

    public void setVCheckStudentHealthPanel(Vector vTempCheckStudentHealth) {

        setShowDataCheckStudentHealth(true);

        if (vTempCheckStudentHealth != null) {
            theCheckStudentHealth = (CheckStudentHealth) vTempCheckStudentHealth.elementAt(0);
            panelYesNoStudentHealthStatus.setSelected(theCheckStudentHealth.f_answer_id);
            panelYesNoStudentHealthResultCheck.setSelected(theCheckStudentHealth.f_answer_check_student_health_id);
            if (theCheckStudentHealth.b_visit_refer_office_id.equals("") || theCheckStudentHealth.b_visit_refer_office_id == null) {
                integerTextFieldCheckStudentHealthReferOut.setText("");
                jTextFieldCheckStudentHealthReferOut.setText("");
            } else {
                Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(theCheckStudentHealth.b_visit_refer_office_id);
                if (theOffice != null) {
                    integerTextFieldCheckStudentHealthReferOut.setText(theOffice.getCode());
                    jTextFieldCheckStudentHealthReferOut.setText(theOffice.getName());
                } else {
                    integerTextFieldCheckStudentHealthReferOut.setText("");
                    jTextFieldCheckStudentHealthReferOut.setText("");
                }
            }
            doubleTextFieldStudentHealthTall.setText(theCheckStudentHealth.check_student_health_tall);
            doubleTextFieldStudentHealthWeight.setText(theCheckStudentHealth.check_student_health_weight);
            jTextAreaStudentHealthNote.setText(theCheckStudentHealth.check_student_health_note);
            jTextAreaStudentHealthRemark.setText(theCheckStudentHealth.check_student_health_remark);
        } else {
            theCheckStudentHealth = new CheckStudentHealth();
            jTextAreaStudentHealthNote.setText("");
            jTextAreaStudentHealthRemark.setText("");
            jTextFieldCheckStudentHealthReferOut.setText("");
            integerTextFieldCheckStudentHealthReferOut.setText("");
            panelYesNoStudentHealthStatus.setSelected("0");
            doubleTextFieldStudentHealthTall.setText("");
            doubleTextFieldStudentHealthWeight.setText("");
            panelYesNoStudentHealthResultCheck.setEnableRadioButton(false);
            panelYesNoStudentHealthStatus.setSelected("1");
            panelYesNoStudentHealthResultCheck.setSelected("1");
        }
        setEnableStudentHealthResultCheck();
    }

    public void setVCheckEyesPanel(Vector vTempCheckEyes) {
        if (theStudent != null) {
            setShowDataCheckEye(true);

            if (vTempCheckEyes != null) {
                theCheckEyes = (CheckEyes) vTempCheckEyes.elementAt(0);
                panelYesNoCheckEye.setSelected(theCheckEyes.f_answer_id);

                ComboboxModel.setCodeComboBox(this.jComboBoxResultCheck, theCheckEyes.f_health_check_eyes_id);

                if (theCheckEyes.b_visit_refer_office_id.equals("") || theCheckEyes.b_visit_refer_office_id == null) {
                    integerTextFieldCheckEyeReferOut.setText("");
                    jTextFieldCheckEyeReferOut.setText("");
                } else {
                    Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(theCheckEyes.b_visit_refer_office_id);
                    if (theOffice != null) {
                        integerTextFieldCheckEyeReferOut.setText(theOffice.getCode());
                        jTextFieldCheckEyeReferOut.setText(theOffice.getName());
                    } else {
                        integerTextFieldCheckEyeReferOut.setText("");
                        jTextFieldCheckEyeReferOut.setText("");
                    }
                }
                jTextAreaEyeNote.setText(theCheckEyes.check_eyes_note);
                jTextAreaEyeRemark.setText(theCheckEyes.check_eyes_remark);
            } else {
                theCheckEyes = new CheckEyes();
                jTextAreaEyeNote.setText("");
                jTextAreaEyeRemark.setText("");
                jTextFieldCheckEyeReferOut.setText("");
                integerTextFieldCheckEyeReferOut.setText("");
                panelYesNoCheckEye.setSelected("0");
                jComboBoxResultCheck.setEnabled(false);
                panelYesNoCheckEye.setSelected("1");
            }
            setEnableCheckEyeResultCheck();
        }
    }

    public void setVCheckNutritionPanel(Vector vTempCheckNutrition) {
        if (theStudent != null) {
            setShowDataNutrition(true);

            if (vTempCheckNutrition != null) {
                theCheckNutrition = (CheckNutrition) vTempCheckNutrition.elementAt(0);
                panelYesNoCheckNutrition.setSelected(theCheckNutrition.f_answer_id);

                ComboboxModel.setCodeComboBox(this.jComboBoxNutritionResultCheck, theCheckNutrition.f_health_nutrition_level_id);
                if (theCheckNutrition.b_visit_refer_office_id.equals("") || theCheckNutrition.b_visit_refer_office_id == null) {
                    integerTextFieldCheckNutritionReferOut.setText("");
                    jTextFieldCheckNutritionReferOut.setText("");
                } else {
                    Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(theCheckNutrition.b_visit_refer_office_id);
                    if (theOffice != null) {
                        integerTextFieldCheckNutritionReferOut.setText(theOffice.getCode());
                        jTextFieldCheckNutritionReferOut.setText(theOffice.getName());
                    } else {
                        integerTextFieldCheckNutritionReferOut.setText("");
                        jTextFieldCheckNutritionReferOut.setText("");
                    }
                }
                jTextAreaNutritionNote.setText(theCheckNutrition.check_nutrition_note);
                jTextAreaNutritionRemark.setText(theCheckNutrition.check_nutrition_remark);
            } else {
                theCheckNutrition = new CheckNutrition();
                jTextFieldCheckNutritionReferOut.setText("");
                jTextAreaNutritionNote.setText("");
                jTextAreaNutritionRemark.setText("");
                integerTextFieldCheckNutritionReferOut.setText("");
                jTextFieldCheckNutritionReferOut.setText("");
                panelYesNoCheckNutrition.setSelected("1");
            }
            setEnableCheckNutritionResultCheck();
        }
    }

    public void setVCheckEarsPanel(Vector vTempCheckEars) {
        if (theStudent != null) {
            setShowDataCheckEar(true);

            if (vTempCheckEars != null) {
                theCheckEars = (CheckEars) vTempCheckEars.elementAt(0);
                panelYesNoCheckEar.setSelected(theCheckEars.f_answer_id);

                ComboboxModel.setCodeComboBox(this.jComboBoxEarResultCheck, theCheckEars.f_health_check_ears_id);
                if (theCheckEars.b_visit_refer_office_id.equals("") || theCheckEars.b_visit_refer_office_id == null) {
                    integerTextFieldCheckEarReferOut.setText("");
                    jTextFieldCheckEarReferOut.setText("");
                } else {
                    Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(theCheckEars.b_visit_refer_office_id);
                    if (theOffice != null) {
                        integerTextFieldCheckEarReferOut.setText(theOffice.getCode());
                        jTextFieldCheckEarReferOut.setText(theOffice.getName());
                    } else {
                        integerTextFieldCheckEarReferOut.setText("");
                        jTextFieldCheckEarReferOut.setText("");
                    }
                }
                jTextAreaEarNote.setText(theCheckEars.check_ears_note);
                jTextAreaEarRemark.setText(theCheckEars.check_ears_remark);
            } else {
                theCheckEars = new CheckEars();
                jTextFieldCheckEarReferOut.setText("");
                jTextAreaEarNote.setText("");
                jTextAreaEarRemark.setText("");
                integerTextFieldCheckEarReferOut.setText("");
                jTextFieldCheckEarReferOut.setText("");
                panelYesNoCheckEar.setSelected("1");
            }
            setEnableCheckEarResultCheck();
        }
    }

    public void setVCheckGoiterPanel(Vector vTempCheckGoiter) {
        if (theStudent != null) {
            setShowDataGoiter(true);

            if (vTempCheckGoiter != null) {
                theCheckGoiter = (CheckGoiter) vTempCheckGoiter.elementAt(0);
                panelYesNoCheckGoiter.setSelected(theCheckGoiter.f_answer_id);
                panelYesNoGoiterResult.setSelected(theCheckGoiter.f_answer_check_goiter_id);

                if (theCheckGoiter.b_visit_refer_office_id.equals("") || theCheckGoiter.b_visit_refer_office_id == null) {
                    integerTextFieldCheckGoiterReferOut.setText("");
                    jTextFieldCheckGoiterReferOut.setText("");
                } else {
                    Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(theCheckGoiter.b_visit_refer_office_id);
                    if (theOffice != null) {
                        integerTextFieldCheckGoiterReferOut.setText(theOffice.getCode());
                        jTextFieldCheckGoiterReferOut.setText(theOffice.getName());
                    } else {
                        integerTextFieldCheckGoiterReferOut.setText("");
                        jTextFieldCheckGoiterReferOut.setText("");
                    }
                }
                jTextAreaGoiterNote.setText(theCheckGoiter.check_goiter_note);
                jTextAreaGoiterRemark.setText(theCheckGoiter.check_goiter_remark);
            } else {
                theCheckGoiter = new CheckGoiter();
                jTextFieldCheckGoiterReferOut.setText("");
                jTextAreaGoiterNote.setText("");
                jTextAreaGoiterRemark.setText("");
                integerTextFieldCheckGoiterReferOut.setText("");
                jTextFieldCheckGoiterReferOut.setText("");
                panelYesNoCheckGoiter.setSelected("1");
                panelYesNoGoiterResult.setSelected("0");
            }
            setEnableCheckGoiterResultCheck();
        }
    }

    public void setVCheckFeBloodPanel(Vector vTempCheckFeBlood) {
        if (theStudent != null) {
            setShowDataFeBlood(true);

            if (vTempCheckFeBlood != null) {
                theCheckFeBlood = (CheckFeBlood) vTempCheckFeBlood.elementAt(0);
                panelYesNoCheckFeBlood.setSelected(theCheckFeBlood.f_answer_id);
                panelYesNoFeBloodResult.setSelected(theCheckFeBlood.f_answer_check_fe_blood_id);
                if (theCheckFeBlood.b_visit_refer_office_id.equals("") || theCheckFeBlood.b_visit_refer_office_id == null) {
                    integerTextFieldCheckFeBloodReferOut.setText("");
                    jTextFieldCheckFeBloodReferOut.setText("");
                } else {
                    Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(theCheckFeBlood.b_visit_refer_office_id);
                    if (theOffice != null) {
                        integerTextFieldCheckFeBloodReferOut.setText(theOffice.getCode());
                        jTextFieldCheckFeBloodReferOut.setText(theOffice.getName());
                    } else {
                        integerTextFieldCheckFeBloodReferOut.setText("");
                        jTextFieldCheckFeBloodReferOut.setText("");
                    }
                }
                jTextAreaFeBloodNote.setText(theCheckFeBlood.check_fe_blood_note);
                jTextAreaFeBloodRemark.setText(theCheckFeBlood.check_fe_blood_remark);
            } else {
                theCheckFeBlood = new CheckFeBlood();
                jTextFieldCheckFeBloodReferOut.setText("");
                jTextAreaFeBloodNote.setText("");
                jTextAreaFeBloodRemark.setText("");
                integerTextFieldCheckFeBloodReferOut.setText("");
                jTextFieldCheckFeBloodReferOut.setText("");
                panelYesNoCheckFeBlood.setSelected("1");
                panelYesNoFeBloodResult.setSelected("1");
                ;
            }
            setEnableCheckFeBloodResultCheck();
        }
    }

    public void setVCheckStudentDentalPanel(Vector vTempCheckStudentDental) {
        if (theStudent != null) {
            setShowDataStudentDental(true);
            if (vTempCheckStudentDental != null) {
                theCheckStudentDental = (CheckStudentDental) vTempCheckStudentDental.elementAt(0);
                panelYesNoCheckDental.setSelected(theCheckStudentDental.f_answer_id);

                ComboboxModel.setCodeComboBox(this.jComboBoxStudentDentalResultCheck, theCheckStudentDental.f_health_gum_level_id);
                if (theCheckStudentDental.b_visit_refer_office_id.equals("") || theCheckStudentDental.b_visit_refer_office_id == null) {
                    integerTextFieldCheckStudentDentalReferOut.setText("");
                    jTextFieldCheckStudentDentalReferOut.setText("");
                } else {
                    Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(theCheckStudentDental.b_visit_refer_office_id);
                    if (theOffice != null) {
                        integerTextFieldCheckStudentDentalReferOut.setText(theOffice.getCode());
                        jTextFieldCheckStudentDentalReferOut.setText(theOffice.getName());
                    } else {
                        integerTextFieldCheckStudentDentalReferOut.setText("");
                        jTextFieldCheckStudentDentalReferOut.setText("");
                    }
                }
                integerTextFieldRealTooth.setText(theCheckStudentDental.check_student_dental_num_tooth);
                integerTextFieldBadRealTooth.setText(theCheckStudentDental.check_student_dental_num_bad_tooth);
                integerTextFieldMilkTooth.setText(theCheckStudentDental.check_student_dental_num_milktooth);
                integerTextFieldBadMilkTooth.setText(theCheckStudentDental.check_student_dental_num_bad_milktooth);

                jTextAreaStudentDentalNote.setText(theCheckStudentDental.check_student_dental_note);
                jTextAreaStudentDentalRemark.setText(theCheckStudentDental.check_student_dental_remark);
            } else {
                theCheckStudentDental = new CheckStudentDental();
                jTextFieldCheckStudentDentalReferOut.setText("");
                jTextAreaStudentDentalNote.setText("");
                jTextAreaStudentDentalRemark.setText("");
                integerTextFieldCheckStudentDentalReferOut.setText("");
                jTextFieldCheckStudentDentalReferOut.setText("");
                jComboBoxStudentDentalResultCheck.setEnabled(false);
                integerTextFieldRealTooth.setText("");
                integerTextFieldBadRealTooth.setText("");
                integerTextFieldMilkTooth.setText("");
                integerTextFieldBadMilkTooth.setText("");
                panelYesNoCheckDental.setSelected("1");
            }
            setEnableCheckDentalResultCheck();
        }
    }

    /*นำค่าจาก CheckEyes Object มาแสดงผล*/
    public void showCheckEyesDetail(CheckEyes theCheckEyes) {
        if (theCheckEyes != null) {
            Vector vStudentTemp = new Vector();

            vStudentTemp = this.theHealthSchoolServiceControl.selectStudentByPK(theCheckEyes.t_health_student_id);
            if (vStudentTemp != null) {
                Student theStudent = (Student) vStudentTemp.elementAt(0);
                vStudentTemp = null;
            }
            if (theCheckEyes != null) {
                panelYesNoCheckEye.setSelected(theCheckEyes.f_answer_id);
                ComboboxModel.setCodeComboBox(this.jComboBoxResultCheck, theCheckEyes.f_health_check_eyes_id);
                jTextAreaEyeNote.setText(theCheckEyes.check_eyes_note);
                jTextAreaEyeRemark.setText(theCheckEyes.check_eyes_remark);
            }
        }
    }

    public void setVCheckWormPanel(Vector vTempCheckWorm) {
        if (theStudent != null) {
            setShowDataCheckWorm(true);
            if (vTempCheckWorm != null) {
                theCheckWorm = (CheckWorm) vTempCheckWorm.elementAt(0);
                panelYesNoCheckWorm.setSelected(theCheckWorm.f_answer_id);
                panelYesNoCheckWormResult.setSelected(theCheckWorm.f_answer_check_worm_id);

                if (theCheckWorm.b_visit_refer_office_id.equals("") || theCheckWorm.b_visit_refer_office_id == null) {
                    integerTextFieldCheckWormReferOut.setText("");
                    jTextFieldCheckWormReferOut.setText("");
                } else {
                    Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(theCheckWorm.b_visit_refer_office_id);
                    if (theOffice != null) {
                        integerTextFieldCheckWormReferOut.setText(theOffice.getCode());
                        jTextFieldCheckWormReferOut.setText(theOffice.getName());
                    } else {
                        integerTextFieldCheckWormReferOut.setText("");
                        jTextFieldCheckWormReferOut.setText("");
                    }
                }
                jTextAreaWormNote.setText(theCheckWorm.check_worm_note);
                jTextAreaWormRemark.setText(theCheckWorm.check_worm_remark);
            } else {
                theCheckWorm = new CheckWorm();
                jTextFieldCheckWormReferOut.setText("");
                jTextAreaWormNote.setText("");
                jTextAreaWormRemark.setText("");
                integerTextFieldCheckWormReferOut.setText("");
                jTextFieldCheckWormReferOut.setText("");
                panelYesNoCheckWorm.setSelected("1");

                panelYesNoCheckWormResult.setSelected("0");
            }
            setEnableCheckWormResultCheck();
        }
    }

    public void setVCheckThalassemiaPanel(Vector vTempCheckThalassemia) {
        if (theStudent != null) {
            setShowDataThalassemia(true);

            if (vTempCheckThalassemia != null) {
                theCheckThalassemia = (CheckThalassemia) vTempCheckThalassemia.elementAt(0);
                panelYesNoCheckThalassemia.setSelected(theCheckThalassemia.f_answer_id);
                panelYesNoCheckThalassemiaResult.setSelected(theCheckThalassemia.f_answer_check_thalassemia_id);

                if (theCheckThalassemia.b_visit_refer_office_id.equals("") || theCheckThalassemia.b_visit_refer_office_id == null) {
                    integerTextFieldCheckThalassemiaReferOut.setText("");
                    jTextFieldCheckThalassemiaReferOut.setText("");
                } else {
                    Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(theCheckThalassemia.b_visit_refer_office_id);
                    if (theOffice != null) {
                        integerTextFieldCheckThalassemiaReferOut.setText(theOffice.getCode());
                        jTextFieldCheckThalassemiaReferOut.setText(theOffice.getName());
                    } else {
                        integerTextFieldCheckThalassemiaReferOut.setText("");
                        jTextFieldCheckThalassemiaReferOut.setText("");
                    }
                }
                jTextAreaThalassemiaNote.setText(theCheckThalassemia.check_thalassemia_note);
                jTextAreaThalassemiaRemark.setText(theCheckThalassemia.check_thalassemia_remark);
            } else {
                theCheckThalassemia = new CheckThalassemia();
                jTextFieldCheckThalassemiaReferOut.setText("");
                jTextAreaThalassemiaNote.setText("");
                jTextAreaThalassemiaRemark.setText("");
                integerTextFieldCheckThalassemiaReferOut.setText("");
                jTextFieldCheckThalassemiaReferOut.setText("");
                panelYesNoCheckThalassemia.setSelected("1");
                panelYesNoCheckThalassemiaResult.setSelected("1");
            }
            setEnableCheckThalassemiaResultCheck();
        }
    }

    public void setVCheckLicePanel(Vector vTempCheckLice) {
        if (theStudent != null) {
            setShowDataCheckLice(true);

            if (vTempCheckLice != null) {
                theCheckLice = (CheckLice) vTempCheckLice.elementAt(0);
                panelYesNoCheckLice.setSelected(theCheckLice.f_answer_id);
                panelYesNoCheckLiceResultCheck.setSelected(theCheckLice.f_answer_check_lice_id);
                if (theCheckLice.b_visit_refer_office_id.equals("") || theCheckLice.b_visit_refer_office_id == null) {
                    integerTextFieldCheckLiceReferOut.setText("");
                    jTextFieldCheckLiceReferOut.setText("");
                } else {
                    Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(theCheckLice.b_visit_refer_office_id);
                    if (theOffice != null) {
                        integerTextFieldCheckLiceReferOut.setText(theOffice.getCode());
                        jTextFieldCheckLiceReferOut.setText(theOffice.getName());
                    } else {
                        integerTextFieldCheckLiceReferOut.setText("");
                        jTextFieldCheckLiceReferOut.setText("");
                    }
                }
                jTextAreaLiceNote.setText(theCheckLice.check_lice_note);
                jTextAreaLiceRemark.setText(theCheckLice.check_lice_remark);
            } else {
                theCheckLice = new CheckLice();
                jTextFieldCheckLiceReferOut.setText("");
                jTextAreaLiceNote.setText("");
                jTextAreaLiceRemark.setText("");
                integerTextFieldCheckLiceReferOut.setText("");
                jTextFieldCheckLiceReferOut.setText("");
                panelYesNoCheckLice.setSelected("1");
                panelYesNoCheckLiceResultCheck.setSelected("0");
            }
            setEnableCheckLiceResultCheck();
        }
    }

    public void setVCheckBodyPanel(Vector vTempCheckBody) {
        if (theStudent != null) {
            setShowDataCheckBody(true);

            if (vTempCheckBody != null) {
                theCheckBody = (CheckBody) vTempCheckBody.elementAt(0);
                panelYesNoCheckBody.setSelected(theCheckBody.f_answer_id);
                panelYesNoCheckBodyResult.setSelected(theCheckBody.f_answer_check_body_id);

                if (theCheckBody.b_visit_refer_office_id.equals("") || theCheckBody.b_visit_refer_office_id == null) {
                    integerTextFieldCheckBodyReferOut.setText("");
                    jTextFieldCheckBodyReferOut.setText("");
                } else {
                    Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(theCheckBody.b_visit_refer_office_id);
                    if (theOffice != null) {
                        integerTextFieldCheckBodyReferOut.setText(theOffice.getCode());
                        jTextFieldCheckBodyReferOut.setText(theOffice.getName());
                    } else {
                        integerTextFieldCheckBodyReferOut.setText("");
                        jTextFieldCheckBodyReferOut.setText("");
                    }
                }
                jTextAreaBodyNote.setText(theCheckBody.check_body_note);
                jTextAreaBodyRemark.setText(theCheckBody.check_body_remark);
            } else {
                theCheckBody = new CheckBody();
                jTextFieldCheckBodyReferOut.setText("");
                jTextAreaBodyNote.setText("");
                jTextAreaBodyRemark.setText("");
                integerTextFieldCheckBodyReferOut.setText("");
                jTextFieldCheckBodyReferOut.setText("");
                panelYesNoCheckBody.setSelected("1");
                panelYesNoCheckBodyResult.setSelected("1");
            }
            setEnableCheckBodyResultCheck();
        }
    }

    public void setVCheckOtherPanel(Vector vTempCheckOther) {
        if (theStudent != null) {
            setShowDataCheckOther(true);

            if (vTempCheckOther != null) {
                theCheckOther = (CheckOther) vTempCheckOther.elementAt(0);
                panelYesNoCheckOther.setSelected(theCheckOther.f_answer_id);
                if (theCheckOther.b_visit_refer_office_id.equals("") || theCheckOther.b_visit_refer_office_id == null) {
                    integerTextFieldCheckOtherReferOut.setText("");
                    jTextFieldCheckOtherReferOut.setText("");
                } else {
                    Office theOffice = this.theHealthSchoolServiceControl.selectOfficeByPK(theCheckOther.b_visit_refer_office_id);
                    if (theOffice != null) {
                        integerTextFieldCheckOtherReferOut.setText(theOffice.getCode());
                        jTextFieldCheckOtherReferOut.setText(theOffice.getName());
                    } else {
                        integerTextFieldCheckOtherReferOut.setText("");
                        jTextFieldCheckOtherReferOut.setText("");
                    }
                }
                jTextAreaOtherNote.setText(theCheckOther.check_other_note);
                jTextAreaOtherRemark.setText(theCheckOther.check_other_remark);
            } else {
                theCheckOther = new CheckOther();
                jTextFieldCheckOtherReferOut.setText("");
                jTextAreaOtherNote.setText("");
                jTextAreaOtherRemark.setText("");
                integerTextFieldCheckOtherReferOut.setText("");
                jTextFieldCheckOtherReferOut.setText("");
                panelYesNoCheckOther.setSelected("1");
            }
            setEnableCheckOtherResultCheck();
        }
    }

    /*สำหรับ clear หน้าจอเมื่อผู้ใช้ทำการกดปุ่ม + หรือ - ของนักเรียน*/
    public void clearGUIStudentDetail() {
        jTextFieldStuName.setText("");
        jTextFieldStuSurName.setText("");
        jTextFieldAge.setText("");
        jTextAreaStudentNote.setText("");
        pIDPanel.setText("");
    }

    /*สำหรับ clear หน้าจอเมื่อผู้ใช้ทำการกดปุ่ม + หรือ - หน้าโรงเรียน*/
    public void clearGUISchoolDetail() {
        jTextFieldSchoolYear.setText("");
        jTextFieldServiceOther.setText("");
        jTextFieldRoom.setText("");
        dateComboBoxServiceDate.setText(DateUtil.convertFieldDate(
                theHosManage.theHC.theLookupControl.getTextCurrentDate()));
    }

    private boolean checkNumOfTooth() {
        String numMilkTooth = integerTextFieldMilkTooth.getText();
        String numBadMilkTooth = integerTextFieldBadMilkTooth.getText();
        String numRealTooth = integerTextFieldRealTooth.getText();
        String numBadRealTooth = integerTextFieldBadRealTooth.getText();
        String errorMsg = "";
        try {
            if ((!numMilkTooth.equals("") && Integer.parseInt(numMilkTooth) > 20) || (!numBadMilkTooth.equals("") && Integer.parseInt(numBadMilkTooth) > 20)) {
                errorMsg = GutilPCU.getTextBundle("WarningCheckMilkTooth") + "\r\n";
            }
            if ((!numRealTooth.equals("") && Integer.parseInt(numRealTooth) > 32) || (!numBadRealTooth.equals("") && Integer.parseInt(numBadRealTooth) > 32)) {
                errorMsg += GutilPCU.getTextBundle("WarningCheckRealTooth") + "\r\n";
            }
            //new code for test num of tooth
            if (!numRealTooth.equals("") && !numBadRealTooth.equals("")) {
                if (Integer.parseInt(numBadRealTooth) > Integer.parseInt(numRealTooth)) {
                    errorMsg += GutilPCU.getTextBundle("WarningCheckNumRealTooth") + "\r\n";
                }
            }
            if (!numMilkTooth.equals("") && !numBadMilkTooth.equals("")) {
                if (Integer.parseInt(numBadMilkTooth) > Integer.parseInt(numMilkTooth)) {
                    errorMsg += GutilPCU.getTextBundle("WarningCheckNumMilkTooth") + "\r\n";
                }
            }
            if (!numRealTooth.equals("") && !numMilkTooth.equals("")) {
                if (Integer.parseInt(numRealTooth) + Integer.parseInt(numMilkTooth) > 32) {
                    errorMsg += GutilPCU.getTextBundle("WarningCheckSumTooth") + "\r\n";
                }
            }
            if (!numBadMilkTooth.equals("") && !numBadRealTooth.equals("")) {
                if (Integer.parseInt(numBadMilkTooth) + Integer.parseInt(numBadRealTooth) > 32) {
                    errorMsg += GutilPCU.getTextBundle("WarningCheckSumTooth") + "\r\n";
                }
            }

            if (("").equals(errorMsg)) {
                return true;
            } else {
                theUS.setStatus(errorMsg, UpdateStatus.WARNING);
                return false;
            }
        } catch (NumberFormatException e) {
            Constant.println("Exception in PanelHealthSchool => NumberFormatException");
            return false;
        }
    }

    /*ค้นหาโรงเรียน*/
    private void searchSchool() {
        String search = this.jTextFieldSearchSchool.getText();
        this.vSchool = this.theVillageControl.listSchoolByNameOrNumber(search, "0");
        setTableListSchool(this.vSchool);
        showTableVisitSchool(this.vSchool);
    }

    private void showTableVisitSchool(Vector vSchool) {
        if (vSchool != null) {
            Vector vSearchVisitSchool = new Vector();
            School school = new School();
            for (int i = 0; i < vSchool.size(); i++) {
                school = (School) vSchool.get(i);
                vSearchVisitSchool.addElement(school.getObjectId());
            }
            if (theVisitSchool != null) {
                vVisitSchool = this.theHealthSchoolServiceControl.listVisitSchoolBySchoolID(vSearchVisitSchool);
            }
            vCurrent = vVisitSchool;
            setTableListVisitSchool(vCurrent);
        }
    }

    /*นำข้อมูลจาก School Object มาแสดงผลในตาราง*/
    private void setTableListSchool(Vector vc) {
        String[] col = {GutilPCU.getTextBundle("No."), GutilPCU.getTextBundle("HealthSchoolName")};
        TableModel tm;
        if (vc != null) {
            tm = new TableModel(col, vc.size());
            School school = new School();
            for (int i = 0; i < vc.size(); i++) {
                school = (School) vc.get(i);
                tm.setValueAt(String.valueOf((i + 1)), i, 0);
                tm.setValueAt(school.school_name, i, 1);
            }
        } else {
            tm = new TableModel(col, 0);
        }
        jTableSchoolList.setModel(tm);
        setPatternShowTable(jTableSchoolList);

    }

    private void setTableListStudent(Vector vStudentTemp) {
        TableModel tm;
        if (vStudentTemp != null) {
            tm = new TableModel(cola, vStudentTemp.size());

            for (int i = 0; i < vStudentTemp.size(); i++) {
                Student student = (Student) vStudentTemp.get(i);
                tm.setValueAt(student.student_firstname + " " + student.student_surname, i, 0);
            }
            jButtonAddStu.setEnabled(true);
            jButtonDelStu.setEnabled(true);
        } else {
            tm = new TableModel(cola, 0);
            //กรณีที่ไม่มีข้อมูลให้ clear ข้อมูลในหน้า GUI ให้หมด
            clearGUIStudentDetail();
            Constant.println("------------- in  setTableListStudent vStudentTemp == null");
            promptSaveStudent();
        }
        jTableStudentService.setModel(tm);
        setPatternShowTable(jTableStudentService);
    }
    /**
     *
     * นำข้อมูลจาก School และ  CheckStudentHealth Object  มาแสดงผลในตาราง
     * @param vStudentTemp
     * @param vCheckStudentHealthTemps
     */
    private void setTableListCheck() {
        TableModel tm = new TableModel(col_list, 12);
        Student student = theStudent;
        int i = 0;
        tm.setValueAt(String.valueOf(i + 1), i, 0);
        tm.setValueAt(HealthSchoolServiceControl.CHECK_LIST[i], i, 1);
        if(vc == null)
            return;
        if(vc[i] != null) {
            CheckStudentHealth checkStudentHealth = (CheckStudentHealth) vc[i].get(0);
            tm.setValueAt(checkStudentHealth.f_answer_id, i, 2);
            tm.setValueAt(tableHealth.get(checkStudentHealth.f_answer_check_student_health_id), i, 3);
        }
        i++;
        tm.setValueAt(String.valueOf(i + 1), i, 0);
        tm.setValueAt(HealthSchoolServiceControl.CHECK_LIST[i], i, 1);
        if (vc[i] != null) {
            CheckEyes checkEyes = (CheckEyes) vc[i].get(0);
            tm.setValueAt(checkEyes.f_answer_id, i, 2);
            tm.setValueAt(theAllComboBoxControl.getValueOfCheckResult(checkEyes.f_health_check_eyes_id), i, 3);
        }
        i++;
        tm.setValueAt(String.valueOf(i + 1), i, 0);
        tm.setValueAt(HealthSchoolServiceControl.CHECK_LIST[i], i, 1);
        if (vc[i] != null) {
            CheckEars checkEars = (CheckEars) vc[i].get(0);
            tm.setValueAt(checkEars.f_answer_id, i, 2);
            tm.setValueAt(theAllComboBoxControl.getValueOfCheckResult(checkEars.f_health_check_ears_id), i, 3);
        }
        i++;
        tm.setValueAt(String.valueOf(i + 1), i, 0);
        tm.setValueAt(HealthSchoolServiceControl.CHECK_LIST[i], i, 1);
        if (vc[i] != null) {
            CheckNutrition checkNutrition = (CheckNutrition) vc[i].get(0);
            tm.setValueAt(checkNutrition.f_answer_id, i, 2);
            tm.setValueAt(theAllComboBoxControl.getValueOfNutritionType(checkNutrition.f_health_nutrition_level_id), i, 3);
        }
        i++;
        tm.setValueAt(String.valueOf(i + 1), i, 0);
        tm.setValueAt(HealthSchoolServiceControl.CHECK_LIST[i], i, 1);
        if (vc[i] != null) {
            CheckGoiter checkGoiter = (CheckGoiter) vc[i].get(0);
            tm.setValueAt(checkGoiter.f_answer_id, i, 2);
            tm.setValueAt(tableNormal.get(checkGoiter.f_answer_check_goiter_id), i, 3);
        }
        i++;
        tm.setValueAt(String.valueOf(i + 1), i, 0);
        tm.setValueAt(HealthSchoolServiceControl.CHECK_LIST[i], i, 1);
        if (vc[i] != null) {
            CheckFeBlood checkFeBlood = (CheckFeBlood) vc[i].get(0);
            tm.setValueAt(checkFeBlood.f_answer_id, i, 2);
            tm.setValueAt(tableNormal.get(checkFeBlood.f_answer_check_fe_blood_id), i, 3);
        }
        i++;
        tm.setValueAt(String.valueOf(i + 1), i, 0);
        tm.setValueAt(HealthSchoolServiceControl.CHECK_LIST[i], i, 1);
        if (vc[i] != null) {
            CheckStudentDental checkStudentDental = (CheckStudentDental) vc[i].get(0);
            tm.setValueAt(checkStudentDental.f_answer_id, i, 2);
            tm.setValueAt(this.theAllComboBoxControl.getValueOfGumLevel(checkStudentDental.f_health_gum_level_id), i, 3);
        }
        i++;
        tm.setValueAt(String.valueOf(i + 1), i, 0);
        tm.setValueAt(HealthSchoolServiceControl.CHECK_LIST[i], i, 1);
        if (vc[i] != null) {
            CheckWorm checkWorm = (CheckWorm) vc[i].get(0);
            tm.setValueAt(checkWorm.f_answer_id, i, 2);
            tm.setValueAt(tableSee.get(checkWorm.f_answer_check_worm_id), i, 3);
        }
        i++;
        tm.setValueAt(String.valueOf(i + 1), i, 0);
        tm.setValueAt(HealthSchoolServiceControl.CHECK_LIST[i], i, 1);
        if (vc[i] != null) {
            CheckThalassemia checkThalassemia = (CheckThalassemia) vc[i].get(0);
            tm.setValueAt(checkThalassemia.f_answer_id, i, 2);
            tm.setValueAt(tableNormal.get(checkThalassemia.f_answer_check_thalassemia_id), i, 3);
        }
        i++;
        tm.setValueAt(String.valueOf(i + 1), i, 0);
        tm.setValueAt(HealthSchoolServiceControl.CHECK_LIST[i], i, 1);
        if (vc[i] != null) {
            CheckLice checkLice = (CheckLice) vc[i].get(0);
            tm.setValueAt(checkLice.f_answer_id, i, 2);
            tm.setValueAt(tableSee.get(checkLice.f_answer_check_lice_id), i, 3);
        }
        i++;
        tm.setValueAt(String.valueOf(i + 1), i, 0);
        tm.setValueAt(HealthSchoolServiceControl.CHECK_LIST[i], i, 1);
        if (vc[i] != null) {
            CheckBody checkBody = (CheckBody) vc[i].get(0);
            tm.setValueAt(checkBody.f_answer_id, i, 2);
            tm.setValueAt(tableNormal.get(checkBody.f_answer_check_body_id), i, 3);
        }
        i++;
        tm.setValueAt(String.valueOf(i + 1), i, 0);
        tm.setValueAt(HealthSchoolServiceControl.CHECK_LIST[i], i, 1);
        if (vc[i] != null) {
            CheckOther checkOther = (CheckOther) vc[i].get(0);
            tm.setValueAt(checkOther.f_answer_id, i, 2);
            tm.setValueAt(checkOther.check_other_note, i, 3);
        }
        jTableCheckStudentHealthDetail.setModel(tm);
        jTableCheckStudentHealthDetail.getColumnModel().getColumn(2).setCellRenderer(theCelRendererLabReferOut);
        jTableCheckStudentHealthDetail.getColumnModel().getColumn(0).setCellRenderer(getRendererCenter());
        jTableCheckStudentHealthDetail.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableCheckStudentHealthDetail.getColumnModel().getColumn(1).setPreferredWidth(400);
        jTableCheckStudentHealthDetail.getColumnModel().getColumn(2).setPreferredWidth(50);
        jTableCheckStudentHealthDetail.getColumnModel().getColumn(3).setPreferredWidth(200);
    }

    private void setShowDataCheckStudentHealth(boolean flag) {
        //การตรวจสุขภาพประจำปี
        if (!flag) {
            jTextAreaStudentHealthNote.setText("");
            jTextAreaStudentHealthRemark.setText("");
            jTextFieldCheckStudentHealthReferOut.setText("");
            integerTextFieldCheckStudentHealthReferOut.setText("");
            panelYesNoStudentHealthStatus.setSelected("0");
            doubleTextFieldStudentHealthTall.setText("");
            doubleTextFieldStudentHealthWeight.setText("");
            jTextAreaStudentHealthNote.setText("");
            jTextAreaStudentHealthRemark.setText("");
            integerTextFieldCheckStudentHealthReferOut.setText("");
            jTextFieldCheckStudentHealthReferOut.setText("");
        }
        jTextAreaStudentHealthNote.setEnabled(flag);
        jTextAreaStudentHealthRemark.setEnabled(flag);
        panelYesNoStudentHealthResultCheck.setEnableRadioButton(flag);
        panelYesNoStudentHealthStatus.setEnableRadioButton(flag);
        jButtonSaveCheckStudentHealth.setEnabled(flag);
        jButtonCheckStudentHealthReferOut.setEnabled(flag);
        integerTextFieldCheckStudentHealthReferOut.setEnabled(flag);

    }

    private void setShowDataCheckEye(boolean flag) {
        if (!flag) {
            jTextAreaEyeNote.setText("");
            jTextAreaEyeRemark.setText("");
            integerTextFieldCheckEyeReferOut.setText("");
            jTextFieldCheckEyeReferOut.setText("");
        }
        jTextAreaEyeNote.setEnabled(flag);
        jTextAreaEyeRemark.setEnabled(flag);
        integerTextFieldCheckEyeReferOut.setEnabled(flag);
        jTextFieldCheckEyeReferOut.setEnabled(flag);
        jButtonSaveCheckEye.setEnabled(flag);
        panelYesNoCheckEye.setEnableRadioButton(flag);
        jButtonCheckEyeReferOut.setEnabled(flag);
        jComboBoxResultCheck.setEnabled(flag);
    }

    private void setShowDataCheckEar(boolean flag) {
        if (!flag) {
            integerTextFieldCheckEarReferOut.setText("");
            jTextFieldCheckEarReferOut.setText("");
            jTextAreaEarNote.setText("");
            jTextAreaEarRemark.setText("");
        }
        integerTextFieldCheckEarReferOut.setEnabled(flag);
        jTextFieldCheckEarReferOut.setEnabled(flag);
        jButtonCheckEarReferOut.setEnabled(flag);
        jTextAreaEarNote.setEnabled(flag);
        jTextAreaEarRemark.setEnabled(flag);
        jButtonSaveCheckEar.setEnabled(flag);
        panelYesNoCheckEar.setEnableRadioButton(flag);
        jComboBoxEarResultCheck.setEnabled(flag);
    }

    private void setShowDataNutrition(boolean flag) {
        if (!flag) {
            integerTextFieldCheckNutritionReferOut.setText("");
            jTextFieldCheckNutritionReferOut.setText("");
            jTextAreaNutritionNote.setText("");
            jTextAreaNutritionRemark.setText("");
        }
        jButtonSaveCheckNutrition.setEnabled(flag);
        jButtonCheckNutritionReferOut.setEnabled(flag);
        jTextAreaNutritionRemark.setEnabled(flag);
        jTextAreaNutritionNote.setEnabled(flag);
        jTextFieldCheckNutritionReferOut.setEnabled(flag);
        integerTextFieldCheckNutritionReferOut.setEnabled(flag);
        panelYesNoCheckNutrition.setEnableRadioButton(flag);

        jComboBoxNutritionResultCheck.setEnabled(flag);
    }

    private void setShowDataGoiter(boolean flag) {
        if (!flag) {
            integerTextFieldCheckGoiterReferOut.setText("");
            jTextFieldCheckGoiterReferOut.setText("");
            jTextAreaGoiterNote.setText("");
            jTextAreaGoiterRemark.setText("");
        }
        jButtonSaveCheckGoiter.setEnabled(flag);
        jButtonCheckGoiterReferOut.setEnabled(flag);
        jTextAreaGoiterRemark.setEnabled(flag);
        jTextAreaGoiterNote.setEnabled(flag);
        jTextFieldCheckGoiterReferOut.setEnabled(flag);
        integerTextFieldCheckGoiterReferOut.setEnabled(flag);
        panelYesNoCheckGoiter.setEnableRadioButton(flag);
        panelYesNoGoiterResult.setEnableRadioButton(flag);
    }

    private void setShowDataFeBlood(boolean flag) {
        if (!flag) {
            integerTextFieldCheckFeBloodReferOut.setText("");
            jTextFieldCheckFeBloodReferOut.setText("");
            jTextAreaFeBloodNote.setText("");
            jTextAreaFeBloodRemark.setText("");
        }
        jButtonSaveCheckFeBlood.setEnabled(flag);
        jButtonCheckFeBloodReferOut.setEnabled(flag);
        jTextAreaFeBloodRemark.setEnabled(flag);
        jTextAreaFeBloodNote.setEnabled(flag);
        jTextFieldCheckFeBloodReferOut.setEnabled(flag);
        integerTextFieldCheckFeBloodReferOut.setEnabled(flag);
        panelYesNoCheckFeBlood.setEnableRadioButton(flag);
        panelYesNoFeBloodResult.setEnableRadioButton(flag);
    }

    private void setShowDataCheckWorm(boolean flag) {
        if (!flag) {
            integerTextFieldCheckWormReferOut.setText("");
            jTextFieldCheckWormReferOut.setText("");
            jTextAreaWormNote.setText("");
            jTextAreaWormRemark.setText("");
        }
        jButtonSaveCheckWorm.setEnabled(flag);
        jButtonCheckWormReferOut.setEnabled(flag);
        jTextAreaWormRemark.setEnabled(flag);
        jTextAreaWormNote.setEnabled(flag);
        jTextFieldCheckWormReferOut.setEnabled(flag);
        integerTextFieldCheckWormReferOut.setEnabled(flag);
        panelYesNoCheckWorm.setEnableRadioButton(flag);
        panelYesNoCheckWormResult.setEnableRadioButton(flag);
    }

    private void setShowDataStudentDental(boolean flag) {
        if (!flag) {
            integerTextFieldCheckStudentDentalReferOut.setText("");
            jTextFieldCheckStudentDentalReferOut.setText("");
            jTextAreaStudentDentalNote.setText("");
            jTextAreaStudentDentalRemark.setText("");
            integerTextFieldMilkTooth.setText("");
            integerTextFieldBadMilkTooth.setText("");
            integerTextFieldRealTooth.setText("");
            integerTextFieldBadRealTooth.setText("");

        }
        jButtonSaveCheckStudentDental.setEnabled(flag);
        jButtonCheckStudentDentalReferOut.setEnabled(flag);
        jTextAreaStudentDentalRemark.setEnabled(flag);
        jTextAreaStudentDentalNote.setEnabled(flag);
        jTextFieldCheckStudentDentalReferOut.setEnabled(flag);
        integerTextFieldCheckStudentDentalReferOut.setEnabled(flag);
        panelYesNoCheckDental.setEnableRadioButton(flag);
        jComboBoxStudentDentalResultCheck.setEnabled(flag);
        integerTextFieldMilkTooth.setEnabled(flag);
        integerTextFieldBadMilkTooth.setEnabled(flag);
        integerTextFieldRealTooth.setEnabled(flag);
        integerTextFieldBadRealTooth.setEnabled(flag);
    }

    private void setShowDataThalassemia(boolean flag) {
        if (!flag) {
            integerTextFieldCheckThalassemiaReferOut.setText("");
            jTextFieldCheckThalassemiaReferOut.setText("");
            jTextAreaThalassemiaNote.setText("");
            jTextAreaThalassemiaRemark.setText("");
        }
        jButtonSaveCheckThalassemia.setEnabled(flag);
        jButtonCheckThalassemiaReferOut.setEnabled(flag);
        jTextAreaThalassemiaRemark.setEnabled(flag);
        jTextAreaThalassemiaNote.setEnabled(flag);
        jTextFieldCheckThalassemiaReferOut.setEnabled(flag);
        integerTextFieldCheckThalassemiaReferOut.setEnabled(flag);
        panelYesNoCheckThalassemia.setEnableRadioButton(flag);
        panelYesNoCheckThalassemiaResult.setEnableRadioButton(flag);

    }

    private void setShowDataCheckLice(boolean flag) {
        if (!flag) {
            integerTextFieldCheckLiceReferOut.setText("");
            jTextFieldCheckLiceReferOut.setText("");
            jTextAreaLiceNote.setText("");
            jTextAreaLiceRemark.setText("");
        }
        jButtonSaveCheckLice.setEnabled(flag);
        jButtonCheckLiceReferOut.setEnabled(flag);
        jTextAreaLiceRemark.setEnabled(flag);
        jTextAreaLiceNote.setEnabled(flag);
        jTextFieldCheckLiceReferOut.setEnabled(flag);
        integerTextFieldCheckLiceReferOut.setEnabled(flag);
        panelYesNoCheckLice.setEnableRadioButton(flag);
        panelYesNoCheckLiceResultCheck.setEnableRadioButton(flag);
    }

    private void setShowDataCheckBody(boolean flag) {
        if (!flag) {
            integerTextFieldCheckBodyReferOut.setText("");
            jTextFieldCheckBodyReferOut.setText("");
            jTextAreaBodyNote.setText("");
            jTextAreaBodyRemark.setText("");
        }
        jButtonSaveCheckBody.setEnabled(flag);
        jButtonCheckBodyReferOut.setEnabled(flag);
        jTextAreaBodyRemark.setEnabled(flag);
        jTextAreaBodyNote.setEnabled(flag);
        jTextFieldCheckBodyReferOut.setEnabled(flag);
        integerTextFieldCheckBodyReferOut.setEnabled(flag);
        panelYesNoCheckBody.setEnableRadioButton(flag);
        panelYesNoCheckBodyResult.setEnableRadioButton(flag);
    }

    private void setShowDataCheckOther(boolean flag) {
        if (!flag) {
            integerTextFieldCheckOtherReferOut.setText("");
            jTextFieldCheckOtherReferOut.setText("");
            jTextAreaOtherNote.setText("");
            jTextAreaOtherRemark.setText("");
        }
        jButtonSaveCheckOther.setEnabled(flag);
        jButtonCheckOtherReferOut.setEnabled(flag);
        jTextAreaOtherRemark.setEnabled(flag);
        jTextAreaOtherNote.setEnabled(flag);
        jTextFieldCheckOtherReferOut.setEnabled(flag);
        integerTextFieldCheckOtherReferOut.setEnabled(flag);
        panelYesNoCheckOther.setEnableRadioButton(flag);
    }

    /*นำข้อมูลจาก VisitSchool Object มาแสดงผลในตาราง*/
    private void setTableListVisitSchool(Vector vc) {
        vVisitSchool = vc;
        String[] col = {
            GutilPCU.getTextBundle("Class"),
            GutilPCU.getTextBundle("Room")};
        TableModel tm;
        if (vc != null) {
            tm = new TableModel(col, vc.size());
            VisitSchool visitSchool = new VisitSchool();
            Vector tempSchool = null;
            for (int i = 0; i < vc.size(); i++) {
//                        tm.setValueAt(String.valueOf((i+1)),i,0);
                visitSchool = (VisitSchool) vc.get(i);
//                        tempSchool = this.theVillageControl.selectSchoolByPK(visitSchool.t_health_school_id);
//                        if(tempSchool != null){
//                            tm.setValueAt(((School)tempSchool.elementAt(0)).school_name,i,1);
//                        }else{
//                            tm.setValueAt("",i,1);
//                        }
                tm.setValueAt(theAllComboBoxControl.getValueOfSchoolClass(visitSchool.b_school_class_id), i, 0);
                tm.setValueAt(visitSchool.visit_school_room, i, 1);
//                        tm.setValueAt(ComboboxModel.getDescriptionComboBox(this.jComboBoxSchoolServiceType,visitSchool.f_health_school_service_type_id),i,4);
//                        tm.setValueAt(GutilPCU.changDateToString(visitSchool.visit_school_service_date,false),i,5);
            }
            setSchoolFromEnable(true);
        } else {
            tm = new TableModel(col, 0);
            Constant.println("setTableListVisitSchool == null");
            promptSaveVisitSchool();
        }
        jTableSchoolDetail.setModel(tm);
        setPatternShowTable(jTableSchoolDetail);

    }

    /*นำข้อมูลจาก GUI มา set ค่าให้ VisitSchool Object*/
    private void getVisitSchool() {
        this.theVisitSchool.t_health_school_id = ComboboxModel.getCodeComboBox(jComboBoxSchoolName);
        this.theVisitSchool.b_school_class_id = ComboboxModel.getCodeComboBox(jComboBoxClass);
        this.theVisitSchool.visit_school_room = this.jTextFieldRoom.getText();
        this.theVisitSchool.f_health_school_service_type_id = ComboboxModel.getCodeComboBox(jComboBoxSchoolServiceType);
        this.theVisitSchool.visit_school_term = this.jTextFieldSchoolYear.getText();
        this.theVisitSchool.visit_school_service_date = this.dateComboBoxServiceDate.getText();
        this.theVisitSchool.visit_school_other_service = this.jTextFieldServiceOther.getText();
        this.theVisitSchool.visit_school_active = "1";
        if (this.theVisitSchool.getObjectId() != null) {
            this.theVisitSchool.visit_school_staff_modify = theEmployee.getObjectId();
            this.theVisitSchool.visit_school_modify_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        } else {
            this.theVisitSchool.visit_school_staff_record = theEmployee.getObjectId();
            this.theVisitSchool.visit_school_record_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        }
    }

    /*นำข้อมูลจาก GUI มา set ค่าให้ Student Object*/
    private void getStudent() {
        this.theStudent.f_patient_prefix_id = ComboboxModel.getCodeComboBox(jComboBoxPrefixStu);
        this.theStudent.f_sex_id = ComboboxModel.getCodeComboBox(jComboBoxStuSex);
        this.theStudent.student_firstname = this.jTextFieldStuName.getText();
        this.theStudent.student_surname = this.jTextFieldStuSurName.getText();
        this.theStudent.student_number = this.jTextFieldStuNumber.getText();
        this.theStudent.t_health_visit_school_id = this.theVisitSchool.getObjectId();
        this.theStudent.student_pid = this.pIDPanel.getText();
        this.theStudent.student_note = this.jTextAreaStudentNote.getText();
    }

    /*นำข้อมูลจาก GUI มา set ค่าให้ CheckStudentHealth Object*/
    private CheckStudentHealth getCheckStudentHealth() {
        this.theCheckStudentHealth.t_health_student_id = this.theStudent.getObjectId();
        this.theCheckStudentHealth.t_health_visit_school_id = theVisitSchool.getObjectId();
        this.theCheckStudentHealth.f_answer_id = panelYesNoStudentHealthStatus.getStringSelect();
        this.theCheckStudentHealth.f_answer_check_student_health_id = this.theCheckStudentHealth.f_answer_id.equals("0") ? "" : panelYesNoStudentHealthResultCheck.getStringSelect();
        this.theCheckStudentHealth.check_student_health_tall = this.doubleTextFieldStudentHealthTall.getText();
        this.theCheckStudentHealth.check_student_health_weight = this.doubleTextFieldStudentHealthWeight.getText();
        if (jTextFieldCheckStudentHealthReferOut.getText().equals("")) {
            this.theCheckStudentHealth.b_visit_refer_office_id = "";
        }
        this.theCheckStudentHealth.check_student_health_note = this.jTextAreaStudentHealthNote.getText();
        this.theCheckStudentHealth.check_student_health_remark = this.jTextAreaStudentHealthRemark.getText();
        this.theCheckStudentHealth.check_student_health_active = "1";
        if (this.theCheckStudentHealth.getObjectId() != null) {
            this.theCheckStudentHealth.check_student_health_staff_modify = theEmployee.getObjectId();
            this.theCheckStudentHealth.check_student_health_modify_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        } else {
            this.theCheckStudentHealth.check_student_health_staff_record = theEmployee.getObjectId();
            this.theCheckStudentHealth.check_student_health_record_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        }
        return theCheckStudentHealth;
    }

    /*นำข้อมูลจาก GUI มา set ค่าให้ CheckEyes Object*/
    private CheckEyes getCheckEyes() {
        this.theCheckEyes.t_health_student_id = this.theStudent.getObjectId();
        this.theCheckEyes.t_health_visit_school_id = theVisitSchool.getObjectId();
        this.theCheckEyes.f_answer_id = panelYesNoCheckEye.getStringSelect();
        this.theCheckEyes.f_health_check_eyes_id = this.theCheckEyes.f_answer_id.equals("0") ? "" : ComboboxModel.getCodeComboBox(jComboBoxResultCheck);
        if (jTextFieldCheckEyeReferOut.getText().equals("")) {
            this.theCheckEyes.b_visit_refer_office_id = "";
        }
        this.theCheckEyes.check_eyes_note = this.jTextAreaEyeNote.getText();
        this.theCheckEyes.check_eyes_remark = this.jTextAreaEyeRemark.getText();
        this.theCheckEyes.check_eyes_active = "1";
        if (this.theCheckEyes.getObjectId() != null) {
            this.theCheckEyes.check_eyes_staff_modify = theEmployee.getObjectId();
            this.theCheckEyes.check_eyes_modify_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        } else {
            this.theCheckEyes.check_eyes_staff_record = theEmployee.getObjectId();
            this.theCheckEyes.check_eyes_record_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        }
        return theCheckEyes;
    }

    private CheckEars getCheckEars() {
        this.theCheckEars.t_health_student_id = this.theStudent.getObjectId();
        this.theCheckEars.t_health_visit_school_id = theVisitSchool.getObjectId();
        this.theCheckEars.f_answer_id = panelYesNoCheckEar.getStringSelect();

        this.theCheckEars.f_health_check_ears_id = this.theCheckEars.f_answer_id.equals("0") ? "" : ComboboxModel.getCodeComboBox(jComboBoxEarResultCheck);
        if (jTextFieldCheckEarReferOut.getText().equals("")) {
            this.theCheckEars.b_visit_refer_office_id = "";
        }
        this.theCheckEars.check_ears_note = this.jTextAreaEarNote.getText();
        this.theCheckEars.check_ears_remark = this.jTextAreaEarRemark.getText();
        this.theCheckEars.check_ears_active = "1";
        if (this.theCheckEars.getObjectId() != null) {
            this.theCheckEars.check_ears_staff_modify = theEmployee.getObjectId();
            this.theCheckEars.check_ears_modify_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        } else {
            this.theCheckEars.check_ears_staff_record = theEmployee.getObjectId();
            this.theCheckEars.check_ears_record_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        }
        return theCheckEars;
    }

    private CheckNutrition getCheckNutrition() {
        this.theCheckNutrition.t_health_student_id = this.theStudent.getObjectId();
        this.theCheckNutrition.t_health_visit_school_id = theVisitSchool.getObjectId();
        this.theCheckNutrition.f_answer_id = panelYesNoCheckNutrition.getStringSelect();

        this.theCheckNutrition.f_health_nutrition_level_id = this.theCheckNutrition.f_answer_id.equals("0") ? "" : ComboboxModel.getCodeComboBox(jComboBoxNutritionResultCheck);
        if (jTextFieldCheckNutritionReferOut.getText().equals("")) {
            this.theCheckNutrition.b_visit_refer_office_id = "";
        }
        this.theCheckNutrition.check_nutrition_note = this.jTextAreaNutritionNote.getText();
        this.theCheckNutrition.check_nutrition_remark = this.jTextAreaNutritionRemark.getText();
        this.theCheckNutrition.check_nutrition_active = "1";
        if (this.theCheckNutrition.getObjectId() != null) {
            this.theCheckNutrition.check_nutrition_staff_modify = theEmployee.getObjectId();
            this.theCheckNutrition.check_nutrition_modify_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        } else {
            this.theCheckNutrition.check_nutrition_staff_record = theEmployee.getObjectId();
            this.theCheckNutrition.check_nutrition_record_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        }
        return theCheckNutrition;
    }

    private CheckGoiter getCheckGoiter() {
        this.theCheckGoiter.t_health_student_id = this.theStudent.getObjectId();
        this.theCheckGoiter.t_health_visit_school_id = theVisitSchool.getObjectId();
        this.theCheckGoiter.f_answer_id = panelYesNoCheckGoiter.getStringSelect();
        this.theCheckGoiter.f_answer_check_goiter_id = this.theCheckGoiter.f_answer_id.equals("0") ? "" : panelYesNoGoiterResult.getStringSelect();
        if (jTextFieldCheckGoiterReferOut.getText().equals("")) {
            this.theCheckGoiter.b_visit_refer_office_id = "";
        }
        this.theCheckGoiter.check_goiter_note = this.jTextAreaGoiterNote.getText();
        this.theCheckGoiter.check_goiter_remark = this.jTextAreaGoiterRemark.getText();
        this.theCheckGoiter.check_goiter_active = "1";
        if (this.theCheckGoiter.getObjectId() != null) {
            this.theCheckGoiter.check_goiter_staff_modify = theEmployee.getObjectId();
            this.theCheckGoiter.check_goiter_modify_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        } else {
            this.theCheckGoiter.check_goiter_staff_record = theEmployee.getObjectId();
            this.theCheckGoiter.check_goiter_record_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        }
        return theCheckGoiter;
    }

    private CheckFeBlood getCheckFeBlood() {
        this.theCheckFeBlood.t_health_student_id = this.theStudent.getObjectId();
        this.theCheckFeBlood.t_health_visit_school_id = theVisitSchool.getObjectId();
        this.theCheckFeBlood.f_answer_id = panelYesNoCheckFeBlood.getStringSelect();
        this.theCheckFeBlood.f_answer_check_fe_blood_id = this.theCheckFeBlood.f_answer_id.equals("0") ? "" : panelYesNoFeBloodResult.getStringSelect();
        if (jTextFieldCheckFeBloodReferOut.getText().equals("")) {
            this.theCheckFeBlood.b_visit_refer_office_id = "";
        }
        this.theCheckFeBlood.check_fe_blood_note = this.jTextAreaFeBloodNote.getText();
        this.theCheckFeBlood.check_fe_blood_remark = this.jTextAreaFeBloodRemark.getText();
        this.theCheckFeBlood.check_fe_blood_active = "1";
        if (this.theCheckFeBlood.getObjectId() != null) {
            this.theCheckFeBlood.check_fe_blood_staff_modify = theEmployee.getObjectId();
            this.theCheckFeBlood.check_fe_blood_modify_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        } else {
            this.theCheckFeBlood.check_fe_blood_staff_record = theEmployee.getObjectId();
            this.theCheckFeBlood.check_fe_blood_record_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        }
        return theCheckFeBlood;
    }

    private CheckStudentDental getCheckStudentDental() {
        this.theCheckStudentDental.t_health_student_id = this.theStudent.getObjectId();
        this.theCheckStudentDental.t_health_visit_school_id = theVisitSchool.getObjectId();
        this.theCheckStudentDental.f_answer_id = panelYesNoCheckDental.getStringSelect();

        this.theCheckStudentDental.f_health_gum_level_id = this.theCheckStudentDental.f_answer_id.equals("0") ? "" : ComboboxModel.getCodeComboBox(jComboBoxStudentDentalResultCheck);
        if (jTextFieldCheckStudentDentalReferOut.getText().equals("")) {
            this.theCheckStudentDental.b_visit_refer_office_id = "";
        }
        this.theCheckStudentDental.check_student_dental_note = this.jTextAreaStudentDentalNote.getText();
        this.theCheckStudentDental.check_student_dental_remark = this.jTextAreaStudentDentalRemark.getText();

        this.theCheckStudentDental.check_student_dental_num_tooth = this.integerTextFieldRealTooth.getText();
        this.theCheckStudentDental.check_student_dental_num_milktooth = this.integerTextFieldMilkTooth.getText();
        this.theCheckStudentDental.check_student_dental_num_bad_tooth = this.integerTextFieldBadRealTooth.getText();
        this.theCheckStudentDental.check_student_dental_num_bad_milktooth = this.integerTextFieldBadMilkTooth.getText();

        this.theCheckStudentDental.check_student_dental_active = "1";
        if (this.theCheckStudentDental.getObjectId() != null) {
            this.theCheckStudentDental.check_student_dental_staff_modify = theEmployee.getObjectId();
            this.theCheckStudentDental.check_student_dental_modify_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        } else {
            this.theCheckStudentDental.check_student_dental_staff_record = theEmployee.getObjectId();
            this.theCheckStudentDental.check_student_dental_record_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        }
        return theCheckStudentDental;
    }

    private CheckWorm getCheckWorm() {
        this.theCheckWorm.t_health_student_id = this.theStudent.getObjectId();
        this.theCheckWorm.t_health_visit_school_id = theVisitSchool.getObjectId();
        this.theCheckWorm.f_answer_id = panelYesNoCheckWorm.getStringSelect();

        this.theCheckWorm.f_answer_check_worm_id = this.theCheckWorm.f_answer_id.equals("0") ? "" : panelYesNoCheckWormResult.getStringSelect();
        if (jTextFieldCheckWormReferOut.getText().equals("")) {
            this.theCheckWorm.b_visit_refer_office_id = "";
        }
        this.theCheckWorm.check_worm_note = this.jTextAreaWormNote.getText();
        this.theCheckWorm.check_worm_remark = this.jTextAreaWormRemark.getText();
        this.theCheckWorm.check_worm_active = "1";
        if (this.theCheckWorm.getObjectId() != null) {
            this.theCheckWorm.check_worm_staff_modify = theEmployee.getObjectId();
            this.theCheckWorm.check_worm_modify_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        } else {
            this.theCheckWorm.check_worm_staff_record = theEmployee.getObjectId();
            this.theCheckWorm.check_worm_record_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        }
        return theCheckWorm;
    }

    private CheckThalassemia getCheckThalassemia() {
        this.theCheckThalassemia.t_health_student_id = this.theStudent.getObjectId();
        this.theCheckThalassemia.t_health_visit_school_id = theVisitSchool.getObjectId();
        this.theCheckThalassemia.f_answer_id = panelYesNoCheckThalassemia.getStringSelect();
        this.theCheckThalassemia.f_answer_check_thalassemia_id = this.theCheckThalassemia.f_answer_id.equals("0") ? "" : panelYesNoCheckThalassemiaResult.getStringSelect();
        if (jTextFieldCheckThalassemiaReferOut.getText().equals("")) {
            this.theCheckThalassemia.b_visit_refer_office_id = "";
        }
        this.theCheckThalassemia.check_thalassemia_note = this.jTextAreaThalassemiaNote.getText();
        this.theCheckThalassemia.check_thalassemia_remark = this.jTextAreaThalassemiaRemark.getText();
        this.theCheckThalassemia.check_thalassemia_active = "1";
        if (this.theCheckThalassemia.getObjectId() != null) {
            this.theCheckThalassemia.check_thalassemia_staff_modify = theEmployee.getObjectId();
            this.theCheckThalassemia.check_thalassemia_modify_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        } else {
            this.theCheckThalassemia.check_thalassemia_staff_record = theEmployee.getObjectId();
            this.theCheckThalassemia.check_thalassemia_record_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        }
        return theCheckThalassemia;
    }

    private CheckLice getCheckLice() {
        this.theCheckLice.t_health_student_id = this.theStudent.getObjectId();
        this.theCheckLice.t_health_visit_school_id = theVisitSchool.getObjectId();
        this.theCheckLice.f_answer_id = panelYesNoCheckLice.getStringSelect();
        this.theCheckLice.f_answer_check_lice_id = this.theCheckLice.f_answer_id.equals("0") ? "" : panelYesNoCheckLiceResultCheck.getStringSelect();

        if (jTextFieldCheckLiceReferOut.getText().equals("")) {
            this.theCheckLice.b_visit_refer_office_id = "";
        }
        this.theCheckLice.check_lice_note = this.jTextAreaLiceNote.getText();
        this.theCheckLice.check_lice_remark = this.jTextAreaLiceRemark.getText();
        this.theCheckLice.check_lice_active = "1";
        if (this.theCheckLice.getObjectId() != null) {
            this.theCheckLice.check_lice_staff_modify = theEmployee.getObjectId();
            this.theCheckLice.check_lice_modify_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        } else {
            this.theCheckLice.check_lice_staff_record = theEmployee.getObjectId();
            this.theCheckLice.check_lice_record_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        }
        return theCheckLice;
    }

    private CheckBody getCheckBody() {
        this.theCheckBody.t_health_student_id = this.theStudent.getObjectId();
        this.theCheckBody.t_health_visit_school_id = theVisitSchool.getObjectId();
        this.theCheckBody.f_answer_id = panelYesNoCheckBody.getStringSelect();

        this.theCheckBody.f_answer_check_body_id = this.theCheckBody.f_answer_id.equals("0") ? "" : panelYesNoCheckBodyResult.getStringSelect();
        if (jTextFieldCheckBodyReferOut.getText().equals("")) {
            this.theCheckBody.b_visit_refer_office_id = "";
        }
        this.theCheckBody.check_body_note = this.jTextAreaBodyNote.getText();
        this.theCheckBody.check_body_remark = this.jTextAreaBodyRemark.getText();
        this.theCheckBody.check_body_active = "1";
        if (this.theCheckBody.getObjectId() != null) {
            this.theCheckBody.check_body_staff_modify = theEmployee.getObjectId();
            this.theCheckBody.check_body_modify_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        } else {
            this.theCheckBody.check_body_staff_record = theEmployee.getObjectId();
            this.theCheckBody.check_body_record_time = Gutil.getTextCurrentDateTime(this.theAllComboBoxControl.theConnectionInf);
        }
        return theCheckBody;
    }

    private CheckOther getCheckOther() {
        this.theCheckOther.t_health_student_id = this.theStudent.getObjectId();
        this.theCheckOther.t_health_visit_school_id = theVisitSchool.getObjectId();
        this.theCheckOther.f_answer_id = panelYesNoCheckOther.getStringSelect();
        if (jTextFieldCheckOtherReferOut.getText().equals("")) {
            this.theCheckOther.b_visit_refer_office_id = "";
        }
        this.theCheckOther.check_other_note = this.jTextAreaOtherNote.getText();
        this.theCheckOther.check_other_remark = this.jTextAreaOtherRemark.getText();
        return theCheckOther;
    }

    public DefaultTableCellRenderer getRendererCenter(){
        if (rendererCenter == null) {
            rendererCenter = new DefaultTableCellRenderer();
            rendererCenter.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        }
        return rendererCenter;
    }
    /*กำหนดรูปแบบของตารางของงานอนามัยโรงเรียนที่ต้องการแสดงผล*/
    private void setPatternShowTable(JTable table) {
        
        //กำหนดให้จัด Column ให้อยู่ตรงกลาง
        table.getColumnModel().getColumn(0).setCellRenderer(getRendererCenter());
        if (table != jTableStudentService && table != jTableSchoolDetail && table != jTableSchoolList) {
            table.getColumnModel().getColumn(0).setPreferredWidth(30);
            table.getColumnModel().getColumn(3).setCellRenderer(theCelRendererLabReferOut);
        } else if (table == jTableSchoolList) {
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(1).setPreferredWidth(150);
        }
    }

    public void showDialogOfficeReferOut(java.awt.event.ActionEvent evt) {
        Office theOffice = new Office();
        JButton thePushButton = (JButton) evt.getSource();

        if (theHosDialog.showDialogOffice(theHosManage, theOffice/*,thePCUObject*/)) {
            if (thePushButton.equals(jButtonCheckEyeReferOut)) {
                jTextFieldCheckEyeReferOut.setText(theOffice.getName());
                integerTextFieldCheckEyeReferOut.setText(theOffice.getCode());
                this.theCheckEyes.b_visit_refer_office_id = theOffice.getObjectId();
            } else if (thePushButton.equals(jButtonCheckEarReferOut)) {
                jTextFieldCheckEarReferOut.setText(theOffice.getName());
                integerTextFieldCheckEarReferOut.setText(theOffice.getCode());
                this.theCheckEars.b_visit_refer_office_id = theOffice.getObjectId();
            } else if (thePushButton.equals(jButtonCheckStudentHealthReferOut)) {
                jTextFieldCheckStudentHealthReferOut.setText(theOffice.getName());
                integerTextFieldCheckStudentHealthReferOut.setText(theOffice.getCode());
                this.theCheckStudentHealth.b_visit_refer_office_id = theOffice.getObjectId();
            } else if (thePushButton.equals(jButtonCheckNutritionReferOut)) {
                jTextFieldCheckNutritionReferOut.setText(theOffice.getName());
                integerTextFieldCheckNutritionReferOut.setText(theOffice.getCode());
                this.theCheckNutrition.b_visit_refer_office_id = theOffice.getObjectId();
            } else if (thePushButton.equals(jButtonCheckGoiterReferOut)) {
                jTextFieldCheckGoiterReferOut.setText(theOffice.getName());
                integerTextFieldCheckGoiterReferOut.setText(theOffice.getCode());
                this.theCheckGoiter.b_visit_refer_office_id = theOffice.getObjectId();
            } else if (thePushButton.equals(jButtonCheckFeBloodReferOut)) {
                jTextFieldCheckFeBloodReferOut.setText(theOffice.getName());
                integerTextFieldCheckFeBloodReferOut.setText(theOffice.getCode());
                this.theCheckFeBlood.b_visit_refer_office_id = theOffice.getObjectId();
            } else if (thePushButton.equals(jButtonCheckStudentDentalReferOut)) {
                jTextFieldCheckStudentDentalReferOut.setText(theOffice.getName());
                integerTextFieldCheckStudentDentalReferOut.setText(theOffice.getCode());
                this.theCheckStudentDental.b_visit_refer_office_id = theOffice.getObjectId();
            } else if (thePushButton.equals(jButtonCheckWormReferOut)) {
                jTextFieldCheckWormReferOut.setText(theOffice.getName());
                integerTextFieldCheckWormReferOut.setText(theOffice.getCode());
                this.theCheckWorm.b_visit_refer_office_id = theOffice.getObjectId();
            } else if (thePushButton.equals(jButtonCheckThalassemiaReferOut)) {
                jTextFieldCheckThalassemiaReferOut.setText(theOffice.getName());
                integerTextFieldCheckThalassemiaReferOut.setText(theOffice.getCode());
                this.theCheckThalassemia.b_visit_refer_office_id = theOffice.getObjectId();
            } else if (thePushButton.equals(jButtonCheckLiceReferOut)) {
                jTextFieldCheckLiceReferOut.setText(theOffice.getName());
                integerTextFieldCheckLiceReferOut.setText(theOffice.getCode());
                this.theCheckLice.b_visit_refer_office_id = theOffice.getObjectId();
            } else if (thePushButton.equals(jButtonCheckBodyReferOut)) {
                jTextFieldCheckBodyReferOut.setText(theOffice.getName());
                integerTextFieldCheckBodyReferOut.setText(theOffice.getCode());
                this.theCheckBody.b_visit_refer_office_id = theOffice.getObjectId();
            } else if (thePushButton.equals(jButtonCheckOtherReferOut)) {
                jTextFieldCheckOtherReferOut.setText(theOffice.getName());
                integerTextFieldCheckOtherReferOut.setText(theOffice.getCode());
                this.theCheckOther.b_visit_refer_office_id = theOffice.getObjectId();
            }
        }
    }

    public void setLanguage() {
        /*jLabel*/
        GutilPCU.setLanguage(col_list);
        GutilPCU.setLanguage(cola);
        GutilPCU.setLanguage(jLabel1);
        GutilPCU.setLanguage(jLabel2);
        GutilPCU.setLanguage(jLabel3);
        GutilPCU.setLanguage(jLabel4);
        GutilPCU.setLanguage(jLabel5);
        GutilPCU.setLanguage(jLabel6);
        GutilPCU.setLanguage(jLabel7);
        GutilPCU.setLanguage(jLabel8);
        GutilPCU.setLanguage(jLabel9);
        GutilPCU.setLanguage(jLabel10);
        GutilPCU.setLanguage(jLabel11);
        GutilPCU.setLanguage(jLabel12);
        GutilPCU.setLanguage(jLabel13);
        GutilPCU.setLanguage(jLabel14);
        GutilPCU.setLanguage(jLabel15);
        GutilPCU.setLanguage(jLabel16);
        GutilPCU.setLanguage(jLabel17);
        GutilPCU.setLanguage(jLabel18);
        GutilPCU.setLanguage(jLabel19);
        GutilPCU.setLanguage(jLabel20);
        GutilPCU.setLanguage(jLabel21);
        GutilPCU.setLanguage(jLabel22);
        GutilPCU.setLanguage(jLabel23);
        GutilPCU.setLanguage(jLabel24);
        jLabelSchoolNameService.setText(GutilPCU.getTextBundle(jLabelSchoolNameService.getText()));
        jLabelSchoolName.setText(GutilPCU.getTextBundle(jLabelSchoolName.getText()));
        jLabelClass.setText(GutilPCU.getTextBundle(jLabelClass.getText()));
        jLabelRoom.setText(GutilPCU.getTextBundle(jLabelRoom.getText()));
        jLabelSchoolYear.setText(GutilPCU.getTextBundle(jLabelSchoolYear.getText()));
        jLabelSchoolServiceDate.setText(GutilPCU.getTextBundle(jLabelSchoolServiceDate.getText()));
        jLabelSchoolSchoolType.setText(GutilPCU.getTextBundle(jLabelSchoolSchoolType.getText()));
        jLabelServiceNote.setText(GutilPCU.getTextBundle(jLabelServiceNote.getText()));
        jLableStatus.setText(GutilPCU.getTextBundle(jLableStatus.getText()));
        jLabelResultCheck.setText(GutilPCU.getTextBundle(jLabelResultCheck.getText()));
        jLabelSchoolClass.setText(GutilPCU.getTextBundle(jLabelSchoolClass.getText()));
        jLabelSchoolClass.setText(GutilPCU.getTextBundle(jLabelSchoolClass.getText()));
        jLabelSchoolRoom.setText(GutilPCU.getTextBundle(jLabelSchoolRoom.getText()));
        jLabelSchoolService.setText(GutilPCU.getTextBundle(jLabelSchoolService.getText()));
        jLableNameStu.setText(GutilPCU.getTextBundle(jLableNameStu.getText()));
        jLabelStuNumber2.setText(GutilPCU.getTextBundle(jLabelStuNumber2.getText()));
        jLabelStuNumber.setText(GutilPCU.getTextBundle(jLabelStuNumber.getText()));


        jLableStuPID.setText(GutilPCU.getTextBundle(jLableStuPID.getText()));
        jLableStuSex.setText(GutilPCU.getTextBundle(jLableStuSex.getText()));
        jLableEarStatus.setText(GutilPCU.getTextBundle(jLableEarStatus.getText()));
        jLabelEarResultCheck.setText(GutilPCU.getTextBundle(jLabelEarResultCheck.getText()));
        jLableCheckEyeReferOut.setText(GutilPCU.getTextBundle(jLableCheckEyeReferOut.getText()));
        jLableCheckEarReferOut.setText(GutilPCU.getTextBundle(jLableCheckEarReferOut.getText()));
        jLableNutritionStatus.setText(GutilPCU.getTextBundle(jLableNutritionStatus.getText()));
        jLabelNutritionResultCheck.setText(GutilPCU.getTextBundle(jLabelNutritionResultCheck.getText()));
        jLableNutritionReferOut.setText(GutilPCU.getTextBundle(jLableNutritionReferOut.getText()));

        jLableStudentHealthStatus.setText(GutilPCU.getTextBundle(jLableStudentHealthStatus.getText()));
        jLabelStudentHealthResultCheck.setText(GutilPCU.getTextBundle(jLabelStudentHealthResultCheck.getText()));
        jLableCheckStudentHealthReferOut.setText(GutilPCU.getTextBundle(jLableCheckStudentHealthReferOut.getText()));
        jLabelStudentHealthTall.setText(GutilPCU.getTextBundle(jLabelStudentHealthTall.getText()));
        jLabelStudentHealthWeight.setText(GutilPCU.getTextBundle(jLabelStudentHealthWeight.getText()));

        jLableGoiterStatus.setText(GutilPCU.getTextBundle(jLableGoiterStatus.getText()));
        jLabelGoiterResultCheck.setText(GutilPCU.getTextBundle(jLabelGoiterResultCheck.getText()));
        jLableGoiterReferOut.setText(GutilPCU.getTextBundle(jLableGoiterReferOut.getText()));

        jLableFeBloodStatus.setText(GutilPCU.getTextBundle(jLableFeBloodStatus.getText()));
        jLabelFeBloodResultCheck.setText(GutilPCU.getTextBundle(jLabelFeBloodResultCheck.getText()));
        jLableFeBloodReferOut.setText(GutilPCU.getTextBundle(jLableFeBloodReferOut.getText()));

        jLableStudentDentalStatus.setText(GutilPCU.getTextBundle(jLableStudentDentalStatus.getText()));
        jLabelStudentDentalResultCheck.setText(GutilPCU.getTextBundle(jLabelStudentDentalResultCheck.getText()));
        jLableStudentDentalReferOut.setText(GutilPCU.getTextBundle(jLableStudentDentalReferOut.getText()));


        jLabelRealTooth.setText(GutilPCU.getTextBundle(jLabelRealTooth.getText()));
        jLabelBadRealTooth.setText(GutilPCU.getTextBundle(jLabelBadRealTooth.getText()));
        jLabelMilkTooth.setText(GutilPCU.getTextBundle(jLabelMilkTooth.getText()));
        jLabelBadMilkTooth.setText(GutilPCU.getTextBundle(jLabelBadMilkTooth.getText()));

        jLableWormStatus.setText(GutilPCU.getTextBundle(jLableWormStatus.getText()));
        jLabelWormResultCheck.setText(GutilPCU.getTextBundle(jLabelWormResultCheck.getText()));
        jLableWormReferOut.setText(GutilPCU.getTextBundle(jLableWormReferOut.getText()));


        jLableThalassemiaStatus.setText(GutilPCU.getTextBundle(jLableThalassemiaStatus.getText()));
        jLabelThalassemiaResultCheck.setText(GutilPCU.getTextBundle(jLabelThalassemiaResultCheck.getText()));
        jLableThalassemiaReferOut.setText(GutilPCU.getTextBundle(jLableThalassemiaReferOut.getText()));

        jLableLiceStatus.setText(GutilPCU.getTextBundle(jLableLiceStatus.getText()));
        jLabelLiceResultCheck.setText(GutilPCU.getTextBundle(jLabelLiceResultCheck.getText()));
        jLableLiceReferOut.setText(GutilPCU.getTextBundle(jLableLiceReferOut.getText()));

        jLableOtherStatus.setText(GutilPCU.getTextBundle(jLableOtherStatus.getText()));
        jLableOtherReferOut.setText(GutilPCU.getTextBundle(jLableOtherReferOut.getText()));

        jLableBodyStatus.setText(GutilPCU.getTextBundle(jLableBodyStatus.getText()));
        jLabelBodyResultCheck.setText(GutilPCU.getTextBundle(jLabelBodyResultCheck.getText()));
        jLableBodyReferOut.setText(GutilPCU.getTextBundle(jLableBodyReferOut.getText()));

        jLableStuSex1.setText(GutilPCU.getTextBundle(jLableStuSex1.getText()));

        /*jButton*/
        jButtonAddSchool.setText(GutilPCU.getTextBundle(jButtonAddSchool.getText()));
        jButtonDelSchool.setText(GutilPCU.getTextBundle(jButtonDelSchool.getText()));
        jButtonSaveSchool.setText(GutilPCU.getTextBundle(jButtonSaveSchool.getText()));
        jButtonSearch.setText(GutilPCU.getTextBundle(jButtonSearch.getText()));
        jButtonSaveCheckEye.setText(GutilPCU.getTextBundle(jButtonSaveCheckEye.getText()));
        jButtonAddStu.setText(GutilPCU.getTextBundle(jButtonAddStu.getText()));
        jButtonDelStu.setText(GutilPCU.getTextBundle(jButtonDelStu.getText()));
        jButtonSaveStu.setText(GutilPCU.getTextBundle(jButtonSaveStu.getText()));
        jButtonSaveCheckEar.setText(GutilPCU.getTextBundle(jButtonSaveCheckEar.getText()));
        jButtonSaveCheckNutrition.setText(GutilPCU.getTextBundle(jButtonSaveCheckNutrition.getText()));
        jButtonSaveCheckStudentHealth.setText(GutilPCU.getTextBundle(jButtonSaveCheckStudentHealth.getText()));
        jButtonSaveCheckGoiter.setText(GutilPCU.getTextBundle(jButtonSaveCheckGoiter.getText()));
        jButtonSaveCheckFeBlood.setText(GutilPCU.getTextBundle(jButtonSaveCheckFeBlood.getText()));
        jButtonSaveCheckStudentDental.setText(GutilPCU.getTextBundle(jButtonSaveCheckStudentDental.getText()));
        jButtonSaveCheckWorm.setText(GutilPCU.getTextBundle(jButtonSaveCheckWorm.getText()));
        jButtonSaveCheckThalassemia.setText(GutilPCU.getTextBundle(jButtonSaveCheckThalassemia.getText()));
        jButtonSaveCheckLice.setText(GutilPCU.getTextBundle(jButtonSaveCheckLice.getText()));
        jButtonSaveCheckBody.setText(GutilPCU.getTextBundle(jButtonSaveCheckBody.getText()));
        jButtonSaveCheckOther.setText(GutilPCU.getTextBundle(jButtonSaveCheckOther.getText()));
        jButtonSearchFamily.setText(GutilPCU.getTextBundle(jButtonSearchFamily.getText()));


        /*TitledBorder*/
        GutilPCU.JPanelLabler(jPanelShowSchoolData);
        GutilPCU.JPanelLabler(jPanelCheckEyeDetail);
        GutilPCU.JPanelLabler(jPanelSearchSchool);
        GutilPCU.JPanelLabler(jPanelSchoolDetail);
        GutilPCU.JPanelLabler(jPanelSubSchoolDetail);
        GutilPCU.JPanelLabler(jPanelCheckEyeDetail);
        GutilPCU.JPanelLabler(jPanelStudentSubDetail);
        GutilPCU.JPanelLabler(jPanelCheckEarDetail);
        GutilPCU.JPanelLabler(jPanelCheckNutritionDetail);


        GutilPCU.JPanelLabler(jPanelCheckNutritionDetail);


        GutilPCU.JPanelLabler(jPanelCheckStudentHealthDetail);

        GutilPCU.JPanelLabler(jPanelCheckGoiterDetail);

        GutilPCU.JPanelLabler(jPanelCheckFeBloodDetail);

        GutilPCU.JPanelLabler(jPanelCheckStudentDentalDetail);


        GutilPCU.JPanelLabler(jPanelCheckWormDetail);

        GutilPCU.JPanelLabler(jPanelCheckThalassemiaDetail);


        GutilPCU.JPanelLabler(jPanelCheckLiceDetail);

        GutilPCU.JPanelLabler(jPanelCheckBodyDetail);

        GutilPCU.JPanelLabler(jPanelCheckOtherDetail);


    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.pcu.utility.DateComboBox dateComboBoxServiceDate;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.pcu.utility.DoubleTextField doubleTextFieldStudentHealthTall;
    private com.pcu.utility.DoubleTextField doubleTextFieldStudentHealthWeight;
    private com.pcu.utility.IntegerTextField integerTextFieldBadMilkTooth;
    private com.pcu.utility.IntegerTextField integerTextFieldBadRealTooth;
    private com.pcu.utility.IntegerTextField integerTextFieldCheckBodyReferOut;
    private com.pcu.utility.IntegerTextField integerTextFieldCheckEarReferOut;
    private com.pcu.utility.IntegerTextField integerTextFieldCheckEyeReferOut;
    private com.pcu.utility.IntegerTextField integerTextFieldCheckFeBloodReferOut;
    private com.pcu.utility.IntegerTextField integerTextFieldCheckGoiterReferOut;
    private com.pcu.utility.IntegerTextField integerTextFieldCheckLiceReferOut;
    private com.pcu.utility.IntegerTextField integerTextFieldCheckNutritionReferOut;
    private com.pcu.utility.IntegerTextField integerTextFieldCheckOtherReferOut;
    private com.pcu.utility.IntegerTextField integerTextFieldCheckStudentDentalReferOut;
    private com.pcu.utility.IntegerTextField integerTextFieldCheckStudentHealthReferOut;
    private com.pcu.utility.IntegerTextField integerTextFieldCheckThalassemiaReferOut;
    private com.pcu.utility.IntegerTextField integerTextFieldCheckWormReferOut;
    private com.pcu.utility.IntegerTextField integerTextFieldMilkTooth;
    private com.pcu.utility.IntegerTextField integerTextFieldRealTooth;
    private javax.swing.JButton jButtonAddSchool;
    private javax.swing.JButton jButtonAddStu;
    private javax.swing.JButton jButtonCheckBodyReferOut;
    private javax.swing.JButton jButtonCheckEarReferOut;
    private javax.swing.JButton jButtonCheckEyeReferOut;
    private javax.swing.JButton jButtonCheckFeBloodReferOut;
    private javax.swing.JButton jButtonCheckGoiterReferOut;
    private javax.swing.JButton jButtonCheckLiceReferOut;
    private javax.swing.JButton jButtonCheckNutritionReferOut;
    private javax.swing.JButton jButtonCheckOtherReferOut;
    private javax.swing.JButton jButtonCheckStudentDentalReferOut;
    private javax.swing.JButton jButtonCheckStudentHealthReferOut;
    private javax.swing.JButton jButtonCheckThalassemiaReferOut;
    private javax.swing.JButton jButtonCheckWormReferOut;
    private javax.swing.JButton jButtonDelSchool;
    private javax.swing.JButton jButtonDelStu;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonSaveCheckBody;
    private javax.swing.JButton jButtonSaveCheckEar;
    private javax.swing.JButton jButtonSaveCheckEye;
    private javax.swing.JButton jButtonSaveCheckFeBlood;
    private javax.swing.JButton jButtonSaveCheckGoiter;
    private javax.swing.JButton jButtonSaveCheckLice;
    private javax.swing.JButton jButtonSaveCheckNutrition;
    private javax.swing.JButton jButtonSaveCheckOther;
    private javax.swing.JButton jButtonSaveCheckStudentDental;
    private javax.swing.JButton jButtonSaveCheckStudentHealth;
    private javax.swing.JButton jButtonSaveCheckThalassemia;
    private javax.swing.JButton jButtonSaveCheckWorm;
    private javax.swing.JButton jButtonSaveSchool;
    private javax.swing.JButton jButtonSaveStu;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonSearchFamily;
    private javax.swing.JComboBox jComboBoxClass;
    private javax.swing.JComboBox jComboBoxEarResultCheck;
    private javax.swing.JComboBox jComboBoxNutritionResultCheck;
    private javax.swing.JComboBox jComboBoxPrefixStu;
    private javax.swing.JComboBox jComboBoxResultCheck;
    private javax.swing.JComboBox jComboBoxSchoolName;
    private javax.swing.JComboBox jComboBoxSchoolServiceType;
    private javax.swing.JComboBox jComboBoxStuSex;
    private javax.swing.JComboBox jComboBoxStudentDentalResultCheck;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBadMilkTooth;
    private javax.swing.JLabel jLabelBadRealTooth;
    private javax.swing.JLabel jLabelBodyResultCheck;
    private javax.swing.JLabel jLabelClass;
    private javax.swing.JLabel jLabelDataSchoolClass;
    private javax.swing.JLabel jLabelDataSchoolName;
    private javax.swing.JLabel jLabelDataSchoolRoom;
    private javax.swing.JLabel jLabelDataSchoolService;
    private javax.swing.JLabel jLabelEarResultCheck;
    private javax.swing.JLabel jLabelFeBloodResultCheck;
    private javax.swing.JLabel jLabelGoiterResultCheck;
    private javax.swing.JLabel jLabelLiceResultCheck;
    private javax.swing.JLabel jLabelMilkTooth;
    private javax.swing.JLabel jLabelNutritionResultCheck;
    private javax.swing.JLabel jLabelRealTooth;
    private javax.swing.JLabel jLabelResultCheck;
    private javax.swing.JLabel jLabelRoom;
    private javax.swing.JLabel jLabelSchoolClass;
    private javax.swing.JLabel jLabelSchoolName;
    private javax.swing.JLabel jLabelSchoolNameService;
    private javax.swing.JLabel jLabelSchoolRoom;
    private javax.swing.JLabel jLabelSchoolSchoolType;
    private javax.swing.JLabel jLabelSchoolService;
    private javax.swing.JLabel jLabelSchoolServiceDate;
    private javax.swing.JLabel jLabelSchoolYear;
    private javax.swing.JLabel jLabelServiceNote;
    private javax.swing.JLabel jLabelStuNumber;
    private javax.swing.JLabel jLabelStuNumber2;
    private javax.swing.JLabel jLabelStudentDentalResultCheck;
    private javax.swing.JLabel jLabelStudentHealthResultCheck;
    private javax.swing.JLabel jLabelStudentHealthTall;
    private javax.swing.JLabel jLabelStudentHealthWeight;
    private javax.swing.JLabel jLabelThalassemiaResultCheck;
    private javax.swing.JLabel jLabelWormResultCheck;
    private javax.swing.JLabel jLableBodyReferOut;
    private javax.swing.JLabel jLableBodyStatus;
    private javax.swing.JLabel jLableCheckEarReferOut;
    private javax.swing.JLabel jLableCheckEyeReferOut;
    private javax.swing.JLabel jLableCheckStudentHealthReferOut;
    private javax.swing.JLabel jLableEarStatus;
    private javax.swing.JLabel jLableFeBloodReferOut;
    private javax.swing.JLabel jLableFeBloodStatus;
    private javax.swing.JLabel jLableGoiterReferOut;
    private javax.swing.JLabel jLableGoiterStatus;
    private javax.swing.JLabel jLableLiceReferOut;
    private javax.swing.JLabel jLableLiceStatus;
    private javax.swing.JLabel jLableNameStu;
    private javax.swing.JLabel jLableNutritionReferOut;
    private javax.swing.JLabel jLableNutritionStatus;
    private javax.swing.JLabel jLableOtherReferOut;
    private javax.swing.JLabel jLableOtherStatus;
    private javax.swing.JLabel jLableStatus;
    private javax.swing.JLabel jLableStuPID;
    private javax.swing.JLabel jLableStuSex;
    private javax.swing.JLabel jLableStuSex1;
    private javax.swing.JLabel jLableStudentDentalReferOut;
    private javax.swing.JLabel jLableStudentDentalStatus;
    private javax.swing.JLabel jLableStudentHealthStatus;
    private javax.swing.JLabel jLableThalassemiaReferOut;
    private javax.swing.JLabel jLableThalassemiaStatus;
    private javax.swing.JLabel jLableWormReferOut;
    private javax.swing.JLabel jLableWormStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelBodyRefer;
    private javax.swing.JPanel jPanelBodyStudentDetail;
    private javax.swing.JPanel jPanelCard;
    private javax.swing.JPanel jPanelCardCheck;
    private javax.swing.JPanel jPanelCheckBodyDetail;
    private javax.swing.JPanel jPanelCheckEarDetail;
    private javax.swing.JPanel jPanelCheckEarRefer;
    private javax.swing.JPanel jPanelCheckEyeDetail;
    private javax.swing.JPanel jPanelCheckFeBloodDetail;
    private javax.swing.JPanel jPanelCheckGoiterDetail;
    private javax.swing.JPanel jPanelCheckLiceDetail;
    private javax.swing.JPanel jPanelCheckNutritionDetail;
    private javax.swing.JPanel jPanelCheckOtherDetail;
    private javax.swing.JPanel jPanelCheckStudentDentalDetail;
    private javax.swing.JPanel jPanelCheckStudentHealthDetail;
    private javax.swing.JPanel jPanelCheckStudentHealthRefer;
    private javax.swing.JPanel jPanelCheckThalassemiaDetail;
    private javax.swing.JPanel jPanelCheckWormDetail;
    private javax.swing.JPanel jPanelEarStudentDetail;
    private javax.swing.JPanel jPanelFeBloodRefer;
    private javax.swing.JPanel jPanelFeBloodStudentDetail;
    private javax.swing.JPanel jPanelGoiterRefer;
    private javax.swing.JPanel jPanelGoiterStudentDetail;
    private javax.swing.JPanel jPanelLiceRefer;
    private javax.swing.JPanel jPanelLiceStudentDetail;
    private javax.swing.JPanel jPanelNutritionRefer;
    private javax.swing.JPanel jPanelNutritionStudentDetail;
    private javax.swing.JPanel jPanelOtherRefer;
    private javax.swing.JPanel jPanelOtherStudentDetail;
    private javax.swing.JPanel jPanelRefer;
    private javax.swing.JPanel jPanelSchoolCheckBodyControl;
    private javax.swing.JPanel jPanelSchoolCheckEarControl;
    private javax.swing.JPanel jPanelSchoolCheckEyeControl;
    private javax.swing.JPanel jPanelSchoolCheckFeBloodControl;
    private javax.swing.JPanel jPanelSchoolCheckGoiterControl;
    private javax.swing.JPanel jPanelSchoolCheckLiceControl;
    private javax.swing.JPanel jPanelSchoolCheckNutritionControl;
    private javax.swing.JPanel jPanelSchoolCheckOtherControl;
    private javax.swing.JPanel jPanelSchoolCheckStudentDentalControl;
    private javax.swing.JPanel jPanelSchoolCheckStudentHealthControl;
    private javax.swing.JPanel jPanelSchoolCheckThalassemiaControl;
    private javax.swing.JPanel jPanelSchoolCheckWormControl;
    private javax.swing.JPanel jPanelSchoolControl;
    private javax.swing.JPanel jPanelSchoolDetail;
    private javax.swing.JPanel jPanelSchoolName;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JPanel jPanelSearchSchool;
    private javax.swing.JPanel jPanelServiceType;
    private javax.swing.JPanel jPanelShowSchoolData;
    private javax.swing.JPanel jPanelStuName;
    private javax.swing.JPanel jPanelStudentDentalRefer;
    private javax.swing.JPanel jPanelStudentDentalStudentDetail;
    private javax.swing.JPanel jPanelStudentDetail;
    private javax.swing.JPanel jPanelStudentHealthStudentDetail;
    private javax.swing.JPanel jPanelStudentServiceControl;
    private javax.swing.JPanel jPanelStudentSubDetail;
    private javax.swing.JPanel jPanelSubSchoolDetail;
    private javax.swing.JPanel jPanelSubStudentDetail;
    private javax.swing.JPanel jPanelThalassemiaRefer;
    private javax.swing.JPanel jPanelThalassemiaStudentDetail;
    private javax.swing.JPanel jPanelWormRefer;
    private javax.swing.JPanel jPanelWormStudentDetail;
    private javax.swing.JScrollPane jScrollPaneCheckStudentHealth;
    private javax.swing.JScrollPane jScrollPaneSchoolDetail;
    private javax.swing.JScrollPane jScrollPaneSchoolList;
    private javax.swing.JScrollPane jScrollPaneStudentHealthNote;
    private javax.swing.JScrollPane jScrollPaneStudentHealthNote1;
    private javax.swing.JScrollPane jScrollPaneStudentHealthNote10;
    private javax.swing.JScrollPane jScrollPaneStudentHealthNote11;
    private javax.swing.JScrollPane jScrollPaneStudentHealthNote2;
    private javax.swing.JScrollPane jScrollPaneStudentHealthNote3;
    private javax.swing.JScrollPane jScrollPaneStudentHealthNote4;
    private javax.swing.JScrollPane jScrollPaneStudentHealthNote5;
    private javax.swing.JScrollPane jScrollPaneStudentHealthNote6;
    private javax.swing.JScrollPane jScrollPaneStudentHealthNote7;
    private javax.swing.JScrollPane jScrollPaneStudentHealthNote8;
    private javax.swing.JScrollPane jScrollPaneStudentHealthNote9;
    private javax.swing.JScrollPane jScrollPaneStudentNote;
    private javax.swing.JScrollPane jScrollPaneStudentServiceDetail;
    private javax.swing.JTable jTableCheckStudentHealthDetail;
    private javax.swing.JTable jTableSchoolDetail;
    private javax.swing.JTable jTableSchoolList;
    private javax.swing.JTable jTableStudentService;
    private javax.swing.JTextArea jTextAreaBodyNote;
    private javax.swing.JTextField jTextAreaBodyRemark;
    private javax.swing.JTextArea jTextAreaEarNote;
    private javax.swing.JTextField jTextAreaEarRemark;
    private javax.swing.JTextArea jTextAreaEyeNote;
    private javax.swing.JTextField jTextAreaEyeRemark;
    private javax.swing.JTextArea jTextAreaFeBloodNote;
    private javax.swing.JTextField jTextAreaFeBloodRemark;
    private javax.swing.JTextArea jTextAreaGoiterNote;
    private javax.swing.JTextField jTextAreaGoiterRemark;
    private javax.swing.JTextArea jTextAreaLiceNote;
    private javax.swing.JTextField jTextAreaLiceRemark;
    private javax.swing.JTextArea jTextAreaNutritionNote;
    private javax.swing.JTextField jTextAreaNutritionRemark;
    private javax.swing.JTextArea jTextAreaOtherNote;
    private javax.swing.JTextField jTextAreaOtherRemark;
    private javax.swing.JTextArea jTextAreaStudentDentalNote;
    private javax.swing.JTextField jTextAreaStudentDentalRemark;
    private javax.swing.JTextArea jTextAreaStudentHealthNote;
    private javax.swing.JTextField jTextAreaStudentHealthRemark;
    private javax.swing.JTextArea jTextAreaStudentNote;
    private javax.swing.JTextArea jTextAreaThalassemiaNote;
    private javax.swing.JTextField jTextAreaThalassemiaRemark;
    private javax.swing.JTextArea jTextAreaWormNote;
    private javax.swing.JTextField jTextAreaWormRemark;
    private javax.swing.JTextField jTextFieldAge;
    private javax.swing.JTextField jTextFieldCheckBodyReferOut;
    private javax.swing.JTextField jTextFieldCheckEarReferOut;
    private javax.swing.JTextField jTextFieldCheckEyeReferOut;
    private javax.swing.JTextField jTextFieldCheckFeBloodReferOut;
    private javax.swing.JTextField jTextFieldCheckGoiterReferOut;
    private javax.swing.JTextField jTextFieldCheckLiceReferOut;
    private javax.swing.JTextField jTextFieldCheckNutritionReferOut;
    private javax.swing.JTextField jTextFieldCheckOtherReferOut;
    private javax.swing.JTextField jTextFieldCheckStudentDentalReferOut;
    private javax.swing.JTextField jTextFieldCheckStudentHealthReferOut;
    private javax.swing.JTextField jTextFieldCheckThalassemiaReferOut;
    private javax.swing.JTextField jTextFieldCheckWormReferOut;
    private javax.swing.JTextField jTextFieldRoom;
    private com.pcu.utility.IntegerTextField jTextFieldSchoolYear;
    private javax.swing.JTextField jTextFieldSearchSchool;
    private javax.swing.JTextField jTextFieldServiceOther;
    private javax.swing.JTextField jTextFieldStuName;
    private javax.swing.JTextField jTextFieldStuNumber;
    private javax.swing.JTextField jTextFieldStuSurName;
    private com.pcu.utility.PIDPanel pIDPanel;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoCheckBody;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoCheckBodyResult;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoCheckDental;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoCheckEar;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoCheckEye;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoCheckFeBlood;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoCheckGoiter;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoCheckLice;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoCheckLiceResultCheck;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoCheckNutrition;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoCheckOther;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoCheckThalassemia;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoCheckThalassemiaResult;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoCheckWorm;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoCheckWormResult;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoFeBloodResult;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoGoiterResult;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoStudentHealthResultCheck;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoStudentHealthStatus;
    // End of variables declaration//GEN-END:variables
}

