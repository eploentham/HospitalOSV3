/*
 * DialogShowUseAdmitNumber.java
 *
 * Created on 11 �ѹ��¹ 2547, 18:50 �.
 */
package com.hosv3.gui.dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;

import com.hospital_os.object.ReverseAdmit;
import com.hospital_os.object.Visit;
import com.hospital_os.utility.TaBleModel;
import com.hosv3.control.VisitControl;
import com.hosv3.utility.DateUtil;
import com.hosv3.utility.GuiLang;
/**
 *
 * @author  tong
 */
public class DialogShowUseAdmitNumber extends javax.swing.JFrame {
    
    /** Creates new form DialogShowUseAdmitNumber */
    Visit theVisit;
    Vector vReverseAdmit;
    VisitControl theVisitControl;
    private String[] col_jTableShowAdmitNumber = {"�Ţ AN","�ѹ���¡��ԡ"};
    public DialogShowUseAdmitNumber(javax.swing.JFrame parent,VisitControl vc,Visit visit,Vector vreverseAdmit) {
        this.setTitle("�ʴ��Ţ AN ���١¡��ԡ ��е�ͧ��ùӡ�Ѻ��������");
        this.setIconImage(parent.getIconImage());
        this.theVisitControl = vc;
        this.theVisit = visit;
        this.vReverseAdmit = vreverseAdmit;
        
        initComponents();
        setLanguage("");
        this.setDialog();
        this.setTableShowAdmitNumber(this.vReverseAdmit);
    }
    public void setLanguage(String msg)
    {        
        GuiLang.setLanguage(jButtonRefresh);
        GuiLang.setLanguage(jButtonOK);
        GuiLang.setLanguage(jButtonCalcel);
        GuiLang.setLanguage(col_jTableShowAdmitNumber);
        GuiLang.setTextBundle(jPanel2);
    }      
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableShowAdmitNumber = new com.hosv3.gui.component.HJTableSort();
        jPanel3 = new javax.swing.JPanel();
        jButtonOK = new javax.swing.JButton();
        jButtonCalcel = new javax.swing.JButton();
        jButtonRefresh = new javax.swing.JButton();
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        jPanel1.setLayout(new java.awt.GridBagLayout());
        jPanel2.setLayout(new java.awt.GridBagLayout());
        jPanel2.setBorder(new javax.swing.border.TitledBorder("\u0e2b\u0e21\u0e32\u0e22\u0e40\u0e25\u0e02 An\u0e17\u0e35\u0e48\u0e16\u0e39\u0e01\u0e22\u0e01\u0e40\u0e25\u0e34\u0e01\u0e41\u0e25\u0e30\u0e15\u0e49\u0e2d\u0e07\u0e01\u0e32\u0e23\u0e19\u0e33\u0e01\u0e25\u0e31\u0e1a\u0e21\u0e32\u0e43\u0e0a\u0e49\u0e43\u0e2b\u0e21\u0e48"));
        jTableShowAdmitNumber.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableShowAdmitNumber.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableShowAdmitNumberMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableShowAdmitNumber);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel2, gridBagConstraints);
        jPanel3.setLayout(new java.awt.GridBagLayout());
        jButtonOK.setText("OK");
        jButtonOK.setPreferredSize(new java.awt.Dimension(75, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(jButtonOK, gridBagConstraints);
        jButtonCalcel.setText("Cancel");
        jButtonCalcel.setMaximumSize(new java.awt.Dimension(49, 25));
        jButtonCalcel.setMinimumSize(new java.awt.Dimension(49, 25));
        jButtonCalcel.setPreferredSize(new java.awt.Dimension(75, 25));
        jButtonCalcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalcelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel3.add(jButtonCalcel, gridBagConstraints);
        jButtonRefresh.setText("Refresh");
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel3.add(jButtonRefresh, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jPanel3, gridBagConstraints);
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        pack();
    }//GEN-END:initComponents
    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        // TODO add your handling code here:
        this.refreshTable();
    }//GEN-LAST:event_jButtonRefreshActionPerformed
    private void jButtonCalcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalcelActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButtonCalcelActionPerformed
    private void jTableShowAdmitNumberMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableShowAdmitNumberMouseReleased
        // TODO add your handling code here:
   
    }//GEN-LAST:event_jTableShowAdmitNumberMouseReleased
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        //System.exit(0);
        dispose();
    }//GEN-LAST:event_exitForm
    
    private void refreshTable()
    {
        this.vReverseAdmit = this.theVisitControl.searchAdmitNumberInReverseAdmit();
        this.setTableShowAdmitNumber(this.vReverseAdmit);
        
    }
  /*  
    private void checkAdmitNumberUsed()
    {
        int choose = this.jTableShowAdmitNumber.getSelectedRow();
        if(choose != -1)
        {   ReverseAdmit ra = (ReverseAdmit)this.vReverseAdmit.get(choose);
            int used = this.theVisitControl.checkSelectUsedAdmitNumber(ra);
            if(used == 0)
            {   
                JOptionPane.showMessageDialog(this,"�Ţ AN " + ra.an + " �١�������","����͹",JOptionPane.OK_OPTION);
                this.vReverseAdmit.remove(choose);
                this.setTableShowAdmitNumber(this.vReverseAdmit);
                return ;
            }
        }
    }*/
    
    
    
    private void setDialog()
    {
        this.setSize(350,450);     
        
        Toolkit thekit = this.getToolkit();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width-this.getSize().width)/2, (screenSize.height-this.getSize().height)/2);
                
    }
    
    public boolean showDialog()
    {   
        
      
        this.setVisible(true);
        return true;
    }
    
    
    
    /*
     *�ʴ������š�ä��Ң���ä������ѧ �¹� vector ���ʴ�
     *input  :vector vCronic 
     *output :
     */
    private void setTableShowAdmitNumber(Vector vc)
    {
          TaBleModel tm ;
        if(vc !=null)
        {
            tm = new TaBleModel(col_jTableShowAdmitNumber,vc.size());
            ReverseAdmit ra = null;
            for(int i=0; i<vc.size(); i++ )
            {   ra = (ReverseAdmit)vc.get(i);
               
                tm.setValueAt(ra.an,i,0);
                tm.setValueAt(DateUtil.getDateToString(DateUtil.getDateFromText(ra.reverse_admit_date),true), i, 1);
               
            }  
   
        }
        else
        {
            tm = new TaBleModel(col_jTableShowAdmitNumber,0);
            
        }
        this.jTableShowAdmitNumber.setModel(tm);
    }  
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //new DialogShowUseAdmitNumber().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCalcel;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private com.hosv3.gui.component.HJTableSort jTableShowAdmitNumber;
    // End of variables declaration//GEN-END:variables
    
}
