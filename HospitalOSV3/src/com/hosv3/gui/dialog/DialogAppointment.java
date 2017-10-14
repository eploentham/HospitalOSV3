/*
 * DialogAppointment.java
 *
 * Created on 7 ธันวาคม 2546, 14:14 น.
 * Modified on 21 เมษายน 2547, 13:00 น.
 */
package com.hosv3.gui.dialog;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.hosv3.control.*;
import com.hosv3.utility.*;
import com.hosv3.object.*;
import com.hosv3.subject.*;
import com.hosv3.usecase.transaction.*;
import com.hosv3.utility.connection.*;
import com.hosv3.control.lookup.*;
import com.hospital_os.object.*;
import com.hospital_os.object.specialQuery.*;
import com.hospital_os.utility.Gutil;
import com.hosv3.utility.Constant;
import com.hospital_os.utility.CellRendererHos;
import com.hospital_os.utility.TaBleModel;
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.ComboFix;
import com.hosv3.gui.component.CellRendererTooltip;
//import com.hosv3.gui.panel.transaction.HosDialog;
/**
 *
 * @author amp
 * @modifier amp
 */
public class DialogAppointment extends JFrame implements UpdateStatus
,ManagePrintResp,ManagePatientResp
{
    HosControl theHC;
    HosObject theHO;
    //HosDialog theHD;//amp:10/8/2549
    public boolean actionCommand = false;
    JFrame aMain;
    LookupControl theLookupControl;
    PatientControl thePatientControl;
    OrderControl theOrderControl;
    VisitControl theVisitControl;
    VisitSubject theVisitSubject;
    SetupControl theSetupControl;
    SystemControl theSystemControl;
    Appointment theAppointment = new Appointment();
    Vector vappointment;
    /**vector ของ จุดบริการ*/
    Vector sPoint;
    /**vector ของ สิทธิการรักษาประจำตัวผู้ป่วย*/
    Vector vPatientPayment;
    /**vector ของ สิทธิการรักษาแต่ละครั้ง*/
    Vector vPayment;
    /**vector ของ การนัดเป็นช่วง*/
    Vector vAppointment = new Vector();
    /**หัวตารางของ รายการ Item ที่ค้นหา*/
    String[] column = {/*"รหัส",*/"ชื่อ"};
    /**หัวตารางของ ดูรายการนัด*/
    private String[] collist = {"Hn","ชื่อ","วันที่","จุดบริการ","สถานะ"};
    CellRendererTooltip theCellRendererTooltip= new CellRendererTooltip(true);
    /**หัวตารางของการนัดหมาย*/
    /**เป็นคิวของ Visit*/
    private QueueVisit theQueueVisit;
    /**
     *showlist
     *true คือ ดูรายการนัด
     *false คือ การนัดหมาย
     */
    private boolean showlist;
    /**
     *flag true คือ ต้อง check เรื่องเวลา
     *     false คือ ไม่ต้อง check เรื่องเวลา
     */
    private boolean flag;
    /**
     *mod true คือ สามารถแก้ไขได้
     *    false คือ ไม่สามารถแก้ไขได้
     */
    private boolean mod;
    private boolean used_queue_visit;
    Vector vItem;
    Vector vAppointmentOrder;
    CellRendererHos dateRender = new CellRendererHos(CellRendererHos.DATE_TIME);
    CellRendererHos appointmentRender = new CellRendererHos(CellRendererHos.APPOINTMENT_STATUS);//amp:9/8/2549
    CellRendererHos hnRender = new CellRendererHos(CellRendererHos.HN);
    long firstClickTime = 0;//amp:9/8/2549
    DialogAppointmentTemplate theDialogAppointmentTemplate;
    private boolean input_mode=true;
    //public static boolean closeDialog = false;
    /** Creates new form DialogAppointment */
    public DialogAppointment(HosControl hc,UpdateStatus us,boolean b)
    {
        aMain = us.getJFrame();
        setIconImage(aMain.getIconImage());
        theLookupControl = hc.theLookupControl;
        thePatientControl = hc.thePatientControl;
        theVisitControl = hc.theVisitControl;
        theSetupControl = hc.theSetupControl;
        theSystemControl = hc.theSystemControl;
        theHO = hc.theHO;
        theHC = hc;
        showlist= b;
    hnRender = new CellRendererHos(CellRendererHos.HN,theLookupControl.getSequenceDataHN().pattern);
        hc.theHS.thePrintSubject.attachManagePrint(this);
        hc.theHS.thePatientSubject.attachManagePatient(this);
        initComponents();
        setComponent();
        setDialog();
        setShowlist(showlist);
        setLanguage("");
        this.jCheckBoxShowDatePeriodActionPerformed(null);
        this.jCheckBoxToDate.setSelected(false);
        this.jCheckBoxToDateActionPerformed(null);
        //amp:13/03/2549 เพื่อให้รายละเอียดมีตัวช่วยของอาการเบื้องต้น
        jTextAreaDescription.setControl(new com.hosv3.control.lookup.VitalTemplateLookup(theHC.theLookupControl));
        jTextAreaDescription.setJFrame(this);
        jTextFieldApType.setControl(new com.hosv3.control.lookup.VitalTemplateLookup(theHC.theLookupControl),this);
        theHC.theHS.theBalloonSubject.attachBalloon(jTextFieldApType);
        theHC.theHS.theBalloonSubject.attachBalloon(jTextAreaDescription);
        this.jComboBoxApType53.setControl(new R53AppApTypeLookup(theLookupControl.theConnectionInf), false);
        //amp:11/08/2549
        hosComboBoxStdAppointmentTemplate.setControl(new AppointmentTemplateLookup(hc.theLookupControl), false);
    }
       ///////////////////////////////////////////////////////////////////////////
    /**
     *ใช้ในการเซตภาษา
     */
    public void setLanguage(String msg)
    {        GuiLang.setLanguage(collist);
        GuiLang.setLanguage(column);
        GuiLang.setLanguage(jButton4);
        GuiLang.setLanguage(jButtonAutoVisit);
        GuiLang.setLanguage(jButtonPreviewAppointment);
        GuiLang.setLanguage(jButtonPrintAppointment);
        GuiLang.setLanguage(jButtonPrintListAppointment);
        GuiLang.setLanguage(jButtonPrintListAppointment1);
        GuiLang.setLanguage(jButtonSave);
        GuiLang.setLanguage(jButtonSearch);
        GuiLang.setLanguage(jCheckBoxAutoVisit);
        GuiLang.setLanguage(jCheckBoxSCurrPatient);
        GuiLang.setLanguage(jCheckBoxShowCancel);
        GuiLang.setLanguage(jCheckBoxShowDatePeriod);
        GuiLang.setLanguage(jCheckBoxToDate);
        GuiLang.setLanguage(jLabel1);
        GuiLang.setLanguage(jLabel10);
        GuiLang.setLanguage(jLabel11);
        GuiLang.setLanguage(jLabel12);
        GuiLang.setLanguage(jLabel13);
        GuiLang.setLanguage(jLabel14);
        GuiLang.setLanguage(jLabel15);
        GuiLang.setLanguage(jLabel18);
        GuiLang.setLanguage(jLabel19);
        GuiLang.setLanguage(jLabel2);
        GuiLang.setLanguage(jLabel2);
        GuiLang.setLanguage(jLabel20);
        GuiLang.setLanguage(jLabel21);
        GuiLang.setLanguage(jLabel22);
        GuiLang.setLanguage(jLabel23);
        GuiLang.setLanguage(jLabel24);
        GuiLang.setLanguage(jLabel25);
        GuiLang.setLanguage(jLabel3);
        GuiLang.setLanguage(jLabel4);
        GuiLang.setLanguage(jLabel5);
        GuiLang.setLanguage(jLabel6);
        GuiLang.setLanguage(jLabel7);
        GuiLang.setLanguage(jLabel8);
        GuiLang.setLanguage(jLabel8);
        GuiLang.setLanguage(jLabel9);
        GuiLang.setLanguage(jLabel16);
        GuiLang.setLanguage(jLabelHide1);
        GuiLang.setLanguage(jPanel10);
        GuiLang.setTextBundle(jPanel3);
        GuiLang.setTextBundle(jPanelHide7);
        jTextFieldCancel.setText(GuiLang.setLanguage(jTextFieldCancel.getText()));
      }
    ////////////////////////////////////////////////////////////////////////////
    /**
     *เป็น fn สลับ Panel ระหว่างดูรายการนัด กับ นัดหมาย
     *ข้อมูลเข้า:ไม่มี
     *ข้อมูลออก:ไม่มี
     */
    public void setEnabledAppointment(boolean b)
    {
        this.jCheckBoxAutoVisit.setEnabled(b);
        this.jComboBoxDoctor.setEnabled(b);
        this.jComboBoxServicePoint.setEnabled(b);
        this.jComboBoxStatus.setEnabled(b);
        this.jTextAreaDescription.setEnabled(b);
        jComboBoxApType53.setEnabled(b);
        //this.jTextFieldHN.setEnabled(b);
        //this.jTextFieldPatientName.setEnabled(b);
        this.dateComboBoxDateAppointment.setEnabled(b);
        this.timeTextFieldTimeAppointment.setEnabled(b);
        this.jButtonQueueVisit.setEnabled(b);
        this.jTextFieldApType.setEnabled(b);
        this.jCheckBoxToDate.setEnabled(b);
        this.dateComboBoxDateAppointment2.setEnabled(b);
        jTextFieldSearchOrder.setEnabled(b);
        jButtonAddOrder.setEnabled(b);
        jButtonDelOrder.setEnabled(b);
    }
    private void setShowlist(boolean showlist)
    {
        //panel will hide when hidden list
        jPanelHide0.setVisible(!showlist);
        jPanelHide2.setVisible(!showlist);
        jPanelHide5.setVisible(!showlist);
        jPanelHide6.setVisible(!showlist);
        jPanelHide7.setVisible(!showlist);
        jLabelHide1.setVisible(!showlist);
        jButtonPrintAppointment.setVisible(!showlist);
        jButtonPreviewAppointment.setVisible(!showlist);
        jCheckBoxToDate.setVisible(!showlist);
        jButtonDel.setVisible(!showlist);
        jButtonAdd.setVisible(!showlist);
        //component will show when show list
        jCheckBoxAutoVisit.setVisible(showlist);
        jButtonPrintListAppointment.setVisible(showlist);
        jButtonPrintListAppointment1.setVisible(showlist);
        jButtonAutoVisit.setVisible(showlist);
        jLabel18.setVisible(!showlist);
        jLabel19.setVisible(!showlist);
        jLabel20.setVisible(!showlist);
        jLabel21.setVisible(!showlist);
        jLabel22.setVisible(!showlist);
        jLabel23.setVisible(!showlist);
        jLabelCountAppointment.setVisible(!showlist);
        jLabelCountDoctor.setVisible(!showlist);
        jLabelCountSP.setVisible(!showlist);
        setEnabledAppointment(showlist);
        this.jComboBoxStatus.setEnabled(true);
    }
    //////////////////////////////////////////////////////////////////////////
    /**
     *dialog ที่ใช้ในการส่งข้อความเตื่อนผู้ใช้
     */
     public void setStatus(String str, int status)
     {
        ThreadStatus theTT = new ThreadStatus(this,this.jLabelStatus);
        theTT.start();
        //JOptionPane.showMessageDialog(this, str, "เตือน", JOptionPane.ERROR_MESSAGE);
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
     //////////////////////////////////////////////////////////////////////////
     /**
      *dialog ที่ใข้ในการให้ผู้ใข้ทำการยีนยันสิ่งต่าง
      */
    public boolean confirmBox(String str, int status)
    {
        int i = JOptionPane.showConfirmDialog(this,str,Constant.getTextBundle("เตือน")
                ,JOptionPane.YES_NO_OPTION);
        return (i==JOptionPane.YES_OPTION);
    }
    ////////////////////////////////////////////////////////////////////////////
    /**
     *init component
     *ทำการเซตค่าให้กับ component ต่างๆ
     *neung
     */
    private void setComponent()
    {
        Vector v = theLookupControl.listServicePoint();
        ServicePoint cf = new ServicePoint();
        cf.setObjectId("0");
        cf.name = Constant.getTextBundle("ทั้งหมด");
        sPoint = new Vector();
        sPoint.add(cf);
        for(int i=0,size=v.size();i<size;i++){
            sPoint.add(v.get(i));
        }
        ComboboxModel.initComboBox(jComboBoxSearchServicePoint, sPoint);
        ComboboxModel.initComboBox(jComboBoxServicePoint, theLookupControl.listServicePoint());
        ComboboxModel.initComboBox(jComboBoxDoctor, theLookupControl.listDoctor());
        ComboboxModel.initComboBox(jComboBoxStatus, theLookupControl.listAppointmentStatus());
        Vector vc = theLookupControl.listAppointmentStatus();
        ComboboxModel.initComboBox(jComboBoxSearchStatus,vc);
        ComboFix allstatus = new ComboFix();
        allstatus.code = "";
        allstatus.name = "แสดงทั้งหมด";
        jComboBoxSearchStatus.insertItemAt((Object)allstatus,0);
        jComboBoxSearchStatus.setSelectedIndex(0);
        timeTextFieldTimeAppointment.setText("");
        jTextFieldApType.setText("");
        jTextAreaDescription.setText("");
        dateComboBoxDateAppointment.setEditable(true);
        dateComboBoxDateFrom.setEditable(true);
        dateComboBoxDateTo.setEditable(true);
        jCheckBoxAutoVisit.setSelected(false);
        jLabel7.setText("");
    }
    public void setPatient(Patient p)
    {
        Patient thePatient = p;
        if(showlist)
        {
            jTextFieldHN.setText("");
            jTextFieldPatientName.setText("");
        }
        else
        {
            theAppointment.patient_id = thePatient.getObjectId();
            //amp
            jTextFieldHN.setText(theLookupControl.getRenderTextHN(thePatient.hn));
            jTextFieldPatientName.setText(thePatient.patient_name + " " + thePatient.patient_last_name);
        }
        if(thePatient==null){
            this.jCheckBoxSCurrPatient.setSelected(false);
            this.jCheckBoxShowDatePeriod.setSelected(true);
            this.jCheckBoxShowDatePeriodActionPerformed(null);
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



        buttonGroup1 = new javax.swing.ButtonGroup();

        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();

        jLabelStatus = new javax.swing.JLabel();

        jPanel3 = new javax.swing.JPanel();

        jScrollPane1 = new javax.swing.JScrollPane();

        jTable1 = new com.hosv3.gui.component.HJTableSort();

        jPanelSearch = new javax.swing.JPanel();

        jButtonSearch = new javax.swing.JButton();

        jComboBoxSearchServicePoint = new javax.swing.JComboBox();

        jPanel6 = new javax.swing.JPanel();

        jLabel8 = new javax.swing.JLabel();

        dateComboBoxDateFrom = new com.hospital_os.utility.DateComboBox();

        dateComboBoxDateTo = new com.hospital_os.utility.DateComboBox();

        jCheckBoxShowDatePeriod = new javax.swing.JCheckBox();

        jCheckBoxShowCancel = new javax.swing.JCheckBox();

        jCheckBoxSCurrPatient = new javax.swing.JCheckBox();

        jComboBoxSearchStatus = new javax.swing.JComboBox();

        jLabel15 = new javax.swing.JLabel();

        jPanel4 = new javax.swing.JPanel();

        jButtonPrintListAppointment = new javax.swing.JButton();

        jButtonAutoVisit = new javax.swing.JButton();

        jButtonPrintListAppointment1 = new javax.swing.JButton();

        jPanel10 = new javax.swing.JPanel();

        jPanel1 = new javax.swing.JPanel();

        jButtonAdd = new javax.swing.JButton();

        jButtonDel = new javax.swing.JButton();

        jButtonPreviewAppointment = new javax.swing.JButton();

        jButtonPrintAppointment = new javax.swing.JButton();

        jButtonSave = new javax.swing.JButton();

        jPanel5 = new javax.swing.JPanel();

        jCheckBoxAutoVisit = new javax.swing.JCheckBox();

        jButtonQueueVisit = new javax.swing.JButton();

        jLabel7 = new javax.swing.JLabel();

        jComboBoxStatus = new javax.swing.JComboBox();

        jLabelVN = new javax.swing.JLabel();

        jLabel13 = new javax.swing.JLabel();

        jLabel12 = new javax.swing.JLabel();

        jPanel2 = new javax.swing.JPanel();

        jLabel2 = new javax.swing.JLabel();

        jTextFieldHN = new javax.swing.JLabel();

        jLabel3 = new javax.swing.JLabel();

        jTextFieldPatientName = new javax.swing.JLabel();

        jTextFieldCancel = new javax.swing.JLabel();

        jLabel14 = new javax.swing.JLabel();

        jTextFieldPlan = new javax.swing.JLabel();

        jPanel22 = new javax.swing.JPanel();

        jPanelHide5 = new javax.swing.JPanel();

        jButtonDelOrder = new javax.swing.JButton();

        jButtonAddOrder = new javax.swing.JButton();

        jLabel1 = new javax.swing.JLabel();

        jTextFieldSearchOrder = new javax.swing.JTextField();

        jScrollPane5 = new javax.swing.JScrollPane();

        jTableItem = new com.hosv3.gui.component.HJTableSort();

        jScrollPane6 = new javax.swing.JScrollPane();

        jTableAppointmentOrder = new com.hosv3.gui.component.HJTableSort();

        jPanel24 = new javax.swing.JPanel();

        jComboBoxServicePoint = new javax.swing.JComboBox();

        jComboBoxDoctor = new javax.swing.JComboBox();

        jPanel25 = new javax.swing.JPanel();

        jLabelCountSP = new javax.swing.JLabel();

        jLabel18 = new javax.swing.JLabel();

        jLabel19 = new javax.swing.JLabel();

        jTextFieldApType = new com.hosv3.gui.component.BalloonTextField();

        jPanel26 = new javax.swing.JPanel();

        jLabelCountDoctor = new javax.swing.JLabel();

        jLabel22 = new javax.swing.JLabel();

        jLabel23 = new javax.swing.JLabel();

        jLabel11 = new javax.swing.JLabel();

        jLabel10 = new javax.swing.JLabel();

        jLabel9 = new javax.swing.JLabel();

        jLabel6 = new javax.swing.JLabel();

        jScrollPane2 = new javax.swing.JScrollPane();

        jTextAreaDescription = new com.hosv3.gui.component.BalloonTextArea();

        jComboBoxApType53 = new com.hosv3.gui.component.HosComboBox();

        jLabel16 = new javax.swing.JLabel();

        jLabel4 = new javax.swing.JLabel();

        jPanelHide0 = new javax.swing.JPanel();

        jButton4 = new javax.swing.JButton();

        hosComboBoxStdAppointmentTemplate = new com.hosv3.gui.component.HosComboBoxStd();

        jButtonDM = new javax.swing.JButton();

        jButton2 = new javax.swing.JButton();

        jButton3 = new javax.swing.JButton();

        jLabelHide1 = new javax.swing.JLabel();

        jPanelTime = new javax.swing.JPanel();

        jPanel27 = new javax.swing.JPanel();

        jLabelCountTime = new javax.swing.JLabel();

        jLabel24 = new javax.swing.JLabel();

        jLabel25 = new javax.swing.JLabel();

        timeTextFieldTimeAppointment = new com.hospital_os.utility.TimeTextField();

        jLabel5 = new javax.swing.JLabel();

        jPanelHide7 = new javax.swing.JPanel();

        jButtonTime700 = new javax.swing.JButton();

        jButtonTime730 = new javax.swing.JButton();

        jButtonTime800 = new javax.swing.JButton();

        jButtonTime830 = new javax.swing.JButton();

        jButtonTime900 = new javax.swing.JButton();

        jButtonTime1000 = new javax.swing.JButton();

        jButtonTime1100 = new javax.swing.JButton();

        jButtonTime1200 = new javax.swing.JButton();

        jButtonTime1300 = new javax.swing.JButton();

        jButtonTime1400 = new javax.swing.JButton();

        jPanelHide6 = new javax.swing.JPanel();

        dateComboBoxDateAppointment2 = new com.hospital_os.utility.DateComboBox();

        jButtonTo1Week = new javax.swing.JButton();

        jButtonTo2Week = new javax.swing.JButton();

        jButtonTo3Week = new javax.swing.JButton();

        jPanel8 = new javax.swing.JPanel();

        jPanelHide2 = new javax.swing.JPanel();

        jButtonAdd4Week = new javax.swing.JButton();

        jButton8Week = new javax.swing.JButton();

        jButton6Week = new javax.swing.JButton();

        jButton4Week = new javax.swing.JButton();

        jButton3Week = new javax.swing.JButton();

        jButton2Week = new javax.swing.JButton();

        jButton1Week = new javax.swing.JButton();

        jButtonAdd1Week = new javax.swing.JButton();

        dateComboBoxDateAppointment = new com.hospital_os.utility.DateComboBox();

        jPanel11 = new javax.swing.JPanel();

        jLabel21 = new javax.swing.JLabel();

        jLabel20 = new javax.swing.JLabel();

        jLabelCountAppointment = new javax.swing.JLabel();

        jCheckBoxToDate = new javax.swing.JCheckBox();



        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        setTitle("null");

        addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing(java.awt.event.WindowEvent evt) {

                formWindowClosing(evt);

            }

        });

        getContentPane().setLayout(new java.awt.GridBagLayout());



        jLabelStatus.setFont(defaultFont1);

        jLabelStatus.setText("Test");

        jLabelStatus.setMaximumSize(new java.awt.Dimension(27, 21));

        jLabelStatus.setMinimumSize(new java.awt.Dimension(27, 21));

        jLabelStatus.setOpaque(true);

        jLabelStatus.setPreferredSize(new java.awt.Dimension(27, 21));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.gridwidth = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        getContentPane().add(jLabelStatus, gridBagConstraints);



        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setLayout(new java.awt.GridBagLayout());



        jScrollPane1.setMinimumSize(new java.awt.Dimension(100, 22));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 80));



        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseReleased(java.awt.event.MouseEvent evt) {

                jTable1MouseReleased(evt);

            }

        });

        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyReleased(java.awt.event.KeyEvent evt) {

                jTable1KeyReleased(evt);

            }

        });

        jScrollPane1.setViewportView(jTable1);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.gridheight = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.weighty = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 12);

        jPanel3.add(jScrollPane1, gridBagConstraints);



        jPanelSearch.setLayout(new java.awt.GridBagLayout());



        jButtonSearch.setText("Search");

        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonSearchActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 4;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);

        jPanelSearch.add(jButtonSearch, gridBagConstraints);



        jComboBoxSearchServicePoint.setFont(defaultFont1);

        jComboBoxSearchServicePoint.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jComboBoxSearchServicePointActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.gridwidth = 3;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);

        jPanelSearch.add(jComboBoxSearchServicePoint, gridBagConstraints);



        jPanel6.setLayout(new java.awt.GridBagLayout());



        jLabel8.setFont(defaultFont1);

        jLabel8.setText("ถึงวันที่");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;

        jPanel6.add(jLabel8, gridBagConstraints);



        dateComboBoxDateFrom.setMinimumSize(new java.awt.Dimension(95, 24));

        dateComboBoxDateFrom.setPreferredSize(new java.awt.Dimension(95, 24));

        dateComboBoxDateFrom.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                dateComboBoxDateFromActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);

        jPanel6.add(dateComboBoxDateFrom, gridBagConstraints);



        dateComboBoxDateTo.setMinimumSize(new java.awt.Dimension(95, 24));

        dateComboBoxDateTo.setPreferredSize(new java.awt.Dimension(95, 24));

        dateComboBoxDateTo.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                dateComboBoxDateToActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);

        jPanel6.add(dateComboBoxDateTo, gridBagConstraints);



        jCheckBoxShowDatePeriod.setText("ตั้งแต่วันที่");

        jCheckBoxShowDatePeriod.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jCheckBoxShowDatePeriodActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        jPanel6.add(jCheckBoxShowDatePeriod, gridBagConstraints);



        jCheckBoxShowCancel.setText("ยกเลิก");

        jCheckBoxShowCancel.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jCheckBoxShowCancelActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);

        jPanel6.add(jCheckBoxShowCancel, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.gridwidth = 5;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.weightx = 1.0;

        jPanelSearch.add(jPanel6, gridBagConstraints);



        jCheckBoxSCurrPatient.setFont(defaultFont1);

        jCheckBoxSCurrPatient.setSelected(true);

        jCheckBoxSCurrPatient.setText("CurrentPatient");

        jCheckBoxSCurrPatient.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jCheckBoxSCurrPatientActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.gridwidth = 2;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);

        jPanelSearch.add(jCheckBoxSCurrPatient, gridBagConstraints);



        jComboBoxSearchStatus.setFont(defaultFont1);

        jComboBoxSearchStatus.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jComboBoxSearchStatusActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.gridwidth = 3;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);

        jPanelSearch.add(jComboBoxSearchStatus, gridBagConstraints);



        jLabel15.setFont(defaultFont1);

        jLabel15.setText("สถานะ");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(5, 1, 0, 0);

        jPanelSearch.add(jLabel15, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(12, 10, 5, 12);

        jPanel3.add(jPanelSearch, gridBagConstraints);



        jPanel4.setLayout(new java.awt.GridBagLayout());



        jButtonPrintListAppointment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/preview24.gif"))); // NOI18N

        jButtonPrintListAppointment.setToolTipText("PreviewListAppointment");

        jButtonPrintListAppointment.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jButtonPrintListAppointment.setMaximumSize(new java.awt.Dimension(26, 26));

        jButtonPrintListAppointment.setMinimumSize(new java.awt.Dimension(26, 26));

        jButtonPrintListAppointment.setPreferredSize(new java.awt.Dimension(26, 26));

        jButtonPrintListAppointment.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonPrintListAppointmentActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;

        gridBagConstraints.weightx = 1.0;

        jPanel4.add(jButtonPrintListAppointment, gridBagConstraints);



        jButtonAutoVisit.setText("AutoVisit");

        jButtonAutoVisit.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonAutoVisitActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.weightx = 1.0;

        jPanel4.add(jButtonAutoVisit, gridBagConstraints);



        jButtonPrintListAppointment1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/print24.gif"))); // NOI18N

        jButtonPrintListAppointment1.setToolTipText("PrintListAppointment");

        jButtonPrintListAppointment1.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jButtonPrintListAppointment1.setMaximumSize(new java.awt.Dimension(26, 26));

        jButtonPrintListAppointment1.setMinimumSize(new java.awt.Dimension(26, 26));

        jButtonPrintListAppointment1.setPreferredSize(new java.awt.Dimension(26, 26));

        jButtonPrintListAppointment1.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonPrintListAppointment1ActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        jPanel4.add(jButtonPrintListAppointment1, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 3;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(17, 12, 5, 12);

        jPanel3.add(jPanel4, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.weighty = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);

        getContentPane().add(jPanel3, gridBagConstraints);



        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Diag_Appoint_Detail"));

        jPanel10.setLayout(new java.awt.GridBagLayout());



        jPanel1.setLayout(new java.awt.GridBagLayout());



        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N

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

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel1.add(jButtonAdd, gridBagConstraints);



        jButtonDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N

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

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.weightx = 1.0;

        jPanel1.add(jButtonDel, gridBagConstraints);



        jButtonPreviewAppointment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/preview24.gif"))); // NOI18N

        jButtonPreviewAppointment.setToolTipText("PreviewAppointment");

        jButtonPreviewAppointment.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jButtonPreviewAppointment.setMaximumSize(new java.awt.Dimension(26, 26));

        jButtonPreviewAppointment.setMinimumSize(new java.awt.Dimension(26, 26));

        jButtonPreviewAppointment.setPreferredSize(new java.awt.Dimension(26, 26));

        jButtonPreviewAppointment.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonPreviewAppointmentActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);

        jPanel1.add(jButtonPreviewAppointment, gridBagConstraints);



        jButtonPrintAppointment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/print24.gif"))); // NOI18N

        jButtonPrintAppointment.setToolTipText("PrintAppointment");

        jButtonPrintAppointment.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jButtonPrintAppointment.setMaximumSize(new java.awt.Dimension(26, 26));

        jButtonPrintAppointment.setMinimumSize(new java.awt.Dimension(26, 26));

        jButtonPrintAppointment.setPreferredSize(new java.awt.Dimension(26, 26));

        jButtonPrintAppointment.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonPrintAppointmentActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 3;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel1.add(jButtonPrintAppointment, gridBagConstraints);



        jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Save16.gif"))); // NOI18N

        jButtonSave.setText("บันทึก");

        jButtonSave.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonSaveActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 4;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;

        jPanel1.add(jButtonSave, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 10;

        gridBagConstraints.gridwidth = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(5, 12, 5, 12);

        jPanel10.add(jPanel1, gridBagConstraints);



        jPanel5.setLayout(new java.awt.GridBagLayout());



        jCheckBoxAutoVisit.setFont(defaultFont1);

        jCheckBoxAutoVisit.setText("OpenAutoVisit");

        jCheckBoxAutoVisit.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jCheckBoxAutoVisitActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);

        jPanel5.add(jCheckBoxAutoVisit, gridBagConstraints);



        jButtonQueueVisit.setText("...");

        jButtonQueueVisit.setEnabled(false);

        jButtonQueueVisit.setMaximumSize(new java.awt.Dimension(24, 24));

        jButtonQueueVisit.setMinimumSize(new java.awt.Dimension(26, 26));

        jButtonQueueVisit.setPreferredSize(new java.awt.Dimension(26, 26));

        jButtonQueueVisit.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonQueueVisitActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 3;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);

        jPanel5.add(jButtonQueueVisit, gridBagConstraints);



        jLabel7.setFont(defaultFont1);

        jLabel7.setText("FixQueue");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);

        jPanel5.add(jLabel7, gridBagConstraints);



        jComboBoxStatus.setFont(defaultFont1);

        jComboBoxStatus.addItemListener(new java.awt.event.ItemListener() {

            public void itemStateChanged(java.awt.event.ItemEvent evt) {

                jComboBoxStatusItemStateChanged(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 5;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);

        jPanel5.add(jComboBoxStatus, gridBagConstraints);



        jLabelVN.setFont(defaultFont1);

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 6;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        jPanel5.add(jLabelVN, gridBagConstraints);



        jLabel13.setFont(defaultFont1);

        jLabel13.setText("AppointmentStatus");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 4;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);

        jPanel5.add(jLabel13, gridBagConstraints);



        jLabel12.setFont(defaultFont1);

        jLabel12.setText("เลือกคิว");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);

        jPanel5.add(jLabel12, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 7;

        gridBagConstraints.gridwidth = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel10.add(jPanel5, gridBagConstraints);



        jPanel2.setLayout(new java.awt.GridBagLayout());



        jLabel2.setFont(defaultFont1);

        jLabel2.setText("HN : ");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        jPanel2.add(jLabel2, gridBagConstraints);



        jTextFieldHN.setFont(defaultFont1);

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);

        jPanel2.add(jTextFieldHN, gridBagConstraints);



        jLabel3.setFont(defaultFont1);

        jLabel3.setText(":");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);

        jPanel2.add(jLabel3, gridBagConstraints);



        jTextFieldPatientName.setFont(defaultFont1);

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 3;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);

        jPanel2.add(jTextFieldPatientName, gridBagConstraints);



        jTextFieldCancel.setFont(defaultFont1);

        jTextFieldCancel.setForeground(new java.awt.Color(255, 0, 0));

        jTextFieldCancel.setText("ยกเลิก");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 4;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;

        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);

        jPanel2.add(jTextFieldCancel, gridBagConstraints);



        jLabel14.setFont(defaultFont1);

        jLabel14.setText("สิทธิ");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);

        jPanel2.add(jLabel14, gridBagConstraints);



        jTextFieldPlan.setFont(defaultFont1);

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 0);

        jPanel2.add(jTextFieldPlan, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.gridwidth = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel10.add(jPanel2, gridBagConstraints);



        jPanel22.setLayout(new java.awt.GridBagLayout());



        jPanelHide5.setLayout(new java.awt.GridBagLayout());



        jButtonDelOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Back16.gif"))); // NOI18N

        jButtonDelOrder.setMaximumSize(new java.awt.Dimension(26, 26));

        jButtonDelOrder.setMinimumSize(new java.awt.Dimension(26, 26));

        jButtonDelOrder.setPreferredSize(new java.awt.Dimension(26, 26));

        jButtonDelOrder.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonDelOrderActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;

        gridBagConstraints.weighty = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);

        jPanelHide5.add(jButtonDelOrder, gridBagConstraints);



        jButtonAddOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/Forward16.gif"))); // NOI18N

        jButtonAddOrder.setMaximumSize(new java.awt.Dimension(26, 26));

        jButtonAddOrder.setMinimumSize(new java.awt.Dimension(26, 26));

        jButtonAddOrder.setPreferredSize(new java.awt.Dimension(26, 26));

        jButtonAddOrder.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonAddOrderActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;

        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);

        jPanelHide5.add(jButtonAddOrder, gridBagConstraints);



        jLabel1.setFont(defaultFont1);

        jLabel1.setText("Search");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        jPanelHide5.add(jLabel1, gridBagConstraints);



        jTextFieldSearchOrder.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyReleased(java.awt.event.KeyEvent evt) {

                jTextFieldSearchOrderKeyReleased(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);

        jPanelHide5.add(jTextFieldSearchOrder, gridBagConstraints);



        jTableItem.setModel(new javax.swing.table.DefaultTableModel(

            new Object [][] {



            },

            new String [] {



            }

        ));

        jTableItem.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseReleased(java.awt.event.MouseEvent evt) {

                jTableItemMouseReleased(evt);

            }

        });

        jTableItem.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyReleased(java.awt.event.KeyEvent evt) {

                jTableItemKeyReleased(evt);

            }

        });

        jScrollPane5.setViewportView(jTableItem);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.gridwidth = 2;

        gridBagConstraints.gridheight = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.weighty = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);

        jPanelHide5.add(jScrollPane5, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridheight = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.weighty = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);

        jPanel22.add(jPanelHide5, gridBagConstraints);



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



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 8;

        gridBagConstraints.gridwidth = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.weighty = 1.0;

        jPanel10.add(jPanel22, gridBagConstraints);



        jPanel24.setLayout(new java.awt.GridBagLayout());



        jComboBoxServicePoint.setFont(defaultFont1);

        jComboBoxServicePoint.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jComboBoxServicePointActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 4;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);

        jPanel24.add(jComboBoxServicePoint, gridBagConstraints);



        jComboBoxDoctor.setFont(defaultFont1);

        jComboBoxDoctor.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jComboBoxDoctorActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 3;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);

        jPanel24.add(jComboBoxDoctor, gridBagConstraints);



        jPanel25.setToolTipText("ในวันที่ และจุดบริการเดียวกัน");

        jPanel25.setLayout(new java.awt.GridBagLayout());



        jLabelCountSP.setFont(defaultFont1);

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;

        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);

        jPanel25.add(jLabelCountSP, gridBagConstraints);



        jLabel18.setFont(defaultFont1);

        jLabel18.setText("จำนวน: ");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        jPanel25.add(jLabel18, gridBagConstraints);



        jLabel19.setFont(defaultFont1);

        jLabel19.setText(" นัด");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);

        jPanel25.add(jLabel19, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 4;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;

        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);

        jPanel24.add(jPanel25, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.gridwidth = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);

        jPanel24.add(jTextFieldApType, gridBagConstraints);



        jPanel26.setToolTipText("ในวันที่และแพทย์เดียวกัน");

        jPanel26.setLayout(new java.awt.GridBagLayout());



        jLabelCountDoctor.setFont(defaultFont1);

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;

        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);

        jPanel26.add(jLabelCountDoctor, gridBagConstraints);



        jLabel22.setFont(defaultFont1);

        jLabel22.setText("จำนวน: ");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        jPanel26.add(jLabel22, gridBagConstraints);



        jLabel23.setFont(defaultFont1);

        jLabel23.setText(" นัด");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);

        jPanel26.add(jLabel23, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 3;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;

        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);

        jPanel24.add(jPanel26, gridBagConstraints);



        jLabel11.setFont(defaultFont1);

        jLabel11.setText("DoctorName");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 3;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        jPanel24.add(jLabel11, gridBagConstraints);



        jLabel10.setFont(defaultFont1);

        jLabel10.setText("ToServicePoint");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 4;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        jPanel24.add(jLabel10, gridBagConstraints);



        jLabel9.setFont(defaultFont1);

        jLabel9.setText("ApType");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        jPanel24.add(jLabel9, gridBagConstraints);



        jLabel6.setFont(defaultFont1);

        jLabel6.setText("Detail");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 10;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel24.add(jLabel6, gridBagConstraints);



        jScrollPane2.setMaximumSize(new java.awt.Dimension(250, 150));

        jScrollPane2.setMinimumSize(new java.awt.Dimension(50, 30));

        jScrollPane2.setPreferredSize(new java.awt.Dimension(50, 30));

        jScrollPane2.setViewportView(jTextAreaDescription);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 10;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.weighty = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);

        jPanel24.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.gridwidth = 2;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);

        jPanel24.add(jComboBoxApType53, gridBagConstraints);



        jLabel16.setFont(defaultFont1);

        jLabel16.setText("ประเภทนัด");

        jLabel16.setToolTipText("สำหรับออกรายงาน 18 แฟ้ม");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        jPanel24.add(jLabel16, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 6;

        gridBagConstraints.gridwidth = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanel10.add(jPanel24, gridBagConstraints);



        jLabel4.setFont(defaultFont1);

        jLabel4.setText("DateAppointment");

        jLabel4.setPreferredSize(new java.awt.Dimension(82, 21));

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(9, 0, 0, 0);

        jPanel10.add(jLabel4, gridBagConstraints);



        jPanelHide0.setLayout(new java.awt.GridBagLayout());



        jButton4.setText("ตัวช่วยนัด");

        jButton4.setMargin(new java.awt.Insets(0, 1, 0, 0));

        jButton4.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButton4ActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 5;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);

        jPanelHide0.add(jButton4, gridBagConstraints);



        hosComboBoxStdAppointmentTemplate.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                hosComboBoxStdAppointmentTemplateActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 4;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);

        jPanelHide0.add(hosComboBoxStdAppointmentTemplate, gridBagConstraints);



        jButtonDM.setText("DM");

        jButtonDM.setMargin(new java.awt.Insets(0, 1, 0, 0));

        jButtonDM.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonDMActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);

        jPanelHide0.add(jButtonDM, gridBagConstraints);



        jButton2.setText("HT");

        jButton2.setMargin(new java.awt.Insets(0, 1, 0, 0));

        jButton2.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButton2ActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        jPanelHide0.add(jButton2, gridBagConstraints);



        jButton3.setText("H");

        jButton3.setMargin(new java.awt.Insets(0, 1, 0, 0));

        jButton3.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButton3ActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 3;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        jPanelHide0.add(jButton3, gridBagConstraints);



        jLabelHide1.setFont(defaultFont1);

        jLabelHide1.setText("ตัวช่วยนัด");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        jPanelHide0.add(jLabelHide1, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.gridwidth = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);

        jPanel10.add(jPanelHide0, gridBagConstraints);



        jPanelTime.setLayout(new java.awt.GridBagLayout());



        jPanel27.setToolTipText("ในวันที่ เวลา และแพทย์ที่นัดเดียวกัน");

        jPanel27.setLayout(new java.awt.GridBagLayout());



        jLabelCountTime.setFont(defaultFont1);

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;

        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);

        jPanel27.add(jLabelCountTime, gridBagConstraints);



        jLabel24.setFont(defaultFont1);

        jLabel24.setText("จำนวน: ");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);

        jPanel27.add(jLabel24, gridBagConstraints);



        jLabel25.setFont(defaultFont1);

        jLabel25.setText(" นัด");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 3;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);

        jPanel27.add(jLabel25, gridBagConstraints);



        timeTextFieldTimeAppointment.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        timeTextFieldTimeAppointment.setToolTipText("");

        timeTextFieldTimeAppointment.setMinimumSize(new java.awt.Dimension(45, 21));

        timeTextFieldTimeAppointment.setName("timeTextFieldTimeAppointment"); // NOI18N

        timeTextFieldTimeAppointment.setPreferredSize(new java.awt.Dimension(45, 21));

        timeTextFieldTimeAppointment.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                timeTextFieldTimeAppointmentActionPerformed(evt);

            }

        });

        timeTextFieldTimeAppointment.addFocusListener(new java.awt.event.FocusAdapter() {

            public void focusLost(java.awt.event.FocusEvent evt) {

                timeTextFieldTimeAppointmentFocusLost(evt);

            }

        });

        timeTextFieldTimeAppointment.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyReleased(java.awt.event.KeyEvent evt) {

                timeTextFieldTimeAppointmentKeyReleased(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.weighty = 1.0;

        jPanel27.add(timeTextFieldTimeAppointment, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);

        jPanelTime.add(jPanel27, gridBagConstraints);



        jLabel5.setFont(defaultFont1);

        jLabel5.setText("TimeAppointment");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.gridheight = 2;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);

        jPanelTime.add(jLabel5, gridBagConstraints);



        jPanelHide7.setLayout(new java.awt.GridBagLayout());



        jButtonTime700.setText("7");

        jButtonTime700.setMargin(new java.awt.Insets(0, 1, 0, 0));

        jButtonTime700.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonTime700ActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanelHide7.add(jButtonTime700, gridBagConstraints);



        jButtonTime730.setText("7.30");

        jButtonTime730.setMargin(new java.awt.Insets(0, 1, 0, 0));

        jButtonTime730.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonTime730ActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 3;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanelHide7.add(jButtonTime730, gridBagConstraints);



        jButtonTime800.setText("8");

        jButtonTime800.setMargin(new java.awt.Insets(0, 1, 0, 0));

        jButtonTime800.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonTime800ActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 4;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanelHide7.add(jButtonTime800, gridBagConstraints);



        jButtonTime830.setText("8.30");

        jButtonTime830.setMargin(new java.awt.Insets(0, 1, 0, 0));

        jButtonTime830.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonTime830ActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 5;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanelHide7.add(jButtonTime830, gridBagConstraints);



        jButtonTime900.setText("9");

        jButtonTime900.setMargin(new java.awt.Insets(0, 1, 0, 0));

        jButtonTime900.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonTime900ActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 6;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanelHide7.add(jButtonTime900, gridBagConstraints);



        jButtonTime1000.setText("10");

        jButtonTime1000.setMargin(new java.awt.Insets(0, 1, 0, 0));

        jButtonTime1000.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonTime1000ActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 7;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanelHide7.add(jButtonTime1000, gridBagConstraints);



        jButtonTime1100.setText("11");

        jButtonTime1100.setMargin(new java.awt.Insets(0, 1, 0, 0));

        jButtonTime1100.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonTime1100ActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 8;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;

        jPanelHide7.add(jButtonTime1100, gridBagConstraints);



        jButtonTime1200.setText("12");

        jButtonTime1200.setMargin(new java.awt.Insets(0, 1, 0, 0));

        jButtonTime1200.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonTime1200ActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 9;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanelHide7.add(jButtonTime1200, gridBagConstraints);



        jButtonTime1300.setText("13");

        jButtonTime1300.setMargin(new java.awt.Insets(0, 1, 0, 0));

        jButtonTime1300.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonTime1300ActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 10;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanelHide7.add(jButtonTime1300, gridBagConstraints);



        jButtonTime1400.setText("14");

        jButtonTime1400.setMargin(new java.awt.Insets(0, 1, 0, 0));

        jButtonTime1400.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonTime1400ActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 11;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        jPanelHide7.add(jButtonTime1400, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        jPanelTime.add(jPanelHide7, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 5;

        gridBagConstraints.gridwidth = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        jPanel10.add(jPanelTime, gridBagConstraints);



        jPanelHide6.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 4;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);

        jPanelHide6.add(dateComboBoxDateAppointment2, gridBagConstraints);



        jButtonTo1Week.setText("1W");

        jButtonTo1Week.setMargin(new java.awt.Insets(1, 1, 1, 1));

        jButtonTo1Week.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonTo1WeekActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        jPanelHide6.add(jButtonTo1Week, gridBagConstraints);



        jButtonTo2Week.setText("2W");

        jButtonTo2Week.setMargin(new java.awt.Insets(1, 1, 1, 1));

        jButtonTo2Week.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonTo2WeekActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 1;

        jPanelHide6.add(jButtonTo2Week, gridBagConstraints);



        jButtonTo3Week.setText("3W");

        jButtonTo3Week.setMargin(new java.awt.Insets(1, 1, 1, 1));

        jButtonTo3Week.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonTo3WeekActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 3;

        gridBagConstraints.gridy = 1;

        jPanelHide6.add(jButtonTo3Week, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 4;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);

        jPanel10.add(jPanelHide6, gridBagConstraints);



        jPanel8.setLayout(new java.awt.GridBagLayout());



        jPanelHide2.setLayout(new java.awt.GridBagLayout());



        jButtonAdd4Week.setText("+4W");

        jButtonAdd4Week.setMargin(new java.awt.Insets(1, 1, 1, 1));

        jButtonAdd4Week.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonAdd4WeekActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 4;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        jPanelHide2.add(jButtonAdd4Week, gridBagConstraints);



        jButton8Week.setText("8W");

        jButton8Week.setMargin(new java.awt.Insets(1, 1, 1, 1));

        jButton8Week.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButton8WeekActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        jPanelHide2.add(jButton8Week, gridBagConstraints);



        jButton6Week.setText("6W");

        jButton6Week.setMargin(new java.awt.Insets(1, 1, 1, 1));

        jButton6Week.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButton6WeekActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        jPanelHide2.add(jButton6Week, gridBagConstraints);



        jButton4Week.setText("4W");

        jButton4Week.setMargin(new java.awt.Insets(1, 1, 1, 1));

        jButton4Week.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButton4WeekActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 4;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        jPanelHide2.add(jButton4Week, gridBagConstraints);



        jButton3Week.setText("3W");

        jButton3Week.setMargin(new java.awt.Insets(1, 1, 1, 1));

        jButton3Week.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButton3WeekActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 3;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        jPanelHide2.add(jButton3Week, gridBagConstraints);



        jButton2Week.setText("2W");

        jButton2Week.setMargin(new java.awt.Insets(1, 1, 1, 1));

        jButton2Week.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButton2WeekActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        jPanelHide2.add(jButton2Week, gridBagConstraints);



        jButton1Week.setText("1W");

        jButton1Week.setMargin(new java.awt.Insets(1, 1, 1, 1));

        jButton1Week.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButton1WeekActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        jPanelHide2.add(jButton1Week, gridBagConstraints);



        jButtonAdd1Week.setText("+1W");

        jButtonAdd1Week.setMargin(new java.awt.Insets(1, 1, 1, 1));

        jButtonAdd1Week.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jButtonAdd1WeekActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 3;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;

        jPanelHide2.add(jButtonAdd1Week, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.gridheight = 2;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        jPanel8.add(jPanelHide2, gridBagConstraints);



        dateComboBoxDateAppointment.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                dateComboBoxDateAppointmentActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.weightx = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(5, 5, 2, 0);

        jPanel8.add(dateComboBoxDateAppointment, gridBagConstraints);



        jPanel11.setToolTipText("ในวันที่นัดเดียวกัน");

        jPanel11.setLayout(new java.awt.GridBagLayout());



        jLabel21.setFont(defaultFont1);

        jLabel21.setText(" นัด");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 4;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);

        jPanel11.add(jLabel21, gridBagConstraints);



        jLabel20.setFont(defaultFont1);

        jLabel20.setText("จำนวน: ");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);

        jPanel11.add(jLabel20, gridBagConstraints);



        jLabelCountAppointment.setFont(defaultFont1);

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 3;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);

        jPanel11.add(jLabelCountAppointment, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 2;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;

        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);

        jPanel8.add(jPanel11, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);

        jPanel10.add(jPanel8, gridBagConstraints);



        jCheckBoxToDate.setFont(defaultFont1);

        jCheckBoxToDate.setSelected(true);

        jCheckBoxToDate.setText("DateEnd");

        jCheckBoxToDate.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                jCheckBoxToDateActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 4;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;

        jPanel10.add(jCheckBoxToDate, gridBagConstraints);



        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.weighty = 1.0;

        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);

        getContentPane().add(jPanel10, gridBagConstraints);



        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width-933)/2, (screenSize.height-627)/2, 933, 627);

    }// </editor-fold>//GEN-END:initComponents


    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed


