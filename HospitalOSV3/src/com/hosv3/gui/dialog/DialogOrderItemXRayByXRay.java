/*
 * DialogOrderItemXRayByXRay.java
 *
 * Created on 29 ����Ҥ� 2547, 18:04 �.
 */
package com.hosv3.gui.dialog;
import java.util.*;
import java.awt.*; 
import javax.swing.*;
import com.hosv3.control.*;
import com.hosv3.utility.connection.*;

import com.hosv3.utility.*;
import com.hosv3.object.*;
import com.hospital_os.object.*; 
import com.hosv3.utility.Constant;
import com.hospital_os.utility.TaBleModel;
/**
 *
 * @author padungrat(tong)
 */
public class DialogOrderItemXRayByXRay extends javax.swing.JFrame {
    
    HosObject theHO; 
    UpdateStatus theUS;
    HosControl theHC;
    public boolean actionCommand = false;
    
    /*Control*/
    private SetupControl theSetupControl;
    private OrderControl theOrderControl;
    private ResultControl theResultControl;
    private SystemControl theSystemControl;
    
    /*Vector*/ 
    private Vector vItemXRay;
    private Vector vFromPanelXRay = new Vector();/*�� Object �ͧ orderitem*/
    private Vector vXRayResultItem;
    private Vector vOrderItem;
    private Vector vTemp;
    
    /*Object*/
    private OrderItem theOrderItem;
    private Item theItem;
    private Visit theVisit;
    //private OrderXRay theOrderXRay;  
    /*Other*/
    private String price = ""; 
    private String[] col_jTableListItemXRay = {"����","���͡����"};
    private String[] col_jTableOrderItem = {"����","˹���","�Ҥ�","ʶҹ�"};       
    
    
    /** Creates new form DialogOrderItemXRayByXRay */
    public DialogOrderItemXRayByXRay(HosControl hc,UpdateStatus us) 
    {        
        //super(us.getJFrame(), true); //�͹������Frame����
        initComponents();
        setLanguage(""); 
        theHC =hc;
        theSetupControl = hc.theSetupControl;
        theOrderControl = hc.theOrderControl;
        theResultControl= hc.theResultControl;
        theSystemControl= hc.theSystemControl;
        theHO = hc.theHO;
        theVisit = hc.theHO.theVisit;
        theUS = us;
        //searchItemXRay();
        setIconImage(theUS.getJFrame().getIconImage());
        setTableItemXRay(null);
        updateOGOrderItemV(null);
        setEnableAll(false);   
                      
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
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListItemXRay = new com.hosv3.gui.component.HJTableSort();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableOrderItem = new com.hosv3.gui.component.HJTableSort();
        jPanel6 = new javax.swing.JPanel();
        jButtonDel = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonClose = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(650, 400));
        jPanel1.setPreferredSize(new java.awt.Dimension(650, 400));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(new javax.swing.border.TitledBorder("\u0e04\u0e49\u0e19\u0e2b\u0e32"));
        jPanel2.setMinimumSize(new java.awt.Dimension(300, 400));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 400));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("\u0e04\u0e49\u0e19\u0e2b\u0e32");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel5.add(jLabel1, gridBagConstraints);

        jTextFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel5.add(jTextFieldSearch, gridBagConstraints);

        jButtonSearch.setText("\u0e04\u0e49\u0e19\u0e2b\u0e32");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel5.add(jButtonSearch, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel5, gridBagConstraints);

        jTableListItemXRay.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableListItemXRay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableListItemXRayMouseReleased(evt);
            }
        });

        jScrollPane1.setViewportView(jTableListItemXRay);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(new javax.swing.border.TitledBorder("\u0e23\u0e32\u0e22\u0e01\u0e32\u0e23\u0e40\u0e2d\u0e01\u0e0b\u0e40\u0e23\u0e22\u0e4c"));
        jPanel3.setMinimumSize(new java.awt.Dimension(320, 400));
        jPanel3.setPreferredSize(new java.awt.Dimension(320, 400));
        jTableOrderItem.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTableOrderItem);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jScrollPane2, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jButtonDel.setText("-");
        jButtonDel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonDel.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDel.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDel.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(jButtonDel, gridBagConstraints);

        jButtonSave.setText("\u0e1a\u0e31\u0e19\u0e17\u0e36\u0e01");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        jPanel6.add(jButtonSave, gridBagConstraints);

        jButtonClose.setText("\u0e1b\u0e34\u0e14");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel6.add(jButtonClose, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel3.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jPanel3, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchActionPerformed
       searchItemXRay();
    }//GEN-LAST:event_jTextFieldSearchActionPerformed
    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        searchItemXRay();
    }//GEN-LAST:event_jButtonSearchActionPerformed
    private void jButtonDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelActionPerformed
        int[] select = jTableOrderItem.getSelectedRows();
        if(select == null || select.length == 0){
            JOptionPane.showConfirmDialog(this, Constant.getTextBundle("�ѧ����բ����ŷ���ź"), Constant.getTextBundle("��͹"), JOptionPane.WARNING_MESSAGE);            
            return;
        }         
        OrderItem oi = new OrderItem();
        oi = (OrderItem)this.vOrderItem.get(select[0]);            
        for(int i=0;i < select.length;i++){
            if(oi != null){   
                if(oi.getObjectId()!= null){   
                    if(oi.order_user.equals(theHO.theEmployee.employee_id) 
                        && oi.vertifier.equals("")){
                        this.vOrderItem.remove(select[0]);
                        this.updateOGOrderItemV(this.vOrderItem);    
                        
                        this.theHC.theOrderControl.deleteOrderItemXRayByXRay(oi);
                    }
                    else{
                        JOptionPane.showMessageDialog(this, Constant.getTextBundle("�������öź��¡����硫��������觨ҡ�ش��ԡ�������"), Constant.getTextBundle("��͹"), JOptionPane.WARNING_MESSAGE);            
                    }
                }
                else{
                    this.vOrderItem.remove(select[0]);
                    this.updateOGOrderItemV(this.vOrderItem);                    
                }
            } 
        }                      
        oi = null;
    }//GEN-LAST:event_jButtonDelActionPerformed
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        updateStatus();
        theOrderControl.saveOrderItemInXRay(vOrderItem,theVisit);
        updateOGOrderItemV(vOrderItem);
        jButtonSave.setEnabled(false);
        setTableItemXRay(null);
        updateOGOrderItemV(null);
        setEnableAll(false);
