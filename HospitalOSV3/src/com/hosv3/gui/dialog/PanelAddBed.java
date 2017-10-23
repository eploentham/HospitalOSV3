/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hosv3.gui.dialog;

import com.hospital_os.object.Ward;
import com.hosv3.control.HosControl;
import com.hosv3.object.Bed;
import com.hosv3.object.Room;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;

/**
 *
 * @author  administrator
 * 1.  60-10-23 ����ͧ ��ͧ     Hospital OS ������� �������ͧ
 * Modify doc 6.
 */
public class PanelAddBed extends javax.swing.JPanel {
    private HosControl theHC;
    private JDialog theJD;
    Ward theWard;
    Room theRoom;
    Bed theBed;
    /**
     * Creates new form PanelAddBed
     */
    public PanelAddBed() {
        initComponents();
    }
    public void setControl(HosControl hc)
    {
        theHC = hc;
    }
    public Bed getBed()
    {
        theBed = new Bed();
        theBed.b_visit_room_id = theRoom.getObjectId();
        theBed.b_visit_ward_id = theWard.getObjectId();
        theBed.visit_bed_number = jTextField1.getText();
        theBed.visit_bed_active = "1";
        return theBed;
    }
    public void showDialog(Ward ward,Room room)
    {
        theWard = ward;
        theRoom = room;
        if(theJD==null)
            theJD = new JDialog(theHC.theUS.getJFrame());
        jTextField1.setText("");
        theJD.add(this);
        theJD.setSize(287, 60);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, (screenSize.height - theJD.getSize().height) / 2);
//        theJD.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                
//            }
//            });
        theJD.setTitle("������§");
        theJD.setModal(true);
        theJD.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("������§");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 0, 0);
        add(jLabel1, gridBagConstraints);

        jTextField1.setMinimumSize(new java.awt.Dimension(150, 28));
        jTextField1.setPreferredSize(new java.awt.Dimension(150, 28));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        add(jTextField1, gridBagConstraints);

        jButton1.setText("�ѹ�֡");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        add(jButton1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        getBed();
        theHC.theSetupControl.saveBed(theBed);
        theJD.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            jButton1ActionPerformed(null);
    }//GEN-LAST:event_jTextField1KeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
