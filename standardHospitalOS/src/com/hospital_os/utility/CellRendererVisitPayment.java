/*
 * CellRendererVisitPayment.java
 *
 * Created on 4 เมษายน 2549, 10:59 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hospital_os.utility;
import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;
import javax.swing.*;
import java.awt.Color;
import java.awt.Component;

/**
 * ใช้ในการแสดง tooltiptext ให้กับ cell ของ สิทธิการรักษา
 * @author tong(Padungrat)
 */
public class CellRendererVisitPayment  extends JLabel implements TableCellRenderer {
    boolean isBordered = true;
    Color color = null;
    Color fontcolor = null;
    ComboFix theComboFix =  null;
    /** Creates a new instance of CellRendererVisitPayment */
    public CellRendererVisitPayment(boolean isBordered) {
        this.isBordered = isBordered;
        this.color = new Color(255,255,255);
        this.fontcolor= new Color(0,0,0);
        setOpaque(true);
    }
    public Component getTableCellRendererComponent(JTable table, Object combofix
        ,boolean isSelected, boolean hasFocus,int row, int column) 
    {
        this.setText("");
        this.setToolTipText("");
        if(combofix != null)
        {
            if (isBordered) 
            {
                if (isSelected) 
                {
                    setBackground(table.getSelectionBackground());                   
                    setForeground(this.fontcolor);
                } 
                else 
                {
                    setBackground(this.color);
                    setForeground(this.fontcolor);
                }
            }
            if(combofix instanceof ComboFix)
            {
                theComboFix = (ComboFix)combofix;
                this.setText(theComboFix.code);
                this.setToolTipText("<html>" + theComboFix.name+ "</html>");
            }
        }
        return this;
    }
}
