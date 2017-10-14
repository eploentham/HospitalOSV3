/*
 * PanelVitalSign.java
 *
 * Created on 18 ตุjTextAreaPhysicalExamลาคม 2546, 9:45 น.
 */
package com.hosv3.gui.panel.transaction;

import com.hosv3.gui.dialog.*;
import com.hosv3.control.*;  
import com.hosv3.utility.connection.*;
import com.hosv3.subject.*;
import com.hosv3.utility.*;
import com.hosv3.object.*;
import com.hosv3.usecase.transaction.*;
import com.hosv3.control.lookup.*;

import com.hospital_os.object.*; 
import com.hospital_os.utility.*;
import com.hosv3.utility.Constant;
import com.hosv3.utility.DateUtil;
//import com.lowagie.text.Phrase;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import com.hosv3.gui.dialog.PanelPhysicalExam;
import com.hosv3.gui.dialog.PanelHealthEducation;
import com.hosv3.gui.dialog.PanelWound;
import com.hosv3.gui.dialog.PanelNCD;
import com.hosv3.gui.dialog.PanelRegisterNCD; 
/**
 *
 *
 * @author henbe
 */
public class PanelVitalSign extends javax.swing.JPanel implements 
ManageVisitResp,
ManageVitalResp,
ManagePatientResp,
ManageLabXrayResp        
{   
    static final long serialVersionUID=0;
    HosObject theHO;
    HosControl theHC;
    HosSubject theHS;
    UpdateStatus theUS;
    HosDialog theHD;
    DialogChooseICD10FromTemplate theDCFT; 
    
    PanelPhysicalExam thePPE;
    PanelWound thePW;
    PanelPatientHistory thePPH;
    PanelHealthEducation thePHE;
    PanelNCD thePNCD;
    PanelRegisterNCD thePRNCD;
  
    String[] column_jTableListPhyEx = {"ร่างกาย","ผลการตรวจ"};
    String[] col_jTableVitalSign = {"วัน-เวลาตรวจ"};
    String[] col_jTablePrimarySymptom = {"วัน-เวลาบันทึก"};
    public static String[] col_jTableAllergy = {"ชื่อยา","อาการที่แพ้"};
    CellRendererHos dateRender = new CellRendererHos(CellRendererHos.DATE_TIME);
    private Vector vVitalSign;
    private Vector vPrimarySymptom;
    private Vector vPhysicalExam;
    private Vector vNutritionType;
    private Vector vHealthEducation;
    private Vector vPhysicalExamNan;

    private PhysicalExam thePhysicalExam;
    private VitalSign theVitalSign;
    private Visit theVisit;
    private Patient thePatient;
    private PrimarySymptom thePrimarySymptom;
    private Vector vGuide;
    private Vector vPersonalDisease;

    private GuideAfterDxTransaction theGuideAfterDxTransaction;  
    /*การนัด sumo 07/08/2549*/
    private Appointment theAppointment;
    /**เป็นคิวของ Visit*/
    private Vector vCalDateAppointment;

    private GHospitalSuit theGHS;

    private GPatientSuit theGPS;

    private DiagDoctorClinic theDiagDoctorClinic = new DiagDoctorClinic();
    
    /** Creates new form PanelVitalSign */
    public PanelVitalSign()
    { 
        initComponents(); 
        jTablePrimarySymptom.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        //ให้มองเห็นแค่ว่าบันทักผ่าน lost focus เท่านั้นให้เหมือนเวอร์ชันก่อนหน้า
        jButtonSaveDx.setVisible(false);
        this.jPanelHide.setVisible(false);
        this.jRadioButtonUD.setVisible(false);
    }
    
    /*neung
     *ทำการตรวจสอบว่าผู้ใช้เป็นใคร
     *และไม่สามารถทำอะไรได้บ้าง ก็จะทำการเซตvisitbleปุ่มนั้นไป
     */
    private void setAuthentication(Employee theEmployee)
    {
        this.setEnabled(theHO.theGActionAuthV.isWriteTabVitalSign());
        jCheckBoxPEText.setEnabled(theHO.theGActionAuthV.isWritePVitalPhysicalExam());
        this.jTextAreaPhysicalExam.setEnabled(theHO.theGActionAuthV.isWritePVitalPhysicalExam());
        this.jButtonPhysicalExam.setVisible(theHO.theGActionAuthV.isWritePVitalPhysicalExam());
        this.jButtonSaveDx.setVisible(theHO.theGActionAuthV.isWritePVitalDiagnosis());
        this.jTextFieldDx.setEnabled(theHO.theGActionAuthV.isWritePVitalDiagnosis());
        jButtonReDx.setVisible(theHO.theGActionAuthV.isWritePVitalDiagnosis());
    }
    
    public void setControl(HosControl hc,GPatientSuit gps,GHospitalSuit ghs, UpdateStatus us)
    {   
        theHC = hc;
        theHO = hc.theHO;
        theHS = hc.theHS;
        theUS = us;
        theGPS = gps;
        theGHS = ghs;
        this.pDVitalSign1.setControl(hc,us);
        theHS.theVitalSubject.attachManageVital(this);
        theHS.thePatientSubject.attachManagePatient(this);
        theHS.theVisitSubject.attachManageVisit(this);        
        theHS.theResultSubject.attachManageXray(this);     
        theHS.theResultSubject.attachManageLab(this);  
        
        jTextAreaMainSymptom.setControl(new VitalTemplateLookup(theHC.theLookupControl));
        jTextAreaMainSymptom.setJFrame(theUS.getJFrame());
        theHS.theBalloonSubject.attachBalloon(jTextAreaMainSymptom);
        
        jTextAreaCurrent.setControl(new VitalTemplateLookup(theHC.theLookupControl));
        jTextAreaCurrent.setJFrame(theUS.getJFrame());
        theHS.theBalloonSubject.attachBalloon(jTextAreaCurrent);
        
        jTextFieldDx.setControl(new DxTemplateLookup(theHC.theLookupControl));
        jTextFieldDx.setEControl(new DxTemplateLookup(theHC.theVitalControl));
        jTextFieldDx.setJFrame(theUS.getJFrame());
        jTextFieldDx.setSelectAround("","\n");
        jTextFieldDx.setCheckRepeate(true);
        theHS.theBalloonSubject.attachBalloon(jTextFieldDx);
        
        jTextAreaGuide.setSelectAround("","\n");
        jTextAreaGuide.setControl(new GuideLookup(theHC.theLookupControl));
        jTextAreaGuide.setJFrame(theUS.getJFrame());
        theHS.theBalloonSubject.attachBalloon(jTextAreaGuide);
        
        jTextAreaPhysicalExam.setSelectAround("",": ");
        jTextAreaPhysicalExam.setControl(new BodyOrganLookup(theHC.theLookupControl));
        jTextAreaPhysicalExam.setJFrame(theUS.getJFrame());
        theHS.theBalloonSubject.attachBalloon(jTextAreaPhysicalExam);
        
        setAuthentication(theHO.theEmployee);
        initComboBox();
        setLanguage(null);
    }
    
    public void initComboBox()
    {
        vCalDateAppointment = theHC.theLookupControl.listCalDateAppointment();
        ComboboxModel.initComboBox(jComboBoxAppointment,vCalDateAppointment);
        ////////////////////////////////////////////////////////////////
        String command = theHC.theLookupControl.readOption().b2_command;
        String ttt = theHC.theLookupControl.readOption().b2_description;
        String icon = theHC.theLookupControl.readOption().b2_icon;
        this.jButtonB2.setVisible(!command.equals(""));
        this.jButtonB2.setToolTipText(ttt);
        if(!icon.equals("")) {
            this.jButtonB2.setIcon(new javax.swing.ImageIcon("icon/"+icon));
            this.jButtonB2.setText("");
        }
        ////////////////////////////////////////////////////////////////
    }
    
    public void setDialog(HosDialog hd)
    {
        theHD = hd;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jPanelDescription = new javax.swing.JPanel();
        jScrollPaneMainSymptom = new javax.swing.JScrollPane();
        jTextAreaMainSymptom = new com.hosv3.gui.component.BalloonTextArea();
        jLabelCurrent = new javax.swing.JLabel();
        jScrollPaneCurrent = new javax.swing.JScrollPane();
        jTextAreaCurrent = new com.hosv3.gui.component.BalloonTextArea();
        jPanelHide = new javax.swing.JPanel();
        jButtonViewPTCard = new javax.swing.JButton();
        jButtonViewSpecialClinic = new javax.swing.JButton();
        jButtonViewDentistry = new javax.swing.JButton();
        jButtonViewOBGYNCard = new javax.swing.JButton();
        jScrollPanePriSym = new javax.swing.JScrollPane();
        jTablePrimarySymptom = new com.hosv3.gui.component.HJTableSort();
        jPanel9 = new javax.swing.JPanel();
        jButtonAddPrimarySymptom = new javax.swing.JButton();
        jButtonDelPrimarySymptom = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabelMainSymptom = new javax.swing.JLabel();
        jCheckBoxBalloon = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jButtonRegiterNCD = new javax.swing.JButton();
        jButtonViewNCD = new javax.swing.JButton();
        jButtonAccident = new javax.swing.JButton();
        jButtonLabResult = new javax.swing.JButton();
        jButtonViewHistory = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jButtonSaveVs = new javax.swing.JButton();
        jButtonChronic = new javax.swing.JButton();
        jButtonSurveil = new javax.swing.JButton();
        jButtonDeath = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jRadioButtonNUG = new javax.swing.JRadioButton();
        jRadioButtonER = new javax.swing.JRadioButton();
        jRadioButtonUG = new javax.swing.JRadioButton();
        jCheckBoxPregnant = new javax.swing.JCheckBox();
        jCheckBoxDenyAllergy = new javax.swing.JCheckBox();
        jRadioButtonUD = new javax.swing.JRadioButton();
        jPanel21 = new javax.swing.JPanel();
        jButtonSavePrimarySymptom = new javax.swing.JButton();
        pDVitalSign1 = new com.hosv3.gui.panel.detail.PDVitalSign();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel20 = new javax.swing.JPanel();
        jScrollPanePhyEx1 = new javax.swing.JScrollPane();
        jTextAreaPhysicalExam = new com.hosv3.gui.component.BalloonTextArea();
        jPanel10 = new javax.swing.JPanel();
        jButtonPhysicalExam = new javax.swing.JButton();
        jButtonB2 = new javax.swing.JButton();
        jCheckBoxPEText = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jButtonSaveGuide = new javax.swing.JButton();
        jButtonPrintGuide = new javax.swing.JButton();
        jButtonHealthEducation = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaGuide = new com.hosv3.gui.component.BalloonTextArea();
        jScrollPaneMainSymptom1 = new javax.swing.JScrollPane();
        jTextFieldDx = new com.hosv3.gui.component.BalloonTextArea();
        jScrollPaneNote = new javax.swing.JScrollPane();
        jTextAreaNote = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel();
        jLabelDx = new javax.swing.JLabel();
        jButtonReDx = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButtonSaveDx = new javax.swing.JButton();
        jCheckBoxBalloonDx = new javax.swing.JCheckBox();
        jPanel19 = new javax.swing.JPanel();
        jComboBoxAppointment = new javax.swing.JComboBox();
        jTextFieldAppointment = new javax.swing.JTextField();
        jButtonAppointment = new javax.swing.JButton();
        jCheckBoxAppointment = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jCheckBoxAdmit = new javax.swing.JCheckBox();
        jButtonAdmit = new javax.swing.JButton();
        jCheckBoxRefer = new javax.swing.JCheckBox();
        jButtonRefer = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jSplitPane1.setBorder(null);
        jSplitPane1.setDividerLocation(490);
        jSplitPane1.setResizeWeight(1.0);
        jSplitPane1.setOneTouchExpandable(true);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanelDescription.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "อาการสำคัญ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jPanelDescription.setMinimumSize(new java.awt.Dimension(140, 371));
        jPanelDescription.setPreferredSize(new java.awt.Dimension(240, 371));
        jPanelDescription.setLayout(new java.awt.GridBagLayout());

        jScrollPaneMainSymptom.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneMainSymptom.setAutoscrolls(true);
        jScrollPaneMainSymptom.setMinimumSize(new java.awt.Dimension(100, 100));
        jScrollPaneMainSymptom.setPreferredSize(new java.awt.Dimension(100, 100));

        jTextAreaMainSymptom.setWrapStyleWord(true);
        jTextAreaMainSymptom.setFont(defaultFont1);
        jTextAreaMainSymptom.setMinimumSize(new java.awt.Dimension(100, 17));
        jTextAreaMainSymptom.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextAreaMainSymptomFocusLost(evt);
            }
        });
        jTextAreaMainSymptom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextAreaMainSymptomKeyReleased(evt);
            }
        });
        jScrollPaneMainSymptom.setViewportView(jTextAreaMainSymptom);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelDescription.add(jScrollPaneMainSymptom, gridBagConstraints);

        jLabelCurrent.setFont(defaultFont1);
        jLabelCurrent.setText("ประวัติปัจจุบัน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelDescription.add(jLabelCurrent, gridBagConstraints);

        jScrollPaneCurrent.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneCurrent.setAutoscrolls(true);
        jScrollPaneCurrent.setMinimumSize(new java.awt.Dimension(100, 85));
        jScrollPaneCurrent.setPreferredSize(new java.awt.Dimension(100, 85));

        jTextAreaCurrent.setWrapStyleWord(true);
        jTextAreaCurrent.setFont(defaultFont1);
        jTextAreaCurrent.setMinimumSize(new java.awt.Dimension(100, 17));
        jTextAreaCurrent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextAreaCurrentKeyReleased(evt);
            }
        });
        jScrollPaneCurrent.setViewportView(jTextAreaCurrent);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanelDescription.add(jScrollPaneCurrent, gridBagConstraints);

        jPanelHide.setLayout(new java.awt.GridBagLayout());

        jButtonViewPTCard.setFont(defaultFont1);
        jButtonViewPTCard.setText("PTcard");
        jButtonViewPTCard.setEnabled(false);
        jButtonViewPTCard.setMargin(new java.awt.Insets(2, 2, 2, 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelHide.add(jButtonViewPTCard, gridBagConstraints);

        jButtonViewSpecialClinic.setFont(defaultFont1);
        jButtonViewSpecialClinic.setText("คลินิคพิเศษ");
        jButtonViewSpecialClinic.setEnabled(false);
        jButtonViewSpecialClinic.setMargin(new java.awt.Insets(2, 2, 2, 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelHide.add(jButtonViewSpecialClinic, gridBagConstraints);

        jButtonViewDentistry.setFont(defaultFont1);
        jButtonViewDentistry.setText("Dentistry");
        jButtonViewDentistry.setEnabled(false);
        jButtonViewDentistry.setMargin(new java.awt.Insets(2, 2, 2, 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelHide.add(jButtonViewDentistry, gridBagConstraints);

        jButtonViewOBGYNCard.setFont(defaultFont1);
        jButtonViewOBGYNCard.setText("OB-GYN");
        jButtonViewOBGYNCard.setEnabled(false);
        jButtonViewOBGYNCard.setMargin(new java.awt.Insets(2, 2, 2, 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelHide.add(jButtonViewOBGYNCard, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelDescription.add(jPanelHide, gridBagConstraints);

        jScrollPanePriSym.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPanePriSym.setMinimumSize(new java.awt.Dimension(250, 40));
        jScrollPanePriSym.setPreferredSize(new java.awt.Dimension(250, 40));

        jTablePrimarySymptom.setFont(defaultFont1);
        jTablePrimarySymptom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTablePrimarySymptomMouseReleased(evt);
            }
        });
        jTablePrimarySymptom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTablePrimarySymptomKeyReleased(evt);
            }
        });
        jScrollPanePriSym.setViewportView(jTablePrimarySymptom);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanelDescription.add(jScrollPanePriSym, gridBagConstraints);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jButtonAddPrimarySymptom.setFont(defaultFont1);
        jButtonAddPrimarySymptom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAddPrimarySymptom.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonAddPrimarySymptom.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonAddPrimarySymptom.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonAddPrimarySymptom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddPrimarySymptomActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel9.add(jButtonAddPrimarySymptom, gridBagConstraints);

        jButtonDelPrimarySymptom.setFont(defaultFont1);
        jButtonDelPrimarySymptom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelPrimarySymptom.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDelPrimarySymptom.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonDelPrimarySymptom.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonDelPrimarySymptom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelPrimarySymptomActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel9.add(jButtonDelPrimarySymptom, gridBagConstraints);

        jButton1.setFont(jButton1.getFont().deriveFont(jButton1.getFont().getStyle() | java.awt.Font.BOLD));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/down-icon.png"))); // NOI18N
        jButton1.setMaximumSize(new java.awt.Dimension(26, 26));
        jButton1.setMinimumSize(new java.awt.Dimension(26, 26));
        jButton1.setPreferredSize(new java.awt.Dimension(26, 26));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel9.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanelDescription.add(jPanel9, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabelMainSymptom.setFont(defaultFont1);
        jLabelMainSymptom.setText("อาการสำคัญ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel6.add(jLabelMainSymptom, gridBagConstraints);

        jCheckBoxBalloon.setFont(defaultFont1);
        jCheckBoxBalloon.setSelected(true);
        jCheckBoxBalloon.setText("ใช้ตัวช่วย");
        jCheckBoxBalloon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxBalloonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(jCheckBoxBalloon, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelDescription.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jPanelDescription, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jButtonRegiterNCD.setFont(defaultFont1);
        jButtonRegiterNCD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/IconTitle.gif"))); // NOI18N
        jButtonRegiterNCD.setToolTipText("ลงทะเบียน NCD ");
        jButtonRegiterNCD.setMaximumSize(new java.awt.Dimension(39, 26));
        jButtonRegiterNCD.setMinimumSize(new java.awt.Dimension(39, 26));
        jButtonRegiterNCD.setPreferredSize(new java.awt.Dimension(39, 26));
        jButtonRegiterNCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegiterNCDActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel5.add(jButtonRegiterNCD, gridBagConstraints);

        jButtonViewNCD.setFont(defaultFont1);
        jButtonViewNCD.setText("NCD");
        jButtonViewNCD.setToolTipText("ดูประวัติ NCD");
        jButtonViewNCD.setMaximumSize(new java.awt.Dimension(52, 26));
        jButtonViewNCD.setMinimumSize(new java.awt.Dimension(52, 26));
        jButtonViewNCD.setPreferredSize(new java.awt.Dimension(52, 26));
        jButtonViewNCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewNCDActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel5.add(jButtonViewNCD, gridBagConstraints);

        jButtonAccident.setFont(defaultFont1);
        jButtonAccident.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/accident.gif"))); // NOI18N
        jButtonAccident.setToolTipText("ข้อมูลอุบัติเหตุ");
        jButtonAccident.setMaximumSize(new java.awt.Dimension(39, 26));
        jButtonAccident.setMinimumSize(new java.awt.Dimension(39, 26));
        jButtonAccident.setPreferredSize(new java.awt.Dimension(39, 26));
        jButtonAccident.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAccidentActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel5.add(jButtonAccident, gridBagConstraints);

        jButtonLabResult.setFont(defaultFont1);
        jButtonLabResult.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/order_history.gif"))); // NOI18N
        jButtonLabResult.setToolTipText("ประวัติ");
        jButtonLabResult.setMaximumSize(new java.awt.Dimension(39, 26));
        jButtonLabResult.setMinimumSize(new java.awt.Dimension(39, 26));
        jButtonLabResult.setPreferredSize(new java.awt.Dimension(39, 26));
        jButtonLabResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLabResultActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel5.add(jButtonLabResult, gridBagConstraints);

        jButtonViewHistory.setFont(defaultFont1);
        jButtonViewHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/patient_history.gif"))); // NOI18N
        jButtonViewHistory.setToolTipText("ประวัติอดีต ประวัติครอบครัว ปัจจัยเสี่ยง");
        jButtonViewHistory.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButtonViewHistory.setMaximumSize(new java.awt.Dimension(39, 26));
        jButtonViewHistory.setMinimumSize(new java.awt.Dimension(39, 26));
        jButtonViewHistory.setPreferredSize(new java.awt.Dimension(39, 26));
        jButtonViewHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewHistoryActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel5.add(jButtonViewHistory, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 0, 0);
        jPanel3.add(jPanel5, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        jButtonSaveVs.setFont(defaultFont1);
        jButtonSaveVs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/save.gif"))); // NOI18N
        jButtonSaveVs.setMaximumSize(new java.awt.Dimension(62, 24));
        jButtonSaveVs.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonSaveVs.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonSaveVs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveVsActionPerformed(evt);
            }
        });
        jButtonSaveVs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButtonSaveVsKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 10);
        jPanel12.add(jButtonSaveVs, gridBagConstraints);

        jButtonChronic.setFont(defaultFont1);
        jButtonChronic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/chronic.gif"))); // NOI18N
        jButtonChronic.setToolTipText("โรคเรื้อรัง");
        jButtonChronic.setMaximumSize(new java.awt.Dimension(52, 26));
        jButtonChronic.setMinimumSize(new java.awt.Dimension(52, 26));
        jButtonChronic.setPreferredSize(new java.awt.Dimension(52, 26));
        jButtonChronic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChronicActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel12.add(jButtonChronic, gridBagConstraints);

        jButtonSurveil.setFont(defaultFont1);
        jButtonSurveil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/surveil.gif"))); // NOI18N
        jButtonSurveil.setToolTipText("โรคเฝ้าระวัง");
        jButtonSurveil.setMaximumSize(new java.awt.Dimension(52, 26));
        jButtonSurveil.setMinimumSize(new java.awt.Dimension(52, 26));
        jButtonSurveil.setPreferredSize(new java.awt.Dimension(52, 26));
        jButtonSurveil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSurveilActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel12.add(jButtonSurveil, gridBagConstraints);

        jButtonDeath.setFont(defaultFont1);
        jButtonDeath.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/death_icon.gif"))); // NOI18N
        jButtonDeath.setToolTipText("ข้อมูลการตาย");
        jButtonDeath.setMaximumSize(new java.awt.Dimension(52, 26));
        jButtonDeath.setMinimumSize(new java.awt.Dimension(52, 26));
        jButtonDeath.setPreferredSize(new java.awt.Dimension(52, 26));
        jButtonDeath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeathActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel12.add(jButtonDeath, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 11, 0);
        jPanel3.add(jPanel12, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        buttonGroup1.add(jRadioButtonNUG);
        jRadioButtonNUG.setFont(defaultFont1);
        jRadioButtonNUG.setText("NonUrgent");
        jRadioButtonNUG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNUGActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel7.add(jRadioButtonNUG, gridBagConstraints);

        buttonGroup1.add(jRadioButtonER);
        jRadioButtonER.setFont(defaultFont1);
        jRadioButtonER.setText("Emergency");
        jRadioButtonER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonERActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel7.add(jRadioButtonER, gridBagConstraints);

        buttonGroup1.add(jRadioButtonUG);
        jRadioButtonUG.setFont(defaultFont1);
        jRadioButtonUG.setText("Urgent");
        jRadioButtonUG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonUGActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel7.add(jRadioButtonUG, gridBagConstraints);

        jCheckBoxPregnant.setFont(defaultFont1);
        jCheckBoxPregnant.setText("ตั้งครรภ์");
        jCheckBoxPregnant.setToolTipText("บันทึกการตั้งครรถ์");
        jCheckBoxPregnant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPregnantActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel7.add(jCheckBoxPregnant, gridBagConstraints);

        jCheckBoxDenyAllergy.setFont(defaultFont1);
        jCheckBoxDenyAllergy.setText("ปฏิเสธแพ้ยา");
        jCheckBoxDenyAllergy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxDenyAllergyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel7.add(jCheckBoxDenyAllergy, gridBagConstraints);

        buttonGroup1.add(jRadioButtonUD);
        jRadioButtonUD.setSelected(true);
        jRadioButtonUD.setText("n/a");
        jRadioButtonUD.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel7.add(jRadioButtonUD, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(11, 5, 0, 0);
        jPanel3.add(jPanel7, gridBagConstraints);

        jPanel21.setLayout(new java.awt.GridBagLayout());

        jButtonSavePrimarySymptom.setFont(defaultFont1);
        jButtonSavePrimarySymptom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/save.gif"))); // NOI18N
        jButtonSavePrimarySymptom.setMaximumSize(new java.awt.Dimension(62, 24));
        jButtonSavePrimarySymptom.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonSavePrimarySymptom.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonSavePrimarySymptom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSavePrimarySymptomActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 5);
        jPanel21.add(jButtonSavePrimarySymptom, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 11, 0);
        jPanel3.add(jPanel21, gridBagConstraints);

        pDVitalSign1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "VitalSign", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        pDVitalSign1.setMinimumSize(new java.awt.Dimension(240, 305));
        pDVitalSign1.setPreferredSize(new java.awt.Dimension(240, 305));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanel3.add(pDVitalSign1, gridBagConstraints);

        jSplitPane1.setLeftComponent(jPanel3);

        jSplitPane2.setBorder(null);
        jSplitPane2.setDividerLocation(75);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setOneTouchExpandable(true);

        jPanel20.setPreferredSize(new java.awt.Dimension(104, 100));
        jPanel20.setLayout(new java.awt.GridBagLayout());

        jScrollPanePhyEx1.setMinimumSize(new java.awt.Dimension(104, 1));
        jScrollPanePhyEx1.setPreferredSize(new java.awt.Dimension(104, 1));

        jTextAreaPhysicalExam.setWrapStyleWord(true);
        jTextAreaPhysicalExam.setEnabled(false);
        jTextAreaPhysicalExam.setFont(defaultFont1);
        jTextAreaPhysicalExam.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextAreaPhysicalExamFocusLost(evt);
            }
        });
        jTextAreaPhysicalExam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextAreaPhysicalExamKeyReleased(evt);
            }
        });
        jScrollPanePhyEx1.setViewportView(jTextAreaPhysicalExam);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel20.add(jScrollPanePhyEx1, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jButtonPhysicalExam.setFont(defaultFont1);
        jButtonPhysicalExam.setText("ตรวจร่างกาย");
        jButtonPhysicalExam.setToolTipText("ตรวจร่างกาย");
        jButtonPhysicalExam.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButtonPhysicalExam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPhysicalExamActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel10.add(jButtonPhysicalExam, gridBagConstraints);

        jButtonB2.setFont(defaultFont1);
        jButtonB2.setText("B2");
        jButtonB2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonB2.setMaximumSize(new java.awt.Dimension(28, 28));
        jButtonB2.setMinimumSize(new java.awt.Dimension(28, 28));
        jButtonB2.setPreferredSize(new java.awt.Dimension(28, 28));
        jButtonB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonB2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel10.add(jButtonB2, gridBagConstraints);

        jCheckBoxPEText.setFont(defaultFont1);
        jCheckBoxPEText.setText("อวัยวะ :ผลตรวจ");
        jCheckBoxPEText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPETextActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel10.add(jCheckBoxPEText, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel20.add(jPanel10, gridBagConstraints);

        jSplitPane2.setLeftComponent(jPanel20);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel13.setLayout(new java.awt.GridBagLayout());

        jButtonSaveGuide.setFont(defaultFont1);
        jButtonSaveGuide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/save.gif"))); // NOI18N
        jButtonSaveGuide.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonSaveGuide.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonSaveGuide.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonSaveGuide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveGuideActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jPanel13.add(jButtonSaveGuide, gridBagConstraints);

        jButtonPrintGuide.setFont(defaultFont1);
        jButtonPrintGuide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/print.gif"))); // NOI18N
        jButtonPrintGuide.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonPrintGuide.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonPrintGuide.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonPrintGuide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintGuideActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel13.add(jButtonPrintGuide, gridBagConstraints);

        jButtonHealthEducation.setFont(defaultFont1);
        jButtonHealthEducation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/home.gif"))); // NOI18N
        jButtonHealthEducation.setToolTipText("การให้สุขศึกษา");
        jButtonHealthEducation.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonHealthEducation.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonHealthEducation.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonHealthEducation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHealthEducationActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weighty = 1.0;
        jPanel13.add(jButtonHealthEducation, gridBagConstraints);

        jLabel2.setFont(defaultFont1);
        jLabel2.setText("คำแนะนำผู้ป่วย/การให้สุขศึกษา");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 2);
        jPanel13.add(jLabel2, gridBagConstraints);

        jScrollPane5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextAreaGuide.setWrapStyleWord(true);
        jTextAreaGuide.setFont(defaultFont1);
        jTextAreaGuide.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextAreaGuideKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(jTextAreaGuide);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel13.add(jScrollPane5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 5, 5);
        jPanel1.add(jPanel13, gridBagConstraints);

        jScrollPaneMainSymptom1.setMinimumSize(new java.awt.Dimension(140, 75));
        jScrollPaneMainSymptom1.setPreferredSize(new java.awt.Dimension(140, 75));

        jTextFieldDx.setLineWrap(false);
        jTextFieldDx.setFont(defaultFont1);
        jTextFieldDx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldDxKeyReleased(evt);
            }
        });
        jScrollPaneMainSymptom1.setViewportView(jTextFieldDx);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 5);
        jPanel1.add(jScrollPaneMainSymptom1, gridBagConstraints);

        jTextAreaNote.setFont(defaultFont1);
        jTextAreaNote.setLineWrap(true);
        jTextAreaNote.setWrapStyleWord(true);
        jTextAreaNote.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextAreaNoteFocusLost(evt);
            }
        });
        jTextAreaNote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextAreaNoteKeyReleased(evt);
            }
        });
        jScrollPaneNote.setViewportView(jTextAreaNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 5);
        jPanel1.add(jScrollPaneNote, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabelDx.setFont(defaultFont1);
        jLabelDx.setText("/หมายเหตุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel8.add(jLabelDx, gridBagConstraints);

        jButtonReDx.setFont(defaultFont1);
        jButtonReDx.setText("ReDx");
        jButtonReDx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReDxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel8.add(jButtonReDx, gridBagConstraints);

        jLabel8.setFont(defaultFont1);
        jLabel8.setText("Dx");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel8.add(jLabel8, gridBagConstraints);

        jButtonSaveDx.setFont(defaultFont1);
        jButtonSaveDx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Save16.gif"))); // NOI18N
        jButtonSaveDx.setMaximumSize(new java.awt.Dimension(62, 24));
        jButtonSaveDx.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonSaveDx.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonSaveDx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveDxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        jPanel8.add(jButtonSaveDx, gridBagConstraints);

        jCheckBoxBalloonDx.setFont(defaultFont1);
        jCheckBoxBalloonDx.setSelected(true);
        jCheckBoxBalloonDx.setText("ตัวช่วย");
        jCheckBoxBalloonDx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxBalloonDxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel8.add(jCheckBoxBalloonDx, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel1.add(jPanel8, gridBagConstraints);

        jPanel19.setLayout(new java.awt.GridBagLayout());

        jComboBoxAppointment.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 0);
        jPanel19.add(jComboBoxAppointment, gridBagConstraints);

        jTextFieldAppointment.setFont(defaultFont1);
        jTextFieldAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAppointmentActionPerformed(evt);
            }
        });
        jTextFieldAppointment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldAppointmentKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 3, 0, 0);
        jPanel19.add(jTextFieldAppointment, gridBagConstraints);

        jButtonAppointment.setFont(defaultFont1);
        jButtonAppointment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/appoint.gif"))); // NOI18N
        jButtonAppointment.setToolTipText("การนัดผู้ป่วย");
        jButtonAppointment.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonAppointment.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonAppointment.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAppointmentActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel19.add(jButtonAppointment, gridBagConstraints);

        jCheckBoxAppointment.setFont(defaultFont1);
        jCheckBoxAppointment.setText("นัด");
        jCheckBoxAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAppointmentActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel19.add(jCheckBoxAppointment, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 5);
        jPanel1.add(jPanel19, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jCheckBoxAdmit.setFont(defaultFont1);
        jCheckBoxAdmit.setText("Admit");
        jCheckBoxAdmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAdmitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(jCheckBoxAdmit, gridBagConstraints);

        jButtonAdmit.setFont(defaultFont1);
        jButtonAdmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/admit.png"))); // NOI18N
        jButtonAdmit.setToolTipText("การ Admit ผู้ป่วย");
        jButtonAdmit.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonAdmit.setMinimumSize(new java.awt.Dimension(39, 26));
        jButtonAdmit.setPreferredSize(new java.awt.Dimension(39, 26));
        jButtonAdmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdmitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(jButtonAdmit, gridBagConstraints);

        jCheckBoxRefer.setFont(defaultFont1);
        jCheckBoxRefer.setText("Refer");
        jCheckBoxRefer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxReferActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(jCheckBoxRefer, gridBagConstraints);

        jButtonRefer.setFont(defaultFont1);
        jButtonRefer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/refer.gif"))); // NOI18N
        jButtonRefer.setToolTipText("ส่งผู้ป่วยไปรักษาต่อ (refer)");
        jButtonRefer.setMaximumSize(new java.awt.Dimension(62, 24));
        jButtonRefer.setMinimumSize(new java.awt.Dimension(39, 26));
        jButtonRefer.setPreferredSize(new java.awt.Dimension(39, 26));
        jButtonRefer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReferActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(jButtonRefer, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 5);
        jPanel1.add(jPanel4, gridBagConstraints);

        jSplitPane2.setRightComponent(jPanel1);

        jSplitPane1.setRightComponent(jSplitPane2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jSplitPane1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonReDxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReDxActionPerformed
       theHC.theDiagnosisControl.saveReDiag();
    }//GEN-LAST:event_jButtonReDxActionPerformed

    private void jTextFieldAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAppointmentActionPerformed
        saveAppointment();
    }//GEN-LAST:event_jTextFieldAppointmentActionPerformed

    private void jButtonB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonB2ActionPerformed
        String command = theHC.theLookupControl.readOption().b2_command;
        try{
            Process proc = Runtime.getRuntime().exec(command);
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
        }        
    }//GEN-LAST:event_jButtonB2ActionPerformed

    private void jButtonDeathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeathActionPerformed
        theHD.showDialogDeath();
    }//GEN-LAST:event_jButtonDeathActionPerformed

    private void jButtonAccidentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAccidentActionPerformed
        theHD.showDialogAccident();
    }//GEN-LAST:event_jButtonAccidentActionPerformed

    private void jButtonSurveilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSurveilActionPerformed
        theHD.showDialogSurveil(null);
    }//GEN-LAST:event_jButtonSurveilActionPerformed

    private void jButtonChronicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChronicActionPerformed
        theHD.showDialogChronic(theHO.theVisit,null);
    }//GEN-LAST:event_jButtonChronicActionPerformed

    private void jTextAreaPhysicalExamFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextAreaPhysicalExamFocusLost

    }//GEN-LAST:event_jTextAreaPhysicalExamFocusLost

    private void jCheckBoxPETextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPETextActionPerformed
        this.jTextAreaPhysicalExam.setEnabled(jCheckBoxPEText.isSelected());
        if(jCheckBoxPEText.isSelected())
            this.vPhysicalExam = new Vector();
    }//GEN-LAST:event_jCheckBoxPETextActionPerformed

    private void jTextFieldAppointmentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAppointmentKeyReleased
