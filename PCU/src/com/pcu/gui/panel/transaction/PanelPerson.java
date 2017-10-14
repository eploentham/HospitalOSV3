/*
 * PanelPerson.java
 *
 * Created on 8 กันยายน 2548, 12:11 น.
 */

package com.pcu.gui.panel.transaction;

import com.hosv3.control.PatientControl;
import com.pcu.control.AllComboBoxControl;
import com.pcu.control.HomeControl;
import com.pcu.control.HosManage;
import com.pcu.control.HospitalosControlInf;
import com.pcu.control.PCUObject;
import com.pcu.gui.dialog.HosDialog;
import com.pcu.subject.HomePatientSubject;
import javax.swing.*;/*ใช้ใน main*/
import java.awt.*;
import java.util.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;

import com.hosv3.control.LookupControl;
import com.hosv3.control.MapCon;
import com.hosv3.utility.connection.UpdateStatus;
import com.hosv3.object.*;
import com.hosv3.control.lookup.*;

import com.hosv3.gui.panel.detail.PanelLookup;
import com.pcu.utility.*;
import com.pcu.subject.ManageHomePatient;
import com.pcu.object.*;
import com.pcu.object.Home;
import com.pcu.utility.DateUtil;
/**
 *
 * @author  jao
 * @dpercated henbe unused
 */
