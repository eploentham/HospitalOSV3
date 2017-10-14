/*
 * PanelANC.java
 *
 * Created on 26 กุมภาพันธ์ 2552, 16:15 น.
 */
package com.pcu.gui.panelpcu;

import com.hospital_os.object.VitalSign;
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.Gutil;
import com.hosv3.utility.Constant;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.control.AllComboBoxControl;
import com.pcu.control.HealthServiceControl;
import com.pcu.control.HosManage;
import com.pcu.control.PCUObject;
import com.pcu.object.AncDetailPcu;
import com.pcu.object.AncPcu;
import com.pcu.object.PcuAnswer;
import com.pcu.object.Pregnancy;
import com.pcu.utility.DateUtil;
import com.pcu.utility.GutilPCU;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Vector;

/**
 *
 * @author  henbe
 */
public class PanelANC extends javax.swing.JPanel {

    private HealthServiceControl theHealthServiceControl;
    HosManage theHosManage;
    private PCUObject pcuobject;
    private UpdateStatus theUS;
    private AllComboBoxControl theAllComboBoxControl;
    private AncDetailPcu theAncDetailPcu;
    private AncPcu theAncPcu;
    private Pregnancy thePregnancy;
    private boolean from_user;

    /** Creates new form PanelANC */
    public PanelANC() {
        initComponents();
    }

    public void setControl(AllComboBoxControl acc, HealthServiceControl hsc, HosManage hm, PCUObject po, UpdateStatus us) {
        theHealthServiceControl = hsc;
        theHosManage = hm;
        theAllComboBoxControl = acc;
        pcuobject = po;
        theUS = us;
        Vector vPostpartumStatusResult = theAllComboBoxControl.listPostpartumStatusResult();
        Vector vPregnancyPregnantLevel = theAllComboBoxControl.listPregnancyPregnantLevel();
        theHosManage.theHosControl.balloon.add(jTextAreaAncExamDescription);
        jTextAreaAncExamDescription.setControl(theHosManage.theHosInf.getVitalTemplate());
        jTextAreaAncExamDescription.setJFrame(theUS.getJFrame());
        ComboboxModel.initComboBox(jComboBoxUterusLevel, theAllComboBoxControl.listUterusLevel());
        ComboboxModel.initComboBox(jComboBoxPosture, theAllComboBoxControl.listBabyStatus());
        ComboboxModel.initComboBox(jComboBoxConduct, theAllComboBoxControl.listConduct());
        ComboboxModel.initComboBox(jComboBoxUrineLevel, vPostpartumStatusResult);
        ComboboxModel.initComboBox(jComboBoxVDRL, vPostpartumStatusResult);
        ComboboxModel.initComboBox(jComboBoxTHALAS, vPostpartumStatusResult);
        ComboboxModel.initComboBox(jComboBoxBabyTHALAS, vPostpartumStatusResult);
        ComboboxModel.initComboBox(jComboBoxHB, vPostpartumStatusResult);
        ComboboxModel.initComboBox(jComboBoxHIV, vPostpartumStatusResult);
        ComboboxModel.initComboBox(jComboBoxHCT, vPostpartumStatusResult);
        ComboboxModel.initComboBox(jComboBoxANCRES, vPostpartumStatusResult);
        ComboboxModel.initComboBox(jComboBoxHighRisk, theAllComboBoxControl.listPregnancyBirthHighRisk());
        ComboboxModel.initComboBox(jComboBoxAncRange, theAllComboBoxControl.listAncSection());
        ComboboxModel.initComboBox(jComboBoxSugar, vPregnancyPregnantLevel);
        ComboboxModel.initComboBox(jComboBoxAlblumin, vPregnancyPregnantLevel);
        GutilPCU.setLanguage(jLabel9);
        GutilPCU.setLanguage(jLabel12);
        jLabelAgOfPre.setText(GutilPCU.getTextBundle(jLabelAgOfPre.getText()));
        jLabelAlblumin.setText(GutilPCU.getTextBundle(jLabelAlblumin.getText()));
        jLabelAncRange.setText(GutilPCU.getTextBundle(jLabelAncRange.getText()));
        jLabelBloodInVag.setText(GutilPCU.getTextBundle(jLabelBloodInVag.getText()));
        jLabelCheckHCTDate.setText(GutilPCU.getTextBundle(jLabelCheckHCTDate.getText()));
        jLabelConduct.setText(GutilPCU.getTextBundle(jLabelConduct.getText()));
        jLabelGiveBirth.setText(GutilPCU.getTextBundle(jLabelGiveBirth.getText()));
        jLabelNextAppDate.setText(GutilPCU.getTextBundle(jLabelNextAppDate.getText()));
        jLabelHeadache.setText(GutilPCU.getTextBundle(jLabelHeadache.getText()));
        jLabelHeartFail.setText(GutilPCU.getTextBundle(jLabelHeartFail.getText()));
        jLabelHighRisk.setText(GutilPCU.getTextBundle(jLabelHighRisk.getText()));
        jLabelMenstru.setText(GutilPCU.getTextBundle(jLabelMenstru.getText()));
        jLabelMovingBaby.setText(GutilPCU.getTextBundle(jLabelMovingBaby.getText()));
        jLabelPosture.setText(GutilPCU.getTextBundle(jLabelPosture.getText()));
        jLabelRisk.setText(GutilPCU.getTextBundle(jLabelRisk.getText()));
        jLabelSoundHeart.setText(GutilPCU.getTextBundle(jLabelSoundHeart.getText()));
        jLabelSqueamish.setText(GutilPCU.getTextBundle(jLabelSqueamish.getText()));
        jLabelSugar.setText(GutilPCU.getTextBundle(jLabelSugar.getText()));
        jLabelSwell.setText(GutilPCU.getTextBundle(jLabelSwell.getText()));
        jLabelThiroil.setText(GutilPCU.getTextBundle(jLabelThiroil.getText()));
        jLabelUrine.setText(GutilPCU.getTextBundle(jLabelUrine.getText()));
        jLabelUterus.setText(GutilPCU.getTextBundle(jLabelUterus.getText()));
        jLabelHighRisk.setText(GutilPCU.getTextBundle(jLabelHighRisk.getText()));
        jLabelCramp.setText(GutilPCU.getTextBundle(jLabelCramp.getText()));
        jLabelN1.setText(GutilPCU.getTextBundle(jLabelN1.getText()));
        jLabelKgs.setText(GutilPCU.getTextBundle(jLabelKgs.getText()));
        jLabelcms.setText(GutilPCU.getTextBundle(jLabelcms.getText()));

        jLabelVDR.setText(GutilPCU.getTextBundle(jLabelVDR.getText()));
        jLabelTHALAS.setText(GutilPCU.getTextBundle(jLabelTHALAS.getText()));
        jLableHB.setText(GutilPCU.getTextBundle(jLableHB.getText()));
        jLableHIV.setText(GutilPCU.getTextBundle(jLableHIV.getText()));
        jLabelHCT.setText(GutilPCU.getTextBundle(jLabelHCT.getText()));
        jLabelCheckHCTDate.setText(GutilPCU.getTextBundle(jLabelCheckHCTDate.getText()));
        jLabelANCRES.setText(GutilPCU.getTextBundle(jLabelANCRES.getText()));
        jLabelDental.setText(GutilPCU.getTextBundle(jLabelDental.getText()));
        jLabelTCaries.setText(GutilPCU.getTextBundle(jLabelTCaries.getText()));
        jLabelTartar.setText(GutilPCU.getTextBundle(jLabelTartar.getText()));
        jLabelGumInf.setText(GutilPCU.getTextBundle(jLabelGumInf.getText()));
        jLabelReceiveTT.setText(GutilPCU.getTextBundle(jLabelReceiveTT.getText()));
        jLabelWeight.setText(GutilPCU.getTextBundle(jLabelWeight.getText()));
        jLabelHigh.setText(GutilPCU.getTextBundle(jLabelHigh.getText()));
        jLabelRemark1.setText(GutilPCU.getTextBundle(jLabelRemark1.getText()));
        jLabelPressure.setText(GutilPCU.getTextBundle(jLabelPressure.getText()));
        jLabelPulse.setText(GutilPCU.getTextBundle(jLabelPulse.getText()));
        jLabelPulseUnit.setText(GutilPCU.getTextBundle(jLabelPulseUnit.getText()));
        jLabelBabyTHALAS.setText(GutilPCU.getTextBundle(jLabelBabyTHALAS.getText()));
        jLabelHCT_R.setText(GutilPCU.getTextBundle(jLabelHCT_R.getText()));
        jLabelTT_R.setText(GutilPCU.getTextBundle(jLabelTT_R.getText()));
        jLabelSurveyDate.setText(GutilPCU.getTextBundle(jLabelSurveyDate.getText()));

        /*Tab*/
        GutilPCU.getTextBundleTab(0, jTabbedPane);
        GutilPCU.getTextBundleTab(1, jTabbedPane);
        GutilPCU.getTextBundleTab(2, jTabbedPane);
        /*Radio*/
        jRadioGiveBirth0.setText(GutilPCU.getTextBundle(jRadioGiveBirth0.getText()));
        jRadioGiveBirth1.setText(GutilPCU.getTextBundle(jRadioGiveBirth1.getText()));
        jRadioHeadache0.setText(GutilPCU.getTextBundle(jRadioHeadache0.getText()));
        jRadioHeadache1.setText(GutilPCU.getTextBundle(jRadioHeadache1.getText()));
        jRadioSoundHeart0.setText(GutilPCU.getTextBundle(jRadioSoundHeart0.getText()));
        jRadioSoundHeart1.setText(GutilPCU.getTextBundle(jRadioSoundHeart1.getText()));
        jRadioThiroil0.setText(GutilPCU.getTextBundle(jRadioThiroil0.getText()));
        jRadioThiroil1.setText(GutilPCU.getTextBundle(jRadioThiroil1.getText()));
        jRadioMovingBaby0.setText(GutilPCU.getTextBundle(jRadioMovingBaby0.getText()));
        jRadioMovingBaby1.setText(GutilPCU.getTextBundle(jRadioMovingBaby1.getText()));
        jRadioCramp0.setText(GutilPCU.getTextBundle(jRadioCramp0.getText()));
        jRadioCramp1.setText(GutilPCU.getTextBundle(jRadioCramp1.getText()));
        jRadioHeart0.setText(GutilPCU.getTextBundle(jRadioHeart0.getText()));
        jRadioHeart1.setText(GutilPCU.getTextBundle(jRadioHeart1.getText()));
        jRadioSwell0.setText(GutilPCU.getTextBundle(jRadioSwell0.getText()));
        jRadioSwell1.setText(GutilPCU.getTextBundle(jRadioSwell1.getText()));
        jRadioSqueamish0.setText(GutilPCU.getTextBundle(jRadioSqueamish0.getText()));
        jRadioSqueamish1.setText(GutilPCU.getTextBundle(jRadioSqueamish1.getText()));
        jRadioRisk0.setText(GutilPCU.getTextBundle(jRadioRisk0.getText()));
        jRadioRisk1.setText(GutilPCU.getTextBundle(jRadioRisk1.getText()));
        jRadioBlood0.setText(GutilPCU.getTextBundle(jRadioBlood0.getText()));
        jRadioBlood1.setText(GutilPCU.getTextBundle(jRadioBlood1.getText()));
        jRadioMenstruation0.setText(GutilPCU.getTextBundle(jRadioMenstruation0.getText()));
        jRadioMenstruation1.setText(GutilPCU.getTextBundle(jRadioMenstruation1.getText()));
        jRadioReceiveTT0.setText(GutilPCU.getTextBundle(jRadioReceiveTT0.getText()));
        jRadioReceiveTT1.setText(GutilPCU.getTextBundle(jRadioReceiveTT1.getText()));
        jRadioButtonDental1.setText(GutilPCU.getTextBundle(jRadioButtonDental1.getText()));
        jRadioButtonDental0.setText(GutilPCU.getTextBundle(jRadioButtonDental0.getText()));
        jRadioButtonGumInf0.setText(GutilPCU.getTextBundle(jRadioButtonGumInf0.getText()));
        jRadioButtonGumInf1.setText(GutilPCU.getTextBundle(jRadioButtonGumInf1.getText()));
        jRadioButtonTartar0.setText(GutilPCU.getTextBundle(jRadioButtonTartar0.getText()));
        jRadioButtonTartar1.setText(GutilPCU.getTextBundle(jRadioButtonTartar1.getText()));
        jRadioButtonAncNoTreat.setText(GutilPCU.getTextBundle(jRadioButtonAncNoTreat.getText()));
        jRadioButtonAncNormal.setText(GutilPCU.getTextBundle(jRadioButtonAncNormal.getText()));
        jRadioButtonAncAbnormal.setText(GutilPCU.getTextBundle(jRadioButtonAncAbnormal.getText()));
        jRadioButtonAncWaitResult.setText(GutilPCU.getTextBundle(jRadioButtonAncWaitResult.getText()));
        jRadioButtonTT1.setText(GutilPCU.getTextBundle(jRadioButtonTT1.getText()));
        jRadioButtonTT2.setText(GutilPCU.getTextBundle(jRadioButtonTT2.getText()));
        jRadioButtonTT3.setText(GutilPCU.getTextBundle(jRadioButtonTT3.getText()));
        jRadioButtonTT4.setText(GutilPCU.getTextBundle(jRadioButtonTT4.getText()));
        jCheckBoxAncOtherOffice.setText(GutilPCU.getTextBundle(jCheckBoxAncOtherOffice.getText()));
    }