//        if(evt.getKeyCode()==KeyEvent.VK_ENTER) 
//        {
//            saveAppointment();
//        }
    }//GEN-LAST:event_jTextFieldAppointmentKeyReleased

    private void jButtonAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAppointmentActionPerformed
        theHD.showDialogAppointment(theHO.thePatient,theHO.theVisit);
    }//GEN-LAST:event_jButtonAppointmentActionPerformed

    private void jTextAreaGuideKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaGuideKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ENTER) 
        {
            jButtonSaveGuideActionPerformed(null);
        }
    }//GEN-LAST:event_jTextAreaGuideKeyReleased

    private void jTextAreaPhysicalExamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaPhysicalExamKeyReleased
        if(evt.getKeyCode()==evt.VK_ENTER)
        {
            vPhysicalExam = new Vector();
            boolean ret = getPhysicalExam(vPhysicalExam);
            if(ret == false)
            {
                setPhysicalExamNanV(vPhysicalExam);
                theUS.setStatus("กรุณากรอก ส่วนของร่างกาย: ผลการตรวจ", UpdateStatus.WARNING);
                return;
            }
            theHC.theVitalControl.savePhysicalExam(vPhysicalExam);
        }
    }//GEN-LAST:event_jTextAreaPhysicalExamKeyReleased

    private void jCheckBoxReferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxReferActionPerformed
        boolean ret = theHC.theVisitControl.updateVisitRefer(jCheckBoxRefer.isSelected());
    }//GEN-LAST:event_jCheckBoxReferActionPerformed

    private void jButtonAdmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdmitActionPerformed
