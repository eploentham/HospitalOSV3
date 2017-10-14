/*
 * PanelYesNoRadioButton.java
 *
 * Created on 30 �ԧ�Ҥ� 2548, 17:34 �.
 */

package com.pcu.gui.utility.component;

import com.hospital_os.utility.Constant;

/**
 *
 * @author  tong(Padungrat)
 */
public class PanelYesNoRadioButton extends javax.swing.JPanel {
    private String select;
    /** Creates new form PanelYesNoRadioButton */
    public PanelYesNoRadioButton() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup = new javax.swing.ButtonGroup();
        jRadioButtonYes = new javax.swing.JRadioButton();
        jRadioButtonNo = new javax.swing.JRadioButton();

        setLayout(new java.awt.GridBagLayout());

        buttonGroup.add(jRadioButtonYes);
        jRadioButtonYes.setText("jRadioButton1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        add(jRadioButtonYes, gridBagConstraints);

        buttonGroup.add(jRadioButtonNo);
        jRadioButtonNo.setText("jRadioButton2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        add(jRadioButtonNo, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents
    
    /**����� ���Ѿ��ͧ panel ����ա�����͡�繨�ԧ������
     *@return �� boolean ����� true ���͡����繨�ԧ false ���͡����� ��
     */
    public boolean getBooleanSelect()
    {
        return jRadioButtonYes.isSelected();
    }
    
    /**����� ���Ѿ��ͧ panel ����ա�����͡�繨�ԧ������(1 ���� 0)
     *@return �� String  ����� 1 ��� true ���͡����繨�ԧ, 0 false ���͡����� ��
     */
    public String getStringSelect()
    {   select = "0";
        if(getBooleanSelect())
        {
            select = "1";
        }
        return select;
    }
    
    /**
     *  ��㹡�á�˹�������������� select ��� radio ����˹
     *@param selected �� String ����� 1 ��select ��Ƿ���繨�ԧ 0 �� select ��Ƿ������繨�ԧ
     */
    public void setSelected(String selected)
    {   setSelected(false);
        if(("1").equalsIgnoreCase(selected))
        {
            setSelected(true);
        }
        
    }
    
    /**
     *  ��㹡�á�˹�������������� select ��� radio ����˹
     *@param selected �� boolean ����� true ��select ��Ƿ���繨�ԧ false �� select ��Ƿ������繨�ԧ
     */
    public void setSelected(boolean selected)
    {
        if(selected)
        {
            jRadioButtonYes.setSelected(selected);
        }
        else
        {
            jRadioButtonNo.setSelected(!selected);
        }
         
    }
    
    //amp
    public void setEnabled(boolean flag)
    {
        if(flag)
        {
            jRadioButtonYes.setEnabled(true);
            jRadioButtonNo.setEnabled(true);
        }
        else
        {
            jRadioButtonYes.setEnabled(false);
            jRadioButtonNo.setEnabled(false);
        }
    }
    
    /**��㹡�á�˹���ͤ����ʴ� 
     *@param yes �繢�ͤ����ͧ radiobuttonyes 
     *@param no �繢�ͤ����ͧ radiobuttonno
     */
    public void setTextYesNo(String yes,String no)
    {
        jRadioButtonNo.setText(no);
        jRadioButtonYes.setText(yes);
                
    }
    
    /**����� JRadioButton �ͧ jRadioButtonNo
     *@return �� Object �ͧ jRadioButtonNo
     */
    public javax.swing.JRadioButton getRadioButtonNo(){
      return jRadioButtonNo;
    }
    
    /**����� JRadioButton �ͧ jRadioButtonYes
     *@return �� Object �ͧ jRadioButtonYes
     */
    public javax.swing.JRadioButton getRadioButtonYes(){
      return jRadioButtonYes;
    }
    
    /**
     *  ������Ѻ setEnable �ͧ RadioButton
     *@param isEnable �� boolean ����� true �� setEnable(true) ��� false �� setEnable(false)
     */
    public void setEnableRadioButton(boolean isEnable){
      jRadioButtonYes.setEnabled(isEnable);
      jRadioButtonNo.setEnabled(isEnable);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JRadioButton jRadioButtonNo;
    private javax.swing.JRadioButton jRadioButtonYes;
    // End of variables declaration//GEN-END:variables
    
    public static void main(String[] argv)
    {
        javax.swing.JFrame frame = new javax.swing.JFrame();
        PanelYesNoRadioButton p = new PanelYesNoRadioButton();
        frame.getContentPane().add(p);
        p.setSelected("1");
        p.setTextYesNo("��","�����");
        Constant.println(p.getStringSelect());
        frame.setVisible(true);
    }
}