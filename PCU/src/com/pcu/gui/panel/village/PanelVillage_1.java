/*
 * PanelVillage.java
 *
 * Created on 9 มิถุนายน 2548, 15:26 น.
 */
package com.pcu.gui.panel.village;

import com.hosv3.control.LookupControl;
import com.pcu.gui.dialog.HosDialog;
import javax.swing.*;/*ใช้ใน main*/
import java.util.*;
import com.hosv3.utility.connection.UpdateStatus;
import com.hospital_os.utility.*;
import com.hospital_os.object.Site;

import com.pcu.utility.*;
import com.pcu.object.*;
import com.pcu.control.*;
/**
 *
 * @author  amp
 */
public class PanelVillage_1 extends javax.swing.JPanel
{
    private SystemControl theSystemControl; 
    private AllComboBoxControl theAllComboBoxControl;
    private VillageControl theVillageControl;
    private SetupPcuControl theSetupPcuControl;
    private LookupControl theLookupControl;
    
    private Village theVillage;
    private Vector vVillage = new Vector();
    
    private Vector vComboBoxVillage = new Vector();    
    private Vector vComboboxChangwat = new Vector();
    private Vector vComboboxMarket = new Vector();
    
    private String employeeId = "";
    private String ownerOther = "";
    private String standardTypeOther = "";  
    private String code = "";
    private String name = "";
    private boolean flagFirst = true;
    
    /*ComboFix*/
    private ComboFix cfv = new ComboFix();  
    private ComboFix cfmkt = new ComboFix();
    private ComboFix cfmktl = new ComboFix();
    private ComboFix cfmk = new ComboFix();    
    
    /*Neung*/
    private HosManage theHM;
    private JFrame theFrame;

    private UpdateStatus theUS;
    
    /** Creates new form PanelVillage */
    public PanelVillage_1(HosManage hm,HosDialog hd,UpdateStatus us) 
    {
        theHM = hm;
        theSystemControl = hm.theHosControl.theSystemControl;
        theAllComboBoxControl = hm.theHosControl.theAllComboBoxControl;
        theVillageControl = hm.theHosControl.theVillageControl;
        theSetupPcuControl = hm.theHosControl.theSetupPcuControl;
        theLookupControl = hm.theHosControl.theLookupControl;
        theUS = us;
        initComponents();
        setLanguage();
        initDatas();
        this.panelActivityGroup1.setControl(theHM,us);
        this.panelCompany1.setControl(theHM,us);
        this.panelMarket1.setControl(theHM,us);
        this.panelResource1.setControl(theHM,us);
        this.panelSchool1.setControl(theHM,us);
        this.panelTemple1.setControl(theHM,us); 
        this.panelWater1.setControl(theHM,us);
        theVillageControl.addHomeObserv(panelActivityGroup1);
        theVillageControl.addHomeObserv(panelCompany1);
        theVillageControl.addHomeObserv(panelMarket1);
        theVillageControl.addHomeObserv(panelResource1);
        theVillageControl.addHomeObserv(panelSchool1);
        theVillageControl.addHomeObserv(panelTemple1);
        theVillageControl.addHomeObserv(panelWater1);
        setDefaultHospital();
    }
    public void setJFrame(JFrame jf){
        theFrame = jf;
    }
    public void initDatas() 
    {  
        searchVillage();
        /***ประเภทตลาด***/
        cfmkt.code = "99";
        cfmkt.name = GutilPCU.getTextBundle("Fill_Other");
        /***ประเภทแผงขายอาหาร**/
        cfmktl.code = "999";
        cfmktl.name = GutilPCU.getTextBundle("Fill_Other");          
        
        /***ComboBox***/    
        Vector vComboBoxOwner = theAllComboBoxControl.listCommunityResorceOwner();
        Vector vComboBoxStandardType = theAllComboBoxControl.listCommunityStandardType();
        Vector vComboBoxStandard = theAllComboBoxControl.listCommunityStandard();
        Vector vComboboxChangwat = theAllComboBoxControl.listAddressCGW(); 
        this.panelCatAddress1.setControl(theHM.theHosInf.getLookupControl(),null);
        ComboboxModel.initComboBox(jComboBoxLocation,theAllComboBoxControl.listVillageLocation());
        dinamicComboBox(0);
        /***DateComboBox***/
        /***Other
                this.panelActivityGroup1;
                this.panelCompany1;
                this.panelMarket1;
                this.panelResource1;
                this.panelSchool1;
                this.panelTemple1;
                this.panelWater1;
         ***/
        theVillage = new Village();
    }  
    
