/*
 * ColumnTableRenderer.java
 *
 * Created on 5 กันยายน 2548, 14:18 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportcenter.utility;
import javax.swing.table.*;
/**
 *
 * @author tong(Padungrat)
 */
public abstract class ColumnTableRenderer 
{
    /** Creates a new instance of TRendererRight */
    public static DefaultTableCellRenderer getRendererRight() {
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        return r;
    }
    
    public static DefaultTableCellRenderer getRendererCenter() {
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        return r;
    }
    
    public static DefaultTableCellRenderer getRendererLeft() {
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(javax.swing.JLabel.LEFT);
        return r;
    }
}
