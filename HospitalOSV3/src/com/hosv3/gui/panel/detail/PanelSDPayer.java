/*
 * PanelSDPayer.java
 *
 * Created on 11 เมษายน 2550, 17:32 น.
 */

package com.hosv3.gui.panel.detail;

import com.hospital_os.object.Payer;
import com.hospital_os.usecase.connection.Persistent;
import com.hosv3.utility.Constant;
import com.hosv3.control.HosControl;
import com.hosv3.control.LookupControl;
import com.hosv3.control.SetupControl;
import com.hosv3.gui.component.PanelSetupImp;
import com.hosv3.utility.GuiLang;
import com.hosv3.utility.connection.UpdateStatus;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author  Aut
 */
public class PanelSDPayer extends javax.swing.JPanel implements PanelSetupImp {
    SetupControl theSetupControl;
    LookupControl theLookupControl;
    UpdateStatus theUS;
    public static final String TITLE = Constant.getTextBundle("PANEL_SETUP_PAYER");
    Payer thePayer;
    
    /** Creates new form PanelSDPayer */
    public PanelSDPayer(HosControl hc, UpdateStatus us) {
        initComponents();
        setControl(hc,us);
        setLanguage();
    }

    public void setXPer(Persistent x) {
        thePayer = (Payer)x;
        this.jTextFieldCode.setText(thePayer.payer_id);
        this.jTextAreaName.setText(thePayer.description);
//        Payer bgi = new Payer();
//        for(int i =0 ; i< payer.size() ; i++)
//        {           
//             if(of == ((Payer)payer.get(i)).payer_id)
//                bgi = (Payer)payer.get(i);
//        }
//        this.thePayer = bgi;
        if(this.thePayer.active.equals("1"))
            this.jCheckBox1.setSelected(true);
        else
            this.jCheckBox1.setSelected(false);
        setEnabled(true);
    }
    
    public void setEnabled(boolean var)
    {
        this.jTextFieldCode.setEditable(var);
        this.jTextAreaName.setEditable(var);
        this.jTextFieldCode.setEnabled(var);
        this.jTextAreaName.setEnabled(var);
        this.jCheckBox1.setEnabled(var);
    }

    public boolean saveXPer(Persistent x) {
        if((!this.thePayer.payer_id.equals(""))&&(!this.thePayer.description.equals("")))
       {
          Payer py = this.theSetupControl.listPayerByCode(this.thePayer.getObjectId());
          if(py==null)
          {
                if(this.jCheckBox1.isSelected())
                    this.thePayer.active = "1";
                else
                    this.thePayer.active = "0";    
                if(this.thePayer.getObjectId() == null)
                {  
                    this.theSetupControl.savePayer(this.thePayer);
                }
                else
                {   
                    this.theSetupControl.editPayer(this.thePayer);
                }
          }
          else
          {
              if(this.thePayer.getObjectId()==null)
              {
                  JOptionPane.showMessageDialog(this,Constant.getTextBundle("ไม่สามารถบันทึกรหัสซ้ำได้ "),Constant.getTextBundle("เตือน "),JOptionPane.OK_OPTION);       
              }
              else
              {
                  if(this.thePayer.getObjectId().equals(py.getObjectId()))
                  {
                      this.theSetupControl.editPayer(this.thePayer);
                  }
                  else
                  {
                      JOptionPane.showMessageDialog(this,Constant.getTextBundle("ไม่สามารถบันทึกรหัสซ้ำได้ "),Constant.getTextBundle("เตือน "),JOptionPane.OK_OPTION);       
                  }
              }
              py = null;
          }
                           
                 setEnabled(true);
                 //searchPayer();
        }
        else
        {
            JOptionPane.showMessageDialog(this,Constant.getTextBundle("ไม่สามารถบันทึกข้อมูลเป็นค่าว่างได้ "),Constant.getTextBundle("เตือน "),JOptionPane.OK_OPTION);       
        }
        return true;
    }

