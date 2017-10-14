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

import com.pcu.control.AllComboBoxControl;
import com.pcu.control.HealthServiceControl;
import com.pcu.control.PCUObject;
import com.pcu.gui.dialog.HosDialog;
import java.awt.event.*;
import java.util.*;
import javax.swing.table.DefaultTableCellRenderer;
import com.pcu.utility.*;
import com.pcu.utility.DateUtil;
import com.pcu.object.*;   
import com.pcu.control.HosManage;
import com.hosv3.utility.connection.UpdateStatus;
import com.hospital_os.utility.*;
import com.hospital_os.utility.Gutil;
import com.hospital_os.object.Patient;
import com.hospital_os.object.Visit;
import com.hospital_os.object.VisitStatus;
import com.hospital_os.object.NutritionType;
import com.hospital_os.object.Dischar;
import com.hosv3.control.lookup.R53NutriNLevellookup;

/**
 *
 * @author  jao
 */
public class PanelNutrition extends javax.swing.JPanel implements PanelObj {
    
    private Nutrition theNutrition;  
    private DefaultTableCellRenderer rendererCenter;       
    private HealthServiceControl theHealthServiceControl;
    private AllComboBoxControl theAllComboBoxControl;
    private Patient thePatient;
    private Visit theVisit;
    private Vector vNutrition = new Vector();    
    private Vector vNutritionType2 = new Vector();
    private String visit_status;
    private PCUObject pcuobject;
    private String office_id;
    private int rowNutrition;
    private Family theFamily;    
    private boolean checksurvey;
    private HosManage theHosManage;
    private HosDialog theHosDialog;
    private UpdateStatus theUS;
    
    public PanelNutrition() {
        initComponents();
        jLabel2.setVisible(false);
    }
    public PanelNutrition(HosManage hm,HosDialog hd,UpdateStatus us)  {
        initComponents();
        jLabel2.setVisible(false);
        setControl(hm,hd,us);
    }
    public void setControl(HosManage hm,HosDialog hd,UpdateStatus us){
        theHosManage = hm;
        theUS = us;
        theAllComboBoxControl = hm.theHosControl.theAllComboBoxControl;
        theHealthServiceControl = hm.theHosControl.theHealthServiceControl;
        theHealthServiceControl.setHosManage(theHosManage);
        theHosDialog = hd;
        setLanguage();
        vNutritionType2 = theAllComboBoxControl.listNutritionType();
        R53NutriNLevellookup lup = new R53NutriNLevellookup(hm.theHosControl.theConnectionInf);
        ComboboxModel.initComboBox(jComboBoxNutrition,vNutritionType2);
//        ComboboxModel.initComboBox(this.jComboBoxNutrition53,lup.listData("%"));
        setEnabled(false);
    }

     /**
      *ไปจัดการต่อเรื่องของข้อมูลที่จะรับ ถ้าเมื่อไรไม่มีข้อมูล นั้นจะทำอย่างไรบนหน้า GUI 
      *@param PCU object
      *@return void
      *@author jao
      *@modify kingland
      *@date 05/09/2549
      */
     public void setObject(PCUObject pcuobject)
     {
        Constant.println("_henbe_______________________" + this.getClass().toString());
        this.pcuobject = pcuobject;
        visit_status ="0";
        theNutrition = null;
        thePatient = null;
        theFamily = null;
        setEnabled(true);
        if(pcuobject != null)
        {
            theFamily = pcuobject.getFamily();
            theVisit = null;
            visit_status = null;
            if(pcuobject.getVisit() != null)
            {
                theVisit = pcuobject.getVisit();
                visit_status = pcuobject.getVisit().visit_status;
                if(pcuobject.getVisit().visit_status.equalsIgnoreCase(VisitStatus.isInProcess()))
                {
                    jButtonAdd.setEnabled(true);
                    setEnabled(true);
                }
                else
                {
                    Constant.println("Old Visit ");                                       
                }
            }
            thePatient = null;
            if(pcuobject.getPatient() != null)
            {
                thePatient = pcuobject.getPatient();
                jButtonAdd.setEnabled(true);  
                setEnabled(true);
            }            
            office_id = null;
            if(pcuobject.getSite() != null)
            {
                office_id = pcuobject.getSite().off_id;
            }
            setNutrition(null);
            setNutritionV(pcuobject.vNutrition);
        }
        if(!checkPatientAndFamily())
        {
            setEnabled(false);
        }
        else if(checkDead())
        {
            setEnabled(false);
        }
    }
     
