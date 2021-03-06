/*
 * PanelDrugWarehouse.java
 *
 * Created on 3 �Զع�¹ 2548, 10:50 �.
 */

package com.pcu.gui.panel.transaction;

import com.pcu.control.PCUControl;
import com.pcu.control.PCUObject;
import com.pcu.utility.*;
import com.pcu.object.DrugIn;

/**
 *
 * @author  amp
 */
public class PanelDrugWarehouse extends javax.swing.JPanel {    
    
    private DrugIn theDrugIn = new DrugIn();    
    private static PCUControl theHosControl;;
    /** Creates new form PanelDrugWarehouse */
    public PanelDrugWarehouse(PCUControl hc) 
    {        
        initComponents();
        setLanguage();
        
        theHosControl = hc;
        this.dateComboBoxDrugIn.setEditable(true);
    }
    
    public void getObject(PCUObject pcuobject)
    {   /** 仨Ѵ��õ������ͧ�ͧ�����ŷ����Ѻ ��������������բ����� ��鹨з����ҧ�ú�˹�� GUI */

    } 
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jPanel1 = new javax.swing.JPanel();
        jLabelDrugIn = new javax.swing.JLabel();
        jPanelSearchDrugIn = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabelSearchInvoiceNumber = new javax.swing.JLabel();
        jTextFieldSearchInvoiceNumber = new javax.swing.JTextField();
        jButtonSearchInvoiceNumber = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanelDrugInDetail = new javax.swing.JPanel();
        jLabelInvoiceNumber = new javax.swing.JLabel();
        jTextFieldInvoiceNumber = new javax.swing.JTextField();
        jLabelDateDrugIn = new javax.swing.JLabel();
        dateComboBoxDrugIn = new com.pcu.utility.DateComboBox();
        jLabelVendor = new javax.swing.JLabel();
        jTextFieldVendor = new javax.swing.JTextField();
        jLabelNote = new javax.swing.JLabel();
        jTextFieldNote = new javax.swing.JTextField();
        jPanelListItemDrugIn = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jButtonDel = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabelDrugIn.setText("DrugIn");
        jPanel1.add(jLabelDrugIn, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jPanel1, gridBagConstraints);

        jPanelSearchDrugIn.setLayout(new java.awt.GridBagLayout());

