/*
 * Ins.java
 *
 * Created on 8 กันยายน 2548, 12:04 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.objectnh;

import com.report12file.utility.Constant;
import com.linuxense.javadbf.DBFField;
import com.report12file.object.*;
import com.report12file.utility.Report12FileData;
import com.report12file.object.Rp12OI;
import com.reportcenter.utility.IOStream;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tong(Padungrat)
 */
public class InsNh extends Ins implements Rp12OI{
    
    public static String EMPTY = "";
    public InsNh() {
    }

    public Rp12OI initInstant() {
        return new InsNh();
    }

    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = "";
        PreparedStatement pm;
        if(mode==1){
            sql = IOStream.readInputDefault("config/rp_12file/12file_ins_vp.sql");
            pm = con.prepareStatement(sql);
            pm.setString(1,startDate);
            pm.setString(2,endDate);
        }
        else{
            sql = IOStream.readInputDefault("config/rp_12file/12file_ins.sql");
            pm = con.prepareStatement(sql);
            pm.setString(1,startDate);
            pm.setString(2,endDate);
            pm.setString(3,startDate);
            pm.setString(4,endDate);
        }
        return pm;
    }


    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report12FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,9);
        Report12FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,2);
        Report12FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,2);
        Report12FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,16);
        Report12FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,8);
        Report12FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,8);
        Report12FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,5);
        Report12FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,5);
            return fields;
    }
    
    public Object[] getDBFValue() {
        Ins p = this;
        Object[] rowData;
        rowData = new Object[8];
                rowData[0] = p.hn;
                rowData[1] = p.inscl;
                rowData[2] = p.subtype;                
                rowData[3] = p.cid;                
                rowData[4] = p.datein;
                rowData[5] = p.dateexp;
                rowData[6] = p.hospmain;
                rowData[7] = p.hospsub;  
        return rowData;
    }
    
}
