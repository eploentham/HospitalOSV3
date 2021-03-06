/*
 * DialogListAnReused.java
 *
 * Created on 26 ��Ȩԡ�¹ 2548, 9:25 �.
 */
package com.hosv3.gui.dialog;

import java.util.*;
import java.awt.*;

import com.hospital_os.utility.TaBleModel;
import com.hospital_os.object.*;
import com.hosv3.control.*;
import com.hosv3.utility.*;
import com.hosv3.utility.connection.*;
/**
 *
 * @author  Administrator
 */
public class DialogListAnReused extends javax.swing.JDialog {
    
    HosControl theHC;
    UpdateStatus theUS;
    Vector vReverseAdmit;
    ReverseAdmit theReverseAdmit;
    String[] columnHeader = {"AN","�ѹ��� Admit","¡��ԡ"};
    /** Creates new form DialogListAnReused */
    public DialogListAnReused(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLanguage("");
        setSize(272,184);
        Toolkit thekit = getToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-getSize().width)/2, (screenSize.height-getSize().height)/2);
    }
    public void setControl(HosControl hc,UpdateStatus us){
        theHC = hc;
        theUS = us;
    }
    public void setLanguage(String msg)
    {
        GuiLang.setLanguage(jPanel1);
        GuiLang.setLanguage(jCheckBox1);
	GuiLang.setLanguage(jButtonCancel);
	GuiLang.setLanguage(jButtonSave);
	GuiLang.setLanguage(columnHeader);
    }    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jButtonRefresh = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new com.hosv3.gui.component.HJTableSort();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(new javax.swing.border.TitledBorder("\u0e2b\u0e21\u0e32\u0e22\u0e40\u0e25\u0e02 An\u0e17\u0e35\u0e48\u0e16\u0e39\u0e01\u0e22\u0e01\u0e40\u0e25\u0e34\u0e01\u0e41\u0e25\u0e30\u0e15\u0e49\u0e2d\u0e07\u0e01\u0e32\u0e23\u0e19\u0e33\u0e01\u0e25\u0e31\u0e1a\u0e21\u0e32\u0e43\u0e0a\u0e49\u0e43\u0e2b\u0e21\u0e48"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jButtonSave.setText("OK");
        jButtonSave.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonSaveActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButtonSave, gridBagConstraints);

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonCancelActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel2.add(jButtonCancel, gridBagConstraints);

        jButtonRefresh.setText("Refresh");
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonRefreshActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel2.add(jButtonRefresh, gridBagConstraints);

        jCheckBox1.setText("\u0e22\u0e01\u0e40\u0e25\u0e34\u0e01");
        jCheckBox1.setToolTipText("\u0e22\u0e01\u0e40\u0e25\u0e34\u0e01\u0e01\u0e32\u0e23\u0e19\u0e33\u0e40\u0e25\u0e02 AN \u0e17\u0e35\u0e48\u0e40\u0e25\u0e37\u0e2d\u0e01\u0e44\u0e1b\u0e43\u0e0a\u0e49\u0e07\u0e32\u0e19\u0e15\u0e48\u0e2d");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jCheckBox1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel2.add(jCheckBox1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jPanel2, gridBagConstraints);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        vReverseAdmit = theHC.theVisitControl.searchAdmitNumberInReverseAdmit();
        updateOG(vReverseAdmit);
    }//GEN-LAST:event_jButtonRefreshActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        int[] row = this.jTable1.getSelectedRows();
        theHC.theVisitControl.saveReverseAdmit(vReverseAdmit,row);
        vReverseAdmit = theHC.theVisitControl.searchAdmitNumberInReverseAdmit();
        updateOG(vReverseAdmit);
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        int row = this.jTable1.getSelectedRow();
        if(row != -1)
            theReverseAdmit = (ReverseAdmit)vReverseAdmit.get(row);        
        this.dispose();
    }//GEN-LAST:event_jButtonSaveActionPerformed
    
    public void updateOG(Vector vc)
    {
        vReverseAdmit = vc;
        TaBleModel tm=null;
        if(vc == null) {
            tm = new TaBleModel(columnHeader,0);
            jTable1.setModel(tm);
            return;
        }
        tm = new TaBleModel(columnHeader,vc.size());
        for(int i=0 ;i<vc.size(); i++)
        {  
            ReverseAdmit ra = (ReverseAdmit)vc.get(i);
            tm.setValueAt(ra.an,i,0);
            tm.setValueAt(DateUtil.convertFieldDate(ra.begin_admit_time),i,1);
            tm.setValueAt(ra.used, i, 2);
        }
        jTable1.setModel(tm);
        if(!vc.isEmpty())
            jTable1.setRowSelectionInterval(0,0);
    }
    
    public static ReverseAdmit showDialog(HosControl hc,UpdateStatus us,Vector v)
    {
        DialogListAnReused dlar = new DialogListAnReused(us.getJFrame(),true);
        dlar.setControl(hc,us);
        dlar.updateOG(v);
        dlar.setVisible(true);
        return dlar.theReverseAdmit;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new DialogListAnReused(new javax.swing.JFrame(), true).setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.hosv3.gui.component.HJTableSort jTable1;
    // End of variables declaration//GEN-END:variables
    
}
