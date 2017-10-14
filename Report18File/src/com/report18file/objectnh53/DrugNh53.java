package com.report18file.objectnh53;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Drug;
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
public class DrugNh53 extends Drug implements Rp18OI{
    private String dname;
    private String didstd;
    private String unit;
    private String unit_packing;
    private String d_update;

    static String[] warning = new String[]{
"รหัสสถานบริการ",
"รหัสบุคคล",
"ลำดับที่",
"วันที่",
"รหัสยา",
"จำนวน",
"ราคาขาย",
"ราคาทุน",
"ชื่อยาเก่า",
"รหัสยา 24 หลัก",
"หน่วย",
"ขนาดต่อหน่วยนับ",
"วันที่ปรับปรุงข้อมูล"
        };
    static String[] header = new String[]{
            "pcucode",
            "pid",
            "seq",
            "date_serv",
            "did",
            "amount",
            "drugpric",
            "drugcost",
            "dname",
            "didstd",
            "unit",
            "unit_packing",
            "d_update"
        };
    /** Creates a new instance of AncNh */
    public DrugNh53() {
    }

    public String[] getHeaderArray() {
        return header;
    }

    public String[] getWarningArray() {
        return warning;
    }
    public String[] getValueArray() {
        return new String[]{pcucode	,
        pid	,
        seq	,
        date_serv	,
        did	,
        amount	,
        drugpric	,
        drugcost,
        dname,
        didstd,
        unit,
        unit_packing,
        d_update	};
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        
        String sql = IOStream.readInputDefault("config/rp_18file_nhso53/18file_nhso53_drug.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }

    public Rp18OI initInstant() {
        return new DrugNh53();
    }

    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        BufferedReader in;
        Drug file = this;
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true,false	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	true,false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	seq	,	16	,	true,false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	date_serv	,	8	,	true	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	did	,	30	,	true,false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	amount	,	12	,	false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	drugpric	,	11	,	false	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	drugcost	,	11	,	false	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	dname	,	255	,	false	)) { 	error[9	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	didstd	,	24	,	false	)) { 	error[10	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	unit	,	20	,	false	)) { 	error[11	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	unit_packing	,	20	,	false	)) { 	error[12	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	d_update	,	14	,	true	)) { 	error[13	]++;    ret=false;}


        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
            sb.append("\n\tรายการที่ผิดพลาดคือ เลข "+warning[2]+":"+ file.seq + " "+warning[3]+":"+ file.date_serv);
        }
         return ret;
    }

    public boolean setValue(ResultSet rs) throws Exception {
        DrugNh53 p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.pid = Report18FileData.getRsString(rs,2);
        p.seq = Report18FileData.getRsString(rs,3);
        p.date_serv = Report18FileData.getRsString(rs,4);
        p.did = Report18FileData.getRsString(rs,5);
        p.amount = Report18FileData.getRsString(rs,6);
        p.drugpric = Report18FileData.getRsString(rs,7);
        p.drugcost = Report18FileData.getRsString(rs,8);
        p.dname = Report18FileData.getRsString(rs,9);
        p.didstd = Report18FileData.getRsString(rs,10);
        p.unit = Report18FileData.getRsString(rs,11);
        p.unit_packing = Report18FileData.getRsString(rs,12);
        p.d_update = Report18FileData.getRsString(rs,13);
        return true;
    }
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,16);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,30);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,12,0);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,11,0);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,11,0);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,255);
        Report18FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,24);
        Report18FileData.initDBFField(10,header,fields,DBFField.FIELD_TYPE_C,20);
        Report18FileData.initDBFField(11,header,fields,DBFField.FIELD_TYPE_C,20);
        Report18FileData.initDBFField(12,header,fields,DBFField.FIELD_TYPE_C,14);
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        DrugNh53 p = this;
        rowData = new Object[13];
        rowData[0] = p.pcucode;
        rowData[1] = p.pid;
        rowData[2] = p.seq;
        rowData[3] = p.date_serv;
        rowData[4] = p.did;
        rowData[5] = (p.amount);
        rowData[6] = (p.drugpric);
        rowData[7] = (p.drugcost);
        rowData[8] = p.dname;
        rowData[9] = p.didstd;
        rowData[10] = p.unit;
        rowData[11] = p.unit_packing;
        rowData[12] = p.d_update;
        return rowData;
    }
}
