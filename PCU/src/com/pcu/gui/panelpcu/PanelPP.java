/*
 * PanelPP.java
 *
 * Created on 26 กรกฎาคม 2548, 11:35 น.
 */
package com.pcu.gui.panelpcu;

import com.hospital_os.utility.Constant;
import com.hospital_os.utility.TableRenderer;
import com.hosv3.control.HosControl;
import com.pcu.control.AfterMCHMotherControl;
import com.pcu.control.HosManage;
import com.pcu.control.PCUObject;
import com.pcu.gui.dialog.DialogApgarScore;
import com.pcu.gui.dialog.HosDialog;
import com.pcu.gui.panel.transaction.PanelFamily;
import com.pcu.subject.PPSubject;
import javax.swing.*;
import java.util.Vector;

import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.Gutil;
import com.hospital_os.object.Patient;
import com.hospital_os.object.Dischar;
import com.hosv3.utility.connection.UpdateStatus;

import com.pcu.utility.*;
import com.pcu.object.*;
import com.pcu.control.AllComboBoxControl;
import com.pcu.subject.ManagePPResp;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author  amp
 */
public class PanelPP extends javax.swing.JPanel implements ManagePPResp
{
    private AfterMCHMotherControl thePPControl; 
    private AllComboBoxControl theAllComboBoxControl;
    private HosManage theHosManage;
    private PPSubject thePPSubject;
    
    private String[] arrayAgarScore;
    private int help = 0;
    private TableModel tm;
    private PP thePP;     
    private boolean checkdead = false;
    private Family theFamily;
    private Patient thePatient;
    private PCUObject pcuobject;
    private JFrame theFrame;
    boolean checkSurvey = false;

    public Vector vChildren;

    private UpdateStatus theUS;

    private PanelFamily thePanelFamily;

