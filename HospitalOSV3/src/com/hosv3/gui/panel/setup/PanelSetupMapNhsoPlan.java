/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelSetupMapNhsoPlan.java
 *
 * Created on 7 ม.ค. 2554, 11:55:05
 */

package com.hosv3.gui.panel.setup;

import com.hospital_os.utility.TaBleModel;
import com.hosv3.control.HosControl;
import com.hosv3.gui.dialog.PanelSearchPlan;
import com.hosv3.object.MapNhsoMainInscl;
import com.hosv3.object.MapNhsoPlan;
import com.hosv3.object.MapNhsoSubInscl;
import com.hosv3.object.NhsoMainInscl;
import com.hosv3.object.NhsoRight;
import com.hosv3.object.NhsoSubInscl;
import com.hosv3.utility.connection.UpdateStatus;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author LionHeart
 */
public class PanelSetupMapNhsoPlan extends javax.swing.JPanel {

    public NhsoRight theNhsoRight;
    Vector theNhsoSubInsclV;
    HosControl theHC;
    UpdateStatus theUS;
    private JDialog theJD;
    String[] sub_inscl_col = {"สิทธิย่อย สปสช"};
    String[] col2 = {"สิทธิ์โรงพยาบาล","เขต"};
    String[] main_inscl_col = {"สิทธิหลัก สปสช"};
    PanelSearchPlan thePanelSearchPlan;
    Vector theMapNhsoSubInsclV;
    Vector theNhsoMainInsclV;
    private Vector theMapNhsoMainInsclV;
    public PanelSetupMapNhsoPlan() {
        initComponents();
        jTabbedPane1.remove(jPanel1);
    }
    public NhsoRight getNhsoRight()
    {
        if(theNhsoRight==null)
            theNhsoRight = new NhsoRight();
        theNhsoRight.des = jTextFieldDes.getText();
        if(jCheckBoxActive.isSelected())
            theNhsoRight.active = "1";
        else
            theNhsoRight.active = "0";
        return theNhsoRight;
    }
    public void setNhsoRight(NhsoRight nhsoRight)
    {
        theNhsoRight = nhsoRight;
        if(theNhsoRight == null)
            theNhsoRight = new NhsoRight();
        jTextFieldDes.setText(theNhsoRight.des);
        if(theNhsoRight.active.equals("1")||theNhsoRight.active.equals(""))
            jCheckBoxActive.setSelected(true);
        else
            jCheckBoxActive.setSelected(false);
    }
    public void setNhsoSubInsclV(Vector v)
    {
        theNhsoSubInsclV = v;
        if(theNhsoSubInsclV == null)
            theNhsoSubInsclV = new Vector();
        TaBleModel tm = new TaBleModel(sub_inscl_col,theNhsoSubInsclV.size());
        for(int i=0;i<theNhsoSubInsclV.size();i++)
        {
            NhsoSubInscl nhsoRight = (NhsoSubInscl) theNhsoSubInsclV.get(i);
            tm.setValueAt(nhsoRight.nhso_sub_inscl_name, i, 0);
        }
        jTable2.setModel(tm);
    }
    public void setNhsoMainInsclV(Vector v)
    {
        theNhsoMainInsclV = v;
        if(theNhsoMainInsclV == null)
            theNhsoMainInsclV = new Vector();
        TaBleModel tm = new TaBleModel(this.main_inscl_col,theNhsoMainInsclV.size());
        for(int i=0;i<theNhsoMainInsclV.size();i++)
        {
            NhsoMainInscl mapNhsoPlan = (NhsoMainInscl) theNhsoMainInsclV.get(i);
            tm.setValueAt(mapNhsoPlan.nhso_main_seq, i, 0);
        }
        jTable4.setModel(tm);
    }
    public void setMapNhsoSubInsclV(Vector v)
    {
        theMapNhsoSubInsclV = v;
        if(theMapNhsoSubInsclV == null)
            theMapNhsoSubInsclV = new Vector();
        TaBleModel tm = new TaBleModel(col2,theMapNhsoSubInsclV.size());
        for(int i=0;i<theMapNhsoSubInsclV.size();i++)
        {
            MapNhsoSubInscl mapNhsoPlan = (MapNhsoSubInscl) theMapNhsoSubInsclV.get(i);
            tm.setValueAt(mapNhsoPlan.contract_plans_des, i, 0);
            if(mapNhsoPlan.map_nhso_sub_inscl_type.equals("1"))
                tm.setValueAt("ในเขต", i, 1);
            else if(mapNhsoPlan.map_nhso_sub_inscl_type.equals("2"))
                tm.setValueAt("นอกเขต", i, 1);
        }
        jTable3.setModel(tm);
    }
    public void setMapNhsoMainInsclV(Vector v)
    {
        theMapNhsoMainInsclV = v;
        if(theMapNhsoSubInsclV == null)
            theMapNhsoSubInsclV = new Vector();
        TaBleModel tm = new TaBleModel(col2,theMapNhsoMainInsclV.size());
        for(int i=0;i<theMapNhsoMainInsclV.size();i++)
        {
            MapNhsoMainInscl mapNhsoPlan = (MapNhsoMainInscl) theMapNhsoMainInsclV.get(i);
            tm.setValueAt(mapNhsoPlan.contract_plans_des, i, 0);
            if(mapNhsoPlan.map_nhso_main_inscl_type.equals("1"))
                tm.setValueAt("ในเขต", i, 1);
            else if(mapNhsoPlan.map_nhso_main_inscl_type.equals("2"))
                tm.setValueAt("นอกเขต", i, 1);
        }
        jTable5.setModel(tm);
    }
    public void setControl(HosControl hc,UpdateStatus us)
    {
        theHC = hc;
        theUS = us;
        setNhsoSubInsclV(theHC.theSystemControl.listNhsoSubInscl());
        setNhsoMainInsclV(theHC.theSystemControl.listNhsoMainInscl());
    }
    public void showDialog()
    {
        if(theJD==null)
            theJD = new JDialog(theUS.getJFrame());
        theJD.add(this);
        theJD.setSize(600,500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, (screenSize.height - theJD.getSize().height) / 2);
        theJD.setTitle("ตั้งค่าการจับคู่สิทธิ์ของ สปสช กับสิทธิ์ของโรงพยาบาล");
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldDes = new javax.swing.JTextField();
        jCheckBoxActive = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel3.setMinimumSize(new java.awt.Dimension(20, 20));
        jPanel3.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(20, 20));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 429;
        gridBagConstraints.ipady = 396;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("ชื่อสิทธิ์ สปสช"));
        jPanel4.setMinimumSize(new java.awt.Dimension(20, 23));
        jPanel4.setPreferredSize(new java.awt.Dimension(20, 569));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("ชื่อสิทธิ์");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel4.add(jLabel1, gridBagConstraints);

        jTextFieldDes.setMinimumSize(new java.awt.Dimension(200, 22));
        jTextFieldDes.setPreferredSize(new java.awt.Dimension(200, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jTextFieldDes, gridBagConstraints);

        jCheckBoxActive.setSelected(true);
        jCheckBoxActive.setText("Active");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 0, 0);
        jPanel4.add(jCheckBoxActive, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 3);
        jPanel1.add(jPanel4, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jButtonAdd.setText("+");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jButtonAdd, gridBagConstraints);

        jButtonDelete.setText("-");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jButtonDelete, gridBagConstraints);

        jButtonSave.setText("บันทึก");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(jButtonSave, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jPanel5, gridBagConstraints);

        jTabbedPane1.addTab("สิทธิ สปสช", jPanel1);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setPreferredSize(new java.awt.Dimension(20, 20));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable2MouseReleased(evt);
            }
        });
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable2KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jScrollPane2, gridBagConstraints);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(20, 20));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTable3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel2.add(jScrollPane3, gridBagConstraints);

        jButton1.setText("-");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 4);
        jPanel2.add(jButton1, gridBagConstraints);

        jTabbedPane1.addTab("จับคู่สิทธิย่อย สปสช กับสิทธิของโรงพยาบาล", jPanel2);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable4MouseReleased(evt);
            }
        });
        jTable4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable4KeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(jScrollPane4, gridBagConstraints);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(jTable5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(jScrollPane5, gridBagConstraints);

        jButton2.setText("-");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel6.add(jButton2, gridBagConstraints);

        jTabbedPane1.addTab("จับคู่สิทธิหลัก สปสช กับสิทธิของโรงพยาบาล", jPanel6);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jTabbedPane1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        setNhsoRight(null);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        getNhsoRight();
        theHC.theSystemControl.saveNhsoRight(theNhsoRight);
        setNhsoSubInsclV(theHC.theSystemControl.listNhsoRight());
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        int select = jTable1.getSelectedRow();
        NhsoRight nhsoRight = (NhsoRight) this.theNhsoSubInsclV.get(select);
        this.setNhsoRight(nhsoRight);
    }//GEN-LAST:event_jTable1MouseReleased

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this.theUS.getJFrame(),"ยืนยันการลบข้อมูล","ยืนยันการลบข้อมูล",JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.NO_OPTION)
            return;
        int select = jTable1.getSelectedRow();
        NhsoRight nhsoRight = (NhsoRight) this.theNhsoSubInsclV.get(select);
        theHC.theSystemControl.deleteNhsoRight(nhsoRight);
        setNhsoSubInsclV(theHC.theSystemControl.listNhsoRight());
        setNhsoRight(null);
        setMapNhsoSubInsclV(null);
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jTable2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseReleased
        int select = jTable2.getSelectedRow();
        NhsoSubInscl nhsoRight = (NhsoSubInscl) this.theNhsoSubInsclV.get(select);
        if(evt.getClickCount()==2)
        {
            if(thePanelSearchPlan == null)
            {
                thePanelSearchPlan = new PanelSearchPlan();
                thePanelSearchPlan.setControl(theHC, theUS);
            }
            thePanelSearchPlan.showDialog(nhsoRight);
            setMapNhsoSubInsclV(theHC.theSystemControl.listMapNhsoSubInsclByNhsoRightID(nhsoRight.getObjectId()));
        }
        else
        {
            setMapNhsoSubInsclV(theHC.theSystemControl.listMapNhsoSubInsclByNhsoRightID(nhsoRight.getObjectId()));
        }
    }//GEN-LAST:event_jTable2MouseReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this.theUS.getJFrame(),"ยืนยันการลบข้อมูล","ยืนยันการลบข้อมูล",JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.NO_OPTION)
            return;
        int select = jTable3.getSelectedRow();
        MapNhsoSubInscl mnsi = (MapNhsoSubInscl) theMapNhsoSubInsclV.get(select);
        theHC.theSystemControl.deleteNhsoSubInscl(mnsi);
        int select2 = jTable2.getSelectedRow();
        NhsoSubInscl nhsoRight = (NhsoSubInscl) this.theNhsoSubInsclV.get(select2);
        setMapNhsoSubInsclV(theHC.theSystemControl.listMapNhsoSubInsclByNhsoRightID(nhsoRight.getObjectId()));
