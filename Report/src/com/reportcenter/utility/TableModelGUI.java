/*
 * HTableModel.java
 *
 * Created on 24 กันยายน 2546, 12:01 น.
 */

package com.reportcenter.utility;

import javax.swing.table.*;
/**
 *
 * @author  Administrator
 */
public class TableModelGUI extends DefaultTableModel{
    
    /** Creates a new instance of HTableModel */
    int table =0;
    int coledit =9;
    int coledit1 =9;
   int coledit2 =9;
    public TableModelGUI() {
    }
    public TableModelGUI(String[] col, int row) {
        super(col,row);
       
    }
   
    public boolean isCellEditable(int row,int col){
        boolean result = false;
        if(super.getColumnCount()>coledit && col==coledit)
        { 
            result = true;
        }
        if(super.getColumnCount()>coledit1 && col==coledit1)
        { 
            result = true;
        }
         if(super.getColumnCount()>coledit2 && col==coledit2)
        { 
            result = true;
        }
        return result;
    }
   
    public void setEditingCol(int col)
    {   coledit = col;
    }
    public void setEditingCol(int col,int col1)
    {   coledit = col;
        coledit1 = col1;
    }      
    public void setEditingCol(int col,int col1,int col2)
    {   coledit = col;
        coledit1 = col1;
        coledit2 = col2;
    } 
    
   
}
