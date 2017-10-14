/*
 * Chronic.java
 *
 * Created on 1 สิงหาคม 2548, 9:59 น.
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
public class Chronic implements Rp18OI{
    /**รหัสสถานบริการ*/
    public String  pcucode = "";
    /**เลขที่บัตรประชาชน*/
    public String  cid = "";
    /**รหัส บุคคล*/
    public String  pid = "";
    /**วันที่วินิจฉัยครั้งแรก*/
    public String  datedx = "";
    /**รหัสวินิฉัยโรคเรื้อรัง*/
    public String  chronic = "";
    /**วันที่จำหน่าย*/
    public String  datedis = "";
    /**ประเภทการจำหน่าย หรือสถานะของผู้ป่วยที่ทราบผลหลังสุด*/
    public String  typedis = "";
    /**วันเดือนปีที่ปรับปรุง*/
    public String  update = "";
    
    /** Creates a new instance of Chronic */
    public Chronic() {
    }
    
    public String[] getHeaderArray() {
        return new String[]{
            
            "pcucode",
            "cid",
            "pid",
            "datedx",
            "chronic",
            "datedis",
            "typedis",
            "d_update"
        };
    }
    
    public String[] getWarningArray() {
        return new String[]{
            
            "สถานพยาบาล",
            "เลขบัตรประชาชน",
            "เลข HCIS",
            "วันที่วินิจฉัย",
            "รหัสโรคเรื้อรัง",
            "วันที่จำหน่าย",
            "ประเภทการจำหน่าย",
            "วันที่บันทึก"
        };
    }
    public String[] getValueArray() {
        return new String[]{
            pcucode	,
            cid	,
            pid	,
            datedx	,
            chronic	,
            datedis	,
            typedis	,
            update	};
    }
    
    ///////////////////////////////////////////////////////addcheckData
    public boolean checkDatadict(StringBuffer sb,int[] error) {
        BufferedReader in;
        
        boolean ret = true;
        error[0]++;
        if(!Report18FileData.checkDataDict(	pcucode	,	5	,	true	)) { 	error[1	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	cid	,	13	,	false	)) { 	error[2	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	pid	,	13	,	true,false	)) { 	error[3	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	datedx	,	8	,	false	)) { 	error[4	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	chronic	,	5	,	true,false	)) { 	error[5	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	datedis	,	8	,	false	)) { 	error[6	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	typedis	,	1	,	false,true,Report18FileData.VALID_19	)) { 	error[7	]++;    ret=false;}
        if(!Report18FileData.checkDataDict(	update	,	8	,	false	)) { 	error[8	]++;    ret=false;}
        
        if(!ret) {
            error[Report18FileData.MAX_COLUMN-1]++;
            if(error[Report18FileData.MAX_COLUMN-1]<Report18FileData.MAX_INCOMPLETE_ROW)
                sb.append("\n\t รายการที่ผิดพลาดคือ เลข HCIS:"+ pid);
        }
        return ret;
    }
    
    public boolean setValue(ResultSet rs) throws Exception {
        Chronic p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.cid = Report18FileData.getRsString(rs,2);
        p.pid = Report18FileData.getRsString(rs,3);
        p.datedx = Report18FileData.getRsString(rs,4);
        p.chronic = Report18FileData.getRsString(rs,5);
        p.chronic =Constant.removeDot(p.chronic);
        p.datedis = Report18FileData.getRsString(rs,6);
        p.typedis = Report18FileData.getRsString(rs,7);
        p.update = Report18FileData.getRsString(rs,8);
        return true;
    }
    
    public Rp18OI initInstant() {
        return new Chronic();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_18file/18file_chronic.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    
    public String getFileName() {
        return "CHRONIC";
    }
    public DBFField[] getDBFField() throws Exception {
        String[] header = getHeaderArray();
        DBFField[] fields = new DBFField[header.length];        
        Report18FileData.initDBFField(0,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(1,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(2,header,fields,DBFField.FIELD_TYPE_C,13);
        Report18FileData.initDBFField(3,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(4,header,fields,DBFField.FIELD_TYPE_C,5);
        Report18FileData.initDBFField(5,header,fields,DBFField.FIELD_TYPE_C,8);
        Report18FileData.initDBFField(6,header,fields,DBFField.FIELD_TYPE_C,1);
        Report18FileData.initDBFField(7,header,fields,DBFField.FIELD_TYPE_C,8);
        return fields;
    }
    public Object[] getDBFValue() {
        Object[] rowData;
        Chronic p = this;
        rowData = new Object[8];
        rowData[0] = p.pcucode;
        rowData[1] = p.cid;
        rowData[2] = p.pid;
        rowData[3] = p.datedx;
        rowData[4] = p.chronic;
        rowData[5] = p.datedis;
        rowData[6] = p.typedis;
        rowData[7] = p.update;
        return rowData;
    }
}
