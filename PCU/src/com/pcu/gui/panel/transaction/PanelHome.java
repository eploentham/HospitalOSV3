/*
 * PanelHome.java
 *
 * Created on 9 มิถุนายน 2548, 13:37 น.
 */

package com.pcu.gui.panel.transaction;

import com.pcu.control.AllComboBoxControl;
import com.pcu.control.HomeControl;
import com.pcu.control.HosManage;
import com.pcu.control.PCUObject;
import com.pcu.control.SetupPcuControl;
import com.pcu.control.SystemControl;
import com.pcu.gui.dialog.HosDialog;
import com.pcu.subject.HomePatientSubject;
import javax.swing.*;
import java.util.*;

import com.hosv3.utility.connection.UpdateStatus;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;

import com.pcu.utility.*;
import com.pcu.subject.ManageHomePatient;
import com.pcu.object.*;
import com.pcu.utility.DateUtil;

import com.hosv3.gui.dialog.DialogDeath;
import com.hosv3.utility.GuiLang;
/**
 *
 * @author  jao
 *@deprecated henbe unused
 */
public class PanelHome extends javax.swing.JPanel implements ManageHomePatient{ 
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JMenuItem jMenuItemCut;
    private javax.swing.JMenuItem jMenuItemPast;
    private javax.swing.JMenuItem jMenuItemMoveHome0;
    
    private Home theHome ;
    private SubHome theSubHome ; 
    private Family theFamily;
    private WaterEradicate theWaterEradicate ; 
    private BugCarrier theBugCarrier ;
    private FoodStandard theFoodStandard ;
    private HouseStandard theHouseStandard; 
    private HomePatientSubject theHomePatientSubject;
    private Herb theHerb;
    private Pet thePet ;   
    private Employee theEmployee;
    private PetType thePetType;
    private Patient thePatient;
    private Village theVillage;
    private Vector vHome = new Vector();
    private Vector vSubHome = new Vector();
    private Vector vWaterEradicate = new Vector();
    private Vector vHouseStandard = new Vector();
    private Vector vFoodStandard = new Vector();
    private Vector vBugCarrier = new Vector();
    private Vector vHerb = new Vector();
    private Vector vPet = new Vector();
    private Vector vPetType = new Vector();
    private Vector vFamily = new Vector();
    private Vector vPatient = new Vector();   
    private Vector vPrefix = new Vector();
    private Vector vSex = new Vector();
    private Vector vTambal = new Vector();
    private ComboFix cftm = new ComboFix();
    private SystemControl theSystemControl;
    private HosDialog theHosDialog;
    private HosManage theHosManage;
    private HomeControl theHomeControl;
    private AllComboBoxControl theAllComboBoxControl;
    private SetupPcuControl theSetupPcuControl;
    private CelRendererSexType theCelRendererSexType = new CelRendererSexType();
    private CelRendererStatusType theCelRendererStatusType = new CelRendererStatusType();
    private Site theSite;
    private String office_id;
    private String staff_id;
    private String search ;
    private String search2 ;
    private String home_number ;
    private String home_house ;
    private String changwat;
    private String road;
    private String moo;
    private String copypid;
    private boolean flang = false;
    private boolean result = false;
    private boolean modifyHome = false;
    private boolean modifyHerb = false;
    private boolean modifyPet = false;
    private boolean actioncombo = true;
    private boolean checkpetborn = false;
    private int rowHome;
    private int rowSubHome;
    private int rowHerb;
    private int rowPet;
    HosManage theHM;
    JFrame frm1;
    private boolean flagInsert = true;

    private UpdateStatus theUS;

    private Vector theFamilyTempV = new Vector();

    private PanelPerson thePanelPerson;

    private PCUObject pcuobject;
    /** Creates new form PanelHome */
    public PanelHome(HosManage hm,HosDialog hd,UpdateStatus us) {
        theHosManage = hm;
        pcuobject = hm.thePO;
        theUS = us;
        theAllComboBoxControl = hm.theHosControl.theAllComboBoxControl;
        theHomeControl = hm.theHosControl.theHomeControl;
        theSystemControl = hm.theHosControl.theSystemControl;
        theSetupPcuControl = hm.theHosControl.theSetupPcuControl;
        theHM = hm;
        theHosDialog = hd;
        theHomePatientSubject = hm.theHosSubject.theHomePatientSubject;
        theHomePatientSubject.attachHomePatient(this);
               
        initComponents();
        initPopupMenu();
        setLanguage();       
        initDatas();
        setDefaultComboBox();
        setDefaultHospital();        
        setEnableGuiHome(true);
        setEnableGuiSubHome(false);
        setEnableGuiHerb(false);
        setEnableGuiPet(false);        
        setEnableGuiFamily(false);
        dateComboBoxPetBirthDay.setEditable(true);
        dateComboBoxVaccineDay.setEditable(true);
        dateComboBoxBirthDay.setEditable(true);
        jComboBoxPetType.setEditable(true);
        jButtonDelHome.setEnabled(false);
        this.jPanelManageFamily.setVisible(false);
        //jButtonSavePatient.setVisible(false);
        thePanelPerson = new PanelPerson(theHosManage,theHosDialog,1,theHome,theFamily); 
    }
     public void initDatas() 
    {           
        //vPrefix = theAllComboBoxControl.listPrefix();
        //vSex = theAllComboBoxControl.listSex();
        ComboboxModel.initComboBox(jComboBoxWaterDrink,theAllComboBoxControl.listWaterDrink());
        ComboboxModel.initComboBox(jComboBoxWaterUse,theAllComboBoxControl.listWaterUse());
        //ComboboxModel.initComboBox(jComboBoxHomeZone,theAllComboBoxControl.listHomeZone());
        ComboboxModel.initComboBox(jComboBoxGarbageMethod,theAllComboBoxControl.listGarbageMethod());
        ComboboxModel.initComboBox(jComboBoxHomeCharacter,theAllComboBoxControl.listHomeChar());
        ComboboxModel.initComboBox(jComboBoxCommunity,theAllComboBoxControl.listComChar());
        ComboboxModel.initComboBox(jComboBoxDungControl,theAllComboBoxControl.listDungMethod());      
        ComboboxModel.initComboBox(jComboBoxPetSex,theAllComboBoxControl.listPetSex());
        ComboboxModel.initComboBox(jComboBoxPetType,theAllComboBoxControl.listPetType());
        ComboboxModel.initComboBox(jComboBoxVillage,theAllComboBoxControl.listVillage());            
        ComboboxModel.initComboBox(jComboBoxVillage1,theAllComboBoxControl.listVillage());
        ComboboxModel.initComboBox(jComboBoxChungwat,theAllComboBoxControl.listAddressCGW());
    }
     
    private void setDefaultHospital()
    {
        theSite = theSystemControl.theHospital_Site;
        if(theSite != null)
        {
            changwat = theSite.changwat;
            String amphur = theSite.amphor;
            String tambol = theSite.tambon;
            jLabelHealthCenterName.setText(theSite.full_name); 
            jLabelTambon.setText(ComboboxModel.getStringConboBox(jComboBoxTambol));
            jLabelAmphur.setText(ComboboxModel.getStringConboBox(jComboBoxAmphur));
            jLabelChangwat.setText(ComboboxModel.getStringConboBox(jComboBoxChungwat));            
            changwat = null;
            amphur = null;
            tambol = null;
        }      
    } 
    
    
    public void setObject(PCUObject pcuobject)
    {   /** ไปจัดการต่อเรื่องของข้อมูลที่จะรับ ถ้าเมื่อไรไม่มีข้อมูล นั้นจะทำอย่างไรบนหน้า GUI */
        Constant.println("_henbe_______________________" + this.getClass().toString());
        if(pcuobject != null)
        {
            theEmployee = null;
            if(pcuobject.getEmployee() != null)
            {
              theEmployee = pcuobject.getEmployee();
              staff_id = pcuobject.getEmployee().getObjectId();
              
            }
            
            if(pcuobject.getVisit() != null)
            {
              //  theVisit = pcuobject.getVisit();
                
            }
            thePatient = null;
            if(pcuobject.getPatient() != null)
            {              
                thePatient = pcuobject.getPatient();
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

        WaterDrinkAmplyGroup = new javax.swing.ButtonGroup();
        WaterUseAmplyGroup = new javax.swing.ButtonGroup();
        ToiletStandardGroup = new javax.swing.ButtonGroup();
        GarbageGroup = new javax.swing.ButtonGroup();
        WaterEradicateGroup = new javax.swing.ButtonGroup();
        HomeDurabilityGroup = new javax.swing.ButtonGroup();
        HomeCareGroup = new javax.swing.ButtonGroup();
        HomeLightGroup = new javax.swing.ButtonGroup();
        HomeCleannessGroup = new javax.swing.ButtonGroup();
        HomeVentilationGroup = new javax.swing.ButtonGroup();
        PublicHealthGroup = new javax.swing.ButtonGroup();
        MixtureFoodGroup = new javax.swing.ButtonGroup();
        FoodVesselGroup = new javax.swing.ButtonGroup();
        FoodCoverGroup = new javax.swing.ButtonGroup();
        WashVesselGroup = new javax.swing.ButtonGroup();
        KeepVesselGroup = new javax.swing.ButtonGroup();
        KitchenGarbageGroup = new javax.swing.ButtonGroup();
        KitchenCleannessGroup = new javax.swing.ButtonGroup();
        FoodStandardGroup = new javax.swing.ButtonGroup();
        UseIodineGroup = new javax.swing.ButtonGroup();
        SaltIodineGroup = new javax.swing.ButtonGroup();
        ProductIodineGroup = new javax.swing.ButtonGroup();
        RatControlGroup = new javax.swing.ButtonGroup();
        CockroachControlGroup = new javax.swing.ButtonGroup();
        FlyControlGroup = new javax.swing.ButtonGroup();
        SummaryControlGroup = new javax.swing.ButtonGroup();
        PetGroup = new javax.swing.ButtonGroup();
        UseGroup = new javax.swing.ButtonGroup();
        PetSexGroup = new javax.swing.ButtonGroup();
        AnimalControlGroup = new javax.swing.ButtonGroup();
        ResponsibleZone = new javax.swing.ButtonGroup();
        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jPanelHealthCenterDetail = new javax.swing.JPanel();
        jLabelHealthCenter = new javax.swing.JLabel();
        jLabelHealthCenterName = new javax.swing.JLabel();
        jLabelTambon1 = new javax.swing.JLabel();
        jLabelTambon = new javax.swing.JLabel();
        jLabelAmphur0 = new javax.swing.JLabel();
        jLabelAmphur = new javax.swing.JLabel();
        jLabelChangwat1 = new javax.swing.JLabel();
        jLabelChangwat = new javax.swing.JLabel();
        jPanelDetail = new javax.swing.JPanel();
        jTabbedPaneHomeDatail = new javax.swing.JTabbedPane();
        jPanelHomeDetail = new javax.swing.JPanel();
        jPanelHome2 = new javax.swing.JPanel();
        jLabelHomeOrder = new javax.swing.JLabel();
        integerTextField1 = new com.pcu.utility.IntegerTextField();
        integerTextField2 = new com.pcu.utility.IntegerTextField();
        integerTextField3 = new com.pcu.utility.IntegerTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelHomeNumber = new javax.swing.JLabel();
        jLabelRoad = new javax.swing.JLabel();
        jLabelChungwat = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabelMoo = new javax.swing.JLabel();
        jTextFieldMoo = new javax.swing.JTextField();
        jTextFieldRoad = new javax.swing.JTextField();
        jTextFieldHomeNumber = new javax.swing.JTextField();
        jLabelVillage1 = new javax.swing.JLabel();
        jComboBoxVillage1 = new javax.swing.JComboBox();
        jPanelHome3 = new javax.swing.JPanel();
        jComboBoxAmphur = new javax.swing.JComboBox();
        jComboBoxChungwat = new javax.swing.JComboBox();
        jComboBoxTambol = new javax.swing.JComboBox();
        jPanelHome5 = new javax.swing.JPanel();
        jLabelVolunteer = new javax.swing.JLabel();
        jTextFieldVolunteer = new javax.swing.JTextField();
        jLabelVolunteerName = new javax.swing.JLabel();
        jTextFieldVolunteerName = new javax.swing.JTextField();
        integerTextFieldQuantity = new com.pcu.utility.IntegerTextField();
        jLabelQuantity = new javax.swing.JLabel();
        jLabelTelephoneOwner = new javax.swing.JLabel();
        jTextFieldOwnerName = new javax.swing.JTextField();
        jLabelOwnerName = new javax.swing.JLabel();
        jTextFieldOwnerNumber = new javax.swing.JTextField();
        jPanelHome6 = new javax.swing.JPanel();
        jLabelHomeCharacter = new javax.swing.JLabel();
        jComboBoxHomeCharacter = new javax.swing.JComboBox();
        jLabelCommunity = new javax.swing.JLabel();
        jComboBoxCommunity = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabelResponsibleZone = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jRadioButtonInZone = new javax.swing.JRadioButton();
        jRadioButtonOutZone = new javax.swing.JRadioButton();
        jPanelFamily = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableFamily = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jButtonCut = new javax.swing.JButton();
        jButtonPaste = new javax.swing.JButton();
        jButtonMoveHome0 = new javax.swing.JButton();
        jPanelManageFamily = new javax.swing.JPanel();
        jButtonDelPatient = new javax.swing.JButton();
        jButtonUpdatePatient = new javax.swing.JButton();
        jButtonStatus = new javax.swing.JButton();
        jButtonAddPatient = new javax.swing.JButton();
        jPanelHerb = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableHerb = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jLabelHerbName = new javax.swing.JLabel();
        jTextFieldHerbName = new javax.swing.JTextField();
        jLabelUse = new javax.swing.JLabel();
        jRadioButtonUse0 = new javax.swing.JRadioButton();
        jRadioButtonUse1 = new javax.swing.JRadioButton();
        jPanel21 = new javax.swing.JPanel();
        jButtonAddHerb = new javax.swing.JButton();
        jButtonDelHerb = new javax.swing.JButton();
        jButtonSaveHerb = new javax.swing.JButton();
        jPanelPet = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTablePet = new javax.swing.JTable();
        jPanel23 = new javax.swing.JPanel();
        jLabelPetType = new javax.swing.JLabel();
        jLabelPetName = new javax.swing.JLabel();
        jLabelPetSex = new javax.swing.JLabel();
        jLabelPetBirthDay = new javax.swing.JLabel();
        jLabelVaccineDay = new javax.swing.JLabel();
        jLabelBirthDay = new javax.swing.JLabel();
        jComboBoxPetType = new javax.swing.JComboBox();
        jTextFieldPetName = new javax.swing.JTextField();
        jComboBoxPetSex = new javax.swing.JComboBox();
        dateComboBoxBirthDay = new com.pcu.utility.DateComboBox();
        dateComboBoxPetBirthDay = new com.pcu.utility.DateComboBox();
        dateComboBoxVaccineDay = new com.pcu.utility.DateComboBox();
        jPanel24 = new javax.swing.JPanel();
        jButtonAddPet = new javax.swing.JButton();
        jButtonDelPet = new javax.swing.JButton();
        jButtonSavePet = new javax.swing.JButton();
        jPanel9Survey = new javax.swing.JPanel();
        jPanelQuertionaire = new javax.swing.JPanel();
        jTabbedPaneQuestionaire = new javax.swing.JTabbedPane();
        jPanelHomeStandard = new javax.swing.JPanel();
        jLabelHomeDurability = new javax.swing.JLabel();
        jLabelHomeCare = new javax.swing.JLabel();
        jLabelHomeLight = new javax.swing.JLabel();
        jLabelHomeCleanness = new javax.swing.JLabel();
        jLabelHomeVentilation = new javax.swing.JLabel();
        jLabelPublicHealth = new javax.swing.JLabel();
        jRadioButtonHomeDura0 = new javax.swing.JRadioButton();
        jRadioButtonHomeDura1 = new javax.swing.JRadioButton();
        jRadioButtonHomeCare0 = new javax.swing.JRadioButton();
        jRadioButtonHomeCare1 = new javax.swing.JRadioButton();
        jRadioButtonHomeLight0 = new javax.swing.JRadioButton();
        jRadioButtonHomeLight1 = new javax.swing.JRadioButton();
        jRadioButtonHomeClean0 = new javax.swing.JRadioButton();
        jRadioButtonHomeClean1 = new javax.swing.JRadioButton();
        jRadioButtonHomeVen0 = new javax.swing.JRadioButton();
        jRadioButtonHomeVen1 = new javax.swing.JRadioButton();
        jRadioButtonPublicHealth0 = new javax.swing.JRadioButton();
        jRadioButtonPublicHealth1 = new javax.swing.JRadioButton();
        jPanelFoodStandard = new javax.swing.JPanel();
        jLabelMixture = new javax.swing.JLabel();
        jLabelFoodVessel = new javax.swing.JLabel();
        jLabelFoodCover = new javax.swing.JLabel();
        jLabelWashVessel = new javax.swing.JLabel();
        jLabelKeepVessel = new javax.swing.JLabel();
        jLabelKitchenGarbage = new javax.swing.JLabel();
        jLabelKitchenClean = new javax.swing.JLabel();
        jLabelFoodStandard = new javax.swing.JLabel();
        jLabelUseIodine = new javax.swing.JLabel();
        jLabelSaltIodine = new javax.swing.JLabel();
        jLabelProductIodine = new javax.swing.JLabel();
        jRadioButtonMixFood0 = new javax.swing.JRadioButton();
        jRadioButtonMixFood1 = new javax.swing.JRadioButton();
        jRadioButtonFoodVessel0 = new javax.swing.JRadioButton();
        jRadioButtonFoodVessel1 = new javax.swing.JRadioButton();
        jRadioButtonFoodCover0 = new javax.swing.JRadioButton();
        jRadioButtonFoodCover1 = new javax.swing.JRadioButton();
        jRadioButtonWash0 = new javax.swing.JRadioButton();
        jRadioButtonWash1 = new javax.swing.JRadioButton();
        jRadioButtonKeep0 = new javax.swing.JRadioButton();
        jRadioButtonKeep1 = new javax.swing.JRadioButton();
        jRadioButtonKitchenGarbage0 = new javax.swing.JRadioButton();
        jRadioButtonKitchenGarbage1 = new javax.swing.JRadioButton();
        jRadioButtonKitchenClean0 = new javax.swing.JRadioButton();
        jRadioButtonKitchenClean1 = new javax.swing.JRadioButton();
        jRadioButtonFoodStand0 = new javax.swing.JRadioButton();
        jRadioButtonFoodStand1 = new javax.swing.JRadioButton();
        jRadioButtonUseIodine0 = new javax.swing.JRadioButton();
        jRadioButtonUseIodine1 = new javax.swing.JRadioButton();
        jRadioButtonSaltIodine0 = new javax.swing.JRadioButton();
        jRadioButtonSaltIodine1 = new javax.swing.JRadioButton();
        jRadioButtonProductIodine0 = new javax.swing.JRadioButton();
        jRadioButtonProductIodine1 = new javax.swing.JRadioButton();
        jPanelHomeWater = new javax.swing.JPanel();
        jLabelWaterDrink = new javax.swing.JLabel();
        jLabelWaterUse = new javax.swing.JLabel();
        jLabelWaterDrinkAmply = new javax.swing.JLabel();
        jLabelWaterUseAmply = new javax.swing.JLabel();
        jLabelToiletStandard = new javax.swing.JLabel();
        jLabelGarbage = new javax.swing.JLabel();
        jLabelWaterEradicate = new javax.swing.JLabel();
        jLabelGarbageMethod = new javax.swing.JLabel();
        jComboBoxWaterDrink = new javax.swing.JComboBox();
        jComboBoxWaterUse = new javax.swing.JComboBox();
        jComboBoxGarbageMethod = new javax.swing.JComboBox();
        jRadioButtonWaterDrink0 = new javax.swing.JRadioButton();
        jRadioButtonWaterDrink1 = new javax.swing.JRadioButton();
        jRadioButtonWaterUse0 = new javax.swing.JRadioButton();
        jRadioButtonWaterUse1 = new javax.swing.JRadioButton();
        jRadioButtonToilet0 = new javax.swing.JRadioButton();
        jRadioButtonToilet1 = new javax.swing.JRadioButton();
        jRadioButtonGarbage0 = new javax.swing.JRadioButton();
        jRadioButtonGarbage1 = new javax.swing.JRadioButton();
        jRadioButtonWaterEra0 = new javax.swing.JRadioButton();
        jRadioButtonWaterEra1 = new javax.swing.JRadioButton();
        jPanelBugControl = new javax.swing.JPanel();
        jLabelRatcontrol = new javax.swing.JLabel();
        jLabelCockroachControl = new javax.swing.JLabel();
        jLabelFlyComtrol = new javax.swing.JLabel();
        jLabelSummaryControl = new javax.swing.JLabel();
        jLabelPet = new javax.swing.JLabel();
        jLabelDungControl = new javax.swing.JLabel();
        jRadioButtonRat0 = new javax.swing.JRadioButton();
        jRadioButtonRat1 = new javax.swing.JRadioButton();
        jRadioButtonCockroach0 = new javax.swing.JRadioButton();
        jRadioButtonCockroach1 = new javax.swing.JRadioButton();
        jRadioButtonFly0 = new javax.swing.JRadioButton();
        jRadioButtonFly1 = new javax.swing.JRadioButton();
        jRadioButtonSummary0 = new javax.swing.JRadioButton();
        jRadioButtonSummary1 = new javax.swing.JRadioButton();
        jRadioButtonPet0 = new javax.swing.JRadioButton();
        jRadioButtonPet1 = new javax.swing.JRadioButton();
        jComboBoxDungControl = new javax.swing.JComboBox();
        jLabelAnimalControl = new javax.swing.JLabel();
        jRadioAnimalControl0 = new javax.swing.JRadioButton();
        jRadioAnimalControl1 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jButtonAddSubHome = new javax.swing.JButton();
        jButtonDelSubHome = new javax.swing.JButton();
        jButtonSaveSubHome = new javax.swing.JButton();
        jPanelSubHome = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSubHome = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButtonAddHome = new javax.swing.JButton();
        jButtonDelHome = new javax.swing.JButton();
        jButtonSaveHome = new javax.swing.JButton();
        jPanelHome = new javax.swing.JPanel();
        jPanelSearch = new javax.swing.JPanel();
        jTextFieldSearchHome = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jComboBoxVillage = new javax.swing.JComboBox();
        jButtonRefresh = new javax.swing.JButton();
        jCheckBoxByVillage = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHome = new javax.swing.JTable();

        setLayout(new java.awt.GridBagLayout());

        jPanelHealthCenterDetail.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HealthCenterDetail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jPanelHealthCenterDetail.setMinimumSize(new java.awt.Dimension(240, 56));
        jPanelHealthCenterDetail.setPreferredSize(new java.awt.Dimension(240, 56));
        jPanelHealthCenterDetail.setRequestFocusEnabled(false);
        jPanelHealthCenterDetail.setLayout(new java.awt.GridBagLayout());

        jLabelHealthCenter.setText("HealthCenterName");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 9, 0);
        jPanelHealthCenterDetail.add(jLabelHealthCenter, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 11, 0);
        jPanelHealthCenterDetail.add(jLabelHealthCenterName, gridBagConstraints);

        jLabelTambon1.setText("Tambol");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 9, 0);
        jPanelHealthCenterDetail.add(jLabelTambon1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 11, 0);
        jPanelHealthCenterDetail.add(jLabelTambon, gridBagConstraints);

        jLabelAmphur0.setText("Amphur");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 9, 0);
        jPanelHealthCenterDetail.add(jLabelAmphur0, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 11, 0);
        jPanelHealthCenterDetail.add(jLabelAmphur, gridBagConstraints);

