/*
 * DialogViewSequence.java
 *
 * Created on 2 ���Ҥ� 2548, 11:08 �.
 */
package com.hosv3.gui.dialog;
import com.hosv3.utility.Constant;
import com.hosv3.control.*;
import com.hosv3.utility.*;
import com.hosv3.utility.connection.*;
import com.hospital_os.utility.TaBleModel;
import java.awt.*;
import javax.swing.table.*;

/**
 *
 * @author  tong
 */
public class DialogViewSequence extends javax.swing.JDialog {
    
    /** Creates new form DialogViewSequence */
    HosControl theHosControl = null;
    private String[] col_jTableViewSequece = {"����","�ӴѺ����ش"};
    DefaultTableCellRenderer rendererRight = new DefaultTableCellRenderer();
    public DialogViewSequence(HosControl hosControl, UpdateStatus us) {
        super(us.getJFrame(),true);
        rendererRight.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        this.theHosControl = hosControl;
        initComponents();
        setLanguage(null);
        //this.setIconImage(us.getJFrame().getIconImage());
        this.setDialog();
    }
    private void setDialog()
    {
        this.setSize(300,250);     
        this.setTitle(Constant.getTextBundle("�ʴ��Ţ Sequence ����ش"));
        Toolkit thekit = this.getToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width-this.getSize().width)/2, (screenSize.height-this.getSize().height)/2);
    }
    public void setLanguage(String msg)
    {         
        GuiLang.setLanguage(this.col_jTableViewSequece);
    }    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableViewSequece = new com.hosv3.gui.component.HJTableSort();
        jPanel3 = new javax.swing.JPanel();

        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jTableViewSequece.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableViewSequece);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jPanel3, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        //System.exit(0);
        this.dispose();
    }//GEN-LAST:event_exitForm
    
    public void viewSequence()
    {
        
        String[] max = new String[9];
        String[] name = new String[9];
        TaBleModel tm = new TaBleModel(col_jTableViewSequece,name.length);
        theHosControl.theVisitControl.maxSequenceNumber(max,name);
        for(int i=0;i<name.length;i++){
            tm.setValueAt(name[i],i,0);    
            tm.setValueAt(max[i],i,1);    
        }
        this.jTableViewSequece.setModel(tm);        
        this.jTableViewSequece.getColumnModel().getColumn(0).setPreferredWidth(300);
        this.jTableViewSequece.getColumnModel().getColumn(1).setPreferredWidth(100);
        this.jTableViewSequece.getColumnModel().getColumn(1).setCellRenderer(rendererRight);
        
    }
    
    public void showDialog()
    {
        this.viewSequence();
        this.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       // new DialogViewSequence(new javax.swing.JFrame(),null).setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private com.hosv3.gui.component.HJTableSort jTableViewSequece;
    // End of variables declaration//GEN-END:variables
    
}