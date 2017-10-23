/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hosv3.gui.component;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author regionton
 */
public class TestEditer extends DefaultCellEditor{
    private PanelBed thePanelBed;
    public TestEditer(JTextField jt)
    {
        super(jt);
    }
    public void setObject(PanelBed panelBed)
    {
        thePanelBed = panelBed;
    }
    public Component getTableCellEditorComponent(JTable table, Object value,
                   boolean isSelected, int row, int column)
    {
        if (isSelected) 
        {
            thePanelBed.jLabel1.setOpaque(true);
            thePanelBed.jPanel1.setOpaque(true);
            thePanelBed.jPanel1.setBackground(Color.LIGHT_GRAY);
            thePanelBed.jLabel1.setBackground(Color.LIGHT_GRAY);
        } 
        else 
        {
            thePanelBed.jLabel1.setOpaque(true);
            thePanelBed.jPanel1.setOpaque(true);
            thePanelBed.jPanel1.setBackground(Color.RED);
            thePanelBed.jLabel1.setBackground(Color.RED);
        }
        return thePanelBed;
    }
}
