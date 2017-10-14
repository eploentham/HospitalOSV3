/*
 * Oop.java
 *
 * Created on 8 กันยายน 2548, 12:06 น.
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
public class OopNh53 extends Rp12OI2{

    /** character width 9 */
    public String hn;
    /** date width 8 */
    public String dateopd;
    /** character width 4 */
    public String clinic;
    /** character width 4 */
    public String oper;
    /** character width 6 */
    public String drop;
    public String pid;
    
    public String seq;    
    public OopNh53() {
    }
    public String[] getValueArray() {
        return new String[]{
hn	,
dateopd	,
clinic	,
oper	,
drop	,
pid,
seq

};
    }
    

    public boolean setValue(ResultSet rs) throws Exception {
        OopNh53 p = this;
	p.	hn	 = Report12FileData.getRsString(rs,	1	);
	p.	dateopd	 = Report12FileData.getRsString(rs,	2	);
	p.	clinic	 = Report12FileData.getRsString(rs,	3	);
	p.	oper	 = Report12FileData.getRsString(rs,	4	);
	p.	drop	 = Report12FileData.getRsString(rs,	5	);
	p.	pid	 = Report12FileData.getRsString(rs,	6	);
	p.	seq	 = Report12FileData.getRsString(rs,	7	);
    return true;
    }
    
    public Rp12OI2 initInstant() {
        return new OopNh53();
    }

    public PreparedStatement getPreparedStatement(Connection con, String startDate, String endDate, int mode) throws Exception {
        String sql = IOStream.readInputDefault("config/rp_12file53/12file53_oop.sql");
        PreparedStatement pm = con.prepareStatement(sql);
        pm.setString(1,startDate);
        pm.setString(2,endDate); 
        return pm;
    }


    public String getFileName() {
        return "OOP";
    }


    public RpField[] getRpFields() {
        if(theRPF!=null)
            return theRPF;
        theRPF = new RpField[]{
        RpField.initData("	hn	","	เลขประจำตัวผู้ป่วย	",	9	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C)
        ,RpField.initData("	dateopd	","	วันที่รับบริการ	",	8	,	true	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_D)
        ,RpField.initData("	clinic	","	รหัสคลินิกที่ให้บริการ	",	4	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C)
        ,RpField.initData("	oper	","	รหัสหัตถการตาม ICD 9 CM	",	7	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C)
        ,RpField.initData("	dropid	","	เลขที่ใบประกอบวิชาชีพ	",	6	,	false	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C)
        ,RpField.initData("	person_id	","	เลขประจำตัวบัตรประชาชน	",	13	,	true	,	true	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C)
        ,RpField.initData("	seq	","	เลขการรับบริการ	",	15	,	true	,	false	,	Report12FileData.VALID_ALL	,	DBFField.FIELD_TYPE_C)
              };
        return theRPF;
    }

}