//        Vector vAN = new Vector();
//        if(VisitType.OPD.equals(theHO.theVisit.visit_type))
//        {
//            Vector vAdmitNumber = theHC.theVisitControl.searchAdmitNumberInReverseAdmit();            
//            if(vAdmitNumber != null && vAdmitNumber.size() >0)
//            {   
//                boolean choose = confirmBox(Constant.getTextBundle("ต้องการนำเลข AN ที่ถูกยกเลิกกลับมาใช้ใหม่หรือไม่ ?"),UpdateStatus.WARNING);
//                if(choose)
//                {
//                    ReverseAdmit ra = DialogListAnReused.showDialog(theHC, theUS, vAdmitNumber);
//                    if(ra!=null)
//                        vAN.add(ra);
//                }
//            }
//        }
//        boolean admit = theHD.showDialogAdmit(theHO.theVisit); 
//        if(!admit)
//        {
//            return;
//        }
//        theHC.theVisitControl.admitVisit(theHO.theVisit,vAN);
        theHD.showDialogAdmit(theHO.theVisit);
    }//GEN-LAST:event_jButtonAdmitActionPerformed

    private void jCheckBoxAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAppointmentActionPerformed
        boolean ret = theHC.theVisitControl.updateVisitAppointment(jCheckBoxAppointment.isSelected());
        if(jCheckBoxAppointment.isSelected())
        {
            jTextFieldAppointment.setEnabled(true);
            jComboBoxAppointment.setEnabled(true);
        }
        else
        {
            jTextFieldAppointment.setEnabled(false);
            jTextFieldAppointment.setText("");
            jComboBoxAppointment.setEnabled(false);
            jComboBoxAppointment.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jCheckBoxAppointmentActionPerformed

    private void jCheckBoxAdmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAdmitActionPerformed
        boolean ret = theHC.theVisitControl.updateVisitAdmit(jCheckBoxAdmit.isSelected());
    }//GEN-LAST:event_jCheckBoxAdmitActionPerformed

    private void jTextAreaNoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaNoteKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_TAB)
            jButtonHealthEducation.requestFocus();
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            theHC.theVitalControl.saveDxNote(jTextAreaNote.getText());
    }//GEN-LAST:event_jTextAreaNoteKeyReleased

    private void jButtonRegiterNCDActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonRegiterNCDActionPerformed
    {//GEN-HEADEREND:event_jButtonRegiterNCDActionPerformed
        //amp:17/06/254:ลงทะเบียน NCD / มารักษาดวยกลุ่มโรค NCD
        if(theHO.thePatient==null)
        {
            theUS.setStatus("กรุณาเลือกผู้ป่วย",UpdateStatus.WARNING);
            return;
        }
        thePRNCD = new PanelRegisterNCD(theHC,theUS);
        thePRNCD.showDialog(false,true);
    }//GEN-LAST:event_jButtonRegiterNCDActionPerformed

    private void jButtonViewNCDActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonViewNCDActionPerformed
    {//GEN-HEADEREND:event_jButtonViewNCDActionPerformed
        //amp:15/06/254:แสดงประวัติ NCD
        if(theHO.thePatient==null)
        {
            theUS.setStatus("กรุณาเลือกผู้ป่วย",UpdateStatus.WARNING);
            return;
        }
        if(thePNCD==null)
        {
            thePNCD = new PanelNCD(theHC,theUS);
        }
        thePNCD.showDialog();
    }//GEN-LAST:event_jButtonViewNCDActionPerformed

    private void jRadioButtonERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonERActionPerformed
        String er = EmergencyStatus.UNDEFINE;
        if(jRadioButtonER.isSelected()) er = EmergencyStatus.EMERGENCY;
        else if(jRadioButtonUG.isSelected()) er = EmergencyStatus.URGENT;
        else if(jRadioButtonNUG.isSelected()) er = EmergencyStatus.NON_URGENT;
        theHC.theVisitControl.updateVisitEmergency(er);
    }//GEN-LAST:event_jRadioButtonERActionPerformed

    private void jRadioButtonUGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonUGActionPerformed
        String er = EmergencyStatus.UNDEFINE;
        if(jRadioButtonER.isSelected()) er = EmergencyStatus.EMERGENCY;
        else if(jRadioButtonUG.isSelected()) er = EmergencyStatus.URGENT;
        else if(jRadioButtonNUG.isSelected()) er = EmergencyStatus.NON_URGENT;
        theHC.theVisitControl.updateVisitEmergency(er);
    }//GEN-LAST:event_jRadioButtonUGActionPerformed

    private void jRadioButtonNUGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNUGActionPerformed
        String er = EmergencyStatus.UNDEFINE;
        if(jRadioButtonER.isSelected()) er = EmergencyStatus.EMERGENCY;
        else if(jRadioButtonUG.isSelected()) er = EmergencyStatus.URGENT;
        else if(jRadioButtonNUG.isSelected()) er = EmergencyStatus.NON_URGENT;
        theHC.theVisitControl.updateVisitEmergency(er);
    }//GEN-LAST:event_jRadioButtonNUGActionPerformed

    private void jButtonHealthEducationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHealthEducationActionPerformed
        showHealthEducation();
    }//GEN-LAST:event_jButtonHealthEducationActionPerformed

    private void jButtonPhysicalExamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPhysicalExamActionPerformed
        showPhysicalExam();
        this.jCheckBoxPEText.setSelected(false);
        this.jTextAreaPhysicalExam.setEnabled(false);
    }//GEN-LAST:event_jButtonPhysicalExamActionPerformed

    private void jButtonViewHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewHistoryActionPerformed
        showPatientHistory();
    }//GEN-LAST:event_jButtonViewHistoryActionPerformed

    private void jButtonReferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReferActionPerformed
        theHD.showDialogReferIn(theHO.theVisit);
    }//GEN-LAST:event_jButtonReferActionPerformed

    private void jTextFieldDxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDxKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_F1)
            theHD.showDialogDxTemplate(jTextFieldDx,new Vector());
        if(evt.getKeyCode()==KeyEvent.VK_TAB)
            jTextFieldDx.transferFocus();
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            jButtonSaveDxActionPerformed(null);
    }//GEN-LAST:event_jTextFieldDxKeyReleased

    private void jTextAreaCurrentFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextAreaCurrentFocusLost

    }//GEN-LAST:event_jTextAreaCurrentFocusLost

    private void jTextAreaMainSymptomFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextAreaMainSymptomFocusLost

    }//GEN-LAST:event_jTextAreaMainSymptomFocusLost

    private void jTextAreaCurrentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaCurrentKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_F1)
           theHD.showDialogPrimarySymptomTemplate(jTextAreaCurrent);
        if(evt.getKeyCode()==KeyEvent.VK_TAB)
            jTextAreaCurrent.transferFocus();
    }//GEN-LAST:event_jTextAreaCurrentKeyReleased

    private void jTextAreaMainSymptomKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaMainSymptomKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_F1)
             theHD.showDialogPrimarySymptomTemplate(jTextAreaMainSymptom);
        if(evt.getKeyCode()==KeyEvent.VK_TAB)
            jTextAreaMainSymptom.transferFocus();
    }//GEN-LAST:event_jTextAreaMainSymptomKeyReleased

    private void jButtonPrintGuideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintGuideActionPerformed
        theGuideAfterDxTransaction = theHC.theVisitControl.listGuideByVisitId(theVisit.getObjectId());
        theHC.thePrintControl.printGuide(theGuideAfterDxTransaction);
    }//GEN-LAST:event_jButtonPrintGuideActionPerformed

    public void saveAppointment()
    {
        String date_time = theHC.theLookupControl.getTextCurrentDateTime();
        if(theVisit.appointment_id.equals("") || theVisit.appointment_id == null){
            theAppointment = theHO.initAppointment(date_time);
        }
        else{
            theAppointment = theHC.thePatientControl.readAppointmentByPK(theVisit.appointment_id);
            if(theAppointment==null || theAppointment.appoint_active.equals("0"))
                theAppointment = theHO.initAppointment(date_time);    
        }
        theAppointment.aptype = jTextFieldAppointment.getText();
        theVisit.cal_date_appointment = Gutil.getGuiData(jComboBoxAppointment);
        theHC.theVisitControl.saveAppointment(theVisit,theAppointment,date_time);
    }
    private void jButtonSaveGuideActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonSaveGuideActionPerformed
    {//GEN-HEADEREND:event_jButtonSaveGuideActionPerformed
        ///////////////////////////////////////////////////////////////////////
        //การตรวจสอบการตรวจร่างกายว่าใช้ตัวช่วยหรือเปล่าหากใช้ก็ไม่ต้องบันทึกอีก
        if(this.jCheckBoxPEText.isSelected())
        {
            vPhysicalExam = new Vector();
            boolean ret = getPhysicalExam(vPhysicalExam);
            if(ret == false){
                theUS.setStatus("ผลการตรวจร่างกายผิดพลาดกรุณากรอก ส่วนของร่างกาย: ผลการตรวจ", UpdateStatus.WARNING);
                return;
            }
            theHC.theVitalControl.savePhysicalExam(vPhysicalExam);
        }        
        saveAppointment();
        saveDxForEmployee();
        getGuide();
        theHC.theVisitControl.saveGuideHealthEducation(vGuide);
        if(jTextAreaGuide.getText().trim().equals(""))
        {
            setHealthEducationV(null);
        }
    }//GEN-LAST:event_jButtonSaveGuideActionPerformed

    private void jTextAreaNoteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextAreaNoteFocusLost
        
    }//GEN-LAST:event_jTextAreaNoteFocusLost

    private void jCheckBoxDenyAllergyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxDenyAllergyActionPerformed
        //ปฏิเสธ ทั้งๆ ที่มียาที่แพ้
        // comment code เพราะว่ามี Feature เมื่อมีรายการที่แพ้แล้วเช็คปฏิเสธแพ้ยาจะถามยืนยันการลบรายการยาที่แพ้ sumo 04/09/2549
