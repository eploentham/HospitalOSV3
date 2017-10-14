/*
 * Nutrition.java
 *
 * Created on 28 กรกฎาคม 2548, 10:39 น.
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
public class Nutrition  implements Rp18OI{
    public String  pcucode = "" ;
    public String  pid = "";
    public String  seq ="" ;
    public String  date_serv ="" ;
    public String  agemonth = "";
    public String  weight = "" ;
    public String  height = "";
    public String  nlevel = "";
    public String date_weight = "";
    
    /** Creates a new instance of Nutrition */
    public Nutrition() {
    }
    
    public String[] getHeaderArray() {
        return new String[]{
            
            "pcucode",
            "pid",
            "seq",
            "date_serv",
            "agemonth",
            "weight",
            "height",
            "nlevel"
        };
    }
    
    public String[] getWarningArray() {
        return new String[]{
"สถานพยาบาล",
"เลข HCIS",
"เลข VN",
"วันที่",
"อายุขณะชั่งน้ำหนัก(เดือน)",
"น้ำหนัก (กรัม)",
"ส่วนสูง (ซม.)",
"ระดับโภชนาการ"
        };
        
    }
    public String[] getValueArray() {
        return new String[]{
            pcucode	,
            pid	,
            seq	,
            date_serv	,
            agemonth	,
            weight	,
            height	,
            nlevel
        };
    }
    
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) {
        BufferedReader in;
        
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode 	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid  	,	13	,	true,false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	seq 	,	8	,	true	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	date_serv 	,	8	,	false	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	agemonth	,	5	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	weight	,	6	,	false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	height	,	6	,	false	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	nlevel	,	1	,	false	)) { 	error[8	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
                sb.append("\n\tรายการที่ผิดพลาดคือ เลข HCIS:"+ pid + " เลข VN:" + seq);
        }
        return ret;
    }
    
    public boolean setValue(ResultSet rs) throws Exception {
        Nutrition p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.pid = Report18FileData.getRsString(rs,2);
        p.seq = Report18FileData.getRsString(rs,3);
        while(p.seq.length()>8) p.seq = p.seq.substring(1);
        p.date_serv = Report18FileData.getRsString(rs,4);
        p.agemonth = Report18FileData.getRsString(rs,5);
        p.weight = Report18FileData.getRsString(rs,6);
        p.height = Report18FileData.getRsString(rs,7);
        p.nlevel = Report18FileData.getRsString(rs,8);
        return true;
    }
    
    public Rp18OI initInstant() {
        return new Nutrition();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        
        String sql = IOStream.readInputDefault("config/rp_18file/18file_nutrition.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    
    public String getFileName() {
        return "NUTRI";
    }
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,6);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,6);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,1);
        
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Nutrition p = this;
        rowData = new Object[8];
        rowData[0] = p.pcucode;
        rowData[1] = p.pid;
        rowData[2] = p.seq;
        rowData[3] = p.date_serv;
        rowData[4] = p.agemonth;
        rowData[5] = p.weight;
        rowData[6] = p.height;
        rowData[7] = p.nlevel;
        return rowData;
    }
}