public class PanelPerson extends javax.swing.JPanel 
implements ManageHomePatient{
    
   private int show;
   private boolean is_panel_mode;
   private boolean checkComboBox;
   private Patient thePatient;
   private Family theFamily;
   private PatientOldHn thePatientOldHn;
   private Home theHome;
   private AllComboBoxControl theAllComboBoxControl;
   private PatientControl thePatientControl;
   private HospitalosControlInf theHosInf;
   private LookupControl theLookupControl;   
   private HomeControl theHomeControl;
   private HomePatientSubject theHomePatientSubject;
   private HosDialog theHosDialog; 
   private Vector vHome = new Vector();
   private Vector vFamily = new Vector();
   private Vector vPatient = new Vector();
   private Vector vPerson;
   private CelRendererSexType theCelRendererSexType = new CelRendererSexType();
   
   HosManage theHM;
   JDialog frm1;
   String changwat;
   PCUObject thePCUObject;
   
   com.hosv3.utility.connection.UpdateStatus theUS;
   String age;

    private boolean mouse_release;
    private Vector vret;
    private MapCon theMC;
    /**
     * @deprecaed henbe unused
     * Creates new form PanelPerson
     */
    public PanelPerson(HosManage hm,HosDialog hd) 
    {
        this();
        setControl(hm,hd);        
        is_panel_mode = true;
       
    }
    
    public PanelPerson() 
    {
        initComponents();
        is_panel_mode = true;
    }
    /**ส่วนหน้าจอบ้าน**/
    public PanelPerson(HosManage hm,HosDialog hd,int sh,Home home,Family family) 
    {
        initComponents(); 
        setControl(hm,hd);
        setVisibleGui(false);
        is_panel_mode = false;
        if(family!=null){
            Family father = this.thePatientControl.readFamilyByFamilyIdRet(family.father_fid);
            Family mother = this.thePatientControl.readFamilyByFamilyIdRet(family.mother_fid);
            Family couple = this.thePatientControl.readFamilyByFamilyIdRet(family.couple_fid);
            setFamily(family,home,father,mother,couple);
        }
        else
            setFamily(null,null,null,null,null);
       
    }
    public void setControl(HosManage hm,HosDialog hd)
    {
        theHM = hm;
        theHosDialog = hd;
        theMC = new MapCon(MapCon.LOOK_PERSON,theUS,theHM.theHosControl.theConnectionInf);
        setControl(hm.theHC.thePatientControl,hm.theHosControl.theUS
                ,hm.theHosControl.theLookupControl,hm.thePO,hm.theHosControl.theAllComboBoxControl
                ,hm.theHosControl.theHomeControl
                ,hm.theHosSubject.theHomePatientSubject,hm.theHosInf);
    }
    public void setControl(PatientControl pc,UpdateStatus us
            ,LookupControl lc,PCUObject po,AllComboBoxControl ac,HomeControl hc
            ,HomePatientSubject hs,HospitalosControlInf hinf)
    {
        theAllComboBoxControl = ac;
        thePatientControl = pc;
        theHomeControl = hc;
        theHosInf = hinf;
        theLookupControl =  lc;     
        theHomePatientSubject = hs;
        theHomePatientSubject.attachHomePatient(this);      
        thePCUObject = po;
        theUS = us;
        initDatas();
        setDefaultComboBox();
        setVisiblePanel(false);
        setLanguage();
//        this.jLabelWorkOffice.setVisible(false);
//        this.jTextFieldWorkOffice.setVisible(false);
        
    }
    
    public void initDatas() 
    {                 
        jComboBoxPrename.setControl(new PrefixLookup(theLookupControl),true);        
        jComboBoxOccup.setControl(null,new OccupationLookup(theLookupControl),new Occupation2());
        Vector v = theAllComboBoxControl.listAddressCGW();
        ComboboxModel.initComboBox(jComboBoxBlood,theAllComboBoxControl.listBlood());
        ComboboxModel.initComboBox(jComboBoxGender,theAllComboBoxControl.listSex());        
        ComboboxModel.initComboBox(jComboBoxMarriage,theAllComboBoxControl.listMarriage());
        ComboboxModel.initComboBox(jComboBoxRace,theAllComboBoxControl.listRace());
        ComboboxModel.initComboBox(jComboBoxNation,theAllComboBoxControl.listNation());
        ComboboxModel.initComboBox(jComboBoxReligion,theAllComboBoxControl.listReligion());
        ComboboxModel.initComboBox(jComboBoxEducate,theAllComboBoxControl.listEducate());
        ComboboxModel.initComboBox(jComboBoxTypeArea,theAllComboBoxControl.listTypeArea());
        ComboboxModel.initComboBox(jComboBoxVillage,theAllComboBoxControl.listVillage());
        ComboboxModel.initComboBox(jComboBoxDisch,listDischarge());
        // GutilPCU.setGuiData(jComboBoxDisch, "9"); ย้ายไปที setDefaultComboBox
        ComboboxModel.initComboBox(jComboBoxC,v);
        R53PersonForeignLookup lup = new R53PersonForeignLookup();
        ComboboxModel.initComboBox(this.jComboBoxLabor,lup.listData("%"));

        searchHome();
    }    
    public void setHomeV(Home home)
    {
        Vector vAddress = new Vector();
        if(home!=null)
            vAddress.add(home);
        setHomeV(vAddress);
        if(!vAddress.isEmpty())
        {
            jTableHome.setRowSelectionInterval(0,0);
            theHome = (Home)vHome.get(0); 
            setHome(theHome);
        }
        setFamilyV(null);
    }
    public void setObject(PCUObject pcuobject)
    {   
        System.out.println("_henbe_______________________" + this.getClass().toString());
        thePCUObject = pcuobject;
        setFamily(pcuobject.getFamily(),pcuobject.getHome()
                ,pcuobject.getFamilyFather()
                ,pcuobject.getFamilyMother()
                ,pcuobject.getFamilyCouple());
        //henbe comment 100253 ton การบันทึกอายุในฐานข้อมูลจะต้องทำใน control บันทึกคนไข้
        //หรืออะไรก็แล้วแต่จะต้องมาจากฟังชันหลักไม่ใช่ notify อย่างนี้
    }
    public void setHome(Home home)
    {
//        System.out.println("public void setHome(Home home)");
        theHome = home;
        if(theHome==null)
        {
            jLabelHomeNumber.setText("");
            jLabelMoo.setText("");
            jLabelRoad.setText("");
            jLabelTambon.setText("");
            jLabelAmpur.setText("");
            jLabelChangwat.setText("");
            jLabelVillageN.setText("");
            setFamilyV(null);
            return;
        }            
        jLabelHomeNumber.setText(theHome.home_house);
        jLabelMoo.setText(theHome.home_moo);
        jLabelRoad.setText(theHome.home_road);
        if(!theHome.home_changwat.equals(""))
        {
            GutilPCU.setGuiData(jComboBoxC,theHome.home_changwat);
            ComboboxModel.initComboBox(jComboBoxA,theAllComboBoxControl.listAddressAmp(theHome.home_changwat));
            GutilPCU.setGuiData(jComboBoxA,theHome.home_amphur);
            ComboboxModel.initComboBox(jComboBoxT,theAllComboBoxControl.listAddressTmp(theHome.home_changwat,theHome.home_amphur)); 
            GutilPCU.setGuiData(jComboBoxT,theHome.home_tambol);
            jLabelTambon.setText(ComboboxModel.getStringConboBox(jComboBoxT));
            jLabelAmpur.setText(ComboboxModel.getStringConboBox(jComboBoxA));
            jLabelChangwat.setText(ComboboxModel.getStringConboBox(jComboBoxC));
        }
        jLabelVillageN.setText(ComboboxModel.getDescriptionComboBox(jComboBoxVillage,theHome.village_id));
        if(theHome.home_moo.equals("0")||theHome.home_house.equals("0")){
            setFamilyV(null);
            if(mouse_release){
                mouse_release = false;
                if(!theUS.confirmBox("บ้านหลังนี้เป็นบ้านนอกเขต ยืนยันการแสดงประชากรในบ้านหลังนี้",UpdateStatus.WARNING)){
                    return;
                }
            }
            else{
                return;
            }
        }
        
        Vector v = theHomeControl.listFamilyByHomeId(theHome.getObjectId());
        this.setFamilyV(v);
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
        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jPanel13 = new javax.swing.JPanel();
        jButtonSearchPatient = new javax.swing.JButton();
        jButtonClose = new javax.swing.JButton();
        jButtonReset1 = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonaddHome = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jComboBoxPrename = new com.hosv3.gui.component.HosComboBox();
        jLabelFname = new javax.swing.JLabel();
        jTextFieldFname = new javax.swing.JTextField();
        jTextFieldLname = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabelPid = new javax.swing.JLabel();
        pidPanel = new com.hosv3.gui.component.PIDPanel();
        jLabelHCIS = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabelGender = new javax.swing.JLabel();
        jComboBoxGender = new javax.swing.JComboBox();
        jCheckBoxTrueBirthday = new javax.swing.JCheckBox();
        dateComboBoxBirthDay = new com.hospital_os.utility.DateComboBox();
        jLabelAge = new javax.swing.JLabel();
        jTextFieldAge = new com.hospital_os.utility.IntegerTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jComboBoxMarriage = new javax.swing.JComboBox();
        jLabelBlood = new javax.swing.JLabel();
        jComboBoxBlood = new javax.swing.JComboBox();
        jComboBoxOccup = new com.hosv3.gui.component.HosComboBox();
        jPanel6 = new javax.swing.JPanel();
        jComboBoxReligion = new javax.swing.JComboBox();
        jLabelEducate = new javax.swing.JLabel();
        jComboBoxEducate = new javax.swing.JComboBox();
        jLabelReligion = new javax.swing.JLabel();
        jLabelNation = new javax.swing.JLabel();
        jLabelRace = new javax.swing.JLabel();
        jComboBoxNation = new javax.swing.JComboBox();
        jComboBoxRace = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jTextFieldFatherFname = new javax.swing.JTextField();
        jTextFieldFatherLname = new javax.swing.JTextField();
        jTextFieldMotherFname = new javax.swing.JTextField();
        jTextFieldMotherLname = new javax.swing.JTextField();
        jTextFieldCoupleFname = new javax.swing.JTextField();
        jTextFieldCoupleLname = new javax.swing.JTextField();
        jButtonFather = new javax.swing.JButton();
        jButtonMother = new javax.swing.JButton();
        jButtonCouple = new javax.swing.JButton();
        jButtonCancelCouple = new javax.swing.JButton();
        jButtonCancelMother = new javax.swing.JButton();
        jButtonCancelFather = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jComboBoxTypeArea = new javax.swing.JComboBox();
        jPanel35 = new javax.swing.JPanel();
        jRadioButtonTenant = new javax.swing.JRadioButton();
        jRadioButtonOwner = new javax.swing.JRadioButton();
        jLabelFamilyNumber = new javax.swing.JLabel();
        integerTextFieldFamilyNumber = new com.pcu.utility.IntegerTextField();
        jLabelLabor = new javax.swing.JLabel();
        jLabelFstatus = new javax.swing.JLabel();
        jLabelFstatus3 = new javax.swing.JLabel();
        dateComboBoxMoveIn = new com.hospital_os.utility.DateComboBox();
        jLabelMarriage = new javax.swing.JLabel();
        jLabelOccup = new javax.swing.JLabel();
        jLabelFatherName = new javax.swing.JLabel();
        jLabelMotherName = new javax.swing.JLabel();
        jLabelCoupleName = new javax.swing.JLabel();
        jComboBoxLabor = new javax.swing.JComboBox();
        jPanel34 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabelHomeNumber = new javax.swing.JLabel();
        jLabelMoo = new javax.swing.JLabel();
        jLabelRoad = new javax.swing.JLabel();
        jComboBoxT = new javax.swing.JComboBox();
        jComboBoxA = new javax.swing.JComboBox();
        jComboBoxC = new javax.swing.JComboBox();
        jPanel17 = new javax.swing.JPanel();
        jLabelTambon = new javax.swing.JLabel();
        jLabelVillageN = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabelChangwat = new javax.swing.JLabel();
        jLabelAmpur = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabelWorkOffice = new javax.swing.JLabel();
        jTextFieldWorkOffice = new javax.swing.JTextField();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jComboBoxVillage = new javax.swing.JComboBox();
        jButtonRefresh = new javax.swing.JButton();
        jCheckBox = new javax.swing.JCheckBox();
        jTextFieldSearchHome = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHome = new com.hosv3.gui.component.HJTableSort();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePerson = new com.hosv3.gui.component.HJTableSort();
        jPanel8 = new javax.swing.JPanel();
        dateComboBoxDisch = new com.hospital_os.utility.DateComboBox();
        jLabelFstatus1 = new javax.swing.JLabel();
        jLabelFstatus2 = new javax.swing.JLabel();
        jComboBoxDisch = new javax.swing.JComboBox();

        setLayout(new java.awt.GridBagLayout());

        jPanel13.setLayout(new java.awt.GridBagLayout());

        jButtonSearchPatient.setFont(defaultFont1);
        jButtonSearchPatient.setText("Search");
        jButtonSearchPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchPatientActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel13.add(jButtonSearchPatient, gridBagConstraints);

        jButtonClose.setFont(defaultFont1);
        jButtonClose.setText("Close");
        jButtonClose.setMaximumSize(new java.awt.Dimension(75, 25));
        jButtonClose.setMinimumSize(new java.awt.Dimension(75, 25));
        jButtonClose.setPreferredSize(new java.awt.Dimension(75, 25));
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel13.add(jButtonClose, gridBagConstraints);

        jButtonReset1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonReset1.setMaximumSize(new java.awt.Dimension(28, 28));
        jButtonReset1.setMinimumSize(new java.awt.Dimension(28, 28));
        jButtonReset1.setPreferredSize(new java.awt.Dimension(28, 28));
        jButtonReset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReset1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel13.add(jButtonReset1, gridBagConstraints);

        jButtonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelete.setMaximumSize(new java.awt.Dimension(28, 28));
        jButtonDelete.setMinimumSize(new java.awt.Dimension(28, 28));
        jButtonDelete.setPreferredSize(new java.awt.Dimension(28, 28));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel13.add(jButtonDelete, gridBagConstraints);

        jButtonSave.setFont(defaultFont1);
        jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Save16.gif"))); // NOI18N
        jButtonSave.setText("Save");
        jButtonSave.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButtonSave.setMaximumSize(new java.awt.Dimension(100, 26));
        jButtonSave.setMinimumSize(new java.awt.Dimension(60, 26));
        jButtonSave.setPreferredSize(new java.awt.Dimension(80, 26));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel13.add(jButtonSave, gridBagConstraints);

        jButtonaddHome.setFont(defaultFont1);
        jButtonaddHome.setText("เพิ่มบ้าน");
        jButtonaddHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonaddHomeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel13.add(jButtonaddHome, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 2, 5);
        add(jPanel13, gridBagConstraints);

        jPanel10.setBorder(null);
        jPanel10.setMinimumSize(new java.awt.Dimension(430, 413));
        jPanel10.setPreferredSize(new java.awt.Dimension(430, 413));
        jPanel10.setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jComboBoxPrename.setFont(defaultFont1);
        jComboBoxPrename.setMinimumSize(new java.awt.Dimension(80, 21));
        jComboBoxPrename.setPreferredSize(new java.awt.Dimension(80, 21));
        jComboBoxPrename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPrenameActionPerformed(evt);
            }
        });
        jComboBoxPrename.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBoxPrenameFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jComboBoxPrename, gridBagConstraints);

        jLabelFname.setFont(defaultFont1);
        jLabelFname.setText("ชื่อ-สกุล");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabelFname, gridBagConstraints);

        jTextFieldFname.setFont(defaultFont1);
        jTextFieldFname.setMinimumSize(new java.awt.Dimension(88, 21));
        jTextFieldFname.setPreferredSize(new java.awt.Dimension(88, 21));
        jTextFieldFname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFnameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jTextFieldFname, gridBagConstraints);

        jTextFieldLname.setFont(defaultFont1);
        jTextFieldLname.setMinimumSize(new java.awt.Dimension(88, 21));
        jTextFieldLname.setPreferredSize(new java.awt.Dimension(88, 21));
        jTextFieldLname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLnameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jTextFieldLname, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 0, 10);
        jPanel10.add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabelPid.setFont(defaultFont1);
        jLabelPid.setText("เลขบัตรประชาชน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 1, 0);
        jPanel2.add(jLabelPid, gridBagConstraints);

        pidPanel.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 1, 0);
        jPanel2.add(pidPanel, gridBagConstraints);

        jLabelHCIS.setFont(defaultFont1);
        jLabelHCIS.setText("HCIS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel2.add(jLabelHCIS, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 12, 0, 12);
        jPanel10.add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabelGender.setFont(defaultFont1);
        jLabelGender.setText("Sex");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(jLabelGender, gridBagConstraints);

        jComboBoxGender.setFont(defaultFont1);
        jComboBoxGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxGenderActionPerformed(evt);
            }
        });
        jComboBoxGender.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBoxGenderFocusGained(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jComboBoxGender, gridBagConstraints);

        jCheckBoxTrueBirthday.setFont(defaultFont1);
        jCheckBoxTrueBirthday.setText("PetBirthDay");
        jCheckBoxTrueBirthday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTrueBirthdayActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jCheckBoxTrueBirthday, gridBagConstraints);

        dateComboBoxBirthDay.setFont(defaultFont1);
        dateComboBoxBirthDay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateComboBoxBirthDayMouseClicked(evt);
            }
        });
        dateComboBoxBirthDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxBirthDayActionPerformed(evt);
            }
        });
        dateComboBoxBirthDay.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateComboBoxBirthDayFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(dateComboBoxBirthDay, gridBagConstraints);

        jLabelAge.setFont(defaultFont1);
        jLabelAge.setText("Age");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jLabelAge, gridBagConstraints);

        jTextFieldAge.setColumns(3);
        jTextFieldAge.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldAge.setFont(defaultFont1);
        jTextFieldAge.setMinimumSize(new java.awt.Dimension(30, 21));
        jTextFieldAge.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldAgeFocusLost(evt);
            }
        });
        jTextFieldAge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldAgeKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jTextFieldAge, gridBagConstraints);

        jLabel3.setFont(defaultFont1);
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Year");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 12, 0, 12);
        jPanel10.add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jComboBoxMarriage.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jComboBoxMarriage, gridBagConstraints);

        jLabelBlood.setFont(defaultFont1);
        jLabelBlood.setText("BloodGroup");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        jPanel4.add(jLabelBlood, gridBagConstraints);

        jComboBoxBlood.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(jComboBoxBlood, gridBagConstraints);

        jComboBoxOccup.setFont(defaultFont1);
        jComboBoxOccup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxOccupActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel4.add(jComboBoxOccup, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 12);
        jPanel10.add(jPanel4, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jComboBoxReligion.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel6.add(jComboBoxReligion, gridBagConstraints);

        jLabelEducate.setFont(defaultFont1);
        jLabelEducate.setText("การศึกษา");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel6.add(jLabelEducate, gridBagConstraints);

        jComboBoxEducate.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel6.add(jComboBoxEducate, gridBagConstraints);

        jLabelReligion.setFont(defaultFont1);
        jLabelReligion.setText("ศาสนา");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 0, 0);
        jPanel6.add(jLabelReligion, gridBagConstraints);

        jLabelNation.setFont(defaultFont1);
        jLabelNation.setText("เชื้อชาติ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel6.add(jLabelNation, gridBagConstraints);

        jLabelRace.setFont(defaultFont1);
        jLabelRace.setText("สัญชาติ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel6.add(jLabelRace, gridBagConstraints);

        jComboBoxNation.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel6.add(jComboBoxNation, gridBagConstraints);

        jComboBoxRace.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel6.add(jComboBoxRace, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 12, 0, 12);
        jPanel10.add(jPanel6, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jTextFieldFatherFname.setFont(defaultFont1);
        jTextFieldFatherFname.setMinimumSize(new java.awt.Dimension(88, 21));
        jTextFieldFatherFname.setPreferredSize(new java.awt.Dimension(88, 21));
        jTextFieldFatherFname.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldFatherFnameCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel7.add(jTextFieldFatherFname, gridBagConstraints);

        jTextFieldFatherLname.setFont(defaultFont1);
        jTextFieldFatherLname.setMinimumSize(new java.awt.Dimension(88, 21));
        jTextFieldFatherLname.setPreferredSize(new java.awt.Dimension(88, 21));
        jTextFieldFatherLname.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldFatherLnameCaretUpdate(evt);
            }
        });
        jTextFieldFatherLname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldFatherLnameFocusGained(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jTextFieldFatherLname, gridBagConstraints);

        jTextFieldMotherFname.setFont(defaultFont1);
        jTextFieldMotherFname.setMinimumSize(new java.awt.Dimension(88, 21));
        jTextFieldMotherFname.setPreferredSize(new java.awt.Dimension(88, 21));
        jTextFieldMotherFname.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldMotherFnameCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel7.add(jTextFieldMotherFname, gridBagConstraints);

        jTextFieldMotherLname.setFont(defaultFont1);
        jTextFieldMotherLname.setMinimumSize(new java.awt.Dimension(88, 21));
        jTextFieldMotherLname.setPreferredSize(new java.awt.Dimension(88, 21));
        jTextFieldMotherLname.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldMotherLnameCaretUpdate(evt);
            }
        });
        jTextFieldMotherLname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldMotherLnameFocusGained(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel7.add(jTextFieldMotherLname, gridBagConstraints);

        jTextFieldCoupleFname.setFont(defaultFont1);
        jTextFieldCoupleFname.setMinimumSize(new java.awt.Dimension(88, 21));
        jTextFieldCoupleFname.setPreferredSize(new java.awt.Dimension(88, 21));
        jTextFieldCoupleFname.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldCoupleFnameCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel7.add(jTextFieldCoupleFname, gridBagConstraints);

        jTextFieldCoupleLname.setFont(defaultFont1);
        jTextFieldCoupleLname.setMinimumSize(new java.awt.Dimension(88, 21));
        jTextFieldCoupleLname.setPreferredSize(new java.awt.Dimension(88, 21));
        jTextFieldCoupleLname.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldCoupleLnameCaretUpdate(evt);
            }
        });
        jTextFieldCoupleLname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCoupleLnameFocusGained(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel7.add(jTextFieldCoupleLname, gridBagConstraints);

        jButtonFather.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonFather.setText("...");
        jButtonFather.setMaximumSize(new java.awt.Dimension(22, 22));
        jButtonFather.setMinimumSize(new java.awt.Dimension(22, 22));
        jButtonFather.setPreferredSize(new java.awt.Dimension(22, 22));
        jButtonFather.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFatherActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        jPanel7.add(jButtonFather, gridBagConstraints);

        jButtonMother.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonMother.setText("...");
        jButtonMother.setMaximumSize(new java.awt.Dimension(22, 22));
        jButtonMother.setMinimumSize(new java.awt.Dimension(22, 22));
        jButtonMother.setPreferredSize(new java.awt.Dimension(22, 22));
        jButtonMother.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMotherActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        jPanel7.add(jButtonMother, gridBagConstraints);

        jButtonCouple.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonCouple.setText("...");
        jButtonCouple.setMaximumSize(new java.awt.Dimension(22, 22));
        jButtonCouple.setMinimumSize(new java.awt.Dimension(22, 22));
        jButtonCouple.setPreferredSize(new java.awt.Dimension(22, 22));
        jButtonCouple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCoupleActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        jPanel7.add(jButtonCouple, gridBagConstraints);

        jButtonCancelCouple.setText("x");
        jButtonCancelCouple.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonCancelCouple.setMaximumSize(new java.awt.Dimension(22, 22));
        jButtonCancelCouple.setMinimumSize(new java.awt.Dimension(22, 22));
        jButtonCancelCouple.setPreferredSize(new java.awt.Dimension(22, 22));
        jButtonCancelCouple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelCoupleActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        jPanel7.add(jButtonCancelCouple, gridBagConstraints);

        jButtonCancelMother.setText("x");
        jButtonCancelMother.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonCancelMother.setMaximumSize(new java.awt.Dimension(22, 22));
        jButtonCancelMother.setMinimumSize(new java.awt.Dimension(22, 22));
        jButtonCancelMother.setPreferredSize(new java.awt.Dimension(22, 22));
        jButtonCancelMother.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelMotherActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        jPanel7.add(jButtonCancelMother, gridBagConstraints);

        jButtonCancelFather.setText("x");
        jButtonCancelFather.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonCancelFather.setMaximumSize(new java.awt.Dimension(22, 22));
        jButtonCancelFather.setMinimumSize(new java.awt.Dimension(22, 22));
        jButtonCancelFather.setPreferredSize(new java.awt.Dimension(22, 22));
        jButtonCancelFather.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelFatherActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        jPanel7.add(jButtonCancelFather, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 12);
        jPanel10.add(jPanel7, gridBagConstraints);

        jPanel16.setLayout(new java.awt.GridBagLayout());

        jComboBoxTypeArea.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel16.add(jComboBoxTypeArea, gridBagConstraints);

        jPanel35.setLayout(new java.awt.GridBagLayout());

        buttonGroup1.add(jRadioButtonTenant);
        jRadioButtonTenant.setFont(defaultFont1);
        jRadioButtonTenant.setSelected(true);
        jRadioButtonTenant.setText("ผู้อาศัย");
        jRadioButtonTenant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTenantActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel35.add(jRadioButtonTenant, gridBagConstraints);

        buttonGroup1.add(jRadioButtonOwner);
        jRadioButtonOwner.setFont(defaultFont1);
        jRadioButtonOwner.setText("OwnerHome");
        jRadioButtonOwner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonOwnerActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel35.add(jRadioButtonOwner, gridBagConstraints);

        jLabelFamilyNumber.setFont(defaultFont1);
        jLabelFamilyNumber.setText("ครอบครัวที่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        jPanel35.add(jLabelFamilyNumber, gridBagConstraints);

        integerTextFieldFamilyNumber.setColumns(2);
        integerTextFieldFamilyNumber.setFont(defaultFont1);
        integerTextFieldFamilyNumber.setMinimumSize(new java.awt.Dimension(30, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel35.add(integerTextFieldFamilyNumber, gridBagConstraints);

        jLabelLabor.setText("ประเภทคนต่างด้าว");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel35.add(jLabelLabor, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel16.add(jPanel35, gridBagConstraints);

        jLabelFstatus.setFont(defaultFont1);
        jLabelFstatus.setText("ใน/นอกเขต");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel16.add(jLabelFstatus, gridBagConstraints);

        jLabelFstatus3.setFont(defaultFont1);
        jLabelFstatus3.setText("วันที่ย้ายเข้า");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel16.add(jLabelFstatus3, gridBagConstraints);

        dateComboBoxMoveIn.setFont(defaultFont1);
        dateComboBoxMoveIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateComboBoxMoveInMouseClicked(evt);
            }
        });
        dateComboBoxMoveIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxMoveInActionPerformed(evt);
            }
        });
        dateComboBoxMoveIn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateComboBoxMoveInFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel16.add(dateComboBoxMoveIn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 12, 0, 12);
        jPanel10.add(jPanel16, gridBagConstraints);

        jLabelMarriage.setFont(defaultFont1);
        jLabelMarriage.setText("สถานภาพ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 12, 0, 0);
        jPanel10.add(jLabelMarriage, gridBagConstraints);

        jLabelOccup.setFont(defaultFont1);
        jLabelOccup.setText("อาชีพ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 12, 0, 0);
        jPanel10.add(jLabelOccup, gridBagConstraints);

        jLabelFatherName.setFont(defaultFont1);
        jLabelFatherName.setText("FatherName");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 0, 0);
        jPanel10.add(jLabelFatherName, gridBagConstraints);

        jLabelMotherName.setFont(defaultFont1);
        jLabelMotherName.setText("MotherName");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 0, 0);
        jPanel10.add(jLabelMotherName, gridBagConstraints);

        jLabelCoupleName.setFont(defaultFont1);
        jLabelCoupleName.setText("ชื่อคู่สมรส");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 0, 0);
        jPanel10.add(jLabelCoupleName, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 16, 0, 12);
        jPanel10.add(jComboBoxLabor, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jPanel10, gridBagConstraints);

        jPanel34.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(defaultFont1);
        jLabel4.setForeground(java.awt.Color.blue);
        jLabel4.setText("HomeNumber");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 1, 0);
        jPanel34.add(jLabel4, gridBagConstraints);

        jLabel6.setFont(defaultFont1);
        jLabel6.setForeground(java.awt.Color.blue);
        jLabel6.setText("Moo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 12, 1, 0);
        jPanel34.add(jLabel6, gridBagConstraints);

        jLabel9.setFont(defaultFont1);
        jLabel9.setForeground(java.awt.Color.blue);
        jLabel9.setText("Road");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 12, 0, 0);
        jPanel34.add(jLabel9, gridBagConstraints);

        jLabelHomeNumber.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel34.add(jLabelHomeNumber, gridBagConstraints);

        jLabelMoo.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel34.add(jLabelMoo, gridBagConstraints);

        jLabelRoad.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel34.add(jLabelRoad, gridBagConstraints);

        jComboBoxT.setFont(defaultFont1);
        jComboBoxT.setEnabled(false);
        jComboBoxT.setMaximumSize(new java.awt.Dimension(28, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        jPanel34.add(jComboBoxT, gridBagConstraints);

        jComboBoxA.setFont(defaultFont1);
        jComboBoxA.setEnabled(false);
        jComboBoxA.setMaximumSize(new java.awt.Dimension(28, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        jPanel34.add(jComboBoxA, gridBagConstraints);

        jComboBoxC.setFont(defaultFont1);
        jComboBoxC.setEnabled(false);
        jComboBoxC.setMaximumSize(new java.awt.Dimension(28, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        jPanel34.add(jComboBoxC, gridBagConstraints);

        jPanel17.setLayout(new java.awt.GridBagLayout());

        jLabelTambon.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel17.add(jLabelTambon, gridBagConstraints);

        jLabelVillageN.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel17.add(jLabelVillageN, gridBagConstraints);

        jLabel10.setFont(defaultFont1);
        jLabel10.setForeground(java.awt.Color.blue);
        jLabel10.setText("Village:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel17.add(jLabel10, gridBagConstraints);

        jLabel14.setFont(defaultFont1);
        jLabel14.setForeground(java.awt.Color.blue);
        jLabel14.setText("ตำบล:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        jPanel17.add(jLabel14, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel34.add(jPanel17, gridBagConstraints);

        jPanel18.setLayout(new java.awt.GridBagLayout());

        jLabelChangwat.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel18.add(jLabelChangwat, gridBagConstraints);

        jLabelAmpur.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel18.add(jLabelAmpur, gridBagConstraints);

        jLabel16.setFont(defaultFont1);
        jLabel16.setForeground(java.awt.Color.blue);
        jLabel16.setText("จังหวัด:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 10, 1, 0);
        jPanel18.add(jLabel16, gridBagConstraints);

        jLabel15.setFont(defaultFont1);
        jLabel15.setForeground(java.awt.Color.blue);
        jLabel15.setText("อำเภอ:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        jPanel18.add(jLabel15, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel34.add(jPanel18, gridBagConstraints);

        jLabelWorkOffice.setFont(defaultFont1);
        jLabelWorkOffice.setText("สถานที่ทำงาน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel34.add(jLabelWorkOffice, gridBagConstraints);

        jTextFieldWorkOffice.setFont(defaultFont1);
        jTextFieldWorkOffice.setMinimumSize(new java.awt.Dimension(88, 21));
        jTextFieldWorkOffice.setPreferredSize(new java.awt.Dimension(88, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel34.add(jTextFieldWorkOffice, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 5, 0, 5);
        add(jPanel34, gridBagConstraints);

        jSplitPane1.setDividerLocation(150);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        jPanel14.setLayout(new java.awt.GridBagLayout());

        jComboBoxVillage.setFont(defaultFont1);
        jComboBoxVillage.setMinimumSize(new java.awt.Dimension(100, 20));
        jComboBoxVillage.setPreferredSize(new java.awt.Dimension(100, 20));
        jComboBoxVillage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxVillageActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel14.add(jComboBoxVillage, gridBagConstraints);

        jButtonRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pcu/images/Refresh2.gif"))); // NOI18N
        jButtonRefresh.setToolTipText("Refresh");
        jButtonRefresh.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonRefresh.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonRefresh.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weighty = 1.0;
        jPanel14.add(jButtonRefresh, gridBagConstraints);

        jCheckBox.setFont(defaultFont1);
        jCheckBox.setText("หมู่");
        jCheckBox.setToolTipText("แสดงบ้านทุกหลังในทุกหมุ่บ้าน");
        jCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel14.add(jCheckBox, gridBagConstraints);

        jTextFieldSearchHome.setFont(defaultFont1);
        jTextFieldSearchHome.setMinimumSize(new java.awt.Dimension(150, 21));
        jTextFieldSearchHome.setPreferredSize(new java.awt.Dimension(150, 21));
        jTextFieldSearchHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchHomeActionPerformed(evt);
            }
        });
        jTextFieldSearchHome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchHomeKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel14.add(jTextFieldSearchHome, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel12.add(jPanel14, gridBagConstraints);

        jScrollPane1.setBorder(null);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 100));

        jTableHome.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jTableHome.setFont(defaultFont1);
        jTableHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableHomeMouseReleased(evt);
            }
        });
        jTableHome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableHomeKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableHome);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        jPanel12.add(jScrollPane1, gridBagConstraints);

        jSplitPane1.setLeftComponent(jPanel12);

        jScrollPane2.setBorder(null);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(100, 100));

        jTablePerson.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jTablePerson.setFont(defaultFont1);
        jTablePerson.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTablePersonMouseReleased(evt);
            }
        });
        jTablePerson.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTablePersonKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTablePerson);

        jSplitPane1.setRightComponent(jScrollPane2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jSplitPane1, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        dateComboBoxDisch.setFont(defaultFont1);
        dateComboBoxDisch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateComboBoxDischMouseClicked(evt);
            }
        });
        dateComboBoxDisch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxDischActionPerformed(evt);
            }
        });
        dateComboBoxDisch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateComboBoxDischFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel8.add(dateComboBoxDisch, gridBagConstraints);

        jLabelFstatus1.setFont(defaultFont1);
        jLabelFstatus1.setText("วันที่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel8.add(jLabelFstatus1, gridBagConstraints);

        jLabelFstatus2.setFont(defaultFont1);
        jLabelFstatus2.setText("จำหน่าย");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel8.add(jLabelFstatus2, gridBagConstraints);

        jComboBoxDisch.setFont(defaultFont1);
        jComboBoxDisch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 ตาย", "2 ย้าย", "3 สาปสูญ", "9 ไม่จำหน่าย" }));
        jComboBoxDisch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDischActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel8.add(jComboBoxDisch, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        add(jPanel8, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldAgeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAgeKeyReleased
        jTextFieldAgeFocusLost(null);
    }//GEN-LAST:event_jTextFieldAgeKeyReleased

    private void jTablePersonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablePersonKeyReleased
        if(evt.getKeyCode()==evt.VK_UP
        || evt.getKeyCode()==evt.VK_DOWN)
            selectPerson();
    }//GEN-LAST:event_jTablePersonKeyReleased

    private void jTextFieldSearchHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchHomeActionPerformed
        searchHome();
    }//GEN-LAST:event_jTextFieldSearchHomeActionPerformed

    private void jTableHomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableHomeKeyReleased
