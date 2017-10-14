package com.report18file.objectnh53;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Appoint;
import com.report18file.object.Rp18OI;
import com.report18file.utility.Constant;
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
public class AppointNh53 extends Appoint implements Rp18OI{
    private String d_update;
    
    /** Creates a new instance of AncNh */
    public AppointNh53() {
    }
    public String[] getHeaderArray() {
        return new String[]{
            "pcucode",
            "pid",
            "seq",
            "date_serv",
            "apdate",
            "aptype",
            "apdiag",
            "d_update"
        };
    }


    public String[] getWarningArray() {
        return new String[]{
"รหัสสถานบริการ",
"รหัส บุคคล",
"ลำดับที่",
"วันที่มาใช้บริการ",
"วันที่นัด",
"ประเภทกิจกรรมที่นัด",
"รหัสโรคที่นัดมาตรวจ",
"วันที่ปรับปรุงข้อมูล"
        };
    }
    public String[] getValueArray() {
        return new String[]{
            pcucode	,
            pid	,
            seq	,
            date_serv	,
            apdate	,
            aptype	,
            apdiag,
            d_update
        };
    }

    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) {
        BufferedReader in;
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode 	,	5	,	true,false	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid 	,	13	,	true,false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	seq 	,	16	,	true,false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	date_serv	,	8	,	true	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	apdate	,	8	,	true	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	aptype	,	3	,	true,false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	apdiag 	,	6	,	false	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	d_update 	,	14	,	true	)) { 	error[8	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
                sb.append("\n\tรายการที่ผิดพลาดคือ เลข HCIS:"+ pid + " เลข VN:" + seq);
        }
        return ret;
    }

    public boolean setValue(ResultSet rs) throws Exception {
        AppointNh53 p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.pid = Report18FileData.getRsString(rs,2);
        p.seq = Report18FileData.getRsString(rs,3);
        //p.date_serv = Converter.changeDateFormate(Report18FileData.getRsString(rs,4));
        p.date_serv = Report18FileData.getRsString(rs,4);
        //p.apdate = Converter.changeDateFormate(Report18FileData.getRsString(rs,5));
        p.apdate = Report18FileData.getRsString(rs,5);
        p.aptype = Report18FileData.getRsString(rs,6);
        if(aptype.length()>3)
            aptype = aptype.substring(0,3);
        p.apdiag = Report18FileData.getRsString(rs,7);
        p.apdiag =Constant.removeDot(p.apdiag);
        p.d_update = Report18FileData.getRsString(rs,8);
        return true;
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_18file_nhso53/18file_nhso53_appoint.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    public Rp18OI initInstant() {
        return new AppointNh53();
    }

    public DBFField[] getDBFField() throws Exception {
        //PCUCODE  	5
        //PID 		13
        //SEQ  (PK)	16
        //DATE_SERV	8
        //APDATE	8
        //APTYPE (PK)	3
        //APDIAG 	6
        //D_UPDATE 	14
        //pu:25/12/2552
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,16);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,3);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,6);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,14);
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        AppointNh53 p = this;
        rowData = new Object[8];
        rowData[0] = p.pcucode;
        rowData[1] = p.pid;
        rowData[2] = p.seq;
        rowData[3] = p.date_serv;
        rowData[4] = p.apdate;
        rowData[5] = p.aptype;
        rowData[6] = p.apdiag;
        rowData[7] = p.d_update;
        return rowData;
    }
}
