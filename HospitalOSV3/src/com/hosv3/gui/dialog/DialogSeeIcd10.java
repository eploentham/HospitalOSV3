/*
 * DialogCause.java
 *
 * Created on 7 ���Ҥ� 2546, 12:04 �.
 */
package com.hosv3.gui.dialog;

import java.util.*;

import java.awt.*;
import javax.swing.*;

import com.hospital_os.utility.TaBleModel;
import com.hospital_os.object.*;

import com.hosv3.control.*;
import com.hosv3.utility.*;
import com.hosv3.object.*;
import com.hosv3.utility.connection.*;



/**
 *
 * @author  amp
 */
public class DialogSeeIcd10 extends javax.swing.JDialog {
    HosObject theHO;
    UpdateStatus theUS;
    HosControl theHC;
    public boolean actionCommand = false;    
    Vector vsee;    
    Idx10V3 of;
    private javax.swing.JTextField jt;
    private DiagnosisControl theDiagnosisControl;
    private String[] col_jTableSee = {"��������´"};
    /** Creates new form DialogCause */
    public DialogSeeIcd10(HosControl hc,UpdateStatus us,Vector vsee1,JTextField jt)
    {
        super(us.getJFrame(), true);
        theUS = us;
        theHC = hc;
        theHO = hc.theHO;
        theDiagnosisControl = theHC.theDiagnosisControl;
        initComponents();
        setLanguage("");
    }
    public void showTable(Vector vs)
    {
        if((vs!=null)&&(vs.size()!=0))
        {
             TaBleModel tm = new TaBleModel(col_jTableSee,vs.size());
             for(int i=0; i< vs.size(); i++)
             {  
                of = (Idx10V3)vs.get(i);    
                tm.setValueAt(of.see,i,0);
                this.jTableSee.setModel(tm);
            }
           
        }
        else
        {
             TaBleModel tm = new TaBleModel(col_jTableSee,0);
             this.jTableSee.setModel(tm);
            
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSee = new com.hosv3.gui.component.HJTableSort();
        jButtonCancel = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jTableSee.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableSee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableSeeMouseReleased(evt);
            }
        });

        jScrollPane1.setViewportView(jTableSee);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jButtonCancel.setText("Cancel");
        jButtonCancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonCancel.setMaximumSize(new java.awt.Dimension(62, 24));
        jButtonCancel.setMinimumSize(new java.awt.Dimension(62, 24));
        jButtonCancel.setPreferredSize(new java.awt.Dimension(62, 24));
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(jButtonCancel, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jTableSeeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSeeMouseReleased
        addSee();
        dispose();
    }//GEN-LAST:event_jTableSeeMouseReleased
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);        
        dispose();
    }//GEN-LAST:event_closeDialog
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      //  new DialogAdmit(new javax.swing.JFrame(), true).setVisible(true);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.hosv3.gui.component.HJTableSort jTableSee;
    // End of variables declaration//GEN-END:variables

     private void addSee()
    {   
        if(vsee!=null)
        {
            int row = this.jTableSee.getSelectedRow();
            JTextField jTextFieldKeyword = (JTextField)jt;
            Idx10V3 oof = new Idx10V3();
            oof = (Idx10V3)vsee.get(row);
            if(oof!=null)
            {
               //henbe_error theHC.theDiagnosisControl.sentSee(of);
                String see = oof.see.substring(0, 4);
                if(see.equals("see "))
                {
                    oof.see = oof.see.substring(4, oof.see.length());
                }
               jTextFieldKeyword.setText(oof.see); 
            }
        }
    }

/*-----------------------------------------------------------*/    
    public boolean showDialog(Vector vsee1,JTextField jt)//, Office off) 
    {  
        
        setSize(300,400); 
        setTitle("�����¡�ä��� ICD10"); 
        this.setLanguage("");
        Toolkit thekit = getToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//thekit.getScreenSize();
        setBounds((screenSize.width + this.getSize().width + 200 )/4,(screenSize.height + this.getSize().height + 50)/4, this.getSize().width,this.getSize().height);       
        setLocation((screenSize.width-getSize().width)/2, (screenSize.height-getSize().height)/2);
        setComponent(jt);
        showTable(vsee1);
        vsee = vsee1;
        setVisible(true);
        if(actionCommand)
        {
              return true;
        }
        System.gc();
        return false;
    }        
    
    public void setLanguage(String msg)
    {      
        GuiLang.setLanguage(jButtonCancel);
        GuiLang.setLanguage(col_jTableSee);
    }     
    public void setComponent(JTextField jtf)
    {
        jt = jtf;
    }
}
