/*
 * DialogAppointmentTemplate.java
 *
 * Created on 10 สิงหาคม 2549, 09:57 น.
 * 
 */
package com.hosv3.gui.dialog;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.hosv3.control.*;
import com.hosv3.utility.*;
import com.hosv3.object.*;
import com.hosv3.utility.connection.*;

import com.hospital_os.object.*;
import com.hospital_os.object.specialQuery.*; 
import com.hospital_os.utility.Gutil;
import com.hosv3.utility.Constant;
import com.hospital_os.utility.CellRendererHos;
import com.hospital_os.utility.TaBleModel;
import com.hospital_os.utility.ComboboxModel;

/**
 *
 * @author amp
 *
 */
public class DialogAppointmentTemplate extends JFrame implements UpdateStatus
{
    HosControl theHC;
    HosObject theHO;
    public boolean actionCommand = false;
    JFrame aMain;     
    private String[] col_head = {"ชื่อ","วันที่นัด"};      
    private QueueVisit theQueueVisit;
    private boolean IOption;     
    CellRendererHos appointmentRender = new CellRendererHos(CellRendererHos.APPOINTMENT_STATUS);
    long firstClickTime = 0;
    private Vector sPoint;
    private Vector vAppointmentTemplateItem;
    private Vector vItem;
    private Vector vAppointmentTemplate;
    private AppointmentTemplate theAppointmentTemplate;
    CellRendererHos dateRender = new CellRendererHos(CellRendererHos.DATE_TIME);
    
    /** Creates new form DialogAppointmentTemplate */
    public DialogAppointmentTemplate(HosControl hc,UpdateStatus us)
    {   
        aMain = us.getJFrame();
        setIconImage(aMain.getIconImage());        
        theHO = hc.theHO;
        theHC = hc;       
        initComponents();
        setLanguage();
        updateOGComponent();
        setDialog();
         
        
        this.jCheckBoxToDate.setSelected(false);
        this.jCheckBoxToDateActionPerformed(null);
        
        //amp:13/03/2549 เพื่อให้รายละเอียดมีตัวช่วยของอาการเบื้องต้น        
        jTextAreaDescription.setControl(new com.hosv3.control.lookup.VitalTemplateLookup(theHC.theLookupControl));
        jTextAreaDescription.setJFrame(this);
        theHC.theHS.theBalloonSubject.attachBalloon(jTextAreaDescription);
    }
       
    /**
     *ใช้ในการเซตภาษา
     */
    public void setLanguage()
    {         
        GuiLang.setTextBundle(jPanel3);
        GuiLang.setTextBundle(jPanel7);
        GuiLang.setLanguage(jLabel5);
        GuiLang.setLanguage(jButtonSave);
        GuiLang.setLanguage(jLabel4);
        GuiLang.setLanguage(jLabel6);
        GuiLang.setLanguage(jLabel5);
        GuiLang.setLanguage(jLabel15);
        GuiLang.setLanguage(jLabelQueue);
        GuiLang.setLanguage(jLabel10);
        GuiLang.setLanguage(jLabel9);
        GuiLang.setLanguage(jLabel11);
        GuiLang.setLanguage(jCheckBoxToDate);
        GuiLang.setLanguage(col_head);
        GuiLang.setLanguage(jPanel10); 
        GuiLang.setLanguage(jLabel1);
        GuiLang.setLanguage(jPanel20); 
        GuiLang.setLanguage(jPanel21); 
        GuiLang.setLanguage(jLabel17);
        GuiLang.setLanguage(jButtonQueueVisit);
    }
    
     /**
     *dialog ที่ใช้ในการส่งข้อความเตื่อนผู้ใช้
     */
     public void setStatus(String str, int status) 
     {   
         
        ThreadStatus theTT = new ThreadStatus(this,this.jLabelStatus);
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
     
     /**
      *dialog ที่ใข้ในการให้ผู้ใข้ทำการยีนยันสิ่งต่าง
      */
    public boolean confirmBox(String str, int status) 
    {
        int i = JOptionPane.showConfirmDialog(this,str,Constant.getTextBundle("เตือน")
                ,JOptionPane.YES_NO_OPTION);
        return (i==JOptionPane.YES_OPTION);
    }
     
     /**
     *init component
     *ทำการเซตค่าให้กับ component ต่างๆ
     *neung
     */
    private void updateOGComponent()
    {   
        Vector v = theHC.theLookupControl.listServicePoint(); 
        ServicePoint cf = new ServicePoint();
        cf.setObjectId("0");
        cf.name = Constant.getTextBundle("ทั้งหมด");
        sPoint = new Vector();
        sPoint.add(cf);
        for(int i=0,size=v.size();i<size;i++)
        {
            sPoint.add(v.get(i));
        }
        ComboboxModel.initComboBox(jComboBoxServicePoint, theHC.theLookupControl.listServicePoint());
        ComboboxModel.initComboBox(jComboBoxDoctor, theHC.theLookupControl.listDoctor());        
        
        dateComboBoxDateAppointment.setEditable(true);       
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabelStatus = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new com.hosv3.gui.component.HJTableSort();
        jPanelSearch = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonDel = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaDescription = new com.hosv3.gui.component.BalloonTextArea();
        jComboBoxDoctor = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jButtonQueueVisit = new javax.swing.JButton();
        jLabelQueue = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jButton6Week = new javax.swing.JButton();
        jButton8Week = new javax.swing.JButton();
        jButtonTo1Week = new javax.swing.JButton();
        jButtonTo2Week = new javax.swing.JButton();
        jButtonTo3Week = new javax.swing.JButton();
        jButton12Week = new javax.swing.JButton();
        dateComboBoxDateAppointment = new com.hospital_os.utility.DateComboBox();
        dateComboBoxDateAppointment2 = new com.hospital_os.utility.DateComboBox();
        jPanel21 = new javax.swing.JPanel();
        timeTextFieldTimeAppointment = new com.hospital_os.utility.TimeTextField();
        jButtonTime1 = new javax.swing.JButton();
        jButtonTime2 = new javax.swing.JButton();
        jButtonTime3 = new javax.swing.JButton();
        jButtonTime4 = new javax.swing.JButton();
        jButtonTime5 = new javax.swing.JButton();
        jButtonTime6 = new javax.swing.JButton();
        jButtonTime7 = new javax.swing.JButton();
        jButtonTime8 = new javax.swing.JButton();
        jButtonTime9 = new javax.swing.JButton();
        jButtonTime10 = new javax.swing.JButton();
        jTextFieldAppointmentTemplateName = new javax.swing.JTextField();
        jButton4Week = new javax.swing.JButton();
        jButton1Week = new javax.swing.JButton();
        jButton2Week = new javax.swing.JButton();
        jButton3Week = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableItem = new com.hosv3.gui.component.HJTableSort();
        jPanel23 = new javax.swing.JPanel();
        jButtonDelOrder = new javax.swing.JButton();
        jButtonAddOrder = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableAppointmentOrder = new com.hosv3.gui.component.HJTableSort();
        jPanel16 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldSearchOrder = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jTextFieldApType = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jCheckBoxToDate = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxServicePoint = new javax.swing.JComboBox();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabelStatus.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabelStatus.setText("Status");
        jLabelStatus.setMaximumSize(new java.awt.Dimension(4, 24));
        jLabelStatus.setMinimumSize(new java.awt.Dimension(4, 20));
        jLabelStatus.setOpaque(true);
        jLabelStatus.setPreferredSize(new java.awt.Dimension(4, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(jLabelStatus, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("\u0e04\u0e49\u0e19\u0e2b\u0e32\u0e15\u0e31\u0e27\u0e0a\u0e48\u0e27\u0e22\u0e19\u0e31\u0e14"));
        jPanel3.setMinimumSize(new java.awt.Dimension(290, 107));
        jPanel3.setPreferredSize(new java.awt.Dimension(250, 165));
        jPanel3.setRequestFocusEnabled(false);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(100, 22));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 80));
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
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 12, 12);
        jPanel3.add(jScrollPane1, gridBagConstraints);