//        if(jCheckBoxDenyAllergy.isSelected()
//        && theHO.vDrugAllergy!=null && !theHO.vDrugAllergy.isEmpty())
//        {
//            jCheckBoxDenyAllergy.setSelected(false);
//            theUS.setStatus("ไม่สามารถบันทึกการปฏิเสธแพ้ยาของผู้ป่วยได้เนื่องจากยังมีรายการยาที่แพ้",UpdateStatus.WARNING);
//            return;
//        }
        int ret = theHC.thePatientControl.savePatientAllergy(jCheckBoxDenyAllergy.isSelected(),theHO.vDrugAllergy);
        //ถ้ามีรายการที่แพ้แล้วไม่ยืนยันการลบรายการยาที่แพ้ให้ CheckBox เคลียร์เป็นไม่ติ๊ก sumo 04/09/2549
        if(ret != 0)
        {
            jCheckBoxDenyAllergy.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxDenyAllergyActionPerformed

    private void jButtonLabResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLabResultActionPerformed
        String currentdate = theHO.date_time;
        String reversedate = DateUtil.getReverseDay(currentdate, 1);
        theHD.showDialogOrderHistory(theHO.thePatient, reversedate, null,CategoryGroup.isLab());
    }//GEN-LAST:event_jButtonLabResultActionPerformed

    private void jButtonSaveVsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonSaveVsKeyReleased
         if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            jButtonSaveVsActionPerformed(null);
    }//GEN-LAST:event_jButtonSaveVsKeyReleased

    private void jCheckBoxPregnantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPregnantActionPerformed
         boolean ret = theHC.theVisitControl.updateVisitPregnant(jCheckBoxPregnant.isSelected());
         if(!ret)
            jCheckBoxPregnant.setSelected(!jCheckBoxPregnant.isSelected());
    }//GEN-LAST:event_jCheckBoxPregnantActionPerformed

    private void jButtonSaveDxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveDxActionPerformed
        saveDxForEmployee();
    }//GEN-LAST:event_jButtonSaveDxActionPerformed

    private void jTablePrimarySymptomKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablePrimarySymptomKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_UP
        || evt.getKeyCode()==KeyEvent.VK_DOWN){
            int row = jTablePrimarySymptom.getSelectedRow();
            if(row==-1)     return;
            thePrimarySymptom = (PrimarySymptom)vPrimarySymptom.get(row);        
            getPrimarySymptom(thePrimarySymptom);
        }
    }//GEN-LAST:event_jTablePrimarySymptomKeyReleased

    private void jTablePrimarySymptomMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePrimarySymptomMouseReleased
        int row = jTablePrimarySymptom.getSelectedRow();
        if(row==-1)     return;
        thePrimarySymptom = (PrimarySymptom)vPrimarySymptom.get(row);        
        getPrimarySymptom(thePrimarySymptom);
    }//GEN-LAST:event_jTablePrimarySymptomMouseReleased

    private void jButtonSavePrimarySymptomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSavePrimarySymptomActionPerformed
        PrimarySymptom ps = getPrimarySymptom();
        theHC.theVitalControl.savePrimarySymptom(ps);
        //henbe comment  เพราะว่าตอนบันทึก vital sign ไม่ได้โปรแกรมมันไม่เตือนอะไรออกมาเลยบอกแต่ว่าบันทึกเสร็จสิ้น
