/*
 * CellRendererOrderItem.java
 *
 * Created on 31 สิงหาคม 2548, 10:53 น.
 */

package com.hosv3.gui.component;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

import com.hospital_os.object.*;

/**
 *
 * @author  kingland
 */
public class CellRendererItem extends JLabel implements TableCellRenderer {
    
    boolean isBordered = true;
    Color color = null;
    Color fontcolor = null;
    /** Creates a new instance of CellRendererOrderItem */
    public CellRendererItem(boolean isBordered) {
        this.isBordered = isBordered;
        color = new Color(255,255,255);
        fontcolor= new Color(0,0,0);
        setOpaque(true); 
    }
    
    public java.awt.Component getTableCellRendererComponent(JTable table, Object value
            , boolean isSelected, boolean hasFocus, int row, int column) {
            Item i = (Item) value;
            if (isSelected) 
            {
                setBackground(table.getSelectionBackground());
//               // setForeground(this.fontcolor);
            } 
            else 
            {
                setBackground(this.color);
//                //setForeground(this.fontcolor);
            }
            setText(i.common_name);
            setToolTipText(i.common_name);
            return this;       
    }
    
}
