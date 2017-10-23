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
public class TaBleModelN extends DefaultTableModel{
    
    /** Creates a new instance of HTableModel */
    int table =0;
    int coledit =9;
    int coledit1 =9;
    int coledit2 =9;
    int coledit3 =9;
    int coledit4 =9;
    int coledit5 =9;
    int coledit6 =9;//pop
    int coledit7 =9;//pop
    int coledit8 =9;//pop
    int coledit9 =9;//pop
    int coledit10 =9;//pop
    int coledit11 =9;//pop
    int coledit12 =9;//pop
    int coledit13 =9;//pop
    int coledit14 =9;//pop
    int coledit15 =9;//pop
    int coledit16 =9;//pop
    int coledit17 =9;//pop
    int coledit18 =9;//pop
    int coledit19 =9;//pop
    int coledit20 =9;//pop
    
    public TaBleModelN() {
    }
    public TaBleModelN(String[] col, int row) {
        super(col,row);
    }
    public TaBleModelN(int col, int row) {
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
        return true;
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
    /**
     * 
     * @param col
     * @param col1 
     * สามารถ set edit column ให้ได้หลายๆ column
     * ตอนนี้ ได้ 5 column
     */
    public void setEditingColN(int col,int col1){
        if(col>=col1) return;
        for(int i=col; i<=col1;i++){
            if(i==0) coledit = i;
            else if(i==1) coledit1 = i;
            else if(i==2) coledit2 = i;
            else if(i==3) coledit3 = i;
            else if(i==4) coledit4 = i;
            else if(i==5) coledit5 = i;
            else if(i==6) coledit6 = i;
            else if(i==7) coledit7 = i;
            else if(i==8) coledit8 = i;
            else if(i==9) coledit9 = i;
            else if(i==10) coledit10 = i;
            else if(i==11) coledit11 = i;
            else if(i==12) coledit12 = i;
            else if(i==13) coledit13 = i;
            else if(i==14) coledit14 = i;
            else if(i==15) coledit15 = i;
            else if(i==16) coledit16 = i;
            else if(i==17) coledit17 = i;
            else if(i==18) coledit18 = i;
            else if(i==19) coledit19 = i;
            else if(i==20) coledit20 = i;
        }
    }
}
