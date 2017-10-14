/*
 * ListVisitByTransfer.java
 *
 * Created on 17 ตุลาคม 2546, 18:21 น.
 */
package com.hosv3.gui.panel.transaction;

import java.awt.CardLayout;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import com.hosv3.object.HosObject;
import com.hosv3.control.*;
import com.hosv3.utility.*;
import com.hosv3.usecase.transaction.*;
import com.hosv3.utility.connection.*;
import com.hosv3.gui.component.CellRendererColor;
import com.hosv3.gui.dialog.HosDialog;
import com.hosv3.subject.*;
import com.hosv3.object.*;
import com.hospital_os.object.*;
import com.hospital_os.utility.CelRendererTranfer;
import com.hospital_os.utility.CellRendererHos;
import com.hospital_os.utility.CelRendererDespen;
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.Gutil;
import com.hosv3.utility.Constant;
import com.hospital_os.utility.Audio;
import com.hospital_os.utility.TaBleModel;
/**
 *
 * @author  Administrator
 */
public class PanelListVisitByTransfer extends javax.swing.JPanel
implements ManageVisitResp,ManagePatientResp,ManageOrderResp,ManageLabXrayResp
{
	static final long serialVersionUID = 0;
    HosObject theHO;
    HosControl theHC;
    HosSubject theHS;
    UpdateStatus theUS;
    HosDialog theHD;
    /** ใช้ในการควบคุมการทำงานร่วมกันของ GUI */
    Visit theVisit;
    Vector theTransfer;
    Vector theQueueDispense2V;
    /**เป็นการแสดง สีของ การถูกล็อก*/
    CelRendererDespen celrender =  new CelRendererDespen();
    CelRendererTranfer cellRendererTranfer = new CelRendererTranfer();
    CellRendererHos vnRender = new CellRendererHos(CellRendererHos.VN);
    CellRendererHos hnRender = new CellRendererHos(CellRendererHos.HN);
    CellRendererHos dateRender = new CellRendererHos(CellRendererHos.DATE_TIME);
    CellRendererHos allergyRender = new CellRendererHos(CellRendererHos.ALLERGY);
    CellRendererHos labRender = new CellRendererHos(CellRendererHos.LAB_STATUS);
    CellRendererColor theCellRendererColor = new CellRendererColor(true);
    Vector vListTransfer = new Vector();  // แทน theTransfer
    Vector vListRemain = new Vector();
    int ref = 0;
    boolean used_queue = false;
     String[] col_sp_queue={"ลำดับ","ล็อก","แพ้ยา","HN","VN","ชื่อ-สกุล","เวลา","จุดบริการ","เลขคิว","คิว","แลป"};
     String[] col_sp =     {"ลำดับ","ล็อก","แพ้ยา","HN","VN","ชื่อ-สกุล","เวลา","จุดบริการ"             ,"แลป"};
     String[] col_lab =    {"ลำดับ","ล็อก","แพ้ยา","HN","VN","ชื่อ-สกุล","เวลา"                       ,"แลป","รหัสสิ่งส่งตรวจ"};
     String[] col_dispense={"ลำดับ"             ,"HN","VN","ชื่อ-สกุล"      ,"จุดบริการสุดท้าย","เวลาเริ่มรอจ่ายยา","จำนวน"};
     String[] col_sp_dx =  {"ลำดับ","ล็อก","แพ้ยา","HN","VN","ชื่อ-สกุล","เวลา","Dx","แลป","คิว"};
     //ใช้ในการแสดงลำดับเลขที่ในตาราง Transaction
    int countrowTransaction = 0;
    //ใช้ในการตรวจสอบ การแสดงผลของ combobox ของ แพทย์
    String authen;
    Timer theTimer;
    //จำนวนผู้ป่วยในจุดบริการ
    int countpatient = 0 ;

    private Vector vQueueStat;

    /** Creates new form ListVisitByTransfer */
    public PanelListVisitByTransfer() {
        initComponents();
        setLanguage(null);
        jTableQueueOpd.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableQueueDispense.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }
    public void setControl(HosControl hc, UpdateStatus us)
    {
        theHC = hc;
        theHO = hc.theHO;
        theHS = hc.theHS;
        theUS = us;
    vnRender = new CellRendererHos(CellRendererHos.VN,theHC.theLookupControl.getSequenceDataVN().pattern);
    hnRender = new CellRendererHos(CellRendererHos.HN,theHC.theLookupControl.getSequenceDataHN().pattern);
        theHS.theVisitSubject.attachManageVisit(this);
        theHS.thePatientSubject.attachManagePatient(this);
        theHS.theOrderSubject.attachManageOrder(this);
        theHS.theResultSubject.attachManageLab(this);
        theHS.theResultSubject.attachManageXray(this);
        setUserAuth();
//        setLanguage(null);

        theTimer = new Timer(Constant.TIME_REFRESH,new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(!theHO.theEmployee.authentication_id.equals(Authentication.STAT)){
                    refreshButton();
                }
            }
        });
        theTimer.start();
        setQueueStatV(new Vector());
    }
    public void setEnabled(boolean enable){
        jTableQueueDispense.setEnabled(enable);
        jTableQueueOpd.setEnabled(enable);
    }
    public void setDialog(HosDialog hd){
        theHD = hd;
    }
    private void setLanguage(String str)
    {
        GuiLang.setLanguage(jButtonDoctorDischarge);
        GuiLang.setLanguage(jCheckBoxSearchByDate);
        GuiLang.setLanguage(jLabelTo);
        GuiLang.setLanguage(jToggleButton1);
        GuiLang.setLanguage(col_dispense);
        GuiLang.setLanguage(col_sp_queue);
        GuiLang.setLanguage(col_sp);
        GuiLang.setLanguage(col_lab);
        GuiLang.setLanguage(col_sp_dx);
        GuiLang.setLanguage(buttonGroupPatient);
        GuiLang.setLanguage(jComboBoxDoctor);
        GuiLang.setLanguage(jComboBoxSpoint);
        GuiLang.setLanguage(jLabelDoctor);
        GuiLang.setLanguage(jLabelPname);
        GuiLang.setLanguage(jPanelTop);
        GuiLang.setLanguage(jPanelTop1);
        GuiLang.setLanguage(jRadioButtonAll);
        GuiLang.setLanguage(jRadioButtonIPD);
        GuiLang.setLanguage(jRadioButtonOPD);
        GuiLang.setLanguage(jScrollPane);
        GuiLang.setLanguage(jScrollPane1);
        GuiLang.setLanguage(jTableQueueOpd);
        GuiLang.setLanguage(jTableQueueDispense);
        GuiLang.setLanguage(jButton1);
        GuiLang.setLanguage(jToggleButtonSound);
    }

    protected void initComboBox(){
        setUserAuth();
    }

    protected void setUserAuth(){
        authen = theHO.theEmployee.authentication_id;
        used_queue = theHC.theLookupControl.readOption().inqueuevisit.equals("1");
        this.jPanelRadioPttype.setVisible(theHO.theGActionAuthV.isReadMenuIpd());
        jPanelQDispense.setVisible(false);
        ComboboxModel.initComboBox(jComboBoxSpoint,theHC.theLookupControl.listServicePointwithOutXL());
        jComboBoxSpoint.insertItemAt(theHO.getServicePointUD(),0);
        chooseServicePoint(theHO.theServicePoint);
        jPanelStatSearch.setVisible(false);
        jPanelTop1.setVisible(true);

        if(authen.equals(Authentication.PHARM)
            || authen.equals(Authentication.ONE)
            || authen.equals(Authentication.LAB)
            || authen.equals(Authentication.HEALTH)){
            jPanelQDispense.setVisible(true);
        }

        if(authen.equals(Authentication.XRAY)
        || authen.equals(Authentication.LAB)
        ||authen.equals(Authentication.STAT)){
            jComboBoxSpoint.setEnabled(false);
            jComboBoxDoctor.setEnabled(false);
        }

        if(authen.equals(Authentication.STAT))
        {
            jRadioButtonAll.setSelected(true);
            this.jToggleButton1.setSelected(true);
            this.jToggleButton1ActionPerformed(null);
            jPanelTop1.setVisible(false);
            jTableQueueOpd.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        }
        else{
            jPanelTop1.setVisible(true);
            jTableQueueOpd.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
        //refreshButton();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroupPatient = new javax.swing.ButtonGroup();
        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jPanelTop = new javax.swing.JPanel();
        jPanelTop1 = new javax.swing.JPanel();
        jLabelPname = new javax.swing.JLabel();
        jLabelDoctor = new javax.swing.JLabel();
        jComboBoxDoctor = new javax.swing.JComboBox();
        jComboBoxSpoint = new javax.swing.JComboBox();
        jPanelRadioPttype = new javax.swing.JPanel();
        jRadioButtonOPD = new javax.swing.JRadioButton();
        jRadioButtonAll = new javax.swing.JRadioButton();
        jRadioButtonIPD = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jToggleButtonSound = new javax.swing.JToggleButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanelStatSearch = new javax.swing.JPanel();
        dateComboBoxStart = new com.hospital_os.utility.DateComboBox();
        jLabelTo = new javax.swing.JLabel();
        dateComboBoxEnd = new com.hospital_os.utility.DateComboBox();
        jCheckBoxSearchByDate = new javax.swing.JCheckBox();
        jButtonDoctorDischarge = new javax.swing.JButton();
        jToggleButtonQueue = new javax.swing.JToggleButton();
        jPanelCard = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanelCenter = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        jTableQueueOpd = new com.hosv3.gui.component.HJTableSort();
        jPanelQDispense = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableQueueDispense = new com.hosv3.gui.component.HJTableSort();
        jButtonDispense = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableQueueStat = new com.hosv3.gui.component.HJTableSort();

        setLayout(new java.awt.GridBagLayout());

        jPanelTop.setLayout(new java.awt.GridBagLayout());

        jPanelTop1.setLayout(new java.awt.GridBagLayout());

        jLabelPname.setFont(defaultFont1);
        jLabelPname.setText("จุดบริการ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 0);
        jPanelTop1.add(jLabelPname, gridBagConstraints);

        jLabelDoctor.setFont(defaultFont1);
        jLabelDoctor.setText("แพทย์");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 12, 2, 0);
        jPanelTop1.add(jLabelDoctor, gridBagConstraints);

        jComboBoxDoctor.setFont(defaultFont1);
        jComboBoxDoctor.setMinimumSize(new java.awt.Dimension(66, 24));
        jComboBoxDoctor.setPreferredSize(new java.awt.Dimension(66, 24));
        jComboBoxDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDoctorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 2);
        jPanelTop1.add(jComboBoxDoctor, gridBagConstraints);

        jComboBoxSpoint.setFont(defaultFont1);
        jComboBoxSpoint.setMinimumSize(new java.awt.Dimension(66, 24));
        jComboBoxSpoint.setPreferredSize(new java.awt.Dimension(66, 24));
        jComboBoxSpoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSpointActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 2);
        jPanelTop1.add(jComboBoxSpoint, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelTop.add(jPanelTop1, gridBagConstraints);

        jPanelRadioPttype.setLayout(new java.awt.GridBagLayout());

        buttonGroupPatient.add(jRadioButtonOPD);
        jRadioButtonOPD.setFont(defaultFont1);
        jRadioButtonOPD.setSelected(true);
        jRadioButtonOPD.setText("OPD");
        jRadioButtonOPD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonOPDActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanelRadioPttype.add(jRadioButtonOPD, gridBagConstraints);

        buttonGroupPatient.add(jRadioButtonAll);
        jRadioButtonAll.setFont(defaultFont1);
        jRadioButtonAll.setText("ALL");
        jRadioButtonAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAllActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 3, 2, 0);
        jPanelRadioPttype.add(jRadioButtonAll, gridBagConstraints);

        buttonGroupPatient.add(jRadioButtonIPD);
        jRadioButtonIPD.setFont(defaultFont1);
        jRadioButtonIPD.setText("IPD");
        jRadioButtonIPD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonIPDActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 3, 2, 0);
        jPanelRadioPttype.add(jRadioButtonIPD, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelTop.add(jPanelRadioPttype, gridBagConstraints);

        jButton1.setFont(defaultFont1);
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/Refresh.png"))); // NOI18N
        jButton1.setToolTipText("เรียกดูข้อมูลใหม่");
        jButton1.setMinimumSize(new java.awt.Dimension(26, 26));
        jButton1.setPreferredSize(new java.awt.Dimension(26, 26));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelTop.add(jButton1, gridBagConstraints);

        jToggleButtonSound.setFont(defaultFont1);
        jToggleButtonSound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/play_sound_off.gif"))); // NOI18N
        jToggleButtonSound.setToolTipText("เปิด/ปิดเสียง ");
        jToggleButtonSound.setMaximumSize(new java.awt.Dimension(27, 27));
        jToggleButtonSound.setMinimumSize(new java.awt.Dimension(26, 26));
        jToggleButtonSound.setPreferredSize(new java.awt.Dimension(26, 26));
        jToggleButtonSound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonSoundActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelTop.add(jToggleButtonSound, gridBagConstraints);

        jToggleButton1.setFont(defaultFont1);
        jToggleButton1.setText("สถิติ");
        jToggleButton1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelTop.add(jToggleButton1, gridBagConstraints);

        jPanelStatSearch.setLayout(new java.awt.GridBagLayout());

        dateComboBoxStart.setFont(defaultFont1);
        dateComboBoxStart.setMinimumSize(new java.awt.Dimension(93, 24));
        dateComboBoxStart.setPreferredSize(new java.awt.Dimension(93, 24));
        dateComboBoxStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxStartActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 5);
        jPanelStatSearch.add(dateComboBoxStart, gridBagConstraints);

        jLabelTo.setFont(defaultFont1);
        jLabelTo.setText("ถึง");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 5);
        jPanelStatSearch.add(jLabelTo, gridBagConstraints);

        dateComboBoxEnd.setFont(defaultFont1);
        dateComboBoxEnd.setMinimumSize(new java.awt.Dimension(93, 24));
        dateComboBoxEnd.setPreferredSize(new java.awt.Dimension(93, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 5);
        jPanelStatSearch.add(dateComboBoxEnd, gridBagConstraints);

        jCheckBoxSearchByDate.setFont(defaultFont1);
        jCheckBoxSearchByDate.setSelected(true);
        jCheckBoxSearchByDate.setText("วันที่");
        jCheckBoxSearchByDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSearchByDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 5);
        jPanelStatSearch.add(jCheckBoxSearchByDate, gridBagConstraints);

        jButtonDoctorDischarge.setFont(defaultFont1);
        jButtonDoctorDischarge.setText("จำหน่าย");
        jButtonDoctorDischarge.setToolTipText("ตามรายการที่เลือก");
        jButtonDoctorDischarge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDoctorDischargeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanelStatSearch.add(jButtonDoctorDischarge, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        jPanelTop.add(jPanelStatSearch, gridBagConstraints);

        jToggleButtonQueue.setFont(new java.awt.Font("Times New Roman", 3, 18));
        jToggleButtonQueue.setForeground(java.awt.Color.blue);
        jToggleButtonQueue.setText("Q");
        jToggleButtonQueue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonQueueActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelTop.add(jToggleButtonQueue, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanelTop, gridBagConstraints);

        jPanelCard.setLayout(new java.awt.CardLayout());

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(1.0);
        jSplitPane1.setOneTouchExpandable(true);

        jPanelCenter.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ผู้ป่วยในจุดบริการ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, defaultFont1));
        jPanelCenter.setMinimumSize(new java.awt.Dimension(150, 47));
        jPanelCenter.setPreferredSize(new java.awt.Dimension(150, 47));
        jPanelCenter.setLayout(new java.awt.GridBagLayout());

        jScrollPane.setPreferredSize(new java.awt.Dimension(453, 403));

        jTableQueueOpd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableQueueOpd.setFont(defaultFont1);
        jTableQueueOpd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableQueueOpdKeyReleased(evt);
            }
        });
        jTableQueueOpd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableQueueOpdMouseReleased(evt);
            }
        });
        jScrollPane.setViewportView(jTableQueueOpd);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelCenter.add(jScrollPane, gridBagConstraints);

        jSplitPane1.setLeftComponent(jPanelCenter);

        jPanelQDispense.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ผู้ป่วยรอจ่ายยา", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jPanelQDispense.setMinimumSize(new java.awt.Dimension(150, 150));
        jPanelQDispense.setPreferredSize(new java.awt.Dimension(150, 150));
        jPanelQDispense.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(453, 403));

        jTableQueueDispense.setFont(defaultFont1);
        jTableQueueDispense.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableQueueDispenseMouseReleased(evt);
            }
        });
        jTableQueueDispense.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableQueueDispenseKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableQueueDispense);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelQDispense.add(jScrollPane1, gridBagConstraints);

        jButtonDispense.setFont(defaultFont1);
        jButtonDispense.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/dispense.gif"))); // NOI18N
        jButtonDispense.setToolTipText(GuiLang.setLanguage("จ่ายยาที่ละหลายคน"));
        jButtonDispense.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonDispense.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonDispense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDispenseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelQDispense.add(jButtonDispense, gridBagConstraints);

        jSplitPane1.setRightComponent(jPanelQDispense);

        jPanelCard.add(jSplitPane1, "card_opd");

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setPreferredSize(new java.awt.Dimension(453, 403));

        jTableQueueStat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableQueueStat.setFont(defaultFont1);
        jTableQueueStat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableQueueStatKeyReleased(evt);
            }
        });
        jTableQueueStat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableQueueStatMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTableQueueStat);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jScrollPane2, gridBagConstraints);

        jPanelCard.add(jPanel3, "card_stat");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanelCard, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents



    private void jButtonDispenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDispenseActionPerformed


        int[] a = this.jTableQueueDispense.getSelectedRows();
        theHC.theOrderControl.dispenseOrderItems(theQueueDispense2V,a);

    }//GEN-LAST:event_jButtonDispenseActionPerformed



    private void jToggleButtonQueueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonQueueActionPerformed


