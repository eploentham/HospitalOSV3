/*
 * DialogLabReferIn.java
 *
 * Created on 19 พฤษภาคม 2547, 12:10 น.
 */
package com.hosv3.gui.dialog;
import javax.swing.JFrame;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import com.hosv3.control.*;
import com.hosv3.utility.*;
import com.hosv3.object.*;
import com.hosv3.subject.*;
//import com.hosv3.gui.panel.setup.*;
import com.hosv3.utility.connection.*;
import com.hosv3.control.lookup.*;
import com.hosv3.usecase.transaction.*;

import com.hospital_os.object.*;
import com.hospital_os.utility.TaBleModel;
import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.ComboboxModel;
import com.hosv3.utility.Constant;
//import com.hosv3.gui.panel.transaction.HosDialog;

/**
 *
 * @author  amp
 */
public class DialogLabReferIn extends javax.swing.JFrame
implements UpdateStatus,ManageLabXrayResp {
    
    public boolean actionCommand = false;
    HosObject theHO;
    HosControl theHC;
    HosSubject theHS;
    UpdateStatus theUS;
//    HosDialog theHD;
    /**Control*/
    private LookupControl theLookupControl;
    private PatientControl thePatientControl;
    private LabReferControl theLabReferControl;
    private SetupControl theSetupControl;
    private SystemControl theSystemControl;
    private OrderControl theOrderControl;
    
    /**Subject*/
    private SetupSubject theSetupSubject;
    
    /**Vector*/
    private Vector vPatientLabreferin;
    private Vector vVisitLabreferin;
    private Vector vHistory; /*เก็บ Object ของ Visit*/
    private Vector vOrderItem; /*เก็บ Object ของ order_item*/
    private Vector vLabResultItem;/*เก็บ Object ของ labresultitem*/ 
    private Vector vOrderResultLabreferin;
    private Vector vOrderItemUpdate;
    private Vector vResult;
    private Vector vTemp;
    private Vector vOrderItemReverseUpdate;
    private Vector vLabSet;
    
    /**Object*/
    private PatientLabreferin thePatientLabreferin = new PatientLabreferin();
    private VisitLabreferin theVisitLabreferin = new VisitLabreferin();
    private OrderItemLabreferin theOrderItemLabreferin;
    private OrderResultLabreferin theOrderResultLabreferin;
    private LabGroup theLabGroup;
    
    /**Other*/
    int RowOrderItem = 0;
    boolean haveResult = false; /*ถ้าเคย save ผล จะเป็น true*/
    boolean canEdit = false; /*ถ้าเป็น true คือสามารถทำงาน TableOrderItem ได้*/
    private String hospitalRefer;
    PanelSetupSearchSub psep;      
    private String[] column = {"ชื่อ","นามสกุล"};
    private String[] col_jTableHistory = {"ครั้งที่","วันที่"};
    private String[] col_jTableOrder = {"ชื่อ"};
    private String[] col_jTableResult = {"ชื่อ","ผล","หน่วย"};
    /** Creates new form DialogLabReferIn */    

    public DialogLabReferIn(HosControl hc,UpdateStatus us)
    {
        setIconImage(us.getJFrame().getIconImage());
        //super(us.getJFrame(), true);
        theHC = hc;
        theHO = theHC.theHO;
        theUS = theHC.theUS;
        theHS = theHC.theHS;
        /*control*/
        theLookupControl = theHC.theLookupControl;
        thePatientControl = theHC.thePatientControl;
        theLabReferControl = theHC.theLabReferControl;
//        theLabReferSubject =;
        theSetupControl = theHC.theSetupControl;
        theSetupSubject = theHS.theSetupSubject;
        theSystemControl = theHC.theSystemControl;
        theOrderControl = theHC.theOrderControl;      
        initComponents();
        setLanguage("");
        jTableHistory.setGuiMode(true);
        jTableOrder.setGuiMode(true);
        jTablePatientLabReferIn.setGuiMode(true);
        jTableResult.setGuiMode(true);
        jTableVisitLabReferIn.setGuiMode(true);
        initComboBox();
        /*notify*/
       
//        setDataAll();
        setEnablePatient(true);
        setEnableOrder(true);
        setEnableResultOrder(true);
        setTableListPatientLabreferin(null);
        setTableHistory(null);
        setTableOrder(null);
        setTableResultLab(null);
        setTableResultLabHaveData(null);
        theHC = hc;
        searchVisitLabreferin();
        //setDialog();
     }
//    public void setDialog(HosDialog hd)
//    {
//        theHD = hd;
//    }
    
    /** ใช้ในการ กำหนดค่าให้กับ combobox ที่อยู่ใน panel
     *ข้อมูลเข้า ไม่มี
     ข้อมูลออก ไม่มี
     */
//    private void setDataAll()
//    {   
//        jButtonPrintResultLab.setVisible(false);
//        theHC.theHS.theResultSubject.attachManageLab(this);
//        ComboboxModel.initComboBox(jComboBoxGender,theHC.theLookupControl.listGender());
//        ComboboxModel.initComboBox(jComboBoxChangwat,theHC.theLookupControl.listAddressCGW());
//        ComboboxModel.initComboBox(jComboBoxAmpur,new Vector());
//        ComboboxModel.initComboBox(jComboBoxTambon,new Vector());
//        jTextFieldHosReferIn.setEnabled(false);
//        /*Set default ตามที่ Set ที่อยู่ของโรงพยาบาลในส่วนของ admin*/
//        if(theHO.theSite != null)
//        {      
//            Gutil.setGuiData(jComboBoxChangwat,theHO.theSite.changwat);
//            if(theHO.theSite.changwat !=null)
//            {    
//                 Gutil.setGuiData(jComboBoxAmpur,theHO.theSite.amphor);
//            }   
//            if(theHO.theSite.amphor !=null)
//            {    
//                Gutil.setGuiData(jComboBoxTambon,theHO.theSite.tambon);
//            }
//        }
//        else
//        {
//            JOptionPane.showMessageDialog(this, "ตาราง Site ไม่มีข้อมูล โปรแกรมจะกำหนดที่อยู่เป็นจังหวัดภูเก็ตให้แทน", "เตือน", JOptionPane.WARNING_MESSAGE);
//            Gutil.setGuiData(jComboBoxChangwat,"830000");
//            Gutil.setGuiData(jComboBoxAmpur,"830100");
//            Gutil.setGuiData(jComboBoxTambon,"830104");            
//        }
//    }   
    
    private void initComboBox(){
        //jComboBoxPrename.setControl(null,new PrefixLookup(theHC.theLookupControl),new Prefix2());
        jComboBoxPrename.setControl(new PrefixLookup(theHC.theLookupControl),true);
        jButtonPrintResultLab.setVisible(false);
        theHC.theHS.theResultSubject.attachManageLab(this);
        ComboboxModel.initComboBox(jComboBoxGender,theHC.theLookupControl.listGender());
        ComboboxModel.initComboBox(jComboBoxChangwat,theHC.theLookupControl.listAddressCGW());
        ComboboxModel.initComboBox(jComboBoxAmpur,new Vector());
        ComboboxModel.initComboBox(jComboBoxTambon,new Vector());
        jTextFieldHosReferIn.setEnabled(false);
        /*Set default ตามที่ Set ที่อยู่ของโรงพยาบาลในส่วนของ admin*/
        if(theHO.theSite != null)
        {      
            Gutil.setGuiData(jComboBoxChangwat,theHO.theSite.changwat);
            if(theHO.theSite.changwat !=null)
            {    
                 Gutil.setGuiData(jComboBoxAmpur,theHO.theSite.amphor);
            }   
            if(theHO.theSite.amphor !=null)
            {    
                Gutil.setGuiData(jComboBoxTambon,theHO.theSite.tambon);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, Constant.getTextBundle("ตาราง Site ไม่มีข้อมูล โปรแกรมจะกำหนดที่อยู่เป็นจังหวัดภูเก็ตให้แทน"), Constant.getTextBundle("เตือน"), JOptionPane.WARNING_MESSAGE);
            Gutil.setGuiData(jComboBoxChangwat,"830000");
            Gutil.setGuiData(jComboBoxAmpur,"830100");
            Gutil.setGuiData(jComboBoxTambon,"830104");            
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jTextFieldSearchFname = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTablePatientLabReferIn = new com.hosv3.gui.component.HJTableSort();
        jTextFieldSearchLname = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabelPrename = new javax.swing.JLabel();
        jLabelFname = new javax.swing.JLabel();
        jTextFieldFname = new javax.swing.JTextField();
        jLabelLname = new javax.swing.JLabel();
        jTextFieldLname = new javax.swing.JTextField();
        jComboBoxPrename = new com.hosv3.gui.component.HosComboBox();
        jPanel13 = new javax.swing.JPanel();
        jLabelPid = new javax.swing.JLabel();
        pidPanel = new com.hospital_os.utility.PIDPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabelGender = new javax.swing.JLabel();
        jComboBoxGender = new javax.swing.JComboBox();
        jLabelAge = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldAge = new com.hospital_os.utility.IntegerTextField();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabelHouse = new javax.swing.JLabel();
        jTextFieldHouse = new javax.swing.JTextField();
        jLabelVillage = new javax.swing.JLabel();
        jTextFieldVillage = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabelRoad = new javax.swing.JLabel();
        jTextFieldRoad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldPhone = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabelTambon = new javax.swing.JLabel();
        jTextFieldChangwat = new com.hospital_os.utility.IntegerTextField();
        jLabelAmpur = new javax.swing.JLabel();
        jComboBoxAmpur = new javax.swing.JComboBox();
        jLabelChangwat = new javax.swing.JLabel();
        jComboBoxChangwat = new javax.swing.JComboBox();
        jTextFieldAmpur = new com.hospital_os.utility.IntegerTextField();
        jTextFieldTambon = new com.hospital_os.utility.IntegerTextField();
        jComboBoxTambon = new javax.swing.JComboBox();
        jPanel18 = new javax.swing.JPanel();
        jButtonSavePatient = new javax.swing.JButton();
        jButtonVisit = new javax.swing.JButton();
        jButtonAddPatient = new javax.swing.JButton();
        jButtonDelPatient = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldHosReferIn = new javax.swing.JTextField();
        jButtonHos = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableVisitLabReferIn = new com.hosv3.gui.component.HJTableSort();
        jPanel22 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHistory = new com.hosv3.gui.component.HJTableSort();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableOrder = new com.hosv3.gui.component.HJTableSort();
        jPanel6 = new javax.swing.JPanel();
        jButtonAddOrder = new javax.swing.JButton();
        jButtonDelOrder = new javax.swing.JButton();
        jButtonSaveOrder = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableResult = new com.hosv3.gui.component.HJTableSort();
        jPanel5 = new javax.swing.JPanel();
        jButtonSaveResult = new javax.swing.JButton();
        jButtonPrintResultLab = new javax.swing.JButton();
        jButtonDischarge = new javax.swing.JButton();
        jLabelStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jTabbedPane4.setMinimumSize(new java.awt.Dimension(710, 330));
        jTabbedPane4.setPreferredSize(new java.awt.Dimension(710, 330));

        jPanel19.setLayout(new java.awt.GridBagLayout());

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("รายชื่อผู้ป่วย"));
        jPanel20.setLayout(new java.awt.GridBagLayout());

        jTextFieldSearchFname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchFnameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel20.add(jTextFieldSearchFname, gridBagConstraints);

        jButtonSearch.setText("Search");
        jButtonSearch.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSearch.setMaximumSize(new java.awt.Dimension(63, 24));
        jButtonSearch.setMinimumSize(new java.awt.Dimension(63, 24));
        jButtonSearch.setPreferredSize(new java.awt.Dimension(63, 24));
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel20.add(jButtonSearch, gridBagConstraints);

        jTablePatientLabReferIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTablePatientLabReferInMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jTablePatientLabReferIn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel20.add(jScrollPane4, gridBagConstraints);

        jTextFieldSearchLname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchLnameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel20.add(jTextFieldSearchLname, gridBagConstraints);

        jLabel8.setText("fname");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel20.add(jLabel8, gridBagConstraints);

        jLabel9.setText("Lname");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel20.add(jLabel9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 0, 2);
        jPanel19.add(jPanel20, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("ข้อมูลผู้ป่วย"));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabelPrename.setText("Prename");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel10.add(jLabelPrename, gridBagConstraints);

        jLabelFname.setText("fname");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel10.add(jLabelFname, gridBagConstraints);

        jTextFieldFname.setMinimumSize(new java.awt.Dimension(88, 21));
        jTextFieldFname.setPreferredSize(new java.awt.Dimension(88, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel10.add(jTextFieldFname, gridBagConstraints);

        jLabelLname.setText("Lname");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel10.add(jLabelLname, gridBagConstraints);

        jTextFieldLname.setMinimumSize(new java.awt.Dimension(88, 21));
        jTextFieldLname.setPreferredSize(new java.awt.Dimension(88, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel10.add(jTextFieldLname, gridBagConstraints);

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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel10.add(jComboBoxPrename, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 3, 3);
        jPanel9.add(jPanel10, gridBagConstraints);

        jPanel13.setLayout(new java.awt.GridBagLayout());

        jLabelPid.setText("PID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel13.add(jLabelPid, gridBagConstraints);

        pidPanel.setText("pidPanel");
        pidPanel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pidPanelFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel13.add(pidPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 3, 3);
        jPanel9.add(jPanel13, gridBagConstraints);

        jPanel11.setLayout(new java.awt.GridBagLayout());

        jLabelGender.setText("Sex");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        jPanel11.add(jLabelGender, gridBagConstraints);

        jComboBoxGender.setMinimumSize(new java.awt.Dimension(70, 21));
        jComboBoxGender.setPreferredSize(new java.awt.Dimension(70, 21));
        jComboBoxGender.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBoxGenderFocusGained(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel11.add(jComboBoxGender, gridBagConstraints);

        jLabelAge.setText("Age");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 0, 2);
        jPanel11.add(jLabelAge, gridBagConstraints);

        jLabel3.setText("Year");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel11.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel11.add(jLabel4, gridBagConstraints);

        jTextFieldAge.setColumns(3);
        jTextFieldAge.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldAge.setText("0");
        jTextFieldAge.setMinimumSize(new java.awt.Dimension(30, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 5);
        jPanel11.add(jTextFieldAge, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 3, 3);
        jPanel9.add(jPanel11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 0, 2);
        jPanel8.add(jPanel9, gridBagConstraints);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("ที่อยู่ผู้ป่วย"));
        jPanel14.setLayout(new java.awt.GridBagLayout());

        jPanel15.setLayout(new java.awt.GridBagLayout());

        jLabelHouse.setText("House");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel15.add(jLabelHouse, gridBagConstraints);

        jTextFieldHouse.setMinimumSize(new java.awt.Dimension(44, 21));
        jTextFieldHouse.setPreferredSize(new java.awt.Dimension(44, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 5);
        jPanel15.add(jTextFieldHouse, gridBagConstraints);

        jLabelVillage.setText("Moo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel15.add(jLabelVillage, gridBagConstraints);

        jTextFieldVillage.setMinimumSize(new java.awt.Dimension(77, 21));
        jTextFieldVillage.setPreferredSize(new java.awt.Dimension(77, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel15.add(jTextFieldVillage, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel14.add(jPanel15, gridBagConstraints);

        jPanel16.setLayout(new java.awt.GridBagLayout());

        jLabelRoad.setText("Road");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 8);
        jPanel16.add(jLabelRoad, gridBagConstraints);

        jTextFieldRoad.setMinimumSize(new java.awt.Dimension(63, 21));
        jTextFieldRoad.setPreferredSize(new java.awt.Dimension(63, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel16.add(jTextFieldRoad, gridBagConstraints);

        jLabel6.setText("Phone");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel16.add(jLabel6, gridBagConstraints);

        jTextFieldPhone.setMinimumSize(new java.awt.Dimension(100, 21));
        jTextFieldPhone.setPreferredSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel16.add(jTextFieldPhone, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel14.add(jPanel16, gridBagConstraints);

        jPanel17.setLayout(new java.awt.GridBagLayout());

        jLabelTambon.setText("Tambon");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel17.add(jLabelTambon, gridBagConstraints);

        jTextFieldChangwat.setColumns(2);
        jTextFieldChangwat.setMinimumSize(new java.awt.Dimension(22, 21));
        jTextFieldChangwat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldChangwatKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel17.add(jTextFieldChangwat, gridBagConstraints);

        jLabelAmpur.setText("Ampur");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel17.add(jLabelAmpur, gridBagConstraints);

        jComboBoxAmpur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAmpurActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 3, 3);
        jPanel17.add(jComboBoxAmpur, gridBagConstraints);

        jLabelChangwat.setText("Changwat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel17.add(jLabelChangwat, gridBagConstraints);

        jComboBoxChangwat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxChangwatActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 3, 3);
        jPanel17.add(jComboBoxChangwat, gridBagConstraints);

        jTextFieldAmpur.setColumns(2);
        jTextFieldAmpur.setMinimumSize(new java.awt.Dimension(22, 21));
        jTextFieldAmpur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldAmpurKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel17.add(jTextFieldAmpur, gridBagConstraints);

        jTextFieldTambon.setColumns(2);
        jTextFieldTambon.setMinimumSize(new java.awt.Dimension(22, 21));
        jTextFieldTambon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldTambonKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel17.add(jTextFieldTambon, gridBagConstraints);

        jComboBoxTambon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTambonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 5, 3);
        jPanel17.add(jComboBoxTambon, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 0);
        jPanel14.add(jPanel17, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 0, 2);
        jPanel8.add(jPanel14, gridBagConstraints);

        jPanel18.setLayout(new java.awt.GridBagLayout());

        jButtonSavePatient.setText("Save");
        jButtonSavePatient.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSavePatient.setMaximumSize(new java.awt.Dimension(60, 26));
        jButtonSavePatient.setMinimumSize(new java.awt.Dimension(60, 26));
        jButtonSavePatient.setPreferredSize(new java.awt.Dimension(60, 26));
        jButtonSavePatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSavePatientActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        jPanel18.add(jButtonSavePatient, gridBagConstraints);

        jButtonVisit.setText("Visit");
        jButtonVisit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonVisit.setMaximumSize(new java.awt.Dimension(60, 26));
        jButtonVisit.setMinimumSize(new java.awt.Dimension(60, 26));
        jButtonVisit.setPreferredSize(new java.awt.Dimension(60, 26));
        jButtonVisit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVisitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel18.add(jButtonVisit, gridBagConstraints);

        jButtonAddPatient.setText("+");
        jButtonAddPatient.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonAddPatient.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonAddPatient.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonAddPatient.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonAddPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddPatientActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel18.add(jButtonAddPatient, gridBagConstraints);

        jButtonDelPatient.setText("-");
        jButtonDelPatient.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonDelPatient.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonDelPatient.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonDelPatient.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonDelPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelPatientActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel18.add(jButtonDelPatient, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("ReferFrom");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel12.add(jLabel5, gridBagConstraints);

        jTextFieldHosReferIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldHosReferInActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel12.add(jTextFieldHosReferIn, gridBagConstraints);

        jButtonHos.setText("...");
        jButtonHos.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonHos.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonHos.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonHos.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonHos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel12.add(jButtonHos, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel18.add(jPanel12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 2);
        jPanel8.add(jPanel18, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel19.add(jPanel8, gridBagConstraints);

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder("รายชื่อผู้ป่วยในจุดบริการ"));
        jPanel24.setLayout(new java.awt.GridBagLayout());

        jTableVisitLabReferIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableVisitLabReferInMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(jTableVisitLabReferIn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel24.add(jScrollPane5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 0, 2);
        jPanel19.add(jPanel24, gridBagConstraints);

        jTabbedPane4.addTab("ค้นหาผู้ป่วย", jPanel19);

        jPanel22.setMinimumSize(new java.awt.Dimension(700, 420));
        jPanel22.setPreferredSize(new java.awt.Dimension(700, 420));
        jPanel22.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("List การรับบริการ"));
        jPanel3.setMinimumSize(new java.awt.Dimension(220, 200));
        jPanel3.setPreferredSize(new java.awt.Dimension(220, 200));
        jPanel3.setRequestFocusEnabled(false);
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jTableHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableHistoryMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableHistory);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel3.add(jScrollPane1, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("FName+Lname");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel7.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jLabelName, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel3.add(jPanel7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 0, 2);
        jPanel22.add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("List รายการ Order"));
        jPanel4.setMinimumSize(new java.awt.Dimension(220, 220));
        jPanel4.setPreferredSize(new java.awt.Dimension(220, 220));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jTableOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableOrderMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTableOrder);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jScrollPane2, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jButtonAddOrder.setText("+");
        jButtonAddOrder.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonAddOrder.setMaximumSize(new java.awt.Dimension(30, 26));
        jButtonAddOrder.setMinimumSize(new java.awt.Dimension(30, 26));
        jButtonAddOrder.setPreferredSize(new java.awt.Dimension(30, 26));
        jButtonAddOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddOrderActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel6.add(jButtonAddOrder, gridBagConstraints);

        jButtonDelOrder.setText("-");
        jButtonDelOrder.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonDelOrder.setMaximumSize(new java.awt.Dimension(30, 26));
        jButtonDelOrder.setMinimumSize(new java.awt.Dimension(30, 26));
        jButtonDelOrder.setPreferredSize(new java.awt.Dimension(30, 26));
        jButtonDelOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelOrderActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel6.add(jButtonDelOrder, gridBagConstraints);

        jButtonSaveOrder.setText("Save");
        jButtonSaveOrder.setMaximumSize(new java.awt.Dimension(60, 26));
        jButtonSaveOrder.setMinimumSize(new java.awt.Dimension(60, 26));
        jButtonSaveOrder.setPreferredSize(new java.awt.Dimension(60, 26));
        jButtonSaveOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveOrderActionPerformed(evt);
            }
        });
        jPanel6.add(jButtonSaveOrder, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel4.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 0, 2);
        jPanel22.add(jPanel4, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("ผลการชันสูตรโรค"));
        jPanel2.setMinimumSize(new java.awt.Dimension(480, 450));
        jPanel2.setPreferredSize(new java.awt.Dimension(480, 450));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jScrollPane3.setViewportView(jTableResult);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel2.add(jScrollPane3, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jButtonSaveResult.setText("Save");
        jButtonSaveResult.setEnabled(false);
        jButtonSaveResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveResultActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(jButtonSaveResult, gridBagConstraints);

        jButtonPrintResultLab.setText("PrintResultLab");
        jButtonPrintResultLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintResultLabActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(jButtonPrintResultLab, gridBagConstraints);

        jButtonDischarge.setText("Discharge");
        jButtonDischarge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDischargeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel5.add(jButtonDischarge, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel2.add(jPanel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 0, 2);
        jPanel22.add(jPanel2, gridBagConstraints);

        jTabbedPane4.addTab("รายการ Lab", jPanel22);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jTabbedPane4, gridBagConstraints);

        jLabelStatus.setText("Status");
        jLabelStatus.setMaximumSize(new java.awt.Dimension(4, 24));
        jLabelStatus.setMinimumSize(new java.awt.Dimension(4, 20));
        jLabelStatus.setOpaque(true);
        jLabelStatus.setPreferredSize(new java.awt.Dimension(4, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jLabelStatus, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxTambonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTambonActionPerformed

        this.jTextFieldAmpur.setText("");
        this.jTextFieldChangwat.setText("");
        this.jTextFieldTambon.setText("");
    }//GEN-LAST:event_jComboBoxTambonActionPerformed

    private void jButtonPrintResultLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintResultLabActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jButtonPrintResultLabActionPerformed

    private void jComboBoxPrenameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxPrenameFocusLost
        jComboBoxPrenameActionPerformed(null);
    }//GEN-LAST:event_jComboBoxPrenameFocusLost

    private void jTextFieldHosReferInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldHosReferInActionPerformed

    }//GEN-LAST:event_jTextFieldHosReferInActionPerformed

    private void jComboBoxPrenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPrenameActionPerformed
        try{
            Prefix thePrefix = (Prefix)jComboBoxPrename.getSelectedItem();
            if(thePrefix!=null){
                Gutil.setGuiData(jComboBoxGender, thePrefix.sex);        
            }
        }
        catch(Exception e){
            Constant.println(e.getMessage());
        }
    }//GEN-LAST:event_jComboBoxPrenameActionPerformed
    private void jTextFieldSearchLnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchLnameActionPerformed
        jButtonSearchActionPerformed(null);
    }//GEN-LAST:event_jTextFieldSearchLnameActionPerformed
    private void jTextFieldSearchFnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchFnameActionPerformed
        jButtonSearchActionPerformed(null);
    }//GEN-LAST:event_jTextFieldSearchFnameActionPerformed
    private void jComboBoxGenderFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxGenderFocusGained
        if(pidPanel.getText().length()!=0)
        {
            boolean result = theLabReferControl.checkPidPatientLabreferinWithPatient(pidPanel.getText());
            boolean result1 = theLabReferControl.checkPidPatientLabreferinWithPatientLabreferin(pidPanel.getText());
            if(result == true || result1 == true)
            {
                 setStatus(Constant.getTextBundle("หมายเลขบัตรประชาชนซ้ำกับผู้ป่วยคนอื่น") +
                         " " +
                         Constant.getTextBundle("กรุณากรอกหมายเลขบัตรประชาชนใหม่อีกครั้ง"),UpdateStatus.WARNING);
            }
        }
    }//GEN-LAST:event_jComboBoxGenderFocusGained
    private void pidPanelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pidPanelFocusLost
        
    }//GEN-LAST:event_pidPanelFocusLost
    private void jButtonDischargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDischargeActionPerformed
        updateVisitLabreferin();
        clearPatient();
        vHistory = null;
        setTableHistory(null);
        setTableOrder(null);
        setTableResultLabHaveData(null);
        setEnableResultOrder(false);
        setEnableOrder(false);
        setEnablePatient(false);
        jButtonAddOrder.setEnabled(false);
        jLabelName.setText("");
        vPatientLabreferin=null;
        setTableListPatientLabreferin(null);  
        searchVisitLabreferin();      
        
    }//GEN-LAST:event_jButtonDischargeActionPerformed
    private void jButtonSaveResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveResultActionPerformed
        setDataToObjectResult();
        saveResultOrderLab();
        setEnableResultOrder(true);
        jButtonSaveResult.setEnabled(false);
        jTableResult.setEnabled(false);        
    }//GEN-LAST:event_jButtonSaveResultActionPerformed
    private void jTableOrderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableOrderMouseReleased
        checkOrderItemId();                    
    }//GEN-LAST:event_jTableOrderMouseReleased
    private void jButtonSaveOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveOrderActionPerformed
        saveOrderItemLabReferIn();  
        jButtonSaveResult.setEnabled(true);
    }//GEN-LAST:event_jButtonSaveOrderActionPerformed
    private void jButtonDelOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelOrderActionPerformed
        if(jTableOrder.getSelectedRows().length == 0)       
             setStatus("ยังไม่มีข้อมูล",UpdateStatus.WARNING);
        else{
             deleteLabReferIn();             
        }
    }//GEN-LAST:event_jButtonDelOrderActionPerformed
    private void jButtonAddOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddOrderActionPerformed
        if(theVisitLabreferin!=null){
            //addLabReferIn();
            if(psep == null){
//                psep = new PanelSetupSearchSub(theHC,this,2);
//                psep.setTitle("รายการ Lab");
                //PanelSetupSearchSub.showDialog(theHC,this,2,"รายการ Lab");
                if(vOrderItem ==null)  vOrderItem = new Vector();
                PanelSetupSearchSub psss = new PanelSetupSearchSub(theHC,this,14); 
                psss.setTitle(Constant.getTextBundle("กำหนดรายการ Lab ชุด"));
                psss.showSearchLabGroup(jTableOrder,vOrderItem,this);
            }
//            if(psep.showSearch()){
//                psep = null;
//            }
            setEnableOrder(true);
        }
        else{
            setStatus("กรุณาเลือกผู้ป่วยที่ visit แล้วก่อน ถึงจะสั่งรายการ order ได้",UpdateStatus.WARNING); 
        }
    }//GEN-LAST:event_jButtonAddOrderActionPerformed
    private void jTableHistoryMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHistoryMouseReleased
        if(jTableHistory.getSelectedRow() == -1)
        {
             setStatus("ยังไม่มีข้อมูล",UpdateStatus.WARNING);            
        }
        else
        {
             vResult=null;
             selectHistoryLabreferin();
             showHospitalRefer();
             searchOrderByVisitId();
             selectTableOrder();
             if(theVisitLabreferin.status.equals("2") || theVisitLabreferin.status.equals("3") )

             {
                setEnableTab3(false);
                jButtonPrintResultLab.setEnabled(true); 
             }
             if(theVisitLabreferin.status.equals("1"))
             {
                setEnableTab3(true);                
             }
             jButtonHos.setEnabled(false);
        } 
    }//GEN-LAST:event_jTableHistoryMouseReleased
    private void jTableVisitLabReferInMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVisitLabReferInMouseReleased
        if(jTableVisitLabReferIn.getSelectedRow() < 0)
        {
             setStatus("ยังไม่มีข้อมูล",UpdateStatus.WARNING);            
        }
        else
        {
             vResult=null;
             jComboBoxPrename.setText("");
             setTableOrder(null);
             setTableResultLab(null);
             setTableResultLabHaveData(null);
             updateOGPatientLabreferinFromTableVisit(); 
             selectVisitLabreferinFormVector(); 
             showHospitalRefer();
             setEnablePatient(true);
             jButtonDischarge.setEnabled(true); 
             jButtonHos.setEnabled(false);
             jTablePatientLabReferIn.clearSelection();
        } 
    }//GEN-LAST:event_jTableVisitLabReferInMouseReleased
    private void jButtonDelPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelPatientActionPerformed
        checkPatientIdInTableVisit();        
    }//GEN-LAST:event_jButtonDelPatientActionPerformed
    private void jComboBoxAmpurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAmpurActionPerformed
        Vector v = theLookupControl.listAddressTmp(
                null,Gutil.getGuiData(jComboBoxAmpur));
        ComboboxModel.initComboBox(jComboBoxTambon,v);
        this.jTextFieldAmpur.setText("");
        this.jTextFieldChangwat.setText("");
        this.jTextFieldTambon.setText("");
    }//GEN-LAST:event_jComboBoxAmpurActionPerformed
    private void jComboBoxChangwatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxChangwatActionPerformed
        String a=Gutil.getGuiData(jComboBoxChangwat);
        Vector v = theLookupControl.listAddressAmp(a);
        ComboboxModel.initComboBox(jComboBoxAmpur,v);
        this.jTextFieldAmpur.setText("");
        this.jTextFieldChangwat.setText("");
        this.jTextFieldTambon.setText("");
    }//GEN-LAST:event_jComboBoxChangwatActionPerformed
    private void jTablePatientLabReferInMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePatientLabReferInMouseReleased
        if(jTablePatientLabReferIn.getSelectedRow() < 0)
        {
             setStatus("ยังไม่มีข้อมูล",UpdateStatus.WARNING);            
        }
        else
        {
             vResult=null;
             setTableOrder(null);
             setTableResultLab(null);
             setTableResultLabHaveData(null);
             updateOGPatientLabreferin(); 
             selectVisitLabreferin(); 
             setEnablePatient(true);  
             jTableVisitLabReferIn.clearSelection();
        }
    }//GEN-LAST:event_jTablePatientLabReferInMouseReleased
    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        searchPatientLabreferin();
