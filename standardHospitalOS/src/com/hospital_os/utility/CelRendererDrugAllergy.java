/*
 * CelRendererDrugAllergy.java
 *
 * Created on 3 æƒ…¿“§¡ 2547, 10:38 π.
 */
package com.hospital_os.utility;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
/**
 *
 * @author  tong
 */
public class CelRendererDrugAllergy implements TableCellRenderer
{
    
   public Component getTableCellRendererComponent(JTable table,
      Object value, boolean isSelected, boolean hasFocus,
      int row, int column)
   {  
      try
      {     
        if(value != null && ((String)value).equals("1"))
        { 
            return patient_drugallergy;            
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace(Constant.getPrintStream());
      }
      
      return patient_nodrugallergy;
   }  
   private JLabel patient_drugallergy = new JLabel(new ImageIcon(getClass().getResource(Gutil.getTextBundleImage("DRUGALLERGY")))); /**/
   private JLabel patient_nodrugallergy = new JLabel(new ImageIcon(getClass().getResource(Gutil.getTextBundleImage("NO_DRUGALLERGY"))));
    
}
