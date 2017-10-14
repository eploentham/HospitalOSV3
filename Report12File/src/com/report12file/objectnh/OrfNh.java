/*
 * Orf.java
 *
 * Created on 8 กันยายน 2548, 12:07 น.
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
public class OrfNh extends Orf  implements Rp12OI{
    
    public OrfNh() {
    }
    public String[] getHeaderArray() {
        return new String[]{
            "hn",
            "dateopd",
            "clinic",
            "refer",
            "refertype",
            "person_id"
        };
    }
    /**
     */
    public String[] getValueArray() {
        return new String[]{
            hn	,
            dateopd	,
            clinic	,
            refer	,
            refertype	,
            pid
        };
    }
    
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report12FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,9);
        Report12FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_D,0);
        Report12FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,4);       
        Report12FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,5);
        Report12FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,1);
        Report12FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,13);
        return fields;
    }
    public Object[] getDBFValue() {
        Orf p = this;
        Object[] rowData;
        rowData = new Object[6];
        
        rowData[0] = p.hn;
        if(!("").equals(p.dateopd)) {
            rowData[1] = StringDate.StringDateToDate(p.dateopd);
        } else {
            rowData[1] = null;
        }
        rowData[2] = p.clinic;
        rowData[3] = p.refer;
        rowData[4] = p.refertype;
        
        rowData[5] = p.pid;
        return rowData;
    }
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) {
        BufferedReader in;
        OrfNh file = this;
        boolean ret = true;
        error[0]++;
        if(!Report12FileData.checkDataDict(	hn	,	9	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	dateopd	,	8	,	true	)) { 	error[2	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	clinic	,	4	,	true	)) { 	error[3	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	refer	,	5	,	false	)) { 	error[4	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	refertype	,	1	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	pid	,	13	,	false	)) { 	error[6	]++;    ret=false;}
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
        OrfNh p = this;
        p.	hn	 = Report12FileData.getRsString(rs,	1	);
        p.	dateopd	 = Report12FileData.getRsString(rs,	2	);
        p.	clinic	 = Report12FileData.getRsString(rs,	3	);
        p.	refer	 = Report12FileData.getRsString(rs,	4	);
        p.	refertype	 = Report12FileData.getRsString(rs,	5	);
        p.	pid	 = Report12FileData.getRsString(rs,	6	);
        
        p.hn = Report12FileData.addDigit(p.hn,9); 
        if(Report12FileData.isAddDigit(p.hn,9) )
            add_digit = true;
        return true;
    }
    
    public Rp12OI initInstant() {
        return new OrfNh();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file50/12file50_orf.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    
}
