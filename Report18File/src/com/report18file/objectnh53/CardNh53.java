package com.report18file.objectnh53;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Card;
import com.report18file.object.Rp18OI;
import com.report18file.utility.Report18FileData;
import java.io.BufferedReader;
import com.reportcenter.utility.IOStream;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
/*
 * AncNh.java
 *
 * Created on 11 กันยายน 2551, 15:17 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author henbe
 */
public class CardNh53 extends Card implements Rp18OI{
    
    /** Creates a new instance of AncNh */
    public CardNh53() {
    }

    public String[] getWarningArray() {
        return new String[]{
"รหัสสถานบริการ",
"เลขที่บัตรประชาชน",
"รหัส บุคคล",
"ประเภทบัตรสิทธิ",
"เลขที่บัตรสิทธิ",
"วันที่ออกบัตร",
"วันที่หมดอายุ",
"สถานบริการหลัก",
"สถานบริการรอง",
"วันที่ปรับปรุงข้อมูล"
        };
    }
    public String[] getHeaderArray() {
        return new String[]{
            "pcucode",
            "cid",
            "pid",
            "instype",
            "insid",
            "start",
            "expir",
            "main",
            "sub",
            "d_update"
        };
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {

        String sql = IOStream.readInputDefault("config/rp_18file_nhso53/18file_nhso53_card.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        pm.setString(3,startDate);
        pm.setString(4,endDate);
        return pm;
    }
    public Rp18OI initInstant() {
        return new CardNh53();
    }

    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) {
        BufferedReader in;
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode 	,	5	,	true,false	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cid	,	13	,	false,true	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	true,false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	instype	,	2	,	false	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	insid	,	18	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	start	,	8	,	false,true	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	expir	,	8	,	false,true	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	main	,	5	,	false	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	sub	,	5	,	false	)) { 	error[9	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	update	,	14	,	true	)) { 	error[10	]++;    ret=false;}

        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
                sb.append("\n\tรายการที่ผิดพลาดคือ เลข HCIS:"+ pid);
        }
        return ret;
    }
    
    
    public boolean setValue(ResultSet rs) throws Exception {
        Card p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.cid = Report18FileData.getRsString(rs,2);
        p.pid = Report18FileData.getRsString(rs,3);
        p.instype = Report18FileData.getRsString(rs,4);
        p.insid = Report18FileData.getRsString(rs,5);
        p.start = Report18FileData.getRsString(rs,6);
        p.expir = Report18FileData.getRsString(rs,7);
        p.main = Report18FileData.getRsString(rs,8);
        p.sub = Report18FileData.getRsString(rs,9);
        p.update = Report18FileData.getRsString(rs,10);
        return true;
    }
    
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,18);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,14);
        
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Card p = this;
        rowData = new Object[10];
        rowData[0] = p.pcucode;
        rowData[1] = p.cid;
        rowData[2] = p.pid;
        rowData[3] = p.instype;
        rowData[4] = p.insid;
        rowData[5] = p.start;
        rowData[6] = p.expir;
        rowData[7] = p.main;
        rowData[8] = p.sub;
        rowData[9] = p.update;
        return rowData;
    }
}
