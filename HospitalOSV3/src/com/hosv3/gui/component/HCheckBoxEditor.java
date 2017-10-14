/*
 * CheckBoxEdit.java
 *
 * Created on 24 พฤษภาคม 2547, 21:31 น.
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
    //จำเป็นต้องมีฟังชันนี้เพราะว่าระบบเอาไว้จดจำว่าค่าเดิมที่เคยเลือกไว้เป็นเท่าไหร่ 
    //หากไม่มีฟังชันนี้ มันก็จะ reset ข้อมูลไม่แสดงค่าที่เราเคย set เอาไว้
    public Object getCellEditorValue() {
        return Boolean.valueOf(button.isSelected());
    }

    public void itemStateChanged(ItemEvent e) {
        super.fireEditingStopped();
    }
}
