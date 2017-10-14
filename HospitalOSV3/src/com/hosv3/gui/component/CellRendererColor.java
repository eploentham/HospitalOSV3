/* 
 * ColorRenderer.java (compiles with releases 1.2, 1.3, and 1.4) is used by 
 * TableDialogEditDemo.java.
 */

package com.hosv3.gui.component;

import com.hospital_os.utility.Gutil;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;

public class CellRendererColor extends JLabel implements TableCellRenderer {
    Border unselectedBorder = null;
    Border selectedBorder = null;
    boolean isBordered = true;

    public CellRendererColor(boolean isBordered) {
        this.isBordered = isBordered;
        setOpaque(true); //MUST do this for background to show up.
    }

    public Component getTableCellRendererComponent(
                            JTable table, Object color,
                            boolean isSelected, boolean hasFocus,
                            int row, int column) {
                                
        String dd = (String)color;
        String col = dd.substring(0,dd.indexOf("|"));
        String ss = dd.substring(dd.indexOf("|")+1);
         //             Constant.println(">>>>>>>>>>>>" + col);          
        Color newColor = Color.white;
        try{ newColor = Gutil.reconvertColor(col);//(Color)color;
        }catch(Exception e){}//Constant.println(e.getMessage());}
        setBackground(newColor);
        if (isBordered) {
            if (isSelected) {
                if (selectedBorder == null) {
                    selectedBorder = BorderFactory.createMatteBorder(2,5,2,5,
                                              table.getSelectionBackground());
                }
                setBorder(selectedBorder);
            } else {
                if (unselectedBorder == null) {
                    unselectedBorder = BorderFactory.createMatteBorder(2,5,2,5,
                                              table.getBackground());
                }
                setBorder(unselectedBorder);
            }
        }
        
       // setToolTipText("RGB value: " + newColor.getRed() + ", "
       //                              + newColor.getGreen() + ", "
       //                              + newColor.getBlue());
        dd = null;
        col = null;
        //setText(ss);
        setToolTipText(ss);
        return this;
    }
}
