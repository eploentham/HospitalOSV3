/*
 * PanelSetupEpiGroupItem.java
 *
 * Created on 6 �չҤ� 2549, 16:45 �.
 */

package com.setupreport.gui.panel;

import com.setupreport.utility.TableModelGUI;
import com.setupreport.utility.Language;
import com.setupreport.gui.dialog.DialogSearchEpi;
import com.setupreport.usecase.SaveEpiGroupItem;
import com.setupreport.manage.HosManage;
import com.setupreport.object.*;

import javax.swing.JOptionPane;
import java.util.Vector;
import javax.swing.*;
/**
 *
 * @author  Ojika
 */
public class PanelSetupEpiGroupItem extends javax.swing.JPanel implements SaveEpiGroupItem
{
    
    /** Creates new form PanelSetupEpiGroupItem */
    HosManage theHosManage;
    TableModelGUI theTableModelGUI;
    String[] colNameGroup;
    String[] colNameItem;
    final String panelname = "EpiGroupItem";
    int language = 1;
    /**���Ǩ�ͺ��Ң����� item ������͡����Ѻ����������������㹢���������������*/
    int status = 1;
    
    Vector vEpiGroup;
    EpiGroup theEpiGroup;
    Vector vEpiGroupItem;
    EpiGroupItem theEpiGroupItem;
    
    public PanelSetupEpiGroupItem(HosManage hosManage)
    {
        this.theHosManage = hosManage;
        initComponents();
        setLanguage();
        this.theHosManage.theHosControl.theHosSubject.theEpiGroupItemSubject.registerMainReportManage(this);
        Language.getTextBundle(panelname, language);
        searchEpiGroup();
        
        // ����ա����ҹ �����������͹
        jButtonSave.setVisible(false);
    }
    
    public String getPanelName()
    {
        return panelname;
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
        jPanelTop = new javax.swing.JPanel();
        jLabelSetupName = new javax.swing.JLabel();
        jPanelLeft = new javax.swing.JPanel();
        jPanelSearch = new javax.swing.JPanel();
        jLabelSearch = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jPanelShowList = new javax.swing.JPanel();
        jScrollPaneShowList = new javax.swing.JScrollPane();
        jTableShowList = new javax.swing.JTable();
        jPanelRight = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabelCode = new javax.swing.JLabel();
        jLabelDescription = new javax.swing.JLabel();
        jTextFieldCode = new javax.swing.JTextField();
        jTextFieldDescrpiton = new javax.swing.JTextField();
        jPanelDisease = new javax.swing.JPanel();
        jPanelShowDisease = new javax.swing.JPanel();
        jScrollPaneShowDisease = new javax.swing.JScrollPane();
        jTableShowListItem = new javax.swing.JTable();
        jPanelControl = new javax.swing.JPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabelSetupName.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabelSetupName.setText("EpiGroupItem");
        jPanelTop.add(jLabelSetupName);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jPanelTop, gridBagConstraints);

        jPanelLeft.setLayout(new java.awt.GridBagLayout());

        jPanelLeft.setMinimumSize(new java.awt.Dimension(200, 53));
        jPanelLeft.setPreferredSize(new java.awt.Dimension(200, 433));
        jPanelSearch.setLayout(new java.awt.GridBagLayout());

        jLabelSearch.setText("Search");
        jPanelSearch.add(jLabelSearch, new java.awt.GridBagConstraints());

        jTextFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
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
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelSearch.add(jButtonSearch, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelLeft.add(jPanelSearch, gridBagConstraints);

        jPanelShowList.setLayout(new java.awt.GridBagLayout());

        jTableShowList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableShowList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableShowListMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableShowListMouseReleased(evt);
            }
        });

        jScrollPaneShowList.setViewportView(jTableShowList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelShowList.add(jScrollPaneShowList, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        jPanelLeft.add(jPanelShowList, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel1.add(jPanelLeft, gridBagConstraints);

        jPanelRight.setLayout(new java.awt.GridBagLayout());

        jPanelRight.setMinimumSize(new java.awt.Dimension(350, 24));
        jPanelRight.setPreferredSize(new java.awt.Dimension(350, 24));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabelCode.setText("Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 0, 0);
        jPanel3.add(jLabelCode, gridBagConstraints);

        jLabelDescription.setText("Description");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel3.add(jLabelDescription, gridBagConstraints);

        jTextFieldCode.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 5);
        jPanel3.add(jTextFieldCode, gridBagConstraints);

        jTextFieldDescrpiton.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 5);
        jPanel3.add(jTextFieldDescrpiton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanelRight.add(jPanel3, gridBagConstraints);

        jPanelDisease.setLayout(new java.awt.GridBagLayout());

        jPanelDisease.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanelShowDisease.setLayout(new java.awt.GridBagLayout());

        jTableShowListItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableShowListItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableShowListItemMouseReleased(evt);
            }
        });

        jScrollPaneShowDisease.setViewportView(jTableShowListItem);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelShowDisease.add(jScrollPaneShowDisease, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanelDisease.add(jPanelShowDisease, gridBagConstraints);

        jPanelControl.setLayout(new java.awt.GridBagLayout());

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

        jPanelControl.add(jButtonAdd, new java.awt.GridBagConstraints());

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
        jPanelControl.add(jButtonDelete, gridBagConstraints);

        jButtonSave.setText("Save");
        jButtonSave.setMaximumSize(new java.awt.Dimension(75, 24));
        jButtonSave.setMinimumSize(new java.awt.Dimension(75, 24));
        jButtonSave.setPreferredSize(new java.awt.Dimension(75, 24));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelControl.add(jButtonSave, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanelDisease.add(jPanelControl, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        jPanelRight.add(jPanelDisease, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 0, 0);
        jPanel1.add(jPanelRight, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel1, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    private void jTableShowListMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTableShowListMouseReleased
    {//GEN-HEADEREND:event_jTableShowListMouseReleased
        // ���͡������Ѥ�չ��ô�����
        selectEpiGroup(jTableShowList.getSelectedRow());
    }//GEN-LAST:event_jTableShowListMouseReleased

    private void jTableShowListItemMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTableShowListItemMouseReleased
    {//GEN-HEADEREND:event_jTableShowListItemMouseReleased
        // ���͡������Ѥ�չ��ô�����
        selectEpiGroupItem(jTableShowListItem.getSelectedRow());
    }//GEN-LAST:event_jTableShowListItemMouseReleased

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonSaveActionPerformed
    {//GEN-HEADEREND:event_jButtonSaveActionPerformed
        // �ѹ�֡��¡���Ѥ�չ�ͧ������Ѥ�չ��ô�����
        saveEpiGroupItem();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonDeleteActionPerformed
    {//GEN-HEADEREND:event_jButtonDeleteActionPerformed
        // ź��¡���Ѥ�չ�ͧ������Ѥ�չ��ô�����
        int confirm_del = JOptionPane.showConfirmDialog(this,Language.getTextBundle("confirm_del_effect_report", language),Language.getTextBundle("confirm_del", language),JOptionPane.YES_NO_OPTION);
        if(confirm_del == 0)
        {
            deleteEpiGroupItem();
        }
        
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAddActionPerformed
    {//GEN-HEADEREND:event_jButtonAddActionPerformed
        // ������¡���Ѥ�չ�ͧ������Ѥ�չ��ô�����
        addEpiGroupItem();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jTableShowListMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTableShowListMouseClicked
    {//GEN-HEADEREND:event_jTableShowListMouseClicked
        // ���͡������Ѥ�չ��ô�����
        selectEpiGroup(jTableShowList.getSelectedRow());
    }//GEN-LAST:event_jTableShowListMouseClicked

    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonSearchActionPerformed
    {//GEN-HEADEREND:event_jButtonSearchActionPerformed
        // ���ҡ�����Ѥ�չ��ô�����
        searchEpiGroup();
    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextFieldSearchActionPerformed
    {//GEN-HEADEREND:event_jTextFieldSearchActionPerformed
        // ���ҡ�����Ѥ�չ��ô�����
        searchEpiGroup();
    }//GEN-LAST:event_jTextFieldSearchActionPerformed
    
    /**
     * �ѹ�֡��¡���Ѥ�չ �ͧ������Ѥ�չ��ô��������
     * @Author Ojika
     * @Date 09/03/2549
     **/
    private void saveEpiGroupItem()
    {
        if(this.theEpiGroup != null && this.vEpiGroupItem != null && this.vEpiGroupItem.size() > 0)
        {
            // �բ���������Ѻ��úѹ�֡ �觢�����仵�Ǩ�ͺ��кѹ�֡           
            this.theHosManage.theHosControl.theEpiGroupItemControl.checkSaveEpiGroupItem(this.vEpiGroupItem);
        }
    }    
    
    /**
     * ����Ѻ���ҡ�����Ѥ�չ ��� �觢�������ʴ�㹵��ҧ
     * @Author Ojika
     * @Date 08/03/2549
     **/
    private void searchEpiGroupItem(String epiGroupId)
    {
        // ������¡�� ���� keyword 令�
        this.vEpiGroupItem = new Vector();
        
        this.vEpiGroupItem = this.theHosManage.theHosControl.theEpiGroupItemControl.selectEpiGroupItemByEpiGroupId(epiGroupId);
                
        // �觢����ŷ����ҡ��ä��ҹ����ʴ��ѧ���ҧ
        setTableEpiGroupItem(this.vEpiGroupItem);     
    }
    
    /**
     * ����Ѻ���ҡ�����Ѥ�չ ��� �觢�������ʴ�㹵��ҧ
     * @Author Ojika
     * @Date 08/03/2549
     **/
    private void searchEpiGroup()
    {
        // ������¡�� ���� keyword 令�
        this.vEpiGroup = new Vector();
        String keyword = jTextFieldSearch.getText().trim();
        
        this.vEpiGroup = this.theHosManage.theHosControl.theEpiGroupItemControl.selectEpiGroupByKeyword(keyword);
        
        // �觢����ŷ����ҡ��ä��ҹ����ʴ��ѧ���ҧ
        setTableEpiGroup(this.vEpiGroup);
        
        this.vEpiGroupItem = null;
        clearGUI();
    }
    
    /**
     * �ʴ� Dialog ����Ѻ���͡��¡���Ѥ�չ
     * @Author Ojika
     * @Date 08/03/2549 
     **/
    private void addEpiGroupItem()
    {
        DialogSearchEpi dialog = new DialogSearchEpi(new JFrame(), true,this.theHosManage, 2);
        dialog.setVisible(true);
    }
    
    /**
     * ź��¡�÷�����͡�͡�ҡ�ҹ������
     * @Author Ojika
     * @Date 08/03/2549
     **/
    private void deleteEpiGroupItem()
    {
        if(this.theEpiGroupItem != null)
        {
            // �觤�Ңͧ Object �ź
            this.theHosManage.theHosControl.theEpiGroupItemControl.deleteEpiGroupItemByKeyId(this.theEpiGroupItem); 
            // �駺͡ʶҹС��ź��¡��         
            
            // ���¡�����ա����
            searchEpiGroupItem(this.theEpiGroup.getObjectId());
        }        
    }    
    
    /**
     * ���͡��¡���Ѥ�չ�ͧ������Ѥ�չ ��� Set ������Ѻ Object �ͧ��¡���Ѥ�չ
     * @Author Ojika
     * @Date 08/03/2549
     **/
     private void selectEpiGroupItem(int row)
     {
        if(row > -1)
        {
            // �ա�����͡�ҡ���ҧ
            if(this.vEpiGroup != null)
            {                
                this.theEpiGroupItem = (EpiGroupItem)vEpiGroupItem.get(row);                
            }
        }
     }
     
    /**
     * ���͡��¡�á�����Ѥ�չ ��� �ʴ���������´
     * @Author Ojika
     * @Date 08/03/2549
     **/
     private void selectEpiGroup(int row)
     {
        if(row > -1)
        {
            this.vEpiGroupItem = null;
            // �ա�����͡�ҡ���ҧ
            if(this.vEpiGroup != null)
            {           
                this.theEpiGroup = (EpiGroup)vEpiGroup.get(row);
                // �֧������ EpiItem
                this.vEpiGroupItem = this.theHosManage.theHosControl.theEpiGroupItemControl.selectEpiGroupItemByEpiGroupId(this.theEpiGroup.getObjectId());
                // �ʴ���������´������    
                setObjectToData();
            }
        }
     }
     
     /**
      * �ʴ���Ҩҡ Object ���Ѻ GUI
      * @Author Ojika
      * @Date 08/03/2549
      **/
     private void setObjectToData()
     {
        if(this.theEpiGroup != null)
        {
            jTextFieldCode.setText(this.theEpiGroup.epi_group_number);
            jTextFieldDescrpiton.setText(this.theEpiGroup.epi_group_description);
            
            if(this.vEpiGroupItem != null && this.vEpiGroupItem.size() > 0)
            {
                // ��� Set ���ҧ
                setTableEpiGroupItem(this.vEpiGroupItem);
            }
            else
            {
                setTableEpiGroupItem(null);
            }
        }
        else
        {
            clearGUI();
        }
     }
     
     /**
      * ����������ź� Gui 
      * @Author Ojika
      * @Date 08/03/2549
      **/
     private void clearGUI()
     {
        jTextFieldCode.setText("");
        jTextFieldDescrpiton.setText("");
        
        setTableEpiGroupItem(null);
     }         
    
    /**
     * �ʴ������ŷ����ҡ��ä���㹵��ҧ
     * @author Ojika
     * @Date 08/03/2549
     **/
    private void setTableEpiGroup(Vector vData)
    {       
        theTableModelGUI = new TableModelGUI(colNameGroup, 0);
        if(vData != null)
        {
            int size = vData.size();
            theTableModelGUI = new TableModelGUI(colNameGroup, size);
            theEpiGroup = null;
            for(int i = 0 ; i < size ;i++)
            {
                theEpiGroup = (EpiGroup)vData.get(i);
                theTableModelGUI.setValueAt(theEpiGroup.epi_group_number,i, 0);
                theTableModelGUI.setValueAt(theEpiGroup.epi_group_description,i, 1);
                theEpiGroup = null;
            }
            
        }
        
        jTableShowList.setModel(theTableModelGUI);
        
        jTableShowList.getColumnModel().getColumn(0).setPreferredWidth(25);
        jTableShowList.getColumnModel().getColumn(1).setPreferredWidth(100);
    }   
    
    /**
     * �ʴ������ŷ����ҡ��ä���㹵��ҧ 
     * @author Ojika
     * @Date 08/03/2549
     **/
    private void setTableEpiGroupItem(Vector vData)
    {       
        theTableModelGUI = new TableModelGUI(colNameItem, 0);
        if(vData != null)
        {
            int size = vData.size();
            theTableModelGUI = new TableModelGUI(colNameItem, size);
            theEpiGroupItem = null;
            for(int i = 0 ; i < size ;i++)
            {
                theEpiGroupItem = (EpiGroupItem)vData.get(i);
                theTableModelGUI.setValueAt(theEpiGroupItem.epi_group_item_description,i, 0); 
                theEpiGroupItem = null;
            }
            
        }
        
        jTableShowListItem.setModel(theTableModelGUI);
        
    }  
    
    private void setLanguage()
    {
        colNameGroup = new String[] {Language.getTextBundle("Code",language),Language.getTextBundle("Description",language)};
        colNameItem = new String[] {Language.getTextBundle("Description",language)};
        
        jLabelSetupName.setText(Language.getTextBundle(jLabelSetupName.getText(),language));
        
        jLabelSearch.setText(Language.getTextBundle(jLabelSearch.getText(),language));
        jButtonSearch.setText(Language.getTextBundle(jButtonSearch.getText(),language));
        
        jLabelCode.setText(Language.getTextBundle(jLabelCode.getText(),language));
        jLabelDescription.setText(Language.getTextBundle(jLabelDescription.getText(),language));
        jButtonSave.setText(Language.getTextBundle(jButtonSave.getText(),language));
        jButtonAdd.setText(Language.getTextBundle(jButtonAdd.getText(),language));
        jButtonDelete.setText(Language.getTextBundle(jButtonDelete.getText(),language));
    }
    
    /**
     * �Ѻ Vector �ͧ Item ������͡���� save ����¡���Ѥ�չ
     * @Author Ojika
     * @Date 08/03/2549
     **/
    public void notifySaveEpiGroupItem(Vector vEpi)
    {
        if(vEpi != null)
        {
            if(this.theEpiGroup != null)
            {
                Epi theEpi = new Epi();
                int size = vEpi.size();
                for(int i =0;i<size;i++)
                {
                    theEpi = (Epi)vEpi.get(i);
                    
                    this.theEpiGroupItem = new EpiGroupItem();
                    
                    //this.theEpiGroupItem.r_epi_group_item_id = theEpi.getObjectId();
                    this.theEpiGroupItem.r_epi_group_id = this.theEpiGroup.getObjectId();
                    this.theEpiGroupItem.b_health_epi_group_id = theEpi.getObjectId();
                    this.theEpiGroupItem.epi_group_item_description = theEpi.health_epi_group_description;
                    
                    checkSameData(this.theEpiGroupItem);
                    this.theEpiGroupItem = null;
                    searchEpiGroupItem(this.theEpiGroup.getObjectId());
                }
                theEpi = null;
                
                //���¡ Function ��ä�����������¹���������
                searchEpiGroupItem(this.theEpiGroup.getObjectId());
            }
            else
            {
                JOptionPane.showMessageDialog(this,Language.getTextBundle("PleaseSelectEpiGroup", language),Language.getTextBundle("Warning", language),JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    private void checkSameData(EpiGroupItem theEGI)
    {
        if(this.vEpiGroupItem != null)
        {
            int size = this.vEpiGroupItem.size();
            if(size > 0)
            {
                for(int i =0;i<size;i++)
                {
                    if(!theEGI.b_health_epi_group_id.equals(((EpiGroupItem)this.vEpiGroupItem.get(i)).b_health_epi_group_id))
                    {
                        status = 1;
                    }
                    else
                    {
                        status = 0;
                        JOptionPane.showMessageDialog(this,Language.getTextBundle("Same_Item", language) + theEGI.epi_group_item_description ,Language.getTextBundle("Warning", language),JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                }
                if(status != 0)
                {
                    // ����բ����ū����仺ѹ�֡
                    this.theHosManage.theHosControl.theEpiGroupItemControl.saveEpiGroupItem(theEGI);
                }
            }
        }
        else
        {
            // ����բ���������Ѻ��Ǩ�ͺ��仺ѹ�֡
            this.theHosManage.theHosControl.theEpiGroupItemControl.saveEpiGroupItem(theEGI);
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JLabel jLabelCode;
    private javax.swing.JLabel jLabelDescription;
    private javax.swing.JLabel jLabelSearch;
    private javax.swing.JLabel jLabelSetupName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelControl;
    private javax.swing.JPanel jPanelDisease;
    private javax.swing.JPanel jPanelLeft;
    private javax.swing.JPanel jPanelRight;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JPanel jPanelShowDisease;
    private javax.swing.JPanel jPanelShowList;
    private javax.swing.JPanel jPanelTop;
    private javax.swing.JScrollPane jScrollPaneShowDisease;
    private javax.swing.JScrollPane jScrollPaneShowList;
    private javax.swing.JTable jTableShowList;
    private javax.swing.JTable jTableShowListItem;
    private javax.swing.JTextField jTextFieldCode;
    private javax.swing.JTextField jTextFieldDescrpiton;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
    
}
