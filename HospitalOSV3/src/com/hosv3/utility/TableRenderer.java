/*
 * TRendererRight.java
 *
 * Created on 19 กรกฎาคม 2548, 0:24 น.
 */

package com.hosv3.utility;

import javax.swing.table.*;
import com.hospital_os.gui.font.*;
/**
 *
 * @author  henbe
 */
public abstract class TableRenderer {
    
    /** Creates a new instance of TRendererRight */
    public static DefaultTableCellRenderer getRendererRight() {
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        r.setFont(new DefaultFont());
        return r;
    }
    public static DefaultTableCellRenderer getRendererCenter() {
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        r.setFont(new DefaultFont());
        return r;
    }
}
