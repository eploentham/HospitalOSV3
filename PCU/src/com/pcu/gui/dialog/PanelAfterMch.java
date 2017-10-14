/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelAfterMch.java
 *
 * Created on 18 พ.ย. 2553, 16:38:13
 */

package com.pcu.gui.dialog;

import com.hospital_os.utility.ComboboxModel;
import com.pcu.control.HosManage;
import com.pcu.object.AfterMchMother;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;

/**
 *
 * @author LionHeart
 */
public class PanelAfterMch extends javax.swing.JPanel {
    private JDialog theJD;
    private HosManage theHM;
    AfterMchMother theAfterMchMother;
    String number = "";
    /** Creates new form PanelAfterMch */
    public PanelAfterMch() {
        initComponents();
        jRadioButtonMen.setVisible(false);
        jRadioButtonCream.setVisible(false);
        jRadioButtonLevel.setVisible(false);
        jRadioButtonStep.setVisible(false);
        jRadioButtonLochia.setVisible(false);
    }
    public void showDialog()
    {
        if(theJD==null)
            theJD = new JDialog(theHM.theUS.getJFrame());
        theJD.add(this);
        theJD.setSize(300, 370);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, (screenSize.height - theJD.getSize().height) / 2);
        theJD.setTitle("การดูแลหลังคลอด");
        theJD.setVisible(true);
    }
    public void setControl(HosManage hm)
    {
        theHM = hm;
        ComboboxModel.initComboBox(jComboBoxSew,hm.theHosControl.theAllComboBoxControl.listPostpartumEpisiotomyType());
        ComboboxModel.initComboBox(jComboBoxSugarLevel,hm.theHosControl.theAllComboBoxControl.listPregnancyPregnantLevel());
        ComboboxModel.initComboBox(jComboBoxAlblumin,hm.theHosControl.theAllComboBoxControl.listPregnancyPregnantLevel());
    }
    public void setAfterMchMother(AfterMchMother afterMchMother,String num)
    {
        number = num;
        theAfterMchMother = afterMchMother;
        if(number.equals("1"))
            this.setAfterMchMother1(afterMchMother);
        else if(number.equals("2"))
            this.setAfterMchMother2(afterMchMother);
        else if(number.equals("3"))
            this.setAfterMchMother3(afterMchMother);
    }
    public AfterMchMother getAfterMchMother1(AfterMchMother afterMchMother)
    {
        theAfterMchMother = afterMchMother;
        if(theAfterMchMother == null)
            theAfterMchMother = new AfterMchMother();
        if(jRadioButtonNormalMen.isSelected())
            theAfterMchMother.menses = "1";
        else if(jRadioButtonAbNormalMen.isSelected())
            theAfterMchMother.menses = "0";
        else if(jRadioButtonMen.isSelected())
            theAfterMchMother.menses = "";
        if(jRadioButtonNormalCream.isSelected())
            theAfterMchMother.cream = "1";
        else if(jRadioButtonAbNormalCream.isSelected())
            theAfterMchMother.cream = "0";
        else if(jRadioButtonCream.isSelected())
            theAfterMchMother.cream = "";
        if(jRadioButtonNormalUterusLevel.isSelected())
            theAfterMchMother.uterus_level = "1";
        else if(jRadioButtonAbNormalUterusLevel.isSelected())
            theAfterMchMother.uterus_level = "0";
        else if(jRadioButtonLevel.isSelected())
            theAfterMchMother.uterus_level = "";
        if(jRadioButtonNormalMilkStep.isSelected())
            theAfterMchMother.milk_seep = "1";
        else if(jRadioButtonAbNormalMilkStep.isSelected())
            theAfterMchMother.milk_seep = "0";
        else if(jRadioButtonStep.isSelected())
            theAfterMchMother.milk_seep = "";
        if(jRadioButtonHasLochia.isSelected())
            theAfterMchMother.lochia = "1";
        else if(jRadioButtonNoLochia.isSelected())
            theAfterMchMother.lochia = "0";
        else if(jRadioButtonLochia.isSelected())
            theAfterMchMother.lochia = "";
        theAfterMchMother.note = jTextFieldNote.getText();
        theAfterMchMother.sew = ComboboxModel.getCodeComboBox(jComboBoxSew);
        theAfterMchMother.sugar_level = ComboboxModel.getCodeComboBox(jComboBoxSugarLevel);
        theAfterMchMother.albumin = ComboboxModel.getCodeComboBox(jComboBoxAlblumin);
        return theAfterMchMother;
    }
    public AfterMchMother getAfterMchMother2(AfterMchMother afterMchMother)
    {
        theAfterMchMother = afterMchMother;
        if(theAfterMchMother == null)
            theAfterMchMother = new AfterMchMother();
        if(jRadioButtonNormalMen.isSelected())
            theAfterMchMother.menses2 = "1";
        else if(jRadioButtonAbNormalMen.isSelected())
            theAfterMchMother.menses2 = "0";
        else if(jRadioButtonMen.isSelected())
            theAfterMchMother.menses2 = "";
        if(jRadioButtonNormalCream.isSelected())
            theAfterMchMother.cream2 = "1";
        else if(jRadioButtonAbNormalCream.isSelected())
            theAfterMchMother.cream2 = "0";
        else if(jRadioButtonCream.isSelected())
            theAfterMchMother.cream2 = "";
        if(jRadioButtonNormalUterusLevel.isSelected())
            theAfterMchMother.uterus_level2 = "1";
        else if(jRadioButtonAbNormalUterusLevel.isSelected())
            theAfterMchMother.uterus_level2 = "0";
        else if(jRadioButtonLevel.isSelected())
            theAfterMchMother.uterus_level2 = "";
        if(jRadioButtonNormalMilkStep.isSelected())
            theAfterMchMother.milk_seep2 = "1";
        else if(jRadioButtonAbNormalMilkStep.isSelected())
            theAfterMchMother.milk_seep2 = "0";
        else if(jRadioButtonStep.isSelected())
            theAfterMchMother.milk_seep2 = "";
        if(jRadioButtonHasLochia.isSelected())
            theAfterMchMother.lochia2 = "1";
        else if(jRadioButtonNoLochia.isSelected())
            theAfterMchMother.lochia2 = "0";
        else if(jRadioButtonLochia.isSelected())
            theAfterMchMother.lochia2 = "";
        theAfterMchMother.note2 = jTextFieldNote.getText();
        theAfterMchMother.sew2 = ComboboxModel.getCodeComboBox(jComboBoxSew);
        theAfterMchMother.sugar_level2 = ComboboxModel.getCodeComboBox(jComboBoxSugarLevel);
        theAfterMchMother.albumin2 = ComboboxModel.getCodeComboBox(jComboBoxAlblumin);
        return theAfterMchMother;
    }
    public AfterMchMother getAfterMchMother3(AfterMchMother afterMchMother)
    {
        theAfterMchMother = afterMchMother;
        if(theAfterMchMother == null)
            theAfterMchMother = new AfterMchMother();
        if(jRadioButtonNormalMen.isSelected())
            theAfterMchMother.menses3 = "1";
        else if(jRadioButtonAbNormalMen.isSelected())
            theAfterMchMother.menses3 = "0";
        else if(jRadioButtonMen.isSelected())
            theAfterMchMother.menses3 = "";
        if(jRadioButtonNormalCream.isSelected())
            theAfterMchMother.cream3 = "1";
        else if(jRadioButtonAbNormalCream.isSelected())
            theAfterMchMother.cream3 = "0";
        else if(jRadioButtonCream.isSelected())
            theAfterMchMother.cream3 = "";
        if(jRadioButtonNormalUterusLevel.isSelected())
            theAfterMchMother.uterus_level3 = "1";
        else if(jRadioButtonAbNormalUterusLevel.isSelected())
            theAfterMchMother.uterus_level3 = "0";
        else if(jRadioButtonLevel.isSelected())
            theAfterMchMother.uterus_level3 = "";
        if(jRadioButtonNormalMilkStep.isSelected())
            theAfterMchMother.milk_seep3 = "1";
        else if(jRadioButtonAbNormalMilkStep.isSelected())
            theAfterMchMother.milk_seep3 = "0";
        else if(jRadioButtonStep.isSelected())
            theAfterMchMother.milk_seep3 = "";
        if(jRadioButtonHasLochia.isSelected())
            theAfterMchMother.lochia3 = "1";
        else if(jRadioButtonNoLochia.isSelected())
            theAfterMchMother.lochia3 = "0";
        else if(jRadioButtonLochia.isSelected())
            theAfterMchMother.lochia3 = "";
        theAfterMchMother.note3 = jTextFieldNote.getText();
        theAfterMchMother.sew3 = ComboboxModel.getCodeComboBox(jComboBoxSew);
        theAfterMchMother.sugar_level3 = ComboboxModel.getCodeComboBox(jComboBoxSugarLevel);
        theAfterMchMother.albumin3 = ComboboxModel.getCodeComboBox(jComboBoxAlblumin);
        return theAfterMchMother;
    }
    public void setAfterMchMother1(AfterMchMother afterMchMother)
    {
        theAfterMchMother = afterMchMother;
        if(theAfterMchMother == null)
            theAfterMchMother = new AfterMchMother();
        if(theAfterMchMother.menses.equals("1"))
            jRadioButtonNormalMen.setSelected(true);
        else if(theAfterMchMother.menses.equals("0"))
            jRadioButtonAbNormalMen.setSelected(true);
        else if(theAfterMchMother.menses.equals(""))
            jRadioButtonMen.setSelected(true);
        if(theAfterMchMother.cream.equals("1"))
            jRadioButtonNormalCream.setSelected(true);
        else if(theAfterMchMother.cream.equals("0"))
            jRadioButtonAbNormalCream.setSelected(true);
        else if(theAfterMchMother.cream.equals(""))
            jRadioButtonCream.setSelected(true);
        if(theAfterMchMother.uterus_level.equals("1"))
            jRadioButtonNormalUterusLevel.setSelected(true);
        else if(theAfterMchMother.uterus_level.equals("0"))
            jRadioButtonAbNormalUterusLevel.setSelected(true);
        else if(theAfterMchMother.uterus_level.equals(""))
            jRadioButtonLevel.setSelected(true);
        if(theAfterMchMother.milk_seep.equals("1"))
            jRadioButtonNormalMilkStep.setSelected(true);
        else if(theAfterMchMother.milk_seep.equals("0"))
            jRadioButtonAbNormalMilkStep.setSelected(true);
        else if(theAfterMchMother.milk_seep.equals(""))
            jRadioButtonStep.setSelected(true);
        if(theAfterMchMother.lochia.equals("1"))
            jRadioButtonHasLochia.setSelected(true);
        else if(theAfterMchMother.lochia.equals("0"))
            jRadioButtonNoLochia.setSelected(true);
        else if(theAfterMchMother.lochia.equals(""))
            jRadioButtonLochia.setSelected(true);
        jTextFieldNote.setText(theAfterMchMother.note);
        jLabelDes.setText("การดูแลหลังคลอดครั้งที่ 1");
        ComboboxModel.setCodeComboBox(jComboBoxSew,theAfterMchMother.sew);
        ComboboxModel.setCodeComboBox(jComboBoxSugarLevel,theAfterMchMother.sugar_level);
        ComboboxModel.setCodeComboBox(jComboBoxAlblumin,theAfterMchMother.albumin);
    }
    public void setAfterMchMother2(AfterMchMother afterMchMother)
    {
        theAfterMchMother = afterMchMother;
        if(theAfterMchMother == null)
            theAfterMchMother = new AfterMchMother();
        if(theAfterMchMother.menses2.equals("1"))
            jRadioButtonNormalMen.setSelected(true);
        else if(theAfterMchMother.menses2.equals("0"))
            jRadioButtonAbNormalMen.setSelected(true);
        else if(theAfterMchMother.menses2.equals(""))
            jRadioButtonMen.setSelected(true);
        if(theAfterMchMother.cream2.equals("1"))
            jRadioButtonNormalCream.setSelected(true);
        else if(theAfterMchMother.cream2.equals("0"))
            jRadioButtonAbNormalCream.setSelected(true);
        else if(theAfterMchMother.cream2.equals(""))
            jRadioButtonCream.setSelected(true);
        if(theAfterMchMother.uterus_level2.equals("1"))
            jRadioButtonNormalUterusLevel.setSelected(true);
        else if(theAfterMchMother.uterus_level2.equals("0"))
            jRadioButtonAbNormalUterusLevel.setSelected(true);
        else if(theAfterMchMother.uterus_level2.equals(""))
            jRadioButtonLevel.setSelected(true);
        if(theAfterMchMother.milk_seep2.equals("1"))
            jRadioButtonNormalMilkStep.setSelected(true);
        else if(theAfterMchMother.milk_seep2.equals("0"))
            jRadioButtonAbNormalMilkStep.setSelected(true);
        else if(theAfterMchMother.milk_seep2.equals(""))
            jRadioButtonStep.setSelected(true);
        if(theAfterMchMother.lochia2.equals("1"))
            jRadioButtonHasLochia.setSelected(true);
        else if(theAfterMchMother.lochia2.equals("0"))
            jRadioButtonNoLochia.setSelected(true);
        else if(theAfterMchMother.lochia2.equals(""))
            jRadioButtonLochia.setSelected(true);
        jTextFieldNote.setText(theAfterMchMother.note2);
        jLabelDes.setText("การดูแลหลังคลอดครั้งที่ 2");
        ComboboxModel.setCodeComboBox(jComboBoxSew,theAfterMchMother.sew2);
        ComboboxModel.setCodeComboBox(jComboBoxSugarLevel,theAfterMchMother.sugar_level2);
        ComboboxModel.setCodeComboBox(jComboBoxAlblumin,theAfterMchMother.albumin2);
    }
    public void setAfterMchMother3(AfterMchMother afterMchMother)
    {
        theAfterMchMother = afterMchMother;
        if(theAfterMchMother == null)
            theAfterMchMother = new AfterMchMother();
        if(theAfterMchMother.menses3.equals("1"))
            jRadioButtonNormalMen.setSelected(true);
        else if(theAfterMchMother.menses3.equals("0"))
            jRadioButtonAbNormalMen.setSelected(true);
        else if(theAfterMchMother.menses3.equals(""))
            jRadioButtonMen.setSelected(true);
        if(theAfterMchMother.cream3.equals("1"))
            jRadioButtonNormalCream.setSelected(true);
        else if(theAfterMchMother.cream3.equals("0"))
            jRadioButtonAbNormalCream.setSelected(true);
        else if(theAfterMchMother.cream3.equals(""))
            jRadioButtonCream.setSelected(true);
        if(theAfterMchMother.uterus_level3.equals("1"))
            jRadioButtonNormalUterusLevel.setSelected(true);
        else if(theAfterMchMother.uterus_level3.equals("0"))
            jRadioButtonAbNormalUterusLevel.setSelected(true);
        else if(theAfterMchMother.uterus_level3.equals(""))
            jRadioButtonLevel.setSelected(true);
        if(theAfterMchMother.milk_seep3.equals("1"))
            jRadioButtonNormalMilkStep.setSelected(true);
        else if(theAfterMchMother.milk_seep3.equals("0"))
            jRadioButtonAbNormalMilkStep.setSelected(true);
        else if(theAfterMchMother.milk_seep3.equals(""))
            jRadioButtonStep.setSelected(true);
        if(theAfterMchMother.lochia3.equals("1"))
            jRadioButtonHasLochia.setSelected(true);
        else if(theAfterMchMother.lochia3.equals("0"))
            jRadioButtonNoLochia.setSelected(true);
        else if(theAfterMchMother.lochia3.equals(""))
            jRadioButtonLochia.setSelected(true);
        jTextFieldNote.setText(theAfterMchMother.note3);
        jLabelDes.setText("การดูแลหลังคลอดครั้งที่ 3");
        ComboboxModel.setCodeComboBox(jComboBoxSew,theAfterMchMother.sew3);
        ComboboxModel.setCodeComboBox(jComboBoxSugarLevel,theAfterMchMother.sugar_level3);
        ComboboxModel.setCodeComboBox(jComboBoxAlblumin,theAfterMchMother.albumin3);
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

        buttonGroupMen = new javax.swing.ButtonGroup();
        buttonGroupCream = new javax.swing.ButtonGroup();
        buttonGroupUterusLevel = new javax.swing.ButtonGroup();
        buttonGroupMilk = new javax.swing.ButtonGroup();
        buttonGroupLochia = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jRadioButtonNormalMen = new javax.swing.JRadioButton();
        jRadioButtonAbNormalMen = new javax.swing.JRadioButton();
        jRadioButtonNormalUterusLevel = new javax.swing.JRadioButton();
        jRadioButtonAbNormalUterusLevel = new javax.swing.JRadioButton();
        jRadioButtonNormalMilkStep = new javax.swing.JRadioButton();
        jRadioButtonAbNormalMilkStep = new javax.swing.JRadioButton();
        jRadioButtonNoLochia = new javax.swing.JRadioButton();
        jRadioButtonHasLochia = new javax.swing.JRadioButton();
        jComboBoxSugarLevel = new javax.swing.JComboBox();
        jRadioButtonNormalCream = new javax.swing.JRadioButton();
        jRadioButtonAbNormalCream = new javax.swing.JRadioButton();
        jComboBoxAlblumin = new javax.swing.JComboBox();
        jComboBoxSew = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextFieldNote = new javax.swing.JTextArea();
        jRadioButtonMen = new javax.swing.JRadioButton();
        jRadioButtonCream = new javax.swing.JRadioButton();
        jRadioButtonLevel = new javax.swing.JRadioButton();
        jRadioButtonStep = new javax.swing.JRadioButton();
        jRadioButtonLochia = new javax.swing.JRadioButton();
        jLabelDes = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setMinimumSize(new java.awt.Dimension(100, 68));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("ประจำเดือน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setText("หัวนม");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setText("ระดับมดลูก");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setText("น้ำนมไหล");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        jLabel6.setText("น้ำคาวปลา");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jLabel6, gridBagConstraints);

        jLabel7.setText("ฝีเย็บ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jLabel7, gridBagConstraints);

        jLabel8.setText("ระดับน้ำตาล");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jLabel8, gridBagConstraints);

        jLabel9.setText("ระดับอัลบูลมิน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jLabel9, gridBagConstraints);

        buttonGroupMen.add(jRadioButtonNormalMen);
        jRadioButtonNormalMen.setText("ปกติ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel1.add(jRadioButtonNormalMen, gridBagConstraints);

        buttonGroupMen.add(jRadioButtonAbNormalMen);
        jRadioButtonAbNormalMen.setText("ผิดปกติ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel1.add(jRadioButtonAbNormalMen, gridBagConstraints);

        buttonGroupUterusLevel.add(jRadioButtonNormalUterusLevel);
        jRadioButtonNormalUterusLevel.setText("ปกติ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel1.add(jRadioButtonNormalUterusLevel, gridBagConstraints);

        buttonGroupUterusLevel.add(jRadioButtonAbNormalUterusLevel);
        jRadioButtonAbNormalUterusLevel.setText("ผิดปกติ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel1.add(jRadioButtonAbNormalUterusLevel, gridBagConstraints);

        buttonGroupMilk.add(jRadioButtonNormalMilkStep);
        jRadioButtonNormalMilkStep.setText("ไหล");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel1.add(jRadioButtonNormalMilkStep, gridBagConstraints);

        buttonGroupMilk.add(jRadioButtonAbNormalMilkStep);
        jRadioButtonAbNormalMilkStep.setText("ไม่ไหล");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel1.add(jRadioButtonAbNormalMilkStep, gridBagConstraints);

        buttonGroupLochia.add(jRadioButtonNoLochia);
        jRadioButtonNoLochia.setText("ไม่มี");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel1.add(jRadioButtonNoLochia, gridBagConstraints);

        buttonGroupLochia.add(jRadioButtonHasLochia);
        jRadioButtonHasLochia.setText("มี");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel1.add(jRadioButtonHasLochia, gridBagConstraints);

        jComboBoxSugarLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxSugarLevel.setMinimumSize(new java.awt.Dimension(100, 18));
        jComboBoxSugarLevel.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel1.add(jComboBoxSugarLevel, gridBagConstraints);

        buttonGroupCream.add(jRadioButtonNormalCream);
        jRadioButtonNormalCream.setText("ปกติ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel1.add(jRadioButtonNormalCream, gridBagConstraints);

        buttonGroupCream.add(jRadioButtonAbNormalCream);
        jRadioButtonAbNormalCream.setText("ผิดปกติ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel1.add(jRadioButtonAbNormalCream, gridBagConstraints);

        jComboBoxAlblumin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxAlblumin.setMinimumSize(new java.awt.Dimension(100, 18));
        jComboBoxAlblumin.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel1.add(jComboBoxAlblumin, gridBagConstraints);

        jComboBoxSew.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxSew.setMinimumSize(new java.awt.Dimension(150, 18));
        jComboBoxSew.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel1.add(jComboBoxSew, gridBagConstraints);

        jLabel10.setText("หมายเหตุ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 3, 0, 0);
        jPanel1.add(jLabel10, gridBagConstraints);

        jTextFieldNote.setColumns(20);
        jTextFieldNote.setRows(5);
        jScrollPane1.setViewportView(jTextFieldNote);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        buttonGroupMen.add(jRadioButtonMen);
        jRadioButtonMen.setText("-");
        jPanel1.add(jRadioButtonMen, new java.awt.GridBagConstraints());

        buttonGroupCream.add(jRadioButtonCream);
        jRadioButtonCream.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        jPanel1.add(jRadioButtonCream, gridBagConstraints);

        buttonGroupUterusLevel.add(jRadioButtonLevel);
        jRadioButtonLevel.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        jPanel1.add(jRadioButtonLevel, gridBagConstraints);

        buttonGroupMilk.add(jRadioButtonStep);
        jRadioButtonStep.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        jPanel1.add(jRadioButtonStep, gridBagConstraints);

        buttonGroupLochia.add(jRadioButtonLochia);
        jRadioButtonLochia.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        jPanel1.add(jRadioButtonLochia, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(jPanel1, gridBagConstraints);

        jLabelDes.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabelDes.setText("การดูแลหลังคลอดครั้งที่");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        add(jLabelDes, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("ตกลง");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButton1, gridBagConstraints);

        jButton2.setText("ยกเลิก");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 3, 3);
        add(jPanel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(number.equals("1"))
            this.getAfterMchMother1(theAfterMchMother);
        else if(number.equals("2"))
            this.getAfterMchMother2(theAfterMchMother);
        else if(number.equals("3"))
            this.getAfterMchMother3(theAfterMchMother);
        this.theJD.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.theJD.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupCream;
    private javax.swing.ButtonGroup buttonGroupLochia;
    private javax.swing.ButtonGroup buttonGroupMen;
    private javax.swing.ButtonGroup buttonGroupMilk;
    private javax.swing.ButtonGroup buttonGroupUterusLevel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBoxAlblumin;
    private javax.swing.JComboBox jComboBoxSew;
    private javax.swing.JComboBox jComboBoxSugarLevel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButtonAbNormalCream;
    private javax.swing.JRadioButton jRadioButtonAbNormalMen;
    private javax.swing.JRadioButton jRadioButtonAbNormalMilkStep;
    private javax.swing.JRadioButton jRadioButtonAbNormalUterusLevel;
    private javax.swing.JRadioButton jRadioButtonCream;
    private javax.swing.JRadioButton jRadioButtonHasLochia;
    private javax.swing.JRadioButton jRadioButtonLevel;
    private javax.swing.JRadioButton jRadioButtonLochia;
    private javax.swing.JRadioButton jRadioButtonMen;
    private javax.swing.JRadioButton jRadioButtonNoLochia;
    private javax.swing.JRadioButton jRadioButtonNormalCream;
    private javax.swing.JRadioButton jRadioButtonNormalMen;
    private javax.swing.JRadioButton jRadioButtonNormalMilkStep;
    private javax.swing.JRadioButton jRadioButtonNormalUterusLevel;
    private javax.swing.JRadioButton jRadioButtonStep;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextFieldNote;
    // End of variables declaration//GEN-END:variables

}
