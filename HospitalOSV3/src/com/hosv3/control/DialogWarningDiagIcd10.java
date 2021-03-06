/*
 * DialogPasswdForCancelReceipt.java
 *
 * Created on 10 �ѹ��¹ 2547, 18:33 �.
 */
package com.hosv3.control;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import com.hosv3.utility.*;
import com.hosv3.object.*;
import com.hosv3.control.lookup.*;
import com.hospital_os.object.*;
import com.hosv3.utility.connection.*;
import com.hosv3.utility.Constant;

/**
 *
 * @author amp
 */
public class DialogWarningDiagIcd10 extends javax.swing.JPanel {
    
    UpdateStatus theUS;
    HosObject theHO;
    JDialog theJD;
    LookupControl theLookupControl; 
    private DiagIcd10 theDiagIcd10; 
    String auth = "";
    public boolean actionCommand = false;    
    Vector vDiagIcd10 = null;
    boolean ret = false;

   public DialogWarningDiagIcd10(HosObject ho,LookupControl hc,UpdateStatus us)
    {
        setControl(ho,hc,us);
        initComponents();
        this.setDialog();
        setLanguage();
   }
   
   public void setControl(HosObject ho,LookupControl hc,UpdateStatus us){
        theLookupControl = hc;
        theUS = us;
        theHO = ho;
        auth = theHO.theEmployee.authentication_id;
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
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonOk = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        hosComboBoxAccident = new com.hosv3.gui.component.HosComboBox();
        jLabelNameAccidentTypeC = new javax.swing.JLabel();
        jLabelNameAccidentTypeC1 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(300, 53));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 53));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jButtonOk.setText("OK");
        jButtonOk.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonOk.setMaximumSize(new java.awt.Dimension(67, 24));
        jButtonOk.setMinimumSize(new java.awt.Dimension(67, 24));
        jButtonOk.setPreferredSize(new java.awt.Dimension(67, 24));
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButtonOk, gridBagConstraints);

        jButtonCancel.setText("Cancel");
        jButtonCancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonCancel.setMaximumSize(new java.awt.Dimension(67, 24));
        jButtonCancel.setMinimumSize(new java.awt.Dimension(67, 24));
        jButtonCancel.setPreferredSize(new java.awt.Dimension(67, 24));
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jPanel2.add(jButtonCancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel1.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 3, 2, 0);
        jPanel1.add(hosComboBoxAccident, gridBagConstraints);

        jLabelNameAccidentTypeC.setText("\u0e25\u0e31\u0e01\u0e29\u0e13\u0e30\u0e01\u0e32\u0e23\u0e40\u0e01\u0e34\u0e14\u0e2d\u0e38\u0e1a\u0e31\u0e15\u0e34\u0e40\u0e2b\u0e15\u0e38");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabelNameAccidentTypeC, gridBagConstraints);

        jLabelNameAccidentTypeC1.setText("\u0e01\u0e23\u0e38\u0e13\u0e32\u0e40\u0e25\u0e37\u0e2d\u0e01\u0e02\u0e49\u0e2d\u0e21\u0e39\u0e25\u0e01\u0e32\u0e23\u0e40\u0e01\u0e34\u0e14\u0e2d\u0e38\u0e1a\u0e31\u0e15\u0e34\u0e40\u0e2b\u0e15\u0e38");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabelNameAccidentTypeC1, gridBagConstraints);

        add(jPanel1, java.awt.BorderLayout.CENTER);

    }
    // </editor-fold>//GEN-END:initComponents

    /**add new method by noom 25/05/48 when user enter its,program will check password on time*/        
	private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        actionCommand = false;            
        if(theJD!=null)
            theJD.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

	private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed
        actionCommand = true;  
        saveDiagnosisIcd10();  
        if(theJD!=null)
            theJD.dispose();
    }//GEN-LAST:event_jButtonOkActionPerformed
/** Closes the dialog */
	private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        ret = true;
        if(theJD!=null)
            theJD.dispose();
    }//GEN-LAST:event_closeDialog
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hosv3.gui.component.HosComboBox hosComboBoxAccident;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JLabel jLabelNameAccidentTypeC;
    private javax.swing.JLabel jLabelNameAccidentTypeC1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
        
    /*-----------------------------------------------------------*/
    private void setDialog() 
    {
        theJD = new JDialog(theUS.getJFrame(),true);
        theJD.setTitle(Constant.getTextBundle("�ѹ�֡������ DiagICD10 �غѵ��˵�"));
        theJD.getContentPane().add(this);  
        theJD.setSize(350,110); 
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width-theJD.getSize().width)/2
                , (screenSize.height-theJD.getSize().height)/2);            
    }    
     
    public boolean showDialog(Vector diag) 
    {
        ret = false;
        this.vDiagIcd10 = diag;
        initData();
        theJD.setVisible(true);
        return ret;
    }
    
    private void initData()
    {
        hosComboBoxAccident.setControl(null,new AccidentTypeLookup(theLookupControl),new AccidentType2());
        hosComboBoxAccident.refresh();
    }
    
    private void updateGODiagnosisIcd10()
    {
       theDiagIcd10 = theHO.initDiagIcd10();
       for(int i = 0; i < vDiagIcd10.size(); i++)
       {
           DiagIcd10 diagicd10 = (DiagIcd10)vDiagIcd10.get(i);
           if(diagicd10.icd10_code.startsWith("S")
            || diagicd10.icd10_code.startsWith("T"))
           {
               String icd10 = hosComboBoxAccident.getText();
               theDiagIcd10.icd10_code = theLookupControl.readAccidentTypeById(icd10).accident_type_number;
               theDiagIcd10.clinic_kid = diagicd10.clinic_kid;
               theDiagIcd10.diag_icd10_staff_record = diagicd10.diag_icd10_staff_record;
               theDiagIcd10.diag_icd10_record_date_time = theLookupControl.getTextCurrentDateTime();
               theDiagIcd10.diagnosis_date = diagicd10.diagnosis_date;
               theDiagIcd10.type = Dxtype.getExternalCauseofInjuryDiagnosis();
               theDiagIcd10.doctor_kid = diagicd10.doctor_kid;
               theDiagIcd10.type = "5";
           }
           vDiagIcd10.add(theDiagIcd10);
           break;
       }
                
    }
    
    private void saveDiagnosisIcd10()
    {
        updateGODiagnosisIcd10();
        ret = true;
    }
    
    public void setLanguage()
    {            
	GuiLang.setLanguage(jButtonOk);
	GuiLang.setLanguage(jLabelNameAccidentTypeC);
	GuiLang.setLanguage(jLabelNameAccidentTypeC1);
	GuiLang.setLanguage(jButtonCancel);
    }
}
