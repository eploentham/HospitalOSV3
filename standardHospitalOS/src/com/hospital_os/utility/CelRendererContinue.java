/*
 * OrderContinueImageTableCellRendererContinue.java
 *
 * Created on 3 æƒ…¿“§¡ 2547, 10:38 π.
 */

package com.hospital_os.utility;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author  amp
 */
public class CelRendererContinue extends JLabel implements TableCellRenderer {
    
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus,
            int row, int column) 
    {
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        if (isSelected) {
            this.setBackground(table.getSelectionBackground());
            this.setForeground(table.getSelectionForeground());
        } 
        else {
            this.setBackground(table.getBackground());
            this.setForeground(table.getForeground());
        }
        try {
            if(((String)value).equals("1")) {
                this.setIcon(new ImageIcon(getClass().getResource("/com/hospital_os/images/order_continue.gif")));
                return this;
            }
        } 
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        
        this.setIcon(new ImageIcon(getClass().getResource("/com/hospital_os/images/order_discontinue.gif")));
        return this;
    }
}
