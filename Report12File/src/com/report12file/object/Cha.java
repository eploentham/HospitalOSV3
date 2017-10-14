/*
 * Cha.java
 *
 * Created on 8 กันยายน 2548, 12:02 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.object;

import com.linuxense.javadbf.DBFField;
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
public class Cha implements Rp12OI{
    
    /** character width 9 */
    public String hn;
    /** character width 9 */
    public String an;
    /** date width 8 */
    public String date;
    /** chrgitem width 2 */
    public String chrgitem;
    /** numeric width 20 */
    public String amount; 
    public String pid;

    private int exp;

    public boolean add_digit;
    public Cha() {
    }
 
    public String[] getHeaderArray() {
        return new String[]{
"hn",
"an",
"date",
"chrgitem",
"amount" 
};
    }
    public String[] getWarningArray() {
        return new String[]{
"เลข HCIS",
"เลข AN",
"วันที่คิดค่ารักษา",
"ชนิดของบริการ",
"จำนวนเงิน"
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
amount	 
};
    }
    
    ///////////////////////////////////////////////////////addcheckData
     public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        BufferedReader in;
        Cha file = this;
        boolean ret = true;
        error[0]++;
        if(!Report12FileData.checkDataDict(	hn	,	9	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	an	,	9	,	false	)) { 	error[2	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	date	,	8	,	true	)) { 	error[3	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	chrgitem	,	2	,	false	)) { 	error[4	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	amount	,	7	,	false	)) { 	error[5	]++;    ret=false;}
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
        Cha p = this;
p.	hn	 = Report12FileData.getRsString(rs,	1	);
p.	an	 = Report12FileData.getRsString(rs,	2	);
p.	date	 = Report12FileData.getRsString(rs,	3	);
p.	chrgitem	 = Report12FileData.getRsString(rs,	4	);
p.	amount	 = Report12FileData.getRsString(rs,	5	);

        p.hn = Report12FileData.addDigit(p.hn,9);
        p.an = Report12FileData.addDigit(p.an,9);
        if(Report12FileData.isAddDigit(p.hn,9)
        || Report12FileData.isAddDigit(p.an,9))
            add_digit = true;
        return true;
    }
    
    public Rp12OI initInstant() {
        return new Cha();
    }

    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file/12file_cha.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        pm.setString(3,startDate);
        pm.setString(4,endDate);
        return pm;
    }

    public String getFileName() {
        return "CHA";
    }   


    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report12FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,9);
        Report12FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,9);
        Report12FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_D,9);
        Report12FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,2);
        Report12FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_N,7,0);
            return fields;
    }

    public Object[] getDBFValue() {
        Cha p = this;
        Object[] rowData;
        rowData = new Object[5];
                rowData[0] = p.hn;
                rowData[1] = p.an;
                rowData[2] = StringDate.StringDateToDate(p.date);
                rowData[3] = p.chrgitem;
                rowData[4] = Report12FileData.getDouble(p.amount);
                return rowData;
    }
}