//amp:10/9/2549
        if(theDialogAppointmentTemplate == null)
            theDialogAppointmentTemplate = new DialogAppointmentTemplate(theHC,this);
        theDialogAppointmentTemplate.showDialog();

    }//GEN-LAST:event_jButton4ActionPerformed


    private void hosComboBoxStdAppointmentTemplateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hosComboBoxStdAppointmentTemplateActionPerformed


        useAppointmentTemplate(Gutil.getGuiData(hosComboBoxStdAppointmentTemplate));

    }//GEN-LAST:event_hosComboBoxStdAppointmentTemplateActionPerformed


    private void jButtonDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDMActionPerformed


        useAppointmentTemplate(AppointmentTemplate.DM);

    }//GEN-LAST:event_jButtonDMActionPerformed


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed


        useAppointmentTemplate(AppointmentTemplate.HT);

    }//GEN-LAST:event_jButton2ActionPerformed


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed


        useAppointmentTemplate(AppointmentTemplate.H);

    }//GEN-LAST:event_jButton3ActionPerformed


    private void jButtonTo1WeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTo1WeekActionPerformed


        calDateByWeekAndDate(1);

    }//GEN-LAST:event_jButtonTo1WeekActionPerformed


    private void jButtonTo2WeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTo2WeekActionPerformed


        calDateByWeekAndDate(2);

    }//GEN-LAST:event_jButtonTo2WeekActionPerformed


    private void jButtonTo3WeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTo3WeekActionPerformed


        calDateByWeekAndDate(3);

    }//GEN-LAST:event_jButtonTo3WeekActionPerformed


    private void timeTextFieldTimeAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeTextFieldTimeAppointmentActionPerformed


        countAppointment(4);

    }//GEN-LAST:event_timeTextFieldTimeAppointmentActionPerformed


    private void timeTextFieldTimeAppointmentFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_timeTextFieldTimeAppointmentFocusLost


        if(flag) {
            if(timeTextFieldTimeAppointment.getText()== null
                    || timeTextFieldTimeAppointment.getText().equals("")){
                JOptionPane.showMessageDialog(this, Constant.getTextBundle("กรุณากรอกเวลาที่นัดด้วย")
                ,Constant.getTextBundle("เตือน"), JOptionPane.WARNING_MESSAGE);
                return;
            }
            /**
             *currTime เก็บเวลาปัจจุบัน
             *printTime เก็บเวลาที่ผู้ใช้พิมพ์
             */
            String currTime = theHC.theLookupControl.getTextCurrentDateTime();
            String printTime = timeTextFieldTimeAppointment.getTextTime();
            /*if(printTime=="" || printTime==null)
            {
                jButtonSave.setEnabled(true);
                return;
            } */
            /**
             *hhCurr เก็บชั่วโมงปัจจุบัน
             *mmCurr เก็บนาทีปัจจุบัน
             *hhPrint เก็บชั่วโมงที่พิมพ์
             *mmPrint เก็บนาทีที่พิมพ์
             */
            int hhCurr = Integer.parseInt(currTime.substring(0,2));
            int mmCurr = Integer.parseInt(currTime.substring(3,5));
            int hhPrint = Integer.parseInt(printTime.substring(0,2));
            int mmPrint = Integer.parseInt(printTime.substring(3,5));
            if(hhPrint > hhCurr) {
                jButtonSave.setEnabled(true);
            }
            if(hhPrint < hhCurr) {
                JOptionPane.showMessageDialog(this, Constant.getTextBundle("ไม่สามารถนัดเวลาย้อนหลังได้")
                ,Constant.getTextBundle("เตือน"), JOptionPane.WARNING_MESSAGE);
                jButtonSave.setEnabled(false);
            }
            if(hhPrint == hhCurr) {
                if(mmPrint >= mmCurr) {
                    jButtonSave.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, Constant.getTextBundle("ไม่สามารถนัดเวลาย้อนหลังได้")
                    ,Constant.getTextBundle("เตือน"), JOptionPane.WARNING_MESSAGE);
                    jButtonSave.setEnabled(false);
                }
            }
        }
        countAppointment(4);

    }//GEN-LAST:event_timeTextFieldTimeAppointmentFocusLost


    private void timeTextFieldTimeAppointmentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timeTextFieldTimeAppointmentKeyReleased


        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jTextFieldApType.requestFocus();
        }

    }//GEN-LAST:event_timeTextFieldTimeAppointmentKeyReleased


    private void jButtonAdd4WeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdd4WeekActionPerformed


        String date1 = dateComboBoxDateAppointment.getText();
        if(!date1.equals(""))
        {
            dateComboBoxDateAppointment.setText(DateUtil.convertFieldDate(
                    DateUtil.addDay(dateComboBoxDateAppointment.getText(),28)));
        }
        else
        {
            setStatus("กรุณาระบุวันที่เริ่มต้นในการนัดหมาย",UpdateStatus.WARNING);
            return;
        }

    }//GEN-LAST:event_jButtonAdd4WeekActionPerformed


    private void jButton8WeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8WeekActionPerformed


        calDateByWeek(8);

    }//GEN-LAST:event_jButton8WeekActionPerformed


    private void jButton6WeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6WeekActionPerformed


        calDateByWeek(6);

    }//GEN-LAST:event_jButton6WeekActionPerformed


    private void jButton4WeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4WeekActionPerformed


        calDateByWeek(4);

    }//GEN-LAST:event_jButton4WeekActionPerformed


    private void jButton3WeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3WeekActionPerformed


        calDateByWeek(3);

    }//GEN-LAST:event_jButton3WeekActionPerformed


    private void jButton2WeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2WeekActionPerformed


        calDateByWeek(2);

    }//GEN-LAST:event_jButton2WeekActionPerformed


    private void jButton1WeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1WeekActionPerformed


        calDateByWeek(1);

    }//GEN-LAST:event_jButton1WeekActionPerformed


    private void jButtonAdd1WeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdd1WeekActionPerformed


        String date1 = dateComboBoxDateAppointment.getText();
        if(!date1.equals(""))
        {
            dateComboBoxDateAppointment.setText(DateUtil.convertFieldDate(
                    DateUtil.addDay(dateComboBoxDateAppointment.getText(),7)));
        }
        else
        {
            setStatus("กรุณาระบุวันที่เริ่มต้นในการนัดหมาย",UpdateStatus.WARNING);
            return;
        }

    }//GEN-LAST:event_jButtonAdd1WeekActionPerformed


    private void dateComboBoxDateAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxDateAppointmentActionPerformed


        if("".equals(dateComboBoxDateAppointment2.getText())) {
            dateComboBoxDateAppointment2.setText(
                    DateUtil.convertFieldDate(dateComboBoxDateAppointment.getText()));
        }
        //amp:7/8/2549: แสดงจำนวนการนัดในวันที่ระบุ
        //2=แสดงจำนวนทั้งของวันและแพทย์
        countAppointment(1);

    }//GEN-LAST:event_dateComboBoxDateAppointmentActionPerformed


    private void jButtonTime800ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTime800ActionPerformed


        timeTextFieldTimeAppointment.setText("08:00");
        countAppointment(4);

    }//GEN-LAST:event_jButtonTime800ActionPerformed


    private void jButtonTime830ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTime830ActionPerformed


        timeTextFieldTimeAppointment.setText("08:30");
        countAppointment(4);
        

    }//GEN-LAST:event_jButtonTime830ActionPerformed


    private void jButtonTime700ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTime700ActionPerformed


        timeTextFieldTimeAppointment.setText("07:00");
        countAppointment(4);

    }//GEN-LAST:event_jButtonTime700ActionPerformed


    private void jButtonTime730ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTime730ActionPerformed


        timeTextFieldTimeAppointment.setText("07:30");
        countAppointment(4);

    }//GEN-LAST:event_jButtonTime730ActionPerformed


    private void jButtonTime900ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTime900ActionPerformed


        timeTextFieldTimeAppointment.setText("09:00");
        countAppointment(4);

    }//GEN-LAST:event_jButtonTime900ActionPerformed


    private void jButtonTime1400ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTime1400ActionPerformed


        timeTextFieldTimeAppointment.setText("14:00");
        countAppointment(4);

    }//GEN-LAST:event_jButtonTime1400ActionPerformed


    private void jButtonTime1300ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTime1300ActionPerformed


        timeTextFieldTimeAppointment.setText("13:00");
        countAppointment(4);

    }//GEN-LAST:event_jButtonTime1300ActionPerformed


    private void jButtonTime1200ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTime1200ActionPerformed


        timeTextFieldTimeAppointment.setText("12:00");
        countAppointment(4);

    }//GEN-LAST:event_jButtonTime1200ActionPerformed


    private void jButtonTime1000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTime1000ActionPerformed


        timeTextFieldTimeAppointment.setText("10:00");
        countAppointment(4);

    }//GEN-LAST:event_jButtonTime1000ActionPerformed


    private void jButtonTime1100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTime1100ActionPerformed


        timeTextFieldTimeAppointment.setText("11:00");
        countAppointment(4);

    }//GEN-LAST:event_jButtonTime1100ActionPerformed


    private void jComboBoxServicePointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxServicePointActionPerformed


        countAppointment(3);

    }//GEN-LAST:event_jComboBoxServicePointActionPerformed


    private void jTableItemMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTableItemMouseReleased



    {//GEN-HEADEREND:event_jTableItemMouseReleased


        //amp:9/8/2549
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


        //amp:9/8/2549
        if(evt.getKeyCode() == KeyEvent.VK_SPACE)
        {
            jButtonAddOrderActionPerformed(null);
            jTextFieldSearchOrder.requestFocus();
        }

    }//GEN-LAST:event_jTableItemKeyReleased


    private void jComboBoxDoctorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxDoctorActionPerformed



    {//GEN-HEADEREND:event_jComboBoxDoctorActionPerformed


        //amp:7/8/2549: เอาการตรวจสอบมาไว้ใน Gui เพราะให้สามารถแสดงข้อความเตือนบน Dialog ได้
        //1=แสดงจำนวนเฉพาะของแพทย์
        countAppointment(2);

    }//GEN-LAST:event_jComboBoxDoctorActionPerformed


    private void jCheckBoxSCurrPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSCurrPatientActionPerformed


        searchAppointment();

    }//GEN-LAST:event_jCheckBoxSCurrPatientActionPerformed


    private void jComboBoxSearchStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSearchStatusActionPerformed


        //searchAppointment();

    }//GEN-LAST:event_jComboBoxSearchStatusActionPerformed


    private void jComboBoxStatusItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_jComboBoxStatusItemStateChanged



    {//GEN-HEADEREND:event_jComboBoxStatusItemStateChanged


    }//GEN-LAST:event_jComboBoxStatusItemStateChanged


    private void jCheckBoxShowCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxShowCancelActionPerformed


        searchAppointment();

    }//GEN-LAST:event_jCheckBoxShowCancelActionPerformed


    private void jCheckBoxShowDatePeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxShowDatePeriodActionPerformed


            this.dateComboBoxDateFrom.setEnabled(jCheckBoxShowDatePeriod.isSelected());
            this.dateComboBoxDateTo.setEnabled(jCheckBoxShowDatePeriod.isSelected());
            searchAppointment();

    }//GEN-LAST:event_jCheckBoxShowDatePeriodActionPerformed


    private void jButtonDelOrderActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDelOrderActionPerformed



    {//GEN-HEADEREND:event_jButtonDelOrderActionPerformed


        //amp:24/02/2549
        int row[] = jTableAppointmentOrder.getSelectedRows();
        Vector vAp = theHC.thePatientControl.deleteAppointmentOrder(vAppointmentOrder,row);
        setAppointmentOrderV(vAp);

    }//GEN-LAST:event_jButtonDelOrderActionPerformed


    private void jTextFieldSearchOrderKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextFieldSearchOrderKeyReleased



    {//GEN-HEADEREND:event_jTextFieldSearchOrderKeyReleased


        //amp:23/02/2549
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) searchItem();
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) jTableItem.requestFocus();
        if(jTextFieldSearchOrder.getText().length() > 1) searchItem();

    }//GEN-LAST:event_jTextFieldSearchOrderKeyReleased


    private void jButtonAddOrderActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddOrderActionPerformed



    {//GEN-HEADEREND:event_jButtonAddOrderActionPerformed


        //amp:24/02/2549
        //ต้องป้องกันไม่ให้ค้นเจอแลบปกปิดด้วยนะ แต่ยากให้บันทึกแลบปกปิดไม่ได้ดีกว่า
        //ไม่มีเหตุผลที่จะป้องกันไม่ให้บันทึกแลบปกปิดได้นะ แต่เป็นความปลอดภัยของข้อมูลมากกว่า
        int[] row = jTableItem.getSelectedRows();
        if(row.length==0)
        {
            setStatus(Constant.getTextBundle("ยังไม่ได้เลือกรายการ Item"),UpdateStatus.WARNING);
            return;
        }
        if(vAppointmentOrder==null)
            vAppointmentOrder = new Vector();
        for(int i=0,size=row.length; i<size; i++)
        {
            Item item =(Item)vItem.get(row[i]);
            if(vAppointmentOrder.isEmpty())//รอบแรกให้ add ได้เลย
            {
                AppointmentOrder appointmentOrder = new AppointmentOrder();
                appointmentOrder.patient_id = theHO.thePatient.getObjectId();
                appointmentOrder.item_id = item.getObjectId();
                appointmentOrder.item_common_name = item.common_name;
                vAppointmentOrder.add(appointmentOrder);
            }
            else
            {
                boolean isSame = false;
                for(int j=0; j<vAppointmentOrder.size(); j++)
                {
                    if(item.getObjectId().equals(((AppointmentOrder)vAppointmentOrder.get(j)).item_id)) isSame = true;
                }
                if(!isSame)
                {
                    AppointmentOrder appointmentOrder = new AppointmentOrder();
                    appointmentOrder.patient_id = theHO.thePatient.getObjectId();
                    appointmentOrder.item_id = item.getObjectId();
                    appointmentOrder.item_common_name = item.common_name;
                    vAppointmentOrder.add(appointmentOrder);
                }
            }
        }
        Constant.println("______________________ jButtonAddOrderActionPerformed");
        setAppointmentOrderV(vAppointmentOrder);

    }//GEN-LAST:event_jButtonAddOrderActionPerformed


    private void jCheckBoxToDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxToDateActionPerformed


        dateComboBoxDateAppointment2.setEnabled(jCheckBoxToDate.isSelected());
        jButtonTo1Week.setEnabled(jCheckBoxToDate.isSelected());
        jButtonTo2Week.setEnabled(jCheckBoxToDate.isSelected());
        jButtonTo3Week.setEnabled(jCheckBoxToDate.isSelected());

    }//GEN-LAST:event_jCheckBoxToDateActionPerformed


    private void dateComboBoxDateToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxDateToActionPerformed


        this.dateComboBoxDateTo.getText();

    }//GEN-LAST:event_dateComboBoxDateToActionPerformed


    private void dateComboBoxDateFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxDateFromActionPerformed


        //searchAppointment();

    }//GEN-LAST:event_dateComboBoxDateFromActionPerformed


    private void jComboBoxSearchServicePointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSearchServicePointActionPerformed


        //searchAppointment();

    }//GEN-LAST:event_jComboBoxSearchServicePointActionPerformed


    private void jButtonPrintListAppointment1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintListAppointment1ActionPerformed


        /*
         *preview = 1 แสดงก่อนพิมพ์
         *preview = 0 ไม่ต้องแสดงก่อนพิมพ์
         */
        String sp_name = ComboboxModel.getStringConboBox(jComboBoxSearchServicePoint);
        String start_date = "";
        if(!jCheckBoxShowDatePeriod.isSelected()){
            start_date = Constant.getTextBundle(" ทั้งหมด");
        }
        else{
            start_date = DateUtil.convertFieldDate(dateComboBoxDateFrom.getText())
            + Constant.getTextBundle(" ถึงวันที่ ")+ DateUtil.convertFieldDate(dateComboBoxDateTo.getText());
        }
        //จัดเรียงข้อมูลตามหน้าจอที่เรียงเอาไว้ก่อนที่จะสั่งพิมพ์
        Vector vGui = new Vector();
        for(int i=0;i<jTable1.getRowCount();i++)
        {
            int index = jTable1.getVectorIndex(i);
            vGui.add(vappointment.get(index));
        }
        theHC.thePrintControl.printAppointmentList(PrintControl.MODE_PRINT, vGui,sp_name,start_date);

    }//GEN-LAST:event_jButtonPrintListAppointment1ActionPerformed


    private void jButtonPreviewAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreviewAppointmentActionPerformed


        theHC.thePrintControl.printAppointment(theAppointment,true);

    }//GEN-LAST:event_jButtonPreviewAppointmentActionPerformed


    private void jButtonPrintListAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintListAppointmentActionPerformed


        String sp_name = ComboboxModel.getStringConboBox(jComboBoxSearchServicePoint);
        String start_date = "";
        if(!jCheckBoxShowDatePeriod.isSelected()){
            start_date = Constant.getTextBundle(" ทั้งหมด");
        }
        else{
            start_date = DateUtil.convertFieldDate(dateComboBoxDateFrom.getText())
            + Constant.getTextBundle(" ถึงวันที่ ")+ DateUtil.convertFieldDate(dateComboBoxDateTo.getText());
        }
        theHC.thePrintControl.printAppointmentList(PrintControl.MODE_PREVIEW, vappointment,sp_name,start_date);

    }//GEN-LAST:event_jButtonPrintListAppointmentActionPerformed


    /**
     *  กำหนด คิวให้กับผู้ป่วยก่อนจะเปิด Auto Visit
     */

    private void jButtonQueueVisitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQueueVisitActionPerformed


        if(theQueueVisit==null)
            theQueueVisit = new QueueVisit();
        if(theAppointment!=null)
        {
            theQueueVisit.setObjectId(theAppointment.queue_visit_id);
        }
        DialogQueueVisit.showDialog(theHC,this,1,theQueueVisit);
        jLabel7.setText(theQueueVisit.description);

    }//GEN-LAST:event_jButtonQueueVisitActionPerformed


    private void jButtonAutoVisitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAutoVisitActionPerformed


        int[] row = this.jTable1.getSelectedRows();
        Vector vapp = new Vector();
        theVisitControl.visitFromVAppointment(this, row, vappointment);

    }//GEN-LAST:event_jButtonAutoVisitActionPerformed


    private void jCheckBoxAutoVisitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAutoVisitActionPerformed


        if(used_queue_visit)
           jButtonQueueVisit.setEnabled(jCheckBoxAutoVisit.isSelected());
           jLabel7.setText("");

    }//GEN-LAST:event_jCheckBoxAutoVisitActionPerformed


    private void dateComboBoxDateAppointmentFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxDateAppointmentFocusLost


    }//GEN-LAST:event_dateComboBoxDateAppointmentFocusLost


    private void jButtonDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelActionPerformed


        Constant.println("______________________ jButtonDelActionPerformed");
        boolean ret = thePatientControl.deleteVAppointment(vappointment,jTable1.getSelectedRows(),this);
        if(!ret)
            return;
        theAppointment = theHO.initAppointment("");
        setAppointment(theAppointment);
        setAppointmentOrderV(null);//amp:13/05/2549
        searchAppointment();

    }//GEN-LAST:event_jButtonDelActionPerformed


    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing


        setVisible(false);
        //closeDialog = true;
        dispose();

    }//GEN-LAST:event_formWindowClosing


    private void jButtonPrintAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintAppointmentActionPerformed


        theHC.thePrintControl.printAppointment(theAppointment,false);

    }//GEN-LAST:event_jButtonPrintAppointmentActionPerformed


    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed


        searchAppointment();
        if(vappointment==null || vappointment.isEmpty())
                setStatus(Constant.getTextBundle("ไม่มีผู้ป่วยที่นัดในช่วงเวลานี้"),UpdateStatus.COMPLETE);
        else    setStatus(Constant.getTextBundle("มีผู้ป่วยอยู่ในคิวนัดทั้งสิ้น") + vappointment.size() +
                Constant.getTextBundle(" คน"),UpdateStatus.COMPLETE);

    }//GEN-LAST:event_jButtonSearchActionPerformed


    private void comboBoxClinic1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxClinic1ActionPerformed


    }//GEN-LAST:event_comboBoxClinic1ActionPerformed


	private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased


        int row = jTable1.getSelectedRow();
        if(row==-1) return;
        SpecialQueryAppointment spappointment = (SpecialQueryAppointment) vappointment.get(row);
        if(spappointment==null) return;
        Hashtable ht = thePatientControl.readHAppointmentByPK(spappointment.t_patient_appointment_id);
        theAppointment = (Appointment)ht.get("Appointment");
        setAppointment(theAppointment);
        Vector a_order = (Vector)ht.get("AppointmentOrderV");
        setAppointmentOrderV(a_order);

    }//GEN-LAST:event_jTable1MouseReleased


    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased


        if(evt.getKeyCode()==evt.VK_UP || evt.getKeyCode()==evt.VK_DOWN)
        {
            this.jTable1MouseReleased(null);
        }

    }//GEN-LAST:event_jTable1KeyReleased




    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
         actionCommand = true;
         if(!this.showlist)
         {
            //นัดเป็นช่วง
             if(this.jCheckBoxToDate.isSelected())
             {
                 String date1 = dateComboBoxDateAppointment.getText();
                 String date2 = dateComboBoxDateAppointment2.getText();
                 if(date1.equals(""))
                 {
                     setStatus("กรุณาระบุวันที่เริ่มนัดหมาย",UpdateStatus.WARNING);
                     return;
                 }
                 int qty_day = DateUtil.countDateDiff(date2,date1)+1;
                 vAppointment = new Vector();
                 for(int i=0;i<qty_day;i++)
                 {
                     theAppointment = new Appointment();
                     getAppointment(theAppointment);
                     theAppointment.appoint_date
                             = DateUtil.addDay(theAppointment.appoint_date,i);
                     vAppointment.add(theAppointment);
                 }
                 thePatientControl.saveAppointment(vAppointment,vAppointmentOrder,this);
             }
             //นัดวันเดียว
             else
             {
                 getAppointment(theAppointment);
                 thePatientControl.saveAppointment(theAppointment,vAppointmentOrder,this);
                 this.setAppointment(theAppointment);
             }
         }
         else
         {
             getAppointment(theAppointment);
             thePatientControl.closeAppointment(theAppointment,this);
             this.setAppointment(theAppointment);
         }
         searchAppointment();

    }//GEN-LAST:event_jButtonSaveActionPerformed


    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed


         actionCommand = false;
         vAppointment = new Vector();
         String date_time = theHC.theLookupControl.getTextCurrentDateTime();
         this.jTable1.clearSelection();
         theAppointment = theHO.initAppointment(date_time);
         theQueueVisit = null;
         setAppointment(theAppointment);
         setAppointmentOrderV(null);
