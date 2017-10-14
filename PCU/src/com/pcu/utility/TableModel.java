/*
 * TableModel.java
 *
 * Created on 17 ÁÔ¶Ø¹ÒÂ¹ 2548, 17:35 ¹.
 */

package com.pcu.utility;
import javax.swing.table.*;
/**
 *
 * @author Noom
 */
public class TableModel extends DefaultTableModel{
    
    /** Creates a new instance of HTableModel */
    int table =0;
    int coledit =9;
    int coledit1 =9;
    int coledit2 =9;
    int coledit3 =9;
    int coledit4 =9;
    public TableModel() {
    }
    public TableModel(String[] col, int row) {
        super(col,row);
        
    }
    
    public boolean isCellEditable(int row,int col){
        // Constant.println("GColumCount : " + super.getColumnCount());
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
        return false;
    }
    
    public void setEditingCol(int col) {
        coledit = col;
    }
    public void setEditingCol(int col,int col1) {
        coledit = col;
        coledit1 = col1;
    }
    public void setEditingCol(int col,int col1,int col2) {
        coledit = col;
        coledit1 = col1;
        coledit2 = col2;
    }
    public void setEditingCol(int col,int col1,int col2,int col3) {
        coledit = col;
        coledit1 = col1;
        coledit2 = col2;
        coledit3 = col3;
    }
    
    public void setEditingCol(int col,int col1,int col2,int col3,int col4) {
        coledit = col;
        coledit1 = col1;
        coledit2 = col2;
        coledit3 = col3;
        coledit4 = col4;
    }
    
}
