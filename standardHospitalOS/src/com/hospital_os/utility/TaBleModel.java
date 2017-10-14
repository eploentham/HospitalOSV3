/*
 * HTableModel.java
 *
 * Created on 24 กันยายน 2546, 12:01 น.
 */

package com.hospital_os.utility;

import javax.swing.table.*;
/**
 *
 * @author  Administrator
 */
public class TaBleModel extends DefaultTableModel{
    
    /** Creates a new instance of HTableModel */
    int table =0;
    int coledit =9;
    int coledit1 =9;
    int coledit2 =9;
    int coledit3 =9;
    int coledit4 =9;
    int coledit5 =9;
    
    public TaBleModel() {
    }
    public TaBleModel(String[] col, int row) {
        super(col,row);
    }
    public TaBleModel(int col, int row) {
        super(row,col);
    }
    public Class getColumnClass(int c) {
    //    Constant.println(c + ":" + getValueAt(0, c).getClass().toString());
        if(getValueAt(0, c)!=null)
            return getValueAt(0, c).getClass();
        return String.class;
    }
/////////////////////////////////////////////////////////////////////////////////
//    เหล่านี้เอาไว้ใช้ในการ sort ตามช่วงเวลาต่างของข้อมูลที่ถูกต้องเช่นการเรียงตัวเลข ตัวอักษร วันที่
//    Class class_object;
//    int col_index=0;
//            
//    
//    public void setColumnClass(Class clarr,int columnindex){
//        Constant.println("setColumnClass");
//        class_object = clarr;
//        col_index = columnindex;
//        //sorter.setColumnComparator(Integer.class, new IntegerCompare());
//    }
//
//    public Class getColumnClass(int columnIndex) {
//        Constant.println("getColumnClass" + columnIndex);
//        if(class_object!=null && columnIndex==col_index)
//        {
//            Constant.println("ok");
//            return class_object;
//        }
//        else
//            return super.getColumnClass(columnIndex);
//    }
////////////////////////////////////////////////////////////////////////////////
    
    public boolean isCellEditable(int row,int col){
       /* Constant.println("GColumCount : " + super.getColumnCount());
*/
        if(super.getColumnCount()>coledit && col==coledit)
            return true;
        if(super.getColumnCount()>coledit1 && col==coledit1)
            return true;
         if(super.getColumnCount()>coledit2 && col==coledit2)
            return true;
        if(super.getColumnCount()>coledit3 && col==coledit3)
            return true;
        if(super.getColumnCount()>coledit4 && col==coledit4)
            return true;
         if(super.getColumnCount()>coledit5 && col==coledit5)
            return true;
        return false;
    }
   
    public void setEditingCol(int col)
    {   coledit = col;
    }
    public void setEditingCol(int col,int col1)
    {   coledit = col;
        coledit1 = col1;
    }      
    public void setEditingCol(int col,int col1,int col2)
    {   
        coledit = col;
        coledit1 = col1;
        coledit2 = col2;
    } 
    public void setEditingCol(int col,int col1,int col2,int col3,int col4,int col5)
    {
        coledit = col;
        coledit1 = col1;
        coledit2 = col2;
        coledit3 = col3;
        coledit4 = col4;
        coledit5 = col5;
    }     
   
}