    public void setObject(PCUObject pcuobject)
    {   /** ไปจัดการต่อเรื่องของข้อมูลที่จะรับ ถ้าเมื่อไรไม่มีข้อมูล นั้นจะทำอย่างไรบนหน้า GUI */
        Constant.println("_henbe_______________________" + getClass().toString());
        if(pcuobject != null)
        {
            if(pcuobject.getEmployee() != null)
            {
                employeeId = pcuobject.getEmployee().getObjectId();
            }
        }
        this.panelActivityGroup1.setObject(pcuobject);
        this.panelCompany1.setObject(pcuobject);
        this.panelMarket1.setObject(pcuobject);
        this.panelResource1.setObject(pcuobject);
        this.panelSchool1.setObject(pcuobject);
        this.panelTemple1.setObject(pcuobject);
        this.panelWater1.setObject(pcuobject);
    }
    
    private void dinamicComboBox(int flag)
    {       
        vComboBoxVillage.removeAllElements();
        if(flag==0 || flag==1)
        {
           /***หมู่บ้าน**/           
            cfv.code = "0"; 
            cfv.name = GutilPCU.getTextBundle("All");           
            vComboBoxVillage.add(cfv);
            //pu 04/01/07: นำ object ของ Village มาใส่ใน Combo fix เพื่อนำไปเซ็ตให้กับ combo box 
            //ในกรณีที่เลือกหมู่บ้านจากแถบหมู่บ้าน จะมีผลให้ combo box หมุ่บ้าน ในแถบอื่นๆ เปลี่ยนแปลงตามไปด้วย
            for(int i=0;i<this.vVillage.size();i++)
            {
                cfv = new ComboFix(); 
                cfv.code = ((Village)this.vVillage.get(i)).getObjectId();
                cfv.name = ((Village)this.vVillage.get(i)).village_name;
                vComboBoxVillage.add(cfv);
            }
            
            if(vComboBoxVillage==null)
            {
                vComboBoxVillage = new Vector();
            }
           
        }
    }
    
