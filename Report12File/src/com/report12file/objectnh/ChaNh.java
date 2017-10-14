/*
 * Cha.java
 *
 * Created on 8 กันยายน 2548, 12:02 น.
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
public class ChaNh extends Cha implements Rp12OI{
    
    public ChaNh() {
    }
 
    public String[] getHeaderArray() {
        return new String[]{
"hn",
"an",
"date",
"chrgitem",
"amount",
"person_id"
};
    }
/**
 */
    public String[] getValueArray() {
        return new String[]{
hn	,
an	,
date	,
chrgitem	,
amount	,
pid
};
    }
    
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report12FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,9);
        Report12FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,9);
        Report12FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_D,9);
        Report12FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,2);
        Report12FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_N,7,0);
        Report12FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,13);
        return fields;
    }
    public Object[] getDBFValue() {
        Cha p = this;
        Object[] rowData;
        rowData = new Object[6];
        rowData[0] = p.hn;
        rowData[1] = p.an;
        rowData[2] = StringDate.StringDateToDate(p.date);
        rowData[3] = p.chrgitem;
        rowData[4] = Report12FileData.getDouble(p.amount);
        rowData[5] = p.pid;
        return rowData;
    }
    ///////////////////////////////////////////////////////addcheckData
     public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        BufferedReader in;
        ChaNh file = this;
        boolean ret = true;
        error[0]++;
        if(!Report12FileData.checkDataDict(	hn	,	9	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	an	,	9	,	false	)) { 	error[2	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	date	,	8	,	true	)) { 	error[3	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	chrgitem	,	2	,	false	)) { 	error[4	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	amount	,	7	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	pid	,	13	,	false	)) { 	error[6	]++;    ret=false;}
        if(!ret) {
            error[Report12FileData.MAX_COLUMN-1]++;
            if(error[Report12FileData.MAX_COLUMN-1]<Report12FileData.MAX_INCOMPLETE_ROW)
            sb.append("\r\n\tรายการที่ผิดพลาดคือ เลข HN:"+ file.hn + "  วันที่:" + date);
            if(add_digit)
                sb.append(" มีการปรับหลักของ HN/VN/AN");
       }
         return ret;
    }

    public boolean setValue(ResultSet rs) throws Exception {
        ChaNh p = this;
p.	hn	 = Report12FileData.getRsString(rs,	1	);
p.	an	 = Report12FileData.getRsString(rs,	2	);
p.	date	 = Report12FileData.getRsString(rs,	3	);
p.	chrgitem	 = Report12FileData.getRsString(rs,	4	);
p.	amount	 = Report12FileData.getRsString(rs,	5	);
p.	pid	 = Report12FileData.getRsString(rs,	6	);

        p.hn = Report12FileData.addDigit(p.hn,9); 
        if(Report12FileData.isAddDigit(p.hn,9) )
            add_digit = true;
            return true;
    }
    
    public Rp12OI initInstant() {
        return new ChaNh();
    }

    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file50/12file50_cha.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        pm.setString(3,startDate);
        pm.setString(4,endDate);
        return pm;
    }

}