    /**
     *เซตการทำงานของหน้าจอ ANC
     *@param boolean
     *@return void
     *@author jao
     *@modify kingland
     *@date 04/09/2549
     */
    public void setEnabledAnc(boolean b) {
        jTextFieldNoteRemark.setEnabled(b);
        jComboBoxAncRange.setEnabled(b);
        integerTextFieldNo.setEnabled(b);
        integerTextFieldDay.setEnabled(b);
        jRadioButtonAncNoTreat.setEnabled(b);
        jRadioButtonAncNormal.setEnabled(b);
        jRadioButtonAncAbnormal.setEnabled(b);
        jRadioButtonAncWaitResult.setEnabled(b);
        jComboBoxHighRisk.setEnabled(b);
        jComboBoxVDRL.setEnabled(b);
        jComboBoxTHALAS.setEnabled(b);
        jComboBoxHB.setEnabled(b);
        jComboBoxHIV.setEnabled(b);
        jComboBoxHCT.setEnabled(b);
        jComboBoxANCRES.setEnabled(b);
        jRadioReceiveTT0.setEnabled(b);
        jRadioReceiveTT1.setEnabled(b);
        jRadioButtonDental0.setEnabled(b);
        jRadioButtonDental1.setEnabled(b);
        integerTextFieldWeight.setEnabled(b);
        jTextAreaAncExamDescription.setEnabled(b);
        integerTextFieldHigh.setEnabled(b);
        jComboBoxSugar.setEnabled(b);
        jRadioCramp0.setEnabled(b);
        jRadioCramp1.setEnabled(b);
        jRadioHeadache1.setEnabled(b);
        jRadioHeadache0.setEnabled(b);
        jComboBoxUterusLevel.setEnabled(b);
        jRadioSoundHeart1.setEnabled(b);
        jRadioSoundHeart0.setEnabled(b);
        jRadioHeart0.setEnabled(b);
        jRadioHeart1.setEnabled(b);
        jRadioSwell0.setEnabled(b);
        jRadioSwell1.setEnabled(b);
        jRadioThiroil1.setEnabled(b);
        jRadioThiroil0.setEnabled(b);
        jRadioSqueamish0.setEnabled(b);
        jRadioSqueamish1.setEnabled(b);
        jRadioRisk0.setEnabled(b);
        jRadioRisk1.setEnabled(b);
        jRadioMovingBaby1.setEnabled(b);
        jRadioMovingBaby0.setEnabled(b);
        jComboBoxUrineLevel.setEnabled(b);
        jComboBoxAlblumin.setEnabled(b);
        jComboBoxPosture.setEnabled(b);
        jRadioBlood0.setEnabled(b);
        jRadioBlood1.setEnabled(b);
        jComboBoxConduct.setEnabled(b);
        jRadioMenstruation0.setEnabled(b);
        jRadioMenstruation1.setEnabled(b);
        jRadioGiveBirth0.setEnabled(b);
        jRadioGiveBirth1.setEnabled(b);
        jTextFieldNoteRemark.setEnabled(b);
//        jButtonDelANC.setEnabled(b);
//        jButtonSaveANC.setEnabled(b);
//        jButtonAddANC.setEnabled(b);
        doubleTextFieldBMI.setEnabled(b);
        jTextFieldPressure1.setEnabled(b);
        jTextFieldPressure2.setEnabled(b);
        jTextFieldPulse.setEnabled(b);
        doubleTextFieldUterusCm.setEnabled(b);
    }

    /**
     * ตรวสอบความสัมพันธ์ระหว่างตัวเลขกับSectionในComboBox
     * @author jao
     */
    private void setSectionSet(int y) {
        System.out.println("private void setSectionSet(int y)");
        if (y < 24) {
            Gutil.setGuiData(jComboBoxAncRange, "1");
            Gutil.setGuiData(jComboBoxHighRisk, "24");
        } else if (y < 28) {
            Gutil.setGuiData(jComboBoxAncRange, "2");
            Gutil.setGuiData(jComboBoxHighRisk, "24");
        } else if (y <= 32) {
            Gutil.setGuiData(jComboBoxAncRange, "3");
            Gutil.setGuiData(jComboBoxHighRisk, "24");
        } else if (y > 32 && y <= 40) {
            Gutil.setGuiData(jComboBoxAncRange, "4");
            Gutil.setGuiData(jComboBoxHighRisk, "24");
        } else if (y > 40) {
            Gutil.setGuiData(jComboBoxAncRange, "4");
            Gutil.setGuiData(jComboBoxHighRisk, "19");
        }
    }

    public AncPcu getAncPcu() {
        Constant.println("private AncPcu getAncPcu() {");

        if (jRadioGiveBirth1.isSelected()) {
            theAncPcu.delivery_status = "1";
        } else {
            theAncPcu.delivery_status = "0";
        }
        if (jRadioReceiveTT1.isSelected()) {
            theAncPcu.anc_tt = "1";
        } else {
            theAncPcu.anc_tt = "0";
        }

//        theAncPcu.pregnancy_id = thePregnancy.getObjectId();
        theAncPcu.no = integerTextFieldNo.getText();
        theAncPcu.anc_section = Gutil.getGuiData(jComboBoxAncRange);
        if (jRadioButtonAncNoTreat.isSelected()) {
            theAncPcu.anc_exam = "0";
        }
        if (jRadioButtonAncNormal.isSelected()) {
            theAncPcu.anc_exam = "1";
        }
        if (jRadioButtonAncAbnormal.isSelected()) {
            theAncPcu.anc_exam = "2";
        }
        if (jRadioButtonAncWaitResult.isSelected()) {
            theAncPcu.anc_exam = "3";
        }
        theAncPcu.birth_high_risk_id = Gutil.getGuiData(jComboBoxHighRisk);
        theAncPcu.anc_vdrl = Gutil.getGuiData(jComboBoxVDRL);
        theAncPcu.anc_thalassemia = Gutil.getGuiData(jComboBoxTHALAS);
        theAncPcu.anc_baby_thalassemia = Gutil.getGuiData(jComboBoxBabyTHALAS);
        theAncPcu.anc_hb = Gutil.getGuiData(jComboBoxHB);
        theAncPcu.anc_hiv = Gutil.getGuiData(jComboBoxHIV);
        theAncPcu.anc_hct = Gutil.getGuiData(jComboBoxHCT);
        theAncPcu.anc_hct_date = dateComboBoxCheckHCTDate.getText();
        theAncPcu.anc_ancres = Gutil.getGuiData(jComboBoxANCRES);
        if (jRadioButtonDental0.isSelected()) {
            theAncPcu.anc_dental_exam = PcuAnswer.Zero();
        } else {
            theAncPcu.anc_dental_exam = PcuAnswer.One();
        }
        if (jRadioButtonGumInf0.isSelected()) {
            theAncPcu.anc_dental_gum = PcuAnswer.Zero();
        } else {
            theAncPcu.anc_dental_gum = PcuAnswer.One();
        }
        if (jRadioButtonTartar0.isSelected()) {
            theAncPcu.anc_dental_tartar = PcuAnswer.Zero();
        } else {
            theAncPcu.anc_dental_tartar = PcuAnswer.One();
        }
        if (jRadioReceiveTT0.isSelected()) {
            theAncPcu.anc_tt = PcuAnswer.Zero();
            theAncPcu.anc_tt_type = "";
        } else {
            theAncPcu.anc_tt = PcuAnswer.One();
            if (jRadioButtonTT1.isSelected()) {
                theAncPcu.anc_tt_type = "1";
            } else if (jRadioButtonTT2.isSelected()) {
                theAncPcu.anc_tt_type = "2";
            } else if (jRadioButtonTT3.isSelected()) {
                theAncPcu.anc_tt_type = "3";
            } else {
                theAncPcu.anc_tt_type = "4";
            }
        }
        theAncPcu.anc_dental_caries = integerTextFieldTCaries.getText();
        theAncPcu.anc_exam_description = Gutil.CheckReservedWords(jTextAreaAncExamDescription.getText());
        theAncPcu.anc_weight = integerTextFieldWeight.getText();
        theAncPcu.anc_high = integerTextFieldHigh.getText();
        theAncPcu.anc_bmi = doubleTextFieldBMI.getText();
        //pu: 17/10/2551 ตรวจสอบการฝากครรภ์ที่สถานพยาบาลอื่น
        theAncPcu.anc_notice = Gutil.CheckReservedWords(jTextFieldNoteRemark.getText());
        theAncPcu.anc_notice = getAncOtherOfficeNotice(theAncPcu.anc_notice, jCheckBoxAncOtherOffice.isSelected());
        if (theAncPcu.anc_hct.equals("0")) {
            theAncPcu.anc_hct_date = "";
        }
        if (!jTextFieldPressure1.getText().equals("") && !jTextFieldPressure2.getText().equals("")) {
            theAncPcu.pressure = jTextFieldPressure1.getText().concat("/").concat(jTextFieldPressure2.getText());
        } else {
            theAncPcu.pressure = "";
            jTextFieldPressure1.setText("");
            jTextFieldPressure2.setText("");
        }
        theAncPcu.anc_hct_result = percentTextHCT_R.getText();
        from_user = false;
        theAncPcu.survey_date = dateComboBoxAncSurveyDate.getText();
        theAncPcu.active = "1";

//        String date_ref = pcuobject.getCurrentDateTime();
//        if (!theAncPcu.survey_date.equals("")) {
//            date_ref = theAncPcu.survey_date;
//        }
//        int[] age_week = DateUtil.countWeekDay(thePregnancy.pregnancy_menses_issue_date, date_ref);

        String date_ref = pcuobject.getCurrentDateTime();
        if (jLabelSurveyDate.isSelected()) {
            date_ref = dateComboBoxAncSurveyDate.getText();
        }else if(!dateComboBoxCheck.getText().equals("")){
            date_ref = dateComboBoxCheck.getText() + "," + timeTextFieldCheck.getText();
        }

 //henbe comment 100253 kong เพราะอะไรถึงทำฟังชันนี้ของเดิมที่มีอยุ่ใช้ไม่ได้หรือ หรือผิด
        int[] age_week = DateUtil.countWeekDay(thePregnancy.pregnancy_menses_issue_date, date_ref);
        if (age_week != null) {
            theAncPcu.anc_gravida_week = String.valueOf(age_week[0]);
            theAncPcu.anc_gravida_day = String.valueOf(age_week[1]);
        }
        if (pcuobject.getVisit() == null) {
            String survey_date = dateComboBoxAncSurveyDate.getText();
            if (survey_date.equals("")) {
                theUS.setStatus(GutilPCU.getTextBundle("WarningNotHaveSurVeyDate"), UpdateStatus.WARNING);
                dateComboBoxAncSurveyDate.requestFocus();
                return null;
            } else if (survey_date.length() != 10) {
                theUS.setStatus(GutilPCU.getTextBundle("WarningNotHaveSurVeyDate"), UpdateStatus.WARNING);
                dateComboBoxAncSurveyDate.requestFocus();
                return null;
            }
        }
        from_user = true;
        return theAncPcu;
    }

    private String getAncOtherOfficeNotice(String notice, boolean is_anc_other_office) {
        //pu:ถ้าเลือกบริการฝากครรภ์ที่สถานพยาบาลอื่น
        if (is_anc_other_office) {
            //pu:ถ้าหมายเหตุมีข้อความ แต่ยังไม่มีข้อความ "0000" จะทำการเพิ่มข้อความ"0000" ที่หน้าหมายเหตุ
            if (notice.equals("")) {
                notice = AncPcu.ANC_OTHER_OFFICE;
            } else if (!notice.substring(0, 4).equals(AncPcu.ANC_OTHER_OFFICE)) {
                notice = AncPcu.ANC_OTHER_OFFICE + notice;
            }
        } else {//pu:ถ้าไม่ได้เลือกบริการฝากครรภ์ที่สถานพยาบาลอื่น
            //pu:ถ้าหมายเหตุไม่เป็นค่าว่าง และมีข้อความ "0000" จะทำการลบข้อความ"0000" ที่หน้าหมายเหตุออก
            if (!notice.equals("") && notice.length()>=4){
                if(notice.substring(0, 4).equals(AncPcu.ANC_OTHER_OFFICE)) {
                    notice = notice.substring(4);
                }
            }
        }
        return notice;
    }

