/*
 * ColumnSorter.java
 *
 * Created on 14 สิงหาคม 2547, 19:19 น.
 */

package com.hospital_os.utility;
import java.util.*;
import javax.swing.table.*;
import javax.swing.*;
/**
 *
 * @author  Administrator
 */
public class ColumnSorter implements Comparator {
    
    boolean ascending;
    boolean isSorted=false;
    boolean usedTableSorter = false;
    javax.swing.table.JTableHeader jth;
    ColumnSorter theColumnSorter;
    
    JTable table;
    /*com.henbe.toy.table_sort.TableSorter sorter;
*/
    
    public ColumnSorter(boolean ascending,JTable jt,boolean used_sort) {
        this.ascending = ascending;
        table = jt;
        usedTableSorter = false;/*used_sort;
*/
        jth = table.getTableHeader();
/*        if(usedTableSorter){
            sorter = new com.henbe.toy.table_sort.TableSorter(table.getModel()); ADDED THIS
            sorter.setTableHeader(jth); ADDED THIS
        }*/
        jth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableHeader1MouseReleased(evt);
            }
        });
    }
    private void jTableHeader1MouseReleased(java.awt.event.MouseEvent evt) {
        Constant.println("ColumnSorter:SortTable");
        sortJTable(table,evt);
    }    
    public void refresh(){
        isSorted = false;
    }
        public int compare(Object a, Object b) {
            /* Treat empty strains like nulls
*/
            if (a instanceof String && ((String)a).length() == 0) {
                a = null;
            }
            if (b instanceof String && ((String)b).length() == 0) {
                b = null;
            }
    
            /* Sort nulls so they appear last, regardless
*/
            /* of sort order
*/
            if (a == null && b == null) {
                return 0;
            } else if (a == null) {
                return 1;
            } else if (b == null) {
                return -1;
            } else if (a instanceof Comparable) {
                if (ascending) {
                    return ((Comparable)a).compareTo(b);
                } else {
                    return ((Comparable)b).compareTo(a);
                }
            } else {
                if (ascending) {
                    return a.toString().compareTo(b.toString());
                } else {
                    return b.toString().compareTo(a.toString());
                }
            }
        }
    private CommonData[] colData;
    private void sortColumn(DefaultTableModel model, int colIndex, boolean ascending) {
        Vector data = model.getDataVector();
        colData = new CommonData[model.getRowCount()];
        /*reserve new instance for sort
*/
        for (int i=0; i<colData.length; i++) {
            Vector v = (Vector)data.get(i);
            colData[i] = new CommonData(colIndex);
            for(int j=0;j<v.size();j++){
                colData[i].add(v.get(j));
            }
            colData[i].setOldIndex(i);
        }
        Arrays.sort(colData,this);
        /*/if we used tableSorter class we don't update gui for save 
*/
        /*/working of program
*/
        for (int i=0; i<colData.length && !usedTableSorter; i++) {
            Vector v = (Vector)data.get(i);
            CommonData sv = (CommonData)colData[i];
            for(int j=0;j<v.size();j++)
                v.set(j, sv.get(j));
        }
        model.fireTableStructureChanged();
        isSorted=true;
    }

    public void sortJTable(JTable table,java.awt.event.MouseEvent evt){
        table.setAutoCreateColumnsFromModel(false);
        /*get Column Click
*/
        TableColumnModel colModel = table.getColumnModel();
        int mColIndex = colModel.getColumnIndexAtX(evt.getX());
        /*sort entire row when sort this column
*/
        boolean ascending = true;
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        sortColumn(model, mColIndex, ascending);
    }
    public int getOriginIndex(int new_index){
        if(isSorted==false) return new_index;
        if(new_index==-1) return -1;
        CommonData cd = colData[new_index];
        return cd.getOldIndex();
    }
    public int[] getOriginIndex(int[] new_arr){
        int ret[];
        ret = new int[new_arr.length];
        for(int i=0;i<new_arr.length;i++){
            ret[i] = getOriginIndex(new_arr[i]);
        }
        return ret;
    }
}
