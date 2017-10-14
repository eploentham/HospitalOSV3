/*
 * Orf.java
 *
 * Created on 8 กันยายน 2548, 12:07 น.
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
public class OrfNh53 extends Rp12OI2{
    /** character width 9 */
    public String hn;
    /** datewidth 8 */
    public String dateopd;
    /** character width 4 */
    public String clinic;
    /** character width 5 */
    public String refer;
    /** character width 1 */
    public String refertype;
    public String pid;
        public String seq;

    public OrfNh53() {
    }
    public String[] getValueArray() {
        return new String[]{
hn	,
dateopd	,
clinic	,
refer	,
refertype,
seq
};
    }

    public boolean setValue(ResultSet rs) throws Exception {
        OrfNh53 p = this;
	p.	hn	 = Report12FileData.getRsString(rs,	1	);
	p.	dateopd	 = Report12FileData.getRsString(rs,	2	);
	p.	clinic	 = Report12FileData.getRsString(rs,	3	);
	p.	refer	 = Report12FileData.getRsString(rs,	4	);
	p.	refertype	 = Report12FileData.getRsString(rs,	5	);
	p.	seq	 = Report12FileData.getRsString(rs,	6	);
    return true;
    }
    
    public Rp12OI2 initInstant() {
        return new OrfNh53();
    }

    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file53/12file53_orf.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        return pm;
    }


    public String getFileName() {
        return "ORF";
    }


    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
        RpField.initData("	hn	","	เลขประจำตัวผู้ป่วย	",	9	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C)
        ,RpField.initData("	dateopd	","	วันที่มารับบริการ	",	8	,	true	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D)
        ,RpField.initData("	clinic	","	รหัสคลินิกที่ให้บริการ	",	4	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C)
        ,RpField.initData("	refer	","	สถานพยาบาลส่งต่อ	",	5	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C)
        ,RpField.initData("	refertype	","	ประเภทการส่งต่อ	",	1	,	true	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C)
        ,RpField.initData("	seq	","	เลขการรับบริการ	",	15	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C)
        };
        return theRPF;
    }

}
