/*
 * BooleanImageTableCellRenderer.java
 *
 * Created on 9 กันยายน 2545, 9:12 น.
 */

package com.hospital_os.utility;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author  tong
 */
public class CelRendererDespen implements TableCellRenderer
{
    
   public Component getTableCellRendererComponent(JTable table,
      Object value, boolean isSelected, boolean hasFocus,
      int row, int column)
   {    
      try
      {    
        
        /*String vn = valuedata.substring(1);
*/
        if(((String)value).equals("1"))
        {   table.setForeground(new Color(255,0,51));
           /* return lblBlue;
*/
            return null;
            
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace(Constant.getPrintStream());
      }
      table.setForeground(new Color(0,0,0));
      /*return lblGray;
*/
       return null;/*lblGray;
*/
   }
   
 
}
