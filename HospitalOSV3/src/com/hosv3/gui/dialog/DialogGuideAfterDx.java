/*
 * DialogGuideAfterDx.java
 *
 * Created on 26 �á�Ҥ� 2547, 13:48 �.
 */
package com.hosv3.gui.dialog;

import com.hosv3.control.*;
import com.hosv3.utility.*;
import com.hosv3.utility.connection.*;
import com.hospital_os.object.*; 
//import com.hosv3.gui.panel.setup.*;
import com.hosv3.subject.*;
import java.util.*;
import java.awt.*;

/**
 *  
 * @author  amp
 */
public class DialogGuideAfterDx extends javax.swing.JFrame {    
    public boolean actionCommand = false;  
    //control 
    HosControl theHC;
    UpdateStatus theUS;
    private SetupControl theSetupControl;
    private SetupSubject theSetupSubject;
    private VisitControl theVisitControl;
    //vector
    private Vector vGuide; //�� Object �ͧ DxTemplate
    //object
    private Visit theVisit = new Visit();

    private GuideAfterDxTransaction theGuideAfterDxTransaction = new GuideAfterDxTransaction();
    PanelSetupSearchSub  psep;
    //other
    private String Temp;
    public static boolean closeDialog = false;
    /** Creates new form DialogHistoryPatient */    
    public DialogGuideAfterDx(HosControl hc,UpdateStatus us
    , Visit visit, Vector vGuide) {
        this.setIconImage(us.getJFrame().getIconImage());
       // super(parent, modal);
        this.vGuide = vGuide;
        this.theVisit = visit;
        theHC = hc;
        theUS = us;
        this.theSetupControl = hc.theSetupControl;
        this.theVisitControl = hc.theVisitControl;
        initComponents();         
        setDialog();
        showGuide();        
        this.jButtonPrintGuide.setVisible(false);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaGuide = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonClose = new javax.swing.JButton();
        jButtonAddGuide = new javax.swing.JButton();
        jButtonPrintGuide = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(new javax.swing.border.TitledBorder("\u0e04\u0e33\u0e41\u0e19\u0e30\u0e19\u0e33\u0e2b\u0e25\u0e31\u0e07\u0e15\u0e23\u0e27\u0e08"));
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseReleased(evt);
            }
        });

        jTextAreaGuide.setLineWrap(true);
        jScrollPane1.setViewportView(jTextAreaGuide);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel3.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 0, 2);
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jButtonSave.setText("\u0e1a\u0e31\u0e19\u0e17\u0e36\u0e01");
        jButtonSave.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSave.setMaximumSize(new java.awt.Dimension(72, 24));
        jButtonSave.setMinimumSize(new java.awt.Dimension(72, 24));
        jButtonSave.setPreferredSize(new java.awt.Dimension(72, 24));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel2.add(jButtonSave, gridBagConstraints);

        jButtonClose.setText("\u0e1b\u0e34\u0e14");
        jButtonClose.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonClose.setMaximumSize(new java.awt.Dimension(72, 24));
        jButtonClose.setMinimumSize(new java.awt.Dimension(72, 24));
        jButtonClose.setPreferredSize(new java.awt.Dimension(72, 24));
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel2.add(jButtonClose, gridBagConstraints);

        jButtonAddGuide.setText("+");
        jButtonAddGuide.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonAddGuide.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonAddGuide.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonAddGuide.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonAddGuide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddGuideActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButtonAddGuide, gridBagConstraints);

        jButtonPrintGuide.setText("\u0e1e\u0e34\u0e21\u0e1e\u0e4c");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel2.add(jButtonPrintGuide, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 2);
        jPanel1.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }//GEN-END:initComponents
    private void jButtonAddGuideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddGuideActionPerformed
        addGuideAfterDx();
    }//GEN-LAST:event_jButtonAddGuideActionPerformed
    private void jScrollPane1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseReleased
        
    }//GEN-LAST:event_jScrollPane1MouseReleased
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        actionCommand = true;  
        saveGuide();        
    }//GEN-LAST:event_jButtonSaveActionPerformed
    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        this.closeDialog = true;
        dispose();
    }//GEN-LAST:event_jButtonCloseActionPerformed
    private void listOffice1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listOffice1KeyReleased
    }//GEN-LAST:event_listOffice1KeyReleased
    private void listOffice1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listOffice1MouseReleased
        
    }//GEN-LAST:event_listOffice1MouseReleased
    private void comboBoxAmphur1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxAmphur1ActionPerformed
    }//GEN-LAST:event_comboBoxAmphur1ActionPerformed
    private void comboBoxChangwat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxChangwat1ActionPerformed
    }//GEN-LAST:event_comboBoxChangwat1ActionPerformed
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        dispose();
    }//GEN-LAST:event_closeDialog
     
    /**
     *show ���йӵ�� Dx
     */
    private void showGuide()
    {
        this.jTextAreaGuide.setEnabled(false);
        this.Temp = "";
        String Temp1 = "";        
        if(this.theVisitControl.listGuideByVisitId(this.theVisit.getObjectId())!=null)
        {
            this.Temp = this.theVisitControl.listGuideByVisitId(this.theVisit.getObjectId()).guide;
            if(this.vGuide==null)
            {
                if(this.Temp.equals(""))
                {
                    this.jTextAreaGuide.setText("1.�Ѻ��зҹ�ҵ��Ἱ����ѡ�Ңͧᾷ��\n2.�Ѻ��зҹ����÷���ջ���ª��\n3.��þѡ��͹�����§��\n4.�ҡ���ҡ�üԴ���Ԥ���Ҿ�ᾷ��");      
                    this.Temp = this.jTextAreaGuide.getText();
                }
                else
                    this.jTextAreaGuide.setText(this.Temp);  
            }
            else
            {
                for(int i=0; i<this.vGuide.size(); i++)
                {
                    if(Temp1.equals(""))
                    {
                        Temp1 = ((DxTemplate)this.vGuide.get(i)).guide_after_dx;                        
                    }
                    else
                    {
                        Temp1 = Temp1 + "\n-----------------------------\n" + ((DxTemplate)this.vGuide.get(i)).guide_after_dx;                                           
                    }
                }
                this.jTextAreaGuide.setText(Temp1);
            }
        }
        else
        {
            if(this.vGuide==null)
            {
                this.jTextAreaGuide.setText("1.�Ѻ��зҹ�ҵ��Ἱ����ѡ�Ңͧᾷ��\n2.�Ѻ��зҹ����÷���ջ���ª��\n3.��þѡ��͹�����§��\n4.�ҡ���ҡ�üԴ���Ԥ���Ҿ�ᾷ��");   
                this.Temp = this.jTextAreaGuide.getText();
            }
            else
            {
                for(int i=0; i<this.vGuide.size(); i++)
                {
                    if(this.Temp.equals(""))
                        this.Temp = ((DxTemplate)this.vGuide.get(i)).guide_after_dx;
                    else
                        this.Temp = this.Temp + "\n-----------------------------\n" + ((DxTemplate)this.vGuide.get(i)).guide_after_dx;
                }
                this.jTextAreaGuide.setText(this.Temp);
            }
        }             
    }
    
    private void addGuideAfterDx()
    {   
        
         if(psep==null)
        {
            psep = new PanelSetupSearchSub(theHC,theUS,10);
            psep.setTitle("���й���ѧ��Ǩ");
       }
        
       
        if(psep.showSearch())
            psep = null;
        /*
        JDialog dlg = new JDialog(this.theParent);
        PanelSetupSearchSub  psep;
        psep = new PanelSetupSearchSub(this.theSetupControl,this.theSetupSubject,10);       
                
        dlg.setSize(300,450);
        dlg.setTitle("���й���ѧ��Ǩ");
        dlg.getContentPane().add(psep, java.awt.BorderLayout.CENTER);
        
        dlg.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dlg.addWindowListener(new java.awt.event.WindowAdapter() 
        {
            public void windowClosing(java.awt.event.WindowEvent evt) 
            {
             
            }
        });
        
        Toolkit thekit = dlg.getToolkit();
        Dimension screenSize = thekit.getDefaultToolkit().getScreenSize();
        dlg.setLocation((screenSize.width-dlg.getSize().width)/2, (screenSize.height-dlg.getSize().height)/2);
        dlg.setVisible(true);
        dlg=null;  
        */
        System.gc();        
    }

    public void notifylistGuide(GuideAfterDx guideAfterDx) 
    {
        if(this.Temp.equalsIgnoreCase(""))
            this.Temp = guideAfterDx.description;
        else
            this.Temp = this.Temp + "\n-----------------------------\n" + guideAfterDx.description;      
        this.jTextAreaGuide.setText(this.Temp);        
    }  
    
    private void saveGuide()
   {
        if(this.theVisitControl.listGuideByVisitId(this.theVisit.getObjectId()) != null)
        {
            this.theGuideAfterDxTransaction = this.theVisitControl.listGuideByVisitId(this.theVisit.getObjectId());
            this.theGuideAfterDxTransaction.guide = this.Temp;
            this.theVisitControl.saveGuideDxTR(this.theGuideAfterDxTransaction); 
        }
        else
        {
            this.theGuideAfterDxTransaction = new GuideAfterDxTransaction();
            this.theGuideAfterDxTransaction.visit_id = this.theVisit.getObjectId();
            this.theGuideAfterDxTransaction.guide = this.Temp;
            this.theVisitControl.saveGuideDxTR(this.theGuideAfterDxTransaction); 
        }
    }
    
    public void setDialog()
    {
        this.setSize(300,400);
        this.setTitle("���й���ѧ��Ǩ");    
        this.setLanguage("");
        Toolkit thekit = this.getToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();       
        this.setLocation((screenSize.width-this.getSize().width)/2, (screenSize.height-this.getSize().height)/2);
        
    }
    
    public void showDialog(Visit visit,Vector vGuide) {
        this.vGuide = vGuide;
        this.theVisit = visit;
        this.setVisible(true);
        showGuide();        
    }
    public boolean isClose()
    {
        return closeDialog;
    }
/*-----------------------------------------------------------*/    
    public static boolean showDialog(HosControl hc,UpdateStatus us,Visit visit
    ,Vector vGuide)
    {
        DialogGuideAfterDx dlg = new DialogGuideAfterDx(hc,us,visit,vGuide);        
        dlg.setSize(300,400);
        dlg.setTitle("���й���ѧ��Ǩ");    
        dlg.setLanguage("");
        Toolkit thekit = dlg.getToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();       
        dlg.setLocation((screenSize.width-dlg.getSize().width)/2, (screenSize.height-dlg.getSize().height)/2);
        dlg.setVisible(true);
        if(dlg.actionCommand)
        {     
              return true;
        }
        dlg=null;
        System.gc();
        return false;
    }  
    
     public void setLanguage(String msg)
    {
        GuiLang.setLanguage(jButtonPrintGuide);
	GuiLang.setLanguage(jButtonClose);
	GuiLang.setLanguage(jButtonSave);
	GuiLang.setTextBundle(jPanel3);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddGuide;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonPrintGuide;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaGuide;
    // End of variables declaration//GEN-END:variables
    
}