    private void setAncOtherOfficeNotice(String notice) {
        //pu:ถ้าหมายเหตุมีข้อความอยู่ และมีข้อความ "0000" ด้วยให้ check box ถูกเลือก แต่ช่องหมายเหตุไม่ต้องแสดงข้อความ "0000"
        if (!notice.equals("") && notice.length()>=4){
            if(notice.substring(0, 4).equals(AncPcu.ANC_OTHER_OFFICE)) {
                jCheckBoxAncOtherOffice.setSelected(true);
                jTextFieldNoteRemark.setText(notice.substring(4));
            }
        } else {//pu:ถ้าหมายเหตุไม่มีข้อความ "0000" ให้ check box ไม่ถูกเลือก ช่องหมายเหตุให้แสดงข้อมูลเดิมที่มีอยู่
            jCheckBoxAncOtherOffice.setSelected(false);
            jTextFieldNoteRemark.setText(notice);
        }
    }

    /**
     * บันทึก v/s ลงฐานข้อมูล
     * จัดเอกสารรายละเอียด uc แล้ว 13/11/47
     */
    public int checkSaveVitalSign(AncPcu theAncPcu) {
        int ret = 0;
        Constant.println("public int checkSaveVitalSign(AncPcu theAncPcu) {");
        //ค่ามาตรฐานเหล่านี้ได้จากการถามนายแพทย์ก้องเกียรติ  เกษเพ็ชร โดยพงศ์ธร ตันธนกิจ
        if (!theAncPcu.anc_weight.equals("")) {
            float weight = Float.parseFloat(theAncPcu.anc_weight);
            if (weight < 0 || weight > 200) {
                theUS.setStatus(GutilPCU.getTextBundle("Weight0-200"), UpdateStatus.WARNING);
                ret = 3;
            }
        }
        if (!theAncPcu.anc_high.equals("")) {
            float height = Float.parseFloat(theAncPcu.anc_high);
            if (height < 30 || height > 250) {
                theUS.setStatus(GutilPCU.getTextBundle("Height30-250"), UpdateStatus.WARNING);
                ret = 4;
            }
        }
        int index = theAncPcu.pressure.indexOf('/');
        if (index != -1) {
            String pres1 = theAncPcu.pressure.substring(0, index);
            String pres2 = theAncPcu.pressure.substring(index + 1);
            if (pres1.equals("") || pres2.equals("")) {
                theUS.setStatus(GutilPCU.getTextBundle("Pressure_Field"), UpdateStatus.WARNING);
                ret = 5;
            }
            float pressure1 = Float.parseFloat(pres1);
            float pressure2 = Float.parseFloat(pres2);
            if (pressure1 < 0 || pressure1 > 350) {
                theUS.setStatus(GutilPCU.getTextBundle("Pressure1_0-350"), UpdateStatus.WARNING);
                ret = 6;
            }
            if (pressure2 < 0 || pressure2 > 160) {
                theUS.setStatus(GutilPCU.getTextBundle("Pressure2_0-160"), UpdateStatus.WARNING);
                ret = 7;
            }
            if (pressure1 < pressure2) {
                theUS.setStatus(GutilPCU.getTextBundle("Pressure2_More_Ppressure1"), UpdateStatus.WARNING);
                ret = 8;
            }
        }
        if (ret == 3) {
            integerTextFieldWeight.requestFocus();
        }
        if (ret == 4) {
            integerTextFieldHigh.requestFocus();
        }
        if (ret == 5) {
            jTextFieldPressure1.requestFocus();
        }
        if (ret == 6) {
            jTextFieldPressure1.requestFocus();
        }
        if (ret == 7) {
            jTextFieldPressure2.requestFocus();
        }
        if (ret == 8) {
            jTextFieldPressure2.requestFocus();
        }
        return ret;
    }

    public void setAncPcu(AncPcu anc, Pregnancy preg) {
        theAncPcu = anc;
        thePregnancy = preg;
        if (theAncPcu == null) {
            System.out.println("private void setAncPcu(AncPcu anc) {");
            theAncPcu = new AncPcu();
            Gutil.setGuiData(jComboBoxAncRange, "1");
            integerTextFieldDay.setText("");
            jRadioButtonAncNoTreat.setSelected(true);
            Gutil.setGuiData(jComboBoxHighRisk, "24");
            Gutil.setGuiData(jComboBoxVDRL, "0");
            Gutil.setGuiData(jComboBoxTHALAS, "0");
            Gutil.setGuiData(jComboBoxBabyTHALAS, "0");
            Gutil.setGuiData(jComboBoxHB, "0");
            Gutil.setGuiData(jComboBoxHIV, "0");
            Gutil.setGuiData(jComboBoxHCT, "0");
            String datetime = pcuobject.getCurrentDateTime();
            String date = datetime.substring(0, 10);
            dateComboBoxCheckHCTDate.setText(Gutil.convertFieldDate(date));
            Gutil.setGuiData(jComboBoxANCRES, "0");
            jRadioReceiveTT0.setSelected(true);
            jRadioButtonDental0.setSelected(true);
            jRadioButtonGumInf0.setSelected(true);
            jRadioButtonTartar0.setSelected(true);
            integerTextFieldWeight.setText("");
            jTextAreaAncExamDescription.setText("");
            integerTextFieldHigh.setText("");
            jTextFieldNoteRemark.setText("");
            doubleTextFieldBMI.setText("");
            jTextFieldPressure1.setText("");
            jTextFieldPressure2.setText("");
            integerTextFieldNo.setText("");
            dateComboBoxCheckHCTDate.setEnabled(false);
            percentTextHCT_R.setEnabled(false);
            jRadioButtonGumInf0.setEnabled(false);
            jRadioButtonGumInf1.setEnabled(false);
            jRadioButtonTartar0.setEnabled(false);
            jRadioButtonTartar1.setEnabled(false);
            integerTextFieldTCaries.setEnabled(false);

            if (thePregnancy != null && !("").equals(thePregnancy.pregnancy_menses_issue_date)) {
                String date_ref = pcuobject.getCurrentDateTime();
//        if(!dateComboBoxAncSurveyDate.getText().equals(""))
//            date_ref = dateComboBoxAncSurveyDate.getText();

                if (jLabelSurveyDate.isSelected()) {
                    date_ref = dateComboBoxAncSurveyDate.getText();
                } else if (!dateComboBoxCheck.getText().equals("")) {
                    date_ref = dateComboBoxCheck.getText() + "," + timeTextFieldCheck.getText();
                }
 //henbe comment 100253 kong เพราะอะไรถึงทำฟังชันนี้ของเดิมที่มีอยุ่ใช้ไม่ได้หรือ หรือผิด
                String age_week = DateUtil.countWeekS(thePregnancy.pregnancy_menses_issue_date, date_ref);
                this.integerTextFieldDay.setText(age_week);
                setSectionSet(DateUtil.countWeek(thePregnancy.pregnancy_menses_issue_date, date_ref));
            }
            if (pcuobject.getVisit() != null) {
                importVitalSign();
            }
            this.jLabelVNAnc.setText("");
            return;
        }
        ComboboxModel.setCodeComboBox(jComboBoxAncRange, theAncPcu.anc_section);
        dateComboBoxAncSurveyDate.setText(Gutil.convertFieldDate(theAncPcu.survey_date));
        this.jLabelSurveyDate.setSelected(!theAncPcu.survey_date.equals(""));
        this.jLabelVNAnc.setText("");
        if (!theAncPcu.visit_id.equals("")) {
            String vn_id = this.theAllComboBoxControl.readVNbyVid(theAncPcu.visit_id);
            this.jLabelVNAnc.setText("VN:" + vn_id);
        }
        integerTextFieldNo.setText(theAncPcu.no);
        this.integerTextFieldDay.setText(
                theAncPcu.anc_gravida_week + " สัปดาห์ " +
                theAncPcu.anc_gravida_day + " วัน");

        String date_ref = pcuobject.getCurrentDateTime();
        if (!thePregnancy.survey_date.equals("")) {
            date_ref = thePregnancy.survey_date;
        } else {
            date_ref = theAncPcu.record_date_time;
        }
        if (theAncPcu.anc_exam.equals("0")) {
            jRadioButtonAncNoTreat.setSelected(true);
        }
        if (theAncPcu.anc_exam.equals("1")) {
            jRadioButtonAncNormal.setSelected(true);
        }
        if (theAncPcu.anc_exam.equals("2")) {
            jRadioButtonAncAbnormal.setSelected(true);
        }
        if (theAncPcu.anc_exam.equals("3")) {
            jRadioButtonAncWaitResult.setSelected(true);
        }
        ComboboxModel.setCodeComboBox(jComboBoxHighRisk, theAncPcu.birth_high_risk_id);
        ComboboxModel.setCodeComboBox(jComboBoxVDRL, theAncPcu.anc_vdrl);
        ComboboxModel.setCodeComboBox(jComboBoxTHALAS, theAncPcu.anc_thalassemia);
        ComboboxModel.setCodeComboBox(jComboBoxBabyTHALAS, theAncPcu.anc_baby_thalassemia);
        ComboboxModel.setCodeComboBox(jComboBoxHB, theAncPcu.anc_hb);
        ComboboxModel.setCodeComboBox(jComboBoxHIV, theAncPcu.anc_hiv);
        ComboboxModel.setCodeComboBox(jComboBoxHCT, theAncPcu.anc_hct);
        dateComboBoxCheckHCTDate.setText(Gutil.convertFieldDate(theAncPcu.anc_hct_date));
        ComboboxModel.setCodeComboBox(jComboBoxANCRES, theAncPcu.anc_ancres);


        if (theAncPcu.anc_dental_exam.equals(PcuAnswer.Zero())) {
            jRadioButtonDental0.setSelected(true);
        } else {
            jRadioButtonDental1.setSelected(true);
        }
        if (theAncPcu.anc_dental_gum.equals(PcuAnswer.Zero())) {
            jRadioButtonGumInf0.setSelected(true);
        } else {
            jRadioButtonGumInf1.setSelected(true);
        }
        if (theAncPcu.anc_dental_tartar.equals(PcuAnswer.Zero())) {
            jRadioButtonTartar0.setSelected(true);
        } else {
            jRadioButtonTartar1.setSelected(true);
        }
        if (theAncPcu.anc_tt.equals(PcuAnswer.Zero())) {
            jRadioReceiveTT0.setSelected(true);
        } else {
            jRadioReceiveTT1.setSelected(true);
            if (theAncPcu.anc_tt_type.equals("1")) {
                jRadioButtonTT1.setSelected(true);
            } else if (theAncPcu.anc_tt_type.equals("2")) {
                jRadioButtonTT2.setSelected(true);
            } else if (theAncPcu.anc_tt_type.equals("3")) {
                jRadioButtonTT3.setSelected(true);
            } else if (theAncPcu.anc_tt_type.equals("4")) {
                jRadioButtonTT4.setSelected(true);
            }
        }
        percentTextHCT_R.setText(theAncPcu.anc_hct_result);
        integerTextFieldTCaries.setText(theAncPcu.anc_dental_caries);
        jTextAreaAncExamDescription.setText(theAncPcu.anc_exam_description);
        integerTextFieldHigh.setText(theAncPcu.anc_high);
        integerTextFieldWeight.setText(theAncPcu.anc_weight);
        doubleTextFieldBMI.setText(theAncPcu.anc_bmi);
        //pu:17/10/2551 เซ็ตหน้า GUI ของการฝากครรภ์สถานพยาบาลอื่น
        setAncOtherOfficeNotice(theAncPcu.anc_notice);
        jTextFieldPressure1.setText("");
        jTextFieldPressure2.setText("");
        if (theAncPcu.pressure != null) {
            String pressure[] = theAncPcu.pressure.split("/");
            if (pressure.length > 1) {
                jTextFieldPressure1.setText(pressure[0]);
                jTextFieldPressure2.setText(pressure[1]);
            }
        }
        if (theAncPcu.delivery_status.equals("1")) {
            jRadioGiveBirth1.setSelected(true);
        } else {
            jRadioGiveBirth0.setSelected(true);
        }

        if (theAncPcu.anc_tt.equals("1")) {
            jRadioReceiveTT1.setSelected(true);
        } else {
            jRadioReceiveTT0.setSelected(true);
        }

        integerTextFieldNo.setEnabled(true);
        integerTextFieldDay.setEnabled(true);
        selectTT();
    }

