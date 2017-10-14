/*
 * CheckBoxEdit.java
 *
 * Created on 24 æƒ…¿“§¡ 2547, 21:31 π.
 */
package com.hosv3.gui.component;
import com.hosv3.utility.Constant;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author  kingland
 */
public class ComBoBoxEditor extends DefaultCellEditor implements ItemListener
{
  private JComboBox button;
  private JTable jtable;
  private String str;
  private JLabel label;
  private JTextArea jscr;
  private TextFieldResultLab textfieldlab;
  private BalloonTextField balloonTextField;//amp:07/04/2549
  private Component com;
  int c = 0;
  
  public ComBoBoxEditor(JComboBox comboBox) 
  {
    super(comboBox);
  }
    
  public Component getTableCellEditorComponent(JTable table, Object value,
                   boolean isSelected, int row, int column) 
  {
    jtable = table;
    if(value instanceof String)
    {
        str = (String) value;
        JLabel label = new JLabel();
        label.setText(str);
        c = 1;
        return label;
    }
    else if(value instanceof JComboBox)
    {
        button = (JComboBox)value;
        button.addItemListener(this);
        c = 2;
        return button;
    }
    else if(value instanceof TextFieldResultLab)
    {
        c = 3;
        textfieldlab = (TextFieldResultLab) value;
        return textfieldlab;
    }
    else if(value instanceof JTextArea)
    {
        c = 5;
        jscr = (JTextArea) value;
        JScrollPane js = new JScrollPane();
        js.setViewportView(jscr);
        return js;
    }  
    else if(value instanceof BalloonTextField)//amp:10/04/2549
    {
        c = 6;
        balloonTextField = (BalloonTextField) value;
        return balloonTextField;
    } 
    else
    {
        c = 4;
        com = (Component)value;
        return com;
    }
  }
    
    public Object getCellEditorValue() 
    {
        if(c==1)
        {
            return label;
        }
        else if(c==2)
        {
            try
            {   
                button.removeItemListener(this);
            }
            catch(Exception ex)
            {
                ex.printStackTrace(Constant.getPrintStream());
            }
            return button;
        }
        else if(c == 3)
        {
            return this.textfieldlab;
        }
        else if(c == 4)
        {
            return com;
        }
        else if(c == 5)
        {
//            JScrollPane js = new JScrollPane();
//            js.setViewportView(jscr);
            return jscr;
        } 
        else if(c == 6)//amp:07/04/2549
        {
            return balloonTextField;
        }
        return null;
  }
    
  public void itemStateChanged(ItemEvent e) 
  {
    super.fireEditingStopped();
  }
}