        jLabelChangwat1.setText("Changwat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 9, 0);
        jPanelHealthCenterDetail.add(jLabelChangwat1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 11, 0);
        jPanelHealthCenterDetail.add(jLabelChangwat, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jPanelHealthCenterDetail, gridBagConstraints);

        jPanelDetail.setLayout(new java.awt.GridBagLayout());

        jTabbedPaneHomeDatail.setFont(defaultFont1);

        jPanelHomeDetail.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HomeDetail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jPanelHomeDetail.setLayout(new java.awt.GridBagLayout());

        jPanelHome2.setLayout(new java.awt.GridBagLayout());

        jLabelHomeOrder.setFont(defaultFont1);
        jLabelHomeOrder.setText("NumberOfHouse");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelHome2.add(jLabelHomeOrder, gridBagConstraints);

        integerTextField1.setBackground(new java.awt.Color(204, 255, 255));
        integerTextField1.setColumns(4);
        integerTextField1.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanelHome2.add(integerTextField1, gridBagConstraints);

        integerTextField2.setBackground(new java.awt.Color(204, 255, 255));
        integerTextField2.setColumns(6);
        integerTextField2.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        integerTextField2.setMinimumSize(new java.awt.Dimension(72, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelHome2.add(integerTextField2, gridBagConstraints);

        integerTextField3.setBackground(new java.awt.Color(204, 255, 255));
        integerTextField3.setColumns(1);
        integerTextField3.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        integerTextField3.setMinimumSize(new java.awt.Dimension(17, 24));
        integerTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                integerTextField3FocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelHome2.add(integerTextField3, gridBagConstraints);

        jLabel1.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelHome2.add(jLabel1, gridBagConstraints);

        jLabel2.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelHome2.add(jLabel2, gridBagConstraints);

        jLabelHomeNumber.setFont(defaultFont1);
        jLabelHomeNumber.setText("HomeNumber");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 0, 0);
        jPanelHome2.add(jLabelHomeNumber, gridBagConstraints);

        jLabelRoad.setFont(defaultFont1);
        jLabelRoad.setText("Moo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        jPanelHome2.add(jLabelRoad, gridBagConstraints);

        jLabelChungwat.setFont(defaultFont1);
        jLabelChungwat.setText("Changwat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelHome2.add(jLabelChungwat, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabelMoo.setFont(defaultFont1);
        jLabelMoo.setText("Road");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel6.add(jLabelMoo, gridBagConstraints);

        jTextFieldMoo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldMoo.setEnabled(false);
        jTextFieldMoo.setMinimumSize(new java.awt.Dimension(25, 21));
        jTextFieldMoo.setPreferredSize(new java.awt.Dimension(25, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel6.add(jTextFieldMoo, gridBagConstraints);

        jTextFieldRoad.setBackground(new java.awt.Color(204, 255, 255));
        jTextFieldRoad.setMinimumSize(new java.awt.Dimension(100, 24));
        jTextFieldRoad.setPreferredSize(new java.awt.Dimension(116, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel6.add(jTextFieldRoad, gridBagConstraints);

        jTextFieldHomeNumber.setBackground(new java.awt.Color(204, 255, 255));
        jTextFieldHomeNumber.setColumns(4);
        jTextFieldHomeNumber.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel6.add(jTextFieldHomeNumber, gridBagConstraints);

        jLabelVillage1.setFont(defaultFont1);
        jLabelVillage1.setText("Village:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 15, 0, 0);
        jPanel6.add(jLabelVillage1, gridBagConstraints);

        jComboBoxVillage1.setBackground(new java.awt.Color(204, 255, 255));
        jComboBoxVillage1.setOpaque(false);
        jComboBoxVillage1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxVillage1ItemStateChanged(evt);
            }
        });
        jComboBoxVillage1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxVillage1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel6.add(jComboBoxVillage1, gridBagConstraints);

        jPanelHome3.setLayout(new java.awt.GridBagLayout());

        jComboBoxAmphur.setEnabled(false);
        jComboBoxAmphur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAmphurActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelHome3.add(jComboBoxAmphur, gridBagConstraints);

        jComboBoxChungwat.setEnabled(false);
        jComboBoxChungwat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxChungwatActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelHome3.add(jComboBoxChungwat, gridBagConstraints);

        jComboBoxTambol.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelHome3.add(jComboBoxTambol, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel6.add(jPanelHome3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelHome2.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelHomeDetail.add(jPanelHome2, gridBagConstraints);

        jPanelHome5.setLayout(new java.awt.GridBagLayout());

        jLabelVolunteer.setFont(defaultFont1);
        jLabelVolunteer.setText("VolunteerNumber");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelHome5.add(jLabelVolunteer, gridBagConstraints);

        jTextFieldVolunteer.setBackground(new java.awt.Color(204, 255, 255));
        jTextFieldVolunteer.setMinimumSize(new java.awt.Dimension(70, 21));
        jTextFieldVolunteer.setPreferredSize(new java.awt.Dimension(70, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelHome5.add(jTextFieldVolunteer, gridBagConstraints);

        jLabelVolunteerName.setFont(defaultFont1);
        jLabelVolunteerName.setText("VolunteerName");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 0, 0);
        jPanelHome5.add(jLabelVolunteerName, gridBagConstraints);

        jTextFieldVolunteerName.setMinimumSize(new java.awt.Dimension(100, 24));
        jTextFieldVolunteerName.setPreferredSize(new java.awt.Dimension(100, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelHome5.add(jTextFieldVolunteerName, gridBagConstraints);

        integerTextFieldQuantity.setBackground(new java.awt.Color(204, 255, 255));
        integerTextFieldQuantity.setColumns(2);
        integerTextFieldQuantity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldQuantity.setMinimumSize(new java.awt.Dimension(100, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelHome5.add(integerTextFieldQuantity, gridBagConstraints);

        jLabelQuantity.setFont(defaultFont1);
        jLabelQuantity.setText("QuantityFamily");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelHome5.add(jLabelQuantity, gridBagConstraints);

        jLabelTelephoneOwner.setFont(defaultFont1);
        jLabelTelephoneOwner.setText("ชื่อเจ้าบ้าน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 0, 0);
        jPanelHome5.add(jLabelTelephoneOwner, gridBagConstraints);

        jTextFieldOwnerName.setEditable(false);
        jTextFieldOwnerName.setMinimumSize(new java.awt.Dimension(70, 21));
        jTextFieldOwnerName.setPreferredSize(new java.awt.Dimension(70, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelHome5.add(jTextFieldOwnerName, gridBagConstraints);

        jLabelOwnerName.setFont(defaultFont1);
        jLabelOwnerName.setText("OwnerNumber");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelHome5.add(jLabelOwnerName, gridBagConstraints);

        jTextFieldOwnerNumber.setBackground(new java.awt.Color(204, 255, 255));
        jTextFieldOwnerNumber.setEditable(false);
        jTextFieldOwnerNumber.setToolTipText("กรุณาเลือกเจ้าบ้านในแถบครอบครัว");
        jTextFieldOwnerNumber.setMinimumSize(new java.awt.Dimension(70, 21));
        jTextFieldOwnerNumber.setPreferredSize(new java.awt.Dimension(70, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelHome5.add(jTextFieldOwnerNumber, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelHomeDetail.add(jPanelHome5, gridBagConstraints);

        jPanelHome6.setLayout(new java.awt.GridBagLayout());

        jLabelHomeCharacter.setFont(defaultFont1);
        jLabelHomeCharacter.setText("HomeCharacter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        jPanelHome6.add(jLabelHomeCharacter, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanelHome6.add(jComboBoxHomeCharacter, gridBagConstraints);

        jLabelCommunity.setFont(defaultFont1);
        jLabelCommunity.setText("CommunityCharacter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelHome6.add(jLabelCommunity, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHome6.add(jComboBoxCommunity, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelHomeDetail.add(jPanelHome6, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabelResponsibleZone.setFont(defaultFont1);
        jLabelResponsibleZone.setText("ResponsibleZone");
        jPanel3.add(jLabelResponsibleZone, new java.awt.GridBagConstraints());

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jRadioButtonInZone.setBackground(new java.awt.Color(204, 255, 255));
        ResponsibleZone.add(jRadioButtonInZone);
        jRadioButtonInZone.setFont(defaultFont1);
        jRadioButtonInZone.setSelected(true);
        jRadioButtonInZone.setText("InZone");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(jRadioButtonInZone, gridBagConstraints);

        jRadioButtonOutZone.setBackground(new java.awt.Color(204, 255, 255));
        ResponsibleZone.add(jRadioButtonOutZone);
        jRadioButtonOutZone.setFont(defaultFont1);
        jRadioButtonOutZone.setText("OutZone");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(jRadioButtonOutZone, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        jPanel3.add(jPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeDetail.add(jPanel3, gridBagConstraints);

        jTabbedPaneHomeDatail.addTab("HomeDetail", jPanelHomeDetail);

        jPanelFamily.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Family", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jPanelFamily.setLayout(new java.awt.GridBagLayout());

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jScrollPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jScrollPane3MouseReleased(evt);
            }
        });

        jTableFamily.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableFamily.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableFamilyKeyReleased(evt);
            }
        });
        jTableFamily.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableFamilyMouseReleased(evt);
            }
        });
        jTableFamily.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableFamilyPropertyChange(evt);
            }
        });
        jScrollPane3.setViewportView(jTableFamily);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel7.add(jScrollPane3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanelFamily.add(jPanel7, gridBagConstraints);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jButtonCut.setFont(defaultFont1);
        jButtonCut.setText("ย้ายออก");
        jButtonCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCutActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel9.add(jButtonCut, gridBagConstraints);

        jButtonPaste.setFont(defaultFont1);
        jButtonPaste.setText("ย้ายเข้า");
        jButtonPaste.setEnabled(false);
        jButtonPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPasteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel9.add(jButtonPaste, gridBagConstraints);

        jButtonMoveHome0.setFont(defaultFont1);
        jButtonMoveHome0.setText("เข้าบ้าน 0");
        jButtonMoveHome0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMoveHome0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel9.add(jButtonMoveHome0, gridBagConstraints);

        jPanelManageFamily.setLayout(new java.awt.GridBagLayout());

        jButtonDelPatient.setFont(defaultFont1);
        jButtonDelPatient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelPatient.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDelPatient.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDelPatient.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonDelPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelPatientActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanelManageFamily.add(jButtonDelPatient, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel9.add(jPanelManageFamily, gridBagConstraints);

        jButtonUpdatePatient.setFont(defaultFont1);
        jButtonUpdatePatient.setText("Update");
        jButtonUpdatePatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdatePatientActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel9.add(jButtonUpdatePatient, gridBagConstraints);

        jButtonStatus.setFont(defaultFont1);
        jButtonStatus.setText("StatusPerson");
        jButtonStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStatusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel9.add(jButtonStatus, gridBagConstraints);

        jButtonAddPatient.setFont(defaultFont1);
        jButtonAddPatient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAddPatient.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonAddPatient.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonAddPatient.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonAddPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddPatientActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel9.add(jButtonAddPatient, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        jPanelFamily.add(jPanel9, gridBagConstraints);

        jTabbedPaneHomeDatail.addTab("Family", jPanelFamily);

        jPanelHerb.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Herb", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jPanelHerb.setLayout(new java.awt.GridBagLayout());

        jPanel19.setLayout(new java.awt.GridBagLayout());

        jTableHerb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableHerb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableHerbKeyReleased(evt);
            }
        });
        jTableHerb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableHerbMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jTableHerb);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel19.add(jScrollPane4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanelHerb.add(jPanel19, gridBagConstraints);

        jPanel20.setLayout(new java.awt.GridBagLayout());

        jLabelHerbName.setFont(defaultFont1);
        jLabelHerbName.setText("HerbName");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel20.add(jLabelHerbName, gridBagConstraints);

        jTextFieldHerbName.setMinimumSize(new java.awt.Dimension(200, 21));
        jTextFieldHerbName.setPreferredSize(new java.awt.Dimension(200, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanel20.add(jTextFieldHerbName, gridBagConstraints);

        jLabelUse.setFont(defaultFont1);
        jLabelUse.setText("UseHerb");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel20.add(jLabelUse, gridBagConstraints);

        UseGroup.add(jRadioButtonUse0);
        jRadioButtonUse0.setFont(defaultFont1);
        jRadioButtonUse0.setText("NotUse");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanel20.add(jRadioButtonUse0, gridBagConstraints);

        UseGroup.add(jRadioButtonUse1);
        jRadioButtonUse1.setFont(defaultFont1);
        jRadioButtonUse1.setSelected(true);
        jRadioButtonUse1.setText("Use");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel20.add(jRadioButtonUse1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanelHerb.add(jPanel20, gridBagConstraints);

        jPanel21.setLayout(new java.awt.GridBagLayout());

        jButtonAddHerb.setFont(defaultFont1);
        jButtonAddHerb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAddHerb.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonAddHerb.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonAddHerb.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonAddHerb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddHerbActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel21.add(jButtonAddHerb, gridBagConstraints);

        jButtonDelHerb.setFont(defaultFont1);
        jButtonDelHerb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelHerb.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDelHerb.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDelHerb.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonDelHerb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelHerbActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel21.add(jButtonDelHerb, gridBagConstraints);

        jButtonSaveHerb.setFont(defaultFont1);
        jButtonSaveHerb.setText("Save");
        jButtonSaveHerb.setMaximumSize(new java.awt.Dimension(67, 24));
        jButtonSaveHerb.setMinimumSize(new java.awt.Dimension(67, 24));
        jButtonSaveHerb.setPreferredSize(new java.awt.Dimension(67, 24));
        jButtonSaveHerb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveHerbActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        jPanel21.add(jButtonSaveHerb, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 5, 5, 5);
        jPanelHerb.add(jPanel21, gridBagConstraints);

        jTabbedPaneHomeDatail.addTab("Herb", jPanelHerb);

        jPanelPet.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PetControl", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jPanelPet.setLayout(new java.awt.GridBagLayout());

        jPanel22.setLayout(new java.awt.GridBagLayout());

        jTablePet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTablePet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTablePetKeyReleased(evt);
            }
        });
        jTablePet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTablePetMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(jTablePet);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel22.add(jScrollPane5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanelPet.add(jPanel22, gridBagConstraints);

        jPanel23.setLayout(new java.awt.GridBagLayout());

        jLabelPetType.setFont(defaultFont1);
        jLabelPetType.setText("PetType");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel23.add(jLabelPetType, gridBagConstraints);

        jLabelPetName.setFont(defaultFont1);
        jLabelPetName.setText("PetName");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel23.add(jLabelPetName, gridBagConstraints);

        jLabelPetSex.setFont(defaultFont1);
        jLabelPetSex.setText("PetSex");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel23.add(jLabelPetSex, gridBagConstraints);

        jLabelPetBirthDay.setFont(defaultFont1);
        jLabelPetBirthDay.setText("PetBirthDay");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel23.add(jLabelPetBirthDay, gridBagConstraints);

        jLabelVaccineDay.setFont(defaultFont1);
        jLabelVaccineDay.setText("VaccineLastdate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel23.add(jLabelVaccineDay, gridBagConstraints);

        jLabelBirthDay.setFont(defaultFont1);
        jLabelBirthDay.setText("BirthControlLastDay");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel23.add(jLabelBirthDay, gridBagConstraints);

        jComboBoxPetType.setMinimumSize(new java.awt.Dimension(100, 24));
        jComboBoxPetType.setPreferredSize(new java.awt.Dimension(100, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanel23.add(jComboBoxPetType, gridBagConstraints);

        jTextFieldPetName.setMinimumSize(new java.awt.Dimension(150, 21));
        jTextFieldPetName.setPreferredSize(new java.awt.Dimension(150, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanel23.add(jTextFieldPetName, gridBagConstraints);

        jComboBoxPetSex.setMinimumSize(new java.awt.Dimension(100, 24));
        jComboBoxPetSex.setPreferredSize(new java.awt.Dimension(100, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 5);
        jPanel23.add(jComboBoxPetSex, gridBagConstraints);

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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanel23.add(dateComboBoxBirthDay, gridBagConstraints);

        dateComboBoxPetBirthDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxPetBirthDayActionPerformed(evt);
            }
        });
        dateComboBoxPetBirthDay.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateComboBoxPetBirthDayFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanel23.add(dateComboBoxPetBirthDay, gridBagConstraints);

        dateComboBoxVaccineDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxVaccineDayActionPerformed(evt);
            }
        });
        dateComboBoxVaccineDay.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateComboBoxVaccineDayFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanel23.add(dateComboBoxVaccineDay, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanelPet.add(jPanel23, gridBagConstraints);

        jPanel24.setLayout(new java.awt.GridBagLayout());

        jButtonAddPet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAddPet.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonAddPet.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonAddPet.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonAddPet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddPetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel24.add(jButtonAddPet, gridBagConstraints);

        jButtonDelPet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelPet.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDelPet.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDelPet.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonDelPet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelPetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel24.add(jButtonDelPet, gridBagConstraints);

        jButtonSavePet.setFont(defaultFont1);
        jButtonSavePet.setText("Save");
        jButtonSavePet.setMaximumSize(new java.awt.Dimension(67, 24));
        jButtonSavePet.setMinimumSize(new java.awt.Dimension(73, 24));
        jButtonSavePet.setPreferredSize(new java.awt.Dimension(67, 24));
        jButtonSavePet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSavePetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        jPanel24.add(jButtonSavePet, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 5, 5, 5);
        jPanelPet.add(jPanel24, gridBagConstraints);

        jTabbedPaneHomeDatail.addTab("PetControl", jPanelPet);

        jPanel9Survey.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel9Survey.setLayout(new java.awt.GridBagLayout());

        jPanelQuertionaire.setLayout(new java.awt.GridBagLayout());

        jTabbedPaneQuestionaire.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        jTabbedPaneQuestionaire.setFont(defaultFont1);

        jPanelHomeStandard.setBorder(null);
        jPanelHomeStandard.setLayout(new java.awt.GridBagLayout());

        jLabelHomeDurability.setFont(defaultFont1);
        jLabelHomeDurability.setText("HomeDurability");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 0, 0);
        jPanelHomeStandard.add(jLabelHomeDurability, gridBagConstraints);

        jLabelHomeCare.setFont(defaultFont1);
        jLabelHomeCare.setText("HomeCare");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeStandard.add(jLabelHomeCare, gridBagConstraints);

        jLabelHomeLight.setFont(defaultFont1);
        jLabelHomeLight.setText("HomeLight");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeStandard.add(jLabelHomeLight, gridBagConstraints);

        jLabelHomeCleanness.setFont(defaultFont1);
        jLabelHomeCleanness.setText("HomeCleanness");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeStandard.add(jLabelHomeCleanness, gridBagConstraints);

        jLabelHomeVentilation.setFont(defaultFont1);
        jLabelHomeVentilation.setText("HomeVentilation");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 11, 0, 0);
        jPanelHomeStandard.add(jLabelHomeVentilation, gridBagConstraints);

        jLabelPublicHealth.setFont(defaultFont1);
        jLabelPublicHealth.setText("PublicHealth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 11, 0, 0);
        jPanelHomeStandard.add(jLabelPublicHealth, gridBagConstraints);

        jRadioButtonHomeDura0.setBackground(new java.awt.Color(204, 255, 255));
        HomeDurabilityGroup.add(jRadioButtonHomeDura0);
        jRadioButtonHomeDura0.setFont(defaultFont1);
        jRadioButtonHomeDura0.setText("NotDurability");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 0, 0);
        jPanelHomeStandard.add(jRadioButtonHomeDura0, gridBagConstraints);

        jRadioButtonHomeDura1.setBackground(new java.awt.Color(204, 255, 255));
        HomeDurabilityGroup.add(jRadioButtonHomeDura1);
        jRadioButtonHomeDura1.setFont(defaultFont1);
        jRadioButtonHomeDura1.setSelected(true);
        jRadioButtonHomeDura1.setText("Durability");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 5, 0, 0);
        jPanelHomeStandard.add(jRadioButtonHomeDura1, gridBagConstraints);

        HomeCareGroup.add(jRadioButtonHomeCare0);
        jRadioButtonHomeCare0.setFont(defaultFont1);
        jRadioButtonHomeCare0.setText("NotHave");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeStandard.add(jRadioButtonHomeCare0, gridBagConstraints);

        HomeCareGroup.add(jRadioButtonHomeCare1);
        jRadioButtonHomeCare1.setFont(defaultFont1);
        jRadioButtonHomeCare1.setSelected(true);
        jRadioButtonHomeCare1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelHomeStandard.add(jRadioButtonHomeCare1, gridBagConstraints);

        jRadioButtonHomeLight0.setBackground(new java.awt.Color(204, 255, 255));
        HomeLightGroup.add(jRadioButtonHomeLight0);
        jRadioButtonHomeLight0.setFont(defaultFont1);
        jRadioButtonHomeLight0.setText("NotHave");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeStandard.add(jRadioButtonHomeLight0, gridBagConstraints);

        jRadioButtonHomeLight1.setBackground(new java.awt.Color(204, 255, 255));
        HomeLightGroup.add(jRadioButtonHomeLight1);
        jRadioButtonHomeLight1.setFont(defaultFont1);
        jRadioButtonHomeLight1.setSelected(true);
        jRadioButtonHomeLight1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelHomeStandard.add(jRadioButtonHomeLight1, gridBagConstraints);

        jRadioButtonHomeClean0.setBackground(new java.awt.Color(204, 255, 255));
        HomeCleannessGroup.add(jRadioButtonHomeClean0);
        jRadioButtonHomeClean0.setFont(defaultFont1);
        jRadioButtonHomeClean0.setText("NotHave");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeStandard.add(jRadioButtonHomeClean0, gridBagConstraints);

        jRadioButtonHomeClean1.setBackground(new java.awt.Color(204, 255, 255));
        HomeCleannessGroup.add(jRadioButtonHomeClean1);
        jRadioButtonHomeClean1.setFont(defaultFont1);
        jRadioButtonHomeClean1.setSelected(true);
        jRadioButtonHomeClean1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelHomeStandard.add(jRadioButtonHomeClean1, gridBagConstraints);

        jRadioButtonHomeVen0.setBackground(new java.awt.Color(204, 255, 255));
        HomeVentilationGroup.add(jRadioButtonHomeVen0);
        jRadioButtonHomeVen0.setFont(defaultFont1);
        jRadioButtonHomeVen0.setText("NotHave");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeStandard.add(jRadioButtonHomeVen0, gridBagConstraints);

        jRadioButtonHomeVen1.setBackground(new java.awt.Color(204, 255, 255));
        HomeVentilationGroup.add(jRadioButtonHomeVen1);
        jRadioButtonHomeVen1.setFont(defaultFont1);
        jRadioButtonHomeVen1.setSelected(true);
        jRadioButtonHomeVen1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelHomeStandard.add(jRadioButtonHomeVen1, gridBagConstraints);

        PublicHealthGroup.add(jRadioButtonPublicHealth0);
        jRadioButtonPublicHealth0.setFont(defaultFont1);
        jRadioButtonPublicHealth0.setText("NotHave");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeStandard.add(jRadioButtonPublicHealth0, gridBagConstraints);

        PublicHealthGroup.add(jRadioButtonPublicHealth1);
        jRadioButtonPublicHealth1.setFont(defaultFont1);
        jRadioButtonPublicHealth1.setSelected(true);
        jRadioButtonPublicHealth1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelHomeStandard.add(jRadioButtonPublicHealth1, gridBagConstraints);

        jTabbedPaneQuestionaire.addTab("ที่พักอาศัย", jPanelHomeStandard);

        jPanelFoodStandard.setLayout(new java.awt.GridBagLayout());

        jLabelMixture.setFont(defaultFont1);
        jLabelMixture.setText("MixtureFood");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelFoodStandard.add(jLabelMixture, gridBagConstraints);

        jLabelFoodVessel.setFont(defaultFont1);
        jLabelFoodVessel.setText("FoodVessel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jLabelFoodVessel, gridBagConstraints);

        jLabelFoodCover.setFont(defaultFont1);
        jLabelFoodCover.setText("FoodCover");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jLabelFoodCover, gridBagConstraints);

        jLabelWashVessel.setFont(defaultFont1);
        jLabelWashVessel.setText("WashVessel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jLabelWashVessel, gridBagConstraints);

        jLabelKeepVessel.setFont(defaultFont1);
        jLabelKeepVessel.setText("KeepVessel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jLabelKeepVessel, gridBagConstraints);

        jLabelKitchenGarbage.setFont(defaultFont1);
        jLabelKitchenGarbage.setText("KitchenGarbage");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jLabelKitchenGarbage, gridBagConstraints);

        jLabelKitchenClean.setFont(defaultFont1);
        jLabelKitchenClean.setText("KitchenCleanness");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jLabelKitchenClean, gridBagConstraints);

        jLabelFoodStandard.setFont(defaultFont1);
        jLabelFoodStandard.setText("FoodStandards");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jLabelFoodStandard, gridBagConstraints);

        jLabelUseIodine.setFont(defaultFont1);
        jLabelUseIodine.setText("UseIodine");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jLabelUseIodine, gridBagConstraints);

        jLabelSaltIodine.setFont(defaultFont1);
        jLabelSaltIodine.setText("SaltIodine");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jLabelSaltIodine, gridBagConstraints);

        jLabelProductIodine.setFont(defaultFont1);
        jLabelProductIodine.setText("ProductIodine");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jLabelProductIodine, gridBagConstraints);

        jRadioButtonMixFood0.setBackground(new java.awt.Color(204, 255, 255));
        MixtureFoodGroup.add(jRadioButtonMixFood0);
        jRadioButtonMixFood0.setFont(defaultFont1);
        jRadioButtonMixFood0.setSelected(true);
        jRadioButtonMixFood0.setText("NotUse");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelFoodStandard.add(jRadioButtonMixFood0, gridBagConstraints);

        jRadioButtonMixFood1.setBackground(new java.awt.Color(204, 255, 255));
        MixtureFoodGroup.add(jRadioButtonMixFood1);
        jRadioButtonMixFood1.setFont(defaultFont1);
        jRadioButtonMixFood1.setText("Use");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelFoodStandard.add(jRadioButtonMixFood1, gridBagConstraints);

        FoodVesselGroup.add(jRadioButtonFoodVessel0);
        jRadioButtonFoodVessel0.setFont(defaultFont1);
        jRadioButtonFoodVessel0.setText("NotStandard");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jRadioButtonFoodVessel0, gridBagConstraints);

        FoodVesselGroup.add(jRadioButtonFoodVessel1);
        jRadioButtonFoodVessel1.setFont(defaultFont1);
        jRadioButtonFoodVessel1.setSelected(true);
        jRadioButtonFoodVessel1.setText("Standard");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelFoodStandard.add(jRadioButtonFoodVessel1, gridBagConstraints);

        FoodCoverGroup.add(jRadioButtonFoodCover0);
        jRadioButtonFoodCover0.setFont(defaultFont1);
        jRadioButtonFoodCover0.setText("NotHave");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jRadioButtonFoodCover0, gridBagConstraints);

        FoodCoverGroup.add(jRadioButtonFoodCover1);
        jRadioButtonFoodCover1.setFont(defaultFont1);
        jRadioButtonFoodCover1.setSelected(true);
        jRadioButtonFoodCover1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelFoodStandard.add(jRadioButtonFoodCover1, gridBagConstraints);

        WashVesselGroup.add(jRadioButtonWash0);
        jRadioButtonWash0.setFont(defaultFont1);
        jRadioButtonWash0.setText("NotStandard");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jRadioButtonWash0, gridBagConstraints);

        WashVesselGroup.add(jRadioButtonWash1);
        jRadioButtonWash1.setFont(defaultFont1);
        jRadioButtonWash1.setSelected(true);
        jRadioButtonWash1.setText("Standard");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelFoodStandard.add(jRadioButtonWash1, gridBagConstraints);

        KeepVesselGroup.add(jRadioButtonKeep0);
        jRadioButtonKeep0.setFont(defaultFont1);
        jRadioButtonKeep0.setText("NotHave");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jRadioButtonKeep0, gridBagConstraints);

        KeepVesselGroup.add(jRadioButtonKeep1);
        jRadioButtonKeep1.setFont(defaultFont1);
        jRadioButtonKeep1.setSelected(true);
        jRadioButtonKeep1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelFoodStandard.add(jRadioButtonKeep1, gridBagConstraints);

        KitchenGarbageGroup.add(jRadioButtonKitchenGarbage0);
        jRadioButtonKitchenGarbage0.setFont(defaultFont1);
        jRadioButtonKitchenGarbage0.setText("NotHave");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jRadioButtonKitchenGarbage0, gridBagConstraints);

        KitchenGarbageGroup.add(jRadioButtonKitchenGarbage1);
        jRadioButtonKitchenGarbage1.setFont(defaultFont1);
        jRadioButtonKitchenGarbage1.setSelected(true);
        jRadioButtonKitchenGarbage1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelFoodStandard.add(jRadioButtonKitchenGarbage1, gridBagConstraints);

        KitchenCleannessGroup.add(jRadioButtonKitchenClean0);
        jRadioButtonKitchenClean0.setFont(defaultFont1);
        jRadioButtonKitchenClean0.setText("NotClean");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jRadioButtonKitchenClean0, gridBagConstraints);

