package com.report12file.audit;
/*
 * TableModel.java
 *
 * Created on 4 กันยายน 2550, 8:59 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Abb
 */
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class TableModel extends DefaultTableModel{


  public TableModel(Vector rowDatas, Vector columnNames){
    this(columnNames);
    for(int i=0; i<rowDatas.size(); i++){
      this.addRow((Vector)rowDatas.elementAt(i),true);
    }
  }

  public void addColumn(String columnName){
    super.addColumn(columnName);
  }

  public TableModel(){
    super();
  }

  public TableModel(Vector columnNames){
    super(columnNames,0);
  }


  public void addRow(Vector rowData) {
    this.addRow(rowData,true);
  }

  public void  addRow(RowData row){
    this.addRow(this.convertToVector(super.columnIdentifiers,row));;
  }




  public void addRow(Vector rowData,boolean isRowEdit){

    Vector rowEdit = new Vector();
    rowEdit.setSize(rowData.size());

    super.addRow(rowData);
  }

    public void addRow(Object[] rowData) {
      Vector vRow = super.convertToVector(rowData);
      this.addRow(vRow,true);
    }




  public boolean isCellEditable(int row,int column){
    return false;
  }

  public void removeRow(int row) {
       super.removeRow(row);
  }

  public void setValueAt(Object value, int row, String columnName) {
    int column = this.getColumnNum(columnName);
    super.setValueAt(value,row,column);
  }

    public Object getValueAt(int row, String columnName) {
         int column = this.getColumnNum(columnName);
         return super.getValueAt(row,column);
     }

    public int getColumnNum(String columnName){
      int column = -1;
      for(int i=0; i<super.getColumnCount(); i++){
        if(columnName.equals(super.getColumnName(i))){
          column = i;
          break;
        }
      }
      return column;
    }

    public RowData getRowData(int rowNum){
      RowData rowData = new RowData();
      Vector vRow = (Vector) super.getDataVector().elementAt(rowNum);
      for(int i=0; i<super.columnIdentifiers.size(); i++){
        rowData.setColumnValue(super.columnIdentifiers.elementAt(i).toString(),vRow.elementAt(i));
      }

      return rowData;
    }


    public void updateRowData(int rowNum, RowData newDataRow){
      Vector vRow = (Vector) super.getDataVector().elementAt(rowNum);
      for(int i=0; i<super.columnIdentifiers.size(); i++){
        String colName = super.columnIdentifiers.elementAt(i).toString();
        Object v = newDataRow.getColumnValue(colName);
        vRow.setElementAt(v,i);
      }
    }


    public Vector convertToVector(Vector columns,RowData row){
     Vector vRow  = new Vector();
     for(int i=0; i<columns.size(); i++){
       vRow.add(row.getColumnValue(columns.elementAt(i).toString()));
     }
     return vRow;
   }

   public Vector getColumnsVector(){
     return this.columnIdentifiers;
   }


  };