//        setLanguage("");
        actionCommand = true;
        dispose();
    }//GEN-LAST:event_jButtonSaveActionPerformed
    private void jTableListItemXRayMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListItemXRayMouseReleased
        if(this.jTableListItemXRay.getSelectedRow() != -1){
            selectItemXRay();
            updateGOOrderItem();           
            updateOGOrderItemV(this.vOrderItem);
            setEnableAll(true);
        }
    }//GEN-LAST:event_jTableListItemXRayMouseReleased
    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        setTableItemXRay(null);
        updateOGOrderItemV(null);
        setEnableAll(false);
//        setLanguage("");
        dispose();       
    }//GEN-LAST:event_jButtonCloseActionPerformed
        
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setTableItemXRay(null);
        updateOGOrderItemV(null);
        setEnableAll(false);
//        setLanguage("");
        dispose();
    }//GEN-LAST:event_closeDialog
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonDel;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.hosv3.gui.component.HJTableSort jTableListItemXRay;
    private com.hosv3.gui.component.HJTableSort jTableOrderItem;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
    
    private void setEnableAll(boolean var)
    {
        this.jButtonDel.setEnabled(var);
        this.jButtonSave.setEnabled(var);
    }
    
    private void searchItemXRay()
    {   
        this.vItemXRay = new Vector();
        String search = "";
        search = this.jTextFieldSearch.getText();
        this.vItemXRay = this.theSetupControl.listSearchItem(search,4,CategoryGroup.isLab());
        setTableItemXRay(this.vItemXRay);
    }   
    
    private void setTableItemXRay(Vector vItemXRay)
    {
        Item p = new Item();
        TaBleModel tm ;
        if(vItemXRay != null)
        {   
            tm= new TaBleModel(col_jTableListItemXRay,vItemXRay.size());            
            for(int i=0 ;i<vItemXRay.size(); i++)
            {  
                p = (Item)vItemXRay.get(i);           
                tm.setValueAt(p.common_name,i,0);                
                tm.setValueAt(theHC.theLookupControl.readCategoryGroupItemString(
                        p.item_group_code_category),i,1);
            }
        }
        else
        {   
            tm= new TaBleModel(col_jTableListItemXRay,0);
        }
            this.jTableListItemXRay.setModel(tm);
    }
    
    private void selectItemXRay()
    {   
        int row = this.jTableListItemXRay.getSelectedRow();
        Item item = (Item)this.vItemXRay.get(row);
        ItemPrice ip = theOrderControl.readItemPriceByItem(item.getObjectId()); 
        price = ip.price;
     
    }
    
    private void updateGOOrderItem()
    {   
        Constant.println("updateGOOrderItem++++++++++++++++++++++++++++++++++++++++++++");
        int row = jTableListItemXRay.getSelectedRow();
        theItem = (Item) vItemXRay.get(row);
        CategoryGroupItem cgi = theHC.theLookupControl.readCategoryGroupItemById(theItem.item_group_code_category);
        Drug drug = theHC.theOrderControl.listDrugByItem(theItem.getObjectId());
        ItemPrice ip = theHC.theOrderControl.readItemPriceByItem(theItem.getObjectId()); 
        theOrderItem = theHO.initOrderItem(theItem,cgi,ip,theHO.date_time);
        
        boolean isrepleat = false;
        if(vOrderItem != null){
            //��Ǩ�ͺ�������¡����硫�������ӡѹ 
            for(int i = 0;i<this.vOrderItem.size();i++){
                OrderItem oi = (OrderItem) vOrderItem.get(i);
                if(oi.item_code.equals(theOrderItem.item_code)){
                    isrepleat = true;
                    break;
                }
            }
            if(isrepleat == true){
                if(javax.swing.JOptionPane.showConfirmDialog(this,Constant.getTextBundle("����¡����硫�������ӡѹ �����硫���������������?")
                    ,Constant.getTextBundle("�׹�ѹ�������"),JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    vOrderItem.add(theOrderItem);
                }
            }
            else{
                /*
                //��Ǩ�ͺ�������¡��Xray���«�ӡѹ�������
                Vector vXRayResultItemNew = theHC.theOrderControl.readXRayResultItem(theOrderItem.item_code);
                boolean isresultitemrepleat = false;
                int sizeorItem = vOrderItem.size();
                int sizereitemnew = vXRayResultItemNew.size();
                int sizereitemold = 0;
                first:for(int i=0; i<sizeorItem; i++){
                    OrderItem oi = (OrderItem) vOrderItem.get(i);
                    Vector vXRayResultItemOld = theHC.theOrderControl.readXRayResultItem(oi.item_code);
                    sizereitemold = vXRayResultItemOld.size();
                    for(int j=0;j<sizereitemold;j++){
                        XRayResultItem roold = (XRayResultItem) vXRayResultItemOld.get(j);
                        for(int k=0; k<sizereitemnew; k++){
                            XRayResultItem ronew = (XRayResultItem) vXRayResultItemNew.get(k);
                            if(roold.getObjectId().equals(ronew.getObjectId())){                               
                                isresultitemrepleat = true;
                                break first;
                            }
                        }
                    }
                 
                }
                 
                if(isresultitemrepleat){
                     if(javax.swing.JOptionPane.showConfirmDialog(this,"����¡����硫�������·���ӡѹ �����硫���������������?"
                    ,"�׹�ѹ�������",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                          vOrderItem.add(theOrderItem);
                      }
                }
                else{
                    vOrderItem.add(theOrderItem);
                }
                 **
                 */
                 vOrderItem.add(theOrderItem);
            }
        }
        else{
            vOrderItem = new Vector();
            vOrderItem.add(theOrderItem);
        }
    }    
    ///////////////////////////////////////////////////////////////////////////  
    private void updateOGOrderItemV(Vector vOrderItem)
    {    
        OrderItem p = new OrderItem();
        Vector v = new Vector();
        TaBleModel tm ;
        if(vOrderItem != null)
        {   
            tm= new TaBleModel(col_jTableOrderItem,vOrderItem.size());
            v =  this.vOrderItem;
            if(v != null)
            {   for(int i=0 ;i<v.size(); i++)
                {  
                    p = (OrderItem)vOrderItem.get(i);
                    Item it = theHC.theLookupControl.readItemById(p.item_code);
                    OrderItemStatus ois = theHC.theLookupControl.readOrderItemStatus(p.status);
                    tm.setValueAt(it.common_name,i,0);               
                    tm.setValueAt(p.qty,i, 1);
                    tm.setValueAt(p.price,i,2);
                    tm.setValueAt(ois.description,i, 3);            
                }
            }
        }
        else
        {   
            tm= new TaBleModel(col_jTableOrderItem,0);
        }
        this.jTableOrderItem.setModel(tm);    
        setTableOrderItemXRayDefault();            
    }
    
    private void setTableOrderItemXRayDefault()
    {       
        this.jTableOrderItem.getColumnModel().getColumn(0).setPreferredWidth(300); // �������ѭ����Ѻ þ.�����, ���͡�ä�� ����Ѻ�ٹ���ä��
        this.jTableOrderItem.getColumnModel().getColumn(1).setPreferredWidth(50); // �ӹǹ            
        this.jTableOrderItem.getColumnModel().getColumn(2).setPreferredWidth(100); // �Ҥ�            
        this.jTableOrderItem.getColumnModel().getColumn(3).setPreferredWidth(100); // ʶҹ�
    }
    ////////////////////////////////////////////////////////
    private void updateStatus()
    {
        String date_time = theHC.theLookupControl.getTextCurrentDateTime();
        for(int i=0; i<this.vOrderItem.size(); i++)
        {
            OrderItem oi = (OrderItem) vOrderItem.get(i);
            if(oi.status.equals("0"))
            {
                oi.status = OrderStatus.VERTIFY;
            }
            
            if(oi.vertifier.equals("")){
                 oi.vertifier = this.theHO.theEmployee.getObjectId();
                 oi.vertify_time = date_time;            
            }
           
        }
    }    
    
/*-----------------------------------------------------------*/    
    public boolean showDialog(Vector vorderXRay,Visit theVisit)
    {       
        //this.vOrderItem = vorderXRay;
        this.vOrderItem = new Vector();
        this.theVisit = theVisit;
        setSize(640,480);
        setTitle(Constant.getTextBundle("�����¡����硫��������"));
        Toolkit thekit = getToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width-getSize().width)/2, (screenSize.height-getSize().height)/2);
        setVisible(true);
        if(vorderXRay == null || vorderXRay.size() == 0){
            Constant.println("No Item Xray");
           // JOptionPane.showMessageDialog(this,"Item = 0","sdf",JOptionPane.ERROR_MESSAGE);
        }
        for(int i=0,size = vorderXRay.size();i<size ;i++){ 
            vOrderItem.add(vorderXRay.get(i));
            OrderItem oi = (OrderItem) vOrderItem.get(i);
        }
        updateOGOrderItemV(vOrderItem);
        if(actionCommand){      
              return true;
        }
        
        System.gc();
        return false;
    }    
    /*-----------------------------------------------------------------------*/
    public void setLanguage(String msg){
       /*
          this.jXRayelClinic.setText("CLINIC");
          this.jXRayelWard.setText("WARD");
          this.jXRayelBed.setText("BED");
          this.jButtonCancel.setText("CANCEL");
          this.jButtonSave.setText("save");
        
        this.jXRayelClinic.setText(Constant.getTextBundle(this.jXRayelClinic.getText()));
        this.jXRayelWard.setText(Constant.getTextBundle(this.jXRayelWard.getText()));
        this.jXRayelBed.setText(Constant.getTextBundle(this.jXRayelBed.getText()));
        
         this.jButtonCancel.setText(Constant.getTextBundle(this.jButtonCancel.getText()));
        this.jButtonSave.setText(Constant.getTextBundle(this.jButtonSave.getText()));
        */
        GuiLang.setTextBundle(jPanel2);
        GuiLang.setTextBundle(jPanel3);
        GuiLang.setLanguage(jLabel1);
        GuiLang.setLanguage(jButtonClose);
        GuiLang.setLanguage(jButtonDel);
        GuiLang.setLanguage(jButtonSave);
        GuiLang.setLanguage(jButtonSearch);
        GuiLang.setLanguage(col_jTableListItemXRay);
        GuiLang.setLanguage(col_jTableOrderItem);        
      }
}
