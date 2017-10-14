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
public class CelRendererSex implements TableCellRenderer
{
    
   public Component getTableCellRendererComponent(JTable table,
      Object value, boolean isSelected, boolean hasFocus,
      int row, int column)
   {  
      try
      {     
        if(value != null)
        {
            if(((String)value).equals("1"))
            { /*  setForeground(new Color(255,0,51));
*/
                return man;

            }
            else
            {   return woman;
            }
        }
        else
        {
            return nosex;
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace(Constant.getPrintStream());
      }
      /*setForeground(new Color(0,0,0));
*/
      return nosex;
   }
   
   /* the following panel is returned for all cells, with
*/
   /* the background color set to the Color value of the cell
*/

   private JLabel man = new JLabel(new ImageIcon(getClass().getResource(Gutil.getTextBundleImage("MAN"))));
   private JLabel woman = new JLabel(new ImageIcon(getClass().getResource(Gutil.getTextBundleImage("WOMAN"))));
   private JLabel nosex = new JLabel(new ImageIcon(getClass().getResource(Gutil.getTextBundleImage("NO_DRUGALLERGY")))); 
}
