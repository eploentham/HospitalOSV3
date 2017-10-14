/*
 * CelRendererSexType.java
 *
 * Created on 7 �á�Ҥ� 2548, 18:00 �.
 */

package com.pcu.utility;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
/**
 *
 * @author Noom
 */
public class CelRendererSexType implements TableCellRenderer{
    private JLabel lblMan = new JLabel(new ImageIcon(getClass().getResource("/com/pcu/images/man.jpg")));
    private JLabel lblWoman = new JLabel(new ImageIcon(getClass().getResource("/com/pcu/images/woman.jpg")));
    private JLabel lblNot= new JLabel("����к�",JLabel.CENTER);
    
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        try {
            lblMan.setToolTipText("���");
            lblWoman.setToolTipText("˭ԧ");
            if(value != null){
                if(((String)value).equals("1")) {
                    return lblMan;
                }else if(((String)value).equals("2")) {
                    return lblWoman;
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return lblNot;
    }
    
    
}