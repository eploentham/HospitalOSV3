
package com.pcu.gui.utility.component;

import javax.swing.*;
import com.pcu.utility.DateComboBox;
import com.pcu.utility.GutilPCU;
import com.hospital_os.usecase.connection.*;


/**
 *
 * @author Administrator
 */
public class DateComboBoxSpecail extends DateComboBox implements java.awt.event.ActionListener,java.awt.event.FocusListener
{
    ConnectionInf theConnectionInf;
    boolean checkSurvay = false;
    JFrame frm ;
    JTable table;
    public DateComboBoxSpecail(ConnectionInf hm)
    {
        theConnectionInf = hm;
        this.addActionListener(this);
        this.addFocusListener(this);
    }
   
    public void focusGained(java.awt.event.FocusEvent focusEvent) {
        
    }

    public void focusLost(java.awt.event.FocusEvent focusEvent) {
        checkSurvay = false;        
    }
    public void actionPerformed(java.awt.event.ActionEvent e)
    {
        this.requestFocus();
        checkDateSurvay();
    }
     /**
     * ตรวจสอบว่าวันออกตรวจเป็นวันในอนาคตหรือไม่
     * @param  
     * @return 
     * @author kingland
     * @date 03-03-2549
     */ 
    private void checkDateSurvay()
    {
        if(!this.getText().equals("") 
            && this.getText().length()==10 
            && com.pcu.utility.DateUtil.countDay(this.getText(),theConnectionInf) == -1 
            && com.pcu.utility.DateUtil.isToday(com.pcu.utility.DateUtil.getDateFromText(this.getText()),theConnectionInf)==false)  
        {            
                 // ไม่สามารถกรอกวันที่เป็นวันในอนาคตได้
                JOptionPane.showMessageDialog(this.frm,GutilPCU.getTextBundle("NoDateFuture") , GutilPCU.getTextBundle("Warning"), JOptionPane.WARNING_MESSAGE);
                this.setText("");    
                if(table != null)
                {
                    this.table.repaint();
                }
                checkSurvay = true;
        }  
    }
    /**
     * 
     * @param  
     * @return 
     * @author kingland
     * @date 03-03-2549
     */
    public void setJFrame(JFrame frame)
    {
        this.frm = frame;
    }
    /**
     * 
     * @param  
     * @return 
     * @author kingland
     * @date 03-03-2549
     */
    public void setJTable(JTable table)
    {
        this.table  = table;
    }
}