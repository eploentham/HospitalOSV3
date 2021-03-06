/*
 * PanelSetupScreenedDisease.java
 *
 * Created on 14 �չҤ� 2549, 15:08 �.
 */

package com.setupreport.gui.panel;

import com.setupreport.object.Item;
import com.setupreport.gui.dialog.DialogSearchItem;
import com.setupreport.gui.dialog.DialogShowTableDataSame;
import com.setupreport.utility.TableModelGUI;
import com.setupreport.manage.HosManage;
import com.setupreport.usecase.SaveScreeningDiseaseItem;
import com.setupreport.utility.Language;
import com.setupreport.object.Rp115Group4Disease;
import com.setupreport.object.Rp115Group4Item;
import javax.swing.JFrame;
import java.util.Vector;
import javax.swing.JOptionPane;
/**
 *
 * @author  pu
 */
public class PanelSetupScreenedDisease extends javax.swing.JPanel implements SaveScreeningDiseaseItem
{
    Rp115Group4Disease theRp115Group4Disease,theRp115Group4DiseaseTemp;
    Rp115Group4Item theRp115Group4Item,theRp115Group4ItemTemp;
    HosManage theHosManage;
    Vector vScreeningDisease;
    Vector vDisease;
    Vector vItem,vItemTemp;
    Vector vcSame;
    Vector vcNotSame;
    TableModelGUI theTableModelGUI;
    String[] colNameDisease;
    String[] colNameItem;
    int language = 1;
    int selectedrows = 0;
    final String panelName = "ScreenedDisease";
    /** Creates new form PanelSetupScreenedDisease */
    public PanelSetupScreenedDisease(HosManage manage)
    {
        theHosManage = manage;
        initComponents();
        setLanguage();
        this.theHosManage.theHosControl.theHosSubject.theScreeningDiseaseItemSubject.registerMainReportManage(this);
        showDiseaseToTable(null);
        showItemToTable(null);
        searchDiasese(); //ojika 28 Feb 2007
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelUpper = new javax.swing.JPanel();
        jLabelScreenedDisease = new javax.swing.JLabel();
        jPanelLeft = new javax.swing.JPanel();
        jPanelSearch = new javax.swing.JPanel();
        jLabelSearch = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jPanelShowData = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDisease = new javax.swing.JTable();
        jPanelRight = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabelCode = new javax.swing.JLabel();
        jTextFieldCode = new javax.swing.JTextField();
        jLabelDescription = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableItem = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        setMinimumSize(new java.awt.Dimension(300, 149));
        setPreferredSize(new java.awt.Dimension(300, 523));
        jLabelScreenedDisease.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabelScreenedDisease.setText("ScreenedDisease");
        jPanelUpper.add(jLabelScreenedDisease);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        add(jPanelUpper, gridBagConstraints);

        jPanelLeft.setLayout(new java.awt.GridBagLayout());

        jPanelLeft.setMinimumSize(new java.awt.Dimension(200, 53));
        jPanelLeft.setPreferredSize(new java.awt.Dimension(200, 433));
        jPanelSearch.setLayout(new java.awt.GridBagLayout());

        jPanelSearch.setPreferredSize(new java.awt.Dimension(100, 24));
        jLabelSearch.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanelSearch.add(jLabelSearch, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanelSearch.add(jTextFieldSearch, gridBagConstraints);

        jButtonSearch.setText("Search");
        jButtonSearch.setMaximumSize(new java.awt.Dimension(75, 24));
        jButtonSearch.setMinimumSize(new java.awt.Dimension(75, 24));
        jButtonSearch.setPreferredSize(new java.awt.Dimension(75, 24));
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelSearch.add(jButtonSearch, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        jPanelLeft.add(jPanelSearch, gridBagConstraints);

        jPanelShowData.setLayout(new java.awt.GridBagLayout());

        jTableDisease.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableDisease.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableDiseaseMouseReleased(evt);
            }
        });

