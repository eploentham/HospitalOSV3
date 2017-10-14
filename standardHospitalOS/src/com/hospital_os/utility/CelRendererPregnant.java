/*
 * CelRendererPregnant.java
 *
 * Created on 21 ¡’π“§¡ 2549, 14:53 π.
 */

package com.hospital_os.utility;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author  amp
 */
public class CelRendererPregnant implements TableCellRenderer
{
    
   public Component getTableCellRendererComponent(JTable table,
      Object value, boolean isSelected, boolean hasFocus,
      int row, int column)
   {  
      try
      {     
        if(((String)value).equals("1"))
        { 
            return pregnant;            
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace(Constant.getPrintStream());
      }      
      return noPregnant;
   }   
   
   private JLabel pregnant = new JLabel(new ImageIcon(getClass().getResource("/com/hospital_os/images/true.gif")));
   private JLabel noPregnant = new JLabel(new ImageIcon(getClass().getResource("/com/hospital_os/images/nodrugAllergy.gif")));
    
}
