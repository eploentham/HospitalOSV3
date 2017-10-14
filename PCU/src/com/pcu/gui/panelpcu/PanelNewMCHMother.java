/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelNewMCHMother.java
 *
 * Created on 28 ม.ค. 2554, 11:25:36
 */

package com.pcu.gui.panelpcu;

import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.Gutil;
import com.hosv3.control.HosControl;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.control.HosManage;
import com.pcu.gui.dialog.HosDialog;
import com.pcu.object.AfterMchMother;
import com.pcu.object.AncPcu;
import com.pcu.object.BornMch;
import com.pcu.object.PcuAnswer;
import com.pcu.object.Pregnancy;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.JDialog;

/**
 *
 * @author LionHeart
 */
public class PanelNewMCHMother extends javax.swing.JPanel {
    private HosControl theHC;
    private UpdateStatus theUS;
    private HosDialog theHD;
    private HosManage theHM;
    private JDialog theJD;
    private Pregnancy thePregnancy;
    private BornMch theBornMch;
    private AncPcu theAncPcu;
    AfterMchMother theAfterMchMother;
    boolean b_left;
    boolean b_right;
    public PanelNewMCHMother() {
        initComponents();
    }

    public void showDialog()
    {
        if(theJD==null)
            theJD = new JDialog(theHC.theUS.getJFrame());
        theJD.add(this);
        theJD.setSize(750, 550);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, (screenSize.height - theJD.getSize().height) / 2);
        theJD.setTitle("ดูแลแม่หลังคลอด");
        theJD.setModal(true);
        theJD.setVisible(true);
    }
    public void setControl(HosManage hm,HosDialog hd,UpdateStatus us)
    {
        theHC = hm.theHC;
        theUS = us;
        theHD = hd;
        theHM = hm;
        Vector vPostpartumStatusResult = theHM.theHosControl.theAllComboBoxControl.listPostpartumStatusResult();
        ComboboxModel.initComboBox(jComboBoxVdrl, vPostpartumStatusResult);
        ComboboxModel.initComboBox(jComboBoxHb, vPostpartumStatusResult);
        ComboboxModel.initComboBox(jComboBoxHiv, vPostpartumStatusResult);
        ComboboxModel.initComboBox(jComboBoxThalassemia, vPostpartumStatusResult);
        ComboboxModel.initComboBox(jComboBoxMethodGiveBirth, this.theHM.theHosControl.theAllComboBoxControl.listBirthMethod());
        ComboboxModel.initComboBox(jComboBoxBirthPlace, this.theHM.theHosControl.theAllComboBoxControl.listPostpartumBirthPlace());
        ComboboxModel.initComboBox(jComboBoxBDoctor, this.theHM.theHosControl.theAllComboBoxControl.listBDoctor());
        ComboboxModel.initComboBox(jComboBoxResultICD10, this.theHM.theHosControl.theAllComboBoxControl.comboBoxViewICD10Pregnant());
//        ComboboxModel.initComboBox(jComboBoxAncRes, vPostpartumStatusResult);
    }
    public void setEnable1(boolean b)
    {
        b_left = b;
        integerTextFieldHosSubCode.setEnabled(b);
        jButtonHosSub.setEnabled(b);
        dateComboBoxHctDate.setEnabled(b);
        jComboBoxThalassemia.setEnabled(b);
        jRadioButtonDental.setEnabled(b);
        jRadioButtonGum.setEnabled(b);
        jRadioButtonTartar.setEnabled(b);
        jComboBoxResultICD10.setEnabled(b);
        jComboBoxBirthPlace.setEnabled(b);
        jComboBoxBDoctor.setEnabled(b);
        dateComboBoxLastMenDate.setEnabled(b);
        dateComboBoxBirthDate.setEnabled(b);
        jComboBoxMethodGiveBirth.setEnabled(b);
        jComboBoxVdrl.setEnabled(b);
        jComboBoxHb.setEnabled(b);
        jComboBoxHiv.setEnabled(b);
        jTextField1.setEnabled(b);
        jTextFieldHctLevel.setEnabled(b);
        jTextFieldL.setEnabled(b);
        jTextFieldP.setEnabled(b);
        jTextFieldA.setEnabled(b);
        jTextFieldGravida.setEnabled(b);
    }
    public void setEnable2(boolean b)
    {
        b_right = b;
        integerTextFieldHosSubCodeHos.setEnabled(b);
        jButton5.setEnabled(b);
        jButtonHosSub1.setEnabled(b);
        jComboBoxBPlace.setEnabled(b);
        jComboBoxSugarLevel.setEnabled(b);
        dateComboBoxSurveyDate.setEnabled(b);
        jComboBoxAlbulmin.setEnabled(b);
        jComboBoxResult.setEnabled(b);
        jComboBoxSew.setEnabled(b);
        jRadioButtonCream0.setEnabled(b);
        jRadioButtonMen1.setEnabled(b);
        jRadioButtonCream1.setEnabled(b);
        jRadioButtonUterus0.setEnabled(b);
        jRadioButtonUterus1.setEnabled(b);
        jRadioButtonMilk0.setEnabled(b);
        jRadioButtonMilk1.setEnabled(b);
        jRadioButtonLochia0.setEnabled(b);
        jRadioButtonLochia1.setEnabled(b);
        jRadioButtonMen0.setEnabled(b);
        jScrollPane1.setEnabled(b);
        jTextAreaSymptomNotice.setEnabled(b);
        jTextFieldCareNo.setEnabled(b);
    }
    public void setLeftPanel(Pregnancy pregnancy,BornMch bornMch,AncPcu ancPcu)
    {
        thePregnancy = pregnancy;
        theBornMch = bornMch;
        theAncPcu = ancPcu;
        if(thePregnancy == null)
        {
            thePregnancy = new Pregnancy();
        }
        if(theBornMch == null)
        {
            theBornMch = new BornMch();
        }
        if(theAncPcu == null)
        {
            theAncPcu = new AncPcu();
        }
        jTextFieldGravida.setText(thePregnancy.pregnancy_gravida_number);
        jTextFieldP.setText(thePregnancy.pregnancy_p);
        jTextFieldA.setText(thePregnancy.pregnancy_a);
        jTextFieldL.setText(thePregnancy.pregnancy_l);
        dateComboBoxLastMenDate.setText(Gutil.convertFieldDate(thePregnancy.pregnancy_menses_issue_date));
        dateComboBoxBirthDate.setText(Gutil.convertFieldDate(thePregnancy.pregnancy_menses_expire_date));
        dateComboBoxHctDate.setText(Gutil.convertFieldDate(theAncPcu.anc_hct_date));
        Gutil.setGuiData(jTextFieldHctLevel,theAncPcu.anc_hct_result);
        Gutil.setGuiData(jComboBoxVdrl,theAncPcu.anc_vdrl);
        Gutil.setGuiData(jComboBoxHb,theAncPcu.anc_hb);
        Gutil.setGuiData(jComboBoxHiv,theAncPcu.anc_hiv);
        Gutil.setGuiData(jComboBoxThalassemia,theAncPcu.anc_thalassemia);
        if(theAncPcu.anc_dental_exam.equals(PcuAnswer.Zero()))
        {
            jRadioButtonDental.setSelectedIndex(0);
        }
        else if(theAncPcu.anc_dental_exam.equals(PcuAnswer.One()))
        {
            jRadioButtonDental.setSelectedIndex(1);
        }
        if(theAncPcu.anc_dental_gum.equals(PcuAnswer.Zero()))
        {
            jRadioButtonGum.setSelectedIndex(0);
        }
        else if(theAncPcu.anc_dental_gum.equals(PcuAnswer.One()))
        {
            jRadioButtonGum.setSelectedIndex(1);
        }
        if(theAncPcu.anc_dental_tartar.equals(PcuAnswer.Zero()))
        {
            jRadioButtonTartar.setSelectedIndex(0);
        }
        else if(theAncPcu.anc_dental_tartar.equals(PcuAnswer.One()))
        {
            jRadioButtonTartar.setSelectedIndex(1);
        }
//        theAncPcu.anc_dental_caries = jTextFieldRemoveTooth.getText();
        theAncPcu.active = "1";
        theAncPcu.no = "1";
    }
    public void getLeftPanel()
    {
        if(thePregnancy == null)
        {
            thePregnancy = new Pregnancy();
        }
        if(theBornMch == null)
        {
            theBornMch = new BornMch();
        }
        if(theAncPcu == null)
        {
            theAncPcu = new AncPcu();
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
        thePregnancy.pregnancy_menses_issue_date = dateComboBoxLastMenDate.getText();
        thePregnancy.pregnancy_menses_expire_date = dateComboBoxBirthDate.getText();
        thePregnancy.active = "1";
        theAncPcu.anc_hct_date = dateComboBoxHctDate.getText();
        theAncPcu.anc_hct_result = jTextFieldHctLevel.getText();
        theAncPcu.anc_vdrl = Gutil.getGuiData(jComboBoxVdrl);
        theAncPcu.anc_hb = Gutil.getGuiData(jComboBoxHb);
        theAncPcu.anc_hiv = Gutil.getGuiData(jComboBoxHiv);
//        theAncPcu.anc_ancres = Gutil.getGuiData(jComboBoxAncRes);
        theAncPcu.anc_thalassemia = Gutil.getGuiData(jComboBoxThalassemia);
        if(jRadioButtonDental.getSelectedIndex()==0)
            theAncPcu.anc_dental_exam = PcuAnswer.Zero();
        else if(jRadioButtonDental.getSelectedIndex()==1)
            theAncPcu.anc_dental_exam = PcuAnswer.One();
        if(jRadioButtonGum.getSelectedIndex()==0)
            theAncPcu.anc_dental_gum = PcuAnswer.Zero();
        else if(jRadioButtonGum.getSelectedIndex()==1)
            theAncPcu.anc_dental_gum = PcuAnswer.One();
        if(jRadioButtonTartar.getSelectedIndex()==0)
            theAncPcu.anc_dental_tartar = PcuAnswer.Zero();
        else if(jRadioButtonTartar.getSelectedIndex()==1)
            theAncPcu.anc_dental_tartar = PcuAnswer.One();
//        theAncPcu.anc_dental_caries = jTextFieldRemoveTooth.getText();
        theAncPcu.active = "1";
        theAncPcu.no = "1";
    }
    public void setRightPanel(AfterMchMother afterMchMother)
    {
        theAfterMchMother = afterMchMother;
        if(theAfterMchMother == null)
        {
            theAfterMchMother = new AfterMchMother();
        }
        jTextFieldCareNo.setText(theAfterMchMother.pregnantnumber);
        dateComboBoxSurveyDate.setText(theAfterMchMother.survey_date);
        Gutil.setGuiData(jComboBoxBPlace,theAfterMchMother.place);
        integerTextFieldHosSubCodeHos.setText(theAfterMchMother.place_hos);

        if(theAfterMchMother.menses.equals("0"))
            jRadioButtonMen0.setSelected(true);
        else if(theAfterMchMother.menses.equals("1"))
            jRadioButtonMen1.setSelected(true);
        if(theAfterMchMother.cream.equals("0"))
            jRadioButtonCream0.setSelected(true);
        else if(theAfterMchMother.cream.equals("1"))
            jRadioButtonCream1.setSelected(true);
        if(theAfterMchMother.uterus_level.equals("0"))
            jRadioButtonUterus0.setSelected(true);
        else if(theAfterMchMother.uterus_level.equals("1"))
            jRadioButtonUterus1.setSelected(true);
        if(theAfterMchMother.uterus_level.equals("0"))
            jRadioButtonMilk0.setSelected(true);
        else if(theAfterMchMother.uterus_level.equals("1"))
            jRadioButtonMilk1.setSelected(true);
        if(theAfterMchMother.lochia.equals("0"))
            jRadioButtonLochia0.setSelected(true);
        else if(theAfterMchMother.lochia.equals("1"))
            jRadioButtonLochia1.setSelected(true);

        Gutil.setGuiData(jComboBoxSew,theAfterMchMother.sew);
        Gutil.setGuiData(jComboBoxSugarLevel,theAfterMchMother.sugar_level);
        Gutil.setGuiData(jComboBoxAlbulmin,theAfterMchMother.albumin);
        if(!theAfterMchMother.result_verify.equals(""))
            jComboBoxResult.setSelectedIndex(Integer.parseInt(theAfterMchMother.result_verify));
        jTextAreaSymptomNotice.setText(theAfterMchMother.symptom_notice);
    }
    public void getRightPanel()
    {
        if(theAfterMchMother == null)
        {
            theAfterMchMother = new AfterMchMother();
        }
        theAfterMchMother.pregnantnumber = jTextFieldCareNo.getText();
        theAfterMchMother.survey_date = dateComboBoxSurveyDate.getText();
        theAfterMchMother.place = Gutil.getGuiData(jComboBoxBPlace);
        theAfterMchMother.place_hos = integerTextFieldHosSubCodeHos.getText();
        if(jRadioButtonMen0.isSelected())
            theAfterMchMother.menses = "0";
        else if(jRadioButtonMen1.isSelected())
            theAfterMchMother.menses = "1";
        if(jRadioButtonCream0.isSelected())
            theAfterMchMother.cream = "0";
        else if(jRadioButtonCream1.isSelected())
            theAfterMchMother.cream = "1";
        if(jRadioButtonUterus0.isSelected())
            theAfterMchMother.uterus_level = "0";
        else if(jRadioButtonUterus1.isSelected())
            theAfterMchMother.uterus_level = "1";
        if(jRadioButtonMilk0.isSelected())
            theAfterMchMother.uterus_level = "0";
        else if(jRadioButtonMilk1.isSelected())
            theAfterMchMother.uterus_level = "1";
        if(jRadioButtonLochia0.isSelected())
            theAfterMchMother.lochia = "0";
        else if(jRadioButtonLochia1.isSelected())
            theAfterMchMother.lochia = "1";
        theAfterMchMother.sew = Gutil.getGuiData(jComboBoxSew);
        theAfterMchMother.sugar_level = Gutil.getGuiData(jComboBoxSugarLevel);
        theAfterMchMother.albumin = Gutil.getGuiData(jComboBoxAlbulmin);
        theAfterMchMother.result_verify = String.valueOf(jComboBoxResult.getSelectedIndex());
        theAfterMchMother.symptom_notice = jTextAreaSymptomNotice.getText();
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
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxBirthPlace = new javax.swing.JComboBox();
        jComboBoxBDoctor = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dateComboBoxLastMenDate = new com.hospital_os.utility.DateComboBox();
        dateComboBoxBirthDate = new com.hospital_os.utility.DateComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        integerTextFieldHosSubCode = new javax.swing.JTextField();
        jTextFieldHosSub = new javax.swing.JTextField();
        jButtonHosSub = new javax.swing.JButton();
        jComboBoxMethodGiveBirth = new javax.swing.JComboBox();
        jComboBoxVdrl = new javax.swing.JComboBox();
        jComboBoxHb = new javax.swing.JComboBox();
        jComboBoxHiv = new javax.swing.JComboBox();
        jComboBoxThalassemia = new javax.swing.JComboBox();
        jRadioButtonDental = new javax.swing.JComboBox();
        jRadioButtonGum = new javax.swing.JComboBox();
        jRadioButtonTartar = new javax.swing.JComboBox();
        jComboBoxResultICD10 = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldHctLevel = new javax.swing.JTextField();
        dateComboBoxHctDate = new com.hospital_os.utility.DateComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jTextFieldCareNo = new com.pcu.utility.IntegerTextField();
        jComboBoxBPlace = new javax.swing.JComboBox();
        jComboBoxSugarLevel = new javax.swing.JComboBox();
        jPanel9 = new javax.swing.JPanel();
        integerTextFieldHosSubCodeHos = new javax.swing.JTextField();
        jTextFieldHosSub1 = new javax.swing.JTextField();
        jButtonHosSub1 = new javax.swing.JButton();
        jRadioButtonCream0 = new javax.swing.JRadioButton();
        jRadioButtonCream1 = new javax.swing.JRadioButton();
        jRadioButtonUterus0 = new javax.swing.JRadioButton();
        jRadioButtonUterus1 = new javax.swing.JRadioButton();
        jRadioButtonMilk0 = new javax.swing.JRadioButton();
        jRadioButtonMilk1 = new javax.swing.JRadioButton();
        jRadioButtonLochia0 = new javax.swing.JRadioButton();
        jRadioButtonLochia1 = new javax.swing.JRadioButton();
        jRadioButtonMen0 = new javax.swing.JRadioButton();
        jRadioButtonMen1 = new javax.swing.JRadioButton();
        jComboBoxAlbulmin = new javax.swing.JComboBox();
        jComboBoxSew = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jComboBoxResult = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaSymptomNotice = new javax.swing.JTextArea();
        dateComboBoxSurveyDate = new com.hospital_os.utility.DateComboBox();
        jPanel6 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("รายละเอียดการตั้งครรภ์และการคลอด"));
        jPanel1.setMinimumSize(new java.awt.Dimension(300, 341));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 358));
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jPanel22, gridBagConstraints);

        jLabel2.setText("วิธีการคลอด/สิ้นสุดการตั้งครรภ์");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("LMP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(jLabel3, gridBagConstraints);

        jLabel4.setText("ประเภทผู้ทำคลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel3.add(jLabel4, gridBagConstraints);

        jComboBoxBirthPlace.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel3.add(jComboBoxBirthPlace, gridBagConstraints);

        jComboBoxBDoctor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel3.add(jComboBoxBDoctor, gridBagConstraints);

        jLabel5.setText("สถานที่คลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jLabel5, gridBagConstraints);

        jLabel6.setText("EDC");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel3.add(jLabel6, gridBagConstraints);

        dateComboBoxLastMenDate.setToolTipText("วันแรกของประจำเดือนครั้งสุดท้าย");
        dateComboBoxLastMenDate.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxLastMenDate.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxLastMenDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxLastMenDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel3.add(dateComboBoxLastMenDate, gridBagConstraints);

        dateComboBoxBirthDate.setToolTipText("วันกำหนดคลอด");
        dateComboBoxBirthDate.setMinimumSize(new java.awt.Dimension(100, 23));
        dateComboBoxBirthDate.setPreferredSize(new java.awt.Dimension(100, 23));
        dateComboBoxBirthDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxBirthDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel3.add(dateComboBoxBirthDate, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jPanel3, gridBagConstraints);

        jLabel7.setText("ผลตรวจ VDRL_RS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel7, gridBagConstraints);

        jLabel8.setText("ผลตรวจ HB_RS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel8, gridBagConstraints);

        jLabel9.setText("ผลตรวจ HIV_RS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel9, gridBagConstraints);

        jLabel10.setText("ผลตรวจ THALLASSAEMIA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel10, gridBagConstraints);

        jLabel11.setText("ตรวจสุขภาพฟัน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel11, gridBagConstraints);

        jLabel12.setText("เหงือกอักเสบ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel12, gridBagConstraints);

        jLabel13.setText("หินน้ำลาย");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel13, gridBagConstraints);

        jLabel14.setText("ฟันผุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jLabel14, gridBagConstraints);

        jLabel15.setText("สถานพยาบาลที่คลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel15, gridBagConstraints);

        jLabel16.setText("ผลสิ้นสุดการตั้งครรภ์");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel16, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        integerTextFieldHosSubCode.setMaximumSize(new java.awt.Dimension(57, 21));
        integerTextFieldHosSubCode.setMinimumSize(new java.awt.Dimension(45, 21));
        integerTextFieldHosSubCode.setPreferredSize(new java.awt.Dimension(45, 21));
        integerTextFieldHosSubCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                integerTextFieldHosSubCodeActionPerformed(evt);
            }
        });
        integerTextFieldHosSubCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                integerTextFieldHosSubCodeFocusLost(evt);
            }
        });
        integerTextFieldHosSubCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldHosSubCodeKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel8.add(integerTextFieldHosSubCode, gridBagConstraints);

        jTextFieldHosSub.setEditable(false);
        jTextFieldHosSub.setBorder(null);
        jTextFieldHosSub.setMinimumSize(new java.awt.Dimension(4, 21));
        jTextFieldHosSub.setPreferredSize(new java.awt.Dimension(4, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 0);
        jPanel8.add(jTextFieldHosSub, gridBagConstraints);

        jButtonHosSub.setText("...");
        jButtonHosSub.setToolTipText("สถานพยาบาลรอง");
        jButtonHosSub.setBorder(null);
        jButtonHosSub.setMaximumSize(new java.awt.Dimension(22, 22));
        jButtonHosSub.setMinimumSize(new java.awt.Dimension(22, 22));
        jButtonHosSub.setPreferredSize(new java.awt.Dimension(22, 22));
        jButtonHosSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHosSubActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel8.add(jButtonHosSub, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jPanel8, gridBagConstraints);

        jComboBoxMethodGiveBirth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jComboBoxMethodGiveBirth, gridBagConstraints);

        jComboBoxVdrl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jComboBoxVdrl, gridBagConstraints);

        jComboBoxHb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jComboBoxHb, gridBagConstraints);

        jComboBoxHiv.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jComboBoxHiv, gridBagConstraints);

        jComboBoxThalassemia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jComboBoxThalassemia, gridBagConstraints);

        jRadioButtonDental.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ไม่", "ใช่" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jRadioButtonDental, gridBagConstraints);

        jRadioButtonGum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ไม่", "ใช่" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jRadioButtonGum, gridBagConstraints);

        jRadioButtonTartar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ไม่", "ใช่" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jRadioButtonTartar, gridBagConstraints);

        jComboBoxResultICD10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxResultICD10.setMinimumSize(new java.awt.Dimension(150, 18));
        jComboBoxResultICD10.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jComboBoxResultICD10, gridBagConstraints);

        jLabel17.setText("วันที่ตรวจ HCT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabel17, gridBagConstraints);

        jTextField1.setMinimumSize(new java.awt.Dimension(80, 20));
        jTextField1.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jTextField1, gridBagConstraints);

        jLabel18.setText("ระดับ HTC");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jLabel18, gridBagConstraints);

        jTextFieldHctLevel.setMinimumSize(new java.awt.Dimension(80, 20));
        jTextFieldHctLevel.setPreferredSize(new java.awt.Dimension(80, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jTextFieldHctLevel, gridBagConstraints);

        dateComboBoxHctDate.setToolTipText("วันกำหนดคลอด");
        dateComboBoxHctDate.setMinimumSize(new java.awt.Dimension(100, 23));
        dateComboBoxHctDate.setPreferredSize(new java.awt.Dimension(100, 23));
        dateComboBoxHctDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxHctDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(dateComboBoxHctDate, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.65;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลแม่หลังคลอด"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel19.setText("ดูแลครั้งที่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jLabel19, gridBagConstraints);

        jLabel20.setText("วันที่ออกตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jLabel20, gridBagConstraints);

        jLabel21.setText("สถานที่คลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jLabel21, gridBagConstraints);

        jLabel22.setText("ประจำเดือน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jLabel22, gridBagConstraints);

        jLabel23.setText("หัวนม");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jLabel23, gridBagConstraints);

        jLabel24.setText("ระดับมดลูก");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jLabel24, gridBagConstraints);

        jLabel25.setText("น้ำนมไหล");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jLabel25, gridBagConstraints);

        jLabel26.setText("น้ำคาวปลา");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jLabel26, gridBagConstraints);

        jLabel27.setText("ฝีเย็บ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jLabel27, gridBagConstraints);

        jLabel28.setText("ระดับน้ำตาล");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jLabel28, gridBagConstraints);

        jLabel30.setText("ระดับอัลบลูมิน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jLabel30, gridBagConstraints);

        jLabel31.setText("ผลการตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jLabel31, gridBagConstraints);

        jTextFieldCareNo.setColumns(2);
        jTextFieldCareNo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldCareNo.setToolTipText("จำนวนการตั้งครรภ์ (ครรภ์ที่)");
        jTextFieldCareNo.setMinimumSize(new java.awt.Dimension(30, 21));
        jTextFieldCareNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCareNoFocusLost(evt);
            }
        });
        jTextFieldCareNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCareNoKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanel2.add(jTextFieldCareNo, gridBagConstraints);

        jComboBoxBPlace.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel2.add(jComboBoxBPlace, gridBagConstraints);

        jComboBoxSugarLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel2.add(jComboBoxSugarLevel, gridBagConstraints);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        integerTextFieldHosSubCodeHos.setMaximumSize(new java.awt.Dimension(57, 21));
        integerTextFieldHosSubCodeHos.setMinimumSize(new java.awt.Dimension(45, 21));
        integerTextFieldHosSubCodeHos.setPreferredSize(new java.awt.Dimension(45, 21));
        integerTextFieldHosSubCodeHos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                integerTextFieldHosSubCodeHosActionPerformed(evt);
            }
        });
        integerTextFieldHosSubCodeHos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                integerTextFieldHosSubCodeHosFocusLost(evt);
            }
        });
        integerTextFieldHosSubCodeHos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldHosSubCodeHosKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanel9.add(integerTextFieldHosSubCodeHos, gridBagConstraints);

        jTextFieldHosSub1.setEditable(false);
        jTextFieldHosSub1.setBorder(null);
        jTextFieldHosSub1.setMinimumSize(new java.awt.Dimension(4, 21));
        jTextFieldHosSub1.setPreferredSize(new java.awt.Dimension(4, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 0);
        jPanel9.add(jTextFieldHosSub1, gridBagConstraints);

        jButtonHosSub1.setText("...");
        jButtonHosSub1.setToolTipText("สถานพยาบาลรอง");
        jButtonHosSub1.setBorder(null);
        jButtonHosSub1.setMaximumSize(new java.awt.Dimension(22, 22));
        jButtonHosSub1.setMinimumSize(new java.awt.Dimension(22, 22));
        jButtonHosSub1.setPreferredSize(new java.awt.Dimension(22, 22));
        jButtonHosSub1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHosSub1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel9.add(jButtonHosSub1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel2.add(jPanel9, gridBagConstraints);

        jRadioButtonCream0.setText("ผิดปกติ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel2.add(jRadioButtonCream0, gridBagConstraints);

        jRadioButtonCream1.setText("ปกติ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel2.add(jRadioButtonCream1, gridBagConstraints);

        jRadioButtonUterus0.setText("ผิดปกติ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel2.add(jRadioButtonUterus0, gridBagConstraints);

        jRadioButtonUterus1.setText("ปกติ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel2.add(jRadioButtonUterus1, gridBagConstraints);

        jRadioButtonMilk0.setText("ไม่ไหล");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel2.add(jRadioButtonMilk0, gridBagConstraints);

        jRadioButtonMilk1.setText("ไหล");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel2.add(jRadioButtonMilk1, gridBagConstraints);

        jRadioButtonLochia0.setText("ไม่มี");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel2.add(jRadioButtonLochia0, gridBagConstraints);

        jRadioButtonLochia1.setText("มี");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel2.add(jRadioButtonLochia1, gridBagConstraints);

        jRadioButtonMen0.setText("ผิดปกติ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel2.add(jRadioButtonMen0, gridBagConstraints);

        jRadioButtonMen1.setText("ปกติ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel2.add(jRadioButtonMen1, gridBagConstraints);

        jComboBoxAlbulmin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel2.add(jComboBoxAlbulmin, gridBagConstraints);

        jComboBoxSew.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel2.add(jComboBoxSew, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jComboBoxResult.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ผิดปกติ", "ปกติ" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(jComboBoxResult, gridBagConstraints);

        jButton5.setText("นัด");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel4.add(jButton5, gridBagConstraints);

        jLabel38.setText("นัดครั้งต่อไป");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel4.add(jLabel38, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel2.add(jPanel4, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("บันทึกอาการ"));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jTextAreaSymptomNotice.setColumns(20);
        jTextAreaSymptomNotice.setRows(5);
        jScrollPane1.setViewportView(jTextAreaSymptomNotice);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 143;
        gridBagConstraints.ipady = 73;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jPanel5, gridBagConstraints);

        dateComboBoxSurveyDate.setToolTipText("วันกำหนดคลอด");
        dateComboBoxSurveyDate.setMinimumSize(new java.awt.Dimension(100, 23));
        dateComboBoxSurveyDate.setPreferredSize(new java.awt.Dimension(100, 23));
        dateComboBoxSurveyDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxSurveyDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel2.add(dateComboBoxSurveyDate, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.35;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
        add(jPanel2, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jButton2.setText("บันทึก");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(jButton2, gridBagConstraints);

        jButton3.setText("ยกเลิก");
        jPanel6.add(jButton3, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(jPanel6, gridBagConstraints);
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
        // TODO add your handling code here:
}//GEN-LAST:event_jTextFieldAFocusLost

    private void jTextFieldAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAKeyReleased
        String txt = jTextFieldA.getText();
        if(txt.length()>=1)
            jTextFieldL.requestFocus();
}//GEN-LAST:event_jTextFieldAKeyReleased

    private void jTextFieldAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAKeyTyped
        // TODO add your handling code here:
}//GEN-LAST:event_jTextFieldAKeyTyped

    private void integerTextFieldHosSubCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_integerTextFieldHosSubCodeActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_integerTextFieldHosSubCodeActionPerformed

    private void integerTextFieldHosSubCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integerTextFieldHosSubCodeFocusLost
        
}//GEN-LAST:event_integerTextFieldHosSubCodeFocusLost

    private void integerTextFieldHosSubCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldHosSubCodeKeyReleased
        
}//GEN-LAST:event_integerTextFieldHosSubCodeKeyReleased

    private void jButtonHosSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHosSubActionPerformed
        
}//GEN-LAST:event_jButtonHosSubActionPerformed

    private void jTextFieldCareNoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCareNoFocusLost
        
    }//GEN-LAST:event_jTextFieldCareNoFocusLost

    private void jTextFieldCareNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCareNoKeyReleased
        
    }//GEN-LAST:event_jTextFieldCareNoKeyReleased

    private void integerTextFieldHosSubCodeHosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_integerTextFieldHosSubCodeHosActionPerformed
        
    }//GEN-LAST:event_integerTextFieldHosSubCodeHosActionPerformed

    private void integerTextFieldHosSubCodeHosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integerTextFieldHosSubCodeHosFocusLost
        
    }//GEN-LAST:event_integerTextFieldHosSubCodeHosFocusLost

    private void integerTextFieldHosSubCodeHosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldHosSubCodeHosKeyReleased
        
    }//GEN-LAST:event_integerTextFieldHosSubCodeHosKeyReleased

    private void jButtonHosSub1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHosSub1ActionPerformed
        
    }//GEN-LAST:event_jButtonHosSub1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
}//GEN-LAST:event_jButton5ActionPerformed

    private void dateComboBoxLastMenDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxLastMenDateActionPerformed
        String dateLastMen = dateComboBoxLastMenDate.getText();
        if (!dateLastMen.equals("")) {
            dateComboBoxBirthDate.setText(com.pcu.utility.DateUtil.calPregnant(dateLastMen));
        }
}//GEN-LAST:event_dateComboBoxLastMenDateActionPerformed

    private void dateComboBoxBirthDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxBirthDateActionPerformed
        
}//GEN-LAST:event_dateComboBoxBirthDateActionPerformed

    private void dateComboBoxHctDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxHctDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateComboBoxHctDateActionPerformed

    private void dateComboBoxSurveyDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxSurveyDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateComboBoxSurveyDateActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(b_left)
        {
            this.getLeftPanel();
        }
        this.getRightPanel();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.utility.DateComboBox dateComboBoxBirthDate;
    private com.hospital_os.utility.DateComboBox dateComboBoxHctDate;
    private com.hospital_os.utility.DateComboBox dateComboBoxLastMenDate;
    private com.hospital_os.utility.DateComboBox dateComboBoxSurveyDate;
    private javax.swing.JTextField integerTextFieldHosSubCode;
    private javax.swing.JTextField integerTextFieldHosSubCodeHos;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonHosSub;
    private javax.swing.JButton jButtonHosSub1;
    private javax.swing.JComboBox jComboBoxAlbulmin;
    private javax.swing.JComboBox jComboBoxBDoctor;
    private javax.swing.JComboBox jComboBoxBPlace;
    private javax.swing.JComboBox jComboBoxBirthPlace;
    private javax.swing.JComboBox jComboBoxHb;
    private javax.swing.JComboBox jComboBoxHiv;
    private javax.swing.JComboBox jComboBoxMethodGiveBirth;
    private javax.swing.JComboBox jComboBoxResult;
    private javax.swing.JComboBox jComboBoxResultICD10;
    private javax.swing.JComboBox jComboBoxSew;
    private javax.swing.JComboBox jComboBoxSugarLevel;
    private javax.swing.JComboBox jComboBoxThalassemia;
    private javax.swing.JComboBox jComboBoxVdrl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel38;
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
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButtonCream0;
    private javax.swing.JRadioButton jRadioButtonCream1;
    private javax.swing.JComboBox jRadioButtonDental;
    private javax.swing.JComboBox jRadioButtonGum;
    private javax.swing.JRadioButton jRadioButtonLochia0;
    private javax.swing.JRadioButton jRadioButtonLochia1;
    private javax.swing.JRadioButton jRadioButtonMen0;
    private javax.swing.JRadioButton jRadioButtonMen1;
    private javax.swing.JRadioButton jRadioButtonMilk0;
    private javax.swing.JRadioButton jRadioButtonMilk1;
    private javax.swing.JComboBox jRadioButtonTartar;
    private javax.swing.JRadioButton jRadioButtonUterus0;
    private javax.swing.JRadioButton jRadioButtonUterus1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaSymptomNotice;
    private javax.swing.JTextField jTextField1;
    private com.pcu.utility.IntegerTextField jTextFieldA;
    private com.pcu.utility.IntegerTextField jTextFieldCareNo;
    private com.pcu.utility.IntegerTextField jTextFieldGravida;
    private javax.swing.JTextField jTextFieldHctLevel;
    private javax.swing.JTextField jTextFieldHosSub;
    private javax.swing.JTextField jTextFieldHosSub1;
    private com.pcu.utility.IntegerTextField jTextFieldL;
    private com.pcu.utility.IntegerTextField jTextFieldP;
    // End of variables declaration//GEN-END:variables

}
