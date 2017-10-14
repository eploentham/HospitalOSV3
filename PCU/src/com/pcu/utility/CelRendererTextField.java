/*
 * CelRendererTextField.java
 *
 * Created on 5 กันยายน 2548, 14:54 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.utility;

import java.awt.*;
import javax.swing.table.*;
import javax.swing.JTable;
/**
 *
 * @author amp
 */
public class CelRendererTextField implements TableCellRenderer
{    
     public Component getTableCellRendererComponent(JTable table, Object value,
                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (value==null) return null;
        return (Component)value;
  }
    
}