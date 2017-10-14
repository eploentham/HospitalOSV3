/*
 * PanelPhysicalExam.java
 *
 * Created on 4 พฤษภาคม 2549, 15:21 น.
 */

package com.hosv3.gui.dialog;

import com.hosv3.utility.Constant;
import com.hosv3.control.*;
import com.hospital_os.object.*;
import com.hospital_os.utility.Gutil;
import com.hosv3.utility.connection.*;
import com.hosv3.utility.*;
import java.util.*;

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author  henbe
 */
public class PanelPhysicalExam extends javax.swing.JPanel {
    
    HosControl theHC;
    UpdateStatus theUS;
    JDialog theJD;
    PhysicalExamNan thePhysicalExamNan = new PhysicalExamNan();
    boolean ret = false;
    /** Creates new form PanelPhysicalExam */
    public PanelPhysicalExam() {
        initComponents();
        setLanguage(null);
    }   
    public PanelPhysicalExam(HosControl hc,UpdateStatus us) {
        initComponents();
        setLanguage(null);
        setControl(hc,us);
        initDialog();
    }
    public void setControl(HosControl hc,UpdateStatus us){
        theHC = hc;
        theUS = us;
        this.panelWound1.setControl(hc,us);
        this.panelWound1.setAccidentMode(false);
    }
    public void initDialog(){
        theJD = new JDialog(theUS.getJFrame(),true);
        theJD.setTitle(Constant.getTextBundle("ผลการตรวจร่างกายของผู้ป่วย"));
        theJD.getContentPane().add(this);  
        theJD.setSize(750,550);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width-theJD.getSize().width)/2
                , (screenSize.height-theJD.getSize().height)/2);
    }
    public void setEnabled(boolean b){
        jButtonCancel.setEnabled(b);
        jButtonSave.setEnabled(b);
        jButtonCancel1.setEnabled(b);
        jButtonSave1.setEnabled(b);
       /* because of color when this disable it not red color it is grey
        *then user difficulte to see it
        jCheckBoxAbdomen_bowe_dec.setEnabled(b);
        jCheckBoxAbdomen_bowe_inc.setEnabled(b);
        jCheckBoxAbdomen_bowe_nor.setEnabled(b);
        jCheckBoxAbdomen_clear.setEnabled(b);
        jCheckBoxAbdomen_dark.setEnabled(b);
        jCheckBoxAbdomen_dis.setEnabled(b);
        jCheckBoxAbdomen_gua.setEnabled(b);
        jCheckBoxAbdomen_nor.setEnabled(b);
        jCheckBoxAbdomen_regu.setEnabled(b);
        jCheckBoxAbdomen_rig.setEnabled(b);
        jCheckBoxAbdomen_sca.setEnabled(b);
        jCheckBoxAbdomen_soft.setEnabled(b);
        jCheckBoxAbdomen_ten.setEnabled(b);
        jCheckBoxExtreme_non_p.setEnabled(b);
        jCheckBoxExtreme_norm.setEnabled(b);
        jCheckBoxExtreme_pitt.setEnabled(b);
        jCheckBoxExtreme_ten.setEnabled(b);
        jCheckBoxHeart_gall.setEnabled(b);
        jCheckBoxHeart_irre.setEnabled(b);
        jCheckBoxHeart_murm.setEnabled(b);
        jCheckBoxHeart_no_murm.setEnabled(b);
        jCheckBoxHeart_requ.setEnabled(b);
        jCheckBoxHeent_cerv_n.setEnabled(b);
        jCheckBoxHeent_cerv_p.setEnabled(b);
        jCheckBoxHeent_eye_h.setEnabled(b);
        jCheckBoxHeent_eye_i.setEnabled(b);
        jCheckBoxHeent_eye_o.setEnabled(b);
        jCheckBoxHeent_eye_p.setEnabled(b);
        jCheckBoxHeent_icte_s.setEnabled(b);
        jCheckBoxHeent_inje_p.setEnabled(b);
        jCheckBoxHeent_no_ict.setEnabled(b);
        jCheckBoxHeent_normal.setEnabled(b);
        jCheckBoxHeent_not_inj.setEnabled(b);
        jCheckBoxHeent_not_p.setEnabled(b);
        jCheckBoxHeent_pale.setEnabled(b);
        jCheckBoxHeent_thyr_e.setEnabled(b);
        jCheckBoxHeent_thyr_nor.setEnabled(b);
        jCheckBoxHeent_ton_en.setEnabled(b);
        jCheckBoxHeent_ton_ex.setEnabled(b);
        jCheckBoxHeent_ton_nor.setEnabled(b);
        jCheckBoxLung_clear.setEnabled(b);
        jCheckBoxLung_coar.setEnabled(b);
        jCheckBoxLung_fine.setEnabled(b);
        jCheckBoxLung_rhon.setEnabled(b);
        jCheckBoxLung_whee.setEnabled(b);
        jCheckBoxNeuro_atax.setEnabled(b);
        jCheckBoxNeuro_cn.setEnabled(b);
        jCheckBoxNeuro_ftn.setEnabled(b);
        jCheckBoxNeuro_gros.setEnabled(b);
        jCheckBoxNeuro_low_l.setEnabled(b);
        jCheckBoxNeuro_low_r.setEnabled(b);
        jCheckBoxNeuro_refl_l.setEnabled(b);
        jCheckBoxNeuro_refl_r.setEnabled(b);
        jCheckBoxNeuro_up_l.setEnabled(b);
        jCheckBoxNeuro_up_r.setEnabled(b);
        jCheckBoxSkin_bull.setEnabled(b);
        jCheckBoxSkin_ecze.setEnabled(b);
        jCheckBoxSkin_exfo.setEnabled(b);
        jCheckBoxSkin_nor.setEnabled(b);
        jCheckBoxSkin_puts.setEnabled(b);
        jCheckBoxSkin_scal.setEnabled(b);
        jCheckBoxSkin_tine.setEnabled(b);
        jCheckBox_ga_dehy_mild.setEnabled(b);
        jCheckBox_ga_dehy_mod.setEnabled(b);
        jCheckBox_ga_dehy_no.setEnabled(b);
        jCheckBox_ga_dehy_ser.setEnabled(b);
        jCheckBox_ga_dys.setEnabled(b);
        jCheckBox_ga_feb.setEnabled(b);
        jCheckBox_ga_gcs.setEnabled(b);
        jCheckBox_ga_jaun.setEnabled(b);
        jCheckBox_ga_look.setEnabled(b);
        jCheckBox_ga_no_jaun.setEnabled(b);
        jCheckBox_ga_normal.setEnabled(b);
        jCheckBox_ga_not_pale.setEnabled(b);
        jCheckBox_ga_pale.setEnabled(b);
        jCheckBox_ga_tach.setEnabled(b);
        jComboBoxHeart_dias_area.setEnabled(b);
        jComboBoxHeart_dias_grad.setEnabled(b);
        jComboBoxHeart_sys_area.setEnabled(b);
        jComboBoxHeart_sys_grad.setEnabled(b);
        jComboBoxNeuro_cn.setEnabled(b);
        jComboBoxNeuro_grad_l_l.setEnabled(b);
        jComboBoxNeuro_grad_l_r.setEnabled(b);
        jComboBoxNeuro_grad_u_l.setEnabled(b);
        jComboBoxNeuro_grad_u_r.setEnabled(b);
        jComboBoxNeuro_refl_l.setEnabled(b);
        jComboBoxNeuro_refl_r.setEnabled(b);
        jComboBox_ga_gcs_e.setEnabled(b);
        jComboBox_ga_gcs_m.setEnabled(b);
        jComboBox_ga_gcs_v.setEnabled(b);
        jRadioButtonHeart_mur_dias.setEnabled(b);
        jRadioButtonHeart_mur_syst.setEnabled(b);
        jRadioButtonHeent_eye_h_b.setEnabled(b);
        jRadioButtonHeent_eye_h_l.setEnabled(b);
        jRadioButtonHeent_eye_h_r.setEnabled(b);
        jRadioButtonHeent_eye_i_b.setEnabled(b);
        jRadioButtonHeent_eye_i_l.setEnabled(b);
        jRadioButtonHeent_eye_i_r.setEnabled(b);
        jRadioButtonHeent_eye_o_b.setEnabled(b);
        jRadioButtonHeent_eye_o_l.setEnabled(b);
        jRadioButtonHeent_eye_o_r.setEnabled(b);
        jRadioButtonHeent_eye_p_b.setEnabled(b);
        jRadioButtonHeent_eye_p_l.setEnabled(b);
        jRadioButtonHeent_eye_p_r.setEnabled(b);
        jRadioButtonLung_coar_b.setEnabled(b);
        jRadioButtonLung_coar_l.setEnabled(b);
        jRadioButtonLung_coar_r.setEnabled(b);
        jRadioButtonLung_fine_b.setEnabled(b);
        jRadioButtonLung_fine_l.setEnabled(b);
        jRadioButtonLung_fine_r.setEnabled(b);
        jRadioButtonLung_rhon_b.setEnabled(b);
        jRadioButtonLung_rhon_l.setEnabled(b);
        jRadioButtonLung_rhon_r.setEnabled(b);
        jRadioButtonLung_whee_b.setEnabled(b);
        jRadioButtonLung_whee_l.setEnabled(b);
        jRadioButtonLung_whee_r.setEnabled(b);
        jRadioButtonNeuro_cn_abno.setEnabled(b);
        jRadioButtonNeuro_cn_imp1.setEnabled(b);
        jRadioButtonNeuro_cn_imp2.setEnabled(b);
        jRadioButtonNeuro_cn_inta.setEnabled(b);
        jRadioButtonNeuro_cn_inta1.setEnabled(b);
        jRadioButtonNeuro_cn_inta2.setEnabled(b);
        jTextAreaOther.setEnabled(b);
        jTextArea_ga_other.setEnabled(b);
        jTextFieldAbdomen_pal_dec.setEnabled(b);
        jTextFieldAbdomen_tend_dec.setEnabled(b);
        jTextFieldExtreme_ten_dec.setEnabled(b);
        jTextFieldExtremity_oth.setEnabled(b);
        jTextFieldLung_others.setEnabled(b);
        jTextFieldNeuro_oth.setEnabled(b);
        jTextFieldSkin_oth.setEnabled(b);
        jTextFieldThyroids_others.setEnabled(b);
        jTextFieldTonsils_others.setEnabled(b);        
        **/
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroupNeuro_ata = new javax.swing.ButtonGroup();
        buttonGroupNeuro_ftn = new javax.swing.ButtonGroup();
        buttonGroupNeuro_cn = new javax.swing.ButtonGroup();
        buttonGroupLung_caus = new javax.swing.ButtonGroup();
        ga_pale = new javax.swing.ButtonGroup();
        ga_juandice = new javax.swing.ButtonGroup();
        buttonGroupEye_pter = new javax.swing.ButtonGroup();
        buttonGroupEye_inj = new javax.swing.ButtonGroup();
        buttonGroupEye_opa = new javax.swing.ButtonGroup();
        buttonGroupEye_her = new javax.swing.ButtonGroup();
        buttonGroupMur_type = new javax.swing.ButtonGroup();
        buttonGroupLung_whee = new javax.swing.ButtonGroup();
        buttonGroupLung_rhon = new javax.swing.ButtonGroup();
        buttonGroupLung_fine = new javax.swing.ButtonGroup();
        buttonGroupLung_coar = new javax.swing.ButtonGroup();
        buttonGroupMurmur = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel33 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jCheckBoxHeart_requ = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxHeart_gall = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxHeart_irre = new com.hosv3.gui.component.HCheckBox();
        jPanel14 = new javax.swing.JPanel();
        jRadioButtonHeart_mur_syst = new javax.swing.JRadioButton();
        jRadioButtonHeart_mur_dias = new javax.swing.JRadioButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jComboBoxHeart_dias_grad = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jComboBoxHeart_dias_area = new javax.swing.JComboBox();
        jPanel47 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jComboBoxHeart_sys_grad = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        jComboBoxHeart_sys_area = new javax.swing.JComboBox();
        jCheckBoxHeart_murm = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxHeart_no_murm = new com.hosv3.gui.component.HCheckBox();
        jLabel16 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jCheckBox_ga_no_jaun = new com.hosv3.gui.component.HCheckBox();
        jPanel22 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox_ga_gcs_m = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jComboBox_ga_gcs_e = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jComboBox_ga_gcs_v = new javax.swing.JComboBox();
        jCheckBox_ga_gcs = new com.hosv3.gui.component.HCheckBox();
        jCheckBox_ga_not_pale = new com.hosv3.gui.component.HCheckBox();
        jCheckBox_ga_feb = new com.hosv3.gui.component.HCheckBox();
        jCheckBox_ga_dys = new com.hosv3.gui.component.HCheckBox();
        jCheckBox_ga_tach = new com.hosv3.gui.component.HCheckBox();
        jCheckBox_ga_look = new com.hosv3.gui.component.HCheckBox();
        jPanel24 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_ga_other = new javax.swing.JTextArea();
        jPanel41 = new javax.swing.JPanel();
        jCheckBox_ga_dehy_ser = new com.hosv3.gui.component.HCheckBox();
        jCheckBox_ga_dehy_mod = new com.hosv3.gui.component.HCheckBox();
        jCheckBox_ga_dehy_mild = new com.hosv3.gui.component.HCheckBox();
        jCheckBox_ga_dehy_no = new com.hosv3.gui.component.HCheckBox();
        jLabel22 = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        jCheckBox_ga_normal = new com.hosv3.gui.component.HCheckBox();
        jCheckBox_ga_jaun = new com.hosv3.gui.component.HCheckBox();
        jCheckBox_ga_pale = new com.hosv3.gui.component.HCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jCheckBoxHeent_eye_p = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxHeent_eye_o = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxHeent_eye_i = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxHeent_eye_h = new com.hosv3.gui.component.HCheckBox();
        jRadioButtonHeent_eye_p_l = new javax.swing.JRadioButton();
        jRadioButtonHeent_eye_i_r = new javax.swing.JRadioButton();
        jRadioButtonHeent_eye_h_b = new javax.swing.JRadioButton();
        jRadioButtonHeent_eye_i_l = new javax.swing.JRadioButton();
        jRadioButtonHeent_eye_p_r = new javax.swing.JRadioButton();
        jRadioButtonHeent_eye_o_b = new javax.swing.JRadioButton();
        jRadioButtonHeent_eye_o_l = new javax.swing.JRadioButton();
        jRadioButtonHeent_eye_o_r = new javax.swing.JRadioButton();
        jRadioButtonHeent_eye_i_b = new javax.swing.JRadioButton();
        jRadioButtonHeent_eye_h_l = new javax.swing.JRadioButton();
        jRadioButtonHeent_eye_h_r = new javax.swing.JRadioButton();
        jRadioButtonHeent_eye_p_b = new javax.swing.JRadioButton();
        jLabel39 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jCheckBoxHeent_ton_ex = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxHeent_ton_en = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxHeent_ton_nor = new com.hosv3.gui.component.HCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldTonsils_others = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jCheckBoxHeent_thyr_e = new com.hosv3.gui.component.HCheckBox();
        jTextFieldThyroids_others = new javax.swing.JTextField();
        jCheckBoxHeent_thyr_nor = new com.hosv3.gui.component.HCheckBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jCheckBoxHeent_not_p = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxHeent_pale = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxHeent_inje_p = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxHeent_not_inj = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxHeent_no_ict = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxHeent_icte_s = new com.hosv3.gui.component.HCheckBox();
        jPanel35 = new javax.swing.JPanel();
        jCheckBoxHeent_cerv_n = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxHeent_cerv_p = new com.hosv3.gui.component.HCheckBox();
        jLabel17 = new javax.swing.JLabel();
        jCheckBoxHeent_normal = new com.hosv3.gui.component.HCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jCheckBoxLung_clear = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxLung_rhon = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxLung_whee = new com.hosv3.gui.component.HCheckBox();
        jPanel25 = new javax.swing.JPanel();
        jRadioButtonLung_fine_b = new javax.swing.JRadioButton();
        jRadioButtonLung_fine_l = new javax.swing.JRadioButton();
        jRadioButtonLung_fine_r = new javax.swing.JRadioButton();
        jCheckBoxLung_fine = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxLung_coar = new com.hosv3.gui.component.HCheckBox();
        jRadioButtonLung_coar_l = new javax.swing.JRadioButton();
        jRadioButtonLung_coar_r = new javax.swing.JRadioButton();
        jRadioButtonLung_coar_b = new javax.swing.JRadioButton();
        jLabel40 = new javax.swing.JLabel();
        jTextFieldLung_others = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jRadioButtonLung_whee_l = new javax.swing.JRadioButton();
        jRadioButtonLung_whee_r = new javax.swing.JRadioButton();
        jRadioButtonLung_whee_b = new javax.swing.JRadioButton();
        jRadioButtonLung_rhon_l = new javax.swing.JRadioButton();
        jRadioButtonLung_rhon_r = new javax.swing.JRadioButton();
        jRadioButtonLung_rhon_b = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jButtonSave1 = new javax.swing.JButton();
        jButtonCancel1 = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jCheckBoxAbdomen_bowe_inc = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxAbdomen_bowe_dec = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxAbdomen_bowe_nor = new com.hosv3.gui.component.HCheckBox();
        jLabel23 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jCheckBoxAbdomen_nor = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxAbdomen_sca = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxAbdomen_dis = new com.hosv3.gui.component.HCheckBox();
        jLabel24 = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        jTextFieldAbdomen_pal_dec = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        jCheckBoxAbdomen_rig = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxAbdomen_soft = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxAbdomen_gua = new com.hosv3.gui.component.HCheckBox();
        jPanel42 = new javax.swing.JPanel();
        jCheckBoxAbdomen_ten = new com.hosv3.gui.component.HCheckBox();
        jTextFieldAbdomen_tend_dec = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jCheckBoxAbdomen_dark = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxAbdomen_clear = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxAbdomen_regu = new com.hosv3.gui.component.HCheckBox();
        jLabel20 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jCheckBoxExtreme_norm = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxExtreme_pitt = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxExtreme_non_p = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxExtreme_ten = new com.hosv3.gui.component.HCheckBox();
        jTextFieldExtreme_ten_dec = new javax.swing.JTextField();
        jPanel44 = new javax.swing.JPanel();
        jTextFieldExtremity_oth = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jCheckBoxNeuro_refl_l = new com.hosv3.gui.component.HCheckBox();
        jComboBoxNeuro_refl_l = new javax.swing.JComboBox();
        jComboBoxNeuro_refl_r = new javax.swing.JComboBox();
        jCheckBoxNeuro_refl_r = new com.hosv3.gui.component.HCheckBox();
        jLabel38 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jComboBoxNeuro_cn = new javax.swing.JComboBox();
        jRadioButtonNeuro_cn_abno = new javax.swing.JRadioButton();
        jRadioButtonNeuro_cn_inta = new javax.swing.JRadioButton();
        jCheckBoxNeuro_cn = new com.hosv3.gui.component.HCheckBox();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jCheckBoxNeuro_up_l = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxNeuro_up_r = new com.hosv3.gui.component.HCheckBox();
        jComboBoxNeuro_grad_u_r = new javax.swing.JComboBox();
        jComboBoxNeuro_grad_u_l = new javax.swing.JComboBox();
        jLabel35 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jCheckBoxNeuro_low_l = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxNeuro_low_r = new com.hosv3.gui.component.HCheckBox();
        jComboBoxNeuro_grad_l_l = new javax.swing.JComboBox();
        jComboBoxNeuro_grad_l_r = new javax.swing.JComboBox();
        jLabel36 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jCheckBoxNeuro_gros = new com.hosv3.gui.component.HCheckBox();
        jPanel12 = new javax.swing.JPanel();
        jCheckBoxNeuro_atax = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxNeuro_ftn = new com.hosv3.gui.component.HCheckBox();
        jRadioButtonNeuro_cn_inta2 = new javax.swing.JRadioButton();
        jRadioButtonNeuro_cn_imp2 = new javax.swing.JRadioButton();
        jRadioButtonNeuro_cn_inta1 = new javax.swing.JRadioButton();
        jRadioButtonNeuro_cn_imp1 = new javax.swing.JRadioButton();
        jLabel37 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        jTextFieldNeuro_oth = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jCheckBoxSkin_nor = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxSkin_bull = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxSkin_tine = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxSkin_exfo = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxSkin_puts = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxSkin_scal = new com.hosv3.gui.component.HCheckBox();
        jCheckBoxSkin_ecze = new com.hosv3.gui.component.HCheckBox();
        jPanel43 = new javax.swing.JPanel();
        jTextFieldSkin_oth = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaOther = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        panelWound1 = new com.hosv3.gui.dialog.PanelWound();

        setLayout(new java.awt.GridBagLayout());

        jPanel33.setLayout(new java.awt.GridBagLayout());

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCheckBoxHeart_requ.setText("Regular");
        jCheckBoxHeart_requ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeart_requActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel4.add(jCheckBoxHeart_requ, gridBagConstraints);

        jCheckBoxHeart_gall.setText("Gallop");
        jCheckBoxHeart_gall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeart_gallActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel4.add(jCheckBoxHeart_gall, gridBagConstraints);

        jCheckBoxHeart_irre.setText("Irregular");
        jCheckBoxHeart_irre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeart_irreActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel4.add(jCheckBoxHeart_irre, gridBagConstraints);

        jPanel14.setLayout(new java.awt.GridBagLayout());

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonGroupMur_type.add(jRadioButtonHeart_mur_syst);
        jRadioButtonHeart_mur_syst.setText("systolic");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 2, 5);
        jPanel14.add(jRadioButtonHeart_mur_syst, gridBagConstraints);

        buttonGroupMur_type.add(jRadioButtonHeart_mur_dias);
        jRadioButtonHeart_mur_dias.setText("diastolic");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 5);
        jPanel14.add(jRadioButtonHeart_mur_dias, gridBagConstraints);

        jPanel11.setLayout(new java.awt.GridBagLayout());

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel13.setText("Grade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel11.add(jLabel13, gridBagConstraints);

        jComboBoxHeart_dias_grad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel11.add(jComboBoxHeart_dias_grad, gridBagConstraints);

        jLabel12.setText("Area");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 2, 5);
        jPanel11.add(jLabel12, gridBagConstraints);

        jComboBoxHeart_dias_area.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MVA", "AVA", "PVA", "LLPB" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 3, 3);
        jPanel11.add(jComboBoxHeart_dias_area, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel14.add(jPanel11, gridBagConstraints);

        jPanel47.setLayout(new java.awt.GridBagLayout());

        jPanel47.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel25.setText("Grade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel47.add(jLabel25, gridBagConstraints);

        jComboBoxHeart_sys_grad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel47.add(jComboBoxHeart_sys_grad, gridBagConstraints);

        jLabel26.setText("Area");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 2, 5);
        jPanel47.add(jLabel26, gridBagConstraints);

        jComboBoxHeart_sys_area.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MVA", "AVA", "PVA", "LLPB" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 3, 3);
        jPanel47.add(jComboBoxHeart_sys_area, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel14.add(jPanel47, gridBagConstraints);

        jCheckBoxHeart_murm.setText("murmur");
        jCheckBoxHeart_murm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeart_murmActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel14.add(jCheckBoxHeart_murm, gridBagConstraints);

        jCheckBoxHeart_no_murm.setText("no murmur");
        jCheckBoxHeart_no_murm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeart_no_murmActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel14.add(jCheckBoxHeart_no_murm, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(jPanel14, gridBagConstraints);

        jLabel16.setText("Beat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(jLabel16, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 2, 5);
        jPanel33.add(jPanel4, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCheckBox_ga_no_jaun.setText("no jaundice");
        jCheckBox_ga_no_jaun.setActionCommand("Normal conrciouness\n");
        jCheckBox_ga_no_jaun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ga_no_jaunActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel1.add(jCheckBox_ga_no_jaun, gridBagConstraints);

        jPanel22.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("E");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel22.add(jLabel1, gridBagConstraints);

        jComboBox_ga_gcs_m.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 5);
        jPanel22.add(jComboBox_ga_gcs_m, gridBagConstraints);

        jLabel11.setText("V");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel22.add(jLabel11, gridBagConstraints);

        jComboBox_ga_gcs_e.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 5);
        jPanel22.add(jComboBox_ga_gcs_e, gridBagConstraints);

        jLabel10.setText("M");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel22.add(jLabel10, gridBagConstraints);

        jComboBox_ga_gcs_v.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 5);
        jPanel22.add(jComboBox_ga_gcs_v, gridBagConstraints);

        jCheckBox_ga_gcs.setText("GCS");
        jCheckBox_ga_gcs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ga_gcsActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel22.add(jCheckBox_ga_gcs, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel1.add(jPanel22, gridBagConstraints);

        jCheckBox_ga_not_pale.setText("not pale");
        jCheckBox_ga_not_pale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ga_not_paleActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel1.add(jCheckBox_ga_not_pale, gridBagConstraints);

        jCheckBox_ga_feb.setText("febrile");
        jCheckBox_ga_feb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ga_febActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel1.add(jCheckBox_ga_feb, gridBagConstraints);

        jCheckBox_ga_dys.setText("dyspnea");
        jCheckBox_ga_dys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ga_dysActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel1.add(jCheckBox_ga_dys, gridBagConstraints);

        jCheckBox_ga_tach.setText("tachypnea");
        jCheckBox_ga_tach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ga_tachActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel1.add(jCheckBox_ga_tach, gridBagConstraints);

        jCheckBox_ga_look.setText("look sick");
        jCheckBox_ga_look.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ga_lookActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel1.add(jCheckBox_ga_look, gridBagConstraints);

        jPanel24.setLayout(new java.awt.GridBagLayout());

        jPanel24.setMinimumSize(new java.awt.Dimension(112, 24));
        jLabel2.setText("others (describe)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel24.add(jLabel2, gridBagConstraints);

        jTextArea_ga_other.setPreferredSize(new java.awt.Dimension(0, 21));
        jScrollPane2.setViewportView(jTextArea_ga_other);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel24.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jPanel24, gridBagConstraints);

        jPanel41.setLayout(new java.awt.GridBagLayout());

        jPanel41.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCheckBox_ga_dehy_ser.setText("severe");
        jCheckBox_ga_dehy_ser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ga_dehy_serActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel41.add(jCheckBox_ga_dehy_ser, gridBagConstraints);

        jCheckBox_ga_dehy_mod.setText("moderate");
        jCheckBox_ga_dehy_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ga_dehy_modActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel41.add(jCheckBox_ga_dehy_mod, gridBagConstraints);

        jCheckBox_ga_dehy_mild.setText("mild");
        jCheckBox_ga_dehy_mild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ga_dehy_mildActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel41.add(jCheckBox_ga_dehy_mild, gridBagConstraints);

        jCheckBox_ga_dehy_no.setText("no ");
        jCheckBox_ga_dehy_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ga_dehy_noActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        jPanel41.add(jCheckBox_ga_dehy_no, gridBagConstraints);

        jLabel22.setText("dehydration");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel41.add(jLabel22, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel1.add(jPanel41, gridBagConstraints);

        jPanel46.setLayout(new java.awt.GridBagLayout());

        jCheckBox_ga_normal.setText("normal");
        jCheckBox_ga_normal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ga_normalActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel46.add(jCheckBox_ga_normal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jPanel46, gridBagConstraints);

        jCheckBox_ga_jaun.setText("jaundice");
        jCheckBox_ga_jaun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ga_jaunActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel1.add(jCheckBox_ga_jaun, gridBagConstraints);

        jCheckBox_ga_pale.setText("pale");
        jCheckBox_ga_pale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ga_paleActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel1.add(jCheckBox_ga_pale, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 2, 2);
        jPanel33.add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jPanel23.setLayout(new java.awt.GridBagLayout());

        jPanel23.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCheckBoxHeent_eye_p.setText("pterygium");
        jCheckBoxHeent_eye_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeent_eye_pActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel23.add(jCheckBoxHeent_eye_p, gridBagConstraints);

        jCheckBoxHeent_eye_o.setText("opaque len");
        jCheckBoxHeent_eye_o.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeent_eye_oActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel23.add(jCheckBoxHeent_eye_o, gridBagConstraints);

        jCheckBoxHeent_eye_i.setText("inject conjuctiva");
        jCheckBoxHeent_eye_i.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeent_eye_iActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel23.add(jCheckBoxHeent_eye_i, gridBagConstraints);

        jCheckBoxHeent_eye_h.setText("hordeolum");
        jCheckBoxHeent_eye_h.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeent_eye_hActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel23.add(jCheckBoxHeent_eye_h, gridBagConstraints);

        buttonGroupEye_pter.add(jRadioButtonHeent_eye_p_l);
        jRadioButtonHeent_eye_p_l.setText("L");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel23.add(jRadioButtonHeent_eye_p_l, gridBagConstraints);

        buttonGroupEye_inj.add(jRadioButtonHeent_eye_i_r);
        jRadioButtonHeent_eye_i_r.setText("R");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel23.add(jRadioButtonHeent_eye_i_r, gridBagConstraints);

        buttonGroupEye_her.add(jRadioButtonHeent_eye_h_b);
        jRadioButtonHeent_eye_h_b.setText("both");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel23.add(jRadioButtonHeent_eye_h_b, gridBagConstraints);

        buttonGroupEye_inj.add(jRadioButtonHeent_eye_i_l);
        jRadioButtonHeent_eye_i_l.setText("L");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel23.add(jRadioButtonHeent_eye_i_l, gridBagConstraints);

        buttonGroupEye_pter.add(jRadioButtonHeent_eye_p_r);
        jRadioButtonHeent_eye_p_r.setText("R");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel23.add(jRadioButtonHeent_eye_p_r, gridBagConstraints);

        buttonGroupEye_opa.add(jRadioButtonHeent_eye_o_b);
        jRadioButtonHeent_eye_o_b.setText("both");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel23.add(jRadioButtonHeent_eye_o_b, gridBagConstraints);

        buttonGroupEye_opa.add(jRadioButtonHeent_eye_o_l);
        jRadioButtonHeent_eye_o_l.setText("L");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel23.add(jRadioButtonHeent_eye_o_l, gridBagConstraints);

        buttonGroupEye_opa.add(jRadioButtonHeent_eye_o_r);
        jRadioButtonHeent_eye_o_r.setText("R");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        jPanel23.add(jRadioButtonHeent_eye_o_r, gridBagConstraints);

        buttonGroupEye_inj.add(jRadioButtonHeent_eye_i_b);
        jRadioButtonHeent_eye_i_b.setText("both");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel23.add(jRadioButtonHeent_eye_i_b, gridBagConstraints);

        buttonGroupEye_her.add(jRadioButtonHeent_eye_h_l);
        jRadioButtonHeent_eye_h_l.setText("L");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel23.add(jRadioButtonHeent_eye_h_l, gridBagConstraints);

        buttonGroupEye_her.add(jRadioButtonHeent_eye_h_r);
        jRadioButtonHeent_eye_h_r.setText("R");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel23.add(jRadioButtonHeent_eye_h_r, gridBagConstraints);

        buttonGroupEye_pter.add(jRadioButtonHeent_eye_p_b);
        jRadioButtonHeent_eye_p_b.setText("both");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 3);
        jPanel23.add(jRadioButtonHeent_eye_p_b, gridBagConstraints);

        jLabel39.setText("eye");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 2, 10);
        jPanel23.add(jLabel39, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        jPanel9.add(jPanel23, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(jPanel9, gridBagConstraints);

        jPanel26.setLayout(new java.awt.GridBagLayout());

        jPanel26.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCheckBoxHeent_ton_ex.setText("exudate");
        jCheckBoxHeent_ton_ex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeent_ton_exActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 5);
        jPanel26.add(jCheckBoxHeent_ton_ex, gridBagConstraints);

        jCheckBoxHeent_ton_en.setText("enlarged");
        jCheckBoxHeent_ton_en.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeent_ton_enActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel26.add(jCheckBoxHeent_ton_en, gridBagConstraints);

        jCheckBoxHeent_ton_nor.setText("normal");
        jCheckBoxHeent_ton_nor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeent_ton_norActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel26.add(jCheckBoxHeent_ton_nor, gridBagConstraints);

        jLabel15.setText("tonsils");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel26.add(jLabel15, gridBagConstraints);

        jTextFieldTonsils_others.setMinimumSize(new java.awt.Dimension(112, 24));
        jTextFieldTonsils_others.setPreferredSize(new java.awt.Dimension(112, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 2, 5);
        jPanel26.add(jTextFieldTonsils_others, gridBagConstraints);

        jLabel27.setText("others (describe)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 2, 5);
        jPanel26.add(jLabel27, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel26, gridBagConstraints);

        jPanel29.setLayout(new java.awt.GridBagLayout());

        jPanel29.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCheckBoxHeent_thyr_e.setText("enlarge");
        jCheckBoxHeent_thyr_e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeent_thyr_eActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 5);
        jPanel29.add(jCheckBoxHeent_thyr_e, gridBagConstraints);

        jTextFieldThyroids_others.setMinimumSize(new java.awt.Dimension(200, 24));
        jTextFieldThyroids_others.setPreferredSize(new java.awt.Dimension(200, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 2, 5);
        jPanel29.add(jTextFieldThyroids_others, gridBagConstraints);

        jCheckBoxHeent_thyr_nor.setText("normal");
        jCheckBoxHeent_thyr_nor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeent_thyr_norActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 5);
        jPanel29.add(jCheckBoxHeent_thyr_nor, gridBagConstraints);

        jLabel19.setText("thyroids");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel29.add(jLabel19, gridBagConstraints);

        jLabel28.setText("others (describe)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 2, 5);
        jPanel29.add(jLabel28, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel29, gridBagConstraints);

        jPanel36.setLayout(new java.awt.GridBagLayout());

        jCheckBoxHeent_not_p.setText("not pale conjunctivae");
        jCheckBoxHeent_not_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeent_not_pActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel36.add(jCheckBoxHeent_not_p, gridBagConstraints);

        jCheckBoxHeent_pale.setText("pale conjunctivae");
        jCheckBoxHeent_pale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeent_paleActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 5);
        jPanel36.add(jCheckBoxHeent_pale, gridBagConstraints);

        jCheckBoxHeent_inje_p.setText("injected pharynx");
        jCheckBoxHeent_inje_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeent_inje_pActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 5);
        jPanel36.add(jCheckBoxHeent_inje_p, gridBagConstraints);

        jCheckBoxHeent_not_inj.setText("not injected pharynx");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel36.add(jCheckBoxHeent_not_inj, gridBagConstraints);

        jCheckBoxHeent_no_ict.setText("no icteric sclerae");
        jCheckBoxHeent_no_ict.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeent_no_ictActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel36.add(jCheckBoxHeent_no_ict, gridBagConstraints);

        jCheckBoxHeent_icte_s.setText("icteric sclerae");
        jCheckBoxHeent_icte_s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeent_icte_sActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 5);
        jPanel36.add(jCheckBoxHeent_icte_s, gridBagConstraints);

        jPanel35.setLayout(new java.awt.GridBagLayout());

        jCheckBoxHeent_cerv_n.setText("palpable");
        jCheckBoxHeent_cerv_n.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeent_cerv_nActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 5);
        jPanel35.add(jCheckBoxHeent_cerv_n, gridBagConstraints);

        jCheckBoxHeent_cerv_p.setText("normal");
        jCheckBoxHeent_cerv_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeent_cerv_pActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 5);
        jPanel35.add(jCheckBoxHeent_cerv_p, gridBagConstraints);

        jLabel17.setText("cervical LN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel35.add(jLabel17, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel36.add(jPanel35, gridBagConstraints);

        jCheckBoxHeent_normal.setText("normal");
        jCheckBoxHeent_normal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHeent_normalActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 5);
        jPanel36.add(jCheckBoxHeent_normal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jPanel36, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 2, 2);
        jPanel33.add(jPanel2, gridBagConstraints);

        jLabel3.setText("GA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel33.add(jLabel3, gridBagConstraints);

        jLabel4.setText("HEENT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel33.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Heart");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel33.add(jLabel5, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCheckBoxLung_clear.setText("Clear");
        jCheckBoxLung_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxLung_clearActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 0);
        jPanel3.add(jCheckBoxLung_clear, gridBagConstraints);

        jCheckBoxLung_rhon.setText("Rhonchi");
        jCheckBoxLung_rhon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxLung_rhonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel3.add(jCheckBoxLung_rhon, gridBagConstraints);

        jCheckBoxLung_whee.setText("Wheezing");
        jCheckBoxLung_whee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxLung_wheeActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel3.add(jCheckBoxLung_whee, gridBagConstraints);

        jPanel25.setLayout(new java.awt.GridBagLayout());

        jPanel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonGroupLung_fine.add(jRadioButtonLung_fine_b);
        jRadioButtonLung_fine_b.setText("both");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jPanel25.add(jRadioButtonLung_fine_b, gridBagConstraints);

        buttonGroupLung_fine.add(jRadioButtonLung_fine_l);
        jRadioButtonLung_fine_l.setText("L");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel25.add(jRadioButtonLung_fine_l, gridBagConstraints);

        buttonGroupLung_fine.add(jRadioButtonLung_fine_r);
        jRadioButtonLung_fine_r.setText("R");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel25.add(jRadioButtonLung_fine_r, gridBagConstraints);

        jCheckBoxLung_fine.setText("fine");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel25.add(jCheckBoxLung_fine, gridBagConstraints);

        jCheckBoxLung_coar.setText("coarse");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel25.add(jCheckBoxLung_coar, gridBagConstraints);

        buttonGroupLung_coar.add(jRadioButtonLung_coar_l);
        jRadioButtonLung_coar_l.setText("L");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel25.add(jRadioButtonLung_coar_l, gridBagConstraints);

        buttonGroupLung_coar.add(jRadioButtonLung_coar_r);
        jRadioButtonLung_coar_r.setText("R");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel25.add(jRadioButtonLung_coar_r, gridBagConstraints);

        buttonGroupLung_coar.add(jRadioButtonLung_coar_b);
        jRadioButtonLung_coar_b.setText("both");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jPanel25.add(jRadioButtonLung_coar_b, gridBagConstraints);

        jLabel40.setText("Crepitation");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 10);
        jPanel25.add(jLabel40, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel3.add(jPanel25, gridBagConstraints);

        jTextFieldLung_others.setMinimumSize(new java.awt.Dimension(150, 24));
        jTextFieldLung_others.setPreferredSize(new java.awt.Dimension(150, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 1);
        jPanel3.add(jTextFieldLung_others, gridBagConstraints);

        jLabel29.setText("others (describe)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel3.add(jLabel29, gridBagConstraints);

        buttonGroupLung_whee.add(jRadioButtonLung_whee_l);
        jRadioButtonLung_whee_l.setText("L");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(jRadioButtonLung_whee_l, gridBagConstraints);

        buttonGroupLung_whee.add(jRadioButtonLung_whee_r);
        jRadioButtonLung_whee_r.setText("R");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(jRadioButtonLung_whee_r, gridBagConstraints);

        buttonGroupLung_whee.add(jRadioButtonLung_whee_b);
        jRadioButtonLung_whee_b.setText("both");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel3.add(jRadioButtonLung_whee_b, gridBagConstraints);

        buttonGroupLung_rhon.add(jRadioButtonLung_rhon_l);
        jRadioButtonLung_rhon_l.setText("L");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(jRadioButtonLung_rhon_l, gridBagConstraints);

        buttonGroupLung_rhon.add(jRadioButtonLung_rhon_r);
        jRadioButtonLung_rhon_r.setText("R");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(jRadioButtonLung_rhon_r, gridBagConstraints);

        buttonGroupLung_rhon.add(jRadioButtonLung_rhon_b);
        jRadioButtonLung_rhon_b.setText("both");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel3.add(jRadioButtonLung_rhon_b, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 2, 2);
        jPanel33.add(jPanel3, gridBagConstraints);

        jLabel6.setText("Lung");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel33.add(jLabel6, gridBagConstraints);

        jPanel13.setLayout(new java.awt.GridBagLayout());

        jButtonSave1.setText("\u0e1a\u0e31\u0e19\u0e17\u0e36\u0e01");
        jButtonSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSave1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel13.add(jButtonSave1, gridBagConstraints);

        jButtonCancel1.setText("\u0e22\u0e01\u0e40\u0e25\u0e34\u0e01");
        jButtonCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancel1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 2);
        jPanel13.add(jButtonCancel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        jPanel33.add(jPanel13, gridBagConstraints);

        jTabbedPane1.addTab("\u0e15\u0e23\u0e27\u0e08\u0e23\u0e48\u0e32\u0e07\u0e01\u0e32\u0e22 1", jPanel33);

        jPanel34.setLayout(new java.awt.GridBagLayout());

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel15.setLayout(new java.awt.GridBagLayout());

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCheckBoxAbdomen_bowe_inc.setText("increased");
        jCheckBoxAbdomen_bowe_inc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAbdomen_bowe_incActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 5);
        jPanel15.add(jCheckBoxAbdomen_bowe_inc, gridBagConstraints);

        jCheckBoxAbdomen_bowe_dec.setText("decreased");
        jCheckBoxAbdomen_bowe_dec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAbdomen_bowe_decActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 5);
        jPanel15.add(jCheckBoxAbdomen_bowe_dec, gridBagConstraints);

        jCheckBoxAbdomen_bowe_nor.setText("normal");
        jCheckBoxAbdomen_bowe_nor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAbdomen_bowe_norActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 5);
        jPanel15.add(jCheckBoxAbdomen_bowe_nor, gridBagConstraints);

        jLabel23.setText("bowel sound");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel15.add(jLabel23, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(jPanel15, gridBagConstraints);

        jPanel30.setLayout(new java.awt.GridBagLayout());

        jPanel30.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCheckBoxAbdomen_nor.setText("normal");
        jCheckBoxAbdomen_nor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAbdomen_norActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 5);
        jPanel30.add(jCheckBoxAbdomen_nor, gridBagConstraints);

        jCheckBoxAbdomen_sca.setText("scaphoid");
        jCheckBoxAbdomen_sca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAbdomen_scaActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 5);
        jPanel30.add(jCheckBoxAbdomen_sca, gridBagConstraints);

        jCheckBoxAbdomen_dis.setText("distend");
        jCheckBoxAbdomen_dis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAbdomen_disActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 5);
        jPanel30.add(jCheckBoxAbdomen_dis, gridBagConstraints);

        jLabel24.setText("contour");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel30.add(jLabel24, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(jPanel30, gridBagConstraints);

        jPanel38.setLayout(new java.awt.GridBagLayout());

        jPanel38.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextFieldAbdomen_pal_dec.setMinimumSize(new java.awt.Dimension(150, 24));
        jTextFieldAbdomen_pal_dec.setPreferredSize(new java.awt.Dimension(150, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 4, 2, 2);
        jPanel38.add(jTextFieldAbdomen_pal_dec, gridBagConstraints);

        jLabel21.setText("palpation");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel38.add(jLabel21, gridBagConstraints);

        jPanel40.setLayout(new java.awt.GridBagLayout());

        jCheckBoxAbdomen_rig.setText("rigidity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel40.add(jCheckBoxAbdomen_rig, gridBagConstraints);

        jCheckBoxAbdomen_soft.setText("soft");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel40.add(jCheckBoxAbdomen_soft, gridBagConstraints);

        jCheckBoxAbdomen_gua.setText("guarding");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel40.add(jCheckBoxAbdomen_gua, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel38.add(jPanel40, gridBagConstraints);

        jPanel42.setLayout(new java.awt.GridBagLayout());

        jCheckBoxAbdomen_ten.setText("tender (area)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 0, 1);
        jPanel42.add(jCheckBoxAbdomen_ten, gridBagConstraints);

        jTextFieldAbdomen_tend_dec.setMinimumSize(new java.awt.Dimension(112, 24));
        jTextFieldAbdomen_tend_dec.setPreferredSize(new java.awt.Dimension(112, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 10, 0, 1);
        jPanel42.add(jTextFieldAbdomen_tend_dec, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 1);
        jPanel38.add(jPanel42, gridBagConstraints);

        jLabel32.setText("others (describe)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 11, 2, 3);
        jPanel38.add(jLabel32, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(jPanel38, gridBagConstraints);

        jPanel39.setLayout(new java.awt.GridBagLayout());

        jPanel39.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCheckBoxAbdomen_dark.setText("\u0e17\u0e36\u0e1a");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 5);
        jPanel39.add(jCheckBoxAbdomen_dark, gridBagConstraints);

        jCheckBoxAbdomen_clear.setText("\u0e42\u0e1b\u0e23\u0e48\u0e07");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 5);
        jPanel39.add(jCheckBoxAbdomen_clear, gridBagConstraints);

        jCheckBoxAbdomen_regu.setText("\u0e1b\u0e01\u0e15\u0e34");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 5);
        jPanel39.add(jCheckBoxAbdomen_regu, gridBagConstraints);

        jLabel20.setText("\u0e40\u0e04\u0e32\u0e30");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel39.add(jLabel20, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(jPanel39, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 2, 2);
        jPanel34.add(jPanel5, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCheckBoxExtreme_norm.setText("normal");
        jCheckBoxExtreme_norm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxExtreme_normActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel6.add(jCheckBoxExtreme_norm, gridBagConstraints);

        jCheckBoxExtreme_pitt.setText("pitting edema");
        jCheckBoxExtreme_pitt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxExtreme_pittActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 5);
        jPanel6.add(jCheckBoxExtreme_pitt, gridBagConstraints);

        jCheckBoxExtreme_non_p.setText("non pitting edema");
        jCheckBoxExtreme_non_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxExtreme_non_pActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel6.add(jCheckBoxExtreme_non_p, gridBagConstraints);

        jCheckBoxExtreme_ten.setText("tender at");
        jCheckBoxExtreme_ten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxExtreme_tenActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel6.add(jCheckBoxExtreme_ten, gridBagConstraints);

        jTextFieldExtreme_ten_dec.setMinimumSize(new java.awt.Dimension(112, 24));
        jTextFieldExtreme_ten_dec.setPreferredSize(new java.awt.Dimension(112, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel6.add(jTextFieldExtreme_ten_dec, gridBagConstraints);

        jPanel44.setLayout(new java.awt.GridBagLayout());

        jTextFieldExtremity_oth.setMinimumSize(new java.awt.Dimension(150, 24));
        jTextFieldExtremity_oth.setPreferredSize(new java.awt.Dimension(150, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel44.add(jTextFieldExtremity_oth, gridBagConstraints);

        jLabel30.setText("others (describe)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel44.add(jLabel30, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 1, 0);
        jPanel6.add(jPanel44, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 2, 2);
        jPanel34.add(jPanel6, gridBagConstraints);

        jPanel10.setLayout(new java.awt.GridBagLayout());

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel21.setLayout(new java.awt.GridBagLayout());

        jPanel21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCheckBoxNeuro_refl_l.setText("L");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 2);
        jPanel21.add(jCheckBoxNeuro_refl_l, gridBagConstraints);

        jComboBoxNeuro_refl_l.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1+", "2+", "3+" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 2);
        jPanel21.add(jComboBoxNeuro_refl_l, gridBagConstraints);

        jComboBoxNeuro_refl_r.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1+", "2+", "3+" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 2);
        jPanel21.add(jComboBoxNeuro_refl_r, gridBagConstraints);

        jCheckBoxNeuro_refl_r.setText("R");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 2);
        jPanel21.add(jCheckBoxNeuro_refl_r, gridBagConstraints);

        jLabel38.setText("Reflex");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 6);
        jPanel21.add(jLabel38, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel10.add(jPanel21, gridBagConstraints);

        jPanel32.setLayout(new java.awt.GridBagLayout());

        jPanel31.setLayout(new java.awt.GridBagLayout());

        jPanel31.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel16.setLayout(new java.awt.GridBagLayout());

        jComboBoxNeuro_cn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel16.add(jComboBoxNeuro_cn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel31.add(jPanel16, gridBagConstraints);

        buttonGroupNeuro_cn.add(jRadioButtonNeuro_cn_abno);
        jRadioButtonNeuro_cn_abno.setText("Abnormal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel31.add(jRadioButtonNeuro_cn_abno, gridBagConstraints);

        buttonGroupNeuro_cn.add(jRadioButtonNeuro_cn_inta);
        jRadioButtonNeuro_cn_inta.setText("Intact");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel31.add(jRadioButtonNeuro_cn_inta, gridBagConstraints);

        jCheckBoxNeuro_cn.setText("CN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel31.add(jCheckBoxNeuro_cn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel32.add(jPanel31, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel10.add(jPanel32, gridBagConstraints);

        jPanel18.setLayout(new java.awt.GridBagLayout());

        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel19.setLayout(new java.awt.GridBagLayout());

        jCheckBoxNeuro_up_l.setText("L");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel19.add(jCheckBoxNeuro_up_l, gridBagConstraints);

        jCheckBoxNeuro_up_r.setText("R");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jPanel19.add(jCheckBoxNeuro_up_r, gridBagConstraints);

        jComboBoxNeuro_grad_u_r.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel19.add(jComboBoxNeuro_grad_u_r, gridBagConstraints);

        jComboBoxNeuro_grad_u_l.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel19.add(jComboBoxNeuro_grad_u_l, gridBagConstraints);

        jLabel35.setText("Upper");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel19.add(jLabel35, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 2);
        jPanel18.add(jPanel19, gridBagConstraints);

        jPanel20.setLayout(new java.awt.GridBagLayout());

        jCheckBoxNeuro_low_l.setText("L");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel20.add(jCheckBoxNeuro_low_l, gridBagConstraints);

        jCheckBoxNeuro_low_r.setText("R");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jPanel20.add(jCheckBoxNeuro_low_r, gridBagConstraints);

        jComboBoxNeuro_grad_l_l.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel20.add(jComboBoxNeuro_grad_l_l, gridBagConstraints);

        jComboBoxNeuro_grad_l_r.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel20.add(jComboBoxNeuro_grad_l_r, gridBagConstraints);

        jLabel36.setText("Lower");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel20.add(jLabel36, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 2);
        jPanel18.add(jPanel20, gridBagConstraints);

        jLabel34.setText("Motor Grade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel18.add(jLabel34, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 1.0;
        jPanel10.add(jPanel18, gridBagConstraints);

        jCheckBoxNeuro_gros.setText("Grossly intact");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel10.add(jCheckBoxNeuro_gros, gridBagConstraints);

        jPanel12.setLayout(new java.awt.GridBagLayout());

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCheckBoxNeuro_atax.setText("Ataxia");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel12.add(jCheckBoxNeuro_atax, gridBagConstraints);

        jCheckBoxNeuro_ftn.setText("FTN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel12.add(jCheckBoxNeuro_ftn, gridBagConstraints);

        buttonGroupNeuro_ftn.add(jRadioButtonNeuro_cn_inta2);
        jRadioButtonNeuro_cn_inta2.setText("Intact");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel12.add(jRadioButtonNeuro_cn_inta2, gridBagConstraints);

        buttonGroupNeuro_ftn.add(jRadioButtonNeuro_cn_imp2);
        jRadioButtonNeuro_cn_imp2.setText("Impare");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel12.add(jRadioButtonNeuro_cn_imp2, gridBagConstraints);

        buttonGroupNeuro_ata.add(jRadioButtonNeuro_cn_inta1);
        jRadioButtonNeuro_cn_inta1.setText("Intact");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel12.add(jRadioButtonNeuro_cn_inta1, gridBagConstraints);

        buttonGroupNeuro_ata.add(jRadioButtonNeuro_cn_imp1);
        jRadioButtonNeuro_cn_imp1.setText("Impare");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel12.add(jRadioButtonNeuro_cn_imp1, gridBagConstraints);

        jLabel37.setText("Cerebellar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 6);
        jPanel12.add(jLabel37, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel10.add(jPanel12, gridBagConstraints);

        jPanel45.setLayout(new java.awt.GridBagLayout());

        jTextFieldNeuro_oth.setMinimumSize(new java.awt.Dimension(150, 24));
        jTextFieldNeuro_oth.setPreferredSize(new java.awt.Dimension(150, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel45.add(jTextFieldNeuro_oth, gridBagConstraints);

        jLabel33.setText("others (describe)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 10, 2, 5);
        jPanel45.add(jLabel33, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanel10.add(jPanel45, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 2, 2);
        jPanel34.add(jPanel10, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jCheckBoxSkin_nor.setText("normal");
        jCheckBoxSkin_nor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSkin_norActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jCheckBoxSkin_nor, gridBagConstraints);

        jCheckBoxSkin_bull.setText("bullous");
        jCheckBoxSkin_bull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSkin_bullActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jCheckBoxSkin_bull, gridBagConstraints);

        jCheckBoxSkin_tine.setText("tined");
        jCheckBoxSkin_tine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSkin_tineActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jCheckBoxSkin_tine, gridBagConstraints);

        jCheckBoxSkin_exfo.setText("exfoliative");
        jCheckBoxSkin_exfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSkin_exfoActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jCheckBoxSkin_exfo, gridBagConstraints);

        jCheckBoxSkin_puts.setText("pustula");
        jCheckBoxSkin_puts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSkin_putsActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jCheckBoxSkin_puts, gridBagConstraints);

        jCheckBoxSkin_scal.setText("scale");
        jCheckBoxSkin_scal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSkin_scalActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jCheckBoxSkin_scal, gridBagConstraints);

        jCheckBoxSkin_ecze.setText("eczema");
        jCheckBoxSkin_ecze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSkin_eczeActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel7.add(jCheckBoxSkin_ecze, gridBagConstraints);

        jPanel43.setLayout(new java.awt.GridBagLayout());

        jTextFieldSkin_oth.setMinimumSize(new java.awt.Dimension(150, 24));
        jTextFieldSkin_oth.setPreferredSize(new java.awt.Dimension(150, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel43.add(jTextFieldSkin_oth, gridBagConstraints);

        jLabel31.setText("others (describe)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel43.add(jLabel31, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        jPanel7.add(jPanel43, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 2, 2);
        jPanel34.add(jPanel7, gridBagConstraints);

        jPanel17.setLayout(new java.awt.GridBagLayout());

        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane1.setViewportView(jTextAreaOther);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel17.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 2, 2);
        jPanel34.add(jPanel17, gridBagConstraints);

        jLabel7.setText("Abdomen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel34.add(jLabel7, gridBagConstraints);

        jLabel8.setText("Neuro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel34.add(jLabel8, gridBagConstraints);

        jLabel9.setText("Extremity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel34.add(jLabel9, gridBagConstraints);

        jLabel14.setText("Others (describe)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel34.add(jLabel14, gridBagConstraints);

        jLabel18.setText("Skin");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel34.add(jLabel18, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jButtonSave.setText("\u0e1a\u0e31\u0e19\u0e17\u0e36\u0e01");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel8.add(jButtonSave, gridBagConstraints);

        jButtonCancel.setText("\u0e22\u0e01\u0e40\u0e25\u0e34\u0e01");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 2);
        jPanel8.add(jButtonCancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        jPanel34.add(jPanel8, gridBagConstraints);

        jTabbedPane1.addTab("\u0e15\u0e23\u0e27\u0e08\u0e23\u0e48\u0e32\u0e07\u0e01\u0e32\u0e22 2", jPanel34);

        jTabbedPane1.addTab("\u0e15\u0e23\u0e27\u0e08\u0e23\u0e48\u0e32\u0e07\u0e01\u0e32\u0e22 3", panelWound1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jTabbedPane1, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancel1ActionPerformed
        if(theJD!=null)
            theJD.dispose();
    }//GEN-LAST:event_jButtonCancel1ActionPerformed

    private void jButtonSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSave1ActionPerformed
        theHC.theVitalControl.savePhysicalExam(getPhysicalExamV());
        ret = true;
        if(theJD!=null)
            theJD.dispose();
    }//GEN-LAST:event_jButtonSave1ActionPerformed

    private void jCheckBoxHeart_no_murmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeart_no_murmActionPerformed
//        this.setSelectedDep(jCheckBoxExtreme_norm,jCheckBoxExtreme_ten);
    }//GEN-LAST:event_jCheckBoxHeart_no_murmActionPerformed

    private void jCheckBoxHeart_murmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeart_murmActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxHeart_murmActionPerformed

    private void jCheckBoxHeent_eye_hActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeent_eye_hActionPerformed
        jRadioButtonHeent_eye_h_b.setSelected(jCheckBoxHeent_eye_h.isSelected());
    }//GEN-LAST:event_jCheckBoxHeent_eye_hActionPerformed

    private void jCheckBoxHeent_eye_oActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeent_eye_oActionPerformed
        jRadioButtonHeent_eye_o_b.setSelected( jCheckBoxHeent_eye_o.isSelected());
    }//GEN-LAST:event_jCheckBoxHeent_eye_oActionPerformed

    private void jCheckBoxHeent_eye_iActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeent_eye_iActionPerformed
        jRadioButtonHeent_eye_i_b.setSelected( jCheckBoxHeent_eye_i.isSelected());
    }//GEN-LAST:event_jCheckBoxHeent_eye_iActionPerformed

    private void jCheckBoxHeent_eye_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeent_eye_pActionPerformed
        jRadioButtonHeent_eye_p_b.setSelected( jCheckBoxHeent_eye_p.isSelected());
    }//GEN-LAST:event_jCheckBoxHeent_eye_pActionPerformed

    private void jCheckBoxExtreme_tenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxExtreme_tenActionPerformed
        this.setSelectedDep(jCheckBoxExtreme_norm,jCheckBoxExtreme_ten);
    }//GEN-LAST:event_jCheckBoxExtreme_tenActionPerformed

    private void jCheckBoxExtreme_non_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxExtreme_non_pActionPerformed
        this.setSelectedDep(jCheckBoxExtreme_norm,jCheckBoxExtreme_non_p);
    }//GEN-LAST:event_jCheckBoxExtreme_non_pActionPerformed

    private void jCheckBoxExtreme_pittActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxExtreme_pittActionPerformed
        this.setSelectedDep(jCheckBoxExtreme_norm,jCheckBoxExtreme_pitt);
    }//GEN-LAST:event_jCheckBoxExtreme_pittActionPerformed

    private void jCheckBoxExtreme_normActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxExtreme_normActionPerformed
        if(jCheckBoxExtreme_norm.isSelected())
        {
            jCheckBoxExtreme_pitt.setSelected(false);
            jCheckBoxExtreme_non_p.setSelected(false);
            jCheckBoxExtreme_ten.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxExtreme_normActionPerformed

    private void jCheckBoxSkin_eczeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSkin_eczeActionPerformed
        this.setSelectedDep(jCheckBoxSkin_nor,jCheckBoxSkin_ecze);
    }//GEN-LAST:event_jCheckBoxSkin_eczeActionPerformed

    private void jCheckBoxSkin_scalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSkin_scalActionPerformed
        this.setSelectedDep(jCheckBoxSkin_nor,jCheckBoxSkin_scal);
    }//GEN-LAST:event_jCheckBoxSkin_scalActionPerformed

    private void jCheckBoxSkin_putsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSkin_putsActionPerformed
        this.setSelectedDep(jCheckBoxSkin_nor,jCheckBoxSkin_puts);
    }//GEN-LAST:event_jCheckBoxSkin_putsActionPerformed

    private void jCheckBoxSkin_exfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSkin_exfoActionPerformed
        this.setSelectedDep(jCheckBoxSkin_nor,jCheckBoxSkin_exfo);
    }//GEN-LAST:event_jCheckBoxSkin_exfoActionPerformed

    private void jCheckBoxSkin_bullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSkin_bullActionPerformed
        this.setSelectedDep(jCheckBoxSkin_nor,jCheckBoxSkin_bull);
    }//GEN-LAST:event_jCheckBoxSkin_bullActionPerformed

    private void jCheckBoxSkin_norActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSkin_norActionPerformed
        if(jCheckBoxSkin_nor.isSelected())
        {
            jCheckBoxSkin_bull.setSelected(false);
            jCheckBoxSkin_puts.setSelected(false);
            jCheckBoxSkin_exfo.setSelected(false);
            jCheckBoxSkin_scal.setSelected(false);
            jCheckBoxSkin_ecze.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxSkin_norActionPerformed

    private void jCheckBoxAbdomen_bowe_decActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAbdomen_bowe_decActionPerformed
        this.setSelectedDep(jCheckBoxAbdomen_bowe_nor,jCheckBoxAbdomen_bowe_dec);
    }//GEN-LAST:event_jCheckBoxAbdomen_bowe_decActionPerformed

    private void jCheckBoxAbdomen_bowe_incActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAbdomen_bowe_incActionPerformed
        this.setSelectedDep(jCheckBoxAbdomen_bowe_nor,jCheckBoxAbdomen_bowe_inc);
    }//GEN-LAST:event_jCheckBoxAbdomen_bowe_incActionPerformed

    private void jCheckBoxAbdomen_bowe_norActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAbdomen_bowe_norActionPerformed
        if(jCheckBoxAbdomen_bowe_nor.isSelected())
        {
            jCheckBoxAbdomen_bowe_inc.setSelected(false);
            jCheckBoxAbdomen_bowe_dec.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxAbdomen_bowe_norActionPerformed

    private void jCheckBoxAbdomen_scaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAbdomen_scaActionPerformed
        this.setSelectedDep(jCheckBoxAbdomen_nor,jCheckBoxAbdomen_sca);
    }//GEN-LAST:event_jCheckBoxAbdomen_scaActionPerformed

    private void jCheckBoxAbdomen_disActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAbdomen_disActionPerformed
        this.setSelectedDep(jCheckBoxAbdomen_nor,jCheckBoxAbdomen_dis);
    }//GEN-LAST:event_jCheckBoxAbdomen_disActionPerformed

    private void jCheckBoxAbdomen_norActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAbdomen_norActionPerformed
        if(jCheckBoxAbdomen_nor.isSelected())
        {
            jCheckBoxAbdomen_dis.setSelected(false);
            jCheckBoxAbdomen_sca.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxAbdomen_norActionPerformed

    private void jCheckBoxLung_rhonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxLung_rhonActionPerformed
        this.setSelectedDep(jCheckBoxLung_clear,jCheckBoxLung_rhon);
    }//GEN-LAST:event_jCheckBoxLung_rhonActionPerformed

    private void jCheckBoxLung_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxLung_clearActionPerformed
        if(jCheckBoxLung_clear.isSelected())
        {
            jCheckBoxLung_rhon.setSelected(false);
            jCheckBoxLung_whee.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxLung_clearActionPerformed

    private void jCheckBoxHeart_irreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeart_irreActionPerformed
        this.setSelectedDep(jCheckBoxHeart_requ,jCheckBoxHeart_irre);
    }//GEN-LAST:event_jCheckBoxHeart_irreActionPerformed

    private void jCheckBoxHeart_gallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeart_gallActionPerformed
        this.setSelectedDep(jCheckBoxHeart_requ,jCheckBoxHeart_gall);
    }//GEN-LAST:event_jCheckBoxHeart_gallActionPerformed

    private void jCheckBoxHeart_requActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeart_requActionPerformed
        if(jCheckBoxHeart_requ.isSelected())
        {
            jCheckBoxHeart_gall.setSelected(false);
            jCheckBoxHeart_irre.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxHeart_requActionPerformed

    private void jCheckBoxHeent_ton_exActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeent_ton_exActionPerformed
        this.setSelectedDep(jCheckBoxHeent_ton_nor,jCheckBoxHeent_ton_ex);
    }//GEN-LAST:event_jCheckBoxHeent_ton_exActionPerformed

    private void jCheckBoxHeent_ton_enActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeent_ton_enActionPerformed
        this.setSelectedDep(jCheckBoxHeent_ton_nor,jCheckBoxHeent_ton_en);
    }//GEN-LAST:event_jCheckBoxHeent_ton_enActionPerformed

    private void jCheckBoxHeent_ton_norActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeent_ton_norActionPerformed
        if(jCheckBoxHeent_ton_nor.isSelected())
        {
            jCheckBoxHeent_ton_en.setSelected(false);
            jCheckBoxHeent_ton_ex.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxHeent_ton_norActionPerformed

    private void jCheckBoxHeent_thyr_eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeent_thyr_eActionPerformed
        jCheckBoxHeent_thyr_nor.setSelected(!jCheckBoxHeent_thyr_e.isSelected());
    }//GEN-LAST:event_jCheckBoxHeent_thyr_eActionPerformed

    private void jCheckBoxHeent_thyr_norActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeent_thyr_norActionPerformed
        jCheckBoxHeent_thyr_e.setSelected(!jCheckBoxHeent_thyr_nor.isSelected());
    }//GEN-LAST:event_jCheckBoxHeent_thyr_norActionPerformed

    private void jCheckBoxHeent_cerv_nActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeent_cerv_nActionPerformed
        //jCheckBoxHeent_cerv_p.setSelected(!jCheckBoxHeent_cerv_n.isSelected());
    }//GEN-LAST:event_jCheckBoxHeent_cerv_nActionPerformed

    private void jCheckBoxHeent_cerv_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeent_cerv_pActionPerformed
        //jCheckBoxHeent_cerv_n.setSelected(!jCheckBoxHeent_cerv_p.isSelected());
    }//GEN-LAST:event_jCheckBoxHeent_cerv_pActionPerformed

    private void jCheckBoxHeent_no_ictActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeent_no_ictActionPerformed
        setSelectedDep(jCheckBoxHeent_normal,jCheckBoxHeent_no_ict);
    }//GEN-LAST:event_jCheckBoxHeent_no_ictActionPerformed

    private void jCheckBoxHeent_icte_sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeent_icte_sActionPerformed
        setSelectedDep(jCheckBoxHeent_normal,jCheckBoxHeent_icte_s);
    }//GEN-LAST:event_jCheckBoxHeent_icte_sActionPerformed

    private void jCheckBoxHeent_inje_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeent_inje_pActionPerformed
        setSelectedDep(jCheckBoxHeent_normal,jCheckBoxHeent_inje_p);
    }//GEN-LAST:event_jCheckBoxHeent_inje_pActionPerformed

    private void jCheckBoxHeent_paleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeent_paleActionPerformed
        setSelectedDep(jCheckBoxHeent_normal,jCheckBoxHeent_pale);
    }//GEN-LAST:event_jCheckBoxHeent_paleActionPerformed

    private void jCheckBoxHeent_normalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeent_normalActionPerformed
        if(jCheckBoxHeent_normal.isSelected())
        {
            setSelectedHeent(false);
            jCheckBoxHeent_normal.setSelected(true);
        }
    }//GEN-LAST:event_jCheckBoxHeent_normalActionPerformed
    public void setSelectedHeent(boolean b){
        jCheckBoxHeent_normal.setSelected(b);
        jCheckBoxHeent_pale.setSelected(b);
        jCheckBoxHeent_inje_p.setSelected(b);
        jCheckBoxHeent_no_ict.setSelected(b);
        jCheckBoxHeent_icte_s.setSelected(b);
        jCheckBoxHeent_not_p.setSelected(b);
        jCheckBoxHeent_not_inj.setSelected(b);
    }
    private void jCheckBox_ga_dysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ga_dysActionPerformed
        setSelectedDep(jCheckBox_ga_normal,jCheckBox_ga_dys);
            
    }//GEN-LAST:event_jCheckBox_ga_dysActionPerformed

    private void jCheckBox_ga_tachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ga_tachActionPerformed
        setSelectedDep(jCheckBox_ga_normal,jCheckBox_ga_tach);
            
    }//GEN-LAST:event_jCheckBox_ga_tachActionPerformed

    private void jCheckBox_ga_lookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ga_lookActionPerformed
        setSelectedDep(jCheckBox_ga_normal,jCheckBox_ga_look);
            
    }//GEN-LAST:event_jCheckBox_ga_lookActionPerformed

    private void jCheckBox_ga_febActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ga_febActionPerformed
        setSelectedDep(jCheckBox_ga_normal,jCheckBox_ga_feb);
            
    }//GEN-LAST:event_jCheckBox_ga_febActionPerformed
            
    private void jCheckBox_ga_dehy_serActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ga_dehy_serActionPerformed
        setSelectedDep(jCheckBox_ga_normal,jCheckBox_ga_dehy_ser);
            
    }//GEN-LAST:event_jCheckBox_ga_dehy_serActionPerformed

    private void jCheckBox_ga_dehy_mildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ga_dehy_mildActionPerformed
        setSelectedDep(jCheckBox_ga_normal,jCheckBox_ga_dehy_mild);
            
    }//GEN-LAST:event_jCheckBox_ga_dehy_mildActionPerformed

    private void jCheckBox_ga_gcsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ga_gcsActionPerformed
        setSelectedDep(jCheckBox_ga_normal,jCheckBox_ga_gcs);
            
    }//GEN-LAST:event_jCheckBox_ga_gcsActionPerformed

    private void jCheckBox_ga_jaunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ga_jaunActionPerformed
        setSelectedDep(jCheckBox_ga_normal,jCheckBox_ga_jaun);
            
    }//GEN-LAST:event_jCheckBox_ga_jaunActionPerformed

    private void jCheckBox_ga_not_paleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ga_not_paleActionPerformed
        setSelectedDep(jCheckBox_ga_normal,jCheckBox_ga_not_pale);
            
    }//GEN-LAST:event_jCheckBox_ga_not_paleActionPerformed

    private void jCheckBox_ga_paleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ga_paleActionPerformed
        setSelectedDep(jCheckBox_ga_normal,jCheckBox_ga_pale);
    }//GEN-LAST:event_jCheckBox_ga_paleActionPerformed
    public void setSelectedGA(boolean b){
        jCheckBox_ga_dys.setSelected(b);
        jCheckBox_ga_tach.setSelected(b);
        jCheckBox_ga_look.setSelected(b);
        jCheckBox_ga_feb.setSelected(b);
        jCheckBox_ga_dehy_ser.setSelected(b);
        jCheckBox_ga_dehy_mild.setSelected(b);
        jCheckBox_ga_gcs.setSelected(b);
        jCheckBox_ga_jaun.setSelected(b);
        jCheckBox_ga_jaun.setSelected(b);
        jCheckBox_ga_not_pale.setSelected(b);
        jCheckBox_ga_pale.setSelected(b);
        jCheckBox_ga_pale.setSelected(b);
        jCheckBox_ga_no_jaun.setSelected(b);
    }
    public void setSelectedDep(JCheckBox ref,JCheckBox main){
        if(main.isSelected())
            ref.setSelected(false);
    }
    private void jCheckBox_ga_normalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ga_normalActionPerformed
        if(jCheckBox_ga_normal.isSelected())
        {
            setSelectedGA(false);
            jCheckBox_ga_normal.setSelected(true);
        }
    }//GEN-LAST:event_jCheckBox_ga_normalActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        if(theJD!=null)
            theJD.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jCheckBoxSkin_tineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSkin_tineActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxSkin_tineActionPerformed

    private void jCheckBoxLung_wheeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxLung_wheeActionPerformed
        this.setSelectedDep(jCheckBoxLung_clear,jCheckBoxLung_whee);
    }//GEN-LAST:event_jCheckBoxLung_wheeActionPerformed

    private void jCheckBoxHeent_not_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHeent_not_pActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxHeent_not_pActionPerformed

    private void jCheckBox_ga_dehy_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ga_dehy_modActionPerformed
        this.setSelectedDep(this.jCheckBox_ga_normal,jCheckBox_ga_dehy_mod);
    }//GEN-LAST:event_jCheckBox_ga_dehy_modActionPerformed

    private void jCheckBox_ga_dehy_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ga_dehy_noActionPerformed
        this.setSelectedDep(this.jCheckBox_ga_normal,jCheckBox_ga_no_jaun);
    }//GEN-LAST:event_jCheckBox_ga_dehy_noActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        theHC.theVitalControl.savePhysicalExam(getPhysicalExamV());
        ret = true;
        if(theJD!=null)
            theJD.dispose();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jCheckBox_ga_no_jaunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ga_no_jaunActionPerformed
        this.setSelectedDep(this.jCheckBox_ga_normal,jCheckBox_ga_no_jaun);
    }//GEN-LAST:event_jCheckBox_ga_no_jaunActionPerformed
    
    
    public void setDefault()
    {
        Constant.println("----------------setDefault------------------");
        jCheckBox_ga_normal.setSelected(false); 
        jCheckBox_ga_pale.setSelected(false);
        jCheckBox_ga_not_pale.setSelected(false);
        jCheckBox_ga_jaun.setSelected(false);
        jCheckBox_ga_no_jaun.setSelected(false);
        jCheckBox_ga_gcs.setSelected(false);
        jComboBox_ga_gcs_e.setSelectedIndex(0);
        jComboBox_ga_gcs_m.setSelectedIndex(0);
        jComboBox_ga_gcs_v.setSelectedIndex(0);
        jCheckBox_ga_dehy_no.setSelected(false);
        jCheckBox_ga_dehy_mod.setSelected(false);
        jCheckBox_ga_dehy_mild.setSelected(false);
        jCheckBox_ga_dehy_ser.setSelected(false);
        jCheckBox_ga_look.setSelected(false);
        jCheckBox_ga_feb.setSelected(false);
        jCheckBox_ga_dys.setSelected(false);
        jCheckBox_ga_tach.setSelected(false);
        jTextArea_ga_other.setText("");
        jCheckBoxHeent_normal.setSelected(false);
        jCheckBoxHeent_pale.setSelected(false);        
        jCheckBoxHeent_not_p.setSelected(false);
        jCheckBoxHeent_inje_p.setSelected(false);
        jCheckBoxHeent_not_inj.setSelected(false);
        jCheckBoxHeent_icte_s.setSelected(false);
        jCheckBoxHeent_no_ict.setSelected(false);
        jCheckBoxHeent_cerv_p.setSelected(false);
        jCheckBoxHeent_cerv_n.setSelected(false);
        jCheckBoxHeent_ton_nor.setSelected(false);
        jCheckBoxHeent_ton_en.setSelected(false);
        jCheckBoxHeent_ton_ex.setSelected(false);
        jTextFieldTonsils_others.setText("");
        jCheckBoxHeent_eye_p.setSelected(false);
        jRadioButtonHeent_eye_p_l.setSelected(false);
        jRadioButtonHeent_eye_p_r.setSelected(false);
        jRadioButtonHeent_eye_p_b.setSelected(false);
        jCheckBoxHeent_eye_i.setSelected(false);
        jRadioButtonHeent_eye_i_l.setSelected(false);
        jRadioButtonHeent_eye_i_r.setSelected(false);
        jRadioButtonHeent_eye_p_b.setSelected(false);
        jCheckBoxHeent_eye_o.setSelected(false);
        jRadioButtonHeent_eye_o_l.setSelected(false);
        jRadioButtonHeent_eye_o_r.setSelected(false);
        jRadioButtonHeent_eye_o_b.setSelected(false);
        jCheckBoxHeent_eye_h.setSelected(false);
        jRadioButtonHeent_eye_h_l.setSelected(false);
        jRadioButtonHeent_eye_h_r.setSelected(false);
        jRadioButtonHeent_eye_h_b.setSelected(false);
        jCheckBoxHeent_thyr_nor.setSelected(false);
        jCheckBoxHeent_thyr_e.setSelected(false);        
        jTextFieldThyroids_others.setText("");
        jCheckBoxHeart_requ.setSelected(false);
        jCheckBoxHeart_gall.setSelected(false);
        jCheckBoxHeart_irre.setSelected(false);
        jCheckBoxHeart_murm.setSelected(false);
        jCheckBoxHeart_no_murm.setSelected(false);
        jRadioButtonHeart_mur_syst.setSelected(false);
        jRadioButtonHeart_mur_dias.setSelected(false);
        jComboBoxHeart_sys_grad.setSelectedIndex(0);
        jComboBoxHeart_sys_area.setSelectedIndex(0);
        jComboBoxHeart_dias_grad.setSelectedIndex(0);
        jComboBoxHeart_dias_area.setSelectedIndex(0);
        jCheckBoxHeart_no_murm.setSelected(false);
        jTextFieldLung_others.setText("");
        jCheckBoxLung_clear.setSelected(false);
        jCheckBoxLung_whee.setSelected(false);
        jRadioButtonLung_whee_l.setSelected(false);
        jRadioButtonLung_whee_r.setSelected(false);
        jRadioButtonLung_whee_b.setSelected(false);
        jCheckBoxLung_rhon.setSelected(false);
        jRadioButtonLung_rhon_l.setSelected(false);
        jRadioButtonLung_rhon_r.setSelected(false);
        jRadioButtonLung_rhon_b.setSelected(false);
        jCheckBoxLung_fine.setSelected(false);
        jRadioButtonLung_fine_l.setSelected(false);
        jRadioButtonLung_fine_r.setSelected(false);
        jRadioButtonLung_fine_b.setSelected(false);
        jCheckBoxLung_coar.setSelected(false);
        jRadioButtonLung_coar_l.setSelected(false);
        jRadioButtonLung_coar_r.setSelected(false);
        jRadioButtonLung_coar_b.setSelected(false);
        jCheckBoxAbdomen_nor.setSelected(false);        
        jCheckBoxAbdomen_dis.setSelected(false);
        jCheckBoxAbdomen_sca.setSelected(false);
        jCheckBoxAbdomen_bowe_nor.setSelected(false);
        jCheckBoxAbdomen_bowe_inc.setSelected(false);
        jCheckBoxAbdomen_bowe_dec.setSelected(false);
        jCheckBoxAbdomen_soft.setSelected(false);
        jCheckBoxAbdomen_gua.setSelected(false);
        jCheckBoxAbdomen_rig.setSelected(false);
        jCheckBoxAbdomen_ten.setSelected(false);
        jTextFieldAbdomen_tend_dec.setText("");
        jTextFieldAbdomen_pal_dec.setText("");
        jCheckBoxAbdomen_regu.setSelected(false);
        jCheckBoxAbdomen_dark.setSelected(false);
        jCheckBoxAbdomen_clear.setSelected(false);
        jCheckBoxSkin_nor.setSelected(false);
        jCheckBoxSkin_bull.setSelected(false);
        jCheckBoxSkin_tine.setSelected(false);
        jCheckBoxSkin_ecze.setSelected(false);
        jCheckBoxSkin_exfo.setSelected(false);
        jCheckBoxSkin_puts.setSelected(false);
        jCheckBoxSkin_scal.setSelected(false);
        jTextFieldSkin_oth.setText("");
        jCheckBoxExtreme_norm.setSelected(false);
        jCheckBoxExtreme_pitt.setSelected(false);
        jCheckBoxExtreme_non_p.setSelected(false);
        jCheckBoxExtreme_ten.setSelected(false);
        jTextFieldExtreme_ten_dec.setText("");
        jTextFieldExtremity_oth.setText("");
        jCheckBoxNeuro_gros.setSelected(false);
        jCheckBoxNeuro_up_l.setSelected(false);
        jComboBoxNeuro_grad_u_l.setSelectedIndex(0);
        jCheckBoxNeuro_up_r.setSelected(false);
        jComboBoxNeuro_grad_u_r.setSelectedIndex(0);
        jCheckBoxNeuro_low_l.setSelected(false);
        jComboBoxNeuro_grad_l_l.setSelectedIndex(0);
        jCheckBoxNeuro_low_r.setSelected(false);
        jComboBoxNeuro_grad_l_r.setSelectedIndex(0);
        jCheckBoxNeuro_cn.setSelected(false);
        jComboBoxNeuro_cn.setSelectedIndex(0);
        jRadioButtonNeuro_cn_inta.setSelected(false);
        jRadioButtonNeuro_cn_abno.setSelected(false);
        jCheckBoxNeuro_atax.setSelected(false);
        jRadioButtonNeuro_cn_inta1.setSelected(false);
        jRadioButtonNeuro_cn_imp1.setSelected(false);
        jCheckBoxNeuro_ftn.setSelected(false);
        jRadioButtonNeuro_cn_inta2.setSelected(false);
        jRadioButtonNeuro_cn_imp2.setSelected(false);
        jCheckBoxNeuro_refl_l.setSelected(false);
        jComboBoxNeuro_refl_l.setSelectedIndex(0);
        jCheckBoxNeuro_refl_r.setSelected(false);
        jComboBoxNeuro_refl_r.setSelectedIndex(0);
        jTextFieldNeuro_oth.setText("");
    }
    public void setPhysicalExamV(Vector v)
    {
        PhysicalExamNan pen = new PhysicalExamNan();
        pen.setPhysicalExamV(v);
        setPhysicalExamNan(pen);
    }  
       
    private void setPhysicalExamNan(PhysicalExamNan pen)
    {
        thePhysicalExamNan = pen;
        ///////////////////////////////////////////////////////////////////////////////
        Gutil.setText(	        thePhysicalExamNan.	ga_normal	,	jCheckBox_ga_normal	);
        Gutil.setText(	        thePhysicalExamNan.	ga_pale	,	jCheckBox_ga_pale	);
        Gutil.setText(	        thePhysicalExamNan.	ga_not_pale	,	jCheckBox_ga_not_pale	);
        Gutil.setText(	        thePhysicalExamNan.	ga_jaun	,	jCheckBox_ga_jaun	);
        Gutil.setText(	        thePhysicalExamNan.	ga_no_jaun	,	jCheckBox_ga_no_jaun	);
        Gutil.setText(	        thePhysicalExamNan.	ga_gcs	,	jCheckBox_ga_gcs	);
        Gutil.setText(	        thePhysicalExamNan.	ga_gcs_e	,	jComboBox_ga_gcs_e	);
        Gutil.setText(	        thePhysicalExamNan.	ga_gcs_m	,	jComboBox_ga_gcs_m	);
        Gutil.setText(	        thePhysicalExamNan.	ga_gcs_v	,	jComboBox_ga_gcs_v	);
        Gutil.setText(	        thePhysicalExamNan.	ga_dehy_no	,	jCheckBox_ga_dehy_no	);
        Gutil.setText(	        thePhysicalExamNan.	ga_dehy_mod	,	jCheckBox_ga_dehy_mod	);
        Gutil.setText(	        thePhysicalExamNan.	ga_dehy_mild	,	jCheckBox_ga_dehy_mild	);
        Gutil.setText(	        thePhysicalExamNan.	ga_dehy_ser	,	jCheckBox_ga_dehy_ser	);
        Gutil.setText(	        thePhysicalExamNan.	ga_look	,	jCheckBox_ga_look	);
        Gutil.setText(	        thePhysicalExamNan.	ga_feb	,	jCheckBox_ga_feb	);
        Gutil.setText(	        thePhysicalExamNan.	ga_dys	,	jCheckBox_ga_dys	);
        Gutil.setText(	        thePhysicalExamNan.	ga_tach	,	jCheckBox_ga_tach	);
        Gutil.setText(	        thePhysicalExamNan.	ga_other	,	jTextArea_ga_other	);
        Gutil.setText(          thePhysicalExamNan.     hee_normal	,	jCheckBoxHeent_normal	);
        Gutil.setText(          thePhysicalExamNan.	hee_pale	,	jCheckBoxHeent_pale	);        
        Gutil.setText(          thePhysicalExamNan.	hee_not_pa	,	jCheckBoxHeent_not_p	);
        Gutil.setText(          thePhysicalExamNan.	hee_inj 	,	jCheckBoxHeent_inje_p	);
        Gutil.setText(          thePhysicalExamNan.	hee_not_inj	,	jCheckBoxHeent_not_inj	);
        Gutil.setText(          thePhysicalExamNan.	hee_ict         ,	jCheckBoxHeent_icte_s	);
        Gutil.setText(          thePhysicalExamNan.	hee_no_ict	,	jCheckBoxHeent_no_ict	);
        Gutil.setText(          thePhysicalExamNan.	hee_cer_nor	,	jCheckBoxHeent_cerv_p	);
        Gutil.setText(          thePhysicalExamNan.	hee_cer_pal	,	jCheckBoxHeent_cerv_n	);
        Gutil.setText(          thePhysicalExamNan.	hee_ton_nor	,	jCheckBoxHeent_ton_nor	);
        Gutil.setText(          thePhysicalExamNan.	hee_ton_en	,	jCheckBoxHeent_ton_en	);
        Gutil.setText(          thePhysicalExamNan.	hee_ton_ex	,	jCheckBoxHeent_ton_ex	);
        Gutil.setText(          thePhysicalExamNan.	hee_ton_oth	,	jTextFieldTonsils_others);
        Gutil.setText(          thePhysicalExamNan.	hee_eye_pter	,	jCheckBoxHeent_eye_p	);
        Gutil.setText(          thePhysicalExamNan.	hee_eye_pter_pos,jRadioButtonHeent_eye_p_l,jRadioButtonHeent_eye_p_r,jRadioButtonHeent_eye_p_b);
        Gutil.setText(          thePhysicalExamNan.	hee_eye_inj	,	jCheckBoxHeent_eye_i	);
        Gutil.setText(          thePhysicalExamNan.	hee_eye_inj_pos	,jRadioButtonHeent_eye_i_l,jRadioButtonHeent_eye_i_r,jRadioButtonHeent_eye_i_b);
        Gutil.setText(          thePhysicalExamNan.	hee_eye_opa	,	jCheckBoxHeent_eye_o	);
        Gutil.setText(          thePhysicalExamNan.	hee_eye_opa_pos	,jRadioButtonHeent_eye_o_l,jRadioButtonHeent_eye_o_r,jRadioButtonHeent_eye_o_b);
        Gutil.setText(          thePhysicalExamNan.	hee_eye_her	,	jCheckBoxHeent_eye_h	);
        Gutil.setText(          thePhysicalExamNan.	hee_eye_her_pos	,jRadioButtonHeent_eye_h_l,jRadioButtonHeent_eye_h_r,jRadioButtonHeent_eye_h_b);
        Gutil.setText(          thePhysicalExamNan.	hee_thyr_nor	,	jCheckBoxHeent_thyr_nor	);
        Gutil.setText(          thePhysicalExamNan.	hee_thyr_en	,	jCheckBoxHeent_thyr_e	);        
        Gutil.setText(          thePhysicalExamNan.	hee_thyr_oth	,	jTextFieldThyroids_others);
        Gutil.setText(          thePhysicalExamNan.	hea_re	,	jCheckBoxHeart_requ	);
        Gutil.setText(          thePhysicalExamNan.	hea_gal	,	jCheckBoxHeart_gall	);
        Gutil.setText(          thePhysicalExamNan.	hea_irr	,	jCheckBoxHeart_irre	);
        Gutil.setText(          thePhysicalExamNan.	hea_mur	,	jCheckBoxHeart_murm	);
        Gutil.setText(          thePhysicalExamNan.	hea_mur_sys	,jRadioButtonHeart_mur_syst	);
        Gutil.setText(          thePhysicalExamNan.	hea_mur_dia	,jRadioButtonHeart_mur_dias	);
        Gutil.setText(          thePhysicalExamNan.	hea_mur_sys_gra	,	jComboBoxHeart_sys_grad	);
        Gutil.setText(          thePhysicalExamNan.	hea_mur_sys_area	,	jComboBoxHeart_sys_area	);
        Gutil.setText(          thePhysicalExamNan.	hea_mur_dias_gra	,	jComboBoxHeart_dias_grad	);
        Gutil.setText(          thePhysicalExamNan.	hea_mur_dias_area	,	jComboBoxHeart_dias_area	);
        Gutil.setText(          thePhysicalExamNan.	hea_no_mur	,	jCheckBoxHeart_no_murm	);
        Gutil.setText(          thePhysicalExamNan.	lung_cle	,	jCheckBoxLung_clear	);
        Gutil.setText(          thePhysicalExamNan.	lung_whe	,	jCheckBoxLung_whee	);
        Gutil.setText(          thePhysicalExamNan.	lung_whe_pos	,jRadioButtonLung_whee_l,jRadioButtonLung_whee_r,jRadioButtonLung_whee_b);
        Gutil.setText(          thePhysicalExamNan.	lung_rho	,	jCheckBoxLung_rhon	);
        Gutil.setText(          thePhysicalExamNan.	lung_rho_pos	,jRadioButtonLung_rhon_l,jRadioButtonLung_rhon_r,jRadioButtonLung_rhon_b);
        Gutil.setText(          thePhysicalExamNan.	lung_cre_fine	,	jCheckBoxLung_fine	);
        Gutil.setText(          thePhysicalExamNan.	lung_cre_fine_pos	,jRadioButtonLung_fine_l,jRadioButtonLung_fine_r,jRadioButtonLung_fine_b);
        Gutil.setText(          thePhysicalExamNan.	lung_cre_coa	,	jCheckBoxLung_coar	);
        Gutil.setText(          thePhysicalExamNan.	lung_cre_coa_pos	,jRadioButtonLung_coar_l,jRadioButtonLung_coar_r,jRadioButtonLung_coar_b);
        Gutil.setText(          thePhysicalExamNan.	lung_oth         ,	jTextFieldLung_others	);
        Gutil.setText(          thePhysicalExamNan.	abd_con_nor	,	jCheckBoxAbdomen_nor	);        
        Gutil.setText(          thePhysicalExamNan.	abd_con_dis	,	jCheckBoxAbdomen_dis	);
        Gutil.setText(          thePhysicalExamNan.	abd_con_sca	,	jCheckBoxAbdomen_sca	);
        Gutil.setText(          thePhysicalExamNan.	abd_bow_nor	,	jCheckBoxAbdomen_bowe_nor	);
        Gutil.setText(          thePhysicalExamNan.	abd_bow_inc	,	jCheckBoxAbdomen_bowe_inc	);
        Gutil.setText(          thePhysicalExamNan.	abd_bow_dec	,	jCheckBoxAbdomen_bowe_dec	);
        Gutil.setText(          thePhysicalExamNan.	abd_pal_soft	,	jCheckBoxAbdomen_soft	);
        Gutil.setText(          thePhysicalExamNan.	abd_pal_gua	,	jCheckBoxAbdomen_gua	);
        Gutil.setText(          thePhysicalExamNan.	abd_pal_rig	,	jCheckBoxAbdomen_rig	);
        Gutil.setText(          thePhysicalExamNan.	abd_pal_ten	,	jCheckBoxAbdomen_ten	);
        Gutil.setText(          thePhysicalExamNan.	abd_pal_ten_area	,	jTextFieldAbdomen_tend_dec	);
        Gutil.setText(          thePhysicalExamNan.	abd_pal_oth	,	jTextFieldAbdomen_pal_dec	);
        Gutil.setText(          thePhysicalExamNan.	abd_kok_reg	,	jCheckBoxAbdomen_regu	);
        Gutil.setText(          thePhysicalExamNan.	abd_kok_dark	,	jCheckBoxAbdomen_dark	);
        Gutil.setText(          thePhysicalExamNan.	abd_kok_clr	,	jCheckBoxAbdomen_clear	);
        Gutil.setText(          thePhysicalExamNan.	skin_nor	,	jCheckBoxSkin_nor	);
        Gutil.setText(          thePhysicalExamNan.	skin_bul	,	jCheckBoxSkin_bull	);
        Gutil.setText(          thePhysicalExamNan.	skin_tin	,	jCheckBoxSkin_tine	);
        Gutil.setText(          thePhysicalExamNan.	skin_ecz	,	jCheckBoxSkin_ecze	);
        Gutil.setText(          thePhysicalExamNan.	skin_exf	,	jCheckBoxSkin_exfo	);
        Gutil.setText(          thePhysicalExamNan.	skin_pus	,	jCheckBoxSkin_puts	);
        Gutil.setText(          thePhysicalExamNan.	skin_sca	,	jCheckBoxSkin_scal	);
        Gutil.setText(          thePhysicalExamNan.	skin_oth	,	jTextFieldSkin_oth	);
        Gutil.setText(          thePhysicalExamNan.	ext_nor	,	jCheckBoxExtreme_norm	);
        Gutil.setText(          thePhysicalExamNan.	ext_pit	,	jCheckBoxExtreme_pitt	);
        Gutil.setText(          thePhysicalExamNan.	ext_non_pit	,	jCheckBoxExtreme_non_p	);
        Gutil.setText(          thePhysicalExamNan.	ext_ten	,	jCheckBoxExtreme_ten	);
        Gutil.setText(          thePhysicalExamNan.	ext_ten_dec	,	jTextFieldExtreme_ten_dec	);
        Gutil.setText(          thePhysicalExamNan.	ext_oth	,	jTextFieldExtremity_oth	);
        Gutil.setText(          thePhysicalExamNan.	neu_gro	,	jCheckBoxNeuro_gros	);
        Gutil.setText(          thePhysicalExamNan.	neu_mot_up_l	,	jCheckBoxNeuro_up_l	);
        Gutil.setText(          thePhysicalExamNan.	neu_mot_up_l_gra	,	jComboBoxNeuro_grad_u_l	);
        Gutil.setText(          thePhysicalExamNan.	neu_mot_up_r	,	jCheckBoxNeuro_up_r	);
        Gutil.setText(          thePhysicalExamNan.	neu_mot_up_r_gra	,	jComboBoxNeuro_grad_u_r	);
        Gutil.setText(          thePhysicalExamNan.	neu_mot_low_l	,	jCheckBoxNeuro_low_l	);
        Gutil.setText(          thePhysicalExamNan.	neu_mot_low_l_gra	,	jComboBoxNeuro_grad_l_l	);
        Gutil.setText(          thePhysicalExamNan.	neu_mot_low_r	,	jCheckBoxNeuro_low_r	);
        Gutil.setText(          thePhysicalExamNan.	neu_mot_low_r_gra	,	jComboBoxNeuro_grad_l_r	);
        Gutil.setText(          thePhysicalExamNan.	neu_cn	,	jCheckBoxNeuro_cn	);
        Gutil.setText(          thePhysicalExamNan.	neu_cn_num	,	jComboBoxNeuro_cn	);
        Gutil.setText(          thePhysicalExamNan.	neu_cn_data	,jRadioButtonNeuro_cn_inta,jRadioButtonNeuro_cn_abno	);
        Gutil.setText(          thePhysicalExamNan.	neu_cer_ata	,	jCheckBoxNeuro_atax	);
        Gutil.setText(          thePhysicalExamNan.	neu_cer_ata_data	,jRadioButtonNeuro_cn_inta1,jRadioButtonNeuro_cn_imp1	);
        Gutil.setText(          thePhysicalExamNan.	neu_cer_ftn	,	jCheckBoxNeuro_ftn	);
        Gutil.setText(          thePhysicalExamNan.	neu_cer_ftn_data	,jRadioButtonNeuro_cn_inta2,jRadioButtonNeuro_cn_imp2	);
        Gutil.setText(          thePhysicalExamNan.	neu_ref_l	,	jCheckBoxNeuro_refl_l	);
        Gutil.setText(          thePhysicalExamNan.	neu_ref_l_vol	,	jComboBoxNeuro_refl_l	);
        Gutil.setText(          thePhysicalExamNan.	neu_ref_r	,	jCheckBoxNeuro_refl_r	);
        Gutil.setText(          thePhysicalExamNan.	neu_ref_r_vol	,	jComboBoxNeuro_refl_r	);
        Gutil.setText(          thePhysicalExamNan.	neu_oth	,	jTextFieldNeuro_oth	);
        Gutil.setText(          thePhysicalExamNan.     others	,	jTextAreaOther	);

    }
    public Vector getPhysicalExamV()
    {
        PhysicalExamNan pen = getPhysicalExamNan();
        return pen.getPhysicalExamV();
    }
    private PhysicalExamNan getPhysicalExamNan()
    {
        ///////////////////////////////////////////////////////////////////////////////thePhysicalExamNan.	ga_normal	 = Gutil.getText(	jCheckBox_ga_normal	);
        thePhysicalExamNan.	ga_normal	 = Gutil.getText(	jCheckBox_ga_normal	);
        thePhysicalExamNan.	ga_pale	 = Gutil.getText(	jCheckBox_ga_pale	);        
        thePhysicalExamNan.	ga_not_pale	 = Gutil.getText(	jCheckBox_ga_not_pale	);
        thePhysicalExamNan.	ga_jaun	 = Gutil.getText(	jCheckBox_ga_jaun	);
        thePhysicalExamNan.	ga_no_jaun	 = Gutil.getText(	jCheckBox_ga_no_jaun	);
        thePhysicalExamNan.	ga_gcs	 = Gutil.getText(	jCheckBox_ga_gcs	);
        thePhysicalExamNan.	ga_gcs_e	 = Gutil.getText(	jComboBox_ga_gcs_e	);
        thePhysicalExamNan.	ga_gcs_m	 = Gutil.getText(	jComboBox_ga_gcs_m	);
        thePhysicalExamNan.	ga_gcs_v	 = Gutil.getText(	jComboBox_ga_gcs_v	);
        thePhysicalExamNan.	ga_dehy_no	 = Gutil.getText(	jCheckBox_ga_dehy_no	);
        thePhysicalExamNan.	ga_dehy_mod	 = Gutil.getText(	jCheckBox_ga_dehy_mod	);
        thePhysicalExamNan.	ga_dehy_mild	 = Gutil.getText(	jCheckBox_ga_dehy_mild	);
        thePhysicalExamNan.	ga_dehy_ser	 = Gutil.getText(	jCheckBox_ga_dehy_ser	);
        thePhysicalExamNan.	ga_look	 = Gutil.getText(	jCheckBox_ga_look	);
        thePhysicalExamNan.	ga_feb	 = Gutil.getText(	jCheckBox_ga_feb	);
        thePhysicalExamNan.	ga_dys	 = Gutil.getText(	jCheckBox_ga_dys	);
        thePhysicalExamNan.	ga_tach	 = Gutil.getText(	jCheckBox_ga_tach	);
        thePhysicalExamNan.	ga_other	 = Gutil.getText(	jTextArea_ga_other	);
        thePhysicalExamNan.	hee_normal	 = Gutil.getText(	jCheckBoxHeent_normal	);
        thePhysicalExamNan.	hee_pale	 = Gutil.getText(	jCheckBoxHeent_pale	);        
        thePhysicalExamNan.	hee_not_pa	 = Gutil.getText(	jCheckBoxHeent_not_p	);
        thePhysicalExamNan.	hee_inj 	 = Gutil.getText(	jCheckBoxHeent_inje_p	);
        thePhysicalExamNan.	hee_not_inj	 = Gutil.getText(	jCheckBoxHeent_not_inj	);
        thePhysicalExamNan.	hee_ict          = Gutil.getText(	jCheckBoxHeent_icte_s	);
        thePhysicalExamNan.	hee_no_ict	 = Gutil.getText(	jCheckBoxHeent_no_ict	);
        thePhysicalExamNan.	hee_cer_nor	 = Gutil.getText(	jCheckBoxHeent_cerv_p	);
        thePhysicalExamNan.	hee_cer_pal	 = Gutil.getText(	jCheckBoxHeent_cerv_n	);
        thePhysicalExamNan.	hee_ton_nor	 = Gutil.getText(	jCheckBoxHeent_ton_nor	);
        thePhysicalExamNan.	hee_ton_en	 = Gutil.getText(	jCheckBoxHeent_ton_en	);
        thePhysicalExamNan.	hee_ton_ex	 = Gutil.getText(	jCheckBoxHeent_ton_ex	);
        thePhysicalExamNan.	hee_ton_oth	 = Gutil.getText(	jTextFieldTonsils_others);
        thePhysicalExamNan.	hee_eye_pter	 = Gutil.getText(	jCheckBoxHeent_eye_p	);
        thePhysicalExamNan.	hee_eye_pter_pos = Gutil.getText(jRadioButtonHeent_eye_p_l,jRadioButtonHeent_eye_p_r,jRadioButtonHeent_eye_p_b);
        thePhysicalExamNan.	hee_eye_inj	 = Gutil.getText(	jCheckBoxHeent_eye_i	);
        thePhysicalExamNan.	hee_eye_inj_pos	 = Gutil.getText(jRadioButtonHeent_eye_i_l, jRadioButtonHeent_eye_i_r,jRadioButtonHeent_eye_i_b);
        thePhysicalExamNan.	hee_eye_opa	 = Gutil.getText(	jCheckBoxHeent_eye_o	);
        thePhysicalExamNan.	hee_eye_opa_pos	 = Gutil.getText(jRadioButtonHeent_eye_o_l,jRadioButtonHeent_eye_o_r,jRadioButtonHeent_eye_o_b);
        thePhysicalExamNan.	hee_eye_her	 = Gutil.getText(	jCheckBoxHeent_eye_h	);
        thePhysicalExamNan.	hee_eye_her_pos	 = Gutil.getText(jRadioButtonHeent_eye_h_l,jRadioButtonHeent_eye_h_r,jRadioButtonHeent_eye_h_b);
        thePhysicalExamNan.	hee_thyr_nor	 = Gutil.getText(	jCheckBoxHeent_thyr_nor	);
        thePhysicalExamNan.	hee_thyr_en	 = Gutil.getText(	jCheckBoxHeent_thyr_e	);        
        thePhysicalExamNan.	hee_thyr_oth	 = Gutil.getText(	jTextFieldThyroids_others);
        thePhysicalExamNan.	hea_re	 = Gutil.getText(	jCheckBoxHeart_requ	);
        thePhysicalExamNan.	hea_gal	 = Gutil.getText(	jCheckBoxHeart_gall	);
        thePhysicalExamNan.	hea_irr	 = Gutil.getText(	jCheckBoxHeart_irre	);
        thePhysicalExamNan.	hea_mur	 = Gutil.getText(	jCheckBoxHeart_murm	);
        thePhysicalExamNan.	hea_mur_sys = Gutil.getText(	jRadioButtonHeart_mur_syst	);
        thePhysicalExamNan.	hea_mur_dia	 = Gutil.getText(	jRadioButtonHeart_mur_dias	);
        thePhysicalExamNan.	hea_mur_sys_gra = Gutil.getText(	jComboBoxHeart_sys_grad	);
        thePhysicalExamNan.	hea_mur_sys_area = Gutil.getText(	jComboBoxHeart_sys_area	);
        thePhysicalExamNan.	hea_mur_dias_gra = Gutil.getText(	jComboBoxHeart_dias_grad	);
        thePhysicalExamNan.	hea_mur_dias_area = Gutil.getText(	jComboBoxHeart_dias_area	);
        thePhysicalExamNan.	hea_no_mur	 = Gutil.getText(	jCheckBoxHeart_no_murm	);
        //thePhysicalExamNan.	hea_oth	 = Gutil.getText(	jTextFieldHeart_others	);
        thePhysicalExamNan.	lung_cle	 = Gutil.getText(	jCheckBoxLung_clear	);
        thePhysicalExamNan.	lung_whe	 = Gutil.getText(	jCheckBoxLung_whee	);
        thePhysicalExamNan.	lung_whe_pos	 = Gutil.getText(jRadioButtonLung_whee_l, jRadioButtonLung_whee_r,jRadioButtonLung_whee_b);
        thePhysicalExamNan.	lung_rho	 = Gutil.getText(	jCheckBoxLung_rhon	);
        thePhysicalExamNan.	lung_rho_pos	 = Gutil.getText(jRadioButtonLung_rhon_l,jRadioButtonLung_rhon_r,jRadioButtonLung_rhon_b);
        thePhysicalExamNan.	lung_cre_fine	 = Gutil.getText(	jCheckBoxLung_fine	);
        thePhysicalExamNan.	lung_cre_fine_pos= Gutil.getText(jRadioButtonLung_fine_l,jRadioButtonLung_fine_r,jRadioButtonLung_fine_b);
        thePhysicalExamNan.	lung_cre_coa	 = Gutil.getText(	jCheckBoxLung_coar	);
        thePhysicalExamNan.	lung_cre_coa_pos = Gutil.getText(jRadioButtonLung_coar_l,jRadioButtonLung_coar_r,jRadioButtonLung_coar_b);
        thePhysicalExamNan.	lung_oth	 = Gutil.getText(	jTextFieldLung_others	);
        thePhysicalExamNan.	abd_con_nor	 = Gutil.getText(	jCheckBoxAbdomen_nor	);        
        thePhysicalExamNan.	abd_con_dis	 = Gutil.getText(	jCheckBoxAbdomen_dis	);
        thePhysicalExamNan.	abd_con_sca	 = Gutil.getText(	jCheckBoxAbdomen_sca	);
        thePhysicalExamNan.	abd_bow_nor	 = Gutil.getText(	jCheckBoxAbdomen_bowe_nor	);
        thePhysicalExamNan.	abd_bow_inc	 = Gutil.getText(	jCheckBoxAbdomen_bowe_inc	);
        thePhysicalExamNan.	abd_bow_dec	 = Gutil.getText(	jCheckBoxAbdomen_bowe_dec	);
        thePhysicalExamNan.	abd_pal_soft	 = Gutil.getText(	jCheckBoxAbdomen_soft	);
        thePhysicalExamNan.	abd_pal_gua	 = Gutil.getText(	jCheckBoxAbdomen_gua	);
        thePhysicalExamNan.	abd_pal_rig	 = Gutil.getText(	jCheckBoxAbdomen_rig	);
        thePhysicalExamNan.	abd_pal_ten	 = Gutil.getText(	jCheckBoxAbdomen_ten	);
        thePhysicalExamNan.	abd_pal_ten_area = Gutil.getText(	jTextFieldAbdomen_tend_dec	);
        thePhysicalExamNan.	abd_pal_oth	 = Gutil.getText(	jTextFieldAbdomen_pal_dec	);
        thePhysicalExamNan.	abd_kok_reg	 = Gutil.getText(	jCheckBoxAbdomen_regu	);
        thePhysicalExamNan.	abd_kok_dark	 = Gutil.getText(	jCheckBoxAbdomen_dark	);
        thePhysicalExamNan.	abd_kok_clr	 = Gutil.getText(	jCheckBoxAbdomen_clear	);
        thePhysicalExamNan.	skin_nor	 = Gutil.getText(	jCheckBoxSkin_nor	);
        thePhysicalExamNan.	skin_bul	 = Gutil.getText(	jCheckBoxSkin_bull	);
        thePhysicalExamNan.	skin_tin	 = Gutil.getText(	jCheckBoxSkin_tine	);
        thePhysicalExamNan.	skin_ecz	 = Gutil.getText(	jCheckBoxSkin_ecze	);
        thePhysicalExamNan.	skin_exf	 = Gutil.getText(	jCheckBoxSkin_exfo	);
        thePhysicalExamNan.	skin_pus	 = Gutil.getText(	jCheckBoxSkin_puts	);
        thePhysicalExamNan.	skin_sca	 = Gutil.getText(	jCheckBoxSkin_scal	);
        thePhysicalExamNan.	skin_oth	 = Gutil.getText(	jTextFieldSkin_oth	);
        thePhysicalExamNan.	ext_nor	 = Gutil.getText(	jCheckBoxExtreme_norm	);
        thePhysicalExamNan.	ext_pit	 = Gutil.getText(	jCheckBoxExtreme_pitt	);
        thePhysicalExamNan.	ext_non_pit	 = Gutil.getText(	jCheckBoxExtreme_non_p	);
        thePhysicalExamNan.	ext_ten	 = Gutil.getText(	jCheckBoxExtreme_ten	);
        thePhysicalExamNan.	ext_ten_dec	 = Gutil.getText(	jTextFieldExtreme_ten_dec	);
        thePhysicalExamNan.	ext_oth	 = Gutil.getText(	jTextFieldExtremity_oth	);
        thePhysicalExamNan.	neu_gro	 = Gutil.getText(	jCheckBoxNeuro_gros	);
        thePhysicalExamNan.	neu_mot_up_l	 = Gutil.getText(	jCheckBoxNeuro_up_l	);
        thePhysicalExamNan.	neu_mot_up_l_gra	 = Gutil.getText(	jComboBoxNeuro_grad_u_l	);
        thePhysicalExamNan.	neu_mot_up_r	 = Gutil.getText(	jCheckBoxNeuro_up_r	);
        thePhysicalExamNan.	neu_mot_up_r_gra	 = Gutil.getText(	jComboBoxNeuro_grad_u_r	);
        thePhysicalExamNan.	neu_mot_low_l	 = Gutil.getText(	jCheckBoxNeuro_low_l	);
        thePhysicalExamNan.	neu_mot_low_l_gra	 = Gutil.getText(	jComboBoxNeuro_grad_l_l	);
        thePhysicalExamNan.	neu_mot_low_r	 = Gutil.getText(	jCheckBoxNeuro_low_r	);
        thePhysicalExamNan.	neu_mot_low_r_gra	 = Gutil.getText(	jComboBoxNeuro_grad_l_r	);
        thePhysicalExamNan.	neu_cn	 = Gutil.getText(	jCheckBoxNeuro_cn	);
        thePhysicalExamNan.	neu_cn_num	 = Gutil.getText(	jComboBoxNeuro_cn	);
        thePhysicalExamNan.	neu_cn_data	 = Gutil.getText(jRadioButtonNeuro_cn_inta,jRadioButtonNeuro_cn_abno);
        thePhysicalExamNan.	neu_cer_ata = Gutil.getText(jCheckBoxNeuro_atax	);
        thePhysicalExamNan.	neu_cer_ata_data = Gutil.getText(jRadioButtonNeuro_cn_inta1,jRadioButtonNeuro_cn_imp1);
        thePhysicalExamNan.	neu_cer_ftn = Gutil.getText(jCheckBoxNeuro_ftn	);
        thePhysicalExamNan.	neu_cer_ftn_data = Gutil.getText(jRadioButtonNeuro_cn_inta2,jRadioButtonNeuro_cn_imp1);
        thePhysicalExamNan.	neu_ref_l	 = Gutil.getText(	jCheckBoxNeuro_refl_l	);
        thePhysicalExamNan.	neu_ref_l_vol	 = Gutil.getText(	jComboBoxNeuro_refl_l	);
        thePhysicalExamNan.	neu_ref_r	 = Gutil.getText(	jCheckBoxNeuro_refl_r	);
        thePhysicalExamNan.	neu_ref_r_vol	 = Gutil.getText(	jComboBoxNeuro_refl_r	);
        thePhysicalExamNan.	neu_oth	 = Gutil.getText(	jTextFieldNeuro_oth	);
        thePhysicalExamNan.	others	 = Gutil.getText(	jTextAreaOther	);

        return thePhysicalExamNan;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroupEye_her;
    private javax.swing.ButtonGroup buttonGroupEye_inj;
    private javax.swing.ButtonGroup buttonGroupEye_opa;
    private javax.swing.ButtonGroup buttonGroupEye_pter;
    private javax.swing.ButtonGroup buttonGroupLung_caus;
    private javax.swing.ButtonGroup buttonGroupLung_coar;
    private javax.swing.ButtonGroup buttonGroupLung_fine;
    private javax.swing.ButtonGroup buttonGroupLung_rhon;
    private javax.swing.ButtonGroup buttonGroupLung_whee;
    private javax.swing.ButtonGroup buttonGroupMur_type;
    private javax.swing.ButtonGroup buttonGroupMurmur;
    private javax.swing.ButtonGroup buttonGroupNeuro_ata;
    private javax.swing.ButtonGroup buttonGroupNeuro_cn;
    private javax.swing.ButtonGroup buttonGroupNeuro_ftn;
    private javax.swing.ButtonGroup ga_juandice;
    private javax.swing.ButtonGroup ga_pale;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonCancel1;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSave1;
    private com.hosv3.gui.component.HCheckBox jCheckBoxAbdomen_bowe_dec;
    private com.hosv3.gui.component.HCheckBox jCheckBoxAbdomen_bowe_inc;
    private com.hosv3.gui.component.HCheckBox jCheckBoxAbdomen_bowe_nor;
    private com.hosv3.gui.component.HCheckBox jCheckBoxAbdomen_clear;
    private com.hosv3.gui.component.HCheckBox jCheckBoxAbdomen_dark;
    private com.hosv3.gui.component.HCheckBox jCheckBoxAbdomen_dis;
    private com.hosv3.gui.component.HCheckBox jCheckBoxAbdomen_gua;
    private com.hosv3.gui.component.HCheckBox jCheckBoxAbdomen_nor;
    private com.hosv3.gui.component.HCheckBox jCheckBoxAbdomen_regu;
    private com.hosv3.gui.component.HCheckBox jCheckBoxAbdomen_rig;
    private com.hosv3.gui.component.HCheckBox jCheckBoxAbdomen_sca;
    private com.hosv3.gui.component.HCheckBox jCheckBoxAbdomen_soft;
    private com.hosv3.gui.component.HCheckBox jCheckBoxAbdomen_ten;
    private com.hosv3.gui.component.HCheckBox jCheckBoxExtreme_non_p;
    private com.hosv3.gui.component.HCheckBox jCheckBoxExtreme_norm;
    private com.hosv3.gui.component.HCheckBox jCheckBoxExtreme_pitt;
    private com.hosv3.gui.component.HCheckBox jCheckBoxExtreme_ten;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeart_gall;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeart_irre;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeart_murm;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeart_no_murm;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeart_requ;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeent_cerv_n;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeent_cerv_p;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeent_eye_h;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeent_eye_i;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeent_eye_o;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeent_eye_p;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeent_icte_s;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeent_inje_p;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeent_no_ict;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeent_normal;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeent_not_inj;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeent_not_p;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeent_pale;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeent_thyr_e;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeent_thyr_nor;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeent_ton_en;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeent_ton_ex;
    private com.hosv3.gui.component.HCheckBox jCheckBoxHeent_ton_nor;
    private com.hosv3.gui.component.HCheckBox jCheckBoxLung_clear;
    private com.hosv3.gui.component.HCheckBox jCheckBoxLung_coar;
    private com.hosv3.gui.component.HCheckBox jCheckBoxLung_fine;
    private com.hosv3.gui.component.HCheckBox jCheckBoxLung_rhon;
    private com.hosv3.gui.component.HCheckBox jCheckBoxLung_whee;
    private com.hosv3.gui.component.HCheckBox jCheckBoxNeuro_atax;
    private com.hosv3.gui.component.HCheckBox jCheckBoxNeuro_cn;
    private com.hosv3.gui.component.HCheckBox jCheckBoxNeuro_ftn;
    private com.hosv3.gui.component.HCheckBox jCheckBoxNeuro_gros;
    private com.hosv3.gui.component.HCheckBox jCheckBoxNeuro_low_l;
    private com.hosv3.gui.component.HCheckBox jCheckBoxNeuro_low_r;
    private com.hosv3.gui.component.HCheckBox jCheckBoxNeuro_refl_l;
    private com.hosv3.gui.component.HCheckBox jCheckBoxNeuro_refl_r;
    private com.hosv3.gui.component.HCheckBox jCheckBoxNeuro_up_l;
    private com.hosv3.gui.component.HCheckBox jCheckBoxNeuro_up_r;
    private com.hosv3.gui.component.HCheckBox jCheckBoxSkin_bull;
    private com.hosv3.gui.component.HCheckBox jCheckBoxSkin_ecze;
    private com.hosv3.gui.component.HCheckBox jCheckBoxSkin_exfo;
    private com.hosv3.gui.component.HCheckBox jCheckBoxSkin_nor;
    private com.hosv3.gui.component.HCheckBox jCheckBoxSkin_puts;
    private com.hosv3.gui.component.HCheckBox jCheckBoxSkin_scal;
    private com.hosv3.gui.component.HCheckBox jCheckBoxSkin_tine;
    private com.hosv3.gui.component.HCheckBox jCheckBox_ga_dehy_mild;
    private com.hosv3.gui.component.HCheckBox jCheckBox_ga_dehy_mod;
    private com.hosv3.gui.component.HCheckBox jCheckBox_ga_dehy_no;
    private com.hosv3.gui.component.HCheckBox jCheckBox_ga_dehy_ser;
    private com.hosv3.gui.component.HCheckBox jCheckBox_ga_dys;
    private com.hosv3.gui.component.HCheckBox jCheckBox_ga_feb;
    private com.hosv3.gui.component.HCheckBox jCheckBox_ga_gcs;
    private com.hosv3.gui.component.HCheckBox jCheckBox_ga_jaun;
    private com.hosv3.gui.component.HCheckBox jCheckBox_ga_look;
    private com.hosv3.gui.component.HCheckBox jCheckBox_ga_no_jaun;
    private com.hosv3.gui.component.HCheckBox jCheckBox_ga_normal;
    private com.hosv3.gui.component.HCheckBox jCheckBox_ga_not_pale;
    private com.hosv3.gui.component.HCheckBox jCheckBox_ga_pale;
    private com.hosv3.gui.component.HCheckBox jCheckBox_ga_tach;
    private javax.swing.JComboBox jComboBoxHeart_dias_area;
    private javax.swing.JComboBox jComboBoxHeart_dias_grad;
    private javax.swing.JComboBox jComboBoxHeart_sys_area;
    private javax.swing.JComboBox jComboBoxHeart_sys_grad;
    private javax.swing.JComboBox jComboBoxNeuro_cn;
    private javax.swing.JComboBox jComboBoxNeuro_grad_l_l;
    private javax.swing.JComboBox jComboBoxNeuro_grad_l_r;
    private javax.swing.JComboBox jComboBoxNeuro_grad_u_l;
    private javax.swing.JComboBox jComboBoxNeuro_grad_u_r;
    private javax.swing.JComboBox jComboBoxNeuro_refl_l;
    private javax.swing.JComboBox jComboBoxNeuro_refl_r;
    private javax.swing.JComboBox jComboBox_ga_gcs_e;
    private javax.swing.JComboBox jComboBox_ga_gcs_m;
    private javax.swing.JComboBox jComboBox_ga_gcs_v;
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
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButtonHeart_mur_dias;
    private javax.swing.JRadioButton jRadioButtonHeart_mur_syst;
    private javax.swing.JRadioButton jRadioButtonHeent_eye_h_b;
    private javax.swing.JRadioButton jRadioButtonHeent_eye_h_l;
    private javax.swing.JRadioButton jRadioButtonHeent_eye_h_r;
    private javax.swing.JRadioButton jRadioButtonHeent_eye_i_b;
    private javax.swing.JRadioButton jRadioButtonHeent_eye_i_l;
    private javax.swing.JRadioButton jRadioButtonHeent_eye_i_r;
    private javax.swing.JRadioButton jRadioButtonHeent_eye_o_b;
    private javax.swing.JRadioButton jRadioButtonHeent_eye_o_l;
    private javax.swing.JRadioButton jRadioButtonHeent_eye_o_r;
    private javax.swing.JRadioButton jRadioButtonHeent_eye_p_b;
    private javax.swing.JRadioButton jRadioButtonHeent_eye_p_l;
    private javax.swing.JRadioButton jRadioButtonHeent_eye_p_r;
    private javax.swing.JRadioButton jRadioButtonLung_coar_b;
    private javax.swing.JRadioButton jRadioButtonLung_coar_l;
    private javax.swing.JRadioButton jRadioButtonLung_coar_r;
    private javax.swing.JRadioButton jRadioButtonLung_fine_b;
    private javax.swing.JRadioButton jRadioButtonLung_fine_l;
    private javax.swing.JRadioButton jRadioButtonLung_fine_r;
    private javax.swing.JRadioButton jRadioButtonLung_rhon_b;
    private javax.swing.JRadioButton jRadioButtonLung_rhon_l;
    private javax.swing.JRadioButton jRadioButtonLung_rhon_r;
    private javax.swing.JRadioButton jRadioButtonLung_whee_b;
    private javax.swing.JRadioButton jRadioButtonLung_whee_l;
    private javax.swing.JRadioButton jRadioButtonLung_whee_r;
    private javax.swing.JRadioButton jRadioButtonNeuro_cn_abno;
    private javax.swing.JRadioButton jRadioButtonNeuro_cn_imp1;
    private javax.swing.JRadioButton jRadioButtonNeuro_cn_imp2;
    private javax.swing.JRadioButton jRadioButtonNeuro_cn_inta;
    private javax.swing.JRadioButton jRadioButtonNeuro_cn_inta1;
    private javax.swing.JRadioButton jRadioButtonNeuro_cn_inta2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextAreaOther;
    private javax.swing.JTextArea jTextArea_ga_other;
    private javax.swing.JTextField jTextFieldAbdomen_pal_dec;
    private javax.swing.JTextField jTextFieldAbdomen_tend_dec;
    private javax.swing.JTextField jTextFieldExtreme_ten_dec;
    private javax.swing.JTextField jTextFieldExtremity_oth;
    private javax.swing.JTextField jTextFieldLung_others;
    private javax.swing.JTextField jTextFieldNeuro_oth;
    private javax.swing.JTextField jTextFieldSkin_oth;
    private javax.swing.JTextField jTextFieldThyroids_others;
    private javax.swing.JTextField jTextFieldTonsils_others;
    private com.hosv3.gui.dialog.PanelWound panelWound1;
    // End of variables declaration//GEN-END:variables
     public boolean showDialog(Vector vPE,boolean is_write)
    {
        //setDefault();
        ret = false;
        //Constant.println("--------------1 vPE = " + vPE.size());
        panelWound1.setVisitId(theHC.theHO.thePatient.getObjectId(),theHC.theHO.theVisit.getObjectId());
        if(vPE==null){
            vPE = theHC.theVitalControl.listPhysicalExam();
            if(vPE.isEmpty()){
                //this.jTabbedPane1.setSelectedIndex(0);
                setDefault();
            }
            else
                setPhysicalExamV(vPE);    
        }
        else
            setPhysicalExamV(vPE);
        
        this.setEnabled(is_write);
        this.jTabbedPane1.setSelectedIndex(0);
        theJD.setVisible(true);    
        return ret;
    }
     
    public static void main(String args[])
    {
        JFrame jf = new JFrame();
        jf.setSize(800,600);
        jf.getContentPane().add(new PanelPhysicalExam());
        jf.pack();
        jf.setVisible(true);
        
    }  
    
    public void setLanguage(String msg)
    {
        GuiLang.setLanguage(jTabbedPane1);
        GuiLang.setLanguage(jButtonCancel);
        GuiLang.setLanguage(jButtonSave);
        GuiLang.setLanguage(jButtonCancel1);
        GuiLang.setLanguage(jButtonSave1);
        GuiLang.setLanguage(jLabel20);
        GuiLang.setLanguage(jCheckBoxAbdomen_regu);
        GuiLang.setLanguage(jCheckBoxAbdomen_dark);
        GuiLang.setLanguage(jCheckBoxAbdomen_clear);
    }
}
