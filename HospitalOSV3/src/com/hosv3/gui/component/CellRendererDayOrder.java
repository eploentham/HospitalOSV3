package com.hosv3.gui.component;

import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;
import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.util.*;
import com.hospital_os.object.*;
   /**
     * //henbe call 
     * Class ที่เป็น renderer ไม่ควรที่จะเรียก control เพราะว่าเมื่อมีการ render หน้าจอใหม่
     * //มันก็จะเรียกติดต่อ control ใหม่ทุกครั้ง เช่นการ alt tab บ่อยก็จะทำงานในฟังชันนี้บ่อยๆ
     *
     */ 
public class CellRendererDayOrder extends JLabel implements TableCellRenderer 
{
    boolean isBordered = true;
    Color color = null;
    Color fontcolor = null;
    String drug_interaction;//amp:31/03/2549
    String display_string;//henbe:28/04/2549
    String short_dose;
    String ln;
  
    
    public CellRendererDayOrder(boolean isBordered) 
    {
        this.isBordered = isBordered;
        this.color = new Color(255,255,255);
        this.fontcolor= new Color(0,0,0);
        setOpaque(true); 
    }
    
    public Component getTableCellRendererComponent(JTable table, Object hashTable
        ,boolean isSelected, boolean hasFocus,int row, int column) 
    { 
        setText("");
        setForeground(Color.BLACK);
        if(hashTable!=null)
        {
            Hashtable ht = (Hashtable)hashTable;
            OrderItem oi = (OrderItem)ht.get("OrderItem");
            drug_interaction = (String)ht.get("String");
            display_string = (String)ht.get("display_string");
            short_dose = (String)ht.get("short_dose");
            ln = (String)ht.get("ln");
            if(drug_interaction==null)   drug_interaction = "";
            if(display_string==null)   display_string = "";
            if(short_dose==null)   short_dose = "";
            if(ln==null)   ln = "";
            if(oi.isLab() && !ln.equals(""))
            {
                ln = "<br>LN : " + ln;
            }
            else
            {
                ln = "";
            }
            setToolTipText("<html><BODY BGCOLOR = #E7FAAF>" + 
                    display_string + drug_interaction + ln +
                    "</BODY></html>");            
            
            if(short_dose!=null && !short_dose.equals(""))
                setText(oi.common_name+ ": " + short_dose);
            else
                setText(oi.common_name);
            
            if (isBordered) 
            {
                if (isSelected) 
                {
                    setBackground(table.getSelectionBackground());                   
                    setForeground(this.fontcolor);
                } 
                else 
                {
                    setBackground(this.color);
                    setForeground(this.fontcolor);
                }
            }
            if("1".equals(oi.drug_allergy) || !"".equals(drug_interaction))
            {
                setForeground(Color.RED);
            }
        }
        return this;
    }
 
  
}
