/*
 * CelRendererHealthSchool.java
 *
 * Created on 7 กรกฎาคม 2548, 11:04 น.
 */

package com.pcu.utility;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
/**
 *
 * @author Noom
 */
public class CelRendererHealthSchool implements TableCellRenderer{
    private JLabel lblCheckHealth = new JLabel(new ImageIcon(getClass().getResource("/com/hospital_os/images/refer_out.gif")));
    private JLabel lblNotCheckHealth = new JLabel(new ImageIcon(getClass().getResource("/com/hospital_os/images/no_refer_out.gif")));
    private JLabel lblEmpty = new JLabel("");
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        try {
            //lblCheckHealth.setToolTipText("ตรวจ");
            //lblNotCheckHealth.setToolTipText("ไม่ตรวจ");
            if(value != null){
                if(((String)value).equals("1")) {
                    return lblCheckHealth;
                }else if(((String)value).equals("0")) {
                    return lblNotCheckHealth;
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return lblEmpty;
    }
    
    
}