    public void setObjectFamily (Family family)
    {
        Constant.println("_henbe other_______________________" + this.getClass().toString());
        visit_status = "0";
        thePatient = null;
        theFamily = family;
        theVisit = null;
        if(theFamily!=null)
        {   
            setEnabled(true);
            jButtonAdd.setEnabled(true);
        }
        setNutrition(null);
        setNutritionV(pcuobject.vNutrition);
    } 
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroupCleanNavel = new javax.swing.ButtonGroup();
        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jLabelHigh = new javax.swing.JLabel();
        jPanelNutritionList = new javax.swing.JPanel();
        jScrollPaneNutriList = new javax.swing.JScrollPane();
        jTableNutriList = new javax.swing.JTable();
        jPanelNutritionDetail = new javax.swing.JPanel();
        jPanelNutritionService = new javax.swing.JPanel();
        jLabelAge = new javax.swing.JLabel();
        jLabelHeadRim = new javax.swing.JLabel();
        jLabelWeight = new javax.swing.JLabel();
        jLabelNewTooth = new javax.swing.JLabel();
        jLabelBadTooth = new javax.swing.JLabel();
        jLabelNutrition = new javax.swing.JLabel();
        jComboBoxNutrition = new javax.swing.JComboBox();
        jLabelBMI = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        integerTextFieldAge = new com.pcu.utility.IntegerTextField();
        jLabelMonth = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabelcms1 = new javax.swing.JLabel();
        integerTextFieldHeadRim = new com.pcu.utility.DoubleTextField();
        jPanel4 = new javax.swing.JPanel();
        integerTextFieldNewTooth = new com.pcu.utility.IntegerTextField();
        jLabelN1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        integerTextFieldBadTooth = new com.pcu.utility.IntegerTextField();
        jLabelN2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabelcms = new javax.swing.JLabel();
        integerTextFieldNtrHigh = new com.hospital_os.utility.DoubleTextField();
        doubleTextFieldBMI = new com.hospital_os.utility.DoubleTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabelKgs = new javax.swing.JLabel();
        integerTextFieldNtrWeight = new com.hospital_os.utility.DoubleTextField();
        dateComboBoxSurvey = new com.pcu.utility.DateComboBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabelCleanNavel = new javax.swing.JLabel();
        jRadioButtonCleanNavel0 = new javax.swing.JRadioButton();
        jRadioButtonCleanNavel1 = new javax.swing.JRadioButton();
        jButtonAppointment = new javax.swing.JButton();
        jLabelSurveyDate = new javax.swing.JCheckBox();
        jPanelRemark = new javax.swing.JPanel();
        jLabelRemark = new javax.swing.JLabel();
        jTextFieldRemark = new javax.swing.JTextField();
        jPanelResultNutrition = new javax.swing.JPanel();
        jScrollPaneNote1 = new javax.swing.JScrollPane();
        jTextAreaNoteResult = new javax.swing.JTextArea();
        jPanel16 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        dateComboBoxCheck = new com.hospital_os.utility.DateComboBox();
        jLabel6 = new javax.swing.JLabel();
        timeTextFieldCheck = new com.hospital_os.utility.TimeTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabelVN = new javax.swing.JLabel();
        jPanelNutritionControl = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jButtonDel = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jLabelHigh.setText("NtrHigh");

        setMinimumSize(new java.awt.Dimension(700, 400));
        setPreferredSize(new java.awt.Dimension(700, 400));
        setRequestFocusEnabled(false);
        setLayout(new java.awt.GridBagLayout());

        jPanelNutritionList.setBorder(javax.swing.BorderFactory.createTitledBorder("Nutrition_Data"));
        jPanelNutritionList.setMinimumSize(new java.awt.Dimension(250, 120));
        jPanelNutritionList.setPreferredSize(new java.awt.Dimension(250, 120));
        jPanelNutritionList.setLayout(new java.awt.GridBagLayout());

        jScrollPaneNutriList.setEnabled(false);
        jScrollPaneNutriList.setMinimumSize(new java.awt.Dimension(235, 23));
        jScrollPaneNutriList.setOpaque(false);
        jScrollPaneNutriList.setPreferredSize(new java.awt.Dimension(235, 90));

