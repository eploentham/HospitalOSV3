/*
 * Iop.java
 *
 * Created on 8 กันยายน 2548, 12:04 น.
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
public class Iop  implements Rp12OI{
    
    /** character width 9 */
    public String an;
    /** character width 4 */
    public String oper;
    /** character width 1 */
    public String optype;
    /** character width 6 */
    public String drop;
    /** date width 8 */
    public String datein;
    /** character width 4 */
    public String timein;
    /** date width 8 */
    public String dateout;
    /** character width 4 */
    public String timeout;

    public boolean add_digit;
    public Iop() {
    }
     public String[] getHeaderArray() {
        return new String[]{
"an",
"oper",
"optype",
"drop",
"datein",
"timein",
"dateout",
"timeout"
};
    }
    public String[] getWarningArray() {
        return new String[]{
"เลข AN",
"รหัสหัตถการ",
"ชนิดของหัตถการ",
"เลขที่ใบประกอบวิชาชีพ",
"วันเริ่มหัตถการ",
"เวลาเริ่ม",
"วันสิ้นสุดหัตถการ",
"เวลาสิ้นสุด"
        };
    }
/**
 */
    public String[] getValueArray() {
        return new String[]{
an	,
oper	,
optype	,
drop	,
datein	,
timein	,
dateout	,
timeout	
};
    }
    
    ///////////////////////////////////////////////////////addcheckData
     public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        BufferedReader in;
        Iop file = this;
        boolean ret = true;
        error[0]++;
        if(!Report12FileData.checkDataDict(	an	,	9	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	oper	,	5	,	true,false	)) { 	error[2	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	optype	,	1	,	true	)) { 	error[3	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	drop	,	6	,	true,false	)) { 	error[4	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	datein	,	8	,	true	)) { 	error[5	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	timein	,	4	,	true	)) { 	error[6	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	dateout	,	8	,	true	)) { 	error[7	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	timeout	,	4	,	true	)) { 	error[8	]++;    ret=false;}
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
        Iop p = this;
p.	an	 = Report12FileData.getRsString(rs,	1	);
p.	oper	 = Report12FileData.getRsString(rs,	2	);
p.	optype	 = Report12FileData.getRsString(rs,	3	);
p.	drop	 = Report12FileData.getRsString(rs,	4	);
p.	datein	 = Report12FileData.getRsString(rs,	5	);
p.	timein	 = Report12FileData.getRsString(rs,	6	);
p.	dateout	 = Report12FileData.getRsString(rs,	7	);
p.	timeout	 = Report12FileData.getRsString(rs,	8	);

                p.oper = subStringICD9(p.oper);
                p.timein = p.timein.replaceAll(":","");
                p.timeout = p.timeout.replaceAll(":","");
            return true;
    }
    
    public Rp12OI initInstant() {
        return new Iop();
    }

    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file/12file_iop.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }

    public String getFileName() {
        return "IOP";
    }   


    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report12FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,9);
        Report12FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,5);
        Report12FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,1);
        Report12FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,6);
        Report12FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_D,8);
        Report12FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,4);
        Report12FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_D,8);
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
                if(!("").equals(p.datein))
                    rowData[4] = StringDate.StringDateToDate(p.datein);
                rowData[5] = p.timein;
                if(!("").equals(p.dateout))
                    rowData[6] = StringDate.StringDateToDate(p.dateout);
                rowData[7] = p.timeout;
        return rowData;
    }  
    /** เอาจุดเลขทศนิยมของหมายเลข ICD9 ออกจากหมายเลข ICD9
     *@Param originalStr เป็น String ที่ต้องการ substring
     *@Return String เป็นข้อความที่ได้หลังจากการ substring
     *@Date 19/10/2549
     *@Author Pu
     *@Modify Ojika 16/12/2549
     */ 
    public String subStringICD9(String originalStr)
    {
        String ori = "";
        ori = originalStr;
        if(ori.equals(null) || ori == null || ori.trim().equals(""))
        {
            ori = "";
        }
        else if(ori.trim().length() > 2)
        {
            ori = ori.substring(0, 2) + ori.substring(3);    
        }
        else
        {
            ori = originalStr.trim();
        }
        return ori;
    }
    
}
