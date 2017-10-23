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
 * @author LionHeart
 */
public class CellEditorPanelRoom extends DefaultCellEditor{
    private PanelRoom thePanelRoom;
    JTextField theJT;
    public CellEditorPanelRoom(JTextField checkBox)
    {
        super(checkBox);
        theJT = checkBox;
    }
    public void setObject(PanelRoom panelRoom)
    {
        thePanelRoom = panelRoom;
        theJT.setText(panelRoom.tmp_name);
    }
    public Component getTableCellEditorComponent(JTable table, Object value,
                   boolean isSelected, int row, int column)
    {
        
        return thePanelRoom;
    }
}
