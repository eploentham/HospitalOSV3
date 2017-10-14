/*
 * PanelPerson.java
 *
 * Created on 8 กันยายน 2548, 12:11 น.
 */

package com.pcu.gui.panel.transaction;

import com.hosv3.control.HosControl;
import com.hosv3.control.PatientControl;
import com.pcu.control.AllComboBoxControl;
import com.pcu.control.HomeControl;
import com.pcu.control.HosManage;
import com.pcu.control.HospitalosControlInf;
import com.pcu.control.PCUObject;
import com.pcu.gui.dialog.HosDialog;
import com.pcu.subject.HomePatientSubject;
import java.awt.event.KeyEvent;
import javax.swing.*;/*ใช้ใน main*/
import java.awt.*;
import java.util.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;

import com.hosv3.control.LookupControl;
import com.hosv3.control.MapCon;
import com.hosv3.control.PrintControl;
import com.hosv3.control.ResultControl;
import com.hosv3.control.SmartCardControl;
import com.hosv3.control.VisitControl;
import com.hosv3.utility.connection.UpdateStatus;
import com.hosv3.object.*;
import com.hosv3.control.lookup.*;
import com.hosv3.gui.dialog.DialogPrintOPD;
import com.hosv3.gui.dialog.PanelRegisterNCD;

import com.hosv3.gui.panel.detail.PanelLookup;
import com.pcu.gui.panel.village.PanelHome2;
import com.pcu.utility.*;
import com.pcu.object.*;
import com.pcu.object.Home;
import com.pcu.utility.DateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
/**
 *
 * @author  jao
 */
public class PanelPerson2 extends javax.swing.JPanel {

   private Patient thePatient;
   private Family theFamily;
   private Home theHome;
   private AllComboBoxControl theAllComboBoxControl;
   private PatientControl thePatientControl;
   private HospitalosControlInf theHosInf;
   private LookupControl theLookupControl;   
   private HomeControl theHomeControl;
   private HosDialog theHosDialog; 
   private Vector vHome = new Vector();
   private Vector vPerson;
   private CelRendererSexType theCelRendererSexType = new CelRendererSexType();
   HosManage theHM;
   JDialog frm1;
   String changwat;
   PCUObject thePCUObject;
   UpdateStatus theUS;
    private boolean mouse_release;
    private Vector<ComboFix> vret;
    private MapCon theMC;
    private ResultControl theResultControl;
    private VisitControl theVisitControl;
    private HosObject theHO;
    private HosControl theHC;
    private SmartCardControl theSMCC;
    private Vector vNCD;
    private PrintControl thePrintControl;
    /** 
     */
    public PanelPerson2()
    {
        this.initComponents();
    }
    public void setControl(HosManage hm,HosDialog hd)
    {
        theHM = hm;
        theHosDialog = hd;
        theMC = new MapCon(MapCon.LOOK_PERSON,theUS,theHM.theHosControl.theConnectionInf);
        theResultControl = hm.theHC.theResultControl;
        theVisitControl = hm.theHC.theVisitControl;
        thePrintControl = hm.theHC.thePrintControl;
        theHO = hm.theHC.theHO;
        theHC = hm.theHC;
        theSMCC = hm.theHC.theSmartCardControl;
        setControl(hm.theHC.thePatientControl,hm.theHosControl.theUS
                ,hm.theHosControl.theLookupControl,hm.thePO,hm.theHosControl.theAllComboBoxControl
                ,hm.theHosControl.theHomeControl
                ,hm.theHosSubject.theHomePatientSubject,hm.theHosInf);
        initDatas();
        setDefaultComboBox();
        setVisiblePanel(false);
        setLanguage();
        setDefaultLocation();
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
        thePCUObject = po;
        theUS = us;
        pidPanel.setComponentColor(204, 255, 255);
    }
    
    public void initDatas() 
    {                 
        jComboBoxPrename.setControl(new PrefixLookup(theLookupControl),true);        
        jComboBoxOccup.setControl(null,new OccupationLookup(theLookupControl),new Occupation2());
        Vector v = theAllComboBoxControl.listAddressCGW();
        ComboboxModel.initComboBox(jComboBoxBlood,theAllComboBoxControl.listBlood());
        ComboboxModel.initComboBox(jComboBoxGender,theAllComboBoxControl.listSex());        
        ComboboxModel.initComboBox(jComboBoxMarriage,theAllComboBoxControl.listMarriage());
        ComboboxModel.initComboBox(jComboBoxReligion,theAllComboBoxControl.listReligion());
        ComboboxModel.initComboBox(jComboBoxEducate,theAllComboBoxControl.listEducate());
        ComboboxModel.initComboBox(jComboBoxTypeArea,theAllComboBoxControl.listTypeArea());
        ComboboxModel.initComboBox(jComboBoxVillage,theAllComboBoxControl.listVillage());
        ComboboxModel.initComboBox(jComboBoxDisch,listDischarge());
        ComboboxModel.initComboBox(jComboBoxC,v);
        R53PersonForeignLookup lup = new R53PersonForeignLookup();
        ComboboxModel.initComboBox(this.jComboBoxLabor,lup.listData("%"));
        jComboBoxOccup.setControl(null,new OccupationLookup(theHC.theLookupControl),new Occupation2());
        jComboBoxRace.setControl(null,new NationLookup(theHC.theLookupControl),new Nation());
        jComboBoxNation.setControl(null,new NationLookup(theHC.theLookupControl),new Nation());
        jComboBoxRelation.setControl(new RelationContractLookup(theHC.theLookupControl),true);
        ComboboxModel.initComboBox(jComboBoxGender,theHC.theLookupControl.listGender());
        ComboboxModel.initComboBox(jComboBoxContactSex,theHC.theLookupControl.listGender());
        panelAddressPatient.setControl(theHC,theUS);
        panelAddressContact.setControl(theHC,theUS);
    }
    public void setObject(PCUObject pcuobject)
    {   
        System.out.println("_henbe_______________________" + this.getClass().toString());
        thePCUObject = pcuobject;
        setFamily(pcuobject.getFamily(),pcuobject.getHome()
                ,pcuobject.getFamilyFather()
                ,pcuobject.getFamilyMother()
                ,pcuobject.getFamilyCouple());
        setPatient(pcuobject.getPatient()); 
    }
    private void setPatient(Patient pat)
    {
        this.jTextFieldHNVisit.setText("");
        thePatient = pat;
        if(thePatient==null){
            if(theHO.theFamily!=null)
                thePatient = theHO.initPatient(theHO.theFamily,theHO.theHome);
            else
                thePatient = theHO.initPatient();
            jLabelHCIS.setText("HCIS");
        }
        Gutil.setGuiData(jTextFieldPrivateDoctor,thePatient.private_doc);
        Gutil.setGuiData(jTextFieldPType,thePatient.p_type);
        Gutil.setGuiData(jTextFieldXN,thePatient.xn);
        Gutil.setGuiData(jTextFieldHN,thePatient.hn);
        Gutil.setGuiData(jComboBoxContactSex,thePatient.sex_contact);
        Gutil.setGuiData(jTextFieldContactFname,thePatient.contact_fname);
        Gutil.setGuiData(jTextFieldContactLname,thePatient.contact_lname);
        jComboBoxRelation.setText(thePatient.relation);
        if(true)
        {
            panelAddressPatient.setAddress(thePatient.house,thePatient.village
            ,thePatient.road,thePatient.phone,thePatient.mobile_phone
            ,thePatient.changwat, thePatient.ampur,thePatient.tambon
            ,thePatient.is_other_country,thePatient.other_address);
            panelAddressContact.setAddress(thePatient.house_contact,thePatient.village_contact
            ,thePatient.road_contact,thePatient.phone_contact,thePatient.contact_mobile_phone
            ,thePatient.changwat_contact, thePatient.ampur_contact,thePatient.tambon_contact);
        }
        else{
            panelAddressContact.reset();
            panelAddressPatient.reset();
        }
        if(theHO.theFamily!=null)
            jLabelHCIS.setText("HCIS:" + theHO.theFamily.hn_hcis);

        setNcdV(theHO.vNCD);
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

        if(!theHome.home_house.equals("0")){
            Vector v = theHomeControl.listFamilyByHomeId(theHome.getObjectId());
            this.setFamilyV(v);
        }
        else{
            setFamilyV(null);
        }
        //หาว่ามีบ้านนี้อยู่ใน list เดิมหรือเปล่าหากมีก็ให้แสดงโดยไม่ต้อง refresh
        int index = -1;
        for(int i=0;i<vHome.size();i++){
            Home hm = (Home)vHome.get(i);
            if(hm.getObjectId().equals(home.getObjectId())){
                index = i;
                break;
            }
        }
        if(index!=-1){
            this.jTableHome.setRowSelectionInterval(index,index);
        }
        else{
            Vector<Home> vAddress = new Vector<Home>();
            vAddress.add(home);
            setHomeV(vAddress);
            setFamilyV(null);
        }
    }