    public void setAncDetailPcu(AncDetailPcu ancd) {
        theAncDetailPcu = ancd;
        if (ancd == null) {
            theAncDetailPcu = new AncDetailPcu();
            Gutil.setGuiData(jComboBoxSugar, "1");
            jRadioCramp0.setSelected(true);
            jRadioHeadache0.setSelected(true);
            Gutil.setGuiData(jComboBoxUterusLevel, "0");
            jRadioThiroil0.setSelected(true);
            jRadioHeart0.setSelected(true);
            jRadioSwell0.setSelected(true);
            jRadioSoundHeart1.setSelected(true);
            jRadioSqueamish0.setSelected(true);
            jRadioRisk0.setSelected(true);
            jRadioMovingBaby1.setSelected(true);
            Gutil.setGuiData(jComboBoxUrineLevel, "1");
            Gutil.setGuiData(jComboBoxAlblumin, "1");
            Gutil.setGuiData(jComboBoxPosture, "9");
            jRadioBlood0.setSelected(true);
            Gutil.setGuiData(jComboBoxConduct, "0");
            jRadioMenstruation0.setSelected(true);
            jRadioGiveBirth0.setSelected(true);
            doubleTextFieldUterusCm.setText("");
        }
        ComboboxModel.setCodeComboBox(jComboBoxSugar, theAncDetailPcu.anc_detail_sugar);
        ComboboxModel.setCodeComboBox(jComboBoxUterusLevel, theAncDetailPcu.anc_detail_fundus_height);
        ComboboxModel.setCodeComboBox(jComboBoxUrineLevel, theAncDetailPcu.anc_detail_urine_alblumin);
        ComboboxModel.setCodeComboBox(jComboBoxAlblumin, theAncDetailPcu.anc_detail_albumin);
        ComboboxModel.setCodeComboBox(jComboBoxPosture, theAncDetailPcu.baby_status_id);
        ComboboxModel.setCodeComboBox(jComboBoxConduct, theAncDetailPcu.conduct_id);
        jTextFieldPulse.setText(theAncDetailPcu.heart_rate);
        doubleTextFieldUterusCm.setText(theAncDetailPcu.anc_detail_uteruscm);
        if (theAncDetailPcu.anc_detail_cramp.equals("1")) {
            jRadioCramp1.setSelected(true);
        } else {
            jRadioCramp0.setSelected(true);
        }

        if (theAncDetailPcu.anc_detail_fetal_heart_sound.equals("1")) {
            jRadioHeart1.setSelected(true);
        } else {
            jRadioHeart0.setSelected(true);
        }

        if (theAncDetailPcu.anc_detail_edema.equals("1")) {
            jRadioSwell1.setSelected(true);
        } else {
            jRadioSwell0.setSelected(true);
        }

        if (theAncDetailPcu.anc_detail_nausea.equals("1")) {
            jRadioSqueamish1.setSelected(true);
        } else {
            jRadioSqueamish0.setSelected(true);
        }

        if (theAncDetailPcu.anc_detail_risk.equals("1")) {
            jRadioRisk1.setSelected(true);
        } else {
            jRadioRisk0.setSelected(true);
        }

        if (theAncDetailPcu.anc_detail_vaginal_breeding.equals("1")) {
            jRadioBlood1.setSelected(true);
        } else {
            jRadioBlood0.setSelected(true);
        }

        if (theAncDetailPcu.anc_detail_vaginal_discharge.equals("1")) {
            jRadioMenstruation1.setSelected(true);
        } else {
            jRadioMenstruation0.setSelected(true);
        }

        if (theAncDetailPcu.anc_detail_headache.equals("1")) {
            jRadioHeadache1.setSelected(true);
        } else {
            jRadioHeadache0.setSelected(true);
        }

        if (theAncDetailPcu.anc_detail_fetal_heart_sound.equals("1")) {
            jRadioSoundHeart1.setSelected(true);
        } else {
            jRadioSoundHeart0.setSelected(true);
        }

        if (theAncDetailPcu.anc_detail_thyroid.equals("1")) {
            jRadioThiroil1.setSelected(true);
        } else {
            jRadioThiroil0.setSelected(true);
        }

        if (theAncDetailPcu.anc_detail_fetal_movement.equals("1")) {
            jRadioMovingBaby1.setSelected(true);
        } else {
            jRadioMovingBaby0.setSelected(true);
        }

    }