//        jButtonSaveVsActionPerformed(null);
    }//GEN-LAST:event_jButtonSavePrimarySymptomActionPerformed

    private void jButtonDelPrimarySymptomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelPrimarySymptomActionPerformed
        int[] row = jTablePrimarySymptom.getSelectedRows();
        theHC.theVitalControl.deletePrimarySymptom(vPrimarySymptom,row);
    }//GEN-LAST:event_jButtonDelPrimarySymptomActionPerformed

    private void jButtonAddPrimarySymptomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddPrimarySymptomActionPerformed
        theUS.setStatus("กรุณากรอกรายการอาการสำคัญ",UpdateStatus.WARNING);
        jTablePrimarySymptom.clearSelection();
        thePrimarySymptom = new PrimarySymptom();
        getPrimarySymptom(thePrimarySymptom);
        jTextAreaMainSymptom.requestFocus();
    }//GEN-LAST:event_jButtonAddPrimarySymptomActionPerformed

   private void jButtonSaveVsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveVsActionPerformed
        this.pDVitalSign1.saveVitalSign();
    }//GEN-LAST:event_jButtonSaveVsActionPerformed

   private void jCheckBoxBalloonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxBalloonActionPerformed
       this.jTextAreaCurrent.setActive(this.jCheckBoxBalloon.isSelected());
       this.jTextAreaMainSymptom.setActive(this.jCheckBoxBalloon.isSelected());
   }//GEN-LAST:event_jCheckBoxBalloonActionPerformed

   private void jCheckBoxBalloonDxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxBalloonDxActionPerformed
       this.jTextFieldDx.setActive(this.jCheckBoxBalloonDx.isSelected());
   }//GEN-LAST:event_jCheckBoxBalloonDxActionPerformed

   private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      this.addFromSelected();
   }//GEN-LAST:event_jButton1ActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAccident;
    private javax.swing.JButton jButtonAddPrimarySymptom;
    private javax.swing.JButton jButtonAdmit;
    private javax.swing.JButton jButtonAppointment;
    private javax.swing.JButton jButtonB2;
    private javax.swing.JButton jButtonChronic;
    private javax.swing.JButton jButtonDeath;
    private javax.swing.JButton jButtonDelPrimarySymptom;
    private javax.swing.JButton jButtonHealthEducation;
    private javax.swing.JButton jButtonLabResult;
    private javax.swing.JButton jButtonPhysicalExam;
    private javax.swing.JButton jButtonPrintGuide;
    private javax.swing.JButton jButtonReDx;
    private javax.swing.JButton jButtonRefer;
    private javax.swing.JButton jButtonRegiterNCD;
    private javax.swing.JButton jButtonSaveDx;
    private javax.swing.JButton jButtonSaveGuide;
    private javax.swing.JButton jButtonSavePrimarySymptom;
    private javax.swing.JButton jButtonSaveVs;
    private javax.swing.JButton jButtonSurveil;
    private javax.swing.JButton jButtonViewDentistry;
    private javax.swing.JButton jButtonViewHistory;
    private javax.swing.JButton jButtonViewNCD;
    private javax.swing.JButton jButtonViewOBGYNCard;
    private javax.swing.JButton jButtonViewPTCard;
    private javax.swing.JButton jButtonViewSpecialClinic;
    private javax.swing.JCheckBox jCheckBoxAdmit;
    private javax.swing.JCheckBox jCheckBoxAppointment;
    private javax.swing.JCheckBox jCheckBoxBalloon;
    private javax.swing.JCheckBox jCheckBoxBalloonDx;
    private javax.swing.JCheckBox jCheckBoxDenyAllergy;
    private javax.swing.JCheckBox jCheckBoxPEText;
    private javax.swing.JCheckBox jCheckBoxPregnant;
    private javax.swing.JCheckBox jCheckBoxRefer;
    private javax.swing.JComboBox jComboBoxAppointment;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelCurrent;
    private javax.swing.JLabel jLabelDx;
    private javax.swing.JLabel jLabelMainSymptom;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelDescription;
    private javax.swing.JPanel jPanelHide;
    private javax.swing.JRadioButton jRadioButtonER;
    private javax.swing.JRadioButton jRadioButtonNUG;
    private javax.swing.JRadioButton jRadioButtonUD;
    private javax.swing.JRadioButton jRadioButtonUG;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPaneCurrent;
    private javax.swing.JScrollPane jScrollPaneMainSymptom;
    private javax.swing.JScrollPane jScrollPaneMainSymptom1;
    private javax.swing.JScrollPane jScrollPaneNote;
    private javax.swing.JScrollPane jScrollPanePhyEx1;
    private javax.swing.JScrollPane jScrollPanePriSym;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private com.hosv3.gui.component.HJTableSort jTablePrimarySymptom;
    private com.hosv3.gui.component.BalloonTextArea jTextAreaCurrent;
    private com.hosv3.gui.component.BalloonTextArea jTextAreaGuide;
    private com.hosv3.gui.component.BalloonTextArea jTextAreaMainSymptom;
    private javax.swing.JTextArea jTextAreaNote;
    private com.hosv3.gui.component.BalloonTextArea jTextAreaPhysicalExam;
    private javax.swing.JTextField jTextFieldAppointment;
    private com.hosv3.gui.component.BalloonTextArea jTextFieldDx;
    private com.hosv3.gui.panel.detail.PDVitalSign pDVitalSign1;
    // End of variables declaration//GEN-END:variables
    
    /**
     * @authen henbe
     * ใครเขียนไม่ยอมบอกอะไรว้า henbe ask
     * ใช้ในการบันทึกข้อมูลDxจากผู้ใช้งาน
     * @deprecated ต้องแก้ให้ใช้งานกับ theDiagDoctorClinic ที่เป็น local และต้องไม่ส่ง employee กับ clinic ไปให้ด้วย
     */
    public void saveDxForEmployee()
    {
        theVisit.diagnosis_note = jTextAreaNote.getText(); 
        theVisit.doctor_dx = jTextFieldDx.getText(); 
        //ต้องการ remove ทุก diag ออกจาก list ทั้งหมด
        //ตรวจสอบว่าได้มีการพิมพข้อความหรือเปล่าหากมีก็ให้ทำการค้น map dx มาใส่ให้ด้วย
        //DxTemplate dxt = theHC.theLookupControl.readDxTemplateByName(jTextFieldDx.getText());
        
        //Constant.println("_________________________if(theVisit.doctor_dx.equals()){");
        if(theVisit.doctor_dx.trim().equals("")){
            Constant.println("true" + theVisit.doctor_dx);
            theHO.vDxTemplate.removeAllElements();
            theHO.vMapVisitDx.removeAllElements();
        }
        
        //Constant.println("_________________________if(theHO.vDxTemplate!=null && theHO.vDxTemplate.isEmpty())");
        if(theHO.vDxTemplate!=null && !theHO.vDxTemplate.isEmpty())
        {
            Constant.println("true" + theHO.vDxTemplate.size());
            int count = theHO.vDxTemplate.size();
            DxTemplate dxt = (DxTemplate)theHO.vDxTemplate.get(count-1);
            //ตรวจสอบว่ามีการจดจำชื่อแพทย์หรือไม่ หากไม่ได้จดจำก็จะให้ชื่อผู้บันทึกเป็นชื่อ default
            //ถ้าหากผู้บันทึกเป็นแพทย์
            if(Gutil.isSelected(theHC.theLookupControl.readOption().auto_diag_icd10))
            {
                theDiagDoctorClinic.doctor_id = theHO.getDoctorIDInVisit();
                theDiagDoctorClinic.clinic_id = dxt.clinic_code;
                theHD.showDialogDiag(theDiagDoctorClinic);
                //ข้อมูลจะถูกบันทึกลงในตัวแปรของ HO นี้อยู่แล้วดังนั้นเราจะต้องเช็คตัวนี้อย่างเดียวก็พอ
                
                Constant.println("_________________________if(dxt.icd_code.indexOf('.')==-1);");
                if(dxt.icd_code.indexOf('.')==-1)
                {
                    Constant.println("true" + dxt.icd_code);
                    MapVisitDx mvd = null;
                    for(int j=0;j<theHO.vMapVisitDx.size();j++){
                        mvd = (MapVisitDx)theHO.vMapVisitDx.get(j);
                        if(mvd.dx_template_id==dxt.getObjectId())
                            break;
                    }
                    Constant.println("_________________________3for(int j=0;j<theHO.vMapVisitDx.size();j++){)"+ mvd==null);
                    if(theDCFT==null)
                        theDCFT = new DialogChooseICD10FromTemplate(theHC,theGPS,theGHS,theUS); 
                    if(dxt.icd_type.equals("2"))
                        theDCFT.showDialog(mvd); 
                }
            }
        }
        else
        {
            String[] dx_array = theVisit.doctor_dx.split("\n");
            if(dx_array!=null && dx_array.length!=0)
            {

                theDiagDoctorClinic.doctor_id = theHO.getDoctorIDInVisit();
                DxTemplate dxTemplate = this.theHC.theVitalControl.readDxTemplateByDes(dx_array[0].trim());
                if(dxTemplate!=null)
                {
                    theDiagDoctorClinic.clinic_id = dxTemplate.clinic_code;
                    theHD.showDialogDiag(theDiagDoctorClinic);
                }
            }
        }
        theHC.theVitalControl.saveDiagDoctor(theVisit,theDiagDoctorClinic); 
    }

   
    private void setEnabledPrimarySymptom(boolean b)
    {
//        jScrollPanePriSym.setEnabled(b);
//        jScrollPaneCurrent.setEnabled(b);
//        jScrollPaneMainSymptom.setEnabled(b);        
        jButtonAddPrimarySymptom.setEnabled(b);
        jButtonDelPrimarySymptom.setEnabled(b);
        jButtonSavePrimarySymptom.setEnabled(b);
        jTextAreaMainSymptom.setEnabled(b);
        jTextAreaCurrent.setEnabled(b);
    }
      
    private void setPrimarySymptomV(Vector vPrimarySymptom1)
    {   
        vPrimarySymptom = vPrimarySymptom1;
        TaBleModel tm ;
        if(vPrimarySymptom==null || vPrimarySymptom.size()==0){
            tm= new TaBleModel(col_jTablePrimarySymptom,0);
            jTablePrimarySymptom.setModel(tm);  
            PrimarySymptom vss = null;
            getPrimarySymptom(vss);            
            return;
        }
        tm = new TaBleModel(col_jTablePrimarySymptom,vPrimarySymptom.size());
        for(int i=0 ;i<vPrimarySymptom.size(); i++)
        {  
            PrimarySymptom ps = (PrimarySymptom)vPrimarySymptom.get(i);
            tm.setValueAt(DateUtil.getDateFromText(ps.record_date_time),i,0);
        }
        int row = jTablePrimarySymptom.getSelectedRow();
        if(row==-1 || row>=vPrimarySymptom.size()) row = 0;        
        jTablePrimarySymptom.setModel(tm); 
        jTablePrimarySymptom.getColumnModel().getColumn(0).setCellRenderer(dateRender);
        jTablePrimarySymptom.setRowSelectionInterval(row,row);
        PrimarySymptom vss = (PrimarySymptom)vPrimarySymptom.get(row);
        getPrimarySymptom(vss);
        //henbe_error/////////////////////////////////////////////////////////        
        /*if(stat.equalsIgnoreCase("7")){
            if(vitaltemplate.equalsIgnoreCase(""))
                vitaltemplate = theHO.theVitalTemplate.description;
            else
                vitaltemplate = vitaltemplate + "," + theHO.theVitalTemplate.description;      
            jTextAreaCurrent.setText(vitaltemplate);             
        }
        if(stat.equalsIgnoreCase("8")) {
            mainsymptom = jTextAreaMainSymptom.getText();
            if(mainsymptom.equalsIgnoreCase(""))
                mainsymptom = theHO.theVitalTemplate.description;
            else
                mainsymptom = mainsymptom + "," + theHO.theVitalTemplate.description;      
            jTextAreaMainSymptom.setText(mainsymptom); 
        }*/
    }
    
    private PrimarySymptom getPrimarySymptom()
    {
        if(thePrimarySymptom==null)
            thePrimarySymptom = new PrimarySymptom();
        thePrimarySymptom.main_symptom = Gutil.CheckReservedWords(jTextAreaMainSymptom.getText());
        thePrimarySymptom.current_illness = Gutil.CheckReservedWords(jTextAreaCurrent.getText()); 
        return thePrimarySymptom;
    }
    
    private String checkNutrition(String nutrition)
    {
        String vital_nutrition = "";
        Vector vNutritionMap = theHC.theLO.vNutritionTypeMap;
        if(vNutritionMap!=null)
        {
            NutritionTypeMap nutritionTypeMap = null;
            for(int i=0,size=vNutritionMap.size(); i<size; i++)
            {
                nutritionTypeMap = (NutritionTypeMap)vNutritionMap.get(i);
                if(nutritionTypeMap.nutrition_old.equals(nutrition))
                {
                    vital_nutrition = nutritionTypeMap.nutrition_new; 
                    break;
                }                
            }
            if("".equals(vital_nutrition))
            {
                vital_nutrition = nutrition;
            }
            nutritionTypeMap = null;
        }
        else
        {
            vital_nutrition = nutrition;
        }
        vNutritionMap = null;        
        return vital_nutrition;
    }
    
    private void getPrimarySymptom(PrimarySymptom ps)
    {           
        thePrimarySymptom = ps;
        if(thePrimarySymptom == null){
            jTextAreaMainSymptom.setText("");
            jTextAreaCurrent.setText(""); 
            return;
        }
        jTextAreaMainSymptom.setText(thePrimarySymptom.main_symptom);
        jTextAreaCurrent.setText(thePrimarySymptom.current_illness);     
    }
    
    /**
     *เซตปุ่มทั้งหมดของหน้า VitalSign
     * check_old ตรวจสอบสถานะในอดีตว่าแก้ไขได้หรือเปล่า ถ้าแก้ไม่ได้อยู่ ก็จะให้แก้ใม่ได้ต่อไป
     */
    public void setPatientEnabled(boolean b)
    {
        jButtonLabResult.setEnabled(b);
        jButtonViewHistory.setEnabled(b);
        //jButtonViewNCD.setEnabled(b);//amp:15/06/2549
        jButtonAccident.setEnabled(b);
        jButtonDeath.setEnabled(b);
        jButtonSurveil.setEnabled(b);
        jButtonChronic.setEnabled(b);
    }
    public void setEnabled(boolean b)
    {
        setPatientEnabled(b);
        setVisitEnabled(b);
    }
    boolean visit_enabled = false;
    public void setVisitEnabled(boolean b)
    {
        visit_enabled = b;
        jCheckBoxDenyAllergy.setEnabled(b);
        jTextFieldDx.setEnabled(b);
        jTextAreaNote.setEnabled(b);
        jButtonSaveDx.setEnabled(b);
//        jTableListPhyEx.setEnabled(b);
        jCheckBoxPregnant.setEnabled(b && is_woman);
        jRadioButtonER.setEnabled(b);
        jRadioButtonNUG.setEnabled(b);
        jRadioButtonUG.setEnabled(b);
        jButtonSaveGuide.setEnabled(b);
        jButtonRegiterNCD.setEnabled(b);//amp:17/06/2549
        jCheckBoxAppointment.setEnabled(b);
        jCheckBoxAdmit.setEnabled(b);
        jCheckBoxRefer.setEnabled(b);
        jButtonAppointment.setEnabled(b);
        jButtonAdmit.setEnabled(b);
        jTextAreaGuide.setEnabled(b);
        jCheckBoxPEText.setEnabled(b); // sumo 25/08/2549
        jTextFieldDx.setEnabled(b);        
        jTextAreaNote.setEnabled(b);
        setEnabledPrimarySymptom(b);
        pDVitalSign1.setEnabled(b);
        jButtonHealthEducation.setEnabled(b);
        jButtonSaveVs.setEnabled(b);        
//        jButtonRefer.setEnabled(b);
        jButtonHealthEducation.setEnabled(b);
//        jButtonPhysicalExam.setEnabled(b);
//        jScrollPaneNote.setEnabled(b);
//        jScrollPanePhyEx.setEnabled(b);
        jButtonPrintGuide.setEnabled(b);
        jTextAreaPhysicalExam.setEnabled(b && jCheckBoxPEText.isSelected());
    }
    
    
    /**
     *อันนี้เข้าใจยากนิดนึง เพราะว่ามันต้องเช็ตว่า ข้อมูล และ การแก้ไขได้หรือไม่
     *หาก Visit ไม่มีค่าก็หมายความว่าเข้าไม่มีทางเข้าไปบันทึกข้อมูลใดๆ หากไม่มี Visit กำกับ
     */  
    private void setVisit(Visit v)
    {
        theVisit = v;
        if(theVisit==null)
        {
            ///setData//////////////////////////////////////////
            jTextFieldDx.setText("");
            jTextAreaNote.setText("");
            jCheckBoxAdmit.setSelected(false);
            jCheckBoxRefer.setSelected(false);
            jRadioButtonUG.setSelected(false);
            jTextFieldAppointment.setText("");
            jComboBoxAppointment.setSelectedIndex(0);
            setVisitEnabled(false);
            jTextAreaGuide.setText("");//amp : 16/02/2549
            return;
        }
        //setData//////////////////////////////////////////////
        jCheckBoxPregnant.setSelected(Gutil.isSelected(theVisit.pregnant));
        String str_dx = v.doctor_dx;
        if(!str_dx.equals("")) str_dx+="\n";
        jTextFieldDx.setText(str_dx);
        jTextAreaNote.setText(v.diagnosis_note);
        jCheckBoxDenyAllergy.setSelected(v.deny_allergy.equals("1"));
        /////////////////////////////////////////////////////////
        jRadioButtonUG.setSelected(false);
        jRadioButtonNUG.setSelected(false);
        jRadioButtonER.setSelected(false);
        jRadioButtonUD.setSelected(true);
        if(v.emergency.equals(EmergencyStatus.EMERGENCY))
            this.jRadioButtonER.setSelected(true);
        else if(v.emergency.equals(EmergencyStatus.NON_URGENT))
            jRadioButtonNUG.setSelected(true);
        else if(v.emergency.equals(EmergencyStatus.URGENT))
            jRadioButtonUG.setSelected(true);
        /////////////////////////////////////////////////////////
        //sumo: 28/07/2549 เพิ่มเงื่อนไขการเช็คว่าผู้ป่วยมีการนัด        
        jComboBoxAppointment.setSelectedIndex(0);
        jTextFieldAppointment.setText("");
        boolean is_appoint = v.have_appointment.equals("1");
        jCheckBoxAppointment.setSelected(is_appoint);
        jTextFieldAppointment.setEnabled(is_appoint);
        jComboBoxAppointment.setEnabled(is_appoint);
        if(is_appoint){
            jTextFieldAppointment.setText(theVisit.cause_appointment);
            Gutil.setGuiData(jComboBoxAppointment, theVisit.cal_date_appointment);
        }
        jCheckBoxAdmit.setSelected(v.have_admit.equals("1"));
        jCheckBoxRefer.setSelected(v.have_refer.equals("1"));
        //ผู้ป่วยออกจากกระบวนการไปแล้ว
        //ผู้ป่วยจำหน่ายทางการแพทย์แล้ว
        //ผู้ป่วยไม่ได้ถูกล้อกโดยผู้ใช้คนนี้
        
        if(theVisit.isLockingByOther(theHO.theEmployee.getObjectId())
        || theVisit.isDischargeDoctor()
        || theVisit.isDropVisit()) {
            setVisitEnabled(false);
            return;
        }   
        setVisitEnabled(true);
        /////////////////////////////////////////////////////////
    }
    public void setLanguage(String msg)
    {         
        GuiLang.setLanguage(col_jTableAllergy);
        GuiLang.setLanguage(col_jTablePrimarySymptom);
        GuiLang.setLanguage(col_jTableVitalSign);
        GuiLang.setLanguage(column_jTableListPhyEx);
        GuiLang.setLanguage(jButtonAccident);
        GuiLang.setLanguage(jButtonAdmit);           
        GuiLang.setLanguage(jButtonAppointment);
        GuiLang.setLanguage(jButtonChronic);
        GuiLang.setLanguage(jButtonDeath);
        GuiLang.setLanguage(jButtonHealthEducation);
        GuiLang.setLanguage(jButtonLabResult); 
        GuiLang.setLanguage(jButtonPhysicalExam);
        GuiLang.setLanguage(jButtonRefer);
        GuiLang.setLanguage(jButtonRegiterNCD);
        GuiLang.setLanguage(jButtonSavePrimarySymptom);
        GuiLang.setLanguage(jButtonSaveVs);
        GuiLang.setLanguage(jButtonSurveil);
        GuiLang.setLanguage(jButtonViewHistory);
        GuiLang.setLanguage(jButtonViewNCD);
        GuiLang.setLanguage(jButtonReDx);
        GuiLang.setLanguage(jCheckBoxAdmit);
        GuiLang.setLanguage(jCheckBoxAppointment);
        GuiLang.setLanguage(jCheckBoxDenyAllergy);
        GuiLang.setLanguage(jCheckBoxPEText);
        GuiLang.setLanguage(jCheckBoxPregnant);
        GuiLang.setLanguage(jCheckBoxRefer);
        GuiLang.setLanguage(jLabel2);
        GuiLang.setLanguage(jLabel8);
        GuiLang.setLanguage(jLabelCurrent);
        GuiLang.setLanguage(jLabelDx);
        GuiLang.setLanguage(jLabelMainSymptom);
        GuiLang.setLanguage(jRadioButtonER);
        GuiLang.setLanguage(jRadioButtonNUG);
        GuiLang.setLanguage(jRadioButtonUG);
        GuiLang.setTextBundle(jPanelDescription);
        GuiLang.setLanguage(pDVitalSign1);
        GuiLang.setLanguage(jCheckBoxBalloon);
        GuiLang.setLanguage(jLabel2);
        GuiLang.setLanguage(jLabelDx);
        GuiLang.setLanguage(jCheckBoxBalloonDx);

    }
    
    /*set Physical Exam ใน vector ให้กับ GUI pu*/
    private void setPhysicalExamNanV(Vector vPENan)
    {
        vPhysicalExamNan = vPENan;
        if(vPhysicalExamNan==null || vPhysicalExamNan.isEmpty())
        {
            setPhysicalExamV(null);
            return ;
        }
        PhysicalExamNan phn = new PhysicalExamNan();
        Vector vPhIn = phn.getForTextArea(vPENan);
        setPhysicalExamV(vPhIn);
    }
    //ไม่มีใครใช้อันนี้แล้วนะ
    private void setPhysicalExamV(Vector vP)
    {
//        Constant.println("setPhysicalExamV()");
        vPhysicalExam = vP;
        String textArea = PhysicalExam.getForTextArea(vPhysicalExam);
        if(!textArea.equals(""))
            textArea += "\n";        
        jTextAreaPhysicalExam.setText(textArea);
    }
    
    
    private void setHosObject(HosObject ho)
    {
        this.setEnabled(true);
        setAuthentication(ho.theEmployee);
        setPatient(ho.thePatient,ho.vDrugAllergy);
        setVisit(ho.theVisit);
        this.pDVitalSign1.setObjectV(ho.vVitalSign);      
        this.pDVitalSign1.setVisit(ho.theVisit);
        setPrimarySymptomV(ho.vPrimarySymptom);      
        setPhysicalExamNanV(ho.vPhysicalExam);
        //setPhysicalExamV(ho.vPhysicalExam);
        setHealthEducationV(ho.vHealthEducation);
        //setGuide(ho.theGuideAfterDxTransaction);        
    }
       
    /**
     *เพื่อคำนวณอายุว่าอยู่ในช่วง 0-6 ปี หรือเปล่า เพื่อนำ check ว่า จะต้อง set ค่าระดับโภชนาการหรือเปล่า
     *ข้อมูลเข้า: ไม่มี
     *ข้อมูลออก: ไม่มี
     */
    boolean is_woman = false;
    private void setPatient(Patient p,Vector allergy)
    {
        thePatient = p;
        boolean patient_not_null = (thePatient!=null);
        if(thePatient!=null)
        {
            String age = DateUtil.calculateAge(thePatient.patient_birthday,theHO.date_time);
            if(age.equals(""))   
                age = "0";    
            
            // ถ้าผู้ป่วยที่เลือกเป็นเพศชายให้ซ่อน CheckBox ตั้งครรภ์ ถ้าเป็นเพศหญิงให้แสดง  sumo 1/8/2549
            is_woman = theHO.thePatient.f_sex_id.equals(Sex.isWOMAN());
        }
        boolean patient_has_ncd = (theHO.vNCD!=null && !theHO.vNCD.isEmpty());
        jButtonViewNCD.setEnabled(patient_not_null && patient_has_ncd);
        jCheckBoxPregnant.setEnabled(is_woman);
    } 
    public void showPatientHistory()
    {
        if(theHO.thePatient==null){
            theUS.setStatus("กรุณาเลือกผู้ป่วย",UpdateStatus.WARNING);
            return;
        }
        if(thePPH==null)
            thePPH = new PanelPatientHistory(theHC,theUS);
        thePPH.showDialog();
    }
    
    public void showPhysicalExam()
    {
        if(theHO.theVisit==null){
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return;
        }
        if(thePPE==null){
            thePPE = new PanelPhysicalExam(theHC,theUS);
        }

        boolean is_enabled = false;
        if(theHO.theVisit==null)
            is_enabled = true;
        else
            is_enabled = !theHO.theVisit.is_discharge_doctor.equals("1")
             && theHO.isLockingVisit();
        thePPE.showDialog(vPhysicalExamNan,is_enabled);
    }
    
    public void showHealthEducation()
    {
        if(theHO.theVisit==null){
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return;
        }
        if(thePHE==null){
            thePHE = new PanelHealthEducation(theHC,theUS);
        }
        if(vHealthEducation==null)
            vHealthEducation = new Vector();
        
        Vector temp = (Vector) vHealthEducation.clone();
        if(thePHE.showDialog(this.vHealthEducation)) {
            if(!jTextAreaGuide.getText().trim().equals("")) {
                if(!theUS.confirmBox(Constant.getTextBundle("พบข้อมูลคำแนะนำสำหรับผู้ป่วย")+" " +
                        Constant.getTextBundle("กรุณายืนยันการบันทึกทับข้อมูลคำแนะนำสำหรับผู้ป่วย"),theUS.WARNING))
                {
                    vHealthEducation = temp;
                    return;
                }
            }
            theHC.theVisitControl.saveGuideHealthEducation(vHealthEducation); 
            
            setHealthEducationV(vHealthEducation);
        }
    }
    
    /**
     *@Author : amp
     *@date : 16/02/2549
     *@see : แสดงคำแนะนำหลังตรวจที่ได้จากการลง dx
     */
