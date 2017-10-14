/*
 * CellRendererOrderItem.java
 *
 * Created on 31 สิงหาคม 2548, 10:53 น.
 */

package com.hosv3.gui.component;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import com.hospital_os.object.*;
/**
 *
 * @author  kingland
 */
public class CellRendererPatient extends JLabel implements TableCellRenderer {
    
    boolean isBordered = true;
    Color color = null;
    Color fontcolor = null;
    /** Creates a new instance of CellRendererOrderItem */
    /**
     * //henbe call 
     * Class ที่เป็น renderer ไม่ควรที่จะเรียก control เพราะว่าเมื่อมีการ render หน้าจอใหม่
     * //มันก็จะเรียกติดต่อ control ใหม่ทุกครั้ง เช่นการ alt tab บ่อยก็จะทำงานในฟังชันนี้บ่อยๆ
     * ซึ่งมันไม่จำเป็นเลยรู้บ้างมั้ย
     */ 
    public CellRendererPatient(boolean isBordered) 
    {
        this.isBordered = isBordered;
        color = new Color(255,255,255);
        fontcolor= new Color(0,0,0);
        setOpaque(true); 
    }
    
    public java.awt.Component getTableCellRendererComponent(JTable table, Object value
            , boolean isSelected, boolean hasFocus, int row, int column) 
    {
        if(value!=null)
        {
            Patient p = (Patient)value;
            String add = p.house; 
            String name = p.patient_name + " " + p.patient_last_name;
            if (isBordered) 
            {
                if (isSelected) 
                {
                    setBackground(table.getSelectionBackground());
                   // setForeground(this.fontcolor);
//                    setForeground(this.color);
                } 
                else {
                    setBackground(this.color);
//                    setForeground(this.fontcolor);
                }
            }
            String ttt = "<html><BODY BGCOLOR = #E7FAAF>ชื่อ : " + name;
            ttt = ttt + add;
            if(p.phone!=null && !p.phone.equals(""))
                ttt = ttt+ " หมายเลขโทรศัพท์ : " + p.phone;
            ttt = ttt+ "</BODY></html>";
            
            setToolTipText(ttt);
            setText(p.patient_name);
            p = null;
            return this;
        }
        else
        {
            return this;
        }
    }

}
