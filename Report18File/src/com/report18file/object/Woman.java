/*
 * Woman.java
 *
 * Created on 1 สิงหาคม 2548, 10:16 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report18file.object;

import com.linuxense.javadbf.DBFField;
import com.report18file.utility.Report18FileData;
import com.reportcenter.utility.IOStream;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Noom
 */
public class Woman implements Rp18OI{
    /** สถานพยาบาล*/
    public String  pcucode = "";
    /** เลข HCIS*/
    public String  pid = "";
    /**รหัสวิธีการคุมกำเนิดปัจจุบัน*/
    public String  fptype = "";
    /**สาเหตุที่ไม่คุมกำเนิด*/
    public String  nofp = "";
    /**จำนวนบุตรที่มีชีวิต*/
    public String  numson = "";
    /**วันเดือนปีที่ปรับปรุง*/
    public String  update = "";
    
    /** Creates a new instance of Woman */
    public Woman() {
    }
    public String[] getHeaderArray() {
        return new String[]{
            
            "pcucode",
            "pid",
            "fptype",
            "nofp",
            "numson",
            "d_update"
        };
    }
    
    public String[] getWarningArray() {
        return new String[]{
"สถานพยาบาล",
"เลข HCIS",
"วิธีการคุมกำเนิดปัจจุบัน",
"สาเหตุที่ไม่คุมกำเนิด",
"จำนวนบุตรที่มีชีวิต",
"วันเดือนปีที่ปรับปรุง"
        };
    }
    public String[] getValueArray() {
        return new String[]{
            pcucode	,
            pid	,
            fptype	,
            nofp	,
            numson	,
            update
        };
    }
    
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) {
        BufferedReader in;
        
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode 	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid 	,	13	,	true,false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	fptype	,	1	,	false,true,Report18FileData.VALID_07	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	nofp	,	1	,	false,true,Report18FileData.VALID_13	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	numson	,	2	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	update	,	8	,	false	)) { 	error[6	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
                sb.append("\n\tรายการที่ผิดพลาดคือ เลข HCIS:"+ pid);
        }
        return ret;
    }
    
    public boolean setValue(ResultSet rs) throws Exception {
        Woman p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.pid = Report18FileData.getRsString(rs,2);
        p.fptype = Report18FileData.getRsString(rs,3);
        p.nofp = Report18FileData.getRsString(rs,4);
        p.numson = Report18FileData.getRsString(rs,5);
        p.update = Report18FileData.getRsString(rs,6);
        return true;
    }
    
    public Rp18OI initInstant() {
        return new Woman();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        
        String sql = IOStream.readInputDefault("config/rp_18file/18file_woman.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    
    public String getFileName() {
        return "WOMEN";
    }
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,2);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,8);
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Woman p = this;
        rowData = new Object[6];
        rowData[0] = p.pcucode;
        rowData[1] = p.pid;
        rowData[2] = p.fptype;
        rowData[3] = p.nofp;
        rowData[4] = p.numson;
        rowData[5] = p.update;
        return rowData;
    }
}
