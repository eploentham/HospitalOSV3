/*
 * Pat.java
 *
 * Created on 8 กันยายน 2548, 12:07 น.
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author tong(Padungrat)
 */
public class Pat implements Rp12OI{
    
    public boolean add_digit;
    /** character width 5 */
    public String hcode;
    /** character width 9 */
    public String hn;
    /** character width 2 */
    public String changwat;
    /** character width 2 */
    public String amphur;
    /** date width 8 */
    public String dob;
    /** character width 1 */
    public String sex;
    /** character width 1 */
    public String marriage;
    /** character width 3 */
    public String occupa;
    /** character width 2 */
    public String nation;
    /** character width 13 */
    public String person_id;
    /** character width 35 */
    public String namepat;
    
    private int exp;
    public Pat() {
    }
    public String[] getHeaderArray() {
        return new String[]{
            "hcode",
            "hn",
            "changwat",
            "amphur",
            "dob",
            "sex",
            "marriage",
            "occupa",
            "nation",
            "person_id"
        };
    }
    public String[] getWarningArray() {
        return new String[]{
"สถานพยาบาล",
"เลข HCIS",
"รหัสจังหวัด",
"รหัสอำเภอ",
"วันเกิด",
"เพศ",
"สภาพสมรส",
"อาชีพ",
"สัญชาติ",
"เลขบัตรประชาชน"
        };
    }
    /**
     */
    public String[] getValueArray() {
        return new String[]{
            hcode	,
            hn	,
            changwat	,
            amphur	,
            dob	,
            sex	,
            marriage	,
            occupa	,
            nation	,
            person_id
        };
    }
    
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) {
        BufferedReader in;
        Pat file = this;
        boolean ret = true;
        error[0]++;
        if(!Report12FileData.checkDataDict(	hcode	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	hn	,	9	,	true	)) { 	error[2	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	changwat	,	2	,	false	)) { 	error[3	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	amphur	,	2	,	false	)) { 	error[4	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	dob	,	8	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	sex	,	1	,	false	)) { 	error[6	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	marriage	,	1	,	false	)) { 	error[7	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	occupa	,	3	,	false	)) { 	error[8	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	nation	,	2	,	false	)) { 	error[9	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	person_id	,	13	,	true	)) { 	error[10	]++;    ret=false;}
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
        Pat p = this;
        p.	hcode	 = Report12FileData.getRsString(rs,	1	);
        p.	hn	 = Report12FileData.getRsString(rs,	2	);
        p.	changwat	 = Report12FileData.getRsString(rs,	3	);
        p.	amphur	 = Report12FileData.getRsString(rs,	4	);
        p.	dob	 = Report12FileData.getRsString(rs,	5	);
        p.	sex	 = Report12FileData.getRsString(rs,	6	);
        p.	marriage	 = Report12FileData.getRsString(rs,	7	);
        p.	occupa	 = Report12FileData.getRsString(rs,	8	);
        if(p.occupa.length()==2)
            p.occupa = "0"+p.occupa;
        p.	nation	 = Report12FileData.getRsString(rs,	9	);
        p.	person_id	 = Report12FileData.getRsString(rs,	10	);
        
        p.hn = Report12FileData.addDigit(p.hn,9); 
        if(Report12FileData.isAddDigit(p.hn,9) )
            add_digit = true;
        return true;
    }
    
    public Rp12OI initInstant() {
        return new Pat();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file/12file_pat1.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        pm.setString(3,startDate);
        pm.setString(4,endDate);
        return pm;
    }
    
    public String getFileName() {
        return "PAT";
    }
    
    
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report12FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report12FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,9);
        Report12FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,2);
        Report12FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,2);
        Report12FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_D,5);
        Report12FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,1);
        Report12FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,1);
        Report12FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,3);
        Report12FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,2);
        Report12FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,13);
        return fields;
    }
    public Object[] getDBFValue() {
        Pat p = this;
        Object[] rowData = new Object[10];
        
        rowData[0] = p.hcode;
        rowData[1] = p.hn;
        rowData[2] = p.changwat;
        rowData[3] = p.amphur;
        rowData[4] = StringDate.StringDateToDate(p.dob);
        rowData[5] = p.sex;
        rowData[6] = p.marriage;
        rowData[7] = p.occupa;
        rowData[8] = p.nation;
        rowData[9] = p.person_id;
        return rowData;
    }
    public static void main(String[] argc){
        Calendar calendar = new GregorianCalendar();
        Date date = calendar.getTime();
        DateFormat localFormat = DateFormat.getDateInstance();
        DateFormat format1 = new SimpleDateFormat( "yyyyMMdd" );
        System.out.println( "yyyyMMdd   " + format1.format( date ) );
        DateFormat format2 = new SimpleDateFormat( "yyyy.MM.dd" );
        DateFormat format3 = new SimpleDateFormat( "MM/dd/yyyy" );
        DateFormat format4 = new SimpleDateFormat( "MM-dd-yyyy" );
        System.out.println( "Localized  " + localFormat.format( date ) );
        System.out.println( "yyyy.MM.dd " + format2.format( date ) );
        System.out.println( "MM/dd/yyyy " + format3.format( date ) );
        System.out.println( "MM-dd-yyyy " + format4.format( date ) );
    }
}
