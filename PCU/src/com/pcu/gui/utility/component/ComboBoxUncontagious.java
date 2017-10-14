/*
 * ComboBoxUncontagious.java
 *
 * Created on 24 กุมภาพันธ์ 2549, 17:40 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.gui.utility.component;
import com.pcu.control.PCUControl;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.hospital_os.utility.ComboFix;
import com.pcu.object.*;
/**
 *
 * @author Administrator
 */
public class ComboBoxUncontagious extends JComboBox implements ItemListener{
    
    String icd10 = "";
    JTable table;
    PCUControl theHC;
    /** Creates a new instance of ComboBoxUncontagious */
    public ComboBoxUncontagious() 
    {
        this.addItemListener(this);
    }
    public void setJTable(JTable ta)
    {
        this.table = ta;
    }
    public void setControl(PCUControl hc)
    {
        this.theHC = hc;
    }
    public void setIcd10(String icd10)
    {
        this.icd10 = icd10;
    }
    public String getIcd10()
    {
        return icd10;
    }

    public void itemStateChanged(java.awt.event.ItemEvent itemEvent) 
    {   
        Disease ds = theHC.theSetupPcuControl.readDiseasebyPK(((ComboFix)this.getSelectedItem()).code);
//        if("".equals(ds.icdCode) || ds.icdCode == null) setIcd10("");
//        else setIcd10(ds.icdCode);
        int select = this.table.getSelectedRow();
        TableModel tm = table.getModel();
        if(select > -1)
        {
            tm.setValueAt(this.getIcd10(), select, 2);
        }
        table.repaint();
    }
}
