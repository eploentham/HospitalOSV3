/*
 * Fp.java
 *
 * Created on 1 สิงหาคม 2548, 10:04 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.object;

import com.linuxense.javadbf.DBFField;
import com.report18file.utility.Report18FileData;
import com.reportcenter.utility.IOStream;
import com.reportcenter.utility.StringDate;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Noom
 */
public class Fp  implements Rp18OI{
    /**สถานพยาบาล*/
    public String  pcucode = "";
    /**เลข HCIS*/
    public String  pid = "";
    /** เลข VN*/
    public String  seq = "";
    /** วันที่*/
    public String  date_serv = "";
    /**รหัสวิธีการคุมกำเเนิด*/
    public String  fptype = "";
    /**ชนิดของยา,เวชภัณฑ์*/
    public String  did = "";
    /**จำนวนเวชภัณฑ์*/
    public String  amount = "";
    /**สถานที่รับบริการ*/
    public String  fpplace = "";
    
    /** Creates a new instance of Fp */
    public Fp() {
    }
    public String[] getHeaderArray() {
        return new String[]{
            
            "pcucode",
            "pid",
            "seq",
            "date_serv",
            "fptype",
            "did",
            "amount",
            "fpplace"
                    
        };
    }
    
    public String[] getWarningArray() {
        return new String[]{
"สถานพยาบาล",
"เลข HCIS",
"เลข VN",
"วันที่",
"รหัสวิธีการคุมกำเเนิด",
"ชนิดของยา,เวชภัณฑ์",
"จำนวนเวชภัณฑ์",
"สถานที่รับบริการ"
        };
    }
    public String[] getValueArray() {
        return new String[]{
            pcucode	,
            pid	,
            seq	,
            date_serv	,
            fptype	,
            did	,
            amount	,
            fpplace
        };
    }
    
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) {
        BufferedReader in;
        
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode 	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid 	,	13	,	true,false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	seq 	,	8	,	true	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	date_serv	,	8	,	false	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	fptype	,	1	,	true,true,Report18FileData.VALID_17	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	did	,	6	,	false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	amount	,	3	,	false,false	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	fpplace	,	5	,	false	)) { 	error[8	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
                sb.append("\n\tรายการที่ผิดพลาดคือ เลข HCIS:"+ pid + " เลข VN:" + seq);
        }
        return ret;
    }
    
    public boolean setValue(ResultSet rs) throws Exception {
        Fp p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.pid = Report18FileData.getRsString(rs,2);
        p.seq = Report18FileData.getRsString(rs,3);
        while(p.seq.length()>8) p.seq = p.seq.substring(1);
        p.date_serv = Report18FileData.getRsString(rs,4);
        p.fptype = Report18FileData.getRsString(rs,5);
        p.did = Report18FileData.getRsString(rs,6);
        p.amount = Report18FileData.getRsString(rs,7);
        try{
            p.amount = String.valueOf(Double.parseDouble(p.amount));
        }catch(Exception e){
            p.amount = "0";
        }
        p.amount = Report18FileData.getBeforeDot(p.amount);
        p.fpplace = Report18FileData.getRsString(rs,8);
        return true;
    }
    
    public Rp18OI initInstant() {
        return new Fp();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        
        String sql = IOStream.readInputDefault("config/rp_18file/18file_fp.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    
    public String getFileName() {
        return "FP";
    }
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,6);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,3);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,5);
        
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Fp p = this;
        rowData = new Object[8];
        rowData[0] = p.pcucode;
        rowData[1] = p.pid;
        rowData[2] = p.seq;
        rowData[3] = p.date_serv;
        rowData[4] = p.fptype;
        rowData[5] = p.did;
        rowData[6] = p.amount;
        rowData[7] = p.fpplace;
        return rowData;
    }
}
