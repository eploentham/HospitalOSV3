/*
 * CheckBoxEdit.java
 *
 * Created on 24 æƒ…¿“§¡ 2547, 21:31 π.
 */
package com.hospital_os.utility;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author  tong
 */
public class ComBoBoxEditor extends DefaultCellEditor implements ItemListener {
    private JComboBox button;
    public ComBoBoxEditor(JComboBox comboBox) {
    super(comboBox);
    }
    public Component getTableCellEditorComponent(JTable table, Object value,
                   boolean isSelected, int row, int column) {
        if (value==null) 
            return null;
        button = (JComboBox)value;
        button.addItemListener(this);
        return (Component)value;
    }
    
    public Object getCellEditorValue() {
        try{   
            button.removeItemListener(this);
        }
        catch(Exception ex)
        {
            Constant.println(ex.getMessage());
        }
        return button;
    }
    public void itemStateChanged(ItemEvent e) {
        super.fireEditingStopped();
    }
}