//    private void setGuide()
//    {
//        setGuide(theHO.theGuideAfterDxTransaction);
//    }
//    private void setGuide(GuideAfterDxTransaction guideAT)
//    {
//        String guide = "";        
//        theGuideAfterDxTransaction = guideAT;
//        if(theGuideAfterDxTransaction!=null)
//            guide = theGuideAfterDxTransaction.guide;
//
//        if(theHO.vGuide!=null)
//        {          
//             for(int i=0,size=theHO.vGuide.size(); i<size; i++)
//             {
//                if(!"".equals(guide) && !"null".equals(guide))
//                    guide = guide + "\n" + (String)theHO.vGuide.get(i);
//                else
//                    guide = (String)theHO.vGuide.get(i);
//             }
//        }    
//        jTextAreaGuide.setText(guide);
//    }
    
    /**
     *@Author : sumo
     *@date : 20/06/2549
     *@see : แสดงข้อมูลสุขศึกษา
     */
    private void setHealthEducationV(Vector vHealthEdu)
    {
        vHealthEducation = vHealthEdu;
        jTextAreaGuide.setText(
            GuideAfterDxTransaction.toString(vHealthEdu));
    }
    /**
     *@Author : amp
     *@date : 16/02/2549
     *@see : บันทึกคำแนะนำหลังตรวจ
     */
    private void saveGuide(String guide)
    {
        if(!"".equals(guide))
        {
            if(theGuideAfterDxTransaction==null) 
                theGuideAfterDxTransaction = new GuideAfterDxTransaction();        
            theGuideAfterDxTransaction.visit_id = theVisit.getObjectId();
            theGuideAfterDxTransaction.guide = Gutil.CheckReservedWords(guide);
            theHC.theVisitControl.saveGuideDxTR(theGuideAfterDxTransaction); 
            theUS.setStatus("บันทึกคำแนะนำหลังตรวจเสร็จสิ้น",UpdateStatus.COMPLETE);
        }
    }
    
    private boolean getPhysicalExam(Vector vP)
    { 
        if(vP==null)
            return false;
        
        vP.removeAllElements();
        if(jTextAreaPhysicalExam.getText().trim().equals("") || jTextAreaPhysicalExam.getText().trim().equals("\n")){
            return true;
        }
        int line_count = this.jTextAreaPhysicalExam.getLineCount();
        Constant.println("line count " + jTextAreaPhysicalExam.getLineCount());
        int count=0;
        for(int i=0;i<line_count;i++)
        {
            try{
                int start = jTextAreaPhysicalExam.getLineStartOffset(i);
                int end = jTextAreaPhysicalExam.getLineEndOffset(i);
                int char_length = end-start;
                String line = jTextAreaPhysicalExam.getText(start, char_length);
                if(line.length()==0){
                    continue;
                }
                int colon_index = line.indexOf(":");
                if(colon_index==-1){
                    return false;
                }
                else
                    count++;
                // หัว:แตก,มีเลือดออก,ต้องเย็บ
                int start_index = colon_index + 1;
                int end_index = line.indexOf(",");
                String body = line.substring(0,colon_index).trim();
                if(body.equals(""))
                    return false;
                while(end_index!=-1)
                {
                    PhysicalExam pe = new PhysicalExam();
                    pe.physical_body = body;
                    pe.detail = line.substring(start_index,end_index).trim();
                    if(pe.detail.endsWith("\n"))
                        pe.detail = pe.detail.substring(0,pe.detail.length()-1);
                    Constant.println(pe.physical_body + ": " + pe.detail);
                    vP.add(pe);
                    start_index = end_index+1;
                    end_index = line.indexOf(",",start_index);
                } 
                end_index = line.length();
                PhysicalExam pe = new PhysicalExam();
                pe.physical_body = body;
                pe.detail = line.substring(start_index,end_index).trim();
                if(pe.detail.endsWith("\n"))
                    pe.detail = pe.detail.substring(0,pe.detail.length()-1);
                Constant.println(pe.physical_body + ": " + pe.detail);
                vP.add(pe);
            }
            catch(Exception e)
            {
                e.printStackTrace(Constant.getPrintStream());
                return false;
                //Constant.println("PanelVitalSign:" + e.printStackTrace(Constant.getPrintStream()));
            }
        }
        return count>0;
    }
    
    private void getGuide()
    { 
        
        int line_count = this.jTextAreaGuide.getLineCount();
        Constant.println("line count " + jTextAreaGuide.getRows());
        Constant.println("line count " + jTextAreaGuide.getLineCount());
        vGuide = new Vector();
        if(jTextAreaGuide.getText().trim().equals(""))
        {
            return;
        }
        for(int i=0;i<line_count;i++)
        {
            try
            {
                int start = jTextAreaGuide.getLineStartOffset(i);
                int end = jTextAreaGuide.getLineEndOffset(i);
                String line = jTextAreaGuide.getText(start,end-start);
                if(line.endsWith("\n")) 
                    line = line.substring(0, line.length()-1);
                if(!line.trim().equals(""))
                {
                    GuideAfterDxTransaction gu = new GuideAfterDxTransaction();
                    if(line.indexOf(":") > -1)
                    {
                        gu.health_head = line.substring(0,line.indexOf(":"));
                        gu.guide = line.substring(line.indexOf(":")+1,line.length());
                    }
                    else
                    {
                        gu.health_head = "";
                        gu.guide = line;
                    }
                    vGuide.add(gu);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace(Constant.getPrintStream());
                //Constant.println("PanelVitalSign:" + e.printStackTrace(Constant.getPrintStream()));
            }
        }
        return;
    }
    
        
    public void notifyAdmitVisit(String str, int status) {
    }

    public void notifyReadVisit(String str, int status) 
    {
      setHosObject(theHO);
    }


    public void notifyObservVisit(String str, int status) {
    }

    public void notifyUnlockVisit(String str, int status) 
    { 
      setHosObject(theHO);
    }

    public void notifyVisitPatient(String str, int status) 
    {      
      setHosObject(theHO);
    }

    public void notifyManageDrugAllergy(String str, int status) 
    {
        setVisit(theHO.theVisit);
    }

    public void notifyManageVitalSign(String str, int status) 
    {      
        pDVitalSign1.setObjectV(theHO.vVitalSign);       
    }

    public void notifyManageAppointment(String str, int status) 
    {
    }
    public void notifySavePatientPayment(String str, int status) 
    {
    }
    public void notifyCheckDoctorTreament(String str, int status) 
    {
    }
    public void notifyDischargeDoctor(String str, int status) 
    {
      setVisit(theHO.theVisit);      
    }
    public void notifyManagePrimarySymptom(String str, int status) {
      setPrimarySymptomV(theHO.vPrimarySymptom);
    }
    public void notifyDeleteMapVisitDxByVisitId(String str, int status) {
    }
    public void notifySaveDiagDoctor(String str, int status)   {
      setVisit(theHO.theVisit);
      if(theHO.theDxTemplateNew){
          int last = theHO.vDxTemplate.size();
          if(last>0){
              DxTemplate dxt = (DxTemplate)theHO.vDxTemplate.get(last-1);
              String guide = this.jTextAreaGuide.getText();
              guide += dxt.guide_after_dx;
              jTextAreaGuide.setText(guide);
          }
      }
    }
    public void notifySaveMapVisitDx(String str, int status) {
    }
    public void notifyDropVisit(String str, int status) {
      setHosObject(theHO);
    }

    public void notifySendVisit(String str, int status) 
    {
      setHosObject(theHO);
    }

    public void notifyDischargeFinancial(String str, int status) 
    {
      setHosObject(theHO);
    }

    public void notifyManagePayment(String str, int status) 
    {
    }

    public void notifyReverseFinancial(String str, int status)
    {
        this.setEnabled(true);
        setVisit(theHO.theVisit);
    }

    public void notifyManagePhysicalExam(String str, int status) 
    {
        Constant.println("public void notifyManagePhysicalExam(String str, int status) ");
        setPhysicalExamNanV(theHO.vPhysicalExam);
        //setPhysicalExamNanV(theHO.vPhysicalExamNan);
    }

    public void notifyReverseDoctor(String str, int status)
    {
        this.setEnabled(true);
        setVisit(theHO.theVisit);
    }

    public void notifyAddDxTemplate(String str, int status) 
    {
        //setGuide();
    }

    public void notifyDeleteVisitPayment(String str, int status) 
    {
    }

    public void notifyDeletePatientPayment(String str, int status) 
    {
    }

     public void notifyAddPrimarySymptom(String str, int status) 
     {
        String text = jTextAreaMainSymptom.getText();
        if(!text.equals(""))
            jTextAreaMainSymptom.setText(text+","+theHO.theVitalTemplate.description);
        else
            jTextAreaMainSymptom.setText(theHO.theVitalTemplate.description);
    }

    public void notifyReadPatient(String str, int status) 
    {
        setHosObject(theHO);
    }
    public void notifyReadFamily(String str, int status) {
        setHosObject(theHO);
    }

    public void notifyDeletePatient(String str, int status){
    }

    public void notifySavePatient(String str, int status){
        setPatient(thePatient,null);
    }

    public void notifyRemainDoctorDischarge(String str, int status)
    {
        setVisit(theHO.theVisit);      
    }

    public void notifySendVisitBackWard(String str, int status) {
        setHosObject(theHO);
    }

    public void notifySaveAppointment(String str, int status) 
    {
    } 

    public void notifyReverseAdmit(String str, int status) 
    {
    }

    public void notifyResetPatient(String str, int status) 
    {
        setHosObject(theHO);
    }    

    public void notifySaveBorrowFilmXray(String str, int status) {
    }

    public void notifyManagePatientLabReferIn(String str, int status)
    {
    }

    public void notifySaveLabResult(String str, int status)
    {
    }

    public void notifyDeleteLabResult(String str, int status)
    {
    }

    public void notifyDeleteLabOrder(String str, int status)
    {
    }

    public void notifySaveRemainLabResult(String str, int status)
    {
    }

    public void notifySaveFilmXray(String str, int status)
    {
    }

    public void notifyDeleteFilmXray(String str, int status)
    {
    }

    public void notifyDeleteResultXray(String str, int status)
    {
    }

    public void notifySaveXrayPosition(String str, int status)
    {
    }

    public void notifyDeleteXrayPosition(String str, int status)
    {
    }

    public void notifySaveResultXray(String str, int status)
    {
    }

    public void notifyXrayReportComplete(String str, int status)
    {
        setHosObject(theHO);
    }

    public void notifyReportResultLab(String str, int status)
    {
    }

    public void notifyAddLabReferOut(String str, int status)
    {
    }

    public void notifyAddLabReferIn(String str, int status)
    {
    }

    public void notifySendResultLab(String str, int status)
    {
        setHosObject(theHO);
    }

    public void notifyDeleteQueueLab(String str, int status)
    {
        setHosObject(theHO);
    }

    private void addFromSelected() {
        int row = jTablePrimarySymptom.getSelectedRow();
        if (row == -1) {
            return;
        }
        jTablePrimarySymptom.clearSelection();
        PrimarySymptom primarySymptom = (PrimarySymptom) vPrimarySymptom.get(row);
        thePrimarySymptom = new PrimarySymptom();
        thePrimarySymptom.main_symptom = primarySymptom.main_symptom;
        thePrimarySymptom.current_illness = primarySymptom.current_illness;
        getPrimarySymptom(thePrimarySymptom);
        jTextAreaMainSymptom.requestFocus();
    }
}
