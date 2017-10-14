/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelSearchItem.java
 *
 * Created on 25 �.�. 2554, 15:32:34
 */

package com.hosv3.gui.dialog;

import com.hospital_os.object.Item;
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.Gutil;
import com.hospital_os.utility.TaBleModel;
import com.hosv3.control.HosControl;
import com.hosv3.utility.connection.UpdateStatus;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.JDialog;

/**
 *
 * @author LionHeart
 */
public class PanelSearchItem extends javax.swing.JPanel {

    /** Creates new form PanelSearchItem */
    HosControl theHC;
    private Vector theItemV;
    private JDialog theJD;
    String[] col = {"��¡��"};
    public String order_list;
    public PanelSearchItem() {
        initComponents();
    }
    public void setItemV(Vector v)
    {
        theItemV = v;
        TaBleModel tm;
        if(theItemV == null)
        {
            theItemV = new Vector();
        }
        tm = new TaBleModel(col,theItemV.size());
        for(int i=0;i<theItemV.size();i++)
        {
            Item item = (Item) theItemV.get(i);
            tm.setValueAt(item.common_name, i, 0);
        }
        jTable1.setModel(tm);
    }
    public void setControl(HosControl hc)
    {
        theHC = hc;
        ComboboxModel.initComboBox(jComboBoxSCategory,theHC.theLookupControl.listCategoryGroupItem());
    }
    public void showDialog()
    {
        if(theJD==null)
            theJD = new JDialog(theHC.theUS.getJFrame());
        theJD.add(this);
        theJD.setSize(450,500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        theJD.setLocation((screenSize.width - theJD.getSize().width) / 2, (screenSize.height - theJD.getSize().height) / 2);
        theJD.setTitle("���͡��¡�õ�Ǩ�ѡ��");
        theJD.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jComboBoxSCategory = new javax.swing.JComboBox();
        jCheckBoxSearchGroup = new javax.swing.JCheckBox();
        jTextFieldSCode = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jCheckBoxS = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new com.hosv3.gui.component.HJTableSort();
        jRadioButtonBegin = new javax.swing.JRadioButton();
        jRadioButtonConsist = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jComboBoxSCategory.setEnabled(false);
        jComboBoxSCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSCategoryActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        add(jComboBoxSCategory, gridBagConstraints);

        jCheckBoxSearchGroup.setText("�����");
        jCheckBoxSearchGroup.setMaximumSize(new java.awt.Dimension(1, 1));
        jCheckBoxSearchGroup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxSearchGroupMouseClicked(evt);
            }
        });
        jCheckBoxSearchGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSearchGroupActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        add(jCheckBoxSearchGroup, gridBagConstraints);

        jTextFieldSCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSCodeActionPerformed(evt);
            }
        });
        jTextFieldSCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSCodeKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 3, 5);
        add(jTextFieldSCode, gridBagConstraints);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/hosv3/property/thai"); // NOI18N
        jButtonSearch.setText(bundle.getString("SEARCH")); // NOI18N
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        add(jButtonSearch, gridBagConstraints);

        jCheckBoxS.setSelected(true);
        jCheckBoxS.setText(bundle.getString("ACTIVE")); // NOI18N
        jCheckBoxS.setMaximumSize(new java.awt.Dimension(1, 1));
        jCheckBoxS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        add(jCheckBoxS, gridBagConstraints);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(100, 22));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 80));

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        add(jScrollPane1, gridBagConstraints);

        buttonGroup1.add(jRadioButtonBegin);
        jRadioButtonBegin.setText("��˹��");
        jRadioButtonBegin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonBeginActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(jRadioButtonBegin, gridBagConstraints);

        buttonGroup1.add(jRadioButtonConsist);
        jRadioButtonConsist.setSelected(true);
        jRadioButtonConsist.setText("��Сͺ����");
        jRadioButtonConsist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonConsistActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        add(jRadioButtonConsist, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("+");
        jButton1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(jButton1, gridBagConstraints);

        jButton2.setText("�Դ");
        jButton2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel1.add(jButton2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxSCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSCategoryActionPerformed
        jButtonSearchActionPerformed(null);
}//GEN-LAST:event_jComboBoxSCategoryActionPerformed

    private void jCheckBoxSearchGroupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxSearchGroupMouseClicked

}//GEN-LAST:event_jCheckBoxSearchGroupMouseClicked

    private void jCheckBoxSearchGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSearchGroupActionPerformed
        jComboBoxSCategory.setEnabled(jCheckBoxSearchGroup.isSelected());
        jButtonSearchActionPerformed(null);
}//GEN-LAST:event_jCheckBoxSearchGroupActionPerformed

    private void jTextFieldSCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSCodeActionPerformed

}//GEN-LAST:event_jTextFieldSCodeActionPerformed

    private void jTextFieldSCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSCodeKeyReleased
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
        {
            jButtonSearchActionPerformed(null);
        }
}//GEN-LAST:event_jTextFieldSCodeKeyReleased

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        String keyword = jTextFieldSCode.getText();
        keyword = Gutil.CheckReservedWords(keyword);
        if(keyword.length() < 1 && !jCheckBoxSearchGroup.isSelected())
        {
            theHC.theUS.setStatus("�ӷ����㹡�ä�������Թ�",UpdateStatus.WARNING);
            return;
        }
        boolean begin_with = this.jRadioButtonBegin.isSelected();
        // Somprasong 120810 comment fix bug search
