
/*
 * PanelAfterMchMother.java
 *
 * Created on 13 มิถุนายน 2548, 18:24 น.
 */
/*
 * ตรวจสอบวันที่สำรวจแล้ว
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
    private boolean selectData;/**ข้อมูลที่ใช้ในการกำหนดการทำงานของ panel ถ้าเป็น true ทำงานปกติ ถ้าเป็น false ให้ทำงานตามข้อมูลอื่น*/    
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
    private String visit_status;/**ใช้ในการบอกสถานะของการ visit ครั้งนั้นๆ*/    
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
        theJD.setTitle("ดูแลแม่หลังคลอด");
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
        headTableAfterMCHMother[0] = "ครรภ์ที่";        
        headTableAfterMCHMother[1] = "ครั้งที่";
        headTableAfterMCHMother[2] = "บันทึก";        
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
     * ส่งค่าข้อมูล JFrame
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
     *ใช้ในการ รับค่า PCUObject จาก PanelPCU เมื่อมีการส่งข้อมูลกลับมาของ Transaction
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
     *อ่านค่าข้อมูลจาก PcuObject
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
    
    {   String yes = GutilPCU.setLanguage("ปกติ");
        
        String no = GutilPCU.setLanguage("ผิดปกติ");
        
        ComboboxModel.initComboBox(jComboBoxCheckPlace,theHC.theAllComboBoxControl.listPostpartumBirthPlace());
        
        ComboboxModel.initComboBox(jComboBoxSew,theHC.theAllComboBoxControl.listPostpartumEpisiotomyType());
        
        ComboboxModel.initComboBox(jComboBoxSugarLevel,theHC.theAllComboBoxControl.listPregnancyPregnantLevel());
        
        ComboboxModel.initComboBox(jComboBoxAlblumin,theHC.theAllComboBoxControl.listPregnancyPregnantLevel());
        
        setInitComboBox();
        
        panelYesNoRadioButtonUterus_level.setTextYesNo(yes,no);
        
        panelYesNoRadioButtonCream.setTextYesNo(yes,no);
        
        panelYesNoRadioButtonMenses.setTextYesNo(yes,no);
        
        panelYesNoRadioButtonResultVerify.setTextYesNo(yes,no);
        
        yes = GutilPCU.setLanguage("มี");
        
        no = GutilPCU.setLanguage("ไม่มี");
        
        panelYesNoRadioButtonUterus_Lochia.setTextYesNo(yes,no);
        
        yes = GutilPCU.setLanguage("ไหล");
        
        no = GutilPCU.setLanguage("ไม่ไหล");
        
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

        jPanelAfterMchMomList.setBorder(javax.swing.BorderFactory.createTitledBorder("ดูแลหลังคลอด(แม่)"));
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

        jButtonAppointment.setText("นัด");
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

        jLabel5.setText("วันที่บันทึก");
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

        jLabel6.setText("น.");
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

        jButtonContinue.setText("จบ");
        jButtonContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonContinueActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        jPanelAfterMchMomControl.add(jButtonContinue, gridBagConstraints);

        jButton1.setText("<< ย้อนกลับ");
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
     * ใช้ในการตรวจสอบการกรอกตัวเลข
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
        {   theUS.setStatus(GutilPCU.getTextBundle("ValueNumberLess"), 2); //การดูแลครั้งที่ต้องมีค่ามากกว่า 0
            integerTextFieldVisitNumber.requestFocus();
            integerTextFieldVisitNumber.selectAll();
        }
        if(number >= 20)
        {   theUS.setStatus(GutilPCU.getTextBundle("ValueNumberMore"), 2); //การดูแลครั้งที่ต้องมีค่าน้อยกว่า 20
            integerTextFieldVisitNumber.requestFocus();
            integerTextFieldVisitNumber.selectAll();
        }
    }
    /**
     *
     * ตรวจสอบจำนวนตัวเลขของลำดับครรถ์
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
        {   theUS.setStatus(GutilPCU.getTextBundle("ValuePragnanLess"), 2); //ลำดับครรภที่ ต้องมีค่ามากกว่า 0
            jTextFieldPregnantNumber.requestFocus();
            jTextFieldPregnantNumber.selectAll();
            flag = false;
        }
        if(number >= 20)
        {   theUS.setStatus(GutilPCU.getTextBundle("ValuePragnanMore"), 2); //ลำดับครรภที่ ต้องมีค่าน้อยกว่า 20
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
     * ใช้ตรวจสอบว่าผู้ป่วยสามารถเข้าใช้งานในหน้านี้ได้หรือไม่
     *
     *  ต้องเป็น หญิง, อายุไม่ต่ำกว่า 15 ปี หรือสามารถใช้งานได้
     *
     * @param patient ข้อมูลผู้ป่วย
     *
     * @return boolean ถ้าเป็น true สามารถเข้าใช้งานได้ ถ้าเป็น false ไม่สามารถใช้งานได้
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
                        //อายุไม่ถึง 15 ปี ต้องการเก็บข้อมูลการคลอดหรือไม่
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
            //ยังไม่ระบุเพศ หรือเป็นเพศชาย ไม่สามารถเข้ารับบริการส่วนนี้ได้
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
    /**ใช้ในการแปลงข้อมูลจาก String ไปเป็น int
     *
     *@param value เป็น String ของ ตัวเลข ถ้าไม่ใช้ ผลที่ได้จะเป็น -1
     *
     *@return เป็น int 0-9
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
    /**ให้แสดง Dialog แสดงความสัมพันธ์*/
    public void showDialogRelationBorn()
    {   theHosDialog.showDialogBornMch(theHosManage,12, pcuobject);
    }
    /**
     *
     * ใช้ในการแสดงผลข้อมูลเป็น tooltiptext ของ textfield
     *
     */
    public void setToolTipText(javax.swing.JButton button)
    {   button.setToolTipText("<html><font color=BLACK>" +GutilPCU.getTextBundle("ShowEnableServiceBornMch")+"</font></html>");
    }
    /**
     *
     *  ใช้ในการ ส่งข้อมูลหลังคลอดกลับไปยังข้อมูลการคลอด
     *
     */
    public void callBornMchMother()
    {   theHosManage.theHosSubject.theAfterMchSubject.notifyCallAfterBornMchMother();
    }
    /**
     *
     * ใช้ในการแสดงข้อมูลออกมา หลังจากที่เลือกข้อมูลในตาราง
     *
     * การแสดงวันที่เข้ารับบริการวางแผนครอบครัว
     *
     * @param row แถวที่ได้เลือก
     *
     * @param inFirst เป็น boolean ถ้าเป็น true เป็นครั้งแรก ถ้าเป็น false ไม่ใช้ครั้งแรก
     *
     * @author ผดุงรัฐ
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
     *  ตรวจสอบ ข้อมูลที่อยู่ใน List ของ ตาราง ว่าเคยเข้ารับบริการหรือไม่ ถ้าเคย จะ return เป็น true
     *
     *  ถ้าไม่เคย เป็น false
     *
     *  @return เป็น boolean true จะมีข้อมูลการเข้ารับบริการแล้ว  false ไม่มีข้อมูลซ้ำ
     *
     */
    private boolean checkServiceForVisitIDInVector()
    {   // ไม่ซ้ำเป็น false
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
            //ตรวจสอบจากฐานข้อมูลว่า มีรายการเข้ารับบริการแล้วหรือยัง ถ้ามีแล้วให้ return เป็น true
            if(!result)
            {   result = checkDataAfterMchMotherByVisitID(visit_id);
            }
            theAfterMchMotherTemp = null;
        }
        return result;
    }
    /**
     *
     *  ใช้ในการตรวจสอบข้อมูล ของ visit_id(การรับบริการ) ของครั้งนี้ ได้เข้ารับบริการหลังคลอด
     *
     *  แล้วหรือยัง ถ้ามีให้ return เป็น true ถ้าไม่มี return เป็น false
     *
     *  @param visit_id เป็น key หลังของตาราง visit
     *
     *  @return เป็น boolean ถ้าเป็น true มีการเข้ารับบริการ ของ visit_id แล้ว, false ยังไม่มีการเข้ารับบริการ
     *
     *  ของ visit_id
     *
     */
    private boolean checkDataAfterMchMotherByVisitID(String visit_id)
    {
        return theHC.theAfterMCHMotherControl.checkDataAfterMchMotherByVisitID(visit_id);
    }
    /**
     *
     * ใช้ในการนำข้อมูลที่ได้จาก Object ของ FamilyPlaing ไปแสดงบน GIU
     *
     *@return boolean ถ้าเป็น true มีการกำหนดความสัมพันธ์แล้ว false ไม่มีการกำหนดความสัมพันธ์แล้ว
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
     * ใช้ในการลบข้อมูล การวางแผนครอบครัวออกจากตาราง
     *
     * โดย ถ้ารายการนั้นเป็นวันเดียวกับที่บันทึก จะลบออกจากตาราง
     *
     *    ถ้ารายการนั้นเป็นคนละวันกับที่บันทึก จะเปลี่ยนสถานะ active เป็น 0
     *
     * @author Tong
     *
     */
    public void deleteAfterMchMother()
    {
        if(this.jTableAfterMchMother.getRowCount()==-1){
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ",UpdateStatus.WARNING);
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
     *ตรวจสอบว่าผู้ป่วยหรือประชากรเสียชีวิตแล้วหรือไม่
     *
     *@param -
     *
     *@return boolean true=เสียชีวิต false=ไม่เสียชีวิต
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
     *ตรวจสอบเพศของผู้รับบริการ
     *
     *@param -
     *
     *@return boolean true=ผ่าน false=ไม่ผ่าน
     *
     *@author kingland
     *
     *@date 04/09/2549
     *
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
     *ใช้ในการบันทึกข้อมูลของ การดูแลแม่หลังคลอด
     *
     *@param -
     *
     *@return boolean true=บันทึกข้อมูลได้  false=บันทึกข้อมูลไม่ได้
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
        {   theUS.setStatus("กรุณาเลือกประชากรหรือบันทึกข้อมูลประชากรก่อน",2);
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
       //1.1.1 ไม่ซ้ำกัน
        //1.1.1.1 ทำการบันทึกข้อมูล การเข้ารับบริการหลังคลอดลงฐานข้อมูล
        int rs = theHC.theAfterMCHMotherControl.saveAfterMCHMother(theAfterMchMother);
        if(rs==0)
            return result;
        setAfterMchMother(theAfterMchMother);
       theAppointment = null;
        //1.1.1.2 ทำการค้นหา และ แสดงข้อมูลลงบนตาราง
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
        {   theUS.setStatus("กรุณาเลือกประชากรหรือบันทึกข้อมูลประชากรก่อน",2);
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
       //1.1.1 ไม่ซ้ำกัน
        //1.1.1.1 ทำการบันทึกข้อมูล การเข้ารับบริการหลังคลอดลงฐานข้อมูล
        int rs = theHC.theAfterMCHMotherControl.saveAfterMCHMotherN(theAfterMchMother);
        if(rs==0)
            return result;
        setAfterMchMother(theAfterMchMother);
       theAppointment = null;
        //1.1.1.2 ทำการค้นหา และ แสดงข้อมูลลงบนตาราง
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
     *ใช้ในการ กำหนดให้เลือก ข้อมูลในตารางเลยเมื่อมีการกดบันทึก
     *
     *โดยจะไปตรวจสอบกับ vector
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
     *  ใช้ในการตรวจสอบการซ้ำกันของลำดับการเข้ารับบริการ
     *
     *  ตรวจสอบข้อมูลดังนี้
     *
     *  1. ตรวจสอบค่าที่อยู่ ใน Vector ของการเข้ารับบริการ
     *
     *  1.1 ตรวจสอบลำดับการเข้ารับบริการกับข้อมูลที่จะบันทึก(จาก textfieldของลำดับการดูแล)
     *
     *  1.1.1 ถ้าข้อมูลที่เข้ามาใหม่ยังไม่มี objectID(การเพิ่มใหม่)
     *
     *  1.1.1.1 ตรวจสอบลำดับการดูแลมีการซ้ำกันหรือไม่ ถ้าซ้ำจะไม่บันทึก
     *
     *  1.1.2 ถ้าข้อมูลที่เข้ามาเป็นข้อมูลเดิม (การแก้ไข)
     *
     *  1.1.2.1 ตรวจสอบลำดับการดูแลมีการซ้ำกันหรือไม่
     *
     *  1.1.2.2 ถ้าซ้ำกัน ตรวจสอบ objectid ซ้ำกันหรือไม่ ถ้าซ้ำกัน ให้ทำงานตรวจสอบใหม่
     *
     *  1.1.2.3 ถ้าไม่ซ้ำกัน จะไม่บันทึก
     *
     *@return boolean เป็น true คือ เกิดการซ้ำกัน, false คือ ไม่เกิดการซ้ำกัน
     *
     */
    private boolean checkSameCareNumber(AfterMchMother amm)
    {   boolean result = false;
        /**ตรวจสอบค่าของครรภ์ที่ ว่ามีค่าหรือไม่ และค่าที่ได้ จะต้องไม่เท่ากับ 0*/
        String num = integerTextFieldVisitNumber.getText().trim();
        if(num.length() >0)
        {   int inum = convertStringToInt(num);
            if(inum <=0 )
            {   //ห้ามเป็น 0
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
        {   //ครรภ์ที่ ต้องมีข้อมูล
            theUS.setStatus(GutilPCU.getTextBundle("ValueNumberNULL"),UpdateStatus.WARNING);
            integerTextFieldVisitNumber.requestFocus();
            result = true;
        }
        num = null;
        boolean resultsame = false;
        /**ถ้า result เป็น true แสดงว่าเกิดการซ้ำกันของตัวเลขดูเลครั้งที่
         *
         *ถ้า result เป็น false แสดงว่าไม่เกิดการซ้ำกันให้ทำการหาข้อมูลที่น่าจะทำให้เกิดการซ้ำกัน
         *
         */
        if(!result)
        {   String carenumber = integerTextFieldVisitNumber.getText();
            if(vMchMother != null)
            {   //ตรวจสอบ ว่าเป็น ของความสัมพันธ์หรือไม่
                if(!selectData)
                {   int rows = vMchMother.size();
                    System.out.println("Rows : " + rows);
                    theAfterMchMotherTemp = null;
                    /**ตรวจสอบ Vector ที่แสดงอยู่ใน list*/
                    for(int i =0; i < rows;i++)
                    {   theAfterMchMotherTemp = (AfterMchMother)vMchMother.get(i);
                        /**ตรวจสอบค่า ถ้าไม่เป็น null*/
                        if(theAfterMchMotherTemp != null && theAfterMchMotherTemp.getObjectId() !=null)
                            
                        {   /**ตรวจสอบค่าที่จะบันทึกว่าเป็นการบันทึกใหม่หรือการแก้ไข*/
                            
                            if(amm.getObjectId() == null)
                                
                            {   /**การบันทึกใหม่ ให้ตรวจสอบเฉพาะลำดับการดูแล*/
                                
                                if(carenumber.equalsIgnoreCase(theAfterMchMotherTemp.pcare))
                                    
                                {   resultsame = true;
                                    
                                    break;
                                    
                                }
                                
                            }
                            
                            /**การแก้ไข ตรวจสอบเฉพาะลำดับการดูแลและเป็นข้อมูลเดียวกันหรือไม่ ถ้าไม่เป็นข้อมูลเดียวกันให้หยุด*/
                            
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
            {   /**ตรวจสอบกับฐานข้อมูล ตาม ข้อมูลการคลอด*/
                if(theBornMch!=null && theBornMch.getObjectId() != null)
                {   resultsame = theHC.theAfterMCHMotherControl.checkDataAfterMchMotherByMCHID(theBornMch.getObjectId(), carenumber);
                    System.out.println("-- resultsame = "+resultsame);
                }
            }
            if(resultsame)
            {   //ลำดับการรับบริการซ้ำกับที่มีอยู่
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
     * ตรวจสอบหมายเลขตั้งครรภ์ซ้ำ
     *
     * @param -
     *
     * @return boolean
     *
     *  true = มีหมายเลขซ้ำ
     *
     *  false = ไม่มีหมายเลขซ้ำ
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
     *ใช้ในการแปลงตัวอักษรเป็นตัวเลข
     *
     *@param value เป็น String ต้องเป็นตัวเลข
     *
     *@return int เป็นตัวเลขที่ถูกแปลงออกมาแล้ว ถ้าเกิด error จะกำหนดให้ เป็น 0
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
     * ใช้ในการ Refresh ข้อมูลในตารางการเข้ารับบริการ วางแผนครอบครัว
     *
     * ขึ้นมาใหม่
     *
     * @author ผดุงรัฐ
     *
     */
    private void refreshTableMCHMother()
    {
        Patient thePatient = pcuobject.getPatient();
        Family theFamily = pcuobject.getFamily();
        vMchMother= null;
        if(selectData&&thePatient!=null&&!thePatient.family_id.equals(""))
        {   /**!thePatient.family_id.equals("") ค้นหาจาก family_id by noom*/
            vMchMother = theHC.theAfterMCHMotherControl.selectAfterMchMotherShowGUITableByFamilyID();
            if(vMchMother==null)
                vMchMother = theHC.theAfterMCHMotherControl.selectAfterMchMotherShowGUITableByPatientID(thePatient.getObjectId());
        }
        else
        {   if(theBornMch != null&&thePatient!=null&&!thePatient.family_id.equals(""))
            {   /**ค้นหาจากข้อมูลการคลอด ครั้งที่*/
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
     * ใช้ในการแสดงข้อมูลลงตาราง ของ GUI
     *
     * @author ผดุงรัฐ
     *
     * @param vc เป็น Vector ของ Object AfterMchMother ที่ใช้ในการแสดงผลบนตาราง
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
        /**กำหนดให้ ค่าtext ของ บริการครั้งที่ มีค่า เมื่อ เอาข้อมูลมาจาก การคลอด*/
        if(!selectData)
        {  // if(size != 0)
            //  {
            //    theAfterMchMotherTemp = (AfterMchMother)vc.get(size-1);
            //     servicenumber = getStringToInt(theAfterMchMotherTemp.pcare);
            //     theAfterMchMotherTemp = null;
            // }
            //ทำการเพิ่ม การดูแลหลังคลอดให้
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
     * ใช้ในการกำหนดค่าจาก GUI ให้กับ Object
     *
     *  @param -
     *
     *  @return boolean ถ้าเป็น true เป็นผู้ป่วยใหม่ false ผู้ป่วยเก่า
     *
     *  @author ผดุงรัฐ
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
     * ใช้ในการแสดงข้อมูลออกมาจาก ComboBox ตาม param ทีกำหนด
     *
     * @author ผดุงรัฐ
     *
     * @param combobox ComboBox ที่ต้องการจะนำค่าออกมาใช้งาน
     *
     * @param type เป็นตัวกำหนดค่าที่จะ return ออก
     *
     * true คือ แสดงออกมาเป็น Code
     *
     * false คือ แสดงออกมาเป็น ข้อความ
     *
     * @return แสดงค่าที่ต้องการออกมา
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
     * ใช้ในการตั้งค่าเริ่มต้นของข้อมูล
     *
     * @author ผดุงรัฐ
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
     * ใช้ในการ กำหนดค่าเริ่มต้นให้กับ Combobox
     *
     * @param combobox ComboBox ที่ต้องการจะให้แสดงค่าเริ่มแรก
     *
     * @param code รหัสที่ต้องการจะให้แสดง เป็นข้อความใน combobox
     *
     * @author ผดุงรัฐ
     *
     */
    private void setInitComboBox(javax.swing.JComboBox combobox,String code)
    {
        ComboboxModel.setCodeComboBox(combobox,code);
    }
    /**
     *
     * แสดงข้อความเตือน
     *
     * @param message = ข้อความที่ต้องการให้แสดง
     *
     *        status = สถานะที่แสดง
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
    /**ใช้ในการตรวจสอบข้อมูลของการเข้ารับบริการ ส่วนนี้ ว่ามี visit_id
     *
     * ตรงกับที่มีอยู่หรือไม่ ถ้าตรงกันให้ ทำการเลือกข้อมูลนั้นไปเลย
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
            // ทำการวนลูปข้อมูลเพื่อหาว่ามีข้อมูลของ visit ครั้งนั้นหรือไม่
            for(int i = 0 ;i< size ; i++)
            {   //ให้ค่าที่ vector เก็บอยู่ที่ตำแหน่ง i
                theAfterMchMotherTemp  = (AfterMchMother)vMchMother.get(i);
                
                //ตรวจสอบ ค่าที่ของ object ที่ เก็บอยู่ใน vector ว่ามีค่าของ visit_id เดียวกันการการเข้ารับบริการครั้งนี้ หรือไม่
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
            // ถ้าใช้ ให้ทำการ เลือกข้อมูลชุดนี้ และแสดงบน GUI และ หยุด การทำงานของ loop
            jTableAfterMchMother.setRowSelectionInterval(selectRow,selectRow);
            selectTableAfterMchMother(selectRow,inFirst);
        }
    }
    /**
     *
     * ใช้ในการตรวจสอบว่า การรับบริการครั้งนี้เคยรับบริการดูแลหลังคลอดแล้วหรือยัง ถ้ามีอยู่แล้ว
     *
     * ให้ทำการ กำหนดให้ปุ่ม บวก ลบ และ บันทึก ไม่สามารถทำงานได้ ในตอนแรกของการใช้งาน
     *
     *
     *
     */
    private void checkServicedForVisit()
    {   //กำหนดให้เป็น false
        String visit_id = "";
        if(pcuobject.getVisit()!=null)
            visit_id = pcuobject.getVisit().getObjectId();
        boolean result = false;
        try
        {   //ตรวจสอบจากฐานข้อมูล
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
     *เซตการใช้งาน Gui
     *
     *@param boolean true=สามารถใช้งานได้ false=ไม่สามารถใช้งานได้
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
     *  รับข้อมูลการคลอด มาเพื่อเข้ารับบริการหลังคลอด
     *
     */
    public void notifyCallAfterMchMother(BornMch bornmch)
    {
        //1. กำหนดค่า ข้อมูลการคลอด
        theBornMch = bornmch;
        //2. กำหนดค่า Object ของ การดูแลหลังคลอดเป็น
        theAfterMchMother = null;
        //3. กำหนด่า ตัว Check selectData เป็น false
        selectData = false;
        //4. กำหนด ให้มีการ Clear ข้อมูลบน GUI และกำหนด ค่า เป็น true เพื่อกำหนด ให้ปุ่ม เพิ่มทำงาน
        setInitDefaultDataGUI(true);
        //5. กำหนด ให้มีการ ค้นหาข้อมูล ตาม ข้อมูลการคลอด และแสดงบนตาราง
        refreshTableMCHMother();
        checkServicedForVisit();
        checkDataForVisit(false);
        // selectDataWhenNotSelect();
    }
    /**ใช้ในการเลือก ข้อมูล แถวแรก ถ้า ไม่มีการเลือกรายการเลย
     *
     */
    private void selectDataWhenNotSelect()
    {
        int row = jTableAfterMchMother.getSelectedRow();
        if(row == -1)
        {   // กำหนดให้เลือกข้อมูล แถวที่ 0 และเลือกบนตารางด้วย
            selectTableAfterMchMother(0,false);
        }
    }
    public void notifyCallAfterBornMchMother()
    {
    }
    /**
     *
     *  ใช้ในการรับ notify จาก panel ของ ข้อมูลการคลอด เพื่อให้ ข้อมูลเป็น ชุดเดียวกันกับ
     *
     *  ข้อมูล การคลอด โดยจะส่ง Object ของ BornMch มายัง panel นี้
     *
     *  @param boornmch เป็น Object ของ BornMch
     *
     */
    public void notifyGetDataBornMchMotherToAfterMchMother(BornMch bornmch)
    {
        if(bornmch!= null)
        {
            theBornMch = bornmch;
            jTextFieldPregnantNumber.setText(theBornMch.gravida);
            //theUS.setStatus("ข้อมูลได้สร้างความสัมพันธ์ให้แล้ว กรุณากดบันทึก","Warning",JOptionPane.OK_OPTION);
            //Comment code by noom 22/10/2548 แก้จากการแสดง Dialog เป็นการแสดงใน status แทน
            theUS.setStatus("ข้อมูลได้สร้างความสัมพันธ์ให้แล้ว กรุณากดบันทึก",2);
        }
    }
    
    
    /**
     *
     * เช็ควันที่สำรวจว่าเป็นวันในอนาคตหรือไม่
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
            // ไม่สามารถกรอกวันที่เป็นวันในอนาคตได้
            if(checksurvey == false)
            {
                theUS.setStatus(GutilPCU.getTextBundle("NoDateFuture"),UpdateStatus.WARNING);
                checksurvey = true;
            }
        }
    }
    
    /**
     *  ใช้ในการรับ notify มาจาก Panel ใหญ่ของ pcu เมื่อมีการคลิกเลือก JTabbedPane ของ PanelPCU จะส่ง Notify
     *  กลับมาให้ panel นี้ ทำงาน
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



