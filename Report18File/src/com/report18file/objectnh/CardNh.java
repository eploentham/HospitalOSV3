package com.report18file.objectnh;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Card;
import com.report18file.object.Rp18OI;
import com.report18file.utility.Report18FileData;
import java.io.BufferedReader;
import com.reportcenter.utility.IOStream;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
/*
 * AncNh.java
 *
 * Created on 11 กันยายน 2551, 15:17 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author henbe
 */
public class CardNh extends Card implements Rp18OI{
    
    /** Creates a new instance of AncNh */
    public CardNh() {
    }
    public String[] getHeaderArray() {
        return new String[]{
            "pcucode",
            "cid",
            "pid",
            "instype",
            "insid",
            "start_date",
            "expir_date",
            "main",
            "sub",
            "update"
        };
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {

        String sql = IOStream.readInputDefault("config/rp_18file_nhso50/18file_nhso50_card.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    public Rp18OI initInstant() {
        return new CardNh();
    }

    
    
    public boolean setValue(ResultSet rs) throws Exception {
        Card p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.cid = Report18FileData.getRsString(rs,2);
        p.pid = Report18FileData.getRsString(rs,3);
        p.instype = Report18FileData.getRsString(rs,4);
        p.insid = Report18FileData.getRsString(rs,5);
        p.start = Report18FileData.getRsString(rs,6);
        p.expir = Report18FileData.getRsString(rs,7);
        p.main = Report18FileData.getRsString(rs,8);
        p.sub = Report18FileData.getRsString(rs,9);
        p.update = Report18FileData.getRsString(rs,10);
        return true;
    }
    
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,18);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_N,5);
        Report18FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,8);
        
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Card p = this;
        rowData = new Object[10];
        rowData[0] = p.pcucode;
        rowData[1] = p.cid;
        rowData[2] = p.pid;
        rowData[3] = p.instype;
        rowData[4] = p.insid;
        rowData[5] = p.start;
        rowData[6] = p.expir;
        rowData[7] = p.main;
        rowData[8] = Report18FileData.getDouble(p.sub);
        rowData[9] = p.update;
        return rowData;
    }
}
