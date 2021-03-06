/*
 * DialogSetPrefix.java
 *
 * Created on 8 ����Ҿѹ�� 2547, 17:32 �.
 */
package com.hosv3.gui.dialog;
import java.util.*;
import java.awt.*;

import com.hospital_os.object.*;
import com.hospital_os.utility.ComboFix;
import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.TaBleModel;

import com.hosv3.control.*;
import com.hosv3.control.lookup.R53PersonPrefixLookup;
import com.hosv3.utility.*;
import com.hosv3.subject.*;
import com.hosv3.utility.connection.*;
/**
 *
 * @author  amp
 */
public class DialogSetPrefix extends javax.swing.JDialog
{
    HosControl theHC;
    public boolean actionCommand = false;
    LookupControl theLookupControl;
    PatientControl thePatientControl;
    public PatientSubject thePatientSubject;
    Prefix thePrefix;
    private Vector vPrefix;
    /** Creates new form DialogSetPrefix */
    public DialogSetPrefix(HosControl hc,UpdateStatus us)
    {   
        super(us.getJFrame(), true);
        theHC = hc;
        theLookupControl = hc.theLookupControl;
        thePatientControl = hc.thePatientControl;   
        initComponents();
        setDialog();
        setLanguage("");
        this.jComboBoxLookup1.setControl(null,new R53PersonPrefixLookup(
            theHC.theConnectionInf),new ComboFix());
    }
    
    
   
    
    private void setDataAll(Vector v,String pf)
    {   
        setPrefixV(v);
        thePrefix = theLookupControl.readPrefixById(pf);
        if(vPrefix != null)
        {
            ComboboxModel.initComboBox(jComboBoxSexP, theLookupControl.listGender());
            //ComboboxModel.initComboBox(jComboBoxTlock, theLookupControl.listTlock());
        }
        if(thePrefix!=null)
        {
            Gutil.setGuiData(jComboBoxSexP,thePrefix.sex);
            Gutil.setGuiData(jComboBoxSexP,thePrefix.tlock);
            Gutil.setGuiData(this.jTextFieldPrename,thePrefix.description);
            Gutil.setGuiData(this.jComboBoxLookup1,thePrefix.prefix_id53);
        }
    }
        