// TODO add your handling code here:
        if(evt.getKeyCode()==evt.VK_UP
        || evt.getKeyCode()==evt.VK_DOWN){ 
            
            int row = jTableHome.getSelectedRow();
            setHome((Home)vHome.get(row));
        }
    }//GEN-LAST:event_jTableHomeKeyReleased

    private void jTablePersonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTablePersonMouseReleased
    {//GEN-HEADEREND:event_jTablePersonMouseReleased
        selectPerson();
    }//GEN-LAST:event_jTablePersonMouseReleased

    private void jComboBoxPrenameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxPrenameFocusLost
// TODO add your handling code here:
        jComboBoxPrenameActionPerformed(null);
    }//GEN-LAST:event_jComboBoxPrenameFocusLost

    private void jButtonaddHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonaddHomeActionPerformed
        PanelHome thePanelHome = new PanelHome(theHM,theHosDialog,theUS); 
        thePanelHome.showPanel(thePCUObject); 
    }//GEN-LAST:event_jButtonaddHomeActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        
//        System.out.println("private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {");
        int ret = theHomeControl.deleteFamily(theFamily,thePatient);
        if(ret==0) 
            return;
        if(is_panel_mode)
            theHosInf.resetPatient();
        jTableHome.clearSelection();
        jTablePerson.clearSelection();
        setFamily(null,null,null,null,null);
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonReset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReset1ActionPerformed
        if(is_panel_mode)
            theHosInf.resetPatient();
        jTableHome.clearSelection();
        jTablePerson.clearSelection();
        setFamily(null,null,null,null,null);
    }//GEN-LAST:event_jButtonReset1ActionPerformed
 
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        
        int ret = theHomeControl.saveFamily(getFamily(),jTextFieldAge.getText(),is_panel_mode,theUS);
        if(frm1!=null)
            frm1.dispose();