        jPanelSearch.setLayout(new java.awt.GridBagLayout());

        jLabel15.setText("\u0e04\u0e49\u0e19\u0e2b\u0e32");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jPanelSearch.add(jLabel15, gridBagConstraints);

        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanelSearch.add(jTextFieldSearch, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 0, 12);
        jPanel3.add(jPanelSearch, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 0, 0);
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("\u0e23\u0e32\u0e22\u0e25\u0e30\u0e40\u0e2d\u0e35\u0e22\u0e14\u0e15\u0e31\u0e27\u0e0a\u0e48\u0e27\u0e22\u0e19\u0e31\u0e14"));
        jPanel10.setMinimumSize(new java.awt.Dimension(400, 946));
        jPanel10.setPreferredSize(new java.awt.Dimension(400, 165));
        jPanel10.setRequestFocusEnabled(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif")));
        jButtonAdd.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonAdd.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonAdd.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonAdd.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jButtonAdd, gridBagConstraints);

        jButtonDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif")));
        jButtonDel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonDel.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonDel.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonDel.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jButtonDel, gridBagConstraints);

        jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Save16.gif")));
        jButtonSave.setText("\u0e1a\u0e31\u0e19\u0e17\u0e36\u0e01");
        jButtonSave.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jButtonSave, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 11, 12);
        jPanel10.add(jPanel1, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel6.setText("Detail");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel7.add(jLabel6, gridBagConstraints);

        jLabel11.setText("DoctorName");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel7.add(jLabel11, gridBagConstraints);

        jScrollPane2.setMaximumSize(new java.awt.Dimension(250, 150));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(50, 30));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(50, 30));
        jScrollPane2.setViewportView(jTextAreaDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        jPanel7.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        jPanel7.add(jComboBoxDoctor, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(87, 35));
        jButtonQueueVisit.setText("\u0e04\u0e34\u0e27");
        jButtonQueueVisit.setEnabled(false);
        jButtonQueueVisit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonQueueVisit.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonQueueVisit.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonQueueVisit.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonQueueVisit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQueueVisitActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jButtonQueueVisit, gridBagConstraints);

        jLabelQueue.setText("\u0e44\u0e21\u0e48\u0e23\u0e30\u0e1a\u0e38");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel5.add(jLabelQueue, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        jPanel7.add(jPanel5, gridBagConstraints);

        jPanel20.setLayout(new java.awt.GridBagLayout());

        jButton6Week.setText("6W");
        jButton6Week.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton6Week.setMaximumSize(new java.awt.Dimension(48, 20));
        jButton6Week.setMinimumSize(new java.awt.Dimension(48, 20));
        jButton6Week.setPreferredSize(new java.awt.Dimension(48, 20));
        jButton6Week.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6WeekActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel20.add(jButton6Week, gridBagConstraints);

        jButton8Week.setText("8W");
        jButton8Week.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton8Week.setMaximumSize(new java.awt.Dimension(48, 20));
        jButton8Week.setMinimumSize(new java.awt.Dimension(48, 20));
        jButton8Week.setPreferredSize(new java.awt.Dimension(48, 20));
        jButton8Week.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8WeekActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel20.add(jButton8Week, gridBagConstraints);

        jButtonTo1Week.setText("1W");
        jButtonTo1Week.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonTo1Week.setMaximumSize(new java.awt.Dimension(48, 20));
        jButtonTo1Week.setMinimumSize(new java.awt.Dimension(48, 20));
        jButtonTo1Week.setPreferredSize(new java.awt.Dimension(48, 20));
        jButtonTo1Week.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTo1WeekActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel20.add(jButtonTo1Week, gridBagConstraints);

        jButtonTo2Week.setText("2W");
        jButtonTo2Week.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonTo2Week.setMaximumSize(new java.awt.Dimension(48, 20));
        jButtonTo2Week.setMinimumSize(new java.awt.Dimension(48, 20));
        jButtonTo2Week.setPreferredSize(new java.awt.Dimension(48, 20));
        jButtonTo2Week.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTo2WeekActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel20.add(jButtonTo2Week, gridBagConstraints);

        jButtonTo3Week.setText("3W");
        jButtonTo3Week.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonTo3Week.setMaximumSize(new java.awt.Dimension(48, 20));
        jButtonTo3Week.setMinimumSize(new java.awt.Dimension(48, 20));
        jButtonTo3Week.setPreferredSize(new java.awt.Dimension(48, 20));
        jButtonTo3Week.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTo3WeekActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel20.add(jButtonTo3Week, gridBagConstraints);

        jButton12Week.setText("12W");
        jButton12Week.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton12Week.setMaximumSize(new java.awt.Dimension(48, 20));
        jButton12Week.setMinimumSize(new java.awt.Dimension(48, 20));
        jButton12Week.setPreferredSize(new java.awt.Dimension(48, 20));
        jButton12Week.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12WeekActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        jPanel20.add(jButton12Week, gridBagConstraints);

        dateComboBoxDateAppointment.setMinimumSize(new java.awt.Dimension(103, 24));
        dateComboBoxDateAppointment.setPreferredSize(new java.awt.Dimension(103, 24));
        dateComboBoxDateAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxDateAppointmentActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel20.add(dateComboBoxDateAppointment, gridBagConstraints);

        dateComboBoxDateAppointment2.setMinimumSize(new java.awt.Dimension(103, 24));
        dateComboBoxDateAppointment2.setPreferredSize(new java.awt.Dimension(103, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel20.add(dateComboBoxDateAppointment2, gridBagConstraints);

        jPanel21.setLayout(new java.awt.GridBagLayout());

        timeTextFieldTimeAppointment.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        timeTextFieldTimeAppointment.setToolTipText("");
        timeTextFieldTimeAppointment.setMinimumSize(new java.awt.Dimension(45, 21));
        timeTextFieldTimeAppointment.setName("timeTextFieldTimeAppointment");
        timeTextFieldTimeAppointment.setPreferredSize(new java.awt.Dimension(45, 21));
        timeTextFieldTimeAppointment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                timeTextFieldTimeAppointmentKeyReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel21.add(timeTextFieldTimeAppointment, gridBagConstraints);

        jButtonTime1.setText("09.00");
        jButtonTime1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonTime1.setMaximumSize(new java.awt.Dimension(60, 20));
        jButtonTime1.setMinimumSize(new java.awt.Dimension(60, 20));
        jButtonTime1.setPreferredSize(new java.awt.Dimension(60, 20));
        jButtonTime1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTime1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel21.add(jButtonTime1, gridBagConstraints);

        jButtonTime2.setText("09.30");
        jButtonTime2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonTime2.setMaximumSize(new java.awt.Dimension(60, 20));
        jButtonTime2.setMinimumSize(new java.awt.Dimension(60, 20));
        jButtonTime2.setPreferredSize(new java.awt.Dimension(60, 20));
        jButtonTime2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTime2ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel21.add(jButtonTime2, gridBagConstraints);

        jButtonTime3.setText("10.00");
        jButtonTime3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonTime3.setMaximumSize(new java.awt.Dimension(60, 20));
        jButtonTime3.setMinimumSize(new java.awt.Dimension(60, 20));
        jButtonTime3.setPreferredSize(new java.awt.Dimension(60, 20));
        jButtonTime3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTime3ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel21.add(jButtonTime3, gridBagConstraints);

        jButtonTime4.setText("10.30");
        jButtonTime4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonTime4.setMaximumSize(new java.awt.Dimension(60, 20));
        jButtonTime4.setMinimumSize(new java.awt.Dimension(60, 20));
        jButtonTime4.setPreferredSize(new java.awt.Dimension(60, 20));
        jButtonTime4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTime4ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel21.add(jButtonTime4, gridBagConstraints);

        jButtonTime5.setText("11.00");
        jButtonTime5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonTime5.setMaximumSize(new java.awt.Dimension(60, 20));
        jButtonTime5.setMinimumSize(new java.awt.Dimension(60, 20));
        jButtonTime5.setPreferredSize(new java.awt.Dimension(60, 20));
        jButtonTime5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTime5ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel21.add(jButtonTime5, gridBagConstraints);

        jButtonTime6.setText("13.00");
        jButtonTime6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonTime6.setMaximumSize(new java.awt.Dimension(60, 20));
        jButtonTime6.setMinimumSize(new java.awt.Dimension(60, 20));
        jButtonTime6.setPreferredSize(new java.awt.Dimension(60, 20));
        jButtonTime6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTime6ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel21.add(jButtonTime6, gridBagConstraints);

        jButtonTime7.setText("13.30");
        jButtonTime7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonTime7.setMaximumSize(new java.awt.Dimension(60, 20));
        jButtonTime7.setMinimumSize(new java.awt.Dimension(60, 20));
        jButtonTime7.setPreferredSize(new java.awt.Dimension(60, 20));
        jButtonTime7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTime7ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel21.add(jButtonTime7, gridBagConstraints);

        jButtonTime8.setText("14.00");
        jButtonTime8.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonTime8.setMaximumSize(new java.awt.Dimension(60, 20));
        jButtonTime8.setMinimumSize(new java.awt.Dimension(60, 20));
        jButtonTime8.setPreferredSize(new java.awt.Dimension(60, 20));
        jButtonTime8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTime8ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel21.add(jButtonTime8, gridBagConstraints);

        jButtonTime9.setText("14.30");
        jButtonTime9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonTime9.setMaximumSize(new java.awt.Dimension(60, 20));
        jButtonTime9.setMinimumSize(new java.awt.Dimension(60, 20));
        jButtonTime9.setPreferredSize(new java.awt.Dimension(60, 20));
        jButtonTime9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTime9ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel21.add(jButtonTime9, gridBagConstraints);

        jButtonTime10.setText("15.00");
        jButtonTime10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonTime10.setMaximumSize(new java.awt.Dimension(60, 20));
        jButtonTime10.setMinimumSize(new java.awt.Dimension(60, 20));
        jButtonTime10.setPreferredSize(new java.awt.Dimension(60, 20));
        jButtonTime10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTime10ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel21.add(jButtonTime10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel20.add(jPanel21, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel20.add(jTextFieldAppointmentTemplateName, gridBagConstraints);

        jButton4Week.setText("4W");
        jButton4Week.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton4Week.setMaximumSize(new java.awt.Dimension(48, 20));
        jButton4Week.setMinimumSize(new java.awt.Dimension(48, 20));
        jButton4Week.setPreferredSize(new java.awt.Dimension(48, 20));
        jButton4Week.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4WeekActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel20.add(jButton4Week, gridBagConstraints);

        jButton1Week.setText("1W");
        jButton1Week.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1Week.setMaximumSize(new java.awt.Dimension(48, 20));
        jButton1Week.setMinimumSize(new java.awt.Dimension(48, 20));
        jButton1Week.setPreferredSize(new java.awt.Dimension(48, 20));
        jButton1Week.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1WeekActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel20.add(jButton1Week, gridBagConstraints);

        jButton2Week.setText("2W");
        jButton2Week.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton2Week.setMaximumSize(new java.awt.Dimension(48, 20));
        jButton2Week.setMinimumSize(new java.awt.Dimension(48, 20));
        jButton2Week.setPreferredSize(new java.awt.Dimension(48, 20));
        jButton2Week.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2WeekActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel20.add(jButton2Week, gridBagConstraints);

        jButton3Week.setText("3W");
        jButton3Week.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton3Week.setMaximumSize(new java.awt.Dimension(48, 20));
        jButton3Week.setMinimumSize(new java.awt.Dimension(48, 20));
        jButton3Week.setPreferredSize(new java.awt.Dimension(48, 20));
        jButton3Week.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3WeekActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel20.add(jButton3Week, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel7.add(jPanel20, gridBagConstraints);

        jPanel22.setLayout(new java.awt.GridBagLayout());

        jTableItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableItemKeyReleased(evt);
            }
        });
        jTableItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableItemMouseReleased(evt);
            }
        });

        jScrollPane5.setViewportView(jTableItem);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel22.add(jScrollPane5, gridBagConstraints);

        jPanel23.setLayout(new java.awt.GridBagLayout());

        jButtonDelOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Back16.gif")));
        jButtonDelOrder.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonDelOrder.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonDelOrder.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonDelOrder.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonDelOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelOrderActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel23.add(jButtonDelOrder, gridBagConstraints);

        jButtonAddOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Forward16.gif")));
        jButtonAddOrder.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonAddOrder.setMaximumSize(new java.awt.Dimension(26, 26));
        jButtonAddOrder.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonAddOrder.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonAddOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddOrderActionPerformed(evt);
            }
        });

        jPanel23.add(jButtonAddOrder, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel22.add(jPanel23, gridBagConstraints);

        jTableAppointmentOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(jTableAppointmentOrder);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel22.add(jScrollPane6, gridBagConstraints);

        jPanel16.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel16.add(jLabel1, gridBagConstraints);

        jTextFieldSearchOrder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchOrderKeyReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel16.add(jTextFieldSearchOrder, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel22.add(jPanel16, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel7.add(jPanel22, gridBagConstraints);

        jPanel24.setLayout(new java.awt.GridBagLayout());

        jTextFieldApType.setPreferredSize(new java.awt.Dimension(50, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel24.add(jTextFieldApType, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        jPanel7.add(jPanel24, gridBagConstraints);

        jLabel9.setText("ApType");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel7.add(jLabel9, gridBagConstraints);

        jLabel17.setText("\u0e0a\u0e37\u0e48\u0e2d\u0e15\u0e31\u0e27\u0e0a\u0e48\u0e27\u0e22\u0e19\u0e31\u0e14");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel7.add(jLabel17, gridBagConstraints);

        jLabel4.setText("DateAppointment");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        jPanel7.add(jLabel4, gridBagConstraints);

        jCheckBoxToDate.setSelected(true);
        jCheckBoxToDate.setText("DateEnd");
        jCheckBoxToDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxToDateActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 0, 0, 0);
        jPanel7.add(jCheckBoxToDate, gridBagConstraints);

        jLabel5.setText("TimeAppointment");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 0, 0, 0);
        jPanel7.add(jLabel5, gridBagConstraints);

        jLabel10.setText("ToServicePoint");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel7.add(jLabel10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        jPanel7.add(jComboBoxServicePoint, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 0, 12);
        jPanel10.add(jPanel7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 5, 0, 12);
        getContentPane().add(jPanel10, gridBagConstraints);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-860)/2, (screenSize.height-658)/2, 860, 658);
    }// </editor-fold>//GEN-END:initComponents

    private void dateComboBoxDateAppointmentActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_dateComboBoxDateAppointmentActionPerformed
    {//GEN-HEADEREND:event_dateComboBoxDateAppointmentActionPerformed
        dateComboBoxDateAppointment2.setText(
            DateUtil.convertFieldDate(dateComboBoxDateAppointment.getText()));
    }//GEN-LAST:event_dateComboBoxDateAppointmentActionPerformed

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextFieldSearchKeyReleased
    {//GEN-HEADEREND:event_jTextFieldSearchKeyReleased
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) searchAppointmentTemplate();
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) jTable1.requestFocus();
        if(jTextFieldSearch.getText().length() > 1) searchAppointmentTemplate();
    }//GEN-LAST:event_jTextFieldSearchKeyReleased

    private void jTableItemMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTableItemMouseReleased
    {//GEN-HEADEREND:event_jTableItemMouseReleased
        long clickTime = System.currentTimeMillis();
        long clickInterval = clickTime - firstClickTime;
        if(clickInterval < 400)
        {
            jButtonAddOrderActionPerformed(null);
            jTextFieldSearchOrder.requestFocus();
            firstClickTime = 0;
        }
        else
        {
            firstClickTime = clickTime;
        }
    }//GEN-LAST:event_jTableItemMouseReleased

    private void jTableItemKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTableItemKeyReleased
    {//GEN-HEADEREND:event_jTableItemKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_SPACE)
        {
            jButtonAddOrderActionPerformed(null);
            jTextFieldSearchOrder.requestFocus();
        }
    }//GEN-LAST:event_jTableItemKeyReleased

    private void jButtonTo3WeekActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonTo3WeekActionPerformed
    {//GEN-HEADEREND:event_jButtonTo3WeekActionPerformed
        calDateByWeekAndDate(3);
    }//GEN-LAST:event_jButtonTo3WeekActionPerformed

    private void jButtonTo2WeekActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonTo2WeekActionPerformed
    {//GEN-HEADEREND:event_jButtonTo2WeekActionPerformed
        calDateByWeekAndDate(2);
    }//GEN-LAST:event_jButtonTo2WeekActionPerformed

    private void jButtonTo1WeekActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonTo1WeekActionPerformed
    {//GEN-HEADEREND:event_jButtonTo1WeekActionPerformed
        calDateByWeekAndDate(1);
    }//GEN-LAST:event_jButtonTo1WeekActionPerformed

    private void jButtonTime10ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonTime10ActionPerformed
    {//GEN-HEADEREND:event_jButtonTime10ActionPerformed
        timeTextFieldTimeAppointment.setText("15:00");
    }//GEN-LAST:event_jButtonTime10ActionPerformed

    private void jButtonTime9ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonTime9ActionPerformed
    {//GEN-HEADEREND:event_jButtonTime9ActionPerformed
        timeTextFieldTimeAppointment.setText("14:30");
    }//GEN-LAST:event_jButtonTime9ActionPerformed

    private void jButtonTime8ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonTime8ActionPerformed
    {//GEN-HEADEREND:event_jButtonTime8ActionPerformed
        timeTextFieldTimeAppointment.setText("14:00");
    }//GEN-LAST:event_jButtonTime8ActionPerformed

    private void jButtonTime7ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonTime7ActionPerformed
    {//GEN-HEADEREND:event_jButtonTime7ActionPerformed
        timeTextFieldTimeAppointment.setText("13:30");
    }//GEN-LAST:event_jButtonTime7ActionPerformed

    private void jButtonTime6ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonTime6ActionPerformed
    {//GEN-HEADEREND:event_jButtonTime6ActionPerformed
        timeTextFieldTimeAppointment.setText("13:00");
    }//GEN-LAST:event_jButtonTime6ActionPerformed

    private void jButtonTime5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonTime5ActionPerformed
    {//GEN-HEADEREND:event_jButtonTime5ActionPerformed
        timeTextFieldTimeAppointment.setText("11:00");
    }//GEN-LAST:event_jButtonTime5ActionPerformed

    private void jButtonTime4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonTime4ActionPerformed
    {//GEN-HEADEREND:event_jButtonTime4ActionPerformed
        timeTextFieldTimeAppointment.setText("10:30");
    }//GEN-LAST:event_jButtonTime4ActionPerformed

    private void jButtonTime3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonTime3ActionPerformed
    {//GEN-HEADEREND:event_jButtonTime3ActionPerformed
        timeTextFieldTimeAppointment.setText("10:00");
    }//GEN-LAST:event_jButtonTime3ActionPerformed

    private void jButtonTime2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonTime2ActionPerformed
    {//GEN-HEADEREND:event_jButtonTime2ActionPerformed
        timeTextFieldTimeAppointment.setText("09:30");
    }//GEN-LAST:event_jButtonTime2ActionPerformed

    private void jButtonTime1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonTime1ActionPerformed
    {//GEN-HEADEREND:event_jButtonTime1ActionPerformed
        timeTextFieldTimeAppointment.setText("09:00");
    }//GEN-LAST:event_jButtonTime1ActionPerformed

    private void jButton12WeekActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton12WeekActionPerformed
    {//GEN-HEADEREND:event_jButton12WeekActionPerformed
        calDateByWeek(12);
    }//GEN-LAST:event_jButton12WeekActionPerformed

    private void jButton8WeekActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton8WeekActionPerformed
    {//GEN-HEADEREND:event_jButton8WeekActionPerformed
        calDateByWeek(8);
    }//GEN-LAST:event_jButton8WeekActionPerformed

    private void jButton6WeekActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton6WeekActionPerformed
    {//GEN-HEADEREND:event_jButton6WeekActionPerformed
        calDateByWeek(6);
    }//GEN-LAST:event_jButton6WeekActionPerformed

    private void jButton4WeekActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton4WeekActionPerformed
    {//GEN-HEADEREND:event_jButton4WeekActionPerformed
        calDateByWeek(4);
    }//GEN-LAST:event_jButton4WeekActionPerformed

    private void jButton3WeekActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton3WeekActionPerformed
    {//GEN-HEADEREND:event_jButton3WeekActionPerformed
        calDateByWeek(3);
    }//GEN-LAST:event_jButton3WeekActionPerformed

    private void jButton2WeekActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2WeekActionPerformed
    {//GEN-HEADEREND:event_jButton2WeekActionPerformed
        calDateByWeek(2);
    }//GEN-LAST:event_jButton2WeekActionPerformed

    private void jButton1WeekActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1WeekActionPerformed
    {//GEN-HEADEREND:event_jButton1WeekActionPerformed
        calDateByWeek(1);
    }//GEN-LAST:event_jButton1WeekActionPerformed

    private void timeTextFieldTimeAppointmentKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_timeTextFieldTimeAppointmentKeyReleased
    {//GEN-HEADEREND:event_timeTextFieldTimeAppointmentKeyReleased
       if(evt.getKeyCode() == KeyEvent.VK_ENTER)
       {
            jTextFieldApType.requestFocus();
       }
    }//GEN-LAST:event_timeTextFieldTimeAppointmentKeyReleased

    private void jButtonDelOrderActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDelOrderActionPerformed
    {//GEN-HEADEREND:event_jButtonDelOrderActionPerformed
        int row[] = jTableAppointmentOrder.getSelectedRows();
        if(row.length==0) 
        {
            setStatus("ยังไม่มีข้อมูล",UpdateStatus.WARNING);
            return ;
        }
        theHC.thePatientControl.deleteAppointmentTemplateItem(vAppointmentTemplateItem,row);
        this.setTableAppointmentTemplateItem(vAppointmentTemplateItem);
    }//GEN-LAST:event_jButtonDelOrderActionPerformed

    private void jTextFieldSearchOrderKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextFieldSearchOrderKeyReleased
    {//GEN-HEADEREND:event_jTextFieldSearchOrderKeyReleased
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) searchItem();
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) jTableItem.requestFocus();
        if(jTextFieldSearchOrder.getText().length() > 1) searchItem();
    }//GEN-LAST:event_jTextFieldSearchOrderKeyReleased

    private void jButtonAddOrderActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddOrderActionPerformed
    {//GEN-HEADEREND:event_jButtonAddOrderActionPerformed
        int[] row = jTableItem.getSelectedRows();
        if(row.length==0)
        {   
            setStatus("ยังไม่ได้เลือกรายการ Item",UpdateStatus.WARNING);
            return;
        }
        if(vAppointmentTemplateItem==null) 
        {
            vAppointmentTemplateItem = new Vector();
        }
        Item item;
        AppointmentTemplateItem apti;
        for(int i=0,size=row.length; i<size; i++)
        {   
            item = (Item)vItem.get(row[i]);             
            if(vAppointmentTemplateItem.isEmpty())//รอบแรกให้ add ได้เลย
            {
                apti = new AppointmentTemplateItem();
                apti.item_id = item.getObjectId();
                apti.item_common_name = item.common_name;
                vAppointmentTemplateItem.add(apti);
            }
            else
            {               
                boolean isSame = false;
                for(int j=0; j<vAppointmentTemplateItem.size(); j++)
                {
                    if(item.getObjectId().equals(((AppointmentTemplateItem)vAppointmentTemplateItem.get(j)).item_id)) isSame = true;                    
                } 
                if(!isSame)
                {
                    apti = new AppointmentTemplateItem();
                    apti.item_id = item.getObjectId();
                    apti.item_common_name = item.common_name;
                    vAppointmentTemplateItem.add(apti);
                }
            }
        }      
        setTableAppointmentTemplateItem(vAppointmentTemplateItem);   
    }//GEN-LAST:event_jButtonAddOrderActionPerformed

    private void jCheckBoxToDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxToDateActionPerformed
        dateComboBoxDateAppointment2.setEnabled(jCheckBoxToDate.isSelected());
        jButtonTo1Week.setEnabled(jCheckBoxToDate.isSelected());
        jButtonTo2Week.setEnabled(jCheckBoxToDate.isSelected());
        jButtonTo3Week.setEnabled(jCheckBoxToDate.isSelected());
    }//GEN-LAST:event_jCheckBoxToDateActionPerformed
    
    private void jButtonQueueVisitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQueueVisitActionPerformed
        if(theQueueVisit==null)
            theQueueVisit = new QueueVisit();
        if(theAppointmentTemplate!=null)
        {
            theQueueVisit.setObjectId(theAppointmentTemplate.queue_visit_id);
        }
        DialogQueueVisit.showDialog(theHC,this,1,theQueueVisit);
        jLabelQueue.setText(theQueueVisit.description);
    }//GEN-LAST:event_jButtonQueueVisitActionPerformed

    private void dateComboBoxDateAppointmentFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxDateAppointmentFocusLost

    }//GEN-LAST:event_dateComboBoxDateAppointmentFocusLost

    private void jButtonDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelActionPerformed
            theHC.thePatientControl.deleteAppointmentTemplate(theAppointmentTemplate,vAppointmentTemplateItem);             
            initAppointmentTemplate();         
            theQueueVisit = null;
            updateOGAppointmentTemplate();
            vAppointmentTemplateItem = null;
            setTableAppointmentTemplateItem(null); 
            searchAppointmentTemplate();

            setStatus("ยกเลิกตัวช่วยนัดเสร็จสิ้น",UpdateStatus.COMPLETE);
    }//GEN-LAST:event_jButtonDelActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        setVisible(false);
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void comboBoxClinic1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxClinic1ActionPerformed
    }//GEN-LAST:event_comboBoxClinic1ActionPerformed

	private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        int row = jTable1.getSelectedRow();
        if(row==-1) return;
        theAppointmentTemplate = (AppointmentTemplate)vAppointmentTemplate.get(row);
        updateOGAppointmentTemplate();
        //เอาค่า commonname มาด้วย
        vAppointmentTemplateItem = theHC.thePatientControl.listAppointmentTemplateItem(theAppointmentTemplate.getObjectId());
        setTableAppointmentTemplateItem(vAppointmentTemplateItem);        
    }//GEN-LAST:event_jTable1MouseReleased

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_SPACE)
        {
            jTable1MouseReleased(null);
            dateComboBoxDateAppointment.requestFocus();
        }
    }//GEN-LAST:event_jTable1KeyReleased
    
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
         if("".equals(jTextFieldAppointmentTemplateName.getText()))
         {
            setStatus("กรุณาระบุชื่อตัวช่วย",UpdateStatus.WARNING);
            return;
         }
         actionCommand = true;
         updateGOAppointmentTemplate();         
         theHC.thePatientControl.saveAppointmentTemplate(theAppointmentTemplate,vAppointmentTemplateItem);         
         searchAppointmentTemplate();
         setStatus("บันทึกตัวช่วยนัดเสร็จสิ้น",UpdateStatus.COMPLETE);
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
         actionCommand = false;
         jTable1.clearSelection();         
         initAppointmentTemplate();         
         theQueueVisit = null;
         updateOGAppointmentTemplate();
         vAppointmentTemplateItem = null;
         setTableAppointmentTemplateItem(null);
         this.setStatus("กรุณากรอกข้อมูลตัวช่วยการนัด",UpdateStatus.COMPLETE);
    }//GEN-LAST:event_jButtonAddActionPerformed
    
    /**
     *@Author: amp
     *@date: 10/8/2549
     *@see: set ค่าเริ่มต้นให้กับ AppointmentTemplate
     *@return: AppointmentTemplate
     */
    private void initAppointmentTemplate()
    {
        theAppointmentTemplate= new AppointmentTemplate();
        theAppointmentTemplate.service_point = Gutil.getGuiData(jComboBoxServicePoint);
        theAppointmentTemplate.doctor = Gutil.getGuiData(jComboBoxDoctor);  
    }

    /**
     *@Author: amp
     *@date: 10/9/2549
     *@see: นำค่าที่เก็บอยู่ใน Object แสดงบน GUI
     */
    public void updateOGAppointmentTemplate()
    {  
        jTextFieldAppointmentTemplateName.setText(theAppointmentTemplate.template_name);
        dateComboBoxDateAppointment.setText(DateUtil.convertFieldDate(theAppointmentTemplate.date));
        dateComboBoxDateAppointment2.setText(DateUtil.convertFieldDate(theAppointmentTemplate.date_to));
        timeTextFieldTimeAppointment.setText(theAppointmentTemplate.time);
        jTextFieldApType.setText(theAppointmentTemplate.aptype);        
        Gutil.setGuiData(jComboBoxServicePoint,theAppointmentTemplate.service_point);
        Gutil.setGuiData(jComboBoxDoctor,theAppointmentTemplate.doctor);
        jTextAreaDescription.setText(theAppointmentTemplate.description); 
        if(IOption)
        {   
            //QueueVisit qv = theHC.theVisitControl.readQueueVisitByQueueVisitID(theAppointmentTemplate.queue_visit_id);
            QueueVisit qv = theHC.theLookupControl.readQueueVisitById(theAppointmentTemplate.queue_visit_id);
            if(qv!=null) 
            {
                jLabelQueue.setText(qv.description);
            }
            else         
            {
                jLabelQueue.setText(GuiLang.setLanguage("ไม่ระบุ"));
            }
        }
        if(AppointmentTemplate.DM.equals(theAppointmentTemplate.getObjectId())  ||
            AppointmentTemplate.HT.equals(theAppointmentTemplate.getObjectId()) ||
            AppointmentTemplate.H.equals(theAppointmentTemplate.getObjectId()))
        {
            jTextFieldAppointmentTemplateName.setEnabled(false);
            jButtonDel.setEnabled(false);
        }
        else
        {
            jTextFieldAppointmentTemplateName.setEnabled(true);
            jButtonDel.setEnabled(true);
        }
     } 
    
    /**
     *@Author: amp
     *@date: 10/09/2549
     *@see: แสดงรายการ item ที่สั่งล่วงหน้า
     *@param: 
     */
    private void setTableAppointmentTemplateItem(Vector vc)
    {
        String[] column = {GuiLang.setLanguage("รายการตรวจรักษาล่วงหน้า")};
        TaBleModel tm;
        if(vc != null)
        {           
            tm = new TaBleModel(column,vc.size());
            for(int i=0,size=vc.size(); i<size; i++)
            {  
                AppointmentTemplateItem apti = (AppointmentTemplateItem)vc.get(i);
                tm.setValueAt(apti.item_common_name,i,0);
            }
        }
        else
        {
            tm = new TaBleModel(column,0);            
        }
        jTableAppointmentOrder.setModel(tm);
    }

    /**
     *@Author: amp
     *@date: 10/09/2549
     *@see: โชว์Dialog
     */
    public void showDialog()
    {
        initAppointmentTemplate();
        searchAppointmentTemplate();
        IOption = Gutil.isSelected(theHC.theLookupControl.readOption().inqueuevisit);        
        setVisible(true);
        setEnableAll(true);
        this.jButtonAddActionPerformed(null);
        vItem = null;
        setTableItem(null);        
        vAppointmentTemplateItem = null;
        setTableAppointmentTemplateItem(null);        
    }
    
    /**
     *@Author: amp
     *@date: 10/09/2549
     *@see: ค้นหาตัวช่วยนัด
     */
    private void searchAppointmentTemplate()
    {
        vAppointmentTemplate = theHC.thePatientControl.listAppointmentTemplateByName(jTextFieldSearch.getText());        
        setTableAppointmentTemplate(vAppointmentTemplate); 
    }
    
    /**
     *@Author: amp
     *@date: 10/08/2549
     *@see: แสดงตัวช่วยนัดที่มีในตาราง
     *@param: Vector ตัวช่วยนัด (AppointmentTemplate)
     */
    private void setTableAppointmentTemplate(Vector vc)
    {                
        TaBleModel tm ;
        if(vc == null || vc.isEmpty())
        {   
            tm= new TaBleModel(col_head,0);
            jTable1.setModel(tm); 
            return;
        }
        tm = new TaBleModel(col_head,vc.size());
        AppointmentTemplate apt;
        for(int i=0 ;i<vc.size(); i++)
        {
            apt = (AppointmentTemplate)vc.get(i);
            tm.setValueAt(apt.template_name,i,0);
            if("".equals(apt.date))
            {
                tm.setValueAt("",i,1);
            }
            else
            {
                tm.setValueAt(DateUtil.getDateFromText(apt.date),i,1);
            }
        }        
        jTable1.setModel(tm);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(100); 
        jTable1.getColumnModel().getColumn(1).setCellRenderer(dateRender);       
    } 
    
    /**
     *@Author : amp
     *@date : 10/09/2549
     *@see : แสดงรายการ item ที่ค้นเจอ
     *@param: Vector ของ item
     */
    private void setTableItem(Vector vc)
    {  
        String[] column = {GuiLang.setLanguage("รายการตรวจรักษา")};
        TaBleModel tm;
        if(vc == null)
        {
            tm = new TaBleModel(column,0);
            jTableItem.setModel(tm);
            return;
        }
        tm = new TaBleModel(column,vc.size());
        for(int i=0,size=vc.size(); i<size; i++)
        {  
            Item item = (Item)vc.get(i);
            tm.setValueAt(item.common_name,i,0);
        }
        jTableItem.setModel(tm);           
    }
    
    public JFrame getJFrame() 
    {
        return this;
    }
    
    /**
     *เซตความยาวDialog
     */
    private void setDialog()
    {   
        setSize(728, 564);
        setTitle(Constant.getTextBundle("ตัวช่วยนัด"));
        Toolkit thekit = getToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-getSize().width)/2, (screenSize.height-getSize().height)/2);
    }
    
    /**
     *@Author: amp
     *@date: 10/08/2549
     *@see: แสดงวันที่จะนัดโดยคำนวณจาก week ที่เลือก
     *@param: จำนวน week
     */
    private void calDateByWeek(int week)
    {
        dateComboBoxDateAppointment.setText(DateUtil.calDateByWeek(week));
    }
    
    /**
     *@Author: amp
     *@date: 10/08/2549
     *@see: แสดงถึงวันที่ โดยคำนวณจาก week ที่เลือก นับจากวันที่นัด
     *@param: จำนวน week
     */
    private void calDateByWeekAndDate(int week)
    {
        String date_appintment = dateComboBoxDateAppointment.getText();
        if("".equals(date_appintment))
        {
            setStatus("ยังไม่ระบุวันที่นัด",UpdateStatus.WARNING);
            return;
        }
        dateComboBoxDateAppointment2.setText(DateUtil.calDateByWeek(date_appintment,week));
    }
    
    /**
     *@Author: amp
     *@date: 9/8/2549
     *@see: ค้นหา Item ยกเว้นแลปปกปิด
     *@param: จำนวน week
     */
    private void searchItem()
    {
        String search = jTextFieldSearchOrder.getText();
        vItem = theHC.theOrderControl.listItemByGroup("",search);
        setTableItem(vItem);
    }
    
    /**
     *@Author: amp
     *@date: 10/08/2549
     *@see: set ค่าบน GUI ให้กับ Object
     */
    public void updateGOAppointmentTemplate()
    {          
        theAppointmentTemplate.template_name = jTextFieldAppointmentTemplateName.getText();
        theAppointmentTemplate.date = dateComboBoxDateAppointment.getText();
        theAppointmentTemplate.date_to = dateComboBoxDateAppointment2.getText();
        theAppointmentTemplate.time = timeTextFieldTimeAppointment.getText();
        theAppointmentTemplate.aptype = Gutil.CheckReservedWords(jTextFieldApType.getText()); 
        theAppointmentTemplate.service_point = Gutil.getGuiData(jComboBoxServicePoint);        
        theAppointmentTemplate.doctor = Gutil.getGuiData(jComboBoxDoctor);
        theAppointmentTemplate.description = Gutil.CheckReservedWords(jTextAreaDescription.getText());
        theAppointmentTemplate.queue_visit_id  = "";
        if(theQueueVisit != null)
            theAppointmentTemplate.queue_visit_id  = theQueueVisit.getObjectId();
    }
    
    /**
     *ใช้ในการ Map QueueVisit
     */
    private MapQueueVisit getMapQueueVisit(Visit v,SpecialQueryAppointment spappointment)
    throws Exception
    {   
        MapQueueVisit mapQueueVisit = new MapQueueVisit();
        QueueVisit queueVisit = theHC.theVisitControl.readSeqQueueVisit(
            spappointment.b_visit_queue_setup_id);
        if(queueVisit == null)
            throw new Exception(Constant.getTextBundle("ไม่พบข้อมูลคิวในการรับบริการ QueueVisit not found"));
        mapQueueVisit.queue_visit  = spappointment.b_visit_queue_setup_id;
        mapQueueVisit.queue = queueVisit.queue;
        return mapQueueVisit;
    }
    
    /**
     *เซตปุ่มต่างๆ
     */
    private void setEnableAll(boolean var)
    {   
        dateComboBoxDateAppointment.setEnabled(var);
        dateComboBoxDateAppointment.setEditable(var);
        timeTextFieldTimeAppointment.setEditable(var);
        jButtonAdd.setEnabled(var);
        jTextFieldApType.setEnabled(var);
        jComboBoxServicePoint.setEnabled(var);        
        jComboBoxDoctor.setEnabled(var);  
        jTextAreaDescription.setEnabled(var);
        jButtonSave.setEnabled(var);
        jButtonDel.setEnabled(var);        
        jButtonQueueVisit.setEnabled(var);
        jTextFieldSearchOrder.setEnabled(var);
        jButtonAddOrder.setEnabled(var);
        jButtonDelOrder.setEnabled(var);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.hospital_os.utility.DateComboBox dateComboBoxDateAppointment;
    private com.hospital_os.utility.DateComboBox dateComboBoxDateAppointment2;
    private javax.swing.JButton jButton12Week;
    private javax.swing.JButton jButton1Week;
    private javax.swing.JButton jButton2Week;
    private javax.swing.JButton jButton3Week;
    private javax.swing.JButton jButton4Week;
    private javax.swing.JButton jButton6Week;
    private javax.swing.JButton jButton8Week;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAddOrder;
    private javax.swing.JButton jButtonDel;
    private javax.swing.JButton jButtonDelOrder;
    private javax.swing.JButton jButtonQueueVisit;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonTime1;
    private javax.swing.JButton jButtonTime10;
    private javax.swing.JButton jButtonTime2;
    private javax.swing.JButton jButtonTime3;
    private javax.swing.JButton jButtonTime4;
    private javax.swing.JButton jButtonTime5;
    private javax.swing.JButton jButtonTime6;
    private javax.swing.JButton jButtonTime7;
    private javax.swing.JButton jButtonTime8;
    private javax.swing.JButton jButtonTime9;
    private javax.swing.JButton jButtonTo1Week;
    private javax.swing.JButton jButtonTo2Week;
    private javax.swing.JButton jButtonTo3Week;
    private javax.swing.JCheckBox jCheckBoxToDate;
    private javax.swing.JComboBox jComboBoxDoctor;
    private javax.swing.JComboBox jComboBoxServicePoint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelQueue;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelSearch;
    protected javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    protected com.hosv3.gui.component.HJTableSort jTable1;
    private com.hosv3.gui.component.HJTableSort jTableAppointmentOrder;
    private com.hosv3.gui.component.HJTableSort jTableItem;
    private com.hosv3.gui.component.BalloonTextArea jTextAreaDescription;
    private javax.swing.JTextField jTextFieldApType;
    private javax.swing.JTextField jTextFieldAppointmentTemplateName;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JTextField jTextFieldSearchOrder;
    private com.hospital_os.utility.TimeTextField timeTextFieldTimeAppointment;
    // End of variables declaration//GEN-END:variables
   
}
