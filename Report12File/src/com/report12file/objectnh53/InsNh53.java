/*
 * Ins.java
 *
 * Created on 8 กันยายน 2548, 12:04 น.
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
public class InsNh53 extends Rp12OI2{

    /** character width 9 */
    public String hn;
    /** character width 2 */
    public String inscl;
    /** character width 2 */
    public String subtype;
    /** character width 16 */
    public String cid;
    /** date width 8 */
    public String datein;
    /** date width 8 */
    public String dateexp;
    /** character width 5 */
    public String hospmain;
    /** character width 5 */
    public String hospsub;
    
    public static String EMPTY = "";
    public InsNh53() {
    }

    public Rp12OI2 initInstant() {
        return new InsNh53();
    }

    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file53/12file53_ins.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        pm.setString(3,startDate);
        pm.setString(4,endDate);
        return pm;
    }
    public String[] getValueArray() {
        return new String[]{
hn	,
inscl	,
subtype	,
cid	,
datein	,
dateexp	,
hospmain	,
hospsub
};
    }
    public boolean setValue(ResultSet rs) throws Exception{
        InsNh53 p = this;
      	p.	hn	 = Report12FileData.getRsString(rs,	1	);
        p.	inscl	 = Report12FileData.getRsString(rs,	2	);
        p.	subtype	 = Report12FileData.getRsString(rs,	3	);
        p.	cid	 = Report12FileData.getRsString(rs,	4	);
        p.	datein	 = Report12FileData.getRsString(rs,	5	);
        p.	dateexp	 = Report12FileData.getRsString(rs,	6	);
        p.	hospmain	 = Report12FileData.getRsString(rs,	7	);
        p.	hospsub	 = Report12FileData.getRsString(rs,	8	);
        return true;
    }
    
    public String getFileName() {
        return "INS";
    }


    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
        RpField.initData("	hn	","	เลขประจำตัวผู้ป่วย	",	9	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	inscl	","	สิทธิการรักษา	",	2	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	subtype	","	ระดับสิทธิของหลักประกัน	",	2	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	cid	","	เลขสิทธิบัตรผู้ป่วย	",	16	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	datein	","	วันที่มีสิทธิ	",	8	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D	)
        ,RpField.initData("	dateexp	","	วันที่หมดสิทธิ	",	8	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D	)
        ,RpField.initData("	hospmain	","	รหัสสถานพยาบาลหลัก	",	5	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	hospsub	","	รหัสสถานพยาบาลรอง	",	5	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        };
        return theRPF;
    }

}