        jScrollPane1.setViewportView(jTableDisease);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelShowData.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 3, 0);
        jPanelLeft.add(jPanelShowData, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 0);
        add(jPanelLeft, gridBagConstraints);

        jPanelRight.setLayout(new java.awt.GridBagLayout());

        jPanelRight.setMinimumSize(new java.awt.Dimension(350, 24));
        jPanelRight.setPreferredSize(new java.awt.Dimension(350, 24));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabelCode.setText("Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabelCode, gridBagConstraints);

        jTextFieldCode.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jTextFieldCode, gridBagConstraints);

        jLabelDescription.setText("Description");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jLabelDescription, gridBagConstraints);

        jScrollPane3.setMinimumSize(new java.awt.Dimension(23, 50));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(102, 50));
        jTextArea1.setEditable(false);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(2);
        jScrollPane3.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jScrollPane3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanelRight.add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jTableItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTableItem);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanelRight.add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/plus16.gif")));
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel3.add(jButtonAdd, gridBagConstraints);

        jButtonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/minus16.gif")));
        jButtonDelete.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButtonDelete.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonDelete.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonDelete.setPreferredSize(new java.awt.Dimension(24, 24));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel3.add(jButtonDelete, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        jPanelRight.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 3);
        add(jPanelRight, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    private void jTableDiseaseMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTableDiseaseMouseReleased
    {//GEN-HEADEREND:event_jTableDiseaseMouseReleased
        // ���͡��¡�á�����Ѵ��ͧ
        selectDisease(this.jTableDisease.getSelectedRow());        
    }//GEN-LAST:event_jTableDiseaseMouseReleased

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeleteActionPerformed
    {//GEN-HEADEREND:event_jButtonDeleteActionPerformed
         // ź��¡�� Item ��� Map �Ѻ������Ѵ��ͧ
        int confirm_del = JOptionPane.showConfirmDialog(this,Language.getTextBundle("confirm_del_effect_report", language),Language.getTextBundle("confirm_del", language),JOptionPane.YES_NO_OPTION);
        
        if(confirm_del == 0)
        {
            deleteItemFromDisease();
        }
        
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddActionPerformed
    {//GEN-HEADEREND:event_jButtonAddActionPerformed
        // ���¡ Dialog ��¡�� Item ������������㹡�����Ѵ��ͧ
        addItemToDisease();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonSearchActionPerformed
    {//GEN-HEADEREND:event_jButtonSearchActionPerformed
        // ������¡�äѴ��ͧ
        searchDiasese();
    }//GEN-LAST:event_jButtonSearchActionPerformed
    /**
     *ź��¡�õ�Ǩ�ѡ���͡�ҡ������Ѵ��ͧ
     *@Author pu
     *@date 16/03/2006
     */
    private void deleteItemFromDisease()
    {
        int[] a = jTableItem.getSelectedRows();
        this.theHosManage.theHosControl.theScreeningDiseaseControl.deleteRp115Item(this.vItem,a);
        this.vItem = this.theHosManage.theHosControl.theScreeningDiseaseControl.getVectorDeleted();
        this.showItemToTable(this.vItem);
    }
    
    /**
     *����Ҫ��ͧ͢ Panel
     *@Author pu
     *@date 16/03/2006
     */
    public String getPanelName()
    {
        return this.panelName;
    }
    /**
     *��������͡��¡�äѴ��ͧ ��������ʴ���¡�õ�Ǩ�ѡ�ҷ������㹡�����Ѵ��ͧ������͡
     *@param row �������Ţ�ͧ�Ƿ��١���͡�ҡ���ҧ
     *@Author pu
     *@date 16/03/2006
     */
    private void selectDisease(int row)
    {
        if(row > -1)
        {
            this.vItem = null;
            // �ա�����͡�ҡ���ҧ
            if(this.vScreeningDisease != null)
            {
                this.theRp115Group4Disease = (Rp115Group4Disease)this.vScreeningDisease.get(row);
                setDiseaseToGUI();
                // �֧�����Ũҡ�ҹ������ ����
                this.vItem = this.theHosManage.theHosControl.theScreeningDiseaseControl.searchRp115ItemByDiseaseID(this.theRp115Group4Disease.getObjectId());
                // �ʴ���������´������
                showItemToTable(this.vItem);
            }
        }
    }
    /**
     *�ʴ���������´�ͧ������Ѵ��ͧ
     *@Author pu
     *@date 16/03/2006
     */
    private void setDiseaseToGUI()
    {
        this.jTextFieldCode.setText(this.theRp115Group4Disease.rp11_disease_number);
        this.jTextArea1.setText(this.theRp115Group4Disease.rp11_disease_description);
    }
    /**
     * ����Ѻ������¡�õ�Ǩ�ѡ�� ��� �觢�������ʴ�㹵��ҧ
     * @Author pu
     * @Date 14/03/2006
     **/
    private void searchDiasese()
    {
        // ������¡�� ���� keyword 令�
        this.vItem = new Vector();
        String keyword = jTextFieldSearch.getText().trim();
        this.vScreeningDisease = this.theHosManage.theHosControl.theScreeningDiseaseControl.searchScreeningDiseaseByKeyword(keyword);
        // �觢����ŷ����ҡ��ä��ҹ����ʴ��ѧ���ҧ
        showDiseaseToTable(this.vScreeningDisease);
       
        this.theRp115Group4Disease = null;
        jTextFieldCode.setText("");
        jTextArea1.setText("");
        this.vItem = null;
        this.showItemToTable(null);
        
    }
    /**
     *������¡�õ�Ǩ�ѡ�� ����ͧ��� Map �Ѻ������Ѵ��ͧ
     *@Author pu
     *@Date 14/03/2006
     */
    public void addItemToDisease()
    {
        DialogSearchItem dialog = new DialogSearchItem(new JFrame(), true, this.theHosManage, 1);
        dialog.setVisible(true);
    }
    
    /**
     * ��㹡�� �ʴ������š�ä���ŧ�����ҧ����˹�
     * @param vc �� Vector �ͧ�����ŷ��ӡ�����������е�ͧ��á�˹�����ʴ������ҧ
     */
    private void showDiseaseToTable(Vector vc)
    {
        theTableModelGUI = new TableModelGUI(colNameDisease, 0);
        if(vc != null)
        {
            int size = vc.size();
            theTableModelGUI = new TableModelGUI(colNameDisease, size);
            theRp115Group4DiseaseTemp = null;
            for(int i = 0 ; i < size ;i++)
            {
                theRp115Group4DiseaseTemp = (Rp115Group4Disease)vc.get(i);
                theTableModelGUI.setValueAt(theRp115Group4DiseaseTemp.rp11_disease_number ,i, 0);
                theTableModelGUI.setValueAt(theRp115Group4DiseaseTemp.rp11_disease_description,i, 1);
                theRp115Group4ItemTemp = null;
            }
        }
        jTableDisease.setModel(theTableModelGUI);
        jTableDisease.getColumnModel().getColumn(0).setPreferredWidth(25);
        jTableDisease.getColumnModel().getColumn(1).setPreferredWidth(100);
    }
    
    /**
     * ��㹡�� �ʴ������š�ä���ŧ�����ҧ����˹�
     * @param vc �� Vector �ͧ�����ŷ��ӡ�����������е�ͧ��á�˹�����ʴ������ҧ
     */
    private void showItemToTable(Vector vc)
    {
        theTableModelGUI = new TableModelGUI(colNameItem, 0);
        if(vc != null)
        {
            int size = vc.size();
            theTableModelGUI = new TableModelGUI(colNameItem, size);
            theRp115Group4ItemTemp = null;
            for(int i = 0 ; i < size ;i++)
            {
                theRp115Group4ItemTemp = (Rp115Group4Item)vc.get(i);
                theTableModelGUI.setValueAt(theRp115Group4ItemTemp.rp11_item_description,i, 0);
                theRp115Group4ItemTemp = null;
            }
        }
        jTableItem.setModel(theTableModelGUI);
    }
    
    /**
     *�ӡ�úѹ�֡��������¡�õ�Ǩ�ѡ��
     *
     */
    public void notifySaveScreeningDiseaseItem(Vector vcItem)
    {
        if(this.vItem != null)
        {
            if(vcItem != null)
            {
                if(this.theRp115Group4Disease != null)
                {
                    Item theItem = new Item();
                    this.vcSame = new Vector();
                    this.vcNotSame = new Vector();
                    int size = vcItem.size();
                    for(int i =0;i<size;i++)
                    {
                        theItem = (Item)vcItem.get(i);
                        
                        this.theRp115Group4Item = new Rp115Group4Item();
                        
                        this.theRp115Group4Item.b_item_id = theItem.getObjectId();
                        this.theRp115Group4Item.rp11_item_number = theItem.item_id;
                        this.theRp115Group4Item.rp11_item_description = theItem.common_name;
                        this.theRp115Group4Item.r_rp11_disease_id = this.theRp115Group4Disease.getObjectId();
                        checkSameData(this.theRp115Group4Item);
                        this.theRp115Group4Item = null;
                    }
                    excecuteData();
                    theItem = null;
                }
                else
                {
                    JOptionPane.showMessageDialog(this,Language.getTextBundle("PleaseSelectDisease", language),Language.getTextBundle("Warning", language),JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        else
        {
            if(this.theRp115Group4Disease != null)
            {
                this.vItem = new Vector();
                int size = vcItem.size();
                
                for(int i =0;i<size;i++)
                {
                    Item item = new Item();
                    Rp115Group4Item rpitem = new Rp115Group4Item();
                    item = (Item)vcItem.get(i);
                    
                    rpitem.b_item_id = item.getObjectId();
                    rpitem.rp11_item_number = item.item_id;
                    rpitem.rp11_item_description = item.common_name;
                    rpitem.r_rp11_disease_id = this.theRp115Group4Disease.getObjectId();
                    this.vItem.add(rpitem);
                    item = null;
                    rpitem = null;
                }
                this.theHosManage.theHosControl.theScreeningDiseaseControl.saveRp115Item(this.vItem);
                this.vItem = this.theHosManage.theHosControl.theScreeningDiseaseControl.searchRp115ItemByDiseaseID(this.theRp115Group4Disease.getObjectId());
                this.showItemToTable(this.vItem);  
               // this.vItem = null;//test null pu*
            }
            else
            {
                JOptionPane.showMessageDialog(this,Language.getTextBundle("PleaseSelectDisease", language),Language.getTextBundle("Warning", language),JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    /**
     *��Ǩ�ͺ�����¡�õ�Ǩ�ѡ�ҷ���ͧ�����������㹡�����Ѵ��ͧ
     *��ӡѺ������������� �������
     *@param rgi �� Object �ͧ��¡�õ�Ǩ�ѡ�ҷ�������
     *Author pu
     *@date 17/03/2006
     */
    private void checkSameData(Rp115Group4Item rgi)
    {
        
        int size = this.vItem.size();
        int flag = 0;
        loopa:for(int i=0;i<size;i++)
        {   //��� item_id �ͧ Item ������� ��ҡѺ item_id �ͧ��¡�õ�Ǩ�ѡ�ҷ��������
            if(rgi.b_item_id.equals(((Rp115Group4Item)this.vItem.get(i)).b_item_id))
            {
                vcSame.add(rgi);
                flag = 0;
                break loopa;
            }
            else
            {
                flag = 1;
            }
        }
        if(flag != 0)
        {
            vcNotSame.add(rgi);
        }
    }
    
    /**
     *�Ӣ����ŷ����ҡ��õ�Ǩ�ͺ��ë��价ӧҹ��ҧ�
     *����繢����ŷ���� ���ա����͹
     *����繢���������� �й������㹰ҹ������ ����ʴ�㹵��ҧ
     *@Author pu
     *@date 17/03/2006
     */
    private void excecuteData()
    {
        if(vcSame != null && !vcSame.isEmpty())
        {
            //�ʴ���¡�÷����㹵��ҧ
            DialogShowTableDataSame dialog = new DialogShowTableDataSame(new JFrame(), true, vcSame);
            dialog.setVisible(true);
        }
        
        if(vcNotSame != null && !vcNotSame.isEmpty())
        {
            //�觤�� Vector �����㹰ҹ��������¡�õ�Ǩ�ѡ�� ����ʴ�㹵��ҧ
            int size = vcNotSame.size();
            this.theHosManage.theHosControl.theScreeningDiseaseControl.saveRp115Item(vcNotSame);
            this.vItem = this.theHosManage.theHosControl.theScreeningDiseaseControl.searchRp115ItemByDiseaseID(this.theRp115Group4Disease.getObjectId());
            this.showItemToTable(this.vItem);
        }
        this.vcSame = null;
        this.vcNotSame = null;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JLabel jLabelCode;
    private javax.swing.JLabel jLabelDescription;
    private javax.swing.JLabel jLabelScreenedDisease;
    private javax.swing.JLabel jLabelSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelLeft;
    private javax.swing.JPanel jPanelRight;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JPanel jPanelShowData;
    private javax.swing.JPanel jPanelUpper;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableDisease;
    private javax.swing.JTable jTableItem;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldCode;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
    private void setLanguage()
    {
        colNameDisease = new String[] {Language.getTextBundle("Code",language),Language.getTextBundle("Description",language)};
        colNameItem = new String[] {Language.getTextBundle("OrderItem",language)};     
        jLabelScreenedDisease.setText(Language.getTextBundle(jLabelScreenedDisease.getText(),language));
        jLabelSearch.setText(Language.getTextBundle(jLabelSearch.getText(),language));
        jButtonSearch.setText(Language.getTextBundle(jButtonSearch.getText(),language));
        
        jLabelCode.setText(Language.getTextBundle(jLabelCode.getText(),language));
        jLabelDescription.setText(Language.getTextBundle(jLabelDescription.getText(),language));
        jButtonAdd.setText(Language.getTextBundle(jButtonAdd.getText(),language));
        jButtonDelete.setText(Language.getTextBundle(jButtonDelete.getText(),language));
    }
}