    public boolean deleteXPer(Persistent x) {
        boolean confirm = theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้ อาจเกิดผลกระทบกับข้อมูลในฐานข้อมูลได้"), UpdateStatus.WARNING);
        if(!confirm) return false;
        this.theSetupControl.deletePayer(this.thePayer);
        
        setEnabled(false);
        clearAll();
        //searchPayer();
        return true;
    }
    
    public Vector listXPer(String key, String active, int offset) {     
       return this.theSetupControl.listPayer(key, active);
    }

    public void setControl(HosControl hc, UpdateStatus us) {
        theSetupControl = hc.theSetupControl;
        theLookupControl = hc.theLookupControl;
        theUS = us;
    }

    public void setupLookup() {
    }

    public void setLanguage() {
        //GuiLang.setLanguage(jLabel3);
        GuiLang.setLanguage(jLabel1);        
        GuiLang.setLanguage(jLabel2);    
        GuiLang.setLanguage(jCheckBox1);    
    }

//    public boolean isButtonDelVisible() {
//        return true;
//    }

    public boolean isActiveVisible() {
        return true;
    }

    public Persistent getXPer() {
        if(thePayer==null) 
            thePayer = new Payer();
        this.thePayer.payer_id = this.jTextFieldCode.getText();
        this.thePayer.description = this.jTextAreaName.getText();
       if((!this.thePayer.payer_id.equals(""))&&(!this.thePayer.description.equals("")))
       {
          Payer py = this.theSetupControl.listPayerByCode(this.thePayer.getObjectId());
          if(py==null)
          {
                if(this.jCheckBox1.isSelected())
                    this.thePayer.active = "1";
                else
                    this.thePayer.active = "0"; 
          }
       }
        return thePayer;
    }

    public String getTitle() {
        return Constant.getTextBundle("ผู้ชำระเงิน");
    }

    public void clearAll() {
        this.jTextFieldCode.setText("");
        this.jTextAreaName.setText("");
        this.jCheckBox1.setSelected(true);
        this.thePayer = new Payer();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jTextFieldCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTextAreaName = new javax.swing.JTextArea();
        jCheckBox1 = new javax.swing.JCheckBox();

        setLayout(new java.awt.GridBagLayout());

        setMinimumSize(new java.awt.Dimension(300, 114));
        setPreferredSize(new java.awt.Dimension(300, 114));
        jLabel1.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("CODE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 11, 0, 1);
        add(jLabel1, gridBagConstraints);

        jTextFieldCode.setMaximumSize(new java.awt.Dimension(150, 21));
        jTextFieldCode.setMinimumSize(new java.awt.Dimension(150, 21));
        jTextFieldCode.setPreferredSize(new java.awt.Dimension(150, 21));
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
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 11);
        add(jTextFieldCode, gridBagConstraints);

        jLabel2.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("NAME"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 11, 0, 0);
        add(jLabel2, gridBagConstraints);

        jScrollPane21.setMaximumSize(new java.awt.Dimension(150, 48));
        jScrollPane21.setMinimumSize(new java.awt.Dimension(150, 48));
        jScrollPane21.setPreferredSize(new java.awt.Dimension(150, 48));
        jTextAreaName.setLineWrap(true);
        jTextAreaName.setWrapStyleWord(true);
        jScrollPane21.setViewportView(jTextAreaName);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 11);
        add(jScrollPane21, gridBagConstraints);

        jCheckBox1.setText(java.util.ResourceBundle.getBundle("com/hosv3/property/thai").getString("ACTIVE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 11, 11);
        add(jCheckBox1, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodeActionPerformed
        
    }//GEN-LAST:event_jTextFieldCodeActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDel;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JTextArea jTextAreaName;
    private javax.swing.JTextField jTextFieldCode;
    // End of variables declaration//GEN-END:variables
    
}