    private void setDefaultHospital()
    {
        Site theSite = theSystemControl.theHospital_Site;
        if(theSite != null)
        {
            String changwat = theSite.changwat;
            String amphur = theSite.amphor;
            String tambol = theSite.tambon;
            this.panelActivityGroup1.setDefaultHospital();
            this.panelCompany1.setDefaultHospital();
            this.panelMarket1.setDefaultHospital();
            this.panelResource1.setDefaultHospital();
            this.panelSchool1.setDefaultHospital();
            this.panelTemple1.setDefaultHospital();
            this.panelWater1.setDefaultHospital();
            jLabelHealthCenterName.setText("สถานพยาบาล "+ theSite.full_name
            +" ตำบล "+ theLookupControl.readAddressString(tambol)
            +" อำเภอ "+ theLookupControl.readAddressString(amphur)
            +" จังหวัด "+ theLookupControl.readAddressString(changwat));
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

        buttonGroupCompanyRegister = new javax.swing.ButtonGroup();
        buttonGroupMarketRegister = new javax.swing.ButtonGroup();
        buttonGroupActivityGroup = new javax.swing.ButtonGroup();
        buttonGroupResourceSatet = new javax.swing.ButtonGroup();
        buttonGroupWaterState = new javax.swing.ButtonGroup();
        buttonGroupCompanyCo = new javax.swing.ButtonGroup();
        buttonGroupMarketCo = new javax.swing.ButtonGroup();
        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jPanelHealthCenterDetail = new javax.swing.JPanel();
        jLabelHealthCenterName = new javax.swing.JLabel();
        jLabelTitleCode = new javax.swing.JLabel();
        jLabelTitleName = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanelSearchVillage = new javax.swing.JPanel();
        jTextFieldSearchVillage = new javax.swing.JTextField();
        jButtonSearchVillage = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVillage = new javax.swing.JTable();
        jPanelVillageDetail = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldVillageName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxLocation = new javax.swing.JComboBox();
        jTextFieldVillageMoo = new com.pcu.utility.IntegerTextField();
        jTextFieldVillageCode = new com.pcu.utility.IntegerTextField();
        panelCatAddress1 = new com.hosv3.gui.component.PanelCatAddress();
        jPanel5 = new javax.swing.JPanel();
        jButtonAddVillage = new javax.swing.JButton();
        jButtonDelVillage = new javax.swing.JButton();
        jButtonSaveVillage = new javax.swing.JButton();
        panelSchool1 = new com.pcu.gui.panel.village.PanelSchool();
        panelTemple1 = new com.pcu.gui.panel.village.PanelTemple();
        panelCompany1 = new com.pcu.gui.panel.village.PanelCompany();
        panelWater1 = new com.pcu.gui.panel.village.PanelWater();
        panelMarket1 = new com.pcu.gui.panel.village.PanelMarket();
        panelResource1 = new com.pcu.gui.panel.village.PanelResource();
        panelActivityGroup1 = new com.pcu.gui.panel.village.PanelActivityGroup();

        setMinimumSize(new java.awt.Dimension(700, 500));
        setPreferredSize(new java.awt.Dimension(700, 500));
        setLayout(new java.awt.GridBagLayout());

        jPanelHealthCenterDetail.setBackground(new java.awt.Color(255, 255, 204));
        jPanelHealthCenterDetail.setMinimumSize(new java.awt.Dimension(550, 42));
        jPanelHealthCenterDetail.setPreferredSize(new java.awt.Dimension(550, 42));
        jPanelHealthCenterDetail.setLayout(new java.awt.GridBagLayout());

        jLabelHealthCenterName.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabelHealthCenterName.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelHealthCenterDetail.add(jLabelHealthCenterName, gridBagConstraints);

        jLabelTitleCode.setFont(new java.awt.Font("Tahoma", 0, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelHealthCenterDetail.add(jLabelTitleCode, gridBagConstraints);

        jLabelTitleName.setFont(new java.awt.Font("Tahoma", 0, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelHealthCenterDetail.add(jLabelTitleName, gridBagConstraints);

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 18));
        jLabel50.setText(":");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelHealthCenterDetail.add(jLabel50, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        add(jPanelHealthCenterDetail, gridBagConstraints);

        jTabbedPane.setFont(defaultFont1);
        jTabbedPane.setMinimumSize(new java.awt.Dimension(700, 440));
        jTabbedPane.setPreferredSize(new java.awt.Dimension(700, 440));
        jTabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPaneMouseClicked(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanelSearchVillage.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SearchVillage", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jPanelSearchVillage.setLayout(new java.awt.GridBagLayout());

        jTextFieldSearchVillage.setFont(defaultFont1);
        jTextFieldSearchVillage.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldSearchVillage.setPreferredSize(new java.awt.Dimension(100, 21));
        jTextFieldSearchVillage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchVillageActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 5, 0, 0);
        jPanelSearchVillage.add(jTextFieldSearchVillage, gridBagConstraints);

        jButtonSearchVillage.setFont(defaultFont1);
        jButtonSearchVillage.setText("Search");
        jButtonSearchVillage.setMaximumSize(new java.awt.Dimension(67, 24));
        jButtonSearchVillage.setMinimumSize(new java.awt.Dimension(67, 24));
        jButtonSearchVillage.setPreferredSize(new java.awt.Dimension(67, 24));
        jButtonSearchVillage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchVillageActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 5, 0, 5);
        jPanelSearchVillage.add(jButtonSearchVillage, gridBagConstraints);

        jTableVillage.setFont(defaultFont1);
        jTableVillage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableVillage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableVillageMouseReleased(evt);
            }
        });
        jTableVillage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableVillageKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableVillage);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelSearchVillage.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel1.add(jPanelSearchVillage, gridBagConstraints);

