/*
 * Aer.java
 *
 * Created on 8 กันยายน 2548, 12:01 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.objectnh53;

import com.linuxense.javadbf.DBFField;
import com.report12file.object.Rp12OI2;
import com.report12file.utility.Report12FileData;
import com.reportcenter.object.RpField;
import com.reportcenter.utility.IOStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tong(Padungrat)
 */
public class AdpNh53 extends Rp12OI2{
    
    public String hn;
    public String an;
    public String dateopd;
    public String type;
    public String code;
    public String qty;
    public String rate;
    public String seq;

    /** Creates a new instance of Aer */
    public AdpNh53() {
    }

    public String[] getValueArray() {
        return new String[]{
            hn,
            an,
            dateopd,
            type,
            code,
            qty,
            rate,
            seq
        };
    }

    public String getFileName() {
        return "ADP";
    }

    public Rp12OI2 initInstant() {
        return new AdpNh53();
    }

    public boolean setValue(ResultSet rs) throws Exception {
            hn	 = Report12FileData.getRsString(rs,	1	);
            an	 = Report12FileData.getRsString(rs,	2	);
            dateopd	 = Report12FileData.getRsString(rs,	3	);
            type	 = Report12FileData.getRsString(rs,	4	);
            code	 = Report12FileData.getRsString(rs,	5	);
            qty	 = Report12FileData.getRsString(rs,	6	);
            rate	 = Report12FileData.getRsString(rs,	7	);
            seq	 = Report12FileData.getRsString(rs,	8	);

            return true;
    }

    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file53/12file53_adp.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }

     
    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
        RpField.initData("	hn	","	เลขประจำตัวผู้ป่วย	",	9	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	an	","	เลข AN	",	9	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	dateopd	","	วันที่รับบริการ	",	8	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D	)
        ,RpField.initData("	type	","	ประเภทค่าใช้จ่าย	",	1	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	code	","	รหัสตามที่ สปสช.กำหนด	",	6	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	qty	","	หน่วย จำนวนครั้ง	",	3	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_N	)
        ,RpField.initData("	rate	","	ราคาต่อหน่วย	",	7	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_N	)
        ,RpField.initData("	seq	","	เลขการรับบริการ	",	15	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        };
        return theRPF;
    }

    
}
