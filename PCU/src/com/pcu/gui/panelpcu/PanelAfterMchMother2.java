/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelAfterMchMother2.java
 *
 * Created on 18 พ.ย. 2553, 10:45:55
 */

package com.pcu.gui.panelpcu;

import com.hospital_os.object.Anc;
import com.hospital_os.object.Office;
import com.hospital_os.utility.ComboboxModel;
import com.pcu.control.PCUObject;
import com.pcu.object.MchDetail;
import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.TaBleModel;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.control.AfterMCHMotherControl;
import com.pcu.control.HosManage;
import com.pcu.gui.dialog.HosDialog;
import com.pcu.object.AfterMchMother;
import com.pcu.object.AncDetailPcu;
import com.pcu.object.AncPcu;
import com.pcu.object.BornMch;
import com.pcu.object.Pregnancy;
import com.pcu.utility.GutilPCU;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.JOptionPane;
/**
 *
 * @author LionHeart
 */
public class PanelAfterMchMother2 extends javax.swing.JPanel {

    /** Creates new form PanelAfterMchMother2 */
    MchDetail theMchDetail;
    Vector theMchDetailV;
    private UpdateStatus theUS;
    private HosDialog theHosDialog;
    private HosManage theHM;
    private PCUObject pcuobject;
    AfterMCHMotherControl theAfterMCHMotherControl;
    String[] cols = {"ครรภ์ที่","LMP","EDC"};
    AfterMchMother theAfterMchMother;
    AfterMchMother theAfterMchMother1;
    AfterMchMother theAfterMchMother2;
    AfterMchMother theAfterMchMother3;
    BornMch theBornMch;
    public PanelAfterMchMother2() {
        initComponents();
    }
    public void setControl(HosManage hm,HosDialog hd,UpdateStatus us)
    {
        theUS = us;
        theHosDialog = hd;
        theHM = hm;
        pcuobject = theHM.thePO;
        theAfterMCHMotherControl = theHM.theHosControl.theAfterMCHMotherControl;
        ComboboxModel.initComboBox(jComboBoxBornPlace,theHM.theHosControl.theAllComboBoxControl.listPostpartumBirthPlace());
        ComboboxModel.initComboBox(jComboBoxBirthMethod, theHM.theHosControl.theAllComboBoxControl.listBirthMethod());
        ComboboxModel.initComboBox(jComboBoxBddoctor, theHM.theHosControl.theAllComboBoxControl.listBDoctor());
        ComboboxModel.initComboBox(jComboBoxResultICD10, theHM.theHosControl.theAllComboBoxControl.comboBoxViewICD10Pregnant());

//        ComboboxModel.initComboBox(jComboBoxVDRL, vPostpartumStatusResult); listPostpartumStatusResult()
        Vector vResult = theHM.theHosControl.theAllComboBoxControl.listPostpartumStatusResult();
        ComboboxModel.initComboBox(jComboBoxVdrl,vResult);
        ComboboxModel.initComboBox(jComboBoxHBRS, vResult);
        ComboboxModel.initComboBox(jComboBoxHIVRS, vResult);
        ComboboxModel.initComboBox(jComboBoxThalassaemia, vResult);
//        ComboboxModel.initComboBox(jComboBoxInfameGum, vResult);
//        ComboboxModel.initComboBox(jComboBoxLimeStone, vResult);

//        ComboboxModel.initComboBox(jComboBoxVdrl, theHM.theHosControl.theAllComboBoxControl.listPPResultDetail());
//        ComboboxModel.initComboBox(jComboBoxHBRS, theHM.theHosControl.theAllComboBoxControl.listPPResultDetail());
//        ComboboxModel.initComboBox(jComboBoxHIVRS, theHM.theHosControl.theAllComboBoxControl.listPPResultDetail());
//        ComboboxModel.initComboBox(jComboBoxThalassaemia, theHM.theHosControl.theAllComboBoxControl.listPPResultDetail());
        ComboboxModel.initComboBox(jComboBoxInfameGum, theHM.theHosControl.theAllComboBoxControl.listPPResultDetail());
        ComboboxModel.initComboBox(jComboBoxLimeStone, theHM.theHosControl.theAllComboBoxControl.listPPResultDetail());

        ComboboxModel.initComboBox(jComboBoxPlace1,theHM.theHosControl.theAllComboBoxControl.listPostpartumBirthPlace());
        ComboboxModel.initComboBox(jComboBoxPlace2,theHM.theHosControl.theAllComboBoxControl.listPostpartumBirthPlace());
        ComboboxModel.initComboBox(jComboBoxPlace3,theHM.theHosControl.theAllComboBoxControl.listPostpartumBirthPlace());
    }
    public MchDetail getMchDetail()
    {
        if(theMchDetail==null)
            theMchDetail = new MchDetail();
        theMchDetail.gravida = jTextFieldGravida.getText();
        theMchDetail.alive_baby = jTextFieldAliveBaby.getText();
        theMchDetail.death_baby = jTextFieldDeathBaby.getText();
        theMchDetail.born_place = Gutil.getGuiData(jComboBoxBornPlace);
        theMchDetail.last_men_date = dateComboBoxLastMenDate.getText();
        theMchDetail.birth_date = dateComboBoxBirthDate.getText();
        theMchDetail.birth_method = Gutil.getGuiData(jComboBoxBirthMethod);
        theMchDetail.bddoctor = Gutil.getGuiData(jComboBoxBddoctor);
        theMchDetail.born_result = Gutil.getGuiData(jComboBoxResultICD10);
        theMchDetail.born_at_hospital = integerTextFieldHosSubCode.getText();
        theMchDetail.vdrl_rs = Gutil.getGuiData(jComboBoxVdrl);
        theMchDetail.hb_rs = Gutil.getGuiData(jComboBoxHBRS);
        theMchDetail.hiv_rs = Gutil.getGuiData(jComboBoxHIVRS);
        theMchDetail.hct_date = dateComboBoxHCTDate.getText();
        theMchDetail.thalassaemia = Gutil.getGuiData(jComboBoxThalassaemia);
        theMchDetail.dental = dateComboBoxDental.getText();
        theMchDetail.decayed_tooth = jTextFieldDeclayedTooth.getText();
        theMchDetail.inflame_gum = Gutil.getGuiData(jComboBoxInfameGum);
        theMchDetail.limestone = Gutil.getGuiData(jComboBoxLimeStone);
        theMchDetail.hct_lavel = jTextFieldHctLavel.getText();
        theMchDetail.born_amount = jTextFieldBornAmount.getText();
        return theMchDetail;
    }
    public void setMchDetail(MchDetail mchDetail)
    {
        theMchDetail = mchDetail;
        if(theMchDetail == null)
            theMchDetail = new MchDetail();
        jTextFieldGravida.setText(theMchDetail.gravida);
        jTextFieldAliveBaby.setText(theMchDetail.alive_baby);
        jTextFieldDeathBaby.setText(theMchDetail.death_baby);
        Gutil.setGuiData(jComboBoxBornPlace,theMchDetail.born_place);//Gutil.convertFieldDate(thePPCare.survey_date2)
        dateComboBoxLastMenDate.setText(Gutil.convertFieldDate(theMchDetail.last_men_date));
        dateComboBoxBirthDate.setText(Gutil.convertFieldDate(theMchDetail.birth_date));
        Gutil.setGuiData(jComboBoxBirthMethod,theMchDetail.birth_method);
        Gutil.setGuiData(jComboBoxBddoctor,theMchDetail.bddoctor);
        Gutil.setGuiData(jComboBoxResultICD10,theMchDetail.born_result);
        integerTextFieldHosSubCode.setText(theMchDetail.born_at_hospital);
        Office office = (Office)this.theHM.theHC.theLookupControl.readHospitalByCode(theMchDetail.born_at_hospital);
        if(office!=null)
            jTextFieldHosSub.setText(office.getName());
        else
            jTextFieldHosSub.setText("");
        Gutil.setGuiData(jComboBoxVdrl,theMchDetail.vdrl_rs);
        Gutil.setGuiData(jComboBoxHBRS,theMchDetail.hb_rs);
        Gutil.setGuiData(jComboBoxHIVRS,theMchDetail.hiv_rs);
        dateComboBoxHCTDate.setText(Gutil.convertFieldDate(theMchDetail.hct_date));
        Gutil.setGuiData(jComboBoxThalassaemia,theMchDetail.thalassaemia);
        dateComboBoxDental.setText(Gutil.convertFieldDate(theMchDetail.dental));
        jTextFieldDeclayedTooth.setText(theMchDetail.decayed_tooth);
        Gutil.setGuiData(jComboBoxInfameGum,theMchDetail.inflame_gum);
        Gutil.setGuiData(jComboBoxLimeStone,theMchDetail.limestone);
        jTextFieldHctLavel.setText(theMchDetail.hct_lavel);
        jTextFieldBornAmount.setText(theMchDetail.born_amount);
    }
    public void setMchDetailV(Vector v)
    {
        theMchDetailV = v;
        TaBleModel tm = null;
        if(theMchDetailV == null)
        {
            theMchDetailV = new Vector();
            tm = new TaBleModel(cols,0);
        }
        tm = new TaBleModel(cols,theMchDetailV.size());
        for(int i=0;i<theMchDetailV.size();i++)
        {
            MchDetail tmp = (MchDetail) theMchDetailV.get(i);
            tm.setValueAt(tmp.gravida, i, 0);
            if(!tmp.last_men_date.equals(""))
                tm.setValueAt(tmp.last_men_date.substring(2), i, 1);
            else
                tm.setValueAt("", i, 1);
            if(!tmp.birth_date.equals(""))
                tm.setValueAt(tmp.birth_date.substring(2), i, 2);
            else
                tm.setValueAt("", i, 2);
        }
        jTableMCHDetail.setModel(tm);
    }
    public void setAfterMchMother(AfterMchMother afterMchMother)
    {
        setAfterMchMother1(afterMchMother);
        setAfterMchMother2(afterMchMother);
        setAfterMchMother3(afterMchMother);
    }
    public void setAfterMchMother1(AfterMchMother afterMchMother)
    {
        theAfterMchMother = afterMchMother;
        if(theAfterMchMother == null)
            theAfterMchMother = new AfterMchMother();
        dateComboBoxTo8.setText(Gutil.convertFieldDate(theAfterMchMother.survey_date));
        ComboboxModel.setCodeComboBox(jComboBoxPlace1,theAfterMchMother.place);
        if(theAfterMchMother.result_verify.equals("1"))
            jRadioButtonNormal1.setSelected(true);
        if(theAfterMchMother.result_verify.equals("0"))
            jRadioButtonAbNormal1.setSelected(true);
        jTextAreaSymptomNotice1.setText(theAfterMchMother.symptom_notice);
        dateComboBoxTo4.setText(Gutil.convertFieldDate(theAfterMchMother.updatetime));
        if(theAfterMchMother.updatetime.length()>11)
            this.timeTextFieldCheck.setText(theAfterMchMother.updatetime.substring(11));
    }
    public void setAfterMchMother2(AfterMchMother afterMchMother)
    {
        theAfterMchMother = afterMchMother;
        if(theAfterMchMother == null)
            theAfterMchMother = new AfterMchMother();
        dateComboBoxTo9.setText(Gutil.convertFieldDate(theAfterMchMother.survey_date2));
        ComboboxModel.setCodeComboBox(jComboBoxPlace2,theAfterMchMother.place2);
        if(theAfterMchMother.result_verify2.equals("1"))
            jRadioButtonNormal2.setSelected(true);
        if(theAfterMchMother.result_verify2.equals("0"))
            jRadioButtonAbNormal2.setSelected(true);
        jTextAreaSymptomNotice2.setText(theAfterMchMother.symptom_notice2);
        dateComboBoxTo4.setText(Gutil.convertFieldDate(theAfterMchMother.updatetime));
        if(theAfterMchMother.updatetime.length()>11)
            this.timeTextFieldCheck.setText(theAfterMchMother.updatetime.substring(11));
    }
    public void setAfterMchMother3(AfterMchMother afterMchMother)
    {
        theAfterMchMother = afterMchMother;
        if(theAfterMchMother == null)
            theAfterMchMother = new AfterMchMother();
        dateComboBoxTo10.setText(Gutil.convertFieldDate(theAfterMchMother.survey_date3));
        ComboboxModel.setCodeComboBox(jComboBoxPlace3,theAfterMchMother.place3);
        if(theAfterMchMother.result_verify3.equals("1"))
            jRadioButtonNormal3.setSelected(true);
        if(theAfterMchMother.result_verify3.equals("0"))
            jRadioButtonAbNormal3.setSelected(true);
        jTextAreaSymptomNotice3.setText(theAfterMchMother.symptom_notice3);
        dateComboBoxTo4.setText(Gutil.convertFieldDate(theAfterMchMother.updatetime));
        if(theAfterMchMother.updatetime.length()>11)
            this.timeTextFieldCheck.setText(theAfterMchMother.updatetime.substring(11));
    }
    public AfterMchMother getAfterMchMother1()
    {
        if(theAfterMchMother == null)
            theAfterMchMother = new AfterMchMother();
        theAfterMchMother.survey_date = dateComboBoxTo8.getText();
        theAfterMchMother.place = ComboboxModel.getCodeComboBox(jComboBoxPlace1);
        if(jRadioButtonNormal1.isSelected())
            theAfterMchMother.result_verify = "1";
        else
            theAfterMchMother.result_verify = "0";
        theAfterMchMother.symptom_notice = jTextAreaSymptomNotice1.getText();
        theAfterMchMother.updatetime = dateComboBoxTo4.getText()+","+timeTextFieldCheck.getText();
        theAfterMchMother.family_id = this.theHM.theHO.theFamily.getObjectId();
        theAfterMchMother.visit_id = this.theHM.theHO.theVisit.getObjectId();
        theAfterMchMother.patient_id = this.theHM.theHO.theVisit.getObjectId();
        theAfterMchMother.active = "1";
        theAfterMchMother.pregnantnumber = jTextFieldGravida.getText();
        theAfterMchMother.pcare = "1";
        return theAfterMchMother;
    }
    public AfterMchMother getAfterMchMother2()
    {
        if(theAfterMchMother == null)
            theAfterMchMother = new AfterMchMother();
        theAfterMchMother.survey_date2 = dateComboBoxTo8.getText();
        theAfterMchMother.place2 = ComboboxModel.getCodeComboBox(jComboBoxPlace1);
        if(jRadioButtonNormal1.isSelected())
            theAfterMchMother.result_verify2 = "1";
        else
            theAfterMchMother.result_verify2 = "0";
        theAfterMchMother.symptom_notice2 = jTextAreaSymptomNotice2.getText();
        theAfterMchMother.updatetime = dateComboBoxTo4.getText()+","+timeTextFieldCheck.getText();
        theAfterMchMother.updatetime2 = dateComboBoxTo4.getText()+","+timeTextFieldCheck.getText();
        theAfterMchMother.family_id2 = this.theHM.theHO.theFamily.getObjectId();
        theAfterMchMother.visit_id2 = this.theHM.theHO.theVisit.getObjectId();
        theAfterMchMother.patient_id2 = this.theHM.theHO.theVisit.getObjectId();
        theAfterMchMother.active2 = "1";
        theAfterMchMother.pregnantnumber2 = jTextFieldGravida.getText();
        theAfterMchMother.pcare2 = "2";
        return theAfterMchMother;
    }
    public AfterMchMother getAfterMchMother3()
    {
        if(theAfterMchMother == null)
            theAfterMchMother = new AfterMchMother();
        theAfterMchMother.survey_date3 = dateComboBoxTo8.getText();
        theAfterMchMother.place3 = ComboboxModel.getCodeComboBox(jComboBoxPlace1);
        if(jRadioButtonNormal1.isSelected())
            theAfterMchMother.result_verify3 = "1";
        else
            theAfterMchMother.result_verify3 = "0";
        theAfterMchMother.symptom_notice3 = jTextAreaSymptomNotice3.getText();
        theAfterMchMother.updatetime = dateComboBoxTo4.getText()+","+timeTextFieldCheck.getText();
        theAfterMchMother.updatetime3 = dateComboBoxTo4.getText()+","+timeTextFieldCheck.getText();
        theAfterMchMother.family_id3 = this.theHM.theHO.theFamily.getObjectId();
        theAfterMchMother.visit_id3 = this.theHM.theHO.theVisit.getObjectId();
        theAfterMchMother.patient_id3 = this.theHM.theHO.theVisit.getObjectId();
        theAfterMchMother.active3 = "1";
        theAfterMchMother.pregnantnumber3 = jTextFieldGravida.getText();
        theAfterMchMother.pcare3 = "3";
        return theAfterMchMother;
    }
    public void setObject(PCUObject pcuo) {
        theMchDetailV = this.theAfterMCHMotherControl.selectMchDetailByFamilyID(pcuo.theHO.theFamily.getObjectId());
//        this.theAfterMchMother = this.theAfterMCHMotherControl.readtMchByMchDetailID("")
        this.setMchDetailV(theMchDetailV);
        this.setMchDetail(null);
        this.setAfterMchMother(null);
        jLabelInfo.setText("");
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

        buttonGroupResult1 = new javax.swing.ButtonGroup();
        buttonGroupResult2 = new javax.swing.ButtonGroup();
        buttonGroupResult3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMCHDetail = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxResultICD10 = new javax.swing.JComboBox();
        jPanel8 = new javax.swing.JPanel();
        integerTextFieldHosSubCode = new javax.swing.JTextField();
        jTextFieldHosSub = new javax.swing.JTextField();
        jButtonHosSub = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBoxBddoctor = new javax.swing.JComboBox();
        jComboBoxBornPlace = new javax.swing.JComboBox();
        jPanel21 = new javax.swing.JPanel();
        dateComboBoxLastMenDate = new com.hospital_os.utility.DateComboBox();
        jLabel9 = new javax.swing.JLabel();
        dateComboBoxBirthDate = new com.hospital_os.utility.DateComboBox();
        jLabel23 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jTextFieldGravida = new com.pcu.utility.IntegerTextField();
        jTextFieldBornAmount = new com.pcu.utility.IntegerTextField();
        jTextFieldAliveBaby = new com.pcu.utility.IntegerTextField();
        jTextFieldDeathBaby = new com.pcu.utility.IntegerTextField();
        jButton10 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jComboBoxBirthMethod = new javax.swing.JComboBox();
        jLabelInfo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jComboBoxThalassaemia = new javax.swing.JComboBox();
        jComboBoxHBRS = new javax.swing.JComboBox();
        jComboBoxHIVRS = new javax.swing.JComboBox();
        dateComboBoxHCTDate = new com.hospital_os.utility.DateComboBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jComboBoxVdrl = new javax.swing.JComboBox();
        jPanel9 = new javax.swing.JPanel();
        dateComboBoxDental = new com.hospital_os.utility.DateComboBox();
        jPanel10 = new javax.swing.JPanel();
        jComboBoxInfameGum = new javax.swing.JComboBox();
        jTextFieldHctLavel = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jComboBoxLimeStone = new javax.swing.JComboBox();
        jTextFieldDeclayedTooth = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaSymptomNotice1 = new javax.swing.JTextArea();
        dateComboBoxTo8 = new com.hospital_os.utility.DateComboBox();
        jComboBoxPlace1 = new javax.swing.JComboBox();
        jPanel14 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jRadioButtonNormal1 = new javax.swing.JRadioButton();
        jRadioButtonAbNormal1 = new javax.swing.JRadioButton();
        jButton7 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaSymptomNotice2 = new javax.swing.JTextArea();
        dateComboBoxTo9 = new com.hospital_os.utility.DateComboBox();
        jComboBoxPlace2 = new javax.swing.JComboBox();
        jPanel16 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jRadioButtonNormal2 = new javax.swing.JRadioButton();
        jRadioButtonAbNormal2 = new javax.swing.JRadioButton();
        jButton8 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaSymptomNotice3 = new javax.swing.JTextArea();
        dateComboBoxTo10 = new com.hospital_os.utility.DateComboBox();
        jComboBoxPlace3 = new javax.swing.JComboBox();
        jPanel18 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jRadioButtonNormal3 = new javax.swing.JRadioButton();
        jRadioButtonAbNormal3 = new javax.swing.JRadioButton();
        jButton9 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        dateComboBoxTo4 = new com.hospital_os.utility.DateComboBox();
        jLabel45 = new javax.swing.JLabel();
        timeTextFieldCheck = new com.hospital_os.utility.TimeTextField();
        jLabel46 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("ลำดับการตั้งครรภ์"));
        jPanel4.setMinimumSize(new java.awt.Dimension(200, 170));
        jPanel4.setPreferredSize(new java.awt.Dimension(200, 170));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(150, 100));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(150, 100));

        jTableMCHDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableMCHDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableMCHDetailMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMCHDetail);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jScrollPane1, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(jButton1, gridBagConstraints);

        jButton2.setText("-");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton2, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel4.add(jPanel6, gridBagConstraints);

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel1.setBackground(new java.awt.Color(204, 204, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel1.setText("มีวันดูแลแม่ 1 อย่างเดียว = 1 point");
        jLabel1.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 2);
        jPanel7.add(jLabel1, gridBagConstraints);

        jLabel2.setBackground(new java.awt.Color(204, 204, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel2.setText("มีวันดูแลแม่ 1 และ 2 = 1.5 point");
        jLabel2.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 2, 2);
        jPanel7.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel4.add(jPanel7, gridBagConstraints);

        jPanel1.add(jPanel4, new java.awt.GridBagConstraints());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("รายละเอียดการตั้งครรภ์และการคลอด"));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel6.setText("สถานที่คลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel5.add(jLabel6, gridBagConstraints);

        jLabel7.setText("ผลสิ้นสุดการตั้งครรภ์");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel5.add(jLabel7, gridBagConstraints);

        jLabel8.setText("สถานพยาบาลที่คลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel5.add(jLabel8, gridBagConstraints);

        jComboBoxResultICD10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel5.add(jComboBoxResultICD10, gridBagConstraints);

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
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel5.add(jPanel8, gridBagConstraints);

        jLabel10.setText("สิ้นสุดการตั้งครรภ์/คลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel5.add(jLabel10, gridBagConstraints);

        jLabel11.setText("ผู้ทำคลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel5.add(jLabel11, gridBagConstraints);

        jComboBoxBddoctor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxBddoctor.setMinimumSize(new java.awt.Dimension(150, 18));
        jComboBoxBddoctor.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel5.add(jComboBoxBddoctor, gridBagConstraints);

        jComboBoxBornPlace.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel5.add(jComboBoxBornPlace, gridBagConstraints);

        jPanel21.setLayout(new java.awt.GridBagLayout());

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
        jPanel21.add(dateComboBoxLastMenDate, gridBagConstraints);

        jLabel9.setText("EDC");
        jLabel9.setToolTipText("วันกำหนดคลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel21.add(jLabel9, gridBagConstraints);

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
        jPanel21.add(dateComboBoxBirthDate, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel5.add(jPanel21, gridBagConstraints);

        jLabel23.setText("G-P-A-L");
        jLabel23.setToolTipText("G = จำนวนการตั้งครรภ์(ครรภ์ที่) ,P = จำนวนการคลอด ,A = จำนวนการแท้ง ,L = จำนวนบุตรที่มีชีวิตอยู่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel5.add(jLabel23, gridBagConstraints);

        jLabel12.setText("LMP");
        jLabel12.setToolTipText("วันแรกของประจำเดือนครั้งสุดท้าย");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jLabel12, gridBagConstraints);

        jPanel23.setLayout(new java.awt.GridBagLayout());

        jPanel22.setLayout(new java.awt.GridBagLayout());

        jLabel29.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jPanel22.add(jLabel29, gridBagConstraints);

        jLabel35.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jPanel22.add(jLabel35, gridBagConstraints);

        jLabel41.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel22.add(jTextFieldGravida, gridBagConstraints);

        jTextFieldBornAmount.setColumns(2);
        jTextFieldBornAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldBornAmount.setToolTipText("จำนวนการคลอด");
        jTextFieldBornAmount.setMinimumSize(new java.awt.Dimension(30, 21));
        jTextFieldBornAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldBornAmountFocusLost(evt);
            }
        });
        jTextFieldBornAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBornAmountKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel22.add(jTextFieldBornAmount, gridBagConstraints);

        jTextFieldAliveBaby.setColumns(2);
        jTextFieldAliveBaby.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldAliveBaby.setToolTipText("จำนวนบุตรที่มีชีวิตอยู่");
        jTextFieldAliveBaby.setMinimumSize(new java.awt.Dimension(30, 21));
        jTextFieldAliveBaby.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldAliveBabyFocusLost(evt);
            }
        });
        jTextFieldAliveBaby.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldAliveBabyKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel22.add(jTextFieldAliveBaby, gridBagConstraints);

        jTextFieldDeathBaby.setColumns(2);
        jTextFieldDeathBaby.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldDeathBaby.setToolTipText("จำนวนการแท้ง");
        jTextFieldDeathBaby.setMinimumSize(new java.awt.Dimension(30, 21));
        jTextFieldDeathBaby.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldDeathBabyFocusLost(evt);
            }
        });
        jTextFieldDeathBaby.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldDeathBabyKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldDeathBabyKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel22.add(jTextFieldDeathBaby, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel23.add(jPanel22, gridBagConstraints);

        jButton10.setText("อ่านข้อมูลเก่า");
        jButton10.setToolTipText("กรุณากรอกข้อมูล G-P-A-L ก่อนดึงข้อมูลเก่าl");
        jButton10.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel23.add(jButton10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel5.add(jPanel23, gridBagConstraints);

        jPanel24.setLayout(new java.awt.GridBagLayout());

        jComboBoxBirthMethod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxBirthMethod.setMinimumSize(new java.awt.Dimension(150, 18));
        jComboBoxBirthMethod.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel24.add(jComboBoxBirthMethod, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel24.add(jLabelInfo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jPanel24, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel1.add(jPanel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("รายละเอียดการตั้งครรภ์และคลอด"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel13.setText("ผล VDRL_RS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jLabel13, gridBagConstraints);

        jLabel14.setText("ผล HB_RS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jLabel14, gridBagConstraints);

        jLabel15.setText("ผล HIV_RS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jLabel15, gridBagConstraints);

        jLabel16.setText("วันตรวจ HCT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(jLabel16, gridBagConstraints);

        jComboBoxThalassaemia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel2.add(jComboBoxThalassaemia, gridBagConstraints);

        jComboBoxHBRS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel2.add(jComboBoxHBRS, gridBagConstraints);

        jComboBoxHIVRS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel2.add(jComboBoxHIVRS, gridBagConstraints);

        dateComboBoxHCTDate.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxHCTDate.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxHCTDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxHCTDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel2.add(dateComboBoxHCTDate, gridBagConstraints);

        jLabel17.setText("ผลตรวจ THALASSAEMIA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel2.add(jLabel17, gridBagConstraints);

        jLabel18.setText("ตรวจสุขภาพฟันและแนะนำ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel2.add(jLabel18, gridBagConstraints);

        jLabel19.setText("มีเหงือกอักเสบ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel2.add(jLabel19, gridBagConstraints);

        jLabel20.setText("ระดับ HCT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel2.add(jLabel20, gridBagConstraints);

        jComboBoxVdrl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel2.add(jComboBoxVdrl, gridBagConstraints);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        dateComboBoxDental.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxDental.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxDental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxDentalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel9.add(dateComboBoxDental, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel2.add(jPanel9, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jComboBoxInfameGum.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel10.add(jComboBoxInfameGum, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel2.add(jPanel10, gridBagConstraints);

        jTextFieldHctLavel.setMinimumSize(new java.awt.Dimension(100, 20));
        jTextFieldHctLavel.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel2.add(jTextFieldHctLavel, gridBagConstraints);

        jLabel21.setText("ฟันผุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel2.add(jLabel21, gridBagConstraints);

        jLabel22.setText("มีหินน้ำลาย");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel2.add(jLabel22, gridBagConstraints);

        jComboBoxLimeStone.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel2.add(jComboBoxLimeStone, gridBagConstraints);

        jTextFieldDeclayedTooth.setMaximumSize(new java.awt.Dimension(57, 21));
        jTextFieldDeclayedTooth.setMinimumSize(new java.awt.Dimension(45, 21));
        jTextFieldDeclayedTooth.setPreferredSize(new java.awt.Dimension(45, 21));
        jTextFieldDeclayedTooth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDeclayedToothActionPerformed(evt);
            }
        });
        jTextFieldDeclayedTooth.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldDeclayedToothFocusLost(evt);
            }
        });
        jTextFieldDeclayedTooth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldDeclayedToothKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel2.add(jTextFieldDeclayedTooth, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลแม่ครั้งที่1"));
        jPanel11.setPreferredSize(new java.awt.Dimension(0, 0));
        jPanel11.setLayout(new java.awt.GridBagLayout());

        jLabel24.setText("บันทึกอาการ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel11.add(jLabel24, gridBagConstraints);

        jLabel25.setText("สรุปผลการตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel11.add(jLabel25, gridBagConstraints);

        jLabel26.setText("นัดครั้งต่อไป");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel11.add(jLabel26, gridBagConstraints);

        jLabel27.setText("สถานที่ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel11.add(jLabel27, gridBagConstraints);

        jLabel28.setText("วันที่ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel11.add(jLabel28, gridBagConstraints);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(23, 50));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(166, 50));

        jTextAreaSymptomNotice1.setColumns(20);
        jTextAreaSymptomNotice1.setRows(5);
        jScrollPane2.setViewportView(jTextAreaSymptomNotice1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel11.add(jScrollPane2, gridBagConstraints);

        dateComboBoxTo8.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxTo8.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxTo8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxTo8ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel11.add(dateComboBoxTo8, gridBagConstraints);

        jComboBoxPlace1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxPlace1.setMinimumSize(new java.awt.Dimension(100, 18));
        jComboBoxPlace1.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel11.add(jComboBoxPlace1, gridBagConstraints);

        jPanel14.setLayout(new java.awt.GridBagLayout());

        jButton3.setText("นัด");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel14.add(jButton3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel11.add(jPanel14, gridBagConstraints);

        jPanel15.setLayout(new java.awt.GridBagLayout());

        buttonGroupResult1.add(jRadioButtonNormal1);
        jRadioButtonNormal1.setText("ปกติ");
        jPanel15.add(jRadioButtonNormal1, new java.awt.GridBagConstraints());

        buttonGroupResult1.add(jRadioButtonAbNormal1);
        jRadioButtonAbNormal1.setText("ผิดปกติ");
        jPanel15.add(jRadioButtonAbNormal1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel11.add(jPanel15, gridBagConstraints);

        jButton7.setText("บันทึกเพิ่มเติม");
        jButton7.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton7.setMaximumSize(new java.awt.Dimension(150, 23));
        jButton7.setMinimumSize(new java.awt.Dimension(150, 23));
        jButton7.setPreferredSize(new java.awt.Dimension(150, 23));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel11.add(jButton7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jPanel11, gridBagConstraints);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลแม่ครั้งที่2"));
        jPanel12.setPreferredSize(new java.awt.Dimension(0, 0));
        jPanel12.setLayout(new java.awt.GridBagLayout());

        jLabel30.setText("บันทึกอาการ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel12.add(jLabel30, gridBagConstraints);

        jLabel31.setText("สรุปผลการตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel12.add(jLabel31, gridBagConstraints);

        jLabel32.setText("นัดครั้งต่อไป");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel12.add(jLabel32, gridBagConstraints);

        jLabel33.setText("สถานที่ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel12.add(jLabel33, gridBagConstraints);

        jLabel34.setText("วันที่ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel12.add(jLabel34, gridBagConstraints);

        jScrollPane3.setMinimumSize(new java.awt.Dimension(23, 50));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(166, 50));

        jTextAreaSymptomNotice2.setColumns(20);
        jTextAreaSymptomNotice2.setRows(5);
        jScrollPane3.setViewportView(jTextAreaSymptomNotice2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel12.add(jScrollPane3, gridBagConstraints);

        dateComboBoxTo9.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxTo9.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxTo9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxTo9ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel12.add(dateComboBoxTo9, gridBagConstraints);

        jComboBoxPlace2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxPlace2.setMinimumSize(new java.awt.Dimension(100, 18));
        jComboBoxPlace2.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel12.add(jComboBoxPlace2, gridBagConstraints);

        jPanel16.setLayout(new java.awt.GridBagLayout());

        jButton4.setText("นัด");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel16.add(jButton4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel12.add(jPanel16, gridBagConstraints);

        jPanel17.setLayout(new java.awt.GridBagLayout());

        buttonGroupResult2.add(jRadioButtonNormal2);
        jRadioButtonNormal2.setText("ปกติ");
        jPanel17.add(jRadioButtonNormal2, new java.awt.GridBagConstraints());

        buttonGroupResult2.add(jRadioButtonAbNormal2);
        jRadioButtonAbNormal2.setText("ผิดปกติ");
        jPanel17.add(jRadioButtonAbNormal2, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel12.add(jPanel17, gridBagConstraints);

        jButton8.setText("บันทึกเพิ่มเติม");
        jButton8.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton8.setMaximumSize(new java.awt.Dimension(150, 23));
        jButton8.setMinimumSize(new java.awt.Dimension(150, 23));
        jButton8.setPreferredSize(new java.awt.Dimension(150, 23));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel12.add(jButton8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jPanel12, gridBagConstraints);

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลแม่ครั้งที่3"));
        jPanel13.setPreferredSize(new java.awt.Dimension(0, 0));
        jPanel13.setLayout(new java.awt.GridBagLayout());

        jLabel36.setText("บันทึกอาการ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel13.add(jLabel36, gridBagConstraints);

        jLabel37.setText("สรุปผลการตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel13.add(jLabel37, gridBagConstraints);

        jLabel38.setText("นัดครั้งต่อไป");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel13.add(jLabel38, gridBagConstraints);

        jLabel39.setText("สถานที่ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel13.add(jLabel39, gridBagConstraints);

        jLabel40.setText("วันที่ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel13.add(jLabel40, gridBagConstraints);

        jScrollPane4.setMinimumSize(new java.awt.Dimension(23, 50));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(166, 50));

        jTextAreaSymptomNotice3.setColumns(20);
        jTextAreaSymptomNotice3.setRows(5);
        jScrollPane4.setViewportView(jTextAreaSymptomNotice3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel13.add(jScrollPane4, gridBagConstraints);

        dateComboBoxTo10.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxTo10.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxTo10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxTo10ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel13.add(dateComboBoxTo10, gridBagConstraints);

        jComboBoxPlace3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxPlace3.setMinimumSize(new java.awt.Dimension(100, 18));
        jComboBoxPlace3.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel13.add(jComboBoxPlace3, gridBagConstraints);

        jPanel18.setLayout(new java.awt.GridBagLayout());

        jButton5.setText("นัด");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel18.add(jButton5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel13.add(jPanel18, gridBagConstraints);

        jPanel19.setLayout(new java.awt.GridBagLayout());

        buttonGroupResult3.add(jRadioButtonNormal3);
        jRadioButtonNormal3.setText("ปกติ");
        jPanel19.add(jRadioButtonNormal3, new java.awt.GridBagConstraints());

        buttonGroupResult3.add(jRadioButtonAbNormal3);
        jRadioButtonAbNormal3.setText("ผิดปกติ");
        jPanel19.add(jRadioButtonAbNormal3, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel13.add(jPanel19, gridBagConstraints);

        jButton9.setText("บันทึกเพิ่มเติม");
        jButton9.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton9.setMaximumSize(new java.awt.Dimension(150, 23));
        jButton9.setMinimumSize(new java.awt.Dimension(150, 23));
        jButton9.setPreferredSize(new java.awt.Dimension(150, 23));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel13.add(jButton9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jPanel13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        add(jPanel3, gridBagConstraints);

        jPanel20.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel20.add(jPanel31, gridBagConstraints);

        jButton6.setText("บันทึก");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel20.add(jButton6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        add(jPanel20, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void integerTextFieldHosSubCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_integerTextFieldHosSubCodeActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_integerTextFieldHosSubCodeActionPerformed

    private void integerTextFieldHosSubCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integerTextFieldHosSubCodeFocusLost
        Office office = theHM.theHC.theLookupControl.readHospitalByCode(integerTextFieldHosSubCode.getText());
        if(office==null) {
            integerTextFieldHosSubCode.setText("");
            jTextFieldHosSub.setText("");
            theUS.setStatus("ไม่พบสถานพยาบาลที่ตรงกับรหัสที่ระบุ กรุณาตรวจสอบรหัสอีกครั้ง", theUS.WARNING);
        } else {
            jTextFieldHosSub.setText(office.name);
        }
}//GEN-LAST:event_integerTextFieldHosSubCodeFocusLost

    private void integerTextFieldHosSubCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_integerTextFieldHosSubCodeKeyReleased
        if(integerTextFieldHosSubCode.getText().length()==5) {
            Office office = theHM.theHC.theLookupControl.readHospitalByCode(integerTextFieldHosSubCode.getText());
            if(office == null)    return;
            if(office.getObjectId()!=null) {
                jTextFieldHosSub.setText(office.name);
            }
        }
}//GEN-LAST:event_integerTextFieldHosSubCodeKeyReleased

    private void jButtonHosSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHosSubActionPerformed
        Office office = new Office();
        if(theMchDetail!=null)
            office.setObjectId(theMchDetail.born_at_hospital);
        if(theHosDialog.showDialogOffice(office)) {
            jTextFieldHosSub.setText(office.getName());
            integerTextFieldHosSubCode.setText(office.getObjectId());
        }
}//GEN-LAST:event_jButtonHosSubActionPerformed

    private void dateComboBoxBirthDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxBirthDateActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_dateComboBoxBirthDateActionPerformed

    private void dateComboBoxLastMenDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxLastMenDateActionPerformed
        String dateLastMen = dateComboBoxLastMenDate.getText();
        if (!dateLastMen.equals("")) {
            dateComboBoxBirthDate.setText(com.pcu.utility.DateUtil.calPregnant(dateLastMen));
        }
    }//GEN-LAST:event_dateComboBoxLastMenDateActionPerformed

    private void dateComboBoxHCTDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxHCTDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateComboBoxHCTDateActionPerformed

    private void dateComboBoxDentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxDentalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateComboBoxDentalActionPerformed

    private void dateComboBoxTo8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxTo8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateComboBoxTo8ActionPerformed

    private void dateComboBoxTo9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxTo9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateComboBoxTo9ActionPerformed

    private void dateComboBoxTo10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxTo10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateComboBoxTo10ActionPerformed

    private void dateComboBoxTo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxTo4ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_dateComboBoxTo4ActionPerformed

    private void timeTextFieldCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeTextFieldCheckMouseClicked

}//GEN-LAST:event_timeTextFieldCheckMouseClicked

    private void timeTextFieldCheckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timeTextFieldCheckKeyReleased

}//GEN-LAST:event_timeTextFieldCheckKeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(jLabelInfo.getText().equals(""))
        {
            int select = jTableMCHDetail.getSelectedRow();
            if(dateComboBoxTo4.getText().equals(""))
            {
                this.theUS.setStatus("กรุณาระบุวันที่บันทึก", UpdateStatus.WARNING);
                dateComboBoxTo4.requestFocus();
                return;
            }
            if(dateComboBoxLastMenDate.getText().equals(""))
            {
                this.theUS.setStatus("กรุณาระบุ LMP", UpdateStatus.WARNING);
                dateComboBoxLastMenDate.requestFocus();
                return;
            }
            if(jTextFieldGravida.getText().equals(""))
            {
                this.theUS.setStatus("กรุณาระบุ จำนวนการตั้งครรภ์ (ครรภ์ที่)", UpdateStatus.WARNING);
                jTextFieldGravida.requestFocus();
                return;
            }
            if(jTextFieldBornAmount.getText().equals(""))
            {
                this.theUS.setStatus("กรุณาระบุ จำนวนการคลอด", UpdateStatus.WARNING);
                jTextFieldBornAmount.requestFocus();
                return;
            }
            if(jTextFieldDeathBaby.getText().equals(""))
            {
                this.theUS.setStatus("กรุณาระบุ จำนวนการแท้ง", UpdateStatus.WARNING);
                jTextFieldDeathBaby.requestFocus();
                return;
            }
            if(jTextFieldAliveBaby.getText().equals(""))
            {
                this.theUS.setStatus("กรุณาระบุ จำนวนบุตรที่มีชีวิตอยู่", UpdateStatus.WARNING);
                jTextFieldAliveBaby.requestFocus();
                return;
            }
            int res = theAfterMCHMotherControl.saveMchDetail(this.getMchDetail());
            if(res<=0)
                return;

            getAfterMchMother1();
            getAfterMchMother2();
            getAfterMchMother3();
            theAfterMchMother.t_health_postpartum_detail_id = theMchDetail.getObjectId();
            int rs = this.theHM.theHosControl.theAfterMCHMotherControl.saveAfterMCHMotherN(theAfterMchMother);
            int tmp = theMchDetailV.size();
            if(rs<=0)
                return;
            theMchDetailV = this.theAfterMCHMotherControl.selectMchDetailByFamilyID(theHM.thePO.theHO.theFamily.getObjectId());
            this.setMchDetailV(theMchDetailV);
            this.setMchDetail(theMchDetail);
            this.setAfterMchMother(theAfterMchMother);
            int row_size = jTableMCHDetail.getRowCount();

            if(theMchDetailV.size()>tmp)
                jTableMCHDetail.setRowSelectionInterval(row_size-1, row_size-1);
            else
                jTableMCHDetail.setRowSelectionInterval(select,select);
            //        if(!jLabelDetail.getText().equals("-")) {
    //            this.theHM.theUS.setStatus("ไม่สามารถบันทึกข้อมูลของหน้าจอเก่าได้", UpdateStatus.WARNING);
    //            return;
    //        }
    //        getPPCare();
    //        getPPCareChild();
    //        int res = theAfterMCHMotherControl.saveOrUpdatePPCareN(thePPCare,thePPCareChild);
    //        setPPCare(thePPCare);
    //        setPPCareChild(thePPCareChild);
        }
        else
        {
            if(theBornMch!=null)
            {
                this.getMchDetail();
                theBornMch.gravida = theMchDetail.gravida;
                theBornMch.lborn = theMchDetail.alive_baby;
                theBornMch.sborn = theMchDetail.death_baby;
                theBornMch.bplace = theMchDetail.born_place;
                theBornMch.bdoctor = theMchDetail.bddoctor;
                theBornMch.birthmethod = theMchDetail.birth_method;
                theBornMch.bresult = theMchDetail.born_result;
                theBornMch.bhosp = theMchDetail.born_at_hospital;
                this.theHM.theHosControl.theAfterMCHMotherControl.saveBornMCHMother2(theBornMch);
            }
            if(this.theAfterMchMother1==null)
            {
                AfterMchMother afterMchMother = new AfterMchMother();
                afterMchMother.setObject(this.getAfterMchMother1(),"1");
                theHM.theHosControl.theAfterMCHMotherControl.saveAfterMCHMother2(afterMchMother);
            }
            else
            {
                theAfterMchMother1.setObject(this.getAfterMchMother1(),"1");
                theHM.theHosControl.theAfterMCHMotherControl.saveAfterMCHMother2(theAfterMchMother1);
            }
            if(this.theAfterMchMother2==null)
            {
                AfterMchMother afterMchMother = new AfterMchMother();
                afterMchMother.setObject(this.getAfterMchMother2(),"2");
                theHM.theHosControl.theAfterMCHMotherControl.saveAfterMCHMother2(afterMchMother);
            }
            else
            {
                theAfterMchMother2.setObject(this.getAfterMchMother2(),"2");
                theHM.theHosControl.theAfterMCHMotherControl.saveAfterMCHMother2(theAfterMchMother2);
            }
            if(this.theAfterMchMother3==null)
            {
                AfterMchMother afterMchMother = new AfterMchMother();
                afterMchMother.setObject(this.getAfterMchMother3(),"3");
                theHM.theHosControl.theAfterMCHMotherControl.saveAfterMCHMother2(afterMchMother);
            }
            else
            {
                theAfterMchMother3.setObject(this.getAfterMchMother3(),"3");
                theHM.theHosControl.theAfterMCHMotherControl.saveAfterMCHMother2(theAfterMchMother3);
            }
        }
}//GEN-LAST:event_jButton6ActionPerformed

    private void jTableMCHDetailMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMCHDetailMouseReleased
        int index = jTableMCHDetail.getSelectedRow();
        MchDetail mchDetail=(MchDetail) theMchDetailV.get(index);
        
        this.setMchDetail(mchDetail);
        theAfterMchMother = this.theAfterMCHMotherControl.readtMchByMchDetailID(mchDetail.getObjectId());
        setAfterMchMother(theAfterMchMother);

        theAfterMchMother1 = null;
        theAfterMchMother2 = null;
        theAfterMchMother3 = null;
        this.jLabelInfo.setText("");
    }//GEN-LAST:event_jTableMCHDetailMouseReleased

    private void jTextFieldDeclayedToothActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDeclayedToothActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDeclayedToothActionPerformed

    private void jTextFieldDeclayedToothFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDeclayedToothFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDeclayedToothFocusLost

    private void jTextFieldDeclayedToothKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDeclayedToothKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDeclayedToothKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(pcuobject.getPatient()==null)
        {
            theUS.setStatus(GutilPCU.getTextBundle("No_Patient_Appointment"),UpdateStatus.WARNING);
            return;
        }
        theHosDialog.showDialogAppointment(theHM,pcuobject);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(pcuobject.getPatient()==null)
        {
            theUS.setStatus(GutilPCU.getTextBundle("No_Patient_Appointment"),UpdateStatus.WARNING);
            return;
        }
        theHosDialog.showDialogAppointment(theHM,pcuobject);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(pcuobject.getPatient()==null)
        {
            theUS.setStatus(GutilPCU.getTextBundle("No_Patient_Appointment"),UpdateStatus.WARNING);
            return;
        }
        theHosDialog.showDialogAppointment(theHM,pcuobject);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        getAfterMchMother1();
        theHosDialog.showPanelAfterMch(theHM,theAfterMchMother,"1");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        getAfterMchMother2();
        theHosDialog.showPanelAfterMch(theHM,theAfterMchMother,"2");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        getAfterMchMother3();
        theHosDialog.showPanelAfterMch(theHM,theAfterMchMother,"3");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setMchDetail(null);
        this.setAfterMchMother(null);
        jTableMCHDetail.clearSelection();

        theAfterMchMother1 = null;
        theAfterMchMother2 = null;
        theAfterMchMother3 = null;
        this.jLabelInfo.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldGravidaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldGravidaFocusLost

        if(jTextFieldGravida.getText().equals("")) {

            theUS.setStatus(GutilPCU.getTextBundle("FillCareTime"), UpdateStatus.WARNING);

        }
    }//GEN-LAST:event_jTextFieldGravidaFocusLost

    private void jTextFieldGravidaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldGravidaKeyReleased
        String txt = jTextFieldGravida.getText();
        if(txt.length()>=1)
            jTextFieldBornAmount.requestFocus();
    }//GEN-LAST:event_jTextFieldGravidaKeyReleased

    private void jTextFieldBornAmountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldBornAmountFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBornAmountFocusLost

    private void jTextFieldBornAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBornAmountKeyReleased
        String txt = jTextFieldBornAmount.getText();
        if(txt.length()>=1)
            jTextFieldDeathBaby.requestFocus();
    }//GEN-LAST:event_jTextFieldBornAmountKeyReleased

    private void jTextFieldDeathBabyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDeathBabyFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDeathBabyFocusLost

    private void jTextFieldDeathBabyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDeathBabyKeyReleased
        String txt = jTextFieldDeathBaby.getText();
        if(txt.length()>=1)
            jTextFieldAliveBaby.requestFocus();
    }//GEN-LAST:event_jTextFieldDeathBabyKeyReleased

    private void jTextFieldAliveBabyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldAliveBabyFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAliveBabyFocusLost

    private void jTextFieldAliveBabyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAliveBabyKeyReleased
        
    }//GEN-LAST:event_jTextFieldAliveBabyKeyReleased

    private void jTextFieldDeathBabyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDeathBabyKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDeathBabyKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int select = JOptionPane.showConfirmDialog(this.theUS.getJFrame(), "ยืนยันการลบรายการ", "ยืนยันการลบรายการ",JOptionPane.YES_NO_OPTION);
        if(select==JOptionPane.NO_OPTION)
        {
            return;
        }

        int index = jTableMCHDetail.getSelectedRow();
        if(index < 0)
        {
            this.theHM.theUS.setStatus("กรุณาเลือกรายการ",UpdateStatus.WARNING);
            return;
        }
        MchDetail mchDetail = (MchDetail) this.theMchDetailV.get(index);
        mchDetail.active = "0";
        theAfterMCHMotherControl.saveMchDetail(mchDetail);

        AfterMchMother afterMchMother = null;
        afterMchMother = this.theAfterMCHMotherControl.readtMchByMchDetailID(mchDetail.getObjectId());
        afterMchMother.active = "0";
        theHM.theHosControl.theAfterMCHMotherControl.saveAfterMCHMotherN(afterMchMother);

        this.setObject(pcuobject);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        theAfterMchMother1 = theHM.theHosControl.theAfterMCHMotherControl.readAfterMchMotherByFamilyIDAndGravidaAndNumber
                (theHM.theHO.theFamily.getObjectId(), jTextFieldGravida.getText(), "1");
        theAfterMchMother2 = theHM.theHosControl.theAfterMCHMotherControl.readAfterMchMotherByFamilyIDAndGravidaAndNumber
                (theHM.theHO.theFamily.getObjectId(), jTextFieldGravida.getText(), "2");
        theAfterMchMother3 = theHM.theHosControl.theAfterMCHMotherControl.readAfterMchMotherByFamilyIDAndGravidaAndNumber
                (theHM.theHO.theFamily.getObjectId(), jTextFieldGravida.getText(), "3");
        AfterMchMother tmp = new AfterMchMother();
        tmp.setObject1(theAfterMchMother1);
        tmp.setobject2(theAfterMchMother2);
        tmp.setobject3(theAfterMchMother3);
        
        if(theAfterMchMother1!=null||theAfterMchMother2!=null||theAfterMchMother2!=null)
        {
            jLabelInfo.setText("ข้อมูลจากหน้าจอเก่า");
            jLabelInfo.setForeground(new java.awt.Color(0, 0, 255));
            setAfterMchMother(tmp);
        }
        else
        {
            jLabelInfo.setText("");
            jLabelInfo.setForeground(new java.awt.Color(0, 0, 0));
        }

        BornMch bornMch = this.theHM.theHosControl.theAfterMCHMotherControl.readBornMchMotherByFamilyIDAndGravida
                (this.theHM.theHO.theFamily.getObjectId(), jTextFieldGravida.getText());
        MchDetail mchDetail = new MchDetail();
        theBornMch = bornMch;
        if(bornMch!=null)
        {
            mchDetail.setObject(bornMch,null,null);
            jLabelInfo.setText("ข้อมูลจากหน้าจอเก่า");
            jLabelInfo.setForeground(new java.awt.Color(0, 0, 255));
            this.setMchDetail(mchDetail);
        }
        Pregnancy pregnancy = theHM.theHosControl.theHealthServiceControl.readPregnancyByFamilyIDAndGravida
                (this.theHM.theHO.theFamily.getObjectId(), jTextFieldGravida.getText());
        if(pregnancy != null)
        {
            mchDetail.setObject(bornMch,pregnancy,null);
            jLabelInfo.setText("ข้อมูลจากหน้าจอเก่า");
            jLabelInfo.setForeground(new java.awt.Color(0, 0, 255));
            this.setMchDetail(mchDetail);
        }
        if(pregnancy!=null)
        {
            Vector ancPcuV = null;
            ancPcuV = theHM.theHosControl.theHealthServiceControl.listAncByPregnantID(pregnancy.getObjectId());
            if(ancPcuV!=null && !ancPcuV.isEmpty())
            {
                AncPcu anc = (AncPcu) ancPcuV.get(ancPcuV.size()-1);
//                AncDetailPcu adp = theHM.theHosControl.theHealthServiceControl.readAncDetailByAncId(anc.getObjectId());
//                System.err.println(adp.anc_detail_fundus_height);
//                System.err.println(anc.anc_weight);
//                System.err.println(anc.anc_high);
                mchDetail.setObject(bornMch,pregnancy,anc);
                this.setMchDetail(mchDetail);
            }

        }
    }//GEN-LAST:event_jButton10ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupResult1;
    private javax.swing.ButtonGroup buttonGroupResult2;
    private javax.swing.ButtonGroup buttonGroupResult3;
    private com.hospital_os.utility.DateComboBox dateComboBoxBirthDate;
    private com.hospital_os.utility.DateComboBox dateComboBoxDental;
    private com.hospital_os.utility.DateComboBox dateComboBoxHCTDate;
    private com.hospital_os.utility.DateComboBox dateComboBoxLastMenDate;
    private com.hospital_os.utility.DateComboBox dateComboBoxTo10;
    private com.hospital_os.utility.DateComboBox dateComboBoxTo4;
    private com.hospital_os.utility.DateComboBox dateComboBoxTo8;
    private com.hospital_os.utility.DateComboBox dateComboBoxTo9;
    private javax.swing.JTextField integerTextFieldHosSubCode;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonHosSub;
    private javax.swing.JComboBox jComboBoxBddoctor;
    private javax.swing.JComboBox jComboBoxBirthMethod;
    private javax.swing.JComboBox jComboBoxBornPlace;
    private javax.swing.JComboBox jComboBoxHBRS;
    private javax.swing.JComboBox jComboBoxHIVRS;
    private javax.swing.JComboBox jComboBoxInfameGum;
    private javax.swing.JComboBox jComboBoxLimeStone;
    private javax.swing.JComboBox jComboBoxPlace1;
    private javax.swing.JComboBox jComboBoxPlace2;
    private javax.swing.JComboBox jComboBoxPlace3;
    private javax.swing.JComboBox jComboBoxResultICD10;
    private javax.swing.JComboBox jComboBoxThalassaemia;
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
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelInfo;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButtonAbNormal1;
    private javax.swing.JRadioButton jRadioButtonAbNormal2;
    private javax.swing.JRadioButton jRadioButtonAbNormal3;
    private javax.swing.JRadioButton jRadioButtonNormal1;
    private javax.swing.JRadioButton jRadioButtonNormal2;
    private javax.swing.JRadioButton jRadioButtonNormal3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableMCHDetail;
    private javax.swing.JTextArea jTextAreaSymptomNotice1;
    private javax.swing.JTextArea jTextAreaSymptomNotice2;
    private javax.swing.JTextArea jTextAreaSymptomNotice3;
    private com.pcu.utility.IntegerTextField jTextFieldAliveBaby;
    private com.pcu.utility.IntegerTextField jTextFieldBornAmount;
    private com.pcu.utility.IntegerTextField jTextFieldDeathBaby;
    private javax.swing.JTextField jTextFieldDeclayedTooth;
    private com.pcu.utility.IntegerTextField jTextFieldGravida;
    private javax.swing.JTextField jTextFieldHctLavel;
    private javax.swing.JTextField jTextFieldHosSub;
    private com.hospital_os.utility.TimeTextField timeTextFieldCheck;
    // End of variables declaration//GEN-END:variables

}