        jPanelVillageDetail.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SearchVillage", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jPanelVillageDetail.setMinimumSize(new java.awt.Dimension(306, 212));
        jPanelVillageDetail.setPreferredSize(new java.awt.Dimension(306, 212));
        jPanelVillageDetail.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(defaultFont1);
        jLabel3.setText("VillageCode");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 0, 0);
        jPanelVillageDetail.add(jLabel3, gridBagConstraints);

        jLabel5.setFont(defaultFont1);
        jLabel5.setText("VillageMoo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelVillageDetail.add(jLabel5, gridBagConstraints);

        jLabel7.setFont(defaultFont1);
        jLabel7.setText("VillageName");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelVillageDetail.add(jLabel7, gridBagConstraints);

        jTextFieldVillageName.setFont(defaultFont1);
        jTextFieldVillageName.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldVillageName.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 11);
        jPanelVillageDetail.add(jTextFieldVillageName, gridBagConstraints);

        jLabel9.setFont(defaultFont1);
        jLabel9.setText("VillageLocation");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanelVillageDetail.add(jLabel9, gridBagConstraints);

        jComboBoxLocation.setFont(defaultFont1);
        jComboBoxLocation.setMinimumSize(new java.awt.Dimension(100, 20));
        jComboBoxLocation.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 11);
        jPanelVillageDetail.add(jComboBoxLocation, gridBagConstraints);

        jTextFieldVillageMoo.setColumns(2);
        jTextFieldVillageMoo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldVillageMoo.setFont(defaultFont1);
        jTextFieldVillageMoo.setMinimumSize(new java.awt.Dimension(26, 19));
        jTextFieldVillageMoo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldVillageMooFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 11);
        jPanelVillageDetail.add(jTextFieldVillageMoo, gridBagConstraints);

        jTextFieldVillageCode.setColumns(10);
        jTextFieldVillageCode.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldVillageCode.setFont(defaultFont1);
        jTextFieldVillageCode.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldVillageCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldVillageCodeFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 5, 0, 11);
        jPanelVillageDetail.add(jTextFieldVillageCode, gridBagConstraints);

        panelCatAddress1.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 11, 11);
        jPanelVillageDetail.add(panelCatAddress1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel1.add(jPanelVillageDetail, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jButtonAddVillage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAddVillage.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonAddVillage.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonAddVillage.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonAddVillage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddVillageActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jButtonAddVillage, gridBagConstraints);

        jButtonDelVillage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelVillage.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDelVillage.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDelVillage.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonDelVillage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelVillageActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel5.add(jButtonDelVillage, gridBagConstraints);

        jButtonSaveVillage.setText("Save");
        jButtonSaveVillage.setMaximumSize(new java.awt.Dimension(67, 24));
        jButtonSaveVillage.setMinimumSize(new java.awt.Dimension(67, 24));
        jButtonSaveVillage.setPreferredSize(new java.awt.Dimension(67, 24));
        jButtonSaveVillage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveVillageActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(jButtonSaveVillage, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 5, 11);
        jPanel1.add(jPanel5, gridBagConstraints);

        jTabbedPane.addTab("Village", jPanel1);
        jTabbedPane.addTab("School", panelSchool1);
        jTabbedPane.addTab("Temple", panelTemple1);
        jTabbedPane.addTab("Company", panelCompany1);
        jTabbedPane.addTab("Water", panelWater1);
        jTabbedPane.addTab("Market", panelMarket1);
        jTabbedPane.addTab("Resource", panelResource1);
        jTabbedPane.addTab("ActivityGroup", panelActivityGroup1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jTabbedPane, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveVillageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveVillageActionPerformed
        saveVillage();
        searchVillage();
    }//GEN-LAST:event_jButtonSaveVillageActionPerformed

    private void jButtonDelVillageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelVillageActionPerformed
        deleteVillage();
        searchVillage();
    }//GEN-LAST:event_jButtonDelVillageActionPerformed

    private void jButtonAddVillageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddVillageActionPerformed
        addVillage();
    }//GEN-LAST:event_jButtonAddVillageActionPerformed

    private void jTextFieldVillageCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldVillageCodeFocusLost
        if(theVillageControl.checkVillageCode(jTextFieldVillageCode.getText().trim())) {
            theUS.setStatus( GutilPCU.getTextBundle("sameVillageCode"),UpdateStatus.WARNING);
            jTextFieldVillageCode.setText("");
            jTextFieldVillageCode.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldVillageCodeFocusLost

    private void jTextFieldVillageMooFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldVillageMooFocusLost
        if(jTextFieldVillageMoo.getText().length() > 2) {
            theUS.setStatus( "ไม่ควรระบุเลขที่หมู่เกิน 3 หลัก",UpdateStatus.WARNING);
            jTextFieldVillageMoo.setText("");
            jTextFieldVillageMoo.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldVillageMooFocusLost

    private void jTableVillageMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVillageMouseReleased
        selectVillage(this.jTableVillage.getSelectedRow());
    }//GEN-LAST:event_jTableVillageMouseReleased

    private void jTableVillageKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableVillageKeyReleased
        if(evt.getKeyCode()==evt.VK_UP ||evt.getKeyCode()==evt.VK_DOWN) {
            selectVillage(this.jTableVillage.getSelectedRow());
        }
    }//GEN-LAST:event_jTableVillageKeyReleased

    private void jButtonSearchVillageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchVillageActionPerformed
        searchVillage();
    }//GEN-LAST:event_jButtonSearchVillageActionPerformed

    private void jTextFieldSearchVillageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchVillageActionPerformed
        searchVillage();
    }//GEN-LAST:event_jTextFieldSearchVillageActionPerformed

    private void jTabbedPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPaneMouseClicked
        //selectTab(jTabbedPane.getSelectedIndex());
    }//GEN-LAST:event_jTabbedPaneMouseClicked
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupActivityGroup;
    private javax.swing.ButtonGroup buttonGroupCompanyCo;
    private javax.swing.ButtonGroup buttonGroupCompanyRegister;
    private javax.swing.ButtonGroup buttonGroupMarketCo;
    private javax.swing.ButtonGroup buttonGroupMarketRegister;
    private javax.swing.ButtonGroup buttonGroupResourceSatet;
    private javax.swing.ButtonGroup buttonGroupWaterState;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private javax.swing.JButton jButtonAddVillage;
    private javax.swing.JButton jButtonDelVillage;
    private javax.swing.JButton jButtonSaveVillage;
    private javax.swing.JButton jButtonSearchVillage;
    private javax.swing.JComboBox jComboBoxLocation;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelHealthCenterName;
    private javax.swing.JLabel jLabelTitleCode;
    private javax.swing.JLabel jLabelTitleName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelHealthCenterDetail;
    private javax.swing.JPanel jPanelSearchVillage;
    private javax.swing.JPanel jPanelVillageDetail;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTableVillage;
    private javax.swing.JTextField jTextFieldSearchVillage;
    private com.pcu.utility.IntegerTextField jTextFieldVillageCode;
    private com.pcu.utility.IntegerTextField jTextFieldVillageMoo;
    private javax.swing.JTextField jTextFieldVillageName;
    private com.pcu.gui.panel.village.PanelActivityGroup panelActivityGroup1;
    private com.hosv3.gui.component.PanelCatAddress panelCatAddress1;
    private com.pcu.gui.panel.village.PanelCompany panelCompany1;
    private com.pcu.gui.panel.village.PanelMarket panelMarket1;
    private com.pcu.gui.panel.village.PanelResource panelResource1;
    private com.pcu.gui.panel.village.PanelSchool panelSchool1;
    private com.pcu.gui.panel.village.PanelTemple panelTemple1;
    private com.pcu.gui.panel.village.PanelWater panelWater1;
    // End of variables declaration//GEN-END:variables
    /**
     *ตรวจสอบว่าลำดับซ้ำกันหรือไม่
     *@return boolean true = ไม่ซ้ำกัน false = ซ้ำกัน
     *@author kingland
     *@date 05/06/2549
     */
    private boolean checkRepeat(int check)
    {
        boolean result = true;
        //โรงเรียน
        if(check == 1)
        {
        }
        //ศาสนสถาน
        
        //สถานประกอบการ
        else if(check == 3)
        {
           
        }
        //แหล่งน้ำ
        else if(check == 4)
        {
          
        }
        //ตลาด
        else if(check == 5)
        {
                        
        }
        //ทรัพยากร
        else if(check == 6)
        {
        }
        //กลุ่มกิจกรรม
        else if(check == 7)
        {
      
        }
        return result;
    }
    private void setLanguage()
    {  
        /*jLabel*/        
        /*TitledBorder*/        
        GutilPCU.JPanelLabler(jPanelHealthCenterDetail);
        /*Tab*/
        GutilPCU.getTextBundleTab(0,jTabbedPane);
        GutilPCU.getTextBundleTab(1,jTabbedPane);
        GutilPCU.getTextBundleTab(2,jTabbedPane);
        GutilPCU.getTextBundleTab(3,jTabbedPane);
        GutilPCU.getTextBundleTab(4,jTabbedPane);
        GutilPCU.getTextBundleTab(5,jTabbedPane); 
        GutilPCU.getTextBundleTab(6,jTabbedPane);
        GutilPCU.getTextBundleTab(7,jTabbedPane);
        /*CheckBox*/
 /*jLabel*/        
        jLabel3.setText(GutilPCU.getTextBundle(jLabel3.getText())); 
        jLabel5.setText(GutilPCU.getTextBundle(jLabel5.getText())); 
        jLabel7.setText(GutilPCU.getTextBundle(jLabel7.getText()));
        jLabel9.setText(GutilPCU.getTextBundle(jLabel9.getText()));
        jLabel50.setText(GutilPCU.getTextBundle(jLabel50.getText()));
        //add by neung
        
        /*jButton*/
        jButtonSearchVillage.setText(GutilPCU.getTextBundle(jButtonSearchVillage.getText()));
        jButtonAddVillage.setText(GutilPCU.getTextBundle(jButtonAddVillage.getText()));
        jButtonDelVillage.setText(GutilPCU.getTextBundle(jButtonDelVillage.getText()));
        jButtonSaveVillage.setText(GutilPCU.getTextBundle(jButtonSaveVillage.getText())); 
        
        /*TitledBorder*/        
        GutilPCU.JPanelLabler(jPanelHealthCenterDetail);
        GutilPCU.JPanelLabler(jPanelSearchVillage);     
        GutilPCU.JPanelLabler(jPanelVillageDetail);
        /*CheckBox*/
    }
    
    /**
     ***Village***
     * @deprecated ผิด pattern ต้องมี updateGO มารับก่อน
     * แล้วค่อยเรียก control save village รวมทั้งเงื่อนไขการเช็คอยู่ผิดที่ต้องอยู่ภายในฟังชันของ control เท่านั้น
     **/
    private void saveVillage()
    {   
        int result = theVillageControl.saveVillage(getVillage(),theUS);
        if(result == 0)
            return;
        //searchVillage();
        setEnableGuiVillage(false);
        dinamicComboBox(1);

        this.panelActivityGroup1.search(theVillage.getObjectId());
        this.panelCompany1.search(theVillage.getObjectId());
        this.panelMarket1.search(theVillage.getObjectId());
        this.panelResource1.search(theVillage.getObjectId());
        this.panelSchool1.search(theVillage.getObjectId());
        this.panelTemple1.search(theVillage.getObjectId());
        this.panelWater1.search(theVillage.getObjectId());        
    }
    
    private void clearGuiVillage()
    {
        Site site = theSystemControl.theHospital_Site;
        Village v = new Village();
        v.village_ampur = site.amphor;
        v.village_tambon = site.tambon;
        v.village_changwat = site.changwat;
       this.setVillage(v);
    }
    
    private void setEnableGuiVillage(boolean flag)
    {
        
    }
    
    private void searchVillage()
    {   
        /***ค้นหาหมู่บ้าน***/
        vVillage =  theVillageControl.listVillageByNameOrNumber(this.jTextFieldSearchVillage.getText());
        /***SetTable***/
        String[] col = {GutilPCU.getTextBundle("หมู่ที่"),
                    GutilPCU.getTextBundle("ชื่อหมู่บ้าน"),
                    GutilPCU.getTextBundle("บ้าน"),
                    GutilPCU.getTextBundle("ครอบครัว")};
                    
        Village villageTemp = new Village();
        Home homeTemp = new Home();
        TaBleModel tm ;
        
        if(vVillage != null)
        {   
            tm = new TaBleModel(col,vVillage.size());
            for(int i=0, size=vVillage.size(); i<size; i++)
            {  
                villageTemp = (Village)vVillage.get(i);
                
                tm.setValueAt(villageTemp.village_moo,i,0); 
                tm.setValueAt(villageTemp.village_name,i,1);
                homeTemp = theVillageControl.countOfHomeSumOfFamily(villageTemp.getObjectId());
                if(homeTemp!=null)
                {
                    if(homeTemp.count_home!=null)
                        tm.setValueAt(homeTemp.count_home,i,2);
                    else
                        tm.setValueAt("0",i,2);
                    if(homeTemp.sum_family!=null)
                        tm.setValueAt(homeTemp.sum_family,i,3);
                    else
                        tm.setValueAt("0",i,3);
                }
                else
                {
                    tm.setValueAt("0",i,2);
                    tm.setValueAt("0",i,3);
                }
            }
        }
        else
        {   
            tm= new TaBleModel(col,0);
        }
        this.jTableVillage.setModel(tm);
        jTableVillage.getColumnModel().getColumn(0).setPreferredWidth(60); // ลำดับ
        jTableVillage.getColumnModel().getColumn(0).setCellRenderer(TableRenderer.getRendererRight());
        jTableVillage.getColumnModel().getColumn(1).setPreferredWidth(120); // ลำดับ
        jTableVillage.getColumnModel().getColumn(2).setPreferredWidth(60); // ลำดับ
        jTableVillage.getColumnModel().getColumn(2).setCellRenderer(TableRenderer.getRendererRight());
        jTableVillage.getColumnModel().getColumn(3).setPreferredWidth(60); // ลำดับ
        jTableVillage.getColumnModel().getColumn(3).setCellRenderer(TableRenderer.getRendererRight());
    }
    
    private void selectVillage(int rowVillage)
    {
        setVillage((Village)vVillage.get(rowVillage));
        selectTab(jTabbedPane.getSelectedIndex());        
    }
    public void setVillage(Village vill){
        theVillage = vill;
        jLabelTitleCode.setText(theVillage.village_number);
        jLabelTitleName.setText(theVillage.village_name);        
        jTextFieldVillageCode.setText(theVillage.village_number);
        jTextFieldVillageMoo.setText(theVillage.village_moo);
        jTextFieldVillageName.setText(theVillage.village_name);
        ComboboxModel.setCodeComboBox(jComboBoxLocation,theVillage.village_location);    
        this.panelCatAddress1.setAddress(
                theVillage.village_changwat,theVillage.village_ampur,theVillage.village_tambon);      
        setEnableGuiVillage(true);
    }
    public Village getVillage(){

        theVillage.village_number = jTextFieldVillageCode.getText();
        theVillage.village_name = jTextFieldVillageName.getText();
        String moo = jTextFieldVillageMoo.getText();
        if(!moo.equals("0"))
        {
            if(moo.length()>2)moo = moo.substring(0,2);
            if(moo.length()==1)moo = "0"+moo;
        }
        theVillage.village_moo = moo;
        theVillage.village_location = Gutil.getGuiData(jComboBoxLocation);
        theVillage.village_changwat = this.panelCatAddress1.getChangwat();
        theVillage.village_ampur = panelCatAddress1.getAmpur();
        theVillage.village_tambon = panelCatAddress1.getTambon();
        return theVillage;
    }
    private void deleteVillage()
    {
            int res = theVillageControl.deleteVillage(theVillage,theUS);
            if(res==0)
                return;
            
            searchVillage();
    }
    
    private void addVillage()
    {
        clearGuiVillage();
        setDefaultHospital();
        theVillage = new Village();
        setEnableGuiVillage(true);
    }
    
    
    private void selectTab(int tab)
    {
            if(theVillage != null)
            {
                this.panelActivityGroup1.search(theVillage.getObjectId());
                this.panelCompany1.search(theVillage.getObjectId());
                this.panelMarket1.search(theVillage.getObjectId());
                this.panelResource1.search(theVillage.getObjectId());
                this.panelSchool1.search(theVillage.getObjectId());
                this.panelTemple1.search(theVillage.getObjectId());
                this.panelWater1.search(theVillage.getObjectId());
            }
            else
            {
                panelSchool1.addSchool();
                panelTemple1.addTemple();
                panelCompany1.addCompany();
                panelWater1.addWater();
                panelMarket1.addMarket();
                panelResource1.addResource();
                panelActivityGroup1.addAGR();
            }
    }
    
}
