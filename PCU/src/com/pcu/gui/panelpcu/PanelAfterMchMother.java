
/*
 * PanelAfterMchMother.java
 *
 * Created on 13 �Զع�¹ 2548, 18:24 �.
 */
/*
 * ��Ǩ�ͺ�ѹ������Ǩ����
 * kingland
 */
package com.pcu.gui.panelpcu;
import com.hospital_os.object.*;
import com.pcu.control.AllComboBoxControl;
import com.pcu.control.HosManage;
import com.pcu.control.PCUControl;
import com.pcu.control.PCUObject;
import com.pcu.gui.dialog.HosDialog;
import java.util.Vector;
import javax.swing.*;
import com.pcu.utility.*;
import com.pcu.object.*;
import com.pcu.subject.ManageAfterMchResp;
import com.pcu.utility.TableModel;
import com.hospital_os.utility.DateUtil;
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.object.Patient;
import com.hospital_os.object.VisitStatus;
import com.hospital_os.utility.Gutil;
import com.hosv3.usecase.transaction.*;
import com.hosv3.utility.connection.UpdateStatus;
import java.awt.Dimension;
import java.awt.Toolkit;
/**
 *
 *
 *
 * @author  tong(Padungrat)
 *
 */

public class PanelAfterMchMother extends javax.swing.JPanel implements
    ManageAfterMchResp,
   ManagePatientResp, PanelObj
{
    JFrame theFrame;
    private int receiveNotify = 2;       
    private boolean checksurvey;    
    private boolean selectData;/**�����ŷ����㹡�á�˹���÷ӧҹ�ͧ panel ����� true �ӧҹ���� ����� false ���ӧҹ������������*/    
    private HosManage theHosManage;    
    private PCUControl theHC;    
    private HosDialog theHosDialog;    
    private Appointment theAppointment;    
    private AfterMchMother theAfterMchMother,theAfterMchMotherTemp;    
    private BornMch theBornMch;    
    public Vector vMchMother;
    private String staff_id;    
    private String[] headTableAfterMCHMother;    
    private TableModel tablemodel;    
    private String visit_status;/**��㹡�ú͡ʶҹТͧ��� visit ���駹���*/    
    private PCUObject pcuobject;    
    private Visit theVisit;    
    private Patient thePatient;    
    private Family theFamily;    
    private Employee theEmployee;    

    private AllComboBoxControl theAllComboBoxControl;
    private UpdateStatus theUS;
    private JDialog theJD;
    PanelAfterMchChild thePanelAfterMchChild;
    /** Creates new form JPanelFp */
    
    public PanelAfterMchMother()
    {
        initComponents();
        jButton1.setVisible(false);
        jButtonContinue.setVisible(false);
    }
    public PanelAfterMchMother(HosManage hm,HosDialog hd,UpdateStatus us)
    {   initComponents();
        setControl(hm,hd,us);
    }
    public void setPanelAfterMchChild(PanelAfterMchChild panelAfterMchChild)
    {
        thePanelAfterMchChild = panelAfterMchChild;
    }
    public void showDialog(boolean b)
    {
        if(theJD==null)
            theJD = new JDialog(theUS.getJFrame());
        theJD.add(this);
        theJD.setSize(720,490);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, (screenSize.height - theJD.getSize().height) / 2);
        theJD.setTitle("���������ѧ��ʹ");
        theJD.setModal(true);
        jButtonContinue.setVisible(b);
        jButton1.setVisible(b);
        theJD.setModal(true);
        theJD.setVisible(true);
    }
    public void setControl(HosManage hm,HosDialog hd,UpdateStatus us)
    {
        theHosManage = hm;
        theUS = us;
        theHC = hm.theHosControl;       
        theHosDialog = hd;        
        pcuobject = hm.thePO;        
        theAllComboBoxControl = hm.theHosControl.theAllComboBoxControl;
        theHosManage.theHosSubject.theAfterMchSubject.attachManageAfterBornMch(this);        
//        theHosManage.theHosControl.theHcHospitalOS.theHS.thePatientSubject.attachManagePatient(this);        
//        initBalloon();       
        initCombobox();        
        headTableAfterMCHMother = new String[3];        
        headTableAfterMCHMother[0] = "�������";        
        headTableAfterMCHMother[1] = "���駷��";
        headTableAfterMCHMother[2] = "�ѹ�֡";        
        selectData = true;        
        setLanguage();        
        setAfterMchMotherV(null);        
    }
    
    public void setJFrame(JFrame frame)
    {   setJFrame(frame,true);
       
    }
       
    public void setJFrame(JFrame frame,boolean show_link)
    {   theFrame = frame;
       
    }
    
    /**
     *
     * �觤�Ң����� JFrame
     *
     * @param -
     *
     * @return JFrame
     *
     * @author kingland
     *
     * @date 28/08/2549
     *
     */
    
    private JFrame getJFrame()    
    {   return theHosManage.theHosInf.getJFrame();        
    }
       
    