//        else{
//            if(ret!=0 && is_panel_mode){  
//                setObject(thePCUObject);
//            }
//        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        ComboboxModel.initComboBox(jComboBoxVillage,theAllComboBoxControl.listVillage());
    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void jComboBoxGenderFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxGenderFocusGained
        boolean result = thePatientControl.checkPatientPid(pidPanel.getText());
        if(theFamily!=null && theFamily.getObjectId()==null && result)
        {
             theUS.setStatus("หมายเลขบัตรประชาชนซ้ำกับผู้ป่วยคนอื่น กรุณากรอกหมายเลขบัตรประชาชนใหม่อีกครั้ง",UpdateStatus.WARNING);
             pidPanel.requestFocus();
        } 
    }//GEN-LAST:event_jComboBoxGenderFocusGained

    private void jComboBoxGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxGenderActionPerformed
        /*
        try{
            Prefix p = (Prefix)jComboBoxPrename.getSelectedItem();
            String sex = GutilPCU.getGuiData(jComboBoxGender);
        }catch(Exception e){
            e.printStackTrace();
        } */      
    }//GEN-LAST:event_jComboBoxGenderActionPerformed

    private void jTextFieldLnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLnameActionPerformed
        if(is_panel_mode)
            jButtonSearchPatientActionPerformed(null);
    }//GEN-LAST:event_jTextFieldLnameActionPerformed

    private void jTextFieldFnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFnameActionPerformed
       if(is_panel_mode)
            jButtonSearchPatientActionPerformed(null);
    }//GEN-LAST:event_jTextFieldFnameActionPerformed

    private void jRadioButtonOwnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonOwnerActionPerformed

    }//GEN-LAST:event_jRadioButtonOwnerActionPerformed

    private void jRadioButtonTenantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTenantActionPerformed
        
    }//GEN-LAST:event_jRadioButtonTenantActionPerformed

    private void jButtonSearchPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchPatientActionPerformed

        theHosDialog.showDialogSearchPatientPCU(theHM,jTextFieldFname.getText(),jTextFieldLname.getText(),"");
    }//GEN-LAST:event_jButtonSearchPatientActionPerformed

    private void jTableHomeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHomeMouseReleased
        mouse_release = true;
        int row = jTableHome.getSelectedRow();
        setHome((Home)vHome.get(row));
        mouse_release = false;
    }//GEN-LAST:event_jTableHomeMouseReleased

    private void jCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxActionPerformed
        searchHome();  
    }//GEN-LAST:event_jCheckBoxActionPerformed

    private void jTextFieldSearchHomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchHomeKeyReleased
// TODO add your handling code here:
//      if(jTextFieldSearchHome.getText().length()>2)
//        searchHome();
//      else if (jTextFieldSearchHome.getText().equals(""))
//        searchHome();
    }//GEN-LAST:event_jTextFieldSearchHomeKeyReleased

    private void jComboBoxVillageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxVillageActionPerformed
        searchHome();
    }//GEN-LAST:event_jComboBoxVillageActionPerformed

    private void jTextFieldCoupleLnameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldCoupleLnameCaretUpdate
// TODO add your handling code here:
        //jTextFieldCoupleName.setText(jTextFieldCoupleFname.getText()+" "+jTextFieldCoupleLname.getText());
    }//GEN-LAST:event_jTextFieldCoupleLnameCaretUpdate

    private void jTextFieldCoupleFnameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldCoupleFnameCaretUpdate
// TODO add your handling code here:
       // jTextFieldCoupleName.setText(jTextFieldCoupleFname.getText()+" "+jTextFieldCoupleLname.getText());
    }//GEN-LAST:event_jTextFieldCoupleFnameCaretUpdate

    private void jTextFieldMotherLnameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldMotherLnameCaretUpdate
// TODO add your handling code here:
       // jTextFieldMotherName.setText(jTextFieldMotherFname.getText()+" "+jTextFieldMotherLname.getText());
    }//GEN-LAST:event_jTextFieldMotherLnameCaretUpdate

    private void jTextFieldFatherLnameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldFatherLnameCaretUpdate
// TODO add your handling code here:
         //jTextFieldFatherName.setText(jTextFieldFatherFname.getText()+" "+jTextFieldFatherLname.getText());
    }//GEN-LAST:event_jTextFieldFatherLnameCaretUpdate

    private void jTextFieldMotherFnameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldMotherFnameCaretUpdate
// TODO add your handling code here:
        //jTextFieldMotherName.setText(jTextFieldMotherFname.getText()+" "+jTextFieldMotherLname.getText());
    }//GEN-LAST:event_jTextFieldMotherFnameCaretUpdate

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
// TODO add your handling code here:
         frm1.dispose();
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jTextFieldCoupleLnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCoupleLnameFocusGained
// TODO add your handling code here:
        if(!jTextFieldCoupleFname.getText().trim().equals(""))
        {
            if(jTextFieldCoupleLname.getText().trim().equals(""))
            {
                GutilPCU.setGuiData(jTextFieldCoupleLname,GutilPCU.getGuiData(jTextFieldLname));
            }
        }
    }//GEN-LAST:event_jTextFieldCoupleLnameFocusGained

    private void jTextFieldMotherLnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldMotherLnameFocusGained
