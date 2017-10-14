/*
 * Appoint.java
 *
 * Created on 1 สิงหาคม 2548, 9:56 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.object;

import com.linuxense.javadbf.DBFField;
import com.report18file.utility.Constant;
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
public class Appoint  implements Rp18OI{
    /**รหัสสถานบริการ*/
    public String pcucode = "";
    /**รหัส บุคคล*/
    public String pid = "";
    /**ลำดับที่*/
    public String seq  = "";
    /**วันที่มาใช้บริการ*/
    public String date_serv = "";
    /**วันที่นัด*/
    public String apdate = "";
    /**ประเภทกิจกรรมที่นัด*/
    public String aptype = "";
    /**รหัสโรคที่นัดมาตรวจ*/
    public String apdiag = "";
    
    /** Creates a new instance of Appoint */
    public Appoint() {
    }
    public String[] getHeaderArray() {
        return new String[]{
            
            
            "pcucode",
            "pid",
            "seq",
            "date_serv",
            "apdate",
            "aptype",
            "apdiag"
        };
    }
    
    public String[] getWarningArray() {
        return new String[]{
            
            
            "สถานพยาบาล",
            "เลข HCIS",
            "เลข VN",
            "วันที่รับบริการ",
            "วันที่นัด",
            "กิจกรรมที่นัด",
            "รหัสโรคที่นัดมาตรวจ"
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
            apdiag
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
        if(!Report18FileData.checkDataDict(	apdate	,	8	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	aptype	,	3	,	true	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	apdiag 	,	5	,	true,false	)) { 	error[7	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
                sb.append("\n\tรายการที่ผิดพลาดคือ เลข HCIS:"+ pid + " เลข VN:" + seq);
        }
        return ret;
    }
    
    public boolean setValue(ResultSet rs) throws Exception {
        Appoint p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.pid = Report18FileData.getRsString(rs,2);
        p.seq = Report18FileData.getRsString(rs,3);
        while(p.seq.length()>8) p.seq = p.seq.substring(1);
        //p.date_serv = Converter.changeDateFormate(Report18FileData.getRsString(rs,4));
        p.date_serv = Report18FileData.getRsString(rs,4);
        //p.apdate = Converter.changeDateFormate(Report18FileData.getRsString(rs,5));
        p.apdate = Report18FileData.getRsString(rs,5);
        p.aptype = Report18FileData.getRsString(rs,6);
        if(aptype.length()>3)
            aptype = aptype.substring(0,3);
        p.apdiag = Report18FileData.getRsString(rs,7);
        p.apdiag =Constant.removeDot(p.apdiag);
        return true;
    }
    
    public Rp18OI initInstant() {
        return new Appoint();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_18file/18file_appoint.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    
    public String getFileName() {
        return "APPOINT";
    }
    
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,3);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,5);
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Appoint p = this;
        rowData = new Object[7];
        rowData[0] = p.pcucode;
        rowData[1] = p.pid;
        rowData[2] = p.seq;
        rowData[3] = p.date_serv;
        rowData[4] = p.apdate;
        rowData[5] = p.aptype;
        rowData[6] = p.apdiag;
        return rowData;
    }
}
