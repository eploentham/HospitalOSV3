/*
 * Opd.java
 *
 * Created on 8 กันยายน 2548, 12:06 น.
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
import com.reportcenter.utility.StringDate;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tong(Padungrat)
 */
public class OpdNh extends Opd implements Rp12OI{
    
    public OpdNh() {
    }
    public String[] getHeaderArray() {
        return new String[]{
            "hn",
            "clinic",
            "dateopd",
            "person_id"
        };
    }
    /**
     */
    public String[] getValueArray() {
        return new String[]{
            hn	,
            clinic	,
            dateopd	,
            pid
        };
    }
    
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report12FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,9);
        Report12FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,4);
        Report12FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_D,9);
        Report12FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,13);
        return fields;
    }
    public Object[] getDBFValue() {
        Opd p = this;
        Object[] rowData;
        rowData = new Object[4];
        
        rowData[0] = p.hn;
        rowData[1] = p.clinic;
        //  rowData[2] = p.dateopd;
        if(!("").equals(p.dateopd)) {
            
            rowData[2] = StringDate.StringDateToDate(p.dateopd);
        }else{
            rowData[2] = null;
        }
        
        rowData[3] = p.pid;
        return rowData;
    }
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) {
        BufferedReader in;
        OpdNh file = this;
        boolean ret = true;
        error[0]++;
        if(!Report12FileData.checkDataDict(	hn	,	9	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	clinic	,	4	,	true	)) { 	error[2	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	dateopd	,	8	,	true	)) { 	error[3	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	pid	,	13	,	false	)) { 	error[4	]++;    ret=false;}
        if(!ret) {
            error[Report12FileData.MAX_COLUMN-1]++;
            if(error[Report12FileData.MAX_COLUMN-1]<Report12FileData.MAX_INCOMPLETE_ROW)
                sb.append("\r\n\tรายการที่ผิดพลาดคือ เลข HN:"+ file.hn + "  วันที่:" + dateopd);
            if(add_digit)
                sb.append(" มีการปรับหลักของ HN/VN/AN");
        }
        return ret;
    }
    
    public boolean setValue(ResultSet rs) throws Exception {
        OpdNh p = this;
        p.	hn	 = Report12FileData.getRsString(rs,	1	);
        p.	clinic	 = Report12FileData.getRsString(rs,	2	);
        p.	dateopd	 = Report12FileData.getRsString(rs,	3	);
        p.	pid	 = Report12FileData.getRsString(rs,	4	);
        
        p.hn = Report12FileData.addDigit(p.hn,9); 
        if(Report12FileData.isAddDigit(p.hn,9) )
            add_digit = true;
        return true;
    }
    
    public Rp12OI initInstant() {
        return new OpdNh();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file50/12file50_opd.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    
}
