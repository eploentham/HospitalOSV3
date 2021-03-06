/*
 * PanelSDSetupClinic.java
 *
 * Created on March 31, 2009, 3:11 PM
 */

package com.hosv3.gui.panel.detail;

import com.hospital_os.object.Clinic;
import com.hospital_os.usecase.connection.Persistent;
import com.hosv3.utility.Constant;
import com.hosv3.control.HosControl;
import com.hosv3.control.SetupControl;
import com.hosv3.gui.component.PanelSetupImp;
import com.hosv3.utility.GuiLang;
import com.hosv3.utility.connection.UpdateStatus;
import java.util.Vector;

/**
 *
 * @author  Administrator
 */
public class PanelSDClinic extends javax.swing.JPanel  implements PanelSetupImp{
    UpdateStatus theUS;
    SetupControl theSetupControl;
    private Clinic theClinic = new Clinic();
    Vector billinggroup = new Vector();
    Vector clinic = new Vector();
    int offset = 24;
    int next = 0;
    int prev = 0;
    int saved = 0; // 0 ��� �������ö insert�� 1 ��� insert ��
    String[] col = {"����","����"};
    /** Creates new form PanelSDSetupClinic */
    public PanelSDClinic() {
        initComponents();
        setLanguage();
    }

    public PanelSDClinic(HosControl hc, UpdateStatus us) {
        initComponents();
        setLanguage();
        setControl(hc,us);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTextAreaName = new javax.swing.JTextArea();
        jCheckBox1 = new javax.swing.JCheckBox();

        setLayout(new java.awt.GridBagLayout());

        jPanel2.setMinimumSize(new java.awt.Dimension(300, 150));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 150));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(defaultFont1);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/hosv3/property/thai"); // NOI18N
        jLabel1.setText(bundle.getString("CODE")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel1, gridBagConstraints);

        jTextFieldCode.setFont(defaultFont1);
        jTextFieldCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanel2.add(jTextFieldCode, gridBagConstraints);

        jLabel2.setFont(defaultFont1);
        jLabel2.setText(bundle.getString("NAME")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        jScrollPane21.setMaximumSize(new java.awt.Dimension(100, 45));
        jScrollPane21.setMinimumSize(new java.awt.Dimension(100, 45));
        jScrollPane21.setPreferredSize(new java.awt.Dimension(100, 45));

        jTextAreaName.setFont(defaultFont1);
        jTextAreaName.setLineWrap(true);
        jTextAreaName.setMinimumSize(new java.awt.Dimension(100, 45));
        jTextAreaName.setPreferredSize(new java.awt.Dimension(100, 45));
        jScrollPane21.setViewportView(jTextAreaName);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel2.add(jScrollPane21, gridBagConstraints);

        jCheckBox1.setFont(defaultFont1);
        jCheckBox1.setText(bundle.getString("ACTIVE")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        jPanel2.add(jCheckBox1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodeActionPerformed
        
    }//GEN-LAST:event_jTextFieldCodeActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JTextArea jTextAreaName;
    private javax.swing.JTextField jTextFieldCode;
    // End of variables declaration//GEN-END:variables
    public void setEnabled(boolean var)
    {   
        jTextFieldCode.setEditable(var);
        jTextAreaName.setEditable(var);
        //jButtonSave.setEnabled(var);
        jCheckBox1.setEnabled(var);
        //jButtonDel.setEnabled(var);
    }
    
    public void clearAll() {
        this.setTheClinic(new Clinic());
    }

    public Persistent getXPer() {
        return this.getTheClinic();
    }

    public void setXPer(Persistent x) {
        this.setTheClinic((Clinic)x);
    }

    public void setLanguage() {
        // GuiLang.setLanguage(jLabel3);
        //GuiLang.setLanguage(jLabelICD9code);
        //GuiLang.setLanguage(jButtonSearch);
        //GuiLang.setLanguage(jCheckBoxS);
        GuiLang.setLanguage(jLabel1);
        GuiLang.setLanguage(jLabel2);
        GuiLang.setLanguage(jCheckBox1);
        //GuiLang.setLanguage(jButtonSave);
        GuiLang.setLanguage(col);
    }

    public void setControl(HosControl hc, UpdateStatus us) {
        theUS = us;
        theSetupControl = hc.theSetupControl;
        hc.theHS.theSetupSubject.addpanelrefrash(this);
        hc.theHS.theSetupSubject.addForLiftAttach(this);
        setupLookup();
        setEnabled(false);
    }

    public void setupLookup() {
       // throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean deleteXPer(Persistent x) {
        return (this. theSetupControl.deleteClinic((Clinic)x)>0);
    }

    public boolean saveXPer(Persistent x) {
         return (this.theSetupControl.saveClinic((Clinic)x)>0);
    }

    public Vector listXPer(String key, String active, int offset) {
        return theSetupControl.listClinicAll(key,active);
    }

    public boolean isActiveVisible() {
        return true;
    }

    public static String TITLE = Constant.getTextBundle("��ª��ͻ������ä");
    public String getTitle() {
        return TITLE;
    }

    public Clinic getTheClinic() {
        if(theClinic==null)
            theClinic = new Clinic();
        theClinic.clinic_id = jTextFieldCode.getText();
        theClinic.name = jTextAreaName.getText();
        if(jCheckBox1.isSelected())
            theClinic.active = "1";
        else
            theClinic.active = "0";
        return theClinic;
    }

    public void setTheClinic(Clinic item) {
        theClinic = item;
        jTextFieldCode.setText(theClinic.clinic_id);
        jTextAreaName.setText(theClinic.name);
        
        if((theClinic.active)!=null&&(theClinic.active).equals("1"))
        jCheckBox1.setSelected(true);
        else
        jCheckBox1.setSelected(false);
    }
    
    public boolean isStartVisible() {
        return false;
    }
    

}