//    private void initBalloon()
//    {   theHosManage.theHosControl.balloon.add(jTextAreaNote);
//        jTextAreaNote.setControl(theHosManage.theHosInf.getVitalTemplate());
//        jTextAreaNote.setJFrame(theHosManage.theHosInf.getJFrame());
//    }
    
    /**
     *
     *��㹡�� �Ѻ��� PCUObject �ҡ PanelPCU ������ա���觢����š�Ѻ�Ңͧ Transaction
     *
     *@param pcuObject
     *
     *@return void
     *
     *@author Tong
     *
     *@modify kingland
     *
     *@date 04/09/2549
     *
     */
    
    public void setObject(PCUObject pcuobject)
    
    {   System.out.println("_henbe_______________________" + this.getClass().toString());
        setInitDefaultDataGUI(false);
        this.setEnabled(true);
        selectData = true;
        this.pcuobject = pcuobject;
        theBornMch = null;
        getObject1();
        refreshTableMCHMother();
        if(pcuobject.getFamily()==null){
            setEnabled(false);
            return;
        }
        
        if(checkDead()){
            setEnabled(false);
            return;
        }
        
        if(!checkSex(false)){
            setEnabled(false);
            return;
        }
    }
    
    /**setPPV
     *
     *��ҹ��Ң����Ũҡ PcuObject
     *
     *@param -
     *
     *@return void
     *
     *@author amp
     *
     *@modify kingland
     *
     *@date 04/09/2549
     *
     */
    
    public void getObject1()
    
    {   theAfterMchMother = null;
        
        if(pcuobject != null)
            
        {   theEmployee = pcuobject.getEmployee();
            
            theVisit = pcuobject.getVisit();
            
            thePatient = pcuobject.getPatient();
            
            theFamily = pcuobject.getFamily();
            
            if(pcuobject.getEmployee() != null)
                
            {   staff_id = pcuobject.getEmployee().getObjectId();
                
            }
            
            String visit_id = null;
            
            if(pcuobject.getVisit() != null)
                
            {   
                
                visit_id = pcuobject.getVisit().getObjectId();
                
                if(pcuobject.getVisit().visit_status.equalsIgnoreCase(VisitStatus.isInProcess()))
                    
                {   jButtonSave.setEnabled(true);
                    
                    jButtonAdd.setEnabled(true);
                    
                }
                
                else
                    
                {   System.out.println("Old Visit ");
                    
                }
                
            }
            
            else
                
            {
                
//                 jButtonAdd.setEnabled(false);
                
            }
            
            if(pcuobject.getPatient() != null)
                
            {   
                checkServicedForVisit();
                
                checkDataForVisit(true);
                
                setEnableGui(true);
                
                jButtonAdd.setEnabled(true);
                
                jButtonSave.setEnabled(true);
                
                jButtonPrint.setEnabled(true);
                
            }
            
            else
                
            {   setAfterMchMotherV(null);
                
            }
            
        }
        
    }
    
    
    
    public void setObjectFamily(Family family)
    
    {
        
        System.out.println("_henbe other_______________________" + this.getClass().toString());
        
        theAfterMchMother = new AfterMchMother();
        
        Family theFamily = family;
        
        System.out.println("pcuobject.getPatient()==null:" + pcuobject.getPatient()==null);
        
        System.out.println("theFamily==null:" + theFamily==null);
        
        Patient thePatient = null;
        
        if(theFamily!=null)
            
        {
            
            setEnableGui(true);
            integerTextFieldVisitNumber.setEnabled(true);
            
            jTextFieldPregnantNumber.setEnabled(true);
            
            jButtonAdd.setEnabled(true);
            
//            jButtonSave.setEnabled(true);
            jButtonSave.setEnabled(true);
            
            jButtonPrint.setEnabled(true);
            
        }
        
        if(pcuobject.getEmployee()!=null)
            
        {
            
            staff_id = pcuobject.getEmployee().getObjectId();
            
        }
        
        refreshTableMCHMother();
        
    }
    
    
    
    
    
    
    
    private void initCombobox()
    
    {   String yes = GutilPCU.setLanguage("����");
        
        String no = GutilPCU.setLanguage("�Դ����");
        
        ComboboxModel.initComboBox(jComboBoxCheckPlace,theHC.theAllComboBoxControl.listPostpartumBirthPlace());
        
        ComboboxModel.initComboBox(jComboBoxSew,theHC.theAllComboBoxControl.listPostpartumEpisiotomyType());
        
        ComboboxModel.initComboBox(jComboBoxSugarLevel,theHC.theAllComboBoxControl.listPregnancyPregnantLevel());
        
        ComboboxModel.initComboBox(jComboBoxAlblumin,theHC.theAllComboBoxControl.listPregnancyPregnantLevel());
        
        setInitComboBox();
        
        panelYesNoRadioButtonUterus_level.setTextYesNo(yes,no);
        
        panelYesNoRadioButtonCream.setTextYesNo(yes,no);
        
        panelYesNoRadioButtonMenses.setTextYesNo(yes,no);
        
        panelYesNoRadioButtonResultVerify.setTextYesNo(yes,no);
        
        yes = GutilPCU.setLanguage("��");
        
        no = GutilPCU.setLanguage("�����");
        
        panelYesNoRadioButtonUterus_Lochia.setTextYesNo(yes,no);
        
        yes = GutilPCU.setLanguage("���");
        
        no = GutilPCU.setLanguage("������");
        
        panelYesNoRadioButtonUterus_Milk_Seep.setTextYesNo(yes,no);
        
        panelYesNoRadioButtonUterus_level.setSelected(true);
        
        panelYesNoRadioButtonCream.setSelected(true);
        
        panelYesNoRadioButtonMenses.setSelected(true);
        
        panelYesNoRadioButtonResultVerify.setSelected(true);
        
        panelYesNoRadioButtonUterus_Lochia.setSelected(true);
        
    }
    
    
    
    /** This method is called from within the constructor to
     *
     * initialize the form.
     *
     * WARNING: Do NOT modify this code. The content of this method is
     *
     * always regenerated by the Form Editor.
     *
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jPanelAfterMchMomList = new javax.swing.JPanel();
        jScrollPaneAfterMchMomList = new javax.swing.JScrollPane();
        jTableAfterMchMother = new javax.swing.JTable();
        jPanelAfterMchMomDetail = new javax.swing.JPanel();
        jPanelAfterMomDetail = new javax.swing.JPanel();
        jLabelCheckPlace = new javax.swing.JLabel();
        jComboBoxCheckPlace = new javax.swing.JComboBox();
        jLabelUterus = new javax.swing.JLabel();
        jLabelCream = new javax.swing.JLabel();
        jLabelMilk = new javax.swing.JLabel();
        jLabelAlblumin = new javax.swing.JLabel();
        jComboBoxAlblumin = new javax.swing.JComboBox();
        jLabelSugarLevel = new javax.swing.JLabel();
        jComboBoxSugarLevel = new javax.swing.JComboBox();
        jLabelLochia = new javax.swing.JLabel();
        jLabelMenses = new javax.swing.JLabel();
        jLabelSew = new javax.swing.JLabel();
        jComboBoxSew = new javax.swing.JComboBox();
        jLabelResult = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldPregnantNumber = new com.pcu.utility.IntegerTextField();
        jLabelPregnantNumber = new javax.swing.JLabel();
        jLabelVisitNumber = new javax.swing.JLabel();
        integerTextFieldVisitNumber = new com.pcu.utility.IntegerTextField();
        jPanelRelationBornMch = new javax.swing.JPanel();
        panelYesNoRadioButtonCream = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        panelYesNoRadioButtonUterus_level = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        panelYesNoRadioButtonMenses = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        panelYesNoRadioButtonResultVerify = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        panelYesNoRadioButtonUterus_Lochia = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        panelYesNoRadioButtonUterus_Milk_Seep = new com.pcu.gui.utility.component.PanelYesNoRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabelAppointment = new javax.swing.JLabel();
        jLabelSurveyDate = new javax.swing.JCheckBox();
        jLabelNextAppDate = new javax.swing.JLabel();
        jButtonAppointment = new javax.swing.JButton();
        dateComboBoxSurvey = new com.pcu.utility.DateComboBox();
        jPanelResultAfterMchMom = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaNote = new javax.swing.JTextArea();
        jPanelRemark = new javax.swing.JPanel();
        jLabelRemark = new javax.swing.JLabel();
        jTextFieldRemarkNote = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        dateComboBoxCheck = new com.hospital_os.utility.DateComboBox();
        jLabel6 = new javax.swing.JLabel();
        timeTextFieldCheck = new com.hospital_os.utility.TimeTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabelVN = new javax.swing.JLabel();
        jPanelAfterMchMomControl = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonDel = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jButtonPrint = new javax.swing.JButton();
        jButtonContinue = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanelAfterMchMomList.setBorder(javax.swing.BorderFactory.createTitledBorder("������ѧ��ʹ(���)"));
        jPanelAfterMchMomList.setMinimumSize(new java.awt.Dimension(175, 120));
        jPanelAfterMchMomList.setPreferredSize(new java.awt.Dimension(175, 120));
        jPanelAfterMchMomList.setLayout(new java.awt.GridBagLayout());

        jScrollPaneAfterMchMomList.setEnabled(false);
        jScrollPaneAfterMchMomList.setMinimumSize(new java.awt.Dimension(240, 23));
        jScrollPaneAfterMchMomList.setPreferredSize(new java.awt.Dimension(269, 100));

        jTableAfterMchMother.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableAfterMchMotherMouseReleased(evt);
            }
        });
        jScrollPaneAfterMchMomList.setViewportView(jTableAfterMchMother);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanelAfterMchMomList.add(jScrollPaneAfterMchMomList, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        add(jPanelAfterMchMomList, gridBagConstraints);

        jPanelAfterMchMomDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("AfterMchMom_Detail"));
        jPanelAfterMchMomDetail.setLayout(new java.awt.GridBagLayout());

        jPanelAfterMomDetail.setFont(new java.awt.Font("Dialog", 0, 8));
        jPanelAfterMomDetail.setLayout(new java.awt.GridBagLayout());

        jLabelCheckPlace.setText("CheckPlace");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 0, 0);
        jPanelAfterMomDetail.add(jLabelCheckPlace, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 1, 0, 0);
        jPanelAfterMomDetail.add(jComboBoxCheckPlace, gridBagConstraints);

        jLabelUterus.setText("Uterus_level");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanelAfterMomDetail.add(jLabelUterus, gridBagConstraints);

        jLabelCream.setText("Cream");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanelAfterMomDetail.add(jLabelCream, gridBagConstraints);

        jLabelMilk.setText("Milk_seep");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanelAfterMomDetail.add(jLabelMilk, gridBagConstraints);

        jLabelAlblumin.setText("Alblumin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 0, 0);
        jPanelAfterMomDetail.add(jLabelAlblumin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 0, 0);
        jPanelAfterMomDetail.add(jComboBoxAlblumin, gridBagConstraints);

        jLabelSugarLevel.setText("SugarLevel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 0, 0);
        jPanelAfterMomDetail.add(jLabelSugarLevel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 0, 0);
        jPanelAfterMomDetail.add(jComboBoxSugarLevel, gridBagConstraints);

        jLabelLochia.setText("Lochia");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanelAfterMomDetail.add(jLabelLochia, gridBagConstraints);

        jLabelMenses.setText("Menses");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanelAfterMomDetail.add(jLabelMenses, gridBagConstraints);

        jLabelSew.setText("Sew");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 10, 0, 0);
        jPanelAfterMomDetail.add(jLabelSew, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 1, 0, 0);
        jPanelAfterMomDetail.add(jComboBoxSew, gridBagConstraints);

        jLabelResult.setText("Result_verify");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanelAfterMomDetail.add(jLabelResult, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jTextFieldPregnantNumber.setColumns(2);
        jTextFieldPregnantNumber.setText("integerTextField1");
        jTextFieldPregnantNumber.setMinimumSize(new java.awt.Dimension(30, 21));
        jTextFieldPregnantNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPregnantNumberKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jTextFieldPregnantNumber, gridBagConstraints);

        jLabelPregnantNumber.setText("PregnantNumber");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabelPregnantNumber, gridBagConstraints);

        jLabelVisitNumber.setText("CareTime");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jLabelVisitNumber, gridBagConstraints);

        integerTextFieldVisitNumber.setColumns(3);
        integerTextFieldVisitNumber.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        integerTextFieldVisitNumber.setText("1");
        integerTextFieldVisitNumber.setMinimumSize(new java.awt.Dimension(30, 21));
        integerTextFieldVisitNumber.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                integerTextFieldVisitNumberFocusLost(evt);
            }
        });
        integerTextFieldVisitNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldVisitNumberKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(integerTextFieldVisitNumber, gridBagConstraints);

        jPanelRelationBornMch.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jPanelRelationBornMch, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelAfterMomDetail.add(jPanel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 0, 0);
        jPanelAfterMomDetail.add(panelYesNoRadioButtonCream, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 0, 0);
        jPanelAfterMomDetail.add(panelYesNoRadioButtonUterus_level, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 0, 0);
        jPanelAfterMomDetail.add(panelYesNoRadioButtonMenses, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 0, 0);
        jPanelAfterMomDetail.add(panelYesNoRadioButtonResultVerify, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 0, 0);
        jPanelAfterMomDetail.add(panelYesNoRadioButtonUterus_Lochia, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 0, 0);
        jPanelAfterMomDetail.add(panelYesNoRadioButtonUterus_Milk_Seep, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jLabelAppointment, gridBagConstraints);

        jLabelSurveyDate.setText("SurveyDate");
        jLabelSurveyDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jLabelSurveyDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabelSurveyDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel2.add(jLabelSurveyDate, gridBagConstraints);

        jLabelNextAppDate.setText("NextAppDate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 0, 0);
        jPanel2.add(jLabelNextAppDate, gridBagConstraints);

        jButtonAppointment.setText("�Ѵ");
        jButtonAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAppointmentActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 1, 0, 0);
        jPanel2.add(jButtonAppointment, gridBagConstraints);

        dateComboBoxSurvey.setEnabled(false);
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
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 6, 0, 0);
        jPanel2.add(dateComboBoxSurvey, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 0, 0);
        jPanelAfterMomDetail.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanelAfterMchMomDetail.add(jPanelAfterMomDetail, gridBagConstraints);

        jPanelResultAfterMchMom.setBorder(javax.swing.BorderFactory.createTitledBorder("ResultAfterMchChild"));
        jPanelResultAfterMchMom.setLayout(new java.awt.GridBagLayout());

        jTextAreaNote.setColumns(20);
        jTextAreaNote.setRows(5);
        jScrollPane1.setViewportView(jTextAreaNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelResultAfterMchMom.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        jPanelAfterMchMomDetail.add(jPanelResultAfterMchMom, gridBagConstraints);

        jPanelRemark.setLayout(new java.awt.GridBagLayout());

        jLabelRemark.setText("Remark");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanelRemark.add(jLabelRemark, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelRemark.add(jTextFieldRemarkNote, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanelAfterMchMomDetail.add(jPanelRemark, gridBagConstraints);

        jPanel16.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("�ѹ���ѹ�֡");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 3);
        jPanel16.add(jLabel5, gridBagConstraints);

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

        jLabel6.setText("�.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 1);
        jPanel16.add(jLabel6, gridBagConstraints);

        timeTextFieldCheck.setMinimumSize(new java.awt.Dimension(45, 23));
        timeTextFieldCheck.setPreferredSize(new java.awt.Dimension(45, 23));
        timeTextFieldCheck.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                timeTextFieldCheckKeyReleased(evt);
            }
        });
        timeTextFieldCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timeTextFieldCheckMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 3);
        jPanel16.add(timeTextFieldCheck, gridBagConstraints);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/clock.gif"))); // NOI18N
        jLabel9.setToolTipText("���ҷ���Ǩ");
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
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 9, 0);
        jPanelAfterMchMomDetail.add(jPanel16, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jPanelAfterMchMomDetail, gridBagConstraints);

        jPanelAfterMchMomControl.setLayout(new java.awt.GridBagLayout());

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelAfterMchMomControl.add(jButtonSave, gridBagConstraints);

        jButtonDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
        jButtonDel.setMaximumSize(new java.awt.Dimension(60, 26));
        jButtonDel.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 1);
        jPanelAfterMchMomControl.add(jButtonDel, gridBagConstraints);

        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAdd.setMaximumSize(new java.awt.Dimension(50, 26));
        jButtonAdd.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAfterMchMomControl.add(jButtonAdd, gridBagConstraints);

        jButtonPrint.setText("Print");
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelAfterMchMomControl.add(jButtonPrint, gridBagConstraints);

        jButtonContinue.setText("��");
        jButtonContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonContinueActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        jPanelAfterMchMomControl.add(jButtonContinue, gridBagConstraints);

        jButton1.setText("<< ��͹��Ѻ");
        jButton1.setMargin(new java.awt.Insets(2, 7, 2, 7));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        jPanelAfterMchMomControl.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        add(jPanelAfterMchMomControl, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelSurveyDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLabelSurveyDateActionPerformed
        this.dateComboBoxSurvey.setEnabled(jLabelSurveyDate.isSelected());
        if(!jLabelSurveyDate.isSelected())
            dateComboBoxSurvey.setText("");
    }//GEN-LAST:event_jLabelSurveyDateActionPerformed
    
    
    
    private void timeTextFieldCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeTextFieldCheckMouseClicked
        
        timeTextFieldCheck.selectAll();
        
    }//GEN-LAST:event_timeTextFieldCheckMouseClicked
    
    
    
    private void timeTextFieldCheckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timeTextFieldCheckKeyReleased
        
        
        
    }//GEN-LAST:event_timeTextFieldCheckKeyReleased
    
    
    
    private void dateComboBoxCheckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateComboBoxCheckKeyReleased
        
        
        
    }//GEN-LAST:event_dateComboBoxCheckKeyReleased
    
    
    
    private void dateComboBoxSurveyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxSurveyFocusLost
        
        checksurvey = false;
        
        checkDateSurvey();
        
    }//GEN-LAST:event_dateComboBoxSurveyFocusLost
    
    
    
    private void dateComboBoxSurveyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxSurveyActionPerformed
        
        checkDateSurvey();
        
        checksurvey = false;
        
    }//GEN-LAST:event_dateComboBoxSurveyActionPerformed
    
    
    
    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintActionPerformed
        
        theHC.thePrintControl.printAfterMchMother(vMchMother, pcuobject.getFamily());
        
    }//GEN-LAST:event_jButtonPrintActionPerformed
    
    
    
    private void jTextFieldPregnantNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPregnantNumberKeyReleased
        
        checkCarePregnantNumber();
        
    }//GEN-LAST:event_jTextFieldPregnantNumberKeyReleased
    
    
    
    private void integerTextFieldVisitNumberFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integerTextFieldVisitNumberFocusLost
        
        if(integerTextFieldVisitNumber.getText().equals(""))
            
        {   theUS.setStatus(GutilPCU.getTextBundle("ValueNumberNULL"), 2);
            
        }
        
    }//GEN-LAST:event_integerTextFieldVisitNumberFocusLost
    
    
    
    private void jButtonAppointmentActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAppointmentActionPerformed

    {//GEN-HEADEREND:event_jButtonAppointmentActionPerformed
        
        if(pcuobject.getPatient()==null)
        {
            
            theUS.setStatus(GutilPCU.getTextBundle("No_Patient_Appointment"),UpdateStatus.WARNING);
            
            return;
            
        }
        
        theHosDialog.showDialogAppointment(theHosManage,pcuobject);
        
        receiveNotify = 3;
        
    }//GEN-LAST:event_jButtonAppointmentActionPerformed
    
    
    
    private void integerTextFieldVisitNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldVisitNumberKeyReleased
        
        checkCareVisitNumber();
        
    }//GEN-LAST:event_integerTextFieldVisitNumberKeyReleased
    
    
        
    
        
    
        
    
    
    private void jTableAfterMchMotherMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAfterMchMotherMouseReleased
        selectTableAfterMchMother(jTableAfterMchMother.getSelectedRow(),false);
        jButtonSave.setEnabled(true);
        
    }//GEN-LAST:event_jTableAfterMchMotherMouseReleased
    
    
    
    private void jButtonDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelActionPerformed
        
        deleteAfterMchMother();
        
    }//GEN-LAST:event_jButtonDelActionPerformed
    
    
    
    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        addAfterMchMother(false);
    }//GEN-LAST:event_jButtonAddActionPerformed
    
    
    
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
         saveAfterMchMother();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonContinueActionPerformed
        this.theJD.setVisible(false);
}//GEN-LAST:event_jButtonContinueActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jButtonContinue.setVisible(false);
        theJD.setVisible(false);
        this.thePanelAfterMchChild.showDialog(true);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /**
     *
     * ��㹡�õ�Ǩ�ͺ��á�͡����Ţ
     *
     * @param -
     *
     * @return void
     *
     * @author -
     *
     * @modify kingland
     *
     * @date 28/08/2549
     *
     */
    public void checkCareVisitNumber()
    {
        if(integerTextFieldVisitNumber.getText().equals(""))
            return;
        int number =-1;
        try
        {   number = Integer.parseInt(integerTextFieldVisitNumber.getText());
        }
        catch(Exception ex)
        {   ex.printStackTrace();
        }
        if(number <=0)
        {   theUS.setStatus(GutilPCU.getTextBundle("ValueNumberLess"), 2); //��ô��Ť��駷���ͧ�դ���ҡ���� 0
            integerTextFieldVisitNumber.requestFocus();
            integerTextFieldVisitNumber.selectAll();
        }
        if(number >= 20)
        {   theUS.setStatus(GutilPCU.getTextBundle("ValueNumberMore"), 2); //��ô��Ť��駷���ͧ�դ�ҹ��¡��� 20
            integerTextFieldVisitNumber.requestFocus();
            integerTextFieldVisitNumber.selectAll();
        }
    }
    /**
     *
     * ��Ǩ�ͺ�ӹǹ����Ţ�ͧ�ӴѺ��ö�
     *
     * @param -
     *
     * @return -
     *
     * @author kingland
     *
     * @date 28/08/2549
     *
     */
    public boolean checkCarePregnantNumber()
    {   boolean flag = true;
        int number =-1;
        if(jTextFieldPregnantNumber.getText().equals(""))
            return false;
        try
        {   number = Integer.parseInt(jTextFieldPregnantNumber.getText());
        }
        catch(Exception ex)
        {   ex.printStackTrace();
        }
        if(number <=0)
        {   theUS.setStatus(GutilPCU.getTextBundle("ValuePragnanLess"), 2); //�ӴѺ������� ��ͧ�դ���ҡ���� 0
            jTextFieldPregnantNumber.requestFocus();
            jTextFieldPregnantNumber.selectAll();
            flag = false;
        }
        if(number >= 20)
        {   theUS.setStatus(GutilPCU.getTextBundle("ValuePragnanMore"), 2); //�ӴѺ������� ��ͧ�դ�ҹ��¡��� 20
            jTextFieldPregnantNumber.requestFocus();
            jTextFieldPregnantNumber.selectAll();
            flag = false;
        }
        if(number == -1)
            flag = true;
        return flag;
    }
    /**
     *
     * ���Ǩ�ͺ��Ҽ���������ö�����ҹ�˹�ҹ�����������
     *
     *  ��ͧ�� ˭ԧ, ��������ӡ��� 15 �� ��������ö��ҹ��
     *
     * @param patient �����ż�����
     *
     * @return boolean ����� true ����ö�����ҹ�� ����� false �������ö��ҹ��
     *
     */
    public boolean checkPatientService(Patient patient,boolean first)
    {
        boolean result = false;
        if(patient != null)
        {
            if(patient.f_sex_id.equalsIgnoreCase(Sex.isWOMAN()))
            {
                if(patient.patient_birthday!= null && patient.patient_birthday.trim().length() >0)
                {
                    String age = DateUtil.calculateAge(patient.patient_birthday,pcuobject.getCurrentDateTime());
                    if(age!= null)
                    {
                        if(getStringToInt(age) >= 15)
                            
                        {   result = true;
                            
                        }
                        //�������֧ 15 �� ��ͧ����红����š�ä�ʹ�������
                        else
                            
                        {
                            
                            if(!first)
                                
                            {
                                
                                if(theUS.confirmBox(GutilPCU.getTextBundle("AGELESS15"),UpdateStatus.WARNING))
                                {   result = true;
                                    
                                }
                                
                            }
                            
                            else
                                
                            {result = true;
                             
                            }
                            
                        }
                    }
                }
                
            }
            //�ѧ����к��� �������Ȫ�� �������ö����Ѻ��ԡ����ǹ�����
            else
            {
                if(!first)
                {
                    theUS.setStatus(GutilPCU.getTextBundle("ISSEXWOMAN"),UpdateStatus.WARNING);
                }
            }
        }
        return result;
    }
    /**��㹡���ŧ�����Ũҡ String ��� int
     *
     *@param value �� String �ͧ ����Ţ �������� �ŷ������� -1
     *
     *@return �� int 0-9
     *
     */
    private int getStringToInt(String string)
    {   int integer=0;
        try
        {   integer = Integer.parseInt(string);
        }
        catch(Exception ex)
        {   integer = -1;
            ex.printStackTrace();
        }
        return integer;
    }
    /**����ʴ� Dialog �ʴ���������ѹ��*/
    public void showDialogRelationBorn()
    {   theHosDialog.showDialogBornMch(theHosManage,12, pcuobject);
    }
    /**
     *
     * ��㹡���ʴ��Ţ������� tooltiptext �ͧ textfield
     *
     */
    public void setToolTipText(javax.swing.JButton button)
    {   button.setToolTipText("<html><font color=BLACK>" +GutilPCU.getTextBundle("ShowEnableServiceBornMch")+"</font></html>");
    }
    /**
     *
     *  ��㹡�� �觢�������ѧ��ʹ��Ѻ��ѧ�����š�ä�ʹ
     *
     */
    public void callBornMchMother()
    {   theHosManage.theHosSubject.theAfterMchSubject.notifyCallAfterBornMchMother();
    }
    /**
     *
     * ��㹡���ʴ��������͡�� ��ѧ�ҡ������͡������㹵��ҧ
     *
     * ����ʴ��ѹ�������Ѻ��ԡ���ҧἹ��ͺ����
     *
     * @param row �Ƿ�������͡
     *
     * @param inFirst �� boolean ����� true �繤����á ����� false ���������á
     *
     * @author ��ا�Ѱ
     *
     */
    public void selectTableAfterMchMother(int row,boolean inFirst)
    {   
        if(row == -1)
            return;
        setAfterMchMother((AfterMchMother)vMchMother.get(row));
    }
    /**
     *
     *  ��Ǩ�ͺ �����ŷ������� List �ͧ ���ҧ ���������Ѻ��ԡ��������� ����� �� return �� true
     *
     *  �������� �� false
     *
     *  @return �� boolean true ���բ����š������Ѻ��ԡ������  false ����բ����ū��
     *
     */
    private boolean checkServiceForVisitIDInVector()
    {   // ������� false
        String visit_id = "";
        if(pcuobject.getVisit()!=null)
            visit_id = pcuobject.getVisit().getObjectId();
        boolean result = false;
        if(vMchMother != null)
        {   int size = vMchMother.size();
            for(int i =0 ; i < size ; i++)
            {   theAfterMchMotherTemp = (AfterMchMother)vMchMother.get(i);
                if(theAfterMchMotherTemp.visit_id.equalsIgnoreCase(visit_id))
                {   result = true;
                    break;
                }
                theAfterMchMotherTemp = null;
            }
            //��Ǩ�ͺ�ҡ�ҹ��������� ����¡������Ѻ��ԡ�����������ѧ ������������ return �� true
            if(!result)
            {   result = checkDataAfterMchMotherByVisitID(visit_id);
            }
            theAfterMchMotherTemp = null;
        }
        return result;
    }
    /**
     *
     *  ��㹡�õ�Ǩ�ͺ������ �ͧ visit_id(����Ѻ��ԡ��) �ͧ���駹�� ������Ѻ��ԡ����ѧ��ʹ
     *
     *  ���������ѧ �������� return �� true �������� return �� false
     *
     *  @param visit_id �� key ��ѧ�ͧ���ҧ visit
     *
     *  @return �� boolean ����� true �ա������Ѻ��ԡ�� �ͧ visit_id ����, false �ѧ����ա������Ѻ��ԡ��
     *
     *  �ͧ visit_id
     *
     */
    private boolean checkDataAfterMchMotherByVisitID(String visit_id)
    {
        return theHC.theAfterMCHMotherControl.checkDataAfterMchMotherByVisitID(visit_id);
    }
    /**
     *
     * ��㹡�ùӢ����ŷ����ҡ Object �ͧ FamilyPlaing ��ʴ��� GIU
     *
     *@return boolean ����� true �ա�á�˹���������ѹ������ false ����ա�á�˹���������ѹ������
     *
     * @author Tong
     *
     */
    public boolean setAfterMchMother(AfterMchMother amm)
    {
        theAfterMchMother = amm;
        jTextFieldPregnantNumber.setText("");
        integerTextFieldVisitNumber.setText("");
        //add code check null value by noom 23/10/2548
        jTextAreaNote.setText(theAfterMchMother.symptom_notice);
        jTextFieldRemarkNote.setText(theAfterMchMother.note);
        jTextFieldPregnantNumber.setText(theAfterMchMother.pregnantnumber);
        integerTextFieldVisitNumber.setText(theAfterMchMother.pcare);
        setInitComboBox(jComboBoxCheckPlace,theAfterMchMother.place);
        setInitComboBox(jComboBoxSew,theAfterMchMother.sew);
        panelYesNoRadioButtonUterus_level.setSelected(theAfterMchMother.uterus_level);
        panelYesNoRadioButtonCream.setSelected(theAfterMchMother.cream);
        panelYesNoRadioButtonMenses.setSelected(theAfterMchMother.menses);
        panelYesNoRadioButtonResultVerify.setSelected(theAfterMchMother.result_verify);
        panelYesNoRadioButtonUterus_Lochia.setSelected(theAfterMchMother.lochia);
        panelYesNoRadioButtonUterus_Milk_Seep.setSelected(theAfterMchMother.milk_seep);
        dateComboBoxSurvey.setText(Gutil.convertFieldDate(theAfterMchMother.survey_date));
        jLabelSurveyDate.setSelected(!dateComboBoxSurvey.getText().equals(""));
        dateComboBoxSurvey.setEnabled(!dateComboBoxSurvey.getText().equals(""));
        this.jLabelVN.setText("");
        if(!theAfterMchMother.visit_id.equals("")){
            String vn_id = this.theAllComboBoxControl.readVNbyVid(theAfterMchMother.visit_id);
            this.jLabelVN.setText("VN:"+vn_id);
        }
        setInitComboBox(jComboBoxSugarLevel,theAfterMchMother.sugar_level);
        setInitComboBox(jComboBoxAlblumin,theAfterMchMother.albumin);
        jLabelAppointment.setText(GutilPCU.changDateToString((theAfterMchMother.appointment),true));
        this.dateComboBoxCheck.setText(DateUtil.convertFieldDate(theAfterMchMother.updatetime));
        String timeCheck = "";
        if(!theAfterMchMother.updatetime.equals(""))
            timeCheck = theAfterMchMother.updatetime.substring(11);
        else
            timeCheck = theHC.theLookupControl.getTextCurrentTime();
        this.timeTextFieldCheck.setText(timeCheck);
        return true;
    }
    /**
     *
     * ��㹡��ź������ ����ҧἹ��ͺ�����͡�ҡ���ҧ
     *
     * �� �����¡�ù�����ѹ���ǡѺ���ѹ�֡ ��ź�͡�ҡ���ҧ
     *
     *    �����¡�ù���繤����ѹ�Ѻ���ѹ�֡ ������¹ʶҹ� active �� 0
     *
     * @author Tong
     *
     */
    public void deleteAfterMchMother()
    {
        if(this.jTableAfterMchMother.getRowCount()==-1){
            theUS.setStatus("��س����͡��¡�÷���ͧ���ź",UpdateStatus.WARNING);
            return;
        }
        int result = theHC.theAfterMCHMotherControl.deleteAfterMchMotherByKeyID(theAfterMchMother);
        if(result == 0)
            return;
        refreshTableMCHMother();
        setInitDefaultDataGUI(true);
        theAfterMchMother = null;
    }
    public void addAfterMchMother(boolean first)
    {
        if(isVisitSame())
            return;
        
        theAfterMchMother = null;
        this.jLabelVN.setText("");
        dateComboBoxSurvey.setText(Gutil.convertFieldDate(""));
        jLabelSurveyDate.setSelected(!dateComboBoxSurvey.getText().equals(""));
        dateComboBoxSurvey.setEnabled(!dateComboBoxSurvey.getText().equals(""));
        setInitDefaultDataGUI(true);
        jTableAfterMchMother.clearSelection();
        if(pcuobject.getVisit()!=null)
        {   
            jButtonSave.setEnabled(true);
            if(checkPatientService(pcuobject.getPatient(),first))
            {
                if(!selectData)
                {   String str = "<html>"+GutilPCU.getTextBundle("ChangePreg")
                    +"<br><b>"+GutilPCU.getTextBundle("ChangePreg1")+"</b>"+GutilPCU.getTextBundle("ChangePreg2")
                    +"</br><br>"+GutilPCU.getTextBundle("ChangePreg3")+"</br></html>";
                    jTextFieldPregnantNumber.setEditable(false);
                    if(theUS.confirmBox(str,UpdateStatus.WARNING))
                    {
                        selectData = true;
                        theBornMch = null;
                        jTextFieldPregnantNumber.setEditable(true);
                        jTextFieldPregnantNumber.requestFocus();

                    }
                }
            }
        }
         
    }
    /**
     *��Ǩ�ͺ����� Visit ����ӡѹ�������
     *@param -
     *@return boolean false=�����   true=���
     *@author Tong
     *@date 01/09/2549
     */
    private boolean isVisitSame() 
    {
//        if(pcuobject.getVisit()==null)
//            return false;
//        
//        if(this.vMchMother == null)
//            return false;
//
//        for(int i=0;i<this.vMchMother.size();i++)
//        {
//            AfterMchMother obj = (AfterMchMother)vMchMother.get(i);
//            if(pcuobject.getVisit().getObjectId().equals(obj.visit_id)) {
//                theUS.setStatus(GutilPCU.getTextBundle("VisitAlready"),UpdateStatus.WARNING);
//                return true;
//            }
//        }
        return false;
    }
    /**
     *
     *��Ǩ�ͺ��Ҽ��������ͻ�Ъҡ����ª��Ե�����������
     *
     *@param -
     *
     *@return boolean true=���ª��Ե false=������ª��Ե
     *
     *@author kingland
     *
     *@date 04/09/2549
     *
     */
    private boolean checkDead()
    {   boolean result = false;
        if(theFamily != null && Dischar.DEATH.equals(theFamily.discharge_status_id))
        {   result = true;
        }
        else if(thePatient != null && Dischar.DEATH.equals(thePatient.discharge_status_id))
        {   result = true;
        }
        return false;//result;
    }
    /**
     *
     *��Ǩ�ͺ�Ȣͧ����Ѻ��ԡ��
     *
     *@param -
     *
     *@return boolean true=��ҹ false=����ҹ
     *
     *@author kingland
     *
     *@date 04/09/2549
     *
     */
    private boolean checkSex(boolean showWarningMessage)
    {   boolean result = true;
        //add code by noom ����Ѻ check �� ˭ԧ��ҹ��
        if(thePatient != null && !("2").equals(thePatient.f_sex_id))
        {   if(showWarningMessage)
            {
                theUS.setStatus(GutilPCU.getTextBundle("ISSEXWOMAN"),UpdateStatus.WARNING);
                //�ѧ����к��� �������Ȫ�� �������ö����Ѻ��ԡ����ǹ�����
            }
            result = false;
        }
        //����Ѻ check �� ˭ԧ��ҹ��
        else if(theFamily!=null && !("2").equals(theFamily.f_sex_id))
        {   if(showWarningMessage)
            {
                theUS.setStatus(GutilPCU.getTextBundle("ISSEXWOMAN"),UpdateStatus.WARNING);
                //�ѧ����к��� �������Ȫ�� �������ö����Ѻ��ԡ����ǹ�����
            }
            result = false;
        }
        return result;
    }
    /**
     *
     *��㹡�úѹ�֡�����Ţͧ ��ô��������ѧ��ʹ
     *
     *@param -
     *
     *@return boolean true=�ѹ�֡��������  false=�ѹ�֡�����������
     *
     *@author -
     *
     *@modify kingland
     *
     *@date 04/09/2549
     *
     */
    public boolean saveAfterMchMother()
    {
        boolean result = false;
        if(theFamily == null )
        {   theUS.setStatus("��س����͡��Ъҡ����ͺѹ�֡�����Ż�Ъҡá�͹",2);
            return result;
        }
        else if(!checkSex(true))
        {   return result;
        }
        else if(checkSameNumCare())
        {   return result;
        }
        updateGOAfterMchMother();
        if(!checkCarePregnantNumber())
            return result;
        if(checkSameCareNumber(theAfterMchMother))
            return result;
       //1.1.1 ����ӡѹ
        //1.1.1.1 �ӡ�úѹ�֡������ �������Ѻ��ԡ����ѧ��ʹŧ�ҹ������
        int rs = theHC.theAfterMCHMotherControl.saveAfterMCHMother(theAfterMchMother);
        if(rs==0)
            return result;
        setAfterMchMother(theAfterMchMother);
       theAppointment = null;
        //1.1.1.2 �ӡ�ä��� ��� �ʴ�������ŧ�����ҧ
        refreshTableMCHMother();
        for(int i=0;i<this.vMchMother.size();i++){
            AfterMchMother pp = (AfterMchMother)vMchMother.get(i);
            if(pp.getObjectId().equals(theAfterMchMother.getObjectId())){
                this.jTableAfterMchMother.setRowSelectionInterval(i,i);
                return true;
            }
        }
        return result;
    }
    public boolean saveAfterMchMotherN()
    {
        boolean result = false;
        if(theFamily == null )
        {   theUS.setStatus("��س����͡��Ъҡ����ͺѹ�֡�����Ż�Ъҡá�͹",2);
            return result;
        }
        else if(!checkSex(true))
        {   return result;
        }
        else if(checkSameNumCare())
        {   return result;
        }
        updateGOAfterMchMother();
        if(!checkCarePregnantNumber())
            return result;
        if(checkSameCareNumber(theAfterMchMother))
            return result;
       //1.1.1 ����ӡѹ
        //1.1.1.1 �ӡ�úѹ�֡������ �������Ѻ��ԡ����ѧ��ʹŧ�ҹ������
        int rs = theHC.theAfterMCHMotherControl.saveAfterMCHMotherN(theAfterMchMother);
        if(rs==0)
            return result;
        setAfterMchMother(theAfterMchMother);
       theAppointment = null;
        //1.1.1.2 �ӡ�ä��� ��� �ʴ�������ŧ�����ҧ
        refreshTableMCHMother();
        for(int i=0;i<this.vMchMother.size();i++){
            AfterMchMother pp = (AfterMchMother)vMchMother.get(i);
            if(pp.getObjectId().equals(theAfterMchMother.getObjectId())){
                this.jTableAfterMchMother.setRowSelectionInterval(i,i);
                return true;
            }
        }
        return result;
    }
    /**
     *
     *��㹡�� ��˹�������͡ ������㹵��ҧ���������ա�á��ѹ�֡
     *
     *�¨�仵�Ǩ�ͺ�Ѻ vector
     *
     *@param -
     *
     *@return void
     *
     *@author kingland
     *
     *@date 04/09/2549
     *
     */
    private void setSelectRowInTable()
    {   if(vMchMother!=null)
        {   if(theAfterMchMother != null)
            {   int size = vMchMother.size();
                int selectRow = -1;
                for(int i = 0 ; i <size ; i++)
                {   theAfterMchMotherTemp = (AfterMchMother)vMchMother.get(i);
                    if(theAfterMchMotherTemp.getObjectId().equalsIgnoreCase(theAfterMchMother.getObjectId()))
                    {   selectRow = i;
                        break;
                    }
                    theAfterMchMotherTemp = null;
                }
                theAfterMchMotherTemp = null;
                if(selectRow != -1)
                {   jTableAfterMchMother.setRowSelectionInterval(selectRow,selectRow);
                    selectTableAfterMchMother(selectRow,false);
                }
            }
        }
    }
    /**
     *
     *  ��㹡�õ�Ǩ�ͺ��ë�ӡѹ�ͧ�ӴѺ�������Ѻ��ԡ��
     *
     *  ��Ǩ�ͺ�����Ŵѧ���
     *
     *  1. ��Ǩ�ͺ��ҷ������ � Vector �ͧ�������Ѻ��ԡ��
     *
     *  1.1 ��Ǩ�ͺ�ӴѺ�������Ѻ��ԡ�áѺ�����ŷ��кѹ�֡(�ҡ textfield�ͧ�ӴѺ��ô���)
     *
     *  1.1.1 ��Ң����ŷ������������ѧ����� objectID(�����������)
     *
     *  1.1.1.1 ��Ǩ�ͺ�ӴѺ��ô����ա�ë�ӡѹ������� ��ҫ�Ө����ѹ�֡
     *
     *  1.1.2 ��Ң����ŷ��������繢�������� (������)
     *
     *  1.1.2.1 ��Ǩ�ͺ�ӴѺ��ô����ա�ë�ӡѹ�������
     *
     *  1.1.2.2 ��ҫ�ӡѹ ��Ǩ�ͺ objectid ��ӡѹ������� ��ҫ�ӡѹ ���ӧҹ��Ǩ�ͺ����
     *
     *  1.1.2.3 �������ӡѹ �����ѹ�֡
     *
     *@return boolean �� true ��� �Դ��ë�ӡѹ, false ��� ����Դ��ë�ӡѹ
     *
     */
    private boolean checkSameCareNumber(AfterMchMother amm)
    {   boolean result = false;
        /**��Ǩ�ͺ��Ңͧ������� ����դ��������� ��Ф�ҷ���� �е�ͧ�����ҡѺ 0*/
        String num = integerTextFieldVisitNumber.getText().trim();
        if(num.length() >0)
        {   int inum = convertStringToInt(num);
            if(inum <=0 )
            {   //������ 0
                theUS.setStatus(GutilPCU.getTextBundle("ValueNumberLess"),UpdateStatus.WARNING);
                integerTextFieldVisitNumber.requestFocus();
                integerTextFieldVisitNumber.selectAll();
                result = true;
            }
            if(inum >= 20)
            {   theUS.setStatus(GutilPCU.getTextBundle("ValueNumberMore"),UpdateStatus.WARNING);
                integerTextFieldVisitNumber.requestFocus();
                integerTextFieldVisitNumber.selectAll();
                result = true;
            }
        }
        else
        {   //������� ��ͧ�բ�����
            theUS.setStatus(GutilPCU.getTextBundle("ValueNumberNULL"),UpdateStatus.WARNING);
            integerTextFieldVisitNumber.requestFocus();
            result = true;
        }
        num = null;
        boolean resultsame = false;
        /**��� result �� true �ʴ�����Դ��ë�ӡѹ�ͧ����Ţ���Ť��駷��
         *
         *��� result �� false �ʴ��������Դ��ë�ӡѹ���ӡ���Ң����ŷ���Ҩз�����Դ��ë�ӡѹ
         *
         */
        if(!result)
        {   String carenumber = integerTextFieldVisitNumber.getText();
            if(vMchMother != null)
            {   //��Ǩ�ͺ ����� �ͧ��������ѹ���������
                if(!selectData)
                {   int rows = vMchMother.size();
                    System.out.println("Rows : " + rows);
                    theAfterMchMotherTemp = null;
                    /**��Ǩ�ͺ Vector ����ʴ������ list*/
                    for(int i =0; i < rows;i++)
                    {   theAfterMchMotherTemp = (AfterMchMother)vMchMother.get(i);
                        /**��Ǩ�ͺ��� �������� null*/
                        if(theAfterMchMotherTemp != null && theAfterMchMotherTemp.getObjectId() !=null)
                            
                        {   /**��Ǩ�ͺ��ҷ��кѹ�֡����繡�úѹ�֡�������͡�����*/
                            
                            if(amm.getObjectId() == null)
                                
                            {   /**��úѹ�֡���� ����Ǩ�ͺ੾���ӴѺ��ô���*/
                                
                                if(carenumber.equalsIgnoreCase(theAfterMchMotherTemp.pcare))
                                    
                                {   resultsame = true;
                                    
                                    break;
                                    
                                }
                                
                            }
                            
                            /**������ ��Ǩ�ͺ੾���ӴѺ��ô�������繢��������ǡѹ������� �������繢��������ǡѹ�����ش*/
                            
                            else
                                
                            {
                                
                                if(carenumber.equalsIgnoreCase(theAfterMchMotherTemp.pcare) && !theAfterMchMotherTemp.getObjectId().equalsIgnoreCase(amm.getObjectId()) )
                                    
                                {   resultsame = true;
                                    
                                    break;
                                    
                                }
                                
                            }
                            
                        }
                        theAfterMchMotherTemp = null;
                    }
                    theAfterMchMotherTemp = null;
                }
            }
            if(resultsame)
            {   /**��Ǩ�ͺ�Ѻ�ҹ������ ��� �����š�ä�ʹ*/
                if(theBornMch!=null && theBornMch.getObjectId() != null)
                {   resultsame = theHC.theAfterMCHMotherControl.checkDataAfterMchMotherByMCHID(theBornMch.getObjectId(), carenumber);
                    System.out.println("-- resultsame = "+resultsame);
                }
            }
            if(resultsame)
            {   //�ӴѺ����Ѻ��ԡ�ë�ӡѺ���������
                theUS.setStatus(GutilPCU.getTextBundle("SAMECARENUMBER"),UpdateStatus.WARNING);
            }
        }
        else
        {   resultsame = true;
        }
        return resultsame;
    }
    /**
     *
     * ��Ǩ�ͺ�����Ţ��駤������
     *
     * @param -
     *
     * @return boolean
     *
     *  true = �������Ţ���
     *
     *  false = ����������Ţ���
     *
     * @author noom
     *
     * @modify kingland
     *
     * @date 28/08/2549
     *
     */
    public boolean checkSameNumCare()
    {   boolean isSame = false;
        if(theAfterMchMother == null)theAfterMchMother = new AfterMchMother();
        String careNo = jTextFieldPregnantNumber.getText().trim();
        String careNumber = integerTextFieldVisitNumber.getText().trim();
        if(vMchMother != null && theAfterMchMother != null &&
                ("".equals(theAfterMchMother.getObjectId()) || theAfterMchMother.getObjectId() == null ))
        {   if(!careNumber.equals(""))
            {   AfterMchMother Amm=new AfterMchMother();
                for(int i=0, size=vMchMother.size(); i<size; i++)
                {   Amm = (AfterMchMother)vMchMother.get(i);
                    if(Amm.pregnantnumber.equals(careNo) && Amm.pcare.equals(careNumber))
                    {   isSame = true;
                        break ;
                    }
                }
            }
            else
            {   isSame = false;
            }
        }
        if(isSame)
        {   theUS.setStatus(GutilPCU.getTextBundle("NumberSame"),UpdateStatus.WARNING);
        }
        return isSame;
    }
    /**
     *
     *��㹡���ŧ����ѡ���繵���Ţ
     *
     *@param value �� String ��ͧ�繵���Ţ
     *
     *@return int �繵���Ţ���١�ŧ�͡������ ����Դ error �С�˹���� �� 0
     *
     *@author padungrat(tong)
     *
     *@modify kingland
     *
     *@date 04/09/2549
     *
     */
    private  int convertStringToInt(String value)
    {   int ivalue =0;
        try
        {   ivalue = Integer.parseInt(value);
        }
        catch(Exception ex)
        {   ex.printStackTrace();
        }
        return ivalue;
    }
    public void refresh(String family_id)
    {
        theHC.theAfterMCHMotherControl.selectAfterMchMotherShowGUITableByFamilyID(family_id);
    }
    /**
     *
     * ��㹡�� Refresh ������㹵��ҧ�������Ѻ��ԡ�� �ҧἹ��ͺ����
     *
     * ���������
     *
     * @author ��ا�Ѱ
     *
     */
    private void refreshTableMCHMother()
    {
        Patient thePatient = pcuobject.getPatient();
        Family theFamily = pcuobject.getFamily();
        vMchMother= null;
        if(selectData&&thePatient!=null&&!thePatient.family_id.equals(""))
        {   /**!thePatient.family_id.equals("") ���Ҩҡ family_id by noom*/
            vMchMother = theHC.theAfterMCHMotherControl.selectAfterMchMotherShowGUITableByFamilyID();
            if(vMchMother==null)
                vMchMother = theHC.theAfterMCHMotherControl.selectAfterMchMotherShowGUITableByPatientID(thePatient.getObjectId());
        }
        else
        {   if(theBornMch != null&&thePatient!=null&&!thePatient.family_id.equals(""))
            {   /**���Ҩҡ�����š�ä�ʹ ���駷��*/
                vMchMother = theHC.theAfterMCHMotherControl.selectAfterMchMotherShowGUITableByFamilyID();
                if(vMchMother==null)
                    vMchMother = theHC.theAfterMCHMotherControl.selectAfterMchMotherShowGUITableByPatientID(thePatient.getObjectId());
            }
        }
        if(vMchMother == null && theFamily!=null)
            vMchMother = theHC.theAfterMCHMotherControl.selectAfterMchMotherShowGUITableByFamilyID();
        setAfterMchMotherV(vMchMother);
    }
    /**
     *
     * ��㹡���ʴ�������ŧ���ҧ �ͧ GUI
     *
     * @author ��ا�Ѱ
     *
     * @param vc �� Vector �ͧ Object AfterMchMother �����㹡���ʴ��ź����ҧ
     *
     * GUI
     *
     */
    private void setAfterMchMotherV(Vector vc)
    {
        tablemodel = new TableModel(headTableAfterMCHMother,0);
        int servicenumber = 0;
        int tempservicenumber =0;
        if(vc!=null)
        {
            System.out.println("Size : " + vc.size());
            tablemodel = new TableModel(headTableAfterMCHMother,vc.size());
            final int size = vc.size();
            for(int i = 0 ; i < size ; i++)
            {
                
                theAfterMchMotherTemp = (AfterMchMother)vc.get(i);
                tablemodel.setValueAt(theAfterMchMotherTemp.pregnantnumber, i, 0);
                tablemodel.setValueAt(theAfterMchMotherTemp.pcare, i, 1);
                System.out.println("------------------"+ theAfterMchMotherTemp.updatetime);
                tablemodel.setValueAt(GutilPCU.changDateToString(theAfterMchMotherTemp.updatetime,false), i, 2);
                //         tablemodel.setValueAt(theAfterMchMotherTemp.getVN(), i, 3);
                tempservicenumber = getStringToInt(theAfterMchMotherTemp.pcare);
                if(tempservicenumber > servicenumber)
                {
                    servicenumber = tempservicenumber;
                }
                
                
                theAfterMchMotherTemp = null;
                
            }
        }
        /**��˹���� ���text �ͧ ��ԡ�ä��駷�� �դ�� ����� ��Ң������Ҩҡ ��ä�ʹ*/
        if(!selectData)
        {  // if(size != 0)
            //  {
            //    theAfterMchMotherTemp = (AfterMchMother)vc.get(size-1);
            //     servicenumber = getStringToInt(theAfterMchMotherTemp.pcare);
            //     theAfterMchMotherTemp = null;
            // }
            //�ӡ������ ��ô�����ѧ��ʹ���
            ++servicenumber;
            integerTextFieldVisitNumber.setText(String.valueOf(servicenumber));
        }
        jTableAfterMchMother.setModel(tablemodel);
        jTableAfterMchMother.getColumnModel().getColumn(0).setPreferredWidth(90);
        jTableAfterMchMother.getColumnModel().getColumn(0).setCellRenderer(ColumnTableRenderer.getRendererCenter());
        jTableAfterMchMother.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableAfterMchMother.getColumnModel().getColumn(1).setCellRenderer(ColumnTableRenderer.getRendererCenter());
        jTableAfterMchMother.getColumnModel().getColumn(2).setPreferredWidth(150);
        //  jTableAfterMchMother.getColumnModel().getColumn(3).setPreferredWidth(150);
        //    jTableAfterMchMother.getColumnModel().getColumn(3).setCellRenderer(ColumnTableRenderer.getRendererCenter());
    }
    /**
     *
     * ��㹡�á�˹���Ҩҡ GUI ���Ѻ Object
     *
     *  @param -
     *
     *  @return boolean ����� true �繼��������� false ���������
     *
     *  @author ��ا�Ѱ
     *
     *  @modify kingland
     *
     *  @date 28/2549
     *
     */
    private boolean updateGOAfterMchMother()
    {
        Patient thePatient = pcuobject.getPatient();
        Family theFamily = pcuobject.getFamily();
        boolean result = true;
        if(theAfterMchMother == null)
           theAfterMchMother = new AfterMchMother();

        theAfterMchMother.pcare = integerTextFieldVisitNumber.getText();
        theAfterMchMother.pregnantnumber = jTextFieldPregnantNumber.getText();
        theAfterMchMother.active = Active.isEnable();
        theAfterMchMother.note = Gutil.CheckReservedWords(jTextFieldRemarkNote.getText());
        theAfterMchMother.symptom_notice= Gutil.CheckReservedWords(jTextAreaNote.getText());
        theAfterMchMother.place = getDataCodeComboBox(jComboBoxCheckPlace,true);
        theAfterMchMother.uterus_level = panelYesNoRadioButtonUterus_level.getStringSelect();
        theAfterMchMother.cream = panelYesNoRadioButtonCream.getStringSelect();
        theAfterMchMother.menses = panelYesNoRadioButtonMenses.getStringSelect();
        theAfterMchMother.result_verify = panelYesNoRadioButtonResultVerify.getStringSelect();
        theAfterMchMother.lochia = panelYesNoRadioButtonUterus_Lochia.getStringSelect();
        theAfterMchMother.sew = getDataCodeComboBox(jComboBoxSew,true);
        theAfterMchMother.albumin  = getDataCodeComboBox(jComboBoxAlblumin,true);
        if(theAppointment!=null)
            theAfterMchMother.appointment = theAppointment.appoint_date;
        theAfterMchMother.sugar_level = getDataCodeComboBox(jComboBoxSugarLevel,true);
        theAfterMchMother.milk_seep = panelYesNoRadioButtonUterus_Milk_Seep.getStringSelect();
        theAfterMchMother.user_cancel="";
        theAfterMchMother.mch_id ="";
        
        if(theAfterMchMother.getObjectId() == null)
        {
            if(theBornMch!=null && theBornMch.getObjectId() != null)
            {   
                theAfterMchMother.mch_id = theBornMch.getObjectId();
                theAfterMchMother.pregnantnumber = theBornMch.gravida;
            }
            theAfterMchMother.user_record = staff_id;
            theAfterMchMother.recordtime = Gutil.getTextCurrentDateTime(theHC.theAllComboBoxControl.theConnectionInf);
            if(pcuobject.getVisit()!=null)
                theAfterMchMother.visit_id= pcuobject.getVisit().getObjectId();
            if(theFamily!=null)
                theAfterMchMother.family_id = theFamily.getObjectId();
            if(pcuobject.getPatient()!=null)
                theAfterMchMother.patient_id= pcuobject.getPatient().getObjectId();
            if(theAfterMchMother.patient_id == null)
                theAfterMchMother.patient_id= "";
            result =true;
        }
        theAfterMchMother.user_modify = staff_id;
        theAfterMchMother.survey_date = dateComboBoxSurvey.getText();
        theAfterMchMother.updatetime = dateComboBoxCheck.getText()+","+timeTextFieldCheck.getText();

        return result;
    }
    /**
     *
     * ��㹡���ʴ��������͡�Ҩҡ ComboBox ��� param �ա�˹�
     *
     * @author ��ا�Ѱ
     *
     * @param combobox ComboBox ����ͧ��èйӤ���͡����ҹ
     *
     * @param type �繵�ǡ�˹���ҷ��� return �͡
     *
     * true ��� �ʴ��͡���� Code
     *
     * false ��� �ʴ��͡���� ��ͤ���
     *
     * @return �ʴ���ҷ���ͧ����͡��
     *
     */
    private String getDataCodeComboBox(javax.swing.JComboBox combobox ,boolean type)
    {
        if(type)
        {
            return ComboboxModel.getCodeComboBox(combobox);
        }
        else
        {
            return ComboboxModel.getStringConboBox(combobox);
        }
    }
    private void setInitComboBox()
    {
        setInitComboBox(jComboBoxCheckPlace,Active.isEnable());
        setInitComboBox(jComboBoxSew,Active.isEnable());
///        setInitComboBox(jComboBoxCream,Active.isEnable());
///        setInitComboBox(jComboResult,Active.isEnable());
///        setInitComboBox(jComboBoxLochia,Active.isEnable());
        ///       setInitComboBox(jComboBoxUterus,Active.isDisable());
///        setInitComboBox(jComboBoxMilk,Active.isEnable());
///       setInitComboBox(jComboBoxMenses,Active.isEnable());
        setInitComboBox(jComboBoxSugarLevel,Active.isDisable());
        setInitComboBox(jComboBoxAlblumin,Active.isEnable());
        panelYesNoRadioButtonUterus_level.setSelected(true);
        panelYesNoRadioButtonCream.setSelected(true);
        panelYesNoRadioButtonMenses.setSelected(true);
        panelYesNoRadioButtonResultVerify.setSelected(true);
        panelYesNoRadioButtonUterus_Lochia.setSelected(true);
        panelYesNoRadioButtonUterus_Milk_Seep.setSelected(true);
    }
    /**
     *
     * ��㹡�õ�駤��������鹢ͧ������
     *
     * @author ��ا�Ѱ
     *
     */
    private void setInitDefaultDataGUI(boolean showadd)
    {
        setInitComboBox();
        jTextFieldPregnantNumber.setText("1");
        jTextFieldPregnantNumber.setEditable(true);
        dateComboBoxCheck.setText(Gutil.convertFieldDate(theHC.theLookupControl.getTextCurrentDate()));
        if(!selectData)
        {
            if(theBornMch!=null)
                jTextFieldPregnantNumber.setText(theBornMch.gravida);
            jTextFieldPregnantNumber.setEditable(false);
        }
        else
        {
            integerTextFieldVisitNumber.setText("");
            theBornMch = null;
        }
        jTextAreaNote.setText("");
        jTextFieldRemarkNote.setText("");
        jLabelAppointment.setText("");
        jPanelRelationBornMch.setVisible(false);
    }
    /**
     *
     * ��㹡�� ��˹��������������Ѻ Combobox
     *
     * @param combobox ComboBox ����ͧ��è�����ʴ����������á
     *
     * @param code ���ʷ���ͧ��è�����ʴ� �繢�ͤ���� combobox
     *
     * @author ��ا�Ѱ
     *
     */
    private void setInitComboBox(javax.swing.JComboBox combobox,String code)
    {
        ComboboxModel.setCodeComboBox(combobox,code);
    }
    /**
     *
     * �ʴ���ͤ�����͹
     *
     * @param message = ��ͤ�������ͧ�������ʴ�
     *
     *        status = ʶҹз���ʴ�
     *
     * @return void
     *
     * @author kingland
     *
     * @date 28/08/2549
     *
     */
    public void setLanguage()
    {   /*jLabel*/
        jLabelAlblumin.setText(GutilPCU.getTextBundle(jLabelAlblumin.getText()));
        jLabelCheckPlace.setText(GutilPCU.getTextBundle(jLabelCheckPlace.getText()));
        jLabelCream.setText(GutilPCU.getTextBundle(jLabelCream.getText()));
        jLabelLochia.setText(GutilPCU.getTextBundle(jLabelLochia.getText()));
        jLabelMenses.setText(GutilPCU.getTextBundle(jLabelMenses.getText()));
        jLabelMilk.setText(GutilPCU.getTextBundle(jLabelMilk.getText()));
        jLabelNextAppDate.setText(GutilPCU.getTextBundle(jLabelNextAppDate.getText()));
        jLabelPregnantNumber.setText(GutilPCU.getTextBundle(jLabelPregnantNumber.getText()));
        jLabelResult.setText(GutilPCU.getTextBundle(jLabelResult.getText()));
        jLabelSew.setText(GutilPCU.getTextBundle(jLabelSew.getText()));
        jLabelSugarLevel.setText(GutilPCU.getTextBundle(jLabelSugarLevel.getText()));
        jLabelUterus.setText(GutilPCU.getTextBundle(jLabelUterus.getText()));
        jLabelSurveyDate.setText(GutilPCU.getTextBundle(jLabelSurveyDate.getText()));
        jLabelVisitNumber.setText(GutilPCU.getTextBundle(jLabelVisitNumber.getText()));
        jLabelRemark.setText(GutilPCU.getTextBundle(jLabelRemark.getText()));
        GutilPCU.setLanguage(jLabel5);
        GutilPCU.setLanguage(jLabel6);
        /*jButton*/
        jButtonAdd.setText(GutilPCU.getTextBundle(jButtonAdd.getText()));
        jButtonDel.setText(GutilPCU.getTextBundle(jButtonDel.getText()));
        jButtonSave.setText(GutilPCU.getTextBundle(jButtonSave.getText()));
        jButtonAppointment.setText(GutilPCU.getTextBundle(jButtonAppointment.getText()));
        jButtonPrint.setText(GutilPCU.getTextBundle(jButtonPrint.getText()));
        /*TitledBorder*/
        GutilPCU.JPanelLabler(jPanelAfterMchMomList);
        GutilPCU.JPanelLabler(jPanelAfterMchMomDetail);
        GutilPCU.JPanelLabler(jPanelResultAfterMchMom);
        headTableAfterMCHMother[0] = GutilPCU.getTextBundle(headTableAfterMCHMother[0]);
        headTableAfterMCHMother[1] =GutilPCU.getTextBundle(headTableAfterMCHMother[1]);
        headTableAfterMCHMother[2] =GutilPCU.getTextBundle(headTableAfterMCHMother[2]);
        //headTableAfterMCHMother[3] =GutilPCU.getTextBundle(headTableAfterMCHMother[3]);
    }
    /**��㹡�õ�Ǩ�ͺ�����Ţͧ�������Ѻ��ԡ�� ��ǹ��� ����� visit_id
     *
     * �ç�Ѻ���������������� ��ҵç�ѹ��� �ӡ�����͡�����Ź������
     *
     */
    private void checkDataForVisit(boolean inFirst)
    {
        String visit_id = "";
        if(pcuobject.getVisit()!=null)
            visit_id = pcuobject.getVisit().getObjectId();
        int selectRow = -1;
        if(vMchMother!=null)
        {
            int size = vMchMother.size();
            // �ӡ��ǹ�ٻ����������������բ����Ţͧ visit ���駹���������
            for(int i = 0 ;i< size ; i++)
            {   //����ҷ�� vector ����������˹� i
                theAfterMchMotherTemp  = (AfterMchMother)vMchMother.get(i);
                
                //��Ǩ�ͺ ��ҷ��ͧ object ��� ������� vector ����դ�Ңͧ visit_id ���ǡѹ��á������Ѻ��ԡ�ä��駹�� �������
                if(visit_id != null && theAfterMchMotherTemp.visit_id.equalsIgnoreCase(visit_id))
                {
                    
                    //  theAfterMchMother = theAfterMchMotherTemp;
                    selectRow = i;
                    break;
                }
                theAfterMchMotherTemp = null;
            }
        }
        if(selectRow != -1)
        {
            // ����� ���ӡ�� ���͡�����Ūش��� ����ʴ��� GUI ��� ��ش ��÷ӧҹ�ͧ loop
            jTableAfterMchMother.setRowSelectionInterval(selectRow,selectRow);
            selectTableAfterMchMother(selectRow,inFirst);
        }
    }
    /**
     *
     * ��㹡�õ�Ǩ�ͺ��� ����Ѻ��ԡ�ä��駹�����Ѻ��ԡ�ô�����ѧ��ʹ���������ѧ �������������
     *
     * ���ӡ�� ��˹������� �ǡ ź ��� �ѹ�֡ �������ö�ӧҹ�� 㹵͹�á�ͧ�����ҹ
     *
     *
     *
     */
    private void checkServicedForVisit()
    {   //��˹������ false
        String visit_id = "";
        if(pcuobject.getVisit()!=null)
            visit_id = pcuobject.getVisit().getObjectId();
        boolean result = false;
        try
        {   //��Ǩ�ͺ�ҡ�ҹ������
            result = checkDataAfterMchMotherByVisitID(visit_id);
        }
        catch(Exception ex)
        {   result = false;
        }
    }
    //amp
    private void setEnableGui(boolean flag)
    {   jTextFieldPregnantNumber.setEnabled(flag);
        integerTextFieldVisitNumber.setEnabled(flag);
        jComboBoxCheckPlace.setEnabled(flag);
        panelYesNoRadioButtonMenses.setEnabled(flag);
        panelYesNoRadioButtonCream.setEnabled(flag);
        panelYesNoRadioButtonUterus_Milk_Seep.setEnabled(flag);
        panelYesNoRadioButtonUterus_Lochia.setEnabled(flag);
        panelYesNoRadioButtonUterus_level.setEnabled(flag);
        jComboBoxSew.setEnabled(flag);
        jComboBoxSugarLevel.setEnabled(flag);
        jComboBoxAlblumin.setEnabled(flag);
        jButtonAppointment.setEnabled(flag);
        panelYesNoRadioButtonResultVerify.setEnabled(flag);
        jTextAreaNote.setEnabled(flag);
        jTextFieldRemarkNote.setEnabled(flag);
    }
    /**
     *
     *૵�����ҹ Gui
     *
     *@param boolean true=����ö��ҹ�� false=�������ö��ҹ��
     *
     *@return void
     *
     *@author kingland
     *
     *@date 04/09/2549
     *
     */
    public void setEnabled(boolean flag)
    {   setEnableGui(flag);
        jButtonAdd.setEnabled(flag);
        jButtonDel.setEnabled(flag);
        jButtonSave.setEnabled(flag);
        jButtonPrint.setEnabled(flag);
    }
    /**
     *
     *  �Ѻ�����š�ä�ʹ ����������Ѻ��ԡ����ѧ��ʹ
     *
     */
    public void notifyCallAfterMchMother(BornMch bornmch)
    {
        //1. ��˹���� �����š�ä�ʹ
        theBornMch = bornmch;
        //2. ��˹���� Object �ͧ ��ô�����ѧ��ʹ��
        theAfterMchMother = null;
        //3. ��˹��� ��� Check selectData �� false
        selectData = false;
        //4. ��˹� ����ա�� Clear �����ź� GUI ��С�˹� ��� �� true ���͡�˹� ������ �����ӧҹ
        setInitDefaultDataGUI(true);
        //5. ��˹� ����ա�� ���Ң����� ��� �����š�ä�ʹ ����ʴ������ҧ
        refreshTableMCHMother();
        checkServicedForVisit();
        checkDataForVisit(false);
        // selectDataWhenNotSelect();
    }
    /**��㹡�����͡ ������ ���á ��� ����ա�����͡��¡�����
     *
     */
    private void selectDataWhenNotSelect()
    {
        int row = jTableAfterMchMother.getSelectedRow();
        if(row == -1)
        {   // ��˹�������͡������ �Ƿ�� 0 ������͡�����ҧ����
            selectTableAfterMchMother(0,false);
        }
    }
    public void notifyCallAfterBornMchMother()
    {
    }
    /**
     *
     *  ��㹡���Ѻ notify �ҡ panel �ͧ �����š�ä�ʹ ������� �������� �ش���ǡѹ�Ѻ
     *
     *  ������ ��ä�ʹ �¨��� Object �ͧ BornMch ���ѧ panel ���
     *
     *  @param boornmch �� Object �ͧ BornMch
     *
     */
    public void notifyGetDataBornMchMotherToAfterMchMother(BornMch bornmch)
    {
        if(bornmch!= null)
        {
            theBornMch = bornmch;
            jTextFieldPregnantNumber.setText(theBornMch.gravida);
            //theUS.setStatus("�����������ҧ��������ѹ��������� ��سҡ��ѹ�֡","Warning",JOptionPane.OK_OPTION);
            //Comment code by noom 22/10/2548 ��ҡ����ʴ� Dialog �繡���ʴ�� status ᷹
            theUS.setStatus("�����������ҧ��������ѹ��������� ��سҡ��ѹ�֡",2);
        }
    }
    
    
    /**
     *
     * ���ѹ������Ǩ������ѹ�͹Ҥ��������
     *
     * @jao
     *
     */
    
    private void checkDateSurvey()
    
    {
        
        if(!dateComboBoxSurvey.getText().equals("")
        && dateComboBoxSurvey.getText().length()==10
                && com.pcu.utility.DateUtil.countDay(dateComboBoxSurvey.getText(),theHosManage.theHosControl.theConnectionInf) == -1
                && com.pcu.utility.DateUtil.isToday(com.pcu.utility.DateUtil.getDateFromText(dateComboBoxSurvey.getText()),theHosManage.theHosControl.theConnectionInf)==false)
        {
            // �������ö��͡�ѹ������ѹ�͹Ҥ���
            if(checksurvey == false)
            {
                theUS.setStatus(GutilPCU.getTextBundle("NoDateFuture"),UpdateStatus.WARNING);
                checksurvey = true;
            }
        }
    }
    
    /**
     *  ��㹡���Ѻ notify �Ҩҡ Panel �˭�ͧ pcu ������ա�ä�ԡ���͡ JTabbedPane �ͧ PanelPCU ���� Notify
     *  ��Ѻ����� panel ��� �ӧҹ
     */
    public void notifyCallAfterBornMchMotherService(boolean inFirst)
    {
        System.out.println("notifyCallAfterBornMchMotherService");
        //  getObject(pcuobject, false);
        selectTableAfterMchMother(jTableAfterMchMother.getSelectedRow(),inFirst);
    }
    
    public void notifyDeletePatient(String str, int param)
    {
    }
    
    public void notifyDeletePatientPayment(String str, int param)
    {
    }
    
    public void notifyManageDrugAllergy(String str, int param)
    {
    }
    
    public void notifyReadPatient(String str, int param)
    {
    }
    
    public void notifyResetPatient(String str, int param)
    {
    }
    
    public void notifySaveAppointment(String str, int param)
    {
        if(receiveNotify==3 && theHosDialog!=null)
        {
            theAppointment = new Appointment();
            theAppointment = theHosDialog.theDialogAppointment.getAppointment();
            jLabelAppointment.setText(GutilPCU.changDateToString((theAppointment.appoint_date),true));
        }
    }
    
    public void notifySavePatient(String str, int param)
    {
    }
    
    public void notifySavePatientPayment(String str, int param)
    {
    }
    
    public void notifyReadFamily(String str, int status)
    {
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.utility.DateComboBox dateComboBoxCheck;
    private com.pcu.utility.DateComboBox dateComboBoxSurvey;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.pcu.utility.IntegerTextField integerTextFieldVisitNumber;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAppointment;
    public javax.swing.JButton jButtonContinue;
    private javax.swing.JButton jButtonDel;
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBoxAlblumin;
    private javax.swing.JComboBox jComboBoxCheckPlace;
    private javax.swing.JComboBox jComboBoxSew;
    private javax.swing.JComboBox jComboBoxSugarLevel;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAlblumin;
    private javax.swing.JLabel jLabelAppointment;
    private javax.swing.JLabel jLabelCheckPlace;
    private javax.swing.JLabel jLabelCream;
    private javax.swing.JLabel jLabelLochia;
    private javax.swing.JLabel jLabelMenses;
    private javax.swing.JLabel jLabelMilk;
    private javax.swing.JLabel jLabelNextAppDate;
    private javax.swing.JLabel jLabelPregnantNumber;
    private javax.swing.JLabel jLabelRemark;
    private javax.swing.JLabel jLabelResult;
    private javax.swing.JLabel jLabelSew;
    private javax.swing.JLabel jLabelSugarLevel;
    private javax.swing.JCheckBox jLabelSurveyDate;
    private javax.swing.JLabel jLabelUterus;
    private javax.swing.JLabel jLabelVN;
    private javax.swing.JLabel jLabelVisitNumber;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelAfterMchMomControl;
    private javax.swing.JPanel jPanelAfterMchMomDetail;
    private javax.swing.JPanel jPanelAfterMchMomList;
    private javax.swing.JPanel jPanelAfterMomDetail;
    private javax.swing.JPanel jPanelRelationBornMch;
    private javax.swing.JPanel jPanelRemark;
    private javax.swing.JPanel jPanelResultAfterMchMom;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneAfterMchMomList;
    public javax.swing.JTable jTableAfterMchMother;
    private javax.swing.JTextArea jTextAreaNote;
    private com.pcu.utility.IntegerTextField jTextFieldPregnantNumber;
    private javax.swing.JTextField jTextFieldRemarkNote;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoRadioButtonCream;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoRadioButtonMenses;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoRadioButtonResultVerify;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoRadioButtonUterus_Lochia;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoRadioButtonUterus_Milk_Seep;
    private com.pcu.gui.utility.component.PanelYesNoRadioButton panelYesNoRadioButtonUterus_level;
    private com.hospital_os.utility.TimeTextField timeTextFieldCheck;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setObject(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getObject() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void refreshList() {
        this.setObject(pcuobject);
    }

    @Override
    public void selectList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}



