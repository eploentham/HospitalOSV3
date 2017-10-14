/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelBornMchMother2.java
 *
 * Created on 29 พ.ย. 2553, 11:50:51
 */

package com.pcu.gui.panelpcu;

import com.hospital_os.object.Office;
import com.hospital_os.utility.ComboFix;
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.TaBleModel;
import com.hosv3.gui.dialog.HosDialog;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.control.HosManage;
import com.pcu.control.PCUObject;
import com.pcu.object.AfterMchMother;
import com.pcu.object.BornMch;
import com.pcu.object.Family;
import com.pcu.object.PP;
import com.pcu.object.PPCare;
import com.pcu.object.PcuAnswer;
import com.pcu.utility.GutilPCU;
import java.util.Vector;
import javax.swing.JDialog;

/**
 *
 * @author LionHeart
 */
public class PanelBornMchMother2 extends javax.swing.JPanel {

    /** Creates new form PanelBornMchMother2 */
    HosManage theHM;
    HosDialog theHD;
    UpdateStatus theUS;
    PanelBornMchMother thePanelBornMchMother;
    PanelPP thePanelPP;
    PanelAfterMchChild thePanelAfterMchChild;
    PanelAfterMchMother thePanelAfterMchMother;
    private JDialog theJD;
    Vector theBornMchV;
    BornMch theBornMch;
    PP thePP;
    AfterMchMother theAfterMchMother;
    PPCare thePPCare;
    String[] cols = {"ครรภ์ที่","วันที่คลอด","บุตรคลอด(คน)"};
    int mch_mother = 1;
    boolean mch_mother_b = false;
    int mch_child = 1;
    boolean mch_child_b = false;
    public PanelBornMchMother2() {
        initComponents();
    }
    public void setPanelAfterMchMother(PanelAfterMchMother panelAfterMchMother)
    {
        thePanelAfterMchMother = panelAfterMchMother;
    }
    public void setPanelAfterMchChild(PanelAfterMchChild panelAfterMchChild)
    {
        thePanelAfterMchChild = panelAfterMchChild;
    }
    public void setPanelBornMchMother(PanelBornMchMother panelBornMchMother)
    {
        thePanelBornMchMother = panelBornMchMother;
        thePanelBornMchMother.setPanelBornMchMother2(this);
    }
    public void setPanelPP(PanelPP panelPP)
    {
        thePanelPP = panelPP;
    }
    public void setControl(HosManage hm,HosDialog hd,UpdateStatus us)
    {
        theHM = hm;
        theHD = hd;
        theUS = us;

        ComboboxModel.initComboBox(jComboBoxBornMethod,theHM.theHosControl.theAllComboBoxControl.listBirthMethod());
        ComboboxModel.initComboBox(jComboBoxResultGiveBirth, theHM.theHosControl.theAllComboBoxControl.listResultGiveBirth());
        ComboboxModel.initComboBox(jComboBoxPreResult, theHM.theHosControl.theAllComboBoxControl.listResultStatus());
        ComboboxModel.initComboBox(jComboBoxBirthPlace, theHM.theHosControl.theAllComboBoxControl.listPostpartumBirthPlace());
        ComboboxModel.initComboBox(jComboBoxBirthResult, theHM.theHosControl.theAllComboBoxControl.comboBoxViewICD10Pregnant());
//        jComboBoxBornMethod.setEnabled(false);
//        jComboBoxResultGiveBirth.setEnabled(false);
//        jComboBoxPreResult.setEnabled(false);
//        jComboBoxBirthPlace.setEnabled(false);
//        jComboBoxBirthResult.setEnabled(false);
//        dateComboBoxBornDate.setEnabled(false);  jComboBoxAspiration
        Vector vPPBreathingTime = theHM.theHosControl.theAllComboBoxControl.listPPBreathingTime();
        ComboboxModel.initComboBox(jComboBoxBreathingTime,vPPBreathingTime);
        ComboboxModel.initComboBox(jComboBoxCryingTime,vPPBreathingTime);
        ComboboxModel.initComboBox(jComboBoxEyeDrug,theHM.theHosControl.theAllComboBoxControl.listPPEyeDrug());
        ComboboxModel.initComboBox(jComboBoxMethod,theHM.theHosControl.theAllComboBoxControl.listBirthMethodForPP());
        ComboboxModel.initComboBox(jComboBoxAspiration,theHM.theHosControl.theAllComboBoxControl.listPPAspiration());

        ComboboxModel.initComboBox(jComboBoxCheckPlace,theHM.theHosControl.theAllComboBoxControl.listPostpartumBirthPlace());
        ComboboxModel.initComboBox(jComboBoxSew,theHM.theHosControl.theAllComboBoxControl.listPostpartumEpisiotomyType());

        ComboboxModel.initComboBox(jComboBoxSugarLevel,theHM.theHosControl.theAllComboBoxControl.listPregnancyPregnantLevel());
        ComboboxModel.initComboBox(jComboBoxAlblumin,theHM.theHosControl.theAllComboBoxControl.listPregnancyPregnantLevel());

        ComboboxModel.initComboBox(jComboBoxCheckPlace2,theHM.theHosControl.theAllComboBoxControl.listPostpartumBirthPlace());

        ComboboxModel.initComboBox(jComboBoxSex,theHM.theHosControl.theAllComboBoxControl.listSex());
    }
    public void setObject(PCUObject pcuobject)
    {
        this.setBornMch(null);
        this.setAfterMchMother(null);
        this.setPP(null);
        this.setPPCare(null);
//        theBornMch=null;
//        thePP=null;
//        theAfterMchMother=null;
//        thePPCare=null;
        Vector vBornMother = theHM.theHosControl.theAfterMCHMotherControl.selectBornMchMotherShowGUITableByFamilyID();
        setBornMchrV(vBornMother);

        
        PP pp = theHM.theHosControl.theAfterMCHMotherControl.readPPByChildFid(theHM.theHO.theFamily.getObjectId());
//        if(pp != null && jComboBox5.getSelectedItem() != null)
        if(pp != null)
        {
            this.setPP(pp);
            if(jComboBox5.getSelectedItem()==null)
            {
                int num = Integer.parseInt(pp.pp_number);
                jComboBox5.removeAllItems();
                for(int i =1;i<=num;i++)
                    jComboBox5.addItem(i);
            }

            jComboBox5.setSelectedItem(pp.pp_number);

            //            PPCare ppCare = theHM.theHosControl.theAfterMCHMotherControl.readPPCareByFamilyIDAndGravida
//                    (pp.family_id,jComboBox5.getSelectedItem().toString());


            PPCare ppCare = theHM.theHosControl.theAfterMCHMotherControl.readPPCareByFamilyIDAndGravida
                (pp.family_id,"1");


            if(ppCare!=null)
                jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลลูกหลังคลอดครั้งที่ 1"));
            else
                jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลลูกหลังคลอดครั้งที่ -"));
            this.setPPCare(ppCare);
            mch_child = 1;
            jComboBox5.setEnabled(false);

            
//            System.err.println("before");
//            if(!ppCare.pp_care_number.equals(""))
//            {
//                System.err.println("added");
//                System.err.println("ppCare.pp_care_number "+ppCare.pp);
//                jComboBox5.addItem(ppCare.pp_care_number);
//                jComboBox5.setSelectedItem(ppCare.pp_care_number);
//            }
//            jButton6.setEnabled(false);
//            if(pp.pp_number.equals(""))
//                jComboBox5.removeAllItems();
//            else
//            {
//                int num = Integer.parseInt(pp.pp_number);
//                jComboBox5.removeAllItems();
//                for(int i =1;i<=num;i++)
//                    jComboBox5.addItem(i);
//            }
        }
        else
        {
            jComboBox5.setEnabled(true);
        }
        


        if(!vBornMother.isEmpty())
        {
            jTableBornMch.setRowSelectionInterval(0,0);
            jTableBornMchMouseReleased(null);
        }
    }
    public void setBornMchrV(Vector bornMotherV)
    {
        TaBleModel tm;
        theBornMchV = bornMotherV;
        if(theBornMchV == null)
            theBornMchV = new Vector();
        tm = new TaBleModel(cols,theBornMchV.size());
        for(int i=0;i<theBornMchV.size();i++)
        {
            BornMch tmp = (BornMch) theBornMchV.get(i);
            tm.setValueAt(tmp.gravida, i,0);
            tm.setValueAt(GutilPCU.changDateToString(tmp.bdate), i,1);
            tm.setValueAt(tmp.lborn, i,2);
        }
        jTableBornMch.setModel(tm);
    }
    public void setBornMch(BornMch bornMch)
    {
        theBornMch = bornMch;
        if(theBornMch == null)
            theBornMch = new BornMch();
        dateComboBoxBornDate.setText(Gutil.convertFieldDate(theBornMch.bdate));
        timeTextFieldBornTime.setText(theBornMch.btime);
        jTextFieldAliveBaby.setText(theBornMch.lborn);
        jTextFieldDeathBaby.setText(theBornMch.sborn);
        ComboboxModel.setCodeComboBox(jComboBoxBornMethod, theBornMch.birthmethod);
        ComboboxModel.setCodeComboBox(jComboBoxResultGiveBirth, theBornMch.btype);
        ComboboxModel.setCodeComboBox(jComboBoxPreResult, theBornMch.presult);
        ComboboxModel.setCodeComboBox(jComboBoxBirthResult, theBornMch.bresult);
        ComboboxModel.setCodeComboBox(jComboBoxBirthPlace, theBornMch.bplace);
    }
    public void setPPCare(PPCare ppCare)
    {
        thePPCare = ppCare;
        if(thePPCare == null)
        {
            thePPCare = new PPCare();
            dateComboBoxSurvey2.setText("");
        }
        dateComboBoxSurvey2.setText("");
        dateComboBoxSurvey2.setText(Gutil.convertFieldDate(thePPCare.survey_date));
        if(!thePPCare.pp_care_deliver_place.equals(""))
        {
            Office theOffice = this.theHM.theHosControl.theHealthSchoolServiceControl.selectOfficeByPK(thePPCare.pp_care_deliver_place);
            jLabel28.setText(theOffice.getName());
        }
        else
        {
            jLabel28.setText("");
        }
        if(thePPCare.pp_care_result.equals("1"))
            jLabel16.setText("ปกติ");
        else if(thePPCare.pp_care_result.equals("2"))
            jLabel16.setText("ผิดปกติ");
        if(thePPCare.pp_care_dermis.equals("1"))
            jLabel32.setText("ปกติ");
        else if(thePPCare.pp_care_dermis.equals("0"))
            jLabel32.setText("ผิดปกติ");
        if(thePPCare.pp_care_navel.equals("1"))
            jLabel34.setText("ปกติ");
        else if(thePPCare.pp_care_navel.equals("0"))
            jLabel34.setText("ผิดปกติ");
        if(thePPCare.pp_care_feces.equals("1"))
            jLabel36.setText("ปกติ");
        else if(thePPCare.pp_care_feces.equals("0"))
            jLabel36.setText("ผิดปกติ");
        if(thePPCare.pp_care_urine.equals("1"))
            jLabel44.setText("ปกติ");
        else if(thePPCare.pp_care_urine.equals("0"))
            jLabel44.setText("ผิดปกติ");
        ComboboxModel.setCodeComboBox(jComboBoxCheckPlace2, thePPCare.pp_care_survey_place);
        jTextArea2.setText(thePPCare.pp_care_state);
    }
    public void setPP(PP pp)
    {
        thePP = pp;
        if(thePP == null)
            thePP = new PP();
        ComboboxModel.setCodeComboBox(jComboBoxBreathingTime,thePP.pp_breathing_time);
        ComboboxModel.setCodeComboBox(jComboBoxCryingTime,thePP.pp_crying_time);
        ComboboxModel.setCodeComboBox(jComboBoxEyeDrug,thePP.pp_eye_drug);
        ComboboxModel.setCodeComboBox(jComboBoxMethod,thePP.pp_method_bearing);
        Family family = this.theHM.theHC.thePatientControl.readFamilyByFamilyIdRet(thePP.family_id);
        if(family!=null)
        {
            ComboboxModel.setCodeComboBox(jComboBoxSex,family.f_sex_id);
        }
        jTextFieldWeight.setText(thePP.pp_weight);
//        System.err.println("thePP "+(thePP==null));
//        System.err.println("theHM "+(theHM==null));
//        System.err.println("theHM.theHosControl "+(theHM.theHosControl==null));
//        if(!thePP.family_id.equals(""))
//        {
//            Family child = theHM.theHosControl.thePatientControl.readFamilyByFamilyIdRet(thePP.family_id);
//            ComboboxModel.setCodeComboBox(jComboBoxSex,child.f_sex_id);
//        }
        if(thePP.pp_initial_condition.equals(PcuAnswer.Zero()))
            jLabel51.setText("Poor");
        else if(thePP.pp_initial_condition.equals(PcuAnswer.One()))
            jLabel51.setText("Good");
        else
            jLabel51.setText("");
        if(thePP.pp_color.equals(PcuAnswer.Zero()))
            jLabel12.setText("Blue");
        else if(thePP.pp_color.equals(PcuAnswer.One()))
            jLabel12.setText("Pink");
        else
            jLabel12.setText("");
        if(thePP.pp_vit_k.equals(PcuAnswer.Zero()))
            jLabel29.setText("ไม่ฉีด");
        else if(thePP.pp_vit_k.equals(PcuAnswer.One()))
            jLabel29.setText("ฉีด");
        else
            jLabel29.setText("");
        if(thePP.pp_activity.equals(PcuAnswer.Zero()))
            jLabel41.setText("Weak");
        else if(thePP.pp_activity.equals(PcuAnswer.One()))
            jLabel41.setText("Good");
        else
            jLabel41.setText("");
        if(thePP.pp_lost_oxygen.equals(PcuAnswer.Zero()))
            jLabel49.setText("ขาด");
        else if(thePP.pp_lost_oxygen.equals(PcuAnswer.One()))
            jLabel49.setText("ไม่ขาด");
        else
            jLabel49.setText("");
        ComboboxModel.setCodeComboBox(jComboBoxAspiration,thePP.pp_aspiration);
//        ComboFix p = (ComboFix)vAspiration.get(thePP.pp_aspiration);
    }
    public void setAfterMchMother(AfterMchMother afterMchMother)
    {
        theAfterMchMother = afterMchMother;
        if(theAfterMchMother == null)
            theAfterMchMother = new AfterMchMother();
        dateComboBoxSurvey.setText(Gutil.convertFieldDate(theAfterMchMother.survey_date));
        ComboboxModel.setCodeComboBox(jComboBoxCheckPlace,theAfterMchMother.place);
        jTextAreaNote.setText(theAfterMchMother.symptom_notice);
        ComboboxModel.setCodeComboBox(jComboBoxSew,theAfterMchMother.sew);
        if(theAfterMchMother.menses.equals("1"))
            jLabel38.setText("ปกติ");
        else if(theAfterMchMother.menses.equals("0"))
            jLabel38.setText("ผิดปกติ");
        if(theAfterMchMother.menses.equals("1"))
            jLabel38.setText("ปกติ");
        else if(theAfterMchMother.menses.equals("0"))
            jLabel38.setText("ผิดปกติ");
        if(theAfterMchMother.result_verify.equals("1"))
            jLabel26.setText("ปกติ");
        else if(theAfterMchMother.result_verify.equals("0"))
            jLabel26.setText("ผิดปกติ");
        ComboboxModel.setCodeComboBox(jComboBoxSugarLevel,theAfterMchMother.sugar_level);
        if(theAfterMchMother.cream.equals("1"))
            jLabel46.setText("ปกติ");
        else if(theAfterMchMother.cream.equals("0"))
            jLabel46.setText("ผิดปกติ");
        ComboboxModel.setCodeComboBox(jComboBoxAlblumin,theAfterMchMother.albumin);
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
        jLabel9 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jComboBoxSugarLevel = new javax.swing.JComboBox();
        jComboBoxAlblumin = new javax.swing.JComboBox();
        jPanel11 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        dateComboBoxSurvey = new com.hospital_os.utility.DateComboBox();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxCheckPlace = new javax.swing.JComboBox();
        jLabel39 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBoxSew = new javax.swing.JComboBox();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaNote = new javax.swing.JTextArea();
        jButton8 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxCheckPlace2 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton9 = new javax.swing.JButton();
        dateComboBoxSurvey2 = new com.hospital_os.utility.DateComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldWeight = new com.pcu.utility.IntegerTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jComboBoxSex = new javax.swing.JComboBox();
        jComboBoxCryingTime = new javax.swing.JComboBox();
        jComboBoxEyeDrug = new javax.swing.JComboBox();
        jComboBoxMethod = new javax.swing.JComboBox();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jComboBoxAspiration = new javax.swing.JComboBox();
        jButton6 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jComboBoxBreathingTime = new javax.swing.JComboBox();
        jComboBox5 = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBornMch = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldDeathBaby = new com.pcu.utility.IntegerTextField();
        jTextFieldAliveBaby = new com.pcu.utility.IntegerTextField();
        jLabel18 = new javax.swing.JLabel();
        jComboBoxBornMethod = new javax.swing.JComboBox();
        jComboBoxBirthPlace = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        dateComboBoxBornDate = new com.hospital_os.utility.DateComboBox();
        jLabel14 = new javax.swing.JLabel();
        timeTextFieldBornTime = new com.hospital_os.utility.TimeTextField();
        jButton7 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jComboBoxResultGiveBirth = new javax.swing.JComboBox();
        jComboBoxPreResult = new javax.swing.JComboBox();
        jPanel18 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jComboBoxBirthResult = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลแม่หลังคลอดครั้งที่ -"));
        jPanel1.setMinimumSize(new java.awt.Dimension(541, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(554, 0));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel9.setText("สรุปผลการตรวจ:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel9, gridBagConstraints);

        jLabel26.setText("-");
        jLabel26.setMinimumSize(new java.awt.Dimension(50, 14));
        jLabel26.setPreferredSize(new java.awt.Dimension(50, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel1.add(jLabel26, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel43.setText("ระดับน้ำตาล");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel8.add(jLabel43, gridBagConstraints);

        jLabel45.setText("หัวนม:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel8.add(jLabel45, gridBagConstraints);

        jLabel46.setText("-");
        jLabel46.setMinimumSize(new java.awt.Dimension(80, 14));
        jLabel46.setPreferredSize(new java.awt.Dimension(80, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel8.add(jLabel46, gridBagConstraints);

        jLabel47.setText("Albumin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel8.add(jLabel47, gridBagConstraints);

        jComboBoxSugarLevel.setEditable(true);
        jComboBoxSugarLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxSugarLevel.setEnabled(false);
        jComboBoxSugarLevel.setFocusable(false);
        jComboBoxSugarLevel.setMinimumSize(new java.awt.Dimension(100, 18));
        jComboBoxSugarLevel.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel8.add(jComboBoxSugarLevel, gridBagConstraints);

        jComboBoxAlblumin.setEditable(true);
        jComboBoxAlblumin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxAlblumin.setEnabled(false);
        jComboBoxAlblumin.setMinimumSize(new java.awt.Dimension(100, 18));
        jComboBoxAlblumin.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jPanel8.add(jComboBoxAlblumin, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel1.add(jPanel8, gridBagConstraints);

        jPanel11.setLayout(new java.awt.GridBagLayout());

        jLabel7.setText("วันที่ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel11.add(jLabel7, gridBagConstraints);

        dateComboBoxSurvey.setEnabled(false);
        dateComboBoxSurvey.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxSurvey.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxSurvey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxSurveyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel11.add(dateComboBoxSurvey, gridBagConstraints);

        jLabel8.setText("สถานที่ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel11.add(jLabel8, gridBagConstraints);

        jComboBoxCheckPlace.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxCheckPlace.setEnabled(false);
        jComboBoxCheckPlace.setMinimumSize(new java.awt.Dimension(100, 18));
        jComboBoxCheckPlace.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel11.add(jComboBoxCheckPlace, gridBagConstraints);

        jLabel39.setText("ฝีเย็บ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel11.add(jLabel39, gridBagConstraints);

        jLabel37.setText("ประจำเดือน:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel11.add(jLabel37, gridBagConstraints);

        jLabel38.setText("-");
        jLabel38.setMinimumSize(new java.awt.Dimension(70, 14));
        jLabel38.setPreferredSize(new java.awt.Dimension(70, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel11.add(jLabel38, gridBagConstraints);

        jPanel13.setLayout(new java.awt.GridBagLayout());

        jButton2.setText("<");
        jButton2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton2, new java.awt.GridBagConstraints());

        jButton3.setText(">");
        jButton3.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton3, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel11.add(jPanel13, gridBagConstraints);

        jComboBoxSew.setEditable(true);
        jComboBoxSew.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxSew.setEnabled(false);
        jComboBoxSew.setMinimumSize(new java.awt.Dimension(70, 18));
        jComboBoxSew.setPreferredSize(new java.awt.Dimension(70, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        jPanel11.add(jComboBoxSew, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jPanel11, gridBagConstraints);

        jPanel15.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("บันทึกอาการ"));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(37, 0));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(50, 0));

        jTextAreaNote.setColumns(20);
        jTextAreaNote.setEditable(false);
        jTextAreaNote.setRows(5);
        jScrollPane2.setViewportView(jTextAreaNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel15.add(jScrollPane2, gridBagConstraints);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pcu/images/edit.gif"))); // NOI18N
        jButton8.setToolTipText("แก้ไขข้อมูลดูแลลูกหลังคลอด");
        jButton8.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        jPanel15.add(jButton8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel15, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
        add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลลูกหลังคลอดครั้งที่ -"));
        jPanel2.setMinimumSize(new java.awt.Dimension(16, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(16, 0));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel10.setText("วันที่ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jLabel10, gridBagConstraints);

        jComboBoxCheckPlace2.setEditable(true);
        jComboBoxCheckPlace2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxCheckPlace2.setEnabled(false);
        jComboBoxCheckPlace2.setMinimumSize(new java.awt.Dimension(100, 20));
        jComboBoxCheckPlace2.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(jComboBoxCheckPlace2, gridBagConstraints);

        jLabel16.setText("-");
        jLabel16.setMinimumSize(new java.awt.Dimension(60, 14));
        jLabel16.setPreferredSize(new java.awt.Dimension(60, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel2.add(jLabel16, gridBagConstraints);

        jLabel23.setText("สถานที่ตรวจ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel2.add(jLabel23, gridBagConstraints);

        jLabel25.setText("ผลการตรวจ:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel2.add(jLabel25, gridBagConstraints);

        jLabel27.setText("สถานที่คลอด:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel2.add(jLabel27, gridBagConstraints);

        jLabel28.setText("-");
        jLabel28.setMinimumSize(new java.awt.Dimension(150, 17));
        jLabel28.setPreferredSize(new java.awt.Dimension(150, 17));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel2.add(jLabel28, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jLabel31.setText("ผิวหนัง:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel7.add(jLabel31, gridBagConstraints);

        jLabel32.setText("-");
        jLabel32.setMinimumSize(new java.awt.Dimension(70, 14));
        jLabel32.setPreferredSize(new java.awt.Dimension(70, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel7.add(jLabel32, gridBagConstraints);

        jLabel33.setText("สะดือ:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel7.add(jLabel33, gridBagConstraints);

        jLabel34.setText("-");
        jLabel34.setMinimumSize(new java.awt.Dimension(70, 14));
        jLabel34.setPreferredSize(new java.awt.Dimension(70, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel7.add(jLabel34, gridBagConstraints);

        jLabel35.setText("อุจจาระ:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel7.add(jLabel35, gridBagConstraints);

        jLabel36.setText("-");
        jLabel36.setMinimumSize(new java.awt.Dimension(70, 14));
        jLabel36.setPreferredSize(new java.awt.Dimension(70, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel7.add(jLabel36, gridBagConstraints);

        jLabel40.setText("ปัสสาวะ:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel7.add(jLabel40, gridBagConstraints);

        jLabel44.setText("-");
        jLabel44.setMinimumSize(new java.awt.Dimension(50, 14));
        jLabel44.setPreferredSize(new java.awt.Dimension(50, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel7.add(jLabel44, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel2.add(jPanel7, gridBagConstraints);

        jPanel14.setLayout(new java.awt.GridBagLayout());

        jButton4.setText("<");
        jButton4.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton4, new java.awt.GridBagConstraints());

        jButton5.setText(">");
        jButton5.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton5, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel14, gridBagConstraints);

        jPanel16.setLayout(new java.awt.GridBagLayout());

        jScrollPane3.setBorder(null);
        jScrollPane3.setMinimumSize(new java.awt.Dimension(37, 0));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(50, 0));

        jTextArea2.setColumns(20);
        jTextArea2.setEditable(false);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel16.add(jScrollPane3, gridBagConstraints);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pcu/images/edit.gif"))); // NOI18N
        jButton9.setToolTipText("แก้ไขข้อมูลดูแลแม่หลังคลอด");
        jButton9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        jPanel16.add(jButton9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jPanel16, gridBagConstraints);

        dateComboBoxSurvey2.setEnabled(false);
        dateComboBoxSurvey2.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxSurvey2.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxSurvey2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxSurvey2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(dateComboBoxSurvey2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
        add(jPanel2, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("ข้อมูลเด็กทารก"));
        jPanel3.setMinimumSize(new java.awt.Dimension(200, 133));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 142));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel3MouseReleased(evt);
            }
        });
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Breating Time");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel3.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Crying Time");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel3.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Eye Drugs");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel3.add(jLabel3, gridBagConstraints);

        jLabel4.setText("วิธีการคลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel3.add(jLabel4, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText("น้ำหนัก");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel6.add(jLabel5, gridBagConstraints);

        jTextFieldWeight.setColumns(4);
        jTextFieldWeight.setEditable(false);
        jTextFieldWeight.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldWeight.setMinimumSize(new java.awt.Dimension(48, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel6.add(jTextFieldWeight, gridBagConstraints);

        jLabel6.setText("กรัม");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel6.add(jLabel6, gridBagConstraints);

        jLabel48.setText("เพศ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel6.add(jLabel48, gridBagConstraints);

        jComboBoxSex.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBoxSex.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel6.add(jComboBoxSex, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel3.add(jPanel6, gridBagConstraints);

        jComboBoxCryingTime.setEditable(true);
        jComboBoxCryingTime.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxCryingTime.setEnabled(false);
        jComboBoxCryingTime.setMinimumSize(new java.awt.Dimension(160, 20));
        jComboBoxCryingTime.setPreferredSize(new java.awt.Dimension(160, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel3.add(jComboBoxCryingTime, gridBagConstraints);

        jComboBoxEyeDrug.setEditable(true);
        jComboBoxEyeDrug.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxEyeDrug.setEnabled(false);
        jComboBoxEyeDrug.setMinimumSize(new java.awt.Dimension(160, 20));
        jComboBoxEyeDrug.setPreferredSize(new java.awt.Dimension(160, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel3.add(jComboBoxEyeDrug, gridBagConstraints);

        jComboBoxMethod.setEditable(true);
        jComboBoxMethod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxMethod.setEnabled(false);
        jComboBoxMethod.setMinimumSize(new java.awt.Dimension(160, 20));
        jComboBoxMethod.setPreferredSize(new java.awt.Dimension(160, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel3.add(jComboBoxMethod, gridBagConstraints);

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel11.setText("Color:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel9.add(jLabel11, gridBagConstraints);

        jLabel12.setText("-");
        jLabel12.setMinimumSize(new java.awt.Dimension(80, 14));
        jLabel12.setPreferredSize(new java.awt.Dimension(80, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 1, 0, 0);
        jPanel9.add(jLabel12, gridBagConstraints);

        jLabel29.setText("-");
        jLabel29.setMinimumSize(new java.awt.Dimension(80, 14));
        jLabel29.setPreferredSize(new java.awt.Dimension(80, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 1, 0, 0);
        jPanel9.add(jLabel29, gridBagConstraints);

        jLabel30.setText("Activity:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel9.add(jLabel30, gridBagConstraints);

        jLabel41.setText("-");
        jLabel41.setMinimumSize(new java.awt.Dimension(80, 14));
        jLabel41.setPreferredSize(new java.awt.Dimension(80, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 1, 0, 0);
        jPanel9.add(jLabel41, gridBagConstraints);

        jLabel42.setText("Oxygen:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 1, 0, 0);
        jPanel9.add(jLabel42, gridBagConstraints);

        jLabel49.setText("-");
        jLabel49.setMinimumSize(new java.awt.Dimension(80, 14));
        jLabel49.setPreferredSize(new java.awt.Dimension(80, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 1, 0, 0);
        jPanel9.add(jLabel49, gridBagConstraints);

        jLabel50.setText("Initial:");
        jLabel50.setToolTipText("Initial Condition");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel9.add(jLabel50, gridBagConstraints);

        jLabel51.setText("-");
        jLabel51.setToolTipText("Initial Condition");
        jLabel51.setMinimumSize(new java.awt.Dimension(80, 14));
        jLabel51.setPreferredSize(new java.awt.Dimension(80, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanel9.add(jLabel51, gridBagConstraints);

        jLabel52.setText("Aspiration");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel9.add(jLabel52, gridBagConstraints);

        jLabel54.setText("รับ Vit K:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 1, 0, 0);
        jPanel9.add(jLabel54, gridBagConstraints);

        jPanel17.setLayout(new java.awt.GridBagLayout());

        jComboBoxAspiration.setEditable(true);
        jComboBoxAspiration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxAspiration.setEnabled(false);
        jComboBoxAspiration.setMinimumSize(new java.awt.Dimension(180, 18));
        jComboBoxAspiration.setPreferredSize(new java.awt.Dimension(180, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel17.add(jComboBoxAspiration, gridBagConstraints);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pcu/images/edit.gif"))); // NOI18N
        jButton6.setToolTipText("แก้ไขข้อมูลเด็กทารก");
        jButton6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel17.add(jButton6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(jPanel17, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel3.add(jPanel9, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel24.setText("คนที่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel10.add(jLabel24, gridBagConstraints);

        jComboBoxBreathingTime.setEditable(true);
        jComboBoxBreathingTime.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxBreathingTime.setEnabled(false);
        jComboBoxBreathingTime.setMinimumSize(new java.awt.Dimension(100, 18));
        jComboBoxBreathingTime.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel10.add(jComboBoxBreathingTime, gridBagConstraints);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        jComboBox5.setMinimumSize(new java.awt.Dimension(40, 18));
        jComboBox5.setPreferredSize(new java.awt.Dimension(40, 20));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel10.add(jComboBox5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel3.add(jPanel10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
        add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("ประวัติการคลอด"));
        jPanel4.setMinimumSize(new java.awt.Dimension(39, 80));
        jPanel4.setPreferredSize(new java.awt.Dimension(166, 80));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(23, 0));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(150, 0));

        jTableBornMch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableBornMch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableBornMchMouseReleased(evt);
            }
        });
        jTableBornMch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableBornMchKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableBornMch);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        add(jPanel4, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("รายละเอียดการคลอด"));
        jPanel5.setMinimumSize(new java.awt.Dimension(297, 140));
        jPanel5.setPreferredSize(new java.awt.Dimension(311, 140));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel13.setText("วันที่คลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jLabel13, gridBagConstraints);

        jLabel15.setText("เด็กตาย");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel5.add(jLabel15, gridBagConstraints);

        jTextFieldDeathBaby.setColumns(4);
        jTextFieldDeathBaby.setEditable(false);
        jTextFieldDeathBaby.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldDeathBaby.setMinimumSize(new java.awt.Dimension(48, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jTextFieldDeathBaby, gridBagConstraints);

        jTextFieldAliveBaby.setColumns(4);
        jTextFieldAliveBaby.setEditable(false);
        jTextFieldAliveBaby.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldAliveBaby.setMinimumSize(new java.awt.Dimension(48, 19));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jTextFieldAliveBaby, gridBagConstraints);

        jLabel18.setText("คลอดที่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel5.add(jLabel18, gridBagConstraints);

        jComboBoxBornMethod.setEditable(true);
        jComboBoxBornMethod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxBornMethod.setEnabled(false);
        jComboBoxBornMethod.setMinimumSize(new java.awt.Dimension(110, 20));
        jComboBoxBornMethod.setPreferredSize(new java.awt.Dimension(110, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jComboBoxBornMethod, gridBagConstraints);

        jComboBoxBirthPlace.setEditable(true);
        jComboBoxBirthPlace.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxBirthPlace.setEnabled(false);
        jComboBoxBirthPlace.setMinimumSize(new java.awt.Dimension(100, 20));
        jComboBoxBirthPlace.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jComboBoxBirthPlace, gridBagConstraints);

        jLabel19.setText("เด็กเกิดมีชีพ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel5.add(jLabel19, gridBagConstraints);

        jLabel20.setText("วิธีการคลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jLabel20, gridBagConstraints);

        jLabel21.setText("ผลการคลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jLabel21, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        dateComboBoxBornDate.setEnabled(false);
        dateComboBoxBornDate.setMinimumSize(new java.awt.Dimension(90, 23));
        dateComboBoxBornDate.setPreferredSize(new java.awt.Dimension(90, 23));
        dateComboBoxBornDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxBornDateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel12.add(dateComboBoxBornDate, gridBagConstraints);

        jLabel14.setText("เวลาคลอด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel12.add(jLabel14, gridBagConstraints);

        timeTextFieldBornTime.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel12.add(timeTextFieldBornTime, gridBagConstraints);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/pcu/images/edit.gif"))); // NOI18N
        jButton7.setToolTipText("แก้ไขข้อมูลการคลอด");
        jButton7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel12.add(jButton7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel5.add(jPanel12, gridBagConstraints);

        jLabel22.setText("ผลตรวจมารดา");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jLabel22, gridBagConstraints);

        jComboBoxResultGiveBirth.setEditable(true);
        jComboBoxResultGiveBirth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxResultGiveBirth.setEnabled(false);
        jComboBoxResultGiveBirth.setMinimumSize(new java.awt.Dimension(110, 20));
        jComboBoxResultGiveBirth.setPreferredSize(new java.awt.Dimension(110, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jComboBoxResultGiveBirth, gridBagConstraints);

        jComboBoxPreResult.setEditable(true);
        jComboBoxPreResult.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxPreResult.setEnabled(false);
        jComboBoxPreResult.setMinimumSize(new java.awt.Dimension(110, 20));
        jComboBoxPreResult.setPreferredSize(new java.awt.Dimension(110, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel5.add(jComboBoxPreResult, gridBagConstraints);

        jPanel18.setLayout(new java.awt.GridBagLayout());

        jLabel17.setText("ผลสิ้นสุดตั้งครรภ์");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        jPanel18.add(jLabel17, gridBagConstraints);

        jComboBoxBirthResult.setEditable(true);
        jComboBoxBirthResult.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxBirthResult.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel18.add(jComboBoxBirthResult, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(jPanel18, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        add(jPanel5, gridBagConstraints);

        jButton1.setText("คลิกเพื่อลงข้อมูล");
        jButton1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 3);
        add(jButton1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        if(theJD==null)
//            theJD = new JDialog(theUS.getJFrame());
//        thePanelBornMchMother.jButtonContinue.setVisible(true);
//        thePanelBornMchMother.setPanelPP(thePanelPP);
//        theJD.add(thePanelBornMchMother);
//        theJD.setSize(800,600);
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, (screenSize.height - theJD.getSize().height) / 2);
//        theJD.setTitle("ข้อมูลการคลอด");
//        theJD.setModal(true);
//        theJD.setVisible(true);
        int select = jTableBornMch.getSelectedRow();
        if(select >= 0)
        {
            thePanelBornMchMother.jTableShowBorn.setRowSelectionInterval(select, select);
            thePanelBornMchMother.selectTableBornMch(select);
        }
        thePanelAfterMchMother.setPanelAfterMchChild(thePanelAfterMchChild);
        thePanelAfterMchChild.setPanelAfterMchMother(thePanelAfterMchMother);
        thePanelAfterMchChild.setPanelPP(thePanelPP);
        thePanelPP.setPanelAfterMchChild(thePanelAfterMchChild);
        thePanelPP.setPanelBornMchMother(thePanelBornMchMother);
        thePanelBornMchMother.setPanelPP(thePanelPP);
        thePanelBornMchMother.showDialog(true);
        thePanelBornMchMother.jButtonContinue.setVisible(false);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void dateComboBoxBornDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxBornDateActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_dateComboBoxBornDateActionPerformed

    private void timeTextFieldCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeTextFieldCheckMouseClicked

}//GEN-LAST:event_timeTextFieldCheckMouseClicked

    private void timeTextFieldCheckKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timeTextFieldCheckKeyReleased

}//GEN-LAST:event_timeTextFieldCheckKeyReleased

    private void dateComboBoxSurveyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxSurveyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateComboBoxSurveyActionPerformed

    private void jTableBornMchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBornMchMouseReleased
        int select = this.jTableBornMch.getSelectedRow();
        BornMch bornMch = (BornMch) theBornMchV.get(select);
        this.setBornMch(bornMch);
        
        PP pp = theHM.theHosControl.theAfterMCHMotherControl.readPPByPidAndGravidaAndPPNumber
                (this.theHM.theHO.thePatient.getObjectId(),this.theBornMch.gravida,"1");
        this.setPP(pp);

        String pp_number = theHM.theHosControl.theAfterMCHMotherControl.getPPNumberByPidAndGravida
                (this.theHM.theHO.thePatient.getObjectId(), this.theBornMch.gravida);
        if(pp_number.equals(""))
            jComboBox5.removeAllItems();
        else
        {
            int num = Integer.parseInt(pp_number);
            jComboBox5.removeAllItems();
            for(int i =1;i<=num;i++)
                jComboBox5.addItem(i);
        }

        AfterMchMother afterMchMother = theHM.theHosControl.theAfterMCHMotherControl.readAfterMchMotherByFamilyIDAndGravidaAndNumber
                (theHM.theHO.theFamily.getObjectId(),theBornMch.gravida,"1");
        if(afterMchMother!=null)
            jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลแม่หลังคลอดครั้งที่ 1"));
        else
            jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลแม่หลังคลอดครั้งที่ -"));
        setAfterMchMother(afterMchMother);
        mch_mother = 1;
    }//GEN-LAST:event_jTableBornMchMouseReleased

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        this.setPPCare(null);
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลลูกหลังคลอดครั้งที่ -"));
        if(theBornMch!=null && jComboBox5.getSelectedItem()!=null)
        {
            PP pp = theHM.theHosControl.theAfterMCHMotherControl.readPPByPidAndGravidaAndPPNumber
                    (this.theHM.theHO.thePatient.getObjectId(),this.theBornMch.gravida
                    ,jComboBox5.getSelectedItem().toString());
            mch_child = 1;
            if(pp==null)
            {
                return;
            }
            this.setPP(pp);
            
            PPCare ppCare = theHM.theHosControl.theAfterMCHMotherControl.readPPCareByFamilyIDAndGravida
                    (pp.family_id,"1");

            if(ppCare!=null)
                jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลลูกหลังคลอดครั้งที่ 1"));
            else
                jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลลูกหลังคลอดครั้งที่ -"));
            this.setPPCare(ppCare);
            
        }

    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int tmp = mch_mother;
        if(mch_mother_b)
        {
            mch_mother++;
            mch_mother_b = false;
        }
        if(mch_mother > 1)
            mch_mother--;
        AfterMchMother afterMchMother = theHM.theHosControl.theAfterMCHMotherControl.readAfterMchMotherByFamilyIDAndGravidaAndNumber
                (theHM.theHO.theFamily.getObjectId(),theBornMch.gravida,String.valueOf(mch_mother));
        if(afterMchMother!=null)
            jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลแม่หลังคลอดครั้งที่ "+mch_mother));
        else
        {
            jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลแม่หลังคลอดครั้งที่ -"));
            mch_mother = tmp;
        }
        setAfterMchMother(afterMchMother);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(mch_mother < 3)
            mch_mother++;
        AfterMchMother afterMchMother = theHM.theHosControl.theAfterMCHMotherControl.readAfterMchMotherByFamilyIDAndGravidaAndNumber
                (theHM.theHO.theFamily.getObjectId(),theBornMch.gravida,String.valueOf(mch_mother));
        if(afterMchMother!=null)
        {
            jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลแม่หลังคลอดครั้งที่ "+mch_mother));
        }
        else
        {
            mch_mother--;
            jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลแม่หลังคลอดครั้งที่ -"));
            mch_mother_b = true;
        }
        setAfterMchMother(afterMchMother);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTableBornMchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableBornMchKeyReleased
        jTableBornMchMouseReleased(null);
    }//GEN-LAST:event_jTableBornMchKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int tmp = mch_child;
        if(mch_child_b)
        {
            mch_child++;
            mch_child_b = false;
        }
        if(mch_child > 1)
            mch_child--;
        PPCare ppCare = theHM.theHosControl.theAfterMCHMotherControl.readPPCareByFamilyIDAndGravida
                (this.thePP.family_id,String.valueOf(mch_child));
        if(ppCare!=null)
        {
            jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลลูกหลังคลอดครั้งที่ "+mch_child));
        }
        else
        {
            jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลลูกหลังคลอดครั้งที่ -"));
            mch_child = tmp;
        }
        this.setPPCare(ppCare);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(mch_child < 3)
            mch_child++;
        PPCare ppCare = theHM.theHosControl.theAfterMCHMotherControl.readPPCareByFamilyIDAndGravida
                (this.thePP.family_id,String.valueOf(mch_child));
        if(ppCare!=null)
        {
            jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลลูกหลังคลอดครั้งที่ "+mch_child));
        }
        else
        {
            mch_child--;
            jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("การดูแลลูกหลังคลอดครั้งที่ -"));
            mch_child_b = true;
        }
        this.setPPCare(ppCare);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jPanel3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseReleased
//        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null
//                , "ข้อมูลเด็กทารก", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION
//                , javax.swing.border.TitledBorder.DEFAULT_POSITION
//                , new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0,204,153)));
    }//GEN-LAST:event_jPanel3MouseReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int select = jTableBornMch.getSelectedRow();
        if(select >= 0)
        {
            thePanelBornMchMother.jTableShowBorn.setRowSelectionInterval(select, select);
            thePanelBornMchMother.selectTableBornMch(select);
            thePanelBornMchMother.setBornMch(theBornMch);
        }
        thePanelBornMchMother.showDialog(false);
        this.setBornMch(theBornMch);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(thePP!=null&&thePanelPP.vChildren!=null && !thePanelPP.vChildren.isEmpty())
        {
            for(int i=0;i<thePanelPP.vChildren.size();i++)
            {
                PP pp = (PP)thePanelPP.vChildren.get(i);
                if(pp!=null&&thePP.getObjectId()!=null&&thePP.getObjectId().equals(pp.getObjectId()))
                {
                    thePanelPP.jTablePP.setRowSelectionInterval(i, i);
                }
            }
        }
        if(thePP!=null&&thePP.getObjectId()!=null)
            thePanelPP.setPP(thePP);
        thePanelPP.showDialog(false);
        this.setPP(thePP);
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if(theAfterMchMother!=null&&thePanelAfterMchMother.vMchMother!=null && !thePanelAfterMchMother.vMchMother.isEmpty())
        {
            for(int i=0;i<thePanelAfterMchMother.vMchMother.size();i++)
            {
                AfterMchMother amm = (AfterMchMother)thePanelAfterMchMother.vMchMother.get(i);
                if(theAfterMchMother.getObjectId()!=null&&amm.getObjectId().equals(theAfterMchMother.getObjectId()))
                {
                    thePanelAfterMchMother.jTableAfterMchMother.setRowSelectionInterval(i, i);
                }
            }
        }
        if(theAfterMchMother!=null&&theAfterMchMother.getObjectId()!=null)
            thePanelAfterMchMother.setAfterMchMother(theAfterMchMother);
        thePanelAfterMchMother.showDialog(false);
        this.setAfterMchMother(theAfterMchMother);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if(thePP!=null&&thePanelAfterMchChild.vChildren!=null && !thePanelAfterMchChild.vChildren.isEmpty())
        {
            for(int i=0;i<thePanelAfterMchChild.vChildren.size();i++)
            {
                PP pp = (PP)thePanelAfterMchChild.vChildren.get(i);
                if(pp!=null&&thePP.getObjectId()!=null&&thePP.getObjectId().equals(pp.getObjectId()))
                {
                    thePanelAfterMchChild.jTablePP.setRowSelectionInterval(i, i);
                    thePanelAfterMchChild.setPPCareV2(pp);
                    thePanelAfterMchChild.pp_child = pp;
                }
            }
        }

        if(thePPCare!=null&&thePanelAfterMchChild.vPPCare!=null && !thePanelAfterMchChild.vPPCare.isEmpty())
        {
            for(int i=0;i<thePanelAfterMchChild.vPPCare.size();i++)
            {
                PPCare ppcare = (PPCare)thePanelAfterMchChild.vPPCare.get(i);
                if(ppcare!=null&&thePPCare.getObjectId()!=null&&thePPCare.getObjectId().equals(ppcare.getObjectId()))
                {
                    thePanelAfterMchChild.jTablePPCare.setRowSelectionInterval(i, i);
                }
            }
        }
        if(thePPCare!=null&&thePPCare.getObjectId()!=null)
        {
            thePanelAfterMchChild.setPPCare(thePPCare);

        }
        thePanelAfterMchChild.showDialog(false);
        this.setPPCare(thePPCare);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void dateComboBoxSurvey2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxSurvey2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateComboBoxSurvey2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.utility.DateComboBox dateComboBoxBornDate;
    private com.hospital_os.utility.DateComboBox dateComboBoxSurvey;
    private com.hospital_os.utility.DateComboBox dateComboBoxSurvey2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBoxAlblumin;
    private javax.swing.JComboBox jComboBoxAspiration;
    private javax.swing.JComboBox jComboBoxBirthPlace;
    private javax.swing.JComboBox jComboBoxBirthResult;
    private javax.swing.JComboBox jComboBoxBornMethod;
    private javax.swing.JComboBox jComboBoxBreathingTime;
    private javax.swing.JComboBox jComboBoxCheckPlace;
    private javax.swing.JComboBox jComboBoxCheckPlace2;
    private javax.swing.JComboBox jComboBoxCryingTime;
    private javax.swing.JComboBox jComboBoxEyeDrug;
    private javax.swing.JComboBox jComboBoxMethod;
    private javax.swing.JComboBox jComboBoxPreResult;
    private javax.swing.JComboBox jComboBoxResultGiveBirth;
    private javax.swing.JComboBox jComboBoxSew;
    private javax.swing.JComboBox jComboBoxSex;
    private javax.swing.JComboBox jComboBoxSugarLevel;
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
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
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
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableBornMch;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextAreaNote;
    private com.pcu.utility.IntegerTextField jTextFieldAliveBaby;
    private com.pcu.utility.IntegerTextField jTextFieldDeathBaby;
    private com.pcu.utility.IntegerTextField jTextFieldWeight;
    private com.hospital_os.utility.TimeTextField timeTextFieldBornTime;
    // End of variables declaration//GEN-END:variables

}