        jTableNutriList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableNutriList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableNutriListMouseReleased(evt);
            }
        });
        jTableNutriList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableNutriListKeyReleased(evt);
            }
        });
        jScrollPaneNutriList.setViewportView(jTableNutriList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelNutritionList.add(jScrollPaneNutriList, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        add(jPanelNutritionList, gridBagConstraints);

        jPanelNutritionDetail.setBorder(javax.swing.BorderFactory.createTitledBorder("Nutrition_Detail"));
        jPanelNutritionDetail.setLayout(new java.awt.GridBagLayout());

        jPanelNutritionService.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanelNutritionService.setLayout(new java.awt.GridBagLayout());

        jLabelAge.setText("NtrAge");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelNutritionService.add(jLabelAge, gridBagConstraints);

        jLabelHeadRim.setText("Rim");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanelNutritionService.add(jLabelHeadRim, gridBagConstraints);

        jLabelWeight.setText("NtrWeight");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanelNutritionService.add(jLabelWeight, gridBagConstraints);

        jLabelNewTooth.setText("NewTooth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanelNutritionService.add(jLabelNewTooth, gridBagConstraints);

        jLabelBadTooth.setText("BadTooth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelNutritionService.add(jLabelBadTooth, gridBagConstraints);

        jLabelNutrition.setText("Nutrition");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelNutritionService.add(jLabelNutrition, gridBagConstraints);

        jComboBoxNutrition.setBackground(new java.awt.Color(204, 255, 255));
        jComboBoxNutrition.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBoxNutritionKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelNutritionService.add(jComboBoxNutrition, gridBagConstraints);

        jLabelBMI.setText("BMI");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanelNutritionService.add(jLabelBMI, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        integerTextFieldAge.setBackground(new java.awt.Color(204, 255, 255));
        integerTextFieldAge.setColumns(4);
        integerTextFieldAge.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldAge.setMinimumSize(new java.awt.Dimension(35, 21));
        integerTextFieldAge.setPreferredSize(new java.awt.Dimension(38, 21));
        integerTextFieldAge.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                integerTextFieldAgeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                integerTextFieldAgeFocusLost(evt);
            }
        });
        integerTextFieldAge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldAgeKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(integerTextFieldAge, gridBagConstraints);

        jLabelMonth.setText("Months");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel2.add(jLabelMonth, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelNutritionService.add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabelcms1.setText("cms");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jLabelcms1, gridBagConstraints);

        integerTextFieldHeadRim.setColumns(4);
        integerTextFieldHeadRim.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldHeadRim.setMinimumSize(new java.awt.Dimension(35, 21));
        integerTextFieldHeadRim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldHeadRimKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel3.add(integerTextFieldHeadRim, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelNutritionService.add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        integerTextFieldNewTooth.setColumns(4);
        integerTextFieldNewTooth.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldNewTooth.setMinimumSize(new java.awt.Dimension(35, 21));
        integerTextFieldNewTooth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldNewToothKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(integerTextFieldNewTooth, gridBagConstraints);

        jLabelN1.setText("n");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(jLabelN1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelNutritionService.add(jPanel4, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        integerTextFieldBadTooth.setColumns(4);
        integerTextFieldBadTooth.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldBadTooth.setMinimumSize(new java.awt.Dimension(35, 21));
        integerTextFieldBadTooth.setPreferredSize(new java.awt.Dimension(38, 21));
        integerTextFieldBadTooth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldBadToothKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(integerTextFieldBadTooth, gridBagConstraints);

        jLabelN2.setText("n");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel5.add(jLabelN2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelNutritionService.add(jPanel5, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabelcms.setText("cms");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jLabelcms, gridBagConstraints);

        integerTextFieldNtrHigh.setBackground(new java.awt.Color(204, 255, 255));
        integerTextFieldNtrHigh.setColumns(4);
        integerTextFieldNtrHigh.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldNtrHigh.setMinimumSize(new java.awt.Dimension(35, 21));
        integerTextFieldNtrHigh.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                integerTextFieldNtrHighFocusLost(evt);
            }
        });
        integerTextFieldNtrHigh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldNtrHighKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel7.add(integerTextFieldNtrHigh, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelNutritionService.add(jPanel7, gridBagConstraints);

        doubleTextFieldBMI.setColumns(4);
        doubleTextFieldBMI.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        doubleTextFieldBMI.setMinimumSize(new java.awt.Dimension(35, 21));
        doubleTextFieldBMI.setPreferredSize(new java.awt.Dimension(38, 21));
        doubleTextFieldBMI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                doubleTextFieldBMIKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelNutritionService.add(doubleTextFieldBMI, gridBagConstraints);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabelKgs.setText("kgs");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel9.add(jLabelKgs, gridBagConstraints);

        integerTextFieldNtrWeight.setBackground(new java.awt.Color(204, 255, 255));
        integerTextFieldNtrWeight.setColumns(4);
        integerTextFieldNtrWeight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldNtrWeight.setMinimumSize(new java.awt.Dimension(35, 21));
        integerTextFieldNtrWeight.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                integerTextFieldNtrWeightFocusLost(evt);
            }
        });
        integerTextFieldNtrWeight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldNtrWeightKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel9.add(integerTextFieldNtrWeight, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelNutritionService.add(jPanel9, gridBagConstraints);

        dateComboBoxSurvey.setEnabled(false);
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
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanelNutritionService.add(dateComboBoxSurvey, gridBagConstraints);

        jLabel1.setText("ส่วนสูง");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 0, 0);
        jPanelNutritionService.add(jLabel1, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabelCleanNavel.setText("CleanNavel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabelCleanNavel, gridBagConstraints);

        buttonGroupCleanNavel.add(jRadioButtonCleanNavel0);
        jRadioButtonCleanNavel0.setText("NotClean");
        jRadioButtonCleanNavel0.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jRadioButtonCleanNavel0KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jRadioButtonCleanNavel0, gridBagConstraints);

        buttonGroupCleanNavel.add(jRadioButtonCleanNavel1);
        jRadioButtonCleanNavel1.setSelected(true);
        jRadioButtonCleanNavel1.setText("Clean");
        jRadioButtonCleanNavel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jRadioButtonCleanNavel1KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jRadioButtonCleanNavel1, gridBagConstraints);

        jButtonAppointment.setText("นัด");
        jButtonAppointment.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAppointmentActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jButtonAppointment, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanelNutritionService.add(jPanel1, gridBagConstraints);

        jLabelSurveyDate.setText("SurveyDate");
        jLabelSurveyDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jLabelSurveyDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabelSurveyDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelNutritionService.add(jLabelSurveyDate, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelNutritionDetail.add(jPanelNutritionService, gridBagConstraints);

        jPanelRemark.setLayout(new java.awt.GridBagLayout());

        jLabelRemark.setText("Remark");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelRemark.add(jLabelRemark, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelRemark.add(jTextFieldRemark, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 11);
        jPanelNutritionDetail.add(jPanelRemark, gridBagConstraints);

        jPanelResultNutrition.setBorder(javax.swing.BorderFactory.createTitledBorder("ResultNutrition"));
        jPanelResultNutrition.setLayout(new java.awt.GridBagLayout());

        jScrollPaneNote1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneNote1.setMinimumSize(new java.awt.Dimension(220, 50));
        jScrollPaneNote1.setPreferredSize(new java.awt.Dimension(220, 50));

        jTextAreaNoteResult.setLineWrap(true);
        jTextAreaNoteResult.setWrapStyleWord(true);
        jScrollPaneNote1.setViewportView(jTextAreaNoteResult);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelResultNutrition.add(jScrollPaneNote1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelNutritionDetail.add(jPanelResultNutrition, gridBagConstraints);

        jPanel16.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("วันที่บันทึก");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 3);
        jPanel16.add(jLabel5, gridBagConstraints);

        dateComboBoxCheck.setBackground(new java.awt.Color(204, 255, 255));
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 5, 0);
        jPanelNutritionDetail.add(jPanel16, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jPanelNutritionDetail, gridBagConstraints);

        jPanelNutritionControl.setLayout(new java.awt.GridBagLayout());

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        jPanelNutritionControl.add(jButtonSave, gridBagConstraints);

        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
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
        jPanelNutritionControl.add(jButtonAdd, gridBagConstraints);

        jButtonDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif"))); // NOI18N
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
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelNutritionControl.add(jButtonDel, gridBagConstraints);

        jLabel2.setText("ช่วงอายุ 0-5 ปีออก 18 แฟ้ม 6-14ปีออก 11รง5");
        jPanelNutritionControl.add(jLabel2, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 5, 11);
        add(jPanelNutritionControl, gridBagConstraints);
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

    private void jButtonAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAppointmentActionPerformed
        if(theHosManage.thePO.getPatient() == null) {
            theUS.setStatus(GutilPCU.getTextBundle("No_Patient_Appointment"),UpdateStatus.WARNING);
            return;
        }
        theHosDialog.showDialogAppointment(theHosManage,theHosManage.thePO);
    }//GEN-LAST:event_jButtonAppointmentActionPerformed

    private void jRadioButtonCleanNavel1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jRadioButtonCleanNavel1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {   jRadioButtonCleanNavel1.setSelected(true);
            if(dateComboBoxSurvey.isVisible())
            {
                dateComboBoxSurvey.requestFocus();
            }
            else
            {
                jRadioButtonCleanNavel1.transferFocus();
            }
        }
        else if(evt.getKeyCode() == KeyEvent.VK_LEFT)
        {
            jRadioButtonCleanNavel0.requestFocus();
        }
    }//GEN-LAST:event_jRadioButtonCleanNavel1KeyReleased

    private void jRadioButtonCleanNavel0KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jRadioButtonCleanNavel0KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            jRadioButtonCleanNavel0.setSelected(true);
            if(dateComboBoxSurvey.isVisible())
            {
                dateComboBoxSurvey.requestFocus();
            }
            else 
            {
                jRadioButtonCleanNavel0.transferFocus();
            }
        }
        else if(evt.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            jRadioButtonCleanNavel1.requestFocus();
        }
    }//GEN-LAST:event_jRadioButtonCleanNavel0KeyReleased

    private void jComboBoxNutritionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxNutritionKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            jComboBoxNutrition.transferFocus();
        }
    }//GEN-LAST:event_jComboBoxNutritionKeyReleased

    private void doubleTextFieldBMIKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_doubleTextFieldBMIKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            doubleTextFieldBMI.transferFocus();
        }
    }//GEN-LAST:event_doubleTextFieldBMIKeyReleased

    private void integerTextFieldNtrWeightKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldNtrWeightKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            integerTextFieldNtrWeight.transferFocus();
        }
    }//GEN-LAST:event_integerTextFieldNtrWeightKeyReleased

    private void integerTextFieldBadToothKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldBadToothKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            integerTextFieldBadTooth.transferFocus();
        }
    }//GEN-LAST:event_integerTextFieldBadToothKeyReleased

    private void integerTextFieldNewToothKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldNewToothKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {   integerTextFieldNewTooth.transferFocus();
        }
    }//GEN-LAST:event_integerTextFieldNewToothKeyReleased

    private void integerTextFieldHeadRimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldHeadRimKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            integerTextFieldHeadRim.transferFocus();
        }
    }//GEN-LAST:event_integerTextFieldHeadRimKeyReleased

    private void integerTextFieldAgeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldAgeKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            integerTextFieldAge.transferFocus();
        }
    }//GEN-LAST:event_integerTextFieldAgeKeyReleased

    private void integerTextFieldNtrWeightFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integerTextFieldNtrWeightFocusLost
         String month = integerTextFieldAge.getText();
         if(month.equals(""))
         {
             return;
         }
        int months = Integer.parseInt(month);
        if(months >= 0 && months < 73)
        {
            int index = com.hospital_os.utility.Constant.calculateIndexComboBoxNutrition(theFamily.f_sex_id,month,
                    integerTextFieldNtrWeight.getText());
            if(index == 5)
            {
                theUS.setStatus("ไม่ได้ชั่งน้ำหนัก", UpdateStatus.WARNING);
                return;
            }
            if(index == 6)
            {
                theUS.setStatus("ไม่ได้ระบุเพศ", UpdateStatus.WARNING);
                return;
            }        
            jComboBoxNutrition.setSelectedIndex(index);        
        }
    }//GEN-LAST:event_integerTextFieldNtrWeightFocusLost

    private void dateComboBoxSurveyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxSurveyFocusLost
        checksurvey = false;
        checkDateSurvey();
    }//GEN-LAST:event_dateComboBoxSurveyFocusLost

    private void dateComboBoxSurveyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxSurveyActionPerformed
        checkDateSurvey();
        checksurvey = false;
    }//GEN-LAST:event_dateComboBoxSurveyActionPerformed

    private void jTableNutriListKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableNutriListKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_UP
        || evt.getKeyCode()==KeyEvent.VK_DOWN){
            selectNutrition(-2);
         }
    }//GEN-LAST:event_jTableNutriListKeyReleased

    private void integerTextFieldAgeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integerTextFieldAgeFocusGained

    }//GEN-LAST:event_integerTextFieldAgeFocusGained

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        saveNutrition();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jTableNutriListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableNutriListMouseReleased
        selectNutrition(-2);
        jButtonDel.setEnabled(true);
    }//GEN-LAST:event_jTableNutriListMouseReleased

    private void jButtonDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelActionPerformed

        deleteNutrition();
    }//GEN-LAST:event_jButtonDelActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
