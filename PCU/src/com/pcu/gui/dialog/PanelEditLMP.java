/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelEditLMP.java
 *
 * Created on 24 �.�. 2553, 11:33:43
 */

package com.pcu.gui.dialog;

import com.hospital_os.utility.Gutil;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.control.HosManage;
import com.pcu.gui.panelpcu.PanelANC;
import com.pcu.object.AncDetailPcu;
import com.pcu.object.AncPcu;
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
public class PanelEditLMP extends javax.swing.JPanel {
    private JDialog theJD;
    HosManage theHM;
    Pregnancy thePregnancy;
    PanelANC thePanelANC;
    AncPcu theAncPcu;
    Vector ancPcuV;
    /** Creates new form PanelEditLMP */
    public PanelEditLMP() {
        initComponents();
    }
    public void setControl(HosManage hm)
    {
        theHM = hm;
    }
    public void setAncPcu(AncPcu ancPcu)
    {
        theAncPcu = ancPcu;
    }
    public void setPanelANC(PanelANC panelANC)
    {
        thePanelANC = panelANC;
    }
    public void setPregnancy(Pregnancy pregnancy)
    {
        thePregnancy = pregnancy;
        if(thePregnancy == null)
        {
//            this.theHM.theUS.setStatus("��س����͡��¡�õ�駤����",UpdateStatus.WARNING);
//            return;
            thePregnancy = new Pregnancy();
        }
        dateComboBoxFirstDateHaveLastMen.setText(Gutil.convertFieldDate(thePregnancy.pregnancy_menses_issue_date));
        dateComboBoxEDC.setText(Gutil.convertFieldDate(thePregnancy.pregnancy_menses_expire_date));
    }
    public void showDialog()
    {
        if(theJD==null)
            theJD = new JDialog();
        theJD.add(this);
        theJD.setSize(320, 220);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, (screenSize.height - theJD.getSize().height) / 2);
        theJD.setTitle("��� LMP");
        theJD.pack();
        theJD.setModal(true);
        theJD.setVisible(true);
    }
    private void setSectionSet(int y,AncPcu ancPcu) {
        System.out.println("private void setSectionSet(int y)");
        if (y < 24) {
            ancPcu.anc_section = "1";
            ancPcu.birth_high_risk_id = "24";
        } else if (y < 28) {
            ancPcu.anc_section = "2";
            ancPcu.birth_high_risk_id = "24";
        } else if (y <= 32) {
            ancPcu.anc_section = "3";
            ancPcu.birth_high_risk_id = "24";
        } else if (y > 32 && y <= 40) {
            ancPcu.anc_section = "4";
            ancPcu.birth_high_risk_id = "24";
        } else if (y > 40) {
            ancPcu.anc_section = "5";
            ancPcu.birth_high_risk_id = "19";
        }
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dateComboBoxFirstDateHaveLastMen = new com.pcu.utility.DateComboBox();
        dateComboBoxEDC = new com.pcu.utility.DateComboBox();
        jCheckBoxEdit = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("��� LMP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(jLabel1, gridBagConstraints);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("LMP ����");
        jLabel2.setToolTipText("�ѹ�á�ͧ����ջ�Ш���͹�����ش����");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Corrected EDC");
        jLabel3.setToolTipText("�ѹ��˹���ʹ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        dateComboBoxFirstDateHaveLastMen.setToolTipText("�ѹ�á�ͧ����ջ�Ш���͹�����ش����");
        dateComboBoxFirstDateHaveLastMen.setMinimumSize(new java.awt.Dimension(100, 24));
        dateComboBoxFirstDateHaveLastMen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxFirstDateHaveLastMenActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
        jPanel1.add(dateComboBoxFirstDateHaveLastMen, gridBagConstraints);

        dateComboBoxEDC.setToolTipText("�ѹ��˹���ʹ");
        dateComboBoxEDC.setMinimumSize(new java.awt.Dimension(100, 24));
        dateComboBoxEDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateComboBoxEDCActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
        jPanel1.add(dateComboBoxEDC, gridBagConstraints);

        jCheckBoxEdit.setText("������ؤ����ͧ��ýҡ�������駷������");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 3);
        jPanel1.add(jCheckBoxEdit, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("�ѹ�֡");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new java.awt.GridBagConstraints());

        jButton2.setText("¡��ԡ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 0);
        jPanel1.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void dateComboBoxFirstDateHaveLastMenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxFirstDateHaveLastMenActionPerformed
        String dateLastMen = dateComboBoxFirstDateHaveLastMen.getText();
        if (!dateLastMen.equals("")) {
            dateComboBoxEDC.setText(com.pcu.utility.DateUtil.calPregnant(dateLastMen));
        }
}//GEN-LAST:event_dateComboBoxFirstDateHaveLastMenActionPerformed

    private void dateComboBoxEDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateComboBoxEDCActionPerformed

}//GEN-LAST:event_dateComboBoxEDCActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(thePregnancy.getObjectId()==null)
        {
            this.theHM.theUS.setStatus("��سҺѹ�֡��õ�駤����", UpdateStatus.WARNING);
            return;
        }
        thePregnancy.pregnancy_menses_issue_date = dateComboBoxFirstDateHaveLastMen.getText();
        thePregnancy.pregnancy_menses_expire_date = dateComboBoxEDC.getText();//thePregnancy.pregnancy_menses_issue_date
        this.theHM.theHosControl.theHealthServiceControl.savePregnancy(thePregnancy);
        if(jCheckBoxEdit.isSelected())
        {
            ancPcuV = theHM.theHosControl.theHealthServiceControl.listAncByPregnantID(thePregnancy.getObjectId());
            for(int i=0;i<ancPcuV.size();i++)
            {
                AncPcu tmp = (AncPcu) ancPcuV.get(i);
                AncDetailPcu adp = theHM.theHosControl.theHealthServiceControl.readAncDetailByAncId(tmp.getObjectId());
                int[] age_week = DateUtil.countWeekDay(thePregnancy.pregnancy_menses_issue_date, tmp.survey_date);
                if (age_week != null) {
                    tmp.anc_gravida_week = String.valueOf(age_week[0]);
                    tmp.anc_gravida_day = String.valueOf(age_week[1]);
                }
                setSectionSet(DateUtil.countWeek(thePregnancy.pregnancy_menses_issue_date, tmp.survey_date),tmp);
                int ret = theHM.theHosControl.theHealthServiceControl.saveAnc(tmp, adp);
            }
        }
        theJD.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        theJD.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.pcu.utility.DateComboBox dateComboBoxEDC;
    private com.pcu.utility.DateComboBox dateComboBoxFirstDateHaveLastMen;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBoxEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}
