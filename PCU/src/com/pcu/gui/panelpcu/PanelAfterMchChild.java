

/*

 * JPanelFp.java

 *

 * Created on 13 มิถุนายน 2548, 18:24 น.

 */

/*

 * ตรวจสอบวันที่สำรวจแล้ว

 * kingland

 */

package com.pcu.gui.panelpcu;


import com.pcu.control.AfterMCHMotherControl;
import com.pcu.control.HosManage;
import com.pcu.control.PCUObject;
import com.pcu.gui.dialog.HosDialog;
import com.pcu.object.PP;

import javax.swing.*;
import java.util.Vector;
import com.pcu.utility.*;
import com.pcu.object.PPCare;
import com.pcu.control.AllComboBoxControl;
import com.pcu.control.HealthSchoolServiceControl;
import com.hosv3.usecase.transaction.*;
import com.hospital_os.object.*;
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.TableRenderer;
import com.pcu.object.Family;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.object.FamilyPlaningTreatment;
import com.pcu.object.PcuAnswer;
import com.pcu.object.PostpartumBirthPlace;
import com.pcu.subject.ManagePPResp;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;
/**

 *

 * @author  amp

 */

public class PanelAfterMchChild extends javax.swing.JPanel
        implements ManagePatientResp ,PanelObj ,ManagePPResp{

    int rowPPCare;

    int receiveNotify = 4;

    boolean checkSurvey = false;

    JFrame theFrame;

    private AllComboBoxControl theAllComboBoxControl;

    private HealthSchoolServiceControl theHealthSchoolServiceControl;

    private HosDialog theHosDialog;

    private HosManage theHosManage;

    public Vector vPPCare;

    private PPCare thePPCare;

    private PCUObject pcuobject;

    private Family theFamily;

    private Patient thePatient;

    private Appointment theAppointment;

    private UpdateStatus theUS;

    private AfterMCHMotherControl theAfterMCHMotherControl;

    public Vector vChildren;

    public PP pp_child;

    private String[] col = new String[]{"คนที่","วันคลอด"};
            String[] colPPCare = new String[]{"CareTime","DateRecord"};
    private JDialog theJD;
    PanelAfterMchMother thePanelAfterMchMother;
    PanelPP thePanelPP;

    /** Creates new form JPanelFp */

    public PanelAfterMchChild(){

        initComponents();
        jButton1.setVisible(false);
        jButtonContinue.setVisible(false);
    }
    public PanelAfterMchChild(HosManage hm,HosDialog hd,UpdateStatus us)
    {
        initComponents();
        setControl(hm,hd,us);
    }
    public void setPanelPP(PanelPP panelPP)
    {
        thePanelPP = panelPP;
    }
    public void setPanelAfterMchMother(PanelAfterMchMother panelAfterMchMother)
    {
        thePanelAfterMchMother = panelAfterMchMother;
    }
    public void setControl(HosManage hm,HosDialog hd,UpdateStatus us)

    {
        theUS = us;
        theHosManage = hm;

        theHosDialog = hd;
        pcuobject = hm.thePO;

//        hm.theHosControl.theHcHospitalOS.theHS.thePatientSubject.attachManagePatient(this);

        theAllComboBoxControl = hm.theHosControl.theAllComboBoxControl;
        theAfterMCHMotherControl = hm.theHosControl.theAfterMCHMotherControl;
        theHealthSchoolServiceControl = hm.theHosControl.theHealthSchoolServiceControl;
        setLanguage();

        initDatas();
        theHosManage.theHosSubject.thePPSubject.attachApgarScore(this);
        setEnabled(false);

    }
    public void showDialog(boolean b)
    {
        if(theJD==null)
            theJD = new JDialog(theUS.getJFrame());
        theJD.add(this);
        theJD.setSize(720,490);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, (screenSize.height - theJD.getSize().height) / 2);
        theJD.setTitle("ดูแลเด็กหลังคลอด");
        theJD.setModal(true);
        jButtonContinue.setVisible(b);
        jButton1.setVisible(b);
        theJD.setModal(true);
        theJD.setVisible(true);
    }
    /**

     *กำหนดค่าเริ่มต้นให้กับ Balloon

     *@param -

     *@return void

     *@author amp

     *@modify kingland

     *@date 04/09/2549

     */

    private void initBalloon() {

        theHosManage.theHosControl.balloon.add(jTextAreaNoteResult);

        jTextAreaNoteResult.setControl(theHosManage.theHosInf.getVitalTemplate());

        jTextAreaNoteResult.setJFrame(getJFrame());

    }

    /**

     *เซตค่าเริ่มต้นให้กับCombobox

     *@param -

     *@return void

     *@author amp

     *@modify kingland

     *@date 04/09/2549

     */

    private void initDatas() {

        ComboboxModel.initComboBox(jComboBoxCheckPlace,theAllComboBoxControl.listPostpartumBirthPlace());

    }

    /**

     *เซต Object ข้อมูล

     *@param PCUObject

     *@return void

     *@author amp

     *@modify kingland

     *@date 04/09/2549

     */

    public void setObject(PCUObject pcuo) {

        System.out.println("_henbe______________________________" + getClass().toString());
        pcuobject = pcuo;
        theFamily = pcuobject.getFamily();
        thePatient = pcuobject.getPatient();
        this.setEnabled(true);
        /** ไปจัดการต่อเรื่องของข้อมูลที่จะรับ ถ้าเมื่อไรไม่มีข้อมูล นั้นจะทำอย่างไรบนหน้า GUI */
        dateComboBoxSurvey.setText(Gutil.convertFieldDate(pcuobject.getCurrentDateTime()));
        
        setPPCare(null);
        setEnableGui(true);
        
        if(!checkPatientAndFamily()) 
            setEnabled(false);

        else if(checkDead()) 
            setEnabled(false);

        Vector v = new Vector();
        if(thePatient!=null){
            v = theAfterMCHMotherControl.listPPByMotherPtid(thePatient.getObjectId());
        }
        this.setPPV(v);
        jScrollPaneListChild.setVisible(!v.isEmpty());
        jCheckBox1.setVisible(!v.isEmpty());
        if(v.isEmpty()){
            Vector vppcare =  theAfterMCHMotherControl.listPPCareByFamilyID(pcuobject.getFamily().getObjectId());
            setPPCareV(vppcare);
        }
    }
    
    private void setPPV(Vector vector)
    {
        vChildren = vector;
        TableModel tm = new TableModel(col,vChildren.size());
        for(int i=0 ;i<vChildren.size(); i++)  {  
            PP vs = (PP)vChildren.get(i);            
            tm.setValueAt(GutilPCU.changDateToString(vs.pp_record_date_time,false),i,1);
            tm.setValueAt(vs.pp_number,i,0);
        }
        jTablePP.setModel(tm); 
        jTablePP.getColumnModel().getColumn(1).setPreferredWidth(400);
        jTablePP.getColumnModel().getColumn(1).setCellRenderer(TableRenderer.getRendererCenter());
        jTablePP.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTablePP.getColumnModel().getColumn(0).setCellRenderer(TableRenderer.getRendererRight());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroupPPCareResult = new javax.swing.ButtonGroup();
        buttonGroupHealth = new javax.swing.ButtonGroup();
        buttonGroupSkin = new javax.swing.ButtonGroup();
        buttonGroupNavel = new javax.swing.ButtonGroup();
        buttonGroupStool = new javax.swing.ButtonGroup();
        buttonGroupUrine = new javax.swing.ButtonGroup();
        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jPanelAfterMchChildList = new javax.swing.JPanel();
        jScrollPaneAfterChildList = new javax.swing.JScrollPane();
        jTablePPCare = new javax.swing.JTable();
        jScrollPaneListChild = new javax.swing.JScrollPane();
        jTablePP = new javax.swing.JTable();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanelAfterMchChildDetail = new javax.swing.JPanel();
        jPanelAfterMchChildService = new javax.swing.JPanel();
        jLabelCheckChild = new javax.swing.JLabel();
        jLabelHealth = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButtonBirthPlace = new javax.swing.JButton();
        integerTextFieldBirthPlace = new com.pcu.utility.IntegerTextField();
        jTextFieldBirthPlace = new javax.swing.JTextField();
        jRadioButtonWeak = new javax.swing.JRadioButton();
        jRadioButtonStrong = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jButtonAppointment = new javax.swing.JButton();
        jLabelNextAppDate = new javax.swing.JLabel();
        jLabelAppointment = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabelMomAge = new javax.swing.JLabel();
        integerTextFieldCareTime = new com.pcu.utility.IntegerTextField();
        jPanel6 = new javax.swing.JPanel();
        integerTextFieldMomAge = new com.pcu.utility.IntegerTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxCheckPlace = new javax.swing.JComboBox();
        jLabelBirthPlace = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelSkin = new javax.swing.JLabel();
        jRadioButtonSkinAbNormal = new javax.swing.JRadioButton();
        jRadioButtonSkinNormal = new javax.swing.JRadioButton();
        jLabelStool = new javax.swing.JLabel();
        jRadioButtonStoolAbnormal = new javax.swing.JRadioButton();
        jRadioButtonStoolNormal = new javax.swing.JRadioButton();
        jLabeNavelChild = new javax.swing.JLabel();
        jRadioButtonNavelAbnormal = new javax.swing.JRadioButton();
        jRadioButtonNavelNormal = new javax.swing.JRadioButton();
        jLabelUrine = new javax.swing.JLabel();
        jRadioButtonUrineAbnormal = new javax.swing.JRadioButton();
        jRadioButtonUrineNormal = new javax.swing.JRadioButton();
        jLabelSurveyDate = new javax.swing.JCheckBox();
        dateComboBoxSurvey = new com.pcu.utility.DateComboBox();
        jPanelResultAfterMchChild = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaNoteResult = new com.hosv3.gui.component.BalloonTextArea();
        jPanel7 = new javax.swing.JPanel();
        jRadioButtonNoTreat = new javax.swing.JRadioButton();
        jRadioButtonNormal = new javax.swing.JRadioButton();
        jRadioButtonAbnormal = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jTextAreaNoteRemark = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        dateComboBoxCheck = new com.hospital_os.utility.DateComboBox();
        jLabel6 = new javax.swing.JLabel();
        timeTextFieldCheck = new com.hospital_os.utility.TimeTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabelVN = new javax.swing.JLabel();
        jPanelAfterMchChildControl = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jButtonContinue = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(700, 400));
        setPreferredSize(new java.awt.Dimension(700, 400));
        setRequestFocusEnabled(false);
        setLayout(new java.awt.GridBagLayout());

        jPanelAfterMchChildList.setMinimumSize(new java.awt.Dimension(200, 120));
        jPanelAfterMchChildList.setOpaque(false);
        jPanelAfterMchChildList.setPreferredSize(new java.awt.Dimension(200, 120));
        jPanelAfterMchChildList.setLayout(new java.awt.GridBagLayout());

        jScrollPaneAfterChildList.setEnabled(false);
        jScrollPaneAfterChildList.setMinimumSize(new java.awt.Dimension(260, 23));
        jScrollPaneAfterChildList.setPreferredSize(new java.awt.Dimension(200, 100));

        jTablePPCare.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTablePPCare.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTablePPCareMouseReleased(evt);
            }
        });
        jScrollPaneAfterChildList.setViewportView(jTablePPCare);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelAfterMchChildList.add(jScrollPaneAfterChildList, gridBagConstraints);

        jScrollPaneListChild.setMinimumSize(new java.awt.Dimension(22, 82));

        jTablePP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTablePPMouseReleased(evt);
            }
        });
        jScrollPaneListChild.setViewportView(jTablePP);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelAfterMchChildList.add(jScrollPaneListChild, gridBagConstraints);

        jCheckBox1.setText("แสดงทั้งหมด");
        jCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelAfterMchChildList.add(jCheckBox1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        add(jPanelAfterMchChildList, gridBagConstraints);

        jPanelAfterMchChildDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("AfterMchChild_Detail"));
        jPanelAfterMchChildDetail.setLayout(new java.awt.GridBagLayout());

        jPanelAfterMchChildService.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanelAfterMchChildService.setLayout(new java.awt.GridBagLayout());

        jLabelCheckChild.setText("CheckPlace");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelAfterMchChildService.add(jLabelCheckChild, gridBagConstraints);

        jLabelHealth.setText("Health");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanelAfterMchChildService.add(jLabelHealth, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButtonBirthPlace.setText("...");
        jButtonBirthPlace.setMaximumSize(new java.awt.Dimension(21, 21));
        jButtonBirthPlace.setMinimumSize(new java.awt.Dimension(21, 21));
        jButtonBirthPlace.setPreferredSize(new java.awt.Dimension(21, 21));
        jButtonBirthPlace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBirthPlaceActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel1.add(jButtonBirthPlace, gridBagConstraints);

        integerTextFieldBirthPlace.setBackground(new java.awt.Color(204, 255, 255));
        integerTextFieldBirthPlace.setColumns(5);
        integerTextFieldBirthPlace.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldBirthPlace.setMinimumSize(new java.awt.Dimension(50, 21));
        integerTextFieldBirthPlace.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldBirthPlaceKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(integerTextFieldBirthPlace, gridBagConstraints);

        jTextFieldBirthPlace.setBackground(new java.awt.Color(204, 255, 255));
        jTextFieldBirthPlace.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel1.add(jTextFieldBirthPlace, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelAfterMchChildService.add(jPanel1, gridBagConstraints);

        buttonGroupHealth.add(jRadioButtonWeak);
        jRadioButtonWeak.setText("Weak");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelAfterMchChildService.add(jRadioButtonWeak, gridBagConstraints);

        buttonGroupHealth.add(jRadioButtonStrong);
        jRadioButtonStrong.setSelected(true);
        jRadioButtonStrong.setText("Strong");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelAfterMchChildService.add(jRadioButtonStrong, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jButtonAppointment.setText("นัด");
        jButtonAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAppointmentActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(jButtonAppointment, gridBagConstraints);

        jLabelNextAppDate.setText("NextAppDate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(jLabelNextAppDate, gridBagConstraints);

        jLabelAppointment.setText("วันนัด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(jLabelAppointment, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelAfterMchChildService.add(jPanel4, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabelMomAge.setText("MomAge");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel5.add(jLabelMomAge, gridBagConstraints);

        integerTextFieldCareTime.setBackground(new java.awt.Color(204, 255, 255));
        integerTextFieldCareTime.setColumns(2);
        integerTextFieldCareTime.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldCareTime.setMinimumSize(new java.awt.Dimension(30, 21));
        integerTextFieldCareTime.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                integerTextFieldCareTimeFocusLost(evt);
            }
        });
        integerTextFieldCareTime.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldCareTimeKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(integerTextFieldCareTime, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        integerTextFieldMomAge.setColumns(3);
        integerTextFieldMomAge.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldMomAge.setMinimumSize(new java.awt.Dimension(42, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel6.add(integerTextFieldMomAge, gridBagConstraints);

        jLabel3.setText("Year");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel6.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel5.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelAfterMchChildService.add(jPanel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanelAfterMchChildService.add(jComboBoxCheckPlace, gridBagConstraints);

        jLabelBirthPlace.setText("BirthPlace");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelAfterMchChildService.add(jLabelBirthPlace, gridBagConstraints);

        jLabel1.setText("CareTime");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelAfterMchChildService.add(jLabel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabelSkin.setText("Skin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jLabelSkin, gridBagConstraints);

        buttonGroupSkin.add(jRadioButtonSkinAbNormal);
        jRadioButtonSkinAbNormal.setText("Abnormal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jRadioButtonSkinAbNormal, gridBagConstraints);

        buttonGroupSkin.add(jRadioButtonSkinNormal);
        jRadioButtonSkinNormal.setSelected(true);
        jRadioButtonSkinNormal.setText("Normal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jRadioButtonSkinNormal, gridBagConstraints);

        jLabelStool.setText("Stool");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jLabelStool, gridBagConstraints);

        buttonGroupStool.add(jRadioButtonStoolAbnormal);
        jRadioButtonStoolAbnormal.setText("Abnormal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jRadioButtonStoolAbnormal, gridBagConstraints);

        buttonGroupStool.add(jRadioButtonStoolNormal);
        jRadioButtonStoolNormal.setSelected(true);
        jRadioButtonStoolNormal.setText("Normal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jRadioButtonStoolNormal, gridBagConstraints);

        jLabeNavelChild.setText("NavelChild");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel2.add(jLabeNavelChild, gridBagConstraints);

        buttonGroupNavel.add(jRadioButtonNavelAbnormal);
        jRadioButtonNavelAbnormal.setText("Abnormal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jRadioButtonNavelAbnormal, gridBagConstraints);

        buttonGroupNavel.add(jRadioButtonNavelNormal);
        jRadioButtonNavelNormal.setSelected(true);
        jRadioButtonNavelNormal.setText("Normal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jRadioButtonNavelNormal, gridBagConstraints);

        jLabelUrine.setText("Urine");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel2.add(jLabelUrine, gridBagConstraints);

        buttonGroupUrine.add(jRadioButtonUrineAbnormal);
        jRadioButtonUrineAbnormal.setText("Abnormal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jRadioButtonUrineAbnormal, gridBagConstraints);

        buttonGroupUrine.add(jRadioButtonUrineNormal);
        jRadioButtonUrineNormal.setSelected(true);
        jRadioButtonUrineNormal.setText("Normal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jRadioButtonUrineNormal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelAfterMchChildService.add(jPanel2, gridBagConstraints);

        jLabelSurveyDate.setText("SurveyDate");
        jLabelSurveyDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jLabelSurveyDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabelSurveyDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAfterMchChildService.add(jLabelSurveyDate, gridBagConstraints);

        dateComboBoxSurvey.setEnabled(false);
        dateComboBoxSurvey.setMinimumSize(new java.awt.Dimension(100, 24));
        dateComboBoxSurvey.setText("");
        dateComboBoxSurvey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxSurveyActionPerformed(evt);
            }
        });
        dateComboBoxSurvey.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateComboBoxSurveyFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelAfterMchChildService.add(dateComboBoxSurvey, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 11);
        jPanelAfterMchChildDetail.add(jPanelAfterMchChildService, gridBagConstraints);

        jPanelResultAfterMchChild.setBorder(javax.swing.BorderFactory.createTitledBorder("PPCareResult"));
        jPanelResultAfterMchChild.setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportView(jTextAreaNoteResult);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelResultAfterMchChild.add(jPanel3, gridBagConstraints);

        jPanel7.setBackground(new java.awt.Color(204, 255, 255));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jRadioButtonNoTreat.setBackground(new java.awt.Color(204, 255, 255));
        buttonGroupPPCareResult.add(jRadioButtonNoTreat);
        jRadioButtonNoTreat.setSelected(true);
        jRadioButtonNoTreat.setText("NoTreat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel7.add(jRadioButtonNoTreat, gridBagConstraints);

        jRadioButtonNormal.setBackground(new java.awt.Color(204, 255, 255));
        buttonGroupPPCareResult.add(jRadioButtonNormal);
        jRadioButtonNormal.setText("Normal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel7.add(jRadioButtonNormal, gridBagConstraints);

        jRadioButtonAbnormal.setBackground(new java.awt.Color(204, 255, 255));
        buttonGroupPPCareResult.add(jRadioButtonAbnormal);
        jRadioButtonAbnormal.setText("Abnormal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel7.add(jRadioButtonAbnormal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelResultAfterMchChild.add(jPanel7, gridBagConstraints);

        jLabel2.setText("Remark");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelResultAfterMchChild.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelResultAfterMchChild.add(jTextAreaNoteRemark, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 11);
        jPanelAfterMchChildDetail.add(jPanelResultAfterMchChild, gridBagConstraints);

        jPanel16.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("วันที่บันทึก");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel16.add(jLabel5, gridBagConstraints);

        dateComboBoxCheck.setBackground(new java.awt.Color(204, 255, 255));
        dateComboBoxCheck.setMinimumSize(new java.awt.Dimension(100, 24));
        dateComboBoxCheck.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dateComboBoxCheckKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 3);
        jPanel16.add(dateComboBoxCheck, gridBagConstraints);

        jLabel6.setText("น.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 1);
        jPanel16.add(jLabel6, gridBagConstraints);

        timeTextFieldCheck.setBackground(new java.awt.Color(204, 255, 255));
        timeTextFieldCheck.setMinimumSize(new java.awt.Dimension(45, 20));
        timeTextFieldCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timeTextFieldCheckMouseClicked(evt);
            }
        });
        timeTextFieldCheck.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                timeTextFieldCheckKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 3);
        jPanel16.add(timeTextFieldCheck, gridBagConstraints);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/clock.gif"))); // NOI18N
        jLabel9.setToolTipText("เวลาที่ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 3);
        jPanel16.add(jLabel9, gridBagConstraints);

        jLabelVN.setText("VN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel16.add(jLabelVN, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 0);
        jPanelAfterMchChildDetail.add(jPanel16, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 5);
        add(jPanelAfterMchChildDetail, gridBagConstraints);

        jPanelAfterMchChildControl.setLayout(new java.awt.GridBagLayout());

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelAfterMchChildControl.add(jButtonSave, gridBagConstraints);

        jButtonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDelete.setMaximumSize(new java.awt.Dimension(25, 25));
        jButtonDelete.setMinimumSize(new java.awt.Dimension(25, 25));
        jButtonDelete.setPreferredSize(new java.awt.Dimension(25, 25));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelAfterMchChildControl.add(jButtonDelete, gridBagConstraints);

        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAdd.setMaximumSize(new java.awt.Dimension(25, 25));
        jButtonAdd.setMinimumSize(new java.awt.Dimension(25, 25));
        jButtonAdd.setPreferredSize(new java.awt.Dimension(25, 25));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelAfterMchChildControl.add(jButtonAdd, gridBagConstraints);

        jButtonContinue.setText("ไปต่อ >>");
        jButtonContinue.setMargin(new java.awt.Insets(2, 7, 2, 7));
        jButtonContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonContinueActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        jPanelAfterMchChildControl.add(jButtonContinue, gridBagConstraints);

        jButton1.setText("<< ย้อนกลับ");
        jButton1.setMargin(new java.awt.Insets(2, 7, 2, 7));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanelAfterMchChildControl.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 5, 5);
        add(jPanelAfterMchChildControl, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelSurveyDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLabelSurveyDateActionPerformed
        this.dateComboBoxSurvey.setEnabled(jLabelSurveyDate.isSelected());
        if(!jLabelSurveyDate.isSelected())
            dateComboBoxSurvey.setText("");
    }//GEN-LAST:event_jLabelSurveyDateActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        Vector v = theAfterMCHMotherControl.listPPByMotherPtid(pcuobject.getPatient().getObjectId());
        jScrollPaneListChild.setVisible(!v.isEmpty());
        jCheckBox1.setVisible(!v.isEmpty());
        this.setPPV(v);
        if(v.isEmpty()){
            Vector vppcare =  theAfterMCHMotherControl.listPPCareByFamilyID(pcuobject.getFamily().getObjectId());
            setPPCareV(vppcare);
        }

    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTablePPMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePPMouseReleased
        pp_child = (PP)vChildren.get(jTablePP.getSelectedRow());
        setPPCareV(theAfterMCHMotherControl.listPPCareByFamilyID(pp_child.family_id));
    }//GEN-LAST:event_jTablePPMouseReleased

    private void timeTextFieldCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeTextFieldCheckMouseClicked

        timeTextFieldCheck.selectAll();

    }//GEN-LAST:event_timeTextFieldCheckMouseClicked

    public void setPPCareV2(PP pp)
    {
        setPPCareV(theAfterMCHMotherControl.listPPCareByFamilyID(pp.family_id));
    }

    public static void main(String[] argc){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(df.format(new Date()));
    }

    private void timeTextFieldCheckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timeTextFieldCheckKeyReleased

        

    }//GEN-LAST:event_timeTextFieldCheckKeyReleased



    private void dateComboBoxCheckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateComboBoxCheckKeyReleased

        

    }//GEN-LAST:event_dateComboBoxCheckKeyReleased

    

    private void dateComboBoxSurveyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxSurveyFocusLost

        checkSurvey = false;

        checkDateSurvay();

    }//GEN-LAST:event_dateComboBoxSurveyFocusLost

    

    private void dateComboBoxSurveyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxSurveyActionPerformed

        checkDateSurvay();

        checkSurvey = false;

    }//GEN-LAST:event_dateComboBoxSurveyActionPerformed

    

    private void integerTextFieldCareTimeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integerTextFieldCareTimeFocusLost

        if(integerTextFieldCareTime.getText().equals("")) {

            theUS.setStatus(GutilPCU.getTextBundle("FillCareTime"), UpdateStatus.WARNING);

        }

    }//GEN-LAST:event_integerTextFieldCareTimeFocusLost

    

    private void integerTextFieldCareTimeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldCareTimeKeyReleased

        checkCareNumber();

    }//GEN-LAST:event_integerTextFieldCareTimeKeyReleased

    

    private void jButtonAppointmentActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAppointmentActionPerformed

    {//GEN-HEADEREND:event_jButtonAppointmentActionPerformed

        if(pcuobject.getPatient()==null){

            theUS.setStatus(GutilPCU.getTextBundle("No_Patient_Appointment"),UpdateStatus.WARNING);

            return;

        }

        theHosDialog.showDialogAppointment(theHosManage,pcuobject);

        receiveNotify = 5;

    }//GEN-LAST:event_jButtonAppointmentActionPerformed

    

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddActionPerformed

    {//GEN-HEADEREND:event_jButtonAddActionPerformed

        addPPCare();

    }//GEN-LAST:event_jButtonAddActionPerformed

    

    private void jTablePPCareMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTablePPCareMouseReleased
    
    {//GEN-HEADEREND:event_jTablePPCareMouseReleased

        selectPPCare(-2);

    }//GEN-LAST:event_jTablePPCareMouseReleased

    

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeleteActionPerformed

    {//GEN-HEADEREND:event_jButtonDeleteActionPerformed
        deletePPCare();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonSaveActionPerformed

    {//GEN-HEADEREND:event_jButtonSaveActionPerformed
        saveOrUpdatePPCare();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    

    private void integerTextFieldBirthPlaceKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_integerTextFieldBirthPlaceKeyReleased

    {//GEN-HEADEREND:event_integerTextFieldBirthPlaceKeyReleased
        showDescriptionHosp();
    }//GEN-LAST:event_integerTextFieldBirthPlaceKeyReleased

    

    private void jButtonBirthPlaceActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonBirthPlaceActionPerformed

    {//GEN-HEADEREND:event_jButtonBirthPlaceActionPerformed

        showDialogBirthHosp();

    }//GEN-LAST:event_jButtonBirthPlaceActionPerformed

    private void jButtonContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonContinueActionPerformed
        jButtonContinue.setVisible(false);
        thePanelAfterMchMother.jButtonContinue.setVisible(true);
        theJD.setVisible(false);
        this.thePanelAfterMchMother.showDialog(true);
}//GEN-LAST:event_jButtonContinueActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jButtonContinue.setVisible(false);
        thePanelAfterMchMother.jButtonContinue.setVisible(true);
        theJD.setVisible(false);
        this.thePanelPP.showDialog(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupHealth;
    private javax.swing.ButtonGroup buttonGroupNavel;
    private javax.swing.ButtonGroup buttonGroupPPCareResult;
    private javax.swing.ButtonGroup buttonGroupSkin;
    private javax.swing.ButtonGroup buttonGroupStool;
    private javax.swing.ButtonGroup buttonGroupUrine;
    private com.hospital_os.utility.DateComboBox dateComboBoxCheck;
    private com.pcu.utility.DateComboBox dateComboBoxSurvey;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.pcu.utility.IntegerTextField integerTextFieldBirthPlace;
    private com.pcu.utility.IntegerTextField integerTextFieldCareTime;
    private com.pcu.utility.IntegerTextField integerTextFieldMomAge;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAppointment;
    private javax.swing.JButton jButtonBirthPlace;
    public javax.swing.JButton jButtonContinue;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBoxCheckPlace;
    private javax.swing.JLabel jLabeNavelChild;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAppointment;
    private javax.swing.JLabel jLabelBirthPlace;
    private javax.swing.JLabel jLabelCheckChild;
    private javax.swing.JLabel jLabelHealth;
    private javax.swing.JLabel jLabelMomAge;
    private javax.swing.JLabel jLabelNextAppDate;
    private javax.swing.JLabel jLabelSkin;
    private javax.swing.JLabel jLabelStool;
    private javax.swing.JCheckBox jLabelSurveyDate;
    private javax.swing.JLabel jLabelUrine;
    private javax.swing.JLabel jLabelVN;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelAfterMchChildControl;
    private javax.swing.JPanel jPanelAfterMchChildDetail;
    private javax.swing.JPanel jPanelAfterMchChildList;
    private javax.swing.JPanel jPanelAfterMchChildService;
    private javax.swing.JPanel jPanelResultAfterMchChild;
    private javax.swing.JRadioButton jRadioButtonAbnormal;
    private javax.swing.JRadioButton jRadioButtonNavelAbnormal;
    private javax.swing.JRadioButton jRadioButtonNavelNormal;
    private javax.swing.JRadioButton jRadioButtonNoTreat;
    private javax.swing.JRadioButton jRadioButtonNormal;
    private javax.swing.JRadioButton jRadioButtonSkinAbNormal;
    private javax.swing.JRadioButton jRadioButtonSkinNormal;
    private javax.swing.JRadioButton jRadioButtonStoolAbnormal;
    private javax.swing.JRadioButton jRadioButtonStoolNormal;
    private javax.swing.JRadioButton jRadioButtonStrong;
    private javax.swing.JRadioButton jRadioButtonUrineAbnormal;
    private javax.swing.JRadioButton jRadioButtonUrineNormal;
    private javax.swing.JRadioButton jRadioButtonWeak;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneAfterChildList;
    private javax.swing.JScrollPane jScrollPaneListChild;
    public javax.swing.JTable jTablePP;
    public javax.swing.JTable jTablePPCare;
    private javax.swing.JTextField jTextAreaNoteRemark;
    private com.hosv3.gui.component.BalloonTextArea jTextAreaNoteResult;
    private javax.swing.JTextField jTextFieldBirthPlace;
    private com.hospital_os.utility.TimeTextField timeTextFieldCheck;
    // End of variables declaration//GEN-END:variables

    

    /**

     *เซตภาษา

     *@param -

     *@return void

     *@author amp

     *@modify kingland

     *@date 04/09/2549

     */

    private void setLanguage() {   /*jLabel*/

        GutilPCU.setLanguage(col);
        jLabeNavelChild.setText(GutilPCU.getTextBundle(jLabeNavelChild.getText()));

        jLabelBirthPlace.setText(GutilPCU.getTextBundle(jLabelBirthPlace.getText()));

        jLabelCheckChild.setText(GutilPCU.getTextBundle(jLabelCheckChild.getText()));

        jLabelHealth.setText(GutilPCU.getTextBundle(jLabelHealth.getText()));

        jLabelMomAge.setText(GutilPCU.getTextBundle(jLabelMomAge.getText()));

        jLabelNextAppDate.setText(GutilPCU.getTextBundle(jLabelNextAppDate.getText()));

        jLabelSkin.setText(GutilPCU.getTextBundle(jLabelSkin.getText()));

        jLabelStool.setText(GutilPCU.getTextBundle(jLabelStool.getText()));

        jLabelUrine.setText(GutilPCU.getTextBundle(jLabelUrine.getText()));

        jLabel1.setText(GutilPCU.getTextBundle(jLabel1.getText()));

        jLabel2.setText(GutilPCU.getTextBundle(jLabel2.getText()));

        jLabel3.setText(GutilPCU.getTextBundle(jLabel3.getText()));

        GutilPCU.setLanguage(jLabelAppointment);
        GutilPCU.setLanguage(jLabel5);
        GutilPCU.setLanguage(jLabel6);
        /*jButton*/

        GutilPCU.setLanguage(jCheckBox1);
        jButtonDelete.setText(GutilPCU.getTextBundle(jButtonDelete.getText()));

        jButtonSave.setText(GutilPCU.getTextBundle(jButtonSave.getText()));

        jButtonAdd.setText(GutilPCU.getTextBundle(jButtonAdd.getText()));

        jButtonAppointment.setText(GutilPCU.getTextBundle(jButtonAppointment.getText()));

        /*jRadioButton*/

        jRadioButtonNoTreat.setText(GutilPCU.getTextBundle(jRadioButtonNoTreat.getText()));

        jRadioButtonNormal.setText(GutilPCU.getTextBundle(jRadioButtonNormal.getText()));

        jRadioButtonAbnormal.setText(GutilPCU.getTextBundle(jRadioButtonAbnormal.getText()));

        jRadioButtonWeak.setText(GutilPCU.getTextBundle(jRadioButtonWeak.getText()));

        jRadioButtonStrong.setText(GutilPCU.getTextBundle(jRadioButtonStrong.getText()));

        jRadioButtonSkinAbNormal.setText(GutilPCU.getTextBundle(jRadioButtonSkinAbNormal.getText()));

        jRadioButtonSkinNormal.setText(GutilPCU.getTextBundle(jRadioButtonSkinNormal.getText()));

        jRadioButtonNavelAbnormal.setText(GutilPCU.getTextBundle(jRadioButtonNavelAbnormal.getText()));

        jRadioButtonNavelNormal.setText(GutilPCU.getTextBundle(jRadioButtonNavelNormal.getText()));

        jRadioButtonStoolAbnormal.setText(GutilPCU.getTextBundle(jRadioButtonStoolAbnormal.getText()));

        jRadioButtonStoolNormal.setText(GutilPCU.getTextBundle(jRadioButtonStoolNormal.getText()));

        jRadioButtonUrineAbnormal.setText(GutilPCU.getTextBundle(jRadioButtonUrineAbnormal.getText()));

        jRadioButtonUrineNormal.setText(GutilPCU.getTextBundle(jRadioButtonUrineNormal.getText()));

        jLabelSurveyDate.setText(GutilPCU.getTextBundle(jLabelSurveyDate.getText()));

        /*TitledBorder*/

        GutilPCU.JPanelLabler(jPanelAfterMchChildList);

        GutilPCU.JPanelLabler(jPanelAfterMchChildDetail);

        GutilPCU.JPanelLabler(jPanelResultAfterMchChild);
        GutilPCU.setLanguage(colPPCare);
    }

    public void setJFrame(JFrame frame) {

        theFrame = frame;

        initBalloon();

    }

    public JFrame getJFrame() {

        return theFrame;

    }

    /**

     * ใช้ในการแสดงข้อมูลที่ได้จากการค้นหารหัสสถานพยาบาล

     *@param -

     *@return void

     *@author amp

     *@modify kingland

     *@date 04/09/2549

     */

    private void showDialogBirthPlace() {

        Office theOffice = new Office();

        if(theHosDialog.showDialogOffice(theHosManage,theOffice)) {

            jTextFieldBirthPlace.setText(theOffice.getName());

            integerTextFieldBirthPlace.setText(theOffice.getCode());

            thePPCare.pp_care_deliver_place = theOffice.getObjectId();

        }

    }

    

    /**

     * ใช้ในการแสดงข้อมูลที่ได้จากการค้นหารหัสสถานพยาบาล

     */

    private void showDescriptionHosp() {

        if(integerTextFieldBirthPlace.getText().length() == 0 || integerTextFieldBirthPlace.getText().equals("")) {
            jTextFieldBirthPlace.setText("");
        } 
        else if(integerTextFieldBirthPlace.getText().length() == 5) {
            Office theOffice = theHealthSchoolServiceControl.selectOfficeByPK(integerTextFieldBirthPlace.getText());
            if(theOffice!=null) {

                if(!theOffice.getObjectId().equals("")) {

                    jTextFieldBirthPlace.setText(theOffice.name);

                    thePPCare.pp_care_deliver_place = theOffice.getObjectId();

                }

            }

        }

    }

    /**

     *เซตการใช้งาน GUI

     *@param -

     *@return void

     *@author amp

     *@modify kingland

     *@date 04/09/2549

     */

    private void setEnableGui(boolean flag) {

        integerTextFieldCareTime.setEnabled(flag);

        jComboBoxCheckPlace.setEnabled(flag);

        jRadioButtonStrong.setEnabled(flag);

        jRadioButtonWeak.setEnabled(flag);

        integerTextFieldMomAge.setEnabled(flag);

        jRadioButtonSkinAbNormal.setEnabled(flag);

        jRadioButtonSkinNormal.setEnabled(flag);

        jRadioButtonNavelAbnormal.setEnabled(flag);

        jRadioButtonNavelNormal.setEnabled(flag);

        jRadioButtonStoolAbnormal.setEnabled(flag);

        jRadioButtonStoolNormal.setEnabled(flag);

        jRadioButtonUrineAbnormal.setEnabled(flag);

        jRadioButtonUrineNormal.setEnabled(flag);

        jButtonAppointment.setEnabled(flag);

        integerTextFieldBirthPlace.setEnabled(flag);

        jTextAreaNoteResult.setEnabled(flag);

        jTextAreaNoteRemark.setEnabled(flag);

        jButtonBirthPlace.setEnabled(flag);

        jButtonSave.setEnabled(flag);

        jRadioButtonNoTreat.setEnabled(flag);

        jRadioButtonNormal.setEnabled(flag);

        jRadioButtonAbnormal.setEnabled(flag);

    }

    /**

     *เซต Enable GUI

     *@param boolean

     *@return void

     *@author kingland

     *@date 04/09/2549

     */

    public void setEnabled(boolean flag) {

        setEnableGui(flag);

        jButtonAdd.setEnabled(flag);

        jButtonDelete.setEnabled(flag);

    }


    /**

     *เคลียร์ GUI

     *@param -

     *@return void

     *@author amp

     *@modify kingland

     *@date 04/09/2549

     */

    private void clearGuiPPCare() {

        integerTextFieldCareTime.setText("");

        integerTextFieldMomAge.setText("");

        //jLabelAppointment.setText("");

        integerTextFieldBirthPlace.setText("");

        jTextFieldBirthPlace.setText("");

        jTextAreaNoteResult.setText("");

        jTextAreaNoteRemark.setText("");

        jRadioButtonStrong.setSelected(true);

        jRadioButtonSkinNormal.setSelected(true);

        jRadioButtonNavelNormal.setSelected(true);

        jRadioButtonStoolNormal.setSelected(true);

        jRadioButtonUrineNormal.setSelected(true);

        jRadioButtonNoTreat.setSelected(true);

    }

    /**

     *ตรวจสอบ Patient และ Family จาก PCUobject

     *@param -

     *@return boolean true=มีผู้ป่วยหรือประชากร false=ไม่มีผู้ป่วยและประชากร

     *@author kingland

     *@date 04/09/2549

     */

    private boolean checkPatientAndFamily() {

        boolean result = true;

        if(theFamily == null) result = false;

        return result;

    }

    /**

     *ตรวจสอบว่าผู้ป่วยหรือประชากรเสียชีวิตแล้วหรือไม่

     *@param -

     *@return boolean true=เสียชีวิต false=ไม่เสียชีวิต

     *@author kingland

     *@date 04/09/2549

     */

    private boolean checkDead() {

        boolean result = false;

        if(theFamily != null && Dischar.DEATH.equals(theFamily.discharge_status_id)) {

            result = true;

        } else if(thePatient != null && Dischar.DEATH.equals(thePatient.discharge_status_id)) {

            result = true;

        }

        return false;//result;

    }


    /**

     *บันทึกข้อมูลการดูแลลูก

     *@param -

     *@return int จำนวนเรคคอร์ดที่บันทึก

     *@author -

     *@modify kingland

     *@date 04/09/2549

     */

    private PPCare getPPCare()
    {
        if(thePPCare==null)
            thePPCare = new PPCare();
        
        thePPCare.pp_care_number = integerTextFieldCareTime.getText();
        thePPCare.pp_care_survey_place = Gutil.getGuiData(jComboBoxCheckPlace);
        thePPCare.pp_care_deliver_place = integerTextFieldBirthPlace.getText();
        thePPCare.pp_care_mom_age = integerTextFieldMomAge.getText();
        thePPCare.pp_care_state = Gutil.CheckReservedWords(jTextAreaNoteResult.getText());
        thePPCare.pp_care_comment = Gutil.CheckReservedWords(jTextAreaNoteRemark.getText());
        if(jRadioButtonWeak.isSelected()) {
            thePPCare.pp_care_health = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_health = PcuAnswer.One();
        }
        if(jRadioButtonSkinAbNormal.isSelected()) {
            thePPCare.pp_care_dermis = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_dermis = PcuAnswer.One();
        }
        if(jRadioButtonNavelAbnormal.isSelected()) {
            thePPCare.pp_care_navel = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_navel = PcuAnswer.One();
        }
        if(jRadioButtonStoolAbnormal.isSelected()) {
            thePPCare.pp_care_feces = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_feces = PcuAnswer.One();
        }
        if(jRadioButtonUrineAbnormal.isSelected()) {
            thePPCare.pp_care_urine = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_urine = PcuAnswer.One();
        }
        if(theAppointment != null) {
            thePPCare.pp_care_next_appointment = theAppointment.appoint_date;
        }
        if(jRadioButtonNoTreat.isSelected()) {
            thePPCare.pp_care_result =  FamilyPlaningTreatment.isNotTreatment();
        }
        if(jRadioButtonNormal.isSelected()) {
            thePPCare.pp_care_result =  FamilyPlaningTreatment.isNormal();
        }
        if(jRadioButtonAbnormal.isSelected()) {
            thePPCare.pp_care_result =  FamilyPlaningTreatment.isAbNormal();
        }
        if(pp_child!=null){
            thePPCare.family_id = pp_child.family_id;
        }
        else{
            thePPCare.family_id = this.pcuobject.getFamily().getObjectId();
        }
        if(jLabelSurveyDate.isSelected())
            thePPCare.survey_date = dateComboBoxSurvey.getText();

        thePPCare.pp_care_modify_date_time = dateComboBoxCheck.getText()+","+timeTextFieldCheck.getText()+":00";
        return thePPCare;
    }
    private boolean saveOrUpdatePPCare() 
    {
        PPCare ppcare = getPPCare();
        if(!checkSameCareTime()) {
            return false;
        }
        if(pcuobject.theHO.theVisit==null)
        {
            theUS.setStatus("กรุณานำผู้ป่วยเข้าสู่กระบวนการ",UpdateStatus.WARNING);
            return false;
        }
        int age = Integer.parseInt(pcuobject.theHO.theVisit.patient_age);
        if(age >= 12)
        {
            Vector v = theHosManage.theHosControl.theAfterMCHMotherControl.listPPCareByPatientID(pcuobject.getPatient().getObjectId());
            if(v.isEmpty())
            {
                theUS.setStatus("กรุณาบันทึกข้อมูลเด็การก",UpdateStatus.WARNING);//กรุณาเลือกประชากรหรือบันทึกข้อมูลประชากรก่อน
                return false;
            }
        }
//        if(pp_child==null)
//        {
//            theUS.setStatus("กรุณาบันทึกข้อมูลเด็การก",UpdateStatus.WARNING);//กรุณาเลือกประชากรหรือบันทึกข้อมูลประชากรก่อน
//            return false;
//        }
        if(pp_child==null && !vChildren.isEmpty()){
            theUS.setStatus("กรุณาเลือกบุตรที่ต้องการดูแลหลังคลอด",UpdateStatus.WARNING);//กรุณาเลือกประชากรหรือบันทึกข้อมูลประชากรก่อน
            return false;
        }
        if (pcuobject.getFamily() == null) {
            theUS.setStatus(GutilPCU.getTextBundle("SavePerson"),UpdateStatus.WARNING);
            //กรุณาเลือกประชากรหรือบันทึกข้อมูลประชากรก่อน
            return false;
        }
        //NOT HAVE VISIT
        if(ppcare.getObjectId()==null && pcuobject.getVisit()==null)
        {
            if(ppcare.survey_date.equals("")) {
                theUS.setStatus(GutilPCU.getTextBundle("WarningNotHaveSurVeyDate"), UpdateStatus.WARNING);
                dateComboBoxSurvey.requestFocus();
                return false;
            }
            else if(ppcare.survey_date.length() != 10) {
                theUS.setStatus(GutilPCU.getTextBundle("WarningNotHaveSurVeyDate"), UpdateStatus.WARNING);
                dateComboBoxSurvey.requestFocus();
                return false;
            }
        }
        if(ppcare.pp_care_number.equals("")) {
            integerTextFieldCareTime.requestFocus();
            return false;
        }
        if(vPPCare!=null && vPPCare.size()>0 )
        {
            PPCare tmp = (PPCare) vPPCare.get(0);
            ppcare.patient_id = tmp.patient_id;
        }
        int res = theAfterMCHMotherControl.saveOrUpdatePPCare(ppcare);
        if(res==0){
            return false;
        }
        String id = ppcare.getObjectId();
        searchPPCareByPatientId();
        for(int i=0;i<vPPCare.size();i++){
            PPCare pp = (PPCare)vPPCare.get(i);
            if(pp.getObjectId().equals(id)){
                this.jTablePPCare.setRowSelectionInterval(i,i);
                setPPCare((PPCare)vPPCare.get(i));
                return true;
            }
        }
        return false;
    }

    /**

     * ตรวจสอบก่อนทำการลบ

     * 1.ตรวจสอบว่าเป็น Visit ครั้งเดียวกันหรือไม่ ถ้าใช่สามารถลบได้ ถ้าไม่ใช่ไม่สามารถลบได้

     * 2.ถ้าเป็นข้อมูลการสำรวจสามารถลบข้อมูลได้ตลอด

     * @return true = สามารถลบได้ false = ไม่สามารถลบได้

     * @author kingland

     * @date 12/06/2549

     */

    

    private boolean deletePPCare() 
    {
        //ถ้าไม่ Visit แล้วทำการลบ
            int result = theAfterMCHMotherControl.deletePPCare(thePPCare);
            if(result>0) {
                setPPCare(null);
                searchPPCareByPatientId();
            }
            return true;

    }

    /**

     * แสดงข้อความเตือน

     * @param message = ข้อความที่ต้องการให้แสด

     *        status = สถานะที่แสดง

     * @return void

     * @author kingland

     * @date 28/08/2549

     */


    private void setPPCareV(Vector vPPC)
    {
        vPPCare = vPPC;
        TableModel tm = new TableModel(colPPCare,vPPCare.size());
        for(int i=0 ; i<vPPCare.size(); i++) {
            PPCare ppCareTemp = (PPCare)vPPCare.get(i);
            tm.setValueAt(ppCareTemp.pp_care_number,i,0);
            tm.setValueAt(GutilPCU.changDateToString(ppCareTemp.pp_care_modify_date_time,false),i,1);
        }
        jTablePPCare.setModel(tm);
        jTablePPCare.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTablePPCare.getColumnModel().getColumn(0).setCellRenderer(ColumnTableRenderer.getRendererCenter());
        jTablePPCare.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTablePPCare.getColumnModel().getColumn(1).setCellRenderer(ColumnTableRenderer.getRendererCenter());
        setPPCare(null);
    }

    

    private void selectPPCare(int row) {
        int rowPPCare = row;
        if(row==-2) {   /*user เป็นผู้เลือกเอง*/
            rowPPCare = jTablePPCare.getSelectedRow();
        }
        setPPCare((PPCare)vPPCare.get(rowPPCare));

    }

    public void setPPCare(PPCare pp) 
    {
        thePPCare = pp;
        if(thePPCare == null){
//            clearGuiPPCare();
            thePPCare = new PPCare();
            thePPCare.pp_care_modify_date_time = this.pcuobject.getCurrentDateTime();
//            return;
        }
        dateComboBoxSurvey.setText(Gutil.convertFieldDate(thePPCare.survey_date));
        jLabelSurveyDate.setSelected(!dateComboBoxSurvey.getText().equals(""));
        dateComboBoxSurvey.setEnabled(!dateComboBoxSurvey.getText().equals(""));
        this.jLabelVN.setText("");
        if(!thePPCare.visit_id.equals("")){
            String vn_id = this.theAllComboBoxControl.readVNbyVid(thePPCare.visit_id);
            this.jLabelVN.setText("VN:"+vn_id);
        }
        integerTextFieldCareTime.setText(thePPCare.pp_care_number);
        if(thePPCare.pp_care_modify_date_time.length()>11)
            this.timeTextFieldCheck.setText(thePPCare.pp_care_modify_date_time.substring(11));
        this.dateComboBoxCheck.setText(Gutil.convertFieldDate(thePPCare.pp_care_modify_date_time));
        ComboboxModel.setCodeComboBox(jComboBoxCheckPlace,thePPCare.pp_care_survey_place);

        if(thePPCare.pp_care_health.equals(PcuAnswer.Zero()))
            jRadioButtonWeak.setSelected(true);
        else
            jRadioButtonStrong.setSelected(true);

        integerTextFieldMomAge.setText(thePPCare.pp_care_mom_age);
        if(thePPCare.pp_care_dermis.equals(PcuAnswer.Zero()))
            jRadioButtonSkinAbNormal.setSelected(true);
        else
            jRadioButtonSkinNormal.setSelected(true);
        if(thePPCare.pp_care_navel.equals(PcuAnswer.Zero())) {
            jRadioButtonNavelAbnormal.setSelected(true);
        } else {
            jRadioButtonNavelNormal.setSelected(true);
        }
        if(thePPCare.pp_care_feces.equals(PcuAnswer.Zero())) {
            jRadioButtonStoolAbnormal.setSelected(true);
        } else {
            jRadioButtonStoolNormal.setSelected(true);
        }
        if(thePPCare.pp_care_urine.equals(PcuAnswer.Zero())) {
            jRadioButtonUrineAbnormal.setSelected(true);
        } else {
            jRadioButtonUrineNormal.setSelected(true);
        }
        jLabelAppointment.setText(GutilPCU.changDateToString((thePPCare.pp_care_next_appointment),true));
        if(thePPCare.pp_care_deliver_place.equals("") || thePPCare.pp_care_deliver_place == null ) {
            integerTextFieldBirthPlace.setText("");
            jTextFieldBirthPlace.setText("");
        } else {
            Office theOffice = theHealthSchoolServiceControl.selectOfficeByPK(thePPCare.pp_care_deliver_place);
            if(theOffice != null) {
                integerTextFieldBirthPlace.setText(theOffice.getCode());
                jTextFieldBirthPlace.setText(theOffice.getName());
            } else {
                integerTextFieldBirthPlace.setText("");
                jTextFieldBirthPlace.setText("");
            }
        }
        if(thePPCare.pp_care_result.equals(FamilyPlaningTreatment.isNotTreatment())) {

            jRadioButtonNoTreat.setSelected(true);

        }

        if(thePPCare.pp_care_result.equals(FamilyPlaningTreatment.isNormal())) {

            jRadioButtonNormal.setSelected(true);

        }

        if(thePPCare.pp_care_result.equals(FamilyPlaningTreatment.isAbNormal())) {

            jRadioButtonAbnormal.setSelected(true);

        }

        jTextAreaNoteResult.setText(thePPCare.pp_care_state);

        jTextAreaNoteRemark.setText(thePPCare.pp_care_comment);

        boolean status = true;

        if(pcuobject.getVisit() != null) {

            status = theAfterMCHMotherControl.checkStatusVisitOfPP(thePPCare.visit_id);

        }

        if(status) {

            setEnableGui(true);

            jButtonDelete.setEnabled(true);

        }

    }

    

    /**

     *ใช้ในการตรวจสอบการกรอกตัวเลข

     *@param -

     *@return void

     *@author jao

     *@modify kingland

     *@date 04/09/2549

     */

    public void checkCareNumber() {

        int number =-1;

        try {

            number = Integer.parseInt(integerTextFieldCareTime.getText());

        } catch(Exception ex) {

            ex.printStackTrace();

        }

        if(number <=0) {

            theUS.setStatus(GutilPCU.getTextBundle("ValueLess"), UpdateStatus.WARNING);//ค่าต้องมากกว่า 0

            integerTextFieldCareTime.requestFocus();

            integerTextFieldCareTime.selectAll();

        }

        if(number >= 20) {

            theUS.setStatus(GutilPCU.getTextBundle("ValueMore"), UpdateStatus.WARNING);//ค่าต้องน้อยกว่า 20

            integerTextFieldCareTime.requestFocus();

            integerTextFieldCareTime.selectAll();

        }

    }


    /**
     *@deprecated henbe unused ให้ใช้ฟังชันที่ดึงจากเลขประชากรแทน
     *ค้นหารายการดูแลหลังคลอด ตรวจสอบว่าเป็นแม่หรือลูกหากเป็นแม่ ก็ค้นลูกมาแสดง หากไม่ใช่ก็ซ่อนรายการลูก
     * และค้นข้อมูลดูลแลหลังคลอดของลูกมาแสดง
     *@param -
     *@return void
     *@author jao
     *@modify kingland
     *@date 04/09/2549
     */

    private void searchPPCareByPatientId() {
        vPPCare =  theAfterMCHMotherControl.listPPCareByFamilyID(pcuobject.getFamily().getObjectId());
        setPPCareV(vPPCare);
    }

    

    public void showDialogBirthHosp() {

        boolean result = false;

        String codeBirthPlace = getDataCodeComboBox(jComboBoxCheckPlace, true);

        String code = integerTextFieldBirthPlace.getText();

        /**ตรวจสอบ visit_id**/

        if( (code.trim().length() >0) && codeBirthPlace.equalsIgnoreCase(PostpartumBirthPlace.isHospital())) {

            if(theUS.confirmBox(GutilPCU.getTextBundle("ChangeHosP1")+"\r\n"+GutilPCU.getTextBundle("ChangeHosP2"),UpdateStatus.WARNING)) {

                result = true;

            }

        }

        if(!result) {

            Office theOffice = new Office();

            if(theHosDialog.showDialogOffice(theHosManage,theOffice)) {

                jTextFieldBirthPlace.setText(theOffice.getName());

                integerTextFieldBirthPlace.setText(theOffice.getCode());

            }

        }

    }

    /**

     * ตรวจสอบว่าวันออกตรวจเป็นวันในอนาคตหรือไม่

     * @param

     * @return

     * @author kingland

     * @date 17-03-2549

     */

    private void checkDateSurvay() {

        if(!dateComboBoxSurvey.getText().equals("")

        && dateComboBoxSurvey.getText().length()==10

                && com.pcu.utility.DateUtil.countDay(dateComboBoxSurvey.getText(),theHosManage.theHosControl.theConnectionInf) == -1

                && com.pcu.utility.DateUtil.isToday(com.pcu.utility.DateUtil.getDateFromText(dateComboBoxSurvey.getText()),theHosManage.theHosControl.theConnectionInf)==false) {

            if(checkSurvey == false) {  // ไม่สามารถกรอกวันที่เป็นวันในอนาคตได้
                theUS.setStatus(GutilPCU.getTextBundle("NoDateFuture"),UpdateStatus.WARNING);
                checkSurvey= true;

            }

        }

    }

    

    /**

     * ใช้ในการแสดงข้อมูลออกมาจาก ComboBox ตาม param ทีกำหนด

     * @author ผดุงรัฐ

     * @param combobox ComboBox ที่ต้องการจะนำค่าออกมาใช้งาน

     * @param type เป็นตัวกำหนดค่าที่จะ return ออก

     * true คือ แสดงออกมาเป็น Code

     * false คือ แสดงออกมาเป็น ข้อความ

     * @return แสดงค่าที่ต้องการออกมา

     */

    private String getDataCodeComboBox(JComboBox combobox ,boolean type) {

        if(type) {

            return ComboboxModel.getCodeComboBox(combobox);

        } else {

            return ComboboxModel.getStringConboBox(combobox);

        }

    }

    

    private void addPPCare() {
        
        if(isVisitSame())
            return;
        setEnableGui(true);
        setPPCare(null);
    }

    /**
     *ตรวจสอบว่ามี Visit ที่ซ้ำกันหรือไม่
     *@param -
     *@return boolean false=ไม่ซ้ำ   true=ซ้ำ
     *@author Tong
     *@date 01/09/2549
     */
    private boolean isVisitSame() 
    {
//        if(pcuobject.getVisit()==null)
//            return false;
//
//        if(this.vPPCare == null)
//            return false;
//
//        for(int i=0;i<vPPCare.size();i++)
//        {
//            PPCare obj = (PPCare)vPPCare.get(i);
//            if(pcuobject.getVisit().getObjectId().equals(obj.visit_id)) {
//                theUS.setStatus(GutilPCU.getTextBundle("VisitAlready"),UpdateStatus.WARNING);
//                return true;
//            }
//        }
        return false;
    }
    /**

     * สำหรับการรับบริการที่ไม่ต้องVisit

     *@param -

     *@return kingland

     *@author jao

     *@modify kingland

     *@date 04/09/2549

     */

    private boolean checkServicePPcare() {

        boolean result = true;

        if(vPPCare!= null && pcuobject.getVisit() != null) {

            PPCare ppc = null;

            for(int i = vPPCare.size()-1 ; i >=0  ; i--) {

                ppc = new PPCare();

                ppc = (PPCare)vPPCare.get(i);

                if(ppc.visit_id.equalsIgnoreCase(pcuobject.getVisit().getObjectId())) {

                    result =  false;

                    break;

                }

                ppc = null;

            }

        }

        return result;

    }

    /**

     * ตรวจสอบว่าหมายเลขการดูแลครั้งที่ซ้ำกันหรือไม่

     * @author kingland

     * @return true = ไม่ซ้ำ false = ซ้ำกัน

     * @date 12/06/2549

     */

    private boolean checkSameCareTime() {

        boolean result = true;

        String str = integerTextFieldCareTime.getText().trim();

        if(vPPCare!=null && vPPCare.size()>0) {

            LoopNumber:for(int i=0, size=vPPCare.size(); i<size; i++) {

                PPCare ppc = (PPCare)vPPCare.get(i);

                if( ppc.pp_care_number.equals(str) && thePPCare.getObjectId()==null) {

                    result = false;
                    //หมายเลขดูแลครั้งที่ซ้ำกับที่มีอยู่แล้ว กรุณาระบุใหม่
                    theUS.setStatus(GutilPCU.getTextBundle("NumberSame"),UpdateStatus.WARNING);

                    break LoopNumber;

                }

            }

        }

        return result;

    }

    

    public void notifyDeletePatient(String str, int param) {

    }

    

    public void notifyDeletePatientPayment(String str, int param) {

    }

    

    public void notifyManageDrugAllergy(String str, int param) {

    }

    

    public void notifyReadPatient(String str, int param) {

    }

    

    public void notifyResetPatient(String str, int param) {

    }

    

    public void notifySaveAppointment(String str, int param) {

        if(receiveNotify==5) {

            theAppointment = new Appointment();

            theAppointment = theHosDialog.theDialogAppointment.getAppointment();

            jLabelAppointment.setText(GutilPCU.changDateToString((theAppointment.appoint_date),true));

        }

    }

    public void notifySavePatient(String str, int param) {

    }

    public void notifySavePatientPayment(String str, int param) {

    }

    public void notifyReadFamily(String str, int status) {

    }

    @Override
    public void setObject(Object obj) {
    }

    @Override
    public Object getObject() {
        return null;
    }

    @Override
    public void refreshList() {
        this.setObject(pcuobject);
    }

    @Override
    public void selectList() {
    }

    @Override
    public void notifyApgarScore(String[] arrayApgarScoreNotify) {
    }

    @Override
    public void notifySavePP(PP pp) {
        Vector v = new Vector();
        if(thePatient!=null){
            v = theAfterMCHMotherControl.listPPByMotherPtid(thePatient.getObjectId());
        }
        this.setPPV(v);
        jScrollPaneListChild.setVisible(!v.isEmpty());
        jCheckBox1.setVisible(!v.isEmpty());
        if(v.isEmpty()){
            Vector vppcare =  theAfterMCHMotherControl.listPPCareByFamilyID(pcuobject.getFamily().getObjectId());
            setPPCareV(vppcare);
            jScrollPaneListChild.setVisible(!vppcare.isEmpty());
            jCheckBox1.setVisible(!vppcare.isEmpty());
        }
    }

}



