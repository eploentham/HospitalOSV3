
package com.report18file.objectnh53;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Serveil;
import com.report18file.object.Rp18OI;
import com.report18file.utility.Constant;
import com.report18file.utility.Report18FileData;
import java.io.BufferedReader;
import com.reportcenter.utility.IOStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class ServeilNh53 extends Serveil implements Rp18OI{
    private String d_update;
    
    /** Creates a new instance of AncNh */
    public ServeilNh53() {
    }

    public String[] getHeaderArray() {
        return new String[]{
            "pcucode",
            "cid",
            "pid",
            "seq",
            "date_serv",
            "diagcode",
            "code506",
            "illdate",
            "illhouse",
            "illvill",
            "illtamb",
            "illampu",
            "illchan",
            "ptstat",
            "date_death",
            "complica",
            "organism",
            "d_update"
        };
    }

    public String[] getWarningArray() {
        return new String[]{"สถานพยาบาล",
"รหัสสถานบริการ",
"เลขที่บัตรประชาชน",
"รหัส บุคคล",
"ลำดับที่",
"วันที่",
"รหัสการวินิจฉัย",
"รหัส 506",
"วันที่เริ่มป่วย",
"บ้านเลขที่(ขณะป่วย)",
"รหัสหมู่บ้าน(ขณะป่วย)",
"รหัสตำบล(ขณะป่วย)",
"รหัสอำเภอ(ขณะป่วย)",
"รหัสจังหวัด(ขณะป่วย)",
"สภาพผู้ป่วย",
"วันที่ตาย",
"สาเหตุการป่วย",
"ชนิดของเชื้อโรค",
"วันที่ปรับปรุงข้อมูล"
        };
    }
    public String[] getValueArray() {
        return new String[]{
            pcucode	,
            cid	,
            pid	,
            seq	,
            date_serv	,
            diagcode	,
            code506	,
            illdate	,
            illhouse	,
            illvill	,
            illtamb	,
            illampu	,
            illchan	,
            ptstat	,
            date_death	,
            complica	,
            organism	,
            d_update
        };
    }


    public boolean setValue(ResultSet rs) throws Exception {
        ServeilNh53 p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.cid = Report18FileData.getRsString(rs,2);
        p.pid = Report18FileData.getRsString(rs,3);
        p.seq = Report18FileData.getRsString(rs,4);
        p.date_serv = Report18FileData.getRsString(rs,5);
        p.diagcode = Report18FileData.getRsString(rs,6);
        p.diagcode = Constant.removeDot(p.diagcode);
        p.code506 = Report18FileData.getRsString(rs,7);
        while(p.code506.length()>2) p.code506 = p.code506.substring(0,p.code506.length()-1);
        p.illdate = Report18FileData.getRsString(rs,8);
        p.illhouse = Report18FileData.getRsString(rs,9);
        p.illvill = Report18FileData.getRsString(rs,10);
        p.illtamb = Report18FileData.getRsString(rs,11);
        p.illampu = Report18FileData.getRsString(rs,12);
        p.illchan = Report18FileData.getRsString(rs,13);
        p.ptstat = Report18FileData.getRsString(rs,14);
        p.date_death = Report18FileData.getRsString(rs,15);
        p.complica = Report18FileData.getRsString(rs,16);
        p.organism = Report18FileData.getRsString(rs,17);
        p.d_update = Report18FileData.getRsString(rs,18);

/**
 *ใน Dtadict ของ สนย. ต้องการข้อมูลรหัส
1 = หาย
2 = ตาย
3 = ยังรักษาอยู่
4 = ไม่ทราบ
แต่ใน HospitalOS มีข้อมูลดังนี้
1 = หาย
2 = ตาย
3 = ยังรักษาอยู่
4 = ไม่ทราบ
5 = รอจำหน่าย/เฝ้าระวัง
6 = ขาดการรักษาไม่มาติดต่ออีก (ทราบว่าขาดการรักษา)
7 = ครบการรักษา
8 = โรคอยู่ในภาวะสงบ(inactive)ไม่มีความจำเป็นต้องรักษา
9 = ปฏิเสธการรักษา
 */
        if(p.ptstat.equals("5")) p.ptstat = "3";
        else if(p.ptstat.equals("6")) p.ptstat = "3";
        else if(p.ptstat.equals("7")) p.ptstat = "4";
        else if(p.ptstat.equals("8")) p.ptstat = "3";
        else if(p.ptstat.equals("9")) p.ptstat = "4";
        else  p.ptstat = "4";
        return true;
    }

    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {

        String sql = IOStream.readInputDefault("config/rp_18file_nhso53/18file_nhso53_surveil.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }

    public Rp18OI initInstant() {
        return new ServeilNh53();
    }

    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) 
    {
        BufferedReader in;
        Serveil file = this;
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true,false	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cid	,	13	,	false,true	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	true,false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	seq	,	16	,	true,false	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	date_serv	,	8	,	true	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	diagcode	,	6	,	true,false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	code506	,	2	,	false	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	illdate	,	8	,	false,true	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	illhouse	,	75	,	false	)) { 	error[9	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	illvill	,	2	,	false	)) { 	error[10	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	illtamb	,	2	,	false	)) { 	error[11	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	illampu	,	2	,	false	)) { 	error[12	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	illchan	,	2	,	false	)) { 	error[13	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ptstat	,	1	,	false,true,Report18FileData.VALID_14	)) { 	error[14	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	date_death	,	8	,	false,true	)) { 	error[15	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	complica	,	3	,	false	)) { 	error[16	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	organism	,	3	,	false	)) { 	error[17	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	d_update	,	14	,	true	)) { 	error[18	]++;    ret=false;}

        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
            sb.append("\n\tรายการที่ผิดพลาดคือ เลข VN:"+ file.seq + " วันที่รับบริการ:"+ file.date_serv);                                
        }
         return ret;
    }
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,16);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,6);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,75);
        Report18FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(10,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(11,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(12,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(13,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(14,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(15,header,fields,DBFField.FIELD_TYPE_C,3);
        Report18FileData.initDBFField(16,header,fields,DBFField.FIELD_TYPE_C,3);
        Report18FileData.initDBFField(17,header,fields,DBFField.FIELD_TYPE_C,14);
        return fields;
    }

    public Object[] getDBFValue() {
        Object[] rowData;
        ServeilNh53 p = this;
        rowData = new Object[18];
        rowData[0] = p.pcucode;
        rowData[1] = p.cid;
        rowData[2] = p.pid;
        rowData[3] = p.seq;
        rowData[4] = p.date_serv;
        rowData[5] = p.diagcode;
        rowData[6] = p.code506;
        rowData[7] = p.illdate;
        rowData[8] = p.illhouse;
        rowData[9] = p.illvill;
        rowData[10] = p.illtamb;
        rowData[11] = p.illampu;
        rowData[12] = p.illchan;
        rowData[13] = p.ptstat;
        rowData[14] = p.date_death;
        rowData[15] = p.complica;
        rowData[16] = p.organism;
        rowData[17] = p.d_update;
        return rowData;
    }
}