// TODO add your handling code here:
        if(!jTextFieldMotherFname.getText().trim().equals(""))
        {
            if(jTextFieldMotherLname.getText().trim().equals(""))
            {
                GutilPCU.setGuiData(jTextFieldMotherLname,GutilPCU.getGuiData(jTextFieldLname));
            }
        }
    }//GEN-LAST:event_jTextFieldMotherLnameFocusGained

    private void jTextFieldFatherLnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldFatherLnameFocusGained
// TODO add your handling code here:
        if(!jTextFieldFatherFname.getText().trim().equals(""))
        {
            if(jTextFieldFatherLname.getText().trim().equals(""))
            {
                GutilPCU.setGuiData(jTextFieldFatherLname,GutilPCU.getGuiData(jTextFieldLname));
            }
        }       
    }//GEN-LAST:event_jTextFieldFatherLnameFocusGained

    private void jTextFieldFatherFnameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldFatherFnameCaretUpdate
// TODO add your handling code here:
      //jTextFieldFatherName.setText(jTextFieldFatherFname.getText()+" "+jTextFieldFatherLname.getText());
    }//GEN-LAST:event_jTextFieldFatherFnameCaretUpdate

    private void jComboBoxOccupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxOccupActionPerformed
// TODO add your handling code here:
        //jTextFieldOcc.setText(jComboBoxOccup.getText()); 
    }//GEN-LAST:event_jComboBoxOccupActionPerformed

    private void jTextFieldAgeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldAgeFocusLost
