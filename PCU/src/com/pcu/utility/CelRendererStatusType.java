/*
 * CelRendererStatusType.java
 *
 * Created on 1 มีนาคม 2549, 14:16 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.utility;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
/**
 *
 * @author Jao
 */
public class CelRendererStatusType implements TableCellRenderer{
    private JLabel lblDeath = new JLabel(new ImageIcon(getClass().getResource("/com/pcu/images/death.gif")));
    private JLabel lblMiss = new JLabel(new ImageIcon(getClass().getResource("/com/pcu/images/miss.gif")));
    private JLabel lblMove = new JLabel(new ImageIcon(getClass().getResource("/com/pcu/images/move.gif")));
    private JLabel lblNot= new JLabel("-",JLabel.CENTER);
    
    /** Creates a new instance of CelRendererStatusType */
    public CelRendererStatusType() {
    }

    public Component getTableCellRendererComponent(JTable jTable, Object value,
                                                   boolean param, boolean param3, 
                                                   int param4, int param5) {
        
        try {
            lblDeath.setToolTipText("เสียชีวิต");
            lblMiss.setToolTipText("สาบสูญ");
            lblMove.setToolTipText("ย้าย");
            if(value != null){
                if(((String)value).equals("1")) {
                    return lblDeath;
                }else if(((String)value).equals("2")) {
                    return lblMove;
                }else if(((String)value).equals("3")) {
                    return lblMiss;
                }
                else if(((String)value).equals("")) {
                    return lblNot;
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return lblNot;
    }
    
}
