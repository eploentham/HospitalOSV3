/*
 * PanelSetupOffice.java
 *
 * Created on 4 ตุลาคม 2546, 11:07 น.
 */
package com.hosv3.gui.panel.setup;
import java.util.Vector;

import com.hosv3.usecase.setup.*;
import com.hosv3.control.*;
import com.hosv3.object.*;
import com.hosv3.utility.connection.*;
import com.hosv3.utility.GuiLang;
import com.hosv3.gui.component.*;
import com.hospital_os.utility.*;
import com.hospital_os.object.*;
import com.hosv3.gui.dialog.HosDialog;
/**
 *
 * @author amp
 */
public class PanelSetupOfficeInCup extends javax.swing.JPanel implements ManageOptionReq,PanelSetup
{
    final private int offset = 24;
    private int next =0;
    private int prev = 0;
    private int save = 0;
    private Office theOffice;
    String[] col = {"รหัส","ชื่อ"};
    
    /**
     เรียกใช้ control ที่ชื่อว่า lookupControl เพื่อทำการ set ค่า ให้กับ combobox ต่างๆ
     จะประกอบด้วย จังหวัด อำเภอ และ ตำบล 
     และเรียกใช้ SetupControl เพื่อทำการ ดิงข้อมูลของ office ขึ้นมาแสดง
     
     โดยทุกตัวจะเก็บข้อมูลไว้ใน vector ทั้งหมด
     
     */
    LookupControl theLookupControl;
    UpdateStatus theUS;
    SetupControl theSetupControl;
    ComboboxModel theComboboxModel;
    HosDialog theHD;
    Vector comboboxoffice = new Vector();
    Vector comboboxaddresscgw = new Vector();
    Vector comboboxaddressamp = new Vector();
    Vector comboboxaddresstmb = new Vector();
    private Vector theOfficeInCupV = new Vector();
    
   // public Office  theOffice;
    public PanelSetupOfficeInCup(HosControl hc, UpdateStatus us) {
        this();
        setControl(hc,us);
        jTable1.setGuiMode(true);
    }
    public PanelSetupOfficeInCup() {
        initComponents();
        setLanguage();
    }
    public void setTitleLabel(String str){
        jLabel3.setText(str);
    }
    /**
        ทำการset ค่าให้กับ conbobox ต่างๆ ประกอบด้วย จังหวัด อำเภอ และ ตำบล
     */
    public void setupLookup()
    {
        selectcomboCgw();
        //henbe_just theOfficeInCupV = theLookupControl.listOffice();
        next = next + offset;
        updateOGOfficeInCupV(theOfficeInCupV);
    }
       /**
     *@Author : amp
     *@date : 29/02/2549
     *@see : จัดการเกี่ยวกับภาษา    
     */
    private void setLanguage()
    {
        GuiLang.setLanguage(jLabel3);
        GuiLang.setLanguage(jLabelICD9code);
        GuiLang.setLanguage(jButtonSearch);
        GuiLang.setLanguage(jLabel1);        
        GuiLang.setLanguage(jLabel2);
        GuiLang.setLanguage(jLabel3);
        GuiLang.setLanguage(jLabel4);
        GuiLang.setLanguage(jButtonSave);        
        GuiLang.setLanguage(jLabel8);        
        GuiLang.setLanguage(jLabel10);
        GuiLang.setLanguage(jLabel9);
        GuiLang.setLanguage(jLabel5);        
        GuiLang.setLanguage(jLabel6);
        GuiLang.setLanguage(jLabel7);   
        GuiLang.setLanguage(col);
    }
/////////////////////Use this for decrease memory usage
    public void setControl(HosControl hc,UpdateStatus us){
        theUS = us;
        setEnableAll(false);
        theLookupControl = hc.theLookupControl;
        theSetupControl = hc.theSetupControl;
        
        hc.theHS.theSetupSubject.addpanelrefrash(this);
        hc.theHS.theSetupSubject.addForLiftAttach(this);
        setLookup();
        jTextFieldCode.setEditable(false);
//        jTextFieldCode.setEnabled(false);
     }
    
