/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelPP2.java
 *
 * Created on 12 พ.ย. 2553, 11:08:13
 */

package com.pcu.gui.panelpcu;

import com.hospital_os.object.Office;
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.Gutil;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.control.AfterMCHMotherControl;
import com.pcu.control.HosManage;
import com.pcu.control.PCUObject;
import com.pcu.gui.dialog.HosDialog;
import com.pcu.object.BornMch;
import com.pcu.object.FamilyPlaningTreatment;
import com.pcu.object.PP;
import com.pcu.object.PPCare;
import com.pcu.object.PPCareChild;
import com.pcu.object.PcuAnswer;
import com.pcu.utility.GutilPCU;


/**
 *
 * @author LionHeart
 */
public class PanelAfterMchChild2 extends javax.swing.JPanel {

    /** Creates new form PanelPP2 */
    PP thePP;
    PPCareChild thePPCareChild;
    PPCare thePPCare;
    PPCare thePPCare1;
    PPCare thePPCare2;
    PPCare thePPCare3;
    AfterMCHMotherControl theAfterMCHMotherControl;
    HosManage theHM;
    PCUObject pcuobject;
    private HosDialog theHosDialog;
    private UpdateStatus theUS;
    String mother_id;
    public PanelAfterMchChild2() {
        initComponents();
    }
    public void setControl(HosManage hm,HosDialog hd,UpdateStatus us)
    {
        theUS = us;
        theHosDialog = hd;
        theHM = hm;
        pcuobject = theHM.thePO;
        theAfterMCHMotherControl = hm.theHosControl.theAfterMCHMotherControl;
        ComboboxModel.initComboBox(jComboBoxCheckPlace1,theHM.theHosControl.theAllComboBoxControl.listPostpartumBirthPlace());
        ComboboxModel.initComboBox(jComboBoxCheckPlace2,theHM.theHosControl.theAllComboBoxControl.listPostpartumBirthPlace());
        ComboboxModel.initComboBox(jComboBoxCheckPlace3,theHM.theHosControl.theAllComboBoxControl.listPostpartumBirthPlace());

        ComboboxModel.initComboBox(jComboBoxBurthPlace, theHM.theHosControl.theAllComboBoxControl.listPostpartumBirthPlace());
        ComboboxModel.initComboBox(jComboBoxBirthMethod, theHM.theHosControl.theAllComboBoxControl.listBirthMethod());
        ComboboxModel.initComboBox(jComboBoxBirthMethod, theHM.theHosControl.theAllComboBoxControl.listBirthMethod());
        ComboboxModel.initComboBox(jComboBoxBBDoctor,theHM.theHosControl.theAllComboBoxControl.listBDoctor());
    }
    public PPCareChild getPPCareChild()
    {
        if(thePPCareChild == null)
        {
            thePPCareChild = new PPCareChild();
//            pidPanel.setText(this.theHM.theHO.thePatient.pid);
//            System.err.println("this.theHM.theHO.theFamily.pid = "+this.theHM.theHO.theFamily.pid);
//            System.err.println("this.theHM.theHO.thePatient.pid = "+this.theHM.theHO.thePatient.pid);
        }
        thePPCareChild.gravida = jComboBoxGravida.getSelectedItem().toString();
        thePPCareChild.mother_pid = pidPanel.getText();
        thePPCareChild.birth_date = dateComboBoxBirthdate.getText();
        thePPCareChild.birth_place = Gutil.getGuiData(jComboBoxBurthPlace);
        thePPCareChild.birth_method = Gutil.getGuiData(jComboBoxBirthMethod);
        thePPCareChild.bddoctor = Gutil.getGuiData(jComboBoxBBDoctor);
        thePPCareChild.born_weight = jTextFieldWeight.getText();
        thePPCareChild.born_at_hospital = integerTextFieldHosSubCode.getText();
        if(jRadioButtonEnoughtOxygen.isSelected())
            thePPCareChild.oxygen = "0";
        else
            thePPCareChild.oxygen = "1";
        if(jRadioButtonRecieveVitK.isSelected())
            thePPCareChild.get_vitk = "1";
        else
            thePPCareChild.get_vitk = "0";
        if(jRadioButtonNormal.isSelected())
            thePPCareChild.result = "1";
        else
            thePPCareChild.result = "0";
        return thePPCareChild;
    }
    public void setPPCareChild(PPCareChild ppCareChild)
    {
        thePPCareChild = ppCareChild;
        if(thePPCareChild == null)
        {
            thePPCareChild = new PPCareChild();
        }
//        integerTextFieldGravida.setText(thePPCareChild.gravida);
        jComboBoxGravida.setSelectedItem(thePPCareChild.gravida);
        pidPanel.setText(thePPCareChild.mother_pid);
        if(thePPCareChild.mother_pid.equals(""))
            pidPanel.setText(this.theHM.theHO.theFamily.mother_pid);
        dateComboBoxBirthdate.setText(Gutil.convertFieldDate(thePPCareChild.birth_date));
        Gutil.setGuiData(jComboBoxBurthPlace,thePPCareChild.birth_place);
        Gutil.setGuiData(jComboBoxBirthMethod,thePPCareChild.birth_method);
        Gutil.setGuiData(jComboBoxBBDoctor,thePPCareChild.bddoctor);
        jTextFieldWeight.setText(thePPCareChild.born_weight);
        integerTextFieldHosSubCode.setText(thePPCareChild.born_at_hospital);
        Office office = (Office)this.theHM.theHC.theLookupControl.readHospitalByCode(thePPCareChild.born_at_hospital);
        if(office!=null)
            jTextFieldHosSub.setText(office.getName());
        else
            jTextFieldHosSub.setText("");
        if(thePPCareChild.oxygen.equals("0"))
            jRadioButtonEnoughtOxygen.setSelected(true);
        else
            jRadioButtonNotEnoughtOxygen.setSelected(true);
        if(thePPCareChild.get_vitk.equals("1"))
            jRadioButtonRecieveVitK.setSelected(true);
        else
            jRadioButtonNotRecieveVitK.setSelected(true);
        if(thePPCareChild.result.equals("1"))
            jRadioButtonNormal.setSelected(true);
        else
            jRadioButtonAbNormal.setSelected(true);
    }
    public PPCare getPPCare()
    {
        getPPCare1();
        getPPCare2();
        getPPCare3();
        thePPCare.family_id = theHM.thePO.getFamily().getObjectId();
        return thePPCare;
    }
    public void setObject(PCUObject pcuo) {
        PPCareChild ppcarechild = null;
        PPCare ppcare = null;
        setPPCare(null);
        setPPCareChild(null);
        ppcarechild = theAfterMCHMotherControl.readPPChildByFid(pcuo.theHO.theFamily.getObjectId());
        if(ppcarechild!=null)
            ppcare = theAfterMCHMotherControl.readPPByPPChildId(ppcarechild.getObjectId());
//        PPCare ppcare = theAfterMCHMotherControl.readPPByVid(pcuo.theHO.theVisit.getObjectId());
//        PPCare ppcare = theAfterMCHMotherControl.readPPByFid(pcuo.theHO.theFamily.getObjectId());
//        if(ppcare!=null)
//            ppcarechild = theAfterMCHMotherControl.readPPChildByPPCare(ppcare.getObjectId());
        setPPCare(ppcare);
        setPPCareChild(ppcarechild);
        if(ppcarechild==null)
            jButton4ActionPerformed(null);
    }
    public void setPPCare(PPCare ppcare)
    {
        thePPCare = ppcare;
        if(thePPCare == null)
            thePPCare = new PPCare();
        dateComboBoxTo.setText(Gutil.convertFieldDate(thePPCare.survey_date));
        dateComboBoxTo1.setText(Gutil.convertFieldDate(thePPCare.survey_date2));
        dateComboBoxTo2.setText(Gutil.convertFieldDate(thePPCare.survey_date3));
        ComboboxModel.setCodeComboBox(jComboBoxCheckPlace1,thePPCare.pp_care_survey_place);
        ComboboxModel.setCodeComboBox(jComboBoxCheckPlace2,thePPCare.pp_care_survey_place2);
        ComboboxModel.setCodeComboBox(jComboBoxCheckPlace3,thePPCare.pp_care_survey_place3);
        if(thePPCare.pp_care_health.equals(PcuAnswer.Zero()))
            jRadioButtonWeak1.setSelected(true);
        else
            jRadioButtonStrong1.setSelected(true);
        if(thePPCare.pp_care_health2.equals(PcuAnswer.Zero()))
            jRadioButtonWeak2.setSelected(true);
        else
            jRadioButtonStrong2.setSelected(true);
        if(thePPCare.pp_care_health3.equals(PcuAnswer.Zero()))
            jRadioButtonWeak3.setSelected(true);
        else
            jRadioButtonStrong3.setSelected(true);
        if(thePPCare.pp_care_dermis.equals(PcuAnswer.Zero()))
            jRadioButtonSkinAbNormal1.setSelected(true);
        else
            jRadioButtonSkinNormal1.setSelected(true);
        if(thePPCare.pp_care_dermis2.equals(PcuAnswer.Zero()))
            jRadioButtonSkinAbNormal2.setSelected(true);
        else
            jRadioButtonSkinNormal2.setSelected(true);
        if(thePPCare.pp_care_dermis3.equals(PcuAnswer.Zero()))
            jRadioButtonSkinAbNormal3.setSelected(true);
        else
            jRadioButtonSkinNormal3.setSelected(true);
        if(thePPCare.pp_care_feces.equals(PcuAnswer.Zero())) {
            jRadioButtonStoolAbnormal1.setSelected(true);
        } else {
            jRadioButtonStoolNormal1.setSelected(true);
        }
        if(thePPCare.pp_care_feces2.equals(PcuAnswer.Zero())) {
            jRadioButtonStoolAbnormal2.setSelected(true);
        } else {
            jRadioButtonStoolNormal2.setSelected(true);
        }
        if(thePPCare.pp_care_feces3.equals(PcuAnswer.Zero())) {
            jRadioButtonStoolAbnormal3.setSelected(true);
        } else {
            jRadioButtonStoolNormal3.setSelected(true);
        }
        if(thePPCare.pp_care_urine.equals(PcuAnswer.Zero())) {
            jRadioButtonUrineAbnormal1.setSelected(true);
        } else {
            jRadioButtonUrineNormal1.setSelected(true);
        }
        if(thePPCare.pp_care_urine2.equals(PcuAnswer.Zero())) {
            jRadioButtonUrineAbnormal2.setSelected(true);
        } else {
            jRadioButtonUrineNormal2.setSelected(true);
        }
        if(thePPCare.pp_care_urine3.equals(PcuAnswer.Zero())) {
            jRadioButtonUrineAbnormal3.setSelected(true);
        } else {
            jRadioButtonUrineNormal3.setSelected(true);
        }
        if(thePPCare.pp_care_navel.equals(PcuAnswer.Zero())) {
            jRadioButtonNavelAbnormal1.setSelected(true);
        } else {
            jRadioButtonNavelNormal1.setSelected(true);
        }
        if(thePPCare.pp_care_navel2.equals(PcuAnswer.Zero())) {
            jRadioButtonNavelAbnormal2.setSelected(true);
        } else {
            jRadioButtonNavelNormal2.setSelected(true);
        }
        if(thePPCare.pp_care_navel3.equals(PcuAnswer.Zero())) {
            jRadioButtonNavelAbnormal3.setSelected(true);
        } else {
            jRadioButtonNavelNormal3.setSelected(true);
        }
        jTextAreaNoteResult1.setText(thePPCare.pp_care_state);
        jTextAreaNoteResult2.setText(thePPCare.pp_care_state2);
        jTextAreaNoteResult3.setText(thePPCare.pp_care_state3);

        if(thePPCare.pp_care_result.equals(FamilyPlaningTreatment.isNotTreatment()))
            jComboBoxResult1.setSelectedIndex(1);
        else if(thePPCare.pp_care_result.equals(FamilyPlaningTreatment.isNormal()))
            jComboBoxResult1.setSelectedIndex(1);
        else if(thePPCare.pp_care_result.equals(FamilyPlaningTreatment.isAbNormal()))
            jComboBoxResult1.setSelectedIndex(2);

        if(thePPCare.pp_care_result2.equals(FamilyPlaningTreatment.isNotTreatment()))
            jComboBoxResult2.setSelectedIndex(1);
        else if(thePPCare.pp_care_result2.equals(FamilyPlaningTreatment.isNormal()))
            jComboBoxResult2.setSelectedIndex(1);
        else if(thePPCare.pp_care_result2.equals(FamilyPlaningTreatment.isAbNormal()))
            jComboBoxResult2.setSelectedIndex(2);

        if(thePPCare.pp_care_result3.equals(FamilyPlaningTreatment.isNotTreatment()))
            jComboBoxResult3.setSelectedIndex(1);
        else if(thePPCare.pp_care_result3.equals(FamilyPlaningTreatment.isNormal()))
            jComboBoxResult3.setSelectedIndex(1);
        else if(thePPCare.pp_care_result3.equals(FamilyPlaningTreatment.isAbNormal()))
            jComboBoxResult3.setSelectedIndex(2);

        dateComboBoxTo4.setText(Gutil.convertFieldDate(thePPCare.pp_care_modify_date_time));
        if(thePPCare.pp_care_modify_date_time.length()>11)
            this.timeTextFieldCheck.setText(thePPCare.pp_care_modify_date_time.substring(11));
        jTextFieldPPCareComment.setText(thePPCare.pp_care_comment);
//        thePPCare.survey_date = dateComboBoxTo.getText();
//        thePPCare.pp_care_survey_place = Gutil.getGuiData(jComboBoxCheckPlace1);
//        if(jRadioButtonSkinAbNormal1.isSelected()) {
//            thePPCare.pp_care_dermis = PcuAnswer.Zero();
//        } else {
//            thePPCare.pp_care_dermis = PcuAnswer.One();
//        }
//        if(jRadioButtonStoolAbnormal1.isSelected()) {
//            thePPCare.pp_care_feces = PcuAnswer.Zero();
//        } else {
//            thePPCare.pp_care_feces = PcuAnswer.One();
//        }
//        if(jRadioButtonNavelAbnormal1.isSelected()) {
//            thePPCare.pp_care_navel = PcuAnswer.Zero();
//        } else {
//            thePPCare.pp_care_navel = PcuAnswer.One();
//        }
//        if(jRadioButtonUrineAbnormal1.isSelected()) {
//            thePPCare.pp_care_urine = PcuAnswer.Zero();
//        } else {
//            thePPCare.pp_care_urine = PcuAnswer.One();
//        }
//        if(jRadioButtonWeak1.isSelected()) {
//            thePPCare.pp_care_health = PcuAnswer.Zero();
//        } else {
//            thePPCare.pp_care_health = PcuAnswer.One();
//        }
//        thePPCare.pp_care_number = "1";
//        if(jComboBoxCheckPlace1.getSelectedIndex()==1)
//            thePPCare.pp_care_state = FamilyPlaningTreatment.isNormal();
//        if(jComboBoxCheckPlace1.getSelectedIndex()==2)
//            thePPCare.pp_care_state = FamilyPlaningTreatment.isAbNormal();
//        thePPCare.pp_care_comment = jTextFieldPPCareComment.getText();
//        thePPCare.pp_care_modify_date_time = dateComboBoxTo4.getText()+","+timeTextFieldCheck.getText()+":00";
    }
    public PPCare getPPCare1()
    {
        if(thePPCare == null)
            thePPCare = new PPCare();
        thePPCare.survey_date = dateComboBoxTo.getText();
        thePPCare.pp_care_survey_place = Gutil.getGuiData(jComboBoxCheckPlace1);
        if(jRadioButtonSkinAbNormal1.isSelected()) {
            thePPCare.pp_care_dermis = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_dermis = PcuAnswer.One();
        }
        if(jRadioButtonStoolAbnormal1.isSelected()) {
            thePPCare.pp_care_feces = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_feces = PcuAnswer.One();
        }
        if(jRadioButtonNavelAbnormal1.isSelected()) {
            thePPCare.pp_care_navel = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_navel = PcuAnswer.One();
        }
        if(jRadioButtonUrineAbnormal1.isSelected()) {
            thePPCare.pp_care_urine = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_urine = PcuAnswer.One();
        }
        if(jRadioButtonWeak1.isSelected()) {
            thePPCare.pp_care_health = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_health = PcuAnswer.One();
        }
        thePPCare.pp_care_number = "1";
        if(jComboBoxResult1.getSelectedIndex()==1)
            thePPCare.pp_care_result = FamilyPlaningTreatment.isNormal();
        if(jComboBoxResult1.getSelectedIndex()==2)
            thePPCare.pp_care_result = FamilyPlaningTreatment.isAbNormal();
        thePPCare.pp_care_comment = jTextFieldPPCareComment.getText();
        thePPCare.pp_care_modify_date_time = dateComboBoxTo4.getText()+","+timeTextFieldCheck.getText()+":00";
        thePPCare.pp_care_state = Gutil.CheckReservedWords(jTextAreaNoteResult1.getText());
        return thePPCare;
    }
    public PPCare getPPCare2()
    {
        if(thePPCare == null)
            thePPCare = new PPCare();
        thePPCare.survey_date2 = dateComboBoxTo1.getText();
        thePPCare.pp_care_survey_place2 = Gutil.getGuiData(jComboBoxCheckPlace2);
        if(jRadioButtonSkinAbNormal2.isSelected()) {
            thePPCare.pp_care_dermis2 = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_dermis2 = PcuAnswer.One();
        }
        if(jRadioButtonStoolAbnormal2.isSelected()) {
            thePPCare.pp_care_feces2 = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_feces2 = PcuAnswer.One();
        }
        if(jRadioButtonNavelAbnormal2.isSelected()) {
            thePPCare.pp_care_navel2 = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_navel2 = PcuAnswer.One();
        }
        if(jRadioButtonUrineAbnormal2.isSelected()) {
            thePPCare.pp_care_urine2 = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_urine2 = PcuAnswer.One();
        }
        if(jRadioButtonWeak2.isSelected()) {
            thePPCare.pp_care_health2 = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_health2 = PcuAnswer.One();
        }
        thePPCare.pp_care_number2 = "1";
        if(jComboBoxResult2.getSelectedIndex()==1)
            thePPCare.pp_care_result2 = FamilyPlaningTreatment.isNormal();
        if(jComboBoxResult2.getSelectedIndex()==2)
            thePPCare.pp_care_result2 = FamilyPlaningTreatment.isAbNormal();
//        thePPCare.pp_care_comment2 = jTextFieldPPCareComment.getText();
        thePPCare.pp_care_modify_date_time2 = dateComboBoxTo4.getText()+","+timeTextFieldCheck.getText()+":00";
        thePPCare.pp_care_state2 = Gutil.CheckReservedWords(jTextAreaNoteResult2.getText());
        return thePPCare;
    }
    public PPCare getPPCare3()
    {
        if(thePPCare == null)
            thePPCare = new PPCare();
        thePPCare.survey_date3 = dateComboBoxTo2.getText();
        thePPCare.pp_care_survey_place3 = Gutil.getGuiData(jComboBoxCheckPlace3);
        if(jRadioButtonSkinAbNormal3.isSelected()) {
            thePPCare.pp_care_dermis3 = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_dermis3 = PcuAnswer.One();
        }
        if(jRadioButtonStoolAbnormal3.isSelected()) {
            thePPCare.pp_care_feces3 = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_feces3 = PcuAnswer.One();
        }
        if(jRadioButtonNavelAbnormal3.isSelected()) {
            thePPCare.pp_care_navel3 = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_navel3 = PcuAnswer.One();
        }
        if(jRadioButtonUrineAbnormal3.isSelected()) {
            thePPCare.pp_care_urine3 = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_urine3 = PcuAnswer.One();
        }
        if(jRadioButtonWeak2.isSelected()) {
            thePPCare.pp_care_health3 = PcuAnswer.Zero();
        } else {
            thePPCare.pp_care_health3 = PcuAnswer.One();
        }
        thePPCare.pp_care_number3 = "1";
        if(jComboBoxResult3.getSelectedIndex()==1)
            thePPCare.pp_care_result3 = FamilyPlaningTreatment.isNormal();
        if(jComboBoxResult3.getSelectedIndex()==2)
            thePPCare.pp_care_result3 = FamilyPlaningTreatment.isAbNormal();
//        thePPCare.pp_care_comment2 = jTextFieldPPCareComment.getText();
        thePPCare.pp_care_modify_date_time3 = dateComboBoxTo4.getText()+","+timeTextFieldCheck.getText()+":00";
        thePPCare.pp_care_state3 = Gutil.CheckReservedWords(jTextAreaNoteResult3.getText());
        return thePPCare;
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

        buttonGroupHealth1 = new javax.swing.ButtonGroup();
        buttonGroupHealth2 = new javax.swing.ButtonGroup();
        buttonGroupHealth3 = new javax.swing.ButtonGroup();
        buttonGroupSkin1 = new javax.swing.ButtonGroup();
        buttonGroupSkin2 = new javax.swing.ButtonGroup();
        buttonGroupSkin3 = new javax.swing.ButtonGroup();
        buttonGroupNavel1 = new javax.swing.ButtonGroup();
        buttonGroupNavel2 = new javax.swing.ButtonGroup();
        buttonGroupNavel3 = new javax.swing.ButtonGroup();
        buttonGroupStool1 = new javax.swing.ButtonGroup();
        buttonGroupStool2 = new javax.swing.ButtonGroup();
        buttonGroupStool3 = new javax.swing.ButtonGroup();
        buttonGroupUrine1 = new javax.swing.ButtonGroup();
        buttonGroupUrine2 = new javax.swing.ButtonGroup();
        buttonGroupUrine3 = new javax.swing.ButtonGroup();
        buttonGroupNotEnoughtOxygen = new javax.swing.ButtonGroup();
        buttonGroupEnoughtOxygen = new javax.swing.ButtonGroup();
        buttonGroupVitk = new javax.swing.ButtonGroup();
        buttonGroupResult = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        dateComboBoxTo2 = new com.hospital_os.utility.DateComboBox();
        jButton3 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jRadioButtonSkinNormal3 = new javax.swing.JRadioButton();
        jRadioButtonSkinAbNormal3 = new javax.swing.JRadioButton();
        jPanel25 = new javax.swing.JPanel();
        jRadioButtonNavelNormal3 = new javax.swing.JRadioButton();
        jRadioButtonNavelAbnormal3 = new javax.swing.JRadioButton();
        jPanel26 = new javax.swing.JPanel();
        jRadioButtonStoolNormal3 = new javax.swing.JRadioButton();
        jRadioButtonStoolAbnormal3 = new javax.swing.JRadioButton();
        jPanel27 = new javax.swing.JPanel();
        jRadioButtonUrineNormal3 = new javax.swing.JRadioButton();
        jRadioButtonUrineAbnormal3 = new javax.swing.JRadioButton();
        jPanel28 = new javax.swing.JPanel();
        jRadioButtonStrong3 = new javax.swing.JRadioButton();
        jRadioButtonWeak3 = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaNoteResult3 = new javax.swing.JTextArea();
        jComboBoxCheckPlace3 = new javax.swing.JComboBox();
        jPanel29 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jComboBoxResult3 = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        dateComboBoxTo = new com.hospital_os.utility.DateComboBox();
        jButton1 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jRadioButtonSkinNormal1 = new javax.swing.JRadioButton();
        jRadioButtonSkinAbNormal1 = new javax.swing.JRadioButton();
        jPanel13 = new javax.swing.JPanel();
        jRadioButtonNavelNormal1 = new javax.swing.JRadioButton();
        jRadioButtonNavelAbnormal1 = new javax.swing.JRadioButton();
        jPanel14 = new javax.swing.JPanel();
        jRadioButtonStoolNormal1 = new javax.swing.JRadioButton();
        jRadioButtonStoolAbnormal1 = new javax.swing.JRadioButton();
        jPanel15 = new javax.swing.JPanel();
        jRadioButtonUrineNormal1 = new javax.swing.JRadioButton();
        jRadioButtonUrineAbnormal1 = new javax.swing.JRadioButton();
        jPanel16 = new javax.swing.JPanel();
        jRadioButtonStrong1 = new javax.swing.JRadioButton();
        jRadioButtonWeak1 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaNoteResult1 = new javax.swing.JTextArea();
        jComboBoxCheckPlace1 = new javax.swing.JComboBox();
        jPanel18 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jComboBoxResult1 = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        dateComboBoxTo1 = new com.hospital_os.utility.DateComboBox();
        jButton2 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jRadioButtonSkinNormal2 = new javax.swing.JRadioButton();
        jRadioButtonSkinAbNormal2 = new javax.swing.JRadioButton();
        jPanel19 = new javax.swing.JPanel();
        jRadioButtonNavelNormal2 = new javax.swing.JRadioButton();
        jRadioButtonNavelAbnormal2 = new javax.swing.JRadioButton();
        jPanel20 = new javax.swing.JPanel();
        jRadioButtonStoolNormal2 = new javax.swing.JRadioButton();
        jRadioButtonStoolAbnormal2 = new javax.swing.JRadioButton();
        jPanel21 = new javax.swing.JPanel();
        jRadioButtonUrineNormal2 = new javax.swing.JRadioButton();
        jRadioButtonUrineAbnormal2 = new javax.swing.JRadioButton();
        jPanel22 = new javax.swing.JPanel();
        jRadioButtonStrong2 = new javax.swing.JRadioButton();
        jRadioButtonWeak2 = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaNoteResult2 = new javax.swing.JTextArea();
        jComboBoxCheckPlace2 = new javax.swing.JComboBox();
        jPanel23 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jComboBoxResult2 = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jRadioButtonNotEnoughtOxygen = new javax.swing.JRadioButton();
        jRadioButtonEnoughtOxygen = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        jRadioButtonRecieveVitK = new javax.swing.JRadioButton();
        jRadioButtonNotRecieveVitK = new javax.swing.JRadioButton();
        jPanel10 = new javax.swing.JPanel();
        jRadioButtonNormal = new javax.swing.JRadioButton();
        jRadioButtonAbNormal = new javax.swing.JRadioButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jTextFieldPPCareComment = new javax.swing.JTextField();
        jTextFieldWeight = new com.pcu.utility.IntegerTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pidPanel = new com.hosv3.gui.component.PIDPanel();
        jComboBoxBurthPlace = new javax.swing.JComboBox();
        jComboBoxBirthMethod = new javax.swing.JComboBox();
        jComboBoxBBDoctor = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        integerTextFieldHosSubCode = new javax.swing.JTextField();
        jTextFieldHosSub = new javax.swing.JTextField();
        jButtonHosSub = new javax.swing.JButton();
        dateComboBoxBirthdate = new com.hospital_os.utility.DateComboBox();
        jComboBoxGravida = new javax.swing.JComboBox();
        jPanel30 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        dateComboBoxTo4 = new com.hospital_os.utility.DateComboBox();
        jLabel45 = new javax.swing.JLabel();
        timeTextFieldCheck = new com.hospital_os.utility.TimeTextField();
        jLabel46 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabelDetail = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลหลังคลอดครั้งที่ 3"));
        jPanel3.setMinimumSize(new java.awt.Dimension(20, 200));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 200));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel34.setText("วันที่ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel3.add(jLabel34, gridBagConstraints);

        jLabel35.setText("นัดต่อไป");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel3.add(jLabel35, gridBagConstraints);

        jLabel36.setText("ตรวจที่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel3.add(jLabel36, gridBagConstraints);

        jLabel37.setText("สุขภาพ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel3.add(jLabel37, gridBagConstraints);

        jLabel38.setText("ผิวหนัง");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel3.add(jLabel38, gridBagConstraints);

        jLabel39.setText("สะดือ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel3.add(jLabel39, gridBagConstraints);

        jLabel40.setText("อุจจาระ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel3.add(jLabel40, gridBagConstraints);

        jLabel41.setText("ปัสสาวะ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel3.add(jLabel41, gridBagConstraints);

        dateComboBoxTo2.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxTo2.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxTo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxTo2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel3.add(dateComboBoxTo2, gridBagConstraints);

        jButton3.setText("นัด");
        jButton3.setMaximumSize(new java.awt.Dimension(150, 23));
        jButton3.setMinimumSize(new java.awt.Dimension(150, 23));
        jButton3.setPreferredSize(new java.awt.Dimension(150, 23));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel3.add(jButton3, gridBagConstraints);

        jPanel24.setLayout(new java.awt.GridBagLayout());

        buttonGroupSkin3.add(jRadioButtonSkinNormal3);
        jRadioButtonSkinNormal3.setText("ปกติ");
        jPanel24.add(jRadioButtonSkinNormal3, new java.awt.GridBagConstraints());

        buttonGroupSkin3.add(jRadioButtonSkinAbNormal3);
        jRadioButtonSkinAbNormal3.setText("ผิดปกติ");
        jPanel24.add(jRadioButtonSkinAbNormal3, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel3.add(jPanel24, gridBagConstraints);

        jPanel25.setLayout(new java.awt.GridBagLayout());

        buttonGroupNavel3.add(jRadioButtonNavelNormal3);
        jRadioButtonNavelNormal3.setText("ปกติ");
        jPanel25.add(jRadioButtonNavelNormal3, new java.awt.GridBagConstraints());

        buttonGroupNavel3.add(jRadioButtonNavelAbnormal3);
        jRadioButtonNavelAbnormal3.setText("ผิดปกติ");
        jPanel25.add(jRadioButtonNavelAbnormal3, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel3.add(jPanel25, gridBagConstraints);

        jPanel26.setLayout(new java.awt.GridBagLayout());

        buttonGroupStool3.add(jRadioButtonStoolNormal3);
        jRadioButtonStoolNormal3.setText("ปกติ");
        jPanel26.add(jRadioButtonStoolNormal3, new java.awt.GridBagConstraints());

        buttonGroupStool3.add(jRadioButtonStoolAbnormal3);
        jRadioButtonStoolAbnormal3.setText("ผิดปกติ");
        jPanel26.add(jRadioButtonStoolAbnormal3, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel3.add(jPanel26, gridBagConstraints);

        jPanel27.setLayout(new java.awt.GridBagLayout());

        buttonGroupUrine3.add(jRadioButtonUrineNormal3);
        jRadioButtonUrineNormal3.setText("ปกติ");
        jPanel27.add(jRadioButtonUrineNormal3, new java.awt.GridBagConstraints());

        buttonGroupUrine3.add(jRadioButtonUrineAbnormal3);
        jRadioButtonUrineAbnormal3.setText("ผิดปกติ");
        jPanel27.add(jRadioButtonUrineAbnormal3, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel3.add(jPanel27, gridBagConstraints);

        jPanel28.setLayout(new java.awt.GridBagLayout());

        buttonGroupHealth3.add(jRadioButtonStrong3);
        jRadioButtonStrong3.setText("สมบูรณ์");
        jPanel28.add(jRadioButtonStrong3, new java.awt.GridBagConstraints());

        buttonGroupHealth3.add(jRadioButtonWeak3);
        jRadioButtonWeak3.setText("ไม่สมบูรณ์");
        jPanel28.add(jRadioButtonWeak3, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel3.add(jPanel28, gridBagConstraints);

        jScrollPane3.setMinimumSize(new java.awt.Dimension(19, 60));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(19, 60));

        jTextAreaNoteResult3.setColumns(20);
        jTextAreaNoteResult3.setRows(5);
        jScrollPane3.setViewportView(jTextAreaNoteResult3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel3.add(jScrollPane3, gridBagConstraints);

        jComboBoxCheckPlace3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxCheckPlace3.setMinimumSize(new java.awt.Dimension(150, 18));
        jComboBoxCheckPlace3.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel3.add(jComboBoxCheckPlace3, gridBagConstraints);

        jPanel29.setLayout(new java.awt.GridBagLayout());

        jLabel42.setText("ผลตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel29.add(jLabel42, gridBagConstraints);

        jComboBoxResult3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ไม่ได้รับการตรวจ", "ปกติ", "ผิดปกติ" }));
        jComboBoxResult3.setMinimumSize(new java.awt.Dimension(150, 18));
        jComboBoxResult3.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel29.add(jComboBoxResult3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(jPanel29, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลหลังคลอดครั้งที่ 1"));
        jPanel4.setMinimumSize(new java.awt.Dimension(20, 200));
        jPanel4.setPreferredSize(new java.awt.Dimension(200, 200));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel14.setText("วันที่ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel4.add(jLabel14, gridBagConstraints);

        jLabel15.setText("นัดต่อไป");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel4.add(jLabel15, gridBagConstraints);

        jLabel16.setText("ตรวจที่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel4.add(jLabel16, gridBagConstraints);

        jLabel17.setText("สุขภาพ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel4.add(jLabel17, gridBagConstraints);

        jLabel18.setText("ผิวหนัง");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel4.add(jLabel18, gridBagConstraints);

        jLabel19.setText("สะดือ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel4.add(jLabel19, gridBagConstraints);

        jLabel20.setText("อุจจาระ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel4.add(jLabel20, gridBagConstraints);

        jLabel21.setText("ปัสสาวะ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel4.add(jLabel21, gridBagConstraints);

        dateComboBoxTo.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxTo.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxToActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel4.add(dateComboBoxTo, gridBagConstraints);

        jButton1.setText("นัด");
        jButton1.setMinimumSize(new java.awt.Dimension(150, 23));
        jButton1.setPreferredSize(new java.awt.Dimension(150, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel4.add(jButton1, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        buttonGroupSkin1.add(jRadioButtonSkinNormal1);
        jRadioButtonSkinNormal1.setText("ปกติ");
        jPanel12.add(jRadioButtonSkinNormal1, new java.awt.GridBagConstraints());

        buttonGroupSkin1.add(jRadioButtonSkinAbNormal1);
        jRadioButtonSkinAbNormal1.setText("ผิดปกติ");
        jPanel12.add(jRadioButtonSkinAbNormal1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel4.add(jPanel12, gridBagConstraints);

        jPanel13.setLayout(new java.awt.GridBagLayout());

        buttonGroupNavel1.add(jRadioButtonNavelNormal1);
        jRadioButtonNavelNormal1.setText("ปกติ");
        jPanel13.add(jRadioButtonNavelNormal1, new java.awt.GridBagConstraints());

        buttonGroupNavel1.add(jRadioButtonNavelAbnormal1);
        jRadioButtonNavelAbnormal1.setText("ผิดปกติ");
        jPanel13.add(jRadioButtonNavelAbnormal1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel4.add(jPanel13, gridBagConstraints);

        jPanel14.setLayout(new java.awt.GridBagLayout());

        buttonGroupStool1.add(jRadioButtonStoolNormal1);
        jRadioButtonStoolNormal1.setText("ปกติ");
        jPanel14.add(jRadioButtonStoolNormal1, new java.awt.GridBagConstraints());

        buttonGroupStool1.add(jRadioButtonStoolAbnormal1);
        jRadioButtonStoolAbnormal1.setText("ผิดปกติ");
        jPanel14.add(jRadioButtonStoolAbnormal1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel4.add(jPanel14, gridBagConstraints);

        jPanel15.setLayout(new java.awt.GridBagLayout());

        buttonGroupUrine1.add(jRadioButtonUrineNormal1);
        jRadioButtonUrineNormal1.setText("ปกติ");
        jPanel15.add(jRadioButtonUrineNormal1, new java.awt.GridBagConstraints());

        buttonGroupUrine1.add(jRadioButtonUrineAbnormal1);
        jRadioButtonUrineAbnormal1.setText("ผิดปกติ");
        jPanel15.add(jRadioButtonUrineAbnormal1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel4.add(jPanel15, gridBagConstraints);

        jPanel16.setLayout(new java.awt.GridBagLayout());

        buttonGroupHealth1.add(jRadioButtonStrong1);
        jRadioButtonStrong1.setText("สมบูรณ์");
        jPanel16.add(jRadioButtonStrong1, new java.awt.GridBagConstraints());

        buttonGroupHealth1.add(jRadioButtonWeak1);
        jRadioButtonWeak1.setText("ไม่สมบูรณ์");
        jPanel16.add(jRadioButtonWeak1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel4.add(jPanel16, gridBagConstraints);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(23, 60));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(166, 60));

        jTextAreaNoteResult1.setColumns(20);
        jTextAreaNoteResult1.setRows(5);
        jScrollPane1.setViewportView(jTextAreaNoteResult1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel4.add(jScrollPane1, gridBagConstraints);

        jComboBoxCheckPlace1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxCheckPlace1.setMinimumSize(new java.awt.Dimension(150, 18));
        jComboBoxCheckPlace1.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel4.add(jComboBoxCheckPlace1, gridBagConstraints);

        jPanel18.setLayout(new java.awt.GridBagLayout());

        jLabel22.setText("ผลตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel18.add(jLabel22, gridBagConstraints);

        jComboBoxResult1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ไม่ได้รับการตรวจ", "ปกติ", "ผิดปกติ" }));
        jComboBoxResult1.setMinimumSize(new java.awt.Dimension(130, 18));
        jComboBoxResult1.setPreferredSize(new java.awt.Dimension(130, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel18.add(jComboBoxResult1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel4.add(jPanel18, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        add(jPanel4, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลหลังคลอดครั้งที่ 2"));
        jPanel5.setMinimumSize(new java.awt.Dimension(20, 200));
        jPanel5.setPreferredSize(new java.awt.Dimension(200, 200));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel24.setText("วันที่ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel5.add(jLabel24, gridBagConstraints);

        jLabel25.setText("นัดต่อไป");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel5.add(jLabel25, gridBagConstraints);

        jLabel26.setText("ตรวจที่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel5.add(jLabel26, gridBagConstraints);

        jLabel27.setText("สุขภาพ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel5.add(jLabel27, gridBagConstraints);

        jLabel28.setText("ผิวหนัง");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel5.add(jLabel28, gridBagConstraints);

        jLabel29.setText("สะดือ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel5.add(jLabel29, gridBagConstraints);

        jLabel30.setText("อุจจาระ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel5.add(jLabel30, gridBagConstraints);

        jLabel31.setText("ปัสสาวะ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel5.add(jLabel31, gridBagConstraints);

        dateComboBoxTo1.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxTo1.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxTo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxTo1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel5.add(dateComboBoxTo1, gridBagConstraints);

        jButton2.setText("นัด");
        jButton2.setMinimumSize(new java.awt.Dimension(150, 23));
        jButton2.setPreferredSize(new java.awt.Dimension(150, 23));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel5.add(jButton2, gridBagConstraints);

        jPanel17.setLayout(new java.awt.GridBagLayout());

        buttonGroupSkin2.add(jRadioButtonSkinNormal2);
        jRadioButtonSkinNormal2.setText("ปกติ");
        jPanel17.add(jRadioButtonSkinNormal2, new java.awt.GridBagConstraints());

        buttonGroupSkin2.add(jRadioButtonSkinAbNormal2);
        jRadioButtonSkinAbNormal2.setText("ผิดปกติ");
        jPanel17.add(jRadioButtonSkinAbNormal2, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel5.add(jPanel17, gridBagConstraints);

        jPanel19.setLayout(new java.awt.GridBagLayout());

        buttonGroupNavel2.add(jRadioButtonNavelNormal2);
        jRadioButtonNavelNormal2.setText("ปกติ");
        jPanel19.add(jRadioButtonNavelNormal2, new java.awt.GridBagConstraints());

        buttonGroupNavel2.add(jRadioButtonNavelAbnormal2);
        jRadioButtonNavelAbnormal2.setText("ผิดปกติ");
        jPanel19.add(jRadioButtonNavelAbnormal2, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel5.add(jPanel19, gridBagConstraints);

        jPanel20.setLayout(new java.awt.GridBagLayout());

        buttonGroupStool2.add(jRadioButtonStoolNormal2);
        jRadioButtonStoolNormal2.setText("ปกติ");
        jPanel20.add(jRadioButtonStoolNormal2, new java.awt.GridBagConstraints());

        buttonGroupStool2.add(jRadioButtonStoolAbnormal2);
        jRadioButtonStoolAbnormal2.setText("ผิดปกติ");
        jPanel20.add(jRadioButtonStoolAbnormal2, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel5.add(jPanel20, gridBagConstraints);

        jPanel21.setLayout(new java.awt.GridBagLayout());

        buttonGroupUrine2.add(jRadioButtonUrineNormal2);
        jRadioButtonUrineNormal2.setText("ปกติ");
        jPanel21.add(jRadioButtonUrineNormal2, new java.awt.GridBagConstraints());

        buttonGroupUrine2.add(jRadioButtonUrineAbnormal2);
        jRadioButtonUrineAbnormal2.setText("ผิดปกติ");
        jPanel21.add(jRadioButtonUrineAbnormal2, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel5.add(jPanel21, gridBagConstraints);

        jPanel22.setLayout(new java.awt.GridBagLayout());

        buttonGroupHealth2.add(jRadioButtonStrong2);
        jRadioButtonStrong2.setText("สมบูรณ์");
        jPanel22.add(jRadioButtonStrong2, new java.awt.GridBagConstraints());

        buttonGroupHealth2.add(jRadioButtonWeak2);
        jRadioButtonWeak2.setText("ไม่สมบูรณ์");
        jPanel22.add(jRadioButtonWeak2, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel5.add(jPanel22, gridBagConstraints);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(19, 60));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(19, 60));

        jTextAreaNoteResult2.setColumns(20);
        jTextAreaNoteResult2.setRows(5);
        jScrollPane2.setViewportView(jTextAreaNoteResult2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel5.add(jScrollPane2, gridBagConstraints);

        jComboBoxCheckPlace2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxCheckPlace2.setMinimumSize(new java.awt.Dimension(150, 18));
        jComboBoxCheckPlace2.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel5.add(jComboBoxCheckPlace2, gridBagConstraints);

        jPanel23.setLayout(new java.awt.GridBagLayout());

        jLabel32.setText("ผลตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel23.add(jLabel32, gridBagConstraints);

        jComboBoxResult2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ไม่ได้รับการตรวจ", "ปกติ", "ผิดปกติ" }));
        jComboBoxResult2.setMinimumSize(new java.awt.Dimension(150, 18));
        jComboBoxResult2.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel23.add(jComboBoxResult2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jPanel23, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        add(jPanel5, gridBagConstraints);

        jPanel6.setMinimumSize(new java.awt.Dimension(240, 206));
        jPanel6.setPreferredSize(new java.awt.Dimension(0, 206));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("ข้อมูลเด็กทารก"));
        jPanel1.setMinimumSize(new java.awt.Dimension(20, 206));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 206));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel8.setText("น้ำหนักแรกคลอด(กรัม)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel1.add(jLabel8, gridBagConstraints);

        jLabel9.setText("ภาวการณ์ขาดออกซิเจน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel1.add(jLabel9, gridBagConstraints);

        jLabel10.setText("ได้รับ Vit K หรือไม่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel1.add(jLabel10, gridBagConstraints);

        jLabel11.setText("ผลการตรวจทารกหลังคลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel1.add(jLabel11, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        buttonGroupEnoughtOxygen.add(jRadioButtonNotEnoughtOxygen);
        jRadioButtonNotEnoughtOxygen.setText("ไม่ขาด");
        jPanel8.add(jRadioButtonNotEnoughtOxygen, new java.awt.GridBagConstraints());

        buttonGroupEnoughtOxygen.add(jRadioButtonEnoughtOxygen);
        jRadioButtonEnoughtOxygen.setText("ขาด");
        jPanel8.add(jRadioButtonEnoughtOxygen, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jPanel8, gridBagConstraints);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        buttonGroupVitk.add(jRadioButtonRecieveVitK);
        jRadioButtonRecieveVitK.setText("ได้รับ");
        jPanel9.add(jRadioButtonRecieveVitK, new java.awt.GridBagConstraints());

        buttonGroupVitk.add(jRadioButtonNotRecieveVitK);
        jRadioButtonNotRecieveVitK.setText("ไม่ได้รับ");
        jPanel9.add(jRadioButtonNotRecieveVitK, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jPanel9, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        buttonGroupResult.add(jRadioButtonNormal);
        jRadioButtonNormal.setText("ปกติ");
        jPanel10.add(jRadioButtonNormal, new java.awt.GridBagConstraints());

        buttonGroupResult.add(jRadioButtonAbNormal);
        jRadioButtonAbNormal.setText("ผิดปกติ");
        jPanel10.add(jRadioButtonAbNormal, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jPanel10, gridBagConstraints);

        jPanel11.setBackground(new java.awt.Color(204, 204, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.setMinimumSize(new java.awt.Dimension(100, 104));
        jPanel11.setLayout(new java.awt.GridBagLayout());

        jLabel12.setBackground(new java.awt.Color(204, 204, 255));
        jLabel12.setText("มีวันดูแลลูกครั้งที่ 1 และ 2 ได้ 1.5 Point");
        jLabel12.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 5, 3);
        jPanel11.add(jLabel12, gridBagConstraints);

        jLabel13.setBackground(new java.awt.Color(204, 204, 255));
        jLabel13.setText("มีวันดูแลลูกครั้งที่ 1 อย่างเดียวได้ 1 Point");
        jLabel13.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);
        jPanel11.add(jLabel13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel11, gridBagConstraints);

        jLabel47.setText("หมายเหตุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel1.add(jLabel47, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jTextFieldPPCareComment, gridBagConstraints);

        jTextFieldWeight.setColumns(5);
        jTextFieldWeight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldWeight.setToolTipText("จำนวนบุตรที่มีชีวิตอยู่");
        jTextFieldWeight.setMinimumSize(new java.awt.Dimension(30, 21));
        jTextFieldWeight.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldWeightFocusLost(evt);
            }
        });
        jTextFieldWeight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldWeightKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jTextFieldWeight, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("ข้อมูลการคลอด"));
        jPanel2.setMinimumSize(new java.awt.Dimension(20, 206));
        jPanel2.setPreferredSize(new java.awt.Dimension(210, 206));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("เลขบัตรประชาชนมารดา");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setText("ครรภ์ที่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel3.setText("วันที่คลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel2.add(jLabel3, gridBagConstraints);

        jLabel4.setText("สถานที่คลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel2.add(jLabel4, gridBagConstraints);

        jLabel5.setText("วิธีการคลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel2.add(jLabel5, gridBagConstraints);

        jLabel6.setText("สถานพยาบาลที่ทำคลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel2.add(jLabel6, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel2.add(pidPanel, gridBagConstraints);

        jComboBoxBurthPlace.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxBurthPlace.setMinimumSize(new java.awt.Dimension(150, 18));
        jComboBoxBurthPlace.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel2.add(jComboBoxBurthPlace, gridBagConstraints);

        jComboBoxBirthMethod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxBirthMethod.setMinimumSize(new java.awt.Dimension(150, 18));
        jComboBoxBirthMethod.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel2.add(jComboBoxBirthMethod, gridBagConstraints);

        jComboBoxBBDoctor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxBBDoctor.setMinimumSize(new java.awt.Dimension(150, 18));
        jComboBoxBBDoctor.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel2.add(jComboBoxBBDoctor, gridBagConstraints);

        jLabel7.setText("ประเภทผู้ทำคลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel2.add(jLabel7, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

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
        jPanel7.add(integerTextFieldHosSubCode, gridBagConstraints);

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
        jPanel7.add(jTextFieldHosSub, gridBagConstraints);

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
        jPanel7.add(jButtonHosSub, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel2.add(jPanel7, gridBagConstraints);

        dateComboBoxBirthdate.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxBirthdate.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxBirthdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxBirthdateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel2.add(dateComboBoxBirthdate, gridBagConstraints);

        jComboBoxGravida.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
        jComboBoxGravida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxGravidaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel2.add(jComboBoxGravida, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        add(jPanel6, gridBagConstraints);

        jPanel30.setLayout(new java.awt.GridBagLayout());

        jButton4.setText("ดึงข้อมูลการคลอด");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel30.add(jButton4, new java.awt.GridBagConstraints());

        jPanel31.setLayout(new java.awt.GridBagLayout());

        jLabel44.setText("วันที่บันทึก");
        jPanel31.add(jLabel44, new java.awt.GridBagConstraints());

        dateComboBoxTo4.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxTo4.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxTo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxTo4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 0, 0);
        jPanel31.add(dateComboBoxTo4, gridBagConstraints);

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/clock.gif"))); // NOI18N
        jLabel45.setToolTipText("เวลาที่ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jPanel31.add(jLabel45, gridBagConstraints);

        timeTextFieldCheck.setMaximumSize(new java.awt.Dimension(46, 21));
        timeTextFieldCheck.setMinimumSize(new java.awt.Dimension(46, 21));
        timeTextFieldCheck.setPreferredSize(new java.awt.Dimension(46, 21));
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
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jPanel31.add(timeTextFieldCheck, gridBagConstraints);

        jLabel46.setText("น.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 1);
        jPanel31.add(jLabel46, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel30.add(jPanel31, gridBagConstraints);

        jButton5.setText("บันทึก");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel30.add(jButton5, gridBagConstraints);

        jLabelDetail.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel30.add(jLabelDetail, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel30, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void integerTextFieldHosSubCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_integerTextFieldHosSubCodeActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_integerTextFieldHosSubCodeActionPerformed

    private void integerTextFieldHosSubCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integerTextFieldHosSubCodeFocusLost
        Office office = theHM.theHC.theLookupControl.readHospitalByCode(integerTextFieldHosSubCode.getText());
        if(office==null)
        {
            integerTextFieldHosSubCode.setText("");
            jTextFieldHosSub.setText("");
            theUS.setStatus("ไม่พบสถานพยาบาลที่ตรงกับรหัสที่ระบุ กรุณาตรวจสอบรหัสอีกครั้ง", theUS.WARNING);
        }
        else
        {
            jTextFieldHosSub.setText(office.name);
        }
}//GEN-LAST:event_integerTextFieldHosSubCodeFocusLost

    private void integerTextFieldHosSubCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldHosSubCodeKeyReleased
        if(integerTextFieldHosSubCode.getText().length()==5)
        {
            Office office = theHM.theHC.theLookupControl.readHospitalByCode(integerTextFieldHosSubCode.getText());
            if(office == null)    return;
            if(office.getObjectId()!=null)
            {
                jTextFieldHosSub.setText(office.name);
            }
        }
}//GEN-LAST:event_integerTextFieldHosSubCodeKeyReleased

    private void jButtonHosSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHosSubActionPerformed
        Office office = new Office();
        if(thePPCareChild!=null)
            office.setObjectId(thePPCareChild.born_at_hospital);
        if(theHosDialog.showDialogOffice(office))
        {
            jTextFieldHosSub.setText(office.getName());
            integerTextFieldHosSubCode.setText(office.getObjectId());
        }
}//GEN-LAST:event_jButtonHosSubActionPerformed

    private void dateComboBoxToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxToActionPerformed
        
}//GEN-LAST:event_dateComboBoxToActionPerformed

    private void dateComboBoxTo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxTo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateComboBoxTo1ActionPerformed

    private void dateComboBoxTo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxTo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateComboBoxTo2ActionPerformed

    private void dateComboBoxBirthdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxBirthdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateComboBoxBirthdateActionPerformed

    private void dateComboBoxTo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxTo4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateComboBoxTo4ActionPerformed

    private void timeTextFieldCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeTextFieldCheckMouseClicked
        
}//GEN-LAST:event_timeTextFieldCheckMouseClicked

    private void timeTextFieldCheckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timeTextFieldCheckKeyReleased

}//GEN-LAST:event_timeTextFieldCheckKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(!jLabelDetail.getText().equals("-"))
        {
            if(thePP == null)
                thePP = new PP();
            if(this.thePPCare1 == null)
            {
                PPCare tmp = new PPCare();
                tmp.setPP1(getPPCare(),"1");
                tmp.family_id = thePP.family_id;
                tmp.pp_care_modify_date_time = dateComboBoxTo4.getText()+","+timeTextFieldCheck.getText()+":00";
                tmp.visit_id = thePP.t_visit_id;
                tmp.pp_care_number = "1";
                tmp.pp_care_deliver_place = integerTextFieldHosSubCode.getText();
                tmp.survey_date = dateComboBoxTo.getText();
                tmp.patient_id = mother_id;
                int res = theAfterMCHMotherControl.saveOrUpdatePPCare2(tmp);
                if(res>0)
                    thePPCare1 = tmp;
            }
            else
            {
                thePPCare1.setPP1(getPPCare(),"1");
//                thePPCare1.family_id = this.theHM.theHO.theFamily.getObjectId();
//                thePPCare1.visit_id = this.theHM.theHO.theVisit.getObjectId();
                thePPCare1.pp_care_number = "1";
                thePPCare1.pp_care_deliver_place = integerTextFieldHosSubCode.getText();
                thePPCare1.survey_date = dateComboBoxTo.getText();
                theAfterMCHMotherControl.saveOrUpdatePPCare2(thePPCare1);
            }
            if(this.thePPCare2 == null)
            {
                PPCare tmp = new PPCare();
                tmp.setPP1(getPPCare2(),"2");
                tmp.family_id = thePP.family_id;
                tmp.pp_care_modify_date_time = dateComboBoxTo4.getText()+","+timeTextFieldCheck.getText()+":00";
                tmp.visit_id = thePP.t_visit_id;
                tmp.pp_care_number = "2";
                tmp.pp_care_deliver_place = integerTextFieldHosSubCode.getText();
                tmp.survey_date = dateComboBoxTo1.getText();
                tmp.patient_id = mother_id;
                int res = theAfterMCHMotherControl.saveOrUpdatePPCare2(tmp);
                if(res>0)
                    thePPCare2 = tmp;
            }
            else
            {
                thePPCare2.setPP1(getPPCare2(),"2");
//                thePPCare2.family_id = this.theHM.theHO.theFamily.getObjectId();
//                thePPCare2.visit_id = this.theHM.theHO.theVisit.getObjectId();
                thePPCare2.pp_care_number = "2";
                thePPCare2.pp_care_deliver_place = integerTextFieldHosSubCode.getText();
                thePPCare2.survey_date = dateComboBoxTo1.getText();
                theAfterMCHMotherControl.saveOrUpdatePPCare2(thePPCare2);
            }
            if(this.thePPCare3 == null)
            {
                PPCare tmp = new PPCare();
                tmp.setPP1(getPPCare3(),"3");
                tmp.family_id = thePP.family_id;
                tmp.pp_care_modify_date_time = dateComboBoxTo4.getText()+","+timeTextFieldCheck.getText()+":00";
                tmp.visit_id = thePP.t_visit_id;
                tmp.pp_care_number = "3";
                tmp.pp_care_deliver_place = integerTextFieldHosSubCode.getText();
                tmp.survey_date = dateComboBoxTo2.getText();
                tmp.patient_id = mother_id;
                int res = theAfterMCHMotherControl.saveOrUpdatePPCare2(tmp);
                if(res>0)
                    thePPCare3 = tmp;
            }
            else
            {
                thePPCare3.setPP1(getPPCare3(),"3");
//                thePPCare3.family_id = this.theHM.theHO.theFamily.getObjectId();
//                thePPCare3.visit_id = this.theHM.theHO.theVisit.getObjectId();
                thePPCare3.pp_care_number = "3";
                thePPCare3.pp_care_deliver_place = integerTextFieldHosSubCode.getText();
                thePPCare3.survey_date = dateComboBoxTo2.getText();
                theAfterMCHMotherControl.saveOrUpdatePPCare2(thePPCare3);
            }
//            this.theHM.theUS.setStatus("ไม่สามารถบันทึกข้อมูลของหน้าจอเก่าได้", UpdateStatus.WARNING);
        }
        else
        {
            getPPCare();
            getPPCareChild();
            int res = theAfterMCHMotherControl.saveOrUpdatePPCareN(thePPCare,thePPCareChild);
            setPPCare(thePPCare);
            setPPCareChild(thePPCareChild);
        }
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(pcuobject.getPatient()==null){

            theUS.setStatus(GutilPCU.getTextBundle("No_Patient_Appointment"),UpdateStatus.WARNING);

            return;

        }

        theHosDialog.showDialogAppointment(theHM,pcuobject);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(pcuobject.getPatient()==null){

            theUS.setStatus(GutilPCU.getTextBundle("No_Patient_Appointment"),UpdateStatus.WARNING);

            return;

        }

        theHosDialog.showDialogAppointment(theHM,pcuobject);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(pcuobject.getPatient()==null){

            theUS.setStatus(GutilPCU.getTextBundle("No_Patient_Appointment"),UpdateStatus.WARNING);

            return;

        }

        theHosDialog.showDialogAppointment(theHM,pcuobject);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBoxGravidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxGravidaActionPerformed
        PPCareChild ppcarechild = null;
        PPCare ppcare = null;
        setPPCare(null);
        setPPCareChild(null);
        ppcarechild = theAfterMCHMotherControl.readPPChildByGravida(theHM.thePO.theHO.theFamily.getObjectId()
                ,jComboBoxGravida.getSelectedItem().toString());
        if(ppcarechild!=null)
            ppcare = theAfterMCHMotherControl.readPPByPPChildId(ppcarechild.getObjectId());
        setPPCare(ppcare);
        setPPCareChild(ppcarechild);
        jButton4ActionPerformed(null);
    }//GEN-LAST:event_jComboBoxGravidaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
//        theAfterMCHMotherControl.readPPBySpecialQuery(this.theHM.theHO.theFamily.getObjectId());
        String mother_patient_id = "";
        mother_id = mother_patient_id = theAfterMCHMotherControl.getMotherByBabyFamilyID(this.theHM.theHO.theFamily.getObjectId());
//        BornMch bornMch = theAfterMCHMotherControl.readMchByFidAndGravida(this.theHM.theHO.theFamily.getObjectId()
//                ,jComboBoxGravida.getSelectedItem().toString());
        BornMch bornMch = theAfterMCHMotherControl.readMchByPidAndGravida(mother_patient_id
                ,jComboBoxGravida.getSelectedItem().toString());
//        PP pp = theAfterMCHMotherControl.readPPByPidAndGravida(this.theHM.theHO.thePatient.getObjectId()
//                ,jComboBoxGravida.getSelectedItem().toString());
        PP pp = theAfterMCHMotherControl.readPPByPidAndGravida(mother_patient_id
                ,jComboBoxGravida.getSelectedItem().toString());
        thePP = pp;
        PPCareChild tmp = new PPCareChild();
        tmp.setObject(bornMch, pp);
        if(bornMch!=null || pp!=null)
            setPPCareChild(tmp);
//        else
//            setPPCareChild(null);
        PPCare pcare=null;
        PPCare pcare_tmp = null;
        if(bornMch!=null || pp!=null)
        {
            thePPCare1 = theAfterMCHMotherControl.readPPCareByFamilyIDAndGravida(this.theHM.theHO.theFamily.getObjectId(),"1");
            pcare_tmp = new PPCare();
            pcare_tmp.setPP1(thePPCare1,"1");
            thePPCare2 = theAfterMCHMotherControl.readPPCareByFamilyIDAndGravida(this.theHM.theHO.theFamily.getObjectId(),"2");
            pcare_tmp.setPP2(thePPCare2);
            thePPCare3 = theAfterMCHMotherControl.readPPCareByFamilyIDAndGravida(this.theHM.theHO.theFamily.getObjectId(),"3");
            pcare_tmp.setPP3(thePPCare3);
            jLabelDetail.setText("ข้อมูลจากหน้าจอเก่า");
            jLabelDetail.setForeground(new java.awt.Color(0, 0, 255));
        }
        else
        {
            jLabelDetail.setText("-");
            jLabelDetail.setForeground(new java.awt.Color(0, 0, 0));
        }
        if(pcare_tmp!=null)
            this.setPPCare(pcare_tmp);
//        readPPCareByFamilyIDAndGravida
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextFieldWeightFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldWeightFocusLost
        // TODO add your handling code here:
}//GEN-LAST:event_jTextFieldWeightFocusLost

    private void jTextFieldWeightKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldWeightKeyReleased
        // TODO add your handling code here:
}//GEN-LAST:event_jTextFieldWeightKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupEnoughtOxygen;
    private javax.swing.ButtonGroup buttonGroupHealth1;
    private javax.swing.ButtonGroup buttonGroupHealth2;
    private javax.swing.ButtonGroup buttonGroupHealth3;
    private javax.swing.ButtonGroup buttonGroupNavel1;
    private javax.swing.ButtonGroup buttonGroupNavel2;
    private javax.swing.ButtonGroup buttonGroupNavel3;
    private javax.swing.ButtonGroup buttonGroupNotEnoughtOxygen;
    private javax.swing.ButtonGroup buttonGroupResult;
    private javax.swing.ButtonGroup buttonGroupSkin1;
    private javax.swing.ButtonGroup buttonGroupSkin2;
    private javax.swing.ButtonGroup buttonGroupSkin3;
    private javax.swing.ButtonGroup buttonGroupStool1;
    private javax.swing.ButtonGroup buttonGroupStool2;
    private javax.swing.ButtonGroup buttonGroupStool3;
    private javax.swing.ButtonGroup buttonGroupUrine1;
    private javax.swing.ButtonGroup buttonGroupUrine2;
    private javax.swing.ButtonGroup buttonGroupUrine3;
    private javax.swing.ButtonGroup buttonGroupVitk;
    private com.hospital_os.utility.DateComboBox dateComboBoxBirthdate;
    private com.hospital_os.utility.DateComboBox dateComboBoxTo;
    private com.hospital_os.utility.DateComboBox dateComboBoxTo1;
    private com.hospital_os.utility.DateComboBox dateComboBoxTo2;
    private com.hospital_os.utility.DateComboBox dateComboBoxTo4;
    private javax.swing.JTextField integerTextFieldHosSubCode;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonHosSub;
    private javax.swing.JComboBox jComboBoxBBDoctor;
    private javax.swing.JComboBox jComboBoxBirthMethod;
    private javax.swing.JComboBox jComboBoxBurthPlace;
    private javax.swing.JComboBox jComboBoxCheckPlace1;
    private javax.swing.JComboBox jComboBoxCheckPlace2;
    private javax.swing.JComboBox jComboBoxCheckPlace3;
    private javax.swing.JComboBox jComboBoxGravida;
    private javax.swing.JComboBox jComboBoxResult1;
    private javax.swing.JComboBox jComboBoxResult2;
    private javax.swing.JComboBox jComboBoxResult3;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDetail;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButtonAbNormal;
    private javax.swing.JRadioButton jRadioButtonEnoughtOxygen;
    private javax.swing.JRadioButton jRadioButtonNavelAbnormal1;
    private javax.swing.JRadioButton jRadioButtonNavelAbnormal2;
    private javax.swing.JRadioButton jRadioButtonNavelAbnormal3;
    private javax.swing.JRadioButton jRadioButtonNavelNormal1;
    private javax.swing.JRadioButton jRadioButtonNavelNormal2;
    private javax.swing.JRadioButton jRadioButtonNavelNormal3;
    private javax.swing.JRadioButton jRadioButtonNormal;
    private javax.swing.JRadioButton jRadioButtonNotEnoughtOxygen;
    private javax.swing.JRadioButton jRadioButtonNotRecieveVitK;
    private javax.swing.JRadioButton jRadioButtonRecieveVitK;
    private javax.swing.JRadioButton jRadioButtonSkinAbNormal1;
    private javax.swing.JRadioButton jRadioButtonSkinAbNormal2;
    private javax.swing.JRadioButton jRadioButtonSkinAbNormal3;
    private javax.swing.JRadioButton jRadioButtonSkinNormal1;
    private javax.swing.JRadioButton jRadioButtonSkinNormal2;
    private javax.swing.JRadioButton jRadioButtonSkinNormal3;
    private javax.swing.JRadioButton jRadioButtonStoolAbnormal1;
    private javax.swing.JRadioButton jRadioButtonStoolAbnormal2;
    private javax.swing.JRadioButton jRadioButtonStoolAbnormal3;
    private javax.swing.JRadioButton jRadioButtonStoolNormal1;
    private javax.swing.JRadioButton jRadioButtonStoolNormal2;
    private javax.swing.JRadioButton jRadioButtonStoolNormal3;
    private javax.swing.JRadioButton jRadioButtonStrong1;
    private javax.swing.JRadioButton jRadioButtonStrong2;
    private javax.swing.JRadioButton jRadioButtonStrong3;
    private javax.swing.JRadioButton jRadioButtonUrineAbnormal1;
    private javax.swing.JRadioButton jRadioButtonUrineAbnormal2;
    private javax.swing.JRadioButton jRadioButtonUrineAbnormal3;
    private javax.swing.JRadioButton jRadioButtonUrineNormal1;
    private javax.swing.JRadioButton jRadioButtonUrineNormal2;
    private javax.swing.JRadioButton jRadioButtonUrineNormal3;
    private javax.swing.JRadioButton jRadioButtonWeak1;
    private javax.swing.JRadioButton jRadioButtonWeak2;
    private javax.swing.JRadioButton jRadioButtonWeak3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextAreaNoteResult1;
    private javax.swing.JTextArea jTextAreaNoteResult2;
    private javax.swing.JTextArea jTextAreaNoteResult3;
    private javax.swing.JTextField jTextFieldHosSub;
    private javax.swing.JTextField jTextFieldPPCareComment;
    private com.pcu.utility.IntegerTextField jTextFieldWeight;
    private com.hosv3.gui.component.PIDPanel pidPanel;
    private com.hospital_os.utility.TimeTextField timeTextFieldCheck;
    // End of variables declaration//GEN-END:variables

}
