/*
 * Irf.java
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
import com.reportcenter.utility.IOStream;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tong(Padungrat)
 */
public class Irf  implements Rp12OI{
    
    /** character width 9 */
    public String an;
    /** character width 5 */
    public String refer;
    /** character width 1 */
    public String refertype;

    public boolean add_digit;
    public Irf() {
    }
    public String[] getHeaderArray() {
        return new String[]{
"an",
"refer",
"refertype"
};
    }
    public String[] getWarningArray() {
        return new String[]{
"เลข AN",
"ตามสถานพยาบาล",
"ชนิดการ Refer"
        };
    }
/**
 */
    public String[] getValueArray() {
        return new String[]{
an	,
refer	,
refertype
};
    }
    
    ///////////////////////////////////////////////////////addcheckData
     public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        BufferedReader in;
        Irf file = this;
        boolean ret = true;
        error[0]++;
        if(!Report12FileData.checkDataDict(	an	,	9	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	refer	,	5	,	true	)) { 	error[2	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	refertype	,	1	,	true,true,Report12FileData.VALID_12	)) { 	error[3	]++;    ret=false;}
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
        Irf p = this;
	p.	an	 = Report12FileData.getRsString(rs,	1	);
	p.	refer	 = Report12FileData.getRsString(rs,	2	);
	p.	refertype	 = Report12FileData.getRsString(rs,	3	);
            return true;
    }
    
    public Rp12OI initInstant() {
        return new Irf();
    }

    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file/12file_irf.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }

    public String getFileName() {
        return "IRF";
    }   


    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report12FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,9);
        Report12FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,5);
        Report12FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,1);
        return fields;
    }  
    public Object[] getDBFValue() {
        Irf p = this;
        Object[] rowData;
        rowData = new Object[3];
                rowData[0] = p.an;
                rowData[1] = p.refer;
                rowData[2] = p.refertype;
        return rowData;
    }  
}
