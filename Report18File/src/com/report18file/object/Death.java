/*
 * Death.java
 *
 * Created on 1 สิงหาคม 2548, 10:00 น.
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
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Noom
 */
public class Death  implements Rp18OI{
    /**รหัสสถานบริการ*/
    public String  pcucode = "";
    /**เลขที่บัตรประชาชน*/
    public String  cid = "";
    /** รหัส บุคคล*/
    public String  pid = "";
    /**วันที่ตาย*/
    public String  ddeath = "";
    /**โรคที่เป็นสาเหตุการตาย_a*/
    public String  cdeath_a = "";
    /** โรคที่เป็นสาเหตุการตาย_b*/
    public String  cdeath_b = "";
    /** โรคที่เป็นสาเหตุการตาย_c*/
    public String  cdeath_c = "";
    /**โรคที่เป็นสาเหตุการตาย_d*/
    public String  cdeath_d = "";
    /**โรคหรือภาวะอื่นที่เป็นเหตุหนุน*/
    public String  odisease = "";
    /**สาเหตุการตาย*/
    public String  cdeath = "";
    /**สถานที่ตาย*/
    public String  pdeath = "";
    
    private String d_update;
    
    /** Creates a new instance of Death */
    public Death() {
    }
    public String[] getHeaderArray() {
        return new String[]{
            
            "pcucode",
            "cid",
            "pid",
            "ddeath",
            "cdeath_a",
            "cdeath_b",
            "cdeath_c",
            "cdeath_d",
            "odisease",
            "cdeath",
            "pdeath",
            "d_update"
        };
    }
    
    public String[] getWarningArray() {
        return new String[]{
            
            "สถานพยาบาล",
            "เลขบัตรประชาชน",
            "เลข HCIS",
            "วันที่เสียชีวิต",
            "สาเหตุการตาย a",
            "สาเหตุการตาย b",
            "สาเหตุการตาย c",
            "สาเหตุการตาย d",
            "โรคที่เป็นเหตุหนุน",
            "สาเหตุการตาย",
            "สถานที่ตาย",
            "วันที่บันทึก"
        };
    }
    public String[] getValueArray() {
        return new String[]{
            pcucode	,
            cid	,
            pid	,
            ddeath	,
            cdeath_a	,
            cdeath_b	,
            cdeath_c	,
            cdeath_d	,
            odisease	,
            cdeath	,
            pdeath	,
            d_update	};
    }
    
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) {
        BufferedReader in;
        
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode ,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cid	,	13	,	false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	true,false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	ddeath	,	8	,	false	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cdeath_a	,	5	,	false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cdeath_b	,	5	,	false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cdeath_c	,	5	,	false	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cdeath_d	,	5	,	false	)) { 	error[8	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	odisease	,	5	,	false	)) { 	error[9	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cdeath	,	5	,	false	)) { 	error[10	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pdeath	,	1	,	false,true,Report18FileData.VALID_12	)) { 	error[11	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	d_update	,	8	,	false	)) { 	error[12	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
                sb.append("\n\tรายการที่ผิดพลาดคือ เลข HCIS:"+ pid);
        }
        return ret;
    }
    
    public boolean setValue(ResultSet rs) throws Exception {
        Death p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.cid = Report18FileData.getRsString(rs,2);
        p.pid = Report18FileData.getRsString(rs,3);
        p.ddeath = Report18FileData.getRsString(rs,4);
        p.cdeath_a = Report18FileData.getRsString(rs,5);
        p.cdeath_a =Constant.removeDot(p.cdeath_a);
        p.cdeath_b = Report18FileData.getRsString(rs,6);
        p.cdeath_b =Constant.removeDot(p.cdeath_b);
        p.cdeath_c = Report18FileData.getRsString(rs,7);
        p.cdeath_c =Constant.removeDot(p.cdeath_c);
        p.cdeath_d = Report18FileData.getRsString(rs,8);
        p.cdeath_d =Constant.removeDot(p.cdeath_d);
        p.odisease = Report18FileData.getRsString(rs,9);
        p.cdeath = Report18FileData.getRsString(rs,10);
        p.cdeath =Constant.removeDot(p.cdeath);
        p.pdeath = Report18FileData.getRsString(rs,11);
        p.d_update = Report18FileData.getRsString(rs,12);
        return true;
    }
    
    public Rp18OI initInstant() {
        return new Death();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_18file/18file_death.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    
    public String getFileName() {
        return "DEATH";
    }
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(8,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(9,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(10,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(11,header,fields,DBFField.FIELD_TYPE_C,8);
        
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Death p = this;
        rowData = new Object[12];
        rowData[0] = p.pcucode;
        rowData[1] = p.cid;
        rowData[2] = p.pid;
        rowData[3] = p.ddeath;
        rowData[4] = p.cdeath_a;
        rowData[5] = p.cdeath_b;
        rowData[6] = p.cdeath_c;
        rowData[7] = p.cdeath_d;
        rowData[8] = p.odisease;
        rowData[9] = p.cdeath;
        rowData[10] = p.pdeath;
        rowData[11] = p.d_update;
        return rowData;
    }
}