//        if (jCheckBoxGroup.isSelected()) {
//            theItemV  = theHC.theOrderControl.listItem(
//                    ComboboxModel.getCodeComboBox(jComboBoxGroup)+ " "+keyword,begin_with);
//        }
//        else {
//            theItemV  = theHC.theOrderControl.listItem(keyword,begin_with);
//        }
        String grpId = jCheckBoxSearchGroup.isSelected()?ComboboxModel.getCodeComboBox(jComboBoxSCategory):"";
        theItemV  = theHC.theOrderControl.listItem(grpId, keyword, begin_with);
        for(int i=0;i<theItemV.size();i++)
        {
            Item item = (Item) theItemV.get(i);
        }
        setItemV(theItemV);
}//GEN-LAST:event_jButtonSearchActionPerformed

    private void jCheckBoxSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSActionPerformed

}//GEN-LAST:event_jCheckBoxSActionPerformed

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        if(evt.getClickCount() == 2)
        {
            jButton1ActionPerformed(null);
        }
}//GEN-LAST:event_jTable1MouseReleased

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        
}//GEN-LAST:event_jTable1KeyReleased

    private void jRadioButtonBeginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonBeginActionPerformed
        jButtonSearchActionPerformed(null);
}//GEN-LAST:event_jRadioButtonBeginActionPerformed

    private void jRadioButtonConsistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonConsistActionPerformed
        jButtonSearchActionPerformed(null);
}//GEN-LAST:event_jRadioButtonConsistActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        order_list = "";
        int[] selected = jTable1.getSelectedRows();
        for(int i=0;i<selected.length;i++)
        {
            Item item = (Item) theItemV.get(selected[i]);
            this.order_list =  order_list+","+item.getObjectId()+"^"+item.common_name.replace(",", " ");
        }
        theJD.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.order_list = "";
        theJD.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JCheckBox jCheckBoxS;
    private javax.swing.JCheckBox jCheckBoxSearchGroup;
    private javax.swing.JComboBox jComboBoxSCategory;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonBegin;
    private javax.swing.JRadioButton jRadioButtonConsist;
    private javax.swing.JScrollPane jScrollPane1;
    private com.hosv3.gui.component.HJTableSort jTable1;
    private javax.swing.JTextField jTextFieldSCode;
    // End of variables declaration//GEN-END:variables

}