// TODO add your handling code here:

    }//GEN-LAST:event_jToggleButtonQueueActionPerformed



    private void jTableQueueStatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableQueueStatKeyReleased


        if(evt.getKeyCode()==KeyEvent.VK_UP || evt.getKeyCode()==KeyEvent.VK_DOWN)
            jTableQueueStatMouseReleased(null);

    }//GEN-LAST:event_jTableQueueStatKeyReleased



    private void jTableQueueStatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableQueueStatMouseReleased


            //กรณี stat เลือกผู้ป่วยหลายคนเพื่อจำหน่ายเพียงครั้งเดียว
        int[] rows = jTableQueueStat.getSelectedRows();
        if(rows.length>1)
            return;

        int row = this.jTableQueueStat.getSelectedRow();
        String[] t = (String[])vQueueStat.get(row);
        theHC.theVisitControl.readVisitPatientByVid(t[t.length-1],"0",null);

    }//GEN-LAST:event_jTableQueueStatMouseReleased



    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed


        CardLayout cl = (CardLayout)jPanelCard.getLayout();
        jPanelStatSearch.setVisible(jToggleButton1.isSelected());
        jPanelTop1.setVisible(!jToggleButton1.isSelected());
        if(this.jToggleButton1.isSelected())
            cl.show(jPanelCard,"card_stat");
        else
            cl.show(jPanelCard,"card_opd");

    }//GEN-LAST:event_jToggleButton1ActionPerformed



    private void jButtonDoctorDischargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDoctorDischargeActionPerformed


        Visit visit = new Visit();
        if(theHD.showDialogDischarge(visit))
         {
            int[] select = jTableQueueStat.getSelectedRows();
            theHC.theVisitControl.dischargeDoctor(visit,vQueueStat,select);
         }

    }//GEN-LAST:event_jButtonDoctorDischargeActionPerformed



    private void dateComboBoxStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxStartActionPerformed


//        dateComboBoxEnd.setText(DateUtil.convertFieldDate(dateComboBoxStart.getText()));
        //this.refreshButton();

    }//GEN-LAST:event_dateComboBoxStartActionPerformed



    private void jCheckBoxSearchByDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSearchByDateActionPerformed


        dateComboBoxEnd.setEnabled(jCheckBoxSearchByDate.isSelected());
        dateComboBoxStart.setEnabled(jCheckBoxSearchByDate.isSelected());
        this.refreshButton();

    }//GEN-LAST:event_jCheckBoxSearchByDateActionPerformed



    private void jToggleButtonSoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonSoundActionPerformed


        //Audio.getAudio("config/sound/new_patient.wav").play();
        if(jToggleButtonSound.isSelected())
            jToggleButtonSound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/play_sound.gif")));
        else
            jToggleButtonSound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/play_sound_off.gif")));
        theHC.theSystemControl.setSoundEnabled(jToggleButtonSound.isSelected());

    }//GEN-LAST:event_jToggleButtonSoundActionPerformed


    /**
     * ใช้ในตรวจสอบว่าเป็น จุดบริการที่มีแพทย์หรือไม่
     *@param ServicePoint
     *@author Pongtorn(Henbe)
     *@date 20/03/49,18:42
     */
    protected void chooseServicePoint(ServicePoint sp)
    {
        jComboBoxDoctor.setVisible(false);
        jLabelDoctor.setVisible(false);
        if(sp==null)
        {
            return;
        }
        ComboboxModel.setCodeComboBox(jComboBoxSpoint,sp.getObjectId());
        if(!sp.service_type_id.equals(ServiceType.DIAG))
        {
            return;
        }
        Vector v = theHC.theLookupControl.listDoctorSP(sp.getObjectId());
        if(v!=null)
        {
            jComboBoxDoctor.setVisible(true);
            jLabelDoctor.setVisible(true);
            ComboboxModel.initComboBox(jComboBoxDoctor,v);
            jComboBoxDoctor.insertItemAt(HosObject.getEmployeeUD(),0);
            jComboBoxDoctor.setSelectedIndex(0);
            if(authen.equals(Authentication.DOCTOR))
            {
                Gutil.setGuiData(jComboBoxDoctor,theHO.theEmployee.getObjectId());
            }
        }
    }


    private void jComboBoxSpointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSpointActionPerformed


        ServicePoint sp = (ServicePoint)jComboBoxSpoint.getSelectedItem();
        chooseServicePoint(sp);
        refreshButton();

    }//GEN-LAST:event_jComboBoxSpointActionPerformed



    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed


        refreshButton();

    }//GEN-LAST:event_jButton1ActionPerformed



    private void jTableQueueDispenseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableQueueDispenseKeyReleased


        if(evt.getKeyCode()==KeyEvent.VK_UP ||evt.getKeyCode()==KeyEvent.VK_DOWN)
            jTableQueueDispenseMouseReleased(null);

    }//GEN-LAST:event_jTableQueueDispenseKeyReleased



    private void jComboBoxDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDoctorActionPerformed


        refreshButton();

    }//GEN-LAST:event_jComboBoxDoctorActionPerformed



       private void jRadioButtonIPDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonIPDActionPerformed


        refreshButton();

    }//GEN-LAST:event_jRadioButtonIPDActionPerformed



    private void jRadioButtonOPDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonOPDActionPerformed


        refreshButton();

    }//GEN-LAST:event_jRadioButtonOPDActionPerformed



    private void jRadioButtonAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAllActionPerformed


        refreshButton();

    }//GEN-LAST:event_jRadioButtonAllActionPerformed



    private void jTableQueueDispenseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableQueueDispenseMouseReleased


        this.jTableQueueOpd.clearSelection();
        int[] rows = jTableQueueDispense.getSelectedRows();
        int row = -1;
        if(rows.length==1)
            row = rows[0];

        if(row == -1) return;
        jTableQueueDispense.setEnabled(false);
        //คิวรอจ่ายยา
        if((theQueueDispense2V != null && theQueueDispense2V.size() > 0)
            && theQueueDispense2V.get(row) instanceof QueueDispense2){
            QueueDispense2 spd = (QueueDispense2)theQueueDispense2V.get(row);
            theHC.theVisitControl.readVisitPatientByVid(spd.visit_id);
        }
        //คิวค้างผลแลบ
        if((vListRemain != null && vListRemain.size() > 0)
            && vListRemain.get(row) instanceof ListTransfer )
        {
            //amp:09/03/2549 เพราะถ้าผู้ป่วยมีแลปปกปิด ต้องซ่อนชื่อ จึงต้องมีตัวตรวจสอบ
            ListTransfer t = (ListTransfer)vListRemain.get(row);
            theHC.theVisitControl.readVisitPatientByVid(t.visit_id,"1",t);
        }
        jTableQueueDispense.setEnabled(true);

    }//GEN-LAST:event_jTableQueueDispenseMouseReleased



    private void jTableQueueOpdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableQueueOpdKeyReleased


        if(evt.getKeyCode()==KeyEvent.VK_UP || evt.getKeyCode()==KeyEvent.VK_DOWN)
            jTableQueueOpdMouseReleased(null);
        if(evt.getKeyCode()==KeyEvent.VK_DELETE)
            if(theHO.theEmployee.authentication_id.equals(Authentication.LAB))
                theHC.theVisitControl.deleteQueueLab(true);
            else
                theHC.theVisitControl.deleteQueueTransfer(vListTransfer,jTableQueueOpd.getSelectedRows());
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            int row = jTableQueueOpd.getSelectedRow();
            Integer queue_value = (Integer)jTableQueueOpd.getValueAt(row,8);
            ListTransfer t = (ListTransfer)vListTransfer.get(row);
            theHC.theVisitControl.saveQueueValue(t,queue_value.toString());
        }

    }//GEN-LAST:event_jTableQueueOpdKeyReleased