//        if(vNutrition!= null && theVisit != null)
//        {
//            for(int i = vNutrition.size()-1 ; i >=0  ; i--)
//            {
//                 Nutrition nutrition2 = (Nutrition)vNutrition.get(i);
//                 if(nutrition2.visit_id.equals(theVisit.getObjectId()))
//                 {
//                     theUS.setStatus(GutilPCU.getTextBundle("VisitAlready"),UpdateStatus.WARNING);
//                     return;
//                 }
//            }
//        }
        setNutrition(null);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void integerTextFieldAgeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integerTextFieldAgeFocusLost
        if((!integerTextFieldAge.getText().equals("")) && Integer.parseInt(integerTextFieldAge.getText()) > 73)
        {
            this.theUS.setStatus("อายุมากกว่า 73 เดือน ข้อมูลนี้จะไม่ออกในรายงาน", UpdateStatus.WARNING);
        }
    }//GEN-LAST:event_integerTextFieldAgeFocusLost

    private void integerTextFieldNtrHighFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integerTextFieldNtrHighFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_integerTextFieldNtrHighFocusLost

    private void integerTextFieldNtrHighKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldNtrHighKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_integerTextFieldNtrHighKeyReleased
    public void setLanguage()
    {    /*jLabel*/
        GutilPCU.setLanguage(jLabel1);
        GutilPCU.setLanguage(jLabelBMI);
        GutilPCU.setLanguage(jLabelAge);
        GutilPCU.setLanguage(jLabelBadTooth);
        GutilPCU.setLanguage(jLabelCleanNavel);
        GutilPCU.setLanguage(jLabelHigh);
        GutilPCU.setLanguage(jLabelNewTooth);
        GutilPCU.setLanguage(jLabelNutrition);
        GutilPCU.setLanguage(jLabelWeight);
        GutilPCU.setLanguage(jLabelMonth);
        GutilPCU.setLanguage(jLabelcms1);
        GutilPCU.setLanguage(jLabelN1);
        GutilPCU.setLanguage(jLabelN2);
        GutilPCU.setLanguage(jLabelKgs);
        GutilPCU.setLanguage(jLabelcms);
        GutilPCU.setLanguage(jLabelRemark);
        GutilPCU.setLanguage(jLabelSurveyDate);
        GutilPCU.setLanguage(jButtonAdd);
        GutilPCU.setLanguage(jButtonDel);
        GutilPCU.setLanguage(jButtonSave);
        GutilPCU.setLanguage(jLabelHeadRim);
        /*TitledBorder*/
        GutilPCU.JPanelLabler(jPanelNutritionList);
        GutilPCU.JPanelLabler(jPanelNutritionDetail);
        GutilPCU.JPanelLabler(jPanelResultNutrition);
        GutilPCU.setLanguage(jLabel5);
        GutilPCU.setLanguage(jLabel6);
//        GutilPCU.JPanelLabler(jPanelRemark);
        GutilPCU.setLanguage(jButtonAppointment);
        /*Radio*/
        jRadioButtonCleanNavel0.setText(GutilPCU.getTextBundle(jRadioButtonCleanNavel0.getText()));
        jRadioButtonCleanNavel1.setText(GutilPCU.getTextBundle(jRadioButtonCleanNavel1.getText()));
      }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupCleanNavel;
    private com.hospital_os.utility.DateComboBox dateComboBoxCheck;
    private com.pcu.utility.DateComboBox dateComboBoxSurvey;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private com.hospital_os.utility.DoubleTextField doubleTextFieldBMI;
    private com.pcu.utility.IntegerTextField integerTextFieldAge;
    private com.pcu.utility.IntegerTextField integerTextFieldBadTooth;
    private com.pcu.utility.DoubleTextField integerTextFieldHeadRim;
    private com.pcu.utility.IntegerTextField integerTextFieldNewTooth;
    private com.hospital_os.utility.DoubleTextField integerTextFieldNtrHigh;
    private com.hospital_os.utility.DoubleTextField integerTextFieldNtrWeight;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonAppointment;
    private javax.swing.JButton jButtonDel;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBoxNutrition;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAge;
    private javax.swing.JLabel jLabelBMI;
    private javax.swing.JLabel jLabelBadTooth;
    private javax.swing.JLabel jLabelCleanNavel;
    private javax.swing.JLabel jLabelHeadRim;
    private javax.swing.JLabel jLabelHigh;
    private javax.swing.JLabel jLabelKgs;
    private javax.swing.JLabel jLabelMonth;
    private javax.swing.JLabel jLabelN1;
    private javax.swing.JLabel jLabelN2;
    private javax.swing.JLabel jLabelNewTooth;
    private javax.swing.JLabel jLabelNutrition;
    private javax.swing.JLabel jLabelRemark;
    private javax.swing.JCheckBox jLabelSurveyDate;
    private javax.swing.JLabel jLabelVN;
    private javax.swing.JLabel jLabelWeight;
    private javax.swing.JLabel jLabelcms;
    private javax.swing.JLabel jLabelcms1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelNutritionControl;
    private javax.swing.JPanel jPanelNutritionDetail;
    private javax.swing.JPanel jPanelNutritionList;
    private javax.swing.JPanel jPanelNutritionService;
    private javax.swing.JPanel jPanelRemark;
    private javax.swing.JPanel jPanelResultNutrition;
    private javax.swing.JRadioButton jRadioButtonCleanNavel0;
    private javax.swing.JRadioButton jRadioButtonCleanNavel1;
    private javax.swing.JScrollPane jScrollPaneNote1;
    private javax.swing.JScrollPane jScrollPaneNutriList;
    private javax.swing.JTable jTableNutriList;
    private javax.swing.JTextArea jTextAreaNoteResult;
    private javax.swing.JTextField jTextFieldRemark;
    private com.hospital_os.utility.TimeTextField timeTextFieldCheck;
    // End of variables declaration//GEN-END:variables
   
    /**
     * ตรวจสอบรากการโภชนาการว่าสามารถลบได้หรือไม่
     * @return boolean true = ถูกต้อง false = ไม่ถูกต้อง
     * @param - 
     * @author kingland
     * @date 29/05/2549
     */
    private boolean checkBeforDel()
    {   boolean result = true;
        //ตรวจว่ารายการมี Visit
        if(theNutrition != null && !"".equals(theNutrition.visit_id))
        {
            if(pcuobject.getVisit() != null && !"".equals(pcuobject.getVisit().getObjectId()))
            {   //ถ้า visit id ตรงกันสามารถลบได้
                if(theNutrition.visit_id.equals(pcuobject.getVisit().getObjectId()))
                {
                    result = true;
                }
                //ถ้า visit id ไม่ตรงกันไม่สามารถลบได้
                else
                {
                    result = false;
                    theUS.setStatus(GutilPCU.getTextBundle("NotDeleteBeforVisit"), UpdateStatus.WARNING);//ไม่สามารถลบรายการที่บันทึกในการรับบริการครั้งก่อนได้
                }
            }
            else
            {
                result = false;
                theUS.setStatus(GutilPCU.getTextBundle("NotDeleteBeforVisit"), UpdateStatus.WARNING);//ไม่สามารถลบรายการที่บันทึกในการรับบริการครั้งก่อนได้
            }
        }
        //ตรวจสอบว่ารายการไม่มี Visit
        else if(result == true && theNutrition != null &&  ("".equals(theNutrition.visit_id) || theNutrition.visit_id == null))
        {
            result = true;
        }
        return result;
    }
    /**
     *เซตการงาน Gui
     *@param boolean
     *@return void
     *@author kingland
     *@date 04/09/2549
     */
    public void setEnabled(boolean flag)
    {
        jButtonAdd.setEnabled(flag);
        integerTextFieldAge.setEnabled(flag);
        integerTextFieldHeadRim.setEnabled(flag);
        integerTextFieldNtrWeight.setEnabled(flag);
        integerTextFieldNtrHigh.setEnabled(flag);
        integerTextFieldNewTooth.setEnabled(flag);
        integerTextFieldBadTooth.setEnabled(flag);
        jRadioButtonCleanNavel0.setEnabled(flag);
        jRadioButtonCleanNavel1.setEnabled(flag);
        jTextAreaNoteResult.setEnabled(flag);
        jTextFieldRemark.setEnabled(flag);
        jButtonDel.setEnabled(flag);
        jButtonSave.setEnabled(flag);
        doubleTextFieldBMI.setEnabled(flag);
    }
    /**
     *นำข้อมูลจาก Gui ใส่ใน Object
     *@param -
     *@return void
     *@author kingland
     *@modify kingland
     *@date 01/09/2549
     */
    public Nutrition getNutrition()
    {   if(theNutrition == null)
            theNutrition = new Nutrition();
        theNutrition.nutrition_age = integerTextFieldAge.getText();
        theNutrition.nutrition_rim = integerTextFieldHeadRim.getText();
        theNutrition.nutrition_weight = integerTextFieldNtrWeight.getText();
        theNutrition.nutrition_high = integerTextFieldNtrHigh.getText();
        theNutrition.nutrition_newtooth = integerTextFieldNewTooth.getText();        
        theNutrition.nutrition_badtooth = integerTextFieldBadTooth.getText();
        if(jRadioButtonCleanNavel0.isSelected())
        {
            theNutrition.answer_id = PcuAnswer.Zero();
        }
        else
        {
            theNutrition.answer_id = PcuAnswer.One();
        } 
        theNutrition.nutrition_bmi = doubleTextFieldBMI.getText();
        theNutrition.nutrition_result = jTextAreaNoteResult.getText();
        theNutrition.nutrition_notice = jTextFieldRemark.getText();
        theNutrition.office_id =office_id;
        theNutrition.survey_date = dateComboBoxSurvey.getText();
        theNutrition.modify_date_time = dateComboBoxCheck.getText()+","+timeTextFieldCheck.getText()+":00";
        if(theNutrition.getObjectId()==null)
            theNutrition.record_date_time = dateComboBoxCheck.getText()+","+timeTextFieldCheck.getText()+":00";
        theNutrition.nutrition_level_id = ComboboxModel.getCodeComboBox(jComboBoxNutrition);
        theNutrition.active = "1";
        return theNutrition;
    }
    /**
     *บันทึกข้อมูลโภชนาการ
     *@param -
     *@return int จำนวนเรคคอร์ดที่บันทึกข้อมูลได้
     *@author -
     *@modify kingland
     *@date 01/09/2549
     */
    private int saveNutrition()
    {
        getNutrition();
        int result = theHealthServiceControl.saveNutrition(theNutrition);
        if(result>=3)
            return result;
        setNutritionV(pcuobject.vNutrition);
        for(int i=0;i<this.vNutrition.size();i++){
            Nutrition pp = (Nutrition)vNutrition.get(i);
            if(pp.getObjectId().equals(theNutrition.getObjectId())){
                this.jTableNutriList.setRowSelectionInterval(i,i);
                return result;
            }
        }
        return result;
    } 
    
    public void setNutritionV(Vector nutritionV)
    {
        vNutrition = nutritionV;
        /***SetTable***/
        String[] col = {GutilPCU.getTextBundle("No"),
                        GutilPCU.getTextBundle("VN"),
                        GutilPCU.getTextBundle("NutritionDateRecord")}; 
                                     
        Nutrition nutritionTemp = new Nutrition(); 
        TaBleModel tm ;
        String vn ="";
        if(vNutrition != null)
        {   
            tm = new TaBleModel(col,vNutrition.size());
            for(int i=0, size=vNutrition.size(); i<size; i++)
            {
                nutritionTemp = (Nutrition)vNutrition.get(i);
                if(nutritionTemp.nutrition_vn.equals("null")||nutritionTemp.nutrition_vn.equals(""))
                    vn = GutilPCU.getTextBundle("NoVN");                 
                else 
                    vn = nutritionTemp.nutrition_vn;
                tm.setValueAt(String.valueOf((i+1)),i,0);
                tm.setValueAt(vn,i,1);                
                tm.setValueAt(GutilPCU.changDateToString(nutritionTemp.modify_date_time,false),i,2);
            }
        }
        else
        {   
            tm = new TaBleModel(col,0);
        }        
        
        jTableNutriList.setModel(tm);  
        nutritionTemp = null;
        if(rendererCenter == null){
            rendererCenter = new DefaultTableCellRenderer();
        }        
        rendererCenter.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        jTableNutriList.getColumnModel().getColumn(0).setCellRenderer(rendererCenter);
        jTableNutriList.getColumnModel().getColumn(1).setCellRenderer(rendererCenter);
        jTableNutriList.getColumnModel().getColumn(2).setCellRenderer(rendererCenter);        
        jTableNutriList.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTableNutriList.getColumnModel().getColumn(1).setPreferredWidth(80); 
        jTableNutriList.getColumnModel().getColumn(2).setPreferredWidth(100); 
    }
    public void setNutrition(Nutrition nutrition)
    {
        theNutrition = nutrition;
        if(theNutrition == null)
        {
            theNutrition = pcuobject.initNutrition();
            
        }

//        if((!theNutrition.nutrition_age.equals("")) && Integer.parseInt(theNutrition.nutrition_age) > 73)
//        {
//            this.theUS.setStatus("อายุมากกว่า 73 เดือน ข้อมูลนี้จะไม่ออกในรายงาน", UpdateStatus.WARNING);
//        }
        if((!theNutrition.nutrition_age.equals("")) && Integer.parseInt(theNutrition.nutrition_age) > 72)
        {
            jLabelAge.setVisible(false);
            integerTextFieldAge.setVisible(false);
            jLabelMonth.setVisible(false);
            jLabelHeadRim.setVisible(false);
            integerTextFieldHeadRim.setVisible(false);
            jLabelcms1.setVisible(false);
        }
        else
        {
            jLabelAge.setVisible(true);
            integerTextFieldAge.setVisible(true);
            jLabelMonth.setVisible(true);
            jLabelHeadRim.setVisible(true);
            integerTextFieldHeadRim.setVisible(true);
            jLabelcms1.setVisible(true);
        }
        integerTextFieldAge.setText(theNutrition.nutrition_age);
        integerTextFieldHeadRim.setText(theNutrition.nutrition_rim);
        integerTextFieldNtrWeight.setText(theNutrition.nutrition_weight);
        integerTextFieldNtrHigh.setText(theNutrition.nutrition_high);

        integerTextFieldNewTooth.setText(theNutrition.nutrition_newtooth);
        integerTextFieldBadTooth.setText(theNutrition.nutrition_badtooth);
        if(theNutrition.answer_id.equals(PcuAnswer.Zero()))
            jRadioButtonCleanNavel0.setSelected(true);
        else
            jRadioButtonCleanNavel1.setSelected(true); 
        doubleTextFieldBMI.setText(theNutrition.nutrition_bmi);
        jTextAreaNoteResult.setText(theNutrition.nutrition_result);
        jTextFieldRemark.setText(theNutrition.nutrition_notice);
        dateComboBoxSurvey.setText(Gutil.convertFieldDate(theNutrition.survey_date));
        jLabelSurveyDate.setSelected(!dateComboBoxSurvey.getText().equals(""));
        dateComboBoxSurvey.setEnabled(!dateComboBoxSurvey.getText().equals(""));
        jLabelVN.setText("");
        if(theVisit != null)
            jLabelVN.setText("VN:" + theVisit.vn);
        if(theVisit != null && visit_status.equals(VisitStatus.isInProcess()))
            setEnabled(true);
        dateComboBoxCheck.setText(DateUtil.convertFieldDate(theNutrition.modify_date_time));
        if(theNutrition.modify_date_time.length() >= 11)
            timeTextFieldCheck.setText(theNutrition.modify_date_time.substring(11));
    }
    private void deleteNutrition()
    {
        int row = jTableNutriList.getSelectedRow();
        if(row==-1)
        {
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการยกเลิก",UpdateStatus.WARNING);
            return;
        }
        if(!checkBeforDel()){
            return;
        }
        theHealthServiceControl.deleteNutrition(theNutrition);
        setNutritionV(pcuobject.vNutrition);
        this.setNutrition(null);
    }
    
    private void selectNutrition(int row)
    {
        this.setNutrition(theNutrition);
        if(row==-2)
        {
            /*user เป็นผู้เลือกเอง*/
            rowNutrition = jTableNutriList.getSelectedRow();            
        }
        else
        {
            /*user บันทึก จะ select record ที่เพิ่งเพิ่ม*/
            rowNutrition = row;            
        }        
        theNutrition = (Nutrition)vNutrition.get(rowNutrition); 
        integerTextFieldAge.setText(theNutrition.nutrition_age);
        integerTextFieldHeadRim.setText(theNutrition.nutrition_rim);
        integerTextFieldNtrWeight.setText(theNutrition.nutrition_weight);
        integerTextFieldNtrHigh.setText(theNutrition.nutrition_high);
        integerTextFieldNewTooth.setText(theNutrition.nutrition_newtooth);
        integerTextFieldBadTooth.setText(theNutrition.nutrition_badtooth);
        if(theNutrition.answer_id.equals(PcuAnswer.Zero()))
        {
            jRadioButtonCleanNavel0.setSelected(true);
        }
        else
        {
            jRadioButtonCleanNavel1.setSelected(true);
        }       
        ComboboxModel.setCodeComboBox(jComboBoxNutrition,theNutrition.nutrition_level_id); 
        doubleTextFieldBMI.setText(theNutrition.nutrition_bmi);
        jTextAreaNoteResult.setText(theNutrition.nutrition_result);
        jTextFieldRemark.setText(theNutrition.nutrition_notice);
        dateComboBoxSurvey.setText(Gutil.convertFieldDate(theNutrition.survey_date));
        jLabelSurveyDate.setSelected(!dateComboBoxSurvey.getText().equals(""));
        dateComboBoxSurvey.setEnabled(!dateComboBoxSurvey.getText().equals(""));
        this.jLabelVN.setText("");
        if(!theNutrition.visit_id.equals("")){
            String vn_id = this.theAllComboBoxControl.readVNbyVid(theNutrition.visit_id);
            this.jLabelVN.setText("VN:"+vn_id);
        }
        if(theVisit!=null && visit_status.equalsIgnoreCase(VisitStatus.isInProcess()))
        {   
            setEnabled(true);
        }

        this.dateComboBoxCheck.setText(DateUtil.convertFieldDate(theNutrition.modify_date_time));
        this.timeTextFieldCheck.setText(theNutrition.modify_date_time.substring(11));
    }


    /**
     *ตรวจสอบ Patient และ Family จาก PCUobject
     *@param -
     *@return boolean true=มีผู้ป่วยหรือประชากร false=ไม่มีผู้ป่วยและประชากร
     *@author kingland
     *@date 04/09/2549
     */
    private boolean checkPatientAndFamily()
    {   boolean result = true;
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
   public static int calculateIndexComboBoxNutrition(String bmi,Vector vNutritionType2)
   {
       int index =0;
       float fbmi = Float.parseFloat(bmi);
       float fmax = 0;
       float fmin = 0;
       for(int i=0,size=vNutritionType2.size(); i<size; i++)
       {
           NutritionType nutritiontype = (NutritionType)vNutritionType2.get(i);
           fmax = Float.parseFloat(nutritiontype.max); 
           fmin = Float.parseFloat(nutritiontype.min); 
           if(fmax>=fbmi && fbmi>=fmin)
           {
               index = i;
               break;
           }
       }
       return index;
   }
    /**
     * เช็ควันที่สำรวจว่าเป็นวันในอนาคตหรือไม่
     * @param -
     * @return void
     * @author jao
     * @modify kingland
     * @date 01/09/2549
     */
    private void checkDateSurvey()
    {   if(!dateComboBoxSurvey.getText().equals("") 
            && dateComboBoxSurvey.getText().length()==10 
            && com.pcu.utility.DateUtil.countDay(dateComboBoxSurvey.getText(),theHosManage.theHosControl.theConnectionInf) == -1 
            && com.pcu.utility.DateUtil.isToday(com.pcu.utility.DateUtil.getDateFromText(dateComboBoxSurvey.getText()),theHosManage.theHosControl.theConnectionInf)==false)  
        {   if(checksurvey == false)
             {  // ไม่สามารถกรอกวันที่เป็นวันในอนาคตได้
                theUS.setStatus(GutilPCU.getTextBundle("NoDateFuture") , UpdateStatus.WARNING);
                checksurvey = true;
             }
        }  
    }

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
    /**
     *เซตสถานะ
     *@param massege = ข้อความที่ต้องการแสดง status = สถาน
     *@return void
     *@author kingland
     *@date 01/09/2549
     */
}