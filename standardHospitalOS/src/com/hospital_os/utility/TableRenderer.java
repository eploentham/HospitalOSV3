/*
 * TRendererRight.java
 *
 * Created on 19 �á�Ҥ� 2548, 0:24 �.
 */

package com.hospital_os.utility;

import javax.swing.table.*;
/**
 *
 * @author  Administrator
 */
public abstract class TableRenderer {
    
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
}