    /**
     *ส่งข้อจาก  Gui ไปใน Object
     *@param -
     *@return void
     *@author jao
     *@modify kignland
     *@date 04/09/2549
     */
    public AncDetailPcu getAncDetailPcu() {
        if (jRadioCramp1.isSelected()) {
            theAncDetailPcu.anc_detail_cramp = "1";
        } else {
            theAncDetailPcu.anc_detail_cramp = "0";
        }
        if (jRadioHeart1.isSelected()) {
            theAncDetailPcu.anc_detail_heart_disease = "1";
        } else {
            theAncDetailPcu.anc_detail_heart_disease = "0";
        }
        if (jRadioHeart1.isSelected()) {
            theAncDetailPcu.anc_detail_fetal_heart_sound = "1";
        } else {
            theAncDetailPcu.anc_detail_fetal_heart_sound = "0";
        }

        if (jRadioSwell1.isSelected()) {
            theAncDetailPcu.anc_detail_edema = "1";
        } else {
            theAncDetailPcu.anc_detail_edema = "0";
        }
        if (jRadioSqueamish1.isSelected()) {
            theAncDetailPcu.anc_detail_nausea = "1";
        } else {
            theAncDetailPcu.anc_detail_nausea = "0";
        }
        if (jRadioRisk1.isSelected()) {
            theAncDetailPcu.anc_detail_risk = "1";
        } else {
            theAncDetailPcu.anc_detail_risk = "0";
        }

        if (jRadioBlood1.isSelected()) {
            theAncDetailPcu.anc_detail_vaginal_breeding = "1";
        } else {
            theAncDetailPcu.anc_detail_vaginal_breeding = "0";
        }
        if (jRadioMenstruation1.isSelected()) {
            theAncDetailPcu.anc_detail_vaginal_discharge = "1";
        } else {
            theAncDetailPcu.anc_detail_vaginal_discharge = "0";
        }
        if (jRadioHeadache1.isSelected()) {
            theAncDetailPcu.anc_detail_headache = "1";
        } else {
            theAncDetailPcu.anc_detail_headache = "0";
        }

        if (jRadioSoundHeart1.isSelected()) {
            theAncDetailPcu.anc_detail_fetal_heart_sound = "1";
        } else {
            theAncDetailPcu.anc_detail_fetal_heart_sound = "0";
        }
        if (jRadioThiroil1.isSelected()) {
            theAncDetailPcu.anc_detail_thyroid = "1";
        } else {
            theAncDetailPcu.anc_detail_thyroid = "0";
        }
        if (jRadioMovingBaby1.isSelected()) {
            theAncDetailPcu.anc_detail_fetal_movement = "1";
        } else {
            theAncDetailPcu.anc_detail_fetal_movement = "0";
        }


//        theAncDetailPcu.pregnancy_id = thePregnancy.getObjectId();
        theAncDetailPcu.anc_detail_sugar = Gutil.getGuiData(jComboBoxSugar);
        theAncDetailPcu.anc_detail_fundus_height = Gutil.getGuiData(jComboBoxUterusLevel);
        theAncDetailPcu.anc_detail_urine_alblumin = Gutil.getGuiData(jComboBoxUrineLevel);
        theAncDetailPcu.anc_detail_albumin = Gutil.getGuiData(jComboBoxAlblumin);
        theAncDetailPcu.baby_status_id = Gutil.getGuiData(jComboBoxPosture);
        theAncDetailPcu.conduct_id = Gutil.getGuiData(jComboBoxConduct);
        theAncDetailPcu.heart_rate = jTextFieldPulse.getText();
        theAncDetailPcu.anc_detail_uteruscm = doubleTextFieldUterusCm.getText();
        theAncDetailPcu.active = "1";

        return theAncDetailPcu;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroupCramp = new javax.swing.ButtonGroup();
        buttonGroupHeart_failure = new javax.swing.ButtonGroup();
        buttonGroupSwell_of_mother = new javax.swing.ButtonGroup();
        buttonGroupSqueamish = new javax.swing.ButtonGroup();
        buttonGroupRisk = new javax.swing.ButtonGroup();
        buttonGroupBlood_in_vagina = new javax.swing.ButtonGroup();
        buttonGroupMenstruation = new javax.swing.ButtonGroup();
        buttonGroupHeadache = new javax.swing.ButtonGroup();
        buttonGroupSound_heart = new javax.swing.ButtonGroup();
        buttonGroupThiroil = new javax.swing.ButtonGroup();
        buttonGroupMoving_baby = new javax.swing.ButtonGroup();
        buttonGroupGive_birth = new javax.swing.ButtonGroup();
        buttonGroupReceiveTT = new javax.swing.ButtonGroup();
        buttonGroupDantal = new javax.swing.ButtonGroup();
        buttonGroupGumInf = new javax.swing.ButtonGroup();
        buttonGroupTartar = new javax.swing.ButtonGroup();
        buttonGroupResultAnc = new javax.swing.ButtonGroup();
        buttonTT = new javax.swing.ButtonGroup();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanelDataSub1 = new javax.swing.JPanel();
        jPanelAncSurvey1 = new javax.swing.JPanel();
        dateComboBoxAncSurveyDate = new com.pcu.utility.DateComboBox();
        jLabelNextAppDate = new javax.swing.JLabel();
        integerTextFieldNo = new com.pcu.utility.IntegerTextField();
        jLabelAgOfPre = new javax.swing.JLabel();
        integerTextFieldDay = new javax.swing.JLabel();
        jLabelSurveyDate = new javax.swing.JCheckBox();
        jPanelData = new javax.swing.JPanel();
        jLabelAncRange = new javax.swing.JLabel();
        jComboBoxAncRange = new javax.swing.JComboBox();
        jLabelHighRisk = new javax.swing.JLabel();
        jComboBoxHighRisk = new javax.swing.JComboBox();
        jPanelCheckAnyRS = new javax.swing.JPanel();
        jLabelTartar = new javax.swing.JLabel();
        jLabelDental = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabelTCaries = new javax.swing.JLabel();
        integerTextFieldTCaries = new com.pcu.utility.IntegerTextField();
        jLabelN1 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jRadioButtonDental1 = new javax.swing.JRadioButton();
        jRadioButtonDental0 = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jRadioButtonGumInf1 = new javax.swing.JRadioButton();
        jRadioButtonGumInf0 = new javax.swing.JRadioButton();
        jLabelGumInf = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jRadioButtonTartar0 = new javax.swing.JRadioButton();
        jRadioButtonTartar1 = new javax.swing.JRadioButton();
        jLabelReceiveTT = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jRadioReceiveTT0 = new javax.swing.JRadioButton();
        jRadioReceiveTT1 = new javax.swing.JRadioButton();
        jPanel26 = new javax.swing.JPanel();
        jRadioButtonTT2 = new javax.swing.JRadioButton();
        jRadioButtonTT1 = new javax.swing.JRadioButton();
        jLabelTT_R = new javax.swing.JLabel();
        jRadioButtonTT3 = new javax.swing.JRadioButton();
        jRadioButtonTT4 = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jLabelWeight = new javax.swing.JLabel();
        jLabelKgs = new javax.swing.JLabel();
        jLabelHigh = new javax.swing.JLabel();
        jLabelcms = new javax.swing.JLabel();
        jLabelBMI = new javax.swing.JLabel();
        doubleTextFieldBMI = new com.hospital_os.utility.DoubleTextField();
        integerTextFieldWeight = new com.pcu.utility.DoubleTextField();
        integerTextFieldHigh = new com.pcu.utility.DoubleTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabelPressure = new javax.swing.JLabel();
        jTextFieldPressure1 = new com.hospital_os.utility.DoubleTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldPressure2 = new com.hospital_os.utility.DoubleTextField();
        jLabelPresUnit = new javax.swing.JLabel();
        jCheckBoxAncOtherOffice = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabelVDR = new javax.swing.JLabel();
        jComboBoxVDRL = new javax.swing.JComboBox();
        jLableHB = new javax.swing.JLabel();
        jComboBoxHB = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jLabelCheckHCTDate = new javax.swing.JLabel();
        dateComboBoxCheckHCTDate = new com.pcu.utility.DateComboBox();
        jLabelHCT_R = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        percentTextHCT_R = new com.hospital_os.utility.PercentTextField();
        jComboBoxHCT = new javax.swing.JComboBox();
        jLabelANCRES = new javax.swing.JLabel();
        jComboBoxANCRES = new javax.swing.JComboBox();
        jLabelBabyTHALAS = new javax.swing.JLabel();
        jComboBoxBabyTHALAS = new javax.swing.JComboBox();
        jLableHIV = new javax.swing.JLabel();
        jComboBoxHIV = new javax.swing.JComboBox();
        jLabelTHALAS = new javax.swing.JLabel();
        jComboBoxTHALAS = new javax.swing.JComboBox();
        jLabelHCT = new javax.swing.JLabel();
        jPanelDataSub2 = new javax.swing.JPanel();
        jLabelHeadache = new javax.swing.JLabel();
        jLabelMovingBaby = new javax.swing.JLabel();
        jLabelBloodInVag = new javax.swing.JLabel();
        jLabelHeartFail = new javax.swing.JLabel();
        jLabelSqueamish = new javax.swing.JLabel();
        jLabelRisk = new javax.swing.JLabel();
        jLabelThiroil = new javax.swing.JLabel();
        jLabelSwell = new javax.swing.JLabel();
        jLabelMenstru = new javax.swing.JLabel();
        jLabelGiveBirth = new javax.swing.JLabel();
        jLabelCramp = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jRadioCramp0 = new javax.swing.JRadioButton();
        jRadioCramp1 = new javax.swing.JRadioButton();
        jPanel10 = new javax.swing.JPanel();
        jRadioHeart0 = new javax.swing.JRadioButton();
        jRadioHeart1 = new javax.swing.JRadioButton();
        jPanel11 = new javax.swing.JPanel();
        jRadioSwell0 = new javax.swing.JRadioButton();
        jRadioSwell1 = new javax.swing.JRadioButton();
        jPanel12 = new javax.swing.JPanel();
        jRadioSqueamish0 = new javax.swing.JRadioButton();
        jRadioSqueamish1 = new javax.swing.JRadioButton();
        jPanel13 = new javax.swing.JPanel();
        jRadioRisk0 = new javax.swing.JRadioButton();
        jRadioRisk1 = new javax.swing.JRadioButton();
        jPanel14 = new javax.swing.JPanel();
        jRadioBlood0 = new javax.swing.JRadioButton();
        jRadioBlood1 = new javax.swing.JRadioButton();
        jPanel15 = new javax.swing.JPanel();
        jRadioMenstruation0 = new javax.swing.JRadioButton();
        jRadioMenstruation1 = new javax.swing.JRadioButton();
        jPanel17 = new javax.swing.JPanel();
        jRadioThiroil0 = new javax.swing.JRadioButton();
        jRadioThiroil1 = new javax.swing.JRadioButton();
        jPanel19 = new javax.swing.JPanel();
        jRadioMovingBaby0 = new javax.swing.JRadioButton();
        jRadioMovingBaby1 = new javax.swing.JRadioButton();
        jRadioGiveBirth0 = new javax.swing.JRadioButton();
        jRadioGiveBirth1 = new javax.swing.JRadioButton();
        jRadioHeadache0 = new javax.swing.JRadioButton();
        jRadioHeadache1 = new javax.swing.JRadioButton();
        jPanel22 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxUterusLevel = new javax.swing.JComboBox();
        doubleTextFieldUterusCm = new com.pcu.utility.DoubleTextField();
        jLabelUterus = new javax.swing.JLabel();
        jLabelConduct = new javax.swing.JLabel();
        jLabelSugar = new javax.swing.JLabel();
        jLabelPosture = new javax.swing.JLabel();
        jLabelAlblumin = new javax.swing.JLabel();
        jLabelUrine = new javax.swing.JLabel();
        jComboBoxConduct = new javax.swing.JComboBox();
        jComboBoxSugar = new javax.swing.JComboBox();
        jComboBoxPosture = new javax.swing.JComboBox();
        jComboBoxAlblumin = new javax.swing.JComboBox();
        jComboBoxUrineLevel = new javax.swing.JComboBox();
        jPanel27 = new javax.swing.JPanel();
        jLabelSoundHeart = new javax.swing.JLabel();
        jRadioSoundHeart0 = new javax.swing.JRadioButton();
        jRadioSoundHeart1 = new javax.swing.JRadioButton();
        jPanel23 = new javax.swing.JPanel();
        jLabelPulse = new javax.swing.JLabel();
        jTextFieldPulse = new com.hospital_os.utility.DoubleTextField();
        jLabelPulseUnit = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jRadioButtonAncNoTreat = new javax.swing.JRadioButton();
        jRadioButtonAncNormal = new javax.swing.JRadioButton();
        jRadioButtonAncAbnormal = new javax.swing.JRadioButton();
        jRadioButtonAncWaitResult = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaAncExamDescription = new com.hosv3.gui.component.BalloonTextArea();
        jLabelRemark1 = new javax.swing.JLabel();
        jTextFieldNoteRemark = new javax.swing.JTextField();
        jPanel30 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        dateComboBoxCheck = new com.hospital_os.utility.DateComboBox();
        jLabel12 = new javax.swing.JLabel();
        timeTextFieldCheck = new com.hospital_os.utility.TimeTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabelVNAnc = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        jTabbedPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTabbedPanePropertyChange(evt);
            }
        });

        jPanelDataSub1.setLayout(new java.awt.GridBagLayout());

        jPanelAncSurvey1.setLayout(new java.awt.GridBagLayout());

        dateComboBoxAncSurveyDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dateComboBoxAncSurveyDateMouseReleased(evt);
            }
        });
        dateComboBoxAncSurveyDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxAncSurveyDateActionPerformed(evt);
            }
        });
        dateComboBoxAncSurveyDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateComboBoxAncSurveyDateFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanelAncSurvey1.add(dateComboBoxAncSurveyDate, gridBagConstraints);

        jLabelNextAppDate.setText("CheckTime");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelAncSurvey1.add(jLabelNextAppDate, gridBagConstraints);

        integerTextFieldNo.setColumns(2);
        integerTextFieldNo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldNo.setMinimumSize(new java.awt.Dimension(30, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelAncSurvey1.add(integerTextFieldNo, gridBagConstraints);

        jLabelAgOfPre.setText("AgOfPregnant");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelAncSurvey1.add(jLabelAgOfPre, gridBagConstraints);

        integerTextFieldDay.setText("AgeWeek");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelAncSurvey1.add(integerTextFieldDay, gridBagConstraints);

        jLabelSurveyDate.setText("SurveyDate");
        jLabelSurveyDate.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jLabelSurveyDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabelSurveyDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanelAncSurvey1.add(jLabelSurveyDate, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 0, 11);
        jPanelDataSub1.add(jPanelAncSurvey1, gridBagConstraints);

        jPanelData.setLayout(new java.awt.GridBagLayout());

        jLabelAncRange.setText("AncRange");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelData.add(jLabelAncRange, gridBagConstraints);

        jComboBoxAncRange.setBackground(new java.awt.Color(204, 255, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jPanelData.add(jComboBoxAncRange, gridBagConstraints);

        jLabelHighRisk.setText("ภาวะเสี่ยง");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelData.add(jLabelHighRisk, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 6, 0, 0);
        jPanelData.add(jComboBoxHighRisk, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 10);
        jPanelDataSub1.add(jPanelData, gridBagConstraints);

        jPanelCheckAnyRS.setLayout(new java.awt.GridBagLayout());

        jLabelTartar.setText("มีหินน้ำลาย");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelCheckAnyRS.add(jLabelTartar, gridBagConstraints);

        jLabelDental.setText("ตรวจสุขภาพฟัน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelCheckAnyRS.add(jLabelDental, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabelTCaries.setText("TCaries");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jLabelTCaries, gridBagConstraints);

        integerTextFieldTCaries.setBackground(new java.awt.Color(204, 255, 255));
        integerTextFieldTCaries.setColumns(2);
        integerTextFieldTCaries.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldTCaries.setText("0");
        integerTextFieldTCaries.setMinimumSize(new java.awt.Dimension(100, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 0, 0);
        jPanel5.add(integerTextFieldTCaries, gridBagConstraints);

        jLabelN1.setText("n");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jPanel5.add(jLabelN1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelCheckAnyRS.add(jPanel5, gridBagConstraints);

        jPanel16.setBackground(new java.awt.Color(204, 255, 255));
        jPanel16.setLayout(new java.awt.GridBagLayout());

        jRadioButtonDental1.setBackground(new java.awt.Color(204, 255, 255));
        buttonGroupDantal.add(jRadioButtonDental1);
        jRadioButtonDental1.setText("Yes");
        jRadioButtonDental1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButtonDental1MouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel16.add(jRadioButtonDental1, gridBagConstraints);

        jRadioButtonDental0.setBackground(new java.awt.Color(204, 255, 255));
        buttonGroupDantal.add(jRadioButtonDental0);
        jRadioButtonDental0.setSelected(true);
        jRadioButtonDental0.setText("Not");
        jRadioButtonDental0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jRadioButtonDental0MouseReleased(evt);
            }
        });
        jRadioButtonDental0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonDental0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel16.add(jRadioButtonDental0, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 0, 0);
        jPanelCheckAnyRS.add(jPanel16, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jRadioButtonGumInf1.setBackground(new java.awt.Color(204, 255, 255));
        buttonGroupGumInf.add(jRadioButtonGumInf1);
        jRadioButtonGumInf1.setText("Yes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel4.add(jRadioButtonGumInf1, gridBagConstraints);

        jRadioButtonGumInf0.setBackground(new java.awt.Color(204, 255, 255));
        buttonGroupGumInf.add(jRadioButtonGumInf0);
        jRadioButtonGumInf0.setSelected(true);
        jRadioButtonGumInf0.setText("Not");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel4.add(jRadioButtonGumInf0, gridBagConstraints);

        jLabelGumInf.setText("เหงือกอักเสบ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(jLabelGumInf, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 0, 0);
        jPanelCheckAnyRS.add(jPanel4, gridBagConstraints);

        jPanel18.setBackground(new java.awt.Color(204, 255, 255));
        jPanel18.setLayout(new java.awt.GridBagLayout());

        jRadioButtonTartar0.setBackground(new java.awt.Color(204, 255, 255));
        buttonGroupTartar.add(jRadioButtonTartar0);
        jRadioButtonTartar0.setSelected(true);
        jRadioButtonTartar0.setText("Not");
        jPanel18.add(jRadioButtonTartar0, new java.awt.GridBagConstraints());

        jRadioButtonTartar1.setBackground(new java.awt.Color(204, 255, 255));
        buttonGroupTartar.add(jRadioButtonTartar1);
        jRadioButtonTartar1.setText("Yes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        jPanel18.add(jRadioButtonTartar1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelCheckAnyRS.add(jPanel18, gridBagConstraints);

        jLabelReceiveTT.setText("รับวัคซีน TT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelCheckAnyRS.add(jLabelReceiveTT, gridBagConstraints);

        jPanel21.setLayout(new java.awt.GridBagLayout());

        buttonGroupReceiveTT.add(jRadioReceiveTT0);
        jRadioReceiveTT0.setText("NotGet");
        jRadioReceiveTT0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioReceiveTT0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel21.add(jRadioReceiveTT0, gridBagConstraints);

        buttonGroupReceiveTT.add(jRadioReceiveTT1);
        jRadioReceiveTT1.setSelected(true);
        jRadioReceiveTT1.setText("Get");
        jRadioReceiveTT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioReceiveTT1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel21.add(jRadioReceiveTT1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelCheckAnyRS.add(jPanel21, gridBagConstraints);

        jPanel26.setLayout(new java.awt.GridBagLayout());

        buttonTT.add(jRadioButtonTT2);
        jRadioButtonTT2.setText("TT_2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel26.add(jRadioButtonTT2, gridBagConstraints);

        buttonTT.add(jRadioButtonTT1);
        jRadioButtonTT1.setSelected(true);
        jRadioButtonTT1.setText("TT_1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel26.add(jRadioButtonTT1, gridBagConstraints);

        jLabelTT_R.setText("TT_R");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel26.add(jLabelTT_R, gridBagConstraints);

        buttonTT.add(jRadioButtonTT3);
        jRadioButtonTT3.setText("TT_3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel26.add(jRadioButtonTT3, gridBagConstraints);

        buttonTT.add(jRadioButtonTT4);
        jRadioButtonTT4.setText("TT_4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel26.add(jRadioButtonTT4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanelCheckAnyRS.add(jPanel26, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabelWeight.setText("NtrWeight");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel7.add(jLabelWeight, gridBagConstraints);

        jLabelKgs.setText("kgs");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jLabelKgs, gridBagConstraints);

        jLabelHigh.setText("NtrHigh");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jLabelHigh, gridBagConstraints);

        jLabelcms.setText("cms");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jLabelcms, gridBagConstraints);

        jLabelBMI.setText("BMI");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jLabelBMI, gridBagConstraints);

        doubleTextFieldBMI.setColumns(4);
        doubleTextFieldBMI.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        doubleTextFieldBMI.setMinimumSize(new java.awt.Dimension(50, 21));
        doubleTextFieldBMI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                doubleTextFieldBMIKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(doubleTextFieldBMI, gridBagConstraints);

        integerTextFieldWeight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldWeight.setMinimumSize(new java.awt.Dimension(40, 21));
        integerTextFieldWeight.setPreferredSize(new java.awt.Dimension(40, 21));
        integerTextFieldWeight.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                integerTextFieldWeightFocusGained(evt);
            }
        });
        integerTextFieldWeight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldWeightKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(integerTextFieldWeight, gridBagConstraints);

        integerTextFieldHigh.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        integerTextFieldHigh.setMinimumSize(new java.awt.Dimension(30, 21));
        integerTextFieldHigh.setPreferredSize(new java.awt.Dimension(35, 21));
        integerTextFieldHigh.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                integerTextFieldHighFocusLost(evt);
            }
        });
        integerTextFieldHigh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                integerTextFieldHighKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(integerTextFieldHigh, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelCheckAnyRS.add(jPanel7, gridBagConstraints);

        jPanel20.setLayout(new java.awt.GridBagLayout());

        jLabelPressure.setText("Pressure");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel20.add(jLabelPressure, gridBagConstraints);

        jTextFieldPressure1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldPressure1.setMinimumSize(new java.awt.Dimension(30, 21));
        jTextFieldPressure1.setPreferredSize(new java.awt.Dimension(35, 21));
        jTextFieldPressure1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPressure1KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel20.add(jTextFieldPressure1, gridBagConstraints);

        jLabel4.setText("/");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel20.add(jLabel4, gridBagConstraints);

        jTextFieldPressure2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldPressure2.setMinimumSize(new java.awt.Dimension(30, 21));
        jTextFieldPressure2.setPreferredSize(new java.awt.Dimension(35, 21));
        jTextFieldPressure2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPressure2KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel20.add(jTextFieldPressure2, gridBagConstraints);

        jLabelPresUnit.setText("mmHg");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel20.add(jLabelPresUnit, gridBagConstraints);

        jCheckBoxAncOtherOffice.setText("AncOtherOffice");
        jCheckBoxAncOtherOffice.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel20.add(jCheckBoxAncOtherOffice, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelCheckAnyRS.add(jPanel20, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 10);
        jPanelDataSub1.add(jPanelCheckAnyRS, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabelVDR.setText("VDRL_RS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jLabelVDR, gridBagConstraints);

        jComboBoxVDRL.setBackground(new java.awt.Color(204, 255, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel3.add(jComboBoxVDRL, gridBagConstraints);

        jLableHB.setText("HB_RS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel3.add(jLableHB, gridBagConstraints);

        jComboBoxHB.setBackground(new java.awt.Color(204, 255, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel3.add(jComboBoxHB, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabelCheckHCTDate.setText("CheckHCTDate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel6.add(jLabelCheckHCTDate, gridBagConstraints);

        dateComboBoxCheckHCTDate.setBackground(new java.awt.Color(204, 255, 255));
        dateComboBoxCheckHCTDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxCheckHCTDateActionPerformed(evt);
            }
        });
        dateComboBoxCheckHCTDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateComboBoxCheckHCTDateFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel6.add(dateComboBoxCheckHCTDate, gridBagConstraints);

        jLabelHCT_R.setText("HCT_R");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel6.add(jLabelHCT_R, gridBagConstraints);

        jLabel5.setText("%");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel6.add(jLabel5, gridBagConstraints);

        percentTextHCT_R.setBackground(new java.awt.Color(204, 255, 255));
        percentTextHCT_R.setColumns(2);
        percentTextHCT_R.setMinimumSize(new java.awt.Dimension(26, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel6.add(percentTextHCT_R, gridBagConstraints);

        jComboBoxHCT.setBackground(new java.awt.Color(204, 255, 255));
        jComboBoxHCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxHCTActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel6.add(jComboBoxHCT, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel3.add(jPanel6, gridBagConstraints);

        jLabelANCRES.setText("สรุปผลการตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 0, 0);
        jPanel3.add(jLabelANCRES, gridBagConstraints);

        jComboBoxANCRES.setBackground(new java.awt.Color(204, 255, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel3.add(jComboBoxANCRES, gridBagConstraints);

        jLabelBabyTHALAS.setText("THALASSAEMIA ในลูก");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 0, 0);
        jPanel3.add(jLabelBabyTHALAS, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel3.add(jComboBoxBabyTHALAS, gridBagConstraints);

        jLableHIV.setText("HIV_RS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel3.add(jLableHIV, gridBagConstraints);

        jComboBoxHIV.setBackground(new java.awt.Color(204, 255, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel3.add(jComboBoxHIV, gridBagConstraints);

        jLabelTHALAS.setText("THALASSAEMIA ในแม่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 0, 0);
        jPanel3.add(jLabelTHALAS, gridBagConstraints);

        jComboBoxTHALAS.setBackground(new java.awt.Color(204, 255, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel3.add(jComboBoxTHALAS, gridBagConstraints);

        jLabelHCT.setText("HCT_RS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(jLabelHCT, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        jPanelDataSub1.add(jPanel3, gridBagConstraints);

        jTabbedPane.addTab("Data1", jPanelDataSub1);

        jPanelDataSub2.setLayout(new java.awt.GridBagLayout());

        jLabelHeadache.setText("Headache");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanelDataSub2.add(jLabelHeadache, gridBagConstraints);

        jLabelMovingBaby.setText("Moving_baby");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanelDataSub2.add(jLabelMovingBaby, gridBagConstraints);

        jLabelBloodInVag.setText("Blood_in_vagina");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanelDataSub2.add(jLabelBloodInVag, gridBagConstraints);

        jLabelHeartFail.setText("Heart_failure");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanelDataSub2.add(jLabelHeartFail, gridBagConstraints);

        jLabelSqueamish.setText("Squeamish");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanelDataSub2.add(jLabelSqueamish, gridBagConstraints);

        jLabelRisk.setText("Risk");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanelDataSub2.add(jLabelRisk, gridBagConstraints);

        jLabelThiroil.setText("ต่อมไทรอยด์โต");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 0, 0);
        jPanelDataSub2.add(jLabelThiroil, gridBagConstraints);

        jLabelSwell.setText("Swell_of_mother");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanelDataSub2.add(jLabelSwell, gridBagConstraints);

        jLabelMenstru.setText("Menstruation");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanelDataSub2.add(jLabelMenstru, gridBagConstraints);

        jLabelGiveBirth.setText("Give_birth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 11, 0, 0);
        jPanelDataSub2.add(jLabelGiveBirth, gridBagConstraints);

        jLabelCramp.setText("Cramp");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 15, 0, 0);
        jPanelDataSub2.add(jLabelCramp, gridBagConstraints);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        buttonGroupCramp.add(jRadioCramp0);
        jRadioCramp0.setText("NotHave");
        jPanel9.add(jRadioCramp0, new java.awt.GridBagConstraints());

        buttonGroupCramp.add(jRadioCramp1);
        jRadioCramp1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel9.add(jRadioCramp1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 5, 0, 0);
        jPanelDataSub2.add(jPanel9, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        buttonGroupHeart_failure.add(jRadioHeart0);
        jRadioHeart0.setText("NotHave");
        jPanel10.add(jRadioHeart0, new java.awt.GridBagConstraints());

        buttonGroupHeart_failure.add(jRadioHeart1);
        jRadioHeart1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel10.add(jRadioHeart1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelDataSub2.add(jPanel10, gridBagConstraints);

        jPanel11.setLayout(new java.awt.GridBagLayout());

        buttonGroupSwell_of_mother.add(jRadioSwell0);
        jRadioSwell0.setText("NotHave");
        jPanel11.add(jRadioSwell0, new java.awt.GridBagConstraints());

        buttonGroupSwell_of_mother.add(jRadioSwell1);
        jRadioSwell1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel11.add(jRadioSwell1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelDataSub2.add(jPanel11, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        buttonGroupSqueamish.add(jRadioSqueamish0);
        jRadioSqueamish0.setText("NotHave");
        jPanel12.add(jRadioSqueamish0, new java.awt.GridBagConstraints());

        buttonGroupSqueamish.add(jRadioSqueamish1);
        jRadioSqueamish1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel12.add(jRadioSqueamish1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelDataSub2.add(jPanel12, gridBagConstraints);

        jPanel13.setLayout(new java.awt.GridBagLayout());

        buttonGroupRisk.add(jRadioRisk0);
        jRadioRisk0.setText("NotHave");
        jPanel13.add(jRadioRisk0, new java.awt.GridBagConstraints());

        buttonGroupRisk.add(jRadioRisk1);
        jRadioRisk1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel13.add(jRadioRisk1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelDataSub2.add(jPanel13, gridBagConstraints);

        jPanel14.setLayout(new java.awt.GridBagLayout());

        buttonGroupBlood_in_vagina.add(jRadioBlood0);
        jRadioBlood0.setText("NotHave");
        jPanel14.add(jRadioBlood0, new java.awt.GridBagConstraints());

        buttonGroupBlood_in_vagina.add(jRadioBlood1);
        jRadioBlood1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel14.add(jRadioBlood1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelDataSub2.add(jPanel14, gridBagConstraints);

        jPanel15.setLayout(new java.awt.GridBagLayout());

        buttonGroupMenstruation.add(jRadioMenstruation0);
        jRadioMenstruation0.setText("NotHave");
        jPanel15.add(jRadioMenstruation0, new java.awt.GridBagConstraints());

        buttonGroupMenstruation.add(jRadioMenstruation1);
        jRadioMenstruation1.setText("Have");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel15.add(jRadioMenstruation1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelDataSub2.add(jPanel15, gridBagConstraints);

        jPanel17.setLayout(new java.awt.GridBagLayout());

        buttonGroupThiroil.add(jRadioThiroil0);
        jRadioThiroil0.setText("Not");
        jPanel17.add(jRadioThiroil0, new java.awt.GridBagConstraints());

        buttonGroupThiroil.add(jRadioThiroil1);
        jRadioThiroil1.setText("Yes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel17.add(jRadioThiroil1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelDataSub2.add(jPanel17, gridBagConstraints);

        jPanel19.setLayout(new java.awt.GridBagLayout());

        buttonGroupMoving_baby.add(jRadioMovingBaby0);
        jRadioMovingBaby0.setText("Not");
        jPanel19.add(jRadioMovingBaby0, new java.awt.GridBagConstraints());

        buttonGroupMoving_baby.add(jRadioMovingBaby1);
        jRadioMovingBaby1.setText("Yes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel19.add(jRadioMovingBaby1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelDataSub2.add(jPanel19, gridBagConstraints);

        buttonGroupGive_birth.add(jRadioGiveBirth0);
        jRadioGiveBirth0.setText("NotGive");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 5, 0, 0);
        jPanelDataSub2.add(jRadioGiveBirth0, gridBagConstraints);

        buttonGroupGive_birth.add(jRadioGiveBirth1);
        jRadioGiveBirth1.setText("GiveBirth");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelDataSub2.add(jRadioGiveBirth1, gridBagConstraints);

        buttonGroupHeadache.add(jRadioHeadache0);
        jRadioHeadache0.setText("NotAche");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelDataSub2.add(jRadioHeadache0, gridBagConstraints);

        buttonGroupHeadache.add(jRadioHeadache1);
        jRadioHeadache1.setText("Ache");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelDataSub2.add(jRadioHeadache1, gridBagConstraints);

        jPanel22.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("/");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel22.add(jLabel1, gridBagConstraints);

        jLabel2.setText("cm");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel22.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel22.add(jComboBoxUterusLevel, gridBagConstraints);

        doubleTextFieldUterusCm.setColumns(4);
        doubleTextFieldUterusCm.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        doubleTextFieldUterusCm.setMinimumSize(new java.awt.Dimension(48, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel22.add(doubleTextFieldUterusCm, gridBagConstraints);

        jLabelUterus.setText("Uterus_level");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel22.add(jLabelUterus, gridBagConstraints);

        jLabelConduct.setText("Conduct");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel22.add(jLabelConduct, gridBagConstraints);

        jLabelSugar.setText("Sugar_in_urine");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel22.add(jLabelSugar, gridBagConstraints);

        jLabelPosture.setText("Posture_baby");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel22.add(jLabelPosture, gridBagConstraints);

        jLabelAlblumin.setText("ระดับอัลบูมิน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel22.add(jLabelAlblumin, gridBagConstraints);

        jLabelUrine.setText("Urine_level");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel22.add(jLabelUrine, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel22.add(jComboBoxConduct, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel22.add(jComboBoxSugar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel22.add(jComboBoxPosture, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel22.add(jComboBoxAlblumin, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel22.add(jComboBoxUrineLevel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        jPanelDataSub2.add(jPanel22, gridBagConstraints);

        jPanel27.setLayout(new java.awt.GridBagLayout());

        jLabelSoundHeart.setText("Sound_heart");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel27.add(jLabelSoundHeart, gridBagConstraints);

        buttonGroupSound_heart.add(jRadioSoundHeart0);
        jRadioSoundHeart0.setText("NotHear");
        jRadioSoundHeart0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioSoundHeart0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel27.add(jRadioSoundHeart0, gridBagConstraints);

        buttonGroupSound_heart.add(jRadioSoundHeart1);
        jRadioSoundHeart1.setText("Hear");
        jRadioSoundHeart1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioSoundHeart1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel27.add(jRadioSoundHeart1, gridBagConstraints);

        jPanel23.setLayout(new java.awt.GridBagLayout());

        jLabelPulse.setText("FHS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel23.add(jLabelPulse, gridBagConstraints);

        jTextFieldPulse.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldPulse.setMinimumSize(new java.awt.Dimension(45, 21));
        jTextFieldPulse.setPreferredSize(new java.awt.Dimension(45, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel23.add(jTextFieldPulse, gridBagConstraints);

        jLabelPulseUnit.setText("n/m");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel23.add(jLabelPulseUnit, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel27.add(jPanel23, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 5, 0);
        jPanelDataSub2.add(jPanel27, gridBagConstraints);

        jTabbedPane.addTab("Data2", jPanelDataSub2);

        jPanel28.setLayout(new java.awt.GridBagLayout());

        jPanel25.setBackground(new java.awt.Color(204, 255, 255));
        jPanel25.setLayout(new java.awt.GridBagLayout());

        jRadioButtonAncNoTreat.setBackground(new java.awt.Color(204, 255, 255));
        buttonGroupResultAnc.add(jRadioButtonAncNoTreat);
        jRadioButtonAncNoTreat.setText("NoTreat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel25.add(jRadioButtonAncNoTreat, gridBagConstraints);

        jRadioButtonAncNormal.setBackground(new java.awt.Color(204, 255, 255));
        buttonGroupResultAnc.add(jRadioButtonAncNormal);
        jRadioButtonAncNormal.setText("Normal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel25.add(jRadioButtonAncNormal, gridBagConstraints);

        jRadioButtonAncAbnormal.setBackground(new java.awt.Color(204, 255, 255));
        buttonGroupResultAnc.add(jRadioButtonAncAbnormal);
        jRadioButtonAncAbnormal.setText("Abnormal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel25.add(jRadioButtonAncAbnormal, gridBagConstraints);

        jRadioButtonAncWaitResult.setBackground(new java.awt.Color(204, 255, 255));
        buttonGroupResultAnc.add(jRadioButtonAncWaitResult);
        jRadioButtonAncWaitResult.setText("WaitResult");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel25.add(jRadioButtonAncWaitResult, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 11, 0, 0);
        jPanel28.add(jPanel25, gridBagConstraints);

        jScrollPane1.setViewportView(jTextAreaAncExamDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 5, 0, 10);
        jPanel28.add(jScrollPane1, gridBagConstraints);

        jLabelRemark1.setText("Remark");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        jPanel28.add(jLabelRemark1, gridBagConstraints);

        jTextFieldNoteRemark.setMinimumSize(new java.awt.Dimension(100, 19));
        jTextFieldNoteRemark.setPreferredSize(new java.awt.Dimension(100, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 0, 10);
        jPanel28.add(jTextFieldNoteRemark, gridBagConstraints);

        jPanel30.setLayout(new java.awt.GridBagLayout());

        jLabel9.setText("วันที่บันทึก");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 3);
        jPanel30.add(jLabel9, gridBagConstraints);

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
        jPanel30.add(dateComboBoxCheck, gridBagConstraints);

        jLabel12.setText("น.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 1);
        jPanel30.add(jLabel12, gridBagConstraints);

        timeTextFieldCheck.setMinimumSize(new java.awt.Dimension(45, 23));
        timeTextFieldCheck.setPreferredSize(new java.awt.Dimension(45, 23));
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
        jPanel30.add(timeTextFieldCheck, gridBagConstraints);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/clock.gif"))); // NOI18N
        jLabel13.setToolTipText("เวลาที่ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 3);
        jPanel30.add(jLabel13, gridBagConstraints);

        jLabelVNAnc.setText("VN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel30.add(jLabelVNAnc, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 10, 0);
        jPanel28.add(jPanel30, gridBagConstraints);

        jTabbedPane.addTab("Data3", jPanel28);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jTabbedPane, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void dateComboBoxAncSurveyDateMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateComboBoxAncSurveyDateMouseReleased
    }//GEN-LAST:event_dateComboBoxAncSurveyDateMouseReleased

    private void jLabelSurveyDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLabelSurveyDateActionPerformed
        this.dateComboBoxAncSurveyDate.setEnabled(jLabelSurveyDate.isSelected());
        if (!jLabelSurveyDate.isSelected()) {
            dateComboBoxAncSurveyDate.setText("");
        }
    }//GEN-LAST:event_jLabelSurveyDateActionPerformed

    private void jTabbedPanePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTabbedPanePropertyChange
// TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPanePropertyChange

    private void timeTextFieldCheckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timeTextFieldCheckKeyReleased
// TODO add your handling code here:
    }//GEN-LAST:event_timeTextFieldCheckKeyReleased

    private void timeTextFieldCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeTextFieldCheckMouseClicked
// TODO add your handling code here:
    }//GEN-LAST:event_timeTextFieldCheckMouseClicked

    private void dateComboBoxCheckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateComboBoxCheckKeyReleased
// TODO add your handling code here:
    }//GEN-LAST:event_dateComboBoxCheckKeyReleased

    private void jRadioSoundHeart1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioSoundHeart1ActionPerformed
        if (jRadioSoundHeart1.isSelected()) {
            jTextFieldPulse.setEnabled(true);
        }
    }//GEN-LAST:event_jRadioSoundHeart1ActionPerformed

    private void jRadioSoundHeart0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioSoundHeart0ActionPerformed
        if (jRadioSoundHeart0.isSelected()) {
            jTextFieldPulse.setEnabled(false);
            jTextFieldPulse.setText("");
        } else {
            jTextFieldPulse.setEnabled(true);
        }
    }//GEN-LAST:event_jRadioSoundHeart0ActionPerformed

    private void jComboBoxHCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxHCTActionPerformed
        dateComboBoxCheckHCTDate.setEnabled(false);
        percentTextHCT_R.setEnabled(false);
        String hct = Gutil.getGuiData(jComboBoxHCT);
        if (hct.equals("1") || hct.equals("2")) {
            dateComboBoxCheckHCTDate.setEnabled(true);
            percentTextHCT_R.setEnabled(true);
        }
    }//GEN-LAST:event_jComboBoxHCTActionPerformed

    private void dateComboBoxCheckHCTDateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxCheckHCTDateFocusLost
    }//GEN-LAST:event_dateComboBoxCheckHCTDateFocusLost

    private void dateComboBoxCheckHCTDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxCheckHCTDateActionPerformed
    }//GEN-LAST:event_dateComboBoxCheckHCTDateActionPerformed

    /*กรองการพิมพ์ตัวอักษรในช่องว่างให้ไม่เกินจำนวนที่กำหนด*/
    private static void FilterTextKey(javax.swing.JTextField jtf, int num) {
        if (jtf.getText().length() > num) {
            jtf.setText(jtf.getText().substring(1, num + 1));
        }
        //if(jtf.getText().length()==num)
        //  jtf.transferFocus();
    }
    private void jTextFieldPressure2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPressure2KeyReleased
        FilterTextKey(jTextFieldPressure2, 3);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            doubleTextFieldBMI.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldPressure2KeyReleased

    private void jTextFieldPressure1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPressure1KeyReleased
        FilterTextKey(jTextFieldPressure1, 3);
        String press1 = jTextFieldPressure1.getText();
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_ENTER || press1.length() == 3) {
            jTextFieldPressure2.requestFocus();
        }
    }//GEN-LAST:event_jTextFieldPressure1KeyReleased

    private void integerTextFieldHighKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldHighKeyReleased
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            jTextFieldPressure1.requestFocus();
        }
    }//GEN-LAST:event_integerTextFieldHighKeyReleased

    /**
     * คำนวนหาค่า BMI
     * @author jao
     */
    public static String calculateBMI(String weight, String hight) {
        float bmi = 0;
        if (!weight.equals("") && !hight.equals("")) {
            float w = Float.parseFloat(weight);
            float h = Float.parseFloat(hight);
            bmi = (w * 10000) / (h * h);
        }
        DecimalFormat dec = new DecimalFormat("00.00");
        return String.valueOf(dec.format(bmi));
    }
    private void integerTextFieldHighFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integerTextFieldHighFocusLost
        String bmi = calculateBMI(integerTextFieldWeight.getText(), integerTextFieldHigh.getText());
        doubleTextFieldBMI.setText(bmi);
    }//GEN-LAST:event_integerTextFieldHighFocusLost

    private void integerTextFieldWeightKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldWeightKeyReleased
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            integerTextFieldHigh.requestFocus();
        }
    }//GEN-LAST:event_integerTextFieldWeightKeyReleased

    /**
     * นำ Vitalsign จากหน้าบันทึกอาการเจ็บป่วยมา
     * @auther jao
     */
    private void importVitalSign() {

        if (pcuobject.getVisit() == null) {
            return;
        }

        Vector vVitalSign = theHealthServiceControl.listVitalSign(pcuobject.getVisit().getObjectId());
        if (vVitalSign.isEmpty()) {
            return;
        }

        int i = vVitalSign.size();
        VitalSign vts = (VitalSign) vVitalSign.get(0);
        integerTextFieldWeight.setText(vts.weight);
        integerTextFieldHigh.setText(vts.height);
        doubleTextFieldBMI.setText(vts.bmi);
        jTextFieldPulse.setText(vts.puls);
        if (vts.pressure != null && !("").equals(vts.pressure)) {
            String pressure[] = vts.pressure.split("/");
            if (pressure.length > 1) {
                jTextFieldPressure1.setText(pressure[0]);
                jTextFieldPressure2.setText(pressure[1]);
            }
        }
    }

    private void integerTextFieldWeightFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integerTextFieldWeightFocusGained
        importVitalSign();
    }//GEN-LAST:event_integerTextFieldWeightFocusGained

    private void doubleTextFieldBMIKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_doubleTextFieldBMIKeyReleased
    }//GEN-LAST:event_doubleTextFieldBMIKeyReleased

    /**
     * เมื่อมีการเลือกวัคซีนTT
     * @param  -
     * @return void
     * @authur kingland
     * @date 14/08/2549
     */
    public void selectTT() {
        if (jRadioReceiveTT1.isSelected()) {
            jRadioButtonTT1.setEnabled(true);
            jRadioButtonTT2.setEnabled(true);
            jRadioButtonTT3.setEnabled(true);
            jRadioButtonTT4.setEnabled(true);
        } else {
            jRadioButtonTT1.setEnabled(false);
            jRadioButtonTT2.setEnabled(false);
            jRadioButtonTT3.setEnabled(false);
            jRadioButtonTT4.setEnabled(false);
        }
    }
    private void jRadioReceiveTT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioReceiveTT1ActionPerformed
        selectTT();
    }//GEN-LAST:event_jRadioReceiveTT1ActionPerformed

    private void jRadioReceiveTT0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioReceiveTT0ActionPerformed
        selectTT();
    }//GEN-LAST:event_jRadioReceiveTT0ActionPerformed

    /**
     *@author jao
     *@modify kingland
     *@date 04/09/2549
     */
    public void selectDental() {
        if (jRadioButtonDental1.isSelected()) {
            jRadioButtonGumInf1.setEnabled(true);
            jRadioButtonGumInf0.setEnabled(true);
            jRadioButtonTartar0.setEnabled(true);
            jRadioButtonTartar1.setEnabled(true);
            integerTextFieldTCaries.setEnabled(true);
        } else {
            jRadioButtonGumInf1.setEnabled(false);
            jRadioButtonGumInf0.setEnabled(false);
            integerTextFieldTCaries.setText("0");
            jRadioButtonGumInf0.setSelected(true);
            jRadioButtonTartar0.setEnabled(false);
            jRadioButtonTartar1.setEnabled(false);
            jRadioButtonTartar0.setSelected(true);
            integerTextFieldTCaries.setEnabled(false);
        }
    }
    private void jRadioButtonDental0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonDental0ActionPerformed
        selectDental();
    }//GEN-LAST:event_jRadioButtonDental0ActionPerformed

    private void jRadioButtonDental0MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonDental0MouseReleased
        selectDental();
    }//GEN-LAST:event_jRadioButtonDental0MouseReleased

    private void jRadioButtonDental1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonDental1MouseReleased
        selectDental();
    }//GEN-LAST:event_jRadioButtonDental1MouseReleased

    private void dateComboBoxAncSurveyDateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateComboBoxAncSurveyDateFocusLost
        checkSurveyAnc = false;
    }//GEN-LAST:event_dateComboBoxAncSurveyDateFocusLost

    /**
     * ตรวจสอบว่าวันออกตรวจเป็นวันในอนาคตหรือไม่
     * @param  -
     * @return -
     * @author kingland
     * @date 17-03-2549
     */
    private void checkDateSurvayAnc() {
        if (!dateComboBoxAncSurveyDate.getText().equals("") && dateComboBoxAncSurveyDate.getText().length() == 10 && com.pcu.utility.DateUtil.countDay(dateComboBoxAncSurveyDate.getText(), theHosManage.theHosControl.theConnectionInf) == -1 && com.pcu.utility.DateUtil.isToday(com.pcu.utility.DateUtil.getDateFromText(
                dateComboBoxAncSurveyDate.getText()), theHosManage.theHosControl.theConnectionInf) == false) {
            if (checkSurveyAnc == false) {
                // ไม่สามารถกรอกวันที่เป็นวันในอนาคตได้
                theUS.setStatus(GutilPCU.getTextBundle("NoDateFuture"), UpdateStatus.WARNING);
                dateComboBoxAncSurveyDate.setText("");
                checkSurveyAnc = true;
            }
        }
        if (checkSurveyAnc) {
            dateComboBoxAncSurveyDate.setText("");
        }

        String date_ref = pcuobject.getCurrentDateTime();
//        if(!dateComboBoxAncSurveyDate.getText().equals(""))
//            date_ref = dateComboBoxAncSurveyDate.getText();

        if (jLabelSurveyDate.isSelected()) {
            date_ref = dateComboBoxAncSurveyDate.getText();
        } else if (!dateComboBoxCheck.getText().equals("")) {
            date_ref = dateComboBoxCheck.getText() + "," + timeTextFieldCheck.getText();
        }
 //henbe comment 100253 kong เพราะอะไรถึงทำฟังชันนี้ของเดิมที่มีอยุ่ใช้ไม่ได้หรือ หรือผิด
 //henbe comment 100253 kong จะจัดโค้ดก็ต้องทำให้ครบทุกไฟลทุกคลาส มาแก้เฉพาะหน้าอย่างนี้ไม่ได้ต้องเข้าที่ประชุม
        //แล้วการตัดบรรทัดก็หาม ต่อยาวดูไม่รู้เรื่องต้อง scroll ไปมาหลายรอบอ่านยากเข้าใจไหม
        String age_week = DateUtil.countWeekS(thePregnancy.pregnancy_menses_issue_date, date_ref);
        this.integerTextFieldDay.setText(age_week);
        if (from_user) {
            setSectionSet(DateUtil.countWeek(thePregnancy.pregnancy_menses_issue_date, date_ref));
        }
    }
    private void dateComboBoxAncSurveyDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxAncSurveyDateActionPerformed
        System.out.println("private void dateComboBoxAncSurveyDateActionPerformed");
        checkDateSurvayAnc();
    }//GEN-LAST:event_dateComboBoxAncSurveyDateActionPerformed

    boolean checkSaveAncPcu() {

        if (integerTextFieldNo.getText().trim().equals("")) {
            theUS.setStatus(GutilPCU.getTextBundle("AncNoIsNull"), UpdateStatus.WARNING);
            integerTextFieldNo.requestFocus();
            return false;
        }
        if (Integer.parseInt(theAncPcu.anc_gravida_week) > 40) {
            if (!theUS.confirmBox(GutilPCU.getTextBundle("AgeIsOver40Week"), UpdateStatus.WARNING)) {
                return false;
            }
        }
        String numBadRealTooth = integerTextFieldTCaries.getText();
        String errorMsg = "";
        try {
            if ((!numBadRealTooth.equals("") && Integer.parseInt(numBadRealTooth) > 32)) {
                errorMsg += GutilPCU.getTextBundle("WarningCheckRealTooth") + "\r\n";
            }
            if (!("").equals(errorMsg)) {
                theUS.setStatus(errorMsg, UpdateStatus.WARNING);
                return false;
            }
        } catch (NumberFormatException e) {
            Constant.println("Exception in PanelHealthSchool => NumberFormatException");
        }

        if (!"".equals(theAncPcu.getObjectId()) && !"".equals(theAncPcu.survey_date)) {
            from_user = false;
            String survey_date = dateComboBoxAncSurveyDate.getText();
            from_user = true;
            if ("".equals(survey_date)) {
                theUS.setStatus(GutilPCU.getTextBundle("WarningNotHaveSurVeyDate"), UpdateStatus.WARNING);
                return false;
            }
        }
        String anctime = integerTextFieldNo.getText().trim();
        if (anctime.equals("0")) {
            integerTextFieldNo.setText("");
            integerTextFieldNo.requestFocus();
            theUS.setStatus("ข้อมูลครั้งที่ตรวจครรถ์ไม่ควรเป็น 0", UpdateStatus.WARNING);
            return false;
        }
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupBlood_in_vagina;
    private javax.swing.ButtonGroup buttonGroupCramp;
    private javax.swing.ButtonGroup buttonGroupDantal;
    private javax.swing.ButtonGroup buttonGroupGive_birth;
    private javax.swing.ButtonGroup buttonGroupGumInf;
    private javax.swing.ButtonGroup buttonGroupHeadache;
    private javax.swing.ButtonGroup buttonGroupHeart_failure;
    private javax.swing.ButtonGroup buttonGroupMenstruation;
    private javax.swing.ButtonGroup buttonGroupMoving_baby;
    private javax.swing.ButtonGroup buttonGroupReceiveTT;
    private javax.swing.ButtonGroup buttonGroupResultAnc;
    private javax.swing.ButtonGroup buttonGroupRisk;
    private javax.swing.ButtonGroup buttonGroupSound_heart;
    private javax.swing.ButtonGroup buttonGroupSqueamish;
    private javax.swing.ButtonGroup buttonGroupSwell_of_mother;
    private javax.swing.ButtonGroup buttonGroupTartar;
    private javax.swing.ButtonGroup buttonGroupThiroil;
    private javax.swing.ButtonGroup buttonTT;
    private com.pcu.utility.DateComboBox dateComboBoxAncSurveyDate;
    private com.hospital_os.utility.DateComboBox dateComboBoxCheck;
    private com.pcu.utility.DateComboBox dateComboBoxCheckHCTDate;
    private com.hospital_os.utility.DoubleTextField doubleTextFieldBMI;
    private com.pcu.utility.DoubleTextField doubleTextFieldUterusCm;
    private javax.swing.JLabel integerTextFieldDay;
    private com.pcu.utility.DoubleTextField integerTextFieldHigh;
    private com.pcu.utility.IntegerTextField integerTextFieldNo;
    private com.pcu.utility.IntegerTextField integerTextFieldTCaries;
    private com.pcu.utility.DoubleTextField integerTextFieldWeight;
    private javax.swing.JCheckBox jCheckBoxAncOtherOffice;
    private javax.swing.JComboBox jComboBoxANCRES;
    private javax.swing.JComboBox jComboBoxAlblumin;
    private javax.swing.JComboBox jComboBoxAncRange;
    private javax.swing.JComboBox jComboBoxBabyTHALAS;
    private javax.swing.JComboBox jComboBoxConduct;
    private javax.swing.JComboBox jComboBoxHB;
    private javax.swing.JComboBox jComboBoxHCT;
    private javax.swing.JComboBox jComboBoxHIV;
    private javax.swing.JComboBox jComboBoxHighRisk;
    private javax.swing.JComboBox jComboBoxPosture;
    private javax.swing.JComboBox jComboBoxSugar;
    private javax.swing.JComboBox jComboBoxTHALAS;
    private javax.swing.JComboBox jComboBoxUrineLevel;
    private javax.swing.JComboBox jComboBoxUterusLevel;
    private javax.swing.JComboBox jComboBoxVDRL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelANCRES;
    private javax.swing.JLabel jLabelAgOfPre;
    private javax.swing.JLabel jLabelAlblumin;
    private javax.swing.JLabel jLabelAncRange;
    private javax.swing.JLabel jLabelBMI;
    private javax.swing.JLabel jLabelBabyTHALAS;
    private javax.swing.JLabel jLabelBloodInVag;
    private javax.swing.JLabel jLabelCheckHCTDate;
    private javax.swing.JLabel jLabelConduct;
    private javax.swing.JLabel jLabelCramp;
    private javax.swing.JLabel jLabelDental;
    private javax.swing.JLabel jLabelGiveBirth;
    private javax.swing.JLabel jLabelGumInf;
    private javax.swing.JLabel jLabelHCT;
    private javax.swing.JLabel jLabelHCT_R;
    private javax.swing.JLabel jLabelHeadache;
    private javax.swing.JLabel jLabelHeartFail;
    private javax.swing.JLabel jLabelHigh;
    private javax.swing.JLabel jLabelHighRisk;
    private javax.swing.JLabel jLabelKgs;
    private javax.swing.JLabel jLabelMenstru;
    private javax.swing.JLabel jLabelMovingBaby;
    private javax.swing.JLabel jLabelN1;
    private javax.swing.JLabel jLabelNextAppDate;
    private javax.swing.JLabel jLabelPosture;
    private javax.swing.JLabel jLabelPresUnit;
    private javax.swing.JLabel jLabelPressure;
    private javax.swing.JLabel jLabelPulse;
    private javax.swing.JLabel jLabelPulseUnit;
    private javax.swing.JLabel jLabelReceiveTT;
    private javax.swing.JLabel jLabelRemark1;
    private javax.swing.JLabel jLabelRisk;
    private javax.swing.JLabel jLabelSoundHeart;
    private javax.swing.JLabel jLabelSqueamish;
    private javax.swing.JLabel jLabelSugar;
    private javax.swing.JCheckBox jLabelSurveyDate;
    private javax.swing.JLabel jLabelSwell;
    private javax.swing.JLabel jLabelTCaries;
    private javax.swing.JLabel jLabelTHALAS;
    private javax.swing.JLabel jLabelTT_R;
    private javax.swing.JLabel jLabelTartar;
    private javax.swing.JLabel jLabelThiroil;
    private javax.swing.JLabel jLabelUrine;
    private javax.swing.JLabel jLabelUterus;
    private javax.swing.JLabel jLabelVDR;
    private javax.swing.JLabel jLabelVNAnc;
    private javax.swing.JLabel jLabelWeight;
    private javax.swing.JLabel jLabelcms;
    private javax.swing.JLabel jLableHB;
    private javax.swing.JLabel jLableHIV;
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
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelAncSurvey1;
    private javax.swing.JPanel jPanelCheckAnyRS;
    private javax.swing.JPanel jPanelData;
    private javax.swing.JPanel jPanelDataSub1;
    private javax.swing.JPanel jPanelDataSub2;
    private javax.swing.JRadioButton jRadioBlood0;
    private javax.swing.JRadioButton jRadioBlood1;
    private javax.swing.JRadioButton jRadioButtonAncAbnormal;
    private javax.swing.JRadioButton jRadioButtonAncNoTreat;
    private javax.swing.JRadioButton jRadioButtonAncNormal;
    private javax.swing.JRadioButton jRadioButtonAncWaitResult;
    private javax.swing.JRadioButton jRadioButtonDental0;
    private javax.swing.JRadioButton jRadioButtonDental1;
    private javax.swing.JRadioButton jRadioButtonGumInf0;
    private javax.swing.JRadioButton jRadioButtonGumInf1;
    private javax.swing.JRadioButton jRadioButtonTT1;
    private javax.swing.JRadioButton jRadioButtonTT2;
    private javax.swing.JRadioButton jRadioButtonTT3;
    private javax.swing.JRadioButton jRadioButtonTT4;
    private javax.swing.JRadioButton jRadioButtonTartar0;
    private javax.swing.JRadioButton jRadioButtonTartar1;
    private javax.swing.JRadioButton jRadioCramp0;
    private javax.swing.JRadioButton jRadioCramp1;
    private javax.swing.JRadioButton jRadioGiveBirth0;
    private javax.swing.JRadioButton jRadioGiveBirth1;
    private javax.swing.JRadioButton jRadioHeadache0;
    private javax.swing.JRadioButton jRadioHeadache1;
    private javax.swing.JRadioButton jRadioHeart0;
    private javax.swing.JRadioButton jRadioHeart1;
    private javax.swing.JRadioButton jRadioMenstruation0;
    private javax.swing.JRadioButton jRadioMenstruation1;
    private javax.swing.JRadioButton jRadioMovingBaby0;
    private javax.swing.JRadioButton jRadioMovingBaby1;
    private javax.swing.JRadioButton jRadioReceiveTT0;
    private javax.swing.JRadioButton jRadioReceiveTT1;
    private javax.swing.JRadioButton jRadioRisk0;
    private javax.swing.JRadioButton jRadioRisk1;
    private javax.swing.JRadioButton jRadioSoundHeart0;
    private javax.swing.JRadioButton jRadioSoundHeart1;
    private javax.swing.JRadioButton jRadioSqueamish0;
    private javax.swing.JRadioButton jRadioSqueamish1;
    private javax.swing.JRadioButton jRadioSwell0;
    private javax.swing.JRadioButton jRadioSwell1;
    private javax.swing.JRadioButton jRadioThiroil0;
    private javax.swing.JRadioButton jRadioThiroil1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane;
    private com.hosv3.gui.component.BalloonTextArea jTextAreaAncExamDescription;
    private javax.swing.JTextField jTextFieldNoteRemark;
    private com.hospital_os.utility.DoubleTextField jTextFieldPressure1;
    private com.hospital_os.utility.DoubleTextField jTextFieldPressure2;
    private com.hospital_os.utility.DoubleTextField jTextFieldPulse;
    private com.hospital_os.utility.PercentTextField percentTextHCT_R;
    private com.hospital_os.utility.TimeTextField timeTextFieldCheck;
    // End of variables declaration//GEN-END:variables
    private boolean checkSurveyAnc;
}
