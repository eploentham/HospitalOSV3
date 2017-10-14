/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelNewAnc.java
 *
 * Created on 28 ม.ค. 2554, 10:53:10
 */

package com.pcu.gui.panelpcu;

import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.Gutil;
import com.hosv3.control.HosControl;
import com.pcu.control.HosManage;
import com.pcu.object.AncDetailPcu;
import com.pcu.object.AncPcu;
import com.pcu.object.PcuAnswer;
import com.pcu.object.Pregnancy;
import com.pcu.utility.DateUtil;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.JDialog;

/**
 *
 * @author LionHeart
 */
public class PanelNewAnc extends javax.swing.JPanel {
    HosControl theHC;
    private JDialog theJD;
    Pregnancy thePregnancy;
    AncPcu theAncPcu;
    HosManage theHM;
    AncDetailPcu theAncDetailPcu;
    public PanelNewAnc() {
        initComponents();
    }
    public void setControl(HosManage hm)
    {
        theHM = hm;
        theHC = theHM.theHC;
        Vector vPostpartumStatusResult = theHM.theHosControl.theAllComboBoxControl.listPostpartumStatusResult();
        ComboboxModel.initComboBox(jComboBoxVdrl, vPostpartumStatusResult);
        ComboboxModel.initComboBox(jComboBoxHb, vPostpartumStatusResult);
        ComboboxModel.initComboBox(jComboBoxHiv, vPostpartumStatusResult);
        ComboboxModel.initComboBox(jComboBoxThalassemia, vPostpartumStatusResult);
        ComboboxModel.initComboBox(jComboBoxAncRes, vPostpartumStatusResult);
    }
    private void calPregnant() {
        String dateLastMen = dateComboBoxFirstDateHaveLastMen.getText();
//        System.out.println("dateLastMen = " + dateLastMen);
        if (!dateLastMen.equals("")) {
            dateComboBoxSetDateGiveBirth.setText(com.pcu.utility.DateUtil.calPregnant(dateLastMen));
        }
//        System.out.println("dateComboBoxSetDateGiveBirth = " + dateComboBoxSetDateGiveBirth.getText());
        String date_ref = theHM.thePO.getCurrentDateTime();
//        if (!thePregnancy.survey_date.equals("")) {
//            date_ref = thePregnancy.survey_date;
//        }
//        if (jLabelSurveyDate.isSelected()) {
//            date_ref = dateComboBoxSurvey.getText();
//        }else if(!dateComboBoxCheck1.getText().equals("")){
//            date_ref = dateComboBoxCheck1.getText() + "," + timeTextFieldCheck1.getText();
//        }
//henbe comment 100253 kong เพิ่มทำไมรู้มั้ยจะแก้ก็ต้องแก้ทั้งระบบไม่ใช่มาทำบางหน้าอย่างนี้
                //แล้วรายงานต้องแก้ด้วยรู้หรือเปล่า แล้วบรรทัดร่นได้ยังไง
//        System.out.println("date_ref = " + date_ref);
//        System.out.println("dateComboBoxFirstDateHaveLastMen = " + dateComboBoxFirstDateHaveLastMen.getText());
        String age_week = DateUtil.countWeekS(dateComboBoxFirstDateHaveLastMen.getText(), date_ref);
//        jLabelAgeWeek.setText(age_week);
    }
    public Pregnancy getPregnancy()
    {
        if(thePregnancy == null)
        {
            thePregnancy = new Pregnancy();
        }
        thePregnancy.patient_id = this.theHC.theHO.thePatient.getObjectId();
        thePregnancy.family_id = this.theHC.theHO.theFamily.getObjectId();
        thePregnancy.pregnancy_hn = this.theHC.theHO.thePatient.hn;
        thePregnancy.pregnancy_vn = this.theHC.theHO.theVisit.vn;
        thePregnancy.office_id = this.theHC.theHO.theSite.off_id;
        thePregnancy.pregnancy_staff_record = this.theHC.theHO.theEmployee.getObjectId();
        thePregnancy.record_date_time = this.theHC.theHO.date_time;
        thePregnancy.modify_date_time = this.theHC.theHO.date_time;
        thePregnancy.pregnancy_gravida_number = jTextFieldGravida.getText();
        thePregnancy.pregnancy_p = jTextFieldP.getText();
        thePregnancy.pregnancy_a = jTextFieldA.getText();
        thePregnancy.pregnancy_l = jTextFieldL.getText();
        thePregnancy.pregnancy_menses_issue_date = dateComboBoxFirstDateHaveLastMen.getText();
        thePregnancy.pregnancy_menses_expire_date = dateComboBoxSetDateGiveBirth.getText();
        thePregnancy.active = "1";
        return thePregnancy;
    }
    public AncPcu getAncPcu()
    {
        if(theAncPcu == null)
            theAncPcu = new AncPcu();
        theAncPcu.anc_hct_date = dateComboBoxHctDate.getText();
        theAncPcu.anc_hct_result = jTextFieldHctLevel.getText();
        theAncPcu.anc_vdrl = Gutil.getGuiData(jComboBoxVdrl);
        theAncPcu.anc_hb = Gutil.getGuiData(jComboBoxHb);
        theAncPcu.anc_hiv = Gutil.getGuiData(jComboBoxHiv);
        theAncPcu.anc_ancres = Gutil.getGuiData(jComboBoxAncRes);
        theAncPcu.anc_thalassemia = Gutil.getGuiData(jComboBoxThalassemia);
        if(jRadioButtonDentalNo.isSelected())
            theAncPcu.anc_dental_exam = PcuAnswer.Zero();
        else if(jRadioButtonDentalYes.isSelected())
            theAncPcu.anc_dental_exam = PcuAnswer.One();
        if(jRadioButtonGumNo.isSelected())
            theAncPcu.anc_dental_gum = PcuAnswer.Zero();
        else if(jRadioButtonGumYes.isSelected())
            theAncPcu.anc_dental_gum = PcuAnswer.One();
        if(jRadioButtonTartarNo.isSelected())
            theAncPcu.anc_dental_tartar = PcuAnswer.Zero();
        else if(jRadioButtonTartarYes.isSelected())
            theAncPcu.anc_dental_tartar = PcuAnswer.One();
        theAncPcu.anc_dental_caries = jTextFieldRemoveTooth.getText();
        theAncPcu.active = "1";
        theAncPcu.no = "1";
        return theAncPcu;
    }
    public AncDetailPcu getAncDetailPcu()
    {
        if(theAncDetailPcu == null)
            theAncDetailPcu = new AncDetailPcu();
        return theAncDetailPcu;
    }
    public void showDialog()
    {
        if(theJD==null)
            theJD = new JDialog(theHC.theUS.getJFrame());
        theJD.add(this);
        theJD.setSize(486, 309);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, (screenSize.height - theJD.getSize().height) / 2);
        theJD.setTitle("ข้อมูลการคลอด");
        theJD.setModal(true);
        theJD.setVisible(true);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jTextFieldGravida = new com.pcu.utility.IntegerTextField();
        jTextFieldP = new com.pcu.utility.IntegerTextField();
        jTextFieldL = new com.pcu.utility.IntegerTextField();
        jTextFieldA = new com.pcu.utility.IntegerTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dateComboBoxFirstDateHaveLastMen = new com.hospital_os.utility.DateComboBox();
        dateComboBoxSetDateGiveBirth = new com.hospital_os.utility.DateComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dateComboBoxHctDate = new com.hospital_os.utility.DateComboBox();
        jComboBoxThalassemia = new javax.swing.JComboBox();
        jComboBoxHb = new javax.swing.JComboBox();
        jComboBoxHiv = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldHctLevel = new javax.swing.JTextField();
        jComboBoxAncRes = new javax.swing.JComboBox();
        jComboBoxVdrl = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jRadioButtonDentalNo = new javax.swing.JRadioButton();
        jRadioButtonDentalYes = new javax.swing.JRadioButton();
        jRadioButtonTartarNo = new javax.swing.JRadioButton();
        jRadioButtonTartarYes = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jRadioButtonGumNo = new javax.swing.JRadioButton();
        jRadioButtonGumYes = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldRemoveTooth = new com.pcu.utility.IntegerTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel22.setLayout(new java.awt.GridBagLayout());

