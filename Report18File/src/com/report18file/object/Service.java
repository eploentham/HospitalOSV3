/*
 * Service.java
 *
 * Created on 1 สิงหาคม 2548, 10:15 น.
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
public class Service  implements Rp18OI{
    /**รหัสสถานบริการ*/
    public String  pcucode = "";
    /**รหัส บุคคล*/
    public String  pid = "";
    /**ลำดับที่*/
    public String  seq = "";
    /** วันที่*/
    public String  date_serv = "";
    /**ชนิดผู้ป่วย*/
    public String  locate = "";
    /**ประเภทผู้รับบริการ*/
    public String  pttype = "";
    /**เวลามารับบริการ*/
    public String  intime = "";
    /**ค่าบริการทั้งหมด*/
    public String  price = "";
    /**สิทธิ*/
    public String  instype = "";
    /**เลขที่บัตรสิทธิ*/
    public String  insid = "";
    /**สถานบริการหลัก*/
    public String  main = "";
    /**จ่ายเงิน*/
    public String  pay = "";
    /**รับการส่งต่อ(หรือไม่)*/
    public String  referin = "";
    /**สถานบริการที่ส่งผู้ป่วยมา*/
    public String  refinhos = "";
    /**ส่งผู้ป่วยไปรักษาต่อ (หรือไม่)*/
    public String  referout = "";
    /** สถานพยาบาลที่ส่งผู้ป่วยไป*/
    public String  refouhos = "";
    /** Creates a new instance of Service */
    public Service() {
    }
    public String[] getHeaderArray() {
        return new String[]{
            
            "pcucode",
            "pid",
            "seq",
            "date_serv",
            "locate",
            "pttype",
            "intime",
            "price",
            "instype",
            "insid",
            "main",
            "pay",
            "referin",
            "refinhos",
            "referout",
            "refouhos"
        };
    }
    
    public String[] getWarningArray() {
        return new String[]{
            "สถานพยาบาล",
            "เลข HCIS",
            "เลข VN",
            "วันที่รับบริการ",
            "ในเขต/นอกเขต",
            "ผู้ป่วยเก่า/ใหม่",
            "ในเวลา/นอกเวลา",
            "ค่าบริการ",
            "สิทธิ",
            "เลขที่สิทธิ",
            "สถานพยาบาลหลัก",
            "เงินที่เรียกเก็บ",
            "รับการส่งต่อ",
            "สถานพยาบาลที่ส่งมา",
            "ส่งไปรักษาต่อ",
            "สถานพยาบาลที่ส่งไป"
        };
    }
    public String[] getValueArray() {
        return new String[]{
            pcucode	,
            pid	,
            seq	,
            date_serv	,
            locate	,
            pttype	,
            intime	,
            price	,
            instype	,
            insid	,
            main	,
            pay	,
            referin	,
            refinhos	,
            referout	,
            refouhos
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
        if(!Report18FileData.checkDataDict(	locate	,	1	,	false,true,Report18FileData.VALID_12	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pttype	,	1	,	false,true,Report18FileData.VALID_02	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	intime	,	1	,	false,true,Report18FileData.VALID_12	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	price	,	11	,	false	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	instype	,	2	,	false	)) { 	error[9	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	insid	,	18	,	false	)) { 	error[10	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	main	,	5	,	false	)) { 	error[11	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pay	,	11	,	false	)) { 	error[12	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	referin	,	1	,	false,true,Report18FileData.VALID_01	)) { 	error[13	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	refinhos	,	5	,	false	)) { 	error[14	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	referout	,	1	,	false,true,Report18FileData.VALID_01	)) { 	error[15	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	refouhos	,	5	,	false	)) { 	error[16	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
                sb.append("\n\tรายการที่ผิดพลาดคือ เลข HCIS:"+ pid + " เลข VN:" + seq);
        }
        return ret;
    }
    
    public boolean setValue(ResultSet rs) throws Exception {
        Service p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.pid = Report18FileData.getRsString(rs,2);
        p.seq = Report18FileData.getRsString(rs,3);
        while(p.seq.length()>8) p.seq = p.seq.substring(1);
        p.date_serv = Report18FileData.getRsString(rs,4);
        p.locate = Report18FileData.getRsString(rs,5);
        String patientID = Report18FileData.getRsString(rs,6);
//            this.getPTTTypeFromPatientID(patientID);
        p.intime =Report18FileData.getRsString(rs,7);
        p.intime = this.getTypeServiceTime(p.intime);
        p.price = Report18FileData.getRsString(rs,8);
        p.instype = Report18FileData.getRsString(rs,9);
        p.insid = Report18FileData.getRsString(rs,10);
        p.main = Report18FileData.getRsString(rs,11);
        p.pay = Report18FileData.getRsString(rs,12);
        
        p.referin = Report18FileData.getRsString(rs,13);
        p.refinhos = Report18FileData.getRsString(rs,14);
        p.referout = Report18FileData.getRsString(rs,15);
        p.refouhos = Report18FileData.getRsString(rs,16);
        p.pttype = Report18FileData.getRsString(rs,17);
        return true;
    }
    
    /*return ค่า 1 เมื่อค่า time(hh:mm) อยู่ในช่วงเวลาราชการ และจะ return ค่า 2 เมื่อค่า time(hh:mm) อยู่นอกช่วงเวลาราชการ*/
    public String getTypeServiceTime(String time){
        boolean isInStartTime = (time.compareTo("08:00") >= 0);
        boolean isInEndTime   = (time.compareTo("16:00") <= 0);
        if(isInStartTime && isInEndTime){
            return "1";
        }
        return "2";
    }
    
    public Rp18OI initInstant() {
        return new Service();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        
        String sql = IOStream.readInputDefault("config/rp_18file/18file_service.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    
    public String getFileName() {
        return "SERVICE";
    }
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,11);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,18);
        Report18FileData.initDBFField(10,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(11,header,fields,DBFField.FIELD_TYPE_C,11);
        Report18FileData.initDBFField(12,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(13,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(14,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(15,header,fields,DBFField.FIELD_TYPE_C,5);
        
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Service p = this;
        rowData = new Object[16];
        rowData[0] =  p.pcucode;
        rowData[1] = p.pid;
        rowData[2] = p.seq;
        rowData[3] = p.date_serv;
        rowData[4] = p.locate;
        rowData[5] = p.pttype;
        rowData[6] = p.intime;
        rowData[7] = p.price;
        rowData[8] = p.instype;
        rowData[9] = p.insid;
        rowData[10] = p.main;
        rowData[11] = p.pay;
        rowData[12] = p.referin;
        rowData[13] = p.refinhos;
        rowData[14] = p.referout;
        rowData[15] = p.refouhos;
        return rowData;
    }
}
