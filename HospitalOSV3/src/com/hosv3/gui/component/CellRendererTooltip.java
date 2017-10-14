/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hosv3.gui.component;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import com.hospital_os.object.*;

public class CellRendererTooltip extends JLabel implements TableCellRenderer {

    boolean isBordered = true;
    Color color = null;
    Color fontcolor = null;
    public CellRendererTooltip(boolean isBordered) {
        this.isBordered = isBordered;
        color = new Color(255,255,255);
        fontcolor= new Color(0,0,0);
        setOpaque(true);
    }

    public java.awt.Component getTableCellRendererComponent(JTable table, Object value
            , boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected)
            {
                setBackground(table.getSelectionBackground());
            }
            else
            {
                setBackground(this.color);
            }
            setText(String.valueOf(value));
            setToolTipText(String.valueOf(value));
            return this;
    }

}
