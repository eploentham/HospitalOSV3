/*
 * Ipd.java
 *
 * Created on 8 กันยายน 2548, 12:05 น.
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
import java.text.NumberFormat;

/**
 *
 * @author tong(Padungrat)
 */
public class Ipd  implements Rp12OI{
    
    /** character width 9 */
    public String hn;
    /** character width 9 */
    public String an;
    /** date width 8 */
    public String dateadm;
    /** character width 4 */
    public String timeadm;
    /** date width 8 */
    public String datedsc;
    /** character width 4 */
    public String timedsc;
    /** character width 1 */
    public String dischs;
    /** character width 1 */
    public String discht;
    /** character width 4 */
    public String warddsc;
    /** character width 2 */
    public String dept;
    /** numeric width 7 */
    public String adm_w;

    public boolean add_digit;
    public Ipd() {
    }
    public String[] getHeaderArray() {
        return new String[]{
"hn",
"an",
"dateadm",
"timeadm",
"datedsc",
"timedsc",
"dischs",
"discht",
"warddsc",
"dept",
"adm_w"
};
    }
    public String[] getWarningArray() {
        return new String[]{
"เลข HCIS",
"เลข AN",
"วันรับเข้า",
"เวลารับเข้า",
"วันจำหน่าย",
"เวลาจำหน่าย",
"สถานภาพจำหน่าย",
"วิธีการจำหน่าย",
"ตึกที่จำหน่าย",
"แผนกที่รักษา",
"น้ำหนักแรกรับ"
        };
    }
/**
 */
    public String[] getValueArray() {
        return new String[]{
hn	,
an	,
dateadm	,
timeadm	,
datedsc	,
timedsc	,
dischs	,
discht	,
warddsc	,
dept,
adm_w
};
    }
    
    ///////////////////////////////////////////////////////addcheckData
     public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        BufferedReader in;
        Ipd file = this;
        boolean ret = true;
        error[0]++;
        if(!Report12FileData.checkDataDict(	hn	,	9	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	an	,	9	,	true	)) { 	error[2	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	dateadm	,	8	,	true	)) { 	error[3	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	timeadm	,	4	,	true	)) { 	error[4	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	datedsc	,	8	,	true	)) { 	error[5	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	timedsc	,	4	,	true	)) { 	error[6	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	dischs	,	1	,	true	)) { 	error[7	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	discht	,	1	,	true	)) { 	error[8	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	warddsc	,	4	,	true,false	)) { 	error[9	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	dept	,	2	,	true	)) { 	error[10	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	adm_w	,	7	,	false	)) { 	error[11	]++;    ret=false;}
        if(!ret) {
            error[Report12FileData.MAX_COLUMN-1]++;
            if(error[Report12FileData.MAX_COLUMN-1]<Report12FileData.MAX_INCOMPLETE_ROW)
            sb.append("\r\n\tรายการที่ผิดพลาดคือ เลข AN:"+ file.an);
            if(add_digit)
                sb.append(" มีการปรับหลักของ HN/VN/AN");
        }
         return ret;
    }

    public boolean setValue(ResultSet rs) throws Exception {
        Ipd p = this;
	p.	hn	 = Report12FileData.getRsString(rs,	1	);
	p.	an	 = Report12FileData.getRsString(rs,	2	);
	p.	dateadm	 = Report12FileData.getRsString(rs,	3	);
	p.	timeadm	 = Report12FileData.getRsString(rs,	4	);
	p.	datedsc	 = Report12FileData.getRsString(rs,	5	);
	p.	timedsc	 = Report12FileData.getRsString(rs,	6	);
	p.	dischs	 = Report12FileData.getRsString(rs,	7	);
	p.	discht	 = Report12FileData.getRsString(rs,	8	);
	p.	warddsc	 = Report12FileData.getRsString(rs,	9	);
        while(p.warddsc.length()>6) p.warddsc = p.warddsc.substring(1);
	p.	dept	 = Report12FileData.getRsString(rs,	10	);
        p.	adm_w	 = Report12FileData.getRsString(rs,	11	);
        try{
            double weight = Double.parseDouble(p.adm_w);
            p.adm_w = NumberFormat.getNumberInstance().format(weight*1000);
        }
        catch(Exception e){}
                 p.timeadm = p.timeadm.replaceAll(":","");
                 p.timedsc = p.timedsc.replaceAll(":","");
        p.hn = Report12FileData.addDigit(p.hn,9);
        p.an = Report12FileData.addDigit(p.an,9);
        if(Report12FileData.isAddDigit(p.hn,9)
        || Report12FileData.isAddDigit(p.an,9))
            add_digit = true;
        return true;
    }
    
    public Rp12OI initInstant() {
        return new Ipd();
    }

    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file/12file_ipd.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }

    public String getFileName() {
        return "IPD";
    }   


    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report12FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,9);
        Report12FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,9);
        Report12FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_D,9);
        Report12FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,4);
        Report12FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_D,9);
        Report12FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,4);
        Report12FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,1);
        Report12FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,1);
        Report12FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,4);
        Report12FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,2);
        Report12FileData.initDBFField(10,header,fields,DBFField.FIELD_TYPE_N,7,3);
//        fields[10].setDecimalCount(3);
        return fields;
    }    
    public static void main(String[] argc){
        System.out.println(
                NumberFormat.getNumberInstance().format(Double.parseDouble("11.199999")));
    }
    public Object[] getDBFValue() {
        Ipd p = this;
        Object[] rowData;
                rowData = new Object[11];
                rowData[0] = p.hn;
                rowData[1] = p.an;
                rowData[2] = StringDate.StringDateToDate(p.dateadm);
                rowData[3] = p.timeadm;
                rowData[4] = StringDate.StringDateToDate(p.datedsc);
                rowData[5] = p.timedsc;
                rowData[6] = p.dischs;
                rowData[7] = p.discht;
                rowData[8] = p.warddsc;
                rowData[9] = p.dept;
                rowData[10] = Report12FileData.getDouble(p.adm_w);
        return rowData;
    }
}