//        setEnablePatient(false);
    }//GEN-LAST:event_jButtonSearchActionPerformed
    private void jButtonVisitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVisitActionPerformed
        if(thePatientLabreferin == null){
            setStatus("ไม่มีผู้ป่วย ไม่สามารถ Visit ได้",UpdateStatus.WARNING);  
            return;
        }  
        if(jTextFieldHosReferIn.getText().equals(""))
        {
            setStatus("กรุณากรอกข้อมูลว่า Refer มาจากที่ไหนก่อนการ Visit",UpdateStatus.WARNING);
            return;
        }
        if(theVisitLabreferin != null && theVisitLabreferin.getObjectId() != null)
        {
            setStatus("ผู้ป่วย Visit แล้ว",UpdateStatus.WARNING);
            return;
        }
        actionCommand = true;       
        setDataToObjectVisitLabreferin();
        theLabReferControl.saveVisitPatientLabreferin(theVisitLabreferin,this);
        setEnablePatient(true);
//        searchPatientLabreferin();
        vHistory = theLabReferControl.listVisitLabreferinByPId(thePatientLabreferin.getObjectId());
        setTableHistory(vHistory);
        jButtonHos.setEnabled(false);  
        searchVisitLabreferin();
    }    private void jTextFieldTambonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-LAST:event_jButtonVisitActionPerformed
        if(jTextFieldTambon.getText().length()==2)//GEN-FIRST:event_jTextFieldTambonKeyReleased
            Gutil.setGuiData(jComboBoxTambon, Gutil.getGuiData(jTextFieldChangwat)+Gutil.getGuiData(jTextFieldAmpur)+Gutil.getGuiData(jTextFieldTambon));
    }//GEN-LAST:event_jTextFieldTambonKeyReleased
    private void jTextFieldAmpurKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAmpurKeyReleased
        if(jTextFieldAmpur.getText().length()==2)
        {
            Gutil.setGuiData(jComboBoxAmpur, Gutil.getGuiData(jTextFieldChangwat)+Gutil.getGuiData(jTextFieldAmpur)+"00");
            jTextFieldTambon.setText("");
        }
    }//GEN-LAST:event_jTextFieldAmpurKeyReleased
    private void jTextFieldChangwatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldChangwatKeyReleased
        if(jTextFieldChangwat.getText().length()==2)
        {
            Gutil.setGuiData(jComboBoxChangwat,Gutil.getGuiData(jTextFieldChangwat)+"0000");
            jTextFieldAmpur.setText("");
            jTextFieldTambon.setText("");
        }
    }//GEN-LAST:event_jTextFieldChangwatKeyReleased
    private void jButtonSavePatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSavePatientActionPerformed
        setDataToObjectPatientLabreferin(); 
        boolean ret = savePatientLabreferin();
        if(ret == false)
        {
            return;
        }
        setEnablePatient(true);
