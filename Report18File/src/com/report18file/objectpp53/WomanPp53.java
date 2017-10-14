package com.report18file.objectpp53;

import com.linuxense.javadbf.DBFField;
import com.report18file.object.Rp18OI2;
import com.report18file.utility.Report18FileData;
import com.reportcenter.object.RpField;

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
public class WomanPp53 extends Rp18OI2{

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
    String person_name;
    /** Creates a new instance of AncNh */
    public WomanPp53() {
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        
        String sql = IOStream.readInputDefault("config/rp_18file_pp53/18file_pp53_woman.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }
    public boolean setValue(ResultSet rs) throws Exception {
        WomanPp53 p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.pid = Report18FileData.getRsString(rs,2);
        p.fptype = Report18FileData.getRsString(rs,3);
        p.nofp = Report18FileData.getRsString(rs,4);
        p.numson = Report18FileData.getRsString(rs,5);
        p.update = Report18FileData.getRsString(rs,6);
        p.person_name = Report18FileData.getRsString(rs,7);
        return true;
    }
    public Rp18OI2 initInstant() {
        return new WomanPp53();
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
    public String getFileName() {
        return "WOMEN";
    }
    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
 RpField.initData("	pcucode	","	รหัสสถานบริการ	",	5	,	true	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	cid	","	รหัสบัตรประชาชน	",	13	,	true	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	fptype	","	รหัสวิธีการคุมกำเนิดปัจจุบัน	",	1	,	true	,	true	,	Report18FileData.VALID_07	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	nofp	","	สาเหตุที่ไม่คุมกำเนิด	",	1	,	true	,	true	,	Report18FileData.VALID_13	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	numson	","	จำนวนบุตรที่มีชีวิต	",	2	,	true	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
,RpField.initData("	d_update	","	วันที่ปรับปรุงข้อมูล	",	14	,	true	,	true	,	Report18FileData.VALID_N13	,	DBFField.FIELD_TYPE_C	)
        };
        return theRPF;
    }
    
}