// TODO add your handling code here:
        if(!jTextFieldAge.getText().equals(""))
        {
            int age = Integer.parseInt(jTextFieldAge.getText());
            int yearCurr = Integer.parseInt(thePCUObject.getCurrentDateTime().substring(0,4));
            String yearBirth = String.valueOf(yearCurr-age);
            dateComboBoxBirthDay.setText("01/07/" + yearBirth);   
        }
    }//GEN-LAST:event_jTextFieldAgeFocusLost

    private void dateComboBoxBirthDayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxBirthDayFocusLost
        /*String bday = dateComboBoxBirthDay.getText();                 
        age = DateUtil.calculateAge(bday,theHM.theHosControl.theConnectionInf);
        jTextFieldAge.setEnabled(true);
        jTextFieldAge.setText(age);
        jTextFieldAge.setEditable(true);*/  
    }//GEN-LAST:event_dateComboBoxBirthDayFocusLost

    private void dateComboBoxBirthDayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateComboBoxBirthDayMouseClicked

        checkComboBox = false;
    }//GEN-LAST:event_dateComboBoxBirthDayMouseClicked

    private void dateComboBoxBirthDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxBirthDayActionPerformed
       String bdate = dateComboBoxBirthDay.getText();
       if(bdate!=null && bdate.length()>0 && bdate.length()!=10) 
       {
            theUS.setStatus("กรุณากรอกวันเกิดให้ถูกรูปแบบ วว/ดด/ปปปป",theUS.WARNING);            
            return;
        }
        String date_time = thePCUObject.getCurrentDateTime();
        if(!bdate.equals("") && com.hosv3.utility.DateUtil.countDateDiff(bdate,date_time)> 0) 
        {
            theUS.setStatus("กรุณาระบุวันเกิดเป็นวันในอดีต", theUS.WARNING);            
            return; 
        }
        if(!bdate.equals(""))
        {
            String age = DateUtil.calculateAge(bdate,date_time);
            jTextFieldAge.setText(age);
        }
        
    }//GEN-LAST:event_dateComboBoxBirthDayActionPerformed

    private void jCheckBoxTrueBirthdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTrueBirthdayActionPerformed

        if(jCheckBoxTrueBirthday.isSelected()){
            dateComboBoxBirthDay.setEnabled(true);
            jTextFieldAge.setEnabled(false);               
        }
        else{
            dateComboBoxBirthDay.setEnabled(false);
            jTextFieldAge.setEnabled(true); 
        }
        checkComboBox = false;
    }//GEN-LAST:event_jCheckBoxTrueBirthdayActionPerformed

    private void jComboBoxPrenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPrenameActionPerformed

        try{           
            Prefix thePrefix = (Prefix)jComboBoxPrename.getSelectedItem();
            if(thePrefix!=null){
                GutilPCU.setGuiData(jComboBoxGender, thePrefix.sex);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_jComboBoxPrenameActionPerformed

    private void dateComboBoxMoveInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateComboBoxMoveInMouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_dateComboBoxMoveInMouseClicked

    private void dateComboBoxMoveInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxMoveInActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_dateComboBoxMoveInActionPerformed

    private void dateComboBoxMoveInFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxMoveInFocusLost
        // TODO add your handling code here:
}//GEN-LAST:event_dateComboBoxMoveInFocusLost

    private void dateComboBoxDischMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateComboBoxDischMouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_dateComboBoxDischMouseClicked

    private void dateComboBoxDischActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxDischActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_dateComboBoxDischActionPerformed

    private void dateComboBoxDischFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxDischFocusLost
        // TODO add your handling code here:
}//GEN-LAST:event_dateComboBoxDischFocusLost

    private void jComboBoxDischActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDischActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxDischActionPerformed

    private void jButtonFatherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFatherActionPerformed
        String family_id = PanelLookup.showDialog(theUS.getJFrame(),theMC
                ,this.jTextFieldFatherFname.getText()+" "+this.jTextFieldFatherLname.getText());
        if(family_id==null)
            return;
        theFamily.father_fid = family_id;
        Family fam = this.thePatientControl.readFamilyByFamilyIdRet(family_id);
        Prefix2 p_father = this.theHM.theHC.theLookupControl.readPrefixById(fam.f_prefix_id);
        setTextFieldDE(jTextFieldFatherFname,p_father.getName() + fam.patient_name);
        setTextFieldDE(jTextFieldFatherLname,fam.patient_last_name);
        theFamily.father_firstname = fam.patient_name;
        theFamily.father_lastname = fam.patient_last_name;
        theFamily.father_pid = fam.pid;
    }//GEN-LAST:event_jButtonFatherActionPerformed

    private void jButtonMotherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMotherActionPerformed
        String family_id = PanelLookup.showDialog(theUS.getJFrame(),theMC
                ,this.jTextFieldMotherFname.getText()+" "+this.jTextFieldMotherLname.getText());
        if(family_id==null)
            return;
        theFamily.mother_fid = family_id;
        Family fam = this.thePatientControl.readFamilyByFamilyIdRet(family_id);
        Prefix2 p_mother = this.theHM.theHC.theLookupControl.readPrefixById(fam.f_prefix_id);
        setTextFieldDE(jTextFieldMotherFname,p_mother.getName() + fam.patient_name);
        setTextFieldDE(jTextFieldMotherLname,fam.patient_last_name);
        theFamily.mother_firstname = fam.patient_name;
        theFamily.mother_lastname = fam.patient_last_name;
        theFamily.mother_pid = fam.pid;
    }//GEN-LAST:event_jButtonMotherActionPerformed

    private void jButtonCoupleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCoupleActionPerformed
        String family_id = PanelLookup.showDialog(theUS.getJFrame(),theMC
                ,this.jTextFieldCoupleFname.getText()+" "+this.jTextFieldCoupleLname.getText());
        if(family_id==null)
            return;
        theFamily.couple_fid = family_id;
        Family fam = this.thePatientControl.readFamilyByFamilyIdRet(family_id);
        Prefix2 p_couple = this.theHM.theHC.theLookupControl.readPrefixById(fam.f_prefix_id);
        setTextFieldDE(jTextFieldCoupleFname,p_couple.getName() + fam.patient_name);
        setTextFieldDE(jTextFieldCoupleLname,fam.patient_last_name);
        theFamily.couple_firstname = fam.patient_name;
        theFamily.couple_lastname = fam.patient_last_name;
        theFamily.couple_id = fam.pid;
    }//GEN-LAST:event_jButtonCoupleActionPerformed

    private void jButtonCancelCoupleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelCoupleActionPerformed
        theFamily.couple_fid = "";
        theFamily.couple_firstname = "";
        theFamily.couple_lastname = "";
        theFamily.couple_id = "";
        setTextFieldDE(jTextFieldCoupleFname,"");
        setTextFieldDE(jTextFieldCoupleLname,"");
}//GEN-LAST:event_jButtonCancelCoupleActionPerformed

    private void jButtonCancelMotherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelMotherActionPerformed
        theFamily.mother_fid = "";
        theFamily.mother_firstname = "";
        theFamily.mother_lastname = "";
        theFamily.mother_pid = "";
        setTextFieldDE(jTextFieldMotherFname,"");
        setTextFieldDE(jTextFieldMotherLname,"");
}//GEN-LAST:event_jButtonCancelMotherActionPerformed

    private void jButtonCancelFatherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelFatherActionPerformed
        theFamily.father_fid = "";
        theFamily.father_firstname = "";
        theFamily.father_lastname = "";
        theFamily.father_pid = "";
        setTextFieldDE(jTextFieldFatherFname,"");
        setTextFieldDE(jTextFieldFatherLname,"");
}//GEN-LAST:event_jButtonCancelFatherActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.hospital_os.utility.DateComboBox dateComboBoxBirthDay;
    private com.hospital_os.utility.DateComboBox dateComboBoxDisch;
    private com.hospital_os.utility.DateComboBox dateComboBoxMoveIn;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.pcu.utility.IntegerTextField integerTextFieldFamilyNumber;
    private javax.swing.JButton jButtonCancelCouple;
    private javax.swing.JButton jButtonCancelFather;
    private javax.swing.JButton jButtonCancelMother;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonCouple;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonFather;
    private javax.swing.JButton jButtonMother;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonReset1;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearchPatient;
    private javax.swing.JButton jButtonaddHome;
    private javax.swing.JCheckBox jCheckBox;
    private javax.swing.JCheckBox jCheckBoxTrueBirthday;
    private javax.swing.JComboBox jComboBoxA;
    private javax.swing.JComboBox jComboBoxBlood;
    private javax.swing.JComboBox jComboBoxC;
    private javax.swing.JComboBox jComboBoxDisch;
    private javax.swing.JComboBox jComboBoxEducate;
    private javax.swing.JComboBox jComboBoxGender;
    private javax.swing.JComboBox jComboBoxLabor;
    private javax.swing.JComboBox jComboBoxMarriage;
    private javax.swing.JComboBox jComboBoxNation;
    private com.hosv3.gui.component.HosComboBox jComboBoxOccup;
    private com.hosv3.gui.component.HosComboBox jComboBoxPrename;
    private javax.swing.JComboBox jComboBoxRace;
    private javax.swing.JComboBox jComboBoxReligion;
    private javax.swing.JComboBox jComboBoxT;
    private javax.swing.JComboBox jComboBoxTypeArea;
    private javax.swing.JComboBox jComboBoxVillage;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAge;
    private javax.swing.JLabel jLabelAmpur;
    private javax.swing.JLabel jLabelBlood;
    private javax.swing.JLabel jLabelChangwat;
    private javax.swing.JLabel jLabelCoupleName;
    private javax.swing.JLabel jLabelEducate;
    private javax.swing.JLabel jLabelFamilyNumber;
    private javax.swing.JLabel jLabelFatherName;
    private javax.swing.JLabel jLabelFname;
    private javax.swing.JLabel jLabelFstatus;
    private javax.swing.JLabel jLabelFstatus1;
    private javax.swing.JLabel jLabelFstatus2;
    private javax.swing.JLabel jLabelFstatus3;
    private javax.swing.JLabel jLabelGender;
    private javax.swing.JLabel jLabelHCIS;
    private javax.swing.JLabel jLabelHomeNumber;
    private javax.swing.JLabel jLabelLabor;
    private javax.swing.JLabel jLabelMarriage;
    private javax.swing.JLabel jLabelMoo;
    private javax.swing.JLabel jLabelMotherName;
    private javax.swing.JLabel jLabelNation;
    private javax.swing.JLabel jLabelOccup;
    private javax.swing.JLabel jLabelPid;
    private javax.swing.JLabel jLabelRace;
    private javax.swing.JLabel jLabelReligion;
    private javax.swing.JLabel jLabelRoad;
    private javax.swing.JLabel jLabelTambon;
    private javax.swing.JLabel jLabelVillageN;
    private javax.swing.JLabel jLabelWorkOffice;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRadioButtonOwner;
    private javax.swing.JRadioButton jRadioButtonTenant;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private com.hosv3.gui.component.HJTableSort jTableHome;
    private com.hosv3.gui.component.HJTableSort jTablePerson;
    private com.hospital_os.utility.IntegerTextField jTextFieldAge;
    private javax.swing.JTextField jTextFieldCoupleFname;
    private javax.swing.JTextField jTextFieldCoupleLname;
    private javax.swing.JTextField jTextFieldFatherFname;
    private javax.swing.JTextField jTextFieldFatherLname;
    private javax.swing.JTextField jTextFieldFname;
    private javax.swing.JTextField jTextFieldLname;
    private javax.swing.JTextField jTextFieldMotherFname;
    private javax.swing.JTextField jTextFieldMotherLname;
    private javax.swing.JTextField jTextFieldSearchHome;
    private javax.swing.JTextField jTextFieldWorkOffice;
    private com.hosv3.gui.component.PIDPanel pidPanel;
    // End of variables declaration//GEN-END:variables
    public boolean showPanel(JFrame jf,int sh,Home home,Family family,UpdateStatus us)
    {
        theUS = us;
        this.setVisibleGui(false);
        if(family!=null){
            Family father = this.thePatientControl.readFamilyByFamilyIdRet(family.father_fid);
            Family mother = this.thePatientControl.readFamilyByFamilyIdRet(family.mother_fid);
            Family couple = this.thePatientControl.readFamilyByFamilyIdRet(family.couple_fid);
            setFamily(family,home,father,mother,couple);
        }
        else
           setFamily(null,home,null,null,null);

        if(frm1 == null)
        {
            frm1 = new JDialog(jf,"ข้อมูลบุคคล",true);
            frm1.setLayout(new java.awt.GridBagLayout());
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.weightx = 1;
            gridBagConstraints.weighty = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            frm1.getContentPane().add(this,gridBagConstraints);
            frm1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        }
        
//        if(is_panel_mode)
//        {
            frm1.setSize(800,520);
//        }
//        else
//        {
//            frm1.setSize(400,450);
//        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frm1.setLocation((screenSize.width-frm1.getSize().width)/2, (screenSize.height-frm1.getSize().height)/2);  
        frm1.setVisible(true);
        show = sh;
        return true;
    }

    private Vector listDischarge() {
        if(vret!=null)
            return vret;
        vret = new Vector();
        ComboFix com = null;
        com = new ComboFix("9","ไม่จำหน่าย");
        vret.add(com);
        com = new ComboFix("1","ตาย");
        vret.add(com);
        com = new ComboFix("2","ย้าย");
        vret.add(com);
         com = new ComboFix("3","สาบสูญ");
        vret.add(com);
        return vret;
    }

    private void setTextFieldDE(JTextField jTextField, String string) {
        jTextField.setText(string);
        jTextField.setEditable(string.equals(""));
    }
    
    private void setVisibleGui(boolean flang)
    {
        jButtonSearchPatient.setVisible(flang);
        jButtonDelete.setVisible(flang);
        jPanel34.setVisible(flang);
        //jButtonPrintOPDCard.setVisible(flang);        
        //jButtonReset1.setVisible(flang);
        jSplitPane1.setVisible(flang);
//        jPanel34.setVisible(flang);
        jButtonaddHome.setVisible(flang);        
    }
    
    private void setVisiblePanel(boolean flang)
    {        
       //jLabelFamilyNumber.setVisible(flang);
       //integerTextFieldFamilyNumber.setVisible(flang);
       //jButtonPrintOPDCard.setVisible(flang);
       jButtonClose.setVisible(flang);
       jComboBoxT.setVisible(flang);
       jComboBoxA.setVisible(flang);
       jComboBoxC.setVisible(flang);
    }
    /**
     * @deprecated henbe unused
     * @param thePatient
     * @return
     */
    private Patient getPatient(Patient thePatient)
    {
        if(thePatient==null)
        {
            thePatient = new Patient();
        }    
        thePatient.patient_birthday = dateComboBoxBirthDay.getText();
        thePatient.bgroup = GutilPCU.getGuiData(jComboBoxBlood);
        thePatient.education = GutilPCU.getGuiData(jComboBoxEducate);
        //thePatient.fstatus = GutilPCU.getGuiData(jComboBoxFstatus);
        thePatient.sex = GutilPCU.getGuiData(jComboBoxGender);
        thePatient.mstatus = GutilPCU.getGuiData(jComboBoxMarriage);
        thePatient.nation = GutilPCU.getGuiData(jComboBoxNation);
        thePatient.occupa = jComboBoxOccup.getText();
        //กรณีไม่พบคำนำหน้าชื่อที่ผู้ใช้กรอก//////////////////////////
        thePatient.prefix_id = jComboBoxPrename.getText();        
        if(thePatient.prefix_id.equals("")){
            thePatient.prefix_id = "add:" + jComboBoxPrename.getDetail();
        }        
        thePatient.race = GutilPCU.getGuiData(jComboBoxRace);
        thePatient.religion = GutilPCU.getGuiData(jComboBoxReligion);
        thePatient.typearea = GutilPCU.getGuiData(jComboBoxTypeArea);
        thePatient.labor = GutilPCU.getGuiData(jComboBoxLabor);
        thePatient.couple_fname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldCoupleFname));
        thePatient.couple_lname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldCoupleLname));
        thePatient.father_fname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldFatherFname));
        thePatient.father_lname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldFatherLname));
        thePatient.fname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldFname));
        thePatient.lname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldLname));
        thePatient.mother_fname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldMotherFname));
        thePatient.mother_lname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldMotherLname));
        thePatient.pid = pidPanel.getText();        
        thePatient.phone = theHome.owner_number;        
        thePatient.has_health_home = "1";
        thePatient.family_id = theFamily.getObjectId();
        if(jCheckBoxTrueBirthday.isSelected())
            thePatient.true_birthday = GutilPCU.getGuiData("1");
        else
            thePatient.true_birthday = GutilPCU.getGuiData("0");
        
        if(thePatient.getObjectId() == null)
        {    
            thePatient.record_date_time = thePCUObject.getCurrentDateTime();
             thePatient.staff_record = thePCUObject.getEmployee().getObjectId();
        }
        else
        {    
             thePatient.update_date_time = thePCUObject.getCurrentDateTime();
             thePatient.staff_modify= thePCUObject.getEmployee().getObjectId();
        }        
        return thePatient;
    }
    /**
     * @deprecated henbe unused
     * @return
     */
    private Patient getPatient()
    {
        if(thePatient != null)
        {
            if(jCheckBoxTrueBirthday.isSelected() || !jTextFieldAge.getText().equals(""))
            {
                thePatient.true_birthday = jCheckBoxTrueBirthday.isSelected()?"1":"0";
                thePatient.patient_birthday = dateComboBoxBirthDay.getText();
                thePatient.bgroup = GutilPCU.getGuiData(jComboBoxBlood);
                thePatient.education = GutilPCU.getGuiData(jComboBoxEducate);
                thePatient.sex = GutilPCU.getGuiData(jComboBoxGender);
                thePatient.mstatus = GutilPCU.getGuiData(jComboBoxMarriage);
                thePatient.nation = GutilPCU.getGuiData(jComboBoxNation);
                thePatient.occupa = jComboBoxOccup.getText();
                thePatient.prefix_id = jComboBoxPrename.getText();        
                thePatient.race = GutilPCU.getGuiData(jComboBoxRace);
                thePatient.religion = GutilPCU.getGuiData(jComboBoxReligion);
                thePatient.typearea = GutilPCU.getGuiData(jComboBoxTypeArea);
                thePatient.labor = GutilPCU.getGuiData(jComboBoxLabor);
                thePatient.couple_fname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldCoupleFname));
                thePatient.couple_lname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldCoupleLname));
                thePatient.father_fname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldFatherFname));
                thePatient.father_lname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldFatherLname));
                thePatient.fname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldFname));
                thePatient.lname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldLname));
                thePatient.pid = pidPanel.getText();        
                thePatient.mother_fname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldMotherFname));
                thePatient.mother_lname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldMotherLname));
                
                 if(theHome != null ){
                    if(!theHome.home_house.equals("0")){
//                        System.out.println("thePatient.house = home.home_house;"+theHome.home_house);
                        thePatient.house = theHome.home_house;
                        thePatient.tambon = theHome.home_tambol;
                        thePatient.ampur = theHome.home_amphur;
                        thePatient.changwat = theHome.home_changwat;
                        thePatient.road = theHome.home_road;
                        thePatient.village = theHome.home_moo;
                    }
                }
            } 
        }
        return thePatient;
    }
    private Family getFamily()
    {
        if(theFamily==null)
        {            
            theFamily = new Family();
        }
        if(jCheckBoxTrueBirthday.isSelected() || !jTextFieldAge.getText().equals(""))
        {
            theFamily.patient_birthday = dateComboBoxBirthDay.getText();
        } 
        theFamily.blood_group_id = GutilPCU.getGuiData(jComboBoxBlood);
        theFamily.education_type_id = GutilPCU.getGuiData(jComboBoxEducate);
        theFamily.f_sex_id = GutilPCU.getGuiData(jComboBoxGender);
        theFamily.marriage_status_id = GutilPCU.getGuiData(jComboBoxMarriage);
        theFamily.nation_id = GutilPCU.getGuiData(jComboBoxNation);
        theFamily.occupation_id = jComboBoxOccup.getText();
        theFamily.f_prefix_id = jComboBoxPrename.getText();        
        if(theFamily.f_prefix_id.equals("")){
            theFamily.f_prefix_id = "add:" + jComboBoxPrename.getDetail();
        }  
        theFamily.race_id = GutilPCU.getGuiData(jComboBoxRace);
        theFamily.religion_id = GutilPCU.getGuiData(jComboBoxReligion);
        theFamily.area_status_id = GutilPCU.getGuiData(jComboBoxTypeArea);
        theFamily.couple_firstname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldCoupleFname));
        theFamily.couple_lastname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldCoupleLname));
        theFamily.father_firstname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldFatherFname));
        theFamily.father_lastname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldFatherLname));
        theFamily.patient_name = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldFname));
        theFamily.patient_last_name = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldLname));
        theFamily.pid = pidPanel.getText();        
        theFamily.mother_firstname = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldMotherFname));
        theFamily.mother_lastname =  GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldMotherLname));
        theFamily.work_office = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(jTextFieldWorkOffice));
        theFamily.family_number = GutilPCU.CheckReservedWords(GutilPCU.getGuiData(integerTextFieldFamilyNumber));
        theFamily.labor = GutilPCU.getGuiData(jComboBoxLabor);
        if(theHome != null && theHome.getObjectId()!=null)
            theFamily.home_id = theHome.getObjectId();
        
        if(jCheckBoxTrueBirthday.isSelected())
            theFamily.patient_birthday_true = GutilPCU.getGuiData("1");
        else
            theFamily.patient_birthday_true = GutilPCU.getGuiData("0");
        
        if(theFamily.getObjectId() == null)
        {    
            theFamily.record_date_time = thePCUObject.getCurrentDateTime();
            theFamily.staff_record = thePCUObject.getEmployee().getObjectId();          
        }
        else
        {    
            theFamily.modify_date_time = thePCUObject.getCurrentDateTime();
            theFamily.staff_modify= thePCUObject.getEmployee().getObjectId();
        }
        theFamily.move_in_date_time = GutilPCU.getGuiData(this.dateComboBoxMoveIn);
        theFamily.discharge_date_time = GutilPCU.getGuiData(this.dateComboBoxDisch);
        theFamily.discharge_status_id = GutilPCU.getGuiData(this.jComboBoxDisch);

        if(this.jRadioButtonOwner.isSelected())
            theFamily.status_id = "1";
        else
            theFamily.status_id = "2";
