/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hosv3.gui.component;

import java.awt.Color;
import java.awt.Component;
import java.util.EventObject;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author henbe
 */
public class TTableCellRenderer implements TableCellRenderer,TableCellEditor{

    public TTableCellRenderer() {
    } 

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        try{
            JLabel jl = (JLabel)value;
            if(isSelected)
                jl.setBackground(Color.PINK);
            else
                jl.setBackground(Color.WHITE);

            return (Component)value;
        }catch(Exception e){
            e.printStackTrace();
            return (Component)value;
        }
            
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return (Component)value;
    }

    public Object getCellEditorValue() {
        return null;
    }

    public boolean isCellEditable(EventObject anEvent) {
        return false;
    }

    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    public boolean stopCellEditing() {
        return true;
    }

    public void cancelCellEditing() {
    }

    public void addCellEditorListener(CellEditorListener l) {
    }

    public void removeCellEditorListener(CellEditorListener l) {
    }
}