/*
 //กำหนดให้แสดง default panel ตรวจสอบสิทธิของผู้ใช้งาน
 */

    private void jTableQueueOpdMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableQueueOpdMouseReleased


        jTableQueueDispense.clearSelection();
        //กรณีผู้ใช้ทั่วไปจะเลือกผู้ป่วยได้แค่ทีละ 1 คนเท่านั้น
        int row = jTableQueueOpd.getSelectedRow();
        if(row == -1)
            return;

        try{
            ListTransfer t = (ListTransfer)vListTransfer.get(row);
            if(this.jToggleButtonQueue.isSelected()){
                theUS.setStatus("กรุณา double click เลขคิวที่ต้องการแก้ไข หรือกดปุ่ม Q เพื่อกลับไปเลือกคนไข้",UpdateStatus.WARNING);
                return;
            }
            theHC.theVisitControl.readVisitPatientByVid(t.visit_id,"0",t);
        }
        catch(Exception e){
            String[] t = (String[])vQueueStat.get(row);
            theHC.theVisitControl.readVisitPatientByVid(t[t.length-1],"0",null);
        }

    }//GEN-LAST:event_jTableQueueOpdMouseReleased


/*        //ตรวจสอบว่าจุดบริการนี้เป็นแพทย์หรือเปล่าถ้าใช้ เมื่อเลือกจุดบริารเป็นห้องตรวจ
        //ให้ default เป็นชื่อแพทย์น login เลย  date 1/03/48 build 8
 **/
    protected void refreshButton()
    {
        Constant.println("PanelListVisitByTransfer protected void refreshButton()");
        setCursor(Constant.CUR_DEFAULT);
        String d = "";
        if(jComboBoxDoctor.isEnabled() && jComboBoxDoctor.isVisible())
            d = Gutil.getGuiData(jComboBoxDoctor);

        String s = Gutil.getGuiData(jComboBoxSpoint);
        String c = "";
        if(jRadioButtonOPD.isSelected())            c = "0";//opd
        if(jRadioButtonIPD.isSelected())            c = "1";//ipd
        ////////////////////////////////////////////////////////////
        int old_qty = 0;
        if(vListTransfer!=null)
            old_qty = vListTransfer.size();
        ////////////////////////////////////////////////////////////
        String date_from = dateComboBoxStart.getText();
        String date_to = dateComboBoxEnd.getText();
        if(!jCheckBoxSearchByDate.isSelected()) {
            date_from = "";
            date_to = "";
        }
        if(this.jToggleButton1.isSelected()){
            Vector vt = theHC.theVisitControl.listQueueICD(c,date_from,date_to);
            setQueueStatV(vt);
        }
        else{
            if(authen.equals(Authentication.XRAY)){
                vListTransfer = theHC.theVisitControl.listQueueXray(c);
                setListTransferV(vListTransfer);
            }
            else if(authen.equals(Authentication.LAB))
            {
                vListTransfer = theHC.theVisitControl.listQueueLab(c);
                setListTransferLab(vListTransfer);
                vListRemain = theHC.theVisitControl.listRemainQueueLab(c);
                setListTransferLabRemain(vListRemain);
            }
            else{
                //หารายชื่อผู้ป่วยที่อยู่ในจุดบริการที่กำหนด
                vListTransfer = theHC.theVisitControl.listTransferByServicePoint(s,d,c);
                setListTransferV(vListTransfer);
            }
            if(authen.equals(Authentication.PHARM)
                || authen.equals(Authentication.ONE)
                || authen.equals(Authentication.HEALTH)) {
                // Drug and one stop service
                theQueueDispense2V = theHC.theVisitControl.listVisitInQueueDispense2(c);
                setQueueDispense2V(theQueueDispense2V);
            }
        }
        if(vListTransfer!=null)
        {
//            Constant.println("________________________ if(vListTransfer!=null)");
            int new_qty = vListTransfer.size();
            if(new_qty > old_qty && theHC.theSystemControl.isSoundEnabled())
            {
//                Constant.println("________________________PlaySound");
                Audio.getAudio("config/sound/new_patient.wav").play();
            }
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupPatient;
    private com.hospital_os.utility.DateComboBox dateComboBoxEnd;
    private com.hospital_os.utility.DateComboBox dateComboBoxStart;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonDispense;
    private javax.swing.JButton jButtonDoctorDischarge;
    private javax.swing.JCheckBox jCheckBoxSearchByDate;
    private javax.swing.JComboBox jComboBoxDoctor;
    private javax.swing.JComboBox jComboBoxSpoint;
    private javax.swing.JLabel jLabelDoctor;
    private javax.swing.JLabel jLabelPname;
    private javax.swing.JLabel jLabelTo;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelCard;
    private javax.swing.JPanel jPanelCenter;
    private javax.swing.JPanel jPanelQDispense;
    private javax.swing.JPanel jPanelRadioPttype;
    private javax.swing.JPanel jPanelStatSearch;
    private javax.swing.JPanel jPanelTop;
    private javax.swing.JPanel jPanelTop1;
    private javax.swing.JRadioButton jRadioButtonAll;
    private javax.swing.JRadioButton jRadioButtonIPD;
    private javax.swing.JRadioButton jRadioButtonOPD;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private com.hosv3.gui.component.HJTableSort jTableQueueDispense;
    private com.hosv3.gui.component.HJTableSort jTableQueueOpd;
    private com.hosv3.gui.component.HJTableSort jTableQueueStat;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButtonQueue;
    private javax.swing.JToggleButton jToggleButtonSound;
    // End of variables declaration//GEN-END:variables




    public void gc()
    {
    }

    //สำหรับตารางรอจ่ายยา
    private void setQueueDispense2V(Vector vc)
    {
        theQueueDispense2V = vc;
        countrowTransaction = 0;
        Vector vPrefix = theHC.theLookupControl.listPrefix();
        TaBleModel tm ;
        if(vc != null)
        {
            tm= new TaBleModel(col_dispense,vc.size());
            for(int i=0,size=vc.size();i<size;i++)
            {
                countrowTransaction = countrowTransaction +1;
                QueueDispense2 pd = (QueueDispense2)vc.get(i);
                if(pd != null)
                {
                   String name = theHC.theLookupControl.getPatientName(pd);

                   tm.setValueAt(new Integer(countrowTransaction),i,0);

                    tm.setValueAt(pd.hn,i,1);
                    tm.setValueAt(pd.vn,i,2);
                    tm.setValueAt(name,i,3);
                    tm.setValueAt(pd.service_point_name,i,4);
                    try{
                        //แบบเก่าที่ยังไม่ได้เรียงตามรูปแบบใหม่
                        //tm.setValueAt(DateUtil.getDateToString(DateUtil
                          //      .getDateFromText(pd.assign_time),true),i,5);
                        tm.setValueAt(DateUtil.getDateFromText(pd.assign_time),i,5);
                    }
                    catch(Exception ex){
                        tm.setValueAt(pd.assign_time,i,5);
                    }
                    tm.setValueAt(new Integer(pd.number_order),i,6);
                }
            }
        }
        else  tm= new TaBleModel(col_dispense,0);
        setQuantityInQueue("จำนวนผู้ป่วยรอรับยา",jPanelQDispense,vc);
        //String[] col_dispense = {"HN","VN","ชื่อ-สกุล","จุดบริการสุดท้าย","เวลาเริ่มรอจ่ายยา","จำนวน"};
        jTableQueueDispense.setModel(tm);
        jTableQueueDispense.getColumnModel().getColumn(0).setPreferredWidth(60); // ลำดับ
        jTableQueueDispense.getColumnModel().getColumn(0).setCellRenderer(TableRenderer.getRendererRight());
        jTableQueueDispense.getColumnModel().getColumn(1).setPreferredWidth(60); // hn
        if(theHC.theLO.theOption.unused_pattern.equals("0"))
            jTableQueueDispense.getColumnModel().getColumn(1).setCellRenderer(hnRender);
        jTableQueueDispense.getColumnModel().getColumn(2).setPreferredWidth(80); // vn
        if(theHC.theLO.theOption.unused_pattern.equals("0"))
            jTableQueueDispense.getColumnModel().getColumn(2).setCellRenderer(vnRender);
        jTableQueueDispense.getColumnModel().getColumn(3).setPreferredWidth(300); // name
        jTableQueueDispense.getColumnModel().getColumn(4).setPreferredWidth(120); // service point
        jTableQueueDispense.getColumnModel().getColumn(4).setCellRenderer(TableRenderer.getRendererCenter());
        jTableQueueDispense.getColumnModel().getColumn(5).setPreferredWidth(160); // wait time
        jTableQueueDispense.getColumnModel().getColumn(5).setCellRenderer(dateRender);
        jTableQueueDispense.getColumnModel().getColumn(6).setPreferredWidth(30); // จำนวน
        jTableQueueDispense.getColumnModel().getColumn(6).setCellRenderer(TableRenderer.getRendererRight());

    }

    private void setTableListTransfer(boolean show)
    {
        jTableQueueOpd.getColumnModel().getColumn(0).setPreferredWidth(50); // ลำดับ
        jTableQueueOpd.getColumnModel().getColumn(0).setCellRenderer(TableRenderer.getRendererRight());
        jTableQueueOpd.getColumnModel().getColumn(1).setPreferredWidth(50); // ล็อก
        jTableQueueOpd.getColumnModel().getColumn(1).setCellRenderer(cellRendererTranfer);
        jTableQueueOpd.getColumnModel().getColumn(2).setPreferredWidth(75); // แพ้ยา
        jTableQueueOpd.getColumnModel().getColumn(2).setCellRenderer(allergyRender);
        jTableQueueOpd.getColumnModel().getColumn(3).setPreferredWidth(115); // HN 1
        if(theHC.theLO.theOption.unused_pattern.equals("0"))
            jTableQueueOpd.getColumnModel().getColumn(3).setCellRenderer(hnRender);
        jTableQueueOpd.getColumnModel().getColumn(4).setPreferredWidth(110); // VN 2
        if(theHC.theLO.theOption.unused_pattern.equals("0"))
            jTableQueueOpd.getColumnModel().getColumn(4).setCellRenderer(vnRender);
        jTableQueueOpd.getColumnModel().getColumn(5).setPreferredWidth(200); // ชื่อ 3
        jTableQueueOpd.getColumnModel().getColumn(6).setPreferredWidth(180); // เวลา 5
        jTableQueueOpd.getColumnModel().getColumn(6).setCellRenderer(dateRender);
        //show ใช้กับกลุ่มผู้ใช้ทั่วไปที่ไม่ใช่ lab หรือ xray
        if(show) {
            jTableQueueOpd.getColumnModel().getColumn(7).setPreferredWidth(150); // จุดบริการ 6
            jTableQueueOpd.getColumnModel().getColumn(7).setCellRenderer(TableRenderer.getRendererCenter());
            //used_queue ใช้กับ option ที่บอกว่าใช้หรือไม่ใช้ คิวผู้ป่วย
            if(used_queue)
            {
                jTableQueueOpd.getColumnModel().getColumn(8).setPreferredWidth(50); // เลขคิว 7
                jTableQueueOpd.getColumnModel().getColumn(8).setCellRenderer(TableRenderer.getRendererRight());
//                jTableQueueOpd.getColumnModel().getColumn(9).setPreferredWidth(80); // คิว 8
                jTableQueueOpd.getColumnModel().getColumn(9).setPreferredWidth(34); // คิว 8
                jTableQueueOpd.getColumnModel().getColumn(9).setCellRenderer(theCellRendererColor); //8
                jTableQueueOpd.getColumnModel().getColumn(10).setCellRenderer(labRender);
                jTableQueueOpd.getColumnModel().getColumn(10).setPreferredWidth(34); // คิว 8
            }
            else{
                jTableQueueOpd.getColumnModel().getColumn(8).setCellRenderer(labRender);
            }
        }
        else
        {
            jTableQueueOpd.getColumnModel().getColumn(7).setCellRenderer(labRender);
            if(theHO.theEmployee.authentication_id.equals(Authentication.XRAY))
            {
                jTableQueueOpd.getColumnModel().getColumn(8).setPreferredWidth(0);
                jTableQueueOpd.getColumnModel().getColumn(8).setMinWidth(0);
                jTableQueueOpd.getColumnModel().getColumn(8).setMaxWidth(0);
                jTableQueueOpd.getColumnModel().getColumn(8).setWidth(0);
            }
        }
    }

    /**สำหรับตารางค้างผลแลป*/
    private void setListTransferLabRemain(Vector vc)
    {
        Vector vPrefix = theHC.theLookupControl.listPrefix();
        if(vc==null){
            vc = new Vector();
        }
        countrowTransaction = 0;
        TaBleModel tm = new TaBleModel(col_lab,vc.size());
        for(int i=0;i<vc.size() && vc!=null;i++)
        {
            countrowTransaction = countrowTransaction +1;
           ListTransfer listTransfer = (ListTransfer)vc.get(i);
           if("".equals(listTransfer.specimen_code)) //amp:06/03/2549 แลปไม่ปกปิด
           {
               String name = theHC.theLookupControl.getPatientName(listTransfer);

               tm.setValueAt(new Integer(countrowTransaction),i,0);
               tm.setValueAt(listTransfer.locking,i,1);
               tm.setValueAt(listTransfer.patient_allergy, i,2);
               tm.setValueAt(listTransfer.hn,i,3);
               tm.setValueAt(listTransfer.vn,i,4);
               tm.setValueAt(name,i,5);
               tm.setValueAt(DateUtil.getDateFromText(listTransfer.assign_time),i,6);
               tm.setValueAt(listTransfer.labstatus, i, 7);
               tm.setValueAt("",i,8);
           }
           else
           {//แลปปกปิด
               tm.setValueAt(new Integer(countrowTransaction),i,0);
//               tm.setValueAt("***",i,1);
//               tm.setValueAt("*****", i,2);
               tm.setValueAt("********",i,3);
               tm.setValueAt("********",i,4);
               tm.setValueAt("*** ***** *****",i,5);
//               tm.setValueAt("*****",i,6);
               tm.setValueAt(listTransfer.labstatus, i, 7);
               tm.setValueAt(listTransfer.specimen_code,i,8);
           }
        }
        //String[] col_dispense = {"HN","VN","ชื่อ-สกุล","จุดบริการสุดท้าย","เวลาเริ่มรอจ่ายยา","จำนวน"};
        jTableQueueDispense.setModel(tm);
        setQuantityInQueue("ค้างผลแลป",jPanelQDispense,vc);
        jTableQueueDispense.getColumnModel().getColumn(0).setPreferredWidth(50); // ลำดับ
        jTableQueueDispense.getColumnModel().getColumn(0).setCellRenderer(TableRenderer.getRendererRight());
        jTableQueueDispense.getColumnModel().getColumn(1).setPreferredWidth(50); // ล็อก
        jTableQueueDispense.getColumnModel().getColumn(1).setCellRenderer(cellRendererTranfer);
        jTableQueueDispense.getColumnModel().getColumn(2).setPreferredWidth(80); // แพ้ยา
        jTableQueueDispense.getColumnModel().getColumn(2).setCellRenderer(allergyRender);
        jTableQueueDispense.getColumnModel().getColumn(3).setPreferredWidth(90); // HN 1
        if(theHC.theLO.theOption.unused_pattern.equals("0"))
            jTableQueueDispense.getColumnModel().getColumn(3).setCellRenderer(hnRender);
        jTableQueueDispense.getColumnModel().getColumn(4).setPreferredWidth(110); // VN 2
        if(theHC.theLO.theOption.unused_pattern.equals("0"))
            jTableQueueDispense.getColumnModel().getColumn(4).setCellRenderer(vnRender);
        jTableQueueDispense.getColumnModel().getColumn(5).setPreferredWidth(200); // ชื่อ 3
        jTableQueueDispense.getColumnModel().getColumn(6).setPreferredWidth(180); // เวลา 5
        jTableQueueDispense.getColumnModel().getColumn(6).setCellRenderer(dateRender);
        jTableQueueDispense.getColumnModel().getColumn(7).setCellRenderer(labRender);
    }
        //xxxxxx
    private void setQueueStatV(Vector vqueue)
    {
        vQueueStat = vqueue;
        TaBleModel tm = new TaBleModel(col_sp_dx,vqueue.size());

        for(int i=0 ;i<vqueue.size(); i++)//row
        {
            String[] str = (String[])vqueue.get(i);
            for(int j=0;j<str.length-1;j++)//column
            {
                tm.setValueAt(str[j],i,j);
                if(j==6)
                    tm.setValueAt(DateUtil.getDateFromText(str[j]),i,j);
                else if(j==9)
                {
                    tm.setValueAt(str[j]+ "|" + str[j+1],i,j);
                    //Constant.println(str[j]+ "|" + str[j+1]);
                    j++;
                }
            }
        }
        jTableQueueStat.setModel(tm);
        jTableQueueStat.getColumnModel().getColumn(0).setPreferredWidth(30); // ลำดับ
        jTableQueueStat.getColumnModel().getColumn(0).setCellRenderer(TableRenderer.getRendererRight());
        jTableQueueStat.getColumnModel().getColumn(1).setPreferredWidth(30); // ล็อก
        jTableQueueStat.getColumnModel().getColumn(1).setCellRenderer(cellRendererTranfer);
        jTableQueueStat.getColumnModel().getColumn(2).setPreferredWidth(50); // แพ้ยา
        jTableQueueStat.getColumnModel().getColumn(2).setCellRenderer(allergyRender);
        jTableQueueStat.getColumnModel().getColumn(3).setPreferredWidth(90); // HN 1
        if(theHC.theLO.theOption.unused_pattern.equals("0"))
            jTableQueueStat.getColumnModel().getColumn(3).setCellRenderer(hnRender);
        jTableQueueStat.getColumnModel().getColumn(4).setPreferredWidth(90); // VN 2
        if(theHC.theLO.theOption.unused_pattern.equals("0"))
            jTableQueueStat.getColumnModel().getColumn(4).setCellRenderer(vnRender);
        jTableQueueStat.getColumnModel().getColumn(5).setPreferredWidth(180); // ชื่อ 3
        jTableQueueStat.getColumnModel().getColumn(6).setPreferredWidth(180); // เวลา 5
        jTableQueueStat.getColumnModel().getColumn(6).setCellRenderer(dateRender);
        jTableQueueStat.getColumnModel().getColumn(7).setPreferredWidth(180); // ชื่อ 3
        jTableQueueStat.getColumnModel().getColumn(8).setPreferredWidth(30); // ล็อก
        jTableQueueStat.getColumnModel().getColumn(8).setCellRenderer(labRender);
        jTableQueueStat.getColumnModel().getColumn(9).setPreferredWidth(100); // คิว
        jTableQueueStat.getColumnModel().getColumn(9).setCellRenderer(theCellRendererColor); //8
        countpatient = vQueueStat.size();

        if(vQueueStat.isEmpty())
        {
             jScrollPane2.setBorder(new javax.swing.border.TitledBorder(null
                , Constant.getTextBundle("ไม่มีผู้ป่วยในคิว")
                , javax.swing.border.TitledBorder.CENTER
                , javax.swing.border.TitledBorder.TOP));
        }
        else
        {
            jScrollPane2.setBorder(new javax.swing.border.TitledBorder(null
                , Constant.getTextBundle("จำนวนผู้ป่วยรอลงรหัสโรค")
                    + "  "+ countpatient +"  "+ Constant.getTextBundle("คน")
                , javax.swing.border.TitledBorder.CENTER
                , javax.swing.border.TitledBorder.TOP));
        }
    }

    //xxxxxx
    private void setListTransferV(Vector vc)
    {
        Vector vPrefix = theHC.theLookupControl.listPrefix();
        countrowTransaction = 0;
        if(vc==null) vc = new Vector();
        TaBleModel tm=null;

        if(used_queue )//แสดงจุดบริการ และแสดงคิว
            tm = new TaBleModel(col_sp_queue,vc.size());
        else    //แสดงจุดบริการ แต่ไม่แสดงคิว
            tm = new TaBleModel(col_sp,vc.size());

        boolean checkvalue = true;
        for(int i=0;i<vc.size() && vc!=null;i++)
        {
            countrowTransaction = countrowTransaction +1;
            ListTransfer listTransfer = (ListTransfer)vc.get(i);
           if(listTransfer.queue == null)
           {
               listTransfer.queue = "0";
               listTransfer.color = "r=255,g=255,b=255";
               listTransfer.description = Constant.getTextBundle("คิวอื่นๆ");
           }
           String name = theHC.theLookupControl.getPatientName(listTransfer);

           tm.setValueAt(new Integer(countrowTransaction),i,0);
           tm.setValueAt(listTransfer.locking,i,1);
           tm.setValueAt(listTransfer.patient_allergy, i,2);
           tm.setValueAt(listTransfer.hn,i,3);
           tm.setValueAt(listTransfer.vn,i,4);
           tm.setValueAt(name,i,5);
           tm.setValueAt(DateUtil.getDateFromText(listTransfer.assign_time),i,6);
           tm.setValueAt(listTransfer.name,i,7);
           if(!used_queue)
           {
               if(listTransfer.labstatus==null)
                   listTransfer.labstatus="0";
               tm.setValueAt(listTransfer.labstatus,i,8);
           }
           else
           {
               if(!listTransfer.queue.equals(""))
                    tm.setValueAt(new Integer(listTransfer.queue),i,8);
               tm.setValueAt(listTransfer.color + "|"
                       + listTransfer.description,i, 9);
               if(listTransfer.labstatus==null) listTransfer.labstatus="0";
               tm.setValueAt(listTransfer.labstatus,i,10);
               tm.setEditingCol(8);
           }
        }
        if(checkvalue)
            countpatient = vc.size();

        jTableQueueOpd.setModel(tm);
        setTableListTransfer(true);
        setQuantityInQueue("จำนวนผู้ป่วยในจุดบริการ",jPanelCenter,vc);
    }

    /**
     *@Author: amp
     *@date : 06/03/2549
     *@see: แสดงคิวแลป
     */
    private void setListTransferLab(Vector vc)
    {
        TaBleModel tm=null;
        countrowTransaction = 0;
        if(vc == null || vc.size() == 0)
        {
            tm = new TaBleModel(col_lab,0);
        }
        else
        {
            tm = new TaBleModel(col_lab,vc.size());
            boolean checkvalue = true;
            for(int i=0;i<vc.size() && vc!=null;i++)
            {   countrowTransaction = countrowTransaction +1;
               ListTransfer listTransfer = (ListTransfer)vc.get(i);
               if("".equals(listTransfer.specimen_code))
               {
                   String name = theHC.theLookupControl.getPatientName(listTransfer);

                   tm.setValueAt(new Integer(countrowTransaction),i,0);
                   tm.setValueAt(listTransfer.locking,i,1);
                   tm.setValueAt(listTransfer.patient_allergy, i,2);
                   tm.setValueAt(listTransfer.hn,i,3);
                   tm.setValueAt(listTransfer.vn,i,4);
                   tm.setValueAt(name,i,5);
                   tm.setValueAt(DateUtil.getDateFromText(listTransfer.assign_time),i,6);
                   tm.setValueAt(listTransfer.labstatus, i, 7);
                   tm.setValueAt("",i,8);
               }
               else
               {
                   tm.setValueAt(new Integer(countrowTransaction),i,0);
//                   tm.setValueAt("***",i,1);
//                   tm.setValueAt("*****", i,2);
                   tm.setValueAt("********",i,3);
                   tm.setValueAt("********",i,4);
                   tm.setValueAt("*** ***** *****",i,5);
//                   tm.setValueAt("*****",i,6);
                   tm.setValueAt(listTransfer.labstatus, i, 7);
                   tm.setValueAt(listTransfer.specimen_code,i,8);
               }
            }
            if(checkvalue)
                countpatient = vc.size();
        }
        jTableQueueOpd.setModel(tm);
        setTableListTransfer(false);
        setQuantityInQueue("จำนวนผู้ป่วยรอลงผลแลป",jPanelCenter,vc);
    }

    private void setQuantityInQueue(String queue_name,JPanel jp,Vector vc)
    {
        int countpatient = 0;
        if(vc!=null)
            countpatient = vc.size();
        if(countpatient == 0)
        {
             jp.setBorder(new javax.swing.border.TitledBorder(null
                , Constant.getTextBundle("ไม่มีผู้ป่วยในคิว")
                , javax.swing.border.TitledBorder.CENTER
                , javax.swing.border.TitledBorder.TOP));

        }
        else
        {
            jp.setBorder(new javax.swing.border.TitledBorder(null
                , Constant.getTextBundle(queue_name)
                    + "  "+ countpatient +"  "+ Constant.getTextBundle("คน")
                , javax.swing.border.TitledBorder.CENTER
                , javax.swing.border.TitledBorder.TOP));
        }
    }
    public void notifyAdmitVisit(String str, int status) {
        refreshButton();
    }

    public void notifyReadVisit(String str, int status) {
        if(!theHO.theEmployee.authentication_id.equals(Authentication.STAT))
            refreshButton();
    }

    public void notifyUnlockVisit(String str, int status) {
        if(!theHO.theEmployee.authentication_id.equals(Authentication.STAT))
            refreshButton();
    }

    public void notifyVisitPatient(String str, int status) {
//        Constant.println("PanelListTransfer__notifyVisitPatient");
        refreshButton();
    }

    public void notifyDropVisit(String str, int status) {
        Constant.println("PanelListTransfer__notifyDropVisit");
        refreshButton();
    }

    public void notifySendVisit(String str, int status) {
        Constant.println("PanelListTransfer__notifySendVisit");
        refreshButton();
    }

    public void notifyManageDrugAllergy(String str, int status) {
        Constant.println("PanelListTransfer__notifyManageDrugAllergy");
        refreshButton();
    }

    public void notifyDischargeDoctor(String str, int status) {
        Constant.println("PanelListTransfer__notifyDischargeDoctor");
        if(theHO.theEmployee.authentication_id.equals(Authentication.STAT))
            refreshButton();
    }

    public void notifyDischargeFinancial(String str, int status) {
        Constant.println("PanelListTransfer__notifyDischargeFinancial");
        refreshButton();
    }

    public void notifyReverseFinancial(String str, int status) {
        Constant.println("PanelListTransfer__notifyReverseFinancial");
        refreshButton();
    }

    public void notifyReverseDoctor(String str, int status) {
        Constant.println("PanelListTransfer__notifyReverseDoctor");
        refreshButton();
    }

    public void notifyCheckDoctorTreament(String str, int status) {
    }

    public void notifyObservVisit(String str, int status) {
    }

    public void notifyDeleteVisitPayment(String str, int status) {
    }

    public void notifyDeletePatientPayment(String str, int status) {
    }

    public void notifyManageAppointment(String str, int status) {
    }

    public void notifySavePatientPayment(String str, int status) {
    }

    public void notifyCancelOrderItem(String str, int status) {
    }

    public void notifyCheckAutoOrder(String str, int status) {
    }

    public void notifyContinueOrderItem(String str, int status) {
    }

    public void notifyDispenseOrderItem(String str, int status) {
        Constant.println("PanelListTransfer__notifyDispenseOrderItem");
        refreshButton();
    }

    public void notifyDoctorOffDrug(String DoctorId, int status) {
    }

    public void notifyExecuteOrderItem(String str, int status) {
        Constant.println("PanelListTransfer__notifyExecuteOrderItem");
        refreshButton();
    }

    public void notifyReferOutLab(String msg, int status) {
    }

    public void notifySaveOrderItem(String str, int status) {
    }

    public void notifySaveOrderItemFromDialogOrder(Vector vOrderItem, Vector vOrderItemDrug, String msg) {
    }

    public void notifySaveOrderItemInLab(String str, int status) {
    }

    public void notifyReceiveReturnDrug(String str, int status) {
    }

    public void notifyVerifyOrderItem(String str, int status) {
        refreshButton();
    }

    public void notifyDeletePatient(String str, int status) {
    }

    public void notifyReadPatient(String str, int status) {
    }
    public void notifyReadFamily(String str, int status) {
        refreshButton();
    }
    public void notifySavePatient(String str, int status) {
    }

    public void notifyRemainDoctorDischarge(String str, int status) {
        Constant.println("PanelListTransfer__notifyRemainDoctorDischarge");
        refreshButton();
    }

    public void notifySendVisitBackWard(String str, int status) {
        Constant.println("PanelListTransfer__notifySendVisitBackWard");
        refreshButton();
    }

    public void notifySaveReturnDrug(String str, int status) {
    }

    public void notifyDeleteFilmXray(String str, int status) {
    }

    public void notifyDeleteLabOrder(String str, int status) {
    }

    public void notifyDeleteLabResult(String str, int status) {
    }

    public void notifyDeleteXrayPosition(String str, int status) {
    }

    public void notifyManagePatientLabReferIn(String str, int status) {
    }

    public void notifyReportResultLab(String str, int status) {
        refreshButton();
    }

    public void notifySaveFilmXray(String str, int status) {
    }

    public void notifySaveLabResult(String str, int status) {
        //refreshButton();
    }

    public void notifySaveResultXray(String str, int status) {
    }

    public void notifySaveXrayPosition(String str, int status) {
    }

    public void notifyXrayReportComplete(String str, int status) {
        refreshButton();
    }

    public void notifyDeleteResultXray(String str, int status) {
    }

    public void notifySaveOrderRequest(String str, int status) {
    }

    public void notifySaveAppointment(String str, int status) {
    }

    public void notifyAddLabReferOut(String str, int status) {
    }

    public void notifyAddLabReferIn(String str, int status) {
    }

    public void notifyReverseAdmit(String str, int status) {
        Constant.println("PanelListTransfer__notifyReverseAdmit");
        refreshButton();
        theHC.theVisitControl.readVisitPatientByVid(theHO.theVisit.getObjectId());
    }

    public void notifyResetPatient(String str, int status) {
        Constant.println("PanelListTransfer__notifyResetPatient");
        refreshButton();
    }

    public void notifySaveRemainLabResult(String str, int status) {
        Constant.println("PanelListTransfer__notifySaveRemainLabResult");
        refreshButton();
    }

    public void notifySendResultLab(String str, int status) {
        Constant.println("PanelListTransfer__notifySendResultLab");
        refreshButton();
    }

    public void notifyDeleteQueueLab(String str, int status) {
         Constant.println("PanelListTransfer__notifyDeleteQueueLab");
        refreshButton();
    }

    public void notifySaveBorrowFilmXray(String str, int status) {
    }

    public static void main(String[] argc){
        //Constant.println(.getInteger(,nm)"11"));
    }
}
