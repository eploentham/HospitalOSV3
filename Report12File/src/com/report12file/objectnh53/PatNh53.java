/*
 * Pat.java
 *
 * Created on 8 กันยายน 2548, 12:07 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.report12file.objectnh53;
import com.linuxense.javadbf.DBFField;
import com.report12file.utility.Report12FileData;
import com.report12file.object.Rp12OI2;
import com.reportcenter.object.RpField;
import com.reportcenter.utility.IOStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tong(Padungrat)
 */
public class PatNh53 extends Rp12OI2{
    
    /** character width 5 */
    public String hcode;
    /** character width 9 */
    public String hn;
    /** character width 2 */
    public String changwat;
    /** character width 2 */
    public String amphur;
    /** date width 8 */
    public String dob;
    /** character width 1 */
    public String sex;
    /** character width 1 */
    public String marriage;
    /** character width 3 */
    public String occupa;
    /** character width 2 */
    public String nation;
    /** character width 13 */
    public String person_id;
    /** character width 35 */
    public String namepat;
    public String title;
    public String fname;
    public String lname;
    public String idtype;

    
    public PatNh53() {
    }
/**
 */
    public String[] getValueArray() {
        return new String[]{
hcode	,
hn	,
changwat	,
amphur	,
dob	,
sex	,
marriage	,
occupa	,
nation	,
person_id	,
namepat	,
title,
fname,
lname,
idtype
};
    }
    

    public boolean setValue(ResultSet rs) throws Exception {
        PatNh53 p = this;
	p.	hcode	 = Report12FileData.getRsString(rs,	1	);
	p.	hn	 = Report12FileData.getRsString(rs,	2	);
	p.	changwat	 = Report12FileData.getRsString(rs,	3	);
	p.	amphur	 = Report12FileData.getRsString(rs,	4	);
	p.	dob	 = Report12FileData.getRsString(rs,	5	);
	p.	sex	 = Report12FileData.getRsString(rs,	6	);
	p.	marriage	 = Report12FileData.getRsString(rs,	7	);
	p.	occupa	 = Report12FileData.getRsString(rs,	8	);
	p.	nation	 = Report12FileData.getRsString(rs,	9	);
	p.	person_id	 = Report12FileData.getRsString(rs,	10	);
	p.	namepat	 = Report12FileData.getRsString(rs,	11	);
	p.	title	 = Report12FileData.getRsString(rs,	12	);
	p.	fname	 = Report12FileData.getRsString(rs, 13	);
	p.	lname	 = Report12FileData.getRsString(rs,	14	);
	p.	idtype	 = Report12FileData.getRsString(rs,	15	);
         
        return true;
    }
    
    public Rp12OI2 initInstant() {
        return new PatNh53();
    }

    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file53/12file53_pat.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate);
        pm.setString(3,startDate);
        pm.setString(4,endDate);
        return pm;
    }


    public String getFileName() {
        return "PAT";
    }


    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
         RpField.initData("	hcode	","	รหัสสถานพยาบาล	",	5	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	hn	","	เลขประจำตัวผู้ป่วย	",	9	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	changwat	","	รหัสจังหวัด	",	2	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	amphur	","	รหัสอำเภอ	",	2	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	dob	","	วันเกิด	",	8	,	true	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D	)
        ,RpField.initData("	sex	","	เพศ	",	1	,	true	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	marriage	","	สภาพสมรส	",	1	,	true	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	occupa	","	อาชีพ	",	3	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	nation	","	สัญชาติ	",	3	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	person_id	","	เลขบัตรประชาชน	",	13	,	true	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	namepat	","	ชื่อ-สกุล	",	36	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	title	","	คำนำหน้า	",	30	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	fname	","	ชื่อ	",	40	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	lname	","	นามสกุล	",	40	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        ,RpField.initData("	idtype	","	ประเภทบัตร	",	1	,	false	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C	)
        };
        return theRPF;
    }

}
