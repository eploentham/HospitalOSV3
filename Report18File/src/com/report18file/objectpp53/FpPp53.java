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
public class FpPp53 extends Rp18OI2{

    /**สถานพยาบาล*/
    public String  pcucode = "";
    /**เลข HCIS*/
    public String  pid = "";
    /** เลข VN*/
    public String  seq = "";
    /** วันที่*/
    public String  date_serv = "";
    /**รหัสวิธีการคุมกำเเนิด*/
    public String  fptype = "";
    /**ชนิดของยา,เวชภัณฑ์*/
    public String  did = "";
    /**จำนวนเวชภัณฑ์*/
    public String  amount = "";
    /**สถานที่รับบริการ*/
    public String  fpplace = "";
    private String d_update;
    /** Creates a new instance of AncNh */
    public FpPp53() {
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {

        String sql = IOStream.readInputDefault("config/rp_18file_pp53/18file_pp53_fp.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        pm.setString(3,startDate);
        pm.setString(4,endDate);
        pm.setString(5,startDate);
        pm.setString(6,endDate);
        return pm;
    }

    public Rp18OI2 initInstant() {
        return new FpPp53();
    }
    public boolean setValue(ResultSet rs) throws Exception {
        FpPp53 p = this;
        p.pcucode = Report18FileData.getRsString(rs,1);
        p.pid = Report18FileData.getRsString(rs,2);
        p.seq = Report18FileData.getRsString(rs,3);
        p.date_serv = Report18FileData.getRsString(rs,4);
        p.fptype = Report18FileData.getRsString(rs,5);
        p.did = Report18FileData.getRsString(rs,6);
        p.amount = Report18FileData.getRsString(rs,7);
        try{
            p.amount = String.valueOf(Double.parseDouble(p.amount));
        }catch(Exception e){
            p.amount = "0";
        }
        p.amount = Report18FileData.getBeforeDot(p.amount);
        p.fpplace = Report18FileData.getRsString(rs,8);
        p.d_update = Report18FileData.getRsString(rs,9);
        return true;
    }

    public String getFileName() {
        return "Fp";
    }
    public String[] getValueArray() {
        return new String[]{
            pcucode	,
            pid	,
            seq	,
            date_serv	,
            fptype	,
            did	,
            amount	,
            fpplace,
            d_update
        };
    }
//henbe comment 100253 kong ใครให้แป้งแก้แล้วไปแก้ใน datadict เอกสาร usecase แล้วหรือยัง
    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[9];
theRPF[0	]=RpField.initData("	pcucode	","	รหัสสถานบริการ	",	5	,	true	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	);
theRPF[1	]=RpField.initData("	cid	","	รหัสบัตรประชาชน	",	13	,	true	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	);
theRPF[2	]=RpField.initData("	seq	","	ลำดับที่	",	16	,	true	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	);
theRPF[3	]=RpField.initData("	date_serv	","	วันที่	",	8	,	true	,	true	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	);
theRPF[4	]=RpField.initData("	fptype	","	รหัสวิธีการคุมกำเเนิด	",	1	,	true	,	true	,	Report18FileData.VALID_17	,	DBFField.FIELD_TYPE_C	);
theRPF[5	]=RpField.initData("	did	","	ชนิดของยา,เวชภัณฑ์	",	30	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	);
theRPF[6	]=RpField.initData("	amount	","	จำนวนเวชภัณฑ์	",	3	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	);
theRPF[7	]=RpField.initData("	fpplace	","	สถานที่รับบริการ 	",	5	,	false	,	false	,	Report18FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	);
theRPF[8	]=RpField.initData("	d_update	","	วันที่ปรับปรุงข้อมูล	",	14	,	false	,	true	,	Report18FileData.VALID_N13	,	DBFField.FIELD_TYPE_C	);
return theRPF;
    }

    
}
