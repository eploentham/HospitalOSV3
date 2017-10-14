/*
 * Cht.java
 *
 * Created on 8 กันยายน 2548, 12:03 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.objectnh53;
import com.linuxense.javadbf.DBFField;
import com.report12file.object.Rp12OI2;
import com.reportcenter.object.RpField;
import com.report12file.utility.Report12FileData;
import com.reportcenter.utility.IOStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tong(Padungrat)
 */
public class ChtNh53 extends Rp12OI2{
     /** character width 9 */
    public String hn;
    /** character width 9 */
    public String an;
    /** date width 8 */
    public String date;
    /** numeric width 20 */
    public String total;
    /** numeric width 20 */
    public String paid;
    /** character width 2 */
    public String pttype;

    public String pid;
    
    public String seq;

    public ChtNh53() {
    }
    public String[] getValueArray() {
        return new String[]{
            hn	,
            an	,
            date	,
            total	,
            paid	,
            pttype	,
            pid,
            seq
        };
    }
    
    
    public boolean setValue(ResultSet rs) throws Exception {
        ChtNh53 p = this;
        p.	hn	 = Report12FileData.getRsString(rs,	1	);
        p.	an	 = Report12FileData.getRsString(rs,	2	);
        p.	date	 = Report12FileData.getRsString(rs,	3	);
        p.	total	 = Report12FileData.getRsString(rs,	4	);
        p.	paid	 = Report12FileData.getRsString(rs,	5	);
        p.	pttype	 = Report12FileData.getRsString(rs,	6	);
        p.	pid	 = Report12FileData.getRsString(rs,	7	);
        p.	seq	 = Report12FileData.getRsString(rs,	8	);
         
        return true;
    }
    
    public Rp12OI2 initInstant() {
        return new ChtNh53();
    }
    
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file53/12file53_cht.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        pm.setString(3,startDate);
        pm.setString(4,endDate);
        return pm;
    }



    public String getFileName() {
        return "CHT";
    }

/**
 * //henbe comment 100253 kong ทำไมแก้กลับไปกลับมาสองรอบใครดูแล datadict กันแน่
 * @return
 */
    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
        RpField.initData("	hn	","	เลขประจำตัวผู้ป่วย	",	9	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	an	","	เลข AN	",	9	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	date	","	วันที่คิดค่ารักษา	",	8	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D	)
        ,RpField.initData("	total	","	จำนวนเงินรวม	",	7	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_N	)
        ,RpField.initData("	paid	","	จำนวนเงินที่ผู้ป่วยจ่าย	",	7	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_N	)
        ,RpField.initData("	pttype	","	ชนิดการชำระเงิน	",	2	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	person_id	","	เลขประจำตัวบัตรประชาชน	",	13	,	true	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	seq	","	เลขการรับบริการ	",	15	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        };
        return theRPF;
    }

}
