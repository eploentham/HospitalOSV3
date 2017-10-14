/*
 * CheckBoxRenderer.java
 *
 * Created on 22 æƒ…¿“§¡ 2547, 20:01 π.
 */

package com.hospital_os.utility;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
/**
 *
 * @author  tong
 */


public class CellRendererCheckBox extends JCheckBox implements TableCellRenderer 
{
    public CellRendererCheckBox(){
        setHorizontalAlignment(this.CENTER);
    }
    public Component getTableCellRendererComponent(JTable table, Object value,
                   boolean isSelected, boolean hasFocus, int row, int column) 
    {
        if (isSelected) 
        {
            this.setBackground(table.getSelectionBackground());
            this.setForeground(table.getSelectionForeground());
        }
        else 
        {
            this.setBackground(table.getBackground());
            this.setForeground(table.getForeground());
        }   
        try{
            if(value instanceof Boolean)
            {
                setSelected(((Boolean)value).booleanValue());                
            }
            if(value instanceof Hashtable)
            {
                Hashtable ht_status = (Hashtable)value;                
                setSelected(((Boolean)ht_status.get("request")).booleanValue());
                setEnabled(((Boolean)ht_status.get("status")).booleanValue());                
            }
            return this;
        }
        catch(Exception e)
        {
            Constant.println(e.getMessage());
            return this;
        }
    }
}