    public void setDefaultLocation()
    {
        panelAddressPatient.setAddress("", "", "", "", "", this.theHO.theSite.changwat, this.theHO.theSite.amphor, this.theHO.theSite.tambon);
        panelAddressContact.setAddress("", "", "", "", "", this.theHO.theSite.changwat, this.theHO.theSite.amphor, this.theHO.theSite.tambon);
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
        buttonGroupFm = new javax.swing.ButtonGroup();
        buttonGroupPt = new javax.swing.ButtonGroup();
        buttonGroupMed = new javax.swing.ButtonGroup();
        jPanel10 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jComboBoxPrename = new com.hosv3.gui.component.HosComboBox();
        jTextFieldFname = new javax.swing.JTextField();
        jTextFieldLname = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabelPid = new javax.swing.JLabel();
        pidPanel = new com.hosv3.gui.component.PIDPanel();
        jLabelHCIS = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButtonWS = new javax.swing.JButton();
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
        jComboBoxRace = new com.hosv3.gui.component.HosComboBox();
        jComboBoxNation = new com.hosv3.gui.component.HosComboBox();
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
        jLabelFstatus = new javax.swing.JLabel();
        jComboBoxLabor = new javax.swing.JComboBox();
        jLabelLabor = new javax.swing.JLabel();
        jTextFieldForeignNo = new javax.swing.JTextField();
        jLabelMarriage = new javax.swing.JLabel();
        jLabelOccup = new javax.swing.JLabel();
        jLabelFatherName = new javax.swing.JLabel();
        jLabelMotherName = new javax.swing.JLabel();
        jLabelCoupleName = new javax.swing.JLabel();
        jPanelCard = new javax.swing.JPanel();
        jPanelPatient = new javax.swing.JPanel();
        jPanelAction = new javax.swing.JPanel();
        jButtonActive = new javax.swing.JButton();
        jTextFieldHNVisit = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jButtonSmartcard = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButtonReset2 = new javax.swing.JButton();
        jButtonSearchPatient1 = new javax.swing.JButton();
        jButtonDelete1 = new javax.swing.JButton();
        jButtonPrintOPDCard = new javax.swing.JButton();
        jButtonSave1 = new javax.swing.JButton();
        jButtonFm1 = new javax.swing.JToggleButton();
        jButtonPt1 = new javax.swing.JToggleButton();
        jButtonMd1 = new javax.swing.JToggleButton();
        jCheckBoxCheck = new javax.swing.JCheckBox();
        jButtonIndex = new javax.swing.JButton();
        jPanelDetail = new javax.swing.JPanel();
        jPanelContact = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxContactSex = new javax.swing.JComboBox();
        jComboBoxRelation = new com.hosv3.gui.component.HosComboBox();
        jButton2 = new javax.swing.JButton();
        panelAddressContact = new com.hosv3.gui.component.PanelAddress();
        jLabelContact = new javax.swing.JLabel();
        jTextFieldContactFname = new javax.swing.JTextField();
        jTextFieldContactLname = new javax.swing.JTextField();
        panelAddressPatient = new com.hosv3.gui.component.PanelAddress();
        jPanelFamily = new javax.swing.JPanel();
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
        jPanel35 = new javax.swing.JPanel();
        jRadioButtonTenant = new javax.swing.JRadioButton();
        jRadioButtonOwner = new javax.swing.JRadioButton();
        jLabelFstatus3 = new javax.swing.JLabel();
        dateComboBoxMoveIn = new com.hospital_os.utility.DateComboBox();
        jLabelWorkOffice = new javax.swing.JLabel();
        jTextFieldWorkOffice = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jButtonSearchPatient = new javax.swing.JButton();
        jButtonReset1 = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonaddHome = new javax.swing.JButton();
        jButtonFm = new javax.swing.JToggleButton();
        jButtonPt = new javax.swing.JToggleButton();
        jButtonMd = new javax.swing.JToggleButton();
        jPanel8 = new javax.swing.JPanel();
        dateComboBoxDisch = new com.hospital_os.utility.DateComboBox();
        jLabelFstatus2 = new javax.swing.JLabel();
        jComboBoxDisch = new javax.swing.JComboBox();
        jLabelFamilyNumber = new javax.swing.JLabel();
        integerTextFieldFamilyNumber = new com.pcu.utility.IntegerTextField();
        jPanelMed = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new com.hosv3.gui.component.HJTableSort();
        jButtonAddVital = new javax.swing.JButton();
        jButtonDelVital = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        jLabelPType = new javax.swing.JLabel();
        jTextFieldPType = new javax.swing.JTextField();
        jLabelPrivateDoctor = new javax.swing.JLabel();
        jTextFieldPrivateDoctor = new javax.swing.JTextField();
        jPanel31 = new javax.swing.JPanel();
        jLabelHN = new javax.swing.JLabel();
        jTextFieldXN = new javax.swing.JTextField();
        jLabelXN = new javax.swing.JLabel();
        jTextFieldHN = new javax.swing.JTextField();
        jButtonFm2 = new javax.swing.JToggleButton();
        jButtonPt2 = new javax.swing.JToggleButton();
        jButtonMd2 = new javax.swing.JToggleButton();
        jButtonSave2 = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel10.setMinimumSize(new java.awt.Dimension(370, 365));
        jPanel10.setPreferredSize(new java.awt.Dimension(370, 365));
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
        jPanel1.add(jComboBoxPrename, gridBagConstraints);

        jTextFieldFname.setBackground(new java.awt.Color(204, 255, 255));
        jTextFieldFname.setFont(defaultFont1);
        jTextFieldFname.setPreferredSize(new java.awt.Dimension(6, 22));
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

        jTextFieldLname.setBackground(new java.awt.Color(204, 255, 255));
        jTextFieldLname.setFont(defaultFont1);
        jTextFieldLname.setMinimumSize(new java.awt.Dimension(6, 22));
        jTextFieldLname.setPreferredSize(new java.awt.Dimension(6, 22));
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
        jPanel10.add(jPanel1, gridBagConstraints);

        jPanel2.setMinimumSize(new java.awt.Dimension(200, 22));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 22));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabelPid.setFont(defaultFont1);
        jLabelPid.setText("CID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 1, 0);
        jPanel2.add(jLabelPid, gridBagConstraints);

        pidPanel.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 1, 0);
        jPanel2.add(pidPanel, gridBagConstraints);

        jLabelHCIS.setFont(defaultFont1);
        jLabelHCIS.setText("HCIS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel2.add(jLabelHCIS, gridBagConstraints);

        jButton1.setText("Copy");
        jButton1.setToolTipText("คัดลอกหมายเลขบัตรประชาชน");
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.setMaximumSize(new java.awt.Dimension(37, 19));
        jButton1.setMinimumSize(new java.awt.Dimension(37, 19));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel2.add(jButton1, gridBagConstraints);

        jButtonWS.setText("ws");
        jButtonWS.setToolTipText("ตรวจสอบสิทธิ์ผ่านทางเว็บเซอร์วิสโดยใช้เลขบัตรประจำตัวประชาชน 13 หลัก");
        jButtonWS.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonWS.setMaximumSize(new java.awt.Dimension(22, 19));
        jButtonWS.setMinimumSize(new java.awt.Dimension(22, 19));
        jButtonWS.setPreferredSize(new java.awt.Dimension(39, 25));
        jButtonWS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonWSActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonWS, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel10.add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabelGender.setFont(defaultFont1);
        jLabelGender.setText("Sex");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(jLabelGender, gridBagConstraints);

        jComboBoxGender.setBackground(new java.awt.Color(204, 255, 255));
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
        jTextFieldAge.setMinimumSize(new java.awt.Dimension(39, 22));
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel10.add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jComboBoxMarriage.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel4.add(jComboBoxOccup, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel10.add(jPanel4, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jComboBoxReligion.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel6.add(jComboBoxReligion, gridBagConstraints);

        jLabelEducate.setFont(defaultFont1);
        jLabelEducate.setText("การศึกษา");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel6.add(jLabelEducate, gridBagConstraints);

        jComboBoxEducate.setBackground(new java.awt.Color(204, 255, 255));
        jComboBoxEducate.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel6.add(jComboBoxEducate, gridBagConstraints);

        jLabelReligion.setFont(defaultFont1);
        jLabelReligion.setText("ศาสนา");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 0);
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

        jComboBoxRace.setDoubleBuffered(true);
        jComboBoxRace.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBoxRaceKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel6.add(jComboBoxRace, gridBagConstraints);

        jComboBoxNation.setDoubleBuffered(true);
        jComboBoxNation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBoxNationKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel6.add(jComboBoxNation, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel10.add(jPanel6, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jTextFieldFatherFname.setFont(defaultFont1);
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

        jButtonFather.setFont(new java.awt.Font("Tahoma", 0, 12));
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
        gridBagConstraints.insets = new java.awt.Insets(1, 2, 0, 0);
        jPanel7.add(jButtonFather, gridBagConstraints);

        jButtonMother.setFont(new java.awt.Font("Tahoma", 0, 12));
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
        gridBagConstraints.insets = new java.awt.Insets(1, 2, 0, 0);
        jPanel7.add(jButtonMother, gridBagConstraints);

        jButtonCouple.setFont(new java.awt.Font("Tahoma", 0, 12));
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
        gridBagConstraints.insets = new java.awt.Insets(1, 2, 0, 0);
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
        gridBagConstraints.insets = new java.awt.Insets(1, 2, 0, 0);
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
        gridBagConstraints.insets = new java.awt.Insets(1, 2, 0, 0);
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
        gridBagConstraints.insets = new java.awt.Insets(1, 2, 0, 0);
        jPanel7.add(jButtonCancelFather, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel10.add(jPanel7, gridBagConstraints);

        jPanel16.setLayout(new java.awt.GridBagLayout());

        jComboBoxTypeArea.setBackground(new java.awt.Color(204, 255, 255));
        jComboBoxTypeArea.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel16.add(jComboBoxTypeArea, gridBagConstraints);

        jLabelFstatus.setFont(defaultFont1);
        jLabelFstatus.setText("ใน/นอกเขต");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel16.add(jLabelFstatus, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 0, 0);
        jPanel16.add(jComboBoxLabor, gridBagConstraints);

        jLabelLabor.setText("ประเภทคนต่างด้าว/เลขประจำตัว");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel16.add(jLabelLabor, gridBagConstraints);

        jTextFieldForeignNo.setColumns(9);
        jTextFieldForeignNo.setFont(defaultFont1);
        jTextFieldForeignNo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldForeignNo.setMinimumSize(new java.awt.Dimension(105, 22));
        jTextFieldForeignNo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldForeignNoCaretUpdate(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 0, 0);
        jPanel16.add(jTextFieldForeignNo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel10.add(jPanel16, gridBagConstraints);

        jLabelMarriage.setFont(defaultFont1);
        jLabelMarriage.setText("สถานภาพ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel10.add(jLabelMarriage, gridBagConstraints);

        jLabelOccup.setFont(defaultFont1);
        jLabelOccup.setText("อาชีพ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel10.add(jLabelOccup, gridBagConstraints);

        jLabelFatherName.setFont(defaultFont1);
        jLabelFatherName.setText("FatherName");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        jPanel10.add(jLabelFatherName, gridBagConstraints);

        jLabelMotherName.setFont(defaultFont1);
        jLabelMotherName.setText("MotherName");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        jPanel10.add(jLabelMotherName, gridBagConstraints);

        jLabelCoupleName.setFont(defaultFont1);
        jLabelCoupleName.setText("ชื่อคู่สมรส");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        jPanel10.add(jLabelCoupleName, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        add(jPanel10, gridBagConstraints);

        jPanelCard.setLayout(new java.awt.CardLayout());

        jPanelPatient.setLayout(new java.awt.GridBagLayout());

        jPanelAction.setLayout(new java.awt.GridBagLayout());

        jButtonActive.setText("Active");
        jButtonActive.setToolTipText("นำกลับมาใช้ใหม่");
        jButtonActive.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActiveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelAction.add(jButtonActive, gridBagConstraints);

        jTextFieldHNVisit.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldHNVisit.setMinimumSize(new java.awt.Dimension(60, 21));
        jTextFieldHNVisit.setPreferredSize(new java.awt.Dimension(60, 21));
        jTextFieldHNVisit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldHNVisitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanelAction.add(jTextFieldHNVisit, gridBagConstraints);

        jLabel17.setText("HN Visit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanelAction.add(jLabel17, gridBagConstraints);

        jButtonSmartcard.setText("สมาร์ทการ์ด");
        jButtonSmartcard.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonSmartcard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSmartcardActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanelAction.add(jButtonSmartcard, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jButtonReset2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonReset2.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonReset2.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonReset2.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonReset2.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonReset2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReset2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel5.add(jButtonReset2, gridBagConstraints);

        jButtonSearchPatient1.setFont(defaultFont1);
        jButtonSearchPatient1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/find.gif"))); // NOI18N
        jButtonSearchPatient1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonSearchPatient1.setMaximumSize(new java.awt.Dimension(30, 26));
        jButtonSearchPatient1.setMinimumSize(new java.awt.Dimension(30, 26));
        jButtonSearchPatient1.setPreferredSize(new java.awt.Dimension(30, 26));
        jButtonSearchPatient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchPatient1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel5.add(jButtonSearchPatient1, gridBagConstraints);

        jButtonDelete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelete1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonDelete1.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonDelete1.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonDelete1.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelete1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jButtonDelete1, gridBagConstraints);

        jButtonPrintOPDCard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/expenses.gif"))); // NOI18N
        jButtonPrintOPDCard.setToolTipText("OPD Card");
        jButtonPrintOPDCard.setMaximumSize(new java.awt.Dimension(28, 28));
        jButtonPrintOPDCard.setMinimumSize(new java.awt.Dimension(28, 28));
        jButtonPrintOPDCard.setPreferredSize(new java.awt.Dimension(28, 28));
        jButtonPrintOPDCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintOPDCardActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel5.add(jButtonPrintOPDCard, gridBagConstraints);

        jButtonSave1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Save16.gif"))); // NOI18N
        jButtonSave1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonSave1.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonSave1.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonSave1.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSave1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel5.add(jButtonSave1, gridBagConstraints);

        buttonGroupPt.add(jButtonFm1);
        jButtonFm1.setText("FM");
        jButtonFm1.setToolTipText("ข้อมูลประชากร");
        jButtonFm1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonFm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFm1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel5.add(jButtonFm1, gridBagConstraints);

        buttonGroupPt.add(jButtonPt1);
        jButtonPt1.setText("PT");
        jButtonPt1.setToolTipText("ข้อมูลผู้ป่วย");
        jButtonPt1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonPt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPt1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel5.add(jButtonPt1, gridBagConstraints);

        buttonGroupPt.add(jButtonMd1);
        jButtonMd1.setText("Med");
        jButtonMd1.setToolTipText("ข้อมูลการแพทย์");
        jButtonMd1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonMd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMd1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel5.add(jButtonMd1, gridBagConstraints);

        jCheckBoxCheck.setFont(defaultFont1);
        jCheckBoxCheck.setText("Check");
        jCheckBoxCheck.setToolTipText("ตรวจสอบข้อมูล");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel5.add(jCheckBoxCheck, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanelAction.add(jPanel5, gridBagConstraints);

        jButtonIndex.setText("ใบ index");
        jButtonIndex.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIndexActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanelAction.add(jButtonIndex, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanelPatient.add(jPanelAction, gridBagConstraints);

        jPanelDetail.setLayout(new java.awt.GridBagLayout());

        jPanelContact.setBorder(javax.swing.BorderFactory.createTitledBorder("ที่อยู่ผู้ติดต่อ"));
        jPanelContact.setLayout(new java.awt.GridBagLayout());

        jPanel25.setLayout(new java.awt.GridBagLayout());

        jLabel7.setText("เกี่ยวข้องเป็น");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel25.add(jLabel7, gridBagConstraints);

        jLabel8.setText("เพศ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel25.add(jLabel8, gridBagConstraints);

        jComboBoxContactSex.setMinimumSize(new java.awt.Dimension(70, 21));
        jComboBoxContactSex.setPreferredSize(new java.awt.Dimension(70, 21));
        jComboBoxContactSex.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBoxContactSexKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel25.add(jComboBoxContactSex, gridBagConstraints);

        jComboBoxRelation.setMinimumSize(new java.awt.Dimension(100, 21));
        jComboBoxRelation.setPreferredSize(new java.awt.Dimension(100, 21));
        jComboBoxRelation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxRelationActionPerformed(evt);
            }
        });
        jComboBoxRelation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBoxRelationKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel25.add(jComboBoxRelation, gridBagConstraints);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/down.gif"))); // NOI18N
        jButton2.setToolTipText("เหมือนที่อยู่ผู้ป่วย");
        jButton2.setMinimumSize(new java.awt.Dimension(25, 25));
        jButton2.setPreferredSize(new java.awt.Dimension(25, 25));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel25.add(jButton2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 5);
        jPanelContact.add(jPanel25, gridBagConstraints);

        panelAddressContact.setOtherAddressVisible(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 5, 5);
        jPanelContact.add(panelAddressContact, gridBagConstraints);

        jLabelContact.setText("ชื่อผู้ติดต่อ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelContact.add(jLabelContact, gridBagConstraints);

        jTextFieldContactFname.setMaximumSize(new java.awt.Dimension(88, 21));
        jTextFieldContactFname.setMinimumSize(new java.awt.Dimension(88, 21));
        jTextFieldContactFname.setPreferredSize(new java.awt.Dimension(88, 21));
        jTextFieldContactFname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldContactFnameFocusLost(evt);
            }
        });
        jTextFieldContactFname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldContactFnameKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelContact.add(jTextFieldContactFname, gridBagConstraints);

        jTextFieldContactLname.setMaximumSize(new java.awt.Dimension(88, 21));
        jTextFieldContactLname.setMinimumSize(new java.awt.Dimension(88, 21));
        jTextFieldContactLname.setPreferredSize(new java.awt.Dimension(88, 21));
        jTextFieldContactLname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldContactLnameFocusGained(evt);
            }
        });
        jTextFieldContactLname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldContactLnameKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 5);
        jPanelContact.add(jTextFieldContactLname, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jPanelDetail.add(jPanelContact, gridBagConstraints);

        panelAddressPatient.setOtherAddressVisible(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        jPanelDetail.add(panelAddressPatient, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelPatient.add(jPanelDetail, gridBagConstraints);

        jPanelCard.add(jPanelPatient, "patient");

        jPanelFamily.setLayout(new java.awt.GridBagLayout());

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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        jPanelFamily.add(jPanel34, gridBagConstraints);

        jPanel12.setMinimumSize(new java.awt.Dimension(321, 175));
        jPanel12.setPreferredSize(new java.awt.Dimension(321, 175));
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
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel12.add(jScrollPane1, gridBagConstraints);

        jScrollPane2.setBorder(null);

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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel12.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanelFamily.add(jPanel12, gridBagConstraints);

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
        gridBagConstraints.gridx = 3;
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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel35.add(jRadioButtonOwner, gridBagConstraints);

        jLabelFstatus3.setFont(defaultFont1);
        jLabelFstatus3.setText("วันที่ย้ายเข้า");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel35.add(jLabelFstatus3, gridBagConstraints);

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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel35.add(dateComboBoxMoveIn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelFamily.add(jPanel35, gridBagConstraints);

        jLabelWorkOffice.setFont(defaultFont1);
        jLabelWorkOffice.setText("สถานที่ทำงาน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanelFamily.add(jLabelWorkOffice, gridBagConstraints);

        jTextFieldWorkOffice.setFont(defaultFont1);
        jTextFieldWorkOffice.setMinimumSize(new java.awt.Dimension(88, 21));
        jTextFieldWorkOffice.setPreferredSize(new java.awt.Dimension(88, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelFamily.add(jTextFieldWorkOffice, gridBagConstraints);

        jPanel13.setLayout(new java.awt.GridBagLayout());

        jButtonSearchPatient.setFont(defaultFont1);
        jButtonSearchPatient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/find.gif"))); // NOI18N
        jButtonSearchPatient.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonSearchPatient.setMaximumSize(new java.awt.Dimension(30, 26));
        jButtonSearchPatient.setMinimumSize(new java.awt.Dimension(30, 26));
        jButtonSearchPatient.setPreferredSize(new java.awt.Dimension(30, 26));
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

        jButtonReset1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonReset1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonReset1.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonReset1.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonReset1.setPreferredSize(new java.awt.Dimension(26, 26));
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
        jButtonDelete.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonDelete.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonDelete.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonDelete.setPreferredSize(new java.awt.Dimension(26, 26));
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
        jButtonSave.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonSave.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonSave.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonSave.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel13.add(jButtonSave, gridBagConstraints);

        jButtonaddHome.setFont(defaultFont1);
        jButtonaddHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pcu/images/home.gif"))); // NOI18N
        jButtonaddHome.setToolTipText("เพิ่มบ้าน");
        jButtonaddHome.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonaddHome.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonaddHome.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonaddHome.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonaddHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonaddHomeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel13.add(jButtonaddHome, gridBagConstraints);

        buttonGroupFm.add(jButtonFm);
        jButtonFm.setText("FM");
        jButtonFm.setToolTipText("ข้อมูลประชากร");
        jButtonFm.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonFm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFmActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel13.add(jButtonFm, gridBagConstraints);

        buttonGroupFm.add(jButtonPt);
        jButtonPt.setText("PT");
        jButtonPt.setToolTipText("ข้อมูลผู้ป่วย");
        jButtonPt.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonPt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel13.add(jButtonPt, gridBagConstraints);

        buttonGroupFm.add(jButtonMd);
        jButtonMd.setText("Med");
        jButtonMd.setToolTipText("ข้อมูลการแพทย์");
        jButtonMd.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonMd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMdActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanel13.add(jButtonMd, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanelFamily.add(jPanel13, gridBagConstraints);

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
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel8.add(dateComboBoxDisch, gridBagConstraints);

        jLabelFstatus2.setFont(defaultFont1);
        jLabelFstatus2.setText("สถานะ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        jPanel8.add(jLabelFstatus2, gridBagConstraints);

        jComboBoxDisch.setFont(defaultFont1);
        jComboBoxDisch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 ตาย", "2 ย้าย", "3 สาปสูญ", "9 ไม่จำหน่าย" }));
        jComboBoxDisch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDischActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel8.add(jComboBoxDisch, gridBagConstraints);

        jLabelFamilyNumber.setFont(defaultFont1);
        jLabelFamilyNumber.setText("ครอบครัวที่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel8.add(jLabelFamilyNumber, gridBagConstraints);

        integerTextFieldFamilyNumber.setColumns(2);
        integerTextFieldFamilyNumber.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        integerTextFieldFamilyNumber.setMinimumSize(new java.awt.Dimension(30, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel8.add(integerTextFieldFamilyNumber, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 5, 0);
        jPanelFamily.add(jPanel8, gridBagConstraints);

        jPanelCard.add(jPanelFamily, "family");

        jPanelMed.setLayout(new java.awt.GridBagLayout());

        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder("ข้อมูล NCD"));
        jPanel33.setMinimumSize(new java.awt.Dimension(63, 90));
        jPanel33.setPreferredSize(new java.awt.Dimension(63, 90));
        jPanel33.setLayout(new java.awt.GridBagLayout());

        jScrollPane3.setPreferredSize(new java.awt.Dimension(80, 80));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel33.add(jScrollPane3, gridBagConstraints);

        jButtonAddVital.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAddVital.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonAddVital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddVitalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 5);
        jPanel33.add(jButtonAddVital, gridBagConstraints);

        jButtonDelVital.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelVital.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonDelVital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelVitalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel33.add(jButtonDelVital, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanelMed.add(jPanel33, gridBagConstraints);

        jPanel30.setLayout(new java.awt.GridBagLayout());

        jLabelPType.setText("ตำแหน่งในชุมชน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 1, 1, 5);
        jPanel30.add(jLabelPType, gridBagConstraints);

        jTextFieldPType.setMaximumSize(new java.awt.Dimension(100, 21));
        jTextFieldPType.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldPType.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 1, 1, 1);
        jPanel30.add(jTextFieldPType, gridBagConstraints);

        jLabelPrivateDoctor.setText("แพทย์ประจำตัว");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 1, 1, 5);
        jPanel30.add(jLabelPrivateDoctor, gridBagConstraints);

        jTextFieldPrivateDoctor.setMaximumSize(new java.awt.Dimension(100, 21));
        jTextFieldPrivateDoctor.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldPrivateDoctor.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 1, 1, 1);
        jPanel30.add(jTextFieldPrivateDoctor, gridBagConstraints);

        jPanel31.setLayout(new java.awt.GridBagLayout());

        jLabelHN.setText("HN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 5);
        jPanel31.add(jLabelHN, gridBagConstraints);

        jTextFieldXN.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldXN.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldXN.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel31.add(jTextFieldXN, gridBagConstraints);

        jLabelXN.setText("XN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 5);
        jPanel31.add(jLabelXN, gridBagConstraints);

        jTextFieldHN.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldHN.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldHN.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 6);
        jPanel31.add(jTextFieldHN, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel30.add(jPanel31, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelMed.add(jPanel30, gridBagConstraints);

        buttonGroupMed.add(jButtonFm2);
        jButtonFm2.setText("FM");
        jButtonFm2.setToolTipText("ข้อมูลประชากร");
        jButtonFm2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonFm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFm2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanelMed.add(jButtonFm2, gridBagConstraints);

        buttonGroupMed.add(jButtonPt2);
        jButtonPt2.setText("PT");
        jButtonPt2.setToolTipText("ข้อมูลผู้ป่วย");
        jButtonPt2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonPt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPt2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanelMed.add(jButtonPt2, gridBagConstraints);

        buttonGroupMed.add(jButtonMd2);
        jButtonMd2.setText("MED");
        jButtonMd2.setToolTipText("ข้อมูลการแพทย์");
        jButtonMd2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonMd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMd2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanelMed.add(jButtonMd2, gridBagConstraints);

        jButtonSave2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Save16.gif"))); // NOI18N
        jButtonSave2.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jButtonSave2.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonSave2.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonSave2.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSave2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelMed.add(jButtonSave2, gridBagConstraints);

        jPanelCard.add(jPanelMed, "medical");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jPanelCard, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldAgeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAgeKeyReleased
        jTextFieldAgeFocusLost(null);
    }//GEN-LAST:event_jTextFieldAgeKeyReleased

    private void jTablePersonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablePersonKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_UP
        || evt.getKeyCode()==KeyEvent.VK_DOWN)
            selectPerson();
    }//GEN-LAST:event_jTablePersonKeyReleased

    private void jTextFieldSearchHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchHomeActionPerformed
        searchHome();
    }//GEN-LAST:event_jTextFieldSearchHomeActionPerformed

    private void jTableHomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableHomeKeyReleased

        if(evt.getKeyCode()==KeyEvent.VK_UP
        || evt.getKeyCode()==KeyEvent.VK_DOWN){
            
            int row = jTableHome.getSelectedRow();
            setHome((Home)vHome.get(row));
        }
    }//GEN-LAST:event_jTableHomeKeyReleased

    private void jTablePersonMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTablePersonMouseReleased
    {//GEN-HEADEREND:event_jTablePersonMouseReleased
        selectPerson();
    }//GEN-LAST:event_jTablePersonMouseReleased

    private void jComboBoxPrenameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxPrenameFocusLost
        jComboBoxPrenameActionPerformed(null);
    }//GEN-LAST:event_jComboBoxPrenameFocusLost

    private void jButtonaddHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonaddHomeActionPerformed
//        PanelHome thePanelHome = new PanelHome(theHM,theHosDialog,theUS);
//        thePanelHome.showPanel(thePCUObject);
        PanelHome2 thePanelHome2 = new PanelHome2(theHM,theHosDialog,theUS);
        thePanelHome2.showPanel(thePCUObject);
    }//GEN-LAST:event_jButtonaddHomeActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        int ret = theHC.thePatientControl.deletePerson(theFamily);
        if(ret!=0)
            return;
        theHosInf.resetPatient();
        jTableHome.clearSelection();
        jTablePerson.clearSelection();
        setFamily(null,null,null,null,null);
        setPatient(null);
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonReset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReset1ActionPerformed
        theHosInf.resetPatient();
        jTableHome.clearSelection();
        jTablePerson.clearSelection();
        setFamily(null,null,null,null,null);
    }//GEN-LAST:event_jButtonReset1ActionPerformed
 
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        int ret = theHomeControl.saveFamily(getFamily(),jTextFieldAge.getText(),true,theUS);
        if(ret==0)
            return ;
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
        jButtonSearchPatientActionPerformed(null);
    }//GEN-LAST:event_jTextFieldLnameActionPerformed

    private void jTextFieldFnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFnameActionPerformed
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
        setHome((Home)vHome.get(row));//        if(theHome.home_house.equals("0")){
        if(theHome.home_house.equals("0")){
            this.setFamilyV(null);
            if(theUS.confirmBox("บ้านหลังนี้เป็นบ้านนอกเขต ยืนยันการแสดงประชากรในบ้านหลังนี้",UpdateStatus.WARNING)){
                Vector v = theHomeControl.listFamilyByHomeId(theHome.getObjectId());
                this.setFamilyV(v);
            }
        }
    }//GEN-LAST:event_jTableHomeMouseReleased

    private void jCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxActionPerformed
        searchHome();  
    }//GEN-LAST:event_jCheckBoxActionPerformed

    private void jTextFieldSearchHomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchHomeKeyReleased

//      if(jTextFieldSearchHome.getText().length()>2)
//        searchHome();
//      else if (jTextFieldSearchHome.getText().equals(""))
//        searchHome();
    }//GEN-LAST:event_jTextFieldSearchHomeKeyReleased

    private void jComboBoxVillageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxVillageActionPerformed
        searchHome();
    }//GEN-LAST:event_jComboBoxVillageActionPerformed

    private void jTextFieldCoupleLnameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldCoupleLnameCaretUpdate

        //jTextFieldCoupleName.setText(jTextFieldCoupleFname.getText()+" "+jTextFieldCoupleLname.getText());
    }//GEN-LAST:event_jTextFieldCoupleLnameCaretUpdate

    private void jTextFieldCoupleFnameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldCoupleFnameCaretUpdate

       // jTextFieldCoupleName.setText(jTextFieldCoupleFname.getText()+" "+jTextFieldCoupleLname.getText());
    }//GEN-LAST:event_jTextFieldCoupleFnameCaretUpdate

    private void jTextFieldMotherLnameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldMotherLnameCaretUpdate

       // jTextFieldMotherName.setText(jTextFieldMotherFname.getText()+" "+jTextFieldMotherLname.getText());
    }//GEN-LAST:event_jTextFieldMotherLnameCaretUpdate

    private void jTextFieldFatherLnameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldFatherLnameCaretUpdate

         //jTextFieldFatherName.setText(jTextFieldFatherFname.getText()+" "+jTextFieldFatherLname.getText());
    }//GEN-LAST:event_jTextFieldFatherLnameCaretUpdate

    private void jTextFieldMotherFnameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldMotherFnameCaretUpdate

        //jTextFieldMotherName.setText(jTextFieldMotherFname.getText()+" "+jTextFieldMotherLname.getText());
    }//GEN-LAST:event_jTextFieldMotherFnameCaretUpdate

    private void jTextFieldCoupleLnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCoupleLnameFocusGained

        if(!jTextFieldCoupleFname.getText().trim().equals(""))
        {
            if(jTextFieldCoupleLname.getText().trim().equals(""))
            {
                GutilPCU.setGuiData(jTextFieldCoupleLname,GutilPCU.getGuiData(jTextFieldLname));
            }
        }
    }//GEN-LAST:event_jTextFieldCoupleLnameFocusGained

    private void jTextFieldMotherLnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldMotherLnameFocusGained

        if(!jTextFieldMotherFname.getText().trim().equals(""))
        {
            if(jTextFieldMotherLname.getText().trim().equals(""))
            {
                GutilPCU.setGuiData(jTextFieldMotherLname,GutilPCU.getGuiData(jTextFieldLname));
            }
        }
    }//GEN-LAST:event_jTextFieldMotherLnameFocusGained

    private void jTextFieldFatherLnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldFatherLnameFocusGained

        if(!jTextFieldFatherFname.getText().trim().equals(""))
        {
            if(jTextFieldFatherLname.getText().trim().equals(""))
            {
                GutilPCU.setGuiData(jTextFieldFatherLname,GutilPCU.getGuiData(jTextFieldLname));
            }
        }       
    }//GEN-LAST:event_jTextFieldFatherLnameFocusGained

    private void jTextFieldFatherFnameCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldFatherFnameCaretUpdate

      //jTextFieldFatherName.setText(jTextFieldFatherFname.getText()+" "+jTextFieldFatherLname.getText());
    }//GEN-LAST:event_jTextFieldFatherFnameCaretUpdate

    private void jComboBoxOccupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxOccupActionPerformed

        //jTextFieldOcc.setText(jComboBoxOccup.getText()); 
    }//GEN-LAST:event_jComboBoxOccupActionPerformed

    private void jTextFieldAgeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldAgeFocusLost

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
        
}//GEN-LAST:event_dateComboBoxMoveInMouseClicked

    private void dateComboBoxMoveInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxMoveInActionPerformed
        
}//GEN-LAST:event_dateComboBoxMoveInActionPerformed

    private void dateComboBoxMoveInFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxMoveInFocusLost
        
}//GEN-LAST:event_dateComboBoxMoveInFocusLost

    private void dateComboBoxDischMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateComboBoxDischMouseClicked
        
}//GEN-LAST:event_dateComboBoxDischMouseClicked

    private void dateComboBoxDischActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxDischActionPerformed
        
}//GEN-LAST:event_dateComboBoxDischActionPerformed

    private void dateComboBoxDischFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxDischFocusLost
        
}//GEN-LAST:event_dateComboBoxDischFocusLost

    private void jComboBoxDischActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDischActionPerformed
        
    }//GEN-LAST:event_jComboBoxDischActionPerformed

    private void jButtonFatherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFatherActionPerformed
        if(theFamily==null)
        {
            this.theUS.setStatus("กรุณาบันทึกข้อมูลประชากรก่อน", UpdateStatus.WARNING);
            return;
        }
        String family_id = PanelLookup.showDialog(theUS.getJFrame(),theMC
                ,this.jTextFieldFatherFname.getText()+" "+this.jTextFieldFatherLname.getText());
        if(family_id==null)
            return;
        
        theFamily.father_fid = family_id;
        Family fam = this.thePatientControl.readFamilyByFamilyIdRet(family_id);
        setTextFieldDE(jTextFieldFatherFname,fam.patient_name);
        setTextFieldDE(jTextFieldFatherLname,fam.patient_last_name);
        theFamily.father_firstname = fam.patient_name;
        theFamily.father_lastname = fam.patient_last_name;
        theFamily.father_pid = fam.pid;
    }//GEN-LAST:event_jButtonFatherActionPerformed

    private void jButtonMotherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMotherActionPerformed
        if(theFamily==null)
        {
            this.theUS.setStatus("กรุณาบันทึกข้อมูลประชากรก่อน", UpdateStatus.WARNING);
            return;
        }
        String family_id = PanelLookup.showDialog(theUS.getJFrame(),theMC
                ,this.jTextFieldMotherFname.getText()+" "+this.jTextFieldMotherLname.getText());
        if(family_id==null)
            return;
        
        theFamily.mother_fid = family_id;
        Family fam = this.thePatientControl.readFamilyByFamilyIdRet(family_id);
        setTextFieldDE(jTextFieldMotherFname,fam.patient_name);
        setTextFieldDE(jTextFieldMotherLname,fam.patient_last_name);
        theFamily.mother_firstname = fam.patient_name;
        theFamily.mother_lastname = fam.patient_last_name;
        theFamily.mother_pid = fam.pid;
    }//GEN-LAST:event_jButtonMotherActionPerformed

    private void jButtonCoupleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCoupleActionPerformed
        if(theFamily==null)
        {
            this.theUS.setStatus("กรุณาบันทึกข้อมูลประชากรก่อน", UpdateStatus.WARNING);
            return;
        }String family_id = PanelLookup.showDialog(theUS.getJFrame(),theMC
                ,this.jTextFieldCoupleFname.getText()+" "+this.jTextFieldCoupleLname.getText());
        if(family_id==null)
            return;
        
        theFamily.couple_fid = family_id;
        Family fam = this.thePatientControl.readFamilyByFamilyIdRet(family_id);
        setTextFieldDE(jTextFieldCoupleFname,fam.patient_name);
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

    private void jComboBoxContactSexKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxContactSexKeyReleased


        Gutil.setTransferCursor(evt,jComboBoxRelation,panelAddressPatient,jTextFieldLname,panelAddressContact,jComboBoxContactSex);
    }//GEN-LAST:event_jComboBoxContactSexKeyReleased

    private void jComboBoxRelationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRelationActionPerformed


    }//GEN-LAST:event_jComboBoxRelationActionPerformed

    private void jComboBoxRelationKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxRelationKeyReleased


        Gutil.setTransferCursor(evt,jComboBoxRelation,panelAddressPatient,jTextFieldLname,panelAddressContact,jComboBoxContactSex);
    }//GEN-LAST:event_jComboBoxRelationKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed



        String h = panelAddressPatient.getHouse();
        String v = panelAddressPatient.getVillage();
        String r = panelAddressPatient.getRoad();
        String p = panelAddressPatient.getPhone();
        String m = panelAddressPatient.getMobile();
        String c = panelAddressPatient.getChangwat();
        String a = panelAddressPatient.getAmpur();
        String t = panelAddressPatient.getTambon();
        panelAddressContact.setAddress(h, v, r, p, m, c, a, t);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextFieldContactFnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldContactFnameFocusLost


        jTextFieldContactLnameFocusGained(null);
    }//GEN-LAST:event_jTextFieldContactFnameFocusLost

    private void jTextFieldContactFnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldContactFnameKeyReleased


        Gutil.setTransferCursor(evt,jTextFieldContactFname,jTextFieldCoupleFname,null,jButtonSave,jTextFieldContactLname);
    }//GEN-LAST:event_jTextFieldContactFnameKeyReleased

    private void jTextFieldContactLnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldContactLnameFocusGained


        if(!jTextFieldContactFname.getText().trim().equals("")){
            if(jTextFieldContactLname.getText().trim().equals("")){
                Gutil.setGuiData(jTextFieldContactLname,Gutil.getGuiData(jTextFieldLname));
            }
        }
    }//GEN-LAST:event_jTextFieldContactLnameFocusGained

    private void jTextFieldContactLnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldContactLnameKeyReleased
        Gutil.setTransferCursor(evt,jTextFieldContactLname,jTextFieldCoupleLname,jTextFieldContactFname,jButtonSave,jRadioButtonOwner);
    }//GEN-LAST:event_jTextFieldContactLnameKeyReleased

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased

    }//GEN-LAST:event_jTable1MouseReleased

    private void jButtonAddVitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddVitalActionPerformed
        if(thePCUObject.getPatient()==null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วย",UpdateStatus.WARNING);
            return;
        }
        //konshow save patient before add NCD
        //เพราะว่าถ้าไม่ save ก่อน ข้อมูลที่ผู้ใช้เพิ่งกรอกไปนั้นจะหายไปแล้วทำให้ต้องกรอกใหม่
        jButtonSave1ActionPerformed(null);
        PanelRegisterNCD thePRNCD = new PanelRegisterNCD(theHC,theUS);
        vNCD = theHO.vNCD;
        if(vNCD == null){
            vNCD = new Vector();
        }
        boolean ret = thePRNCD.showDialog(true,true,vNCD);
        if(ret){
            thePatientControl.saveNCD(thePCUObject.getPatient(),vNCD);
            setNcdV(theHO.vNCD);
        }

    }//GEN-LAST:event_jButtonAddVitalActionPerformed

    private void jButtonDelVitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelVitalActionPerformed
        if(thePCUObject.getPatient()==null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วย",UpdateStatus.WARNING);
            return;
        }
        int[]  row = this.jTable1.getSelectedRows();
        //        vNCD = theHO.vNCD;
        if(row.length != 0) {
            for(int i=row.length-1;i>=0;i--) {
                vNCD.remove(row[i]);
            }
        }

        thePatientControl.saveNCD(thePCUObject.getPatient(),vNCD);
        setNcdV(theHO.vNCD);
    }//GEN-LAST:event_jButtonDelVitalActionPerformed

    private void jButtonSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSave1ActionPerformed
        if(jCheckBoxCheck.isSelected() && GutilPCU.getGuiData(jComboBoxLabor).equals(""))
        {
            if(jTextFieldFname.getText().equals(""))
            {
                jTextFieldFname.requestFocus();
                this.theUS.setStatus("กรุณาระบุชื่อผู้ป่วย", UpdateStatus.WARNING);
                return;
            }
            else if(jTextFieldLname.getText().equals(""))
            {
                jTextFieldLname.requestFocus();
                this.theUS.setStatus("กรุณาระบุนามสกุล", UpdateStatus.WARNING);
                return;
            }
            else if(pidPanel.getText().equals(""))
            {
                pidPanel.requestFocusFirstDigit();
                this.theUS.setStatus("กรุณาระบุเลขบัตรประชาชน", UpdateStatus.WARNING);
                return;
            }
        }
        String old_xn = "";
        if(thePatient!=null)
            old_xn = thePatient.xn;
        Patient pt = getPatient();
        JTextField jt = (JTextField)(jComboBoxRelation.getEditor()).getEditorComponent();
        String desc = jt.getText();
        if(!old_xn.equals(pt.xn))
            theResultControl.savePatientXn(pt.xn,false); 
        int ret=thePatientControl.savePatient(pt,jTextFieldAge.getText(),desc,vNCD);
        if(ret==3) pidPanel.requestFocus();
        else if(ret==6) jTextFieldContactFname.requestFocus();
        else if(ret==9) jTextFieldAge.requestFocus();
        else if(ret==10) jComboBoxOccup.requestFocus();
        else if(ret==5) jTextFieldAge.requestFocus();
    }//GEN-LAST:event_jButtonSave1ActionPerformed

    private void jButtonPrintOPDCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintOPDCardActionPerformed
        if(thePatient == null){
            theUS.setStatus("กรุณาเลือกผู้ป่วย",UpdateStatus.WARNING);
            return;
        }
        thePrintControl.printOPDCard(PrintControl.MODE_PREVIEW,theHO.vVisitPayment);
        //DialogPrintOPD.showPanel(theUS.getJFrame(),0,theHC);
    }//GEN-LAST:event_jButtonPrintOPDCardActionPerformed

    private void jButtonActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActiveActionPerformed
        thePatientControl.activePatient();
    }//GEN-LAST:event_jButtonActiveActionPerformed

    private void jTextFieldHNVisitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldHNVisitActionPerformed


        setCursor(Constant.CUR_WAIT); 
        int i = thePatientControl.readPatientByHn(jTextFieldHNVisit.getText());
        if(i==0){
            theUS.setStatus(Constant.getTextBundle("ไม่พบผู้ป่วย") +
                    " " +
                    Constant.getTextBundle("HN") +
                    " " +jTextFieldHNVisit.getText() ,UpdateStatus.WARNING);
            setCursor(Constant.CUR_DEFAULT);
            return;
        }
        if(i>1){
            theUS.setStatus(Constant.getTextBundle("พบผู้ป่วย") +
                    " " +
                    Constant.getTextBundle("HN") +
                    " " +jTextFieldHNVisit.getText()+" " +
                    Constant.getTextBundle("มากว่า 1 คนกรุณาตรวจสอบ") ,UpdateStatus.WARNING);
            setCursor(Constant.CUR_DEFAULT);
            return;
        }
        QueueVisit qv = new QueueVisit();
        Vector vWaitAppointment = thePatientControl.listWaitAppointment(thePCUObject.getPatient().getObjectId());
        Vector chooseApp = new Vector();
        boolean IOption = Gutil.isSelected( theLookupControl.readOption().inqueuevisit);
        Visit theVisit = new Visit();
        if(IOption || vWaitAppointment!=null) {
            boolean ret = theHosDialog.showDialogQueueVisit(theVisit,qv,0,IOption,vWaitAppointment,chooseApp);
            if(!ret){
                setCursor(Constant.CUR_DEFAULT);
                return;
            }
        }
        Payment vp = null;
        
        if(!theHO.vPatientPayment.isEmpty()) {
            vp = (PatientPayment)theHO.vPatientPayment.get(0);
            vp.setObjectId(null);
        }
        else{
            Plan plan = theLookupControl.readPlanById(Plan.SELF_PAY);
            if(plan==null){
                theUS.setStatus("ผู้ป่วยไม่มีสิทธิประจำตัวกรุณาบันทึกสิทธิประจำตัวก่อน",UpdateStatus.WARNING);
                return;
            }
            vp = theHO.initPayment(plan);
        }
        Vector vVisitPayment = new Vector();
        vVisitPayment.add(vp);
        if(chooseApp.isEmpty())
            theVisitControl.visitPatient(theVisit,vVisitPayment,qv);
        else {
            Appointment app = (Appointment)chooseApp.get(0);
            theVisitControl.visitPatientApp(theVisit,vVisitPayment,qv,app);
        }
        setCursor(Constant.CUR_DEFAULT);
    }//GEN-LAST:event_jTextFieldHNVisitActionPerformed

    private void jButtonSmartcardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSmartcardActionPerformed
        if (theSMCC.startReader(true, true)) {
            setSmartCardData();
        }
}//GEN-LAST:event_jButtonSmartcardActionPerformed
    public void setSmartCardData() {

        String str_pid = theSMCC.getPID();
//        String lat = "";
//        String lng = "";
        //check กับ db ก่อน ถ้าไม่มีคือผู้ป่วยใหม่
        Vector vPatient = theHC.thePatientControl.listPatientByPID(str_pid);
        if (!vPatient.isEmpty()) {
            Patient pt = (Patient) vPatient.get(0);
            if (pt.getObjectId() != null) {
                theHC.thePatientControl.readPatientByPatientID(pt.getObjectId());
            } else {
                theHC.thePatientControl.readFamilyByFamilyId(pt.family_id);
            }
            return;
        }
        //ผู้ป่วยใหม่ อ่านค่าจากบัตรมาให้
        String str_title = (theSMCC.getThaiTitleName());
        String str_fname = (theSMCC.getThaiFirstName());
        String str_lname = (theSMCC.getThaiLastName());
        String str_dob = (theSMCC.getDOB("yyyy-MM-dd", SmartCardControl.LOCALE_TH));
        String str_age = (theSMCC.getAgeYear() + "");
        String str_age_month = (theSMCC.getAgeMonth() + "");
        String str_addrNo = (theSMCC.getAddressNo());
        String str_addrMoo = (theSMCC.getAddressMoo());
        String str_addrSoi = (theSMCC.getAddressSoi());
        String str_addrRoad = (theSMCC.getAddressRoad());
        String str_addrTumbol = (theSMCC.getAddressTumbon());
        String str_addrAmphur = (theSMCC.getAddressAmphur());
        String str_addrChangwat = (theSMCC.getAddressChangwat());
        String str_sexId = theSMCC.getSexID();
        //------------------ Set value to GUI
        Prefix prefix = theHC.theLookupControl.readPrefixByName(str_title);
        jComboBoxPrename.setText(prefix==null?"":prefix.getCode());
        Gutil.setGuiData(jTextFieldFname, str_fname);
        Gutil.setGuiData(jTextFieldLname, str_lname);
        pidPanel.setText(str_pid);
        Gutil.setGuiData(jComboBoxGender, prefix==null?(str_sexId==null?"":str_sexId):prefix.sex);
        setBirthDateTrue("1");
        dateComboBoxBirthDay.setText(DateUtil.convertFieldDate(str_dob));
        Gutil.setGuiData(jTextFieldAge, str_age);
        Vector vAddress = theHC.thePatientControl.getAddressBySMC(theSMCC);
        if (!vAddress.isEmpty()) {
            Address c_addr = (Address) vAddress.get(0);
            Address a_addr = (Address) vAddress.get(1);
            Address t_addr = (Address) vAddress.get(2);
            // address
            panelAddressPatient.setAddress(
                    str_addrNo,
                    str_addrMoo,
                    str_addrRoad,
                    "",
                    "",
                    c_addr == null ? "" : c_addr.getCode(),
                    a_addr == null ? "" : a_addr.getCode(),
                    t_addr == null ? "" : t_addr.getCode(),
                    "0",
                    "");
        }
    }
    private void jButtonFm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFm1ActionPerformed
        showCard("family");
    }//GEN-LAST:event_jButtonFm1ActionPerformed

    private void jComboBoxRaceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxRaceKeyReleased
        Gutil.setTransferCursor(evt,jComboBoxRace,jComboBoxOccup,null,jComboBoxReligion,jComboBoxNation);
}//GEN-LAST:event_jComboBoxRaceKeyReleased

    private void jComboBoxNationKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxNationKeyReleased
        Gutil.setTransferCursor(evt,jComboBoxNation,jComboBoxOccup,jComboBoxRace,jComboBoxEducate,jRadioButtonOwner);
}//GEN-LAST:event_jComboBoxNationKeyReleased

    private void jButtonPt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPt1ActionPerformed
        showCard("patient");
    }//GEN-LAST:event_jButtonPt1ActionPerformed

    private void jButtonMd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMd1ActionPerformed
        showCard("medical");
    }//GEN-LAST:event_jButtonMd1ActionPerformed

    private void jButtonFmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFmActionPerformed
        showCard("family");
    }//GEN-LAST:event_jButtonFmActionPerformed

    private void jButtonPtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPtActionPerformed
        showCard("patient");
    }//GEN-LAST:event_jButtonPtActionPerformed

    private void jButtonMdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMdActionPerformed
        showCard("medical");
    }//GEN-LAST:event_jButtonMdActionPerformed

    private void jButtonFm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFm2ActionPerformed
        showCard("family");
    }//GEN-LAST:event_jButtonFm2ActionPerformed

    private void jButtonPt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPt2ActionPerformed
        showCard("patient");
    }//GEN-LAST:event_jButtonPt2ActionPerformed

    private void jButtonMd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMd2ActionPerformed
        showCard("medical");
    }//GEN-LAST:event_jButtonMd2ActionPerformed

    private void jButtonReset2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReset2ActionPerformed
        theHosInf.resetPatient();
        jTableHome.clearSelection();
        jTablePerson.clearSelection();
        setFamily(null,null,null,null,null);
        setDefaultLocation();