        KitchenCleannessGroup.add(jRadioButtonKitchenClean1);
        jRadioButtonKitchenClean1.setFont(defaultFont1);
        jRadioButtonKitchenClean1.setSelected(true);
        jRadioButtonKitchenClean1.setText("Clean");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelFoodStandard.add(jRadioButtonKitchenClean1, gridBagConstraints);

        FoodStandardGroup.add(jRadioButtonFoodStand0);
        jRadioButtonFoodStand0.setFont(defaultFont1);
        jRadioButtonFoodStand0.setText("NotStandard");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jRadioButtonFoodStand0, gridBagConstraints);

        FoodStandardGroup.add(jRadioButtonFoodStand1);
        jRadioButtonFoodStand1.setFont(defaultFont1);
        jRadioButtonFoodStand1.setSelected(true);
        jRadioButtonFoodStand1.setText("Standard");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelFoodStandard.add(jRadioButtonFoodStand1, gridBagConstraints);

        UseIodineGroup.add(jRadioButtonUseIodine0);
        jRadioButtonUseIodine0.setFont(defaultFont1);
        jRadioButtonUseIodine0.setText("NotUse");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jRadioButtonUseIodine0, gridBagConstraints);

        UseIodineGroup.add(jRadioButtonUseIodine1);
        jRadioButtonUseIodine1.setFont(defaultFont1);
        jRadioButtonUseIodine1.setSelected(true);
        jRadioButtonUseIodine1.setText("Use");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelFoodStandard.add(jRadioButtonUseIodine1, gridBagConstraints);

        SaltIodineGroup.add(jRadioButtonSaltIodine0);
        jRadioButtonSaltIodine0.setFont(defaultFont1);
        jRadioButtonSaltIodine0.setText("NotUse");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jRadioButtonSaltIodine0, gridBagConstraints);

        SaltIodineGroup.add(jRadioButtonSaltIodine1);
        jRadioButtonSaltIodine1.setFont(defaultFont1);
        jRadioButtonSaltIodine1.setSelected(true);
        jRadioButtonSaltIodine1.setText("Use");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelFoodStandard.add(jRadioButtonSaltIodine1, gridBagConstraints);

        ProductIodineGroup.add(jRadioButtonProductIodine0);
        jRadioButtonProductIodine0.setFont(defaultFont1);
        jRadioButtonProductIodine0.setText("NotHave");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelFoodStandard.add(jRadioButtonProductIodine0, gridBagConstraints);

        ProductIodineGroup.add(jRadioButtonProductIodine1);
        jRadioButtonProductIodine1.setFont(defaultFont1);
        jRadioButtonProductIodine1.setSelected(true);
        jRadioButtonProductIodine1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelFoodStandard.add(jRadioButtonProductIodine1, gridBagConstraints);

        jTabbedPaneQuestionaire.addTab("อาหาร", jPanelFoodStandard);

        jPanelHomeWater.setLayout(new java.awt.GridBagLayout());

        jLabelWaterDrink.setFont(defaultFont1);
        jLabelWaterDrink.setText("WaterDrink");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 0, 0);
        jPanelHomeWater.add(jLabelWaterDrink, gridBagConstraints);

        jLabelWaterUse.setFont(defaultFont1);
        jLabelWaterUse.setText("WaterUse");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeWater.add(jLabelWaterUse, gridBagConstraints);

        jLabelWaterDrinkAmply.setFont(defaultFont1);
        jLabelWaterDrinkAmply.setText("WaterDrinkAmply");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeWater.add(jLabelWaterDrinkAmply, gridBagConstraints);

        jLabelWaterUseAmply.setFont(defaultFont1);
        jLabelWaterUseAmply.setText("WaterUseAmply");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeWater.add(jLabelWaterUseAmply, gridBagConstraints);

        jLabelToiletStandard.setFont(defaultFont1);
        jLabelToiletStandard.setText("ToiletStandard");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeWater.add(jLabelToiletStandard, gridBagConstraints);

        jLabelGarbage.setFont(defaultFont1);
        jLabelGarbage.setText("Garbage");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeWater.add(jLabelGarbage, gridBagConstraints);

        jLabelWaterEradicate.setFont(defaultFont1);
        jLabelWaterEradicate.setText("กำจัดน้ำเสีย");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeWater.add(jLabelWaterEradicate, gridBagConstraints);

        jLabelGarbageMethod.setFont(defaultFont1);
        jLabelGarbageMethod.setText("GarbageMethod");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeWater.add(jLabelGarbageMethod, gridBagConstraints);

        jComboBoxWaterDrink.setBackground(new java.awt.Color(204, 255, 255));
        jComboBoxWaterDrink.setMinimumSize(new java.awt.Dimension(130, 24));
        jComboBoxWaterDrink.setPreferredSize(new java.awt.Dimension(130, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 0, 0);
        jPanelHomeWater.add(jComboBoxWaterDrink, gridBagConstraints);

        jComboBoxWaterUse.setMinimumSize(new java.awt.Dimension(130, 24));
        jComboBoxWaterUse.setPreferredSize(new java.awt.Dimension(130, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeWater.add(jComboBoxWaterUse, gridBagConstraints);

        jComboBoxGarbageMethod.setBackground(new java.awt.Color(204, 255, 255));
        jComboBoxGarbageMethod.setMinimumSize(new java.awt.Dimension(130, 24));
        jComboBoxGarbageMethod.setPreferredSize(new java.awt.Dimension(130, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeWater.add(jComboBoxGarbageMethod, gridBagConstraints);

        jRadioButtonWaterDrink0.setBackground(new java.awt.Color(204, 255, 255));
        WaterDrinkAmplyGroup.add(jRadioButtonWaterDrink0);
        jRadioButtonWaterDrink0.setFont(defaultFont1);
        jRadioButtonWaterDrink0.setText("NotEnough");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeWater.add(jRadioButtonWaterDrink0, gridBagConstraints);

        jRadioButtonWaterDrink1.setBackground(new java.awt.Color(204, 255, 255));
        WaterDrinkAmplyGroup.add(jRadioButtonWaterDrink1);
        jRadioButtonWaterDrink1.setFont(defaultFont1);
        jRadioButtonWaterDrink1.setSelected(true);
        jRadioButtonWaterDrink1.setText("Enough");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelHomeWater.add(jRadioButtonWaterDrink1, gridBagConstraints);

        WaterUseAmplyGroup.add(jRadioButtonWaterUse0);
        jRadioButtonWaterUse0.setFont(defaultFont1);
        jRadioButtonWaterUse0.setText("NotEnough");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeWater.add(jRadioButtonWaterUse0, gridBagConstraints);

        WaterUseAmplyGroup.add(jRadioButtonWaterUse1);
        jRadioButtonWaterUse1.setFont(defaultFont1);
        jRadioButtonWaterUse1.setSelected(true);
        jRadioButtonWaterUse1.setText("Enough");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelHomeWater.add(jRadioButtonWaterUse1, gridBagConstraints);

        jRadioButtonToilet0.setBackground(new java.awt.Color(204, 255, 255));
        ToiletStandardGroup.add(jRadioButtonToilet0);
        jRadioButtonToilet0.setFont(defaultFont1);
        jRadioButtonToilet0.setText("NotStandard");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeWater.add(jRadioButtonToilet0, gridBagConstraints);

        jRadioButtonToilet1.setBackground(new java.awt.Color(204, 255, 255));
        ToiletStandardGroup.add(jRadioButtonToilet1);
        jRadioButtonToilet1.setFont(defaultFont1);
        jRadioButtonToilet1.setSelected(true);
        jRadioButtonToilet1.setText("Standard");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelHomeWater.add(jRadioButtonToilet1, gridBagConstraints);

        GarbageGroup.add(jRadioButtonGarbage0);
        jRadioButtonGarbage0.setFont(defaultFont1);
        jRadioButtonGarbage0.setText("NotHave");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeWater.add(jRadioButtonGarbage0, gridBagConstraints);

        GarbageGroup.add(jRadioButtonGarbage1);
        jRadioButtonGarbage1.setFont(defaultFont1);
        jRadioButtonGarbage1.setSelected(true);
        jRadioButtonGarbage1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelHomeWater.add(jRadioButtonGarbage1, gridBagConstraints);

        jRadioButtonWaterEra0.setBackground(new java.awt.Color(204, 255, 255));
        WaterEradicateGroup.add(jRadioButtonWaterEra0);
        jRadioButtonWaterEra0.setFont(defaultFont1);
        jRadioButtonWaterEra0.setText("ไม่กำจัด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelHomeWater.add(jRadioButtonWaterEra0, gridBagConstraints);

        jRadioButtonWaterEra1.setBackground(new java.awt.Color(204, 255, 255));
        WaterEradicateGroup.add(jRadioButtonWaterEra1);
        jRadioButtonWaterEra1.setFont(defaultFont1);
        jRadioButtonWaterEra1.setSelected(true);
        jRadioButtonWaterEra1.setText("กำจัด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelHomeWater.add(jRadioButtonWaterEra1, gridBagConstraints);

        jTabbedPaneQuestionaire.addTab("ความสะอาด", jPanelHomeWater);

        jPanelBugControl.setLayout(new java.awt.GridBagLayout());

        jLabelRatcontrol.setFont(defaultFont1);
        jLabelRatcontrol.setText("RatControl");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanelBugControl.add(jLabelRatcontrol, gridBagConstraints);

        jLabelCockroachControl.setFont(defaultFont1);
        jLabelCockroachControl.setText("CockroachControl");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanelBugControl.add(jLabelCockroachControl, gridBagConstraints);

        jLabelFlyComtrol.setFont(defaultFont1);
        jLabelFlyComtrol.setText("FlyControl");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelBugControl.add(jLabelFlyComtrol, gridBagConstraints);

        jLabelSummaryControl.setFont(defaultFont1);
        jLabelSummaryControl.setText("ควบคุมแมลงนำโรค");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelBugControl.add(jLabelSummaryControl, gridBagConstraints);

        jLabelPet.setFont(defaultFont1);
        jLabelPet.setText("Pet");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanelBugControl.add(jLabelPet, gridBagConstraints);

        jLabelDungControl.setFont(defaultFont1);
        jLabelDungControl.setText("DungControl");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 0, 0);
        jPanelBugControl.add(jLabelDungControl, gridBagConstraints);

        RatControlGroup.add(jRadioButtonRat0);
        jRadioButtonRat0.setFont(defaultFont1);
        jRadioButtonRat0.setText("NotHave");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        jPanelBugControl.add(jRadioButtonRat0, gridBagConstraints);

        RatControlGroup.add(jRadioButtonRat1);
        jRadioButtonRat1.setFont(defaultFont1);
        jRadioButtonRat1.setSelected(true);
        jRadioButtonRat1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 0);
        jPanelBugControl.add(jRadioButtonRat1, gridBagConstraints);

        CockroachControlGroup.add(jRadioButtonCockroach0);
        jRadioButtonCockroach0.setFont(defaultFont1);
        jRadioButtonCockroach0.setText("NotHave");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        jPanelBugControl.add(jRadioButtonCockroach0, gridBagConstraints);

        CockroachControlGroup.add(jRadioButtonCockroach1);
        jRadioButtonCockroach1.setFont(defaultFont1);
        jRadioButtonCockroach1.setSelected(true);
        jRadioButtonCockroach1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 0);
        jPanelBugControl.add(jRadioButtonCockroach1, gridBagConstraints);

        FlyControlGroup.add(jRadioButtonFly0);
        jRadioButtonFly0.setFont(defaultFont1);
        jRadioButtonFly0.setText("NotHave");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 0, 0);
        jPanelBugControl.add(jRadioButtonFly0, gridBagConstraints);

        FlyControlGroup.add(jRadioButtonFly1);
        jRadioButtonFly1.setFont(defaultFont1);
        jRadioButtonFly1.setSelected(true);
        jRadioButtonFly1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelBugControl.add(jRadioButtonFly1, gridBagConstraints);

        jRadioButtonSummary0.setBackground(new java.awt.Color(204, 255, 255));
        SummaryControlGroup.add(jRadioButtonSummary0);
        jRadioButtonSummary0.setFont(defaultFont1);
        jRadioButtonSummary0.setText("ไม่ควบคุม");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 0, 0);
        jPanelBugControl.add(jRadioButtonSummary0, gridBagConstraints);

        jRadioButtonSummary1.setBackground(new java.awt.Color(204, 255, 255));
        SummaryControlGroup.add(jRadioButtonSummary1);
        jRadioButtonSummary1.setFont(defaultFont1);
        jRadioButtonSummary1.setSelected(true);
        jRadioButtonSummary1.setText("ควบคุม");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelBugControl.add(jRadioButtonSummary1, gridBagConstraints);

        PetGroup.add(jRadioButtonPet0);
        jRadioButtonPet0.setFont(defaultFont1);
        jRadioButtonPet0.setSelected(true);
        jRadioButtonPet0.setText("NotFeed");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        jPanelBugControl.add(jRadioButtonPet0, gridBagConstraints);

        PetGroup.add(jRadioButtonPet1);
        jRadioButtonPet1.setFont(defaultFont1);
        jRadioButtonPet1.setText("Feed");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 0);
        jPanelBugControl.add(jRadioButtonPet1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 0, 0);
        jPanelBugControl.add(jComboBoxDungControl, gridBagConstraints);

        jLabelAnimalControl.setFont(defaultFont1);
        jLabelAnimalControl.setText("AnimalControl");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelBugControl.add(jLabelAnimalControl, gridBagConstraints);

        jRadioAnimalControl0.setBackground(new java.awt.Color(204, 255, 255));
        AnimalControlGroup.add(jRadioAnimalControl0);
        jRadioAnimalControl0.setFont(defaultFont1);
        jRadioAnimalControl0.setText("NotControl");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 0, 0);
        jPanelBugControl.add(jRadioAnimalControl0, gridBagConstraints);

        jRadioAnimalControl1.setBackground(new java.awt.Color(204, 255, 255));
        AnimalControlGroup.add(jRadioAnimalControl1);
        jRadioAnimalControl1.setFont(defaultFont1);
        jRadioAnimalControl1.setSelected(true);
        jRadioAnimalControl1.setText("Control");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelBugControl.add(jRadioAnimalControl1, gridBagConstraints);

        jTabbedPaneQuestionaire.addTab("การควบคุมสัตว์", jPanelBugControl);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelQuertionaire.add(jTabbedPaneQuestionaire, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButtonAddSubHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAddSubHome.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonAddSubHome.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonAddSubHome.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonAddSubHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddSubHomeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jButtonAddSubHome, gridBagConstraints);

        jButtonDelSubHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelSubHome.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDelSubHome.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDelSubHome.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonDelSubHome.setRequestFocusEnabled(false);
        jButtonDelSubHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelSubHomeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel1.add(jButtonDelSubHome, gridBagConstraints);

        jButtonSaveSubHome.setFont(defaultFont1);
        jButtonSaveSubHome.setText("Save");
        jButtonSaveSubHome.setMaximumSize(new java.awt.Dimension(67, 24));
        jButtonSaveSubHome.setMinimumSize(new java.awt.Dimension(67, 24));
        jButtonSaveSubHome.setPreferredSize(new java.awt.Dimension(67, 24));
        jButtonSaveSubHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveSubHomeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButtonSaveSubHome, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelQuertionaire.add(jPanel1, gridBagConstraints);

        jPanelSubHome.setBorder(null);
        jPanelSubHome.setMinimumSize(new java.awt.Dimension(145, 20));
        jPanelSubHome.setOpaque(false);
        jPanelSubHome.setPreferredSize(new java.awt.Dimension(145, 20));
        jPanelSubHome.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 402));

        jTableSubHome.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableSubHome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableSubHomeKeyReleased(evt);
            }
        });
        jTableSubHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableSubHomeMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTableSubHome);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelSubHome.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 54;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelQuertionaire.add(jPanelSubHome, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9Survey.add(jPanelQuertionaire, gridBagConstraints);

        jTabbedPaneHomeDatail.addTab("การสำรวจ", jPanel9Survey);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanelDetail.add(jTabbedPaneHomeDatail, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jButtonAddHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAddHome.setText("เพิ่มบ้าน");
        jButtonAddHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddHomeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(jButtonAddHome, gridBagConstraints);

        jButtonDelHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelHome.setText("ยกเลิกบ้าน");
        jButtonDelHome.setRequestFocusEnabled(false);
        jButtonDelHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelHomeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel2.add(jButtonDelHome, gridBagConstraints);

        jButtonSaveHome.setFont(defaultFont1);
        jButtonSaveHome.setText("Save");
        jButtonSaveHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveHomeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButtonSaveHome, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanelDetail.add(jPanel2, gridBagConstraints);

        jPanelHome.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SearchHome", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jPanelHome.setMinimumSize(new java.awt.Dimension(200, 200));
        jPanelHome.setPreferredSize(new java.awt.Dimension(200, 200));
        jPanelHome.setLayout(new java.awt.GridBagLayout());

        jPanelSearch.setLayout(new java.awt.GridBagLayout());

        jTextFieldSearchHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchHomeActionPerformed(evt);
            }
        });
        jTextFieldSearchHome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldSearchHomeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchHomeKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanelSearch.add(jTextFieldSearchHome, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanelHome.add(jPanelSearch, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jComboBoxVillage.setEnabled(false);
        jComboBoxVillage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxVillageActionPerformed(evt);
            }
        });
        jComboBoxVillage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jComboBoxVillageMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel8.add(jComboBoxVillage, gridBagConstraints);

        jButtonRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pcu/images/Refresh2.gif"))); // NOI18N
        jButtonRefresh.setToolTipText("Refresh หมู่บ้าน");
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel8.add(jButtonRefresh, gridBagConstraints);

        jCheckBoxByVillage.setFont(defaultFont1);
        jCheckBoxByVillage.setText("VillageName");
        jCheckBoxByVillage.setToolTipText("แสดงบ้านทุกหลังในทุกหมุ่บ้าน");
        jCheckBoxByVillage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxByVillageActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel8.add(jCheckBoxByVillage, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 6);
        jPanelHome.add(jPanel8, gridBagConstraints);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(18, 100));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 500));

        jTableHome.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableHome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableHomeKeyReleased(evt);
            }
        });
        jTableHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableHomeMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableHome);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelHome.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 54;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelDetail.add(jPanelHome, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanelDetail, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jScrollPane3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane3MouseReleased
        if(evt.isPopupTrigger())   {
            jPopupMenu1.setLocation((evt.getX()+jScrollPane3.getLocationOnScreen().x)
                , (evt.getY()+jScrollPane3.getLocationOnScreen().y));
            jPopupMenu1.setVisible(true);
        }
        else {
            jPopupMenu1.setVisible(false);
        }
    }//GEN-LAST:event_jScrollPane3MouseReleased

    private void jTableFamilyPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableFamilyPropertyChange
        setEnabledMoveHomeButton(true);
    }//GEN-LAST:event_jTableFamilyPropertyChange

    private void jButtonMoveHome0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMoveHome0ActionPerformed
        int ret = theHomeControl.saveFamily(getCutFamily(),null);
        if(ret==0)
            return;
        setTablePatient();
        setEnableGuiFamily(true);
    }//GEN-LAST:event_jButtonMoveHome0ActionPerformed

    private void jButtonPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPasteActionPerformed
        int ret = theHomeControl.saveFamily(theFamilyTempV,theHome);
        if(ret==0)
            return;
        setTablePatient();
        theFamilyTempV.clear();
        setEnableGuiFamily(true);
    }//GEN-LAST:event_jButtonPasteActionPerformed

    private void jButtonCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCutActionPerformed
        theFamilyTempV = getCutFamily();        
        setEnabledMoveHomeButton(true);
    }//GEN-LAST:event_jButtonCutActionPerformed

    private void jTextFieldSearchHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchHomeActionPerformed
         setTableHome();
    }//GEN-LAST:event_jTextFieldSearchHomeActionPerformed

    private void jComboBoxVillage1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxVillage1ItemStateChanged
        Village village = theHomeControl.listVillage(ComboboxModel.getCodeComboBox(jComboBoxVillage1));
        if(village != null && !"".equalsIgnoreCase(village.getObjectId()))
        {
            Gutil.setGuiData(jComboBoxChungwat, village.village_changwat);
            Gutil.setGuiData(jComboBoxAmphur, village.village_ampur);
            Gutil.setGuiData(jComboBoxTambol, village.village_tambon);
        }
    }//GEN-LAST:event_jComboBoxVillage1ItemStateChanged

    private void jButtonStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStatusActionPerformed

        boolean res = theHosDialog.showDialogPersonStatus(theHM,theFamily,frm1);
        if(res){
            if(theFamily.discharge_status_id.equals(Dischar.DEATH)){
                DialogDeath theDialogDeath = new DialogDeath(theHosManage.theHC,frm1);
                Death dt = theHosManage.theHC.theHO.initDeath();
                theDialogDeath.showDialogForPCU(dt,theFamily);
            }
            else{
                this.theHomeControl.saveFamilyDischarge(theFamily);
            }
            setTablePatient();
        }
    }//GEN-LAST:event_jButtonStatusActionPerformed

    private void integerTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integerTextField3FocusLost

    }//GEN-LAST:event_integerTextField3FocusLost

    private void jTableSubHomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableSubHomeKeyReleased

        if(evt.getKeyCode()==evt.VK_UP
        || evt.getKeyCode()==evt.VK_DOWN){
            selectSubHome(-2); 
        }
    }//GEN-LAST:event_jTableSubHomeKeyReleased

    private void jTablePetKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablePetKeyReleased

        if(evt.getKeyCode()==evt.VK_UP
        || evt.getKeyCode()==evt.VK_DOWN){
            selectPet(-2);
        }
    }//GEN-LAST:event_jTablePetKeyReleased

    private void jTableHerbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableHerbKeyReleased

        if(evt.getKeyCode()==evt.VK_UP
        || evt.getKeyCode()==evt.VK_DOWN){
            selectHerb(-2);
            jButtonDelHerb.setEnabled(true);             
         }
    }//GEN-LAST:event_jTableHerbKeyReleased

    private void jTableFamilyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableFamilyKeyReleased
        if(evt.getKeyCode()==evt.VK_UP
        || evt.getKeyCode()==evt.VK_DOWN){ 
            setEnableGuiFamily(true);
            selectFamily();
        }        
    }//GEN-LAST:event_jTableFamilyKeyReleased

    private void jTableHomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableHomeKeyReleased
        if(evt.getKeyCode()==evt.VK_UP
        || evt.getKeyCode()==evt.VK_DOWN){
            selectHome(-2);
        }        
    }//GEN-LAST:event_jTableHomeKeyReleased

    private void dateComboBoxVaccineDayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxVaccineDayFocusLost
        checkpetborn = false;
    }//GEN-LAST:event_dateComboBoxVaccineDayFocusLost

    private void dateComboBoxVaccineDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxVaccineDayActionPerformed
        checkDatePetVaccine();
    }//GEN-LAST:event_dateComboBoxVaccineDayActionPerformed

    private void dateComboBoxBirthDayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxBirthDayFocusLost
        checkpetborn = false;
    }//GEN-LAST:event_dateComboBoxBirthDayFocusLost

    private void dateComboBoxBirthDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxBirthDayActionPerformed

        checkDatePetControl();
    }//GEN-LAST:event_dateComboBoxBirthDayActionPerformed

    private void dateComboBoxPetBirthDayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxPetBirthDayFocusLost

        checkpetborn = false;
    }//GEN-LAST:event_dateComboBoxPetBirthDayFocusLost

    private void dateComboBoxPetBirthDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxPetBirthDayActionPerformed
 
        checkDatePetBirth();
    }//GEN-LAST:event_dateComboBoxPetBirthDayActionPerformed

    private void jComboBoxVillage1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxVillage1ActionPerformed

        //relationComboBoxVillage1();        
        //searchHome();
        selectVillage();
    }//GEN-LAST:event_jComboBoxVillage1ActionPerformed

    private void jTextFieldSearchHomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchHomeKeyReleased
        if(jTextFieldSearchHome.getText().length()>2)
        {   searchHomeBegin();
            //setTableHome();
        }
        else if (jTextFieldSearchHome.getText().equals(""))
        {   searchHomeBegin();
            //setTableHome();
        }
    }//GEN-LAST:event_jTextFieldSearchHomeKeyReleased

    private void jTableFamilyMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFamilyMouseReleased
        if(evt.isPopupTrigger())   {
            jPopupMenu1.setLocation((evt.getX()+this.jTableFamily.getLocationOnScreen().x)
                , (evt.getY()+jTableFamily.getLocationOnScreen().y));
            jPopupMenu1.setVisible(true);
        }
        else {
            jPopupMenu1.setVisible(false);
        }
        setEnableGuiFamily(true);
        selectFamily();
        setEnabledMoveHomeButton(true);
    }//GEN-LAST:event_jTableFamilyMouseReleased

    private void jButtonDelPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelPatientActionPerformed
        int row = jTableFamily.getSelectedRow();
        if(row != -1)
        {   if(!theUS.confirmBox(GutilPCU.getTextBundle("ConfirmDeletePerson"),UpdateStatus.WARNING))
                return;
            deleteDataToObjectFamily(row);               
        }   
    }//GEN-LAST:event_jButtonDelPatientActionPerformed

    private void jButtonUpdatePatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdatePatientActionPerformed
        copypid = theFamily.pid;
        thePanelPerson.showPanel(frm1,1,theHome,theFamily,theUS);
        setTableHome();
        setTablePatient();
    }//GEN-LAST:event_jButtonUpdatePatientActionPerformed

    private void jTextFieldSearchHomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchHomeKeyPressed
        if(jTextFieldSearchHome.getText().length()>2)
        {   searchHomeBegin();
            //setTableHome();
        }
        else if (jTextFieldSearchHome.getText().equals(""))
        {   searchHomeBegin();
            //setTableHome();
        }
    }//GEN-LAST:event_jTextFieldSearchHomeKeyPressed

    private void jButtonAddPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddPatientActionPerformed
        copypid = "";
        thePanelPerson.showPanel(frm1,1,theHome,null); 
        setTablePatient();
    }//GEN-LAST:event_jButtonAddPatientActionPerformed

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        setTableHome();
        ComboboxModel.initComboBox(jComboBoxPetType,theAllComboBoxControl.listPetType());
        ComboboxModel.initComboBox(jComboBoxVillage,theAllComboBoxControl.listVillage());
        ComboboxModel.initComboBox(jComboBoxVillage1,theAllComboBoxControl.listVillage());
    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void jComboBoxVillageMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxVillageMouseReleased
        
    }//GEN-LAST:event_jComboBoxVillageMouseReleased

    private void jCheckBoxByVillageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxByVillageActionPerformed
        jComboBoxVillage.setEnabled(jCheckBoxByVillage.isSelected());
        searchHomeBegin();
        setTableHome();        
    }//GEN-LAST:event_jCheckBoxByVillageActionPerformed

    private void jComboBoxVillageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxVillageActionPerformed
        
        relationComboBoxVillage();        
        //if(!actioncombo)
        searchHomeBegin();
        setTableHome();
        selectVillage();
    }//GEN-LAST:event_jComboBoxVillageActionPerformed

    private void jComboBoxAmphurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAmphurActionPerformed
        
        selectAmpur();
    }//GEN-LAST:event_jComboBoxAmphurActionPerformed

    private void jButtonDelPetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelPetActionPerformed
        
        int row = jTablePet.getSelectedRow();
        if(row!=-1)
        { 
            if(!theUS.confirmBox(GutilPCU.getTextBundle("ConfirmDelete"),UpdateStatus.WARNING))
                return;
            {
                deletePet();        
            }
        }
    }//GEN-LAST:event_jButtonDelPetActionPerformed

    private void jTablePetMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePetMouseReleased
        
        selectPet(-2);
    }//GEN-LAST:event_jTablePetMouseReleased

    private void jTableHerbMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHerbMouseReleased
        
        selectHerb(-2);
        jButtonDelHerb.setEnabled(true);
    }//GEN-LAST:event_jTableHerbMouseReleased

    private void jButtonDelHerbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelHerbActionPerformed
        
        int row = jTableHerb.getSelectedRow();
        if(row!=-1)
        { 
            if(!theUS.confirmBox(GutilPCU.getTextBundle("ConfirmDelete"),UpdateStatus.WARNING))
                return;
            {
                deleteHerb();
            }
        }
    }//GEN-LAST:event_jButtonDelHerbActionPerformed

    private void jButtonAddHerbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddHerbActionPerformed
        addHerb();        
    }//GEN-LAST:event_jButtonAddHerbActionPerformed

    private void jButtonSaveHerbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveHerbActionPerformed
        
        if(jTextFieldHerbName.getText().equals(""))
        {
            theUS.setStatus(GutilPCU.getTextBundle("HerbNameIsEmpty"),UpdateStatus.WARNING); 
        }
        else
        {
            if(theHerb==null)theHerb = new Herb();
            setDataToObjectHerb();
            setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
            saveHerb();
            setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_jButtonSaveHerbActionPerformed

    private void jButtonDelSubHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelSubHomeActionPerformed
        
        int row = jTableSubHome.getSelectedRow();
        if(row!=-1)
        {
            if(!theUS.confirmBox(GutilPCU.getTextBundle("ConfirmDelete"),UpdateStatus.WARNING))
                return;
            {
                deleteSubHome();
            }
        }
    }//GEN-LAST:event_jButtonDelSubHomeActionPerformed

    private void jButtonAddSubHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddSubHomeActionPerformed
        
        addSubHome();        
    }//GEN-LAST:event_jButtonAddSubHomeActionPerformed

    private void jButtonSaveSubHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveSubHomeActionPerformed
        
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        saveSubHome();
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jButtonSaveSubHomeActionPerformed

    private void jButtonDelHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelHomeActionPerformed
       deleteHome();
    }//GEN-LAST:event_jButtonDelHomeActionPerformed

    private void jButtonAddHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddHomeActionPerformed
        addHome();
    }//GEN-LAST:event_jButtonAddHomeActionPerformed

    private void jButtonSaveHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveHomeActionPerformed
        saveHome(getHome());
    }//GEN-LAST:event_jButtonSaveHomeActionPerformed

    private void jComboBoxChungwatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxChungwatActionPerformed
        selectChangwat();
    }//GEN-LAST:event_jComboBoxChungwatActionPerformed

    private void jTableSubHomeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSubHomeMouseReleased
        
        selectSubHome(-2);        
    }//GEN-LAST:event_jTableSubHomeMouseReleased

    private void jTableHomeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHomeMouseReleased
        
        selectHome(-2);  
        flagInsert = false;
    }//GEN-LAST:event_jTableHomeMouseReleased

    private void jButtonSavePetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSavePetActionPerformed

        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        savePet();
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jButtonSavePetActionPerformed

    private void jButtonAddPetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddPetActionPerformed

        addPet();
    }//GEN-LAST:event_jButtonAddPetActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup AnimalControlGroup;
    private javax.swing.ButtonGroup CockroachControlGroup;
    private javax.swing.ButtonGroup FlyControlGroup;
    private javax.swing.ButtonGroup FoodCoverGroup;
    private javax.swing.ButtonGroup FoodStandardGroup;
    private javax.swing.ButtonGroup FoodVesselGroup;
    private javax.swing.ButtonGroup GarbageGroup;
    private javax.swing.ButtonGroup HomeCareGroup;
    private javax.swing.ButtonGroup HomeCleannessGroup;
    private javax.swing.ButtonGroup HomeDurabilityGroup;
    private javax.swing.ButtonGroup HomeLightGroup;
    private javax.swing.ButtonGroup HomeVentilationGroup;
    private javax.swing.ButtonGroup KeepVesselGroup;
    private javax.swing.ButtonGroup KitchenCleannessGroup;
    private javax.swing.ButtonGroup KitchenGarbageGroup;
    private javax.swing.ButtonGroup MixtureFoodGroup;
    private javax.swing.ButtonGroup PetGroup;
    private javax.swing.ButtonGroup PetSexGroup;
    private javax.swing.ButtonGroup ProductIodineGroup;
    private javax.swing.ButtonGroup PublicHealthGroup;
    private javax.swing.ButtonGroup RatControlGroup;
    private javax.swing.ButtonGroup ResponsibleZone;
    private javax.swing.ButtonGroup SaltIodineGroup;
    private javax.swing.ButtonGroup SummaryControlGroup;
    private javax.swing.ButtonGroup ToiletStandardGroup;
    private javax.swing.ButtonGroup UseGroup;
    private javax.swing.ButtonGroup UseIodineGroup;
    private javax.swing.ButtonGroup WashVesselGroup;
    private javax.swing.ButtonGroup WaterDrinkAmplyGroup;
    private javax.swing.ButtonGroup WaterEradicateGroup;
    private javax.swing.ButtonGroup WaterUseAmplyGroup;
    private com.pcu.utility.DateComboBox dateComboBoxBirthDay;
    private com.pcu.utility.DateComboBox dateComboBoxPetBirthDay;
    private com.pcu.utility.DateComboBox dateComboBoxVaccineDay;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.pcu.utility.IntegerTextField integerTextField1;
    private com.pcu.utility.IntegerTextField integerTextField2;
    private com.pcu.utility.IntegerTextField integerTextField3;
    private com.pcu.utility.IntegerTextField integerTextFieldQuantity;
    private javax.swing.JButton jButtonAddHerb;
    private javax.swing.JButton jButtonAddHome;
    private javax.swing.JButton jButtonAddPatient;
    private javax.swing.JButton jButtonAddPet;
    private javax.swing.JButton jButtonAddSubHome;
    private javax.swing.JButton jButtonCut;
    private javax.swing.JButton jButtonDelHerb;
    private javax.swing.JButton jButtonDelHome;
    private javax.swing.JButton jButtonDelPatient;
    private javax.swing.JButton jButtonDelPet;
    private javax.swing.JButton jButtonDelSubHome;
    private javax.swing.JButton jButtonMoveHome0;
    private javax.swing.JButton jButtonPaste;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonSaveHerb;
    private javax.swing.JButton jButtonSaveHome;
    private javax.swing.JButton jButtonSavePet;
    private javax.swing.JButton jButtonSaveSubHome;
    private javax.swing.JButton jButtonStatus;
    private javax.swing.JButton jButtonUpdatePatient;
    private javax.swing.JCheckBox jCheckBoxByVillage;
    private javax.swing.JComboBox jComboBoxAmphur;
    private javax.swing.JComboBox jComboBoxChungwat;
    private javax.swing.JComboBox jComboBoxCommunity;
    private javax.swing.JComboBox jComboBoxDungControl;
    private javax.swing.JComboBox jComboBoxGarbageMethod;
    private javax.swing.JComboBox jComboBoxHomeCharacter;
    private javax.swing.JComboBox jComboBoxPetSex;
    private javax.swing.JComboBox jComboBoxPetType;
    private javax.swing.JComboBox jComboBoxTambol;
    private javax.swing.JComboBox jComboBoxVillage;
    private javax.swing.JComboBox jComboBoxVillage1;
    private javax.swing.JComboBox jComboBoxWaterDrink;
    private javax.swing.JComboBox jComboBoxWaterUse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelAmphur;
    private javax.swing.JLabel jLabelAmphur0;
    private javax.swing.JLabel jLabelAnimalControl;
    private javax.swing.JLabel jLabelBirthDay;
    private javax.swing.JLabel jLabelChangwat;
    private javax.swing.JLabel jLabelChangwat1;
    private javax.swing.JLabel jLabelChungwat;
    private javax.swing.JLabel jLabelCockroachControl;
    private javax.swing.JLabel jLabelCommunity;
    private javax.swing.JLabel jLabelDungControl;
    private javax.swing.JLabel jLabelFlyComtrol;
    private javax.swing.JLabel jLabelFoodCover;
    private javax.swing.JLabel jLabelFoodStandard;
    private javax.swing.JLabel jLabelFoodVessel;
    private javax.swing.JLabel jLabelGarbage;
    private javax.swing.JLabel jLabelGarbageMethod;
    private javax.swing.JLabel jLabelHealthCenter;
    private javax.swing.JLabel jLabelHealthCenterName;
    private javax.swing.JLabel jLabelHerbName;
    private javax.swing.JLabel jLabelHomeCare;
    private javax.swing.JLabel jLabelHomeCharacter;
    private javax.swing.JLabel jLabelHomeCleanness;
    private javax.swing.JLabel jLabelHomeDurability;
    private javax.swing.JLabel jLabelHomeLight;
    private javax.swing.JLabel jLabelHomeNumber;
    private javax.swing.JLabel jLabelHomeOrder;
    private javax.swing.JLabel jLabelHomeVentilation;
    private javax.swing.JLabel jLabelKeepVessel;
    private javax.swing.JLabel jLabelKitchenClean;
    private javax.swing.JLabel jLabelKitchenGarbage;
    private javax.swing.JLabel jLabelMixture;
    private javax.swing.JLabel jLabelMoo;
    private javax.swing.JLabel jLabelOwnerName;
    private javax.swing.JLabel jLabelPet;
    private javax.swing.JLabel jLabelPetBirthDay;
    private javax.swing.JLabel jLabelPetName;
    private javax.swing.JLabel jLabelPetSex;
    private javax.swing.JLabel jLabelPetType;
    private javax.swing.JLabel jLabelProductIodine;
    private javax.swing.JLabel jLabelPublicHealth;
    private javax.swing.JLabel jLabelQuantity;
    private javax.swing.JLabel jLabelRatcontrol;
    private javax.swing.JLabel jLabelResponsibleZone;
    private javax.swing.JLabel jLabelRoad;
    private javax.swing.JLabel jLabelSaltIodine;
    private javax.swing.JLabel jLabelSummaryControl;
    private javax.swing.JLabel jLabelTambon;
    private javax.swing.JLabel jLabelTambon1;
    private javax.swing.JLabel jLabelTelephoneOwner;
    private javax.swing.JLabel jLabelToiletStandard;
    private javax.swing.JLabel jLabelUse;
    private javax.swing.JLabel jLabelUseIodine;
    private javax.swing.JLabel jLabelVaccineDay;
    private javax.swing.JLabel jLabelVillage1;
    private javax.swing.JLabel jLabelVolunteer;
    private javax.swing.JLabel jLabelVolunteerName;
    private javax.swing.JLabel jLabelWashVessel;
    private javax.swing.JLabel jLabelWaterDrink;
    private javax.swing.JLabel jLabelWaterDrinkAmply;
    private javax.swing.JLabel jLabelWaterEradicate;
    private javax.swing.JLabel jLabelWaterUse;
    private javax.swing.JLabel jLabelWaterUseAmply;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel9Survey;
    private javax.swing.JPanel jPanelBugControl;
    private javax.swing.JPanel jPanelDetail;
    private javax.swing.JPanel jPanelFamily;
    private javax.swing.JPanel jPanelFoodStandard;
    private javax.swing.JPanel jPanelHealthCenterDetail;
    private javax.swing.JPanel jPanelHerb;
    private javax.swing.JPanel jPanelHome;
    private javax.swing.JPanel jPanelHome2;
    private javax.swing.JPanel jPanelHome3;
    private javax.swing.JPanel jPanelHome5;
    private javax.swing.JPanel jPanelHome6;
    private javax.swing.JPanel jPanelHomeDetail;
    private javax.swing.JPanel jPanelHomeStandard;
    private javax.swing.JPanel jPanelHomeWater;
    private javax.swing.JPanel jPanelManageFamily;
    private javax.swing.JPanel jPanelPet;
    private javax.swing.JPanel jPanelQuertionaire;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JPanel jPanelSubHome;
    private javax.swing.JRadioButton jRadioAnimalControl0;
    private javax.swing.JRadioButton jRadioAnimalControl1;
    private javax.swing.JRadioButton jRadioButtonCockroach0;
    private javax.swing.JRadioButton jRadioButtonCockroach1;
    private javax.swing.JRadioButton jRadioButtonFly0;
    private javax.swing.JRadioButton jRadioButtonFly1;
    private javax.swing.JRadioButton jRadioButtonFoodCover0;
    private javax.swing.JRadioButton jRadioButtonFoodCover1;
    private javax.swing.JRadioButton jRadioButtonFoodStand0;
    private javax.swing.JRadioButton jRadioButtonFoodStand1;
    private javax.swing.JRadioButton jRadioButtonFoodVessel0;
    private javax.swing.JRadioButton jRadioButtonFoodVessel1;
    private javax.swing.JRadioButton jRadioButtonGarbage0;
    private javax.swing.JRadioButton jRadioButtonGarbage1;
    private javax.swing.JRadioButton jRadioButtonHomeCare0;
    private javax.swing.JRadioButton jRadioButtonHomeCare1;
    private javax.swing.JRadioButton jRadioButtonHomeClean0;
    private javax.swing.JRadioButton jRadioButtonHomeClean1;
    private javax.swing.JRadioButton jRadioButtonHomeDura0;
    private javax.swing.JRadioButton jRadioButtonHomeDura1;
    private javax.swing.JRadioButton jRadioButtonHomeLight0;
    private javax.swing.JRadioButton jRadioButtonHomeLight1;
    private javax.swing.JRadioButton jRadioButtonHomeVen0;
    private javax.swing.JRadioButton jRadioButtonHomeVen1;
    private javax.swing.JRadioButton jRadioButtonInZone;
    private javax.swing.JRadioButton jRadioButtonKeep0;
    private javax.swing.JRadioButton jRadioButtonKeep1;
    private javax.swing.JRadioButton jRadioButtonKitchenClean0;
    private javax.swing.JRadioButton jRadioButtonKitchenClean1;
    private javax.swing.JRadioButton jRadioButtonKitchenGarbage0;
    private javax.swing.JRadioButton jRadioButtonKitchenGarbage1;
    private javax.swing.JRadioButton jRadioButtonMixFood0;
    private javax.swing.JRadioButton jRadioButtonMixFood1;
    private javax.swing.JRadioButton jRadioButtonOutZone;
    private javax.swing.JRadioButton jRadioButtonPet0;
    private javax.swing.JRadioButton jRadioButtonPet1;
    private javax.swing.JRadioButton jRadioButtonProductIodine0;
    private javax.swing.JRadioButton jRadioButtonProductIodine1;
    private javax.swing.JRadioButton jRadioButtonPublicHealth0;
    private javax.swing.JRadioButton jRadioButtonPublicHealth1;
    private javax.swing.JRadioButton jRadioButtonRat0;
    private javax.swing.JRadioButton jRadioButtonRat1;
    private javax.swing.JRadioButton jRadioButtonSaltIodine0;
    private javax.swing.JRadioButton jRadioButtonSaltIodine1;
    private javax.swing.JRadioButton jRadioButtonSummary0;
    private javax.swing.JRadioButton jRadioButtonSummary1;
    private javax.swing.JRadioButton jRadioButtonToilet0;
    private javax.swing.JRadioButton jRadioButtonToilet1;
    private javax.swing.JRadioButton jRadioButtonUse0;
    private javax.swing.JRadioButton jRadioButtonUse1;
    private javax.swing.JRadioButton jRadioButtonUseIodine0;
    private javax.swing.JRadioButton jRadioButtonUseIodine1;
    private javax.swing.JRadioButton jRadioButtonWash0;
    private javax.swing.JRadioButton jRadioButtonWash1;
    private javax.swing.JRadioButton jRadioButtonWaterDrink0;
    private javax.swing.JRadioButton jRadioButtonWaterDrink1;
    private javax.swing.JRadioButton jRadioButtonWaterEra0;
    private javax.swing.JRadioButton jRadioButtonWaterEra1;
    private javax.swing.JRadioButton jRadioButtonWaterUse0;
    private javax.swing.JRadioButton jRadioButtonWaterUse1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPaneHomeDatail;
    private javax.swing.JTabbedPane jTabbedPaneQuestionaire;
    private javax.swing.JTable jTableFamily;
    private javax.swing.JTable jTableHerb;
    private javax.swing.JTable jTableHome;
    private javax.swing.JTable jTablePet;
    private javax.swing.JTable jTableSubHome;
    private javax.swing.JTextField jTextFieldHerbName;
    private javax.swing.JTextField jTextFieldHomeNumber;
    private javax.swing.JTextField jTextFieldMoo;
    private javax.swing.JTextField jTextFieldOwnerName;
    private javax.swing.JTextField jTextFieldOwnerNumber;
    private javax.swing.JTextField jTextFieldPetName;
    private javax.swing.JTextField jTextFieldRoad;
    private javax.swing.JTextField jTextFieldSearchHome;
    private javax.swing.JTextField jTextFieldVolunteer;
    private javax.swing.JTextField jTextFieldVolunteerName;
    // End of variables declaration//GEN-END:variables
    public void setLanguage()
    {  
        /*jLabel*/
        GutilPCU.setGuiLang(jLabelChungwat);
        GutilPCU.setGuiLang(jLabelAmphur0);
        GutilPCU.setGuiLang(jTabbedPaneHomeDatail);
        GutilPCU.setGuiLang(jTabbedPaneQuestionaire);
        GutilPCU.setGuiLang(jLabelWaterEradicate);
        GutilPCU.setGuiLang(jLabelSummaryControl);
        GutilPCU.setGuiLang(jLabelTelephoneOwner);
      
        //jLabelChungwat.setText(GutilPCU.getTextBundle(jLabelChungwat.getText())); 
        jLabelHomeOrder.setText(GutilPCU.getTextBundle(jLabelHomeOrder.getText()));
        jLabelHomeNumber.setText(GutilPCU.getTextBundle(jLabelHomeNumber.getText())); 
        jLabelMoo.setText(GutilPCU.getTextBundle(jLabelMoo.getText())); 
        jLabelRoad.setText(GutilPCU.getTextBundle(jLabelRoad.getText())); 
        jLabelQuantity.setText(GutilPCU.getTextBundle(jLabelQuantity.getText()));
        jLabelResponsibleZone.setText(GutilPCU.getTextBundle(jLabelResponsibleZone.getText()));        
        jLabelVolunteer.setText(GutilPCU.getTextBundle(jLabelVolunteer.getText())); 
        jLabelOwnerName.setText(GutilPCU.getTextBundle(jLabelOwnerName.getText())); 
        jLabelHomeCharacter.setText(GutilPCU.getTextBundle(jLabelHomeCharacter.getText())); 
        jLabelCommunity.setText(GutilPCU.getTextBundle(jLabelCommunity.getText()));
        jLabelHealthCenterName.setText(GutilPCU.getTextBundle(jLabelHealthCenterName.getText()));
        jLabelHealthCenter.setText(GutilPCU.getTextBundle(jLabelHealthCenter.getText()));
        jLabelTambon1.setText(GutilPCU.getTextBundle(jLabelTambon1.getText()));
        jLabelTambon.setText(GutilPCU.getTextBundle(jLabelTambon.getText()));
        jLabelChangwat1.setText(GutilPCU.getTextBundle(jLabelChangwat1.getText()));
        jLabelChangwat.setText(GutilPCU.getTextBundle(jLabelChangwat.getText()));
        jLabelAnimalControl.setText(GutilPCU.getTextBundle(jLabelAnimalControl.getText()));
        jLabelVolunteerName.setText(GutilPCU.getTextBundle(jLabelVolunteerName.getText()));
        jLabelTelephoneOwner.setText(GutilPCU.getTextBundle(jLabelTelephoneOwner.getText()));
        jLabelVillage1.setText(GutilPCU.getTextBundle(jLabelVillage1.getText()));
       
        /*jLabelQuestionaire*/
        jLabelWaterDrink.setText(GutilPCU.getTextBundle(jLabelWaterDrink.getText())); 
        jLabelWaterUse.setText(GutilPCU.getTextBundle(jLabelWaterUse.getText())); 
        jLabelWaterDrinkAmply.setText(GutilPCU.getTextBundle(jLabelWaterDrinkAmply.getText()));         
        jLabelWaterUseAmply.setText(GutilPCU.getTextBundle(jLabelWaterUseAmply.getText())); 
        jLabelToiletStandard.setText(GutilPCU.getTextBundle(jLabelToiletStandard.getText())); 
        jLabelGarbage.setText(GutilPCU.getTextBundle(jLabelGarbage.getText())); 
        jLabelWaterEradicate.setText(GutilPCU.getTextBundle(jLabelWaterEradicate.getText())); 
        jLabelGarbageMethod.setText(GutilPCU.getTextBundle(jLabelGarbageMethod.getText())); 
        jLabelHomeDurability.setText(GutilPCU.getTextBundle(jLabelHomeDurability.getText()));
        jLabelHomeCare.setText(GutilPCU.getTextBundle(jLabelHomeCare.getText()));
        jLabelHomeLight.setText(GutilPCU.getTextBundle(jLabelHomeLight.getText()));
        jLabelHomeCleanness.setText(GutilPCU.getTextBundle(jLabelHomeCleanness.getText()));
        jLabelHomeVentilation.setText(GutilPCU.getTextBundle(jLabelHomeVentilation.getText()));
        jLabelPublicHealth.setText(GutilPCU.getTextBundle(jLabelPublicHealth.getText()));
        jLabelMixture.setText(GutilPCU.getTextBundle(jLabelMixture.getText()));
        jLabelFoodVessel.setText(GutilPCU.getTextBundle(jLabelFoodVessel.getText()));
        jLabelFoodCover.setText(GutilPCU.getTextBundle(jLabelFoodCover.getText()));
        jLabelWashVessel.setText(GutilPCU.getTextBundle(jLabelWashVessel.getText()));
        jLabelKeepVessel.setText(GutilPCU.getTextBundle(jLabelKeepVessel.getText()));
        jLabelKitchenGarbage.setText(GutilPCU.getTextBundle(jLabelKitchenGarbage.getText()));
        jLabelKitchenClean.setText(GutilPCU.getTextBundle(jLabelKitchenClean.getText()));
        jLabelFoodStandard.setText(GutilPCU.getTextBundle(jLabelFoodStandard.getText()));
        jLabelUseIodine.setText(GutilPCU.getTextBundle(jLabelUseIodine.getText()));
        jLabelSaltIodine.setText(GutilPCU.getTextBundle(jLabelSaltIodine.getText()));
        jLabelProductIodine.setText(GutilPCU.getTextBundle(jLabelProductIodine.getText()));
        jLabelRatcontrol.setText(GutilPCU.getTextBundle(jLabelRatcontrol.getText()));
        jLabelCockroachControl.setText(GutilPCU.getTextBundle(jLabelCockroachControl.getText()));
        jLabelFlyComtrol.setText(GutilPCU.getTextBundle(jLabelFlyComtrol.getText()));
        jLabelSummaryControl.setText(GutilPCU.getTextBundle(jLabelSummaryControl.getText()));
        jLabelPet.setText(GutilPCU.getTextBundle(jLabelPet.getText()));
        jLabelDungControl.setText(GutilPCU.getTextBundle(jLabelDungControl.getText()));
        jLabelHerbName.setText(GutilPCU.getTextBundle(jLabelHerbName.getText()));
        jLabelUse.setText(GutilPCU.getTextBundle(jLabelUse.getText()));
        jLabelPetType.setText(GutilPCU.getTextBundle(jLabelPetType.getText()));
        jLabelPetName.setText(GutilPCU.getTextBundle(jLabelPetName.getText()));
        jLabelPetSex.setText(GutilPCU.getTextBundle(jLabelPetSex.getText()));
        jLabelPetBirthDay.setText(GutilPCU.getTextBundle(jLabelPetBirthDay.getText()));
       
        GutilPCU.setGuiLang(jLabelVaccineDay);
        //jLabelVaccineDay.setText(GutilPCU.getTextBundle(jLabelVaccineDay.getText()));
        jLabelBirthDay.setText(GutilPCU.getTextBundle(jLabelBirthDay.getText()));    
        
        /*jButton*/
        GutilPCU.setGuiLang(jButtonCut);
        GutilPCU.setGuiLang(jButtonDelHome);
        GutilPCU.setGuiLang(jButtonPaste);
        GutilPCU.setGuiLang(jButtonMoveHome0);
        jButtonAddHome.setText(GutilPCU.getTextBundle(jButtonAddHome.getText()));
        jButtonDelHome.setText(GutilPCU.getTextBundle(jButtonDelHome.getText()));
        jButtonSaveHome.setText(GutilPCU.getTextBundle(jButtonSaveHome.getText()));
        jButtonAddSubHome.setText(GutilPCU.getTextBundle(jButtonAddSubHome.getText()));
        jButtonDelSubHome.setText(GutilPCU.getTextBundle(jButtonDelSubHome.getText()));
        jButtonSaveSubHome.setText(GutilPCU.getTextBundle(jButtonSaveSubHome.getText()));        
        jButtonAddHerb.setText(GutilPCU.getTextBundle(jButtonAddHerb.getText()));
        jButtonDelHerb.setText(GutilPCU.getTextBundle(jButtonDelHerb.getText()));
        jButtonSaveHerb.setText(GutilPCU.getTextBundle(jButtonSaveHerb.getText()));
        jButtonAddPet.setText(GutilPCU.getTextBundle(jButtonAddPet.getText()));
        jButtonDelPet.setText(GutilPCU.getTextBundle(jButtonDelPet.getText()));
        jButtonSavePet.setText(GutilPCU.getTextBundle(jButtonSavePet.getText()));
        jButtonAddPatient.setText(GutilPCU.getTextBundle(jButtonAddPatient.getText()));
        jButtonDelPatient.setText(GutilPCU.getTextBundle(jButtonDelPatient.getText()));
        jButtonUpdatePatient.setText(GutilPCU.getTextBundle(jButtonUpdatePatient.getText()));
        jButtonStatus.setText(GutilPCU.getTextBundle(jButtonStatus.getText()));
        
        /*jRatioButton*/
        GutilPCU.setGuiLang(jRadioButtonWaterEra0);
        GutilPCU.setGuiLang(jRadioButtonWaterEra1);
        GutilPCU.setGuiLang(jRadioButtonSummary0);
        GutilPCU.setGuiLang(jRadioButtonSummary1);
        jRadioButtonWaterDrink0.setText(GutilPCU.getTextBundle(jRadioButtonWaterDrink0.getText()));
        jRadioButtonWaterDrink1.setText(GutilPCU.getTextBundle(jRadioButtonWaterDrink1.getText()));
        jRadioButtonWaterUse0.setText(GutilPCU.getTextBundle(jRadioButtonWaterUse0.getText()));
        jRadioButtonWaterUse1.setText(GutilPCU.getTextBundle(jRadioButtonWaterUse1.getText()));
        jRadioButtonToilet0.setText(GutilPCU.getTextBundle(jRadioButtonToilet0.getText()));
        jRadioButtonToilet1.setText(GutilPCU.getTextBundle(jRadioButtonToilet1.getText()));
        jRadioButtonGarbage0.setText(GutilPCU.getTextBundle(jRadioButtonGarbage0.getText()));
        jRadioButtonGarbage1.setText(GutilPCU.getTextBundle(jRadioButtonGarbage1.getText()));
        jRadioButtonWaterEra0.setText(GutilPCU.getTextBundle(jRadioButtonWaterEra0.getText()));
        jRadioButtonWaterEra1.setText(GutilPCU.getTextBundle(jRadioButtonWaterEra1.getText()));
        jRadioButtonHomeDura0.setText(GutilPCU.getTextBundle(jRadioButtonHomeDura0.getText()));
        jRadioButtonHomeDura1.setText(GutilPCU.getTextBundle(jRadioButtonHomeDura1.getText()));
        jRadioButtonHomeCare0.setText(GutilPCU.getTextBundle(jRadioButtonHomeCare0.getText()));
        jRadioButtonHomeCare1.setText(GutilPCU.getTextBundle(jRadioButtonHomeCare1.getText()));
        jRadioButtonHomeLight0.setText(GutilPCU.getTextBundle(jRadioButtonHomeLight0.getText()));
        jRadioButtonHomeLight1.setText(GutilPCU.getTextBundle(jRadioButtonHomeLight1.getText()));
        jRadioButtonHomeClean0.setText(GutilPCU.getTextBundle(jRadioButtonHomeClean0.getText()));
        jRadioButtonHomeClean1.setText(GutilPCU.getTextBundle(jRadioButtonHomeClean1.getText()));
        jRadioButtonHomeVen0.setText(GutilPCU.getTextBundle(jRadioButtonHomeVen0.getText()));
        jRadioButtonHomeVen1.setText(GutilPCU.getTextBundle(jRadioButtonHomeVen1.getText()));
        jRadioButtonPublicHealth0.setText(GutilPCU.getTextBundle(jRadioButtonPublicHealth0.getText()));
        jRadioButtonPublicHealth1.setText(GutilPCU.getTextBundle(jRadioButtonPublicHealth1.getText()));
        jRadioButtonMixFood0.setText(GutilPCU.getTextBundle(jRadioButtonMixFood0.getText()));
        jRadioButtonMixFood1.setText(GutilPCU.getTextBundle(jRadioButtonMixFood1.getText()));
        jRadioButtonFoodVessel0.setText(GutilPCU.getTextBundle(jRadioButtonFoodVessel0.getText()));
        jRadioButtonFoodVessel1.setText(GutilPCU.getTextBundle(jRadioButtonFoodVessel1.getText()));
        jRadioButtonFoodCover0.setText(GutilPCU.getTextBundle(jRadioButtonFoodCover0.getText()));
        jRadioButtonFoodCover1.setText(GutilPCU.getTextBundle(jRadioButtonFoodCover1.getText()));
        jRadioButtonWash0.setText(GutilPCU.getTextBundle(jRadioButtonWash0.getText()));
        jRadioButtonWash1.setText(GutilPCU.getTextBundle(jRadioButtonWash1.getText()));
        jRadioButtonKeep0.setText(GutilPCU.getTextBundle(jRadioButtonKeep0.getText()));
        jRadioButtonKeep1.setText(GutilPCU.getTextBundle(jRadioButtonKeep1.getText()));
        jRadioButtonKitchenGarbage0.setText(GutilPCU.getTextBundle(jRadioButtonKitchenGarbage0.getText()));
        jRadioButtonKitchenGarbage1.setText(GutilPCU.getTextBundle(jRadioButtonKitchenGarbage1.getText()));
        jRadioButtonKitchenClean0.setText(GutilPCU.getTextBundle(jRadioButtonKitchenClean0.getText()));
        jRadioButtonKitchenClean1.setText(GutilPCU.getTextBundle(jRadioButtonKitchenClean1.getText()));
        jRadioButtonFoodStand0.setText(GutilPCU.getTextBundle(jRadioButtonFoodStand0.getText()));
        jRadioButtonFoodStand1.setText(GutilPCU.getTextBundle(jRadioButtonFoodStand1.getText()));
        jRadioButtonUseIodine0.setText(GutilPCU.getTextBundle(jRadioButtonUseIodine0.getText()));
        jRadioButtonUseIodine1.setText(GutilPCU.getTextBundle(jRadioButtonUseIodine1.getText()));
        jRadioButtonSaltIodine0.setText(GutilPCU.getTextBundle(jRadioButtonSaltIodine0.getText()));
        jRadioButtonSaltIodine1.setText(GutilPCU.getTextBundle(jRadioButtonSaltIodine1.getText()));
        jRadioButtonProductIodine0.setText(GutilPCU.getTextBundle(jRadioButtonProductIodine0.getText()));
        jRadioButtonProductIodine1.setText(GutilPCU.getTextBundle(jRadioButtonProductIodine1.getText()));
        jRadioButtonRat0.setText(GutilPCU.getTextBundle(jRadioButtonRat0.getText()));
        jRadioButtonRat1.setText(GutilPCU.getTextBundle(jRadioButtonRat1.getText()));
        jRadioButtonCockroach0.setText(GutilPCU.getTextBundle(jRadioButtonCockroach0.getText()));
        jRadioButtonCockroach1.setText(GutilPCU.getTextBundle(jRadioButtonCockroach1.getText()));
        jRadioButtonFly0.setText(GutilPCU.getTextBundle(jRadioButtonFly0.getText()));
        jRadioButtonFly1.setText(GutilPCU.getTextBundle(jRadioButtonFly1.getText()));
        jRadioButtonSummary0.setText(GutilPCU.getTextBundle(jRadioButtonSummary0.getText()));
        jRadioButtonSummary1.setText(GutilPCU.getTextBundle(jRadioButtonSummary1.getText()));
        jRadioButtonPet0.setText(GutilPCU.getTextBundle(jRadioButtonPet0.getText()));
        jRadioButtonPet1.setText(GutilPCU.getTextBundle(jRadioButtonPet1.getText()));
        jRadioButtonUse0.setText(GutilPCU.getTextBundle(jRadioButtonUse0.getText()));
        jRadioButtonUse1.setText(GutilPCU.getTextBundle(jRadioButtonUse1.getText()));
        jRadioAnimalControl0.setText(GutilPCU.getTextBundle(jRadioAnimalControl0.getText()));
        jRadioAnimalControl1.setText(GutilPCU.getTextBundle(jRadioAnimalControl1.getText()));
        jRadioButtonInZone.setText(GutilPCU.getTextBundle(jRadioButtonInZone.getText()));
        jRadioButtonOutZone.setText(GutilPCU.getTextBundle(jRadioButtonOutZone.getText()));
        /*TitledBorder*/
        
        GutilPCU.JPanelLabler(jPanelHome);
        GutilPCU.JPanelLabler(jPanelSubHome);
        //GutilPCU.JPanelLabler(jPanelDetail);  
        GutilPCU.JPanelLabler(jPanelQuertionaire);
        
        
        GutilPCU.JPanelLabler(jPanelHomeDetail);
        GutilPCU.JPanelLabler(jPanelFamily);
        GutilPCU.JPanelLabler(jPanelHomeWater);
        GutilPCU.JPanelLabler(jPanelHomeStandard);
        GutilPCU.JPanelLabler(jPanelFoodStandard);
        GutilPCU.JPanelLabler(jPanelBugControl);
        GutilPCU.JPanelLabler(jPanelHerb);
        GutilPCU.JPanelLabler(jPanelPet);
        GutilPCU.JPanelLabler(jPanelHealthCenterDetail);
        
        /*Tab*/
        GutilPCU.getTextBundleTab(0,jTabbedPaneHomeDatail);
        GutilPCU.getTextBundleTab(1,jTabbedPaneHomeDatail);
        GutilPCU.getTextBundleTab(2,jTabbedPaneHomeDatail);
        GutilPCU.getTextBundleTab(3,jTabbedPaneHomeDatail);
        GutilPCU.getTextBundleTab(0,jTabbedPaneQuestionaire);
        GutilPCU.getTextBundleTab(1,jTabbedPaneQuestionaire);
        GutilPCU.getTextBundleTab(2,jTabbedPaneQuestionaire);
        GutilPCU.getTextBundleTab(3,jTabbedPaneQuestionaire);       
        /*CheckBox*/
        jCheckBoxByVillage.setText(GutilPCU.getTextBundle(jCheckBoxByVillage.getText()));
    }
    
    public void setDefaultComboBox()
    {
        if(theSystemControl.theHospital_Site != null)
            {      
                Gutil.setGuiData(jComboBoxChungwat,theSystemControl.theHospital_Site.changwat);
                
                if(theSystemControl.theHospital_Site.changwat !=null)
                {   
                    Gutil.setGuiData(jComboBoxAmphur,theSystemControl.theHospital_Site.amphor);
                    
                }   
                if(theSystemControl.theHospital_Site.amphor !=null)
                {    
                    Gutil.setGuiData(jComboBoxTambol,theSystemControl.theHospital_Site.tambon);
                    
                }
                  
            }
            else
            {
                theUS.setStatus( "ตาราง Site ไม่มีข้อมูล โปรแกรมจะกำหนดที่อยู่เป็นจังหวัดภูเก็ตให้แทน",UpdateStatus.WARNING);
                Gutil.setGuiData(jComboBoxChungwat,"830000");              
                Gutil.setGuiData(jComboBoxAmphur,"830100");                
                Gutil.setGuiData(jComboBoxTambol,"830104");
            }
    }
     
    private void selectAmpur()
    {   
        String ampur = ComboboxModel.getCodeComboBox(jComboBoxAmphur);
        changwat = ComboboxModel.getCodeComboBox(jComboBoxChungwat);
        
        vTambal = theAllComboBoxControl.listAddressTmp(changwat,ampur);
        cftm.code = "";
        cftm.name = GutilPCU.getTextBundle("NotFix");
        if(vTambal==null)
        {
            vTambal = new Vector();
            vTambal.add(cftm);
        }
        ComboboxModel.initComboBox(jComboBoxTambol,vTambal); 
        //ComboboxModel.initComboBox(jComboBoxTambol,theAllComboBoxControl.listAddressTmp(changwat,ampur)); 
        
        changwat =null;
        ampur = null;
    }
    
    private void selectChangwat()
    {   
        changwat = ComboboxModel.getCodeComboBox(jComboBoxChungwat);
        ComboboxModel.initComboBox(jComboBoxAmphur,theAllComboBoxControl.listAddressAmp(changwat) ); 
                
        changwat = null;
    }
   
    
    public void clearGuiHerb()
    {   jTextFieldHerbName.setText("");
    }
    
    private void clearTableHerb()
    {       
        TaBleModel tm ;        
        tm= new TaBleModel(null,0);
        jTableHerb.setModel(tm);          
    }
     
    public void clearGuiPet()
    {
       jTextFieldPetName.setText("");
       Gutil.setGuiData(jComboBoxPetType,"7220000000001");
       Gutil.setGuiData(jComboBoxPetSex,"1");
       dateComboBoxPetBirthDay.setText("");
       dateComboBoxBirthDay.setText("") ;
       dateComboBoxVaccineDay.setText("");
       
    }
    private void clearTablePet()
    {        
        TaBleModel tm ;        
        tm= new TaBleModel(null,0); 
        jTablePet.setModel(tm);
    }
   
    public void clearGuiSubHome()
    {         
        /*jRadioButton*/ 
        jRadioButtonWaterDrink1.setSelected(true);
        jRadioButtonWaterUse1.setSelected(true);
        jRadioButtonToilet1.setSelected(true);
        jRadioButtonGarbage1.setSelected(true);
        jRadioButtonWaterEra1.setSelected(true);
        jRadioButtonHomeDura1.setSelected(true);
        jRadioButtonHomeCare1.setSelected(true);
        jRadioButtonHomeLight1.setSelected(true);
        jRadioButtonHomeClean1.setSelected(true);
        jRadioButtonHomeVen1.setSelected(true);
        jRadioButtonPublicHealth1.setSelected(true);
        jRadioButtonMixFood0.setSelected(true);
        jRadioButtonFoodVessel1.setSelected(true);
        jRadioButtonFoodCover1.setSelected(true);
        jRadioButtonWash1.setSelected(true);
        jRadioButtonKeep1.setSelected(true);
        jRadioButtonKitchenGarbage1.setSelected(true);
        jRadioButtonKitchenClean1.setSelected(true);
        jRadioButtonFoodStand1.setSelected(true);
        jRadioButtonUseIodine1.setSelected(true);
        jRadioButtonSaltIodine1.setSelected(true);
        jRadioButtonProductIodine1.setSelected(true);
        jRadioButtonRat1.setSelected(true);
        jRadioButtonCockroach1.setSelected(true);
        jRadioButtonFly1.setSelected(true);
        jRadioButtonSummary1.setSelected(true);
        jRadioButtonPet0.setSelected(true);
        jRadioAnimalControl1.setSelected(true);
        
        Gutil.setGuiData(jComboBoxWaterDrink,"1");
        Gutil.setGuiData(jComboBoxWaterUse,"1");
        Gutil.setGuiData(jComboBoxGarbageMethod,"1");
        Gutil.setGuiData(jComboBoxDungControl,"2");
        
           
    }   
    
    private void clearTableSubHome()    
    {         
        TaBleModel tm ;        
        tm= new TaBleModel(null,0); 
        jTableSubHome.setModel(tm);
    }
    
    private void clearTableFamily()    
    {         
        TaBleModel tm ;        
        tm= new TaBleModel(null,0); 
        jTableFamily.setModel(tm);
    }
     
    private void setEnableGuiHome(boolean flag)
    {   jTextFieldVolunteerName.setEnabled(flag);
        this.jTextFieldOwnerName.setEnabled(flag);
        integerTextField1.setEnabled(flag);
        integerTextField2.setEnabled(flag);
        integerTextField3.setEnabled(flag);
        jTextFieldHomeNumber.setEnabled(flag);        
        jTextFieldRoad.setEnabled(flag);
        integerTextFieldQuantity.setEnabled(flag);
        jTextFieldVolunteer.setEnabled(flag);
        jTextFieldOwnerName.setEnabled(flag);       
        jComboBoxHomeCharacter.setEnabled(flag);
        jComboBoxCommunity.setEnabled(flag);
        jButtonDelHome.setEnabled(flag);
        jButtonSaveHome.setEnabled(flag);
        jRadioButtonInZone.setEnabled(flag);
        jRadioButtonOutZone.setEnabled(flag);
        jComboBoxVillage1.setEnabled(flag);
        
        jButtonAddPatient.setEnabled(flag);
        jButtonAddHerb.setEnabled(flag);
        jButtonSaveHerb.setEnabled(flag);
        jButtonAddPet.setEnabled(flag);
        jButtonSavePet.setEnabled(flag);
        jTextFieldHerbName.setEnabled(flag);
        jRadioButtonUse0.setEnabled(flag);
        jRadioButtonUse1.setEnabled(flag);
        jComboBoxPetType.setEnabled(flag);
        jComboBoxPetSex.setEnabled(flag);
        jTextFieldPetName.setEnabled(flag);
        dateComboBoxPetBirthDay.setEnabled(flag);
        dateComboBoxBirthDay.setEnabled(flag);
        dateComboBoxVaccineDay.setEnabled(flag);
    }
     
    private void setEnableGuiSubHome(boolean flag)
    {   jRadioButtonWaterDrink1.setEnabled(flag);
        jRadioButtonWaterUse1.setEnabled(flag);
        jRadioButtonToilet1.setEnabled(flag);
        jRadioButtonGarbage1.setEnabled(flag);
        jRadioButtonWaterEra1.setEnabled(flag);
        jRadioButtonHomeDura1.setEnabled(flag);
        jRadioButtonHomeCare1.setEnabled(flag);
        jRadioButtonHomeLight1.setEnabled(flag);
        jRadioButtonHomeClean1.setEnabled(flag);
        jRadioButtonHomeVen1.setEnabled(flag);
        jRadioButtonPublicHealth1.setEnabled(flag);
        jRadioButtonMixFood0.setEnabled(flag);
        jRadioButtonFoodVessel1.setEnabled(flag);
        jRadioButtonFoodCover1.setEnabled(flag);
        jRadioButtonWash1.setEnabled(flag);
        jRadioButtonKeep1.setEnabled(flag);
        jRadioButtonKitchenGarbage1.setEnabled(flag);
        jRadioButtonKitchenClean1.setEnabled(flag);
        jRadioButtonFoodStand1.setEnabled(flag);
        jRadioButtonUseIodine1.setEnabled(flag);
        jRadioButtonSaltIodine1.setEnabled(flag);
        jRadioButtonProductIodine1.setEnabled(flag);
        jRadioButtonRat1.setEnabled(flag);
        jRadioButtonCockroach1.setEnabled(flag);
        jRadioButtonFly1.setEnabled(flag);
        jRadioButtonSummary1.setEnabled(flag);
        jRadioButtonPet0.setEnabled(flag);
        jRadioButtonWaterDrink0.setEnabled(flag);
        jRadioButtonWaterUse0.setEnabled(flag);
        jRadioButtonToilet0.setEnabled(flag);
        jRadioButtonGarbage0.setEnabled(flag);
        jRadioButtonWaterEra0.setEnabled(flag);
        jRadioButtonHomeDura0.setEnabled(flag);
        jRadioButtonHomeCare0.setEnabled(flag);
        jRadioButtonHomeLight0.setEnabled(flag);
        jRadioButtonHomeClean0.setEnabled(flag);
        jRadioButtonHomeVen0.setEnabled(flag);
        jRadioButtonPublicHealth0.setEnabled(flag);
        jRadioButtonMixFood1.setEnabled(flag);
        jRadioButtonFoodVessel0.setEnabled(flag);
        jRadioButtonFoodCover0.setEnabled(flag);
        jRadioButtonWash0.setEnabled(flag);
        jRadioButtonKeep0.setEnabled(flag);
        jRadioButtonKitchenGarbage0.setEnabled(flag);
        jRadioButtonKitchenClean0.setEnabled(flag);
        jRadioButtonFoodStand0.setEnabled(flag);
        jRadioButtonUseIodine0.setEnabled(flag);
        jRadioButtonSaltIodine0.setEnabled(flag);
        jRadioButtonProductIodine0.setEnabled(flag);
        jRadioButtonRat0.setEnabled(flag);
        jRadioButtonCockroach0.setEnabled(flag);
        jRadioButtonFly0.setEnabled(flag);
        jRadioButtonSummary0.setEnabled(flag);
        jRadioButtonPet1.setEnabled(flag);        
        jComboBoxWaterDrink.setEnabled(flag);        
        jComboBoxWaterUse.setEnabled(flag);
        jComboBoxGarbageMethod.setEnabled(flag);
        jComboBoxDungControl.setEnabled(flag);
        jButtonDelSubHome.setEnabled(flag);
        jButtonSaveSubHome.setEnabled(flag);
        jButtonAddSubHome.setEnabled(flag);
        jRadioAnimalControl0.setEnabled(flag);
        jRadioAnimalControl1.setEnabled(flag);
    }
    
    private void setEnableGuiFamily(boolean flag)
    {        
        jButtonUpdatePatient.setEnabled(flag);
        jButtonDelPatient.setEnabled(flag);
        jButtonAddPatient.setEnabled(flag);
        jButtonStatus.setEnabled(flag);
        setEnabledMoveHomeButton(flag);
    }
      
    private void setEnableGuiHerb(boolean flag)
    {
        jTextFieldHerbName.setEnabled(flag);
        jRadioButtonUse0.setEnabled(flag);
        jRadioButtonUse1.setEnabled(flag);
        jButtonDelHerb.setEnabled(flag);
        jButtonSaveHerb.setEnabled(flag);
        jButtonAddHerb.setEnabled(flag);
    }
      
    private void setEnableGuiPet(boolean flag)
    {
        jComboBoxPetType.setEnabled(flag);        
        jComboBoxPetSex.setEnabled(flag);
        jTextFieldPetName.setEnabled(flag);
        dateComboBoxPetBirthDay.setEnabled(flag);
        dateComboBoxBirthDay.setEnabled(flag);
        dateComboBoxVaccineDay.setEnabled(flag);
        jButtonDelPet.setEnabled(flag);
        jButtonSavePet.setEnabled(flag);
        jButtonAddPet.setEnabled(flag);
    }
    
    private void setObjectToData() 
    {
       
        if(theWaterEradicate.home_water.equals("1"))            
            jRadioButtonWaterDrink1.setSelected(true);        
        else            
            jRadioButtonWaterDrink0.setSelected(true);
        
        if(theWaterEradicate.water_supply.equals("1"))            
            jRadioButtonWaterUse1.setSelected(true);        
        else            
            jRadioButtonWaterUse0.setSelected(true); 
        
        if(theWaterEradicate.toilet.equals("1"))            
            jRadioButtonToilet1.setSelected(true);        
        else            
            jRadioButtonToilet0.setSelected(true);
        
        if(theWaterEradicate.garbage.equals("1"))            
            jRadioButtonGarbage1.setSelected(true);        
        else            
            jRadioButtonGarbage0.setSelected(true);
        
        if(theWaterEradicate.eradicate.equals("1"))            
            jRadioButtonWaterEra1.setSelected(true);        
        else            
            jRadioButtonWaterEra0.setSelected(true);
        
        if(theHouseStandard.durability.equals("1"))            
            jRadioButtonHomeDura1.setSelected(true);        
        else            
            jRadioButtonHomeDura0.setSelected(true);
        
        if(theHouseStandard.care.equals("1"))            
            jRadioButtonHomeCare1.setSelected(true);        
        else            
            jRadioButtonHomeCare0.setSelected(true);
        
        if(theHouseStandard.light.equals("1"))            
            jRadioButtonHomeLight1.setSelected(true);        
        else            
            jRadioButtonHomeLight0.setSelected(true);
        
        if(theHouseStandard.cleanness.equals("1"))            
            jRadioButtonHomeClean1.setSelected(true);        
        else            
            jRadioButtonHomeClean0.setSelected(true);
        
        if(theHouseStandard.ventilation.equals("1"))            
            jRadioButtonHomeVen1.setSelected(true);        
        else            
            jRadioButtonHomeVen0.setSelected(true);
        
        if(theHouseStandard.house_standard.equals("1"))            
            jRadioButtonPublicHealth1.setSelected(true);        
        else            
            jRadioButtonPublicHealth0.setSelected(true);
        
        if(theFoodStandard.mixture_food.equals("1"))            
            jRadioButtonMixFood1.setSelected(true);        
        else            
            jRadioButtonMixFood0.setSelected(true);
        
        if(theFoodStandard.food_vessel.equals("1"))            
            jRadioButtonFoodVessel1.setSelected(true);        
        else            
            jRadioButtonFoodVessel0.setSelected(true);
        
        if(theFoodStandard.food_cover.equals("1"))            
            jRadioButtonFoodCover1.setSelected(true);        
        else            
            jRadioButtonFoodCover0.setSelected(true);
        
        if(theFoodStandard.food_wash.equals("1"))            
            jRadioButtonWash1.setSelected(true);        
        else            
            jRadioButtonWash0.setSelected(true);
        
        if(theFoodStandard.food_keep.equals("1"))            
            jRadioButtonKeep1.setSelected(true);        
        else            
            jRadioButtonKeep0.setSelected(true);
        
        if(theFoodStandard.kitchen_garbage.equals("1"))            
            jRadioButtonKitchenGarbage1.setSelected(true);        
        else            
            jRadioButtonKitchenGarbage0.setSelected(true);
        
        if(theFoodStandard.kitchen_cleanness.equals("1"))            
            jRadioButtonKitchenClean1.setSelected(true);        
        else            
            jRadioButtonKitchenClean0.setSelected(true);
        
        if(theFoodStandard.food_standard.equals("1"))            
            jRadioButtonFoodStand1.setSelected(true);        
        else            
            jRadioButtonFoodStand0.setSelected(true);
        
        if(theFoodStandard.iodine.equals("1"))            
            jRadioButtonUseIodine1.setSelected(true);        
        else            
            jRadioButtonUseIodine0.setSelected(true);
        
        if(theFoodStandard.salt_iodine.equals("1"))            
            jRadioButtonSaltIodine1.setSelected(true);        
        else            
            jRadioButtonSaltIodine0.setSelected(true);
        
        if(theFoodStandard.product_iodine.equals("1"))            
            jRadioButtonProductIodine1.setSelected(true);        
        else            
            jRadioButtonProductIodine0.setSelected(true);
        
        if(theBugCarrier.rat_control.equals("1"))            
            jRadioButtonRat1.setSelected(true);        
        else            
            jRadioButtonRat0.setSelected(true);
        
        if(theBugCarrier.cockroach_control.equals("1"))            
            jRadioButtonCockroach1.setSelected(true);        
        else            
            jRadioButtonCockroach0.setSelected(true);
        
        if(theBugCarrier.fly_control.equals("1"))            
            jRadioButtonFly1.setSelected(true);        
        else            
            jRadioButtonFly0.setSelected(true);
        
        if(theBugCarrier.bug_control.equals("1"))            
            jRadioButtonSummary1.setSelected(true);        
        else            
            jRadioButtonSummary0.setSelected(true);
                
        if(theBugCarrier.home_pet.equals("1"))            
            jRadioButtonPet1.setSelected(true);        
        else            
            jRadioButtonPet0.setSelected(true); 
        
        if(theBugCarrier.animal_control.equals("1"))            
            jRadioAnimalControl1.setSelected(true);        
        else            
            jRadioAnimalControl0.setSelected(true);        
    }
     
    private void setDataToObject() 
    {        
        if(jRadioButtonWaterDrink1.isSelected())
            theWaterEradicate.home_water = "1";        
        else            
            theWaterEradicate.home_water = "0";   
        
        if(jRadioButtonWaterUse1.isSelected())
            theWaterEradicate.water_supply = "1";        
        else            
            theWaterEradicate.water_supply = "0";        
        
        if(jRadioButtonToilet1.isSelected())
            theWaterEradicate.toilet = "1";        
        else            
            theWaterEradicate.toilet = "0";        
        
        if(jRadioButtonGarbage1.isSelected())
            theWaterEradicate.garbage = "1";        
        else            
            theWaterEradicate.garbage = "0";        
        
        if(jRadioButtonWaterEra1.isSelected())
            theWaterEradicate.eradicate = "1";        
        else            
            theWaterEradicate.eradicate = "0";        
        
        if(jRadioButtonHomeDura1.isSelected())
            theHouseStandard.durability = "1";        
        else            
            theHouseStandard.durability = "0";        
        
        if(jRadioButtonHomeCare1.isSelected())
            theHouseStandard.care = "1";        
        else            
            theHouseStandard.care = "0";                
        
        if(jRadioButtonHomeLight1.isSelected())
            theHouseStandard.light = "1";        
        else            
            theHouseStandard.light = "0";        
        
        if(jRadioButtonHomeClean1.isSelected())
            theHouseStandard.cleanness = "1";       
        else            
            theHouseStandard.cleanness = "0";        
        
        if(jRadioButtonHomeVen1.isSelected())
            theHouseStandard.ventilation = "1";        
        else            
            theHouseStandard.ventilation = "0";        
        
        if(jRadioButtonPublicHealth1.isSelected())
            theHouseStandard.house_standard = "1";        
        else            
            theHouseStandard.house_standard = "0";        
        
        if(jRadioButtonMixFood1.isSelected())
            theFoodStandard.mixture_food = "1";        
        else            
            theFoodStandard.mixture_food = "0";        
        
        if(jRadioButtonFoodVessel1.isSelected())
            theFoodStandard.food_vessel = "1";        
        else            
            theFoodStandard.food_vessel = "0";        
        
        if(jRadioButtonFoodCover1.isSelected())
            theFoodStandard.food_cover = "1";        
        else            
            theFoodStandard.food_cover = "0";        
        
        if(jRadioButtonWash1.isSelected())
            theFoodStandard.food_wash = "1";        
        else            
            theFoodStandard.food_wash = "0";        
        
        if(jRadioButtonKeep1.isSelected())
            theFoodStandard.food_keep = "1";        
        else            
            theFoodStandard.food_keep = "0";        
        
        if(jRadioButtonKitchenGarbage1.isSelected())
            theFoodStandard.kitchen_garbage = "1";        
        else            
            theFoodStandard.kitchen_garbage = "0";        
        
        if(jRadioButtonKitchenClean1.isSelected())
            theFoodStandard.kitchen_cleanness = "1";        
        else            
            theFoodStandard.kitchen_cleanness = "0";        
        
        if(jRadioButtonFoodStand1.isSelected())
            theFoodStandard.food_standard = "1";        
        else            
            theFoodStandard.food_standard = "0";        
        
        if(jRadioButtonUseIodine1.isSelected())
            theFoodStandard.iodine = "1";        
        else            
            theFoodStandard.iodine = "0";        
        
        if(jRadioButtonSaltIodine1.isSelected())
            theFoodStandard.salt_iodine = "1";        
        else            
            theFoodStandard.salt_iodine = "0";        
        
        if(jRadioButtonProductIodine1.isSelected())
            theFoodStandard.product_iodine = "1";        
        else            
            theFoodStandard.product_iodine = "0";        
        
        if(jRadioButtonRat1.isSelected())
            theBugCarrier.rat_control = "1";        
        else            
            theBugCarrier.rat_control = "0";        
        
        if(jRadioButtonCockroach1.isSelected())
            theBugCarrier.cockroach_control = "1";        
        else            
            theBugCarrier.cockroach_control = "0";        
        
        if(jRadioButtonFly1.isSelected())
            theBugCarrier.fly_control = "1";        
        else            
            theBugCarrier.fly_control = "0";        
        
        if(jRadioButtonSummary1.isSelected())
            theBugCarrier.bug_control = "1";        
        else            
            theBugCarrier.bug_control = "0";        
        
        if(jRadioButtonPet1.isSelected())
            theBugCarrier.home_pet = "1";        
        else            
            theBugCarrier.home_pet = "0";        
        
        if(jRadioAnimalControl1.isSelected())
            theBugCarrier.animal_control = "1";        
        else            
            theBugCarrier.animal_control = "0";
    }
    private void setDataToObjectHerb()
    {
        if(jRadioButtonUse1.isSelected())
            theHerb.herb_use = "1";        
        else            
            theHerb.herb_use = "0";
    }
    private void saveWaterEradicate() 
    {
        /***User***/ 
        theWaterEradicate.sub_home_id = theSubHome.getObjectId(); 
        theWaterEradicate.type_id = Gutil.getGuiData(jComboBoxWaterDrink);
        theWaterEradicate.supply_id = Gutil.getGuiData(jComboBoxWaterUse);
        theWaterEradicate.garbage_method_id = Gutil.getGuiData(jComboBoxGarbageMethod);   
        theHomeControl.saveWaterEradicate(theWaterEradicate);
        theWaterEradicate=null;
    }
     
    private void saveHouseStandard()
    {   theHouseStandard.sub_home_id = theSubHome.getObjectId();
        theHomeControl.saveHouseStandard(theHouseStandard); 
        theHouseStandard=null; 
    }
    
    private void saveBugCarrier()
    {   theBugCarrier.sub_home_id = theSubHome.getObjectId(); 
        theBugCarrier.dung_control_id = Gutil.getGuiData(jComboBoxDungControl);
        theHomeControl.saveBugCarrier(theBugCarrier);          
        theBugCarrier=null;
    }
    
    private void saveFoodStandard() 
    {   theFoodStandard.sub_home_id = theSubHome.getObjectId(); 
        theHomeControl.saveFoodStandard(theFoodStandard); 
        theFoodStandard=null;
    }
    
    /**
     * @author henbe
     * @deprecated ผิด pattern ต้องมี updateGO มารับก่อน
     * แล้วค่อยเรียก control save home รวมทั้งเงื่อนไขการเช็คอยู่ผิดที่ต้องอยู่ภายในฟังชันของ control เท่านั้น
     **/
    public Home getHome()
    {
        if(theHome == null) 
            theHome = new Home();
        
        theHome.home_number = getHomeNumber();
        theHome.home_house = jTextFieldHomeNumber.getText();
        theHome.home_moo = jTextFieldMoo.getText();
        theHome.home_road = jTextFieldRoad.getText();
        theHome.family = integerTextFieldQuantity.getText();        
        theHome.volunteer = jTextFieldVolunteer.getText();
        theHome.v_name = jTextFieldVolunteerName.getText();
        theHome.owner = jTextFieldOwnerName.getText();
        theHome.owner_number = jTextFieldOwnerNumber.getText();
        theHome.charactor_id = Gutil.getGuiData(jComboBoxHomeCharacter);
        theHome.community_charac_id = Gutil.getGuiData(jComboBoxCommunity);
        theHome.charactor_id = Gutil.getGuiData(jComboBoxHomeCharacter);
        theHome.zone = PcuAnswer.One();
        if(jRadioButtonOutZone.isSelected())
           theHome.zone = PcuAnswer.Zero();
        theHome.village_id = Gutil.getGuiData(jComboBoxVillage1);
        return theHome;
    }
    
    private void setTablePatient()    
    {    
          if(theHome!=null)
          {
              search = theHome.getObjectId();
              vFamily =  theHomeControl.listFamilyByHomeId(search);              
              String[] col = {
                              GutilPCU.getTextBundle("Name") + "-" + GutilPCU.getTextBundle("SurName"),
                              GutilPCU.getTextBundle("Age"),
                              GutilPCU.getTextBundle("Sex"),
                              GutilPCU.getTextBundle("PID"),
                              GutilPCU.getTextBundle("PatientStatus"),
                              GutilPCU.getTextBundle("Position")};
                              
             Family familyTemp = new Family(); 
             TaBleModel tm ;
             String status = "";          
             if(vFamily != null)
             {   
                tm = new TaBleModel(col,vFamily.size());
                for(int i=0, size=vFamily.size(); i<size; i++)
                {                  
                    familyTemp = (Family)vFamily.get(i);
                    if(familyTemp.status_id.equals("1"))
                        status = GutilPCU.getTextBundle("OwnerHome");
                    else
                        status = GutilPCU.getTextBundle("Away");
                    
                    String age = DateUtil.calculateAge(familyTemp.patient_birthday
                        ,pcuobject.getCurrentDateTime());
                    tm.setValueAt(theAllComboBoxControl.getValueOfPrefix(familyTemp.f_prefix_id)
                        + "  " + familyTemp.patient_name + "  " + familyTemp.patient_last_name,i,0);
                    //tm.setValueAt(familyTemp.patient_name,i,1);
                    //tm.setValueAt(familyTemp.patient_last_name,i,2);
                    tm.setValueAt(age+(" ")+GutilPCU.getTextBundle("Year"),i,1);
                    tm.setValueAt(familyTemp.f_sex_id,i,2);
                    tm.setValueAt(familyTemp.pid,i,3);
                    //tm.setValueAt(familyTemp.hn,i,4);
                    tm.setValueAt(familyTemp.discharge_status_id,i,4);
                    tm.setValueAt(status,i,5);
                }                
             }
             else
             {               
                tm= new TaBleModel(col,0);
                jButtonDelPatient.setEnabled(false);
                jButtonStatus.setEnabled(false);
                jButtonUpdatePatient.setEnabled(false);
             } 
             familyTemp = null;
             jTableFamily.setModel(tm);
             jTableFamily.getColumnModel().getColumn(0).setPreferredWidth(130);
             //jTableFamily.getColumnModel().getColumn(1).setPreferredWidth(60);
             //jTableFamily.getColumnModel().getColumn(2).setPreferredWidth(60);
             jTableFamily.getColumnModel().getColumn(1).setPreferredWidth(4); 
             jTableFamily.getColumnModel().getColumn(1).setCellRenderer(ColumnTableRenderer.getRendererCenter());
             jTableFamily.getColumnModel().getColumn(2).setPreferredWidth(10);
             jTableFamily.getColumnModel().getColumn(2).setCellRenderer(theCelRendererSexType);
             jTableFamily.getColumnModel().getColumn(3).setPreferredWidth(70);
             
             jTableFamily.getColumnModel().getColumn(4).setPreferredWidth(40);
             jTableFamily.getColumnModel().getColumn(4).setCellRenderer(theCelRendererStatusType);
             jTableFamily.getColumnModel().getColumn(5).setPreferredWidth(23);     
          }
    }
    /**
     *ค้นหาบ้าน และทำการแสดงตารางบ้าน
     *@param -
     *@return void
     *@author jao
     *@modify kingland
     *@date 30/08/2549
     */
    private void setTableHome()
    {   
        
        Constant.println("__PanelHome___________________________setTableHome)");
        search = "";
        search2 = "";
        search = jTextFieldSearchHome.getText();  //รับค่าจาก TextFiledsearch
        search2 = Gutil.getGuiData(jComboBoxVillage); //รับค่าจาก ComboBoxVillage        
        if(!jCheckBoxByVillage.isSelected()) //ถ้ามีการเลือกแสดงบ้านทั้งหมดทุกหมู่บ้าน
        {   
            vHome =  theHomeControl.listHomeAllVillage(search, false); 
        }
        else
        {   if(!search2.equals(""))
               vHome =  theHomeControl.listHomeByNumber2(search,search2);
        }
        
        String[] col = {GutilPCU.getTextBundle("เลขที่"),
                    GutilPCU.getTextBundle("VillageName")};
        Home homeTemp = new Home(); 
        TaBleModel tm ;
        if(vHome != null)
        {   tm = new TaBleModel(col,vHome.size());
            for(int i=0, size=vHome.size(); i<size; i++)
            {   homeTemp = (Home)vHome.get(i);                
                tm.setValueAt(homeTemp.home_house,i,0); 
                tm.setValueAt(readVillageName(theAllComboBoxControl.listVillage(),homeTemp.village_id),i,1);
            }
        }
        else
        {   tm= new TaBleModel(col,0);
        } 
        jTableHome.setModel(tm);  
        jTableHome.getColumnModel().getColumn(0).setPreferredWidth(70);     /*บ้านเลขที่*/        
        jTableHome.getColumnModel().getColumn(1).setPreferredWidth(120);     /*ชื่อหมู่บ้าน*/        
    }
    
    public String readVillageName(Vector v,String id){
        for(int i=0;i<v.size();i++){
            ComboFix vv = (ComboFix)v.get(i);
            if(vv.code.equals(id))
                return vv.name;
        }
        return "";
    }
    private void selectHome(int row)
    {     
        if(row==-2)
        {/*user เป็นผู้เลือกเอง*/
            rowHome = jTableHome.getSelectedRow();            
        }
        else
        {/*user บันทึก จะ select record ที่เพิ่งเพิ่ม*/
            rowHome = row;            
        }
        setHome((Home)vHome.get(rowHome));
    }
    
    public void setHome(Home home)
    {  
        theHome = home;
        if(theHome==null){
            integerTextField1.setText("");
            integerTextField2.setText("");
            integerTextField3.setText("");
            jTextFieldHomeNumber.setText("");        
            jTextFieldRoad.setText("");
            integerTextFieldQuantity.setText("");
            jTextFieldVolunteer.setText("");
            jTextFieldVolunteerName.setText("");
            jTextFieldOwnerName.setText("");
            jTextFieldOwnerNumber.setText("");
            Gutil.setGuiData(jComboBoxHomeCharacter,"01");
            Gutil.setGuiData(jComboBoxCommunity,"01");
            jRadioButtonInZone.setSelected(true);   
            return;
        }
        //this.jTextFieldHomeOrder.setText(this.theHome.home_number);        
        jTextFieldHomeNumber.setText(theHome.home_house);
        jTextFieldMoo.setText(theHome.home_moo);
        jTextFieldRoad.setText(theHome.home_road);
        integerTextFieldQuantity.setText(theHome.family);
        jTextFieldVolunteer.setText(theHome.volunteer);
        jTextFieldVolunteerName.setText(theHome.v_name);
        jTextFieldOwnerName.setText(theHome.owner);
        jTextFieldOwnerNumber.setText(theHome.owner_number);
        ComboboxModel.setCodeComboBox(jComboBoxHomeCharacter,theHome.charactor_id);   
        ComboboxModel.setCodeComboBox(jComboBoxCommunity,theHome.community_charac_id);
        if(theHome.zone.equals(PcuAnswer.Zero()))
        {
            jRadioButtonOutZone.setSelected(true);
        }
        else
        {
            jRadioButtonInZone.setSelected(true);
        }        
        ComboboxModel.setCodeComboBox(jComboBoxVillage1,theHome.village_id); 
        objectToHomeNumber();
        setEnableGuiHome(true);        
        setEnableGuiSubHome(true);
        setEnableGuiPet(true);
        setEnableGuiHerb(true);
        setTableSubHome(theHome);
        setTableHerb();
        setTablePet();
        setTablePatient();
        clearGuiPet();
        clearGuiHerb();
        jButtonDelSubHome.setEnabled(false);        
        jButtonDelPet.setEnabled(false);
        jButtonDelHerb.setEnabled(false);
        jButtonDelHome.setEnabled(true);
        jButtonAddPatient.setEnabled(true);
        jButtonDelPatient.setEnabled(false);
        jButtonUpdatePatient.setEnabled(false);
        jButtonStatus.setEnabled(false);
    }
    /**
     *ลบบ้าน
     *@param -
     *@return void
     *@author jao henbe
     *@modify kingland
     *@date 30/08/2549
     */
    private void deleteHome()
    {   
        boolean person = true;
        Vector vPerson = theHomeControl.listFamilyByHomeId(theHome.getObjectId());
        if(!vPerson.isEmpty()){
            theUS.setStatus(GutilPCU.getTextBundle(
                    "TheHomeHavePerson"),UpdateStatus.WARNING); 
            return;
        }
        int row = jTableHome.getSelectedRow();
        if(row==-1)  {
            theUS.setStatus(GutilPCU.getTextBundle(
                "กรุณาเลือกบ้านที่ต้องการลบ"),UpdateStatus.WARNING); 
            return;
        }
        if(!theUS.confirmBox(GutilPCU.getTextBundle(
                "ConfirmDelete"),UpdateStatus.WARNING)){
            return;
        }
        int result = theHomeControl.deleteHome(theHome);
        if(result > 0)
        {   theHome = null ;
            setHome(null);
            setTableHome();
            setTableSubHome(theHome);
            setTablePet();
            setTableHerb();
            setEnableGuiHome(false);
            setEnableGuiSubHome(false);
        }
    }
    
    private void addHome()
    {
        jTableHome.clearSelection();
        setHome(null);
        clearGuiPet();
        clearGuiHerb();
        clearTablePet();
        clearTableHerb();
        clearTableSubHome();
        theHome = null;
        setEnableGuiHome(true);
        setEnableGuiPet(false);
        setEnableGuiFamily(false);
        setEnableGuiHerb(false);
        setEnableGuiSubHome(false);
        setDefaultComboBox();
        jButtonDelHome.setEnabled(false);
    }     
    private void saveSubHome() 
    {
        
        /***User***/
        if(theSubHome==null)
            theSubHome = new SubHome();
         if(theWaterEradicate==null) 
            theWaterEradicate = new WaterEradicate();
        if(theHouseStandard==null) 
            theHouseStandard = new HouseStandard();
        if(theFoodStandard==null) 
            theFoodStandard = new FoodStandard();
        if(theBugCarrier==null) 
            theBugCarrier = new BugCarrier();
        theSubHome.home_id = theHome.getObjectId();
        /***System***/
        if(theSubHome.getObjectId() != null)
        {        
            theSubHome.staff_modify = staff_id;//theSystemControl.theEmployee.getObjectId();
            theSubHome.modify_date_time = Gutil.getTextCurrentDateTime(theAllComboBoxControl.theConnectionInf);
        }
        else
        {
            theSubHome.staff_record = staff_id;//theSystemControl.theEmployee.getObjectId();
            theSubHome.record_date_time = Gutil.getTextCurrentDateTime(theAllComboBoxControl.theConnectionInf);
        }
        theSubHome.sub_home_active = "1";        
        theSubHome=theHomeControl.saveSubHome(theSubHome);
        setTableSubHome(theHome);
        setDataToObject();
        saveWaterEradicate();
        saveHouseStandard();
        saveFoodStandard();
        saveBugCarrier();
        int lastRow = vSubHome.size()-1;
        if(lastRow!=-1) {
            jTableSubHome.setRowSelectionInterval(lastRow,lastRow);
            selectSubHome(lastRow);
        }
        //setEnableGuiSubHome(false);
        jButtonAddHerb.setEnabled(true);            
     }
    
    /**
     *เซตตารางข้อมูลย่อยบ้าน
     *@param Home ข้อมูลบ้าน
     *@return void
     *@author jao
     *@modify kingland
     *@date 30/08/2549
     */
    private void setTableSubHome(Home home)
    {   if(home!=null)
        {   search = theHome.getObjectId();          
            vSubHome =  theHomeControl.listSubHomeByDate(search);
        }
        else
            vSubHome = null;
        String[] col = {GutilPCU.getTextBundle("No"),
                        GutilPCU.getTextBundle("DateRecord")};
        SubHome subHomeTemp = new SubHome(); 
        TaBleModel tm ;
        if(vSubHome != null)
        {   tm = new TaBleModel(col,vSubHome.size());
            for(int i=0, size=vSubHome.size(); i<size; i++)
            {  subHomeTemp = (SubHome)vSubHome.get(i);
                tm.setValueAt(String.valueOf((i+1)),i,0);
                tm.setValueAt(GutilPCU.changDateToString(subHomeTemp.record_date_time,true),i,1);
            }
        }
        else
        {   tm= new TaBleModel(col,0);
        }        
        subHomeTemp = null;
        jTableSubHome.setModel(tm);                  
        jTableSubHome.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTableSubHome.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTableSubHome.getColumnModel().getColumn(1).setCellRenderer(ColumnTableRenderer.getRendererCenter());
    }
          
    private void selectSubHome(int row)
    {        
        int size = vSubHome.size();
        if(row==-2)
        {/*user เป็นผู้เลือกเอง*/
            rowSubHome = jTableSubHome.getSelectedRow();            
        }
        else
        {/*user บันทึก จะ select record ที่เพิ่งเพิ่ม*/
            rowSubHome = row;            
        }
        theSubHome = (SubHome)vSubHome.get(rowSubHome); 
        searchAllQust();
        setObjectToData();
        
        ComboboxModel.setCodeComboBox(jComboBoxWaterDrink,theWaterEradicate.type_id);
        ComboboxModel.setCodeComboBox(jComboBoxWaterUse,theWaterEradicate.supply_id);
        ComboboxModel.setCodeComboBox(jComboBoxGarbageMethod,theWaterEradicate.garbage_method_id);
        ComboboxModel.setCodeComboBox(jComboBoxDungControl,theBugCarrier.dung_control_id);
        if(size-1 == rowSubHome)
            setEnableGuiSubHome(true);
        else
            setEnableGuiSubHome(false);
        jButtonAddSubHome.setEnabled(true);
        jButtonDelSubHome.setEnabled(true);
    }
        
    private void deleteSubHome()
    {
        theSubHome.staff_cancel = staff_id;//theSystemControl.theEmployee.getObjectId();
        theSubHome.cancel_date_time = Gutil.getTextCurrentDateTime(theAllComboBoxControl.theConnectionInf);
        theSubHome.sub_home_active = "0";        
        theHomeControl.saveSubHome(theSubHome);
        theSubHome = null ;
        clearGuiSubHome();
        setTableSubHome(theHome);
        setEnableGuiSubHome(false);
        jButtonAddSubHome.setEnabled(true);
    }
    
    private void addSubHome()
    {
         jTableSubHome.clearSelection();
         clearGuiSubHome();
         addNewAll();
         setEnableGuiSubHome(true);
     }
    private void searchWater() 
    {   /***ค้นหาข้อมูลการใช้น้ำ***/
        search = theSubHome.getObjectId();        
        vWaterEradicate =  theHomeControl.listWaterBySubHome(search);
        /***แปลงVecterเข้าObject***/
        WaterEradicate waterEradicateTemp = new WaterEradicate(); 
        for(int i=0, size=vWaterEradicate.size(); i<size; i++)
        {
             waterEradicateTemp = (WaterEradicate)vWaterEradicate.get(i);
             theWaterEradicate = waterEradicateTemp ;
        }
     }
     private void searchHouse() 
     {
        /***ค้นข้อมูลสุขาภิบาลบ้าน***/ 
        search = theSubHome.getObjectId();        
        vHouseStandard =  theHomeControl.listHouseBySubHome(search); 
        /***แปลงVecterเข้าObject***/
        HouseStandard houseStandardTemp = new HouseStandard(); 
        for(int i=0, size=vHouseStandard.size(); i<size; i++)
        {
             houseStandardTemp = (HouseStandard)vHouseStandard.get(i);
             theHouseStandard = houseStandardTemp ;
        }
     }    
     
     private void searchFood() 
     {
        /***ค้นข้อมูลสุขาภิบาลอาหาร***/ 
        search = theSubHome.getObjectId();        
        vFoodStandard =  theHomeControl.listFoodBySubHome(search); 
        /***แปลงVecterเข้าObject***/
        FoodStandard foodStandardTemp = new FoodStandard(); 
        for(int i=0, size=vFoodStandard.size(); i<size; i++)
        {
             foodStandardTemp = (FoodStandard)vFoodStandard.get(i);
             theFoodStandard = foodStandardTemp ;
        }
     }
     
     private void searchBugCarrier() 
     {
        
        /***ค้นข้อมูลแมลงนำโรค***/
        search = theSubHome.getObjectId();        
        vBugCarrier =  theHomeControl.listBugCarrierBySubHome(search);  
        /***แปลงVecterเข้าObject***/
        BugCarrier bugCarrierTemp = new BugCarrier(); 
        for(int i=0, size=vBugCarrier.size(); i<size; i++)
        {
             bugCarrierTemp = (BugCarrier)vBugCarrier.get(i);
             theBugCarrier = bugCarrierTemp ;
        }   
     }     
     private void saveHerb() 
     {
        /***User***/        
        theHerb.herb_name = jTextFieldHerbName.getText();
        theHerb.home_id = theHome.getObjectId();
        /***System***/
        if(theHerb.getObjectId() != null)
        {        
            theHerb.staff_modify = staff_id;//theSystemControl.theEmployee.getObjectId();
            theHerb.modify_date_time = Gutil.getTextCurrentDateTime(theAllComboBoxControl.theConnectionInf);
            modifyHerb = true;
        }
        else
        {
            theHerb.staff_record = staff_id;//theSystemControl.theEmployee.getObjectId();
            theHerb.record_date_time = Gutil.getTextCurrentDateTime(theAllComboBoxControl.theConnectionInf);
            modifyHerb = false;
        }
        theHerb.herb_active = "1";           
        theHomeControl.saveHerb(theHerb);
        clearGuiHerb();
        setTableHerb(); 
        if(modifyHerb)
        {
            jTableHerb.setRowSelectionInterval(rowHerb,rowHerb);
            selectHerb(rowHerb);
        }
        else
        {
            int lastRow = vHerb.size()-1;
            if(lastRow!=-1) {
                jTableHerb.setRowSelectionInterval(lastRow,lastRow);
                selectHerb(lastRow);
            }
        }
        //setEnableGuiHerb(false);
        jButtonAddHerb.setEnabled(true);
    } 
    /**
     *ค้นหาสมุนไฟรและสร้างตารางสมุนไฟร
     *@param -
     *@return void
     *@author jao
     *@modify kingland
     *@date 30/08/2549
     */  
    private void setTableHerb()
    {   if(theHome!=null)
        {   search = theHome.getObjectId();
            vHerb =  theHomeControl.listHerbByHome(search); 
        }
        else
            vHerb = null;
        
        String[] col = {GutilPCU.getTextBundle("HerbName"),                    
                    GutilPCU.getTextBundle("DateRecord"),
                    GutilPCU.getTextBundle("ModifyRecord")};                 
                    
        Herb herbTemp = new Herb(); 
        TaBleModel tm ;
        if(vHerb != null)
        {   tm = new TaBleModel(col,vHerb.size());
            for(int i=0, size=vHerb.size(); i<size; i++)
            {   herbTemp = (Herb)vHerb.get(i);
                tm.setValueAt(herbTemp.herb_name,i,0); /*ชื่อสมุนไพร*/
                tm.setValueAt(GutilPCU.changDateToString(herbTemp.record_date_time,true),i,1);   /*วันที่บันทึก*/
                tm.setValueAt(GutilPCU.changDateToString(herbTemp.modify_date_time,true),i,2);   /*วันที่บันทึก*/
            }
        }
        else
        {   tm= new TaBleModel(col,0);
        } 
        herbTemp = null;
        jTableHerb.setModel(tm);
        jTableHerb.getColumnModel().getColumn(1).setCellRenderer(ColumnTableRenderer.getRendererCenter());
        jTableHerb.getColumnModel().getColumn(2).setCellRenderer(ColumnTableRenderer.getRendererCenter());
        jTableHerb.getColumnModel().getColumn(0).setPreferredWidth(150);     /*ชื่อสมุนไพร*/
        jTableHerb.getColumnModel().getColumn(1).setPreferredWidth(100);     /*วันที่บันทึก*/        
        jTableHerb.getColumnModel().getColumn(1).setPreferredWidth(100);
    }
    private void selectHerb(int row)
    {        
        if(row==-2)
        {/*user เป็นผู้เลือกเอง*/
            rowHerb = jTableHerb.getSelectedRow();            
        }
        else
        {/*user บันทึก จะ select record ที่เพิ่งเพิ่ม*/
            rowHerb = row;            
        }
        theHerb = (Herb)vHerb.get(rowHerb); 
        jTextFieldHerbName.setText(theHerb.herb_name);               
        /***ObjectToData(Herb)***/
        if(theHerb.herb_use.equals("1"))            
            jRadioButtonUse1.setSelected(true);        
        else            
            jRadioButtonUse0.setSelected(true);   
        
        jButtonDelHerb.setEnabled(true);
        setEnableGuiHerb(true);
    }
    private void deleteHerb()
    {
        theHerb.staff_cancel = staff_id;//theSystemControl.theEmployee.getObjectId();
        theHerb.cancel_date_time = Gutil.getTextCurrentDateTime(theAllComboBoxControl.theConnectionInf);
        theHerb.herb_active = "0";        
        theHomeControl.saveHerb(theHerb);
        clearGuiHerb();
        setTableHerb();
        setEnableGuiHerb(false);
        jButtonAddHerb.setEnabled(true);
    }
    private void addHerb()
    {   jTableHerb.clearSelection();
        clearGuiHerb();
        addNewAll();
        setEnableGuiHerb(true);
    }     
    
    private void savePet() 
    {
        if(thePet==null)thePet = new Pet();
        checkPetType();
        if(result)
        {
            /***User***/  
            if(thePet==null)thePet = new Pet();
            thePet.home_id = theHome.getObjectId(); 
            thePet.name = jTextFieldPetName.getText();
            if(thePet.pet_id.equals(""))
            {
                thePet.pet_id = Gutil.getGuiData(jComboBoxPetType);
            }
            thePet.sex = Gutil.getGuiData(jComboBoxPetSex);
            thePet.birthday = dateComboBoxPetBirthDay.getText();
            thePet.birth_control_lastdate = dateComboBoxBirthDay.getText();
            thePet.vaccine_lastdate = dateComboBoxVaccineDay.getText();
            /***System***/
            if(thePet.getObjectId() != null)
            {        
                thePet.staff_modify = staff_id;//theSystemControl.theEmployee.getObjectId();
                thePet.modify_date_time = Gutil.getTextCurrentDateTime(theAllComboBoxControl.theConnectionInf);
                modifyPet = true;
            }
            else
            {
                thePet.staff_record = staff_id;//theSystemControl.theEmployee.getObjectId();
                thePet.record_date_time = Gutil.getTextCurrentDateTime(theAllComboBoxControl.theConnectionInf);
                modifyPet = false;
            }
            thePet.pet_active = "1";           
            theHomeControl.savePet(thePet);
            ComboboxModel.initComboBox(jComboBoxPetType,theAllComboBoxControl.listPetType());
            clearGuiPet();
            setTablePet();
            if(modifyPet)
            {
                jTablePet.setRowSelectionInterval(rowPet,rowPet);
                selectPet(rowPet);
            }
            else
            {
                int lastRow = vPet.size()-1;
                if(lastRow!=-1) {
                    jTablePet.setRowSelectionInterval(lastRow,lastRow);
                    selectPet(lastRow);
                }
            }            
            //setEnableGuiPet(false);            
            
        }
    } 
    /**
     *ค้นหาสัตว์เลี้ยงและสร้างตารางสัตว์เลี้ยง
     *@param -
     *@return void
     *@author jao
     *@modify kingland
     *@date 30/08/2549
     */  
    private void setTablePet()
    {   if(theHome!=null)
        {   search = theHome.getObjectId();
            vPet =  theHomeControl.listPetByHome(search);
        }
        else
            vPet = null;
        
        String[] col = {GutilPCU.getTextBundle("PetName"),                    
                    GutilPCU.getTextBundle("PetType"),
                    GutilPCU.getTextBundle("PetSex"),
                    GutilPCU.getTextBundle("PetBirthDay"),
                    GutilPCU.getTextBundle("รับยาคุม"),
                    GutilPCU.getTextBundle("รับวัคซีน")};                 
                    
        Pet petTemp = new Pet(); 
        TaBleModel tm ;
        if(vPet != null)
        {   tm = new TaBleModel(col,vPet.size());
            for(int i=0, size=vPet.size(); i<size; i++)
            {   petTemp = (Pet)vPet.get(i);
                tm.setValueAt(petTemp.name,i,0); /*ชื่อสัตว์เลี้ยง*/
                tm.setValueAt(ComboboxModel.getDescriptionComboBox(jComboBoxPetType,petTemp.pet_id),i,1);
                tm.setValueAt(ComboboxModel.getDescriptionComboBox(jComboBoxPetSex,petTemp.sex),i,2);                 
                tm.setValueAt(GutilPCU.changDateToString(petTemp.birthday,false),i,3);   /*วันที่เกิด*/
                tm.setValueAt(GutilPCU.changDateToString(petTemp.birth_control_lastdate,false),i,4);   /*วันที่ทำหมัน*/
                tm.setValueAt(GutilPCU.changDateToString(petTemp.vaccine_lastdate,false),i,5);   /*วันที่ฉีดวัคซีน*/
            }
        }
        else
        {   tm= new TaBleModel(col,0);
        } 
        petTemp = null;
        jTablePet.setModel(tm);  
        jTablePet.getColumnModel().getColumn(0).setPreferredWidth(150);     /*ชื่อสัตว์เลี้ยง*/
        jTablePet.getColumnModel().getColumn(1).setPreferredWidth(100);     /*ประเภท*/        
        jTablePet.getColumnModel().getColumn(2).setPreferredWidth(50);     /*เพศ*/        
        jTablePet.getColumnModel().getColumn(3).setPreferredWidth(150);     /*วันเกิด*/        
        jTablePet.getColumnModel().getColumn(4).setPreferredWidth(150);     /*วันที่ทำหมัน*/        
        jTablePet.getColumnModel().getColumn(5).setPreferredWidth(150);     /*วันที่ฉีดวัคซีน*/        
    }
    private void selectPet(int row)
    {        
        if(row==-2)
        {/*user เป็นผู้เลือกเอง*/
            rowPet = jTablePet.getSelectedRow();            
        }
        else
        {/*user บันทึก จะ select record ที่เพิ่งเพิ่ม*/
            rowPet = row;            
        }
        thePet = (Pet)vPet.get(rowPet); 
        jTextFieldPetName.setText(thePet.name);
        ComboboxModel.setCodeComboBox(jComboBoxPetType,thePet.pet_id);
        ComboboxModel.setCodeComboBox(jComboBoxPetSex,thePet.sex);
        dateComboBoxPetBirthDay.setText(Gutil.convertFieldDate(thePet.birthday));  
        dateComboBoxBirthDay.setText(Gutil.convertFieldDate(thePet.birth_control_lastdate)) ;
        dateComboBoxVaccineDay.setText(Gutil.convertFieldDate(thePet.vaccine_lastdate));
        setEnableGuiPet(true);
        jButtonDelPet.setEnabled(true);
        jButtonSavePet.setEnabled(true);
        
    }
    
    private void deletePet()
    {
        thePet.staff_cancel = staff_id;//theSystemControl.theEmployee.getObjectId();
        thePet.cancel_date_time = Gutil.getTextCurrentDateTime(theAllComboBoxControl.theConnectionInf);
        thePet.pet_active = "0";        
        theHomeControl.savePet(thePet);        
        clearGuiPet();
        setTablePet();
        setEnableGuiPet(false);
        jButtonAddPet.setEnabled(true);
    }
    
    private void addPet()
    {
        jTablePet.clearSelection();
        clearGuiPet();
        thePet = new Pet();
        setEnableGuiPet(true);
    }
    
    private void selectFamily()
    {
        int row = jTableFamily.getSelectedRow();
        if(row==-1)
            return;
        theFamily = (Family)vFamily.elementAt(row);
    }
    
    /**
     * search ข้อมูลที่เกี่ยวกับแบบสอบถาม
     * @author jao
     */
    private void searchAllQust()
    {
         searchWater();
         searchHouse();
         searchFood();
         searchBugCarrier();
    }
    
    /**
     * clear GUI ที่ไม่เกี่ยวข้องก่อน search
     * @author jao
     */
    private void searchHomeBegin()
    {
        setHome(null);
        clearGuiHerb();
        clearGuiPet();
        clearGuiSubHome();
        clearTableHerb();
        clearTablePet();
        clearTableSubHome();
        clearTableFamily();
        //setEnableGuiHome(false);
        setEnableGuiPet(false);
        setEnableGuiFamily(false);
        setEnableGuiHerb(false);
        setEnableGuiSubHome(false);
    }
    /**
     * new Object ทั้งหมดที่เกี่ยวข้องก่อนที่จะเซฟ
     * @author jao
     */
    private void addNewAll()
    {
         theSubHome = new SubHome();
         theWaterEradicate = new WaterEradicate();
         theHouseStandard = new HouseStandard();
         theFoodStandard = new FoodStandard();
         theBugCarrier = new BugCarrier();
         theHerb = new Herb();
    }
    
    /**
     * ตรวจความถูกต้องของข้อมูล เช่น ค่าว่าง,ค่าที่ซ้ำกัน ก่อนที่จะเซฟ
     * @author jao
     * @deprecated henbe ตั้งชื่อผิดเพราะว่ามันมีการบ้นทึกลงไปด้วยไม่ใช่แค่เช็คอย่างเดียว
     */
    private void saveHome(Home theHome)
    {
        //number = "";
        boolean samevalue=false;
        if(theHome.home_house.equals(""))
        {
            theUS.setStatus(GutilPCU.getTextBundle("FillHouse"),UpdateStatus.WARNING);
            return;
        }
        if(theHome.village_id.equals(""))
        {
            theUS.setStatus(GutilPCU.getTextBundle("VillageIsNull"),UpdateStatus.WARNING);
            return;
        }
        if(vHome == null){
            vHome = new Vector();
        }
        int result = theHomeControl.saveHome(theHome,theHome.village_id,theUS);
        if(result==0)
            return;
//        setHome(theHome);
        setTableHome();
    }
    
    /**
     * ตรวจสอบว่าในตาราง b_health_home_pet_type มีสัตว์
     * ประเภทที่กรอกแล้วหรือยังถ้ายังจะบันทึกลง database ให้อัตโนมัติ
     * @auther jao
     */
    private boolean checkPetType()
    {
        boolean pet = false;
        String sameid = "";
        if(vPetType!=null)
        {
            vPetType = theHomeControl.listPetType();
        }
        thePetType = new PetType();        
        String pettype = null; 
        pettype =((JTextField)(jComboBoxPetType.getEditor()).getEditorComponent()).getText().trim();
        if(!pettype.equals(""))
        {                       
            int number = vPetType.size();
            for(int i=0, size=vPetType.size(); i<size; i++)
            {                            
                thePetType = (PetType)vPetType.get(i);
                if(thePetType.name.equalsIgnoreCase(pettype))
                {
                    pet = true;
                    sameid = thePetType.getObjectId();
                    break;
                }
            }
            if(!pet)
            {   
                Constant.println("DataBase not have this Pettype name insert into database now");
                thePetType = new PetType();                
                thePetType.active = "1";
                thePetType.number= String.valueOf(number+1) ;
                thePetType.name = pettype;
                thePetType = theHomeControl.savePetType(thePetType);
                thePet.pet_id = thePetType.getObjectId();                
                thePetType = null;
                result = true;
            }
            else 
            {   
                Constant.println("DataBase have this Pettype name");
                thePet.pet_id = sameid ;
                result = true;            
            }
        }
         else
         {
            theUS.setStatus(GutilPCU.getTextBundle("InsertPettypeOrSelectFromComboBox"),UpdateStatus.WARNING);
            result = false;
         }
        return result;
    }
    
    private Vector getCutFamily() {
        Vector familyTempV = new Vector();        
        
        familyTempV.clear();
        int[] rows = jTableFamily.getSelectedRows();
                
        for (int i = 0; i < rows.length; i++) {
            familyTempV.add(vFamily.get(rows[i]));
        }
        return familyTempV;
    }
    
    private String getHomeNumber()
    {    
        home_number = "";
        String a = integerTextField1.getText();
        String b = integerTextField2.getText();
        String c = integerTextField3.getText();
        boolean flag = true;
        if(a.length()==4 && b.length()==6 && c.length()==1)
        {
            home_number = a+b+c;
            Constant.println(a+b+c);         
            return home_number;
        }
        else
        {
            theUS.setStatus(GutilPCU.getTextBundle("HomeNumberFormat"),UpdateStatus.WARNING);
            integerTextField1.requestFocus();
            return "";
        }
    }
    
    private void objectToHomeNumber()
    {
        if(!theHome.home_number.equals("")&&theHome.home_number.length()==11)
        {
            integerTextField1.setText(theHome.home_number.substring(0,4));
            integerTextField2.setText(theHome.home_number.substring(4,10));
            integerTextField3.setText(theHome.home_number.substring(10));
        }
        else
        {
            integerTextField1.setText("");
            integerTextField2.setText("");
            integerTextField3.setText("");
        }
    }
    
    public boolean showPanel(PCUObject pcuobject)
    {
        if(frm1 ==null)
        {
            frm1 = new javax.swing.JFrame(GuiLang.setLanguage("ข้อมูลบ้าน"));
        }
//        java.awt.Image image = theHM.theHosInf.getJFrame().getIconImage();
//        frm1.setIconImage(image);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        frm1.getContentPane().add(this,java.awt.BorderLayout.CENTER);
        frm1.setSize(800, 600);//,800,450);
        frm1.setLocation((screenSize.width-frm1.getSize().width)/2,(screenSize.height-frm1.getSize().height)/2);
        frm1.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        frm1.setVisible(true);
        setObject(pcuobject);
        return true;
    }
    
    private void relationComboBoxVillage()
    {   
        String village_id = Gutil.getGuiData(jComboBoxVillage);        
        if(village_id!="")
            ComboboxModel.setCodeComboBox(jComboBoxVillage1,village_id);            
        actioncombo = false;
    }
    
    /**
     * 
     */
    private void relationComboBoxVillage1()
    {
        String village_id = Gutil.getGuiData(jComboBoxVillage1);        
        if(village_id!="")
            ComboboxModel.setCodeComboBox(jComboBoxVillage,village_id);  
        actioncombo = true;
    }
    
    
    /**
     * ตรวจสอบเลขบัตรประชาชนว่ามีเลขซ้ำก่อนที่จะนำบุคคลเข้าบ้าน
     * @param ออปเจ็ค Family
     * @return boolean true = ไม่ซ้ำ  false = ซ้ำ
     * @author jao
     * @modify kingland
     * @date 30/08/2549
     */
    private boolean checkPidInTable(Family family)
    {   boolean result = true ;
        if(!family.pid.equals(""))
        {   Family fm = null;
            Vector vFm = null;
            vFm = theHomeControl.listFamilyByPID(family.pid);           
            if(vFm!=null)
            {   for(int i=0 ;i<vFm.size(); i++)
                {   fm = (Family)vFm.elementAt(i);
                    if(fm.getObjectId().equals(family.getObjectId()))  
                    {   //ผู้ใช้แก้ไขข้อมูลโดยไม่เปลี่ยนเลขบัตร
                        result = true;
                    }
                    else 
                    {   
                        theUS.setStatus(GutilPCU.getTextBundle("HavePID")+
                        (" ")+fm.pid+(" ")+GutilPCU.getTextBundle("InDataBase"),UpdateStatus.WARNING); 
                        result =false ;
                       // theFamily.hn = "";
//                        theFamily.patient_id = "";
                    }
                }
            } 
        }        
        return result;
    }
    
    /**
     * ลบรายชื่อผู้ที่อยู่ในบ้าน
     * @jao 
     * @deprecated henbe unused
     */
    private void deleteDataToObjectFamily(int row)
    {
        Family  family = (Family)vFamily.remove(row);
        if(family.getObjectId() != null)
        {   
            family.staff_cancel = theHM.theHosInf.getCurrentEmployee().getObjectId();
            family.cancel_date_time = com.hospital_os.utility.DateUtil.getTextCurrentDateTime(theHM.theHosControl.theConnectionInf);
            family.active = "0";
            theHomeControl.savePatientInFamily(family);
        }
        setTablePatient();
        jButtonDelPatient.setEnabled(false);
    }
    
    /**
     * บันทึกบุคคลจากหน้าPanelPerson
     * @jao 
     * @deprecated henbe unused
     */
    private void saveFamily(Family family)
    {
        theHomeControl.savePatientInFamily(theFamily);
        setTablePatient();
        setEnableGuiFamily(false);
        jButtonAddPatient.setEnabled(true);
    }
    
    /**
     * เช็ควันที่ที่สัตว์เลี้ยงเกิดว่าเป็นวันในอนาคตหรือไม่
     * @jao 
     */
    private void checkDatePetBirth()
    {        
        if(!dateComboBoxPetBirthDay.getText().equals("") 
            && dateComboBoxPetBirthDay.getText().length()==10 
            && com.pcu.utility.DateUtil.countDay(dateComboBoxPetBirthDay.getText(),theHosManage.theHosControl.theConnectionInf) == -1 
            && com.pcu.utility.DateUtil.isToday(com.pcu.utility.DateUtil.getDateFromText(dateComboBoxPetBirthDay.getText()),theHosManage.theHosControl.theConnectionInf)==false)  
        {            
             if(checkpetborn == false)
             {
                 // ไม่สามารถกรอกวันที่เป็นวันในอนาคตได้
                theUS.setStatus(GutilPCU.getTextBundle("NoDateFuture") , UpdateStatus.WARNING);
                dateComboBoxPetBirthDay.setText("");                              
                checkpetborn = true;
             }

        }  
        if(checkpetborn)
        {
            dateComboBoxPetBirthDay.setText("");    
        }
    }
    
    /**
     * เช็ควันที่ที่สัตว์เลี้ยงคุมกำเนิดว่าเป็นวันในอนาคตหรือไม่
     * @jao 
     */
    private void checkDatePetControl()
    {
        if(!dateComboBoxBirthDay.getText().equals("") 
            && dateComboBoxBirthDay.getText().length()==10 
            && com.pcu.utility.DateUtil.countDay(dateComboBoxBirthDay.getText(),theHosManage.theHosControl.theConnectionInf) == -1 
            && com.pcu.utility.DateUtil.isToday(com.pcu.utility.DateUtil.getDateFromText(dateComboBoxBirthDay.getText()),theHosManage.theHosControl.theConnectionInf)==false)  
        {            
             if(checkpetborn == false)
             {
                 // ไม่สามารถกรอกวันที่เป็นวันในอนาคตได้
                theUS.setStatus(GutilPCU.getTextBundle("NoDateFuture") , UpdateStatus.WARNING);
                dateComboBoxBirthDay.setText("");                              
                checkpetborn = true;
             }
        }  
        if(checkpetborn)
        {
            dateComboBoxBirthDay.setText("");    
        }
    }
    
    /**
     * เช็ควันที่ที่สัตว์เลี้ยงรับวัคซีนว่าเป็นวันในอนาคตหรือไม่
     * @jao 
     */
    private void checkDatePetVaccine()
    { 
        if(!dateComboBoxVaccineDay.getText().equals("") 
            && dateComboBoxVaccineDay.getText().length()==10 
            && com.pcu.utility.DateUtil.countDay(dateComboBoxVaccineDay.getText(),theHosManage.theHosControl.theConnectionInf) == -1 
            && com.pcu.utility.DateUtil.isToday(com.pcu.utility.DateUtil.getDateFromText(dateComboBoxVaccineDay.getText()),theHosManage.theHosControl.theConnectionInf)==false)  
        {            
             if(checkpetborn == false)
             {
                 // ไม่สามารถกรอกวันที่เป็นวันในอนาคตได้
                theUS.setStatus(GutilPCU.getTextBundle("NoDateFuture") , UpdateStatus.WARNING);
                dateComboBoxVaccineDay.setText("");                              
                checkpetborn = true;
             }
        }  
        if(checkpetborn)
        {
            dateComboBoxVaccineDay.setText("");    
        }
    }
    
    private void selectVillage(){        
        String village_id = Gutil.getGuiData(jComboBoxVillage1);
        theVillage = theHomeControl.listVillage(village_id);
        if(theVillage != null)
            jTextFieldMoo.setText(theVillage.village_moo);
    }

    public void notifylistPatientbyFamily(Family family,int i) 
    {   
        Constant.println("---------Neung------notifylistPatientbyFamily------"+this.getClass().toString());
//        if(i==0)//บันทึกบุคคลจากหน้าPanelPerson
//        {   
//            saveFamily(family); 
//        }
//        else
//        {   
//            theHomeControl.savePatientInFamily(theFamily);            
//        }
//        theHM.theHC.thePatientControl.readFamilyByFamilyId(family.getObjectId());
        setTablePatient();
    }

    public void notifylistHome(Home home) {       
        setHome(home);
    }

    public void notifylistPatientbyFamilyInSearch(Family family) {
    }

    public void notifyChangeHome(String str, int status) {
        selectHome(-2);  
        flagInsert = false;
    }

    public void notifySaveStatus(Family family, int i)
    {
//         Constant.println("---------Neung------notifySaveStatus------"+this.getClass().toString());
//        if(i==0)
//        {   saveFamily(family); 
//        }
//        else
//        {   theHomeControl.saveStatus(theFamily);            
//            setTablePatient();
//        }
         setTablePatient();
//        this.theHM.theHC.thePatientControl.readFamilyByFamilyId(family.getObjectId());
    }
    
    private void initPopupMenu() {
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu1.setAlignmentX(jPopupMenu1.LEFT_ALIGNMENT);
        jPopupMenu1.setInvoker(jScrollPane3);
        javax.swing.JSeparator jSeparator1 = new javax.swing.JSeparator();
        jMenuItemCut = new javax.swing.JMenuItem();
        jMenuItemCut.setText("ย้ายออก");        
//        jMenuItemCut.setAccelerator(javax.swing.KeyStroke.getKeyStroke('X'));
        jMenuItemPast = new javax.swing.JMenuItem();
        jMenuItemPast.setText("ย้ายเข้า");
//        jMenuItemPast.setAccelerator(javax.swing.KeyStroke.getKeyStroke('V'));
        jMenuItemMoveHome0 = new javax.swing.JMenuItem();
        jMenuItemMoveHome0.setText("ย้ายเข้าบ้าน 0");
        jPopupMenu1.add(jMenuItemCut);
        jPopupMenu1.add(jMenuItemPast);
        jPopupMenu1.add(jMenuItemMoveHome0);
        
        jMenuItemCut.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonCutActionPerformed(evt);
            }
        });
        //
        jMenuItemPast.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonPasteActionPerformed(evt);
            }
        });
        jMenuItemMoveHome0.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonMoveHome0ActionPerformed(evt);
            }
        });  
    }

    private void setEnabledMoveHomeButton(boolean b) {
        //button
        jButtonPaste.setEnabled(b&&!theFamilyTempV.isEmpty());
        jButtonCut.setEnabled(b&&(jTableFamily.getSelectedRowCount()>0));
        jButtonMoveHome0.setEnabled(b&&jTableFamily.getSelectedRowCount()>0);
        //menu item
        jMenuItemPast.setEnabled(b&&!theFamilyTempV.isEmpty());        
        jMenuItemCut.setEnabled(b&&jTableFamily.getSelectedRowCount()>0);        
        jMenuItemMoveHome0.setEnabled(b&&jTableFamily.getSelectedRowCount()>0);
    }

    public void setPanelPerson(PanelPerson pp) {
        
    }
    
}
