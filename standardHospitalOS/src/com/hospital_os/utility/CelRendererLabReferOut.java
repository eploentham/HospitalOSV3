/*
 * BooleanImageTableCellRenderer.java
 *
 * Created on 16 æƒ…¿“§¡ 2547, 9:12 π.
 */

package com.hospital_os.utility;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author  amp
 */
public class CelRendererLabReferOut implements TableCellRenderer
{
    
   public Component getTableCellRendererComponent(JTable table,
      Object value, boolean isSelected, boolean hasFocus,
      int row, int column)
   {  
      try
      {     
        if(((String)value).equals("1"))
        { 
            return lblReferOut;            
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace(Constant.getPrintStream());
      }      
      return lblNoReferOut;
   }   
   
   private JLabel lblReferOut = new JLabel(new ImageIcon(getClass().getResource("/com/hospital_os/images/refer_out.gif")));
   private JLabel lblNoReferOut = new JLabel(new ImageIcon(getClass().getResource("/com/hospital_os/images/no_refer_out.gif")));
    
}