//        searchPatientLabreferin();
    }//GEN-LAST:event_jButtonSavePatientActionPerformed
    private void jButtonAddPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddPatientActionPerformed
         clearPatient();         
         thePatientLabreferin = new PatientLabreferin();
         theVisitLabreferin = new VisitLabreferin();
         setEnablePatient(true);
         jComboBoxPrename.setText("");
         jButtonDelPatient.setEnabled(false);
         jButtonVisit.setEnabled(false);
    }//GEN-LAST:event_jButtonAddPatientActionPerformed
    private void jButtonHosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHosActionPerformed
        Office of = new Office();
        //of.setObjectId(theVisitLabreferin.hos_referin);                
        if(DialogOffice.showDialog(theHC,this,of)){
           // theVisitLabreferin.hos_referin = of.getObjectId();
            hospitalRefer = "";
            hospitalRefer = of.getObjectId();
            jTextFieldHosReferIn.setText(of.name);
        }
    }//GEN-LAST:event_jButtonHosActionPerformed
    private void listOffice1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listOffice1KeyReleased
    }//GEN-LAST:event_listOffice1KeyReleased
    private void listOffice1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listOffice1MouseReleased
    }//GEN-LAST:event_listOffice1MouseReleased
    private void comboBoxAmphur1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxAmphur1ActionPerformed
    }//GEN-LAST:event_comboBoxAmphur1ActionPerformed
    private void comboBoxChangwat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxChangwat1ActionPerformed
    }//GEN-LAST:event_comboBoxChangwat1ActionPerformed
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        dispose();
    }//GEN-LAST:event_closeDialog
     
    /**
     *ไม่ให้สามารถแก้ไขได้ เมื่อยังไม่กดปุ่ม +
     *หรือต้องการแก้ไข
     */
    private void setEnablePatient(boolean var)
    {   
        jTextFieldAmpur.setEnabled(var);
        jTextFieldChangwat.setEditable(var);
        jTextFieldTambon.setEnabled(var);        
        jTextFieldAge.setEditable(var);        
        jTextFieldHouse.setEnabled(var); 
        jTextFieldFname.setEnabled(var);
        jTextFieldLname.setEnabled(var);
        jTextFieldPhone.setEnabled(var);
        jTextFieldRoad.setEnabled(var);
        jTextFieldVillage.setEnabled(var);
        pidPanel.setEnabled(var); 
        
        jComboBoxPrename.setEnabled(var);
        jComboBoxGender.setEnabled(var);
        jComboBoxChangwat.setEnabled(var); 
        jComboBoxAmpur.setEnabled(var); 
        jComboBoxTambon.setEnabled(var); 
        
        jButtonDelPatient.setEnabled(var);        
        jButtonHos.setEnabled(var);  
        jButtonSavePatient.setEnabled(var);        
        jButtonVisit.setEnabled(var);            
    }
    
    /**
     *ไม่ให้สามารถแก้ไขได้ เมื่อยังไม่กดปุ่ม +
     *หรือต้องการแก้ไข
     */
    private void setEnableOrder(boolean var)
    {   
        jButtonDelOrder.setEnabled(var);        
        jButtonSaveOrder.setEnabled(var);                     
    }
    
    /**
     *ไม่ให้สามารถแก้ไขได้ เมื่อยังไม่กดปุ่ม +
     *หรือต้องการแก้ไข
     */
    private void setEnableResultOrder(boolean var)
    {   
        jButtonPrintResultLab.setEnabled(var);        
        jButtonSaveResult.setEnabled(var); 
        jButtonDischarge.setEnabled(var); 
    }
    
    private void setEnableTab3(boolean var)
    {
        //nabled(var);        
        jButtonAddOrder.setEnabled(var);
        jButtonDelOrder.setEnabled(var);
        jButtonSaveOrder.setEnabled(var);
        jTableResult.setEnabled(var);
        jButtonSaveResult.setEnabled(var);
        jButtonDischarge.setEnabled(var);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    /**
     *เคลียร์ text เพื่อรอลงข้อมูล
     */
private void clearPatient()
    {   
        jTextFieldAmpur.setText("");
        jTextFieldChangwat.setText("");
        jTextFieldTambon.setText("");
        jTextFieldAge.setText(""); 
        jTextFieldFname.setText(""); 
        jTextFieldHosReferIn.setText("");
        jTextFieldHouse.setText("");
        jTextFieldLname.setText("");
        jTextFieldPhone.setText("");
        jTextFieldRoad.setText("");
        jTextFieldVillage.setText("");  
        pidPanel.setText("");
    }
    /*
     *นำข้อมูลที่มีการใส่ในหน้า GUI Add ไว้ใน Obj
     */ 
    private void setDataToObjectPatientLabreferin()
    {   
        thePatientLabreferin.prefix_id = jComboBoxPrename.getText();
        Constant.println("p.prefix_id:" + thePatientLabreferin.prefix_id);
        if(thePatientLabreferin.prefix_id.equals("")){
            thePatientLabreferin.prefix_id = "add:" + jComboBoxPrename.getDetail();
        }
        Constant.println("p.prefix_id:" + thePatientLabreferin.prefix_id);
        thePatientLabreferin.fname = jTextFieldFname.getText();
        thePatientLabreferin.lname = jTextFieldLname.getText();
        thePatientLabreferin.pid = pidPanel.getText();
        thePatientLabreferin.sex = Gutil.getGuiData(jComboBoxGender);
        thePatientLabreferin.age = jTextFieldAge.getText();
        thePatientLabreferin.house = jTextFieldHouse.getText();
        thePatientLabreferin.village = jTextFieldVillage.getText(); 
        thePatientLabreferin.road = jTextFieldRoad.getText();
        thePatientLabreferin.phone = jTextFieldPhone.getText();
        thePatientLabreferin.changwat = ComboboxModel.getCodeComboBox(jComboBoxChangwat);
        thePatientLabreferin.amphur = ComboboxModel.getCodeComboBox(jComboBoxAmpur);
        thePatientLabreferin.tambon = ComboboxModel.getCodeComboBox(jComboBoxTambon);
    }
    
    /**
     *นำข้อมูลที่มีการใส่ในหน้า GUI Add ไว้ใน Obj
     */ 
    private void setDataToObjectVisitLabreferin(){  
        if(theVisitLabreferin == null)
        {
            theVisitLabreferin = new VisitLabreferin();
        }
        theVisitLabreferin.patient_labreferin_id = thePatientLabreferin.getObjectId();      
        theVisitLabreferin.status = "1"; 
        theVisitLabreferin.hos_referin = hospitalRefer;
    }
    /**
     *save ข้อมูลลงฐานข้อมูลถ้าเป็นคนใหม่ แต่ถ้าเป็นคนเก่าก็ update
     */
    private boolean savePatientLabreferin()
    { 
        boolean ret = theLabReferControl.savePatientLabreferin(thePatientLabreferin, this);
        return ret;
    }
    
    /**
     *ค้นหาผู้ป่วยของ Labreferin
     */    
    private void searchPatientLabreferin()
    {         

        vPatientLabreferin = theLabReferControl.listPatientLabreferinByName(jTextFieldSearchFname.getText(),jTextFieldSearchLname.getText());        
        if(vPatientLabreferin == null || vPatientLabreferin.isEmpty())
            setStatus("ไม่เจอผู้ป่วยชื่อนี้ในฐานข้อมูล",UpdateStatus.WARNING);
                 
        setTableListPatientLabreferin(vPatientLabreferin);               
    }
    
    /**
     *show patient labreferin ที่มีการ add ไว้
     **/
    private void setTableListPatientLabreferin(Vector vPatientLabreferin)
    {   
        TaBleModel tm ;
        if(vPatientLabreferin != null)
        {   
            tm= new TaBleModel(column,vPatientLabreferin.size());            
            for(int i=0 ;i<vPatientLabreferin.size(); i++)
            {  
                PatientLabreferin p = (PatientLabreferin)vPatientLabreferin.get(i);
                tm.setValueAt(p.fname,i,0);                
                tm.setValueAt(p.lname,i,1);
            }           
        }
        else
        {   
            tm= new TaBleModel(column,0);
        }
            jTablePatientLabReferIn.setModel(tm); 
    } 
    
    /**
     *show ตามที่เลือก จากตาราง patient
     */
    private void updateOGPatientLabreferin()
    {   
        int row = jTablePatientLabReferIn.getSelectedRow();
        thePatientLabreferin = new PatientLabreferin();        
        thePatientLabreferin = (PatientLabreferin)vPatientLabreferin.get(row);
        jTextFieldFname.setText(thePatientLabreferin.fname);     
        jTextFieldLname.setText(thePatientLabreferin.lname);
        pidPanel.setText(thePatientLabreferin.pid);
        Gutil.setGuiData(jComboBoxGender,thePatientLabreferin.sex);
        jComboBoxPrename.setText(thePatientLabreferin.prefix_id);
        jTextFieldAge.setText(thePatientLabreferin.age);
        jTextFieldHosReferIn.setText("");
        jTextFieldHouse.setText(thePatientLabreferin.house);
        jTextFieldVillage.setText(thePatientLabreferin.village);
        jTextFieldRoad.setText(thePatientLabreferin.road);
        jTextFieldPhone.setText(thePatientLabreferin.phone);
        jLabelName.setText(thePatientLabreferin.fname + " " + thePatientLabreferin.lname);
        Gutil.setGuiData(jComboBoxChangwat,thePatientLabreferin.changwat);
        Gutil.setGuiData(jComboBoxAmpur,thePatientLabreferin.amphur);
        Gutil.setGuiData(jComboBoxTambon,thePatientLabreferin.tambon);
     
        this.jTextFieldAmpur.setText("");
        this.jTextFieldChangwat.setText("");
        this.jTextFieldTambon.setText("");
        /*List ประวัติการเข้ารับบริการ ของผู้ป่วยที่ถูกเลือก*/
        vHistory = theLabReferControl.listVisitLabreferinByPId(thePatientLabreferin.getObjectId());
        setTableHistory(vHistory);
    } 
  
    /**
     *เก็บใส่ object ตามที่เลือก
     */
    private void selectVisitLabreferin()
    {  
        theVisitLabreferin = new VisitLabreferin();
        theVisitLabreferin = theLabReferControl.readVisitInProcessByPatientId(thePatientLabreferin.getObjectId());                
        if(theVisitLabreferin==null)
        {
            jButtonHos.setEnabled(true);
            return;
        }
        else
        {
            jButtonHos.setEnabled(false);
        }
    }
    private void checkPatientIdInTableVisit()
    {
        if(theLabReferControl.checkPatientInTableVisit(thePatientLabreferin.getObjectId())==true)
        {
            setStatus("ผู้ป่วยเคยเข้ารับบริการแล้ว ไม่สามารถลบได้",UpdateStatus.WARNING);
        }
        else
        {
            theLabReferControl.deletePatientLabreferIn(thePatientLabreferin);
            clearPatient();
            setEnablePatient(false);
            setTableListPatientLabreferin(null);
            setStatus("การลบข้อมูลเสร็จสิ้น",UpdateStatus.COMPLETE);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    /**
     *ค้นหาผู้ป่วยของ Visitreferin เฉพาะที่เข้าสู่กระบวนการ
     */
    private void searchVisitLabreferin()
    {         
        vVisitLabreferin = theLabReferControl.listVisitLabreferin();
        setTableListVisitLabreferin(vVisitLabreferin);               
    }
    
    /**
     *show visit labreferin ที่มีการ add ไว้
     **/
    private void setTableListVisitLabreferin(Vector vVisitLabreferin)
    {   
        TaBleModel tm ;
        if(vVisitLabreferin != null)
        {   
            tm= new TaBleModel(column,vVisitLabreferin.size());  
            for(int i=0 ;i<vVisitLabreferin.size(); i++)
            {  
                VisitLabreferin v = (VisitLabreferin)vVisitLabreferin.get(i); 
                PatientLabreferin p = theLabReferControl.readPatientLabByPk(v.patient_labreferin_id); 
                if(p != null){
                    tm.setValueAt(p.fname,i,0);                
                    tm.setValueAt(p.lname,i,1);
                }
            }           
        }
        else
        {   
            tm= new TaBleModel(column,0);
        }
            jTableVisitLabReferIn.setModel(tm); 
    } 
    
    /**
     *show ตามที่เลือก จากตาราง visit
     */
    private void updateOGPatientLabreferinFromTableVisit()
    {   
        int row = jTableVisitLabReferIn.getSelectedRow();
        thePatientLabreferin = new PatientLabreferin();        
        thePatientLabreferin = theLabReferControl.readPatientLabByPk(((VisitLabreferin)vVisitLabreferin.get(row)).patient_labreferin_id);
        Gutil.setGuiData(jComboBoxPrename,thePatientLabreferin.prefix_id);
        jTextFieldFname.setText(thePatientLabreferin.fname);     
        jTextFieldLname.setText(thePatientLabreferin.lname);
        pidPanel.setText(thePatientLabreferin.pid);
        Gutil.setGuiData(jComboBoxGender,thePatientLabreferin.sex);
        jTextFieldAge.setText(thePatientLabreferin.age);
        jTextFieldHouse.setText(thePatientLabreferin.house);
        jTextFieldVillage.setText(thePatientLabreferin.village);
        jTextFieldRoad.setText(thePatientLabreferin.road);
        jTextFieldPhone.setText(thePatientLabreferin.phone);
        jLabelName.setText(thePatientLabreferin.fname + " " + thePatientLabreferin.lname);
        Gutil.setGuiData(jComboBoxChangwat,thePatientLabreferin.changwat);
        Gutil.setGuiData(jComboBoxAmpur,thePatientLabreferin.amphur);
        Gutil.setGuiData(jComboBoxTambon,thePatientLabreferin.tambon); 
    
        /*List ประวัติการเข้ารับบริการ ของผู้ป่วยที่ถูกเลือก*/
        vHistory = theLabReferControl.listVisitLabreferinByPId(thePatientLabreferin.getObjectId());
        setTableHistory(vHistory);
    } 
    /**
     *show ประวัติการเข้ารับบริการ
     **/
    private void setTableHistory(Vector vHistory)
    {   
        TaBleModel tm ;
        if(vHistory != null)
        {   
            tm= new TaBleModel(col_jTableHistory,vHistory.size());            
            for(int i=0 ;i<vHistory.size(); i++)
            {  
                tm.setValueAt(String.valueOf(i+1),i,0);                
                tm.setValueAt(DateUtil.convertFieldDate(((VisitLabreferin)vHistory.get(i)).begin_visit_time),i,1);
            }           
        }
        else
        {   
            tm= new TaBleModel(col_jTableHistory,0);
        }
            jTableHistory.setModel(tm); 
    } 
    /**
     *เก็บใส่ object visit labreferin ตามที่เลือก
     */
    private void selectVisitLabreferinFormVector()
    {   
        int row = jTableVisitLabReferIn.getSelectedRow();
        theVisitLabreferin = new VisitLabreferin();        
        theVisitLabreferin = (VisitLabreferin)vVisitLabreferin.get(row);       
    }
    
    private void showHospitalRefer()
    {
        if(theVisitLabreferin.hos_referin.equalsIgnoreCase("null") || theVisitLabreferin.hos_referin.equals(""))        
            jTextFieldHosReferIn.setText("");
        else
            jTextFieldHosReferIn.setText(((Office)theLookupControl.readHospitalByCode(theVisitLabreferin.hos_referin)).name);        
    }
    /**
     *เก็บใส่ object order item labreferin ตามที่เลือก
     */    
    private void selectHistoryLabreferin()
    {   
        int row = jTableHistory.getSelectedRow();
        theVisitLabreferin = new VisitLabreferin();        
        theVisitLabreferin = (VisitLabreferin)vHistory.get(row);        
    }     
    /**
     *ค้นหา order ของแต่ละ visit
     */
    private void searchOrderByVisitId()
    {         
        vOrderItem = theLabReferControl.listOrderByVisitId(theVisitLabreferin.getObjectId());        
        setTableOrder(vOrderItem);
        if(theLabReferControl.checkVisitInResult(theVisitLabreferin.getObjectId())==true){
            vResult = theLabReferControl.listResultByVId(theVisitLabreferin.getObjectId());
            setTableResultLabHaveData(vResult);
            jButtonSaveResult.setEnabled(true);
        }
        else
            setTableResultLabHaveData(null);
    }
    
//    private void addLabReferIn()
//    {   
//        if(psep==null){
//            psep = new PanelSetupSearchSub(theHC,theUS,2);
//            psep.setTitle("รายการ Lab");
//        }
//        if(psep.showSearch())
//            psep = null;
//        //System.gc();
//    }
    
    /*Show ความเป็นไปของโปรแกรม*/
    public void setStatus(String str,int status) 
    {
        ThreadStatus theTT = new ThreadStatus(this.getJFrame(),this.jLabelStatus);
        theTT.start();
        str = Constant.getTextBundle(str);
        jLabelStatus.setText(" " + str);
        Constant.println("----SetStatus---- " + str);
        if(status == UpdateStatus.WARNING){
            jLabelStatus.setBackground(Color.YELLOW);
        }
        if(status == UpdateStatus.COMPLETE){
            jLabelStatus.setBackground(Color.GREEN);
        }
        if(status == UpdateStatus.ERROR){
            jLabelStatus.setBackground(Color.RED);
        }
    }
    
    public void notifysavePatientLabreferin(PatientLabreferin p, String status) 
    {
        thePatientLabreferin = p;
    }
    
    public void notifysaveVisitPatientLabreferin(VisitLabreferin visitLabreferin, String msg) 
    {
        theVisitLabreferin = visitLabreferin;
    }
    //////////////////////////////////////////////////////////////////////////
    public void updateGOOrderItem(Vector vOrderItem){
        
    }
   ///////////////////////////////////////////////////////////////////////////
    /**
     *โครตซับซ้อน
     *หน้ามี่การเปลี่ยนแปลงฟั่งก์ชั่นใหม่
     */
    public void updateOGOrderItem(Item item) 
    { 
        String date_time = theHC.theLookupControl.getTextCurrentDateTime();
        String CommonName="";
        int have = 0;
        if(vResult!=null){            
            for(int i=0; i<vResult.size(); i++){
                if(item.getObjectId().equals(((OrderResultLabreferin)vResult.get(i)).item_id)){
                    have = have + 1;
                    CommonName = String.valueOf((theLabReferControl.readOrderItemByPk(((OrderResultLabreferin)vResult.get(i)).order_item_labreferin_id)).common_name);
                }                                
            }      
            if(have>0){                
                int rs = JOptionPane.showConfirmDialog(this, Constant.getTextBundle("มี Item ตัวนี้ ใน ") + CommonName + " "+Constant.getTextBundle("ยังต้องการจะเพิ่ม Item ตัวนี้อีกหรือไม่"), Constant.getTextBundle("ยืนยันการเพิ่ม"), JOptionPane.YES_NO_OPTION);     
                if(rs == JOptionPane.YES_OPTION){                                  
                        OrderItemLabreferin oilri = new OrderItemLabreferin();
                        oilri.item_id = item.getObjectId();
                        oilri.common_name = item.common_name;            
                        oilri.status = "1";
                        oilri.user_vertify = theHO.theEmployee.getObjectId();
                        oilri.time_vertify = date_time;
                        oilri.visit_labreferin_id = theVisitLabreferin.getObjectId();
                        if(oilri != null){
                            if(vOrderItem==null)
                                vOrderItem = new Vector();                            
                            vOrderItem.add(oilri);
                            setTableOrder(vOrderItem);
                            setStatus("ข้อมูลถูกเพิ่มแล้ว",UpdateStatus.COMPLETE);  
                        }                    
                 }  
                 if(rs == JOptionPane.NO_OPTION){
                    setStatus("ข้อมูลยังไม่ถูกเพิ่ม",UpdateStatus.WARNING);
                 }                 
            }
            else
            {  
                OrderItemLabreferin oilri = new OrderItemLabreferin();
                oilri.item_id = item.getObjectId();
                oilri.common_name = item.common_name;            
                oilri.status = "1";
                oilri.user_vertify = theHO.theEmployee.getObjectId();
                oilri.time_vertify = date_time;
                oilri.visit_labreferin_id = theVisitLabreferin.getObjectId();
                if(oilri != null)
                {
                    if(vOrderItem==null)
                        vOrderItem = new Vector();
                    if(vOrderItem.size()==0){
                        vOrderItem.add(oilri);
                        setTableOrder(vOrderItem);
                        setStatus("ข้อมูลถูกเพิ่มแล้ว",UpdateStatus.COMPLETE);
                    }
                    else{
                        int same1 =0;
                        int rowsame1 =0;
                        for(int j = 0 ; j< vOrderItem.size() ; j++){   
                            if(((OrderItemLabreferin)vOrderItem.get(j)).item_id.equals(item.getObjectId())){ 
                                same1 = 1;
                                rowsame1 = j ;
                            }
                        }
                        if(same1 ==0){   
                            setTableOrder(null);
                            vOrderItem.add(oilri);                    
                            setTableOrder(vOrderItem);
                            setStatus("ข้อมูลถูกเพิ่มแล้ว",UpdateStatus.COMPLETE);
                        }
                    }           
                }
            }
        }
        else{
            OrderItemLabreferin oilri = new OrderItemLabreferin();
            oilri.item_id = item.getObjectId();
            oilri.common_name = item.common_name;            
            oilri.status = "1";
            oilri.user_vertify = theHO.theEmployee.getObjectId();
            oilri.time_vertify = date_time;
            oilri.visit_labreferin_id = theVisitLabreferin.getObjectId();
            if(oilri != null)
            {
                if(vOrderItem==null)
                    vOrderItem = new Vector();
                if(vOrderItem.size()==0)
                {
                    vOrderItem.add(oilri);
                    setTableOrder(vOrderItem);
                    setStatus("ข้อมูลถูกเพิ่มแล้ว",UpdateStatus.COMPLETE);
                }
                else
                {
                    int same =0;
                    int rowsame =0;
                    for(int i = 0 ; i< vOrderItem.size() ; i++)
                    {   
                        if(((OrderItemLabreferin)vOrderItem.get(i)).item_id.equals(item.getObjectId()))
                        {                             
                            same = 1;
                            rowsame = i ;
                        }
                    }
                    if(same ==0)
                    {   
                        setTableOrder(null);
                        vOrderItem.add(oilri);                    
                        setTableOrder(vOrderItem);
                        setStatus("ข้อมูลถูกเพิ่มแล้ว",UpdateStatus.COMPLETE);
                    }
                }            
            } 
        }
    }
    
    private void setTableOrder(Vector vOrderItem){   
        TaBleModel tm ;
       
        if(vOrderItem != null){
            tm= new TaBleModel(col_jTableOrder,vOrderItem.size());
            for(int i=0 ;i<vOrderItem.size(); i++){  
                tm.setValueAt(((OrderItemLabreferin)vOrderItem.get(i)).common_name,i,0);
            }            
        }
        else{   
            tm= new TaBleModel(col_jTableOrder,0);
        }
            jTableOrder.setModel(tm);                  
    }
    
    ///////////////////////////////////////////////////////////////////////////
    private void deleteLabReferIn()
    {   
        int[] row = jTableOrder.getSelectedRows();
        int count = 0;
        OrderItemLabreferin oilri = new OrderItemLabreferin();            
        for(int i=row.length-1;i>=0;i--){
            oilri = (OrderItemLabreferin)vOrderItem.get(row[i]);
            if(oilri != null){   
                if(oilri.getObjectId()!= null){   
                    int rs = JOptionPane.NO_OPTION;
                    if(count == 0){
                        rs = JOptionPane.showConfirmDialog(this, Constant.getTextBundle("แน่ใจแล้วหรือว่าจะลบ Lab ตัว เพราะถ้าคุณได้กรอกผลของ Lab ตัวนี้แล้ว ผลก็จะหายไปด้วย"), Constant.getTextBundle("ยืนยันการลบ"), JOptionPane.YES_NO_OPTION);                         
                    }
                    if(rs == JOptionPane.YES_OPTION){                                    
                        vOrderItem.remove(row[i]);
                        setTableOrder(vOrderItem);                   
                        theLabReferControl.deleteOrderItemLabReferIn(oilri); 
                        //theLabReferControl.deleteOrderResultLabReferIn(oilri.getObjectId());
                        selectTableOrder();                        
                     }  
                     if(rs == JOptionPane.NO_OPTION){
                        setStatus("ข้อมูลยังไม่ถูกลบ",UpdateStatus.WARNING);
                     }
                }
                else{
                    vOrderItem.remove(row[i]);
                    setTableOrder(vOrderItem);
                    selectTableOrder(); 
                }
            } 
        }
        oilri = null;
    }  
        
    private void  saveOrderItemLabReferIn()
    {   
        theLabReferControl.saveOrderItemLabReferin(vOrderItem);       
        setStatus("บันทึกรายการ order",UpdateStatus.COMPLETE);       
    }
    
    
    private void selectTableOrder()
    {        
        if(theLabReferControl.checkVisitInResult(theVisitLabreferin.getObjectId())== true){
              
            vTemp = new Vector()  ;          
            vResult = theLabReferControl.listResultByVId(theVisitLabreferin.getObjectId());
            for(int o=0; o<vOrderItem.size(); o++)
            {
                OrderItemLabreferin oil = (OrderItemLabreferin)vOrderItem.get(o);
                if(theLabReferControl.checkOrderItemIdInResult(oil.getObjectId())==false)
                {
                    vLabResultItem = theOrderControl.readLabResultItem(oil.item_id);
                    for(int j=0;j<vLabResultItem.size();j++)
                    {
                        vTemp.add((LabResultItem)vLabResultItem.get(j));
                    }                    
                }               
            }
            vLabSet = new Vector();
            for(int p=0; p<vTemp.size(); p++)
            {
                theOrderResultLabreferin = new OrderResultLabreferin();
                theOrderResultLabreferin.name = ((LabResultItem)vTemp.get(p)).name;
                theOrderResultLabreferin.result = "";
                theOrderResultLabreferin.unit = ((LabResultItem)vTemp.get(p)).unit;
                theOrderResultLabreferin.patient_labreferin_id = thePatientLabreferin.getObjectId();
                theOrderResultLabreferin.visit_labreferin_id = theVisitLabreferin.getObjectId();
                theOrderResultLabreferin.item_id = ((LabResultItem)vTemp.get(p)).item_id;
                     
                int result = 0;
                vLabSet = theLabReferControl.listLabSetByItemId(((LabResultItem)vTemp.get(p)).item_id);
                if(vLabSet==null || vLabSet.isEmpty())
                    result = 0;
                else
                {
                    for(int m=0; m<vLabSet.size(); m++)
                    {                             
                        for(int n=0; n<vOrderItem.size(); n++)
                        {
                            if(theLabReferControl.readLabGroupByPk(((LabSet)vLabSet.get(m)).lab_group_id).item_id.equals(((OrderItemLabreferin)vOrderItem.get(n)).item_id))
                                result = result + 1;                         
                        }
                    }
                }
                if(result==0)
                {/*กรณีไม่ใช่ Lab Group*/
                    theOrderResultLabreferin.order_item_labreferin_id = theLabReferControl.readOrderItemByItemIdAndVisitId(((LabResultItem)vTemp.get(p)).item_id, theVisitLabreferin.getObjectId()).getObjectId();                                                            
                }
                else
                {/*กรณี Lab Group*/
                    for(int a=0; a<vLabSet.size(); a++)
                    {
                        for(int b=0; b<vOrderItem.size(); b++)
                        {
                            if(theLabReferControl.readLabGroupByPk(((LabSet)vLabSet.get(a)).lab_group_id).item_id.equals(((OrderItemLabreferin)vOrderItem.get(b)).item_id))
                            {
                                theOrderResultLabreferin.order_item_labreferin_id = ((OrderItemLabreferin)vOrderItem.get(b)).getObjectId();                                                                                           
                            }
                        }
                    }                    
                }               
                vResult.add(theOrderResultLabreferin);
                theOrderResultLabreferin=null;
            }
            setTableResultLabHaveData(vResult);
            haveResult=true;             
        }
        else
        {
            vTemp = new Vector();
            for(int i=0;i<vOrderItem.size();i++)
            {
                OrderItemLabreferin oil = (OrderItemLabreferin)vOrderItem.get(i);
                vLabResultItem = theOrderControl.readLabResultItem(oil.item_id);
                for(int j=0;j<vLabResultItem.size();j++)
                {
                    vTemp.add((LabResultItem)vLabResultItem.get(j));
                }
            }           
            setTableResultLab(vTemp); 
            haveResult=false;            
        }
        if(theLabReferControl.checkVisitInOrder(theVisitLabreferin.getObjectId())==true)
            jButtonSaveResult.setEnabled(true);
        else
            jButtonSaveResult.setEnabled(false);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    /**
     *เซตตารางResult
     */
    private void setTableResultLab(Vector vc)
    {   
        TaBleModel tm ;
        if(vc != null){   
            tm= new TaBleModel(col_jTableResult,vc.size());
            tm.setEditingCol(1);
            for(int i=0 ;i<vc.size(); i++){  
                tm.setValueAt(((LabResultItem)vc.get(i)).name,i,0);                
                tm.setValueAt("",i,1);                
                tm.setValueAt(((LabResultItem)vc.get(i)).unit,i,2);
            }
        }
        else{   
            tm= new TaBleModel(col_jTableResult,0);
        }
        jTableResult.setModel(tm);
        jTableResult.getColumnModel().getColumn(1).setCellRenderer(TableRenderer.getRendererRight());
        jTableResult.getColumnModel().getColumn(2).setCellRenderer(TableRenderer.getRendererCenter());
    }
    
    ///////////////////////////////////////////////////////////////////////////
    /**
     *เซตตารางResultตอนที่มีข้อมูล
     */
    private void setTableResultLabHaveData(Vector vResult)
    {
        TaBleModel tm ;
        if(vResult != null){
            tm= new TaBleModel(col_jTableResult,vResult.size());
            tm.setEditingCol(1);
            for(int i=0;i<vResult.size();i++){
               tm.setValueAt(((OrderResultLabreferin)vResult.get(i)).name ,i ,0);               
               if(((OrderResultLabreferin)vResult.get(i)).result.equals("") || ((OrderResultLabreferin)vResult.get(i)).result==null || ((OrderResultLabreferin)vResult.get(i)).result.equals("null"))
                   tm.setValueAt("" ,i ,1); 
               else
                   tm.setValueAt(((OrderResultLabreferin)vResult.get(i)).result ,i ,1);    
               tm.setValueAt(((OrderResultLabreferin)vResult.get(i)).unit ,i ,2);
            }
        }
        else
        {
            tm= new TaBleModel(col_jTableResult,0);
        }
        jTableResult.setModel(tm);
        jTableResult.getColumnModel().getColumn(1).setCellRenderer(TableRenderer.getRendererRight());
        jTableResult.getColumnModel().getColumn(2).setCellRenderer(TableRenderer.getRendererCenter());
    }  
    
    //////////////////////////////////////////////////////////////////////////
    /**
     *เซตข้อมูลในObjectResult
     */
    private void setDataToObjectResult()
    {   
        String date_time = theHC.theLookupControl.getTextCurrentDateTime();
        if(haveResult==false)
        {   
            vLabSet = new Vector();
            vOrderItemUpdate = new Vector();
            vOrderResultLabreferin = new Vector();
            for(int i=0; i<vTemp.size(); i++){   
                theOrderResultLabreferin = new OrderResultLabreferin();
                theOrderResultLabreferin.name = (String)jTableResult.getValueAt(i,0);
                theOrderResultLabreferin.result = (String)jTableResult.getValueAt(i,1);
                theOrderResultLabreferin.unit = (String)jTableResult.getValueAt(i,2);
                theOrderResultLabreferin.item_id = ((LabResultItem)vTemp.get(i)).item_id;
                int result = 0;
                vLabSet = theLabReferControl.listLabSetByItemId(((LabResultItem)vTemp.get(i)).item_id);
                if(vLabSet==null || vLabSet.isEmpty()){
                    result = 0;
                }
                else{
                    for(int m=0; m<vLabSet.size(); m++){                             
                        for(int n=0; n<vOrderItem.size(); n++){
                            if(theLabReferControl.readLabGroupByPk(((LabSet)vLabSet.get(m)).lab_group_id).item_id.equals(((OrderItemLabreferin)vOrderItem.get(n)).item_id))
                                result = result + 1;                         
                        }
                    }
                }
                if(result==0)
                {/*กรณีไม่ใช่ Lab Group*/ 
                    theOrderResultLabreferin.order_item_labreferin_id = theLabReferControl.readOrderItemByItemIdAndVisitId(((LabResultItem)vTemp.get(i)).item_id, theVisitLabreferin.getObjectId()).getObjectId();
                    if(!((String)jTableResult.getValueAt(i,1)).equals(""))
                    {
                        theOrderItemLabreferin = new OrderItemLabreferin();                          
                        theOrderItemLabreferin = theLabReferControl.readOrderItemByItemIdAndVisitId(((LabResultItem)vTemp.get(i)).item_id, theVisitLabreferin.getObjectId());
                        theOrderItemLabreferin.status = "2";
                        vOrderItemUpdate.add(theOrderItemLabreferin);
                        theOrderItemLabreferin = null;
                    }
                }
                else
                {/*กรณี Lab Group*/ 
                    for(int a=0; a<vLabSet.size(); a++)
                    {
                        for(int b=0; b<vOrderItem.size(); b++)
                        {
                            if(theLabReferControl.readLabGroupByPk(((LabSet)vLabSet.get(a)).lab_group_id).item_id.equals(((OrderItemLabreferin)vOrderItem.get(b)).item_id))
                            {
                                theOrderResultLabreferin.order_item_labreferin_id = ((OrderItemLabreferin)vOrderItem.get(b)).getObjectId();                                                             
                                if(!((String)jTableResult.getValueAt(i,1)).equals(""))
                                {
                                    theOrderItemLabreferin = new OrderItemLabreferin();
                                    theOrderItemLabreferin = (OrderItemLabreferin)vOrderItem.get(b);
                                    theOrderItemLabreferin.status = "2";
                                    vOrderItemUpdate.add(theOrderItemLabreferin);
                                    theOrderItemLabreferin = null;
                                }
                            }
                        }
                    }                   
                }
                theOrderResultLabreferin.patient_labreferin_id = thePatientLabreferin.getObjectId();
                theOrderResultLabreferin.visit_labreferin_id = theVisitLabreferin.getObjectId();
                theOrderResultLabreferin.user_report = theHO.theEmployee.getObjectId();
                theOrderResultLabreferin.time_report = date_time;
                vOrderResultLabreferin.add(theOrderResultLabreferin);
                theOrderResultLabreferin = null;
            } 
            vLabSet=null;
        }
        if(haveResult==true)
        {
            vOrderItemUpdate = new Vector() ;
            vOrderItemReverseUpdate = new Vector();
            for(int i=0; i<vResult.size(); i++)
            {   
                theOrderResultLabreferin = new OrderResultLabreferin();
                theOrderResultLabreferin = (OrderResultLabreferin)vResult.get(i);
                theOrderResultLabreferin.result = (String)jTableResult.getValueAt(i,1);
                theOrderResultLabreferin.user_report = theHO.theEmployee.getObjectId();
                theOrderResultLabreferin.time_report = date_time;
                vOrderResultLabreferin.add(theOrderResultLabreferin);        
                if(!((String)jTableResult.getValueAt(i,1)).equals(""))
                {
                    theOrderItemLabreferin = new OrderItemLabreferin();
                    theOrderItemLabreferin = theLabReferControl.readOrderItemByOrderItemIdAndVisitId(((OrderResultLabreferin)vResult.get(i)).order_item_labreferin_id);
                    theOrderItemLabreferin.status = "2";
                    vOrderItemUpdate.add(theOrderItemLabreferin);
                    theOrderItemLabreferin = null;
                }
                if(((String)jTableResult.getValueAt(i,1)).equals(""))
                {
                    theOrderItemLabreferin = new OrderItemLabreferin();
                    theOrderItemLabreferin = theLabReferControl.readOrderItemByOrderItemIdAndVisitId(((OrderResultLabreferin)vResult.get(i)).order_item_labreferin_id);
                    if(!theOrderItemLabreferin.status.equals("1"))
                    {
                        theOrderItemLabreferin.status = "1";
                        vOrderItemReverseUpdate.add(theOrderItemLabreferin);                        
                    }
                    theOrderItemLabreferin = null;
                }
                theOrderResultLabreferin = null;
            }  
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    /**
     *เซฟResultของแลป
     */
    private void saveResultOrderLab()
    {   
        if(vOrderResultLabreferin!=null)
        {
            for(int i=0; i<vOrderResultLabreferin.size(); i++)
            {
                theLabReferControl.saveOrderResultLabrefrin((OrderResultLabreferin)vOrderResultLabreferin.get(i));            
            }
        }
        if(vOrderItemUpdate!=null)
        {
            for(int j=0; j<vOrderItemUpdate.size(); j++)
            {
                theLabReferControl.updateOrderItemLabreferin((OrderItemLabreferin)vOrderItemUpdate.get(j));
            } 
        }
        if(vOrderItemReverseUpdate!=null)
        {
            for(int k=0; k<vOrderItemReverseUpdate.size(); k++)
            {
                theLabReferControl.updateOrderItemLabreferin((OrderItemLabreferin)vOrderItemReverseUpdate.get(k));
            } 
        }
        setStatus("บันทึกผลเสร็จสิ้น",UpdateStatus.COMPLETE);
    }
    
    //////////////////////////////////////////////////////////////////////////
    /**
     *เรียกใช้เมื่อทำการจำหน่าย
     */
    private void updateVisitLabreferin(){
        theVisitLabreferin.status = "2";
        theVisitLabreferin.end_visit_time = theHC.theLookupControl.getTextCurrentDateTime();
        theLabReferControl.updateVisitLabreferin(theVisitLabreferin);
        setStatus("การจำหน่ายผู้ป่วยเสร็จสิ้น",UpdateStatus.COMPLETE);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    private void checkOrderItemId()
    {
        int result=0;
        for(int i=0; i<vOrderItem.size(); i++)
        {
            if(((OrderItemLabreferin)vOrderItem.get(i)).getObjectId()==null){
                result = result + 1;
            }
        }
        if(result>0){
            //jTableOrder.setEnabled(false);
            setStatus("ต้องบันทึกรายการ order ก่อน",UpdateStatus.WARNING);            
        }
        else{
            //jTableOrder.setEnabled(true);
            if(theVisitLabreferin.status.equals("1")){
                selectTableOrder();
                setEnableOrder(true);        
                jTableResult.setEnabled(true);
            }
            else{
                setEnableOrder(false);        
                jTableResult.setEnabled(false);
            }
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    /**
     *เรียกใช้เมื่อต้องการแสดงDialog
     */
    public boolean showDialog(HosControl hc,UpdateStatus us)
    {
        //DialogLabReferIn dlg = new DialogLabReferIn(hc,us);        
        setSize(640, 480);
        setTitle(Constant.getTextBundle("การรับบริการของ Lab ReferIn"));   
//        setLanguage("");
        Toolkit thekit = this.getToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-this.getSize().width)/2, (screenSize.height-this.getSize().height)/2);
        setVisible(true);
        if(actionCommand)
        {     
              return true;
        }
        System.gc();
        return false;
    }
    
     public void setLanguage(String msg)
    {
        GuiLang.setLanguage(jLabel8);
        GuiLang.setLanguage(jLabel6);
        GuiLang.setLanguage(jLabelRoad);
        GuiLang.setLanguage(jLabelVillage);
        GuiLang.setLanguage(jLabelHouse);
        GuiLang.setLanguage(jLabel5);
        GuiLang.setLanguage(jLabel3);
        GuiLang.setLanguage(jLabelAge);
        GuiLang.setLanguage(jLabelGender);
        GuiLang.setLanguage(jLabelPid);
        GuiLang.setLanguage(jLabelLname);
        GuiLang.setLanguage(jLabelFname);
        GuiLang.setLanguage(jLabelPrename);
        GuiLang.setLanguage(jButtonSearch);
        GuiLang.setLanguage(jLabel9);
        GuiLang.setLanguage(jButtonVisit);
        GuiLang.setLanguage(jButtonSaveResult);
	GuiLang.setLanguage(jButtonSaveOrder);
	GuiLang.setLanguage(jButtonSavePatient);
	GuiLang.setLanguage(jButtonPrintResultLab);
	GuiLang.setLanguage(jButtonDischarge);
	GuiLang.setLanguage(jLabel1);
	GuiLang.setLanguage(jLabelTambon);
	GuiLang.setLanguage(jLabelAmpur);
        GuiLang.setLanguage(jLabelChangwat);
        GuiLang.setLanguage(jButtonSearch);
        GuiLang.setLanguage(column);
        GuiLang.setLanguage(col_jTableHistory);
        GuiLang.setLanguage(col_jTableOrder);
        GuiLang.setLanguage(col_jTableResult);
        GuiLang.setLanguage(jButtonSearch);
        GuiLang.setLanguage(jTabbedPane4);
        GuiLang.setTextBundle(jPanel20);
        GuiLang.setTextBundle(jPanel9);
        GuiLang.setTextBundle(jPanel14);
        GuiLang.setTextBundle(jPanel24);
        GuiLang.setTextBundle(jPanel2);
        GuiLang.setTextBundle(jPanel3);
        GuiLang.setTextBundle(jPanel4);
    }
    
    public boolean confirmBox(String str, int status) {
        int i = JOptionPane.showConfirmDialog(this,str,Constant.getTextBundle("เตือน")
                ,JOptionPane.YES_NO_OPTION);
        return (i==JOptionPane.YES_OPTION);
    }
    
    public JFrame getJFrame() {
        return this;
    }
       
    public void notifyAddLabReferIn(String str, int status) {
        Vector vItem = theHO.vLabReferIn;
        for(int i = 0;i < vItem.size();i++){
            Item item = (Item) vItem.get(i);
            updateOGOrderItem(item);
        }
    }
    
    public void notifyAddLabReferOut(String str, int status) {
    }
    
    public void notifyDeleteFilmXray(String str, int status) {
    }
    
    public void notifyDeleteLabOrder(String str, int status) {
    }
    
    public void notifyDeleteLabResult(String str, int status) {
    }
    
    public void notifyDeleteResultXray(String str, int status) {
    }
    
    public void notifyDeleteXrayPosition(String str, int status) {
    }
    
    public void notifyManagePatientLabReferIn(String str, int status) {
    }
    
    public void notifyReportResultLab(String str, int status) {
    }
    
    public void notifySaveFilmXray(String str, int status) {
    }
    
    public void notifySaveLabResult(String str, int status) {
    }
    
    public void notifySaveResultXray(String str, int status) {
    }
    
    public void notifySaveXrayPosition(String str, int status) {
    }
    
    public void notifyXrayReportComplete(String str, int status) {
    }
    
    public void notifySaveRemainLabResult(String str, int status) {
    }
    
    public void notifySendResultLab(String str, int status) {
    }
    
    public void notifyDeleteQueueLab(String str, int status) {
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddOrder;
    private javax.swing.JButton jButtonAddPatient;
    private javax.swing.JButton jButtonDelOrder;
    private javax.swing.JButton jButtonDelPatient;
    private javax.swing.JButton jButtonDischarge;
    private javax.swing.JButton jButtonHos;
    private javax.swing.JButton jButtonPrintResultLab;
    private javax.swing.JButton jButtonSaveOrder;
    private javax.swing.JButton jButtonSavePatient;
    private javax.swing.JButton jButtonSaveResult;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonVisit;
    private javax.swing.JComboBox jComboBoxAmpur;
    private javax.swing.JComboBox jComboBoxChangwat;
    private javax.swing.JComboBox jComboBoxGender;
    private com.hosv3.gui.component.HosComboBox jComboBoxPrename;
    private javax.swing.JComboBox jComboBoxTambon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAge;
    private javax.swing.JLabel jLabelAmpur;
    private javax.swing.JLabel jLabelChangwat;
    private javax.swing.JLabel jLabelFname;
    private javax.swing.JLabel jLabelGender;
    private javax.swing.JLabel jLabelHouse;
    private javax.swing.JLabel jLabelLname;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPid;
    private javax.swing.JLabel jLabelPrename;
    private javax.swing.JLabel jLabelRoad;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelTambon;
    private javax.swing.JLabel jLabelVillage;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane4;
    private com.hosv3.gui.component.HJTableSort jTableHistory;
    private com.hosv3.gui.component.HJTableSort jTableOrder;
    private com.hosv3.gui.component.HJTableSort jTablePatientLabReferIn;
    private com.hosv3.gui.component.HJTableSort jTableResult;
    private com.hosv3.gui.component.HJTableSort jTableVisitLabReferIn;
    private com.hospital_os.utility.IntegerTextField jTextFieldAge;
    private com.hospital_os.utility.IntegerTextField jTextFieldAmpur;
    private com.hospital_os.utility.IntegerTextField jTextFieldChangwat;
    private javax.swing.JTextField jTextFieldFname;
    private javax.swing.JTextField jTextFieldHosReferIn;
    private javax.swing.JTextField jTextFieldHouse;
    private javax.swing.JTextField jTextFieldLname;
    private javax.swing.JTextField jTextFieldPhone;
    private javax.swing.JTextField jTextFieldRoad;
    private javax.swing.JTextField jTextFieldSearchFname;
    private javax.swing.JTextField jTextFieldSearchLname;
    private com.hospital_os.utility.IntegerTextField jTextFieldTambon;
    private javax.swing.JTextField jTextFieldVillage;
    private com.hospital_os.utility.PIDPanel pidPanel;
    // End of variables declaration//GEN-END:variables
}