        jLabel29.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jPanel22.add(jLabel29, gridBagConstraints);

        jLabel35.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jPanel22.add(jLabel35, gridBagConstraints);

        jLabel41.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jPanel22.add(jLabel41, gridBagConstraints);

        jTextFieldGravida.setColumns(2);
        jTextFieldGravida.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldGravida.setToolTipText("จำนวนการตั้งครรภ์ (ครรภ์ที่)");
        jTextFieldGravida.setMinimumSize(new java.awt.Dimension(30, 21));
        jTextFieldGravida.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldGravidaFocusLost(evt);
            }
        });
        jTextFieldGravida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldGravidaKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel22.add(jTextFieldGravida, gridBagConstraints);

        jTextFieldP.setColumns(2);
        jTextFieldP.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldP.setToolTipText("จำนวนการคลอด");
        jTextFieldP.setMinimumSize(new java.awt.Dimension(30, 21));
        jTextFieldP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldPFocusLost(evt);
            }
        });
        jTextFieldP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel22.add(jTextFieldP, gridBagConstraints);

        jTextFieldL.setColumns(2);
        jTextFieldL.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldL.setToolTipText("จำนวนบุตรที่มีชีวิตอยู่");
        jTextFieldL.setMinimumSize(new java.awt.Dimension(30, 21));
        jTextFieldL.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldLFocusLost(evt);
            }
        });
        jTextFieldL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldLKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel22.add(jTextFieldL, gridBagConstraints);

        jTextFieldA.setColumns(2);
        jTextFieldA.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldA.setToolTipText("จำนวนการแท้ง");
        jTextFieldA.setMinimumSize(new java.awt.Dimension(30, 21));
        jTextFieldA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldAFocusLost(evt);
            }
        });
        jTextFieldA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldAKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldAKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        jPanel22.add(jTextFieldA, gridBagConstraints);

        jLabel1.setText("G-P-A-L");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel22.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jPanel22, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        add(jPanel1, gridBagConstraints);

        jLabel2.setText("วันแรกของการมีประจำเดือนครั้งสุดท้าย");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        add(jLabel2, gridBagConstraints);

        jLabel3.setText("วันที่กำหนดคลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        add(jLabel3, gridBagConstraints);

        dateComboBoxFirstDateHaveLastMen.setToolTipText("วันแรกของประจำเดือนครั้งสุดท้าย");
        dateComboBoxFirstDateHaveLastMen.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxFirstDateHaveLastMen.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxFirstDateHaveLastMen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxFirstDateHaveLastMenActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        add(dateComboBoxFirstDateHaveLastMen, gridBagConstraints);

        dateComboBoxSetDateGiveBirth.setToolTipText("วันแรกของประจำเดือนครั้งสุดท้าย");
        dateComboBoxSetDateGiveBirth.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxSetDateGiveBirth.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxSetDateGiveBirth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxSetDateGiveBirthActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        add(dateComboBoxSetDateGiveBirth, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("ข้อมูลพื้นฐาน"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("ผล VDRL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jLabel4, gridBagConstraints);

        jLabel5.setText("ผล HB");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jLabel5, gridBagConstraints);

        jLabel6.setText("ผล HIV");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jLabel6, gridBagConstraints);

        jLabel7.setText("วันที่ตรวจ HCT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jLabel7, gridBagConstraints);

        dateComboBoxHctDate.setToolTipText("วันแรกของประจำเดือนครั้งสุดท้าย");
        dateComboBoxHctDate.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxHctDate.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxHctDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxHctDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel2.add(dateComboBoxHctDate, gridBagConstraints);

        jComboBoxThalassemia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel2.add(jComboBoxThalassemia, gridBagConstraints);

        jComboBoxHb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel2.add(jComboBoxHb, gridBagConstraints);

        jComboBoxHiv.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel2.add(jComboBoxHiv, gridBagConstraints);

        jLabel8.setText("ระดับ HCT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel2.add(jLabel8, gridBagConstraints);

        jLabel9.setText("ผลตรวจ Thalssemia ในแม่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel2.add(jLabel9, gridBagConstraints);

        jLabel10.setText("สรุปผลการตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel2.add(jLabel10, gridBagConstraints);

        jTextFieldHctLevel.setMinimumSize(new java.awt.Dimension(100, 20));
        jTextFieldHctLevel.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel2.add(jTextFieldHctLevel, gridBagConstraints);

        jComboBoxAncRes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel2.add(jComboBoxAncRes, gridBagConstraints);

        jComboBoxVdrl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel2.add(jComboBoxVdrl, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel11.setText("ตรวจสุขภาพฟัน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(jLabel11, gridBagConstraints);

        jLabel12.setText("มีหินน้ำลาย");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jLabel12, gridBagConstraints);

        buttonGroup1.add(jRadioButtonDentalNo);
        jRadioButtonDentalNo.setText("ไม่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel3.add(jRadioButtonDentalNo, gridBagConstraints);

        buttonGroup1.add(jRadioButtonDentalYes);
        jRadioButtonDentalYes.setText("ใช่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel3.add(jRadioButtonDentalYes, gridBagConstraints);

        buttonGroup2.add(jRadioButtonTartarNo);
        jRadioButtonTartarNo.setText("ไม่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel3.add(jRadioButtonTartarNo, gridBagConstraints);

        buttonGroup2.add(jRadioButtonTartarYes);
        jRadioButtonTartarYes.setText("ใช่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel3.add(jRadioButtonTartarYes, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel13.setText("เหงือกอักเสบ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(jLabel13, gridBagConstraints);

        buttonGroup3.add(jRadioButtonGumNo);
        jRadioButtonGumNo.setText("ไม่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel4.add(jRadioButtonGumNo, gridBagConstraints);

        buttonGroup3.add(jRadioButtonGumYes);
        jRadioButtonGumYes.setText("ใช่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel4.add(jRadioButtonGumYes, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel3.add(jPanel4, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel14.setText("ฟันผุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jLabel14, gridBagConstraints);

        jTextFieldRemoveTooth.setColumns(2);
        jTextFieldRemoveTooth.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldRemoveTooth.setToolTipText("จำนวนการแท้ง");
        jTextFieldRemoveTooth.setMinimumSize(new java.awt.Dimension(30, 21));
        jTextFieldRemoveTooth.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldRemoveToothFocusLost(evt);
            }
        });
        jTextFieldRemoveTooth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldRemoveToothKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldRemoveToothKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel5.add(jTextFieldRemoveTooth, gridBagConstraints);

        jLabel15.setText("ซี่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel5.add(jLabel15, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 11.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel3.add(jPanel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 11.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jPanel3, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("บันทึก");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(jButton1, gridBagConstraints);

        jButton2.setText("ยกเลิก");
        jPanel6.add(jButton2, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        jPanel2.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        add(jPanel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldGravidaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldGravidaFocusLost

}//GEN-LAST:event_jTextFieldGravidaFocusLost

    private void jTextFieldGravidaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldGravidaKeyReleased
        String txt = jTextFieldGravida.getText();
        if(txt.length()>=1)
            jTextFieldP.requestFocus();
}//GEN-LAST:event_jTextFieldGravidaKeyReleased

    private void jTextFieldPFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPFocusLost
        // TODO add your handling code here:
}//GEN-LAST:event_jTextFieldPFocusLost

    private void jTextFieldPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPKeyReleased
        String txt = jTextFieldP.getText();
        if(txt.length()>=1)
            jTextFieldA.requestFocus();
}//GEN-LAST:event_jTextFieldPKeyReleased

    private void jTextFieldLFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldLFocusLost
        // TODO add your handling code here:
}//GEN-LAST:event_jTextFieldLFocusLost

    private void jTextFieldLKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldLKeyReleased

}//GEN-LAST:event_jTextFieldLKeyReleased

    private void jTextFieldAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldAFocusLost
        
}//GEN-LAST:event_jTextFieldAFocusLost

    private void jTextFieldAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAKeyReleased
        String txt = jTextFieldA.getText();
        if(txt.length()>=1)
            jTextFieldL.requestFocus();
}//GEN-LAST:event_jTextFieldAKeyReleased

    private void jTextFieldAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAKeyTyped
        // TODO add your handling code here:
}//GEN-LAST:event_jTextFieldAKeyTyped

    private void dateComboBoxFirstDateHaveLastMenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxFirstDateHaveLastMenActionPerformed
        calPregnant();
}//GEN-LAST:event_dateComboBoxFirstDateHaveLastMenActionPerformed

    private void dateComboBoxSetDateGiveBirthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxSetDateGiveBirthActionPerformed
        
}//GEN-LAST:event_dateComboBoxSetDateGiveBirthActionPerformed

    private void dateComboBoxHctDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxHctDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateComboBoxHctDateActionPerformed

    private void jTextFieldRemoveToothFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldRemoveToothFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRemoveToothFocusLost

    private void jTextFieldRemoveToothKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldRemoveToothKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRemoveToothKeyReleased

    private void jTextFieldRemoveToothKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldRemoveToothKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRemoveToothKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.getPregnancy();
        this.getAncPcu();
        this.getAncDetailPcu();
        int result = theHM.theHosControl.theHealthServiceControl.savePregnancyN(thePregnancy);
        theAncPcu.pregnancy_id = thePregnancy.getObjectId();
        theAncDetailPcu.pregnancy_id = thePregnancy.getObjectId();
        result = theHM.theHosControl.theHealthServiceControl.saveAnc(theAncPcu, theAncDetailPcu);
        theJD.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private com.hospital_os.utility.DateComboBox dateComboBoxFirstDateHaveLastMen;
    private com.hospital_os.utility.DateComboBox dateComboBoxHctDate;
    private com.hospital_os.utility.DateComboBox dateComboBoxSetDateGiveBirth;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBoxAncRes;
    private javax.swing.JComboBox jComboBoxHb;
    private javax.swing.JComboBox jComboBoxHiv;
    private javax.swing.JComboBox jComboBoxThalassemia;
    private javax.swing.JComboBox jComboBoxVdrl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButtonDentalNo;
    private javax.swing.JRadioButton jRadioButtonDentalYes;
    private javax.swing.JRadioButton jRadioButtonGumNo;
    private javax.swing.JRadioButton jRadioButtonGumYes;
    private javax.swing.JRadioButton jRadioButtonTartarNo;
    private javax.swing.JRadioButton jRadioButtonTartarYes;
    private com.pcu.utility.IntegerTextField jTextFieldA;
    private com.pcu.utility.IntegerTextField jTextFieldGravida;
    private javax.swing.JTextField jTextFieldHctLevel;
    private com.pcu.utility.IntegerTextField jTextFieldL;
    private com.pcu.utility.IntegerTextField jTextFieldP;
    private com.pcu.utility.IntegerTextField jTextFieldRemoveTooth;
    // End of variables declaration//GEN-END:variables

}
