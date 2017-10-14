/*
 * Cht.java
 *
 * Created on 8 กันยายน 2548, 12:03 น.
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
public class Cht implements Rp12OI{
     /** character width 9 */
    public String hn;
    /** character width 9 */
    public String an;
    /** date width 8 */
    public String date;
    /** numeric width 20 */
    public String total;
    /** numeric width 20 */
    public String paid;
    /** character width 2 */
    public String pttype;

    public String pid;

    private int exp;

    public boolean add_digit;
    public Cht() {
    }
     public String[] getHeaderArray() {
        return new String[]{
"hn",
"an",
"date",
"total",
"paid",
"pttype" 
};
    }
    public String[] getWarningArray() {
        return new String[]{
"เลข HCIS",
"เลข AN",
"วันที่คิดค่ารักษา",
"จำนวนเงินรวม",
"จำนวนเงินที่ผู้ป่วยจ่าย",
"ชนิดการชำระเงิน"
        };
    }
/**
 */
    public String[] getValueArray() {
        return new String[]{
hn	,
an	,
date	,
total	,
paid	,
pttype	 
};
    }
    
    ///////////////////////////////////////////////////////addcheckData
     public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        BufferedReader in;
        Cht file = this;
        boolean ret = true;
        error[0]++;
        if(!Report12FileData.checkDataDict(	hn	,	9	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	an	,	9	,	false	)) { 	error[2	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	date	,	8	,	true	)) { 	error[3	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	total	,	7	,	false	)) { 	error[4	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	paid	,	7	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	pttype	,	2	,	false	)) { 	error[6	]++;    ret=false;}
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
        Cht p = this;
p.	hn	 = Report12FileData.getRsString(rs,	1	);
p.	an	 = Report12FileData.getRsString(rs,	2	);
p.	date	 = Report12FileData.getRsString(rs,	3	);
p.	total	 = Report12FileData.getRsString(rs,	4	);
p.	paid	 = Report12FileData.getRsString(rs,	5	);
p.	pttype	 = Report12FileData.getRsString(rs,	6	);

        p.hn = Report12FileData.addDigit(p.hn,9);
        p.an = Report12FileData.addDigit(p.an,9);
        if(Report12FileData.isAddDigit(p.hn,9)
        || Report12FileData.isAddDigit(p.an,9))
            add_digit = true;
            return true;
    }
    
    public Rp12OI initInstant() {
        return new Cht();
    }

    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file/12file_cht.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        pm.setString(3,startDate);
        pm.setString(4,endDate);
        return pm;
    }

    public String getFileName() {
        return "CHT";
    }   


    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report12FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,9);
        Report12FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,9);
        Report12FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_D,9);
        Report12FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_N,7,0);
        Report12FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_N,7,0);
        Report12FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,2);
            return fields;
    }   

    public Object[] getDBFValue() {
        Cht p = this;
        Object[] rowData;rowData = new Object[6];
                rowData[0] = p.hn ;
                rowData[1] = p.an ;                
                rowData[2] = StringDate.StringDateToDate(p.date);
                rowData[3] = Report12FileData.getDouble(p.total);
                rowData[4] = Report12FileData.getDouble(p.paid);
                rowData[5] = p.pttype;
                
        return rowData;
    }
}
