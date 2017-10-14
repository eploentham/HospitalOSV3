/*
 * CheckBoxRenderer.java
 *
 * Created on 22 æƒ…¿“§¡ 2547, 20:01 π.
 */

package com.hosv3.gui.component;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author  kingland
 */


public class CellRendererComboBox implements TableCellRenderer 
{
  public Component getTableCellRendererComponent(JTable table, Object value,
                   boolean isSelected, boolean hasFocus, int row, int column) {
   
    if (value==null)
    {
        return null;
    }    
    else if(value instanceof String)
    {
        JLabel label = new JLabel();
        label.setText((String)value);
        return label;
    }
    else if(value instanceof JTextArea)
    {
        JScrollPane js = new JScrollPane();
        js.setViewportView((Component)value);
        return js;
    }
    else if(value instanceof BalloonTextField)
    {
        BalloonTextField balloonTextField = new BalloonTextField();
        balloonTextField.setText(((BalloonTextField)value).getText());
        return balloonTextField;
    }
    else if(value instanceof TextFieldResultLab)
    {
        TextFieldResultLab tf = (TextFieldResultLab)value;
        JLabel label = new JLabel();
        label.setText(tf.getText());
        if(tf.isAbnormalValue()){
            Font font = label.getFont();
            label.setFont(new Font(font.getFontName(), Font.BOLD, font.getSize()));
            label.setForeground(Color.RED);
        }else{
//        if(tf.isNormalValue()){
            Font font = label.getFont();
            label.setFont(new Font(font.getFontName(), Font.PLAIN, font.getSize()));
//            if(tf.nonormal){
//                label.setForeground(Color.BLACK);
//            }else{
//                if(tf.isNormalValue()){
//                    label.setForeground(Color.BLUE);
//                }else{
//                    label.setForeground(Color.BLACK);
//                }
//            }
            label.setForeground(Color.BLACK);
        }
        return label;
    }
    else
        return (Component)value;
  }
}



