/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelChooseBornMchMother.java
 *
 * Created on 31 �.�. 2554, 10:26:38
 */

package com.pcu.gui.dialog;

import com.hosv3.control.HosControl;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.control.HosManage;
import com.pcu.gui.panelpcu.PanelNewMCHMother;
import com.pcu.object.AncPcu;
import com.pcu.object.BornMch;
import com.pcu.object.Pregnancy;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;

/**
 *
 * @author LionHeart
 */
public class PanelChooseBornMchMother extends javax.swing.JPanel {
    private HosManage theHM;
    private HosDialog theHD;
    private UpdateStatus theUS;
    HosControl theHC;
    private JDialog theJD;
    private int anc_time=0;
    PanelNewMCHMother thePanelNewMCHMother;
    private Pregnancy thePregnancy;
    private BornMch theBornMch;
    private AncPcu theAncPcu;
    public PanelChooseBornMchMother() {
        initComponents();
    }
    public void setControl(HosManage hm,HosDialog hd,UpdateStatus us)
    {
        theHM = hm;
        theHC = theHM.theHC;
        theHD = hd;
        theUS = us;
    }
    public void showDialog()
    {
        if(theJD==null)
            theJD = new JDialog(theHC.theUS.getJFrame());
        theJD.add(this);
        theJD.setSize(279,130);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, (screenSize.height - theJD.getSize().height) / 2);
        theJD.setTitle("���͡�����Ŵ��������ѧ��ʹ");
        theJD.setModal(true);
        theJD.setVisible(true);
    }
    public void setAncTime(int anc_time,int born_size)
    {
        this.anc_time = anc_time;
        jLabel2.setText(String.valueOf(anc_time));
        jComboBox1.removeAllItems();
        if(this.anc_time == 0)
        {
            jComboBox1.addItem("������͡�����");
        }
        else
        {
            for(int i=1;i<=born_size;i++)
            {
                this.jComboBox1.addItem(String.valueOf(i));
            }
        }
    }
    public void setLeftPanel(Pregnancy pregnancy,BornMch bornMch,AncPcu ancPcu)
    {
        thePregnancy = pregnancy;
        theBornMch = bornMch;
        theAncPcu = ancPcu;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("�������ա�ýҡ���������");
        jPanel1.add(jLabel1, new java.awt.GridBagConstraints());

        jLabel2.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setText("����");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        add(jPanel1, gridBagConstraints);

        jLabel4.setText("���͡�ѹ�֡���������ѧ��ʹ�ҡ��������� ?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 5);
        add(jLabel4, gridBagConstraints);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setMinimumSize(new java.awt.Dimension(100, 18));
        jComboBox1.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 5);
        add(jComboBox1, gridBagConstraints);

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
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        theJD.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(thePanelNewMCHMother == null)
        {
            thePanelNewMCHMother = new PanelNewMCHMother();
            thePanelNewMCHMother.setControl(theHM, theHD, theUS);
        }
        if(anc_time>0)
        {
            thePanelNewMCHMother.setEnable1(false);
            thePanelNewMCHMother.setEnable2(true);
        }
        else
        {
            thePanelNewMCHMother.setEnable1(true);
            thePanelNewMCHMother.setEnable2(true);
        }
        theJD.setVisible(false);
        thePanelNewMCHMother.setLeftPanel(thePregnancy, theBornMch, theAncPcu);
        thePanelNewMCHMother.showDialog();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}