    private HosControl theHC;
    private String[] col = new String[]{"คนที่","วันที่บันทึก"};
            String[] colApgar = {"ApgarScore",
                    "OneMin",
                    "FiveMin",
                    "TenMin"};
    private JDialog theJD;
    PanelAfterMchChild thePanelAfterMchChild;
    PanelBornMchMother thePanelBornMchMother;
    /** Creates new form PanelPP */
    public PanelPP(){
        initComponents();
        //ไม่มีทางที่จะมีการคลอดและลงข้อมูลนอกโรงพยาบาล
        jPanelAncSurvey.setVisible(false);
        jButton1.setVisible(false);
        jButtonContinue.setVisible(false);
//        jComboBoxSex.setEnabled(true);
//        jLabel24.setVisible(false);
//        jComboBoxSex.setVisible(false);
    }
    public void setPanelBornMchMother(PanelBornMchMother panelBornMchMother)
    {
        thePanelBornMchMother = panelBornMchMother;
    }
    public void setPanelAfterMchChild(PanelAfterMchChild panelAfterMchChild)
    {
        thePanelAfterMchChild = panelAfterMchChild;
    }
    public void showDialog(boolean b)
    {
        if(theJD==null)
            theJD = new JDialog(theHC.theUS.getJFrame());
        theJD.add(this);
        theJD.setSize(720,490);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, (screenSize.height - theJD.getSize().height) / 2);
        theJD.setTitle("ข้อมูลเด็กทารก");
        theJD.setModal(true);
        jButtonContinue.setVisible(b);
        jButton1.setVisible(b);
        theJD.setModal(true);
        theJD.setVisible(true);
    }
    public void setControl(HosManage hm,HosDialog hd,UpdateStatus us)
    {
        theHosManage = hm; 
        pcuobject = hm.thePO;
        theUS = us;
        thePPControl = hm.theHosControl.theAfterMCHMotherControl;
        theAllComboBoxControl = hm.theHosControl.theAllComboBoxControl;
        thePPSubject = hm.theHosSubject.thePPSubject;     
        thePPSubject.attachApgarScore(this);   
        theHC = theHosManage.theHC;
        setLanguage();
        initDatas();
    }
    private void initBalloon()
    {   theHosManage.theHosControl.balloon.add(jTextAreaDiseasesAbnormal);
        jTextAreaDiseasesAbnormal.setControl(theHosManage.theHosInf.getVitalTemplate());
        jTextAreaDiseasesAbnormal.setJFrame( getJFrame());
        theHosManage.theHosControl.balloon.add(jTextAreaComment);
        jTextAreaComment.setControl(theHosManage.theHosInf.getVitalTemplate());
        jTextAreaComment.setJFrame( getJFrame());
    }
    public void initDatas() 
    {
        if(arrayAgarScore==null)
           arrayAgarScore = new String[7];
        tm = new TableModel(colApgar,6);
        setTableApgarScore();
        Vector vPPBreathingTime = theAllComboBoxControl.listPPBreathingTime();
        /*init combobox*/
        ComboboxModel.initComboBox(jComboBoxBreathingTime,vPPBreathingTime);
        ComboboxModel.initComboBox(jComboBoxCryingTime,vPPBreathingTime);
        ComboboxModel.initComboBox(jComboBoxAspiration,theAllComboBoxControl.listPPAspiration());
        ComboboxModel.initComboBox(jComboBoxSex,theAllComboBoxControl.listSex());
        ComboboxModel.initComboBox(jComboBoxEyeDrug,theAllComboBoxControl.listPPEyeDrug());
        ComboboxModel.initComboBox(jComboBoxMethod,theAllComboBoxControl.listBirthMethodForPP());
        ComboboxModel.initComboBox(jComboBoxInfantState,theAllComboBoxControl.listPPState());
        ComboboxModel.initComboBox(jComboBoxCauseOfDead,theAllComboBoxControl.listPPDeadCause());
        dateComboBoxDateDead.setEditable(true);
        dateComboBoxDateDead.setText("");
        timeTextFieldTimeDead.setText("");
    } 
    
   public void setObject(PCUObject pcuobject)
    {
        Constant.println("_henbe_______________________" + this.getClass().toString());
        this.pcuobject = pcuobject;
        thePP = null;   
        theFamily = null;
        thePatient = null;
        jTableApgarScore.clearSelection();
        theFamily = pcuobject.getFamily();
        if(pcuobject.getPatient() != null)
        {   thePatient = pcuobject.getPatient();
            theFamily = pcuobject.getFamily();
            thePP = thePPControl.readPPByFamilyID();
        }
        else if(thePP==null && pcuobject.getFamily()!=null&&pcuobject.getFamily().getObjectId()!=null)
        {   thePatient = pcuobject.getPatient();
            theFamily = pcuobject.getFamily(); 
            thePP = thePPControl.readPPByFamilyID();
        }
        
        //////////////////////////////////////////////////////////////////////////////////
        if(thePP!=null)
        {   
            setEnabled(true);                    
            setPP(thePP);
            boolean flagvisit = thePPControl.checkStatusVisitOfPP(thePP.t_visit_id);
            if(flagvisit)
                jButtonDelete.setEnabled(true);/*เข้าสู่กระบวนการ*/
            if(("1").equals(ComboboxModel.getCodeComboBox(jComboBoxInfantState)))
            {   
                jComboBoxCauseOfDead.setEnabled(false);
                dateComboBoxDateDead.setEnabled(false);
                timeTextFieldTimeDead.setEnabled(false);
            }
            else
            {  
                jComboBoxCauseOfDead.setEnabled(true);
                dateComboBoxDateDead.setEnabled(true);
                timeTextFieldTimeDead.setEnabled(true);
            }
        }
        else
        {   clearGuiPP();
            clearTableApgarScore();
        } 
        if(pcuobject.getPatient()!=null){
            setPPV(thePPControl.listPPCareByPatientID(pcuobject.getPatient().getObjectId()));
            jScrollPane5.setVisible(!vChildren.isEmpty());
            this.jButtonAdd.setVisible(!vChildren.isEmpty());
            this.jButtonDelete.setVisible(!vChildren.isEmpty());
            if(vChildren.isEmpty()){
                PP pp = thePPControl.readPPByChildFid(pcuobject.getFamily().getObjectId());
                this.setPP(pp);
            }
        }
        else{
            setPPV(new Vector());
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editoror.
     */
    
    private void showDialogPerson(Home theHome, Family family) 
    {
        if(thePanelFamily==null)
            thePanelFamily = new PanelFamily(theHosManage,theHome,family);
        //สร้างตัว clone เพื่อที่เวลา get ข้อมูลจาก Gui มาใส่ object แล้วบันทึกไม่ผ่าน ค่าของ family ตัวนี้ก็จะยังไม่เปลี่ยนแปลง
        if(family!=null)
            family = (Family)family.clone();
        thePanelFamily.showPanel(theUS.getJFrame(),theHome,family,theUS);
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
        if(pcuobject.getVisit()==null) 
            return false;        
        
        if(this.vChildren == null) 
            return false;
        
        for(int i=0;i<vChildren.size();i++) 
        {
            PP obj = (PP)vChildren.get(i);
            if(pcuobject.getVisit().getObjectId().equals(obj.t_visit_id)) {
                theUS.setStatus(GutilPCU.getTextBundle("VisitAlready"),UpdateStatus.WARNING);
                return true;
            }
        }
        return false;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroupPPLostO2 = new javax.swing.ButtonGroup();
        buttonGroupPPRecVK = new javax.swing.ButtonGroup();
        buttonGroupColor = new javax.swing.ButtonGroup();
        buttonGroupInitialCondition = new javax.swing.ButtonGroup();
        buttonGroupActivity = new javax.swing.ButtonGroup();
        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaResuscitation = new javax.swing.JTextArea();
        jLabel26 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaDiseasesAbnormal = new com.hosv3.gui.component.BalloonTextArea();
        jLabel24 = new javax.swing.JLabel();
        jComboBoxSex = new javax.swing.JComboBox();
        jLabelWeight = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldWeight = new com.pcu.utility.IntegerTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        dateComboBoxCheck = new com.hospital_os.utility.DateComboBox();
        jLabel16 = new javax.swing.JLabel();
        timeTextFieldCheck = new com.hospital_os.utility.TimeTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabelVN = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jRadioButtonActivityGood = new javax.swing.JRadioButton();
        jLabelRecVK = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jComboBoxAspiration = new javax.swing.JComboBox();
        jRadioButtonInject = new javax.swing.JRadioButton();
        jRadioButtonNoInject = new javax.swing.JRadioButton();
        jRadioButtonBlue = new javax.swing.JRadioButton();
        jRadioButtonActivityWeak = new javax.swing.JRadioButton();
        jRadioButtonPink = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        jLabelLostO2 = new javax.swing.JLabel();
        jRadioButtonPPLostO2No = new javax.swing.JRadioButton();
        jRadioButtonPPLostO2Yes = new javax.swing.JRadioButton();
        jRadioButtonICPoor = new javax.swing.JRadioButton();
        jRadioButtonICGood = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jLabelMethodBearing1 = new javax.swing.JLabel();
        pIDPanelMother = new com.pcu.utility.PIDPanel();
        jPanelListChild = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTablePP = new javax.swing.JTable();
        jPanelAncSurvey = new javax.swing.JPanel();
        dateComboBoxSurvey = new com.pcu.utility.DateComboBox();
        jLabelSurveyDate = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        integerTextFieldInfantNumber = new com.pcu.utility.IntegerTextField();
        jLabel29 = new javax.swing.JLabel();
        integerTextFieldGravida = new com.pcu.utility.IntegerTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jComboBoxBreathingTime = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        jComboBoxCryingTime = new javax.swing.JComboBox();
        jLabel25 = new javax.swing.JLabel();
        jComboBoxEyeDrug = new javax.swing.JComboBox();
        jComboBoxMethod = new javax.swing.JComboBox();
        jButtonSaveChild = new javax.swing.JButton();
        jLabelMethodBearing = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        dateComboBoxDateDead = new com.pcu.utility.DateComboBox();
        timeTextFieldTimeDead = new com.pcu.utility.TimeTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaComment = new com.hosv3.gui.component.BalloonTextArea();
        jPanel11 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        integerTextFieldLength = new com.pcu.utility.DoubleTextField();
        integerTextFieldFrontoOccipital = new com.pcu.utility.DoubleTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        integerTextFieldChest = new com.pcu.utility.DoubleTextField();
        integerTextFieldT = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxInfantState = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jComboBoxCauseOfDead = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jButtonDelete = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonPrint = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jButtonContinue = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableApgarScore = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButtonHelp1 = new javax.swing.JButton();
        jButtonHelp5 = new javax.swing.JButton();
        jButtonHelp10 = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Infant Evaluation at birth"));
        jPanel1.setMinimumSize(new java.awt.Dimension(450, 400));
        jPanel1.setPreferredSize(new java.awt.Dimension(450, 400));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextAreaResuscitation.setLineWrap(true);
        jTextAreaResuscitation.setWrapStyleWord(true);
        jScrollPane3.setViewportView(jTextAreaResuscitation);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel9.add(jScrollPane3, gridBagConstraints);

        jLabel26.setText("DiseasesOrAbnormalities");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel9.add(jLabel26, gridBagConstraints);

        jLabel23.setText("Resuscitation");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel9.add(jLabel23, gridBagConstraints);

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setMinimumSize(new java.awt.Dimension(24, 40));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(104, 40));
        jScrollPane4.setViewportView(jTextAreaDiseasesAbnormal);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel9.add(jScrollPane4, gridBagConstraints);

        jLabel24.setText("PPSex");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel9.add(jLabel24, gridBagConstraints);

        jComboBoxSex.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel9.add(jComboBoxSex, gridBagConstraints);

        jLabelWeight.setText("PPWeight");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel9.add(jLabelWeight, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel27.setText("gms");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel8.add(jLabel27, gridBagConstraints);

        jTextFieldWeight.setColumns(4);
        jTextFieldWeight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldWeight.setMinimumSize(new java.awt.Dimension(48, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel8.add(jTextFieldWeight, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel9.add(jPanel8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jPanel9, gridBagConstraints);

        jPanel16.setLayout(new java.awt.GridBagLayout());

        jLabel15.setText("วันที่บันทึก");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 3);
        jPanel16.add(jLabel15, gridBagConstraints);

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

        jLabel16.setText("น.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 1);
        jPanel16.add(jLabel16, gridBagConstraints);

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

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/clock.gif"))); // NOI18N
        jLabel30.setToolTipText("เวลาที่ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 3);
        jPanel16.add(jLabel30, gridBagConstraints);

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
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel1.add(jPanel16, gridBagConstraints);

        jPanel13.setLayout(new java.awt.GridBagLayout());

        jLabel19.setText("Activity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel13.add(jLabel19, gridBagConstraints);

        buttonGroupActivity.add(jRadioButtonActivityGood);
        jRadioButtonActivityGood.setSelected(true);
        jRadioButtonActivityGood.setText("Good");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel13.add(jRadioButtonActivityGood, gridBagConstraints);

        jLabelRecVK.setText("PPRecVK");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel13.add(jLabelRecVK, gridBagConstraints);

        jLabel17.setText("Color");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel13.add(jLabel17, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel13.add(jComboBoxAspiration, gridBagConstraints);

        jRadioButtonInject.setBackground(new java.awt.Color(204, 255, 255));
        buttonGroupPPRecVK.add(jRadioButtonInject);
        jRadioButtonInject.setSelected(true);
        jRadioButtonInject.setText("Inject");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel13.add(jRadioButtonInject, gridBagConstraints);

        jRadioButtonNoInject.setBackground(new java.awt.Color(204, 255, 255));
        buttonGroupPPRecVK.add(jRadioButtonNoInject);
        jRadioButtonNoInject.setText("NoInject");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel13.add(jRadioButtonNoInject, gridBagConstraints);

        buttonGroupColor.add(jRadioButtonBlue);
        jRadioButtonBlue.setText("Blue");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel13.add(jRadioButtonBlue, gridBagConstraints);

        buttonGroupActivity.add(jRadioButtonActivityWeak);
        jRadioButtonActivityWeak.setText("PPWeak");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel13.add(jRadioButtonActivityWeak, gridBagConstraints);

        buttonGroupColor.add(jRadioButtonPink);
        jRadioButtonPink.setSelected(true);
        jRadioButtonPink.setText("Pink");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel13.add(jRadioButtonPink, gridBagConstraints);

        jLabel22.setText("Aspiration");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel13.add(jLabel22, gridBagConstraints);

        jLabelLostO2.setText("PPLostO2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel13.add(jLabelLostO2, gridBagConstraints);

        jRadioButtonPPLostO2No.setBackground(new java.awt.Color(204, 255, 255));
        buttonGroupPPLostO2.add(jRadioButtonPPLostO2No);
        jRadioButtonPPLostO2No.setSelected(true);
        jRadioButtonPPLostO2No.setText("AspNo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel13.add(jRadioButtonPPLostO2No, gridBagConstraints);

        jRadioButtonPPLostO2Yes.setBackground(new java.awt.Color(204, 255, 255));
        buttonGroupPPLostO2.add(jRadioButtonPPLostO2Yes);
        jRadioButtonPPLostO2Yes.setText("AspYes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel13.add(jRadioButtonPPLostO2Yes, gridBagConstraints);

        buttonGroupInitialCondition.add(jRadioButtonICPoor);
        jRadioButtonICPoor.setText("Poor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel13.add(jRadioButtonICPoor, gridBagConstraints);

        buttonGroupInitialCondition.add(jRadioButtonICGood);
        jRadioButtonICGood.setSelected(true);
        jRadioButtonICGood.setText("Good");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel13.add(jRadioButtonICGood, gridBagConstraints);

        jLabel11.setText("InitialCondition");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel13.add(jLabel11, gridBagConstraints);

        jLabelMethodBearing1.setText("เลขบัตรมารดา (ผู้ป่วยปัจจุบัน)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel13.add(jLabelMethodBearing1, gridBagConstraints);

        pIDPanelMother.setEnabled(false);
        pIDPanelMother.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pIDPanelMotherFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel13.add(pIDPanelMother, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        jPanel1.add(jPanel13, gridBagConstraints);

        jPanelListChild.setLayout(new java.awt.GridBagLayout());

        jTablePP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTablePPMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(jTablePP);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelListChild.add(jScrollPane5, gridBagConstraints);

        jPanelAncSurvey.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelAncSurvey.add(dateComboBoxSurvey, gridBagConstraints);

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
        jPanelAncSurvey.add(jLabelSurveyDate, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanelListChild.add(jPanelAncSurvey, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        integerTextFieldInfantNumber.setColumns(2);
        integerTextFieldInfantNumber.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldInfantNumber.setMinimumSize(new java.awt.Dimension(22, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel2.add(integerTextFieldInfantNumber, gridBagConstraints);

        jLabel29.setText("PregnantNumber");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jLabel29, gridBagConstraints);

        integerTextFieldGravida.setColumns(2);
        integerTextFieldGravida.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldGravida.setMinimumSize(new java.awt.Dimension(22, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel2.add(integerTextFieldGravida, gridBagConstraints);

        jLabel1.setText("InfantNumber");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel20.setText("BreathingTime");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel2.add(jLabel20, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel2.add(jComboBoxBreathingTime, gridBagConstraints);

        jLabel21.setText("CryingTime");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel2.add(jLabel21, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel2.add(jComboBoxCryingTime, gridBagConstraints);

        jLabel25.setText("EyeDrugs");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel2.add(jLabel25, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel2.add(jComboBoxEyeDrug, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel2.add(jComboBoxMethod, gridBagConstraints);

        jButtonSaveChild.setText("แก้ไขข้อมูลประชากร (เด็ก)");
        jButtonSaveChild.setMaximumSize(new java.awt.Dimension(67, 24));
        jButtonSaveChild.setMinimumSize(new java.awt.Dimension(67, 24));
        jButtonSaveChild.setPreferredSize(new java.awt.Dimension(67, 24));
        jButtonSaveChild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveChildActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel2.add(jButtonSaveChild, gridBagConstraints);

        jLabelMethodBearing.setText("PPMethodBearing");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel2.add(jLabelMethodBearing, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 0, 5);
        jPanelListChild.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jPanelListChild, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        add(jPanel1, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Circumference"));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel6.setLayout(new java.awt.GridBagLayout());

        dateComboBoxDateDead.setMinimumSize(new java.awt.Dimension(100, 24));
        dateComboBoxDateDead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxDateDeadActionPerformed(evt);
            }
        });
        dateComboBoxDateDead.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateComboBoxDateDeadFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel6.add(dateComboBoxDateDead, gridBagConstraints);

        timeTextFieldTimeDead.setColumns(5);
        timeTextFieldTimeDead.setMaximumSize(new java.awt.Dimension(40, 19));
        timeTextFieldTimeDead.setMinimumSize(new java.awt.Dimension(40, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel6.add(timeTextFieldTimeDead, gridBagConstraints);

        jLabel14.setText("DeadDate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jPanel6.add(jLabel14, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(jPanel6, gridBagConstraints);

        jLabel18.setText("Comments");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel3.add(jLabel18, gridBagConstraints);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportView(jTextAreaComment);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jScrollPane1, gridBagConstraints);

        jPanel11.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("PPRimHead");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel11.add(jLabel2, gridBagConstraints);

        jLabel6.setText("Length");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        jPanel11.add(jLabel6, gridBagConstraints);

        integerTextFieldLength.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldLength.setMinimumSize(new java.awt.Dimension(30, 22));
        integerTextFieldLength.setPreferredSize(new java.awt.Dimension(30, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 0, 0);
        jPanel11.add(integerTextFieldLength, gridBagConstraints);

        integerTextFieldFrontoOccipital.setColumns(3);
        integerTextFieldFrontoOccipital.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldFrontoOccipital.setMinimumSize(new java.awt.Dimension(30, 22));
        integerTextFieldFrontoOccipital.setPreferredSize(new java.awt.Dimension(30, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel11.add(integerTextFieldFrontoOccipital, gridBagConstraints);

        jLabel3.setText("cms");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel11.add(jLabel3, gridBagConstraints);

        jLabel7.setText("cms");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 0, 0);
        jPanel11.add(jLabel7, gridBagConstraints);

        jLabel4.setText("Chest");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        jPanel11.add(jLabel4, gridBagConstraints);

        integerTextFieldChest.setColumns(3);
        integerTextFieldChest.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldChest.setMinimumSize(new java.awt.Dimension(30, 22));
        integerTextFieldChest.setPreferredSize(new java.awt.Dimension(30, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 0, 0);
        jPanel11.add(integerTextFieldChest, gridBagConstraints);

        integerTextFieldT.setColumns(3);
        integerTextFieldT.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        integerTextFieldT.setMinimumSize(new java.awt.Dimension(30, 22));
        integerTextFieldT.setPreferredSize(new java.awt.Dimension(30, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 0, 0);
        jPanel11.add(integerTextFieldT, gridBagConstraints);

        jLabel8.setText("T");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        jPanel11.add(jLabel8, gridBagConstraints);

        jLabel9.setText("C");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 0, 0);
        jPanel11.add(jLabel9, gridBagConstraints);

        jLabel5.setText("cms");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 0, 0);
        jPanel11.add(jLabel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(jPanel11, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel10.setText("InfantState");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        jPanel12.add(jLabel10, gridBagConstraints);

        jComboBoxInfantState.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxInfantStateItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 0, 0);
        jPanel12.add(jComboBoxInfantState, gridBagConstraints);

        jLabel13.setText("CauseOfDead");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        jPanel12.add(jLabel13, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 0, 0);
        jPanel12.add(jComboBoxCauseOfDead, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(jPanel12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jPanel3, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel7.add(jButtonDelete, gridBagConstraints);

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
        jPanel7.add(jButtonSave, gridBagConstraints);

        jButtonPrint.setText("Print");
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel7.add(jButtonPrint, gridBagConstraints);

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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel7.add(jButtonAdd, gridBagConstraints);

        jButtonContinue.setText("ไปต่อ >>");
        jButtonContinue.setMargin(new java.awt.Insets(2, 7, 2, 7));
        jButtonContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonContinueActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel7.add(jButtonContinue, gridBagConstraints);

        jButton1.setText("<< ย้อนกลับ");
        jButton1.setMargin(new java.awt.Insets(2, 7, 2, 7));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        jPanel7.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 5, 5);
        add(jPanel7, gridBagConstraints);

        jPanel4.setMinimumSize(new java.awt.Dimension(200, 120));
        jPanel4.setPreferredSize(new java.awt.Dimension(200, 120));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jTableApgarScore.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableApgarScore);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 5, 0, 5);
        add(jPanel4, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jButtonHelp1.setText("1 นาที");
        jButtonHelp1.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jButtonHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHelp1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jButtonHelp1, gridBagConstraints);

        jButtonHelp5.setText("5 นาที");
        jButtonHelp5.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jButtonHelp5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHelp5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel5.add(jButtonHelp5, gridBagConstraints);

        jButtonHelp10.setText("10 นาที");
        jButtonHelp10.setMargin(new java.awt.Insets(2, 10, 2, 10));
        jButtonHelp10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHelp10ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel5.add(jButtonHelp10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jPanel5, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelSurveyDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLabelSurveyDateActionPerformed
        this.dateComboBoxSurvey.setEnabled(jLabelSurveyDate.isSelected());
        if(!jLabelSurveyDate.isSelected())
            dateComboBoxSurvey.setText("");
    }//GEN-LAST:event_jLabelSurveyDateActionPerformed

    private void jButtonSaveChildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveChildActionPerformed
        if(thePP.family_id.equals("") || thePP.getObjectId()==null){
            theUS.setStatus("กรุณาบันทึกข้อมูลเด็กทารกก่อนแก้ไขข้อมูลประชากร(เด็ก)",UpdateStatus.WARNING);
            return;
        }
        Family fm_child = thePPControl.readFamilyRet(this.thePP,ComboboxModel.getCodeComboBox(jComboBoxSex));
        showDialogPerson(pcuobject.getHome(),fm_child);
    }//GEN-LAST:event_jButtonSaveChildActionPerformed
    
    private void timeTextFieldCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeTextFieldCheckMouseClicked
        timeTextFieldCheck.selectAll();
    }//GEN-LAST:event_timeTextFieldCheckMouseClicked

    private void timeTextFieldCheckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timeTextFieldCheckKeyReleased
        
    }//GEN-LAST:event_timeTextFieldCheckKeyReleased

    private void dateComboBoxCheckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateComboBoxCheckKeyReleased
        
    }//GEN-LAST:event_dateComboBoxCheckKeyReleased


    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        setPP(null);
        this.setEnabled(true);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jTablePPMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePPMouseReleased
        PP pp_child = (PP)vChildren.get(jTablePP.getSelectedRow());
        this.setPP(pp_child);
    }//GEN-LAST:event_jTablePPMouseReleased

    private void dateComboBoxSurveyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxSurveyFocusLost
        checkSurvey = false;
        checkDateSurvay();
    }//GEN-LAST:event_dateComboBoxSurveyFocusLost

    private void dateComboBoxSurveyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxSurveyActionPerformed
        checkDateSurvay();
        checkSurvey = false;
    }//GEN-LAST:event_dateComboBoxSurveyActionPerformed

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintActionPerformed
        printPP();
    }//GEN-LAST:event_jButtonPrintActionPerformed

    private void pIDPanelMotherFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pIDPanelMotherFocusLost

    }//GEN-LAST:event_pIDPanelMotherFocusLost

    private void dateComboBoxDateDeadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxDateDeadActionPerformed
        checkDateControl();
    }//GEN-LAST:event_dateComboBoxDateDeadActionPerformed

    private void dateComboBoxDateDeadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxDateDeadFocusLost
        checkdead = false;
    }//GEN-LAST:event_dateComboBoxDateDeadFocusLost

    private void jComboBoxInfantStateItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_jComboBoxInfantStateItemStateChanged
    {//GEN-HEADEREND:event_jComboBoxInfantStateItemStateChanged
        if(ComboboxModel.getCodeComboBox(jComboBoxInfantState).equals("1")
        || ComboboxModel.getCodeComboBox(jComboBoxInfantState).equals("0"))
        {
            jComboBoxCauseOfDead.setEnabled(false);
            dateComboBoxDateDead.setEnabled(false);
            timeTextFieldTimeDead.setEnabled(false);
            ComboboxModel.setCodeComboBox(jComboBoxCauseOfDead, "0");
            dateComboBoxDateDead.setText("");
            timeTextFieldTimeDead.setText("");
        }
        else
        {
            jComboBoxCauseOfDead.setEnabled(true);
            dateComboBoxDateDead.setEnabled(true);
            timeTextFieldTimeDead.setEnabled(true);
            
        }
    }//GEN-LAST:event_jComboBoxInfantStateItemStateChanged

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeleteActionPerformed
    {//GEN-HEADEREND:event_jButtonDeleteActionPerformed
        deletePP();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonSaveActionPerformed
    {//GEN-HEADEREND:event_jButtonSaveActionPerformed
        saveOrUpdatePP();
//        if(vChildren==null||vChildren.isEmpty())
//            theJD.setVisible(false);
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonHelp10ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonHelp10ActionPerformed
    {//GEN-HEADEREND:event_jButtonHelp10ActionPerformed
        help = 10;
        apgarScoreAt(help);
    }//GEN-LAST:event_jButtonHelp10ActionPerformed

    private void jButtonHelp5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonHelp5ActionPerformed
    {//GEN-HEADEREND:event_jButtonHelp5ActionPerformed
        help = 5;
        apgarScoreAt(help);
    }//GEN-LAST:event_jButtonHelp5ActionPerformed

    private void jButtonHelp1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonHelp1ActionPerformed
    {//GEN-HEADEREND:event_jButtonHelp1ActionPerformed
        help = 1;
        apgarScoreAt(help);
    }//GEN-LAST:event_jButtonHelp1ActionPerformed

    private void jButtonContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonContinueActionPerformed
        jButtonContinue.setVisible(false);
        thePanelAfterMchChild.jButtonContinue.setVisible(true);
        theJD.setVisible(false);
        this.thePanelAfterMchChild.showDialog(true);
    }//GEN-LAST:event_jButtonContinueActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jButtonContinue.setVisible(false);
        thePanelAfterMchChild.jButtonContinue.setVisible(true);
        theJD.setVisible(false);
        this.thePanelBornMchMother.showDialog(true);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupActivity;
    private javax.swing.ButtonGroup buttonGroupColor;
    private javax.swing.ButtonGroup buttonGroupInitialCondition;
    private javax.swing.ButtonGroup buttonGroupPPLostO2;
    private javax.swing.ButtonGroup buttonGroupPPRecVK;
    private com.hospital_os.utility.DateComboBox dateComboBoxCheck;
    private com.pcu.utility.DateComboBox dateComboBoxDateDead;
    private com.pcu.utility.DateComboBox dateComboBoxSurvey;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.pcu.utility.DoubleTextField integerTextFieldChest;
    private com.pcu.utility.DoubleTextField integerTextFieldFrontoOccipital;
    private com.pcu.utility.IntegerTextField integerTextFieldGravida;
    private com.pcu.utility.IntegerTextField integerTextFieldInfantNumber;
    private com.pcu.utility.DoubleTextField integerTextFieldLength;
    private javax.swing.JTextField integerTextFieldT;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAdd;
    public javax.swing.JButton jButtonContinue;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonHelp1;
    private javax.swing.JButton jButtonHelp10;
    private javax.swing.JButton jButtonHelp5;
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSaveChild;
    private javax.swing.JComboBox jComboBoxAspiration;
    private javax.swing.JComboBox jComboBoxBreathingTime;
    private javax.swing.JComboBox jComboBoxCauseOfDead;
    private javax.swing.JComboBox jComboBoxCryingTime;
    private javax.swing.JComboBox jComboBoxEyeDrug;
    private javax.swing.JComboBox jComboBoxInfantState;
    private javax.swing.JComboBox jComboBoxMethod;
    private javax.swing.JComboBox jComboBoxSex;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelLostO2;
    private javax.swing.JLabel jLabelMethodBearing;
    private javax.swing.JLabel jLabelMethodBearing1;
    private javax.swing.JLabel jLabelRecVK;
    private javax.swing.JCheckBox jLabelSurveyDate;
    private javax.swing.JLabel jLabelVN;
    private javax.swing.JLabel jLabelWeight;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelAncSurvey;
    private javax.swing.JPanel jPanelListChild;
    private javax.swing.JRadioButton jRadioButtonActivityGood;
    private javax.swing.JRadioButton jRadioButtonActivityWeak;
    private javax.swing.JRadioButton jRadioButtonBlue;
    private javax.swing.JRadioButton jRadioButtonICGood;
    private javax.swing.JRadioButton jRadioButtonICPoor;
    private javax.swing.JRadioButton jRadioButtonInject;
    private javax.swing.JRadioButton jRadioButtonNoInject;
    private javax.swing.JRadioButton jRadioButtonPPLostO2No;
    private javax.swing.JRadioButton jRadioButtonPPLostO2Yes;
    private javax.swing.JRadioButton jRadioButtonPink;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTableApgarScore;
    public javax.swing.JTable jTablePP;
    private com.hosv3.gui.component.BalloonTextArea jTextAreaComment;
    private com.hosv3.gui.component.BalloonTextArea jTextAreaDiseasesAbnormal;
    private javax.swing.JTextArea jTextAreaResuscitation;
    private com.pcu.utility.IntegerTextField jTextFieldWeight;
    private com.pcu.utility.PIDPanel pIDPanelMother;
    private com.hospital_os.utility.TimeTextField timeTextFieldCheck;
    private com.pcu.utility.TimeTextField timeTextFieldTimeDead;
    // End of variables declaration//GEN-END:variables
    /**
     *ตรวจสอบว่าผู้ป่วยหรือประชากรเสียชีวิตแล้วหรือไม่
     *@param -
     *@return boolean true=เสียชีวิต false=ไม่เสียชีวิต
     *@author kingland
     *@date 04/09/2549
     */
    private boolean checkDead()
    {   boolean result = false;
        if(theFamily != null && Dischar.DEATH.equals(theFamily.discharge_status_id))
        {   result = true;
        }
        else if(thePatient != null && Dischar.DEATH.equals(thePatient.discharge_status_id))
        {   result = true;
        }
//        System.err.println("_________________checkDead____________________"+result);
        return false;//result;
    }
    /**
     *ตรวจสอบ Patient และ Family จาก PCUobject
     *@param -
     *@return boolean true=มีผู้ป่วยหรือประชากร false=ไม่มีผู้ป่วยและประชากร
     *@author kingland
     *@date 04/09/2549
     */
    private boolean checkPatientAndFamily()
    {  
        boolean result = true;
        if(theFamily == null) result = false;
//        System.err.println("_________________checkPatientAndFamily____________________"+result);
        return result;
    }
    /**
     *ตรวจสอบเพศของผู้รับบริการ
     *@param -
     *@return boolean true=ผ่าน false=ไม่ผ่าน
     *@author kingland
     *@date 04/09/2549
     */
    private boolean checkSex(boolean showWarningMessage)
    {   boolean result = true;
        //add code by noom สำหรับ check เพศ หญิงเท่านั้น
        if(thePatient != null && !("2").equals(thePatient.f_sex_id))
        {   if(showWarningMessage)
            {   
                theUS.setStatus(GutilPCU.getTextBundle("ISSEXWOMAN"),UpdateStatus.WARNING); 
                //ยังไม่ระบุเพศ หรือเป็นเพศชาย ไม่สามารถเข้ารับบริการส่วนนี้ได้
            }
            result = false;
        }
        //สำหรับ check เพศ หญิงเท่านั้น
        else if(theFamily!=null && !("2").equals(theFamily.f_sex_id))
        {   if(showWarningMessage)
            {   
                theUS.setStatus(GutilPCU.getTextBundle("ISSEXWOMAN"),UpdateStatus.WARNING);
                //ยังไม่ระบุเพศ หรือเป็นเพศชาย ไม่สามารถเข้ารับบริการส่วนนี้ได้
            }
            result = false;
        }
        return result;
    }
    /**
     *
     */
    public void refresh(String family_id)
    {
        thePP = thePPControl.readPPByFamilyID(family_id); 
        //setPP(thePP);
    }
    private void refreshPP()
    {
        if(pcuobject.getPatient() != null && !"".equalsIgnoreCase(pcuobject.getPatient().getObjectId()))
        {
            thePP = thePPControl.readPPByFamilyID(); 
            if(thePP == null)
            {
                thePP = thePPControl.readPPByPatientId(pcuobject.getPatient().getObjectId()); 
            }
        }
        else if(theFamily != null && !"".equalsIgnoreCase(theFamily.getLogDetail()))
        {
            thePP = thePPControl.readPPByFamilyID(); 
        }
    }
    /**
     *
     */
    public void setLanguage()
    {
          GutilPCU.setLanguage(col);
          GutilPCU.setLanguage(colApgar);
          /*TitledBorder*/        
          GutilPCU.JPanelLabler(jPanel1);

          GutilPCU.setLanguage(jPanel3);
          GutilPCU.setLanguage(jLabel13);
          GutilPCU.setLanguage(jLabel14);
          
          /*jButton*/
          GutilPCU.setLanguage(jButtonSaveChild);
          jButtonHelp1.setText(GutilPCU.getTextBundle(jButtonHelp1.getText()));
          jButtonHelp5.setText(GutilPCU.getTextBundle(jButtonHelp5.getText()));
          jButtonHelp10.setText(GutilPCU.getTextBundle(jButtonHelp10.getText()));
          jButtonDelete.setText(GutilPCU.getTextBundle(jButtonDelete.getText()));
          jButtonSave.setText(GutilPCU.getTextBundle(jButtonSave.getText()));
          jButtonPrint.setText(GutilPCU.getTextBundle(jButtonPrint.getText()));
          
          /*jLabel*/
          GutilPCU.setLanguage(jLabel15);
          GutilPCU.setLanguage(jLabel16);
          GutilPCU.setLanguage(jLabelMethodBearing1);
          jLabel1.setText(GutilPCU.getTextBundle(jLabel1.getText()));
          jLabel2.setText(GutilPCU.getTextBundle(jLabel2.getText())); 
          jLabel3.setText(GutilPCU.getTextBundle(jLabel3.getText()));
          jLabel4.setText(GutilPCU.getTextBundle(jLabel4.getText()));
          jLabel5.setText(GutilPCU.getTextBundle(jLabel5.getText()));
          jLabel6.setText(GutilPCU.getTextBundle(jLabel6.getText())); 
          jLabel7.setText(GutilPCU.getTextBundle(jLabel7.getText()));  
          jLabel8.setText(GutilPCU.getTextBundle(jLabel8.getText()));
          jLabel9.setText(GutilPCU.getTextBundle(jLabel9.getText()));
          jLabel10.setText(GutilPCU.getTextBundle(jLabel10.getText()));
          jLabel11.setText(GutilPCU.getTextBundle(jLabel11.getText())); 
          jLabel17.setText(GutilPCU.getTextBundle(jLabel17.getText()));
          jLabel18.setText(GutilPCU.getTextBundle(jLabel18.getText()));
          jLabel19.setText(GutilPCU.getTextBundle(jLabel19.getText()));
          jLabel20.setText(GutilPCU.getTextBundle(jLabel20.getText()));
          jLabel21.setText(GutilPCU.getTextBundle(jLabel21.getText()));
          jLabel22.setText(GutilPCU.getTextBundle(jLabel22.getText()));
          jLabel23.setText(GutilPCU.getTextBundle(jLabel23.getText()));
          jLabel24.setText(GutilPCU.getTextBundle(jLabel24.getText()));
          jLabel25.setText(GutilPCU.getTextBundle(jLabel25.getText()));
          jLabel26.setText(GutilPCU.getTextBundle(jLabel26.getText()));
          jLabel27.setText(GutilPCU.getTextBundle(jLabel27.getText()));
          jLabel29.setText(GutilPCU.getTextBundle(jLabel29.getText()));
          jLabelMethodBearing.setText(GutilPCU.getTextBundle(jLabelMethodBearing.getText()));
          jLabelLostO2.setText(GutilPCU.getTextBundle(jLabelLostO2.getText()));
          jLabelRecVK.setText(GutilPCU.getTextBundle(jLabelRecVK.getText()));
          jLabelWeight.setText(GutilPCU.getTextBundle(jLabelWeight.getText()));
          jLabelSurveyDate.setText(GutilPCU.getTextBundle(jLabelSurveyDate.getText()));
          /*jRadioButton*/
          jRadioButtonPPLostO2No.setText(GutilPCU.getTextBundle(jRadioButtonPPLostO2No.getText()));
          jRadioButtonPPLostO2Yes.setText(GutilPCU.getTextBundle(jRadioButtonPPLostO2Yes.getText()));
          jRadioButtonNoInject.setText(GutilPCU.getTextBundle(jRadioButtonNoInject.getText()));
          jRadioButtonInject.setText(GutilPCU.getTextBundle(jRadioButtonInject.getText()));
          jRadioButtonBlue.setText(GutilPCU.getTextBundle(jRadioButtonBlue.getText()));
          jRadioButtonPink.setText(GutilPCU.getTextBundle(jRadioButtonPink.getText()));
          jRadioButtonICPoor.setText(GutilPCU.getTextBundle(jRadioButtonICPoor.getText()));
          jRadioButtonICGood.setText(GutilPCU.getTextBundle(jRadioButtonICGood.getText()));
          jRadioButtonActivityWeak.setText(GutilPCU.getTextBundle(jRadioButtonActivityWeak.getText()));
          jRadioButtonActivityGood.setText(GutilPCU.getTextBundle(jRadioButtonActivityGood.getText()));
    }
    public void setJFrame(JFrame frame)
    {
        theFrame = frame;
        initBalloon();
    }
    public JFrame getJFrame()
    {
        return theFrame;
    }
    private void setTableApgarScore()
    {               
        tm.setValueAt("HeartRate",0,0); 
        tm.setValueAt("RespirationEffort",1,0); 
        tm.setValueAt("MuscleTone",2,0); 
        tm.setValueAt("ReflexIrritability",3,0);
        tm.setValueAt("SkinColor",4,0);        
        tm.setValueAt("TotalScore",5,0);
        tm.setEditingCol(1,2,3); 
        
        if(arrayAgarScore != null)
        {    
            if(arrayAgarScore[0]!=null) 
            {
                for(int i=0, size=arrayAgarScore.length-1; i<size; i++)
                {                       
                    int colm = 1; 
                    if(help==1)
                    {
                        colm = 1;
                    }
                    if(help==5)
                    {
                        colm = 2;
                    }
                    if(help==10)
                    {
                        colm = 3;
                    }
                    tm.setValueAt(arrayAgarScore[i],i,colm);                    
                }   
            }
            else
            {
                clearTableApgarScore();
            }
        } 
        jTableApgarScore.setModel(tm);  
        
        jTableApgarScore.getColumnModel().getColumn(0).setPreferredWidth(300); 
        jTableApgarScore.getColumnModel().getColumn(1).setPreferredWidth(100); 
        jTableApgarScore.getColumnModel().getColumn(1).setCellRenderer(ColumnTableRenderer.getRendererRight());
        jTableApgarScore.getColumnModel().getColumn(2).setPreferredWidth(100); 
        jTableApgarScore.getColumnModel().getColumn(2).setCellRenderer(ColumnTableRenderer.getRendererRight());
        jTableApgarScore.getColumnModel().getColumn(3).setPreferredWidth(100); 
        jTableApgarScore.getColumnModel().getColumn(3).setCellRenderer(ColumnTableRenderer.getRendererRight());
    }
    
    private void apgarScoreAt(int minute)
    {

        if(thePP==null)
        {
            thePP = new PP();
        }
        if(minute==1)
        {
            arrayAgarScore[0] = (String)jTableApgarScore.getValueAt(0,1);
            arrayAgarScore[1] = (String)jTableApgarScore.getValueAt(1,1);
            arrayAgarScore[2] = (String)jTableApgarScore.getValueAt(2,1);
            arrayAgarScore[3] = (String)jTableApgarScore.getValueAt(3,1);
            arrayAgarScore[4] = (String)jTableApgarScore.getValueAt(4,1);
            arrayAgarScore[5] = (String)jTableApgarScore.getValueAt(5,1);
            arrayAgarScore[6] = thePP.pp_apgar_heart_rate_per_minute_one_minute;
        }
        if(minute==5)
        {
            arrayAgarScore[0] = (String)jTableApgarScore.getValueAt(0,2);
            arrayAgarScore[1] = (String)jTableApgarScore.getValueAt(1,2);
            arrayAgarScore[2] = (String)jTableApgarScore.getValueAt(2,2);
            arrayAgarScore[3] = (String)jTableApgarScore.getValueAt(3,2);
            arrayAgarScore[4] = (String)jTableApgarScore.getValueAt(4,2);
            arrayAgarScore[5] = (String)jTableApgarScore.getValueAt(5,2);
            arrayAgarScore[6] = thePP.pp_apgar_heart_rate_per_minute_five_minute;
        }
        if(minute==10)
        {
            arrayAgarScore[0] = (String)jTableApgarScore.getValueAt(0,3);
            arrayAgarScore[1] = (String)jTableApgarScore.getValueAt(1,3);
            arrayAgarScore[2] = (String)jTableApgarScore.getValueAt(2,3);
            arrayAgarScore[3] = (String)jTableApgarScore.getValueAt(3,3);
            arrayAgarScore[4] = (String)jTableApgarScore.getValueAt(4,3);
            arrayAgarScore[5] = (String)jTableApgarScore.getValueAt(5,3);
            arrayAgarScore[6] = thePP.pp_apgar_heart_rate_per_minute_ten_minute;
        }
        showDialogPPApgarScore(theHosManage,minute,arrayAgarScore);       
    }
    DialogApgarScore theDialogApgarScore;
    public void showDialogPPApgarScore(HosManage hm,int minute,String[] arrayApgarScore)
    {
        if(theDialogApgarScore == null)
            theDialogApgarScore = new DialogApgarScore(theFrame, true,hm);
        theDialogApgarScore.showDialog(minute,arrayApgarScore);
    }

    private void clearGuiPP()
    {
        integerTextFieldInfantNumber.setText("");
        pIDPanelMother.setText("");
        integerTextFieldGravida.setText("");
        jTextFieldWeight.setText("");
        jTextAreaResuscitation.setText("");  
        jTextAreaDiseasesAbnormal.setText("");
        integerTextFieldFrontoOccipital.setText("");
        integerTextFieldLength.setText("");   
        integerTextFieldChest.setText("");      
        integerTextFieldT.setText(""); 
        dateComboBoxDateDead.setText("");
        timeTextFieldTimeDead.setText("");
        jTextAreaComment.setText("");
        jRadioButtonPPLostO2No.setSelected(true); 
        jRadioButtonInject.setSelected(true); 
        jRadioButtonPink.setSelected(true);
        jRadioButtonICGood.setSelected(true);
        jRadioButtonActivityGood.setSelected(true);
    }
    /**
     *เซตการใช้งาน GUI
     *@param boolean
     *@return void
     *@author tong
     *@modify kingland
     *@date05/09/2549
     */
    public void setEnabled(boolean flag)
    {   integerTextFieldInfantNumber.setEnabled(flag);        
        integerTextFieldGravida.setEnabled(flag);        
        jRadioButtonICPoor.setEnabled(flag);  
        jRadioButtonICGood.setEnabled(flag);  
        jRadioButtonBlue.setEnabled(flag);
        jRadioButtonPink.setEnabled(flag);
        jRadioButtonActivityWeak.setEnabled(flag);  
        jRadioButtonActivityGood.setEnabled(flag);
        jComboBoxBreathingTime.setEnabled(flag);        
        jComboBoxCryingTime.setEnabled(flag);        
        jComboBoxAspiration.setEnabled(flag);
        jTextFieldWeight.setEnabled(flag);         
        jComboBoxEyeDrug.setEnabled(flag);        
        jComboBoxMethod.setEnabled(flag);        
        jRadioButtonPPLostO2No.setEnabled(flag);
        jRadioButtonPPLostO2Yes.setEnabled(flag);
        jRadioButtonNoInject.setEnabled(flag); 
        jRadioButtonInject.setEnabled(flag);
        jTextAreaResuscitation.setEnabled(flag);        
        jTextAreaDiseasesAbnormal.setEnabled(flag);        
        jButtonHelp1.setEnabled(flag);         
        jButtonHelp5.setEnabled(flag);        
        jButtonHelp10.setEnabled(flag);       
        integerTextFieldFrontoOccipital.setEnabled(flag);        
        integerTextFieldLength.setEnabled(flag);        
        integerTextFieldChest.setEnabled(flag);        
        integerTextFieldT.setEnabled(flag);
        jComboBoxInfantState.setEnabled(flag);       
        dateComboBoxDateDead.setEnabled(flag);        
        timeTextFieldTimeDead.setEnabled(flag);        
        jTextAreaComment.setEnabled(flag);        
        jButtonSave.setEnabled(flag);  
        pIDPanelMother.setEnabled(flag);
        jButtonPrint.setEnabled(flag);
        dateComboBoxSurvey.setEnabled(flag);
        jTableApgarScore.setEnabled(flag);
        jButtonDelete.setEnabled(flag);
    }
    /**
     * แสดงข้อความเตือน
     * @param message = ข้อความที่ต้องการให้แสดง
     *        status = สถานะที่แสดง
     * @return void
     * @author kingland
     * @date 28/08/2549
     */
    private void saveOrUpdatePP()
    {
        PP pp = thePPControl.saveOrUpdatePP(getPP(),pcuobject.getFamily()
                ,ComboboxModel.getCodeComboBox(jComboBoxSex)); 
        if(pp==null)
            return;
        this.theHosManage.theHosSubject.thePPSubject.notifySavePP(pp);
        setPPV(thePPControl.listPPCareByPatientID(pcuobject.getPatient().getObjectId()));

        //เลือกรายการที่บันทึกไปแล้วให้
        for(int i=0;i<this.vChildren.size();i++){
            PP ppiv = (PP)vChildren.get(i);
            if(ppiv.getObjectId().equals(pp.getObjectId())){
                this.jTablePP.setRowSelectionInterval(i,i);
                return ;
            }
        }
    }
    
    public PP getPP()
    {
        if(thePP == null)
            thePP = new PP();
        
        thePP.pp_number = integerTextFieldInfantNumber.getText();
        thePP.pp_mother_pid = pIDPanelMother.getText();
        thePP.pp_gravida = integerTextFieldGravida.getText();
        if(jRadioButtonICPoor.isSelected())
            thePP.pp_initial_condition = PcuAnswer.Zero(); 
        else
            thePP.pp_initial_condition = PcuAnswer.One();
        if(jRadioButtonBlue.isSelected())
            thePP.pp_color = PcuAnswer.Zero();
        else
            thePP.pp_color = PcuAnswer.One();
        if(jRadioButtonActivityWeak.isSelected())
            thePP.pp_activity = PcuAnswer.Zero();
        else
            thePP.pp_activity = PcuAnswer.One();

        thePP.pp_breathing_time = Gutil.getGuiData(jComboBoxBreathingTime);    
        thePP.pp_crying_time = Gutil.getGuiData(jComboBoxCryingTime);   
        thePP.pp_aspiration = Gutil.getGuiData(jComboBoxAspiration);
        thePP.pp_weight = jTextFieldWeight.getText();        
        thePP.pp_eye_drug = Gutil.getGuiData(jComboBoxEyeDrug);    
        thePP.pp_method_bearing = Gutil.getGuiData(jComboBoxMethod); 
        thePP.survey_date = dateComboBoxSurvey.getText();
        thePP.pp_modify_date_time = dateComboBoxCheck.getText()+","+timeTextFieldCheck.getText();
        /*
         * 0 = ขาด
         * 1 = ไม่ขาด
         **/
        if(jRadioButtonPPLostO2Yes.isSelected())
            thePP.pp_lost_oxygen = PcuAnswer.Zero();
        else
            thePP.pp_lost_oxygen = PcuAnswer.One();
        
        if(jRadioButtonNoInject.isSelected())
            thePP.pp_vit_k = PcuAnswer.Zero();
        else
            thePP.pp_vit_k = PcuAnswer.One();
        
        thePP.pp_resuscitation = Gutil.CheckReservedWords(jTextAreaResuscitation.getText());          
        thePP.pp_diseases_abnormal = Gutil.CheckReservedWords(jTextAreaDiseasesAbnormal.getText());
        thePP.pp_apgar_heart_rate_one_minute = (String)jTableApgarScore.getValueAt(0,1);
        thePP.pp_apgar_respiration_effort_one_minute = (String)jTableApgarScore.getValueAt(1,1);
        thePP.pp_apgar_muscle_tone_one_minute = (String)jTableApgarScore.getValueAt(2,1);
        thePP.pp_apgar_reflex_irritability_one_minute = (String)jTableApgarScore.getValueAt(3,1);
        thePP.pp_apgar_skin_color_one_minute = (String)jTableApgarScore.getValueAt(4,1);
        thePP.pp_apgar_total_score_one_minute = (String)jTableApgarScore.getValueAt(5,1);
        thePP.pp_apgar_heart_rate_five_minute = (String)jTableApgarScore.getValueAt(0,2);
        thePP.pp_apgar_respiration_effort_five_minute = (String)jTableApgarScore.getValueAt(1,2);    
        thePP.pp_apgar_muscle_tone_five_minute = (String)jTableApgarScore.getValueAt(2,2);
        thePP.pp_apgar_reflex_irritability_five_minute = (String)jTableApgarScore.getValueAt(3,2);
        thePP.pp_apgar_skin_color_five_minute = (String)jTableApgarScore.getValueAt(4,2);
        thePP.pp_apgar_total_score_five_minute = (String)jTableApgarScore.getValueAt(5,2);
        thePP.pp_apgar_heart_rate_ten_minute = (String)jTableApgarScore.getValueAt(0,3);
        thePP.pp_apgar_respiration_effort_ten_minute = (String)jTableApgarScore.getValueAt(1,3);
        thePP.pp_apgar_muscle_tone_ten_minute = (String)jTableApgarScore.getValueAt(2,3);
        thePP.pp_apgar_reflex_irritability_ten_minute = (String)jTableApgarScore.getValueAt(3,3);
        thePP.pp_apgar_skin_color_ten_minute = (String)jTableApgarScore.getValueAt(4,3);
        thePP.pp_apgar_total_score_ten_minute = (String)jTableApgarScore.getValueAt(5,3);        
        thePP.pp_fronto_occipital = integerTextFieldFrontoOccipital.getText();  
        thePP.pp_length = integerTextFieldLength.getText();     
        thePP.pp_chest = integerTextFieldChest.getText();         
        thePP.pp_tempperature = integerTextFieldT.getText();  
        thePP.pp_state = Gutil.getGuiData(jComboBoxInfantState);      
        thePP.pp_dead_type = "";
        thePP.pp_dead_cause = Gutil.getGuiData(jComboBoxCauseOfDead); 
        
        thePP.pp_dead_date = dateComboBoxDateDead.getText();        
        thePP.pp_dead_time = timeTextFieldTimeDead.getText();      
        thePP.pp_comment = Gutil.CheckReservedWords(jTextAreaComment.getText());   
        return thePP;
    }
    
    private void deletePP()
    {
        if(this.jTablePP.getSelectedRow()==-1){
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ",UpdateStatus.WARNING);
            return;
        }
        boolean ret = thePPControl.deletePP(thePP);
        if(!ret)
            return;
        setPPV(thePPControl.listPPCareByPatientID(pcuobject.getPatient().getObjectId()));
    }
    
    private void clearTableApgarScore()
    {   tm.setValueAt("",0,1); 
        tm.setValueAt("",1,1);  
        tm.setValueAt("",2,1);
        tm.setValueAt("",3,1);
        tm.setValueAt("",4,1);
        tm.setValueAt("",5,1);
        tm.setValueAt("",0,2); 
        tm.setValueAt("",1,2);  
        tm.setValueAt("",2,2);
        tm.setValueAt("",3,2);
        tm.setValueAt("",4,2);
        tm.setValueAt("",5,2);
        tm.setValueAt("",0,3); 
        tm.setValueAt("",1,3);  
        tm.setValueAt("",2,3);
        tm.setValueAt("",3,3);
        tm.setValueAt("",4,3);
        tm.setValueAt("",5,3);
        tm.setEditingCol(1,2,3);
    }
    
    public void setPP(PP pp)
    {
        thePP = pp;
        if(thePP == null){
            thePP = new PP();
            jTablePP.clearSelection();
            thePP.pp_mother_pid = this.pcuobject.getFamily().pid;
            thePP.pp_modify_date_time = pcuobject.getCurrentDateTime();
            ComboboxModel.setCodeComboBox(jComboBoxSex,"3");
        }
        this.dateComboBoxCheck.setText(DateUtil.convertFieldDate(thePP.pp_modify_date_time));
        String timeCheck = "";
        if(thePP.pp_modify_date_time.length()>11)
            timeCheck = thePP.pp_modify_date_time.substring(11);           
        this.timeTextFieldCheck.setText(timeCheck);
        
        dateComboBoxSurvey.setText(Gutil.convertFieldDate(thePP.survey_date));
        jLabelSurveyDate.setSelected(!dateComboBoxSurvey.getText().equals(""));
        dateComboBoxSurvey.setEnabled(!dateComboBoxSurvey.getText().equals(""));
        this.jLabelVN.setText("");
        if(!thePP.t_visit_id.equals("")){
            String vn_id = this.theAllComboBoxControl.readVNbyVid(thePP.t_visit_id);
            this.jLabelVN.setText("VN:"+vn_id);
        }
        integerTextFieldInfantNumber.setText(thePP.pp_number);
        //บอกว่าเป็นข้อมูลคลอดของแม่หรือลูก
        pIDPanelMother.setText(thePP.pp_mother_pid); 
        integerTextFieldGravida.setText(thePP.pp_gravida);
        if(thePP.pp_initial_condition.equals(PcuAnswer.Zero()))
            jRadioButtonICPoor.setSelected(true);
        else
            jRadioButtonICGood.setSelected(true);
        
        if(thePP.pp_color.equals(PcuAnswer.Zero()))
            jRadioButtonBlue.setSelected(true);
        else
            jRadioButtonPink.setSelected(true);
        
        if(thePP.pp_activity.equals(PcuAnswer.Zero()))
            jRadioButtonActivityWeak.setSelected(true);
        else
            jRadioButtonActivityGood.setSelected(true);
        
        if(thePP.pp_lost_oxygen.equals(PcuAnswer.Zero()))
            jRadioButtonPPLostO2Yes.setSelected(true);
        else
            jRadioButtonPPLostO2No.setSelected(true);
        
        if(thePP.pp_vit_k.equals(PcuAnswer.Zero()))
            jRadioButtonNoInject.setSelected(true);
        else
            jRadioButtonInject.setSelected(true);
        
        ComboboxModel.setCodeComboBox(jComboBoxBreathingTime,thePP.pp_breathing_time);
        ComboboxModel.setCodeComboBox(jComboBoxCryingTime,thePP.pp_crying_time);
        ComboboxModel.setCodeComboBox(jComboBoxAspiration,thePP.pp_aspiration);
//        ComboboxModel.setCodeComboBox(jComboBoxSex,thePP.);
        jTextFieldWeight.setText(thePP.pp_weight);     
        ComboboxModel.setCodeComboBox(jComboBoxEyeDrug,thePP.pp_eye_drug);
        ComboboxModel.setCodeComboBox(jComboBoxMethod,thePP.pp_method_bearing);
        jTextAreaResuscitation.setText(thePP.pp_resuscitation); 
        jTextAreaDiseasesAbnormal.setText(thePP.pp_diseases_abnormal);
        tm.setValueAt(thePP.pp_apgar_heart_rate_one_minute,0,1); 
        tm.setValueAt(thePP.pp_apgar_respiration_effort_one_minute,1,1);  
        tm.setValueAt(thePP.pp_apgar_muscle_tone_one_minute,2,1);
        tm.setValueAt(thePP.pp_apgar_reflex_irritability_one_minute,3,1);
        tm.setValueAt(thePP.pp_apgar_skin_color_one_minute,4,1);
        tm.setValueAt(thePP.pp_apgar_total_score_one_minute,5,1);        
        tm.setValueAt(thePP.pp_apgar_heart_rate_five_minute,0,2);
        tm.setValueAt(thePP.pp_apgar_respiration_effort_five_minute,1,2); 
        tm.setValueAt(thePP.pp_apgar_muscle_tone_five_minute,2,2);  
        tm.setValueAt(thePP.pp_apgar_reflex_irritability_five_minute,3,2);
        tm.setValueAt(thePP.pp_apgar_skin_color_five_minute,4,2);
        tm.setValueAt(thePP.pp_apgar_total_score_five_minute,5,2);        
        tm.setValueAt(thePP.pp_apgar_heart_rate_ten_minute,0,3);
        tm.setValueAt(thePP.pp_apgar_respiration_effort_ten_minute,1,3); 
        tm.setValueAt(thePP.pp_apgar_muscle_tone_ten_minute,2,3);  
        tm.setValueAt(thePP.pp_apgar_reflex_irritability_ten_minute,3,3);
        tm.setValueAt(thePP.pp_apgar_skin_color_ten_minute,4,3);
        tm.setValueAt(thePP.pp_apgar_total_score_ten_minute,5,3);   
        integerTextFieldFrontoOccipital.setText(thePP.pp_fronto_occipital);           
        integerTextFieldLength.setText(thePP.pp_length);
        integerTextFieldChest.setText(thePP.pp_chest); 
        integerTextFieldT.setText(thePP.pp_tempperature); 
        ComboboxModel.setCodeComboBox(jComboBoxInfantState,thePP.pp_state);   
        ComboboxModel.setCodeComboBox(jComboBoxCauseOfDead,thePP.pp_dead_cause);  
        dateComboBoxDateDead.setText(Gutil.convertFieldDate(thePP.pp_dead_date)); 
        //dateComboBoxDateDead.setText(thePP.pp_dead_date);  
        timeTextFieldTimeDead.setText(thePP.pp_dead_time);   
        jTextAreaComment.setText(thePP.pp_comment);  
        /*เมื่อเลือกเป็น มีชีวิต ข้อมูลการตายจะถูก disable ไว้ */
        if(("1").equals(thePP.pp_state))
        {
//            jComboBoxDeadType.setEnabled(false);
//            jComboBoxCauseOfDead.setEnabled(false);
//            dateComboBoxDateDead.setEnabled(false);
//            timeTextFieldTimeDead.setEnabled(false);
        }
        else
        {
            jComboBoxCauseOfDead.setEnabled(true);
            dateComboBoxDateDead.setEnabled(true);
            timeTextFieldTimeDead.setEnabled(true);
        }
        Family child = theHC.thePatientControl.readFamilyByFamilyIdRet(thePP.family_id);
        if(child!=null)
        {
            ComboboxModel.setCodeComboBox(jComboBoxSex,child.f_sex_id);
            jComboBoxSex.setEnabled(false);
        }
        else
        {
            jComboBoxSex.setEnabled(true);
        }


    }
    
    public void notifyApgarScore(String[] arrayApgarScoreNotify)
    {
        arrayAgarScore = arrayApgarScoreNotify;
        if(help==1)
        {
            thePP.pp_apgar_heart_rate_per_minute_one_minute = arrayAgarScore[6];
        }
        if(help==5)
        {
            thePP.pp_apgar_heart_rate_per_minute_five_minute = arrayAgarScore[6];
        }
        if(help==10)
        {
            thePP.pp_apgar_heart_rate_per_minute_ten_minute = arrayAgarScore[6];
        }
        setTableApgarScore();  
        
        /*
         * amp
         * ตรวจสอบภาวะการขาดอ็อกซิเจน ถ้านะนาทีไหนก็ตามน้อยกว่าเท่ากับ7ให้มีภาวะการขาดอ็อกซิเจน
         * 99 = ตารางเป็นค่าว่าง
         **/
        int one = 99;
        int five = 99;
        int ten = 99;
        if(("").equals(String.valueOf(jTableApgarScore.getValueAt(5, 1))))
            one = 99;
        else
            one = Integer.parseInt(String.valueOf(jTableApgarScore.getValueAt(5, 1)));
        if(("").equals(String.valueOf(jTableApgarScore.getValueAt(5, 2))))
            five = 99;
        else
            five = Integer.parseInt(String.valueOf(jTableApgarScore.getValueAt(5, 2)));
        if(("").equals(String.valueOf(jTableApgarScore.getValueAt(5, 3))))
            ten = 99;
        else
            ten = Integer.parseInt(String.valueOf(jTableApgarScore.getValueAt(5, 3)));
        
        if(one <= 7 || five <=7 || ten <=7)
            jRadioButtonPPLostO2Yes.setSelected(true); 
        else
            jRadioButtonPPLostO2No.setSelected(true); 
    }
    
    private void checkDateControl()
    {
        if(!dateComboBoxDateDead.getText().equals("") 
            && dateComboBoxDateDead.getText().length()==10 
            && com.pcu.utility.DateUtil.countDay(dateComboBoxDateDead.getText(),theHosManage.theHosControl.theConnectionInf) == -1 
            && com.pcu.utility.DateUtil.isToday(com.pcu.utility.DateUtil.getDateFromText(dateComboBoxDateDead.getText()),theHosManage.theHosControl.theConnectionInf)==false)  
        {            
             if(checkdead == false)
             {
                 // ไม่สามารถกรอกวันที่เป็นวันในอนาคตได้
                theUS.setStatus(GutilPCU.getTextBundle("NoDateFuture") , UpdateStatus.WARNING);
                dateComboBoxDateDead.setText("");                              
                checkdead = true;
             }

        }  
        if(checkdead)
        {
            dateComboBoxDateDead.setText("");    
        }
    }
    private void printPP()
    {
        if(thePP!=null&&!thePP.pp_number.equals(""))
            theHosManage.theHosControl.thePrintControl.printPP(thePP);
        else
            theUS.setStatus(GutilPCU.getTextBundle("Data_PP_NULL"), UpdateStatus.WARNING);
    }
    /**
     * ตรวจสอบว่าวันออกตรวจเป็นวันในอนาคตหรือไม่
     * @param  
     * @return 
     * @author kingland
     * @date 17-03-2549
     */ 
    private void checkDateSurvay()
    {        
        if(!dateComboBoxSurvey.getText().equals("") 
            && dateComboBoxSurvey.getText().length()==10 
            && com.pcu.utility.DateUtil.countDay(dateComboBoxSurvey.getText(),theHosManage.theHosControl.theConnectionInf) == -1 
            && com.pcu.utility.DateUtil.isToday(com.pcu.utility.DateUtil.getDateFromText(dateComboBoxSurvey.getText()),theHosManage.theHosControl.theConnectionInf)==false)  
        {            
             if(checkSurvey == false)
             {
                 // ไม่สามารถกรอกวันที่เป็นวันในอนาคตได้
                theUS.setStatus(GutilPCU.getTextBundle("NoDateFuture") , UpdateStatus.WARNING);
                checkSurvey= true;
             }
        }  
    }
    private void setPPV(Vector vector)
    {
        vChildren = vector;
        TableModel tm = new TableModel(col,vChildren.size());
        for(int i=0 ;i<vChildren.size(); i++) 
        {  
            PP vs = (PP)vChildren.get(i);            
            tm.setValueAt(GutilPCU.changDateToString(vs.pp_record_date_time,false),i,1);
            tm.setValueAt(vs.pp_number,i,0);
        }
        
        int select = jTablePP.getSelectedRow();
        int old_size = jTablePP.getRowCount();
        
        jTablePP.setModel(tm); 
        jTablePP.getColumnModel().getColumn(1).setPreferredWidth(400);
        jTablePP.getColumnModel().getColumn(1).setCellRenderer(TableRenderer.getRendererCenter());
        jTablePP.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTablePP.getColumnModel().getColumn(0).setCellRenderer(TableRenderer.getRendererRight());
        
        //คือการเพิ่มและเลือกใหม่
        if(select==-1 && vChildren.size()>old_size)
            select = vChildren.size()-1;
        else if(select!=-1 && vChildren.size()<old_size)
            select = -1;
        
        if(select!=-1){
            jTablePP.setRowSelectionInterval(select,select);
            setPP((PP)vChildren.get(select));
        }
        else
        {
            setPP(this.thePP);
        }
    }

    @Override
    public void notifySavePP(PP pp) {

    }
}
