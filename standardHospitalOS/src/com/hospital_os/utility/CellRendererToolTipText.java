/*
 * CellRendererToolTipText.java
 *
 * Created on 24 ¡’π“§¡ 2549, 11:45 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.utility;
import javax.swing.*;

import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;
/**
 *
 * @author tong(Padungrat)
 */
public class CellRendererToolTipText extends JLabel implements TableCellRenderer 
{
   
    boolean isBordered = true;
    Color color = null;
    Color fontcolor = null;
    
    public CellRendererToolTipText(boolean isBordered) {
        this.isBordered = isBordered;
        this.color = new Color(255,255,255);
        this.fontcolor= new Color(0,0,0);
        //this.setHorizontalAlignment(this.CENTER);
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
//                    setForeground(this.color);
                } 
                else 
                {
                    
                    setBackground(this.color);
//                    setForeground(this.fontcolor);
              
                }
            }
            setToolTipText("<html><BODY BGCOLOR = #E7FAAF>"+ name + "</BODY></html>");
            setText(name);
         
         
        }
        
        
        return this;
    }
    
}
