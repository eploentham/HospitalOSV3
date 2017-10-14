/*
 * BooleanImageTableCellRenderer.java
 *
 * Created on 16 æƒ…¿“§¡ 2547, 9:12 π.
 */

package com.hosv3.gui.component;

import com.hosv3.utility.Constant;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author  amp
 */
public class CelRendererLabReport implements TableCellRenderer
{
    
   public Component getTableCellRendererComponent(JTable table,
      Object value, boolean isSelected, boolean hasFocus,
      int row, int column)
   {  
      try
      {     
        if(((String)value).equals("4"))
        { 
            return lblReported;            
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace(Constant.getPrintStream());
      }      
      return lblReport;
   }   
   
   private JLabel lblReported = new JLabel(new ImageIcon(getClass().getResource("/com/hospital_os/images/refer_out.gif")));
   private JLabel lblReport  = new JLabel(new ImageIcon(getClass().getResource("/com/hospital_os/images/no_refer_out.gif")));
    
}
