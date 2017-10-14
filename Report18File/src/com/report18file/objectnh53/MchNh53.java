package com.report18file.objectnh53;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Mch;
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
public class MchNh53 extends Mch implements Rp18OI{
    
    /** Creates a new instance of AncNh */
    public MchNh53() {
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {

        String sql = IOStream.readInputDefault("config/rp_18file_nhso53/18file_nhso53_mch.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    public String[] getWarningArray() {
        return new String[]{
"รหัสสถานบริการ",
"รหัสบุคคล",
"ครรภ์ที่",
"วันแรกของการมีประจำเดือนครั้งท้าย",
"วันที่กำหนดคลอด",
"ผลการตรวจ  VDRL_RS",
"ผลการตรวจ  HB_RS",
"ผลการตรวจ  HIV_RS",
"วันที่ตรวจ HCT.",
"ผลการตรวจ  HCT",
"ผลการตรวจ THALASSAEMIA",
"ตรวจสุขภาพฟันและแนะนำ(หรือไม่)",
"ฟันผุ(จำนวน)",
"หินน้ำลาย (มีหรือไม่)",
"เหงือกอักเสบ(มีหรือไม่)",
"วันคลอด / วันสิ้นสุดการตั้งครรภ์",
"ผลสิ้นสุดการตั้งครรภ์",
"สถานที่คลอด",
"รหัสสถานพยาบาลที่คลอด",
"วิธีการคลอด / สิ้นสุดการตั้งครรภ์",
"ประเภทของผู้ทำคลอด",
"จำนวนเกิดมีชีพ",
"จำนวนตายคลอด",
"วันที่ดูแลแม่ครั้งที่ 1",
"วันที่ดูแลแม่ครั้งที่ 2",
"วันที่ดูแลแม่ครั้งที่ 3",
"ผลการตรวจมารดาหลังคลอด",
"วันที่ปรับปรุงข้อมูล"
        };
    }
    public String[] getHeaderArray() {
        return new String[]{
            "pcucode",
            "pid",
            "gravida",
            "lmp",
            "edc",
            "vdrl_rs",
            "hb_rs",
            "hiv_rs",
            "datehct",
            "hct_rs",
            "thalass",
            "dental",
            "tcaries",
            "tartar",
            "guminf",
            "bdate",
            "bresult",
            "bplace",
            "bhosp",
            "btype",
            "bdoctor",
            "lborn",
            "sborn",
            "ppcare1",
            "ppcare2",
            "ppcare3",
            "ppres",
            "d_update"
                    
        };
    }
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,2,0);
        Report18FileData.initDBFField(10,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(11,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(12,header,fields,DBFField.FIELD_TYPE_C,2,0);
        Report18FileData.initDBFField(13,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(14,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(15,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(16,header,fields,DBFField.FIELD_TYPE_C,6);
        Report18FileData.initDBFField(17,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(18,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(19,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(20,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(21,header,fields,DBFField.FIELD_TYPE_C,1,0);
        Report18FileData.initDBFField(22,header,fields,DBFField.FIELD_TYPE_C,1,0);
        Report18FileData.initDBFField(23,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(24,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(25,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(26,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(27,header,fields,DBFField.FIELD_TYPE_C,14);
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Mch p = this;
        rowData = new Object[28];
        rowData[0] = p.pcucode;
        rowData[1] = p.pid;
        rowData[2] = p.gravida;
        rowData[3] = p.lmp;
        rowData[4] = p.edc;
        rowData[5] = p.vdrl_rs;
        rowData[6] = p.hb_rs;
        rowData[7] = p.hiv_rs;
        rowData[8] = p.datehct;
        rowData[9] = (p.hct_rs);
        rowData[10] = p.thalass;
        rowData[11] = p.dental;
        rowData[12] = (p.tcaries);
        rowData[13] = p.tartar;
        rowData[14] = p.guminf;
        rowData[15]  = p.bdate;
        rowData[16]  = p.bresult;
        rowData[17]  = p.bplace;
        rowData[18]  = p.bhosp;
        rowData[19]  = p.btype;
        rowData[20]  = p.bdoctor;
        rowData[21] = (p.lborn);
        rowData[22] = (p.sborn);
        rowData[23]  = p.ppcare1;
        rowData[24]  = p.ppcare2;
        rowData[25]  = p.ppcare3;
        rowData[26]  = p.ppres;
        rowData[27]  = p.update;
        return rowData;
    }
    public Rp18OI initInstant() {
        MchNh53 mmm = new MchNh53();
        mmm.setConnectionInf(this.theConnectionInf);
        return mmm;
    }

    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        BufferedReader in;
        Mch file = this;
        boolean ret = true;
        error[0]++;
         if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true,false	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	true,false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	gravida	,	2	,	true,false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	lmp	,	8	,	false,true	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	edc	,	8	,	false,true	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	vdrl_rs	,	1	,	false,true,Report18FileData.VALID_12_89	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	hb_rs	,	1	,	false,true,Report18FileData.VALID_12_89	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	hiv_rs	,	1	,	false,true,Report18FileData.VALID_12_89	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	datehct	,	8	,	false,true	)) { 	error[9	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	hct_rs	,	2	,	false	)) { 	error[10	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	thalass	,	1	,	false,true,Report18FileData.VALID_12_89	)) { 	error[11	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	dental	,	1	,	false,true,Report18FileData.VALID_01	)) { 	error[12	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	tcaries	,	2	,	false	)) { 	error[13	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	tartar	,	1	,	false,true,Report18FileData.VALID_01_8	)) { 	error[14	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	guminf	,	1	,	false,true,Report18FileData.VALID_01_8	)) { 	error[15	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bdate	,	8	,	false,true	)) { 	error[16	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bresult	,	6	,	false	)) { 	error[17	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bplace	,	1	,	false,true,Report18FileData.VALID_15	)) { 	error[18	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bhosp	,	5	,	false	)) { 	error[19	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	btype	,	1	,	false,true,Report18FileData.VALID_16	)) { 	error[20	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	bdoctor	,	1	,	false,true,Report18FileData.VALID_15	)) { 	error[21	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	lborn	,	1	,	true	)) { 	error[22	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	sborn	,	1	,	true	)) { 	error[23	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ppcare1	,	8	,	false,true	)) { 	error[24	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ppcare2	,	8	,	false,true	)) { 	error[25	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ppcare3	,	8	,	false,true	)) { 	error[26	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ppres	,	1	,	true,true,Report18FileData.VALID_12	)) { 	error[27	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	update	,	14	,	true	)) { 	error[28	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
            sb.append("\n\tรายการที่ผิดพลาดคือ เลข HCIS:"+ file.pid);
        }
         return ret;
    }
    
}