        jPanelSearchDrugIn.setBorder(new javax.swing.border.TitledBorder(null, "SearchDrugIn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabelSearchInvoiceNumber.setFont(defaultFont1);
        jLabelSearchInvoiceNumber.setText("InvoiceNumber");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jLabelSearchInvoiceNumber, gridBagConstraints);

        jTextFieldSearchInvoiceNumber.setMinimumSize(new java.awt.Dimension(90, 21));
        jTextFieldSearchInvoiceNumber.setPreferredSize(new java.awt.Dimension(90, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel2.add(jTextFieldSearchInvoiceNumber, gridBagConstraints);

        jButtonSearchInvoiceNumber.setFont(defaultFont1);
        jButtonSearchInvoiceNumber.setText("Search");
        jButtonSearchInvoiceNumber.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSearchInvoiceNumber.setMaximumSize(new java.awt.Dimension(67, 25));
        jButtonSearchInvoiceNumber.setMinimumSize(new java.awt.Dimension(67, 25));
        jButtonSearchInvoiceNumber.setPreferredSize(new java.awt.Dimension(67, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jButtonSearchInvoiceNumber, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanelSearchDrugIn.add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelSearchDrugIn.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanelSearchDrugIn, gridBagConstraints);

        jPanelDrugInDetail.setLayout(new java.awt.GridBagLayout());

        jPanelDrugInDetail.setBorder(new javax.swing.border.TitledBorder(null, "DrugInDetail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jLabelInvoiceNumber.setFont(defaultFont1);
        jLabelInvoiceNumber.setText("InvoiceNumber");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanelDrugInDetail.add(jLabelInvoiceNumber, gridBagConstraints);

        jTextFieldInvoiceNumber.setMinimumSize(new java.awt.Dimension(90, 21));
        jTextFieldInvoiceNumber.setPreferredSize(new java.awt.Dimension(90, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelDrugInDetail.add(jTextFieldInvoiceNumber, gridBagConstraints);

        jLabelDateDrugIn.setFont(defaultFont1);
        jLabelDateDrugIn.setText("DateDrugIn");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelDrugInDetail.add(jLabelDateDrugIn, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelDrugInDetail.add(dateComboBoxDrugIn, gridBagConstraints);

        jLabelVendor.setFont(defaultFont1);
        jLabelVendor.setText("Vendor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelDrugInDetail.add(jLabelVendor, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelDrugInDetail.add(jTextFieldVendor, gridBagConstraints);

        jLabelNote.setFont(defaultFont1);
        jLabelNote.setText("Note");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelDrugInDetail.add(jLabelNote, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanelDrugInDetail.add(jTextFieldNote, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jPanelDrugInDetail, gridBagConstraints);

        jPanelListItemDrugIn.setLayout(new java.awt.GridBagLayout());

        jPanelListItemDrugIn.setBorder(new javax.swing.border.TitledBorder(null, "ItemDrugIn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, defaultFont1));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelListItemDrugIn.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        add(jPanelListItemDrugIn, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jButtonSave.setFont(defaultFont1);
        jButtonSave.setText("Save");
        jButtonSave.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonSave.setMaximumSize(new java.awt.Dimension(67, 25));
        jButtonSave.setMinimumSize(new java.awt.Dimension(67, 25));
        jButtonSave.setPreferredSize(new java.awt.Dimension(67, 25));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jButtonSave, gridBagConstraints);

        jButtonAdd.setFont(defaultFont1);
        jButtonAdd.setText("Add");
        jButtonAdd.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonAdd.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonAdd.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonAdd.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel4.add(jButtonAdd, gridBagConstraints);

        jButtonDel.setFont(defaultFont1);
        jButtonDel.setText("Delete");
        jButtonDel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonDel.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDel.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDel.setPreferredSize(new java.awt.Dimension(24, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel4.add(jButtonDel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanel4, gridBagConstraints);

    }
    // </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        clearGui();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        saveOrUpdateDrugIn();
    }//GEN-LAST:event_jButtonSaveActionPerformed
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.pcu.utility.DateComboBox dateComboBoxDrugIn;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDel;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearchInvoiceNumber;
    private javax.swing.JLabel jLabelDateDrugIn;
    private javax.swing.JLabel jLabelDrugIn;
    private javax.swing.JLabel jLabelInvoiceNumber;
    private javax.swing.JLabel jLabelNote;
    private javax.swing.JLabel jLabelSearchInvoiceNumber;
    private javax.swing.JLabel jLabelVendor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelDrugInDetail;
    private javax.swing.JPanel jPanelListItemDrugIn;
    private javax.swing.JPanel jPanelSearchDrugIn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextFieldInvoiceNumber;
    private javax.swing.JTextField jTextFieldNote;
    private javax.swing.JTextField jTextFieldSearchInvoiceNumber;
    private javax.swing.JTextField jTextFieldVendor;
    // End of variables declaration//GEN-END:variables
    
    public void setLanguage()
    {  
        /*jLabel*/
        jLabelSearchInvoiceNumber.setText(GutilPCU.getTextBundle(jLabelSearchInvoiceNumber.getText())); 
        jLabelDrugIn.setText(GutilPCU.getTextBundle(jLabelDrugIn.getText())); 
        jLabelInvoiceNumber.setText(GutilPCU.getTextBundle(jLabelInvoiceNumber.getText())); 
        jLabelDateDrugIn.setText(GutilPCU.getTextBundle(jLabelDateDrugIn.getText()));        
        jLabelVendor.setText(GutilPCU.getTextBundle(jLabelVendor.getText()));        
        jLabelNote.setText(GutilPCU.getTextBundle(jLabelNote.getText()));        
                
        /*jButton*/
        jButtonSearchInvoiceNumber.setText(GutilPCU.getTextBundle(jButtonSearchInvoiceNumber.getText()));
        jButtonAdd.setText(GutilPCU.getTextBundle(jButtonAdd.getText()));
        jButtonDel.setText(GutilPCU.getTextBundle(jButtonDel.getText()));
        jButtonSave.setText(GutilPCU.getTextBundle(jButtonSave.getText()));  
        
        /*TitledBorder*/        
        GutilPCU.JPanelLabler(jPanelSearchDrugIn);
        GutilPCU.JPanelLabler(jPanelDrugInDetail);
        GutilPCU.JPanelLabler(jPanelListItemDrugIn);                
    }
    
    public static void main(String[] argv)
    {         
//        HosControl hc = new HosControl();
//        PanelDrugWarehouse thePanelDrugWarehouse = new PanelDrugWarehouse(hc);       
//        JFrame frm = new JFrame("PanelDrugWarehouse");        
//      
//        frm.getContentPane().add(thePanelDrugWarehouse);       
//        frm.pack();
//        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frm.setVisible(true);        
    }
    
    public void clearGui()
    {
        this.jTextFieldInvoiceNumber.setText("");
        this.jTextFieldVendor.setText("");
        this.jTextFieldNote.setText("");
    }
    
    public void saveOrUpdateDrugIn()
    {        
        this.theDrugIn.drug_in_invoice_number = this.jTextFieldInvoiceNumber.getText();
        this.theDrugIn.drug_in_date_time = this.dateComboBoxDrugIn.getText();
        this.theDrugIn.drug_in_vendor = this.jTextFieldVendor.getText();
        this.theDrugIn.drug_in_note = this.jTextFieldNote.getText();
        this.theHosControl.saveDrugIn(this.theDrugIn);   
    }
}
