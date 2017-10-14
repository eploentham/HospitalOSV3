package com.report18file.objectnh53;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Chronic;
import com.report18file.object.Rp18OI;
import com.report18file.utility.Report18FileData;
import java.io.BufferedReader;
import com.reportcenter.utility.IOStream;
import java.sql.PreparedStatement;
import java.sql.Connection;
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
public class ChronicNh53 extends Chronic implements Rp18OI{
    
    /** Creates a new instance of AncNh */
    public ChronicNh53() {
    }
        public String[] getWarningArray() {
        return new String[]{

 "รหัสสถานบริการ",
"เลขที่บัตรประชาชน",
"รหัส บุคคล",
"วันที่วินิจฉัยครั้งแรก",
"รหัสวินิฉัยโรคเรื้อรัง",
"วันที่จำหน่าย",
"ประเภทการจำหน่าย หรือสถานะของผู้ป่วยที่ทราบผลหลังสุด",
"วันที่ปรับปรุงข้อมูล"
        };
    }
    public String[] getHeaderArray() {
        return new String[]{
            
            "pcucode",
            "cid",
            "pid",
            "datedx",
            "chronic",
            "datedis",
            "typedis",
            "d_update"
        };
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_18file_nhso53/18file_nhso53_chronic.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    public Rp18OI initInstant() {
        return new ChronicNh53();
    }

    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        BufferedReader in;
        Chronic file = this;
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true,false	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cid	,	13	,	true,true	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	true,false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	datedx	,	8	,	true,true	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	chronic	,	6	,	true,false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	datedis	,	8	,	true,true	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	typedis	,	1	,	true,true,Report18FileData.VALID_19	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	update	,	14	,	true	)) { 	error[8	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
            sb.append("\n\tรายการที่ผิดพลาดคือ เลขบัตรประชาชน:"+ file.cid);
        }
         return ret;
    }

    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,6);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,14);
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Chronic p = this;
        rowData = new Object[8];
        rowData[0] = p.pcucode;
        rowData[1] = p.cid;
        rowData[2] = p.pid;
        rowData[3] = p.datedx;
        rowData[4] = p.chronic;
        rowData[5] = p.datedis;
        rowData[6] = p.typedis;
        rowData[7] = p.update;
        return rowData;
    }
}
