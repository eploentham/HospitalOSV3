/*
 * Anc.java
 *
 * Created on 1 สิงหาคม 2548, 9:55 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.object;

import com.linuxense.javadbf.DBFField;
import com.printing.utility.IOStream;
import com.report18file.utility.Report18FileData; 
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Noom
 */
public class Anc implements Rp18OI{
    /**รหัสสถานบริการ*/
    public String pcucode = "";
    /**รหัส บุคคล*/
    public String pid = "";
    /**	ลำดับที่*/
    public String seq = "";
    /**วันที่*/
    public String date_serv = "";
    /**รหัสสถานที่ตรวจ*/
    public String aplace= "";
    /**ครรภ์ที่*/
    public String gravida= "";
    /**ANC ช่วงที่*/
    public String ancno= "";
    /**อายุครรภ์*/
    public String ga= "";
    /**ผลการตรวจ*/
    public String ancres= "";
    /**เอาไว้เช็คว่าเป็นการตรวจที่รพ หรือเปล่าโดยเช็คว่าค่าแรกขึ้นต้นด้วย 0000 หรือไม่*/
    public String notice= "";
    
    /** Creates a new instance of Anc */
    public Anc() {
    }
    
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
        };
    }
    
    /**
     *
     * rowData = new Object[9];
     * rowData[0] = p.pcucode;
     * rowData[1] = p.pid;
     * rowData[2] = p.seq;
     * rowData[3] = p.date_serv;
     *
     * rowData[4] = p.aplace;
     * rowData[5] = p.gravida;
     * rowData[6] = p.ancno;
     *
     * if(!("").equals(p.ga)){
     * rowData[7] = new Double(p.ga);
     * }else{
     * rowData[7] = null;
     * }
     *
     * rowData[8] = p.ancres;
     **/
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
            ancres
        };
    }
    
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) {
        // check ว่าเป็นการตรวจจากสถานพยาบาลอื่นหรือไม่หากใช้จะไม่นับมาในรายการทั้งหมด
        if(this.notice.startsWith("0000"))
            return false;
       
        BufferedReader in;
        Anc file = this;
        boolean ret = true;
        //total_record
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid  	,	13	,	true,false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	seq  	,	8	,	true	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	date_serv 	,	8	,	false	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	aplace	,	5	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	gravida	,	2	,	false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ancno	,	1	,	false,true,Report18FileData.VALID_14	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ga	,	3	,	false	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ancres	,	1	,	false,true,Report18FileData.VALID_12	)) { 	error[9	]++;    ret=false;}
        
        if(!ret) {
            //passed record
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
                sb.append("\n\tรายการที่ผิดพลาดคือ เลข HCIS:"+ file.pid + " วันที่รับบริการ:"+ file.date_serv);
        }
        return ret;
    }
    
    public boolean setValue(ResultSet rs) throws Exception {
        Anc p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.pid = Report18FileData.getRsString(rs,2);
        p.seq = Report18FileData.getRsString(rs,3);
        while(p.seq.length()>8) p.seq = p.seq.substring(1);
        p.date_serv = Report18FileData.getRsString(rs,4);
        p.aplace = Report18FileData.getRsString(rs,5);
        p.gravida = Report18FileData.getRsString(rs,6);
        if(p.gravida.length()==1)
            p.gravida = "0" + p.gravida;
        p.ancno = Report18FileData.getRsString(rs,7);
        p.ga = Report18FileData.getRsString(rs,8);
        p.ancres = Report18FileData.getRsString(rs,9);
        p.notice = Report18FileData.getRsString(rs,10);
        return true;
    }
    
    public Rp18OI initInstant() {
        return new Anc();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_18file/18file_anc.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    
    public String getFileName() {
        return "ANC";
    }
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,3);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,1);
        
        return fields;
    }
    
    public Object[] getDBFValue() {
        Object[] rowData;
        Anc p = this;
        rowData = new Object[9];
        rowData[0] = p.pcucode;
        rowData[1] = p.pid;
        rowData[2] = p.seq;
        rowData[3] = p.date_serv;
        rowData[4] = p.aplace;
        rowData[5] = p.gravida;
        rowData[6] = p.ancno;
        rowData[7] = p.ga;
        rowData[8] = p.ancres;
        return rowData;
    }

    public String[] getWarningArray() {
        return new String[]{
            "สถานพยาบาล",
            "เลข HCIS",
            "เลข VN",
            "วันที่รับบริการ",
            "aplace",
            "ลำดับครรภ์",
            "ancno",
            "ga",
            "ผลตรวจ",
        };
    }

}
