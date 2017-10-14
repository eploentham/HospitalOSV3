/*
 * CellRendererReport.java
 *
 * Created on 29 กรกฎาคม 2548, 13:20 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.utility;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
/**
 *
 * @author Noom
 */
public class CellRendererReport implements TableCellRenderer{
    private JLabel lblReportCheck = new JLabel(new ImageIcon(getClass().getResource("/com/report12file/gui/images/report_action.jpg")));
    private JLabel lblReport = new JLabel(new ImageIcon(getClass().getResource("/com/report12file/gui/images/report.jpg")));
    private JLabel lblEmpty = new JLabel("");
    
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        try {
            lblReportCheck.setToolTipText("เลือก");
            lblReport.setToolTipText("ไม่เลือก");
            if(value != null){
                if(((String)value).equals("0")) {
                    return lblReport;
                }else if(((String)value).equals("1")) {
                    return lblReportCheck;
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    return lblEmpty;
    }
}  
    
