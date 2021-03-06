/*
 * Orf.java
 *
 * Created on 8 �ѹ��¹ 2548, 12:07 �.
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
public class Orf  implements Rp12OI{
    
    /** character width 9 */
    public String hn;
    /** datewidth 8 */
    public String dateopd;
    /** character width 4 */
    public String clinic;
    /** character width 5 */
    public String refer;
    /** character width 1 */
    public String refertype;
    public String pid;
    
    private int exp;

    public boolean add_digit;
    
    public Orf() {
    }
    public String[] getHeaderArray() {
        return new String[]{
            "hn",
            "dateopd",
            "clinic",
            "refer",
            "refertype"
        };
    }
    public String[] getWarningArray() {
        return new String[]{
"�Ţ HCIS",
"�ѹ������Ѻ��ԡ��",
"���ʤ�Թԡ�������ԡ��",
"ʶҹ��Һ���觵��",
"����������觵��"
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
            refertype
        };
    }
    
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) {
        BufferedReader in;
        Orf file = this;
        boolean ret = true;
        error[0]++;
        if(!Report12FileData.checkDataDict(	hn	,	9	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	dateopd	,	8	,	true	)) { 	error[2	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	clinic	,	4	,	true	)) { 	error[3	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	refer	,	5	,	false	)) { 	error[4	]++;    ret=false;}
        if(!Report12FileData.checkDataDict(	refertype	,	1	,	false	)) { 	error[5	]++;    ret=false;}
        if(!ret) {
            error[Report12FileData.MAX_COLUMN-1]++;
            if(error[Report12FileData.MAX_COLUMN-1]<Report12FileData.MAX_INCOMPLETE_ROW)
                sb.append("\r\n\t��¡�÷��Դ��Ҵ��� �Ţ HN:"+ file.hn + "  �ѹ���:" + dateopd);
            if(add_digit)
                sb.append(" �ա�û�Ѻ��ѡ�ͧ HN/VN/AN");
        }
        return ret;
    }
    
    public boolean setValue(ResultSet rs) throws Exception {
        Orf p = this;
        p.	hn	 = Report12FileData.getRsString(rs,	1	);
        p.	dateopd	 = Report12FileData.getRsString(rs,	2	);
        p.	clinic	 = Report12FileData.getRsString(rs,	3	);
        p.	refer	 = Report12FileData.getRsString(rs,	4	);
        p.	refertype	 = Report12FileData.getRsString(rs,	5	);
        
        p.hn = Report12FileData.addDigit(p.hn,9); 
        if(Report12FileData.isAddDigit(p.hn,9) )
            add_digit = true;
        return true;
    }
    
    public Rp12OI initInstant() {
        return new Orf();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file/12file_orf.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    
    public String getFileName() {
        return "ORF";
    }
    
    
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report12FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,9);
        Report12FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_D,0);
        Report12FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,4);       
        Report12FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,5);
        Report12FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,1);
        return fields;
    }
    public Object[] getDBFValue() {
        Orf p = this;
        Object[] rowData;
        rowData = new Object[5];
        
        rowData[0] = p.hn;
        if(!("").equals(p.dateopd)) {
            rowData[1] = StringDate.StringDateToDate(p.dateopd);
        } else {
            rowData[1] = null;
        }
        rowData[2] = p.clinic;
        rowData[3] = p.refer;
        rowData[4] = p.refertype;
        
        return rowData;
    }
}
