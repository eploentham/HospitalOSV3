/*
 * CheckBoxEdit.java
 *
 * Created on 24 ����Ҥ� 2547, 21:31 �.
 */

package com.hospital_os.utility;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author  tong
 */
public class CheckBoxEditor extends DefaultCellEditor implements ItemListener 
{
    private JCheckBox button;
    private boolean is_edit = true;
    
    public CheckBoxEditor(JCheckBox checkBox) {
        super(checkBox);
        button = checkBox;
    }
    public void setCheckBoxEnabled(boolean b){
        is_edit = b;
    }
    public Component getTableCellEditorComponent(JTable table, Object value,
                   boolean isSelected, int row, int column) 
    {
        if (value==null) 
            return null;
        try{
            Constant.println(value.getClass().toString());
            button = (JCheckBox)value;
            button.setEnabled(is_edit);//henbe_test
            button.addItemListener(this);
        }catch(Exception e){
            Constant.println("___CheckBoxEditor " + e.getMessage());
            Boolean val = (Boolean)value;
            button.setSelected(val.booleanValue());
        }
        return button;
    }
    ///////////////////////////////////////////////////////////////////////////
    //���繵�ͧ�տѧ�ѹ�����������к������騴����Ҥ�������������͡������������ 
    //�ҡ����տѧ�ѹ��� �ѹ��� reset ����������ʴ���ҷ������� set ������
    public Object getCellEditorValue() {
        return Boolean.valueOf(button.isSelected());
    }
    
    //    
    //    public Object getCellEditorValue() {
    //    try{   
    //        button.removeItemListener(this);
    //    }
    //    catch(Exception ex)
    //    {
    //        ex.printStackTrace(Constant.getPrintStream());
    //    }
    //    return button;
    //  }

    public void itemStateChanged(ItemEvent e) {
        super.fireEditingStopped();
    }
}
