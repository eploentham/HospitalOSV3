/*
 * Ipd.java
 *
 * Created on 8 กันยายน 2548, 12:05 น.
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
public class IpdNh extends Ipd implements Rp12OI{
    
    public IpdNh() {
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
"dept"
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
dept
};
    }
    
    ///////////////////////////////////////////////////////addcheckData
     public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        BufferedReader in;
        IpdNh file = this;
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
        if(!ret) {
            error[Report12FileData.MAX_COLUMN-1]++;
            if(error[Report12FileData.MAX_COLUMN-1]<Report12FileData.MAX_INCOMPLETE_ROW)
                sb.append("\r\n\tรายการที่ผิดพลาดคือ เลข AN:"+ file.an);
            if(add_digit)
                sb.append(" มีการปรับหลักของ HN/VN/AN");
        }
         return ret;
    }

    
    public Rp12OI initInstant() {
        return new IpdNh();
    }



    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report12FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,9);
        Report12FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,9);
        Report12FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,8);
        Report12FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,4);
        Report12FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,8);
        Report12FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,4);
        Report12FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,1);
        Report12FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,1);
        Report12FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,4);
        Report12FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,2);
//        fields[10].setDecimalCount(3);
        return fields;
    }    
    public Object[] getDBFValue() {
        Ipd p = this;
        Object[] rowData;
                rowData = new Object[10];
                rowData[0] = p.hn;
                rowData[1] = p.an;
                rowData[2] = p.dateadm;
                rowData[3] = p.timeadm;
                rowData[4] = p.datedsc;
                rowData[5] = p.timedsc;
                rowData[6] = p.dischs;
                rowData[7] = p.discht;
                rowData[8] = p.warddsc;
                rowData[9] = p.dept;
                
        return rowData;
    }

}
