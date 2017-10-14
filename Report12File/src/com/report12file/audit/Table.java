package com.report12file.audit;
/*
 * Table.java
 *
 * Created on 4 กันยายน 2550, 8:58 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Abb
 */
import javax.swing.JTable;


public class Table extends JTable{
private TableModel model = new TableModel();


  public Table() {
    setModel(model);
  }


  public void addColumn(String columnName){
   model.addColumn(columnName);
  }


  public void addRow(RowData row){
    model.addRow(row);
  }


  public void removeRow(int rowNum){
    model.removeRow(rowNum);
  }
  

  public RowData getRowData(int rowNum){
    return model.getRowData(rowNum);
  }


public int getRowCount(){
  if( model == null) return 0;
  return model.getRowCount();
}


public void updateRowData(int rowNum, RowData newRow){
  model.updateRowData(rowNum,newRow);
  this.repaint();
}


}
