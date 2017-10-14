package com.report12file.audit;
/*
 * RowData.java
 *
 * Created on 4 กันยายน 2550, 9:00 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Abb
 */
import java.util.Hashtable;
import java.util.Vector;

public class RowData extends Hashtable{


    public void setColumnValue(String columnName, Object value) {
      if(value != null){
        super.put(columnName, value);
      }else{
        super.remove(columnName);
      }
    }


    public Object getColumnValue(String columnName){
      return super.get(columnName);
    }


  }