//
//
//        for(int i=0;i<select.length;i++)
//        {
//            MapNhsoPlan mapNhsoPlan = (MapNhsoPlan) theMapNhsoSubInsclV.get(select[i]);
//            theHC.theSystemControl.deleteMapNhsoPlan(mapNhsoPlan);
//            setMapNhsoPlanV(theHC.theSystemControl.listMapNhsoPlanByNhsoRightID(nhsoRight.getObjectId()));
//        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseReleased
        int select = jTable4.getSelectedRow();
        NhsoMainInscl nhsoMainInscl = (NhsoMainInscl) theNhsoMainInsclV.get(select);
        if(evt.getClickCount()==2)
        {
            if(thePanelSearchPlan == null)
            {
                thePanelSearchPlan = new PanelSearchPlan();
                thePanelSearchPlan.setControl(theHC, theUS);
            }
            thePanelSearchPlan.showDialog(nhsoMainInscl);
            setMapNhsoMainInsclV(theHC.theSystemControl.listMapNhsoMainInsclByNhsoRightID(nhsoMainInscl.getObjectId()));
        }
        else
        {
            setMapNhsoMainInsclV(theHC.theSystemControl.listMapNhsoMainInsclByNhsoRightID(nhsoMainInscl.getObjectId()));
        }
    }//GEN-LAST:event_jTable4MouseReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this.theUS.getJFrame(),"ยืนยันการลบข้อมูล","ยืนยันการลบข้อมูล",JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.NO_OPTION)
            return;
        int select = jTable5.getSelectedRow();
        MapNhsoMainInscl mnsi = (MapNhsoMainInscl) theMapNhsoMainInsclV.get(select);
        theHC.theSystemControl.deleteNhsoMainInscl(mnsi);
        int select2 = jTable4.getSelectedRow();
        NhsoMainInscl nhsoRight = (NhsoMainInscl) this.theNhsoMainInsclV.get(select2);
        setMapNhsoMainInsclV(theHC.theSystemControl.listMapNhsoMainInsclByNhsoRightID(nhsoRight.getObjectId()));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyReleased
        int select = jTable2.getSelectedRow();
        NhsoSubInscl nhsoRight = (NhsoSubInscl) this.theNhsoSubInsclV.get(select);
        setMapNhsoSubInsclV(theHC.theSystemControl.listMapNhsoSubInsclByNhsoRightID(nhsoRight.getObjectId()));
    }//GEN-LAST:event_jTable2KeyReleased

    private void jTable4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable4KeyReleased
        int select = jTable4.getSelectedRow();
        NhsoMainInscl nhsoMainInscl = (NhsoMainInscl) theNhsoMainInsclV.get(select);
        setMapNhsoMainInsclV(theHC.theSystemControl.listMapNhsoMainInsclByNhsoRightID(nhsoMainInscl.getObjectId()));
    }//GEN-LAST:event_jTable4KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JCheckBox jCheckBoxActive;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextFieldDes;
    // End of variables declaration//GEN-END:variables

}
