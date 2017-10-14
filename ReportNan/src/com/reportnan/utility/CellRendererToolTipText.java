package com.reportnan.utility;

import javax.swing.JLabel;
import javax.swing.JTable;

import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;


public class CellRendererToolTipText extends JLabel implements TableCellRenderer 
{
   
    boolean isBordered = true;
    Color color = null;
    Color fontcolor = null;
    
    public CellRendererToolTipText(boolean isBordered) {
        this.isBordered = isBordered;
        this.color = new Color(255,255,255);
        this.fontcolor= new Color(0,0,0);
        setOpaque(true); 
    }
    
    public Component getTableCellRendererComponent(
                            JTable table, Object namecell,
                            boolean isSelected, boolean hasFocus,
                            int row, int column) 
    { 
        if(namecell!=null)
        {
            String name = (String)namecell;
            
           
            if (isBordered) 
            {
                if (isSelected) 
                {
                    setBackground(table.getSelectionBackground());
                   /* setForeground(this.fontcolor);*/
                    setForeground(this.color);
                } 
                else 
                {
                    
                    setBackground(this.color);
                    setForeground(this.fontcolor);
              
                }
            }
            setToolTipText("<html><BODY BGCOLOR = #E7FAAF>"+ name + "</BODY></html>");
            setText(name);
         
         
        }
        
        
        return this;
    }
    
    
}
