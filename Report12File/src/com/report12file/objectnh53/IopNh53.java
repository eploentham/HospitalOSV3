/*
 * Iop.java
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
public class IopNh53 extends Rp12OI2{


    /** character width 9 */
    public String an;
    /** character width 4 */
    public String oper;
    /** character width 1 */
    public String optype;
    /** character width 6 */
    public String drop;
    /** date width 8 */
    public String datein;
    /** character width 4 */
    public String timein;
    /** date width 8 */
    public String dateout;
    /** character width 4 */
    public String timeout;

    public IopNh53() {
    }

    public Rp12OI2 initInstant() {
        return new IopNh53();
    }

    public String[] getValueArray() {
        return new String[]{
an	,
oper	,
optype	,
drop	,
datein	,
timein	,
dateout	,
timeout
};
    }
    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file53/12file53_iop.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }

    public boolean setValue(ResultSet rs) throws Exception {
        IopNh53 p = this;
p.	an	 = Report12FileData.getRsString(rs,	1	);
p.	oper	 = Report12FileData.getRsString(rs,	2	);
p.	optype	 = Report12FileData.getRsString(rs,	3	);
p.	drop	 = Report12FileData.getRsString(rs,	4	);
p.	datein	 = Report12FileData.getRsString(rs,	5	);
p.	timein	 = Report12FileData.getRsString(rs,	6	);
p.	dateout	 = Report12FileData.getRsString(rs,	7	);
p.	timeout	 = Report12FileData.getRsString(rs,	8	);
            return true;
    }


    ///////////////////////////////////////////////////////addcheckData


    public String getFileName() {
        return "IOP";
    }
 
    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
        RpField.initData("	an	","	เลข AN	",	9	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	oper	","	รหัสหัตถการ	",	7	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	optype	","	ชนิดของหัตถการ	",	1	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	dropid	","	เลขที่ใบประกอบวิชาชีพ	",	6	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	datein	","	วันเริ่มหัตถการ	",	8	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D	)
        ,RpField.initData("	timein	","	เวลาเริ่ม	",	4	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	dateout	","	วันสิ้นสุดหัตถการ	",	8	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D	)
        ,RpField.initData("	timeout	","	เวลาสิ้นสุด	",	4	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        };
        return theRPF;
    }

}
