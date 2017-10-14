package com.report18file.objectnh53;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Anc;
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
 * 
 *
 */

/**
 *
 * @author henbe
 */
public class AncNh53 extends Anc implements Rp18OI{
    private String d_update;
    
    /** Creates a new instance of AncNh */
    public AncNh53() {
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        //config/rp_18file_nhso50/18file_nhso50_anc.sql
        String sql = IOStream.readInputDefault("config/rp_18file_nhso53/18file_nhso53_anc.sql");

        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        pm.setString(3,startDate);
        pm.setString(4,endDate);
        pm.setString(5,startDate);
        pm.setString(6,endDate);
        return pm;
    }
    public Rp18OI initInstant() {
        return new AncNh53();
    }
/**
 *  ancno
1	ช่วงที่ 1  อายุครรภ์ 1-27 สัปดาห์ (ก่อน 12 สัปดาห์)
2	ช่วงที่ 2 อายุครรภ์ 28-31 สัปดาห์
3	ช่วงที่ 3 อายุครรภ์ 32-35 สัปดาห์
4	ชว่งที่ 4 อายุครรภ์36-39 สัปดาห์

 * @return
 */
    public String[] getHeaderArray() {
        return new String[]{
            "pcucode",
            "pid",
            "seq",
            "date_serv",
            "aplace",
            "gravida",
            "ancno",
            "ga",
            "ancres",
            "d_update"
        };
    }
    public String[] getWarningArray() {
        return new String[]{
"รหัสสถานบริการ",
"รหัสบุคคล",
"ลำดับที่",
"วันที่",
"รหัสสถานที่ตรวจ",
"ครรภ์ที่",
"ANC ช่วงที่",
"อายุครรภ์",
"ผลการตรวจ",
"วันที่ปรับปรุงข้อมูล"
        };
    }
    public String[] getValueArray() {
        return new String[]{
            pcucode,
            pid,
            seq,
            date_serv,
            aplace,
            gravida,
            ancno,
            ga,
            ancres,
            d_update
        };
    }
    public boolean checkDatadict(StringBuffer sb,int[] error)
    {
        // check ว่าเป็นการตรวจจากสถานพยาบาลอื่นหรือไม่หากใช้จะไม่นับมาในรายการทั้งหมด
        if(this.notice.startsWith("0000"))
            return false;

        BufferedReader in;
        Anc file = this;
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true,false	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	true,false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	seq	,	16	,	true,false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	date_serv	,	8	,	true	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	aplace	,	5	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	gravida	,	2	,	false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ancno	,	1	,	false,true,Report18FileData.VALID_14	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ga	,	3	,	false	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ancres	,	1	,	false,true,Report18FileData.VALID_12	)) { 	error[9	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	d_update	,	14	,	true	)) { 	error[10	]++;    ret=false;}

        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW && sb!=null)
                sb.append("\n\tรายการที่ผิดพลาดคือ เลข VN:"+ file.seq + " วันที่รับบริการ:"+ file.date_serv);
        }
         return ret;
    }
    public boolean setValue(ResultSet rs) throws Exception {
        AncNh53 p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.pid = Report18FileData.getRsString(rs,2);
        p.seq = Report18FileData.getRsString(rs,3);
        p.date_serv = Report18FileData.getRsString(rs,4);
        p.aplace = Report18FileData.getRsString(rs,5);
        p.gravida = Report18FileData.getRsString(rs,6);
        if(p.gravida.length()==1)
            p.gravida = "0" + p.gravida;
        p.ancno = Report18FileData.getRsString(rs,7);
        p.ga = Report18FileData.getRsString(rs,8);
        p.ancres = Report18FileData.getRsString(rs,9);
        p.d_update = Report18FileData.getRsString(rs,10);
        return true;
    }
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,16);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,3,0);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,14);
        
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        AncNh53 p = this;
        rowData = new Object[10];
        rowData[0] = p.pcucode;
        rowData[1] = p.pid;
        rowData[2] = p.seq;
        rowData[3] = p.date_serv;
        rowData[4] = p.aplace;
        rowData[5] = p.gravida;
        rowData[6] = p.ancno;
        rowData[7] = (p.ga);
        rowData[8] = p.ancres;
        rowData[9] = p.d_update;
        return rowData;
    }
    ///////////////////////////////////////////////////////addcheckData

}
