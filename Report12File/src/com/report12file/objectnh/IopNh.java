/*
 * Iop.java
 *
 * Created on 8 กันยายน 2548, 12:04 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.objectnh;
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
public class IopNh extends Iop  implements Rp12OI{
    
    public IopNh() {
    }
     public String[] getHeaderArray() {
        return new String[]{
"an",
"oper",
"optype",
"dropid",
"datein",
"timein",
"dateout",
"timeout"
};
    }
    public Rp12OI initInstant() {
        return new IopNh();
    }

    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file/12file_iop.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report12FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,9);
        Report12FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,5);
        Report12FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,1);
        Report12FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,6);
        Report12FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,20);
        Report12FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,4);
        Report12FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,20);
        Report12FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,4);
            return fields;
    } 
    public Object[] getDBFValue() {
        Iop p = this;
        Object[] rowData;
        rowData = new Object[8];              
                rowData[0] = p.an;
                rowData[1] = p.oper;                
                rowData[2] = p.optype;
                rowData[3] = p.drop;
                rowData[4] = p.datein;
                rowData[5] = p.timein;
                rowData[6] = p.dateout;
                rowData[7] = p.timeout;
        return rowData;
    }  
}