    public void setPrefixV(Vector v){
         
         vPrefix = v;
       if(v==null)
       {
            jTable1.setModel(null);
            return;
       }
       String[] column = {"�ӹ�˹�Ҫ���","active"};
       GuiLang.setLanguage(column);
       TaBleModel tm = new TaBleModel(column,vPrefix.size());
       for(int i=0,size=vPrefix.size();i<size;i++)
       {
           Prefix pd = (Prefix)vPrefix.get(i);
               
           tm.setValueAt(pd.description,i,0);
           if(pd.active.equals("1"))
               tm.setValueAt("��ҹ",i,1);
           else
               tm.setValueAt("¡��ԡ",i,1);
       }
       jTable1.setModel(tm);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabelPrename = new javax.swing.JLabel();
        jLabelSex = new javax.swing.JLabel();
        jComboBoxSexP = new javax.swing.JComboBox();
        jLabelTlock = new javax.swing.JLabel();
        jComboBoxTlock = new javax.swing.JComboBox();
        jCheckBoxActive = new javax.swing.JCheckBox();
        jTextFieldPrename = new javax.swing.JTextField();
        jComboBoxLookup1 = new com.hosv3.gui.component.HosComboBox();
        jLabelInfection = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new com.hosv3.gui.component.HJTableSort();

        jLabel1.setText("jLabel1");

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButtonSave, gridBagConstraints);

        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif"))); // NOI18N
        jButtonAdd.setMinimumSize(new java.awt.Dimension(26, 26));
        jButtonAdd.setPreferredSize(new java.awt.Dimension(26, 26));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        jButtonAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButtonAddKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jButtonAdd, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 0, 2);
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabelPrename.setText("Prename");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel3.add(jLabelPrename, gridBagConstraints);

        jLabelSex.setText("Sex");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel3.add(jLabelSex, gridBagConstraints);

        jComboBoxSexP.setMinimumSize(new java.awt.Dimension(31, 21));
        jComboBoxSexP.setPreferredSize(new java.awt.Dimension(31, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 2);
        jPanel3.add(jComboBoxSexP, gridBagConstraints);

        jLabelTlock.setText("Tlock");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 0);
        jPanel3.add(jLabelTlock, gridBagConstraints);

        jComboBoxTlock.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "�����", "��������" }));
        jComboBoxTlock.setMinimumSize(new java.awt.Dimension(31, 21));
        jComboBoxTlock.setPreferredSize(new java.awt.Dimension(31, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 2);
        jPanel3.add(jComboBoxTlock, gridBagConstraints);

        jCheckBoxActive.setText("active");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 2);
        jPanel3.add(jCheckBoxActive, gridBagConstraints);

        jTextFieldPrename.setMinimumSize(new java.awt.Dimension(120, 21));
        jTextFieldPrename.setPreferredSize(new java.awt.Dimension(120, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 0, 2);
        jPanel3.add(jTextFieldPrename, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
        jPanel3.add(jComboBoxLookup1, gridBagConstraints);

        jLabelInfection.setText("����ҵðҹ��§ҹ");
        jLabelInfection.setToolTipText("��§ҹ 18 ��� 53");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 0);
        jPanel3.add(jLabelInfection, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 0, 2);
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseReleased(evt);
            }
        });

        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel4, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if(evt.getKeyCode()==evt.VK_UP ||evt.getKeyCode()==evt.VK_DOWN)
            jTable1MouseReleased(null);
    }//GEN-LAST:event_jTable1KeyReleased

    private void jButtonAddKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonAddKeyReleased

    }//GEN-LAST:event_jButtonAddKeyReleased

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        this.jTextFieldPrename.setEnabled(true);
        this.setPrefix(new Prefix());
    }//GEN-LAST:event_jButtonAddActionPerformed
                       
	private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        actionCommand = true;
        theHC.theLookupControl.savePrefix(getPrefix());
        this.setPrefixV(theHC.theLookupControl.listPrefixAll());
	}//GEN-LAST:event_jButtonSaveActionPerformed

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        thePrefix = (Prefix)vPrefix.get(jTable1.getSelectedRow());
        setPrefix(thePrefix);   
        this.jTextFieldPrename.setEnabled(false);
    }//GEN-LAST:event_jTable1MouseReleased
      
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jScrollPane1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseReleased
   /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      //  new DialogSetPrefix(new javax.swing.JFrame(), true).setVisible(true);
    } 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JCheckBox jCheckBoxActive;
    private com.hosv3.gui.component.HosComboBox jComboBoxLookup1;
    private javax.swing.JComboBox jComboBoxSexP;
    private javax.swing.JComboBox jComboBoxTlock;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelInfection;
    private javax.swing.JLabel jLabelPrename;
    private javax.swing.JLabel jLabelSex;
    private javax.swing.JLabel jLabelTlock;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private com.hosv3.gui.component.HJTableSort jTable1;
    private javax.swing.JTextField jTextFieldPrename;
    // End of variables declaration//GEN-END:variables
        
    public Prefix getPrefix()
    {   
        if(thePrefix==null)
            thePrefix = new Prefix();
        thePrefix.description = this.jTextFieldPrename.getText();
        thePrefix.sex = Gutil.getGuiData(jComboBoxSexP);
        thePrefix.tlock = (jComboBoxTlock.getSelectedIndex()==0)?"3":"0";
        thePrefix.active = Gutil.isSelected(jCheckBoxActive);
        thePrefix.prefix_id53 = Gutil.getGuiData(this.jComboBoxLookup1);
        return thePrefix;
    }
    
/*-----------------------------------------------------------*/    
    public void showDialog(Vector vprefix,String pf)
    {
        this.setDataAll(vprefix,pf);
        this.setVisible(true);
    } 
    
    private void setDialog()
    {
        this.setSize(750,250);
        this.setTitle(Constant.getTextBundle("�׹�ѹ��èѺ���ӹ�˹����"));  
        this.setLanguage("");
        Toolkit thekit = this.getToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width-this.getSize().width)/2, (screenSize.height-this.getSize().height)/2);        
       
    }
      public void setLanguage(String msg)
      {        
            GuiLang.setLanguage(jLabelPrename);
            GuiLang.setLanguage(jLabelSex);
            GuiLang.setLanguage(jLabelTlock);
            GuiLang.setLanguage(jButtonSave);
            GuiLang.setLanguage(jLabelInfection);
      } 

    private void setPrefix(Prefix prefix) {
        
        thePrefix = prefix;
        this.jTextFieldPrename.setText(thePrefix.description);
        Gutil.setGuiData(jComboBoxSexP,thePrefix.sex);
        jComboBoxTlock.setSelectedIndex(1);
        if(thePrefix.tlock.equals("3"))
            jComboBoxTlock.setSelectedIndex(0);
        if("1".equals(thePrefix.active))
            jCheckBoxActive.setSelected(true);
        else
            jCheckBoxActive.setSelected(false);
        Gutil.setGuiData(this.jComboBoxLookup1,thePrefix.prefix_id53);
    }
      
}
