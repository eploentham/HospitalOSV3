/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelServiceLocation.java
 *
 * Created on 27 �.�. 2553, 10:21:29
 */

package com.hosv3.gui.dialog;

import com.hospital_os.object.Visit;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author LionHeart
 */
public class PanelServiceLocation extends javax.swing.JPanel {
    private JDialog theJD;
    Visit theVisit;
    /** Creates new form PanelServiceLocation */
    public PanelServiceLocation() {
        initComponents();
    }
    public void showDialog(JFrame theJF,Visit visit)
    {
        theVisit= visit;
        if(theJD==null)
            theJD = new JDialog(theJF);
        theJD.add(this);
        theJD.setSize(205,160);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, (screenSize.height - theJD.getSize().height) / 2);
        theJD.setTitle("�кػ����� Visit");
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
        jLabel1 = new javax.swing.JLabel();
        jRadioButtonServiceLocationIN = new javax.swing.JRadioButton();
        jRadioButtonServiceLocationOUT = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("���͡�������ͧ Visit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jLabel1, gridBagConstraints);

        buttonGroup1.add(jRadioButtonServiceLocationIN);
        jRadioButtonServiceLocationIN.setSelected(true);
        jRadioButtonServiceLocationIN.setText("����ԡ���˹��º�ԡ��");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 5);
        add(jRadioButtonServiceLocationIN, gridBagConstraints);

        buttonGroup1.add(jRadioButtonServiceLocationOUT);
        jRadioButtonServiceLocationOUT.setText("����ԡ�ù͡˹��º�ԡ��");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        add(jRadioButtonServiceLocationOUT, gridBagConstraints);

        jButton1.setText("��ŧ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        add(jButton1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jRadioButtonServiceLocationIN.isSelected())
            theVisit.service_location = "1";
        else
            theVisit.service_location = "2";
        theJD.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButtonServiceLocationIN;
    private javax.swing.JRadioButton jRadioButtonServiceLocationOUT;
    // End of variables declaration//GEN-END:variables

}