    public void setHosDialog(HosDialog hd)
    {
        if(hd == null)
        {
            Constant.println("PanelSetupOfficeInCup+++++++++++++++++++++++++++++++++theHD = null");
        }
        theHD = hd;
    }
    private void setLookup()
    {   
        comboboxaddresscgw = theLookupControl.listAddressCGW();
        ComboboxModel.initComboBox(jComboBoxChangwat,comboboxaddresscgw);
        comboboxaddressamp = theLookupControl.listAddressAmp(ComboboxModel.getCodeComboBox(jComboBoxChangwat));
        ComboboxModel.initComboBox(jComboBoxAmphur,comboboxaddressamp);
        comboboxaddresstmb = theLookupControl.listAddressTmp(ComboboxModel.getCodeComboBox(jComboBoxChangwat),ComboboxModel.getCodeComboBox(jComboBoxAmphur));
        if(comboboxaddresstmb != null)
            ComboboxModel.initComboBox(jComboBoxTambon,comboboxaddresstmb);
    }
    /////////////////////Use this for decrease memory usage    
    /**
        นำค่าที่ได้จากการ query ของ ข้อมูลจังหวัด มาเก็บไว้ใน vector จากนั้นทำการ setค่าให้กับ combobox
     โดยเรียก class ที่ชื่อว่า ComboboxModel 
        จากนั้นก็ทำการ query ข้อมูลอำเภอ และตำบล
     
     */
    private void selectcomboCgw()
    {   comboboxaddresscgw = theLookupControl.listAddressCGW();
        ComboboxModel.initComboBox(jComboBoxChangwat,comboboxaddresscgw);
        comboboxaddressamp = theLookupControl.listAddressAmp(ComboboxModel.getCodeComboBox(jComboBoxChangwat));
        ComboboxModel.initComboBox(jComboBoxAmphur,comboboxaddressamp);
        comboboxaddresstmb = theLookupControl.listAddressTmp(ComboboxModel.getCodeComboBox(jComboBoxChangwat),ComboboxModel.getCodeComboBox(jComboBoxAmphur));
        if(comboboxaddresstmb != null)
            ComboboxModel.initComboBox(jComboBoxTambon,comboboxaddresstmb);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        headFont1 = new com.hospital_os.gui.font.HeadFont();
        tableResultsModel1 = new com.hospital_os.utility.TableResultsModel();
        fontFormatTitle1 = new com.hospital_os.gui.font.FontFormatTitle();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldCode = new javax.swing.JTextField();
        jTextFieldOffName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxChangwat = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaFullName = new javax.swing.JTextArea();
        jComboBoxAmphur = new javax.swing.JComboBox();
        jComboBoxTambon = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldDepartment = new javax.swing.JTextField();
        jTextFieldMoo = new javax.swing.JTextField();
        jTextFieldBed = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabelICD9code = new javax.swing.JLabel();
        jTextFieldSCode = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new com.hosv3.gui.component.HJTableSort();
        jPanel5 = new javax.swing.JPanel();
        jButtonPrev = new javax.swing.JButton();
        jButtonNext = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonDel = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMaximumSize(new java.awt.Dimension(350, 330));
        jPanel2.setMinimumSize(new java.awt.Dimension(350, 330));
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 330));
        jLabel1.setFont(defaultFont1);
        jLabel1.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("CODE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(defaultFont1);
        jLabel2.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("OFFICE_OFFNAME"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        jTextFieldCode.setFont(defaultFont1);
        jTextFieldCode.setEnabled(false);
        jTextFieldCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodeActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel2.add(jTextFieldCode, gridBagConstraints);

        jTextFieldOffName.setFont(defaultFont1);
        jTextFieldOffName.setDoubleBuffered(true);
        jTextFieldOffName.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel2.add(jTextFieldOffName, gridBagConstraints);

        jLabel3.setFont(defaultFont1);
        jLabel3.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("OFFICE_FULLNAME"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel3, gridBagConstraints);

        jComboBoxChangwat.setFont(defaultFont1);
        jComboBoxChangwat.setEnabled(false);
        jComboBoxChangwat.setMaximumSize(new java.awt.Dimension(150, 24));
        jComboBoxChangwat.setMinimumSize(new java.awt.Dimension(150, 24));
        jComboBoxChangwat.setPreferredSize(new java.awt.Dimension(150, 24));
        jComboBoxChangwat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxChangwatActionPerformed(evt);
            }
        });
        jComboBoxChangwat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBoxChangwatFocusLost(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel2.add(jComboBoxChangwat, gridBagConstraints);

        jLabel5.setFont(defaultFont1);
        jLabel5.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("ADDRESS_CHANGWAT_COL"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(defaultFont1);
        jLabel6.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("ADDRESS_AMPHUR_COL"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel6, gridBagConstraints);

        jScrollPane2.setMaximumSize(new java.awt.Dimension(150, 48));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(150, 48));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(150, 48));
        jTextAreaFullName.setFont(defaultFont1);
        jTextAreaFullName.setLineWrap(true);
        jTextAreaFullName.setWrapStyleWord(true);
        jTextAreaFullName.setDoubleBuffered(true);
        jTextAreaFullName.setEnabled(false);
        jScrollPane2.setViewportView(jTextAreaFullName);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel2.add(jScrollPane2, gridBagConstraints);

        jComboBoxAmphur.setFont(defaultFont1);
        jComboBoxAmphur.setEnabled(false);
        jComboBoxAmphur.setMaximumSize(new java.awt.Dimension(150, 24));
        jComboBoxAmphur.setMinimumSize(new java.awt.Dimension(150, 24));
        jComboBoxAmphur.setPreferredSize(new java.awt.Dimension(150, 24));
        jComboBoxAmphur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAmphurActionPerformed(evt);
            }
        });
        jComboBoxAmphur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBoxAmphurFocusLost(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel2.add(jComboBoxAmphur, gridBagConstraints);

        jComboBoxTambon.setFont(defaultFont1);
        jComboBoxTambon.setEnabled(false);
        jComboBoxTambon.setMaximumSize(new java.awt.Dimension(150, 24));
        jComboBoxTambon.setMinimumSize(new java.awt.Dimension(150, 24));
        jComboBoxTambon.setPreferredSize(new java.awt.Dimension(150, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel2.add(jComboBoxTambon, gridBagConstraints);

        jLabel7.setFont(defaultFont1);
        jLabel7.setText("\u0e15\u0e33\u0e1a\u0e25");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel7, gridBagConstraints);

        jLabel8.setFont(defaultFont1);
        jLabel8.setText("\u0e2a\u0e31\u0e07\u0e01\u0e31\u0e14");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(defaultFont1);
        jLabel9.setText("\u0e2b\u0e21\u0e39\u0e48\u0e17\u0e35\u0e48");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel9, gridBagConstraints);

        jLabel10.setFont(defaultFont1);
        jLabel10.setText("\u0e40\u0e15\u0e35\u0e22\u0e07");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel10, gridBagConstraints);

        jTextFieldDepartment.setFont(defaultFont1);
        jTextFieldDepartment.setDoubleBuffered(true);
        jTextFieldDepartment.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel2.add(jTextFieldDepartment, gridBagConstraints);

        jTextFieldMoo.setFont(defaultFont1);
        jTextFieldMoo.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel2.add(jTextFieldMoo, gridBagConstraints);

        jTextFieldBed.setFont(defaultFont1);
        jTextFieldBed.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel2.add(jTextFieldBed, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setMinimumSize(new java.awt.Dimension(300, 96));
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 154));
        jLabelICD9code.setFont(defaultFont1);
        jLabelICD9code.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("SEARCH"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 11);
        jPanel3.add(jLabelICD9code, gridBagConstraints);

        jTextFieldSCode.setFont(defaultFont1);
        jTextFieldSCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSCodeActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel3.add(jTextFieldSCode, gridBagConstraints);

        jButtonSearch.setFont(defaultFont1);
        jButtonSearch.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("SEARCH"));
        jButtonSearch.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSearch.setMaximumSize(new java.awt.Dimension(67, 25));
        jButtonSearch.setMinimumSize(new java.awt.Dimension(67, 25));
        jButtonSearch.setPreferredSize(new java.awt.Dimension(67, 25));
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel3.add(jButtonSearch, gridBagConstraints);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(100, 22));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 80));
        jTable1.setModel(tableResultsModel1);
        jTable1.setFont(defaultFont1);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });

        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(jScrollPane1, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jButtonPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Back16.gif")));
        jButtonPrev.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonPrev.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonPrev.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrevActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel5.add(jButtonPrev, gridBagConstraints);

        jButtonNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Forward16.gif")));
        jButtonNext.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonNext.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonNext.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel5.add(jButtonNext, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel3.add(jPanel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(fontFormatTitle1);
        jLabel4.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("PANEL_SETUP_OFFICE_INCUP"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jPanel4.add(jLabel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanel4, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButtonAdd.setFont(defaultFont1);
        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif")));
        jButtonAdd.setMargin(new java.awt.Insets(0, 0, 0, 0));
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
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 2);
        jPanel1.add(jButtonAdd, gridBagConstraints);

        jButtonDel.setFont(defaultFont1);
        jButtonDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif")));
        jButtonDel.setEnabled(false);
        jButtonDel.setMargin(new java.awt.Insets(0, 0, 0, 0));
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
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel1.add(jButtonDel, gridBagConstraints);

        jButtonSave.setFont(defaultFont1);
        jButtonSave.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("SAVE"));
        jButtonSave.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSave.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonSave.setPreferredSize(new java.awt.Dimension(60, 24));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel1.add(jButtonSave, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        add(jPanel1, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodeActionPerformed
//        theLookupControl.readHospitalByCode(this.jTextFieldCode.getText());
    }//GEN-LAST:event_jTextFieldCodeActionPerformed
    private void jComboBoxAmphurFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxAmphurFocusLost
        // Add your handling code here:
        //selectAmpor();
    }//GEN-LAST:event_jComboBoxAmphurFocusLost
    private void jComboBoxChangwatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxChangwatFocusLost
        // Add your handling code here:
    }//GEN-LAST:event_jComboBoxChangwatFocusLost
    private void jComboBoxAmphurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAmphurActionPerformed
        // Add your handling code here:
     selectAmpor();
    }//GEN-LAST:event_jComboBoxAmphurActionPerformed
    private void jComboBoxChangwatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxChangwatActionPerformed
        // Add your handling code here:
        selectChangwat();       
    }//GEN-LAST:event_jComboBoxChangwatActionPerformed
    private void jButtonDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelActionPerformed
        deleteOffice();
    }//GEN-LAST:event_jButtonDelActionPerformed
    private void jTextFieldSCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSCodeActionPerformed
        searchOffice();       
    }//GEN-LAST:event_jTextFieldSCodeActionPerformed
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
       saveOffice();
    }//GEN-LAST:event_jButtonSaveActionPerformed
    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        this.jTable1.clearSelection();
        Office office = new Office();
        office.setObjectId("");
        if(theHD.showDialogOffice(office))
        {
            office = theLookupControl.readHospitalByCode(office.getObjectId());
            this.updateOG(office);
        }
    }//GEN-LAST:event_jButtonAddActionPerformed
    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        //next = next + offset;
        updateOGOfficeInCupV(theOfficeInCupV);
    }//GEN-LAST:event_jButtonNextActionPerformed
    private void jButtonPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrevActionPerformed
        //next = next - offset;
        updateOGOfficeInCupV(theOfficeInCupV);
    }//GEN-LAST:event_jButtonPrevActionPerformed
    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        searchOffice();
    }//GEN-LAST:event_jButtonSearchActionPerformed
    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        selectOffice();
        jButtonDel.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseReleased
    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if(evt.getKeyCode()==evt.VK_UP || evt.getKeyCode()==evt.VK_DOWN){
            selectOffice();
            jButtonDel.setEnabled(true);
        }
	}//GEN-LAST:event_jTable1KeyReleased
    private void setEnableAll(boolean var)
    {
//        jTextFieldCode.setEnabled(var);
//        jTextAreaFullName.setEnabled(var);
//        jTextFieldBed.setEnabled(var);
//        jTextFieldDepartment.setEnabled(var);
//        jTextFieldMoo.setEnabled(var);
//        jTextFieldOffName.setEnabled(var);
//        jComboBoxAmphur.setEnabled(var);
//        jComboBoxChangwat.setEnabled(var);
//        jComboBoxTambon.setEnabled(var);
        jButtonDel.setEnabled(var);
        jButtonSave.setEnabled(var);
    }
    private void showNextData()
    {   String search = jTextFieldSCode.getText();
        theOfficeInCupV = theSetupControl.listOfficeByName(search);
        updateOGOfficeInCupV(theOfficeInCupV);
        
        search = null;
    }
    private void searchOffice()
    {   next = 0;
        prev = 0;
        String search = jTextFieldSCode.getText();      
        theOfficeInCupV =  theLookupControl.listOfficeInCup(search);
        updateOGOfficeInCupV(theOfficeInCupV);
    }
    private void clearAll()
    {
        jTextFieldCode.setText("");
        jTextFieldOffName.setText("");
        jTextAreaFullName.setText("");
        jTextFieldDepartment.setText("");
        jTextFieldMoo.setText("");
        jTextFieldMoo.setText("");
    }
    /**
     เมื่อมีการเลือก จังหวัด จะทำให้ combobox ของ อำเภอเปลี่ยนไปด้วย
     โดยการลบข้อมูลเก่าที่อยู่ใน combobox ของอำเภอ ออก แล้วใส่ข้อมูลใหม่เข้าไป
     */
    private void selectChangwat()
    {
        jComboBoxAmphur.removeAllItems();
        comboboxaddressamp.clear();
        comboboxaddressamp = theLookupControl.listAddressAmp(ComboboxModel.getCodeComboBox(jComboBoxChangwat));
        if(comboboxaddressamp != null)
            ComboboxModel.initComboBox(jComboBoxAmphur,comboboxaddressamp);
    }
    private void selectAmpor()
    {  
        jComboBoxTambon.removeAllItems();
        if(jComboBoxChangwat !=null)
        {   comboboxaddresstmb = theLookupControl.listAddressTmp(null,ComboboxModel.getCodeComboBox(jComboBoxAmphur));
            if(comboboxaddresstmb != null)
                ComboboxModel.initComboBox(jComboBoxTambon,comboboxaddresstmb);
        }
    }
    private void selectOffice()
    {
        int row = jTable1.getSelectedRow();
        String of = (String)jTable1.getValueAt(row, 0);
        Office  office = theLookupControl.readHospitalByCode(of);
        updateOG(office);
    }
    public void updateOG(Office office)
    {
        if(office==null)
            office = new Office();
        theOffice = office;
        jTextFieldCode.setText(office.getObjectId());
//        jTextFieldCode.setEditable(false);
        jTextFieldOffName.setText(office.name);
        jTextAreaFullName.setText(office.off_name1);
        jTextFieldDepartment.setText(office.off_name2);
        jTextFieldBed.setText(office.bed);
        jTextFieldMoo.setText(office.moo);
        ComboboxModel.setCodeComboBox(jComboBoxChangwat, office.changwat+"0000");
        if(office.changwat != null)
        if(!ComboboxModel.setCodeComboBox(jComboBoxAmphur, office.changwat+office.ampur+"00"))
        {
            jComboBoxAmphur.removeAllItems();
            comboboxaddressamp.clear();
        }
        selectAmpor();
        //ComboboxModel.setCodeComboBox(jComboBoxTambon, office.changwat+office.ampur+office.tambon);
        if(office.tambon !=null)
        if(!ComboboxModel.setCodeComboBox(jComboBoxTambon, office.changwat+office.ampur+office.tambon))
        {   
            jComboBoxTambon.removeAllItems();
            comboboxaddresstmb.clear();
        } 
        setEnableAll(true);
    }
    /**
        ทำการแสดงค่าที่ได้จากการค้นหาโดยส่ง vector ที่ได้จากการค้นหา office มาจากนั้นจะใช้ class ที่ชื่อว่า TaBelModel
     จัดการกับข้อมูลที่เป็น Vector จะทำให้เราสามารถที่จะแสดงข้อมูลใหนก็ได้ โดยใน vector แต่ละตัวจะเก็บข้อมูลป็น Object 
     
     
     */
    private void updateOGOfficeInCupV(Vector voffice)
    {  
        if(!voffice.isEmpty()){
            TaBleModel tm = new TaBleModel(col,voffice.size());
            for(int i=0 ;i<voffice.size();i++)
            {  
               OfficeInCup of = (OfficeInCup)voffice.get(prev + i);
               tm.setValueAt(of.code,i,0);
               tm.setValueAt(of.name,i,1);
           }
            jTable1.setModel(tm);
         }
         else{
             TaBleModel tm = new TaBleModel(col,0);
             jTable1.setModel(tm);
         }
         jTable1.getColumnModel().getColumn(0).setPreferredWidth(60); // รหัส
         jTable1.getColumnModel().getColumn(1).setPreferredWidth(150); // ชื่อย่อ
    }

    private void updateGO()
    {
//       this.theOffice = o;
       if(theOffice==null)
       {
            theOffice = new Office();
            theOffice.minis = "";
            theOffice.dep = "";
            theOffice.off_type = "";
            theOffice.specific = "";
       }
        theOffice.pk_field = jTextFieldCode.getText();
        theOffice.name = jTextFieldOffName.getText();
        theOffice.off_name1 = jTextAreaFullName.getText();
        theOffice.off_name2 = jTextFieldDepartment.getText();
        theOffice.bed = jTextFieldBed.getText();
        theOffice.moo = jTextFieldMoo.getText();
        if((Gutil.getGuiData(jComboBoxChangwat)==null)||(Gutil.getGuiData(jComboBoxChangwat).equals("")))
        {  
        theOffice.changwat="00";
        }
        else
        {
         theOffice.changwat = Gutil.getGuiData(jComboBoxChangwat).substring(0,2);
        }
        if((Gutil.getGuiData(jComboBoxAmphur)== null)||(Gutil.getGuiData(jComboBoxAmphur).equals("")))
        {
          theOffice.ampur ="00";
        }
        else
        {
            theOffice.ampur = Gutil.getGuiData(jComboBoxAmphur).substring(2,4);
        }
        if((Gutil.getGuiData(jComboBoxTambon)==null)||(Gutil.getGuiData(jComboBoxTambon).equals("")))
        {   
         theOffice.tambon = "00";
        }
        else
        {
            theOffice.tambon = Gutil.getGuiData(jComboBoxTambon).substring(4,6);
        }
    }
    private void saveOffice()
    {
        updateGO();
        if(theOffice == null || theOffice.getObjectId()==null
            || theOffice.name.equals("")){
                theUS.setStatus("ไม่สามารถบันทึกข้อมูลเป็นค่าว่างได้",UpdateStatus.WARNING);
                return;
        }
        OfficeInCup oc = theLookupControl.readOfficeInCupByCode(theOffice.getObjectId());
        if(oc!=null){
            theUS.setStatus("ไม่สามารถบันทึกรหัสซ้ำได้",UpdateStatus.WARNING);
            return;
        }
        theSetupControl.saveOfficeInCup(theOffice);
        searchOffice();
    }
    public void deleteOffice()
    {
        int row = this.jTable1.getSelectedRow();
        OfficeInCup oic = (OfficeInCup)theOfficeInCupV.get(row);
        int ret = theSetupControl.deleteOfficeInCup(oic);
        if(ret==0)
                return;
        this.searchOffice();
//        jButtonDel.setEnabled(false);
        clearAll();
        setEnableAll(false);
    }

    public void notifyreFrashPanel() {
        setLookup();
    }
    public void notifysetEnableForLift(boolean b) {
        jButtonDel.setVisible(b);
    }
    public int editOption(Option option) {
        Constant.println("PanelSetupOffice function is not use.");
        return -1;
    }
    public Vector listOptionAll() {
        Constant.println("PanelSetupOffice function is not use.");
        return null;
    }
    public void reFrashPanel() {
    }
    public Option readOption() {
        Constant.println("PanelSetupOffice function is not use.");
        return null;
    }
    public int saveOption(Option option) {
        Constant.println("PanelSetupOffice function is not use.");
        return -1;
    }
    public void setEnableForLift(boolean b) {
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.hospital_os.gui.font.FontFormatTitle fontFormatTitle1;
    private com.hospital_os.gui.font.HeadFont headFont1;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDel;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrev;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JComboBox jComboBoxAmphur;
    private javax.swing.JComboBox jComboBoxChangwat;
    private javax.swing.JComboBox jComboBoxTambon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelICD9code;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.hosv3.gui.component.HJTableSort jTable1;
    private javax.swing.JTextArea jTextAreaFullName;
    private javax.swing.JTextField jTextFieldBed;
    private javax.swing.JTextField jTextFieldCode;
    private javax.swing.JTextField jTextFieldDepartment;
    private javax.swing.JTextField jTextFieldMoo;
    private javax.swing.JTextField jTextFieldOffName;
    private javax.swing.JTextField jTextFieldSCode;
    private com.hospital_os.utility.TableResultsModel tableResultsModel1;
    // End of variables declaration//GEN-END:variables
}
