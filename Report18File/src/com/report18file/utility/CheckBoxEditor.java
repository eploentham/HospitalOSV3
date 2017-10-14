/*
 * CheckBoxEditor.java
 *
 * Created on 29 กรกฎาคม 2548, 11:18 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.utility;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
/**
 *
 * @author Noom
 */
public class CheckBoxEditor extends    DefaultCellEditor
        implements ItemListener {
    private JCheckBox button;
    
    public CheckBoxEditor(JCheckBox checkBox) {
        super(checkBox);
    }
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (value==null) return null;
        button = (JCheckBox)value;
        button.addItemListener(this);
        return (Component)value;
    }
    
    public Object getCellEditorValue() {
        try{
            button.removeItemListener(this);
        } catch(Exception ex) {
        }
        return button;
    }
    
    public void itemStateChanged(ItemEvent e) {
        super.fireEditingStopped();
    }
}