//        jComboBoxRace.refresh();
//        jComboBoxNation.refresh();
        jComboBoxPrename.refresh();
        jComboBoxRelation.refresh();
    }//GEN-LAST:event_jButtonReset2ActionPerformed

    private void jButtonSearchPatient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchPatient1ActionPerformed
        theHosDialog.showDialogSearchPatientPCU(theHM,jTextFieldFname.getText(),jTextFieldLname.getText(),"");
    }//GEN-LAST:event_jButtonSearchPatient1ActionPerformed

    private void jButtonDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelete1ActionPerformed
        theHC.thePatientControl.deletePerson(theFamily);
    }//GEN-LAST:event_jButtonDelete1ActionPerformed

    private void jButtonSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSave2ActionPerformed
        //konshow ที่ต้องมีปุ่ม save ตรงนี้ด้วยเพื่อความสะดวกในการใช้งานคือ เมื่อผู้ใช้เลือก ncd ก่อนแล้วค่อยกรอกตำแหน่งชุมชน
        //กรอกแพทย์ประจำตัว ถ้าไม่มีปุ่มตรงนี้ผู้ใช้ตรงเลือกไปที่ PT แล้วกด save เพื่อทำการบันทึก
        jButtonSave1ActionPerformed(null);
    }//GEN-LAST:event_jButtonSave2ActionPerformed

    private void jTextFieldForeignNoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldForeignNoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldForeignNoCaretUpdate

    private void jButtonIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIndexActionPerformed
        if(theHO.thePatient == null) {
            theHC.theUS.setStatus("ยังไม่ได้เลือกผู้ป่วย",UpdateStatus.WARNING);
            return;
        }
        if(theHO.theSite.off_id.equals("23220"))
        {
            boolean choosePrinter = false;
            try {
                String[] data = theHC.theLO.path_print.split(";");
                if(data[0].equals(Active.isDisable()))
                    choosePrinter = false;
                else
                    choosePrinter = true;

                this.theHC.theConnectionInf.open();
                Map o = new HashMap();
                if(theHO.theVisit!=null)
                    o.put("visit_id", theHO.theVisit.getObjectId());
                if(theHO.thePatient!=null)
                    o.put("patient_id", theHO.thePatient.getObjectId());
                if(theHO.theFamily!=null)
                    o.put("family_id", theHO.theFamily.getObjectId());
                if(theHO.theHome!=null)
                    o.put("home_id", theHO.theHome.getObjectId());

                JasperReport jrp = JasperCompileManager.compileReport("hprinting/byuser/index.xml");
                JasperPrint jp = JasperFillManager.fillReport(jrp,o, theHC.theConnectionInf.getConnection());
                if(choosePrinter)
                    JasperViewer.viewReport(jp,false);
                else
                    JasperPrintManager.printReport(jp,choosePrinter);
            } catch (JRException ex) {
                ex.printStackTrace();
            } finally{
                this.theHC.theConnectionInf.close();
            }
        }
        else
        {
            
            theHC.thePrintControl.printIndex();
        }
}//GEN-LAST:event_jButtonIndexActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        StringSelection stringSelection = new StringSelection(this.pidPanel.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents( stringSelection,null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonWSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWSActionPerformed
        int rightData = this.theHC.theVisitControl.seacrhWSRightByPid(theHC.theHP.aPanelVisit.theHD);
        if(rightData == 0) {
            theUS.setStatus("กรุณาตรวจสอบความถูกต้องของเลขบัตรประชาชน", UpdateStatus.WARNING);
            return;
        }
        if(rightData == 4) {
            theUS.setStatus("ไม่พบสิทธิ์ใดๆของผู้ป่วย", UpdateStatus.WARNING);
            return;
        }
        if(rightData == 5) {
            theUS.setStatus("กรุณาตรวจสอบ Username และ Password ของ Webservice", UpdateStatus.WARNING);
            return;
        }
        if(rightData == 6) {
            theUS.setStatus("กรุณาตรวจสอบความถูกต้องของ WS URL", UpdateStatus.WARNING);
            return;
        }
        if(rightData == 7) {
            theUS.setStatus("ไม่สามารถเชื่อมต่อไปยัง WebService กรุณาตรวจสอบการเชื่อมต่อ", UpdateStatus.WARNING);
            return;
        }
        if(rightData == 8) {
            theUS.setStatus("พบข้อผิดพลาดของ WebService อาจเป็นเพราะ Webservice Down กรุณารอสักครู่", UpdateStatus.WARNING);
            return;
        }
        if(rightData == 9) {
            theUS.setStatus("เนื่องจากมีผู้ใช้งานจำนวนมาก กรุณาลองอีกครั้ง", UpdateStatus.WARNING);
            return;
        }
        if(rightData == 10) {
            theUS.setStatus("ยกเลยโดยผู้ใช้", UpdateStatus.WARNING);
            return;
        }
        if(rightData == 11) {
            theUS.setStatus("กรุณาเลือกผู้ป่วย", UpdateStatus.WARNING);
            return;
        }
        //          jTablePatientPayment.setRowSelectionInterval(vPatientPayment.size()-1, vPatientPayment.size()-1);
        
}//GEN-LAST:event_jButtonWSActionPerformed
    public void setNcdV(Vector ncd_v)
    {
        vNCD = ncd_v;
        TaBleModel tm ;
        if(vNCD == null || vNCD.isEmpty()){
            tm= new TaBleModel(new String[]{""},0);
            jTable1.setModel(tm);
            return;
        }
        tm = new TaBleModel(new String[]{"",""},vNCD.size());
        for(int i=0 ;i<vNCD.size(); i++)
        {
            NCD ncd_data = (NCD)vNCD.get(i);
            tm.setValueAt(ncd_data.ncd_number,i,0);
            NCDGroup group = theHC.theLookupControl.readNCDGroupById(ncd_data.ncd_group_id);
            if(group!=null)
                tm.setValueAt(group.description,i,1);
        }
        jTable1.setModel(tm);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroupFm;
    private javax.swing.ButtonGroup buttonGroupMed;
    private javax.swing.ButtonGroup buttonGroupPt;
    private com.hospital_os.utility.DateComboBox dateComboBoxBirthDay;
    private com.hospital_os.utility.DateComboBox dateComboBoxDisch;
    private com.hospital_os.utility.DateComboBox dateComboBoxMoveIn;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.pcu.utility.IntegerTextField integerTextFieldFamilyNumber;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonActive;
    private javax.swing.JButton jButtonAddVital;
    private javax.swing.JButton jButtonCancelCouple;
    private javax.swing.JButton jButtonCancelFather;
    private javax.swing.JButton jButtonCancelMother;
    private javax.swing.JButton jButtonCouple;
    private javax.swing.JButton jButtonDelVital;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonDelete1;
    private javax.swing.JButton jButtonFather;
    private javax.swing.JToggleButton jButtonFm;
    private javax.swing.JToggleButton jButtonFm1;
    private javax.swing.JToggleButton jButtonFm2;
    private javax.swing.JButton jButtonIndex;
    private javax.swing.JToggleButton jButtonMd;
    private javax.swing.JToggleButton jButtonMd1;
    private javax.swing.JToggleButton jButtonMd2;
    private javax.swing.JButton jButtonMother;
    private javax.swing.JButton jButtonPrintOPDCard;
    private javax.swing.JToggleButton jButtonPt;
    private javax.swing.JToggleButton jButtonPt1;
    private javax.swing.JToggleButton jButtonPt2;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonReset1;
    private javax.swing.JButton jButtonReset2;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSave1;
    private javax.swing.JButton jButtonSave2;
    private javax.swing.JButton jButtonSearchPatient;
    private javax.swing.JButton jButtonSearchPatient1;
    private javax.swing.JButton jButtonSmartcard;
    private javax.swing.JButton jButtonWS;
    private javax.swing.JButton jButtonaddHome;
    private javax.swing.JCheckBox jCheckBox;
    private javax.swing.JCheckBox jCheckBoxCheck;
    private javax.swing.JCheckBox jCheckBoxTrueBirthday;
    private javax.swing.JComboBox jComboBoxA;
    private javax.swing.JComboBox jComboBoxBlood;
    private javax.swing.JComboBox jComboBoxC;
    private javax.swing.JComboBox jComboBoxContactSex;
    private javax.swing.JComboBox jComboBoxDisch;
    private javax.swing.JComboBox jComboBoxEducate;
    private javax.swing.JComboBox jComboBoxGender;
    private javax.swing.JComboBox jComboBoxLabor;
    private javax.swing.JComboBox jComboBoxMarriage;
    private com.hosv3.gui.component.HosComboBox jComboBoxNation;
    private com.hosv3.gui.component.HosComboBox jComboBoxOccup;
    private com.hosv3.gui.component.HosComboBox jComboBoxPrename;
    private com.hosv3.gui.component.HosComboBox jComboBoxRace;
    private com.hosv3.gui.component.HosComboBox jComboBoxRelation;
    private javax.swing.JComboBox jComboBoxReligion;
    private javax.swing.JComboBox jComboBoxT;
    private javax.swing.JComboBox jComboBoxTypeArea;
    private javax.swing.JComboBox jComboBoxVillage;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAge;
    private javax.swing.JLabel jLabelAmpur;
    private javax.swing.JLabel jLabelBlood;
    private javax.swing.JLabel jLabelChangwat;
    private javax.swing.JLabel jLabelContact;
    private javax.swing.JLabel jLabelCoupleName;
    private javax.swing.JLabel jLabelEducate;
    private javax.swing.JLabel jLabelFamilyNumber;
    private javax.swing.JLabel jLabelFatherName;
    private javax.swing.JLabel jLabelFstatus;
    private javax.swing.JLabel jLabelFstatus2;
    private javax.swing.JLabel jLabelFstatus3;
    private javax.swing.JLabel jLabelGender;
    private javax.swing.JLabel jLabelHCIS;
    private javax.swing.JLabel jLabelHN;
    private javax.swing.JLabel jLabelHomeNumber;
    private javax.swing.JLabel jLabelLabor;
    private javax.swing.JLabel jLabelMarriage;
    private javax.swing.JLabel jLabelMoo;
    private javax.swing.JLabel jLabelMotherName;
    private javax.swing.JLabel jLabelNation;
    private javax.swing.JLabel jLabelOccup;
    private javax.swing.JLabel jLabelPType;
    private javax.swing.JLabel jLabelPid;
    private javax.swing.JLabel jLabelPrivateDoctor;
    private javax.swing.JLabel jLabelRace;
    private javax.swing.JLabel jLabelReligion;
    private javax.swing.JLabel jLabelRoad;
    private javax.swing.JLabel jLabelTambon;
    private javax.swing.JLabel jLabelVillageN;
    private javax.swing.JLabel jLabelWorkOffice;
    private javax.swing.JLabel jLabelXN;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelAction;
    private javax.swing.JPanel jPanelCard;
    private javax.swing.JPanel jPanelContact;
    private javax.swing.JPanel jPanelDetail;
    private javax.swing.JPanel jPanelFamily;
    private javax.swing.JPanel jPanelMed;
    private javax.swing.JPanel jPanelPatient;
    private javax.swing.JRadioButton jRadioButtonOwner;
    private javax.swing.JRadioButton jRadioButtonTenant;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.hosv3.gui.component.HJTableSort jTable1;
    private com.hosv3.gui.component.HJTableSort jTableHome;
    private com.hosv3.gui.component.HJTableSort jTablePerson;
    private com.hospital_os.utility.IntegerTextField jTextFieldAge;
    private javax.swing.JTextField jTextFieldContactFname;
    private javax.swing.JTextField jTextFieldContactLname;
    private javax.swing.JTextField jTextFieldCoupleFname;
    private javax.swing.JTextField jTextFieldCoupleLname;
    private javax.swing.JTextField jTextFieldFatherFname;
    private javax.swing.JTextField jTextFieldFatherLname;
    private javax.swing.JTextField jTextFieldFname;
    private javax.swing.JTextField jTextFieldForeignNo;
    private javax.swing.JTextField jTextFieldHN;
    private javax.swing.JTextField jTextFieldHNVisit;
    private javax.swing.JTextField jTextFieldLname;
    private javax.swing.JTextField jTextFieldMotherFname;
    private javax.swing.JTextField jTextFieldMotherLname;
    private javax.swing.JTextField jTextFieldPType;
    private javax.swing.JTextField jTextFieldPrivateDoctor;
    private javax.swing.JTextField jTextFieldSearchHome;
    private javax.swing.JTextField jTextFieldWorkOffice;
    private javax.swing.JTextField jTextFieldXN;
    private com.hosv3.gui.component.PanelAddress panelAddressContact;
    private com.hosv3.gui.component.PanelAddress panelAddressPatient;
    private com.hosv3.gui.component.PIDPanel pidPanel;
    // End of variables declaration//GEN-END:variables


    private Vector listDischarge() {
        if(vret!=null)
            return vret;
        vret = new Vector<ComboFix>();
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
        jPanel34.setVisible(flang);
        jButtonaddHome.setVisible(flang);        
    }
    
    private void setVisiblePanel(boolean flang)
    {
       jComboBoxT.setVisible(flang);
       jComboBoxA.setVisible(flang);
       jComboBoxC.setVisible(flang);
    }

    public Patient getPatient()
    {
        if(thePatient==null)
            thePatient = theHO.initPatient();
        thePatient.setFamily(getFamily());
        thePatient.p_type = this.jTextFieldPType.getText();
        thePatient.private_doc = Gutil.getGuiData(jTextFieldPrivateDoctor);
        thePatient.relation = jComboBoxRelation.getText();
        thePatient.sex_contact = Gutil.getGuiData(jComboBoxContactSex);
        thePatient.xn = jTextFieldXN.getText();
//        thePatient.hn = jTextFieldHN.getText();
        thePatient.contact_fname = Gutil.getGuiData(jTextFieldContactFname);
        thePatient.contact_lname = Gutil.getGuiData(jTextFieldContactLname);
        if(!panelAddressPatient.isOtherCountry().equals("1"))
        {
            thePatient.changwat = panelAddressPatient.getChangwat();
            thePatient.tambon = panelAddressPatient.getTambon();
            thePatient.ampur = panelAddressPatient.getAmpur();
        }
        else
        {
            thePatient.changwat = "";
            thePatient.tambon = "";
            thePatient.ampur = "";
        }
        thePatient.changwat_contact = panelAddressContact.getChangwat();
        thePatient.ampur_contact = panelAddressContact.getAmpur(); 
        thePatient.tambon_contact = panelAddressContact.getTambon();
        thePatient.house = panelAddressPatient.getHouse();
        thePatient.house_contact = panelAddressContact.getHouse();
        thePatient.phone = panelAddressPatient.getPhone();
        thePatient.phone_contact = panelAddressContact.getPhone();
        thePatient.mobile_phone = panelAddressPatient.getMobile();
        thePatient.contact_mobile_phone = panelAddressContact.getMobile();
        thePatient.road = panelAddressPatient.getRoad();
        thePatient.road_contact = panelAddressContact.getRoad();
        thePatient.village = panelAddressPatient.getVillage();
        thePatient.village_contact = panelAddressContact.getVillage();
        thePatient.is_other_country = panelAddressPatient.isOtherCountry();
        thePatient.other_address = panelAddressPatient.getOtherCountry();

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
        theFamily.foreigner_card_no = GutilPCU.getGuiData(jTextFieldForeignNo);

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
        theFamily.move_in_date_time = dateComboBoxMoveIn.getText();
        theFamily.discharge_date_time = dateComboBoxDisch.getText();
        theFamily.discharge_status_id = GutilPCU.getGuiData(this.jComboBoxDisch);

        if(this.jRadioButtonOwner.isSelected())
            theFamily.status_id = "1";
        else
            theFamily.status_id = "2";
        if(theHome!=null)
            theFamily.home_id = theHome.getObjectId();
        return theFamily;
    }
        
    public void setDefaultComboBox()
    {
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
            GutilPCU.setGuiData(jComboBoxLabor,"");
            GutilPCU.setGuiData(jTextFieldForeignNo,"");
            setBirthDateTrue("0");
            dateComboBoxBirthDay.setText("");
            dateComboBoxDisch.setText("");
            dateComboBoxMoveIn.setText("");
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
        setHome(home);

        // SOmprasong add 281209 clear all before set object
        theFamily = family;
        thePatient = null;
        jLabelHCIS.setText("HCIS:");
        if(theFamily==null) {
             setDefaultComboBox();
             return;
         }
        jComboBoxNation.refresh();
        jComboBoxRace.refresh();
         jComboBoxPrename.setText(theFamily.f_prefix_id);
         GutilPCU.setGuiData(jTextFieldFname,theFamily.patient_name);
         GutilPCU.setGuiData(jTextFieldLname,theFamily.patient_last_name);
         GutilPCU.setGuiData(jComboBoxGender,theFamily.f_sex_id);
         String age = DateUtil.calculateAge(theFamily.patient_birthday,this.thePCUObject.getCurrentDateTime());
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
             setTextFieldDE(jTextFieldFatherFname,father.patient_name);
             setTextFieldDE(jTextFieldFatherLname,father.patient_last_name);
         }
         else{
             jTextFieldFatherFname.setEditable(true);
             jTextFieldFatherLname.setEditable(true);
             GutilPCU.setGuiData(jTextFieldFatherFname,theFamily.father_firstname);
             GutilPCU.setGuiData(jTextFieldFatherLname,theFamily.father_lastname);
         }
         if(mother!=null){
             setTextFieldDE(jTextFieldMotherFname,mother.patient_name);
             setTextFieldDE(jTextFieldMotherLname,mother.patient_last_name);
         }
         else{
             jTextFieldMotherFname.setEditable(true);
             jTextFieldMotherLname.setEditable(true);
             GutilPCU.setGuiData(jTextFieldMotherFname,theFamily.mother_firstname);
             GutilPCU.setGuiData(jTextFieldMotherLname,theFamily.mother_lastname);
         }
         if(couple!=null){
             setTextFieldDE(jTextFieldCoupleFname,couple.patient_name);
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
         GutilPCU.setGuiData(jTextFieldForeignNo,theFamily.foreigner_card_no);

         pidPanel.setText(theFamily.pid);
         if(("1").equalsIgnoreCase(theFamily.status_id))
            jRadioButtonOwner.setSelected(true);
         else
            jRadioButtonTenant.setSelected(true);
         
         setBirthDateTrue(theFamily.patient_birthday_true);
         GutilPCU.setGuiData(jTextFieldWorkOffice,theFamily.work_office);
         dateComboBoxBirthDay.setText(DateUtil.convertFieldDate(theFamily.patient_birthday));
         GutilPCU.setGuiData(integerTextFieldFamilyNumber,theFamily.family_number);
         dateComboBoxMoveIn.setText(DateUtil.convertFieldDate(theFamily.move_in_date_time));
         dateComboBoxDisch.setText(DateUtil.convertFieldDate(theFamily.discharge_date_time));
         GutilPCU.setGuiData(this.jComboBoxDisch, theFamily.discharge_status_id);
         
        jLabelHCIS.setText("HCIS:" + theFamily.hn_hcis);
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
         if(vPerson != null)
         {
             tm = new TaBleModel(col,(vPerson.size()<100)?vPerson.size():100);
            String currentDateTime = thePCUObject.getCurrentDateTime();
            //ดักไว้ที่จำนวนไม่เกิน 100 คนเพราะมันจะช้ามาก
            for(int i=0;i<vPerson.size() && i<100; i++)
            {   familyTemp = (Family)vPerson.get(i);
                String family_age = DateUtil.calculateAge(familyTemp.patient_birthday,currentDateTime);               
                //tm.setValueAt(familyTemp.hn,i,0);  
                tm.setValueAt(theHC
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
        int rowPerson = jTablePerson.getSelectedRow(); 
        if(rowPerson != -1)
        {
            Family fam = (Family)vPerson.get(rowPerson);
            String pat_id = thePatientControl.getPatientIDByFamilyID(fam.getObjectId());
//            thePatientControl.readFamilyByFamilyId(fam.getObjectId());
            thePatientControl.readPatientByPatientID(pat_id);
        }
    }


    public void setLanguage(){
        GutilPCU.setLanguage(jLabelHCIS);
        GutilPCU.setLanguage(jLabelHN);
        GutilPCU.setLanguage(jLabelXN);
        GutilPCU.setLanguage(jButtonMd);
        GutilPCU.setLanguage(jButtonPt);
        GutilPCU.setLanguage(jButtonFm);
        GutilPCU.setLanguage(jButtonaddHome);
        GutilPCU.setLanguage(jButtonMd1);
        GutilPCU.setLanguage(jButtonPt1);
        GutilPCU.setLanguage(jButtonFm1);
        GutilPCU.setLanguage(jButtonPrintOPDCard);
        GutilPCU.setLanguage(jLabel17);
        GutilPCU.setLanguage(jButton2);
        GutilPCU.setLanguage(jPanel10);
        GutilPCU.setLanguage(jLabelPid);
        GutilPCU.setLanguage(jLabelGender);
        GutilPCU.setLanguage(jCheckBoxTrueBirthday);
        GutilPCU.setLanguage(jLabelAge);
        GutilPCU.setLanguage(jLabel3);
        GutilPCU.setLanguage(jLabelMarriage);
        GutilPCU.setLanguage(jLabelBlood);
        GutilPCU.setLanguage(jLabelOccup);
        GutilPCU.setLanguage(jLabelRace);
        GutilPCU.setLanguage(jLabelNation);
        GutilPCU.setLanguage(jLabelReligion);
        GutilPCU.setLanguage(jLabelEducate);
        GutilPCU.setLanguage(jLabelFatherName);
        GutilPCU.setLanguage(jLabelMotherName);
        GutilPCU.setLanguage(jLabelCoupleName);
        GutilPCU.setLanguage(jLabelFstatus);
        GutilPCU.setLanguage(jLabelFamilyNumber);
        GutilPCU.setLanguage(jRadioButtonOwner);
        GutilPCU.setLanguage(jRadioButtonTenant);
        GutilPCU.setLanguage(jLabel4);
        GutilPCU.setLanguage(jLabel6);
        GutilPCU.setLanguage(jLabel9);
        GutilPCU.setLanguage(jLabel10);
        GutilPCU.setLanguage(jLabel14);
        GutilPCU.setLanguage(jLabel15);
        GutilPCU.setLanguage(jLabel16);
        GutilPCU.setLanguage(jCheckBox);
        GutilPCU.setLanguage(jButtonSave);
        GutilPCU.setLanguage(jButtonaddHome);
        GutilPCU.setLanguage(jButtonSearchPatient);
        GutilPCU.setLanguage(jLabelFstatus3);
        GutilPCU.setLanguage(jLabelLabor);
        GutilPCU.setLanguage(jLabelFstatus2);
        GutilPCU.setLanguage(jRadioButtonOwner);
        GutilPCU.setLanguage(jRadioButtonTenant);
        GutilPCU.setLanguage(jLabelWorkOffice);
        GutilPCU.setLanguage(jLabelContact);
        GutilPCU.setLanguage(jLabel7);
        GutilPCU.setLanguage(jLabel8);
        GutilPCU.setLanguage(jButton2);
        GutilPCU.setLanguage(jButtonActive);
        GutilPCU.setLanguage(jButtonSmartcard);
        GutilPCU.setLanguage(jLabelPType);
        GutilPCU.setLanguage(jLabelPrivateDoctor);
        GutilPCU.setLanguage(jPanelContact);
        GutilPCU.setLanguage(jPanel33);
        panelAddressContact.setLanguage("");
        panelAddressPatient.setLanguage("");
    }
    public static void main(String[] argc){
        PanelPerson2 pp = new PanelPerson2();
        JFrame jf = new JFrame();
        jf.getContentPane().add(pp);
        jf.pack();
        jf.setVisible(true);
    }
 
    public void setUpdateStatus(UpdateStatus us) {
        theUS = us;
    }

    private void showCard(String cardname) {
        CardLayout cl = (CardLayout)jPanelCard.getLayout();
        cl.show(jPanelCard,cardname);
        if(cardname.equals("family")){
            this.jButtonFm.setSelected(true);
            this.jButtonFm1.setSelected(true);
            this.jButtonFm2.setSelected(true);
        }
        if(cardname.equals("patient")){
            this.jButtonPt.setSelected(true);
            this.jButtonPt1.setSelected(true);
            this.jButtonPt2.setSelected(true);
        }
        if(cardname.equals("medical")){
            this.jButtonMd.setSelected(true);
            this.jButtonMd1.setSelected(true);
            this.jButtonMd2.setSelected(true);
        }
    }
}
