/*
 * CheckBoxRenderer.java
 *
 * Created on 22 æƒ…¿“§¡ 2547, 20:01 π.
 */

package com.hospital_os.utility;
  import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author  tong
 */


public class CellRendererComboBox implements TableCellRenderer {
  public Component getTableCellRendererComponent(JTable table, Object value,
                   boolean isSelected, boolean hasFocus, int row, int column) {
    if (value==null) return null;
    return (Component)value;
  }
}



