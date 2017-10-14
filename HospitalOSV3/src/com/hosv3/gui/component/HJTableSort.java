/*
 * HJTableSort.java
 *
 * Created on 27 กุมภาพันธ์ 2549, 10:43 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.hosv3.gui.component;

import com.hosv3.utility.Constant;
import com.hospital_os.utility.TaBleModel;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.util.*;
/**
 *
 * @author henbe
 */
public class HJTableSort extends JTable //implements Comparator
{
    /*เป็นค่าที่บอกว่าอยากรู้แถวที่เลือกจาก vector ที่ดึงมาในครั้งแรก หรือจาก เลขบรรทัด*/
    boolean gui_mode = false;
    TableSorter sorter = new TableSorter();

    public void setModel(AbstractTableModel tm){
        
        if(sorter!=null){
            int col = -1;
            for(int i=0;i<tm.getColumnCount();i++){
                if(TableSorter.NOT_SORTED
                        != sorter.getSortingStatus(col))
                     col = i;   
            }
            sorter.setTableModel(tm);
            if(col!=-1)
                sorter.setSortingStatus(col,sorter.getSortingStatus(col));
            super.setModel(sorter);
            sorter.setTableHeader(super.getTableHeader()); //ADDED THIS
        }
        else
            super.setModel(tm);
    }

    public void setModel(AbstractTableModel tm, TableSorter sorter){
        this.sorter = sorter;
        setModel(tm);
    }
    
    public void setGuiMode(boolean is_gui)
    {
        gui_mode = is_gui;
    }
    
    public int getSelectedRow()
    {        
        int row = super.getSelectedRow();
        if(gui_mode)
        {
            return row;
        }
        if(sorter!=null && row!=-1 && sorter.isSorting())
            row = sorter.modelIndex(row);
        return row;
    }
    
    public int[] getSelectedRows()
    {        
        int[] rows = super.getSelectedRows();        
        /**
         *amp:16/5/2549 
         *เพราะถ้าไม่ comment เอาไว้ จะทำให้เมื่อ sort ตาราง order ที่มีการสั้งรายตรวจรักษาให้ผู้ป่วยจะเกิด ทำให้เลือกผิดตัว
         if(rows.length==1)
        {
            return rows;
        }*/        
        if(sorter!=null && !gui_mode && sorter.isSorting())
        {
            for(int i=0;i<rows.length;i++)
            {
                rows[i] = sorter.modelIndex(rows[i]);
            }            
        }
        Arrays.sort(rows);//amp:16/5/2549
        return rows;
    }
    public int getVectorIndex(int gui_index){
        return sorter.modelIndex(gui_index);
    }

    
    
    public class IntegerCompare implements Comparator
    {
        public int compare(Object o1,Object o2)
        {
            Constant.println("public int compare(Object o1,Object o2)");
            Integer i1 = (Integer)o1;
            Integer i2 = (Integer)o2;
            return i1.compareTo(i2);
        }   
        public boolean equals(Object object)
        {
            return object instanceof Integer;
        }
    }
    
    public static void main(String[] argc)
    {
        JFrame jf = new JFrame();
        HJTableSort h = new HJTableSort();
        JScrollPane js = new JScrollPane();
        //JTable h = new JTable();
        js.setViewportView(h);
        TaBleModel aa = new TaBleModel(new String[]{"index","name"},10);
        String array[] = new String[]{"1","2","12","13","41"};
        aa.setValueAt(Integer.valueOf(array[0]),1,1);
        aa.setValueAt(Integer.valueOf(array[1]),2,1);
        aa.setValueAt(new Integer(array[2]),3,1);
        aa.setValueAt(new Integer(array[3]),4,1);
        aa.setValueAt(new Integer(array[4]),5,1);
        h.setModel(aa);
        jf.getContentPane().add(js);
        jf.setSize(new Dimension(300,200));
        jf.setVisible(true);
    }
}
