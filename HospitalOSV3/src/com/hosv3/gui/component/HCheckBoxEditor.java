/*
 * CheckBoxEdit.java
 *
 * Created on 24 ����Ҥ� 2547, 21:31 �.
 */

package com.hosv3.gui.component;
import com.hosv3.utility.Constant;
import com.hosv3.utility.connection.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
/**
 *
 * @author  tong
 */
public class HCheckBoxEditor extends DefaultCellEditor implements ItemListener 
{
    private JCheckBox button = new JCheckBox();
    private ExecuteControlInf theEC;
    private String type;
    
    ///////////////////////////////////////////////////////////////////////////
    public HCheckBoxEditor(JCheckBox checkBox) {
        super(checkBox);
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxContinueActionPerformed(evt);
            }
        });
    }
    ///////////////////////////////////////////////////////////////////////////
    public void setEControl(ExecuteControlInf ec){
        theEC = ec;
    }
    ///////////////////////////////////////////////////////////////////////////
    public void setType(String str){
        type = str;
    }
    ///////////////////////////////////////////////////////////////////////////
    public void jCheckBoxContinueActionPerformed(java.awt.event.ActionEvent evt)
    {
        Constant.println("jCheckBoxContinueActionPerformed");
        if(theEC==null)
            return;
        
        if(button.isSelected()){
            boolean b = theEC.execute(type + "1");
            if(!b) button.setSelected(false);
        }
        else{
            boolean b = theEC.execute(type + "0");
            if(!b) button.setSelected(true);
        }
    }
    ///////////////////////////////////////////////////////////////////////////
    public Component getTableCellEditorComponent(JTable table, Object value,
                   boolean isSelected, int row, int column)
    {
        if(value instanceof Boolean)
        {
            button.setSelected(((Boolean)value).booleanValue());                
        }
        if(value instanceof Hashtable)
        {
            Hashtable ht_status = (Hashtable)value;                
            button.setSelected(((Boolean)ht_status.get("request")).booleanValue());
            button.setEnabled(((Boolean)ht_status.get("status")).booleanValue());                
        }        
        return button;
    }
    ///////////////////////////////////////////////////////////////////////////
    //���繵�ͧ�տѧ�ѹ�����������к������騴����Ҥ�������������͡������������ 
    //�ҡ����տѧ�ѹ��� �ѹ��� reset ����������ʴ���ҷ������� set ������
    public Object getCellEditorValue() {
        return Boolean.valueOf(button.isSelected());
    }

    public void itemStateChanged(ItemEvent e) {
        super.fireEditingStopped();
    }
}
