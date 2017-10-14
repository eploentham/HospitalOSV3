/*
 * Ins.java
 *
 * Created on 8 กันยายน 2548, 12:04 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.object;

import com.report12file.utility.Constant;
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
public class Ins  implements Rp12OI{
    
    /** character width 9 */
    public String hn;
    /** character width 2 */
    public String inscl;
    /** character width 2 */
    public String subtype;
    /** character width 16 */
    public String cid;
    /** date width 8 */
    public String datein;
    /** date width 8 */
    public String dateexp;
    /** character width 5 */
    public String hospmain;
    /** character width 5 */
    public String hospsub;
    
    public static String EMPTY = "";

    public boolean add_digit;
    public Ins() {
    }

    public void updateNull() 
    {
        inscl = Constant.getNotNullValue(inscl);
        subtype = Constant.getNotNullValue(subtype);
        cid = Constant.getNotNullValue(cid);
        datein = Constant.getNotNullValue(datein);
        dateexp = Constant.getNotNullValue(dateexp);
        hospmain = Constant.getNotNullValue(hospmain);
        hospsub = Constant.getNotNullValue(hospsub);
    }
    public String[] getHeaderArray() {
        return new String[]{
"hn",
"inscl",
"subtype",
"cid",
"datein",
"dateexp",
"hospmain",
"hospsub"
};
    }
    public String[] getWarningArray() {
        return new String[]{"เลข HN",
"สิทธิการรักษา",
"ระดับสิทธิของหลักประกัน",
"หมายเลขบัตร",
"วันที่มีสิทธิ",
"วันที่หมดสิทธิ",
"สถานพยาบาลหลัก",
"สถานพยาบาลรอง"
        };
    }
/**
 */
    public String[] getValueArray() {
        return new String[]{
hn	,
inscl	,
subtype	,
cid	,
datein	,
dateexp	,
hospmain	,
hospsub	
};
    }
    
    ///////////////////////////////////////////////////////addcheckData
     public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        BufferedReader in; 
        Ins file = this;
        boolean ret = true;
        error[0]++;
        if(!Report12FileData.checkDataDict(	hn	,	9	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	inscl	,	2	,	false	)) { 	error[2	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	subtype	,	2	,	false	)) { 	error[3	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	cid	,	16	,	false	)) { 	error[4	]++;    ret=false;}
        if(!Report12FileData.checkDataDictDate(	datein	,	8	,	false	)) {    error[5	]++;    ret=false;}
        if(!Report12FileData.checkDataDictDate(	dateexp	,	8	,	false	)) {    error[6	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	hospmain	,	5	,	false	)) { 	error[7	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	hospsub	,	5	,	false	)) { 	error[8	]++;    ret=false;}
        if(!ret) {
            error[Report12FileData.MAX_COLUMN-1]++;
            if(error[Report12FileData.MAX_COLUMN-1]<Report12FileData.MAX_INCOMPLETE_ROW)
                sb.append("\r\n\tรายการที่ผิดพลาดคือ เลข HN:"+ file.hn);
            if(add_digit)
                sb.append(" มีการปรับหลักของ HN/VN/AN");
        }
         return ret;
    }

    public boolean setValue(ResultSet rs) throws Exception {
        Ins p = this;
	p.	hn	 = Report12FileData.getRsString(rs,	1	);
	p.	inscl	 = Report12FileData.getRsString(rs,	2	);
	p.	subtype	 = Report12FileData.getRsString(rs,	3	);
	p.	cid	 = Report12FileData.getRsString(rs,	4	);
	p.	datein	 = Report12FileData.getRsString(rs,	5	);
	p.	dateexp	 = Report12FileData.getRsString(rs,	6	);
	p.	hospmain	 = Report12FileData.getRsString(rs,	7	);
	p.	hospsub	 = Report12FileData.getRsString(rs,	8	);

        p.hn = Report12FileData.addDigit(p.hn,9); 
        if(Report12FileData.isAddDigit(p.hn,9) )
            add_digit = true;
            return true;
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file/12file_ins.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        pm.setString(3,startDate);
        pm.setString(4,endDate);
        return pm;
    }

    public String getFileName() {
        return "INS";
    }   


    public Rp12OI initInstant() {
        return new Ins();
    }

    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report12FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,9);
        Report12FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,2);
        Report12FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,2);
        Report12FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,16);
        Report12FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_D,8);
        Report12FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_D,8);
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
                rowData[4] = StringDate.StringDateToDate(p.datein);
                rowData[5] = StringDate.StringDateToDate(p.dateexp);
                rowData[6] = p.hospmain;
                rowData[7] = p.hospsub;  
        return rowData;
    }
}