//         this.setStatus(Constant.getTextBundle("กรุณากรอกข้อมูลการนัด"),UpdateStatus.WARNING);

    }//GEN-LAST:event_jButtonAddActionPerformed


    //////////////////////////////////////////////////////////////////////////
    /**
     *ใช้ในการ Map QueueVisit
     */
    private MapQueueVisit getMapQueueVisit(Visit v,SpecialQueryAppointment spappointment)
    throws Exception
    {
        MapQueueVisit mapQueueVisit = new MapQueueVisit();
        QueueVisit queueVisit = theVisitControl.readSeqQueueVisit(
            spappointment.b_visit_queue_setup_id);
        if(queueVisit == null)
            throw new Exception(Constant.getTextBundle("ไม่พบข้อมูลคิวในการรับบริการ QueueVisit not found"));
        mapQueueVisit.queue_visit  = spappointment.b_visit_queue_setup_id;
        mapQueueVisit.queue = queueVisit.queue;
        return mapQueueVisit;
    }
    ////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    public JFrame getJFrame() {
        return this;
    }
    public void getAppointment(Appointment appointment)
    {
        this.input_mode = false;
            jLabelCountAppointment.setText("");
            jLabelCountDoctor.setText("");
            jLabelCountSP.setText("");
        if(theHO.thePatient!=null)
            appointment.patient_id = theHO.thePatient.getObjectId();
        if(theHO.theVisit!=null)
            appointment.visit_id = theHO.theVisit.getObjectId();
        appointment.date_serv = theHC.theLookupControl.getTextCurrentDateTime();
        appointment.appoint_date = dateComboBoxDateAppointment.getText();//Gutil.getGuiBDate(dateComboBoxDateAppointment.getText());
        appointment.appoint_time = timeTextFieldTimeAppointment.getText();
        appointment.aptype = Gutil.CheckReservedWords(jTextFieldApType.getText());
        appointment.aptype53 = ComboboxModel.getCodeComboBox(jComboBoxApType53);
        appointment.clinic_code = ComboboxModel.getCodeComboBox(jComboBoxServicePoint);
        appointment.doctor_code = ComboboxModel.getCodeComboBox(jComboBoxDoctor);
        appointment.description = Gutil.CheckReservedWords(jTextAreaDescription.getText());
        appointment.status = ComboboxModel.getCodeComboBox(jComboBoxStatus);
        if(jCheckBoxAutoVisit.isSelected())
                appointment.auto_visit = "1";
        else    appointment.auto_visit = "0";
        if(theQueueVisit != null)
        {
            appointment.queue_visit_id  = theQueueVisit.getObjectId();
        }
        if(appointment.appoint_staff_record == null || appointment.appoint_staff_record.equals(""))
        {
            appointment.appoint_staff_record = theHO.theEmployee.getObjectId();
            appointment.appoint_record_date_time = theLookupControl.getTextCurrentDateTime();
        }
        this.input_mode = true;
    }
    ///////////////////////////////////////////////////////////////////////////
    /**
     *เซตความยาวDialog
     */
    private void setDialog()
    {
        setSize(740,550);
        setTitle(Constant.getTextBundle("การนัดหมาย"));
        Toolkit thekit = getToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-getSize().width)/2, (screenSize.height-getSize().height)/2);
    }
    public Appointment getAppointment(){
        return theAppointment;
    }
    //////////////////////////////////////////////////////////////////////////
    /**
     *เป็นฟังก์ชั่นที่ใช้ในการโชว์Dialog
     * @param patient = ข้อมูลผู้ป่วย
     * visit = ข้อมูลการรับบริการ
     * list = กำหนดให้แสดง list รายการนัด
     * appoint = ข้อมูลการนัดที่ส่งมา
     * @author kingland
     */
    public void showDialog(Patient patient,Visit visit,boolean list,Appointment appoint)
    {
        this.showlist = list;
        this.jCheckBoxSCurrPatient.setSelected(patient!=null);
        //this.jCheckBoxShowDatePeriod.setSelected(patient==null);
        this.jCheckBoxShowDatePeriodActionPerformed(null);
        searchAppointment();
//        hosComboBoxStdAppointmentTemplate.refresh();
        used_queue_visit = Gutil.isSelected( theHC.theLookupControl.readOption().inqueuevisit);
        if(!list)
            jButtonAddActionPerformed(null);
        if(appoint!=null)
        {
            Hashtable ht = thePatientControl.readHAppointmentByPK(appoint.getObjectId());
            if(ht!=null){
                theAppointment = (Appointment)ht.get("Appointment");
                setAppointment(theAppointment);
                Vector a_order = (Vector)ht.get("AppointmentOrderV");
                setAppointmentOrderV(a_order);
            }
        }
        setVisible(true);
    }
    /**
     *เป็นฟังก์ชั่นที่ใช้ในการโชว์Dialog
     */
    public void showDialog(Patient patient,Visit visit,boolean list)
    {
        if(!list)
            theAppointment = theHO.initAppointment("");
        showDialog(patient,visit,list, theAppointment);
    }
   ////////////////////////////////////////////////////////////////////////////
    /**
     *เป็น Static Function
     *เพื่อทำการโชว์Dialog
     */
    public static boolean showDialog(HosControl hc,UpdateStatus us, boolean b){
        DialogAppointment dlg = new DialogAppointment(hc,us,b);
        dlg.setSize(740,450);
        dlg.setTitle(Constant.getTextBundle("การนัดหมาย"));
        Toolkit thekit = dlg.getToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        dlg.setLocation((screenSize.width-dlg.getSize().width)/2, (screenSize.height-dlg.getSize().height)/2);
        dlg.setVisible(true);
        if(dlg.actionCommand){
            return true;
        }
        dlg=null;
        System.gc();
        return false;
    }
    private void searchAppointment()
    {
        String datefrom = dateComboBoxDateFrom.getText();
        String dateto = dateComboBoxDateTo.getText();
        String sp = Gutil.getGuiData(jComboBoxSearchServicePoint);
        String sta = Gutil.getGuiData(jComboBoxSearchStatus);
        String active = "1";
        if(jCheckBoxShowCancel.isSelected())
        {
            active = "0";
        }
        boolean all_period = !jCheckBoxShowDatePeriod.isSelected();
        String pid = null;
        if(this.jCheckBoxSCurrPatient.isSelected() && theHO.thePatient!=null)
            pid = theHO.thePatient.getObjectId();
        vappointment =  thePatientControl.listAppointmentByDateSP(all_period
            ,datefrom,dateto,sp,pid,sta,active,this);
        setAppointmentV(vappointment);
    }
    /**
     *เมื่อทำการเลือก Appointment
     */
    public void setAppointment(Appointment app)
    {
        this.input_mode = false;
            jLabelCountAppointment.setText("");
            jLabelCountDoctor.setText("");
            jLabelCountSP.setText("");
        theAppointment = app;
        this.jCheckBoxToDate.setSelected(false);
        //-------------------------------------------------------------------------
        dateComboBoxDateAppointment.setText(DateUtil.convertFieldDate(theAppointment.appoint_date));
        timeTextFieldTimeAppointment.setText(theAppointment.appoint_time);
        jTextFieldApType.setText(theAppointment.aptype);
        Gutil.setGuiData(this.jComboBoxApType53,theAppointment.aptype53);
        Gutil.setGuiData(jComboBoxServicePoint,theAppointment.clinic_code);
        Gutil.setGuiData(jComboBoxDoctor,theAppointment.doctor_code);
        Gutil.setGuiData(jComboBoxStatus,theAppointment.status);
        jTextAreaDescription.setText(theAppointment.description);
        jCheckBoxAutoVisit.setSelected(theAppointment.auto_visit.equals("1"));
        if(theAppointment.patient_id!=null && !theAppointment.patient_id.equals(""))
        {
            Patient thePatient = thePatientControl.readPatientByPatientIdRet(theAppointment.patient_id,theAppointment.patient_id);
            if(thePatient!=null)//amp:2/8/2549
            {
                jTextFieldHN.setText(theLookupControl.getRenderTextHN(thePatient.hn));//amp:2/8/2549
                Prefix prefix = theHC.theLookupControl.readPrefixById(thePatient.f_prefix_id);
                String sPrefix = "";
                if(prefix!=null)
                    sPrefix = prefix.description;
                jTextFieldPatientName.setText(sPrefix + " "
                            + thePatient.patient_name + " " + thePatient.patient_last_name);
            }
            else
            {
                jTextFieldPatientName.setText(Constant.getTextBundle("ไม่พบข้อมูลผู้ป่วย"));
            }
        }
        else{
            jTextFieldPatientName.setText(Constant.getTextBundle("ไม่พบข้อมูลผู้ป่วย"));
        }
        if(theHO.vOldVisitPayment == null || theHO.vOldVisitPayment.isEmpty())
        {
            jTextFieldPlan.setText(Constant.getTextBundle("ไม่พบสิทธิการรักษาของการเข้ารับบริการครั้งก่อน"));
        }
        else
        {
            Payment pm = (Payment)theHO.vOldVisitPayment.get(0);
            Plan plan = theHC.theLookupControl.readPlanById(pm.plan_kid);
            if(plan != null)
                jTextFieldPlan.setText(plan.description);
            else
                jTextFieldPlan.setText(Constant.getTextBundle("ไม่พบสิทธิการรักษาของการเข้ารับบริการครั้งก่อน"));
        }
        jLabel7.setText("");
        if(used_queue_visit) {
            //QueueVisit qv = theVisitControl.readQueueVisitByQueueVisitID(theAppointment.queue_visit_id);
            QueueVisit qv = theLookupControl.readQueueVisitById(theAppointment.queue_visit_id);
            if(qv!=null) jLabel7.setText(qv.description);
        }
        // ถ้า theAppointment.vn ไม่เท่ากับ ช่องว่าง sumo 25/08/2549
        jLabelVN.setText(theLookupControl.getRenderTextVN(theAppointment.vn));
        boolean has_oid = theAppointment.getObjectId()!=null;
        this.jButtonPreviewAppointment.setEnabled(has_oid);
        this.jButtonPrintAppointment.setEnabled(has_oid);
        //สาเหตุการยกเลิกจะแสดงเมื่อ รายการนัดนี้มีสถานะเป็นยกเลิก
        jTextFieldCancel.setVisible(theAppointment.appoint_active.equals("0"));
        //amp:13/05/2549 เมื่อสถานะของนัดเป็นมาตามนัดแล้ว จะต้องเพิ่มหรือลบรายการ order ล่วงหน้าไม่ได้
        setEnabledAppointment(!showlist);
        if(theAppointment.status.equals("0"))
            this.jComboBoxStatus.setEnabled(true);
        this.input_mode = true;
//        this.jButtonSave.setEnabled(!theAppointment.isStatusClosed());
     }
    ////////////////////////////////////////////////////////////////////////////
    /**
     *ไม่ใช้แล้ว
     *check ว่าเป็นวันในอดีตหรือเปล่า
     *ข้อมูลเข้า: วันที่นัด, วันที่ปัจจุบัน
     *ข้อมูลออก: boolean
     */