//        System.out.println("_____________family_home3 " + theFamily.home_id);
//        System.out.println("_____________family_home " + theFamily.getObjectId());
        if(theHome!=null)
            theFamily.home_id = theHome.getObjectId();
        return theFamily;
    }
        
    public void setDefaultComboBox()
    {   
        try{
            GutilPCU.setGuiData(jComboBoxBlood,"1");
            GutilPCU.setGuiData(jComboBoxGender,"1");
            GutilPCU.setGuiData(jComboBoxMarriage,"1");
            GutilPCU.setGuiData(jComboBoxNation,"99");
            jComboBoxOccup.setText("000");
            jComboBoxPrename.setText("000");
            GutilPCU.setGuiData(jComboBoxRace,"99");
            GutilPCU.setGuiData(jComboBoxReligion,"1");
            GutilPCU.setGuiData(jComboBoxEducate,"11");
            setTextFieldDE(jTextFieldCoupleFname,"");
            setTextFieldDE(jTextFieldCoupleLname,"");
            setTextFieldDE(jTextFieldFatherFname,"");
            setTextFieldDE(jTextFieldFatherLname,"");
            GutilPCU.setGuiData(jTextFieldFname,"");
            GutilPCU.setGuiData(jTextFieldLname,"");
            setTextFieldDE(jTextFieldMotherFname,"");
            setTextFieldDE(jTextFieldMotherLname,"");
            pidPanel.setText("");
            GutilPCU.setGuiData(jTextFieldAge,"");
            GutilPCU.setGuiData(jTextFieldWorkOffice,"");
            GutilPCU.setGuiData(integerTextFieldFamilyNumber,"");
            jRadioButtonTenant.setSelected(true);
            GutilPCU.setGuiData(jComboBoxTypeArea,"1");
            GutilPCU.setGuiData(this.jComboBoxLabor,"1");
            setBirthDateTrue("0");
            dateComboBoxBirthDay.setText("");            
            GutilPCU.setGuiData(jLabelHomeNumber,"");
            GutilPCU.setGuiData(jLabelMoo,"");
            GutilPCU.setGuiData(jLabelRoad,"");
            GutilPCU.setGuiData(jLabelTambon,"");
            GutilPCU.setGuiData(jLabelAmpur,"");
            GutilPCU.setGuiData(jLabelChangwat,"");
            GutilPCU.setGuiData(jLabelVillageN,"");
            // somprasong 251209
            GutilPCU.setGuiData(jComboBoxDisch, "9");
        }
        catch(Exception  e)
        {
            e.printStackTrace();
        }
    }
    
    private void setBirthDateTrue(String str){
        boolean bdate_true = GutilPCU.isSelected(str);
        jCheckBoxTrueBirthday.setSelected(bdate_true);
        dateComboBoxBirthDay.setEnabled(bdate_true);
        jTextFieldAge.setEnabled(!bdate_true);
    }
    
    /**
     * ค้นหาบ้าน
     * @param  -    
     * @return void
     * @authur modify by kingland
     * @date 4/8/2549
     */
    private void searchHome()
    {           
//        System.out.println("__PanelPerson___________________________private void searchHome()");
        String search = "";//เลขที่บ้าน
        String search2 = "";//รหัสหมู่บ้าน
        search = jTextFieldSearchHome.getText();  //รับค่าจาก TextFiledsearch
        search2 = GutilPCU.getGuiData(jComboBoxVillage); //รับค่าจาก ComboBoxVillage
        jComboBoxVillage.setEnabled(jCheckBox.isSelected());
        if(jCheckBox.isSelected()) //ถ้ามีการเลือกแสดงบ้านทั้งหมดทุกหมู่บ้าน
        {
            if(!search2.equals(""))
            {
                if(search.indexOf("#")==0)
                    vHome =  theHomeControl.listHomeByHomeNumber(search.replaceAll("#", ""),search2, true);
                else
                    vHome =  theHomeControl.listHomeByHomeNumber(search.replaceAll("#", ""),search2, false);
            }
        }
        else
        {
            if(search.indexOf("#")==0)
                vHome =  theHomeControl.listHomeAllVillage(search.replaceAll("#", ""), true);
            else
                vHome =  theHomeControl.listHomeAllVillage(search.replaceAll("#", ""), false);
        }
        setHomeV(vHome);
        setFamilyV(null);
    }
    public void setHomeV(Vector v)
    {
        vHome = v;
        /***SetTable***/
        String[] col = {GutilPCU.getTextBundle("HomeNumber"),
                        //GutilPCU.getTextBundle("หมู่ที่:"),
                        GutilPCU.getTextBundle("ถนน:"),
                    GutilPCU.getTextBundle("VillageName")};
                    
        TaBleModel tm ;
        if(vHome != null)
        {   
            tm = new TaBleModel(col,vHome.size());
            for(int i=0;i<vHome.size(); i++)
            {                  
                Home homeTemp = (Home)vHome.get(i);                
                tm.setValueAt(homeTemp.home_house,i,0);
                //tm.setValueAt(homeTemp.home_moo,i,1);
                tm.setValueAt(homeTemp.home_road,i,1);
                tm.setValueAt(ComboboxModel.getDescriptionComboBox(jComboBoxVillage,homeTemp.village_id),i,2);
            }
        }
        else
        {   
            tm= new TaBleModel(col,0);
        } 
        jTableHome.setModel(tm);          
        /***SetTableDefault***/        
        jTableHome.getColumnModel().getColumn(0).setPreferredWidth(40);     /*บ้านเลขที่*/      
         jTableHome.getColumnModel().getColumn(0).setCellRenderer(ColumnTableRenderer.getRendererRight());  
        jTableHome.getColumnModel().getColumn(1).setPreferredWidth(100);     /*ถนน*/
        jTableHome.getColumnModel().getColumn(2).setPreferredWidth(100);     /*ชื่อหมู่บ้าน*/        
    }
    
    
    private void setFamily(Family family,Home home,Family father,Family mother,Family couple)
    {
        System.out.println("private void setFamily(Family family)");
        theFamily = family;
        theHome = home;
        // SOmprasong add 281209 clear all before set object
        jLabelHCIS.setText("HCIS:");
        if(theFamily==null) {
            setDefaultComboBox();
             return;
         }
         jComboBoxPrename.setText(theFamily.f_prefix_id);
         GutilPCU.setGuiData(jTextFieldFname,theFamily.patient_name);
         GutilPCU.setGuiData(jTextFieldLname,theFamily.patient_last_name);
         GutilPCU.setGuiData(jComboBoxGender,theFamily.f_sex_id);
         age = DateUtil.calculateAge(theFamily.patient_birthday,this.thePCUObject.getCurrentDateTime());
         if(age.equals("0")){
             if(thePatient!=null)
                 age = DateUtil.calculateAge(thePatient.patient_birthday,thePCUObject.getCurrentDateTime());
         }
         GutilPCU.setGuiData(jTextFieldAge,age);
         GutilPCU.setGuiData(jComboBoxMarriage,theFamily.marriage_status_id);
         GutilPCU.setGuiData(jComboBoxBlood,theFamily.blood_group_id);
         //GutilPCU.setGuiData(jTextFieldOcc,theFamily.occupation_id);
         jComboBoxOccup.setText(theFamily.occupation_id);
         GutilPCU.setGuiData(jComboBoxRace,theFamily.race_id);
         GutilPCU.setGuiData(jComboBoxNation,theFamily.nation_id);
         GutilPCU.setGuiData(jComboBoxReligion,theFamily.religion_id);
         GutilPCU.setGuiData(jComboBoxEducate,theFamily.education_type_id);
         if(father!=null){
             Prefix2 p_father = this.theHM.theHC.theLookupControl.readPrefixById(father.f_prefix_id);
             setTextFieldDE(jTextFieldFatherFname,p_father.getName() + father.patient_name);
             setTextFieldDE(jTextFieldFatherLname,father.patient_last_name);
         }
         else{
             jTextFieldFatherFname.setEditable(true);
             jTextFieldFatherLname.setEditable(true);
             GutilPCU.setGuiData(jTextFieldFatherFname,theFamily.father_firstname);
             GutilPCU.setGuiData(jTextFieldFatherLname,theFamily.father_lastname);
         }
         if(mother!=null){
             Prefix2 p_mother = this.theHM.theHC.theLookupControl.readPrefixById(mother.f_prefix_id);
             setTextFieldDE(jTextFieldMotherFname,p_mother.getName() + mother.patient_name);
             setTextFieldDE(jTextFieldMotherLname,mother.patient_last_name);
         }
         else{
             jTextFieldMotherFname.setEditable(true);
             jTextFieldMotherLname.setEditable(true);
             GutilPCU.setGuiData(jTextFieldMotherFname,theFamily.mother_firstname);
             GutilPCU.setGuiData(jTextFieldMotherLname,theFamily.mother_lastname);
         }
         if(couple!=null){
             Prefix2 p_couple = this.theHM.theHC.theLookupControl.readPrefixById(couple.f_prefix_id);
             setTextFieldDE(jTextFieldCoupleFname,p_couple.getName() + couple.patient_name);
             setTextFieldDE(jTextFieldCoupleLname,couple.patient_last_name);
         }
         else{
             jTextFieldCoupleFname.setEditable(true);
             jTextFieldCoupleLname.setEditable(true);
             GutilPCU.setGuiData(jTextFieldCoupleFname,theFamily.couple_firstname);
             GutilPCU.setGuiData(jTextFieldCoupleLname,theFamily.couple_lastname);
         }
         GutilPCU.setGuiData(jComboBoxTypeArea,theFamily.area_status_id);
         GutilPCU.setGuiData(jComboBoxLabor,theFamily.labor);
         pidPanel.setText(theFamily.pid);
         if(("1").equalsIgnoreCase(theFamily.status_id))
            jRadioButtonOwner.setSelected(true);
         else
            jRadioButtonTenant.setSelected(true);
         
         setBirthDateTrue(theFamily.patient_birthday_true);
         GutilPCU.setGuiData(jTextFieldWorkOffice,theFamily.work_office);
         dateComboBoxBirthDay.setText(com.hosv3.utility.DateUtil.convertFieldDate(theFamily.patient_birthday));
         GutilPCU.setGuiData(integerTextFieldFamilyNumber,theFamily.family_number);
         GutilPCU.setGuiData(this.dateComboBoxMoveIn, theFamily.move_in_date_time);
         GutilPCU.setGuiData(this.dateComboBoxDisch, theFamily.discharge_date_time);
         GutilPCU.setGuiData(this.jComboBoxDisch, theFamily.discharge_status_id);
         
        jLabelHCIS.setText("HCIS:" + theFamily.hn_hcis);
        setHomeV(home);
    }
    //แสดงในส่วนของที่อยู่ผู้ป่วยให้ถูกต้อง หากว่าอยู่นอกเขต
    private void setPatient(Patient pat,Village village,Home home)
    {      
//        System.out.println("private void setPatient(Patient pat,Village village,Home home)");
        thePatient = pat;
        if(thePatient==null)
            return;
        if(village.village_moo.equals("0") 
        || village.village_moo.equals("00")
        || home.home_house.equals("0"))
        {
            jLabelTambon.setText(theLookupControl.readAddressString(thePatient.tambon));
            jLabelAmpur.setText(theLookupControl.readAddressString(thePatient.ampur));
            jLabelChangwat.setText(theLookupControl.readAddressString(thePatient.changwat));
            jLabelHomeNumber.setText(thePatient.house);
            jLabelMoo.setText(thePatient.village);
            jLabelRoad.setText(thePatient.road);
        }
    }
    /**
     * @deprecated henbe unused
     * @return
     */
    public Patient initPatient()
    {
        Patient thePatient = new Patient();
        //theFamily = new Family();
        vPerson = null;
        jTableHome.clearSelection();
        jTablePerson.clearSelection();
        thePatient.cid_couple = "";
        thePatient.cid_f = "";
        thePatient.cif_m = "";
        thePatient.patient_birthday = "";
        thePatient.ampur = "";
        thePatient.changwat = "";
        thePatient.tambon =  "";
        thePatient.bgroup = "1";
        thePatient.education = "09";
        thePatient.fstatus =  "2";
        thePatient.sex =  "1";
        thePatient.labor =  "1";
        thePatient.mstatus = "1";
        thePatient.nation =  "99";
        thePatient.occupa =  "504";
        thePatient.prefix_id =  "000";
        thePatient.race =  "99";
        thePatient.religion =  "1";
        thePatient.typearea =  "1";
        thePatient.couple_fname =  "";
        thePatient.couple_lname =  "";
        thePatient.father_fname =  "";
        thePatient.father_lname =  "";
        thePatient.fname =  "";
        thePatient.house =  "";
        thePatient.lname =  "";
        thePatient.mother_fname =  "";
        thePatient.mother_lname =  "";
        thePatient.private_doc =  "";
        thePatient.p_type =  "";
        thePatient.road =  "";
        thePatient.village =  "";
        thePatient.pid =  "";
        thePatient.phone =  "";
        thePatient.relation =  "00";
        thePatient.sex_contact =  "1";
        thePatient.house_contact =  "";
        thePatient.village_contact =  "";
        thePatient.road_contact =  "";
        thePatient.phone_contact =  "";
        thePatient.ampur_contact = thePCUObject.getSite().amphor;
        thePatient.changwat_contact = thePCUObject.getSite().changwat;
        thePatient.tambon_contact =  thePCUObject.getSite().tambon;
        thePatient.xn =  "";
        thePatient.pid =  "";
        thePatient.contact_fname =  "";
        thePatient.contact_lname =  "";
        thePatient.true_birthday = "0";
        thePatient.phone = "";
        thePatient.has_health_home = "0";
        thePatient.record_date_time =  "";
        thePatient.staff_record = thePCUObject.getEmployee().getObjectId();
        jLabelVillageN.setText("");
        return thePatient;
    }    
    
     
    
    private void setFamilyV(Vector v)
    {   
        vPerson = v;
        String[] col = { //GutilPCU.getTextBundle("HN"),
                         GutilPCU.getTextBundle("Name")+"-"+GutilPCU.getTextBundle("SurName"),
                         GutilPCU.getTextBundle("Age"),
                         GutilPCU.getTextBundle("Sex"),"ตำแหน่ง"};
                          
         Family familyTemp = new Family();          
         TaBleModel tm ;
         String status = "";          
         if(vPerson != null)
         {
             tm = new TaBleModel(col,(vPerson.size()<100)?vPerson.size():100);
            String currentDateTime = thePCUObject.getCurrentDateTime();
            //ดักไว้ที่จำนวนไม่เกิน 100 คนเพราะมันจะช้ามาก
            for(int i=0;i<vPerson.size() && i<100; i++)
            {   familyTemp = (Family)vPerson.get(i);
                if(familyTemp.status_id.equals("1"))
                    status = GutilPCU.getTextBundle("OwnerHome");
                else 
                    status = GutilPCU.getTextBundle("Away");
                String family_age = DateUtil.calculateAge(familyTemp.patient_birthday,currentDateTime);               
                //tm.setValueAt(familyTemp.hn,i,0);  
                tm.setValueAt(theHM.theHC
                        .theLookupControl.readPrefixString(familyTemp.f_prefix_id)
                        +" "+familyTemp.patient_name+"   "+familyTemp.patient_last_name
                        ,i,0);
                tm.setValueAt(family_age+(" ")+GutilPCU.getTextBundle("Year"),i,1);
                tm.setValueAt(familyTemp.f_sex_id,i,2);
                String homestatus = "ผู้อาศัย";
                if(familyTemp.status_id.equals("1"))
                    homestatus = "เจ้าบ้าน";
                tm.setValueAt(homestatus,i,3);
            }
         }
         else
         {  tm= new TaBleModel(col,0);
         } 
         familyTemp = null;
         jTablePerson.setModel(tm);
         //jTablePerson.getColumnModel().getColumn(0).setPreferredWidth(30);
         jTablePerson.getColumnModel().getColumn(0).setPreferredWidth(200);         
         jTablePerson.getColumnModel().getColumn(1).setPreferredWidth(30); 
         jTablePerson.getColumnModel().getColumn(1).setCellRenderer(ColumnTableRenderer.getRendererCenter());
         jTablePerson.getColumnModel().getColumn(2).setPreferredWidth(30);
         jTablePerson.getColumnModel().getColumn(2).setCellRenderer(theCelRendererSexType);         
         jTablePerson.getColumnModel().getColumn(3).setPreferredWidth(50);
    }    
    
    /**
     *@deprecated henbe unused
     **/
    private void selectPerson()
    {   
//        System.out.println("_________________selectPerson____________________");
        int rowPerson = jTablePerson.getSelectedRow(); 
        if(rowPerson != -1)
        {
            Family theFamily = (Family)vPerson.get(rowPerson);             
            thePatientControl.readFamilyByFamilyId(theFamily.getObjectId());
        }
    }
    
    public void notifylistHome(com.pcu.object.Home home) {
        searchHome();
    }

    public void notifylistPatientbyFamily(Family family,int i) 
    {        
    }

    public void notifylistPatientbyFamilyInSearch(Family family) 
    {
    }

    public void setLanguage(){
        GutilPCU.setGuiLang(jLabelFname);
        GutilPCU.setGuiLang(jPanel10);
        GutilPCU.setGuiLang(jLabelPid);
        GutilPCU.setGuiLang(jLabelGender);
        GutilPCU.setGuiLang(jCheckBoxTrueBirthday);
        GutilPCU.setGuiLang(jLabelAge);
        GutilPCU.setGuiLang(jLabel3);
        GutilPCU.setGuiLang(jLabelMarriage);
        GutilPCU.setGuiLang(jLabelBlood);
        GutilPCU.setGuiLang(jLabelOccup);
        GutilPCU.setGuiLang(jLabelRace);
        GutilPCU.setGuiLang(jLabelNation);
        GutilPCU.setGuiLang(jLabelReligion);
        GutilPCU.setGuiLang(jLabelEducate);
        GutilPCU.setGuiLang(jLabelFatherName);
        GutilPCU.setGuiLang(jLabelMotherName);
        GutilPCU.setGuiLang(jLabelCoupleName);
        GutilPCU.setGuiLang(jLabelFstatus);
        GutilPCU.setGuiLang(jLabelFamilyNumber);
        GutilPCU.setGuiLang(jRadioButtonOwner);
        GutilPCU.setGuiLang(jRadioButtonTenant);
        GutilPCU.setGuiLang(jLabel4);
        GutilPCU.setGuiLang(jLabel6);
        GutilPCU.setGuiLang(jLabel9);
        GutilPCU.setGuiLang(jLabel10);
        GutilPCU.setGuiLang(jLabel14);
        GutilPCU.setGuiLang(jLabel15);
        GutilPCU.setGuiLang(jLabel16);
        GutilPCU.setGuiLang(jCheckBox);
        GutilPCU.setGuiLang(jButtonSave);
        GutilPCU.setGuiLang(jButtonaddHome);
        GutilPCU.setGuiLang(jButtonClose);
        GutilPCU.setGuiLang(jButtonSearchPatient);
       
    }
    public void notifyChangeHome(String str, int status) {
        str = Constant.getTextBundle(str);
        int row = jTableHome.getSelectedRow();
        if(row!=-1)
            setHome((Home)vHome.get(row));
        theUS.setStatus(str, status);
    }

    public void notifySaveStatus(Family family, int status) {
    }
    
    public static void main(String[] argc){
        System.out.println("time is " + String.valueOf(System.currentTimeMillis()));
        System.out.println("time is " + Long.toString(System.currentTimeMillis()));
        System.out.println("time is " + System.currentTimeMillis());
        System.out.println("time is " + System.currentTimeMillis());
        System.out.println("time is " + System.currentTimeMillis());
    }

    public boolean showPanel(JFrame frm1, int i, Home theHome, Family theFamily) {
        return showPanel(frm1,i,theHome,theFamily,theUS);
    }

    public void setUpdateStatus(UpdateStatus us) {
        theUS = us;
    }
}