//    private void checkDateAppointment()
//    {
//        /**
//         *dateApp: วันที่นัด
//         *today: วันที่ปัจจุบัน
//         *b: เก็บค่า boolean
//         */
//        Date dateApp = DateUtil.getDateFromText(dateComboBoxDateAppointment.getText());
//        Date today = DateUtil.getDateFromText(theLookupControl.getTextCurrentDate());
//        boolean b = DateUtil.beforeDate(dateApp,today);
//        if(b==false)
//            mod=false;
//        else
//            mod=true;
//    }
    ////////////////////////////////////////////////////////////////////////////
    /**
     *เซตค่าในตาราง Appointment
     */
    private void setAppointmentV(Vector vappointment)
    {
        TaBleModel tm ;
        if(vappointment == null || vappointment.isEmpty())
        {
            tm= new TaBleModel(collist,0);
            jTable1.setModel(tm);
            return;
        }
        tm = new TaBleModel(collist,vappointment.size());
        String appointment_date_time = "";
        String prefix_str = "";
        String pt_name = "";
        Prefix prefix;
        SpecialQueryAppointment spappointment;
        for(int i=0 ;i<vappointment.size(); i++)
        {
            spappointment = (SpecialQueryAppointment)vappointment.get(i);
            prefix = this.theLookupControl.readPrefixById(spappointment.patient_prefix);
            prefix_str = "";
            if(prefix!=null)  prefix_str = prefix.description;
            pt_name = prefix_str + " "
                + spappointment.patient_firstname + " "
                + spappointment.patient_lastname;
            tm.setValueAt(spappointment.patient_hn,i,0);
            tm.setValueAt(pt_name,i,1);
            if("".equals(spappointment.patient_appointment_time))
            {
                tm.setValueAt(DateUtil.getDateFromText(spappointment.patient_appointment_date),i,2);
            }
            else
            {
                tm.setValueAt(DateUtil.getDateFromText(spappointment.patient_appointment_date +
                        "," + spappointment.patient_appointment_time),i,2);
            }
            tm.setValueAt(spappointment.service_point_description,i,3);
            tm.setValueAt(spappointment.patient_appointment_status,i,4);
        }
        jTable1.setModel(tm);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(hnRender);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(90);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(dateRender);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(25);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(appointmentRender);
    }
    /**
     *@Author : amp
     *@date : 23/02/2549
     *@see : แสดงรายการ item ที่ค้นเจอ
     */
    private void setItemV(Vector vc)
    {
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
        jTableItem.getColumnModel().getColumn(0).setCellRenderer(theCellRendererTooltip);
    }
    /**
     *@Author: amp
     *@date: 23/02/2549
     *@see: แสดงรายการ order ที่สั่งล่วงหน้า
     */
    private void setAppointmentOrderV(Vector vc)
    {
        vAppointmentOrder = vc;
        String[] column = {GuiLang.setLanguage("รายการตรวจรักษาล่วงหน้า")};
        TaBleModel tm;
        if(vc != null)
        {
            tm = new TaBleModel(column,vc.size());
            for(int i=0,size=vc.size(); i<size; i++)
            {
                AppointmentOrder apor = (AppointmentOrder)vc.get(i);
                tm.setValueAt(apor.item_common_name,i,0);
            }
            jTableAppointmentOrder.setModel(tm);
            jTableAppointmentOrder.getColumnModel().getColumn(0).setCellRenderer(theCellRendererTooltip);
        }
        else
        {
            tm = new TaBleModel(column,0);
            jTableAppointmentOrder.setModel(tm);
        }

    }
    /**
     *@Author: amp
     *@date: 9/8/2549
     *@see: แสดงชุดล่วงหน้า
     *@param: Vector ของชุดล่วงหน้า
     */
    private void setHelpItemV(Vector vc)
    {
        String[] column = {GuiLang.setLanguage("รายการตรวจรักษาล่วงหน้า")};
        TaBleModel tm;
        if(vc != null)
        {
            tm = new TaBleModel(column,vc.size());
            for(int i=0,size=vc.size(); i<size; i++)
            {
                AppointmentOrder apor = (AppointmentOrder)vc.get(i);
                tm.setValueAt(apor.item_common_name,i,0);
            }
            jTableAppointmentOrder.setModel(tm);
        }
        else
        {
            tm = new TaBleModel(column,0);
            jTableAppointmentOrder.setModel(tm);
        }
    }
    /**
     *@Authur: amp
     *@date: 7/8/2549
     *@see: คำนวณจำนวนการนัด
     */
    private void countAppointment(int show)
    {
        if(!input_mode)
            return ;
            String date_appointment = dateComboBoxDateAppointment.getText();
        if(show==1){
            jLabelCountAppointment.setText("");
            if("".equals(date_appointment))
                return;
            jLabelCountAppointment.setText(theHC.thePatientControl.countAppointment(date_appointment));
        }
        else if(show==2){
             jLabelCountDoctor.setText("");
            String doctor = ComboboxModel.getCodeComboBox(jComboBoxDoctor);
            if("".equals(doctor))//จำนวนของแพทย์
                return;
            jLabelCountDoctor.setText(theHC.thePatientControl.countDoctor(date_appointment,doctor));
        }
        else if(show==3){
            jLabelCountSP.setText("");
            String sp = ComboboxModel.getCodeComboBox(this.jComboBoxServicePoint);
            //จำนวนของวัน
            if(sp.equals("")) //จำนวนของจุดบริการ
                return;
            jLabelCountSP.setText(theHC.thePatientControl.countAppointmentSP(date_appointment,sp));
        }
        else if(show==4){
            jLabelCountTime.setText("");
            if(timeTextFieldTimeAppointment.getText().equals(""))
                return;
            String count = theHC.thePatientControl.countAppointmentTime(
                    date_appointment
                    ,this.timeTextFieldTimeAppointment.getText()
                    ,ComboboxModel.getCodeComboBox(this.jComboBoxDoctor));
            jLabelCountTime.setText(count);
        }
    }
    /**
     *@Author: amp
     *@date: 8/8/2549
     *@see: แสดงวันที่จะนัดโดยคำนวณจาก week ที่เลือก
     *@param: จำนวน week
     */
    private void calDateByWeek(int week)
    {
        dateComboBoxDateAppointment.setText(DateUtil.calDateByWeek(week));
    }
    /**
     *@Author: amp
     *@date: 8/8/2549
     *@see: แสดงถึงวันที่ โดยคำนวณจาก week ที่เลือก นับจากวันที่นัด
     *@param: จำนวน week
     */
    private void calDateByWeekAndDate(int week)
    {
        String date_appintment = dateComboBoxDateAppointment.getText();
        if("".equals(date_appintment))
        {
            //setStatus(Constant.getTextBundle("ยังไม่ระบุวันที่นัด"),UpdateStatus.WARNING);
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
        setItemV(vItem);
    }
    /**
     *@Author: amp
     *@date: 10/08/2549
     *@see: แสดงค่าเริ่มต้นการนัดจากตัวช่วย
     *@param: key_id ตัวช่วยนัดที่เลือก
     */
    private void useAppointmentTemplate(String appointment_template_id)
    {
        if(!"".equals(appointment_template_id))
        {
            Hashtable ht = new Hashtable();
            ht = theHC.thePatientControl.listAppointmentTemplateAndItem(appointment_template_id);
            initByAppointmentTemplate((AppointmentTemplate)ht.get("AppointmentTemplate"));
            setAppointment(theAppointment);
            initByVAppointmentTemplateItem((Vector)ht.get("vAppointmentTemplateItem"));
        }
    }
    /**
     *@Author: amp
     *@date: 10/08/2549
     *@see: set ค่าเริ่มต้นให้กับ Appointment จาก AppointmentTemplate
     *@param: AppointmentTemplate
     */
    private void initByAppointmentTemplate(AppointmentTemplate theAppointmentTemplate)
    {
        theQueueVisit = null;
        if(theAppointment==null)
        {
            vAppointment = new Vector();
            this.jTable1.clearSelection();
            theAppointment = new Appointment2();
            if(theHO.thePatient!=null)
                theAppointment.patient_id = theHO.thePatient.getObjectId();
            if(theHO.theVisit!=null)
                theAppointment.visit_id = theHO.theVisit.getObjectId();
        }
        theAppointment.date_serv = theHC.theLookupControl.getTextCurrentDateTime();
        theAppointment.appoint_date = theAppointmentTemplate.date;
        if(!theAppointmentTemplate.date_to.equals(""))
        {
            dateComboBoxDateAppointment2.setText(DateUtil.convertFieldDate(theAppointmentTemplate.date_to));
            this.jCheckBoxToDate.setSelected(true);
        }
        theAppointment.appoint_time = theAppointmentTemplate.time;
        theAppointment.aptype = theAppointmentTemplate.aptype;
        theAppointment.clinic_code = theAppointmentTemplate.service_point;
        theAppointment.description = theAppointmentTemplate.description;
        theAppointment.queue_visit_id = theAppointmentTemplate.queue_visit_id;
        theAppointment.doctor_code = theAppointmentTemplate.doctor;
        theAppointment.status = AppointmentStatus.WAIT;
        theAppointment.auto_visit = "1";
    }
    /**
     *@Author: amp
     *@date: 10/08/2549
     *@see: set ค่าเริ่มต้นให้กับ vAppointmentOrder จาก vAppointmentTemplateItem
     *@param: vAppointmentTemplateItem
     */
    private void initByVAppointmentTemplateItem(Vector vAppointmentTemplateItem)
    {
        Constant.println("______________________initByVAppointmentTemplateItem");
        if(vAppointmentTemplateItem == null)
        {
            setAppointmentOrderV(null);
            return;
        }
        AppointmentTemplateItem apti;
        AppointmentOrder apo;
        vAppointmentOrder = new Vector();
        for(int i=0,size=vAppointmentTemplateItem.size(); i<size; i++)
        {
            apo = new AppointmentOrder();
            apti = (AppointmentTemplateItem)vAppointmentTemplateItem.get(i);
            apo.item_common_name = apti.item_common_name;
            apo.item_id = apti.item_id;
            apo.patient_id = theHO.thePatient.getObjectId();
            vAppointmentOrder.addElement(apo);
        }
        setAppointmentOrderV(vAppointmentOrder);
    }
      public void notifyPrintAppointmentList(String str, int status) {
      }
      public void notifyPreviewAppointmentList(String str, int status) {
      }
      public void notifyPrintDrugSticker(String str, int status) {
      }
      public void notifyPrintOPDCard(String str, int status) {
      }
      public void notifyPrintChronicList(String str, int status) {
      }
      public void notifyPriviewChronicList(String str, int status) {
      }
      public void notifyPreviewSelectDrugList(String str, int status) {
      }
      public void notifyPreviewSumByBillingGroup(String str, int status) {
      }
      public void notifyPrintSelectDrugList(String str, int status) {
      }
      public void notifyPrintSumByBillingGroup(String str, int status) {
      }
      public void notifyDeletePatient(String str, int status) {
      }
      public void notifyDeletePatientPayment(String str, int status) {
      }
      public void notifyManageDrugAllergy(String str, int status) {
      }
      public void notifyReadPatient(String str, int status) {
      }
      public void notifyReadFamily(String str, int status) {
      }
      public void notifyResetPatient(String str, int status) {
      }
      public void notifySaveAppointment(String str, int status)
      {
          setStatus(str,status);
//          hosComboBoxStdAppointmentTemplate.refresh();
      }
      public void notifySavePatient(String str, int status) {
      }
      public void notifySavePatientPayment(String str, int status) {
      }


    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.ButtonGroup buttonGroup1;

    private com.hospital_os.utility.DateComboBox dateComboBoxDateAppointment;

    private com.hospital_os.utility.DateComboBox dateComboBoxDateAppointment2;

    private com.hospital_os.utility.DateComboBox dateComboBoxDateFrom;

    private com.hospital_os.utility.DateComboBox dateComboBoxDateTo;

    private com.hospital_os.gui.font.DefaultFont defaultFont1;

    private com.hosv3.gui.component.HosComboBoxStd hosComboBoxStdAppointmentTemplate;

    private javax.swing.JButton jButton1Week;

    private javax.swing.JButton jButton2;

    private javax.swing.JButton jButton2Week;

    private javax.swing.JButton jButton3;

    private javax.swing.JButton jButton3Week;

    private javax.swing.JButton jButton4;

    private javax.swing.JButton jButton4Week;

    private javax.swing.JButton jButton6Week;

    private javax.swing.JButton jButton8Week;

    private javax.swing.JButton jButtonAdd;

    private javax.swing.JButton jButtonAdd1Week;

    private javax.swing.JButton jButtonAdd4Week;

    private javax.swing.JButton jButtonAddOrder;

    private javax.swing.JButton jButtonAutoVisit;

    private javax.swing.JButton jButtonDM;

    private javax.swing.JButton jButtonDel;

    private javax.swing.JButton jButtonDelOrder;

    private javax.swing.JButton jButtonPreviewAppointment;

    private javax.swing.JButton jButtonPrintAppointment;

    private javax.swing.JButton jButtonPrintListAppointment;

    private javax.swing.JButton jButtonPrintListAppointment1;

    private javax.swing.JButton jButtonQueueVisit;

    private javax.swing.JButton jButtonSave;

    private javax.swing.JButton jButtonSearch;

    private javax.swing.JButton jButtonTime1000;

    private javax.swing.JButton jButtonTime1100;

    private javax.swing.JButton jButtonTime1200;

    private javax.swing.JButton jButtonTime1300;

    private javax.swing.JButton jButtonTime1400;

    private javax.swing.JButton jButtonTime700;

    private javax.swing.JButton jButtonTime730;

    private javax.swing.JButton jButtonTime800;

    private javax.swing.JButton jButtonTime830;

    private javax.swing.JButton jButtonTime900;

    private javax.swing.JButton jButtonTo1Week;

    private javax.swing.JButton jButtonTo2Week;

    private javax.swing.JButton jButtonTo3Week;

    private javax.swing.JCheckBox jCheckBoxAutoVisit;

    private javax.swing.JCheckBox jCheckBoxSCurrPatient;

    private javax.swing.JCheckBox jCheckBoxShowCancel;

    private javax.swing.JCheckBox jCheckBoxShowDatePeriod;

    private javax.swing.JCheckBox jCheckBoxToDate;

    private com.hosv3.gui.component.HosComboBox jComboBoxApType53;

    private javax.swing.JComboBox jComboBoxDoctor;

    private javax.swing.JComboBox jComboBoxSearchServicePoint;

    private javax.swing.JComboBox jComboBoxSearchStatus;

    private javax.swing.JComboBox jComboBoxServicePoint;

    private javax.swing.JComboBox jComboBoxStatus;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabel10;

    private javax.swing.JLabel jLabel11;

    private javax.swing.JLabel jLabel12;

    private javax.swing.JLabel jLabel13;

    private javax.swing.JLabel jLabel14;

    private javax.swing.JLabel jLabel15;

    private javax.swing.JLabel jLabel16;

    private javax.swing.JLabel jLabel18;

    private javax.swing.JLabel jLabel19;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel20;

    private javax.swing.JLabel jLabel21;

    private javax.swing.JLabel jLabel22;

    private javax.swing.JLabel jLabel23;

    private javax.swing.JLabel jLabel24;

    private javax.swing.JLabel jLabel25;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JLabel jLabel4;

    private javax.swing.JLabel jLabel5;

    private javax.swing.JLabel jLabel6;

    private javax.swing.JLabel jLabel7;

    private javax.swing.JLabel jLabel8;

    private javax.swing.JLabel jLabel9;

    private javax.swing.JLabel jLabelCountAppointment;

    private javax.swing.JLabel jLabelCountDoctor;

    private javax.swing.JLabel jLabelCountSP;

    private javax.swing.JLabel jLabelCountTime;

    private javax.swing.JLabel jLabelHide1;

    private javax.swing.JLabel jLabelStatus;

    private javax.swing.JLabel jLabelVN;

    private javax.swing.JPanel jPanel1;

    private javax.swing.JPanel jPanel10;

    private javax.swing.JPanel jPanel11;

    private javax.swing.JPanel jPanel2;

    private javax.swing.JPanel jPanel22;

    private javax.swing.JPanel jPanel24;

    private javax.swing.JPanel jPanel25;

    private javax.swing.JPanel jPanel26;

    private javax.swing.JPanel jPanel27;

    private javax.swing.JPanel jPanel3;

    private javax.swing.JPanel jPanel4;

    private javax.swing.JPanel jPanel5;

    private javax.swing.JPanel jPanel6;

    private javax.swing.JPanel jPanel8;

    private javax.swing.JPanel jPanelHide0;

    private javax.swing.JPanel jPanelHide2;

    private javax.swing.JPanel jPanelHide5;

    private javax.swing.JPanel jPanelHide6;

    private javax.swing.JPanel jPanelHide7;

    private javax.swing.JPanel jPanelSearch;

    private javax.swing.JPanel jPanelTime;

    protected javax.swing.JScrollPane jScrollPane1;

    private javax.swing.JScrollPane jScrollPane2;

    private javax.swing.JScrollPane jScrollPane5;

    private javax.swing.JScrollPane jScrollPane6;

    protected com.hosv3.gui.component.HJTableSort jTable1;

    private com.hosv3.gui.component.HJTableSort jTableAppointmentOrder;

    private com.hosv3.gui.component.HJTableSort jTableItem;

    private com.hosv3.gui.component.BalloonTextArea jTextAreaDescription;

    private com.hosv3.gui.component.BalloonTextField jTextFieldApType;

    private javax.swing.JLabel jTextFieldCancel;

    private javax.swing.JLabel jTextFieldHN;

    private javax.swing.JLabel jTextFieldPatientName;

    private javax.swing.JLabel jTextFieldPlan;

    private javax.swing.JTextField jTextFieldSearchOrder;

    private com.hospital_os.utility.TimeTextField timeTextFieldTimeAppointment;

    // End of variables declaration//GEN-END:variables


    public static void main(String[] argc){
        Constant.println(DateUtil.convertFieldDate("2524-12-12"));
    }
    public void notifySaveBorrowFilmXray(String str, int status) {
    }
}
